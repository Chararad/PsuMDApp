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

public class active_recall extends Activity implements B4AActivity{
	public static active_recall mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.active_recall");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (active_recall).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.active_recall");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.active_recall", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (active_recall) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (active_recall) Resume **");
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
		return active_recall.class;
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
            BA.LogInfo("** Activity (active_recall) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (active_recall) Pause event (activity is not paused). **");
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
            active_recall mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (active_recall) Resume **");
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
public static boolean _praise = false;
public anywheresoftware.b4a.objects.LabelWrapper _deckname_label = null;
public anywheresoftware.b4a.objects.LabelWrapper _question = null;
public anywheresoftware.b4a.objects.LabelWrapper _answer = null;
public anywheresoftware.b4a.objects.collections.List _cards = null;
public static int _currentindex = 0;
public anywheresoftware.b4a.objects.ButtonWrapper _showanswerbtn = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _pb = null;
public anywheresoftware.b4a.objects.LabelWrapper _progress = null;
public anywheresoftware.b4a.objects.ButtonWrapper _nextbtn = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.day_module _day_module = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.clockactivity _clockactivity = null;
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
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
 //BA.debugLineNum = 29;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 33;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 35;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark\")";
mostCurrent._activity.LoadLayout("AARLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
 //BA.debugLineNum = 41;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark\")";
mostCurrent._activity.LoadLayout("AARLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 2: {
 //BA.debugLineNum = 47;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 50;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark\")";
mostCurrent._activity.LoadLayout("AARLayoutDark",mostCurrent.activityBA);
 };
 break; }
}
;
 //BA.debugLineNum = 55;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 57;BA.debugLine="cards = tappeddeck.Get(Subdeck_Module.selectedsub";
mostCurrent._cards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
 //BA.debugLineNum = 59;BA.debugLine="currentindex = 0";
_currentindex = (int) (0);
 //BA.debugLineNum = 60;BA.debugLine="DeckName_Label.text = Subdeck_Module.selectedsubd";
mostCurrent._deckname_label.setText(BA.ObjectToCharSequence(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ));
 //BA.debugLineNum = 62;BA.debugLine="ShuffleCards(cards)";
_shufflecards(mostCurrent._cards);
 //BA.debugLineNum = 63;BA.debugLine="ShowProgress";
_showprogress();
 //BA.debugLineNum = 65;BA.debugLine="ShowCard";
_showcard();
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 102;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Private Sub backbtn_Click";
 //BA.debugLineNum = 150;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
 //BA.debugLineNum = 151;BA.debugLine="currentindex = currentindex -1";
_currentindex = (int) (_currentindex-1);
 //BA.debugLineNum = 152;BA.debugLine="If currentindex < 0 Then";
if (_currentindex<0) { 
 //BA.debugLineNum = 153;BA.debugLine="currentindex = cards.Size -1";
_currentindex = (int) (mostCurrent._cards.getSize()-1);
 };
 //BA.debugLineNum = 156;BA.debugLine="ShowCard";
_showcard();
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private DeckName_Label As Label";
mostCurrent._deckname_label = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private Question As Label";
mostCurrent._question = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private Answer As Label";
mostCurrent._answer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim cards As List";
mostCurrent._cards = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 21;BA.debugLine="Dim currentindex As Int 'to find the current inde";
_currentindex = 0;
 //BA.debugLineNum = 22;BA.debugLine="Private showAnswerbtn As Button";
