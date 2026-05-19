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

public class musicactivity extends Activity implements B4AActivity{
	public static musicactivity mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.musicactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (musicactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.musicactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.musicactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (musicactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (musicactivity) Resume **");
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
		return musicactivity.class;
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
            BA.LogInfo("** Activity (musicactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (musicactivity) Pause event (activity is not paused). **");
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
            musicactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (musicactivity) Resume **");
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
public static anywheresoftware.b4a.objects.Timer _uitimer = null;
public static int _currentpage = 0;
public static int _totalpages = 0;
public static anywheresoftware.b4a.phone.Phone.ContentChooser _chooser = null;
public static boolean _skiptutorial = false;
public static boolean _savecheckstate = false;
public anywheresoftware.b4a.objects.LabelWrapper _lbldesc = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnnext = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkdontshow = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _songtitle = null;
public anywheresoftware.b4a.objects.ButtonWrapper _pausebtn = null;
public anywheresoftware.b4a.objects.LabelWrapper _songruntime = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnupload = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.musicservice _musicservice = null;
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
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=10682368;
 //BA.debugLineNum = 10682368;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=10682369;
 //BA.debugLineNum = 10682369;BA.debugLine="chooser.Initialize(\"chooser\")";
_chooser.Initialize("chooser");
RDebugUtils.currentLine=10682370;
 //BA.debugLineNum = 10682370;BA.debugLine="currentPage = 0";
_currentpage = (int) (0);
RDebugUtils.currentLine=10682372;
 //BA.debugLineNum = 10682372;BA.debugLine="If skipTutorial Then";
if (_skiptutorial) { 
RDebugUtils.currentLine=10682373;
 //BA.debugLineNum = 10682373;BA.debugLine="LoadMusicPlayer";
_loadmusicplayer();
 }else {
RDebugUtils.currentLine=10682375;
 //BA.debugLineNum = 10682375;BA.debugLine="ShowTutorialPage";
_showtutorialpage();
 };
RDebugUtils.currentLine=10682377;
 //BA.debugLineNum = 10682377;BA.debugLine="End Sub";
return "";
}
public static String  _loadmusicplayer() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadmusicplayer", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadmusicplayer", null));}
int _i = 0;
String _rawpath = "";
String _displaytitle = "";
RDebugUtils.currentLine=10878976;
 //BA.debugLineNum = 10878976;BA.debugLine="Sub LoadMusicPlayer";
RDebugUtils.currentLine=10878977;
 //BA.debugLineNum = 10878977;BA.debugLine="Activity.RemoveAllViews";
mostCurrent._activity.RemoveAllViews();
RDebugUtils.currentLine=10878979;
 //BA.debugLineNum = 10878979;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=10878981;
 //BA.debugLineNum = 10878981;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10878982;
 //BA.debugLineNum = 10878982;BA.debugLine="Activity.LoadLayout(\"musicLayout\")";
