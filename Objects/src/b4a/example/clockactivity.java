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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.clockactivity");
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



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.leaderboard _leaderboard = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.card_module _card_module = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.day_module _day_module = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=15400960;
 //BA.debugLineNum = 15400960;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=15400961;
 //BA.debugLineNum = 15400961;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=15400963;
 //BA.debugLineNum = 15400963;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15400964;
 //BA.debugLineNum = 15400964;BA.debugLine="Activity.LoadLayout(\"clocklayout\")";
mostCurrent._activity.LoadLayout("clocklayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=15400966;
 //BA.debugLineNum = 15400966;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark\")";
mostCurrent._activity.LoadLayout("clocklayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=15400969;
 //BA.debugLineNum = 15400969;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15400970;
 //BA.debugLineNum = 15400970;BA.debugLine="Activity.LoadLayout(\"clocklayout2\")";
mostCurrent._activity.LoadLayout("clocklayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=15400972;
 //BA.debugLineNum = 15400972;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark2\")";
mostCurrent._activity.LoadLayout("clocklayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=15400975;
 //BA.debugLineNum = 15400975;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15400976;
 //BA.debugLineNum = 15400976;BA.debugLine="Activity.LoadLayout(\"clocklayout3\")";
mostCurrent._activity.LoadLayout("clocklayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=15400978;
 //BA.debugLineNum = 15400978;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark3\")";
mostCurrent._activity.LoadLayout("clocklayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=15400982;
 //BA.debugLineNum = 15400982;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
RDebugUtils.currentLine=15400983;
 //BA.debugLineNum = 15400983;BA.debugLine="timerCount.Initialize(\"tmr\", 1000)";
_timercount.Initialize(processBA,"tmr",(long) (1000));
 };
RDebugUtils.currentLine=15400986;
 //BA.debugLineNum = 15400986;BA.debugLine="timerCount.Enabled = False";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=15400987;
 //BA.debugLineNum = 15400987;BA.debugLine="updateLbl";
_updatelbl();
RDebugUtils.currentLine=15400988;
 //BA.debugLineNum = 15400988;BA.debugLine="pomoCounter.Text = counter";
mostCurrent._pomocounter.setText(BA.ObjectToCharSequence(_counter));
RDebugUtils.currentLine=15400989;
 //BA.debugLineNum = 15400989;BA.debugLine="End Sub";
return "";
}
public static String  _updatelbl() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatelbl", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatelbl", null));}
int _mins = 0;
int _secs = 0;
RDebugUtils.currentLine=15859712;
 //BA.debugLineNum = 15859712;BA.debugLine="Sub updateLbl";
RDebugUtils.currentLine=15859713;
 //BA.debugLineNum = 15859713;BA.debugLine="Dim mins As Int = secondsRemain / 60";
_mins = (int) (_secondsremain/(double)60);
RDebugUtils.currentLine=15859714;
 //BA.debugLineNum = 15859714;BA.debugLine="Dim secs As Int = secondsRemain Mod 60";
_secs = (int) (_secondsremain%60);
RDebugUtils.currentLine=15859715;
 //BA.debugLineNum = 15859715;BA.debugLine="pomotimerLbl.Text = $\"$02.0{mins}:$02.0{secs}\"$";
mostCurrent._pomotimerlbl.setText(BA.ObjectToCharSequence((""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("02.0",(Object)(_mins))+":"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("02.0",(Object)(_secs))+"")));
RDebugUtils.currentLine=15859716;
 //BA.debugLineNum = 15859716;BA.debugLine="pomoCounter.Text = counter";
mostCurrent._pomocounter.setText(BA.ObjectToCharSequence(_counter));
RDebugUtils.currentLine=15859718;
 //BA.debugLineNum = 15859718;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
RDebugUtils.currentLine=15859719;
 //BA.debugLineNum = 15859719;BA.debugLine="sessionLbl.Text = \"Pomodoro\"";
mostCurrent._sessionlbl.setText(BA.ObjectToCharSequence("Pomodoro"));
 }else {
RDebugUtils.currentLine=15859721;
 //BA.debugLineNum = 15859721;BA.debugLine="If break = 1 Then";
if (_break==1) { 
RDebugUtils.currentLine=15859722;
 //BA.debugLineNum = 15859722;BA.debugLine="sessionLbl.Text = \"Long Break\"";
mostCurrent._sessionlbl.setText(BA.ObjectToCharSequence("Long Break"));
 }else {
RDebugUtils.currentLine=15859724;
 //BA.debugLineNum = 15859724;BA.debugLine="sessionLbl.Text = \"Short Break\"";
mostCurrent._sessionlbl.setText(BA.ObjectToCharSequence("Short Break"));
 };
 };
RDebugUtils.currentLine=15859727;
 //BA.debugLineNum = 15859727;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="clockactivity";
