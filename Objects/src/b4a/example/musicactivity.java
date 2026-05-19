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
public static anywheresoftware.b4a.phone.Phone.ContentChooser _chooser = null;
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
public b4a.example.day_module _day_module = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=23789568;
 //BA.debugLineNum = 23789568;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=23789569;
 //BA.debugLineNum = 23789569;BA.debugLine="chooser.Initialize(\"chooser\")";
_chooser.Initialize("chooser");
RDebugUtils.currentLine=23789570;
 //BA.debugLineNum = 23789570;BA.debugLine="LoadMusicPlayer";
_loadmusicplayer();
RDebugUtils.currentLine=23789571;
 //BA.debugLineNum = 23789571;BA.debugLine="End Sub";
return "";
}
public static String  _loadmusicplayer() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadmusicplayer", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadmusicplayer", null));}
int _i = 0;
String _rawpath = "";
String _displaytitle = "";
RDebugUtils.currentLine=23855104;
 //BA.debugLineNum = 23855104;BA.debugLine="Sub LoadMusicPlayer";
RDebugUtils.currentLine=23855105;
 //BA.debugLineNum = 23855105;BA.debugLine="Activity.RemoveAllViews";
mostCurrent._activity.RemoveAllViews();
RDebugUtils.currentLine=23855107;
 //BA.debugLineNum = 23855107;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=23855109;
 //BA.debugLineNum = 23855109;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=23855110;
 //BA.debugLineNum = 23855110;BA.debugLine="Activity.LoadLayout(\"musicLayout\")";
