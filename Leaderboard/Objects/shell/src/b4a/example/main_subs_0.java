package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,53);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 53;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 54;BA.debugLine="Activity.LoadLayout(\"layout\")";
Debug.ShouldStop(2097152);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"Leaderboard\")";
Debug.ShouldStop(4194304);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Leaderboard")),main.mostCurrent.activityBA);
 BA.debugLineNum = 56;BA.debugLine="DailyList.Initialize";
Debug.ShouldStop(8388608);
main.mostCurrent._dailylist.runVoidMethod ("Initialize");
 BA.debugLineNum = 57;BA.debugLine="WeeklyList.Initialize";
Debug.ShouldStop(16777216);
main.mostCurrent._weeklylist.runVoidMethod ("Initialize");
 BA.debugLineNum = 58;BA.debugLine="AllTimeList.Initialize";
Debug.ShouldStop(33554432);
main.mostCurrent._alltimelist.runVoidMethod ("Initialize");
 BA.debugLineNum = 59;BA.debugLine="InitDB";
Debug.ShouldStop(67108864);
_initdb();
 BA.debugLineNum = 60;BA.debugLine="EnsureSeedData";
Debug.ShouldStop(134217728);
_ensureseeddata();
 BA.debugLineNum = 61;BA.debugLine="SetupTheme";
Debug.ShouldStop(268435456);
_setuptheme();
 BA.debugLineNum = 62;BA.debugLine="SetupInsertControls";
Debug.ShouldStop(536870912);
_setupinsertcontrols();
 BA.debugLineNum = 63;BA.debugLine="ShowBoard(\"DAILY\")";
