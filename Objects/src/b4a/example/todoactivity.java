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
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.collections.List _savedlists = null;
String _title = "";
RDebugUtils.currentLine=4980736;
 //BA.debugLineNum = 4980736;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=4980737;
 //BA.debugLineNum = 4980737;BA.debugLine="Activity.LoadLayout(\"MAINtodolistlayout\")";
mostCurrent._activity.LoadLayout("MAINtodolistlayout",mostCurrent.activityBA);
RDebugUtils.currentLine=4980739;
 //BA.debugLineNum = 4980739;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=4980741;
 //BA.debugLineNum = 4980741;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=4980742;
 //BA.debugLineNum = 4980742;BA.debugLine="TabHost1.AddTab(\"Your Lists\", \"todolistLayout.";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"Your Lists","todolistLayout.bal");
RDebugUtils.currentLine=4980743;
 //BA.debugLineNum = 4980743;BA.debugLine="TabHost1.AddTab(\"Groups\", \"grouptodolistlayout";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"Groups","grouptodolistlayout.bal");
 }else {
RDebugUtils.currentLine=4980745;
 //BA.debugLineNum = 4980745;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark\")";
mostCurrent._activity.LoadLayout("todoListLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=4980748;
 //BA.debugLineNum = 4980748;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=4980749;
 //BA.debugLineNum = 4980749;BA.debugLine="Activity.LoadLayout(\"todoListLayout2\")";
mostCurrent._activity.LoadLayout("todoListLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=4980751;
 //BA.debugLineNum = 4980751;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark2\")";
mostCurrent._activity.LoadLayout("todoListLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=4980754;
 //BA.debugLineNum = 4980754;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=4980755;
 //BA.debugLineNum = 4980755;BA.debugLine="Activity.LoadLayout(\"todoListLayout3\")";
mostCurrent._activity.LoadLayout("todoListLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=4980757;
 //BA.debugLineNum = 4980757;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark3\")";
mostCurrent._activity.LoadLayout("todoListLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=4980761;
 //BA.debugLineNum = 4980761;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=4980762;
 //BA.debugLineNum = 4980762;BA.debugLine="addTitleTextArea.Background = Null";
mostCurrent._addtitletextarea.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=4980764;
 //BA.debugLineNum = 4980764;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=4980765;
 //BA.debugLineNum = 4980765;BA.debugLine="tasksList.GetBase.Visible = False";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4980767;
 //BA.debugLineNum = 4980767;BA.debugLine="kvs = Starter.taskKvs";
_kvs = mostCurrent._starter._taskkvs /*b4a.example3.keyvaluestore*/ ;
RDebugUtils.currentLine=4980769;
 //BA.debugLineNum = 4980769;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=4980770;
 //BA.debugLineNum = 4980770;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=4980771;
 //BA.debugLineNum = 4980771;BA.debugLine="For Each title As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group30 = _savedlists;
final int groupLen30 = group30.getSize()
;int index30 = 0;
;
for (; index30 < groupLen30;index30++){
_title = BA.ObjectToString(group30.Get(index30));
RDebugUtils.currentLine=4980772;
 //BA.debugLineNum = 4980772;BA.debugLine="listsList.AddTextItem(title, title)";
mostCurrent._listslist._addtextitem((Object)(_title),(Object)(_title));
 }
};
 };
RDebugUtils.currentLine=4980776;
 //BA.debugLineNum = 4980776;BA.debugLine="loadMyGroups";
_loadmygroups();
RDebugUtils.currentLine=4980778;
 //BA.debugLineNum = 4980778;BA.debugLine="End Sub";
return "";
}
public static String  _newaddtaskbtn() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newaddtaskbtn", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newaddtaskbtn", null));}
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=5046272;
 //BA.debugLineNum = 5046272;BA.debugLine="Sub newAddTaskBtn";
RDebugUtils.currentLine=5046273;
 //BA.debugLineNum = 5046273;BA.debugLine="addTaskBtnPNL.Initialize(\"addTaskBtnPNL\")";
mostCurrent._addtaskbtnpnl.Initialize(mostCurrent.activityBA,"addTaskBtnPNL");
RDebugUtils.currentLine=5046274;
 //BA.debugLineNum = 5046274;BA.debugLine="addTaskBtnPNL.SetLayout(10dip, 0dip, 190dip, 70di";
mostCurrent._addtaskbtnpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=5046275;
 //BA.debugLineNum = 5046275;BA.debugLine="addTaskBtnPNL.Color = Colors.ARGB(0, 255, 255, 25";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=5046276;
 //BA.debugLineNum = 5046276;BA.debugLine="addTaskBtn.Initialize(\"addTaskBtn\")";
mostCurrent._addtaskbtn.Initialize(mostCurrent.activityBA,"addTaskBtn");
RDebugUtils.currentLine=5046277;
 //BA.debugLineNum = 5046277;BA.debugLine="addTaskBtn.Text = \"+ add a task \"";
mostCurrent._addtaskbtn.setText(BA.ObjectToCharSequence("+ add a task "));
RDebugUtils.currentLine=5046278;
 //BA.debugLineNum = 5046278;BA.debugLine="addTaskBtnPNL.AddView(addTaskBtn, 10dip, 20dip, a";
mostCurrent._addtaskbtnpnl.AddView((android.view.View)(mostCurrent._addtaskbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=5046279;
 //BA.debugLineNum = 5046279;BA.debugLine="tasksList.Add(addTaskBtnPNL, \"\")";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._addtaskbtnpnl.getObject())),(Object)(""));
RDebugUtils.currentLine=5046281;
 //BA.debugLineNum = 5046281;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=5046282;
 //BA.debugLineNum = 5046282;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=5046284;
 //BA.debugLineNum = 5046284;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5046285;
 //BA.debugLineNum = 5046285;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=5046286;
 //BA.debugLineNum = 5046286;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=5046287;
 //BA.debugLineNum = 5046287;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5046288;
 //BA.debugLineNum = 5046288;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5046290;
 //BA.debugLineNum = 5046290;BA.debugLine="addTaskBtnPNL.Color = Colors.Transparent";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=5046291;
 //BA.debugLineNum = 5046291;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=5046292;
 //BA.debugLineNum = 5046292;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5046293;
 //BA.debugLineNum = 5046293;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=5046296;
 //BA.debugLineNum = 5046296;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5046297;
 //BA.debugLineNum = 5046297;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=5046298;
 //BA.debugLineNum = 5046298;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=5046299;
 //BA.debugLineNum = 5046299;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5046300;
 //BA.debugLineNum = 5046300;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5046302;
 //BA.debugLineNum = 5046302;BA.debugLine="addTaskBtnPNL.Color = Colors.Transparent";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=5046303;
 //BA.debugLineNum = 5046303;BA.debugLine="cd.Initialize(Colors.ARGB(120, 255, 255, 255),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (255),(int) (255),(int) (255)),(int) (200));
RDebugUtils.currentLine=5046304;
 //BA.debugLineNum = 5046304;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5046305;
 //BA.debugLineNum = 5046305;BA.debugLine="addTaskBtn.TextColor = Colors.Black";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
case 2: {
RDebugUtils.currentLine=5046308;
 //BA.debugLineNum = 5046308;BA.debugLine="addTaskBtn.Typeface = pixeltf";
mostCurrent._addtaskbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=5046309;
 //BA.debugLineNum = 5046309;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5046310;
 //BA.debugLineNum = 5046310;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=5046311;
 //BA.debugLineNum = 5046311;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=5046312;
 //BA.debugLineNum = 5046312;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5046313;
 //BA.debugLineNum = 5046313;BA.debugLine="addTaskBtn.TextColor = Colors.White";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5046315;
 //BA.debugLineNum = 5046315;BA.debugLine="addTaskBtnPNL.Color = Colors.ARGB(0, 232, 213,";
mostCurrent._addtaskbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (232),(int) (213),(int) (179)));
RDebugUtils.currentLine=5046316;
 //BA.debugLineNum = 5046316;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=5046317;
 //BA.debugLineNum = 5046317;BA.debugLine="addTaskBtn.Background = cd";
mostCurrent._addtaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5046318;
 //BA.debugLineNum = 5046318;BA.debugLine="addTaskBtn.TextColor = Colors.Black";
mostCurrent._addtaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
}
;
RDebugUtils.currentLine=5046322;
 //BA.debugLineNum = 5046322;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5898240;
 //BA.debugLineNum = 5898240;BA.debugLine="Sub loadMyGroups";
RDebugUtils.currentLine=5898241;
 //BA.debugLineNum = 5898241;BA.debugLine="groupList.Clear";
mostCurrent._grouplist._clear();
RDebugUtils.currentLine=5898242;
 //BA.debugLineNum = 5898242;BA.debugLine="groupET.Text = \"\"";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5898243;
 //BA.debugLineNum = 5898243;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5898244;
 //BA.debugLineNum = 5898244;BA.debugLine="listsListGrp.Clear";
mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=5898245;
 //BA.debugLineNum = 5898245;BA.debugLine="listsListGrp.GetBase.Visible = False";
mostCurrent._listslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5898246;
 //BA.debugLineNum = 5898246;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=5898247;
 //BA.debugLineNum = 5898247;BA.debugLine="tasksListGrp.GetBase.Visible = False";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5898248;
 //BA.debugLineNum = 5898248;BA.debugLine="progressBarGrp.Progress = 0";
mostCurrent._progressbargrp.setProgress((int) (0));
RDebugUtils.currentLine=5898249;
 //BA.debugLineNum = 5898249;BA.debugLine="progressNumberGrp.Text = \"\"";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5898250;
 //BA.debugLineNum = 5898250;BA.debugLine="progressPercentGrp.Text = \"\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5898252;
 //BA.debugLineNum = 5898252;BA.debugLine="If kvs.ContainsKey(\"groups\") = False Then Return";
if (_kvs._containskey("groups")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=5898254;
 //BA.debugLineNum = 5898254;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));
RDebugUtils.currentLine=5898255;
 //BA.debugLineNum = 5898255;BA.debugLine="For Each code As String In allGroups";
{
final anywheresoftware.b4a.BA.IterableList group13 = _allgroups;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_code = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=5898256;
 //BA.debugLineNum = 5898256;BA.debugLine="Dim membersKey As String = \"group_members_\" & co";
_memberskey = "group_members_"+_code;
RDebugUtils.currentLine=5898257;
 //BA.debugLineNum = 5898257;BA.debugLine="If kvs.ContainsKey(membersKey) Then";
if (_kvs._containskey(_memberskey)) { 
RDebugUtils.currentLine=5898258;
 //BA.debugLineNum = 5898258;BA.debugLine="Dim members As List = kvs.Get(membersKey)";
_members = new anywheresoftware.b4a.objects.collections.List();
_members = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_memberskey)));
RDebugUtils.currentLine=5898259;
 //BA.debugLineNum = 5898259;BA.debugLine="Dim myID As String = Starter.currentUserID ' ho";
_myid = mostCurrent._starter._currentuserid /*String*/ ;
RDebugUtils.currentLine=5898260;
 //BA.debugLineNum = 5898260;BA.debugLine="For Each m As String In members";
{
final anywheresoftware.b4a.BA.IterableList group18 = _members;
final int groupLen18 = group18.getSize()
;int index18 = 0;
;
for (; index18 < groupLen18;index18++){
_m = BA.ObjectToString(group18.Get(index18));
RDebugUtils.currentLine=5898261;
 //BA.debugLineNum = 5898261;BA.debugLine="If m = myID Then";
if ((_m).equals(_myid)) { 
RDebugUtils.currentLine=5898262;
 //BA.debugLineNum = 5898262;BA.debugLine="Dim groupName As String = kvs.Get(\"group_name";
_groupname = BA.ObjectToString(_kvs._get("group_name_"+_code));
RDebugUtils.currentLine=5898263;
 //BA.debugLineNum = 5898263;BA.debugLine="groupList.AddTextItem(groupName, code)";
mostCurrent._grouplist._addtextitem((Object)(_groupname),(Object)(_code));
RDebugUtils.currentLine=5898264;
 //BA.debugLineNum = 5898264;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 }
};
RDebugUtils.currentLine=5898269;
 //BA.debugLineNum = 5898269;BA.debugLine="End Sub";
return "";
}
public static String  _addtaskbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtaskbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtaskbtn_click", null));}
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=5439488;
 //BA.debugLineNum = 5439488;BA.debugLine="Sub addTaskBtn_Click";
RDebugUtils.currentLine=5439490;
 //BA.debugLineNum = 5439490;BA.debugLine="addTaskBtn.Enabled = False";
mostCurrent._addtaskbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5439491;
 //BA.debugLineNum = 5439491;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1)";
mostCurrent._taskslist._removeat((int) (mostCurrent._taskslist._getsize()-1));
RDebugUtils.currentLine=5439493;
 //BA.debugLineNum = 5439493;BA.debugLine="addTaskPanel.Initialize(\"addTaskPanel\")";
mostCurrent._addtaskpanel.Initialize(mostCurrent.activityBA,"addTaskPanel");
RDebugUtils.currentLine=5439494;
 //BA.debugLineNum = 5439494;BA.debugLine="addTaskPanel.SetLayout(10dip, 0, 20dip, 120dip)";
mostCurrent._addtaskpanel.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=5439495;
 //BA.debugLineNum = 5439495;BA.debugLine="addTaskPanel.Color = Colors.ARGB(0, 255, 255, 255";
mostCurrent._addtaskpanel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=5439497;
 //BA.debugLineNum = 5439497;BA.debugLine="addTaskTextArea.Initialize(\"addTodoText\")";
mostCurrent._addtasktextarea.Initialize(mostCurrent.activityBA,"addTodoText");
RDebugUtils.currentLine=5439498;
 //BA.debugLineNum = 5439498;BA.debugLine="addTaskTextArea.Hint = \"Add a task...\"";
mostCurrent._addtasktextarea.setHint("Add a task...");
RDebugUtils.currentLine=5439499;
 //BA.debugLineNum = 5439499;BA.debugLine="addTaskTextArea.Tag = Null";
mostCurrent._addtasktextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=5439501;
 //BA.debugLineNum = 5439501;BA.debugLine="enterTaskBtn.Initialize(\"enterTaskBtn\")";
mostCurrent._entertaskbtn.Initialize(mostCurrent.activityBA,"enterTaskBtn");
RDebugUtils.currentLine=5439502;
 //BA.debugLineNum = 5439502;BA.debugLine="enterTaskBtn.Text = \"Enter task\"";
mostCurrent._entertaskbtn.setText(BA.ObjectToCharSequence("Enter task"));
RDebugUtils.currentLine=5439504;
 //BA.debugLineNum = 5439504;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=5439505;
 //BA.debugLineNum = 5439505;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=5439507;
 //BA.debugLineNum = 5439507;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5439508;
 //BA.debugLineNum = 5439508;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5439509;
 //BA.debugLineNum = 5439509;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5439510;
 //BA.debugLineNum = 5439510;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (59),(int) (117),(int) (151)),(int) (200));
RDebugUtils.currentLine=5439511;
 //BA.debugLineNum = 5439511;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5439512;
 //BA.debugLineNum = 5439512;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5439514;
 //BA.debugLineNum = 5439514;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(120, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)));
RDebugUtils.currentLine=5439515;
 //BA.debugLineNum = 5439515;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5439516;
 //BA.debugLineNum = 5439516;BA.debugLine="cd.Initialize(Colors.ARGB(120, 98, 43, 20), 20";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=5439517;
 //BA.debugLineNum = 5439517;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5439518;
 //BA.debugLineNum = 5439518;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=5439521;
 //BA.debugLineNum = 5439521;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5439522;
 //BA.debugLineNum = 5439522;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5439523;
 //BA.debugLineNum = 5439523;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5439524;
 //BA.debugLineNum = 5439524;BA.debugLine="cd.Initialize(Colors.ARGB(100, 255, 255, 255),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (255),(int) (255),(int) (255)),(int) (200));
RDebugUtils.currentLine=5439525;
 //BA.debugLineNum = 5439525;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5439526;
 //BA.debugLineNum = 5439526;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5439528;
 //BA.debugLineNum = 5439528;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(255, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (137),(int) (162),(int) (185)));
RDebugUtils.currentLine=5439529;
 //BA.debugLineNum = 5439529;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5439530;
 //BA.debugLineNum = 5439530;BA.debugLine="cd.Initialize(Colors.ARGB(255, 137, 162, 185),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (137),(int) (162),(int) (185)),(int) (200));
RDebugUtils.currentLine=5439531;
 //BA.debugLineNum = 5439531;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5439532;
 //BA.debugLineNum = 5439532;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 2: {
RDebugUtils.currentLine=5439535;
 //BA.debugLineNum = 5439535;BA.debugLine="addTaskTextArea.Typeface = pixeltf";
mostCurrent._addtasktextarea.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=5439536;
 //BA.debugLineNum = 5439536;BA.debugLine="enterTaskBtn.Typeface = pixeltf";
mostCurrent._entertaskbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=5439537;
 //BA.debugLineNum = 5439537;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5439538;
 //BA.debugLineNum = 5439538;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5439539;
 //BA.debugLineNum = 5439539;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5439540;
 //BA.debugLineNum = 5439540;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=5439541;
 //BA.debugLineNum = 5439541;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5439542;
 //BA.debugLineNum = 5439542;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5439544;
 //BA.debugLineNum = 5439544;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=5439545;
 //BA.debugLineNum = 5439545;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=5439546;
 //BA.debugLineNum = 5439546;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=5439547;
 //BA.debugLineNum = 5439547;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5439548;
 //BA.debugLineNum = 5439548;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
}
;
RDebugUtils.currentLine=5439552;
 //BA.debugLineNum = 5439552;BA.debugLine="addTaskPanel.AddView(addTaskTextArea, 0, 0, addTa";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._addtasktextarea.getObject()),(int) (0),(int) (0),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=5439553;
 //BA.debugLineNum = 5439553;BA.debugLine="addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addT";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._entertaskbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=5439555;
 //BA.debugLineNum = 5439555;BA.debugLine="tasksList.Add(addTaskPanel, addTaskPanel)";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._addtaskpanel.getObject())),(Object)(mostCurrent._addtaskpanel.getObject()));
RDebugUtils.currentLine=5439557;
 //BA.debugLineNum = 5439557;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7143424;
 //BA.debugLineNum = 7143424;BA.debugLine="Sub addTaskBtnGrp_Click";
RDebugUtils.currentLine=7143425;
 //BA.debugLineNum = 7143425;BA.debugLine="Dim addBtn As Button = Sender";
_addbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
_addbtn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=7143426;
 //BA.debugLineNum = 7143426;BA.debugLine="Dim ctx As List = addBtn.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_addbtn.getTag()));
RDebugUtils.currentLine=7143427;
 //BA.debugLineNum = 7143427;BA.debugLine="Dim code As String = ctx.Get(0)";
_code = BA.ObjectToString(_ctx.Get((int) (0)));
RDebugUtils.currentLine=7143428;
 //BA.debugLineNum = 7143428;BA.debugLine="Dim currentGrpList As String = ctx.Get(1)";
_currentgrplist = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=7143430;
 //BA.debugLineNum = 7143430;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
mostCurrent._taskslistgrp._removeat((int) (mostCurrent._taskslistgrp._getsize()-1));
RDebugUtils.currentLine=7143432;
 //BA.debugLineNum = 7143432;BA.debugLine="Dim addPNL As Panel";
_addpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=7143433;
 //BA.debugLineNum = 7143433;BA.debugLine="addPNL.Initialize(\"addTaskPNLGrp\")";
_addpnl.Initialize(mostCurrent.activityBA,"addTaskPNLGrp");
RDebugUtils.currentLine=7143434;
 //BA.debugLineNum = 7143434;BA.debugLine="addPNL.SetLayout(10dip, 0, 20dip, 120dip)";
_addpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=7143435;
 //BA.debugLineNum = 7143435;BA.debugLine="addPNL.Color = Colors.Transparent";
_addpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=7143437;
 //BA.debugLineNum = 7143437;BA.debugLine="Dim addET As EditText";
_addet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=7143438;
 //BA.debugLineNum = 7143438;BA.debugLine="addET.Initialize(\"addTaskETGrp\")";
_addet.Initialize(mostCurrent.activityBA,"addTaskETGrp");
RDebugUtils.currentLine=7143439;
 //BA.debugLineNum = 7143439;BA.debugLine="addET.Hint = \"Add a task...\"";
_addet.setHint("Add a task...");
RDebugUtils.currentLine=7143440;
 //BA.debugLineNum = 7143440;BA.debugLine="addET.Tag = Null";
_addet.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=7143442;
 //BA.debugLineNum = 7143442;BA.debugLine="Dim confirmBtn As Button";
_confirmbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=7143443;
 //BA.debugLineNum = 7143443;BA.debugLine="confirmBtn.Initialize(\"enterTaskBtnGrp\")";