mostCurrent._activity.LoadLayout("musicLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=10878984;
 //BA.debugLineNum = 10878984;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark\")";
mostCurrent._activity.LoadLayout("musicLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=10878987;
 //BA.debugLineNum = 10878987;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10878988;
 //BA.debugLineNum = 10878988;BA.debugLine="Activity.LoadLayout(\"musicLayout2\")";
mostCurrent._activity.LoadLayout("musicLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=10878990;
 //BA.debugLineNum = 10878990;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark2\")";
mostCurrent._activity.LoadLayout("musicLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=10878993;
 //BA.debugLineNum = 10878993;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10878994;
 //BA.debugLineNum = 10878994;BA.debugLine="Activity.LoadLayout(\"musicLayout3\")";
mostCurrent._activity.LoadLayout("musicLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=10878996;
 //BA.debugLineNum = 10878996;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark3\")";
mostCurrent._activity.LoadLayout("musicLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=10879000;
 //BA.debugLineNum = 10879000;BA.debugLine="If musicService.mediaPlayer.IsInitialized = False";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10879001;
 //BA.debugLineNum = 10879001;BA.debugLine="StartService(musicService)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._musicservice.getObject()));
 };
RDebugUtils.currentLine=10879006;
 //BA.debugLineNum = 10879006;BA.debugLine="For i = 0 To musicService.musicPlaylist.Size - 1";
{
final int step25 = 1;
final int limit25 = (int) (mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .getSize()-1);
_i = (int) (0) ;
for (;_i <= limit25 ;_i = _i + step25 ) {
RDebugUtils.currentLine=10879007;
 //BA.debugLineNum = 10879007;BA.debugLine="Dim rawPath As String";
_rawpath = "";
RDebugUtils.currentLine=10879008;
 //BA.debugLineNum = 10879008;BA.debugLine="rawPath = musicService.musicPlaylist.Get(i)";
_rawpath = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_i));
RDebugUtils.currentLine=10879009;
 //BA.debugLineNum = 10879009;BA.debugLine="Dim displayTitle As String";
_displaytitle = "";
RDebugUtils.currentLine=10879010;
 //BA.debugLineNum = 10879010;BA.debugLine="displayTitle = getDisplayTitle(rawPath)";
_displaytitle = _getdisplaytitle(_rawpath);
RDebugUtils.currentLine=10879011;
 //BA.debugLineNum = 10879011;BA.debugLine="ListView1.AddSingleLine((i + 1) & \"   \" & displa";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence(BA.NumberToString((_i+1))+"   "+_displaytitle));
RDebugUtils.currentLine=10879012;
 //BA.debugLineNum = 10879012;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=10879013;
 //BA.debugLineNum = 10879013;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Co";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=10879015;
 //BA.debugLineNum = 10879015;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Co";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (24),(int) (20),(int) (37)));
 };
 }
};
RDebugUtils.currentLine=10879019;
 //BA.debugLineNum = 10879019;BA.debugLine="uiTimer.Initialize(\"uiTimer\", 500)";
_uitimer.Initialize(processBA,"uiTimer",(long) (500));
RDebugUtils.currentLine=10879020;
 //BA.debugLineNum = 10879020;BA.debugLine="uiTimer.Enabled = True";
_uitimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=10879021;
 //BA.debugLineNum = 10879021;BA.debugLine="End Sub";
return "";
}
public static String  _showtutorialpage() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showtutorialpage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showtutorialpage", null));}
RDebugUtils.currentLine=10747904;
 //BA.debugLineNum = 10747904;BA.debugLine="Sub ShowTutorialPage";
RDebugUtils.currentLine=10747905;
 //BA.debugLineNum = 10747905;BA.debugLine="Activity.RemoveAllViews";
mostCurrent._activity.RemoveAllViews();
RDebugUtils.currentLine=10747908;
 //BA.debugLineNum = 10747908;BA.debugLine="lblDesc.Initialize(\"\")";
mostCurrent._lbldesc.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=10747909;
 //BA.debugLineNum = 10747909;BA.debugLine="lblDesc.TextSize = 20";
mostCurrent._lbldesc.setTextSize((float) (20));
RDebugUtils.currentLine=10747910;
 //BA.debugLineNum = 10747910;BA.debugLine="lblDesc.TextColor = Colors.RGB(20, 40, 80)";
mostCurrent._lbldesc.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (20),(int) (40),(int) (80)));
RDebugUtils.currentLine=10747911;
 //BA.debugLineNum = 10747911;BA.debugLine="lblDesc.Gravity = Gravity.CENTER";
mostCurrent._lbldesc.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=10747912;
 //BA.debugLineNum = 10747912;BA.debugLine="lblDesc.Typeface = Typeface.DEFAULT_BOLD";
mostCurrent._lbldesc.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=10747913;
 //BA.debugLineNum = 10747913;BA.debugLine="lblDesc.SetLayout(20dip, 30dip, 100%x - 40dip, 60";
mostCurrent._lbldesc.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (60),mostCurrent.activityBA));
RDebugUtils.currentLine=10747914;
 //BA.debugLineNum = 10747914;BA.debugLine="Activity.AddView(lblDesc, 20dip, 30dip, 100%x - 4";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lbldesc.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (60),mostCurrent.activityBA));
RDebugUtils.currentLine=10747917;
 //BA.debugLineNum = 10747917;BA.debugLine="If currentPage = 2 Then";
