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

public class leaderboard extends Activity implements B4AActivity{
	public static leaderboard mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.leaderboard");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (leaderboard).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.leaderboard");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.leaderboard", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (leaderboard) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (leaderboard) Resume **");
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
		return leaderboard.class;
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
            BA.LogInfo("** Activity (leaderboard) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (leaderboard) Pause event (activity is not paused). **");
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
            leaderboard mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (leaderboard) Resume **");
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
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
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
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=9306112;
 //BA.debugLineNum = 9306112;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=9306113;
 //BA.debugLineNum = 9306113;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=9306115;
 //BA.debugLineNum = 9306115;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=9306116;
 //BA.debugLineNum = 9306116;BA.debugLine="Activity.LoadLayout(\"leaderboard\")";
mostCurrent._activity.LoadLayout("leaderboard",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=9306118;
 //BA.debugLineNum = 9306118;BA.debugLine="Activity.LoadLayout(\"leaderboard\")";
mostCurrent._activity.LoadLayout("leaderboard",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=9306121;
 //BA.debugLineNum = 9306121;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=9306122;
 //BA.debugLineNum = 9306122;BA.debugLine="Activity.LoadLayout(\"leaderboard2\")";
mostCurrent._activity.LoadLayout("leaderboard2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=9306124;
 //BA.debugLineNum = 9306124;BA.debugLine="Activity.LoadLayout(\"leaderboarddark2\")";
mostCurrent._activity.LoadLayout("leaderboarddark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=9306127;
 //BA.debugLineNum = 9306127;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=9306128;
 //BA.debugLineNum = 9306128;BA.debugLine="Activity.LoadLayout(\"leaderboard3\")";
mostCurrent._activity.LoadLayout("leaderboard3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=9306130;
 //BA.debugLineNum = 9306130;BA.debugLine="Activity.LoadLayout(\"leaderboarddark3\")";
mostCurrent._activity.LoadLayout("leaderboarddark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=9306134;
 //BA.debugLineNum = 9306134;BA.debugLine="DailyList.Initialize";
mostCurrent._dailylist.Initialize();
RDebugUtils.currentLine=9306135;
 //BA.debugLineNum = 9306135;BA.debugLine="WeeklyList.Initialize";
mostCurrent._weeklylist.Initialize();
RDebugUtils.currentLine=9306136;
 //BA.debugLineNum = 9306136;BA.debugLine="AllTimeList.Initialize";
mostCurrent._alltimelist.Initialize();
RDebugUtils.currentLine=9306137;
 //BA.debugLineNum = 9306137;BA.debugLine="InitDB";
_initdb();
RDebugUtils.currentLine=9306138;
 //BA.debugLineNum = 9306138;BA.debugLine="EnsureSeedData";
_ensureseeddata();
RDebugUtils.currentLine=9306139;
 //BA.debugLineNum = 9306139;BA.debugLine="SetupInsertControls";
_setupinsertcontrols();
RDebugUtils.currentLine=9306140;
 //BA.debugLineNum = 9306140;BA.debugLine="ShowBoard(\"DAILY\")";
_showboard("DAILY");
RDebugUtils.currentLine=9306141;
 //BA.debugLineNum = 9306141;BA.debugLine="End Sub";
return "";
}
public static String  _initdb() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "initdb", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "initdb", null));}
RDebugUtils.currentLine=9830400;
 //BA.debugLineNum = 9830400;BA.debugLine="Private Sub InitDB";
RDebugUtils.currentLine=9830401;
 //BA.debugLineNum = 9830401;BA.debugLine="SQL1.Initialize(File.DirInternal, DB_NAME, True)";
_sql1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_db_name,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=9830402;
 //BA.debugLineNum = 9830402;BA.debugLine="SQL1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS lea";
_sql1.ExecNonQuery("CREATE TABLE IF NOT EXISTS leaderboard ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"mode TEXT, "+"name TEXT, "+"xp INTEGER, "+"streak INTEGER, "+"correct_rate INTEGER)");
RDebugUtils.currentLine=9830409;
 //BA.debugLineNum = 9830409;BA.debugLine="End Sub";
return "";
}
public static String  _ensureseeddata() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ensureseeddata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ensureseeddata", null));}
int _c = 0;
RDebugUtils.currentLine=9895936;
 //BA.debugLineNum = 9895936;BA.debugLine="Private Sub EnsureSeedData";
RDebugUtils.currentLine=9895937;
 //BA.debugLineNum = 9895937;BA.debugLine="Dim c As Int = SQL1.ExecQuerySingleResult(\"SELECT";
_c = (int)(Double.parseDouble(_sql1.ExecQuerySingleResult("SELECT COUNT(*) FROM leaderboard")));
RDebugUtils.currentLine=9895938;
 //BA.debugLineNum = 9895938;BA.debugLine="If c = 0 Then SetupMockData";
if (_c==0) { 
_setupmockdata();};
RDebugUtils.currentLine=9895939;
 //BA.debugLineNum = 9895939;BA.debugLine="End Sub";
return "";
}
public static String  _setupinsertcontrols() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setupinsertcontrols", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setupinsertcontrols", null));}
RDebugUtils.currentLine=9502720;
 //BA.debugLineNum = 9502720;BA.debugLine="Private Sub SetupInsertControls";
RDebugUtils.currentLine=9502721;
 //BA.debugLineNum = 9502721;BA.debugLine="pnlInsert.Initialize(\"\")";
mostCurrent._pnlinsert.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=9502722;
 //BA.debugLineNum = 9502722;BA.debugLine="pnlInsert.Color = xui.Color_ARGB(255, 255, 247, 2";
