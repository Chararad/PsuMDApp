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

public class subdeck_module extends Activity implements B4AActivity{
	public static subdeck_module mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.subdeck_module");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (subdeck_module).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.subdeck_module");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.subdeck_module", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (subdeck_module) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (subdeck_module) Resume **");
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
		return subdeck_module.class;
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
            BA.LogInfo("** Activity (subdeck_module) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (subdeck_module) Pause event (activity is not paused). **");
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
            subdeck_module mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (subdeck_module) Resume **");
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
public static String _selectedsubdeck = "";
public anywheresoftware.b4a.objects.ButtonWrapper _addbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _addpanel2 = null;
public anywheresoftware.b4a.objects.collections.Map _alldecks = null;
public static String _selecteddeck = "";
public anywheresoftware.b4a.objects.ListViewWrapper _lvsubdecks = null;
public anywheresoftware.b4a.objects.LabelWrapper _decknamelabel = null;
public anywheresoftware.b4a.objects.PanelWrapper _addpanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _et1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _ar_confirmationpanel = null;
public anywheresoftware.b4a.objects.LabelWrapper _confirmlabel = null;
public static int _number_of_cards = 0;
public anywheresoftware.b4a.objects.PanelWrapper _alterpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _renamepanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _renameet = null;
public anywheresoftware.b4a.objects.PanelWrapper _deleteconfirmation = null;
public anywheresoftware.b4a.objects.EditTextWrapper _topic_et = null;
public anywheresoftware.b4a.objects.PanelWrapper _topic_panel = null;
public anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter _j = null;
public anywheresoftware.b4a.phone.Phone.ContentChooser _cc = null;
public anywheresoftware.b4a.objects.ButtonWrapper _pickpdfbtn = null;
public static String _api1 = "";
public static String _api2 = "";
public static String _myapikey = "";
public static String _aiglobaltext = "";
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
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.day_module _day_module = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activerecall_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activerecall_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activerecall_click", null));}
anywheresoftware.b4a.objects.collections.Map _chosendeck = null;
String _deckname = "";
anywheresoftware.b4a.objects.collections.List _flashacards = null;
RDebugUtils.currentLine=28049408;
 //BA.debugLineNum = 28049408;BA.debugLine="Private Sub activerecall_Click";
RDebugUtils.currentLine=28049411;
 //BA.debugLineNum = 28049411;BA.debugLine="number_of_cards = 0";
_number_of_cards = (int) (0);
RDebugUtils.currentLine=28049413;
 //BA.debugLineNum = 28049413;BA.debugLine="Dim chosendeck As Map = alldecks.Get(selecteddeck";
_chosendeck = new anywheresoftware.b4a.objects.collections.Map();
_chosendeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
RDebugUtils.currentLine=28049415;
 //BA.debugLineNum = 28049415;BA.debugLine="For Each deckName As String In chosendeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _chosendeck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=28049416;
 //BA.debugLineNum = 28049416;BA.debugLine="Dim flashacards As List = chosendeck.Get(deckNam";
_flashacards = new anywheresoftware.b4a.objects.collections.List();
_flashacards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_chosendeck.Get((Object)(_deckname))));
RDebugUtils.currentLine=28049417;
 //BA.debugLineNum = 28049417;BA.debugLine="number_of_cards = number_of_cards + flashacards.";
_number_of_cards = (int) (_number_of_cards+_flashacards.getSize());
 }
};
RDebugUtils.currentLine=28049420;
 //BA.debugLineNum = 28049420;BA.debugLine="AR_confirmationpanel.Visible = True";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28049421;
 //BA.debugLineNum = 28049421;BA.debugLine="confirmlabel.Text = \"You got \" & number_of_cards";
mostCurrent._confirmlabel.setText(BA.ObjectToCharSequence("You got "+BA.NumberToString(_number_of_cards)+" cards"));
RDebugUtils.currentLine=28049422;
 //BA.debugLineNum = 28049422;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=27328512;
 //BA.debugLineNum = 27328512;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=27328513;
 //BA.debugLineNum = 27328513;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=27328515;
 //BA.debugLineNum = 27328515;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=27328516;
 //BA.debugLineNum = 27328516;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=27328518;
 //BA.debugLineNum = 27328518;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark\"";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=27328521;
 //BA.debugLineNum = 27328521;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=27328522;
 //BA.debugLineNum = 27328522;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=27328524;
 //BA.debugLineNum = 27328524;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark2";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=27328527;
 //BA.debugLineNum = 27328527;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=27328528;
 //BA.debugLineNum = 27328528;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=27328530;
 //BA.debugLineNum = 27328530;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark3";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=27328534;
 //BA.debugLineNum = 27328534;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=27328536;
 //BA.debugLineNum = 27328536;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
mostCurrent._lvsubdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=27328538;
 //BA.debugLineNum = 27328538;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
mostCurrent._lvsubdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=27328541;
 //BA.debugLineNum = 27328541;BA.debugLine="cc.Initialize(\"CC\")";
mostCurrent._cc.Initialize("CC");
RDebugUtils.currentLine=27328543;
 //BA.debugLineNum = 27328543;BA.debugLine="decknamelabel.Text = selecteddeck";
