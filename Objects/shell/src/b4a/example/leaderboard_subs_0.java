package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class leaderboard_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,45);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 45;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 46;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(8192);
switch (BA.switchObjectToInt(leaderboard.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 48;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",leaderboard.mostCurrent._starter._darkmode /*RemoteObject*/ ,leaderboard.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 49;BA.debugLine="Activity.LoadLayout(\"leaderboard\")";
Debug.ShouldStop(65536);
leaderboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("leaderboard")),leaderboard.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 51;BA.debugLine="Activity.LoadLayout(\"leaderboard\")";
Debug.ShouldStop(262144);
leaderboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("leaderboard")),leaderboard.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 54;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",leaderboard.mostCurrent._starter._darkmode /*RemoteObject*/ ,leaderboard.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"leaderboard2\")";
Debug.ShouldStop(4194304);
leaderboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("leaderboard2")),leaderboard.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 57;BA.debugLine="Activity.LoadLayout(\"leaderboarddark2\")";
Debug.ShouldStop(16777216);
leaderboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("leaderboarddark2")),leaderboard.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 60;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",leaderboard.mostCurrent._starter._darkmode /*RemoteObject*/ ,leaderboard.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 61;BA.debugLine="Activity.LoadLayout(\"leaderboard3\")";
Debug.ShouldStop(268435456);
leaderboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("leaderboard3")),leaderboard.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 63;BA.debugLine="Activity.LoadLayout(\"leaderboarddark3\")";
Debug.ShouldStop(1073741824);
leaderboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("leaderboarddark3")),leaderboard.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 67;BA.debugLine="DailyList.Initialize";
Debug.ShouldStop(4);
leaderboard.mostCurrent._dailylist.runVoidMethod ("Initialize");
 BA.debugLineNum = 68;BA.debugLine="WeeklyList.Initialize";
Debug.ShouldStop(8);
leaderboard.mostCurrent._weeklylist.runVoidMethod ("Initialize");
 BA.debugLineNum = 69;BA.debugLine="AllTimeList.Initialize";
Debug.ShouldStop(16);
leaderboard.mostCurrent._alltimelist.runVoidMethod ("Initialize");
 BA.debugLineNum = 70;BA.debugLine="InitDB";
Debug.ShouldStop(32);
_initdb();
 BA.debugLineNum = 71;BA.debugLine="EnsureSeedData";
Debug.ShouldStop(64);
_ensureseeddata();
 BA.debugLineNum = 72;BA.debugLine="SetupInsertControls";
Debug.ShouldStop(128);
_setupinsertcontrols();
 BA.debugLineNum = 73;BA.debugLine="ShowBoard(\"DAILY\")";
Debug.ShouldStop(256);
_showboard(RemoteObject.createImmutable("DAILY"));
 BA.debugLineNum = 74;BA.debugLine="End Sub";
