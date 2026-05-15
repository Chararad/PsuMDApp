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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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


public static class _userscore{
public boolean IsInitialized;
public String Name;
public int XP;
public int Streak;
public int CorrectRate;
public void Initialize() {
IsInitialized = true;
Name = "";
XP = 0;
Streak = 0;
CorrectRate = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public static String _db_name = "";
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlheader = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsubtitle = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnltopcard = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltopuser = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltoppoints = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltopstreak = null;
public b4a.example3.customlistview _clvboard = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btndaily = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnweekly = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnalltime = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfooter = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlinsert = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edtname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edtxp = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edtstreak = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btninsert = null;
public anywheresoftware.b4a.objects.collections.List _dailylist = null;
public anywheresoftware.b4a.objects.collections.List _weeklylist = null;
public anywheresoftware.b4a.objects.collections.List _alltimelist = null;
public static String _currentmode = "";
public b4a.example.dateutils _dateutils = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"Leaderboard\")";
mostCurrent._activity.LoadLayout("Leaderboard",mostCurrent.activityBA);
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="DailyList.Initialize";
mostCurrent._dailylist.Initialize();
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="WeeklyList.Initialize";
mostCurrent._weeklylist.Initialize();
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="AllTimeList.Initialize";
mostCurrent._alltimelist.Initialize();
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="InitDB";
_initdb();
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="EnsureSeedData";
_ensureseeddata();
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="SetupTheme";
_setuptheme();
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="SetupInsertControls";
_setupinsertcontrols();
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="ShowBoard(\"DAILY\")";
_showboard("DAILY");
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="End Sub";
return "";
}
public static String  _initdb() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "initdb", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "initdb", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Private Sub InitDB";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="SQL1.Initialize(File.DirInternal, DB_NAME, True)";
_sql1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_db_name,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="SQL1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS lea";
_sql1.ExecNonQuery("CREATE TABLE IF NOT EXISTS leaderboard ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"mode TEXT, "+"name TEXT, "+"xp INTEGER, "+"streak INTEGER, "+"correct_rate INTEGER)");
RDebugUtils.currentLine=720905;
 //BA.debugLineNum = 720905;BA.debugLine="End Sub";
return "";
}
public static String  _ensureseeddata() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ensureseeddata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ensureseeddata", null));}
int _c = 0;
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Private Sub EnsureSeedData";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="Dim c As Int = SQL1.ExecQuerySingleResult(\"SELECT";
_c = (int)(Double.parseDouble(_sql1.ExecQuerySingleResult("SELECT COUNT(*) FROM leaderboard")));
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="If c = 0 Then SetupMockData";
if (_c==0) { 
_setupmockdata();};
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="End Sub";
return "";
}
public static String  _setuptheme() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setuptheme", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setuptheme", null));}
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Private Sub SetupTheme";
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="Activity.Color = xui.Color_ARGB(255, 245, 239, 22";
mostCurrent._activity.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (245),(int) (239),(int) (229)));
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="pnlHeader.Color = xui.Color_ARGB(255, 107, 92, 14";
mostCurrent._pnlheader.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (107),(int) (92),(int) (141)));
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="lblTitle.TextColor = xui.Color_White";
mostCurrent._lbltitle.setTextColor(mostCurrent._xui.Color_White);
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="lblSubtitle.TextColor = xui.Color_ARGB(255, 232,";
mostCurrent._lblsubtitle.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (232),(int) (224),(int) (255)));
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="pnlTopCard.Color = xui.Color_ARGB(255, 255, 247,";
mostCurrent._pnltopcard.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (255),(int) (247),(int) (236)));
RDebugUtils.currentLine=327690;
 //BA.debugLineNum = 327690;BA.debugLine="lblTopUser.TextColor = xui.Color_ARGB(255, 64, 52";
mostCurrent._lbltopuser.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (64),(int) (52),(int) (82)));
RDebugUtils.currentLine=327691;
 //BA.debugLineNum = 327691;BA.debugLine="lblTopPoints.TextColor = xui.Color_ARGB(255, 105,";
mostCurrent._lbltoppoints.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (105),(int) (89),(int) (128)));
RDebugUtils.currentLine=327692;
 //BA.debugLineNum = 327692;BA.debugLine="lblTopStreak.TextColor = xui.Color_ARGB(255, 131,";
mostCurrent._lbltopstreak.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (131),(int) (102),(int) (82)));
RDebugUtils.currentLine=327694;
 //BA.debugLineNum = 327694;BA.debugLine="btnDaily.Color = xui.Color_ARGB(255, 185, 155, 21";
