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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.mainactivity");
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
public b4a.example.day_module _day_module = null;
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
public static void  _activity_create(boolean _firsttime) throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"Layouthsv\")";
parent.mostCurrent._activity.LoadLayout("Layouthsv",mostCurrent.activityBA);
 //BA.debugLineNum = 36;BA.debugLine="If FirstTime Then";
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
 //BA.debugLineNum = 37;BA.debugLine="kvs = Starter.notesKvs";
parent._kvs = parent.mostCurrent._starter._noteskvs /*b4a.example3.keyvaluestore*/ ;
 //BA.debugLineNum = 38;BA.debugLine="kvsPref = Starter.prefKvs";
parent._kvspref = parent.mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ;
 //BA.debugLineNum = 39;BA.debugLine="timerClock.Initialize(\"timerClock\", 1000)";
parent._timerclock.Initialize(processBA,"timerClock",(long) (1000));
 //BA.debugLineNum = 40;BA.debugLine="timerClock.Enabled = True";
parent._timerclock.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 4:
//C
this.state = 5;
;
 //BA.debugLineNum = 43;BA.debugLine="hsv.Panel.Width = size";
parent.mostCurrent._hsv.getPanel().setWidth(parent._size);
 //BA.debugLineNum = 44;BA.debugLine="hsv.Panel.Height = size";
parent.mostCurrent._hsv.getPanel().setHeight(parent._size);
 //BA.debugLineNum = 46;BA.debugLine="regLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._reglayout = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 47;BA.debugLine="darkModeLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._darkmodelayout = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 49;BA.debugLine="hsv.Panel.AddView(regLayout, 0, 0, hsv.Panel.Widt";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._reglayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
 //BA.debugLineNum = 50;BA.debugLine="hsv.Panel.AddView(darkModeLayout, 0, 0, hsv.Panel";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._darkmodelayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
 //BA.debugLineNum = 52;BA.debugLine="Select Starter.themeNumber";
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
 //BA.debugLineNum = 54;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
parent.mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
 //BA.debugLineNum = 55;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
 //BA.debugLineNum = 56;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnComputer";
parent.mostCurrent._computergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
 //BA.debugLineNum = 57;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtncom";
parent.mostCurrent._dcomputergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtncomputer.GIF");
 if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 59;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
parent.mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
 //BA.debugLineNum = 60;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.G";
parent.mostCurrent._computergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
 //BA.debugLineNum = 62;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 64;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
parent.mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
 //BA.debugLineNum = 65;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
 //BA.debugLineNum = 66;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\")";
parent.mostCurrent._computergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
 //BA.debugLineNum = 67;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
 //BA.debugLineNum = 68;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
parent.mostCurrent._curtain._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
 //BA.debugLineNum = 69;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\")";
parent.mostCurrent._dcurtain._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 if (true) break;
;
 //BA.debugLineNum = 72;BA.debugLine="If Starter.darkMode Then";

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
 //BA.debugLineNum = 73;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 74;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
 //BA.debugLineNum = 75;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 77;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 78;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
 if (true) break;

case 17:
//C
this.state = -1;
;
 //BA.debugLineNum = 81;BA.debugLine="Sleep(50)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (50));
this.state = 18;
return;
case 18:
//C
this.state = -1;
;
 //BA.debugLineNum = 82;BA.debugLine="hsv.ScrollPosition = Max(0, (hsv.Panel.Width - 10";