RDebugUtils.currentLine=15532032;
 //BA.debugLineNum = 15532032;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=15532034;
 //BA.debugLineNum = 15532034;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=15466496;
 //BA.debugLineNum = 15466496;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=15466498;
 //BA.debugLineNum = 15466498;BA.debugLine="End Sub";
return "";
}
public static String  _closel_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "closel_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "closel_click", null));}
RDebugUtils.currentLine=16252928;
 //BA.debugLineNum = 16252928;BA.debugLine="Private Sub closeL_Click";
RDebugUtils.currentLine=16252929;
 //BA.debugLineNum = 16252929;BA.debugLine="settingsPnl.Visible = False";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16252930;
 //BA.debugLineNum = 16252930;BA.debugLine="End Sub";
return "";
}
public static String  _exitbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "exitbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "exitbtn_click", null));}
RDebugUtils.currentLine=15597568;
 //BA.debugLineNum = 15597568;BA.debugLine="Private Sub exitBtn_Click";
RDebugUtils.currentLine=15597569;
 //BA.debugLineNum = 15597569;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=15597570;
 //BA.debugLineNum = 15597570;BA.debugLine="End Sub";
return "";
}
public static String  _formatbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "formatbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "formatbtn_click", null));}
RDebugUtils.currentLine=15663104;
 //BA.debugLineNum = 15663104;BA.debugLine="Private Sub formatBtn_Click";
RDebugUtils.currentLine=15663106;
 //BA.debugLineNum = 15663106;BA.debugLine="MainActivity.format24h = Not(MainActivity.format2";
mostCurrent._mainactivity._format24h /*boolean*/  = anywheresoftware.b4a.keywords.Common.Not(mostCurrent._mainactivity._format24h /*boolean*/ );
RDebugUtils.currentLine=15663108;
 //BA.debugLineNum = 15663108;BA.debugLine="If MainActivity.format24h Then";
if (mostCurrent._mainactivity._format24h /*boolean*/ ) { 
RDebugUtils.currentLine=15663109;
 //BA.debugLineNum = 15663109;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
RDebugUtils.currentLine=15663110;
 //BA.debugLineNum = 15663110;BA.debugLine="ToastMessageShow(\"Time Format Changed\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Time Format Changed"),anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=15663112;
 //BA.debugLineNum = 15663112;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
RDebugUtils.currentLine=15663113;
 //BA.debugLineNum = 15663113;BA.debugLine="ToastMessageShow(\"Time Format Changed\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Time Format Changed"),anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=15663115;
 //BA.debugLineNum = 15663115;BA.debugLine="CallSub(MainActivity, \"timerClock_Tick\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._mainactivity.getObject()),"timerClock_Tick");
RDebugUtils.currentLine=15663117;
 //BA.debugLineNum = 15663117;BA.debugLine="End Sub";
return "";
}
public static String  _longbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "longbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "longbtn_click", null));}
RDebugUtils.currentLine=16121856;
 //BA.debugLineNum = 16121856;BA.debugLine="Private Sub longBtn_Click";
RDebugUtils.currentLine=16121857;
 //BA.debugLineNum = 16121857;BA.debugLine="timerStop";
_timerstop();
RDebugUtils.currentLine=16121858;
 //BA.debugLineNum = 16121858;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
RDebugUtils.currentLine=16121859;
 //BA.debugLineNum = 16121859;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
RDebugUtils.currentLine=16121860;
 //BA.debugLineNum = 16121860;BA.debugLine="break = 1";
_break = (int) (1);
RDebugUtils.currentLine=16121861;
 //BA.debugLineNum = 16121861;BA.debugLine="updateLbl";
_updatelbl();
RDebugUtils.currentLine=16121862;
 //BA.debugLineNum = 16121862;BA.debugLine="End Sub";
return "";
}
public static String  _timerstop() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerstop", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerstop", null));}
RDebugUtils.currentLine=16449536;
 //BA.debugLineNum = 16449536;BA.debugLine="Private Sub timerStop";
RDebugUtils.currentLine=16449537;
 //BA.debugLineNum = 16449537;BA.debugLine="timerCount.Enabled = False";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16449538;
 //BA.debugLineNum = 16449538;BA.debugLine="playing = False";
_playing = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=16449539;
 //BA.debugLineNum = 16449539;BA.debugLine="playBtn.Text = \"Start\"";
mostCurrent._playbtn.setText(BA.ObjectToCharSequence("Start"));
RDebugUtils.currentLine=16449540;
 //BA.debugLineNum = 16449540;BA.debugLine="End Sub";
return "";
}
public static String  _playbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "playbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "playbtn_click", null));}
RDebugUtils.currentLine=15728640;
 //BA.debugLineNum = 15728640;BA.debugLine="Private Sub playBtn_Click";