if (_currentpage==2) { 
RDebugUtils.currentLine=10747918;
 //BA.debugLineNum = 10747918;BA.debugLine="chkDontShow.Initialize(\"chkDontShow\")";
mostCurrent._chkdontshow.Initialize(mostCurrent.activityBA,"chkDontShow");
RDebugUtils.currentLine=10747919;
 //BA.debugLineNum = 10747919;BA.debugLine="chkDontShow.Text = \"🙈 Don't show this again\"";
mostCurrent._chkdontshow.setText(BA.ObjectToCharSequence("🙈 Don't show this again"));
RDebugUtils.currentLine=10747920;
 //BA.debugLineNum = 10747920;BA.debugLine="chkDontShow.TextSize = 14";
mostCurrent._chkdontshow.setTextSize((float) (14));
RDebugUtils.currentLine=10747921;
 //BA.debugLineNum = 10747921;BA.debugLine="chkDontShow.Typeface = Typeface.DEFAULT";
mostCurrent._chkdontshow.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT);
RDebugUtils.currentLine=10747922;
 //BA.debugLineNum = 10747922;BA.debugLine="chkDontShow.Checked = saveCheckState";
mostCurrent._chkdontshow.setChecked(_savecheckstate);
RDebugUtils.currentLine=10747923;
 //BA.debugLineNum = 10747923;BA.debugLine="chkDontShow.SetLayout(20dip, 55%y, 200dip, 30dip)";
mostCurrent._chkdontshow.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (55),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=10747924;
 //BA.debugLineNum = 10747924;BA.debugLine="Activity.AddView(chkDontShow, 20dip, 55%y, 200dip";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._chkdontshow.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (55),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 };
RDebugUtils.currentLine=10747928;
 //BA.debugLineNum = 10747928;BA.debugLine="btnnext.Initialize(\"btnnext\")";
mostCurrent._btnnext.Initialize(mostCurrent.activityBA,"btnnext");
RDebugUtils.currentLine=10747929;
 //BA.debugLineNum = 10747929;BA.debugLine="btnnext.TextSize = 16";
mostCurrent._btnnext.setTextSize((float) (16));
RDebugUtils.currentLine=10747930;
 //BA.debugLineNum = 10747930;BA.debugLine="btnnext.Color = Colors.RGB(100, 120, 180)";
mostCurrent._btnnext.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (100),(int) (120),(int) (180)));
RDebugUtils.currentLine=10747931;
 //BA.debugLineNum = 10747931;BA.debugLine="btnnext.TextColor = Colors.White";
mostCurrent._btnnext.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=10747932;
 //BA.debugLineNum = 10747932;BA.debugLine="btnnext.Typeface = Typeface.DEFAULT_BOLD";
mostCurrent._btnnext.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=10747933;
 //BA.debugLineNum = 10747933;BA.debugLine="btnnext.SetLayout(50dip, 60%y, 200dip, 50dip)";
mostCurrent._btnnext.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=10747934;
 //BA.debugLineNum = 10747934;BA.debugLine="Activity.AddView(btnnext, 50dip, 60%y, 200dip, 50";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnnext.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=10747937;
 //BA.debugLineNum = 10747937;BA.debugLine="If currentPage = 0 Then";
if (_currentpage==0) { 
RDebugUtils.currentLine=10747938;
 //BA.debugLineNum = 10747938;BA.debugLine="lblDesc.Text = \"🎵 WELCOME TO MUSIC PLAYER 🎵\" &";
mostCurrent._lbldesc.setText(BA.ObjectToCharSequence("🎵 WELCOME TO MUSIC PLAYER 🎵"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Easily play, pause, skip "+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"and enjoy all your music "+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Everything you need is right here "));
RDebugUtils.currentLine=10747942;
 //BA.debugLineNum = 10747942;BA.debugLine="btnnext.Text = \"➡️ Next\"";
mostCurrent._btnnext.setText(BA.ObjectToCharSequence("➡️ Next"));
 }else 
{RDebugUtils.currentLine=10747944;
 //BA.debugLineNum = 10747944;BA.debugLine="else If currentPage = 1 Then";
if (_currentpage==1) { 
RDebugUtils.currentLine=10747945;
 //BA.debugLineNum = 10747945;BA.debugLine="lblDesc.Text = \"️HOW TO USE THE BUTTONS: ️\" & CR";
mostCurrent._lbldesc.setText(BA.ObjectToCharSequence("️HOW TO USE THE BUTTONS: ️"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"⏮️ PREVIOUS: Go back to last song "+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"⏯️ PLAY/PAUSE: Start/stop music"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"⏭️ NEXT: Go to the next song"));
RDebugUtils.currentLine=10747949;
 //BA.debugLineNum = 10747949;BA.debugLine="btnnext.Text = \"➡️ Next\"";
mostCurrent._btnnext.setText(BA.ObjectToCharSequence("➡️ Next"));
 }else 
{RDebugUtils.currentLine=10747951;
 //BA.debugLineNum = 10747951;BA.debugLine="else If currentPage = 2 Then";
if (_currentpage==2) { 
RDebugUtils.currentLine=10747952;
 //BA.debugLineNum = 10747952;BA.debugLine="lblDesc.Text = \"✅ ALL DONE! ✅\" & CRLF & CRLF & _";
mostCurrent._lbldesc.setText(BA.ObjectToCharSequence("✅ ALL DONE! ✅"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Now you know all features "+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Upload your own music"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Anytime from the Main player "+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Enjoy listening!"));
RDebugUtils.currentLine=10747957;
 //BA.debugLineNum = 10747957;BA.debugLine="btnnext.Text = \"✅ Finish\"";
mostCurrent._btnnext.setText(BA.ObjectToCharSequence("✅ Finish"));
 }}}
