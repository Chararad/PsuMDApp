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

public class all_active_recall extends Activity implements B4AActivity{
	public static all_active_recall mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.all_active_recall");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (all_active_recall).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.all_active_recall");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.all_active_recall", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (all_active_recall) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (all_active_recall) Resume **");
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
		return all_active_recall.class;
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
            BA.LogInfo("** Activity (all_active_recall) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (all_active_recall) Pause event (activity is not paused). **");
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
            all_active_recall mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (all_active_recall) Resume **");
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
public anywheresoftware.b4a.objects.collections.List _cards = null;
public static int _currentindex = 0;
public anywheresoftware.b4a.objects.LabelWrapper _question = null;
public anywheresoftware.b4a.objects.LabelWrapper _answer = null;
public anywheresoftware.b4a.objects.LabelWrapper _deckname_label = null;
public anywheresoftware.b4a.objects.ButtonWrapper _showanswerbtn = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _pb = null;
public anywheresoftware.b4a.objects.LabelWrapper _progress = null;
public anywheresoftware.b4a.objects.ButtonWrapper _nextbtn = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.leaderboard _leaderboard = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
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
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.collections.Map _chosendeck = null;
String _subdeckname = "";
anywheresoftware.b4a.objects.collections.List _subcards = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.collections.Map _newcard = null;
RDebugUtils.currentLine=15335424;
 //BA.debugLineNum = 15335424;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=15335426;
 //BA.debugLineNum = 15335426;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=15335428;
 //BA.debugLineNum = 15335428;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15335429;
 //BA.debugLineNum = 15335429;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