Debug.ShouldStop(1073741824);
_showboard(RemoteObject.createImmutable("DAILY"));
 BA.debugLineNum = 64;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,69);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 69;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16);
 BA.debugLineNum = 70;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,66);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 66;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2);
 BA.debugLineNum = 67;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("AddUserScore (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,165);
if (RapidSub.canDelegate("adduserscore")) { return b4a.example.main.remoteMe.runUserSub(false, "main","adduserscore", _mode, _n, _xp, _st, _cr);}
Debug.locals.put("mode", _mode);
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 165;BA.debugLine="Public Sub AddUserScore(mode As String, n As Strin";
Debug.ShouldStop(16);
 BA.debugLineNum = 166;BA.debugLine="InsertUserToDB(mode, n, xp, st, cr)";
Debug.ShouldStop(32);
_insertusertodb(_mode,_n,_xp,_st,_cr);
 BA.debugLineNum = 167;BA.debugLine="If mode = CurrentMode Then ShowBoard(mode)";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",_mode,main.mostCurrent._currentmode)) { 
_showboard(_mode);};
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
public static RemoteObject  _addusertomode(RemoteObject _mode,RemoteObject _n,RemoteObject _xp,RemoteObject _st,RemoteObject _cr) throws Exception{
try {
		Debug.PushSubsStack("AddUserToMode (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,157);
if (RapidSub.canDelegate("addusertomode")) { return b4a.example.main.remoteMe.runUserSub(false, "main","addusertomode", _mode, _n, _xp, _st, _cr);}
RemoteObject _target = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("mode", _mode);
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 157;BA.debugLine="Private Sub AddUserToMode(mode As String, n As Str";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="Dim target As List = GetModeList(mode)";
Debug.ShouldStop(536870912);
_target = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_target = _getmodelist(_mode);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 159;BA.debugLine="If target.IsInitialized = False Then Return";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",_target.runMethod(true,"IsInitialized"),main.mostCurrent.__c.getField(true,"False"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 160;BA.debugLine="target.Add(CreateUser(n, xp, st, cr))";
Debug.ShouldStop(-2147483648);
_target.runVoidMethod ("Add",(Object)((_createuser(_n,_xp,_st,_cr))));
 BA.debugLineNum = 161;BA.debugLine="SortBoard(target)";
Debug.ShouldStop(1);
_sortboard(_target);
 BA.debugLineNum = 162;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("btnAllTime_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,321);
if (RapidSub.canDelegate("btnalltime_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnalltime_click");}
 BA.debugLineNum = 321;BA.debugLine="Private Sub btnAllTime_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 322;BA.debugLine="ShowBoard(\"ALLTIME\")";
Debug.ShouldStop(2);
_showboard(RemoteObject.createImmutable("ALLTIME"));
 BA.debugLineNum = 323;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("btnDaily_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,313);
if (RapidSub.canDelegate("btndaily_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btndaily_click");}
 BA.debugLineNum = 313;BA.debugLine="Private Sub btnDaily_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 314;BA.debugLine="ShowBoard(\"DAILY\")";
Debug.ShouldStop(33554432);
_showboard(RemoteObject.createImmutable("DAILY"));
 BA.debugLineNum = 315;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("btnInsert_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,325);
if (RapidSub.canDelegate("btninsert_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btninsert_click");}
RemoteObject _n = RemoteObject.createImmutable("");
RemoteObject _xptext = RemoteObject.createImmutable("");
RemoteObject _streaktext = RemoteObject.createImmutable("");
RemoteObject _xp = RemoteObject.createImmutable(0);
RemoteObject _st = RemoteObject.createImmutable(0);
 BA.debugLineNum = 325;BA.debugLine="Private Sub btnInsert_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 326;BA.debugLine="Dim n As String = edtName.Text.Trim";
Debug.ShouldStop(32);
_n = main.mostCurrent._edtname.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("n", _n);Debug.locals.put("n", _n);
 BA.debugLineNum = 327;BA.debugLine="Dim xpText As String = edtXP.Text.Trim";
Debug.ShouldStop(64);
_xptext = main.mostCurrent._edtxp.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("xpText", _xptext);Debug.locals.put("xpText", _xptext);
 BA.debugLineNum = 328;BA.debugLine="Dim streakText As String = edtStreak.Text.Trim";
Debug.ShouldStop(128);
_streaktext = main.mostCurrent._edtstreak.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("streakText", _streaktext);Debug.locals.put("streakText", _streaktext);
 BA.debugLineNum = 330;BA.debugLine="If n.Length = 0 Or xpText.Length = 0 Or streakTex";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",_n.runMethod(true,"length"),BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("=",_xptext.runMethod(true,"length"),BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("=",_streaktext.runMethod(true,"length"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 331;BA.debugLine="ToastMessageShow(\"Please fill Name, XP, and Stre";
Debug.ShouldStop(1024);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Please fill Name, XP, and Streak.")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 332;BA.debugLine="Return";
Debug.ShouldStop(2048);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 335;BA.debugLine="If IsNumber(xpText) = False Or IsNumber(streakTex";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",main.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(_xptext)),main.mostCurrent.__c.getField(true,"False")) || RemoteObject.solveBoolean("=",main.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(_streaktext)),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 336;BA.debugLine="ToastMessageShow(\"XP and Streak must be numbers.";
Debug.ShouldStop(32768);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("XP and Streak must be numbers.")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 337;BA.debugLine="Return";
Debug.ShouldStop(65536);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 340;BA.debugLine="Dim xp As Int = xpText";
Debug.ShouldStop(524288);
_xp = BA.numberCast(int.class, _xptext);Debug.locals.put("xp", _xp);Debug.locals.put("xp", _xp);
 BA.debugLineNum = 341;BA.debugLine="Dim st As Int = streakText";
Debug.ShouldStop(1048576);
_st = BA.numberCast(int.class, _streaktext);Debug.locals.put("st", _st);Debug.locals.put("st", _st);
 BA.debugLineNum = 342;BA.debugLine="If xp < 0 Or st < 0 Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("<",_xp,BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("<",_st,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 343;BA.debugLine="ToastMessageShow(\"XP and Streak cannot be negati";
Debug.ShouldStop(4194304);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("XP and Streak cannot be negative.")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 344;BA.debugLine="Return";
Debug.ShouldStop(8388608);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 348;BA.debugLine="AddUserScore(CurrentMode, n, xp, st, 0)";
Debug.ShouldStop(134217728);
_adduserscore(main.mostCurrent._currentmode,_n,_xp,_st,BA.numberCast(int.class, 0));
 BA.debugLineNum = 350;BA.debugLine="edtName.Text = \"\"";
Debug.ShouldStop(536870912);
main.mostCurrent._edtname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 351;BA.debugLine="edtXP.Text = \"\"";
Debug.ShouldStop(1073741824);
main.mostCurrent._edtxp.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 352;BA.debugLine="edtStreak.Text = \"\"";
Debug.ShouldStop(-2147483648);
main.mostCurrent._edtstreak.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 353;BA.debugLine="ToastMessageShow(\"Inserted and ranked successfull";
Debug.ShouldStop(1);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Inserted and ranked successfully.")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 354;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("btnWeekly_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,317);
if (RapidSub.canDelegate("btnweekly_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnweekly_click");}
 BA.debugLineNum = 317;BA.debugLine="Private Sub btnWeekly_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 318;BA.debugLine="ShowBoard(\"WEEKLY\")";
Debug.ShouldStop(536870912);
_showboard(RemoteObject.createImmutable("WEEKLY"));
 BA.debugLineNum = 319;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("CreateUser (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,146);
if (RapidSub.canDelegate("createuser")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createuser", _n, _xp, _st, _cr);}
RemoteObject _u = RemoteObject.declareNull("b4a.example.main._userscore");
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 146;BA.debugLine="Private Sub CreateUser(n As String, xp As Int, st";
Debug.ShouldStop(131072);
 BA.debugLineNum = 147;BA.debugLine="Dim u As UserScore";
Debug.ShouldStop(262144);
_u = RemoteObject.createNew ("b4a.example.main._userscore");Debug.locals.put("u", _u);
 BA.debugLineNum = 148;BA.debugLine="u.Initialize";
Debug.ShouldStop(524288);
_u.runVoidMethod ("Initialize");
 BA.debugLineNum = 149;BA.debugLine="u.Name = n";
Debug.ShouldStop(1048576);
_u.setField ("Name" /*RemoteObject*/ ,_n);
 BA.debugLineNum = 150;BA.debugLine="u.XP = xp";
Debug.ShouldStop(2097152);
_u.setField ("XP" /*RemoteObject*/ ,_xp);
 BA.debugLineNum = 151;BA.debugLine="u.Streak = st";
Debug.ShouldStop(4194304);
_u.setField ("Streak" /*RemoteObject*/ ,_st);
 BA.debugLineNum = 152;BA.debugLine="u.CorrectRate = cr";
Debug.ShouldStop(8388608);
_u.setField ("CorrectRate" /*RemoteObject*/ ,_cr);
 BA.debugLineNum = 153;BA.debugLine="Return u";
Debug.ShouldStop(16777216);
if (true) return _u;
 BA.debugLineNum = 154;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("EnsureSeedData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,181);
if (RapidSub.canDelegate("ensureseeddata")) { return b4a.example.main.remoteMe.runUserSub(false, "main","ensureseeddata");}
RemoteObject _c = RemoteObject.createImmutable(0);
 BA.debugLineNum = 181;BA.debugLine="Private Sub EnsureSeedData";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 182;BA.debugLine="Dim c As Int = SQL1.ExecQuerySingleResult(\"SELECT";
Debug.ShouldStop(2097152);
_c = BA.numberCast(int.class, main._sql1.runMethod(true,"ExecQuerySingleResult",(Object)(RemoteObject.createImmutable("SELECT COUNT(*) FROM leaderboard"))));Debug.locals.put("c", _c);Debug.locals.put("c", _c);
 BA.debugLineNum = 183;BA.debugLine="If c = 0 Then SetupMockData";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",_c,BA.numberCast(double.class, 0))) { 
_setupmockdata();};
 BA.debugLineNum = 184;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
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
		Debug.PushSubsStack("GetModeList (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,204);
if (RapidSub.canDelegate("getmodelist")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getmodelist", _mode);}
Debug.locals.put("mode", _mode);
 BA.debugLineNum = 204;BA.debugLine="Private Sub GetModeList(mode As String) As List";
Debug.ShouldStop(2048);
 BA.debugLineNum = 205;BA.debugLine="Select mode.ToUpperCase";
Debug.ShouldStop(4096);
switch (BA.switchObjectToInt(_mode.runMethod(true,"toUpperCase"),BA.ObjectToString("DAILY"),BA.ObjectToString("WEEKLY"))) {
case 0: {
 BA.debugLineNum = 207;BA.debugLine="Return DailyList";
Debug.ShouldStop(16384);
if (true) return main.mostCurrent._dailylist;
 break; }
case 1: {
 BA.debugLineNum = 209;BA.debugLine="Return WeeklyList";
Debug.ShouldStop(65536);
if (true) return main.mostCurrent._weeklylist;
 break; }
default: {
 BA.debugLineNum = 211;BA.debugLine="Return AllTimeList";
Debug.ShouldStop(262144);
if (true) return main.mostCurrent._alltimelist;
 break; }
}
;
 BA.debugLineNum = 213;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
main.mostCurrent._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 23;BA.debugLine="Private pnlHeader As Panel";
main.mostCurrent._pnlheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private lblTitle As Label";
main.mostCurrent._lbltitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private lblSubtitle As Label";
main.mostCurrent._lblsubtitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private pnlTopCard As Panel";
main.mostCurrent._pnltopcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private lblTopUser As Label";
main.mostCurrent._lbltopuser = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private lblTopPoints As Label";
main.mostCurrent._lbltoppoints = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private lblTopStreak As Label";
main.mostCurrent._lbltopstreak = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private clvBoard As CustomListView";
main.mostCurrent._clvboard = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 34;BA.debugLine="Private btnDaily As Button";
main.mostCurrent._btndaily = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private btnWeekly As Button";
main.mostCurrent._btnweekly = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private btnAllTime As Button";
main.mostCurrent._btnalltime = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private lblFooter As Label";
main.mostCurrent._lblfooter = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private pnlInsert As Panel";
main.mostCurrent._pnlinsert = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private edtName As EditText";
main.mostCurrent._edtname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private edtXP As EditText";
main.mostCurrent._edtxp = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private edtStreak As EditText";
main.mostCurrent._edtstreak = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private btnInsert As Button";
main.mostCurrent._btninsert = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 46;BA.debugLine="Type UserScore(Name As String, XP As Int, Streak";
;
 //BA.debugLineNum = 47;BA.debugLine="Private DailyList As List";
main.mostCurrent._dailylist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 48;BA.debugLine="Private WeeklyList As List";
main.mostCurrent._weeklylist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 49;BA.debugLine="Private AllTimeList As List";
main.mostCurrent._alltimelist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 50;BA.debugLine="Private CurrentMode As String";
main.mostCurrent._currentmode = RemoteObject.createImmutable("");
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _highlightbutton(RemoteObject _activebtn) throws Exception{
try {
		Debug.PushSubsStack("HighlightButton (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,302);
if (RapidSub.canDelegate("highlightbutton")) { return b4a.example.main.remoteMe.runUserSub(false, "main","highlightbutton", _activebtn);}
RemoteObject _activecolor = RemoteObject.createImmutable(0);
RemoteObject _normalcolor = RemoteObject.createImmutable(0);
Debug.locals.put("activeBtn", _activebtn);
 BA.debugLineNum = 302;BA.debugLine="Private Sub HighlightButton(activeBtn As Button)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 303;BA.debugLine="Dim activeColor As Int = xui.Color_ARGB(255, 141,";
Debug.ShouldStop(16384);
_activecolor = main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 141)),(Object)(BA.numberCast(int.class, 113)),(Object)(BA.numberCast(int.class, 176)));Debug.locals.put("activeColor", _activecolor);Debug.locals.put("activeColor", _activecolor);
 BA.debugLineNum = 304;BA.debugLine="Dim normalColor As Int = xui.Color_ARGB(255, 185,";
Debug.ShouldStop(32768);
_normalcolor = main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 185)),(Object)(BA.numberCast(int.class, 155)),(Object)(BA.numberCast(int.class, 214)));Debug.locals.put("normalColor", _normalcolor);Debug.locals.put("normalColor", _normalcolor);
 BA.debugLineNum = 306;BA.debugLine="btnDaily.Color = normalColor";
Debug.ShouldStop(131072);
main.mostCurrent._btndaily.runVoidMethod ("setColor",_normalcolor);
 BA.debugLineNum = 307;BA.debugLine="btnWeekly.Color = normalColor";
Debug.ShouldStop(262144);
main.mostCurrent._btnweekly.runVoidMethod ("setColor",_normalcolor);
 BA.debugLineNum = 308;BA.debugLine="btnAllTime.Color = normalColor";
Debug.ShouldStop(524288);
main.mostCurrent._btnalltime.runVoidMethod ("setColor",_normalcolor);
 BA.debugLineNum = 310;BA.debugLine="activeBtn.Color = activeColor";
Debug.ShouldStop(2097152);
_activebtn.runVoidMethod ("setColor",_activecolor);
 BA.debugLineNum = 311;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initdb() throws Exception{
try {
		Debug.PushSubsStack("InitDB (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,170);
if (RapidSub.canDelegate("initdb")) { return b4a.example.main.remoteMe.runUserSub(false, "main","initdb");}
 BA.debugLineNum = 170;BA.debugLine="Private Sub InitDB";
Debug.ShouldStop(512);
 BA.debugLineNum = 171;BA.debugLine="SQL1.Initialize(File.DirInternal, DB_NAME, True)";
Debug.ShouldStop(1024);
main._sql1.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(main._db_name),(Object)(main.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 172;BA.debugLine="SQL1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS lea";
Debug.ShouldStop(2048);
main._sql1.runVoidMethod ("ExecNonQuery",(Object)(RemoteObject.concat(RemoteObject.createImmutable("CREATE TABLE IF NOT EXISTS leaderboard ("),RemoteObject.createImmutable("id INTEGER PRIMARY KEY AUTOINCREMENT, "),RemoteObject.createImmutable("mode TEXT, "),RemoteObject.createImmutable("name TEXT, "),RemoteObject.createImmutable("xp INTEGER, "),RemoteObject.createImmutable("streak INTEGER, "),RemoteObject.createImmutable("correct_rate INTEGER)"))));
 BA.debugLineNum = 179;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("InsertUserToDB (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,186);
if (RapidSub.canDelegate("insertusertodb")) { return b4a.example.main.remoteMe.runUserSub(false, "main","insertusertodb", _mode, _n, _xp, _st, _cr);}
Debug.locals.put("mode", _mode);
Debug.locals.put("n", _n);
Debug.locals.put("xp", _xp);
Debug.locals.put("st", _st);
Debug.locals.put("cr", _cr);
 BA.debugLineNum = 186;BA.debugLine="Private Sub InsertUserToDB(mode As String, n As St";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO leaderboard(mode,";
Debug.ShouldStop(67108864);
main._sql1.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO leaderboard(mode, name, xp, streak, correct_rate) VALUES (?, ?, ?, ?, ?)")),(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {5},new Object[] {(_mode.runMethod(true,"toUpperCase")),(_n),(_xp),(_st),(_cr)})))));
 BA.debugLineNum = 189;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
		Debug.PushSubsStack("LoadBoardFromDB (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,191);
if (RapidSub.canDelegate("loadboardfromdb")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadboardfromdb", _mode);}
RemoteObject _target = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _rs = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
Debug.locals.put("mode", _mode);
 BA.debugLineNum = 191;BA.debugLine="Private Sub LoadBoardFromDB(mode As String)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 192;BA.debugLine="Dim target As List = GetModeList(mode)";
Debug.ShouldStop(-2147483648);
_target = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_target = _getmodelist(_mode);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 193;BA.debugLine="target.Clear";
Debug.ShouldStop(1);
_target.runVoidMethod ("Clear");
 BA.debugLineNum = 194;BA.debugLine="Dim rs As ResultSet = SQL1.ExecQuery2( _ 		\"SELEC";
Debug.ShouldStop(2);
_rs = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
_rs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.ResultSetWrapper"), main._sql1.runMethod(false,"ExecQuery2",(Object)(RemoteObject.concat(RemoteObject.createImmutable("SELECT name, xp, streak, correct_rate FROM leaderboard WHERE mode = ? "),RemoteObject.createImmutable("ORDER BY xp DESC, streak DESC, correct_rate DESC"))),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {_mode.runMethod(true,"toUpperCase")}))));Debug.locals.put("rs", _rs);Debug.locals.put("rs", _rs);
 BA.debugLineNum = 198;BA.debugLine="Do While rs.NextRow";
Debug.ShouldStop(32);
while (_rs.runMethod(true,"NextRow").<Boolean>get().booleanValue()) {
 BA.debugLineNum = 199;BA.debugLine="target.Add(CreateUser(rs.GetString(\"name\"), rs.G";
Debug.ShouldStop(64);
_target.runVoidMethod ("Add",(Object)((_createuser(_rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("name"))),_rs.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("xp"))),_rs.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("streak"))),_rs.runMethod(true,"GetInt",(Object)(RemoteObject.createImmutable("correct_rate")))))));
 }
;
 BA.debugLineNum = 201;BA.debugLine="rs.Close";
Debug.ShouldStop(256);
_rs.runVoidMethod ("Close");
 BA.debugLineNum = 202;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
xuiviewsutils_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
animatedcounter.myClass = BA.getDeviceClass ("b4a.example.animatedcounter");
anotherprogressbar.myClass = BA.getDeviceClass ("b4a.example.anotherprogressbar");
b4xbreadcrumb.myClass = BA.getDeviceClass ("b4a.example.b4xbreadcrumb");
b4xcolortemplate.myClass = BA.getDeviceClass ("b4a.example.b4xcolortemplate");
b4xcombobox.myClass = BA.getDeviceClass ("b4a.example.b4xcombobox");
b4xdatetemplate.myClass = BA.getDeviceClass ("b4a.example.b4xdatetemplate");
b4xdialog.myClass = BA.getDeviceClass ("b4a.example.b4xdialog");
b4xfloattextfield.myClass = BA.getDeviceClass ("b4a.example.b4xfloattextfield");
b4ximageview.myClass = BA.getDeviceClass ("b4a.example.b4ximageview");
b4xinputtemplate.myClass = BA.getDeviceClass ("b4a.example.b4xinputtemplate");
b4xlisttemplate.myClass = BA.getDeviceClass ("b4a.example.b4xlisttemplate");
b4xloadingindicator.myClass = BA.getDeviceClass ("b4a.example.b4xloadingindicator");
b4xlongtexttemplate.myClass = BA.getDeviceClass ("b4a.example.b4xlongtexttemplate");
b4xplusminus.myClass = BA.getDeviceClass ("b4a.example.b4xplusminus");
b4xprogressdialog.myClass = BA.getDeviceClass ("b4a.example.b4xprogressdialog");
b4xradiobutton.myClass = BA.getDeviceClass ("b4a.example.b4xradiobutton");
b4xsearchtemplate.myClass = BA.getDeviceClass ("b4a.example.b4xsearchtemplate");
b4xseekbar.myClass = BA.getDeviceClass ("b4a.example.b4xseekbar");
b4xsignaturetemplate.myClass = BA.getDeviceClass ("b4a.example.b4xsignaturetemplate");
b4xswitch.myClass = BA.getDeviceClass ("b4a.example.b4xswitch");
b4xtimedtemplate.myClass = BA.getDeviceClass ("b4a.example.b4xtimedtemplate");
madewithlove.myClass = BA.getDeviceClass ("b4a.example.madewithlove");
b4xformatter.myClass = BA.getDeviceClass ("b4a.example.b4xformatter");
roundslider.myClass = BA.getDeviceClass ("b4a.example.roundslider");
scrollinglabel.myClass = BA.getDeviceClass ("b4a.example.scrollinglabel");
swiftbutton.myClass = BA.getDeviceClass ("b4a.example.swiftbutton");
xuiviewsutils.myClass = BA.getDeviceClass ("b4a.example.xuiviewsutils");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private SQL1 As SQL";
main._sql1 = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 16;BA.debugLine="Private DB_NAME As String = \"leaderboard.db\"";
main._db_name = BA.ObjectToString("leaderboard.db");
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _setupinsertcontrols() throws Exception{
try {
		Debug.PushSubsStack("SetupInsertControls (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,97);
if (RapidSub.canDelegate("setupinsertcontrols")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setupinsertcontrols");}
 BA.debugLineNum = 97;BA.debugLine="Private Sub SetupInsertControls";
Debug.ShouldStop(1);
 BA.debugLineNum = 98;BA.debugLine="pnlInsert.Initialize(\"\")";
Debug.ShouldStop(2);
main.mostCurrent._pnlinsert.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 99;BA.debugLine="pnlInsert.Color = xui.Color_ARGB(255, 255, 247, 2";
Debug.ShouldStop(4);
main.mostCurrent._pnlinsert.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 236))));
 BA.debugLineNum = 100;BA.debugLine="Activity.AddView(pnlInsert, 8dip, Activity.Height";
Debug.ShouldStop(8);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._pnlinsert.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._activity.runMethod(true,"getHeight"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 154)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._activity.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 16)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 146)))));
 BA.debugLineNum = 102;BA.debugLine="edtName.Initialize(\"\")";
Debug.ShouldStop(32);
main.mostCurrent._edtname.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 103;BA.debugLine="edtName.Hint = \"Name\"";
Debug.ShouldStop(64);
main.mostCurrent._edtname.runMethod(true,"setHint",BA.ObjectToString("Name"));
 BA.debugLineNum = 104;BA.debugLine="pnlInsert.AddView(edtName, 8dip, 8dip, pnlInsert.";
Debug.ShouldStop(128);
main.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((main.mostCurrent._edtname.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlinsert.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 16)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 106;BA.debugLine="edtXP.Initialize(\"\")";
Debug.ShouldStop(512);
main.mostCurrent._edtxp.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 107;BA.debugLine="edtXP.Hint = \"XP\"";
Debug.ShouldStop(1024);
main.mostCurrent._edtxp.runMethod(true,"setHint",BA.ObjectToString("XP"));
 BA.debugLineNum = 108;BA.debugLine="edtXP.InputType = edtXP.INPUT_TYPE_NUMBERS";
Debug.ShouldStop(2048);
main.mostCurrent._edtxp.runMethod(true,"setInputType",main.mostCurrent._edtxp.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 109;BA.debugLine="pnlInsert.AddView(edtXP, 8dip, 54dip, (pnlInsert.";
Debug.ShouldStop(4096);
main.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((main.mostCurrent._edtxp.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 54)))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlinsert.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 24)))}, "-",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 111;BA.debugLine="edtStreak.Initialize(\"\")";
Debug.ShouldStop(16384);
main.mostCurrent._edtstreak.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 112;BA.debugLine="edtStreak.Hint = \"Streak\"";
Debug.ShouldStop(32768);
main.mostCurrent._edtstreak.runMethod(true,"setHint",BA.ObjectToString("Streak"));
 BA.debugLineNum = 113;BA.debugLine="edtStreak.InputType = edtStreak.INPUT_TYPE_NUMBER";
Debug.ShouldStop(65536);
main.mostCurrent._edtstreak.runMethod(true,"setInputType",main.mostCurrent._edtstreak.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 114;BA.debugLine="pnlInsert.AddView(edtStreak, edtXP.Left + edtXP.W";
Debug.ShouldStop(131072);
main.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((main.mostCurrent._edtstreak.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._edtxp.runMethod(true,"getLeft"),main.mostCurrent._edtxp.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))}, "++",2, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 54)))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlinsert.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 24)))}, "-",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 116;BA.debugLine="btnInsert.Initialize(\"btnInsert\")";
Debug.ShouldStop(524288);
main.mostCurrent._btninsert.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnInsert")));
 BA.debugLineNum = 117;BA.debugLine="btnInsert.Text = \"Insert to Leaderboard\"";
