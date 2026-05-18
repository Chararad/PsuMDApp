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

public class corkactivity extends Activity implements B4AActivity{
	public static corkactivity mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.corkactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (corkactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.corkactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.corkactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (corkactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (corkactivity) Resume **");
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
		return corkactivity.class;
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
            BA.LogInfo("** Activity (corkactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (corkactivity) Pause event (activity is not paused). **");
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
            corkactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (corkactivity) Resume **");
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
public static anywheresoftware.b4a.phone.Phone.ContentChooser _imgpicker = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.PanelWrapper _boardpnl = null;
public anywheresoftware.b4a.objects.PanelWrapper _addpnl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _canvabtn = null;
public anywheresoftware.b4a.objects.ButtonWrapper _imgbtn = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgview = null;
public anywheresoftware.b4a.objects.ButtonWrapper _stickybtn = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _notepnl = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _canvaspnl = null;
public static float _lastx = 0f;
public static float _lasty = 0f;
public anywheresoftware.b4a.objects.LabelWrapper _place1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place11 = null;
public anywheresoftware.b4a.objects.LabelWrapper _place12 = null;
public anywheresoftware.b4a.objects.LabelWrapper _deletelbl = null;
public static int _r = 0;
public static int _g = 0;
public static int _b = 0;
public static int _r2 = 0;
public static int _g2 = 0;
public static int _b2 = 0;
public anywheresoftware.b4a.objects.SpinnerWrapper _penspnr = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _sizespnr = null;
public static int _width = 0;
public static int _height = 0;
public b4a.example.dragdropview _ddn = null;
public b4a.example.dragdropview _ddi = null;
public b4a.example.dragdropview _ddc = null;
public static int _notecount = 0;
public static int _imgcount = 0;
public static int _canvascount = 0;
public static boolean _isloading = false;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _pixeltf = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.day_module _day_module = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
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
public ResumableSub_Activity_Create(b4a.example.corkactivity parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
b4a.example.corkactivity parent;
boolean _firsttime;
int _loadednotecount = 0;
int _i = 0;
String _nkey = "";
int _savedcolor = 0;
int _loadedimgcount = 0;
String _ikey = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
int _loadedcanvascount = 0;
String _ckey = "";
anywheresoftware.b4a.objects.PanelWrapper _f = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.B4XCanvas.B4XRect _canvasrect = null;
int step43;
int limit43;
int step56;
int limit56;
int step70;
int limit70;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 59;BA.debugLine="Select Starter.themeNumber";
if (true) break;

case 1:
//select
this.state = 26;
switch (BA.switchObjectToInt(parent.mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
this.state = 3;
if (true) break;
}
case 1: {
this.state = 11;
if (true) break;
}
case 2: {
this.state = 19;
if (true) break;
}
}
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 61;BA.debugLine="If Starter.darkMode = False Then";
if (true) break;

case 4:
//if
this.state = 9;
if (parent.mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 62;BA.debugLine="Activity.LoadLayout(\"corkboardLayout\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout",mostCurrent.activityBA);
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 64;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayoutDark",mostCurrent.activityBA);
 if (true) break;

case 9:
//C
this.state = 26;
;
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 67;BA.debugLine="If Starter.darkMode = False Then";
if (true) break;

case 12:
//if
this.state = 17;
if (parent.mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 17;
 //BA.debugLineNum = 68;BA.debugLine="Activity.LoadLayout(\"corkboardLayout2\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout2",mostCurrent.activityBA);
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 70;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark2\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayoutDark2",mostCurrent.activityBA);
 if (true) break;

case 17:
//C
this.state = 26;
;
 if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 73;BA.debugLine="If Starter.darkMode = False Then";
if (true) break;

case 20:
//if
this.state = 25;
if (parent.mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 22;
}else {
this.state = 24;
}if (true) break;

case 22:
//C
this.state = 25;
 //BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"corkboardLayout3\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout3",mostCurrent.activityBA);
 if (true) break;

case 24:
//C
this.state = 25;
 //BA.debugLineNum = 76;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark3\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayoutDark3",mostCurrent.activityBA);
 if (true) break;

case 25:
//C
this.state = 26;
;
 if (true) break;
;
 //BA.debugLineNum = 80;BA.debugLine="If Starter.darkMode = False Then";

case 26:
//if
this.state = 31;
if (parent.mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 28;
}else {
this.state = 30;
}if (true) break;

case 28:
//C
this.state = 31;
 //BA.debugLineNum = 81;BA.debugLine="penSpnr.DropdownBackgroundColor = Colors.DarkGra";
parent.mostCurrent._penspnr.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 82;BA.debugLine="penSpnr.DropdownTextColor = Colors.White";
parent.mostCurrent._penspnr.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 if (true) break;

case 30:
//C
this.state = 31;
 //BA.debugLineNum = 84;BA.debugLine="penSpnr.DropdownTextColor = Colors.Black";
parent.mostCurrent._penspnr.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 if (true) break;

case 31:
//C
this.state = 32;
;
 //BA.debugLineNum = 87;BA.debugLine="penSpnr.AddAll(Array As String(\"Black\", \"Blue\", \"";
parent.mostCurrent._penspnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Black","Blue","Green","Red","Yellow","Eraser"}));
 //BA.debugLineNum = 88;BA.debugLine="If FirstTime Then";
if (true) break;

case 32:
//if
this.state = 35;
if (_firsttime) { 
this.state = 34;
}if (true) break;

case 34:
//C
this.state = 35;
 //BA.debugLineNum = 89;BA.debugLine="imgPicker.Initialize(\"CC\")";
parent._imgpicker.Initialize("CC");
 if (true) break;

case 35:
//C
this.state = 36;
;
 //BA.debugLineNum = 91;BA.debugLine="penSpnr.Visible = False";
parent.mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 92;BA.debugLine="Width = 80dip";
parent._width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
 //BA.debugLineNum = 93;BA.debugLine="Height = 60dip";
parent._height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 //BA.debugLineNum = 94;BA.debugLine="ddn.Initialize(Me, \"NoteDrag\")";
parent.mostCurrent._ddn._initialize(processBA,corkactivity.getObject(),"NoteDrag");
 //BA.debugLineNum = 95;BA.debugLine="ddi.Initialize(Me, \"ImgDrag\")";
parent.mostCurrent._ddi._initialize(processBA,corkactivity.getObject(),"ImgDrag");
 //BA.debugLineNum = 96;BA.debugLine="ddc.Initialize(Me, \"CanvasDrag\")";
parent.mostCurrent._ddc._initialize(processBA,corkactivity.getObject(),"CanvasDrag");
 //BA.debugLineNum = 98;BA.debugLine="If Main.kvs.IsInitialized = False Then";
if (true) break;

case 36:
//if
this.state = 39;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 38;
}if (true) break;

case 38:
//C
this.state = 39;
 //BA.debugLineNum = 99;BA.debugLine="Main.kvs.Initialize(File.DirInternal, \"notes_dat";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"notes_data");
 if (true) break;

case 39:
//C
this.state = 40;
;
 //BA.debugLineNum = 102;BA.debugLine="isLoading = True";
parent._isloading = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 104;BA.debugLine="If Main.kvs.ContainsKey(\"note_count\") Then noteCo";
if (true) break;

case 40:
//if
this.state = 47;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._containskey("note_count")) { 
this.state = 42;
;}
else {
this.state = 44;
;}if (true) break;

case 42:
//C
this.state = 47;
parent._notecount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("note_count")));
if (true) break;

case 44:
//C
this.state = 47;
parent._notecount = (int) (0);
if (true) break;

case 47:
//C
this.state = 48;
;
 //BA.debugLineNum = 105;BA.debugLine="Dim loadedNoteCount As Int = noteCount";
_loadednotecount = parent._notecount;
 //BA.debugLineNum = 106;BA.debugLine="For i = 0 To loadedNoteCount - 1";
if (true) break;

case 48:
//for
this.state = 55;
step43 = 1;
limit43 = (int) (_loadednotecount-1);
_i = (int) (0) ;
this.state = 90;
if (true) break;

case 90:
//C
this.state = 55;
if ((step43 > 0 && _i <= limit43) || (step43 < 0 && _i >= limit43)) this.state = 50;
if (true) break;

case 91:
//C
this.state = 90;
_i = ((int)(0 + _i + step43)) ;
if (true) break;

case 50:
//C
this.state = 51;
 //BA.debugLineNum = 107;BA.debugLine="Dim nkey As String = \"note_\" & i";
_nkey = "note_"+BA.NumberToString(_i);
 //BA.debugLineNum = 108;BA.debugLine="If Main.kvs.ContainsKey(nkey & \"_text\") Then";
if (true) break;

case 51:
//if
this.state = 54;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._containskey(_nkey+"_text")) { 
this.state = 53;
}if (true) break;

