package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class card_module extends Activity implements B4AActivity{
	public static card_module mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.card_module");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (card_module).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.card_module");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.card_module", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (card_module) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (card_module) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return card_module.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (card_module) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (card_module) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            card_module mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (card_module) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static String _subdeck = "";
public static boolean _isedit = false;
public static int _editindex = 0;
public anywheresoftware.b4a.objects.LabelWrapper _subdecklabel = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _deleteconfirmation = null;
public static int _numtag = 0;
public anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter _j = null;
public anywheresoftware.b4a.phone.Phone.ContentChooser _cc = null;
public anywheresoftware.b4a.objects.ButtonWrapper _pickpdfbtn = null;
public static String _api1 = "";
public static String _api2 = "";
public static String _myapikey = "";
public static String _aiglobaltext = "";
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.leaderboard _leaderboard = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.day_module _day_module = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activerecall_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activerecall_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activerecall_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
int _number_of_cards = 0;
RDebugUtils.currentLine=17694720;
 //BA.debugLineNum = 17694720;BA.debugLine="Private Sub activerecall_Click";
RDebugUtils.currentLine=17694722;
 //BA.debugLineNum = 17694722;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=17694723;
 //BA.debugLineNum = 17694723;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=17694724;
 //BA.debugLineNum = 17694724;BA.debugLine="Dim number_of_cards As Int = subdeckcards.size";
_number_of_cards = _subdeckcards.getSize();
RDebugUtils.currentLine=17694726;
 //BA.debugLineNum = 17694726;BA.debugLine="If number_of_cards = 0 Then";
if (_number_of_cards==0) { 
RDebugUtils.currentLine=17694727;
 //BA.debugLineNum = 17694727;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No cards available"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=17694728;
 //BA.debugLineNum = 17694728;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=17694730;
 //BA.debugLineNum = 17694730;BA.debugLine="StartActivity(active_recall)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._active_recall.getObject()));
RDebugUtils.currentLine=17694731;
 //BA.debugLineNum = 17694731;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
RDebugUtils.currentLine=17235968;
 //BA.debugLineNum = 17235968;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=17235969;
 //BA.debugLineNum = 17235969;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=17235971;
 //BA.debugLineNum = 17235971;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17235972;
 //BA.debugLineNum = 17235972;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=17235974;
 //BA.debugLineNum = 17235974;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=17235977;
 //BA.debugLineNum = 17235977;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17235978;
 //BA.debugLineNum = 17235978;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=17235980;
 //BA.debugLineNum = 17235980;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark2\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=17235983;
 //BA.debugLineNum = 17235983;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17235984;
 //BA.debugLineNum = 17235984;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=17235986;
 //BA.debugLineNum = 17235986;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark3\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=17235990;
 //BA.debugLineNum = 17235990;BA.debugLine="cc.Initialize(\"CC\")";
mostCurrent._cc.Initialize("CC");
RDebugUtils.currentLine=17235992;
 //BA.debugLineNum = 17235992;BA.debugLine="subdecklabel.Text = Subdeck_Module.selectedsubdec";
mostCurrent._subdecklabel.setText(BA.ObjectToCharSequence(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ));
RDebugUtils.currentLine=17235994;
 //BA.debugLineNum = 17235994;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=17235995;
 //BA.debugLineNum = 17235995;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=17235998;
 //BA.debugLineNum = 17235998;BA.debugLine="ShowSubdeckCards(subdeckcards)";
_showsubdeckcards(_subdeckcards);
RDebugUtils.currentLine=17236000;
 //BA.debugLineNum = 17236000;BA.debugLine="End Sub";
return "";
}
public static String  _showsubdeckcards(anywheresoftware.b4a.objects.collections.List _cardslist) throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showsubdeckcards", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showsubdeckcards", new Object[] {_cardslist}));}
int _toppos = 0;
int _cardheight = 0;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _btnwidth = 0;
anywheresoftware.b4a.objects.ButtonWrapper _editbtn = null;
anywheresoftware.b4a.objects.ButtonWrapper _deletebtn = null;
RDebugUtils.currentLine=17301504;
 //BA.debugLineNum = 17301504;BA.debugLine="Sub ShowSubdeckCards(cardsList As List)";
RDebugUtils.currentLine=17301506;
 //BA.debugLineNum = 17301506;BA.debugLine="ScrollView1.Panel.RemoveAllViews";
