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
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.card_module _card_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.day_module _day_module = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
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
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="Activity.LoadLayout(\"Layouthsv\")";
parent.mostCurrent._activity.LoadLayout("Layouthsv",mostCurrent.activityBA);
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="If FirstTime Then";
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
RDebugUtils.currentLine=1572867;
 //BA.debugLineNum = 1572867;BA.debugLine="kvs = Starter.notesKvs";
parent._kvs = parent.mostCurrent._starter._noteskvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=1572868;
 //BA.debugLineNum = 1572868;BA.debugLine="kvsPref = Starter.prefKvs";
parent._kvspref = parent.mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=1572869;
 //BA.debugLineNum = 1572869;BA.debugLine="timerClock.Initialize(\"timerClock\", 1000)";
parent._timerclock.Initialize(processBA,"timerClock",(long) (1000));
RDebugUtils.currentLine=1572870;
 //BA.debugLineNum = 1572870;BA.debugLine="timerClock.Enabled = True";
parent._timerclock.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 4:
//C
this.state = 5;
;
RDebugUtils.currentLine=1572873;
 //BA.debugLineNum = 1572873;BA.debugLine="hsv.Panel.Width = size";
parent.mostCurrent._hsv.getPanel().setWidth(parent._size);
RDebugUtils.currentLine=1572874;
 //BA.debugLineNum = 1572874;BA.debugLine="hsv.Panel.Height = size";
parent.mostCurrent._hsv.getPanel().setHeight(parent._size);
RDebugUtils.currentLine=1572876;
 //BA.debugLineNum = 1572876;BA.debugLine="regLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._reglayout = parent._xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=1572877;
 //BA.debugLineNum = 1572877;BA.debugLine="darkModeLayout = xui.CreatePanel(\"\")";
parent.mostCurrent._darkmodelayout = parent._xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=1572879;
 //BA.debugLineNum = 1572879;BA.debugLine="hsv.Panel.AddView(regLayout, 0, 0, hsv.Panel.Widt";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._reglayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
RDebugUtils.currentLine=1572880;
 //BA.debugLineNum = 1572880;BA.debugLine="hsv.Panel.AddView(darkModeLayout, 0, 0, hsv.Panel";
parent.mostCurrent._hsv.getPanel().AddView((android.view.View)(parent.mostCurrent._darkmodelayout.getObject()),(int) (0),(int) (0),parent.mostCurrent._hsv.getPanel().getWidth(),parent.mostCurrent._hsv.getPanel().getHeight());
RDebugUtils.currentLine=1572882;
 //BA.debugLineNum = 1572882;BA.debugLine="Select Starter.themeNumber";
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
RDebugUtils.currentLine=1572884;
 //BA.debugLineNum = 1572884;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
parent.mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=1572885;
 //BA.debugLineNum = 1572885;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=1572886;
 //BA.debugLineNum = 1572886;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnComputer";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
RDebugUtils.currentLine=1572887;
 //BA.debugLineNum = 1572887;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtncom";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtncomputer.GIF");
 if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=1572889;
 //BA.debugLineNum = 1572889;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
parent.mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=1572890;
 //BA.debugLineNum = 1572890;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
RDebugUtils.currentLine=1572891;
 //BA.debugLineNum = 1572891;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.G";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
RDebugUtils.currentLine=1572892;
 //BA.debugLineNum = 1572892;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=1572894;
 //BA.debugLineNum = 1572894;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
parent.mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
RDebugUtils.currentLine=1572895;
 //BA.debugLineNum = 1572895;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
parent.mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
RDebugUtils.currentLine=1572896;
 //BA.debugLineNum = 1572896;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\")";
parent.mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
RDebugUtils.currentLine=1572897;
 //BA.debugLineNum = 1572897;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GIF";
parent.mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
RDebugUtils.currentLine=1572898;
 //BA.debugLineNum = 1572898;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
parent.mostCurrent._curtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
RDebugUtils.currentLine=1572899;
 //BA.debugLineNum = 1572899;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\")";
parent.mostCurrent._dcurtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 if (true) break;
;
RDebugUtils.currentLine=1572902;
 //BA.debugLineNum = 1572902;BA.debugLine="If Starter.darkMode Then";

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
RDebugUtils.currentLine=1572903;
 //BA.debugLineNum = 1572903;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1572904;
 //BA.debugLineNum = 1572904;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
RDebugUtils.currentLine=1572905;
 //BA.debugLineNum = 1572905;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=1572907;
 //BA.debugLineNum = 1572907;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1572908;
 //BA.debugLineNum = 1572908;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
 if (true) break;

