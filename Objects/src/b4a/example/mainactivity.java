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
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
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
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Activity.LoadLayout(\"Layouthsv\")";
parent.mostCurrent._activity.LoadLayout("Layouthsv",mostCurrent.activityBA);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="If FirstTime Then";
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
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="kvs = Starter.notesKvs";
parent._kvs = parent.mostCurrent._starter._noteskvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="kvsPref = Starter.prefKvs";
parent._kvspref = parent.mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="timerClock.Initialize(\"timerClock\", 1000)";
parent._timerclock.Initialize(processBA,"timerClock",(long) (1000));
RDebugUtils.currentLine=917510;
 //BA.debugLineNum = 917510;BA.debugLine="timerClock.Enabled = True";
parent._timerclock.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 4:
//C
this.state = 5;
;
RDebugUtils.currentLine=917513;
 //BA.debugLineNum = 917513;BA.debugLine="hsv.Panel.Width = size";
parent.mostCurrent._hsv.getPanel().setWidth(parent._size);
RDebugUtils.currentLine=917514;
 //BA.debugLineNum = 917514;BA.debugLine="hsv.Panel.Height = size";
parent.mostCurrent._hsv.getPanel().setHeight(parent._size);
RDebugUtils.currentLine=917516;
 //BA.debugLineNum = 917516;BA.debugLine="regLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._reglayout = parent._xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=917517;
 //BA.debugLineNum = 917517;BA.debugLine="darkModeLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._darkmodelayout = parent._xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=917519;
 //BA.debugLineNum = 917519;BA.debugLine="hsv.Panel.AddView(regLayout, 0, 0, hsv.Panel.Widt";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._reglayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
RDebugUtils.currentLine=917520;
 //BA.debugLineNum = 917520;BA.debugLine="hsv.Panel.AddView(darkModeLayout, 0, 0, hsv.Panel";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._darkmodelayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
RDebugUtils.currentLine=917522;
 //BA.debugLineNum = 917522;BA.debugLine="Select Starter.themeNumber";
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
RDebugUtils.currentLine=917524;
 //BA.debugLineNum = 917524;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
parent.mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=917525;
 //BA.debugLineNum = 917525;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=917526;
 //BA.debugLineNum = 917526;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnComputer";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
RDebugUtils.currentLine=917527;
 //BA.debugLineNum = 917527;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtncom";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtncomputer.GIF");
 if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=917529;
 //BA.debugLineNum = 917529;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
parent.mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=917530;
 //BA.debugLineNum = 917530;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
RDebugUtils.currentLine=917531;
 //BA.debugLineNum = 917531;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.G";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
RDebugUtils.currentLine=917532;
 //BA.debugLineNum = 917532;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=917534;
 //BA.debugLineNum = 917534;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
parent.mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
RDebugUtils.currentLine=917535;
 //BA.debugLineNum = 917535;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
RDebugUtils.currentLine=917536;
 //BA.debugLineNum = 917536;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\")";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
RDebugUtils.currentLine=917537;
 //BA.debugLineNum = 917537;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
RDebugUtils.currentLine=917538;
 //BA.debugLineNum = 917538;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
parent.mostCurrent._curtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
RDebugUtils.currentLine=917539;
 //BA.debugLineNum = 917539;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\")";
parent.mostCurrent._dcurtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 if (true) break;
;
RDebugUtils.currentLine=917542;
 //BA.debugLineNum = 917542;BA.debugLine="If Starter.darkMode Then";

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
RDebugUtils.currentLine=917543;
 //BA.debugLineNum = 917543;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=917544;
 //BA.debugLineNum = 917544;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
RDebugUtils.currentLine=917545;
 //BA.debugLineNum = 917545;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=917547;
 //BA.debugLineNum = 917547;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917548;
 //BA.debugLineNum = 917548;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
 if (true) break;

case 17:
//C
this.state = -1;
;
RDebugUtils.currentLine=917551;
 //BA.debugLineNum = 917551;BA.debugLine="Sleep(50)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "activity_create"),(int) (50));
this.state = 18;
return;
case 18:
//C
this.state = -1;
;
RDebugUtils.currentLine=917552;
 //BA.debugLineNum = 917552;BA.debugLine="hsv.ScrollPosition = Max(0, (hsv.Panel.Width - 10";
parent.mostCurrent._hsv.setScrollPosition((int) (anywheresoftware.b4a.keywords.Common.Max(0,(parent.mostCurrent._hsv.getPanel().getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA))/(double)2)));
RDebugUtils.currentLine=917554;
 //BA.debugLineNum = 917554;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="mainactivity";
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="If format24h Then";
if (_format24h) { 
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="DateTime.TimeFormat = \"HH:mm\" ' 24-Hour Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 }else {
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\" ' AM/PM Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 };
RDebugUtils.currentLine=983048;
 //BA.debugLineNum = 983048;BA.debugLine="If Starter.themeChanged Then";
if (mostCurrent._starter._themechanged /*boolean*/ ) { 
RDebugUtils.currentLine=983049;
 //BA.debugLineNum = 983049;BA.debugLine="regLayout.RemoveAllViews";
mostCurrent._reglayout.RemoveAllViews();
RDebugUtils.currentLine=983050;
 //BA.debugLineNum = 983050;BA.debugLine="darkModeLayout.RemoveAllViews";
mostCurrent._darkmodelayout.RemoveAllViews();
RDebugUtils.currentLine=983052;
 //BA.debugLineNum = 983052;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=983054;
 //BA.debugLineNum = 983054;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=983055;
 //BA.debugLineNum = 983055;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=983056;
 //BA.debugLineNum = 983056;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnCompute";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
RDebugUtils.currentLine=983057;
 //BA.debugLineNum = 983057;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtnCo";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtnComputer.GIF");
 break; }
case 1: {
RDebugUtils.currentLine=983059;
 //BA.debugLineNum = 983059;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=983060;
 //BA.debugLineNum = 983060;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
RDebugUtils.currentLine=983061;
 //BA.debugLineNum = 983061;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
RDebugUtils.currentLine=983062;
 //BA.debugLineNum = 983062;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GI";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 break; }
case 2: {
RDebugUtils.currentLine=983064;
 //BA.debugLineNum = 983064;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
RDebugUtils.currentLine=983065;
 //BA.debugLineNum = 983065;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
RDebugUtils.currentLine=983066;
 //BA.debugLineNum = 983066;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\"";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
RDebugUtils.currentLine=983067;
 //BA.debugLineNum = 983067;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GI";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
RDebugUtils.currentLine=983068;
 //BA.debugLineNum = 983068;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
mostCurrent._curtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
RDebugUtils.currentLine=983069;
 //BA.debugLineNum = 983069;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\"";
mostCurrent._dcurtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 break; }
}
;
RDebugUtils.currentLine=983071;
 //BA.debugLineNum = 983071;BA.debugLine="Starter.themeChanged = False";
mostCurrent._starter._themechanged /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=983073;
 //BA.debugLineNum = 983073;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bookie_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bookie_click", null));}
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Private Sub bookie_Click";
RDebugUtils.currentLine=1966081;
 //BA.debugLineNum = 1966081;BA.debugLine="StartActivity(FlashcardActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._flashcardactivity.getObject()));
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bookie_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bookie_longclick", null));}
RDebugUtils.currentLine=2752512;
 //BA.debugLineNum = 2752512;BA.debugLine="Private Sub bookie_LongClick";
RDebugUtils.currentLine=2752513;
 //BA.debugLineNum = 2752513;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2752515;
 //BA.debugLineNum = 2752515;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2752516;
 //BA.debugLineNum = 2752516;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2752517;
 //BA.debugLineNum = 2752517;BA.debugLine="showInfoPage(3)";
_showinfopage((int) (3));
RDebugUtils.currentLine=2752518;
 //BA.debugLineNum = 2752518;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2752520;
 //BA.debugLineNum = 2752520;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopopup() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showinfopopup", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showinfopopup", null));}
anywheresoftware.b4a.objects.ButtonWrapper _closebtn = null;
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Private Sub showInfoPopup";
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="infoPnl = xui.CreatePanel(\"infoPnl\")";
mostCurrent._infopnl = _xui.CreatePanel(processBA,"infoPnl");
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="Activity.AddView(infoPnl, 75dip, 225dip, 300dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._infopnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (220)));
RDebugUtils.currentLine=1310724;
 //BA.debugLineNum = 1310724;BA.debugLine="infoPnl.SetColorAndBorder(xui.Color_White, 2dip,";
mostCurrent._infopnl.SetColorAndBorder(_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=1310726;
 //BA.debugLineNum = 1310726;BA.debugLine="Dim closeBtn As Button";
_closebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=1310727;
 //BA.debugLineNum = 1310727;BA.debugLine="closeBtn.Initialize(\"infoPnlClose\")";
_closebtn.Initialize(mostCurrent.activityBA,"infoPnlClose");
RDebugUtils.currentLine=1310728;
 //BA.debugLineNum = 1310728;BA.debugLine="closeBtn.Text = \"x\"";
_closebtn.setText(BA.ObjectToCharSequence("x"));
RDebugUtils.currentLine=1310729;
 //BA.debugLineNum = 1310729;BA.debugLine="closeBtn.TextSize = 6";
_closebtn.setTextSize((float) (6));
RDebugUtils.currentLine=1310730;
 //BA.debugLineNum = 1310730;BA.debugLine="infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28";
mostCurrent._infopnl.AddView((android.view.View)(_closebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (265)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)));
RDebugUtils.currentLine=1310732;
 //BA.debugLineNum = 1310732;BA.debugLine="infoTitleLbl.Initialize(\"\")";
mostCurrent._infotitlelbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1310733;
 //BA.debugLineNum = 1310733;BA.debugLine="infoTitleLbl.TextSize = 16";
mostCurrent._infotitlelbl.setTextSize((float) (16));
RDebugUtils.currentLine=1310734;
 //BA.debugLineNum = 1310734;BA.debugLine="infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infotitlelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=1310735;
 //BA.debugLineNum = 1310735;BA.debugLine="infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infotitlelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (248)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=1310737;
 //BA.debugLineNum = 1310737;BA.debugLine="infoDescLbl.Initialize(\"\")";
mostCurrent._infodesclbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1310738;
 //BA.debugLineNum = 1310738;BA.debugLine="infoDescLbl.TextSize = 10";
mostCurrent._infodesclbl.setTextSize((float) (10));
RDebugUtils.currentLine=1310739;
 //BA.debugLineNum = 1310739;BA.debugLine="infoDescLbl.Gravity = Gravity.TOP";
mostCurrent._infodesclbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
RDebugUtils.currentLine=1310740;
 //BA.debugLineNum = 1310740;BA.debugLine="infoDescLbl.SingleLine = False";
mostCurrent._infodesclbl.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1310741;
 //BA.debugLineNum = 1310741;BA.debugLine="infoPnl.AddView(infoDescLbl, 12dip, 52dip, 276dip";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infodesclbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (276)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (140)));
RDebugUtils.currentLine=1310743;
 //BA.debugLineNum = 1310743;BA.debugLine="infoPageLbl.Initialize(\"\")";
mostCurrent._infopagelbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1310744;
 //BA.debugLineNum = 1310744;BA.debugLine="infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infopagelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=1310745;
 //BA.debugLineNum = 1310745;BA.debugLine="infoPageLbl.TextSize = 10";
mostCurrent._infopagelbl.setTextSize((float) (10));
RDebugUtils.currentLine=1310746;
 //BA.debugLineNum = 1310746;BA.debugLine="infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infopagelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (184)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
RDebugUtils.currentLine=1310749;
 //BA.debugLineNum = 1310749;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
RDebugUtils.currentLine=1310750;
 //BA.debugLineNum = 1310750;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopage(int _page) throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showinfopage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showinfopage", new Object[] {_page}));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Private Sub showInfoPage(page As Int)";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="infoPage = page";
_infopage = _page;
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="Select page";
switch (_page) {
case 0: {
RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="infoTitleLbl.Text = \"Calendar\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Calendar"));
RDebugUtils.currentLine=1376261;
 //BA.debugLineNum = 1376261;BA.debugLine="infoDescLbl.Text = \"The calendar comes in three";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."));
 break; }
