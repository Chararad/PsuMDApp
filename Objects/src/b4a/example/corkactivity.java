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
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _pixeltf = null;
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
RDebugUtils.currentLine=16646145;
 //BA.debugLineNum = 16646145;BA.debugLine="Select Starter.themeNumber";
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
RDebugUtils.currentLine=16646147;
 //BA.debugLineNum = 16646147;BA.debugLine="If Starter.darkMode = False Then";
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
RDebugUtils.currentLine=16646148;
 //BA.debugLineNum = 16646148;BA.debugLine="Activity.LoadLayout(\"corkboardLayout\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout",mostCurrent.activityBA);
 if (true) break;

case 8:
//C
this.state = 9;
RDebugUtils.currentLine=16646150;
 //BA.debugLineNum = 16646150;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark\")";
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
RDebugUtils.currentLine=16646153;
 //BA.debugLineNum = 16646153;BA.debugLine="If Starter.darkMode = False Then";
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
RDebugUtils.currentLine=16646154;
 //BA.debugLineNum = 16646154;BA.debugLine="Activity.LoadLayout(\"corkboardLayout2\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout2",mostCurrent.activityBA);
 if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=16646156;
 //BA.debugLineNum = 16646156;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark2\")";
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
RDebugUtils.currentLine=16646159;
 //BA.debugLineNum = 16646159;BA.debugLine="If Starter.darkMode = False Then";
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
RDebugUtils.currentLine=16646160;
 //BA.debugLineNum = 16646160;BA.debugLine="Activity.LoadLayout(\"corkboardLayout3\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayout3",mostCurrent.activityBA);
 if (true) break;

case 24:
//C
this.state = 25;
RDebugUtils.currentLine=16646162;
 //BA.debugLineNum = 16646162;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark3\")";
parent.mostCurrent._activity.LoadLayout("corkboardLayoutDark3",mostCurrent.activityBA);
 if (true) break;

case 25:
//C
this.state = 26;
;
 if (true) break;
;
RDebugUtils.currentLine=16646166;
 //BA.debugLineNum = 16646166;BA.debugLine="If Starter.darkMode = False Then";

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
RDebugUtils.currentLine=16646167;
 //BA.debugLineNum = 16646167;BA.debugLine="penSpnr.DropdownBackgroundColor = Colors.DarkGra";
parent.mostCurrent._penspnr.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=16646168;
 //BA.debugLineNum = 16646168;BA.debugLine="penSpnr.DropdownTextColor = Colors.White";
parent.mostCurrent._penspnr.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 if (true) break;

case 30:
//C
this.state = 31;
RDebugUtils.currentLine=16646170;
 //BA.debugLineNum = 16646170;BA.debugLine="penSpnr.DropdownTextColor = Colors.Black";
parent.mostCurrent._penspnr.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 if (true) break;

case 31:
//C
this.state = 32;
;
RDebugUtils.currentLine=16646173;
 //BA.debugLineNum = 16646173;BA.debugLine="penSpnr.AddAll(Array As String(\"Black\", \"Blue\", \"";
parent.mostCurrent._penspnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Black","Blue","Green","Red","Yellow","Eraser"}));
RDebugUtils.currentLine=16646174;
 //BA.debugLineNum = 16646174;BA.debugLine="If FirstTime Then";
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
RDebugUtils.currentLine=16646175;
 //BA.debugLineNum = 16646175;BA.debugLine="imgPicker.Initialize(\"CC\")";
parent._imgpicker.Initialize("CC");
 if (true) break;

case 35:
//C
this.state = 36;
;
RDebugUtils.currentLine=16646177;
 //BA.debugLineNum = 16646177;BA.debugLine="penSpnr.Visible = False";
parent.mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16646178;
 //BA.debugLineNum = 16646178;BA.debugLine="Width = 80dip";
parent._width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=16646179;
 //BA.debugLineNum = 16646179;BA.debugLine="Height = 60dip";
parent._height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=16646180;
 //BA.debugLineNum = 16646180;BA.debugLine="ddn.Initialize(Me, \"NoteDrag\")";
parent.mostCurrent._ddn._initialize(processBA,corkactivity.getObject(),"NoteDrag");
RDebugUtils.currentLine=16646181;
 //BA.debugLineNum = 16646181;BA.debugLine="ddi.Initialize(Me, \"ImgDrag\")";
parent.mostCurrent._ddi._initialize(processBA,corkactivity.getObject(),"ImgDrag");
RDebugUtils.currentLine=16646182;
 //BA.debugLineNum = 16646182;BA.debugLine="ddc.Initialize(Me, \"CanvasDrag\")";
parent.mostCurrent._ddc._initialize(processBA,corkactivity.getObject(),"CanvasDrag");
RDebugUtils.currentLine=16646184;
 //BA.debugLineNum = 16646184;BA.debugLine="If Main.kvs.IsInitialized = False Then";
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
RDebugUtils.currentLine=16646185;
 //BA.debugLineNum = 16646185;BA.debugLine="Main.kvs.Initialize(File.DirInternal, \"notes_dat";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"notes_data");
 if (true) break;

case 39:
//C
this.state = 40;
;
RDebugUtils.currentLine=16646188;
 //BA.debugLineNum = 16646188;BA.debugLine="isLoading = True";
parent._isloading = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=16646190;
 //BA.debugLineNum = 16646190;BA.debugLine="If Main.kvs.ContainsKey(\"note_count\") Then noteCo";
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
RDebugUtils.currentLine=16646191;
 //BA.debugLineNum = 16646191;BA.debugLine="Dim loadedNoteCount As Int = noteCount";
_loadednotecount = parent._notecount;
RDebugUtils.currentLine=16646192;
 //BA.debugLineNum = 16646192;BA.debugLine="For i = 0 To loadedNoteCount - 1";
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
RDebugUtils.currentLine=16646193;
 //BA.debugLineNum = 16646193;BA.debugLine="Dim nkey As String = \"note_\" & i";
_nkey = "note_"+BA.NumberToString(_i);
RDebugUtils.currentLine=16646194;
 //BA.debugLineNum = 16646194;BA.debugLine="If Main.kvs.ContainsKey(nkey & \"_text\") Then";
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
RDebugUtils.currentLine=16646195;
 //BA.debugLineNum = 16646195;BA.debugLine="Dim savedColor As Int = Main.kvs.Get(nkey & \"_c";
_savedcolor = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_color")));
RDebugUtils.currentLine=16646196;
 //BA.debugLineNum = 16646196;BA.debugLine="R = Bit.And(Bit.ShiftRight(savedColor, 16), 0xF";
parent._r = anywheresoftware.b4a.keywords.Common.Bit.And(anywheresoftware.b4a.keywords.Common.Bit.ShiftRight(_savedcolor,(int) (16)),((int)0xff));
RDebugUtils.currentLine=16646197;
 //BA.debugLineNum = 16646197;BA.debugLine="G = Bit.And(Bit.ShiftRight(savedColor, 8), 0xFF";
parent._g = anywheresoftware.b4a.keywords.Common.Bit.And(anywheresoftware.b4a.keywords.Common.Bit.ShiftRight(_savedcolor,(int) (8)),((int)0xff));
RDebugUtils.currentLine=16646198;
 //BA.debugLineNum = 16646198;BA.debugLine="B = Bit.And(savedColor, 0xFF)";
parent._b = anywheresoftware.b4a.keywords.Common.Bit.And(_savedcolor,((int)0xff));
RDebugUtils.currentLine=16646199;
 //BA.debugLineNum = 16646199;BA.debugLine="noteCount = i + 1";
parent._notecount = (int) (_i+1);
RDebugUtils.currentLine=16646200;
 //BA.debugLineNum = 16646200;BA.debugLine="AddStickyNote(Main.kvs.Get(nkey & \"_text\"), Mai";
