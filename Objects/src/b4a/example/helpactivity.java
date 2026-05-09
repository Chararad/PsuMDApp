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
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=16842752;
 //BA.debugLineNum = 16842752;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=16842753;
 //BA.debugLineNum = 16842753;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=16842755;
 //BA.debugLineNum = 16842755;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=16842756;
 //BA.debugLineNum = 16842756;BA.debugLine="Activity.LoadLayout(\"helpAct\")";
mostCurrent._activity.LoadLayout("helpAct",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=16842758;
 //BA.debugLineNum = 16842758;BA.debugLine="Activity.LoadLayout(\"helpActDark\")";
mostCurrent._activity.LoadLayout("helpActDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=16842761;
 //BA.debugLineNum = 16842761;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=16842762;
 //BA.debugLineNum = 16842762;BA.debugLine="Activity.LoadLayout(\"helpAct2\")";
mostCurrent._activity.LoadLayout("helpAct2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=16842764;
 //BA.debugLineNum = 16842764;BA.debugLine="Activity.LoadLayout(\"helpActDark2\")";
mostCurrent._activity.LoadLayout("helpActDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=16842767;
 //BA.debugLineNum = 16842767;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=16842768;
 //BA.debugLineNum = 16842768;BA.debugLine="Activity.LoadLayout(\"helpAct3\")";
mostCurrent._activity.LoadLayout("helpAct3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=16842770;
 //BA.debugLineNum = 16842770;BA.debugLine="Activity.LoadLayout(\"helpActDark3\")";
mostCurrent._activity.LoadLayout("helpActDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=16842774;
 //BA.debugLineNum = 16842774;BA.debugLine="showHelpPage(0)";
_showhelppage((int) (0));
RDebugUtils.currentLine=16842775;
 //BA.debugLineNum = 16842775;BA.debugLine="End Sub";
return "";
}
public static String  _showhelppage(int _page) throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showhelppage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showhelppage", new Object[] {_page}));}
RDebugUtils.currentLine=17039360;
 //BA.debugLineNum = 17039360;BA.debugLine="Sub showHelpPage(page As Int)";
RDebugUtils.currentLine=17039361;
 //BA.debugLineNum = 17039361;BA.debugLine="helpPage = page";
_helppage = _page;
RDebugUtils.currentLine=17039363;
 //BA.debugLineNum = 17039363;BA.debugLine="Select page";
switch (_page) {
case 0: {
RDebugUtils.currentLine=17039365;
 //BA.debugLineNum = 17039365;BA.debugLine="titleLbl.Text = \"Welcome\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Welcome"));
RDebugUtils.currentLine=17039366;
 //BA.debugLineNum = 17039366;BA.debugLine="descriptionLbl.Text = \"This app is tailor-made";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("This app is tailor-made for students—or anyone who wants to learn. It has all the features you'll need in order to learn effectively!"));
RDebugUtils.currentLine=17039367;
 //BA.debugLineNum = 17039367;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039368;
 //BA.debugLineNum = 17039368;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wreath.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039369;
 //BA.debugLineNum = 17039369;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039370;
 //BA.debugLineNum = 17039370;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039371;
 //BA.debugLineNum = 17039371;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039373;
 //BA.debugLineNum = 17039373;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dwreath.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039374;
 //BA.debugLineNum = 17039374;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039375;
 //BA.debugLineNum = 17039375;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039376;
 //BA.debugLineNum = 17039376;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 1: {
RDebugUtils.currentLine=17039379;
 //BA.debugLineNum = 17039379;BA.debugLine="titleLbl.Text = \"Calendar\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Calendar"));
RDebugUtils.currentLine=17039380;
 //BA.debugLineNum = 17039380;BA.debugLine="descriptionLbl.Text = \"The calendar comes in th";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."));
RDebugUtils.currentLine=17039381;
 //BA.debugLineNum = 17039381;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039382;
 //BA.debugLineNum = 17039382;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calendarui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039383;
 //BA.debugLineNum = 17039383;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1249.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039384;
 //BA.debugLineNum = 17039384;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1248.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039385;
 //BA.debugLineNum = 17039385;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1247.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039387;
 //BA.debugLineNum = 17039387;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dcalendarui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039388;
 //BA.debugLineNum = 17039388;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1252.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039389;
 //BA.debugLineNum = 17039389;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1251.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039390;
 //BA.debugLineNum = 17039390;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1250.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 2: {
RDebugUtils.currentLine=17039393;
 //BA.debugLineNum = 17039393;BA.debugLine="titleLbl.Text = \"Clock\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Clock"));
RDebugUtils.currentLine=17039394;
 //BA.debugLineNum = 17039394;BA.debugLine="descriptionLbl.Text = \"The clock keeps you on t";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."));
RDebugUtils.currentLine=17039395;
 //BA.debugLineNum = 17039395;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039396;
 //BA.debugLineNum = 17039396;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"clockui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039397;
 //BA.debugLineNum = 17039397;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1255.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039398;
 //BA.debugLineNum = 17039398;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1254.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039399;
 //BA.debugLineNum = 17039399;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1253.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039401;
 //BA.debugLineNum = 17039401;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dclockui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039402;
 //BA.debugLineNum = 17039402;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1258.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039403;
 //BA.debugLineNum = 17039403;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1257.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039404;
 //BA.debugLineNum = 17039404;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1256.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 3: {
RDebugUtils.currentLine=17039407;
 //BA.debugLineNum = 17039407;BA.debugLine="titleLbl.Text = \"Corkboard\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Corkboard"));
RDebugUtils.currentLine=17039408;
 //BA.debugLineNum = 17039408;BA.debugLine="descriptionLbl.Text = \"The corkboard gives you";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."));
RDebugUtils.currentLine=17039409;
 //BA.debugLineNum = 17039409;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039410;
 //BA.debugLineNum = 17039410;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"corkboardui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039411;
 //BA.debugLineNum = 17039411;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1261.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039412;
 //BA.debugLineNum = 17039412;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1260.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039413;
 //BA.debugLineNum = 17039413;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1259.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039415;
 //BA.debugLineNum = 17039415;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dcorkboardui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039416;
 //BA.debugLineNum = 17039416;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1264.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039417;
 //BA.debugLineNum = 17039417;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1263.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039418;
 //BA.debugLineNum = 17039418;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1262.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 4: {
RDebugUtils.currentLine=17039421;
 //BA.debugLineNum = 17039421;BA.debugLine="titleLbl.Text = \"Flashcards\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Flashcards"));
RDebugUtils.currentLine=17039422;
 //BA.debugLineNum = 17039422;BA.debugLine="descriptionLbl.Text = \"The flashcard feature or";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."));
RDebugUtils.currentLine=17039423;
 //BA.debugLineNum = 17039423;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039424;
 //BA.debugLineNum = 17039424;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"flashcardsui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039425;
 //BA.debugLineNum = 17039425;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1267.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039426;
 //BA.debugLineNum = 17039426;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1266.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039427;
 //BA.debugLineNum = 17039427;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1265.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039429;
 //BA.debugLineNum = 17039429;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dflashcardui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039430;
 //BA.debugLineNum = 17039430;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1270.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039431;
 //BA.debugLineNum = 17039431;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1269.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039432;
 //BA.debugLineNum = 17039432;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1268.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 5: {
RDebugUtils.currentLine=17039435;
 //BA.debugLineNum = 17039435;BA.debugLine="titleLbl.Text = \"Music Player\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Music Player"));
RDebugUtils.currentLine=17039436;
 //BA.debugLineNum = 17039436;BA.debugLine="descriptionLbl.Text = \"The music player plays t";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music."));
RDebugUtils.currentLine=17039437;
 //BA.debugLineNum = 17039437;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039438;
 //BA.debugLineNum = 17039438;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"musicplayerui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039439;
 //BA.debugLineNum = 17039439;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1273.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039440;
 //BA.debugLineNum = 17039440;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1272.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039441;
 //BA.debugLineNum = 17039441;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1271.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039443;
 //BA.debugLineNum = 17039443;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dmusicplayerui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039444;
 //BA.debugLineNum = 17039444;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1276.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039445;
 //BA.debugLineNum = 17039445;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1275.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039446;
 //BA.debugLineNum = 17039446;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1274.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 6: {
RDebugUtils.currentLine=17039449;
 //BA.debugLineNum = 17039449;BA.debugLine="titleLbl.Text = \"Notepad\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Notepad"));
RDebugUtils.currentLine=17039450;
 //BA.debugLineNum = 17039450;BA.debugLine="descriptionLbl.Text = \"The notepad keeps all yo";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."));
RDebugUtils.currentLine=17039451;
 //BA.debugLineNum = 17039451;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039452;
 //BA.debugLineNum = 17039452;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"notepadui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039453;
 //BA.debugLineNum = 17039453;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1279.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039454;
 //BA.debugLineNum = 17039454;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1278.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039455;
 //BA.debugLineNum = 17039455;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1277.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039457;
 //BA.debugLineNum = 17039457;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dnotepadui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039458;
 //BA.debugLineNum = 17039458;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1282.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039459;
 //BA.debugLineNum = 17039459;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1281.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039460;
 //BA.debugLineNum = 17039460;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1283.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 7: {
RDebugUtils.currentLine=17039463;
 //BA.debugLineNum = 17039463;BA.debugLine="titleLbl.Text = \"To-do List\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("To-do List"));
RDebugUtils.currentLine=17039464;
 //BA.debugLineNum = 17039464;BA.debugLine="descriptionLbl.Text = \"The to-do list enables y";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."));
RDebugUtils.currentLine=17039465;
 //BA.debugLineNum = 17039465;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039466;
 //BA.debugLineNum = 17039466;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"todoui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039467;
 //BA.debugLineNum = 17039467;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1285.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039468;
 //BA.debugLineNum = 17039468;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1284.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039469;
 //BA.debugLineNum = 17039469;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1283.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039471;
 //BA.debugLineNum = 17039471;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dtodoui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039472;
 //BA.debugLineNum = 17039472;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1288.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039473;
 //BA.debugLineNum = 17039473;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1287.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039474;
 //BA.debugLineNum = 17039474;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1286.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 8: {
RDebugUtils.currentLine=17039477;
 //BA.debugLineNum = 17039477;BA.debugLine="titleLbl.Text = \"Themes\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Themes"));
RDebugUtils.currentLine=17039478;
 //BA.debugLineNum = 17039478;BA.debugLine="descriptionLbl.Text = \"Themes let you put your";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
RDebugUtils.currentLine=17039479;
 //BA.debugLineNum = 17039479;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039480;
 //BA.debugLineNum = 17039480;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"themesui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039481;
 //BA.debugLineNum = 17039481;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1291.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039482;
 //BA.debugLineNum = 17039482;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1290.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039483;
 //BA.debugLineNum = 17039483;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1289.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039485;
 //BA.debugLineNum = 17039485;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dthemesui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039486;
 //BA.debugLineNum = 17039486;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1294.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039487;
 //BA.debugLineNum = 17039487;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1293.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039488;
 //BA.debugLineNum = 17039488;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1292.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 9: {
RDebugUtils.currentLine=17039491;
 //BA.debugLineNum = 17039491;BA.debugLine="titleLbl.Text = \"Lamp\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Lamp"));
RDebugUtils.currentLine=17039492;
 //BA.debugLineNum = 17039492;BA.debugLine="descriptionLbl.Text = \"The lamp gives you contr";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
RDebugUtils.currentLine=17039493;
 //BA.debugLineNum = 17039493;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039494;
 //BA.debugLineNum = 17039494;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lampui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039495;
 //BA.debugLineNum = 17039495;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1297.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039496;
 //BA.debugLineNum = 17039496;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1296.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039497;
 //BA.debugLineNum = 17039497;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1295.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039499;
 //BA.debugLineNum = 17039499;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dlampui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039500;
 //BA.debugLineNum = 17039500;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1298.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039501;
 //BA.debugLineNum = 17039501;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1299.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039502;
 //BA.debugLineNum = 17039502;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"IMG_1300.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
case 10: {
RDebugUtils.currentLine=17039505;
 //BA.debugLineNum = 17039505;BA.debugLine="titleLbl.Text = \"Navigation\"";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence("Navigation"));
RDebugUtils.currentLine=17039506;
 //BA.debugLineNum = 17039506;BA.debugLine="descriptionLbl.Text = \"Navigation is your home";
mostCurrent._descriptionlbl.setText(BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
RDebugUtils.currentLine=17039507;
 //BA.debugLineNum = 17039507;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17039508;
 //BA.debugLineNum = 17039508;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"navigationui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039509;
 //BA.debugLineNum = 17039509;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039510;
 //BA.debugLineNum = 17039510;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Navbtn.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039511;
 //BA.debugLineNum = 17039511;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }else {
RDebugUtils.currentLine=17039513;
 //BA.debugLineNum = 17039513;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
mostCurrent._helpimage.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dnavigationui.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039514;
 //BA.debugLineNum = 17039514;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton1.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039515;
 //BA.debugLineNum = 17039515;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton2.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Navbtn.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=17039516;
 //BA.debugLineNum = 17039516;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
mostCurrent._iconbutton3.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dstar.png",mostCurrent._helpimage.getWidth(),mostCurrent._helpimage.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 };
 break; }
}
;
RDebugUtils.currentLine=17039521;
 //BA.debugLineNum = 17039521;BA.debugLine="backBtn.Enabled = (page > 0)";
mostCurrent._backbtn.setEnabled((_page>0));
RDebugUtils.currentLine=17039522;
 //BA.debugLineNum = 17039522;BA.debugLine="nextBtn.Enabled = (page < 10)";
mostCurrent._nextbtn.setEnabled((_page<10));
RDebugUtils.currentLine=17039523;
 //BA.debugLineNum = 17039523;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="helpactivity";
RDebugUtils.currentLine=16973824;
 //BA.debugLineNum = 16973824;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=16973826;
 //BA.debugLineNum = 16973826;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=16908288;
 //BA.debugLineNum = 16908288;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=16908290;
 //BA.debugLineNum = 16908290;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=17170432;
 //BA.debugLineNum = 17170432;BA.debugLine="Sub backBtn_Click";
RDebugUtils.currentLine=17170433;
 //BA.debugLineNum = 17170433;BA.debugLine="If helpPage > 0 Then";
if (_helppage>0) { 
RDebugUtils.currentLine=17170434;
 //BA.debugLineNum = 17170434;BA.debugLine="showHelpPage(helpPage - 1)";
_showhelppage((int) (_helppage-1));
 };
RDebugUtils.currentLine=17170436;
 //BA.debugLineNum = 17170436;BA.debugLine="End Sub";
return "";
}
public static String  _closehelp_click() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "closehelp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "closehelp_click", null));}
RDebugUtils.currentLine=17235968;
 //BA.debugLineNum = 17235968;BA.debugLine="Sub closeHelp_Click";
RDebugUtils.currentLine=17235969;
 //BA.debugLineNum = 17235969;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=17235970;
 //BA.debugLineNum = 17235970;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="helpactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=17104896;
 //BA.debugLineNum = 17104896;BA.debugLine="Sub nextBtn_Click";
RDebugUtils.currentLine=17104897;
 //BA.debugLineNum = 17104897;BA.debugLine="If helpPage < 10 Then";
if (_helppage<10) { 
RDebugUtils.currentLine=17104898;
 //BA.debugLineNum = 17104898;BA.debugLine="showHelpPage(helpPage + 1)";
_showhelppage((int) (_helppage+1));
 };
RDebugUtils.currentLine=17104900;
 //BA.debugLineNum = 17104900;BA.debugLine="End Sub";
return "";
}
}