_confirmbtn.Initialize(mostCurrent.activityBA,"enterTaskBtnGrp");
RDebugUtils.currentLine=7143444;
 //BA.debugLineNum = 7143444;BA.debugLine="confirmBtn.Text = \"Enter task\"";
_confirmbtn.setText(BA.ObjectToCharSequence("Enter task"));
RDebugUtils.currentLine=7143446;
 //BA.debugLineNum = 7143446;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=7143447;
 //BA.debugLineNum = 7143447;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=7143448;
 //BA.debugLineNum = 7143448;BA.debugLine="confirmBtn.Background = cd";
_confirmbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=7143449;
 //BA.debugLineNum = 7143449;BA.debugLine="confirmBtn.TextColor = Colors.White";
_confirmbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=7143452;
 //BA.debugLineNum = 7143452;BA.debugLine="Dim ctx2 As List";
_ctx2 = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=7143453;
 //BA.debugLineNum = 7143453;BA.debugLine="ctx2.Initialize";
_ctx2.Initialize();
RDebugUtils.currentLine=7143454;
 //BA.debugLineNum = 7143454;BA.debugLine="ctx2.Add(code)";
_ctx2.Add((Object)(_code));
RDebugUtils.currentLine=7143455;
 //BA.debugLineNum = 7143455;BA.debugLine="ctx2.Add(currentGrpList)";
_ctx2.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=7143456;
 //BA.debugLineNum = 7143456;BA.debugLine="ctx2.Add(addET)     ' index 2: the EditText";
_ctx2.Add((Object)(_addet.getObject()));
RDebugUtils.currentLine=7143457;
 //BA.debugLineNum = 7143457;BA.debugLine="ctx2.Add(Null)      ' index 3: oldTask (Null = ne";
