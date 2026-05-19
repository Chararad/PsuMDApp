package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class register_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,25);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.register.remoteMe.runUserSub(false, "register","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 25;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout(\"Register_layout\")";
Debug.ShouldStop(67108864);
register.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Register_layout")),register.mostCurrent.activityBA);
 BA.debugLineNum = 29;BA.debugLine="txtpassword_regis.PasswordMode = True";
Debug.ShouldStop(268435456);
register.mostCurrent._txtpassword_regis.runVoidMethod ("setPasswordMode",register.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 30;BA.debugLine="btneyes_regis.SetBackgroundImage(LoadBitmap(File.";
Debug.ShouldStop(536870912);
register.mostCurrent._btneyes_regis.runVoidMethod ("SetBackgroundImageNew",(Object)((register.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(register.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("close-eye.png"))).getObject())));
 BA.debugLineNum = 31;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,37);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.register.remoteMe.runUserSub(false, "register","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 37;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16);
 BA.debugLineNum = 39;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,33);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.register.remoteMe.runUserSub(false, "register","activity_resume");}
 BA.debugLineNum = 33;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1);
 BA.debugLineNum = 35;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnback_click() throws Exception{
try {
		Debug.PushSubsStack("btnback_Click (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,109);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.register.remoteMe.runUserSub(false, "register","btnback_click");}
 BA.debugLineNum = 109;BA.debugLine="Private Sub btnback_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 110;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8192);
register.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 111;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnconfirm_pass_click() throws Exception{
try {
		Debug.PushSubsStack("btnconfirm_pass_Click (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,113);
if (RapidSub.canDelegate("btnconfirm_pass_click")) { return b4a.example.register.remoteMe.runUserSub(false, "register","btnconfirm_pass_click");}
 BA.debugLineNum = 113;BA.debugLine="Private Sub btnconfirm_pass_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 114;BA.debugLine="btnconfirm_pass.Height = 30dip";
Debug.ShouldStop(131072);
register.mostCurrent._btnconfirm_pass.runMethod(true,"setHeight",register.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 115;BA.debugLine="btnconfirm_pass.Width = 30dip";
Debug.ShouldStop(262144);
register.mostCurrent._btnconfirm_pass.runMethod(true,"setWidth",register.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 117;BA.debugLine="If isPasswordHidden Then";
Debug.ShouldStop(1048576);
if (register._ispasswordhidden.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 118;BA.debugLine="txtconfirm_password.PasswordMode = False";
Debug.ShouldStop(2097152);
register.mostCurrent._txtconfirm_password.runVoidMethod ("setPasswordMode",register.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 119;BA.debugLine="btnconfirm_pass.SetBackgroundImage(LoadBitmap(Fi";
Debug.ShouldStop(4194304);
register.mostCurrent._btnconfirm_pass.runVoidMethod ("SetBackgroundImageNew",(Object)((register.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(register.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("open-eye.png"))).getObject())));
 BA.debugLineNum = 120;BA.debugLine="isPasswordHidden = False";
Debug.ShouldStop(8388608);
register._ispasswordhidden = register.mostCurrent.__c.getField(true,"False");
 }else {
 BA.debugLineNum = 123;BA.debugLine="txtconfirm_password.PasswordMode = True";
Debug.ShouldStop(67108864);
register.mostCurrent._txtconfirm_password.runVoidMethod ("setPasswordMode",register.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 124;BA.debugLine="btnconfirm_pass.SetBackgroundImage(LoadBitmap(Fi";
Debug.ShouldStop(134217728);
register.mostCurrent._btnconfirm_pass.runVoidMethod ("SetBackgroundImageNew",(Object)((register.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(register.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("close-eye.png"))).getObject())));
 BA.debugLineNum = 125;BA.debugLine="isPasswordHidden = True";
Debug.ShouldStop(268435456);
register._ispasswordhidden = register.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 128;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btneyes_regis_click() throws Exception{
try {
		Debug.PushSubsStack("btneyes_regis_Click (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,93);
if (RapidSub.canDelegate("btneyes_regis_click")) { return b4a.example.register.remoteMe.runUserSub(false, "register","btneyes_regis_click");}
 BA.debugLineNum = 93;BA.debugLine="Private Sub btneyes_regis_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 94;BA.debugLine="btneyes_regis.Height = 30dip";
Debug.ShouldStop(536870912);
register.mostCurrent._btneyes_regis.runMethod(true,"setHeight",register.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 95;BA.debugLine="btneyes_regis.Width = 30dip";
Debug.ShouldStop(1073741824);
register.mostCurrent._btneyes_regis.runMethod(true,"setWidth",register.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 97;BA.debugLine="If isPasswordHidden Then";
Debug.ShouldStop(1);
if (register._ispasswordhidden.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 98;BA.debugLine="txtpassword_regis.PasswordMode = False";
Debug.ShouldStop(2);
register.mostCurrent._txtpassword_regis.runVoidMethod ("setPasswordMode",register.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 99;BA.debugLine="btneyes_regis.SetBackgroundImage(LoadBitmap(File";
Debug.ShouldStop(4);
register.mostCurrent._btneyes_regis.runVoidMethod ("SetBackgroundImageNew",(Object)((register.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(register.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("open-eye.png"))).getObject())));
 BA.debugLineNum = 100;BA.debugLine="isPasswordHidden = False";
Debug.ShouldStop(8);
register._ispasswordhidden = register.mostCurrent.__c.getField(true,"False");
 }else {
 BA.debugLineNum = 103;BA.debugLine="txtpassword_regis.PasswordMode = True";
Debug.ShouldStop(64);
register.mostCurrent._txtpassword_regis.runVoidMethod ("setPasswordMode",register.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 104;BA.debugLine="btneyes_regis.SetBackgroundImage(LoadBitmap(File";
Debug.ShouldStop(128);
register.mostCurrent._btneyes_regis.runVoidMethod ("SetBackgroundImageNew",(Object)((register.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(register.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("close-eye.png"))).getObject())));
 BA.debugLineNum = 105;BA.debugLine="isPasswordHidden = True";
Debug.ShouldStop(256);
register._ispasswordhidden = register.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 107;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnlogin_click() throws Exception{
try {
		Debug.PushSubsStack("btnlogin_Click (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,88);
if (RapidSub.canDelegate("btnlogin_click")) { return b4a.example.register.remoteMe.runUserSub(false, "register","btnlogin_click");}
 BA.debugLineNum = 88;BA.debugLine="Private Sub btnlogin_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 89;BA.debugLine="StartActivity(LogIn)";
Debug.ShouldStop(16777216);
register.mostCurrent.__c.runVoidMethod ("StartActivity",register.processBA,(Object)((register.mostCurrent._login.getObject())));
 BA.debugLineNum = 90;BA.debugLine="Activity.Finish";
Debug.ShouldStop(33554432);
register.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnregister_click() throws Exception{
try {
		Debug.PushSubsStack("btnregister_Click (register) ","register",10,register.mostCurrent.activityBA,register.mostCurrent,42);
if (RapidSub.canDelegate("btnregister_click")) { return b4a.example.register.remoteMe.runUserSub(false, "register","btnregister_click");}
RemoteObject _email = RemoteObject.createImmutable("");
RemoteObject _password = RemoteObject.createImmutable("");
RemoteObject _name = RemoteObject.createImmutable("");
RemoteObject _confirm = RemoteObject.createImmutable("");
RemoteObject _skiptutorial = RemoteObject.createImmutable(false);
 BA.debugLineNum = 42;BA.debugLine="Private Sub btnregister_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 43;BA.debugLine="Dim email As String = txtemail.Text.Trim";
Debug.ShouldStop(1024);
_email = register.mostCurrent._txtemail.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("email", _email);Debug.locals.put("email", _email);
 BA.debugLineNum = 44;BA.debugLine="Dim password As String = txtpassword_regis.Text.T";
Debug.ShouldStop(2048);
_password = register.mostCurrent._txtpassword_regis.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("password", _password);Debug.locals.put("password", _password);
 BA.debugLineNum = 45;BA.debugLine="Dim name As String = txtname.Text.Trim";
Debug.ShouldStop(4096);
_name = register.mostCurrent._txtname.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 46;BA.debugLine="Dim confirm As String = txtconfirm_password.Text.";
Debug.ShouldStop(8192);
_confirm = register.mostCurrent._txtconfirm_password.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("confirm", _confirm);Debug.locals.put("confirm", _confirm);
 BA.debugLineNum = 48;BA.debugLine="If email = \"\" Or password = \"\" Or name = \"\" Or co";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",_email,BA.ObjectToString("")) || RemoteObject.solveBoolean("=",_password,BA.ObjectToString("")) || RemoteObject.solveBoolean("=",_name,BA.ObjectToString("")) || RemoteObject.solveBoolean("=",_confirm,BA.ObjectToString(""))) { 
 BA.debugLineNum = 49;BA.debugLine="ToastMessageShow(\"Please enter all fields\", Fals";
Debug.ShouldStop(65536);
register.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Please enter all fields")),(Object)(register.mostCurrent.__c.getField(true,"False")));
 }else 
{ BA.debugLineNum = 51;BA.debugLine="Else If email.EndsWith(\"@gmail.com\") = False Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_email.runMethod(true,"endsWith",(Object)(RemoteObject.createImmutable("@gmail.com"))),register.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 52;BA.debugLine="ToastMessageShow(\"Email must be a Gmail address\"";
Debug.ShouldStop(524288);
register.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Email must be a Gmail address")),(Object)(register.mostCurrent.__c.getField(true,"False")));
 }else 
{ BA.debugLineNum = 54;BA.debugLine="Else If password.Length < 8 Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("<",_password.runMethod(true,"length"),BA.numberCast(double.class, 8))) { 
 BA.debugLineNum = 55;BA.debugLine="ToastMessageShow(\"Password must be at least 8 ch";
Debug.ShouldStop(4194304);
register.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Password must be at least 8 characters")),(Object)(register.mostCurrent.__c.getField(true,"False")));
 }else 
{ BA.debugLineNum = 57;BA.debugLine="Else If password <> confirm Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("!",_password,_confirm)) { 
 BA.debugLineNum = 58;BA.debugLine="ToastMessageShow(\"Passwords do not match\", False";
Debug.ShouldStop(33554432);
register.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Passwords do not match")),(Object)(register.mostCurrent.__c.getField(true,"False")));
 }else {
 BA.debugLineNum = 61;BA.debugLine="Try";
Debug.ShouldStop(268435456);
try { BA.debugLineNum = 62;BA.debugLine="Starter.SQL1.ExecNonQuery2(\"INSERT INTO users (";
Debug.ShouldStop(536870912);
register.mostCurrent._starter._sql1 /*RemoteObject*/ .runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO users (email, name, password) VALUES (?, ?, ?)")),(Object)(register.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {3},new Object[] {(_email),(_name),(_password)})))));
 BA.debugLineNum = 64;BA.debugLine="ToastMessageShow(\"Registration successful\", Fal";
Debug.ShouldStop(-2147483648);
register.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Registration successful")),(Object)(register.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 66;BA.debugLine="txtemail.Text = \"\"";
Debug.ShouldStop(2);
register.mostCurrent._txtemail.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 67;BA.debugLine="txtname.Text = \"\"";
Debug.ShouldStop(4);
register.mostCurrent._txtname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 68;BA.debugLine="txtpassword_regis.Text = \"\"";
Debug.ShouldStop(8);
register.mostCurrent._txtpassword_regis.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 69;BA.debugLine="txtconfirm_password.Text = \"\"";
Debug.ShouldStop(16);
register.mostCurrent._txtconfirm_password.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 71;BA.debugLine="Dim skipTutorial As Boolean = False";
Debug.ShouldStop(64);
_skiptutorial = register.mostCurrent.__c.getField(true,"False");Debug.locals.put("skipTutorial", _skiptutorial);Debug.locals.put("skipTutorial", _skiptutorial);
 BA.debugLineNum = 72;BA.debugLine="If Starter.prefKvs.ContainsKey(\"skipTutorial\")";
Debug.ShouldStop(128);
if (register.mostCurrent._starter._prefkvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("skipTutorial"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 73;BA.debugLine="skipTutorial = Starter.prefKvs.Get(\"skipTutori";
Debug.ShouldStop(256);
_skiptutorial = BA.ObjectToBoolean(register.mostCurrent._starter._prefkvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("skipTutorial"))));Debug.locals.put("skipTutorial", _skiptutorial);
 };
 BA.debugLineNum = 76;BA.debugLine="If skipTutorial Then";
Debug.ShouldStop(2048);
if (_skiptutorial.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 77;BA.debugLine="StartActivity(MainActivity)";
Debug.ShouldStop(4096);
register.mostCurrent.__c.runVoidMethod ("StartActivity",register.processBA,(Object)((register.mostCurrent._mainactivity.getObject())));
 }else {
 BA.debugLineNum = 79;BA.debugLine="StartActivity(tutorialActivity)";
Debug.ShouldStop(16384);
register.mostCurrent.__c.runVoidMethod ("StartActivity",register.processBA,(Object)((register.mostCurrent._tutorialactivity.getObject())));
 };
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e31) {
			BA.rdebugUtils.runVoidMethod("setLastException",register.processBA, e31.toString()); BA.debugLineNum = 82;BA.debugLine="ToastMessageShow(\"Registration failed: Account";
Debug.ShouldStop(131072);
register.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Registration failed: Account exist")),(Object)(register.mostCurrent.__c.getField(true,"False")));
 };
 }}}}
;
 BA.debugLineNum = 86;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private txtemail As EditText";
register.mostCurrent._txtemail = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private txtname As EditText";
register.mostCurrent._txtname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private txtpassword_regis As EditText";
register.mostCurrent._txtpassword_regis = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Dim isPasswordHidden As Boolean = True";
register._ispasswordhidden = register.mostCurrent.__c.getField(true,"True");
 //BA.debugLineNum = 20;BA.debugLine="Private btneyes_regis As Button";
register.mostCurrent._btneyes_regis = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private btnconfirm_pass As Button";
register.mostCurrent._btnconfirm_pass = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private txtconfirm_password As EditText";
register.mostCurrent._txtconfirm_password = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}