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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.card_module");
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
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.day_module _day_module = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.editnote _editnote = null;
public b4a.example.httputils2service _httputils2service = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activerecall_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
int _number_of_cards = 0;
 //BA.debugLineNum = 160;BA.debugLine="Private Sub activerecall_Click";
 //BA.debugLineNum = 162;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 163;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 164;BA.debugLine="Dim number_of_cards As Int = subdeckcards.size";
_number_of_cards = _subdeckcards.getSize();
 //BA.debugLineNum = 166;BA.debugLine="If number_of_cards = 0 Then";
if (_number_of_cards==0) { 
 //BA.debugLineNum = 167;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No cards available"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 168;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 170;BA.debugLine="StartActivity(active_recall)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._active_recall.getObject()));
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 34;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayout",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
 //BA.debugLineNum = 40;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayout2",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark2\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
 //BA.debugLineNum = 46;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 47;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayout3",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 49;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark3\")";
mostCurrent._activity.LoadLayout("Card_ModuleLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
 //BA.debugLineNum = 53;BA.debugLine="cc.Initialize(\"CC\")";
mostCurrent._cc.Initialize("CC");
 //BA.debugLineNum = 55;BA.debugLine="subdecklabel.Text = Subdeck_Module.selectedsubdec";
mostCurrent._subdecklabel.setText(BA.ObjectToCharSequence(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ));
 //BA.debugLineNum = 57;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 58;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 61;BA.debugLine="ShowSubdeckCards(subdeckcards)";
_showsubdeckcards(_subdeckcards);
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
 //BA.debugLineNum = 137;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 139;BA.debugLine="If active_recall.praise = True Then";
if (mostCurrent._active_recall._praise /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 140;BA.debugLine="active_recall.praise = False";
mostCurrent._active_recall._praise /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 141;BA.debugLine="MsgboxAsync(\"You Finished Your Sub-Deck\", \"Congr";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You Finished Your Sub-Deck"),BA.ObjectToCharSequence("Congratulations"),processBA);
 };
 //BA.debugLineNum = 144;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 145;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 146;BA.debugLine="ShowSubdeckCards(subdeckcards)";
_showsubdeckcards(_subdeckcards);
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _addbtn_click() throws Exception{
 //BA.debugLineNum = 173;BA.debugLine="Private Sub addbtn_Click";
 //BA.debugLineNum = 175;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
_subdeck = mostCurrent._subdeck_module._selectedsubdeck /*String*/ ;
 //BA.debugLineNum = 176;BA.debugLine="StartActivity(add_card_module2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module2.getObject()));
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 155;BA.debugLine="Private Sub backbtn_Click";
 //BA.debugLineNum = 157;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_click() throws Exception{
 //BA.debugLineNum = 188;BA.debugLine="Private Sub canceldelete_Click";
 //BA.debugLineNum = 189;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _cc_result(boolean _success,String _dir,String _filename) throws Exception{
 //BA.debugLineNum = 197;BA.debugLine="Sub CC_RESULT (Success As Boolean, dir As String ,";
 //BA.debugLineNum = 199;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 200;BA.debugLine="Log(\"Dir: \" & dir)";
anywheresoftware.b4a.keywords.Common.LogImpl("631129603","Dir: "+_dir,0);
 //BA.debugLineNum = 201;BA.debugLine="Log(\"File: \" & fileName)";
anywheresoftware.b4a.keywords.Common.LogImpl("631129604","File: "+_filename,0);
 //BA.debugLineNum = 203;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
 //BA.debugLineNum = 204;BA.debugLine="Log(\"PDF saved as temp.pdf\")";
anywheresoftware.b4a.keywords.Common.LogImpl("631129607","PDF saved as temp.pdf",0);
 //BA.debugLineNum = 206;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
 //BA.debugLineNum = 207;BA.debugLine="ProgressDialogShow(\"Generating Cards...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generating Cards..."));
 //BA.debugLineNum = 208;BA.debugLine="GenerateFlashCardsFromPDF";
_generateflashcardsfrompdf();
 }else {
 //BA.debugLineNum = 211;BA.debugLine="Log(\"User Cancelled\")";
anywheresoftware.b4a.keywords.Common.LogImpl("631129614","User Cancelled",0);
 };
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _cards = null;
 //BA.debugLineNum = 179;BA.debugLine="Private Sub confirmdelete_Click";
 //BA.debugLineNum = 180;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 181;BA.debugLine="Dim cards As List = tappedDeck.Get(Subdeck_Module";
_cards = new anywheresoftware.b4a.objects.collections.List();
_cards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 183;BA.debugLine="cards.RemoveAt(numtag)";
_cards.RemoveAt(_numtag);
 //BA.debugLineNum = 184;BA.debugLine="ShowSubdeckCards(cards)";
_showsubdeckcards(_cards);
 //BA.debugLineNum = 185;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return "";
}
public static String  _deletebtn_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
int _index = 0;
 //BA.debugLineNum = 128;BA.debugLine="Sub deletebtn_click";
 //BA.debugLineNum = 130;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 131;BA.debugLine="Dim index As Int = b.Tag";
_index = (int)(BA.ObjectToNumber(_b.getTag()));
 //BA.debugLineNum = 132;BA.debugLine="numtag = index";
_numtag = _index;
 //BA.debugLineNum = 133;BA.debugLine="deleteconfirmation.Visible = True";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _editbtn_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
int _index = 0;
 //BA.debugLineNum = 116;BA.debugLine="Sub editbtn_Click";
 //BA.debugLineNum = 118;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
_subdeck = mostCurrent._subdeck_module._selectedsubdeck /*String*/ ;
 //BA.debugLineNum = 119;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 120;BA.debugLine="Dim index As Int = b.Tag";
_index = (int)(BA.ObjectToNumber(_b.getTag()));
 //BA.debugLineNum = 121;BA.debugLine="editindex = index";
_editindex = _index;
 //BA.debugLineNum = 122;BA.debugLine="isEdit = True";
_isedit = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 124;BA.debugLine="StartActivity(add_card_module2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module2.getObject()));
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _ftf_btn_click() throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Private Sub ftf_btn_Click";
 //BA.debugLineNum = 194;BA.debugLine="cc.Show(\"application/pdf\", \"Select PDF\")";
mostCurrent._cc.Show(processBA,"application/pdf","Select PDF");
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public static String  _generateflashcardsfrompdf() throws Exception{
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
 //BA.debugLineNum = 215;BA.debugLine="Sub GenerateFlashCardsFromPDF";
 //BA.debugLineNum = 218;BA.debugLine="Dim In As InputStream = File.OpenInput(File.DirIn";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
_in = anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
 //BA.debugLineNum = 219;BA.debugLine="Dim bytes() As Byte = Bit.InputStreamToBytes(In)";
_bytes = anywheresoftware.b4a.keywords.Common.Bit.InputStreamToBytes((java.io.InputStream)(_in.getObject()));
 //BA.debugLineNum = 220;BA.debugLine="In.Close";
_in.Close();
 //BA.debugLineNum = 224;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 225;BA.debugLine="Dim base64 As String = su.EncodeBase64(bytes)";
_base64 = _su.EncodeBase64(_bytes);
 //BA.debugLineNum = 228;BA.debugLine="Dim prompt As String = _     \"Create flashcards f";
_prompt = "Create flashcards from the provided PDF content."+anywheresoftware.b4a.keywords.Common.CRLF+"RULES:"+anywheresoftware.b4a.keywords.Common.CRLF+"- Extract ONLY from the PDF"+anywheresoftware.b4a.keywords.Common.CRLF+"- Return ONLY valid JSON"+anywheresoftware.b4a.keywords.Common.CRLF+"- No markdown, no explanations"+anywheresoftware.b4a.keywords.Common.CRLF+"- Format exactly:"+anywheresoftware.b4a.keywords.Common.CRLF+"{"+"\"flashcards\": ["+"{"+"\"question\": \"...\","+"\"answer\": \"...\""+"}"+"]"+"}";
 //BA.debugLineNum = 245;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 246;BA.debugLine="root.Initialize";
_root.Initialize();
 //BA.debugLineNum = 248;BA.debugLine="Dim contents As List";
_contents = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 249;BA.debugLine="contents.Initialize";
_contents.Initialize();
 //BA.debugLineNum = 251;BA.debugLine="Dim contentItem As Map";
_contentitem = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 252;BA.debugLine="contentItem.Initialize";
_contentitem.Initialize();
 //BA.debugLineNum = 254;BA.debugLine="Dim parts As List";
_parts = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 255;BA.debugLine="parts.Initialize";
_parts.Initialize();
 //BA.debugLineNum = 258;BA.debugLine="Dim filePart As Map";
_filepart = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 259;BA.debugLine="filePart.Initialize";
_filepart.Initialize();
 //BA.debugLineNum = 261;BA.debugLine="Dim inline As Map";
_inline = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 262;BA.debugLine="inline.Initialize";
_inline.Initialize();
 //BA.debugLineNum = 263;BA.debugLine="inline.Put(\"mime_type\", \"application/pdf\")";
_inline.Put((Object)("mime_type"),(Object)("application/pdf"));
 //BA.debugLineNum = 264;BA.debugLine="inline.Put(\"data\", base64)";
_inline.Put((Object)("data"),(Object)(_base64));
 //BA.debugLineNum = 266;BA.debugLine="filePart.Put(\"inline_data\", inline)";
_filepart.Put((Object)("inline_data"),(Object)(_inline.getObject()));
 //BA.debugLineNum = 267;BA.debugLine="parts.Add(filePart)";
_parts.Add((Object)(_filepart.getObject()));
 //BA.debugLineNum = 270;BA.debugLine="Dim textPart As Map";
_textpart = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 271;BA.debugLine="textPart.Initialize";
_textpart.Initialize();
 //BA.debugLineNum = 272;BA.debugLine="textPart.Put(\"text\", prompt)";
_textpart.Put((Object)("text"),(Object)(_prompt));
 //BA.debugLineNum = 273;BA.debugLine="parts.Add(textPart)";
_parts.Add((Object)(_textpart.getObject()));
 //BA.debugLineNum = 275;BA.debugLine="contentItem.Put(\"parts\", parts)";
_contentitem.Put((Object)("parts"),(Object)(_parts.getObject()));
 //BA.debugLineNum = 276;BA.debugLine="contents.Add(contentItem)";
_contents.Add((Object)(_contentitem.getObject()));
 //BA.debugLineNum = 278;BA.debugLine="root.Put(\"contents\", contents)";
_root.Put((Object)("contents"),(Object)(_contents.getObject()));
 //BA.debugLineNum = 280;BA.debugLine="Dim gen As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 281;BA.debugLine="gen.Initialize(root)";
_gen.Initialize(_root);
 //BA.debugLineNum = 283;BA.debugLine="Dim json As String = gen.ToString";
_json = _gen.ToString();
 //BA.debugLineNum = 285;BA.debugLine="Log(\"AUTO PDF REQUEST: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("631195206","AUTO PDF REQUEST: ",0);
 //BA.debugLineNum = 286;BA.debugLine="Log(json)";
anywheresoftware.b4a.keywords.Common.LogImpl("631195207",_json,0);
 //BA.debugLineNum = 288;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+mostCurrent._myapikey;
 //BA.debugLineNum = 290;BA.debugLine="Dim Job As HttpJob";
_job = new b4a.example.httpjob();
 //BA.debugLineNum = 291;BA.debugLine="Job.Initialize(\"GeminiPDF\", Me)";
_job._initialize /*String*/ (processBA,"GeminiPDF",card_module.getObject());
 //BA.debugLineNum = 292;BA.debugLine="Job.PostString(URL, json)";
_job._poststring /*String*/ (_url,_json);
 //BA.debugLineNum = 293;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetContentType("application/json");
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private subdecklabel As Label";
mostCurrent._subdecklabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private deleteconfirmation As Panel";
mostCurrent._deleteconfirmation = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim numtag As Int";
_numtag = 0;
 //BA.debugLineNum = 22;BA.debugLine="Dim j As JSON";
mostCurrent._j = new anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter();
 //BA.debugLineNum = 23;BA.debugLine="Private cc As ContentChooser";
mostCurrent._cc = new anywheresoftware.b4a.phone.Phone.ContentChooser();
 //BA.debugLineNum = 24;BA.debugLine="Private pickPDFBtn As Button";
mostCurrent._pickpdfbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim api1 As String = \"AIzaSyAGccTYG-Mscl_16Z72t\"";
mostCurrent._api1 = "AIzaSyAGccTYG-Mscl_16Z72t";
 //BA.debugLineNum = 26;BA.debugLine="Dim api2 As String = \"-GIN9ITMdrDGhQ\"";
mostCurrent._api2 = "-GIN9ITMdrDGhQ";
 //BA.debugLineNum = 27;BA.debugLine="Dim MyAPIKey As String = api1&api2";
mostCurrent._myapikey = mostCurrent._api1+mostCurrent._api2;
 //BA.debugLineNum = 28;BA.debugLine="Dim AIGlobalText As String";
mostCurrent._aiglobaltext = "";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4a.example.httpjob _job) throws Exception{
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
 //BA.debugLineNum = 297;BA.debugLine="Sub JobDone (job As HttpJob)";
 //BA.debugLineNum = 298;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 299;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 300;BA.debugLine="Dim response As String";
_response = "";
 //BA.debugLineNum = 301;BA.debugLine="response = job.GetString";
_response = _job._getstring /*String*/ ();
 //BA.debugLineNum = 302;BA.debugLine="Log(\"RAW RESPONSE: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("631260677","RAW RESPONSE: ",0);
 //BA.debugLineNum = 303;BA.debugLine="Log(response)";
anywheresoftware.b4a.keywords.Common.LogImpl("631260678",_response,0);
 //BA.debugLineNum = 305;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 306;BA.debugLine="jp.Initialize(response)";
_jp.Initialize(_response);
 //BA.debugLineNum = 308;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
 //BA.debugLineNum = 310;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
_candidates = new anywheresoftware.b4a.objects.collections.List();
_candidates = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("candidates"))));
 //BA.debugLineNum = 312;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
