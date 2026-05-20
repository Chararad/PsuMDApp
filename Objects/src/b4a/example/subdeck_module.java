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
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activerecall_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activerecall_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activerecall_click", null));}
anywheresoftware.b4a.objects.collections.Map _chosendeck = null;
String _deckname = "";
anywheresoftware.b4a.objects.collections.List _flashacards = null;
RDebugUtils.currentLine=33816576;
 //BA.debugLineNum = 33816576;BA.debugLine="Private Sub activerecall_Click";
RDebugUtils.currentLine=33816579;
 //BA.debugLineNum = 33816579;BA.debugLine="number_of_cards = 0";
_number_of_cards = (int) (0);
RDebugUtils.currentLine=33816581;
 //BA.debugLineNum = 33816581;BA.debugLine="Dim chosendeck As Map = alldecks.Get(selecteddeck";
_chosendeck = new anywheresoftware.b4a.objects.collections.Map();
_chosendeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
RDebugUtils.currentLine=33816583;
 //BA.debugLineNum = 33816583;BA.debugLine="For Each deckName As String In chosendeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _chosendeck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=33816584;
 //BA.debugLineNum = 33816584;BA.debugLine="Dim flashacards As List = chosendeck.Get(deckNam";
_flashacards = new anywheresoftware.b4a.objects.collections.List();
_flashacards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_chosendeck.Get((Object)(_deckname))));
RDebugUtils.currentLine=33816585;
 //BA.debugLineNum = 33816585;BA.debugLine="number_of_cards = number_of_cards + flashacards.";
_number_of_cards = (int) (_number_of_cards+_flashacards.getSize());
 }
};
RDebugUtils.currentLine=33816588;
 //BA.debugLineNum = 33816588;BA.debugLine="AR_confirmationpanel.Visible = True";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=33816589;
 //BA.debugLineNum = 33816589;BA.debugLine="confirmlabel.Text = \"You got \" & number_of_cards";
mostCurrent._confirmlabel.setText(BA.ObjectToCharSequence("You got "+BA.NumberToString(_number_of_cards)+" cards"));
RDebugUtils.currentLine=33816590;
 //BA.debugLineNum = 33816590;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=33095680;
 //BA.debugLineNum = 33095680;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=33095681;
 //BA.debugLineNum = 33095681;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=33095683;
 //BA.debugLineNum = 33095683;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=33095684;
 //BA.debugLineNum = 33095684;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=33095686;
 //BA.debugLineNum = 33095686;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark\"";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=33095689;
 //BA.debugLineNum = 33095689;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=33095690;
 //BA.debugLineNum = 33095690;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=33095692;
 //BA.debugLineNum = 33095692;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark2";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=33095695;
 //BA.debugLineNum = 33095695;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=33095696;
 //BA.debugLineNum = 33095696;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=33095698;
 //BA.debugLineNum = 33095698;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark3";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=33095702;
 //BA.debugLineNum = 33095702;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=33095704;
 //BA.debugLineNum = 33095704;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
mostCurrent._lvsubdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
RDebugUtils.currentLine=33095706;
 //BA.debugLineNum = 33095706;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
mostCurrent._lvsubdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=33095709;
 //BA.debugLineNum = 33095709;BA.debugLine="cc.Initialize(\"CC\")";
mostCurrent._cc.Initialize("CC");
RDebugUtils.currentLine=33095711;
 //BA.debugLineNum = 33095711;BA.debugLine="decknamelabel.Text = selecteddeck";
mostCurrent._decknamelabel.setText(BA.ObjectToCharSequence(mostCurrent._selecteddeck));
RDebugUtils.currentLine=33095713;
 //BA.debugLineNum = 33095713;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33095714;
 //BA.debugLineNum = 33095714;BA.debugLine="addpanel2.Visible = False";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33095715;
 //BA.debugLineNum = 33095715;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33095716;
 //BA.debugLineNum = 33095716;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33095717;
 //BA.debugLineNum = 33095717;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33095718;
 //BA.debugLineNum = 33095718;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33095720;
 //BA.debugLineNum = 33095720;BA.debugLine="Refresh";
_refresh();
RDebugUtils.currentLine=33095722;
 //BA.debugLineNum = 33095722;BA.debugLine="End Sub";
return "";
}
public static String  _refresh() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "refresh", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "refresh", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
String _deckname = "";
RDebugUtils.currentLine=33226752;
 //BA.debugLineNum = 33226752;BA.debugLine="Sub Refresh";
RDebugUtils.currentLine=33226753;
 //BA.debugLineNum = 33226753;BA.debugLine="LVSubdecks.clear";
mostCurrent._lvsubdecks.Clear();
RDebugUtils.currentLine=33226755;
 //BA.debugLineNum = 33226755;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
