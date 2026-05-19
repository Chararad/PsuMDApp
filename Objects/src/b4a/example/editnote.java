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

public class editnote extends Activity implements B4AActivity{
	public static editnote mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.editnote");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (editnote).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.editnote");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.editnote", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (editnote) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (editnote) Resume **");
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
		return editnote.class;
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
            BA.LogInfo("** Activity (editnote) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (editnote) Pause event (activity is not paused). **");
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
            editnote mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (editnote) Resume **");
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
public static b4a.example.main._mynote _activenote = null;
public anywheresoftware.b4a.objects.EditTextWrapper _contenttxt = null;
public anywheresoftware.b4a.objects.ButtonWrapper _savebtn = null;
public anywheresoftware.b4a.objects.EditTextWrapper _tagstxt = null;
public anywheresoftware.b4a.objects.EditTextWrapper _titletxt = null;
public static String _api1 = "";
public static String _api2 = "";
public static String _myapikey = "";
public anywheresoftware.b4a.phone.Phone.ContentChooser _cc = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.card_module _card_module = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.day_module _day_module = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="editnote";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=21757952;
 //BA.debugLineNum = 21757952;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=21757953;
 //BA.debugLineNum = 21757953;BA.debugLine="Select Starter.themeNumber";
switch (BA.switchObjectToInt(mostCurrent._starter._themenumber /*int*/ ,(int) (0),(int) (1),(int) (2))) {
case 0: {
RDebugUtils.currentLine=21757955;
 //BA.debugLineNum = 21757955;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=21757956;
 //BA.debugLineNum = 21757956;BA.debugLine="Activity.LoadLayout(\"editnoteLayout\")";
mostCurrent._activity.LoadLayout("editnoteLayout",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=21757958;
 //BA.debugLineNum = 21757958;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark\")";
mostCurrent._activity.LoadLayout("editnoteLayoutDark",mostCurrent.activityBA);
 };
 break; }
case 1: {
RDebugUtils.currentLine=21757961;
 //BA.debugLineNum = 21757961;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=21757962;
 //BA.debugLineNum = 21757962;BA.debugLine="Activity.LoadLayout(\"editnoteLayout2\")";
mostCurrent._activity.LoadLayout("editnoteLayout2",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=21757964;
 //BA.debugLineNum = 21757964;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark2\")";
mostCurrent._activity.LoadLayout("editnoteLayoutDark2",mostCurrent.activityBA);
 };
 break; }
case 2: {
RDebugUtils.currentLine=21757967;
 //BA.debugLineNum = 21757967;BA.debugLine="If Starter.darkMode = False Then";
if (mostCurrent._starter._darkmode /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=21757968;
 //BA.debugLineNum = 21757968;BA.debugLine="Activity.LoadLayout(\"editnoteLayout3\")";
mostCurrent._activity.LoadLayout("editnoteLayout3",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=21757970;
 //BA.debugLineNum = 21757970;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark3\")";
mostCurrent._activity.LoadLayout("editnoteLayoutDark3",mostCurrent.activityBA);
 };
 break; }
}
;
RDebugUtils.currentLine=21757974;
 //BA.debugLineNum = 21757974;BA.debugLine="cc.Initialize(\"CC\")";
mostCurrent._cc.Initialize("CC");
RDebugUtils.currentLine=21757976;
 //BA.debugLineNum = 21757976;BA.debugLine="contentTxt.Background = Null";
mostCurrent._contenttxt.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=21757977;
 //BA.debugLineNum = 21757977;BA.debugLine="contentTxt.Gravity = Bit.Or(Gravity.TOP, Gravity.";
mostCurrent._contenttxt.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.TOP,anywheresoftware.b4a.keywords.Common.Gravity.LEFT));
RDebugUtils.currentLine=21757978;
 //BA.debugLineNum = 21757978;BA.debugLine="If ActiveNote.IsInitialized Then";
