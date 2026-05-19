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
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
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
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=18153472;
 //BA.debugLineNum = 18153472;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=18153474;
 //BA.debugLineNum = 18153474;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=18153475;
 //BA.debugLineNum = 18153475;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=18153476;
 //BA.debugLineNum = 18153476;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=18153478;
 //BA.debugLineNum = 18153478;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=18153479;
 //BA.debugLineNum = 18153479;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout",mostCurrent.activityBA);
RDebugUtils.currentLine=18153480;
 //BA.debugLineNum = 18153480;BA.debugLine="cd.Initialize(Colors.ARGB(125, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (125),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=18153481;
 //BA.debugLineNum = 18153481;BA.debugLine="Day_btn.Background = cd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=18153482;
 //BA.debugLineNum = 18153482;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=18153484;
 //BA.debugLineNum = 18153484;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark",mostCurrent.activityBA);
RDebugUtils.currentLine=18153485;
 //BA.debugLineNum = 18153485;BA.debugLine="cd.Initialize(Colors.ARGB(125, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (125),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=18153486;
 //BA.debugLineNum = 18153486;BA.debugLine="Day_btn.Background = cd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=18153487;
 //BA.debugLineNum = 18153487;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=18153490;
 //BA.debugLineNum = 18153490;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=18153491;
 //BA.debugLineNum = 18153491;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout2",mostCurrent.activityBA);
RDebugUtils.currentLine=18153492;
 //BA.debugLineNum = 18153492;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Pink .png").getObject()));
RDebugUtils.currentLine=18153493;
 //BA.debugLineNum = 18153493;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=18153494;
 //BA.debugLineNum = 18153494;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=18153496;
 //BA.debugLineNum = 18153496;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark2\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark2",mostCurrent.activityBA);
RDebugUtils.currentLine=18153497;
 //BA.debugLineNum = 18153497;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"pink";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pink Skeumorphic Button DARK.png").getObject()));
RDebugUtils.currentLine=18153498;
 //BA.debugLineNum = 18153498;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=18153499;
 //BA.debugLineNum = 18153499;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=18153502;
 //BA.debugLineNum = 18153502;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=18153503;
 //BA.debugLineNum = 18153503;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayout3",mostCurrent.activityBA);
RDebugUtils.currentLine=18153504;
 //BA.debugLineNum = 18153504;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
RDebugUtils.currentLine=18153505;
 //BA.debugLineNum = 18153505;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=18153506;
 //BA.debugLineNum = 18153506;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=18153508;
 //BA.debugLineNum = 18153508;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark3\")";
mostCurrent._activity.LoadLayout("Day_ModuleLayoutDark3",mostCurrent.activityBA);
RDebugUtils.currentLine=18153509;
 //BA.debugLineNum = 18153509;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtnd.png").getObject()));
RDebugUtils.currentLine=18153510;
 //BA.debugLineNum = 18153510;BA.debugLine="Day_btn.Background = bd";
mostCurrent._day_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=18153511;
 //BA.debugLineNum = 18153511;BA.debugLine="Day_btn.TextColor = Colors.White";
mostCurrent._day_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
RDebugUtils.currentLine=18153515;
 //BA.debugLineNum = 18153515;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=18153516;
 //BA.debugLineNum = 18153516;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
mostCurrent._starttimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=18153517;
 //BA.debugLineNum = 18153517;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.Black";
mostCurrent._starttimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=18153518;
 //BA.debugLineNum = 18153518;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.W";
mostCurrent._endtimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=18153519;
 //BA.debugLineNum = 18153519;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.Black";
mostCurrent._endtimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=18153521;
 //BA.debugLineNum = 18153521;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
mostCurrent._starttimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=18153522;
 //BA.debugLineNum = 18153522;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.White";
mostCurrent._starttimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=18153523;
 //BA.debugLineNum = 18153523;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.D";
mostCurrent._endtimelinesp.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=18153524;
 //BA.debugLineNum = 18153524;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.White";
mostCurrent._endtimelinesp.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=18153527;
 //BA.debugLineNum = 18153527;BA.debugLine="date_todaylbl.Text = SetDate(currentDate)";
mostCurrent._date_todaylbl.setText(BA.ObjectToCharSequence(_setdate(_currentdate)));
RDebugUtils.currentLine=18153528;
 //BA.debugLineNum = 18153528;BA.debugLine="add_events_module.currentDate = SetDate(currentDa";
mostCurrent._add_events_module._currentdate /*String*/  = _setdate(_currentdate);
RDebugUtils.currentLine=18153529;
 //BA.debugLineNum = 18153529;BA.debugLine="SetUpSpinners";
_setupspinners();
RDebugUtils.currentLine=18153531;
 //BA.debugLineNum = 18153531;BA.debugLine="Log(currentDate)";
anywheresoftware.b4a.keywords.Common.LogImpl("418153531",_currentdate,0);
RDebugUtils.currentLine=18153533;
 //BA.debugLineNum = 18153533;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=19070976;
 //BA.debugLineNum = 19070976;BA.debugLine="Sub SetDate(Tagdate As String) As String";
RDebugUtils.currentLine=19070978;
 //BA.debugLineNum = 19070978;BA.debugLine="Dim parts() As String = Regex.Split(\"-\", Tagdate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_tagdate);
RDebugUtils.currentLine=19070979;
 //BA.debugLineNum = 19070979;BA.debugLine="Dim year As String = parts(0)";
_year = _parts[(int) (0)];
RDebugUtils.currentLine=19070980;
 //BA.debugLineNum = 19070980;BA.debugLine="Dim monthNum As Int = parts(1)";
_monthnum = (int)(Double.parseDouble(_parts[(int) (1)]));
RDebugUtils.currentLine=19070981;
 //BA.debugLineNum = 19070981;BA.debugLine="Dim day As String = parts(2)";
_day = _parts[(int) (2)];
RDebugUtils.currentLine=19070983;
 //BA.debugLineNum = 19070983;BA.debugLine="Dim monthName As String";
_monthname = "";
RDebugUtils.currentLine=19070984;
 //BA.debugLineNum = 19070984;BA.debugLine="Select monthNum";
switch (_monthnum) {
case 1: {
RDebugUtils.currentLine=19070985;
 //BA.debugLineNum = 19070985;BA.debugLine="Case 1: monthName = \"January\"";
_monthname = "January";
 break; }
case 2: {
RDebugUtils.currentLine=19070986;
 //BA.debugLineNum = 19070986;BA.debugLine="Case 2: monthName = \"February\"";
_monthname = "February";
 break; }
case 3: {
RDebugUtils.currentLine=19070987;
 //BA.debugLineNum = 19070987;BA.debugLine="Case 3: monthName = \"March\"";
_monthname = "March";
 break; }
case 4: {
RDebugUtils.currentLine=19070988;
 //BA.debugLineNum = 19070988;BA.debugLine="Case 4: monthName = \"April\"";
_monthname = "April";
 break; }
case 5: {
RDebugUtils.currentLine=19070989;
 //BA.debugLineNum = 19070989;BA.debugLine="Case 5: monthName = \"May\"";
_monthname = "May";
 break; }
case 6: {
RDebugUtils.currentLine=19070990;
 //BA.debugLineNum = 19070990;BA.debugLine="Case 6: monthName = \"June\"";
_monthname = "June";
 break; }
case 7: {
RDebugUtils.currentLine=19070991;
 //BA.debugLineNum = 19070991;BA.debugLine="Case 7: monthName = \"July\"";
_monthname = "July";
 break; }
case 8: {
RDebugUtils.currentLine=19070992;
 //BA.debugLineNum = 19070992;BA.debugLine="Case 8: monthName = \"August\"";
_monthname = "August";
 break; }
case 9: {
RDebugUtils.currentLine=19070993;
 //BA.debugLineNum = 19070993;BA.debugLine="Case 9: monthName = \"September\"";
_monthname = "September";
 break; }
case 10: {
RDebugUtils.currentLine=19070994;
 //BA.debugLineNum = 19070994;BA.debugLine="Case 10: monthName = \"October\"";
_monthname = "October";
 break; }
case 11: {
RDebugUtils.currentLine=19070995;
 //BA.debugLineNum = 19070995;BA.debugLine="Case 11: monthName = \"November\"";
_monthname = "November";
 break; }
case 12: {
RDebugUtils.currentLine=19070996;
 //BA.debugLineNum = 19070996;BA.debugLine="Case 12: monthName = \"December\"";
_monthname = "December";
 break; }
}
;
RDebugUtils.currentLine=19070999;
 //BA.debugLineNum = 19070999;BA.debugLine="Dim ts As Long = DateTime.DateParse(Tagdate)";