mostCurrent._scrollview1.getPanel().RemoveAllViews();
RDebugUtils.currentLine=17301508;
 //BA.debugLineNum = 17301508;BA.debugLine="Dim topPos As Int = 0";
_toppos = (int) (0);
RDebugUtils.currentLine=17301509;
 //BA.debugLineNum = 17301509;BA.debugLine="Dim cardHeight As Int = 150dip 'height";
_cardheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150));
RDebugUtils.currentLine=17301511;
 //BA.debugLineNum = 17301511;BA.debugLine="For i = 0 To cardsList.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_cardslist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
RDebugUtils.currentLine=17301512;
 //BA.debugLineNum = 17301512;BA.debugLine="Dim card As Map = cardsList.Get(i)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_cardslist.Get(_i)));
RDebugUtils.currentLine=17301513;
 //BA.debugLineNum = 17301513;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=17301514;
 //BA.debugLineNum = 17301514;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=17301515;
 //BA.debugLineNum = 17301515;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17301516;
 //BA.debugLineNum = 17301516;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=17301518;
 //BA.debugLineNum = 17301518;BA.debugLine="p.Color = Colors.Black";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
RDebugUtils.currentLine=17301520;
 //BA.debugLineNum = 17301520;BA.debugLine="ScrollView1.Panel.AddView(p, 10dip, topPos, Scro";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_toppos,(int) (mostCurrent._scrollview1.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_cardheight);
RDebugUtils.currentLine=17301522;
 //BA.debugLineNum = 17301522;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=17301523;
 //BA.debugLineNum = 17301523;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=17301524;
 //BA.debugLineNum = 17301524;BA.debugLine="lbl.Text = \"Q: \" & card.Get(\"Q\") & CRLF & \"A: \"";
_lbl.setText(BA.ObjectToCharSequence("Q: "+BA.ObjectToString(_card.Get((Object)("Q")))+anywheresoftware.b4a.keywords.Common.CRLF+"A: "+BA.ObjectToString(_card.Get((Object)("A")))));
RDebugUtils.currentLine=17301525;
 //BA.debugLineNum = 17301525;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17301526;
 //BA.debugLineNum = 17301526;BA.debugLine="lbl.TextColor = Colors.black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=17301528;
 //BA.debugLineNum = 17301528;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=17301530;
 //BA.debugLineNum = 17301530;BA.debugLine="lbl.TextSize = 12";
_lbl.setTextSize((float) (12));
RDebugUtils.currentLine=17301531;
 //BA.debugLineNum = 17301531;BA.debugLine="lbl.SingleLine = False";
_lbl.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17301533;
 //BA.debugLineNum = 17301533;BA.debugLine="p.AddView(lbl, 10dip, 10dip, ScrollView1.Width -";
_p.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._scrollview1.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=17301534;
 //BA.debugLineNum = 17301534;BA.debugLine="topPos = topPos + lbl.height + 10dip";
_toppos = (int) (_toppos+_lbl.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
RDebugUtils.currentLine=17301536;
 //BA.debugLineNum = 17301536;BA.debugLine="Dim btnwidth As Int = 100dip";
_btnwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100));
RDebugUtils.currentLine=17301538;
 //BA.debugLineNum = 17301538;BA.debugLine="Dim editbtn As Button";
_editbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17301539;
 //BA.debugLineNum = 17301539;BA.debugLine="editbtn.Initialize(\"Editbtn\") 'btn name";
_editbtn.Initialize(mostCurrent.activityBA,"Editbtn");
RDebugUtils.currentLine=17301540;
 //BA.debugLineNum = 17301540;BA.debugLine="editbtn.Tag = i 'tag/index";
_editbtn.setTag((Object)(_i));
RDebugUtils.currentLine=17301541;
 //BA.debugLineNum = 17301541;BA.debugLine="editbtn.Text = \"Edit\" 'button text display";
_editbtn.setText(BA.ObjectToCharSequence("Edit"));
RDebugUtils.currentLine=17301542;
 //BA.debugLineNum = 17301542;BA.debugLine="p.AddView(editbtn, 30dip, 100dip, btnwidth, 40di";
