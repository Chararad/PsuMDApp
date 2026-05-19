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

public class helpactivity extends Activity implements B4AActivity{
	public static helpactivity mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.helpactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (helpactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.helpactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.helpactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (helpactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (helpactivity) Resume **");
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
		return helpactivity.class;
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
            BA.LogInfo("** Activity (helpactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (helpactivity) Pause event (activity is not paused). **");
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
            helpactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (helpactivity) Resume **");
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
public static int _helppage = 0;
public anywheresoftware.b4a.objects.LabelWrapper _titlelbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _descriptionlbl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _backbtn = null;
public anywheresoftware.b4a.objects.ButtonWrapper _nextbtn = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _helpimage = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iconbutton1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iconbutton2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iconbutton3 = null;
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
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
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
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=12189696;
 //BA.debugLineNum = 12189696;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=12189697;
 //BA.debugLineNum = 12189697;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=12189699;
 //BA.debugLineNum = 12189699;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12189700;
 //BA.debugLineNum = 12189700;BA.debugLine="Activity.LoadLayout(\"helpAct\")";
mostCurrent._activity.LoadLayout("helpAct",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12189702;
 //BA.debugLineNum = 12189702;BA.debugLine="Activity.LoadLayout(\"helpActDark\")";
mostCurrent._activity.LoadLayout("helpActDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=12189705;
 //BA.debugLineNum = 12189705;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12189706;
 //BA.debugLineNum = 12189706;BA.debugLine="Activity.LoadLayout(\"helpAct2\")";
mostCurrent._activity.LoadLayout("helpAct2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12189708;
 //BA.debugLineNum = 12189708;BA.debugLine="Activity.LoadLayout(\"helpActDark2\")";
mostCurrent._activity.LoadLayout("helpActDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=12189711;
 //BA.debugLineNum = 12189711;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12189712;
 //BA.debugLineNum = 12189712;BA.debugLine="Activity.LoadLayout(\"helpAct3\")";
mostCurrent._activity.LoadLayout("helpAct3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12189714;
 //BA.debugLineNum = 12189714;BA.debugLine="Activity.LoadLayout(\"helpActDark3\")";
mostCurrent._activity.LoadLayout("helpActDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=12189718;
 //BA.debugLineNum = 12189718;BA.debugLine="showHelpPage(0)";
_showhelppage((int) (0));
RDebugUtils.currentLine=12189719;
 //BA.debugLineNum = 12189719;BA.debugLine="End Sub";
return "";
}
public static String  _showhelppage(int _page) throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showhelppage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showhelppage", new Object[] {_page}));}
RDebugUtils.currentLine=12386304;
 //BA.debugLineNum = 12386304;BA.debugLine="Sub showHelpPage(page As Int)";
RDebugUtils.currentLine=12386305;
 //BA.debugLineNum = 12386305;BA.debugLine="helpPage = page";
_helppage = _page;
RDebugUtils.currentLine=12386307;
 //BA.debugLineNum = 12386307;BA.debugLine="Select page";
switch (_page) {
case 0: {
RDebugUtils.currentLine=12386309;
 //BA.debugLineNum = 12386309;BA.debugLine="titleLbl.Text = \"Welcome\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Welcome"));
RDebugUtils.currentLine=12386310;
 //BA.debugLineNum = 12386310;BA.debugLine="descriptionLbl.Text = \"This app is tailor-made";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("This app is tailor-made for students—or anyone who wants to learn. It has all the features you'll need in order to learn effectively!"));
RDebugUtils.currentLine=12386311;
 //BA.debugLineNum = 12386311;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386312;
 //BA.debugLineNum = 12386312;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wreath2.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386313;
 //BA.debugLineNum = 12386313;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star2.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386314;
 //BA.debugLineNum = 12386314;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star2.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386315;
 //BA.debugLineNum = 12386315;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star2.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386317;
 //BA.debugLineNum = 12386317;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wreath3.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386318;
 //BA.debugLineNum = 12386318;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386319;
 //BA.debugLineNum = 12386319;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386320;
 //BA.debugLineNum = 12386320;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 1: {
RDebugUtils.currentLine=12386323;
 //BA.debugLineNum = 12386323;BA.debugLine="titleLbl.Text = \"Calendar\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Calendar"));
RDebugUtils.currentLine=12386324;
 //BA.debugLineNum = 12386324;BA.debugLine="descriptionLbl.Text = \"The calendar comes in th";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."));
RDebugUtils.currentLine=12386325;
 //BA.debugLineNum = 12386325;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386326;
 //BA.debugLineNum = 12386326;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dcalendarui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386327;
 //BA.debugLineNum = 12386327;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1249.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386328;
 //BA.debugLineNum = 12386328;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1248.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386329;
 //BA.debugLineNum = 12386329;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1247.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386331;
 //BA.debugLineNum = 12386331;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386332;
 //BA.debugLineNum = 12386332;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1252.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386333;
 //BA.debugLineNum = 12386333;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1251.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386334;
 //BA.debugLineNum = 12386334;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1250.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 2: {
RDebugUtils.currentLine=12386337;
 //BA.debugLineNum = 12386337;BA.debugLine="titleLbl.Text = \"Clock\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Clock"));
RDebugUtils.currentLine=12386338;
 //BA.debugLineNum = 12386338;BA.debugLine="descriptionLbl.Text = \"The clock keeps you on t";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."));
RDebugUtils.currentLine=12386339;
 //BA.debugLineNum = 12386339;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386340;
 //BA.debugLineNum = 12386340;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dclockui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386341;
 //BA.debugLineNum = 12386341;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1255.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386342;
 //BA.debugLineNum = 12386342;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1254.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386343;
 //BA.debugLineNum = 12386343;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1253.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386345;
 //BA.debugLineNum = 12386345;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"clockui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386346;
 //BA.debugLineNum = 12386346;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1258.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386347;
 //BA.debugLineNum = 12386347;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1257.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386348;
 //BA.debugLineNum = 12386348;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1256.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 3: {
RDebugUtils.currentLine=12386351;
 //BA.debugLineNum = 12386351;BA.debugLine="titleLbl.Text = \"Corkboard\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Corkboard"));
RDebugUtils.currentLine=12386352;
 //BA.debugLineNum = 12386352;BA.debugLine="descriptionLbl.Text = \"The corkboard gives you";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."));
RDebugUtils.currentLine=12386353;
 //BA.debugLineNum = 12386353;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386354;
 //BA.debugLineNum = 12386354;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dcorkboardui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386355;
 //BA.debugLineNum = 12386355;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1261.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386356;
 //BA.debugLineNum = 12386356;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1260.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386357;
 //BA.debugLineNum = 12386357;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1259.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386359;
 //BA.debugLineNum = 12386359;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"corkboardui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386360;
 //BA.debugLineNum = 12386360;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1264.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386361;
 //BA.debugLineNum = 12386361;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1263.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386362;
 //BA.debugLineNum = 12386362;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1262.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 4: {
RDebugUtils.currentLine=12386365;
 //BA.debugLineNum = 12386365;BA.debugLine="titleLbl.Text = \"Flashcards\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Flashcards"));
RDebugUtils.currentLine=12386366;
 //BA.debugLineNum = 12386366;BA.debugLine="descriptionLbl.Text = \"The flashcard feature or";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."));
RDebugUtils.currentLine=12386367;
 //BA.debugLineNum = 12386367;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386368;
 //BA.debugLineNum = 12386368;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dflashcardsui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386369;
 //BA.debugLineNum = 12386369;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1267.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386370;
 //BA.debugLineNum = 12386370;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1266.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386371;
 //BA.debugLineNum = 12386371;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1265.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386373;
 //BA.debugLineNum = 12386373;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"flashcardui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386374;
 //BA.debugLineNum = 12386374;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1270.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386375;
 //BA.debugLineNum = 12386375;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1269.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386376;
 //BA.debugLineNum = 12386376;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1268.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 5: {
RDebugUtils.currentLine=12386379;
 //BA.debugLineNum = 12386379;BA.debugLine="titleLbl.Text = \"Music Player\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Music Player"));
RDebugUtils.currentLine=12386380;
 //BA.debugLineNum = 12386380;BA.debugLine="descriptionLbl.Text = \"The music player plays t";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music."));
RDebugUtils.currentLine=12386381;
 //BA.debugLineNum = 12386381;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386382;
 //BA.debugLineNum = 12386382;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dmusicplayerui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386383;
 //BA.debugLineNum = 12386383;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1273.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386384;
 //BA.debugLineNum = 12386384;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1272.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386385;
 //BA.debugLineNum = 12386385;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1271.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386387;
 //BA.debugLineNum = 12386387;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"musicplayerui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386388;
 //BA.debugLineNum = 12386388;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1276.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386389;
 //BA.debugLineNum = 12386389;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1275.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386390;
 //BA.debugLineNum = 12386390;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1274.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 6: {
RDebugUtils.currentLine=12386393;
 //BA.debugLineNum = 12386393;BA.debugLine="titleLbl.Text = \"Notepad\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Notepad"));
RDebugUtils.currentLine=12386394;
 //BA.debugLineNum = 12386394;BA.debugLine="descriptionLbl.Text = \"The notepad keeps all yo";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."));
RDebugUtils.currentLine=12386395;
 //BA.debugLineNum = 12386395;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386396;
 //BA.debugLineNum = 12386396;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dnotepadui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386397;
 //BA.debugLineNum = 12386397;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1279.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386398;
 //BA.debugLineNum = 12386398;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1278.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386399;
 //BA.debugLineNum = 12386399;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1277.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386401;
 //BA.debugLineNum = 12386401;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"notepadui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386402;
 //BA.debugLineNum = 12386402;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1282.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386403;
 //BA.debugLineNum = 12386403;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1281.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386404;
 //BA.debugLineNum = 12386404;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1283.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 7: {
RDebugUtils.currentLine=12386407;
 //BA.debugLineNum = 12386407;BA.debugLine="titleLbl.Text = \"To-do List\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("To-do List"));
RDebugUtils.currentLine=12386408;
 //BA.debugLineNum = 12386408;BA.debugLine="descriptionLbl.Text = \"The to-do list enables y";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."));
RDebugUtils.currentLine=12386409;
 //BA.debugLineNum = 12386409;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386410;
 //BA.debugLineNum = 12386410;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dtodoui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386411;
 //BA.debugLineNum = 12386411;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1285.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386412;
 //BA.debugLineNum = 12386412;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1284.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386413;
 //BA.debugLineNum = 12386413;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1283.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386415;
 //BA.debugLineNum = 12386415;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"todoui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386416;
 //BA.debugLineNum = 12386416;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1288.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386417;
 //BA.debugLineNum = 12386417;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1287.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386418;
 //BA.debugLineNum = 12386418;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1286.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 8: {
RDebugUtils.currentLine=12386421;
 //BA.debugLineNum = 12386421;BA.debugLine="titleLbl.Text = \"Themes\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Themes"));
RDebugUtils.currentLine=12386422;
 //BA.debugLineNum = 12386422;BA.debugLine="descriptionLbl.Text = \"Themes let you put your";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
RDebugUtils.currentLine=12386423;
 //BA.debugLineNum = 12386423;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386424;
 //BA.debugLineNum = 12386424;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dthemesui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386425;
 //BA.debugLineNum = 12386425;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1291.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386426;
 //BA.debugLineNum = 12386426;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1290.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386427;
 //BA.debugLineNum = 12386427;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1289.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386429;
 //BA.debugLineNum = 12386429;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"themesui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386430;
 //BA.debugLineNum = 12386430;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1294.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386431;
 //BA.debugLineNum = 12386431;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1293.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386432;
 //BA.debugLineNum = 12386432;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1292.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 9: {
RDebugUtils.currentLine=12386435;
 //BA.debugLineNum = 12386435;BA.debugLine="titleLbl.Text = \"Lamp\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Lamp"));
RDebugUtils.currentLine=12386436;
 //BA.debugLineNum = 12386436;BA.debugLine="descriptionLbl.Text = \"The lamp gives you contr";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
RDebugUtils.currentLine=12386437;
 //BA.debugLineNum = 12386437;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386438;
 //BA.debugLineNum = 12386438;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dlampui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386439;
 //BA.debugLineNum = 12386439;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1297.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386440;
 //BA.debugLineNum = 12386440;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1296.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386441;
 //BA.debugLineNum = 12386441;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1295.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386443;
 //BA.debugLineNum = 12386443;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lampui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386444;
 //BA.debugLineNum = 12386444;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1298.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386445;
 //BA.debugLineNum = 12386445;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1299.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386446;
 //BA.debugLineNum = 12386446;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1300.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 10: {
RDebugUtils.currentLine=12386449;
 //BA.debugLineNum = 12386449;BA.debugLine="titleLbl.Text = \"Navigation\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Navigation"));
RDebugUtils.currentLine=12386450;
 //BA.debugLineNum = 12386450;BA.debugLine="descriptionLbl.Text = \"Navigation is your home";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
RDebugUtils.currentLine=12386451;
 //BA.debugLineNum = 12386451;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12386452;
 //BA.debugLineNum = 12386452;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dnavigationui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386453;
 //BA.debugLineNum = 12386453;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386454;
 //BA.debugLineNum = 12386454;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"menu.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386455;
 //BA.debugLineNum = 12386455;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=12386457;
 //BA.debugLineNum = 12386457;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"navigationui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386458;
 //BA.debugLineNum = 12386458;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386459;
 //BA.debugLineNum = 12386459;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"menu.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=12386460;
 //BA.debugLineNum = 12386460;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
}
;
RDebugUtils.currentLine=12386465;
 //BA.debugLineNum = 12386465;BA.debugLine="backBtn.Enabled = (page > 0)";
mostCurrent._backbtn.setEnabled((_page>0));
RDebugUtils.currentLine=12386466;
 //BA.debugLineNum = 12386466;BA.debugLine="nextBtn.Enabled = (page < 10)";
mostCurrent._nextbtn.setEnabled((_page<10));
RDebugUtils.currentLine=12386467;
 //BA.debugLineNum = 12386467;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="helpactivity";
RDebugUtils.currentLine=12320768;
 //BA.debugLineNum = 12320768;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=12320770;
 //BA.debugLineNum = 12320770;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=12255232;
 //BA.debugLineNum = 12255232;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=12255234;
 //BA.debugLineNum = 12255234;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=12517376;
 //BA.debugLineNum = 12517376;BA.debugLine="Sub backBtn_Click";
RDebugUtils.currentLine=12517377;
 //BA.debugLineNum = 12517377;BA.debugLine="If helpPage > 0 Then";
if (_helppage>0) { 
RDebugUtils.currentLine=12517378;
 //BA.debugLineNum = 12517378;BA.debugLine="showHelpPage(helpPage - 1)";
_showhelppage((int) (_helppage-1));
 };
RDebugUtils.currentLine=12517380;
 //BA.debugLineNum = 12517380;BA.debugLine="End Sub";
return "";
}
public static String  _closehelp_click() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "closehelp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "closehelp_click", null));}
RDebugUtils.currentLine=12582912;
 //BA.debugLineNum = 12582912;BA.debugLine="Sub closeHelp_Click";
RDebugUtils.currentLine=12582913;
 //BA.debugLineNum = 12582913;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=12582914;
 //BA.debugLineNum = 12582914;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=12451840;
 //BA.debugLineNum = 12451840;BA.debugLine="Sub nextBtn_Click";
RDebugUtils.currentLine=12451841;
 //BA.debugLineNum = 12451841;BA.debugLine="If helpPage < 10 Then";
if (_helppage<10) { 
RDebugUtils.currentLine=12451842;
 //BA.debugLineNum = 12451842;BA.debugLine="showHelpPage(helpPage + 1)";
_showhelppage((int) (_helppage+1));
 };
RDebugUtils.currentLine=12451844;
 //BA.debugLineNum = 12451844;BA.debugLine="End Sub";
return "";
}
}