_ts = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_tagdate);
RDebugUtils.currentLine=19071000;
 //BA.debugLineNum = 19071000;BA.debugLine="Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)";
_weekdaynum = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_ts);
RDebugUtils.currentLine=19071001;
 //BA.debugLineNum = 19071001;BA.debugLine="Dim week As String";
_week = "";
RDebugUtils.currentLine=19071002;
 //BA.debugLineNum = 19071002;BA.debugLine="Select weekdayNum";
switch (_weekdaynum) {
case 1: {
RDebugUtils.currentLine=19071003;
 //BA.debugLineNum = 19071003;BA.debugLine="Case 1: week = \"Sunday\"";
_week = "Sunday";
 break; }
case 2: {
RDebugUtils.currentLine=19071004;
 //BA.debugLineNum = 19071004;BA.debugLine="Case 2: week = \"Monday\"";
_week = "Monday";
 break; }
case 3: {
RDebugUtils.currentLine=19071005;
 //BA.debugLineNum = 19071005;BA.debugLine="Case 3: week = \"Tuesday\"";
_week = "Tuesday";
 break; }
case 4: {
RDebugUtils.currentLine=19071006;
 //BA.debugLineNum = 19071006;BA.debugLine="Case 4: week = \"Wednesday\"";
_week = "Wednesday";
 break; }
case 5: {
RDebugUtils.currentLine=19071007;
 //BA.debugLineNum = 19071007;BA.debugLine="Case 5: week = \"Thursday\"";
_week = "Thursday";
 break; }
case 6: {
RDebugUtils.currentLine=19071008;
 //BA.debugLineNum = 19071008;BA.debugLine="Case 6: week = \"Friday\"";
_week = "Friday";
 break; }
case 7: {
RDebugUtils.currentLine=19071009;
 //BA.debugLineNum = 19071009;BA.debugLine="Case 7: week = \"Saturday\"";
_week = "Saturday";
 break; }
}
;
RDebugUtils.currentLine=19071012;
 //BA.debugLineNum = 19071012;BA.debugLine="Return week & \", \" & monthName & \" \" & day & \", \"";
if (true) return _week+", "+_monthname+" "+_day+", "+_year;
RDebugUtils.currentLine=19071013;
 //BA.debugLineNum = 19071013;BA.debugLine="End Sub";
return "";
}
public static String  _setupspinners() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setupspinners", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setupspinners", null));}
anywheresoftware.b4a.objects.collections.List _hours = null;
int _i = 0;
RDebugUtils.currentLine=18219008;
 //BA.debugLineNum = 18219008;BA.debugLine="Sub SetUpSpinners";
RDebugUtils.currentLine=18219009;
 //BA.debugLineNum = 18219009;BA.debugLine="Dim hours As List";
_hours = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=18219010;
 //BA.debugLineNum = 18219010;BA.debugLine="hours.Initialize";
_hours.Initialize();
RDebugUtils.currentLine=18219011;
 //BA.debugLineNum = 18219011;BA.debugLine="For i = 0 To 24";
{
final int step3 = 1;
final int limit3 = (int) (24);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=18219012;
 //BA.debugLineNum = 18219012;BA.debugLine="hours.Add(GetTimeString(i))";
_hours.Add((Object)(_gettimestring(_i)));
 }
};
RDebugUtils.currentLine=18219014;
 //BA.debugLineNum = 18219014;BA.debugLine="starttimelineSP.AddAll(hours)";
mostCurrent._starttimelinesp.AddAll(_hours);
RDebugUtils.currentLine=18219015;
 //BA.debugLineNum = 18219015;BA.debugLine="endtimelineSP.AddAll(hours)";
mostCurrent._endtimelinesp.AddAll(_hours);
RDebugUtils.currentLine=18219016;
 //BA.debugLineNum = 18219016;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="day_module";
RDebugUtils.currentLine=19202048;
 //BA.debugLineNum = 19202048;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=19202050;
 //BA.debugLineNum = 19202050;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=19136512;
 //BA.debugLineNum = 19136512;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=19136513;
 //BA.debugLineNum = 19136513;BA.debugLine="UpdateTimeLine";
_updatetimeline();
RDebugUtils.currentLine=19136514;
 //BA.debugLineNum = 19136514;BA.debugLine="If addeventsfeedback = True Then";
if (_addeventsfeedback==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=19136515;
 //BA.debugLineNum = 19136515;BA.debugLine="addeventsfeedback = False";
_addeventsfeedback = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=19136516;
 //BA.debugLineNum = 19136516;BA.debugLine="MsgboxAsync(\"Event Saved\", \"Saved\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event Saved"),BA.ObjectToCharSequence("Saved"),processBA);
 };
RDebugUtils.currentLine=19136518;
 //BA.debugLineNum = 19136518;BA.debugLine="End Sub";
return "";
}
public static String  _updatetimeline() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatetimeline", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatetimeline", null));}
RDebugUtils.currentLine=18546688;
 //BA.debugLineNum = 18546688;BA.debugLine="Sub UpdateTimeLine";
RDebugUtils.currentLine=18546689;
 //BA.debugLineNum = 18546689;BA.debugLine="DrawMainEvents";
_drawmainevents();
RDebugUtils.currentLine=18546690;
 //BA.debugLineNum = 18546690;BA.debugLine="DrawHourLabels";
_drawhourlabels();
RDebugUtils.currentLine=18546691;
 //BA.debugLineNum = 18546691;BA.debugLine="DrawTimelineEvents";
_drawtimelineevents();
RDebugUtils.currentLine=18546692;
 //BA.debugLineNum = 18546692;BA.debugLine="End Sub";
return "";
}
public static String  _addevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addevent_btn_click", null));}
RDebugUtils.currentLine=19529728;
 //BA.debugLineNum = 19529728;BA.debugLine="Private Sub Addevent_btn_Click";
RDebugUtils.currentLine=19529729;
 //BA.debugLineNum = 19529729;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19529730;
 //BA.debugLineNum = 19529730;BA.debugLine="add_events_module.eventtype = \"Event\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Event";
RDebugUtils.currentLine=19529731;
 //BA.debugLineNum = 19529731;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=19529732;
 //BA.debugLineNum = 19529732;BA.debugLine="End Sub";
return "";
}
public static String  _addnew_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addnew_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addnew_btn_click", null));}
RDebugUtils.currentLine=19595264;
 //BA.debugLineNum = 19595264;BA.debugLine="Private Sub addnew_btn_Click";
RDebugUtils.currentLine=19595266;
 //BA.debugLineNum = 19595266;BA.debugLine="If addpanel.Visible =True Then";
if (mostCurrent._addpanel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=19595267;
 //BA.debugLineNum = 19595267;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19595268;
 //BA.debugLineNum = 19595268;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=19595270;
 //BA.debugLineNum = 19595270;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=19595273;
 //BA.debugLineNum = 19595273;BA.debugLine="End Sub";
return "";
}
public static String  _addtask_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtask_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtask_btn_click", null));}
RDebugUtils.currentLine=19660800;
 //BA.debugLineNum = 19660800;BA.debugLine="Private Sub Addtask_btn_Click";
RDebugUtils.currentLine=19660801;
 //BA.debugLineNum = 19660801;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19660802;
 //BA.debugLineNum = 19660802;BA.debugLine="add_events_module.eventtype = \"Task\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Task";
RDebugUtils.currentLine=19660803;
 //BA.debugLineNum = 19660803;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=19660804;
 //BA.debugLineNum = 19660804;BA.debugLine="End Sub";
return "";
}
public static String  _birthday_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "birthday_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "birthday_btn_click", null));}
RDebugUtils.currentLine=19726336;
 //BA.debugLineNum = 19726336;BA.debugLine="Private Sub birthday_btn_Click";
