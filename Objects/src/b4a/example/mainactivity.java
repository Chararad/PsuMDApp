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

public class mainactivity extends Activity implements B4AActivity{
	public static mainactivity mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.mainactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (mainactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.mainactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.mainactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (mainactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (mainactivity) Resume **");
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
		return mainactivity.class;
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
            BA.LogInfo("** Activity (mainactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (mainactivity) Pause event (activity is not paused). **");
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
            mainactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (mainactivity) Resume **");
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
public static anywheresoftware.b4a.objects.Timer _timerclock = null;
public static b4a.example3.keyvaluestore _kvs = null;
public static b4a.example3.keyvaluestore _kvspref = null;
public static boolean _format24h = false;
public anywheresoftware.b4a.objects.B4XViewWrapper _reglayout = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _darkmodelayout = null;
public static int _size = 0;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _hsv = null;
public b4a.example.b4xgifview _computergif = null;
public b4a.example.b4xgifview _dcomputergif = null;
public b4a.example.b4xgifview _curtain = null;
public b4a.example.b4xgifview _dcurtain = null;
public b4a.example.b4xgifview _notesopen = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _notebook = null;
public b4a.example.b4xgifview _dnotesopen = null;
public anywheresoftware.b4a.objects.ButtonWrapper _clockbtn = null;
public anywheresoftware.b4a.objects.ButtonWrapper _clocklightbtn = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _infopnl = null;
public anywheresoftware.b4a.objects.LabelWrapper _infotitlelbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _infodesclbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _infopagelbl = null;
public static int _infopage = 0;
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
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _timerclock_tick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerclock_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerclock_tick", null));}
RDebugUtils.currentLine=27525120;
 //BA.debugLineNum = 27525120;BA.debugLine="Sub timerClock_Tick";
RDebugUtils.currentLine=27525121;
 //BA.debugLineNum = 27525121;BA.debugLine="clockBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clockbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=27525122;
 //BA.debugLineNum = 27525122;BA.debugLine="clockLightBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clocklightbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=27525123;
 //BA.debugLineNum = 27525123;BA.debugLine="End Sub";
return "";
}
public static void  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.mainactivity parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
b4a.example.mainactivity parent;
boolean _firsttime;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="mainactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=27197441;
 //BA.debugLineNum = 27197441;BA.debugLine="Activity.LoadLayout(\"Layouthsv\")";
parent.mostCurrent._activity.LoadLayout("Layouthsv",mostCurrent.activityBA);
RDebugUtils.currentLine=27197442;
 //BA.debugLineNum = 27197442;BA.debugLine="If FirstTime Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_firsttime) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=27197443;
 //BA.debugLineNum = 27197443;BA.debugLine="kvs = Starter.notesKvs";
parent._kvs = parent.mostCurrent._starter._noteskvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=27197444;
 //BA.debugLineNum = 27197444;BA.debugLine="kvsPref = Starter.prefKvs";
parent._kvspref = parent.mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=27197445;
 //BA.debugLineNum = 27197445;BA.debugLine="timerClock.Initialize(\"timerClock\", 1000)";
parent._timerclock.Initialize(processBA,"timerClock",(long) (1000));
RDebugUtils.currentLine=27197446;
 //BA.debugLineNum = 27197446;BA.debugLine="timerClock.Enabled = True";
parent._timerclock.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 4:
//C
this.state = 5;
;
RDebugUtils.currentLine=27197449;
 //BA.debugLineNum = 27197449;BA.debugLine="hsv.Panel.Width = size";
parent.mostCurrent._hsv.getPanel().setWidth(parent._size);
RDebugUtils.currentLine=27197450;
 //BA.debugLineNum = 27197450;BA.debugLine="hsv.Panel.Height = size";
parent.mostCurrent._hsv.getPanel().setHeight(parent._size);
RDebugUtils.currentLine=27197452;
 //BA.debugLineNum = 27197452;BA.debugLine="regLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._reglayout = parent._xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=27197453;
 //BA.debugLineNum = 27197453;BA.debugLine="darkModeLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._darkmodelayout = parent._xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=27197455;
 //BA.debugLineNum = 27197455;BA.debugLine="hsv.Panel.AddView(regLayout, 0, 0, hsv.Panel.Widt";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._reglayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
RDebugUtils.currentLine=27197456;
 //BA.debugLineNum = 27197456;BA.debugLine="hsv.Panel.AddView(darkModeLayout, 0, 0, hsv.Panel";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._darkmodelayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
RDebugUtils.currentLine=27197458;
 //BA.debugLineNum = 27197458;BA.debugLine="Select Starter.themeNumber";
if (true) break;

case 5:
//select
this.state = 12;
switch (BA.switchObjectToInt(parent.mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
this.state = 7;
if (true) break;
}
case 1: {
this.state = 9;
if (true) break;
}
case 2: {
this.state = 11;
if (true) break;
}
}
if (true) break;

case 7:
//C
this.state = 12;
RDebugUtils.currentLine=27197460;
 //BA.debugLineNum = 27197460;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
parent.mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=27197461;
 //BA.debugLineNum = 27197461;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=27197462;
 //BA.debugLineNum = 27197462;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnComputer";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
RDebugUtils.currentLine=27197463;
 //BA.debugLineNum = 27197463;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtncom";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtncomputer.GIF");
 if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=27197465;
 //BA.debugLineNum = 27197465;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
parent.mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=27197466;
 //BA.debugLineNum = 27197466;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
RDebugUtils.currentLine=27197467;
 //BA.debugLineNum = 27197467;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.G";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
RDebugUtils.currentLine=27197468;
 //BA.debugLineNum = 27197468;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=27197470;
 //BA.debugLineNum = 27197470;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
parent.mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
RDebugUtils.currentLine=27197471;
 //BA.debugLineNum = 27197471;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
RDebugUtils.currentLine=27197472;
 //BA.debugLineNum = 27197472;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\")";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
RDebugUtils.currentLine=27197473;
 //BA.debugLineNum = 27197473;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
RDebugUtils.currentLine=27197474;
 //BA.debugLineNum = 27197474;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
parent.mostCurrent._curtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
RDebugUtils.currentLine=27197475;
 //BA.debugLineNum = 27197475;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\")";
parent.mostCurrent._dcurtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 if (true) break;
;
RDebugUtils.currentLine=27197478;
 //BA.debugLineNum = 27197478;BA.debugLine="If Starter.darkMode Then";

case 12:
//if
this.state = 17;
if (parent.mostCurrent._starter._darkmode /*boolean*/ ) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 17;
RDebugUtils.currentLine=27197479;
 //BA.debugLineNum = 27197479;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=27197480;
 //BA.debugLineNum = 27197480;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
RDebugUtils.currentLine=27197481;
 //BA.debugLineNum = 27197481;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=27197483;
 //BA.debugLineNum = 27197483;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27197484;
 //BA.debugLineNum = 27197484;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
 if (true) break;

case 17:
//C
this.state = -1;
;
RDebugUtils.currentLine=27197487;
 //BA.debugLineNum = 27197487;BA.debugLine="Sleep(50)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "activity_create"),(int) (50));
this.state = 18;
return;
case 18:
//C
this.state = -1;
;
RDebugUtils.currentLine=27197488;
 //BA.debugLineNum = 27197488;BA.debugLine="hsv.ScrollPosition = Max(0, (hsv.Panel.Width - 10";
parent.mostCurrent._hsv.setScrollPosition((int) (anywheresoftware.b4a.keywords.Common.Max(0,(parent.mostCurrent._hsv.getPanel().getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA))/(double)2)));
RDebugUtils.currentLine=27197490;
 //BA.debugLineNum = 27197490;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="mainactivity";
RDebugUtils.currentLine=27328512;
 //BA.debugLineNum = 27328512;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=27328514;
 //BA.debugLineNum = 27328514;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=27262976;
 //BA.debugLineNum = 27262976;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=27262978;
 //BA.debugLineNum = 27262978;BA.debugLine="If format24h Then";
if (_format24h) { 
RDebugUtils.currentLine=27262979;
 //BA.debugLineNum = 27262979;BA.debugLine="DateTime.TimeFormat = \"HH:mm\" ' 24-Hour Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 }else {
RDebugUtils.currentLine=27262981;
 //BA.debugLineNum = 27262981;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\" ' AM/PM Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 };
RDebugUtils.currentLine=27262984;
 //BA.debugLineNum = 27262984;BA.debugLine="If Starter.themeChanged Then";
if (mostCurrent._starter._themechanged /*boolean*/ ) { 
RDebugUtils.currentLine=27262985;
 //BA.debugLineNum = 27262985;BA.debugLine="regLayout.RemoveAllViews";
mostCurrent._reglayout.RemoveAllViews();
RDebugUtils.currentLine=27262986;
 //BA.debugLineNum = 27262986;BA.debugLine="darkModeLayout.RemoveAllViews";
mostCurrent._darkmodelayout.RemoveAllViews();
RDebugUtils.currentLine=27262988;
 //BA.debugLineNum = 27262988;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=27262990;
 //BA.debugLineNum = 27262990;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=27262991;
 //BA.debugLineNum = 27262991;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=27262992;
 //BA.debugLineNum = 27262992;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnCompute";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
RDebugUtils.currentLine=27262993;
 //BA.debugLineNum = 27262993;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtnCo";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtnComputer.GIF");
 break; }
case 1: {
RDebugUtils.currentLine=27262995;
 //BA.debugLineNum = 27262995;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=27262996;
 //BA.debugLineNum = 27262996;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
RDebugUtils.currentLine=27262997;
 //BA.debugLineNum = 27262997;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
RDebugUtils.currentLine=27262998;
 //BA.debugLineNum = 27262998;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GI";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 break; }
case 2: {
RDebugUtils.currentLine=27263000;
 //BA.debugLineNum = 27263000;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
RDebugUtils.currentLine=27263001;
 //BA.debugLineNum = 27263001;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
RDebugUtils.currentLine=27263002;
 //BA.debugLineNum = 27263002;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\"";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
RDebugUtils.currentLine=27263003;
 //BA.debugLineNum = 27263003;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GI";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
RDebugUtils.currentLine=27263004;
 //BA.debugLineNum = 27263004;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
mostCurrent._curtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
RDebugUtils.currentLine=27263005;
 //BA.debugLineNum = 27263005;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\"";
mostCurrent._dcurtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 break; }
}
;
RDebugUtils.currentLine=27263007;
 //BA.debugLineNum = 27263007;BA.debugLine="Starter.themeChanged = False";
mostCurrent._starter._themechanged /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=27263009;
 //BA.debugLineNum = 27263009;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bookie_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bookie_click", null));}
RDebugUtils.currentLine=28246016;
 //BA.debugLineNum = 28246016;BA.debugLine="Private Sub bookie_Click";
RDebugUtils.currentLine=28246017;
 //BA.debugLineNum = 28246017;BA.debugLine="StartActivity(FlashcardActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._flashcardactivity.getObject()));
RDebugUtils.currentLine=28246018;
 //BA.debugLineNum = 28246018;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bookie_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bookie_longclick", null));}
RDebugUtils.currentLine=29032448;
 //BA.debugLineNum = 29032448;BA.debugLine="Private Sub bookie_LongClick";
RDebugUtils.currentLine=29032449;
 //BA.debugLineNum = 29032449;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29032450;
 //BA.debugLineNum = 29032450;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29032451;
 //BA.debugLineNum = 29032451;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29032452;
 //BA.debugLineNum = 29032452;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29032453;
 //BA.debugLineNum = 29032453;BA.debugLine="showInfoPage(3)";
_showinfopage((int) (3));
RDebugUtils.currentLine=29032454;
 //BA.debugLineNum = 29032454;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29032456;
 //BA.debugLineNum = 29032456;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopopup() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showinfopopup", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showinfopopup", null));}
anywheresoftware.b4a.objects.ButtonWrapper _closebtn = null;
RDebugUtils.currentLine=27590656;
 //BA.debugLineNum = 27590656;BA.debugLine="Private Sub showInfoPopup";
RDebugUtils.currentLine=27590658;
 //BA.debugLineNum = 27590658;BA.debugLine="infoPnl = xui.CreatePanel(\"infoPnl\")";
mostCurrent._infopnl = _xui.CreatePanel(processBA,"infoPnl");
RDebugUtils.currentLine=27590659;
 //BA.debugLineNum = 27590659;BA.debugLine="Activity.AddView(infoPnl, 75dip, 205dip, 300dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._infopnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (340)));
RDebugUtils.currentLine=27590660;
 //BA.debugLineNum = 27590660;BA.debugLine="infoPnl.SetColorAndBorder(xui.Color_White, 2dip,";
mostCurrent._infopnl.SetColorAndBorder(_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=27590662;
 //BA.debugLineNum = 27590662;BA.debugLine="Dim closeBtn As Button";
_closebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=27590663;
 //BA.debugLineNum = 27590663;BA.debugLine="closeBtn.Initialize(\"infoPnlClose\")";
_closebtn.Initialize(mostCurrent.activityBA,"infoPnlClose");
RDebugUtils.currentLine=27590664;
 //BA.debugLineNum = 27590664;BA.debugLine="closeBtn.Text = \"x\"";
_closebtn.setText(BA.ObjectToCharSequence("x"));
RDebugUtils.currentLine=27590665;
 //BA.debugLineNum = 27590665;BA.debugLine="closeBtn.TextSize = 7";
_closebtn.setTextSize((float) (7));
RDebugUtils.currentLine=27590666;
 //BA.debugLineNum = 27590666;BA.debugLine="infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28";
mostCurrent._infopnl.AddView((android.view.View)(_closebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (265)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)));
RDebugUtils.currentLine=27590668;
 //BA.debugLineNum = 27590668;BA.debugLine="infoTitleLbl.Initialize(\"\")";
mostCurrent._infotitlelbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=27590669;
 //BA.debugLineNum = 27590669;BA.debugLine="infoTitleLbl.TextSize = 16";
mostCurrent._infotitlelbl.setTextSize((float) (16));
RDebugUtils.currentLine=27590670;
 //BA.debugLineNum = 27590670;BA.debugLine="infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infotitlelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=27590671;
 //BA.debugLineNum = 27590671;BA.debugLine="infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infotitlelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (248)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=27590673;
 //BA.debugLineNum = 27590673;BA.debugLine="infoDescLbl.Initialize(\"\")";
mostCurrent._infodesclbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=27590674;
 //BA.debugLineNum = 27590674;BA.debugLine="infoDescLbl.TextSize = 10";
mostCurrent._infodesclbl.setTextSize((float) (10));
RDebugUtils.currentLine=27590675;
 //BA.debugLineNum = 27590675;BA.debugLine="infoDescLbl.Gravity = Gravity.TOP";
mostCurrent._infodesclbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
RDebugUtils.currentLine=27590676;
 //BA.debugLineNum = 27590676;BA.debugLine="infoDescLbl.SingleLine = False";
mostCurrent._infodesclbl.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27590677;
 //BA.debugLineNum = 27590677;BA.debugLine="infoPnl.AddView(infoDescLbl, 12dip, 43dip, 276dip";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infodesclbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (43)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (276)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (280)));
RDebugUtils.currentLine=27590679;
 //BA.debugLineNum = 27590679;BA.debugLine="infoPageLbl.Initialize(\"\")";
mostCurrent._infopagelbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=27590680;
 //BA.debugLineNum = 27590680;BA.debugLine="infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infopagelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=27590681;
 //BA.debugLineNum = 27590681;BA.debugLine="infoPageLbl.TextSize = 10";
mostCurrent._infopagelbl.setTextSize((float) (10));
RDebugUtils.currentLine=27590682;
 //BA.debugLineNum = 27590682;BA.debugLine="infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infopagelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (184)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
RDebugUtils.currentLine=27590685;
 //BA.debugLineNum = 27590685;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
RDebugUtils.currentLine=27590686;
 //BA.debugLineNum = 27590686;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopage(int _page) throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showinfopage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showinfopage", new Object[] {_page}));}
RDebugUtils.currentLine=27656192;
 //BA.debugLineNum = 27656192;BA.debugLine="Private Sub showInfoPage(page As Int)";
RDebugUtils.currentLine=27656193;
 //BA.debugLineNum = 27656193;BA.debugLine="infoPage = page";
_infopage = _page;
RDebugUtils.currentLine=27656194;
 //BA.debugLineNum = 27656194;BA.debugLine="Select page";
switch (_page) {
case 0: {
RDebugUtils.currentLine=27656196;
 //BA.debugLineNum = 27656196;BA.debugLine="infoDescLbl.TextSize = 12";
mostCurrent._infodesclbl.setTextSize((float) (12));
RDebugUtils.currentLine=27656197;
 //BA.debugLineNum = 27656197;BA.debugLine="infoTitleLbl.Text = \"Calendar\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Calendar"));
RDebugUtils.currentLine=27656198;
 //BA.debugLineNum = 27656198;BA.debugLine="infoDescLbl.Text = \"1. Tap the Calendar on the";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the Calendar on the home screen to open the Scheduling System."+anywheresoftware.b4a.keywords.Common.CRLF+"2. You'll land on the Month View by default — a full overview of your calendar."+anywheresoftware.b4a.keywords.Common.CRLF+"3. Use the Menu Button (upper left) to switch between Month, Day, and Schedule views."+anywheresoftware.b4a.keywords.Common.CRLF+"4. Tap any date to open a detailed hour-by-hour timeline for that day."+anywheresoftware.b4a.keywords.Common.CRLF+"5. Select any time slot to begin creating a schedule entry."+anywheresoftware.b4a.keywords.Common.CRLF+"6. Tap the + Icon and choose from: Add Event, Add Task, Birthday, or Out of Office."+anywheresoftware.b4a.keywords.Common.CRLF+"7. Press Save to confirm, or Delete to remove an existing entry."+anywheresoftware.b4a.keywords.Common.CRLF+"8. Use the Arrow Down Button to navigate between months and years."+anywheresoftware.b4a.keywords.Common.CRLF+"9. To share your schedule with a group, tap the group tab and tap the join/create button and enter or create your group."));
 break; }
case 1: {
RDebugUtils.currentLine=27656208;
 //BA.debugLineNum = 27656208;BA.debugLine="infoDescLbl.TextSize = 14";
mostCurrent._infodesclbl.setTextSize((float) (14));
RDebugUtils.currentLine=27656209;
 //BA.debugLineNum = 27656209;BA.debugLine="infoTitleLbl.Text = \"Clock\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Clock"));
RDebugUtils.currentLine=27656210;
 //BA.debugLineNum = 27656210;BA.debugLine="infoDescLbl.Text = \"1. Tap the Clock on the hom";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the Clock on the home screen To open the Pomodoro timer."+anywheresoftware.b4a.keywords.Common.CRLF+"2. Press Start To begin a 25-minute focus session."+anywheresoftware.b4a.keywords.Common.CRLF+"3. When the session ends, the timer automatically shifts to a short break (3 mins) Or long break (10 mins)."+anywheresoftware.b4a.keywords.Common.CRLF+"4. Use the Next button To manually switch between Pomodoro, short break, And long break modes."+anywheresoftware.b4a.keywords.Common.CRLF+"5. Press Pause anytime To pause the timer."+anywheresoftware.b4a.keywords.Common.CRLF+"6. Tap Settings To customize the length of each session To your preference."+anywheresoftware.b4a.keywords.Common.CRLF+"7. You can also switch the clock display format from within Settings."));
 break; }
case 2: {
RDebugUtils.currentLine=27656218;
 //BA.debugLineNum = 27656218;BA.debugLine="infoDescLbl.TextSize = 16";
mostCurrent._infodesclbl.setTextSize((float) (16));
RDebugUtils.currentLine=27656219;
 //BA.debugLineNum = 27656219;BA.debugLine="infoTitleLbl.Text = \"Corkboard\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Corkboard"));
RDebugUtils.currentLine=27656220;
 //BA.debugLineNum = 27656220;BA.debugLine="infoDescLbl.Text = \"1. Tap the Corkboard on the";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the Corkboard on the home screen to open your digital canvas."+anywheresoftware.b4a.keywords.Common.CRLF+"2. Tap the Note Button to add a sticky note — type your reminder or idea and place it anywhere on the board."+anywheresoftware.b4a.keywords.Common.CRLF+"3. Tap the Image Button to pin a photo or image onto the board."+anywheresoftware.b4a.keywords.Common.CRLF+"4. Tap the Canvas Tool to draw, sketch, or map out ideas freehand."+anywheresoftware.b4a.keywords.Common.CRLF+"5. Press and drag any element to reposition it wherever you like."+anywheresoftware.b4a.keywords.Common.CRLF+"6. Arrange your board however feels right — there's no wrong way to use it."));
 break; }
case 3: {
RDebugUtils.currentLine=27656227;
 //BA.debugLineNum = 27656227;BA.debugLine="infoDescLbl.TextSize = 10";
mostCurrent._infodesclbl.setTextSize((float) (10));
RDebugUtils.currentLine=27656228;
 //BA.debugLineNum = 27656228;BA.debugLine="infoTitleLbl.Text = \"Flashcards\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Flashcards"));
RDebugUtils.currentLine=27656229;
 //BA.debugLineNum = 27656229;BA.debugLine="infoDescLbl.Text = \"1. Tap the Books on the hom";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the Books on the home screen to open the Flashcards feature."+anywheresoftware.b4a.keywords.Common.CRLF+"2. Tap the + Icon to create a new deck — enter a name and save."+anywheresoftware.b4a.keywords.Common.CRLF+"3. Long-press a deck to access options: Add Card, Browse Cards, Rename, Create Subdeck, or Delete."+anywheresoftware.b4a.keywords.Common.CRLF+"4. To add a card, open a deck and tap the + Icon — enter the question on one side and the answer on the other."+anywheresoftware.b4a.keywords.Common.CRLF+"5. To review, open a deck and tap a card — press Show Answer to flip it."+anywheresoftware.b4a.keywords.Common.CRLF+"6. Use the Next button to move to the next card, or Refresh to restart the set."+anywheresoftware.b4a.keywords.Common.CRLF+"7. Use Active Recall mode To challenge yourself To remember the answer before flipping."+anywheresoftware.b4a.keywords.Common.CRLF+"8. To edit r remove a card, long-press it And select your action."+anywheresoftware.b4a.keywords.Common.CRLF+"9. To generate flashcards from a File, tap the Files To Flashcard button on any subdeck, upload your document, and Athena will build a full deck For you automatically."+anywheresoftware.b4a.keywords.Common.CRLF+"10. To generate flashcards from a topic, tap the AI cards And subdecks button on any deck, and Athena will build the rest for you."));
 break; }
case 4: {
RDebugUtils.currentLine=27656240;
 //BA.debugLineNum = 27656240;BA.debugLine="infoDescLbl.TextSize = 16";
mostCurrent._infodesclbl.setTextSize((float) (16));
RDebugUtils.currentLine=27656241;
 //BA.debugLineNum = 27656241;BA.debugLine="infoTitleLbl.Text = \"Music Player\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Music Player"));
RDebugUtils.currentLine=27656242;
 //BA.debugLineNum = 27656242;BA.debugLine="infoDescLbl.Text = \"1. Tap the Music Player on";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the Music Player on the home screen to open it."+anywheresoftware.b4a.keywords.Common.CRLF+"2. Press Play to start the lo-fi music."+anywheresoftware.b4a.keywords.Common.CRLF+"3. Use the Select option to choose a specific track from the library."+anywheresoftware.b4a.keywords.Common.CRLF+"4. Press Next to skip to the next track."+anywheresoftware.b4a.keywords.Common.CRLF+"5. Press Pause anytime to stop the music."+anywheresoftware.b4a.keywords.Common.CRLF+"6. Scroll through the expanded music library for more track options."+anywheresoftware.b4a.keywords.Common.CRLF+"7. To add your own music, tap the Upload Music button and select a song from your device."));
 break; }
case 5: {
RDebugUtils.currentLine=27656250;
 //BA.debugLineNum = 27656250;BA.debugLine="infoDescLbl.TextSize = 14";
mostCurrent._infodesclbl.setTextSize((float) (14));
RDebugUtils.currentLine=27656251;
 //BA.debugLineNum = 27656251;BA.debugLine="infoTitleLbl.Text = \"Notepad\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Notepad"));
RDebugUtils.currentLine=27656252;
 //BA.debugLineNum = 27656252;BA.debugLine="infoDescLbl.Text = \"1. Tap the Notepad on the h";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the Notepad on the home screen to open the Notes feature."+anywheresoftware.b4a.keywords.Common.CRLF+"2. Press the + Button to create a new note."+anywheresoftware.b4a.keywords.Common.CRLF+"3. Enter a title, add relevant tags, then type your content in the body field."+anywheresoftware.b4a.keywords.Common.CRLF+"4. Press Save when you're done."+anywheresoftware.b4a.keywords.Common.CRLF+"5. To find a note, use the search bar — type the title or a tag and your note appears instantly."+anywheresoftware.b4a.keywords.Common.CRLF+"6. To delete a note, long-press it and tap Yes to confirm."+anywheresoftware.b4a.keywords.Common.CRLF+"7. To generate summarized notes from a file, tap Insert PDF button, upload your file, and let Athena do the rest."));
 break; }
case 6: {
RDebugUtils.currentLine=27656260;
 //BA.debugLineNum = 27656260;BA.debugLine="infoDescLbl.TextSize = 14";
mostCurrent._infodesclbl.setTextSize((float) (14));
RDebugUtils.currentLine=27656261;
 //BA.debugLineNum = 27656261;BA.debugLine="infoTitleLbl.Text = \"To-do List\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("To-do List"));
RDebugUtils.currentLine=27656262;
 //BA.debugLineNum = 27656262;BA.debugLine="infoDescLbl.Text = \"1. Tap the PC Screen on the";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("1. Tap the PC Screen on the home screen to open the To-Do List."+anywheresoftware.b4a.keywords.Common.CRLF+"2. Press + New List to create a new list."+anywheresoftware.b4a.keywords.Common.CRLF+"3. Open the list and tap the input field to add a task."+anywheresoftware.b4a.keywords.Common.CRLF+"4. Type your task and press Enter to save it."+anywheresoftware.b4a.keywords.Common.CRLF+"5. Tap the checkbox beside a task to mark it as complete — watch your progress percentage go up."+anywheresoftware.b4a.keywords.Common.CRLF+"6. To delete a task or list, long-press it and confirm deletion."+anywheresoftware.b4a.keywords.Common.CRLF+"7. To share your to-do list with a group, tap the group tab and tap the join/create button and enter or create your group."));
 break; }
case 7: {
RDebugUtils.currentLine=27656270;
 //BA.debugLineNum = 27656270;BA.debugLine="infoDescLbl.TextSize = 22";
mostCurrent._infodesclbl.setTextSize((float) (22));
RDebugUtils.currentLine=27656271;
 //BA.debugLineNum = 27656271;BA.debugLine="infoTitleLbl.Text = \"Themes\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Themes"));
RDebugUtils.currentLine=27656272;
 //BA.debugLineNum = 27656272;BA.debugLine="infoDescLbl.Text = \"Themes let you put your own";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
 break; }
case 8: {
RDebugUtils.currentLine=27656274;
 //BA.debugLineNum = 27656274;BA.debugLine="infoDescLbl.TextSize = 22";
mostCurrent._infodesclbl.setTextSize((float) (22));
RDebugUtils.currentLine=27656275;
 //BA.debugLineNum = 27656275;BA.debugLine="infoTitleLbl.Text = \"Lamp\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Lamp"));
RDebugUtils.currentLine=27656276;
 //BA.debugLineNum = 27656276;BA.debugLine="infoDescLbl.Text = \"The lamp gives you control";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
 break; }
case 9: {
RDebugUtils.currentLine=27656278;
 //BA.debugLineNum = 27656278;BA.debugLine="infoDescLbl.TextSize = 22";
mostCurrent._infodesclbl.setTextSize((float) (22));
RDebugUtils.currentLine=27656279;
 //BA.debugLineNum = 27656279;BA.debugLine="infoTitleLbl.Text = \"Navigation\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Navigation"));
RDebugUtils.currentLine=27656280;
 //BA.debugLineNum = 27656280;BA.debugLine="infoDescLbl.Text = \"Navigation is your home bas";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
 break; }
}
;
RDebugUtils.currentLine=27656284;
 //BA.debugLineNum = 27656284;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calendar_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "calendar_click", null));}
RDebugUtils.currentLine=28311552;
 //BA.debugLineNum = 28311552;BA.debugLine="Private Sub calendar_Click";
RDebugUtils.currentLine=28311553;
 //BA.debugLineNum = 28311553;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
RDebugUtils.currentLine=28311554;
 //BA.debugLineNum = 28311554;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calendar_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "calendar_longclick", null));}
RDebugUtils.currentLine=28770304;
 //BA.debugLineNum = 28770304;BA.debugLine="Private Sub calendar_LongClick";
RDebugUtils.currentLine=28770305;
 //BA.debugLineNum = 28770305;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=28770306;
 //BA.debugLineNum = 28770306;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=28770307;
 //BA.debugLineNum = 28770307;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28770308;
 //BA.debugLineNum = 28770308;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=28770309;
 //BA.debugLineNum = 28770309;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
RDebugUtils.currentLine=28770310;
 //BA.debugLineNum = 28770310;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=28770312;
 //BA.debugLineNum = 28770312;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clockbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clockbtn_click", null));}
RDebugUtils.currentLine=27721728;
 //BA.debugLineNum = 27721728;BA.debugLine="Private Sub clockBtn_Click";
RDebugUtils.currentLine=27721729;
 //BA.debugLineNum = 27721729;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
RDebugUtils.currentLine=27721730;
 //BA.debugLineNum = 27721730;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clockbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clockbtn_longclick", null));}
RDebugUtils.currentLine=28835840;
 //BA.debugLineNum = 28835840;BA.debugLine="Private Sub clockBtn_LongClick";
RDebugUtils.currentLine=28835841;
 //BA.debugLineNum = 28835841;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=28835842;
 //BA.debugLineNum = 28835842;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=28835843;
 //BA.debugLineNum = 28835843;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28835844;
 //BA.debugLineNum = 28835844;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=28835845;
 //BA.debugLineNum = 28835845;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
RDebugUtils.currentLine=28835846;
 //BA.debugLineNum = 28835846;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=28835848;
 //BA.debugLineNum = 28835848;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clocklightbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clocklightbtn_click", null));}
RDebugUtils.currentLine=27787264;
 //BA.debugLineNum = 27787264;BA.debugLine="Private Sub  clockLightBtn_Click";
RDebugUtils.currentLine=27787265;
 //BA.debugLineNum = 27787265;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
RDebugUtils.currentLine=27787266;
 //BA.debugLineNum = 27787266;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clocklightbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clocklightbtn_longclick", null));}
RDebugUtils.currentLine=28901376;
 //BA.debugLineNum = 28901376;BA.debugLine="Private Sub clockLightBtn_LongClick";
RDebugUtils.currentLine=28901377;
 //BA.debugLineNum = 28901377;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=28901378;
 //BA.debugLineNum = 28901378;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=28901379;
 //BA.debugLineNum = 28901379;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28901380;
 //BA.debugLineNum = 28901380;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=28901381;
 //BA.debugLineNum = 28901381;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
RDebugUtils.currentLine=28901382;
 //BA.debugLineNum = 28901382;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=28901384;
 //BA.debugLineNum = 28901384;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "corkie_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "corkie_click", null));}
RDebugUtils.currentLine=28639232;
 //BA.debugLineNum = 28639232;BA.debugLine="Private Sub corkie_Click";
RDebugUtils.currentLine=28639233;
 //BA.debugLineNum = 28639233;BA.debugLine="StartActivity(corkActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._corkactivity.getObject()));
RDebugUtils.currentLine=28639234;
 //BA.debugLineNum = 28639234;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "corkie_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "corkie_longclick", null));}
RDebugUtils.currentLine=28966912;
 //BA.debugLineNum = 28966912;BA.debugLine="Private Sub corkie_LongClick";
RDebugUtils.currentLine=28966913;
 //BA.debugLineNum = 28966913;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=28966914;
 //BA.debugLineNum = 28966914;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=28966915;
 //BA.debugLineNum = 28966915;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28966916;
 //BA.debugLineNum = 28966916;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=28966917;
 //BA.debugLineNum = 28966917;BA.debugLine="showInfoPage(2)";
_showinfopage((int) (2));
RDebugUtils.currentLine=28966918;
 //BA.debugLineNum = 28966918;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=28966920;
 //BA.debugLineNum = 28966920;BA.debugLine="End Sub";
return "";
}
public static void  _dlamp_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dlamp_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "dlamp_click", null); return;}
ResumableSub_dlamp_Click rsub = new ResumableSub_dlamp_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_dlamp_Click extends BA.ResumableSub {
public ResumableSub_dlamp_Click(b4a.example.mainactivity parent) {
this.parent = parent;
}
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="mainactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
RDebugUtils.currentLine=27459585;
 //BA.debugLineNum = 27459585;BA.debugLine="Starter.darkMode = False";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=27459586;
 //BA.debugLineNum = 27459586;BA.debugLine="kvsPref.Put(\"darkMode\", False)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.False));
RDebugUtils.currentLine=27459587;
 //BA.debugLineNum = 27459587;BA.debugLine="regLayout.Visible = True";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=27459588;
 //BA.debugLineNum = 27459588;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
RDebugUtils.currentLine=27459589;
 //BA.debugLineNum = 27459589;BA.debugLine="regLayout.Alpha = 0";
parent.mostCurrent._reglayout.setAlpha((float) (0));
RDebugUtils.currentLine=27459590;
 //BA.debugLineNum = 27459590;BA.debugLine="regLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (1));
RDebugUtils.currentLine=27459591;
 //BA.debugLineNum = 27459591;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (0));
RDebugUtils.currentLine=27459592;
 //BA.debugLineNum = 27459592;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "dlamp_click"),(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=27459593;
 //BA.debugLineNum = 27459593;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27459594;
 //BA.debugLineNum = 27459594;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _dlamp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dlamp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dlamp_longclick", null));}
RDebugUtils.currentLine=29425664;
 //BA.debugLineNum = 29425664;BA.debugLine="Private Sub dlamp_LongClick";
RDebugUtils.currentLine=29425665;
 //BA.debugLineNum = 29425665;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29425666;
 //BA.debugLineNum = 29425666;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29425667;
 //BA.debugLineNum = 29425667;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29425668;
 //BA.debugLineNum = 29425668;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29425669;
 //BA.debugLineNum = 29425669;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
RDebugUtils.currentLine=29425670;
 //BA.debugLineNum = 29425670;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29425672;
 //BA.debugLineNum = 29425672;BA.debugLine="End Sub";
return "";
}
public static String  _helpbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "helpbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "helpbtn_click", null));}
RDebugUtils.currentLine=27918336;
 //BA.debugLineNum = 27918336;BA.debugLine="Private Sub helpBtn_Click";
