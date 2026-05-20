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

public class tutorialactivity extends Activity implements B4AActivity{
	public static tutorialactivity mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.tutorialactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (tutorialactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.tutorialactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.tutorialactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (tutorialactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (tutorialactivity) Resume **");
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
		return tutorialactivity.class;
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
            BA.LogInfo("** Activity (tutorialactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (tutorialactivity) Pause event (activity is not paused). **");
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
            tutorialactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (tutorialactivity) Resume **");
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
public static int _tutorialpage = 0;
public anywheresoftware.b4a.objects.LabelWrapper _headerlbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _bodylbl = null;
public anywheresoftware.b4a.objects.LabelWrapper _pageindicator = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnback = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnnext = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkdontshow = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _tutorialimg = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
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
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="tutorialactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=9043968;
 //BA.debugLineNum = 9043968;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=9043969;
 //BA.debugLineNum = 9043969;BA.debugLine="Activity.LoadLayout(\"tutoriallayout\")";
mostCurrent._activity.LoadLayout("tutoriallayout",mostCurrent.activityBA);
RDebugUtils.currentLine=9043970;
 //BA.debugLineNum = 9043970;BA.debugLine="showTutorialPage(0)";
_showtutorialpage((int) (0));
RDebugUtils.currentLine=9043971;
 //BA.debugLineNum = 9043971;BA.debugLine="End Sub";
return "";
}
public static String  _showtutorialpage(int _page) throws Exception{
RDebugUtils.currentModule="tutorialactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showtutorialpage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showtutorialpage", new Object[] {_page}));}
RDebugUtils.currentLine=9240576;
 //BA.debugLineNum = 9240576;BA.debugLine="Sub showTutorialPage(page As Int)";
RDebugUtils.currentLine=9240577;
 //BA.debugLineNum = 9240577;BA.debugLine="tutorialPage = page";
_tutorialpage = _page;
RDebugUtils.currentLine=9240579;
 //BA.debugLineNum = 9240579;BA.debugLine="Select page";