parent.mostCurrent._hsv.setScrollPosition((int) (anywheresoftware.b4a.keywords.Common.Max(0,(parent.mostCurrent._hsv.getPanel().getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA))/(double)2)));
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 88;BA.debugLine="If format24h Then";
if (_format24h) { 
 //BA.debugLineNum = 89;BA.debugLine="DateTime.TimeFormat = \"HH:mm\" ' 24-Hour Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 }else {
 //BA.debugLineNum = 91;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\" ' AM/PM Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 };
 //BA.debugLineNum = 94;BA.debugLine="If Starter.themeChanged Then";
if (mostCurrent._starter._themechanged /*boolean*/ ) { 
 //BA.debugLineNum = 95;BA.debugLine="regLayout.RemoveAllViews";
mostCurrent._reglayout.RemoveAllViews();
 //BA.debugLineNum = 96;BA.debugLine="darkModeLayout.RemoveAllViews";
mostCurrent._darkmodelayout.RemoveAllViews();
 //BA.debugLineNum = 98;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 100;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
 //BA.debugLineNum = 101;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
 //BA.debugLineNum = 102;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnCompute";
mostCurrent._computergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
 //BA.debugLineNum = 103;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtnCo";
mostCurrent._dcomputergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtnComputer.GIF");
 break; }
case 1: {
 //BA.debugLineNum = 105;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
 //BA.debugLineNum = 106;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
 //BA.debugLineNum = 107;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.";
mostCurrent._computergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
 //BA.debugLineNum = 108;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GI";
mostCurrent._dcomputergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 break; }
case 2: {
 //BA.debugLineNum = 110;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
 //BA.debugLineNum = 111;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
 //BA.debugLineNum = 112;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\"";
mostCurrent._computergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
 //BA.debugLineNum = 113;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GI";
mostCurrent._dcomputergif._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
 //BA.debugLineNum = 114;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
mostCurrent._curtain._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
 //BA.debugLineNum = 115;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\"";
mostCurrent._dcurtain._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 break; }
}
;
 //BA.debugLineNum = 117;BA.debugLine="Starter.themeChanged = False";
