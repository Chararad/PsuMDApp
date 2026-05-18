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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.day_module");
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
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
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
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 53;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 55;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 56;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 57;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 59;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 60;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="cd.Initialize(Colors.ARGB(125, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (125),(int) (98),(int) (43),(int) (20)),(int) (200));
 //BA.debugLineNum = 62;BA.debugLine="Day_btn.Background = cd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 63;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 65;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark",mostCurrent.activityBA);
 //BA.debugLineNum = 66;BA.debugLine="cd.Initialize(Colors.ARGB(125, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (125),(int) (59),(int) (117),(int) (151)),(int) (200));
 //BA.debugLineNum = 67;BA.debugLine="Day_btn.Background = cd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
 //BA.debugLineNum = 71;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 72;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout2",mostCurrent.activityBA);
 //BA.debugLineNum = 73;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Pink .png").getObject()));
 //BA.debugLineNum = 74;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 77;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark2\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark2",mostCurrent.activityBA);
 //BA.debugLineNum = 78;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"pink";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pink Skeumorphic Button DARK.png").getObject()));
 //BA.debugLineNum = 79;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 80;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
 //BA.debugLineNum = 83;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 84;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout3",mostCurrent.activityBA);
 //BA.debugLineNum = 85;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
 //BA.debugLineNum = 86;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 87;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 89;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark3\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark3",mostCurrent.activityBA);
 //BA.debugLineNum = 90;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtnd.png").getObject()));
 //BA.debugLineNum = 91;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 92;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
 //BA.debugLineNum = 96;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 97;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
mostCurrent._starttimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 98;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.Black";
mostCurrent._starttimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 99;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.W";
mostCurrent._endtimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 100;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.Black";
mostCurrent._endtimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
 //BA.debugLineNum = 102;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
mostCurrent._starttimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 103;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.White";
mostCurrent._starttimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 104;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.D";
mostCurrent._endtimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 105;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.White";
mostCurrent._endtimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 108;BA.debugLine="date_todaylbl.Text = SetDate(currentDate)";
mostCurrent._date_todaylbl.setText(BA.ObjectToCharSequence(_setdate(_currentdate)));
 //BA.debugLineNum = 109;BA.debugLine="add_events_module.currentDate = SetDate(currentDa";
mostCurrent._add_events_module._currentdate /*String*/  = _setdate(_currentdate);
 //BA.debugLineNum = 110;BA.debugLine="SetUpSpinners";
_setupspinners();
 //BA.debugLineNum = 112;BA.debugLine="Log(currentDate)";
anywheresoftware.b4a.keywords.Common.LogImpl("66488123",_currentdate,0);
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 465;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 457;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 458;BA.debugLine="UpdateTimeLine";
_updatetimeline();
 //BA.debugLineNum = 459;BA.debugLine="If addeventsfeedback = True Then";
if (_addeventsfeedback==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 460;BA.debugLine="addeventsfeedback = False";
_addeventsfeedback = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 461;BA.debugLine="MsgboxAsync(\"Event Saved\", \"Saved\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event Saved"),BA.ObjectToCharSequence("Saved"),processBA);
 };
 //BA.debugLineNum = 463;BA.debugLine="End Sub";
