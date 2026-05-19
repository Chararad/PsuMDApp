package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class login_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,21);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.login.remoteMe.runUserSub(false, "login","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 21;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"Login_layout\")";
Debug.ShouldStop(4194304);
login.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Login_layout")),login.mostCurrent.activityBA);
 BA.debugLineNum = 25;BA.debugLine="txtpassword.PasswordMode = True";
Debug.ShouldStop(16777216);
login.mostCurrent._txtpassword.runVoidMethod ("setPasswordMode",login.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 26;BA.debugLine="btneyes.SetBackgroundImage(LoadBitmap(File.DirAss";
Debug.ShouldStop(33554432);
login.mostCurrent._btneyes.runVoidMethod ("SetBackgroundImageNew",(Object)((login.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(login.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("close-eye.png"))).getObject())));
 BA.debugLineNum = 27;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("Activity_Pause (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,33);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.login.remoteMe.runUserSub(false, "login","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 33;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,29);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.login.remoteMe.runUserSub(false, "login","activity_resume");}
 BA.debugLineNum = 29;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(268435456);
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
public static RemoteObject  _btnback_click() throws Exception{
try {
		Debug.PushSubsStack("btnback_Click (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,80);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.login.remoteMe.runUserSub(false, "login","btnback_click");}
 BA.debugLineNum = 80;BA.debugLine="Private Sub btnback_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 81;BA.debugLine="Activity.Finish";
Debug.ShouldStop(65536);
login.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 82;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btneyes_click() throws Exception{
try {
		Debug.PushSubsStack("btneyes_Click (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,64);
if (RapidSub.canDelegate("btneyes_click")) { return b4a.example.login.remoteMe.runUserSub(false, "login","btneyes_click");}
 BA.debugLineNum = 64;BA.debugLine="Private Sub btneyes_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 65;BA.debugLine="btneyes.Height = 30dip";
Debug.ShouldStop(1);
login.mostCurrent._btneyes.runMethod(true,"setHeight",login.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 66;BA.debugLine="btneyes.Width = 30dip";
Debug.ShouldStop(2);
login.mostCurrent._btneyes.runMethod(true,"setWidth",login.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 68;BA.debugLine="If isPasswordHidden Then";
Debug.ShouldStop(8);
if (login._ispasswordhidden.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 69;BA.debugLine="txtpassword.PasswordMode = False";
Debug.ShouldStop(16);
login.mostCurrent._txtpassword.runVoidMethod ("setPasswordMode",login.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 70;BA.debugLine="btneyes.SetBackgroundImage(LoadBitmap(File.DirAs";
Debug.ShouldStop(32);
login.mostCurrent._btneyes.runVoidMethod ("SetBackgroundImageNew",(Object)((login.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(login.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("open-eye.png"))).getObject())));
 BA.debugLineNum = 71;BA.debugLine="isPasswordHidden = False";
Debug.ShouldStop(64);
login._ispasswordhidden = login.mostCurrent.__c.getField(true,"False");
 }else {
 BA.debugLineNum = 74;BA.debugLine="txtpassword.PasswordMode = True";
Debug.ShouldStop(512);
login.mostCurrent._txtpassword.runVoidMethod ("setPasswordMode",login.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 75;BA.debugLine="btneyes.SetBackgroundImage(LoadBitmap(File.DirAs";
Debug.ShouldStop(1024);
login.mostCurrent._btneyes.runVoidMethod ("SetBackgroundImageNew",(Object)((login.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(login.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("close-eye.png"))).getObject())));
 BA.debugLineNum = 76;BA.debugLine="isPasswordHidden = True";
Debug.ShouldStop(2048);
login._ispasswordhidden = login.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 78;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
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
		Debug.PushSubsStack("btnlogin_Click (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,38);
if (RapidSub.canDelegate("btnlogin_click")) { return b4a.example.login.remoteMe.runUserSub(false, "login","btnlogin_click");}
RemoteObject _email = RemoteObject.createImmutable("");
RemoteObject _password = RemoteObject.createImmutable("");
RemoteObject _cursor1 = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
 BA.debugLineNum = 38;BA.debugLine="Private Sub btnlogin_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 39;BA.debugLine="Dim email As String = txtemail.Text.Trim";
Debug.ShouldStop(64);
_email = login.mostCurrent._txtemail.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("email", _email);Debug.locals.put("email", _email);
 BA.debugLineNum = 40;BA.debugLine="Dim password As String = txtpassword.Text.Trim";
Debug.ShouldStop(128);
_password = login.mostCurrent._txtpassword.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("password", _password);Debug.locals.put("password", _password);
 BA.debugLineNum = 41;BA.debugLine="If email = \"\" Or password = \"\" Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",_email,BA.ObjectToString("")) || RemoteObject.solveBoolean("=",_password,BA.ObjectToString(""))) { 
 BA.debugLineNum = 42;BA.debugLine="ToastMessageShow(\"Please enter both email and pa";
Debug.ShouldStop(512);
login.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Please enter both email and password")),(Object)(login.mostCurrent.__c.getField(true,"False")));
 }else {
 BA.debugLineNum = 44;BA.debugLine="Dim Cursor1 As Cursor";
Debug.ShouldStop(2048);
_cursor1 = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("Cursor1", _cursor1);
 BA.debugLineNum = 45;BA.debugLine="Cursor1 = Starter.SQL1.ExecQuery2(\"SELECT * FROM";
Debug.ShouldStop(4096);
_cursor1 = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), login.mostCurrent._starter._sql1 /*RemoteObject*/ .runMethod(false,"ExecQuery2",(Object)(BA.ObjectToString("SELECT * FROM users WHERE email=? AND password=?")),(Object)(RemoteObject.createNewArray("String",new int[] {2},new Object[] {_email,_password}))));Debug.locals.put("Cursor1", _cursor1);
 BA.debugLineNum = 48;BA.debugLine="If Cursor1.RowCount > 0 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(">",_cursor1.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 49;BA.debugLine="ToastMessageShow(\"Login successful\", False)";
Debug.ShouldStop(65536);
login.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Login successful")),(Object)(login.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 50;BA.debugLine="Cursor1.Close";
Debug.ShouldStop(131072);
_cursor1.runVoidMethod ("Close");
 BA.debugLineNum = 52;BA.debugLine="txtemail.Text = \"\"";
Debug.ShouldStop(524288);
login.mostCurrent._txtemail.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 53;BA.debugLine="txtpassword.Text = \"\"";
Debug.ShouldStop(1048576);
login.mostCurrent._txtpassword.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 54;BA.debugLine="StartActivity(MainActivity)";
Debug.ShouldStop(2097152);
login.mostCurrent.__c.runVoidMethod ("StartActivity",login.processBA,(Object)((login.mostCurrent._mainactivity.getObject())));
 }else {
 BA.debugLineNum = 56;BA.debugLine="ToastMessageShow(\"Invalid email or password\", F";
Debug.ShouldStop(8388608);
login.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Invalid email or password")),(Object)(login.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 57;BA.debugLine="Cursor1.Close";
Debug.ShouldStop(16777216);
_cursor1.runVoidMethod ("Close");
 };
 };
 BA.debugLineNum = 60;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnsignup_click() throws Exception{
try {
		Debug.PushSubsStack("btnSignUp_Click (login) ","login",9,login.mostCurrent.activityBA,login.mostCurrent,84);
if (RapidSub.canDelegate("btnsignup_click")) { return b4a.example.login.remoteMe.runUserSub(false, "login","btnsignup_click");}
 BA.debugLineNum = 84;BA.debugLine="Private Sub btnSignUp_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 85;BA.debugLine="StartActivity(Register)";
Debug.ShouldStop(1048576);
login.mostCurrent.__c.runVoidMethod ("StartActivity",login.processBA,(Object)((login.mostCurrent._register.getObject())));
 BA.debugLineNum = 86;BA.debugLine="Activity.Finish";
Debug.ShouldStop(2097152);
login.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 87;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
 //BA.debugLineNum = 15;BA.debugLine="Private txtemail As EditText";
login.mostCurrent._txtemail = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private txtpassword As EditText";
login.mostCurrent._txtpassword = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private btneyes As Button";
login.mostCurrent._btneyes = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Dim isPasswordHidden As Boolean = True";
login._ispasswordhidden = login.mostCurrent.__c.getField(true,"True");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}