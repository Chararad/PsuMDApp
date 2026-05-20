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

public class schedule_module extends Activity implements B4AActivity{
	public static schedule_module mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.schedule_module");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (schedule_module).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.schedule_module");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.schedule_module", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (schedule_module) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (schedule_module) Resume **");
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
		return schedule_module.class;
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
            BA.LogInfo("** Activity (schedule_module) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (schedule_module) Pause event (activity is not paused). **");
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
            schedule_module mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (schedule_module) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _menupanel = null;
public anywheresoftware.b4a.objects.ButtonWrapper _sched_btn = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _schedulesv = null;
public anywheresoftware.b4a.objects.LabelWrapper _noschedlabel = null;
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
public b4a.example.card_module _card_module = null;
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
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=32243712;
 //BA.debugLineNum = 32243712;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=32243714;
 //BA.debugLineNum = 32243714;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=32243715;
 //BA.debugLineNum = 32243715;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=32243716;
 //BA.debugLineNum = 32243716;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=32243718;
 //BA.debugLineNum = 32243718;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=32243719;
 //BA.debugLineNum = 32243719;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Schedule_ModuleLayout",mostCurrent.activityBA);
RDebugUtils.currentLine=32243720;
 //BA.debugLineNum = 32243720;BA.debugLine="cd.Initialize(Colors.ARGB(125, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (125),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=32243721;
 //BA.debugLineNum = 32243721;BA.debugLine="sched_btn.Background = cd";
mostCurrent._sched_btn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=32243722;
 //BA.debugLineNum = 32243722;BA.debugLine="sched_btn.TextColor = Colors.White";
mostCurrent._sched_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=32243724;
 //BA.debugLineNum = 32243724;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayoutDark";
mostCurrent._activity.LoadLayout("Schedule_ModuleLayoutDark",mostCurrent.activityBA);
RDebugUtils.currentLine=32243725;
 //BA.debugLineNum = 32243725;BA.debugLine="cd.Initialize(Colors.ARGB(125, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (125),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=32243726;
 //BA.debugLineNum = 32243726;BA.debugLine="sched_btn.Background = cd";
mostCurrent._sched_btn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=32243727;
 //BA.debugLineNum = 32243727;BA.debugLine="sched_btn.TextColor = Colors.White";
mostCurrent._sched_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=32243730;
 //BA.debugLineNum = 32243730;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=32243731;
 //BA.debugLineNum = 32243731;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Schedule_ModuleLayout2",mostCurrent.activityBA);
RDebugUtils.currentLine=32243732;
 //BA.debugLineNum = 32243732;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Pink .png").getObject()));
RDebugUtils.currentLine=32243733;
 //BA.debugLineNum = 32243733;BA.debugLine="sched_btn.Background = bd";
mostCurrent._sched_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=32243734;
 //BA.debugLineNum = 32243734;BA.debugLine="sched_btn.TextColor = Colors.White";
mostCurrent._sched_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=32243736;
 //BA.debugLineNum = 32243736;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayoutDark";
mostCurrent._activity.LoadLayout("Schedule_ModuleLayoutDark2",mostCurrent.activityBA);
RDebugUtils.currentLine=32243737;
 //BA.debugLineNum = 32243737;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"pink";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pink Skeumorphic Button DARK.png").getObject()));
RDebugUtils.currentLine=32243738;
 //BA.debugLineNum = 32243738;BA.debugLine="sched_btn.Background = bd";
mostCurrent._sched_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=32243739;
 //BA.debugLineNum = 32243739;BA.debugLine="sched_btn.TextColor = Colors.White";
mostCurrent._sched_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=32243742;
 //BA.debugLineNum = 32243742;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=32243743;
 //BA.debugLineNum = 32243743;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Schedule_ModuleLayout3",mostCurrent.activityBA);
RDebugUtils.currentLine=32243744;
 //BA.debugLineNum = 32243744;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
RDebugUtils.currentLine=32243745;
 //BA.debugLineNum = 32243745;BA.debugLine="sched_btn.Background = bd";