return "";
}
public static String  _addevent_btn_click() throws Exception{
 //BA.debugLineNum = 488;BA.debugLine="Private Sub Addevent_btn_Click";
 //BA.debugLineNum = 489;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 490;BA.debugLine="add_events_module.eventtype = \"Event\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Event";
 //BA.debugLineNum = 491;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
 //BA.debugLineNum = 492;BA.debugLine="End Sub";
return "";
}
public static String  _addnew_btn_click() throws Exception{
 //BA.debugLineNum = 494;BA.debugLine="Private Sub addnew_btn_Click";
 //BA.debugLineNum = 496;BA.debugLine="If addpanel.Visible =True Then";
if (mostCurrent._addpanel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 497;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 498;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 500;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 503;BA.debugLine="End Sub";
return "";
}
public static String  _addtask_btn_click() throws Exception{
 //BA.debugLineNum = 505;BA.debugLine="Private Sub Addtask_btn_Click";
 //BA.debugLineNum = 506;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 507;BA.debugLine="add_events_module.eventtype = \"Task\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Task";
 //BA.debugLineNum = 508;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
 //BA.debugLineNum = 509;BA.debugLine="End Sub";
return "";
}
public static String  _birthday_btn_click() throws Exception{
 //BA.debugLineNum = 511;BA.debugLine="Private Sub birthday_btn_Click";
 //BA.debugLineNum = 512;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 513;BA.debugLine="add_events_module.eventtype = \"Birthday\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Birthday";
 //BA.debugLineNum = 514;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public static String  _birthdayrb_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 616;BA.debugLine="Private Sub birthdayrb_CheckedChange(Checked As Bo";
 //BA.debugLineNum = 617;BA.debugLine="eventtype = \"Birthday\"";
mostCurrent._eventtype = "Birthday";
 //BA.debugLineNum = 618;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_btn_click() throws Exception{
 //BA.debugLineNum = 565;BA.debugLine="Private Sub cancelDelete_btn_Click";
 //BA.debugLineNum = 566;BA.debugLine="deletepanel.Visible = False";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 567;BA.debugLine="End Sub";
return "";
}
public static String  _canceledit_btn_click() throws Exception{
 //BA.debugLineNum = 538;BA.debugLine="Private Sub cancelEdit_btn_Click";
 //BA.debugLineNum = 539;BA.debugLine="EditInfoPanel.Visible = False";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 540;BA.debugLine="End Sub";
return "";
}
public static String  _canceltldelete_btn_click() throws Exception{
 //BA.debugLineNum = 588;BA.debugLine="Private Sub cancelTLdelete_btn_Click";
 //BA.debugLineNum = 589;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 590;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_btn_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
 //BA.debugLineNum = 569;BA.debugLine="Private Sub confirmdelete_btn_Click";
 //BA.debugLineNum = 570;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 //BA.debugLineNum = 571;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
 //BA.debugLineNum = 572;BA.debugLine="allevents.RemoveAt(currentIndex)";
_allevents.RemoveAt(_currentindex);
 //BA.debugLineNum = 573;BA.debugLine="deletepanel.Visible = False";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 574;BA.debugLine="eventInfo_panel.Visible = False";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 575;BA.debugLine="SaveCalendar";
_savecalendar();
 //BA.debugLineNum = 576;BA.debugLine="DrawMainEvents";
_drawmainevents();
 //BA.debugLineNum = 577;BA.debugLine="End Sub";
return "";
}
public static String  _day_btn_click() throws Exception{
 //BA.debugLineNum = 479;BA.debugLine="Private Sub Day_btn_Click";
 //BA.debugLineNum = 480;BA.debugLine="menupanel.Visible = False";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 481;BA.debugLine="End Sub";
return "";
}
public static String  _deleteevent_btn_click() throws Exception{
 //BA.debugLineNum = 534;BA.debugLine="Private Sub DeleteEvent_btn_Click";
 //BA.debugLineNum = 535;BA.debugLine="deletepanel.Visible = True";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 536;BA.debugLine="End Sub";
return "";
}
public static String  _deletetlconfirm_btn_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _ev = null;
 //BA.debugLineNum = 592;BA.debugLine="Private Sub deleteTLconfirm_btn_Click";
 //BA.debugLineNum = 593;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 //BA.debugLineNum = 594;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
 //BA.debugLineNum = 596;BA.debugLine="For i = timeline.Size -1 To 0 Step -1";
{
final int step3 = -1;
final int limit3 = (int) (0);
_i = (int) (_timeline.getSize()-1) ;
for (;_i >= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 597;BA.debugLine="Dim ev As Map = timeline.Get(i)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_timeline.Get(_i)));
 //BA.debugLineNum = 598;BA.debugLine="If ev.Get(\"ID\") = currentevId Then";
if ((_ev.Get((Object)("ID"))).equals((Object)(_currentevid))) { 
 //BA.debugLineNum = 599;BA.debugLine="timeline.RemoveAt(i)";
_timeline.RemoveAt(_i);
 //BA.debugLineNum = 600;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 604;BA.debugLine="addTL_et.Text = \"\"";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 605;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 606;BA.debugLine="addEventTL_panel.Visible = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 607;BA.debugLine="SaveCalendar";
_savecalendar();
 //BA.debugLineNum = 608;BA.debugLine="UpdateTimeLine";
_updatetimeline();
 //BA.debugLineNum = 609;BA.debugLine="End Sub";
return "";
}
public static String  _deletetlevent_btn_click() throws Exception{
 //BA.debugLineNum = 584;BA.debugLine="Private Sub deleteTLevent_btn_Click";
 //BA.debugLineNum = 585;BA.debugLine="deleteTLevent_confirmationpanel.Visible = True";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 586;BA.debugLine="End Sub";
return "";
}
public static String  _drawhourlabels() throws Exception{
int _rowh = 0;
int _h = 0;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 193;BA.debugLine="Sub DrawHourLabels";
 //BA.debugLineNum = 196;BA.debugLine="Svtimeline.Panel.RemoveAllViews";
mostCurrent._svtimeline.getPanel().RemoveAllViews();
 //BA.debugLineNum = 198;BA.debugLine="Dim rowh As Int = 60dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 //BA.debugLineNum = 199;BA.debugLine="Svtimeline.Panel.Height = 24 * rowh";
mostCurrent._svtimeline.getPanel().setHeight((int) (24*_rowh));
 //BA.debugLineNum = 202;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 203;BA.debugLine="For h = 0 To 23";
{
final int step5 = 1;
final int limit5 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit5 ;_h = _h + step5 ) {
 //BA.debugLineNum = 204;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 205;BA.debugLine="p.Initialize(\"hour\")";
_p.Initialize(mostCurrent.activityBA,"hour");
 //BA.debugLineNum = 206;BA.debugLine="p.Tag = h";
_p.setTag((Object)(_h));
 //BA.debugLineNum = 207;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimelin";
mostCurrent._svtimeline.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) (_h*_rowh),mostCurrent._svtimeline.getWidth(),_rowh);
 //BA.debugLineNum = 209;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 210;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 211;BA.debugLine="lbl.Text = GetTimeString(h)";
_lbl.setText(BA.ObjectToCharSequence(_gettimestring(_h)));
 //BA.debugLineNum = 212;BA.debugLine="lbl.TextColor = Colors.DarkGray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 213;BA.debugLine="lbl.Gravity = Gravity.left";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
 //BA.debugLineNum = 214;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
_p.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),_rowh);
 }
};
 }else {
 //BA.debugLineNum = 217;BA.debugLine="For h = 0 To 23";
{
final int step18 = 1;
final int limit18 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit18 ;_h = _h + step18 ) {
 //BA.debugLineNum = 218;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 219;BA.debugLine="p.Initialize(\"hour\")";
_p.Initialize(mostCurrent.activityBA,"hour");
 //BA.debugLineNum = 220;BA.debugLine="p.Tag = h";
_p.setTag((Object)(_h));
 //BA.debugLineNum = 221;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimeli";
mostCurrent._svtimeline.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) (_h*_rowh),mostCurrent._svtimeline.getWidth(),_rowh);
 //BA.debugLineNum = 223;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 224;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 225;BA.debugLine="lbl.Text = GetTimeString(h)";
_lbl.setText(BA.ObjectToCharSequence(_gettimestring(_h)));
 //BA.debugLineNum = 226;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 227;BA.debugLine="lbl.Gravity = Gravity.left";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
 //BA.debugLineNum = 228;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
