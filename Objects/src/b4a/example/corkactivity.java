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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.corkactivity");
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



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.card_module _card_module = null;
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
public static void  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}); return;}
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
RDebugUtils.currentModule="corkactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=12451841;
 //BA.debugLineNum = 12451841;BA.debugLine="Select Starter.themeNumber";
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
RDebugUtils.currentLine=12451843;
 //BA.debugLineNum = 12451843;BA.debugLine="If Starter.darkMode = False Then";
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
RDebugUtils.currentLine=12451844;
 //BA.debugLineNum = 12451844;BA.debugLine="Activity.LoadLayout(\"corkboardLayout\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout",mostCurrent.activityBA);
 if (true) break;

case 8:
//C
this.state = 9;
RDebugUtils.currentLine=12451846;
 //BA.debugLineNum = 12451846;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark\")";
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
RDebugUtils.currentLine=12451849;
 //BA.debugLineNum = 12451849;BA.debugLine="If Starter.darkMode = False Then";
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
RDebugUtils.currentLine=12451850;
 //BA.debugLineNum = 12451850;BA.debugLine="Activity.LoadLayout(\"corkboardLayout2\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout2",mostCurrent.activityBA);
 if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=12451852;
 //BA.debugLineNum = 12451852;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark2\")";
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
RDebugUtils.currentLine=12451855;
 //BA.debugLineNum = 12451855;BA.debugLine="If Starter.darkMode = False Then";
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
RDebugUtils.currentLine=12451856;
 //BA.debugLineNum = 12451856;BA.debugLine="Activity.LoadLayout(\"corkboardLayout3\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout3",mostCurrent.activityBA);
 if (true) break;

case 24:
//C
this.state = 25;
RDebugUtils.currentLine=12451858;
 //BA.debugLineNum = 12451858;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark3\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayoutDark3",mostCurrent.activityBA);
 if (true) break;

case 25:
//C
this.state = 26;
;
 if (true) break;
;
RDebugUtils.currentLine=12451862;
 //BA.debugLineNum = 12451862;BA.debugLine="If Starter.darkMode = False Then";

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
RDebugUtils.currentLine=12451863;
 //BA.debugLineNum = 12451863;BA.debugLine="penSpnr.DropdownBackgroundColor = Colors.DarkGra";
parent.mostCurrent._penspnr.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=12451864;
 //BA.debugLineNum = 12451864;BA.debugLine="penSpnr.DropdownTextColor = Colors.White";
parent.mostCurrent._penspnr.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 if (true) break;

case 30:
//C
this.state = 31;
RDebugUtils.currentLine=12451866;
 //BA.debugLineNum = 12451866;BA.debugLine="penSpnr.DropdownTextColor = Colors.Black";
parent.mostCurrent._penspnr.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 if (true) break;

case 31:
//C
this.state = 32;
;
RDebugUtils.currentLine=12451869;
 //BA.debugLineNum = 12451869;BA.debugLine="penSpnr.AddAll(Array As String(\"Black\", \"Blue\", \"";
parent.mostCurrent._penspnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Black","Blue","Green","Red","Yellow","Eraser"}));
RDebugUtils.currentLine=12451870;
 //BA.debugLineNum = 12451870;BA.debugLine="If FirstTime Then";
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
RDebugUtils.currentLine=12451871;
 //BA.debugLineNum = 12451871;BA.debugLine="imgPicker.Initialize(\"CC\")";
parent._imgpicker.Initialize("CC");
 if (true) break;

case 35:
//C
this.state = 36;
;
RDebugUtils.currentLine=12451873;
 //BA.debugLineNum = 12451873;BA.debugLine="penSpnr.Visible = False";
parent.mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12451874;
 //BA.debugLineNum = 12451874;BA.debugLine="Width = 80dip";
parent._width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=12451875;
 //BA.debugLineNum = 12451875;BA.debugLine="Height = 60dip";
parent._height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=12451876;
 //BA.debugLineNum = 12451876;BA.debugLine="ddn.Initialize(Me, \"NoteDrag\")";
parent.mostCurrent._ddn._initialize(processBA,corkactivity.getObject(),"NoteDrag");
RDebugUtils.currentLine=12451877;
 //BA.debugLineNum = 12451877;BA.debugLine="ddi.Initialize(Me, \"ImgDrag\")";
parent.mostCurrent._ddi._initialize(processBA,corkactivity.getObject(),"ImgDrag");
RDebugUtils.currentLine=12451878;
 //BA.debugLineNum = 12451878;BA.debugLine="ddc.Initialize(Me, \"CanvasDrag\")";
parent.mostCurrent._ddc._initialize(processBA,corkactivity.getObject(),"CanvasDrag");
RDebugUtils.currentLine=12451880;
 //BA.debugLineNum = 12451880;BA.debugLine="If Main.kvs.IsInitialized = False Then";
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
RDebugUtils.currentLine=12451881;
 //BA.debugLineNum = 12451881;BA.debugLine="Main.kvs.Initialize(File.DirInternal, \"notes_dat";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"notes_data");
 if (true) break;

case 39:
//C
this.state = 40;
;
RDebugUtils.currentLine=12451884;
 //BA.debugLineNum = 12451884;BA.debugLine="isLoading = True";
parent._isloading = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=12451886;
 //BA.debugLineNum = 12451886;BA.debugLine="If Main.kvs.ContainsKey(\"note_count\") Then noteCo";
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
RDebugUtils.currentLine=12451887;
 //BA.debugLineNum = 12451887;BA.debugLine="Dim loadedNoteCount As Int = noteCount";
_loadednotecount = parent._notecount;
RDebugUtils.currentLine=12451888;
 //BA.debugLineNum = 12451888;BA.debugLine="For i = 0 To loadedNoteCount - 1";
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
RDebugUtils.currentLine=12451889;
 //BA.debugLineNum = 12451889;BA.debugLine="Dim nkey As String = \"note_\" & i";
_nkey = "note_"+BA.NumberToString(_i);
RDebugUtils.currentLine=12451890;
 //BA.debugLineNum = 12451890;BA.debugLine="If Main.kvs.ContainsKey(nkey & \"_text\") Then";
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
RDebugUtils.currentLine=12451891;
 //BA.debugLineNum = 12451891;BA.debugLine="Dim savedColor As Int = Main.kvs.Get(nkey & \"_c";
_savedcolor = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_color")));
RDebugUtils.currentLine=12451892;
 //BA.debugLineNum = 12451892;BA.debugLine="R = Bit.And(Bit.ShiftRight(savedColor, 16), 0xF";
parent._r = anywheresoftware.b4a.keywords.Common.Bit.And(anywheresoftware.b4a.keywords.Common.Bit.ShiftRight(_savedcolor,(int) (16)),((int)0xff));
RDebugUtils.currentLine=12451893;
 //BA.debugLineNum = 12451893;BA.debugLine="G = Bit.And(Bit.ShiftRight(savedColor, 8), 0xFF";
parent._g = anywheresoftware.b4a.keywords.Common.Bit.And(anywheresoftware.b4a.keywords.Common.Bit.ShiftRight(_savedcolor,(int) (8)),((int)0xff));
RDebugUtils.currentLine=12451894;
 //BA.debugLineNum = 12451894;BA.debugLine="B = Bit.And(savedColor, 0xFF)";
parent._b = anywheresoftware.b4a.keywords.Common.Bit.And(_savedcolor,((int)0xff));
RDebugUtils.currentLine=12451895;
 //BA.debugLineNum = 12451895;BA.debugLine="noteCount = i + 1";
parent._notecount = (int) (_i+1);
RDebugUtils.currentLine=12451896;
 //BA.debugLineNum = 12451896;BA.debugLine="AddStickyNote(Main.kvs.Get(nkey & \"_text\"), Mai";