mostCurrent._starter._themechanged /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_click() throws Exception{
 //BA.debugLineNum = 253;BA.debugLine="Private Sub bookie_Click";
 //BA.debugLineNum = 254;BA.debugLine="StartActivity(FlashcardActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._flashcardactivity.getObject()));
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_longclick() throws Exception{
 //BA.debugLineNum = 360;BA.debugLine="Private Sub bookie_LongClick";
 //BA.debugLineNum = 361;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 362;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 363;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 364;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 365;BA.debugLine="showInfoPage(3)";
_showinfopage((int) (3));
 //BA.debugLineNum = 366;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 368;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_click() throws Exception{
 //BA.debugLineNum = 257;BA.debugLine="Private Sub calendar_Click";
 //BA.debugLineNum = 258;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_longclick() throws Exception{
 //BA.debugLineNum = 320;BA.debugLine="Private Sub calendar_LongClick";
 //BA.debugLineNum = 321;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 322;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 323;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 324;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 325;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
 //BA.debugLineNum = 326;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 328;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_click() throws Exception{
 //BA.debugLineNum = 225;BA.debugLine="Private Sub clockBtn_Click";
 //BA.debugLineNum = 226;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_longclick() throws Exception{
 //BA.debugLineNum = 330;BA.debugLine="Private Sub clockBtn_LongClick";
 //BA.debugLineNum = 331;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 332;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 333;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 334;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 335;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
 //BA.debugLineNum = 336;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_click() throws Exception{
 //BA.debugLineNum = 229;BA.debugLine="Private Sub  clockLightBtn_Click";
 //BA.debugLineNum = 230;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_longclick() throws Exception{
 //BA.debugLineNum = 340;BA.debugLine="Private Sub clockLightBtn_LongClick";
 //BA.debugLineNum = 341;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 342;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 343;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 344;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 345;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
 //BA.debugLineNum = 346;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 348;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_click() throws Exception{
 //BA.debugLineNum = 311;BA.debugLine="Private Sub corkie_Click";
 //BA.debugLineNum = 312;BA.debugLine="StartActivity(corkActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._corkactivity.getObject()));
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_longclick() throws Exception{
 //BA.debugLineNum = 350;BA.debugLine="Private Sub corkie_LongClick";
 //BA.debugLineNum = 351;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 352;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 353;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 354;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 355;BA.debugLine="showInfoPage(2)";
_showinfopage((int) (2));
 //BA.debugLineNum = 356;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 358;BA.debugLine="End Sub";
return "";
}
public static void  _dlamp_click() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 138;BA.debugLine="Starter.darkMode = False";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 139;BA.debugLine="kvsPref.Put(\"darkMode\", False)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.False));
 //BA.debugLineNum = 140;BA.debugLine="regLayout.Visible = True";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 141;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
 //BA.debugLineNum = 142;BA.debugLine="regLayout.Alpha = 0";
parent.mostCurrent._reglayout.setAlpha((float) (0));
 //BA.debugLineNum = 143;BA.debugLine="regLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (1));
 //BA.debugLineNum = 144;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (0));
 //BA.debugLineNum = 145;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 146;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _dlamp_longclick() throws Exception{
 //BA.debugLineNum = 420;BA.debugLine="Private Sub dlamp_LongClick";
 //BA.debugLineNum = 421;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 422;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 423;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 424;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 425;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
 //BA.debugLineNum = 426;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 428;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim regLayout, darkModeLayout As B4XView";
mostCurrent._reglayout = new anywheresoftware.b4a.objects.B4XViewWrapper();
mostCurrent._darkmodelayout = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Dim size As Int = 100%y";
_size = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA);
 //BA.debugLineNum = 17;BA.debugLine="Private hsv As HorizontalScrollView";
mostCurrent._hsv = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private computerGif As B4XGifView";
mostCurrent._computergif = new b4a.example.b4xgifview();
 //BA.debugLineNum = 19;BA.debugLine="Private dcomputerGif As B4XGifView";
mostCurrent._dcomputergif = new b4a.example.b4xgifview();
 //BA.debugLineNum = 20;BA.debugLine="Private curtain As B4XGifView";
mostCurrent._curtain = new b4a.example.b4xgifview();
 //BA.debugLineNum = 21;BA.debugLine="Private dCurtain As B4XGifView";
mostCurrent._dcurtain = new b4a.example.b4xgifview();
 //BA.debugLineNum = 22;BA.debugLine="Private notesOpen As B4XGifView";
mostCurrent._notesopen = new b4a.example.b4xgifview();
 //BA.debugLineNum = 23;BA.debugLine="Private noteBook As ImageView";
mostCurrent._notebook = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private dnotesOpen As B4XGifView";
mostCurrent._dnotesopen = new b4a.example.b4xgifview();
 //BA.debugLineNum = 25;BA.debugLine="Private clockBtn As Button";
mostCurrent._clockbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private clockLightBtn As Button";
mostCurrent._clocklightbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private infoPnl As B4XView";
mostCurrent._infopnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private infoTitleLbl As Label";
mostCurrent._infotitlelbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private infoDescLbl As Label";
mostCurrent._infodesclbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private infoPageLbl As Label";
mostCurrent._infopagelbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim infoPage As Int = 0";
_infopage = (int) (0);
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _helpbtn_click() throws Exception{
 //BA.debugLineNum = 237;BA.debugLine="Private Sub helpBtn_Click";
 //BA.debugLineNum = 238;BA.debugLine="StartActivity(helpActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._helpactivity.getObject()));
 //BA.debugLineNum = 239;BA.debugLine="End Sub";
return "";
}
public static String  _infopnlclose_click() throws Exception{
 //BA.debugLineNum = 241;BA.debugLine="Private Sub infoPnlClose_Click";
 //BA.debugLineNum = 242;BA.debugLine="infoPnl.Visible = False";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static void  _lamp_click() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 126;BA.debugLine="Starter.darkMode = True";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 127;BA.debugLine="kvsPref.Put(\"darkMode\", True)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.True));
 //BA.debugLineNum = 128;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 129;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
 //BA.debugLineNum = 130;BA.debugLine="darkModeLayout.Alpha = 0";
parent.mostCurrent._darkmodelayout.setAlpha((float) (0));
 //BA.debugLineNum = 131;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (1));
 //BA.debugLineNum = 132;BA.debugLine="regLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (0));
 //BA.debugLineNum = 133;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 134;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lamp_longclick() throws Exception{
 //BA.debugLineNum = 410;BA.debugLine="Private Sub lamp_LongClick";
 //BA.debugLineNum = 411;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 412;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 413;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 414;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 415;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
 //BA.debugLineNum = 416;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 418;BA.debugLine="End Sub";
return "";
}
public static String  _mp_click() throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Private Sub mP_Click";
 //BA.debugLineNum = 250;BA.debugLine="StartActivity(musicActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._musicactivity.getObject()));
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static String  _mp_longclick() throws Exception{
 //BA.debugLineNum = 370;BA.debugLine="Private Sub mP_LongClick";
 //BA.debugLineNum = 371;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 372;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 373;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 374;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 375;BA.debugLine="showInfoPage(4)";
_showinfopage((int) (4));
 //BA.debugLineNum = 376;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 378;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_click() throws Exception{
 //BA.debugLineNum = 233;BA.debugLine="Private Sub navBtn_Click";
 //BA.debugLineNum = 234;BA.debugLine="StartActivity(navActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._navactivity.getObject()));
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_longclick() throws Exception{
 //BA.debugLineNum = 430;BA.debugLine="Private Sub navBtn_LongClick";
 //BA.debugLineNum = 431;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 432;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 433;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 434;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 435;BA.debugLine="showInfoPage(9)";
_showinfopage((int) (9));
 //BA.debugLineNum = 436;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 438;BA.debugLine="End Sub";
return "";
}
public static String  _notebook_click() throws Exception{
 //BA.debugLineNum = 261;BA.debugLine="Private Sub noteBook_Click";
 //BA.debugLineNum = 262;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 264;BA.debugLine="CallSub(Me, \"NotesTransition1\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,mainactivity.getObject(),"NotesTransition1");
 break; }
case 1: {
 //BA.debugLineNum = 266;BA.debugLine="CallSub(Me, \"NotesTransition2\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,mainactivity.getObject(),"NotesTransition2");
 break; }
case 2: {
 //BA.debugLineNum = 268;BA.debugLine="CallSub(Me, \"NotesTransition3\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,mainactivity.getObject(),"NotesTransition3");
 break; }
}
;
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public static String  _notebook_longclick() throws Exception{
 //BA.debugLineNum = 380;BA.debugLine="Private Sub noteBook_LongClick";
 //BA.debugLineNum = 381;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 382;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 383;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 384;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 385;BA.debugLine="showInfoPage(5)";
_showinfopage((int) (5));
 //BA.debugLineNum = 386;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 388;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _notestransition1() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
 //BA.debugLineNum = 273;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"Openbook.GIF\")";
parent.mostCurrent._notesopen._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Openbook.GIF");
 //BA.debugLineNum = 274;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 275;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"Darkopenbook.G";
parent.mostCurrent._dnotesopen._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Darkopenbook.GIF");
 //BA.debugLineNum = 276;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 277;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 278;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 280;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 282;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
 //BA.debugLineNum = 283;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _notestransition2() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
 //BA.debugLineNum = 286;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes2.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes2.GIF");
 //BA.debugLineNum = 287;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 288;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes2.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes2.GIF");
 //BA.debugLineNum = 289;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 290;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 291;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 293;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 295;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
 //BA.debugLineNum = 296;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _notestransition3() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
 //BA.debugLineNum = 299;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes3.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes3.GIF");
 //BA.debugLineNum = 300;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 301;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes3.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes3.GIF");
 //BA.debugLineNum = 302;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 303;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 304;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 306;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 308;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
 //BA.debugLineNum = 309;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _plant_click() throws Exception{
 //BA.debugLineNum = 315;BA.debugLine="Private Sub plant_Click";
 //BA.debugLineNum = 316;BA.debugLine="StartActivity(themeActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._themeactivity.getObject()));
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return "";
}
public static String  _plant_longclick() throws Exception{
 //BA.debugLineNum = 400;BA.debugLine="Private Sub plant_LongClick";
 //BA.debugLineNum = 401;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 402;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 403;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 404;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 405;BA.debugLine="showInfoPage(7)";
_showinfopage((int) (7));
 //BA.debugLineNum = 406;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 408;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 8;BA.debugLine="Private timerClock As Timer";
_timerclock = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 9;BA.debugLine="Public kvs As KeyValueStore";
_kvs = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 10;BA.debugLine="Public kvsPref As KeyValueStore";
_kvspref = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 11;BA.debugLine="Public format24h As Boolean = False";
_format24h = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopage(int _page) throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Private Sub showInfoPage(page As Int)";
 //BA.debugLineNum = 188;BA.debugLine="infoPage = page";
_infopage = _page;
 //BA.debugLineNum = 189;BA.debugLine="Select page";
switch (_page) {
case 0: {
 //BA.debugLineNum = 191;BA.debugLine="infoTitleLbl.Text = \"Calendar\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Calendar"));
 //BA.debugLineNum = 192;BA.debugLine="infoDescLbl.Text = \"The calendar comes in three";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."));
 break; }
case 1: {
 //BA.debugLineNum = 194;BA.debugLine="infoTitleLbl.Text = \"Clock\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Clock"));
 //BA.debugLineNum = 195;BA.debugLine="infoDescLbl.Text = \"The clock keeps you on time";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."));
 break; }
case 2: {
 //BA.debugLineNum = 197;BA.debugLine="infoTitleLbl.Text = \"Corkboard\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Corkboard"));
 //BA.debugLineNum = 198;BA.debugLine="infoDescLbl.Text = \"The corkboard gives you a c";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."));
 break; }
case 3: {
 //BA.debugLineNum = 200;BA.debugLine="infoTitleLbl.Text = \"Flashcards\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Flashcards"));
 //BA.debugLineNum = 201;BA.debugLine="infoDescLbl.Text = \"The flashcard feature organ";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."));
 break; }
case 4: {
 //BA.debugLineNum = 203;BA.debugLine="infoTitleLbl.Text = \"Music Player\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Music Player"));
 //BA.debugLineNum = 204;BA.debugLine="infoDescLbl.Text = \"The music player plays the";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music.  "));
 break; }
case 5: {
 //BA.debugLineNum = 206;BA.debugLine="infoTitleLbl.Text = \"Notepad\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Notepad"));
 //BA.debugLineNum = 207;BA.debugLine="infoDescLbl.Text = \"The notepad keeps all your";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."));
 break; }
case 6: {
 //BA.debugLineNum = 209;BA.debugLine="infoTitleLbl.Text = \"To-do List\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("To-do List"));
 //BA.debugLineNum = 210;BA.debugLine="infoDescLbl.Text = \"The to-do list enables you";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."));
 break; }
case 7: {
 //BA.debugLineNum = 212;BA.debugLine="infoTitleLbl.Text = \"Themes\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Themes"));
 //BA.debugLineNum = 213;BA.debugLine="infoDescLbl.Text = \"Themes let you put your own";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
 break; }
case 8: {
 //BA.debugLineNum = 215;BA.debugLine="infoTitleLbl.Text = \"Lamp\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Lamp"));
 //BA.debugLineNum = 216;BA.debugLine="infoDescLbl.Text = \"The lamp gives you control";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
 break; }
case 9: {
 //BA.debugLineNum = 218;BA.debugLine="infoTitleLbl.Text = \"Navigation\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Navigation"));
 //BA.debugLineNum = 219;BA.debugLine="infoDescLbl.Text = \"Navigation is your home bas";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
 break; }
}
;
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopopup() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _closebtn = null;
 //BA.debugLineNum = 155;BA.debugLine="Private Sub showInfoPopup";
 //BA.debugLineNum = 157;BA.debugLine="infoPnl = xui.CreatePanel(\"infoPnl\")";
mostCurrent._infopnl = _xui.CreatePanel(processBA,"infoPnl");
 //BA.debugLineNum = 158;BA.debugLine="Activity.AddView(infoPnl, 75dip, 225dip, 300dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._infopnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (220)));
 //BA.debugLineNum = 159;BA.debugLine="infoPnl.SetColorAndBorder(xui.Color_White, 2dip,";
mostCurrent._infopnl.SetColorAndBorder(_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 161;BA.debugLine="Dim closeBtn As Button";
_closebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 162;BA.debugLine="closeBtn.Initialize(\"infoPnlClose\")";
_closebtn.Initialize(mostCurrent.activityBA,"infoPnlClose");
 //BA.debugLineNum = 163;BA.debugLine="closeBtn.Text = \"x\"";
_closebtn.setText(BA.ObjectToCharSequence("x"));
 //BA.debugLineNum = 164;BA.debugLine="closeBtn.TextSize = 6";
_closebtn.setTextSize((float) (6));
 //BA.debugLineNum = 165;BA.debugLine="infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28";
mostCurrent._infopnl.AddView((android.view.View)(_closebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (265)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)));
 //BA.debugLineNum = 167;BA.debugLine="infoTitleLbl.Initialize(\"\")";
mostCurrent._infotitlelbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 168;BA.debugLine="infoTitleLbl.TextSize = 16";
mostCurrent._infotitlelbl.setTextSize((float) (16));
 //BA.debugLineNum = 169;BA.debugLine="infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infotitlelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 170;BA.debugLine="infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infotitlelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (248)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 172;BA.debugLine="infoDescLbl.Initialize(\"\")";
mostCurrent._infodesclbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 173;BA.debugLine="infoDescLbl.TextSize = 10";
mostCurrent._infodesclbl.setTextSize((float) (10));
 //BA.debugLineNum = 174;BA.debugLine="infoDescLbl.Gravity = Gravity.TOP";
mostCurrent._infodesclbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
 //BA.debugLineNum = 175;BA.debugLine="infoDescLbl.SingleLine = False";
mostCurrent._infodesclbl.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 176;BA.debugLine="infoPnl.AddView(infoDescLbl, 12dip, 52dip, 276dip";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infodesclbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (276)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (140)));
 //BA.debugLineNum = 178;BA.debugLine="infoPageLbl.Initialize(\"\")";