_p.AddView((android.view.View)(_editbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),_btnwidth,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=17301544;
 //BA.debugLineNum = 17301544;BA.debugLine="Dim deletebtn As Button";
_deletebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17301545;
 //BA.debugLineNum = 17301545;BA.debugLine="deletebtn.Initialize(\"Deletebtn\")";
_deletebtn.Initialize(mostCurrent.activityBA,"Deletebtn");
RDebugUtils.currentLine=17301546;
 //BA.debugLineNum = 17301546;BA.debugLine="deletebtn.Tag = i";
_deletebtn.setTag((Object)(_i));
RDebugUtils.currentLine=17301547;
 //BA.debugLineNum = 17301547;BA.debugLine="deletebtn.Text = \"Delete\"";
_deletebtn.setText(BA.ObjectToCharSequence("Delete"));
RDebugUtils.currentLine=17301548;
 //BA.debugLineNum = 17301548;BA.debugLine="p.AddView(deletebtn, 200dip, 100dip, btnwidth, 4";
_p.AddView((android.view.View)(_deletebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),_btnwidth,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=17301549;
 //BA.debugLineNum = 17301549;BA.debugLine="topPos = topPos + cardHeight + 10dip";
_toppos = (int) (_toppos+_cardheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 }
};
RDebugUtils.currentLine=17301551;
 //BA.debugLineNum = 17301551;BA.debugLine="ScrollView1.Panel.Height = topPos + 10dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (_toppos+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
RDebugUtils.currentLine=17301552;
 //BA.debugLineNum = 17301552;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="card_module";
RDebugUtils.currentLine=17563648;
 //BA.debugLineNum = 17563648;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=17563650;
 //BA.debugLineNum = 17563650;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
RDebugUtils.currentLine=17498112;
 //BA.debugLineNum = 17498112;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=17498114;
 //BA.debugLineNum = 17498114;BA.debugLine="If active_recall.praise = True Then";
if (mostCurrent._active_recall._praise /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=17498115;
 //BA.debugLineNum = 17498115;BA.debugLine="active_recall.praise = False";
mostCurrent._active_recall._praise /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=17498116;
 //BA.debugLineNum = 17498116;BA.debugLine="MsgboxAsync(\"You Finished Your Sub-Deck\", \"Congr";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You Finished Your Sub-Deck"),BA.ObjectToCharSequence("Congratulations"),processBA);
 };
RDebugUtils.currentLine=17498119;
 //BA.debugLineNum = 17498119;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=17498120;
 //BA.debugLineNum = 17498120;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=17498121;
 //BA.debugLineNum = 17498121;BA.debugLine="ShowSubdeckCards(subdeckcards)";
_showsubdeckcards(_subdeckcards);
RDebugUtils.currentLine=17498122;
 //BA.debugLineNum = 17498122;BA.debugLine="End Sub";
return "";
}
public static String  _addbtn_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addbtn_click", null));}
RDebugUtils.currentLine=17760256;
 //BA.debugLineNum = 17760256;BA.debugLine="Private Sub addbtn_Click";
RDebugUtils.currentLine=17760258;
 //BA.debugLineNum = 17760258;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
_subdeck = mostCurrent._subdeck_module._selectedsubdeck /*String*/ ;
RDebugUtils.currentLine=17760259;
 //BA.debugLineNum = 17760259;BA.debugLine="StartActivity(add_card_module2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module2.getObject()));
RDebugUtils.currentLine=17760260;
 //BA.debugLineNum = 17760260;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=17629184;
 //BA.debugLineNum = 17629184;BA.debugLine="Private Sub backbtn_Click";
RDebugUtils.currentLine=17629186;
 //BA.debugLineNum = 17629186;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=17629187;
 //BA.debugLineNum = 17629187;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceldelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceldelete_click", null));}
RDebugUtils.currentLine=17891328;
 //BA.debugLineNum = 17891328;BA.debugLine="Private Sub canceldelete_Click";
RDebugUtils.currentLine=17891329;
 //BA.debugLineNum = 17891329;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17891330;
 //BA.debugLineNum = 17891330;BA.debugLine="End Sub";
return "";
}
public static String  _cc_result(boolean _success,String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cc_result", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cc_result", new Object[] {_success,_dir,_filename}));}
RDebugUtils.currentLine=18022400;
 //BA.debugLineNum = 18022400;BA.debugLine="Sub CC_RESULT (Success As Boolean, dir As String ,";
RDebugUtils.currentLine=18022402;
 //BA.debugLineNum = 18022402;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=18022403;
 //BA.debugLineNum = 18022403;BA.debugLine="Log(\"Dir: \" & dir)";
anywheresoftware.b4a.keywords.Common.LogImpl("118022403","Dir: "+_dir,0);
RDebugUtils.currentLine=18022404;
 //BA.debugLineNum = 18022404;BA.debugLine="Log(\"File: \" & fileName)";