mostCurrent._activity.LoadLayout("musicLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=23855112;
 //BA.debugLineNum = 23855112;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark\")";
mostCurrent._activity.LoadLayout("musicLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=23855115;
 //BA.debugLineNum = 23855115;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=23855116;
 //BA.debugLineNum = 23855116;BA.debugLine="Activity.LoadLayout(\"musicLayout2\")";
mostCurrent._activity.LoadLayout("musicLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=23855118;
 //BA.debugLineNum = 23855118;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark2\")";
mostCurrent._activity.LoadLayout("musicLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=23855121;
 //BA.debugLineNum = 23855121;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=23855122;
 //BA.debugLineNum = 23855122;BA.debugLine="Activity.LoadLayout(\"musicLayout3\")";
mostCurrent._activity.LoadLayout("musicLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=23855124;
 //BA.debugLineNum = 23855124;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark3\")";
mostCurrent._activity.LoadLayout("musicLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=23855128;
 //BA.debugLineNum = 23855128;BA.debugLine="If musicService.mediaPlayer.IsInitialized = False";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=23855129;
 //BA.debugLineNum = 23855129;BA.debugLine="StartService(musicService)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._musicservice.getObject()));
 };
RDebugUtils.currentLine=23855134;
 //BA.debugLineNum = 23855134;BA.debugLine="For i = 0 To musicService.musicPlaylist.Size - 1";
{
final int step25 = 1;
final int limit25 = (int) (mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .getSize()-1);
_i = (int) (0) ;
for (;_i <= limit25 ;_i = _i + step25 ) {
RDebugUtils.currentLine=23855135;
 //BA.debugLineNum = 23855135;BA.debugLine="Dim rawPath As String";
_rawpath = "";
RDebugUtils.currentLine=23855136;
 //BA.debugLineNum = 23855136;BA.debugLine="rawPath = musicService.musicPlaylist.Get(i)";
_rawpath = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_i));
RDebugUtils.currentLine=23855137;
 //BA.debugLineNum = 23855137;BA.debugLine="Dim displayTitle As String";
_displaytitle = "";
RDebugUtils.currentLine=23855138;
 //BA.debugLineNum = 23855138;BA.debugLine="displayTitle = getDisplayTitle(rawPath)";
_displaytitle = _getdisplaytitle(_rawpath);
RDebugUtils.currentLine=23855139;
 //BA.debugLineNum = 23855139;BA.debugLine="ListView1.AddSingleLine((i + 1) & \"   \" & displa";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence(BA.NumberToString((_i+1))+"   "+_displaytitle));
RDebugUtils.currentLine=23855140;
 //BA.debugLineNum = 23855140;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode";
if (mostCurrent._starter._themenumber /*int*/ ==2 && mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=23855141;
 //BA.debugLineNum = 23855141;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Co";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=23855143;
 //BA.debugLineNum = 23855143;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Co";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (24),(int) (20),(int) (37)));
 };
 }
};
RDebugUtils.currentLine=23855147;
 //BA.debugLineNum = 23855147;BA.debugLine="uiTimer.Initialize(\"uiTimer\", 500)";
_uitimer.Initialize(processBA,"uiTimer",(long) (500));
RDebugUtils.currentLine=23855148;
 //BA.debugLineNum = 23855148;BA.debugLine="uiTimer.Enabled = True";
_uitimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=23855149;
 //BA.debugLineNum = 23855149;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="musicactivity";
RDebugUtils.currentLine=24313856;
 //BA.debugLineNum = 24313856;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
RDebugUtils.currentLine=24313858;
 //BA.debugLineNum = 24313858;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=24248320;
 //BA.debugLineNum = 24248320;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=24248322;
 //BA.debugLineNum = 24248322;BA.debugLine="End Sub";
return "";
}
public static String  _btnupload_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnupload_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnupload_click", null));}
RDebugUtils.currentLine=23986176;
 //BA.debugLineNum = 23986176;BA.debugLine="Sub btnUpload_Click";
RDebugUtils.currentLine=23986177;
 //BA.debugLineNum = 23986177;BA.debugLine="chooser.Show(\"audio/*\", \"Choose Music File\")";
_chooser.Show(processBA,"audio/*","Choose Music File");
RDebugUtils.currentLine=23986178;
 //BA.debugLineNum = 23986178;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=24051712;
 //BA.debugLineNum = 24051712;BA.debugLine="Sub chooser_Result (Success As Boolean, Dir As Str";
RDebugUtils.currentLine=24051713;
 //BA.debugLineNum = 24051713;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=24051721;
 //BA.debugLineNum = 24051721;BA.debugLine="Dim fullUri As String";
_fulluri = "";
RDebugUtils.currentLine=24051722;
 //BA.debugLineNum = 24051722;BA.debugLine="fullUri = Dir & FileName";
_fulluri = _dir+_filename;
RDebugUtils.currentLine=24051726;
 //BA.debugLineNum = 24051726;BA.debugLine="Dim destName As String";
_destname = "";
RDebugUtils.currentLine=24051727;
 //BA.debugLineNum = 24051727;BA.debugLine="destName = fullUri.SubString(fullUri.LastIndexOf";
_destname = _fulluri.substring((int) (_fulluri.lastIndexOf("/")+1));
RDebugUtils.currentLine=24051728;
 //BA.debugLineNum = 24051728;BA.debugLine="destName = destName.Replace(\"%20\", \" \")";
_destname = _destname.replace("%20"," ");
RDebugUtils.currentLine=24051729;
 //BA.debugLineNum = 24051729;BA.debugLine="destName = destName.Replace(\"%26\", \"&\")";
_destname = _destname.replace("%26","&");
RDebugUtils.currentLine=24051730;
 //BA.debugLineNum = 24051730;BA.debugLine="destName = destName.Replace(\"%27\", \"'\")";
_destname = _destname.replace("%27","'");
RDebugUtils.currentLine=24051731;
 //BA.debugLineNum = 24051731;BA.debugLine="destName = destName.Replace(\"%28\", \"(\")";
_destname = _destname.replace("%28","(");
RDebugUtils.currentLine=24051732;
 //BA.debugLineNum = 24051732;BA.debugLine="destName = destName.Replace(\"%29\", \")\")";
_destname = _destname.replace("%29",")");
RDebugUtils.currentLine=24051733;
 //BA.debugLineNum = 24051733;BA.debugLine="destName = destName.Replace(\"%2C\", \",\")";
_destname = _destname.replace("%2C",",");
RDebugUtils.currentLine=24051736;
 //BA.debugLineNum = 24051736;BA.debugLine="If destName.EndsWith(\".mp3\") = False And destNam";
if (_destname.endsWith(".mp3")==anywheresoftware.b4a.keywords.Common.False && _destname.endsWith(".MP3")==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=24051737;
 //BA.debugLineNum = 24051737;BA.debugLine="destName = destName & \".mp3\"";
_destname = _destname+".mp3";
 };
RDebugUtils.currentLine=24051741;
 //BA.debugLineNum = 24051741;BA.debugLine="Dim destDir As String";
_destdir = "";
RDebugUtils.currentLine=24051742;
 //BA.debugLineNum = 24051742;BA.debugLine="destDir = File.DirInternalCache";
_destdir = anywheresoftware.b4a.keywords.Common.File.getDirInternalCache();
RDebugUtils.currentLine=24051744;
 //BA.debugLineNum = 24051744;BA.debugLine="Try";
try {RDebugUtils.currentLine=24051745;
 //BA.debugLineNum = 24051745;BA.debugLine="File.Copy(Dir, FileName, destDir, destName)";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,_destdir,_destname);
 } 
       catch (Exception e20) {
			processBA.setLastException(e20);RDebugUtils.currentLine=24051747;
 //BA.debugLineNum = 24051747;BA.debugLine="ToastMessageShow(\"❌ Could not copy file: \" & La";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("❌ Could not copy file: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=24051748;
 //BA.debugLineNum = 24051748;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=24051752;
 //BA.debugLineNum = 24051752;BA.debugLine="Dim cachedPath As String";
_cachedpath = "";
RDebugUtils.currentLine=24051753;
 //BA.debugLineNum = 24051753;BA.debugLine="cachedPath = destDir & \"/\" & destName";
_cachedpath = _destdir+"/"+_destname;
RDebugUtils.currentLine=24051754;
 //BA.debugLineNum = 24051754;BA.debugLine="musicService.musicPlaylist.Add(cachedPath)";
mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Add((Object)(_cachedpath));
RDebugUtils.currentLine=24051757;
 //BA.debugLineNum = 24051757;BA.debugLine="Dim displayTitle As String";
_displaytitle = "";
RDebugUtils.currentLine=24051758;
 //BA.debugLineNum = 24051758;BA.debugLine="displayTitle = getDisplayTitle(cachedPath)";
_displaytitle = _getdisplaytitle(_cachedpath);
RDebugUtils.currentLine=24051759;
 //BA.debugLineNum = 24051759;BA.debugLine="Dim idx As Int = musicService.musicPlaylist.Size";
_idx = mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .getSize();
RDebugUtils.currentLine=24051760;
 //BA.debugLineNum = 24051760;BA.debugLine="ListView1.AddSingleLine(idx & \"   \" & displayTit";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence(BA.NumberToString(_idx)+"   "+_displaytitle));
RDebugUtils.currentLine=24051762;
 //BA.debugLineNum = 24051762;BA.debugLine="ToastMessageShow(\"✅ Added: \" & displayTitle, Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("✅ Added: "+_displaytitle),anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=24051764;
 //BA.debugLineNum = 24051764;BA.debugLine="End Sub";
return "";
}
public static String  _getdisplaytitle(String _rawpath) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getdisplaytitle", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getdisplaytitle", new Object[] {_rawpath}));}
String _name = "";
RDebugUtils.currentLine=23920640;
 //BA.debugLineNum = 23920640;BA.debugLine="Sub getDisplayTitle(rawPath As String) As String";
