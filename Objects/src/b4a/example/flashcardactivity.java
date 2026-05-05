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

public class flashcardactivity extends Activity implements B4AActivity{
	public static flashcardactivity mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.flashcardactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (flashcardactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.flashcardactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.flashcardactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (flashcardactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (flashcardactivity) Resume **");
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
		return flashcardactivity.class;
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
            BA.LogInfo("** Activity (flashcardactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (flashcardactivity) Pause event (activity is not paused). **");
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
            flashcardactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (flashcardactivity) Resume **");
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
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static anywheresoftware.b4a.objects.collections.Map _deck = null;
public static String _selecteddeck = "";
public static String _item_longclick = "";
public static b4a.example3.keyvaluestore _kvs = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvdecks = null;
public anywheresoftware.b4a.objects.ButtonWrapper _addbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _addpanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _et1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _decknamelabel = null;
public anywheresoftware.b4a.objects.PanelWrapper _decksettingpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _renamepanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _newdeckname = null;
public anywheresoftware.b4a.objects.PanelWrapper _newsubdeckpanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _newsubdecket = null;
public anywheresoftware.b4a.objects.PanelWrapper _deleteconfirmation = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.card_module _card_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.day_module _day_module = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.editnote _editnote = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
int _radius = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=19398656;
 //BA.debugLineNum = 19398656;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=19398660;
 //BA.debugLineNum = 19398660;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=19398661;
 //BA.debugLineNum = 19398661;BA.debugLine="Activity.LoadLayout(\"FlashCardLayout\")";
mostCurrent._activity.LoadLayout("FlashCardLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=19398663;
 //BA.debugLineNum = 19398663;BA.debugLine="Activity.LoadLayout(\"FlashCardLayoutDark\")";
mostCurrent._activity.LoadLayout("FlashCardLayoutDark",mostCurrent.activityBA);
 };
RDebugUtils.currentLine=19398667;
 //BA.debugLineNum = 19398667;BA.debugLine="Dim radius As Int = Addbtn.Width/2";
_radius = (int) (mostCurrent._addbtn.getWidth()/(double)2);
RDebugUtils.currentLine=19398668;
 //BA.debugLineNum = 19398668;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=19398669;
 //BA.debugLineNum = 19398669;BA.debugLine="cd.Initialize(Colors.Gray, radius)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Gray,_radius);
RDebugUtils.currentLine=19398670;
 //BA.debugLineNum = 19398670;BA.debugLine="Addbtn.Background = cd";
mostCurrent._addbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=19398673;
 //BA.debugLineNum = 19398673;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=19398674;
 //BA.debugLineNum = 19398674;BA.debugLine="LVdecks.SingleLineLayout.Label.textcolor = Color";
mostCurrent._lvdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=19398676;
 //BA.debugLineNum = 19398676;BA.debugLine="LVdecks.SingleLineLayout.Label.textcolor = Color";
mostCurrent._lvdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=19398680;
 //BA.debugLineNum = 19398680;BA.debugLine="kvs = Starter.deckKvs";
_kvs = mostCurrent._starter._deckkvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=19398681;
 //BA.debugLineNum = 19398681;BA.debugLine="deck = Starter.deck";
_deck = mostCurrent._starter._deck /*anywheresoftware.b4a.objects.collections.Map*/ ;
RDebugUtils.currentLine=19398686;
 //BA.debugLineNum = 19398686;BA.debugLine="refreshbtn_Click";
_refreshbtn_click();
RDebugUtils.currentLine=19398689;
 //BA.debugLineNum = 19398689;BA.debugLine="End Sub";
return "";
}
public static String  _refreshbtn_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "refreshbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "refreshbtn_click", null));}
String _deckname = "";
RDebugUtils.currentLine=19857408;
 //BA.debugLineNum = 19857408;BA.debugLine="Private Sub refreshbtn_Click";
RDebugUtils.currentLine=19857410;
 //BA.debugLineNum = 19857410;BA.debugLine="LVdecks.clear";
mostCurrent._lvdecks.Clear();
RDebugUtils.currentLine=19857411;
 //BA.debugLineNum = 19857411;BA.debugLine="For Each deckName As String In deck.keys";
{
final anywheresoftware.b4a.BA.IterableList group2 = _deck.Keys();
final int groupLen2 = group2.getSize()
;int index2 = 0;
;
for (; index2 < groupLen2;index2++){
_deckname = BA.ObjectToString(group2.Get(index2));
RDebugUtils.currentLine=19857412;
 //BA.debugLineNum = 19857412;BA.debugLine="LVdecks.AddSingleLine(deckName)";
mostCurrent._lvdecks.AddSingleLine(BA.ObjectToCharSequence(_deckname));
 }
};
RDebugUtils.currentLine=19857414;
 //BA.debugLineNum = 19857414;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="flashcardactivity";
RDebugUtils.currentLine=19529728;
 //BA.debugLineNum = 19529728;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=19529730;
 //BA.debugLineNum = 19529730;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=19464192;
 //BA.debugLineNum = 19464192;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=19464194;
 //BA.debugLineNum = 19464194;BA.debugLine="End Sub";
return "";
}
public static String  _addbtn_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addbtn_click", null));}
RDebugUtils.currentLine=19660800;
 //BA.debugLineNum = 19660800;BA.debugLine="Private Sub Addbtn_Click";
RDebugUtils.currentLine=19660802;
 //BA.debugLineNum = 19660802;BA.debugLine="If addpanel.Visible = True Then";
if (mostCurrent._addpanel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=19660803;
 //BA.debugLineNum = 19660803;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=19660805;
 //BA.debugLineNum = 19660805;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=19660806;
 //BA.debugLineNum = 19660806;BA.debugLine="End Sub";
return "";
}
public static String  _addcards_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addcards_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addcards_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=20054016;
 //BA.debugLineNum = 20054016;BA.debugLine="Private Sub addcards_Click";
RDebugUtils.currentLine=20054017;
 //BA.debugLineNum = 20054017;BA.debugLine="Dim tappeddeck As Map = deck.Get(item_longclick)";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_deck.Get((Object)(_item_longclick))));
RDebugUtils.currentLine=20054018;
 //BA.debugLineNum = 20054018;BA.debugLine="If tappeddeck.Size = 0 Then";
if (_tappeddeck.getSize()==0) { 
RDebugUtils.currentLine=20054019;
 //BA.debugLineNum = 20054019;BA.debugLine="MsgboxAsync(\"Create A Sub-Deck first\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Create A Sub-Deck first"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20054020;
 //BA.debugLineNum = 20054020;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=20054022;
 //BA.debugLineNum = 20054022;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20054023;
 //BA.debugLineNum = 20054023;BA.debugLine="StartActivity(Add_card_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module.getObject()));
RDebugUtils.currentLine=20054024;
 //BA.debugLineNum = 20054024;BA.debugLine="End Sub";
return "";
}
public static String  _browse_cards_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "browse_cards_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "browse_cards_click", null));}
RDebugUtils.currentLine=20316160;
 //BA.debugLineNum = 20316160;BA.debugLine="Private Sub browse_cards_Click";
RDebugUtils.currentLine=20316161;
 //BA.debugLineNum = 20316161;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20316162;
 //BA.debugLineNum = 20316162;BA.debugLine="StartActivity(deck_all_cards)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._deck_all_cards.getObject()));
RDebugUtils.currentLine=20316163;
 //BA.debugLineNum = 20316163;BA.debugLine="End Sub";
return "";
}
public static String  _cancel_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancel_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancel_click", null));}
RDebugUtils.currentLine=19726336;
 //BA.debugLineNum = 19726336;BA.debugLine="Private Sub cancel_Click";
