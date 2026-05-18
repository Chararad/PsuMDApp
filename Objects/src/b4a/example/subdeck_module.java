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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.subdeck_module");
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
public b4a.example.day_module _day_module = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.active_recall _active_recall = null;
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
public static String  _activerecall_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _chosendeck = null;
String _deckname = "";
anywheresoftware.b4a.objects.collections.List _flashacards = null;
 //BA.debugLineNum = 184;BA.debugLine="Private Sub activerecall_Click";
 //BA.debugLineNum = 187;BA.debugLine="number_of_cards = 0";
_number_of_cards = (int) (0);
 //BA.debugLineNum = 189;BA.debugLine="Dim chosendeck As Map = alldecks.Get(selecteddeck";
_chosendeck = new anywheresoftware.b4a.objects.collections.Map();
_chosendeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
 //BA.debugLineNum = 191;BA.debugLine="For Each deckName As String In chosendeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _chosendeck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.Get(index3));
 //BA.debugLineNum = 192;BA.debugLine="Dim flashacards As List = chosendeck.Get(deckNam";
_flashacards = new anywheresoftware.b4a.objects.collections.List();
_flashacards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_chosendeck.Get((Object)(_deckname))));
 //BA.debugLineNum = 193;BA.debugLine="number_of_cards = number_of_cards + flashacards.";
_number_of_cards = (int) (_number_of_cards+_flashacards.getSize());
 }
};
 //BA.debugLineNum = 196;BA.debugLine="AR_confirmationpanel.Visible = True";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 197;BA.debugLine="confirmlabel.Text = \"You got \" & number_of_cards";
mostCurrent._confirmlabel.setText(BA.ObjectToCharSequence("You got "+BA.NumberToString(_number_of_cards)+" cards"));
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 45;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 46;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 48;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 49;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 51;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark\"";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
 //BA.debugLineNum = 54;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout2\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout2",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 57;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark2";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
 //BA.debugLineNum = 60;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 61;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout3\")";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayout3",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 63;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark3";