mostCurrent._decknamelabel.setText(BA.ObjectToCharSequence(mostCurrent._selecteddeck));
RDebugUtils.currentLine=27328545;
 //BA.debugLineNum = 27328545;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27328546;
 //BA.debugLineNum = 27328546;BA.debugLine="addpanel2.Visible = False";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27328547;
 //BA.debugLineNum = 27328547;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27328548;
 //BA.debugLineNum = 27328548;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27328549;
 //BA.debugLineNum = 27328549;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27328550;
 //BA.debugLineNum = 27328550;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27328552;
 //BA.debugLineNum = 27328552;BA.debugLine="Refresh";
_refresh();
RDebugUtils.currentLine=27328554;
 //BA.debugLineNum = 27328554;BA.debugLine="End Sub";
return "";
}
public static String  _refresh() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "refresh", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "refresh", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
String _deckname = "";
RDebugUtils.currentLine=27459584;
 //BA.debugLineNum = 27459584;BA.debugLine="Sub Refresh";
RDebugUtils.currentLine=27459585;
 //BA.debugLineNum = 27459585;BA.debugLine="LVSubdecks.clear";
mostCurrent._lvsubdecks.Clear();
RDebugUtils.currentLine=27459587;
 //BA.debugLineNum = 27459587;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
RDebugUtils.currentLine=27459590;
 //BA.debugLineNum = 27459590;BA.debugLine="For Each deckName As String In tappeddeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _tappeddeck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=27459591;
 //BA.debugLineNum = 27459591;BA.debugLine="LVSubdecks.AddSingleLine(deckName)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(_deckname));
 }
};
RDebugUtils.currentLine=27459593;
 //BA.debugLineNum = 27459593;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="subdeck_module";
RDebugUtils.currentLine=27590656;
 //BA.debugLineNum = 27590656;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=27590658;
 //BA.debugLineNum = 27590658;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=27525120;
 //BA.debugLineNum = 27525120;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=27525121;
 //BA.debugLineNum = 27525121;BA.debugLine="If all_active_recall.praise = True Then";
if (mostCurrent._all_active_recall._praise /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=27525122;
 //BA.debugLineNum = 27525122;BA.debugLine="all_active_recall.praise = False";
mostCurrent._all_active_recall._praise /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=27525123;
 //BA.debugLineNum = 27525123;BA.debugLine="MsgboxAsync(\"You Finished Your Deck\", \"Congratul";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You Finished Your Deck"),BA.ObjectToCharSequence("Congratulations"),processBA);
 };
RDebugUtils.currentLine=27525125;
 //BA.debugLineNum = 27525125;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27525126;
 //BA.debugLineNum = 27525126;BA.debugLine="End Sub";
return "";
}
public static String  _addbtn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addbtn_click", null));}
RDebugUtils.currentLine=27656192;
 //BA.debugLineNum = 27656192;BA.debugLine="Private Sub Addbtn_Click";
RDebugUtils.currentLine=27656194;
 //BA.debugLineNum = 27656194;BA.debugLine="If addpanel2.Visible = False Then";
if (mostCurrent._addpanel2.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=27656195;
 //BA.debugLineNum = 27656195;BA.debugLine="addpanel2.Visible = True";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=27656197;
 //BA.debugLineNum = 27656197;BA.debugLine="addpanel2.Visible = False";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=27656200;
 //BA.debugLineNum = 27656200;BA.debugLine="End Sub";
return "";
}
public static String  _addcard_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addcard_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addcard_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=27721728;
 //BA.debugLineNum = 27721728;BA.debugLine="Private Sub addcard_Click";
RDebugUtils.currentLine=27721730;
 //BA.debugLineNum = 27721730;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=27721731;
 //BA.debugLineNum = 27721731;BA.debugLine="If tappeddeck.Size = 0 Then";
if (_tappeddeck.getSize()==0) { 
RDebugUtils.currentLine=27721732;
 //BA.debugLineNum = 27721732;BA.debugLine="MsgboxAsync(\"Create A Sub-Deck first\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Create A Sub-Deck first"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=27721733;
 //BA.debugLineNum = 27721733;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=27721735;
 //BA.debugLineNum = 27721735;BA.debugLine="StartActivity(Add_card_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module.getObject()));
RDebugUtils.currentLine=27721736;
 //BA.debugLineNum = 27721736;BA.debugLine="End Sub";
return "";
}
public static String  _addsub_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addsub_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addsub_click", null));}
RDebugUtils.currentLine=27787264;
 //BA.debugLineNum = 27787264;BA.debugLine="Private Sub addsub_Click";
RDebugUtils.currentLine=27787266;
 //BA.debugLineNum = 27787266;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=27787267;
 //BA.debugLineNum = 27787267;BA.debugLine="End Sub";
return "";
}
public static String  _ai_cards_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ai_cards_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ai_cards_click", null));}
RDebugUtils.currentLine=28901376;
 //BA.debugLineNum = 28901376;BA.debugLine="Private Sub AI_cards_Click";