RDebugUtils.currentLine=33226758;
 //BA.debugLineNum = 33226758;BA.debugLine="For Each deckName As String In tappeddeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _tappeddeck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=33226759;
 //BA.debugLineNum = 33226759;BA.debugLine="LVSubdecks.AddSingleLine(deckName)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(_deckname));
 }
};
RDebugUtils.currentLine=33226761;
 //BA.debugLineNum = 33226761;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="subdeck_module";
RDebugUtils.currentLine=33357824;
 //BA.debugLineNum = 33357824;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=33357826;
 //BA.debugLineNum = 33357826;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=33292288;
 //BA.debugLineNum = 33292288;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=33292289;
 //BA.debugLineNum = 33292289;BA.debugLine="If all_active_recall.praise = True Then";
if (mostCurrent._all_active_recall._praise /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=33292290;
 //BA.debugLineNum = 33292290;BA.debugLine="all_active_recall.praise = False";
mostCurrent._all_active_recall._praise /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=33292291;
 //BA.debugLineNum = 33292291;BA.debugLine="MsgboxAsync(\"You Finished Your Deck\", \"Congratul";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You Finished Your Deck"),BA.ObjectToCharSequence("Congratulations"),processBA);
 };
RDebugUtils.currentLine=33292293;
 //BA.debugLineNum = 33292293;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33292294;
 //BA.debugLineNum = 33292294;BA.debugLine="End Sub";
return "";
}
public static String  _addbtn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addbtn_click", null));}
RDebugUtils.currentLine=33423360;
 //BA.debugLineNum = 33423360;BA.debugLine="Private Sub Addbtn_Click";
RDebugUtils.currentLine=33423362;
 //BA.debugLineNum = 33423362;BA.debugLine="If addpanel2.Visible = False Then";
if (mostCurrent._addpanel2.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=33423363;
 //BA.debugLineNum = 33423363;BA.debugLine="addpanel2.Visible = True";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=33423365;
 //BA.debugLineNum = 33423365;BA.debugLine="addpanel2.Visible = False";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=33423368;
 //BA.debugLineNum = 33423368;BA.debugLine="End Sub";
return "";
}
public static String  _addcard_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addcard_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addcard_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=33488896;
 //BA.debugLineNum = 33488896;BA.debugLine="Private Sub addcard_Click";
RDebugUtils.currentLine=33488898;
 //BA.debugLineNum = 33488898;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=33488899;
 //BA.debugLineNum = 33488899;BA.debugLine="If tappeddeck.Size = 0 Then";
if (_tappeddeck.getSize()==0) { 
RDebugUtils.currentLine=33488900;
 //BA.debugLineNum = 33488900;BA.debugLine="MsgboxAsync(\"Create A Sub-Deck first\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Create A Sub-Deck first"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=33488901;
 //BA.debugLineNum = 33488901;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=33488903;
 //BA.debugLineNum = 33488903;BA.debugLine="StartActivity(Add_card_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module.getObject()));
RDebugUtils.currentLine=33488904;
 //BA.debugLineNum = 33488904;BA.debugLine="End Sub";
return "";
}
public static String  _addsub_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addsub_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addsub_click", null));}
RDebugUtils.currentLine=33554432;
 //BA.debugLineNum = 33554432;BA.debugLine="Private Sub addsub_Click";
RDebugUtils.currentLine=33554434;
 //BA.debugLineNum = 33554434;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=33554435;
 //BA.debugLineNum = 33554435;BA.debugLine="End Sub";
return "";
}
public static String  _ai_cards_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ai_cards_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ai_cards_click", null));}
RDebugUtils.currentLine=34668544;
 //BA.debugLineNum = 34668544;BA.debugLine="Private Sub AI_cards_Click";
RDebugUtils.currentLine=34668545;
 //BA.debugLineNum = 34668545;BA.debugLine="If topic_panel.Visible = True Then";
if (mostCurrent._topic_panel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=34668546;
 //BA.debugLineNum = 34668546;BA.debugLine="topic_panel.visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34668547;
 //BA.debugLineNum = 34668547;BA.debugLine="Return";
if (true) return "";
 }else {
RDebugUtils.currentLine=34668549;
 //BA.debugLineNum = 34668549;BA.debugLine="topic_panel.Visible = True";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=34668552;
 //BA.debugLineNum = 34668552;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "backbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "backbtn_click", null));}
RDebugUtils.currentLine=34078720;
 //BA.debugLineNum = 34078720;BA.debugLine="Private Sub backbtn_Click";
RDebugUtils.currentLine=34078721;
 //BA.debugLineNum = 34078721;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=34078722;
 //BA.debugLineNum = 34078722;BA.debugLine="End Sub";
return "";
}
public static String  _cancel_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancel_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancel_click", null));}
RDebugUtils.currentLine=33619968;
 //BA.debugLineNum = 33619968;BA.debugLine="Private Sub cancel_Click";