_addstickynote(BA.ObjectToString(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_text")),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_y"))));
 if (true) break;

case 54:
//C
this.state = 91;
;
 if (true) break;
if (true) break;
;
RDebugUtils.currentLine=12451900;
 //BA.debugLineNum = 12451900;BA.debugLine="If Main.kvs.ContainsKey(\"img_count\") Then imgCoun";

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
RDebugUtils.currentLine=12451901;
 //BA.debugLineNum = 12451901;BA.debugLine="Dim loadedImgCount As Int = imgCount";
_loadedimgcount = parent._imgcount;
RDebugUtils.currentLine=12451902;
 //BA.debugLineNum = 12451902;BA.debugLine="For i = 0 To loadedImgCount - 1";
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
RDebugUtils.currentLine=12451903;
 //BA.debugLineNum = 12451903;BA.debugLine="Dim ikey As String = \"img_\" & i";
_ikey = "img_"+BA.NumberToString(_i);
RDebugUtils.currentLine=12451904;
 //BA.debugLineNum = 12451904;BA.debugLine="If Main.kvs.ContainsKey(ikey & \"_file\") Then";
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
RDebugUtils.currentLine=12451905;
 //BA.debugLineNum = 12451905;BA.debugLine="Dim iv As ImageView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=12451906;
 //BA.debugLineNum = 12451906;BA.debugLine="iv.Initialize(\"ImgView\")";
_iv.Initialize(mostCurrent.activityBA,"ImgView");
RDebugUtils.currentLine=12451907;
 //BA.debugLineNum = 12451907;BA.debugLine="boardPnl.AddView(iv, Main.kvs.Get(ikey & \"_x\"),";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_iv.getObject()),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_y"))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=12451908;
 //BA.debugLineNum = 12451908;BA.debugLine="iv.Bitmap = LoadBitmapResize(File.DirInternal,";
_iv.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),BA.ObjectToString(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_file")),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12451909;
 //BA.debugLineNum = 12451909;BA.debugLine="iv.Tag = ikey";
_iv.setTag((Object)(_ikey));
RDebugUtils.currentLine=12451910;
 //BA.debugLineNum = 12451910;BA.debugLine="ddi.AddDragView(iv, False)";
parent.mostCurrent._ddi._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12451911;
 //BA.debugLineNum = 12451911;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).A";
parent.mostCurrent._ddi._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
 if (true) break;

case 69:
//C
this.state = 93;
;
 if (true) break;
if (true) break;
;
RDebugUtils.currentLine=12451915;
 //BA.debugLineNum = 12451915;BA.debugLine="If Main.kvs.ContainsKey(\"cvs_count\") Then canvasC";

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
RDebugUtils.currentLine=12451916;
 //BA.debugLineNum = 12451916;BA.debugLine="Dim loadedCanvasCount As Int = canvasCount";
_loadedcanvascount = parent._canvascount;
RDebugUtils.currentLine=12451917;
 //BA.debugLineNum = 12451917;BA.debugLine="For i = 0 To loadedCanvasCount - 1";
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
RDebugUtils.currentLine=12451918;
 //BA.debugLineNum = 12451918;BA.debugLine="Dim ckey As String = \"cvs_\" & i";
_ckey = "cvs_"+BA.NumberToString(_i);
RDebugUtils.currentLine=12451919;
 //BA.debugLineNum = 12451919;BA.debugLine="If Main.kvs.ContainsKey(ckey & \"_x\") Then";
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
RDebugUtils.currentLine=12451920;
 //BA.debugLineNum = 12451920;BA.debugLine="Width = Main.kvs.Get(ckey & \"_w\")";
parent._width = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_w")));
RDebugUtils.currentLine=12451921;
 //BA.debugLineNum = 12451921;BA.debugLine="Height = Main.kvs.Get(ckey & \"_h\")";
parent._height = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_h")));
RDebugUtils.currentLine=12451923;
 //BA.debugLineNum = 12451923;BA.debugLine="Dim f As Panel";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=12451924;
 //BA.debugLineNum = 12451924;BA.debugLine="f.Initialize(\"CanvasFrame\")";
_f.Initialize(mostCurrent.activityBA,"CanvasFrame");
RDebugUtils.currentLine=12451925;
 //BA.debugLineNum = 12451925;BA.debugLine="f.Color = Colors.Black";
_f.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=12451926;
 //BA.debugLineNum = 12451926;BA.debugLine="boardPnl.AddView(f, Main.kvs.Get(ckey & \"_x\"),";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_f.getObject()),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_y"))),(int) (parent._width+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (parent._height+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
RDebugUtils.currentLine=12451927;
 //BA.debugLineNum = 12451927;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=12451928;
 //BA.debugLineNum = 12451928;BA.debugLine="p.Initialize(\"CanvasPanel\")";
_p.Initialize(mostCurrent.activityBA,"CanvasPanel");
RDebugUtils.currentLine=12451929;
 //BA.debugLineNum = 12451929;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=12451930;
 //BA.debugLineNum = 12451930;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
_f.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),parent._width,parent._height);
RDebugUtils.currentLine=12451931;
 //BA.debugLineNum = 12451931;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "activity_create"),(int) (0));
this.state = 96;
return;
case 96:
//C
this.state = 84;
;
RDebugUtils.currentLine=12451932;
 //BA.debugLineNum = 12451932;BA.debugLine="Dim cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
RDebugUtils.currentLine=12451933;
 //BA.debugLineNum = 12451933;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())));
RDebugUtils.currentLine=12451934;
 //BA.debugLineNum = 12451934;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray,";
_cvs.DrawRect(_cvs.getTargetRect(),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
RDebugUtils.currentLine=12451935;
 //BA.debugLineNum = 12451935;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
RDebugUtils.currentLine=12451936;
 //BA.debugLineNum = 12451936;BA.debugLine="p.Tag = cvs";
_p.setTag((Object)(_cvs));
RDebugUtils.currentLine=12451937;
 //BA.debugLineNum = 12451937;BA.debugLine="ddc.AddDragView(f, False)";
parent.mostCurrent._ddc._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_f.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12451938;
 //BA.debugLineNum = 12451938;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).A";
parent.mostCurrent._ddc._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=12451939;
 //BA.debugLineNum = 12451939;BA.debugLine="f.Tag = ckey";
_f.setTag((Object)(_ckey));
RDebugUtils.currentLine=12451941;
 //BA.debugLineNum = 12451941;BA.debugLine="If File.Exists(File.DirInternal, ckey & \".png\")";
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
RDebugUtils.currentLine=12451942;
 //BA.debugLineNum = 12451942;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "activity_create"),(int) (0));
this.state = 97;
return;
case 97:
//C
this.state = 87;
;
RDebugUtils.currentLine=12451943;
 //BA.debugLineNum = 12451943;BA.debugLine="Dim bmp As Bitmap = LoadBitmap(File.DirInterna";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_ckey+".png");
RDebugUtils.currentLine=12451944;
 //BA.debugLineNum = 12451944;BA.debugLine="Dim canvasRect As B4XRect = cvs.TargetRect";
_canvasrect = _cvs.getTargetRect();
RDebugUtils.currentLine=12451945;
 //BA.debugLineNum = 12451945;BA.debugLine="cvs.DrawBitmap(bmp, canvasRect)";
_cvs.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),_canvasrect);
RDebugUtils.currentLine=12451946;
 //BA.debugLineNum = 12451946;BA.debugLine="cvs.Invalidate";
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
RDebugUtils.currentLine=12451950;
 //BA.debugLineNum = 12451950;BA.debugLine="canvasCount = loadedCanvasCount";
