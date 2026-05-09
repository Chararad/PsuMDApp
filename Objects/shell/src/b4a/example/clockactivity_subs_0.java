package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class clockactivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,40);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 40;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(256);
switch (BA.switchObjectToInt(clockactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 43;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",clockactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,clockactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"clocklayout\")";
Debug.ShouldStop(2048);
clockactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("clocklayout")),clockactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 46;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark\")";
Debug.ShouldStop(8192);
clockactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("clocklayoutDark")),clockactivity.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 49;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",clockactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,clockactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 50;BA.debugLine="Activity.LoadLayout(\"clocklayout2\")";
Debug.ShouldStop(131072);
clockactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("clocklayout2")),clockactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 52;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark2\")";
Debug.ShouldStop(524288);
clockactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("clocklayoutDark2")),clockactivity.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 55;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",clockactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,clockactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 56;BA.debugLine="Activity.LoadLayout(\"clocklayout3\")";
Debug.ShouldStop(8388608);
clockactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("clocklayout3")),clockactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 58;BA.debugLine="Activity.LoadLayout(\"clocklayoutDark3\")";
Debug.ShouldStop(33554432);
clockactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("clocklayoutDark3")),clockactivity.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 62;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(536870912);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 63;BA.debugLine="timerCount.Initialize(\"tmr\", 1000)";
Debug.ShouldStop(1073741824);
clockactivity._timercount.runVoidMethod ("Initialize",clockactivity.processBA,(Object)(BA.ObjectToString("tmr")),(Object)(BA.numberCast(long.class, 1000)));
 };
 BA.debugLineNum = 66;BA.debugLine="timerCount.Enabled = False";