_ctx2.Add(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=7143458;
 //BA.debugLineNum = 7143458;BA.debugLine="confirmBtn.Tag = ctx2";
_confirmbtn.setTag((Object)(_ctx2.getObject()));
RDebugUtils.currentLine=7143460;
 //BA.debugLineNum = 7143460;BA.debugLine="addPNL.AddView(addET, 0, 0, 190dip, 60dip)";
_addpnl.AddView((android.view.View)(_addet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=7143461;
 //BA.debugLineNum = 7143461;BA.debugLine="addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40di";
_addpnl.AddView((android.view.View)(_confirmbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=7143463;
 //BA.debugLineNum = 7143463;BA.debugLine="tasksListGrp.Add(addPNL, addPNL)";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_addpnl.getObject())),(Object)(_addpnl.getObject()));
RDebugUtils.currentLine=7143464;
 //BA.debugLineNum = 7143464;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5177344;
 //BA.debugLineNum = 5177344;BA.debugLine="Sub addTitleTextArea_EnterPressed";
RDebugUtils.currentLine=5177347;
 //BA.debugLineNum = 5177347;BA.debugLine="If addTitleTextArea.Tag <> Null And addTitleTextA";
if (mostCurrent._addtitletextarea.getTag()!= null && mostCurrent._addtitletextarea.getTag() instanceof java.util.List) { 
RDebugUtils.currentLine=5177348;
 //BA.debugLineNum = 5177348;BA.debugLine="Dim ctx As List = addTitleTextArea.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(mostCurrent._addtitletextarea.getTag()));
RDebugUtils.currentLine=5177349;
 //BA.debugLineNum = 5177349;BA.debugLine="Dim oldIndex As Int = ctx.Get(0)";
_oldindex = (int)(BA.ObjectToNumber(_ctx.Get((int) (0))));
RDebugUtils.currentLine=5177350;
 //BA.debugLineNum = 5177350;BA.debugLine="Dim oldTitle As String = ctx.Get(1)";
_oldtitle = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=5177351;
 //BA.debugLineNum = 5177351;BA.debugLine="Dim newTitle As String = addTitleTextArea.Text.T";
_newtitle = mostCurrent._addtitletextarea.getText().trim();
RDebugUtils.currentLine=5177353;
 //BA.debugLineNum = 5177353;BA.debugLine="If newTitle = \"\" Or newTitle = oldTitle Then";
if ((_newtitle).equals("") || (_newtitle).equals(_oldtitle)) { 
RDebugUtils.currentLine=5177354;
 //BA.debugLineNum = 5177354;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=5177355;
 //BA.debugLineNum = 5177355;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=5177356;
 //BA.debugLineNum = 5177356;BA.debugLine="addTitleTextArea.Enabled = False";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5177357;
 //BA.debugLineNum = 5177357;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=5177361;
 //BA.debugLineNum = 5177361;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=5177362;
 //BA.debugLineNum = 5177362;BA.debugLine="For Each existingTitle As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group13 = _savedlists;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_existingtitle = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=5177363;
 //BA.debugLineNum = 5177363;BA.debugLine="If existingTitle = newTitle Then";
if ((_existingtitle).equals(_newtitle)) { 
RDebugUtils.currentLine=5177364;
 //BA.debugLineNum = 5177364;BA.debugLine="MsgboxAsync(\"A list with that name already exi";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("A list with that name already exists."),BA.ObjectToCharSequence("Duplicate title"),processBA);
RDebugUtils.currentLine=5177365;
 //BA.debugLineNum = 5177365;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=5177370;
 //BA.debugLineNum = 5177370;BA.debugLine="savedLists.Set(oldIndex, newTitle)";
_savedlists.Set(_oldindex,(Object)(_newtitle));
RDebugUtils.currentLine=5177371;
 //BA.debugLineNum = 5177371;BA.debugLine="kvs.Put(\"lists\", savedLists)";
_kvs._put("lists",(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=5177374;
 //BA.debugLineNum = 5177374;BA.debugLine="Dim oldKey As String = \"list_\" & oldTitle";
_oldkey = "list_"+_oldtitle;
RDebugUtils.currentLine=5177375;
 //BA.debugLineNum = 5177375;BA.debugLine="Dim newKey As String = \"list_\" & newTitle";
_newkey = "list_"+_newtitle;
RDebugUtils.currentLine=5177376;
 //BA.debugLineNum = 5177376;BA.debugLine="If kvs.ContainsKey(oldKey) Then";
if (_kvs._containskey(_oldkey)) { 
RDebugUtils.currentLine=5177377;
 //BA.debugLineNum = 5177377;BA.debugLine="Dim savedTasks As List = kvs.Get(oldKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_oldkey)));
RDebugUtils.currentLine=5177378;
 //BA.debugLineNum = 5177378;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group25 = _savedtasks;
final int groupLen25 = group25.getSize()
;int index25 = 0;
;
for (; index25 < groupLen25;index25++){
_task = BA.ObjectToString(group25.Get(index25));
RDebugUtils.currentLine=5177379;
 //BA.debugLineNum = 5177379;BA.debugLine="Dim oldCK As String = \"checked_\" & oldTitle &";
_oldck = "checked_"+_oldtitle+"_"+_task;
RDebugUtils.currentLine=5177380;
 //BA.debugLineNum = 5177380;BA.debugLine="Dim newCK As String = \"checked_\" & newTitle &";
_newck = "checked_"+_newtitle+"_"+_task;
RDebugUtils.currentLine=5177381;
 //BA.debugLineNum = 5177381;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=5177382;
 //BA.debugLineNum = 5177382;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=5177383;
 //BA.debugLineNum = 5177383;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
 }
};
RDebugUtils.currentLine=5177386;
 //BA.debugLineNum = 5177386;BA.debugLine="kvs.Put(newKey, savedTasks)";
_kvs._put(_newkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=5177387;
 //BA.debugLineNum = 5177387;BA.debugLine="kvs.Remove(oldKey)";
_kvs._remove(_oldkey);
 };
RDebugUtils.currentLine=5177391;
 //BA.debugLineNum = 5177391;BA.debugLine="If currentList = oldTitle Then";
if ((mostCurrent._currentlist).equals(_oldtitle)) { 
RDebugUtils.currentLine=5177392;
 //BA.debugLineNum = 5177392;BA.debugLine="currentList = newTitle";
mostCurrent._currentlist = _newtitle;
 };
RDebugUtils.currentLine=5177396;
 //BA.debugLineNum = 5177396;BA.debugLine="listsList.Clear";
mostCurrent._listslist._clear();
RDebugUtils.currentLine=5177397;
 //BA.debugLineNum = 5177397;BA.debugLine="Dim savedLists2 As List = kvs.Get(\"lists\")";
_savedlists2 = new anywheresoftware.b4a.objects.collections.List();
_savedlists2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=5177398;
 //BA.debugLineNum = 5177398;BA.debugLine="For Each t As String In savedLists2";
{
final anywheresoftware.b4a.BA.IterableList group41 = _savedlists2;
final int groupLen41 = group41.getSize()
;int index41 = 0;
;
for (; index41 < groupLen41;index41++){
_t = BA.ObjectToString(group41.Get(index41));
RDebugUtils.currentLine=5177399;
 //BA.debugLineNum = 5177399;BA.debugLine="listsList.AddTextItem(t, t)";
mostCurrent._listslist._addtextitem((Object)(_t),(Object)(_t));
 }
};
RDebugUtils.currentLine=5177402;
 //BA.debugLineNum = 5177402;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=5177403;
 //BA.debugLineNum = 5177403;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=5177404;
 //BA.debugLineNum = 5177404;BA.debugLine="addTitleTextArea.Enabled = False";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5177405;
 //BA.debugLineNum = 5177405;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5177406;
 //BA.debugLineNum = 5177406;BA.debugLine="isAddingList = False";
_isaddinglist = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=5177407;
 //BA.debugLineNum = 5177407;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5177408;
 //BA.debugLineNum = 5177408;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=5177412;
 //BA.debugLineNum = 5177412;BA.debugLine="Dim title As String = addTitleTextArea.Text";
_title = mostCurrent._addtitletextarea.getText();
RDebugUtils.currentLine=5177414;
 //BA.debugLineNum = 5177414;BA.debugLine="Dim savedLists As List";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=5177415;
 //BA.debugLineNum = 5177415;BA.debugLine="savedLists.Initialize";
_savedlists.Initialize();
RDebugUtils.currentLine=5177417;
 //BA.debugLineNum = 5177417;BA.debugLine="If title = \"\" Then";
if ((_title).equals("")) { 
RDebugUtils.currentLine=5177418;
 //BA.debugLineNum = 5177418;BA.debugLine="title = \"Untitled\" & untitledNo";
_title = "Untitled"+BA.NumberToString(_untitledno);
RDebugUtils.currentLine=5177420;
 //BA.debugLineNum = 5177420;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=5177421;
 //BA.debugLineNum = 5177421;BA.debugLine="savedLists = kvs.Get(\"lists\")";
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=5177422;
 //BA.debugLineNum = 5177422;BA.debugLine="For Each existingTitle As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group59 = _savedlists;
final int groupLen59 = group59.getSize()
;int index59 = 0;
;
for (; index59 < groupLen59;index59++){
_existingtitle = BA.ObjectToString(group59.Get(index59));
RDebugUtils.currentLine=5177423;
 //BA.debugLineNum = 5177423;BA.debugLine="If title = existingTitle Then";
if ((_title).equals(_existingtitle)) { 
RDebugUtils.currentLine=5177424;
 //BA.debugLineNum = 5177424;BA.debugLine="untitledNo = untitledNo + 1";
_untitledno = (int) (_untitledno+1);
RDebugUtils.currentLine=5177425;
 //BA.debugLineNum = 5177425;BA.debugLine="title = \"Untitled\" & untitledNo";
_title = "Untitled"+BA.NumberToString(_untitledno);
 };
 }
};
 };
RDebugUtils.currentLine=5177430;
 //BA.debugLineNum = 5177430;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=5177433;
 //BA.debugLineNum = 5177433;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=5177434;
 //BA.debugLineNum = 5177434;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
RDebugUtils.currentLine=5177435;
 //BA.debugLineNum = 5177435;BA.debugLine="For Each existingTitle As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group70 = _savedlists;
final int groupLen70 = group70.getSize()
;int index70 = 0;
;
for (; index70 < groupLen70;index70++){
_existingtitle = BA.ObjectToString(group70.Get(index70));
RDebugUtils.currentLine=5177436;
 //BA.debugLineNum = 5177436;BA.debugLine="If existingTitle = title Then";
if ((_existingtitle).equals(_title)) { 
RDebugUtils.currentLine=5177437;
 //BA.debugLineNum = 5177437;BA.debugLine="MsgboxAsync(\"List already exists.\", \"Duplicate";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("List already exists."),BA.ObjectToCharSequence("Duplicate title"),processBA);
RDebugUtils.currentLine=5177438;
 //BA.debugLineNum = 5177438;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5177439;
 //BA.debugLineNum = 5177439;BA.debugLine="addTitleTextArea.Text = \"\"";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5177440;
 //BA.debugLineNum = 5177440;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 };
RDebugUtils.currentLine=5177445;
 //BA.debugLineNum = 5177445;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
if (_kvs._containskey("lists")) { 
RDebugUtils.currentLine=5177446;
 //BA.debugLineNum = 5177446;BA.debugLine="savedLists = kvs.Get(\"lists\")";
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("lists")));
 };
RDebugUtils.currentLine=5177449;
 //BA.debugLineNum = 5177449;BA.debugLine="savedLists.Add(title)";
_savedlists.Add((Object)(_title));
RDebugUtils.currentLine=5177450;
 //BA.debugLineNum = 5177450;BA.debugLine="kvs.Put(\"lists\", savedLists)";
_kvs._put("lists",(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=5177452;
 //BA.debugLineNum = 5177452;BA.debugLine="listsList.AddTextItem(title, title)";
mostCurrent._listslist._addtextitem((Object)(_title),(Object)(_title));
RDebugUtils.currentLine=5177454;
 //BA.debugLineNum = 5177454;BA.debugLine="currentList = title";
mostCurrent._currentlist = _title;
RDebugUtils.currentLine=5177455;
 //BA.debugLineNum = 5177455;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=5177456;
 //BA.debugLineNum = 5177456;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5177457;
 //BA.debugLineNum = 5177457;BA.debugLine="addTitleTextArea.Enabled = False";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5177459;
 //BA.debugLineNum = 5177459;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=5177460;
 //BA.debugLineNum = 5177460;BA.debugLine="tasksList.GetBase.Visible = True";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5177461;
 //BA.debugLineNum = 5177461;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=5177463;
 //BA.debugLineNum = 5177463;BA.debugLine="newListBtn.Enabled = True";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5177464;
 //BA.debugLineNum = 5177464;BA.debugLine="isAddingList = False";
_isaddinglist = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=5177465;
 //BA.debugLineNum = 5177465;BA.debugLine="progressNumber.Text = \"0 / 0 tasks done!\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence("0 / 0 tasks done!"));
RDebugUtils.currentLine=5177466;
 //BA.debugLineNum = 5177466;BA.debugLine="progressPercent.Text = \"0%\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence("0%"));
RDebugUtils.currentLine=5177467;
 //BA.debugLineNum = 5177467;BA.debugLine="progressBar.Progress = 0";
mostCurrent._progressbar.setProgress((int) (0));
RDebugUtils.currentLine=5177469;
 //BA.debugLineNum = 5177469;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6619136;
 //BA.debugLineNum = 6619136;BA.debugLine="Sub deleteGroup(code As String)";
RDebugUtils.currentLine=6619138;
 //BA.debugLineNum = 6619138;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=6619139;
 //BA.debugLineNum = 6619139;BA.debugLine="If kvs.ContainsKey(listsKey) Then";
if (_kvs._containskey(_listskey)) { 
RDebugUtils.currentLine=6619140;
 //BA.debugLineNum = 6619140;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));
RDebugUtils.currentLine=6619141;
 //BA.debugLineNum = 6619141;BA.debugLine="For Each listName As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group4 = _savedlists;
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_listname = BA.ObjectToString(group4.Get(index4));
RDebugUtils.currentLine=6619142;
 //BA.debugLineNum = 6619142;BA.debugLine="Dim taskKey As String = \"group_list_\" & code &";
_taskkey = "group_list_"+_code+"_"+_listname;
RDebugUtils.currentLine=6619143;
 //BA.debugLineNum = 6619143;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
if (_kvs._containskey(_taskkey)) { 
RDebugUtils.currentLine=6619144;
 //BA.debugLineNum = 6619144;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=6619145;
 //BA.debugLineNum = 6619145;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group8 = _savedtasks;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_task = BA.ObjectToString(group8.Get(index8));
RDebugUtils.currentLine=6619146;
 //BA.debugLineNum = 6619146;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & li";
_kvs._remove("group_checked_"+_code+"_"+_listname+"_"+_task);
 }
};
RDebugUtils.currentLine=6619148;
 //BA.debugLineNum = 6619148;BA.debugLine="kvs.Remove(taskKey)";
_kvs._remove(_taskkey);
 };
 }
};
RDebugUtils.currentLine=6619151;
 //BA.debugLineNum = 6619151;BA.debugLine="kvs.Remove(listsKey)";
_kvs._remove(_listskey);
 };
RDebugUtils.currentLine=6619153;
 //BA.debugLineNum = 6619153;BA.debugLine="kvs.Remove(\"group_name_\" & code)";
_kvs._remove("group_name_"+_code);
RDebugUtils.currentLine=6619154;
 //BA.debugLineNum = 6619154;BA.debugLine="kvs.Remove(\"group_owner_\" & code)";
_kvs._remove("group_owner_"+_code);
RDebugUtils.currentLine=6619155;
 //BA.debugLineNum = 6619155;BA.debugLine="kvs.Remove(\"group_members_\" & code)";
_kvs._remove("group_members_"+_code);
RDebugUtils.currentLine=6619158;
 //BA.debugLineNum = 6619158;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));
RDebugUtils.currentLine=6619159;
 //BA.debugLineNum = 6619159;BA.debugLine="Dim idx As Int = allGroups.IndexOf(code)";
_idx = _allgroups.IndexOf((Object)(_code));
RDebugUtils.currentLine=6619160;
 //BA.debugLineNum = 6619160;BA.debugLine="If idx >= 0 Then allGroups.RemoveAt(idx)";
if (_idx>=0) { 
_allgroups.RemoveAt(_idx);};
RDebugUtils.currentLine=6619161;
 //BA.debugLineNum = 6619161;BA.debugLine="kvs.Put(\"groups\", allGroups)";
_kvs._put("groups",(Object)(_allgroups.getObject()));
RDebugUtils.currentLine=6619162;
 //BA.debugLineNum = 6619162;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5505024;
 //BA.debugLineNum = 5505024;BA.debugLine="Sub enterTaskBtn_Click";
RDebugUtils.currentLine=5505026;
 //BA.debugLineNum = 5505026;BA.debugLine="Dim newTask As String = addTaskTextArea.Text.Trim";
_newtask = mostCurrent._addtasktextarea.getText().trim();
RDebugUtils.currentLine=5505027;
 //BA.debugLineNum = 5505027;BA.debugLine="If newTask = \"\" Then";
if ((_newtask).equals("")) { 
RDebugUtils.currentLine=5505028;
 //BA.debugLineNum = 5505028;BA.debugLine="MsgboxAsync(\"Please enter a task.\", \"No task ent";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a task."),BA.ObjectToCharSequence("No task entered"),processBA);
RDebugUtils.currentLine=5505029;
 //BA.debugLineNum = 5505029;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=5505032;
 //BA.debugLineNum = 5505032;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=5505033;
 //BA.debugLineNum = 5505033;BA.debugLine="Dim savedTasks As List";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=5505034;
 //BA.debugLineNum = 5505034;BA.debugLine="savedTasks.Initialize";
_savedtasks.Initialize();
RDebugUtils.currentLine=5505036;
 //BA.debugLineNum = 5505036;BA.debugLine="If kvs.ContainsKey(key) Then";
if (_kvs._containskey(_key)) { 
RDebugUtils.currentLine=5505037;
 //BA.debugLineNum = 5505037;BA.debugLine="savedTasks = kvs.Get(key)";
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
 };
RDebugUtils.currentLine=5505040;
 //BA.debugLineNum = 5505040;BA.debugLine="For Each existingTask As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group12 = _savedtasks;
final int groupLen12 = group12.getSize()
;int index12 = 0;
;
for (; index12 < groupLen12;index12++){
_existingtask = BA.ObjectToString(group12.Get(index12));
RDebugUtils.currentLine=5505041;
 //BA.debugLineNum = 5505041;BA.debugLine="If existingTask = newTask Then";
if ((_existingtask).equals(_newtask)) { 
RDebugUtils.currentLine=5505042;
 //BA.debugLineNum = 5505042;BA.debugLine="MsgboxAsync(\"A task with that name already exis";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("A task with that name already exists."),BA.ObjectToCharSequence("Duplicate task"),processBA);
RDebugUtils.currentLine=5505043;
 //BA.debugLineNum = 5505043;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=5505048;
 //BA.debugLineNum = 5505048;BA.debugLine="If addTaskTextArea.Tag <> Null Then";
if (mostCurrent._addtasktextarea.getTag()!= null) { 
RDebugUtils.currentLine=5505049;
 //BA.debugLineNum = 5505049;BA.debugLine="Dim ctx As List = addTaskTextArea.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(mostCurrent._addtasktextarea.getTag()));
RDebugUtils.currentLine=5505050;
 //BA.debugLineNum = 5505050;BA.debugLine="Dim oldTask As String = ctx.Get(1)";
_oldtask = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=5505052;
 //BA.debugLineNum = 5505052;BA.debugLine="Dim taskIndex As Int = savedTasks.IndexOf(oldTas";
_taskindex = _savedtasks.IndexOf((Object)(_oldtask));
RDebugUtils.currentLine=5505053;
 //BA.debugLineNum = 5505053;BA.debugLine="If taskIndex >= 0 Then";
if (_taskindex>=0) { 
RDebugUtils.currentLine=5505054;
 //BA.debugLineNum = 5505054;BA.debugLine="savedTasks.Set(taskIndex, newTask)";
_savedtasks.Set(_taskindex,(Object)(_newtask));
RDebugUtils.currentLine=5505055;
 //BA.debugLineNum = 5505055;BA.debugLine="kvs.Put(key, savedTasks)";
_kvs._put(_key,(Object)(_savedtasks.getObject()));
 };
RDebugUtils.currentLine=5505059;
 //BA.debugLineNum = 5505059;BA.debugLine="Dim oldCK As String = \"checked_\" & currentList &";
_oldck = "checked_"+mostCurrent._currentlist+"_"+_oldtask;
RDebugUtils.currentLine=5505060;
 //BA.debugLineNum = 5505060;BA.debugLine="Dim newCK As String = \"checked_\" & currentList &";
_newck = "checked_"+mostCurrent._currentlist+"_"+_newtask;
RDebugUtils.currentLine=5505061;
 //BA.debugLineNum = 5505061;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=5505062;
 //BA.debugLineNum = 5505062;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=5505063;
 //BA.debugLineNum = 5505063;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
RDebugUtils.currentLine=5505066;
 //BA.debugLineNum = 5505066;BA.debugLine="addTaskTextArea.Tag = Null";
mostCurrent._addtasktextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=5505069;
 //BA.debugLineNum = 5505069;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=5505070;
 //BA.debugLineNum = 5505070;BA.debugLine="Dim savedTasks2 As List = kvs.Get(key)";
_savedtasks2 = new anywheresoftware.b4a.objects.collections.List();
_savedtasks2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
RDebugUtils.currentLine=5505071;
 //BA.debugLineNum = 5505071;BA.debugLine="For Each t As String In savedTasks2";
{
final anywheresoftware.b4a.BA.IterableList group35 = _savedtasks2;
final int groupLen35 = group35.getSize()
;int index35 = 0;
;
for (; index35 < groupLen35;index35++){
_t = BA.ObjectToString(group35.Get(index35));
RDebugUtils.currentLine=5505072;
 //BA.debugLineNum = 5505072;BA.debugLine="tasksListUI(t)";
_taskslistui(_t);
 }
};
RDebugUtils.currentLine=5505074;
 //BA.debugLineNum = 5505074;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=5505075;
 //BA.debugLineNum = 5505075;BA.debugLine="addTaskBtn.Enabled = True";
mostCurrent._addtaskbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5505076;
 //BA.debugLineNum = 5505076;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=5505077;
 //BA.debugLineNum = 5505077;BA.debugLine="ToastMessageShow(\"Task renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5505078;
 //BA.debugLineNum = 5505078;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=5505082;
 //BA.debugLineNum = 5505082;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1)";
mostCurrent._taskslist._removeat((int) (mostCurrent._taskslist._getsize()-1));
RDebugUtils.currentLine=5505084;
 //BA.debugLineNum = 5505084;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=5505085;
 //BA.debugLineNum = 5505085;BA.debugLine="Dim savedTasks As List";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=5505086;
 //BA.debugLineNum = 5505086;BA.debugLine="If kvs.ContainsKey(key) Then";
if (_kvs._containskey(_key)) { 
RDebugUtils.currentLine=5505087;
 //BA.debugLineNum = 5505087;BA.debugLine="savedTasks = kvs.Get(key)";
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
 }else {
RDebugUtils.currentLine=5505089;
 //BA.debugLineNum = 5505089;BA.debugLine="savedTasks.Initialize";
_savedtasks.Initialize();
 };
RDebugUtils.currentLine=5505092;
 //BA.debugLineNum = 5505092;BA.debugLine="savedTasks.Add(newTask)";
_savedtasks.Add((Object)(_newtask));
RDebugUtils.currentLine=5505093;
 //BA.debugLineNum = 5505093;BA.debugLine="kvs.Put(key, savedTasks)";
_kvs._put(_key,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=5505095;
 //BA.debugLineNum = 5505095;BA.debugLine="tasksListUI(newTask)";
_taskslistui(_newtask);
RDebugUtils.currentLine=5505096;
 //BA.debugLineNum = 5505096;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=5505097;
 //BA.debugLineNum = 5505097;BA.debugLine="addTaskBtn.Enabled = True";
mostCurrent._addtaskbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5505098;
 //BA.debugLineNum = 5505098;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=5505100;
 //BA.debugLineNum = 5505100;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5767168;
 //BA.debugLineNum = 5767168;BA.debugLine="Sub tasksListUI(newTask As String)";
RDebugUtils.currentLine=5767170;
 //BA.debugLineNum = 5767170;BA.debugLine="Dim taskPNL As Panel";
_taskpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=5767171;
 //BA.debugLineNum = 5767171;BA.debugLine="taskPNL.Initialize(\"taskPNL\")";
_taskpnl.Initialize(mostCurrent.activityBA,"taskPNL");
RDebugUtils.currentLine=5767172;
 //BA.debugLineNum = 5767172;BA.debugLine="taskPNL.SetLayout(0, 0, 250dip, 60dip)";
_taskpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=5767174;
 //BA.debugLineNum = 5767174;BA.debugLine="Dim taskCheckbox As CheckBox";
_taskcheckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
RDebugUtils.currentLine=5767175;
 //BA.debugLineNum = 5767175;BA.debugLine="taskCheckbox.Initialize(\"taskCheckbox\")";
_taskcheckbox.Initialize(mostCurrent.activityBA,"taskCheckbox");
RDebugUtils.currentLine=5767176;
 //BA.debugLineNum = 5767176;BA.debugLine="taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip,";
_taskpnl.AddView((android.view.View)(_taskcheckbox.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=5767178;
 //BA.debugLineNum = 5767178;BA.debugLine="Dim taskLBL As Label";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=5767179;
 //BA.debugLineNum = 5767179;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5767180;
 //BA.debugLineNum = 5767180;BA.debugLine="taskLBL.Initialize(\"taskLBL\")";
_tasklbl.Initialize(mostCurrent.activityBA,"taskLBL");
RDebugUtils.currentLine=5767181;
 //BA.debugLineNum = 5767181;BA.debugLine="taskLBL.Text = newTask";
_tasklbl.setText(BA.ObjectToCharSequence(_newtask));
RDebugUtils.currentLine=5767182;
 //BA.debugLineNum = 5767182;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5767183;
 //BA.debugLineNum = 5767183;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.W";
_taskpnl.AddView((android.view.View)(_tasklbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (_taskpnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),_taskpnl.getHeight());
 }else {
RDebugUtils.currentLine=5767185;
 //BA.debugLineNum = 5767185;BA.debugLine="taskLBL.Initialize(\"taskLBL\")";
_tasklbl.Initialize(mostCurrent.activityBA,"taskLBL");
RDebugUtils.currentLine=5767186;
 //BA.debugLineNum = 5767186;BA.debugLine="taskLBL.Text = newTask";
_tasklbl.setText(BA.ObjectToCharSequence(_newtask));
RDebugUtils.currentLine=5767187;
 //BA.debugLineNum = 5767187;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=5767188;
 //BA.debugLineNum = 5767188;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.W";
_taskpnl.AddView((android.view.View)(_tasklbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (_taskpnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),_taskpnl.getHeight());
 };
RDebugUtils.currentLine=5767192;
 //BA.debugLineNum = 5767192;BA.debugLine="Dim divider As Panel";
_divider = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=5767193;
 //BA.debugLineNum = 5767193;BA.debugLine="divider.Initialize(\"line\")";
_divider.Initialize(mostCurrent.activityBA,"line");
RDebugUtils.currentLine=5767194;
 //BA.debugLineNum = 5767194;BA.debugLine="divider.Color = Colors.ARGB(255, 60, 60, 60)";
_divider.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (60),(int) (60),(int) (60)));
RDebugUtils.currentLine=5767195;
 //BA.debugLineNum = 5767195;BA.debugLine="taskPNL.AddView(divider, 0, 59dip, taskPNL.Width,";
_taskpnl.AddView((android.view.View)(_divider.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (59)),_taskpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
RDebugUtils.currentLine=5767197;
 //BA.debugLineNum = 5767197;BA.debugLine="taskCheckbox.Tag = taskLBL";
_taskcheckbox.setTag((Object)(_tasklbl.getObject()));
RDebugUtils.currentLine=5767200;
 //BA.debugLineNum = 5767200;BA.debugLine="Dim checkedKey As String = \"checked_\" & currentLi";
_checkedkey = "checked_"+mostCurrent._currentlist+"_"+_newtask;
RDebugUtils.currentLine=5767201;
 //BA.debugLineNum = 5767201;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=5767202;
 //BA.debugLineNum = 5767202;BA.debugLine="Dim isChecked As Boolean = kvs.Get(checkedKey)";
_ischecked = BA.ObjectToBoolean(_kvs._get(_checkedkey));
RDebugUtils.currentLine=5767203;
 //BA.debugLineNum = 5767203;BA.debugLine="taskCheckbox.Checked = isChecked";
_taskcheckbox.setChecked(_ischecked);
RDebugUtils.currentLine=5767204;
 //BA.debugLineNum = 5767204;BA.debugLine="If isChecked Then";
if (_ischecked) { 
RDebugUtils.currentLine=5767205;
 //BA.debugLineNum = 5767205;BA.debugLine="taskLBL.TextColor = Colors.ARGB(50, 0, 0, 0)";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (0),(int) (0),(int) (0)));
 };
 };
RDebugUtils.currentLine=5767209;
 //BA.debugLineNum = 5767209;BA.debugLine="tasksList.Add(taskPNL, newTask)";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_taskpnl.getObject())),(Object)(_newtask));
RDebugUtils.currentLine=5767211;
 //BA.debugLineNum = 5767211;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5832704;
 //BA.debugLineNum = 5832704;BA.debugLine="Sub updateProgress";
RDebugUtils.currentLine=5832706;
 //BA.debugLineNum = 5832706;BA.debugLine="If currentList = \"\" Then";
if ((mostCurrent._currentlist).equals("")) { 
RDebugUtils.currentLine=5832707;
 //BA.debugLineNum = 5832707;BA.debugLine="progressNumber.Text = \"\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5832708;
 //BA.debugLineNum = 5832708;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=5832711;
 //BA.debugLineNum = 5832711;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=5832712;
 //BA.debugLineNum = 5832712;BA.debugLine="If kvs.ContainsKey(key) = False Then";
if (_kvs._containskey(_key)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=5832713;
 //BA.debugLineNum = 5832713;BA.debugLine="progressNumber.Text = \"0 / 0 tasks done!\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence("0 / 0 tasks done!"));
RDebugUtils.currentLine=5832714;
 //BA.debugLineNum = 5832714;BA.debugLine="progressPercent.Text = \"0%\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence("0%"));
RDebugUtils.currentLine=5832715;
 //BA.debugLineNum = 5832715;BA.debugLine="progressBar.Progress = 0";
mostCurrent._progressbar.setProgress((int) (0));
RDebugUtils.currentLine=5832716;
 //BA.debugLineNum = 5832716;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=5832720;
 //BA.debugLineNum = 5832720;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
RDebugUtils.currentLine=5832721;
 //BA.debugLineNum = 5832721;BA.debugLine="Dim totalTasks As Int = savedTasks.Size";
_totaltasks = _savedtasks.getSize();
RDebugUtils.currentLine=5832722;
 //BA.debugLineNum = 5832722;BA.debugLine="Dim doneTasks As Int = 0";
_donetasks = (int) (0);
RDebugUtils.currentLine=5832723;
 //BA.debugLineNum = 5832723;BA.debugLine="Dim percentageTasks As Int = 0";
_percentagetasks = (int) (0);
RDebugUtils.currentLine=5832725;
 //BA.debugLineNum = 5832725;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group16 = _savedtasks;
final int groupLen16 = group16.getSize()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_task = BA.ObjectToString(group16.Get(index16));
RDebugUtils.currentLine=5832726;
 //BA.debugLineNum = 5832726;BA.debugLine="Dim checkedKey As String = \"checked_\" & currentL";
_checkedkey = "checked_"+mostCurrent._currentlist+"_"+_task;
RDebugUtils.currentLine=5832727;
 //BA.debugLineNum = 5832727;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=5832728;
 //BA.debugLineNum = 5832728;BA.debugLine="If kvs.Get(checkedKey) = True Then";
if ((_kvs._get(_checkedkey)).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
RDebugUtils.currentLine=5832729;
 //BA.debugLineNum = 5832729;BA.debugLine="doneTasks = doneTasks + 1";
_donetasks = (int) (_donetasks+1);
 };
 };
 }
};
RDebugUtils.currentLine=5832734;
 //BA.debugLineNum = 5832734;BA.debugLine="percentageTasks = (doneTasks / totalTasks) * 100";
_percentagetasks = (int) ((_donetasks/(double)_totaltasks)*100);
RDebugUtils.currentLine=5832736;
 //BA.debugLineNum = 5832736;BA.debugLine="progressNumber.Text = doneTasks & \" / \" & totalTa";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence(BA.NumberToString(_donetasks)+" / "+BA.NumberToString(_totaltasks)+" tasks done!"));
RDebugUtils.currentLine=5832737;
 //BA.debugLineNum = 5832737;BA.debugLine="progressPercent.Text = percentageTasks & \"%\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence(BA.NumberToString(_percentagetasks)+"%"));
RDebugUtils.currentLine=5832738;
 //BA.debugLineNum = 5832738;BA.debugLine="progressBar.Progress = percentageTasks";
mostCurrent._progressbar.setProgress(_percentagetasks);
RDebugUtils.currentLine=5832740;
 //BA.debugLineNum = 5832740;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7208960;
 //BA.debugLineNum = 7208960;BA.debugLine="Sub enterTaskBtnGrp_Click";
RDebugUtils.currentLine=7208961;
 //BA.debugLineNum = 7208961;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=7208962;
 //BA.debugLineNum = 7208962;BA.debugLine="Dim ctx As List = btn.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_btn.getTag()));
RDebugUtils.currentLine=7208963;
 //BA.debugLineNum = 7208963;BA.debugLine="Dim code As String = ctx.Get(0)";
_code = BA.ObjectToString(_ctx.Get((int) (0)));
RDebugUtils.currentLine=7208964;
 //BA.debugLineNum = 7208964;BA.debugLine="Dim currentGrpList As String = ctx.Get(1)";
_currentgrplist = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=7208965;
 //BA.debugLineNum = 7208965;BA.debugLine="Dim addET As EditText = ctx.Get(2)";
_addet = new anywheresoftware.b4a.objects.EditTextWrapper();
_addet = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_ctx.Get((int) (2))));
RDebugUtils.currentLine=7208966;
 //BA.debugLineNum = 7208966;BA.debugLine="Dim oldTask As Object = ctx.Get(3)";
_oldtask = _ctx.Get((int) (3));
RDebugUtils.currentLine=7208968;
 //BA.debugLineNum = 7208968;BA.debugLine="Dim newTask As String = addET.Text.Trim";
_newtask = _addet.getText().trim();
RDebugUtils.currentLine=7208969;
 //BA.debugLineNum = 7208969;BA.debugLine="If newTask = \"\" Then";
if ((_newtask).equals("")) { 
RDebugUtils.currentLine=7208970;
 //BA.debugLineNum = 7208970;BA.debugLine="MsgboxAsync(\"Please enter a task.\", \"No task ent";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a task."),BA.ObjectToCharSequence("No task entered"),processBA);
RDebugUtils.currentLine=7208971;
 //BA.debugLineNum = 7208971;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=7208974;
 //BA.debugLineNum = 7208974;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=7208975;
 //BA.debugLineNum = 7208975;BA.debugLine="Dim savedTasks As List";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=7208976;
 //BA.debugLineNum = 7208976;BA.debugLine="savedTasks.Initialize";
_savedtasks.Initialize();
RDebugUtils.currentLine=7208977;
 //BA.debugLineNum = 7208977;BA.debugLine="If kvs.ContainsKey(taskKey) Then savedTasks = kvs";
if (_kvs._containskey(_taskkey)) { 
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));};
RDebugUtils.currentLine=7208980;
 //BA.debugLineNum = 7208980;BA.debugLine="For Each existing As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group16 = _savedtasks;
final int groupLen16 = group16.getSize()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_existing = BA.ObjectToString(group16.Get(index16));
RDebugUtils.currentLine=7208981;
 //BA.debugLineNum = 7208981;BA.debugLine="If existing = newTask Then";
if ((_existing).equals(_newtask)) { 
RDebugUtils.currentLine=7208982;
 //BA.debugLineNum = 7208982;BA.debugLine="MsgboxAsync(\"Task already exists.\", \"Duplicate\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Task already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=7208983;
 //BA.debugLineNum = 7208983;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=7208987;
 //BA.debugLineNum = 7208987;BA.debugLine="If oldTask <> Null Then";
if (_oldtask!= null) { 
RDebugUtils.currentLine=7208989;
 //BA.debugLineNum = 7208989;BA.debugLine="Dim taskIndex As Int = savedTasks.IndexOf(oldTas";
_taskindex = _savedtasks.IndexOf(_oldtask);
RDebugUtils.currentLine=7208990;
 //BA.debugLineNum = 7208990;BA.debugLine="If taskIndex >= 0 Then";
if (_taskindex>=0) { 
RDebugUtils.currentLine=7208991;
 //BA.debugLineNum = 7208991;BA.debugLine="savedTasks.Set(taskIndex, newTask)";
_savedtasks.Set(_taskindex,(Object)(_newtask));
RDebugUtils.currentLine=7208992;
 //BA.debugLineNum = 7208992;BA.debugLine="kvs.Put(taskKey, savedTasks)";
_kvs._put(_taskkey,(Object)(_savedtasks.getObject()));
 };
RDebugUtils.currentLine=7208995;
 //BA.debugLineNum = 7208995;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code &";
_oldck = "group_checked_"+_code+"_"+_currentgrplist+"_"+BA.ObjectToString(_oldtask);
RDebugUtils.currentLine=7208996;
 //BA.debugLineNum = 7208996;BA.debugLine="Dim newCK As String = \"group_checked_\" & code &";
_newck = "group_checked_"+_code+"_"+_currentgrplist+"_"+_newtask;
RDebugUtils.currentLine=7208997;
 //BA.debugLineNum = 7208997;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=7208998;
 //BA.debugLineNum = 7208998;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=7208999;
 //BA.debugLineNum = 7208999;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
RDebugUtils.currentLine=7209003;
 //BA.debugLineNum = 7209003;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=7209004;
 //BA.debugLineNum = 7209004;BA.debugLine="Dim savedTasks2 As List = kvs.Get(taskKey)";
_savedtasks2 = new anywheresoftware.b4a.objects.collections.List();
_savedtasks2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=7209005;
 //BA.debugLineNum = 7209005;BA.debugLine="For Each t As String In savedTasks2";
{
final anywheresoftware.b4a.BA.IterableList group36 = _savedtasks2;
final int groupLen36 = group36.getSize()
;int index36 = 0;
;
for (; index36 < groupLen36;index36++){
_t = BA.ObjectToString(group36.Get(index36));
RDebugUtils.currentLine=7209006;
 //BA.debugLineNum = 7209006;BA.debugLine="tasksListGrpUI(t, code, currentGrpList)";
_taskslistgrpui(_t,_code,_currentgrplist);
 }
};
RDebugUtils.currentLine=7209008;
 //BA.debugLineNum = 7209008;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
_newaddtaskbtngrp(_code,_currentgrplist);
RDebugUtils.currentLine=7209009;
 //BA.debugLineNum = 7209009;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=7209010;
 //BA.debugLineNum = 7209010;BA.debugLine="ToastMessageShow(\"Task renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=7209011;
 //BA.debugLineNum = 7209011;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=7209015;
 //BA.debugLineNum = 7209015;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
mostCurrent._taskslistgrp._removeat((int) (mostCurrent._taskslistgrp._getsize()-1));
RDebugUtils.currentLine=7209016;
 //BA.debugLineNum = 7209016;BA.debugLine="savedTasks.Add(newTask)";
_savedtasks.Add((Object)(_newtask));
RDebugUtils.currentLine=7209017;
 //BA.debugLineNum = 7209017;BA.debugLine="kvs.Put(taskKey, savedTasks)";
_kvs._put(_taskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=7209018;
 //BA.debugLineNum = 7209018;BA.debugLine="tasksListGrpUI(newTask, code, currentGrpList)";
_taskslistgrpui(_newtask,_code,_currentgrplist);
RDebugUtils.currentLine=7209019;
 //BA.debugLineNum = 7209019;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
_newaddtaskbtngrp(_code,_currentgrplist);
RDebugUtils.currentLine=7209020;
 //BA.debugLineNum = 7209020;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=7209021;
 //BA.debugLineNum = 7209021;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7405568;
 //BA.debugLineNum = 7405568;BA.debugLine="Sub tasksListGrpUI(newTask As String, code As Stri";
RDebugUtils.currentLine=7405569;
 //BA.debugLineNum = 7405569;BA.debugLine="Dim taskPNL As Panel";
_taskpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=7405570;
 //BA.debugLineNum = 7405570;BA.debugLine="taskPNL.Initialize(\"taskPNLGrp\")";
_taskpnl.Initialize(mostCurrent.activityBA,"taskPNLGrp");
RDebugUtils.currentLine=7405571;
 //BA.debugLineNum = 7405571;BA.debugLine="taskPNL.SetLayout(0, 0, 250dip, 60dip)";
_taskpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=7405573;
 //BA.debugLineNum = 7405573;BA.debugLine="Dim taskCheckbox As CheckBox";
_taskcheckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
RDebugUtils.currentLine=7405574;
 //BA.debugLineNum = 7405574;BA.debugLine="taskCheckbox.Initialize(\"taskCheckboxGrp\")";
_taskcheckbox.Initialize(mostCurrent.activityBA,"taskCheckboxGrp");
RDebugUtils.currentLine=7405575;
 //BA.debugLineNum = 7405575;BA.debugLine="taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip,";
_taskpnl.AddView((android.view.View)(_taskcheckbox.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=7405577;
 //BA.debugLineNum = 7405577;BA.debugLine="Dim taskLBL As Label";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=7405578;
 //BA.debugLineNum = 7405578;BA.debugLine="taskLBL.Initialize(\"taskLBLGrp\")";
_tasklbl.Initialize(mostCurrent.activityBA,"taskLBLGrp");
RDebugUtils.currentLine=7405579;
 //BA.debugLineNum = 7405579;BA.debugLine="taskLBL.Text = newTask";
_tasklbl.setText(BA.ObjectToCharSequence(_newtask));
RDebugUtils.currentLine=7405580;
 //BA.debugLineNum = 7405580;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=7405581;
 //BA.debugLineNum = 7405581;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=7405583;
 //BA.debugLineNum = 7405583;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
RDebugUtils.currentLine=7405585;
 //BA.debugLineNum = 7405585;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.Wi";
_taskpnl.AddView((android.view.View)(_tasklbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (_taskpnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),_taskpnl.getHeight());
RDebugUtils.currentLine=7405587;
 //BA.debugLineNum = 7405587;BA.debugLine="Dim divider As Panel";
_divider = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=7405588;
 //BA.debugLineNum = 7405588;BA.debugLine="divider.Initialize(\"line\")";
_divider.Initialize(mostCurrent.activityBA,"line");
RDebugUtils.currentLine=7405589;
 //BA.debugLineNum = 7405589;BA.debugLine="divider.Color = Colors.ARGB(255, 60, 60, 60)";
_divider.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (60),(int) (60),(int) (60)));
RDebugUtils.currentLine=7405590;
 //BA.debugLineNum = 7405590;BA.debugLine="taskPNL.AddView(divider, 0, 59dip, taskPNL.Width,";
_taskpnl.AddView((android.view.View)(_divider.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (59)),_taskpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
RDebugUtils.currentLine=7405593;
 //BA.debugLineNum = 7405593;BA.debugLine="Dim cbCtx As List";
_cbctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=7405594;
 //BA.debugLineNum = 7405594;BA.debugLine="cbCtx.Initialize";
_cbctx.Initialize();
RDebugUtils.currentLine=7405595;
 //BA.debugLineNum = 7405595;BA.debugLine="cbCtx.Add(code)";
_cbctx.Add((Object)(_code));
RDebugUtils.currentLine=7405596;
 //BA.debugLineNum = 7405596;BA.debugLine="cbCtx.Add(currentGrpList)";
_cbctx.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=7405597;
 //BA.debugLineNum = 7405597;BA.debugLine="cbCtx.Add(taskLBL)";
_cbctx.Add((Object)(_tasklbl.getObject()));
RDebugUtils.currentLine=7405598;
 //BA.debugLineNum = 7405598;BA.debugLine="taskCheckbox.Tag = cbCtx";
_taskcheckbox.setTag((Object)(_cbctx.getObject()));
RDebugUtils.currentLine=7405601;
 //BA.debugLineNum = 7405601;BA.debugLine="Dim checkedKey As String = \"group_checked_\" & cod";
_checkedkey = "group_checked_"+_code+"_"+_currentgrplist+"_"+_newtask;
RDebugUtils.currentLine=7405602;
 //BA.debugLineNum = 7405602;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=7405603;
 //BA.debugLineNum = 7405603;BA.debugLine="Dim isChecked As Boolean = kvs.Get(checkedKey)";
_ischecked = BA.ObjectToBoolean(_kvs._get(_checkedkey));
RDebugUtils.currentLine=7405604;
 //BA.debugLineNum = 7405604;BA.debugLine="taskCheckbox.Checked = isChecked";
_taskcheckbox.setChecked(_ischecked);
RDebugUtils.currentLine=7405605;
 //BA.debugLineNum = 7405605;BA.debugLine="If isChecked Then taskLBL.TextColor = Colors.ARG";
if (_ischecked) { 
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (50),(int) (0),(int) (0),(int) (0)));};
 };
RDebugUtils.currentLine=7405608;
 //BA.debugLineNum = 7405608;BA.debugLine="tasksListGrp.Add(taskPNL, newTask)";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_taskpnl.getObject())),(Object)(_newtask));
RDebugUtils.currentLine=7405609;
 //BA.debugLineNum = 7405609;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7077888;
 //BA.debugLineNum = 7077888;BA.debugLine="Sub newAddTaskBtnGrp(code As String, currentGrpLis";
RDebugUtils.currentLine=7077889;
 //BA.debugLineNum = 7077889;BA.debugLine="Dim addBtnPNL As Panel";
_addbtnpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=7077890;
 //BA.debugLineNum = 7077890;BA.debugLine="addBtnPNL.Initialize(\"addTaskBtnPNLGrp\")";
_addbtnpnl.Initialize(mostCurrent.activityBA,"addTaskBtnPNLGrp");
RDebugUtils.currentLine=7077891;
 //BA.debugLineNum = 7077891;BA.debugLine="addBtnPNL.SetLayout(10dip, 0dip, 190dip, 70dip)";
_addbtnpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=7077892;
 //BA.debugLineNum = 7077892;BA.debugLine="addBtnPNL.Color = Colors.Transparent";
_addbtnpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=7077894;
 //BA.debugLineNum = 7077894;BA.debugLine="Dim addBtn As Button";
_addbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=7077895;
 //BA.debugLineNum = 7077895;BA.debugLine="addBtn.Initialize(\"addTaskBtnGrp\")";
_addbtn.Initialize(mostCurrent.activityBA,"addTaskBtnGrp");
RDebugUtils.currentLine=7077896;
 //BA.debugLineNum = 7077896;BA.debugLine="addBtn.Text = \"+ add a task\"";
_addbtn.setText(BA.ObjectToCharSequence("+ add a task"));
RDebugUtils.currentLine=7077898;
 //BA.debugLineNum = 7077898;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=7077899;
 //BA.debugLineNum = 7077899;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=7077900;
 //BA.debugLineNum = 7077900;BA.debugLine="addBtn.Background = cd";
_addbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=7077901;
 //BA.debugLineNum = 7077901;BA.debugLine="addBtn.TextColor = Colors.White";
_addbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=7077904;
 //BA.debugLineNum = 7077904;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=7077905;
 //BA.debugLineNum = 7077905;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=7077906;
 //BA.debugLineNum = 7077906;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=7077907;
 //BA.debugLineNum = 7077907;BA.debugLine="ctx.Add(currentGrpList)";
_ctx.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=7077908;
 //BA.debugLineNum = 7077908;BA.debugLine="addBtn.Tag = ctx";
_addbtn.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=7077910;
 //BA.debugLineNum = 7077910;BA.debugLine="addBtnPNL.AddView(addBtn, 10dip, 20dip, addBtnPNL";
_addbtnpnl.AddView((android.view.View)(_addbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),_addbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=7077911;
 //BA.debugLineNum = 7077911;BA.debugLine="tasksListGrp.Add(addBtnPNL, \"\")";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_addbtnpnl.getObject())),(Object)(""));
RDebugUtils.currentLine=7077912;
 //BA.debugLineNum = 7077912;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7536640;
 //BA.debugLineNum = 7536640;BA.debugLine="Sub updateProgressGrp(code As String, currentGrpLi";
RDebugUtils.currentLine=7536641;
 //BA.debugLineNum = 7536641;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=7536642;
 //BA.debugLineNum = 7536642;BA.debugLine="If kvs.ContainsKey(taskKey) = False Then";
if (_kvs._containskey(_taskkey)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=7536643;
 //BA.debugLineNum = 7536643;BA.debugLine="progressBarGrp.Progress = 0";
mostCurrent._progressbargrp.setProgress((int) (0));
RDebugUtils.currentLine=7536644;
 //BA.debugLineNum = 7536644;BA.debugLine="progressNumberGrp.Text = \"0 / 0 tasks done!\"";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence("0 / 0 tasks done!"));
RDebugUtils.currentLine=7536645;
 //BA.debugLineNum = 7536645;BA.debugLine="progressPercentGrp.Text = \"0%\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence("0%"));
RDebugUtils.currentLine=7536646;
 //BA.debugLineNum = 7536646;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=7536649;
 //BA.debugLineNum = 7536649;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=7536650;
 //BA.debugLineNum = 7536650;BA.debugLine="Dim totalTasks As Int = savedTasks.Size";
_totaltasks = _savedtasks.getSize();
RDebugUtils.currentLine=7536651;
 //BA.debugLineNum = 7536651;BA.debugLine="Dim doneTasks As Int = 0";
_donetasks = (int) (0);
RDebugUtils.currentLine=7536653;
 //BA.debugLineNum = 7536653;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group11 = _savedtasks;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_task = BA.ObjectToString(group11.Get(index11));
RDebugUtils.currentLine=7536654;
 //BA.debugLineNum = 7536654;BA.debugLine="Dim checkedKey As String = \"group_checked_\" & co";
_checkedkey = "group_checked_"+_code+"_"+_currentgrplist+"_"+_task;
RDebugUtils.currentLine=7536655;
 //BA.debugLineNum = 7536655;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
if (_kvs._containskey(_checkedkey)) { 
RDebugUtils.currentLine=7536656;
 //BA.debugLineNum = 7536656;BA.debugLine="If kvs.Get(checkedKey) = True Then doneTasks =";
if ((_kvs._get(_checkedkey)).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
_donetasks = (int) (_donetasks+1);};
 };
 }
};
RDebugUtils.currentLine=7536660;
 //BA.debugLineNum = 7536660;BA.debugLine="Dim pct As Int = 0";
_pct = (int) (0);
RDebugUtils.currentLine=7536661;
 //BA.debugLineNum = 7536661;BA.debugLine="If totalTasks > 0 Then pct = (doneTasks / totalTa";
if (_totaltasks>0) { 
_pct = (int) ((_donetasks/(double)_totaltasks)*100);};
RDebugUtils.currentLine=7536663;
 //BA.debugLineNum = 7536663;BA.debugLine="progressNumberGrp.Text = doneTasks & \" / \" & tota";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence(BA.NumberToString(_donetasks)+" / "+BA.NumberToString(_totaltasks)+" tasks done!"));
RDebugUtils.currentLine=7536664;
 //BA.debugLineNum = 7536664;BA.debugLine="progressPercentGrp.Text = pct & \"%\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence(BA.NumberToString(_pct)+"%"));
RDebugUtils.currentLine=7536665;
 //BA.debugLineNum = 7536665;BA.debugLine="progressBarGrp.Progress = pct";
mostCurrent._progressbargrp.setProgress(_pct);
RDebugUtils.currentLine=7536666;
 //BA.debugLineNum = 7536666;BA.debugLine="End Sub";
return "";
}
public static String  _generategroupcode(int _length) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "generategroupcode", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "generategroupcode", new Object[] {_length}));}
String _chars = "";
String _code = "";
int _i = 0;
RDebugUtils.currentLine=6160384;
 //BA.debugLineNum = 6160384;BA.debugLine="Sub generateGroupCode (length As Int) As String";
RDebugUtils.currentLine=6160385;
 //BA.debugLineNum = 6160385;BA.debugLine="Dim chars As String = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
RDebugUtils.currentLine=6160386;
 //BA.debugLineNum = 6160386;BA.debugLine="Dim code As String = \"\"";
_code = "";
RDebugUtils.currentLine=6160387;
 //BA.debugLineNum = 6160387;BA.debugLine="For i = 0 To length - 1";
{
final int step3 = 1;
final int limit3 = (int) (_length-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=6160388;
 //BA.debugLineNum = 6160388;BA.debugLine="code = code & (chars.CharAt(Rnd(0,chars.Length))";
_code = _code+BA.ObjectToString((_chars.charAt(anywheresoftware.b4a.keywords.Common.Rnd((int) (0),_chars.length()))));
 }
};
RDebugUtils.currentLine=6160390;
 //BA.debugLineNum = 6160390;BA.debugLine="Return code";
if (true) return _code;
RDebugUtils.currentLine=6160391;
 //BA.debugLineNum = 6160391;BA.debugLine="End Sub";
return "";
}
public static String  _getcurrentgrplist() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getcurrentgrplist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getcurrentgrplist", null));}
RDebugUtils.currentLine=7602176;
 //BA.debugLineNum = 7602176;BA.debugLine="Sub getCurrentGrpList As String";
RDebugUtils.currentLine=7602177;
 //BA.debugLineNum = 7602177;BA.debugLine="Return currentGrpListName   ' module-level var yo";
if (true) return mostCurrent._currentgrplistname;
RDebugUtils.currentLine=7602178;
 //BA.debugLineNum = 7602178;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6094848;
 //BA.debugLineNum = 6094848;BA.debugLine="Sub groupET_EnterPressed";
RDebugUtils.currentLine=6094849;
 //BA.debugLineNum = 6094849;BA.debugLine="If groupET.Tag Is List Then";
if (mostCurrent._groupet.getTag() instanceof java.util.List) { 
RDebugUtils.currentLine=6094851;
 //BA.debugLineNum = 6094851;BA.debugLine="Dim ctx As List = groupET.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(mostCurrent._groupet.getTag()));
RDebugUtils.currentLine=6094852;
 //BA.debugLineNum = 6094852;BA.debugLine="Dim code As String = ctx.Get(1)";
_code = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=6094853;
 //BA.debugLineNum = 6094853;BA.debugLine="Dim newName As String = groupET.Text.Trim";
_newname = mostCurrent._groupet.getText().trim();
RDebugUtils.currentLine=6094854;
 //BA.debugLineNum = 6094854;BA.debugLine="If newName = \"\" Then";
if ((_newname).equals("")) { 
RDebugUtils.currentLine=6094855;
 //BA.debugLineNum = 6094855;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_kvs._get("group_name_"+_code)));
RDebugUtils.currentLine=6094856;
 //BA.debugLineNum = 6094856;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6094857;
 //BA.debugLineNum = 6094857;BA.debugLine="groupET.Tag = code";
mostCurrent._groupet.setTag((Object)(_code));
RDebugUtils.currentLine=6094858;
 //BA.debugLineNum = 6094858;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=6094860;
 //BA.debugLineNum = 6094860;BA.debugLine="kvs.Put(\"group_name_\" & code, newName)";
_kvs._put("group_name_"+_code,(Object)(_newname));
RDebugUtils.currentLine=6094861;
 //BA.debugLineNum = 6094861;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6094862;
 //BA.debugLineNum = 6094862;BA.debugLine="groupET.Tag = code";
mostCurrent._groupet.setTag((Object)(_code));
RDebugUtils.currentLine=6094863;
 //BA.debugLineNum = 6094863;BA.debugLine="loadMyGroups";
_loadmygroups();
RDebugUtils.currentLine=6094864;
 //BA.debugLineNum = 6094864;BA.debugLine="groupET.Text = newName";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_newname));
RDebugUtils.currentLine=6094865;
 //BA.debugLineNum = 6094865;BA.debugLine="ToastMessageShow(\"Group renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Group renamed"),anywheresoftware.b4a.keywords.Common.False);
 }else 
{RDebugUtils.currentLine=6094867;
 //BA.debugLineNum = 6094867;BA.debugLine="Else If groupET.Tag = \"creating\" Then";
if ((mostCurrent._groupet.getTag()).equals((Object)("creating"))) { 
RDebugUtils.currentLine=6094869;
 //BA.debugLineNum = 6094869;BA.debugLine="Dim groupName As String = groupET.Text.Trim";
_groupname = mostCurrent._groupet.getText().trim();
RDebugUtils.currentLine=6094870;
 //BA.debugLineNum = 6094870;BA.debugLine="If groupName = \"\" Then";
if ((_groupname).equals("")) { 
RDebugUtils.currentLine=6094871;
 //BA.debugLineNum = 6094871;BA.debugLine="MsgboxAsync(\"Please enter a group name.\", \"No n";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a group name."),BA.ObjectToCharSequence("No name"),processBA);
RDebugUtils.currentLine=6094872;
 //BA.debugLineNum = 6094872;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=6094875;
 //BA.debugLineNum = 6094875;BA.debugLine="Dim code As String = generateGroupCode(6)";
_code = _generategroupcode((int) (6));
RDebugUtils.currentLine=6094877;
 //BA.debugLineNum = 6094877;BA.debugLine="Dim allGroups As List";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=6094878;
 //BA.debugLineNum = 6094878;BA.debugLine="allGroups.Initialize";
_allgroups.Initialize();
RDebugUtils.currentLine=6094879;
 //BA.debugLineNum = 6094879;BA.debugLine="If kvs.ContainsKey(\"groups\") Then allGroups = kv";
if (_kvs._containskey("groups")) { 
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));};
RDebugUtils.currentLine=6094880;
 //BA.debugLineNum = 6094880;BA.debugLine="allGroups.Add(code)";
_allgroups.Add((Object)(_code));
RDebugUtils.currentLine=6094881;
 //BA.debugLineNum = 6094881;BA.debugLine="kvs.Put(\"groups\", allGroups)";
_kvs._put("groups",(Object)(_allgroups.getObject()));
RDebugUtils.currentLine=6094883;
 //BA.debugLineNum = 6094883;BA.debugLine="kvs.Put(\"group_name_\" & code, groupName)";
_kvs._put("group_name_"+_code,(Object)(_groupname));
RDebugUtils.currentLine=6094884;
 //BA.debugLineNum = 6094884;BA.debugLine="kvs.Put(\"group_owner_\" & code, Starter.currentUs";
_kvs._put("group_owner_"+_code,(Object)(mostCurrent._starter._currentuserid /*String*/ ));
RDebugUtils.currentLine=6094886;
 //BA.debugLineNum = 6094886;BA.debugLine="Dim members As List";
_members = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=6094887;
 //BA.debugLineNum = 6094887;BA.debugLine="members.Initialize";
_members.Initialize();
RDebugUtils.currentLine=6094888;
 //BA.debugLineNum = 6094888;BA.debugLine="members.Add(Starter.currentUserID)";
_members.Add((Object)(mostCurrent._starter._currentuserid /*String*/ ));
RDebugUtils.currentLine=6094889;
 //BA.debugLineNum = 6094889;BA.debugLine="kvs.Put(\"group_members_\" & code, members)";
_kvs._put("group_members_"+_code,(Object)(_members.getObject()));
RDebugUtils.currentLine=6094891;
 //BA.debugLineNum = 6094891;BA.debugLine="groupET.Tag = Null";
mostCurrent._groupet.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=6094892;
 //BA.debugLineNum = 6094892;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6094894;
 //BA.debugLineNum = 6094894;BA.debugLine="ToastMessageShow(\"Group \"\"\" & groupName & \"\"\" cr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Group \""+_groupname+"\" created! Code: "+_code),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6094895;
 //BA.debugLineNum = 6094895;BA.debugLine="loadMyGroups";
_loadmygroups();
 }}
;
RDebugUtils.currentLine=6094897;
 //BA.debugLineNum = 6094897;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6422528;
 //BA.debugLineNum = 6422528;BA.debugLine="Sub groupList_ItemClick(Index As Int, Value As Obj";
RDebugUtils.currentLine=6422529;
 //BA.debugLineNum = 6422529;BA.debugLine="Dim code As String = Value";
_code = BA.ObjectToString(_value);
RDebugUtils.currentLine=6422530;
 //BA.debugLineNum = 6422530;BA.debugLine="If code = \"joinPanel\" Then Return";
if ((_code).equals("joinPanel")) { 
if (true) return "";};
RDebugUtils.currentLine=6422533;
 //BA.debugLineNum = 6422533;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_kvs._get("group_name_"+_code)));
RDebugUtils.currentLine=6422534;
 //BA.debugLineNum = 6422534;BA.debugLine="groupET.Enabled = False";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6422535;
 //BA.debugLineNum = 6422535;BA.debugLine="groupET.Tag = code   ' store current group code i";
mostCurrent._groupet.setTag((Object)(_code));
RDebugUtils.currentLine=6422538;
 //BA.debugLineNum = 6422538;BA.debugLine="listsListGrp.Clear";
mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=6422539;
 //BA.debugLineNum = 6422539;BA.debugLine="listsListGrp.GetBase.Visible = True";
mostCurrent._listslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6422540;
 //BA.debugLineNum = 6422540;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=6422541;
 //BA.debugLineNum = 6422541;BA.debugLine="tasksListGrp.GetBase.Visible = False";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6422543;
 //BA.debugLineNum = 6422543;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=6422544;
 //BA.debugLineNum = 6422544;BA.debugLine="If kvs.ContainsKey(listsKey) Then";
if (_kvs._containskey(_listskey)) { 
RDebugUtils.currentLine=6422545;
 //BA.debugLineNum = 6422545;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));
RDebugUtils.currentLine=6422546;
 //BA.debugLineNum = 6422546;BA.debugLine="For Each listName As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group13 = _savedlists;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_listname = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=6422547;
 //BA.debugLineNum = 6422547;BA.debugLine="listsListGrp.AddTextItem(listName, listName)";
mostCurrent._listslistgrp._addtextitem((Object)(_listname),(Object)(_listname));
 }
};
 };
RDebugUtils.currentLine=6422551;
 //BA.debugLineNum = 6422551;BA.debugLine="progressBarGrp.Progress = 0";
mostCurrent._progressbargrp.setProgress((int) (0));
RDebugUtils.currentLine=6422552;
 //BA.debugLineNum = 6422552;BA.debugLine="progressNumberGrp.Text = \"\"";
mostCurrent._progressnumbergrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=6422553;
 //BA.debugLineNum = 6422553;BA.debugLine="progressPercentGrp.Text = \"\"";
mostCurrent._progresspercentgrp.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=6422554;
 //BA.debugLineNum = 6422554;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6488065;
 //BA.debugLineNum = 6488065;BA.debugLine="If Value = \"joinPanel\" Then Return";
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
RDebugUtils.currentLine=6488067;
 //BA.debugLineNum = 6488067;BA.debugLine="Dim code As String = Value";
_code = BA.ObjectToString(_value);
RDebugUtils.currentLine=6488068;
 //BA.debugLineNum = 6488068;BA.debugLine="Msgbox2Async(\"What do you want to do with this gr";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("What do you want to do with this group?"),BA.ObjectToCharSequence(parent._kvs._get("group_name_"+_code)),"Rename","Copy Invite Code","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6488069;
 //BA.debugLineNum = 6488069;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "grouplist_itemlongclick"), null);
this.state = 27;
return;
case 27:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=6488071;
 //BA.debugLineNum = 6488071;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 26;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=6488075;
 //BA.debugLineNum = 6488075;BA.debugLine="Else If res = DialogResponse.CANCEL Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
this.state = 11;
}else 
{RDebugUtils.currentLine=6488085;
 //BA.debugLineNum = 6488085;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 13;
}}}
if (true) break;

case 9:
//C
this.state = 26;
RDebugUtils.currentLine=6488073;
 //BA.debugLineNum = 6488073;BA.debugLine="showRenameGroupPanel(Index, code)";
_showrenamegrouppanel(_index,_code);
 if (true) break;

case 11:
//C
this.state = 26;
RDebugUtils.currentLine=6488077;
 //BA.debugLineNum = 6488077;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=6488078;
 //BA.debugLineNum = 6488078;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext(processBA);
RDebugUtils.currentLine=6488079;
 //BA.debugLineNum = 6488079;BA.debugLine="Dim clipManager As JavaObject = jo.RunMethod(\"ge";
_clipmanager = new anywheresoftware.b4j.object.JavaObject();
_clipmanager = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.RunMethod("getSystemService",new Object[]{(Object)("clipboard")})));
RDebugUtils.currentLine=6488080;
 //BA.debugLineNum = 6488080;BA.debugLine="Dim clipData As JavaObject";
