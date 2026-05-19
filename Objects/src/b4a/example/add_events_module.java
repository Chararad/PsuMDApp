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

public class add_events_module extends Activity implements B4AActivity{
	public static add_events_module mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.add_events_module");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (add_events_module).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.add_events_module");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.add_events_module", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (add_events_module) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (add_events_module) Resume **");
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
		return add_events_module.class;
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
            BA.LogInfo("** Activity (add_events_module) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (add_events_module) Pause event (activity is not paused). **");
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
            add_events_module mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (add_events_module) Resume **");
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
public static String _eventtype = "";
public static String _currentdate = "";
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _taskrb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _eventrb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _birthdayrb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _ooorb = null;
public anywheresoftware.b4a.objects.LabelWrapper _timelbl = null;
public anywheresoftware.b4a.objects.EditTextWrapper _title_et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _description_et = null;
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
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.card_module _card_module = null;
public b4a.example.clockactivity _clockactivity = null;
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
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=12582912;
 //BA.debugLineNum = 12582912;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=12582913;
 //BA.debugLineNum = 12582913;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=12582915;
 //BA.debugLineNum = 12582915;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12582916;
 //BA.debugLineNum = 12582916;BA.debugLine="Activity.LoadLayout(\"AEMLayout\")";
mostCurrent._activity.LoadLayout("AEMLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12582918;
 //BA.debugLineNum = 12582918;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark\")";
mostCurrent._activity.LoadLayout("AEMLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=12582921;
 //BA.debugLineNum = 12582921;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12582922;
 //BA.debugLineNum = 12582922;BA.debugLine="Activity.LoadLayout(\"AEMLayout2\")";
mostCurrent._activity.LoadLayout("AEMLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12582924;
 //BA.debugLineNum = 12582924;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark2\")";
mostCurrent._activity.LoadLayout("AEMLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=12582927;
 //BA.debugLineNum = 12582927;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12582928;
 //BA.debugLineNum = 12582928;BA.debugLine="Activity.LoadLayout(\"AEMLayout3\")";
mostCurrent._activity.LoadLayout("AEMLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12582930;
 //BA.debugLineNum = 12582930;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark3\")";
mostCurrent._activity.LoadLayout("AEMLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=12582934;
 //BA.debugLineNum = 12582934;BA.debugLine="timelbl.Text = currentDate";
mostCurrent._timelbl.setText(BA.ObjectToCharSequence(_currentdate));
RDebugUtils.currentLine=12582936;
 //BA.debugLineNum = 12582936;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="add_events_module";
RDebugUtils.currentLine=12713984;
 //BA.debugLineNum = 12713984;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=12713986;
 //BA.debugLineNum = 12713986;BA.debugLine="eventrb.Checked = False";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12713987;
 //BA.debugLineNum = 12713987;BA.debugLine="taskrb.Checked = False";
mostCurrent._taskrb.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12713988;
 //BA.debugLineNum = 12713988;BA.debugLine="birthdayrb.Checked = False";
mostCurrent._birthdayrb.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12713989;
 //BA.debugLineNum = 12713989;BA.debugLine="ooorb.Checked = False";
mostCurrent._ooorb.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12713990;
 //BA.debugLineNum = 12713990;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=12648448;
 //BA.debugLineNum = 12648448;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=12648449;
 //BA.debugLineNum = 12648449;BA.debugLine="If eventtype = \"Event\" Then";
if ((_eventtype).equals("Event")) { 
RDebugUtils.currentLine=12648450;
 //BA.debugLineNum = 12648450;BA.debugLine="eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }else 
{RDebugUtils.currentLine=12648451;
 //BA.debugLineNum = 12648451;BA.debugLine="Else if eventtype = \"Task\" Then";
if ((_eventtype).equals("Task")) { 
RDebugUtils.currentLine=12648452;
 //BA.debugLineNum = 12648452;BA.debugLine="taskrb.Checked = True";
mostCurrent._taskrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }else 
{RDebugUtils.currentLine=12648453;
 //BA.debugLineNum = 12648453;BA.debugLine="Else if eventtype = \"Birthday\" Then";
if ((_eventtype).equals("Birthday")) { 
RDebugUtils.currentLine=12648454;
 //BA.debugLineNum = 12648454;BA.debugLine="birthdayrb.Checked = True";
mostCurrent._birthdayrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }else 
{RDebugUtils.currentLine=12648455;
 //BA.debugLineNum = 12648455;BA.debugLine="Else if eventtype = \"OOO\" Then";
if ((_eventtype).equals("OOO")) { 
RDebugUtils.currentLine=12648456;
 //BA.debugLineNum = 12648456;BA.debugLine="ooorb.Checked = True";
mostCurrent._ooorb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }}}}
;
RDebugUtils.currentLine=12648458;
 //BA.debugLineNum = 12648458;BA.debugLine="End Sub";
return "";
}
public static String  _birthdayrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "birthdayrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "birthdayrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=13107200;
 //BA.debugLineNum = 13107200;BA.debugLine="Private Sub birthdayrb_CheckedChange(Checked As Bo";
RDebugUtils.currentLine=13107201;
 //BA.debugLineNum = 13107201;BA.debugLine="eventtype = \"Birthday\"";
_eventtype = "Birthday";
RDebugUtils.currentLine=13107202;
 //BA.debugLineNum = 13107202;BA.debugLine="End Sub";
return "";
}
public static String  _eventrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "eventrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "eventrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=13172736;
 //BA.debugLineNum = 13172736;BA.debugLine="Private Sub eventrb_CheckedChange(Checked As Boole";
RDebugUtils.currentLine=13172737;
 //BA.debugLineNum = 13172737;BA.debugLine="eventtype = \"Event\"";
_eventtype = "Event";
RDebugUtils.currentLine=13172738;
 //BA.debugLineNum = 13172738;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _mapinitializer() throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mapinitializer", false))
	 {return ((anywheresoftware.b4a.objects.collections.Map) Debug.delegate(mostCurrent.activityBA, "mapinitializer", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
RDebugUtils.currentLine=12910592;
 //BA.debugLineNum = 12910592;BA.debugLine="Sub MapInitializer As Map";
RDebugUtils.currentLine=12910593;
 //BA.debugLineNum = 12910593;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=12910595;
 //BA.debugLineNum = 12910595;BA.debugLine="eventmap.Initialize";
_eventmap.Initialize();
RDebugUtils.currentLine=12910596;
 //BA.debugLineNum = 12910596;BA.debugLine="Dim allevents As List";
_allevents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=12910597;
 //BA.debugLineNum = 12910597;BA.debugLine="allevents.initialize";
_allevents.Initialize();
RDebugUtils.currentLine=12910599;
 //BA.debugLineNum = 12910599;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=12910600;
 //BA.debugLineNum = 12910600;BA.debugLine="timeline.initialize";
_timeline.Initialize();
RDebugUtils.currentLine=12910602;
 //BA.debugLineNum = 12910602;BA.debugLine="eventmap.Put(\"AllEvents\", allevents)";
_eventmap.Put((Object)("AllEvents"),(Object)(_allevents.getObject()));
RDebugUtils.currentLine=12910603;
 //BA.debugLineNum = 12910603;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
RDebugUtils.currentLine=12910605;
 //BA.debugLineNum = 12910605;BA.debugLine="CalendarActivity.CalendarMap.Put(day_module.curre";
mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)(mostCurrent._day_module._currentdate /*String*/ ),(Object)(_eventmap.getObject()));
RDebugUtils.currentLine=12910607;
 //BA.debugLineNum = 12910607;BA.debugLine="Return eventmap";
if (true) return _eventmap;
RDebugUtils.currentLine=12910608;
 //BA.debugLineNum = 12910608;BA.debugLine="End Sub";
return null;
}
public static String  _ooorb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ooorb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ooorb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=13041664;
 //BA.debugLineNum = 13041664;BA.debugLine="Private Sub ooorb_CheckedChange(Checked As Boolean";
RDebugUtils.currentLine=13041665;
 //BA.debugLineNum = 13041665;BA.debugLine="eventtype = \"OOO\"";
_eventtype = "OOO";
RDebugUtils.currentLine=13041666;
 //BA.debugLineNum = 13041666;BA.debugLine="End Sub";
return "";
}
public static String  _save_btn_click() throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "save_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "save_btn_click", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _getallevents = null;
anywheresoftware.b4a.objects.collections.Map _putevent = null;
RDebugUtils.currentLine=12976128;
 //BA.debugLineNum = 12976128;BA.debugLine="Private Sub save_btn_Click";
RDebugUtils.currentLine=12976129;
 //BA.debugLineNum = 12976129;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=12976130;
 //BA.debugLineNum = 12976130;BA.debugLine="If title_et.text = \"\" Then";
if ((mostCurrent._title_et.getText()).equals("")) { 
RDebugUtils.currentLine=12976131;
 //BA.debugLineNum = 12976131;BA.debugLine="MsgboxAsync(\"Enter The Event Title\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Enter The Event Title"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=12976132;
 //BA.debugLineNum = 12976132;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=12976135;
 //BA.debugLineNum = 12976135;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(day_m";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(mostCurrent._day_module._currentdate /*String*/ ))) { 
RDebugUtils.currentLine=12976136;
 //BA.debugLineNum = 12976136;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(day_";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._day_module._currentdate /*String*/ ))));
 }else {
RDebugUtils.currentLine=12976138;
 //BA.debugLineNum = 12976138;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
RDebugUtils.currentLine=12976141;
 //BA.debugLineNum = 12976141;BA.debugLine="Dim getAllevents As List = eventmap.Get(\"AllEvent";
_getallevents = new anywheresoftware.b4a.objects.collections.List();
_getallevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=12976142;
 //BA.debugLineNum = 12976142;BA.debugLine="Dim putevent As Map";
_putevent = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=12976143;
 //BA.debugLineNum = 12976143;BA.debugLine="putevent.Initialize";
_putevent.Initialize();
RDebugUtils.currentLine=12976144;
 //BA.debugLineNum = 12976144;BA.debugLine="putevent.Put(\"Title\", title_et.Text)";
_putevent.Put((Object)("Title"),(Object)(mostCurrent._title_et.getText()));
RDebugUtils.currentLine=12976145;
 //BA.debugLineNum = 12976145;BA.debugLine="putevent.Put(\"Description\", description_et.Text)";
_putevent.Put((Object)("Description"),(Object)(mostCurrent._description_et.getText()));
RDebugUtils.currentLine=12976146;
 //BA.debugLineNum = 12976146;BA.debugLine="putevent.Put(\"Tags\", eventtype)";
_putevent.Put((Object)("Tags"),(Object)(_eventtype));
RDebugUtils.currentLine=12976148;
 //BA.debugLineNum = 12976148;BA.debugLine="getAllevents.Add(putevent)";
_getallevents.Add((Object)(_putevent.getObject()));
RDebugUtils.currentLine=12976149;
 //BA.debugLineNum = 12976149;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=12976150;
 //BA.debugLineNum = 12976150;BA.debugLine="day_module.addeventsfeedback = True";
mostCurrent._day_module._addeventsfeedback /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=12976151;
 //BA.debugLineNum = 12976151;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=12976152;
 //BA.debugLineNum = 12976152;BA.debugLine="End Sub";
return "";
}
public static String  _savecalendar() throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savecalendar", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savecalendar", null));}
RDebugUtils.currentLine=12845056;
 //BA.debugLineNum = 12845056;BA.debugLine="Sub SaveCalendar";
RDebugUtils.currentLine=12845057;
 //BA.debugLineNum = 12845057;BA.debugLine="CalendarActivity.kvs.put(\"CalendarKVS\", CalendarA";
mostCurrent._calendaractivity._kvs /*b4a.example3.keyvaluestore*/ ._put("CalendarKVS",(Object)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
RDebugUtils.currentLine=12845058;
 //BA.debugLineNum = 12845058;BA.debugLine="End Sub";
return "";
}
public static String  _taskrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=13238272;
 //BA.debugLineNum = 13238272;BA.debugLine="Private Sub taskrb_CheckedChange(Checked As Boolea";
RDebugUtils.currentLine=13238273;
 //BA.debugLineNum = 13238273;BA.debugLine="eventtype = \"Task\"";
_eventtype = "Task";
RDebugUtils.currentLine=13238274;
 //BA.debugLineNum = 13238274;BA.debugLine="End Sub";
return "";
}
public static String  _x_btn_click() throws Exception{
RDebugUtils.currentModule="add_events_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "x_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "x_btn_click", null));}
RDebugUtils.currentLine=12779520;
 //BA.debugLineNum = 12779520;BA.debugLine="Private Sub x_btn_Click";
RDebugUtils.currentLine=12779521;
 //BA.debugLineNum = 12779521;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=12779522;
 //BA.debugLineNum = 12779522;BA.debugLine="End Sub";
return "";
}
}