case 17:
//C
this.state = -1;
;
RDebugUtils.currentLine=1572911;
 //BA.debugLineNum = 1572911;BA.debugLine="Sleep(50)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "activity_create"),(int) (50));
this.state = 18;
return;
case 18:
//C
this.state = -1;
;
RDebugUtils.currentLine=1572912;
 //BA.debugLineNum = 1572912;BA.debugLine="hsv.ScrollPosition = Max(0, (hsv.Panel.Width - 10";
parent.mostCurrent._hsv.setScrollPosition((int) (anywheresoftware.b4a.keywords.Common.Max(0,(parent.mostCurrent._hsv.getPanel().getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA))/(double)2)));
RDebugUtils.currentLine=1572914;
 //BA.debugLineNum = 1572914;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="mainactivity";
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="If format24h Then";
if (_format24h) { 
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="DateTime.TimeFormat = \"HH:mm\" ' 24-Hour Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 }else {
RDebugUtils.currentLine=1638405;
 //BA.debugLineNum = 1638405;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\" ' AM/PM Format";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 };
RDebugUtils.currentLine=1638408;
 //BA.debugLineNum = 1638408;BA.debugLine="If Starter.themeChanged Then";
if (mostCurrent._starter._themechanged /*boolean*/ ) { 
RDebugUtils.currentLine=1638409;
 //BA.debugLineNum = 1638409;BA.debugLine="regLayout.RemoveAllViews";
mostCurrent._reglayout.RemoveAllViews();
RDebugUtils.currentLine=1638410;
 //BA.debugLineNum = 1638410;BA.debugLine="darkModeLayout.RemoveAllViews";
mostCurrent._darkmodelayout.RemoveAllViews();
RDebugUtils.currentLine=1638412;
 //BA.debugLineNum = 1638412;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=1638414;
 //BA.debugLineNum = 1638414;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
mostCurrent._reglayout.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=1638415;
 //BA.debugLineNum = 1638415;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
mostCurrent._darkmodelayout.LoadLayout("Layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=1638416;
 //BA.debugLineNum = 1638416;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnCompute";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BtnComputer.GIF");
RDebugUtils.currentLine=1638417;
 //BA.debugLineNum = 1638417;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtnCo";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"darkbtnComputer.GIF");
 break; }
case 1: {
RDebugUtils.currentLine=1638419;
 //BA.debugLineNum = 1638419;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
mostCurrent._reglayout.LoadLayout("Layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=1638420;
 //BA.debugLineNum = 1638420;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
mostCurrent._darkmodelayout.LoadLayout("Layout4",mostCurrent.activityBA);
RDebugUtils.currentLine=1638421;
 //BA.debugLineNum = 1638421;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mikucomp2.GIF");
RDebugUtils.currentLine=1638422;
 //BA.debugLineNum = 1638422;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GI";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp2.GIF");
 break; }
case 2: {
RDebugUtils.currentLine=1638424;
 //BA.debugLineNum = 1638424;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
mostCurrent._reglayout.LoadLayout("Layout5",mostCurrent.activityBA);
RDebugUtils.currentLine=1638425;
 //BA.debugLineNum = 1638425;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
mostCurrent._darkmodelayout.LoadLayout("Layout6",mostCurrent.activityBA);
RDebugUtils.currentLine=1638426;
 //BA.debugLineNum = 1638426;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\"";
mostCurrent._computergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comp3.GIF");
RDebugUtils.currentLine=1638427;
 //BA.debugLineNum = 1638427;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GI";
mostCurrent._dcomputergif._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DComp3.GIF");
RDebugUtils.currentLine=1638428;
 //BA.debugLineNum = 1638428;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
mostCurrent._curtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Curtain.GIF");
RDebugUtils.currentLine=1638429;
 //BA.debugLineNum = 1638429;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\"";
mostCurrent._dcurtain._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DCurtain.GIF");
 break; }
}
;
RDebugUtils.currentLine=1638431;
 //BA.debugLineNum = 1638431;BA.debugLine="Starter.themeChanged = False";
mostCurrent._starter._themechanged /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=1638433;
 //BA.debugLineNum = 1638433;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bookie_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bookie_click", null));}
RDebugUtils.currentLine=2555904;
 //BA.debugLineNum = 2555904;BA.debugLine="Private Sub bookie_Click";
RDebugUtils.currentLine=2555905;
 //BA.debugLineNum = 2555905;BA.debugLine="StartActivity(FlashcardActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._flashcardactivity.getObject()));
RDebugUtils.currentLine=2555906;
 //BA.debugLineNum = 2555906;BA.debugLine="End Sub";
return "";
}
public static String  _bookie_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bookie_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bookie_longclick", null));}
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Private Sub bookie_LongClick";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3342339;
 //BA.debugLineNum = 3342339;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342340;
 //BA.debugLineNum = 3342340;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3342341;
 //BA.debugLineNum = 3342341;BA.debugLine="showInfoPage(3)";
_showinfopage((int) (3));
RDebugUtils.currentLine=3342342;
 //BA.debugLineNum = 3342342;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3342344;
 //BA.debugLineNum = 3342344;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopopup() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showinfopopup", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showinfopopup", null));}
anywheresoftware.b4a.objects.ButtonWrapper _closebtn = null;
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Private Sub showInfoPopup";
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="infoPnl = xui.CreatePanel(\"infoPnl\")";
mostCurrent._infopnl = _xui.CreatePanel(processBA,"infoPnl");
RDebugUtils.currentLine=1966083;
 //BA.debugLineNum = 1966083;BA.debugLine="Activity.AddView(infoPnl, 75dip, 225dip, 300dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._infopnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (220)));
RDebugUtils.currentLine=1966084;
 //BA.debugLineNum = 1966084;BA.debugLine="infoPnl.SetColorAndBorder(xui.Color_White, 2dip,";
mostCurrent._infopnl.SetColorAndBorder(_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=1966086;
 //BA.debugLineNum = 1966086;BA.debugLine="Dim closeBtn As Button";
_closebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=1966087;
 //BA.debugLineNum = 1966087;BA.debugLine="closeBtn.Initialize(\"infoPnlClose\")";
_closebtn.Initialize(mostCurrent.activityBA,"infoPnlClose");
RDebugUtils.currentLine=1966088;
 //BA.debugLineNum = 1966088;BA.debugLine="closeBtn.Text = \"x\"";
_closebtn.setText(BA.ObjectToCharSequence("x"));
RDebugUtils.currentLine=1966089;
 //BA.debugLineNum = 1966089;BA.debugLine="closeBtn.TextSize = 6";
_closebtn.setTextSize((float) (6));
RDebugUtils.currentLine=1966090;
 //BA.debugLineNum = 1966090;BA.debugLine="infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28";
mostCurrent._infopnl.AddView((android.view.View)(_closebtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (265)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)));
RDebugUtils.currentLine=1966092;
 //BA.debugLineNum = 1966092;BA.debugLine="infoTitleLbl.Initialize(\"\")";
mostCurrent._infotitlelbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1966093;
 //BA.debugLineNum = 1966093;BA.debugLine="infoTitleLbl.TextSize = 16";
mostCurrent._infotitlelbl.setTextSize((float) (16));
RDebugUtils.currentLine=1966094;
 //BA.debugLineNum = 1966094;BA.debugLine="infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infotitlelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=1966095;
 //BA.debugLineNum = 1966095;BA.debugLine="infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infotitlelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (248)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=1966097;
 //BA.debugLineNum = 1966097;BA.debugLine="infoDescLbl.Initialize(\"\")";
mostCurrent._infodesclbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1966098;
 //BA.debugLineNum = 1966098;BA.debugLine="infoDescLbl.TextSize = 10";
mostCurrent._infodesclbl.setTextSize((float) (10));
RDebugUtils.currentLine=1966099;
 //BA.debugLineNum = 1966099;BA.debugLine="infoDescLbl.Gravity = Gravity.TOP";
mostCurrent._infodesclbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
RDebugUtils.currentLine=1966100;
 //BA.debugLineNum = 1966100;BA.debugLine="infoDescLbl.SingleLine = False";
mostCurrent._infodesclbl.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966101;
 //BA.debugLineNum = 1966101;BA.debugLine="infoPnl.AddView(infoDescLbl, 12dip, 52dip, 276dip";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infodesclbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (276)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (140)));
RDebugUtils.currentLine=1966103;
 //BA.debugLineNum = 1966103;BA.debugLine="infoPageLbl.Initialize(\"\")";
mostCurrent._infopagelbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1966104;
 //BA.debugLineNum = 1966104;BA.debugLine="infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._infopagelbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
RDebugUtils.currentLine=1966105;
 //BA.debugLineNum = 1966105;BA.debugLine="infoPageLbl.TextSize = 10";
mostCurrent._infopagelbl.setTextSize((float) (10));
RDebugUtils.currentLine=1966106;
 //BA.debugLineNum = 1966106;BA.debugLine="infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110di";
mostCurrent._infopnl.AddView((android.view.View)(mostCurrent._infopagelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (184)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
RDebugUtils.currentLine=1966109;
 //BA.debugLineNum = 1966109;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
RDebugUtils.currentLine=1966110;
 //BA.debugLineNum = 1966110;BA.debugLine="End Sub";
return "";
}
public static String  _showinfopage(int _page) throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showinfopage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showinfopage", new Object[] {_page}));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Private Sub showInfoPage(page As Int)";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="infoPage = page";
_infopage = _page;
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="Select page";
switch (_page) {
case 0: {
RDebugUtils.currentLine=2031620;
 //BA.debugLineNum = 2031620;BA.debugLine="infoTitleLbl.Text = \"Calendar\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Calendar"));
RDebugUtils.currentLine=2031621;
 //BA.debugLineNum = 2031621;BA.debugLine="infoDescLbl.Text = \"The calendar comes in three";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."));
 break; }
case 1: {
RDebugUtils.currentLine=2031623;
 //BA.debugLineNum = 2031623;BA.debugLine="infoTitleLbl.Text = \"Clock\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Clock"));
RDebugUtils.currentLine=2031624;
 //BA.debugLineNum = 2031624;BA.debugLine="infoDescLbl.Text = \"The clock keeps you on time";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."));
 break; }
case 2: {
RDebugUtils.currentLine=2031626;
 //BA.debugLineNum = 2031626;BA.debugLine="infoTitleLbl.Text = \"Corkboard\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Corkboard"));
RDebugUtils.currentLine=2031627;
 //BA.debugLineNum = 2031627;BA.debugLine="infoDescLbl.Text = \"The corkboard gives you a c";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."));
 break; }
case 3: {
RDebugUtils.currentLine=2031629;
 //BA.debugLineNum = 2031629;BA.debugLine="infoTitleLbl.Text = \"Flashcards\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Flashcards"));
RDebugUtils.currentLine=2031630;
 //BA.debugLineNum = 2031630;BA.debugLine="infoDescLbl.Text = \"The flashcard feature organ";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."));
 break; }
case 4: {
RDebugUtils.currentLine=2031632;
 //BA.debugLineNum = 2031632;BA.debugLine="infoTitleLbl.Text = \"Music Player\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Music Player"));
RDebugUtils.currentLine=2031633;
 //BA.debugLineNum = 2031633;BA.debugLine="infoDescLbl.Text = \"The music player plays the";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music.  "));
 break; }
case 5: {
RDebugUtils.currentLine=2031635;
 //BA.debugLineNum = 2031635;BA.debugLine="infoTitleLbl.Text = \"Notepad\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Notepad"));
RDebugUtils.currentLine=2031636;
 //BA.debugLineNum = 2031636;BA.debugLine="infoDescLbl.Text = \"The notepad keeps all your";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."));
 break; }
case 6: {
RDebugUtils.currentLine=2031638;
 //BA.debugLineNum = 2031638;BA.debugLine="infoTitleLbl.Text = \"To-do List\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("To-do List"));
RDebugUtils.currentLine=2031639;
 //BA.debugLineNum = 2031639;BA.debugLine="infoDescLbl.Text = \"The to-do list enables you";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."));
 break; }
case 7: {
RDebugUtils.currentLine=2031641;
 //BA.debugLineNum = 2031641;BA.debugLine="infoTitleLbl.Text = \"Themes\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Themes"));
RDebugUtils.currentLine=2031642;
 //BA.debugLineNum = 2031642;BA.debugLine="infoDescLbl.Text = \"Themes let you put your own";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
 break; }
case 8: {
RDebugUtils.currentLine=2031644;
 //BA.debugLineNum = 2031644;BA.debugLine="infoTitleLbl.Text = \"Lamp\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Lamp"));
RDebugUtils.currentLine=2031645;
 //BA.debugLineNum = 2031645;BA.debugLine="infoDescLbl.Text = \"The lamp gives you control";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
 break; }
case 9: {
RDebugUtils.currentLine=2031647;
 //BA.debugLineNum = 2031647;BA.debugLine="infoTitleLbl.Text = \"Navigation\"";
mostCurrent._infotitlelbl.setText(BA.ObjectToCharSequence("Navigation"));
RDebugUtils.currentLine=2031648;
 //BA.debugLineNum = 2031648;BA.debugLine="infoDescLbl.Text = \"Navigation is your home bas";
mostCurrent._infodesclbl.setText(BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
 break; }
}
;
RDebugUtils.currentLine=2031652;
 //BA.debugLineNum = 2031652;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calendar_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "calendar_click", null));}
RDebugUtils.currentLine=2621440;
 //BA.debugLineNum = 2621440;BA.debugLine="Private Sub calendar_Click";
RDebugUtils.currentLine=2621441;
 //BA.debugLineNum = 2621441;BA.debugLine="StartActivity(CalendarActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._calendaractivity.getObject()));
RDebugUtils.currentLine=2621442;
 //BA.debugLineNum = 2621442;BA.debugLine="End Sub";
return "";
}
public static String  _calendar_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calendar_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "calendar_longclick", null));}
RDebugUtils.currentLine=3080192;
 //BA.debugLineNum = 3080192;BA.debugLine="Private Sub calendar_LongClick";
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
 //BA.debugLineNum = 3080197;BA.debugLine="showInfoPage(0)";
