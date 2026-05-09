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
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _songtitle = null;
public anywheresoftware.b4a.objects.ButtonWrapper _pausebtn = null;
public anywheresoftware.b4a.objects.LabelWrapper _songruntime = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
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
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
int _i = 0;
String _title = "";
RDebugUtils.currentLine=17432576;
 //BA.debugLineNum = 17432576;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=17432577;
 //BA.debugLineNum = 17432577;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=17432579;
 //BA.debugLineNum = 17432579;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17432580;
 //BA.debugLineNum = 17432580;BA.debugLine="Activity.LoadLayout(\"musicLayout\")";
mostCurrent._activity.LoadLayout("musicLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=17432582;
 //BA.debugLineNum = 17432582;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark\")";
mostCurrent._activity.LoadLayout("musicLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=17432585;
 //BA.debugLineNum = 17432585;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17432586;
 //BA.debugLineNum = 17432586;BA.debugLine="Activity.LoadLayout(\"musicLayout2\")";
mostCurrent._activity.LoadLayout("musicLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=17432588;
 //BA.debugLineNum = 17432588;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark2\")";
mostCurrent._activity.LoadLayout("musicLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=17432591;
 //BA.debugLineNum = 17432591;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17432592;
 //BA.debugLineNum = 17432592;BA.debugLine="Activity.LoadLayout(\"musicLayout3\")";
mostCurrent._activity.LoadLayout("musicLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=17432594;
 //BA.debugLineNum = 17432594;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark3\")";
mostCurrent._activity.LoadLayout("musicLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=17432599;
 //BA.debugLineNum = 17432599;BA.debugLine="If musicService.mediaPlayer.IsInitialized = False";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17432600;
 //BA.debugLineNum = 17432600;BA.debugLine="StartService(musicService)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._musicservice.getObject()));
 };
RDebugUtils.currentLine=17432603;
 //BA.debugLineNum = 17432603;BA.debugLine="For i = 0 To musicService.musicPlaylist.Size - 1";
{
final int step24 = 1;
final int limit24 = (int) (mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .getSize()-1);
_i = (int) (0) ;
for (;_i <= limit24 ;_i = _i + step24 ) {
RDebugUtils.currentLine=17432604;
 //BA.debugLineNum = 17432604;BA.debugLine="Dim title As String = musicService.musicPlaylist";
_title = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_i));
RDebugUtils.currentLine=17432605;
 //BA.debugLineNum = 17432605;BA.debugLine="title = title.SubString2(0, title.Length - 4)  '";
_title = _title.substring((int) (0),(int) (_title.length()-4));
RDebugUtils.currentLine=17432606;
 //BA.debugLineNum = 17432606;BA.debugLine="title = title.SubString(7)";
_title = _title.substring((int) (7));
RDebugUtils.currentLine=17432607;
 //BA.debugLineNum = 17432607;BA.debugLine="ListView1.AddSingleLine((i + 1) & \"   \" & title)";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence(BA.NumberToString((_i+1))+"   "+_title));
RDebugUtils.currentLine=17432608;
 //BA.debugLineNum = 17432608;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Col";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }
};
RDebugUtils.currentLine=17432612;
 //BA.debugLineNum = 17432612;BA.debugLine="uiTimer.Initialize(\"uiTimer\", 500)";
_uitimer.Initialize(processBA,"uiTimer",(long) (500));
RDebugUtils.currentLine=17432613;
 //BA.debugLineNum = 17432613;BA.debugLine="uiTimer.Enabled = True";
_uitimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17432614;
 //BA.debugLineNum = 17432614;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="musicactivity";
RDebugUtils.currentLine=17563648;
 //BA.debugLineNum = 17563648;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
RDebugUtils.currentLine=17563650;
 //BA.debugLineNum = 17563650;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=17498112;
 //BA.debugLineNum = 17498112;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=17498114;
 //BA.debugLineNum = 17498114;BA.debugLine="End Sub";
return "";
}
public static String  _formatsongdur(int _ms) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "formatsongdur", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "formatsongdur", new Object[] {_ms}));}
int _seconds = 0;
int _minutes = 0;
RDebugUtils.currentLine=17629184;
 //BA.debugLineNum = 17629184;BA.debugLine="Sub formatSongDur(ms As Int) As String";
RDebugUtils.currentLine=17629185;
 //BA.debugLineNum = 17629185;BA.debugLine="Dim seconds As Int = ms / 1000";
_seconds = (int) (_ms/(double)1000);
RDebugUtils.currentLine=17629186;
 //BA.debugLineNum = 17629186;BA.debugLine="Dim minutes As Int = seconds / 60";
_minutes = (int) (_seconds/(double)60);
RDebugUtils.currentLine=17629187;
 //BA.debugLineNum = 17629187;BA.debugLine="seconds = seconds Mod 60";
_seconds = (int) (_seconds%60);
RDebugUtils.currentLine=17629188;
 //BA.debugLineNum = 17629188;BA.debugLine="Return NumberFormat(minutes, 2, 0) & \":\" & Number";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_minutes,(int) (2),(int) (0))+":"+anywheresoftware.b4a.keywords.Common.NumberFormat(_seconds,(int) (2),(int) (0));
RDebugUtils.currentLine=17629189;
 //BA.debugLineNum = 17629189;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listview1_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listview1_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=18022400;
 //BA.debugLineNum = 18022400;BA.debugLine="Sub ListView1_ItemClick(Position As Int, Value As";
RDebugUtils.currentLine=18022401;
 //BA.debugLineNum = 18022401;BA.debugLine="CallSub2(musicService, \"setSong\", Position)";
