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

public class todoactivity extends Activity implements B4AActivity{
	public static todoactivity mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.todoactivity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (todoactivity).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.todoactivity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.todoactivity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (todoactivity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (todoactivity) Resume **");
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
		return todoactivity.class;
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
            BA.LogInfo("** Activity (todoactivity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (todoactivity) Pause event (activity is not paused). **");
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
            todoactivity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (todoactivity) Resume **");
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
public static b4a.example3.keyvaluestore _kvs = null;
public anywheresoftware.b4a.objects.EditTextWrapper _addtitletextarea = null;
public b4a.example3.customlistview _listslist = null;
public anywheresoftware.b4a.objects.ButtonWrapper _newlistbtn = null;
public b4a.example3.customlistview _taskslist = null;
public static boolean _isaddinglist = false;
public anywheresoftware.b4a.objects.PanelWrapper _addtaskbtnpnl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _addtaskbtn = null;
public static String _currentlist = "";
public anywheresoftware.b4a.objects.PanelWrapper _addtaskpanel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _addtasktextarea = null;
public anywheresoftware.b4a.objects.ButtonWrapper _entertaskbtn = null;
public static int _untitledno = 0;
public anywheresoftware.b4a.objects.LabelWrapper _progressnumber = null;
public anywheresoftware.b4a.objects.LabelWrapper _progresspercent = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _progressbar = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _pixeltf = null;
public anywheresoftware.b4a.objects.TabHostWrapper _tabhost1 = null;
public b4a.example3.customlistview _grouplist = null;
public anywheresoftware.b4a.objects.EditTextWrapper _addtitletextareagrp = null;
public b4a.example3.customlistview _listslistgrp = null;
public anywheresoftware.b4a.objects.ButtonWrapper _newgroupbtn = null;
public anywheresoftware.b4a.objects.ButtonWrapper _newlistbtngrp = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _progressbargrp = null;
public anywheresoftware.b4a.objects.LabelWrapper _progressnumbergrp = null;
public anywheresoftware.b4a.objects.LabelWrapper _progresspercentgrp = null;
public b4a.example3.customlistview _taskslistgrp = null;
public anywheresoftware.b4a.objects.EditTextWrapper _groupet = null;
public static String _currentgrplistname = "";
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
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
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _title = "";
RDebugUtils.currentLine=3407872;
 //BA.debugLineNum = 3407872;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=3407873;
 //BA.debugLineNum = 3407873;BA.debugLine="Activity.LoadLayout(\"MAINtodolistlayout\")";
mostCurrent._activity.LoadLayout("MAINtodolistlayout",mostCurrent.activityBA);
RDebugUtils.currentLine=3407875;
 //BA.debugLineNum = 3407875;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=3407877;
 //BA.debugLineNum = 3407877;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=3407878;
 //BA.debugLineNum = 3407878;BA.debugLine="TabHost1.AddTab(\"Your Lists\", \"todolistLayout.";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"Your Lists","todolistLayout.bal");
RDebugUtils.currentLine=3407879;
 //BA.debugLineNum = 3407879;BA.debugLine="TabHost1.AddTab(\"Groups\", \"grouptodolistlayout";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"Groups","grouptodolistlayout.bal");
 }else {
RDebugUtils.currentLine=3407881;
 //BA.debugLineNum = 3407881;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark\")";
mostCurrent._activity.LoadLayout("todoListLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=3407884;
 //BA.debugLineNum = 3407884;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=3407885;
 //BA.debugLineNum = 3407885;BA.debugLine="Activity.LoadLayout(\"todoListLayout2\")";
mostCurrent._activity.LoadLayout("todoListLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=3407887;
 //BA.debugLineNum = 3407887;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark2\")";
mostCurrent._activity.LoadLayout("todoListLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=3407890;
 //BA.debugLineNum = 3407890;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=3407891;
 //BA.debugLineNum = 3407891;BA.debugLine="Activity.LoadLayout(\"todoListLayout3\")";
mostCurrent._activity.LoadLayout("todoListLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=3407893;
 //BA.debugLineNum = 3407893;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark3\")";
mostCurrent._activity.LoadLayout("todoListLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=3407897;
 //BA.debugLineNum = 3407897;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=3407898;
 //BA.debugLineNum = 3407898;BA.debugLine="addTitleTextArea.Background = Null";
mostCurrent._addtitletextarea.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=3407900;
 //BA.debugLineNum = 3407900;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=3407901;
 //BA.debugLineNum = 3407901;BA.debugLine="tasksList.GetBase.Visible = False";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3407903;
 //BA.debugLineNum = 3407903;BA.debugLine="kvs = Starter.taskKvs";
_kvs = mostCurrent._starter._taskkvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=3407905;
 //BA.debugLineNum = 3407905;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=3407906;
 //BA.debugLineNum = 3407906;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=3407907;
 //BA.debugLineNum = 3407907;BA.debugLine="For Each title As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group30 = _savedlists;
final int groupLen30 = group30.getSize()
;int index30 = 0;
;
for (; index30 < groupLen30;index30++){
_title = BA.ObjectToString(group30.Get(index30));
RDebugUtils.currentLine=3407908;
 //BA.debugLineNum = 3407908;BA.debugLine="listsList.AddTextItem(title, title)";
mostCurrent._listslist._addtextitem((Object)(_title),(Object)(_title));
 }
};
 };
RDebugUtils.currentLine=3407912;
 //BA.debugLineNum = 3407912;BA.debugLine="loadMyGroups";
_loadmygroups();
RDebugUtils.currentLine=3407914;
 //BA.debugLineNum = 3407914;BA.debugLine="End Sub";
return "";
}
public static String  _newaddtaskbtn() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newaddtaskbtn", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newaddtaskbtn", null));}
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=3604480;
 //BA.debugLineNum = 3604480;BA.debugLine="Sub newAddTaskBtn";
RDebugUtils.currentLine=3604481;
 //BA.debugLineNum = 3604481;BA.debugLine="addTaskBtnPNL.Initialize(\"addTaskBtnPNL\")";
mostCurrent._addtaskbtnpnl.Initialize(mostCurrent.activityBA,"addTaskBtnPNL");
RDebugUtils.currentLine=3604482;
 //BA.debugLineNum = 3604482;BA.debugLine="addTaskBtnPNL.SetLayout(10dip, 0dip, 190dip, 70di";
mostCurrent._addtaskbtnpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=3604483;
 //BA.debugLineNum = 3604483;BA.debugLine="addTaskBtnPNL.Color = Colors.ARGB(0, 255, 255, 25";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=3604484;
 //BA.debugLineNum = 3604484;BA.debugLine="addTaskBtn.Initialize(\"addTaskBtn\")";
mostCurrent._addtaskbtn.Initialize(mostCurrent.activityBA,"addTaskBtn");
RDebugUtils.currentLine=3604485;
 //BA.debugLineNum = 3604485;BA.debugLine="addTaskBtn.Text = \"+ add a task \"";
mostCurrent._addtaskbtn.setText(BA.ObjectToCharSequence("+ add a task "));
RDebugUtils.currentLine=3604486;
 //BA.debugLineNum = 3604486;BA.debugLine="addTaskBtnPNL.AddView(addTaskBtn, 10dip, 20dip, a";
mostCurrent._addtaskbtnpnl.AddView((android.view.View)(mostCurrent._addtaskbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=3604487;
 //BA.debugLineNum = 3604487;BA.debugLine="tasksList.Add(addTaskBtnPNL, \"\")";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._addtaskbtnpnl.getObject())),(Object)(""));
RDebugUtils.currentLine=3604489;
 //BA.debugLineNum = 3604489;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=3604490;
 //BA.debugLineNum = 3604490;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=3604492;
 //BA.debugLineNum = 3604492;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=3604493;
 //BA.debugLineNum = 3604493;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=3604494;
 //BA.debugLineNum = 3604494;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=3604495;
 //BA.debugLineNum = 3604495;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3604496;
 //BA.debugLineNum = 3604496;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=3604498;
 //BA.debugLineNum = 3604498;BA.debugLine="addTaskBtnPNL.Color = Colors.Transparent";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=3604499;
 //BA.debugLineNum = 3604499;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=3604500;
 //BA.debugLineNum = 3604500;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3604501;
 //BA.debugLineNum = 3604501;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=3604504;
 //BA.debugLineNum = 3604504;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=3604505;
 //BA.debugLineNum = 3604505;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=3604506;
 //BA.debugLineNum = 3604506;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=3604507;
 //BA.debugLineNum = 3604507;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3604508;
 //BA.debugLineNum = 3604508;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=3604510;
 //BA.debugLineNum = 3604510;BA.debugLine="addTaskBtnPNL.Color = Colors.Transparent";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=3604511;
 //BA.debugLineNum = 3604511;BA.debugLine="cd.Initialize(Colors.ARGB(120, 255, 255, 255),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (255),(int) (255),(int) (255)),(int) (200));
RDebugUtils.currentLine=3604512;
 //BA.debugLineNum = 3604512;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3604513;
 //BA.debugLineNum = 3604513;BA.debugLine="addTaskBtn.TextColor = Colors.Black";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
case 2: {
RDebugUtils.currentLine=3604516;
 //BA.debugLineNum = 3604516;BA.debugLine="addTaskBtn.Typeface = pixeltf";
mostCurrent._addtaskbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=3604517;
 //BA.debugLineNum = 3604517;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=3604518;
 //BA.debugLineNum = 3604518;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=3604519;
 //BA.debugLineNum = 3604519;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=3604520;
 //BA.debugLineNum = 3604520;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3604521;
 //BA.debugLineNum = 3604521;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=3604523;
 //BA.debugLineNum = 3604523;BA.debugLine="addTaskBtnPNL.Color = Colors.ARGB(0, 232, 213,";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (232),(int) (213),(int) (179)));
RDebugUtils.currentLine=3604524;
 //BA.debugLineNum = 3604524;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=3604525;
 //BA.debugLineNum = 3604525;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3604526;
 //BA.debugLineNum = 3604526;BA.debugLine="addTaskBtn.TextColor = Colors.Black";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
}
;
RDebugUtils.currentLine=3604530;
 //BA.debugLineNum = 3604530;BA.debugLine="End Sub";
return "";
}
public static String  _loadmygroups() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadmygroups", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadmygroups", null));}
anywheresoftware.b4a.objects.collections.List _allgroups = null;
String _code = "";
String _memberskey = "";
anywheresoftware.b4a.objects.collections.List _members = null;
String _myid = "";
String _m = "";
String _groupname = "";
RDebugUtils.currentLine=33947648;
 //BA.debugLineNum = 33947648;BA.debugLine="Sub loadMyGroups";
RDebugUtils.currentLine=33947649;
 //BA.debugLineNum = 33947649;BA.debugLine="groupList.Clear";
mostCurrent._grouplist._clear();
RDebugUtils.currentLine=33947650;
 //BA.debugLineNum = 33947650;BA.debugLine="groupET.Text = \"\"";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=33947651;
 //BA.debugLineNum = 33947651;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33947652;
 //BA.debugLineNum = 33947652;BA.debugLine="listsListGrp.Clear";
mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=33947653;
 //BA.debugLineNum = 33947653;BA.debugLine="listsListGrp.GetBase.Visible = False";
mostCurrent._listslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33947654;
 //BA.debugLineNum = 33947654;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=33947655;
 //BA.debugLineNum = 33947655;BA.debugLine="tasksListGrp.GetBase.Visible = False";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=33947656;
 //BA.debugLineNum = 33947656;BA.debugLine="progressBarGrp.Progress = 0";
mostCurrent._progressbargrp.setProgress((int) (0));
RDebugUtils.currentLine=33947657;
 //BA.debugLineNum = 33947657;BA.debugLine="progressNumberGrp.Text = \"\"";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=33947658;
 //BA.debugLineNum = 33947658;BA.debugLine="progressPercentGrp.Text = \"\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=33947660;
 //BA.debugLineNum = 33947660;BA.debugLine="If kvs.ContainsKey(\"groups\") = False Then Return";
if (_kvs._containskey("groups")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=33947662;
 //BA.debugLineNum = 33947662;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));
RDebugUtils.currentLine=33947663;
 //BA.debugLineNum = 33947663;BA.debugLine="For Each code As String In allGroups";
{
final anywheresoftware.b4a.BA.IterableList group13 = _allgroups;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_code = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=33947664;
 //BA.debugLineNum = 33947664;BA.debugLine="Dim membersKey As String = \"group_members_\" & co";
_memberskey = "group_members_"+_code;
RDebugUtils.currentLine=33947665;
 //BA.debugLineNum = 33947665;BA.debugLine="If kvs.ContainsKey(membersKey) Then";
if (_kvs._containskey(_memberskey)) { 
RDebugUtils.currentLine=33947666;
 //BA.debugLineNum = 33947666;BA.debugLine="Dim members As List = kvs.Get(membersKey)";
_members = new anywheresoftware.b4a.objects.collections.List();
_members = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_memberskey)));
RDebugUtils.currentLine=33947667;
 //BA.debugLineNum = 33947667;BA.debugLine="Dim myID As String = Starter.currentUserID ' ho";
_myid = mostCurrent._starter._currentuserid /*String*/ ;
RDebugUtils.currentLine=33947668;
 //BA.debugLineNum = 33947668;BA.debugLine="For Each m As String In members";
{
final anywheresoftware.b4a.BA.IterableList group18 = _members;
final int groupLen18 = group18.getSize()
;int index18 = 0;
;
for (; index18 < groupLen18;index18++){
_m = BA.ObjectToString(group18.Get(index18));
RDebugUtils.currentLine=33947669;
 //BA.debugLineNum = 33947669;BA.debugLine="If m = myID Then";
if ((_m).equals(_myid)) { 
RDebugUtils.currentLine=33947670;
 //BA.debugLineNum = 33947670;BA.debugLine="Dim groupName As String = kvs.Get(\"group_name";
_groupname = BA.ObjectToString(_kvs._get("group_name_"+_code));
RDebugUtils.currentLine=33947671;
 //BA.debugLineNum = 33947671;BA.debugLine="groupList.AddTextItem(groupName, code)";
mostCurrent._grouplist._addtextitem((Object)(_groupname),(Object)(_code));
RDebugUtils.currentLine=33947672;
 //BA.debugLineNum = 33947672;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 }
};
RDebugUtils.currentLine=33947677;
 //BA.debugLineNum = 33947677;BA.debugLine="End Sub";
return "";
}
public static String  _addtaskbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtaskbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtaskbtn_click", null));}
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=3997696;
 //BA.debugLineNum = 3997696;BA.debugLine="Sub addTaskBtn_Click";
RDebugUtils.currentLine=3997698;
 //BA.debugLineNum = 3997698;BA.debugLine="addTaskBtn.Enabled = False";
mostCurrent._addtaskbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997699;
 //BA.debugLineNum = 3997699;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1)";
mostCurrent._taskslist._removeat((int) (mostCurrent._taskslist._getsize()-1));
RDebugUtils.currentLine=3997701;
 //BA.debugLineNum = 3997701;BA.debugLine="addTaskPanel.Initialize(\"addTaskPanel\")";
mostCurrent._addtaskpanel.Initialize(mostCurrent.activityBA,"addTaskPanel");
RDebugUtils.currentLine=3997702;
 //BA.debugLineNum = 3997702;BA.debugLine="addTaskPanel.SetLayout(10dip, 0, 20dip, 120dip)";
mostCurrent._addtaskpanel.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=3997703;
 //BA.debugLineNum = 3997703;BA.debugLine="addTaskPanel.Color = Colors.ARGB(0, 255, 255, 255";
mostCurrent._addtaskpanel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=3997705;
 //BA.debugLineNum = 3997705;BA.debugLine="addTaskTextArea.Initialize(\"addTodoText\")";
mostCurrent._addtasktextarea.Initialize(mostCurrent.activityBA,"addTodoText");
RDebugUtils.currentLine=3997706;
 //BA.debugLineNum = 3997706;BA.debugLine="addTaskTextArea.Hint = \"Add a task...\"";
mostCurrent._addtasktextarea.setHint("Add a task...");
RDebugUtils.currentLine=3997707;
 //BA.debugLineNum = 3997707;BA.debugLine="addTaskTextArea.Tag = Null";
mostCurrent._addtasktextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=3997709;
 //BA.debugLineNum = 3997709;BA.debugLine="enterTaskBtn.Initialize(\"enterTaskBtn\")";
mostCurrent._entertaskbtn.Initialize(mostCurrent.activityBA,"enterTaskBtn");
RDebugUtils.currentLine=3997710;
 //BA.debugLineNum = 3997710;BA.debugLine="enterTaskBtn.Text = \"Enter task\"";
mostCurrent._entertaskbtn.setText(BA.ObjectToCharSequence("Enter task"));
RDebugUtils.currentLine=3997712;
 //BA.debugLineNum = 3997712;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=3997713;
 //BA.debugLineNum = 3997713;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=3997715;
 //BA.debugLineNum = 3997715;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=3997716;
 //BA.debugLineNum = 3997716;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=3997717;
 //BA.debugLineNum = 3997717;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=3997718;
 //BA.debugLineNum = 3997718;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=3997719;
 //BA.debugLineNum = 3997719;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3997720;
 //BA.debugLineNum = 3997720;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=3997722;
 //BA.debugLineNum = 3997722;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(120, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)));
RDebugUtils.currentLine=3997723;
 //BA.debugLineNum = 3997723;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=3997724;
 //BA.debugLineNum = 3997724;BA.debugLine="cd.Initialize(Colors.ARGB(120, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=3997725;
 //BA.debugLineNum = 3997725;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3997726;
 //BA.debugLineNum = 3997726;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=3997729;
 //BA.debugLineNum = 3997729;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=3997730;
 //BA.debugLineNum = 3997730;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=3997731;
 //BA.debugLineNum = 3997731;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=3997732;
 //BA.debugLineNum = 3997732;BA.debugLine="cd.Initialize(Colors.ARGB(100, 255, 255, 255),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (255),(int) (255),(int) (255)),(int) (200));
RDebugUtils.currentLine=3997733;
 //BA.debugLineNum = 3997733;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3997734;
 //BA.debugLineNum = 3997734;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=3997736;
 //BA.debugLineNum = 3997736;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(255, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (137),(int) (162),(int) (185)));
RDebugUtils.currentLine=3997737;
 //BA.debugLineNum = 3997737;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=3997738;
 //BA.debugLineNum = 3997738;BA.debugLine="cd.Initialize(Colors.ARGB(255, 137, 162, 185),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (137),(int) (162),(int) (185)),(int) (200));
RDebugUtils.currentLine=3997739;
 //BA.debugLineNum = 3997739;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3997740;
 //BA.debugLineNum = 3997740;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=3997743;
 //BA.debugLineNum = 3997743;BA.debugLine="addTaskTextArea.Typeface = pixeltf";
mostCurrent._addtasktextarea.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=3997744;
 //BA.debugLineNum = 3997744;BA.debugLine="enterTaskBtn.Typeface = pixeltf";
mostCurrent._entertaskbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=3997745;
 //BA.debugLineNum = 3997745;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=3997746;
 //BA.debugLineNum = 3997746;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=3997747;
 //BA.debugLineNum = 3997747;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=3997748;
 //BA.debugLineNum = 3997748;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=3997749;
 //BA.debugLineNum = 3997749;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3997750;
 //BA.debugLineNum = 3997750;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=3997752;
 //BA.debugLineNum = 3997752;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=3997753;
 //BA.debugLineNum = 3997753;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=3997754;
 //BA.debugLineNum = 3997754;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=3997755;
 //BA.debugLineNum = 3997755;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=3997756;
 //BA.debugLineNum = 3997756;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
}
;
RDebugUtils.currentLine=3997760;
 //BA.debugLineNum = 3997760;BA.debugLine="addTaskPanel.AddView(addTaskTextArea, 0, 0, addTa";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._addtasktextarea.getObject()),(int) (0),(int) (0),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=3997761;
 //BA.debugLineNum = 3997761;BA.debugLine="addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addT";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._entertaskbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=3997763;
 //BA.debugLineNum = 3997763;BA.debugLine="tasksList.Add(addTaskPanel, addTaskPanel)";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._addtaskpanel.getObject())),(Object)(mostCurrent._addtaskpanel.getObject()));
RDebugUtils.currentLine=3997765;
 //BA.debugLineNum = 3997765;BA.debugLine="End Sub";
return "";
}
public static String  _addtaskbtngrp_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtaskbtngrp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtaskbtngrp_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _addbtn = null;
anywheresoftware.b4a.objects.collections.List _ctx = null;
String _code = "";
String _currentgrplist = "";
anywheresoftware.b4a.objects.PanelWrapper _addpnl = null;
anywheresoftware.b4a.objects.EditTextWrapper _addet = null;
anywheresoftware.b4a.objects.ButtonWrapper _confirmbtn = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.collections.List _ctx2 = null;
RDebugUtils.currentLine=35192832;
 //BA.debugLineNum = 35192832;BA.debugLine="Sub addTaskBtnGrp_Click";
RDebugUtils.currentLine=35192833;
 //BA.debugLineNum = 35192833;BA.debugLine="Dim addBtn As Button = Sender";
_addbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
_addbtn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=35192834;
 //BA.debugLineNum = 35192834;BA.debugLine="Dim ctx As List = addBtn.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_addbtn.getTag()));
RDebugUtils.currentLine=35192835;
 //BA.debugLineNum = 35192835;BA.debugLine="Dim code As String = ctx.Get(0)";
_code = BA.ObjectToString(_ctx.Get((int) (0)));
RDebugUtils.currentLine=35192836;
 //BA.debugLineNum = 35192836;BA.debugLine="Dim currentGrpList As String = ctx.Get(1)";
_currentgrplist = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=35192838;
 //BA.debugLineNum = 35192838;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
mostCurrent._taskslistgrp._removeat((int) (mostCurrent._taskslistgrp._getsize()-1));
RDebugUtils.currentLine=35192840;
 //BA.debugLineNum = 35192840;BA.debugLine="Dim addPNL As Panel";
_addpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=35192841;
 //BA.debugLineNum = 35192841;BA.debugLine="addPNL.Initialize(\"addTaskPNLGrp\")";
_addpnl.Initialize(mostCurrent.activityBA,"addTaskPNLGrp");
RDebugUtils.currentLine=35192842;
 //BA.debugLineNum = 35192842;BA.debugLine="addPNL.SetLayout(10dip, 0, 20dip, 120dip)";
_addpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=35192843;
 //BA.debugLineNum = 35192843;BA.debugLine="addPNL.Color = Colors.Transparent";
_addpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=35192845;
 //BA.debugLineNum = 35192845;BA.debugLine="Dim addET As EditText";
_addet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=35192846;
 //BA.debugLineNum = 35192846;BA.debugLine="addET.Initialize(\"addTaskETGrp\")";