mostCurrent._sched_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=32243746;
 //BA.debugLineNum = 32243746;BA.debugLine="sched_btn.TextColor = Colors.White";
mostCurrent._sched_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=32243748;
 //BA.debugLineNum = 32243748;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayoutDark";
mostCurrent._activity.LoadLayout("Schedule_ModuleLayoutDark3",mostCurrent.activityBA);
RDebugUtils.currentLine=32243749;
 //BA.debugLineNum = 32243749;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtnd.png").getObject()));
RDebugUtils.currentLine=32243750;
 //BA.debugLineNum = 32243750;BA.debugLine="sched_btn.Background = bd";
mostCurrent._sched_btn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=32243751;
 //BA.debugLineNum = 32243751;BA.debugLine="sched_btn.TextColor = Colors.White";
mostCurrent._sched_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
RDebugUtils.currentLine=32243755;
 //BA.debugLineNum = 32243755;BA.debugLine="DrawSchedule";
_drawschedule();
RDebugUtils.currentLine=32243757;
 //BA.debugLineNum = 32243757;BA.debugLine="End Sub";
return "";
}
public static String  _drawschedule() throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "drawschedule", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "drawschedule", null));}
int _y = 0;
anywheresoftware.b4a.objects.collections.List _sorteddates = null;
String _keys = "";
String _date = "";
anywheresoftware.b4a.objects.collections.Map _eventmap = null;
anywheresoftware.b4a.objects.collections.List _allevents = null;
anywheresoftware.b4a.objects.collections.List _timeline = null;
anywheresoftware.b4a.objects.LabelWrapper _lbldate = null;
anywheresoftware.b4a.objects.collections.Map _ev = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _st = 0;
int _en = 0;
RDebugUtils.currentLine=32309248;
 //BA.debugLineNum = 32309248;BA.debugLine="Sub DrawSchedule";
RDebugUtils.currentLine=32309249;
 //BA.debugLineNum = 32309249;BA.debugLine="scheduleSV.Panel.RemoveAllViews";
mostCurrent._schedulesv.getPanel().RemoveAllViews();
RDebugUtils.currentLine=32309251;
 //BA.debugLineNum = 32309251;BA.debugLine="Dim y As Int = 0";
_y = (int) (0);
RDebugUtils.currentLine=32309252;
 //BA.debugLineNum = 32309252;BA.debugLine="Dim sortedDates As List";
_sorteddates = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=32309253;
 //BA.debugLineNum = 32309253;BA.debugLine="sortedDates.Initialize";
_sorteddates.Initialize();
RDebugUtils.currentLine=32309255;
 //BA.debugLineNum = 32309255;BA.debugLine="If CalendarActivity.CalendarMap.Size = 0 Then";
if (mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .getSize()==0) { 
RDebugUtils.currentLine=32309256;
 //BA.debugLineNum = 32309256;BA.debugLine="noschedlabel.Text = \"No Schedule\"";
mostCurrent._noschedlabel.setText(BA.ObjectToCharSequence("No Schedule"));
 };
RDebugUtils.currentLine=32309259;
 //BA.debugLineNum = 32309259;BA.debugLine="For Each keys As String In CalendarActivity.Calen";
{
final anywheresoftware.b4a.BA.IterableList group8 = mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Keys();
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_keys = BA.ObjectToString(group8.Get(index8));
RDebugUtils.currentLine=32309260;
 //BA.debugLineNum = 32309260;BA.debugLine="sortedDates.Add(keys)";
_sorteddates.Add((Object)(_keys));
RDebugUtils.currentLine=32309261;
 //BA.debugLineNum = 32309261;BA.debugLine="Log(keys)";
anywheresoftware.b4a.keywords.Common.LogImpl("132309261",_keys,0);
 }
};
RDebugUtils.currentLine=32309264;
 //BA.debugLineNum = 32309264;BA.debugLine="sortedDates.Sort(True)";