RDebugUtils.currentLine=15728641;
 //BA.debugLineNum = 15728641;BA.debugLine="If secondsRemain > 0 Then";
if (_secondsremain>0) { 
RDebugUtils.currentLine=15728642;
 //BA.debugLineNum = 15728642;BA.debugLine="timerCount.Enabled = True";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=15728644;
 //BA.debugLineNum = 15728644;BA.debugLine="If playing = True Then";
if (_playing==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15728645;
 //BA.debugLineNum = 15728645;BA.debugLine="timerStop";
_timerstop();
 }else {
RDebugUtils.currentLine=15728647;
 //BA.debugLineNum = 15728647;BA.debugLine="timerCount.Enabled = True";
_timercount.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=15728648;
 //BA.debugLineNum = 15728648;BA.debugLine="playing = True";
_playing = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=15728649;
 //BA.debugLineNum = 15728649;BA.debugLine="playBtn.Text = \"Pause\"";
mostCurrent._playbtn.setText(BA.ObjectToCharSequence("Pause"));
 };
RDebugUtils.currentLine=15728652;
 //BA.debugLineNum = 15728652;BA.debugLine="End Sub";
return "";
}
public static String  _pomobtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pomobtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pomobtn_click", null));}
RDebugUtils.currentLine=15990784;
 //BA.debugLineNum = 15990784;BA.debugLine="Private Sub pomoBtn_Click";
RDebugUtils.currentLine=15990785;
 //BA.debugLineNum = 15990785;BA.debugLine="timerStop";
_timerstop();
RDebugUtils.currentLine=15990786;
 //BA.debugLineNum = 15990786;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
RDebugUtils.currentLine=15990787;
 //BA.debugLineNum = 15990787;BA.debugLine="timerState = 0";
_timerstate = (int) (0);
RDebugUtils.currentLine=15990788;
 //BA.debugLineNum = 15990788;BA.debugLine="updateLbl";
_updatelbl();
RDebugUtils.currentLine=15990789;
 //BA.debugLineNum = 15990789;BA.debugLine="End Sub";
return "";
}
public static String  _savebtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savebtn_click", null));}
RDebugUtils.currentLine=16318464;
 //BA.debugLineNum = 16318464;BA.debugLine="Private Sub saveBtn_Click";
RDebugUtils.currentLine=16318466;
 //BA.debugLineNum = 16318466;BA.debugLine="If IsNumber(pomoTxt.Text) Then pomoDef = pomoTxt.";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._pomotxt.getText())) { 
_pomodef = (int) ((double)(Double.parseDouble(mostCurrent._pomotxt.getText()))*60);};
RDebugUtils.currentLine=16318467;
 //BA.debugLineNum = 16318467;BA.debugLine="If IsNumber(shortTxt.Text) Then shortDef = shortT";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._shorttxt.getText())) { 
_shortdef = (int) ((double)(Double.parseDouble(mostCurrent._shorttxt.getText()))*60);};
RDebugUtils.currentLine=16318468;
 //BA.debugLineNum = 16318468;BA.debugLine="If IsNumber(longTxt.Text) Then longDef = longTxt.";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._longtxt.getText())) { 
_longdef = (int) ((double)(Double.parseDouble(mostCurrent._longtxt.getText()))*60);};
RDebugUtils.currentLine=16318470;
 //BA.debugLineNum = 16318470;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
RDebugUtils.currentLine=16318471;
 //BA.debugLineNum = 16318471;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
 }else {
RDebugUtils.currentLine=16318474;
 //BA.debugLineNum = 16318474;BA.debugLine="If break = 1 Then";
if (_break==1) { 
RDebugUtils.currentLine=16318475;
 //BA.debugLineNum = 16318475;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
 }else {
RDebugUtils.currentLine=16318477;
 //BA.debugLineNum = 16318477;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
 };
 };
RDebugUtils.currentLine=16318480;
 //BA.debugLineNum = 16318480;BA.debugLine="updateLbl";
_updatelbl();
RDebugUtils.currentLine=16318481;
 //BA.debugLineNum = 16318481;BA.debugLine="settingsPnl.Visible = False";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16318482;
 //BA.debugLineNum = 16318482;BA.debugLine="End Sub";
return "";
}
public static String  _settingsbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "settingsbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "settingsbtn_click", null));}
RDebugUtils.currentLine=16187392;
 //BA.debugLineNum = 16187392;BA.debugLine="Private Sub settingsBtn_Click";
RDebugUtils.currentLine=16187393;
 //BA.debugLineNum = 16187393;BA.debugLine="settingsWindow(250dip, 180dip)";
_settingswindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=16187394;
 //BA.debugLineNum = 16187394;BA.debugLine="settingsPnl.Visible = True";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=16187395;
 //BA.debugLineNum = 16187395;BA.debugLine="End Sub";