anywheresoftware.b4a.keywords.Common.LogImpl("118022404","File: "+_filename,0);
RDebugUtils.currentLine=18022406;
 //BA.debugLineNum = 18022406;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
RDebugUtils.currentLine=18022407;
 //BA.debugLineNum = 18022407;BA.debugLine="Log(\"PDF saved as temp.pdf\")";
anywheresoftware.b4a.keywords.Common.LogImpl("118022407","PDF saved as temp.pdf",0);
RDebugUtils.currentLine=18022409;
 //BA.debugLineNum = 18022409;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
RDebugUtils.currentLine=18022410;
 //BA.debugLineNum = 18022410;BA.debugLine="ProgressDialogShow(\"Generating Cards...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generating Cards..."));
RDebugUtils.currentLine=18022411;
 //BA.debugLineNum = 18022411;BA.debugLine="GenerateFlashCardsFromPDF";
_generateflashcardsfrompdf();
 }else {
RDebugUtils.currentLine=18022414;
 //BA.debugLineNum = 18022414;BA.debugLine="Log(\"User Cancelled\")";
anywheresoftware.b4a.keywords.Common.LogImpl("118022414","User Cancelled",0);
 };
RDebugUtils.currentLine=18022416;
 //BA.debugLineNum = 18022416;BA.debugLine="End Sub";
return "";
}
public static String  _generateflashcardsfrompdf() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "generateflashcardsfrompdf", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "generateflashcardsfrompdf", null));}
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
byte[] _bytes = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
String _base64 = "";
String _prompt = "";
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _contents = null;
anywheresoftware.b4a.objects.collections.Map _contentitem = null;
anywheresoftware.b4a.objects.collections.List _parts = null;
anywheresoftware.b4a.objects.collections.Map _filepart = null;
anywheresoftware.b4a.objects.collections.Map _inline = null;
anywheresoftware.b4a.objects.collections.Map _textpart = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
String _json = "";
String _url = "";
b4a.example.httpjob _job = null;
RDebugUtils.currentLine=18087936;
 //BA.debugLineNum = 18087936;BA.debugLine="Sub GenerateFlashCardsFromPDF";
RDebugUtils.currentLine=18087939;
 //BA.debugLineNum = 18087939;BA.debugLine="Dim In As InputStream = File.OpenInput(File.DirIn";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
_in = anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
RDebugUtils.currentLine=18087940;
 //BA.debugLineNum = 18087940;BA.debugLine="Dim bytes() As Byte = Bit.InputStreamToBytes(In)";
_bytes = anywheresoftware.b4a.keywords.Common.Bit.InputStreamToBytes((java.io.InputStream)(_in.getObject()));
RDebugUtils.currentLine=18087941;
 //BA.debugLineNum = 18087941;BA.debugLine="In.Close";
_in.Close();
RDebugUtils.currentLine=18087945;
 //BA.debugLineNum = 18087945;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
RDebugUtils.currentLine=18087946;
 //BA.debugLineNum = 18087946;BA.debugLine="Dim base64 As String = su.EncodeBase64(bytes)";
_base64 = _su.EncodeBase64(_bytes);
RDebugUtils.currentLine=18087949;
 //BA.debugLineNum = 18087949;BA.debugLine="Dim prompt As String = _     \"Create flashcards f";
_prompt = "Create flashcards from the provided PDF content."+anywheresoftware.b4a.keywords.Common.CRLF+"RULES:"+anywheresoftware.b4a.keywords.Common.CRLF+"- Extract ONLY from the PDF"+anywheresoftware.b4a.keywords.Common.CRLF+"- Return ONLY valid JSON"+anywheresoftware.b4a.keywords.Common.CRLF+"- No markdown, no explanations"+anywheresoftware.b4a.keywords.Common.CRLF+"- Format exactly:"+anywheresoftware.b4a.keywords.Common.CRLF+"{"+"\"flashcards\": ["+"{"+"\"question\": \"...\","+"\"answer\": \"...\""+"}"+"]"+"}";
RDebugUtils.currentLine=18087966;
 //BA.debugLineNum = 18087966;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18087967;
 //BA.debugLineNum = 18087967;BA.debugLine="root.Initialize";
_root.Initialize();
RDebugUtils.currentLine=18087969;
 //BA.debugLineNum = 18087969;BA.debugLine="Dim contents As List";