_sorteddates.Sort(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=32309266;
 //BA.debugLineNum = 32309266;BA.debugLine="For Each date As String In sortedDates";
{
final anywheresoftware.b4a.BA.IterableList group13 = _sorteddates;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_date = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=32309267;
 //BA.debugLineNum = 32309267;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarM";
_eventmap = new anywheresoftware.b4a.objects.collections.Map();
_eventmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._calendaractivity._calendarmap /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(_date))));
RDebugUtils.currentLine=32309268;
 //BA.debugLineNum = 32309268;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\"";
_allevents = new anywheresoftware.b4a.objects.collections.List();
_allevents = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("AllEvents"))));
RDebugUtils.currentLine=32309269;
 //BA.debugLineNum = 32309269;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
_timeline = new anywheresoftware.b4a.objects.collections.List();
_timeline = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_eventmap.Get((Object)("Timeline"))));
RDebugUtils.currentLine=32309271;
 //BA.debugLineNum = 32309271;BA.debugLine="Dim lbldate As Label";
_lbldate = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=32309272;
 //BA.debugLineNum = 32309272;BA.debugLine="lbldate.initialize(\"\")";
_lbldate.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=32309273;
 //BA.debugLineNum = 32309273;BA.debugLine="lbldate.Text = SetDate(date)";
_lbldate.setText(BA.ObjectToCharSequence(_setdate(_date)));
RDebugUtils.currentLine=32309274;
 //BA.debugLineNum = 32309274;BA.debugLine="lbldate.TextSize = 16";
_lbldate.setTextSize((float) (16));
RDebugUtils.currentLine=32309275;
 //BA.debugLineNum = 32309275;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=32309277;
 //BA.debugLineNum = 32309277;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=32309278;
 //BA.debugLineNum = 32309278;BA.debugLine="lbldate.Color = Colors.ARGB(120, 23, 20, 36)";
_lbldate.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (23),(int) (20),(int) (36)));
RDebugUtils.currentLine=32309279;
 //BA.debugLineNum = 32309279;BA.debugLine="lbldate.TextColor = Colors.Cyan";
_lbldate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 }else {
RDebugUtils.currentLine=32309281;
 //BA.debugLineNum = 32309281;BA.debugLine="lbldate.Color = Colors.ARGB(120, 23, 20, 36)";
_lbldate.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (23),(int) (20),(int) (36)));
RDebugUtils.currentLine=32309282;
 //BA.debugLineNum = 32309282;BA.debugLine="lbldate.TextColor = Colors.Cyan";
_lbldate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 };
 break; }
case 1: {
RDebugUtils.currentLine=32309285;
 //BA.debugLineNum = 32309285;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=32309286;
 //BA.debugLineNum = 32309286;BA.debugLine="lbldate.Color = Colors.ARGB(120, 16, 26, 95)";
_lbldate.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (16),(int) (26),(int) (95)));
RDebugUtils.currentLine=32309287;
 //BA.debugLineNum = 32309287;BA.debugLine="lbldate.TextColor = Colors.White";
_lbldate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=32309289;
 //BA.debugLineNum = 32309289;BA.debugLine="lbldate.Color = Colors.ARGB(200, 137, 162, 18";
_lbldate.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (137),(int) (162),(int) (185)));
RDebugUtils.currentLine=32309290;
 //BA.debugLineNum = 32309290;BA.debugLine="lbldate.TextColor = Colors.White";
_lbldate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=32309293;
 //BA.debugLineNum = 32309293;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=32309294;
 //BA.debugLineNum = 32309294;BA.debugLine="lbldate.Color = Colors.ARGB(120, 0, 0, 0)";
_lbldate.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (0),(int) (0),(int) (0)));
RDebugUtils.currentLine=32309295;
 //BA.debugLineNum = 32309295;BA.debugLine="lbldate.TextColor = Colors.Cyan";