_addet.Initialize(mostCurrent.activityBA,"addTaskETGrp");
RDebugUtils.currentLine=35192847;
 //BA.debugLineNum = 35192847;BA.debugLine="addET.Hint = \"Add a task...\"";
_addet.setHint("Add a task...");
RDebugUtils.currentLine=35192848;
 //BA.debugLineNum = 35192848;BA.debugLine="addET.Tag = Null";
_addet.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=35192850;
 //BA.debugLineNum = 35192850;BA.debugLine="Dim confirmBtn As Button";
_confirmbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=35192851;
 //BA.debugLineNum = 35192851;BA.debugLine="confirmBtn.Initialize(\"enterTaskBtnGrp\")";
_confirmbtn.Initialize(mostCurrent.activityBA,"enterTaskBtnGrp");
RDebugUtils.currentLine=35192852;
 //BA.debugLineNum = 35192852;BA.debugLine="confirmBtn.Text = \"Enter task\"";
_confirmbtn.setText(BA.ObjectToCharSequence("Enter task"));
RDebugUtils.currentLine=35192854;
 //BA.debugLineNum = 35192854;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=35192855;
 //BA.debugLineNum = 35192855;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=35192856;
 //BA.debugLineNum = 35192856;BA.debugLine="confirmBtn.Background = cd";
_confirmbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=35192857;
 //BA.debugLineNum = 35192857;BA.debugLine="confirmBtn.TextColor = Colors.White";
_confirmbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=35192860;
 //BA.debugLineNum = 35192860;BA.debugLine="Dim ctx2 As List";
_ctx2 = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=35192861;
 //BA.debugLineNum = 35192861;BA.debugLine="ctx2.Initialize";
_ctx2.Initialize();
RDebugUtils.currentLine=35192862;
 //BA.debugLineNum = 35192862;BA.debugLine="ctx2.Add(code)";
_ctx2.Add((Object)(_code));
RDebugUtils.currentLine=35192863;
 //BA.debugLineNum = 35192863;BA.debugLine="ctx2.Add(currentGrpList)";
_ctx2.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=35192864;
 //BA.debugLineNum = 35192864;BA.debugLine="ctx2.Add(addET)     ' index 2: the EditText";
_ctx2.Add((Object)(_addet.getObject()));
RDebugUtils.currentLine=35192865;
 //BA.debugLineNum = 35192865;BA.debugLine="ctx2.Add(Null)      ' index 3: oldTask (Null = ne";
_ctx2.Add(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=35192866;
 //BA.debugLineNum = 35192866;BA.debugLine="confirmBtn.Tag = ctx2";
_confirmbtn.setTag((Object)(_ctx2.getObject()));
RDebugUtils.currentLine=35192868;
 //BA.debugLineNum = 35192868;BA.debugLine="addPNL.AddView(addET, 0, 0, 190dip, 60dip)";
_addpnl.AddView((android.view.View)(_addet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=35192869;
 //BA.debugLineNum = 35192869;BA.debugLine="addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40di";
_addpnl.AddView((android.view.View)(_confirmbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=35192871;
 //BA.debugLineNum = 35192871;BA.debugLine="tasksListGrp.Add(addPNL, addPNL)";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_addpnl.getObject())),(Object)(_addpnl.getObject()));
RDebugUtils.currentLine=35192872;
 //BA.debugLineNum = 35192872;BA.debugLine="End Sub";
return "";
}
public static String  _addtitletextarea_enterpressed() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtitletextarea_enterpressed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtitletextarea_enterpressed", null));}
anywheresoftware.b4a.objects.collections.List _ctx = null;
int _oldindex = 0;
String _oldtitle = "";
String _newtitle = "";
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _existingtitle = "";
String _oldkey = "";
String _newkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
String _oldck = "";
String _newck = "";
anywheresoftware.b4a.objects.collections.List _savedlists2 = null;
String _t = "";
String _title = "";
RDebugUtils.currentLine=3735552;
 //BA.debugLineNum = 3735552;BA.debugLine="Sub addTitleTextArea_EnterPressed";
RDebugUtils.currentLine=3735555;
 //BA.debugLineNum = 3735555;BA.debugLine="If addTitleTextArea.Tag <> Null And addTitleTextA";
if (mostCurrent._addtitletextarea.getTag()!= null && mostCurrent._addtitletextarea.getTag() instanceof java.util.List) { 
RDebugUtils.currentLine=3735556;
 //BA.debugLineNum = 3735556;BA.debugLine="Dim ctx As List = addTitleTextArea.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(mostCurrent._addtitletextarea.getTag()));
RDebugUtils.currentLine=3735557;
 //BA.debugLineNum = 3735557;BA.debugLine="Dim oldIndex As Int = ctx.Get(0)";
_oldindex = (int)(BA.ObjectToNumber(_ctx.Get((int) (0))));
RDebugUtils.currentLine=3735558;
 //BA.debugLineNum = 3735558;BA.debugLine="Dim oldTitle As String = ctx.Get(1)";
_oldtitle = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=3735559;
 //BA.debugLineNum = 3735559;BA.debugLine="Dim newTitle As String = addTitleTextArea.Text.T";
_newtitle = mostCurrent._addtitletextarea.getText().trim();
RDebugUtils.currentLine=3735561;
 //BA.debugLineNum = 3735561;BA.debugLine="If newTitle = \"\" Or newTitle = oldTitle Then";
if ((_newtitle).equals("") || (_newtitle).equals(_oldtitle)) { 
RDebugUtils.currentLine=3735562;
 //BA.debugLineNum = 3735562;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=3735563;
 //BA.debugLineNum = 3735563;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=3735564;
 //BA.debugLineNum = 3735564;BA.debugLine="addTitleTextArea.Enabled = False";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735565;
 //BA.debugLineNum = 3735565;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3735569;
 //BA.debugLineNum = 3735569;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=3735570;
 //BA.debugLineNum = 3735570;BA.debugLine="For Each existingTitle As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group13 = _savedlists;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_existingtitle = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=3735571;
 //BA.debugLineNum = 3735571;BA.debugLine="If existingTitle = newTitle Then";
if ((_existingtitle).equals(_newtitle)) { 
RDebugUtils.currentLine=3735572;
 //BA.debugLineNum = 3735572;BA.debugLine="MsgboxAsync(\"A list with that name already exi";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("A list with that name already exists."),BA.ObjectToCharSequence("Duplicate title"),processBA);
RDebugUtils.currentLine=3735573;
 //BA.debugLineNum = 3735573;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=3735578;
 //BA.debugLineNum = 3735578;BA.debugLine="savedLists.Set(oldIndex, newTitle)";
_savedlists.Set(_oldindex,(Object)(_newtitle));
RDebugUtils.currentLine=3735579;
 //BA.debugLineNum = 3735579;BA.debugLine="kvs.Put(\"lists\", savedLists)";
_kvs._put("lists",(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=3735582;
 //BA.debugLineNum = 3735582;BA.debugLine="Dim oldKey As String = \"list_\" & oldTitle";
_oldkey = "list_"+_oldtitle;
RDebugUtils.currentLine=3735583;
 //BA.debugLineNum = 3735583;BA.debugLine="Dim newKey As String = \"list_\" & newTitle";
_newkey = "list_"+_newtitle;
RDebugUtils.currentLine=3735584;
 //BA.debugLineNum = 3735584;BA.debugLine="If kvs.ContainsKey(oldKey) Then";
if (_kvs._containskey(_oldkey)) { 
RDebugUtils.currentLine=3735585;
 //BA.debugLineNum = 3735585;BA.debugLine="Dim savedTasks As List = kvs.Get(oldKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_oldkey)));
RDebugUtils.currentLine=3735586;
 //BA.debugLineNum = 3735586;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group25 = _savedtasks;
final int groupLen25 = group25.getSize()
;int index25 = 0;
;
for (; index25 < groupLen25;index25++){
_task = BA.ObjectToString(group25.Get(index25));
RDebugUtils.currentLine=3735587;
 //BA.debugLineNum = 3735587;BA.debugLine="Dim oldCK As String = \"checked_\" & oldTitle &";
_oldck = "checked_"+_oldtitle+"_"+_task;
RDebugUtils.currentLine=3735588;
 //BA.debugLineNum = 3735588;BA.debugLine="Dim newCK As String = \"checked_\" & newTitle &";
_newck = "checked_"+_newtitle+"_"+_task;
RDebugUtils.currentLine=3735589;
 //BA.debugLineNum = 3735589;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=3735590;
 //BA.debugLineNum = 3735590;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=3735591;
 //BA.debugLineNum = 3735591;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
 }
};
RDebugUtils.currentLine=3735594;
 //BA.debugLineNum = 3735594;BA.debugLine="kvs.Put(newKey, savedTasks)";
_kvs._put(_newkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=3735595;
 //BA.debugLineNum = 3735595;BA.debugLine="kvs.Remove(oldKey)";
_kvs._remove(_oldkey);
 };
RDebugUtils.currentLine=3735599;
 //BA.debugLineNum = 3735599;BA.debugLine="If currentList = oldTitle Then";
if ((mostCurrent._currentlist).equals(_oldtitle)) { 
RDebugUtils.currentLine=3735600;
 //BA.debugLineNum = 3735600;BA.debugLine="currentList = newTitle";
mostCurrent._currentlist = _newtitle;
 };
RDebugUtils.currentLine=3735604;
 //BA.debugLineNum = 3735604;BA.debugLine="listsList.Clear";
mostCurrent._listslist._clear();
RDebugUtils.currentLine=3735605;
 //BA.debugLineNum = 3735605;BA.debugLine="Dim savedLists2 As List = kvs.Get(\"lists\")";
_savedlists2 = new anywheresoftware.b4a.objects.collections.List();
_savedlists2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=3735606;
 //BA.debugLineNum = 3735606;BA.debugLine="For Each t As String In savedLists2";
{
final anywheresoftware.b4a.BA.IterableList group41 = _savedlists2;
final int groupLen41 = group41.getSize()
;int index41 = 0;
;
for (; index41 < groupLen41;index41++){
_t = BA.ObjectToString(group41.Get(index41));
RDebugUtils.currentLine=3735607;
 //BA.debugLineNum = 3735607;BA.debugLine="listsList.AddTextItem(t, t)";
mostCurrent._listslist._addtextitem((Object)(_t),(Object)(_t));
 }
};
RDebugUtils.currentLine=3735610;
 //BA.debugLineNum = 3735610;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=3735611;
 //BA.debugLineNum = 3735611;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=3735612;
 //BA.debugLineNum = 3735612;BA.debugLine="addTitleTextArea.Enabled = False";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735613;
 //BA.debugLineNum = 3735613;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3735614;
 //BA.debugLineNum = 3735614;BA.debugLine="isAddingList = False";
_isaddinglist = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=3735615;
 //BA.debugLineNum = 3735615;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735616;
 //BA.debugLineNum = 3735616;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3735620;
 //BA.debugLineNum = 3735620;BA.debugLine="Dim title As String = addTitleTextArea.Text";
_title = mostCurrent._addtitletextarea.getText();
RDebugUtils.currentLine=3735622;
 //BA.debugLineNum = 3735622;BA.debugLine="Dim savedLists As List";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=3735623;
 //BA.debugLineNum = 3735623;BA.debugLine="savedLists.Initialize";
_savedlists.Initialize();
RDebugUtils.currentLine=3735625;
 //BA.debugLineNum = 3735625;BA.debugLine="If title = \"\" Then";
if ((_title).equals("")) { 
RDebugUtils.currentLine=3735626;
 //BA.debugLineNum = 3735626;BA.debugLine="title = \"Untitled\" & untitledNo";
_title = "Untitled"+BA.NumberToString(_untitledno);
RDebugUtils.currentLine=3735628;
 //BA.debugLineNum = 3735628;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=3735629;
 //BA.debugLineNum = 3735629;BA.debugLine="savedLists = kvs.Get(\"lists\")";
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=3735630;
 //BA.debugLineNum = 3735630;BA.debugLine="For Each existingTitle As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group59 = _savedlists;
final int groupLen59 = group59.getSize()
;int index59 = 0;
;
for (; index59 < groupLen59;index59++){
_existingtitle = BA.ObjectToString(group59.Get(index59));
RDebugUtils.currentLine=3735631;
 //BA.debugLineNum = 3735631;BA.debugLine="If title = existingTitle Then";
if ((_title).equals(_existingtitle)) { 
RDebugUtils.currentLine=3735632;
 //BA.debugLineNum = 3735632;BA.debugLine="untitledNo = untitledNo + 1";
_untitledno = (int) (_untitledno+1);
RDebugUtils.currentLine=3735633;
 //BA.debugLineNum = 3735633;BA.debugLine="title = \"Untitled\" & untitledNo";
_title = "Untitled"+BA.NumberToString(_untitledno);
 };
 }
};
 };
RDebugUtils.currentLine=3735638;
 //BA.debugLineNum = 3735638;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=3735641;
 //BA.debugLineNum = 3735641;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=3735642;
 //BA.debugLineNum = 3735642;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=3735643;
 //BA.debugLineNum = 3735643;BA.debugLine="For Each existingTitle As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group70 = _savedlists;
final int groupLen70 = group70.getSize()
;int index70 = 0;
;
for (; index70 < groupLen70;index70++){
_existingtitle = BA.ObjectToString(group70.Get(index70));
RDebugUtils.currentLine=3735644;
 //BA.debugLineNum = 3735644;BA.debugLine="If existingTitle = title Then";
if ((_existingtitle).equals(_title)) { 
RDebugUtils.currentLine=3735645;
 //BA.debugLineNum = 3735645;BA.debugLine="MsgboxAsync(\"List already exists.\", \"Duplicate";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("List already exists."),BA.ObjectToCharSequence("Duplicate title"),processBA);
RDebugUtils.currentLine=3735646;
 //BA.debugLineNum = 3735646;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3735647;
 //BA.debugLineNum = 3735647;BA.debugLine="addTitleTextArea.Text = \"\"";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3735648;
 //BA.debugLineNum = 3735648;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 };
RDebugUtils.currentLine=3735653;
 //BA.debugLineNum = 3735653;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=3735654;
 //BA.debugLineNum = 3735654;BA.debugLine="savedLists = kvs.Get(\"lists\")";
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
 };
RDebugUtils.currentLine=3735657;
 //BA.debugLineNum = 3735657;BA.debugLine="savedLists.Add(title)";
_savedlists.Add((Object)(_title));
RDebugUtils.currentLine=3735658;
 //BA.debugLineNum = 3735658;BA.debugLine="kvs.Put(\"lists\", savedLists)";
_kvs._put("lists",(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=3735660;
 //BA.debugLineNum = 3735660;BA.debugLine="listsList.AddTextItem(title, title)";
mostCurrent._listslist._addtextitem((Object)(_title),(Object)(_title));
RDebugUtils.currentLine=3735662;
 //BA.debugLineNum = 3735662;BA.debugLine="currentList = title";
mostCurrent._currentlist = _title;
RDebugUtils.currentLine=3735663;
 //BA.debugLineNum = 3735663;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=3735664;
 //BA.debugLineNum = 3735664;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3735665;
 //BA.debugLineNum = 3735665;BA.debugLine="addTitleTextArea.Enabled = False";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735667;
 //BA.debugLineNum = 3735667;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=3735668;
 //BA.debugLineNum = 3735668;BA.debugLine="tasksList.GetBase.Visible = True";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3735669;
 //BA.debugLineNum = 3735669;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=3735671;
 //BA.debugLineNum = 3735671;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3735672;
 //BA.debugLineNum = 3735672;BA.debugLine="isAddingList = False";
_isaddinglist = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=3735673;
 //BA.debugLineNum = 3735673;BA.debugLine="progressNumber.Text = \"0 / 0 tasks done!\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence("0 / 0 tasks done!"));
RDebugUtils.currentLine=3735674;
 //BA.debugLineNum = 3735674;BA.debugLine="progressPercent.Text = \"0%\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence("0%"));
RDebugUtils.currentLine=3735675;
 //BA.debugLineNum = 3735675;BA.debugLine="progressBar.Progress = 0";
mostCurrent._progressbar.setProgress((int) (0));
RDebugUtils.currentLine=3735677;
 //BA.debugLineNum = 3735677;BA.debugLine="End Sub";
return "";
}
public static String  _deletegroup(String _code) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "deletegroup", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "deletegroup", new Object[] {_code}));}
String _listskey = "";
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _listname = "";
String _taskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
anywheresoftware.b4a.objects.collections.List _allgroups = null;
int _idx = 0;
RDebugUtils.currentLine=34668544;
 //BA.debugLineNum = 34668544;BA.debugLine="Sub deleteGroup(code As String)";
RDebugUtils.currentLine=34668546;
 //BA.debugLineNum = 34668546;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=34668547;
 //BA.debugLineNum = 34668547;BA.debugLine="If kvs.ContainsKey(listsKey) Then";
if (_kvs._containskey(_listskey)) { 
RDebugUtils.currentLine=34668548;
 //BA.debugLineNum = 34668548;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));
RDebugUtils.currentLine=34668549;
 //BA.debugLineNum = 34668549;BA.debugLine="For Each listName As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group4 = _savedlists;
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_listname = BA.ObjectToString(group4.Get(index4));
RDebugUtils.currentLine=34668550;
 //BA.debugLineNum = 34668550;BA.debugLine="Dim taskKey As String = \"group_list_\" & code &";
_taskkey = "group_list_"+_code+"_"+_listname;
RDebugUtils.currentLine=34668551;
 //BA.debugLineNum = 34668551;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
if (_kvs._containskey(_taskkey)) { 
RDebugUtils.currentLine=34668552;
 //BA.debugLineNum = 34668552;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=34668553;
 //BA.debugLineNum = 34668553;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group8 = _savedtasks;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_task = BA.ObjectToString(group8.Get(index8));
RDebugUtils.currentLine=34668554;
 //BA.debugLineNum = 34668554;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & li";
_kvs._remove("group_checked_"+_code+"_"+_listname+"_"+_task);
 }
};
RDebugUtils.currentLine=34668556;
 //BA.debugLineNum = 34668556;BA.debugLine="kvs.Remove(taskKey)";
_kvs._remove(_taskkey);
 };
 }
};
RDebugUtils.currentLine=34668559;
 //BA.debugLineNum = 34668559;BA.debugLine="kvs.Remove(listsKey)";
_kvs._remove(_listskey);
 };
RDebugUtils.currentLine=34668561;
 //BA.debugLineNum = 34668561;BA.debugLine="kvs.Remove(\"group_name_\" & code)";
_kvs._remove("group_name_"+_code);
RDebugUtils.currentLine=34668562;
 //BA.debugLineNum = 34668562;BA.debugLine="kvs.Remove(\"group_owner_\" & code)";
_kvs._remove("group_owner_"+_code);
RDebugUtils.currentLine=34668563;
 //BA.debugLineNum = 34668563;BA.debugLine="kvs.Remove(\"group_members_\" & code)";
_kvs._remove("group_members_"+_code);
RDebugUtils.currentLine=34668566;
 //BA.debugLineNum = 34668566;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));
RDebugUtils.currentLine=34668567;
 //BA.debugLineNum = 34668567;BA.debugLine="Dim idx As Int = allGroups.IndexOf(code)";
_idx = _allgroups.IndexOf((Object)(_code));
RDebugUtils.currentLine=34668568;
 //BA.debugLineNum = 34668568;BA.debugLine="If idx >= 0 Then allGroups.RemoveAt(idx)";
if (_idx>=0) { 
_allgroups.RemoveAt(_idx);};
RDebugUtils.currentLine=34668569;
 //BA.debugLineNum = 34668569;BA.debugLine="kvs.Put(\"groups\", allGroups)";
_kvs._put("groups",(Object)(_allgroups.getObject()));
RDebugUtils.currentLine=34668570;
 //BA.debugLineNum = 34668570;BA.debugLine="End Sub";
return "";
}
public static String  _entertaskbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "entertaskbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "entertaskbtn_click", null));}
String _newtask = "";
String _key = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _existingtask = "";
anywheresoftware.b4a.objects.collections.List _ctx = null;
String _oldtask = "";
int _taskindex = 0;
String _oldck = "";
String _newck = "";
anywheresoftware.b4a.objects.collections.List _savedtasks2 = null;
String _t = "";
RDebugUtils.currentLine=4063232;
 //BA.debugLineNum = 4063232;BA.debugLine="Sub enterTaskBtn_Click";
RDebugUtils.currentLine=4063234;
 //BA.debugLineNum = 4063234;BA.debugLine="Dim newTask As String = addTaskTextArea.Text.Trim";
_newtask = mostCurrent._addtasktextarea.getText().trim();
RDebugUtils.currentLine=4063235;
 //BA.debugLineNum = 4063235;BA.debugLine="If newTask = \"\" Then";
if ((_newtask).equals("")) { 
RDebugUtils.currentLine=4063236;
 //BA.debugLineNum = 4063236;BA.debugLine="MsgboxAsync(\"Please enter a task.\", \"No task ent";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a task."),BA.ObjectToCharSequence("No task entered"),processBA);
RDebugUtils.currentLine=4063237;
 //BA.debugLineNum = 4063237;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=4063240;
 //BA.debugLineNum = 4063240;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=4063241;
 //BA.debugLineNum = 4063241;BA.debugLine="Dim savedTasks As List";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=4063242;
 //BA.debugLineNum = 4063242;BA.debugLine="savedTasks.Initialize";
_savedtasks.Initialize();
RDebugUtils.currentLine=4063244;
 //BA.debugLineNum = 4063244;BA.debugLine="If kvs.ContainsKey(key) Then";
if (_kvs._containskey(_key)) { 
RDebugUtils.currentLine=4063245;
 //BA.debugLineNum = 4063245;BA.debugLine="savedTasks = kvs.Get(key)";
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
 };
RDebugUtils.currentLine=4063248;
 //BA.debugLineNum = 4063248;BA.debugLine="For Each existingTask As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group12 = _savedtasks;
final int groupLen12 = group12.getSize()
;int index12 = 0;
;
for (; index12 < groupLen12;index12++){
_existingtask = BA.ObjectToString(group12.Get(index12));
RDebugUtils.currentLine=4063249;
 //BA.debugLineNum = 4063249;BA.debugLine="If existingTask = newTask Then";
if ((_existingtask).equals(_newtask)) { 
RDebugUtils.currentLine=4063250;
 //BA.debugLineNum = 4063250;BA.debugLine="MsgboxAsync(\"A task with that name already exis";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("A task with that name already exists."),BA.ObjectToCharSequence("Duplicate task"),processBA);
RDebugUtils.currentLine=4063251;
 //BA.debugLineNum = 4063251;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=4063256;
 //BA.debugLineNum = 4063256;BA.debugLine="If addTaskTextArea.Tag <> Null Then";
if (mostCurrent._addtasktextarea.getTag()!= null) { 
RDebugUtils.currentLine=4063257;
 //BA.debugLineNum = 4063257;BA.debugLine="Dim ctx As List = addTaskTextArea.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(mostCurrent._addtasktextarea.getTag()));
RDebugUtils.currentLine=4063258;
 //BA.debugLineNum = 4063258;BA.debugLine="Dim oldTask As String = ctx.Get(1)";
_oldtask = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=4063260;
 //BA.debugLineNum = 4063260;BA.debugLine="Dim taskIndex As Int = savedTasks.IndexOf(oldTas";
_taskindex = _savedtasks.IndexOf((Object)(_oldtask));
RDebugUtils.currentLine=4063261;
 //BA.debugLineNum = 4063261;BA.debugLine="If taskIndex >= 0 Then";
if (_taskindex>=0) { 
RDebugUtils.currentLine=4063262;
 //BA.debugLineNum = 4063262;BA.debugLine="savedTasks.Set(taskIndex, newTask)";
_savedtasks.Set(_taskindex,(Object)(_newtask));
RDebugUtils.currentLine=4063263;
 //BA.debugLineNum = 4063263;BA.debugLine="kvs.Put(key, savedTasks)";
_kvs._put(_key,(Object)(_savedtasks.getObject()));
 };
RDebugUtils.currentLine=4063267;
 //BA.debugLineNum = 4063267;BA.debugLine="Dim oldCK As String = \"checked_\" & currentList &";
_oldck = "checked_"+mostCurrent._currentlist+"_"+_oldtask;
RDebugUtils.currentLine=4063268;
 //BA.debugLineNum = 4063268;BA.debugLine="Dim newCK As String = \"checked_\" & currentList &";
_newck = "checked_"+mostCurrent._currentlist+"_"+_newtask;
RDebugUtils.currentLine=4063269;
 //BA.debugLineNum = 4063269;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=4063270;
 //BA.debugLineNum = 4063270;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=4063271;
 //BA.debugLineNum = 4063271;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
RDebugUtils.currentLine=4063274;
 //BA.debugLineNum = 4063274;BA.debugLine="addTaskTextArea.Tag = Null";
mostCurrent._addtasktextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=4063277;
 //BA.debugLineNum = 4063277;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=4063278;
 //BA.debugLineNum = 4063278;BA.debugLine="Dim savedTasks2 As List = kvs.Get(key)";
_savedtasks2 = new anywheresoftware.b4a.objects.collections.List();
_savedtasks2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
RDebugUtils.currentLine=4063279;
 //BA.debugLineNum = 4063279;BA.debugLine="For Each t As String In savedTasks2";
{
final anywheresoftware.b4a.BA.IterableList group35 = _savedtasks2;
final int groupLen35 = group35.getSize()
;int index35 = 0;
;
for (; index35 < groupLen35;index35++){
_t = BA.ObjectToString(group35.Get(index35));
RDebugUtils.currentLine=4063280;
 //BA.debugLineNum = 4063280;BA.debugLine="tasksListUI(t)";
_taskslistui(_t);
 }
};
RDebugUtils.currentLine=4063282;
 //BA.debugLineNum = 4063282;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=4063283;
 //BA.debugLineNum = 4063283;BA.debugLine="addTaskBtn.Enabled = True";
mostCurrent._addtaskbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063284;
 //BA.debugLineNum = 4063284;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=4063285;
 //BA.debugLineNum = 4063285;BA.debugLine="ToastMessageShow(\"Task renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4063286;
 //BA.debugLineNum = 4063286;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=4063290;
 //BA.debugLineNum = 4063290;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1)";
mostCurrent._taskslist._removeat((int) (mostCurrent._taskslist._getsize()-1));
RDebugUtils.currentLine=4063292;
 //BA.debugLineNum = 4063292;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=4063293;
 //BA.debugLineNum = 4063293;BA.debugLine="Dim savedTasks As List";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=4063294;
 //BA.debugLineNum = 4063294;BA.debugLine="If kvs.ContainsKey(key) Then";
if (_kvs._containskey(_key)) { 
RDebugUtils.currentLine=4063295;
 //BA.debugLineNum = 4063295;BA.debugLine="savedTasks = kvs.Get(key)";
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
 }else {
RDebugUtils.currentLine=4063297;
 //BA.debugLineNum = 4063297;BA.debugLine="savedTasks.Initialize";
_savedtasks.Initialize();
 };
RDebugUtils.currentLine=4063300;
 //BA.debugLineNum = 4063300;BA.debugLine="savedTasks.Add(newTask)";
_savedtasks.Add((Object)(_newtask));
RDebugUtils.currentLine=4063301;
 //BA.debugLineNum = 4063301;BA.debugLine="kvs.Put(key, savedTasks)";
_kvs._put(_key,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=4063303;
 //BA.debugLineNum = 4063303;BA.debugLine="tasksListUI(newTask)";
_taskslistui(_newtask);
RDebugUtils.currentLine=4063304;
 //BA.debugLineNum = 4063304;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=4063305;
 //BA.debugLineNum = 4063305;BA.debugLine="addTaskBtn.Enabled = True";
mostCurrent._addtaskbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063306;
 //BA.debugLineNum = 4063306;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=4063308;
 //BA.debugLineNum = 4063308;BA.debugLine="End Sub";
return "";
}
public static String  _taskslistui(String _newtask) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskslistui", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskslistui", new Object[] {_newtask}));}
anywheresoftware.b4a.objects.PanelWrapper _taskpnl = null;
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _taskcheckbox = null;
anywheresoftware.b4a.objects.LabelWrapper _tasklbl = null;
anywheresoftware.b4a.objects.PanelWrapper _divider = null;
String _checkedkey = "";
boolean _ischecked = false;
RDebugUtils.currentLine=4325376;
 //BA.debugLineNum = 4325376;BA.debugLine="Sub tasksListUI(newTask As String)";
RDebugUtils.currentLine=4325378;
 //BA.debugLineNum = 4325378;BA.debugLine="Dim taskPNL As Panel";
_taskpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=4325379;
 //BA.debugLineNum = 4325379;BA.debugLine="taskPNL.Initialize(\"taskPNL\")";
_taskpnl.Initialize(mostCurrent.activityBA,"taskPNL");
RDebugUtils.currentLine=4325380;
 //BA.debugLineNum = 4325380;BA.debugLine="taskPNL.SetLayout(0, 0, 250dip, 60dip)";
_taskpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=4325382;
 //BA.debugLineNum = 4325382;BA.debugLine="Dim taskCheckbox As CheckBox";
_taskcheckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
RDebugUtils.currentLine=4325383;
 //BA.debugLineNum = 4325383;BA.debugLine="taskCheckbox.Initialize(\"taskCheckbox\")";
_taskcheckbox.Initialize(mostCurrent.activityBA,"taskCheckbox");
RDebugUtils.currentLine=4325384;
 //BA.debugLineNum = 4325384;BA.debugLine="taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip,";
_taskpnl.AddView((android.view.View)(_taskcheckbox.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=4325386;
 //BA.debugLineNum = 4325386;BA.debugLine="Dim taskLBL As Label";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=4325387;
 //BA.debugLineNum = 4325387;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=4325388;
 //BA.debugLineNum = 4325388;BA.debugLine="taskLBL.Initialize(\"taskLBL\")";
_tasklbl.Initialize(mostCurrent.activityBA,"taskLBL");
RDebugUtils.currentLine=4325389;
 //BA.debugLineNum = 4325389;BA.debugLine="taskLBL.Text = newTask";
_tasklbl.setText(BA.ObjectToCharSequence(_newtask));
RDebugUtils.currentLine=4325390;
 //BA.debugLineNum = 4325390;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=4325391;
 //BA.debugLineNum = 4325391;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.W";
_taskpnl.AddView((android.view.View)(_tasklbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (_taskpnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),_taskpnl.getHeight());
 }else {
RDebugUtils.currentLine=4325393;
 //BA.debugLineNum = 4325393;BA.debugLine="taskLBL.Initialize(\"taskLBL\")";
_tasklbl.Initialize(mostCurrent.activityBA,"taskLBL");
RDebugUtils.currentLine=4325394;
 //BA.debugLineNum = 4325394;BA.debugLine="taskLBL.Text = newTask";
_tasklbl.setText(BA.ObjectToCharSequence(_newtask));
RDebugUtils.currentLine=4325395;
 //BA.debugLineNum = 4325395;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=4325396;
 //BA.debugLineNum = 4325396;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.W";
_taskpnl.AddView((android.view.View)(_tasklbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (_taskpnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),_taskpnl.getHeight());
 };
RDebugUtils.currentLine=4325400;
 //BA.debugLineNum = 4325400;BA.debugLine="Dim divider As Panel";
_divider = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=4325401;
 //BA.debugLineNum = 4325401;BA.debugLine="divider.Initialize(\"line\")";
_divider.Initialize(mostCurrent.activityBA,"line");
RDebugUtils.currentLine=4325402;
 //BA.debugLineNum = 4325402;BA.debugLine="divider.Color = Colors.ARGB(255, 60, 60, 60)";
_divider.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (60),(int) (60),(int) (60)));
RDebugUtils.currentLine=4325403;
 //BA.debugLineNum = 4325403;BA.debugLine="taskPNL.AddView(divider, 0, 59dip, taskPNL.Width,";
_taskpnl.AddView((android.view.View)(_divider.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (59)),_taskpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
RDebugUtils.currentLine=4325405;
 //BA.debugLineNum = 4325405;BA.debugLine="taskCheckbox.Tag = taskLBL";
_taskcheckbox.setTag((Object)(_tasklbl.getObject()));
RDebugUtils.currentLine=4325408;
 //BA.debugLineNum = 4325408;BA.debugLine="Dim checkedKey As String = \"checked_\" & currentLi";
_checkedkey = "checked_"+mostCurrent._currentlist+"_"+_newtask;
RDebugUtils.currentLine=4325409;
 //BA.debugLineNum = 4325409;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=4325410;
 //BA.debugLineNum = 4325410;BA.debugLine="Dim isChecked As Boolean = kvs.Get(checkedKey)";
_ischecked = BA.ObjectToBoolean(_kvs._get(_checkedkey));
RDebugUtils.currentLine=4325411;
 //BA.debugLineNum = 4325411;BA.debugLine="taskCheckbox.Checked = isChecked";
_taskcheckbox.setChecked(_ischecked);
RDebugUtils.currentLine=4325412;
 //BA.debugLineNum = 4325412;BA.debugLine="If isChecked Then";
if (_ischecked) { 
RDebugUtils.currentLine=4325413;
 //BA.debugLineNum = 4325413;BA.debugLine="taskLBL.TextColor = Colors.ARGB(50, 0, 0, 0)";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (0),(int) (0),(int) (0)));
 };
 };
RDebugUtils.currentLine=4325417;
 //BA.debugLineNum = 4325417;BA.debugLine="tasksList.Add(taskPNL, newTask)";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_taskpnl.getObject())),(Object)(_newtask));
RDebugUtils.currentLine=4325419;
 //BA.debugLineNum = 4325419;BA.debugLine="End Sub";
return "";
}
public static String  _updateprogress() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updateprogress", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updateprogress", null));}
String _key = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
int _totaltasks = 0;
int _donetasks = 0;
int _percentagetasks = 0;
String _task = "";
String _checkedkey = "";
RDebugUtils.currentLine=4390912;
 //BA.debugLineNum = 4390912;BA.debugLine="Sub updateProgress";