Debug.ShouldStop(512);
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
		Debug.PushSubsStack("Activity_Pause (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,79);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 79;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16384);
 BA.debugLineNum = 80;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("Activity_Resume (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,76);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","activity_resume");}
 BA.debugLineNum = 76;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2048);
 BA.debugLineNum = 77;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _adduserscore(RemoteObject _mode,RemoteObject _n,RemoteObject _xp,RemoteObject _st,RemoteObject _cr) throws Exception{
try {
		Debug.PushSubsStack("AddUserScore (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,154);
if (RapidSub.canDelegate("adduserscore")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","adduserscore", _mode, _n, _xp, _st, _cr);}
Debug.locals.put("mode", _mode);
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 154;BA.debugLine="Public Sub AddUserScore(mode As String, n As Strin";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 155;BA.debugLine="InsertUserToDB(mode, n, xp, st, cr)";
Debug.ShouldStop(67108864);
_insertusertodb(_mode,_n,_xp,_st,_cr);
 BA.debugLineNum = 156;BA.debugLine="If mode = CurrentMode Then ShowBoard(mode)";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_mode,leaderboard.mostCurrent._currentmode)) { 
_showboard(_mode);};
 BA.debugLineNum = 157;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addusertomode(RemoteObject _mode,RemoteObject _n,RemoteObject _xp,RemoteObject _st,RemoteObject _cr) throws Exception{
try {
		Debug.PushSubsStack("AddUserToMode (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,146);
if (RapidSub.canDelegate("addusertomode")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","addusertomode", _mode, _n, _xp, _st, _cr);}
RemoteObject _target = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("mode", _mode);
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 146;BA.debugLine="Private Sub AddUserToMode(mode As String, n As Str";
Debug.ShouldStop(131072);
 BA.debugLineNum = 147;BA.debugLine="Dim target As List = GetModeList(mode)";
Debug.ShouldStop(262144);
_target = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_target = _getmodelist(_mode);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 148;BA.debugLine="If target.IsInitialized = False Then Return";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",_target.runMethod(true,"IsInitialized"),leaderboard.mostCurrent.__c.getField(true,"False"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 149;BA.debugLine="target.Add(CreateUser(n, xp, st, cr))";
Debug.ShouldStop(1048576);
_target.runVoidMethod ("Add",(Object)((_createuser(_n,_xp,_st,_cr))));
 BA.debugLineNum = 150;BA.debugLine="SortBoard(target)";
Debug.ShouldStop(2097152);
_sortboard(_target);
 BA.debugLineNum = 151;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnalltime_click() throws Exception{
try {
		Debug.PushSubsStack("btnAllTime_Click (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,330);
if (RapidSub.canDelegate("btnalltime_click")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","btnalltime_click");}
 BA.debugLineNum = 330;BA.debugLine="Private Sub btnAllTime_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 331;BA.debugLine="ShowBoard(\"ALLTIME\")";
Debug.ShouldStop(1024);
_showboard(RemoteObject.createImmutable("ALLTIME"));
 BA.debugLineNum = 332;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btndaily_click() throws Exception{
try {
		Debug.PushSubsStack("btnDaily_Click (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,322);
if (RapidSub.canDelegate("btndaily_click")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","btndaily_click");}
 BA.debugLineNum = 322;BA.debugLine="Private Sub btnDaily_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 323;BA.debugLine="ShowBoard(\"DAILY\")";
Debug.ShouldStop(4);
_showboard(RemoteObject.createImmutable("DAILY"));
 BA.debugLineNum = 324;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btninsert_click() throws Exception{
try {
		Debug.PushSubsStack("btnInsert_Click (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,334);
if (RapidSub.canDelegate("btninsert_click")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","btninsert_click");}
RemoteObject _n = RemoteObject.createImmutable("");
RemoteObject _xptext = RemoteObject.createImmutable("");
RemoteObject _streaktext = RemoteObject.createImmutable("");
RemoteObject _xp = RemoteObject.createImmutable(0);
RemoteObject _st = RemoteObject.createImmutable(0);
 BA.debugLineNum = 334;BA.debugLine="Private Sub btnInsert_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 335;BA.debugLine="Dim n As String = edtName.Text.Trim";
Debug.ShouldStop(16384);
_n = leaderboard.mostCurrent._edtname.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("n", _n);Debug.locals.put("n", _n);
 BA.debugLineNum = 336;BA.debugLine="Dim xpText As String = edtXP.Text.Trim";
Debug.ShouldStop(32768);
_xptext = leaderboard.mostCurrent._edtxp.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("xpText", _xptext);Debug.locals.put("xpText", _xptext);
 BA.debugLineNum = 337;BA.debugLine="Dim streakText As String = edtStreak.Text.Trim";
Debug.ShouldStop(65536);
_streaktext = leaderboard.mostCurrent._edtstreak.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("streakText", _streaktext);Debug.locals.put("streakText", _streaktext);
 BA.debugLineNum = 339;BA.debugLine="If n.Length = 0 Or xpText.Length = 0 Or streakTex";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_n.runMethod(true,"length"),BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("=",_xptext.runMethod(true,"length"),BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("=",_streaktext.runMethod(true,"length"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 340;BA.debugLine="ToastMessageShow(\"Please fill Name, XP, and Stre";
Debug.ShouldStop(524288);
leaderboard.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Please fill Name, XP, and Streak.")),(Object)(leaderboard.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 341;BA.debugLine="Return";
Debug.ShouldStop(1048576);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 344;BA.debugLine="If IsNumber(xpText) = False Or IsNumber(streakTex";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",leaderboard.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(_xptext)),leaderboard.mostCurrent.__c.getField(true,"False")) || RemoteObject.solveBoolean("=",leaderboard.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(_streaktext)),leaderboard.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 345;BA.debugLine="ToastMessageShow(\"XP and Streak must be numbers.";
Debug.ShouldStop(16777216);
leaderboard.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("XP and Streak must be numbers.")),(Object)(leaderboard.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 346;BA.debugLine="Return";
Debug.ShouldStop(33554432);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 349;BA.debugLine="Dim xp As Int = xpText";
Debug.ShouldStop(268435456);
_xp = BA.numberCast(int.class, _xptext);Debug.locals.put("xp", _xp);Debug.locals.put("xp", _xp);
 BA.debugLineNum = 350;BA.debugLine="Dim st As Int = streakText";
Debug.ShouldStop(536870912);
_st = BA.numberCast(int.class, _streaktext);Debug.locals.put("st", _st);Debug.locals.put("st", _st);
 BA.debugLineNum = 351;BA.debugLine="If xp < 0 Or st < 0 Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("<",_xp,BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("<",_st,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 352;BA.debugLine="ToastMessageShow(\"XP and Streak cannot be negati";
Debug.ShouldStop(-2147483648);
leaderboard.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("XP and Streak cannot be negative.")),(Object)(leaderboard.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 353;BA.debugLine="Return";
Debug.ShouldStop(1);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 357;BA.debugLine="AddUserScore(CurrentMode, n, xp, st, 0)";
Debug.ShouldStop(16);
_adduserscore(leaderboard.mostCurrent._currentmode,_n,_xp,_st,BA.numberCast(int.class, 0));
 BA.debugLineNum = 359;BA.debugLine="edtName.Text = \"\"";
Debug.ShouldStop(64);
leaderboard.mostCurrent._edtname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 360;BA.debugLine="edtXP.Text = \"\"";
Debug.ShouldStop(128);
leaderboard.mostCurrent._edtxp.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 361;BA.debugLine="edtStreak.Text = \"\"";
Debug.ShouldStop(256);
leaderboard.mostCurrent._edtstreak.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 362;BA.debugLine="ToastMessageShow(\"Inserted and ranked successfull";
Debug.ShouldStop(512);
leaderboard.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Inserted and ranked successfully.")),(Object)(leaderboard.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 363;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnweekly_click() throws Exception{
try {
		Debug.PushSubsStack("btnWeekly_Click (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,326);
if (RapidSub.canDelegate("btnweekly_click")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","btnweekly_click");}
 BA.debugLineNum = 326;BA.debugLine="Private Sub btnWeekly_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 327;BA.debugLine="ShowBoard(\"WEEKLY\")";
Debug.ShouldStop(64);
_showboard(RemoteObject.createImmutable("WEEKLY"));
 BA.debugLineNum = 328;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createuser(RemoteObject _n,RemoteObject _xp,RemoteObject _st,RemoteObject _cr) throws Exception{
try {
		Debug.PushSubsStack("CreateUser (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,135);
if (RapidSub.canDelegate("createuser")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","createuser", _n, _xp, _st, _cr);}
RemoteObject _u = RemoteObject.declareNull("b4a.example.leaderboard._userscore");
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 135;BA.debugLine="Private Sub CreateUser(n As String, xp As Int, st";
Debug.ShouldStop(64);
 BA.debugLineNum = 136;BA.debugLine="Dim u As UserScore";
Debug.ShouldStop(128);
_u = RemoteObject.createNew ("b4a.example.leaderboard._userscore");Debug.locals.put("u", _u);
 BA.debugLineNum = 137;BA.debugLine="u.Initialize";
Debug.ShouldStop(256);
_u.runVoidMethod ("Initialize");
 BA.debugLineNum = 138;BA.debugLine="u.Name = n";
Debug.ShouldStop(512);
_u.setField ("Name" /*RemoteObject*/ ,_n);
 BA.debugLineNum = 139;BA.debugLine="u.XP = xp";
Debug.ShouldStop(1024);
_u.setField ("XP" /*RemoteObject*/ ,_xp);
 BA.debugLineNum = 140;BA.debugLine="u.Streak = st";
Debug.ShouldStop(2048);
_u.setField ("Streak" /*RemoteObject*/ ,_st);
 BA.debugLineNum = 141;BA.debugLine="u.CorrectRate = cr";
Debug.ShouldStop(4096);
_u.setField ("CorrectRate" /*RemoteObject*/ ,_cr);
 BA.debugLineNum = 142;BA.debugLine="Return u";
Debug.ShouldStop(8192);
if (true) return _u;
 BA.debugLineNum = 143;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ensureseeddata() throws Exception{
try {
		Debug.PushSubsStack("EnsureSeedData (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,170);
if (RapidSub.canDelegate("ensureseeddata")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","ensureseeddata");}
RemoteObject _c = RemoteObject.createImmutable(0);
 BA.debugLineNum = 170;BA.debugLine="Private Sub EnsureSeedData";
Debug.ShouldStop(512);
 BA.debugLineNum = 171;BA.debugLine="Dim c As Int = SQL1.ExecQuerySingleResult(\"SELECT";
Debug.ShouldStop(1024);
_c = BA.numberCast(int.class, leaderboard._sql1.runMethod(true,"ExecQuerySingleResult",(Object)(RemoteObject.createImmutable("SELECT COUNT(*) FROM leaderboard"))));Debug.locals.put("c", _c);Debug.locals.put("c", _c);
 BA.debugLineNum = 172;BA.debugLine="If c = 0 Then SetupMockData";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",_c,BA.numberCast(double.class, 0))) { 
_setupmockdata();};
 BA.debugLineNum = 173;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getmodelist(RemoteObject _mode) throws Exception{
try {
		Debug.PushSubsStack("GetModeList (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,193);
if (RapidSub.canDelegate("getmodelist")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","getmodelist", _mode);}
Debug.locals.put("mode", _mode);
 BA.debugLineNum = 193;BA.debugLine="Private Sub GetModeList(mode As String) As List";
Debug.ShouldStop(1);
 BA.debugLineNum = 194;BA.debugLine="Select mode.ToUpperCase";
Debug.ShouldStop(2);
switch (BA.switchObjectToInt(_mode.runMethod(true,"toUpperCase"),BA.ObjectToString("DAILY"),BA.ObjectToString("WEEKLY"))) {
case 0: {
 BA.debugLineNum = 196;BA.debugLine="Return DailyList";
Debug.ShouldStop(8);
if (true) return leaderboard.mostCurrent._dailylist;
 break; }
case 1: {
 BA.debugLineNum = 198;BA.debugLine="Return WeeklyList";
Debug.ShouldStop(32);
if (true) return leaderboard.mostCurrent._weeklylist;
 break; }
default: {
 BA.debugLineNum = 200;BA.debugLine="Return AllTimeList";
Debug.ShouldStop(128);
if (true) return leaderboard.mostCurrent._alltimelist;
 break; }
}
;
 BA.debugLineNum = 202;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private xui As XUI";
leaderboard.mostCurrent._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 15;BA.debugLine="Private pnlHeader As Panel";
leaderboard.mostCurrent._pnlheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private lblTitle As Label";
leaderboard.mostCurrent._lbltitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private lblSubtitle As Label";
leaderboard.mostCurrent._lblsubtitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private pnlTopCard As Panel";
leaderboard.mostCurrent._pnltopcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private lblTopUser As Label";
leaderboard.mostCurrent._lbltopuser = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private lblTopPoints As Label";
leaderboard.mostCurrent._lbltoppoints = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private lblTopStreak As Label";
leaderboard.mostCurrent._lbltopstreak = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private clvBoard As CustomListView";
leaderboard.mostCurrent._clvboard = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 26;BA.debugLine="Private btnDaily As Button";
leaderboard.mostCurrent._btndaily = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private btnWeekly As Button";
leaderboard.mostCurrent._btnweekly = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private btnAllTime As Button";
leaderboard.mostCurrent._btnalltime = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private lblFooter As Label";
leaderboard.mostCurrent._lblfooter = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private pnlInsert As Panel";
leaderboard.mostCurrent._pnlinsert = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private edtName As EditText";
leaderboard.mostCurrent._edtname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private edtXP As EditText";
leaderboard.mostCurrent._edtxp = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private edtStreak As EditText";
leaderboard.mostCurrent._edtstreak = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private btnInsert As Button";
leaderboard.mostCurrent._btninsert = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Type UserScore(Name As String, XP As Int, Streak";
;
 //BA.debugLineNum = 39;BA.debugLine="Private DailyList As List";
leaderboard.mostCurrent._dailylist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 40;BA.debugLine="Private WeeklyList As List";
leaderboard.mostCurrent._weeklylist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 41;BA.debugLine="Private AllTimeList As List";
leaderboard.mostCurrent._alltimelist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 42;BA.debugLine="Private CurrentMode As String";
leaderboard.mostCurrent._currentmode = RemoteObject.createImmutable("");
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _initdb() throws Exception{
try {
		Debug.PushSubsStack("InitDB (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,159);
if (RapidSub.canDelegate("initdb")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","initdb");}
 BA.debugLineNum = 159;BA.debugLine="Private Sub InitDB";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 160;BA.debugLine="SQL1.Initialize(File.DirInternal, DB_NAME, True)";
Debug.ShouldStop(-2147483648);
leaderboard._sql1.runVoidMethod ("Initialize",(Object)(leaderboard.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(leaderboard._db_name),(Object)(leaderboard.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 161;BA.debugLine="SQL1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS lea";
Debug.ShouldStop(1);
leaderboard._sql1.runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("CREATE TABLE IF NOT EXISTS leaderboard ("),RemoteObject.createImmutable("id INTEGER PRIMARY KEY AUTOINCREMENT, "),RemoteObject.createImmutable("mode TEXT, "),RemoteObject.createImmutable("name TEXT, "),RemoteObject.createImmutable("xp INTEGER, "),RemoteObject.createImmutable("streak INTEGER, "),RemoteObject.createImmutable("correct_rate INTEGER)"))));
 BA.debugLineNum = 168;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _insertusertodb(RemoteObject _mode,RemoteObject _n,RemoteObject _xp,RemoteObject _st,RemoteObject _cr) throws Exception{
try {
		Debug.PushSubsStack("InsertUserToDB (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,175);
if (RapidSub.canDelegate("insertusertodb")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","insertusertodb", _mode, _n, _xp, _st, _cr);}
Debug.locals.put("mode", _mode);
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 175;BA.debugLine="Private Sub InsertUserToDB(mode As String, n As St";
Debug.ShouldStop(16384);
 BA.debugLineNum = 176;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO leaderboard(mode,";
Debug.ShouldStop(32768);
leaderboard._sql1.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO leaderboard(mode, name, xp, streak, correct_rate) VALUES (?, ?, ?, ?, ?)")),(Object)(leaderboard.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {5},new Object[] {(_mode.runMethod(true,"toUpperCase")),(_n),(_xp),(_st),(_cr)})))));
 BA.debugLineNum = 178;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loadboardfromdb(RemoteObject _mode) throws Exception{
try {
		Debug.PushSubsStack("LoadBoardFromDB (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,180);
if (RapidSub.canDelegate("loadboardfromdb")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","loadboardfromdb", _mode);}
RemoteObject _target = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _rs = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
Debug.locals.put("mode", _mode);
 BA.debugLineNum = 180;BA.debugLine="Private Sub LoadBoardFromDB(mode As String)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 181;BA.debugLine="Dim target As List = GetModeList(mode)";
Debug.ShouldStop(1048576);
_target = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_target = _getmodelist(_mode);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 182;BA.debugLine="target.Clear";
Debug.ShouldStop(2097152);
_target.runVoidMethod ("Clear");
 BA.debugLineNum = 183;BA.debugLine="Dim rs As ResultSet = SQL1.ExecQuery2( _ 		\"SELEC";
Debug.ShouldStop(4194304);
_rs = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
_rs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.ResultSetWrapper"), leaderboard._sql1.runMethod(false,"ExecQuery2",(Object)(RemoteObject.concat(RemoteObject.createImmutable("SELECT name, xp, streak, correct_rate FROM leaderboard WHERE mode = ? "),RemoteObject.createImmutable("ORDER BY xp DESC, streak DESC, correct_rate DESC"))),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {_mode.runMethod(true,"toUpperCase")}))));Debug.locals.put("rs", _rs);Debug.locals.put("rs", _rs);
 BA.debugLineNum = 187;BA.debugLine="Do While rs.NextRow";
Debug.ShouldStop(67108864);
while (_rs.runMethod(true,"NextRow").<Boolean>get().booleanValue()) {
 BA.debugLineNum = 188;BA.debugLine="target.Add(CreateUser(rs.GetString(\"name\"), rs.G";
Debug.ShouldStop(134217728);
_target.runVoidMethod ("Add",(Object)((_createuser(_rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("name"))),_rs.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("xp"))),_rs.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("streak"))),_rs.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("correct_rate")))))));
 }
;
 BA.debugLineNum = 190;BA.debugLine="rs.Close";
Debug.ShouldStop(536870912);
_rs.runVoidMethod ("Close");
 BA.debugLineNum = 191;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private SQL1 As SQL";
leaderboard._sql1 = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 8;BA.debugLine="Private DB_NAME As String = \"leaderboard.db\"";
leaderboard._db_name = BA.ObjectToString("leaderboard.db");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _setupinsertcontrols() throws Exception{
try {
		Debug.PushSubsStack("SetupInsertControls (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,82);
if (RapidSub.canDelegate("setupinsertcontrols")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","setupinsertcontrols");}
 BA.debugLineNum = 82;BA.debugLine="Private Sub SetupInsertControls";
Debug.ShouldStop(131072);
 BA.debugLineNum = 83;BA.debugLine="pnlInsert.Initialize(\"\")";
Debug.ShouldStop(262144);
leaderboard.mostCurrent._pnlinsert.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 84;BA.debugLine="pnlInsert.Color = xui.Color_ARGB(255, 255, 247, 2";
Debug.ShouldStop(524288);
leaderboard.mostCurrent._pnlinsert.runVoidMethod ("setColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 236))));
 BA.debugLineNum = 85;BA.debugLine="Activity.AddView(pnlInsert, 8dip, Activity.Height";
Debug.ShouldStop(1048576);
leaderboard.mostCurrent._activity.runVoidMethod ("AddView",(Object)((leaderboard.mostCurrent._pnlinsert.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._activity.runMethod(true,"getHeight"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 162)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._activity.runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 16)))}, "-",1, 1)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 154)))));
 BA.debugLineNum = 87;BA.debugLine="edtName.Initialize(\"\")";
Debug.ShouldStop(4194304);
leaderboard.mostCurrent._edtname.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 88;BA.debugLine="edtName.Hint = \"Name\"";
Debug.ShouldStop(8388608);
leaderboard.mostCurrent._edtname.runMethod(true,"setHint",BA.ObjectToString("Name"));
 BA.debugLineNum = 89;BA.debugLine="edtName.TextSize = 16";
Debug.ShouldStop(16777216);
leaderboard.mostCurrent._edtname.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 90;BA.debugLine="pnlInsert.AddView(edtName, 8dip, 6dip, pnlInsert.";
Debug.ShouldStop(33554432);
leaderboard.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((leaderboard.mostCurrent._edtname.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 6)))),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._pnlinsert.runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 16)))}, "-",1, 1)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 42)))));
 BA.debugLineNum = 92;BA.debugLine="edtXP.Initialize(\"\")";
Debug.ShouldStop(134217728);
leaderboard.mostCurrent._edtxp.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 93;BA.debugLine="edtXP.Hint = \"XP\"";
Debug.ShouldStop(268435456);
leaderboard.mostCurrent._edtxp.runMethod(true,"setHint",BA.ObjectToString("XP"));
 BA.debugLineNum = 94;BA.debugLine="edtXP.TextSize = 16";
Debug.ShouldStop(536870912);
leaderboard.mostCurrent._edtxp.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 95;BA.debugLine="edtXP.InputType = edtXP.INPUT_TYPE_NUMBERS";
Debug.ShouldStop(1073741824);
leaderboard.mostCurrent._edtxp.runMethod(true,"setInputType",leaderboard.mostCurrent._edtxp.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 96;BA.debugLine="pnlInsert.AddView(edtXP, 8dip, 54dip, (pnlInsert.";
Debug.ShouldStop(-2147483648);
leaderboard.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((leaderboard.mostCurrent._edtxp.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 54)))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._pnlinsert.runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 24)))}, "-",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 42)))));
 BA.debugLineNum = 98;BA.debugLine="edtStreak.Initialize(\"\")";
Debug.ShouldStop(2);
leaderboard.mostCurrent._edtstreak.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 99;BA.debugLine="edtStreak.Hint = \"Streak\"";
Debug.ShouldStop(4);
leaderboard.mostCurrent._edtstreak.runMethod(true,"setHint",BA.ObjectToString("Streak"));
 BA.debugLineNum = 100;BA.debugLine="edtStreak.TextSize = 16";
Debug.ShouldStop(8);
leaderboard.mostCurrent._edtstreak.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 101;BA.debugLine="edtStreak.InputType = edtStreak.INPUT_TYPE_NUMBER";
Debug.ShouldStop(16);
leaderboard.mostCurrent._edtstreak.runMethod(true,"setInputType",leaderboard.mostCurrent._edtstreak.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 102;BA.debugLine="pnlInsert.AddView(edtStreak, edtXP.Left + edtXP.W";
Debug.ShouldStop(32);
leaderboard.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((leaderboard.mostCurrent._edtstreak.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._edtxp.runMethod(true,"getLeft"),leaderboard.mostCurrent._edtxp.runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))}, "++",2, 1)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 54)))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._pnlinsert.runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 24)))}, "-",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 42)))));
 BA.debugLineNum = 104;BA.debugLine="btnInsert.Initialize(\"btnInsert\")";
Debug.ShouldStop(128);
leaderboard.mostCurrent._btninsert.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnInsert")));
 BA.debugLineNum = 105;BA.debugLine="btnInsert.Text = \"Insert to Leaderboard\"";
Debug.ShouldStop(256);
leaderboard.mostCurrent._btninsert.runMethod(true,"setText",BA.ObjectToCharSequence("Insert to Leaderboard"));
 BA.debugLineNum = 106;BA.debugLine="btnInsert.TextSize = 16";
Debug.ShouldStop(512);
leaderboard.mostCurrent._btninsert.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 107;BA.debugLine="btnInsert.Color = xui.Color_ARGB(255, 141, 113, 1";
Debug.ShouldStop(1024);
leaderboard.mostCurrent._btninsert.runVoidMethod ("setColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 141)),(Object)(BA.numberCast(int.class, 113)),(Object)(BA.numberCast(int.class, 176))));
 BA.debugLineNum = 108;BA.debugLine="btnInsert.TextColor = xui.Color_White";
Debug.ShouldStop(2048);
leaderboard.mostCurrent._btninsert.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 109;BA.debugLine="pnlInsert.AddView(btnInsert, 8dip, 102dip, pnlIns";
Debug.ShouldStop(4096);
leaderboard.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((leaderboard.mostCurrent._btninsert.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 102)))),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._pnlinsert.runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 16)))}, "-",1, 1)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 44)))));
 BA.debugLineNum = 110;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setupmockdata() throws Exception{
try {
		Debug.PushSubsStack("SetupMockData (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,112);
if (RapidSub.canDelegate("setupmockdata")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","setupmockdata");}
 BA.debugLineNum = 112;BA.debugLine="Private Sub SetupMockData";
Debug.ShouldStop(32768);
 BA.debugLineNum = 114;BA.debugLine="InsertUserToDB(\"DAILY\", \"Mika\", 320, 7, 93)";
Debug.ShouldStop(131072);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Mika"),BA.numberCast(int.class, 320),BA.numberCast(int.class, 7),BA.numberCast(int.class, 93));
 BA.debugLineNum = 115;BA.debugLine="InsertUserToDB(\"DAILY\", \"Rei\", 295, 5, 90)";
Debug.ShouldStop(262144);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Rei"),BA.numberCast(int.class, 295),BA.numberCast(int.class, 5),BA.numberCast(int.class, 90));
 BA.debugLineNum = 116;BA.debugLine="InsertUserToDB(\"DAILY\", \"Noah\", 280, 4, 88)";
Debug.ShouldStop(524288);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Noah"),BA.numberCast(int.class, 280),BA.numberCast(int.class, 4),BA.numberCast(int.class, 88));
 BA.debugLineNum = 117;BA.debugLine="InsertUserToDB(\"DAILY\", \"Ava\", 250, 3, 86)";
Debug.ShouldStop(1048576);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Ava"),BA.numberCast(int.class, 250),BA.numberCast(int.class, 3),BA.numberCast(int.class, 86));
 BA.debugLineNum = 118;BA.debugLine="InsertUserToDB(\"DAILY\", \"Luna\", 220, 2, 82)";
Debug.ShouldStop(2097152);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Luna"),BA.numberCast(int.class, 220),BA.numberCast(int.class, 2),BA.numberCast(int.class, 82));
 BA.debugLineNum = 121;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Mika\", 1760, 19, 92)";
Debug.ShouldStop(16777216);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Mika"),BA.numberCast(int.class, 1760),BA.numberCast(int.class, 19),BA.numberCast(int.class, 92));
 BA.debugLineNum = 122;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Ava\", 1690, 13, 91)";
Debug.ShouldStop(33554432);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Ava"),BA.numberCast(int.class, 1690),BA.numberCast(int.class, 13),BA.numberCast(int.class, 91));
 BA.debugLineNum = 123;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Rei\", 1610, 11, 89)";
Debug.ShouldStop(67108864);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Rei"),BA.numberCast(int.class, 1610),BA.numberCast(int.class, 11),BA.numberCast(int.class, 89));
 BA.debugLineNum = 124;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Noah\", 1495, 9, 87)";
Debug.ShouldStop(134217728);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Noah"),BA.numberCast(int.class, 1495),BA.numberCast(int.class, 9),BA.numberCast(int.class, 87));
 BA.debugLineNum = 125;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Luna\", 1420, 8, 85)";
Debug.ShouldStop(268435456);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Luna"),BA.numberCast(int.class, 1420),BA.numberCast(int.class, 8),BA.numberCast(int.class, 85));
 BA.debugLineNum = 128;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Ava\", 12850, 41, 90)";
Debug.ShouldStop(-2147483648);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Ava"),BA.numberCast(int.class, 12850),BA.numberCast(int.class, 41),BA.numberCast(int.class, 90));
 BA.debugLineNum = 129;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Mika\", 12110, 37, 91)";
Debug.ShouldStop(1);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Mika"),BA.numberCast(int.class, 12110),BA.numberCast(int.class, 37),BA.numberCast(int.class, 91));
 BA.debugLineNum = 130;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Noah\", 11680, 29, 88)";
Debug.ShouldStop(2);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Noah"),BA.numberCast(int.class, 11680),BA.numberCast(int.class, 29),BA.numberCast(int.class, 88));
 BA.debugLineNum = 131;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Rei\", 11300, 24, 87)";
Debug.ShouldStop(4);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Rei"),BA.numberCast(int.class, 11300),BA.numberCast(int.class, 24),BA.numberCast(int.class, 87));
 BA.debugLineNum = 132;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Luna\", 10950, 22, 86)";
Debug.ShouldStop(8);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Luna"),BA.numberCast(int.class, 10950),BA.numberCast(int.class, 22),BA.numberCast(int.class, 86));
 BA.debugLineNum = 133;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _shouldswapforranking(RemoteObject _a,RemoteObject _b) throws Exception{
try {
		Debug.PushSubsStack("ShouldSwapForRanking (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,217);
if (RapidSub.canDelegate("shouldswapforranking")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","shouldswapforranking", _a, _b);}
Debug.locals.put("a", _a);
Debug.locals.put("b", _b);
 BA.debugLineNum = 217;BA.debugLine="Private Sub ShouldSwapForRanking(a As UserScore, b";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 218;BA.debugLine="If b.XP > a.XP Then Return True";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean(">",_b.getField(true,"XP" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"XP" /*RemoteObject*/ )))) { 
if (true) return leaderboard.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 219;BA.debugLine="If b.XP = a.XP And b.Streak > a.Streak Then Retur";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",_b.getField(true,"XP" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"XP" /*RemoteObject*/ ))) && RemoteObject.solveBoolean(">",_b.getField(true,"Streak" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"Streak" /*RemoteObject*/ )))) { 
if (true) return leaderboard.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 220;BA.debugLine="If b.XP = a.XP And b.Streak = a.Streak And b.Corr";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_b.getField(true,"XP" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"XP" /*RemoteObject*/ ))) && RemoteObject.solveBoolean("=",_b.getField(true,"Streak" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"Streak" /*RemoteObject*/ ))) && RemoteObject.solveBoolean(">",_b.getField(true,"CorrectRate" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"CorrectRate" /*RemoteObject*/ )))) { 
if (true) return leaderboard.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 221;BA.debugLine="Return False";
Debug.ShouldStop(268435456);
if (true) return leaderboard.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 222;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showboard(RemoteObject _mode) throws Exception{
try {
		Debug.PushSubsStack("ShowBoard (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,224);
if (RapidSub.canDelegate("showboard")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","showboard", _mode);}
RemoteObject _source = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _top = RemoteObject.declareNull("b4a.example.leaderboard._userscore");
int _i = 0;
RemoteObject _u = RemoteObject.declareNull("b4a.example.leaderboard._userscore");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lblrank = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lblname = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lblstats = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("mode", _mode);
 BA.debugLineNum = 224;BA.debugLine="Private Sub ShowBoard(mode As String)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 225;BA.debugLine="mode = mode.ToUpperCase";
Debug.ShouldStop(1);
_mode = _mode.runMethod(true,"toUpperCase");Debug.locals.put("mode", _mode);
 BA.debugLineNum = 226;BA.debugLine="CurrentMode = mode";
Debug.ShouldStop(2);
leaderboard.mostCurrent._currentmode = _mode;
 BA.debugLineNum = 228;BA.debugLine="clvBoard.AsView.SetLayoutAnimated(0, clvBoard.AsV";
Debug.ShouldStop(8);
leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 0)),(Object)(leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getLeft")),(Object)(leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getTop")),(Object)(leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getWidth")),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._activity.runMethod(true,"getHeight"),leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getTop"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))}, "--",2, 1)));
 BA.debugLineNum = 231;BA.debugLine="Dim source As List";