mostCurrent._pnlinsert.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (255),(int) (247),(int) (236)));
RDebugUtils.currentLine=9502723;
 //BA.debugLineNum = 9502723;BA.debugLine="Activity.AddView(pnlInsert, 8dip, Activity.Height";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlinsert.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),(int) (mostCurrent._activity.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (162))),(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (154)));
RDebugUtils.currentLine=9502725;
 //BA.debugLineNum = 9502725;BA.debugLine="edtName.Initialize(\"\")";
mostCurrent._edtname.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=9502726;
 //BA.debugLineNum = 9502726;BA.debugLine="edtName.Hint = \"Name\"";
mostCurrent._edtname.setHint("Name");
RDebugUtils.currentLine=9502727;
 //BA.debugLineNum = 9502727;BA.debugLine="edtName.TextSize = 16";
mostCurrent._edtname.setTextSize((float) (16));
RDebugUtils.currentLine=9502728;
 //BA.debugLineNum = 9502728;BA.debugLine="pnlInsert.AddView(edtName, 8dip, 6dip, pnlInsert.";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._edtname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)),(int) (mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (42)));
RDebugUtils.currentLine=9502730;
 //BA.debugLineNum = 9502730;BA.debugLine="edtXP.Initialize(\"\")";
mostCurrent._edtxp.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=9502731;
 //BA.debugLineNum = 9502731;BA.debugLine="edtXP.Hint = \"XP\"";
mostCurrent._edtxp.setHint("XP");
RDebugUtils.currentLine=9502732;
 //BA.debugLineNum = 9502732;BA.debugLine="edtXP.TextSize = 16";
mostCurrent._edtxp.setTextSize((float) (16));
RDebugUtils.currentLine=9502733;
 //BA.debugLineNum = 9502733;BA.debugLine="edtXP.InputType = edtXP.INPUT_TYPE_NUMBERS";
mostCurrent._edtxp.setInputType(mostCurrent._edtxp.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=9502734;
 //BA.debugLineNum = 9502734;BA.debugLine="pnlInsert.AddView(edtXP, 8dip, 54dip, (pnlInsert.";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._edtxp.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),(int) ((mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)))/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (42)));
RDebugUtils.currentLine=9502736;
 //BA.debugLineNum = 9502736;BA.debugLine="edtStreak.Initialize(\"\")";
mostCurrent._edtstreak.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=9502737;
 //BA.debugLineNum = 9502737;BA.debugLine="edtStreak.Hint = \"Streak\"";
mostCurrent._edtstreak.setHint("Streak");
RDebugUtils.currentLine=9502738;
 //BA.debugLineNum = 9502738;BA.debugLine="edtStreak.TextSize = 16";
mostCurrent._edtstreak.setTextSize((float) (16));
RDebugUtils.currentLine=9502739;
 //BA.debugLineNum = 9502739;BA.debugLine="edtStreak.InputType = edtStreak.INPUT_TYPE_NUMBER";
mostCurrent._edtstreak.setInputType(mostCurrent._edtstreak.INPUT_TYPE_NUMBERS);
RDebugUtils.currentLine=9502740;
 //BA.debugLineNum = 9502740;BA.debugLine="pnlInsert.AddView(edtStreak, edtXP.Left + edtXP.W";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._edtstreak.getObject()),(int) (mostCurrent._edtxp.getLeft()+mostCurrent._edtxp.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),(int) ((mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)))/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (42)));
RDebugUtils.currentLine=9502742;
 //BA.debugLineNum = 9502742;BA.debugLine="btnInsert.Initialize(\"btnInsert\")";
mostCurrent._btninsert.Initialize(mostCurrent.activityBA,"btnInsert");
RDebugUtils.currentLine=9502743;
 //BA.debugLineNum = 9502743;BA.debugLine="btnInsert.Text = \"Insert to Leaderboard\"";
mostCurrent._btninsert.setText(BA.ObjectToCharSequence("Insert to Leaderboard"));
RDebugUtils.currentLine=9502744;
 //BA.debugLineNum = 9502744;BA.debugLine="btnInsert.TextSize = 16";
mostCurrent._btninsert.setTextSize((float) (16));
RDebugUtils.currentLine=9502745;
 //BA.debugLineNum = 9502745;BA.debugLine="btnInsert.Color = xui.Color_ARGB(255, 141, 113, 1";
mostCurrent._btninsert.setColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (141),(int) (113),(int) (176)));
RDebugUtils.currentLine=9502746;
 //BA.debugLineNum = 9502746;BA.debugLine="btnInsert.TextColor = xui.Color_White";
mostCurrent._btninsert.setTextColor(mostCurrent._xui.Color_White);
RDebugUtils.currentLine=9502747;
 //BA.debugLineNum = 9502747;BA.debugLine="pnlInsert.AddView(btnInsert, 8dip, 102dip, pnlIns";
mostCurrent._pnlinsert.AddView((android.view.View)(mostCurrent._btninsert.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (102)),(int) (mostCurrent._pnlinsert.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (44)));
RDebugUtils.currentLine=9502748;
 //BA.debugLineNum = 9502748;BA.debugLine="End Sub";
return "";
}
public static String  _showboard(String _mode) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showboard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showboard", new Object[] {_mode}));}
anywheresoftware.b4a.objects.collections.List _source = null;
b4a.example.leaderboard._userscore _top = null;
int _i = 0;
b4a.example.leaderboard._userscore _u = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lblrank = null;
anywheresoftware.b4a.objects.LabelWrapper _lblname = null;
anywheresoftware.b4a.objects.LabelWrapper _lblstats = null;
RDebugUtils.currentLine=10289152;
 //BA.debugLineNum = 10289152;BA.debugLine="Private Sub ShowBoard(mode As String)";