;
RDebugUtils.currentLine=10747959;
 //BA.debugLineNum = 10747959;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="musicactivity";
RDebugUtils.currentLine=11337728;
 //BA.debugLineNum = 11337728;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
RDebugUtils.currentLine=11337730;
 //BA.debugLineNum = 11337730;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=11272192;
 //BA.debugLineNum = 11272192;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=11272194;
 //BA.debugLineNum = 11272194;BA.debugLine="End Sub";
return "";
}
public static String  _btnnext_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnnext_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnnext_click", null));}
RDebugUtils.currentLine=10813440;
 //BA.debugLineNum = 10813440;BA.debugLine="Sub btnnext_Click";
RDebugUtils.currentLine=10813441;
 //BA.debugLineNum = 10813441;BA.debugLine="currentPage = currentPage + 1";
_currentpage = (int) (_currentpage+1);
RDebugUtils.currentLine=10813443;
 //BA.debugLineNum = 10813443;BA.debugLine="If currentPage < totalPages Then";
if (_currentpage<_totalpages) { 
RDebugUtils.currentLine=10813444;
 //BA.debugLineNum = 10813444;BA.debugLine="ShowTutorialPage";
_showtutorialpage();
 }else {
RDebugUtils.currentLine=10813447;
 //BA.debugLineNum = 10813447;BA.debugLine="If chkDontShow.Checked Then";
if (mostCurrent._chkdontshow.getChecked()) { 
RDebugUtils.currentLine=10813448;
 //BA.debugLineNum = 10813448;BA.debugLine="skipTutorial = True";
_skiptutorial = anywheresoftware.b4a.keywords.Common.True;
 }else {
RDebugUtils.currentLine=10813450;
 //BA.debugLineNum = 10813450;BA.debugLine="skipTutorial = False";
_skiptutorial = anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=10813453;
 //BA.debugLineNum = 10813453;BA.debugLine="LoadMusicPlayer";
_loadmusicplayer();
 };
RDebugUtils.currentLine=10813455;
 //BA.debugLineNum = 10813455;BA.debugLine="End Sub";
return "";
}
public static String  _btnupload_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnupload_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnupload_click", null));}
RDebugUtils.currentLine=11010048;
 //BA.debugLineNum = 11010048;BA.debugLine="Sub btnUpload_Click";
RDebugUtils.currentLine=11010049;
 //BA.debugLineNum = 11010049;BA.debugLine="chooser.Show(\"audio/*\", \"Choose Music File\")";
_chooser.Show(processBA,"audio/*","Choose Music File");
RDebugUtils.currentLine=11010050;
 //BA.debugLineNum = 11010050;BA.debugLine="End Sub";
return "";
}
public static String  _chooser_result(boolean _success,String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "chooser_result", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "chooser_result", new Object[] {_success,_dir,_filename}));}
String _fulluri = "";
String _destname = "";
String _destdir = "";
String _cachedpath = "";
String _displaytitle = "";
int _idx = 0;
RDebugUtils.currentLine=11075584;
 //BA.debugLineNum = 11075584;BA.debugLine="Sub chooser_Result (Success As Boolean, Dir As Str";
RDebugUtils.currentLine=11075585;
 //BA.debugLineNum = 11075585;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=11075593;
 //BA.debugLineNum = 11075593;BA.debugLine="Dim fullUri As String";
_fulluri = "";
RDebugUtils.currentLine=11075594;
 //BA.debugLineNum = 11075594;BA.debugLine="fullUri = Dir & FileName";
_fulluri = _dir+_filename;
RDebugUtils.currentLine=11075598;
 //BA.debugLineNum = 11075598;BA.debugLine="Dim destName As String";