RDebugUtils.currentLine=19726338;
 //BA.debugLineNum = 19726338;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19726340;
 //BA.debugLineNum = 19726340;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=19726341;
 //BA.debugLineNum = 19726341;BA.debugLine="End Sub";
return "";
}
public static String  _cancelbtn_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelbtn_click", null));}
RDebugUtils.currentLine=20381696;
 //BA.debugLineNum = 20381696;BA.debugLine="Private Sub cancelbtn_Click";
RDebugUtils.currentLine=20381697;
 //BA.debugLineNum = 20381697;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20381698;
 //BA.debugLineNum = 20381698;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceldelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceldelete_click", null));}
RDebugUtils.currentLine=20774912;
 //BA.debugLineNum = 20774912;BA.debugLine="Private Sub canceldelete_Click";
RDebugUtils.currentLine=20774913;
 //BA.debugLineNum = 20774913;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20774914;
 //BA.debugLineNum = 20774914;BA.debugLine="End Sub";
return "";
}
public static String  _cancelnew_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelnew_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelnew_click", null));}
RDebugUtils.currentLine=20512768;
 //BA.debugLineNum = 20512768;BA.debugLine="Private Sub cancelnew_Click";
RDebugUtils.currentLine=20512769;
 //BA.debugLineNum = 20512769;BA.debugLine="newdeckname.text = False";