RDebugUtils.currentLine=10289153;
 //BA.debugLineNum = 10289153;BA.debugLine="mode = mode.ToUpperCase";
_mode = _mode.toUpperCase();
RDebugUtils.currentLine=10289154;
 //BA.debugLineNum = 10289154;BA.debugLine="CurrentMode = mode";
mostCurrent._currentmode = _mode;
RDebugUtils.currentLine=10289156;
 //BA.debugLineNum = 10289156;BA.debugLine="clvBoard.AsView.SetLayoutAnimated(0, clvBoard.AsV";
mostCurrent._clvboard._asview().SetLayoutAnimated((int) (0),mostCurrent._clvboard._asview().getLeft(),mostCurrent._clvboard._asview().getTop(),mostCurrent._clvboard._asview().getWidth(),(int) (mostCurrent._activity.getHeight()-mostCurrent._clvboard._asview().getTop()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200))));
RDebugUtils.currentLine=10289159;
 //BA.debugLineNum = 10289159;BA.debugLine="Dim source As List";
_source = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=10289160;
 //BA.debugLineNum = 10289160;BA.debugLine="Select mode";
switch (BA.switchObjectToInt(_mode,"DAILY","WEEKLY")) {
case 0: {
RDebugUtils.currentLine=10289162;
 //BA.debugLineNum = 10289162;BA.debugLine="source = DailyList";
_source = mostCurrent._dailylist;
RDebugUtils.currentLine=10289163;
 //BA.debugLineNum = 10289163;BA.debugLine="lblSubtitle.Text = \"Today's top flashcard learn";
mostCurrent._lblsubtitle.setText(BA.ObjectToCharSequence("Today's top flashcard learners"));
 break; }
case 1: {
RDebugUtils.currentLine=10289165;
 //BA.debugLineNum = 10289165;BA.debugLine="source = WeeklyList";
_source = mostCurrent._weeklylist;
RDebugUtils.currentLine=10289166;
 //BA.debugLineNum = 10289166;BA.debugLine="lblSubtitle.Text = \"Weekly focus champions\"";
mostCurrent._lblsubtitle.setText(BA.ObjectToCharSequence("Weekly focus champions"));
 break; }
default: {
RDebugUtils.currentLine=10289168;
 //BA.debugLineNum = 10289168;BA.debugLine="source = AllTimeList";
_source = mostCurrent._alltimelist;
RDebugUtils.currentLine=10289169;
 //BA.debugLineNum = 10289169;BA.debugLine="lblSubtitle.Text = \"All-time cozy legends\"";
mostCurrent._lblsubtitle.setText(BA.ObjectToCharSequence("All-time cozy legends"));
 break; }
}
;
RDebugUtils.currentLine=10289172;
 //BA.debugLineNum = 10289172;BA.debugLine="LoadBoardFromDB(mode)";
_loadboardfromdb(_mode);
RDebugUtils.currentLine=10289173;
 //BA.debugLineNum = 10289173;BA.debugLine="If source.Size = 0 Then Return";
if (_source.getSize()==0) { 
if (true) return "";};
RDebugUtils.currentLine=10289176;
 //BA.debugLineNum = 10289176;BA.debugLine="Dim top As UserScore = source.Get(0)";
_top = (b4a.example.leaderboard._userscore)(_source.Get((int) (0)));
RDebugUtils.currentLine=10289177;
 //BA.debugLineNum = 10289177;BA.debugLine="lblTopUser.Text = \"🏆 \" & top.Name";
mostCurrent._lbltopuser.setText(BA.ObjectToCharSequence("🏆 "+_top.Name /*String*/ ));
RDebugUtils.currentLine=10289178;
 //BA.debugLineNum = 10289178;BA.debugLine="lblTopPoints.Text = top.XP & \" XP\"";
mostCurrent._lbltoppoints.setText(BA.ObjectToCharSequence(BA.NumberToString(_top.XP /*int*/ )+" XP"));
RDebugUtils.currentLine=10289179;
 //BA.debugLineNum = 10289179;BA.debugLine="lblTopStreak.Text = \"🔥 \" & top.Streak & \" day st";
mostCurrent._lbltopstreak.setText(BA.ObjectToCharSequence("🔥 "+BA.NumberToString(_top.Streak /*int*/ )+" day streak · "+BA.NumberToString(_top.CorrectRate /*int*/ )+"% correct"));
RDebugUtils.currentLine=10289182;
 //BA.debugLineNum = 10289182;BA.debugLine="clvBoard.Clear";