mostCurrent._activity.LoadLayout("Subdeck_ModuleLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
 //BA.debugLineNum = 67;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 69;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
mostCurrent._lvsubdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else {
 //BA.debugLineNum = 71;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
mostCurrent._lvsubdecks.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 74;BA.debugLine="cc.Initialize(\"CC\")";
mostCurrent._cc.Initialize("CC");
 //BA.debugLineNum = 76;BA.debugLine="decknamelabel.Text = selecteddeck";
mostCurrent._decknamelabel.setText(BA.ObjectToCharSequence(mostCurrent._selecteddeck));
 //BA.debugLineNum = 78;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 79;BA.debugLine="addpanel2.Visible = False";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 80;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 81;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 82;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 83;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 85;BA.debugLine="Refresh";
_refresh();
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 105;BA.debugLine="If all_active_recall.praise = True Then";
if (mostCurrent._all_active_recall._praise /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 106;BA.debugLine="all_active_recall.praise = False";
mostCurrent._all_active_recall._praise /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 107;BA.debugLine="MsgboxAsync(\"You Finished Your Deck\", \"Congratul";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You Finished Your Deck"),BA.ObjectToCharSequence("Congratulations"),processBA);
 };
 //BA.debugLineNum = 109;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _addbtn_click() throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Private Sub Addbtn_Click";
 //BA.debugLineNum = 119;BA.debugLine="If addpanel2.Visible = False Then";
if (mostCurrent._addpanel2.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 120;BA.debugLine="addpanel2.Visible = True";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 122;BA.debugLine="addpanel2.Visible = False";
mostCurrent._addpanel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static String  _addcard_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
 //BA.debugLineNum = 127;BA.debugLine="Private Sub addcard_Click";
 //BA.debugLineNum = 129;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 130;BA.debugLine="If tappeddeck.Size = 0 Then";
if (_tappeddeck.getSize()==0) { 
 //BA.debugLineNum = 131;BA.debugLine="MsgboxAsync(\"Create A Sub-Deck first\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Create A Sub-Deck first"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 132;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 134;BA.debugLine="StartActivity(Add_card_module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._add_card_module.getObject()));
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _addsub_click() throws Exception{
 //BA.debugLineNum = 137;BA.debugLine="Private Sub addsub_Click";
 //BA.debugLineNum = 139;BA.debugLine="addpanel.Visible = True";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _ai_cards_click() throws Exception{
 //BA.debugLineNum = 288;BA.debugLine="Private Sub AI_cards_Click";
 //BA.debugLineNum = 289;BA.debugLine="If topic_panel.Visible = True Then";
if (mostCurrent._topic_panel.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 290;BA.debugLine="topic_panel.visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 291;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 293;BA.debugLine="topic_panel.Visible = True";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 296;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Private Sub backbtn_Click";
 //BA.debugLineNum = 222;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public static String  _cancel_click() throws Exception{
 //BA.debugLineNum = 142;BA.debugLine="Private Sub cancel_Click";
 //BA.debugLineNum = 144;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 145;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _cancelalter_click() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Private Sub cancelalter_Click";
 //BA.debugLineNum = 236;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public static String  _cancelconfirmation_click() throws Exception{
 //BA.debugLineNum = 209;BA.debugLine="Private Sub cancelconfirmation_Click";
 //BA.debugLineNum = 211;BA.debugLine="AR_confirmationpanel.Visible = False";
mostCurrent._ar_confirmationpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _canceldelete_click() throws Exception{
 //BA.debugLineNum = 283;BA.debugLine="Private Sub canceldelete_Click";
 //BA.debugLineNum = 284;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 285;BA.debugLine="End Sub";
return "";
}
public static String  _cancelrename_click() throws Exception{
 //BA.debugLineNum = 244;BA.debugLine="Private Sub cancelrename_Click";
 //BA.debugLineNum = 245;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 246;BA.debugLine="renameet.Text = \"\"";
mostCurrent._renameet.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static String  _confirmdelete_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
 //BA.debugLineNum = 275;BA.debugLine="Private Sub confirmdelete_Click";
 //BA.debugLineNum = 276;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 277;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
_tappeddeck.Remove((Object)(_selectedsubdeck));
 //BA.debugLineNum = 278;BA.debugLine="SaveDecks";
_savedecks();
 //BA.debugLineNum = 279;BA.debugLine="Refresh";
_refresh();
 //BA.debugLineNum = 280;BA.debugLine="deleteconfirmation.Visible = False";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 281;BA.debugLine="End Sub";
return "";
}
public static String  _confirmrename_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _getsubdeck = null;
String _names = "";
 //BA.debugLineNum = 249;BA.debugLine="Private Sub confirmrename_Click";
 //BA.debugLineNum = 250;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 252;BA.debugLine="If renameet.Text = \"\" Then";
if ((mostCurrent._renameet.getText()).equals("")) { 
 //BA.debugLineNum = 253;BA.debugLine="MsgboxAsync(\"New Name must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("New Name must have a name"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 254;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 258;BA.debugLine="Dim getsubdeck As List = tappeddeck.Get(selecteds";
_getsubdeck = new anywheresoftware.b4a.objects.collections.List();
_getsubdeck = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(_selectedsubdeck))));
 //BA.debugLineNum = 260;BA.debugLine="For Each names As String In tappeddeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group7 = _tappeddeck.Keys();
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.Get(index7));
 //BA.debugLineNum = 261;BA.debugLine="If renameet.Text = names Then";
if ((mostCurrent._renameet.getText()).equals(_names)) { 
 //BA.debugLineNum = 262;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 263;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 267;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
_tappeddeck.Remove((Object)(_selectedsubdeck));
 //BA.debugLineNum = 268;BA.debugLine="tappeddeck.Put(renameet.Text, getsubdeck)";
_tappeddeck.Put((Object)(mostCurrent._renameet.getText()),(Object)(_getsubdeck.getObject()));
 //BA.debugLineNum = 269;BA.debugLine="renameet.Text = \"\"";
mostCurrent._renameet.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 270;BA.debugLine="SaveDecks";
_savedecks();
 //BA.debugLineNum = 271;BA.debugLine="Refresh";
_refresh();
 //BA.debugLineNum = 272;BA.debugLine="renamepanel.Visible = False";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return "";
}
public static String  _create_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
Object _name = null;
 //BA.debugLineNum = 148;BA.debugLine="Private Sub create_Click";
 //BA.debugLineNum = 151;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
 //BA.debugLineNum = 152;BA.debugLine="Dim flashcards As List";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 154;BA.debugLine="flashcards.initialize";
_flashcards.Initialize();
 //BA.debugLineNum = 157;BA.debugLine="For Each name In tappeddeck.Keys";
{
final anywheresoftware.b4a.BA.IterableList group4 = _tappeddeck.Keys();
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_name = group4.Get(index4);
 //BA.debugLineNum = 158;BA.debugLine="If et1.Text = name Then";
if ((mostCurrent._et1.getText()).equals(BA.ObjectToString(_name))) { 
 //BA.debugLineNum = 159;BA.debugLine="MsgboxAsync(\"Sub-Deck Already Exist\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 160;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 165;BA.debugLine="If et1.Text = \"\" Then";
if ((mostCurrent._et1.getText()).equals("")) { 
 //BA.debugLineNum = 166;BA.debugLine="MsgboxAsync(\"Sub-Deck must have a name\", \"Error\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub-Deck must have a name"),BA.ObjectToCharSequence("Error"),processBA);
 }else {
 //BA.debugLineNum = 169;BA.debugLine="addpanel.Visible = False";
mostCurrent._addpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 170;BA.debugLine="LVSubdecks.AddSingleLine(et1.Text)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._et1.getText()));
 //BA.debugLineNum = 171;BA.debugLine="tappeddeck.Put(et1.Text, flashcards)";
_tappeddeck.Put((Object)(mostCurrent._et1.getText()),(Object)(_flashcards.getObject()));
 //BA.debugLineNum = 172;BA.debugLine="SaveDecks";
_savedecks();
 //BA.debugLineNum = 174;BA.debugLine="et1.Text = \"\"";
mostCurrent._et1.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _deletesubdeck_click() throws Exception{
 //BA.debugLineNum = 225;BA.debugLine="Private Sub deletesubdeck_Click";
 //BA.debugLineNum = 226;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 227;BA.debugLine="deleteconfirmation.Visible = True";
mostCurrent._deleteconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return "";
}
public static String  _generateflashcards(String _topic) throws Exception{
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
 //BA.debugLineNum = 326;BA.debugLine="Sub GenerateFlashCards(Topic As String)";
 //BA.debugLineNum = 328;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+mostCurrent._myapikey;
 //BA.debugLineNum = 330;BA.debugLine="Dim Job As HttpJob";
_job = new b4a.example.httpjob();
 //BA.debugLineNum = 331;BA.debugLine="Job.Initialize(\"Gemini\", Me)";
_job._initialize /*String*/ (processBA,"Gemini",subdeck_module.getObject());
 //BA.debugLineNum = 333;BA.debugLine="Dim prompt As String = _ 	\"Create flashcards from";
_prompt = "Create flashcards from the Topic below."+"RULES:"+"- Return ONLY valid JSON"+"- No explanations"+"- No markdown"+"- Must have 20+ Flashcards"+"- Format must exactly follow this structure:"+"{"+"\"flashcards\": ["+"{"+"\"question\": \"Question here\","+"\"answer\": \"Answer here\""+"}"+"]"+"}"+"Topic:"+_topic;
 //BA.debugLineNum = 351;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 352;BA.debugLine="root.Initialize";
_root.Initialize();
 //BA.debugLineNum = 354;BA.debugLine="Dim contents As List";
_contents = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 355;BA.debugLine="contents.Initialize";
_contents.Initialize();
 //BA.debugLineNum = 357;BA.debugLine="Dim contentItem As Map";
_contentitem = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 358;BA.debugLine="contentItem.Initialize";
_contentitem.Initialize();
 //BA.debugLineNum = 360;BA.debugLine="Dim parts As List";
_parts = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 361;BA.debugLine="parts.Initialize";
_parts.Initialize();
 //BA.debugLineNum = 363;BA.debugLine="Dim textPart As Map";
_textpart = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 364;BA.debugLine="textPart.Initialize";
_textpart.Initialize();
 //BA.debugLineNum = 366;BA.debugLine="textPart.Put(\"text\", prompt)";
_textpart.Put((Object)("text"),(Object)(_prompt));
 //BA.debugLineNum = 367;BA.debugLine="parts.Add(textPart)";
_parts.Add((Object)(_textpart.getObject()));
 //BA.debugLineNum = 369;BA.debugLine="contentItem.Put(\"parts\", parts)";
_contentitem.Put((Object)("parts"),(Object)(_parts.getObject()));
 //BA.debugLineNum = 370;BA.debugLine="contents.Add(contentItem)";
_contents.Add((Object)(_contentitem.getObject()));
 //BA.debugLineNum = 371;BA.debugLine="root.Put(\"contents\", contents)";
_root.Put((Object)("contents"),(Object)(_contents.getObject()));
 //BA.debugLineNum = 373;BA.debugLine="Dim gen As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 374;BA.debugLine="gen.Initialize(root)";
_gen.Initialize(_root);
 //BA.debugLineNum = 376;BA.debugLine="Dim json As String = gen.ToString";
_json = _gen.ToString();
 //BA.debugLineNum = 378;BA.debugLine="Log(\"REQUEST: \")";
anywheresoftware.b4a.keywords.Common.LogImpl("630867508","REQUEST: ",0);
 //BA.debugLineNum = 379;BA.debugLine="Log(json)";
anywheresoftware.b4a.keywords.Common.LogImpl("630867509",_json,0);
 //BA.debugLineNum = 381;BA.debugLine="Job.PostString(URL, json)";
_job._poststring /*String*/ (_url,_json);
 //BA.debugLineNum = 382;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetContentType("application/json");
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private Addbtn As Button";
mostCurrent._addbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private addpanel2 As Panel";
mostCurrent._addpanel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim alldecks As Map = FlashcardActivity.deck 'tak";
mostCurrent._alldecks = new anywheresoftware.b4a.objects.collections.Map();
mostCurrent._alldecks = mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ ;
 //BA.debugLineNum = 22;BA.debugLine="Dim selecteddeck As String = FlashcardActivity.se";
mostCurrent._selecteddeck = mostCurrent._flashcardactivity._selecteddeck /*String*/ ;
 //BA.debugLineNum = 23;BA.debugLine="Private LVSubdecks As ListView";
mostCurrent._lvsubdecks = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private decknamelabel As Label";
mostCurrent._decknamelabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private addpanel As Panel";
mostCurrent._addpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private et1 As EditText";
mostCurrent._et1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private AR_confirmationpanel As Panel";
mostCurrent._ar_confirmationpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private confirmlabel As Label";
mostCurrent._confirmlabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim number_of_cards As Int = 0 'to count the numb";
_number_of_cards = (int) (0);
 //BA.debugLineNum = 30;BA.debugLine="Private alterpanel As Panel";
mostCurrent._alterpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private renamepanel As Panel";
mostCurrent._renamepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private renameet As EditText";
mostCurrent._renameet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private deleteconfirmation As Panel";
mostCurrent._deleteconfirmation = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private topic_et As EditText";
mostCurrent._topic_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private topic_panel As Panel";
mostCurrent._topic_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim j As JSON";
mostCurrent._j = new anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter();
 //BA.debugLineNum = 37;BA.debugLine="Private cc As ContentChooser";
mostCurrent._cc = new anywheresoftware.b4a.phone.Phone.ContentChooser();
 //BA.debugLineNum = 38;BA.debugLine="Private pickPDFBtn As Button";
mostCurrent._pickpdfbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim api1 As String = \"AIzaSyAGccTYG-Mscl_16Z72t\"";
mostCurrent._api1 = "AIzaSyAGccTYG-Mscl_16Z72t";
 //BA.debugLineNum = 40;BA.debugLine="Dim api2 As String = \"-GIN9ITMdrDGhQ\"";
mostCurrent._api2 = "-GIN9ITMdrDGhQ";
 //BA.debugLineNum = 41;BA.debugLine="Dim MyAPIKey As String = api1&api2";
mostCurrent._myapikey = mostCurrent._api1+mostCurrent._api2;
 //BA.debugLineNum = 42;BA.debugLine="Dim AIGlobalText As String";
mostCurrent._aiglobaltext = "";
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _goback_click() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Private Sub goback_Click";
 //BA.debugLineNum = 181;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4a.example.httpjob _job) throws Exception{
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
 //BA.debugLineNum = 387;BA.debugLine="Sub JobDone (job As HttpJob)";
 //BA.debugLineNum = 388;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 389;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 391;BA.debugLine="Dim response As String = job.GetString";
_response = _job._getstring /*String*/ ();
 //BA.debugLineNum = 392;BA.debugLine="Log(response)";
anywheresoftware.b4a.keywords.Common.LogImpl("630932997",_response,0);
 //BA.debugLineNum = 394;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 395;BA.debugLine="jp.Initialize(response)";
_jp.Initialize(_response);
 //BA.debugLineNum = 397;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
 //BA.debugLineNum = 398;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
_candidates = new anywheresoftware.b4a.objects.collections.List();
_candidates = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("candidates"))));
 //BA.debugLineNum = 399;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
_candidate = new anywheresoftware.b4a.objects.collections.Map();
_candidate = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidates.Get((int) (0))));
 //BA.debugLineNum = 400;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
_content = new anywheresoftware.b4a.objects.collections.Map();
_content = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidate.Get((Object)("content"))));
 //BA.debugLineNum = 401;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