_contents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=18087970;
 //BA.debugLineNum = 18087970;BA.debugLine="contents.Initialize";
_contents.Initialize();
RDebugUtils.currentLine=18087972;
 //BA.debugLineNum = 18087972;BA.debugLine="Dim contentItem As Map";
_contentitem = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18087973;
 //BA.debugLineNum = 18087973;BA.debugLine="contentItem.Initialize";
_contentitem.Initialize();
RDebugUtils.currentLine=18087975;
 //BA.debugLineNum = 18087975;BA.debugLine="Dim parts As List";
_parts = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=18087976;
 //BA.debugLineNum = 18087976;BA.debugLine="parts.Initialize";
_parts.Initialize();
RDebugUtils.currentLine=18087979;
 //BA.debugLineNum = 18087979;BA.debugLine="Dim filePart As Map";
_filepart = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18087980;
 //BA.debugLineNum = 18087980;BA.debugLine="filePart.Initialize";
_filepart.Initialize();
RDebugUtils.currentLine=18087982;
 //BA.debugLineNum = 18087982;BA.debugLine="Dim inline As Map";
_inline = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18087983;
 //BA.debugLineNum = 18087983;BA.debugLine="inline.Initialize";
_inline.Initialize();
RDebugUtils.currentLine=18087984;
 //BA.debugLineNum = 18087984;BA.debugLine="inline.Put(\"mime_type\", \"application/pdf\")";
_inline.Put((Object)("mime_type"),(Object)("application/pdf"));
RDebugUtils.currentLine=18087985;
 //BA.debugLineNum = 18087985;BA.debugLine="inline.Put(\"data\", base64)";
_inline.Put((Object)("data"),(Object)(_base64));
RDebugUtils.currentLine=18087987;
 //BA.debugLineNum = 18087987;BA.debugLine="filePart.Put(\"inline_data\", inline)";
_filepart.Put((Object)("inline_data"),(Object)(_inline.getObject()));
RDebugUtils.currentLine=18087988;
 //BA.debugLineNum = 18087988;BA.debugLine="parts.Add(filePart)";
_parts.Add((Object)(_filepart.getObject()));
RDebugUtils.currentLine=18087991;
 //BA.debugLineNum = 18087991;BA.debugLine="Dim textPart As Map";
_textpart = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18087992;
 //BA.debugLineNum = 18087992;BA.debugLine="textPart.Initialize";
_textpart.Initialize();
RDebugUtils.currentLine=18087993;
 //BA.debugLineNum = 18087993;BA.debugLine="textPart.Put(\"text\", prompt)";
_textpart.Put((Object)("text"),(Object)(_prompt));
RDebugUtils.currentLine=18087994;
 //BA.debugLineNum = 18087994;BA.debugLine="parts.Add(textPart)";
_parts.Add((Object)(_textpart.getObject()));
RDebugUtils.currentLine=18087996;
 //BA.debugLineNum = 18087996;BA.debugLine="contentItem.Put(\"parts\", parts)";
_contentitem.Put((Object)("parts"),(Object)(_parts.getObject()));
RDebugUtils.currentLine=18087997;
 //BA.debugLineNum = 18087997;BA.debugLine="contents.Add(contentItem)";
_contents.Add((Object)(_contentitem.getObject()));
RDebugUtils.currentLine=18087999;
 //BA.debugLineNum = 18087999;BA.debugLine="root.Put(\"contents\", contents)";
_root.Put((Object)("contents"),(Object)(_contents.getObject()));
RDebugUtils.currentLine=18088001;
 //BA.debugLineNum = 18088001;BA.debugLine="Dim gen As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=18088002;
 //BA.debugLineNum = 18088002;BA.debugLine="gen.Initialize(root)";
_gen.Initialize(_root);
RDebugUtils.currentLine=18088004;
 //BA.debugLineNum = 18088004;BA.debugLine="Dim json As String = gen.ToString";
_json = _gen.ToString();
RDebugUtils.currentLine=18088006;
 //BA.debugLineNum = 18088006;BA.debugLine="Log(\"AUTO PDF REQUEST: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("118088006","AUTO PDF REQUEST: ",0);
RDebugUtils.currentLine=18088007;
 //BA.debugLineNum = 18088007;BA.debugLine="Log(json)";
anywheresoftware.b4a.keywords.Common.LogImpl("118088007",_json,0);
RDebugUtils.currentLine=18088009;
 //BA.debugLineNum = 18088009;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+mostCurrent._myapikey;