case 1: {
RDebugUtils.currentLine=1376263;
 //BA.debugLineNum = 1376263;BA.debugLine="infoTitleLbl.Text = \"Clock\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Clock"));
RDebugUtils.currentLine=1376264;
 //BA.debugLineNum = 1376264;BA.debugLine="infoDescLbl.Text = \"The clock keeps you on time";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."));
 break; }
case 2: {
RDebugUtils.currentLine=1376266;
 //BA.debugLineNum = 1376266;BA.debugLine="infoTitleLbl.Text = \"Corkboard\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Corkboard"));
RDebugUtils.currentLine=1376267;
 //BA.debugLineNum = 1376267;BA.debugLine="infoDescLbl.Text = \"The corkboard gives you a c";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."));
 break; }
case 3: {
RDebugUtils.currentLine=1376269;
 //BA.debugLineNum = 1376269;BA.debugLine="infoTitleLbl.Text = \"Flashcards\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Flashcards"));
RDebugUtils.currentLine=1376270;
 //BA.debugLineNum = 1376270;BA.debugLine="infoDescLbl.Text = \"The flashcard feature organ";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."));
 break; }
case 4: {
RDebugUtils.currentLine=1376272;
 //BA.debugLineNum = 1376272;BA.debugLine="infoTitleLbl.Text = \"Music Player\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Music Player"));
RDebugUtils.currentLine=1376273;
 //BA.debugLineNum = 1376273;BA.debugLine="infoDescLbl.Text = \"The music player plays the";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music.  "));
 break; }