RDebugUtils.currentLine=27918337;
 //BA.debugLineNum = 27918337;BA.debugLine="StartActivity(helpActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._helpactivity.getObject()));
RDebugUtils.currentLine=27918338;
 //BA.debugLineNum = 27918338;BA.debugLine="End Sub";
return "";
}
public static String  _infopnlclose_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "infopnlclose_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "infopnlclose_click", null));}
RDebugUtils.currentLine=28049408;
 //BA.debugLineNum = 28049408;BA.debugLine="Private Sub infoPnlClose_Click";
RDebugUtils.currentLine=28049409;
 //BA.debugLineNum = 28049409;BA.debugLine="infoPnl.Visible = False";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28049410;
 //BA.debugLineNum = 28049410;BA.debugLine="End Sub";
return "";
}
public static void  _lamp_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lamp_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "lamp_click", null); return;}
ResumableSub_lamp_Click rsub = new ResumableSub_lamp_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lamp_Click extends BA.ResumableSub {
public ResumableSub_lamp_Click(b4a.example.mainactivity parent) {
this.parent = parent;
}
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="mainactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
RDebugUtils.currentLine=27394049;
 //BA.debugLineNum = 27394049;BA.debugLine="Starter.darkMode = True";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=27394050;
 //BA.debugLineNum = 27394050;BA.debugLine="kvsPref.Put(\"darkMode\", True)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.True));
RDebugUtils.currentLine=27394051;
 //BA.debugLineNum = 27394051;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=27394052;
 //BA.debugLineNum = 27394052;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
RDebugUtils.currentLine=27394053;
 //BA.debugLineNum = 27394053;BA.debugLine="darkModeLayout.Alpha = 0";
parent.mostCurrent._darkmodelayout.setAlpha((float) (0));
RDebugUtils.currentLine=27394054;
 //BA.debugLineNum = 27394054;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (1));
RDebugUtils.currentLine=27394055;
 //BA.debugLineNum = 27394055;BA.debugLine="regLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (0));
RDebugUtils.currentLine=27394056;
 //BA.debugLineNum = 27394056;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "lamp_click"),(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=27394057;
 //BA.debugLineNum = 27394057;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27394058;
 //BA.debugLineNum = 27394058;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lamp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lamp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lamp_longclick", null));}
RDebugUtils.currentLine=29360128;
 //BA.debugLineNum = 29360128;BA.debugLine="Private Sub lamp_LongClick";
RDebugUtils.currentLine=29360129;
 //BA.debugLineNum = 29360129;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29360130;
 //BA.debugLineNum = 29360130;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29360131;
 //BA.debugLineNum = 29360131;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29360132;
 //BA.debugLineNum = 29360132;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29360133;
 //BA.debugLineNum = 29360133;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
RDebugUtils.currentLine=29360134;
 //BA.debugLineNum = 29360134;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29360136;
 //BA.debugLineNum = 29360136;BA.debugLine="End Sub";
return "";
}
public static String  _mp_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mp_click", null));}
RDebugUtils.currentLine=28180480;
 //BA.debugLineNum = 28180480;BA.debugLine="Private Sub mP_Click";