Debug.ShouldStop(1048576);
main.mostCurrent._btninsert.runMethod(true,"setText",BA.ObjectToCharSequence("Insert to Leaderboard"));
 BA.debugLineNum = 118;BA.debugLine="btnInsert.Color = xui.Color_ARGB(255, 141, 113, 1";
Debug.ShouldStop(2097152);
main.mostCurrent._btninsert.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 141)),(Object)(BA.numberCast(int.class, 113)),(Object)(BA.numberCast(int.class, 176))));
 BA.debugLineNum = 119;BA.debugLine="btnInsert.TextColor = xui.Color_White";
Debug.ShouldStop(4194304);
main.mostCurrent._btninsert.runMethod(true,"setTextColor",main.mostCurrent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 120;BA.debugLine="pnlInsert.AddView(btnInsert, 8dip, 100dip, pnlIns";
Debug.ShouldStop(8388608);
main.mostCurrent._pnlinsert.runVoidMethod ("AddView",(Object)((main.mostCurrent._btninsert.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlinsert.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 16)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 121;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("SetupMockData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,123);
if (RapidSub.canDelegate("setupmockdata")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setupmockdata");}
 BA.debugLineNum = 123;BA.debugLine="Private Sub SetupMockData";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 125;BA.debugLine="InsertUserToDB(\"DAILY\", \"Mika\", 320, 7, 93)";
Debug.ShouldStop(268435456);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Mika"),BA.numberCast(int.class, 320),BA.numberCast(int.class, 7),BA.numberCast(int.class, 93));
 BA.debugLineNum = 126;BA.debugLine="InsertUserToDB(\"DAILY\", \"Rei\", 295, 5, 90)";
Debug.ShouldStop(536870912);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Rei"),BA.numberCast(int.class, 295),BA.numberCast(int.class, 5),BA.numberCast(int.class, 90));
 BA.debugLineNum = 127;BA.debugLine="InsertUserToDB(\"DAILY\", \"Noah\", 280, 4, 88)";
Debug.ShouldStop(1073741824);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Noah"),BA.numberCast(int.class, 280),BA.numberCast(int.class, 4),BA.numberCast(int.class, 88));
 BA.debugLineNum = 128;BA.debugLine="InsertUserToDB(\"DAILY\", \"Ava\", 250, 3, 86)";
Debug.ShouldStop(-2147483648);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Ava"),BA.numberCast(int.class, 250),BA.numberCast(int.class, 3),BA.numberCast(int.class, 86));
 BA.debugLineNum = 129;BA.debugLine="InsertUserToDB(\"DAILY\", \"Luna\", 220, 2, 82)";
Debug.ShouldStop(1);
_insertusertodb(BA.ObjectToString("DAILY"),BA.ObjectToString("Luna"),BA.numberCast(int.class, 220),BA.numberCast(int.class, 2),BA.numberCast(int.class, 82));
 BA.debugLineNum = 132;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Mika\", 1760, 19, 92)";
Debug.ShouldStop(8);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Mika"),BA.numberCast(int.class, 1760),BA.numberCast(int.class, 19),BA.numberCast(int.class, 92));
 BA.debugLineNum = 133;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Ava\", 1690, 13, 91)";
Debug.ShouldStop(16);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Ava"),BA.numberCast(int.class, 1690),BA.numberCast(int.class, 13),BA.numberCast(int.class, 91));
 BA.debugLineNum = 134;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Rei\", 1610, 11, 89)";
Debug.ShouldStop(32);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Rei"),BA.numberCast(int.class, 1610),BA.numberCast(int.class, 11),BA.numberCast(int.class, 89));
 BA.debugLineNum = 135;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Noah\", 1495, 9, 87)";
Debug.ShouldStop(64);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Noah"),BA.numberCast(int.class, 1495),BA.numberCast(int.class, 9),BA.numberCast(int.class, 87));
 BA.debugLineNum = 136;BA.debugLine="InsertUserToDB(\"WEEKLY\", \"Luna\", 1420, 8, 85)";
Debug.ShouldStop(128);
_insertusertodb(BA.ObjectToString("WEEKLY"),BA.ObjectToString("Luna"),BA.numberCast(int.class, 1420),BA.numberCast(int.class, 8),BA.numberCast(int.class, 85));
 BA.debugLineNum = 139;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Ava\", 12850, 41, 90)";
Debug.ShouldStop(1024);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Ava"),BA.numberCast(int.class, 12850),BA.numberCast(int.class, 41),BA.numberCast(int.class, 90));
 BA.debugLineNum = 140;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Mika\", 12110, 37, 91)";
Debug.ShouldStop(2048);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Mika"),BA.numberCast(int.class, 12110),BA.numberCast(int.class, 37),BA.numberCast(int.class, 91));
 BA.debugLineNum = 141;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Noah\", 11680, 29, 88)";
Debug.ShouldStop(4096);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Noah"),BA.numberCast(int.class, 11680),BA.numberCast(int.class, 29),BA.numberCast(int.class, 88));
 BA.debugLineNum = 142;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Rei\", 11300, 24, 87)";
Debug.ShouldStop(8192);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Rei"),BA.numberCast(int.class, 11300),BA.numberCast(int.class, 24),BA.numberCast(int.class, 87));
 BA.debugLineNum = 143;BA.debugLine="InsertUserToDB(\"ALLTIME\", \"Luna\", 10950, 22, 86)";
Debug.ShouldStop(16384);
_insertusertodb(BA.ObjectToString("ALLTIME"),BA.ObjectToString("Luna"),BA.numberCast(int.class, 10950),BA.numberCast(int.class, 22),BA.numberCast(int.class, 86));
 BA.debugLineNum = 144;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setuptheme() throws Exception{
try {
		Debug.PushSubsStack("SetupTheme (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,72);
if (RapidSub.canDelegate("setuptheme")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setuptheme");}
 BA.debugLineNum = 72;BA.debugLine="Private Sub SetupTheme";
Debug.ShouldStop(128);
 BA.debugLineNum = 74;BA.debugLine="Activity.Color = xui.Color_ARGB(255, 245, 239, 22";
Debug.ShouldStop(512);
main.mostCurrent._activity.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 245)),(Object)(BA.numberCast(int.class, 239)),(Object)(BA.numberCast(int.class, 229))));
 BA.debugLineNum = 76;BA.debugLine="pnlHeader.Color = xui.Color_ARGB(255, 107, 92, 14";
Debug.ShouldStop(2048);
main.mostCurrent._pnlheader.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 107)),(Object)(BA.numberCast(int.class, 92)),(Object)(BA.numberCast(int.class, 141))));
 BA.debugLineNum = 77;BA.debugLine="lblTitle.TextColor = xui.Color_White";
Debug.ShouldStop(4096);
main.mostCurrent._lbltitle.runMethod(true,"setTextColor",main.mostCurrent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 78;BA.debugLine="lblSubtitle.TextColor = xui.Color_ARGB(255, 232,";
Debug.ShouldStop(8192);
main.mostCurrent._lblsubtitle.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 232)),(Object)(BA.numberCast(int.class, 224)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 80;BA.debugLine="pnlTopCard.Color = xui.Color_ARGB(255, 255, 247,";
Debug.ShouldStop(32768);
main.mostCurrent._pnltopcard.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 236))));
 BA.debugLineNum = 82;BA.debugLine="lblTopUser.TextColor = xui.Color_ARGB(255, 64, 52";