return "";
}
public static String  _settingswindow(int _pw,int _ph) throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "settingswindow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "settingswindow", new Object[] {_pw,_ph}));}
anywheresoftware.b4a.objects.LabelWrapper _lblp = null;
anywheresoftware.b4a.objects.LabelWrapper _lbls = null;
anywheresoftware.b4a.objects.LabelWrapper _lbll = null;
anywheresoftware.b4a.objects.LabelWrapper _closel = null;
anywheresoftware.b4a.objects.ButtonWrapper _savebtn = null;
RDebugUtils.currentLine=15925248;
 //BA.debugLineNum = 15925248;BA.debugLine="Private Sub settingsWindow(pW As Int, pH As Int)";
RDebugUtils.currentLine=15925249;
 //BA.debugLineNum = 15925249;BA.debugLine="settingsPnl = xui.CreatePanel(\"settingsPnl\")";
mostCurrent._settingspnl = _xui.CreatePanel(processBA,"settingsPnl");
RDebugUtils.currentLine=15925250;
 //BA.debugLineNum = 15925250;BA.debugLine="Activity.AddView(settingsPnl, centerLeft, centerT";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._settingspnl.getObject()),_centerleft,_centertop,_pw,_ph);
RDebugUtils.currentLine=15925251;
 //BA.debugLineNum = 15925251;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=15925253;
 //BA.debugLineNum = 15925253;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15925254;
 //BA.debugLineNum = 15925254;BA.debugLine="settingsPnl.Color = xui.Color_RGB(255, 255, 25";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=15925255;
 //BA.debugLineNum = 15925255;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(22";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (227),(int) (193),(int) (159)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (98),(int) (43),(int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 }else {
RDebugUtils.currentLine=15925257;
 //BA.debugLineNum = 15925257;BA.debugLine="settingsPnl.Color = xui.Color_RGB(255, 255, 25";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=15925258;
 //BA.debugLineNum = 15925258;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(18";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (187),(int) (213),(int) (218)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (59),(int) (117),(int) (151)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 };
 break; }
case 1: {
RDebugUtils.currentLine=15925261;
 //BA.debugLineNum = 15925261;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15925262;
 //BA.debugLineNum = 15925262;BA.debugLine="settingsPnl.Color = xui.Color_RGB(1, 130, 237)";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (1),(int) (130),(int) (237)));
RDebugUtils.currentLine=15925263;
 //BA.debugLineNum = 15925263;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(1,";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (1),(int) (130),(int) (237)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (102),(int) (213),(int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 }else {
RDebugUtils.currentLine=15925265;
 //BA.debugLineNum = 15925265;BA.debugLine="settingsPnl.Color = xui.Color_RGB(40, 106, 193";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (40),(int) (106),(int) (193)));
RDebugUtils.currentLine=15925266;
 //BA.debugLineNum = 15925266;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(69";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (69),(int) (132),(int) (204)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (40),(int) (106),(int) (193)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 };
 break; }
case 2: {
RDebugUtils.currentLine=15925269;
 //BA.debugLineNum = 15925269;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15925270;
 //BA.debugLineNum = 15925270;BA.debugLine="settingsPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=15925271;
 //BA.debugLineNum = 15925271;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(23";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (231),(int) (213),(int) (179)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (73),(int) (43),(int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 }else {
RDebugUtils.currentLine=15925273;
 //BA.debugLineNum = 15925273;BA.debugLine="settingsPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._settingspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=15925274;
 //BA.debugLineNum = 15925274;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_RGB(24";
mostCurrent._settingspnl.SetColorAndBorder(_xui.Color_RGB((int) (24),(int) (20),(int) (37)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 };
 break; }
}
;
RDebugUtils.currentLine=15925278;
 //BA.debugLineNum = 15925278;BA.debugLine="settingsPnl.Enabled = False";
mostCurrent._settingspnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=15925279;
 //BA.debugLineNum = 15925279;BA.debugLine="settingsPnl.Visible = False";
mostCurrent._settingspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=15925283;
 //BA.debugLineNum = 15925283;BA.debugLine="pomoTxt.Initialize(\"pomoTxt\")";
mostCurrent._pomotxt.Initialize(mostCurrent.activityBA,"pomoTxt");
RDebugUtils.currentLine=15925284;
 //BA.debugLineNum = 15925284;BA.debugLine="pomoTxt.Hint = \"Pomo\"";
mostCurrent._pomotxt.setHint("Pomo");
RDebugUtils.currentLine=15925285;
 //BA.debugLineNum = 15925285;BA.debugLine="pomoTxt.InputType = pomoTxt.INPUT_TYPE_NUMBERS";