Debug.ShouldStop(64);
_source = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("source", _source);
 BA.debugLineNum = 232;BA.debugLine="Select mode";
Debug.ShouldStop(128);
switch (BA.switchObjectToInt(_mode,BA.ObjectToString("DAILY"),BA.ObjectToString("WEEKLY"))) {
case 0: {
 BA.debugLineNum = 234;BA.debugLine="source = DailyList";
Debug.ShouldStop(512);
_source = leaderboard.mostCurrent._dailylist;Debug.locals.put("source", _source);
 BA.debugLineNum = 235;BA.debugLine="lblSubtitle.Text = \"Today's top flashcard learn";
Debug.ShouldStop(1024);
leaderboard.mostCurrent._lblsubtitle.runMethod(true,"setText",BA.ObjectToCharSequence("Today's top flashcard learners"));
 break; }
case 1: {
 BA.debugLineNum = 237;BA.debugLine="source = WeeklyList";
Debug.ShouldStop(4096);
_source = leaderboard.mostCurrent._weeklylist;Debug.locals.put("source", _source);
 BA.debugLineNum = 238;BA.debugLine="lblSubtitle.Text = \"Weekly focus champions\"";
Debug.ShouldStop(8192);
leaderboard.mostCurrent._lblsubtitle.runMethod(true,"setText",BA.ObjectToCharSequence("Weekly focus champions"));
 break; }
default: {
 BA.debugLineNum = 240;BA.debugLine="source = AllTimeList";
Debug.ShouldStop(32768);
_source = leaderboard.mostCurrent._alltimelist;Debug.locals.put("source", _source);
 BA.debugLineNum = 241;BA.debugLine="lblSubtitle.Text = \"All-time cozy legends\"";
Debug.ShouldStop(65536);
leaderboard.mostCurrent._lblsubtitle.runMethod(true,"setText",BA.ObjectToCharSequence("All-time cozy legends"));
 break; }
}
;
 BA.debugLineNum = 244;BA.debugLine="LoadBoardFromDB(mode)";
