package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class home_activity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (home_activity) ","home_activity",22,home_activity.mostCurrent.activityBA,home_activity.mostCurrent,16);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.home_activity.remoteMe.runUserSub(false, "home_activity","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 16;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 17;BA.debugLine="Activity.LoadLayout(\"Home_Activity_Layout\")";
Debug.ShouldStop(65536);
home_activity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Home_Activity_Layout")),home_activity.mostCurrent.activityBA);
 BA.debugLineNum = 18;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("Activity_Pause (home_activity) ","home_activity",22,home_activity.mostCurrent.activityBA,home_activity.mostCurrent,24);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.home_activity.remoteMe.runUserSub(false, "home_activity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 24;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 26;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("Activity_Resume (home_activity) ","home_activity",22,home_activity.mostCurrent.activityBA,home_activity.mostCurrent,20);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.home_activity.remoteMe.runUserSub(false, "home_activity","activity_resume");}
 BA.debugLineNum = 20;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 22;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btncreateacc_click() throws Exception{
try {
		Debug.PushSubsStack("btnCreateAcc_Click (home_activity) ","home_activity",22,home_activity.mostCurrent.activityBA,home_activity.mostCurrent,33);
if (RapidSub.canDelegate("btncreateacc_click")) { return b4a.example.home_activity.remoteMe.runUserSub(false, "home_activity","btncreateacc_click");}
 BA.debugLineNum = 33;BA.debugLine="Private Sub btnCreateAcc_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 34;BA.debugLine="StartActivity(Register)";
Debug.ShouldStop(2);
home_activity.mostCurrent.__c.runVoidMethod ("StartActivity",home_activity.processBA,(Object)((home_activity.mostCurrent._register.getObject())));
 BA.debugLineNum = 35;BA.debugLine="Activity.Finish";
Debug.ShouldStop(4);
home_activity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 36;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
		Debug.PushSubsStack("btnLogin_Click (home_activity) ","home_activity",22,home_activity.mostCurrent.activityBA,home_activity.mostCurrent,28);
if (RapidSub.canDelegate("btnlogin_click")) { return b4a.example.home_activity.remoteMe.runUserSub(false, "home_activity","btnlogin_click");}
 BA.debugLineNum = 28;BA.debugLine="Private Sub btnLogin_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 29;BA.debugLine="StartActivity(LogIn)";
Debug.ShouldStop(268435456);
home_activity.mostCurrent.__c.runVoidMethod ("StartActivity",home_activity.processBA,(Object)((home_activity.mostCurrent._login.getObject())));
 BA.debugLineNum = 30;BA.debugLine="Activity.finish";
Debug.ShouldStop(536870912);
home_activity.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private xui As XUI";
home_activity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}