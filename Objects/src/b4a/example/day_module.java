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

public class day_module extends Activity implements B4AActivity{
	public static day_module mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.day_module");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (day_module).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.day_module");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.day_module", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (day_module) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (day_module) Resume **");
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
		return day_module.class;
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
            BA.LogInfo("** Activity (day_module) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (day_module) Pause event (activity is not paused). **");
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
            day_module mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (day_module) Resume **");
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
public static String _currentdate = "";
public static boolean _addeventsfeedback = false;
public static int _currentindex = 0;
public static anywheresoftware.b4a.objects.collections.Map _schedules = null;
public anywheresoftware.b4a.objects.ButtonWrapper _day_btn = null;
public anywheresoftware.b4a.objects.PanelWrapper _menupanel = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _svtimeline = null;
public anywheresoftware.b4a.objects.LabelWrapper _date_todaylbl = null;
public anywheresoftware.b4a.objects.PanelWrapper _addpanel = null;
public anywheresoftware.b4a.objects.ButtonWrapper _addtask_btn = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _svevents = null;
public anywheresoftware.b4a.objects.LabelWrapper _eventdescription_lbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _eventtitle_lbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _tags_lbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _datetoday_lbl = null;
public anywheresoftware.b4a.objects.PanelWrapper _eventinfo_panel = null;
public anywheresoftware.b4a.objects.PanelWrapper _editinfopanel = null;
public anywheresoftware.b4a.objects.collections.Map _currenttaggedevent = null;
public static int _timeindex = 0;
public static String _eventtype = "";
public static long _currentevid = 0L;
public anywheresoftware.b4a.objects.EditTextWrapper _edittitle_et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _editdescription_et = null;
public anywheresoftware.b4a.objects.PanelWrapper _deletepanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _addtl_et = null;
public anywheresoftware.b4a.objects.PanelWrapper _addeventtl_panel = null;
public anywheresoftware.b4a.objects.LabelWrapper _timelabel = null;
public anywheresoftware.b4a.objects.PanelWrapper _deletetlevent_confirmationpanel = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _starttimelinesp = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _endtimelinesp = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _taskrb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _eventrb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _birthdayrb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _ooorb = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.card_module _card_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=9437184;
 //BA.debugLineNum = 9437184;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=9437185;
 //BA.debugLineNum = 9437185;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=9437186;
 //BA.debugLineNum = 9437186;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout",mostCurrent.activityBA);
RDebugUtils.currentLine=9437187;
 //BA.debugLineNum = 9437187;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
mostCurrent._starttimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=9437188;
 //BA.debugLineNum = 9437188;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.Black";
mostCurrent._starttimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=9437189;
 //BA.debugLineNum = 9437189;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.W";
mostCurrent._endtimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=9437190;
 //BA.debugLineNum = 9437190;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.Black";
mostCurrent._endtimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=9437192;
 //BA.debugLineNum = 9437192;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark",mostCurrent.activityBA);
RDebugUtils.currentLine=9437193;
 //BA.debugLineNum = 9437193;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
mostCurrent._starttimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=9437194;
 //BA.debugLineNum = 9437194;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.White";
mostCurrent._starttimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=9437195;
 //BA.debugLineNum = 9437195;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.D";
mostCurrent._endtimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=9437196;
 //BA.debugLineNum = 9437196;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.White";
mostCurrent._endtimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=9437199;
 //BA.debugLineNum = 9437199;BA.debugLine="Day_btn.Color = Colors.LightGray";
mostCurrent._day_btn.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
RDebugUtils.currentLine=9437200;
 //BA.debugLineNum = 9437200;BA.debugLine="date_todaylbl.Text = SetDate(currentDate)";
mostCurrent._date_todaylbl.setText(BA.ObjectToCharSequence(_setdate(_currentdate)));
RDebugUtils.currentLine=9437201;
 //BA.debugLineNum = 9437201;BA.debugLine="add_events_module.currentDate = SetDate(currentDa";
mostCurrent._add_events_module._currentdate /*String*/  = _setdate(_currentdate);
RDebugUtils.currentLine=9437202;
 //BA.debugLineNum = 9437202;BA.debugLine="SetUpSpinners";
_setupspinners();
RDebugUtils.currentLine=9437204;
 //BA.debugLineNum = 9437204;BA.debugLine="Log(currentDate)";
anywheresoftware.b4a.keywords.Common.LogImpl("29437204",_currentdate,0);
RDebugUtils.currentLine=9437206;
 //BA.debugLineNum = 9437206;BA.debugLine="End Sub";
return "";
}
public static String  _setdate(String _tagdate) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setdate", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setdate", new Object[] {_tagdate}));}
String[] _parts = null;
String _year = "";
int _monthnum = 0;
String _day = "";
String _monthname = "";
long _ts = 0L;
int _weekdaynum = 0;
String _week = "";
RDebugUtils.currentLine=10354688;
 //BA.debugLineNum = 10354688;BA.debugLine="Sub SetDate(Tagdate As String) As String";
RDebugUtils.currentLine=10354690;
 //BA.debugLineNum = 10354690;BA.debugLine="Dim parts() As String = Regex.Split(\"-\", Tagdate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_tagdate);
RDebugUtils.currentLine=10354691;
 //BA.debugLineNum = 10354691;BA.debugLine="Dim year As String = parts(0)";
_year = _parts[(int) (0)];
RDebugUtils.currentLine=10354692;
 //BA.debugLineNum = 10354692;BA.debugLine="Dim monthNum As Int = parts(1)";
_monthnum = (int)(Double.parseDouble(_parts[(int) (1)]));
RDebugUtils.currentLine=10354693;
 //BA.debugLineNum = 10354693;BA.debugLine="Dim day As String = parts(2)";
_day = _parts[(int) (2)];
RDebugUtils.currentLine=10354695;
 //BA.debugLineNum = 10354695;BA.debugLine="Dim monthName As String";
_monthname = "";
RDebugUtils.currentLine=10354696;
 //BA.debugLineNum = 10354696;BA.debugLine="Select monthNum";
switch (_monthnum) {
case 1: {
RDebugUtils.currentLine=10354697;
 //BA.debugLineNum = 10354697;BA.debugLine="Case 1: monthName = \"January\"";
_monthname = "January";
 break; }
case 2: {
RDebugUtils.currentLine=10354698;
 //BA.debugLineNum = 10354698;BA.debugLine="Case 2: monthName = \"February\"";
_monthname = "February";
 break; }
case 3: {
RDebugUtils.currentLine=10354699;
 //BA.debugLineNum = 10354699;BA.debugLine="Case 3: monthName = \"March\"";
_monthname = "March";
 break; }
case 4: {
RDebugUtils.currentLine=10354700;
 //BA.debugLineNum = 10354700;BA.debugLine="Case 4: monthName = \"April\"";
_monthname = "April";
 break; }
case 5: {
RDebugUtils.currentLine=10354701;
 //BA.debugLineNum = 10354701;BA.debugLine="Case 5: monthName = \"May\"";
_monthname = "May";
 break; }
case 6: {
RDebugUtils.currentLine=10354702;
 //BA.debugLineNum = 10354702;BA.debugLine="Case 6: monthName = \"June\"";
_monthname = "June";
 break; }
case 7: {
RDebugUtils.currentLine=10354703;
 //BA.debugLineNum = 10354703;BA.debugLine="Case 7: monthName = \"July\"";
_monthname = "July";
 break; }
case 8: {
RDebugUtils.currentLine=10354704;
 //BA.debugLineNum = 10354704;BA.debugLine="Case 8: monthName = \"August\"";
_monthname = "August";
 break; }
case 9: {
RDebugUtils.currentLine=10354705;
 //BA.debugLineNum = 10354705;BA.debugLine="Case 9: monthName = \"September\"";
_monthname = "September";
 break; }
case 10: {
RDebugUtils.currentLine=10354706;
 //BA.debugLineNum = 10354706;BA.debugLine="Case 10: monthName = \"October\"";
_monthname = "October";
 break; }
case 11: {
RDebugUtils.currentLine=10354707;
 //BA.debugLineNum = 10354707;BA.debugLine="Case 11: monthName = \"November\"";
_monthname = "November";
 break; }
case 12: {
RDebugUtils.currentLine=10354708;
 //BA.debugLineNum = 10354708;BA.debugLine="Case 12: monthName = \"December\"";
_monthname = "December";
 break; }
}
;
RDebugUtils.currentLine=10354711;
 //BA.debugLineNum = 10354711;BA.debugLine="Dim ts As Long = DateTime.DateParse(Tagdate)";