_showinfopage((int) (0));
RDebugUtils.currentLine=3080198;
 //BA.debugLineNum = 3080198;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3080200;
 //BA.debugLineNum = 3080200;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clockbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clockbtn_click", null));}
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Private Sub clockBtn_Click";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="End Sub";
return "";
}
public static String  _clockbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clockbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clockbtn_longclick", null));}
RDebugUtils.currentLine=3145728;
 //BA.debugLineNum = 3145728;BA.debugLine="Private Sub clockBtn_LongClick";
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
 //BA.debugLineNum = 3145733;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
RDebugUtils.currentLine=3145734;
 //BA.debugLineNum = 3145734;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3145736;
 //BA.debugLineNum = 3145736;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clocklightbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clocklightbtn_click", null));}
RDebugUtils.currentLine=2162688;
 //BA.debugLineNum = 2162688;BA.debugLine="Private Sub  clockLightBtn_Click";
RDebugUtils.currentLine=2162689;
 //BA.debugLineNum = 2162689;BA.debugLine="StartActivity(clockActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._clockactivity.getObject()));
RDebugUtils.currentLine=2162690;
 //BA.debugLineNum = 2162690;BA.debugLine="End Sub";
return "";
}
public static String  _clocklightbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clocklightbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clocklightbtn_longclick", null));}
RDebugUtils.currentLine=3211264;
 //BA.debugLineNum = 3211264;BA.debugLine="Private Sub clockLightBtn_LongClick";
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
 //BA.debugLineNum = 3211269;BA.debugLine="showInfoPage(1)";