Debug.ShouldStop(131072);
main.mostCurrent._lbltopuser.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 52)),(Object)(BA.numberCast(int.class, 82))));
 BA.debugLineNum = 83;BA.debugLine="lblTopPoints.TextColor = xui.Color_ARGB(255, 105,";
Debug.ShouldStop(262144);
main.mostCurrent._lbltoppoints.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 105)),(Object)(BA.numberCast(int.class, 89)),(Object)(BA.numberCast(int.class, 128))));
 BA.debugLineNum = 84;BA.debugLine="lblTopStreak.TextColor = xui.Color_ARGB(255, 131,";
Debug.ShouldStop(524288);
main.mostCurrent._lbltopstreak.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 131)),(Object)(BA.numberCast(int.class, 102)),(Object)(BA.numberCast(int.class, 82))));
 BA.debugLineNum = 86;BA.debugLine="btnDaily.Color = xui.Color_ARGB(255, 185, 155, 21";
Debug.ShouldStop(2097152);
main.mostCurrent._btndaily.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 185)),(Object)(BA.numberCast(int.class, 155)),(Object)(BA.numberCast(int.class, 214))));
 BA.debugLineNum = 87;BA.debugLine="btnWeekly.Color = xui.Color_ARGB(255, 185, 155, 2";
Debug.ShouldStop(4194304);
main.mostCurrent._btnweekly.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 185)),(Object)(BA.numberCast(int.class, 155)),(Object)(BA.numberCast(int.class, 214))));
 BA.debugLineNum = 88;BA.debugLine="btnAllTime.Color = xui.Color_ARGB(255, 185, 155,";