mostCurrent._clvboard._clear();
RDebugUtils.currentLine=10289183;
 //BA.debugLineNum = 10289183;BA.debugLine="For i = 0 To source.Size - 1";
{
final int step23 = 1;
final int limit23 = (int) (_source.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit23 ;_i = _i + step23 ) {
RDebugUtils.currentLine=10289184;
 //BA.debugLineNum = 10289184;BA.debugLine="Dim u As UserScore = source.Get(i)";
_u = (b4a.example.leaderboard._userscore)(_source.Get(_i));
RDebugUtils.currentLine=10289185;
 //BA.debugLineNum = 10289185;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=10289186;
 //BA.debugLineNum = 10289186;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=10289187;
 //BA.debugLineNum = 10289187;BA.debugLine="p.Color = xui.Color_ARGB(0, 255, 250, 242)";
_p.setColor(mostCurrent._xui.Color_ARGB((int) (0),(int) (255),(int) (250),(int) (242)));
RDebugUtils.currentLine=10289188;
 //BA.debugLineNum = 10289188;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, clvBoard.AsView.Wid";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),mostCurrent._clvboard._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=10289190;
 //BA.debugLineNum = 10289190;BA.debugLine="Dim lblRank As Label";
_lblrank = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=10289191;
 //BA.debugLineNum = 10289191;BA.debugLine="lblRank.Initialize(\"\")";
_lblrank.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=10289192;
 //BA.debugLineNum = 10289192;BA.debugLine="lblRank.Text = \"#\" & (i + 1)";
_lblrank.setText(BA.ObjectToCharSequence("#"+BA.NumberToString((_i+1))));
RDebugUtils.currentLine=10289193;
 //BA.debugLineNum = 10289193;BA.debugLine="lblRank.TextSize = 15";
_lblrank.setTextSize((float) (15));
RDebugUtils.currentLine=10289194;
 //BA.debugLineNum = 10289194;BA.debugLine="lblRank.Gravity = Gravity.CENTER_VERTICAL";
_lblrank.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=10289195;
 //BA.debugLineNum = 10289195;BA.debugLine="p.AddView(lblRank, 10dip, 0, 40dip, 62dip)";
_p.AddView((android.view.View)(_lblrank.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=10289197;
 //BA.debugLineNum = 10289197;BA.debugLine="Dim lblName As Label";
_lblname = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=10289198;
 //BA.debugLineNum = 10289198;BA.debugLine="lblName.Initialize(\"\")";
_lblname.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=10289199;
 //BA.debugLineNum = 10289199;BA.debugLine="lblName.Text = u.Name";
_lblname.setText(BA.ObjectToCharSequence(_u.Name /*String*/ ));
RDebugUtils.currentLine=10289200;
 //BA.debugLineNum = 10289200;BA.debugLine="lblName.TextSize = 16";
_lblname.setTextSize((float) (16));
RDebugUtils.currentLine=10289201;
 //BA.debugLineNum = 10289201;BA.debugLine="lblName.Gravity = Gravity.CENTER_VERTICAL";
_lblname.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=10289202;
 //BA.debugLineNum = 10289202;BA.debugLine="p.AddView(lblName, 55dip, 0, 110dip, 62dip)";
_p.AddView((android.view.View)(_lblname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=10289204;
 //BA.debugLineNum = 10289204;BA.debugLine="Dim lblStats As Label";
_lblstats = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=10289205;
 //BA.debugLineNum = 10289205;BA.debugLine="lblStats.Initialize(\"\")";
_lblstats.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=10289206;
 //BA.debugLineNum = 10289206;BA.debugLine="lblStats.Text = u.XP & \" XP   •   🔥\" & u.Streak";
_lblstats.setText(BA.ObjectToCharSequence(BA.NumberToString(_u.XP /*int*/ )+" XP   •   🔥"+BA.NumberToString(_u.Streak /*int*/ )+"   •   "+BA.NumberToString(_u.CorrectRate /*int*/ )+"%"));
RDebugUtils.currentLine=10289207;
 //BA.debugLineNum = 10289207;BA.debugLine="lblStats.TextSize = 13";
_lblstats.setTextSize((float) (13));
RDebugUtils.currentLine=10289208;
 //BA.debugLineNum = 10289208;BA.debugLine="lblStats.Gravity = Gravity.CENTER_VERTICAL";
_lblstats.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=10289209;
 //BA.debugLineNum = 10289209;BA.debugLine="p.AddView(lblStats, 165dip, 0, clvBoard.AsView.W";
_p.AddView((android.view.View)(_lblstats.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (165)),(int) (0),(int) (mostCurrent._clvboard._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (175))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (62)));
RDebugUtils.currentLine=10289211;
 //BA.debugLineNum = 10289211;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=10289213;
 //BA.debugLineNum = 10289213;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=10289214;
 //BA.debugLineNum = 10289214;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (84),(int) (72),(int) (101)));
RDebugUtils.currentLine=10289215;
 //BA.debugLineNum = 10289215;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (62),(int) (50),(int) (80)));
RDebugUtils.currentLine=10289216;
 //BA.debugLineNum = 10289216;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
 }else {
RDebugUtils.currentLine=10289218;
 //BA.debugLineNum = 10289218;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (84),(int) (72),(int) (101)));
RDebugUtils.currentLine=10289219;
 //BA.debugLineNum = 10289219;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (62),(int) (50),(int) (80)));
RDebugUtils.currentLine=10289220;
 //BA.debugLineNum = 10289220;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
 };
 break; }
case 1: {
RDebugUtils.currentLine=10289223;
 //BA.debugLineNum = 10289223;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=10289224;
 //BA.debugLineNum = 10289224;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (84),(int) (72),(int) (101)));
RDebugUtils.currentLine=10289225;
 //BA.debugLineNum = 10289225;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (62),(int) (50),(int) (80)));
RDebugUtils.currentLine=10289226;
 //BA.debugLineNum = 10289226;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
 }else {
RDebugUtils.currentLine=10289228;
 //BA.debugLineNum = 10289228;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (84),(int) (72),(int) (101)));
RDebugUtils.currentLine=10289229;
 //BA.debugLineNum = 10289229;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (62),(int) (50),(int) (80)));
RDebugUtils.currentLine=10289230;
 //BA.debugLineNum = 10289230;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
 };
 break; }
case 2: {
RDebugUtils.currentLine=10289233;
 //BA.debugLineNum = 10289233;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=10289234;
 //BA.debugLineNum = 10289234;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 255,";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=10289235;
 //BA.debugLineNum = 10289235;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 255,";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=10289236;
 //BA.debugLineNum = 10289236;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
 }else {
RDebugUtils.currentLine=10289238;
 //BA.debugLineNum = 10289238;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
_lblrank.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (84),(int) (72),(int) (101)));
RDebugUtils.currentLine=10289239;
 //BA.debugLineNum = 10289239;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
_lblname.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (62),(int) (50),(int) (80)));
RDebugUtils.currentLine=10289240;
 //BA.debugLineNum = 10289240;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