RDebugUtils.currentLine=33619970;
 //BA.debugLineNum = 33619970;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33619971;
 //BA.debugLineNum = 33619971;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=33619972;
 //BA.debugLineNum = 33619972;BA.debugLine="End Sub";
return "";
}
public static String  _cancelalter_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelalter_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelalter_click", null));}
RDebugUtils.currentLine=34275328;
 //BA.debugLineNum = 34275328;BA.debugLine="Private Sub cancelalter_Click";
RDebugUtils.currentLine=34275329;
 //BA.debugLineNum = 34275329;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34275330;
 //BA.debugLineNum = 34275330;BA.debugLine="End Sub";
return "";
}
public static String  _cancelconfirmation_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelconfirmation_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelconfirmation_click", null));}
RDebugUtils.currentLine=33947648;
 //BA.debugLineNum = 33947648;BA.debugLine="Private Sub cancelconfirmation_Click";
RDebugUtils.currentLine=33947650;
 //BA.debugLineNum = 33947650;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33947651;
 //BA.debugLineNum = 33947651;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "canceldelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "canceldelete_click", null));}
RDebugUtils.currentLine=34603008;
 //BA.debugLineNum = 34603008;BA.debugLine="Private Sub canceldelete_Click";
RDebugUtils.currentLine=34603009;
 //BA.debugLineNum = 34603009;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34603010;
 //BA.debugLineNum = 34603010;BA.debugLine="End Sub";
return "";
}
public static String  _cancelrename_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cancelrename_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cancelrename_click", null));}
RDebugUtils.currentLine=34406400;
 //BA.debugLineNum = 34406400;BA.debugLine="Private Sub cancelrename_Click";
RDebugUtils.currentLine=34406401;
 //BA.debugLineNum = 34406401;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34406402;
 //BA.debugLineNum = 34406402;BA.debugLine="renameet.Text = \"\"";
mostCurrent._renameet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34406403;
 //BA.debugLineNum = 34406403;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmdelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmdelete_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
RDebugUtils.currentLine=34537472;
 //BA.debugLineNum = 34537472;BA.debugLine="Private Sub confirmdelete_Click";
RDebugUtils.currentLine=34537473;
 //BA.debugLineNum = 34537473;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=34537474;
 //BA.debugLineNum = 34537474;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
_tappeddeck.Remove((Object)(_selectedsubdeck));
RDebugUtils.currentLine=34537475;
 //BA.debugLineNum = 34537475;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=34537476;
 //BA.debugLineNum = 34537476;BA.debugLine="Refresh";
_refresh();
RDebugUtils.currentLine=34537477;
 //BA.debugLineNum = 34537477;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34537478;
 //BA.debugLineNum = 34537478;BA.debugLine="End Sub";
return "";
}
public static String  _savedecks() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savedecks", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savedecks", null));}
RDebugUtils.currentLine=33161216;
 //BA.debugLineNum = 33161216;BA.debugLine="Sub SaveDecks";
RDebugUtils.currentLine=33161217;
 //BA.debugLineNum = 33161217;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
mostCurrent._flashcardactivity._kvs /*b4a.example3.keyvaluestore*/ ._put("deck_data",(Object)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
RDebugUtils.currentLine=33161218;
 //BA.debugLineNum = 33161218;BA.debugLine="End Sub";
return "";
}
public static String  _confirmrename_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmrename_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "confirmrename_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _getsubdeck = null;
String _names = "";
RDebugUtils.currentLine=34471936;
 //BA.debugLineNum = 34471936;BA.debugLine="Private Sub confirmrename_Click";
RDebugUtils.currentLine=34471937;
 //BA.debugLineNum = 34471937;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=34471939;
 //BA.debugLineNum = 34471939;BA.debugLine="If renameet.Text = \"\" Then";
if ((mostCurrent._renameet.getText()).equals("")) { 
RDebugUtils.currentLine=34471940;
 //BA.debugLineNum = 34471940;BA.debugLine="MsgboxAsync(\"New Name must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("New Name must have a name"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=34471941;
 //BA.debugLineNum = 34471941;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34471945;
 //BA.debugLineNum = 34471945;BA.debugLine="Dim getsubdeck As List = tappeddeck.Get(selecteds";
_getsubdeck = new anywheresoftware.b4a.objects.collections.List();
_getsubdeck = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(_selectedsubdeck))));
RDebugUtils.currentLine=34471947;
 //BA.debugLineNum = 34471947;BA.debugLine="For Each names As String In tappeddeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group7 = _tappeddeck.Keys();
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.Get(index7));
RDebugUtils.currentLine=34471948;
 //BA.debugLineNum = 34471948;BA.debugLine="If renameet.Text = names Then";
