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

public class add_card_module2 extends Activity implements B4AActivity{
	public static add_card_module2 mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.add_card_module2");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (add_card_module2).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.add_card_module2");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.add_card_module2", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (add_card_module2) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (add_card_module2) Resume **");
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
		return add_card_module2.class;
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
            BA.LogInfo("** Activity (add_card_module2) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (add_card_module2) Pause event (activity is not paused). **");
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
            add_card_module2 mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (add_card_module2) Resume **");
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
public anywheresoftware.b4a.objects.LabelWrapper _subdeckname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _questionet = null;
public anywheresoftware.b4a.objects.EditTextWrapper _answeret = null;
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
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="add_card_module2";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _flashcard = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
RDebugUtils.currentLine=12058624;
 //BA.debugLineNum = 12058624;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=12058626;
 //BA.debugLineNum = 12058626;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=12058628;
 //BA.debugLineNum = 12058628;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12058629;
 //BA.debugLineNum = 12058629;BA.debugLine="Activity.LoadLayout(\"ACM2Layout\")";
mostCurrent._activity.LoadLayout("ACM2Layout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12058631;
 //BA.debugLineNum = 12058631;BA.debugLine="Activity.LoadLayout(\"ACM2LayoutDark\")";
mostCurrent._activity.LoadLayout("ACM2LayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=12058634;
 //BA.debugLineNum = 12058634;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12058635;
 //BA.debugLineNum = 12058635;BA.debugLine="Activity.LoadLayout(\"ACM2Layout2\")";
mostCurrent._activity.LoadLayout("ACM2Layout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12058637;
 //BA.debugLineNum = 12058637;BA.debugLine="Activity.LoadLayout(\"ACM2LayoutDark2\")";
mostCurrent._activity.LoadLayout("ACM2LayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=12058640;
 //BA.debugLineNum = 12058640;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=12058641;
 //BA.debugLineNum = 12058641;BA.debugLine="Activity.LoadLayout(\"ACM2Layout3\")";
mostCurrent._activity.LoadLayout("ACM2Layout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=12058643;
 //BA.debugLineNum = 12058643;BA.debugLine="Activity.LoadLayout(\"ACM2LayoutDark3\")";
mostCurrent._activity.LoadLayout("ACM2LayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=12058648;
 //BA.debugLineNum = 12058648;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=12058650;
 //BA.debugLineNum = 12058650;BA.debugLine="subdeckname.text = Card_Module.subdeck";
mostCurrent._subdeckname.setText(BA.ObjectToCharSequence(mostCurrent._card_module._subdeck /*String*/ ));
RDebugUtils.currentLine=12058652;
 //BA.debugLineNum = 12058652;BA.debugLine="If Card_Module.isedit = True Then";
if (mostCurrent._card_module._isedit /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=12058653;
 //BA.debugLineNum = 12058653;BA.debugLine="Dim flashcard As List = tappeddeck.Get(Card_Modu";
_flashcard = new anywheresoftware.b4a.objects.collections.List();
_flashcard = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._card_module._subdeck /*String*/ ))));
RDebugUtils.currentLine=12058654;
 //BA.debugLineNum = 12058654;BA.debugLine="Dim card As Map = flashcard.Get(Card_Module.edit";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_flashcard.Get(mostCurrent._card_module._editindex /*int*/ )));
RDebugUtils.currentLine=12058655;
 //BA.debugLineNum = 12058655;BA.debugLine="QuestionET.Text = card.Get(\"Q\")";
mostCurrent._questionet.setText(BA.ObjectToCharSequence(_card.Get((Object)("Q"))));
RDebugUtils.currentLine=12058656;
 //BA.debugLineNum = 12058656;BA.debugLine="AnswerET.Text = card.Get(\"A\")";
mostCurrent._answeret.setText(BA.ObjectToCharSequence(_card.Get((Object)("A"))));
 };
RDebugUtils.currentLine=12058659;
 //BA.debugLineNum = 12058659;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="add_card_module2";
RDebugUtils.currentLine=12255232;
 //BA.debugLineNum = 12255232;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=12255233;
 //BA.debugLineNum = 12255233;BA.debugLine="Card_Module.Isedit = False";
mostCurrent._card_module._isedit /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12255234;
 //BA.debugLineNum = 12255234;BA.debugLine="QuestionET.Text = \"\"";
mostCurrent._questionet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=12255235;
 //BA.debugLineNum = 12255235;BA.debugLine="AnswerET.Text = \"\"";
mostCurrent._answeret.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=12255236;
 //BA.debugLineNum = 12255236;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="add_card_module2";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=12189696;
 //BA.debugLineNum = 12189696;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=12189698;
 //BA.debugLineNum = 12189698;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="add_card_module2";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=12320768;
 //BA.debugLineNum = 12320768;BA.debugLine="Private Sub backbtn_Click";
RDebugUtils.currentLine=12320770;
 //BA.debugLineNum = 12320770;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=12320771;
 //BA.debugLineNum = 12320771;BA.debugLine="End Sub";
return "";
}
public static String  _savecard_click() throws Exception{
RDebugUtils.currentModule="add_card_module2";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savecard_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savecard_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _editcards = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
anywheresoftware.b4a.objects.collections.Map _cards = null;
RDebugUtils.currentLine=12386304;
 //BA.debugLineNum = 12386304;BA.debugLine="Private Sub SaveCard_Click";
RDebugUtils.currentLine=12386306;
 //BA.debugLineNum = 12386306;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=12386308;
 //BA.debugLineNum = 12386308;BA.debugLine="If Card_Module.isEdit = True Then";
if (mostCurrent._card_module._isedit /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=12386309;
 //BA.debugLineNum = 12386309;BA.debugLine="Card_Module.isEdit = False";
mostCurrent._card_module._isedit /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12386312;
 //BA.debugLineNum = 12386312;BA.debugLine="If QuestionET.Text = \"\" Then";
if ((mostCurrent._questionet.getText()).equals("")) { 
RDebugUtils.currentLine=12386313;
 //BA.debugLineNum = 12386313;BA.debugLine="MsgboxAsync(\"Enter the Question!\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Enter the Question!"),BA.ObjectToCharSequence("Error"),processBA);
 }else 
{RDebugUtils.currentLine=12386314;
 //BA.debugLineNum = 12386314;BA.debugLine="Else if AnswerET.Text = \"\" Then";
if ((mostCurrent._answeret.getText()).equals("")) { 
RDebugUtils.currentLine=12386315;
 //BA.debugLineNum = 12386315;BA.debugLine="MsgboxAsync(\"Enter the Answer!\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Enter the Answer!"),BA.ObjectToCharSequence("Error"),processBA);
 }else {
RDebugUtils.currentLine=12386318;
 //BA.debugLineNum = 12386318;BA.debugLine="Dim Editcards As List = tappeddeck.Get(Card_Mod";
_editcards = new anywheresoftware.b4a.objects.collections.List();
_editcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._card_module._subdeck /*String*/ ))));
RDebugUtils.currentLine=12386319;
 //BA.debugLineNum = 12386319;BA.debugLine="Dim card As Map = Editcards.Get(Card_Module.edi";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_editcards.Get(mostCurrent._card_module._editindex /*int*/ )));
RDebugUtils.currentLine=12386320;
 //BA.debugLineNum = 12386320;BA.debugLine="card.Put(\"Q\", QuestionET.text)";
_card.Put((Object)("Q"),(Object)(mostCurrent._questionet.getText()));
RDebugUtils.currentLine=12386321;
 //BA.debugLineNum = 12386321;BA.debugLine="card.Put(\"A\", AnswerET.text)";
_card.Put((Object)("A"),(Object)(mostCurrent._answeret.getText()));
 }}