_destname = "";
RDebugUtils.currentLine=11075599;
 //BA.debugLineNum = 11075599;BA.debugLine="destName = fullUri.SubString(fullUri.LastIndexOf";
_destname = _fulluri.substring((int) (_fulluri.lastIndexOf("/")+1));
RDebugUtils.currentLine=11075600;
 //BA.debugLineNum = 11075600;BA.debugLine="destName = destName.Replace(\"%20\", \" \")";
_destname = _destname.replace("%20"," ");
RDebugUtils.currentLine=11075601;
 //BA.debugLineNum = 11075601;BA.debugLine="destName = destName.Replace(\"%26\", \"&\")";
_destname = _destname.replace("%26","&");
RDebugUtils.currentLine=11075602;
 //BA.debugLineNum = 11075602;BA.debugLine="destName = destName.Replace(\"%27\", \"'\")";
_destname = _destname.replace("%27","'");
RDebugUtils.currentLine=11075603;
 //BA.debugLineNum = 11075603;BA.debugLine="destName = destName.Replace(\"%28\", \"(\")";
_destname = _destname.replace("%28","(");
RDebugUtils.currentLine=11075604;
 //BA.debugLineNum = 11075604;BA.debugLine="destName = destName.Replace(\"%29\", \")\")";
_destname = _destname.replace("%29",")");
RDebugUtils.currentLine=11075605;
 //BA.debugLineNum = 11075605;BA.debugLine="destName = destName.Replace(\"%2C\", \",\")";
_destname = _destname.replace("%2C",",");
RDebugUtils.currentLine=11075608;
 //BA.debugLineNum = 11075608;BA.debugLine="If destName.EndsWith(\".mp3\") = False And destNam";
if (_destname.endsWith(".mp3")==anywheresoftware.b4a.keywords.Common.False && _destname.endsWith(".MP3")==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=11075609;
 //BA.debugLineNum = 11075609;BA.debugLine="destName = destName & \".mp3\"";
_destname = _destname+".mp3";
 };
RDebugUtils.currentLine=11075613;
 //BA.debugLineNum = 11075613;BA.debugLine="Dim destDir As String";
_destdir = "";
RDebugUtils.currentLine=11075614;
 //BA.debugLineNum = 11075614;BA.debugLine="destDir = File.DirInternalCache";
_destdir = anywheresoftware.b4a.keywords.Common.File.getDirInternalCache();
RDebugUtils.currentLine=11075616;
 //BA.debugLineNum = 11075616;BA.debugLine="Try";
try {RDebugUtils.currentLine=11075617;
 //BA.debugLineNum = 11075617;BA.debugLine="File.Copy(Dir, FileName, destDir, destName)";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,_destdir,_destname);
 } 
       catch (Exception e20) {
			processBA.setLastException(e20);RDebugUtils.currentLine=11075619;
 //BA.debugLineNum = 11075619;BA.debugLine="ToastMessageShow(\"❌ Could not copy file: \" & La";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("❌ Could not copy file: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=11075620;
 //BA.debugLineNum = 11075620;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=11075624;
 //BA.debugLineNum = 11075624;BA.debugLine="Dim cachedPath As String";
_cachedpath = "";
RDebugUtils.currentLine=11075625;
 //BA.debugLineNum = 11075625;BA.debugLine="cachedPath = destDir & \"/\" & destName";
_cachedpath = _destdir+"/"+_destname;
RDebugUtils.currentLine=11075626;
 //BA.debugLineNum = 11075626;BA.debugLine="musicService.musicPlaylist.Add(cachedPath)";
mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Add((Object)(_cachedpath));
RDebugUtils.currentLine=11075629;
 //BA.debugLineNum = 11075629;BA.debugLine="Dim displayTitle As String";
_displaytitle = "";
RDebugUtils.currentLine=11075630;
 //BA.debugLineNum = 11075630;BA.debugLine="displayTitle = getDisplayTitle(cachedPath)";
_displaytitle = _getdisplaytitle(_cachedpath);
RDebugUtils.currentLine=11075631;
 //BA.debugLineNum = 11075631;BA.debugLine="Dim idx As Int = musicService.musicPlaylist.Size";
_idx = mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .getSize();
RDebugUtils.currentLine=11075632;
 //BA.debugLineNum = 11075632;BA.debugLine="ListView1.AddSingleLine(idx & \"   \" & displayTit";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence(BA.NumberToString(_idx)+"   "+_displaytitle));
RDebugUtils.currentLine=11075634;
 //BA.debugLineNum = 11075634;BA.debugLine="ToastMessageShow(\"✅ Added: \" & displayTitle, Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("✅ Added: "+_displaytitle),anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=11075636;
 //BA.debugLineNum = 11075636;BA.debugLine="End Sub";
return "";
}
public static String  _getdisplaytitle(String _rawpath) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getdisplaytitle", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getdisplaytitle", new Object[] {_rawpath}));}
String _name = "";
RDebugUtils.currentLine=10944512;
 //BA.debugLineNum = 10944512;BA.debugLine="Sub getDisplayTitle(rawPath As String) As String";