RDebugUtils.currentLine=28180481;
 //BA.debugLineNum = 28180481;BA.debugLine="StartActivity(musicActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._musicactivity.getObject()));
RDebugUtils.currentLine=28180482;
 //BA.debugLineNum = 28180482;BA.debugLine="End Sub";
return "";
}
public static String  _mp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mp_longclick", null));}
RDebugUtils.currentLine=29097984;
 //BA.debugLineNum = 29097984;BA.debugLine="Private Sub mP_LongClick";
RDebugUtils.currentLine=29097985;
 //BA.debugLineNum = 29097985;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29097986;
 //BA.debugLineNum = 29097986;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29097987;
 //BA.debugLineNum = 29097987;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29097988;
 //BA.debugLineNum = 29097988;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29097989;
 //BA.debugLineNum = 29097989;BA.debugLine="showInfoPage(4)";
_showinfopage((int) (4));
RDebugUtils.currentLine=29097990;
 //BA.debugLineNum = 29097990;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29097992;
 //BA.debugLineNum = 29097992;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navbtn_click", null));}
RDebugUtils.currentLine=27852800;
 //BA.debugLineNum = 27852800;BA.debugLine="Private Sub navBtn_Click";
RDebugUtils.currentLine=27852801;
 //BA.debugLineNum = 27852801;BA.debugLine="StartActivity(navActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._navactivity.getObject()));