RDebugUtils.currentLine=23920641;
 //BA.debugLineNum = 23920641;BA.debugLine="Dim name As String";
_name = "";
RDebugUtils.currentLine=23920642;
 //BA.debugLineNum = 23920642;BA.debugLine="name = rawPath.SubString(rawPath.LastIndexOf(\"/\")";
_name = _rawpath.substring((int) (_rawpath.lastIndexOf("/")+1));
RDebugUtils.currentLine=23920644;
 //BA.debugLineNum = 23920644;BA.debugLine="If name.EndsWith(\".mp3\") Or name.EndsWith(\".MP3\")";
if (_name.endsWith(".mp3") || _name.endsWith(".MP3")) { 
RDebugUtils.currentLine=23920645;
 //BA.debugLineNum = 23920645;BA.debugLine="name = name.SubString2(0, name.Length - 4)";
_name = _name.substring((int) (0),(int) (_name.length()-4));
 };
RDebugUtils.currentLine=23920648;
 //BA.debugLineNum = 23920648;BA.debugLine="name = name.Replace(\"%20\", \" \")";
_name = _name.replace("%20"," ");
RDebugUtils.currentLine=23920649;
 //BA.debugLineNum = 23920649;BA.debugLine="name = name.Replace(\"%26\", \"&\")";