_candidate = new anywheresoftware.b4a.objects.collections.Map();
_candidate = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidates.Get((int) (0))));
 //BA.debugLineNum = 314;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
_content = new anywheresoftware.b4a.objects.collections.Map();
_content = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidate.Get((Object)("content"))));
 //BA.debugLineNum = 316;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
_parts = new anywheresoftware.b4a.objects.collections.List();
_parts = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_content.Get((Object)("parts"))));
 //BA.debugLineNum = 318;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
_firstpart = new anywheresoftware.b4a.objects.collections.Map();
_firstpart = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_parts.Get((int) (0))));
 //BA.debugLineNum = 320;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
_aitext = BA.ObjectToString(_firstpart.Get((Object)("text")));
 //BA.debugLineNum = 322;BA.debugLine="Log(\"AI JSON:\")";
anywheresoftware.b4a.keywords.Common.LogImpl("631260697","AI JSON:",0);
 //BA.debugLineNum = 323;BA.debugLine="Log(aiText)";
anywheresoftware.b4a.keywords.Common.LogImpl("631260698",_aitext,0);
 //BA.debugLineNum = 324;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"cached_ai.txt",_aitext);
 //BA.debugLineNum = 325;BA.debugLine="ParseFlashcard(aiText)";
_parseflashcard(_aitext);
 //BA.debugLineNum = 327;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.G";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 328;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdec";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 331;BA.debugLine="ShowSubdeckCards(subdeckcards)";