RDebugUtils.currentLine=4390914;
 //BA.debugLineNum = 4390914;BA.debugLine="If currentList = \"\" Then";
if ((mostCurrent._currentlist).equals("")) { 
RDebugUtils.currentLine=4390915;
 //BA.debugLineNum = 4390915;BA.debugLine="progressNumber.Text = \"\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=4390916;
 //BA.debugLineNum = 4390916;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=4390919;
 //BA.debugLineNum = 4390919;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=4390920;
 //BA.debugLineNum = 4390920;BA.debugLine="If kvs.ContainsKey(key) = False Then";
if (_kvs._containskey(_key)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=4390921;
 //BA.debugLineNum = 4390921;BA.debugLine="progressNumber.Text = \"0 / 0 tasks done!\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence("0 / 0 tasks done!"));
RDebugUtils.currentLine=4390922;
 //BA.debugLineNum = 4390922;BA.debugLine="progressPercent.Text = \"0%\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence("0%"));
RDebugUtils.currentLine=4390923;
 //BA.debugLineNum = 4390923;BA.debugLine="progressBar.Progress = 0";
mostCurrent._progressbar.setProgress((int) (0));
RDebugUtils.currentLine=4390924;
 //BA.debugLineNum = 4390924;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=4390928;
 //BA.debugLineNum = 4390928;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
RDebugUtils.currentLine=4390929;
 //BA.debugLineNum = 4390929;BA.debugLine="Dim totalTasks As Int = savedTasks.Size";
_totaltasks = _savedtasks.getSize();
RDebugUtils.currentLine=4390930;
 //BA.debugLineNum = 4390930;BA.debugLine="Dim doneTasks As Int = 0";
_donetasks = (int) (0);
RDebugUtils.currentLine=4390931;
 //BA.debugLineNum = 4390931;BA.debugLine="Dim percentageTasks As Int = 0";
_percentagetasks = (int) (0);
RDebugUtils.currentLine=4390933;
 //BA.debugLineNum = 4390933;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group16 = _savedtasks;
final int groupLen16 = group16.getSize()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_task = BA.ObjectToString(group16.Get(index16));
RDebugUtils.currentLine=4390934;
 //BA.debugLineNum = 4390934;BA.debugLine="Dim checkedKey As String = \"checked_\" & currentL";
_checkedkey = "checked_"+mostCurrent._currentlist+"_"+_task;
RDebugUtils.currentLine=4390935;
 //BA.debugLineNum = 4390935;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=4390936;
 //BA.debugLineNum = 4390936;BA.debugLine="If kvs.Get(checkedKey) = True Then";
if ((_kvs._get(_checkedkey)).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
RDebugUtils.currentLine=4390937;
 //BA.debugLineNum = 4390937;BA.debugLine="doneTasks = doneTasks + 1";
_donetasks = (int) (_donetasks+1);
 };
 };
 }
};
RDebugUtils.currentLine=4390942;
 //BA.debugLineNum = 4390942;BA.debugLine="percentageTasks = (doneTasks / totalTasks) * 100";
_percentagetasks = (int) ((_donetasks/(double)_totaltasks)*100);
RDebugUtils.currentLine=4390944;
 //BA.debugLineNum = 4390944;BA.debugLine="progressNumber.Text = doneTasks & \" / \" & totalTa";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence(BA.NumberToString(_donetasks)+" / "+BA.NumberToString(_totaltasks)+" tasks done!"));
RDebugUtils.currentLine=4390945;
 //BA.debugLineNum = 4390945;BA.debugLine="progressPercent.Text = percentageTasks & \"%\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence(BA.NumberToString(_percentagetasks)+"%"));
RDebugUtils.currentLine=4390946;
 //BA.debugLineNum = 4390946;BA.debugLine="progressBar.Progress = percentageTasks";
mostCurrent._progressbar.setProgress(_percentagetasks);
RDebugUtils.currentLine=4390948;
 //BA.debugLineNum = 4390948;BA.debugLine="End Sub";
return "";
}
public static String  _entertaskbtngrp_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "entertaskbtngrp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "entertaskbtngrp_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
anywheresoftware.b4a.objects.collections.List _ctx = null;
String _code = "";
String _currentgrplist = "";
anywheresoftware.b4a.objects.EditTextWrapper _addet = null;
Object _oldtask = null;
String _newtask = "";
String _taskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _existing = "";
int _taskindex = 0;
String _oldck = "";
String _newck = "";
anywheresoftware.b4a.objects.collections.List _savedtasks2 = null;
String _t = "";
RDebugUtils.currentLine=35258368;
 //BA.debugLineNum = 35258368;BA.debugLine="Sub enterTaskBtnGrp_Click";
RDebugUtils.currentLine=35258369;
 //BA.debugLineNum = 35258369;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=35258370;
 //BA.debugLineNum = 35258370;BA.debugLine="Dim ctx As List = btn.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_btn.getTag()));
RDebugUtils.currentLine=35258371;
 //BA.debugLineNum = 35258371;BA.debugLine="Dim code As String = ctx.Get(0)";
_code = BA.ObjectToString(_ctx.Get((int) (0)));
RDebugUtils.currentLine=35258372;
 //BA.debugLineNum = 35258372;BA.debugLine="Dim currentGrpList As String = ctx.Get(1)";
_currentgrplist = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=35258373;
 //BA.debugLineNum = 35258373;BA.debugLine="Dim addET As EditText = ctx.Get(2)";
_addet = new anywheresoftware.b4a.objects.EditTextWrapper();
_addet = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_ctx.Get((int) (2))));
RDebugUtils.currentLine=35258374;
 //BA.debugLineNum = 35258374;BA.debugLine="Dim oldTask As Object = ctx.Get(3)";
_oldtask = _ctx.Get((int) (3));
RDebugUtils.currentLine=35258376;
 //BA.debugLineNum = 35258376;BA.debugLine="Dim newTask As String = addET.Text.Trim";
_newtask = _addet.getText().trim();
RDebugUtils.currentLine=35258377;
 //BA.debugLineNum = 35258377;BA.debugLine="If newTask = \"\" Then";
if ((_newtask).equals("")) { 
RDebugUtils.currentLine=35258378;
 //BA.debugLineNum = 35258378;BA.debugLine="MsgboxAsync(\"Please enter a task.\", \"No task ent";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a task."),BA.ObjectToCharSequence("No task entered"),processBA);
RDebugUtils.currentLine=35258379;
 //BA.debugLineNum = 35258379;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=35258382;
 //BA.debugLineNum = 35258382;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=35258383;
 //BA.debugLineNum = 35258383;BA.debugLine="Dim savedTasks As List";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=35258384;
 //BA.debugLineNum = 35258384;BA.debugLine="savedTasks.Initialize";
_savedtasks.Initialize();
RDebugUtils.currentLine=35258385;
 //BA.debugLineNum = 35258385;BA.debugLine="If kvs.ContainsKey(taskKey) Then savedTasks = kvs";
if (_kvs._containskey(_taskkey)) { 
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));};
RDebugUtils.currentLine=35258388;
 //BA.debugLineNum = 35258388;BA.debugLine="For Each existing As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group16 = _savedtasks;
final int groupLen16 = group16.getSize()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_existing = BA.ObjectToString(group16.Get(index16));
RDebugUtils.currentLine=35258389;
 //BA.debugLineNum = 35258389;BA.debugLine="If existing = newTask Then";
if ((_existing).equals(_newtask)) { 
RDebugUtils.currentLine=35258390;
 //BA.debugLineNum = 35258390;BA.debugLine="MsgboxAsync(\"Task already exists.\", \"Duplicate\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Task already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=35258391;
 //BA.debugLineNum = 35258391;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=35258395;
 //BA.debugLineNum = 35258395;BA.debugLine="If oldTask <> Null Then";
if (_oldtask!= null) { 
RDebugUtils.currentLine=35258397;
 //BA.debugLineNum = 35258397;BA.debugLine="Dim taskIndex As Int = savedTasks.IndexOf(oldTas";
_taskindex = _savedtasks.IndexOf(_oldtask);
RDebugUtils.currentLine=35258398;
 //BA.debugLineNum = 35258398;BA.debugLine="If taskIndex >= 0 Then";
if (_taskindex>=0) { 
RDebugUtils.currentLine=35258399;
 //BA.debugLineNum = 35258399;BA.debugLine="savedTasks.Set(taskIndex, newTask)";
_savedtasks.Set(_taskindex,(Object)(_newtask));
RDebugUtils.currentLine=35258400;
 //BA.debugLineNum = 35258400;BA.debugLine="kvs.Put(taskKey, savedTasks)";
_kvs._put(_taskkey,(Object)(_savedtasks.getObject()));
 };
RDebugUtils.currentLine=35258403;
 //BA.debugLineNum = 35258403;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code &";
_oldck = "group_checked_"+_code+"_"+_currentgrplist+"_"+BA.ObjectToString(_oldtask);
RDebugUtils.currentLine=35258404;
 //BA.debugLineNum = 35258404;BA.debugLine="Dim newCK As String = \"group_checked_\" & code &";
_newck = "group_checked_"+_code+"_"+_currentgrplist+"_"+_newtask;
RDebugUtils.currentLine=35258405;
 //BA.debugLineNum = 35258405;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=35258406;
 //BA.debugLineNum = 35258406;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=35258407;
 //BA.debugLineNum = 35258407;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
RDebugUtils.currentLine=35258411;
 //BA.debugLineNum = 35258411;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=35258412;
 //BA.debugLineNum = 35258412;BA.debugLine="Dim savedTasks2 As List = kvs.Get(taskKey)";
_savedtasks2 = new anywheresoftware.b4a.objects.collections.List();
_savedtasks2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=35258413;
 //BA.debugLineNum = 35258413;BA.debugLine="For Each t As String In savedTasks2";
{
final anywheresoftware.b4a.BA.IterableList group36 = _savedtasks2;
final int groupLen36 = group36.getSize()
;int index36 = 0;
;
for (; index36 < groupLen36;index36++){
_t = BA.ObjectToString(group36.Get(index36));
RDebugUtils.currentLine=35258414;
 //BA.debugLineNum = 35258414;BA.debugLine="tasksListGrpUI(t, code, currentGrpList)";
_taskslistgrpui(_t,_code,_currentgrplist);
 }
};
RDebugUtils.currentLine=35258416;
 //BA.debugLineNum = 35258416;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
_newaddtaskbtngrp(_code,_currentgrplist);
RDebugUtils.currentLine=35258417;
 //BA.debugLineNum = 35258417;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=35258418;
 //BA.debugLineNum = 35258418;BA.debugLine="ToastMessageShow(\"Task renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=35258419;
 //BA.debugLineNum = 35258419;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=35258423;
 //BA.debugLineNum = 35258423;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
mostCurrent._taskslistgrp._removeat((int) (mostCurrent._taskslistgrp._getsize()-1));
RDebugUtils.currentLine=35258424;
 //BA.debugLineNum = 35258424;BA.debugLine="savedTasks.Add(newTask)";
_savedtasks.Add((Object)(_newtask));
RDebugUtils.currentLine=35258425;
 //BA.debugLineNum = 35258425;BA.debugLine="kvs.Put(taskKey, savedTasks)";
_kvs._put(_taskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=35258426;
 //BA.debugLineNum = 35258426;BA.debugLine="tasksListGrpUI(newTask, code, currentGrpList)";
_taskslistgrpui(_newtask,_code,_currentgrplist);
RDebugUtils.currentLine=35258427;
 //BA.debugLineNum = 35258427;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
_newaddtaskbtngrp(_code,_currentgrplist);
RDebugUtils.currentLine=35258428;
 //BA.debugLineNum = 35258428;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=35258429;
 //BA.debugLineNum = 35258429;BA.debugLine="End Sub";
return "";
}
public static String  _taskslistgrpui(String _newtask,String _code,String _currentgrplist) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskslistgrpui", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskslistgrpui", new Object[] {_newtask,_code,_currentgrplist}));}
anywheresoftware.b4a.objects.PanelWrapper _taskpnl = null;
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _taskcheckbox = null;
anywheresoftware.b4a.objects.LabelWrapper _tasklbl = null;
anywheresoftware.b4a.objects.PanelWrapper _divider = null;
anywheresoftware.b4a.objects.collections.List _cbctx = null;
String _checkedkey = "";
boolean _ischecked = false;
RDebugUtils.currentLine=35454976;
 //BA.debugLineNum = 35454976;BA.debugLine="Sub tasksListGrpUI(newTask As String, code As Stri";