case 53:
//C
this.state = 54;
 //BA.debugLineNum = 109;BA.debugLine="Dim savedColor As Int = Main.kvs.Get(nkey & \"_c";
_savedcolor = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_color")));
 //BA.debugLineNum = 110;BA.debugLine="R = Bit.And(Bit.ShiftRight(savedColor, 16), 0xF";
parent._r = anywheresoftware.b4a.keywords.Common.Bit.And(anywheresoftware.b4a.keywords.Common.Bit.ShiftRight(_savedcolor,(int) (16)),((int)0xff));
 //BA.debugLineNum = 111;BA.debugLine="G = Bit.And(Bit.ShiftRight(savedColor, 8), 0xFF";
parent._g = anywheresoftware.b4a.keywords.Common.Bit.And(anywheresoftware.b4a.keywords.Common.Bit.ShiftRight(_savedcolor,(int) (8)),((int)0xff));
 //BA.debugLineNum = 112;BA.debugLine="B = Bit.And(savedColor, 0xFF)";
parent._b = anywheresoftware.b4a.keywords.Common.Bit.And(_savedcolor,((int)0xff));
 //BA.debugLineNum = 113;BA.debugLine="noteCount = i + 1";
parent._notecount = (int) (_i+1);
 //BA.debugLineNum = 114;BA.debugLine="AddStickyNote(Main.kvs.Get(nkey & \"_text\"), Mai";
_addstickynote(BA.ObjectToString(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_text")),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_y"))));
 if (true) break;

case 54:
//C
this.state = 91;
;
 if (true) break;
if (true) break;
;
 //BA.debugLineNum = 118;BA.debugLine="If Main.kvs.ContainsKey(\"img_count\") Then imgCoun";

case 55:
//if
this.state = 62;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._containskey("img_count")) { 
this.state = 57;
;}
else {
this.state = 59;
;}if (true) break;

case 57:
//C
this.state = 62;
parent._imgcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("img_count")));
if (true) break;

case 59:
//C
this.state = 62;
parent._imgcount = (int) (0);
if (true) break;

case 62:
//C
this.state = 63;
;
 //BA.debugLineNum = 119;BA.debugLine="Dim loadedImgCount As Int = imgCount";
_loadedimgcount = parent._imgcount;
 //BA.debugLineNum = 120;BA.debugLine="For i = 0 To loadedImgCount - 1";
if (true) break;

case 63:
//for
this.state = 70;
step56 = 1;
limit56 = (int) (_loadedimgcount-1);
_i = (int) (0) ;
this.state = 92;
if (true) break;

case 92:
//C
this.state = 70;
if ((step56 > 0 && _i <= limit56) || (step56 < 0 && _i >= limit56)) this.state = 65;
if (true) break;

case 93:
//C
this.state = 92;
_i = ((int)(0 + _i + step56)) ;
if (true) break;

case 65:
//C
this.state = 66;
 //BA.debugLineNum = 121;BA.debugLine="Dim ikey As String = \"img_\" & i";
_ikey = "img_"+BA.NumberToString(_i);
 //BA.debugLineNum = 122;BA.debugLine="If Main.kvs.ContainsKey(ikey & \"_file\") Then";
if (true) break;

case 66:
//if
this.state = 69;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._containskey(_ikey+"_file")) { 
this.state = 68;
}if (true) break;

case 68:
//C
this.state = 69;
 //BA.debugLineNum = 123;BA.debugLine="Dim iv As ImageView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 124;BA.debugLine="iv.Initialize(\"ImgView\")";
_iv.Initialize(mostCurrent.activityBA,"ImgView");
 //BA.debugLineNum = 125;BA.debugLine="boardPnl.AddView(iv, Main.kvs.Get(ikey & \"_x\"),";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_iv.getObject()),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_y"))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 126;BA.debugLine="iv.Bitmap = LoadBitmapResize(File.DirInternal,";
_iv.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),BA.ObjectToString(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_file")),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 127;BA.debugLine="iv.Tag = ikey";
_iv.setTag((Object)(_ikey));
 //BA.debugLineNum = 128;BA.debugLine="ddi.AddDragView(iv, False)";
parent.mostCurrent._ddi._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject())),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 129;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).A";
parent.mostCurrent._ddi._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
 if (true) break;

case 69:
//C
this.state = 93;
;
 if (true) break;
if (true) break;
;
 //BA.debugLineNum = 133;BA.debugLine="If Main.kvs.ContainsKey(\"cvs_count\") Then canvasC";

case 70:
//if
this.state = 77;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._containskey("cvs_count")) { 
this.state = 72;
;}
else {
this.state = 74;
;}if (true) break;

case 72:
//C
this.state = 77;
parent._canvascount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("cvs_count")));
if (true) break;

case 74:
//C
this.state = 77;
parent._canvascount = (int) (0);
if (true) break;

case 77:
//C
this.state = 78;
;
 //BA.debugLineNum = 134;BA.debugLine="Dim loadedCanvasCount As Int = canvasCount";
_loadedcanvascount = parent._canvascount;
 //BA.debugLineNum = 135;BA.debugLine="For i = 0 To loadedCanvasCount - 1";
if (true) break;

case 78:
//for
this.state = 89;
step70 = 1;
limit70 = (int) (_loadedcanvascount-1);
_i = (int) (0) ;
this.state = 94;
if (true) break;

case 94:
//C
this.state = 89;
if ((step70 > 0 && _i <= limit70) || (step70 < 0 && _i >= limit70)) this.state = 80;
if (true) break;

case 95:
//C
this.state = 94;
_i = ((int)(0 + _i + step70)) ;
if (true) break;