_p.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),_rowh);
 }
};
 };
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _drawmainevents() throws Exception{
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
int _y = 0;
int _rowheight = 0;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
 //BA.debugLineNum = 130;BA.debugLine="Sub DrawMainEvents";
 //BA.debugLineNum = 131;BA.debugLine="svEvents.Panel.RemoveAllViews";
mostCurrent._svevents.getPanel().RemoveAllViews();
 //BA.debugLineNum = 132;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate)))) { 
 //BA.debugLineNum = 133;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 136;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 //BA.debugLineNum = 137;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
 //BA.debugLineNum = 139;BA.debugLine="Dim y As Int = 0";
_y = (int) (0);
 //BA.debugLineNum = 140;BA.debugLine="Dim rowHeight As Int = 50dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50));
 //BA.debugLineNum = 141;BA.debugLine="For i = 0 To allevents.Size -1";
{
final int step9 = 1;
final int limit9 = (int) (_allevents.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
 //BA.debugLineNum = 142;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 143;BA.debugLine="Dim ev As Map = allevents.Get(i)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_allevents.Get(_i)));
 //BA.debugLineNum = 144;BA.debugLine="lbl.Initialize(\"mainEvent\")";
_lbl.Initialize(mostCurrent.activityBA,"mainEvent");
 //BA.debugLineNum = 145;BA.debugLine="lbl.Tag = i";
_lbl.setTag((Object)(_i));
 //BA.debugLineNum = 146;BA.debugLine="lbl.Text = ev.Get(\"Title\")";
_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
 //BA.debugLineNum = 147;BA.debugLine="lbl.Gravity = Gravity.CENTER_vertical";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 148;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 149;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
_lbl.setColor(_identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags")))));
 //BA.debugLineNum = 151;BA.debugLine="svEvents.Panel.AddView(lbl, 0, y, svEvents.Width";
mostCurrent._svevents.getPanel().AddView((android.view.View)(_lbl.getObject()),(int) (0),_y,mostCurrent._svevents.getWidth(),_rowheight);
 //BA.debugLineNum = 152;BA.debugLine="y = y+rowHeight";
_y = (int) (_y+_rowheight);
 }
};
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _drawtimelineevents() throws Exception{
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
 //BA.debugLineNum = 260;BA.debugLine="Sub DrawTimelineEvents";
 //BA.debugLineNum = 261;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate)))) { 
if (true) return "";};
 //BA.debugLineNum = 263;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 //BA.debugLineNum = 264;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
 //BA.debugLineNum = 266;BA.debugLine="Dim rowh As Int = 60dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 //BA.debugLineNum = 268;BA.debugLine="For h = 0 To 23";
{
final int step5 = 1;
final int limit5 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit5 ;_h = _h + step5 ) {
 //BA.debugLineNum = 269;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetVie";
_hourpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_hourpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._svtimeline.getPanel().GetView(_h).getObject()));
 //BA.debugLineNum = 271;BA.debugLine="If hourPanel.NumberOfViews > 1 Then";
if (_hourpanel.getNumberOfViews()>1) { 
 //BA.debugLineNum = 272;BA.debugLine="hourPanel.RemoveViewAt(1)";
_hourpanel.RemoveViewAt((int) (1));
 };
 }
};
 //BA.debugLineNum = 276;BA.debugLine="For Each ev  As Map In timeline";
_ev = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group11 = _timeline;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group11.Get(index11)));
 //BA.debugLineNum = 277;BA.debugLine="Dim starth As Int = ev.Get(\"Start\")";
_starth = (int)(BA.ObjectToNumber(_ev.Get((Object)("Start"))));
 //BA.debugLineNum = 278;BA.debugLine="Dim endh As Int = ev.Get(\"End\")";
_endh = (int)(BA.ObjectToNumber(_ev.Get((Object)("End"))));
 //BA.debugLineNum = 279;BA.debugLine="Dim title As String = ev.Get(\"Title\")";
_title = BA.ObjectToString(_ev.Get((Object)("Title")));
 //BA.debugLineNum = 280;BA.debugLine="Dim color As Int = IdentifyColor(ev.Get(\"Tags\"))";
_color = _identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags"))));
 //BA.debugLineNum = 281;BA.debugLine="For h = starth To endh - 1";
{
final int step16 = 1;
final int limit16 = (int) (_endh-1);
_h = _starth ;
for (;_h <= limit16 ;_h = _h + step16 ) {
 //BA.debugLineNum = 282;BA.debugLine="If h >= 0 And h <= 23 Then";
if (_h>=0 && _h<=23) { 
 //BA.debugLineNum = 283;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetV";
_hourpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_hourpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._svtimeline.getPanel().GetView(_h).getObject()));
 //BA.debugLineNum = 284;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 285;BA.debugLine="lbl.initialize(\"TimelineEvent\")";
_lbl.Initialize(mostCurrent.activityBA,"TimelineEvent");
 //BA.debugLineNum = 286;BA.debugLine="lbl.Tag = ev";
_lbl.setTag((Object)(_ev.getObject()));
 //BA.debugLineNum = 287;BA.debugLine="lbl.Text = title";
_lbl.setText(BA.ObjectToCharSequence(_title));
 //BA.debugLineNum = 288;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 289;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 290;BA.debugLine="lbl.Color = color";
_lbl.setColor(_color);
 //BA.debugLineNum = 291;BA.debugLine="hourPanel.AddView(lbl, 65dip, 0, hourPanel.Wid";
_hourpanel.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),(int) (0),(int) (_hourpanel.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70))),_rowh);
 };
 }
};
 }
};
 //BA.debugLineNum = 296;BA.debugLine="End Sub";