_showinfopage((int) (1));
RDebugUtils.currentLine=3211270;
 //BA.debugLineNum = 3211270;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3211272;
 //BA.debugLineNum = 3211272;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "corkie_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "corkie_click", null));}
RDebugUtils.currentLine=2949120;
 //BA.debugLineNum = 2949120;BA.debugLine="Private Sub corkie_Click";
RDebugUtils.currentLine=2949121;
 //BA.debugLineNum = 2949121;BA.debugLine="StartActivity(corkActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._corkactivity.getObject()));
RDebugUtils.currentLine=2949122;
 //BA.debugLineNum = 2949122;BA.debugLine="End Sub";
return "";
}
public static String  _corkie_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "corkie_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "corkie_longclick", null));}
RDebugUtils.currentLine=3276800;
 //BA.debugLineNum = 3276800;BA.debugLine="Private Sub corkie_LongClick";
RDebugUtils.currentLine=3276801;
 //BA.debugLineNum = 3276801;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3276802;
 //BA.debugLineNum = 3276802;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3276803;
 //BA.debugLineNum = 3276803;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3276804;
 //BA.debugLineNum = 3276804;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3276805;
 //BA.debugLineNum = 3276805;BA.debugLine="showInfoPage(2)";
_showinfopage((int) (2));
RDebugUtils.currentLine=3276806;
 //BA.debugLineNum = 3276806;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3276808;
 //BA.debugLineNum = 3276808;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="Starter.darkMode = False";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="kvsPref.Put(\"darkMode\", False)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.False));