case 80:
//C
this.state = 81;
 //BA.debugLineNum = 136;BA.debugLine="Dim ckey As String = \"cvs_\" & i";
_ckey = "cvs_"+BA.NumberToString(_i);
 //BA.debugLineNum = 137;BA.debugLine="If Main.kvs.ContainsKey(ckey & \"_x\") Then";
if (true) break;

case 81:
//if
this.state = 88;
if (parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._containskey(_ckey+"_x")) { 
this.state = 83;
}if (true) break;

case 83:
//C
this.state = 84;
 //BA.debugLineNum = 138;BA.debugLine="Width = Main.kvs.Get(ckey & \"_w\")";
parent._width = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_w")));
 //BA.debugLineNum = 139;BA.debugLine="Height = Main.kvs.Get(ckey & \"_h\")";
parent._height = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_h")));
 //BA.debugLineNum = 141;BA.debugLine="Dim f As Panel";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 142;BA.debugLine="f.Initialize(\"CanvasFrame\")";
_f.Initialize(mostCurrent.activityBA,"CanvasFrame");
 //BA.debugLineNum = 143;BA.debugLine="f.Color = Colors.Black";
_f.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 144;BA.debugLine="boardPnl.AddView(f, Main.kvs.Get(ckey & \"_x\"),";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_f.getObject()),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_y"))),(int) (parent._width+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (parent._height+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 145;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 146;BA.debugLine="p.Initialize(\"CanvasPanel\")";
_p.Initialize(mostCurrent.activityBA,"CanvasPanel");
 //BA.debugLineNum = 147;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 148;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
_f.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),parent._width,parent._height);
 //BA.debugLineNum = 149;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 96;
return;
case 96:
//C
this.state = 84;
;
 //BA.debugLineNum = 150;BA.debugLine="Dim cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
 //BA.debugLineNum = 151;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())));
 //BA.debugLineNum = 152;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray,";
_cvs.DrawRect(_cvs.getTargetRect(),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 153;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
 //BA.debugLineNum = 154;BA.debugLine="p.Tag = cvs";
_p.setTag((Object)(_cvs));
 //BA.debugLineNum = 155;BA.debugLine="ddc.AddDragView(f, False)";
parent.mostCurrent._ddc._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_f.getObject())),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 156;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).A";
parent.mostCurrent._ddc._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
 //BA.debugLineNum = 157;BA.debugLine="f.Tag = ckey";
_f.setTag((Object)(_ckey));
 //BA.debugLineNum = 159;BA.debugLine="If File.Exists(File.DirInternal, ckey & \".png\")";
if (true) break;

case 84:
//if
this.state = 87;
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_ckey+".png")) { 
this.state = 86;
}if (true) break;

case 86:
//C
this.state = 87;
 //BA.debugLineNum = 160;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 97;
return;
case 97:
//C
this.state = 87;
;
 //BA.debugLineNum = 161;BA.debugLine="Dim bmp As Bitmap = LoadBitmap(File.DirInterna";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_ckey+".png");
 //BA.debugLineNum = 162;BA.debugLine="Dim canvasRect As B4XRect = cvs.TargetRect";
_canvasrect = _cvs.getTargetRect();
 //BA.debugLineNum = 163;BA.debugLine="cvs.DrawBitmap(bmp, canvasRect)";
_cvs.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),_canvasrect);
 //BA.debugLineNum = 164;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
 if (true) break;

case 87:
//C
this.state = 88;
;
 if (true) break;

case 88:
//C
this.state = 95;
;
 if (true) break;
if (true) break;

case 89:
//C
this.state = -1;
;
 //BA.debugLineNum = 168;BA.debugLine="canvasCount = loadedCanvasCount";
parent._canvascount = _loadedcanvascount;
 //BA.debugLineNum = 169;BA.debugLine="Width = 80dip";
parent._width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
 //BA.debugLineNum = 170;BA.debugLine="Height = 60dip";
parent._height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 //BA.debugLineNum = 171;BA.debugLine="isLoading = False";
parent._isloading = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 175;BA.debugLine="If canvasCount > 0 Then";
if (_canvascount>0) { 
 //BA.debugLineNum = 176;BA.debugLine="penSpnr.Visible = True";
mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static void  _addcanvas(int _x,int _y) throws Exception{
ResumableSub_AddCanvas rsub = new ResumableSub_AddCanvas(null,_x,_y);
rsub.resume(processBA, null);
}
public static class ResumableSub_AddCanvas extends BA.ResumableSub {
public ResumableSub_AddCanvas(b4a.example.corkactivity parent,int _x,int _y) {
this.parent = parent;
this._x = _x;
this._y = _y;
}
b4a.example.corkactivity parent;
int _x;
int _y;
anywheresoftware.b4a.objects.PanelWrapper _f = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
String _key = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 255;BA.debugLine="Dim f As Panel";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 256;BA.debugLine="f.Initialize(\"CanvasFrame\")";
_f.Initialize(mostCurrent.activityBA,"CanvasFrame");
 //BA.debugLineNum = 257;BA.debugLine="f.Color = Colors.Black";
_f.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 258;BA.debugLine="boardPnl.AddView(f, x, y, Width + 20dip, Height +";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_f.getObject()),_x,_y,(int) (parent._width+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (parent._height+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 260;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 261;BA.debugLine="p.Initialize(\"CanvasPanel\")";
_p.Initialize(mostCurrent.activityBA,"CanvasPanel");
 //BA.debugLineNum = 262;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 263;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
_f.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),parent._width,parent._height);
 //BA.debugLineNum = 265;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 7;
return;
case 7:
//C
this.state = 1;
;
 //BA.debugLineNum = 267;BA.debugLine="Dim cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
 //BA.debugLineNum = 268;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())));
 //BA.debugLineNum = 269;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray, Fa";
_cvs.DrawRect(_cvs.getTargetRect(),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 270;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
 //BA.debugLineNum = 271;BA.debugLine="p.Tag = cvs";
_p.setTag((Object)(_cvs));
 //BA.debugLineNum = 273;BA.debugLine="ddc.AddDragView(f, False)";
parent.mostCurrent._ddc._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_f.getObject())),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 274;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).Add";
parent.mostCurrent._ddc._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
 //BA.debugLineNum = 275;BA.debugLine="If isLoading = False Then";
if (true) break;

case 1:
//if
this.state = 6;
if (parent._isloading==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 276;BA.debugLine="Dim key As String = \"cvs_\" & canvasCount";
_key = "cvs_"+BA.NumberToString(parent._canvascount);
 //BA.debugLineNum = 277;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_x));
 //BA.debugLineNum = 278;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_y));
 //BA.debugLineNum = 279;BA.debugLine="Main.kvs.Put(key & \"_w\", Width)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_w",(Object)(parent._width));
 //BA.debugLineNum = 280;BA.debugLine="Main.kvs.Put(key & \"_h\", Height)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_h",(Object)(parent._height));
 //BA.debugLineNum = 281;BA.debugLine="Main.kvs.Put(\"cvs_count\", canvasCount + 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("cvs_count",(Object)(parent._canvascount+1));
 //BA.debugLineNum = 282;BA.debugLine="f.Tag = key";
_f.setTag((Object)(_key));
 //BA.debugLineNum = 283;BA.debugLine="canvasCount = canvasCount + 1";
parent._canvascount = (int) (parent._canvascount+1);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 285;BA.debugLine="f.Tag = \"cvs_\" & (canvasCount - 1)";
_f.setTag((Object)("cvs_"+BA.NumberToString((parent._canvascount-1))));
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _addcbtn_click() throws Exception{
 //BA.debugLineNum = 458;BA.debugLine="Private Sub addcBtn_Click";
 //BA.debugLineNum = 459;BA.debugLine="AddCanvas(150dip, 500dip)";
_addcanvas(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)));
 //BA.debugLineNum = 460;BA.debugLine="canvasPnl.Visible = False";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 461;BA.debugLine="canvaBtn.Enabled = True";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 462;BA.debugLine="stickyBtn.Enabled = True";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 463;BA.debugLine="imgBtn.Enabled = True";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