RDebugUtils.currentLine=19726337;
 //BA.debugLineNum = 19726337;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19726338;
 //BA.debugLineNum = 19726338;BA.debugLine="add_events_module.eventtype = \"Birthday\"";
mostCurrent._add_events_module._eventtype /*String*/  = "Birthday";
RDebugUtils.currentLine=19726339;
 //BA.debugLineNum = 19726339;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=19726340;
 //BA.debugLineNum = 19726340;BA.debugLine="End Sub";
return "";
}
public static String  _birthdayrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "birthdayrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "birthdayrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=20643840;
 //BA.debugLineNum = 20643840;BA.debugLine="Private Sub birthdayrb_CheckedChange(Checked As Bo";
RDebugUtils.currentLine=20643841;
 //BA.debugLineNum = 20643841;BA.debugLine="eventtype = \"Birthday\"";
mostCurrent._eventtype = "Birthday";
RDebugUtils.currentLine=20643842;
 //BA.debugLineNum = 20643842;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceldelete_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceldelete_btn_click", null));}
RDebugUtils.currentLine=20185088;
 //BA.debugLineNum = 20185088;BA.debugLine="Private Sub cancelDelete_btn_Click";
RDebugUtils.currentLine=20185089;
 //BA.debugLineNum = 20185089;BA.debugLine="deletepanel.Visible = False";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20185090;
 //BA.debugLineNum = 20185090;BA.debugLine="End Sub";
return "";
}
public static String  _canceledit_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceledit_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceledit_btn_click", null));}
RDebugUtils.currentLine=20054016;
 //BA.debugLineNum = 20054016;BA.debugLine="Private Sub cancelEdit_btn_Click";
RDebugUtils.currentLine=20054017;
 //BA.debugLineNum = 20054017;BA.debugLine="EditInfoPanel.Visible = False";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20054018;
 //BA.debugLineNum = 20054018;BA.debugLine="End Sub";
return "";
}
public static String  _canceltldelete_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceltldelete_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceltldelete_btn_click", null));}
RDebugUtils.currentLine=20447232;
 //BA.debugLineNum = 20447232;BA.debugLine="Private Sub cancelTLdelete_btn_Click";
RDebugUtils.currentLine=20447233;
 //BA.debugLineNum = 20447233;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20447234;
 //BA.debugLineNum = 20447234;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmdelete_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmdelete_btn_click", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
RDebugUtils.currentLine=20250624;
 //BA.debugLineNum = 20250624;BA.debugLine="Private Sub confirmdelete_btn_Click";
RDebugUtils.currentLine=20250625;
 //BA.debugLineNum = 20250625;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=20250626;
 //BA.debugLineNum = 20250626;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=20250627;
 //BA.debugLineNum = 20250627;BA.debugLine="allevents.RemoveAt(currentIndex)";
_allevents.RemoveAt(_currentindex);
RDebugUtils.currentLine=20250628;
 //BA.debugLineNum = 20250628;BA.debugLine="deletepanel.Visible = False";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20250629;
 //BA.debugLineNum = 20250629;BA.debugLine="eventInfo_panel.Visible = False";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20250630;
 //BA.debugLineNum = 20250630;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=20250631;
 //BA.debugLineNum = 20250631;BA.debugLine="DrawMainEvents";
_drawmainevents();
RDebugUtils.currentLine=20250632;
 //BA.debugLineNum = 20250632;BA.debugLine="End Sub";
return "";
}
public static String  _savecalendar() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savecalendar", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savecalendar", null));}
RDebugUtils.currentLine=18284544;
 //BA.debugLineNum = 18284544;BA.debugLine="Sub SaveCalendar";
RDebugUtils.currentLine=18284545;
 //BA.debugLineNum = 18284545;BA.debugLine="CalendarActivity.kvs.put(\"CalendarKVS\", CalendarA";
mostCurrent._calendaractivity._kvs /*b4a.example3.keyvaluestore*/ ._put("CalendarKVS",(Object)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
RDebugUtils.currentLine=18284546;
 //BA.debugLineNum = 18284546;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18350080;
 //BA.debugLineNum = 18350080;BA.debugLine="Sub DrawMainEvents";
RDebugUtils.currentLine=18350081;
 //BA.debugLineNum = 18350081;BA.debugLine="svEvents.Panel.RemoveAllViews";
mostCurrent._svevents.getPanel().RemoveAllViews();
RDebugUtils.currentLine=18350082;
 //BA.debugLineNum = 18350082;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate)))) { 
RDebugUtils.currentLine=18350083;
 //BA.debugLineNum = 18350083;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=18350086;
 //BA.debugLineNum = 18350086;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=18350087;
 //BA.debugLineNum = 18350087;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=18350089;
 //BA.debugLineNum = 18350089;BA.debugLine="Dim y As Int = 0";
_y = (int) (0);
RDebugUtils.currentLine=18350090;
 //BA.debugLineNum = 18350090;BA.debugLine="Dim rowHeight As Int = 50dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50));
RDebugUtils.currentLine=18350091;
 //BA.debugLineNum = 18350091;BA.debugLine="For i = 0 To allevents.Size -1";
{
final int step9 = 1;
final int limit9 = (int) (_allevents.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
RDebugUtils.currentLine=18350092;
 //BA.debugLineNum = 18350092;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=18350093;
 //BA.debugLineNum = 18350093;BA.debugLine="Dim ev As Map = allevents.Get(i)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_allevents.Get(_i)));
RDebugUtils.currentLine=18350094;
 //BA.debugLineNum = 18350094;BA.debugLine="lbl.Initialize(\"mainEvent\")";
_lbl.Initialize(mostCurrent.activityBA,"mainEvent");
RDebugUtils.currentLine=18350095;
 //BA.debugLineNum = 18350095;BA.debugLine="lbl.Tag = i";
_lbl.setTag((Object)(_i));
RDebugUtils.currentLine=18350096;
 //BA.debugLineNum = 18350096;BA.debugLine="lbl.Text = ev.Get(\"Title\")";
_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=18350097;
 //BA.debugLineNum = 18350097;BA.debugLine="lbl.Gravity = Gravity.CENTER_vertical";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=18350098;
 //BA.debugLineNum = 18350098;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=18350099;
 //BA.debugLineNum = 18350099;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
_lbl.setColor(_identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags")))));
RDebugUtils.currentLine=18350101;
 //BA.debugLineNum = 18350101;BA.debugLine="svEvents.Panel.AddView(lbl, 0, y, svEvents.Width";
mostCurrent._svevents.getPanel().AddView((android.view.View)(_lbl.getObject()),(int) (0),_y,mostCurrent._svevents.getWidth(),_rowheight);
RDebugUtils.currentLine=18350102;
 //BA.debugLineNum = 18350102;BA.debugLine="y = y+rowHeight";
_y = (int) (_y+_rowheight);
 }
};
RDebugUtils.currentLine=18350105;
 //BA.debugLineNum = 18350105;BA.debugLine="End Sub";
return "";
}
public static String  _day_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "day_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "day_btn_click", null));}
RDebugUtils.currentLine=19398656;
 //BA.debugLineNum = 19398656;BA.debugLine="Private Sub Day_btn_Click";
RDebugUtils.currentLine=19398657;
 //BA.debugLineNum = 19398657;BA.debugLine="menupanel.Visible = False";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19398658;
 //BA.debugLineNum = 19398658;BA.debugLine="End Sub";
return "";
}
public static String  _deleteevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deleteevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deleteevent_btn_click", null));}
RDebugUtils.currentLine=19988480;
 //BA.debugLineNum = 19988480;BA.debugLine="Private Sub DeleteEvent_btn_Click";
RDebugUtils.currentLine=19988481;
 //BA.debugLineNum = 19988481;BA.debugLine="deletepanel.Visible = True";
mostCurrent._deletepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=19988482;
 //BA.debugLineNum = 19988482;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=20512768;
 //BA.debugLineNum = 20512768;BA.debugLine="Private Sub deleteTLconfirm_btn_Click";