RDebugUtils.currentLine=18088011;
 //BA.debugLineNum = 18088011;BA.debugLine="Dim Job As HttpJob";
_job = new b4a.example.httpjob();
RDebugUtils.currentLine=18088012;
 //BA.debugLineNum = 18088012;BA.debugLine="Job.Initialize(\"GeminiPDF\", Me)";
_job._initialize /*String*/ (null,processBA,"GeminiPDF",card_module.getObject());
RDebugUtils.currentLine=18088013;
 //BA.debugLineNum = 18088013;BA.debugLine="Job.PostString(URL, json)";
_job._poststring /*String*/ (null,_url,_json);
RDebugUtils.currentLine=18088014;
 //BA.debugLineNum = 18088014;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null).SetContentType("application/json");
RDebugUtils.currentLine=18088016;
 //BA.debugLineNum = 18088016;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmdelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmdelete_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _cards = null;
RDebugUtils.currentLine=17825792;
 //BA.debugLineNum = 17825792;BA.debugLine="Private Sub confirmdelete_Click";
RDebugUtils.currentLine=17825793;
 //BA.debugLineNum = 17825793;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=17825794;
 //BA.debugLineNum = 17825794;BA.debugLine="Dim cards As List = tappedDeck.Get(Subdeck_Module";
_cards = new anywheresoftware.b4a.objects.collections.List();
_cards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=17825796;
 //BA.debugLineNum = 17825796;BA.debugLine="cards.RemoveAt(numtag)";
_cards.RemoveAt(_numtag);
RDebugUtils.currentLine=17825797;
 //BA.debugLineNum = 17825797;BA.debugLine="ShowSubdeckCards(cards)";
_showsubdeckcards(_cards);
RDebugUtils.currentLine=17825798;
 //BA.debugLineNum = 17825798;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17825799;
 //BA.debugLineNum = 17825799;BA.debugLine="End Sub";
return "";
}
public static String  _deletebtn_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletebtn_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
int _index = 0;
RDebugUtils.currentLine=17432576;
 //BA.debugLineNum = 17432576;BA.debugLine="Sub deletebtn_click";
RDebugUtils.currentLine=17432578;
 //BA.debugLineNum = 17432578;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=17432579;
 //BA.debugLineNum = 17432579;BA.debugLine="Dim index As Int = b.Tag";
_index = (int)(BA.ObjectToNumber(_b.getTag()));
RDebugUtils.currentLine=17432580;
 //BA.debugLineNum = 17432580;BA.debugLine="numtag = index";
_numtag = _index;
RDebugUtils.currentLine=17432581;
 //BA.debugLineNum = 17432581;BA.debugLine="deleteconfirmation.Visible = True";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17432583;
 //BA.debugLineNum = 17432583;BA.debugLine="End Sub";
return "";
}
public static String  _editbtn_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "editbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "editbtn_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
int _index = 0;
RDebugUtils.currentLine=17367040;
 //BA.debugLineNum = 17367040;BA.debugLine="Sub editbtn_Click";
RDebugUtils.currentLine=17367042;
 //BA.debugLineNum = 17367042;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
_subdeck = mostCurrent._subdeck_module._selectedsubdeck /*String*/ ;
RDebugUtils.currentLine=17367043;
 //BA.debugLineNum = 17367043;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=17367044;
 //BA.debugLineNum = 17367044;BA.debugLine="Dim index As Int = b.Tag";
_index = (int)(BA.ObjectToNumber(_b.getTag()));
RDebugUtils.currentLine=17367045;
 //BA.debugLineNum = 17367045;BA.debugLine="editindex = index";
_editindex = _index;
RDebugUtils.currentLine=17367046;
 //BA.debugLineNum = 17367046;BA.debugLine="isEdit = True";
_isedit = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=17367048;
 //BA.debugLineNum = 17367048;BA.debugLine="StartActivity(add_card_module2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module2.getObject()));
RDebugUtils.currentLine=17367050;
 //BA.debugLineNum = 17367050;BA.debugLine="End Sub";
return "";
}
public static String  _ftf_btn_click() throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ftf_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ftf_btn_click", null));}
RDebugUtils.currentLine=17956864;
 //BA.debugLineNum = 17956864;BA.debugLine="Private Sub ftf_btn_Click";
RDebugUtils.currentLine=17956865;
 //BA.debugLineNum = 17956865;BA.debugLine="cc.Show(\"application/pdf\", \"Select PDF\")";