return "";
}
public static String  _addnbtn_click() throws Exception{
 //BA.debugLineNum = 447;BA.debugLine="Private Sub addnBtn_Click";
 //BA.debugLineNum = 448;BA.debugLine="AddStickyNote(\"\", 150dip, 500dip)";
_addstickynote("",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)));
 //BA.debugLineNum = 449;BA.debugLine="notePnl.Visible = False";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 450;BA.debugLine="R = 255";
_r = (int) (255);
 //BA.debugLineNum = 451;BA.debugLine="G = 105";
_g = (int) (105);
 //BA.debugLineNum = 452;BA.debugLine="B = 97";
_b = (int) (97);
 //BA.debugLineNum = 453;BA.debugLine="stickyBtn.Enabled = True";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 454;BA.debugLine="canvaBtn.Enabled = True";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 455;BA.debugLine="imgBtn.Enabled = True";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 456;BA.debugLine="End Sub";
return "";
}
public static String  _addstickynote(String _text,int _x,int _y) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.EditTextWrapper _txt = null;
String _key = "";
 //BA.debugLineNum = 186;BA.debugLine="Sub AddStickyNote(Text As String, x As Int, y As I";
 //BA.debugLineNum = 187;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 188;BA.debugLine="p.Initialize(\"NotePanel\")";
_p.Initialize(mostCurrent.activityBA,"NotePanel");
 //BA.debugLineNum = 189;BA.debugLine="p.Color = Colors.RGB(R, G, B)";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB(_r,_g,_b));
 //BA.debugLineNum = 191;BA.debugLine="Dim txt As EditText";
_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 192;BA.debugLine="txt.Initialize(\"NoteText\")";
_txt.Initialize(mostCurrent.activityBA,"NoteText");
 //BA.debugLineNum = 193;BA.debugLine="txt.Tag = p";
_txt.setTag((Object)(_p.getObject()));
 //BA.debugLineNum = 194;BA.debugLine="txt.Text = Text";
_txt.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 195;BA.debugLine="txt.TextSize = 12";
_txt.setTextSize((float) (12));
 //BA.debugLineNum = 196;BA.debugLine="txt.Background = Null";
_txt.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 197;BA.debugLine="txt.TextColor = Colors.Black";
_txt.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 198;BA.debugLine="txt.Gravity = Gravity.TOP";
_txt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
 //BA.debugLineNum = 200;BA.debugLine="p.AddView(txt, 5dip, 15dip, 90dip, 70dip)";
_p.AddView((android.view.View)(_txt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
 //BA.debugLineNum = 202;BA.debugLine="boardPnl.AddView(p, x, y, 100dip, 100dip)";
mostCurrent._boardpnl.AddView((android.view.View)(_p.getObject()),_x,_y,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 204;BA.debugLine="ddn.AddDragView(p, False)";
mostCurrent._ddn._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 205;BA.debugLine="ddn.AddPlaceView(place1).AddPlaceView(place2).Add";
mostCurrent._ddn._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._deletelbl.getObject())));
 //BA.debugLineNum = 207;BA.debugLine="If isLoading = False Then";
if (_isloading==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 208;BA.debugLine="Dim key As String = \"note_\" & noteCount";
_key = "note_"+BA.NumberToString(_notecount);
 //BA.debugLineNum = 209;BA.debugLine="Main.kvs.Put(key & \"_text\", Text)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_text",(Object)(_text));
 //BA.debugLineNum = 210;BA.debugLine="Main.kvs.Put(key & \"_color\", Colors.RGB(R, G, B)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_color",(Object)(anywheresoftware.b4a.keywords.Common.Colors.RGB(_r,_g,_b)));
 //BA.debugLineNum = 211;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_x));
 //BA.debugLineNum = 212;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_y));
 //BA.debugLineNum = 213;BA.debugLine="Main.kvs.Put(\"note_count\", noteCount + 1)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("note_count",(Object)(_notecount+1));
 //BA.debugLineNum = 214;BA.debugLine="p.Tag = key";
_p.setTag((Object)(_key));
 //BA.debugLineNum = 215;BA.debugLine="noteCount = noteCount + 1";
_notecount = (int) (_notecount+1);
 }else {
 //BA.debugLineNum = 217;BA.debugLine="p.Tag = \"note_\" & (noteCount - 1)";
_p.setTag((Object)("note_"+BA.NumberToString((_notecount-1))));
 };
 //BA.debugLineNum = 219;BA.debugLine="Log(\"deleteLbl initialized: \" & (deleteLbl.IsInit";