RDebugUtils.currentLine=20512769;
 //BA.debugLineNum = 20512769;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=20512770;
 //BA.debugLineNum = 20512770;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=20512772;
 //BA.debugLineNum = 20512772;BA.debugLine="For i = timeline.Size -1 To 0 Step -1";
{
final int step3 = -1;
final int limit3 = (int) (0);
_i = (int) (_timeline.getSize()-1) ;
for (;_i >= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=20512773;
 //BA.debugLineNum = 20512773;BA.debugLine="Dim ev As Map = timeline.Get(i)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_timeline.Get(_i)));
RDebugUtils.currentLine=20512774;
 //BA.debugLineNum = 20512774;BA.debugLine="If ev.Get(\"ID\") = currentevId Then";
if ((_ev.Get((Object)("ID"))).equals((Object)(_currentevid))) { 
RDebugUtils.currentLine=20512775;
 //BA.debugLineNum = 20512775;BA.debugLine="timeline.RemoveAt(i)";
_timeline.RemoveAt(_i);
RDebugUtils.currentLine=20512776;
 //BA.debugLineNum = 20512776;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=20512780;
 //BA.debugLineNum = 20512780;BA.debugLine="addTL_et.Text = \"\"";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=20512781;
 //BA.debugLineNum = 20512781;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20512782;
 //BA.debugLineNum = 20512782;BA.debugLine="addEventTL_panel.Visible = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20512783;
 //BA.debugLineNum = 20512783;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=20512784;
 //BA.debugLineNum = 20512784;BA.debugLine="UpdateTimeLine";
_updatetimeline();
RDebugUtils.currentLine=20512785;
 //BA.debugLineNum = 20512785;BA.debugLine="End Sub";
return "";
}
public static String  _deletetlevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletetlevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletetlevent_btn_click", null));}
RDebugUtils.currentLine=20381696;
 //BA.debugLineNum = 20381696;BA.debugLine="Private Sub deleteTLevent_btn_Click";
RDebugUtils.currentLine=20381697;
 //BA.debugLineNum = 20381697;BA.debugLine="deleteTLevent_confirmationpanel.Visible = True";
mostCurrent._deletetlevent_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=20381698;
 //BA.debugLineNum = 20381698;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18612224;
 //BA.debugLineNum = 18612224;BA.debugLine="Sub DrawHourLabels";
RDebugUtils.currentLine=18612227;
 //BA.debugLineNum = 18612227;BA.debugLine="Svtimeline.Panel.RemoveAllViews";
mostCurrent._svtimeline.getPanel().RemoveAllViews();
RDebugUtils.currentLine=18612229;
 //BA.debugLineNum = 18612229;BA.debugLine="Dim rowh As Int = 60dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=18612230;
 //BA.debugLineNum = 18612230;BA.debugLine="Svtimeline.Panel.Height = 24 * rowh";
mostCurrent._svtimeline.getPanel().setHeight((int) (24*_rowh));
RDebugUtils.currentLine=18612233;
 //BA.debugLineNum = 18612233;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=18612234;
 //BA.debugLineNum = 18612234;BA.debugLine="For h = 0 To 23";
{
final int step5 = 1;
final int limit5 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit5 ;_h = _h + step5 ) {
RDebugUtils.currentLine=18612235;
 //BA.debugLineNum = 18612235;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=18612236;
 //BA.debugLineNum = 18612236;BA.debugLine="p.Initialize(\"hour\")";
_p.Initialize(mostCurrent.activityBA,"hour");
RDebugUtils.currentLine=18612237;
 //BA.debugLineNum = 18612237;BA.debugLine="p.Tag = h";
_p.setTag((Object)(_h));
RDebugUtils.currentLine=18612238;
 //BA.debugLineNum = 18612238;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimelin";
mostCurrent._svtimeline.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) (_h*_rowh),mostCurrent._svtimeline.getWidth(),_rowh);
RDebugUtils.currentLine=18612240;
 //BA.debugLineNum = 18612240;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=18612241;
 //BA.debugLineNum = 18612241;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=18612242;
 //BA.debugLineNum = 18612242;BA.debugLine="lbl.Text = GetTimeString(h)";
_lbl.setText(BA.ObjectToCharSequence(_gettimestring(_h)));
RDebugUtils.currentLine=18612243;
 //BA.debugLineNum = 18612243;BA.debugLine="lbl.TextColor = Colors.DarkGray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=18612244;
 //BA.debugLineNum = 18612244;BA.debugLine="lbl.Gravity = Gravity.left";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=18612245;
 //BA.debugLineNum = 18612245;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
_p.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),_rowh);
 }
};
 }else {
RDebugUtils.currentLine=18612248;
 //BA.debugLineNum = 18612248;BA.debugLine="For h = 0 To 23";
{
final int step18 = 1;
final int limit18 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit18 ;_h = _h + step18 ) {
RDebugUtils.currentLine=18612249;
 //BA.debugLineNum = 18612249;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=18612250;
 //BA.debugLineNum = 18612250;BA.debugLine="p.Initialize(\"hour\")";
_p.Initialize(mostCurrent.activityBA,"hour");
RDebugUtils.currentLine=18612251;
 //BA.debugLineNum = 18612251;BA.debugLine="p.Tag = h";
_p.setTag((Object)(_h));
RDebugUtils.currentLine=18612252;
 //BA.debugLineNum = 18612252;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimeli";
mostCurrent._svtimeline.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) (_h*_rowh),mostCurrent._svtimeline.getWidth(),_rowh);
RDebugUtils.currentLine=18612254;
 //BA.debugLineNum = 18612254;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=18612255;
 //BA.debugLineNum = 18612255;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=18612256;
 //BA.debugLineNum = 18612256;BA.debugLine="lbl.Text = GetTimeString(h)";
_lbl.setText(BA.ObjectToCharSequence(_gettimestring(_h)));
RDebugUtils.currentLine=18612257;
 //BA.debugLineNum = 18612257;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=18612258;
 //BA.debugLineNum = 18612258;BA.debugLine="lbl.Gravity = Gravity.left";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=18612259;
 //BA.debugLineNum = 18612259;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
_p.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),_rowh);
 }
};
 };
RDebugUtils.currentLine=18612264;
 //BA.debugLineNum = 18612264;BA.debugLine="End Sub";
return "";
}
public static String  _gettimestring(int _h) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "gettimestring", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "gettimestring", new Object[] {_h}));}
int _num = 0;
String _ampm = "";
RDebugUtils.currentLine=18677760;
 //BA.debugLineNum = 18677760;BA.debugLine="Sub GetTimeString (h As Int) As String";
RDebugUtils.currentLine=18677761;
 //BA.debugLineNum = 18677761;BA.debugLine="Dim num As Int";
_num = 0;
RDebugUtils.currentLine=18677762;
 //BA.debugLineNum = 18677762;BA.debugLine="Dim ampm As String";
_ampm = "";
RDebugUtils.currentLine=18677763;
 //BA.debugLineNum = 18677763;BA.debugLine="If h = 0 Then";