_lbldate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 }else {
RDebugUtils.currentLine=32309297;
 //BA.debugLineNum = 32309297;BA.debugLine="lbldate.Color = Colors.ARGB(120, 255, 255, 25";
_lbldate.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=32309298;
 //BA.debugLineNum = 32309298;BA.debugLine="lbldate.TextColor = Colors.RGB(79, 46, 46)";
_lbldate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (79),(int) (46),(int) (46)));
 };
 break; }
}
;
RDebugUtils.currentLine=32309303;
 //BA.debugLineNum = 32309303;BA.debugLine="If allevents.Size = 0 And timeline.size = 0 Then";
if (_allevents.getSize()==0 && _timeline.getSize()==0) { 
RDebugUtils.currentLine=32309304;
 //BA.debugLineNum = 32309304;BA.debugLine="Continue";
if (true) continue;
 };
RDebugUtils.currentLine=32309307;
 //BA.debugLineNum = 32309307;BA.debugLine="scheduleSV.Panel.AddView(lbldate, 0, y, schedule";
mostCurrent._schedulesv.getPanel().AddView((android.view.View)(_lbldate.getObject()),(int) (0),_y,mostCurrent._schedulesv.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=32309308;
 //BA.debugLineNum = 32309308;BA.debugLine="y = y+ 40dip";
_y = (int) (_y+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=32309310;
 //BA.debugLineNum = 32309310;BA.debugLine="For Each ev As Map In allevents";
_ev = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group52 = _allevents;
final int groupLen52 = group52.getSize()
;int index52 = 0;
;
for (; index52 < groupLen52;index52++){
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group52.Get(index52)));
RDebugUtils.currentLine=32309311;
 //BA.debugLineNum = 32309311;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=32309312;
 //BA.debugLineNum = 32309312;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=32309313;
 //BA.debugLineNum = 32309313;BA.debugLine="lbl.Text = ev.Get(\"Title\")";
_lbl.setText(BA.ObjectToCharSequence(_ev.Get((Object)("Title"))));
RDebugUtils.currentLine=32309314;
 //BA.debugLineNum = 32309314;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
_lbl.setColor(_identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags")))));
RDebugUtils.currentLine=32309315;
 //BA.debugLineNum = 32309315;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=32309316;
 //BA.debugLineNum = 32309316;BA.debugLine="scheduleSV.Panel.AddView(lbl, 10dip, y, schedul";
mostCurrent._schedulesv.getPanel().AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_y,(int) (mostCurrent._schedulesv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=32309317;
 //BA.debugLineNum = 32309317;BA.debugLine="y = y + 40dip";
_y = (int) (_y+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 }
};
RDebugUtils.currentLine=32309321;
 //BA.debugLineNum = 32309321;BA.debugLine="For Each ev As Map In timeline";
_ev = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group61 = _timeline;
final int groupLen61 = group61.getSize()
;int index61 = 0;
;
for (; index61 < groupLen61;index61++){
_ev = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group61.Get(index61)));
RDebugUtils.currentLine=32309322;
 //BA.debugLineNum = 32309322;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=32309323;
 //BA.debugLineNum = 32309323;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=32309324;
 //BA.debugLineNum = 32309324;BA.debugLine="Dim st As Int = ev.Get(\"Start\")";
_st = (int)(BA.ObjectToNumber(_ev.Get((Object)("Start"))));
RDebugUtils.currentLine=32309325;
 //BA.debugLineNum = 32309325;BA.debugLine="Dim en As Int = ev.Get(\"End\")";
_en = (int)(BA.ObjectToNumber(_ev.Get((Object)("End"))));
RDebugUtils.currentLine=32309326;
 //BA.debugLineNum = 32309326;BA.debugLine="lbl.Text = ev.Get(\"Title\") & \" (\" & GetTimeStri";
_lbl.setText(BA.ObjectToCharSequence(BA.ObjectToString(_ev.Get((Object)("Title")))+" ("+_gettimestring(_st)+" - "+_gettimestring(_en)+")"));
RDebugUtils.currentLine=32309328;
 //BA.debugLineNum = 32309328;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
_lbl.setColor(_identifycolor(BA.ObjectToString(_ev.Get((Object)("Tags")))));
RDebugUtils.currentLine=32309329;
 //BA.debugLineNum = 32309329;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=32309330;
 //BA.debugLineNum = 32309330;BA.debugLine="scheduleSV.Panel.AddView(lbl, 10dip, y, schedul";
mostCurrent._schedulesv.getPanel().AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_y,(int) (mostCurrent._schedulesv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=32309331;
 //BA.debugLineNum = 32309331;BA.debugLine="y = y+40dip";
_y = (int) (_y+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 }
};
 }
};
RDebugUtils.currentLine=32309334;
 //BA.debugLineNum = 32309334;BA.debugLine="scheduleSV.Panel.Height = y";