RDebugUtils.currentLine=27852802;
 //BA.debugLineNum = 27852802;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navbtn_longclick", null));}
RDebugUtils.currentLine=29491200;
 //BA.debugLineNum = 29491200;BA.debugLine="Private Sub navBtn_LongClick";
RDebugUtils.currentLine=29491201;
 //BA.debugLineNum = 29491201;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29491202;
 //BA.debugLineNum = 29491202;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29491203;
 //BA.debugLineNum = 29491203;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29491204;
 //BA.debugLineNum = 29491204;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29491205;
 //BA.debugLineNum = 29491205;BA.debugLine="showInfoPage(9)";
_showinfopage((int) (9));
RDebugUtils.currentLine=29491206;
 //BA.debugLineNum = 29491206;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29491208;
 //BA.debugLineNum = 29491208;BA.debugLine="End Sub";
return "";
}
public static String  _notebook_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notebook_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notebook_click", null));}
RDebugUtils.currentLine=28377088;
 //BA.debugLineNum = 28377088;BA.debugLine="Private Sub noteBook_Click";
RDebugUtils.currentLine=28377089;
 //BA.debugLineNum = 28377089;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=28377091;
 //BA.debugLineNum = 28377091;BA.debugLine="CallSub(Me, \"NotesTransition1\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition1");
 break; }