RDebugUtils.currentLine=35454977;
 //BA.debugLineNum = 35454977;BA.debugLine="Dim taskPNL As Panel";
_taskpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=35454978;
 //BA.debugLineNum = 35454978;BA.debugLine="taskPNL.Initialize(\"taskPNLGrp\")";
_taskpnl.Initialize(mostCurrent.activityBA,"taskPNLGrp");
RDebugUtils.currentLine=35454979;
 //BA.debugLineNum = 35454979;BA.debugLine="taskPNL.SetLayout(0, 0, 250dip, 60dip)";
_taskpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=35454981;
 //BA.debugLineNum = 35454981;BA.debugLine="Dim taskCheckbox As CheckBox";
_taskcheckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
RDebugUtils.currentLine=35454982;
 //BA.debugLineNum = 35454982;BA.debugLine="taskCheckbox.Initialize(\"taskCheckboxGrp\")";
_taskcheckbox.Initialize(mostCurrent.activityBA,"taskCheckboxGrp");
RDebugUtils.currentLine=35454983;
 //BA.debugLineNum = 35454983;BA.debugLine="taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip,";
_taskpnl.AddView((android.view.View)(_taskcheckbox.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=35454985;
 //BA.debugLineNum = 35454985;BA.debugLine="Dim taskLBL As Label";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=35454986;
 //BA.debugLineNum = 35454986;BA.debugLine="taskLBL.Initialize(\"taskLBLGrp\")";
_tasklbl.Initialize(mostCurrent.activityBA,"taskLBLGrp");
RDebugUtils.currentLine=35454987;
 //BA.debugLineNum = 35454987;BA.debugLine="taskLBL.Text = newTask";
_tasklbl.setText(BA.ObjectToCharSequence(_newtask));
RDebugUtils.currentLine=35454988;
 //BA.debugLineNum = 35454988;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=35454989;
 //BA.debugLineNum = 35454989;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=35454991;
 //BA.debugLineNum = 35454991;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
RDebugUtils.currentLine=35454993;
 //BA.debugLineNum = 35454993;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.Wi";
_taskpnl.AddView((android.view.View)(_tasklbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (_taskpnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),_taskpnl.getHeight());
RDebugUtils.currentLine=35454995;
 //BA.debugLineNum = 35454995;BA.debugLine="Dim divider As Panel";
_divider = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=35454996;
 //BA.debugLineNum = 35454996;BA.debugLine="divider.Initialize(\"line\")";
_divider.Initialize(mostCurrent.activityBA,"line");
RDebugUtils.currentLine=35454997;
 //BA.debugLineNum = 35454997;BA.debugLine="divider.Color = Colors.ARGB(255, 60, 60, 60)";
_divider.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (60),(int) (60),(int) (60)));
RDebugUtils.currentLine=35454998;
 //BA.debugLineNum = 35454998;BA.debugLine="taskPNL.AddView(divider, 0, 59dip, taskPNL.Width,";
_taskpnl.AddView((android.view.View)(_divider.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (59)),_taskpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
RDebugUtils.currentLine=35455001;
 //BA.debugLineNum = 35455001;BA.debugLine="Dim cbCtx As List";
_cbctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=35455002;
 //BA.debugLineNum = 35455002;BA.debugLine="cbCtx.Initialize";
_cbctx.Initialize();
RDebugUtils.currentLine=35455003;
 //BA.debugLineNum = 35455003;BA.debugLine="cbCtx.Add(code)";
_cbctx.Add((Object)(_code));
RDebugUtils.currentLine=35455004;
 //BA.debugLineNum = 35455004;BA.debugLine="cbCtx.Add(currentGrpList)";
_cbctx.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=35455005;
 //BA.debugLineNum = 35455005;BA.debugLine="cbCtx.Add(taskLBL)";
_cbctx.Add((Object)(_tasklbl.getObject()));
RDebugUtils.currentLine=35455006;
 //BA.debugLineNum = 35455006;BA.debugLine="taskCheckbox.Tag = cbCtx";
_taskcheckbox.setTag((Object)(_cbctx.getObject()));
RDebugUtils.currentLine=35455009;
 //BA.debugLineNum = 35455009;BA.debugLine="Dim checkedKey As String = \"group_checked_\" & cod";
_checkedkey = "group_checked_"+_code+"_"+_currentgrplist+"_"+_newtask;
RDebugUtils.currentLine=35455010;
 //BA.debugLineNum = 35455010;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=35455011;
 //BA.debugLineNum = 35455011;BA.debugLine="Dim isChecked As Boolean = kvs.Get(checkedKey)";
_ischecked = BA.ObjectToBoolean(_kvs._get(_checkedkey));
RDebugUtils.currentLine=35455012;
 //BA.debugLineNum = 35455012;BA.debugLine="taskCheckbox.Checked = isChecked";
_taskcheckbox.setChecked(_ischecked);
RDebugUtils.currentLine=35455013;
 //BA.debugLineNum = 35455013;BA.debugLine="If isChecked Then taskLBL.TextColor = Colors.ARG";
if (_ischecked) { 
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (0),(int) (0),(int) (0)));};
 };
RDebugUtils.currentLine=35455016;
 //BA.debugLineNum = 35455016;BA.debugLine="tasksListGrp.Add(taskPNL, newTask)";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_taskpnl.getObject())),(Object)(_newtask));
RDebugUtils.currentLine=35455017;
 //BA.debugLineNum = 35455017;BA.debugLine="End Sub";
return "";
}
public static String  _newaddtaskbtngrp(String _code,String _currentgrplist) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newaddtaskbtngrp", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newaddtaskbtngrp", new Object[] {_code,_currentgrplist}));}
anywheresoftware.b4a.objects.PanelWrapper _addbtnpnl = null;
anywheresoftware.b4a.objects.ButtonWrapper _addbtn = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.collections.List _ctx = null;
RDebugUtils.currentLine=35127296;
 //BA.debugLineNum = 35127296;BA.debugLine="Sub newAddTaskBtnGrp(code As String, currentGrpLis";
RDebugUtils.currentLine=35127297;
 //BA.debugLineNum = 35127297;BA.debugLine="Dim addBtnPNL As Panel";
_addbtnpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=35127298;
 //BA.debugLineNum = 35127298;BA.debugLine="addBtnPNL.Initialize(\"addTaskBtnPNLGrp\")";
_addbtnpnl.Initialize(mostCurrent.activityBA,"addTaskBtnPNLGrp");
RDebugUtils.currentLine=35127299;
 //BA.debugLineNum = 35127299;BA.debugLine="addBtnPNL.SetLayout(10dip, 0dip, 190dip, 70dip)";
_addbtnpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=35127300;
 //BA.debugLineNum = 35127300;BA.debugLine="addBtnPNL.Color = Colors.Transparent";
_addbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=35127302;
 //BA.debugLineNum = 35127302;BA.debugLine="Dim addBtn As Button";
_addbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=35127303;
 //BA.debugLineNum = 35127303;BA.debugLine="addBtn.Initialize(\"addTaskBtnGrp\")";
_addbtn.Initialize(mostCurrent.activityBA,"addTaskBtnGrp");
RDebugUtils.currentLine=35127304;
 //BA.debugLineNum = 35127304;BA.debugLine="addBtn.Text = \"+ add a task\"";
_addbtn.setText(BA.ObjectToCharSequence("+ add a task"));
RDebugUtils.currentLine=35127306;
 //BA.debugLineNum = 35127306;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=35127307;
 //BA.debugLineNum = 35127307;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=35127308;
 //BA.debugLineNum = 35127308;BA.debugLine="addBtn.Background = cd";
_addbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=35127309;
 //BA.debugLineNum = 35127309;BA.debugLine="addBtn.TextColor = Colors.White";
_addbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=35127312;
 //BA.debugLineNum = 35127312;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=35127313;
 //BA.debugLineNum = 35127313;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=35127314;
 //BA.debugLineNum = 35127314;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=35127315;
 //BA.debugLineNum = 35127315;BA.debugLine="ctx.Add(currentGrpList)";
_ctx.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=35127316;
 //BA.debugLineNum = 35127316;BA.debugLine="addBtn.Tag = ctx";
_addbtn.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=35127318;
 //BA.debugLineNum = 35127318;BA.debugLine="addBtnPNL.AddView(addBtn, 10dip, 20dip, addBtnPNL";
_addbtnpnl.AddView((android.view.View)(_addbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),_addbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=35127319;
 //BA.debugLineNum = 35127319;BA.debugLine="tasksListGrp.Add(addBtnPNL, \"\")";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_addbtnpnl.getObject())),(Object)(""));
RDebugUtils.currentLine=35127320;
 //BA.debugLineNum = 35127320;BA.debugLine="End Sub";
return "";
}
public static String  _updateprogressgrp(String _code,String _currentgrplist) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updateprogressgrp", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updateprogressgrp", new Object[] {_code,_currentgrplist}));}
String _taskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
int _totaltasks = 0;
int _donetasks = 0;
String _task = "";
String _checkedkey = "";
int _pct = 0;
RDebugUtils.currentLine=35586048;
 //BA.debugLineNum = 35586048;BA.debugLine="Sub updateProgressGrp(code As String, currentGrpLi";
RDebugUtils.currentLine=35586049;
 //BA.debugLineNum = 35586049;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=35586050;
 //BA.debugLineNum = 35586050;BA.debugLine="If kvs.ContainsKey(taskKey) = False Then";
if (_kvs._containskey(_taskkey)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=35586051;
 //BA.debugLineNum = 35586051;BA.debugLine="progressBarGrp.Progress = 0";
mostCurrent._progressbargrp.setProgress((int) (0));
RDebugUtils.currentLine=35586052;
 //BA.debugLineNum = 35586052;BA.debugLine="progressNumberGrp.Text = \"0 / 0 tasks done!\"";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence("0 / 0 tasks done!"));
RDebugUtils.currentLine=35586053;
 //BA.debugLineNum = 35586053;BA.debugLine="progressPercentGrp.Text = \"0%\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence("0%"));
RDebugUtils.currentLine=35586054;
 //BA.debugLineNum = 35586054;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=35586057;
 //BA.debugLineNum = 35586057;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=35586058;
 //BA.debugLineNum = 35586058;BA.debugLine="Dim totalTasks As Int = savedTasks.Size";
_totaltasks = _savedtasks.getSize();
RDebugUtils.currentLine=35586059;
 //BA.debugLineNum = 35586059;BA.debugLine="Dim doneTasks As Int = 0";
_donetasks = (int) (0);
RDebugUtils.currentLine=35586061;
 //BA.debugLineNum = 35586061;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group11 = _savedtasks;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_task = BA.ObjectToString(group11.Get(index11));
RDebugUtils.currentLine=35586062;
 //BA.debugLineNum = 35586062;BA.debugLine="Dim checkedKey As String = \"group_checked_\" & co";
_checkedkey = "group_checked_"+_code+"_"+_currentgrplist+"_"+_task;
RDebugUtils.currentLine=35586063;
 //BA.debugLineNum = 35586063;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=35586064;
 //BA.debugLineNum = 35586064;BA.debugLine="If kvs.Get(checkedKey) = True Then doneTasks =";
if ((_kvs._get(_checkedkey)).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
_donetasks = (int) (_donetasks+1);};
 };
 }
};
RDebugUtils.currentLine=35586068;
 //BA.debugLineNum = 35586068;BA.debugLine="Dim pct As Int = 0";
_pct = (int) (0);
RDebugUtils.currentLine=35586069;
 //BA.debugLineNum = 35586069;BA.debugLine="If totalTasks > 0 Then pct = (doneTasks / totalTa";
if (_totaltasks>0) { 
_pct = (int) ((_donetasks/(double)_totaltasks)*100);};
RDebugUtils.currentLine=35586071;
 //BA.debugLineNum = 35586071;BA.debugLine="progressNumberGrp.Text = doneTasks & \" / \" & tota";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence(BA.NumberToString(_donetasks)+" / "+BA.NumberToString(_totaltasks)+" tasks done!"));
RDebugUtils.currentLine=35586072;
 //BA.debugLineNum = 35586072;BA.debugLine="progressPercentGrp.Text = pct & \"%\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence(BA.NumberToString(_pct)+"%"));
RDebugUtils.currentLine=35586073;
 //BA.debugLineNum = 35586073;BA.debugLine="progressBarGrp.Progress = pct";
mostCurrent._progressbargrp.setProgress(_pct);
RDebugUtils.currentLine=35586074;
 //BA.debugLineNum = 35586074;BA.debugLine="End Sub";
return "";
}
public static String  _generategroupcode(int _length) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "generategroupcode", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "generategroupcode", new Object[] {_length}));}
String _chars = "";
String _code = "";
int _i = 0;
RDebugUtils.currentLine=34209792;
 //BA.debugLineNum = 34209792;BA.debugLine="Sub generateGroupCode (length As Int) As String";
RDebugUtils.currentLine=34209793;
 //BA.debugLineNum = 34209793;BA.debugLine="Dim chars As String = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
RDebugUtils.currentLine=34209794;
 //BA.debugLineNum = 34209794;BA.debugLine="Dim code As String = \"\"";
_code = "";
RDebugUtils.currentLine=34209795;
 //BA.debugLineNum = 34209795;BA.debugLine="For i = 0 To length - 1";
{
final int step3 = 1;
final int limit3 = (int) (_length-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=34209796;
 //BA.debugLineNum = 34209796;BA.debugLine="code = code & (chars.CharAt(Rnd(0,chars.Length))";
_code = _code+BA.ObjectToString((_chars.charAt(anywheresoftware.b4a.keywords.Common.Rnd((int) (0),_chars.length()))));
 }
};
RDebugUtils.currentLine=34209798;
 //BA.debugLineNum = 34209798;BA.debugLine="Return code";
if (true) return _code;
RDebugUtils.currentLine=34209799;
 //BA.debugLineNum = 34209799;BA.debugLine="End Sub";
return "";
}
public static String  _getcurrentgrplist() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getcurrentgrplist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getcurrentgrplist", null));}
RDebugUtils.currentLine=35651584;
 //BA.debugLineNum = 35651584;BA.debugLine="Sub getCurrentGrpList As String";
RDebugUtils.currentLine=35651585;
 //BA.debugLineNum = 35651585;BA.debugLine="Return currentGrpListName   ' module-level var yo";
if (true) return mostCurrent._currentgrplistname;
RDebugUtils.currentLine=35651586;
 //BA.debugLineNum = 35651586;BA.debugLine="End Sub";
return "";
}
public static String  _groupet_enterpressed() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "groupet_enterpressed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "groupet_enterpressed", null));}
anywheresoftware.b4a.objects.collections.List _ctx = null;
String _code = "";
String _newname = "";
String _groupname = "";
anywheresoftware.b4a.objects.collections.List _allgroups = null;
anywheresoftware.b4a.objects.collections.List _members = null;
RDebugUtils.currentLine=34144256;
 //BA.debugLineNum = 34144256;BA.debugLine="Sub groupET_EnterPressed";
RDebugUtils.currentLine=34144257;
 //BA.debugLineNum = 34144257;BA.debugLine="If groupET.Tag Is List Then";
if (mostCurrent._groupet.getTag() instanceof java.util.List) { 
RDebugUtils.currentLine=34144259;
 //BA.debugLineNum = 34144259;BA.debugLine="Dim ctx As List = groupET.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(mostCurrent._groupet.getTag()));
RDebugUtils.currentLine=34144260;
 //BA.debugLineNum = 34144260;BA.debugLine="Dim code As String = ctx.Get(1)";
_code = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=34144261;
 //BA.debugLineNum = 34144261;BA.debugLine="Dim newName As String = groupET.Text.Trim";
_newname = mostCurrent._groupet.getText().trim();
RDebugUtils.currentLine=34144262;
 //BA.debugLineNum = 34144262;BA.debugLine="If newName = \"\" Then";
if ((_newname).equals("")) { 
RDebugUtils.currentLine=34144263;
 //BA.debugLineNum = 34144263;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_kvs._get("group_name_"+_code)));
RDebugUtils.currentLine=34144264;
 //BA.debugLineNum = 34144264;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34144265;
 //BA.debugLineNum = 34144265;BA.debugLine="groupET.Tag = code";
mostCurrent._groupet.setTag((Object)(_code));
RDebugUtils.currentLine=34144266;
 //BA.debugLineNum = 34144266;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34144268;
 //BA.debugLineNum = 34144268;BA.debugLine="kvs.Put(\"group_name_\" & code, newName)";
_kvs._put("group_name_"+_code,(Object)(_newname));
RDebugUtils.currentLine=34144269;
 //BA.debugLineNum = 34144269;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34144270;
 //BA.debugLineNum = 34144270;BA.debugLine="groupET.Tag = code";
mostCurrent._groupet.setTag((Object)(_code));
RDebugUtils.currentLine=34144271;
 //BA.debugLineNum = 34144271;BA.debugLine="loadMyGroups";
_loadmygroups();
RDebugUtils.currentLine=34144272;
 //BA.debugLineNum = 34144272;BA.debugLine="groupET.Text = newName";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_newname));
RDebugUtils.currentLine=34144273;
 //BA.debugLineNum = 34144273;BA.debugLine="ToastMessageShow(\"Group renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Group renamed"),anywheresoftware.b4a.keywords.Common.False);
 }else 
{RDebugUtils.currentLine=34144275;
 //BA.debugLineNum = 34144275;BA.debugLine="Else If groupET.Tag = \"creating\" Then";
if ((mostCurrent._groupet.getTag()).equals((Object)("creating"))) { 
RDebugUtils.currentLine=34144277;
 //BA.debugLineNum = 34144277;BA.debugLine="Dim groupName As String = groupET.Text.Trim";
_groupname = mostCurrent._groupet.getText().trim();
RDebugUtils.currentLine=34144278;
 //BA.debugLineNum = 34144278;BA.debugLine="If groupName = \"\" Then";
if ((_groupname).equals("")) { 
RDebugUtils.currentLine=34144279;
 //BA.debugLineNum = 34144279;BA.debugLine="MsgboxAsync(\"Please enter a group name.\", \"No n";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a group name."),BA.ObjectToCharSequence("No name"),processBA);
RDebugUtils.currentLine=34144280;
 //BA.debugLineNum = 34144280;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34144283;
 //BA.debugLineNum = 34144283;BA.debugLine="Dim code As String = generateGroupCode(6)";
_code = _generategroupcode((int) (6));
RDebugUtils.currentLine=34144285;
 //BA.debugLineNum = 34144285;BA.debugLine="Dim allGroups As List";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34144286;
 //BA.debugLineNum = 34144286;BA.debugLine="allGroups.Initialize";
_allgroups.Initialize();
RDebugUtils.currentLine=34144287;
 //BA.debugLineNum = 34144287;BA.debugLine="If kvs.ContainsKey(\"groups\") Then allGroups = kv";
if (_kvs._containskey("groups")) { 
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));};
RDebugUtils.currentLine=34144288;
 //BA.debugLineNum = 34144288;BA.debugLine="allGroups.Add(code)";
_allgroups.Add((Object)(_code));
RDebugUtils.currentLine=34144289;
 //BA.debugLineNum = 34144289;BA.debugLine="kvs.Put(\"groups\", allGroups)";
_kvs._put("groups",(Object)(_allgroups.getObject()));
RDebugUtils.currentLine=34144291;
 //BA.debugLineNum = 34144291;BA.debugLine="kvs.Put(\"group_name_\" & code, groupName)";
_kvs._put("group_name_"+_code,(Object)(_groupname));
RDebugUtils.currentLine=34144292;
 //BA.debugLineNum = 34144292;BA.debugLine="kvs.Put(\"group_owner_\" & code, Starter.currentUs";
_kvs._put("group_owner_"+_code,(Object)(mostCurrent._starter._currentuserid /*String*/ ));
RDebugUtils.currentLine=34144294;
 //BA.debugLineNum = 34144294;BA.debugLine="Dim members As List";
_members = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34144295;
 //BA.debugLineNum = 34144295;BA.debugLine="members.Initialize";
_members.Initialize();
RDebugUtils.currentLine=34144296;
 //BA.debugLineNum = 34144296;BA.debugLine="members.Add(Starter.currentUserID)";
_members.Add((Object)(mostCurrent._starter._currentuserid /*String*/ ));
RDebugUtils.currentLine=34144297;
 //BA.debugLineNum = 34144297;BA.debugLine="kvs.Put(\"group_members_\" & code, members)";
_kvs._put("group_members_"+_code,(Object)(_members.getObject()));
RDebugUtils.currentLine=34144299;
 //BA.debugLineNum = 34144299;BA.debugLine="groupET.Tag = Null";
mostCurrent._groupet.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=34144300;
 //BA.debugLineNum = 34144300;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34144302;
 //BA.debugLineNum = 34144302;BA.debugLine="ToastMessageShow(\"Group \"\"\" & groupName & \"\"\" cr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Group \""+_groupname+"\" created! Code: "+_code),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34144303;
 //BA.debugLineNum = 34144303;BA.debugLine="loadMyGroups";
_loadmygroups();
 }}
;
RDebugUtils.currentLine=34144305;
 //BA.debugLineNum = 34144305;BA.debugLine="End Sub";
return "";
}
public static String  _grouplist_itemclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "grouplist_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "grouplist_itemclick", new Object[] {_index,_value}));}
String _code = "";
String _listskey = "";
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _listname = "";
RDebugUtils.currentLine=34471936;
 //BA.debugLineNum = 34471936;BA.debugLine="Sub groupList_ItemClick(Index As Int, Value As Obj";
RDebugUtils.currentLine=34471937;
 //BA.debugLineNum = 34471937;BA.debugLine="Dim code As String = Value";
_code = BA.ObjectToString(_value);
RDebugUtils.currentLine=34471938;
 //BA.debugLineNum = 34471938;BA.debugLine="If code = \"joinPanel\" Then Return";
if ((_code).equals("joinPanel")) { 
if (true) return "";};
RDebugUtils.currentLine=34471941;
 //BA.debugLineNum = 34471941;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_kvs._get("group_name_"+_code)));