mostCurrent._pomotxt.setInputType(mostCurrent._pomotxt.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=15925286;
 //BA.debugLineNum = 15925286;BA.debugLine="pomoTxt.Text = pomoDef / 60";
mostCurrent._pomotxt.setText(BA.ObjectToCharSequence(_pomodef/(double)60));
RDebugUtils.currentLine=15925287;
 //BA.debugLineNum = 15925287;BA.debugLine="pomoTxt.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._pomotxt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=15925288;
 //BA.debugLineNum = 15925288;BA.debugLine="settingsPnl.AddView(pomoTxt, 10dip, 40dip, 70dip,";
mostCurrent._settingspnl.AddView((android.view.View)(mostCurrent._pomotxt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=15925289;
 //BA.debugLineNum = 15925289;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925290;
 //BA.debugLineNum = 15925290;BA.debugLine="pomoTxt.HintColor = Colors.ARGB(50, 255, 255, 25";
mostCurrent._pomotxt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (255),(int) (255),(int) (255)));
 };
RDebugUtils.currentLine=15925293;
 //BA.debugLineNum = 15925293;BA.debugLine="shortTxt.Initialize(\"shortTxt\")";
mostCurrent._shorttxt.Initialize(mostCurrent.activityBA,"shortTxt");
RDebugUtils.currentLine=15925294;
 //BA.debugLineNum = 15925294;BA.debugLine="shortTxt.Hint = \"Short\"";
mostCurrent._shorttxt.setHint("Short");
RDebugUtils.currentLine=15925295;
 //BA.debugLineNum = 15925295;BA.debugLine="shortTxt.InputType = shortTxt.INPUT_TYPE_NUMBERS";
mostCurrent._shorttxt.setInputType(mostCurrent._shorttxt.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=15925296;
 //BA.debugLineNum = 15925296;BA.debugLine="shortTxt.Text = shortDef / 60";
mostCurrent._shorttxt.setText(BA.ObjectToCharSequence(_shortdef/(double)60));
RDebugUtils.currentLine=15925297;
 //BA.debugLineNum = 15925297;BA.debugLine="shortTxt.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._shorttxt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=15925298;
 //BA.debugLineNum = 15925298;BA.debugLine="settingsPnl.AddView(shortTxt, 90dip, 40dip, 70dip";
mostCurrent._settingspnl.AddView((android.view.View)(mostCurrent._shorttxt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=15925299;
 //BA.debugLineNum = 15925299;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925300;
 //BA.debugLineNum = 15925300;BA.debugLine="shortTxt.HintColor = Colors.ARGB(50, 255, 255, 2";
mostCurrent._shorttxt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (255),(int) (255),(int) (255)));
 };
RDebugUtils.currentLine=15925303;
 //BA.debugLineNum = 15925303;BA.debugLine="longTxt.Initialize(\"longTxt\")";
mostCurrent._longtxt.Initialize(mostCurrent.activityBA,"longTxt");
RDebugUtils.currentLine=15925304;
 //BA.debugLineNum = 15925304;BA.debugLine="longTxt.Hint = \"Long\"";
mostCurrent._longtxt.setHint("Long");
RDebugUtils.currentLine=15925305;
 //BA.debugLineNum = 15925305;BA.debugLine="longTxt.InputType = longTxt.INPUT_TYPE_NUMBERS";
mostCurrent._longtxt.setInputType(mostCurrent._longtxt.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=15925306;
 //BA.debugLineNum = 15925306;BA.debugLine="longTxt.Text = longDef / 60";
mostCurrent._longtxt.setText(BA.ObjectToCharSequence(_longdef/(double)60));
RDebugUtils.currentLine=15925307;
 //BA.debugLineNum = 15925307;BA.debugLine="longTxt.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._longtxt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=15925308;
 //BA.debugLineNum = 15925308;BA.debugLine="settingsPnl.AddView(longTxt, 170dip, 40dip, 70dip";
mostCurrent._settingspnl.AddView((android.view.View)(mostCurrent._longtxt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (170)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=15925309;
 //BA.debugLineNum = 15925309;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925310;
 //BA.debugLineNum = 15925310;BA.debugLine="longTxt.HintColor = Colors.ARGB(50, 255, 255, 25";
mostCurrent._longtxt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (255),(int) (255),(int) (255)));
 };
RDebugUtils.currentLine=15925313;
 //BA.debugLineNum = 15925313;BA.debugLine="Dim lblP, lblS, lblL As Label";
_lblp = new anywheresoftware.b4a.objects.LabelWrapper();
_lbls = new anywheresoftware.b4a.objects.LabelWrapper();
_lbll = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=15925315;
 //BA.debugLineNum = 15925315;BA.debugLine="lblP.Initialize(\"\")";