if ((mostCurrent._renameet.getText()).equals(_names)) { 
RDebugUtils.currentLine=34471949;
 //BA.debugLineNum = 34471949;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=34471950;
 //BA.debugLineNum = 34471950;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=34471954;
 //BA.debugLineNum = 34471954;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
_tappeddeck.Remove((Object)(_selectedsubdeck));
RDebugUtils.currentLine=34471955;
 //BA.debugLineNum = 34471955;BA.debugLine="tappeddeck.Put(renameet.Text, getsubdeck)";
_tappeddeck.Put((Object)(mostCurrent._renameet.getText()),(Object)(_getsubdeck.getObject()));
RDebugUtils.currentLine=34471956;
 //BA.debugLineNum = 34471956;BA.debugLine="renameet.Text = \"\"";
mostCurrent._renameet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34471957;
 //BA.debugLineNum = 34471957;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=34471958;
 //BA.debugLineNum = 34471958;BA.debugLine="Refresh";
_refresh();
RDebugUtils.currentLine=34471959;
 //BA.debugLineNum = 34471959;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34471960;
 //BA.debugLineNum = 34471960;BA.debugLine="End Sub";
return "";
}
public static String  _create_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "create_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "create_click", null));}
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
Object _name = null;
RDebugUtils.currentLine=33685504;
 //BA.debugLineNum = 33685504;BA.debugLine="Private Sub create_Click";
RDebugUtils.currentLine=33685507;
 //BA.debugLineNum = 33685507;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
RDebugUtils.currentLine=33685508;
 //BA.debugLineNum = 33685508;BA.debugLine="Dim flashcards As List";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=33685510;
 //BA.debugLineNum = 33685510;BA.debugLine="flashcards.initialize";
_flashcards.Initialize();
RDebugUtils.currentLine=33685513;
 //BA.debugLineNum = 33685513;BA.debugLine="For Each name In tappeddeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group4 = _tappeddeck.Keys();
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_name = group4.Get(index4);
RDebugUtils.currentLine=33685514;
 //BA.debugLineNum = 33685514;BA.debugLine="If et1.Text = name Then";
if ((mostCurrent._et1.getText()).equals(BA.ObjectToString(_name))) { 
RDebugUtils.currentLine=33685515;
 //BA.debugLineNum = 33685515;BA.debugLine="MsgboxAsync(\"Sub-Deck Already Exist\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=33685516;
 //BA.debugLineNum = 33685516;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=33685521;
 //BA.debugLineNum = 33685521;BA.debugLine="If et1.Text = \"\" Then";
if ((mostCurrent._et1.getText()).equals("")) { 
RDebugUtils.currentLine=33685522;
 //BA.debugLineNum = 33685522;BA.debugLine="MsgboxAsync(\"Sub-Deck must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck must have a name"),BA.ObjectToCharSequence("Error"),processBA);
 }else {
RDebugUtils.currentLine=33685525;
 //BA.debugLineNum = 33685525;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33685526;
 //BA.debugLineNum = 33685526;BA.debugLine="LVSubdecks.AddSingleLine(et1.Text)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._et1.getText()));
RDebugUtils.currentLine=33685527;
 //BA.debugLineNum = 33685527;BA.debugLine="tappeddeck.Put(et1.Text, flashcards)";
_tappeddeck.Put((Object)(mostCurrent._et1.getText()),(Object)(_flashcards.getObject()));
RDebugUtils.currentLine=33685528;
 //BA.debugLineNum = 33685528;BA.debugLine="SaveDecks";
_savedecks();
RDebugUtils.currentLine=33685530;
 //BA.debugLineNum = 33685530;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
 };
RDebugUtils.currentLine=33685533;
 //BA.debugLineNum = 33685533;BA.debugLine="End Sub";
return "";
}
public static String  _deletesubdeck_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletesubdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletesubdeck_click", null));}
RDebugUtils.currentLine=34144256;
 //BA.debugLineNum = 34144256;BA.debugLine="Private Sub deletesubdeck_Click";
RDebugUtils.currentLine=34144257;
 //BA.debugLineNum = 34144257;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34144258;
 //BA.debugLineNum = 34144258;BA.debugLine="deleteconfirmation.Visible = True";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34144259;
 //BA.debugLineNum = 34144259;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=34865152;
 //BA.debugLineNum = 34865152;BA.debugLine="Sub GenerateFlashCards(Topic As String)";
RDebugUtils.currentLine=34865154;
 //BA.debugLineNum = 34865154;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+mostCurrent._myapikey;
RDebugUtils.currentLine=34865156;
 //BA.debugLineNum = 34865156;BA.debugLine="Dim Job As HttpJob";