RDebugUtils.currentLine=1835011;
 //BA.debugLineNum = 1835011;BA.debugLine="regLayout.Visible = True";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1835012;
 //BA.debugLineNum = 1835012;BA.debugLine="regLayout.BringToFront";
parent.mostCurrent._reglayout.BringToFront();
RDebugUtils.currentLine=1835013;
 //BA.debugLineNum = 1835013;BA.debugLine="regLayout.Alpha = 0";
parent.mostCurrent._reglayout.setAlpha((float) (0));
RDebugUtils.currentLine=1835014;
 //BA.debugLineNum = 1835014;BA.debugLine="regLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (1));
RDebugUtils.currentLine=1835015;
 //BA.debugLineNum = 1835015;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (0));
RDebugUtils.currentLine=1835016;
 //BA.debugLineNum = 1835016;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "dlamp_click"),(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=1835017;
 //BA.debugLineNum = 1835017;BA.debugLine="darkModeLayout.Visible = False";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1835018;
 //BA.debugLineNum = 1835018;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _dlamp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dlamp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dlamp_longclick", null));}
RDebugUtils.currentLine=3735552;
 //BA.debugLineNum = 3735552;BA.debugLine="Private Sub dlamp_LongClick";
RDebugUtils.currentLine=3735553;
 //BA.debugLineNum = 3735553;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3735554;
 //BA.debugLineNum = 3735554;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3735555;
 //BA.debugLineNum = 3735555;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3735556;
 //BA.debugLineNum = 3735556;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3735557;
 //BA.debugLineNum = 3735557;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