parent._canvascount = _loadedcanvascount;
RDebugUtils.currentLine=12451951;
 //BA.debugLineNum = 12451951;BA.debugLine="Width = 80dip";
parent._width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=12451952;
 //BA.debugLineNum = 12451952;BA.debugLine="Height = 60dip";
parent._height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=12451953;
 //BA.debugLineNum = 12451953;BA.debugLine="isLoading = False";
parent._isloading = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12451954;
 //BA.debugLineNum = 12451954;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _addstickynote(String _text,int _x,int _y) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addstickynote", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addstickynote", new Object[] {_text,_x,_y}));}
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.EditTextWrapper _txt = null;
String _key = "";
RDebugUtils.currentLine=12648448;
 //BA.debugLineNum = 12648448;BA.debugLine="Sub AddStickyNote(Text As String, x As Int, y As I";
RDebugUtils.currentLine=12648449;
 //BA.debugLineNum = 12648449;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=12648450;
 //BA.debugLineNum = 12648450;BA.debugLine="p.Initialize(\"NotePanel\")";
_p.Initialize(mostCurrent.activityBA,"NotePanel");
RDebugUtils.currentLine=12648451;
 //BA.debugLineNum = 12648451;BA.debugLine="p.Color = Colors.RGB(R, G, B)";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB(_r,_g,_b));
RDebugUtils.currentLine=12648453;
 //BA.debugLineNum = 12648453;BA.debugLine="Dim txt As EditText";
_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=12648454;
 //BA.debugLineNum = 12648454;BA.debugLine="txt.Initialize(\"NoteText\")";
_txt.Initialize(mostCurrent.activityBA,"NoteText");
RDebugUtils.currentLine=12648455;
 //BA.debugLineNum = 12648455;BA.debugLine="txt.Tag = p";
_txt.setTag((Object)(_p.getObject()));
RDebugUtils.currentLine=12648456;
 //BA.debugLineNum = 12648456;BA.debugLine="txt.Text = Text";
_txt.setText(BA.ObjectToCharSequence(_text));
RDebugUtils.currentLine=12648457;
 //BA.debugLineNum = 12648457;BA.debugLine="txt.TextSize = 12";
_txt.setTextSize((float) (12));
RDebugUtils.currentLine=12648458;
 //BA.debugLineNum = 12648458;BA.debugLine="txt.Background = Null";
_txt.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=12648459;
 //BA.debugLineNum = 12648459;BA.debugLine="txt.TextColor = Colors.Black";
_txt.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=12648460;
 //BA.debugLineNum = 12648460;BA.debugLine="txt.Gravity = Gravity.TOP";
_txt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
RDebugUtils.currentLine=12648462;
 //BA.debugLineNum = 12648462;BA.debugLine="p.AddView(txt, 5dip, 15dip, 90dip, 70dip)";
_p.AddView((android.view.View)(_txt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=12648464;
 //BA.debugLineNum = 12648464;BA.debugLine="boardPnl.AddView(p, x, y, 100dip, 100dip)";
mostCurrent._boardpnl.AddView((android.view.View)(_p.getObject()),_x,_y,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=12648466;
 //BA.debugLineNum = 12648466;BA.debugLine="ddn.AddDragView(p, False)";
mostCurrent._ddn._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12648467;
 //BA.debugLineNum = 12648467;BA.debugLine="ddn.AddPlaceView(place1).AddPlaceView(place2).Add";
mostCurrent._ddn._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=12648469;
 //BA.debugLineNum = 12648469;BA.debugLine="If isLoading = False Then";
if (_isloading==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12648470;
 //BA.debugLineNum = 12648470;BA.debugLine="Dim key As String = \"note_\" & noteCount";
_key = "note_"+BA.NumberToString(_notecount);
RDebugUtils.currentLine=12648471;
 //BA.debugLineNum = 12648471;BA.debugLine="Main.kvs.Put(key & \"_text\", Text)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_text",(Object)(_text));
RDebugUtils.currentLine=12648472;
 //BA.debugLineNum = 12648472;BA.debugLine="Main.kvs.Put(key & \"_color\", Colors.RGB(R, G, B)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_color",(Object)(anywheresoftware.b4a.keywords.Common.Colors.RGB(_r,_g,_b)));
RDebugUtils.currentLine=12648473;
 //BA.debugLineNum = 12648473;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_x));
RDebugUtils.currentLine=12648474;
 //BA.debugLineNum = 12648474;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_y));
RDebugUtils.currentLine=12648475;
 //BA.debugLineNum = 12648475;BA.debugLine="Main.kvs.Put(\"note_count\", noteCount + 1)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("note_count",(Object)(_notecount+1));
RDebugUtils.currentLine=12648476;
 //BA.debugLineNum = 12648476;BA.debugLine="p.Tag = key";
_p.setTag((Object)(_key));
RDebugUtils.currentLine=12648477;
 //BA.debugLineNum = 12648477;BA.debugLine="noteCount = noteCount + 1";
_notecount = (int) (_notecount+1);
 }else {
RDebugUtils.currentLine=12648479;
 //BA.debugLineNum = 12648479;BA.debugLine="p.Tag = \"note_\" & (noteCount - 1)";
_p.setTag((Object)("note_"+BA.NumberToString((_notecount-1))));
 };
RDebugUtils.currentLine=12648481;
 //BA.debugLineNum = 12648481;BA.debugLine="Log(\"deleteLbl initialized: \" & (deleteLbl.IsInit";
anywheresoftware.b4a.keywords.Common.LogImpl("312648481","deleteLbl initialized: "+BA.ObjectToString((mostCurrent._deletelbl.IsInitialized())),0);
RDebugUtils.currentLine=12648482;
 //BA.debugLineNum = 12648482;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="corkactivity";
RDebugUtils.currentLine=12582912;
 //BA.debugLineNum = 12582912;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=12582914;
 //BA.debugLineNum = 12582914;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=12517376;
 //BA.debugLineNum = 12517376;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=12517377;
 //BA.debugLineNum = 12517377;BA.debugLine="If canvasCount > 0 Then";
if (_canvascount>0) { 
RDebugUtils.currentLine=12517378;
 //BA.debugLineNum = 12517378;BA.debugLine="penSpnr.Visible = True";
mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=12517380;
 //BA.debugLineNum = 12517380;BA.debugLine="End Sub";
return "";
}
public static void  _addcanvas(int _x,int _y) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addcanvas", false))
	 {Debug.delegate(mostCurrent.activityBA, "addcanvas", new Object[] {_x,_y}); return;}
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
RDebugUtils.currentModule="corkactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=12845057;
 //BA.debugLineNum = 12845057;BA.debugLine="Dim f As Panel";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=12845058;
 //BA.debugLineNum = 12845058;BA.debugLine="f.Initialize(\"CanvasFrame\")";
_f.Initialize(mostCurrent.activityBA,"CanvasFrame");
RDebugUtils.currentLine=12845059;
 //BA.debugLineNum = 12845059;BA.debugLine="f.Color = Colors.Black";
_f.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=12845060;
 //BA.debugLineNum = 12845060;BA.debugLine="boardPnl.AddView(f, x, y, Width + 20dip, Height +";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_f.getObject()),_x,_y,(int) (parent._width+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (parent._height+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
RDebugUtils.currentLine=12845062;
 //BA.debugLineNum = 12845062;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=12845063;
 //BA.debugLineNum = 12845063;BA.debugLine="p.Initialize(\"CanvasPanel\")";
_p.Initialize(mostCurrent.activityBA,"CanvasPanel");
RDebugUtils.currentLine=12845064;
 //BA.debugLineNum = 12845064;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=12845065;
 //BA.debugLineNum = 12845065;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
_f.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),parent._width,parent._height);
RDebugUtils.currentLine=12845067;
 //BA.debugLineNum = 12845067;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "addcanvas"),(int) (0));
this.state = 7;
return;
case 7:
//C
this.state = 1;
;
RDebugUtils.currentLine=12845069;
 //BA.debugLineNum = 12845069;BA.debugLine="Dim cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
RDebugUtils.currentLine=12845070;
 //BA.debugLineNum = 12845070;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())));
RDebugUtils.currentLine=12845071;
 //BA.debugLineNum = 12845071;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray, Fa";
_cvs.DrawRect(_cvs.getTargetRect(),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
RDebugUtils.currentLine=12845072;
 //BA.debugLineNum = 12845072;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
RDebugUtils.currentLine=12845073;
 //BA.debugLineNum = 12845073;BA.debugLine="p.Tag = cvs";
_p.setTag((Object)(_cvs));
RDebugUtils.currentLine=12845075;
 //BA.debugLineNum = 12845075;BA.debugLine="ddc.AddDragView(f, False)";
parent.mostCurrent._ddc._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_f.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12845076;
 //BA.debugLineNum = 12845076;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).Add";
parent.mostCurrent._ddc._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=12845077;
 //BA.debugLineNum = 12845077;BA.debugLine="If isLoading = False Then";
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
RDebugUtils.currentLine=12845078;
 //BA.debugLineNum = 12845078;BA.debugLine="Dim key As String = \"cvs_\" & canvasCount";
_key = "cvs_"+BA.NumberToString(parent._canvascount);
RDebugUtils.currentLine=12845079;
 //BA.debugLineNum = 12845079;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_x));
RDebugUtils.currentLine=12845080;
 //BA.debugLineNum = 12845080;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_y));
RDebugUtils.currentLine=12845081;
 //BA.debugLineNum = 12845081;BA.debugLine="Main.kvs.Put(key & \"_w\", Width)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_w",(Object)(parent._width));
RDebugUtils.currentLine=12845082;
 //BA.debugLineNum = 12845082;BA.debugLine="Main.kvs.Put(key & \"_h\", Height)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_h",(Object)(parent._height));
RDebugUtils.currentLine=12845083;
 //BA.debugLineNum = 12845083;BA.debugLine="Main.kvs.Put(\"cvs_count\", canvasCount + 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("cvs_count",(Object)(parent._canvascount+1));
RDebugUtils.currentLine=12845084;
 //BA.debugLineNum = 12845084;BA.debugLine="f.Tag = key";
_f.setTag((Object)(_key));
RDebugUtils.currentLine=12845085;
 //BA.debugLineNum = 12845085;BA.debugLine="canvasCount = canvasCount + 1";
parent._canvascount = (int) (parent._canvascount+1);
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=12845087;
 //BA.debugLineNum = 12845087;BA.debugLine="f.Tag = \"cvs_\" & (canvasCount - 1)";
_f.setTag((Object)("cvs_"+BA.NumberToString((parent._canvascount-1))));
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=12845089;
 //BA.debugLineNum = 12845089;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _addcbtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addcbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addcbtn_click", null));}
RDebugUtils.currentLine=13172736;
 //BA.debugLineNum = 13172736;BA.debugLine="Private Sub addcBtn_Click";
RDebugUtils.currentLine=13172737;
 //BA.debugLineNum = 13172737;BA.debugLine="AddCanvas(150dip, 500dip)";
_addcanvas(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)));
RDebugUtils.currentLine=13172738;
 //BA.debugLineNum = 13172738;BA.debugLine="canvasPnl.Visible = False";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13172739;
 //BA.debugLineNum = 13172739;BA.debugLine="canvaBtn.Enabled = True";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13172740;
 //BA.debugLineNum = 13172740;BA.debugLine="stickyBtn.Enabled = True";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13172741;
 //BA.debugLineNum = 13172741;BA.debugLine="imgBtn.Enabled = True";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13172742;
 //BA.debugLineNum = 13172742;BA.debugLine="End Sub";