return "";
}
public static String  _editeventinfo_btn_click() throws Exception{
 //BA.debugLineNum = 523;BA.debugLine="Private Sub editeventinfo_btn_Click";
 //BA.debugLineNum = 524;BA.debugLine="EditInfoPanel.Visible = True";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 525;BA.debugLine="editTitle_et.text = currenttaggedEvent.Get(\"Title";
mostCurrent._edittitle_et.setText(BA.ObjectToCharSequence(mostCurrent._currenttaggedevent.Get((Object)("Title"))));
 //BA.debugLineNum = 526;BA.debugLine="editDescription_et.Text = currenttaggedEvent.Get(";
mostCurrent._editdescription_et.setText(BA.ObjectToCharSequence(mostCurrent._currenttaggedevent.Get((Object)("Description"))));
 //BA.debugLineNum = 527;BA.debugLine="End Sub";
return "";
}
public static String  _eventrb_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 620;BA.debugLine="Private Sub eventrb_CheckedChange(Checked As Boole";
 //BA.debugLineNum = 621;BA.debugLine="eventtype = \"Event\"";
mostCurrent._eventtype = "Event";
 //BA.debugLineNum = 622;BA.debugLine="End Sub";
return "";
}
public static String  _gettimestring(int _h) throws Exception{
int _num = 0;
String _ampm = "";
 //BA.debugLineNum = 235;BA.debugLine="Sub GetTimeString (h As Int) As String";
 //BA.debugLineNum = 236;BA.debugLine="Dim num As Int";
_num = 0;
 //BA.debugLineNum = 237;BA.debugLine="Dim ampm As String";
_ampm = "";
 //BA.debugLineNum = 238;BA.debugLine="If h = 0 Then";
if (_h==0) { 
 //BA.debugLineNum = 239;BA.debugLine="num = 12";
_num = (int) (12);
 //BA.debugLineNum = 240;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else if(_h==12) { 
 //BA.debugLineNum = 242;BA.debugLine="num = h";
_num = _h;
 //BA.debugLineNum = 243;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 }else if(_h>12) { 
 //BA.debugLineNum = 245;BA.debugLine="num = h - 12";
_num = (int) (_h-12);
 //BA.debugLineNum = 246;BA.debugLine="If num = 12 Then";
if (_num==12) { 
 //BA.debugLineNum = 247;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else {
 //BA.debugLineNum = 249;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 };
 }else {
 //BA.debugLineNum = 253;BA.debugLine="num = h";
_num = _h;
 //BA.debugLineNum = 254;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 };
 //BA.debugLineNum = 257;BA.debugLine="Return num & \":00\" & ampm";
if (true) return BA.NumberToString(_num)+":00"+_ampm;
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private Day_btn As Button";
mostCurrent._day_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private menupanel As Panel";
mostCurrent._menupanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private Svtimeline As ScrollView";
mostCurrent._svtimeline = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private date_todaylbl As Label";
mostCurrent._date_todaylbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private addpanel As Panel";
mostCurrent._addpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Addtask_btn As Button";
mostCurrent._addtask_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private svEvents As ScrollView";
mostCurrent._svevents = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private eventdescription_lbl As Label";
mostCurrent._eventdescription_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private eventTitle_lbl As Label";
mostCurrent._eventtitle_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private tags_lbl As Label";
mostCurrent._tags_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private dateToday_lbl As Label";
mostCurrent._datetoday_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private eventInfo_panel As Panel";
mostCurrent._eventinfo_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private EditInfoPanel As Panel";
mostCurrent._editinfopanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim currenttaggedEvent As Map";
mostCurrent._currenttaggedevent = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 34;BA.debugLine="Dim timeIndex As Int";
_timeindex = 0;
 //BA.debugLineNum = 35;BA.debugLine="Dim eventtype As String";
mostCurrent._eventtype = "";
 //BA.debugLineNum = 36;BA.debugLine="Dim currentevId As Long";
_currentevid = 0L;
 //BA.debugLineNum = 38;BA.debugLine="Private editTitle_et As EditText";
mostCurrent._edittitle_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private editDescription_et As EditText";
mostCurrent._editdescription_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private deletepanel As Panel";
mostCurrent._deletepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private addTL_et As EditText";
mostCurrent._addtl_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private addEventTL_panel As Panel";
mostCurrent._addeventtl_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private timelabel As Label";
mostCurrent._timelabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private deleteTLevent_confirmationpanel As Panel";
mostCurrent._deletetlevent_confirmationpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private starttimelineSP As Spinner";
mostCurrent._starttimelinesp = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private endtimelineSP As Spinner";
mostCurrent._endtimelinesp = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private taskrb As RadioButton";
mostCurrent._taskrb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private eventrb As RadioButton";
mostCurrent._eventrb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private birthdayrb As RadioButton";
mostCurrent._birthdayrb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private ooorb As RadioButton";
mostCurrent._ooorb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _hour_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
String _tappedindex = "";
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
 //BA.debugLineNum = 321;BA.debugLine="Sub hour_click";
 //BA.debugLineNum = 322;BA.debugLine="Dim p As Panel =  Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 323;BA.debugLine="Dim tappedIndex = p.tag";
_tappedindex = BA.ObjectToString(_p.getTag());
 //BA.debugLineNum = 324;BA.debugLine="timeIndex = p.tag";
_timeindex = (int)(BA.ObjectToNumber(_p.getTag()));
 //BA.debugLineNum = 326;BA.debugLine="addEventTL_panel.Visible  =True";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 328;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 329;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate))) { 
 //BA.debugLineNum = 330;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 }else {
 //BA.debugLineNum = 332;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
 //BA.debugLineNum = 334;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 335;BA.debugLine="If eventmap.containskey(\"Timeline\") Then";
if (_eventmap.ContainsKey((Object)("Timeline"))) { 
 //BA.debugLineNum = 336;BA.debugLine="timeline = eventmap.Get(\"Timeline\")";
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
 }else {
 //BA.debugLineNum = 338;BA.debugLine="timeline.Initialize";
_timeline.Initialize();
 //BA.debugLineNum = 339;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
 };
 //BA.debugLineNum = 342;BA.debugLine="starttimelineSP.SelectedIndex = timeIndex";
mostCurrent._starttimelinesp.setSelectedIndex(_timeindex);
 //BA.debugLineNum = 343;BA.debugLine="endtimelineSP.SelectedIndex = Min(timeIndex +1, 2";
mostCurrent._endtimelinesp.setSelectedIndex((int) (anywheresoftware.b4a.keywords.Common.Min(_timeindex+1,24)));
 //BA.debugLineNum = 344;BA.debugLine="eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 348;BA.debugLine="End Sub";
return "";
}
public static int  _identifycolor(String _typeofevent) throws Exception{
int _mycolor = 0;
 //BA.debugLineNum = 173;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
 //BA.debugLineNum = 174;BA.debugLine="Dim mycolor As Int";
_mycolor = 0;
 //BA.debugLineNum = 175;BA.debugLine="If typeofevent = \"Task\" Then";
if ((_typeofevent).equals("Task")) { 
 //BA.debugLineNum = 176;BA.debugLine="mycolor = Colors.ARGB(255, 0, 191, 255)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (0),(int) (191),(int) (255));
 }else if((_typeofevent).equals("Event")) { 
 //BA.debugLineNum = 178;BA.debugLine="mycolor = Colors.ARGB(255, 152, 255, 152)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (152),(int) (255),(int) (152));
 }else if((_typeofevent).equals("Birthday")) { 
 //BA.debugLineNum = 180;BA.debugLine="mycolor = Colors.ARGB(255, 255, 182, 193)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (182),(int) (193));
 }else if((_typeofevent).equals("OOO")) { 
 //BA.debugLineNum = 182;BA.debugLine="mycolor = Colors.ARGB(255, 255, 215, 0)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (215),(int) (0));
 };
 //BA.debugLineNum = 184;BA.debugLine="Return mycolor";
if (true) return _mycolor;
 //BA.debugLineNum = 185;BA.debugLine="End Sub";
return 0;
}
public static String  _mainevent_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
 //BA.debugLineNum = 157;BA.debugLine="Sub mainEvent_click";
 //BA.debugLineNum = 158;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)((_currentdate)))));
 //BA.debugLineNum = 159;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
 //BA.debugLineNum = 160;BA.debugLine="Dim lbl As Label = Sender";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 161;BA.debugLine="Dim ev As Map = allevents.get(lbl.Tag)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_allevents.Get((int)(BA.ObjectToNumber(_lbl.getTag())))));
 //BA.debugLineNum = 162;BA.debugLine="currenttaggedEvent = ev";
mostCurrent._currenttaggedevent = _ev;
 //BA.debugLineNum = 163;BA.debugLine="currentIndex = lbl.tag";
_currentindex = (int)(BA.ObjectToNumber(_lbl.getTag()));
 //BA.debugLineNum = 165;BA.debugLine="eventInfo_panel.Visible = True";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 166;BA.debugLine="dateToday_lbl.Text = currentDate";
mostCurrent._datetoday_lbl.setText(BA.ObjectToCharSequence(_currentdate));
 //BA.debugLineNum = 167;BA.debugLine="eventTitle_lbl.Text = ev.get(\"Title\")";
mostCurrent._eventtitle_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
 //BA.debugLineNum = 168;BA.debugLine="eventdescription_lbl.Text = ev.Get(\"Description\")";
mostCurrent._eventdescription_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Description"))));
 //BA.debugLineNum = 169;BA.debugLine="tags_lbl.text = ev.Get(\"Tags\")";
mostCurrent._tags_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Tags"))));
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _mapinitializer() throws Exception{
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
 //BA.debugLineNum = 352;BA.debugLine="Sub MapInitializer As Map";
 //BA.debugLineNum = 353;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 355;BA.debugLine="eventmap.Initialize";
_eventmap.Initialize();
 //BA.debugLineNum = 356;BA.debugLine="Dim allevents As List";
_allevents = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 357;BA.debugLine="allevents.initialize";
_allevents.Initialize();
 //BA.debugLineNum = 359;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 360;BA.debugLine="timeline.initialize";
_timeline.Initialize();
 //BA.debugLineNum = 362;BA.debugLine="eventmap.Put(\"AllEvents\", allevents)";
_eventmap.Put((Object)("AllEvents"),(Object)(_allevents.getObject()));
 //BA.debugLineNum = 363;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
 //BA.debugLineNum = 365;BA.debugLine="CalendarActivity.CalendarMap.Put(currentDate, eve";
mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)(_currentdate),(Object)(_eventmap.getObject()));
 //BA.debugLineNum = 367;BA.debugLine="Return eventmap";
if (true) return _eventmap;
 //BA.debugLineNum = 368;BA.debugLine="End Sub";
return null;
}
public static String  _menu_btn_click() throws Exception{
 //BA.debugLineNum = 470;BA.debugLine="Private Sub menu_btn_Click";
 //BA.debugLineNum = 471;BA.debugLine="menupanel.Visible =True";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 472;BA.debugLine="End Sub";
return "";
}
public static String  _month_btn_click() throws Exception{
 //BA.debugLineNum = 483;BA.debugLine="Private Sub Month_btn_Click";
 //BA.debugLineNum = 484;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 485;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
 //BA.debugLineNum = 486;BA.debugLine="End Sub";
return "";
}
public static String  _ooo_btn_click() throws Exception{
 //BA.debugLineNum = 517;BA.debugLine="Private Sub ooo_btn_Click";
 //BA.debugLineNum = 518;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 519;BA.debugLine="add_events_module.eventtype = \"OOO\"";
mostCurrent._add_events_module._eventtype /*String*/  = "OOO";
 //BA.debugLineNum = 520;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
 //BA.debugLineNum = 521;BA.debugLine="End Sub";
return "";
}
public static String  _ooorb_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 612;BA.debugLine="Private Sub ooorb_CheckedChange(Checked As Boolean";
 //BA.debugLineNum = 613;BA.debugLine="eventtype = \"OOO\"";