case 5: {
RDebugUtils.currentLine=1376275;
 //BA.debugLineNum = 1376275;BA.debugLine="infoTitleLbl.Text = \"Notepad\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Notepad"));
RDebugUtils.currentLine=1376276;
 //BA.debugLineNum = 1376276;BA.debugLine="infoDescLbl.Text = \"The notepad keeps all your";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."));
 break; }
case 6: {
RDebugUtils.currentLine=1376278;
 //BA.debugLineNum = 1376278;BA.debugLine="infoTitleLbl.Text = \"To-do List\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("To-do List"));
RDebugUtils.currentLine=1376279;
 //BA.debugLineNum = 1376279;BA.debugLine="infoDescLbl.Text = \"The to-do list enables you";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."));
 break; }
case 7: {
RDebugUtils.currentLine=1376281;
 //BA.debugLineNum = 1376281;BA.debugLine="infoTitleLbl.Text = \"Themes\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Themes"));
RDebugUtils.currentLine=1376282;
 //BA.debugLineNum = 1376282;BA.debugLine="infoDescLbl.Text = \"Themes let you put your own";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
 break; }
case 8: {
RDebugUtils.currentLine=1376284;
 //BA.debugLineNum = 1376284;BA.debugLine="infoTitleLbl.Text = \"Lamp\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Lamp"));
RDebugUtils.currentLine=1376285;
 //BA.debugLineNum = 1376285;BA.debugLine="infoDescLbl.Text = \"The lamp gives you control";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
 break; }