_addstickynote(BA.ObjectToString(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_text")),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_nkey+"_y"))));
 if (true) break;

case 54:
//C
this.state = 91;
;
 if (true) break;
if (true) break;
;
RDebugUtils.currentLine=16646204;
 //BA.debugLineNum = 16646204;BA.debugLine="If Main.kvs.ContainsKey(\"img_count\") Then imgCoun";

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
RDebugUtils.currentLine=16646205;
 //BA.debugLineNum = 16646205;BA.debugLine="Dim loadedImgCount As Int = imgCount";
_loadedimgcount = parent._imgcount;
RDebugUtils.currentLine=16646206;
 //BA.debugLineNum = 16646206;BA.debugLine="For i = 0 To loadedImgCount - 1";
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
RDebugUtils.currentLine=16646207;
 //BA.debugLineNum = 16646207;BA.debugLine="Dim ikey As String = \"img_\" & i";
_ikey = "img_"+BA.NumberToString(_i);
RDebugUtils.currentLine=16646208;
 //BA.debugLineNum = 16646208;BA.debugLine="If Main.kvs.ContainsKey(ikey & \"_file\") Then";
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
RDebugUtils.currentLine=16646209;
 //BA.debugLineNum = 16646209;BA.debugLine="Dim iv As ImageView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=16646210;
 //BA.debugLineNum = 16646210;BA.debugLine="iv.Initialize(\"ImgView\")";
_iv.Initialize(mostCurrent.activityBA,"ImgView");
RDebugUtils.currentLine=16646211;
 //BA.debugLineNum = 16646211;BA.debugLine="boardPnl.AddView(iv, Main.kvs.Get(ikey & \"_x\"),";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_iv.getObject()),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_y"))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=16646212;
 //BA.debugLineNum = 16646212;BA.debugLine="iv.Bitmap = LoadBitmapResize(File.DirInternal,";
_iv.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),BA.ObjectToString(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ikey+"_file")),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=16646213;
 //BA.debugLineNum = 16646213;BA.debugLine="iv.Tag = ikey";
_iv.setTag((Object)(_ikey));
RDebugUtils.currentLine=16646214;
 //BA.debugLineNum = 16646214;BA.debugLine="ddi.AddDragView(iv, False)";
parent.mostCurrent._ddi._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16646215;
 //BA.debugLineNum = 16646215;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).A";
parent.mostCurrent._ddi._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
 if (true) break;

case 69:
//C
this.state = 93;
;
 if (true) break;
if (true) break;
;
RDebugUtils.currentLine=16646219;
 //BA.debugLineNum = 16646219;BA.debugLine="If Main.kvs.ContainsKey(\"cvs_count\") Then canvasC";

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
RDebugUtils.currentLine=16646220;
 //BA.debugLineNum = 16646220;BA.debugLine="Dim loadedCanvasCount As Int = canvasCount";
_loadedcanvascount = parent._canvascount;
RDebugUtils.currentLine=16646221;
 //BA.debugLineNum = 16646221;BA.debugLine="For i = 0 To loadedCanvasCount - 1";
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
RDebugUtils.currentLine=16646222;
 //BA.debugLineNum = 16646222;BA.debugLine="Dim ckey As String = \"cvs_\" & i";
_ckey = "cvs_"+BA.NumberToString(_i);
RDebugUtils.currentLine=16646223;
 //BA.debugLineNum = 16646223;BA.debugLine="If Main.kvs.ContainsKey(ckey & \"_x\") Then";
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
RDebugUtils.currentLine=16646224;
 //BA.debugLineNum = 16646224;BA.debugLine="Width = Main.kvs.Get(ckey & \"_w\")";
parent._width = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_w")));
RDebugUtils.currentLine=16646225;
 //BA.debugLineNum = 16646225;BA.debugLine="Height = Main.kvs.Get(ckey & \"_h\")";
parent._height = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_h")));
RDebugUtils.currentLine=16646227;
 //BA.debugLineNum = 16646227;BA.debugLine="Dim f As Panel";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=16646228;
 //BA.debugLineNum = 16646228;BA.debugLine="f.Initialize(\"CanvasFrame\")";
_f.Initialize(mostCurrent.activityBA,"CanvasFrame");
RDebugUtils.currentLine=16646229;
 //BA.debugLineNum = 16646229;BA.debugLine="f.Color = Colors.Black";
_f.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=16646230;
 //BA.debugLineNum = 16646230;BA.debugLine="boardPnl.AddView(f, Main.kvs.Get(ckey & \"_x\"),";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_f.getObject()),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_x"))),(int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get(_ckey+"_y"))),(int) (parent._width+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (parent._height+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
RDebugUtils.currentLine=16646231;
 //BA.debugLineNum = 16646231;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=16646232;
 //BA.debugLineNum = 16646232;BA.debugLine="p.Initialize(\"CanvasPanel\")";
_p.Initialize(mostCurrent.activityBA,"CanvasPanel");
RDebugUtils.currentLine=16646233;
 //BA.debugLineNum = 16646233;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=16646234;
 //BA.debugLineNum = 16646234;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
_f.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),parent._width,parent._height);
RDebugUtils.currentLine=16646235;
 //BA.debugLineNum = 16646235;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "activity_create"),(int) (0));
this.state = 96;
return;
case 96:
//C
this.state = 84;
;
RDebugUtils.currentLine=16646236;
 //BA.debugLineNum = 16646236;BA.debugLine="Dim cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
RDebugUtils.currentLine=16646237;
 //BA.debugLineNum = 16646237;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())));
RDebugUtils.currentLine=16646238;
 //BA.debugLineNum = 16646238;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray,";
_cvs.DrawRect(_cvs.getTargetRect(),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
RDebugUtils.currentLine=16646239;
 //BA.debugLineNum = 16646239;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
RDebugUtils.currentLine=16646240;
 //BA.debugLineNum = 16646240;BA.debugLine="p.Tag = cvs";
_p.setTag((Object)(_cvs));
RDebugUtils.currentLine=16646241;
 //BA.debugLineNum = 16646241;BA.debugLine="ddc.AddDragView(f, False)";
parent.mostCurrent._ddc._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_f.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16646242;
 //BA.debugLineNum = 16646242;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).A";
parent.mostCurrent._ddc._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=16646243;
 //BA.debugLineNum = 16646243;BA.debugLine="f.Tag = ckey";
_f.setTag((Object)(_ckey));
RDebugUtils.currentLine=16646245;
 //BA.debugLineNum = 16646245;BA.debugLine="If File.Exists(File.DirInternal, ckey & \".png\")";
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
RDebugUtils.currentLine=16646246;
 //BA.debugLineNum = 16646246;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "activity_create"),(int) (0));
this.state = 97;
return;
case 97:
//C
this.state = 87;
;
RDebugUtils.currentLine=16646247;
 //BA.debugLineNum = 16646247;BA.debugLine="Dim bmp As Bitmap = LoadBitmap(File.DirInterna";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_ckey+".png");
RDebugUtils.currentLine=16646248;
 //BA.debugLineNum = 16646248;BA.debugLine="Dim canvasRect As B4XRect = cvs.TargetRect";
_canvasrect = _cvs.getTargetRect();
RDebugUtils.currentLine=16646249;
 //BA.debugLineNum = 16646249;BA.debugLine="cvs.DrawBitmap(bmp, canvasRect)";
_cvs.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),_canvasrect);
RDebugUtils.currentLine=16646250;
 //BA.debugLineNum = 16646250;BA.debugLine="cvs.Invalidate";
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
RDebugUtils.currentLine=16646254;
 //BA.debugLineNum = 16646254;BA.debugLine="canvasCount = loadedCanvasCount";
parent._canvascount = _loadedcanvascount;
RDebugUtils.currentLine=16646255;
 //BA.debugLineNum = 16646255;BA.debugLine="Width = 80dip";
parent._width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=16646256;
 //BA.debugLineNum = 16646256;BA.debugLine="Height = 60dip";
parent._height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
RDebugUtils.currentLine=16646257;
 //BA.debugLineNum = 16646257;BA.debugLine="isLoading = False";
parent._isloading = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=16646258;
 //BA.debugLineNum = 16646258;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=16842752;
 //BA.debugLineNum = 16842752;BA.debugLine="Sub AddStickyNote(Text As String, x As Int, y As I";
RDebugUtils.currentLine=16842753;
 //BA.debugLineNum = 16842753;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=16842754;
 //BA.debugLineNum = 16842754;BA.debugLine="p.Initialize(\"NotePanel\")";
_p.Initialize(mostCurrent.activityBA,"NotePanel");
RDebugUtils.currentLine=16842755;
 //BA.debugLineNum = 16842755;BA.debugLine="p.Color = Colors.RGB(R, G, B)";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB(_r,_g,_b));
RDebugUtils.currentLine=16842757;
 //BA.debugLineNum = 16842757;BA.debugLine="Dim txt As EditText";
_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=16842758;
 //BA.debugLineNum = 16842758;BA.debugLine="txt.Initialize(\"NoteText\")";