RDebugUtils.currentLine=28901377;
 //BA.debugLineNum = 28901377;BA.debugLine="If topic_panel.Visible = True Then";
if (mostCurrent._topic_panel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=28901378;
 //BA.debugLineNum = 28901378;BA.debugLine="topic_panel.visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28901379;
 //BA.debugLineNum = 28901379;BA.debugLine="Return";
if (true) return "";
 }else {
RDebugUtils.currentLine=28901381;
 //BA.debugLineNum = 28901381;BA.debugLine="topic_panel.Visible = True";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=28901384;
 //BA.debugLineNum = 28901384;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=28311552;
 //BA.debugLineNum = 28311552;BA.debugLine="Private Sub backbtn_Click";
RDebugUtils.currentLine=28311553;
 //BA.debugLineNum = 28311553;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=28311554;
 //BA.debugLineNum = 28311554;BA.debugLine="End Sub";
return "";
}
public static String  _cancel_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancel_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancel_click", null));}
RDebugUtils.currentLine=27852800;
 //BA.debugLineNum = 27852800;BA.debugLine="Private Sub cancel_Click";
RDebugUtils.currentLine=27852802;
 //BA.debugLineNum = 27852802;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27852803;
 //BA.debugLineNum = 27852803;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=27852804;
 //BA.debugLineNum = 27852804;BA.debugLine="End Sub";
return "";
}
public static String  _cancelalter_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelalter_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelalter_click", null));}
RDebugUtils.currentLine=28508160;
 //BA.debugLineNum = 28508160;BA.debugLine="Private Sub cancelalter_Click";
RDebugUtils.currentLine=28508161;
 //BA.debugLineNum = 28508161;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28508162;
 //BA.debugLineNum = 28508162;BA.debugLine="End Sub";
return "";
}
public static String  _cancelconfirmation_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelconfirmation_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelconfirmation_click", null));}
RDebugUtils.currentLine=28180480;
 //BA.debugLineNum = 28180480;BA.debugLine="Private Sub cancelconfirmation_Click";
RDebugUtils.currentLine=28180482;
 //BA.debugLineNum = 28180482;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28180483;
 //BA.debugLineNum = 28180483;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceldelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceldelete_click", null));}
RDebugUtils.currentLine=28835840;
 //BA.debugLineNum = 28835840;BA.debugLine="Private Sub canceldelete_Click";
RDebugUtils.currentLine=28835841;
 //BA.debugLineNum = 28835841;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28835842;
 //BA.debugLineNum = 28835842;BA.debugLine="End Sub";
return "";
}
public static String  _cancelrename_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelrename_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelrename_click", null));}
RDebugUtils.currentLine=28639232;
 //BA.debugLineNum = 28639232;BA.debugLine="Private Sub cancelrename_Click";
RDebugUtils.currentLine=28639233;
 //BA.debugLineNum = 28639233;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28639234;
 //BA.debugLineNum = 28639234;BA.debugLine="renameet.Text = \"\"";
mostCurrent._renameet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=28639235;
 //BA.debugLineNum = 28639235;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmdelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmdelete_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=28770304;
 //BA.debugLineNum = 28770304;BA.debugLine="Private Sub confirmdelete_Click";
RDebugUtils.currentLine=28770305;
 //BA.debugLineNum = 28770305;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=28770306;
 //BA.debugLineNum = 28770306;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
_tappeddeck.Remove((Object)(_selectedsubdeck));
RDebugUtils.currentLine=28770307;
 //BA.debugLineNum = 28770307;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=28770308;
 //BA.debugLineNum = 28770308;BA.debugLine="Refresh";
_refresh();
RDebugUtils.currentLine=28770309;
 //BA.debugLineNum = 28770309;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28770310;
 //BA.debugLineNum = 28770310;BA.debugLine="End Sub";
return "";
}
public static String  _savedecks() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savedecks", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savedecks", null));}
RDebugUtils.currentLine=27394048;
 //BA.debugLineNum = 27394048;BA.debugLine="Sub SaveDecks";
RDebugUtils.currentLine=27394049;
 //BA.debugLineNum = 27394049;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
mostCurrent._flashcardactivity._kvs /*b4a.example3.keyvaluestore*/ ._put("deck_data",(Object)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
RDebugUtils.currentLine=27394050;
 //BA.debugLineNum = 27394050;BA.debugLine="End Sub";
return "";
}
public static String  _confirmrename_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmrename_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmrename_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _getsubdeck = null;
String _names = "";
RDebugUtils.currentLine=28704768;
 //BA.debugLineNum = 28704768;BA.debugLine="Private Sub confirmrename_Click";
RDebugUtils.currentLine=28704769;
 //BA.debugLineNum = 28704769;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=28704771;
 //BA.debugLineNum = 28704771;BA.debugLine="If renameet.Text = \"\" Then";