RDebugUtils.currentLine=10944513;
 //BA.debugLineNum = 10944513;BA.debugLine="Dim name As String";
_name = "";
RDebugUtils.currentLine=10944514;
 //BA.debugLineNum = 10944514;BA.debugLine="name = rawPath.SubString(rawPath.LastIndexOf(\"/\")";
_name = _rawpath.substring((int) (_rawpath.lastIndexOf("/")+1));
RDebugUtils.currentLine=10944516;
 //BA.debugLineNum = 10944516;BA.debugLine="If name.EndsWith(\".mp3\") Or name.EndsWith(\".MP3\")";
if (_name.endsWith(".mp3") || _name.endsWith(".MP3")) { 
RDebugUtils.currentLine=10944517;
 //BA.debugLineNum = 10944517;BA.debugLine="name = name.SubString2(0, name.Length - 4)";
_name = _name.substring((int) (0),(int) (_name.length()-4));
 };
RDebugUtils.currentLine=10944520;
 //BA.debugLineNum = 10944520;BA.debugLine="name = name.Replace(\"%20\", \" \")";
_name = _name.replace("%20"," ");
RDebugUtils.currentLine=10944521;
 //BA.debugLineNum = 10944521;BA.debugLine="name = name.Replace(\"%26\", \"&\")";
_name = _name.replace("%26","&");
RDebugUtils.currentLine=10944522;
 //BA.debugLineNum = 10944522;BA.debugLine="name = name.Replace(\"%27\", \"'\")";
_name = _name.replace("%27","'");
RDebugUtils.currentLine=10944523;
 //BA.debugLineNum = 10944523;BA.debugLine="name = name.Replace(\"%28\", \"(\")";
_name = _name.replace("%28","(");
RDebugUtils.currentLine=10944524;
 //BA.debugLineNum = 10944524;BA.debugLine="name = name.Replace(\"%29\", \")\")";
_name = _name.replace("%29",")");
RDebugUtils.currentLine=10944525;
 //BA.debugLineNum = 10944525;BA.debugLine="name = name.Replace(\"%2C\", \",\")";
_name = _name.replace("%2C",",");
RDebugUtils.currentLine=10944526;
 //BA.debugLineNum = 10944526;BA.debugLine="Return name";
if (true) return _name;
RDebugUtils.currentLine=10944527;
 //BA.debugLineNum = 10944527;BA.debugLine="End Sub";
return "";
}
public static String  _formatsongdur(int _ms) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "formatsongdur", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "formatsongdur", new Object[] {_ms}));}
int _seconds = 0;
int _minutes = 0;
RDebugUtils.currentLine=11403264;
 //BA.debugLineNum = 11403264;BA.debugLine="Sub formatSongDur(ms As Int) As String";
RDebugUtils.currentLine=11403265;
 //BA.debugLineNum = 11403265;BA.debugLine="Dim seconds As Int";
_seconds = 0;
RDebugUtils.currentLine=11403266;
 //BA.debugLineNum = 11403266;BA.debugLine="Dim minutes As Int";
_minutes = 0;
RDebugUtils.currentLine=11403267;
 //BA.debugLineNum = 11403267;BA.debugLine="seconds = ms / 1000";
_seconds = (int) (_ms/(double)1000);
RDebugUtils.currentLine=11403268;
 //BA.debugLineNum = 11403268;BA.debugLine="minutes = seconds / 60";
_minutes = (int) (_seconds/(double)60);
RDebugUtils.currentLine=11403269;
 //BA.debugLineNum = 11403269;BA.debugLine="seconds = seconds Mod 60";