if (_h==0) { 
RDebugUtils.currentLine=18677764;
 //BA.debugLineNum = 18677764;BA.debugLine="num = 12";
_num = (int) (12);
RDebugUtils.currentLine=18677765;
 //BA.debugLineNum = 18677765;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else 
{RDebugUtils.currentLine=18677766;
 //BA.debugLineNum = 18677766;BA.debugLine="Else if h = 12 Then";
if (_h==12) { 
RDebugUtils.currentLine=18677767;
 //BA.debugLineNum = 18677767;BA.debugLine="num = h";
_num = _h;
RDebugUtils.currentLine=18677768;
 //BA.debugLineNum = 18677768;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 }else 
{RDebugUtils.currentLine=18677769;
 //BA.debugLineNum = 18677769;BA.debugLine="Else if h > 12 Then";
if (_h>12) { 
RDebugUtils.currentLine=18677770;
 //BA.debugLineNum = 18677770;BA.debugLine="num = h - 12";
_num = (int) (_h-12);
RDebugUtils.currentLine=18677771;
 //BA.debugLineNum = 18677771;BA.debugLine="If num = 12 Then";
if (_num==12) { 
RDebugUtils.currentLine=18677772;
 //BA.debugLineNum = 18677772;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else {
RDebugUtils.currentLine=18677774;
 //BA.debugLineNum = 18677774;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 };
 }else {
RDebugUtils.currentLine=18677778;
 //BA.debugLineNum = 18677778;BA.debugLine="num = h";
_num = _h;
RDebugUtils.currentLine=18677779;
 //BA.debugLineNum = 18677779;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }}}
;
RDebugUtils.currentLine=18677782;
 //BA.debugLineNum = 18677782;BA.debugLine="Return num & \":00\" & ampm";
if (true) return BA.NumberToString(_num)+":00"+_ampm;
RDebugUtils.currentLine=18677783;
 //BA.debugLineNum = 18677783;BA.debugLine="End Sub";
return "";
}
public static int  _identifycolor(String _typeofevent) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "identifycolor", false))
	 {return ((Integer) Debug.delegate(mostCurrent.activityBA, "identifycolor", new Object[] {_typeofevent}));}
int _mycolor = 0;
RDebugUtils.currentLine=18481152;
 //BA.debugLineNum = 18481152;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
RDebugUtils.currentLine=18481153;
 //BA.debugLineNum = 18481153;BA.debugLine="Dim mycolor As Int";
_mycolor = 0;
RDebugUtils.currentLine=18481154;
 //BA.debugLineNum = 18481154;BA.debugLine="If typeofevent = \"Task\" Then";
if ((_typeofevent).equals("Task")) { 
RDebugUtils.currentLine=18481155;
 //BA.debugLineNum = 18481155;BA.debugLine="mycolor = Colors.ARGB(255, 0, 191, 255)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (0),(int) (191),(int) (255));
 }else 
{RDebugUtils.currentLine=18481156;
 //BA.debugLineNum = 18481156;BA.debugLine="Else if typeofevent = \"Event\" Then";
if ((_typeofevent).equals("Event")) { 
RDebugUtils.currentLine=18481157;
 //BA.debugLineNum = 18481157;BA.debugLine="mycolor = Colors.ARGB(255, 152, 255, 152)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (152),(int) (255),(int) (152));
 }else 
{RDebugUtils.currentLine=18481158;
 //BA.debugLineNum = 18481158;BA.debugLine="Else if typeofevent = \"Birthday\" Then";
if ((_typeofevent).equals("Birthday")) { 
RDebugUtils.currentLine=18481159;
 //BA.debugLineNum = 18481159;BA.debugLine="mycolor = Colors.ARGB(255, 255, 182, 193)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (182),(int) (193));
 }else 
{RDebugUtils.currentLine=18481160;
 //BA.debugLineNum = 18481160;BA.debugLine="Else if typeofevent = \"OOO\" Then";
if ((_typeofevent).equals("OOO")) { 
RDebugUtils.currentLine=18481161;
 //BA.debugLineNum = 18481161;BA.debugLine="mycolor = Colors.ARGB(255, 255, 215, 0)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (215),(int) (0));
 }}}}
;
RDebugUtils.currentLine=18481163;
 //BA.debugLineNum = 18481163;BA.debugLine="Return mycolor";
if (true) return _mycolor;
RDebugUtils.currentLine=18481164;
 //BA.debugLineNum = 18481164;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18743296;
 //BA.debugLineNum = 18743296;BA.debugLine="Sub DrawTimelineEvents";
RDebugUtils.currentLine=18743297;
 //BA.debugLineNum = 18743297;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate)))) { 
if (true) return "";};
RDebugUtils.currentLine=18743299;
 //BA.debugLineNum = 18743299;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=18743300;
 //BA.debugLineNum = 18743300;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=18743302;
 //BA.debugLineNum = 18743302;BA.debugLine="Dim rowh As Int = 60dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=18743304;
 //BA.debugLineNum = 18743304;BA.debugLine="For h = 0 To 23";
{
final int step5 = 1;
final int limit5 = (int) (23);
_h = (int) (0) ;
for (;_h <= limit5 ;_h = _h + step5 ) {
RDebugUtils.currentLine=18743305;
 //BA.debugLineNum = 18743305;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetVie";
_hourpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_hourpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._svtimeline.getPanel().GetView(_h).getObject()));
RDebugUtils.currentLine=18743307;
 //BA.debugLineNum = 18743307;BA.debugLine="If hourPanel.NumberOfViews > 1 Then";
if (_hourpanel.getNumberOfViews()>1) { 
RDebugUtils.currentLine=18743308;
 //BA.debugLineNum = 18743308;BA.debugLine="hourPanel.RemoveViewAt(1)";
_hourpanel.RemoveViewAt((int) (1));
 };
 }
};
RDebugUtils.currentLine=18743312;
 //BA.debugLineNum = 18743312;BA.debugLine="For Each ev  As Map In timeline";
_ev = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group11 = _timeline;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group11.Get(index11)));
RDebugUtils.currentLine=18743313;
 //BA.debugLineNum = 18743313;BA.debugLine="Dim starth As Int = ev.Get(\"Start\")";
_starth = (int)(BA.ObjectToNumber(_ev.Get((Object)("Start"))));
RDebugUtils.currentLine=18743314;
 //BA.debugLineNum = 18743314;BA.debugLine="Dim endh As Int = ev.Get(\"End\")";
_endh = (int)(BA.ObjectToNumber(_ev.Get((Object)("End"))));
RDebugUtils.currentLine=18743315;
 //BA.debugLineNum = 18743315;BA.debugLine="Dim title As String = ev.Get(\"Title\")";
_title = BA.ObjectToString(_ev.Get((Object)("Title")));
RDebugUtils.currentLine=18743316;
 //BA.debugLineNum = 18743316;BA.debugLine="Dim color As Int = IdentifyColor(ev.Get(\"Tags\"))";
_color = _identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags"))));
RDebugUtils.currentLine=18743317;
 //BA.debugLineNum = 18743317;BA.debugLine="For h = starth To endh - 1";
{
final int step16 = 1;
final int limit16 = (int) (_endh-1);
_h = _starth ;
for (;_h <= limit16 ;_h = _h + step16 ) {
RDebugUtils.currentLine=18743318;
 //BA.debugLineNum = 18743318;BA.debugLine="If h >= 0 And h <= 23 Then";
if (_h>=0 && _h<=23) { 
RDebugUtils.currentLine=18743319;
 //BA.debugLineNum = 18743319;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetV";
_hourpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_hourpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._svtimeline.getPanel().GetView(_h).getObject()));
RDebugUtils.currentLine=18743320;
 //BA.debugLineNum = 18743320;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=18743321;
 //BA.debugLineNum = 18743321;BA.debugLine="lbl.initialize(\"TimelineEvent\")";
_lbl.Initialize(mostCurrent.activityBA,"TimelineEvent");
RDebugUtils.currentLine=18743322;
 //BA.debugLineNum = 18743322;BA.debugLine="lbl.Tag = ev";
_lbl.setTag((Object)(_ev.getObject()));
RDebugUtils.currentLine=18743323;
 //BA.debugLineNum = 18743323;BA.debugLine="lbl.Text = title";
_lbl.setText(BA.ObjectToCharSequence(_title));
RDebugUtils.currentLine=18743324;
 //BA.debugLineNum = 18743324;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=18743325;
 //BA.debugLineNum = 18743325;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=18743326;
 //BA.debugLineNum = 18743326;BA.debugLine="lbl.Color = color";
_lbl.setColor(_color);
RDebugUtils.currentLine=18743327;
 //BA.debugLineNum = 18743327;BA.debugLine="hourPanel.AddView(lbl, 65dip, 0, hourPanel.Wid";
_hourpanel.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),(int) (0),(int) (_hourpanel.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70))),_rowh);
 };
 }
};
 }
};
RDebugUtils.currentLine=18743332;
 //BA.debugLineNum = 18743332;BA.debugLine="End Sub";
return "";
}
public static String  _editeventinfo_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "editeventinfo_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "editeventinfo_btn_click", null));}
RDebugUtils.currentLine=19857408;
 //BA.debugLineNum = 19857408;BA.debugLine="Private Sub editeventinfo_btn_Click";
RDebugUtils.currentLine=19857409;
 //BA.debugLineNum = 19857409;BA.debugLine="EditInfoPanel.Visible = True";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=19857410;
 //BA.debugLineNum = 19857410;BA.debugLine="editTitle_et.text = currenttaggedEvent.Get(\"Title";