_clipdata = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=6488081;
 //BA.debugLineNum = 6488081;BA.debugLine="clipData.InitializeStatic(\"android.content.ClipD";
_clipdata.InitializeStatic("android.content.ClipData");
RDebugUtils.currentLine=6488082;
 //BA.debugLineNum = 6488082;BA.debugLine="clipManager.RunMethod(\"setPrimaryClip\", Array(cl";
_clipmanager.RunMethod("setPrimaryClip",new Object[]{_clipdata.RunMethod("newPlainText",new Object[]{(Object)("Group Code"),(Object)(_code)})});
RDebugUtils.currentLine=6488083;
 //BA.debugLineNum = 6488083;BA.debugLine="ToastMessageShow(\"Invite code copied: \" & code,";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Invite code copied: "+_code),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 13:
//C
this.state = 14;
RDebugUtils.currentLine=6488087;
 //BA.debugLineNum = 6488087;BA.debugLine="Dim ownerKey As String = \"group_owner_\" & code";
_ownerkey = "group_owner_"+_code;
RDebugUtils.currentLine=6488088;
 //BA.debugLineNum = 6488088;BA.debugLine="If kvs.ContainsKey(ownerKey) Then";
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
RDebugUtils.currentLine=6488089;
 //BA.debugLineNum = 6488089;BA.debugLine="If kvs.Get(ownerKey) <> Starter.currentUserID T";
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
RDebugUtils.currentLine=6488090;
 //BA.debugLineNum = 6488090;BA.debugLine="MsgboxAsync(\"Only the group creator can delete";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Only the group creator can delete the group."),BA.ObjectToCharSequence("Not allowed"),processBA);