RDebugUtils.currentLine=3735558;
 //BA.debugLineNum = 3735558;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3735560;
 //BA.debugLineNum = 3735560;BA.debugLine="End Sub";
return "";
}
public static String  _helpbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "helpbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "helpbtn_click", null));}
RDebugUtils.currentLine=2293760;
 //BA.debugLineNum = 2293760;BA.debugLine="Private Sub helpBtn_Click";
RDebugUtils.currentLine=2293761;
 //BA.debugLineNum = 2293761;BA.debugLine="StartActivity(helpActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._helpactivity.getObject()));
RDebugUtils.currentLine=2293762;
 //BA.debugLineNum = 2293762;BA.debugLine="End Sub";
return "";
}
public static String  _infopnlclose_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "infopnlclose_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "infopnlclose_click", null));}
RDebugUtils.currentLine=2359296;
 //BA.debugLineNum = 2359296;BA.debugLine="Private Sub infoPnlClose_Click";
RDebugUtils.currentLine=2359297;
 //BA.debugLineNum = 2359297;BA.debugLine="infoPnl.Visible = False";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2359298;
 //BA.debugLineNum = 2359298;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="Starter.darkMode = True";
parent.mostCurrent._starter._darkmode /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="kvsPref.Put(\"darkMode\", True)";
parent._kvspref._put("darkMode",(Object)(anywheresoftware.b4a.keywords.Common.True));
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="darkModeLayout.Visible = True";
parent.mostCurrent._darkmodelayout.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1769476;
 //BA.debugLineNum = 1769476;BA.debugLine="darkModeLayout.BringToFront";
parent.mostCurrent._darkmodelayout.BringToFront();
RDebugUtils.currentLine=1769477;
 //BA.debugLineNum = 1769477;BA.debugLine="darkModeLayout.Alpha = 0";
parent.mostCurrent._darkmodelayout.setAlpha((float) (0));
RDebugUtils.currentLine=1769478;
 //BA.debugLineNum = 1769478;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 1)";
parent.mostCurrent._darkmodelayout.SetAlphaAnimated((int) (250),(float) (1));
RDebugUtils.currentLine=1769479;
 //BA.debugLineNum = 1769479;BA.debugLine="regLayout.SetAlphaAnimated(250, 0)";
parent.mostCurrent._reglayout.SetAlphaAnimated((int) (250),(float) (0));
RDebugUtils.currentLine=1769480;
 //BA.debugLineNum = 1769480;BA.debugLine="Sleep(250)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "lamp_click"),(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=1769481;
 //BA.debugLineNum = 1769481;BA.debugLine="regLayout.Visible = False";
parent.mostCurrent._reglayout.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1769482;
 //BA.debugLineNum = 1769482;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lamp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lamp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lamp_longclick", null));}
RDebugUtils.currentLine=3670016;
 //BA.debugLineNum = 3670016;BA.debugLine="Private Sub lamp_LongClick";
RDebugUtils.currentLine=3670017;
 //BA.debugLineNum = 3670017;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3670018;
 //BA.debugLineNum = 3670018;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3670019;
 //BA.debugLineNum = 3670019;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3670020;
 //BA.debugLineNum = 3670020;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3670021;
 //BA.debugLineNum = 3670021;BA.debugLine="showInfoPage(8)";
_showinfopage((int) (8));
RDebugUtils.currentLine=3670022;
 //BA.debugLineNum = 3670022;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3670024;
 //BA.debugLineNum = 3670024;BA.debugLine="End Sub";
return "";
}
public static String  _mp_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mp_click", null));}
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Private Sub mP_Click";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="StartActivity(musicActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._musicactivity.getObject()));
RDebugUtils.currentLine=2490370;
 //BA.debugLineNum = 2490370;BA.debugLine="End Sub";
return "";
}
public static String  _mp_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "mp_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "mp_longclick", null));}
RDebugUtils.currentLine=3407872;
 //BA.debugLineNum = 3407872;BA.debugLine="Private Sub mP_LongClick";