_parts = new anywheresoftware.b4a.objects.collections.List();
_parts = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_content.Get((Object)("parts"))));
 //BA.debugLineNum = 402;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
_firstpart = new anywheresoftware.b4a.objects.collections.Map();
_firstpart = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_parts.Get((int) (0))));
 //BA.debugLineNum = 404;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
_aitext = BA.ObjectToString(_firstpart.Get((Object)("text")));
 //BA.debugLineNum = 405;BA.debugLine="AIGlobalText = aiText";
mostCurrent._aiglobaltext = _aitext;
 //BA.debugLineNum = 407;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"cached_ai.txt",_aitext);
 //BA.debugLineNum = 409;BA.debugLine="Dim flashcards As List = ParseFlashcard(aiText)";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = _parseflashcard(_aitext);
 //BA.debugLineNum = 411;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.G";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 413;BA.debugLine="LVSubdecks.AddSingleLine(topic_et.Text)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(mostCurrent._topic_et.getText()));
 //BA.debugLineNum = 414;BA.debugLine="tappeddeck.Put(topic_et.Text, flashcards)";
_tappeddeck.Put((Object)(mostCurrent._topic_et.getText()),(Object)(_flashcards.getObject()));
 //BA.debugLineNum = 415;BA.debugLine="topic_et.Text = \"\"";