_txt.Initialize(mostCurrent.activityBA,"NoteText");
RDebugUtils.currentLine=16842759;
 //BA.debugLineNum = 16842759;BA.debugLine="txt.Tag = p";
_txt.setTag((Object)(_p.getObject()));
RDebugUtils.currentLine=16842760;
 //BA.debugLineNum = 16842760;BA.debugLine="txt.Text = Text";
_txt.setText(BA.ObjectToCharSequence(_text));
RDebugUtils.currentLine=16842761;
 //BA.debugLineNum = 16842761;BA.debugLine="txt.TextSize = 12";
_txt.setTextSize((float) (12));
RDebugUtils.currentLine=16842762;
 //BA.debugLineNum = 16842762;BA.debugLine="txt.Background = Null";
_txt.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=16842763;
 //BA.debugLineNum = 16842763;BA.debugLine="txt.TextColor = Colors.Black";
_txt.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=16842764;
 //BA.debugLineNum = 16842764;BA.debugLine="txt.Gravity = Gravity.TOP";
_txt.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
RDebugUtils.currentLine=16842766;
 //BA.debugLineNum = 16842766;BA.debugLine="p.AddView(txt, 5dip, 15dip, 90dip, 70dip)";
_p.AddView((android.view.View)(_txt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=16842768;
 //BA.debugLineNum = 16842768;BA.debugLine="boardPnl.AddView(p, x, y, 100dip, 100dip)";
mostCurrent._boardpnl.AddView((android.view.View)(_p.getObject()),_x,_y,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=16842770;
 //BA.debugLineNum = 16842770;BA.debugLine="ddn.AddDragView(p, False)";
mostCurrent._ddn._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16842771;
 //BA.debugLineNum = 16842771;BA.debugLine="ddn.AddPlaceView(place1).AddPlaceView(place2).Add";
mostCurrent._ddn._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=16842773;
 //BA.debugLineNum = 16842773;BA.debugLine="If isLoading = False Then";
if (_isloading==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=16842774;
 //BA.debugLineNum = 16842774;BA.debugLine="Dim key As String = \"note_\" & noteCount";
_key = "note_"+BA.NumberToString(_notecount);
RDebugUtils.currentLine=16842775;
 //BA.debugLineNum = 16842775;BA.debugLine="Main.kvs.Put(key & \"_text\", Text)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_text",(Object)(_text));
RDebugUtils.currentLine=16842776;
 //BA.debugLineNum = 16842776;BA.debugLine="Main.kvs.Put(key & \"_color\", Colors.RGB(R, G, B)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_color",(Object)(anywheresoftware.b4a.keywords.Common.Colors.RGB(_r,_g,_b)));
RDebugUtils.currentLine=16842777;
 //BA.debugLineNum = 16842777;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_x));
RDebugUtils.currentLine=16842778;
 //BA.debugLineNum = 16842778;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_y));
RDebugUtils.currentLine=16842779;
 //BA.debugLineNum = 16842779;BA.debugLine="Main.kvs.Put(\"note_count\", noteCount + 1)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("note_count",(Object)(_notecount+1));
RDebugUtils.currentLine=16842780;
 //BA.debugLineNum = 16842780;BA.debugLine="p.Tag = key";
_p.setTag((Object)(_key));
RDebugUtils.currentLine=16842781;
 //BA.debugLineNum = 16842781;BA.debugLine="noteCount = noteCount + 1";
_notecount = (int) (_notecount+1);
 }else {
RDebugUtils.currentLine=16842783;
 //BA.debugLineNum = 16842783;BA.debugLine="p.Tag = \"note_\" & (noteCount - 1)";
_p.setTag((Object)("note_"+BA.NumberToString((_notecount-1))));
 };
RDebugUtils.currentLine=16842785;
 //BA.debugLineNum = 16842785;BA.debugLine="Log(\"deleteLbl initialized: \" & (deleteLbl.IsInit";
anywheresoftware.b4a.keywords.Common.LogImpl("416842785","deleteLbl initialized: "+BA.ObjectToString((mostCurrent._deletelbl.IsInitialized())),0);
RDebugUtils.currentLine=16842786;
 //BA.debugLineNum = 16842786;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="corkactivity";
RDebugUtils.currentLine=16777216;
 //BA.debugLineNum = 16777216;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=16777218;
 //BA.debugLineNum = 16777218;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=16711680;
 //BA.debugLineNum = 16711680;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=16711681;
 //BA.debugLineNum = 16711681;BA.debugLine="If canvasCount > 0 Then";
if (_canvascount>0) { 
RDebugUtils.currentLine=16711682;
 //BA.debugLineNum = 16711682;BA.debugLine="penSpnr.Visible = True";
mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=16711684;
 //BA.debugLineNum = 16711684;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=17039361;
 //BA.debugLineNum = 17039361;BA.debugLine="Dim f As Panel";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=17039362;
 //BA.debugLineNum = 17039362;BA.debugLine="f.Initialize(\"CanvasFrame\")";
_f.Initialize(mostCurrent.activityBA,"CanvasFrame");
RDebugUtils.currentLine=17039363;
 //BA.debugLineNum = 17039363;BA.debugLine="f.Color = Colors.Black";
_f.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=17039364;
 //BA.debugLineNum = 17039364;BA.debugLine="boardPnl.AddView(f, x, y, Width + 20dip, Height +";
parent.mostCurrent._boardpnl.AddView((android.view.View)(_f.getObject()),_x,_y,(int) (parent._width+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (parent._height+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
RDebugUtils.currentLine=17039366;
 //BA.debugLineNum = 17039366;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=17039367;
 //BA.debugLineNum = 17039367;BA.debugLine="p.Initialize(\"CanvasPanel\")";
_p.Initialize(mostCurrent.activityBA,"CanvasPanel");
RDebugUtils.currentLine=17039368;
 //BA.debugLineNum = 17039368;BA.debugLine="p.Color = Colors.White";
_p.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=17039369;
 //BA.debugLineNum = 17039369;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
_f.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),parent._width,parent._height);
RDebugUtils.currentLine=17039371;
 //BA.debugLineNum = 17039371;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "addcanvas"),(int) (0));
this.state = 7;
return;
case 7:
//C
this.state = 1;
;
RDebugUtils.currentLine=17039373;
 //BA.debugLineNum = 17039373;BA.debugLine="Dim cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
RDebugUtils.currentLine=17039374;
 //BA.debugLineNum = 17039374;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject())));
RDebugUtils.currentLine=17039375;
 //BA.debugLineNum = 17039375;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray, Fa";
_cvs.DrawRect(_cvs.getTargetRect(),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
RDebugUtils.currentLine=17039376;
 //BA.debugLineNum = 17039376;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
RDebugUtils.currentLine=17039377;
 //BA.debugLineNum = 17039377;BA.debugLine="p.Tag = cvs";
_p.setTag((Object)(_cvs));
RDebugUtils.currentLine=17039379;
 //BA.debugLineNum = 17039379;BA.debugLine="ddc.AddDragView(f, False)";
parent.mostCurrent._ddc._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_f.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17039380;
 //BA.debugLineNum = 17039380;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).Add";
parent.mostCurrent._ddc._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=17039381;
 //BA.debugLineNum = 17039381;BA.debugLine="If isLoading = False Then";
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
RDebugUtils.currentLine=17039382;
 //BA.debugLineNum = 17039382;BA.debugLine="Dim key As String = \"cvs_\" & canvasCount";
_key = "cvs_"+BA.NumberToString(parent._canvascount);
RDebugUtils.currentLine=17039383;
 //BA.debugLineNum = 17039383;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_x));
RDebugUtils.currentLine=17039384;
 //BA.debugLineNum = 17039384;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_y));
RDebugUtils.currentLine=17039385;
 //BA.debugLineNum = 17039385;BA.debugLine="Main.kvs.Put(key & \"_w\", Width)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_w",(Object)(parent._width));
RDebugUtils.currentLine=17039386;
 //BA.debugLineNum = 17039386;BA.debugLine="Main.kvs.Put(key & \"_h\", Height)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_h",(Object)(parent._height));
RDebugUtils.currentLine=17039387;
 //BA.debugLineNum = 17039387;BA.debugLine="Main.kvs.Put(\"cvs_count\", canvasCount + 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("cvs_count",(Object)(parent._canvascount+1));
RDebugUtils.currentLine=17039388;
 //BA.debugLineNum = 17039388;BA.debugLine="f.Tag = key";
_f.setTag((Object)(_key));
RDebugUtils.currentLine=17039389;
 //BA.debugLineNum = 17039389;BA.debugLine="canvasCount = canvasCount + 1";
parent._canvascount = (int) (parent._canvascount+1);
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=17039391;
 //BA.debugLineNum = 17039391;BA.debugLine="f.Tag = \"cvs_\" & (canvasCount - 1)";
_f.setTag((Object)("cvs_"+BA.NumberToString((parent._canvascount-1))));
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=17039393;
 //BA.debugLineNum = 17039393;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _addcbtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addcbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addcbtn_click", null));}
RDebugUtils.currentLine=17367040;
 //BA.debugLineNum = 17367040;BA.debugLine="Private Sub addcBtn_Click";
RDebugUtils.currentLine=17367041;
 //BA.debugLineNum = 17367041;BA.debugLine="AddCanvas(150dip, 500dip)";
_addcanvas(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)));
RDebugUtils.currentLine=17367042;
 //BA.debugLineNum = 17367042;BA.debugLine="canvasPnl.Visible = False";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17367043;
 //BA.debugLineNum = 17367043;BA.debugLine="canvaBtn.Enabled = True";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17367044;
 //BA.debugLineNum = 17367044;BA.debugLine="stickyBtn.Enabled = True";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17367045;
 //BA.debugLineNum = 17367045;BA.debugLine="imgBtn.Enabled = True";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17367046;
 //BA.debugLineNum = 17367046;BA.debugLine="End Sub";
return "";
}
public static String  _addnbtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addnbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addnbtn_click", null));}
RDebugUtils.currentLine=17301504;
 //BA.debugLineNum = 17301504;BA.debugLine="Private Sub addnBtn_Click";
RDebugUtils.currentLine=17301505;
 //BA.debugLineNum = 17301505;BA.debugLine="AddStickyNote(\"\", 150dip, 500dip)";
_addstickynote("",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)));
RDebugUtils.currentLine=17301506;
 //BA.debugLineNum = 17301506;BA.debugLine="notePnl.Visible = False";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17301507;
 //BA.debugLineNum = 17301507;BA.debugLine="R = 255";
_r = (int) (255);
RDebugUtils.currentLine=17301508;
 //BA.debugLineNum = 17301508;BA.debugLine="G = 105";
_g = (int) (105);
RDebugUtils.currentLine=17301509;
 //BA.debugLineNum = 17301509;BA.debugLine="B = 97";
_b = (int) (97);
RDebugUtils.currentLine=17301510;
 //BA.debugLineNum = 17301510;BA.debugLine="stickyBtn.Enabled = True";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17301511;
 //BA.debugLineNum = 17301511;BA.debugLine="canvaBtn.Enabled = True";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17301512;
 //BA.debugLineNum = 17301512;BA.debugLine="imgBtn.Enabled = True";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17301513;
 //BA.debugLineNum = 17301513;BA.debugLine="End Sub";
return "";
}
public static String  _canvabtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canvabtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canvabtn_click", null));}
RDebugUtils.currentLine=17563648;
 //BA.debugLineNum = 17563648;BA.debugLine="Private Sub canvaBtn_Click";