mostCurrent._newdeckname.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.False));
RDebugUtils.currentLine=20512770;
 //BA.debugLineNum = 20512770;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20512771;
 //BA.debugLineNum = 20512771;BA.debugLine="End Sub";
return "";
}
public static String  _cancelnewsubdeck_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelnewsubdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelnewsubdeck_click", null));}
RDebugUtils.currentLine=20643840;
 //BA.debugLineNum = 20643840;BA.debugLine="Private Sub cancelnewsubdeck_Click";
RDebugUtils.currentLine=20643841;
 //BA.debugLineNum = 20643841;BA.debugLine="newsubdecket.Text = \"\"";
mostCurrent._newsubdecket.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=20643842;
 //BA.debugLineNum = 20643842;BA.debugLine="newsubdeckpanel.Visible = False";
mostCurrent._newsubdeckpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20643843;
 //BA.debugLineNum = 20643843;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmdelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmdelete_click", null));}
RDebugUtils.currentLine=20709376;
 //BA.debugLineNum = 20709376;BA.debugLine="Private Sub confirmdelete_Click";
RDebugUtils.currentLine=20709377;
 //BA.debugLineNum = 20709377;BA.debugLine="deck.Remove(item_longclick)";
_deck.Remove((Object)(_item_longclick));
RDebugUtils.currentLine=20709378;
 //BA.debugLineNum = 20709378;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20709379;
 //BA.debugLineNum = 20709379;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20709380;
 //BA.debugLineNum = 20709380;BA.debugLine="refreshbtn_Click";
_refreshbtn_click();
RDebugUtils.currentLine=20709381;
 //BA.debugLineNum = 20709381;BA.debugLine="End Sub";
return "";
}
public static String  _confirmnew_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmnew_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmnew_click", null));}
anywheresoftware.b4a.objects.collections.Map _getsubdeck = null;
String _names = "";
RDebugUtils.currentLine=20447232;
 //BA.debugLineNum = 20447232;BA.debugLine="Private Sub confirmnew_Click";
RDebugUtils.currentLine=20447233;
 //BA.debugLineNum = 20447233;BA.debugLine="Dim getsubdeck As Map";
_getsubdeck = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=20447234;
 //BA.debugLineNum = 20447234;BA.debugLine="If newdeckname.Text = \"\" Then";