_ts = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_tagdate);
RDebugUtils.currentLine=10354712;
 //BA.debugLineNum = 10354712;BA.debugLine="Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)";
_weekdaynum = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_ts);
RDebugUtils.currentLine=10354713;
 //BA.debugLineNum = 10354713;BA.debugLine="Dim week As String";
_week = "";
RDebugUtils.currentLine=10354714;
 //BA.debugLineNum = 10354714;BA.debugLine="Select weekdayNum";
switch (_weekdaynum) {
case 1: {
RDebugUtils.currentLine=10354715;
 //BA.debugLineNum = 10354715;BA.debugLine="Case 1: week = \"Sunday\"";
_week = "Sunday";
 break; }
case 2: {
RDebugUtils.currentLine=10354716;
 //BA.debugLineNum = 10354716;BA.debugLine="Case 2: week = \"Monday\"";
_week = "Monday";
 break; }
case 3: {
RDebugUtils.currentLine=10354717;
 //BA.debugLineNum = 10354717;BA.debugLine="Case 3: week = \"Tuesday\"";
_week = "Tuesday";
 break; }
case 4: {
RDebugUtils.currentLine=10354718;
 //BA.debugLineNum = 10354718;BA.debugLine="Case 4: week = \"Wednesday\"";
_week = "Wednesday";
 break; }
case 5: {
RDebugUtils.currentLine=10354719;
 //BA.debugLineNum = 10354719;BA.debugLine="Case 5: week = \"Thursday\"";
_week = "Thursday";
 break; }
case 6: {
RDebugUtils.currentLine=10354720;
 //BA.debugLineNum = 10354720;BA.debugLine="Case 6: week = \"Friday\"";
_week = "Friday";
 break; }
case 7: {
RDebugUtils.currentLine=10354721;
 //BA.debugLineNum = 10354721;BA.debugLine="Case 7: week = \"Saturday\"";
_week = "Saturday";
 break; }
}
;
RDebugUtils.currentLine=10354724;
 //BA.debugLineNum = 10354724;BA.debugLine="Return week & \", \" & monthName & \" \" & day & \", \"";
if (true) return _week+", "+_monthname+" "+_day+", "+_year;
RDebugUtils.currentLine=10354725;
 //BA.debugLineNum = 10354725;BA.debugLine="End Sub";
return "";
}
public static String  _setupspinners() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setupspinners", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setupspinners", null));}
anywheresoftware.b4a.objects.collections.List _hours = null;
int _i = 0;
RDebugUtils.currentLine=9502720;
 //BA.debugLineNum = 9502720;BA.debugLine="Sub SetUpSpinners";
RDebugUtils.currentLine=9502721;
 //BA.debugLineNum = 9502721;BA.debugLine="Dim hours As List";
_hours = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=9502722;
 //BA.debugLineNum = 9502722;BA.debugLine="hours.Initialize";
_hours.Initialize();
RDebugUtils.currentLine=9502723;
 //BA.debugLineNum = 9502723;BA.debugLine="For i = 0 To 24";
{
final int step3 = 1;
final int limit3 = (int) (24);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=9502724;
 //BA.debugLineNum = 9502724;BA.debugLine="hours.Add(GetTimeString(i))";
_hours.Add((Object)(_gettimestring(_i)));
 }
};
RDebugUtils.currentLine=9502726;
 //BA.debugLineNum = 9502726;BA.debugLine="starttimelineSP.AddAll(hours)";
mostCurrent._starttimelinesp.AddAll(_hours);
RDebugUtils.currentLine=9502727;
 //BA.debugLineNum = 9502727;BA.debugLine="endtimelineSP.AddAll(hours)";
mostCurrent._endtimelinesp.AddAll(_hours);
RDebugUtils.currentLine=9502728;
 //BA.debugLineNum = 9502728;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="day_module";
RDebugUtils.currentLine=10485760;
 //BA.debugLineNum = 10485760;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=10485762;
 //BA.debugLineNum = 10485762;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=10420224;
 //BA.debugLineNum = 10420224;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=10420225;
 //BA.debugLineNum = 10420225;BA.debugLine="UpdateTimeLine";
_updatetimeline();
RDebugUtils.currentLine=10420226;
 //BA.debugLineNum = 10420226;BA.debugLine="If addeventsfeedback = True Then";
if (_addeventsfeedback==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=10420227;
 //BA.debugLineNum = 10420227;BA.debugLine="addeventsfeedback = False";
_addeventsfeedback = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=10420228;
 //BA.debugLineNum = 10420228;BA.debugLine="MsgboxAsync(\"Event Saved\", \"Saved\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event Saved"),BA.ObjectToCharSequence("Saved"),processBA);
 };
RDebugUtils.currentLine=10420230;
 //BA.debugLineNum = 10420230;BA.debugLine="End Sub";
return "";
}
public static String  _updatetimeline() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatetimeline", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatetimeline", null));}
RDebugUtils.currentLine=9830400;
 //BA.debugLineNum = 9830400;BA.debugLine="Sub UpdateTimeLine";
RDebugUtils.currentLine=9830401;
 //BA.debugLineNum = 9830401;BA.debugLine="DrawMainEvents";
_drawmainevents();
RDebugUtils.currentLine=9830402;
 //BA.debugLineNum = 9830402;BA.debugLine="DrawHourLabels";
_drawhourlabels();
RDebugUtils.currentLine=9830403;
 //BA.debugLineNum = 9830403;BA.debugLine="DrawTimelineEvents";
_drawtimelineevents();
RDebugUtils.currentLine=9830404;
 //BA.debugLineNum = 9830404;BA.debugLine="End Sub";
return "";
}
public static String  _addevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addevent_btn_click", null));}
RDebugUtils.currentLine=10813440;
 //BA.debugLineNum = 10813440;BA.debugLine="Private Sub Addevent_btn_Click";
RDebugUtils.currentLine=10813441;
 //BA.debugLineNum = 10813441;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10813442;
 //BA.debugLineNum = 10813442;BA.debugLine="add_events_module.eventtype = \"Event\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Event";
RDebugUtils.currentLine=10813443;
 //BA.debugLineNum = 10813443;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=10813444;
 //BA.debugLineNum = 10813444;BA.debugLine="End Sub";
return "";
}
public static String  _addnew_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addnew_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addnew_btn_click", null));}
RDebugUtils.currentLine=10878976;
 //BA.debugLineNum = 10878976;BA.debugLine="Private Sub addnew_btn_Click";
RDebugUtils.currentLine=10878978;
 //BA.debugLineNum = 10878978;BA.debugLine="If addpanel.Visible =True Then";
if (mostCurrent._addpanel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=10878979;
 //BA.debugLineNum = 10878979;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10878980;
 //BA.debugLineNum = 10878980;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=10878982;
 //BA.debugLineNum = 10878982;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=10878985;
 //BA.debugLineNum = 10878985;BA.debugLine="End Sub";
return "";
}
public static String  _addtask_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtask_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtask_btn_click", null));}
RDebugUtils.currentLine=10944512;
 //BA.debugLineNum = 10944512;BA.debugLine="Private Sub Addtask_btn_Click";
RDebugUtils.currentLine=10944513;
 //BA.debugLineNum = 10944513;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10944514;
 //BA.debugLineNum = 10944514;BA.debugLine="add_events_module.eventtype = \"Task\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Task";
RDebugUtils.currentLine=10944515;
 //BA.debugLineNum = 10944515;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=10944516;
 //BA.debugLineNum = 10944516;BA.debugLine="End Sub";
return "";
}
public static String  _birthday_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "birthday_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "birthday_btn_click", null));}
RDebugUtils.currentLine=11010048;
 //BA.debugLineNum = 11010048;BA.debugLine="Private Sub birthday_btn_Click";
RDebugUtils.currentLine=11010049;
 //BA.debugLineNum = 11010049;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11010050;
 //BA.debugLineNum = 11010050;BA.debugLine="add_events_module.eventtype = \"Birthday\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Birthday";