Debug.ShouldStop(2);
clockactivity._timercount.runMethod(true,"setEnabled",clockactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 67;BA.debugLine="updateLbl";
Debug.ShouldStop(4);
_updatelbl();
 BA.debugLineNum = 68;BA.debugLine="pomoCounter.Text = counter";
Debug.ShouldStop(8);
clockactivity.mostCurrent._pomocounter.runMethod(true,"setText",BA.ObjectToCharSequence(clockactivity._counter));
 BA.debugLineNum = 69;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("Activity_Pause (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,75);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 75;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1024);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,71);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","activity_resume");}
 BA.debugLineNum = 71;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(64);
 BA.debugLineNum = 73;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _closel_click() throws Exception{
try {
		Debug.PushSubsStack("closeL_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,244);
if (RapidSub.canDelegate("closel_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","closel_click");}
 BA.debugLineNum = 244;BA.debugLine="Private Sub closeL_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 245;BA.debugLine="settingsPnl.Visible = False";
Debug.ShouldStop(1048576);
clockactivity.mostCurrent._settingspnl.runMethod(true,"setVisible",clockactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 246;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _exitbtn_click() throws Exception{
try {
		Debug.PushSubsStack("exitBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,79);
if (RapidSub.canDelegate("exitbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","exitbtn_click");}
 BA.debugLineNum = 79;BA.debugLine="Private Sub exitBtn_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 80;BA.debugLine="Activity.Finish";
Debug.ShouldStop(32768);
clockactivity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 81;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _formatbtn_click() throws Exception{
try {
		Debug.PushSubsStack("formatBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,83);
if (RapidSub.canDelegate("formatbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","formatbtn_click");}
 BA.debugLineNum = 83;BA.debugLine="Private Sub formatBtn_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 85;BA.debugLine="MainActivity.format24h = Not(MainActivity.format2";
Debug.ShouldStop(1048576);
clockactivity.mostCurrent._mainactivity._format24h /*RemoteObject*/  = clockactivity.mostCurrent.__c.runMethod(true,"Not",(Object)(clockactivity.mostCurrent._mainactivity._format24h /*RemoteObject*/ ));
 BA.debugLineNum = 87;BA.debugLine="If MainActivity.format24h Then";
Debug.ShouldStop(4194304);
if (clockactivity.mostCurrent._mainactivity._format24h /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 88;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
Debug.ShouldStop(8388608);
clockactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setTimeFormat",BA.ObjectToString("HH:mm"));
 }else {
 BA.debugLineNum = 90;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
Debug.ShouldStop(33554432);
clockactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setTimeFormat",BA.ObjectToString("hh:mm a"));
 };
 BA.debugLineNum = 92;BA.debugLine="CallSub(MainActivity, \"timerClock_Tick\")";
Debug.ShouldStop(134217728);
clockactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",clockactivity.processBA,(Object)((clockactivity.mostCurrent._mainactivity.getObject())),(Object)(RemoteObject.createImmutable("timerClock_Tick")));
 BA.debugLineNum = 94;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private pomotimerLbl As Label";
clockactivity.mostCurrent._pomotimerlbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private playBtn As Button";
clockactivity.mostCurrent._playbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private sessionLbl As Label";
clockactivity.mostCurrent._sessionlbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private pomoCounter As Label";
clockactivity.mostCurrent._pomocounter = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private settingsBtn As Button";
clockactivity.mostCurrent._settingsbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private settingsPnl As B4XView";
clockactivity.mostCurrent._settingspnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private pomoTxt As EditText";
clockactivity.mostCurrent._pomotxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private shortTxt As EditText";
clockactivity.mostCurrent._shorttxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private longTxt As EditText";
clockactivity.mostCurrent._longtxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Dim timerState As Int = 0";
clockactivity._timerstate = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 29;BA.debugLine="Dim counter As Int = 0";
clockactivity._counter = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 30;BA.debugLine="Dim pomoDef As Int = 1500";
clockactivity._pomodef = BA.numberCast(int.class, 1500);
 //BA.debugLineNum = 31;BA.debugLine="Dim shortDef As Int = 300";
clockactivity._shortdef = BA.numberCast(int.class, 300);
 //BA.debugLineNum = 32;BA.debugLine="Dim longDef As Int = 900";
clockactivity._longdef = BA.numberCast(int.class, 900);
 //BA.debugLineNum = 33;BA.debugLine="Dim centerLeft As Int = 100dip";
clockactivity._centerleft = clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)));
 //BA.debugLineNum = 34;BA.debugLine="Dim centerTop As Int = 225dip";
clockactivity._centertop = clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 225)));
 //BA.debugLineNum = 35;BA.debugLine="Dim playing As Boolean = False";
clockactivity._playing = clockactivity.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 36;BA.debugLine="Dim break As Int = 0";
clockactivity._break = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _longbtn_click() throws Exception{
try {
		Debug.PushSubsStack("longBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,231);
if (RapidSub.canDelegate("longbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","longbtn_click");}
 BA.debugLineNum = 231;BA.debugLine="Private Sub longBtn_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 232;BA.debugLine="timerStop";
Debug.ShouldStop(128);
_timerstop();
 BA.debugLineNum = 233;BA.debugLine="secondsRemain = longDef";
Debug.ShouldStop(256);
clockactivity._secondsremain = clockactivity._longdef;
 BA.debugLineNum = 234;BA.debugLine="timerState = 1";
Debug.ShouldStop(512);
clockactivity._timerstate = BA.numberCast(int.class, 1);
 BA.debugLineNum = 235;BA.debugLine="break = 1";
Debug.ShouldStop(1024);
clockactivity._break = BA.numberCast(int.class, 1);
 BA.debugLineNum = 236;BA.debugLine="updateLbl";
Debug.ShouldStop(2048);
_updatelbl();
 BA.debugLineNum = 237;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _playbtn_click() throws Exception{
try {
		Debug.PushSubsStack("playBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,96);
if (RapidSub.canDelegate("playbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","playbtn_click");}
 BA.debugLineNum = 96;BA.debugLine="Private Sub playBtn_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 97;BA.debugLine="If secondsRemain > 0 Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean(">",clockactivity._secondsremain,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 98;BA.debugLine="timerCount.Enabled = True";
Debug.ShouldStop(2);
clockactivity._timercount.runMethod(true,"setEnabled",clockactivity.mostCurrent.__c.getField(true,"True"));
 };
 BA.debugLineNum = 100;BA.debugLine="If playing = True Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",clockactivity._playing,clockactivity.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 101;BA.debugLine="timerStop";
Debug.ShouldStop(16);
_timerstop();
 }else {
 BA.debugLineNum = 103;BA.debugLine="timerCount.Enabled = True";
Debug.ShouldStop(64);
clockactivity._timercount.runMethod(true,"setEnabled",clockactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 104;BA.debugLine="playing = True";
Debug.ShouldStop(128);
clockactivity._playing = clockactivity.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 105;BA.debugLine="playBtn.Text = \"Pause\"";
Debug.ShouldStop(256);
clockactivity.mostCurrent._playbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Pause"));
 };
 BA.debugLineNum = 108;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pomobtn_click() throws Exception{
try {
		Debug.PushSubsStack("pomoBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,216);
if (RapidSub.canDelegate("pomobtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","pomobtn_click");}
 BA.debugLineNum = 216;BA.debugLine="Private Sub pomoBtn_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 217;BA.debugLine="timerStop";
Debug.ShouldStop(16777216);
_timerstop();
 BA.debugLineNum = 218;BA.debugLine="secondsRemain = pomoDef";
Debug.ShouldStop(33554432);
clockactivity._secondsremain = clockactivity._pomodef;
 BA.debugLineNum = 219;BA.debugLine="timerState = 0";
Debug.ShouldStop(67108864);
clockactivity._timerstate = BA.numberCast(int.class, 0);
 BA.debugLineNum = 220;BA.debugLine="updateLbl";
Debug.ShouldStop(134217728);
_updatelbl();
 BA.debugLineNum = 221;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
 //BA.debugLineNum = 9;BA.debugLine="Private timerCount As Timer";
clockactivity._timercount = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
clockactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 11;BA.debugLine="Private secondsRemain As Int = 1500";
clockactivity._secondsremain = BA.numberCast(int.class, 1500);
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _savebtn_click() throws Exception{
try {
		Debug.PushSubsStack("saveBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,248);
if (RapidSub.canDelegate("savebtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","savebtn_click");}
 BA.debugLineNum = 248;BA.debugLine="Private Sub saveBtn_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 250;BA.debugLine="If IsNumber(pomoTxt.Text) Then pomoDef = pomoTxt.";
Debug.ShouldStop(33554432);
if (clockactivity.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(clockactivity.mostCurrent._pomotxt.runMethod(true,"getText"))).<Boolean>get().booleanValue()) { 
clockactivity._pomodef = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, clockactivity.mostCurrent._pomotxt.runMethod(true,"getText")),RemoteObject.createImmutable(60)}, "*",0, 0));};
 BA.debugLineNum = 251;BA.debugLine="If IsNumber(shortTxt.Text) Then shortDef = shortT";
Debug.ShouldStop(67108864);
if (clockactivity.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(clockactivity.mostCurrent._shorttxt.runMethod(true,"getText"))).<Boolean>get().booleanValue()) { 
clockactivity._shortdef = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, clockactivity.mostCurrent._shorttxt.runMethod(true,"getText")),RemoteObject.createImmutable(60)}, "*",0, 0));};
 BA.debugLineNum = 252;BA.debugLine="If IsNumber(longTxt.Text) Then longDef = longTxt.";
Debug.ShouldStop(134217728);
if (clockactivity.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(clockactivity.mostCurrent._longtxt.runMethod(true,"getText"))).<Boolean>get().booleanValue()) { 
clockactivity._longdef = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, clockactivity.mostCurrent._longtxt.runMethod(true,"getText")),RemoteObject.createImmutable(60)}, "*",0, 0));};
 BA.debugLineNum = 254;BA.debugLine="If timerState = 0 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",clockactivity._timerstate,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 255;BA.debugLine="secondsRemain = pomoDef";
Debug.ShouldStop(1073741824);
clockactivity._secondsremain = clockactivity._pomodef;
 }else {
 BA.debugLineNum = 258;BA.debugLine="If break = 1 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",clockactivity._break,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 259;BA.debugLine="secondsRemain = longDef";
Debug.ShouldStop(4);
clockactivity._secondsremain = clockactivity._longdef;
 }else {
 BA.debugLineNum = 261;BA.debugLine="secondsRemain = shortDef";
Debug.ShouldStop(16);
clockactivity._secondsremain = clockactivity._shortdef;
 };
 };
 BA.debugLineNum = 264;BA.debugLine="updateLbl";