if (_activenote.IsInitialized /*boolean*/ ) { 
RDebugUtils.currentLine=21757979;
 //BA.debugLineNum = 21757979;BA.debugLine="titleTxt.Text = ActiveNote.Title";
mostCurrent._titletxt.setText(BA.ObjectToCharSequence(_activenote.Title /*String*/ ));
RDebugUtils.currentLine=21757980;
 //BA.debugLineNum = 21757980;BA.debugLine="tagsTxt.Text = ActiveNote.Tags";
mostCurrent._tagstxt.setText(BA.ObjectToCharSequence(_activenote.Tags /*String*/ ));
RDebugUtils.currentLine=21757981;
 //BA.debugLineNum = 21757981;BA.debugLine="contentTxt.Text = ActiveNote.Content";
mostCurrent._contenttxt.setText(BA.ObjectToCharSequence(_activenote.Content /*String*/ ));
 };
RDebugUtils.currentLine=21757983;
 //BA.debugLineNum = 21757983;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="editnote";
RDebugUtils.currentLine=21889024;
 //BA.debugLineNum = 21889024;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=21889025;
 //BA.debugLineNum = 21889025;BA.debugLine="If UserClosed Then ActiveNote.Initialize";
if (_userclosed) { 
_activenote.Initialize();};
RDebugUtils.currentLine=21889026;
 //BA.debugLineNum = 21889026;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="editnote";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=21823488;
 //BA.debugLineNum = 21823488;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=21823490;
 //BA.debugLineNum = 21823490;BA.debugLine="End Sub";
return "";
}
public static String  _ai_notes_click() throws Exception{
RDebugUtils.currentModule="editnote";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ai_notes_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ai_notes_click", null));}
RDebugUtils.currentLine=22020096;
 //BA.debugLineNum = 22020096;BA.debugLine="Private Sub AI_notes_Click";
RDebugUtils.currentLine=22020097;
 //BA.debugLineNum = 22020097;BA.debugLine="cc.Show(\"application/pdf\", \"Select PDF\")";
mostCurrent._cc.Show(processBA,"application/pdf","Select PDF");
RDebugUtils.currentLine=22020099;
 //BA.debugLineNum = 22020099;BA.debugLine="End Sub";
return "";
}
public static String  _cc_result(boolean _success,String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="editnote";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cc_result", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cc_result", new Object[] {_success,_dir,_filename}));}
RDebugUtils.currentLine=22085632;
 //BA.debugLineNum = 22085632;BA.debugLine="Sub CC_RESULT (Success As Boolean, dir As String,";
RDebugUtils.currentLine=22085633;
 //BA.debugLineNum = 22085633;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=22085634;
 //BA.debugLineNum = 22085634;BA.debugLine="Log(\"Selected: \" & dir & \" / \" & fileName)";
anywheresoftware.b4a.keywords.Common.LogImpl("222085634","Selected: "+_dir+" / "+_filename,0);
RDebugUtils.currentLine=22085635;
 //BA.debugLineNum = 22085635;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
anywheresoftware.b4a.keywords.Common.File.Copy(_dir,_filename,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
RDebugUtils.currentLine=22085636;
 //BA.debugLineNum = 22085636;BA.debugLine="Log(\"PDF saved\")";
anywheresoftware.b4a.keywords.Common.LogImpl("222085636","PDF saved",0);
RDebugUtils.currentLine=22085637;
 //BA.debugLineNum = 22085637;BA.debugLine="GenerateNotesFromPDF";
_generatenotesfrompdf();
 }else {
RDebugUtils.currentLine=22085639;
 //BA.debugLineNum = 22085639;BA.debugLine="Log(\"User Cancelled\")";
anywheresoftware.b4a.keywords.Common.LogImpl("222085639","User Cancelled",0);
 };
RDebugUtils.currentLine=22085641;
 //BA.debugLineNum = 22085641;BA.debugLine="End Sub";