if ((mostCurrent._newdeckname.getText()).equals("")) { 
RDebugUtils.currentLine=20447235;
 //BA.debugLineNum = 20447235;BA.debugLine="MsgboxAsync(\"New Name must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("New Name must have a name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20447236;
 //BA.debugLineNum = 20447236;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=20447238;
 //BA.debugLineNum = 20447238;BA.debugLine="For Each names As String In deck.keys";
{
final anywheresoftware.b4a.BA.IterableList group6 = _deck.Keys();
final int groupLen6 = group6.getSize()
;int index6 = 0;
;
for (; index6 < groupLen6;index6++){
_names = BA.ObjectToString(group6.Get(index6));
RDebugUtils.currentLine=20447239;
 //BA.debugLineNum = 20447239;BA.debugLine="getsubdeck = deck.Get(item_longclick)";
_getsubdeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_deck.Get((Object)(_item_longclick))));
RDebugUtils.currentLine=20447240;
 //BA.debugLineNum = 20447240;BA.debugLine="If newdeckname.Text = names Then";
if ((mostCurrent._newdeckname.getText()).equals(_names)) { 
RDebugUtils.currentLine=20447241;
 //BA.debugLineNum = 20447241;BA.debugLine="MsgboxAsync(\"Sub-Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20447242;
 //BA.debugLineNum = 20447242;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=20447245;
 //BA.debugLineNum = 20447245;BA.debugLine="deck.Remove(item_longclick)";
_deck.Remove((Object)(_item_longclick));
RDebugUtils.currentLine=20447246;
 //BA.debugLineNum = 20447246;BA.debugLine="deck.Put(newdeckname.Text, getsubdeck)";
_deck.Put((Object)(mostCurrent._newdeckname.getText()),(Object)(_getsubdeck.getObject()));
RDebugUtils.currentLine=20447247;
 //BA.debugLineNum = 20447247;BA.debugLine="newdeckname.text = \"\"";
mostCurrent._newdeckname.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=20447248;
 //BA.debugLineNum = 20447248;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=20447249;
 //BA.debugLineNum = 20447249;BA.debugLine="refreshbtn_Click";
_refreshbtn_click();
RDebugUtils.currentLine=20447250;
 //BA.debugLineNum = 20447250;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20447251;
 //BA.debugLineNum = 20447251;BA.debugLine="End Sub";
return "";
}
public static String  _savedecks() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savedecks", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savedecks", null));}
RDebugUtils.currentLine=19595264;
 //BA.debugLineNum = 19595264;BA.debugLine="Sub SaveDecks";
RDebugUtils.currentLine=19595265;
 //BA.debugLineNum = 19595265;BA.debugLine="kvs.Put(\"deck_data\", deck)";
_kvs._put("deck_data",(Object)(_deck.getObject()));
RDebugUtils.currentLine=19595266;
 //BA.debugLineNum = 19595266;BA.debugLine="End Sub";
return "";
}
public static String  _create_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "create_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "create_click", null));}
anywheresoftware.b4a.objects.collections.Map _newsubdeck = null;
String _names = "";
RDebugUtils.currentLine=19791872;
 //BA.debugLineNum = 19791872;BA.debugLine="Private Sub create_Click";
RDebugUtils.currentLine=19791875;
 //BA.debugLineNum = 19791875;BA.debugLine="Dim newsubdeck As Map";
_newsubdeck = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=19791877;
 //BA.debugLineNum = 19791877;BA.debugLine="newsubdeck.initialize";
_newsubdeck.Initialize();
RDebugUtils.currentLine=19791880;
 //BA.debugLineNum = 19791880;BA.debugLine="For Each names As String In deck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _deck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_names = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=19791881;
 //BA.debugLineNum = 19791881;BA.debugLine="If et1.Text = names Then";