if ((mostCurrent._renameet.getText()).equals("")) { 
RDebugUtils.currentLine=28704772;
 //BA.debugLineNum = 28704772;BA.debugLine="MsgboxAsync(\"New Name must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("New Name must have a name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=28704773;
 //BA.debugLineNum = 28704773;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=28704777;
 //BA.debugLineNum = 28704777;BA.debugLine="Dim getsubdeck As List = tappeddeck.Get(selecteds";
_getsubdeck = new anywheresoftware.b4a.objects.collections.List();
_getsubdeck = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(_selectedsubdeck))));
RDebugUtils.currentLine=28704779;
 //BA.debugLineNum = 28704779;BA.debugLine="For Each names As String In tappeddeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group7 = _tappeddeck.Keys();
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.Get(index7));
RDebugUtils.currentLine=28704780;
 //BA.debugLineNum = 28704780;BA.debugLine="If renameet.Text = names Then";
if ((mostCurrent._renameet.getText()).equals(_names)) { 
RDebugUtils.currentLine=28704781;
 //BA.debugLineNum = 28704781;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=28704782;
 //BA.debugLineNum = 28704782;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=28704786;
 //BA.debugLineNum = 28704786;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
_tappeddeck.Remove((Object)(_selectedsubdeck));
RDebugUtils.currentLine=28704787;
 //BA.debugLineNum = 28704787;BA.debugLine="tappeddeck.Put(renameet.Text, getsubdeck)";
_tappeddeck.Put((Object)(mostCurrent._renameet.getText()),(Object)(_getsubdeck.getObject()));
RDebugUtils.currentLine=28704788;
 //BA.debugLineNum = 28704788;BA.debugLine="renameet.Text = \"\"";
mostCurrent._renameet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=28704789;
 //BA.debugLineNum = 28704789;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=28704790;
 //BA.debugLineNum = 28704790;BA.debugLine="Refresh";
_refresh();
RDebugUtils.currentLine=28704791;
 //BA.debugLineNum = 28704791;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28704792;
 //BA.debugLineNum = 28704792;BA.debugLine="End Sub";
return "";
}
public static String  _create_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "create_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "create_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
Object _name = null;
RDebugUtils.currentLine=27918336;
 //BA.debugLineNum = 27918336;BA.debugLine="Private Sub create_Click";
RDebugUtils.currentLine=27918339;
 //BA.debugLineNum = 27918339;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
RDebugUtils.currentLine=27918340;
 //BA.debugLineNum = 27918340;BA.debugLine="Dim flashcards As List";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=27918342;
 //BA.debugLineNum = 27918342;BA.debugLine="flashcards.initialize";
_flashcards.Initialize();
RDebugUtils.currentLine=27918345;
 //BA.debugLineNum = 27918345;BA.debugLine="For Each name In tappeddeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group4 = _tappeddeck.Keys();
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_name = group4.Get(index4);
RDebugUtils.currentLine=27918346;
 //BA.debugLineNum = 27918346;BA.debugLine="If et1.Text = name Then";
if ((mostCurrent._et1.getText()).equals(BA.ObjectToString(_name))) { 
RDebugUtils.currentLine=27918347;
 //BA.debugLineNum = 27918347;BA.debugLine="MsgboxAsync(\"Sub-Deck Already Exist\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=27918348;
 //BA.debugLineNum = 27918348;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=27918353;
 //BA.debugLineNum = 27918353;BA.debugLine="If et1.Text = \"\" Then";
if ((mostCurrent._et1.getText()).equals("")) { 
RDebugUtils.currentLine=27918354;
 //BA.debugLineNum = 27918354;BA.debugLine="MsgboxAsync(\"Sub-Deck must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck must have a name"),BA.ObjectToCharSequence("Error"),processBA);
 }else {
RDebugUtils.currentLine=27918357;
 //BA.debugLineNum = 27918357;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=27918358;
 //BA.debugLineNum = 27918358;BA.debugLine="LVSubdecks.AddSingleLine(et1.Text)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._et1.getText()));
RDebugUtils.currentLine=27918359;
 //BA.debugLineNum = 27918359;BA.debugLine="tappeddeck.Put(et1.Text, flashcards)";
_tappeddeck.Put((Object)(mostCurrent._et1.getText()),(Object)(_flashcards.getObject()));
RDebugUtils.currentLine=27918360;
 //BA.debugLineNum = 27918360;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=27918362;
 //BA.debugLineNum = 27918362;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
 };
RDebugUtils.currentLine=27918365;
 //BA.debugLineNum = 27918365;BA.debugLine="End Sub";
return "";
}
public static String  _deletesubdeck_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletesubdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletesubdeck_click", null));}
RDebugUtils.currentLine=28377088;
 //BA.debugLineNum = 28377088;BA.debugLine="Private Sub deletesubdeck_Click";
RDebugUtils.currentLine=28377089;
 //BA.debugLineNum = 28377089;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28377090;
 //BA.debugLineNum = 28377090;BA.debugLine="deleteconfirmation.Visible = True";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28377091;
 //BA.debugLineNum = 28377091;BA.debugLine="End Sub";