Debug.ShouldStop(8388608);
main.mostCurrent._btnalltime.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 185)),(Object)(BA.numberCast(int.class, 155)),(Object)(BA.numberCast(int.class, 214))));
 BA.debugLineNum = 90;BA.debugLine="btnDaily.TextColor = xui.Color_White";
Debug.ShouldStop(33554432);
main.mostCurrent._btndaily.runMethod(true,"setTextColor",main.mostCurrent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 91;BA.debugLine="btnWeekly.TextColor = xui.Color_White";
Debug.ShouldStop(67108864);
main.mostCurrent._btnweekly.runMethod(true,"setTextColor",main.mostCurrent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 92;BA.debugLine="btnAllTime.TextColor = xui.Color_White";
Debug.ShouldStop(134217728);
main.mostCurrent._btnalltime.runMethod(true,"setTextColor",main.mostCurrent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 94;BA.debugLine="lblFooter.TextColor = xui.Color_ARGB(255, 120, 10";
Debug.ShouldStop(536870912);
main.mostCurrent._lblfooter.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 104)),(Object)(BA.numberCast(int.class, 94))));
 BA.debugLineNum = 95;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("ShouldSwapForRanking (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,228);
if (RapidSub.canDelegate("shouldswapforranking")) { return b4a.example.main.remoteMe.runUserSub(false, "main","shouldswapforranking", _a, _b);}
Debug.locals.put("a", _a);
Debug.locals.put("b", _b);
 BA.debugLineNum = 228;BA.debugLine="Private Sub ShouldSwapForRanking(a As UserScore, b";
Debug.ShouldStop(8);
 BA.debugLineNum = 229;BA.debugLine="If b.XP > a.XP Then Return True";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean(">",_b.getField(true,"XP" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"XP" /*RemoteObject*/ )))) { 
if (true) return main.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 230;BA.debugLine="If b.XP = a.XP And b.Streak > a.Streak Then Retur";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",_b.getField(true,"XP" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"XP" /*RemoteObject*/ ))) && RemoteObject.solveBoolean(">",_b.getField(true,"Streak" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"Streak" /*RemoteObject*/ )))) { 
if (true) return main.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 231;BA.debugLine="If b.XP = a.XP And b.Streak = a.Streak And b.Corr";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",_b.getField(true,"XP" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"XP" /*RemoteObject*/ ))) && RemoteObject.solveBoolean("=",_b.getField(true,"Streak" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"Streak" /*RemoteObject*/ ))) && RemoteObject.solveBoolean(">",_b.getField(true,"CorrectRate" /*RemoteObject*/ ),BA.numberCast(double.class, _a.getField(true,"CorrectRate" /*RemoteObject*/ )))) { 
if (true) return main.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 232;BA.debugLine="Return False";
Debug.ShouldStop(128);
if (true) return main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 233;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("ShowBoard (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,235);
if (RapidSub.canDelegate("showboard")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showboard", _mode);}
RemoteObject _source = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _top = RemoteObject.declareNull("b4a.example.main._userscore");
int _i = 0;
RemoteObject _u = RemoteObject.declareNull("b4a.example.main._userscore");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lblrank = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lblname = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lblstats = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("mode", _mode);
 BA.debugLineNum = 235;BA.debugLine="Private Sub ShowBoard(mode As String)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 236;BA.debugLine="mode = mode.ToUpperCase";
Debug.ShouldStop(2048);
_mode = _mode.runMethod(true,"toUpperCase");Debug.locals.put("mode", _mode);
 BA.debugLineNum = 237;BA.debugLine="CurrentMode = mode";
Debug.ShouldStop(4096);
main.mostCurrent._currentmode = _mode;
 BA.debugLineNum = 238;BA.debugLine="Dim source As List";
Debug.ShouldStop(8192);
_source = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("source", _source);
 BA.debugLineNum = 239;BA.debugLine="Select mode";
Debug.ShouldStop(16384);
switch (BA.switchObjectToInt(_mode,BA.ObjectToString("DAILY"),BA.ObjectToString("WEEKLY"))) {
case 0: {
 BA.debugLineNum = 241;BA.debugLine="source = DailyList";
Debug.ShouldStop(65536);
_source = main.mostCurrent._dailylist;Debug.locals.put("source", _source);
 BA.debugLineNum = 242;BA.debugLine="lblSubtitle.Text = \"Today's top flashcard learn";
Debug.ShouldStop(131072);
main.mostCurrent._lblsubtitle.runMethod(true,"setText",BA.ObjectToCharSequence("Today's top flashcard learners"));
 BA.debugLineNum = 243;BA.debugLine="HighlightButton(btnDaily)";
Debug.ShouldStop(262144);
_highlightbutton(main.mostCurrent._btndaily);
 break; }
case 1: {
 BA.debugLineNum = 245;BA.debugLine="source = WeeklyList";
Debug.ShouldStop(1048576);
_source = main.mostCurrent._weeklylist;Debug.locals.put("source", _source);
 BA.debugLineNum = 246;BA.debugLine="lblSubtitle.Text = \"Weekly focus champions\"";
Debug.ShouldStop(2097152);
main.mostCurrent._lblsubtitle.runMethod(true,"setText",BA.ObjectToCharSequence("Weekly focus champions"));
 BA.debugLineNum = 247;BA.debugLine="HighlightButton(btnWeekly)";
Debug.ShouldStop(4194304);
_highlightbutton(main.mostCurrent._btnweekly);
 break; }
default: {
 BA.debugLineNum = 249;BA.debugLine="source = AllTimeList";
Debug.ShouldStop(16777216);
_source = main.mostCurrent._alltimelist;Debug.locals.put("source", _source);
 BA.debugLineNum = 250;BA.debugLine="lblSubtitle.Text = \"All-time cozy legends\"";
Debug.ShouldStop(33554432);
main.mostCurrent._lblsubtitle.runMethod(true,"setText",BA.ObjectToCharSequence("All-time cozy legends"));
 BA.debugLineNum = 251;BA.debugLine="HighlightButton(btnAllTime)";
Debug.ShouldStop(67108864);
_highlightbutton(main.mostCurrent._btnalltime);
 break; }
}
;
 BA.debugLineNum = 254;BA.debugLine="LoadBoardFromDB(mode)";
Debug.ShouldStop(536870912);
_loadboardfromdb(_mode);
 BA.debugLineNum = 255;BA.debugLine="If source.Size = 0 Then Return";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",_source.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 258;BA.debugLine="Dim top As UserScore = source.Get(0)";
Debug.ShouldStop(2);
_top = (_source.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("top", _top);Debug.locals.put("top", _top);
 BA.debugLineNum = 259;BA.debugLine="lblTopUser.Text = \"🏆 \" & top.Name";
Debug.ShouldStop(4);
main.mostCurrent._lbltopuser.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("🏆 "),_top.getField(true,"Name" /*RemoteObject*/ ))));
 BA.debugLineNum = 260;BA.debugLine="lblTopPoints.Text = top.XP & \" XP\"";
Debug.ShouldStop(8);
main.mostCurrent._lbltoppoints.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_top.getField(true,"XP" /*RemoteObject*/ ),RemoteObject.createImmutable(" XP"))));
 BA.debugLineNum = 261;BA.debugLine="lblTopStreak.Text = \"🔥 \" & top.Streak & \" day st";
