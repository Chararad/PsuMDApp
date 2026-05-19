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

public class register extends Activity implements B4AActivity{
	public static register mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.register");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (register).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.register");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.register", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (register) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (register) Resume **");
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
		return register.class;
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
            BA.LogInfo("** Activity (register) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (register) Pause event (activity is not paused). **");
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
            register mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (register) Resume **");
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
public anywheresoftware.b4a.objects.EditTextWrapper _txtemail = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpassword_regis = null;
public static boolean _ispasswordhidden = false;
public anywheresoftware.b4a.objects.ButtonWrapper _btneyes_regis = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnconfirm_pass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtconfirm_password = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
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
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=8126464;
 //BA.debugLineNum = 8126464;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=8126466;
 //BA.debugLineNum = 8126466;BA.debugLine="Activity.LoadLayout(\"Register_layout\")";
mostCurrent._activity.LoadLayout("Register_layout",mostCurrent.activityBA);
RDebugUtils.currentLine=8126468;
 //BA.debugLineNum = 8126468;BA.debugLine="txtpassword_regis.PasswordMode = True";
mostCurrent._txtpassword_regis.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=8126469;
 //BA.debugLineNum = 8126469;BA.debugLine="btneyes_regis.SetBackgroundImage(LoadBitmap(File.";
mostCurrent._btneyes_regis.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"close-eye.png").getObject()));
RDebugUtils.currentLine=8126470;
 //BA.debugLineNum = 8126470;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="register";
RDebugUtils.currentLine=8257536;
 //BA.debugLineNum = 8257536;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=8257538;
 //BA.debugLineNum = 8257538;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=8192000;
 //BA.debugLineNum = 8192000;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=8192002;
 //BA.debugLineNum = 8192002;BA.debugLine="End Sub";
return "";
}
public static String  _btnback_click() throws Exception{
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnback_click", null));}
RDebugUtils.currentLine=8519680;
 //BA.debugLineNum = 8519680;BA.debugLine="Private Sub btnback_Click";
RDebugUtils.currentLine=8519681;
 //BA.debugLineNum = 8519681;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=8519682;
 //BA.debugLineNum = 8519682;BA.debugLine="End Sub";
return "";
}
public static String  _btnconfirm_pass_click() throws Exception{
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnconfirm_pass_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnconfirm_pass_click", null));}
RDebugUtils.currentLine=8585216;
 //BA.debugLineNum = 8585216;BA.debugLine="Private Sub btnconfirm_pass_Click";
RDebugUtils.currentLine=8585217;
 //BA.debugLineNum = 8585217;BA.debugLine="btnconfirm_pass.Height = 30dip";
mostCurrent._btnconfirm_pass.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=8585218;
 //BA.debugLineNum = 8585218;BA.debugLine="btnconfirm_pass.Width = 30dip";
mostCurrent._btnconfirm_pass.setWidth(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=8585220;
 //BA.debugLineNum = 8585220;BA.debugLine="If isPasswordHidden Then";
if (_ispasswordhidden) { 
RDebugUtils.currentLine=8585221;
 //BA.debugLineNum = 8585221;BA.debugLine="txtconfirm_password.PasswordMode = False";
mostCurrent._txtconfirm_password.setPasswordMode(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=8585222;
 //BA.debugLineNum = 8585222;BA.debugLine="btnconfirm_pass.SetBackgroundImage(LoadBitmap(Fi";
mostCurrent._btnconfirm_pass.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"open-eye.png").getObject()));
RDebugUtils.currentLine=8585223;
 //BA.debugLineNum = 8585223;BA.debugLine="isPasswordHidden = False";
_ispasswordhidden = anywheresoftware.b4a.keywords.Common.False;
 }else {
RDebugUtils.currentLine=8585226;
 //BA.debugLineNum = 8585226;BA.debugLine="txtconfirm_password.PasswordMode = True";
mostCurrent._txtconfirm_password.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=8585227;
 //BA.debugLineNum = 8585227;BA.debugLine="btnconfirm_pass.SetBackgroundImage(LoadBitmap(Fi";
mostCurrent._btnconfirm_pass.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"close-eye.png").getObject()));
RDebugUtils.currentLine=8585228;
 //BA.debugLineNum = 8585228;BA.debugLine="isPasswordHidden = True";
_ispasswordhidden = anywheresoftware.b4a.keywords.Common.True;
 };
RDebugUtils.currentLine=8585231;
 //BA.debugLineNum = 8585231;BA.debugLine="End Sub";