Debug.ShouldStop(524288);
_loadboardfromdb(_mode);
 BA.debugLineNum = 245;BA.debugLine="If source.Size = 0 Then Return";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",_source.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 248;BA.debugLine="Dim top As UserScore = source.Get(0)";
Debug.ShouldStop(8388608);
_top = (_source.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("top", _top);Debug.locals.put("top", _top);
 BA.debugLineNum = 249;BA.debugLine="lblTopUser.Text = \"🏆 \" & top.Name";
Debug.ShouldStop(16777216);
leaderboard.mostCurrent._lbltopuser.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("🏆 "),_top.getField(true,"Name" /*RemoteObject*/ ))));
 BA.debugLineNum = 250;BA.debugLine="lblTopPoints.Text = top.XP & \" XP\"";
Debug.ShouldStop(33554432);
leaderboard.mostCurrent._lbltoppoints.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_top.getField(true,"XP" /*RemoteObject*/ ),RemoteObject.createImmutable(" XP"))));
 BA.debugLineNum = 251;BA.debugLine="lblTopStreak.Text = \"🔥 \" & top.Streak & \" day st";
Debug.ShouldStop(67108864);
leaderboard.mostCurrent._lbltopstreak.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("🔥 "),_top.getField(true,"Streak" /*RemoteObject*/ ),RemoteObject.createImmutable(" day streak · "),_top.getField(true,"CorrectRate" /*RemoteObject*/ ),RemoteObject.createImmutable("% correct"))));
 BA.debugLineNum = 254;BA.debugLine="clvBoard.Clear";