_lblstats.setTextColor(mostCurrent._xui.Color_ARGB((int) (255),(int) (122),(int) (98),(int) (82)));
 };
 break; }
}
;
RDebugUtils.currentLine=10289244;
 //BA.debugLineNum = 10289244;BA.debugLine="clvBoard.Add(p, u.Name)";
mostCurrent._clvboard._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())),(Object)(_u.Name /*String*/ ));
 }
};
RDebugUtils.currentLine=10289247;
 //BA.debugLineNum = 10289247;BA.debugLine="lblFooter.Text = \"Keep reviewing your flashcards";
mostCurrent._lblfooter.setText(BA.ObjectToCharSequence("Keep reviewing your flashcards to climb the cozy board ✨"));
RDebugUtils.currentLine=10289248;
 //BA.debugLineNum = 10289248;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="leaderboard";
RDebugUtils.currentLine=9437184;
 //BA.debugLineNum = 9437184;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=9437185;
 //BA.debugLineNum = 9437185;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=9371648;
 //BA.debugLineNum = 9371648;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=9371649;
 //BA.debugLineNum = 9371649;BA.debugLine="End Sub";
return "";
}
public static String  _adduserscore(String _mode,String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "adduserscore", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "adduserscore", new Object[] {_mode,_n,_xp,_st,_cr}));}
RDebugUtils.currentLine=9764864;
 //BA.debugLineNum = 9764864;BA.debugLine="Public Sub AddUserScore(mode As String, n As Strin";
RDebugUtils.currentLine=9764865;
 //BA.debugLineNum = 9764865;BA.debugLine="InsertUserToDB(mode, n, xp, st, cr)";
_insertusertodb(_mode,_n,_xp,_st,_cr);
RDebugUtils.currentLine=9764866;
 //BA.debugLineNum = 9764866;BA.debugLine="If mode = CurrentMode Then ShowBoard(mode)";
if ((_mode).equals(mostCurrent._currentmode)) { 
_showboard(_mode);};
RDebugUtils.currentLine=9764867;
 //BA.debugLineNum = 9764867;BA.debugLine="End Sub";
return "";
}
public static String  _insertusertodb(String _mode,String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "insertusertodb", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "insertusertodb", new Object[] {_mode,_n,_xp,_st,_cr}));}
RDebugUtils.currentLine=9961472;
 //BA.debugLineNum = 9961472;BA.debugLine="Private Sub InsertUserToDB(mode As String, n As St";
RDebugUtils.currentLine=9961473;
 //BA.debugLineNum = 9961473;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO leaderboard(mode,";
_sql1.ExecNonQuery2("INSERT INTO leaderboard(mode, name, xp, streak, correct_rate) VALUES (?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_mode.toUpperCase()),(Object)(_n),(Object)(_xp),(Object)(_st),(Object)(_cr)}));
RDebugUtils.currentLine=9961475;
 //BA.debugLineNum = 9961475;BA.debugLine="End Sub";
return "";
}
public static String  _addusertomode(String _mode,String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addusertomode", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addusertomode", new Object[] {_mode,_n,_xp,_st,_cr}));}
anywheresoftware.b4a.objects.collections.List _target = null;
RDebugUtils.currentLine=9699328;
 //BA.debugLineNum = 9699328;BA.debugLine="Private Sub AddUserToMode(mode As String, n As Str";
RDebugUtils.currentLine=9699329;
 //BA.debugLineNum = 9699329;BA.debugLine="Dim target As List = GetModeList(mode)";
_target = new anywheresoftware.b4a.objects.collections.List();
_target = _getmodelist(_mode);
RDebugUtils.currentLine=9699330;
 //BA.debugLineNum = 9699330;BA.debugLine="If target.IsInitialized = False Then Return";
if (_target.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=9699331;
 //BA.debugLineNum = 9699331;BA.debugLine="target.Add(CreateUser(n, xp, st, cr))";
_target.Add((Object)(_createuser(_n,_xp,_st,_cr)));
RDebugUtils.currentLine=9699332;
 //BA.debugLineNum = 9699332;BA.debugLine="SortBoard(target)";
_sortboard(_target);
RDebugUtils.currentLine=9699333;
 //BA.debugLineNum = 9699333;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _getmodelist(String _mode) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getmodelist", false))
	 {return ((anywheresoftware.b4a.objects.collections.List) Debug.delegate(mostCurrent.activityBA, "getmodelist", new Object[] {_mode}));}
RDebugUtils.currentLine=10092544;
 //BA.debugLineNum = 10092544;BA.debugLine="Private Sub GetModeList(mode As String) As List";
RDebugUtils.currentLine=10092545;
 //BA.debugLineNum = 10092545;BA.debugLine="Select mode.ToUpperCase";
switch (BA.switchObjectToInt(_mode.toUpperCase(),"DAILY","WEEKLY")) {
case 0: {
RDebugUtils.currentLine=10092547;
 //BA.debugLineNum = 10092547;BA.debugLine="Return DailyList";
if (true) return mostCurrent._dailylist;
 break; }
case 1: {
RDebugUtils.currentLine=10092549;
 //BA.debugLineNum = 10092549;BA.debugLine="Return WeeklyList";
if (true) return mostCurrent._weeklylist;
 break; }
default: {
RDebugUtils.currentLine=10092551;
 //BA.debugLineNum = 10092551;BA.debugLine="Return AllTimeList";
if (true) return mostCurrent._alltimelist;
 break; }
}
;
RDebugUtils.currentLine=10092553;
 //BA.debugLineNum = 10092553;BA.debugLine="End Sub";
return null;
}
public static b4a.example.leaderboard._userscore  _createuser(String _n,int _xp,int _st,int _cr) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createuser", false))
	 {return ((b4a.example.leaderboard._userscore) Debug.delegate(mostCurrent.activityBA, "createuser", new Object[] {_n,_xp,_st,_cr}));}