anywheresoftware.b4a.keywords.Common.CallSubDebug2(processBA,(Object)(mostCurrent._musicservice.getObject()),"setSong",(Object)(_position));
RDebugUtils.currentLine=18022402;
 //BA.debugLineNum = 18022402;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=17825792;
 //BA.debugLineNum = 17825792;BA.debugLine="Sub nextBtn_Click";
RDebugUtils.currentLine=17825793;
 //BA.debugLineNum = 17825793;BA.debugLine="CallSub(musicService, \"nextSong\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"nextSong");
RDebugUtils.currentLine=17825794;
 //BA.debugLineNum = 17825794;BA.debugLine="End Sub";
return "";
}
public static String  _pausebtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pausebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pausebtn_click", null));}
RDebugUtils.currentLine=17956864;
 //BA.debugLineNum = 17956864;BA.debugLine="Sub pauseBtn_Click";
RDebugUtils.currentLine=17956865;
 //BA.debugLineNum = 17956865;BA.debugLine="CallSub(musicService, \"pauseToggle\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"pauseToggle");
RDebugUtils.currentLine=17956866;
 //BA.debugLineNum = 17956866;BA.debugLine="End Sub";
return "";
}
public static String  _prevbtn_click() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "prevbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "prevbtn_click", null));}
RDebugUtils.currentLine=17891328;
 //BA.debugLineNum = 17891328;BA.debugLine="Sub prevBtn_Click";
RDebugUtils.currentLine=17891329;
 //BA.debugLineNum = 17891329;BA.debugLine="CallSub(musicService, \"prevSong\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,(Object)(mostCurrent._musicservice.getObject()),"prevSong");
RDebugUtils.currentLine=17891330;
 //BA.debugLineNum = 17891330;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar1_valuechanged(int _value,boolean _userchanged) throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "seekbar1_valuechanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "seekbar1_valuechanged", new Object[] {_value,_userchanged}));}
RDebugUtils.currentLine=17760256;
 //BA.debugLineNum = 17760256;BA.debugLine="Sub SeekBar1_ValueChanged(Value As Int, UserChange";
RDebugUtils.currentLine=17760257;
 //BA.debugLineNum = 17760257;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
RDebugUtils.currentLine=17760258;
 //BA.debugLineNum = 17760258;BA.debugLine="musicService.mediaPlayer.Position = Value";
mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .setPosition(_value);
 };
RDebugUtils.currentLine=17760260;
 //BA.debugLineNum = 17760260;BA.debugLine="End Sub";
return "";
}
public static String  _uitimer_tick() throws Exception{
RDebugUtils.currentModule="musicactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "uitimer_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "uitimer_tick", null));}
String _title = "";
RDebugUtils.currentLine=17694720;
 //BA.debugLineNum = 17694720;BA.debugLine="Sub uiTimer_Tick";
RDebugUtils.currentLine=17694721;
 //BA.debugLineNum = 17694721;BA.debugLine="If musicService.mediaPlayer.IsInitialized Then";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsInitialized()) { 
RDebugUtils.currentLine=17694723;
 //BA.debugLineNum = 17694723;BA.debugLine="Dim title As String = musicService.musicPlaylist";
_title = BA.ObjectToString(mostCurrent._musicservice._musicplaylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(mostCurrent._musicservice._currentsong /*int*/ ));
RDebugUtils.currentLine=17694724;
 //BA.debugLineNum = 17694724;BA.debugLine="title = title.SubString2(0, title.Length - 4)  '";
_title = _title.substring((int) (0),(int) (_title.length()-4));
RDebugUtils.currentLine=17694725;
 //BA.debugLineNum = 17694725;BA.debugLine="title = title.SubString(7) 'remove /tracks";
_title = _title.substring((int) (7));
RDebugUtils.currentLine=17694727;
 //BA.debugLineNum = 17694727;BA.debugLine="SeekBar1.Max = musicService.mediaPlayer.Duration";
mostCurrent._seekbar1.setMax(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getDuration());
RDebugUtils.currentLine=17694728;
 //BA.debugLineNum = 17694728;BA.debugLine="SeekBar1.Value = musicService.mediaPlayer.Positi";
mostCurrent._seekbar1.setValue(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getPosition());
RDebugUtils.currentLine=17694729;
 //BA.debugLineNum = 17694729;BA.debugLine="songRuntime.Text = formatSongDur(musicService.me";
mostCurrent._songruntime.setText(BA.ObjectToCharSequence(_formatsongdur(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getPosition())+" / "+_formatsongdur(mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .getDuration())));
RDebugUtils.currentLine=17694730;
 //BA.debugLineNum = 17694730;BA.debugLine="songTitle.Text = title";
mostCurrent._songtitle.setText(BA.ObjectToCharSequence(_title));
RDebugUtils.currentLine=17694732;
 //BA.debugLineNum = 17694732;BA.debugLine="If musicService.mediaPlayer.IsPlaying Then";
if (mostCurrent._musicservice._mediaplayer /*anywheresoftware.b4a.objects.MediaPlayerWrapper*/ .IsPlaying()) { 
RDebugUtils.currentLine=17694733;
 //BA.debugLineNum = 17694733;BA.debugLine="pauseBtn.Text = \"❚❚\"";
mostCurrent._pausebtn.setText(BA.ObjectToCharSequence("❚❚"));
 }else {
RDebugUtils.currentLine=17694735;
 //BA.debugLineNum = 17694735;BA.debugLine="pauseBtn.Text = \"▶\"";
mostCurrent._pausebtn.setText(BA.ObjectToCharSequence("▶"));
 };
 };
RDebugUtils.currentLine=17694738;
 //BA.debugLineNum = 17694738;BA.debugLine="End Sub";
return "";
}
}