mostCurrent._topic_et.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 416;BA.debugLine="SaveDecks";
_savedecks();
 }else {
 //BA.debugLineNum = 419;BA.debugLine="Log(job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("630933024",_job._errormessage /*String*/ ,0);
 //BA.debugLineNum = 420;BA.debugLine="Msgbox(\"Error making your AI Flashcards\", \"Error";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error making your AI Flashcards"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 423;BA.debugLine="job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 424;BA.debugLine="End Sub";
return "";
}
public static String  _lvsubdecks_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 214;BA.debugLine="Private Sub LVSubdecks_ItemClick (Position As Int,";
 //BA.debugLineNum = 216;BA.debugLine="selectedsubdeck = Value 'gives the chsoen subdeck";
_selectedsubdeck = BA.ObjectToString(_value);
 //BA.debugLineNum = 218;BA.debugLine="StartActivity(Card_Module)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._card_module.getObject()));
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _lvsubdecks_itemlongclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 239;BA.debugLine="Private Sub LVSubdecks_ItemLongClick (Position As";
 //BA.debugLineNum = 240;BA.debugLine="alterpanel.Visible = True";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 241;BA.debugLine="selectedsubdeck = Value";
_selectedsubdeck = BA.ObjectToString(_value);
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _parseflashcard(String _jsontext) throws Exception{
anywheresoftware.b4a.objects.collections.List _flashcard = null;
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _flashcards = null;
anywheresoftware.b4a.objects.collections.Map _card = null;
anywheresoftware.b4a.objects.collections.Map _cards = null;
String _question = "";
String _answer = "";
 //BA.debugLineNum = 427;BA.debugLine="Sub ParseFlashcard (jsonText As String) As List";
 //BA.debugLineNum = 429;BA.debugLine="Dim flashcard As List";
_flashcard = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 430;BA.debugLine="flashcard.Initialize";
_flashcard.Initialize();
 //BA.debugLineNum = 431;BA.debugLine="Try";
try { //BA.debugLineNum = 433;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 434;BA.debugLine="jp.Initialize(jsonText)";
_jp.Initialize(_jsontext);
 //BA.debugLineNum = 436;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
 //BA.debugLineNum = 438;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
_flashcards = new anywheresoftware.b4a.objects.collections.List();
_flashcards = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("flashcards"))));
 //BA.debugLineNum = 440;BA.debugLine="For Each card As Map In flashcards";