return "";
}
public static String  _generateflashcards(String _topic) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "generateflashcards", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "generateflashcards", new Object[] {_topic}));}
String _url = "";
b4a.example.httpjob _job = null;
String _prompt = "";
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _contents = null;
anywheresoftware.b4a.objects.collections.Map _contentitem = null;
anywheresoftware.b4a.objects.collections.List _parts = null;
anywheresoftware.b4a.objects.collections.Map _textpart = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
String _json = "";
RDebugUtils.currentLine=29097984;
 //BA.debugLineNum = 29097984;BA.debugLine="Sub GenerateFlashCards(Topic As String)";
RDebugUtils.currentLine=29097986;
 //BA.debugLineNum = 29097986;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+mostCurrent._myapikey;
RDebugUtils.currentLine=29097988;
 //BA.debugLineNum = 29097988;BA.debugLine="Dim Job As HttpJob";
_job = new b4a.example.httpjob();
RDebugUtils.currentLine=29097989;
 //BA.debugLineNum = 29097989;BA.debugLine="Job.Initialize(\"Gemini\", Me)";
_job._initialize /*String*/ (null,processBA,"Gemini",subdeck_module.getObject());
RDebugUtils.currentLine=29097991;
 //BA.debugLineNum = 29097991;BA.debugLine="Dim prompt As String = _ 	\"Create flashcards from";
_prompt = "Create flashcards from the Topic below."+"RULES:"+"- Return ONLY valid JSON"+"- No explanations"+"- No markdown"+"- Must have 20+ Flashcards"+"- Format must exactly follow this structure:"+"{"+"\"flashcards\": ["+"{"+"\"question\": \"Question here\","+"\"answer\": \"Answer here\""+"}"+"]"+"}"+"Topic:"+_topic;
RDebugUtils.currentLine=29098009;
 //BA.debugLineNum = 29098009;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=29098010;
 //BA.debugLineNum = 29098010;BA.debugLine="root.Initialize";
_root.Initialize();
RDebugUtils.currentLine=29098012;
 //BA.debugLineNum = 29098012;BA.debugLine="Dim contents As List";
_contents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=29098013;
 //BA.debugLineNum = 29098013;BA.debugLine="contents.Initialize";
_contents.Initialize();
RDebugUtils.currentLine=29098015;
 //BA.debugLineNum = 29098015;BA.debugLine="Dim contentItem As Map";
_contentitem = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=29098016;
 //BA.debugLineNum = 29098016;BA.debugLine="contentItem.Initialize";
_contentitem.Initialize();
RDebugUtils.currentLine=29098018;
 //BA.debugLineNum = 29098018;BA.debugLine="Dim parts As List";
_parts = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=29098019;
 //BA.debugLineNum = 29098019;BA.debugLine="parts.Initialize";
_parts.Initialize();
RDebugUtils.currentLine=29098021;
 //BA.debugLineNum = 29098021;BA.debugLine="Dim textPart As Map";
_textpart = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=29098022;
 //BA.debugLineNum = 29098022;BA.debugLine="textPart.Initialize";
_textpart.Initialize();
RDebugUtils.currentLine=29098024;
 //BA.debugLineNum = 29098024;BA.debugLine="textPart.Put(\"text\", prompt)";
_textpart.Put((Object)("text"),(Object)(_prompt));
RDebugUtils.currentLine=29098025;
 //BA.debugLineNum = 29098025;BA.debugLine="parts.Add(textPart)";
_parts.Add((Object)(_textpart.getObject()));
RDebugUtils.currentLine=29098027;
 //BA.debugLineNum = 29098027;BA.debugLine="contentItem.Put(\"parts\", parts)";
_contentitem.Put((Object)("parts"),(Object)(_parts.getObject()));
RDebugUtils.currentLine=29098028;
 //BA.debugLineNum = 29098028;BA.debugLine="contents.Add(contentItem)";
_contents.Add((Object)(_contentitem.getObject()));
RDebugUtils.currentLine=29098029;
 //BA.debugLineNum = 29098029;BA.debugLine="root.Put(\"contents\", contents)";
_root.Put((Object)("contents"),(Object)(_contents.getObject()));
RDebugUtils.currentLine=29098031;
 //BA.debugLineNum = 29098031;BA.debugLine="Dim gen As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=29098032;
 //BA.debugLineNum = 29098032;BA.debugLine="gen.Initialize(root)";
_gen.Initialize(_root);
RDebugUtils.currentLine=29098034;
 //BA.debugLineNum = 29098034;BA.debugLine="Dim json As String = gen.ToString";
_json = _gen.ToString();
RDebugUtils.currentLine=29098036;
 //BA.debugLineNum = 29098036;BA.debugLine="Log(\"REQUEST: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("429098036","REQUEST: ",0);
RDebugUtils.currentLine=29098037;
 //BA.debugLineNum = 29098037;BA.debugLine="Log(json)";
anywheresoftware.b4a.keywords.Common.LogImpl("429098037",_json,0);
RDebugUtils.currentLine=29098039;
 //BA.debugLineNum = 29098039;BA.debugLine="Job.PostString(URL, json)";
_job._poststring /*String*/ (null,_url,_json);
RDebugUtils.currentLine=29098040;
 //BA.debugLineNum = 29098040;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null).SetContentType("application/json");