return "";
}
public static String  _generatenotesfrompdf() throws Exception{
RDebugUtils.currentModule="editnote";
if (Debug.shouldDelegate(mostCurrent.activityBA, "generatenotesfrompdf", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "generatenotesfrompdf", null));}
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
byte[] _bytes = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
String _base64 = "";
String _prompt = "";
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.List _contents = null;
anywheresoftware.b4a.objects.collections.Map _item = null;
anywheresoftware.b4a.objects.collections.List _parts = null;
anywheresoftware.b4a.objects.collections.Map _filepart = null;
anywheresoftware.b4a.objects.collections.Map _inline = null;
anywheresoftware.b4a.objects.collections.Map _textpart = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
String _json = "";
String _url = "";
b4a.example.httpjob _job = null;
RDebugUtils.currentLine=22151168;
 //BA.debugLineNum = 22151168;BA.debugLine="Sub GenerateNotesFromPDF";
RDebugUtils.currentLine=22151171;
 //BA.debugLineNum = 22151171;BA.debugLine="Dim In As InputStream = File.OpenInput(File.DirIn";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
_in = anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"temp.pdf");
RDebugUtils.currentLine=22151172;
 //BA.debugLineNum = 22151172;BA.debugLine="Dim bytes() As Byte = Bit.InputStreamToBytes(In)";
_bytes = anywheresoftware.b4a.keywords.Common.Bit.InputStreamToBytes((java.io.InputStream)(_in.getObject()));
RDebugUtils.currentLine=22151173;
 //BA.debugLineNum = 22151173;BA.debugLine="In.Close";
_in.Close();
RDebugUtils.currentLine=22151175;
 //BA.debugLineNum = 22151175;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
RDebugUtils.currentLine=22151176;
 //BA.debugLineNum = 22151176;BA.debugLine="Dim base64 As String = su.EncodeBase64(bytes)";
_base64 = _su.EncodeBase64(_bytes);
RDebugUtils.currentLine=22151179;
 //BA.debugLineNum = 22151179;BA.debugLine="Dim prompt As String = _     \"Convert this PDF in";
_prompt = "Convert this PDF into clean study notes."+anywheresoftware.b4a.keywords.Common.CRLF+"RULES:"+anywheresoftware.b4a.keywords.Common.CRLF+"- Extract only important points"+anywheresoftware.b4a.keywords.Common.CRLF+"- Use bullet points and headings"+anywheresoftware.b4a.keywords.Common.CRLF+"- Keep it simple and study-ready"+anywheresoftware.b4a.keywords.Common.CRLF+"- Output ONLY plain text notes";
RDebugUtils.currentLine=22151188;
 //BA.debugLineNum = 22151188;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=22151189;
 //BA.debugLineNum = 22151189;BA.debugLine="root.Initialize";
_root.Initialize();
RDebugUtils.currentLine=22151191;
 //BA.debugLineNum = 22151191;BA.debugLine="Dim contents As List";
_contents = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=22151192;
 //BA.debugLineNum = 22151192;BA.debugLine="contents.Initialize";
_contents.Initialize();
RDebugUtils.currentLine=22151194;
 //BA.debugLineNum = 22151194;BA.debugLine="Dim item As Map";
_item = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=22151195;
 //BA.debugLineNum = 22151195;BA.debugLine="item.Initialize";
_item.Initialize();
RDebugUtils.currentLine=22151197;
 //BA.debugLineNum = 22151197;BA.debugLine="Dim parts As List";
_parts = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=22151198;
 //BA.debugLineNum = 22151198;BA.debugLine="parts.Initialize";
_parts.Initialize();
RDebugUtils.currentLine=22151201;
 //BA.debugLineNum = 22151201;BA.debugLine="Dim filePart As Map";
_filepart = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=22151202;
 //BA.debugLineNum = 22151202;BA.debugLine="filePart.Initialize";
_filepart.Initialize();
RDebugUtils.currentLine=22151204;
 //BA.debugLineNum = 22151204;BA.debugLine="Dim inline As Map";
_inline = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=22151205;
 //BA.debugLineNum = 22151205;BA.debugLine="inline.Initialize";