RDebugUtils.currentLine=11010051;
 //BA.debugLineNum = 11010051;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=11010052;
 //BA.debugLineNum = 11010052;BA.debugLine="End Sub";
return "";
}
public static String  _birthdayrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "birthdayrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "birthdayrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=11927552;
 //BA.debugLineNum = 11927552;BA.debugLine="Private Sub birthdayrb_CheckedChange(Checked As Bo";
RDebugUtils.currentLine=11927553;
 //BA.debugLineNum = 11927553;BA.debugLine="eventtype = \"Birthday\"";
mostCurrent._eventtype = "Birthday";
RDebugUtils.currentLine=11927554;
 //BA.debugLineNum = 11927554;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceldelete_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceldelete_btn_click", null));}
RDebugUtils.currentLine=11468800;
 //BA.debugLineNum = 11468800;BA.debugLine="Private Sub cancelDelete_btn_Click";
RDebugUtils.currentLine=11468801;
 //BA.debugLineNum = 11468801;BA.debugLine="deletepanel.Visible = False";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11468802;
 //BA.debugLineNum = 11468802;BA.debugLine="End Sub";
return "";
}
public static String  _canceledit_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceledit_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceledit_btn_click", null));}
RDebugUtils.currentLine=11337728;
 //BA.debugLineNum = 11337728;BA.debugLine="Private Sub cancelEdit_btn_Click";
RDebugUtils.currentLine=11337729;
 //BA.debugLineNum = 11337729;BA.debugLine="EditInfoPanel.Visible = False";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11337730;
 //BA.debugLineNum = 11337730;BA.debugLine="End Sub";
return "";
}
public static String  _canceltldelete_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceltldelete_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceltldelete_btn_click", null));}
RDebugUtils.currentLine=11730944;
 //BA.debugLineNum = 11730944;BA.debugLine="Private Sub cancelTLdelete_btn_Click";
RDebugUtils.currentLine=11730945;
 //BA.debugLineNum = 11730945;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11730946;
 //BA.debugLineNum = 11730946;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmdelete_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmdelete_btn_click", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
RDebugUtils.currentLine=11534336;
 //BA.debugLineNum = 11534336;BA.debugLine="Private Sub confirmdelete_btn_Click";
RDebugUtils.currentLine=11534337;
 //BA.debugLineNum = 11534337;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=11534338;
 //BA.debugLineNum = 11534338;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=11534339;
 //BA.debugLineNum = 11534339;BA.debugLine="allevents.RemoveAt(currentIndex)";
_allevents.RemoveAt(_currentindex);
RDebugUtils.currentLine=11534340;
 //BA.debugLineNum = 11534340;BA.debugLine="deletepanel.Visible = False";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11534341;
 //BA.debugLineNum = 11534341;BA.debugLine="eventInfo_panel.Visible = False";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11534342;
 //BA.debugLineNum = 11534342;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=11534343;
 //BA.debugLineNum = 11534343;BA.debugLine="DrawMainEvents";
_drawmainevents();
RDebugUtils.currentLine=11534344;
 //BA.debugLineNum = 11534344;BA.debugLine="End Sub";
return "";
}
public static String  _savecalendar() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savecalendar", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savecalendar", null));}
RDebugUtils.currentLine=9568256;
 //BA.debugLineNum = 9568256;BA.debugLine="Sub SaveCalendar";
RDebugUtils.currentLine=9568257;
 //BA.debugLineNum = 9568257;BA.debugLine="CalendarActivity.kvs.put(\"CalendarKVS\", CalendarA";
mostCurrent._calendaractivity._kvs /*b4a.example3.keyvaluestore*/ ._put("CalendarKVS",(Object)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
RDebugUtils.currentLine=9568258;
 //BA.debugLineNum = 9568258;BA.debugLine="End Sub";
return "";
}
public static String  _drawmainevents() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "drawmainevents", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "drawmainevents", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
int _y = 0;
int _rowheight = 0;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
RDebugUtils.currentLine=9633792;
 //BA.debugLineNum = 9633792;BA.debugLine="Sub DrawMainEvents";
RDebugUtils.currentLine=9633793;
 //BA.debugLineNum = 9633793;BA.debugLine="svEvents.Panel.RemoveAllViews";
mostCurrent._svevents.getPanel().RemoveAllViews();
RDebugUtils.currentLine=9633794;
 //BA.debugLineNum = 9633794;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate)))) { 
RDebugUtils.currentLine=9633795;
 //BA.debugLineNum = 9633795;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=9633798;
 //BA.debugLineNum = 9633798;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=9633799;
 //BA.debugLineNum = 9633799;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=9633801;
 //BA.debugLineNum = 9633801;BA.debugLine="Dim y As Int = 0";
_y = (int) (0);
RDebugUtils.currentLine=9633802;
 //BA.debugLineNum = 9633802;BA.debugLine="Dim rowHeight As Int = 50dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50));
RDebugUtils.currentLine=9633803;
 //BA.debugLineNum = 9633803;BA.debugLine="For i = 0 To allevents.Size -1";
{
final int step9 = 1;
final int limit9 = (int) (_allevents.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
RDebugUtils.currentLine=9633804;
 //BA.debugLineNum = 9633804;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=9633805;
 //BA.debugLineNum = 9633805;BA.debugLine="Dim ev As Map = allevents.Get(i)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_allevents.Get(_i)));
RDebugUtils.currentLine=9633806;
 //BA.debugLineNum = 9633806;BA.debugLine="lbl.Initialize(\"mainEvent\")";
_lbl.Initialize(mostCurrent.activityBA,"mainEvent");
RDebugUtils.currentLine=9633807;
 //BA.debugLineNum = 9633807;BA.debugLine="lbl.Tag = i";
_lbl.setTag((Object)(_i));
RDebugUtils.currentLine=9633808;
 //BA.debugLineNum = 9633808;BA.debugLine="lbl.Text = ev.Get(\"Title\")";
_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=9633809;
 //BA.debugLineNum = 9633809;BA.debugLine="lbl.Gravity = Gravity.CENTER_vertical";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=9633810;
 //BA.debugLineNum = 9633810;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=9633811;
 //BA.debugLineNum = 9633811;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
_lbl.setColor(_identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags")))));
RDebugUtils.currentLine=9633813;
 //BA.debugLineNum = 9633813;BA.debugLine="svEvents.Panel.AddView(lbl, 0, y, svEvents.Width";
mostCurrent._svevents.getPanel().AddView((android.view.View)(_lbl.getObject()),(int) (0),_y,mostCurrent._svevents.getWidth(),_rowheight);
RDebugUtils.currentLine=9633814;
 //BA.debugLineNum = 9633814;BA.debugLine="y = y+rowHeight";
_y = (int) (_y+_rowheight);
 }
};
RDebugUtils.currentLine=9633817;
 //BA.debugLineNum = 9633817;BA.debugLine="End Sub";
return "";
}
public static String  _day_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "day_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "day_btn_click", null));}
RDebugUtils.currentLine=10682368;
 //BA.debugLineNum = 10682368;BA.debugLine="Private Sub Day_btn_Click";
RDebugUtils.currentLine=10682369;
 //BA.debugLineNum = 10682369;BA.debugLine="menupanel.Visible = False";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10682370;
 //BA.debugLineNum = 10682370;BA.debugLine="End Sub";
return "";
}
public static String  _deleteevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deleteevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deleteevent_btn_click", null));}
RDebugUtils.currentLine=11272192;
 //BA.debugLineNum = 11272192;BA.debugLine="Private Sub DeleteEvent_btn_Click";
RDebugUtils.currentLine=11272193;
 //BA.debugLineNum = 11272193;BA.debugLine="deletepanel.Visible = True";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=11272194;
 //BA.debugLineNum = 11272194;BA.debugLine="End Sub";
return "";
}
public static String  _deletetlconfirm_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletetlconfirm_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletetlconfirm_btn_click", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _ev = null;
RDebugUtils.currentLine=11796480;
 //BA.debugLineNum = 11796480;BA.debugLine="Private Sub deleteTLconfirm_btn_Click";