RDebugUtils.currentLine=6488091;
 //BA.debugLineNum = 6488091;BA.debugLine="Return";
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
RDebugUtils.currentLine=6488095;
 //BA.debugLineNum = 6488095;BA.debugLine="Msgbox2Async(\"Delete this group and all its data";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete this group and all its data?"),BA.ObjectToCharSequence("Confirm"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6488096;
 //BA.debugLineNum = 6488096;BA.debugLine="Wait For Msgbox_Result (res2 As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "grouplist_itemlongclick"), null);
this.state = 28;
return;
case 28:
//C
this.state = 22;
_res2 = (Integer) result[0];
;
RDebugUtils.currentLine=6488097;
 //BA.debugLineNum = 6488097;BA.debugLine="If res2 = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=6488098;
 //BA.debugLineNum = 6488098;BA.debugLine="deleteGroup(code)";
_deletegroup(_code);
RDebugUtils.currentLine=6488099;
 //BA.debugLineNum = 6488099;BA.debugLine="loadMyGroups";
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
RDebugUtils.currentLine=6488102;
 //BA.debugLineNum = 6488102;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6553600;
 //BA.debugLineNum = 6553600;BA.debugLine="Sub showRenameGroupPanel(Index As Int, code As Str";
RDebugUtils.currentLine=6553601;
 //BA.debugLineNum = 6553601;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(_kvs._get("group_name_"+_code)));
RDebugUtils.currentLine=6553602;
 //BA.debugLineNum = 6553602;BA.debugLine="groupET.Enabled = True";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6553603;
 //BA.debugLineNum = 6553603;BA.debugLine="groupET.RequestFocus";
mostCurrent._groupet.RequestFocus();
RDebugUtils.currentLine=6553604;
 //BA.debugLineNum = 6553604;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=6553605;
 //BA.debugLineNum = 6553605;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=6553606;
 //BA.debugLineNum = 6553606;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=6553607;
 //BA.debugLineNum = 6553607;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=6553608;
 //BA.debugLineNum = 6553608;BA.debugLine="groupET.Tag = ctx   ' Tag is now a List, not \"cre";
mostCurrent._groupet.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=6553609;
 //BA.debugLineNum = 6553609;BA.debugLine="End Sub";
return "";
}
public static boolean  _iscodeunique(String _code) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "iscodeunique", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "iscodeunique", new Object[] {_code}));}
anywheresoftware.b4a.objects.collections.List _allgroups = null;
String _c = "";
RDebugUtils.currentLine=6225920;
 //BA.debugLineNum = 6225920;BA.debugLine="Sub isCodeUnique(code As String) As Boolean";
RDebugUtils.currentLine=6225921;
 //BA.debugLineNum = 6225921;BA.debugLine="If kvs.ContainsKey(\"groups\") = False Then Return";
if (_kvs._containskey("groups")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
RDebugUtils.currentLine=6225922;
 //BA.debugLineNum = 6225922;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
_allgroups = new anywheresoftware.b4a.objects.collections.List();
_allgroups = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get("groups")));
RDebugUtils.currentLine=6225923;
 //BA.debugLineNum = 6225923;BA.debugLine="For Each c As String In allGroups";
{
final anywheresoftware.b4a.BA.IterableList group3 = _allgroups;
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_c = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=6225924;
 //BA.debugLineNum = 6225924;BA.debugLine="If c = code Then Return False";
if ((_c).equals(_code)) { 
if (true) return anywheresoftware.b4a.keywords.Common.False;};
 }
};
RDebugUtils.currentLine=6225926;
 //BA.debugLineNum = 6225926;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=6225927;
 //BA.debugLineNum = 6225927;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6356992;
 //BA.debugLineNum = 6356992;BA.debugLine="Sub joinBtn_Click";
RDebugUtils.currentLine=6356993;
 //BA.debugLineNum = 6356993;BA.debugLine="Dim joinBtn As Button = Sender";
_joinbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
_joinbtn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=6356994;
 //BA.debugLineNum = 6356994;BA.debugLine="Dim joinET As EditText = joinBtn.Tag";
_joinet = new anywheresoftware.b4a.objects.EditTextWrapper();
_joinet = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_joinbtn.getTag()));
RDebugUtils.currentLine=6356995;
 //BA.debugLineNum = 6356995;BA.debugLine="Dim code As String = joinET.Text.Trim.ToUpperCase";
_code = _joinet.getText().trim().toUpperCase();
RDebugUtils.currentLine=6356997;
 //BA.debugLineNum = 6356997;BA.debugLine="If code.Length <> 6 Then";
if (_code.length()!=6) { 
RDebugUtils.currentLine=6356998;
 //BA.debugLineNum = 6356998;BA.debugLine="MsgboxAsync(\"Code must be exactly 6 characters.\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Code must be exactly 6 characters."),BA.ObjectToCharSequence("Invalid code"),processBA);
RDebugUtils.currentLine=6356999;
 //BA.debugLineNum = 6356999;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=6357002;
 //BA.debugLineNum = 6357002;BA.debugLine="If kvs.ContainsKey(\"group_name_\" & code) = False";
if (_kvs._containskey("group_name_"+_code)==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=6357003;
 //BA.debugLineNum = 6357003;BA.debugLine="MsgboxAsync(\"No group found with that code.\", \"N";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No group found with that code."),BA.ObjectToCharSequence("Not found"),processBA);
RDebugUtils.currentLine=6357004;
 //BA.debugLineNum = 6357004;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=6357008;
 //BA.debugLineNum = 6357008;BA.debugLine="Dim membersKey As String = \"group_members_\" & cod";
_memberskey = "group_members_"+_code;
RDebugUtils.currentLine=6357009;
 //BA.debugLineNum = 6357009;BA.debugLine="Dim members As List = kvs.Get(membersKey)";
_members = new anywheresoftware.b4a.objects.collections.List();
_members = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_memberskey)));
RDebugUtils.currentLine=6357010;
 //BA.debugLineNum = 6357010;BA.debugLine="For Each m As String In members";
{
final anywheresoftware.b4a.BA.IterableList group14 = _members;
final int groupLen14 = group14.getSize()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_m = BA.ObjectToString(group14.Get(index14));
RDebugUtils.currentLine=6357011;
 //BA.debugLineNum = 6357011;BA.debugLine="If m = Starter.currentUserID Then";
if ((_m).equals(mostCurrent._starter._currentuserid /*String*/ )) { 
RDebugUtils.currentLine=6357012;
 //BA.debugLineNum = 6357012;BA.debugLine="MsgboxAsync(\"You are already in this group.\", \"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("You are already in this group."),BA.ObjectToCharSequence("Already joined"),processBA);
RDebugUtils.currentLine=6357013;
 //BA.debugLineNum = 6357013;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=6357017;
 //BA.debugLineNum = 6357017;BA.debugLine="members.Add(Starter.currentUserID)";
_members.Add((Object)(mostCurrent._starter._currentuserid /*String*/ ));
RDebugUtils.currentLine=6357018;
 //BA.debugLineNum = 6357018;BA.debugLine="kvs.Put(membersKey, members)";
_kvs._put(_memberskey,(Object)(_members.getObject()));
RDebugUtils.currentLine=6357022;
 //BA.debugLineNum = 6357022;BA.debugLine="For i = 0 To groupList.Size - 1";
{
final int step22 = 1;
final int limit22 = (int) (mostCurrent._grouplist._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit22 ;_i = _i + step22 ) {
RDebugUtils.currentLine=6357023;
 //BA.debugLineNum = 6357023;BA.debugLine="Dim pnl As B4XView = groupList.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._grouplist._getpanel(_i);
 }
};
RDebugUtils.currentLine=6357026;
 //BA.debugLineNum = 6357026;BA.debugLine="groupList.RemoveAt(groupList.Size - 1)";
mostCurrent._grouplist._removeat((int) (mostCurrent._grouplist._getsize()-1));
RDebugUtils.currentLine=6357028;
 //BA.debugLineNum = 6357028;BA.debugLine="Dim groupName As String = kvs.Get(\"group_name_\" &";
_groupname = BA.ObjectToString(_kvs._get("group_name_"+_code));
RDebugUtils.currentLine=6357029;
 //BA.debugLineNum = 6357029;BA.debugLine="ToastMessageShow(\"Joined group: \" & groupName, Tr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Joined group: "+_groupname),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6357030;
 //BA.debugLineNum = 6357030;BA.debugLine="loadMyGroups";
_loadmygroups();
RDebugUtils.currentLine=6357031;
 //BA.debugLineNum = 6357031;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5242880;
 //BA.debugLineNum = 5242880;BA.debugLine="Sub listsList_ItemClick(Index As Int, Value As Obj";
RDebugUtils.currentLine=5242882;
 //BA.debugLineNum = 5242882;BA.debugLine="If isAddingList Then Return";
if (_isaddinglist) { 
if (true) return "";};
RDebugUtils.currentLine=5242884;
 //BA.debugLineNum = 5242884;BA.debugLine="Dim listPNL As B4XView = listsList.GetPanel(Index";
_listpnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_listpnl = mostCurrent._listslist._getpanel(_index);
RDebugUtils.currentLine=5242885;
 //BA.debugLineNum = 5242885;BA.debugLine="Dim listLBL As Label = listPNL.GetView(0)";
_listlbl = new anywheresoftware.b4a.objects.LabelWrapper();
_listlbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_listpnl.GetView((int) (0)).getObject()));
RDebugUtils.currentLine=5242887;
 //BA.debugLineNum = 5242887;BA.debugLine="currentList = listLBL.Text";
mostCurrent._currentlist = _listlbl.getText();
RDebugUtils.currentLine=5242888;
 //BA.debugLineNum = 5242888;BA.debugLine="addTitleTextArea.Text = currentList";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(mostCurrent._currentlist));
RDebugUtils.currentLine=5242889;
 //BA.debugLineNum = 5242889;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5242891;
 //BA.debugLineNum = 5242891;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=5242893;
 //BA.debugLineNum = 5242893;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+mostCurrent._currentlist;
RDebugUtils.currentLine=5242895;
 //BA.debugLineNum = 5242895;BA.debugLine="If kvs.ContainsKey(key) Then";
if (_kvs._containskey(_key)) { 
RDebugUtils.currentLine=5242896;
 //BA.debugLineNum = 5242896;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_key)));
RDebugUtils.currentLine=5242897;
 //BA.debugLineNum = 5242897;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group11 = _savedtasks;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_task = BA.ObjectToString(group11.Get(index11));
RDebugUtils.currentLine=5242898;
 //BA.debugLineNum = 5242898;BA.debugLine="tasksListUI(task)";
_taskslistui(_task);
 }
};
 };
RDebugUtils.currentLine=5242902;
 //BA.debugLineNum = 5242902;BA.debugLine="tasksList.GetBase.Visible = True";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5242903;
 //BA.debugLineNum = 5242903;BA.debugLine="newAddTaskBtn";
_newaddtaskbtn();
RDebugUtils.currentLine=5242904;
 //BA.debugLineNum = 5242904;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=5242906;
 //BA.debugLineNum = 5242906;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5308418;
 //BA.debugLineNum = 5308418;BA.debugLine="Msgbox2Async(\"Delete or rename this list?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this list?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5308419;
 //BA.debugLineNum = 5308419;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslist_itemlongclick"), null);
this.state = 22;
return;
case 22:
//C
this.state = 1;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=5308421;
 //BA.debugLineNum = 5308421;BA.debugLine="If res = DialogResponse.POSITIVE Then ' Rename";
if (true) break;

case 1:
//if
this.state = 21;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}else 
{RDebugUtils.currentLine=5308424;
 //BA.debugLineNum = 5308424;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then ' Dele";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 5;
}}
if (true) break;

case 3:
//C
this.state = 21;
RDebugUtils.currentLine=5308422;
 //BA.debugLineNum = 5308422;BA.debugLine="showRenameListPanel(Index, Value)";
_showrenamelistpanel(_index,BA.ObjectToString(_value));
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=5308426;
 //BA.debugLineNum = 5308426;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete th";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Are you sure you want to delete the list \""+BA.ObjectToString(_value)+"\"?"),BA.ObjectToCharSequence("Confirmation"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5308427;
 //BA.debugLineNum = 5308427;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslist_itemlongclick"), null);
this.state = 23;
return;
case 23:
//C
this.state = 6;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=5308428;
 //BA.debugLineNum = 5308428;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=5308429;
 //BA.debugLineNum = 5308429;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get("lists")));
RDebugUtils.currentLine=5308430;
 //BA.debugLineNum = 5308430;BA.debugLine="savedLists.RemoveAt(Index)";
_savedlists.RemoveAt(_index);
RDebugUtils.currentLine=5308431;
 //BA.debugLineNum = 5308431;BA.debugLine="kvs.Put(\"lists\", savedLists)";