RDebugUtils.currentLine=17563649;
 //BA.debugLineNum = 17563649;BA.debugLine="canvasWindow(250dip, 180dip)";
_canvaswindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=17563650;
 //BA.debugLineNum = 17563650;BA.debugLine="canvasPnl.Visible = True";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17563651;
 //BA.debugLineNum = 17563651;BA.debugLine="penSpnr.Visible = True";
mostCurrent._penspnr.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17563652;
 //BA.debugLineNum = 17563652;BA.debugLine="stickyBtn.Enabled = False";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17563653;
 //BA.debugLineNum = 17563653;BA.debugLine="canvaBtn.Enabled = False";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17563654;
 //BA.debugLineNum = 17563654;BA.debugLine="imgBtn.Enabled = False";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17563655;
 //BA.debugLineNum = 17563655;BA.debugLine="End Sub";
return "";
}
public static String  _canvaswindow(int _pw,int _ph) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canvaswindow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canvaswindow", new Object[] {_pw,_ph}));}
anywheresoftware.b4a.objects.ButtonWrapper _addcbtn = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=17235968;
 //BA.debugLineNum = 17235968;BA.debugLine="Private Sub canvasWindow(pW As Int, pH As Int)";
RDebugUtils.currentLine=17235969;
 //BA.debugLineNum = 17235969;BA.debugLine="canvasPnl = xui.CreatePanel(\"canvasPanel\")";
mostCurrent._canvaspnl = _xui.CreatePanel(processBA,"canvasPanel");
RDebugUtils.currentLine=17235970;
 //BA.debugLineNum = 17235970;BA.debugLine="Activity.AddView(canvasPnl, 100dip, 225dip, pW, p";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._canvaspnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),_pw,_ph);
RDebugUtils.currentLine=17235974;
 //BA.debugLineNum = 17235974;BA.debugLine="sizeSpnr.Initialize(\"sizeSpnr\")";
mostCurrent._sizespnr.Initialize(mostCurrent.activityBA,"sizeSpnr");
RDebugUtils.currentLine=17235975;
 //BA.debugLineNum = 17235975;BA.debugLine="sizeSpnr.AddAll(Array As String(\"1x1\", \"2x1\", \"1x";
mostCurrent._sizespnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"1x1","2x1","1x2","2x2","3x2","2x3"}));
RDebugUtils.currentLine=17235976;
 //BA.debugLineNum = 17235976;BA.debugLine="canvasPnl.AddView(sizeSpnr, 10dip, 10dip, pW - 20";