RDebugUtils.currentLine=29098042;
 //BA.debugLineNum = 29098042;BA.debugLine="End Sub";
return "";
}
public static String  _goback_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "goback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "goback_click", null));}
RDebugUtils.currentLine=27983872;
 //BA.debugLineNum = 27983872;BA.debugLine="Private Sub goback_Click";
RDebugUtils.currentLine=27983874;
 //BA.debugLineNum = 27983874;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=27983875;
 //BA.debugLineNum = 27983875;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4a.example.httpjob _job) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "jobdone", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "jobdone", new Object[] {_job}));}
String _response = "";
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _candidates = null;
anywheresoftware.b4a.objects.collections.Map _candidate = null;
anywheresoftware.b4a.objects.collections.Map _content = null;
anywheresoftware.b4a.objects.collections.List _parts = null;
anywheresoftware.b4a.objects.collections.Map _firstpart = null;
String _aitext = "";
anywheresoftware.b4a.objects.collections.List _flashcards = null;
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=29163520;
 //BA.debugLineNum = 29163520;BA.debugLine="Sub JobDone (job As HttpJob)";
RDebugUtils.currentLine=29163521;
 //BA.debugLineNum = 29163521;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
RDebugUtils.currentLine=29163522;
 //BA.debugLineNum = 29163522;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
RDebugUtils.currentLine=29163524;
 //BA.debugLineNum = 29163524;BA.debugLine="Dim response As String = job.GetString";
_response = _job._getstring /*String*/ (null);
RDebugUtils.currentLine=29163525;
 //BA.debugLineNum = 29163525;BA.debugLine="Log(response)";
anywheresoftware.b4a.keywords.Common.LogImpl("429163525",_response,0);
RDebugUtils.currentLine=29163527;
 //BA.debugLineNum = 29163527;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=29163528;
 //BA.debugLineNum = 29163528;BA.debugLine="jp.Initialize(response)";
_jp.Initialize(_response);
RDebugUtils.currentLine=29163530;
 //BA.debugLineNum = 29163530;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=29163531;
 //BA.debugLineNum = 29163531;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
_candidates = new anywheresoftware.b4a.objects.collections.List();
_candidates = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("candidates"))));
RDebugUtils.currentLine=29163532;
 //BA.debugLineNum = 29163532;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
_candidate = new anywheresoftware.b4a.objects.collections.Map();
_candidate = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidates.Get((int) (0))));
RDebugUtils.currentLine=29163533;
 //BA.debugLineNum = 29163533;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
_content = new anywheresoftware.b4a.objects.collections.Map();
_content = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidate.Get((Object)("content"))));
RDebugUtils.currentLine=29163534;
 //BA.debugLineNum = 29163534;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
_parts = new anywheresoftware.b4a.objects.collections.List();
_parts = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_content.Get((Object)("parts"))));
RDebugUtils.currentLine=29163535;
 //BA.debugLineNum = 29163535;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
_firstpart = new anywheresoftware.b4a.objects.collections.Map();
_firstpart = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_parts.Get((int) (0))));
RDebugUtils.currentLine=29163537;
 //BA.debugLineNum = 29163537;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
_aitext = BA.ObjectToString(_firstpart.Get((Object)("text")));
RDebugUtils.currentLine=29163538;
 //BA.debugLineNum = 29163538;BA.debugLine="AIGlobalText = aiText";
mostCurrent._aiglobaltext = _aitext;
RDebugUtils.currentLine=29163540;
 //BA.debugLineNum = 29163540;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"cached_ai.txt",_aitext);
RDebugUtils.currentLine=29163542;
 //BA.debugLineNum = 29163542;BA.debugLine="Dim flashcards As List = ParseFlashcard(aiText)";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = _parseflashcard(_aitext);
RDebugUtils.currentLine=29163544;
 //BA.debugLineNum = 29163544;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.G";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=29163546;
 //BA.debugLineNum = 29163546;BA.debugLine="LVSubdecks.AddSingleLine(topic_et.Text)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._topic_et.getText()));
RDebugUtils.currentLine=29163547;
 //BA.debugLineNum = 29163547;BA.debugLine="tappeddeck.Put(topic_et.Text, flashcards)";
_tappeddeck.Put((Object)(mostCurrent._topic_et.getText()),(Object)(_flashcards.getObject()));
RDebugUtils.currentLine=29163548;
 //BA.debugLineNum = 29163548;BA.debugLine="topic_et.Text = \"\"";
mostCurrent._topic_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=29163549;
 //BA.debugLineNum = 29163549;BA.debugLine="SaveDecks";