_job = new b4a.example.httpjob();
RDebugUtils.currentLine=34865157;
 //BA.debugLineNum = 34865157;BA.debugLine="Job.Initialize(\"Gemini\", Me)";
_job._initialize /*String*/ (null,processBA,"Gemini",subdeck_module.getObject());
RDebugUtils.currentLine=34865159;
 //BA.debugLineNum = 34865159;BA.debugLine="Dim prompt As String = _ 	\"Create flashcards from";
_prompt = "Create flashcards from the Topic below."+"RULES:"+"- Return ONLY valid JSON"+"- No explanations"+"- No markdown"+"- Must have 20+ Flashcards"+"- Format must exactly follow this structure:"+"{"+"\"flashcards\": ["+"{"+"\"question\": \"Question here\","+"\"answer\": \"Answer here\""+"}"+"]"+"}"+"Topic:"+_topic;
RDebugUtils.currentLine=34865177;
 //BA.debugLineNum = 34865177;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=34865178;
 //BA.debugLineNum = 34865178;BA.debugLine="root.Initialize";
_root.Initialize();
RDebugUtils.currentLine=34865180;
 //BA.debugLineNum = 34865180;BA.debugLine="Dim contents As List";
_contents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34865181;
 //BA.debugLineNum = 34865181;BA.debugLine="contents.Initialize";
_contents.Initialize();
RDebugUtils.currentLine=34865183;
 //BA.debugLineNum = 34865183;BA.debugLine="Dim contentItem As Map";
_contentitem = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=34865184;
 //BA.debugLineNum = 34865184;BA.debugLine="contentItem.Initialize";
_contentitem.Initialize();
RDebugUtils.currentLine=34865186;
 //BA.debugLineNum = 34865186;BA.debugLine="Dim parts As List";
_parts = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34865187;
 //BA.debugLineNum = 34865187;BA.debugLine="parts.Initialize";
_parts.Initialize();
RDebugUtils.currentLine=34865189;
 //BA.debugLineNum = 34865189;BA.debugLine="Dim textPart As Map";
_textpart = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=34865190;
 //BA.debugLineNum = 34865190;BA.debugLine="textPart.Initialize";
_textpart.Initialize();
RDebugUtils.currentLine=34865192;
 //BA.debugLineNum = 34865192;BA.debugLine="textPart.Put(\"text\", prompt)";
_textpart.Put((Object)("text"),(Object)(_prompt));
RDebugUtils.currentLine=34865193;
 //BA.debugLineNum = 34865193;BA.debugLine="parts.Add(textPart)";
_parts.Add((Object)(_textpart.getObject()));
RDebugUtils.currentLine=34865195;
 //BA.debugLineNum = 34865195;BA.debugLine="contentItem.Put(\"parts\", parts)";
_contentitem.Put((Object)("parts"),(Object)(_parts.getObject()));
RDebugUtils.currentLine=34865196;
 //BA.debugLineNum = 34865196;BA.debugLine="contents.Add(contentItem)";
_contents.Add((Object)(_contentitem.getObject()));
RDebugUtils.currentLine=34865197;
 //BA.debugLineNum = 34865197;BA.debugLine="root.Put(\"contents\", contents)";
_root.Put((Object)("contents"),(Object)(_contents.getObject()));
RDebugUtils.currentLine=34865199;
 //BA.debugLineNum = 34865199;BA.debugLine="Dim gen As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=34865200;
 //BA.debugLineNum = 34865200;BA.debugLine="gen.Initialize(root)";
_gen.Initialize(_root);
RDebugUtils.currentLine=34865202;
 //BA.debugLineNum = 34865202;BA.debugLine="Dim json As String = gen.ToString";
_json = _gen.ToString();
RDebugUtils.currentLine=34865204;
 //BA.debugLineNum = 34865204;BA.debugLine="Log(\"REQUEST: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("134865204","REQUEST: ",0);
RDebugUtils.currentLine=34865205;
 //BA.debugLineNum = 34865205;BA.debugLine="Log(json)";
anywheresoftware.b4a.keywords.Common.LogImpl("134865205",_json,0);
RDebugUtils.currentLine=34865207;
 //BA.debugLineNum = 34865207;BA.debugLine="Job.PostString(URL, json)";
_job._poststring /*String*/ (null,_url,_json);
RDebugUtils.currentLine=34865208;
 //BA.debugLineNum = 34865208;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null).SetContentType("application/json");
RDebugUtils.currentLine=34865210;
 //BA.debugLineNum = 34865210;BA.debugLine="End Sub";
return "";
}
public static String  _goback_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "goback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "goback_click", null));}
RDebugUtils.currentLine=33751040;
 //BA.debugLineNum = 33751040;BA.debugLine="Private Sub goback_Click";