_inline.Initialize();
RDebugUtils.currentLine=22151206;
 //BA.debugLineNum = 22151206;BA.debugLine="inline.Put(\"mime_type\", \"application/pdf\")";
_inline.Put((Object)("mime_type"),(Object)("application/pdf"));
RDebugUtils.currentLine=22151207;
 //BA.debugLineNum = 22151207;BA.debugLine="inline.Put(\"data\", base64)";
_inline.Put((Object)("data"),(Object)(_base64));
RDebugUtils.currentLine=22151209;
 //BA.debugLineNum = 22151209;BA.debugLine="filePart.Put(\"inline_data\", inline)";
_filepart.Put((Object)("inline_data"),(Object)(_inline.getObject()));
RDebugUtils.currentLine=22151210;
 //BA.debugLineNum = 22151210;BA.debugLine="parts.Add(filePart)";
_parts.Add((Object)(_filepart.getObject()));
RDebugUtils.currentLine=22151213;
 //BA.debugLineNum = 22151213;BA.debugLine="Dim textPart As Map";
_textpart = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=22151214;
 //BA.debugLineNum = 22151214;BA.debugLine="textPart.Initialize";
_textpart.Initialize();
RDebugUtils.currentLine=22151215;
 //BA.debugLineNum = 22151215;BA.debugLine="textPart.Put(\"text\", prompt)";
_textpart.Put((Object)("text"),(Object)(_prompt));
RDebugUtils.currentLine=22151216;
 //BA.debugLineNum = 22151216;BA.debugLine="parts.Add(textPart)";
_parts.Add((Object)(_textpart.getObject()));
RDebugUtils.currentLine=22151218;
 //BA.debugLineNum = 22151218;BA.debugLine="item.Put(\"parts\", parts)";
_item.Put((Object)("parts"),(Object)(_parts.getObject()));
RDebugUtils.currentLine=22151219;
 //BA.debugLineNum = 22151219;BA.debugLine="contents.Add(item)";
_contents.Add((Object)(_item.getObject()));
RDebugUtils.currentLine=22151221;
 //BA.debugLineNum = 22151221;BA.debugLine="root.Put(\"contents\", contents)";
_root.Put((Object)("contents"),(Object)(_contents.getObject()));
RDebugUtils.currentLine=22151223;
 //BA.debugLineNum = 22151223;BA.debugLine="Dim gen As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=22151224;
 //BA.debugLineNum = 22151224;BA.debugLine="gen.Initialize(root)";
_gen.Initialize(_root);
RDebugUtils.currentLine=22151226;
 //BA.debugLineNum = 22151226;BA.debugLine="Dim json As String = gen.ToString";
_json = _gen.ToString();
RDebugUtils.currentLine=22151228;
 //BA.debugLineNum = 22151228;BA.debugLine="Dim url As String = \"https://generativelanguage.g";
_url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+mostCurrent._myapikey;
RDebugUtils.currentLine=22151230;
 //BA.debugLineNum = 22151230;BA.debugLine="Dim job As HttpJob";
_job = new b4a.example.httpjob();
RDebugUtils.currentLine=22151231;
 //BA.debugLineNum = 22151231;BA.debugLine="job.Initialize(\"GeminiNotes\", Me)";
_job._initialize /*String*/ (null,processBA,"GeminiNotes",editnote.getObject());
RDebugUtils.currentLine=22151233;
 //BA.debugLineNum = 22151233;BA.debugLine="job.PostString(url, json)";
_job._poststring /*String*/ (null,_url,_json);
RDebugUtils.currentLine=22151234;
 //BA.debugLineNum = 22151234;BA.debugLine="job.GetRequest.SetContentType(\"application/json\")";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null).SetContentType("application/json");
RDebugUtils.currentLine=22151236;
 //BA.debugLineNum = 22151236;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4a.example.httpjob _job) throws Exception{
RDebugUtils.currentModule="editnote";
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
String _notes = "";
RDebugUtils.currentLine=22216704;
 //BA.debugLineNum = 22216704;BA.debugLine="Sub JobDone (job As HttpJob)";