_card = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _flashcards;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group8.Get(index8)));
 //BA.debugLineNum = 442;BA.debugLine="Dim cards As Map";
_cards = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 443;BA.debugLine="cards.initialize";
_cards.Initialize();
 //BA.debugLineNum = 445;BA.debugLine="Dim question As String = card.Get(\"question\")";
_question = BA.ObjectToString(_card.Get((Object)("question")));
 //BA.debugLineNum = 447;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
_answer = BA.ObjectToString(_card.Get((Object)("answer")));
 //BA.debugLineNum = 449;BA.debugLine="cards.Put(\"Q\", question)";
_cards.Put((Object)("Q"),(Object)(_question));
 //BA.debugLineNum = 450;BA.debugLine="cards.Put(\"A\", answer)";
_cards.Put((Object)("A"),(Object)(_answer));
 //BA.debugLineNum = 451;BA.debugLine="flashcard.Add(cards)";
_flashcard.Add((Object)(_cards.getObject()));
 //BA.debugLineNum = 453;BA.debugLine="Log(\"===================\")";
anywheresoftware.b4a.keywords.Common.LogImpl("630998554","===================",0);
 //BA.debugLineNum = 454;BA.debugLine="Log(\"QUESTION: \" & question)";
anywheresoftware.b4a.keywords.Common.LogImpl("630998555","QUESTION: "+_question,0);
 //BA.debugLineNum = 455;BA.debugLine="Log(\"ANSWER: \" & answer)";