switch (_page) {
case 0: {
RDebugUtils.currentLine=9240581;
 //BA.debugLineNum = 9240581;BA.debugLine="headerLbl.Text = \"Welcome to Athena!\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Welcome to Athena!"));
RDebugUtils.currentLine=9240582;
 //BA.debugLineNum = 9240582;BA.debugLine="bodyLbl.Text = \"Welcome to Athena — your all-in";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Welcome to Athena — your all-in-one study companion. She's here to keep you focused, organized, and inspired. Before we get started, let's show you around."));
RDebugUtils.currentLine=9240583;
 //BA.debugLineNum = 9240583;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpwreath.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 1: {
RDebugUtils.currentLine=9240586;
 //BA.debugLineNum = 9240586;BA.debugLine="headerLbl.Text = \"Home Screen  (1/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Home Screen  (1/2)"));
RDebugUtils.currentLine=9240587;
 //BA.debugLineNum = 9240587;BA.debugLine="bodyLbl.Text = \"This is your central hub — beau";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("This is your central hub — beautiful and intuitive."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Everything you need lives right here:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Pomodoro Timer  (Clock)"+anywheresoftware.b4a.keywords.Common.CRLF+"  • To-Do List  (PC Screen)"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Calendar"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Flashcards  (Books)"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Corkboard"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Notepad"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Lo-fi Music Player"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Dark / Light Mode  (Lamp)"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Themes  (Plant / Shelf Toy)"));
RDebugUtils.currentLine=9240598;
 //BA.debugLineNum = 9240598;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"homescreenui.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 2: {
RDebugUtils.currentLine=9240601;
 //BA.debugLineNum = 9240601;BA.debugLine="headerLbl.Text = \"Home Screen  (2/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Home Screen  (2/2)"));
RDebugUtils.currentLine=9240602;
 //BA.debugLineNum = 9240602;BA.debugLine="bodyLbl.Text = \"The home screen is alive — subt";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("The home screen is alive — subtle animations and smooth transitions make "+"every interaction feel fluid and enjoyable."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Tapping any feature icon shows a short description of that tool."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Opening Athena for the first time? This Tutorial walks you through everything "+"before you start — so you're never left guessing."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"You can reopen this guide anytime from the Help screen."));
RDebugUtils.currentLine=9240608;
 //BA.debugLineNum = 9240608;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"dhomescreenui.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 3: {
RDebugUtils.currentLine=9240611;
 //BA.debugLineNum = 9240611;BA.debugLine="headerLbl.Text = \"Navigation & Help\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Navigation & Help"));
RDebugUtils.currentLine=9240612;
 //BA.debugLineNum = 9240612;BA.debugLine="bodyLbl.Text = \"Navigation Button — your quick-";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Navigation Button — your quick-access menu."+anywheresoftware.b4a.keywords.Common.CRLF+"Tap it for a clean list of every feature: Pomodoro, Notepad, Corkboard, "+"Flashcards, and more. No clutter — just fast, direct navigation."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Help Button — highlights every clickable object on the home screen "+"and explains what it does, so you'll never feel lost."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Long-press any button to see its name and a step-by-step walkthrough "+"of how to use that specific feature."));
RDebugUtils.currentLine=9240619;
 //BA.debugLineNum = 9240619;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpnav.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 4: {
RDebugUtils.currentLine=9240622;
 //BA.debugLineNum = 9240622;BA.debugLine="headerLbl.Text = \"Leaderboard\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Leaderboard"));
RDebugUtils.currentLine=9240623;
 //BA.debugLineNum = 9240623;BA.debugLine="bodyLbl.Text = \"Leaderboard Button — your ranki";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Leaderboard Button — your ranking board for flashcard progress."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Tap the star button for to see every player's XP, streak, and their ranking!"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Daily, Weekly, All-Time — pick your battleground. Tap any TAB To switch views."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Top card — the #1 spot, front and center."+"The current leader gets the big card up top. That could be you."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Ranking — XP wins, streak breaks ties. More XP = higher rank. Same XP? "+"Longer streak goes first. Still tied? Better correct rate wins."));
RDebugUtils.currentLine=9240630;
 //BA.debugLineNum = 9240630;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helplb.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 5: {
RDebugUtils.currentLine=9240633;
 //BA.debugLineNum = 9240633;BA.debugLine="headerLbl.Text = \"Lamp — Dark / Light Mode\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Lamp — Dark / Light Mode"));
RDebugUtils.currentLine=9240634;
 //BA.debugLineNum = 9240634;BA.debugLine="bodyLbl.Text = \"Tap the Lamp to instantly switc";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the Lamp to instantly switch between Light Mode and Dark Mode."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Whether you're studying under the morning sun or pulling a late-night session, "+"Athena adjusts with you — reducing eye strain and keeping your focus sharp "+"no matter the hour."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"One tap, and your whole environment transforms."));
RDebugUtils.currentLine=9240639;
 //BA.debugLineNum = 9240639;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helplamp.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 6: {
RDebugUtils.currentLine=9240642;
 //BA.debugLineNum = 9240642;BA.debugLine="headerLbl.Text = \"Music Player  (1/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Music Player  (1/2)"));
RDebugUtils.currentLine=9240643;
 //BA.debugLineNum = 9240643;BA.debugLine="bodyLbl.Text = \"Tap the record player and lo-fi";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the record player and lo-fi music fills the room — instantly setting "+"the mood for deep focus."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Controls:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Play / Pause — start or stop the current track."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Previous / Next — move between songs."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Seek bar — jump to any point in the track."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Playlist — tap any song in the list to play it directly."));
RDebugUtils.currentLine=9240650;
 //BA.debugLineNum = 9240650;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpmusic.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 7: {
RDebugUtils.currentLine=9240653;
 //BA.debugLineNum = 9240653;BA.debugLine="headerLbl.Text = \"Music Player  (2/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Music Player  (2/2)"));
RDebugUtils.currentLine=9240654;
 //BA.debugLineNum = 9240654;BA.debugLine="bodyLbl.Text = \"Athena's music library has grow";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Athena's music library has grown — more curated lo-fi tracks are now built in."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Have a song that puts you in the zone? Upload it directly into the app "+"from the player screen anytime."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Your study playlist, your rules."));
RDebugUtils.currentLine=9240658;
 //BA.debugLineNum = 9240658;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpmusic.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 8: {
RDebugUtils.currentLine=9240661;
 //BA.debugLineNum = 9240661;BA.debugLine="headerLbl.Text = \"Clock & Pomodoro  (1/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Clock & Pomodoro  (1/2)"));
RDebugUtils.currentLine=9240662;
 //BA.debugLineNum = 9240662;BA.debugLine="bodyLbl.Text = \"The Clock is more than a timepi";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("The Clock is more than a timepiece — it is your productivity partner."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Real-time clock:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap the format button to switch between 12-hr and 24-hr display."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Pomodoro method — default durations:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Pomodoro session  — 25 minutes"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Short break          —   3 minutes"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Long break            — 10 minutes"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"After four Pomodoro cycles you automatically earn a longer rest."));
RDebugUtils.currentLine=9240670;
 //BA.debugLineNum = 9240670;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpclock.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 9: {
RDebugUtils.currentLine=9240673;
 //BA.debugLineNum = 9240673;BA.debugLine="headerLbl.Text = \"Clock & Pomodoro  (2/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Clock & Pomodoro  (2/2)"));
RDebugUtils.currentLine=9240674;
 //BA.debugLineNum = 9240674;BA.debugLine="bodyLbl.Text = \"Controls:\" & CRLF & _";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Controls:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Start / Pause — begin or pause the current session."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Next — cycle between Pomodoro, short break, and long break."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Settings:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Customize the duration of each timer type to fit your own rhythm."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Change the clock display format."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Stay focused, rest when earned, and keep the cycle going."));
RDebugUtils.currentLine=9240681;
 //BA.debugLineNum = 9240681;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpclock.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 10: {
RDebugUtils.currentLine=9240684;
 //BA.debugLineNum = 9240684;BA.debugLine="headerLbl.Text = \"Flashcards  (1/3)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Flashcards  (1/3)"));
RDebugUtils.currentLine=9240685;
 //BA.debugLineNum = 9240685;BA.debugLine="bodyLbl.Text = \"Tap the Books to open Flashcard";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the Books to open Flashcards — your ultimate memory tool."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Deck management:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap  +  to create a new deck, name it, and save."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Long-press a deck to: add cards, create subdecks, rename, or delete."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Open a deck and tap  +  to add subdecks or cards from inside."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Confirmation is always required before anything is permanently removed."));
RDebugUtils.currentLine=9240691;
 //BA.debugLineNum = 9240691;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpflashcard.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 11: {
RDebugUtils.currentLine=9240694;
 //BA.debugLineNum = 9240694;BA.debugLine="headerLbl.Text = \"Flashcards  (2/3)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Flashcards  (2/3)"));
RDebugUtils.currentLine=9240695;
 //BA.debugLineNum = 9240695;BA.debugLine="bodyLbl.Text = \"Reviewing a deck:\" & CRLF & _";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Reviewing a deck:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Answer — flip the card to reveal the answer."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Next — advance to the next card."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Back — revisit the previous card."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Refresh — reshuffle and restart the deck."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Active Recall challenges you to remember before flipping — one of the most "+"effective techniques for long-term retention."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"A progress bar and percentage show how far you have made it through a deck."));
RDebugUtils.currentLine=9240703;
 //BA.debugLineNum = 9240703;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpflashcard.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 12: {
RDebugUtils.currentLine=9240706;
 //BA.debugLineNum = 9240706;BA.debugLine="headerLbl.Text = \"Flashcards  (3/3)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Flashcards  (3/3)"));
RDebugUtils.currentLine=9240707;
 //BA.debugLineNum = 9240707;BA.debugLine="bodyLbl.Text = \"Athena's AI can do the heavy li";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Athena's AI can do the heavy lifting for you."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Upload a file — a PDF, a document, or your lecture notes — and the AI reads "+"it and automatically generates a full set of flashcards for you."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Your deck, built without the tedious effort."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Because your convenience matters too."));
RDebugUtils.currentLine=9240712;
 //BA.debugLineNum = 9240712;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpflashcard.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 13: {
RDebugUtils.currentLine=9240715;
 //BA.debugLineNum = 9240715;BA.debugLine="headerLbl.Text = \"Calendar  (1/3)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Calendar  (1/3)"));
RDebugUtils.currentLine=9240716;
 //BA.debugLineNum = 9240716;BA.debugLine="bodyLbl.Text = \"Tap the Calendar to open your p";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the Calendar to open your personal time designer."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"By default you land on Month View — your big-picture overview of everything ahead."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Three views via the Menu Button (top-left):"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Schedule View — streamlined list of all upcoming tasks and events."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Day View — focused hour-by-hour agenda for a single day."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Month View — full calendar grid; use the Arrow button to change month/year."));
RDebugUtils.currentLine=9240722;
 //BA.debugLineNum = 9240722;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpcalendar.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 14: {
RDebugUtils.currentLine=9240725;
 //BA.debugLineNum = 9240725;BA.debugLine="headerLbl.Text = \"Calendar  (2/3)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Calendar  (2/3)"));
RDebugUtils.currentLine=9240726;
 //BA.debugLineNum = 9240726;BA.debugLine="bodyLbl.Text = \"Adding events:\" & CRLF & _";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Adding events:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap any date to open that day's hour-by-hour timeline."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap any time slot to create a schedule at that time."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap  +  and choose from:"+anywheresoftware.b4a.keywords.Common.CRLF+"       - Add Event"+anywheresoftware.b4a.keywords.Common.CRLF+"       - Add Task"+anywheresoftware.b4a.keywords.Common.CRLF+"       - Birthday"+anywheresoftware.b4a.keywords.Common.CRLF+"       - Out of Office"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Always tap Save to confirm — or Delete if plans change."));
RDebugUtils.currentLine=9240735;
 //BA.debugLineNum = 9240735;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpcalendar.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 15: {
RDebugUtils.currentLine=9240738;
 //BA.debugLineNum = 9240738;BA.debugLine="headerLbl.Text = \"Calendar  (3/3)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Calendar  (3/3)"));
RDebugUtils.currentLine=9240739;
 //BA.debugLineNum = 9240739;BA.debugLine="bodyLbl.Text = \"Schedules are no longer just pe";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Schedules are no longer just personal."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Group Collaboration lets you create a group with classmates or coworkers "+"and share your schedule with them — keeping everyone on the same page for "+"group projects, study sessions, and deadlines."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Never miss a group deadline again."));
RDebugUtils.currentLine=9240744;
 //BA.debugLineNum = 9240744;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpcalendar.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 16: {
RDebugUtils.currentLine=9240747;
 //BA.debugLineNum = 9240747;BA.debugLine="headerLbl.Text = \"To-Do List  (1/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("To-Do List  (1/2)"));
RDebugUtils.currentLine=9240748;
 //BA.debugLineNum = 9240748;BA.debugLine="bodyLbl.Text = \"Tap the PC Screen to open the T";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the PC Screen to open the To-Do List — your accountability partner."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Structured checkboxes keep every item actionable."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"How to use:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap New List to create a list (school, personal, group work...)."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Open the list, type a task, and press Enter to save."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Check tasks off as you complete them."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Navigate between lists easily from the side panel."));
RDebugUtils.currentLine=9240755;
 //BA.debugLineNum = 9240755;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helptodo.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 17: {
RDebugUtils.currentLine=9240758;
 //BA.debugLineNum = 9240758;BA.debugLine="headerLbl.Text = \"To-Do List  (2/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("To-Do List  (2/2)"));
RDebugUtils.currentLine=9240759;
 //BA.debugLineNum = 9240759;BA.debugLine="bodyLbl.Text = \"Progress tracker:\" & CRLF & _";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Progress tracker:"+anywheresoftware.b4a.keywords.Common.CRLF+"As you tick off tasks, the percentage tracker updates in real time — "+"showing exactly how close you are to finishing each list."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Every checkmark is a small win that adds up to something bigger."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Shared lists:"+anywheresoftware.b4a.keywords.Common.CRLF+"Share a To-Do List with your group — assign tasks, track progress together, "+"and make sure no one falls behind."));
RDebugUtils.currentLine=9240766;
 //BA.debugLineNum = 9240766;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helptodo.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 18: {
RDebugUtils.currentLine=9240769;
 //BA.debugLineNum = 9240769;BA.debugLine="headerLbl.Text = \"Notepad  (1/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Notepad  (1/2)"));
RDebugUtils.currentLine=9240770;
 //BA.debugLineNum = 9240770;BA.debugLine="bodyLbl.Text = \"Tap the Notepad to capture the";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the Notepad to capture the details that matter most."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Creating a note:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Tap  +  to start a new note."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Enter a title and add tags to stay organized."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Write your content, then press Save."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Managing notes:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Long-press a note and confirm to delete it."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Use the search bar — type a title or tag and your note appears instantly."));
RDebugUtils.currentLine=9240778;
 //BA.debugLineNum = 9240778;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpnotepad.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 19: {
RDebugUtils.currentLine=9240781;
 //BA.debugLineNum = 9240781;BA.debugLine="headerLbl.Text = \"Notepad  (2/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Notepad  (2/2)"));
RDebugUtils.currentLine=9240782;
 //BA.debugLineNum = 9240782;BA.debugLine="bodyLbl.Text = \"Athena's AI can summarize for y";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Athena's AI can summarize for you."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Upload a file or paste a block of text, and the AI reads it and generates "+"clean, condensed notes — capturing the key ideas without reading every word."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Lecture notes, project ideas, personal reflections — everything stays "+"structured, accessible, and always within reach."));
RDebugUtils.currentLine=9240787;
 //BA.debugLineNum = 9240787;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpnotepad.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 20: {
RDebugUtils.currentLine=9240790;
 //BA.debugLineNum = 9240790;BA.debugLine="headerLbl.Text = \"Corkboard  (1/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Corkboard  (1/2)"));
RDebugUtils.currentLine=9240791;
 //BA.debugLineNum = 9240791;BA.debugLine="bodyLbl.Text = \"Tap the Corkboard — your digita";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Tap the Corkboard — your digital canvas, fully customizable and personal."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"What you can add:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Sticky Notes — quick reminders or brainstorming in a variety of colors."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Images — pin pictures from your gallery to inspire or organize a project."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Canvas — draw freely with colored pens; sketch ideas or map out concepts."));
RDebugUtils.currentLine=9240796;
 //BA.debugLineNum = 9240796;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpcorkboard.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 21: {
RDebugUtils.currentLine=9240799;
 //BA.debugLineNum = 9240799;BA.debugLine="headerLbl.Text = \"Corkboard  (2/2)\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Corkboard  (2/2)"));
RDebugUtils.currentLine=9240800;
 //BA.debugLineNum = 9240800;BA.debugLine="bodyLbl.Text = \"The Corkboard is where organiza";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("The Corkboard is where organization meets creativity."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Arrange, move, and rearrange everything exactly the way you want — "+"turning abstract ideas into something you can see, interact with, and refine."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"It is not just a workspace. It is a reflection of how you think."));
RDebugUtils.currentLine=9240804;
 //BA.debugLineNum = 9240804;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpcorkboard.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 22: {
RDebugUtils.currentLine=9240807;
 //BA.debugLineNum = 9240807;BA.debugLine="headerLbl.Text = \"Themes\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("Themes"));
RDebugUtils.currentLine=9240808;
 //BA.debugLineNum = 9240808;BA.debugLine="bodyLbl.Text = \"Your workspace should feel like";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Your workspace should feel like yours."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Tap the Plant or the Stuffed Toy on the upper-right shelf to instantly "+"change the app's entire look and feel."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Three themes to choose from:"+anywheresoftware.b4a.keywords.Common.CRLF+"  • Default              — the classic Athena look."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Y2K Aero          — glossy, retro-futuristic vibes."+anywheresoftware.b4a.keywords.Common.CRLF+"  • Pixelated Rustic — cozy pixel-art aesthetic."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"One tap and your environment refreshes — lively, inspiring, and uniquely you."));
RDebugUtils.currentLine=9240816;
 //BA.debugLineNum = 9240816;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helptheme.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
case 23: {
RDebugUtils.currentLine=9240819;
 //BA.debugLineNum = 9240819;BA.debugLine="headerLbl.Text = \"You're All Set!\"";
mostCurrent._headerlbl.setText(BA.ObjectToCharSequence("You're All Set!"));
RDebugUtils.currentLine=9240820;
 //BA.debugLineNum = 9240820;BA.debugLine="bodyLbl.Text = \"Everything you need is right at";
mostCurrent._bodylbl.setText(BA.ObjectToCharSequence("Everything you need is right at your fingertips — now go use Athena to your heart and brain's content."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Tick \"Don't show again\" below To skip this guide on future launches. You always have the Help Screen And Long Press feature If you ever need help. "+anywheresoftware.b4a.keywords.Common.CRLF+""));
RDebugUtils.currentLine=9240822;
 //BA.debugLineNum = 9240822;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
mostCurrent._tutorialimg.setBitmap((android.graphics.Bitmap)(_xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"helpwreath.png",mostCurrent._tutorialimg.getWidth(),mostCurrent._tutorialimg.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 break; }
}
;
RDebugUtils.currentLine=9240826;
 //BA.debugLineNum = 9240826;BA.debugLine="pageIndicator.Text = (page + 1) & \" / 24\"";
mostCurrent._pageindicator.setText(BA.ObjectToCharSequence(BA.NumberToString((_page+1))+" / 24"));
RDebugUtils.currentLine=9240829;
 //BA.debugLineNum = 9240829;BA.debugLine="chkDontShow.Visible = (page = 23)";
mostCurrent._chkdontshow.setVisible((_page==23));
RDebugUtils.currentLine=9240832;
 //BA.debugLineNum = 9240832;BA.debugLine="btnBack.Enabled = (page > 0)";
mostCurrent._btnback.setEnabled((_page>0));
RDebugUtils.currentLine=9240835;
 //BA.debugLineNum = 9240835;BA.debugLine="If page = 23 Then";
if (_page==23) { 
RDebugUtils.currentLine=9240836;
 //BA.debugLineNum = 9240836;BA.debugLine="btnNext.Text = \"Finish\"";
mostCurrent._btnnext.setText(BA.ObjectToCharSequence("Finish"));
 }else {
RDebugUtils.currentLine=9240838;
 //BA.debugLineNum = 9240838;BA.debugLine="btnNext.Text = \"Next\"";
mostCurrent._btnnext.setText(BA.ObjectToCharSequence("Next"));
 };
RDebugUtils.currentLine=9240840;
 //BA.debugLineNum = 9240840;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="tutorialactivity";
RDebugUtils.currentLine=9175040;
 //BA.debugLineNum = 9175040;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
RDebugUtils.currentLine=9175042;
 //BA.debugLineNum = 9175042;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="tutorialactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=9109504;
 //BA.debugLineNum = 9109504;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=9109506;
 //BA.debugLineNum = 9109506;BA.debugLine="End Sub";
return "";
}
public static String  _btnback_click() throws Exception{
RDebugUtils.currentModule="tutorialactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnback_click", null));}
RDebugUtils.currentLine=9371648;
 //BA.debugLineNum = 9371648;BA.debugLine="Sub btnBack_Click";