mostCurrent._btndaily.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (185),(int) (155),(int) (214)));
RDebugUtils.currentLine=327695;
 //BA.debugLineNum = 327695;BA.debugLine="btnWeekly.Color = xui.Color_ARGB(255, 185, 155, 2";
mostCurrent._btnweekly.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (185),(int) (155),(int) (214)));
RDebugUtils.currentLine=327696;
 //BA.debugLineNum = 327696;BA.debugLine="btnAllTime.Color = xui.Color_ARGB(255, 185, 155,";
mostCurrent._btnalltime.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (185),(int) (155),(int) (214)));
RDebugUtils.currentLine=327698;
 //BA.debugLineNum = 327698;BA.debugLine="btnDaily.TextColor = xui.Color_White";
mostCurrent._btndaily.setTextColor(mostCurrent._xui.Color_White);
RDebugUtils.currentLine=327699;
 //BA.debugLineNum = 327699;BA.debugLine="btnWeekly.TextColor = xui.Color_White";
mostCurrent._btnweekly.setTextColor(mostCurrent._xui.Color_White);
RDebugUtils.currentLine=327700;
 //BA.debugLineNum = 327700;BA.debugLine="btnAllTime.TextColor = xui.Color_White";
mostCurrent._btnalltime.setTextColor(mostCurrent._xui.Color_White);
RDebugUtils.currentLine=327702;
 //BA.debugLineNum = 327702;BA.debugLine="lblFooter.TextColor = xui.Color_ARGB(255, 120, 10";
mostCurrent._lblfooter.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (120),(int) (104),(int) (94)));
RDebugUtils.currentLine=327703;
 //BA.debugLineNum = 327703;BA.debugLine="End Sub";
return "";
}
public static String  _setupinsertcontrols() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setupinsertcontrols", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setupinsertcontrols", null));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Private Sub SetupInsertControls";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="pnlInsert.Initialize(\"\")";
mostCurrent._pnlinsert.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="pnlInsert.Color = xui.Color_ARGB(255, 255, 247, 2";
mostCurrent._pnlinsert.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (255),(int) (247),(int) (236)));
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="Activity.AddView(pnlInsert, 8dip, Activity.Height";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlinsert.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),(int) (mostCurrent._activity.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (154))),(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (146)));
RDebugUtils.currentLine=393221;
 //BA.debugLineNum = 393221;BA.debugLine="edtName.Initialize(\"\")";
mostCurrent._edtname.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="edtName.Hint = \"Name\"";
mostCurrent._edtname.setHint("Name");
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="pnlInsert.AddView(edtName, 8dip, 8dip, pnlInsert.";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._edtname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),(int) (mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=393225;
 //BA.debugLineNum = 393225;BA.debugLine="edtXP.Initialize(\"\")";
mostCurrent._edtxp.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=393226;
 //BA.debugLineNum = 393226;BA.debugLine="edtXP.Hint = \"XP\"";
mostCurrent._edtxp.setHint("XP");
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="edtXP.InputType = edtXP.INPUT_TYPE_NUMBERS";
mostCurrent._edtxp.setInputType(mostCurrent._edtxp.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="pnlInsert.AddView(edtXP, 8dip, 54dip, (pnlInsert.";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._edtxp.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),(int) ((mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)))/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=393230;
 //BA.debugLineNum = 393230;BA.debugLine="edtStreak.Initialize(\"\")";
mostCurrent._edtstreak.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=393231;
 //BA.debugLineNum = 393231;BA.debugLine="edtStreak.Hint = \"Streak\"";
mostCurrent._edtstreak.setHint("Streak");
RDebugUtils.currentLine=393232;
 //BA.debugLineNum = 393232;BA.debugLine="edtStreak.InputType = edtStreak.INPUT_TYPE_NUMBER";
mostCurrent._edtstreak.setInputType(mostCurrent._edtstreak.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=393233;
 //BA.debugLineNum = 393233;BA.debugLine="pnlInsert.AddView(edtStreak, edtXP.Left + edtXP.W";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._edtstreak.getObject()),(int) (mostCurrent._edtxp.getLeft()+mostCurrent._edtxp.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),(int) ((mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)))/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=393235;
 //BA.debugLineNum = 393235;BA.debugLine="btnInsert.Initialize(\"btnInsert\")";
mostCurrent._btninsert.Initialize(mostCurrent.activityBA,"btnInsert");
RDebugUtils.currentLine=393236;
 //BA.debugLineNum = 393236;BA.debugLine="btnInsert.Text = \"Insert to Leaderboard\"";
mostCurrent._btninsert.setText(BA.ObjectToCharSequence("Insert to Leaderboard"));
RDebugUtils.currentLine=393237;
 //BA.debugLineNum = 393237;BA.debugLine="btnInsert.Color = xui.Color_ARGB(255, 141, 113, 1";
mostCurrent._btninsert.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (141),(int) (113),(int) (176)));
RDebugUtils.currentLine=393238;
 //BA.debugLineNum = 393238;BA.debugLine="btnInsert.TextColor = xui.Color_White";