_savedecks();
 }else {
RDebugUtils.currentLine=29163552;
 //BA.debugLineNum = 29163552;BA.debugLine="Log(job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("429163552",_job._errormessage /*String*/ ,0);
RDebugUtils.currentLine=29163553;
 //BA.debugLineNum = 29163553;BA.debugLine="Msgbox(\"Error making your AI Flashcards\", \"Error";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error making your AI Flashcards"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 };
RDebugUtils.currentLine=29163556;
 //BA.debugLineNum = 29163556;BA.debugLine="job.Release";
_job._release /*String*/ (null);
RDebugUtils.currentLine=29163557;
 //BA.debugLineNum = 29163557;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _parseflashcard(String _jsontext) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "parseflashcard", false))
	 {return ((anywheresoftware.b4a.objects.collections.List) Debug.delegate(mostCurrent.activityBA, "parseflashcard", new Object[] {_jsontext}));}
anywheresoftware.b4a.objects.collections.List _flashcard = null;
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.collections.Map _cards = null;
String _question = "";
String _answer = "";
RDebugUtils.currentLine=29229056;
 //BA.debugLineNum = 29229056;BA.debugLine="Sub ParseFlashcard (jsonText As String) As List";
RDebugUtils.currentLine=29229058;
 //BA.debugLineNum = 29229058;BA.debugLine="Dim flashcard As List";
_flashcard = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=29229059;
 //BA.debugLineNum = 29229059;BA.debugLine="flashcard.Initialize";
_flashcard.Initialize();
RDebugUtils.currentLine=29229060;
 //BA.debugLineNum = 29229060;BA.debugLine="Try";
try {RDebugUtils.currentLine=29229062;
 //BA.debugLineNum = 29229062;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=29229063;
 //BA.debugLineNum = 29229063;BA.debugLine="jp.Initialize(jsonText)";
_jp.Initialize(_jsontext);
RDebugUtils.currentLine=29229065;
 //BA.debugLineNum = 29229065;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=29229067;
 //BA.debugLineNum = 29229067;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("flashcards"))));
RDebugUtils.currentLine=29229069;
 //BA.debugLineNum = 29229069;BA.debugLine="For Each card As Map In flashcards";
_card = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _flashcards;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group8.Get(index8)));
RDebugUtils.currentLine=29229071;
 //BA.debugLineNum = 29229071;BA.debugLine="Dim cards As Map";
_cards = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=29229072;
 //BA.debugLineNum = 29229072;BA.debugLine="cards.initialize";
_cards.Initialize();
RDebugUtils.currentLine=29229074;
 //BA.debugLineNum = 29229074;BA.debugLine="Dim question As String = card.Get(\"question\")";
_question = BA.ObjectToString(_card.Get((Object)("question")));
RDebugUtils.currentLine=29229076;
 //BA.debugLineNum = 29229076;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
_answer = BA.ObjectToString(_card.Get((Object)("answer")));
RDebugUtils.currentLine=29229078;
 //BA.debugLineNum = 29229078;BA.debugLine="cards.Put(\"Q\", question)";
_cards.Put((Object)("Q"),(Object)(_question));
RDebugUtils.currentLine=29229079;
 //BA.debugLineNum = 29229079;BA.debugLine="cards.Put(\"A\", answer)";
_cards.Put((Object)("A"),(Object)(_answer));
RDebugUtils.currentLine=29229080;
 //BA.debugLineNum = 29229080;BA.debugLine="flashcard.Add(cards)";
_flashcard.Add((Object)(_cards.getObject()));
RDebugUtils.currentLine=29229082;
 //BA.debugLineNum = 29229082;BA.debugLine="Log(\"===================\")";
anywheresoftware.b4a.keywords.Common.LogImpl("429229082","===================",0);
RDebugUtils.currentLine=29229083;
 //BA.debugLineNum = 29229083;BA.debugLine="Log(\"QUESTION: \" & question)";
anywheresoftware.b4a.keywords.Common.LogImpl("429229083","QUESTION: "+_question,0);
RDebugUtils.currentLine=29229084;
 //BA.debugLineNum = 29229084;BA.debugLine="Log(\"ANSWER: \" & answer)";
anywheresoftware.b4a.keywords.Common.LogImpl("429229084","ANSWER: "+_answer,0);
 }
};
 } 
       catch (Exception e21) {
			processBA.setLastException(e21);RDebugUtils.currentLine=29229090;
 //BA.debugLineNum = 29229090;BA.debugLine="Log(\"INVALID JSON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("429229090","INVALID JSON",0);
 };
RDebugUtils.currentLine=29229095;
 //BA.debugLineNum = 29229095;BA.debugLine="Return flashcard";
if (true) return _flashcard;
RDebugUtils.currentLine=29229096;
 //BA.debugLineNum = 29229096;BA.debugLine="End Sub";
return null;
}
public static String  _lvsubdecks_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvsubdecks_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvsubdecks_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=28246016;
 //BA.debugLineNum = 28246016;BA.debugLine="Private Sub LVSubdecks_ItemClick (Position As Int,";
RDebugUtils.currentLine=28246018;
 //BA.debugLineNum = 28246018;BA.debugLine="selectedsubdeck = Value 'gives the chsoen subdeck";
_selectedsubdeck = BA.ObjectToString(_value);
RDebugUtils.currentLine=28246020;
 //BA.debugLineNum = 28246020;BA.debugLine="StartActivity(Card_Module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._card_module.getObject()));
RDebugUtils.currentLine=28246021;
 //BA.debugLineNum = 28246021;BA.debugLine="End Sub";