return "";
}
public static String  _addnbtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addnbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addnbtn_click", null));}
RDebugUtils.currentLine=13107200;
 //BA.debugLineNum = 13107200;BA.debugLine="Private Sub addnBtn_Click";
RDebugUtils.currentLine=13107201;
 //BA.debugLineNum = 13107201;BA.debugLine="AddStickyNote(\"\", 150dip, 500dip)";
_addstickynote("",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)));
RDebugUtils.currentLine=13107202;
 //BA.debugLineNum = 13107202;BA.debugLine="notePnl.Visible = False";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13107203;
 //BA.debugLineNum = 13107203;BA.debugLine="R = 255";
_r = (int) (255);
RDebugUtils.currentLine=13107204;
 //BA.debugLineNum = 13107204;BA.debugLine="G = 105";
_g = (int) (105);
RDebugUtils.currentLine=13107205;
 //BA.debugLineNum = 13107205;BA.debugLine="B = 97";
_b = (int) (97);
RDebugUtils.currentLine=13107206;
 //BA.debugLineNum = 13107206;BA.debugLine="stickyBtn.Enabled = True";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13107207;
 //BA.debugLineNum = 13107207;BA.debugLine="canvaBtn.Enabled = True";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13107208;
 //BA.debugLineNum = 13107208;BA.debugLine="imgBtn.Enabled = True";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13107209;
 //BA.debugLineNum = 13107209;BA.debugLine="End Sub";
return "";
}
public static String  _canvabtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canvabtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canvabtn_click", null));}
RDebugUtils.currentLine=13369344;
 //BA.debugLineNum = 13369344;BA.debugLine="Private Sub canvaBtn_Click";
RDebugUtils.currentLine=13369345;
 //BA.debugLineNum = 13369345;BA.debugLine="canvasWindow(250dip, 180dip)";
_canvaswindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=13369346;
 //BA.debugLineNum = 13369346;BA.debugLine="canvasPnl.Visible = True";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13369347;
 //BA.debugLineNum = 13369347;BA.debugLine="penSpnr.Visible = True";
mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13369348;
 //BA.debugLineNum = 13369348;BA.debugLine="stickyBtn.Enabled = False";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13369349;
 //BA.debugLineNum = 13369349;BA.debugLine="canvaBtn.Enabled = False";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13369350;
 //BA.debugLineNum = 13369350;BA.debugLine="imgBtn.Enabled = False";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13369351;
 //BA.debugLineNum = 13369351;BA.debugLine="End Sub";
return "";
}
public static String  _canvaswindow(int _pw,int _ph) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canvaswindow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canvaswindow", new Object[] {_pw,_ph}));}
anywheresoftware.b4a.objects.ButtonWrapper _addcbtn = null;
RDebugUtils.currentLine=13041664;
 //BA.debugLineNum = 13041664;BA.debugLine="Private Sub canvasWindow(pW As Int, pH As Int)";
RDebugUtils.currentLine=13041665;
 //BA.debugLineNum = 13041665;BA.debugLine="canvasPnl = xui.CreatePanel(\"canvasPanel\")";
mostCurrent._canvaspnl = _xui.CreatePanel(processBA,"canvasPanel");
RDebugUtils.currentLine=13041666;
 //BA.debugLineNum = 13041666;BA.debugLine="Activity.AddView(canvasPnl, 100dip, 225dip, pW, p";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._canvaspnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),_pw,_ph);
RDebugUtils.currentLine=13041667;
 //BA.debugLineNum = 13041667;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=13041668;
 //BA.debugLineNum = 13041668;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_White, 2dip";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=13041671;
 //BA.debugLineNum = 13041671;BA.debugLine="sizeSpnr.Initialize(\"sizeSpnr\")";