mostCurrent._btninsert.setTextColor(mostCurrent._xui.Color_White);
RDebugUtils.currentLine=393239;
 //BA.debugLineNum = 393239;BA.debugLine="pnlInsert.AddView(btnInsert, 8dip, 100dip, pnlIns";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._btninsert.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),(int) (mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=393240;
 //BA.debugLineNum = 393240;BA.debugLine="End Sub";
return "";
}
public static String  _showboard(String _mode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showboard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showboard", new Object[] {_mode}));}
anywheresoftware.b4a.objects.collections.List _source = null;
b4a.example.main._userscore _top = null;
int _i = 0;
b4a.example.main._userscore _u = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lblrank = null;
anywheresoftware.b4a.objects.LabelWrapper _lblname = null;
anywheresoftware.b4a.objects.LabelWrapper _lblstats = null;
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Private Sub ShowBoard(mode As String)";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="mode = mode.ToUpperCase";
_mode = _mode.toUpperCase();
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="CurrentMode = mode";
mostCurrent._currentmode = _mode;
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="Dim source As List";
_source = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=1179652;
 //BA.debugLineNum = 1179652;BA.debugLine="Select mode";
switch (BA.switchObjectToInt(_mode,"DAILY","WEEKLY")) {
case 0: {
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="source = DailyList";
_source = mostCurrent._dailylist;
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="lblSubtitle.Text = \"Today's top flashcard learn";
mostCurrent._lblsubtitle.setText(BA.ObjectToCharSequence("Today's top flashcard learners"));
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="HighlightButton(btnDaily)";
_highlightbutton(mostCurrent._btndaily);
 break; }
case 1: {
RDebugUtils.currentLine=1179658;
 //BA.debugLineNum = 1179658;BA.debugLine="source = WeeklyList";
_source = mostCurrent._weeklylist;
RDebugUtils.currentLine=1179659;
 //BA.debugLineNum = 1179659;BA.debugLine="lblSubtitle.Text = \"Weekly focus champions\"";
mostCurrent._lblsubtitle.setText(BA.ObjectToCharSequence("Weekly focus champions"));
RDebugUtils.currentLine=1179660;
 //BA.debugLineNum = 1179660;BA.debugLine="HighlightButton(btnWeekly)";
_highlightbutton(mostCurrent._btnweekly);
 break; }
default: {
RDebugUtils.currentLine=1179662;
 //BA.debugLineNum = 1179662;BA.debugLine="source = AllTimeList";
_source = mostCurrent._alltimelist;
RDebugUtils.currentLine=1179663;
 //BA.debugLineNum = 1179663;BA.debugLine="lblSubtitle.Text = \"All-time cozy legends\"";
mostCurrent._lblsubtitle.setText(BA.ObjectToCharSequence("All-time cozy legends"));
RDebugUtils.currentLine=1179664;
 //BA.debugLineNum = 1179664;BA.debugLine="HighlightButton(btnAllTime)";
_highlightbutton(mostCurrent._btnalltime);
 break; }
}
;
RDebugUtils.currentLine=1179667;
 //BA.debugLineNum = 1179667;BA.debugLine="LoadBoardFromDB(mode)";
_loadboardfromdb(_mode);
RDebugUtils.currentLine=1179668;
 //BA.debugLineNum = 1179668;BA.debugLine="If source.Size = 0 Then Return";
if (_source.getSize()==0) { 
if (true) return "";};
RDebugUtils.currentLine=1179671;
 //BA.debugLineNum = 1179671;BA.debugLine="Dim top As UserScore = source.Get(0)";
_top = (b4a.example.main._userscore)(_source.Get((int) (0)));
RDebugUtils.currentLine=1179672;
 //BA.debugLineNum = 1179672;BA.debugLine="lblTopUser.Text = \"🏆 \" & top.Name";
mostCurrent._lbltopuser.setText(BA.ObjectToCharSequence("🏆 "+_top.Name /*String*/ ));
RDebugUtils.currentLine=1179673;
 //BA.debugLineNum = 1179673;BA.debugLine="lblTopPoints.Text = top.XP & \" XP\"";
mostCurrent._lbltoppoints.setText(BA.ObjectToCharSequence(BA.NumberToString(_top.XP /*int*/ )+" XP"));
RDebugUtils.currentLine=1179674;
 //BA.debugLineNum = 1179674;BA.debugLine="lblTopStreak.Text = \"🔥 \" & top.Streak & \" day st";
mostCurrent._lbltopstreak.setText(BA.ObjectToCharSequence("🔥 "+BA.NumberToString(_top.Streak /*int*/ )+" day streak · "+BA.NumberToString(_top.CorrectRate /*int*/ )+"% correct"));
RDebugUtils.currentLine=1179677;
 //BA.debugLineNum = 1179677;BA.debugLine="clvBoard.Clear";
mostCurrent._clvboard._clear();
RDebugUtils.currentLine=1179678;
 //BA.debugLineNum = 1179678;BA.debugLine="For i = 0 To source.Size - 1";
{
final int step25 = 1;
final int limit25 = (int) (_source.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit25 ;_i = _i + step25 ) {
RDebugUtils.currentLine=1179679;
 //BA.debugLineNum = 1179679;BA.debugLine="Dim u As UserScore = source.Get(i)";
_u = (b4a.example.main._userscore)(_source.Get(_i));
RDebugUtils.currentLine=1179680;
 //BA.debugLineNum = 1179680;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1179681;
 //BA.debugLineNum = 1179681;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1179682;
 //BA.debugLineNum = 1179682;BA.debugLine="p.Color = xui.Color_ARGB(255, 255, 250, 242)";
_p.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (255),(int) (250),(int) (242)));
RDebugUtils.currentLine=1179683;
 //BA.debugLineNum = 1179683;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, clvBoard.AsView.Wid";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),mostCurrent._clvboard._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=1179685;
 //BA.debugLineNum = 1179685;BA.debugLine="Dim lblRank As Label";
_lblrank = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1179686;
 //BA.debugLineNum = 1179686;BA.debugLine="lblRank.Initialize(\"\")";
_lblrank.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1179687;
 //BA.debugLineNum = 1179687;BA.debugLine="lblRank.Text = \"#\" & (i + 1)";
_lblrank.setText(BA.ObjectToCharSequence("#"+BA.NumberToString((_i+1))));
RDebugUtils.currentLine=1179688;
 //BA.debugLineNum = 1179688;BA.debugLine="lblRank.TextSize = 15";
_lblrank.setTextSize((float) (15));
RDebugUtils.currentLine=1179689;
 //BA.debugLineNum = 1179689;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 72,";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (84),(int) (72),(int) (101)));
RDebugUtils.currentLine=1179690;
 //BA.debugLineNum = 1179690;BA.debugLine="lblRank.Gravity = Gravity.CENTER_VERTICAL";
_lblrank.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=1179691;
 //BA.debugLineNum = 1179691;BA.debugLine="p.AddView(lblRank, 10dip, 0, 40dip, 62dip)";
_p.AddView((android.view.View)(_lblrank.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=1179693;
 //BA.debugLineNum = 1179693;BA.debugLine="Dim lblName As Label";
_lblname = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1179694;
 //BA.debugLineNum = 1179694;BA.debugLine="lblName.Initialize(\"\")";
_lblname.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1179695;
 //BA.debugLineNum = 1179695;BA.debugLine="lblName.Text = u.Name";
_lblname.setText(BA.ObjectToCharSequence(_u.Name /*String*/ ));
RDebugUtils.currentLine=1179696;
 //BA.debugLineNum = 1179696;BA.debugLine="lblName.TextSize = 16";
_lblname.setTextSize((float) (16));
RDebugUtils.currentLine=1179697;
 //BA.debugLineNum = 1179697;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 50,";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (62),(int) (50),(int) (80)));
RDebugUtils.currentLine=1179698;
 //BA.debugLineNum = 1179698;BA.debugLine="lblName.Gravity = Gravity.CENTER_VERTICAL";
_lblname.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=1179699;
 //BA.debugLineNum = 1179699;BA.debugLine="p.AddView(lblName, 55dip, 0, 110dip, 62dip)";
_p.AddView((android.view.View)(_lblname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=1179701;
 //BA.debugLineNum = 1179701;BA.debugLine="Dim lblStats As Label";
_lblstats = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1179702;
 //BA.debugLineNum = 1179702;BA.debugLine="lblStats.Initialize(\"\")";
_lblstats.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1179703;
 //BA.debugLineNum = 1179703;BA.debugLine="lblStats.Text = u.XP & \" XP   •   🔥\" & u.Streak";
_lblstats.setText(BA.ObjectToCharSequence(BA.NumberToString(_u.XP /*int*/ )+" XP   •   🔥"+BA.NumberToString(_u.Streak /*int*/ )+"   •   "+BA.NumberToString(_u.CorrectRate /*int*/ )+"%"));
RDebugUtils.currentLine=1179704;
 //BA.debugLineNum = 1179704;BA.debugLine="lblStats.TextSize = 13";
_lblstats.setTextSize((float) (13));
RDebugUtils.currentLine=1179705;
 //BA.debugLineNum = 1179705;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122, 98";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
RDebugUtils.currentLine=1179706;
 //BA.debugLineNum = 1179706;BA.debugLine="lblStats.Gravity = Gravity.CENTER_VERTICAL";
_lblstats.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=1179707;
 //BA.debugLineNum = 1179707;BA.debugLine="p.AddView(lblStats, 165dip, 0, clvBoard.AsView.W";
_p.AddView((android.view.View)(_lblstats.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (165)),(int) (0),(int) (mostCurrent._clvboard._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (175))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=1179709;
 //BA.debugLineNum = 1179709;BA.debugLine="clvBoard.Add(p, u.Name)";
mostCurrent._clvboard._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())),(Object)(_u.Name /*String*/ ));
 }
};
RDebugUtils.currentLine=1179712;
 //BA.debugLineNum = 1179712;BA.debugLine="lblFooter.Text = \"Keep reviewing your flashcards";
mostCurrent._lblfooter.setText(BA.ObjectToCharSequence("Keep reviewing your flashcards to climb the cozy board ✨"));
RDebugUtils.currentLine=1179713;
 //BA.debugLineNum = 1179713;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="End Sub";
return "";
}
public static String  _adduserscore(String _mode,String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "adduserscore", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "adduserscore", new Object[] {_mode,_n,_xp,_st,_cr}));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Public Sub AddUserScore(mode As String, n As Strin";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="InsertUserToDB(mode, n, xp, st, cr)";
_insertusertodb(_mode,_n,_xp,_st,_cr);
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="If mode = CurrentMode Then ShowBoard(mode)";
if ((_mode).equals(mostCurrent._currentmode)) { 
_showboard(_mode);};
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="End Sub";
return "";
}
public static String  _insertusertodb(String _mode,String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "insertusertodb", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "insertusertodb", new Object[] {_mode,_n,_xp,_st,_cr}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Private Sub InsertUserToDB(mode As String, n As St";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO leaderboard(mode,";
_sql1.ExecNonQuery2("INSERT INTO leaderboard(mode, name, xp, streak, correct_rate) VALUES (?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_mode.toUpperCase()),(Object)(_n),(Object)(_xp),(Object)(_st),(Object)(_cr)}));
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _addusertomode(String _mode,String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addusertomode", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addusertomode", new Object[] {_mode,_n,_xp,_st,_cr}));}
anywheresoftware.b4a.objects.collections.List _target = null;
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Private Sub AddUserToMode(mode As String, n As Str";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="Dim target As List = GetModeList(mode)";
_target = new anywheresoftware.b4a.objects.collections.List();
_target = _getmodelist(_mode);
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="If target.IsInitialized = False Then Return";
if (_target.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="target.Add(CreateUser(n, xp, st, cr))";
_target.Add((Object)(_createuser(_n,_xp,_st,_cr)));
RDebugUtils.currentLine=589828;
 //BA.debugLineNum = 589828;BA.debugLine="SortBoard(target)";
_sortboard(_target);
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _getmodelist(String _mode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getmodelist", false))
	 {return ((anywheresoftware.b4a.objects.collections.List) Debug.delegate(mostCurrent.activityBA, "getmodelist", new Object[] {_mode}));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Private Sub GetModeList(mode As String) As List";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Select mode.ToUpperCase";
switch (BA.switchObjectToInt(_mode.toUpperCase(),"DAILY","WEEKLY")) {
case 0: {
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="Return DailyList";
if (true) return mostCurrent._dailylist;
 break; }
case 1: {
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="Return WeeklyList";
if (true) return mostCurrent._weeklylist;
 break; }
default: {
RDebugUtils.currentLine=983047;
 //BA.debugLineNum = 983047;BA.debugLine="Return AllTimeList";
if (true) return mostCurrent._alltimelist;
 break; }
}
;
RDebugUtils.currentLine=983049;
 //BA.debugLineNum = 983049;BA.debugLine="End Sub";
return null;
}
public static b4a.example.main._userscore  _createuser(String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createuser", false))
	 {return ((b4a.example.main._userscore) Debug.delegate(mostCurrent.activityBA, "createuser", new Object[] {_n,_xp,_st,_cr}));}
b4a.example.main._userscore _u = null;
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Private Sub CreateUser(n As String, xp As Int, st";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Dim u As UserScore";
_u = new b4a.example.main._userscore();
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="u.Initialize";
_u.Initialize();
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="u.Name = n";
_u.Name /*String*/  = _n;
RDebugUtils.currentLine=524292;
 //BA.debugLineNum = 524292;BA.debugLine="u.XP = xp";
_u.XP /*int*/  = _xp;
RDebugUtils.currentLine=524293;
 //BA.debugLineNum = 524293;BA.debugLine="u.Streak = st";
_u.Streak /*int*/  = _st;
RDebugUtils.currentLine=524294;
 //BA.debugLineNum = 524294;BA.debugLine="u.CorrectRate = cr";
_u.CorrectRate /*int*/  = _cr;
RDebugUtils.currentLine=524295;
 //BA.debugLineNum = 524295;BA.debugLine="Return u";
if (true) return _u;
RDebugUtils.currentLine=524296;
 //BA.debugLineNum = 524296;BA.debugLine="End Sub";
return null;
}
public static String  _sortboard(anywheresoftware.b4a.objects.collections.List _board) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sortboard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sortboard", new Object[] {_board}));}
int _i = 0;
int _j = 0;
b4a.example.main._userscore _a = null;
b4a.example.main._userscore _b = null;
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Private Sub SortBoard(board As List)";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="For i = 0 To board.Size - 2";
{
final int step1 = 1;
final int limit1 = (int) (_board.getSize()-2);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="For j = i + 1 To board.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (_board.getSize()-1);
_j = (int) (_i+1) ;
for (;_j <= limit2 ;_j = _j + step2 ) {
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="Dim a As UserScore = board.Get(i)";
_a = (b4a.example.main._userscore)(_board.Get(_i));
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="Dim b As UserScore = board.Get(j)";
_b = (b4a.example.main._userscore)(_board.Get(_j));
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="If ShouldSwapForRanking(a, b) Then";
if (_shouldswapforranking(_a,_b)) { 
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="board.Set(i, b)";
_board.Set(_i,(Object)(_b));
RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="board.Set(j, a)";
_board.Set(_j,(Object)(_a));
 };
 }
};
 }
};
RDebugUtils.currentLine=1048587;
 //BA.debugLineNum = 1048587;BA.debugLine="End Sub";
return "";
}
public static String  _btnalltime_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnalltime_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnalltime_click", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub btnAllTime_Click";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="ShowBoard(\"ALLTIME\")";
_showboard("ALLTIME");
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="End Sub";
return "";
}
public static String  _btndaily_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btndaily_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btndaily_click", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Private Sub btnDaily_Click";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="ShowBoard(\"DAILY\")";
_showboard("DAILY");
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="End Sub";
return "";
}
public static String  _btninsert_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btninsert_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btninsert_click", null));}
String _n = "";
String _xptext = "";
String _streaktext = "";
int _xp = 0;
int _st = 0;
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Private Sub btnInsert_Click";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="Dim n As String = edtName.Text.Trim";
_n = mostCurrent._edtname.getText().trim();
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="Dim xpText As String = edtXP.Text.Trim";
_xptext = mostCurrent._edtxp.getText().trim();
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="Dim streakText As String = edtStreak.Text.Trim";
_streaktext = mostCurrent._edtstreak.getText().trim();
RDebugUtils.currentLine=1507333;
 //BA.debugLineNum = 1507333;BA.debugLine="If n.Length = 0 Or xpText.Length = 0 Or streakTex";