RDebugUtils.currentLine=34471942;
 //BA.debugLineNum = 34471942;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34471943;
 //BA.debugLineNum = 34471943;BA.debugLine="groupET.Tag = code   ' store current group code i";
mostCurrent._groupet.setTag((Object)(_code));
RDebugUtils.currentLine=34471946;
 //BA.debugLineNum = 34471946;BA.debugLine="listsListGrp.Clear";
mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=34471947;
 //BA.debugLineNum = 34471947;BA.debugLine="listsListGrp.GetBase.Visible = True";
mostCurrent._listslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34471948;
 //BA.debugLineNum = 34471948;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=34471949;
 //BA.debugLineNum = 34471949;BA.debugLine="tasksListGrp.GetBase.Visible = False";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34471951;
 //BA.debugLineNum = 34471951;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=34471952;
 //BA.debugLineNum = 34471952;BA.debugLine="If kvs.ContainsKey(listsKey) Then";
if (_kvs._containskey(_listskey)) { 
RDebugUtils.currentLine=34471953;
 //BA.debugLineNum = 34471953;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));
RDebugUtils.currentLine=34471954;
 //BA.debugLineNum = 34471954;BA.debugLine="For Each listName As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group13 = _savedlists;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_listname = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=34471955;
 //BA.debugLineNum = 34471955;BA.debugLine="listsListGrp.AddTextItem(listName, listName)";
mostCurrent._listslistgrp._addtextitem((Object)(_listname),(Object)(_listname));
 }
};
 };
RDebugUtils.currentLine=34471959;
 //BA.debugLineNum = 34471959;BA.debugLine="progressBarGrp.Progress = 0";
mostCurrent._progressbargrp.setProgress((int) (0));
RDebugUtils.currentLine=34471960;
 //BA.debugLineNum = 34471960;BA.debugLine="progressNumberGrp.Text = \"\"";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34471961;
 //BA.debugLineNum = 34471961;BA.debugLine="progressPercentGrp.Text = \"\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34471962;
 //BA.debugLineNum = 34471962;BA.debugLine="End Sub";
return "";
}
public static void  _grouplist_itemlongclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "grouplist_itemlongclick", false))
	 {Debug.delegate(mostCurrent.activityBA, "grouplist_itemlongclick", new Object[] {_index,_value}); return;}
ResumableSub_groupList_ItemLongClick rsub = new ResumableSub_groupList_ItemLongClick(null,_index,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_groupList_ItemLongClick extends BA.ResumableSub {
public ResumableSub_groupList_ItemLongClick(b4a.example.todoactivity parent,int _index,Object _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
b4a.example.todoactivity parent;
int _index;
Object _value;
String _code = "";
int _res = 0;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _clipmanager = null;
anywheresoftware.b4j.object.JavaObject _clipdata = null;
String _ownerkey = "";
int _res2 = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="todoactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=34537473;
 //BA.debugLineNum = 34537473;BA.debugLine="If Value = \"joinPanel\" Then Return";
if (true) break;

case 1:
//if
this.state = 6;
if ((_value).equals((Object)("joinPanel"))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
RDebugUtils.currentLine=34537475;
 //BA.debugLineNum = 34537475;BA.debugLine="Dim code As String = Value";
_code = BA.ObjectToString(_value);
RDebugUtils.currentLine=34537476;
 //BA.debugLineNum = 34537476;BA.debugLine="Msgbox2Async(\"What do you want to do with this gr";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("What do you want to do with this group?"),BA.ObjectToCharSequence(parent._kvs._get("group_name_"+_code)),"Rename","Copy Invite Code","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34537477;
 //BA.debugLineNum = 34537477;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "grouplist_itemlongclick"), null);
this.state = 27;
return;
case 27:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=34537479;
 //BA.debugLineNum = 34537479;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 26;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=34537483;
 //BA.debugLineNum = 34537483;BA.debugLine="Else If res = DialogResponse.CANCEL Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
this.state = 11;
}else 
{RDebugUtils.currentLine=34537493;
 //BA.debugLineNum = 34537493;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 13;
}}}
if (true) break;

case 9:
//C
this.state = 26;
RDebugUtils.currentLine=34537481;
 //BA.debugLineNum = 34537481;BA.debugLine="showRenameGroupPanel(Index, code)";
_showrenamegrouppanel(_index,_code);
 if (true) break;

case 11:
//C
this.state = 26;
RDebugUtils.currentLine=34537485;
 //BA.debugLineNum = 34537485;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=34537486;
 //BA.debugLineNum = 34537486;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext(processBA);
RDebugUtils.currentLine=34537487;
 //BA.debugLineNum = 34537487;BA.debugLine="Dim clipManager As JavaObject = jo.RunMethod(\"ge";
_clipmanager = new anywheresoftware.b4j.object.JavaObject();
_clipmanager = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.RunMethod("getSystemService",new Object[]{(Object)("clipboard")})));
RDebugUtils.currentLine=34537488;
 //BA.debugLineNum = 34537488;BA.debugLine="Dim clipData As JavaObject";
_clipdata = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=34537489;
 //BA.debugLineNum = 34537489;BA.debugLine="clipData.InitializeStatic(\"android.content.ClipD";
_clipdata.InitializeStatic("android.content.ClipData");
RDebugUtils.currentLine=34537490;
 //BA.debugLineNum = 34537490;BA.debugLine="clipManager.RunMethod(\"setPrimaryClip\", Array(cl";
_clipmanager.RunMethod("setPrimaryClip",new Object[]{_clipdata.RunMethod("newPlainText",new Object[]{(Object)("Group Code"),(Object)(_code)})});
RDebugUtils.currentLine=34537491;
 //BA.debugLineNum = 34537491;BA.debugLine="ToastMessageShow(\"Invite code copied: \" & code,";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Invite code copied: "+_code),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 13:
//C
this.state = 14;
RDebugUtils.currentLine=34537495;
 //BA.debugLineNum = 34537495;BA.debugLine="Dim ownerKey As String = \"group_owner_\" & code";
_ownerkey = "group_owner_"+_code;
RDebugUtils.currentLine=34537496;
 //BA.debugLineNum = 34537496;BA.debugLine="If kvs.ContainsKey(ownerKey) Then";
if (true) break;

case 14:
//if
this.state = 21;
if (parent._kvs._containskey(_ownerkey)) { 
this.state = 16;
}if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=34537497;
 //BA.debugLineNum = 34537497;BA.debugLine="If kvs.Get(ownerKey) <> Starter.currentUserID T";
if (true) break;

case 17:
//if
this.state = 20;
if ((parent._kvs._get(_ownerkey)).equals((Object)(parent.mostCurrent._starter._currentuserid /*String*/ )) == false) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
RDebugUtils.currentLine=34537498;
 //BA.debugLineNum = 34537498;BA.debugLine="MsgboxAsync(\"Only the group creator can delete";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Only the group creator can delete the group."),BA.ObjectToCharSequence("Not allowed"),processBA);
RDebugUtils.currentLine=34537499;
 //BA.debugLineNum = 34537499;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 20:
//C
this.state = 21;
;
 if (true) break;

case 21:
//C
this.state = 22;
;
RDebugUtils.currentLine=34537503;
 //BA.debugLineNum = 34537503;BA.debugLine="Msgbox2Async(\"Delete this group and all its data";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete this group and all its data?"),BA.ObjectToCharSequence("Confirm"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34537504;
 //BA.debugLineNum = 34537504;BA.debugLine="Wait For Msgbox_Result (res2 As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "grouplist_itemlongclick"), null);
this.state = 28;
return;
case 28:
//C
this.state = 22;
_res2 = (Integer) result[0];
;
RDebugUtils.currentLine=34537505;
 //BA.debugLineNum = 34537505;BA.debugLine="If res2 = DialogResponse.NEGATIVE Then";
if (true) break;

case 22:
//if
this.state = 25;
if (_res2==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 24;
}if (true) break;

case 24:
//C
this.state = 25;
RDebugUtils.currentLine=34537506;
 //BA.debugLineNum = 34537506;BA.debugLine="deleteGroup(code)";
_deletegroup(_code);
RDebugUtils.currentLine=34537507;
 //BA.debugLineNum = 34537507;BA.debugLine="loadMyGroups";
_loadmygroups();
 if (true) break;

case 25:
//C
this.state = 26;
;
 if (true) break;

case 26:
//C
this.state = -1;
;
RDebugUtils.currentLine=34537510;
 //BA.debugLineNum = 34537510;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _showrenamegrouppanel(int _index,String _code) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showrenamegrouppanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showrenamegrouppanel", new Object[] {_index,_code}));}
anywheresoftware.b4a.objects.collections.List _ctx = null;
RDebugUtils.currentLine=34603008;
 //BA.debugLineNum = 34603008;BA.debugLine="Sub showRenameGroupPanel(Index As Int, code As Str";
RDebugUtils.currentLine=34603009;
 //BA.debugLineNum = 34603009;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_kvs._get("group_name_"+_code)));
RDebugUtils.currentLine=34603010;
 //BA.debugLineNum = 34603010;BA.debugLine="groupET.Enabled = True";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34603011;
 //BA.debugLineNum = 34603011;BA.debugLine="groupET.RequestFocus";
mostCurrent._groupet.RequestFocus();
RDebugUtils.currentLine=34603012;
 //BA.debugLineNum = 34603012;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34603013;
 //BA.debugLineNum = 34603013;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=34603014;
 //BA.debugLineNum = 34603014;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=34603015;
 //BA.debugLineNum = 34603015;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=34603016;
 //BA.debugLineNum = 34603016;BA.debugLine="groupET.Tag = ctx   ' Tag is now a List, not \"cre";
mostCurrent._groupet.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=34603017;
 //BA.debugLineNum = 34603017;BA.debugLine="End Sub";
return "";
}
public static boolean  _iscodeunique(String _code) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "iscodeunique", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "iscodeunique", new Object[] {_code}));}
anywheresoftware.b4a.objects.collections.List _allgroups = null;
String _c = "";
RDebugUtils.currentLine=34275328;
 //BA.debugLineNum = 34275328;BA.debugLine="Sub isCodeUnique(code As String) As Boolean";
RDebugUtils.currentLine=34275329;
 //BA.debugLineNum = 34275329;BA.debugLine="If kvs.ContainsKey(\"groups\") = False Then Return";
if (_kvs._containskey("groups")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=34275330;
 //BA.debugLineNum = 34275330;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));
RDebugUtils.currentLine=34275331;
 //BA.debugLineNum = 34275331;BA.debugLine="For Each c As String In allGroups";
{
final anywheresoftware.b4a.BA.IterableList group3 = _allgroups;
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_c = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=34275332;
 //BA.debugLineNum = 34275332;BA.debugLine="If c = code Then Return False";
if ((_c).equals(_code)) { 
if (true) return anywheresoftware.b4a.keywords.Common.False;};
 }
};
RDebugUtils.currentLine=34275334;
 //BA.debugLineNum = 34275334;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=34275335;
 //BA.debugLineNum = 34275335;BA.debugLine="End Sub";
return false;
}
public static String  _joinbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "joinbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "joinbtn_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _joinbtn = null;
anywheresoftware.b4a.objects.EditTextWrapper _joinet = null;
String _code = "";
String _memberskey = "";
anywheresoftware.b4a.objects.collections.List _members = null;
String _m = "";
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
String _groupname = "";
RDebugUtils.currentLine=34406400;
 //BA.debugLineNum = 34406400;BA.debugLine="Sub joinBtn_Click";
RDebugUtils.currentLine=34406401;
 //BA.debugLineNum = 34406401;BA.debugLine="Dim joinBtn As Button = Sender";
_joinbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
_joinbtn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=34406402;
 //BA.debugLineNum = 34406402;BA.debugLine="Dim joinET As EditText = joinBtn.Tag";
_joinet = new anywheresoftware.b4a.objects.EditTextWrapper();
_joinet = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_joinbtn.getTag()));
RDebugUtils.currentLine=34406403;
 //BA.debugLineNum = 34406403;BA.debugLine="Dim code As String = joinET.Text.Trim.ToUpperCase";
_code = _joinet.getText().trim().toUpperCase();
RDebugUtils.currentLine=34406405;
 //BA.debugLineNum = 34406405;BA.debugLine="If code.Length <> 6 Then";
if (_code.length()!=6) { 
RDebugUtils.currentLine=34406406;
 //BA.debugLineNum = 34406406;BA.debugLine="MsgboxAsync(\"Code must be exactly 6 characters.\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Code must be exactly 6 characters."),BA.ObjectToCharSequence("Invalid code"),processBA);
RDebugUtils.currentLine=34406407;
 //BA.debugLineNum = 34406407;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34406410;
 //BA.debugLineNum = 34406410;BA.debugLine="If kvs.ContainsKey(\"group_name_\" & code) = False";
if (_kvs._containskey("group_name_"+_code)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=34406411;
 //BA.debugLineNum = 34406411;BA.debugLine="MsgboxAsync(\"No group found with that code.\", \"N";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No group found with that code."),BA.ObjectToCharSequence("Not found"),processBA);
RDebugUtils.currentLine=34406412;
 //BA.debugLineNum = 34406412;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34406416;
 //BA.debugLineNum = 34406416;BA.debugLine="Dim membersKey As String = \"group_members_\" & cod";
_memberskey = "group_members_"+_code;
RDebugUtils.currentLine=34406417;
 //BA.debugLineNum = 34406417;BA.debugLine="Dim members As List = kvs.Get(membersKey)";
_members = new anywheresoftware.b4a.objects.collections.List();
_members = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_memberskey)));
RDebugUtils.currentLine=34406418;
 //BA.debugLineNum = 34406418;BA.debugLine="For Each m As String In members";
{
final anywheresoftware.b4a.BA.IterableList group14 = _members;
final int groupLen14 = group14.getSize()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_m = BA.ObjectToString(group14.Get(index14));
RDebugUtils.currentLine=34406419;
 //BA.debugLineNum = 34406419;BA.debugLine="If m = Starter.currentUserID Then";
if ((_m).equals(mostCurrent._starter._currentuserid /*String*/ )) { 
RDebugUtils.currentLine=34406420;
 //BA.debugLineNum = 34406420;BA.debugLine="MsgboxAsync(\"You are already in this group.\", \"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You are already in this group."),BA.ObjectToCharSequence("Already joined"),processBA);
RDebugUtils.currentLine=34406421;
 //BA.debugLineNum = 34406421;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=34406425;
 //BA.debugLineNum = 34406425;BA.debugLine="members.Add(Starter.currentUserID)";
_members.Add((Object)(mostCurrent._starter._currentuserid /*String*/ ));
RDebugUtils.currentLine=34406426;
 //BA.debugLineNum = 34406426;BA.debugLine="kvs.Put(membersKey, members)";
_kvs._put(_memberskey,(Object)(_members.getObject()));
RDebugUtils.currentLine=34406430;
 //BA.debugLineNum = 34406430;BA.debugLine="For i = 0 To groupList.Size - 1";
{
final int step22 = 1;
final int limit22 = (int) (mostCurrent._grouplist._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit22 ;_i = _i + step22 ) {
RDebugUtils.currentLine=34406431;
 //BA.debugLineNum = 34406431;BA.debugLine="Dim pnl As B4XView = groupList.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._grouplist._getpanel(_i);
 }
};
RDebugUtils.currentLine=34406434;
 //BA.debugLineNum = 34406434;BA.debugLine="groupList.RemoveAt(groupList.Size - 1)";
mostCurrent._grouplist._removeat((int) (mostCurrent._grouplist._getsize()-1));
RDebugUtils.currentLine=34406436;
 //BA.debugLineNum = 34406436;BA.debugLine="Dim groupName As String = kvs.Get(\"group_name_\" &";
_groupname = BA.ObjectToString(_kvs._get("group_name_"+_code));
RDebugUtils.currentLine=34406437;
 //BA.debugLineNum = 34406437;BA.debugLine="ToastMessageShow(\"Joined group: \" & groupName, Tr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Joined group: "+_groupname),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34406438;
 //BA.debugLineNum = 34406438;BA.debugLine="loadMyGroups";
_loadmygroups();
RDebugUtils.currentLine=34406439;
 //BA.debugLineNum = 34406439;BA.debugLine="End Sub";
return "";
}
public static String  _listslist_itemclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listslist_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listslist_itemclick", new Object[] {_index,_value}));}
anywheresoftware.b4a.objects.B4XViewWrapper _listpnl = null;
anywheresoftware.b4a.objects.LabelWrapper _listlbl = null;
String _key = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
RDebugUtils.currentLine=3801088;
 //BA.debugLineNum = 3801088;BA.debugLine="Sub listsList_ItemClick(Index As Int, Value As Obj";
RDebugUtils.currentLine=3801090;
 //BA.debugLineNum = 3801090;BA.debugLine="If isAddingList Then Return";
if (_isaddinglist) { 
if (true) return "";};
RDebugUtils.currentLine=3801092;
 //BA.debugLineNum = 3801092;BA.debugLine="Dim listPNL As B4XView = listsList.GetPanel(Index";
_listpnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_listpnl = mostCurrent._listslist._getpanel(_index);
RDebugUtils.currentLine=3801093;
 //BA.debugLineNum = 3801093;BA.debugLine="Dim listLBL As Label = listPNL.GetView(0)";
_listlbl = new anywheresoftware.b4a.objects.LabelWrapper();
_listlbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_listpnl.GetView((int) (0)).getObject()));
RDebugUtils.currentLine=3801095;
 //BA.debugLineNum = 3801095;BA.debugLine="currentList = listLBL.Text";
mostCurrent._currentlist = _listlbl.getText();
RDebugUtils.currentLine=3801096;
 //BA.debugLineNum = 3801096;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=3801097;
 //BA.debugLineNum = 3801097;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801099;
 //BA.debugLineNum = 3801099;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=3801101;
 //BA.debugLineNum = 3801101;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=3801103;
 //BA.debugLineNum = 3801103;BA.debugLine="If kvs.ContainsKey(key) Then";
if (_kvs._containskey(_key)) { 
RDebugUtils.currentLine=3801104;
 //BA.debugLineNum = 3801104;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
RDebugUtils.currentLine=3801105;
 //BA.debugLineNum = 3801105;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group11 = _savedtasks;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_task = BA.ObjectToString(group11.Get(index11));
RDebugUtils.currentLine=3801106;
 //BA.debugLineNum = 3801106;BA.debugLine="tasksListUI(task)";
_taskslistui(_task);
 }
};
 };
RDebugUtils.currentLine=3801110;
 //BA.debugLineNum = 3801110;BA.debugLine="tasksList.GetBase.Visible = True";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801111;
 //BA.debugLineNum = 3801111;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=3801112;
 //BA.debugLineNum = 3801112;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=3801114;
 //BA.debugLineNum = 3801114;BA.debugLine="End Sub";
return "";
}
public static void  _listslist_itemlongclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listslist_itemlongclick", false))
	 {Debug.delegate(mostCurrent.activityBA, "listslist_itemlongclick", new Object[] {_index,_value}); return;}
ResumableSub_listsList_ItemLongClick rsub = new ResumableSub_listsList_ItemLongClick(null,_index,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_listsList_ItemLongClick extends BA.ResumableSub {
public ResumableSub_listsList_ItemLongClick(b4a.example.todoactivity parent,int _index,Object _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
b4a.example.todoactivity parent;
int _index;
Object _value;
int _res = 0;
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _key = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
anywheresoftware.b4a.BA.IterableList group18;
int index18;
int groupLen18;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="todoactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=3866626;
 //BA.debugLineNum = 3866626;BA.debugLine="Msgbox2Async(\"Delete or rename this list?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this list?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3866627;
 //BA.debugLineNum = 3866627;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslist_itemlongclick"), null);
this.state = 22;
return;
case 22:
//C
this.state = 1;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=3866629;
 //BA.debugLineNum = 3866629;BA.debugLine="If res = DialogResponse.POSITIVE Then ' Rename";
if (true) break;

case 1:
//if
this.state = 21;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}else 
{RDebugUtils.currentLine=3866632;
 //BA.debugLineNum = 3866632;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then ' Dele";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 5;
}}
if (true) break;

case 3:
//C
this.state = 21;
RDebugUtils.currentLine=3866630;
 //BA.debugLineNum = 3866630;BA.debugLine="showRenameListPanel(Index, Value)";
_showrenamelistpanel(_index,BA.ObjectToString(_value));
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=3866634;
 //BA.debugLineNum = 3866634;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete th";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete the list \""+BA.ObjectToString(_value)+"\"?"),BA.ObjectToCharSequence("Confirmation"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3866635;
 //BA.debugLineNum = 3866635;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslist_itemlongclick"), null);
this.state = 23;
return;
case 23:
//C
this.state = 6;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=3866636;
 //BA.debugLineNum = 3866636;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
if (true) break;

case 6:
//if
this.state = 9;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 8;
}if (true) break;

case 8:
//C
this.state = 9;
RDebugUtils.currentLine=3866637;
 //BA.debugLineNum = 3866637;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get("lists")));
RDebugUtils.currentLine=3866638;
 //BA.debugLineNum = 3866638;BA.debugLine="savedLists.RemoveAt(Index)";
_savedlists.RemoveAt(_index);
RDebugUtils.currentLine=3866639;
 //BA.debugLineNum = 3866639;BA.debugLine="kvs.Put(\"lists\", savedLists)";