anywheresoftware.b4a.keywords.Common.LogImpl("630998556","ANSWER: "+_answer,0);
 }
};
 } 
       catch (Exception e21) {
			processBA.setLastException(e21); //BA.debugLineNum = 461;BA.debugLine="Log(\"INVALID JSON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("630998562","INVALID JSON",0);
 };
 //BA.debugLineNum = 466;BA.debugLine="Return flashcard";
if (true) return _flashcard;
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim selectedsubdeck As String 'chosen subdeck";
_selectedsubdeck = "";
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _refresh() throws Exception{
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
String _deckname = "";
 //BA.debugLineNum = 93;BA.debugLine="Sub Refresh";
 //BA.debugLineNum = 94;BA.debugLine="LVSubdecks.clear";
mostCurrent._lvsubdecks.Clear();
 //BA.debugLineNum = 96;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._alldecks.Get((Object)(mostCurrent._selecteddeck))));
 //BA.debugLineNum = 99;BA.debugLine="For Each deckName As String In tappeddeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _tappeddeck.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.Get(index3));
 //BA.debugLineNum = 100;BA.debugLine="LVSubdecks.AddSingleLine(deckName)";
mostCurrent._lvsubdecks.AddSingleLine(BA.ObjectToCharSequence(_deckname));
 }
};
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _renamesubdeck_click() throws Exception{
 //BA.debugLineNum = 230;BA.debugLine="Private Sub renamesubdeck_Click";
 //BA.debugLineNum = 231;BA.debugLine="alterpanel.Visible = False";
mostCurrent._alterpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 232;BA.debugLine="renamepanel.visible = True";
mostCurrent._renamepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _savedecks() throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Sub SaveDecks";
 //BA.debugLineNum = 90;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
mostCurrent._flashcardactivity._kvs /*b4a.example3.keyvaluestore*/ ._put("deck_data",(Object)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .getObject()));
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _startarbtn_click() throws Exception{
 //BA.debugLineNum = 200;BA.debugLine="Private Sub startArbtn_Click";
 //BA.debugLineNum = 202;BA.debugLine="If number_of_cards = 0 Then";
if (_number_of_cards==0) { 
 //BA.debugLineNum = 203;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No cards available"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 204;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 206;BA.debugLine="StartActivity(all_active_recall)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._all_active_recall.getObject()));
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public static String  _topic_btn_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _getsubdeck = null;
anywheresoftware.b4a.objects.collections.Map _tappeddeck = null;
String _names = "";
 //BA.debugLineNum = 303;BA.debugLine="Private Sub topic_btn_Click";
 //BA.debugLineNum = 304;BA.debugLine="If topic_et.Text = \"\" Then";
if ((mostCurrent._topic_et.getText()).equals("")) { 
 //BA.debugLineNum = 305;BA.debugLine="Msgbox(\"Invalid Subdeck Topic\", \"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Invalid Subdeck Topic"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 //BA.debugLineNum = 306;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 309;BA.debugLine="Dim getsubdeck As List";
_getsubdeck = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 310;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
_tappeddeck = new anywheresoftware.b4a.objects.collections.Map();
_tappeddeck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(mostCurrent._flashcardactivity._deck /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)(mostCurrent._flashcardactivity._selecteddeck /*String*/ ))));
 //BA.debugLineNum = 311;BA.debugLine="For Each names As String In tappeddeck.keys";
{
final anywheresoftware.b4a.BA.IterableList group7 = _tappeddeck.Keys();
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.Get(index7));
 //BA.debugLineNum = 312;BA.debugLine="getsubdeck = tappeddeck.Get(selectedsubdeck)";
_getsubdeck = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tappeddeck.Get((Object)(_selectedsubdeck))));
 //BA.debugLineNum = 313;BA.debugLine="If topic_et.Text = names Then";
if ((mostCurrent._topic_et.getText()).equals(_names)) { 
 //BA.debugLineNum = 314;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Sub Deck Name Already Exist"),BA.ObjectToCharSequence("Error"),processBA);
 //BA.debugLineNum = 315;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 319;BA.debugLine="ProgressDialogShow(\"Generating Flashcards...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Generating Flashcards..."));
 //BA.debugLineNum = 321;BA.debugLine="GenerateFlashCards(topic_et.Text)";
_generateflashcards(mostCurrent._topic_et.getText());
 //BA.debugLineNum = 323;BA.debugLine="topic_panel.Visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 324;BA.debugLine="End Sub";
return "";
}
public static String  _topic_cancel_click() throws Exception{
 //BA.debugLineNum = 298;BA.debugLine="Private Sub topic_cancel_Click";
 //BA.debugLineNum = 299;BA.debugLine="topic_panel.Visible = False";
mostCurrent._topic_panel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 300;BA.debugLine="topic_et.Text = \"\"";
mostCurrent._topic_et.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 301;BA.debugLine="End Sub";
return "";
}
}