_showsubdeckcards(_subdeckcards);
 }else {
 //BA.debugLineNum = 334;BA.debugLine="Log(\"ERROR: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("631260709","ERROR: ",0);
 //BA.debugLineNum = 335;BA.debugLine="Log(job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("631260710",_job._errormessage /*String*/ ,0);
 //BA.debugLineNum = 336;BA.debugLine="Msgbox(\"Error Parsing your PDF file\", \"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error Parsing your PDF file"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 339;BA.debugLine="job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 340;BA.debugLine="End Sub";
return "";
}
public static String  _parseflashcard(String _jsontext) throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _subdeckcards = null;
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.collections.Map _cards = null;
String _question = "";
String _answer = "";
 //BA.debugLineNum = 342;BA.debugLine="Sub ParseFlashcard (jsonText As String)";
 //BA.debugLineNum = 343;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 344;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
_subdeckcards = new anywheresoftware.b4a.objects.collections.List();
_subdeckcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 346;BA.debugLine="Try";
try { //BA.debugLineNum = 348;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 349;BA.debugLine="jp.Initialize(jsonText)";
_jp.Initialize(_jsontext);
 //BA.debugLineNum = 351;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
 //BA.debugLineNum = 353;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("flashcards"))));
 //BA.debugLineNum = 355;BA.debugLine="For Each card As Map In flashcards";
_card = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _flashcards;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group8.Get(index8)));
 //BA.debugLineNum = 357;BA.debugLine="Dim cards As Map";