case 9: {
RDebugUtils.currentLine=1376287;
 //BA.debugLineNum = 1376287;BA.debugLine="infoTitleLbl.Text = \"Navigation\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Navigation"));
RDebugUtils.currentLine=1376288;
 //BA.debugLineNum = 1376288;BA.debugLine="infoDescLbl.Text = \"Navigation is your home bas";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
 break; }
}
;
RDebugUtils.currentLine=1376292;
 //BA.debugLineNum = 1376292;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calendar_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "calendar_click", null));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Private Sub calendar_Click";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calendar_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "calendar_longclick", null));}
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Private Sub calendar_LongClick";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2490370;
 //BA.debugLineNum = 2490370;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2490371;
 //BA.debugLineNum = 2490371;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2490372;
 //BA.debugLineNum = 2490372;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2490373;
 //BA.debugLineNum = 2490373;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
RDebugUtils.currentLine=2490374;
 //BA.debugLineNum = 2490374;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2490376;
 //BA.debugLineNum = 2490376;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clockbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clockbtn_click", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub clockBtn_Click";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clockbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clockbtn_longclick", null));}
RDebugUtils.currentLine=2555904;
 //BA.debugLineNum = 2555904;BA.debugLine="Private Sub clockBtn_LongClick";
RDebugUtils.currentLine=2555905;
 //BA.debugLineNum = 2555905;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2555906;
 //BA.debugLineNum = 2555906;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2555907;
 //BA.debugLineNum = 2555907;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2555908;
 //BA.debugLineNum = 2555908;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2555909;
 //BA.debugLineNum = 2555909;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
RDebugUtils.currentLine=2555910;
 //BA.debugLineNum = 2555910;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2555912;
 //BA.debugLineNum = 2555912;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clocklightbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clocklightbtn_click", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Private Sub  clockLightBtn_Click";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clocklightbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clocklightbtn_longclick", null));}