parent._kvs._put("lists",(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=3866640;
 //BA.debugLineNum = 3866640;BA.debugLine="listsList.RemoveAt(Index)";
parent.mostCurrent._listslist._removeat(_index);
RDebugUtils.currentLine=3866641;
 //BA.debugLineNum = 3866641;BA.debugLine="ToastMessageShow(\"List deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 9:
//C
this.state = 10;
;
RDebugUtils.currentLine=3866645;
 //BA.debugLineNum = 3866645;BA.debugLine="Dim key As String = \"list_\" & Value";
_key = "list_"+BA.ObjectToString(_value);
RDebugUtils.currentLine=3866646;
 //BA.debugLineNum = 3866646;BA.debugLine="If kvs.ContainsKey(key) Then";
if (true) break;

case 10:
//if
this.state = 17;
if (parent._kvs._containskey(_key)) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
RDebugUtils.currentLine=3866647;
 //BA.debugLineNum = 3866647;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_key)));
RDebugUtils.currentLine=3866648;
 //BA.debugLineNum = 3866648;BA.debugLine="For Each task As String In savedTasks";
if (true) break;

case 13:
//for
this.state = 16;
group18 = _savedtasks;
index18 = 0;
groupLen18 = group18.getSize();
this.state = 24;
if (true) break;

case 24:
//C
this.state = 16;
if (index18 < groupLen18) {
this.state = 15;
_task = BA.ObjectToString(group18.Get(index18));}
if (true) break;

case 25:
//C
this.state = 24;
index18++;
if (true) break;

case 15:
//C
this.state = 25;
RDebugUtils.currentLine=3866649;
 //BA.debugLineNum = 3866649;BA.debugLine="kvs.Remove(\"checked_\" & Value & \"_\" & task)";
parent._kvs._remove("checked_"+BA.ObjectToString(_value)+"_"+_task);
 if (true) break;
if (true) break;

case 16:
//C
this.state = 17;
;
RDebugUtils.currentLine=3866651;
 //BA.debugLineNum = 3866651;BA.debugLine="kvs.Remove(key)";
parent._kvs._remove(_key);
 if (true) break;
;
RDebugUtils.currentLine=3866655;
 //BA.debugLineNum = 3866655;BA.debugLine="If currentList = Value Then";

case 17:
//if
this.state = 20;
if ((parent.mostCurrent._currentlist).equals(BA.ObjectToString(_value))) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
RDebugUtils.currentLine=3866656;
 //BA.debugLineNum = 3866656;BA.debugLine="currentList = \"\"";
parent.mostCurrent._currentlist = "";
RDebugUtils.currentLine=3866657;
 //BA.debugLineNum = 3866657;BA.debugLine="tasksList.Clear";
parent.mostCurrent._taskslist._clear();
RDebugUtils.currentLine=3866658;
 //BA.debugLineNum = 3866658;BA.debugLine="tasksList.GetBase.Visible = False";
parent.mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3866659;
 //BA.debugLineNum = 3866659;BA.debugLine="addTitleTextArea.Text = \"\"";
parent.mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3866660;
 //BA.debugLineNum = 3866660;BA.debugLine="addTitleTextArea.Visible = False";
parent.mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 20:
//C
this.state = 21;
;
 if (true) break;

case 21:
//C
this.state = -1;
;
RDebugUtils.currentLine=3866664;
 //BA.debugLineNum = 3866664;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _showrenamelistpanel(int _index,String _oldtitle) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showrenamelistpanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showrenamelistpanel", new Object[] {_index,_oldtitle}));}
anywheresoftware.b4a.objects.collections.List _ctx = null;
RDebugUtils.currentLine=3932160;
 //BA.debugLineNum = 3932160;BA.debugLine="Sub showRenameListPanel(Index As Int, oldTitle As";
RDebugUtils.currentLine=3932162;
 //BA.debugLineNum = 3932162;BA.debugLine="addTitleTextArea.Text = oldTitle";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(_oldtitle));
RDebugUtils.currentLine=3932163;
 //BA.debugLineNum = 3932163;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3932164;
 //BA.debugLineNum = 3932164;BA.debugLine="addTitleTextArea.Enabled = True";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3932165;
 //BA.debugLineNum = 3932165;BA.debugLine="addTitleTextArea.RequestFocus";
mostCurrent._addtitletextarea.RequestFocus();
RDebugUtils.currentLine=3932167;
 //BA.debugLineNum = 3932167;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=3932168;
 //BA.debugLineNum = 3932168;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=3932169;
 //BA.debugLineNum = 3932169;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=3932170;
 //BA.debugLineNum = 3932170;BA.debugLine="ctx.Add(oldTitle)";
_ctx.Add((Object)(_oldtitle));
RDebugUtils.currentLine=3932171;
 //BA.debugLineNum = 3932171;BA.debugLine="addTitleTextArea.Tag = ctx";
mostCurrent._addtitletextarea.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=3932173;
 //BA.debugLineNum = 3932173;BA.debugLine="End Sub";
return "";
}
public static String  _listslistgrp_itemclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listslistgrp_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "listslistgrp_itemclick", new Object[] {_index,_value}));}
String _code = "";
String _currentgrplist = "";
String _taskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
RDebugUtils.currentLine=34865152;
 //BA.debugLineNum = 34865152;BA.debugLine="Sub listsListGrp_ItemClick(Index As Int, Value As";
RDebugUtils.currentLine=34865153;
 //BA.debugLineNum = 34865153;BA.debugLine="If Value = \"newListPanel\" Then Return";
if ((_value).equals((Object)("newListPanel"))) { 
if (true) return "";};
RDebugUtils.currentLine=34865154;
 //BA.debugLineNum = 34865154;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(mostCurrent._groupet.getTag());
RDebugUtils.currentLine=34865155;
 //BA.debugLineNum = 34865155;BA.debugLine="Dim currentGrpList As String = Value";
_currentgrplist = BA.ObjectToString(_value);
RDebugUtils.currentLine=34865157;
 //BA.debugLineNum = 34865157;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=34865158;
 //BA.debugLineNum = 34865158;BA.debugLine="tasksListGrp.GetBase.Visible = True";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34865160;
 //BA.debugLineNum = 34865160;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=34865161;
 //BA.debugLineNum = 34865161;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
if (_kvs._containskey(_taskkey)) { 
RDebugUtils.currentLine=34865162;
 //BA.debugLineNum = 34865162;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=34865163;
 //BA.debugLineNum = 34865163;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group9 = _savedtasks;
final int groupLen9 = group9.getSize()
;int index9 = 0;
;
for (; index9 < groupLen9;index9++){
_task = BA.ObjectToString(group9.Get(index9));
RDebugUtils.currentLine=34865164;
 //BA.debugLineNum = 34865164;BA.debugLine="tasksListGrpUI(task, code, currentGrpList)";
_taskslistgrpui(_task,_code,_currentgrplist);
 }
};
 };
RDebugUtils.currentLine=34865168;
 //BA.debugLineNum = 34865168;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
_newaddtaskbtngrp(_code,_currentgrplist);
RDebugUtils.currentLine=34865169;
 //BA.debugLineNum = 34865169;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=34865170;
 //BA.debugLineNum = 34865170;BA.debugLine="End Sub";
return "";
}
public static void  _listslistgrp_itemlongclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "listslistgrp_itemlongclick", false))
	 {Debug.delegate(mostCurrent.activityBA, "listslistgrp_itemlongclick", new Object[] {_index,_value}); return;}
ResumableSub_listsListGrp_ItemLongClick rsub = new ResumableSub_listsListGrp_ItemLongClick(null,_index,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_listsListGrp_ItemLongClick extends BA.ResumableSub {
public ResumableSub_listsListGrp_ItemLongClick(b4a.example.todoactivity parent,int _index,Object _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
b4a.example.todoactivity parent;
int _index;
Object _value;
String _code = "";
int _res = 0;
String _newname = "";
String _listskey = "";
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _existing = "";
String _oldtaskkey = "";
String _newtaskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
String _oldck = "";
String _newck = "";
String _ln = "";
int _res2 = 0;
String _taskkey = "";
anywheresoftware.b4a.BA.IterableList group12;
int index12;
int groupLen12;
anywheresoftware.b4a.BA.IterableList group22;
int index22;
int groupLen22;
anywheresoftware.b4a.BA.IterableList group36;
int index36;
int groupLen36;
anywheresoftware.b4a.BA.IterableList group51;
int index51;
int groupLen51;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="todoactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=34930689;
 //BA.debugLineNum = 34930689;BA.debugLine="If Value = \"newListPanel\" Then Return";
if (true) break;

case 1:
//if
this.state = 6;
if ((_value).equals((Object)("newListPanel"))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
RDebugUtils.currentLine=34930690;
 //BA.debugLineNum = 34930690;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(parent.mostCurrent._groupet.getTag());
RDebugUtils.currentLine=34930692;
 //BA.debugLineNum = 34930692;BA.debugLine="Msgbox2Async(\"Delete or rename this list?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this list?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34930693;
 //BA.debugLineNum = 34930693;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslistgrp_itemlongclick"), null);
this.state = 53;
return;
case 53:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=34930695;
 //BA.debugLineNum = 34930695;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 52;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=34930741;
 //BA.debugLineNum = 34930741;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 39;
}}
if (true) break;

case 9:
//C
this.state = 10;
RDebugUtils.currentLine=34930697;
 //BA.debugLineNum = 34930697;BA.debugLine="Dim newName As String = \"\"";
_newname = "";
RDebugUtils.currentLine=34930698;
 //BA.debugLineNum = 34930698;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 10:
//if
this.state = 13;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
RDebugUtils.currentLine=34930700;
 //BA.debugLineNum = 34930700;BA.debugLine="showRenameListPanelGrp(Index, Value, code)";
_showrenamelistpanelgrp(_index,BA.ObjectToString(_value),_code);
 if (true) break;

case 13:
//C
this.state = 14;
;
RDebugUtils.currentLine=34930703;
 //BA.debugLineNum = 34930703;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=34930704;
 //BA.debugLineNum = 34930704;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_listskey)));
RDebugUtils.currentLine=34930707;
 //BA.debugLineNum = 34930707;BA.debugLine="For Each existing As String In savedLists";
if (true) break;

case 14:
//for
this.state = 21;
group12 = _savedlists;
index12 = 0;
groupLen12 = group12.getSize();
this.state = 54;
if (true) break;

case 54:
//C
this.state = 21;
if (index12 < groupLen12) {
this.state = 16;
_existing = BA.ObjectToString(group12.Get(index12));}
if (true) break;

case 55:
//C
this.state = 54;
index12++;
if (true) break;

case 16:
//C
this.state = 17;
RDebugUtils.currentLine=34930708;
 //BA.debugLineNum = 34930708;BA.debugLine="If existing = newName Then";
if (true) break;

case 17:
//if
this.state = 20;
if ((_existing).equals(_newname)) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
RDebugUtils.currentLine=34930709;
 //BA.debugLineNum = 34930709;BA.debugLine="MsgboxAsync(\"Name already exists.\", \"Duplicate";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Name already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=34930710;
 //BA.debugLineNum = 34930710;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 20:
//C
this.state = 55;
;
 if (true) break;
if (true) break;

case 21:
//C
this.state = 22;
;
RDebugUtils.currentLine=34930715;
 //BA.debugLineNum = 34930715;BA.debugLine="Dim oldTaskKey As String = \"group_list_\" & code";
_oldtaskkey = "group_list_"+_code+"_"+BA.ObjectToString(_value);
RDebugUtils.currentLine=34930716;
 //BA.debugLineNum = 34930716;BA.debugLine="Dim newTaskKey As String = \"group_list_\" & code";
_newtaskkey = "group_list_"+_code+"_"+_newname;
RDebugUtils.currentLine=34930717;
 //BA.debugLineNum = 34930717;BA.debugLine="If kvs.ContainsKey(oldTaskKey) Then";
if (true) break;

case 22:
//if
this.state = 33;
if (parent._kvs._containskey(_oldtaskkey)) { 
this.state = 24;
}if (true) break;

case 24:
//C
this.state = 25;
RDebugUtils.currentLine=34930718;
 //BA.debugLineNum = 34930718;BA.debugLine="Dim savedTasks As List = kvs.Get(oldTaskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_oldtaskkey)));
RDebugUtils.currentLine=34930719;
 //BA.debugLineNum = 34930719;BA.debugLine="For Each task As String In savedTasks";
if (true) break;

case 25:
//for
this.state = 32;
group22 = _savedtasks;
index22 = 0;
groupLen22 = group22.getSize();
this.state = 56;
if (true) break;

case 56:
//C
this.state = 32;
if (index22 < groupLen22) {
this.state = 27;
_task = BA.ObjectToString(group22.Get(index22));}
if (true) break;

case 57:
//C
this.state = 56;
index22++;
if (true) break;

case 27:
//C
this.state = 28;
RDebugUtils.currentLine=34930720;
 //BA.debugLineNum = 34930720;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code";
_oldck = "group_checked_"+_code+"_"+BA.ObjectToString(_value)+"_"+_task;
RDebugUtils.currentLine=34930721;
 //BA.debugLineNum = 34930721;BA.debugLine="Dim newCK As String = \"group_checked_\" & code";
_newck = "group_checked_"+_code+"_"+_newname+"_"+_task;
RDebugUtils.currentLine=34930722;
 //BA.debugLineNum = 34930722;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (true) break;

case 28:
//if
this.state = 31;
if (parent._kvs._containskey(_oldck)) { 
this.state = 30;
}if (true) break;

case 30:
//C
this.state = 31;
RDebugUtils.currentLine=34930723;
 //BA.debugLineNum = 34930723;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
parent._kvs._put(_newck,parent._kvs._get(_oldck));
RDebugUtils.currentLine=34930724;
 //BA.debugLineNum = 34930724;BA.debugLine="kvs.Remove(oldCK)";
parent._kvs._remove(_oldck);
 if (true) break;

case 31:
//C
this.state = 57;
;
 if (true) break;
if (true) break;

case 32:
//C
this.state = 33;
;
RDebugUtils.currentLine=34930727;
 //BA.debugLineNum = 34930727;BA.debugLine="kvs.Put(newTaskKey, savedTasks)";
parent._kvs._put(_newtaskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=34930728;
 //BA.debugLineNum = 34930728;BA.debugLine="kvs.Remove(oldTaskKey)";
parent._kvs._remove(_oldtaskkey);
 if (true) break;

case 33:
//C
this.state = 34;
;
RDebugUtils.currentLine=34930731;
 //BA.debugLineNum = 34930731;BA.debugLine="savedLists.Set(Index, newName)";
_savedlists.Set(_index,(Object)(_newname));
RDebugUtils.currentLine=34930732;
 //BA.debugLineNum = 34930732;BA.debugLine="kvs.Put(listsKey, savedLists)";
parent._kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=34930734;
 //BA.debugLineNum = 34930734;BA.debugLine="listsListGrp.Clear";
parent.mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=34930735;
 //BA.debugLineNum = 34930735;BA.debugLine="For Each ln As String In savedLists";
if (true) break;

case 34:
//for
this.state = 37;
group36 = _savedlists;
index36 = 0;
groupLen36 = group36.getSize();
this.state = 58;
if (true) break;

case 58:
//C
this.state = 37;
if (index36 < groupLen36) {
this.state = 36;
_ln = BA.ObjectToString(group36.Get(index36));}
if (true) break;

case 59:
//C
this.state = 58;
index36++;
if (true) break;

case 36:
//C
this.state = 59;
RDebugUtils.currentLine=34930736;
 //BA.debugLineNum = 34930736;BA.debugLine="listsListGrp.AddTextItem(ln, ln)";
parent.mostCurrent._listslistgrp._addtextitem((Object)(_ln),(Object)(_ln));
 if (true) break;
if (true) break;

case 37:
//C
this.state = 52;
;
RDebugUtils.currentLine=34930739;
 //BA.debugLineNum = 34930739;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List renamed"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 39:
//C
this.state = 40;
RDebugUtils.currentLine=34930742;
 //BA.debugLineNum = 34930742;BA.debugLine="Msgbox2Async(\"Delete list \"\"\" & Value & \"\"\"?\", \"";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete list \""+BA.ObjectToString(_value)+"\"?"),BA.ObjectToCharSequence("Confirm"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34930743;
 //BA.debugLineNum = 34930743;BA.debugLine="Wait For Msgbox_Result (res2 As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslistgrp_itemlongclick"), null);
this.state = 60;
return;
case 60:
//C
this.state = 40;
_res2 = (Integer) result[0];
;
RDebugUtils.currentLine=34930744;
 //BA.debugLineNum = 34930744;BA.debugLine="If res2 = DialogResponse.NEGATIVE Then";
if (true) break;

case 40:
//if
this.state = 51;
if (_res2==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 42;
}if (true) break;

case 42:
//C
this.state = 43;
RDebugUtils.currentLine=34930745;
 //BA.debugLineNum = 34930745;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=34930746;
 //BA.debugLineNum = 34930746;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_listskey)));
RDebugUtils.currentLine=34930747;
 //BA.debugLineNum = 34930747;BA.debugLine="savedLists.RemoveAt(Index)";
_savedlists.RemoveAt(_index);
RDebugUtils.currentLine=34930748;
 //BA.debugLineNum = 34930748;BA.debugLine="kvs.Put(listsKey, savedLists)";
parent._kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=34930751;
 //BA.debugLineNum = 34930751;BA.debugLine="Dim taskKey As String = \"group_list_\" & code &";
_taskkey = "group_list_"+_code+"_"+BA.ObjectToString(_value);
RDebugUtils.currentLine=34930752;
 //BA.debugLineNum = 34930752;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
if (true) break;

case 43:
//if
this.state = 50;
if (parent._kvs._containskey(_taskkey)) { 
this.state = 45;
}if (true) break;

case 45:
//C
this.state = 46;
RDebugUtils.currentLine=34930753;
 //BA.debugLineNum = 34930753;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_taskkey)));
RDebugUtils.currentLine=34930754;
 //BA.debugLineNum = 34930754;BA.debugLine="For Each task As String In savedTasks";
if (true) break;

case 46:
//for
this.state = 49;
group51 = _savedtasks;
index51 = 0;
groupLen51 = group51.getSize();
this.state = 61;
if (true) break;

case 61:
//C
this.state = 49;
if (index51 < groupLen51) {
this.state = 48;
_task = BA.ObjectToString(group51.Get(index51));}
if (true) break;

case 62:
//C
this.state = 61;
index51++;
if (true) break;

case 48:
//C
this.state = 62;
RDebugUtils.currentLine=34930755;
 //BA.debugLineNum = 34930755;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & Va";
parent._kvs._remove("group_checked_"+_code+"_"+BA.ObjectToString(_value)+"_"+_task);
 if (true) break;
if (true) break;

case 49:
//C
this.state = 50;
;
RDebugUtils.currentLine=34930757;
 //BA.debugLineNum = 34930757;BA.debugLine="kvs.Remove(taskKey)";
parent._kvs._remove(_taskkey);
 if (true) break;

case 50:
//C
this.state = 51;
;
RDebugUtils.currentLine=34930760;
 //BA.debugLineNum = 34930760;BA.debugLine="listsListGrp.RemoveAt(Index)";
parent.mostCurrent._listslistgrp._removeat(_index);
RDebugUtils.currentLine=34930761;
 //BA.debugLineNum = 34930761;BA.debugLine="tasksListGrp.Clear";
parent.mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=34930762;
 //BA.debugLineNum = 34930762;BA.debugLine="tasksListGrp.GetBase.Visible = False";
parent.mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34930763;
 //BA.debugLineNum = 34930763;BA.debugLine="ToastMessageShow(\"List deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 51:
//C
this.state = 52;
;
 if (true) break;

case 52:
//C
this.state = -1;
;
RDebugUtils.currentLine=34930766;
 //BA.debugLineNum = 34930766;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _showrenamelistpanelgrp(int _index,String _oldname,String _code) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showrenamelistpanelgrp", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showrenamelistpanelgrp", new Object[] {_index,_oldname,_code}));}
anywheresoftware.b4a.objects.PanelWrapper _renamepnl = null;
anywheresoftware.b4a.objects.EditTextWrapper _renameet = null;
anywheresoftware.b4a.objects.ButtonWrapper _renamebtn = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.collections.List _ctx = null;
RDebugUtils.currentLine=34996224;
 //BA.debugLineNum = 34996224;BA.debugLine="Sub showRenameListPanelGrp(Index As Int, oldName A";
RDebugUtils.currentLine=34996225;
 //BA.debugLineNum = 34996225;BA.debugLine="Dim renamePNL As Panel";
_renamepnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=34996226;
 //BA.debugLineNum = 34996226;BA.debugLine="renamePNL.Initialize(\"renameListPNLGrp\")";
_renamepnl.Initialize(mostCurrent.activityBA,"renameListPNLGrp");
RDebugUtils.currentLine=34996227;
 //BA.debugLineNum = 34996227;BA.debugLine="renamePNL.SetLayout(0, 0, 250dip, 120dip)";
_renamepnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=34996228;
 //BA.debugLineNum = 34996228;BA.debugLine="renamePNL.Color = Colors.Transparent";
_renamepnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=34996230;
 //BA.debugLineNum = 34996230;BA.debugLine="Dim renameET As EditText";
_renameet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=34996231;
 //BA.debugLineNum = 34996231;BA.debugLine="renameET.Initialize(\"renameListETGrp\")";
_renameet.Initialize(mostCurrent.activityBA,"renameListETGrp");
RDebugUtils.currentLine=34996232;
 //BA.debugLineNum = 34996232;BA.debugLine="renameET.Text = oldName";
_renameet.setText(BA.ObjectToCharSequence(_oldname));
RDebugUtils.currentLine=34996234;
 //BA.debugLineNum = 34996234;BA.debugLine="Dim renameBtn As Button";
_renamebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=34996235;
 //BA.debugLineNum = 34996235;BA.debugLine="renameBtn.Initialize(\"renameListBtnGrp\")";
_renamebtn.Initialize(mostCurrent.activityBA,"renameListBtnGrp");
RDebugUtils.currentLine=34996236;
 //BA.debugLineNum = 34996236;BA.debugLine="renameBtn.Text = \"Rename\"";
_renamebtn.setText(BA.ObjectToCharSequence("Rename"));
RDebugUtils.currentLine=34996238;
 //BA.debugLineNum = 34996238;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=34996239;
 //BA.debugLineNum = 34996239;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=34996240;
 //BA.debugLineNum = 34996240;BA.debugLine="renameBtn.Background = cd";
_renamebtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=34996241;
 //BA.debugLineNum = 34996241;BA.debugLine="renameBtn.TextColor = Colors.White";
_renamebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=34996243;
 //BA.debugLineNum = 34996243;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34996244;
 //BA.debugLineNum = 34996244;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=34996245;
 //BA.debugLineNum = 34996245;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=34996246;
 //BA.debugLineNum = 34996246;BA.debugLine="ctx.Add(oldName)";
_ctx.Add((Object)(_oldname));
RDebugUtils.currentLine=34996247;
 //BA.debugLineNum = 34996247;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=34996248;
 //BA.debugLineNum = 34996248;BA.debugLine="ctx.Add(renameET)";
_ctx.Add((Object)(_renameet.getObject()));
RDebugUtils.currentLine=34996249;
 //BA.debugLineNum = 34996249;BA.debugLine="renameBtn.Tag = ctx";
_renamebtn.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=34996251;
 //BA.debugLineNum = 34996251;BA.debugLine="renamePNL.AddView(renameET, 0, 0, 250dip, 55dip)";
_renamepnl.AddView((android.view.View)(_renameet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=34996252;
 //BA.debugLineNum = 34996252;BA.debugLine="renamePNL.AddView(renameBtn, 0, 65dip, 250dip, 40";
_renamepnl.AddView((android.view.View)(_renamebtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=34996254;
 //BA.debugLineNum = 34996254;BA.debugLine="listsListGrp.Add(renamePNL, \"renameListPanel\")";
mostCurrent._listslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_renamepnl.getObject())),(Object)("renameListPanel"));
RDebugUtils.currentLine=34996255;
 //BA.debugLineNum = 34996255;BA.debugLine="End Sub";
return "";
}
public static void  _newgroupbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newgroupbtn_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "newgroupbtn_click", null); return;}
ResumableSub_newGroupBtn_Click rsub = new ResumableSub_newGroupBtn_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_newGroupBtn_Click extends BA.ResumableSub {
public ResumableSub_newGroupBtn_Click(b4a.example.todoactivity parent) {
this.parent = parent;
}
b4a.example.todoactivity parent;
int _res = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="todoactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=34013185;
 //BA.debugLineNum = 34013185;BA.debugLine="Msgbox2Async(\"Join or create a group?\", \"Groups\",";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Join or create a group?"),BA.ObjectToCharSequence("Groups"),"Create","","Join",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34013186;
 //BA.debugLineNum = 34013186;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "newgroupbtn_click"), null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=34013188;
 //BA.debugLineNum = 34013188;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}else 
{RDebugUtils.currentLine=34013191;
 //BA.debugLineNum = 34013191;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 5;
}}
if (true) break;

case 3:
//C
this.state = 6;
RDebugUtils.currentLine=34013190;
 //BA.debugLineNum = 34013190;BA.debugLine="showCreateGroupPanel";
_showcreategrouppanel();
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=34013193;
 //BA.debugLineNum = 34013193;BA.debugLine="showJoinGroupPanel";
_showjoingrouppanel();
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=34013195;
 //BA.debugLineNum = 34013195;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _showcreategrouppanel() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showcreategrouppanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showcreategrouppanel", null));}
RDebugUtils.currentLine=34078720;
 //BA.debugLineNum = 34078720;BA.debugLine="Sub showCreateGroupPanel";
RDebugUtils.currentLine=34078722;
 //BA.debugLineNum = 34078722;BA.debugLine="groupET.Text = \"\"";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=34078723;
 //BA.debugLineNum = 34078723;BA.debugLine="groupET.Hint = \"Enter group name...\"";
mostCurrent._groupet.setHint("Enter group name...");
RDebugUtils.currentLine=34078724;
 //BA.debugLineNum = 34078724;BA.debugLine="groupET.Enabled = True";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34078725;
 //BA.debugLineNum = 34078725;BA.debugLine="groupET.Tag = \"creating\"";
mostCurrent._groupet.setTag((Object)("creating"));
RDebugUtils.currentLine=34078726;
 //BA.debugLineNum = 34078726;BA.debugLine="groupET.RequestFocus";
mostCurrent._groupet.RequestFocus();
RDebugUtils.currentLine=34078727;
 //BA.debugLineNum = 34078727;BA.debugLine="End Sub";
return "";
}
public static String  _showjoingrouppanel() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showjoingrouppanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showjoingrouppanel", null));}
anywheresoftware.b4a.objects.PanelWrapper _joinpnl = null;
anywheresoftware.b4a.objects.EditTextWrapper _joinet = null;
anywheresoftware.b4a.objects.ButtonWrapper _joinbtn = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=34340864;
 //BA.debugLineNum = 34340864;BA.debugLine="Sub showJoinGroupPanel";