_cards = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 358;BA.debugLine="cards.Initialize";
_cards.Initialize();
 //BA.debugLineNum = 360;BA.debugLine="Dim question As String = card.Get(\"question\")";
_question = BA.ObjectToString(_card.Get((Object)("question")));
 //BA.debugLineNum = 362;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
_answer = BA.ObjectToString(_card.Get((Object)("answer")));
 //BA.debugLineNum = 364;BA.debugLine="cards.Put(\"Q\", question)";
_cards.Put((Object)("Q"),(Object)(_question));
 //BA.debugLineNum = 365;BA.debugLine="cards.Put(\"A\", answer)";
_cards.Put((Object)("A"),(Object)(_answer));
 //BA.debugLineNum = 366;BA.debugLine="subdeckcards.Add(cards)";
_subdeckcards.Add((Object)(_cards.getObject()));
 //BA.debugLineNum = 368;BA.debugLine="Log(\"===================\")";
anywheresoftware.b4a.keywords.Common.LogImpl("631326234","===================",0);
 //BA.debugLineNum = 369;BA.debugLine="Log(\"QUESTION: \" & question)";
anywheresoftware.b4a.keywords.Common.LogImpl("631326235","QUESTION: "+_question,0);
 //BA.debugLineNum = 370;BA.debugLine="Log(\"ANSWER: \" & answer)";