_name = _name.replace("%26","&");
RDebugUtils.currentLine=23920650;
 //BA.debugLineNum = 23920650;BA.debugLine="name = name.Replace(\"%27\", \"'\")";
_name = _name.replace("%27","'");
RDebugUtils.currentLine=23920651;
 //BA.debugLineNum = 23920651;BA.debugLine="name = name.Replace(\"%28\", \"(\")";
_name = _name.replace("%28","(");
RDebugUtils.currentLine=23920652;
 //BA.debugLineNum = 23920652;BA.debugLine="name = name.Replace(\"%29\", \")\")";
_name = _name.replace("%29",")");
RDebugUtils.currentLine=23920653;
 //BA.debugLineNum = 23920653;BA.debugLine="name = name.Replace(\"%2C\", \",\")";
_name = _name.replace("%2C",",");
RDebugUtils.currentLine=23920654;
 //BA.debugLineNum = 23920654;BA.debugLine="Return name";
if (true) return _name;
RDebugUtils.currentLine=23920655;
 //BA.debugLineNum = 23920655;BA.debugLine="End Sub";
return "";
}
public static String  _formatsongdur(int _ms) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "formatsongdur", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "formatsongdur", new Object[] {_ms}));}
int _seconds = 0;
int _minutes = 0;
RDebugUtils.currentLine=24379392;
 //BA.debugLineNum = 24379392;BA.debugLine="Sub formatSongDur(ms As Int) As String";
RDebugUtils.currentLine=24379393;
 //BA.debugLineNum = 24379393;BA.debugLine="Dim seconds As Int";
_seconds = 0;
RDebugUtils.currentLine=24379394;
 //BA.debugLineNum = 24379394;BA.debugLine="Dim minutes As Int";
_minutes = 0;
RDebugUtils.currentLine=24379395;
 //BA.debugLineNum = 24379395;BA.debugLine="seconds = ms / 1000";
_seconds = (int) (_ms/(double)1000);
RDebugUtils.currentLine=24379396;
 //BA.debugLineNum = 24379396;BA.debugLine="minutes = seconds / 60";
_minutes = (int) (_seconds/(double)60);
RDebugUtils.currentLine=24379397;
 //BA.debugLineNum = 24379397;BA.debugLine="seconds = seconds Mod 60";
_seconds = (int) (_seconds%60);
RDebugUtils.currentLine=24379398;
 //BA.debugLineNum = 24379398;BA.debugLine="Return NumberFormat(minutes, 2, 0) & \":\" & Number";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_minutes,(int) (2),(int) (0))+":"+anywheresoftware.b4a.keywords.Common.NumberFormat(_seconds,(int) (2),(int) (0));
RDebugUtils.currentLine=24379399;
 //BA.debugLineNum = 24379399;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listview1_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listview1_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=24182784;
 //BA.debugLineNum = 24182784;BA.debugLine="Sub ListView1_ItemClick(Position As Int, Value As";
RDebugUtils.currentLine=24182785;
 //BA.debugLineNum = 24182785;BA.debugLine="CallSub2(musicService, \"setSong\", Position)";