_lblp.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=15925316;
 //BA.debugLineNum = 15925316;BA.debugLine="lblP.Text = \"Pomo\"";
_lblp.setText(BA.ObjectToCharSequence("Pomo"));
RDebugUtils.currentLine=15925317;
 //BA.debugLineNum = 15925317;BA.debugLine="lblP.TextSize = 12";
_lblp.setTextSize((float) (12));
RDebugUtils.currentLine=15925318;
 //BA.debugLineNum = 15925318;BA.debugLine="lblP.Gravity = Gravity.CENTER_HORIZONTAL";
_lblp.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=15925319;
 //BA.debugLineNum = 15925319;BA.debugLine="settingsPnl.AddView(lblP, 10dip, 80dip, 70dip, 20";
mostCurrent._settingspnl.AddView((android.view.View)(_lblp.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
RDebugUtils.currentLine=15925320;
 //BA.debugLineNum = 15925320;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925321;
 //BA.debugLineNum = 15925321;BA.debugLine="lblP.TextColor = Colors.White";
_lblp.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=15925324;
 //BA.debugLineNum = 15925324;BA.debugLine="lblS.Initialize(\"\")";
_lbls.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=15925325;
 //BA.debugLineNum = 15925325;BA.debugLine="lblS.Text = \"Short\"";
_lbls.setText(BA.ObjectToCharSequence("Short"));
RDebugUtils.currentLine=15925326;
 //BA.debugLineNum = 15925326;BA.debugLine="lblS.TextSize = 12";
_lbls.setTextSize((float) (12));
RDebugUtils.currentLine=15925327;
 //BA.debugLineNum = 15925327;BA.debugLine="lblS.Gravity = Gravity.CENTER_HORIZONTAL";
_lbls.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=15925328;
 //BA.debugLineNum = 15925328;BA.debugLine="settingsPnl.AddView(lblS, 90dip, 80dip, 70dip, 20";
mostCurrent._settingspnl.AddView((android.view.View)(_lbls.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
RDebugUtils.currentLine=15925329;
 //BA.debugLineNum = 15925329;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925330;
 //BA.debugLineNum = 15925330;BA.debugLine="lblS.TextColor = Colors.White";
_lbls.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=15925333;
 //BA.debugLineNum = 15925333;BA.debugLine="lblL.Initialize(\"\")";
_lbll.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=15925334;
 //BA.debugLineNum = 15925334;BA.debugLine="lblL.Text = \"Long\"";
_lbll.setText(BA.ObjectToCharSequence("Long"));
RDebugUtils.currentLine=15925335;
 //BA.debugLineNum = 15925335;BA.debugLine="lblL.TextSize = 12";
_lbll.setTextSize((float) (12));
RDebugUtils.currentLine=15925336;
 //BA.debugLineNum = 15925336;BA.debugLine="lblL.Gravity = Gravity.CENTER_HORIZONTAL";
_lbll.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=15925337;
 //BA.debugLineNum = 15925337;BA.debugLine="settingsPnl.AddView(lblL, 170dip, 80dip, 70dip, 2";
mostCurrent._settingspnl.AddView((android.view.View)(_lbll.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (170)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
RDebugUtils.currentLine=15925338;
 //BA.debugLineNum = 15925338;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925339;
 //BA.debugLineNum = 15925339;BA.debugLine="lblL.TextColor = Colors.White";
_lbll.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=15925342;
 //BA.debugLineNum = 15925342;BA.debugLine="Dim closeL As Label";
_closel = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=15925343;
 //BA.debugLineNum = 15925343;BA.debugLine="closeL.Initialize(\"closeL\")";
_closel.Initialize(mostCurrent.activityBA,"closeL");
RDebugUtils.currentLine=15925344;
 //BA.debugLineNum = 15925344;BA.debugLine="settingsPnl.AddView(closeL, 10dip, 10dip, 20dip,";
mostCurrent._settingspnl.AddView((android.view.View)(_closel.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
RDebugUtils.currentLine=15925345;
 //BA.debugLineNum = 15925345;BA.debugLine="closeL.Text = \"X\"";
_closel.setText(BA.ObjectToCharSequence("X"));
RDebugUtils.currentLine=15925346;
 //BA.debugLineNum = 15925346;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode =";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=15925347;
 //BA.debugLineNum = 15925347;BA.debugLine="closeL.TextColor = Colors.White";
_closel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=15925350;
 //BA.debugLineNum = 15925350;BA.debugLine="Dim saveBtn As Button";
_savebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=15925351;
 //BA.debugLineNum = 15925351;BA.debugLine="saveBtn.Initialize(\"saveBtn\")";
_savebtn.Initialize(mostCurrent.activityBA,"saveBtn");
RDebugUtils.currentLine=15925352;
 //BA.debugLineNum = 15925352;BA.debugLine="saveBtn.Text = \"Save Settings\"";
_savebtn.setText(BA.ObjectToCharSequence("Save Settings"));
RDebugUtils.currentLine=15925353;
 //BA.debugLineNum = 15925353;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=15925355;
 //BA.debugLineNum = 15925355;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15925356;
 //BA.debugLineNum = 15925356;BA.debugLine="saveBtn.TextColor = Colors.RGB(255, 255, 255)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=15925357;
 //BA.debugLineNum = 15925357;BA.debugLine="saveBtn.Color = Colors.RGB(98, 43, 20)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (98),(int) (43),(int) (20)));
 }else {
RDebugUtils.currentLine=15925359;
 //BA.debugLineNum = 15925359;BA.debugLine="saveBtn.TextColor = Colors.RGB(255, 255, 255)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=15925360;
 //BA.debugLineNum = 15925360;BA.debugLine="saveBtn.Color = Colors.RGB(59, 117, 151)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (59),(int) (117),(int) (151)));
 };
 break; }
case 1: {
RDebugUtils.currentLine=15925363;
 //BA.debugLineNum = 15925363;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15925364;
 //BA.debugLineNum = 15925364;BA.debugLine="saveBtn.TextColor = Colors.RGB(231, 213, 179)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (231),(int) (213),(int) (179)));
RDebugUtils.currentLine=15925365;
 //BA.debugLineNum = 15925365;BA.debugLine="saveBtn.Color = Colors.RGB(102, 213, 100)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (213),(int) (100)));
 }else {
RDebugUtils.currentLine=15925367;
 //BA.debugLineNum = 15925367;BA.debugLine="saveBtn.TextColor = Colors.Black";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=15925368;
 //BA.debugLineNum = 15925368;BA.debugLine="saveBtn.Color = Colors.RGB(102, 213, 100)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (213),(int) (100)));
 };
 break; }
case 2: {
RDebugUtils.currentLine=15925371;
 //BA.debugLineNum = 15925371;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15925372;
 //BA.debugLineNum = 15925372;BA.debugLine="saveBtn.TextColor = Colors.RGB(231, 213, 179)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (231),(int) (213),(int) (179)));
RDebugUtils.currentLine=15925373;
 //BA.debugLineNum = 15925373;BA.debugLine="saveBtn.Color = Colors.RGB(115,62,57)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (115),(int) (62),(int) (57)));
 }else {
RDebugUtils.currentLine=15925375;
 //BA.debugLineNum = 15925375;BA.debugLine="saveBtn.TextColor = Colors.RGB(24, 20, 37)";
_savebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (24),(int) (20),(int) (37)));
RDebugUtils.currentLine=15925376;
 //BA.debugLineNum = 15925376;BA.debugLine="saveBtn.Color = Colors.RGB(90,105,136)";
_savebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (90),(int) (105),(int) (136)));
 };
 break; }
}
;
RDebugUtils.currentLine=15925379;
 //BA.debugLineNum = 15925379;BA.debugLine="settingsPnl.AddView(saveBtn, 10dip, 130dip, 230di";