mostCurrent._schedulesv.getPanel().setHeight(_y);
RDebugUtils.currentLine=32309336;
 //BA.debugLineNum = 32309336;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="schedule_module";
RDebugUtils.currentLine=32636928;
 //BA.debugLineNum = 32636928;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=32636930;
 //BA.debugLineNum = 32636930;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=32571392;
 //BA.debugLineNum = 32571392;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=32571394;
 //BA.debugLineNum = 32571394;BA.debugLine="End Sub";
return "";
}
public static String  _day_btn_click() throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "day_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "day_btn_click", null));}
int _currentyear = 0;
int _currentmonth = 0;
int _currentday = 0;
RDebugUtils.currentLine=32833536;
 //BA.debugLineNum = 32833536;BA.debugLine="Private Sub Day_btn_Click";
RDebugUtils.currentLine=32833537;
 //BA.debugLineNum = 32833537;BA.debugLine="Dim currentyear As Int = DateTime.GetYear(DateTim";
_currentyear = anywheresoftware.b4a.keywords.Common.DateTime.GetYear(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
RDebugUtils.currentLine=32833538;
 //BA.debugLineNum = 32833538;BA.debugLine="Dim currentmonth As Int = DateTime.GetMonth(DateT";
_currentmonth = anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
RDebugUtils.currentLine=32833539;
 //BA.debugLineNum = 32833539;BA.debugLine="Dim currentday As Int = DateTime.GetDayOfMonth(Da";
_currentday = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
RDebugUtils.currentLine=32833540;
 //BA.debugLineNum = 32833540;BA.debugLine="day_module.currentDate = currentyear & \"-\" & curr";
mostCurrent._day_module._currentdate /*String*/  = BA.NumberToString(_currentyear)+"-"+BA.NumberToString(_currentmonth)+"-"+BA.NumberToString(_currentday);
RDebugUtils.currentLine=32833541;
 //BA.debugLineNum = 32833541;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=32833542;
 //BA.debugLineNum = 32833542;BA.debugLine="StartActivity(day_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._day_module.getObject()));
RDebugUtils.currentLine=32833543;
 //BA.debugLineNum = 32833543;BA.debugLine="End Sub";
return "";
}
public static String  _setdate(String _tagdate) throws Exception{
RDebugUtils.currentModule="schedule_module";
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
RDebugUtils.currentLine=32505856;
 //BA.debugLineNum = 32505856;BA.debugLine="Sub SetDate(Tagdate As String) As String";
RDebugUtils.currentLine=32505858;
 //BA.debugLineNum = 32505858;BA.debugLine="Dim parts() As String = Regex.Split(\"-\", Tagdate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_tagdate);
RDebugUtils.currentLine=32505859;
 //BA.debugLineNum = 32505859;BA.debugLine="Dim year As String = parts(0)";
_year = _parts[(int) (0)];
RDebugUtils.currentLine=32505860;
 //BA.debugLineNum = 32505860;BA.debugLine="Dim monthNum As Int = parts(1)";
_monthnum = (int)(Double.parseDouble(_parts[(int) (1)]));
RDebugUtils.currentLine=32505861;
 //BA.debugLineNum = 32505861;BA.debugLine="Dim day As String = parts(2)";
_day = _parts[(int) (2)];
RDebugUtils.currentLine=32505863;
 //BA.debugLineNum = 32505863;BA.debugLine="Dim monthName As String";
_monthname = "";
RDebugUtils.currentLine=32505864;
 //BA.debugLineNum = 32505864;BA.debugLine="Select monthNum";
switch (_monthnum) {
case 1: {
RDebugUtils.currentLine=32505865;
 //BA.debugLineNum = 32505865;BA.debugLine="Case 1: monthName = \"January\"";
_monthname = "January";
 break; }
case 2: {
RDebugUtils.currentLine=32505866;
 //BA.debugLineNum = 32505866;BA.debugLine="Case 2: monthName = \"February\"";
_monthname = "February";
 break; }
case 3: {
RDebugUtils.currentLine=32505867;
 //BA.debugLineNum = 32505867;BA.debugLine="Case 3: monthName = \"March\"";
_monthname = "March";
 break; }
case 4: {
RDebugUtils.currentLine=32505868;
 //BA.debugLineNum = 32505868;BA.debugLine="Case 4: monthName = \"April\"";
_monthname = "April";
 break; }
case 5: {
RDebugUtils.currentLine=32505869;
 //BA.debugLineNum = 32505869;BA.debugLine="Case 5: monthName = \"May\"";
_monthname = "May";
 break; }
case 6: {
RDebugUtils.currentLine=32505870;
 //BA.debugLineNum = 32505870;BA.debugLine="Case 6: monthName = \"June\"";
_monthname = "June";
 break; }
case 7: {
RDebugUtils.currentLine=32505871;
 //BA.debugLineNum = 32505871;BA.debugLine="Case 7: monthName = \"July\"";
_monthname = "July";
 break; }
case 8: {
RDebugUtils.currentLine=32505872;
 //BA.debugLineNum = 32505872;BA.debugLine="Case 8: monthName = \"August\"";
_monthname = "August";
 break; }
case 9: {
RDebugUtils.currentLine=32505873;
 //BA.debugLineNum = 32505873;BA.debugLine="Case 9: monthName = \"September\"";
_monthname = "September";
 break; }
case 10: {
RDebugUtils.currentLine=32505874;
 //BA.debugLineNum = 32505874;BA.debugLine="Case 10: monthName = \"October\"";
_monthname = "October";
 break; }
case 11: {
RDebugUtils.currentLine=32505875;
 //BA.debugLineNum = 32505875;BA.debugLine="Case 11: monthName = \"November\"";
_monthname = "November";
 break; }
case 12: {
RDebugUtils.currentLine=32505876;
 //BA.debugLineNum = 32505876;BA.debugLine="Case 12: monthName = \"December\"";
_monthname = "December";
 break; }
}
;
RDebugUtils.currentLine=32505879;
 //BA.debugLineNum = 32505879;BA.debugLine="Dim ts As Long = DateTime.DateParse(Tagdate)";
_ts = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_tagdate);
RDebugUtils.currentLine=32505880;
 //BA.debugLineNum = 32505880;BA.debugLine="Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)";