if (_n.length()==0 || _xptext.length()==0 || _streaktext.length()==0) { 
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="ToastMessageShow(\"Please fill Name, XP, and Stre";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Please fill Name, XP, and Streak."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1507338;
 //BA.debugLineNum = 1507338;BA.debugLine="If IsNumber(xpText) = False Or IsNumber(streakTex";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_xptext)==anywheresoftware.b4a.keywords.Common.False || anywheresoftware.b4a.keywords.Common.IsNumber(_streaktext)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=1507339;
 //BA.debugLineNum = 1507339;BA.debugLine="ToastMessageShow(\"XP and Streak must be numbers.";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("XP and Streak must be numbers."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507340;
 //BA.debugLineNum = 1507340;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1507343;
 //BA.debugLineNum = 1507343;BA.debugLine="Dim xp As Int = xpText";
_xp = (int)(Double.parseDouble(_xptext));
RDebugUtils.currentLine=1507344;
 //BA.debugLineNum = 1507344;BA.debugLine="Dim st As Int = streakText";
_st = (int)(Double.parseDouble(_streaktext));
RDebugUtils.currentLine=1507345;
 //BA.debugLineNum = 1507345;BA.debugLine="If xp < 0 Or st < 0 Then";
if (_xp<0 || _st<0) { 
RDebugUtils.currentLine=1507346;
 //BA.debugLineNum = 1507346;BA.debugLine="ToastMessageShow(\"XP and Streak cannot be negati";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("XP and Streak cannot be negative."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507347;
 //BA.debugLineNum = 1507347;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1507351;
 //BA.debugLineNum = 1507351;BA.debugLine="AddUserScore(CurrentMode, n, xp, st, 0)";
_adduserscore(mostCurrent._currentmode,_n,_xp,_st,(int) (0));
RDebugUtils.currentLine=1507353;
 //BA.debugLineNum = 1507353;BA.debugLine="edtName.Text = \"\"";
mostCurrent._edtname.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=1507354;
 //BA.debugLineNum = 1507354;BA.debugLine="edtXP.Text = \"\"";
mostCurrent._edtxp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=1507355;
 //BA.debugLineNum = 1507355;BA.debugLine="edtStreak.Text = \"\"";
mostCurrent._edtstreak.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=1507356;
 //BA.debugLineNum = 1507356;BA.debugLine="ToastMessageShow(\"Inserted and ranked successfull";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Inserted and ranked successfully."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507357;
 //BA.debugLineNum = 1507357;BA.debugLine="End Sub";
return "";
}
public static String  _btnweekly_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnweekly_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnweekly_click", null));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Private Sub btnWeekly_Click";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="ShowBoard(\"WEEKLY\")";
_showboard("WEEKLY");
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="End Sub";
return "";
}
public static String  _setupmockdata() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setupmockdata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setupmockdata", null));}
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Private Sub SetupMockData";
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="InsertUserToDB(\"DAILY\", \"Mika\", 320, 7, 93)";
_insertusertodb("DAILY","Mika",(int) (320),(int) (7),(int) (93));
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="InsertUserToDB(\"DAILY\", \"Rei\", 295, 5, 90)";
_insertusertodb("DAILY","Rei",(int) (295),(int) (5),(int) (90));
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="InsertUserToDB(\"DAILY\", \"Noah\", 280, 4, 88)";
_insertusertodb("DAILY","Noah",(int) (280),(int) (4),(int) (88));
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="InsertUserToDB(\"DAILY\", \"Ava\", 250, 3, 86)";
_insertusertodb("DAILY","Ava",(int) (250),(int) (3),(int) (86));
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="InsertUserToDB(\"DAILY\", \"Luna\", 220, 2, 82)";
_insertusertodb("DAILY","Luna",(int) (220),(int) (2),(int) (82));
RDebugUtils.currentLine=458761;
 //BA.debugLineNum = 458761;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Mika\", 1760, 19, 92)";