mostCurrent._showanswerbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private pb As ProgressBar";
mostCurrent._pb = new anywheresoftware.b4a.objects.ProgressBarWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Progress As Label";
mostCurrent._progress = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private nextbtn As Button";
mostCurrent._nextbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _goback_click() throws Exception{
 //BA.debugLineNum = 143;BA.debugLine="Private Sub goback_Click";
 //BA.debugLineNum = 145;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Private Sub nextbtn_Click";
 //BA.debugLineNum = 127;BA.debugLine="nextbtn.Visible = False";
mostCurrent._nextbtn.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 129;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
 //BA.debugLineNum = 130;BA.debugLine="currentindex = currentindex +1";
_currentindex = (int) (_currentindex+1);
 //BA.debugLineNum = 132;BA.debugLine="If currentindex >= cards.Size Then";
if (_currentindex>=mostCurrent._cards.getSize()) { 
 //BA.debugLineNum = 133;BA.debugLine="ShuffleCards(cards)";
_shufflecards(mostCurrent._cards);
 //BA.debugLineNum = 134;BA.debugLine="praise = True";
_praise = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 135;BA.debugLine="MsgboxAsync(\"You've finished your subdeck\", \"Con";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You've finished your subdeck"),BA.ObjectToCharSequence("Congratulations"),processBA);
 //BA.debugLineNum = 136;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 137;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 139;BA.debugLine="ShowCard";
_showcard();
 //BA.debugLineNum = 140;BA.debugLine="ShowProgress";
_showprogress();
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim praise As Boolean = False";
_praise = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _showanswerbtn_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _card = null;
 //BA.debugLineNum = 107;BA.debugLine="Private Sub showAnswerbtn_Click";
 //BA.debugLineNum = 110;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._cards.Get(_currentindex)));
 //BA.debugLineNum = 112;BA.debugLine="If showAnswerbtn.Text = \"Show Answer\" Then";
if ((mostCurrent._showanswerbtn.getText()).equals("Show Answer")) { 
 //BA.debugLineNum = 114;BA.debugLine="Answer.Text = card.Get(\"A\")";
mostCurrent._answer.setText(BA.ObjectToCharSequence(_card.Get((Object)("A"))));
 //BA.debugLineNum = 115;BA.debugLine="showAnswerbtn.Text = \"Hide Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Hide Answer"));
 }else {
 //BA.debugLineNum = 118;BA.debugLine="Answer.Text = \"\"";
mostCurrent._answer.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 119;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
 };
 //BA.debugLineNum = 123;BA.debugLine="nextbtn.Visible = True";
mostCurrent._nextbtn.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static String  _showcard() throws Exception{
anywheresoftware.b4a.objects.collections.Map _card = null;
 //BA.debugLineNum = 88;BA.debugLine="Sub ShowCard";
 //BA.debugLineNum = 91;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._cards.Get(_currentindex)));
 //BA.debugLineNum = 93;BA.debugLine="Question.Text = card.Get(\"Q\")";
mostCurrent._question.setText(BA.ObjectToCharSequence(_card.Get((Object)("Q"))));
 //BA.debugLineNum = 94;BA.debugLine="Answer.Text = \"\"";
mostCurrent._answer.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _showprogress() throws Exception{
int _totalsession = 0;
int _studied = 0;
int _percent = 0;
 //BA.debugLineNum = 69;BA.debugLine="Sub ShowProgress";
 //BA.debugLineNum = 70;BA.debugLine="Dim totalsession As Int = cards.Size";
_totalsession = mostCurrent._cards.getSize();
 //BA.debugLineNum = 71;BA.debugLine="Dim studied As Int = currentindex";
_studied = _currentindex;
 //BA.debugLineNum = 72;BA.debugLine="Dim percent As Int = (studied * 100)/ totalsessio";
_percent = (int) ((_studied*100)/(double)_totalsession);
 //BA.debugLineNum = 73;BA.debugLine="pb.Progress = percent";
mostCurrent._pb.setProgress(_percent);
 //BA.debugLineNum = 74;BA.debugLine="Progress.Text = studied & \"/\" & totalsession & \"";
mostCurrent._progress.setText(BA.ObjectToCharSequence(BA.NumberToString(_studied)+"/"+BA.NumberToString(_totalsession)+" "+BA.NumberToString(_percent)+"%"));
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _shufflecards(anywheresoftware.b4a.objects.collections.List _cardlist) throws Exception{
int _i = 0;
int _j = 0;
Object _temp = null;
 //BA.debugLineNum = 77;BA.debugLine="Sub ShuffleCards(cardList As List)";
 //BA.debugLineNum = 79;BA.debugLine="For i = cardList.Size-1 To 1 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_i = (int) (_cardlist.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 80;BA.debugLine="Dim j As Int = Rnd(0, i+1)";
_j = anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (_i+1));
 //BA.debugLineNum = 82;BA.debugLine="Dim temp As Object = cardList.Get(i)";
_temp = _cardlist.Get(_i);
 //BA.debugLineNum = 83;BA.debugLine="cardList.Set(i, cardList.Get(j))";
_cardlist.Set(_i,_cardlist.Get(_j));
 //BA.debugLineNum = 84;BA.debugLine="cardList.Set(j, temp)";
_cardlist.Set(_j,_temp);
 }
};
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
}
