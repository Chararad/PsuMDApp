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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.active_recall");
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



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.leaderboard _leaderboard = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
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
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=10747904;
 //BA.debugLineNum = 10747904;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=10747908;
 //BA.debugLineNum = 10747908;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=10747910;
 //BA.debugLineNum = 10747910;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10747911;
 //BA.debugLineNum = 10747911;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=10747913;
 //BA.debugLineNum = 10747913;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark\")";
mostCurrent._activity.LoadLayout("AARLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=10747916;
 //BA.debugLineNum = 10747916;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10747917;
 //BA.debugLineNum = 10747917;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=10747919;
 //BA.debugLineNum = 10747919;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark\")";
mostCurrent._activity.LoadLayout("AARLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=10747922;
 //BA.debugLineNum = 10747922;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10747923;
 //BA.debugLineNum = 10747923;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=10747925;
 //BA.debugLineNum = 10747925;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark\")";
mostCurrent._activity.LoadLayout("AARLayoutDark",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=10747930;
 //BA.debugLineNum = 10747930;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=10747932;
 //BA.debugLineNum = 10747932;BA.debugLine="cards = tappeddeck.Get(Subdeck_Module.selectedsub";
mostCurrent._cards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ))));
RDebugUtils.currentLine=10747934;
 //BA.debugLineNum = 10747934;BA.debugLine="currentindex = 0";
_currentindex = (int) (0);
RDebugUtils.currentLine=10747935;
 //BA.debugLineNum = 10747935;BA.debugLine="DeckName_Label.text = Subdeck_Module.selectedsubd";
mostCurrent._deckname_label.setText(BA.ObjectToCharSequence(mostCurrent._subdeck_module._selectedsubdeck /*String*/ ));
RDebugUtils.currentLine=10747937;
 //BA.debugLineNum = 10747937;BA.debugLine="ShuffleCards(cards)";
_shufflecards(mostCurrent._cards);
RDebugUtils.currentLine=10747938;
 //BA.debugLineNum = 10747938;BA.debugLine="ShowProgress";
_showprogress();
RDebugUtils.currentLine=10747940;
 //BA.debugLineNum = 10747940;BA.debugLine="ShowCard";
_showcard();
RDebugUtils.currentLine=10747942;
 //BA.debugLineNum = 10747942;BA.debugLine="End Sub";
return "";
}
public static String  _shufflecards(anywheresoftware.b4a.objects.collections.List _cardlist) throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "shufflecards", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "shufflecards", new Object[] {_cardlist}));}
int _i = 0;
int _j = 0;
Object _temp = null;
RDebugUtils.currentLine=10878976;
 //BA.debugLineNum = 10878976;BA.debugLine="Sub ShuffleCards(cardList As List)";
RDebugUtils.currentLine=10878978;
 //BA.debugLineNum = 10878978;BA.debugLine="For i = cardList.Size-1 To 1 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_i = (int) (_cardlist.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
RDebugUtils.currentLine=10878979;
 //BA.debugLineNum = 10878979;BA.debugLine="Dim j As Int = Rnd(0, i+1)";
_j = anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (_i+1));
RDebugUtils.currentLine=10878981;
 //BA.debugLineNum = 10878981;BA.debugLine="Dim temp As Object = cardList.Get(i)";
_temp = _cardlist.Get(_i);
RDebugUtils.currentLine=10878982;
 //BA.debugLineNum = 10878982;BA.debugLine="cardList.Set(i, cardList.Get(j))";
_cardlist.Set(_i,_cardlist.Get(_j));
RDebugUtils.currentLine=10878983;
 //BA.debugLineNum = 10878983;BA.debugLine="cardList.Set(j, temp)";
_cardlist.Set(_j,_temp);
 }
};
RDebugUtils.currentLine=10878985;
 //BA.debugLineNum = 10878985;BA.debugLine="End Sub";