Debug.ShouldStop(16);
main.mostCurrent._lbltopstreak.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("🔥 "),_top.getField(true,"Streak" /*RemoteObject*/ ),RemoteObject.createImmutable(" day streak · "),_top.getField(true,"CorrectRate" /*RemoteObject*/ ),RemoteObject.createImmutable("% correct"))));
 BA.debugLineNum = 264;BA.debugLine="clvBoard.Clear";
Debug.ShouldStop(128);
main.mostCurrent._clvboard.runVoidMethod ("_clear");
 BA.debugLineNum = 265;BA.debugLine="For i = 0 To source.Size - 1";
Debug.ShouldStop(256);
{
final int step25 = 1;
final int limit25 = RemoteObject.solve(new RemoteObject[] {_source.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step25 > 0 && _i <= limit25) || (step25 < 0 && _i >= limit25) ;_i = ((int)(0 + _i + step25))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 266;BA.debugLine="Dim u As UserScore = source.Get(i)";
Debug.ShouldStop(512);
_u = (_source.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("u", _u);Debug.locals.put("u", _u);
 BA.debugLineNum = 267;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(1024);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 268;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(2048);
_p.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 269;BA.debugLine="p.Color = xui.Color_ARGB(255, 255, 250, 242)";
Debug.ShouldStop(4096);
_p.runVoidMethod ("setColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 250)),(Object)(BA.numberCast(int.class, 242))));
 BA.debugLineNum = 270;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, clvBoard.AsView.Wid";
Debug.ShouldStop(8192);
_p.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getWidth")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 272;BA.debugLine="Dim lblRank As Label";
Debug.ShouldStop(32768);
_lblrank = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblRank", _lblrank);
 BA.debugLineNum = 273;BA.debugLine="lblRank.Initialize(\"\")";
Debug.ShouldStop(65536);
_lblrank.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 274;BA.debugLine="lblRank.Text = \"#\" & (i + 1)";
Debug.ShouldStop(131072);
_lblrank.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("#"),(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1)))));
 BA.debugLineNum = 275;BA.debugLine="lblRank.TextSize = 15";