mostCurrent._sizespnr.Initialize(mostCurrent.activityBA,"sizeSpnr");
RDebugUtils.currentLine=13041672;
 //BA.debugLineNum = 13041672;BA.debugLine="sizeSpnr.AddAll(Array As String(\"1x1\", \"2x1\", \"1x";
mostCurrent._sizespnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"1x1","2x1","1x2","2x2","3x2","2x3"}));
RDebugUtils.currentLine=13041673;
 //BA.debugLineNum = 13041673;BA.debugLine="canvasPnl.AddView(sizeSpnr, 10dip, 10dip, pW - 20";
mostCurrent._canvaspnl.AddView((android.view.View)(mostCurrent._sizespnr.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=13041675;
 //BA.debugLineNum = 13041675;BA.debugLine="Dim addcBtn As Button";
_addcbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=13041676;
 //BA.debugLineNum = 13041676;BA.debugLine="addcBtn.Initialize(\"addcBtn\")";
_addcbtn.Initialize(mostCurrent.activityBA,"addcBtn");
RDebugUtils.currentLine=13041677;
 //BA.debugLineNum = 13041677;BA.debugLine="addcBtn.Text = \"Add Canvas\"";
_addcbtn.setText(BA.ObjectToCharSequence("Add Canvas"));
RDebugUtils.currentLine=13041678;
 //BA.debugLineNum = 13041678;BA.debugLine="canvasPnl.AddView(addcBtn, 10dip, 60dip, (pW / 2)";
mostCurrent._canvaspnl.AddView((android.view.View)(_addcbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),(int) ((_pw/(double)2)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=13041680;
 //BA.debugLineNum = 13041680;BA.debugLine="canvasPnl.Enabled = False";
mostCurrent._canvaspnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13041681;
 //BA.debugLineNum = 13041681;BA.debugLine="canvasPnl.Visible = False";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13041682;
 //BA.debugLineNum = 13041682;BA.debugLine="End Sub";
return "";
}
public static void  _canvasdrag_placedview(anywheresoftware.b4a.objects.ConcreteViewWrapper _cdragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _cplaceview) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canvasdrag_placedview", false))
	 {Debug.delegate(mostCurrent.activityBA, "canvasdrag_placedview", new Object[] {_cdragview,_cplaceview}); return;}
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
RDebugUtils.currentModule="corkactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=13762561;
 //BA.debugLineNum = 13762561;BA.debugLine="If cPlaceView.Tag = \"delete\" Then";
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
RDebugUtils.currentLine=13762562;
 //BA.debugLineNum = 13762562;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete ca";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete canvas?"),BA.ObjectToCharSequence("Delete Canvas"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13762563;
 //BA.debugLineNum = 13762563;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "canvasdrag_placedview"), null);
this.state = 15;
return;
case 15:
//C
this.state = 4;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=13762564;
 //BA.debugLineNum = 13762564;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=13762565;
 //BA.debugLineNum = 13762565;BA.debugLine="Dim f As Panel = cDragView";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_cdragview.getObject()));
RDebugUtils.currentLine=13762566;
 //BA.debugLineNum = 13762566;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
RDebugUtils.currentLine=13762567;
 //BA.debugLineNum = 13762567;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png");
RDebugUtils.currentLine=13762568;
 //BA.debugLineNum = 13762568;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
RDebugUtils.currentLine=13762569;
 //BA.debugLineNum = 13762569;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
RDebugUtils.currentLine=13762570;
 //BA.debugLineNum = 13762570;BA.debugLine="Main.kvs.Remove(key & \"_w\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_w");
RDebugUtils.currentLine=13762571;
 //BA.debugLineNum = 13762571;BA.debugLine="Main.kvs.Remove(key & \"_h\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_h");
RDebugUtils.currentLine=13762572;
 //BA.debugLineNum = 13762572;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"cvs_count\")";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("cvs_count")));
RDebugUtils.currentLine=13762573;
 //BA.debugLineNum = 13762573;BA.debugLine="Main.kvs.Put(\"cvs_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("cvs_count",(Object)(_newcount-1));
RDebugUtils.currentLine=13762574;
 //BA.debugLineNum = 13762574;BA.debugLine="canvasCount = canvasCount - 1";
parent._canvascount = (int) (parent._canvascount-1);
RDebugUtils.currentLine=13762575;
 //BA.debugLineNum = 13762575;BA.debugLine="f.Visible = False";
_f.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13762576;
 //BA.debugLineNum = 13762576;BA.debugLine="ToastMessageShow(\"Canvas Deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Canvas Deleted"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13762577;
 //BA.debugLineNum = 13762577;BA.debugLine="If canvasCount = 0 Then";
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
RDebugUtils.currentLine=13762578;
 //BA.debugLineNum = 13762578;BA.debugLine="penSpnr.Visible = False";
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
RDebugUtils.currentLine=13762582;
 //BA.debugLineNum = 13762582;BA.debugLine="Dim f As Panel = cDragView";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_cdragview.getObject()));
RDebugUtils.currentLine=13762583;
 //BA.debugLineNum = 13762583;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
RDebugUtils.currentLine=13762584;
 //BA.debugLineNum = 13762584;BA.debugLine="Main.kvs.Put(key & \"_x\", f.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_f.getLeft()));
RDebugUtils.currentLine=13762585;
 //BA.debugLineNum = 13762585;BA.debugLine="Main.kvs.Put(key & \"_y\", f.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_f.getTop()));
 if (true) break;

case 14:
//C
this.state = -1;
;
RDebugUtils.currentLine=13762587;
 //BA.debugLineNum = 13762587;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _canvaspanel_touch(int _action,float _x,float _y) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canvaspanel_touch", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canvaspanel_touch", new Object[] {_action,_x,_y}));}
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
anywheresoftware.b4a.objects.PanelWrapper _f = null;
String _key = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
RDebugUtils.currentLine=12910592;
 //BA.debugLineNum = 12910592;BA.debugLine="Sub CanvasPanel_Touch (Action As Int, X As Float,";
RDebugUtils.currentLine=12910593;
 //BA.debugLineNum = 12910593;BA.debugLine="Dim p As Panel = Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=12910594;
 //BA.debugLineNum = 12910594;BA.debugLine="Dim cvs As B4XCanvas = p.Tag";
_cvs = (anywheresoftware.b4a.objects.B4XCanvas)(_p.getTag());
RDebugUtils.currentLine=12910595;
 //BA.debugLineNum = 12910595;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE)) {
case 0: {
RDebugUtils.currentLine=12910597;
 //BA.debugLineNum = 12910597;BA.debugLine="LastX = X";
_lastx = _x;
RDebugUtils.currentLine=12910598;
 //BA.debugLineNum = 12910598;BA.debugLine="LastY = Y";
_lasty = _y;
 break; }
case 1: {
RDebugUtils.currentLine=12910600;
 //BA.debugLineNum = 12910600;BA.debugLine="cvs.DrawLine(LastX, LastY, X, Y, Colors.RGB(R2,";
_cvs.DrawLine(_lastx,_lasty,_x,_y,anywheresoftware.b4a.keywords.Common.Colors.RGB(_r2,_g2,_b2),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))));
RDebugUtils.currentLine=12910601;
 //BA.debugLineNum = 12910601;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
RDebugUtils.currentLine=12910602;
 //BA.debugLineNum = 12910602;BA.debugLine="LastX = X";
_lastx = _x;
RDebugUtils.currentLine=12910603;
 //BA.debugLineNum = 12910603;BA.debugLine="LastY = Y";
_lasty = _y;
RDebugUtils.currentLine=12910604;
 //BA.debugLineNum = 12910604;BA.debugLine="Dim f As Panel = p.Parent";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getParent()));
RDebugUtils.currentLine=12910605;
 //BA.debugLineNum = 12910605;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
RDebugUtils.currentLine=12910606;
 //BA.debugLineNum = 12910606;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=12910607;
 //BA.debugLineNum = 12910607;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \"";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png",anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12910608;
 //BA.debugLineNum = 12910608;BA.debugLine="cvs.CreateBitmap.WriteToStream(out, 100, \"PNG\")";
_cvs.CreateBitmap().WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
RDebugUtils.currentLine=12910609;
 //BA.debugLineNum = 12910609;BA.debugLine="out.Close";
_out.Close();
 break; }
}
;
RDebugUtils.currentLine=12910611;
 //BA.debugLineNum = 12910611;BA.debugLine="End Sub";
