package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class confirmed_entry_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (confirmed_entry) ","confirmed_entry",4,confirmed_entry.mostCurrent.activityBA,confirmed_entry.mostCurrent,19);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.confirmed_entry.remoteMe.runUserSub(false, "confirmed_entry","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 19;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 21;BA.debugLine="Activity.LoadLayout(\"confirm_layout\")";
Debug.ShouldStop(1048576);
confirmed_entry.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("confirm_layout")),confirmed_entry.mostCurrent.activityBA);
 BA.debugLineNum = 23;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("Activity_Pause (confirmed_entry) ","confirmed_entry",4,confirmed_entry.mostCurrent.activityBA,confirmed_entry.mostCurrent,29);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.confirmed_entry.remoteMe.runUserSub(false, "confirmed_entry","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 29;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (confirmed_entry) ","confirmed_entry",4,confirmed_entry.mostCurrent.activityBA,confirmed_entry.mostCurrent,25);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.confirmed_entry.remoteMe.runUserSub(false, "confirmed_entry","activity_resume");}
 BA.debugLineNum = 25;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(16777216);
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
public static RemoteObject  _btnshowusers_click() throws Exception{
try {
		Debug.PushSubsStack("btnshowusers_Click (confirmed_entry) ","confirmed_entry",4,confirmed_entry.mostCurrent.activityBA,confirmed_entry.mostCurrent,34);
if (RapidSub.canDelegate("btnshowusers_click")) { return b4a.example.confirmed_entry.remoteMe.runUserSub(false, "confirmed_entry","btnshowusers_click");}
RemoteObject _cursor1 = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
RemoteObject _sb = RemoteObject.declareNull("anywheresoftware.b4a.keywords.StringBuilderWrapper");
int _i = 0;
RemoteObject _id = RemoteObject.createImmutable(0);
RemoteObject _email = RemoteObject.createImmutable("");
RemoteObject _name = RemoteObject.createImmutable("");
 BA.debugLineNum = 34;BA.debugLine="Private Sub btnshowusers_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 35;BA.debugLine="Dim Cursor1 As Cursor";
Debug.ShouldStop(4);
_cursor1 = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("Cursor1", _cursor1);
 BA.debugLineNum = 36;BA.debugLine="Cursor1 = Starter.SQL1.ExecQuery(\"SELECT id, emai";
Debug.ShouldStop(8);
_cursor1 = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), confirmed_entry.mostCurrent._starter._sql1 /*RemoteObject*/ .runMethod(false,"ExecQuery",(Object)(RemoteObject.createImmutable("SELECT id, email, name FROM users"))));Debug.locals.put("Cursor1", _cursor1);
 BA.debugLineNum = 38;BA.debugLine="If Cursor1.RowCount = 0 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",_cursor1.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 39;BA.debugLine="MsgboxAsync(\"No users found in database\", \"Users";
Debug.ShouldStop(64);
confirmed_entry.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("No users found in database")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Users"))),confirmed_entry.processBA);
 }else {
 BA.debugLineNum = 41;BA.debugLine="Dim sb As StringBuilder";
Debug.ShouldStop(256);
_sb = RemoteObject.createNew ("anywheresoftware.b4a.keywords.StringBuilderWrapper");Debug.locals.put("sb", _sb);
 BA.debugLineNum = 42;BA.debugLine="sb.Initialize";
Debug.ShouldStop(512);
_sb.runVoidMethod ("Initialize");
 BA.debugLineNum = 43;BA.debugLine="sb.Append(\"ID | Email | Name\").Append(CRLF)";
Debug.ShouldStop(1024);
_sb.runMethod(false,"Append",(Object)(RemoteObject.createImmutable("ID | Email | Name"))).runVoidMethod ("Append",(Object)(confirmed_entry.mostCurrent.__c.getField(true,"CRLF")));
 BA.debugLineNum = 44;BA.debugLine="sb.Append(\"---------------------------\").Append(";
Debug.ShouldStop(2048);
_sb.runMethod(false,"Append",(Object)(RemoteObject.createImmutable("---------------------------"))).runVoidMethod ("Append",(Object)(confirmed_entry.mostCurrent.__c.getField(true,"CRLF")));
 BA.debugLineNum = 46;BA.debugLine="For i = 0 To Cursor1.RowCount - 1";
Debug.ShouldStop(8192);
{
final int step10 = 1;
final int limit10 = RemoteObject.solve(new RemoteObject[] {_cursor1.runMethod(true,"getRowCount"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10) ;_i = ((int)(0 + _i + step10))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 47;BA.debugLine="Cursor1.Position = i";
Debug.ShouldStop(16384);
_cursor1.runMethod(true,"setPosition",BA.numberCast(int.class, _i));
 BA.debugLineNum = 48;BA.debugLine="Dim id As Int = Cursor1.GetInt(\"id\")";
Debug.ShouldStop(32768);
_id = _cursor1.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("id")));Debug.locals.put("id", _id);Debug.locals.put("id", _id);
 BA.debugLineNum = 49;BA.debugLine="Dim email As String = Cursor1.GetString(\"email\"";
Debug.ShouldStop(65536);
_email = _cursor1.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("email")));Debug.locals.put("email", _email);Debug.locals.put("email", _email);
 BA.debugLineNum = 50;BA.debugLine="Dim name As String = Cursor1.GetString(\"name\")";
Debug.ShouldStop(131072);
_name = _cursor1.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("name")));Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 52;BA.debugLine="sb.Append(id).Append(\" | \").Append(email).Appen";
Debug.ShouldStop(524288);
_sb.runMethod(false,"Append",(Object)(BA.NumberToString(_id))).runMethod(false,"Append",(Object)(RemoteObject.createImmutable(" | "))).runMethod(false,"Append",(Object)(_email)).runMethod(false,"Append",(Object)(RemoteObject.createImmutable(" | "))).runMethod(false,"Append",(Object)(_name)).runVoidMethod ("Append",(Object)(confirmed_entry.mostCurrent.__c.getField(true,"CRLF")));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 56;BA.debugLine="MsgboxAsync(sb.ToString, \"Users in Database\")";
Debug.ShouldStop(8388608);
confirmed_entry.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(_sb.runMethod(true,"ToString"))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Users in Database"))),confirmed_entry.processBA);
 };
 BA.debugLineNum = 59;BA.debugLine="Cursor1.Close";
Debug.ShouldStop(67108864);
_cursor1.runVoidMethod ("Close");
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private btnshowusers As Button";
confirmed_entry.mostCurrent._btnshowusers = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}