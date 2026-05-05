package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class editnote_subs_1 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (editnote) ","editnote",18,editnote.mostCurrent.activityBA,editnote.mostCurrent,22);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 23;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(4194304);
switch (BA.switchObjectToInt(editnote.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 25;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._starter._darkmode /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout(\"editnoteLayout\")";
Debug.ShouldStop(33554432);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayout")),editnote.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark\")";
Debug.ShouldStop(134217728);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayoutDark")),editnote.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 31;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._starter._darkmode /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"editnoteLayout2\")";
Debug.ShouldStop(-2147483648);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayout2")),editnote.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark2\")";
Debug.ShouldStop(2);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayoutDark2")),editnote.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 37;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._starter._darkmode /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"editnoteLayout3\")";
Debug.ShouldStop(32);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayout3")),editnote.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 40;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark3\")";
Debug.ShouldStop(128);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayoutDark3")),editnote.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 44;BA.debugLine="contentTxt.Background = Null";
Debug.ShouldStop(2048);
editnote.mostCurrent._contenttxt.runMethod(false,"setBackground",(editnote.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 45;BA.debugLine="contentTxt.Gravity = Bit.Or(Gravity.TOP, Gravity.";
Debug.ShouldStop(4096);
editnote.mostCurrent._contenttxt.runMethod(true,"setGravity",editnote.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(editnote.mostCurrent.__c.getField(false,"Gravity").getField(true,"TOP")),(Object)(editnote.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"))));
 BA.debugLineNum = 46;BA.debugLine="If ActiveNote.IsInitialized Then";
Debug.ShouldStop(8192);
if (editnote._activenote.getField(true,"IsInitialized" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 47;BA.debugLine="titleTxt.Text = ActiveNote.Title";
Debug.ShouldStop(16384);
editnote.mostCurrent._titletxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(editnote._activenote.getField(true,"Title" /*RemoteObject*/ )));
 BA.debugLineNum = 48;BA.debugLine="tagsTxt.Text = ActiveNote.Tags";
Debug.ShouldStop(32768);
editnote.mostCurrent._tagstxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(editnote._activenote.getField(true,"Tags" /*RemoteObject*/ )));
 BA.debugLineNum = 49;BA.debugLine="contentTxt.Text = ActiveNote.Content";
Debug.ShouldStop(65536);
editnote.mostCurrent._contenttxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(editnote._activenote.getField(true,"Content" /*RemoteObject*/ )));
 };
 BA.debugLineNum = 51;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("Activity_Pause (editnote) ","editnote",18,editnote.mostCurrent.activityBA,editnote.mostCurrent,57);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 57;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 58;BA.debugLine="If UserClosed Then ActiveNote.Initialize";
Debug.ShouldStop(33554432);
if (_userclosed.<Boolean>get().booleanValue()) { 
editnote._activenote.runVoidMethod ("Initialize");};
 BA.debugLineNum = 59;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("Activity_Resume (editnote) ","editnote",18,editnote.mostCurrent.activityBA,editnote.mostCurrent,53);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","activity_resume");}
 BA.debugLineNum = 53;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 55;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 16;BA.debugLine="Private contentTxt As EditText";
editnote.mostCurrent._contenttxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private saveBtn As Button";
editnote.mostCurrent._savebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private tagsTxt As EditText";
editnote.mostCurrent._tagstxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private titleTxt As EditText";
editnote.mostCurrent._titletxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public ActiveNote As MyNote";
editnote._activenote = RemoteObject.createNew ("b4a.example.main._mynote");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _savebtn_click() throws Exception{
try {
		Debug.PushSubsStack("saveBtn_Click (editnote) ","editnote",18,editnote.mostCurrent.activityBA,editnote.mostCurrent,61);
if (RapidSub.canDelegate("savebtn_click")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","savebtn_click");}
RemoteObject _n = RemoteObject.declareNull("b4a.example.main._mynote");
 BA.debugLineNum = 61;BA.debugLine="Sub saveBtn_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 62;BA.debugLine="If titleTxt.Text.Trim = \"\" Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._titletxt.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 63;BA.debugLine="MsgboxAsync(\"Please add a title\", \"\")";
Debug.ShouldStop(1073741824);
editnote.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please add a title")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),editnote.processBA);
 BA.debugLineNum = 64;BA.debugLine="Return";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 67;BA.debugLine="Dim n As MyNote";
Debug.ShouldStop(4);
_n = RemoteObject.createNew ("b4a.example.main._mynote");Debug.locals.put("n", _n);
 BA.debugLineNum = 68;BA.debugLine="n.Initialize";
Debug.ShouldStop(8);
_n.runVoidMethod ("Initialize");
 BA.debugLineNum = 70;BA.debugLine="If ActiveNote.IsInitialized And ActiveNote.noteID";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean(".",editnote._activenote.getField(true,"IsInitialized" /*RemoteObject*/ )) && RemoteObject.solveBoolean("!",editnote._activenote.getField(true,"noteID" /*RemoteObject*/ ),BA.numberCast(long.class, 0))) { 
 BA.debugLineNum = 71;BA.debugLine="n.noteID = ActiveNote.noteID";
Debug.ShouldStop(64);
_n.setField ("noteID" /*RemoteObject*/ ,editnote._activenote.getField(true,"noteID" /*RemoteObject*/ ));
 }else {
 BA.debugLineNum = 73;BA.debugLine="n.noteID = (DateTime.Now * 1000) + Rnd(1, 1000)";
Debug.ShouldStop(256);
_n.setField ("noteID" /*RemoteObject*/ ,RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {editnote.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"),RemoteObject.createImmutable(1000)}, "*",0, 2)),editnote.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 1000)))}, "+",1, 2));
 };
 BA.debugLineNum = 77;BA.debugLine="n.Title = titleTxt.Text";
Debug.ShouldStop(4096);
_n.setField ("Title" /*RemoteObject*/ ,editnote.mostCurrent._titletxt.runMethod(true,"getText"));
 BA.debugLineNum = 78;BA.debugLine="n.Tags = tagsTxt.Text";
Debug.ShouldStop(8192);
_n.setField ("Tags" /*RemoteObject*/ ,editnote.mostCurrent._tagstxt.runMethod(true,"getText"));
 BA.debugLineNum = 79;BA.debugLine="n.Content = contentTxt.Text";
Debug.ShouldStop(16384);
_n.setField ("Content" /*RemoteObject*/ ,editnote.mostCurrent._contenttxt.runMethod(true,"getText"));
 BA.debugLineNum = 80;BA.debugLine="n.DateAdded = DateTime.Now";
Debug.ShouldStop(32768);
_n.setField ("DateAdded" /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"));
 BA.debugLineNum = 82;BA.debugLine="MainActivity.kvs.Put(\"N_\" & n.noteID, n)";
Debug.ShouldStop(131072);
editnote.mostCurrent._mainactivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(RemoteObject.createImmutable("N_"),_n.getField(true,"noteID" /*RemoteObject*/ ))),(Object)((_n)));
 BA.debugLineNum = 84;BA.debugLine="ToastMessageShow(\"Note Saved\", False)";
Debug.ShouldStop(524288);
editnote.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Note Saved")),(Object)(editnote.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 85;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1048576);
editnote.mostCurrent._activity.runVoidMethod ("Finish");
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
}