mostCurrent._activity.LoadLayout("AARLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=15335431;
 //BA.debugLineNum = 15335431;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark\")";
mostCurrent._activity.LoadLayout("AEMLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=15335434;
 //BA.debugLineNum = 15335434;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15335435;
 //BA.debugLineNum = 15335435;BA.debugLine="Activity.LoadLayout(\"AARLayout2\")";
mostCurrent._activity.LoadLayout("AARLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=15335437;
 //BA.debugLineNum = 15335437;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark2\")";
mostCurrent._activity.LoadLayout("AARLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=15335440;
 //BA.debugLineNum = 15335440;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=15335441;
 //BA.debugLineNum = 15335441;BA.debugLine="Activity.LoadLayout(\"AARLayout3\")";
mostCurrent._activity.LoadLayout("AARLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=15335443;
 //BA.debugLineNum = 15335443;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark3\")";
mostCurrent._activity.LoadLayout("AARLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=15335447;
 //BA.debugLineNum = 15335447;BA.debugLine="cards.Initialize";
mostCurrent._cards.Initialize();
RDebugUtils.currentLine=15335448;
 //BA.debugLineNum = 15335448;BA.debugLine="RndSeed(DateTime.Now) 'seeding randomizer";
anywheresoftware.b4a.keywords.Common.RndSeed(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
RDebugUtils.currentLine=15335451;
 //BA.debugLineNum = 15335451;BA.debugLine="Dim chosendeck As Map = FlashcardActivity.deck.Ge";
_chosendeck = new anywheresoftware.b4a.objects.collections.Map();
_chosendeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=15335452;
 //BA.debugLineNum = 15335452;BA.debugLine="For Each subdeckname As String In chosendeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group24 = _chosendeck.Keys();
final int groupLen24 = group24.getSize()
;int index24 = 0;
;
for (; index24 < groupLen24;index24++){
_subdeckname = BA.ObjectToString(group24.Get(index24));
RDebugUtils.currentLine=15335453;
 //BA.debugLineNum = 15335453;BA.debugLine="Dim subcards As List = chosendeck.Get(subdecknam";
_subcards = new anywheresoftware.b4a.objects.collections.List();
_subcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_chosendeck.Get((Object)(_subdeckname))));
RDebugUtils.currentLine=15335454;
 //BA.debugLineNum = 15335454;BA.debugLine="For Each card As Map In subcards";
_card = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group26 = _subcards;
final int groupLen26 = group26.getSize()
;int index26 = 0;
;
for (; index26 < groupLen26;index26++){
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group26.Get(index26)));
RDebugUtils.currentLine=15335455;
 //BA.debugLineNum = 15335455;BA.debugLine="Dim newcard As Map";
_newcard = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=15335456;
 //BA.debugLineNum = 15335456;BA.debugLine="newcard.Initialize";
_newcard.Initialize();
RDebugUtils.currentLine=15335457;
 //BA.debugLineNum = 15335457;BA.debugLine="newcard.Put(\"Q\", card.Get(\"Q\"))";
_newcard.Put((Object)("Q"),_card.Get((Object)("Q")));
RDebugUtils.currentLine=15335458;
 //BA.debugLineNum = 15335458;BA.debugLine="newcard.Put(\"A\", card.Get(\"A\"))";
_newcard.Put((Object)("A"),_card.Get((Object)("A")));
RDebugUtils.currentLine=15335459;
 //BA.debugLineNum = 15335459;BA.debugLine="newcard.Put(\"subdeck\", subdeckname) 'get subdec";
_newcard.Put((Object)("subdeck"),(Object)(_subdeckname));
RDebugUtils.currentLine=15335460;
 //BA.debugLineNum = 15335460;BA.debugLine="cards.Add(newcard)";
mostCurrent._cards.Add((Object)(_newcard.getObject()));
 }
};
 }
};
RDebugUtils.currentLine=15335464;
 //BA.debugLineNum = 15335464;BA.debugLine="ShuffleCards(cards)";
_shufflecards(mostCurrent._cards);
RDebugUtils.currentLine=15335465;
 //BA.debugLineNum = 15335465;BA.debugLine="currentindex = 0";
_currentindex = (int) (0);
RDebugUtils.currentLine=15335467;
 //BA.debugLineNum = 15335467;BA.debugLine="Showcard";
_showcard();
RDebugUtils.currentLine=15335468;
 //BA.debugLineNum = 15335468;BA.debugLine="ShowProgress";
_showprogress();
RDebugUtils.currentLine=15335469;
 //BA.debugLineNum = 15335469;BA.debugLine="End Sub";
return "";
}
public static String  _shufflecards(anywheresoftware.b4a.objects.collections.List _cardlist) throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "shufflecards", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "shufflecards", new Object[] {_cardlist}));}
int _i = 0;
int _j = 0;
Object _temp = null;
RDebugUtils.currentLine=15466496;
 //BA.debugLineNum = 15466496;BA.debugLine="Sub ShuffleCards(cardList As List)";
RDebugUtils.currentLine=15466498;
 //BA.debugLineNum = 15466498;BA.debugLine="For i = cardList.Size-1 To 1 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_i = (int) (_cardlist.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
RDebugUtils.currentLine=15466499;
 //BA.debugLineNum = 15466499;BA.debugLine="Dim j As Int = Rnd(0, i+1)";
_j = anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (_i+1));
RDebugUtils.currentLine=15466501;
 //BA.debugLineNum = 15466501;BA.debugLine="Dim temp As Object = cardList.Get(i)";
_temp = _cardlist.Get(_i);
RDebugUtils.currentLine=15466502;
 //BA.debugLineNum = 15466502;BA.debugLine="cardList.Set(i, cardList.Get(j))";
_cardlist.Set(_i,_cardlist.Get(_j));
RDebugUtils.currentLine=15466503;
 //BA.debugLineNum = 15466503;BA.debugLine="cardList.Set(j, temp)";
_cardlist.Set(_j,_temp);
 }
};
RDebugUtils.currentLine=15466505;
 //BA.debugLineNum = 15466505;BA.debugLine="End Sub";
return "";
}
public static String  _showcard() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showcard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showcard", null));}
anywheresoftware.b4a.objects.collections.Map _card = null;
RDebugUtils.currentLine=15532032;
 //BA.debugLineNum = 15532032;BA.debugLine="Sub Showcard";