_seconds = (int) (_seconds%60);
RDebugUtils.currentLine=11403270;
 //BA.debugLineNum = 11403270;BA.debugLine="Return NumberFormat(minutes, 2, 0) & \":\" & Number";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_minutes,(int) (2),(int) (0))+":"+anywheresoftware.b4a.keywords.Common.NumberFormat(_seconds,(int) (2),(int) (0));
RDebugUtils.currentLine=11403271;
 //BA.debugLineNum = 11403271;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listview1_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listview1_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=11206656;
 //BA.debugLineNum = 11206656;BA.debugLine="Sub ListView1_ItemClick(Position As Int, Value As";
RDebugUtils.currentLine=11206657;
 //BA.debugLineNum = 11206657;BA.debugLine="CallSub2(musicService, \"setSong\", Position)";
anywheresoftware.b4a.keywords.Common.CallSubDebug2(processBA,(Object)(mostCurrent._musicservice.getObject()),"setSong",(Object)(_position));
RDebugUtils.currentLine=11206658;
 //BA.debugLineNum = 11206658;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemlongclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listview1_itemlongclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listview1_itemlongclick", new Object[] {_position,_value}));}
String _rawpath = "";
String _displaytitle = "";
String _sourcelabel = "";
RDebugUtils.currentLine=11141120;
 //BA.debugLineNum = 11141120;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
RDebugUtils.currentLine=11141122;
 //BA.debugLineNum = 11141122;BA.debugLine="Dim rawPath As String";
_rawpath = "";
RDebugUtils.currentLine=11141123;
 //BA.debugLineNum = 11141123;BA.debugLine="rawPath = musicService.musicPlaylist.Get(Position";
_rawpath = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_position));
RDebugUtils.currentLine=11141124;
 //BA.debugLineNum = 11141124;BA.debugLine="Dim displayTitle As String";
_displaytitle = "";
RDebugUtils.currentLine=11141125;
 //BA.debugLineNum = 11141125;BA.debugLine="displayTitle = getDisplayTitle(rawPath)";
_displaytitle = _getdisplaytitle(_rawpath);
RDebugUtils.currentLine=11141128;
 //BA.debugLineNum = 11141128;BA.debugLine="Dim sourceLabel As String";
_sourcelabel = "";
RDebugUtils.currentLine=11141129;
 //BA.debugLineNum = 11141129;BA.debugLine="If rawPath.StartsWith(\"tracks/\") Then";
if (_rawpath.startsWith("tracks/")) { 
RDebugUtils.currentLine=11141130;
 //BA.debugLineNum = 11141130;BA.debugLine="sourceLabel = \"Built-in / Assets\"";
_sourcelabel = "Built-in / Assets";
 }else {
RDebugUtils.currentLine=11141132;
 //BA.debugLineNum = 11141132;BA.debugLine="sourceLabel = rawPath";
_sourcelabel = _rawpath;
 };
RDebugUtils.currentLine=11141135;
 //BA.debugLineNum = 11141135;BA.debugLine="MsgboxAsync (\"📝 SONG INFO:\" & CRLF & CRLF & _";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("📝 SONG INFO:"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Title: "+_displaytitle+anywheresoftware.b4a.keywords.Common.CRLF+"Track #: "+BA.NumberToString((_position+1))+anywheresoftware.b4a.keywords.Common.CRLF+"Path: "+_sourcelabel),BA.ObjectToCharSequence("Song Details"),processBA);
RDebugUtils.currentLine=11141139;
 //BA.debugLineNum = 11141139;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=11599872;
 //BA.debugLineNum = 11599872;BA.debugLine="Sub nextBtn_Click";
RDebugUtils.currentLine=11599873;
 //BA.debugLineNum = 11599873;BA.debugLine="CallSub(musicService, \"nextSong\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"nextSong");
RDebugUtils.currentLine=11599874;
 //BA.debugLineNum = 11599874;BA.debugLine="End Sub";
return "";
}
public static String  _pausebtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pausebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pausebtn_click", null));}
RDebugUtils.currentLine=11730944;
 //BA.debugLineNum = 11730944;BA.debugLine="Sub pauseBtn_Click";
RDebugUtils.currentLine=11730945;
 //BA.debugLineNum = 11730945;BA.debugLine="CallSub(musicService, \"pauseToggle\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"pauseToggle");
RDebugUtils.currentLine=11730946;
 //BA.debugLineNum = 11730946;BA.debugLine="End Sub";
return "";
}
public static String  _prevbtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "prevbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "prevbtn_click", null));}
RDebugUtils.currentLine=11665408;
 //BA.debugLineNum = 11665408;BA.debugLine="Sub prevBtn_Click";