mostCurrent._canvaspnl.AddView((android.view.View)(mostCurrent._sizespnr.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=17235978;
 //BA.debugLineNum = 17235978;BA.debugLine="Dim addcBtn As Button";
_addcbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17235979;
 //BA.debugLineNum = 17235979;BA.debugLine="addcBtn.Initialize(\"addcBtn\")";
_addcbtn.Initialize(mostCurrent.activityBA,"addcBtn");
RDebugUtils.currentLine=17235980;
 //BA.debugLineNum = 17235980;BA.debugLine="addcBtn.Text = \"Add Canvas\"";
_addcbtn.setText(BA.ObjectToCharSequence("Add Canvas"));
RDebugUtils.currentLine=17235981;
 //BA.debugLineNum = 17235981;BA.debugLine="canvasPnl.AddView(addcBtn, 10dip, 60dip, (pW / 2)";
mostCurrent._canvaspnl.AddView((android.view.View)(_addcbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),(int) ((_pw/(double)2)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=17235983;
 //BA.debugLineNum = 17235983;BA.debugLine="canvasPnl.Enabled = False";
mostCurrent._canvaspnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17235984;
 //BA.debugLineNum = 17235984;BA.debugLine="canvasPnl.Visible = False";
mostCurrent._canvaspnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17235986;
 //BA.debugLineNum = 17235986;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=17235987;
 //BA.debugLineNum = 17235987;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=17235988;
 //BA.debugLineNum = 17235988;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=17235990;
 //BA.debugLineNum = 17235990;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=17235991;
 //BA.debugLineNum = 17235991;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17235992;
 //BA.debugLineNum = 17235992;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(187,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (187),(int) (213),(int) (218)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17235993;
 //BA.debugLineNum = 17235993;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=17235994;
 //BA.debugLineNum = 17235994;BA.debugLine="addcBtn.Background = cd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=17235995;
 //BA.debugLineNum = 17235995;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=17235997;
 //BA.debugLineNum = 17235997;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17235998;
 //BA.debugLineNum = 17235998;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(227,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (227),(int) (193),(int) (159)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17235999;
 //BA.debugLineNum = 17235999;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=17236000;
 //BA.debugLineNum = 17236000;BA.debugLine="addcBtn.Background = cd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=17236001;
 //BA.debugLineNum = 17236001;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=17236004;
 //BA.debugLineNum = 17236004;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=17236005;
 //BA.debugLineNum = 17236005;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17236006;
 //BA.debugLineNum = 17236006;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(27,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (27),(int) (98),(int) (161)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (16),(int) (26),(int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17236007;
 //BA.debugLineNum = 17236007;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"dark";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dark Skeumorphic Button blue.png").getObject()));
RDebugUtils.currentLine=17236008;
 //BA.debugLineNum = 17236008;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17236009;
 //BA.debugLineNum = 17236009;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=17236011;
 //BA.debugLineNum = 17236011;BA.debugLine="canvasPnl.Color = xui.Color_RGB(255, 255, 255)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=17236012;
 //BA.debugLineNum = 17236012;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(51,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (51),(int) (187),(int) (152)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17236013;
 //BA.debugLineNum = 17236013;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Button 03.png").getObject()));
RDebugUtils.currentLine=17236014;
 //BA.debugLineNum = 17236014;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17236015;
 //BA.debugLineNum = 17236015;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=17236018;
 //BA.debugLineNum = 17236018;BA.debugLine="addcBtn.Typeface = pixeltf";
_addcbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=17236019;
 //BA.debugLineNum = 17236019;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=17236020;
 //BA.debugLineNum = 17236020;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17236021;
 //BA.debugLineNum = 17236021;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(37,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (37),(int) (57),(int) (94)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17236022;
 //BA.debugLineNum = 17236022;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
RDebugUtils.currentLine=17236023;
 //BA.debugLineNum = 17236023;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17236024;
 //BA.debugLineNum = 17236024;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=17236026;
 //BA.debugLineNum = 17236026;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._canvaspnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17236027;
 //BA.debugLineNum = 17236027;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(234,";
mostCurrent._canvaspnl.SetColorAndBorder(_xui.Color_RGB((int) (234),(int) (212),(int) (179)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (79),(int) (46),(int) (46)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17236028;
 //BA.debugLineNum = 17236028;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
RDebugUtils.currentLine=17236029;
 //BA.debugLineNum = 17236029;BA.debugLine="addcBtn.Background = bd";
_addcbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17236030;
 //BA.debugLineNum = 17236030;BA.debugLine="addcBtn.TextColor = Colors.White";
_addcbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
RDebugUtils.currentLine=17236034;
 //BA.debugLineNum = 17236034;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=17956865;
 //BA.debugLineNum = 17956865;BA.debugLine="If cPlaceView.Tag = \"delete\" Then";
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
RDebugUtils.currentLine=17956866;
 //BA.debugLineNum = 17956866;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete ca";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete canvas?"),BA.ObjectToCharSequence("Delete Canvas"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17956867;
 //BA.debugLineNum = 17956867;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "canvasdrag_placedview"), null);
this.state = 15;
return;
case 15:
//C
this.state = 4;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=17956868;
 //BA.debugLineNum = 17956868;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=17956869;
 //BA.debugLineNum = 17956869;BA.debugLine="Dim f As Panel = cDragView";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_cdragview.getObject()));
RDebugUtils.currentLine=17956870;
 //BA.debugLineNum = 17956870;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
RDebugUtils.currentLine=17956871;
 //BA.debugLineNum = 17956871;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png");
RDebugUtils.currentLine=17956872;
 //BA.debugLineNum = 17956872;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
RDebugUtils.currentLine=17956873;
 //BA.debugLineNum = 17956873;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
RDebugUtils.currentLine=17956874;
 //BA.debugLineNum = 17956874;BA.debugLine="Main.kvs.Remove(key & \"_w\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_w");
RDebugUtils.currentLine=17956875;
 //BA.debugLineNum = 17956875;BA.debugLine="Main.kvs.Remove(key & \"_h\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_h");
RDebugUtils.currentLine=17956876;
 //BA.debugLineNum = 17956876;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"cvs_count\")";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("cvs_count")));
RDebugUtils.currentLine=17956877;
 //BA.debugLineNum = 17956877;BA.debugLine="Main.kvs.Put(\"cvs_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("cvs_count",(Object)(_newcount-1));
RDebugUtils.currentLine=17956878;
 //BA.debugLineNum = 17956878;BA.debugLine="canvasCount = canvasCount - 1";
parent._canvascount = (int) (parent._canvascount-1);
RDebugUtils.currentLine=17956879;
 //BA.debugLineNum = 17956879;BA.debugLine="f.Visible = False";
_f.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17956880;
 //BA.debugLineNum = 17956880;BA.debugLine="ToastMessageShow(\"Canvas Deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Canvas Deleted"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17956881;
 //BA.debugLineNum = 17956881;BA.debugLine="If canvasCount = 0 Then";
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
RDebugUtils.currentLine=17956882;
 //BA.debugLineNum = 17956882;BA.debugLine="penSpnr.Visible = False";
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
RDebugUtils.currentLine=17956886;
 //BA.debugLineNum = 17956886;BA.debugLine="Dim f As Panel = cDragView";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_cdragview.getObject()));
RDebugUtils.currentLine=17956887;
 //BA.debugLineNum = 17956887;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
RDebugUtils.currentLine=17956888;
 //BA.debugLineNum = 17956888;BA.debugLine="Main.kvs.Put(key & \"_x\", f.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_f.getLeft()));
RDebugUtils.currentLine=17956889;
 //BA.debugLineNum = 17956889;BA.debugLine="Main.kvs.Put(key & \"_y\", f.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_f.getTop()));
 if (true) break;

case 14:
//C
this.state = -1;
;
RDebugUtils.currentLine=17956891;
 //BA.debugLineNum = 17956891;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=17104896;
 //BA.debugLineNum = 17104896;BA.debugLine="Sub CanvasPanel_Touch (Action As Int, X As Float,";
RDebugUtils.currentLine=17104897;
 //BA.debugLineNum = 17104897;BA.debugLine="Dim p As Panel = Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=17104898;
 //BA.debugLineNum = 17104898;BA.debugLine="Dim cvs As B4XCanvas = p.Tag";
_cvs = (anywheresoftware.b4a.objects.B4XCanvas)(_p.getTag());
RDebugUtils.currentLine=17104899;
 //BA.debugLineNum = 17104899;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE)) {
case 0: {
RDebugUtils.currentLine=17104901;
 //BA.debugLineNum = 17104901;BA.debugLine="LastX = X";
_lastx = _x;
RDebugUtils.currentLine=17104902;
 //BA.debugLineNum = 17104902;BA.debugLine="LastY = Y";
_lasty = _y;
 break; }
case 1: {
RDebugUtils.currentLine=17104904;
 //BA.debugLineNum = 17104904;BA.debugLine="cvs.DrawLine(LastX, LastY, X, Y, Colors.RGB(R2,";
_cvs.DrawLine(_lastx,_lasty,_x,_y,anywheresoftware.b4a.keywords.Common.Colors.RGB(_r2,_g2,_b2),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))));
RDebugUtils.currentLine=17104905;
 //BA.debugLineNum = 17104905;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
RDebugUtils.currentLine=17104906;
 //BA.debugLineNum = 17104906;BA.debugLine="LastX = X";
_lastx = _x;
RDebugUtils.currentLine=17104907;
 //BA.debugLineNum = 17104907;BA.debugLine="LastY = Y";
_lasty = _y;
RDebugUtils.currentLine=17104908;
 //BA.debugLineNum = 17104908;BA.debugLine="Dim f As Panel = p.Parent";
_f = new anywheresoftware.b4a.objects.PanelWrapper();
_f = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.getParent()));
RDebugUtils.currentLine=17104909;
 //BA.debugLineNum = 17104909;BA.debugLine="Dim key As String = f.Tag";
_key = BA.ObjectToString(_f.getTag());
RDebugUtils.currentLine=17104910;
 //BA.debugLineNum = 17104910;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=17104911;
 //BA.debugLineNum = 17104911;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \"";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png",anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17104912;
 //BA.debugLineNum = 17104912;BA.debugLine="cvs.CreateBitmap.WriteToStream(out, 100, \"PNG\")";
_cvs.CreateBitmap().WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
RDebugUtils.currentLine=17104913;
 //BA.debugLineNum = 17104913;BA.debugLine="out.Close";
_out.Close();
 break; }
}
;
RDebugUtils.currentLine=17104915;
 //BA.debugLineNum = 17104915;BA.debugLine="End Sub";
return "";
}
public static String  _cc_result(boolean _success,String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cc_result", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cc_result", new Object[] {_success,_dir,_filename}));}
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
String _key = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
RDebugUtils.currentLine=16973824;
 //BA.debugLineNum = 16973824;BA.debugLine="Sub CC_Result (Success As Boolean, Dir As String,";
RDebugUtils.currentLine=16973825;
 //BA.debugLineNum = 16973825;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=16973826;
 //BA.debugLineNum = 16973826;BA.debugLine="imgView.Initialize(\"ImgView\")";
mostCurrent._imgview.Initialize(mostCurrent.activityBA,"ImgView");
RDebugUtils.currentLine=16973827;
 //BA.debugLineNum = 16973827;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