RDebugUtils.currentLine=11796481;
 //BA.debugLineNum = 11796481;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=11796482;
 //BA.debugLineNum = 11796482;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=11796484;
 //BA.debugLineNum = 11796484;BA.debugLine="For i = timeline.Size -1 To 0 Step -1";
{
final int step3 = -1;
final int limit3 = (int) (0);
_i = (int) (_timeline.getSize()-1) ;
for (;_i >= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=11796485;
 //BA.debugLineNum = 11796485;BA.debugLine="Dim ev As Map = timeline.Get(i)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_timeline.Get(_i)));
RDebugUtils.currentLine=11796486;
 //BA.debugLineNum = 11796486;BA.debugLine="If ev.Get(\"ID\") = currentevId Then";
if ((_ev.Get((Object)("ID"))).equals((Object)(_currentevid))) { 
RDebugUtils.currentLine=11796487;
 //BA.debugLineNum = 11796487;BA.debugLine="timeline.RemoveAt(i)";
_timeline.RemoveAt(_i);
RDebugUtils.currentLine=11796488;
 //BA.debugLineNum = 11796488;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=11796492;
 //BA.debugLineNum = 11796492;BA.debugLine="addTL_et.Text = \"\"";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=11796493;
 //BA.debugLineNum = 11796493;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11796494;
 //BA.debugLineNum = 11796494;BA.debugLine="addEventTL_panel.Visible = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11796495;
 //BA.debugLineNum = 11796495;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=11796496;
 //BA.debugLineNum = 11796496;BA.debugLine="UpdateTimeLine";
_updatetimeline();
RDebugUtils.currentLine=11796497;
 //BA.debugLineNum = 11796497;BA.debugLine="End Sub";
return "";
}
public static String  _deletetlevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletetlevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletetlevent_btn_click", null));}
RDebugUtils.currentLine=11665408;
 //BA.debugLineNum = 11665408;BA.debugLine="Private Sub deleteTLevent_btn_Click";
RDebugUtils.currentLine=11665409;
 //BA.debugLineNum = 11665409;BA.debugLine="deleteTLevent_confirmationpanel.Visible = True";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=11665410;
 //BA.debugLineNum = 11665410;BA.debugLine="End Sub";
return "";
}
public static String  _drawhourlabels() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "drawhourlabels", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "drawhourlabels", null));}
int _rowh = 0;
int _h = 0;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
RDebugUtils.currentLine=9895936;
 //BA.debugLineNum = 9895936;BA.debugLine="Sub DrawHourLabels";
RDebugUtils.currentLine=9895939;
 //BA.debugLineNum = 9895939;BA.debugLine="Svtimeline.Panel.RemoveAllViews";
mostCurrent._svtimeline.getPanel().RemoveAllViews();
RDebugUtils.currentLine=9895941;
 //BA.debugLineNum = 9895941;BA.debugLine="Dim rowh As Int = 60dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=9895942;
 //BA.debugLineNum = 9895942;BA.debugLine="Svtimeline.Panel.Height = 24 * rowh";
mostCurrent._svtimeline.getPanel().setHeight((int) (24*_rowh));
RDebugUtils.currentLine=9895945;
 //BA.debugLineNum = 9895945;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=9895946;
 //BA.debugLineNum = 9895946;BA.debugLine="For h = 0 To 23";
{
final int step5 = 1;
final int limit5 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit5 ;_h = _h + step5 ) {
RDebugUtils.currentLine=9895947;
 //BA.debugLineNum = 9895947;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=9895948;
 //BA.debugLineNum = 9895948;BA.debugLine="p.Initialize(\"hour\")";
_p.Initialize(mostCurrent.activityBA,"hour");
RDebugUtils.currentLine=9895949;
 //BA.debugLineNum = 9895949;BA.debugLine="p.Tag = h";
_p.setTag((Object)(_h));
RDebugUtils.currentLine=9895950;
 //BA.debugLineNum = 9895950;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimelin";
mostCurrent._svtimeline.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) (_h*_rowh),mostCurrent._svtimeline.getWidth(),_rowh);
RDebugUtils.currentLine=9895952;
 //BA.debugLineNum = 9895952;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=9895953;
 //BA.debugLineNum = 9895953;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=9895954;
 //BA.debugLineNum = 9895954;BA.debugLine="lbl.Text = GetTimeString(h)";
_lbl.setText(BA.ObjectToCharSequence(_gettimestring(_h)));
RDebugUtils.currentLine=9895955;
 //BA.debugLineNum = 9895955;BA.debugLine="lbl.TextColor = Colors.DarkGray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=9895956;
 //BA.debugLineNum = 9895956;BA.debugLine="lbl.Gravity = Gravity.left";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=9895957;
 //BA.debugLineNum = 9895957;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
_p.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),_rowh);
 }
};
 }else {
RDebugUtils.currentLine=9895960;
 //BA.debugLineNum = 9895960;BA.debugLine="For h = 0 To 23";
{
final int step18 = 1;
final int limit18 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit18 ;_h = _h + step18 ) {
RDebugUtils.currentLine=9895961;
 //BA.debugLineNum = 9895961;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=9895962;
 //BA.debugLineNum = 9895962;BA.debugLine="p.Initialize(\"hour\")";
_p.Initialize(mostCurrent.activityBA,"hour");
RDebugUtils.currentLine=9895963;
 //BA.debugLineNum = 9895963;BA.debugLine="p.Tag = h";
_p.setTag((Object)(_h));
RDebugUtils.currentLine=9895964;
 //BA.debugLineNum = 9895964;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimeli";
mostCurrent._svtimeline.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) (_h*_rowh),mostCurrent._svtimeline.getWidth(),_rowh);
RDebugUtils.currentLine=9895966;
 //BA.debugLineNum = 9895966;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=9895967;
 //BA.debugLineNum = 9895967;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=9895968;
 //BA.debugLineNum = 9895968;BA.debugLine="lbl.Text = GetTimeString(h)";
_lbl.setText(BA.ObjectToCharSequence(_gettimestring(_h)));
RDebugUtils.currentLine=9895969;
 //BA.debugLineNum = 9895969;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=9895970;
 //BA.debugLineNum = 9895970;BA.debugLine="lbl.Gravity = Gravity.left";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=9895971;
 //BA.debugLineNum = 9895971;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
_p.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),_rowh);
 }
};
 };
RDebugUtils.currentLine=9895976;
 //BA.debugLineNum = 9895976;BA.debugLine="End Sub";
return "";
}
public static String  _gettimestring(int _h) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "gettimestring", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "gettimestring", new Object[] {_h}));}
int _num = 0;
String _ampm = "";
RDebugUtils.currentLine=9961472;
 //BA.debugLineNum = 9961472;BA.debugLine="Sub GetTimeString (h As Int) As String";
RDebugUtils.currentLine=9961473;
 //BA.debugLineNum = 9961473;BA.debugLine="Dim num As Int";
_num = 0;
RDebugUtils.currentLine=9961474;
 //BA.debugLineNum = 9961474;BA.debugLine="Dim ampm As String";
_ampm = "";
RDebugUtils.currentLine=9961475;
 //BA.debugLineNum = 9961475;BA.debugLine="If h = 0 Then";