_insertusertodb("WEEKLY","Mika",(int) (1760),(int) (19),(int) (92));
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Ava\", 1690, 13, 91)";
_insertusertodb("WEEKLY","Ava",(int) (1690),(int) (13),(int) (91));
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Rei\", 1610, 11, 89)";
_insertusertodb("WEEKLY","Rei",(int) (1610),(int) (11),(int) (89));
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Noah\", 1495, 9, 87)";
_insertusertodb("WEEKLY","Noah",(int) (1495),(int) (9),(int) (87));
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Luna\", 1420, 8, 85)";
_insertusertodb("WEEKLY","Luna",(int) (1420),(int) (8),(int) (85));
RDebugUtils.currentLine=458768;
 //BA.debugLineNum = 458768;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Ava\", 12850, 41, 90)";
_insertusertodb("ALLTIME","Ava",(int) (12850),(int) (41),(int) (90));
RDebugUtils.currentLine=458769;
 //BA.debugLineNum = 458769;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Mika\", 12110, 37, 91)";
_insertusertodb("ALLTIME","Mika",(int) (12110),(int) (37),(int) (91));
RDebugUtils.currentLine=458770;
 //BA.debugLineNum = 458770;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Noah\", 11680, 29, 88)";
_insertusertodb("ALLTIME","Noah",(int) (11680),(int) (29),(int) (88));
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Rei\", 11300, 24, 87)";
_insertusertodb("ALLTIME","Rei",(int) (11300),(int) (24),(int) (87));
RDebugUtils.currentLine=458772;
 //BA.debugLineNum = 458772;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Luna\", 10950, 22, 86)";