mostCurrent._settingspnl.AddView((android.view.View)(_savebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (130)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (230)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=15925381;
 //BA.debugLineNum = 15925381;BA.debugLine="End Sub";
return "";
}
public static String  _shortbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "shortbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "shortbtn_click", null));}
RDebugUtils.currentLine=16056320;
 //BA.debugLineNum = 16056320;BA.debugLine="Private Sub shortBtn_Click";
RDebugUtils.currentLine=16056321;
 //BA.debugLineNum = 16056321;BA.debugLine="timerStop";
_timerstop();
RDebugUtils.currentLine=16056322;
 //BA.debugLineNum = 16056322;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
RDebugUtils.currentLine=16056323;
 //BA.debugLineNum = 16056323;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
RDebugUtils.currentLine=16056324;
 //BA.debugLineNum = 16056324;BA.debugLine="break = 0";
_break = (int) (0);
RDebugUtils.currentLine=16056325;
 //BA.debugLineNum = 16056325;BA.debugLine="updateLbl";
_updatelbl();
RDebugUtils.currentLine=16056326;
 //BA.debugLineNum = 16056326;BA.debugLine="End Sub";
return "";
}
public static String  _skipbtn_click() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "skipbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "skipbtn_click", null));}
RDebugUtils.currentLine=16384000;
 //BA.debugLineNum = 16384000;BA.debugLine="Private Sub skipBtn_Click";
RDebugUtils.currentLine=16384001;
 //BA.debugLineNum = 16384001;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
RDebugUtils.currentLine=16384002;
 //BA.debugLineNum = 16384002;BA.debugLine="counter = counter + 1";