Debug.ShouldStop(262144);
_lblrank.runMethod(true,"setTextSize",BA.numberCast(float.class, 15));
 BA.debugLineNum = 276;BA.debugLine="lblRank.TextColor = xui.Color_ARGB(255, 84, 72,";
Debug.ShouldStop(524288);
_lblrank.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 84)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 101))));
 BA.debugLineNum = 277;BA.debugLine="lblRank.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(1048576);
_lblrank.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 278;BA.debugLine="p.AddView(lblRank, 10dip, 0, 40dip, 62dip)";
Debug.ShouldStop(2097152);
_p.runVoidMethod ("AddView",(Object)((_lblrank.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 280;BA.debugLine="Dim lblName As Label";
Debug.ShouldStop(8388608);
_lblname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblName", _lblname);
 BA.debugLineNum = 281;BA.debugLine="lblName.Initialize(\"\")";
Debug.ShouldStop(16777216);
_lblname.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 282;BA.debugLine="lblName.Text = u.Name";
Debug.ShouldStop(33554432);
_lblname.runMethod(true,"setText",BA.ObjectToCharSequence(_u.getField(true,"Name" /*RemoteObject*/ )));
 BA.debugLineNum = 283;BA.debugLine="lblName.TextSize = 16";
Debug.ShouldStop(67108864);
_lblname.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 284;BA.debugLine="lblName.TextColor = xui.Color_ARGB(255, 62, 50,";
Debug.ShouldStop(134217728);
_lblname.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 62)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 285;BA.debugLine="lblName.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(268435456);
_lblname.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 286;BA.debugLine="p.AddView(lblName, 55dip, 0, 110dip, 62dip)";
Debug.ShouldStop(536870912);
_p.runVoidMethod ("AddView",(Object)((_lblname.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 110)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 288;BA.debugLine="Dim lblStats As Label";
Debug.ShouldStop(-2147483648);
_lblstats = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblStats", _lblstats);
 BA.debugLineNum = 289;BA.debugLine="lblStats.Initialize(\"\")";
Debug.ShouldStop(1);
_lblstats.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 290;BA.debugLine="lblStats.Text = u.XP & \" XP   •   🔥\" & u.Streak";
Debug.ShouldStop(2);
_lblstats.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_u.getField(true,"XP" /*RemoteObject*/ ),RemoteObject.createImmutable(" XP   •   🔥"),_u.getField(true,"Streak" /*RemoteObject*/ ),RemoteObject.createImmutable("   •   "),_u.getField(true,"CorrectRate" /*RemoteObject*/ ),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 291;BA.debugLine="lblStats.TextSize = 13";
Debug.ShouldStop(4);
_lblstats.runMethod(true,"setTextSize",BA.numberCast(float.class, 13));
 BA.debugLineNum = 292;BA.debugLine="lblStats.TextColor = xui.Color_ARGB(255, 122, 98";
Debug.ShouldStop(8);
_lblstats.runMethod(true,"setTextColor",main.mostCurrent._xui.runMethod(true,"Color_ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 82))));
 BA.debugLineNum = 293;BA.debugLine="lblStats.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(16);
_lblstats.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 294;BA.debugLine="p.AddView(lblStats, 165dip, 0, clvBoard.AsView.W";
Debug.ShouldStop(32);
_p.runVoidMethod ("AddView",(Object)((_lblstats.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 165)))),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._clvboard.runMethod(false,"_asview").runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 175)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 62)))));
 BA.debugLineNum = 296;BA.debugLine="clvBoard.Add(p, u.Name)";