Debug.ShouldStop(536870912);
leaderboard.mostCurrent._clvboard.runVoidMethod ("_clear");
 BA.debugLineNum = 255;BA.debugLine="For i = 0 To source.Size - 1";
Debug.ShouldStop(1073741824);
{
final int step23 = 1;
final int limit23 = RemoteObject.solve(new RemoteObject[] {_source.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23) ;_i = ((int)(0 + _i + step23))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 256;BA.debugLine="Dim u As UserScore = source.Get(i)";
Debug.ShouldStop(-2147483648);
_u = (_source.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("u", _u);Debug.locals.put("u", _u);
 BA.debugLineNum = 257;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(1);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 258;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(2);
_p.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 259;BA.debugLine="p.Color = xui.Color_ARGB(0, 255, 250, 242)";
Debug.ShouldStop(4);
_p.runVoidMethod ("setColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 250)),(Object)(BA.numberCast(int.class, 242))));
 BA.debugLineNum = 260;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, clvBoard.AsView.Wid";
Debug.ShouldStop(8);
_p.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getWidth")),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 262;BA.debugLine="Dim lblRank As Label";
Debug.ShouldStop(32);
_lblrank = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblRank", _lblrank);
 BA.debugLineNum = 263;BA.debugLine="lblRank.Initialize(\"\")";
Debug.ShouldStop(64);
_lblrank.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 264;BA.debugLine="lblRank.Text = \"#\" & (i + 1)";
Debug.ShouldStop(128);
_lblrank.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("#"),(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1)))));
 BA.debugLineNum = 265;BA.debugLine="lblRank.TextSize = 15";