if (_h==0) { 
RDebugUtils.currentLine=9961476;
 //BA.debugLineNum = 9961476;BA.debugLine="num = 12";
_num = (int) (12);
RDebugUtils.currentLine=9961477;
 //BA.debugLineNum = 9961477;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else 
{RDebugUtils.currentLine=9961478;
 //BA.debugLineNum = 9961478;BA.debugLine="Else if h = 12 Then";
if (_h==12) { 
RDebugUtils.currentLine=9961479;
 //BA.debugLineNum = 9961479;BA.debugLine="num = h";
_num = _h;
RDebugUtils.currentLine=9961480;
 //BA.debugLineNum = 9961480;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 }else 
{RDebugUtils.currentLine=9961481;
 //BA.debugLineNum = 9961481;BA.debugLine="Else if h > 12 Then";
if (_h>12) { 
RDebugUtils.currentLine=9961482;
 //BA.debugLineNum = 9961482;BA.debugLine="num = h - 12";
_num = (int) (_h-12);
RDebugUtils.currentLine=9961483;
 //BA.debugLineNum = 9961483;BA.debugLine="If num = 12 Then";
if (_num==12) { 
RDebugUtils.currentLine=9961484;
 //BA.debugLineNum = 9961484;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else {
RDebugUtils.currentLine=9961486;
 //BA.debugLineNum = 9961486;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 };
 }else {
RDebugUtils.currentLine=9961490;
 //BA.debugLineNum = 9961490;BA.debugLine="num = h";
_num = _h;
RDebugUtils.currentLine=9961491;
 //BA.debugLineNum = 9961491;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }}}
;
RDebugUtils.currentLine=9961494;
 //BA.debugLineNum = 9961494;BA.debugLine="Return num & \":00\" & ampm";
if (true) return BA.NumberToString(_num)+":00"+_ampm;
RDebugUtils.currentLine=9961495;
 //BA.debugLineNum = 9961495;BA.debugLine="End Sub";
return "";
}
public static int  _identifycolor(String _typeofevent) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "identifycolor", false))
	 {return ((Integer) Debug.delegate(mostCurrent.activityBA, "identifycolor", new Object[] {_typeofevent}));}
int _mycolor = 0;
RDebugUtils.currentLine=9764864;
 //BA.debugLineNum = 9764864;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
RDebugUtils.currentLine=9764865;
 //BA.debugLineNum = 9764865;BA.debugLine="Dim mycolor As Int";
_mycolor = 0;
RDebugUtils.currentLine=9764866;
 //BA.debugLineNum = 9764866;BA.debugLine="If typeofevent = \"Task\" Then";
if ((_typeofevent).equals("Task")) { 
RDebugUtils.currentLine=9764867;
 //BA.debugLineNum = 9764867;BA.debugLine="mycolor = Colors.ARGB(255, 0, 191, 255)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (0),(int) (191),(int) (255));
 }else 
{RDebugUtils.currentLine=9764868;
 //BA.debugLineNum = 9764868;BA.debugLine="Else if typeofevent = \"Event\" Then";
if ((_typeofevent).equals("Event")) { 
RDebugUtils.currentLine=9764869;
 //BA.debugLineNum = 9764869;BA.debugLine="mycolor = Colors.ARGB(255, 152, 255, 152)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (152),(int) (255),(int) (152));
 }else 
{RDebugUtils.currentLine=9764870;
 //BA.debugLineNum = 9764870;BA.debugLine="Else if typeofevent = \"Birthday\" Then";
if ((_typeofevent).equals("Birthday")) { 
RDebugUtils.currentLine=9764871;
 //BA.debugLineNum = 9764871;BA.debugLine="mycolor = Colors.ARGB(255, 255, 182, 193)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (182),(int) (193));
 }else 
{RDebugUtils.currentLine=9764872;
 //BA.debugLineNum = 9764872;BA.debugLine="Else if typeofevent = \"OOO\" Then";
if ((_typeofevent).equals("OOO")) { 
RDebugUtils.currentLine=9764873;
 //BA.debugLineNum = 9764873;BA.debugLine="mycolor = Colors.ARGB(255, 255, 215, 0)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (215),(int) (0));
 }}}}
;
RDebugUtils.currentLine=9764875;
 //BA.debugLineNum = 9764875;BA.debugLine="Return mycolor";
if (true) return _mycolor;
RDebugUtils.currentLine=9764876;
 //BA.debugLineNum = 9764876;BA.debugLine="End Sub";
return 0;
}
public static String  _drawtimelineevents() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "drawtimelineevents", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "drawtimelineevents", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
int _rowh = 0;
int _h = 0;
anywheresoftware.b4a.objects.PanelWrapper _hourpanel = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
int _starth = 0;
int _endh = 0;
String _title = "";
int _color = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
RDebugUtils.currentLine=10027008;
 //BA.debugLineNum = 10027008;BA.debugLine="Sub DrawTimelineEvents";
RDebugUtils.currentLine=10027009;
 //BA.debugLineNum = 10027009;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate)))) { 
if (true) return "";};
RDebugUtils.currentLine=10027011;
 //BA.debugLineNum = 10027011;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=10027012;
 //BA.debugLineNum = 10027012;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=10027014;
 //BA.debugLineNum = 10027014;BA.debugLine="Dim rowh As Int = 60dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=10027016;
 //BA.debugLineNum = 10027016;BA.debugLine="For h = 0 To 23";
{
final int step5 = 1;
final int limit5 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit5 ;_h = _h + step5 ) {
RDebugUtils.currentLine=10027017;
 //BA.debugLineNum = 10027017;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetVie";
_hourpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_hourpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._svtimeline.getPanel().GetView(_h).getObject()));
RDebugUtils.currentLine=10027019;
 //BA.debugLineNum = 10027019;BA.debugLine="If hourPanel.NumberOfViews > 1 Then";
if (_hourpanel.getNumberOfViews()>1) { 
RDebugUtils.currentLine=10027020;
 //BA.debugLineNum = 10027020;BA.debugLine="hourPanel.RemoveViewAt(1)";
_hourpanel.RemoveViewAt((int) (1));
 };
 }
};
RDebugUtils.currentLine=10027024;
 //BA.debugLineNum = 10027024;BA.debugLine="For Each ev  As Map In timeline";
_ev = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group11 = _timeline;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group11.Get(index11)));
RDebugUtils.currentLine=10027025;
 //BA.debugLineNum = 10027025;BA.debugLine="Dim starth As Int = ev.Get(\"Start\")";
_starth = (int)(BA.ObjectToNumber(_ev.Get((Object)("Start"))));
RDebugUtils.currentLine=10027026;
 //BA.debugLineNum = 10027026;BA.debugLine="Dim endh As Int = ev.Get(\"End\")";
_endh = (int)(BA.ObjectToNumber(_ev.Get((Object)("End"))));
RDebugUtils.currentLine=10027027;
 //BA.debugLineNum = 10027027;BA.debugLine="Dim title As String = ev.Get(\"Title\")";
_title = BA.ObjectToString(_ev.Get((Object)("Title")));
RDebugUtils.currentLine=10027028;
 //BA.debugLineNum = 10027028;BA.debugLine="Dim color As Int = IdentifyColor(ev.Get(\"Tags\"))";
_color = _identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags"))));
RDebugUtils.currentLine=10027029;
 //BA.debugLineNum = 10027029;BA.debugLine="For h = starth To endh - 1";
{
final int step16 = 1;
final int limit16 = (int) (_endh-1);
_h = _starth ;
for (;_h <= limit16 ;_h = _h + step16 ) {
RDebugUtils.currentLine=10027030;
 //BA.debugLineNum = 10027030;BA.debugLine="If h >= 0 And h <= 23 Then";
if (_h>=0 && _h<=23) { 
RDebugUtils.currentLine=10027031;
 //BA.debugLineNum = 10027031;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetV";
_hourpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_hourpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._svtimeline.getPanel().GetView(_h).getObject()));
RDebugUtils.currentLine=10027032;
 //BA.debugLineNum = 10027032;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=10027033;
 //BA.debugLineNum = 10027033;BA.debugLine="lbl.initialize(\"TimelineEvent\")";
_lbl.Initialize(mostCurrent.activityBA,"TimelineEvent");
RDebugUtils.currentLine=10027034;
 //BA.debugLineNum = 10027034;BA.debugLine="lbl.Tag = ev";
_lbl.setTag((Object)(_ev.getObject()));
RDebugUtils.currentLine=10027035;
 //BA.debugLineNum = 10027035;BA.debugLine="lbl.Text = title";
_lbl.setText(BA.ObjectToCharSequence(_title));
RDebugUtils.currentLine=10027036;
 //BA.debugLineNum = 10027036;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=10027037;
 //BA.debugLineNum = 10027037;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=10027038;
 //BA.debugLineNum = 10027038;BA.debugLine="lbl.Color = color";
_lbl.setColor(_color);
RDebugUtils.currentLine=10027039;
 //BA.debugLineNum = 10027039;BA.debugLine="hourPanel.AddView(lbl, 65dip, 0, hourPanel.Wid";
_hourpanel.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),(int) (0),(int) (_hourpanel.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70))),_rowh);
 };
 }
};
 }
};
RDebugUtils.currentLine=10027044;
 //BA.debugLineNum = 10027044;BA.debugLine="End Sub";
