package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class navactivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,18);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 19;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(262144);
switch (BA.switchObjectToInt(navactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 21;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",navactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,navactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"navAct\")";
Debug.ShouldStop(2097152);
navactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("navAct")),navactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 24;BA.debugLine="Activity.LoadLayout(\"navActDark\")";
Debug.ShouldStop(8388608);
navactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("navActDark")),navactivity.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 27;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",navactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,navactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"navAct2\")";
Debug.ShouldStop(134217728);
navactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("navAct2")),navactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"navActDark2\")";
Debug.ShouldStop(536870912);
navactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("navActDark2")),navactivity.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 33;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",navactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,navactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"navAct3\")";
Debug.ShouldStop(2);
navactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("navAct3")),navactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"navActDark3\")";
Debug.ShouldStop(8);
navactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("navActDark3")),navactivity.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 40;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("Activity_Pause (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,46);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 46;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 48;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,42);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","activity_resume");}
 BA.debugLineNum = 42;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(512);
 BA.debugLineNum = 44;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _calendarbtn_click() throws Exception{
try {
		Debug.PushSubsStack("calendarBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,51);
if (RapidSub.canDelegate("calendarbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","calendarbtn_click");}
 BA.debugLineNum = 51;BA.debugLine="Private Sub calendarBtn_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 52;BA.debugLine="StartActivity(CalendarActivity)";
Debug.ShouldStop(524288);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._calendaractivity.getObject())));
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _clkbtn_click() throws Exception{
try {
		Debug.PushSubsStack("clkBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,55);
if (RapidSub.canDelegate("clkbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","clkbtn_click");}
 BA.debugLineNum = 55;BA.debugLine="Private Sub clkBtn_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 56;BA.debugLine="StartActivity(clockActivity)";
Debug.ShouldStop(8388608);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._clockactivity.getObject())));
 BA.debugLineNum = 57;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _corkpadbtn_click() throws Exception{
try {
		Debug.PushSubsStack("corkpadBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,59);
if (RapidSub.canDelegate("corkpadbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","corkpadbtn_click");}
 BA.debugLineNum = 59;BA.debugLine="Private Sub corkpadBtn_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 60;BA.debugLine="StartActivity(corkActivity)";
Debug.ShouldStop(134217728);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._corkactivity.getObject())));
 BA.debugLineNum = 61;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
		Debug.PushSubsStack("exitBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,87);
if (RapidSub.canDelegate("exitbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","exitbtn_click");}
 BA.debugLineNum = 87;BA.debugLine="Private Sub exitBtn_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 88;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8388608);
navactivity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 89;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _flashbtn_click() throws Exception{
try {
		Debug.PushSubsStack("flashBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,63);
if (RapidSub.canDelegate("flashbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","flashbtn_click");}
 BA.debugLineNum = 63;BA.debugLine="Private Sub flashBtn_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 64;BA.debugLine="StartActivity(FlashcardActivity)";
Debug.ShouldStop(-2147483648);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._flashcardactivity.getObject())));
 BA.debugLineNum = 65;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _helpbtn_click() throws Exception{
try {
		Debug.PushSubsStack("helpBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,79);
if (RapidSub.canDelegate("helpbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","helpbtn_click");}
 BA.debugLineNum = 79;BA.debugLine="Private Sub helpBtn_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 80;BA.debugLine="StartActivity(helpActivity)";
Debug.ShouldStop(32768);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._helpactivity.getObject())));
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
public static RemoteObject  _musicbtn_click() throws Exception{
try {
		Debug.PushSubsStack("musicBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,67);
if (RapidSub.canDelegate("musicbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","musicbtn_click");}
 BA.debugLineNum = 67;BA.debugLine="Private Sub musicBtn_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 68;BA.debugLine="StartActivity(musicActivity)";
Debug.ShouldStop(8);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._musicactivity.getObject())));
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
public static RemoteObject  _ntpdbtn_click() throws Exception{
try {
		Debug.PushSubsStack("ntpdBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,71);
if (RapidSub.canDelegate("ntpdbtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","ntpdbtn_click");}
 BA.debugLineNum = 71;BA.debugLine="Private Sub ntpdBtn_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 72;BA.debugLine="StartActivity(noteActivity)";
Debug.ShouldStop(128);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._noteactivity.getObject())));
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
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _themebtn_click() throws Exception{
try {
		Debug.PushSubsStack("themeBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,83);
if (RapidSub.canDelegate("themebtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","themebtn_click");}
 BA.debugLineNum = 83;BA.debugLine="Private Sub themeBtn_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 84;BA.debugLine="StartActivity(themeActivity)";
Debug.ShouldStop(524288);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._themeactivity.getObject())));
 BA.debugLineNum = 85;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _todobtn_click() throws Exception{
try {
		Debug.PushSubsStack("todoBtn_Click (navactivity) ","navactivity",18,navactivity.mostCurrent.activityBA,navactivity.mostCurrent,75);
if (RapidSub.canDelegate("todobtn_click")) { return b4a.example.navactivity.remoteMe.runUserSub(false, "navactivity","todobtn_click");}
 BA.debugLineNum = 75;BA.debugLine="Private Sub todoBtn_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 76;BA.debugLine="StartActivity(todoActivity)";
Debug.ShouldStop(2048);
navactivity.mostCurrent.__c.runVoidMethod ("StartActivity",navactivity.processBA,(Object)((navactivity.mostCurrent._todoactivity.getObject())));
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
}