RDebugUtils.currentLine=2621440;
 //BA.debugLineNum = 2621440;BA.debugLine="Private Sub clockLightBtn_LongClick";
RDebugUtils.currentLine=2621441;
 //BA.debugLineNum = 2621441;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2621442;
 //BA.debugLineNum = 2621442;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2621443;
 //BA.debugLineNum = 2621443;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621444;
 //BA.debugLineNum = 2621444;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2621445;
 //BA.debugLineNum = 2621445;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
RDebugUtils.currentLine=2621446;
 //BA.debugLineNum = 2621446;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2621448;
 //BA.debugLineNum = 2621448;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "corkie_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "corkie_click", null));}
RDebugUtils.currentLine=2359296;
 //BA.debugLineNum = 2359296;BA.debugLine="Private Sub corkie_Click";
RDebugUtils.currentLine=2359297;
 //BA.debugLineNum = 2359297;BA.debugLine="StartActivity(corkActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._corkactivity.getObject()));
RDebugUtils.currentLine=2359298;
 //BA.debugLineNum = 2359298;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "corkie_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "corkie_longclick", null));}
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Private Sub corkie_LongClick";
RDebugUtils.currentLine=2686977;
 //BA.debugLineNum = 2686977;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2686978;
 //BA.debugLineNum = 2686978;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2686979;
 //BA.debugLineNum = 2686979;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2686980;
 //BA.debugLineNum = 2686980;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2686981;
 //BA.debugLineNum = 2686981;BA.debugLine="showInfoPage(2)";
_showinfopage((int) (2));
RDebugUtils.currentLine=2686982;
 //BA.debugLineNum = 2686982;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2686984;
 //BA.debugLineNum = 2686984;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Starter.darkMode = False";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="kvsPref.Put(\"darkMode\", False)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.False));
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="regLayout.Visible = True";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179652;
 //BA.debugLineNum = 1179652;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
RDebugUtils.currentLine=1179653;
 //BA.debugLineNum = 1179653;BA.debugLine="regLayout.Alpha = 0";
parent.mostCurrent._reglayout.setAlpha((float) (0));
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="regLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (1));
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (0));
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "dlamp_click"),(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=1179657;
 //BA.debugLineNum = 1179657;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179658;
 //BA.debugLineNum = 1179658;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _dlamp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dlamp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dlamp_longclick", null));}
RDebugUtils.currentLine=3145728;
 //BA.debugLineNum = 3145728;BA.debugLine="Private Sub dlamp_LongClick";
RDebugUtils.currentLine=3145729;
 //BA.debugLineNum = 3145729;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3145730;
 //BA.debugLineNum = 3145730;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3145731;
 //BA.debugLineNum = 3145731;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3145732;
 //BA.debugLineNum = 3145732;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3145733;
 //BA.debugLineNum = 3145733;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
RDebugUtils.currentLine=3145734;
 //BA.debugLineNum = 3145734;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3145736;
 //BA.debugLineNum = 3145736;BA.debugLine="End Sub";
return "";
}
public static String  _helpbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "helpbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "helpbtn_click", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Private Sub helpBtn_Click";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="StartActivity(helpActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._helpactivity.getObject()));
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="End Sub";
return "";
}
public static String  _infopnlclose_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "infopnlclose_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "infopnlclose_click", null));}
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Private Sub infoPnlClose_Click";
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="infoPnl.Visible = False";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Starter.darkMode = True";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="kvsPref.Put(\"darkMode\", True)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.True));
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1114116;
 //BA.debugLineNum = 1114116;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
RDebugUtils.currentLine=1114117;
 //BA.debugLineNum = 1114117;BA.debugLine="darkModeLayout.Alpha = 0";
parent.mostCurrent._darkmodelayout.setAlpha((float) (0));
RDebugUtils.currentLine=1114118;
 //BA.debugLineNum = 1114118;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (1));
RDebugUtils.currentLine=1114119;
 //BA.debugLineNum = 1114119;BA.debugLine="regLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (0));
RDebugUtils.currentLine=1114120;
 //BA.debugLineNum = 1114120;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "lamp_click"),(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=1114121;
 //BA.debugLineNum = 1114121;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114122;
 //BA.debugLineNum = 1114122;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lamp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lamp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lamp_longclick", null));}
RDebugUtils.currentLine=3080192;
 //BA.debugLineNum = 3080192;BA.debugLine="Private Sub lamp_LongClick";
RDebugUtils.currentLine=3080193;
 //BA.debugLineNum = 3080193;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3080194;
 //BA.debugLineNum = 3080194;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3080195;
 //BA.debugLineNum = 3080195;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3080196;
 //BA.debugLineNum = 3080196;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3080197;
 //BA.debugLineNum = 3080197;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
RDebugUtils.currentLine=3080198;
 //BA.debugLineNum = 3080198;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3080200;
 //BA.debugLineNum = 3080200;BA.debugLine="End Sub";
return "";
}
public static String  _mp_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mp_click", null));}
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Private Sub mP_Click";
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="StartActivity(musicActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._musicactivity.getObject()));
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="End Sub";
return "";
}
public static String  _mp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mp_longclick", null));}
RDebugUtils.currentLine=2818048;
 //BA.debugLineNum = 2818048;BA.debugLine="Private Sub mP_LongClick";