anywheresoftware.b4a.keywords.Common.CallSubDebug2(processBA,(Object)(mostCurrent._musicservice.getObject()),"setSong",(Object)(_position));
RDebugUtils.currentLine=24182786;
 //BA.debugLineNum = 24182786;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemlongclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listview1_itemlongclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listview1_itemlongclick", new Object[] {_position,_value}));}
String _rawpath = "";
String _displaytitle = "";
String _sourcelabel = "";
RDebugUtils.currentLine=24117248;
 //BA.debugLineNum = 24117248;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
RDebugUtils.currentLine=24117250;
 //BA.debugLineNum = 24117250;BA.debugLine="Dim rawPath As String";
_rawpath = "";
RDebugUtils.currentLine=24117251;
 //BA.debugLineNum = 24117251;BA.debugLine="rawPath = musicService.musicPlaylist.Get(Position";
_rawpath = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_position));
RDebugUtils.currentLine=24117252;
 //BA.debugLineNum = 24117252;BA.debugLine="Dim displayTitle As String";
_displaytitle = "";
RDebugUtils.currentLine=24117253;
 //BA.debugLineNum = 24117253;BA.debugLine="displayTitle = getDisplayTitle(rawPath)";
_displaytitle = _getdisplaytitle(_rawpath);
RDebugUtils.currentLine=24117256;
 //BA.debugLineNum = 24117256;BA.debugLine="Dim sourceLabel As String";
_sourcelabel = "";
RDebugUtils.currentLine=24117257;
 //BA.debugLineNum = 24117257;BA.debugLine="If rawPath.StartsWith(\"tracks/\") Then";
if (_rawpath.startsWith("tracks/")) { 
RDebugUtils.currentLine=24117258;
 //BA.debugLineNum = 24117258;BA.debugLine="sourceLabel = \"Built-in / Assets\"";
_sourcelabel = "Built-in / Assets";
 }else {
RDebugUtils.currentLine=24117260;
 //BA.debugLineNum = 24117260;BA.debugLine="sourceLabel = rawPath";
_sourcelabel = _rawpath;
 };
RDebugUtils.currentLine=24117263;
 //BA.debugLineNum = 24117263;BA.debugLine="MsgboxAsync (\"📝 SONG INFO:\" & CRLF & CRLF & _";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("📝 SONG INFO:"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Title: "+_displaytitle+anywheresoftware.b4a.keywords.Common.CRLF+"Track #: "+BA.NumberToString((_position+1))+anywheresoftware.b4a.keywords.Common.CRLF+"Path: "+_sourcelabel),BA.ObjectToCharSequence("Song Details"),processBA);
RDebugUtils.currentLine=24117267;
 //BA.debugLineNum = 24117267;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=24576000;
 //BA.debugLineNum = 24576000;BA.debugLine="Sub nextBtn_Click";
RDebugUtils.currentLine=24576001;
 //BA.debugLineNum = 24576001;BA.debugLine="CallSub(musicService, \"nextSong\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"nextSong");
RDebugUtils.currentLine=24576002;
 //BA.debugLineNum = 24576002;BA.debugLine="End Sub";
return "";
}
public static String  _pausebtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pausebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pausebtn_click", null));}
RDebugUtils.currentLine=24707072;
 //BA.debugLineNum = 24707072;BA.debugLine="Sub pauseBtn_Click";
RDebugUtils.currentLine=24707073;
 //BA.debugLineNum = 24707073;BA.debugLine="CallSub(musicService, \"pauseToggle\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"pauseToggle");
RDebugUtils.currentLine=24707074;
 //BA.debugLineNum = 24707074;BA.debugLine="End Sub";
return "";
}
public static String  _prevbtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "prevbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "prevbtn_click", null));}
RDebugUtils.currentLine=24641536;
 //BA.debugLineNum = 24641536;BA.debugLine="Sub prevBtn_Click";
RDebugUtils.currentLine=24641537;
 //BA.debugLineNum = 24641537;BA.debugLine="CallSub(musicService, \"prevSong\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"prevSong");
RDebugUtils.currentLine=24641538;
 //BA.debugLineNum = 24641538;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar1_valuechanged(int _value,boolean _userchanged) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "seekbar1_valuechanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "seekbar1_valuechanged", new Object[] {_value,_userchanged}));}