return "";
}
public static String  _editeventinfo_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "editeventinfo_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "editeventinfo_btn_click", null));}
RDebugUtils.currentLine=11141120;
 //BA.debugLineNum = 11141120;BA.debugLine="Private Sub editeventinfo_btn_Click";
RDebugUtils.currentLine=11141121;
 //BA.debugLineNum = 11141121;BA.debugLine="EditInfoPanel.Visible = True";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=11141122;
 //BA.debugLineNum = 11141122;BA.debugLine="editTitle_et.text = currenttaggedEvent.Get(\"Title";
mostCurrent._edittitle_et.setText(BA.ObjectToCharSequence(mostCurrent._currenttaggedevent.Get((Object)("Title"))));
RDebugUtils.currentLine=11141123;
 //BA.debugLineNum = 11141123;BA.debugLine="editDescription_et.Text = currenttaggedEvent.Get(";
mostCurrent._editdescription_et.setText(BA.ObjectToCharSequence(mostCurrent._currenttaggedevent.Get((Object)("Description"))));
RDebugUtils.currentLine=11141124;
 //BA.debugLineNum = 11141124;BA.debugLine="End Sub";
return "";
}
public static String  _eventrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "eventrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "eventrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=11993088;
 //BA.debugLineNum = 11993088;BA.debugLine="Private Sub eventrb_CheckedChange(Checked As Boole";
RDebugUtils.currentLine=11993089;
 //BA.debugLineNum = 11993089;BA.debugLine="eventtype = \"Event\"";
mostCurrent._eventtype = "Event";
RDebugUtils.currentLine=11993090;
 //BA.debugLineNum = 11993090;BA.debugLine="End Sub";
return "";
}
public static String  _hour_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "hour_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "hour_click", null));}
anywheresoftware.b4a.objects.PanelWrapper _p = null;
String _tappedindex = "";
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
RDebugUtils.currentLine=10158080;
 //BA.debugLineNum = 10158080;BA.debugLine="Sub hour_click";
RDebugUtils.currentLine=10158081;
 //BA.debugLineNum = 10158081;BA.debugLine="Dim p As Panel =  Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=10158082;
 //BA.debugLineNum = 10158082;BA.debugLine="Dim tappedIndex = p.tag";
_tappedindex = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=10158083;
 //BA.debugLineNum = 10158083;BA.debugLine="timeIndex = p.tag";
_timeindex = (int)(BA.ObjectToNumber(_p.getTag()));
RDebugUtils.currentLine=10158085;
 //BA.debugLineNum = 10158085;BA.debugLine="addEventTL_panel.Visible  =True";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=10158087;
 //BA.debugLineNum = 10158087;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=10158088;
 //BA.debugLineNum = 10158088;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate))) { 
RDebugUtils.currentLine=10158089;
 //BA.debugLineNum = 10158089;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 }else {
RDebugUtils.currentLine=10158091;
 //BA.debugLineNum = 10158091;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
RDebugUtils.currentLine=10158093;
 //BA.debugLineNum = 10158093;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=10158094;
 //BA.debugLineNum = 10158094;BA.debugLine="If eventmap.containskey(\"Timeline\") Then";
if (_eventmap.ContainsKey((Object)("Timeline"))) { 
RDebugUtils.currentLine=10158095;
 //BA.debugLineNum = 10158095;BA.debugLine="timeline = eventmap.Get(\"Timeline\")";
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
 }else {
RDebugUtils.currentLine=10158097;
 //BA.debugLineNum = 10158097;BA.debugLine="timeline.Initialize";
_timeline.Initialize();
RDebugUtils.currentLine=10158098;
 //BA.debugLineNum = 10158098;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
 };
RDebugUtils.currentLine=10158101;
 //BA.debugLineNum = 10158101;BA.debugLine="starttimelineSP.SelectedIndex = timeIndex";
mostCurrent._starttimelinesp.setSelectedIndex(_timeindex);
RDebugUtils.currentLine=10158102;
 //BA.debugLineNum = 10158102;BA.debugLine="endtimelineSP.SelectedIndex = Min(timeIndex +1, 2";
mostCurrent._endtimelinesp.setSelectedIndex((int) (anywheresoftware.b4a.keywords.Common.Min(_timeindex+1,24)));
RDebugUtils.currentLine=10158103;
 //BA.debugLineNum = 10158103;BA.debugLine="eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=10158107;
 //BA.debugLineNum = 10158107;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _mapinitializer() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mapinitializer", false))
	 {return ((anywheresoftware.b4a.objects.collections.Map) Debug.delegate(mostCurrent.activityBA, "mapinitializer", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
RDebugUtils.currentLine=10223616;
 //BA.debugLineNum = 10223616;BA.debugLine="Sub MapInitializer As Map";
RDebugUtils.currentLine=10223617;
 //BA.debugLineNum = 10223617;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=10223619;
 //BA.debugLineNum = 10223619;BA.debugLine="eventmap.Initialize";
_eventmap.Initialize();
RDebugUtils.currentLine=10223620;
 //BA.debugLineNum = 10223620;BA.debugLine="Dim allevents As List";
_allevents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=10223621;
 //BA.debugLineNum = 10223621;BA.debugLine="allevents.initialize";
_allevents.Initialize();
RDebugUtils.currentLine=10223623;
 //BA.debugLineNum = 10223623;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=10223624;
 //BA.debugLineNum = 10223624;BA.debugLine="timeline.initialize";
_timeline.Initialize();
RDebugUtils.currentLine=10223626;
 //BA.debugLineNum = 10223626;BA.debugLine="eventmap.Put(\"AllEvents\", allevents)";
_eventmap.Put((Object)("AllEvents"),(Object)(_allevents.getObject()));
RDebugUtils.currentLine=10223627;
 //BA.debugLineNum = 10223627;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
RDebugUtils.currentLine=10223629;
 //BA.debugLineNum = 10223629;BA.debugLine="CalendarActivity.CalendarMap.Put(currentDate, eve";
mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)(_currentdate),(Object)(_eventmap.getObject()));
RDebugUtils.currentLine=10223631;
 //BA.debugLineNum = 10223631;BA.debugLine="Return eventmap";
if (true) return _eventmap;
RDebugUtils.currentLine=10223632;
 //BA.debugLineNum = 10223632;BA.debugLine="End Sub";
return null;
}
public static String  _mainevent_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mainevent_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mainevent_click", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
RDebugUtils.currentLine=9699328;
 //BA.debugLineNum = 9699328;BA.debugLine="Sub mainEvent_click";
RDebugUtils.currentLine=9699329;
 //BA.debugLineNum = 9699329;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)((_currentdate)))));
RDebugUtils.currentLine=9699330;
 //BA.debugLineNum = 9699330;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=9699331;
 //BA.debugLineNum = 9699331;BA.debugLine="Dim lbl As Label = Sender";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=9699332;
 //BA.debugLineNum = 9699332;BA.debugLine="Dim ev As Map = allevents.get(lbl.Tag)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_allevents.Get((int)(BA.ObjectToNumber(_lbl.getTag())))));
RDebugUtils.currentLine=9699333;
 //BA.debugLineNum = 9699333;BA.debugLine="currenttaggedEvent = ev";
mostCurrent._currenttaggedevent = _ev;
RDebugUtils.currentLine=9699334;
 //BA.debugLineNum = 9699334;BA.debugLine="currentIndex = lbl.tag";
_currentindex = (int)(BA.ObjectToNumber(_lbl.getTag()));
RDebugUtils.currentLine=9699336;
 //BA.debugLineNum = 9699336;BA.debugLine="eventInfo_panel.Visible = True";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=9699337;
 //BA.debugLineNum = 9699337;BA.debugLine="dateToday_lbl.Text = currentDate";
mostCurrent._datetoday_lbl.setText(BA.ObjectToCharSequence(_currentdate));
RDebugUtils.currentLine=9699338;
 //BA.debugLineNum = 9699338;BA.debugLine="eventTitle_lbl.Text = ev.get(\"Title\")";
mostCurrent._eventtitle_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=9699339;
 //BA.debugLineNum = 9699339;BA.debugLine="eventdescription_lbl.Text = ev.Get(\"Description\")";