return "";
}
public static String  _cc_result(boolean _success,String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cc_result", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cc_result", new Object[] {_success,_dir,_filename}));}
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
String _key = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
RDebugUtils.currentLine=12779520;
 //BA.debugLineNum = 12779520;BA.debugLine="Sub CC_Result (Success As Boolean, Dir As String,";
RDebugUtils.currentLine=12779521;
 //BA.debugLineNum = 12779521;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=12779522;
 //BA.debugLineNum = 12779522;BA.debugLine="imgView.Initialize(\"ImgView\")";
mostCurrent._imgview.Initialize(mostCurrent.activityBA,"ImgView");
RDebugUtils.currentLine=12779523;
 //BA.debugLineNum = 12779523;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
RDebugUtils.currentLine=12779524;
 //BA.debugLineNum = 12779524;BA.debugLine="bmp = LoadBitmapResize(Dir, FileName, 100dip, 10";
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmapResize(_dir,_filename,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=12779525;
 //BA.debugLineNum = 12779525;BA.debugLine="imgView.Bitmap = bmp";
mostCurrent._imgview.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=12779526;
 //BA.debugLineNum = 12779526;BA.debugLine="boardPnl.AddView(imgView, 150dip, 500dip, 100dip";
mostCurrent._boardpnl.AddView((android.view.View)(mostCurrent._imgview.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=12779527;
 //BA.debugLineNum = 12779527;BA.debugLine="ddi.AddDragView(imgView, False)";
mostCurrent._ddi._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._imgview.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12779528;
 //BA.debugLineNum = 12779528;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).Ad";
mostCurrent._ddi._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=12779529;
 //BA.debugLineNum = 12779529;BA.debugLine="Dim key As String = \"img_\" & imgCount";
_key = "img_"+BA.NumberToString(_imgcount);
RDebugUtils.currentLine=12779530;
 //BA.debugLineNum = 12779530;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=12779531;
 //BA.debugLineNum = 12779531;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \".";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png",anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12779532;
 //BA.debugLineNum = 12779532;BA.debugLine="bmp.WriteToStream(out, 100, \"PNG\")";
_bmp.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
RDebugUtils.currentLine=12779533;
 //BA.debugLineNum = 12779533;BA.debugLine="out.Close";
_out.Close();
RDebugUtils.currentLine=12779534;
 //BA.debugLineNum = 12779534;BA.debugLine="Main.kvs.Put(key & \"_file\", key & \".png\")";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_file",(Object)(_key+".png"));
RDebugUtils.currentLine=12779535;
 //BA.debugLineNum = 12779535;BA.debugLine="Main.kvs.Put(key & \"_x\", 150dip)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150))));
RDebugUtils.currentLine=12779536;
 //BA.debugLineNum = 12779536;BA.debugLine="Main.kvs.Put(key & \"_y\", 500dip)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500))));
RDebugUtils.currentLine=12779537;
 //BA.debugLineNum = 12779537;BA.debugLine="Main.kvs.Put(\"img_count\", imgCount + 1)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("img_count",(Object)(_imgcount+1));
RDebugUtils.currentLine=12779538;
 //BA.debugLineNum = 12779538;BA.debugLine="imgView.Tag = key";
mostCurrent._imgview.setTag((Object)(_key));
RDebugUtils.currentLine=12779539;
 //BA.debugLineNum = 12779539;BA.debugLine="imgCount = imgCount + 1";
_imgcount = (int) (_imgcount+1);
 }else {
RDebugUtils.currentLine=12779541;
 //BA.debugLineNum = 12779541;BA.debugLine="ToastMessageShow(\"No image selected\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No image selected"),anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=12779543;
 //BA.debugLineNum = 12779543;BA.debugLine="End Sub";
return "";
}
public static String  _colorsspnr_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "colorsspnr_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "colorsspnr_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=13500416;
 //BA.debugLineNum = 13500416;BA.debugLine="Private Sub colorsSpnr_ItemClick (Position As Int,";
RDebugUtils.currentLine=13500417;
 //BA.debugLineNum = 13500417;BA.debugLine="Select Position";
switch (_position) {
case 0: {
RDebugUtils.currentLine=13500419;
 //BA.debugLineNum = 13500419;BA.debugLine="R = 255";
_r = (int) (255);
RDebugUtils.currentLine=13500420;
 //BA.debugLineNum = 13500420;BA.debugLine="G = 105";
_g = (int) (105);
RDebugUtils.currentLine=13500421;
 //BA.debugLineNum = 13500421;BA.debugLine="B = 97";
_b = (int) (97);
 break; }
case 1: {
RDebugUtils.currentLine=13500423;
 //BA.debugLineNum = 13500423;BA.debugLine="R = 155";
_r = (int) (155);
RDebugUtils.currentLine=13500424;
 //BA.debugLineNum = 13500424;BA.debugLine="G = 190";
_g = (int) (190);
RDebugUtils.currentLine=13500425;
 //BA.debugLineNum = 13500425;BA.debugLine="B = 237";
_b = (int) (237);
 break; }
case 2: {
RDebugUtils.currentLine=13500427;
 //BA.debugLineNum = 13500427;BA.debugLine="R = 248";
_r = (int) (248);
RDebugUtils.currentLine=13500428;
 //BA.debugLineNum = 13500428;BA.debugLine="G = 241";
_g = (int) (241);
RDebugUtils.currentLine=13500429;
 //BA.debugLineNum = 13500429;BA.debugLine="B = 174";
_b = (int) (174);
 break; }
}
;
RDebugUtils.currentLine=13500431;
 //BA.debugLineNum = 13500431;BA.debugLine="End Sub";
return "";
}
public static String  _imgbtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "imgbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "imgbtn_click", null));}
RDebugUtils.currentLine=13303808;
 //BA.debugLineNum = 13303808;BA.debugLine="Private Sub imgBtn_Click";
RDebugUtils.currentLine=13303809;
 //BA.debugLineNum = 13303809;BA.debugLine="imgPicker.Show(\"image/*\", \"Select a Photo\")";
_imgpicker.Show(processBA,"image/*","Select a Photo");
RDebugUtils.currentLine=13303810;
 //BA.debugLineNum = 13303810;BA.debugLine="End Sub";
return "";
}
public static void  _imgdrag_placedview(anywheresoftware.b4a.objects.ConcreteViewWrapper _idragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _iplaceview) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "imgdrag_placedview", false))
	 {Debug.delegate(mostCurrent.activityBA, "imgdrag_placedview", new Object[] {_idragview,_iplaceview}); return;}
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
RDebugUtils.currentModule="corkactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=13697025;
 //BA.debugLineNum = 13697025;BA.debugLine="If iPlaceView.Tag = \"delete\" Then";
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
RDebugUtils.currentLine=13697026;
 //BA.debugLineNum = 13697026;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete im";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete image?"),BA.ObjectToCharSequence("Delete Image"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13697027;
 //BA.debugLineNum = 13697027;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "imgdrag_placedview"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=13697028;
 //BA.debugLineNum = 13697028;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=13697029;
 //BA.debugLineNum = 13697029;BA.debugLine="Dim iv As ImageView = iDragView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_idragview.getObject()));