mostCurrent._cc.Show(processBA,"application/pdf","Select PDF");
RDebugUtils.currentLine=17956866;
 //BA.debugLineNum = 17956866;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4a.example.httpjob _job) throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "jobdone", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "jobdone", new Object[] {_job}));}
String _response = "";
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _candidates = null;
anywheresoftware.b4a.objects.collections.Map _candidate = null;
anywheresoftware.b4a.objects.collections.Map _content = null;
anywheresoftware.b4a.objects.collections.List _parts = null;
anywheresoftware.b4a.objects.collections.Map _firstpart = null;
String _aitext = "";
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
RDebugUtils.currentLine=18153472;
 //BA.debugLineNum = 18153472;BA.debugLine="Sub JobDone (job As HttpJob)";
RDebugUtils.currentLine=18153473;
 //BA.debugLineNum = 18153473;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
RDebugUtils.currentLine=18153474;
 //BA.debugLineNum = 18153474;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
RDebugUtils.currentLine=18153475;
 //BA.debugLineNum = 18153475;BA.debugLine="Dim response As String";
_response = "";
RDebugUtils.currentLine=18153476;
 //BA.debugLineNum = 18153476;BA.debugLine="response = job.GetString";
_response = _job._getstring /*String*/ (null);
RDebugUtils.currentLine=18153477;
 //BA.debugLineNum = 18153477;BA.debugLine="Log(\"RAW RESPONSE: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("118153477","RAW RESPONSE: ",0);
RDebugUtils.currentLine=18153478;
 //BA.debugLineNum = 18153478;BA.debugLine="Log(response)";
anywheresoftware.b4a.keywords.Common.LogImpl("118153478",_response,0);
RDebugUtils.currentLine=18153480;
 //BA.debugLineNum = 18153480;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=18153481;
 //BA.debugLineNum = 18153481;BA.debugLine="jp.Initialize(response)";
_jp.Initialize(_response);
RDebugUtils.currentLine=18153483;
 //BA.debugLineNum = 18153483;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=18153485;
 //BA.debugLineNum = 18153485;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
_candidates = new anywheresoftware.b4a.objects.collections.List();
_candidates = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("candidates"))));
RDebugUtils.currentLine=18153487;
 //BA.debugLineNum = 18153487;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
_candidate = new anywheresoftware.b4a.objects.collections.Map();
_candidate = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidates.Get((int) (0))));
RDebugUtils.currentLine=18153489;
 //BA.debugLineNum = 18153489;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
_content = new anywheresoftware.b4a.objects.collections.Map();
_content = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidate.Get((Object)("content"))));
RDebugUtils.currentLine=18153491;
 //BA.debugLineNum = 18153491;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
_parts = new anywheresoftware.b4a.objects.collections.List();
_parts = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_content.Get((Object)("parts"))));
RDebugUtils.currentLine=18153493;
 //BA.debugLineNum = 18153493;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
_firstpart = new anywheresoftware.b4a.objects.collections.Map();
_firstpart = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_parts.Get((int) (0))));
RDebugUtils.currentLine=18153495;
 //BA.debugLineNum = 18153495;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
_aitext = BA.ObjectToString(_firstpart.Get((Object)("text")));
RDebugUtils.currentLine=18153497;
 //BA.debugLineNum = 18153497;BA.debugLine="Log(\"AI JSON:\")";
anywheresoftware.b4a.keywords.Common.LogImpl("118153497","AI JSON:",0);
RDebugUtils.currentLine=18153498;
 //BA.debugLineNum = 18153498;BA.debugLine="Log(aiText)";
anywheresoftware.b4a.keywords.Common.LogImpl("118153498",_aitext,0);
RDebugUtils.currentLine=18153499;
 //BA.debugLineNum = 18153499;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"cached_ai.txt",_aitext);
RDebugUtils.currentLine=18153500;
 //BA.debugLineNum = 18153500;BA.debugLine="ParseFlashcard(aiText)";
_parseflashcard(_aitext);
RDebugUtils.currentLine=18153502;
 //BA.debugLineNum = 18153502;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.G";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=18153503;
 //BA.debugLineNum = 18153503;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdec";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=18153506;
 //BA.debugLineNum = 18153506;BA.debugLine="ShowSubdeckCards(subdeckcards)";