_insertusertodb("ALLTIME","Luna",(int) (10950),(int) (22),(int) (86));
RDebugUtils.currentLine=458773;
 //BA.debugLineNum = 458773;BA.debugLine="End Sub";
return "";
}
public static String  _highlightbutton(anywheresoftware.b4a.objects.ButtonWrapper _activebtn) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "highlightbutton", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "highlightbutton", new Object[] {_activebtn}));}
int _activecolor = 0;
int _normalcolor = 0;
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Private Sub HighlightButton(activeBtn As Button)";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="Dim activeColor As Int = xui.Color_ARGB(255, 141,";
_activecolor = mostCurrent._xui.Color_ARGB((int) (255),(int) (141),(int) (113),(int) (176));
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="Dim normalColor As Int = xui.Color_ARGB(255, 185,";
_normalcolor = mostCurrent._xui.Color_ARGB((int) (255),(int) (185),(int) (155),(int) (214));
RDebugUtils.currentLine=1245188;
 //BA.debugLineNum = 1245188;BA.debugLine="btnDaily.Color = normalColor";
mostCurrent._btndaily.setColor(_normalcolor);
RDebugUtils.currentLine=1245189;
 //BA.debugLineNum = 1245189;BA.debugLine="btnWeekly.Color = normalColor";
mostCurrent._btnweekly.setColor(_normalcolor);
RDebugUtils.currentLine=1245190;
 //BA.debugLineNum = 1245190;BA.debugLine="btnAllTime.Color = normalColor";
mostCurrent._btnalltime.setColor(_normalcolor);
RDebugUtils.currentLine=1245192;
 //BA.debugLineNum = 1245192;BA.debugLine="activeBtn.Color = activeColor";
_activebtn.setColor(_activecolor);
RDebugUtils.currentLine=1245193;
 //BA.debugLineNum = 1245193;BA.debugLine="End Sub";
return "";
}
public static String  _loadboardfromdb(String _mode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadboardfromdb", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadboardfromdb", new Object[] {_mode}));}
anywheresoftware.b4a.objects.collections.List _target = null;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Private Sub LoadBoardFromDB(mode As String)";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Dim target As List = GetModeList(mode)";
_target = new anywheresoftware.b4a.objects.collections.List();
_target = _getmodelist(_mode);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="target.Clear";
_target.Clear();
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="Dim rs As ResultSet = SQL1.ExecQuery2( _ 		\"SELEC";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(_sql1.ExecQuery2("SELECT name, xp, streak, correct_rate FROM leaderboard WHERE mode = ? "+"ORDER BY xp DESC, streak DESC, correct_rate DESC",new String[]{_mode.toUpperCase()})));
RDebugUtils.currentLine=917511;
 //BA.debugLineNum = 917511;BA.debugLine="Do While rs.NextRow";