anywheresoftware.b4a.keywords.Common.LogImpl("611075617","deleteLbl initialized: "+BA.ObjectToString((mostCurrent._deletelbl.IsInitialized())),0);
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _canvabtn_click() throws Exception{
 //BA.debugLineNum = 480;BA.debugLine="Private Sub canvaBtn_Click";
 //BA.debugLineNum = 481;BA.debugLine="canvasWindow(250dip, 180dip)";
_canvaswindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
 //BA.debugLineNum = 482;BA.debugLine="canvasPnl.Visible = True";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 483;BA.debugLine="penSpnr.Visible = True";
mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 484;BA.debugLine="stickyBtn.Enabled = False";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 485;BA.debugLine="canvaBtn.Enabled = False";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 486;BA.debugLine="imgBtn.Enabled = False";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 487;BA.debugLine="End Sub";
return "";
}
public static void  _canvasdrag_placedview(anywheresoftware.b4a.objects.ConcreteViewWrapper _cdragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _cplaceview) throws Exception{
ResumableSub_CanvasDrag_PlacedView rsub = new ResumableSub_CanvasDrag_PlacedView(null,_cdragview,_cplaceview);
rsub.resume(processBA, null);
}
public static class ResumableSub_CanvasDrag_PlacedView extends BA.ResumableSub {
public ResumableSub_CanvasDrag_PlacedView(b4a.example.corkactivity parent,anywheresoftware.b4a.objects.ConcreteViewWrapper _cdragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _cplaceview) {
this.parent = parent;
this._cdragview = _cdragview;
this._cplaceview = _cplaceview;
}
b4a.example.corkactivity parent;
anywheresoftware.b4a.objects.ConcreteViewWrapper _cdragview;
anywheresoftware.b4a.objects.ConcreteViewWrapper _cplaceview;
int _res = 0;
anywheresoftware.b4a.objects.PanelWrapper _f = null;
String _key = "";
int _newcount = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 611;BA.debugLine="If cPlaceView.Tag = \"delete\" Then";
if (true) break;

case 1:
//if
this.state = 14;
if ((_cplaceview.getTag()).equals((Object)("delete"))) { 
this.state = 3;
}else {
this.state = 13;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 612;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete ca";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete canvas?"),BA.ObjectToCharSequence("Delete Canvas"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 613;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 15;
return;
case 15:
//C
this.state = 4;
_res = (Integer) result[0];
;
 //BA.debugLineNum = 614;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 615;BA.debugLine="Dim f As Panel = cDragView";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_cdragview.getObject()));
 //BA.debugLineNum = 616;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
 //BA.debugLineNum = 617;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png");
 //BA.debugLineNum = 618;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
 //BA.debugLineNum = 619;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
 //BA.debugLineNum = 620;BA.debugLine="Main.kvs.Remove(key & \"_w\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_w");
 //BA.debugLineNum = 621;BA.debugLine="Main.kvs.Remove(key & \"_h\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_h");
 //BA.debugLineNum = 622;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"cvs_count\")";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("cvs_count")));
 //BA.debugLineNum = 623;BA.debugLine="Main.kvs.Put(\"cvs_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("cvs_count",(Object)(_newcount-1));
 //BA.debugLineNum = 624;BA.debugLine="canvasCount = canvasCount - 1";
parent._canvascount = (int) (parent._canvascount-1);
 //BA.debugLineNum = 625;BA.debugLine="f.Visible = False";
_f.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 626;BA.debugLine="ToastMessageShow(\"Canvas Deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Canvas Deleted"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 627;BA.debugLine="If canvasCount = 0 Then";
if (true) break;

case 7:
//if
this.state = 10;
if (parent._canvascount==0) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 628;BA.debugLine="penSpnr.Visible = False";
parent.mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 632;BA.debugLine="Dim f As Panel = cDragView";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_cdragview.getObject()));
 //BA.debugLineNum = 633;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
 //BA.debugLineNum = 634;BA.debugLine="Main.kvs.Put(key & \"_x\", f.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_f.getLeft()));
 //BA.debugLineNum = 635;BA.debugLine="Main.kvs.Put(key & \"_y\", f.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_f.getTop()));
 if (true) break;

case 14:
//C
this.state = -1;
;
 //BA.debugLineNum = 637;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _res) throws Exception{
}
public static String  _canvaspanel_touch(int _action,float _x,float _y) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
anywheresoftware.b4a.objects.PanelWrapper _f = null;
String _key = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 289;BA.debugLine="Sub CanvasPanel_Touch (Action As Int, X As Float,";
 //BA.debugLineNum = 290;BA.debugLine="Dim p As Panel = Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 291;BA.debugLine="Dim cvs As B4XCanvas = p.Tag";
_cvs = (anywheresoftware.b4a.objects.B4XCanvas)(_p.getTag());
 //BA.debugLineNum = 292;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE)) {
case 0: {
 //BA.debugLineNum = 294;BA.debugLine="LastX = X";
_lastx = _x;
 //BA.debugLineNum = 295;BA.debugLine="LastY = Y";
_lasty = _y;
 break; }
case 1: {
 //BA.debugLineNum = 297;BA.debugLine="cvs.DrawLine(LastX, LastY, X, Y, Colors.RGB(R2,";
_cvs.DrawLine(_lastx,_lasty,_x,_y,anywheresoftware.b4a.keywords.Common.Colors.RGB(_r2,_g2,_b2),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))));
 //BA.debugLineNum = 298;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
 //BA.debugLineNum = 299;BA.debugLine="LastX = X";
_lastx = _x;
 //BA.debugLineNum = 300;BA.debugLine="LastY = Y";
_lasty = _y;
 //BA.debugLineNum = 301;BA.debugLine="Dim f As Panel = p.Parent";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getParent()));
 //BA.debugLineNum = 302;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
 //BA.debugLineNum = 303;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 304;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \"";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 305;BA.debugLine="cvs.CreateBitmap.WriteToStream(out, 100, \"PNG\")";
_cvs.CreateBitmap().WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 306;BA.debugLine="out.Close";
_out.Close();
 break; }
}
;
 //BA.debugLineNum = 308;BA.debugLine="End Sub";
return "";
}
public static String  _canvaswindow(int _pw,int _ph) throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _addcbtn = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 379;BA.debugLine="Private Sub canvasWindow(pW As Int, pH As Int)";
 //BA.debugLineNum = 380;BA.debugLine="canvasPnl = xui.CreatePanel(\"canvasPanel\")";
mostCurrent._canvaspnl = _xui.CreatePanel(processBA,"canvasPanel");
 //BA.debugLineNum = 381;BA.debugLine="Activity.AddView(canvasPnl, 100dip, 225dip, pW, p";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._canvaspnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),_pw,_ph);
 //BA.debugLineNum = 385;BA.debugLine="sizeSpnr.Initialize(\"sizeSpnr\")";
mostCurrent._sizespnr.Initialize(mostCurrent.activityBA,"sizeSpnr");
 //BA.debugLineNum = 386;BA.debugLine="sizeSpnr.AddAll(Array As String(\"1x1\", \"2x1\", \"1x";
mostCurrent._sizespnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"1x1","2x1","1x2","2x2","3x2","2x3"}));
 //BA.debugLineNum = 387;BA.debugLine="canvasPnl.AddView(sizeSpnr, 10dip, 10dip, pW - 20";