mostCurrent._eventtype = "OOO";
 //BA.debugLineNum = 614;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim currentDate As String";
_currentdate = "";
 //BA.debugLineNum = 10;BA.debugLine="Dim addeventsfeedback As Boolean";
_addeventsfeedback = false;
 //BA.debugLineNum = 11;BA.debugLine="Dim currentIndex As Int";
_currentindex = 0;
 //BA.debugLineNum = 12;BA.debugLine="Dim schedules As Map";
_schedules = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _savecalendar() throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub SaveCalendar";
 //BA.debugLineNum = 127;BA.debugLine="CalendarActivity.kvs.put(\"CalendarKVS\", CalendarA";
mostCurrent._calendaractivity._kvs /*b4a.example3.keyvaluestore*/ ._put("CalendarKVS",(Object)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
public static String  _saveedit_btn_click() throws Exception{
 //BA.debugLineNum = 542;BA.debugLine="Private Sub saveEdit_btn_Click";
 //BA.debugLineNum = 543;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
if (mostCurrent._starttimelinesp.getSelectedIndex()==mostCurrent._endtimelinesp.getSelectedIndex() || mostCurrent._starttimelinesp.getSelectedIndex()>mostCurrent._endtimelinesp.getSelectedIndex()) { 
 //BA.debugLineNum = 544;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Invalid Timeline"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 545;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 548;BA.debugLine="If editTitle_et.text = \"\" Then";
if ((mostCurrent._edittitle_et.getText()).equals("")) { 
 //BA.debugLineNum = 549;BA.debugLine="MsgboxAsync(\"Event must have name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event must have name"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 550;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 552;BA.debugLine="currenttaggedEvent.Put(\"Title\", editTitle_et.Text";
mostCurrent._currenttaggedevent.Put((Object)("Title"),(Object)(mostCurrent._edittitle_et.getText()));
 //BA.debugLineNum = 553;BA.debugLine="currenttaggedEvent.Put(\"Description\", editDescrip";
mostCurrent._currenttaggedevent.Put((Object)("Description"),(Object)(mostCurrent._editdescription_et.getText()));
 //BA.debugLineNum = 555;BA.debugLine="x_EventInfo_btn_Click";
_x_eventinfo_btn_click();
 //BA.debugLineNum = 556;BA.debugLine="eventTitle_lbl.text = editTitle_et.Text";
mostCurrent._eventtitle_lbl.setText(BA.ObjectToCharSequence(mostCurrent._edittitle_et.getText()));
 //BA.debugLineNum = 557;BA.debugLine="eventdescription_lbl.Text = editDescription_et.te";
mostCurrent._eventdescription_lbl.setText(BA.ObjectToCharSequence(mostCurrent._editdescription_et.getText()));
 //BA.debugLineNum = 558;BA.debugLine="eventInfo_panel.Visible = True";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 559;BA.debugLine="SaveCalendar";
_savecalendar();
 //BA.debugLineNum = 560;BA.debugLine="DrawMainEvents";
_drawmainevents();
 //BA.debugLineNum = 563;BA.debugLine="End Sub";
return "";
}
public static String  _savetl_btn_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _existing = null;
int _st = 0;
int _en = 0;
 //BA.debugLineNum = 370;BA.debugLine="Private Sub saveTL_btn_Click";
 //BA.debugLineNum = 371;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
if (mostCurrent._starttimelinesp.getSelectedIndex()==mostCurrent._endtimelinesp.getSelectedIndex() || mostCurrent._starttimelinesp.getSelectedIndex()>mostCurrent._endtimelinesp.getSelectedIndex()) { 
 //BA.debugLineNum = 372;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Invalid Timeline"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 373;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 375;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 //BA.debugLineNum = 376;BA.debugLine="If addTL_et.text = \"\" Then";
if ((mostCurrent._addtl_et.getText()).equals("")) { 
 //BA.debugLineNum = 377;BA.debugLine="MsgboxAsync(\"Event must have a name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event must have a name"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 378;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 381;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate))) { 
 //BA.debugLineNum = 382;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 }else {
 //BA.debugLineNum = 384;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
 //BA.debugLineNum = 386;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
 //BA.debugLineNum = 387;BA.debugLine="Dim ev As Map";
_ev = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 388;BA.debugLine="ev.Initialize";
_ev.Initialize();
 //BA.debugLineNum = 390;BA.debugLine="ev.Put(\"ID\", DateTime.Now)";
_ev.Put((Object)("ID"),(Object)(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 391;BA.debugLine="ev.Put(\"Title\", addTL_et.Text)";
_ev.Put((Object)("Title"),(Object)(mostCurrent._addtl_et.getText()));
 //BA.debugLineNum = 392;BA.debugLine="ev.Put(\"Start\", starttimelineSP.SelectedIndex)";
_ev.Put((Object)("Start"),(Object)(mostCurrent._starttimelinesp.getSelectedIndex()));
 //BA.debugLineNum = 393;BA.debugLine="ev.Put(\"End\", endtimelineSP.SelectedIndex)";
_ev.Put((Object)("End"),(Object)(mostCurrent._endtimelinesp.getSelectedIndex()));
 //BA.debugLineNum = 394;BA.debugLine="ev.Put(\"Tags\", eventtype)";
_ev.Put((Object)("Tags"),(Object)(mostCurrent._eventtype));
 //BA.debugLineNum = 397;BA.debugLine="For i = timeline.Size - 1 To 0 Step -1";
{
final int step23 = -1;
final int limit23 = (int) (0);
_i = (int) (_timeline.getSize()-1) ;
for (;_i >= limit23 ;_i = _i + step23 ) {
 //BA.debugLineNum = 398;BA.debugLine="Dim existing As Map = timeline.Get(i)";
_existing = new anywheresoftware.b4a.objects.collections.Map();
_existing = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_timeline.Get(_i)));
 //BA.debugLineNum = 399;BA.debugLine="Dim st As Int = existing.Get(\"Start\")";
_st = (int)(BA.ObjectToNumber(_existing.Get((Object)("Start"))));
 //BA.debugLineNum = 400;BA.debugLine="Dim en As Int = existing.Get(\"End\")";
_en = (int)(BA.ObjectToNumber(_existing.Get((Object)("End"))));
 //BA.debugLineNum = 403;BA.debugLine="If (starttimelineSP.SelectedIndex < en) And (end";
if ((mostCurrent._starttimelinesp.getSelectedIndex()<_en) && (mostCurrent._endtimelinesp.getSelectedIndex()>_st)) { 
 //BA.debugLineNum = 404;BA.debugLine="timeline.RemoveAt(i)";
_timeline.RemoveAt(_i);
 //BA.debugLineNum = 405;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 409;BA.debugLine="timeline.add(ev)";
_timeline.Add((Object)(_ev.getObject()));
 //BA.debugLineNum = 411;BA.debugLine="SaveCalendar";
_savecalendar();
 //BA.debugLineNum = 412;BA.debugLine="UpdateTimeLine";
_updatetimeline();
 //BA.debugLineNum = 413;BA.debugLine="addEventTL_panel.Visible  = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 415;BA.debugLine="End Sub";
return "";
}
public static String  _sched_btn_click() throws Exception{
 //BA.debugLineNum = 474;BA.debugLine="Private Sub sched_btn_Click";
 //BA.debugLineNum = 475;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 476;BA.debugLine="StartActivity(Schedule_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._schedule_module.getObject()));
 //BA.debugLineNum = 477;BA.debugLine="End Sub";
return "";
}
public static String  _setdate(String _tagdate) throws Exception{
String[] _parts = null;
String _year = "";
int _monthnum = 0;
String _day = "";
String _monthname = "";
long _ts = 0L;
int _weekdaynum = 0;
String _week = "";
 //BA.debugLineNum = 418;BA.debugLine="Sub SetDate(Tagdate As String) As String";
 //BA.debugLineNum = 420;BA.debugLine="Dim parts() As String = Regex.Split(\"-\", Tagdate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_tagdate);
 //BA.debugLineNum = 421;BA.debugLine="Dim year As String = parts(0)";
_year = _parts[(int) (0)];
 //BA.debugLineNum = 422;BA.debugLine="Dim monthNum As Int = parts(1)";
_monthnum = (int)(Double.parseDouble(_parts[(int) (1)]));
 //BA.debugLineNum = 423;BA.debugLine="Dim day As String = parts(2)";
_day = _parts[(int) (2)];
 //BA.debugLineNum = 425;BA.debugLine="Dim monthName As String";
_monthname = "";
 //BA.debugLineNum = 426;BA.debugLine="Select monthNum";
switch (_monthnum) {
case 1: {
 //BA.debugLineNum = 427;BA.debugLine="Case 1: monthName = \"January\"";
_monthname = "January";
 break; }
case 2: {
 //BA.debugLineNum = 428;BA.debugLine="Case 2: monthName = \"February\"";
_monthname = "February";
 break; }
case 3: {
 //BA.debugLineNum = 429;BA.debugLine="Case 3: monthName = \"March\"";
_monthname = "March";
 break; }
case 4: {
 //BA.debugLineNum = 430;BA.debugLine="Case 4: monthName = \"April\"";
_monthname = "April";
 break; }
case 5: {
 //BA.debugLineNum = 431;BA.debugLine="Case 5: monthName = \"May\"";
_monthname = "May";
 break; }
case 6: {
 //BA.debugLineNum = 432;BA.debugLine="Case 6: monthName = \"June\"";
_monthname = "June";
 break; }
case 7: {
 //BA.debugLineNum = 433;BA.debugLine="Case 7: monthName = \"July\"";
_monthname = "July";
 break; }
case 8: {
 //BA.debugLineNum = 434;BA.debugLine="Case 8: monthName = \"August\"";
_monthname = "August";
 break; }
case 9: {
 //BA.debugLineNum = 435;BA.debugLine="Case 9: monthName = \"September\"";
_monthname = "September";
 break; }
case 10: {
 //BA.debugLineNum = 436;BA.debugLine="Case 10: monthName = \"October\"";
_monthname = "October";
 break; }
case 11: {
 //BA.debugLineNum = 437;BA.debugLine="Case 11: monthName = \"November\"";
_monthname = "November";
 break; }
case 12: {
 //BA.debugLineNum = 438;BA.debugLine="Case 12: monthName = \"December\"";
_monthname = "December";
 break; }
}
;
 //BA.debugLineNum = 441;BA.debugLine="Dim ts As Long = DateTime.DateParse(Tagdate)";
_ts = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_tagdate);
 //BA.debugLineNum = 442;BA.debugLine="Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)";
_weekdaynum = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_ts);
 //BA.debugLineNum = 443;BA.debugLine="Dim week As String";
_week = "";
 //BA.debugLineNum = 444;BA.debugLine="Select weekdayNum";
switch (_weekdaynum) {
case 1: {
 //BA.debugLineNum = 445;BA.debugLine="Case 1: week = \"Sunday\"";
_week = "Sunday";
 break; }
case 2: {
 //BA.debugLineNum = 446;BA.debugLine="Case 2: week = \"Monday\"";
_week = "Monday";
 break; }
case 3: {
 //BA.debugLineNum = 447;BA.debugLine="Case 3: week = \"Tuesday\"";
_week = "Tuesday";
 break; }
case 4: {
 //BA.debugLineNum = 448;BA.debugLine="Case 4: week = \"Wednesday\"";
_week = "Wednesday";
 break; }
case 5: {
 //BA.debugLineNum = 449;BA.debugLine="Case 5: week = \"Thursday\"";
_week = "Thursday";
 break; }
case 6: {
 //BA.debugLineNum = 450;BA.debugLine="Case 6: week = \"Friday\"";
_week = "Friday";
 break; }
case 7: {
 //BA.debugLineNum = 451;BA.debugLine="Case 7: week = \"Saturday\"";
_week = "Saturday";
 break; }
}
;
 //BA.debugLineNum = 454;BA.debugLine="Return week & \", \" & monthName & \" \" & day & \", \"";
if (true) return _week+", "+_monthname+" "+_day+", "+_year;
 //BA.debugLineNum = 455;BA.debugLine="End Sub";
return "";
}
public static String  _setupspinners() throws Exception{
anywheresoftware.b4a.objects.collections.List _hours = null;
int _i = 0;
 //BA.debugLineNum = 116;BA.debugLine="Sub SetUpSpinners";
 //BA.debugLineNum = 117;BA.debugLine="Dim hours As List";
_hours = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 118;BA.debugLine="hours.Initialize";
_hours.Initialize();
 //BA.debugLineNum = 119;BA.debugLine="For i = 0 To 24";
{
final int step3 = 1;
final int limit3 = (int) (24);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 120;BA.debugLine="hours.Add(GetTimeString(i))";
_hours.Add((Object)(_gettimestring(_i)));
 }
};
 //BA.debugLineNum = 122;BA.debugLine="starttimelineSP.AddAll(hours)";
mostCurrent._starttimelinesp.AddAll(_hours);
 //BA.debugLineNum = 123;BA.debugLine="endtimelineSP.AddAll(hours)";
mostCurrent._endtimelinesp.AddAll(_hours);
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static String  _taskrb_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 624;BA.debugLine="Private Sub taskrb_CheckedChange(Checked As Boolea";
 //BA.debugLineNum = 625;BA.debugLine="eventtype = \"Task\"";
mostCurrent._eventtype = "Task";
 //BA.debugLineNum = 626;BA.debugLine="End Sub";
return "";
}
public static String  _timelineevent_click() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
 //BA.debugLineNum = 298;BA.debugLine="Sub timelineEvent_Click";
 //BA.debugLineNum = 299;BA.debugLine="Dim lbl As Label = Sender";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 300;BA.debugLine="Dim ev As Map = lbl.Tag";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_lbl.getTag()));
 //BA.debugLineNum = 301;BA.debugLine="currenttaggedEvent = ev";
mostCurrent._currenttaggedevent = _ev;
 //BA.debugLineNum = 302;BA.debugLine="currentevId = ev.Get(\"ID\")";
_currentevid = BA.ObjectToLongNumber(_ev.Get((Object)("ID")));
 //BA.debugLineNum = 305;BA.debugLine="addEventTL_panel.Visible = True";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 308;BA.debugLine="addTL_et.Text = ev.Get(\"Title\")";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
 //BA.debugLineNum = 309;BA.debugLine="starttimelineSP.SelectedIndex = ev.Get(\"Start\")";
mostCurrent._starttimelinesp.setSelectedIndex((int)(BA.ObjectToNumber(_ev.Get((Object)("Start")))));
 //BA.debugLineNum = 310;BA.debugLine="endtimelineSP.SelectedIndex = ev.Get(\"End\")";
mostCurrent._endtimelinesp.setSelectedIndex((int)(BA.ObjectToNumber(_ev.Get((Object)("End")))));
 //BA.debugLineNum = 313;BA.debugLine="Select Case ev.Get(\"Tags\")";
switch (BA.switchObjectToInt(_ev.Get((Object)("Tags")),(Object)("Task"),(Object)("Event"),(Object)("Birthday"),(Object)("OOO"))) {
case 0: {
 //BA.debugLineNum = 314;BA.debugLine="Case \"Task\": taskrb.Checked = True";
mostCurrent._taskrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 1: {
 //BA.debugLineNum = 315;BA.debugLine="Case \"Event\": eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 2: {
 //BA.debugLineNum = 316;BA.debugLine="Case \"Birthday\": birthdayrb.Checked = True";
mostCurrent._birthdayrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 3: {
 //BA.debugLineNum = 317;BA.debugLine="Case \"OOO\": ooorb.Checked = True";
mostCurrent._ooorb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 319;BA.debugLine="End Sub";
return "";
}
public static String  _updatetimeline() throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Sub UpdateTimeLine";
 //BA.debugLineNum = 188;BA.debugLine="DrawMainEvents";
_drawmainevents();
 //BA.debugLineNum = 189;BA.debugLine="DrawHourLabels";
_drawhourlabels();
 //BA.debugLineNum = 190;BA.debugLine="DrawTimelineEvents";
_drawtimelineevents();
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static String  _x_eventinfo_btn_click() throws Exception{
 //BA.debugLineNum = 529;BA.debugLine="Private Sub x_EventInfo_btn_Click";
 //BA.debugLineNum = 530;BA.debugLine="eventInfo_panel.Visible = False";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 531;BA.debugLine="EditInfoPanel.visible = False";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 532;BA.debugLine="End Sub";
return "";
}
public static String  _x_tlevent_btn_click() throws Exception{
 //BA.debugLineNum = 579;BA.debugLine="Private Sub x_TLevent_btn_Click";
 //BA.debugLineNum = 580;BA.debugLine="addEventTL_panel.Visible = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 581;BA.debugLine="addTL_et.text = \"\"";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 582;BA.debugLine="End Sub";
return "";
}
}