_weekdaynum = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_ts);
RDebugUtils.currentLine=32505881;
 //BA.debugLineNum = 32505881;BA.debugLine="Dim week As String";
_week = "";
RDebugUtils.currentLine=32505882;
 //BA.debugLineNum = 32505882;BA.debugLine="Select weekdayNum";
switch (_weekdaynum) {
case 1: {
RDebugUtils.currentLine=32505883;
 //BA.debugLineNum = 32505883;BA.debugLine="Case 1: week = \"Sunday\"";
_week = "Sunday";
 break; }
case 2: {
RDebugUtils.currentLine=32505884;
 //BA.debugLineNum = 32505884;BA.debugLine="Case 2: week = \"Monday\"";
_week = "Monday";
 break; }
case 3: {
RDebugUtils.currentLine=32505885;
 //BA.debugLineNum = 32505885;BA.debugLine="Case 3: week = \"Tuesday\"";
_week = "Tuesday";
 break; }
case 4: {
RDebugUtils.currentLine=32505886;
 //BA.debugLineNum = 32505886;BA.debugLine="Case 4: week = \"Wednesday\"";
_week = "Wednesday";
 break; }
case 5: {
RDebugUtils.currentLine=32505887;
 //BA.debugLineNum = 32505887;BA.debugLine="Case 5: week = \"Thursday\"";
_week = "Thursday";
 break; }
case 6: {
RDebugUtils.currentLine=32505888;
 //BA.debugLineNum = 32505888;BA.debugLine="Case 6: week = \"Friday\"";
_week = "Friday";
 break; }
case 7: {
RDebugUtils.currentLine=32505889;
 //BA.debugLineNum = 32505889;BA.debugLine="Case 7: week = \"Saturday\"";
_week = "Saturday";
 break; }
}
;
RDebugUtils.currentLine=32505892;
 //BA.debugLineNum = 32505892;BA.debugLine="Return week & \", \" & monthName & \" \" & day & \", \"";