anywheresoftware.b4a.keywords.Common.LogImpl("631326236","ANSWER: "+_answer,0);
 }
};
 } 
       catch (Exception e21) {
			processBA.setLastException(e21); //BA.debugLineNum = 376;BA.debugLine="Log(\"INVALID JSON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("631326242","INVALID JSON",0);
 };
 //BA.debugLineNum = 380;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim subdeck As String";
_subdeck = "";
 //BA.debugLineNum = 10;BA.debugLine="Dim isEdit As Boolean";
_isedit = false;
 //BA.debugLineNum = 11;BA.debugLine="Dim editindex As Int";
_editindex = 0;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _showsubdeckcards(anywheresoftware.b4a.objects.collections.List _cardslist) throws Exception{
int _toppos = 0;
int _cardheight = 0;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _btnwidth = 0;
anywheresoftware.b4a.objects.ButtonWrapper _editbtn = null;
anywheresoftware.b4a.objects.ButtonWrapper _deletebtn = null;
 //BA.debugLineNum = 65;BA.debugLine="Sub ShowSubdeckCards(cardsList As List)";
 //BA.debugLineNum = 67;BA.debugLine="ScrollView1.Panel.RemoveAllViews";
mostCurrent._scrollview1.getPanel().RemoveAllViews();
 //BA.debugLineNum = 69;BA.debugLine="Dim topPos As Int = 0";
_toppos = (int) (0);
 //BA.debugLineNum = 70;BA.debugLine="Dim cardHeight As Int = 150dip 'height";
_cardheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150));
 //BA.debugLineNum = 72;BA.debugLine="For i = 0 To cardsList.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_cardslist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 73;BA.debugLine="Dim card As Map = cardsList.Get(i)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_cardslist.Get(_i)));
 //BA.debugLineNum = 74;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 76;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 77;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 79;BA.debugLine="p.Color = Colors.Black";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 //BA.debugLineNum = 81;BA.debugLine="ScrollView1.Panel.AddView(p, 10dip, topPos, Scro";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_toppos,(int) (mostCurrent._scrollview1.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_cardheight);
 //BA.debugLineNum = 83;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 84;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 85;BA.debugLine="lbl.Text = \"Q: \" & card.Get(\"Q\") & CRLF & \"A: \"";