;
RDebugUtils.currentLine=12386323;
 //BA.debugLineNum = 12386323;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=12386324;
 //BA.debugLineNum = 12386324;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=12386325;
 //BA.debugLineNum = 12386325;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=12386332;
 //BA.debugLineNum = 12386332;BA.debugLine="If QuestionET.Text = \"\" Then";
if ((mostCurrent._questionet.getText()).equals("")) { 
RDebugUtils.currentLine=12386333;
 //BA.debugLineNum = 12386333;BA.debugLine="MsgboxAsync(\"Enter the Question!\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Enter the Question!"),BA.ObjectToCharSequence("Error"),processBA);
 }else 
{RDebugUtils.currentLine=12386334;
 //BA.debugLineNum = 12386334;BA.debugLine="Else if AnswerET.Text = \"\" Then";
if ((mostCurrent._answeret.getText()).equals("")) { 
RDebugUtils.currentLine=12386335;
 //BA.debugLineNum = 12386335;BA.debugLine="MsgboxAsync(\"Enter the Answer!\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Enter the Answer!"),BA.ObjectToCharSequence("Error"),processBA);
 }else {
RDebugUtils.currentLine=12386340;
 //BA.debugLineNum = 12386340;BA.debugLine="Dim flashcards As List = tappeddeck.Get(Card_Mod";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._card_module._subdeck /*String*/ ))));
RDebugUtils.currentLine=12386343;
 //BA.debugLineNum = 12386343;BA.debugLine="Dim cards As Map";
_cards = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=12386344;
 //BA.debugLineNum = 12386344;BA.debugLine="cards.initialize";
_cards.Initialize();
RDebugUtils.currentLine=12386345;
 //BA.debugLineNum = 12386345;BA.debugLine="cards.Put(\"Q\", QuestionET.Text)";
_cards.Put((Object)("Q"),(Object)(mostCurrent._questionet.getText()));
RDebugUtils.currentLine=12386346;
 //BA.debugLineNum = 12386346;BA.debugLine="cards.Put(\"A\", AnswerET.Text)";
_cards.Put((Object)("A"),(Object)(mostCurrent._answeret.getText()));
RDebugUtils.currentLine=12386349;
 //BA.debugLineNum = 12386349;BA.debugLine="flashcards.Add(cards)";
_flashcards.Add((Object)(_cards.getObject()));
RDebugUtils.currentLine=12386350;
 //BA.debugLineNum = 12386350;BA.debugLine="QuestionET.Text = \"\"";
mostCurrent._questionet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=12386351;
 //BA.debugLineNum = 12386351;BA.debugLine="AnswerET.Text = \"\"";
mostCurrent._answeret.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=12386353;
 //BA.debugLineNum = 12386353;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=12386355;
 //BA.debugLineNum = 12386355;BA.debugLine="MsgboxAsync(\"Card is Successfully Saved\", \"Card";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Card is Successfully Saved"),BA.ObjectToCharSequence("Card Saved!"),processBA);
 }}
;
RDebugUtils.currentLine=12386358;
 //BA.debugLineNum = 12386358;BA.debugLine="End Sub";
return "";
}
public static String  _savedecks() throws Exception{
RDebugUtils.currentModule="add_card_module2";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savedecks", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savedecks", null));}
RDebugUtils.currentLine=12124160;
 //BA.debugLineNum = 12124160;BA.debugLine="Sub SaveDecks";
RDebugUtils.currentLine=12124161;
 //BA.debugLineNum = 12124161;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
mostCurrent._flashcardactivity._kvs /*b4a.example3.keyvaluestore*/ ._put("deck_data",(Object)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
RDebugUtils.currentLine=12124162;
 //BA.debugLineNum = 12124162;BA.debugLine="End Sub";
return "";
}
}