RDebugUtils.currentLine=34340866;
 //BA.debugLineNum = 34340866;BA.debugLine="Dim joinPNL As Panel";
_joinpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=34340867;
 //BA.debugLineNum = 34340867;BA.debugLine="joinPNL.Initialize(\"joinPNL\")";
_joinpnl.Initialize(mostCurrent.activityBA,"joinPNL");
RDebugUtils.currentLine=34340868;
 //BA.debugLineNum = 34340868;BA.debugLine="joinPNL.SetLayout(0, 0, 250dip, 120dip)";
_joinpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=34340869;
 //BA.debugLineNum = 34340869;BA.debugLine="joinPNL.Color = Colors.Transparent";
_joinpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=34340871;
 //BA.debugLineNum = 34340871;BA.debugLine="Dim joinET As EditText";
_joinet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=34340872;
 //BA.debugLineNum = 34340872;BA.debugLine="joinET.Initialize(\"joinET\")";
_joinet.Initialize(mostCurrent.activityBA,"joinET");
RDebugUtils.currentLine=34340873;
 //BA.debugLineNum = 34340873;BA.debugLine="joinET.Hint = \"Enter 6-char group code...\"";
_joinet.setHint("Enter 6-char group code...");
RDebugUtils.currentLine=34340875;
 //BA.debugLineNum = 34340875;BA.debugLine="Dim joinBtn As Button";
_joinbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=34340876;
 //BA.debugLineNum = 34340876;BA.debugLine="joinBtn.Initialize(\"joinBtn\")";
_joinbtn.Initialize(mostCurrent.activityBA,"joinBtn");
RDebugUtils.currentLine=34340877;
 //BA.debugLineNum = 34340877;BA.debugLine="joinBtn.Text = \"Join Group\"";
_joinbtn.setText(BA.ObjectToCharSequence("Join Group"));
RDebugUtils.currentLine=34340879;
 //BA.debugLineNum = 34340879;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=34340880;
 //BA.debugLineNum = 34340880;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=34340881;
 //BA.debugLineNum = 34340881;BA.debugLine="joinBtn.Background = cd";
_joinbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=34340882;
 //BA.debugLineNum = 34340882;BA.debugLine="joinBtn.TextColor = Colors.White";
_joinbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=34340884;
 //BA.debugLineNum = 34340884;BA.debugLine="joinPNL.AddView(joinET, 0, 0, 250dip, 55dip)";
_joinpnl.AddView((android.view.View)(_joinet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=34340885;
 //BA.debugLineNum = 34340885;BA.debugLine="joinPNL.AddView(joinBtn, 0, 65dip, 250dip, 40dip)";
_joinpnl.AddView((android.view.View)(_joinbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=34340888;
 //BA.debugLineNum = 34340888;BA.debugLine="joinBtn.Tag = joinET";
_joinbtn.setTag((Object)(_joinet.getObject()));
RDebugUtils.currentLine=34340890;
 //BA.debugLineNum = 34340890;BA.debugLine="groupList.Add(joinPNL, \"joinPanel\")";
mostCurrent._grouplist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_joinpnl.getObject())),(Object)("joinPanel"));
RDebugUtils.currentLine=34340891;
 //BA.debugLineNum = 34340891;BA.debugLine="End Sub";
return "";
}
public static String  _newlistbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newlistbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newlistbtn_click", null));}
RDebugUtils.currentLine=3670016;
 //BA.debugLineNum = 3670016;BA.debugLine="Sub newListBtn_Click";
RDebugUtils.currentLine=3670018;
 //BA.debugLineNum = 3670018;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=3670019;
 //BA.debugLineNum = 3670019;BA.debugLine="isAddingList = True";
_isaddinglist = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=3670021;
 //BA.debugLineNum = 3670021;BA.debugLine="progressNumber.Text = \"\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3670022;
 //BA.debugLineNum = 3670022;BA.debugLine="progressPercent.Text = \"\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3670023;
 //BA.debugLineNum = 3670023;BA.debugLine="progressBar.Progress = 0";
mostCurrent._progressbar.setProgress((int) (0));
RDebugUtils.currentLine=3670025;
 //BA.debugLineNum = 3670025;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3670026;
 //BA.debugLineNum = 3670026;BA.debugLine="addTitleTextArea.Enabled = True";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3670027;
 //BA.debugLineNum = 3670027;BA.debugLine="addTitleTextArea.Background = Null";
mostCurrent._addtitletextarea.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=3670028;
 //BA.debugLineNum = 3670028;BA.debugLine="addTitleTextArea.Text = \"\"";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3670029;
 //BA.debugLineNum = 3670029;BA.debugLine="addTitleTextArea.Hint = \"+ add a title...\"";
mostCurrent._addtitletextarea.setHint("+ add a title...");
RDebugUtils.currentLine=3670030;
 //BA.debugLineNum = 3670030;BA.debugLine="addTitleTextArea.RequestFocus";
mostCurrent._addtitletextarea.RequestFocus();
RDebugUtils.currentLine=3670031;
 //BA.debugLineNum = 3670031;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=3670033;
 //BA.debugLineNum = 3670033;BA.debugLine="newListBtn.Enabled = False";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3670035;
 //BA.debugLineNum = 3670035;BA.debugLine="tasksList.GetBase.Visible = True";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3670036;
 //BA.debugLineNum = 3670036;BA.debugLine="addTaskBtn.Visible = True";
mostCurrent._addtaskbtn.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3670038;
 //BA.debugLineNum = 3670038;BA.debugLine="End Sub";
return "";
}
public static String  _newlistbtngrp_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newlistbtngrp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newlistbtngrp_click", null));}
String _code = "";
anywheresoftware.b4a.objects.PanelWrapper _newlistpnl = null;
anywheresoftware.b4a.objects.EditTextWrapper _newlistet = null;
anywheresoftware.b4a.objects.ButtonWrapper _newlistconfirmbtn = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=34734080;
 //BA.debugLineNum = 34734080;BA.debugLine="Sub newListBtnGrp_Click";
RDebugUtils.currentLine=34734081;
 //BA.debugLineNum = 34734081;BA.debugLine="If groupET.Tag = Null Or groupET.Tag Is List Then";
if (mostCurrent._groupet.getTag()== null || mostCurrent._groupet.getTag() instanceof java.util.List) { 
if (true) return "";};
RDebugUtils.currentLine=34734083;
 //BA.debugLineNum = 34734083;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(mostCurrent._groupet.getTag());
RDebugUtils.currentLine=34734085;
 //BA.debugLineNum = 34734085;BA.debugLine="listsListGrp.GetBase.Visible = True";
mostCurrent._listslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=34734086;
 //BA.debugLineNum = 34734086;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=34734087;
 //BA.debugLineNum = 34734087;BA.debugLine="tasksListGrp.GetBase.Visible = False";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34734090;
 //BA.debugLineNum = 34734090;BA.debugLine="Dim newListPNL As Panel";
_newlistpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=34734091;
 //BA.debugLineNum = 34734091;BA.debugLine="newListPNL.Initialize(\"newListPNLGrp\")";
_newlistpnl.Initialize(mostCurrent.activityBA,"newListPNLGrp");
RDebugUtils.currentLine=34734092;
 //BA.debugLineNum = 34734092;BA.debugLine="newListPNL.SetLayout(0, 0, 250dip, 120dip)";
_newlistpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=34734093;
 //BA.debugLineNum = 34734093;BA.debugLine="newListPNL.Color = Colors.Transparent";
_newlistpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=34734095;
 //BA.debugLineNum = 34734095;BA.debugLine="Dim newListET As EditText";
_newlistet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=34734096;
 //BA.debugLineNum = 34734096;BA.debugLine="newListET.Initialize(\"newListETGrp\")";
_newlistet.Initialize(mostCurrent.activityBA,"newListETGrp");
RDebugUtils.currentLine=34734097;
 //BA.debugLineNum = 34734097;BA.debugLine="newListET.Hint = \"List name...\"";
_newlistet.setHint("List name...");
RDebugUtils.currentLine=34734099;
 //BA.debugLineNum = 34734099;BA.debugLine="Dim newListConfirmBtn As Button";
_newlistconfirmbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=34734100;
 //BA.debugLineNum = 34734100;BA.debugLine="newListConfirmBtn.Initialize(\"newListConfirmBtnGr";
_newlistconfirmbtn.Initialize(mostCurrent.activityBA,"newListConfirmBtnGrp");
RDebugUtils.currentLine=34734101;
 //BA.debugLineNum = 34734101;BA.debugLine="newListConfirmBtn.Text = \"Create List\"";
_newlistconfirmbtn.setText(BA.ObjectToCharSequence("Create List"));
RDebugUtils.currentLine=34734103;
 //BA.debugLineNum = 34734103;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=34734104;
 //BA.debugLineNum = 34734104;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=34734105;
 //BA.debugLineNum = 34734105;BA.debugLine="newListConfirmBtn.Background = cd";
_newlistconfirmbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=34734106;
 //BA.debugLineNum = 34734106;BA.debugLine="newListConfirmBtn.TextColor = Colors.White";
_newlistconfirmbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=34734108;
 //BA.debugLineNum = 34734108;BA.debugLine="newListPNL.AddView(newListET, 0, 0, 250dip, 55dip";
_newlistpnl.AddView((android.view.View)(_newlistet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=34734109;
 //BA.debugLineNum = 34734109;BA.debugLine="newListPNL.AddView(newListConfirmBtn, 0, 65dip, 2";
_newlistpnl.AddView((android.view.View)(_newlistconfirmbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=34734111;
 //BA.debugLineNum = 34734111;BA.debugLine="newListConfirmBtn.Tag = newListET";
_newlistconfirmbtn.setTag((Object)(_newlistet.getObject()));
RDebugUtils.currentLine=34734113;
 //BA.debugLineNum = 34734113;BA.debugLine="listsListGrp.Add(newListPNL, \"newListPanel\")";
mostCurrent._listslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_newlistpnl.getObject())),(Object)("newListPanel"));
RDebugUtils.currentLine=34734114;
 //BA.debugLineNum = 34734114;BA.debugLine="End Sub";
return "";
}
public static String  _newlistconfirmbtngrp_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newlistconfirmbtngrp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newlistconfirmbtngrp_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
anywheresoftware.b4a.objects.EditTextWrapper _et = null;
String _listname = "";
String _code = "";
String _listskey = "";
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _existing = "";
RDebugUtils.currentLine=34799616;
 //BA.debugLineNum = 34799616;BA.debugLine="Sub newListConfirmBtnGrp_Click";
RDebugUtils.currentLine=34799617;
 //BA.debugLineNum = 34799617;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=34799618;
 //BA.debugLineNum = 34799618;BA.debugLine="Dim et As EditText = btn.Tag";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
_et = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_btn.getTag()));
RDebugUtils.currentLine=34799619;
 //BA.debugLineNum = 34799619;BA.debugLine="Dim listName As String = et.Text.Trim";
_listname = _et.getText().trim();
RDebugUtils.currentLine=34799620;
 //BA.debugLineNum = 34799620;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(mostCurrent._groupet.getTag());
RDebugUtils.currentLine=34799622;
 //BA.debugLineNum = 34799622;BA.debugLine="If listName = \"\" Then";
if ((_listname).equals("")) { 
RDebugUtils.currentLine=34799623;
 //BA.debugLineNum = 34799623;BA.debugLine="MsgboxAsync(\"Please enter a list name.\", \"No nam";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a list name."),BA.ObjectToCharSequence("No name"),processBA);
RDebugUtils.currentLine=34799624;
 //BA.debugLineNum = 34799624;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=34799627;
 //BA.debugLineNum = 34799627;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=34799628;
 //BA.debugLineNum = 34799628;BA.debugLine="Dim savedLists As List";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=34799629;
 //BA.debugLineNum = 34799629;BA.debugLine="savedLists.Initialize";
_savedlists.Initialize();
RDebugUtils.currentLine=34799630;
 //BA.debugLineNum = 34799630;BA.debugLine="If kvs.ContainsKey(listsKey) Then savedLists = kv";
if (_kvs._containskey(_listskey)) { 
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));};
RDebugUtils.currentLine=34799632;
 //BA.debugLineNum = 34799632;BA.debugLine="For Each existing As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group13 = _savedlists;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_existing = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=34799633;
 //BA.debugLineNum = 34799633;BA.debugLine="If existing = listName Then";
if ((_existing).equals(_listname)) { 
RDebugUtils.currentLine=34799634;
 //BA.debugLineNum = 34799634;BA.debugLine="MsgboxAsync(\"A list with that name already exis";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("A list with that name already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=34799635;
 //BA.debugLineNum = 34799635;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=34799639;
 //BA.debugLineNum = 34799639;BA.debugLine="savedLists.Add(listName)";
_savedlists.Add((Object)(_listname));
RDebugUtils.currentLine=34799640;
 //BA.debugLineNum = 34799640;BA.debugLine="kvs.Put(listsKey, savedLists)";
_kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=34799643;
 //BA.debugLineNum = 34799643;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
mostCurrent._listslistgrp._removeat((int) (mostCurrent._listslistgrp._getsize()-1));
RDebugUtils.currentLine=34799644;
 //BA.debugLineNum = 34799644;BA.debugLine="listsListGrp.AddTextItem(listName, listName)";
mostCurrent._listslistgrp._addtextitem((Object)(_listname),(Object)(_listname));
RDebugUtils.currentLine=34799646;
 //BA.debugLineNum = 34799646;BA.debugLine="ToastMessageShow(\"List created\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List created"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=34799647;
 //BA.debugLineNum = 34799647;BA.debugLine="End Sub";
return "";
}
public static String  _renamelistbtngrp_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "renamelistbtngrp_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "renamelistbtngrp_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
anywheresoftware.b4a.objects.collections.List _ctx = null;
int _index = 0;
String _oldname = "";
String _code = "";
anywheresoftware.b4a.objects.EditTextWrapper _renameet = null;
String _newname = "";
String _listskey = "";
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _existing = "";
String _oldtaskkey = "";
String _newtaskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;
String _task = "";
String _oldck = "";
String _newck = "";
String _ln = "";
RDebugUtils.currentLine=35061760;
 //BA.debugLineNum = 35061760;BA.debugLine="Sub renameListBtnGrp_Click";
RDebugUtils.currentLine=35061761;
 //BA.debugLineNum = 35061761;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=35061762;
 //BA.debugLineNum = 35061762;BA.debugLine="Dim ctx As List = btn.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_btn.getTag()));
RDebugUtils.currentLine=35061763;
 //BA.debugLineNum = 35061763;BA.debugLine="Dim Index As Int = ctx.Get(0)";
_index = (int)(BA.ObjectToNumber(_ctx.Get((int) (0))));
RDebugUtils.currentLine=35061764;
 //BA.debugLineNum = 35061764;BA.debugLine="Dim oldName As String = ctx.Get(1)";
_oldname = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=35061765;
 //BA.debugLineNum = 35061765;BA.debugLine="Dim code As String = ctx.Get(2)";
_code = BA.ObjectToString(_ctx.Get((int) (2)));
RDebugUtils.currentLine=35061766;
 //BA.debugLineNum = 35061766;BA.debugLine="Dim renameET As EditText = ctx.Get(3)";
_renameet = new anywheresoftware.b4a.objects.EditTextWrapper();
_renameet = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_ctx.Get((int) (3))));
RDebugUtils.currentLine=35061767;
 //BA.debugLineNum = 35061767;BA.debugLine="Dim newName As String = renameET.Text.Trim";
_newname = _renameet.getText().trim();
RDebugUtils.currentLine=35061769;
 //BA.debugLineNum = 35061769;BA.debugLine="If newName = \"\" Or newName = oldName Then";
if ((_newname).equals("") || (_newname).equals(_oldname)) { 
RDebugUtils.currentLine=35061770;
 //BA.debugLineNum = 35061770;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
mostCurrent._listslistgrp._removeat((int) (mostCurrent._listslistgrp._getsize()-1));
RDebugUtils.currentLine=35061771;
 //BA.debugLineNum = 35061771;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=35061774;
 //BA.debugLineNum = 35061774;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=35061775;
 //BA.debugLineNum = 35061775;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));
RDebugUtils.currentLine=35061777;
 //BA.debugLineNum = 35061777;BA.debugLine="For Each existing As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group14 = _savedlists;
final int groupLen14 = group14.getSize()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_existing = BA.ObjectToString(group14.Get(index14));
RDebugUtils.currentLine=35061778;
 //BA.debugLineNum = 35061778;BA.debugLine="If existing = newName Then";
if ((_existing).equals(_newname)) { 
RDebugUtils.currentLine=35061779;
 //BA.debugLineNum = 35061779;BA.debugLine="MsgboxAsync(\"Name already exists.\", \"Duplicate\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Name already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=35061780;
 //BA.debugLineNum = 35061780;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=35061785;
 //BA.debugLineNum = 35061785;BA.debugLine="Dim oldTaskKey As String = \"group_list_\" & code &";
_oldtaskkey = "group_list_"+_code+"_"+_oldname;
RDebugUtils.currentLine=35061786;
 //BA.debugLineNum = 35061786;BA.debugLine="Dim newTaskKey As String = \"group_list_\" & code &";
_newtaskkey = "group_list_"+_code+"_"+_newname;
RDebugUtils.currentLine=35061787;
 //BA.debugLineNum = 35061787;BA.debugLine="If kvs.ContainsKey(oldTaskKey) Then";
if (_kvs._containskey(_oldtaskkey)) { 
RDebugUtils.currentLine=35061788;
 //BA.debugLineNum = 35061788;BA.debugLine="Dim savedTasks As List = kvs.Get(oldTaskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_oldtaskkey)));
RDebugUtils.currentLine=35061789;
 //BA.debugLineNum = 35061789;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group24 = _savedtasks;
final int groupLen24 = group24.getSize()
;int index24 = 0;
;
for (; index24 < groupLen24;index24++){
_task = BA.ObjectToString(group24.Get(index24));
RDebugUtils.currentLine=35061790;
 //BA.debugLineNum = 35061790;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code &";
_oldck = "group_checked_"+_code+"_"+_oldname+"_"+_task;
RDebugUtils.currentLine=35061791;
 //BA.debugLineNum = 35061791;BA.debugLine="Dim newCK As String = \"group_checked_\" & code &";
_newck = "group_checked_"+_code+"_"+_newname+"_"+_task;
RDebugUtils.currentLine=35061792;
 //BA.debugLineNum = 35061792;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=35061793;
 //BA.debugLineNum = 35061793;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=35061794;
 //BA.debugLineNum = 35061794;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
 }
};
RDebugUtils.currentLine=35061797;
 //BA.debugLineNum = 35061797;BA.debugLine="kvs.Put(newTaskKey, savedTasks)";
_kvs._put(_newtaskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=35061798;
 //BA.debugLineNum = 35061798;BA.debugLine="kvs.Remove(oldTaskKey)";
_kvs._remove(_oldtaskkey);
 };
RDebugUtils.currentLine=35061801;
 //BA.debugLineNum = 35061801;BA.debugLine="savedLists.Set(Index, newName)";
_savedlists.Set(_index,(Object)(_newname));
RDebugUtils.currentLine=35061802;
 //BA.debugLineNum = 35061802;BA.debugLine="kvs.Put(listsKey, savedLists)";
_kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=35061804;
 //BA.debugLineNum = 35061804;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
mostCurrent._listslistgrp._removeat((int) (mostCurrent._listslistgrp._getsize()-1));
RDebugUtils.currentLine=35061805;
 //BA.debugLineNum = 35061805;BA.debugLine="listsListGrp.Clear";
mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=35061806;
 //BA.debugLineNum = 35061806;BA.debugLine="For Each ln As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group39 = _savedlists;
final int groupLen39 = group39.getSize()
;int index39 = 0;
;
for (; index39 < groupLen39;index39++){
_ln = BA.ObjectToString(group39.Get(index39));
RDebugUtils.currentLine=35061807;
 //BA.debugLineNum = 35061807;BA.debugLine="listsListGrp.AddTextItem(ln, ln)";
mostCurrent._listslistgrp._addtextitem((Object)(_ln),(Object)(_ln));
 }
};
RDebugUtils.currentLine=35061810;
 //BA.debugLineNum = 35061810;BA.debugLine="If currentGrpListName = oldName Then currentGrpLi";
if ((mostCurrent._currentgrplistname).equals(_oldname)) { 
mostCurrent._currentgrplistname = _newname;};
RDebugUtils.currentLine=35061811;
 //BA.debugLineNum = 35061811;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=35061812;
 //BA.debugLineNum = 35061812;BA.debugLine="End Sub";
return "";
}
public static String  _showrenametaskpanel(int _index,String _oldtask) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showrenametaskpanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showrenametaskpanel", new Object[] {_index,_oldtask}));}
anywheresoftware.b4a.objects.collections.List _ctx = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=4194304;
 //BA.debugLineNum = 4194304;BA.debugLine="Sub showRenameTaskPanel(Index As Int, oldTask As S";