mostCurrent._edittitle_et.setText(BA.ObjectToCharSequence(mostCurrent._currenttaggedevent.Get((Object)("Title"))));
RDebugUtils.currentLine=19857411;
 //BA.debugLineNum = 19857411;BA.debugLine="editDescription_et.Text = currenttaggedEvent.Get(";
mostCurrent._editdescription_et.setText(BA.ObjectToCharSequence(mostCurrent._currenttaggedevent.Get((Object)("Description"))));
RDebugUtils.currentLine=19857412;
 //BA.debugLineNum = 19857412;BA.debugLine="End Sub";
return "";
}
public static String  _eventrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "eventrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "eventrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=20709376;
 //BA.debugLineNum = 20709376;BA.debugLine="Private Sub eventrb_CheckedChange(Checked As Boole";
RDebugUtils.currentLine=20709377;
 //BA.debugLineNum = 20709377;BA.debugLine="eventtype = \"Event\"";
mostCurrent._eventtype = "Event";
RDebugUtils.currentLine=20709378;
 //BA.debugLineNum = 20709378;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18874368;
 //BA.debugLineNum = 18874368;BA.debugLine="Sub hour_click";
RDebugUtils.currentLine=18874369;
 //BA.debugLineNum = 18874369;BA.debugLine="Dim p As Panel =  Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=18874370;
 //BA.debugLineNum = 18874370;BA.debugLine="Dim tappedIndex = p.tag";
_tappedindex = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=18874371;
 //BA.debugLineNum = 18874371;BA.debugLine="timeIndex = p.tag";
_timeindex = (int)(BA.ObjectToNumber(_p.getTag()));
RDebugUtils.currentLine=18874373;
 //BA.debugLineNum = 18874373;BA.debugLine="addEventTL_panel.Visible  =True";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=18874375;
 //BA.debugLineNum = 18874375;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18874376;
 //BA.debugLineNum = 18874376;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate))) { 
RDebugUtils.currentLine=18874377;
 //BA.debugLineNum = 18874377;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 }else {
RDebugUtils.currentLine=18874379;
 //BA.debugLineNum = 18874379;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
RDebugUtils.currentLine=18874381;
 //BA.debugLineNum = 18874381;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=18874382;
 //BA.debugLineNum = 18874382;BA.debugLine="If eventmap.containskey(\"Timeline\") Then";
if (_eventmap.ContainsKey((Object)("Timeline"))) { 
RDebugUtils.currentLine=18874383;
 //BA.debugLineNum = 18874383;BA.debugLine="timeline = eventmap.Get(\"Timeline\")";
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
 }else {
RDebugUtils.currentLine=18874385;
 //BA.debugLineNum = 18874385;BA.debugLine="timeline.Initialize";
_timeline.Initialize();
RDebugUtils.currentLine=18874386;
 //BA.debugLineNum = 18874386;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
 };
RDebugUtils.currentLine=18874389;
 //BA.debugLineNum = 18874389;BA.debugLine="starttimelineSP.SelectedIndex = timeIndex";
mostCurrent._starttimelinesp.setSelectedIndex(_timeindex);
RDebugUtils.currentLine=18874390;
 //BA.debugLineNum = 18874390;BA.debugLine="endtimelineSP.SelectedIndex = Min(timeIndex +1, 2";
mostCurrent._endtimelinesp.setSelectedIndex((int) (anywheresoftware.b4a.keywords.Common.Min(_timeindex+1,24)));
RDebugUtils.currentLine=18874391;
 //BA.debugLineNum = 18874391;BA.debugLine="eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=18874395;
 //BA.debugLineNum = 18874395;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _mapinitializer() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mapinitializer", false))
	 {return ((anywheresoftware.b4a.objects.collections.Map) Debug.delegate(mostCurrent.activityBA, "mapinitializer", null));}
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
RDebugUtils.currentLine=18939904;
 //BA.debugLineNum = 18939904;BA.debugLine="Sub MapInitializer As Map";
RDebugUtils.currentLine=18939905;
 //BA.debugLineNum = 18939905;BA.debugLine="Dim eventmap As Map";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=18939907;
 //BA.debugLineNum = 18939907;BA.debugLine="eventmap.Initialize";
_eventmap.Initialize();
RDebugUtils.currentLine=18939908;
 //BA.debugLineNum = 18939908;BA.debugLine="Dim allevents As List";
_allevents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=18939909;
 //BA.debugLineNum = 18939909;BA.debugLine="allevents.initialize";
_allevents.Initialize();
RDebugUtils.currentLine=18939911;
 //BA.debugLineNum = 18939911;BA.debugLine="Dim timeline As List";
_timeline = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=18939912;
 //BA.debugLineNum = 18939912;BA.debugLine="timeline.initialize";
_timeline.Initialize();
RDebugUtils.currentLine=18939914;
 //BA.debugLineNum = 18939914;BA.debugLine="eventmap.Put(\"AllEvents\", allevents)";
_eventmap.Put((Object)("AllEvents"),(Object)(_allevents.getObject()));
RDebugUtils.currentLine=18939915;
 //BA.debugLineNum = 18939915;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
_eventmap.Put((Object)("Timeline"),(Object)(_timeline.getObject()));
RDebugUtils.currentLine=18939917;
 //BA.debugLineNum = 18939917;BA.debugLine="CalendarActivity.CalendarMap.Put(currentDate, eve";
mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)(_currentdate),(Object)(_eventmap.getObject()));
RDebugUtils.currentLine=18939919;
 //BA.debugLineNum = 18939919;BA.debugLine="Return eventmap";
if (true) return _eventmap;
RDebugUtils.currentLine=18939920;
 //BA.debugLineNum = 18939920;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18415616;
 //BA.debugLineNum = 18415616;BA.debugLine="Sub mainEvent_click";
RDebugUtils.currentLine=18415617;
 //BA.debugLineNum = 18415617;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)((_currentdate)))));
RDebugUtils.currentLine=18415618;
 //BA.debugLineNum = 18415618;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=18415619;
 //BA.debugLineNum = 18415619;BA.debugLine="Dim lbl As Label = Sender";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=18415620;
 //BA.debugLineNum = 18415620;BA.debugLine="Dim ev As Map = allevents.get(lbl.Tag)";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_allevents.Get((int)(BA.ObjectToNumber(_lbl.getTag())))));
RDebugUtils.currentLine=18415621;
 //BA.debugLineNum = 18415621;BA.debugLine="currenttaggedEvent = ev";
mostCurrent._currenttaggedevent = _ev;
RDebugUtils.currentLine=18415622;
 //BA.debugLineNum = 18415622;BA.debugLine="currentIndex = lbl.tag";
_currentindex = (int)(BA.ObjectToNumber(_lbl.getTag()));
RDebugUtils.currentLine=18415624;
 //BA.debugLineNum = 18415624;BA.debugLine="eventInfo_panel.Visible = True";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=18415625;
 //BA.debugLineNum = 18415625;BA.debugLine="dateToday_lbl.Text = currentDate";
mostCurrent._datetoday_lbl.setText(BA.ObjectToCharSequence(_currentdate));
RDebugUtils.currentLine=18415626;
 //BA.debugLineNum = 18415626;BA.debugLine="eventTitle_lbl.Text = ev.get(\"Title\")";
mostCurrent._eventtitle_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=18415627;
 //BA.debugLineNum = 18415627;BA.debugLine="eventdescription_lbl.Text = ev.Get(\"Description\")";
mostCurrent._eventdescription_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Description"))));
RDebugUtils.currentLine=18415628;
 //BA.debugLineNum = 18415628;BA.debugLine="tags_lbl.text = ev.Get(\"Tags\")";
mostCurrent._tags_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Tags"))));
RDebugUtils.currentLine=18415630;
 //BA.debugLineNum = 18415630;BA.debugLine="End Sub";
return "";
}
public static String  _menu_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "menu_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "menu_btn_click", null));}
RDebugUtils.currentLine=19267584;
 //BA.debugLineNum = 19267584;BA.debugLine="Private Sub menu_btn_Click";