return "";
}
public static String  _showprogress() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showprogress", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showprogress", null));}
int _totalsession = 0;
int _studied = 0;
int _percent = 0;
RDebugUtils.currentLine=10813440;
 //BA.debugLineNum = 10813440;BA.debugLine="Sub ShowProgress";
RDebugUtils.currentLine=10813441;
 //BA.debugLineNum = 10813441;BA.debugLine="Dim totalsession As Int = cards.Size";
_totalsession = mostCurrent._cards.getSize();
RDebugUtils.currentLine=10813442;
 //BA.debugLineNum = 10813442;BA.debugLine="Dim studied As Int = currentindex";
_studied = _currentindex;
RDebugUtils.currentLine=10813443;
 //BA.debugLineNum = 10813443;BA.debugLine="Dim percent As Int = (studied * 100)/ totalsessio";
_percent = (int) ((_studied*100)/(double)_totalsession);
RDebugUtils.currentLine=10813444;
 //BA.debugLineNum = 10813444;BA.debugLine="pb.Progress = percent";
mostCurrent._pb.setProgress(_percent);
RDebugUtils.currentLine=10813445;
 //BA.debugLineNum = 10813445;BA.debugLine="Progress.Text = studied & \"/\" & totalsession & \"";
mostCurrent._progress.setText(BA.ObjectToCharSequence(BA.NumberToString(_studied)+"/"+BA.NumberToString(_totalsession)+" "+BA.NumberToString(_percent)+"%"));
RDebugUtils.currentLine=10813446;
 //BA.debugLineNum = 10813446;BA.debugLine="End Sub";
return "";
}
public static String  _showcard() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showcard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showcard", null));}
anywheresoftware.b4a.objects.collections.Map _card = null;
RDebugUtils.currentLine=10944512;
 //BA.debugLineNum = 10944512;BA.debugLine="Sub ShowCard";
RDebugUtils.currentLine=10944515;
 //BA.debugLineNum = 10944515;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._cards.Get(_currentindex)));
RDebugUtils.currentLine=10944517;
 //BA.debugLineNum = 10944517;BA.debugLine="Question.Text = card.Get(\"Q\")";
mostCurrent._question.setText(BA.ObjectToCharSequence(_card.Get((Object)("Q"))));
RDebugUtils.currentLine=10944518;
 //BA.debugLineNum = 10944518;BA.debugLine="Answer.Text = \"\"";
mostCurrent._answer.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=10944519;
 //BA.debugLineNum = 10944519;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="active_recall";
RDebugUtils.currentLine=11075584;
 //BA.debugLineNum = 11075584;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=11075586;
 //BA.debugLineNum = 11075586;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=11010048;
 //BA.debugLineNum = 11010048;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=11010050;
 //BA.debugLineNum = 11010050;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=11337728;
 //BA.debugLineNum = 11337728;BA.debugLine="Private Sub backbtn_Click";
RDebugUtils.currentLine=11337730;
 //BA.debugLineNum = 11337730;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
RDebugUtils.currentLine=11337731;
 //BA.debugLineNum = 11337731;BA.debugLine="currentindex = currentindex -1";
_currentindex = (int) (_currentindex-1);
RDebugUtils.currentLine=11337732;
 //BA.debugLineNum = 11337732;BA.debugLine="If currentindex < 0 Then";
if (_currentindex<0) { 
RDebugUtils.currentLine=11337733;
 //BA.debugLineNum = 11337733;BA.debugLine="currentindex = cards.Size -1";
_currentindex = (int) (mostCurrent._cards.getSize()-1);
 };
RDebugUtils.currentLine=11337736;
 //BA.debugLineNum = 11337736;BA.debugLine="ShowCard";
_showcard();
RDebugUtils.currentLine=11337738;
 //BA.debugLineNum = 11337738;BA.debugLine="End Sub";