Debug.ShouldStop(256);
_lblrank.runMethod(true,"setTextSize",BA.numberCast(float.class, 15));
 BA.debugLineNum = 266;BA.debugLine="lblRank.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(512);
_lblrank.runMethod(true,"setGravity",leaderboard.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 267;BA.debugLine="p.AddView(lblRank, 10dip, 0, 40dip, 62dip)";
Debug.ShouldStop(1024);
_p.runVoidMethod ("AddView",(Object)((_lblrank.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 269;BA.debugLine="Dim lblName As Label";
Debug.ShouldStop(4096);
_lblname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblName", _lblname);
 BA.debugLineNum = 270;BA.debugLine="lblName.Initialize(\"\")";
Debug.ShouldStop(8192);
_lblname.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 271;BA.debugLine="lblName.Text = u.Name";
Debug.ShouldStop(16384);
_lblname.runMethod(true,"setText",BA.ObjectToCharSequence(_u.getField(true,"Name" /*RemoteObject*/ )));
 BA.debugLineNum = 272;BA.debugLine="lblName.TextSize = 16";
Debug.ShouldStop(32768);
_lblname.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 273;BA.debugLine="lblName.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(65536);
_lblname.runMethod(true,"setGravity",leaderboard.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 274;BA.debugLine="p.AddView(lblName, 55dip, 0, 110dip, 62dip)";
Debug.ShouldStop(131072);
_p.runVoidMethod ("AddView",(Object)((_lblname.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))),(Object)(BA.numberCast(int.class, 0)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 110)))),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 276;BA.debugLine="Dim lblStats As Label";
Debug.ShouldStop(524288);
_lblstats = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblStats", _lblstats);
 BA.debugLineNum = 277;BA.debugLine="lblStats.Initialize(\"\")";
Debug.ShouldStop(1048576);
_lblstats.runVoidMethod ("Initialize",leaderboard.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 278;BA.debugLine="lblStats.Text = u.XP & \" XP   •   🔥\" & u.Streak";
Debug.ShouldStop(2097152);
_lblstats.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_u.getField(true,"XP" /*RemoteObject*/ ),RemoteObject.createImmutable(" XP   •   🔥"),_u.getField(true,"Streak" /*RemoteObject*/ ),RemoteObject.createImmutable("   •   "),_u.getField(true,"CorrectRate" /*RemoteObject*/ ),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 279;BA.debugLine="lblStats.TextSize = 13";
Debug.ShouldStop(4194304);
_lblstats.runMethod(true,"setTextSize",BA.numberCast(float.class, 13));
 BA.debugLineNum = 280;BA.debugLine="lblStats.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(8388608);
_lblstats.runMethod(true,"setGravity",leaderboard.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 281;BA.debugLine="p.AddView(lblStats, 165dip, 0, clvBoard.AsView.W";
Debug.ShouldStop(16777216);
_p.runVoidMethod ("AddView",(Object)((_lblstats.getObject())),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 165)))),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {leaderboard.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getWidth"),leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 175)))}, "-",1, 1)),(Object)(leaderboard.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 283;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(67108864);
switch (BA.switchObjectToInt(leaderboard.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 285;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(268435456);
if (leaderboard.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 286;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
Debug.ShouldStop(536870912);
_lblrank.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 84)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 101))));
 BA.debugLineNum = 287;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