if (true) return _week+", "+_monthname+" "+_day+", "+_year;
RDebugUtils.currentLine=32505893;
 //BA.debugLineNum = 32505893;BA.debugLine="End Sub";
return "";
}
public static int  _identifycolor(String _typeofevent) throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "identifycolor", false))
	 {return ((Integer) Debug.delegate(mostCurrent.activityBA, "identifycolor", new Object[] {_typeofevent}));}
int _mycolor = 0;
RDebugUtils.currentLine=32440320;
 //BA.debugLineNum = 32440320;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
RDebugUtils.currentLine=32440321;
 //BA.debugLineNum = 32440321;BA.debugLine="Dim mycolor As Int";
_mycolor = 0;
RDebugUtils.currentLine=32440322;
 //BA.debugLineNum = 32440322;BA.debugLine="If typeofevent = \"Task\" Then";
if ((_typeofevent).equals("Task")) { 
RDebugUtils.currentLine=32440323;
 //BA.debugLineNum = 32440323;BA.debugLine="mycolor = Colors.ARGB(190, 0, 191, 255)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (190),(int) (0),(int) (191),(int) (255));
 }else 
{RDebugUtils.currentLine=32440324;
 //BA.debugLineNum = 32440324;BA.debugLine="Else if typeofevent = \"Event\" Then";
if ((_typeofevent).equals("Event")) { 
RDebugUtils.currentLine=32440325;
 //BA.debugLineNum = 32440325;BA.debugLine="mycolor = Colors.ARGB(190, 152, 255, 152)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (190),(int) (152),(int) (255),(int) (152));
 }else 
{RDebugUtils.currentLine=32440326;
 //BA.debugLineNum = 32440326;BA.debugLine="Else if typeofevent = \"Birthday\" Then";
if ((_typeofevent).equals("Birthday")) { 
RDebugUtils.currentLine=32440327;
 //BA.debugLineNum = 32440327;BA.debugLine="mycolor = Colors.ARGB(190, 255, 182, 193)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (190),(int) (255),(int) (182),(int) (193));
 }else 
{RDebugUtils.currentLine=32440328;
 //BA.debugLineNum = 32440328;BA.debugLine="Else if typeofevent = \"OOO\" Then";
if ((_typeofevent).equals("OOO")) { 
RDebugUtils.currentLine=32440329;
 //BA.debugLineNum = 32440329;BA.debugLine="mycolor = Colors.ARGB(190, 255, 215, 0)";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (190),(int) (255),(int) (215),(int) (0));
 }}}}
;
RDebugUtils.currentLine=32440332;
 //BA.debugLineNum = 32440332;BA.debugLine="Return mycolor";
if (true) return _mycolor;
RDebugUtils.currentLine=32440333;
 //BA.debugLineNum = 32440333;BA.debugLine="End Sub";
return 0;
}
public static String  _gettimestring(int _h) throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "gettimestring", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "gettimestring", new Object[] {_h}));}
int _num = 0;
String _ampm = "";
RDebugUtils.currentLine=32374784;
 //BA.debugLineNum = 32374784;BA.debugLine="Sub GetTimeString (h As Int) As String";
RDebugUtils.currentLine=32374785;
 //BA.debugLineNum = 32374785;BA.debugLine="Dim num As Int";