parent._kvs._put("lists",(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=5308432;
 //BA.debugLineNum = 5308432;BA.debugLine="listsList.RemoveAt(Index)";
parent.mostCurrent._listslist._removeat(_index);
RDebugUtils.currentLine=5308433;
 //BA.debugLineNum = 5308433;BA.debugLine="ToastMessageShow(\"List deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 9:
//C
this.state = 10;
;
RDebugUtils.currentLine=5308437;
 //BA.debugLineNum = 5308437;BA.debugLine="Dim key As String = \"list_\" & Value";
_key = "list_"+BA.ObjectToString(_value);
RDebugUtils.currentLine=5308438;
 //BA.debugLineNum = 5308438;BA.debugLine="If kvs.ContainsKey(key) Then";
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
RDebugUtils.currentLine=5308439;
 //BA.debugLineNum = 5308439;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_key)));
RDebugUtils.currentLine=5308440;
 //BA.debugLineNum = 5308440;BA.debugLine="For Each task As String In savedTasks";
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
RDebugUtils.currentLine=5308441;
 //BA.debugLineNum = 5308441;BA.debugLine="kvs.Remove(\"checked_\" & Value & \"_\" & task)";
parent._kvs._remove("checked_"+BA.ObjectToString(_value)+"_"+_task);
 if (true) break;
if (true) break;

case 16:
//C
this.state = 17;
;
RDebugUtils.currentLine=5308443;
 //BA.debugLineNum = 5308443;BA.debugLine="kvs.Remove(key)";
parent._kvs._remove(_key);
 if (true) break;
;
RDebugUtils.currentLine=5308447;
 //BA.debugLineNum = 5308447;BA.debugLine="If currentList = Value Then";

case 17:
//if
this.state = 20;
if ((parent.mostCurrent._currentlist).equals(BA.ObjectToString(_value))) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
RDebugUtils.currentLine=5308448;
 //BA.debugLineNum = 5308448;BA.debugLine="currentList = \"\"";
parent.mostCurrent._currentlist = "";
RDebugUtils.currentLine=5308449;
 //BA.debugLineNum = 5308449;BA.debugLine="tasksList.Clear";
parent.mostCurrent._taskslist._clear();
RDebugUtils.currentLine=5308450;
 //BA.debugLineNum = 5308450;BA.debugLine="tasksList.GetBase.Visible = False";
parent.mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5308451;
 //BA.debugLineNum = 5308451;BA.debugLine="addTitleTextArea.Text = \"\"";
parent.mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5308452;
 //BA.debugLineNum = 5308452;BA.debugLine="addTitleTextArea.Visible = False";
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
RDebugUtils.currentLine=5308456;
 //BA.debugLineNum = 5308456;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5373952;
 //BA.debugLineNum = 5373952;BA.debugLine="Sub showRenameListPanel(Index As Int, oldTitle As";
RDebugUtils.currentLine=5373954;
 //BA.debugLineNum = 5373954;BA.debugLine="addTitleTextArea.Text = oldTitle";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(_oldtitle));
RDebugUtils.currentLine=5373955;
 //BA.debugLineNum = 5373955;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5373956;
 //BA.debugLineNum = 5373956;BA.debugLine="addTitleTextArea.Enabled = True";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5373957;
 //BA.debugLineNum = 5373957;BA.debugLine="addTitleTextArea.RequestFocus";
mostCurrent._addtitletextarea.RequestFocus();
RDebugUtils.currentLine=5373959;
 //BA.debugLineNum = 5373959;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=5373960;
 //BA.debugLineNum = 5373960;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=5373961;
 //BA.debugLineNum = 5373961;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=5373962;
 //BA.debugLineNum = 5373962;BA.debugLine="ctx.Add(oldTitle)";
_ctx.Add((Object)(_oldtitle));
RDebugUtils.currentLine=5373963;
 //BA.debugLineNum = 5373963;BA.debugLine="addTitleTextArea.Tag = ctx";
mostCurrent._addtitletextarea.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=5373965;
 //BA.debugLineNum = 5373965;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6815744;
 //BA.debugLineNum = 6815744;BA.debugLine="Sub listsListGrp_ItemClick(Index As Int, Value As";
RDebugUtils.currentLine=6815745;
 //BA.debugLineNum = 6815745;BA.debugLine="If Value = \"newListPanel\" Then Return";
if ((_value).equals((Object)("newListPanel"))) { 
if (true) return "";};
RDebugUtils.currentLine=6815746;
 //BA.debugLineNum = 6815746;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(mostCurrent._groupet.getTag());
RDebugUtils.currentLine=6815747;
 //BA.debugLineNum = 6815747;BA.debugLine="Dim currentGrpList As String = Value";
_currentgrplist = BA.ObjectToString(_value);
RDebugUtils.currentLine=6815749;
 //BA.debugLineNum = 6815749;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=6815750;
 //BA.debugLineNum = 6815750;BA.debugLine="tasksListGrp.GetBase.Visible = True";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6815752;
 //BA.debugLineNum = 6815752;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=6815753;
 //BA.debugLineNum = 6815753;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
if (_kvs._containskey(_taskkey)) { 
RDebugUtils.currentLine=6815754;
 //BA.debugLineNum = 6815754;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_taskkey)));
RDebugUtils.currentLine=6815755;
 //BA.debugLineNum = 6815755;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group9 = _savedtasks;
final int groupLen9 = group9.getSize()
;int index9 = 0;
;
for (; index9 < groupLen9;index9++){
_task = BA.ObjectToString(group9.Get(index9));
RDebugUtils.currentLine=6815756;
 //BA.debugLineNum = 6815756;BA.debugLine="tasksListGrpUI(task, code, currentGrpList)";
_taskslistgrpui(_task,_code,_currentgrplist);
 }
};
 };
RDebugUtils.currentLine=6815760;
 //BA.debugLineNum = 6815760;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
_newaddtaskbtngrp(_code,_currentgrplist);
RDebugUtils.currentLine=6815761;
 //BA.debugLineNum = 6815761;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=6815762;
 //BA.debugLineNum = 6815762;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6881281;
 //BA.debugLineNum = 6881281;BA.debugLine="If Value = \"newListPanel\" Then Return";
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
RDebugUtils.currentLine=6881282;
 //BA.debugLineNum = 6881282;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(parent.mostCurrent._groupet.getTag());
RDebugUtils.currentLine=6881284;
 //BA.debugLineNum = 6881284;BA.debugLine="Msgbox2Async(\"Delete or rename this list?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this list?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6881285;
 //BA.debugLineNum = 6881285;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslistgrp_itemlongclick"), null);
this.state = 53;
return;
case 53:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=6881287;
 //BA.debugLineNum = 6881287;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 52;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=6881333;
 //BA.debugLineNum = 6881333;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 39;
}}
if (true) break;

case 9:
//C
this.state = 10;
RDebugUtils.currentLine=6881289;
 //BA.debugLineNum = 6881289;BA.debugLine="Dim newName As String = \"\"";
_newname = "";
RDebugUtils.currentLine=6881290;
 //BA.debugLineNum = 6881290;BA.debugLine="If res = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=6881292;
 //BA.debugLineNum = 6881292;BA.debugLine="showRenameListPanelGrp(Index, Value, code)";
_showrenamelistpanelgrp(_index,BA.ObjectToString(_value),_code);
 if (true) break;

case 13:
//C
this.state = 14;
;
RDebugUtils.currentLine=6881295;
 //BA.debugLineNum = 6881295;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=6881296;
 //BA.debugLineNum = 6881296;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_listskey)));
RDebugUtils.currentLine=6881299;
 //BA.debugLineNum = 6881299;BA.debugLine="For Each existing As String In savedLists";
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
RDebugUtils.currentLine=6881300;
 //BA.debugLineNum = 6881300;BA.debugLine="If existing = newName Then";
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
RDebugUtils.currentLine=6881301;
 //BA.debugLineNum = 6881301;BA.debugLine="MsgboxAsync(\"Name already exists.\", \"Duplicate";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Name already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=6881302;
 //BA.debugLineNum = 6881302;BA.debugLine="Return";
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
RDebugUtils.currentLine=6881307;
 //BA.debugLineNum = 6881307;BA.debugLine="Dim oldTaskKey As String = \"group_list_\" & code";
_oldtaskkey = "group_list_"+_code+"_"+BA.ObjectToString(_value);
RDebugUtils.currentLine=6881308;
 //BA.debugLineNum = 6881308;BA.debugLine="Dim newTaskKey As String = \"group_list_\" & code";
_newtaskkey = "group_list_"+_code+"_"+_newname;
RDebugUtils.currentLine=6881309;
 //BA.debugLineNum = 6881309;BA.debugLine="If kvs.ContainsKey(oldTaskKey) Then";
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
RDebugUtils.currentLine=6881310;
 //BA.debugLineNum = 6881310;BA.debugLine="Dim savedTasks As List = kvs.Get(oldTaskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_oldtaskkey)));
RDebugUtils.currentLine=6881311;
 //BA.debugLineNum = 6881311;BA.debugLine="For Each task As String In savedTasks";
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
RDebugUtils.currentLine=6881312;
 //BA.debugLineNum = 6881312;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code";
_oldck = "group_checked_"+_code+"_"+BA.ObjectToString(_value)+"_"+_task;
RDebugUtils.currentLine=6881313;
 //BA.debugLineNum = 6881313;BA.debugLine="Dim newCK As String = \"group_checked_\" & code";
_newck = "group_checked_"+_code+"_"+_newname+"_"+_task;
RDebugUtils.currentLine=6881314;
 //BA.debugLineNum = 6881314;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
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
RDebugUtils.currentLine=6881315;
 //BA.debugLineNum = 6881315;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
parent._kvs._put(_newck,parent._kvs._get(_oldck));
RDebugUtils.currentLine=6881316;
 //BA.debugLineNum = 6881316;BA.debugLine="kvs.Remove(oldCK)";
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
RDebugUtils.currentLine=6881319;
 //BA.debugLineNum = 6881319;BA.debugLine="kvs.Put(newTaskKey, savedTasks)";