if ((mostCurrent._et1.getText()).equals(_names)) { 
RDebugUtils.currentLine=19791882;
 //BA.debugLineNum = 19791882;BA.debugLine="MsgboxAsync(\"Deck Already Exist\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Deck Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=19791883;
 //BA.debugLineNum = 19791883;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=19791888;
 //BA.debugLineNum = 19791888;BA.debugLine="If et1.Text = \"\" Then";
if ((mostCurrent._et1.getText()).equals("")) { 
RDebugUtils.currentLine=19791889;
 //BA.debugLineNum = 19791889;BA.debugLine="MsgboxAsync(\"Deck must have a name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Deck must have a name"),BA.ObjectToCharSequence("Error"),processBA);
 }else {
RDebugUtils.currentLine=19791892;
 //BA.debugLineNum = 19791892;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19791893;
 //BA.debugLineNum = 19791893;BA.debugLine="LVdecks.AddSingleLine(et1.Text)";
mostCurrent._lvdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._et1.getText()));
RDebugUtils.currentLine=19791896;
 //BA.debugLineNum = 19791896;BA.debugLine="deck.Put(et1.Text, newsubdeck)";
_deck.Put((Object)(mostCurrent._et1.getText()),(Object)(_newsubdeck.getObject()));
RDebugUtils.currentLine=19791897;
 //BA.debugLineNum = 19791897;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=19791899;
 //BA.debugLineNum = 19791899;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
 };
RDebugUtils.currentLine=19791902;
 //BA.debugLineNum = 19791902;BA.debugLine="End Sub";
return "";
}
public static String  _create_subdeck_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "create_subdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "create_subdeck_click", null));}
RDebugUtils.currentLine=20250624;
 //BA.debugLineNum = 20250624;BA.debugLine="Private Sub create_subdeck_Click";
RDebugUtils.currentLine=20250625;
 //BA.debugLineNum = 20250625;BA.debugLine="newsubdeckpanel.Visible = True";
mostCurrent._newsubdeckpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=20250626;
 //BA.debugLineNum = 20250626;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20250627;
 //BA.debugLineNum = 20250627;BA.debugLine="End Sub";
return "";
}
public static String  _createnewsubdeck_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createnewsubdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createnewsubdeck_click", null));}
anywheresoftware.b4a.objects.collections.Map _createdeck = null;
anywheresoftware.b4a.objects.collections.List _flashcard = null;
String _subdeck = "";
RDebugUtils.currentLine=20578304;
 //BA.debugLineNum = 20578304;BA.debugLine="Private Sub createnewsubdeck_Click";
RDebugUtils.currentLine=20578305;
 //BA.debugLineNum = 20578305;BA.debugLine="If newsubdecket.Text = \"\" Then";
if ((mostCurrent._newsubdecket.getText()).equals("")) { 
RDebugUtils.currentLine=20578306;
 //BA.debugLineNum = 20578306;BA.debugLine="MsgboxAsync(\"New Sub Deck must have a name\", \"Er";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("New Sub Deck must have a name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20578307;
 //BA.debugLineNum = 20578307;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=20578309;
 //BA.debugLineNum = 20578309;BA.debugLine="Dim createdeck As Map = deck.Get(item_longclick)";
_createdeck = new anywheresoftware.b4a.objects.collections.Map();
_createdeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_deck.Get((Object)(_item_longclick))));
RDebugUtils.currentLine=20578310;
 //BA.debugLineNum = 20578310;BA.debugLine="Dim flashcard As List";
_flashcard = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=20578311;
 //BA.debugLineNum = 20578311;BA.debugLine="flashcard.Initialize";
_flashcard.Initialize();
RDebugUtils.currentLine=20578312;
 //BA.debugLineNum = 20578312;BA.debugLine="For Each subdeck As String In createdeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group8 = _createdeck.Keys();
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_subdeck = BA.ObjectToString(group8.Get(index8));
RDebugUtils.currentLine=20578313;
 //BA.debugLineNum = 20578313;BA.debugLine="If newsubdecket.Text = subdeck Then";