RDebugUtils.currentLine=4194306;
 //BA.debugLineNum = 4194306;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1) ' remove \"";
mostCurrent._taskslist._removeat((int) (mostCurrent._taskslist._getsize()-1));
RDebugUtils.currentLine=4194308;
 //BA.debugLineNum = 4194308;BA.debugLine="addTaskPanel.Initialize(\"addTaskPanel\")";
mostCurrent._addtaskpanel.Initialize(mostCurrent.activityBA,"addTaskPanel");
RDebugUtils.currentLine=4194309;
 //BA.debugLineNum = 4194309;BA.debugLine="addTaskPanel.SetLayout(10dip, 0, 240dip, 120dip)";
mostCurrent._addtaskpanel.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=4194310;
 //BA.debugLineNum = 4194310;BA.debugLine="addTaskPanel.Color = Colors.ARGB(0, 247, 247, 247";
mostCurrent._addtaskpanel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=4194312;
 //BA.debugLineNum = 4194312;BA.debugLine="addTaskTextArea.Initialize(\"addTodoText\")";
mostCurrent._addtasktextarea.Initialize(mostCurrent.activityBA,"addTodoText");
RDebugUtils.currentLine=4194313;
 //BA.debugLineNum = 4194313;BA.debugLine="addTaskTextArea.Text = oldTask";
mostCurrent._addtasktextarea.setText(BA.ObjectToCharSequence(_oldtask));
RDebugUtils.currentLine=4194314;
 //BA.debugLineNum = 4194314;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=4194315;
 //BA.debugLineNum = 4194315;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=4194316;
 //BA.debugLineNum = 4194316;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=4194317;
 //BA.debugLineNum = 4194317;BA.debugLine="ctx.Add(oldTask)";
_ctx.Add((Object)(_oldtask));
RDebugUtils.currentLine=4194318;
 //BA.debugLineNum = 4194318;BA.debugLine="addTaskTextArea.Tag = ctx";
mostCurrent._addtasktextarea.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=4194320;
 //BA.debugLineNum = 4194320;BA.debugLine="enterTaskBtn.Initialize(\"enterTaskBtn\")";
mostCurrent._entertaskbtn.Initialize(mostCurrent.activityBA,"enterTaskBtn");
RDebugUtils.currentLine=4194321;
 //BA.debugLineNum = 4194321;BA.debugLine="enterTaskBtn.Text = \"Rename task\"";
mostCurrent._entertaskbtn.setText(BA.ObjectToCharSequence("Rename task"));
RDebugUtils.currentLine=4194323;
 //BA.debugLineNum = 4194323;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=4194324;
 //BA.debugLineNum = 4194324;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=4194326;
 //BA.debugLineNum = 4194326;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=4194327;
 //BA.debugLineNum = 4194327;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=4194328;
 //BA.debugLineNum = 4194328;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=4194329;
 //BA.debugLineNum = 4194329;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=4194330;
 //BA.debugLineNum = 4194330;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4194331;
 //BA.debugLineNum = 4194331;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=4194333;
 //BA.debugLineNum = 4194333;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=4194334;
 //BA.debugLineNum = 4194334;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=4194335;
 //BA.debugLineNum = 4194335;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=4194336;
 //BA.debugLineNum = 4194336;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4194337;
 //BA.debugLineNum = 4194337;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=4194340;
 //BA.debugLineNum = 4194340;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=4194341;
 //BA.debugLineNum = 4194341;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=4194342;
 //BA.debugLineNum = 4194342;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=4194343;
 //BA.debugLineNum = 4194343;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=4194344;
 //BA.debugLineNum = 4194344;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4194345;
 //BA.debugLineNum = 4194345;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=4194347;
 //BA.debugLineNum = 4194347;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=4194348;
 //BA.debugLineNum = 4194348;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=4194349;
 //BA.debugLineNum = 4194349;BA.debugLine="cd.Initialize(Colors.ARGB(255, 137, 162, 185),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (137),(int) (162),(int) (185)),(int) (200));
RDebugUtils.currentLine=4194350;
 //BA.debugLineNum = 4194350;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4194351;
 //BA.debugLineNum = 4194351;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
case 2: {
RDebugUtils.currentLine=4194354;
 //BA.debugLineNum = 4194354;BA.debugLine="addTaskTextArea.Typeface = pixeltf";
mostCurrent._addtasktextarea.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=4194355;
 //BA.debugLineNum = 4194355;BA.debugLine="enterTaskBtn.Typeface = pixeltf";
mostCurrent._entertaskbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=4194356;
 //BA.debugLineNum = 4194356;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=4194357;
 //BA.debugLineNum = 4194357;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=4194358;
 //BA.debugLineNum = 4194358;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=4194359;
 //BA.debugLineNum = 4194359;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=4194360;
 //BA.debugLineNum = 4194360;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4194361;
 //BA.debugLineNum = 4194361;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=4194363;
 //BA.debugLineNum = 4194363;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=4194364;
 //BA.debugLineNum = 4194364;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=4194365;
 //BA.debugLineNum = 4194365;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=4194366;
 //BA.debugLineNum = 4194366;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4194367;
 //BA.debugLineNum = 4194367;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
}
;
RDebugUtils.currentLine=4194371;
 //BA.debugLineNum = 4194371;BA.debugLine="addTaskPanel.AddView(addTaskTextArea, 0, 0, addTa";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._addtasktextarea.getObject()),(int) (0),(int) (0),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=4194372;
 //BA.debugLineNum = 4194372;BA.debugLine="addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addT";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._entertaskbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=4194374;
 //BA.debugLineNum = 4194374;BA.debugLine="tasksList.Add(addTaskPanel, addTaskPanel)";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._addtaskpanel.getObject())),(Object)(mostCurrent._addtaskpanel.getObject()));
RDebugUtils.currentLine=4194376;
 //BA.debugLineNum = 4194376;BA.debugLine="End Sub";
return "";
}
public static String  _showrenametaskpanelgrp(int _index,String _oldtask,String _code,String _currentgrplist) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showrenametaskpanelgrp", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showrenametaskpanelgrp", new Object[] {_index,_oldtask,_code,_currentgrplist}));}
anywheresoftware.b4a.objects.PanelWrapper _addpnl = null;
anywheresoftware.b4a.objects.EditTextWrapper _addet = null;
anywheresoftware.b4a.objects.ButtonWrapper _confirmbtn = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.collections.List _ctx = null;
RDebugUtils.currentLine=35389440;
 //BA.debugLineNum = 35389440;BA.debugLine="Sub showRenameTaskPanelGrp(Index As Int, oldTask A";
RDebugUtils.currentLine=35389441;
 //BA.debugLineNum = 35389441;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
mostCurrent._taskslistgrp._removeat((int) (mostCurrent._taskslistgrp._getsize()-1));
RDebugUtils.currentLine=35389443;
 //BA.debugLineNum = 35389443;BA.debugLine="Dim addPNL As Panel";
_addpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=35389444;
 //BA.debugLineNum = 35389444;BA.debugLine="addPNL.Initialize(\"addTaskPNLGrp\")";
_addpnl.Initialize(mostCurrent.activityBA,"addTaskPNLGrp");
RDebugUtils.currentLine=35389445;
 //BA.debugLineNum = 35389445;BA.debugLine="addPNL.SetLayout(10dip, 0, 240dip, 120dip)";
_addpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=35389446;
 //BA.debugLineNum = 35389446;BA.debugLine="addPNL.Color = Colors.Transparent";
_addpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=35389448;
 //BA.debugLineNum = 35389448;BA.debugLine="Dim addET As EditText";
_addet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=35389449;
 //BA.debugLineNum = 35389449;BA.debugLine="addET.Initialize(\"addTaskETGrp\")";
_addet.Initialize(mostCurrent.activityBA,"addTaskETGrp");
RDebugUtils.currentLine=35389450;
 //BA.debugLineNum = 35389450;BA.debugLine="addET.Text = oldTask";
_addet.setText(BA.ObjectToCharSequence(_oldtask));
RDebugUtils.currentLine=35389452;
 //BA.debugLineNum = 35389452;BA.debugLine="Dim confirmBtn As Button";
_confirmbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=35389453;
 //BA.debugLineNum = 35389453;BA.debugLine="confirmBtn.Initialize(\"enterTaskBtnGrp\")";
_confirmbtn.Initialize(mostCurrent.activityBA,"enterTaskBtnGrp");
RDebugUtils.currentLine=35389454;
 //BA.debugLineNum = 35389454;BA.debugLine="confirmBtn.Text = \"Rename task\"";
_confirmbtn.setText(BA.ObjectToCharSequence("Rename task"));
RDebugUtils.currentLine=35389456;
 //BA.debugLineNum = 35389456;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=35389457;
 //BA.debugLineNum = 35389457;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=35389458;
 //BA.debugLineNum = 35389458;BA.debugLine="confirmBtn.Background = cd";
_confirmbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=35389459;
 //BA.debugLineNum = 35389459;BA.debugLine="confirmBtn.TextColor = Colors.White";
_confirmbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=35389461;
 //BA.debugLineNum = 35389461;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=35389462;
 //BA.debugLineNum = 35389462;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=35389463;
 //BA.debugLineNum = 35389463;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=35389464;
 //BA.debugLineNum = 35389464;BA.debugLine="ctx.Add(currentGrpList)";
_ctx.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=35389465;
 //BA.debugLineNum = 35389465;BA.debugLine="ctx.Add(addET)";
_ctx.Add((Object)(_addet.getObject()));
RDebugUtils.currentLine=35389466;
 //BA.debugLineNum = 35389466;BA.debugLine="ctx.Add(oldTask)    ' index 3 is not Null → renam";
_ctx.Add((Object)(_oldtask));
RDebugUtils.currentLine=35389467;
 //BA.debugLineNum = 35389467;BA.debugLine="confirmBtn.Tag = ctx";
_confirmbtn.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=35389469;
 //BA.debugLineNum = 35389469;BA.debugLine="addPNL.AddView(addET, 0, 0, 190dip, 60dip)";
_addpnl.AddView((android.view.View)(_addet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=35389470;
 //BA.debugLineNum = 35389470;BA.debugLine="addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40di";
_addpnl.AddView((android.view.View)(_confirmbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=35389472;
 //BA.debugLineNum = 35389472;BA.debugLine="tasksListGrp.Add(addPNL, addPNL)";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_addpnl.getObject())),(Object)(_addpnl.getObject()));
RDebugUtils.currentLine=35389473;
 //BA.debugLineNum = 35389473;BA.debugLine="End Sub";
return "";
}
public static String  _taskcheckbox_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskcheckbox_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskcheckbox_checkedchange", new Object[] {_checked}));}
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _taskcheckbox = null;
anywheresoftware.b4a.objects.LabelWrapper _tasklbl = null;
String _key = "";
RDebugUtils.currentLine=4259840;
 //BA.debugLineNum = 4259840;BA.debugLine="Sub taskCheckbox_CheckedChange(Checked As Boolean)";
RDebugUtils.currentLine=4259842;
 //BA.debugLineNum = 4259842;BA.debugLine="Dim taskCheckbox As CheckBox = Sender";
_taskcheckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
_taskcheckbox = (anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper(), (android.widget.CheckBox)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=4259843;
 //BA.debugLineNum = 4259843;BA.debugLine="Dim taskLBL As Label = taskCheckbox.Tag";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
_tasklbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_taskcheckbox.getTag()));
RDebugUtils.currentLine=4259844;
 //BA.debugLineNum = 4259844;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=4259845;
 //BA.debugLineNum = 4259845;BA.debugLine="If Checked Then";
if (_checked) { 
RDebugUtils.currentLine=4259846;
 //BA.debugLineNum = 4259846;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128,";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (128),(int) (128),(int) (128)));
 }else {
RDebugUtils.currentLine=4259848;
 //BA.debugLineNum = 4259848;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 }else {
RDebugUtils.currentLine=4259851;
 //BA.debugLineNum = 4259851;BA.debugLine="If Checked Then";
if (_checked) { 
RDebugUtils.currentLine=4259852;
 //BA.debugLineNum = 4259852;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128,";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (128),(int) (128),(int) (128)));
 }else {
RDebugUtils.currentLine=4259854;
 //BA.debugLineNum = 4259854;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 };
RDebugUtils.currentLine=4259859;
 //BA.debugLineNum = 4259859;BA.debugLine="Dim key As String = \"checked_\" & currentList & \"_";
_key = "checked_"+mostCurrent._currentlist+"_"+_tasklbl.getText();
RDebugUtils.currentLine=4259860;
 //BA.debugLineNum = 4259860;BA.debugLine="kvs.Put(key, Checked)";
_kvs._put(_key,(Object)(_checked));
RDebugUtils.currentLine=4259862;
 //BA.debugLineNum = 4259862;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=4259864;
 //BA.debugLineNum = 4259864;BA.debugLine="End Sub";
return "";
}
public static String  _taskcheckboxgrp_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskcheckboxgrp_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskcheckboxgrp_checkedchange", new Object[] {_checked}));}
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cb = null;
anywheresoftware.b4a.objects.collections.List _cbctx = null;
String _code = "";
String _currentgrplist = "";
anywheresoftware.b4a.objects.LabelWrapper _tasklbl = null;
String _key = "";
RDebugUtils.currentLine=35520512;
 //BA.debugLineNum = 35520512;BA.debugLine="Sub taskCheckboxGrp_CheckedChange(Checked As Boole";
RDebugUtils.currentLine=35520513;
 //BA.debugLineNum = 35520513;BA.debugLine="Dim cb As CheckBox = Sender";
_cb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
_cb = (anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper(), (android.widget.CheckBox)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=35520514;
 //BA.debugLineNum = 35520514;BA.debugLine="Dim cbCtx As List = cb.Tag";
_cbctx = new anywheresoftware.b4a.objects.collections.List();
_cbctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_cb.getTag()));
RDebugUtils.currentLine=35520515;
 //BA.debugLineNum = 35520515;BA.debugLine="Dim code As String = cbCtx.Get(0)";
_code = BA.ObjectToString(_cbctx.Get((int) (0)));
RDebugUtils.currentLine=35520516;
 //BA.debugLineNum = 35520516;BA.debugLine="Dim currentGrpList As String = cbCtx.Get(1)";
_currentgrplist = BA.ObjectToString(_cbctx.Get((int) (1)));
RDebugUtils.currentLine=35520517;
 //BA.debugLineNum = 35520517;BA.debugLine="Dim taskLBL As Label = cbCtx.Get(2)";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
_tasklbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_cbctx.Get((int) (2))));
RDebugUtils.currentLine=35520519;
 //BA.debugLineNum = 35520519;BA.debugLine="If Checked Then";
if (_checked) { 
RDebugUtils.currentLine=35520520;
 //BA.debugLineNum = 35520520;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128, 1";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (128),(int) (128),(int) (128)));
 }else {
RDebugUtils.currentLine=35520522;
 //BA.debugLineNum = 35520522;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=35520523;
 //BA.debugLineNum = 35520523;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=35520525;
 //BA.debugLineNum = 35520525;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 };
RDebugUtils.currentLine=35520529;
 //BA.debugLineNum = 35520529;BA.debugLine="Dim key As String = \"group_checked_\" & code & \"_\"";
_key = "group_checked_"+_code+"_"+_currentgrplist+"_"+_tasklbl.getText();
RDebugUtils.currentLine=35520530;
 //BA.debugLineNum = 35520530;BA.debugLine="kvs.Put(key, Checked)";
_kvs._put(_key,(Object)(_checked));
RDebugUtils.currentLine=35520531;
 //BA.debugLineNum = 35520531;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=35520532;
 //BA.debugLineNum = 35520532;BA.debugLine="End Sub";
return "";
}
public static void  _taskslist_itemlongclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskslist_itemlongclick", false))
	 {Debug.delegate(mostCurrent.activityBA, "taskslist_itemlongclick", new Object[] {_index,_value}); return;}
ResumableSub_tasksList_ItemLongClick rsub = new ResumableSub_tasksList_ItemLongClick(null,_index,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_tasksList_ItemLongClick extends BA.ResumableSub {
public ResumableSub_tasksList_ItemLongClick(b4a.example.todoactivity parent,int _index,Object _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
b4a.example.todoactivity parent;
int _index;
Object _value;
int _res = 0;
String _key = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="todoactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4128770;
 //BA.debugLineNum = 4128770;BA.debugLine="If Value = \"\" Then Return";
if (true) break;

case 1:
//if
this.state = 6;
if ((_value).equals((Object)(""))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
RDebugUtils.currentLine=4128772;
 //BA.debugLineNum = 4128772;BA.debugLine="Msgbox2Async(\"Delete or rename this task?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this task?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4128773;
 //BA.debugLineNum = 4128773;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "taskslist_itemlongclick"), null);
this.state = 13;
return;
case 13:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=4128775;
 //BA.debugLineNum = 4128775;BA.debugLine="If res = DialogResponse.POSITIVE Then ' Rename";
if (true) break;

case 7:
//if
this.state = 12;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=4128778;
 //BA.debugLineNum = 4128778;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then ' Dele";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 11;
}}
if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=4128776;
 //BA.debugLineNum = 4128776;BA.debugLine="showRenameTaskPanel(Index, Value)";
_showrenametaskpanel(_index,BA.ObjectToString(_value));
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=4128779;
 //BA.debugLineNum = 4128779;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+parent.mostCurrent._currentlist;
RDebugUtils.currentLine=4128780;
 //BA.debugLineNum = 4128780;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_key)));
RDebugUtils.currentLine=4128781;
 //BA.debugLineNum = 4128781;BA.debugLine="savedTasks.RemoveAt(Index)";
_savedtasks.RemoveAt(_index);
RDebugUtils.currentLine=4128782;
 //BA.debugLineNum = 4128782;BA.debugLine="kvs.Put(key, savedTasks)";
parent._kvs._put(_key,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=4128783;
 //BA.debugLineNum = 4128783;BA.debugLine="kvs.Remove(\"checked_\" & currentList & \"_\" & Valu";
parent._kvs._remove("checked_"+parent.mostCurrent._currentlist+"_"+BA.ObjectToString(_value));
RDebugUtils.currentLine=4128784;
 //BA.debugLineNum = 4128784;BA.debugLine="tasksList.RemoveAt(Index)";
parent.mostCurrent._taskslist._removeat(_index);
RDebugUtils.currentLine=4128785;
 //BA.debugLineNum = 4128785;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=4128786;
 //BA.debugLineNum = 4128786;BA.debugLine="ToastMessageShow(\"Task deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = -1;
;
RDebugUtils.currentLine=4128789;
 //BA.debugLineNum = 4128789;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _taskslistgrp_itemlongclick(int _index,Object _value) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskslistgrp_itemlongclick", false))
	 {Debug.delegate(mostCurrent.activityBA, "taskslistgrp_itemlongclick", new Object[] {_index,_value}); return;}
ResumableSub_tasksListGrp_ItemLongClick rsub = new ResumableSub_tasksListGrp_ItemLongClick(null,_index,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_tasksListGrp_ItemLongClick extends BA.ResumableSub {
public ResumableSub_tasksListGrp_ItemLongClick(b4a.example.todoactivity parent,int _index,Object _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
b4a.example.todoactivity parent;
int _index;
Object _value;
String _code = "";
String _currentgrplist = "";
int _res = 0;
String _taskkey = "";
anywheresoftware.b4a.objects.collections.List _savedtasks = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="todoactivity";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=35323905;
 //BA.debugLineNum = 35323905;BA.debugLine="If Value = \"\" Then Return";
if (true) break;

case 1:
//if
this.state = 6;
if ((_value).equals((Object)(""))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
RDebugUtils.currentLine=35323907;
 //BA.debugLineNum = 35323907;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(parent.mostCurrent._groupet.getTag());
RDebugUtils.currentLine=35323909;
 //BA.debugLineNum = 35323909;BA.debugLine="Dim currentGrpList As String = getCurrentGrpList";
_currentgrplist = _getcurrentgrplist();
RDebugUtils.currentLine=35323911;
 //BA.debugLineNum = 35323911;BA.debugLine="Msgbox2Async(\"Delete or rename this task?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this task?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=35323912;
 //BA.debugLineNum = 35323912;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "taskslistgrp_itemlongclick"), null);
this.state = 13;
return;
case 13:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=35323914;
 //BA.debugLineNum = 35323914;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=35323918;
 //BA.debugLineNum = 35323918;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 11;
}}
if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=35323916;
 //BA.debugLineNum = 35323916;BA.debugLine="showRenameTaskPanelGrp(Index, Value, code, curre";
_showrenametaskpanelgrp(_index,BA.ObjectToString(_value),_code,_currentgrplist);
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=35323919;
 //BA.debugLineNum = 35323919;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=35323920;
 //BA.debugLineNum = 35323920;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_taskkey)));
RDebugUtils.currentLine=35323921;
 //BA.debugLineNum = 35323921;BA.debugLine="savedTasks.RemoveAt(Index)";
_savedtasks.RemoveAt(_index);
RDebugUtils.currentLine=35323922;
 //BA.debugLineNum = 35323922;BA.debugLine="kvs.Put(taskKey, savedTasks)";
parent._kvs._put(_taskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=35323923;
 //BA.debugLineNum = 35323923;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & curre";
parent._kvs._remove("group_checked_"+_code+"_"+_currentgrplist+"_"+BA.ObjectToString(_value));
RDebugUtils.currentLine=35323924;
 //BA.debugLineNum = 35323924;BA.debugLine="tasksListGrp.RemoveAt(Index)";
parent.mostCurrent._taskslistgrp._removeat(_index);
RDebugUtils.currentLine=35323925;
 //BA.debugLineNum = 35323925;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=35323926;
 //BA.debugLineNum = 35323926;BA.debugLine="ToastMessageShow(\"Task deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = -1;
;
RDebugUtils.currentLine=35323928;
 //BA.debugLineNum = 35323928;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
}