b4a.example.leaderboard._userscore _u = null;
RDebugUtils.currentLine=9633792;
 //BA.debugLineNum = 9633792;BA.debugLine="Private Sub CreateUser(n As String, xp As Int, st";
RDebugUtils.currentLine=9633793;
 //BA.debugLineNum = 9633793;BA.debugLine="Dim u As UserScore";
_u = new b4a.example.leaderboard._userscore();
RDebugUtils.currentLine=9633794;
 //BA.debugLineNum = 9633794;BA.debugLine="u.Initialize";
_u.Initialize();
RDebugUtils.currentLine=9633795;
 //BA.debugLineNum = 9633795;BA.debugLine="u.Name = n";
_u.Name /*String*/  = _n;
RDebugUtils.currentLine=9633796;
 //BA.debugLineNum = 9633796;BA.debugLine="u.XP = xp";
_u.XP /*int*/  = _xp;
RDebugUtils.currentLine=9633797;
 //BA.debugLineNum = 9633797;BA.debugLine="u.Streak = st";
_u.Streak /*int*/  = _st;
RDebugUtils.currentLine=9633798;
 //BA.debugLineNum = 9633798;BA.debugLine="u.CorrectRate = cr";
_u.CorrectRate /*int*/  = _cr;
RDebugUtils.currentLine=9633799;
 //BA.debugLineNum = 9633799;BA.debugLine="Return u";
if (true) return _u;
RDebugUtils.currentLine=9633800;
 //BA.debugLineNum = 9633800;BA.debugLine="End Sub";
return null;
}
public static String  _sortboard(anywheresoftware.b4a.objects.collections.List _board) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sortboard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sortboard", new Object[] {_board}));}
int _i = 0;
int _j = 0;
b4a.example.leaderboard._userscore _a = null;
b4a.example.leaderboard._userscore _b = null;
RDebugUtils.currentLine=10158080;
 //BA.debugLineNum = 10158080;BA.debugLine="Private Sub SortBoard(board As List)";
RDebugUtils.currentLine=10158081;
 //BA.debugLineNum = 10158081;BA.debugLine="For i = 0 To board.Size - 2";
{
final int step1 = 1;
final int limit1 = (int) (_board.getSize()-2);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
RDebugUtils.currentLine=10158082;
 //BA.debugLineNum = 10158082;BA.debugLine="For j = i + 1 To board.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (_board.getSize()-1);
_j = (int) (_i+1) ;
for (;_j <= limit2 ;_j = _j + step2 ) {
RDebugUtils.currentLine=10158083;
 //BA.debugLineNum = 10158083;BA.debugLine="Dim a As UserScore = board.Get(i)";
_a = (b4a.example.leaderboard._userscore)(_board.Get(_i));
RDebugUtils.currentLine=10158084;
 //BA.debugLineNum = 10158084;BA.debugLine="Dim b As UserScore = board.Get(j)";
_b = (b4a.example.leaderboard._userscore)(_board.Get(_j));
RDebugUtils.currentLine=10158085;
 //BA.debugLineNum = 10158085;BA.debugLine="If ShouldSwapForRanking(a, b) Then";
if (_shouldswapforranking(_a,_b)) { 
RDebugUtils.currentLine=10158086;
 //BA.debugLineNum = 10158086;BA.debugLine="board.Set(i, b)";
_board.Set(_i,(Object)(_b));
RDebugUtils.currentLine=10158087;
 //BA.debugLineNum = 10158087;BA.debugLine="board.Set(j, a)";
_board.Set(_j,(Object)(_a));
 };
 }
};
 }
};
RDebugUtils.currentLine=10158091;
 //BA.debugLineNum = 10158091;BA.debugLine="End Sub";
return "";
}
public static String  _btnalltime_click() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnalltime_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnalltime_click", null));}
RDebugUtils.currentLine=10485760;
 //BA.debugLineNum = 10485760;BA.debugLine="Private Sub btnAllTime_Click";
RDebugUtils.currentLine=10485761;
 //BA.debugLineNum = 10485761;BA.debugLine="ShowBoard(\"ALLTIME\")";
_showboard("ALLTIME");
RDebugUtils.currentLine=10485762;
 //BA.debugLineNum = 10485762;BA.debugLine="End Sub";
return "";
}
public static String  _btndaily_click() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btndaily_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btndaily_click", null));}
RDebugUtils.currentLine=10354688;
 //BA.debugLineNum = 10354688;BA.debugLine="Private Sub btnDaily_Click";
RDebugUtils.currentLine=10354689;
 //BA.debugLineNum = 10354689;BA.debugLine="ShowBoard(\"DAILY\")";
_showboard("DAILY");
RDebugUtils.currentLine=10354690;
 //BA.debugLineNum = 10354690;BA.debugLine="End Sub";
return "";
}
public static String  _btninsert_click() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btninsert_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btninsert_click", null));}
String _n = "";
String _xptext = "";
String _streaktext = "";
int _xp = 0;
int _st = 0;
RDebugUtils.currentLine=10551296;
 //BA.debugLineNum = 10551296;BA.debugLine="Private Sub btnInsert_Click";
RDebugUtils.currentLine=10551297;
 //BA.debugLineNum = 10551297;BA.debugLine="Dim n As String = edtName.Text.Trim";
_n = mostCurrent._edtname.getText().trim();
RDebugUtils.currentLine=10551298;
 //BA.debugLineNum = 10551298;BA.debugLine="Dim xpText As String = edtXP.Text.Trim";