if ((mostCurrent._newsubdecket.getText()).equals(_subdeck)) { 
RDebugUtils.currentLine=20578314;
 //BA.debugLineNum = 20578314;BA.debugLine="MsgboxAsync(\"Subdeck Already Exist\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Subdeck Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20578315;
 //BA.debugLineNum = 20578315;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=20578318;
 //BA.debugLineNum = 20578318;BA.debugLine="createdeck.Put(newsubdecket.Text, flashcard)";
_createdeck.Put((Object)(mostCurrent._newsubdecket.getText()),(Object)(_flashcard.getObject()));
RDebugUtils.currentLine=20578319;
 //BA.debugLineNum = 20578319;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=20578320;
 //BA.debugLineNum = 20578320;BA.debugLine="newsubdecket.Text = \"\"";
mostCurrent._newsubdecket.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=20578321;
 //BA.debugLineNum = 20578321;BA.debugLine="newsubdeckpanel.Visible = False";
mostCurrent._newsubdeckpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20578322;
 //BA.debugLineNum = 20578322;BA.debugLine="StartActivity(Subdeck_Module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._subdeck_module.getObject()));
RDebugUtils.currentLine=20578323;
 //BA.debugLineNum = 20578323;BA.debugLine="End Sub";
return "";
}
public static String  _delete_deck_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "delete_deck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "delete_deck_click", null));}
RDebugUtils.currentLine=20185088;
 //BA.debugLineNum = 20185088;BA.debugLine="Private Sub delete_deck_Click";
RDebugUtils.currentLine=20185089;
 //BA.debugLineNum = 20185089;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20185090;
 //BA.debugLineNum = 20185090;BA.debugLine="deleteconfirmation.Visible = True";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=20185091;
 //BA.debugLineNum = 20185091;BA.debugLine="End Sub";
return "";
}
public static String  _lvdecks_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvdecks_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvdecks_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=19922944;
 //BA.debugLineNum = 19922944;BA.debugLine="Private Sub LVdecks_ItemClick (Position As Int, Va";
RDebugUtils.currentLine=19922946;
 //BA.debugLineNum = 19922946;BA.debugLine="selecteddeck = Value";
_selecteddeck = BA.ObjectToString(_value);
RDebugUtils.currentLine=19922947;
 //BA.debugLineNum = 19922947;BA.debugLine="StartActivity(Subdeck_Module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._subdeck_module.getObject()));
RDebugUtils.currentLine=19922948;
 //BA.debugLineNum = 19922948;BA.debugLine="End Sub";
return "";
}
public static String  _lvdecks_itemlongclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvdecks_itemlongclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvdecks_itemlongclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=19988480;
 //BA.debugLineNum = 19988480;BA.debugLine="Private Sub LVdecks_ItemLongClick (Position As Int";
RDebugUtils.currentLine=19988482;
 //BA.debugLineNum = 19988482;BA.debugLine="decksettingpanel.Visible = True";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=19988483;
 //BA.debugLineNum = 19988483;BA.debugLine="item_longclick = Value";
_item_longclick = BA.ObjectToString(_value);
RDebugUtils.currentLine=19988484;
 //BA.debugLineNum = 19988484;BA.debugLine="selecteddeck = Value";
_selecteddeck = BA.ObjectToString(_value);
RDebugUtils.currentLine=19988485;
 //BA.debugLineNum = 19988485;BA.debugLine="End Sub";
return "";
}
public static String  _rename_deck_click() throws Exception{
RDebugUtils.currentModule="flashcardactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "rename_deck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "rename_deck_click", null));}
RDebugUtils.currentLine=20119552;
 //BA.debugLineNum = 20119552;BA.debugLine="Private Sub rename_deck_Click";
RDebugUtils.currentLine=20119553;
 //BA.debugLineNum = 20119553;BA.debugLine="decksettingpanel.Visible = False";
mostCurrent._decksettingpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20119554;
 //BA.debugLineNum = 20119554;BA.debugLine="renamepanel.Visible = True";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=20119555;
 //BA.debugLineNum = 20119555;BA.debugLine="End Sub";
return "";
}
}