mostCurrent._eventdescription_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Description"))));
RDebugUtils.currentLine=9699340;
 //BA.debugLineNum = 9699340;BA.debugLine="tags_lbl.text = ev.Get(\"Tags\")";
mostCurrent._tags_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Tags"))));
RDebugUtils.currentLine=9699342;
 //BA.debugLineNum = 9699342;BA.debugLine="End Sub";
return "";
}
public static String  _menu_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "menu_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "menu_btn_click", null));}
RDebugUtils.currentLine=10551296;
 //BA.debugLineNum = 10551296;BA.debugLine="Private Sub menu_btn_Click";
RDebugUtils.currentLine=10551297;
 //BA.debugLineNum = 10551297;BA.debugLine="menupanel.Visible =True";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=10551298;
 //BA.debugLineNum = 10551298;BA.debugLine="End Sub";
return "";
}
public static String  _month_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "month_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "month_btn_click", null));}
RDebugUtils.currentLine=10747904;
 //BA.debugLineNum = 10747904;BA.debugLine="Private Sub Month_btn_Click";
RDebugUtils.currentLine=10747905;
 //BA.debugLineNum = 10747905;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=10747906;
 //BA.debugLineNum = 10747906;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
RDebugUtils.currentLine=10747907;
 //BA.debugLineNum = 10747907;BA.debugLine="End Sub";
return "";
}
public static String  _ooo_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ooo_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ooo_btn_click", null));}
RDebugUtils.currentLine=11075584;
 //BA.debugLineNum = 11075584;BA.debugLine="Private Sub ooo_btn_Click";
RDebugUtils.currentLine=11075585;
 //BA.debugLineNum = 11075585;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11075586;
 //BA.debugLineNum = 11075586;BA.debugLine="add_events_module.eventtype = \"OOO\"";
mostCurrent._add_events_module._eventtype /*String*/  = "OOO";
RDebugUtils.currentLine=11075587;
 //BA.debugLineNum = 11075587;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=11075588;
 //BA.debugLineNum = 11075588;BA.debugLine="End Sub";
return "";
}
public static String  _ooorb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ooorb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ooorb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=11862016;
 //BA.debugLineNum = 11862016;BA.debugLine="Private Sub ooorb_CheckedChange(Checked As Boolean";
RDebugUtils.currentLine=11862017;
 //BA.debugLineNum = 11862017;BA.debugLine="eventtype = \"OOO\"";
mostCurrent._eventtype = "OOO";
RDebugUtils.currentLine=11862018;
 //BA.debugLineNum = 11862018;BA.debugLine="End Sub";
return "";
}
public static String  _saveedit_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "saveedit_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "saveedit_btn_click", null));}
RDebugUtils.currentLine=11403264;
 //BA.debugLineNum = 11403264;BA.debugLine="Private Sub saveEdit_btn_Click";
RDebugUtils.currentLine=11403265;
 //BA.debugLineNum = 11403265;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
if (mostCurrent._starttimelinesp.getSelectedIndex()==mostCurrent._endtimelinesp.getSelectedIndex() || mostCurrent._starttimelinesp.getSelectedIndex()>mostCurrent._endtimelinesp.getSelectedIndex()) { 
RDebugUtils.currentLine=11403266;
 //BA.debugLineNum = 11403266;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Invalid Timeline"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=11403267;
 //BA.debugLineNum = 11403267;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=11403270;
 //BA.debugLineNum = 11403270;BA.debugLine="If editTitle_et.text = \"\" Then";
if ((mostCurrent._edittitle_et.getText()).equals("")) { 
RDebugUtils.currentLine=11403271;
 //BA.debugLineNum = 11403271;BA.debugLine="MsgboxAsync(\"Event must have name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event must have name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=11403272;
 //BA.debugLineNum = 11403272;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=11403274;
 //BA.debugLineNum = 11403274;BA.debugLine="currenttaggedEvent.Put(\"Title\", editTitle_et.Text";
mostCurrent._currenttaggedevent.Put((Object)("Title"),(Object)(mostCurrent._edittitle_et.getText()));
RDebugUtils.currentLine=11403275;
 //BA.debugLineNum = 11403275;BA.debugLine="currenttaggedEvent.Put(\"Description\", editDescrip";
mostCurrent._currenttaggedevent.Put((Object)("Description"),(Object)(mostCurrent._editdescription_et.getText()));
RDebugUtils.currentLine=11403277;
 //BA.debugLineNum = 11403277;BA.debugLine="x_EventInfo_btn_Click";
_x_eventinfo_btn_click();
RDebugUtils.currentLine=11403278;
 //BA.debugLineNum = 11403278;BA.debugLine="eventTitle_lbl.text = editTitle_et.Text";
mostCurrent._eventtitle_lbl.setText(BA.ObjectToCharSequence(mostCurrent._edittitle_et.getText()));
RDebugUtils.currentLine=11403279;
 //BA.debugLineNum = 11403279;BA.debugLine="eventdescription_lbl.Text = editDescription_et.te";
mostCurrent._eventdescription_lbl.setText(BA.ObjectToCharSequence(mostCurrent._editdescription_et.getText()));
RDebugUtils.currentLine=11403280;
 //BA.debugLineNum = 11403280;BA.debugLine="eventInfo_panel.Visible = True";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=11403281;
 //BA.debugLineNum = 11403281;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=11403282;
 //BA.debugLineNum = 11403282;BA.debugLine="DrawMainEvents";
_drawmainevents();
RDebugUtils.currentLine=11403285;
 //BA.debugLineNum = 11403285;BA.debugLine="End Sub";
return "";
}
public static String  _x_eventinfo_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "x_eventinfo_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "x_eventinfo_btn_click", null));}
RDebugUtils.currentLine=11206656;
 //BA.debugLineNum = 11206656;BA.debugLine="Private Sub x_EventInfo_btn_Click";
RDebugUtils.currentLine=11206657;
 //BA.debugLineNum = 11206657;BA.debugLine="eventInfo_panel.Visible = False";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11206658;
 //BA.debugLineNum = 11206658;BA.debugLine="EditInfoPanel.visible = False";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11206659;
 //BA.debugLineNum = 11206659;BA.debugLine="End Sub";
return "";
}
public static String  _savetl_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savetl_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savetl_btn_click", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _existing = null;
int _st = 0;
int _en = 0;
RDebugUtils.currentLine=10289152;
 //BA.debugLineNum = 10289152;BA.debugLine="Private Sub saveTL_btn_Click";
RDebugUtils.currentLine=10289153;
 //BA.debugLineNum = 10289153;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
