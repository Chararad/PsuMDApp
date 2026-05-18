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

public class clockactivity extends Activity implements B4AActivity{
	public static clockactivity mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.clockactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (clockactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.clockactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.clockactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (clockactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (clockactivity) Resume **");
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
		return clockactivity.class;
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
            BA.LogInfo("** Activity (clockactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (clockactivity) Pause event (activity is not paused). **");
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
            clockactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (clockactivity) Resume **");
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
public static anywheresoftware.b4a.objects.Timer _timercount = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static int _secondsremain = 0;
public anywheresoftware.b4a.objects.LabelWrapper _pomotimerlbl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _playbtn = null;
public anywheresoftware.b4a.objects.LabelWrapper _sessionlbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _pomocounter = null;
public anywheresoftware.b4a.objects.ButtonWrapper _settingsbtn = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _settingspnl = null;
public anywheresoftware.b4a.objects.EditTextWrapper _pomotxt = null;
public anywheresoftware.b4a.objects.EditTextWrapper _shorttxt = null;
public anywheresoftware.b4a.objects.EditTextWrapper _longtxt = null;
public static int _timerstate = 0;
public static int _counter = 0;
public static int _pomodef = 0;
public static int _shortdef = 0;
public static int _longdef = 0;
public static int _centerleft = 0;
public static int _centertop = 0;
public static boolean _playing = false;
public static int _break = 0;
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
public b4a.example.card_module _card_module = null;
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
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 41;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 43;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"clocklayout\")";
mostCurrent._activity.LoadLayout("clocklayout",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 46;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark\")";
mostCurrent._activity.LoadLayout("clocklayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
 //BA.debugLineNum = 49;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 50;BA.debugLine="Activity.LoadLayout(\"clocklayout2\")";
mostCurrent._activity.LoadLayout("clocklayout2",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 52;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark2\")";
mostCurrent._activity.LoadLayout("clocklayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
 //BA.debugLineNum = 55;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 56;BA.debugLine="Activity.LoadLayout(\"clocklayout3\")";
mostCurrent._activity.LoadLayout("clocklayout3",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 58;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark3\")";
mostCurrent._activity.LoadLayout("clocklayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
 //BA.debugLineNum = 62;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 63;BA.debugLine="timerCount.Initialize(\"tmr\", 1000)";
_timercount.Initialize(processBA,"tmr",(long) (1000));
 };
 //BA.debugLineNum = 66;BA.debugLine="timerCount.Enabled = False";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 67;BA.debugLine="updateLbl";
_updatelbl();
 //BA.debugLineNum = 68;BA.debugLine="pomoCounter.Text = counter";
mostCurrent._pomocounter.setText(BA.ObjectToCharSequence(_counter));
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 75;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public static String  _closel_click() throws Exception{
 //BA.debugLineNum = 320;BA.debugLine="Private Sub closeL_Click";
 //BA.debugLineNum = 321;BA.debugLine="settingsPnl.Visible = False";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public static String  _exitbtn_click() throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Private Sub exitBtn_Click";
 //BA.debugLineNum = 80;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _formatbtn_click() throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Private Sub formatBtn_Click";
 //BA.debugLineNum = 85;BA.debugLine="MainActivity.format24h = Not(MainActivity.format2";
mostCurrent._mainactivity._format24h /*boolean*/  = anywheresoftware.b4a.keywords.Common.Not(mostCurrent._mainactivity._format24h /*boolean*/ );
 //BA.debugLineNum = 87;BA.debugLine="If MainActivity.format24h Then";
if (mostCurrent._mainactivity._format24h /*boolean*/ ) { 
 //BA.debugLineNum = 88;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 89;BA.debugLine="ToastMessageShow(\"Time Format Changed\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Time Format Changed"),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 91;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 92;BA.debugLine="ToastMessageShow(\"Time Format Changed\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Time Format Changed"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 94;BA.debugLine="CallSub(MainActivity, \"timerClock_Tick\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,(Object)(mostCurrent._mainactivity.getObject()),"timerClock_Tick");
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private pomotimerLbl As Label";
mostCurrent._pomotimerlbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private playBtn As Button";
mostCurrent._playbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private sessionLbl As Label";
mostCurrent._sessionlbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private pomoCounter As Label";
mostCurrent._pomocounter = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private settingsBtn As Button";
mostCurrent._settingsbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private settingsPnl As B4XView";
mostCurrent._settingspnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private pomoTxt As EditText";
mostCurrent._pomotxt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private shortTxt As EditText";
mostCurrent._shorttxt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private longTxt As EditText";
mostCurrent._longtxt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim timerState As Int = 0";
_timerstate = (int) (0);
 //BA.debugLineNum = 29;BA.debugLine="Dim counter As Int = 0";
_counter = (int) (0);
 //BA.debugLineNum = 30;BA.debugLine="Dim pomoDef As Int = 1500";
_pomodef = (int) (1500);
 //BA.debugLineNum = 31;BA.debugLine="Dim shortDef As Int = 300";
_shortdef = (int) (300);
 //BA.debugLineNum = 32;BA.debugLine="Dim longDef As Int = 900";
_longdef = (int) (900);
 //BA.debugLineNum = 33;BA.debugLine="Dim centerLeft As Int = 100dip";
_centerleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100));
 //BA.debugLineNum = 34;BA.debugLine="Dim centerTop As Int = 285dip";
_centertop = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (285));
 //BA.debugLineNum = 35;BA.debugLine="Dim playing As Boolean = False";
_playing = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 36;BA.debugLine="Dim break As Int = 0";
_break = (int) (0);
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _longbtn_click() throws Exception{
 //BA.debugLineNum = 307;BA.debugLine="Private Sub longBtn_Click";
 //BA.debugLineNum = 308;BA.debugLine="timerStop";
_timerstop();
 //BA.debugLineNum = 309;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
 //BA.debugLineNum = 310;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
 //BA.debugLineNum = 311;BA.debugLine="break = 1";
_break = (int) (1);
 //BA.debugLineNum = 312;BA.debugLine="updateLbl";
_updatelbl();
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _playbtn_click() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Private Sub playBtn_Click";
 //BA.debugLineNum = 99;BA.debugLine="If secondsRemain > 0 Then";
if (_secondsremain>0) { 
 //BA.debugLineNum = 100;BA.debugLine="timerCount.Enabled = True";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 102;BA.debugLine="If playing = True Then";
if (_playing==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 103;BA.debugLine="timerStop";
_timerstop();
 }else {
 //BA.debugLineNum = 105;BA.debugLine="timerCount.Enabled = True";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 106;BA.debugLine="playing = True";
_playing = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 107;BA.debugLine="playBtn.Text = \"Pause\"";
mostCurrent._playbtn.setText(BA.ObjectToCharSequence("Pause"));
 };
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _pomobtn_click() throws Exception{
 //BA.debugLineNum = 292;BA.debugLine="Private Sub pomoBtn_Click";
 //BA.debugLineNum = 293;BA.debugLine="timerStop";
_timerstop();
 //BA.debugLineNum = 294;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
 //BA.debugLineNum = 295;BA.debugLine="timerState = 0";
_timerstate = (int) (0);
 //BA.debugLineNum = 296;BA.debugLine="updateLbl";
_updatelbl();
 //BA.debugLineNum = 297;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private timerCount As Timer";
_timercount = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 11;BA.debugLine="Private secondsRemain As Int = 1500";
_secondsremain = (int) (1500);
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _savebtn_click() throws Exception{
 //BA.debugLineNum = 324;BA.debugLine="Private Sub saveBtn_Click";
 //BA.debugLineNum = 326;BA.debugLine="If IsNumber(pomoTxt.Text) Then pomoDef = pomoTxt.";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._pomotxt.getText())) { 
_pomodef = (int) ((double)(Double.parseDouble(mostCurrent._pomotxt.getText()))*60);};
 //BA.debugLineNum = 327;BA.debugLine="If IsNumber(shortTxt.Text) Then shortDef = shortT";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._shorttxt.getText())) { 
_shortdef = (int) ((double)(Double.parseDouble(mostCurrent._shorttxt.getText()))*60);};
 //BA.debugLineNum = 328;BA.debugLine="If IsNumber(longTxt.Text) Then longDef = longTxt.";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._longtxt.getText())) { 
_longdef = (int) ((double)(Double.parseDouble(mostCurrent._longtxt.getText()))*60);};
 //BA.debugLineNum = 330;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
 //BA.debugLineNum = 331;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
 }else {
 //BA.debugLineNum = 334;BA.debugLine="If break = 1 Then";
if (_break==1) { 
 //BA.debugLineNum = 335;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
 }else {
 //BA.debugLineNum = 337;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
 };
 };
 //BA.debugLineNum = 340;BA.debugLine="updateLbl";
_updatelbl();
 //BA.debugLineNum = 341;BA.debugLine="settingsPnl.Visible = False";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 342;BA.debugLine="End Sub";
return "";
}
public static String  _settingsbtn_click() throws Exception{
 //BA.debugLineNum = 315;BA.debugLine="Private Sub settingsBtn_Click";
 //BA.debugLineNum = 316;BA.debugLine="settingsWindow(250dip, 180dip)";
_settingswindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
 //BA.debugLineNum = 317;BA.debugLine="settingsPnl.Visible = True";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
return "";
}
public static String  _settingswindow(int _pw,int _ph) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lblp = null;
anywheresoftware.b4a.objects.LabelWrapper _lbls = null;
anywheresoftware.b4a.objects.LabelWrapper _lbll = null;
anywheresoftware.b4a.objects.LabelWrapper _closel = null;
anywheresoftware.b4a.objects.ButtonWrapper _savebtn = null;
 //BA.debugLineNum = 157;BA.debugLine="Private Sub settingsWindow(pW As Int, pH As Int)";
 //BA.debugLineNum = 158;BA.debugLine="settingsPnl = xui.CreatePanel(\"settingsPnl\")";
mostCurrent._settingspnl = _xui.CreatePanel(processBA,"settingsPnl");
 //BA.debugLineNum = 159;BA.debugLine="Activity.AddView(settingsPnl, centerLeft, centerT";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._settingspnl.getObject()),_centerleft,_centertop,_pw,_ph);
 //BA.debugLineNum = 160;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 162;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 163;BA.debugLine="settingsPnl.Color = xui.Color_RGB(255, 255, 25";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 164;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(22";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (227),(int) (193),(int) (159)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (98),(int) (43),(int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 }else {
 //BA.debugLineNum = 166;BA.debugLine="settingsPnl.Color = xui.Color_RGB(255, 255, 25";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 167;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(18";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (187),(int) (213),(int) (218)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (59),(int) (117),(int) (151)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 };
 break; }
case 1: {
 //BA.debugLineNum = 170;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 171;BA.debugLine="settingsPnl.Color = xui.Color_RGB(1, 130, 237)";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (1),(int) (130),(int) (237)));
 //BA.debugLineNum = 172;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(1,";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (1),(int) (130),(int) (237)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (102),(int) (213),(int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 }else {
 //BA.debugLineNum = 174;BA.debugLine="settingsPnl.Color = xui.Color_RGB(40, 106, 193";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (40),(int) (106),(int) (193)));
 //BA.debugLineNum = 175;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(69";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (69),(int) (132),(int) (204)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (40),(int) (106),(int) (193)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 };
 break; }
case 2: {
 //BA.debugLineNum = 178;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 179;BA.debugLine="settingsPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 180;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(23";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (231),(int) (213),(int) (179)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (73),(int) (43),(int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 }else {
 //BA.debugLineNum = 182;BA.debugLine="settingsPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 183;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(24";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (24),(int) (20),(int) (37)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 };
 break; }
}
;
 //BA.debugLineNum = 187;BA.debugLine="settingsPnl.Enabled = False";
mostCurrent._settingspnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="settingsPnl.Visible = False";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 192;BA.debugLine="pomoTxt.Initialize(\"pomoTxt\")";
mostCurrent._pomotxt.Initialize(mostCurrent.activityBA,"pomoTxt");
 //BA.debugLineNum = 193;BA.debugLine="pomoTxt.Hint = \"Pomo\"";
mostCurrent._pomotxt.setHint("Pomo");
 //BA.debugLineNum = 194;BA.debugLine="pomoTxt.InputType = pomoTxt.INPUT_TYPE_NUMBERS";
mostCurrent._pomotxt.setInputType(mostCurrent._pomotxt.INPUT_TYPE_NUMBERS);
 //BA.debugLineNum = 195;BA.debugLine="pomoTxt.Text = pomoDef / 60";
mostCurrent._pomotxt.setText(BA.ObjectToCharSequence(_pomodef/(double)60));
 //BA.debugLineNum = 196;BA.debugLine="pomoTxt.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._pomotxt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 197;BA.debugLine="settingsPnl.AddView(pomoTxt, 10dip, 40dip, 70dip,";
mostCurrent._settingspnl.AddView((android.view.View)(mostCurrent._pomotxt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 198;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 199;BA.debugLine="pomoTxt.HintColor = Colors.ARGB(50, 255, 255, 25";
mostCurrent._pomotxt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (255),(int) (255),(int) (255)));
 };
 //BA.debugLineNum = 202;BA.debugLine="shortTxt.Initialize(\"shortTxt\")";
mostCurrent._shorttxt.Initialize(mostCurrent.activityBA,"shortTxt");
 //BA.debugLineNum = 203;BA.debugLine="shortTxt.Hint = \"Short\"";
mostCurrent._shorttxt.setHint("Short");
 //BA.debugLineNum = 204;BA.debugLine="shortTxt.InputType = shortTxt.INPUT_TYPE_NUMBERS";
mostCurrent._shorttxt.setInputType(mostCurrent._shorttxt.INPUT_TYPE_NUMBERS);
 //BA.debugLineNum = 205;BA.debugLine="shortTxt.Text = shortDef / 60";
mostCurrent._shorttxt.setText(BA.ObjectToCharSequence(_shortdef/(double)60));
 //BA.debugLineNum = 206;BA.debugLine="shortTxt.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._shorttxt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 207;BA.debugLine="settingsPnl.AddView(shortTxt, 90dip, 40dip, 70dip";
mostCurrent._settingspnl.AddView((android.view.View)(mostCurrent._shorttxt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 208;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 209;BA.debugLine="shortTxt.HintColor = Colors.ARGB(50, 255, 255, 2";
mostCurrent._shorttxt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (255),(int) (255),(int) (255)));
 };
 //BA.debugLineNum = 212;BA.debugLine="longTxt.Initialize(\"longTxt\")";
mostCurrent._longtxt.Initialize(mostCurrent.activityBA,"longTxt");
 //BA.debugLineNum = 213;BA.debugLine="longTxt.Hint = \"Long\"";
mostCurrent._longtxt.setHint("Long");
 //BA.debugLineNum = 214;BA.debugLine="longTxt.InputType = longTxt.INPUT_TYPE_NUMBERS";
mostCurrent._longtxt.setInputType(mostCurrent._longtxt.INPUT_TYPE_NUMBERS);
 //BA.debugLineNum = 215;BA.debugLine="longTxt.Text = longDef / 60";
mostCurrent._longtxt.setText(BA.ObjectToCharSequence(_longdef/(double)60));
 //BA.debugLineNum = 216;BA.debugLine="longTxt.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._longtxt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 217;BA.debugLine="settingsPnl.AddView(longTxt, 170dip, 40dip, 70dip";
mostCurrent._settingspnl.AddView((android.view.View)(mostCurrent._longtxt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (170)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 218;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 219;BA.debugLine="longTxt.HintColor = Colors.ARGB(50, 255, 255, 25";
mostCurrent._longtxt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (255),(int) (255),(int) (255)));
 };
 //BA.debugLineNum = 222;BA.debugLine="Dim lblP, lblS, lblL As Label";
_lblp = new anywheresoftware.b4a.objects.LabelWrapper();
_lbls = new anywheresoftware.b4a.objects.LabelWrapper();
_lbll = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 224;BA.debugLine="lblP.Initialize(\"\")";
_lblp.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 225;BA.debugLine="lblP.Text = \"Pomo\"";
_lblp.setText(BA.ObjectToCharSequence("Pomo"));
 //BA.debugLineNum = 226;BA.debugLine="lblP.TextSize = 12";
_lblp.setTextSize((float) (12));
 //BA.debugLineNum = 227;BA.debugLine="lblP.Gravity = Gravity.CENTER_HORIZONTAL";
_lblp.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 228;BA.debugLine="settingsPnl.AddView(lblP, 10dip, 80dip, 70dip, 20";
mostCurrent._settingspnl.AddView((android.view.View)(_lblp.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 229;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 230;BA.debugLine="lblP.TextColor = Colors.White";
_lblp.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 233;BA.debugLine="lblS.Initialize(\"\")";
_lbls.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 234;BA.debugLine="lblS.Text = \"Short\"";
_lbls.setText(BA.ObjectToCharSequence("Short"));
 //BA.debugLineNum = 235;BA.debugLine="lblS.TextSize = 12";
_lbls.setTextSize((float) (12));
 //BA.debugLineNum = 236;BA.debugLine="lblS.Gravity = Gravity.CENTER_HORIZONTAL";
_lbls.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 237;BA.debugLine="settingsPnl.AddView(lblS, 90dip, 80dip, 70dip, 20";
mostCurrent._settingspnl.AddView((android.view.View)(_lbls.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 238;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 239;BA.debugLine="lblS.TextColor = Colors.White";
_lbls.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 242;BA.debugLine="lblL.Initialize(\"\")";
_lbll.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 243;BA.debugLine="lblL.Text = \"Long\"";
_lbll.setText(BA.ObjectToCharSequence("Long"));
 //BA.debugLineNum = 244;BA.debugLine="lblL.TextSize = 12";
_lbll.setTextSize((float) (12));
 //BA.debugLineNum = 245;BA.debugLine="lblL.Gravity = Gravity.CENTER_HORIZONTAL";
_lbll.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 246;BA.debugLine="settingsPnl.AddView(lblL, 170dip, 80dip, 70dip, 2";
mostCurrent._settingspnl.AddView((android.view.View)(_lbll.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (170)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 247;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 248;BA.debugLine="lblL.TextColor = Colors.White";
_lbll.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 251;BA.debugLine="Dim closeL As Label";
_closel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 252;BA.debugLine="closeL.Initialize(\"closeL\")";
_closel.Initialize(mostCurrent.activityBA,"closeL");
 //BA.debugLineNum = 253;BA.debugLine="settingsPnl.AddView(closeL, 10dip, 10dip, 20dip,";
mostCurrent._settingspnl.AddView((android.view.View)(_closel.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 254;BA.debugLine="closeL.Text = \"X\"";
_closel.setText(BA.ObjectToCharSequence("X"));
 //BA.debugLineNum = 255;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 256;BA.debugLine="closeL.TextColor = Colors.White";
_closel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 259;BA.debugLine="Dim saveBtn As Button";
_savebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 260;BA.debugLine="saveBtn.Initialize(\"saveBtn\")";
_savebtn.Initialize(mostCurrent.activityBA,"saveBtn");
 //BA.debugLineNum = 261;BA.debugLine="saveBtn.Text = \"Save Settings\"";
_savebtn.setText(BA.ObjectToCharSequence("Save Settings"));
 //BA.debugLineNum = 262;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 264;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 265;BA.debugLine="saveBtn.TextColor = Colors.RGB(255, 255, 255)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 266;BA.debugLine="saveBtn.Color = Colors.RGB(98, 43, 20)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (98),(int) (43),(int) (20)));
 }else {
 //BA.debugLineNum = 268;BA.debugLine="saveBtn.TextColor = Colors.RGB(255, 255, 255)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 269;BA.debugLine="saveBtn.Color = Colors.RGB(59, 117, 151)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (59),(int) (117),(int) (151)));
 };
 break; }
case 1: {
 //BA.debugLineNum = 272;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 273;BA.debugLine="saveBtn.TextColor = Colors.RGB(231, 213, 179)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (231),(int) (213),(int) (179)));
 //BA.debugLineNum = 274;BA.debugLine="saveBtn.Color = Colors.RGB(102, 213, 100)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (213),(int) (100)));
 }else {
 //BA.debugLineNum = 276;BA.debugLine="saveBtn.TextColor = Colors.Black";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 277;BA.debugLine="saveBtn.Color = Colors.RGB(102, 213, 100)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (213),(int) (100)));
 };
 break; }
case 2: {
 //BA.debugLineNum = 280;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 281;BA.debugLine="saveBtn.TextColor = Colors.RGB(231, 213, 179)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (231),(int) (213),(int) (179)));
 //BA.debugLineNum = 282;BA.debugLine="saveBtn.Color = Colors.RGB(115,62,57)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (115),(int) (62),(int) (57)));
 }else {
 //BA.debugLineNum = 284;BA.debugLine="saveBtn.TextColor = Colors.RGB(24, 20, 37)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (24),(int) (20),(int) (37)));
 //BA.debugLineNum = 285;BA.debugLine="saveBtn.Color = Colors.RGB(90,105,136)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (90),(int) (105),(int) (136)));
 };
 break; }
}
;
 //BA.debugLineNum = 288;BA.debugLine="settingsPnl.AddView(saveBtn, 10dip, 130dip, 230di";
mostCurrent._settingspnl.AddView((android.view.View)(_savebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (130)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (230)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return "";
}
public static String  _shortbtn_click() throws Exception{
 //BA.debugLineNum = 299;BA.debugLine="Private Sub shortBtn_Click";
 //BA.debugLineNum = 300;BA.debugLine="timerStop";
_timerstop();
 //BA.debugLineNum = 301;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
 //BA.debugLineNum = 302;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
 //BA.debugLineNum = 303;BA.debugLine="break = 0";
_break = (int) (0);
 //BA.debugLineNum = 304;BA.debugLine="updateLbl";
_updatelbl();
 //BA.debugLineNum = 305;BA.debugLine="End Sub";
return "";
}
public static String  _skipbtn_click() throws Exception{
 //BA.debugLineNum = 344;BA.debugLine="Private Sub skipBtn_Click";
 //BA.debugLineNum = 345;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
 //BA.debugLineNum = 346;BA.debugLine="counter = counter + 1";
_counter = (int) (_counter+1);
 //BA.debugLineNum = 348;BA.debugLine="If counter Mod 4 = 0 Then";
if (_counter%4==0) { 
 //BA.debugLineNum = 349;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
 //BA.debugLineNum = 350;BA.debugLine="break = 1";
_break = (int) (1);
 }else {
 //BA.debugLineNum = 352;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
 //BA.debugLineNum = 353;BA.debugLine="break = 0";
_break = (int) (0);
 };
 //BA.debugLineNum = 355;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
 }else if(_timerstate==1) { 
 //BA.debugLineNum = 358;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
 //BA.debugLineNum = 359;BA.debugLine="timerState = 0";
_timerstate = (int) (0);
 };
 //BA.debugLineNum = 362;BA.debugLine="updateLbl";
_updatelbl();
 //BA.debugLineNum = 363;BA.debugLine="timerStop";
_timerstop();
 //BA.debugLineNum = 364;BA.debugLine="End Sub";
return "";
}
public static String  _timerstop() throws Exception{
 //BA.debugLineNum = 366;BA.debugLine="Private Sub timerStop";
 //BA.debugLineNum = 367;BA.debugLine="timerCount.Enabled = False";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 368;BA.debugLine="playing = False";
_playing = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 369;BA.debugLine="playBtn.Text = \"Start\"";
mostCurrent._playbtn.setText(BA.ObjectToCharSequence("Start"));
 //BA.debugLineNum = 370;BA.debugLine="End Sub";
return "";
}
public static String  _tmr_tick() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub tmr_Tick";
 //BA.debugLineNum = 113;BA.debugLine="If secondsRemain > 0 Then";
if (_secondsremain>0) { 
 //BA.debugLineNum = 114;BA.debugLine="secondsRemain = secondsRemain - 1";
_secondsremain = (int) (_secondsremain-1);
 //BA.debugLineNum = 115;BA.debugLine="updateLbl";
_updatelbl();
 }else {
 //BA.debugLineNum = 117;BA.debugLine="timerStop";
_timerstop();
 //BA.debugLineNum = 119;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
 //BA.debugLineNum = 120;BA.debugLine="counter = counter + 1";
_counter = (int) (_counter+1);
 //BA.debugLineNum = 122;BA.debugLine="If counter Mod 4 = 0 Then";
if (_counter%4==0) { 
 //BA.debugLineNum = 123;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
 //BA.debugLineNum = 124;BA.debugLine="break = 1";
_break = (int) (1);
 }else {
 //BA.debugLineNum = 126;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
 //BA.debugLineNum = 127;BA.debugLine="break = 0";
_break = (int) (0);
 };
 //BA.debugLineNum = 129;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
 }else if(_timerstate==1) { 
 //BA.debugLineNum = 132;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
 //BA.debugLineNum = 133;BA.debugLine="timerState = 0";
_timerstate = (int) (0);
 };
 //BA.debugLineNum = 136;BA.debugLine="updateLbl";
_updatelbl();
 };
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _updatelbl() throws Exception{
int _mins = 0;
int _secs = 0;
 //BA.debugLineNum = 140;BA.debugLine="Sub updateLbl";
 //BA.debugLineNum = 141;BA.debugLine="Dim mins As Int = secondsRemain / 60";
_mins = (int) (_secondsremain/(double)60);
 //BA.debugLineNum = 142;BA.debugLine="Dim secs As Int = secondsRemain Mod 60";
_secs = (int) (_secondsremain%60);
 //BA.debugLineNum = 143;BA.debugLine="pomotimerLbl.Text = $\"$02.0{mins}:$02.0{secs}\"$";
mostCurrent._pomotimerlbl.setText(BA.ObjectToCharSequence((""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("02.0",(Object)(_mins))+":"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("02.0",(Object)(_secs))+"")));
 //BA.debugLineNum = 144;BA.debugLine="pomoCounter.Text = counter";
mostCurrent._pomocounter.setText(BA.ObjectToCharSequence(_counter));
 //BA.debugLineNum = 146;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
 //BA.debugLineNum = 147;BA.debugLine="sessionLbl.Text = \"Pomodoro\"";
mostCurrent._sessionlbl.setText(BA.ObjectToCharSequence("Pomodoro"));
 }else {
 //BA.debugLineNum = 149;BA.debugLine="If break = 1 Then";
if (_break==1) { 
 //BA.debugLineNum = 150;BA.debugLine="sessionLbl.Text = \"Long Break\"";
mostCurrent._sessionlbl.setText(BA.ObjectToCharSequence("Long Break"));
 }else {
 //BA.debugLineNum = 152;BA.debugLine="sessionLbl.Text = \"Short Break\"";
mostCurrent._sessionlbl.setText(BA.ObjectToCharSequence("Short Break"));
 };
 };
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
}