RDebugUtils.currentLine=16973828;
 //BA.debugLineNum = 16973828;BA.debugLine="bmp = LoadBitmapResize(Dir, FileName, 100dip, 10";
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmapResize(_dir,_filename,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=16973829;
 //BA.debugLineNum = 16973829;BA.debugLine="imgView.Bitmap = bmp";
mostCurrent._imgview.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=16973830;
 //BA.debugLineNum = 16973830;BA.debugLine="boardPnl.AddView(imgView, 150dip, 500dip, 100dip";
mostCurrent._boardpnl.AddView((android.view.View)(mostCurrent._imgview.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=16973831;
 //BA.debugLineNum = 16973831;BA.debugLine="ddi.AddDragView(imgView, False)";
mostCurrent._ddi._adddragview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._imgview.getObject())),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16973832;
 //BA.debugLineNum = 16973832;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).Ad";
mostCurrent._ddi._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place1.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place2.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place3.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place4.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place5.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place6.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place7.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place8.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place9.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place10.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place11.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._place12.getObject())))._addplaceview((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._deletelbl.getObject())));
RDebugUtils.currentLine=16973833;
 //BA.debugLineNum = 16973833;BA.debugLine="Dim key As String = \"img_\" & imgCount";
_key = "img_"+BA.NumberToString(_imgcount);
RDebugUtils.currentLine=16973834;
 //BA.debugLineNum = 16973834;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=16973835;
 //BA.debugLineNum = 16973835;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \".";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png",anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=16973836;
 //BA.debugLineNum = 16973836;BA.debugLine="bmp.WriteToStream(out, 100, \"PNG\")";
_bmp.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
RDebugUtils.currentLine=16973837;
 //BA.debugLineNum = 16973837;BA.debugLine="out.Close";
_out.Close();
RDebugUtils.currentLine=16973838;
 //BA.debugLineNum = 16973838;BA.debugLine="Main.kvs.Put(key & \"_file\", key & \".png\")";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_file",(Object)(_key+".png"));
RDebugUtils.currentLine=16973839;
 //BA.debugLineNum = 16973839;BA.debugLine="Main.kvs.Put(key & \"_x\", 150dip)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150))));
RDebugUtils.currentLine=16973840;
 //BA.debugLineNum = 16973840;BA.debugLine="Main.kvs.Put(key & \"_y\", 500dip)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (500))));
RDebugUtils.currentLine=16973841;
 //BA.debugLineNum = 16973841;BA.debugLine="Main.kvs.Put(\"img_count\", imgCount + 1)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("img_count",(Object)(_imgcount+1));
RDebugUtils.currentLine=16973842;
 //BA.debugLineNum = 16973842;BA.debugLine="imgView.Tag = key";
mostCurrent._imgview.setTag((Object)(_key));
RDebugUtils.currentLine=16973843;
 //BA.debugLineNum = 16973843;BA.debugLine="imgCount = imgCount + 1";
_imgcount = (int) (_imgcount+1);
 }else {
RDebugUtils.currentLine=16973845;
 //BA.debugLineNum = 16973845;BA.debugLine="ToastMessageShow(\"No image selected\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No image selected"),anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=16973847;
 //BA.debugLineNum = 16973847;BA.debugLine="End Sub";
return "";
}
public static String  _colorsspnr_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "colorsspnr_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "colorsspnr_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=17694720;
 //BA.debugLineNum = 17694720;BA.debugLine="Private Sub colorsSpnr_ItemClick (Position As Int,";
RDebugUtils.currentLine=17694721;
 //BA.debugLineNum = 17694721;BA.debugLine="Select Position";
switch (_position) {
case 0: {
RDebugUtils.currentLine=17694723;
 //BA.debugLineNum = 17694723;BA.debugLine="R = 255";
_r = (int) (255);
RDebugUtils.currentLine=17694724;
 //BA.debugLineNum = 17694724;BA.debugLine="G = 105";
_g = (int) (105);
RDebugUtils.currentLine=17694725;
 //BA.debugLineNum = 17694725;BA.debugLine="B = 97";
_b = (int) (97);
 break; }
case 1: {
RDebugUtils.currentLine=17694727;
 //BA.debugLineNum = 17694727;BA.debugLine="R = 155";
_r = (int) (155);
RDebugUtils.currentLine=17694728;
 //BA.debugLineNum = 17694728;BA.debugLine="G = 190";
_g = (int) (190);
RDebugUtils.currentLine=17694729;
 //BA.debugLineNum = 17694729;BA.debugLine="B = 237";
_b = (int) (237);
 break; }
case 2: {
RDebugUtils.currentLine=17694731;
 //BA.debugLineNum = 17694731;BA.debugLine="R = 248";
_r = (int) (248);
RDebugUtils.currentLine=17694732;
 //BA.debugLineNum = 17694732;BA.debugLine="G = 241";
_g = (int) (241);
RDebugUtils.currentLine=17694733;
 //BA.debugLineNum = 17694733;BA.debugLine="B = 174";
_b = (int) (174);
 break; }
}
;
RDebugUtils.currentLine=17694735;
 //BA.debugLineNum = 17694735;BA.debugLine="End Sub";
return "";
}
public static String  _imgbtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "imgbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "imgbtn_click", null));}
RDebugUtils.currentLine=17498112;
 //BA.debugLineNum = 17498112;BA.debugLine="Private Sub imgBtn_Click";
RDebugUtils.currentLine=17498113;
 //BA.debugLineNum = 17498113;BA.debugLine="imgPicker.Show(\"image/*\", \"Select a Photo\")";
_imgpicker.Show(processBA,"image/*","Select a Photo");
RDebugUtils.currentLine=17498114;
 //BA.debugLineNum = 17498114;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=17891329;
 //BA.debugLineNum = 17891329;BA.debugLine="If iPlaceView.Tag = \"delete\" Then";
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
RDebugUtils.currentLine=17891330;
 //BA.debugLineNum = 17891330;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete im";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete image?"),BA.ObjectToCharSequence("Delete Image"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17891331;
 //BA.debugLineNum = 17891331;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "imgdrag_placedview"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=17891332;
 //BA.debugLineNum = 17891332;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=17891333;
 //BA.debugLineNum = 17891333;BA.debugLine="Dim iv As ImageView = iDragView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_idragview.getObject()));
RDebugUtils.currentLine=17891334;
 //BA.debugLineNum = 17891334;BA.debugLine="Dim key As String = iv.Tag";
_key = BA.ObjectToString(_iv.getTag());
RDebugUtils.currentLine=17891335;
 //BA.debugLineNum = 17891335;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_key+".png");
RDebugUtils.currentLine=17891336;
 //BA.debugLineNum = 17891336;BA.debugLine="Main.kvs.Remove(key & \"_file\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_file");
RDebugUtils.currentLine=17891337;
 //BA.debugLineNum = 17891337;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
RDebugUtils.currentLine=17891338;
 //BA.debugLineNum = 17891338;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
RDebugUtils.currentLine=17891339;
 //BA.debugLineNum = 17891339;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"img_count\")";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("img_count")));
RDebugUtils.currentLine=17891340;
 //BA.debugLineNum = 17891340;BA.debugLine="Main.kvs.Put(\"img_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("img_count",(Object)(_newcount-1));
RDebugUtils.currentLine=17891341;
 //BA.debugLineNum = 17891341;BA.debugLine="iv.Visible = False";
_iv.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17891342;
 //BA.debugLineNum = 17891342;BA.debugLine="ToastMessageShow(\"Image Deleted\", False)";
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
RDebugUtils.currentLine=17891345;
 //BA.debugLineNum = 17891345;BA.debugLine="Dim iv As ImageView = iDragView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_idragview.getObject()));
RDebugUtils.currentLine=17891346;
 //BA.debugLineNum = 17891346;BA.debugLine="Dim key As String = iv.Tag";
_key = BA.ObjectToString(_iv.getTag());
RDebugUtils.currentLine=17891347;
 //BA.debugLineNum = 17891347;BA.debugLine="Main.kvs.Put(key & \"_x\", iv.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_iv.getLeft()));
RDebugUtils.currentLine=17891348;
 //BA.debugLineNum = 17891348;BA.debugLine="Main.kvs.Put(key & \"_y\", iv.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_iv.getTop()));
 if (true) break;