mostCurrent._canvaspnl.AddView((android.view.View)(mostCurrent._sizespnr.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 389;BA.debugLine="Dim addcBtn As Button";
_addcbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 390;BA.debugLine="addcBtn.Initialize(\"addcBtn\")";
_addcbtn.Initialize(mostCurrent.activityBA,"addcBtn");
 //BA.debugLineNum = 391;BA.debugLine="addcBtn.Text = \"Add Canvas\"";
_addcbtn.setText(BA.ObjectToCharSequence("Add Canvas"));
 //BA.debugLineNum = 392;BA.debugLine="canvasPnl.AddView(addcBtn, 10dip, 60dip, (pW / 2)";
mostCurrent._canvaspnl.AddView((android.view.View)(_addcbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),(int) ((_pw/(double)2)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 394;BA.debugLine="canvasPnl.Enabled = False";
mostCurrent._canvaspnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 395;BA.debugLine="canvasPnl.Visible = False";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 397;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 398;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 399;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 401;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
 //BA.debugLineNum = 402;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 403;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(187,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (187),(int) (213),(int) (218)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 404;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
 //BA.debugLineNum = 405;BA.debugLine="addcBtn.Background = cd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 406;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 408;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 409;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(227,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (227),(int) (193),(int) (159)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 410;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
 //BA.debugLineNum = 411;BA.debugLine="addcBtn.Background = cd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 412;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
 //BA.debugLineNum = 415;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
 //BA.debugLineNum = 416;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 417;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(27,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (27),(int) (98),(int) (161)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (16),(int) (26),(int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 418;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"dark";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dark Skeumorphic Button blue.png").getObject()));
 //BA.debugLineNum = 419;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 420;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 422;BA.debugLine="canvasPnl.Color = xui.Color_RGB(255, 255, 255)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 423;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(51,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (51),(int) (187),(int) (152)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 424;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Button 03.png").getObject()));
 //BA.debugLineNum = 425;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 426;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
 //BA.debugLineNum = 429;BA.debugLine="addcBtn.Typeface = pixeltf";
_addcbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
 //BA.debugLineNum = 430;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
 //BA.debugLineNum = 431;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 432;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(37,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (37),(int) (57),(int) (94)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 433;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
 //BA.debugLineNum = 434;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 435;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 437;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 438;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(234,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (234),(int) (212),(int) (179)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (79),(int) (46),(int) (46)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 439;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
 //BA.debugLineNum = 440;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 441;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
 //BA.debugLineNum = 445;BA.debugLine="End Sub";
return "";
}
public static String  _cc_result(boolean _success,String _dir,String _filename) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
String _key = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 229;BA.debugLine="Sub CC_Result (Success As Boolean, Dir As String,";
 //BA.debugLineNum = 230;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 231;BA.debugLine="imgView.Initialize(\"ImgView\")";
mostCurrent._imgview.Initialize(mostCurrent.activityBA,"ImgView");
 //BA.debugLineNum = 232;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 233;BA.debugLine="bmp = LoadBitmapResize(Dir, FileName, 100dip, 10";
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmapResize(_dir,_filename,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 234;BA.debugLine="imgView.Bitmap = bmp";
mostCurrent._imgview.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 235;BA.debugLine="boardPnl.AddView(imgView, 150dip, 500dip, 100dip";
mostCurrent._boardpnl.AddView((android.view.View)(mostCurrent._imgview.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 236;BA.debugLine="ddi.AddDragView(imgView, False)";
mostCurrent._ddi._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._imgview.getObject())),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 237;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).Ad";
mostCurrent._ddi._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._deletelbl.getObject())));
 //BA.debugLineNum = 238;BA.debugLine="Dim key As String = \"img_\" & imgCount";
_key = "img_"+BA.NumberToString(_imgcount);
 //BA.debugLineNum = 239;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 240;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \".";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 241;BA.debugLine="bmp.WriteToStream(out, 100, \"PNG\")";
_bmp.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 242;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 243;BA.debugLine="Main.kvs.Put(key & \"_file\", key & \".png\")";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_file",(Object)(_key+".png"));
 //BA.debugLineNum = 244;BA.debugLine="Main.kvs.Put(key & \"_x\", 150dip)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150))));
 //BA.debugLineNum = 245;BA.debugLine="Main.kvs.Put(key & \"_y\", 500dip)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500))));
 //BA.debugLineNum = 246;BA.debugLine="Main.kvs.Put(\"img_count\", imgCount + 1)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("img_count",(Object)(_imgcount+1));
 //BA.debugLineNum = 247;BA.debugLine="imgView.Tag = key";
mostCurrent._imgview.setTag((Object)(_key));
 //BA.debugLineNum = 248;BA.debugLine="imgCount = imgCount + 1";
_imgcount = (int) (_imgcount+1);
 }else {
 //BA.debugLineNum = 250;BA.debugLine="ToastMessageShow(\"No image selected\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No image selected"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _colorsspnr_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 514;BA.debugLine="Private Sub colorsSpnr_ItemClick (Position As Int,";
 //BA.debugLineNum = 515;BA.debugLine="Select Position";
switch (_position) {
case 0: {
 //BA.debugLineNum = 517;BA.debugLine="R = 255";
_r = (int) (255);
 //BA.debugLineNum = 518;BA.debugLine="G = 105";
_g = (int) (105);
 //BA.debugLineNum = 519;BA.debugLine="B = 97";
_b = (int) (97);
 break; }
case 1: {
 //BA.debugLineNum = 521;BA.debugLine="R = 155";
_r = (int) (155);
 //BA.debugLineNum = 522;BA.debugLine="G = 190";
_g = (int) (190);
 //BA.debugLineNum = 523;BA.debugLine="B = 237";
_b = (int) (237);
 break; }
case 2: {
 //BA.debugLineNum = 525;BA.debugLine="R = 248";
_r = (int) (248);
 //BA.debugLineNum = 526;BA.debugLine="G = 241";
_g = (int) (241);
 //BA.debugLineNum = 527;BA.debugLine="B = 174";
_b = (int) (174);
 break; }
}
;
 //BA.debugLineNum = 529;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private boardPnl As Panel";
mostCurrent._boardpnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private addPnl As Panel";
mostCurrent._addpnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private canvaBtn As Button";
mostCurrent._canvabtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private imgBtn As Button";
mostCurrent._imgbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private imgView As ImageView";
mostCurrent._imgview = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private stickyBtn As Button";
mostCurrent._stickybtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private notePnl As B4XView";
mostCurrent._notepnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private canvasPnl As B4XView";
mostCurrent._canvaspnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private LastX, LastY As Float";
_lastx = 0f;
_lasty = 0f;
 //BA.debugLineNum = 25;BA.debugLine="Private place1 As Label";
mostCurrent._place1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private place2 As Label";
mostCurrent._place2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private place3 As Label";
mostCurrent._place3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private place4 As Label";
mostCurrent._place4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private place5 As Label";
mostCurrent._place5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private place6 As Label";
mostCurrent._place6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private place7 As Label";
mostCurrent._place7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private place8 As Label";
mostCurrent._place8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private place9 As Label";
mostCurrent._place9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private place10 As Label";
mostCurrent._place10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private place11 As Label";
mostCurrent._place11 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private place12 As Label";
mostCurrent._place12 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private deleteLbl As Label";
mostCurrent._deletelbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private R = 255, G = 105, B = 97 As Int";
_r = (int) (255);
_g = (int) (105);
_b = (int) (97);
 //BA.debugLineNum = 39;BA.debugLine="Private R2, G2, B2 As Int";
_r2 = 0;
_g2 = 0;
_b2 = 0;
 //BA.debugLineNum = 40;BA.debugLine="Private penSpnr As Spinner";
mostCurrent._penspnr = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private sizeSpnr As Spinner";
mostCurrent._sizespnr = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private Width As Int";
_width = 0;
 //BA.debugLineNum = 43;BA.debugLine="Private Height As Int";
_height = 0;
 //BA.debugLineNum = 45;BA.debugLine="Dim ddn As DragDropView";
mostCurrent._ddn = new b4a.example.dragdropview();
 //BA.debugLineNum = 46;BA.debugLine="Dim ddi As DragDropView";
mostCurrent._ddi = new b4a.example.dragdropview();
 //BA.debugLineNum = 47;BA.debugLine="Dim ddc As DragDropView";
mostCurrent._ddc = new b4a.example.dragdropview();
 //BA.debugLineNum = 49;BA.debugLine="Private noteCount As Int = 0";
_notecount = (int) (0);
 //BA.debugLineNum = 50;BA.debugLine="Private imgCount As Int = 0";
_imgcount = (int) (0);
 //BA.debugLineNum = 51;BA.debugLine="Private canvasCount As Int = 0";
_canvascount = (int) (0);
 //BA.debugLineNum = 52;BA.debugLine="Private isLoading As Boolean = False";
_isloading = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 54;BA.debugLine="Dim pixeltf As Typeface";
mostCurrent._pixeltf = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 55;BA.debugLine="pixeltf = Typeface.LoadFromAssets(\"minecraft.ttf\"";
mostCurrent._pixeltf = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("minecraft.ttf")));
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _imgbtn_click() throws Exception{
 //BA.debugLineNum = 476;BA.debugLine="Private Sub imgBtn_Click";
 //BA.debugLineNum = 477;BA.debugLine="imgPicker.Show(\"image/*\", \"Select a Photo\")";
_imgpicker.Show(processBA,"image/*","Select a Photo");
 //BA.debugLineNum = 478;BA.debugLine="End Sub";
return "";
}
public static void  _imgdrag_placedview(anywheresoftware.b4a.objects.ConcreteViewWrapper _idragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _iplaceview) throws Exception{
ResumableSub_ImgDrag_PlacedView rsub = new ResumableSub_ImgDrag_PlacedView(null,_idragview,_iplaceview);
rsub.resume(processBA, null);
}
public static class ResumableSub_ImgDrag_PlacedView extends BA.ResumableSub {
public ResumableSub_ImgDrag_PlacedView(b4a.example.corkactivity parent,anywheresoftware.b4a.objects.ConcreteViewWrapper _idragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _iplaceview) {
this.parent = parent;
this._idragview = _idragview;
this._iplaceview = _iplaceview;
}
b4a.example.corkactivity parent;
anywheresoftware.b4a.objects.ConcreteViewWrapper _idragview;
anywheresoftware.b4a.objects.ConcreteViewWrapper _iplaceview;
int _res = 0;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
String _key = "";
int _newcount = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 587;BA.debugLine="If iPlaceView.Tag = \"delete\" Then";
if (true) break;

case 1:
//if
this.state = 10;
if ((_iplaceview.getTag()).equals((Object)("delete"))) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 588;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete im";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete image?"),BA.ObjectToCharSequence("Delete Image"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 589;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (Integer) result[0];
;
 //BA.debugLineNum = 590;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 591;BA.debugLine="Dim iv As ImageView = iDragView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_idragview.getObject()));
 //BA.debugLineNum = 592;BA.debugLine="Dim key As String = iv.Tag";
_key = BA.ObjectToString(_iv.getTag());
 //BA.debugLineNum = 593;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png");
 //BA.debugLineNum = 594;BA.debugLine="Main.kvs.Remove(key & \"_file\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_file");
 //BA.debugLineNum = 595;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
 //BA.debugLineNum = 596;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
 //BA.debugLineNum = 597;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"img_count\")";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("img_count")));
 //BA.debugLineNum = 598;BA.debugLine="Main.kvs.Put(\"img_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("img_count",(Object)(_newcount-1));
 //BA.debugLineNum = 599;BA.debugLine="iv.Visible = False";
_iv.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 600;BA.debugLine="ToastMessageShow(\"Image Deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Image Deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 603;BA.debugLine="Dim iv As ImageView = iDragView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_idragview.getObject()));
 //BA.debugLineNum = 604;BA.debugLine="Dim key As String = iv.Tag";