RDebugUtils.currentLine=13697030;
 //BA.debugLineNum = 13697030;BA.debugLine="Dim key As String = iv.Tag";
_key = BA.ObjectToString(_iv.getTag());
RDebugUtils.currentLine=13697031;
 //BA.debugLineNum = 13697031;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png");
RDebugUtils.currentLine=13697032;
 //BA.debugLineNum = 13697032;BA.debugLine="Main.kvs.Remove(key & \"_file\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_file");
RDebugUtils.currentLine=13697033;
 //BA.debugLineNum = 13697033;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
RDebugUtils.currentLine=13697034;
 //BA.debugLineNum = 13697034;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
RDebugUtils.currentLine=13697035;
 //BA.debugLineNum = 13697035;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"img_count\")";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("img_count")));
RDebugUtils.currentLine=13697036;
 //BA.debugLineNum = 13697036;BA.debugLine="Main.kvs.Put(\"img_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("img_count",(Object)(_newcount-1));
RDebugUtils.currentLine=13697037;
 //BA.debugLineNum = 13697037;BA.debugLine="iv.Visible = False";
_iv.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13697038;
 //BA.debugLineNum = 13697038;BA.debugLine="ToastMessageShow(\"Image Deleted\", False)";
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
RDebugUtils.currentLine=13697041;
 //BA.debugLineNum = 13697041;BA.debugLine="Dim iv As ImageView = iDragView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_idragview.getObject()));
RDebugUtils.currentLine=13697042;
 //BA.debugLineNum = 13697042;BA.debugLine="Dim key As String = iv.Tag";
_key = BA.ObjectToString(_iv.getTag());
RDebugUtils.currentLine=13697043;
 //BA.debugLineNum = 13697043;BA.debugLine="Main.kvs.Put(key & \"_x\", iv.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_iv.getLeft()));
RDebugUtils.currentLine=13697044;
 //BA.debugLineNum = 13697044;BA.debugLine="Main.kvs.Put(key & \"_y\", iv.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_iv.getTop()));
 if (true) break;

case 10:
//C
this.state = -1;
;
RDebugUtils.currentLine=13697046;
 //BA.debugLineNum = 13697046;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _notedrag_placedview(anywheresoftware.b4a.objects.ConcreteViewWrapper _ndragview,anywheresoftware.b4a.objects.ConcreteViewWrapper _nplaceview) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notedrag_placedview", false))
	 {Debug.delegate(mostCurrent.activityBA, "notedrag_placedview", new Object[] {_ndragview,_nplaceview}); return;}
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
RDebugUtils.currentModule="corkactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=13631489;
 //BA.debugLineNum = 13631489;BA.debugLine="If nPlaceView.Tag = \"delete\" Then";
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
RDebugUtils.currentLine=13631490;
 //BA.debugLineNum = 13631490;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete no";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete note?"),BA.ObjectToCharSequence("Delete Note"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13631491;
 //BA.debugLineNum = 13631491;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "notedrag_placedview"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=13631492;
 //BA.debugLineNum = 13631492;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=13631493;
 //BA.debugLineNum = 13631493;BA.debugLine="Dim p As Panel = nDragView";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_ndragview.getObject()));
RDebugUtils.currentLine=13631494;
 //BA.debugLineNum = 13631494;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=13631495;
 //BA.debugLineNum = 13631495;BA.debugLine="Main.kvs.Remove(key & \"_text\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_text");
RDebugUtils.currentLine=13631496;
 //BA.debugLineNum = 13631496;BA.debugLine="Main.kvs.Remove(key & \"_color\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_color");
RDebugUtils.currentLine=13631497;
 //BA.debugLineNum = 13631497;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
RDebugUtils.currentLine=13631498;
 //BA.debugLineNum = 13631498;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
RDebugUtils.currentLine=13631499;
 //BA.debugLineNum = 13631499;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"note_count\"";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("note_count")));
RDebugUtils.currentLine=13631500;
 //BA.debugLineNum = 13631500;BA.debugLine="Main.kvs.Put(\"note_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("note_count",(Object)(_newcount-1));
RDebugUtils.currentLine=13631501;
 //BA.debugLineNum = 13631501;BA.debugLine="p.Visible = False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13631502;
 //BA.debugLineNum = 13631502;BA.debugLine="ToastMessageShow(\"Note Deleted\", False)";
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
RDebugUtils.currentLine=13631505;
 //BA.debugLineNum = 13631505;BA.debugLine="Dim p As Panel = nDragView";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_ndragview.getObject()));
RDebugUtils.currentLine=13631506;
 //BA.debugLineNum = 13631506;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=13631507;
 //BA.debugLineNum = 13631507;BA.debugLine="Main.kvs.Put(key & \"_x\", p.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_p.getLeft()));
RDebugUtils.currentLine=13631508;
 //BA.debugLineNum = 13631508;BA.debugLine="Main.kvs.Put(key & \"_y\", p.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_p.getTop()));
 if (true) break;

case 10:
//C
this.state = -1;
;
RDebugUtils.currentLine=13631510;
 //BA.debugLineNum = 13631510;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _notetext_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notetext_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notetext_textchanged", new Object[] {_old,_new}));}
anywheresoftware.b4a.objects.EditTextWrapper _txt = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
String _key = "";
RDebugUtils.currentLine=12713984;
 //BA.debugLineNum = 12713984;BA.debugLine="Sub NoteText_TextChanged(Old As String, New As Str";
RDebugUtils.currentLine=12713985;
 //BA.debugLineNum = 12713985;BA.debugLine="Dim txt As EditText = Sender";
_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
_txt = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=12713986;
 //BA.debugLineNum = 12713986;BA.debugLine="Dim p As Panel = txt.Tag";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_txt.getTag()));
RDebugUtils.currentLine=12713987;
 //BA.debugLineNum = 12713987;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=12713988;
 //BA.debugLineNum = 12713988;BA.debugLine="Main.kvs.Put(key & \"_text\", New)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_text",(Object)(_new));
RDebugUtils.currentLine=12713989;
 //BA.debugLineNum = 12713989;BA.debugLine="End Sub";
return "";
}
public static String  _notewindow(int _pw,int _ph) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notewindow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notewindow", new Object[] {_pw,_ph}));}
anywheresoftware.b4a.objects.SpinnerWrapper _colorsspnr = null;
anywheresoftware.b4a.objects.ButtonWrapper _addnbtn = null;
RDebugUtils.currentLine=12976128;
 //BA.debugLineNum = 12976128;BA.debugLine="Private Sub noteWindow(pW As Int, pH As Int)";
RDebugUtils.currentLine=12976129;
 //BA.debugLineNum = 12976129;BA.debugLine="notePnl = xui.CreatePanel(\"notePnl\")";
mostCurrent._notepnl = _xui.CreatePanel(processBA,"notePnl");
RDebugUtils.currentLine=12976130;
 //BA.debugLineNum = 12976130;BA.debugLine="Activity.AddView(notePnl, 100dip, 225dip, pW, pH)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._notepnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),_pw,_ph);
RDebugUtils.currentLine=12976131;
 //BA.debugLineNum = 12976131;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=12976132;
 //BA.debugLineNum = 12976132;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_White, 2dip,";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=12976134;
 //BA.debugLineNum = 12976134;BA.debugLine="Dim colorsSpnr As Spinner";
_colorsspnr = new anywheresoftware.b4a.objects.SpinnerWrapper();
RDebugUtils.currentLine=12976135;
 //BA.debugLineNum = 12976135;BA.debugLine="colorsSpnr.Initialize(\"colorsSpnr\")";