RDebugUtils.currentLine=9371649;
 //BA.debugLineNum = 9371649;BA.debugLine="If tutorialPage > 0 Then";
if (_tutorialpage>0) { 
RDebugUtils.currentLine=9371650;
 //BA.debugLineNum = 9371650;BA.debugLine="showTutorialPage(tutorialPage - 1)";
_showtutorialpage((int) (_tutorialpage-1));
 };
RDebugUtils.currentLine=9371652;
 //BA.debugLineNum = 9371652;BA.debugLine="End Sub";
return "";
}
public static String  _btnnext_click() throws Exception{
RDebugUtils.currentModule="tutorialactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnnext_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnnext_click", null));}
RDebugUtils.currentLine=9306112;
 //BA.debugLineNum = 9306112;BA.debugLine="Sub btnNext_Click";
RDebugUtils.currentLine=9306113;
 //BA.debugLineNum = 9306113;BA.debugLine="If tutorialPage < 23 Then";
if (_tutorialpage<23) { 
RDebugUtils.currentLine=9306114;
 //BA.debugLineNum = 9306114;BA.debugLine="showTutorialPage(tutorialPage + 1)";
_showtutorialpage((int) (_tutorialpage+1));
 }else {
RDebugUtils.currentLine=9306116;
 //BA.debugLineNum = 9306116;BA.debugLine="If chkDontShow.Checked Then";
if (mostCurrent._chkdontshow.getChecked()) { 
RDebugUtils.currentLine=9306117;
 //BA.debugLineNum = 9306117;BA.debugLine="Starter.prefKvs.Put(\"skipTutorial\", True)";
mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ._put("skipTutorial",(Object)(anywheresoftware.b4a.keywords.Common.True));
 };
RDebugUtils.currentLine=9306119;
 //BA.debugLineNum = 9306119;BA.debugLine="StartActivity(MainActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._mainactivity.getObject()));
RDebugUtils.currentLine=9306120;
 //BA.debugLineNum = 9306120;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
RDebugUtils.currentLine=9306122;
 //BA.debugLineNum = 9306122;BA.debugLine="End Sub";
return "";
}
}