parent._kvs._put(_newtaskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=6881320;
 //BA.debugLineNum = 6881320;BA.debugLine="kvs.Remove(oldTaskKey)";
parent._kvs._remove(_oldtaskkey);
 if (true) break;

case 33:
//C
this.state = 34;
;
RDebugUtils.currentLine=6881323;
 //BA.debugLineNum = 6881323;BA.debugLine="savedLists.Set(Index, newName)";
_savedlists.Set(_index,(Object)(_newname));
RDebugUtils.currentLine=6881324;
 //BA.debugLineNum = 6881324;BA.debugLine="kvs.Put(listsKey, savedLists)";
parent._kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=6881326;
 //BA.debugLineNum = 6881326;BA.debugLine="listsListGrp.Clear";
parent.mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=6881327;
 //BA.debugLineNum = 6881327;BA.debugLine="For Each ln As String In savedLists";
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
RDebugUtils.currentLine=6881328;
 //BA.debugLineNum = 6881328;BA.debugLine="listsListGrp.AddTextItem(ln, ln)";
parent.mostCurrent._listslistgrp._addtextitem((Object)(_ln),(Object)(_ln));
 if (true) break;
if (true) break;

case 37:
//C
this.state = 52;
;
RDebugUtils.currentLine=6881331;
 //BA.debugLineNum = 6881331;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List renamed"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 39:
//C
this.state = 40;
RDebugUtils.currentLine=6881334;
 //BA.debugLineNum = 6881334;BA.debugLine="Msgbox2Async(\"Delete list \"\"\" & Value & \"\"\"?\", \"";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete list \""+BA.ObjectToString(_value)+"\"?"),BA.ObjectToCharSequence("Confirm"),"No","","Yes",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6881335;
 //BA.debugLineNum = 6881335;BA.debugLine="Wait For Msgbox_Result (res2 As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "listslistgrp_itemlongclick"), null);
this.state = 60;
return;
case 60:
//C
this.state = 40;
_res2 = (Integer) result[0];
;
RDebugUtils.currentLine=6881336;
 //BA.debugLineNum = 6881336;BA.debugLine="If res2 = DialogResponse.NEGATIVE Then";
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
RDebugUtils.currentLine=6881337;
 //BA.debugLineNum = 6881337;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=6881338;
 //BA.debugLineNum = 6881338;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_listskey)));
RDebugUtils.currentLine=6881339;
 //BA.debugLineNum = 6881339;BA.debugLine="savedLists.RemoveAt(Index)";
_savedlists.RemoveAt(_index);
RDebugUtils.currentLine=6881340;
 //BA.debugLineNum = 6881340;BA.debugLine="kvs.Put(listsKey, savedLists)";
parent._kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=6881343;
 //BA.debugLineNum = 6881343;BA.debugLine="Dim taskKey As String = \"group_list_\" & code &";
_taskkey = "group_list_"+_code+"_"+BA.ObjectToString(_value);
RDebugUtils.currentLine=6881344;
 //BA.debugLineNum = 6881344;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
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
RDebugUtils.currentLine=6881345;
 //BA.debugLineNum = 6881345;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_taskkey)));
RDebugUtils.currentLine=6881346;
 //BA.debugLineNum = 6881346;BA.debugLine="For Each task As String In savedTasks";
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
RDebugUtils.currentLine=6881347;
 //BA.debugLineNum = 6881347;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & Va";
parent._kvs._remove("group_checked_"+_code+"_"+BA.ObjectToString(_value)+"_"+_task);
 if (true) break;
if (true) break;

case 49:
//C
this.state = 50;
;
RDebugUtils.currentLine=6881349;
 //BA.debugLineNum = 6881349;BA.debugLine="kvs.Remove(taskKey)";
parent._kvs._remove(_taskkey);
 if (true) break;

case 50:
//C
this.state = 51;
;
RDebugUtils.currentLine=6881352;
 //BA.debugLineNum = 6881352;BA.debugLine="listsListGrp.RemoveAt(Index)";
parent.mostCurrent._listslistgrp._removeat(_index);
RDebugUtils.currentLine=6881353;
 //BA.debugLineNum = 6881353;BA.debugLine="tasksListGrp.Clear";
parent.mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=6881354;
 //BA.debugLineNum = 6881354;BA.debugLine="tasksListGrp.GetBase.Visible = False";
parent.mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6881355;
 //BA.debugLineNum = 6881355;BA.debugLine="ToastMessageShow(\"List deleted\", False)";
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
RDebugUtils.currentLine=6881358;
 //BA.debugLineNum = 6881358;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6946816;
 //BA.debugLineNum = 6946816;BA.debugLine="Sub showRenameListPanelGrp(Index As Int, oldName A";
RDebugUtils.currentLine=6946817;
 //BA.debugLineNum = 6946817;BA.debugLine="Dim renamePNL As Panel";
_renamepnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=6946818;
 //BA.debugLineNum = 6946818;BA.debugLine="renamePNL.Initialize(\"renameListPNLGrp\")";
_renamepnl.Initialize(mostCurrent.activityBA,"renameListPNLGrp");
RDebugUtils.currentLine=6946819;
 //BA.debugLineNum = 6946819;BA.debugLine="renamePNL.SetLayout(0, 0, 250dip, 120dip)";
_renamepnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=6946820;
 //BA.debugLineNum = 6946820;BA.debugLine="renamePNL.Color = Colors.Transparent";
_renamepnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=6946822;
 //BA.debugLineNum = 6946822;BA.debugLine="Dim renameET As EditText";
_renameet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=6946823;
 //BA.debugLineNum = 6946823;BA.debugLine="renameET.Initialize(\"renameListETGrp\")";
_renameet.Initialize(mostCurrent.activityBA,"renameListETGrp");
RDebugUtils.currentLine=6946824;
 //BA.debugLineNum = 6946824;BA.debugLine="renameET.Text = oldName";
_renameet.setText(BA.ObjectToCharSequence(_oldname));
RDebugUtils.currentLine=6946826;
 //BA.debugLineNum = 6946826;BA.debugLine="Dim renameBtn As Button";
_renamebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=6946827;
 //BA.debugLineNum = 6946827;BA.debugLine="renameBtn.Initialize(\"renameListBtnGrp\")";
_renamebtn.Initialize(mostCurrent.activityBA,"renameListBtnGrp");
RDebugUtils.currentLine=6946828;
 //BA.debugLineNum = 6946828;BA.debugLine="renameBtn.Text = \"Rename\"";
_renamebtn.setText(BA.ObjectToCharSequence("Rename"));
RDebugUtils.currentLine=6946830;
 //BA.debugLineNum = 6946830;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=6946831;
 //BA.debugLineNum = 6946831;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=6946832;
 //BA.debugLineNum = 6946832;BA.debugLine="renameBtn.Background = cd";
_renamebtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=6946833;
 //BA.debugLineNum = 6946833;BA.debugLine="renameBtn.TextColor = Colors.White";
_renamebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=6946835;
 //BA.debugLineNum = 6946835;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=6946836;
 //BA.debugLineNum = 6946836;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=6946837;
 //BA.debugLineNum = 6946837;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=6946838;
 //BA.debugLineNum = 6946838;BA.debugLine="ctx.Add(oldName)";
_ctx.Add((Object)(_oldname));
RDebugUtils.currentLine=6946839;
 //BA.debugLineNum = 6946839;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=6946840;
 //BA.debugLineNum = 6946840;BA.debugLine="ctx.Add(renameET)";
_ctx.Add((Object)(_renameet.getObject()));
RDebugUtils.currentLine=6946841;
 //BA.debugLineNum = 6946841;BA.debugLine="renameBtn.Tag = ctx";
_renamebtn.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=6946843;
 //BA.debugLineNum = 6946843;BA.debugLine="renamePNL.AddView(renameET, 0, 0, 250dip, 55dip)";
_renamepnl.AddView((android.view.View)(_renameet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=6946844;
 //BA.debugLineNum = 6946844;BA.debugLine="renamePNL.AddView(renameBtn, 0, 65dip, 250dip, 40";
_renamepnl.AddView((android.view.View)(_renamebtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=6946846;
 //BA.debugLineNum = 6946846;BA.debugLine="listsListGrp.Add(renamePNL, \"renameListPanel\")";
mostCurrent._listslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_renamepnl.getObject())),(Object)("renameListPanel"));
RDebugUtils.currentLine=6946847;
 //BA.debugLineNum = 6946847;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5963777;
 //BA.debugLineNum = 5963777;BA.debugLine="Msgbox2Async(\"Join or create a group?\", \"Groups\",";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Join or create a group?"),BA.ObjectToCharSequence("Groups"),"Create","","Join",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5963778;
 //BA.debugLineNum = 5963778;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "newgroupbtn_click"), null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=5963780;
 //BA.debugLineNum = 5963780;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}else 
{RDebugUtils.currentLine=5963783;
 //BA.debugLineNum = 5963783;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 5;
}}
if (true) break;

case 3:
//C
this.state = 6;
RDebugUtils.currentLine=5963782;
 //BA.debugLineNum = 5963782;BA.debugLine="showCreateGroupPanel";
_showcreategrouppanel();
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=5963785;
 //BA.debugLineNum = 5963785;BA.debugLine="showJoinGroupPanel";
_showjoingrouppanel();
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=5963787;
 //BA.debugLineNum = 5963787;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _showcreategrouppanel() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showcreategrouppanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showcreategrouppanel", null));}
RDebugUtils.currentLine=6029312;
 //BA.debugLineNum = 6029312;BA.debugLine="Sub showCreateGroupPanel";
RDebugUtils.currentLine=6029314;
 //BA.debugLineNum = 6029314;BA.debugLine="groupET.Text = \"\"";
mostCurrent._groupet.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=6029315;
 //BA.debugLineNum = 6029315;BA.debugLine="groupET.Hint = \"Enter group name...\"";
mostCurrent._groupet.setHint("Enter group name...");
RDebugUtils.currentLine=6029316;
 //BA.debugLineNum = 6029316;BA.debugLine="groupET.Enabled = True";
mostCurrent._groupet.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6029317;
 //BA.debugLineNum = 6029317;BA.debugLine="groupET.Tag = \"creating\"";
mostCurrent._groupet.setTag((Object)("creating"));
RDebugUtils.currentLine=6029318;
 //BA.debugLineNum = 6029318;BA.debugLine="groupET.RequestFocus";
mostCurrent._groupet.RequestFocus();
RDebugUtils.currentLine=6029319;
 //BA.debugLineNum = 6029319;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6291456;
 //BA.debugLineNum = 6291456;BA.debugLine="Sub showJoinGroupPanel";
RDebugUtils.currentLine=6291458;
 //BA.debugLineNum = 6291458;BA.debugLine="Dim joinPNL As Panel";
_joinpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=6291459;
 //BA.debugLineNum = 6291459;BA.debugLine="joinPNL.Initialize(\"joinPNL\")";
_joinpnl.Initialize(mostCurrent.activityBA,"joinPNL");
RDebugUtils.currentLine=6291460;
 //BA.debugLineNum = 6291460;BA.debugLine="joinPNL.SetLayout(0, 0, 250dip, 120dip)";
_joinpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=6291461;
 //BA.debugLineNum = 6291461;BA.debugLine="joinPNL.Color = Colors.Transparent";
_joinpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=6291463;
 //BA.debugLineNum = 6291463;BA.debugLine="Dim joinET As EditText";
_joinet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=6291464;
 //BA.debugLineNum = 6291464;BA.debugLine="joinET.Initialize(\"joinET\")";
_joinet.Initialize(mostCurrent.activityBA,"joinET");
RDebugUtils.currentLine=6291465;
 //BA.debugLineNum = 6291465;BA.debugLine="joinET.Hint = \"Enter 6-char group code...\"";
_joinet.setHint("Enter 6-char group code...");
RDebugUtils.currentLine=6291467;
 //BA.debugLineNum = 6291467;BA.debugLine="Dim joinBtn As Button";
_joinbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=6291468;
 //BA.debugLineNum = 6291468;BA.debugLine="joinBtn.Initialize(\"joinBtn\")";
_joinbtn.Initialize(mostCurrent.activityBA,"joinBtn");
RDebugUtils.currentLine=6291469;
 //BA.debugLineNum = 6291469;BA.debugLine="joinBtn.Text = \"Join Group\"";
_joinbtn.setText(BA.ObjectToCharSequence("Join Group"));
RDebugUtils.currentLine=6291471;
 //BA.debugLineNum = 6291471;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=6291472;
 //BA.debugLineNum = 6291472;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=6291473;
 //BA.debugLineNum = 6291473;BA.debugLine="joinBtn.Background = cd";
_joinbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=6291474;
 //BA.debugLineNum = 6291474;BA.debugLine="joinBtn.TextColor = Colors.White";
_joinbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=6291476;
 //BA.debugLineNum = 6291476;BA.debugLine="joinPNL.AddView(joinET, 0, 0, 250dip, 55dip)";
_joinpnl.AddView((android.view.View)(_joinet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=6291477;
 //BA.debugLineNum = 6291477;BA.debugLine="joinPNL.AddView(joinBtn, 0, 65dip, 250dip, 40dip)";
_joinpnl.AddView((android.view.View)(_joinbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=6291480;
 //BA.debugLineNum = 6291480;BA.debugLine="joinBtn.Tag = joinET";
_joinbtn.setTag((Object)(_joinet.getObject()));
RDebugUtils.currentLine=6291482;
 //BA.debugLineNum = 6291482;BA.debugLine="groupList.Add(joinPNL, \"joinPanel\")";
mostCurrent._grouplist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_joinpnl.getObject())),(Object)("joinPanel"));
RDebugUtils.currentLine=6291483;
 //BA.debugLineNum = 6291483;BA.debugLine="End Sub";
return "";
}
public static String  _newlistbtn_click() throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "newlistbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "newlistbtn_click", null));}
RDebugUtils.currentLine=5111808;
 //BA.debugLineNum = 5111808;BA.debugLine="Sub newListBtn_Click";
RDebugUtils.currentLine=5111810;
 //BA.debugLineNum = 5111810;BA.debugLine="tasksList.Clear";
mostCurrent._taskslist._clear();
RDebugUtils.currentLine=5111811;
 //BA.debugLineNum = 5111811;BA.debugLine="isAddingList = True";
_isaddinglist = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=5111813;
 //BA.debugLineNum = 5111813;BA.debugLine="progressNumber.Text = \"\"";
mostCurrent._progressnumber.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5111814;
 //BA.debugLineNum = 5111814;BA.debugLine="progressPercent.Text = \"\"";
mostCurrent._progresspercent.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5111815;
 //BA.debugLineNum = 5111815;BA.debugLine="progressBar.Progress = 0";
mostCurrent._progressbar.setProgress((int) (0));
RDebugUtils.currentLine=5111817;
 //BA.debugLineNum = 5111817;BA.debugLine="addTitleTextArea.Visible = True";
mostCurrent._addtitletextarea.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5111818;
 //BA.debugLineNum = 5111818;BA.debugLine="addTitleTextArea.Enabled = True";
mostCurrent._addtitletextarea.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5111819;
 //BA.debugLineNum = 5111819;BA.debugLine="addTitleTextArea.Background = Null";
mostCurrent._addtitletextarea.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=5111820;
 //BA.debugLineNum = 5111820;BA.debugLine="addTitleTextArea.Text = \"\"";
mostCurrent._addtitletextarea.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=5111821;
 //BA.debugLineNum = 5111821;BA.debugLine="addTitleTextArea.Hint = \"+ add a title...\"";
mostCurrent._addtitletextarea.setHint("+ add a title...");
RDebugUtils.currentLine=5111822;
 //BA.debugLineNum = 5111822;BA.debugLine="addTitleTextArea.RequestFocus";
mostCurrent._addtitletextarea.RequestFocus();
RDebugUtils.currentLine=5111823;
 //BA.debugLineNum = 5111823;BA.debugLine="addTitleTextArea.Tag = Null";
mostCurrent._addtitletextarea.setTag(anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=5111825;
 //BA.debugLineNum = 5111825;BA.debugLine="newListBtn.Enabled = False";
mostCurrent._newlistbtn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5111827;
 //BA.debugLineNum = 5111827;BA.debugLine="tasksList.GetBase.Visible = True";
mostCurrent._taskslist._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5111828;
 //BA.debugLineNum = 5111828;BA.debugLine="addTaskBtn.Visible = True";
mostCurrent._addtaskbtn.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5111830;
 //BA.debugLineNum = 5111830;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6684672;
 //BA.debugLineNum = 6684672;BA.debugLine="Sub newListBtnGrp_Click";
RDebugUtils.currentLine=6684673;
 //BA.debugLineNum = 6684673;BA.debugLine="If groupET.Tag = Null Or groupET.Tag Is List Then";
if (mostCurrent._groupet.getTag()== null || mostCurrent._groupet.getTag() instanceof java.util.List) { 
if (true) return "";};
RDebugUtils.currentLine=6684675;
 //BA.debugLineNum = 6684675;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(mostCurrent._groupet.getTag());
RDebugUtils.currentLine=6684677;
 //BA.debugLineNum = 6684677;BA.debugLine="listsListGrp.GetBase.Visible = True";
mostCurrent._listslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=6684678;
 //BA.debugLineNum = 6684678;BA.debugLine="tasksListGrp.Clear";
mostCurrent._taskslistgrp._clear();
RDebugUtils.currentLine=6684679;
 //BA.debugLineNum = 6684679;BA.debugLine="tasksListGrp.GetBase.Visible = False";
mostCurrent._taskslistgrp._getbase().setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6684682;
 //BA.debugLineNum = 6684682;BA.debugLine="Dim newListPNL As Panel";
_newlistpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=6684683;
 //BA.debugLineNum = 6684683;BA.debugLine="newListPNL.Initialize(\"newListPNLGrp\")";
_newlistpnl.Initialize(mostCurrent.activityBA,"newListPNLGrp");
RDebugUtils.currentLine=6684684;
 //BA.debugLineNum = 6684684;BA.debugLine="newListPNL.SetLayout(0, 0, 250dip, 120dip)";
_newlistpnl.SetLayout((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=6684685;
 //BA.debugLineNum = 6684685;BA.debugLine="newListPNL.Color = Colors.Transparent";
_newlistpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=6684687;
 //BA.debugLineNum = 6684687;BA.debugLine="Dim newListET As EditText";
_newlistet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=6684688;
 //BA.debugLineNum = 6684688;BA.debugLine="newListET.Initialize(\"newListETGrp\")";
_newlistet.Initialize(mostCurrent.activityBA,"newListETGrp");
RDebugUtils.currentLine=6684689;
 //BA.debugLineNum = 6684689;BA.debugLine="newListET.Hint = \"List name...\"";
_newlistet.setHint("List name...");
RDebugUtils.currentLine=6684691;
 //BA.debugLineNum = 6684691;BA.debugLine="Dim newListConfirmBtn As Button";
_newlistconfirmbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=6684692;
 //BA.debugLineNum = 6684692;BA.debugLine="newListConfirmBtn.Initialize(\"newListConfirmBtnGr";
_newlistconfirmbtn.Initialize(mostCurrent.activityBA,"newListConfirmBtnGrp");
RDebugUtils.currentLine=6684693;
 //BA.debugLineNum = 6684693;BA.debugLine="newListConfirmBtn.Text = \"Create List\"";
_newlistconfirmbtn.setText(BA.ObjectToCharSequence("Create List"));
RDebugUtils.currentLine=6684695;
 //BA.debugLineNum = 6684695;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=6684696;
 //BA.debugLineNum = 6684696;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=6684697;
 //BA.debugLineNum = 6684697;BA.debugLine="newListConfirmBtn.Background = cd";
_newlistconfirmbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=6684698;
 //BA.debugLineNum = 6684698;BA.debugLine="newListConfirmBtn.TextColor = Colors.White";
_newlistconfirmbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=6684700;
 //BA.debugLineNum = 6684700;BA.debugLine="newListPNL.AddView(newListET, 0, 0, 250dip, 55dip";
_newlistpnl.AddView((android.view.View)(_newlistet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=6684701;
 //BA.debugLineNum = 6684701;BA.debugLine="newListPNL.AddView(newListConfirmBtn, 0, 65dip, 2";
_newlistpnl.AddView((android.view.View)(_newlistconfirmbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=6684703;
 //BA.debugLineNum = 6684703;BA.debugLine="newListConfirmBtn.Tag = newListET";
_newlistconfirmbtn.setTag((Object)(_newlistet.getObject()));
RDebugUtils.currentLine=6684705;
 //BA.debugLineNum = 6684705;BA.debugLine="listsListGrp.Add(newListPNL, \"newListPanel\")";
mostCurrent._listslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_newlistpnl.getObject())),(Object)("newListPanel"));
RDebugUtils.currentLine=6684706;
 //BA.debugLineNum = 6684706;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=6750208;
 //BA.debugLineNum = 6750208;BA.debugLine="Sub newListConfirmBtnGrp_Click";
RDebugUtils.currentLine=6750209;
 //BA.debugLineNum = 6750209;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=6750210;
 //BA.debugLineNum = 6750210;BA.debugLine="Dim et As EditText = btn.Tag";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
_et = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_btn.getTag()));
RDebugUtils.currentLine=6750211;
 //BA.debugLineNum = 6750211;BA.debugLine="Dim listName As String = et.Text.Trim";
_listname = _et.getText().trim();
RDebugUtils.currentLine=6750212;
 //BA.debugLineNum = 6750212;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(mostCurrent._groupet.getTag());
RDebugUtils.currentLine=6750214;
 //BA.debugLineNum = 6750214;BA.debugLine="If listName = \"\" Then";
if ((_listname).equals("")) { 
RDebugUtils.currentLine=6750215;
 //BA.debugLineNum = 6750215;BA.debugLine="MsgboxAsync(\"Please enter a list name.\", \"No nam";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a list name."),BA.ObjectToCharSequence("No name"),processBA);
RDebugUtils.currentLine=6750216;
 //BA.debugLineNum = 6750216;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=6750219;
 //BA.debugLineNum = 6750219;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=6750220;
 //BA.debugLineNum = 6750220;BA.debugLine="Dim savedLists As List";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=6750221;
 //BA.debugLineNum = 6750221;BA.debugLine="savedLists.Initialize";
_savedlists.Initialize();
RDebugUtils.currentLine=6750222;
 //BA.debugLineNum = 6750222;BA.debugLine="If kvs.ContainsKey(listsKey) Then savedLists = kv";
if (_kvs._containskey(_listskey)) { 
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));};
RDebugUtils.currentLine=6750224;
 //BA.debugLineNum = 6750224;BA.debugLine="For Each existing As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group13 = _savedlists;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_existing = BA.ObjectToString(group13.Get(index13));
RDebugUtils.currentLine=6750225;
 //BA.debugLineNum = 6750225;BA.debugLine="If existing = listName Then";
if ((_existing).equals(_listname)) { 
RDebugUtils.currentLine=6750226;
 //BA.debugLineNum = 6750226;BA.debugLine="MsgboxAsync(\"A list with that name already exis";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("A list with that name already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=6750227;
 //BA.debugLineNum = 6750227;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=6750231;
 //BA.debugLineNum = 6750231;BA.debugLine="savedLists.Add(listName)";
_savedlists.Add((Object)(_listname));
RDebugUtils.currentLine=6750232;
 //BA.debugLineNum = 6750232;BA.debugLine="kvs.Put(listsKey, savedLists)";
_kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=6750235;
 //BA.debugLineNum = 6750235;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
mostCurrent._listslistgrp._removeat((int) (mostCurrent._listslistgrp._getsize()-1));
RDebugUtils.currentLine=6750236;
 //BA.debugLineNum = 6750236;BA.debugLine="listsListGrp.AddTextItem(listName, listName)";
mostCurrent._listslistgrp._addtextitem((Object)(_listname),(Object)(_listname));
RDebugUtils.currentLine=6750238;
 //BA.debugLineNum = 6750238;BA.debugLine="ToastMessageShow(\"List created\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List created"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6750239;
 //BA.debugLineNum = 6750239;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7012352;
 //BA.debugLineNum = 7012352;BA.debugLine="Sub renameListBtnGrp_Click";
RDebugUtils.currentLine=7012353;
 //BA.debugLineNum = 7012353;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=7012354;
 //BA.debugLineNum = 7012354;BA.debugLine="Dim ctx As List = btn.Tag";
_ctx = new anywheresoftware.b4a.objects.collections.List();
_ctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_btn.getTag()));
RDebugUtils.currentLine=7012355;
 //BA.debugLineNum = 7012355;BA.debugLine="Dim Index As Int = ctx.Get(0)";
_index = (int)(BA.ObjectToNumber(_ctx.Get((int) (0))));
RDebugUtils.currentLine=7012356;
 //BA.debugLineNum = 7012356;BA.debugLine="Dim oldName As String = ctx.Get(1)";
_oldname = BA.ObjectToString(_ctx.Get((int) (1)));
RDebugUtils.currentLine=7012357;
 //BA.debugLineNum = 7012357;BA.debugLine="Dim code As String = ctx.Get(2)";
_code = BA.ObjectToString(_ctx.Get((int) (2)));
RDebugUtils.currentLine=7012358;
 //BA.debugLineNum = 7012358;BA.debugLine="Dim renameET As EditText = ctx.Get(3)";
_renameet = new anywheresoftware.b4a.objects.EditTextWrapper();
_renameet = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_ctx.Get((int) (3))));
RDebugUtils.currentLine=7012359;
 //BA.debugLineNum = 7012359;BA.debugLine="Dim newName As String = renameET.Text.Trim";
_newname = _renameet.getText().trim();
RDebugUtils.currentLine=7012361;
 //BA.debugLineNum = 7012361;BA.debugLine="If newName = \"\" Or newName = oldName Then";
if ((_newname).equals("") || (_newname).equals(_oldname)) { 
RDebugUtils.currentLine=7012362;
 //BA.debugLineNum = 7012362;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
mostCurrent._listslistgrp._removeat((int) (mostCurrent._listslistgrp._getsize()-1));
RDebugUtils.currentLine=7012363;
 //BA.debugLineNum = 7012363;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=7012366;
 //BA.debugLineNum = 7012366;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
_listskey = "group_lists_"+_code;
RDebugUtils.currentLine=7012367;
 //BA.debugLineNum = 7012367;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
_savedlists = new anywheresoftware.b4a.objects.collections.List();
_savedlists = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_listskey)));
RDebugUtils.currentLine=7012369;
 //BA.debugLineNum = 7012369;BA.debugLine="For Each existing As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group14 = _savedlists;
final int groupLen14 = group14.getSize()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_existing = BA.ObjectToString(group14.Get(index14));
RDebugUtils.currentLine=7012370;
 //BA.debugLineNum = 7012370;BA.debugLine="If existing = newName Then";
if ((_existing).equals(_newname)) { 
RDebugUtils.currentLine=7012371;
 //BA.debugLineNum = 7012371;BA.debugLine="MsgboxAsync(\"Name already exists.\", \"Duplicate\"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Name already exists."),BA.ObjectToCharSequence("Duplicate"),processBA);
RDebugUtils.currentLine=7012372;
 //BA.debugLineNum = 7012372;BA.debugLine="Return";
if (true) return "";
 };
 }
};
RDebugUtils.currentLine=7012377;
 //BA.debugLineNum = 7012377;BA.debugLine="Dim oldTaskKey As String = \"group_list_\" & code &";
_oldtaskkey = "group_list_"+_code+"_"+_oldname;
RDebugUtils.currentLine=7012378;
 //BA.debugLineNum = 7012378;BA.debugLine="Dim newTaskKey As String = \"group_list_\" & code &";
_newtaskkey = "group_list_"+_code+"_"+_newname;
RDebugUtils.currentLine=7012379;
 //BA.debugLineNum = 7012379;BA.debugLine="If kvs.ContainsKey(oldTaskKey) Then";
if (_kvs._containskey(_oldtaskkey)) { 
RDebugUtils.currentLine=7012380;
 //BA.debugLineNum = 7012380;BA.debugLine="Dim savedTasks As List = kvs.Get(oldTaskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_kvs._get(_oldtaskkey)));
RDebugUtils.currentLine=7012381;
 //BA.debugLineNum = 7012381;BA.debugLine="For Each task As String In savedTasks";
{
final anywheresoftware.b4a.BA.IterableList group24 = _savedtasks;
final int groupLen24 = group24.getSize()
;int index24 = 0;
;
for (; index24 < groupLen24;index24++){
_task = BA.ObjectToString(group24.Get(index24));
RDebugUtils.currentLine=7012382;
 //BA.debugLineNum = 7012382;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code &";
_oldck = "group_checked_"+_code+"_"+_oldname+"_"+_task;
RDebugUtils.currentLine=7012383;
 //BA.debugLineNum = 7012383;BA.debugLine="Dim newCK As String = \"group_checked_\" & code &";
_newck = "group_checked_"+_code+"_"+_newname+"_"+_task;
RDebugUtils.currentLine=7012384;
 //BA.debugLineNum = 7012384;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
if (_kvs._containskey(_oldck)) { 
RDebugUtils.currentLine=7012385;
 //BA.debugLineNum = 7012385;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
_kvs._put(_newck,_kvs._get(_oldck));
RDebugUtils.currentLine=7012386;
 //BA.debugLineNum = 7012386;BA.debugLine="kvs.Remove(oldCK)";
_kvs._remove(_oldck);
 };
 }
};
RDebugUtils.currentLine=7012389;
 //BA.debugLineNum = 7012389;BA.debugLine="kvs.Put(newTaskKey, savedTasks)";
_kvs._put(_newtaskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=7012390;
 //BA.debugLineNum = 7012390;BA.debugLine="kvs.Remove(oldTaskKey)";
_kvs._remove(_oldtaskkey);
 };
RDebugUtils.currentLine=7012393;
 //BA.debugLineNum = 7012393;BA.debugLine="savedLists.Set(Index, newName)";
_savedlists.Set(_index,(Object)(_newname));
RDebugUtils.currentLine=7012394;
 //BA.debugLineNum = 7012394;BA.debugLine="kvs.Put(listsKey, savedLists)";
_kvs._put(_listskey,(Object)(_savedlists.getObject()));
RDebugUtils.currentLine=7012396;
 //BA.debugLineNum = 7012396;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
mostCurrent._listslistgrp._removeat((int) (mostCurrent._listslistgrp._getsize()-1));
RDebugUtils.currentLine=7012397;
 //BA.debugLineNum = 7012397;BA.debugLine="listsListGrp.Clear";
mostCurrent._listslistgrp._clear();
RDebugUtils.currentLine=7012398;
 //BA.debugLineNum = 7012398;BA.debugLine="For Each ln As String In savedLists";
{
final anywheresoftware.b4a.BA.IterableList group39 = _savedlists;
final int groupLen39 = group39.getSize()
;int index39 = 0;
;
for (; index39 < groupLen39;index39++){
_ln = BA.ObjectToString(group39.Get(index39));
RDebugUtils.currentLine=7012399;
 //BA.debugLineNum = 7012399;BA.debugLine="listsListGrp.AddTextItem(ln, ln)";
mostCurrent._listslistgrp._addtextitem((Object)(_ln),(Object)(_ln));
 }
};
RDebugUtils.currentLine=7012402;
 //BA.debugLineNum = 7012402;BA.debugLine="If currentGrpListName = oldName Then currentGrpLi";
if ((mostCurrent._currentgrplistname).equals(_oldname)) { 
mostCurrent._currentgrplistname = _newname;};
RDebugUtils.currentLine=7012403;
 //BA.debugLineNum = 7012403;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("List renamed"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=7012404;
 //BA.debugLineNum = 7012404;BA.debugLine="End Sub";
return "";
}
public static String  _showrenametaskpanel(int _index,String _oldtask) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showrenametaskpanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showrenametaskpanel", new Object[] {_index,_oldtask}));}
anywheresoftware.b4a.objects.collections.List _ctx = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=5636096;
 //BA.debugLineNum = 5636096;BA.debugLine="Sub showRenameTaskPanel(Index As Int, oldTask As S";
RDebugUtils.currentLine=5636098;
 //BA.debugLineNum = 5636098;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1) ' remove \"";
mostCurrent._taskslist._removeat((int) (mostCurrent._taskslist._getsize()-1));
RDebugUtils.currentLine=5636100;
 //BA.debugLineNum = 5636100;BA.debugLine="addTaskPanel.Initialize(\"addTaskPanel\")";
mostCurrent._addtaskpanel.Initialize(mostCurrent.activityBA,"addTaskPanel");
RDebugUtils.currentLine=5636101;
 //BA.debugLineNum = 5636101;BA.debugLine="addTaskPanel.SetLayout(10dip, 0, 240dip, 120dip)";
mostCurrent._addtaskpanel.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=5636102;
 //BA.debugLineNum = 5636102;BA.debugLine="addTaskPanel.Color = Colors.ARGB(0, 247, 247, 247";
mostCurrent._addtaskpanel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5636104;
 //BA.debugLineNum = 5636104;BA.debugLine="addTaskTextArea.Initialize(\"addTodoText\")";
mostCurrent._addtasktextarea.Initialize(mostCurrent.activityBA,"addTodoText");
RDebugUtils.currentLine=5636105;
 //BA.debugLineNum = 5636105;BA.debugLine="addTaskTextArea.Text = oldTask";