_colorsspnr.Initialize(mostCurrent.activityBA,"colorsSpnr");
RDebugUtils.currentLine=12976136;
 //BA.debugLineNum = 12976136;BA.debugLine="colorsSpnr.AddAll(Array As String(\"Red\", \"Blue\",";
_colorsspnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Red","Blue","Yellow"}));
RDebugUtils.currentLine=12976137;
 //BA.debugLineNum = 12976137;BA.debugLine="notePnl.AddView(colorsSpnr, 10dip, 10dip, pW - 20";
mostCurrent._notepnl.AddView((android.view.View)(_colorsspnr.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=12976139;
 //BA.debugLineNum = 12976139;BA.debugLine="Dim addnBtn As Button";
_addnbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=12976140;
 //BA.debugLineNum = 12976140;BA.debugLine="addnBtn.Initialize(\"addnBtn\")";
_addnbtn.Initialize(mostCurrent.activityBA,"addnBtn");
RDebugUtils.currentLine=12976141;
 //BA.debugLineNum = 12976141;BA.debugLine="addnBtn.Text = \"Add Note\"";
_addnbtn.setText(BA.ObjectToCharSequence("Add Note"));
RDebugUtils.currentLine=12976142;
 //BA.debugLineNum = 12976142;BA.debugLine="notePnl.AddView(addnBtn, 10dip, 60dip, (pW / 2) -";
mostCurrent._notepnl.AddView((android.view.View)(_addnbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),(int) ((_pw/(double)2)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=12976144;
 //BA.debugLineNum = 12976144;BA.debugLine="notePnl.Enabled = False";
mostCurrent._notepnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12976145;
 //BA.debugLineNum = 12976145;BA.debugLine="notePnl.Visible = False";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12976146;
 //BA.debugLineNum = 12976146;BA.debugLine="End Sub";
return "";
}
public static String  _penspnr_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "penspnr_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "penspnr_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=13565952;
 //BA.debugLineNum = 13565952;BA.debugLine="Private Sub penSpnr_ItemClick (Position As Int, Va";
RDebugUtils.currentLine=13565953;
 //BA.debugLineNum = 13565953;BA.debugLine="Select Position";
switch (_position) {
case 0: {
RDebugUtils.currentLine=13565955;
 //BA.debugLineNum = 13565955;BA.debugLine="R2 = 0";
_r2 = (int) (0);
RDebugUtils.currentLine=13565956;
 //BA.debugLineNum = 13565956;BA.debugLine="G2 = 0";
_g2 = (int) (0);
RDebugUtils.currentLine=13565957;
 //BA.debugLineNum = 13565957;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 1: {
RDebugUtils.currentLine=13565959;
 //BA.debugLineNum = 13565959;BA.debugLine="R2 = 0";
_r2 = (int) (0);
RDebugUtils.currentLine=13565960;
 //BA.debugLineNum = 13565960;BA.debugLine="G2 = 0";
_g2 = (int) (0);
RDebugUtils.currentLine=13565961;
 //BA.debugLineNum = 13565961;BA.debugLine="B2 = 255";
_b2 = (int) (255);
 break; }
case 2: {
RDebugUtils.currentLine=13565963;
 //BA.debugLineNum = 13565963;BA.debugLine="R2 = 0";
_r2 = (int) (0);
RDebugUtils.currentLine=13565964;
 //BA.debugLineNum = 13565964;BA.debugLine="G2 = 255";
_g2 = (int) (255);
RDebugUtils.currentLine=13565965;
 //BA.debugLineNum = 13565965;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 3: {
RDebugUtils.currentLine=13565967;
 //BA.debugLineNum = 13565967;BA.debugLine="R2 = 255";
_r2 = (int) (255);
RDebugUtils.currentLine=13565968;
 //BA.debugLineNum = 13565968;BA.debugLine="G2 = 0";
_g2 = (int) (0);
RDebugUtils.currentLine=13565969;
 //BA.debugLineNum = 13565969;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 4: {
RDebugUtils.currentLine=13565971;
 //BA.debugLineNum = 13565971;BA.debugLine="R2 = 255";
_r2 = (int) (255);
RDebugUtils.currentLine=13565972;
 //BA.debugLineNum = 13565972;BA.debugLine="G2 = 255";
_g2 = (int) (255);
RDebugUtils.currentLine=13565973;
 //BA.debugLineNum = 13565973;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 5: {
RDebugUtils.currentLine=13565975;
 //BA.debugLineNum = 13565975;BA.debugLine="R2 = 255";
_r2 = (int) (255);
RDebugUtils.currentLine=13565976;
 //BA.debugLineNum = 13565976;BA.debugLine="G2 = 255";
_g2 = (int) (255);
RDebugUtils.currentLine=13565977;
 //BA.debugLineNum = 13565977;BA.debugLine="B2 = 255";
_b2 = (int) (255);
 break; }
}
;
RDebugUtils.currentLine=13565979;
 //BA.debugLineNum = 13565979;BA.debugLine="End Sub";
return "";
}
public static String  _sizespnr_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sizespnr_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sizespnr_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=13434880;
 //BA.debugLineNum = 13434880;BA.debugLine="Private Sub sizeSpnr_ItemClick (Position As Int, V";
RDebugUtils.currentLine=13434881;
 //BA.debugLineNum = 13434881;BA.debugLine="Select Position";
switch (_position) {
case 0: {
RDebugUtils.currentLine=13434883;
 //BA.debugLineNum = 13434883;BA.debugLine="Width = 80dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=13434884;
 //BA.debugLineNum = 13434884;BA.debugLine="Height = 60dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 break; }
case 1: {
RDebugUtils.currentLine=13434886;
 //BA.debugLineNum = 13434886;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
RDebugUtils.currentLine=13434887;
 //BA.debugLineNum = 13434887;BA.debugLine="Height = 60dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 break; }
case 2: {
RDebugUtils.currentLine=13434889;
 //BA.debugLineNum = 13434889;BA.debugLine="Width = 80dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=13434890;
 //BA.debugLineNum = 13434890;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 3: {
RDebugUtils.currentLine=13434892;
 //BA.debugLineNum = 13434892;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
RDebugUtils.currentLine=13434893;
 //BA.debugLineNum = 13434893;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 4: {
RDebugUtils.currentLine=13434895;
 //BA.debugLineNum = 13434895;BA.debugLine="Width = 330dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
RDebugUtils.currentLine=13434896;
 //BA.debugLineNum = 13434896;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 5: {
RDebugUtils.currentLine=13434898;
 //BA.debugLineNum = 13434898;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
RDebugUtils.currentLine=13434899;
 //BA.debugLineNum = 13434899;BA.debugLine="Height = 310dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (310));
 break; }
}
;
RDebugUtils.currentLine=13434901;
 //BA.debugLineNum = 13434901;BA.debugLine="End Sub";
return "";
}
public static String  _stickybtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "stickybtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "stickybtn_click", null));}
RDebugUtils.currentLine=13238272;
 //BA.debugLineNum = 13238272;BA.debugLine="Private Sub stickyBtn_Click";
RDebugUtils.currentLine=13238273;
 //BA.debugLineNum = 13238273;BA.debugLine="noteWindow(250dip, 180dip)";
_notewindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=13238274;
 //BA.debugLineNum = 13238274;BA.debugLine="notePnl.Visible = True";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=13238275;
 //BA.debugLineNum = 13238275;BA.debugLine="stickyBtn.Enabled = False";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13238276;
 //BA.debugLineNum = 13238276;BA.debugLine="canvaBtn.Enabled = False";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13238277;
 //BA.debugLineNum = 13238277;BA.debugLine="imgBtn.Enabled = False";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=13238278;
 //BA.debugLineNum = 13238278;BA.debugLine="End Sub";
return "";
}
}