return "";
}
public static String  _goback_click() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "goback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "goback_click", null));}
RDebugUtils.currentLine=11272192;
 //BA.debugLineNum = 11272192;BA.debugLine="Private Sub goback_Click";
RDebugUtils.currentLine=11272194;
 //BA.debugLineNum = 11272194;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=11272195;
 //BA.debugLineNum = 11272195;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=11206656;
 //BA.debugLineNum = 11206656;BA.debugLine="Private Sub nextbtn_Click";
RDebugUtils.currentLine=11206657;
 //BA.debugLineNum = 11206657;BA.debugLine="nextbtn.Visible = False";
mostCurrent._nextbtn.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=11206659;
 //BA.debugLineNum = 11206659;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
RDebugUtils.currentLine=11206660;
 //BA.debugLineNum = 11206660;BA.debugLine="currentindex = currentindex +1";
_currentindex = (int) (_currentindex+1);
RDebugUtils.currentLine=11206662;
 //BA.debugLineNum = 11206662;BA.debugLine="If currentindex >= cards.Size Then";
if (_currentindex>=mostCurrent._cards.getSize()) { 
RDebugUtils.currentLine=11206663;
 //BA.debugLineNum = 11206663;BA.debugLine="ShuffleCards(cards)";
_shufflecards(mostCurrent._cards);
RDebugUtils.currentLine=11206664;
 //BA.debugLineNum = 11206664;BA.debugLine="praise = True";
_praise = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=11206665;
 //BA.debugLineNum = 11206665;BA.debugLine="MsgboxAsync(\"You've finished your subdeck\", \"Con";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You've finished your subdeck"),BA.ObjectToCharSequence("Congratulations"),processBA);
RDebugUtils.currentLine=11206666;
 //BA.debugLineNum = 11206666;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=11206667;
 //BA.debugLineNum = 11206667;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=11206669;
 //BA.debugLineNum = 11206669;BA.debugLine="ShowCard";
_showcard();
RDebugUtils.currentLine=11206670;
 //BA.debugLineNum = 11206670;BA.debugLine="ShowProgress";
_showprogress();
RDebugUtils.currentLine=11206671;
 //BA.debugLineNum = 11206671;BA.debugLine="End Sub";
return "";
}
public static String  _showanswerbtn_click() throws Exception{
RDebugUtils.currentModule="active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showanswerbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showanswerbtn_click", null));}
anywheresoftware.b4a.objects.collections.Map _card = null;
RDebugUtils.currentLine=11141120;
 //BA.debugLineNum = 11141120;BA.debugLine="Private Sub showAnswerbtn_Click";
RDebugUtils.currentLine=11141123;
 //BA.debugLineNum = 11141123;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._cards.Get(_currentindex)));
RDebugUtils.currentLine=11141125;
 //BA.debugLineNum = 11141125;BA.debugLine="If showAnswerbtn.Text = \"Show Answer\" Then";
if ((mostCurrent._showanswerbtn.getText()).equals("Show Answer")) { 
RDebugUtils.currentLine=11141127;
 //BA.debugLineNum = 11141127;BA.debugLine="Answer.Text = card.Get(\"A\")";
mostCurrent._answer.setText(BA.ObjectToCharSequence(_card.Get((Object)("A"))));
RDebugUtils.currentLine=11141128;
 //BA.debugLineNum = 11141128;BA.debugLine="showAnswerbtn.Text = \"Hide Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Hide Answer"));
 }else {
RDebugUtils.currentLine=11141131;
 //BA.debugLineNum = 11141131;BA.debugLine="Answer.Text = \"\"";
mostCurrent._answer.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=11141132;
 //BA.debugLineNum = 11141132;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
 };
RDebugUtils.currentLine=11141136;
 //BA.debugLineNum = 11141136;BA.debugLine="nextbtn.Visible = True";
mostCurrent._nextbtn.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=11141137;
 //BA.debugLineNum = 11141137;BA.debugLine="End Sub";
return "";
}
}