_num = 0;
RDebugUtils.currentLine=32374786;
 //BA.debugLineNum = 32374786;BA.debugLine="Dim ampm As String";
_ampm = "";
RDebugUtils.currentLine=32374787;
 //BA.debugLineNum = 32374787;BA.debugLine="If h = 0 Then";
if (_h==0) { 
RDebugUtils.currentLine=32374788;
 //BA.debugLineNum = 32374788;BA.debugLine="num = 12";
_num = (int) (12);
RDebugUtils.currentLine=32374789;
 //BA.debugLineNum = 32374789;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else 
{RDebugUtils.currentLine=32374790;
 //BA.debugLineNum = 32374790;BA.debugLine="Else if h = 12 Then";
if (_h==12) { 
RDebugUtils.currentLine=32374791;
 //BA.debugLineNum = 32374791;BA.debugLine="num = h";
_num = _h;
RDebugUtils.currentLine=32374792;
 //BA.debugLineNum = 32374792;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 }else 
{RDebugUtils.currentLine=32374793;
 //BA.debugLineNum = 32374793;BA.debugLine="Else if h > 12 Then";
if (_h>12) { 
RDebugUtils.currentLine=32374794;
 //BA.debugLineNum = 32374794;BA.debugLine="num = h - 12";
_num = (int) (_h-12);
RDebugUtils.currentLine=32374795;
 //BA.debugLineNum = 32374795;BA.debugLine="If num = 12 Then";
if (_num==12) { 
RDebugUtils.currentLine=32374796;
 //BA.debugLineNum = 32374796;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }else {
RDebugUtils.currentLine=32374798;
 //BA.debugLineNum = 32374798;BA.debugLine="ampm = \"pm\"";
_ampm = "pm";
 };
 }else {
RDebugUtils.currentLine=32374802;
 //BA.debugLineNum = 32374802;BA.debugLine="num = h";
_num = _h;
RDebugUtils.currentLine=32374803;
 //BA.debugLineNum = 32374803;BA.debugLine="ampm = \"am\"";
_ampm = "am";
 }}}
;
RDebugUtils.currentLine=32374806;
 //BA.debugLineNum = 32374806;BA.debugLine="Return num & \":00\" & ampm";
if (true) return BA.NumberToString(_num)+":00"+_ampm;
RDebugUtils.currentLine=32374807;
 //BA.debugLineNum = 32374807;BA.debugLine="End Sub";
return "";
}
public static String  _menu_btn_click() throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "menu_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "menu_btn_click", null));}
RDebugUtils.currentLine=32702464;
 //BA.debugLineNum = 32702464;BA.debugLine="Private Sub menu_btn_Click";
RDebugUtils.currentLine=32702465;
 //BA.debugLineNum = 32702465;BA.debugLine="menupanel.Visible =True";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=32702466;
 //BA.debugLineNum = 32702466;BA.debugLine="End Sub";
return "";
}
public static String  _month_btn_click() throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "month_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "month_btn_click", null));}
RDebugUtils.currentLine=32899072;
 //BA.debugLineNum = 32899072;BA.debugLine="Private Sub Month_btn_Click";
RDebugUtils.currentLine=32899073;
 //BA.debugLineNum = 32899073;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=32899074;
 //BA.debugLineNum = 32899074;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
RDebugUtils.currentLine=32899075;
 //BA.debugLineNum = 32899075;BA.debugLine="End Sub";
return "";
}
public static String  _sched_btn_click() throws Exception{
RDebugUtils.currentModule="schedule_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sched_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sched_btn_click", null));}
RDebugUtils.currentLine=32768000;
 //BA.debugLineNum = 32768000;BA.debugLine="Private Sub sched_btn_Click";
RDebugUtils.currentLine=32768001;
 //BA.debugLineNum = 32768001;BA.debugLine="menupanel.visible = False";
mostCurrent._menupanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=32768002;
 //BA.debugLineNum = 32768002;BA.debugLine="End Sub";
return "";
}
}