case 10:
//C
this.state = -1;
;
RDebugUtils.currentLine=17891350;
 //BA.debugLineNum = 17891350;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=17825793;
 //BA.debugLineNum = 17825793;BA.debugLine="If nPlaceView.Tag = \"delete\" Then";
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
RDebugUtils.currentLine=17825794;
 //BA.debugLineNum = 17825794;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete no";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete note?"),BA.ObjectToCharSequence("Delete Note"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17825795;
 //BA.debugLineNum = 17825795;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "corkactivity", "notedrag_placedview"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=17825796;
 //BA.debugLineNum = 17825796;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=17825797;
 //BA.debugLineNum = 17825797;BA.debugLine="Dim p As Panel = nDragView";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_ndragview.getObject()));
RDebugUtils.currentLine=17825798;
 //BA.debugLineNum = 17825798;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=17825799;
 //BA.debugLineNum = 17825799;BA.debugLine="Main.kvs.Remove(key & \"_text\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_text");
RDebugUtils.currentLine=17825800;
 //BA.debugLineNum = 17825800;BA.debugLine="Main.kvs.Remove(key & \"_color\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_color");
RDebugUtils.currentLine=17825801;
 //BA.debugLineNum = 17825801;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_x");
RDebugUtils.currentLine=17825802;
 //BA.debugLineNum = 17825802;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._remove(_key+"_y");
RDebugUtils.currentLine=17825803;
 //BA.debugLineNum = 17825803;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"note_count\"";
_newcount = (int)(BA.ObjectToNumber(parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._get("note_count")));
RDebugUtils.currentLine=17825804;
 //BA.debugLineNum = 17825804;BA.debugLine="Main.kvs.Put(\"note_count\", newCount - 1)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put("note_count",(Object)(_newcount-1));
RDebugUtils.currentLine=17825805;
 //BA.debugLineNum = 17825805;BA.debugLine="p.Visible = False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17825806;
 //BA.debugLineNum = 17825806;BA.debugLine="ToastMessageShow(\"Note Deleted\", False)";
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
RDebugUtils.currentLine=17825809;
 //BA.debugLineNum = 17825809;BA.debugLine="Dim p As Panel = nDragView";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_ndragview.getObject()));
RDebugUtils.currentLine=17825810;
 //BA.debugLineNum = 17825810;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=17825811;
 //BA.debugLineNum = 17825811;BA.debugLine="Main.kvs.Put(key & \"_x\", p.Left)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_x",(Object)(_p.getLeft()));
RDebugUtils.currentLine=17825812;
 //BA.debugLineNum = 17825812;BA.debugLine="Main.kvs.Put(key & \"_y\", p.Top)";
parent.mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_y",(Object)(_p.getTop()));
 if (true) break;

case 10:
//C
this.state = -1;
;
RDebugUtils.currentLine=17825814;
 //BA.debugLineNum = 17825814;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=16908288;
 //BA.debugLineNum = 16908288;BA.debugLine="Sub NoteText_TextChanged(Old As String, New As Str";
RDebugUtils.currentLine=16908289;
 //BA.debugLineNum = 16908289;BA.debugLine="Dim txt As EditText = Sender";
_txt = new anywheresoftware.b4a.objects.EditTextWrapper();
_txt = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=16908290;
 //BA.debugLineNum = 16908290;BA.debugLine="Dim p As Panel = txt.Tag";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_txt.getTag()));
RDebugUtils.currentLine=16908291;
 //BA.debugLineNum = 16908291;BA.debugLine="Dim key As String = p.Tag";
_key = BA.ObjectToString(_p.getTag());
RDebugUtils.currentLine=16908292;
 //BA.debugLineNum = 16908292;BA.debugLine="Main.kvs.Put(key & \"_text\", New)";
mostCurrent._main._kvs /*b4a.example3.keyvaluestore*/ ._put(_key+"_text",(Object)(_new));
RDebugUtils.currentLine=16908293;
 //BA.debugLineNum = 16908293;BA.debugLine="End Sub";
return "";
}
public static String  _notewindow(int _pw,int _ph) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "notewindow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "notewindow", new Object[] {_pw,_ph}));}
anywheresoftware.b4a.objects.SpinnerWrapper _colorsspnr = null;
anywheresoftware.b4a.objects.ButtonWrapper _addnbtn = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=17170432;
 //BA.debugLineNum = 17170432;BA.debugLine="Private Sub noteWindow(pW As Int, pH As Int)";
RDebugUtils.currentLine=17170433;
 //BA.debugLineNum = 17170433;BA.debugLine="notePnl = xui.CreatePanel(\"notePnl\")";
mostCurrent._notepnl = _xui.CreatePanel(processBA,"notePnl");
RDebugUtils.currentLine=17170434;
 //BA.debugLineNum = 17170434;BA.debugLine="Activity.AddView(notePnl, 100dip, 225dip, pW, pH)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._notepnl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (225)),_pw,_ph);
RDebugUtils.currentLine=17170437;
 //BA.debugLineNum = 17170437;BA.debugLine="Dim colorsSpnr As Spinner";
_colorsspnr = new anywheresoftware.b4a.objects.SpinnerWrapper();
RDebugUtils.currentLine=17170438;
 //BA.debugLineNum = 17170438;BA.debugLine="colorsSpnr.Initialize(\"colorsSpnr\")";
_colorsspnr.Initialize(mostCurrent.activityBA,"colorsSpnr");
RDebugUtils.currentLine=17170439;
 //BA.debugLineNum = 17170439;BA.debugLine="colorsSpnr.AddAll(Array As String(\"Red\", \"Blue\",";
_colorsspnr.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Red","Blue","Yellow"}));
RDebugUtils.currentLine=17170440;
 //BA.debugLineNum = 17170440;BA.debugLine="notePnl.AddView(colorsSpnr, 10dip, 10dip, pW - 20";