_lbl.setText(BA.ObjectToCharSequence("Q: "+BA.ObjectToString(_card.Get((Object)("Q")))+anywheresoftware.b4a.keywords.Common.CRLF+"A: "+BA.ObjectToString(_card.Get((Object)("A")))));
 //BA.debugLineNum = 86;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 87;BA.debugLine="lbl.TextColor = Colors.black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
 //BA.debugLineNum = 89;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 91;BA.debugLine="lbl.TextSize = 12";
_lbl.setTextSize((float) (12));
 //BA.debugLineNum = 92;BA.debugLine="lbl.SingleLine = False";
_lbl.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 94;BA.debugLine="p.AddView(lbl, 10dip, 10dip, ScrollView1.Width -";
_p.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._scrollview1.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 95;BA.debugLine="topPos = topPos + lbl.height + 10dip";
_toppos = (int) (_toppos+_lbl.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 97;BA.debugLine="Dim btnwidth As Int = 100dip";
_btnwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100));
 //BA.debugLineNum = 99;BA.debugLine="Dim editbtn As Button";
_editbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 100;BA.debugLine="editbtn.Initialize(\"Editbtn\") 'btn name";
_editbtn.Initialize(mostCurrent.activityBA,"Editbtn");
 //BA.debugLineNum = 101;BA.debugLine="editbtn.Tag = i 'tag/index";
_editbtn.setTag((Object)(_i));
 //BA.debugLineNum = 102;BA.debugLine="editbtn.Text = \"Edit\" 'button text display";
_editbtn.setText(BA.ObjectToCharSequence("Edit"));
 //BA.debugLineNum = 103;BA.debugLine="p.AddView(editbtn, 30dip, 100dip, btnwidth, 40di";
_p.AddView((android.view.View)(_editbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),_btnwidth,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 105;BA.debugLine="Dim deletebtn As Button";
_deletebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 106;BA.debugLine="deletebtn.Initialize(\"Deletebtn\")";
_deletebtn.Initialize(mostCurrent.activityBA,"Deletebtn");
 //BA.debugLineNum = 107;BA.debugLine="deletebtn.Tag = i";
_deletebtn.setTag((Object)(_i));
 //BA.debugLineNum = 108;BA.debugLine="deletebtn.Text = \"Delete\"";
_deletebtn.setText(BA.ObjectToCharSequence("Delete"));
 //BA.debugLineNum = 109;BA.debugLine="p.AddView(deletebtn, 200dip, 100dip, btnwidth, 4";
_p.AddView((android.view.View)(_deletebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),_btnwidth,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 110;BA.debugLine="topPos = topPos + cardHeight + 10dip";
_toppos = (int) (_toppos+_cardheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 }
};
 //BA.debugLineNum = 112;BA.debugLine="ScrollView1.Panel.Height = topPos + 10dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (_toppos+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
}