if (mostCurrent._starttimelinesp.getSelectedIndex()==mostCurrent._endtimelinesp.getSelectedIndex() || mostCurrent._starttimelinesp.getSelectedIndex()>mostCurrent._endtimelinesp.getSelectedIndex()) { 
RDebugUtils.currentLine=10289154;
 //BA.debugLineNum = 10289154;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Invalid Timeline"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=10289155;
 //BA.debugLineNum = 10289155;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=10289157;
 //BA.debugLineNum = 10289157;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=10289158;
 //BA.debugLineNum = 10289158;BA.debugLine="If addTL_et.text = \"\" Then";
if ((mostCurrent._addtl_et.getText()).equals("")) { 
RDebugUtils.currentLine=10289159;
 //BA.debugLineNum = 10289159;BA.debugLine="MsgboxAsync(\"Event must have a name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event must have a name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=10289160;
 //BA.debugLineNum = 10289160;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=10289163;
 //BA.debugLineNum = 10289163;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate))) { 
RDebugUtils.currentLine=10289164;
 //BA.debugLineNum = 10289164;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 }else {
RDebugUtils.currentLine=10289166;
 //BA.debugLineNum = 10289166;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
RDebugUtils.currentLine=10289168;
 //BA.debugLineNum = 10289168;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=10289169;
 //BA.debugLineNum = 10289169;BA.debugLine="Dim ev As Map";
_ev = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=10289170;
 //BA.debugLineNum = 10289170;BA.debugLine="ev.Initialize";
_ev.Initialize();
RDebugUtils.currentLine=10289172;
 //BA.debugLineNum = 10289172;BA.debugLine="ev.Put(\"ID\", DateTime.Now)";
_ev.Put((Object)("ID"),(Object)(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
RDebugUtils.currentLine=10289173;
 //BA.debugLineNum = 10289173;BA.debugLine="ev.Put(\"Title\", addTL_et.Text)";
_ev.Put((Object)("Title"),(Object)(mostCurrent._addtl_et.getText()));
RDebugUtils.currentLine=10289174;
 //BA.debugLineNum = 10289174;BA.debugLine="ev.Put(\"Start\", starttimelineSP.SelectedIndex)";
_ev.Put((Object)("Start"),(Object)(mostCurrent._starttimelinesp.getSelectedIndex()));
RDebugUtils.currentLine=10289175;
 //BA.debugLineNum = 10289175;BA.debugLine="ev.Put(\"End\", endtimelineSP.SelectedIndex)";
_ev.Put((Object)("End"),(Object)(mostCurrent._endtimelinesp.getSelectedIndex()));
RDebugUtils.currentLine=10289176;
 //BA.debugLineNum = 10289176;BA.debugLine="ev.Put(\"Tags\", eventtype)";
_ev.Put((Object)("Tags"),(Object)(mostCurrent._eventtype));
RDebugUtils.currentLine=10289179;
 //BA.debugLineNum = 10289179;BA.debugLine="For i = timeline.Size - 1 To 0 Step -1";
{
final int step23 = -1;
final int limit23 = (int) (0);
_i = (int) (_timeline.getSize()-1) ;
for (;_i >= limit23 ;_i = _i + step23 ) {
RDebugUtils.currentLine=10289180;
 //BA.debugLineNum = 10289180;BA.debugLine="Dim existing As Map = timeline.Get(i)";
_existing = new anywheresoftware.b4a.objects.collections.Map();
_existing = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_timeline.Get(_i)));
RDebugUtils.currentLine=10289181;
 //BA.debugLineNum = 10289181;BA.debugLine="Dim st As Int = existing.Get(\"Start\")";
_st = (int)(BA.ObjectToNumber(_existing.Get((Object)("Start"))));
RDebugUtils.currentLine=10289182;
 //BA.debugLineNum = 10289182;BA.debugLine="Dim en As Int = existing.Get(\"End\")";
_en = (int)(BA.ObjectToNumber(_existing.Get((Object)("End"))));
RDebugUtils.currentLine=10289185;
 //BA.debugLineNum = 10289185;BA.debugLine="If (starttimelineSP.SelectedIndex < en) And (end";
if ((mostCurrent._starttimelinesp.getSelectedIndex()<_en) && (mostCurrent._endtimelinesp.getSelectedIndex()>_st)) { 
RDebugUtils.currentLine=10289186;
 //BA.debugLineNum = 10289186;BA.debugLine="timeline.RemoveAt(i)";
_timeline.RemoveAt(_i);
RDebugUtils.currentLine=10289187;
 //BA.debugLineNum = 10289187;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=10289191;
 //BA.debugLineNum = 10289191;BA.debugLine="timeline.add(ev)";
_timeline.Add((Object)(_ev.getObject()));
RDebugUtils.currentLine=10289193;
 //BA.debugLineNum = 10289193;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=10289194;
 //BA.debugLineNum = 10289194;BA.debugLine="UpdateTimeLine";
_updatetimeline();
RDebugUtils.currentLine=10289195;
 //BA.debugLineNum = 10289195;BA.debugLine="addEventTL_panel.Visible  = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10289197;
 //BA.debugLineNum = 10289197;BA.debugLine="End Sub";
return "";
}
public static String  _sched_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sched_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sched_btn_click", null));}
RDebugUtils.currentLine=10616832;
 //BA.debugLineNum = 10616832;BA.debugLine="Private Sub sched_btn_Click";
RDebugUtils.currentLine=10616833;
 //BA.debugLineNum = 10616833;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=10616834;
 //BA.debugLineNum = 10616834;BA.debugLine="StartActivity(Schedule_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._schedule_module.getObject()));
RDebugUtils.currentLine=10616835;
 //BA.debugLineNum = 10616835;BA.debugLine="End Sub";
return "";
}
public static String  _taskrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=12058624;
 //BA.debugLineNum = 12058624;BA.debugLine="Private Sub taskrb_CheckedChange(Checked As Boolea";
RDebugUtils.currentLine=12058625;
 //BA.debugLineNum = 12058625;BA.debugLine="eventtype = \"Task\"";
mostCurrent._eventtype = "Task";
RDebugUtils.currentLine=12058626;
 //BA.debugLineNum = 12058626;BA.debugLine="End Sub";
return "";
}
public static String  _timelineevent_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timelineevent_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timelineevent_click", null));}
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
RDebugUtils.currentLine=10092544;
 //BA.debugLineNum = 10092544;BA.debugLine="Sub timelineEvent_Click";
RDebugUtils.currentLine=10092545;
 //BA.debugLineNum = 10092545;BA.debugLine="Dim lbl As Label = Sender";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=10092546;
 //BA.debugLineNum = 10092546;BA.debugLine="Dim ev As Map = lbl.Tag";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_lbl.getTag()));
RDebugUtils.currentLine=10092547;
 //BA.debugLineNum = 10092547;BA.debugLine="currenttaggedEvent = ev";
mostCurrent._currenttaggedevent = _ev;
RDebugUtils.currentLine=10092548;
 //BA.debugLineNum = 10092548;BA.debugLine="currentevId = ev.Get(\"ID\")";
_currentevid = BA.ObjectToLongNumber(_ev.Get((Object)("ID")));
RDebugUtils.currentLine=10092551;
 //BA.debugLineNum = 10092551;BA.debugLine="addEventTL_panel.Visible = True";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=10092554;
 //BA.debugLineNum = 10092554;BA.debugLine="addTL_et.Text = ev.Get(\"Title\")";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=10092555;
 //BA.debugLineNum = 10092555;BA.debugLine="starttimelineSP.SelectedIndex = ev.Get(\"Start\")";
mostCurrent._starttimelinesp.setSelectedIndex((int)(BA.ObjectToNumber(_ev.Get((Object)("Start")))));
RDebugUtils.currentLine=10092556;
 //BA.debugLineNum = 10092556;BA.debugLine="endtimelineSP.SelectedIndex = ev.Get(\"End\")";
mostCurrent._endtimelinesp.setSelectedIndex((int)(BA.ObjectToNumber(_ev.Get((Object)("End")))));
RDebugUtils.currentLine=10092559;
 //BA.debugLineNum = 10092559;BA.debugLine="Select Case ev.Get(\"Tags\")";
switch (BA.switchObjectToInt(_ev.Get((Object)("Tags")),(Object)("Task"),(Object)("Event"),(Object)("Birthday"),(Object)("OOO"))) {
case 0: {
RDebugUtils.currentLine=10092560;
 //BA.debugLineNum = 10092560;BA.debugLine="Case \"Task\": taskrb.Checked = True";
mostCurrent._taskrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 1: {
RDebugUtils.currentLine=10092561;
 //BA.debugLineNum = 10092561;BA.debugLine="Case \"Event\": eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 2: {
RDebugUtils.currentLine=10092562;
 //BA.debugLineNum = 10092562;BA.debugLine="Case \"Birthday\": birthdayrb.Checked = True";
mostCurrent._birthdayrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 3: {
RDebugUtils.currentLine=10092563;
 //BA.debugLineNum = 10092563;BA.debugLine="Case \"OOO\": ooorb.Checked = True";
mostCurrent._ooorb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
RDebugUtils.currentLine=10092565;
 //BA.debugLineNum = 10092565;BA.debugLine="End Sub";
return "";
}
public static String  _x_tlevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "x_tlevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "x_tlevent_btn_click", null));}
RDebugUtils.currentLine=11599872;
 //BA.debugLineNum = 11599872;BA.debugLine="Private Sub x_TLevent_btn_Click";
RDebugUtils.currentLine=11599873;
 //BA.debugLineNum = 11599873;BA.debugLine="addEventTL_panel.Visible = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11599874;
 //BA.debugLineNum = 11599874;BA.debugLine="addTL_et.text = \"\"";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=11599875;
 //BA.debugLineNum = 11599875;BA.debugLine="End Sub";
return "";
}
}