Debug.ShouldStop(1073741824);
_lblname.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 62)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 288;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
Debug.ShouldStop(-2147483648);
_lblstats.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 }else {
 BA.debugLineNum = 290;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
Debug.ShouldStop(2);
_lblrank.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 84)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 101))));
 BA.debugLineNum = 291;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
Debug.ShouldStop(4);
_lblname.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 62)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 292;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
Debug.ShouldStop(8);
_lblstats.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 };
 break; }
case 1: {
 BA.debugLineNum = 295;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(64);
if (leaderboard.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 296;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
Debug.ShouldStop(128);
_lblrank.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 84)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 101))));
 BA.debugLineNum = 297;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
Debug.ShouldStop(256);
_lblname.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 62)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 298;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
Debug.ShouldStop(512);
_lblstats.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 }else {
 BA.debugLineNum = 300;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
Debug.ShouldStop(2048);
_lblrank.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 84)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 101))));
 BA.debugLineNum = 301;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
Debug.ShouldStop(4096);
_lblname.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 62)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 302;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
Debug.ShouldStop(8192);
_lblstats.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 };
 break; }
case 2: {
 BA.debugLineNum = 305;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(65536);
if (leaderboard.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 306;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 255,";
Debug.ShouldStop(131072);
_lblrank.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 307;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 255,";
Debug.ShouldStop(262144);
_lblname.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 308;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
Debug.ShouldStop(524288);
_lblstats.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 }else {
 BA.debugLineNum = 310;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 7";
Debug.ShouldStop(2097152);
_lblrank.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 84)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 101))));
 BA.debugLineNum = 311;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 5";