RDebugUtils.currentLine=11665409;
 //BA.debugLineNum = 11665409;BA.debugLine="CallSub(musicService, \"prevSong\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"prevSong");
RDebugUtils.currentLine=11665410;
 //BA.debugLineNum = 11665410;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar1_valuechanged(int _value,boolean _userchanged) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "seekbar1_valuechanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "seekbar1_valuechanged", new Object[] {_value,_userchanged}));}
RDebugUtils.currentLine=11534336;
 //BA.debugLineNum = 11534336;BA.debugLine="Sub SeekBar1_ValueChanged(Value As Int, UserChange";
RDebugUtils.currentLine=11534337;
 //BA.debugLineNum = 11534337;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
RDebugUtils.currentLine=11534338;
 //BA.debugLineNum = 11534338;BA.debugLine="musicService.mediaPlayer.Position = Value";
mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .setPosition(_value);
 };
RDebugUtils.currentLine=11534340;
 //BA.debugLineNum = 11534340;BA.debugLine="End Sub";
return "";
}
public static String  _uitimer_tick() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "uitimer_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "uitimer_tick", null));}
String _rawpath = "";
String _title = "";
RDebugUtils.currentLine=11468800;
 //BA.debugLineNum = 11468800;BA.debugLine="Sub uiTimer_Tick";
RDebugUtils.currentLine=11468801;
 //BA.debugLineNum = 11468801;BA.debugLine="If musicService.mediaPlayer.IsInitialized Then";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsInitialized()) { 
RDebugUtils.currentLine=11468802;
 //BA.debugLineNum = 11468802;BA.debugLine="Dim rawPath As String";
_rawpath = "";
RDebugUtils.currentLine=11468803;
 //BA.debugLineNum = 11468803;BA.debugLine="rawPath = musicService.musicPlaylist.Get(musicSe";
_rawpath = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(mostCurrent._musicservice._currentsong /*int*/ ));
RDebugUtils.currentLine=11468804;
 //BA.debugLineNum = 11468804;BA.debugLine="Dim title As String";
_title = "";
RDebugUtils.currentLine=11468805;
 //BA.debugLineNum = 11468805;BA.debugLine="title = getDisplayTitle(rawPath)";
_title = _getdisplaytitle(_rawpath);
RDebugUtils.currentLine=11468807;
 //BA.debugLineNum = 11468807;BA.debugLine="SeekBar1.Max = musicService.mediaPlayer.Duration";
mostCurrent._seekbar1.setMax(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getDuration());
RDebugUtils.currentLine=11468808;
 //BA.debugLineNum = 11468808;BA.debugLine="SeekBar1.Value = musicService.mediaPlayer.Positi";
mostCurrent._seekbar1.setValue(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getPosition());
RDebugUtils.currentLine=11468809;
 //BA.debugLineNum = 11468809;BA.debugLine="songRuntime.Text = formatSongDur(musicService.me";
mostCurrent._songruntime.setText(BA.ObjectToCharSequence(_formatsongdur(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getPosition())+" / "+_formatsongdur(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getDuration())));
RDebugUtils.currentLine=11468810;
 //BA.debugLineNum = 11468810;BA.debugLine="songTitle.Text = title";
mostCurrent._songtitle.setText(BA.ObjectToCharSequence(_title));
RDebugUtils.currentLine=11468812;
 //BA.debugLineNum = 11468812;BA.debugLine="If musicService.mediaPlayer.IsPlaying Then";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsPlaying()) { 
RDebugUtils.currentLine=11468813;
 //BA.debugLineNum = 11468813;BA.debugLine="pauseBtn.Text = \"❚❚\"";
mostCurrent._pausebtn.setText(BA.ObjectToCharSequence("❚❚"));
 }else {
RDebugUtils.currentLine=11468815;
 //BA.debugLineNum = 11468815;BA.debugLine="pauseBtn.Text = \"▶\"";
mostCurrent._pausebtn.setText(BA.ObjectToCharSequence("▶"));
RDebugUtils.currentLine=11468816;
 //BA.debugLineNum = 11468816;BA.debugLine="pauseBtn.TextSize = 24";
mostCurrent._pausebtn.setTextSize((float) (24));
 };
 };
RDebugUtils.currentLine=11468819;
 //BA.debugLineNum = 11468819;BA.debugLine="End Sub";
return "";
}
}