RDebugUtils.currentLine=24510464;
 //BA.debugLineNum = 24510464;BA.debugLine="Sub SeekBar1_ValueChanged(Value As Int, UserChange";
RDebugUtils.currentLine=24510465;
 //BA.debugLineNum = 24510465;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
RDebugUtils.currentLine=24510466;
 //BA.debugLineNum = 24510466;BA.debugLine="musicService.mediaPlayer.Position = Value";
mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .setPosition(_value);
 };
RDebugUtils.currentLine=24510468;
 //BA.debugLineNum = 24510468;BA.debugLine="End Sub";
return "";
}
public static String  _uitimer_tick() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "uitimer_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "uitimer_tick", null));}
String _rawpath = "";
String _title = "";
RDebugUtils.currentLine=24444928;
 //BA.debugLineNum = 24444928;BA.debugLine="Sub uiTimer_Tick";
RDebugUtils.currentLine=24444929;
 //BA.debugLineNum = 24444929;BA.debugLine="If musicService.mediaPlayer.IsInitialized Then";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsInitialized()) { 
RDebugUtils.currentLine=24444930;
 //BA.debugLineNum = 24444930;BA.debugLine="Dim rawPath As String";
_rawpath = "";
RDebugUtils.currentLine=24444931;
 //BA.debugLineNum = 24444931;BA.debugLine="rawPath = musicService.musicPlaylist.Get(musicSe";
_rawpath = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(mostCurrent._musicservice._currentsong /*int*/ ));
RDebugUtils.currentLine=24444932;
 //BA.debugLineNum = 24444932;BA.debugLine="Dim title As String";
_title = "";
RDebugUtils.currentLine=24444933;
 //BA.debugLineNum = 24444933;BA.debugLine="title = getDisplayTitle(rawPath)";
_title = _getdisplaytitle(_rawpath);
RDebugUtils.currentLine=24444935;
 //BA.debugLineNum = 24444935;BA.debugLine="SeekBar1.Max = musicService.mediaPlayer.Duration";
mostCurrent._seekbar1.setMax(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getDuration());
RDebugUtils.currentLine=24444936;
 //BA.debugLineNum = 24444936;BA.debugLine="SeekBar1.Value = musicService.mediaPlayer.Positi";
mostCurrent._seekbar1.setValue(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getPosition());
RDebugUtils.currentLine=24444937;
 //BA.debugLineNum = 24444937;BA.debugLine="songRuntime.Text = formatSongDur(musicService.me";
mostCurrent._songruntime.setText(BA.ObjectToCharSequence(_formatsongdur(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getPosition())+" / "+_formatsongdur(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getDuration())));
RDebugUtils.currentLine=24444938;
 //BA.debugLineNum = 24444938;BA.debugLine="songTitle.Text = title";
mostCurrent._songtitle.setText(BA.ObjectToCharSequence(_title));
RDebugUtils.currentLine=24444940;
 //BA.debugLineNum = 24444940;BA.debugLine="If musicService.mediaPlayer.IsPlaying Then";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsPlaying()) { 
RDebugUtils.currentLine=24444941;
 //BA.debugLineNum = 24444941;BA.debugLine="pauseBtn.Text = \"❚❚\"";
mostCurrent._pausebtn.setText(BA.ObjectToCharSequence("❚❚"));
 }else {
RDebugUtils.currentLine=24444943;
 //BA.debugLineNum = 24444943;BA.debugLine="pauseBtn.Text = \"▶\"";
mostCurrent._pausebtn.setText(BA.ObjectToCharSequence("▶"));
RDebugUtils.currentLine=24444944;
 //BA.debugLineNum = 24444944;BA.debugLine="pauseBtn.TextSize = 24";
mostCurrent._pausebtn.setTextSize((float) (24));
 };
 };
RDebugUtils.currentLine=24444947;
 //BA.debugLineNum = 24444947;BA.debugLine="End Sub";
return "";
}
}