mostCurrent._infopagelbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 179;BA.debugLine="infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infopagelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 180;BA.debugLine="infoPageLbl.TextSize = 10";
mostCurrent._infopagelbl.setTextSize((float) (10));
 //BA.debugLineNum = 181;BA.debugLine="infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infopagelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (184)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
 //BA.debugLineNum = 184;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
 //BA.debugLineNum = 185;BA.debugLine="End Sub";
return "";
}
public static String  _timerclock_tick() throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Sub timerClock_Tick";
 //BA.debugLineNum = 150;BA.debugLine="clockBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clockbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 151;BA.debugLine="clockLightBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clocklightbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_click() throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Private Sub todolistBtn_Click";
 //BA.debugLineNum = 246;BA.debugLine="StartActivity(todoActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._todoactivity.getObject()));
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_longclick() throws Exception{
 //BA.debugLineNum = 390;BA.debugLine="Private Sub todolistBtn_LongClick";
 //BA.debugLineNum = 391;BA.debugLine="showInfoPopup";
_showinfopopup();
 //BA.debugLineNum = 392;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
 //BA.debugLineNum = 393;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 394;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
 //BA.debugLineNum = 395;BA.debugLine="showInfoPage(6)";
_showinfopage((int) (6));
 //BA.debugLineNum = 396;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 398;BA.debugLine="End Sub";
return "";
}
}