_xptext = mostCurrent._edtxp.getText().trim();
RDebugUtils.currentLine=10551299;
 //BA.debugLineNum = 10551299;BA.debugLine="Dim streakText As String = edtStreak.Text.Trim";
_streaktext = mostCurrent._edtstreak.getText().trim();
RDebugUtils.currentLine=10551301;
 //BA.debugLineNum = 10551301;BA.debugLine="If n.Length = 0 Or xpText.Length = 0 Or streakTex";
if (_n.length()==0 || _xptext.length()==0 || _streaktext.length()==0) { 
RDebugUtils.currentLine=10551302;
 //BA.debugLineNum = 10551302;BA.debugLine="ToastMessageShow(\"Please fill Name, XP, and Stre";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Please fill Name, XP, and Streak."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10551303;
 //BA.debugLineNum = 10551303;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=10551306;
 //BA.debugLineNum = 10551306;BA.debugLine="If IsNumber(xpText) = False Or IsNumber(streakTex";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_xptext)==anywheresoftware.b4a.keywords.Common.False || anywheresoftware.b4a.keywords.Common.IsNumber(_streaktext)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10551307;
 //BA.debugLineNum = 10551307;BA.debugLine="ToastMessageShow(\"XP and Streak must be numbers.";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("XP and Streak must be numbers."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10551308;
 //BA.debugLineNum = 10551308;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=10551311;
 //BA.debugLineNum = 10551311;BA.debugLine="Dim xp As Int = xpText";
_xp = (int)(Double.parseDouble(_xptext));
RDebugUtils.currentLine=10551312;
 //BA.debugLineNum = 10551312;BA.debugLine="Dim st As Int = streakText";
_st = (int)(Double.parseDouble(_streaktext));
RDebugUtils.currentLine=10551313;
 //BA.debugLineNum = 10551313;BA.debugLine="If xp < 0 Or st < 0 Then";
if (_xp<0 || _st<0) { 
RDebugUtils.currentLine=10551314;
 //BA.debugLineNum = 10551314;BA.debugLine="ToastMessageShow(\"XP and Streak cannot be negati";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("XP and Streak cannot be negative."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10551315;
 //BA.debugLineNum = 10551315;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=10551319;
 //BA.debugLineNum = 10551319;BA.debugLine="AddUserScore(CurrentMode, n, xp, st, 0)";
_adduserscore(mostCurrent._currentmode,_n,_xp,_st,(int) (0));
RDebugUtils.currentLine=10551321;
 //BA.debugLineNum = 10551321;BA.debugLine="edtName.Text = \"\"";
mostCurrent._edtname.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=10551322;
 //BA.debugLineNum = 10551322;BA.debugLine="edtXP.Text = \"\"";
mostCurrent._edtxp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=10551323;
 //BA.debugLineNum = 10551323;BA.debugLine="edtStreak.Text = \"\"";
mostCurrent._edtstreak.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=10551324;
 //BA.debugLineNum = 10551324;BA.debugLine="ToastMessageShow(\"Inserted and ranked successfull";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Inserted and ranked successfully."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10551325;
 //BA.debugLineNum = 10551325;BA.debugLine="End Sub";
return "";
}
public static String  _btnweekly_click() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnweekly_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnweekly_click", null));}
RDebugUtils.currentLine=10420224;
 //BA.debugLineNum = 10420224;BA.debugLine="Private Sub btnWeekly_Click";
RDebugUtils.currentLine=10420225;
 //BA.debugLineNum = 10420225;BA.debugLine="ShowBoard(\"WEEKLY\")";
_showboard("WEEKLY");
RDebugUtils.currentLine=10420226;
 //BA.debugLineNum = 10420226;BA.debugLine="End Sub";
return "";
}
public static String  _setupmockdata() throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setupmockdata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setupmockdata", null));}
RDebugUtils.currentLine=9568256;
 //BA.debugLineNum = 9568256;BA.debugLine="Private Sub SetupMockData";
RDebugUtils.currentLine=9568258;
 //BA.debugLineNum = 9568258;BA.debugLine="InsertUserToDB(\"DAILY\", \"Mika\", 320, 7, 93)";
_insertusertodb("DAILY","Mika",(int) (320),(int) (7),(int) (93));
RDebugUtils.currentLine=9568259;
 //BA.debugLineNum = 9568259;BA.debugLine="InsertUserToDB(\"DAILY\", \"Rei\", 295, 5, 90)";
_insertusertodb("DAILY","Rei",(int) (295),(int) (5),(int) (90));
RDebugUtils.currentLine=9568260;
 //BA.debugLineNum = 9568260;BA.debugLine="InsertUserToDB(\"DAILY\", \"Noah\", 280, 4, 88)";
_insertusertodb("DAILY","Noah",(int) (280),(int) (4),(int) (88));
RDebugUtils.currentLine=9568261;
 //BA.debugLineNum = 9568261;BA.debugLine="InsertUserToDB(\"DAILY\", \"Ava\", 250, 3, 86)";
_insertusertodb("DAILY","Ava",(int) (250),(int) (3),(int) (86));
RDebugUtils.currentLine=9568262;
 //BA.debugLineNum = 9568262;BA.debugLine="InsertUserToDB(\"DAILY\", \"Luna\", 220, 2, 82)";
_insertusertodb("DAILY","Luna",(int) (220),(int) (2),(int) (82));
RDebugUtils.currentLine=9568265;
 //BA.debugLineNum = 9568265;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Mika\", 1760, 19, 92)";
_insertusertodb("WEEKLY","Mika",(int) (1760),(int) (19),(int) (92));
RDebugUtils.currentLine=9568266;
 //BA.debugLineNum = 9568266;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Ava\", 1690, 13, 91)";