RDebugUtils.currentLine=2818049;
 //BA.debugLineNum = 2818049;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2818050;
 //BA.debugLineNum = 2818050;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2818051;
 //BA.debugLineNum = 2818051;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2818052;
 //BA.debugLineNum = 2818052;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2818053;
 //BA.debugLineNum = 2818053;BA.debugLine="showInfoPage(4)";
_showinfopage((int) (4));
RDebugUtils.currentLine=2818054;
 //BA.debugLineNum = 2818054;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2818056;
 //BA.debugLineNum = 2818056;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navbtn_click", null));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Private Sub navBtn_Click";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="StartActivity(navActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._navactivity.getObject()));
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navbtn_longclick", null));}
RDebugUtils.currentLine=3211264;
 //BA.debugLineNum = 3211264;BA.debugLine="Private Sub navBtn_LongClick";
RDebugUtils.currentLine=3211265;
 //BA.debugLineNum = 3211265;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3211266;
 //BA.debugLineNum = 3211266;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3211267;
 //BA.debugLineNum = 3211267;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3211268;
 //BA.debugLineNum = 3211268;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3211269;
 //BA.debugLineNum = 3211269;BA.debugLine="showInfoPage(9)";
_showinfopage((int) (9));
RDebugUtils.currentLine=3211270;
 //BA.debugLineNum = 3211270;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3211272;
 //BA.debugLineNum = 3211272;BA.debugLine="End Sub";
return "";
}
public static String  _notebook_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notebook_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notebook_click", null));}
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Private Sub noteBook_Click";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=2097155;
 //BA.debugLineNum = 2097155;BA.debugLine="CallSub(Me, \"NotesTransition1\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition1");
 break; }
case 1: {
RDebugUtils.currentLine=2097157;
 //BA.debugLineNum = 2097157;BA.debugLine="CallSub(Me, \"NotesTransition2\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition2");
 break; }
case 2: {
RDebugUtils.currentLine=2097159;
 //BA.debugLineNum = 2097159;BA.debugLine="CallSub(Me, \"NotesTransition3\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition3");
 break; }
}
;
RDebugUtils.currentLine=2097161;
 //BA.debugLineNum = 2097161;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2162689;
 //BA.debugLineNum = 2162689;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"Openbook.GIF\")";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Openbook.GIF");
RDebugUtils.currentLine=2162690;
 //BA.debugLineNum = 2162690;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2162691;
 //BA.debugLineNum = 2162691;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"Darkopenbook.G";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Darkopenbook.GIF");
RDebugUtils.currentLine=2162692;
 //BA.debugLineNum = 2162692;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2162693;
 //BA.debugLineNum = 2162693;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2162694;
 //BA.debugLineNum = 2162694;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2162696;
 //BA.debugLineNum = 2162696;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition1"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=2162698;
 //BA.debugLineNum = 2162698;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=2162699;
 //BA.debugLineNum = 2162699;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2228225;
 //BA.debugLineNum = 2228225;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes2.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes2.GIF");
RDebugUtils.currentLine=2228226;
 //BA.debugLineNum = 2228226;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2228227;
 //BA.debugLineNum = 2228227;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes2.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes2.GIF");