mostCurrent._notepnl.AddView((android.view.View)(_colorsspnr.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=17170442;
 //BA.debugLineNum = 17170442;BA.debugLine="Dim addnBtn As Button";
_addnbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17170443;
 //BA.debugLineNum = 17170443;BA.debugLine="addnBtn.Initialize(\"addnBtn\")";
_addnbtn.Initialize(mostCurrent.activityBA,"addnBtn");
RDebugUtils.currentLine=17170444;
 //BA.debugLineNum = 17170444;BA.debugLine="addnBtn.Text = \"Add Note\"";
_addnbtn.setText(BA.ObjectToCharSequence("Add Note"));
RDebugUtils.currentLine=17170445;
 //BA.debugLineNum = 17170445;BA.debugLine="notePnl.AddView(addnBtn, 10dip, 60dip, (pW / 2) -";
mostCurrent._notepnl.AddView((android.view.View)(_addnbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),(int) ((_pw/(double)2)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=17170447;
 //BA.debugLineNum = 17170447;BA.debugLine="notePnl.Enabled = False";
mostCurrent._notepnl.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17170448;
 //BA.debugLineNum = 17170448;BA.debugLine="notePnl.Visible = False";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17170450;
 //BA.debugLineNum = 17170450;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=17170451;
 //BA.debugLineNum = 17170451;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=17170452;
 //BA.debugLineNum = 17170452;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=17170454;
 //BA.debugLineNum = 17170454;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=17170455;
 //BA.debugLineNum = 17170455;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17170456;
 //BA.debugLineNum = 17170456;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(187, 2";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (187),(int) (213),(int) (218)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17170457;
 //BA.debugLineNum = 17170457;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=17170458;
 //BA.debugLineNum = 17170458;BA.debugLine="addnBtn.Background = cd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=17170459;
 //BA.debugLineNum = 17170459;BA.debugLine="addnBtn.TextColor = Colors.Black";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=17170461;
 //BA.debugLineNum = 17170461;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17170462;
 //BA.debugLineNum = 17170462;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(227, 1";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (227),(int) (193),(int) (159)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17170463;
 //BA.debugLineNum = 17170463;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=17170464;
 //BA.debugLineNum = 17170464;BA.debugLine="addnBtn.Background = cd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=17170465;
 //BA.debugLineNum = 17170465;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=17170468;
 //BA.debugLineNum = 17170468;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=17170469;
 //BA.debugLineNum = 17170469;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17170470;
 //BA.debugLineNum = 17170470;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(27, 98";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (27),(int) (98),(int) (161)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (16),(int) (26),(int) (95)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17170471;
 //BA.debugLineNum = 17170471;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"dark";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dark Skeumorphic Button blue.png").getObject()));
RDebugUtils.currentLine=17170472;
 //BA.debugLineNum = 17170472;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17170473;
 //BA.debugLineNum = 17170473;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=17170475;
 //BA.debugLineNum = 17170475;BA.debugLine="notePnl.Color = xui.Color_RGB(255, 255, 255)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=17170476;
 //BA.debugLineNum = 17170476;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(51, 18";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (51),(int) (187),(int) (152)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17170477;
 //BA.debugLineNum = 17170477;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Skeumorphic Button 03.png").getObject()));
RDebugUtils.currentLine=17170478;
 //BA.debugLineNum = 17170478;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17170479;
 //BA.debugLineNum = 17170479;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=17170482;
 //BA.debugLineNum = 17170482;BA.debugLine="addnBtn.Typeface = pixeltf";
_addnbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=17170483;
 //BA.debugLineNum = 17170483;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=17170484;
 //BA.debugLineNum = 17170484;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17170485;
 //BA.debugLineNum = 17170485;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(37, 57";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (37),(int) (57),(int) (94)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_Black,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17170486;
 //BA.debugLineNum = 17170486;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
RDebugUtils.currentLine=17170487;
 //BA.debugLineNum = 17170487;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17170488;
 //BA.debugLineNum = 17170488;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=17170490;
 //BA.debugLineNum = 17170490;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
mostCurrent._notepnl.setColor(_xui.Color_RGB((int) (50),(int) (50),(int) (50)));
RDebugUtils.currentLine=17170491;
 //BA.debugLineNum = 17170491;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(234, 2";
mostCurrent._notepnl.SetColorAndBorder(_xui.Color_RGB((int) (234),(int) (212),(int) (179)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),_xui.Color_RGB((int) (79),(int) (46),(int) (46)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=17170492;
 //BA.debugLineNum = 17170492;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarpbtn.png").getObject()));
RDebugUtils.currentLine=17170493;
 //BA.debugLineNum = 17170493;BA.debugLine="addnBtn.Background = bd";
_addnbtn.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
RDebugUtils.currentLine=17170494;
 //BA.debugLineNum = 17170494;BA.debugLine="addnBtn.TextColor = Colors.White";
_addnbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
}
;
RDebugUtils.currentLine=17170497;
 //BA.debugLineNum = 17170497;BA.debugLine="End Sub";
return "";
}
public static String  _penspnr_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "penspnr_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "penspnr_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=17760256;
 //BA.debugLineNum = 17760256;BA.debugLine="Private Sub penSpnr_ItemClick (Position As Int, Va";
RDebugUtils.currentLine=17760257;
 //BA.debugLineNum = 17760257;BA.debugLine="Select Position";
switch (_position) {
case 0: {
RDebugUtils.currentLine=17760259;
 //BA.debugLineNum = 17760259;BA.debugLine="R2 = 0";
_r2 = (int) (0);
RDebugUtils.currentLine=17760260;
 //BA.debugLineNum = 17760260;BA.debugLine="G2 = 0";
_g2 = (int) (0);
RDebugUtils.currentLine=17760261;
 //BA.debugLineNum = 17760261;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 1: {
RDebugUtils.currentLine=17760263;
 //BA.debugLineNum = 17760263;BA.debugLine="R2 = 0";
_r2 = (int) (0);
RDebugUtils.currentLine=17760264;
 //BA.debugLineNum = 17760264;BA.debugLine="G2 = 0";
_g2 = (int) (0);
RDebugUtils.currentLine=17760265;
 //BA.debugLineNum = 17760265;BA.debugLine="B2 = 255";
_b2 = (int) (255);
 break; }
case 2: {
RDebugUtils.currentLine=17760267;
 //BA.debugLineNum = 17760267;BA.debugLine="R2 = 0";
_r2 = (int) (0);
RDebugUtils.currentLine=17760268;
 //BA.debugLineNum = 17760268;BA.debugLine="G2 = 255";
_g2 = (int) (255);
RDebugUtils.currentLine=17760269;
 //BA.debugLineNum = 17760269;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 3: {
RDebugUtils.currentLine=17760271;
 //BA.debugLineNum = 17760271;BA.debugLine="R2 = 255";
_r2 = (int) (255);
RDebugUtils.currentLine=17760272;
 //BA.debugLineNum = 17760272;BA.debugLine="G2 = 0";
_g2 = (int) (0);
RDebugUtils.currentLine=17760273;
 //BA.debugLineNum = 17760273;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 4: {
RDebugUtils.currentLine=17760275;
 //BA.debugLineNum = 17760275;BA.debugLine="R2 = 255";
_r2 = (int) (255);
RDebugUtils.currentLine=17760276;
 //BA.debugLineNum = 17760276;BA.debugLine="G2 = 255";
_g2 = (int) (255);
RDebugUtils.currentLine=17760277;
 //BA.debugLineNum = 17760277;BA.debugLine="B2 = 0";
_b2 = (int) (0);
 break; }
case 5: {
RDebugUtils.currentLine=17760279;
 //BA.debugLineNum = 17760279;BA.debugLine="R2 = 255";
_r2 = (int) (255);
RDebugUtils.currentLine=17760280;
 //BA.debugLineNum = 17760280;BA.debugLine="G2 = 255";
_g2 = (int) (255);
RDebugUtils.currentLine=17760281;
 //BA.debugLineNum = 17760281;BA.debugLine="B2 = 255";
_b2 = (int) (255);
 break; }
}
;
RDebugUtils.currentLine=17760283;
 //BA.debugLineNum = 17760283;BA.debugLine="End Sub";
return "";
}
public static String  _sizespnr_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sizespnr_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sizespnr_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=17629184;
 //BA.debugLineNum = 17629184;BA.debugLine="Private Sub sizeSpnr_ItemClick (Position As Int, V";
RDebugUtils.currentLine=17629185;
 //BA.debugLineNum = 17629185;BA.debugLine="Select Position";
switch (_position) {
case 0: {
RDebugUtils.currentLine=17629187;
 //BA.debugLineNum = 17629187;BA.debugLine="Width = 80dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=17629188;
 //BA.debugLineNum = 17629188;BA.debugLine="Height = 60dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 break; }
case 1: {
RDebugUtils.currentLine=17629190;
 //BA.debugLineNum = 17629190;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
RDebugUtils.currentLine=17629191;
 //BA.debugLineNum = 17629191;BA.debugLine="Height = 60dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60));
 break; }
case 2: {
RDebugUtils.currentLine=17629193;
 //BA.debugLineNum = 17629193;BA.debugLine="Width = 80dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=17629194;
 //BA.debugLineNum = 17629194;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 3: {
RDebugUtils.currentLine=17629196;
 //BA.debugLineNum = 17629196;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
RDebugUtils.currentLine=17629197;
 //BA.debugLineNum = 17629197;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 4: {
RDebugUtils.currentLine=17629199;
 //BA.debugLineNum = 17629199;BA.debugLine="Width = 330dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
RDebugUtils.currentLine=17629200;
 //BA.debugLineNum = 17629200;BA.debugLine="Height = 185dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (185));
 break; }
case 5: {
RDebugUtils.currentLine=17629202;
 //BA.debugLineNum = 17629202;BA.debugLine="Width = 205dip";
_width = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (205));
RDebugUtils.currentLine=17629203;
 //BA.debugLineNum = 17629203;BA.debugLine="Height = 310dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (310));
 break; }
}
;
RDebugUtils.currentLine=17629205;
 //BA.debugLineNum = 17629205;BA.debugLine="End Sub";
return "";
}
public static String  _stickybtn_click() throws Exception{
RDebugUtils.currentModule="corkactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "stickybtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "stickybtn_click", null));}
RDebugUtils.currentLine=17432576;
 //BA.debugLineNum = 17432576;BA.debugLine="Private Sub stickyBtn_Click";
RDebugUtils.currentLine=17432577;
 //BA.debugLineNum = 17432577;BA.debugLine="noteWindow(250dip, 180dip)";
_notewindow(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=17432578;
 //BA.debugLineNum = 17432578;BA.debugLine="notePnl.Visible = True";
mostCurrent._notepnl.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=17432579;
 //BA.debugLineNum = 17432579;BA.debugLine="stickyBtn.Enabled = False";
mostCurrent._stickybtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17432580;
 //BA.debugLineNum = 17432580;BA.debugLine="canvaBtn.Enabled = False";
mostCurrent._canvabtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17432581;
 //BA.debugLineNum = 17432581;BA.debugLine="imgBtn.Enabled = False";
mostCurrent._imgbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17432582;
 //BA.debugLineNum = 17432582;BA.debugLine="End Sub";
return "";
}
}