Debug.ShouldStop(4194304);
_lblname.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 62)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 312;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122,";
Debug.ShouldStop(8388608);
_lblstats.runMethod(true,"setTextColor",leaderboard.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 };
 break; }
}
;
 BA.debugLineNum = 316;BA.debugLine="clvBoard.Add(p, u.Name)";
Debug.ShouldStop(134217728);
leaderboard.mostCurrent._clvboard.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject()),(Object)((_u.getField(true,"Name" /*RemoteObject*/ ))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 319;BA.debugLine="lblFooter.Text = \"Keep reviewing your flashcards";
Debug.ShouldStop(1073741824);
leaderboard.mostCurrent._lblfooter.runMethod(true,"setText",BA.ObjectToCharSequence("Keep reviewing your flashcards to climb the cozy board ✨"));
 BA.debugLineNum = 320;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _sortboard(RemoteObject _board) throws Exception{
try {
		Debug.PushSubsStack("SortBoard (leaderboard) ","leaderboard",11,leaderboard.mostCurrent.activityBA,leaderboard.mostCurrent,204);
if (RapidSub.canDelegate("sortboard")) { return b4a.example.leaderboard.remoteMe.runUserSub(false, "leaderboard","sortboard", _board);}
int _i = 0;
int _j = 0;
RemoteObject _a = RemoteObject.declareNull("b4a.example.leaderboard._userscore");
RemoteObject _b = RemoteObject.declareNull("b4a.example.leaderboard._userscore");
Debug.locals.put("board", _board);
 BA.debugLineNum = 204;BA.debugLine="Private Sub SortBoard(board As List)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 205;BA.debugLine="For i = 0 To board.Size - 2";
Debug.ShouldStop(4096);
{
final int step1 = 1;
final int limit1 = RemoteObject.solve(new RemoteObject[] {_board.runMethod(true,"getSize"),RemoteObject.createImmutable(2)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1) ;_i = ((int)(0 + _i + step1))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 206;BA.debugLine="For j = i + 1 To board.Size - 1";
Debug.ShouldStop(8192);
{
final int step2 = 1;
final int limit2 = RemoteObject.solve(new RemoteObject[] {_board.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_j = RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1).<Integer>get().intValue() ;
for (;(step2 > 0 && _j <= limit2) || (step2 < 0 && _j >= limit2) ;_j = ((int)(0 + _j + step2))  ) {
Debug.locals.put("j", _j);
 BA.debugLineNum = 207;BA.debugLine="Dim a As UserScore = board.Get(i)";
Debug.ShouldStop(16384);
_a = (_board.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("a", _a);Debug.locals.put("a", _a);
 BA.debugLineNum = 208;BA.debugLine="Dim b As UserScore = board.Get(j)";
Debug.ShouldStop(32768);
_b = (_board.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _j))));Debug.locals.put("b", _b);Debug.locals.put("b", _b);
 BA.debugLineNum = 209;BA.debugLine="If ShouldSwapForRanking(a, b) Then";
Debug.ShouldStop(65536);
if (_shouldswapforranking(_a,_b).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 210;BA.debugLine="board.Set(i, b)";
Debug.ShouldStop(131072);
_board.runVoidMethod ("Set",(Object)(BA.numberCast(int.class, _i)),(Object)((_b)));
 BA.debugLineNum = 211;BA.debugLine="board.Set(j, a)";
Debug.ShouldStop(262144);
_board.runVoidMethod ("Set",(Object)(BA.numberCast(int.class, _j)),(Object)((_a)));
 };
 }
}Debug.locals.put("j", _j);
;
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 215;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}