RDebugUtils.currentLine=2228228;
 //BA.debugLineNum = 2228228;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2228229;
 //BA.debugLineNum = 2228229;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2228230;
 //BA.debugLineNum = 2228230;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2228232;
 //BA.debugLineNum = 2228232;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition2"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=2228234;
 //BA.debugLineNum = 2228234;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=2228235;
 //BA.debugLineNum = 2228235;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2293761;
 //BA.debugLineNum = 2293761;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes3.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes3.GIF");
RDebugUtils.currentLine=2293762;
 //BA.debugLineNum = 2293762;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2293763;
 //BA.debugLineNum = 2293763;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes3.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes3.GIF");
RDebugUtils.currentLine=2293764;
 //BA.debugLineNum = 2293764;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2293765;
 //BA.debugLineNum = 2293765;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2293766;
 //BA.debugLineNum = 2293766;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2293768;
 //BA.debugLineNum = 2293768;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition3"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=2293770;
 //BA.debugLineNum = 2293770;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=2293771;
 //BA.debugLineNum = 2293771;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _notebook_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notebook_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notebook_longclick", null));}
RDebugUtils.currentLine=2883584;
 //BA.debugLineNum = 2883584;BA.debugLine="Private Sub noteBook_LongClick";
RDebugUtils.currentLine=2883585;
 //BA.debugLineNum = 2883585;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2883586;
 //BA.debugLineNum = 2883586;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2883587;
 //BA.debugLineNum = 2883587;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2883588;
 //BA.debugLineNum = 2883588;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2883589;
 //BA.debugLineNum = 2883589;BA.debugLine="showInfoPage(5)";
_showinfopage((int) (5));
RDebugUtils.currentLine=2883590;
 //BA.debugLineNum = 2883590;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2883592;
 //BA.debugLineNum = 2883592;BA.debugLine="End Sub";
return "";
}
public static String  _plant_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "plant_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "plant_click", null));}
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Private Sub plant_Click";
RDebugUtils.currentLine=2424833;
 //BA.debugLineNum = 2424833;BA.debugLine="StartActivity(themeActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._themeactivity.getObject()));
RDebugUtils.currentLine=2424834;
 //BA.debugLineNum = 2424834;BA.debugLine="End Sub";
return "";
}
public static String  _plant_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "plant_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "plant_longclick", null));}
RDebugUtils.currentLine=3014656;
 //BA.debugLineNum = 3014656;BA.debugLine="Private Sub plant_LongClick";
RDebugUtils.currentLine=3014657;
 //BA.debugLineNum = 3014657;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3014658;
 //BA.debugLineNum = 3014658;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3014659;
 //BA.debugLineNum = 3014659;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3014660;
 //BA.debugLineNum = 3014660;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3014661;
 //BA.debugLineNum = 3014661;BA.debugLine="showInfoPage(7)";
_showinfopage((int) (7));
RDebugUtils.currentLine=3014662;
 //BA.debugLineNum = 3014662;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3014664;
 //BA.debugLineNum = 3014664;BA.debugLine="End Sub";
return "";
}
public static String  _statsbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "statsbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "statsbtn_click", null));}
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Private Sub statsBtn_Click";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="StartActivity(Leaderboard)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._leaderboard.getObject()));
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="End Sub";
return "";
}
public static String  _timerclock_tick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerclock_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerclock_tick", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub timerClock_Tick";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="clockBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clockbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="clockLightBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clocklightbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=1245187;
 //BA.debugLineNum = 1245187;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "todolistbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "todolistbtn_click", null));}
RDebugUtils.currentLine=1835008;
 //BA.debugLineNum = 1835008;BA.debugLine="Private Sub todolistBtn_Click";
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="StartActivity(todoActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._todoactivity.getObject()));
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "todolistbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "todolistbtn_longclick", null));}
RDebugUtils.currentLine=2949120;
 //BA.debugLineNum = 2949120;BA.debugLine="Private Sub todolistBtn_LongClick";
RDebugUtils.currentLine=2949121;
 //BA.debugLineNum = 2949121;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=2949122;
 //BA.debugLineNum = 2949122;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=2949123;
 //BA.debugLineNum = 2949123;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2949124;
 //BA.debugLineNum = 2949124;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=2949125;
 //BA.debugLineNum = 2949125;BA.debugLine="showInfoPage(6)";
_showinfopage((int) (6));
RDebugUtils.currentLine=2949126;
 //BA.debugLineNum = 2949126;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2949128;
 //BA.debugLineNum = 2949128;BA.debugLine="End Sub";
return "";
}
}