while (_rs.NextRow()) {
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="target.Add(CreateUser(rs.GetString(\"name\"), rs.G";
_target.Add((Object)(_createuser(_rs.GetString("name"),_rs.GetInt("xp"),_rs.GetInt("streak"),_rs.GetInt("correct_rate"))));
 }
;
RDebugUtils.currentLine=917514;
 //BA.debugLineNum = 917514;BA.debugLine="rs.Close";
_rs.Close();
RDebugUtils.currentLine=917515;
 //BA.debugLineNum = 917515;BA.debugLine="End Sub";
return "";
}
public static boolean  _shouldswapforranking(b4a.example.main._userscore _a,b4a.example.main._userscore _b) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "shouldswapforranking", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "shouldswapforranking", new Object[] {_a,_b}));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub ShouldSwapForRanking(a As UserScore, b";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="If b.XP > a.XP Then Return True";
if (_b.XP /*int*/ >_a.XP /*int*/ ) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="If b.XP = a.XP And b.Streak > a.Streak Then Retur";
if (_b.XP /*int*/ ==_a.XP /*int*/  && _b.Streak /*int*/ >_a.Streak /*int*/ ) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="If b.XP = a.XP And b.Streak = a.Streak And b.Corr";
if (_b.XP /*int*/ ==_a.XP /*int*/  && _b.Streak /*int*/ ==_a.Streak /*int*/  && _b.CorrectRate /*int*/ >_a.CorrectRate /*int*/ ) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=1114116;
 //BA.debugLineNum = 1114116;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=1114117;
 //BA.debugLineNum = 1114117;BA.debugLine="End Sub";
return false;
}
}