return "";
}
public static String  _btneyes_regis_click() throws Exception{
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btneyes_regis_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btneyes_regis_click", null));}
RDebugUtils.currentLine=8454144;
 //BA.debugLineNum = 8454144;BA.debugLine="Private Sub btneyes_regis_Click";
RDebugUtils.currentLine=8454145;
 //BA.debugLineNum = 8454145;BA.debugLine="btneyes_regis.Height = 30dip";
mostCurrent._btneyes_regis.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=8454146;
 //BA.debugLineNum = 8454146;BA.debugLine="btneyes_regis.Width = 30dip";
mostCurrent._btneyes_regis.setWidth(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=8454148;
 //BA.debugLineNum = 8454148;BA.debugLine="If isPasswordHidden Then";
if (_ispasswordhidden) { 
RDebugUtils.currentLine=8454149;
 //BA.debugLineNum = 8454149;BA.debugLine="txtpassword_regis.PasswordMode = False";
mostCurrent._txtpassword_regis.setPasswordMode(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=8454150;
 //BA.debugLineNum = 8454150;BA.debugLine="btneyes_regis.SetBackgroundImage(LoadBitmap(File";
mostCurrent._btneyes_regis.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"open-eye.png").getObject()));
RDebugUtils.currentLine=8454151;
 //BA.debugLineNum = 8454151;BA.debugLine="isPasswordHidden = False";
_ispasswordhidden = anywheresoftware.b4a.keywords.Common.False;
 }else {
RDebugUtils.currentLine=8454154;
 //BA.debugLineNum = 8454154;BA.debugLine="txtpassword_regis.PasswordMode = True";
mostCurrent._txtpassword_regis.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=8454155;
 //BA.debugLineNum = 8454155;BA.debugLine="btneyes_regis.SetBackgroundImage(LoadBitmap(File";
mostCurrent._btneyes_regis.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"close-eye.png").getObject()));
RDebugUtils.currentLine=8454156;
 //BA.debugLineNum = 8454156;BA.debugLine="isPasswordHidden = True";
_ispasswordhidden = anywheresoftware.b4a.keywords.Common.True;
 };
RDebugUtils.currentLine=8454158;
 //BA.debugLineNum = 8454158;BA.debugLine="End Sub";