RDebugUtils.currentLine=19267585;
 //BA.debugLineNum = 19267585;BA.debugLine="menupanel.Visible =True";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=19267586;
 //BA.debugLineNum = 19267586;BA.debugLine="End Sub";
return "";
}
public static String  _month_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "month_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "month_btn_click", null));}
RDebugUtils.currentLine=19464192;
 //BA.debugLineNum = 19464192;BA.debugLine="Private Sub Month_btn_Click";
RDebugUtils.currentLine=19464193;
 //BA.debugLineNum = 19464193;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=19464194;
 //BA.debugLineNum = 19464194;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
RDebugUtils.currentLine=19464195;
 //BA.debugLineNum = 19464195;BA.debugLine="End Sub";
return "";
}
public static String  _ooo_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ooo_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ooo_btn_click", null));}
RDebugUtils.currentLine=19791872;
 //BA.debugLineNum = 19791872;BA.debugLine="Private Sub ooo_btn_Click";
RDebugUtils.currentLine=19791873;
 //BA.debugLineNum = 19791873;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19791874;
 //BA.debugLineNum = 19791874;BA.debugLine="add_events_module.eventtype = \"OOO\"";
mostCurrent._add_events_module._eventtype /*String*/  = "OOO";
RDebugUtils.currentLine=19791875;
 //BA.debugLineNum = 19791875;BA.debugLine="StartActivity(add_events_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_events_module.getObject()));
RDebugUtils.currentLine=19791876;
 //BA.debugLineNum = 19791876;BA.debugLine="End Sub";
return "";
}
public static String  _ooorb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ooorb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ooorb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=20578304;
 //BA.debugLineNum = 20578304;BA.debugLine="Private Sub ooorb_CheckedChange(Checked As Boolean";
RDebugUtils.currentLine=20578305;
 //BA.debugLineNum = 20578305;BA.debugLine="eventtype = \"OOO\"";
mostCurrent._eventtype = "OOO";
RDebugUtils.currentLine=20578306;
 //BA.debugLineNum = 20578306;BA.debugLine="End Sub";
return "";
}
public static String  _saveedit_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "saveedit_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "saveedit_btn_click", null));}
RDebugUtils.currentLine=20119552;
 //BA.debugLineNum = 20119552;BA.debugLine="Private Sub saveEdit_btn_Click";
RDebugUtils.currentLine=20119553;
 //BA.debugLineNum = 20119553;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
if (mostCurrent._starttimelinesp.getSelectedIndex()==mostCurrent._endtimelinesp.getSelectedIndex() || mostCurrent._starttimelinesp.getSelectedIndex()>mostCurrent._endtimelinesp.getSelectedIndex()) { 
RDebugUtils.currentLine=20119554;
 //BA.debugLineNum = 20119554;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Invalid Timeline"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20119555;
 //BA.debugLineNum = 20119555;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=20119558;
 //BA.debugLineNum = 20119558;BA.debugLine="If editTitle_et.text = \"\" Then";
if ((mostCurrent._edittitle_et.getText()).equals("")) { 
RDebugUtils.currentLine=20119559;
 //BA.debugLineNum = 20119559;BA.debugLine="MsgboxAsync(\"Event must have name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event must have name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=20119560;
 //BA.debugLineNum = 20119560;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=20119562;
 //BA.debugLineNum = 20119562;BA.debugLine="currenttaggedEvent.Put(\"Title\", editTitle_et.Text";
mostCurrent._currenttaggedevent.Put((Object)("Title"),(Object)(mostCurrent._edittitle_et.getText()));
RDebugUtils.currentLine=20119563;
 //BA.debugLineNum = 20119563;BA.debugLine="currenttaggedEvent.Put(\"Description\", editDescrip";
mostCurrent._currenttaggedevent.Put((Object)("Description"),(Object)(mostCurrent._editdescription_et.getText()));
RDebugUtils.currentLine=20119565;
 //BA.debugLineNum = 20119565;BA.debugLine="x_EventInfo_btn_Click";
_x_eventinfo_btn_click();
RDebugUtils.currentLine=20119566;
 //BA.debugLineNum = 20119566;BA.debugLine="eventTitle_lbl.text = editTitle_et.Text";
mostCurrent._eventtitle_lbl.setText(BA.ObjectToCharSequence(mostCurrent._edittitle_et.getText()));
RDebugUtils.currentLine=20119567;
 //BA.debugLineNum = 20119567;BA.debugLine="eventdescription_lbl.Text = editDescription_et.te";
mostCurrent._eventdescription_lbl.setText(BA.ObjectToCharSequence(mostCurrent._editdescription_et.getText()));
RDebugUtils.currentLine=20119568;
 //BA.debugLineNum = 20119568;BA.debugLine="eventInfo_panel.Visible = True";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=20119569;
 //BA.debugLineNum = 20119569;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=20119570;
 //BA.debugLineNum = 20119570;BA.debugLine="DrawMainEvents";
_drawmainevents();
RDebugUtils.currentLine=20119573;
 //BA.debugLineNum = 20119573;BA.debugLine="End Sub";
return "";
}
public static String  _x_eventinfo_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "x_eventinfo_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "x_eventinfo_btn_click", null));}
RDebugUtils.currentLine=19922944;
 //BA.debugLineNum = 19922944;BA.debugLine="Private Sub x_EventInfo_btn_Click";
RDebugUtils.currentLine=19922945;
 //BA.debugLineNum = 19922945;BA.debugLine="eventInfo_panel.Visible = False";
mostCurrent._eventinfo_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19922946;
 //BA.debugLineNum = 19922946;BA.debugLine="EditInfoPanel.visible = False";
mostCurrent._editinfopanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19922947;
 //BA.debugLineNum = 19922947;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=19005440;
 //BA.debugLineNum = 19005440;BA.debugLine="Private Sub saveTL_btn_Click";
RDebugUtils.currentLine=19005441;
 //BA.debugLineNum = 19005441;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
if (mostCurrent._starttimelinesp.getSelectedIndex()==mostCurrent._endtimelinesp.getSelectedIndex() || mostCurrent._starttimelinesp.getSelectedIndex()>mostCurrent._endtimelinesp.getSelectedIndex()) { 
RDebugUtils.currentLine=19005442;
 //BA.debugLineNum = 19005442;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Invalid Timeline"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=19005443;
 //BA.debugLineNum = 19005443;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=19005445;
 //BA.debugLineNum = 19005445;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
RDebugUtils.currentLine=19005446;
 //BA.debugLineNum = 19005446;BA.debugLine="If addTL_et.text = \"\" Then";
if ((mostCurrent._addtl_et.getText()).equals("")) { 
RDebugUtils.currentLine=19005447;
 //BA.debugLineNum = 19005447;BA.debugLine="MsgboxAsync(\"Event must have a name\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Event must have a name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=19005448;
 //BA.debugLineNum = 19005448;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=19005451;
 //BA.debugLineNum = 19005451;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .ContainsKey((Object)(_currentdate))) { 
RDebugUtils.currentLine=19005452;
 //BA.debugLineNum = 19005452;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_currentdate))));
 }else {
RDebugUtils.currentLine=19005454;
 //BA.debugLineNum = 19005454;BA.debugLine="eventmap = MapInitializer";
_eventmap = _mapinitializer();
 };
RDebugUtils.currentLine=19005456;
 //BA.debugLineNum = 19005456;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=19005457;
 //BA.debugLineNum = 19005457;BA.debugLine="Dim ev As Map";
_ev = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=19005458;
 //BA.debugLineNum = 19005458;BA.debugLine="ev.Initialize";
_ev.Initialize();
RDebugUtils.currentLine=19005460;
 //BA.debugLineNum = 19005460;BA.debugLine="ev.Put(\"ID\", DateTime.Now)";
_ev.Put((Object)("ID"),(Object)(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
RDebugUtils.currentLine=19005461;
 //BA.debugLineNum = 19005461;BA.debugLine="ev.Put(\"Title\", addTL_et.Text)";
_ev.Put((Object)("Title"),(Object)(mostCurrent._addtl_et.getText()));
RDebugUtils.currentLine=19005462;
 //BA.debugLineNum = 19005462;BA.debugLine="ev.Put(\"Start\", starttimelineSP.SelectedIndex)";
_ev.Put((Object)("Start"),(Object)(mostCurrent._starttimelinesp.getSelectedIndex()));
RDebugUtils.currentLine=19005463;
 //BA.debugLineNum = 19005463;BA.debugLine="ev.Put(\"End\", endtimelineSP.SelectedIndex)";
_ev.Put((Object)("End"),(Object)(mostCurrent._endtimelinesp.getSelectedIndex()));
RDebugUtils.currentLine=19005464;
 //BA.debugLineNum = 19005464;BA.debugLine="ev.Put(\"Tags\", eventtype)";
_ev.Put((Object)("Tags"),(Object)(mostCurrent._eventtype));
RDebugUtils.currentLine=19005467;
 //BA.debugLineNum = 19005467;BA.debugLine="For i = timeline.Size - 1 To 0 Step -1";
{
final int step23 = -1;
final int limit23 = (int) (0);
_i = (int) (_timeline.getSize()-1) ;
for (;_i >= limit23 ;_i = _i + step23 ) {
RDebugUtils.currentLine=19005468;
 //BA.debugLineNum = 19005468;BA.debugLine="Dim existing As Map = timeline.Get(i)";
_existing = new anywheresoftware.b4a.objects.collections.Map();
_existing = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_timeline.Get(_i)));
RDebugUtils.currentLine=19005469;
 //BA.debugLineNum = 19005469;BA.debugLine="Dim st As Int = existing.Get(\"Start\")";
_st = (int)(BA.ObjectToNumber(_existing.Get((Object)("Start"))));
RDebugUtils.currentLine=19005470;
 //BA.debugLineNum = 19005470;BA.debugLine="Dim en As Int = existing.Get(\"End\")";
_en = (int)(BA.ObjectToNumber(_existing.Get((Object)("End"))));
RDebugUtils.currentLine=19005473;
 //BA.debugLineNum = 19005473;BA.debugLine="If (starttimelineSP.SelectedIndex < en) And (end";
if ((mostCurrent._starttimelinesp.getSelectedIndex()<_en) && (mostCurrent._endtimelinesp.getSelectedIndex()>_st)) { 
RDebugUtils.currentLine=19005474;
 //BA.debugLineNum = 19005474;BA.debugLine="timeline.RemoveAt(i)";
_timeline.RemoveAt(_i);
RDebugUtils.currentLine=19005475;
 //BA.debugLineNum = 19005475;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=19005479;
 //BA.debugLineNum = 19005479;BA.debugLine="timeline.add(ev)";
_timeline.Add((Object)(_ev.getObject()));
RDebugUtils.currentLine=19005481;
 //BA.debugLineNum = 19005481;BA.debugLine="SaveCalendar";
_savecalendar();
RDebugUtils.currentLine=19005482;
 //BA.debugLineNum = 19005482;BA.debugLine="UpdateTimeLine";
_updatetimeline();
RDebugUtils.currentLine=19005483;
 //BA.debugLineNum = 19005483;BA.debugLine="addEventTL_panel.Visible  = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=19005485;
 //BA.debugLineNum = 19005485;BA.debugLine="End Sub";
return "";
}
public static String  _sched_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sched_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sched_btn_click", null));}
RDebugUtils.currentLine=19333120;
 //BA.debugLineNum = 19333120;BA.debugLine="Private Sub sched_btn_Click";
RDebugUtils.currentLine=19333121;
 //BA.debugLineNum = 19333121;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=19333122;
 //BA.debugLineNum = 19333122;BA.debugLine="StartActivity(Schedule_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._schedule_module.getObject()));
RDebugUtils.currentLine=19333123;
 //BA.debugLineNum = 19333123;BA.debugLine="End Sub";
return "";
}
public static String  _taskrb_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskrb_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskrb_checkedchange", new Object[] {_checked}));}
RDebugUtils.currentLine=20774912;
 //BA.debugLineNum = 20774912;BA.debugLine="Private Sub taskrb_CheckedChange(Checked As Boolea";
RDebugUtils.currentLine=20774913;
 //BA.debugLineNum = 20774913;BA.debugLine="eventtype = \"Task\"";
mostCurrent._eventtype = "Task";
RDebugUtils.currentLine=20774914;
 //BA.debugLineNum = 20774914;BA.debugLine="End Sub";
return "";
}
public static String  _timelineevent_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timelineevent_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timelineevent_click", null));}
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
RDebugUtils.currentLine=18808832;
 //BA.debugLineNum = 18808832;BA.debugLine="Sub timelineEvent_Click";
RDebugUtils.currentLine=18808833;
 //BA.debugLineNum = 18808833;BA.debugLine="Dim lbl As Label = Sender";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=18808834;
 //BA.debugLineNum = 18808834;BA.debugLine="Dim ev As Map = lbl.Tag";
_ev = new anywheresoftware.b4a.objects.collections.Map();
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_lbl.getTag()));
RDebugUtils.currentLine=18808835;
 //BA.debugLineNum = 18808835;BA.debugLine="currenttaggedEvent = ev";