RDebugUtils.currentLine=33751042;
 //BA.debugLineNum = 33751042;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=33751043;
 //BA.debugLineNum = 33751043;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=34930688;
 //BA.debugLineNum = 34930688;BA.debugLine="Sub JobDone (job As HttpJob)";
RDebugUtils.currentLine=34930689;
 //BA.debugLineNum = 34930689;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
RDebugUtils.currentLine=34930690;
 //BA.debugLineNum = 34930690;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
RDebugUtils.currentLine=34930692;
 //BA.debugLineNum = 34930692;BA.debugLine="Dim response As String = job.GetString";
_response = _job._getstring /*String*/ (null);
RDebugUtils.currentLine=34930693;
 //BA.debugLineNum = 34930693;BA.debugLine="Log(response)";
anywheresoftware.b4a.keywords.Common.LogImpl("134930693",_response,0);
RDebugUtils.currentLine=34930695;
 //BA.debugLineNum = 34930695;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=34930696;
 //BA.debugLineNum = 34930696;BA.debugLine="jp.Initialize(response)";
_jp.Initialize(_response);
RDebugUtils.currentLine=34930698;
 //BA.debugLineNum = 34930698;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=34930699;
 //BA.debugLineNum = 34930699;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
_candidates = new anywheresoftware.b4a.objects.collections.List();
_candidates = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("candidates"))));
RDebugUtils.currentLine=34930700;
 //BA.debugLineNum = 34930700;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
_candidate = new anywheresoftware.b4a.objects.collections.Map();
_candidate = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidates.Get((int) (0))));
RDebugUtils.currentLine=34930701;
 //BA.debugLineNum = 34930701;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
_content = new anywheresoftware.b4a.objects.collections.Map();
_content = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidate.Get((Object)("content"))));
RDebugUtils.currentLine=34930702;
 //BA.debugLineNum = 34930702;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
_parts = new anywheresoftware.b4a.objects.collections.List();
_parts = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_content.Get((Object)("parts"))));
RDebugUtils.currentLine=34930703;
 //BA.debugLineNum = 34930703;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
_firstpart = new anywheresoftware.b4a.objects.collections.Map();
_firstpart = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_parts.Get((int) (0))));
RDebugUtils.currentLine=34930705;
 //BA.debugLineNum = 34930705;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
_aitext = BA.ObjectToString(_firstpart.Get((Object)("text")));
RDebugUtils.currentLine=34930706;
 //BA.debugLineNum = 34930706;BA.debugLine="AIGlobalText = aiText";
mostCurrent._aiglobaltext = _aitext;
RDebugUtils.currentLine=34930708;
 //BA.debugLineNum = 34930708;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"cached_ai.txt",_aitext);
RDebugUtils.currentLine=34930710;
 //BA.debugLineNum = 34930710;BA.debugLine="Dim flashcards As List = ParseFlashcard(aiText)";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = _parseflashcard(_aitext);
RDebugUtils.currentLine=34930712;
 //BA.debugLineNum = 34930712;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.G";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=34930714;
 //BA.debugLineNum = 34930714;BA.debugLine="LVSubdecks.AddSingleLine(topic_et.Text)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._topic_et.getText()));
RDebugUtils.currentLine=34930715;
 //BA.debugLineNum = 34930715;BA.debugLine="tappeddeck.Put(topic_et.Text, flashcards)";
_tappeddeck.Put((Object)(mostCurrent._topic_et.getText()),(Object)(_flashcards.getObject()));
RDebugUtils.currentLine=34930716;
 //BA.debugLineNum = 34930716;BA.debugLine="topic_et.Text = \"\"";
mostCurrent._topic_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34930717;
 //BA.debugLineNum = 34930717;BA.debugLine="SaveDecks";
_savedecks();
 }else {
RDebugUtils.currentLine=34930720;
 //BA.debugLineNum = 34930720;BA.debugLine="Log(job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("134930720",_job._errormessage /*String*/ ,0);
RDebugUtils.currentLine=34930721;
 //BA.debugLineNum = 34930721;BA.debugLine="Msgbox(\"Error making your AI Flashcards\", \"Error";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error making your AI Flashcards"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 };
RDebugUtils.currentLine=34930724;
 //BA.debugLineNum = 34930724;BA.debugLine="job.Release";
_job._release /*String*/ (null);
RDebugUtils.currentLine=34930725;
 //BA.debugLineNum = 34930725;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=34996224;
 //BA.debugLineNum = 34996224;BA.debugLine="Sub ParseFlashcard (jsonText As String) As List";
RDebugUtils.currentLine=34996226;
 //BA.debugLineNum = 34996226;BA.debugLine="Dim flashcard As List";
_flashcard = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34996227;
 //BA.debugLineNum = 34996227;BA.debugLine="flashcard.Initialize";
_flashcard.Initialize();
RDebugUtils.currentLine=34996228;
 //BA.debugLineNum = 34996228;BA.debugLine="Try";
try {RDebugUtils.currentLine=34996230;
 //BA.debugLineNum = 34996230;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=34996231;
 //BA.debugLineNum = 34996231;BA.debugLine="jp.Initialize(jsonText)";
_jp.Initialize(_jsontext);
RDebugUtils.currentLine=34996233;
 //BA.debugLineNum = 34996233;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=34996235;
 //BA.debugLineNum = 34996235;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("flashcards"))));
RDebugUtils.currentLine=34996237;
 //BA.debugLineNum = 34996237;BA.debugLine="For Each card As Map In flashcards";
_card = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _flashcards;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group8.Get(index8)));
RDebugUtils.currentLine=34996239;
 //BA.debugLineNum = 34996239;BA.debugLine="Dim cards As Map";