return "";
}
public static String  _btnlogin_click() throws Exception{
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnlogin_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnlogin_click", null));}
RDebugUtils.currentLine=8388608;
 //BA.debugLineNum = 8388608;BA.debugLine="Private Sub btnlogin_Click";
RDebugUtils.currentLine=8388609;
 //BA.debugLineNum = 8388609;BA.debugLine="StartActivity(LogIn)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._login.getObject()));
RDebugUtils.currentLine=8388610;
 //BA.debugLineNum = 8388610;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=8388611;
 //BA.debugLineNum = 8388611;BA.debugLine="End Sub";
return "";
}
public static String  _btnregister_click() throws Exception{
RDebugUtils.currentModule="register";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnregister_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnregister_click", null));}
String _email = "";
String _password = "";
String _name = "";
String _confirm = "";
boolean _skiptutorial = false;
RDebugUtils.currentLine=8323072;
 //BA.debugLineNum = 8323072;BA.debugLine="Private Sub btnregister_Click";
RDebugUtils.currentLine=8323073;
 //BA.debugLineNum = 8323073;BA.debugLine="Dim email As String = txtemail.Text.Trim";
_email = mostCurrent._txtemail.getText().trim();
RDebugUtils.currentLine=8323074;
 //BA.debugLineNum = 8323074;BA.debugLine="Dim password As String = txtpassword_regis.Text.T";
_password = mostCurrent._txtpassword_regis.getText().trim();
RDebugUtils.currentLine=8323075;
 //BA.debugLineNum = 8323075;BA.debugLine="Dim name As String = txtname.Text.Trim";
_name = mostCurrent._txtname.getText().trim();
RDebugUtils.currentLine=8323076;
 //BA.debugLineNum = 8323076;BA.debugLine="Dim confirm As String = txtconfirm_password.Text.";
_confirm = mostCurrent._txtconfirm_password.getText().trim();
RDebugUtils.currentLine=8323078;
 //BA.debugLineNum = 8323078;BA.debugLine="If email = \"\" Or password = \"\" Or name = \"\" Or co";
if ((_email).equals("") || (_password).equals("") || (_name).equals("") || (_confirm).equals("")) { 
RDebugUtils.currentLine=8323079;
 //BA.debugLineNum = 8323079;BA.debugLine="ToastMessageShow(\"Please enter all fields\", Fals";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Please enter all fields"),anywheresoftware.b4a.keywords.Common.False);
 }else 
{RDebugUtils.currentLine=8323081;
 //BA.debugLineNum = 8323081;BA.debugLine="Else If email.EndsWith(\"@gmail.com\") = False Then";
if (_email.endsWith("@gmail.com")==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=8323082;
 //BA.debugLineNum = 8323082;BA.debugLine="ToastMessageShow(\"Email must be a Gmail address\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Email must be a Gmail address"),anywheresoftware.b4a.keywords.Common.False);
 }else 
{RDebugUtils.currentLine=8323084;
 //BA.debugLineNum = 8323084;BA.debugLine="Else If password.Length < 8 Then";
if (_password.length()<8) { 
RDebugUtils.currentLine=8323085;
 //BA.debugLineNum = 8323085;BA.debugLine="ToastMessageShow(\"Password must be at least 8 ch";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Password must be at least 8 characters"),anywheresoftware.b4a.keywords.Common.False);
 }else 
{RDebugUtils.currentLine=8323087;
 //BA.debugLineNum = 8323087;BA.debugLine="Else If password <> confirm Then";
if ((_password).equals(_confirm) == false) { 
RDebugUtils.currentLine=8323088;
 //BA.debugLineNum = 8323088;BA.debugLine="ToastMessageShow(\"Passwords do not match\", False";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Passwords do not match"),anywheresoftware.b4a.keywords.Common.False);
 }else {
RDebugUtils.currentLine=8323091;
 //BA.debugLineNum = 8323091;BA.debugLine="Try";
try {RDebugUtils.currentLine=8323092;
 //BA.debugLineNum = 8323092;BA.debugLine="Starter.SQL1.ExecNonQuery2(\"INSERT INTO users (";
mostCurrent._starter._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO users (email, name, password) VALUES (?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_email),(Object)(_name),(Object)(_password)}));
RDebugUtils.currentLine=8323094;
 //BA.debugLineNum = 8323094;BA.debugLine="ToastMessageShow(\"Registration successful\", Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Registration successful"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=8323096;
 //BA.debugLineNum = 8323096;BA.debugLine="txtemail.Text = \"\"";
mostCurrent._txtemail.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=8323097;
 //BA.debugLineNum = 8323097;BA.debugLine="txtname.Text = \"\"";
mostCurrent._txtname.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=8323098;
 //BA.debugLineNum = 8323098;BA.debugLine="txtpassword_regis.Text = \"\"";
mostCurrent._txtpassword_regis.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=8323099;
 //BA.debugLineNum = 8323099;BA.debugLine="txtconfirm_password.Text = \"\"";
mostCurrent._txtconfirm_password.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=8323101;
 //BA.debugLineNum = 8323101;BA.debugLine="Dim skipTutorial As Boolean = False";
_skiptutorial = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=8323102;
 //BA.debugLineNum = 8323102;BA.debugLine="If Starter.prefKvs.ContainsKey(\"skipTutorial\")";
if (mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ._containskey("skipTutorial")) { 
RDebugUtils.currentLine=8323103;
 //BA.debugLineNum = 8323103;BA.debugLine="skipTutorial = Starter.prefKvs.Get(\"skipTutori";
_skiptutorial = BA.ObjectToBoolean(mostCurrent._starter._prefkvs /*b4a.example3.keyvaluestore*/ ._get("skipTutorial"));
 };
RDebugUtils.currentLine=8323106;
 //BA.debugLineNum = 8323106;BA.debugLine="If skipTutorial Then";
if (_skiptutorial) { 
RDebugUtils.currentLine=8323107;
 //BA.debugLineNum = 8323107;BA.debugLine="StartActivity(MainActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._mainactivity.getObject()));
 }else {
RDebugUtils.currentLine=8323109;
 //BA.debugLineNum = 8323109;BA.debugLine="StartActivity(tutorialActivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tutorialactivity.getObject()));
 };
 } 
       catch (Exception e31) {
			processBA.setLastException(e31);RDebugUtils.currentLine=8323112;
 //BA.debugLineNum = 8323112;BA.debugLine="ToastMessageShow(\"Registration failed: Account";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Registration failed: Account exist"),anywheresoftware.b4a.keywords.Common.False);
 };
 }}}}
;
RDebugUtils.currentLine=8323116;
 //BA.debugLineNum = 8323116;BA.debugLine="End Sub";
return "";
}
}