RDebugUtils.currentLine=3407873;
 //BA.debugLineNum = 3407873;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3407874;
 //BA.debugLineNum = 3407874;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3407875;
 //BA.debugLineNum = 3407875;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3407876;
 //BA.debugLineNum = 3407876;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3407877;
 //BA.debugLineNum = 3407877;BA.debugLine="showInfoPage(4)";
_showinfopage((int) (4));
RDebugUtils.currentLine=3407878;
 //BA.debugLineNum = 3407878;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3407880;
 //BA.debugLineNum = 3407880;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navbtn_click", null));}
RDebugUtils.currentLine=2228224;
 //BA.debugLineNum = 2228224;BA.debugLine="Private Sub navBtn_Click";
RDebugUtils.currentLine=2228225;
 //BA.debugLineNum = 2228225;BA.debugLine="StartActivity(navActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._navactivity.getObject()));
RDebugUtils.currentLine=2228226;
 //BA.debugLineNum = 2228226;BA.debugLine="End Sub";
return "";
}
public static String  _navbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navbtn_longclick", null));}
RDebugUtils.currentLine=3801088;
 //BA.debugLineNum = 3801088;BA.debugLine="Private Sub navBtn_LongClick";
RDebugUtils.currentLine=3801089;
 //BA.debugLineNum = 3801089;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3801090;
 //BA.debugLineNum = 3801090;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3801091;
 //BA.debugLineNum = 3801091;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801092;
 //BA.debugLineNum = 3801092;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3801093;
 //BA.debugLineNum = 3801093;BA.debugLine="showInfoPage(9)";
_showinfopage((int) (9));
RDebugUtils.currentLine=3801094;
 //BA.debugLineNum = 3801094;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3801096;
 //BA.debugLineNum = 3801096;BA.debugLine="End Sub";
return "";
}
public static String  _notebook_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notebook_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notebook_click", null));}
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Private Sub noteBook_Click";
RDebugUtils.currentLine=2686977;
 //BA.debugLineNum = 2686977;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=2686979;
 //BA.debugLineNum = 2686979;BA.debugLine="CallSub(Me, \"NotesTransition1\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition1");
 break; }
case 1: {
RDebugUtils.currentLine=2686981;
 //BA.debugLineNum = 2686981;BA.debugLine="CallSub(Me, \"NotesTransition2\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition2");
 break; }
case 2: {
RDebugUtils.currentLine=2686983;
 //BA.debugLineNum = 2686983;BA.debugLine="CallSub(Me, \"NotesTransition3\")";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,mainactivity.getObject(),"NotesTransition3");
 break; }
}
;
RDebugUtils.currentLine=2686985;
 //BA.debugLineNum = 2686985;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2752513;
 //BA.debugLineNum = 2752513;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"Openbook.GIF\")";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Openbook.GIF");
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2752515;
 //BA.debugLineNum = 2752515;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"Darkopenbook.G";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Darkopenbook.GIF");
RDebugUtils.currentLine=2752516;
 //BA.debugLineNum = 2752516;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2752517;
 //BA.debugLineNum = 2752517;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2752518;
 //BA.debugLineNum = 2752518;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2752520;
 //BA.debugLineNum = 2752520;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition1"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=2752522;
 //BA.debugLineNum = 2752522;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=2752523;
 //BA.debugLineNum = 2752523;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2818049;
 //BA.debugLineNum = 2818049;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes2.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes2.GIF");
RDebugUtils.currentLine=2818050;
 //BA.debugLineNum = 2818050;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2818051;
 //BA.debugLineNum = 2818051;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes2.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes2.GIF");
RDebugUtils.currentLine=2818052;
 //BA.debugLineNum = 2818052;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2818053;
 //BA.debugLineNum = 2818053;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818054;
 //BA.debugLineNum = 2818054;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818056;
 //BA.debugLineNum = 2818056;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition2"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=2818058;
 //BA.debugLineNum = 2818058;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=2818059;
 //BA.debugLineNum = 2818059;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2883585;
 //BA.debugLineNum = 2883585;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes3.GIF\"";
parent.mostCurrent._notesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"OpenNotes3.GIF");
RDebugUtils.currentLine=2883586;
 //BA.debugLineNum = 2883586;BA.debugLine="notesOpen.mBase.Visible = True";
parent.mostCurrent._notesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2883587;
 //BA.debugLineNum = 2883587;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes3.GI";