_cards = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=34996240;
 //BA.debugLineNum = 34996240;BA.debugLine="cards.initialize";
_cards.Initialize();
RDebugUtils.currentLine=34996242;
 //BA.debugLineNum = 34996242;BA.debugLine="Dim question As String = card.Get(\"question\")";
_question = BA.ObjectToString(_card.Get((Object)("question")));
RDebugUtils.currentLine=34996244;
 //BA.debugLineNum = 34996244;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
_answer = BA.ObjectToString(_card.Get((Object)("answer")));
RDebugUtils.currentLine=34996246;
 //BA.debugLineNum = 34996246;BA.debugLine="cards.Put(\"Q\", question)";
_cards.Put((Object)("Q"),(Object)(_question));
RDebugUtils.currentLine=34996247;
 //BA.debugLineNum = 34996247;BA.debugLine="cards.Put(\"A\", answer)";
_cards.Put((Object)("A"),(Object)(_answer));
RDebugUtils.currentLine=34996248;
 //BA.debugLineNum = 34996248;BA.debugLine="flashcard.Add(cards)";
_flashcard.Add((Object)(_cards.getObject()));
RDebugUtils.currentLine=34996250;
 //BA.debugLineNum = 34996250;BA.debugLine="Log(\"===================\")";
anywheresoftware.b4a.keywords.Common.LogImpl("134996250","===================",0);
RDebugUtils.currentLine=34996251;
 //BA.debugLineNum = 34996251;BA.debugLine="Log(\"QUESTION: \" & question)";
anywheresoftware.b4a.keywords.Common.LogImpl("134996251","QUESTION: "+_question,0);
RDebugUtils.currentLine=34996252;
 //BA.debugLineNum = 34996252;BA.debugLine="Log(\"ANSWER: \" & answer)";
anywheresoftware.b4a.keywords.Common.LogImpl("134996252","ANSWER: "+_answer,0);
 }
};
 } 
       catch (Exception e21) {
			processBA.setLastException(e21);RDebugUtils.currentLine=34996258;
 //BA.debugLineNum = 34996258;BA.debugLine="Log(\"INVALID JSON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("134996258","INVALID JSON",0);
 };
RDebugUtils.currentLine=34996263;
 //BA.debugLineNum = 34996263;BA.debugLine="Return flashcard";
if (true) return _flashcard;
RDebugUtils.currentLine=34996264;
 //BA.debugLineNum = 34996264;BA.debugLine="End Sub";
return null;
}
public static String  _lvsubdecks_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvsubdecks_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvsubdecks_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=34013184;
 //BA.debugLineNum = 34013184;BA.debugLine="Private Sub LVSubdecks_ItemClick (Position As Int,";
RDebugUtils.currentLine=34013186;
 //BA.debugLineNum = 34013186;BA.debugLine="selectedsubdeck = Value 'gives the chsoen subdeck";
_selectedsubdeck = BA.ObjectToString(_value);
RDebugUtils.currentLine=34013188;
 //BA.debugLineNum = 34013188;BA.debugLine="StartActivity(Card_Module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._card_module.getObject()));
RDebugUtils.currentLine=34013189;
 //BA.debugLineNum = 34013189;BA.debugLine="End Sub";
return "";
}
public static String  _lvsubdecks_itemlongclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lvsubdecks_itemlongclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lvsubdecks_itemlongclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=34340864;
 //BA.debugLineNum = 34340864;BA.debugLine="Private Sub LVSubdecks_ItemLongClick (Position As";
RDebugUtils.currentLine=34340865;
 //BA.debugLineNum = 34340865;BA.debugLine="alterpanel.Visible = True";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34340866;
 //BA.debugLineNum = 34340866;BA.debugLine="selectedsubdeck = Value";