_counter = (int) (_counter+1);
RDebugUtils.currentLine=16384004;
 //BA.debugLineNum = 16384004;BA.debugLine="If counter Mod 4 = 0 Then";
if (_counter%4==0) { 
RDebugUtils.currentLine=16384005;
 //BA.debugLineNum = 16384005;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
RDebugUtils.currentLine=16384006;
 //BA.debugLineNum = 16384006;BA.debugLine="break = 1";
_break = (int) (1);
 }else {
RDebugUtils.currentLine=16384008;
 //BA.debugLineNum = 16384008;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
RDebugUtils.currentLine=16384009;
 //BA.debugLineNum = 16384009;BA.debugLine="break = 0";
_break = (int) (0);
 };
RDebugUtils.currentLine=16384011;
 //BA.debugLineNum = 16384011;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
 }else 
{RDebugUtils.currentLine=16384013;
 //BA.debugLineNum = 16384013;BA.debugLine="Else If timerState = 1 Then";
if (_timerstate==1) { 
RDebugUtils.currentLine=16384014;
 //BA.debugLineNum = 16384014;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
RDebugUtils.currentLine=16384015;
 //BA.debugLineNum = 16384015;BA.debugLine="timerState = 0";
_timerstate = (int) (0);
 }}
;
RDebugUtils.currentLine=16384018;
 //BA.debugLineNum = 16384018;BA.debugLine="updateLbl";
_updatelbl();
RDebugUtils.currentLine=16384019;
 //BA.debugLineNum = 16384019;BA.debugLine="timerStop";
_timerstop();
RDebugUtils.currentLine=16384020;
 //BA.debugLineNum = 16384020;BA.debugLine="End Sub";
return "";
}
public static String  _tmr_tick() throws Exception{
RDebugUtils.currentModule="clockactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "tmr_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "tmr_tick", null));}
RDebugUtils.currentLine=15794176;
 //BA.debugLineNum = 15794176;BA.debugLine="Sub tmr_Tick";
RDebugUtils.currentLine=15794177;
 //BA.debugLineNum = 15794177;BA.debugLine="If secondsRemain > 0 Then";
if (_secondsremain>0) { 
RDebugUtils.currentLine=15794178;
 //BA.debugLineNum = 15794178;BA.debugLine="secondsRemain = secondsRemain - 1";
_secondsremain = (int) (_secondsremain-1);
RDebugUtils.currentLine=15794179;
 //BA.debugLineNum = 15794179;BA.debugLine="updateLbl";
_updatelbl();
 }else {
RDebugUtils.currentLine=15794181;
 //BA.debugLineNum = 15794181;BA.debugLine="timerStop";
_timerstop();
RDebugUtils.currentLine=15794183;
 //BA.debugLineNum = 15794183;BA.debugLine="If timerState = 0 Then";
if (_timerstate==0) { 
RDebugUtils.currentLine=15794184;
 //BA.debugLineNum = 15794184;BA.debugLine="counter = counter + 1";
_counter = (int) (_counter+1);
RDebugUtils.currentLine=15794186;
 //BA.debugLineNum = 15794186;BA.debugLine="If counter Mod 4 = 0 Then";
if (_counter%4==0) { 
RDebugUtils.currentLine=15794187;
 //BA.debugLineNum = 15794187;BA.debugLine="secondsRemain = longDef";
_secondsremain = _longdef;
RDebugUtils.currentLine=15794188;
 //BA.debugLineNum = 15794188;BA.debugLine="break = 1";
_break = (int) (1);
 }else {
RDebugUtils.currentLine=15794190;
 //BA.debugLineNum = 15794190;BA.debugLine="secondsRemain = shortDef";
_secondsremain = _shortdef;
RDebugUtils.currentLine=15794191;
 //BA.debugLineNum = 15794191;BA.debugLine="break = 0";
_break = (int) (0);
 };
RDebugUtils.currentLine=15794193;
 //BA.debugLineNum = 15794193;BA.debugLine="timerState = 1";
_timerstate = (int) (1);
 }else 
{RDebugUtils.currentLine=15794195;
 //BA.debugLineNum = 15794195;BA.debugLine="Else If timerState = 1 Then";
if (_timerstate==1) { 
RDebugUtils.currentLine=15794196;
 //BA.debugLineNum = 15794196;BA.debugLine="secondsRemain = pomoDef";
_secondsremain = _pomodef;
RDebugUtils.currentLine=15794197;
 //BA.debugLineNum = 15794197;BA.debugLine="timerState = 0";
_timerstate = (int) (0);
 }}
;
RDebugUtils.currentLine=15794200;
 //BA.debugLineNum = 15794200;BA.debugLine="updateLbl";
_updatelbl();
 };
RDebugUtils.currentLine=15794202;
 //BA.debugLineNum = 15794202;BA.debugLine="End Sub";
return "";
}
}