RDebugUtils.currentLine=15532034;
 //BA.debugLineNum = 15532034;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._cards.Get(_currentindex)));
RDebugUtils.currentLine=15532035;
 //BA.debugLineNum = 15532035;BA.debugLine="Question.Text = card.Get(\"Q\")";
mostCurrent._question.setText(BA.ObjectToCharSequence(_card.Get((Object)("Q"))));
RDebugUtils.currentLine=15532036;
 //BA.debugLineNum = 15532036;BA.debugLine="Answer.Text = \"\"";
mostCurrent._answer.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=15532037;
 //BA.debugLineNum = 15532037;BA.debugLine="DeckName_Label.Text = card.Get(\"subdeck\")";
mostCurrent._deckname_label.setText(BA.ObjectToCharSequence(_card.Get((Object)("subdeck"))));
RDebugUtils.currentLine=15532038;
 //BA.debugLineNum = 15532038;BA.debugLine="End Sub";
return "";
}
public static String  _showprogress() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showprogress", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showprogress", null));}
int _totalsession = 0;
int _studied = 0;
int _percent = 0;
RDebugUtils.currentLine=15400960;
 //BA.debugLineNum = 15400960;BA.debugLine="Sub ShowProgress";
RDebugUtils.currentLine=15400961;
 //BA.debugLineNum = 15400961;BA.debugLine="Dim totalsession As Int = cards.Size";
_totalsession = mostCurrent._cards.getSize();
RDebugUtils.currentLine=15400962;
 //BA.debugLineNum = 15400962;BA.debugLine="Dim studied As Int = currentindex";
_studied = _currentindex;
RDebugUtils.currentLine=15400963;
 //BA.debugLineNum = 15400963;BA.debugLine="Dim percent As Int = (studied * 100)/ totalsessio";
_percent = (int) ((_studied*100)/(double)_totalsession);
RDebugUtils.currentLine=15400964;
 //BA.debugLineNum = 15400964;BA.debugLine="pb.Progress = percent";
mostCurrent._pb.setProgress(_percent);
RDebugUtils.currentLine=15400965;
 //BA.debugLineNum = 15400965;BA.debugLine="Progress.Text = studied & \"/\" & totalsession & \"";
mostCurrent._progress.setText(BA.ObjectToCharSequence(BA.NumberToString(_studied)+"/"+BA.NumberToString(_totalsession)+" "+BA.NumberToString(_percent)+"%"));
RDebugUtils.currentLine=15400966;
 //BA.debugLineNum = 15400966;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="all_active_recall";
RDebugUtils.currentLine=15663104;
 //BA.debugLineNum = 15663104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=15663106;
 //BA.debugLineNum = 15663106;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=15597568;
 //BA.debugLineNum = 15597568;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=15597570;
 //BA.debugLineNum = 15597570;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=15794176;
 //BA.debugLineNum = 15794176;BA.debugLine="Private Sub backbtn_Click";
RDebugUtils.currentLine=15794178;
 //BA.debugLineNum = 15794178;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
RDebugUtils.currentLine=15794179;
 //BA.debugLineNum = 15794179;BA.debugLine="If currentindex = 0 Then";
if (_currentindex==0) { 
 }else {
RDebugUtils.currentLine=15794182;
 //BA.debugLineNum = 15794182;BA.debugLine="currentindex = currentindex-1";
_currentindex = (int) (_currentindex-1);
RDebugUtils.currentLine=15794183;
 //BA.debugLineNum = 15794183;BA.debugLine="Showcard";
_showcard();
 };
RDebugUtils.currentLine=15794185;
 //BA.debugLineNum = 15794185;BA.debugLine="End Sub";
return "";
}
public static String  _goback_click() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "goback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "goback_click", null));}
RDebugUtils.currentLine=15925248;
 //BA.debugLineNum = 15925248;BA.debugLine="Private Sub goback_Click";