_insertusertodb("WEEKLY","Ava",(int) (1690),(int) (13),(int) (91));
RDebugUtils.currentLine=9568267;
 //BA.debugLineNum = 9568267;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Rei\", 1610, 11, 89)";
_insertusertodb("WEEKLY","Rei",(int) (1610),(int) (11),(int) (89));
RDebugUtils.currentLine=9568268;
 //BA.debugLineNum = 9568268;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Noah\", 1495, 9, 87)";
_insertusertodb("WEEKLY","Noah",(int) (1495),(int) (9),(int) (87));
RDebugUtils.currentLine=9568269;
 //BA.debugLineNum = 9568269;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Luna\", 1420, 8, 85)";
_insertusertodb("WEEKLY","Luna",(int) (1420),(int) (8),(int) (85));
RDebugUtils.currentLine=9568272;
 //BA.debugLineNum = 9568272;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Ava\", 12850, 41, 90)";
_insertusertodb("ALLTIME","Ava",(int) (12850),(int) (41),(int) (90));
RDebugUtils.currentLine=9568273;
 //BA.debugLineNum = 9568273;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Mika\", 12110, 37, 91)";
_insertusertodb("ALLTIME","Mika",(int) (12110),(int) (37),(int) (91));
RDebugUtils.currentLine=9568274;
 //BA.debugLineNum = 9568274;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Noah\", 11680, 29, 88)";
_insertusertodb("ALLTIME","Noah",(int) (11680),(int) (29),(int) (88));
RDebugUtils.currentLine=9568275;
 //BA.debugLineNum = 9568275;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Rei\", 11300, 24, 87)";
_insertusertodb("ALLTIME","Rei",(int) (11300),(int) (24),(int) (87));
RDebugUtils.currentLine=9568276;
 //BA.debugLineNum = 9568276;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Luna\", 10950, 22, 86)";
_insertusertodb("ALLTIME","Luna",(int) (10950),(int) (22),(int) (86));
RDebugUtils.currentLine=9568277;
 //BA.debugLineNum = 9568277;BA.debugLine="End Sub";
return "";
}
public static String  _loadboardfromdb(String _mode) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadboardfromdb", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadboardfromdb", new Object[] {_mode}));}
anywheresoftware.b4a.objects.collections.List _target = null;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
RDebugUtils.currentLine=10027008;
 //BA.debugLineNum = 10027008;BA.debugLine="Private Sub LoadBoardFromDB(mode As String)";
RDebugUtils.currentLine=10027009;
 //BA.debugLineNum = 10027009;BA.debugLine="Dim target As List = GetModeList(mode)";
_target = new anywheresoftware.b4a.objects.collections.List();
_target = _getmodelist(_mode);
RDebugUtils.currentLine=10027010;
 //BA.debugLineNum = 10027010;BA.debugLine="target.Clear";
_target.Clear();
RDebugUtils.currentLine=10027011;
 //BA.debugLineNum = 10027011;BA.debugLine="Dim rs As ResultSet = SQL1.ExecQuery2( _ 		\"SELEC";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(_sql1.ExecQuery2("SELECT name, xp, streak, correct_rate FROM leaderboard WHERE mode = ? "+"ORDER BY xp DESC, streak DESC, correct_rate DESC",new String[]{_mode.toUpperCase()})));
RDebugUtils.currentLine=10027015;
 //BA.debugLineNum = 10027015;BA.debugLine="Do While rs.NextRow";
while (_rs.NextRow()) {
RDebugUtils.currentLine=10027016;
 //BA.debugLineNum = 10027016;BA.debugLine="target.Add(CreateUser(rs.GetString(\"name\"), rs.G";
_target.Add((Object)(_createuser(_rs.GetString("name"),_rs.GetInt("xp"),_rs.GetInt("streak"),_rs.GetInt("correct_rate"))));
 }
;
RDebugUtils.currentLine=10027018;
 //BA.debugLineNum = 10027018;BA.debugLine="rs.Close";
_rs.Close();
RDebugUtils.currentLine=10027019;
 //BA.debugLineNum = 10027019;BA.debugLine="End Sub";
return "";
}
public static boolean  _shouldswapforranking(b4a.example.leaderboard._userscore _a,b4a.example.leaderboard._userscore _b) throws Exception{
RDebugUtils.currentModule="leaderboard";
if (Debug.shouldDelegate(mostCurrent.activityBA, "shouldswapforranking", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "shouldswapforranking", new Object[] {_a,_b}));}
RDebugUtils.currentLine=10223616;
 //BA.debugLineNum = 10223616;BA.debugLine="Private Sub ShouldSwapForRanking(a As UserScore, b";
RDebugUtils.currentLine=10223617;
 //BA.debugLineNum = 10223617;BA.debugLine="If b.XP > a.XP Then Return True";
if (_b.XP /*int*/ >_a.XP /*int*/ ) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=10223618;
 //BA.debugLineNum = 10223618;BA.debugLine="If b.XP = a.XP And b.Streak > a.Streak Then Retur";
if (_b.XP /*int*/ ==_a.XP /*int*/  && _b.Streak /*int*/ >_a.Streak /*int*/ ) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=10223619;
 //BA.debugLineNum = 10223619;BA.debugLine="If b.XP = a.XP And b.Streak = a.Streak And b.Corr";
if (_b.XP /*int*/ ==_a.XP /*int*/  && _b.Streak /*int*/ ==_a.Streak /*int*/  && _b.CorrectRate /*int*/ >_a.CorrectRate /*int*/ ) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=10223620;
 //BA.debugLineNum = 10223620;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=10223621;
 //BA.debugLineNum = 10223621;BA.debugLine="End Sub";
return false;
}
}