_showsubdeckcards(_subdeckcards);
 }else {
RDebugUtils.currentLine=18153509;
 //BA.debugLineNum = 18153509;BA.debugLine="Log(\"ERROR: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("118153509","ERROR: ",0);
RDebugUtils.currentLine=18153510;
 //BA.debugLineNum = 18153510;BA.debugLine="Log(job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("118153510",_job._errormessage /*String*/ ,0);
RDebugUtils.currentLine=18153511;
 //BA.debugLineNum = 18153511;BA.debugLine="Msgbox(\"Error Parsing your PDF file\", \"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error Parsing your PDF file"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 };
RDebugUtils.currentLine=18153514;
 //BA.debugLineNum = 18153514;BA.debugLine="job.Release";
_job._release /*String*/ (null);
RDebugUtils.currentLine=18153515;
 //BA.debugLineNum = 18153515;BA.debugLine="End Sub";
return "";
}
public static String  _parseflashcard(String _jsontext) throws Exception{
RDebugUtils.currentModule="card_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "parseflashcard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "parseflashcard", new Object[] {_jsontext}));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.collections.Map _cards = null;
String _question = "";
String _answer = "";
RDebugUtils.currentLine=18219008;
 //BA.debugLineNum = 18219008;BA.debugLine="Sub ParseFlashcard (jsonText As String)";
RDebugUtils.currentLine=18219009;
 //BA.debugLineNum = 18219009;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=18219010;
 //BA.debugLineNum = 18219010;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=18219012;
 //BA.debugLineNum = 18219012;BA.debugLine="Try";
try {RDebugUtils.currentLine=18219014;
 //BA.debugLineNum = 18219014;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=18219015;
 //BA.debugLineNum = 18219015;BA.debugLine="jp.Initialize(jsonText)";
_jp.Initialize(_jsontext);
RDebugUtils.currentLine=18219017;
 //BA.debugLineNum = 18219017;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=18219019;
 //BA.debugLineNum = 18219019;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("flashcards"))));
RDebugUtils.currentLine=18219021;
 //BA.debugLineNum = 18219021;BA.debugLine="For Each card As Map In flashcards";
_card = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _flashcards;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group8.Get(index8)));
RDebugUtils.currentLine=18219023;
 //BA.debugLineNum = 18219023;BA.debugLine="Dim cards As Map";
_cards = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18219024;
 //BA.debugLineNum = 18219024;BA.debugLine="cards.Initialize";
_cards.Initialize();
RDebugUtils.currentLine=18219026;
 //BA.debugLineNum = 18219026;BA.debugLine="Dim question As String = card.Get(\"question\")";
_question = BA.ObjectToString(_card.Get((Object)("question")));
RDebugUtils.currentLine=18219028;
 //BA.debugLineNum = 18219028;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
_answer = BA.ObjectToString(_card.Get((Object)("answer")));
RDebugUtils.currentLine=18219030;
 //BA.debugLineNum = 18219030;BA.debugLine="cards.Put(\"Q\", question)";
_cards.Put((Object)("Q"),(Object)(_question));
RDebugUtils.currentLine=18219031;
 //BA.debugLineNum = 18219031;BA.debugLine="cards.Put(\"A\", answer)";
_cards.Put((Object)("A"),(Object)(_answer));
RDebugUtils.currentLine=18219032;
 //BA.debugLineNum = 18219032;BA.debugLine="subdeckcards.Add(cards)";
_subdeckcards.Add((Object)(_cards.getObject()));
RDebugUtils.currentLine=18219034;
 //BA.debugLineNum = 18219034;BA.debugLine="Log(\"===================\")";
anywheresoftware.b4a.keywords.Common.LogImpl("118219034","===================",0);
RDebugUtils.currentLine=18219035;
 //BA.debugLineNum = 18219035;BA.debugLine="Log(\"QUESTION: \" & question)";
anywheresoftware.b4a.keywords.Common.LogImpl("118219035","QUESTION: "+_question,0);
RDebugUtils.currentLine=18219036;
 //BA.debugLineNum = 18219036;BA.debugLine="Log(\"ANSWER: \" & answer)";
anywheresoftware.b4a.keywords.Common.LogImpl("118219036","ANSWER: "+_answer,0);
 }
};
 } 
       catch (Exception e21) {
			processBA.setLastException(e21);RDebugUtils.currentLine=18219042;
 //BA.debugLineNum = 18219042;BA.debugLine="Log(\"INVALID JSON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("118219042","INVALID JSON",0);
 };
RDebugUtils.currentLine=18219046;
 //BA.debugLineNum = 18219046;BA.debugLine="End Sub";
return "";
}
}