_selectedsubdeck = BA.ObjectToString(_value);
RDebugUtils.currentLine=34340867;
 //BA.debugLineNum = 34340867;BA.debugLine="End Sub";
return "";
}
public static String  _renamesubdeck_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "renamesubdeck_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "renamesubdeck_click", null));}
RDebugUtils.currentLine=34209792;
 //BA.debugLineNum = 34209792;BA.debugLine="Private Sub renamesubdeck_Click";
RDebugUtils.currentLine=34209793;
 //BA.debugLineNum = 34209793;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34209794;
 //BA.debugLineNum = 34209794;BA.debugLine="renamepanel.visible = True";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34209795;
 //BA.debugLineNum = 34209795;BA.debugLine="End Sub";
return "";
}
public static String  _startarbtn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "startarbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "startarbtn_click", null));}
RDebugUtils.currentLine=33882112;
 //BA.debugLineNum = 33882112;BA.debugLine="Private Sub startArbtn_Click";
RDebugUtils.currentLine=33882114;
 //BA.debugLineNum = 33882114;BA.debugLine="If number_of_cards = 0 Then";
if (_number_of_cards==0) { 
RDebugUtils.currentLine=33882115;
 //BA.debugLineNum = 33882115;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No cards available"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=33882116;
 //BA.debugLineNum = 33882116;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=33882118;
 //BA.debugLineNum = 33882118;BA.debugLine="StartActivity(all_active_recall)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._all_active_recall.getObject()));
RDebugUtils.currentLine=33882119;
 //BA.debugLineNum = 33882119;BA.debugLine="End Sub";
return "";
}
public static String  _topic_btn_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "topic_btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "topic_btn_click", null));}
anywheresoftware.b4a.objects.collections.List _getsubdeck = null;
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
String _names = "";
RDebugUtils.currentLine=34799616;
 //BA.debugLineNum = 34799616;BA.debugLine="Private Sub topic_btn_Click";
RDebugUtils.currentLine=34799617;
 //BA.debugLineNum = 34799617;BA.debugLine="If topic_et.Text = \"\" Then";
if ((mostCurrent._topic_et.getText()).equals("")) { 
RDebugUtils.currentLine=34799618;
 //BA.debugLineNum = 34799618;BA.debugLine="Msgbox(\"Invalid Subdeck Topic\", \"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Invalid Subdeck Topic"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
RDebugUtils.currentLine=34799619;
 //BA.debugLineNum = 34799619;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34799622;
 //BA.debugLineNum = 34799622;BA.debugLine="Dim getsubdeck As List";
_getsubdeck = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34799623;
 //BA.debugLineNum = 34799623;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
RDebugUtils.currentLine=34799624;
 //BA.debugLineNum = 34799624;BA.debugLine="For Each names As String In tappeddeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group7 = _tappeddeck.Keys();
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.Get(index7));
RDebugUtils.currentLine=34799625;
 //BA.debugLineNum = 34799625;BA.debugLine="getsubdeck = tappeddeck.Get(selectedsubdeck)";
_getsubdeck = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(_selectedsubdeck))));
RDebugUtils.currentLine=34799626;
 //BA.debugLineNum = 34799626;BA.debugLine="If topic_et.Text = names Then";
if ((mostCurrent._topic_et.getText()).equals(_names)) { 
RDebugUtils.currentLine=34799627;
 //BA.debugLineNum = 34799627;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=34799628;
 //BA.debugLineNum = 34799628;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=34799632;
 //BA.debugLineNum = 34799632;BA.debugLine="ProgressDialogShow(\"Generating Flashcards...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generating Flashcards..."));
RDebugUtils.currentLine=34799634;
 //BA.debugLineNum = 34799634;BA.debugLine="GenerateFlashCards(topic_et.Text)";
_generateflashcards(mostCurrent._topic_et.getText());
RDebugUtils.currentLine=34799636;
 //BA.debugLineNum = 34799636;BA.debugLine="topic_panel.Visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34799637;
 //BA.debugLineNum = 34799637;BA.debugLine="End Sub";
return "";
}
public static String  _topic_cancel_click() throws Exception{
RDebugUtils.currentModule="subdeck_module";
if (Debug.shouldDelegate(mostCurrent.activityBA, "topic_cancel_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "topic_cancel_click", null));}
RDebugUtils.currentLine=34734080;
 //BA.debugLineNum = 34734080;BA.debugLine="Private Sub topic_cancel_Click";
RDebugUtils.currentLine=34734081;
 //BA.debugLineNum = 34734081;BA.debugLine="topic_panel.Visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34734082;
 //BA.debugLineNum = 34734082;BA.debugLine="topic_et.Text = \"\"";
mostCurrent._topic_et.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34734083;
 //BA.debugLineNum = 34734083;BA.debugLine="End Sub";
return "";
}
}