mostCurrent._addtasktextarea.setText(BA.ObjectToCharSequence(_oldtask));
RDebugUtils.currentLine=5636106;
 //BA.debugLineNum = 5636106;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=5636107;
 //BA.debugLineNum = 5636107;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=5636108;
 //BA.debugLineNum = 5636108;BA.debugLine="ctx.Add(Index)";
_ctx.Add((Object)(_index));
RDebugUtils.currentLine=5636109;
 //BA.debugLineNum = 5636109;BA.debugLine="ctx.Add(oldTask)";
_ctx.Add((Object)(_oldtask));
RDebugUtils.currentLine=5636110;
 //BA.debugLineNum = 5636110;BA.debugLine="addTaskTextArea.Tag = ctx";
mostCurrent._addtasktextarea.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=5636112;
 //BA.debugLineNum = 5636112;BA.debugLine="enterTaskBtn.Initialize(\"enterTaskBtn\")";
mostCurrent._entertaskbtn.Initialize(mostCurrent.activityBA,"enterTaskBtn");
RDebugUtils.currentLine=5636113;
 //BA.debugLineNum = 5636113;BA.debugLine="enterTaskBtn.Text = \"Rename task\"";
mostCurrent._entertaskbtn.setText(BA.ObjectToCharSequence("Rename task"));
RDebugUtils.currentLine=5636115;
 //BA.debugLineNum = 5636115;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=5636116;
 //BA.debugLineNum = 5636116;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=5636118;
 //BA.debugLineNum = 5636118;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5636119;
 //BA.debugLineNum = 5636119;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5636120;
 //BA.debugLineNum = 5636120;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5636121;
 //BA.debugLineNum = 5636121;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=5636122;
 //BA.debugLineNum = 5636122;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5636123;
 //BA.debugLineNum = 5636123;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5636125;
 //BA.debugLineNum = 5636125;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=5636126;
 //BA.debugLineNum = 5636126;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5636127;
 //BA.debugLineNum = 5636127;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=5636128;
 //BA.debugLineNum = 5636128;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5636129;
 //BA.debugLineNum = 5636129;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 break; }
case 1: {
RDebugUtils.currentLine=5636132;
 //BA.debugLineNum = 5636132;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5636133;
 //BA.debugLineNum = 5636133;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5636134;
 //BA.debugLineNum = 5636134;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5636135;
 //BA.debugLineNum = 5636135;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=5636136;
 //BA.debugLineNum = 5636136;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5636137;
 //BA.debugLineNum = 5636137;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5636139;
 //BA.debugLineNum = 5636139;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=5636140;
 //BA.debugLineNum = 5636140;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=5636141;
 //BA.debugLineNum = 5636141;BA.debugLine="cd.Initialize(Colors.ARGB(255, 137, 162, 185),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (137),(int) (162),(int) (185)),(int) (200));
RDebugUtils.currentLine=5636142;
 //BA.debugLineNum = 5636142;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5636143;
 //BA.debugLineNum = 5636143;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
case 2: {
RDebugUtils.currentLine=5636146;
 //BA.debugLineNum = 5636146;BA.debugLine="addTaskTextArea.Typeface = pixeltf";
mostCurrent._addtasktextarea.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=5636147;
 //BA.debugLineNum = 5636147;BA.debugLine="enterTaskBtn.Typeface = pixeltf";
mostCurrent._entertaskbtn.setTypeface((android.graphics.Typeface)(mostCurrent._pixeltf.getObject()));
RDebugUtils.currentLine=5636148;
 //BA.debugLineNum = 5636148;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5636149;
 //BA.debugLineNum = 5636149;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (247),(int) (247),(int) (247)));
RDebugUtils.currentLine=5636150;
 //BA.debugLineNum = 5636150;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=5636151;
 //BA.debugLineNum = 5636151;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (90),(int) (105),(int) (136)),(int) (200));
RDebugUtils.currentLine=5636152;
 //BA.debugLineNum = 5636152;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5636153;
 //BA.debugLineNum = 5636153;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=5636155;
 //BA.debugLineNum = 5636155;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
mostCurrent._addtasktextarea.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (17),(int) (17),(int) (17)));
RDebugUtils.currentLine=5636156;
 //BA.debugLineNum = 5636156;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
mostCurrent._addtasktextarea.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=5636157;
 //BA.debugLineNum = 5636157;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (120),(int) (184),(int) (120),(int) (46)),(int) (200));
RDebugUtils.currentLine=5636158;
 //BA.debugLineNum = 5636158;BA.debugLine="enterTaskBtn.Background = cd";
mostCurrent._entertaskbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5636159;
 //BA.debugLineNum = 5636159;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
mostCurrent._entertaskbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 break; }
}
;
RDebugUtils.currentLine=5636163;
 //BA.debugLineNum = 5636163;BA.debugLine="addTaskPanel.AddView(addTaskTextArea, 0, 0, addTa";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._addtasktextarea.getObject()),(int) (0),(int) (0),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=5636164;
 //BA.debugLineNum = 5636164;BA.debugLine="addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addT";
mostCurrent._addtaskpanel.AddView((android.view.View)(mostCurrent._entertaskbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),mostCurrent._addtaskbtnpnl.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=5636166;
 //BA.debugLineNum = 5636166;BA.debugLine="tasksList.Add(addTaskPanel, addTaskPanel)";
mostCurrent._taskslist._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._addtaskpanel.getObject())),(Object)(mostCurrent._addtaskpanel.getObject()));
RDebugUtils.currentLine=5636168;
 //BA.debugLineNum = 5636168;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7340032;
 //BA.debugLineNum = 7340032;BA.debugLine="Sub showRenameTaskPanelGrp(Index As Int, oldTask A";
RDebugUtils.currentLine=7340033;
 //BA.debugLineNum = 7340033;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
mostCurrent._taskslistgrp._removeat((int) (mostCurrent._taskslistgrp._getsize()-1));
RDebugUtils.currentLine=7340035;
 //BA.debugLineNum = 7340035;BA.debugLine="Dim addPNL As Panel";
_addpnl = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=7340036;
 //BA.debugLineNum = 7340036;BA.debugLine="addPNL.Initialize(\"addTaskPNLGrp\")";
_addpnl.Initialize(mostCurrent.activityBA,"addTaskPNLGrp");
RDebugUtils.currentLine=7340037;
 //BA.debugLineNum = 7340037;BA.debugLine="addPNL.SetLayout(10dip, 0, 240dip, 120dip)";
_addpnl.SetLayout(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=7340038;
 //BA.debugLineNum = 7340038;BA.debugLine="addPNL.Color = Colors.Transparent";
_addpnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=7340040;
 //BA.debugLineNum = 7340040;BA.debugLine="Dim addET As EditText";
_addet = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=7340041;
 //BA.debugLineNum = 7340041;BA.debugLine="addET.Initialize(\"addTaskETGrp\")";
_addet.Initialize(mostCurrent.activityBA,"addTaskETGrp");
RDebugUtils.currentLine=7340042;
 //BA.debugLineNum = 7340042;BA.debugLine="addET.Text = oldTask";
_addet.setText(BA.ObjectToCharSequence(_oldtask));
RDebugUtils.currentLine=7340044;
 //BA.debugLineNum = 7340044;BA.debugLine="Dim confirmBtn As Button";
_confirmbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=7340045;
 //BA.debugLineNum = 7340045;BA.debugLine="confirmBtn.Initialize(\"enterTaskBtnGrp\")";
_confirmbtn.Initialize(mostCurrent.activityBA,"enterTaskBtnGrp");
RDebugUtils.currentLine=7340046;
 //BA.debugLineNum = 7340046;BA.debugLine="confirmBtn.Text = \"Rename task\"";
_confirmbtn.setText(BA.ObjectToCharSequence("Rename task"));
RDebugUtils.currentLine=7340048;
 //BA.debugLineNum = 7340048;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=7340049;
 //BA.debugLineNum = 7340049;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (98),(int) (43),(int) (20)),(int) (200));
RDebugUtils.currentLine=7340050;
 //BA.debugLineNum = 7340050;BA.debugLine="confirmBtn.Background = cd";
_confirmbtn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=7340051;
 //BA.debugLineNum = 7340051;BA.debugLine="confirmBtn.TextColor = Colors.White";
_confirmbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=7340053;
 //BA.debugLineNum = 7340053;BA.debugLine="Dim ctx As List";
_ctx = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=7340054;
 //BA.debugLineNum = 7340054;BA.debugLine="ctx.Initialize";
_ctx.Initialize();
RDebugUtils.currentLine=7340055;
 //BA.debugLineNum = 7340055;BA.debugLine="ctx.Add(code)";
_ctx.Add((Object)(_code));
RDebugUtils.currentLine=7340056;
 //BA.debugLineNum = 7340056;BA.debugLine="ctx.Add(currentGrpList)";
_ctx.Add((Object)(_currentgrplist));
RDebugUtils.currentLine=7340057;
 //BA.debugLineNum = 7340057;BA.debugLine="ctx.Add(addET)";
_ctx.Add((Object)(_addet.getObject()));
RDebugUtils.currentLine=7340058;
 //BA.debugLineNum = 7340058;BA.debugLine="ctx.Add(oldTask)    ' index 3 is not Null → renam";
_ctx.Add((Object)(_oldtask));
RDebugUtils.currentLine=7340059;
 //BA.debugLineNum = 7340059;BA.debugLine="confirmBtn.Tag = ctx";
_confirmbtn.setTag((Object)(_ctx.getObject()));
RDebugUtils.currentLine=7340061;
 //BA.debugLineNum = 7340061;BA.debugLine="addPNL.AddView(addET, 0, 0, 190dip, 60dip)";
_addpnl.AddView((android.view.View)(_addet.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=7340062;
 //BA.debugLineNum = 7340062;BA.debugLine="addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40di";
_addpnl.AddView((android.view.View)(_confirmbtn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=7340064;
 //BA.debugLineNum = 7340064;BA.debugLine="tasksListGrp.Add(addPNL, addPNL)";
mostCurrent._taskslistgrp._add((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_addpnl.getObject())),(Object)(_addpnl.getObject()));
RDebugUtils.currentLine=7340065;
 //BA.debugLineNum = 7340065;BA.debugLine="End Sub";
return "";
}
public static String  _taskcheckbox_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="todoactivity";
if (Debug.shouldDelegate(mostCurrent.activityBA, "taskcheckbox_checkedchange", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "taskcheckbox_checkedchange", new Object[] {_checked}));}
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _taskcheckbox = null;
anywheresoftware.b4a.objects.LabelWrapper _tasklbl = null;
String _key = "";
RDebugUtils.currentLine=5701632;
 //BA.debugLineNum = 5701632;BA.debugLine="Sub taskCheckbox_CheckedChange(Checked As Boolean)";
RDebugUtils.currentLine=5701634;
 //BA.debugLineNum = 5701634;BA.debugLine="Dim taskCheckbox As CheckBox = Sender";
_taskcheckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
_taskcheckbox = (anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper(), (android.widget.CheckBox)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=5701635;
 //BA.debugLineNum = 5701635;BA.debugLine="Dim taskLBL As Label = taskCheckbox.Tag";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
_tasklbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_taskcheckbox.getTag()));
RDebugUtils.currentLine=5701636;
 //BA.debugLineNum = 5701636;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=5701637;
 //BA.debugLineNum = 5701637;BA.debugLine="If Checked Then";
if (_checked) { 
RDebugUtils.currentLine=5701638;
 //BA.debugLineNum = 5701638;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128,";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (128),(int) (128),(int) (128)));
 }else {
RDebugUtils.currentLine=5701640;
 //BA.debugLineNum = 5701640;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 }else {
RDebugUtils.currentLine=5701643;
 //BA.debugLineNum = 5701643;BA.debugLine="If Checked Then";
if (_checked) { 
RDebugUtils.currentLine=5701644;
 //BA.debugLineNum = 5701644;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128,";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (128),(int) (128),(int) (128)));
 }else {
RDebugUtils.currentLine=5701646;
 //BA.debugLineNum = 5701646;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 };
RDebugUtils.currentLine=5701651;
 //BA.debugLineNum = 5701651;BA.debugLine="Dim key As String = \"checked_\" & currentList & \"_";
_key = "checked_"+mostCurrent._currentlist+"_"+_tasklbl.getText();
RDebugUtils.currentLine=5701652;
 //BA.debugLineNum = 5701652;BA.debugLine="kvs.Put(key, Checked)";
_kvs._put(_key,(Object)(_checked));
RDebugUtils.currentLine=5701654;
 //BA.debugLineNum = 5701654;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=5701656;
 //BA.debugLineNum = 5701656;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7471104;
 //BA.debugLineNum = 7471104;BA.debugLine="Sub taskCheckboxGrp_CheckedChange(Checked As Boole";
RDebugUtils.currentLine=7471105;
 //BA.debugLineNum = 7471105;BA.debugLine="Dim cb As CheckBox = Sender";
_cb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
_cb = (anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper(), (android.widget.CheckBox)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=7471106;
 //BA.debugLineNum = 7471106;BA.debugLine="Dim cbCtx As List = cb.Tag";
_cbctx = new anywheresoftware.b4a.objects.collections.List();
_cbctx = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_cb.getTag()));
RDebugUtils.currentLine=7471107;
 //BA.debugLineNum = 7471107;BA.debugLine="Dim code As String = cbCtx.Get(0)";
_code = BA.ObjectToString(_cbctx.Get((int) (0)));
RDebugUtils.currentLine=7471108;
 //BA.debugLineNum = 7471108;BA.debugLine="Dim currentGrpList As String = cbCtx.Get(1)";
_currentgrplist = BA.ObjectToString(_cbctx.Get((int) (1)));
RDebugUtils.currentLine=7471109;
 //BA.debugLineNum = 7471109;BA.debugLine="Dim taskLBL As Label = cbCtx.Get(2)";
_tasklbl = new anywheresoftware.b4a.objects.LabelWrapper();
_tasklbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_cbctx.Get((int) (2))));
RDebugUtils.currentLine=7471111;
 //BA.debugLineNum = 7471111;BA.debugLine="If Checked Then";
if (_checked) { 
RDebugUtils.currentLine=7471112;
 //BA.debugLineNum = 7471112;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128, 1";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (128),(int) (128),(int) (128)));
 }else {
RDebugUtils.currentLine=7471114;
 //BA.debugLineNum = 7471114;BA.debugLine="If Starter.darkMode Then";
if (mostCurrent._starter._darkmode /*boolean*/ ) { 
RDebugUtils.currentLine=7471115;
 //BA.debugLineNum = 7471115;BA.debugLine="taskLBL.TextColor = Colors.White";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=7471117;
 //BA.debugLineNum = 7471117;BA.debugLine="taskLBL.TextColor = Colors.Black";
_tasklbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
 };
RDebugUtils.currentLine=7471121;
 //BA.debugLineNum = 7471121;BA.debugLine="Dim key As String = \"group_checked_\" & code & \"_\"";
_key = "group_checked_"+_code+"_"+_currentgrplist+"_"+_tasklbl.getText();
RDebugUtils.currentLine=7471122;
 //BA.debugLineNum = 7471122;BA.debugLine="kvs.Put(key, Checked)";
_kvs._put(_key,(Object)(_checked));
RDebugUtils.currentLine=7471123;
 //BA.debugLineNum = 7471123;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=7471124;
 //BA.debugLineNum = 7471124;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5570562;
 //BA.debugLineNum = 5570562;BA.debugLine="If Value = \"\" Then Return";
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
RDebugUtils.currentLine=5570564;
 //BA.debugLineNum = 5570564;BA.debugLine="Msgbox2Async(\"Delete or rename this task?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this task?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=5570565;
 //BA.debugLineNum = 5570565;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "taskslist_itemlongclick"), null);
this.state = 13;
return;
case 13:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=5570567;
 //BA.debugLineNum = 5570567;BA.debugLine="If res = DialogResponse.POSITIVE Then ' Rename";
if (true) break;

case 7:
//if
this.state = 12;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=5570570;
 //BA.debugLineNum = 5570570;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then ' Dele";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 11;
}}
if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=5570568;
 //BA.debugLineNum = 5570568;BA.debugLine="showRenameTaskPanel(Index, Value)";
_showrenametaskpanel(_index,BA.ObjectToString(_value));
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=5570571;
 //BA.debugLineNum = 5570571;BA.debugLine="Dim key As String = \"list_\" & currentList";
_key = "list_"+parent.mostCurrent._currentlist;
RDebugUtils.currentLine=5570572;
 //BA.debugLineNum = 5570572;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_key)));
RDebugUtils.currentLine=5570573;
 //BA.debugLineNum = 5570573;BA.debugLine="savedTasks.RemoveAt(Index)";
_savedtasks.RemoveAt(_index);
RDebugUtils.currentLine=5570574;
 //BA.debugLineNum = 5570574;BA.debugLine="kvs.Put(key, savedTasks)";
parent._kvs._put(_key,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=5570575;
 //BA.debugLineNum = 5570575;BA.debugLine="kvs.Remove(\"checked_\" & currentList & \"_\" & Valu";
parent._kvs._remove("checked_"+parent.mostCurrent._currentlist+"_"+BA.ObjectToString(_value));
RDebugUtils.currentLine=5570576;
 //BA.debugLineNum = 5570576;BA.debugLine="tasksList.RemoveAt(Index)";
parent.mostCurrent._taskslist._removeat(_index);
RDebugUtils.currentLine=5570577;
 //BA.debugLineNum = 5570577;BA.debugLine="updateProgress";
_updateprogress();
RDebugUtils.currentLine=5570578;
 //BA.debugLineNum = 5570578;BA.debugLine="ToastMessageShow(\"Task deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = -1;
;
RDebugUtils.currentLine=5570581;
 //BA.debugLineNum = 5570581;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7274497;
 //BA.debugLineNum = 7274497;BA.debugLine="If Value = \"\" Then Return";
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
RDebugUtils.currentLine=7274499;
 //BA.debugLineNum = 7274499;BA.debugLine="Dim code As String = groupET.Tag";
_code = BA.ObjectToString(parent.mostCurrent._groupet.getTag());
RDebugUtils.currentLine=7274501;
 //BA.debugLineNum = 7274501;BA.debugLine="Dim currentGrpList As String = getCurrentGrpList";
_currentgrplist = _getcurrentgrplist();
RDebugUtils.currentLine=7274503;
 //BA.debugLineNum = 7274503;BA.debugLine="Msgbox2Async(\"Delete or rename this task?\", Value";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Delete or rename this task?"),BA.ObjectToCharSequence(_value),"Rename","","Delete",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=7274504;
 //BA.debugLineNum = 7274504;BA.debugLine="Wait For Msgbox_Result (res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "todoactivity", "taskslistgrp_itemlongclick"), null);
this.state = 13;
return;
case 13:
//C
this.state = 7;
_res = (Integer) result[0];
;
RDebugUtils.currentLine=7274506;
 //BA.debugLineNum = 7274506;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}else 
{RDebugUtils.currentLine=7274510;
 //BA.debugLineNum = 7274510;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 11;
}}
if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=7274508;
 //BA.debugLineNum = 7274508;BA.debugLine="showRenameTaskPanelGrp(Index, Value, code, curre";
_showrenametaskpanelgrp(_index,BA.ObjectToString(_value),_code,_currentgrplist);
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=7274511;
 //BA.debugLineNum = 7274511;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"";
_taskkey = "group_list_"+_code+"_"+_currentgrplist;
RDebugUtils.currentLine=7274512;
 //BA.debugLineNum = 7274512;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
_savedtasks = new anywheresoftware.b4a.objects.collections.List();
_savedtasks = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(parent._kvs._get(_taskkey)));
RDebugUtils.currentLine=7274513;
 //BA.debugLineNum = 7274513;BA.debugLine="savedTasks.RemoveAt(Index)";
_savedtasks.RemoveAt(_index);
RDebugUtils.currentLine=7274514;
 //BA.debugLineNum = 7274514;BA.debugLine="kvs.Put(taskKey, savedTasks)";
parent._kvs._put(_taskkey,(Object)(_savedtasks.getObject()));
RDebugUtils.currentLine=7274515;
 //BA.debugLineNum = 7274515;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & curre";
parent._kvs._remove("group_checked_"+_code+"_"+_currentgrplist+"_"+BA.ObjectToString(_value));
RDebugUtils.currentLine=7274516;
 //BA.debugLineNum = 7274516;BA.debugLine="tasksListGrp.RemoveAt(Index)";
parent.mostCurrent._taskslistgrp._removeat(_index);
RDebugUtils.currentLine=7274517;
 //BA.debugLineNum = 7274517;BA.debugLine="updateProgressGrp(code, currentGrpList)";
_updateprogressgrp(_code,_currentgrplist);
RDebugUtils.currentLine=7274518;
 //BA.debugLineNum = 7274518;BA.debugLine="ToastMessageShow(\"Task deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Task deleted"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = -1;
;
RDebugUtils.currentLine=7274520;
 //BA.debugLineNum = 7274520;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
}