Debug.ShouldStop(128);
_updatelbl();
 BA.debugLineNum = 265;BA.debugLine="settingsPnl.Visible = False";
Debug.ShouldStop(256);
clockactivity.mostCurrent._settingspnl.runMethod(true,"setVisible",clockactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 266;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _settingsbtn_click() throws Exception{
try {
		Debug.PushSubsStack("settingsBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,239);
if (RapidSub.canDelegate("settingsbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","settingsbtn_click");}
 BA.debugLineNum = 239;BA.debugLine="Private Sub settingsBtn_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 240;BA.debugLine="settingsWindow(250dip, 180dip)";
Debug.ShouldStop(32768);
_settingswindow(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250))),clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 241;BA.debugLine="settingsPnl.Visible = True";
Debug.ShouldStop(65536);
clockactivity.mostCurrent._settingspnl.runMethod(true,"setVisible",clockactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 242;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _settingswindow(RemoteObject _pw,RemoteObject _ph) throws Exception{
try {
		Debug.PushSubsStack("settingsWindow (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,155);
if (RapidSub.canDelegate("settingswindow")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","settingswindow", _pw, _ph);}
RemoteObject _lblp = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lbls = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lbll = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _closel = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _savebtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
Debug.locals.put("pW", _pw);
Debug.locals.put("pH", _ph);
 BA.debugLineNum = 155;BA.debugLine="Private Sub settingsWindow(pW As Int, pH As Int)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 156;BA.debugLine="settingsPnl = xui.CreatePanel(\"settingsPnl\")";
Debug.ShouldStop(134217728);
clockactivity.mostCurrent._settingspnl = clockactivity._xui.runMethod(false,"CreatePanel",clockactivity.processBA,(Object)(RemoteObject.createImmutable("settingsPnl")));
 BA.debugLineNum = 157;BA.debugLine="Activity.AddView(settingsPnl, centerLeft, centerT";
Debug.ShouldStop(268435456);
clockactivity.mostCurrent._activity.runVoidMethod ("AddView",(Object)((clockactivity.mostCurrent._settingspnl.getObject())),(Object)(clockactivity._centerleft),(Object)(clockactivity._centertop),(Object)(_pw),(Object)(_ph));
 BA.debugLineNum = 158;BA.debugLine="settingsPnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(536870912);
clockactivity.mostCurrent._settingspnl.runMethod(true,"setColor",clockactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 159;BA.debugLine="settingsPnl.SetColorAndBorder(xui.Color_White, 2d";
Debug.ShouldStop(1073741824);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("SetColorAndBorder",(Object)(clockactivity._xui.getField(true,"Color_White")),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(clockactivity._xui.getField(true,"Color_Black")),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 160;BA.debugLine="settingsPnl.Enabled = False";
Debug.ShouldStop(-2147483648);
clockactivity.mostCurrent._settingspnl.runMethod(true,"setEnabled",clockactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 161;BA.debugLine="settingsPnl.Visible = False";
Debug.ShouldStop(1);
clockactivity.mostCurrent._settingspnl.runMethod(true,"setVisible",clockactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 163;BA.debugLine="pomoTxt.Initialize(\"pomoTxt\")";
Debug.ShouldStop(4);
clockactivity.mostCurrent._pomotxt.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pomoTxt")));
 BA.debugLineNum = 164;BA.debugLine="pomoTxt.Hint = \"Pomo\"";
Debug.ShouldStop(8);
clockactivity.mostCurrent._pomotxt.runMethod(true,"setHint",BA.ObjectToString("Pomo"));
 BA.debugLineNum = 165;BA.debugLine="pomoTxt.InputType = pomoTxt.INPUT_TYPE_NUMBERS";
Debug.ShouldStop(16);
clockactivity.mostCurrent._pomotxt.runMethod(true,"setInputType",clockactivity.mostCurrent._pomotxt.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 166;BA.debugLine="pomoTxt.Text = pomoDef / 60";
Debug.ShouldStop(32);
clockactivity.mostCurrent._pomotxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(RemoteObject.solve(new RemoteObject[] {clockactivity._pomodef,RemoteObject.createImmutable(60)}, "/",0, 0)));
 BA.debugLineNum = 167;BA.debugLine="pomoTxt.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(64);
clockactivity.mostCurrent._pomotxt.runMethod(true,"setGravity",clockactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 168;BA.debugLine="settingsPnl.AddView(pomoTxt, 10dip, 40dip, 70dip,";
Debug.ShouldStop(128);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((clockactivity.mostCurrent._pomotxt.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 170;BA.debugLine="shortTxt.Initialize(\"shortTxt\")";
Debug.ShouldStop(512);
clockactivity.mostCurrent._shorttxt.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("shortTxt")));
 BA.debugLineNum = 171;BA.debugLine="shortTxt.Hint = \"Short\"";
Debug.ShouldStop(1024);
clockactivity.mostCurrent._shorttxt.runMethod(true,"setHint",BA.ObjectToString("Short"));
 BA.debugLineNum = 172;BA.debugLine="shortTxt.InputType = shortTxt.INPUT_TYPE_NUMBERS";
Debug.ShouldStop(2048);
clockactivity.mostCurrent._shorttxt.runMethod(true,"setInputType",clockactivity.mostCurrent._shorttxt.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 173;BA.debugLine="shortTxt.Text = shortDef / 60";
Debug.ShouldStop(4096);
clockactivity.mostCurrent._shorttxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(RemoteObject.solve(new RemoteObject[] {clockactivity._shortdef,RemoteObject.createImmutable(60)}, "/",0, 0)));
 BA.debugLineNum = 174;BA.debugLine="shortTxt.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(8192);
clockactivity.mostCurrent._shorttxt.runMethod(true,"setGravity",clockactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 175;BA.debugLine="settingsPnl.AddView(shortTxt, 90dip, 40dip, 70dip";
Debug.ShouldStop(16384);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((clockactivity.mostCurrent._shorttxt.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 177;BA.debugLine="longTxt.Initialize(\"longTxt\")";
Debug.ShouldStop(65536);
clockactivity.mostCurrent._longtxt.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("longTxt")));
 BA.debugLineNum = 178;BA.debugLine="longTxt.Hint = \"Long\"";
Debug.ShouldStop(131072);
clockactivity.mostCurrent._longtxt.runMethod(true,"setHint",BA.ObjectToString("Long"));
 BA.debugLineNum = 179;BA.debugLine="longTxt.InputType = longTxt.INPUT_TYPE_NUMBERS";
Debug.ShouldStop(262144);
clockactivity.mostCurrent._longtxt.runMethod(true,"setInputType",clockactivity.mostCurrent._longtxt.getField(true,"INPUT_TYPE_NUMBERS"));
 BA.debugLineNum = 180;BA.debugLine="longTxt.Text = longDef / 60";
Debug.ShouldStop(524288);
clockactivity.mostCurrent._longtxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(RemoteObject.solve(new RemoteObject[] {clockactivity._longdef,RemoteObject.createImmutable(60)}, "/",0, 0)));
 BA.debugLineNum = 181;BA.debugLine="longTxt.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(1048576);
clockactivity.mostCurrent._longtxt.runMethod(true,"setGravity",clockactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 182;BA.debugLine="settingsPnl.AddView(longTxt, 170dip, 40dip, 70dip";
Debug.ShouldStop(2097152);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((clockactivity.mostCurrent._longtxt.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 170)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 184;BA.debugLine="Dim lblP, lblS, lblL As Label";
Debug.ShouldStop(8388608);
_lblp = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblP", _lblp);
_lbls = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblS", _lbls);
_lbll = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblL", _lbll);
 BA.debugLineNum = 186;BA.debugLine="lblP.Initialize(\"\")";
Debug.ShouldStop(33554432);
_lblp.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 187;BA.debugLine="lblP.Text = \"Pomo\"";
Debug.ShouldStop(67108864);
_lblp.runMethod(true,"setText",BA.ObjectToCharSequence("Pomo"));
 BA.debugLineNum = 188;BA.debugLine="lblP.TextSize = 12";
Debug.ShouldStop(134217728);
_lblp.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 189;BA.debugLine="lblP.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(268435456);
_lblp.runMethod(true,"setGravity",clockactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 190;BA.debugLine="settingsPnl.AddView(lblP, 10dip, 80dip, 70dip, 20";
Debug.ShouldStop(536870912);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((_lblp.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))));
 BA.debugLineNum = 192;BA.debugLine="lblS.Initialize(\"\")";
Debug.ShouldStop(-2147483648);
_lbls.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 193;BA.debugLine="lblS.Text = \"Short\"";
Debug.ShouldStop(1);
_lbls.runMethod(true,"setText",BA.ObjectToCharSequence("Short"));
 BA.debugLineNum = 194;BA.debugLine="lblS.TextSize = 12";
Debug.ShouldStop(2);
_lbls.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 195;BA.debugLine="lblS.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(4);
_lbls.runMethod(true,"setGravity",clockactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 196;BA.debugLine="settingsPnl.AddView(lblS, 90dip, 80dip, 70dip, 20";
Debug.ShouldStop(8);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((_lbls.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))));
 BA.debugLineNum = 198;BA.debugLine="lblL.Initialize(\"\")";
Debug.ShouldStop(32);
_lbll.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 199;BA.debugLine="lblL.Text = \"Long\"";
Debug.ShouldStop(64);
_lbll.runMethod(true,"setText",BA.ObjectToCharSequence("Long"));
 BA.debugLineNum = 200;BA.debugLine="lblL.TextSize = 12";
Debug.ShouldStop(128);
_lbll.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 201;BA.debugLine="lblL.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(256);
_lbll.runMethod(true,"setGravity",clockactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 202;BA.debugLine="settingsPnl.AddView(lblL, 170dip, 80dip, 70dip, 2";
Debug.ShouldStop(512);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((_lbll.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 170)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))));
 BA.debugLineNum = 204;BA.debugLine="Dim closeL As Label";
Debug.ShouldStop(2048);
_closel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("closeL", _closel);
 BA.debugLineNum = 205;BA.debugLine="closeL.Initialize(\"closeL\")";
Debug.ShouldStop(4096);
_closel.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("closeL")));
 BA.debugLineNum = 206;BA.debugLine="settingsPnl.AddView(closeL, 10dip, 10dip, 20dip,";
Debug.ShouldStop(8192);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((_closel.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))));
 BA.debugLineNum = 207;BA.debugLine="closeL.Text = \"X\"";
Debug.ShouldStop(16384);
_closel.runMethod(true,"setText",BA.ObjectToCharSequence("X"));
 BA.debugLineNum = 209;BA.debugLine="Dim saveBtn As Button";
Debug.ShouldStop(65536);
_savebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("saveBtn", _savebtn);
 BA.debugLineNum = 210;BA.debugLine="saveBtn.Initialize(\"saveBtn\")";
Debug.ShouldStop(131072);
_savebtn.runVoidMethod ("Initialize",clockactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("saveBtn")));
 BA.debugLineNum = 211;BA.debugLine="saveBtn.Text = \"Save Settings\"";
Debug.ShouldStop(262144);
_savebtn.runMethod(true,"setText",BA.ObjectToCharSequence("Save Settings"));
 BA.debugLineNum = 212;BA.debugLine="settingsPnl.AddView(saveBtn, 10dip, 130dip, 230di";
Debug.ShouldStop(524288);
clockactivity.mostCurrent._settingspnl.runVoidMethod ("AddView",(Object)((_savebtn.getObject())),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 130)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 230)))),(Object)(clockactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 214;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _shortbtn_click() throws Exception{
try {
		Debug.PushSubsStack("shortBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,223);
if (RapidSub.canDelegate("shortbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","shortbtn_click");}
 BA.debugLineNum = 223;BA.debugLine="Private Sub shortBtn_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 224;BA.debugLine="timerStop";
Debug.ShouldStop(-2147483648);
_timerstop();
 BA.debugLineNum = 225;BA.debugLine="secondsRemain = shortDef";
Debug.ShouldStop(1);
clockactivity._secondsremain = clockactivity._shortdef;
 BA.debugLineNum = 226;BA.debugLine="timerState = 1";
Debug.ShouldStop(2);
clockactivity._timerstate = BA.numberCast(int.class, 1);
 BA.debugLineNum = 227;BA.debugLine="break = 0";
Debug.ShouldStop(4);
clockactivity._break = BA.numberCast(int.class, 0);
 BA.debugLineNum = 228;BA.debugLine="updateLbl";
Debug.ShouldStop(8);
_updatelbl();
 BA.debugLineNum = 229;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _skipbtn_click() throws Exception{
try {
		Debug.PushSubsStack("skipBtn_Click (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,268);
if (RapidSub.canDelegate("skipbtn_click")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","skipbtn_click");}
 BA.debugLineNum = 268;BA.debugLine="Private Sub skipBtn_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 269;BA.debugLine="If timerState = 0 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",clockactivity._timerstate,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 270;BA.debugLine="counter = counter + 1";
Debug.ShouldStop(8192);
clockactivity._counter = RemoteObject.solve(new RemoteObject[] {clockactivity._counter,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 272;BA.debugLine="If counter Mod 4 = 0 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",RemoteObject.solve(new RemoteObject[] {clockactivity._counter,RemoteObject.createImmutable(4)}, "%",0, 1),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 273;BA.debugLine="secondsRemain = longDef";
Debug.ShouldStop(65536);
clockactivity._secondsremain = clockactivity._longdef;
 BA.debugLineNum = 274;BA.debugLine="break = 1";
Debug.ShouldStop(131072);
clockactivity._break = BA.numberCast(int.class, 1);
 }else {
 BA.debugLineNum = 276;BA.debugLine="secondsRemain = shortDef";
Debug.ShouldStop(524288);
clockactivity._secondsremain = clockactivity._shortdef;
 BA.debugLineNum = 277;BA.debugLine="break = 0";
Debug.ShouldStop(1048576);
clockactivity._break = BA.numberCast(int.class, 0);
 };
 BA.debugLineNum = 279;BA.debugLine="timerState = 1";
Debug.ShouldStop(4194304);
clockactivity._timerstate = BA.numberCast(int.class, 1);
 }else 
{ BA.debugLineNum = 281;BA.debugLine="Else If timerState = 1 Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",clockactivity._timerstate,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 282;BA.debugLine="secondsRemain = pomoDef";
Debug.ShouldStop(33554432);
clockactivity._secondsremain = clockactivity._pomodef;
 BA.debugLineNum = 283;BA.debugLine="timerState = 0";
Debug.ShouldStop(67108864);
clockactivity._timerstate = BA.numberCast(int.class, 0);
 }}
;
 BA.debugLineNum = 286;BA.debugLine="updateLbl";
Debug.ShouldStop(536870912);
_updatelbl();
 BA.debugLineNum = 287;BA.debugLine="timerStop";
Debug.ShouldStop(1073741824);
_timerstop();
 BA.debugLineNum = 288;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _timerstop() throws Exception{
try {
		Debug.PushSubsStack("timerStop (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,290);
if (RapidSub.canDelegate("timerstop")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","timerstop");}
 BA.debugLineNum = 290;BA.debugLine="Private Sub timerStop";
Debug.ShouldStop(2);
 BA.debugLineNum = 291;BA.debugLine="timerCount.Enabled = False";
Debug.ShouldStop(4);
clockactivity._timercount.runMethod(true,"setEnabled",clockactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 292;BA.debugLine="playing = False";
Debug.ShouldStop(8);
clockactivity._playing = clockactivity.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 293;BA.debugLine="playBtn.Text = \"Start\"";
Debug.ShouldStop(16);
clockactivity.mostCurrent._playbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Start"));
 BA.debugLineNum = 294;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _tmr_tick() throws Exception{
try {
		Debug.PushSubsStack("tmr_Tick (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,110);
if (RapidSub.canDelegate("tmr_tick")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","tmr_tick");}
 BA.debugLineNum = 110;BA.debugLine="Sub tmr_Tick";
Debug.ShouldStop(8192);
 BA.debugLineNum = 111;BA.debugLine="If secondsRemain > 0 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean(">",clockactivity._secondsremain,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 112;BA.debugLine="secondsRemain = secondsRemain - 1";
Debug.ShouldStop(32768);
clockactivity._secondsremain = RemoteObject.solve(new RemoteObject[] {clockactivity._secondsremain,RemoteObject.createImmutable(1)}, "-",1, 1);
 BA.debugLineNum = 113;BA.debugLine="updateLbl";
Debug.ShouldStop(65536);
_updatelbl();
 }else {
 BA.debugLineNum = 115;BA.debugLine="timerStop";
Debug.ShouldStop(262144);
_timerstop();
 BA.debugLineNum = 117;BA.debugLine="If timerState = 0 Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",clockactivity._timerstate,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 118;BA.debugLine="counter = counter + 1";
Debug.ShouldStop(2097152);
clockactivity._counter = RemoteObject.solve(new RemoteObject[] {clockactivity._counter,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 120;BA.debugLine="If counter Mod 4 = 0 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",RemoteObject.solve(new RemoteObject[] {clockactivity._counter,RemoteObject.createImmutable(4)}, "%",0, 1),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 121;BA.debugLine="secondsRemain = longDef";
Debug.ShouldStop(16777216);
clockactivity._secondsremain = clockactivity._longdef;
 BA.debugLineNum = 122;BA.debugLine="break = 1";
Debug.ShouldStop(33554432);
clockactivity._break = BA.numberCast(int.class, 1);
 }else {
 BA.debugLineNum = 124;BA.debugLine="secondsRemain = shortDef";
Debug.ShouldStop(134217728);
clockactivity._secondsremain = clockactivity._shortdef;
 BA.debugLineNum = 125;BA.debugLine="break = 0";
Debug.ShouldStop(268435456);
clockactivity._break = BA.numberCast(int.class, 0);
 };
 BA.debugLineNum = 127;BA.debugLine="timerState = 1";
Debug.ShouldStop(1073741824);
clockactivity._timerstate = BA.numberCast(int.class, 1);
 }else 
{ BA.debugLineNum = 129;BA.debugLine="Else If timerState = 1 Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",clockactivity._timerstate,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 130;BA.debugLine="secondsRemain = pomoDef";
Debug.ShouldStop(2);
clockactivity._secondsremain = clockactivity._pomodef;
 BA.debugLineNum = 131;BA.debugLine="timerState = 0";
Debug.ShouldStop(4);
clockactivity._timerstate = BA.numberCast(int.class, 0);
 }}
;
 BA.debugLineNum = 134;BA.debugLine="updateLbl";
Debug.ShouldStop(32);
_updatelbl();
 };
 BA.debugLineNum = 136;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _updatelbl() throws Exception{
try {
		Debug.PushSubsStack("updateLbl (clockactivity) ","clockactivity",10,clockactivity.mostCurrent.activityBA,clockactivity.mostCurrent,138);
if (RapidSub.canDelegate("updatelbl")) { return b4a.example.clockactivity.remoteMe.runUserSub(false, "clockactivity","updatelbl");}
RemoteObject _mins = RemoteObject.createImmutable(0);
RemoteObject _secs = RemoteObject.createImmutable(0);
 BA.debugLineNum = 138;BA.debugLine="Sub updateLbl";
Debug.ShouldStop(512);
 BA.debugLineNum = 139;BA.debugLine="Dim mins As Int = secondsRemain / 60";
Debug.ShouldStop(1024);
_mins = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {clockactivity._secondsremain,RemoteObject.createImmutable(60)}, "/",0, 0));Debug.locals.put("mins", _mins);Debug.locals.put("mins", _mins);
 BA.debugLineNum = 140;BA.debugLine="Dim secs As Int = secondsRemain Mod 60";
Debug.ShouldStop(2048);
_secs = RemoteObject.solve(new RemoteObject[] {clockactivity._secondsremain,RemoteObject.createImmutable(60)}, "%",0, 1);Debug.locals.put("secs", _secs);Debug.locals.put("secs", _secs);
 BA.debugLineNum = 141;BA.debugLine="pomotimerLbl.Text = $\"$02.0{mins}:$02.0{secs}\"$";
Debug.ShouldStop(4096);
clockactivity.mostCurrent._pomotimerlbl.runMethod(true,"setText",BA.ObjectToCharSequence((RemoteObject.concat(RemoteObject.createImmutable(""),clockactivity.mostCurrent.__c.runMethod(true,"SmartStringFormatter",(Object)(BA.ObjectToString("02.0")),(Object)((_mins))),RemoteObject.createImmutable(":"),clockactivity.mostCurrent.__c.runMethod(true,"SmartStringFormatter",(Object)(BA.ObjectToString("02.0")),(Object)((_secs))),RemoteObject.createImmutable("")))));
 BA.debugLineNum = 142;BA.debugLine="pomoCounter.Text = counter";
Debug.ShouldStop(8192);
clockactivity.mostCurrent._pomocounter.runMethod(true,"setText",BA.ObjectToCharSequence(clockactivity._counter));
 BA.debugLineNum = 144;BA.debugLine="If timerState = 0 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",clockactivity._timerstate,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 145;BA.debugLine="sessionLbl.Text = \"Pomodoro\"";
Debug.ShouldStop(65536);
clockactivity.mostCurrent._sessionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Pomodoro"));
 }else {
 BA.debugLineNum = 147;BA.debugLine="If break = 1 Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",clockactivity._break,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 148;BA.debugLine="sessionLbl.Text = \"Long Break\"";
Debug.ShouldStop(524288);
clockactivity.mostCurrent._sessionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Long Break"));
 }else {
 BA.debugLineNum = 150;BA.debugLine="sessionLbl.Text = \"Short Break\"";
Debug.ShouldStop(2097152);
clockactivity.mostCurrent._sessionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Short Break"));
 };
 };
 BA.debugLineNum = 153;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}