RDebugUtils.currentLine=22216705;
 //BA.debugLineNum = 22216705;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
RDebugUtils.currentLine=22216706;
 //BA.debugLineNum = 22216706;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
RDebugUtils.currentLine=22216708;
 //BA.debugLineNum = 22216708;BA.debugLine="If job.JobName = \"GeminiNotes\" Then";
if ((_job._jobname /*String*/ ).equals("GeminiNotes")) { 
RDebugUtils.currentLine=22216710;
 //BA.debugLineNum = 22216710;BA.debugLine="Dim response As String = job.GetString";
_response = _job._getstring /*String*/ (null);
RDebugUtils.currentLine=22216712;
 //BA.debugLineNum = 22216712;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=22216713;
 //BA.debugLineNum = 22216713;BA.debugLine="jp.Initialize(response)";
_jp.Initialize(_response);
RDebugUtils.currentLine=22216715;
 //BA.debugLineNum = 22216715;BA.debugLine="Dim root As Map = jp.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _jp.NextObject();
RDebugUtils.currentLine=22216716;
 //BA.debugLineNum = 22216716;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
_candidates = new anywheresoftware.b4a.objects.collections.List();
_candidates = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("candidates"))));
RDebugUtils.currentLine=22216717;
 //BA.debugLineNum = 22216717;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
_candidate = new anywheresoftware.b4a.objects.collections.Map();
_candidate = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidates.Get((int) (0))));
RDebugUtils.currentLine=22216718;
 //BA.debugLineNum = 22216718;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
_content = new anywheresoftware.b4a.objects.collections.Map();
_content = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_candidate.Get((Object)("content"))));
RDebugUtils.currentLine=22216719;
 //BA.debugLineNum = 22216719;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
_parts = new anywheresoftware.b4a.objects.collections.List();
_parts = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_content.Get((Object)("parts"))));
RDebugUtils.currentLine=22216720;
 //BA.debugLineNum = 22216720;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
_firstpart = new anywheresoftware.b4a.objects.collections.Map();
_firstpart = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_parts.Get((int) (0))));
RDebugUtils.currentLine=22216722;
 //BA.debugLineNum = 22216722;BA.debugLine="Dim notes As String = firstPart.Get(\"text\")";
_notes = BA.ObjectToString(_firstpart.Get((Object)("text")));
RDebugUtils.currentLine=22216724;
 //BA.debugLineNum = 22216724;BA.debugLine="Log(\"NOTES OUTPUT:\")";
anywheresoftware.b4a.keywords.Common.LogImpl("222216724","NOTES OUTPUT:",0);
RDebugUtils.currentLine=22216725;
 //BA.debugLineNum = 22216725;BA.debugLine="Log(notes)";
anywheresoftware.b4a.keywords.Common.LogImpl("222216725",_notes,0);
RDebugUtils.currentLine=22216728;
 //BA.debugLineNum = 22216728;BA.debugLine="If contentTxt.Text.Trim = \"\" Then";
if ((mostCurrent._contenttxt.getText().trim()).equals("")) { 
RDebugUtils.currentLine=22216729;
 //BA.debugLineNum = 22216729;BA.debugLine="contentTxt.Text = notes";
mostCurrent._contenttxt.setText(BA.ObjectToCharSequence(_notes));
 }else {
RDebugUtils.currentLine=22216731;
 //BA.debugLineNum = 22216731;BA.debugLine="contentTxt.Text = contentTxt.Text & CRLF & CRL";
mostCurrent._contenttxt.setText(BA.ObjectToCharSequence(mostCurrent._contenttxt.getText()+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+_notes));
 };
RDebugUtils.currentLine=22216735;
 //BA.debugLineNum = 22216735;BA.debugLine="File.WriteString(File.DirInternal, \"notes.txt\",";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"notes.txt",mostCurrent._contenttxt.getText());
 };
 }else {
RDebugUtils.currentLine=22216740;
 //BA.debugLineNum = 22216740;BA.debugLine="Log(\"ERROR: \" & job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("222216740","ERROR: "+_job._errormessage /*String*/ ,0);
 };