_key = BA.ObjectToString(_iv.getTag());
 //BA.debugLineNum = 605;BA.debugLine="Main.kvs.Put(key & \"_x\", iv.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_iv.getLeft()));
 //BA.debugLineNum = 606;BA.debugLine="Main.kvs.Put(key & \"_y\", iv.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_iv.getTop()));
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 608;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _notedrag_placedview(anywheresoftware.b4a.objects.ConcreteViewWrapper _ndragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _nplaceview) throws Exception{
ResumableSub_NoteDrag_PlacedView rsub = new ResumableSub_NoteDrag_PlacedView(null,_ndragview,_nplaceview);
rsub.resume(processBA, null);
}
public static class ResumableSub_NoteDrag_PlacedView extends BA.ResumableSub {
public ResumableSub_NoteDrag_PlacedView(b4a.example.corkactivity parent,anywheresoftware.b4a.objects.ConcreteViewWrapper _ndragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _nplaceview) {
this.parent = parent;
this._ndragview = _ndragview;
this._nplaceview = _nplaceview;
}
b4a.example.corkactivity parent;
anywheresoftware.b4a.objects.ConcreteViewWrapper _ndragview;
anywheresoftware.b4a.objects.ConcreteViewWrapper _nplaceview;
int _res = 0;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
String _key = "";
int _newcount = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 563;BA.debugLine="If nPlaceView.Tag = \"delete\" Then";
if (true) break;

case 1:
//if
this.state = 10;
if ((_nplaceview.getTag()).equals((Object)("delete"))) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 564;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete no";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete note?"),BA.ObjectToCharSequence("Delete Note"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 565;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (Integer) result[0];
;
 //BA.debugLineNum = 566;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 567;BA.debugLine="Dim p As Panel = nDragView";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_ndragview.getObject()));
 //BA.debugLineNum = 568;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
 //BA.debugLineNum = 569;BA.debugLine="Main.kvs.Remove(key & \"_text\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_text");
 //BA.debugLineNum = 570;BA.debugLine="Main.kvs.Remove(key & \"_color\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_color");
 //BA.debugLineNum = 571;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
 //BA.debugLineNum = 572;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
 //BA.debugLineNum = 573;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"note_count\"";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("note_count")));
 //BA.debugLineNum = 574;BA.debugLine="Main.kvs.Put(\"note_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("note_count",(Object)(_newcount-1));
 //BA.debugLineNum = 575;BA.debugLine="p.Visible = False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 576;BA.debugLine="ToastMessageShow(\"Note Deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Note Deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 579;BA.debugLine="Dim p As Panel = nDragView";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_ndragview.getObject()));
 //BA.debugLineNum = 580;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
 //BA.debugLineNum = 581;BA.debugLine="Main.kvs.Put(key & \"_x\", p.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_p.getLeft()));
 //BA.debugLineNum = 582;BA.debugLine="Main.kvs.Put(key & \"_y\", p.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_p.getTop()));
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 584;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _notetext_textchanged(String _old,String _new) throws Exception{
anywheresoftware.b4a.objects.EditTextWrapper _txt = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
String _key = "";
 //BA.debugLineNum = 222;BA.debugLine="Sub NoteText_TextChanged(Old As String, New As Str";
 //BA.debugLineNum = 223;BA.debugLine="Dim txt As EditText = Sender";
_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
_txt = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 224;BA.debugLine="Dim p As Panel = txt.Tag";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_txt.getTag()));
 //BA.debugLineNum = 225;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
 //BA.debugLineNum = 226;BA.debugLine="Main.kvs.Put(key & \"_text\", New)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_text",(Object)(_new));
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static String  _notewindow(int _pw,int _ph) throws Exception{
anywheresoftware.b4a.objects.SpinnerWrapper _colorsspnr = null;
anywheresoftware.b4a.objects.ButtonWrapper _addnbtn = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 312;BA.debugLine="Private Sub noteWindow(pW As Int, pH As Int)";
 //BA.debugLineNum = 313;BA.debugLine="notePnl = xui.CreatePanel(\"notePnl\")";
mostCurrent._notepnl = _xui.CreatePanel(processBA,"notePnl");
 //BA.debugLineNum = 314;BA.debugLine="Activity.AddView(notePnl, 100dip, 225dip, pW, pH)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._notepnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),_pw,_ph);
 //BA.debugLineNum = 317;BA.debugLine="Dim colorsSpnr As Spinner";
_colorsspnr = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 318;BA.debugLine="colorsSpnr.Initialize(\"colorsSpnr\")";
_colorsspnr.Initialize(mostCurrent.activityBA,"colorsSpnr");
 //BA.debugLineNum = 319;BA.debugLine="colorsSpnr.AddAll(Array As String(\"Red\", \"Blue\",";
_colorsspnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Red","Blue","Yellow"}));
 //BA.debugLineNum = 320;BA.debugLine="notePnl.AddView(colorsSpnr, 10dip, 10dip, pW - 20";
mostCurrent._notepnl.AddView((android.view.View)(_colorsspnr.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 322;BA.debugLine="Dim addnBtn As Button";
_addnbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 323;BA.debugLine="addnBtn.Initialize(\"addnBtn\")";
_addnbtn.Initialize(mostCurrent.activityBA,"addnBtn");
 //BA.debugLineNum = 324;BA.debugLine="addnBtn.Text = \"Add Note\"";
_addnbtn.setText(BA.ObjectToCharSequence("Add Note"));
 //BA.debugLineNum = 325;BA.debugLine="notePnl.AddView(addnBtn, 10dip, 60dip, (pW / 2) -";
mostCurrent._notepnl.AddView((android.view.View)(_addnbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),(int) ((_pw/(double)2)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 327;BA.debugLine="notePnl.Enabled = False";
mostCurrent._notepnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 328;BA.debugLine="notePnl.Visible = False";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 330;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 331;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 332;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 334;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
 //BA.debugLineNum = 335;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 336;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(187, 2";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (187),(int) (213),(int) (218)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 337;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
 //BA.debugLineNum = 338;BA.debugLine="addnBtn.Background = cd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 339;BA.debugLine="addnBtn.TextColor = Colors.Black";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
 //BA.debugLineNum = 341;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 342;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(227, 1";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (227),(int) (193),(int) (159)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 343;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
 //BA.debugLineNum = 344;BA.debugLine="addnBtn.Background = cd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 345;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
 //BA.debugLineNum = 348;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
 //BA.debugLineNum = 349;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 350;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(27, 98";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (27),(int) (98),(int) (161)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (16),(int) (26),(int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 351;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"dark";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dark Skeumorphic Button blue.png").getObject()));
 //BA.debugLineNum = 352;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 353;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 355;BA.debugLine="notePnl.Color = xui.Color_RGB(255, 255, 255)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
 //BA.debugLineNum = 356;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(51, 18";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (51),(int) (187),(int) (152)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 357;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Button 03.png").getObject()));
 //BA.debugLineNum = 358;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 359;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
 //BA.debugLineNum = 362;BA.debugLine="addnBtn.Typeface = pixeltf";
_addnbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
 //BA.debugLineNum = 363;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
 //BA.debugLineNum = 364;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 365;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(37, 57";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (37),(int) (57),(int) (94)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 366;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
 //BA.debugLineNum = 367;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 368;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 370;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 371;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(234, 2";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (234),(int) (212),(int) (179)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (79),(int) (46),(int) (46)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 372;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
 //BA.debugLineNum = 373;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 374;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
 //BA.debugLineNum = 377;BA.debugLine="End Sub";
return "";
}
public static String  _penspnr_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 531;BA.debugLine="Private Sub penSpnr_ItemClick (Position As Int, Va";
 //BA.debugLineNum = 532;BA.debugLine="Select Position";
switch (_position) {
case 0: {
 //BA.debugLineNum = 534;BA.debugLine="R2 = 0";
_r2 = (int) (0);
 //BA.debugLineNum = 535;BA.debugLine="G2 = 0";
_g2 = (int) (0);
 //BA.debugLineNum = 536;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 1: {
 //BA.debugLineNum = 538;BA.debugLine="R2 = 0";
_r2 = (int) (0);
 //BA.debugLineNum = 539;BA.debugLine="G2 = 0";
_g2 = (int) (0);
 //BA.debugLineNum = 540;BA.debugLine="B2 = 255";
_b2 = (int) (255);
 break; }
case 2: {
 //BA.debugLineNum = 542;BA.debugLine="R2 = 0";
_r2 = (int) (0);
 //BA.debugLineNum = 543;BA.debugLine="G2 = 255";
_g2 = (int) (255);
 //BA.debugLineNum = 544;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 3: {
 //BA.debugLineNum = 546;BA.debugLine="R2 = 255";
_r2 = (int) (255);
 //BA.debugLineNum = 547;BA.debugLine="G2 = 0";
_g2 = (int) (0);
 //BA.debugLineNum = 548;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 4: {
 //BA.debugLineNum = 550;BA.debugLine="R2 = 255";
_r2 = (int) (255);
 //BA.debugLineNum = 551;BA.debugLine="G2 = 255";
_g2 = (int) (255);
 //BA.debugLineNum = 552;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 5: {
 //BA.debugLineNum = 554;BA.debugLine="R2 = 255";
_r2 = (int) (255);
 //BA.debugLineNum = 555;BA.debugLine="G2 = 255";
_g2 = (int) (255);
 //BA.debugLineNum = 556;BA.debugLine="B2 = 255";
_b2 = (int) (255);
 break; }
}
;
 //BA.debugLineNum = 558;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private imgPicker As ContentChooser";
_imgpicker = new anywheresoftware.b4a.phone.Phone.ContentChooser();
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _sizespnr_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 491;BA.debugLine="Private Sub sizeSpnr_ItemClick (Position As Int, V";
 //BA.debugLineNum = 492;BA.debugLine="Select Position";
switch (_position) {
case 0: {
 //BA.debugLineNum = 494;BA.debugLine="Width = 80dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
 //BA.debugLineNum = 495;BA.debugLine="Height = 60dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 break; }
case 1: {
 //BA.debugLineNum = 497;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
 //BA.debugLineNum = 498;BA.debugLine="Height = 60dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 break; }
case 2: {
 //BA.debugLineNum = 500;BA.debugLine="Width = 80dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
 //BA.debugLineNum = 501;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 3: {
 //BA.debugLineNum = 503;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
 //BA.debugLineNum = 504;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 4: {
 //BA.debugLineNum = 506;BA.debugLine="Width = 330dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
 //BA.debugLineNum = 507;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 5: {
 //BA.debugLineNum = 509;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
 //BA.debugLineNum = 510;BA.debugLine="Height = 310dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (310));
 break; }
}
;
 //BA.debugLineNum = 512;BA.debugLine="End Sub";
return "";
}
public static String  _stickybtn_click() throws Exception{
 //BA.debugLineNum = 468;BA.debugLine="Private Sub stickyBtn_Click";
 //BA.debugLineNum = 469;BA.debugLine="noteWindow(250dip, 180dip)";
_notewindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
 //BA.debugLineNum = 470;BA.debugLine="notePnl.Visible = True";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 471;BA.debugLine="stickyBtn.Enabled = False";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 472;BA.debugLine="canvaBtn.Enabled = False";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 473;BA.debugLine="imgBtn.Enabled = False";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 474;BA.debugLine="End Sub";
return "";
}
}