case 1: {
RDebugUtils.currentLine=28377093;
 //BA.debugLineNum = 28377093;BA.debugLine="CallSub(Me, \"NotesTransition2\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition2");
 break; }
case 2: {
RDebugUtils.currentLine=28377095;
 //BA.debugLineNum = 28377095;BA.debugLine="CallSub(Me, \"NotesTransition3\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition3");
 break; }
}
;
RDebugUtils.currentLine=28377097;
 //BA.debugLineNum = 28377097;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _notestransition1() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notestransition1", false))
	 {return ((anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) Debug.delegate(mostCurrent.activityBA, "notestransition1", null));}
ResumableSub_NotesTransition1 rsub = new ResumableSub_NotesTransition1(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_NotesTransition1 extends BA.ResumableSub {
public ResumableSub_NotesTransition1(b4a.example.mainactivity parent) {
this.parent = parent;
}
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="mainactivity";

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
RDebugUtils.currentLine=28442625;
 //BA.debugLineNum = 28442625;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"Openbook.GIF\")";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Openbook.GIF");
RDebugUtils.currentLine=28442626;
 //BA.debugLineNum = 28442626;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28442627;
 //BA.debugLineNum = 28442627;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"Darkopenbook.G";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Darkopenbook.GIF");
RDebugUtils.currentLine=28442628;
 //BA.debugLineNum = 28442628;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28442629;
 //BA.debugLineNum = 28442629;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28442630;
 //BA.debugLineNum = 28442630;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28442632;
 //BA.debugLineNum = 28442632;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition1"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=28442634;
 //BA.debugLineNum = 28442634;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=28442635;
 //BA.debugLineNum = 28442635;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _notestransition2() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notestransition2", false))
	 {return ((anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) Debug.delegate(mostCurrent.activityBA, "notestransition2", null));}
ResumableSub_NotesTransition2 rsub = new ResumableSub_NotesTransition2(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_NotesTransition2 extends BA.ResumableSub {
public ResumableSub_NotesTransition2(b4a.example.mainactivity parent) {
this.parent = parent;
}
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="mainactivity";

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
RDebugUtils.currentLine=28508161;
 //BA.debugLineNum = 28508161;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes2.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes2.GIF");
RDebugUtils.currentLine=28508162;
 //BA.debugLineNum = 28508162;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28508163;
 //BA.debugLineNum = 28508163;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes2.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes2.GIF");
RDebugUtils.currentLine=28508164;
 //BA.debugLineNum = 28508164;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28508165;
 //BA.debugLineNum = 28508165;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28508166;
 //BA.debugLineNum = 28508166;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28508168;
 //BA.debugLineNum = 28508168;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition2"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=28508170;
 //BA.debugLineNum = 28508170;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=28508171;
 //BA.debugLineNum = 28508171;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _notestransition3() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notestransition3", false))
	 {return ((anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) Debug.delegate(mostCurrent.activityBA, "notestransition3", null));}
ResumableSub_NotesTransition3 rsub = new ResumableSub_NotesTransition3(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_NotesTransition3 extends BA.ResumableSub {
public ResumableSub_NotesTransition3(b4a.example.mainactivity parent) {
this.parent = parent;
}
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="mainactivity";

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
RDebugUtils.currentLine=28573697;
 //BA.debugLineNum = 28573697;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes3.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes3.GIF");
RDebugUtils.currentLine=28573698;
 //BA.debugLineNum = 28573698;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28573699;
 //BA.debugLineNum = 28573699;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes3.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes3.GIF");
RDebugUtils.currentLine=28573700;
 //BA.debugLineNum = 28573700;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28573701;
 //BA.debugLineNum = 28573701;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28573702;
 //BA.debugLineNum = 28573702;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28573704;
 //BA.debugLineNum = 28573704;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition3"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=28573706;
 //BA.debugLineNum = 28573706;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=28573707;
 //BA.debugLineNum = 28573707;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _notebook_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notebook_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notebook_longclick", null));}
RDebugUtils.currentLine=29163520;
 //BA.debugLineNum = 29163520;BA.debugLine="Private Sub noteBook_LongClick";
RDebugUtils.currentLine=29163521;
 //BA.debugLineNum = 29163521;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29163522;
 //BA.debugLineNum = 29163522;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29163523;
 //BA.debugLineNum = 29163523;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29163524;
 //BA.debugLineNum = 29163524;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29163525;
 //BA.debugLineNum = 29163525;BA.debugLine="showInfoPage(5)";
_showinfopage((int) (5));
RDebugUtils.currentLine=29163526;
 //BA.debugLineNum = 29163526;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29163528;
 //BA.debugLineNum = 29163528;BA.debugLine="End Sub";
return "";
}
public static String  _plant_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "plant_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "plant_click", null));}
RDebugUtils.currentLine=28704768;
 //BA.debugLineNum = 28704768;BA.debugLine="Private Sub plant_Click";
RDebugUtils.currentLine=28704769;
 //BA.debugLineNum = 28704769;BA.debugLine="StartActivity(themeActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._themeactivity.getObject()));
RDebugUtils.currentLine=28704770;
 //BA.debugLineNum = 28704770;BA.debugLine="End Sub";
return "";
}
public static String  _plant_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "plant_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "plant_longclick", null));}
RDebugUtils.currentLine=29294592;
 //BA.debugLineNum = 29294592;BA.debugLine="Private Sub plant_LongClick";
RDebugUtils.currentLine=29294593;
 //BA.debugLineNum = 29294593;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29294594;
 //BA.debugLineNum = 29294594;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29294595;
 //BA.debugLineNum = 29294595;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29294596;
 //BA.debugLineNum = 29294596;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29294597;
 //BA.debugLineNum = 29294597;BA.debugLine="showInfoPage(7)";
_showinfopage((int) (7));
RDebugUtils.currentLine=29294598;
 //BA.debugLineNum = 29294598;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29294600;
 //BA.debugLineNum = 29294600;BA.debugLine="End Sub";
return "";
}
public static String  _statsbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "statsbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "statsbtn_click", null));}
RDebugUtils.currentLine=27983872;
 //BA.debugLineNum = 27983872;BA.debugLine="Private Sub statsBtn_Click";
RDebugUtils.currentLine=27983873;
 //BA.debugLineNum = 27983873;BA.debugLine="StartActivity(Leaderboard)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._leaderboard.getObject()));
RDebugUtils.currentLine=27983874;
 //BA.debugLineNum = 27983874;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "todolistbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "todolistbtn_click", null));}
RDebugUtils.currentLine=28114944;
 //BA.debugLineNum = 28114944;BA.debugLine="Private Sub todolistBtn_Click";
RDebugUtils.currentLine=28114945;
 //BA.debugLineNum = 28114945;BA.debugLine="StartActivity(todoActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._todoactivity.getObject()));
RDebugUtils.currentLine=28114946;
 //BA.debugLineNum = 28114946;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "todolistbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "todolistbtn_longclick", null));}
RDebugUtils.currentLine=29229056;
 //BA.debugLineNum = 29229056;BA.debugLine="Private Sub todolistBtn_LongClick";
RDebugUtils.currentLine=29229057;
 //BA.debugLineNum = 29229057;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=29229058;
 //BA.debugLineNum = 29229058;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=29229059;
 //BA.debugLineNum = 29229059;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=29229060;
 //BA.debugLineNum = 29229060;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=29229061;
 //BA.debugLineNum = 29229061;BA.debugLine="showInfoPage(6)";
_showinfopage((int) (6));
RDebugUtils.currentLine=29229062;
 //BA.debugLineNum = 29229062;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29229064;
 //BA.debugLineNum = 29229064;BA.debugLine="End Sub";
return "";
}
}