RDebugUtils.currentLine=15925249;
 //BA.debugLineNum = 15925249;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=15925250;
 //BA.debugLineNum = 15925250;BA.debugLine="End Sub";
return "";
}
public static String  _nextbtn_click() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "nextbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "nextbtn_click", null));}
RDebugUtils.currentLine=15859712;
 //BA.debugLineNum = 15859712;BA.debugLine="Private Sub nextbtn_Click";
RDebugUtils.currentLine=15859714;
 //BA.debugLineNum = 15859714;BA.debugLine="nextbtn.Visible = False";
mostCurrent._nextbtn.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=15859715;
 //BA.debugLineNum = 15859715;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
RDebugUtils.currentLine=15859716;
 //BA.debugLineNum = 15859716;BA.debugLine="currentindex = currentindex +1";
_currentindex = (int) (_currentindex+1);
RDebugUtils.currentLine=15859717;
 //BA.debugLineNum = 15859717;BA.debugLine="If currentindex >= cards.Size Then";
if (_currentindex>=mostCurrent._cards.getSize()) { 
RDebugUtils.currentLine=15859718;
 //BA.debugLineNum = 15859718;BA.debugLine="MsgboxAsync(\"Decks Finished\", \"Active Recall\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Decks Finished"),BA.ObjectToCharSequence("Active Recall"),processBA);
RDebugUtils.currentLine=15859719;
 //BA.debugLineNum = 15859719;BA.debugLine="praise = True";
_praise = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=15859720;
 //BA.debugLineNum = 15859720;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=15859721;
 //BA.debugLineNum = 15859721;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=15859724;
 //BA.debugLineNum = 15859724;BA.debugLine="Showcard";
_showcard();
RDebugUtils.currentLine=15859725;
 //BA.debugLineNum = 15859725;BA.debugLine="ShowProgress";
_showprogress();
RDebugUtils.currentLine=15859726;
 //BA.debugLineNum = 15859726;BA.debugLine="End Sub";
return "";
}
public static String  _showanswerbtn_click() throws Exception{
RDebugUtils.currentModule="all_active_recall";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showanswerbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showanswerbtn_click", null));}
anywheresoftware.b4a.objects.collections.Map _card = null;
RDebugUtils.currentLine=15728640;
 //BA.debugLineNum = 15728640;BA.debugLine="Private Sub showAnswerbtn_Click";
RDebugUtils.currentLine=15728642;
 //BA.debugLineNum = 15728642;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
_card = new anywheresoftware.b4a.objects.collections.Map();
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._cards.Get(_currentindex)));
RDebugUtils.currentLine=15728643;
 //BA.debugLineNum = 15728643;BA.debugLine="If showAnswerbtn.Text = \"Show Answer\" Then";
if ((mostCurrent._showanswerbtn.getText()).equals("Show Answer")) { 
RDebugUtils.currentLine=15728644;
 //BA.debugLineNum = 15728644;BA.debugLine="Answer.Text = card.Get(\"A\")";
mostCurrent._answer.setText(BA.ObjectToCharSequence(_card.Get((Object)("A"))));
RDebugUtils.currentLine=15728645;
 //BA.debugLineNum = 15728645;BA.debugLine="showAnswerbtn.Text = \"Hide Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Hide Answer"));
 }else {
RDebugUtils.currentLine=15728648;
 //BA.debugLineNum = 15728648;BA.debugLine="Answer.Text = \"\"";
mostCurrent._answer.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=15728649;
 //BA.debugLineNum = 15728649;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
mostCurrent._showanswerbtn.setText(BA.ObjectToCharSequence("Show Answer"));
 };
RDebugUtils.currentLine=15728651;
 //BA.debugLineNum = 15728651;BA.debugLine="nextbtn.Visible = True";
mostCurrent._nextbtn.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=15728652;
 //BA.debugLineNum = 15728652;BA.debugLine="End Sub";
return "";
}
}