parent.mostCurrent._dnotesopen._setgif /*String*/ (null,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DOpenNotes3.GIF");
RDebugUtils.currentLine=2883588;
 //BA.debugLineNum = 2883588;BA.debugLine="dnotesOpen.mBase.Visible = True";
parent.mostCurrent._dnotesopen._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2883589;
 //BA.debugLineNum = 2883589;BA.debugLine="noteBook.Enabled = False";
parent.mostCurrent._notebook.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2883590;
 //BA.debugLineNum = 2883590;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
parent.mostCurrent._notebook.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2883592;
 //BA.debugLineNum = 2883592;BA.debugLine="Sleep(1500)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "mainactivity", "notestransition3"),(int) (1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=2883594;
 //BA.debugLineNum = 2883594;BA.debugLine="StartActivity(noteActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._noteactivity.getObject()));
RDebugUtils.currentLine=2883595;
 //BA.debugLineNum = 2883595;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _notebook_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notebook_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notebook_longclick", null));}
RDebugUtils.currentLine=3473408;
 //BA.debugLineNum = 3473408;BA.debugLine="Private Sub noteBook_LongClick";
RDebugUtils.currentLine=3473409;
 //BA.debugLineNum = 3473409;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3473410;
 //BA.debugLineNum = 3473410;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3473411;
 //BA.debugLineNum = 3473411;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3473412;
 //BA.debugLineNum = 3473412;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3473413;
 //BA.debugLineNum = 3473413;BA.debugLine="showInfoPage(5)";
_showinfopage((int) (5));
RDebugUtils.currentLine=3473414;
 //BA.debugLineNum = 3473414;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3473416;
 //BA.debugLineNum = 3473416;BA.debugLine="End Sub";
return "";
}
public static String  _plant_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "plant_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "plant_click", null));}
RDebugUtils.currentLine=3014656;
 //BA.debugLineNum = 3014656;BA.debugLine="Private Sub plant_Click";
RDebugUtils.currentLine=3014657;
 //BA.debugLineNum = 3014657;BA.debugLine="StartActivity(themeActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._themeactivity.getObject()));
RDebugUtils.currentLine=3014658;
 //BA.debugLineNum = 3014658;BA.debugLine="End Sub";
return "";
}
public static String  _plant_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "plant_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "plant_longclick", null));}
RDebugUtils.currentLine=3604480;
 //BA.debugLineNum = 3604480;BA.debugLine="Private Sub plant_LongClick";
RDebugUtils.currentLine=3604481;
 //BA.debugLineNum = 3604481;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3604482;
 //BA.debugLineNum = 3604482;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3604483;
 //BA.debugLineNum = 3604483;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3604484;
 //BA.debugLineNum = 3604484;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3604485;
 //BA.debugLineNum = 3604485;BA.debugLine="showInfoPage(7)";
_showinfopage((int) (7));
RDebugUtils.currentLine=3604486;
 //BA.debugLineNum = 3604486;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3604488;
 //BA.debugLineNum = 3604488;BA.debugLine="End Sub";
return "";
}
public static String  _timerclock_tick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerclock_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerclock_tick", null));}
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Sub timerClock_Tick";
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="clockBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clockbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="clockLightBtn.Text = DateTime.Time(DateTime.Now)";
mostCurrent._clocklightbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_click() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "todolistbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "todolistbtn_click", null));}
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Private Sub todolistBtn_Click";
RDebugUtils.currentLine=2424833;
 //BA.debugLineNum = 2424833;BA.debugLine="StartActivity(todoActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._todoactivity.getObject()));
RDebugUtils.currentLine=2424834;
 //BA.debugLineNum = 2424834;BA.debugLine="End Sub";
return "";
}
public static String  _todolistbtn_longclick() throws Exception{
RDebugUtils.currentModule="mainactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "todolistbtn_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "todolistbtn_longclick", null));}
RDebugUtils.currentLine=3538944;
 //BA.debugLineNum = 3538944;BA.debugLine="Private Sub todolistBtn_LongClick";
RDebugUtils.currentLine=3538945;
 //BA.debugLineNum = 3538945;BA.debugLine="showInfoPopup";
_showinfopopup();
RDebugUtils.currentLine=3538946;
 //BA.debugLineNum = 3538946;BA.debugLine="If infoPnl <> Null Then";
if (mostCurrent._infopnl!= null) { 
RDebugUtils.currentLine=3538947;
 //BA.debugLineNum = 3538947;BA.debugLine="infoPnl.Visible = True";
mostCurrent._infopnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3538948;
 //BA.debugLineNum = 3538948;BA.debugLine="infoPnl.BringToFront";
mostCurrent._infopnl.BringToFront();
RDebugUtils.currentLine=3538949;
 //BA.debugLineNum = 3538949;BA.debugLine="showInfoPage(6)";
_showinfopage((int) (6));
RDebugUtils.currentLine=3538950;
 //BA.debugLineNum = 3538950;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3538952;
 //BA.debugLineNum = 3538952;BA.debugLine="End Sub";
return "";
}
}