RDebugUtils.currentLine=22216743;
 //BA.debugLineNum = 22216743;BA.debugLine="job.Release";
_job._release /*String*/ (null);
RDebugUtils.currentLine=22216745;
 //BA.debugLineNum = 22216745;BA.debugLine="End Sub";
return "";
}
public static String  _savebtn_click() throws Exception{
RDebugUtils.currentModule="editnote";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savebtn_click", null));}
b4a.example.main._mynote _n = null;
RDebugUtils.currentLine=21954560;
 //BA.debugLineNum = 21954560;BA.debugLine="Sub saveBtn_Click";
RDebugUtils.currentLine=21954561;
 //BA.debugLineNum = 21954561;BA.debugLine="If titleTxt.Text.Trim = \"\" Then";
if ((mostCurrent._titletxt.getText().trim()).equals("")) { 
RDebugUtils.currentLine=21954562;
 //BA.debugLineNum = 21954562;BA.debugLine="MsgboxAsync(\"Please add a title\", \"\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please add a title"),BA.ObjectToCharSequence(""),processBA);
RDebugUtils.currentLine=21954563;
 //BA.debugLineNum = 21954563;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=21954566;
 //BA.debugLineNum = 21954566;BA.debugLine="Dim n As MyNote";
_n = new b4a.example.main._mynote();
RDebugUtils.currentLine=21954567;
 //BA.debugLineNum = 21954567;BA.debugLine="n.Initialize";
_n.Initialize();
RDebugUtils.currentLine=21954569;
 //BA.debugLineNum = 21954569;BA.debugLine="If ActiveNote.IsInitialized And ActiveNote.noteID";
if (_activenote.IsInitialized /*boolean*/  && _activenote.noteID /*long*/ !=0) { 
RDebugUtils.currentLine=21954570;
 //BA.debugLineNum = 21954570;BA.debugLine="n.noteID = ActiveNote.noteID";
_n.noteID /*long*/  = _activenote.noteID /*long*/ ;
 }else {
RDebugUtils.currentLine=21954572;
 //BA.debugLineNum = 21954572;BA.debugLine="n.noteID = (DateTime.Now * 1000) + Rnd(1, 1000)";
_n.noteID /*long*/  = (long) ((anywheresoftware.b4a.keywords.Common.DateTime.getNow()*1000)+anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (1000)));
 };
RDebugUtils.currentLine=21954576;
 //BA.debugLineNum = 21954576;BA.debugLine="n.Title = titleTxt.Text";
_n.Title /*String*/  = mostCurrent._titletxt.getText();
RDebugUtils.currentLine=21954577;
 //BA.debugLineNum = 21954577;BA.debugLine="n.Tags = tagsTxt.Text";
_n.Tags /*String*/  = mostCurrent._tagstxt.getText();
RDebugUtils.currentLine=21954578;
 //BA.debugLineNum = 21954578;BA.debugLine="n.Content = contentTxt.Text";
_n.Content /*String*/  = mostCurrent._contenttxt.getText();
RDebugUtils.currentLine=21954579;
 //BA.debugLineNum = 21954579;BA.debugLine="n.DateAdded = DateTime.Now";
_n.DateAdded /*long*/  = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
RDebugUtils.currentLine=21954581;
 //BA.debugLineNum = 21954581;BA.debugLine="MainActivity.kvs.Put(\"N_\" & n.noteID, n)";
mostCurrent._mainactivity._kvs /*b4a.example3.keyvaluestore*/ ._put("N_"+BA.NumberToString(_n.noteID /*long*/ ),(Object)(_n));
RDebugUtils.currentLine=21954583;
 //BA.debugLineNum = 21954583;BA.debugLine="ToastMessageShow(\"Note Saved\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Note Saved"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=21954584;
 //BA.debugLineNum = 21954584;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=21954585;
 //BA.debugLineNum = 21954585;BA.debugLine="End Sub";
return "";
}
}