Debug.ShouldStop(128);
main.mostCurrent._clvboard.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject()),(Object)((_u.getField(true,"Name" /*RemoteObject*/ ))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 299;BA.debugLine="lblFooter.Text = \"Keep reviewing your flashcards";
Debug.ShouldStop(1024);
main.mostCurrent._lblfooter.runMethod(true,"setText",BA.ObjectToCharSequence("Keep reviewing your flashcards to climb the cozy board ✨"));
 BA.debugLineNum = 300;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("SortBoard (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,215);
if (RapidSub.canDelegate("sortboard")) { return b4a.example.main.remoteMe.runUserSub(false, "main","sortboard", _board);}
int _i = 0;
int _j = 0;
RemoteObject _a = RemoteObject.declareNull("b4a.example.main._userscore");
RemoteObject _b = RemoteObject.declareNull("b4a.example.main._userscore");
Debug.locals.put("board", _board);
 BA.debugLineNum = 215;BA.debugLine="Private Sub SortBoard(board As List)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 216;BA.debugLine="For i = 0 To board.Size - 2";
Debug.ShouldStop(8388608);
{
final int step1 = 1;
final int limit1 = RemoteObject.solve(new RemoteObject[] {_board.runMethod(true,"getSize"),RemoteObject.createImmutable(2)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1) ;_i = ((int)(0 + _i + step1))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 217;BA.debugLine="For j = i + 1 To board.Size - 1";
Debug.ShouldStop(16777216);
{
final int step2 = 1;
final int limit2 = RemoteObject.solve(new RemoteObject[] {_board.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_j = RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1).<Integer>get().intValue() ;
for (;(step2 > 0 && _j <= limit2) || (step2 < 0 && _j >= limit2) ;_j = ((int)(0 + _j + step2))  ) {
Debug.locals.put("j", _j);
 BA.debugLineNum = 218;BA.debugLine="Dim a As UserScore = board.Get(i)";
Debug.ShouldStop(33554432);
_a = (_board.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("a", _a);Debug.locals.put("a", _a);
 BA.debugLineNum = 219;BA.debugLine="Dim b As UserScore = board.Get(j)";
Debug.ShouldStop(67108864);
_b = (_board.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _j))));Debug.locals.put("b", _b);Debug.locals.put("b", _b);
 BA.debugLineNum = 220;BA.debugLine="If ShouldSwapForRanking(a, b) Then";
Debug.ShouldStop(134217728);
if (_shouldswapforranking(_a,_b).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 221;BA.debugLine="board.Set(i, b)";
Debug.ShouldStop(268435456);
_board.runVoidMethod ("Set",(Object)(BA.numberCast(int.class, _i)),(Object)((_b)));
 BA.debugLineNum = 222;BA.debugLine="board.Set(j, a)";
Debug.ShouldStop(536870912);
_board.runVoidMethod ("Set",(Object)(BA.numberCast(int.class, _j)),(Object)((_a)));
 };
 }
}Debug.locals.put("j", _j);
;
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 226;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}