mostCurrent._currenttaggedevent = _ev;
RDebugUtils.currentLine=18808836;
 //BA.debugLineNum = 18808836;BA.debugLine="currentevId = ev.Get(\"ID\")";
_currentevid = BA.ObjectToLongNumber(_ev.Get((Object)("ID")));
RDebugUtils.currentLine=18808839;
 //BA.debugLineNum = 18808839;BA.debugLine="addEventTL_panel.Visible = True";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=18808842;
 //BA.debugLineNum = 18808842;BA.debugLine="addTL_et.Text = ev.Get(\"Title\")";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=18808843;
 //BA.debugLineNum = 18808843;BA.debugLine="starttimelineSP.SelectedIndex = ev.Get(\"Start\")";
mostCurrent._starttimelinesp.setSelectedIndex((int)(BA.ObjectToNumber(_ev.Get((Object)("Start")))));
RDebugUtils.currentLine=18808844;
 //BA.debugLineNum = 18808844;BA.debugLine="endtimelineSP.SelectedIndex = ev.Get(\"End\")";
mostCurrent._endtimelinesp.setSelectedIndex((int)(BA.ObjectToNumber(_ev.Get((Object)("End")))));
RDebugUtils.currentLine=18808847;
 //BA.debugLineNum = 18808847;BA.debugLine="Select Case ev.Get(\"Tags\")";
switch (BA.switchObjectToInt(_ev.Get((Object)("Tags")),(Object)("Task"),(Object)("Event"),(Object)("Birthday"),(Object)("OOO"))) {
case 0: {
RDebugUtils.currentLine=18808848;
 //BA.debugLineNum = 18808848;BA.debugLine="Case \"Task\": taskrb.Checked = True";
mostCurrent._taskrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 1: {
RDebugUtils.currentLine=18808849;
 //BA.debugLineNum = 18808849;BA.debugLine="Case \"Event\": eventrb.Checked = True";
mostCurrent._eventrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 2: {
RDebugUtils.currentLine=18808850;
 //BA.debugLineNum = 18808850;BA.debugLine="Case \"Birthday\": birthdayrb.Checked = True";
mostCurrent._birthdayrb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 3: {
RDebugUtils.currentLine=18808851;
 //BA.debugLineNum = 18808851;BA.debugLine="Case \"OOO\": ooorb.Checked = True";
mostCurrent._ooorb.setChecked(anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
RDebugUtils.currentLine=18808853;
 //BA.debugLineNum = 18808853;BA.debugLine="End Sub";
return "";
}
public static String  _x_tlevent_btn_click() throws Exception{
RDebugUtils.currentModule="day_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "x_tlevent_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "x_tlevent_btn_click", null));}
RDebugUtils.currentLine=20316160;
 //BA.debugLineNum = 20316160;BA.debugLine="Private Sub x_TLevent_btn_Click";
RDebugUtils.currentLine=20316161;
 //BA.debugLineNum = 20316161;BA.debugLine="addEventTL_panel.Visible = False";
mostCurrent._addeventtl_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=20316162;
 //BA.debugLineNum = 20316162;BA.debugLine="addTL_et.text = \"\"";
mostCurrent._addtl_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=20316163;
 //BA.debugLineNum = 20316163;BA.debugLine="End Sub";
return "";
}
}