return "";
}
public static String  _lvsubdecks_itemlongclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvsubdecks_itemlongclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvsubdecks_itemlongclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=28573696;
 //BA.debugLineNum = 28573696;BA.debugLine="Private Sub LVSubdecks_ItemLongClick (Position As";
RDebugUtils.currentLine=28573697;
 //BA.debugLineNum = 28573697;BA.debugLine="alterpanel.Visible = True";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28573698;
 //BA.debugLineNum = 28573698;BA.debugLine="selectedsubdeck = Value";
_selectedsubdeck = BA.ObjectToString(_value);
RDebugUtils.currentLine=28573699;
 //BA.debugLineNum = 28573699;BA.debugLine="End Sub";
return "";
}
public static String  _renamesubdeck_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "renamesubdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "renamesubdeck_click", null));}
RDebugUtils.currentLine=28442624;
 //BA.debugLineNum = 28442624;BA.debugLine="Private Sub renamesubdeck_Click";
RDebugUtils.currentLine=28442625;
 //BA.debugLineNum = 28442625;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28442626;
 //BA.debugLineNum = 28442626;BA.debugLine="renamepanel.visible = True";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=28442627;
 //BA.debugLineNum = 28442627;BA.debugLine="End Sub";
return "";
}
public static String  _startarbtn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "startarbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "startarbtn_click", null));}
RDebugUtils.currentLine=28114944;
 //BA.debugLineNum = 28114944;BA.debugLine="Private Sub startArbtn_Click";
RDebugUtils.currentLine=28114946;
 //BA.debugLineNum = 28114946;BA.debugLine="If number_of_cards = 0 Then";
if (_number_of_cards==0) { 
RDebugUtils.currentLine=28114947;
 //BA.debugLineNum = 28114947;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No cards available"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=28114948;
 //BA.debugLineNum = 28114948;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=28114950;
 //BA.debugLineNum = 28114950;BA.debugLine="StartActivity(all_active_recall)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._all_active_recall.getObject()));
RDebugUtils.currentLine=28114951;
 //BA.debugLineNum = 28114951;BA.debugLine="End Sub";
return "";
}
public static String  _topic_btn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "topic_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "topic_btn_click", null));}
anywheresoftware.b4a.objects.collections.List _getsubdeck = null;
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
String _names = "";
RDebugUtils.currentLine=29032448;
 //BA.debugLineNum = 29032448;BA.debugLine="Private Sub topic_btn_Click";
RDebugUtils.currentLine=29032449;
 //BA.debugLineNum = 29032449;BA.debugLine="If topic_et.Text = \"\" Then";
if ((mostCurrent._topic_et.getText()).equals("")) { 
RDebugUtils.currentLine=29032450;
 //BA.debugLineNum = 29032450;BA.debugLine="Msgbox(\"Invalid Subdeck Topic\", \"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Invalid Subdeck Topic"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
RDebugUtils.currentLine=29032451;
 //BA.debugLineNum = 29032451;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=29032454;
 //BA.debugLineNum = 29032454;BA.debugLine="Dim getsubdeck As List";
_getsubdeck = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=29032455;
 //BA.debugLineNum = 29032455;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=29032456;
 //BA.debugLineNum = 29032456;BA.debugLine="For Each names As String In tappeddeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group7 = _tappeddeck.Keys();
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.Get(index7));
RDebugUtils.currentLine=29032457;
 //BA.debugLineNum = 29032457;BA.debugLine="getsubdeck = tappeddeck.Get(selectedsubdeck)";
_getsubdeck = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(_selectedsubdeck))));
RDebugUtils.currentLine=29032458;
 //BA.debugLineNum = 29032458;BA.debugLine="If topic_et.Text = names Then";
if ((mostCurrent._topic_et.getText()).equals(_names)) { 
RDebugUtils.currentLine=29032459;
 //BA.debugLineNum = 29032459;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=29032460;
 //BA.debugLineNum = 29032460;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=29032464;
 //BA.debugLineNum = 29032464;BA.debugLine="ProgressDialogShow(\"Generating Flashcards...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generating Flashcards..."));
RDebugUtils.currentLine=29032466;
 //BA.debugLineNum = 29032466;BA.debugLine="GenerateFlashCards(topic_et.Text)";
_generateflashcards(mostCurrent._topic_et.getText());
RDebugUtils.currentLine=29032468;
 //BA.debugLineNum = 29032468;BA.debugLine="topic_panel.Visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=29032469;
 //BA.debugLineNum = 29032469;BA.debugLine="End Sub";
return "";
}
public static String  _topic_cancel_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "topic_cancel_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "topic_cancel_click", null));}
RDebugUtils.currentLine=28966912;
 //BA.debugLineNum = 28966912;BA.debugLine="Private Sub topic_cancel_Click";
RDebugUtils.currentLine=28966913;
 //BA.debugLineNum = 28966913;BA.debugLine="topic_panel.Visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=28966914;
 //BA.debugLineNum = 28966914;BA.debugLine="topic_et.Text = \"\"";
mostCurrent._topic_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=28966915;
 //BA.debugLineNum = 28966915;BA.debugLine="End Sub";
return "";
}
}