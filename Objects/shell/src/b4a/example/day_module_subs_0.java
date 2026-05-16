package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class day_module_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,53);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","activity_create", _firsttime);}
RemoteObject _bd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 53;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 55;BA.debugLine="Dim bd As BitmapDrawable";
Debug.ShouldStop(4194304);
_bd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd", _bd);
 BA.debugLineNum = 56;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(8388608);
switch (BA.switchObjectToInt(day_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 58;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,day_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 59;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout\")";
Debug.ShouldStop(67108864);
day_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Day_ModuleLayout")),day_module.mostCurrent.activityBA);
 BA.debugLineNum = 60;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(134217728);
_bd.runVoidMethod ("Initialize",(Object)((day_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(day_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 61;BA.debugLine="Day_btn.Background = bd";
Debug.ShouldStop(268435456);
day_module.mostCurrent._day_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 62;BA.debugLine="Day_btn.TextColor = Colors.White";
Debug.ShouldStop(536870912);
day_module.mostCurrent._day_btn.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 64;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark\")";
Debug.ShouldStop(-2147483648);
day_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Day_ModuleLayoutDark")),day_module.mostCurrent.activityBA);
 BA.debugLineNum = 65;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(1);
_bd.runVoidMethod ("Initialize",(Object)((day_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(day_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 66;BA.debugLine="Day_btn.Background = bd";
Debug.ShouldStop(2);
day_module.mostCurrent._day_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 67;BA.debugLine="Day_btn.TextColor = Colors.White";
Debug.ShouldStop(4);
day_module.mostCurrent._day_btn.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 70;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,day_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 71;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout2\")";
Debug.ShouldStop(64);
day_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Day_ModuleLayout2")),day_module.mostCurrent.activityBA);
 BA.debugLineNum = 72;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(128);
_bd.runVoidMethod ("Initialize",(Object)((day_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(day_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 73;BA.debugLine="Day_btn.Background = bd";
Debug.ShouldStop(256);
day_module.mostCurrent._day_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 74;BA.debugLine="Day_btn.TextColor = Colors.White";
Debug.ShouldStop(512);
day_module.mostCurrent._day_btn.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 76;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark2\")";
Debug.ShouldStop(2048);
day_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Day_ModuleLayoutDark2")),day_module.mostCurrent.activityBA);
 BA.debugLineNum = 77;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(4096);
_bd.runVoidMethod ("Initialize",(Object)((day_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(day_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 78;BA.debugLine="Day_btn.Background = bd";
Debug.ShouldStop(8192);
day_module.mostCurrent._day_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 79;BA.debugLine="Day_btn.TextColor = Colors.White";
Debug.ShouldStop(16384);
day_module.mostCurrent._day_btn.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 2: {
 BA.debugLineNum = 82;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,day_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 83;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayout3\")";
Debug.ShouldStop(262144);
day_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Day_ModuleLayout3")),day_module.mostCurrent.activityBA);
 BA.debugLineNum = 84;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(524288);
_bd.runVoidMethod ("Initialize",(Object)((day_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(day_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 85;BA.debugLine="Day_btn.Background = bd";
Debug.ShouldStop(1048576);
day_module.mostCurrent._day_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 86;BA.debugLine="Day_btn.TextColor = Colors.White";
Debug.ShouldStop(2097152);
day_module.mostCurrent._day_btn.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 88;BA.debugLine="Activity.LoadLayout(\"Day_ModuleLayoutDark3\")";
Debug.ShouldStop(8388608);
day_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Day_ModuleLayoutDark3")),day_module.mostCurrent.activityBA);
 BA.debugLineNum = 89;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(16777216);
_bd.runVoidMethod ("Initialize",(Object)((day_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(day_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtnd.png"))).getObject())));
 BA.debugLineNum = 90;BA.debugLine="Day_btn.Background = bd";
Debug.ShouldStop(33554432);
day_module.mostCurrent._day_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 91;BA.debugLine="Day_btn.TextColor = Colors.White";
Debug.ShouldStop(67108864);
day_module.mostCurrent._day_btn.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
}
;
 BA.debugLineNum = 95;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,day_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 96;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
Debug.ShouldStop(-2147483648);
day_module.mostCurrent._starttimelinesp.runMethod(true,"setDropdownBackgroundColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 97;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.Black";
Debug.ShouldStop(1);
day_module.mostCurrent._starttimelinesp.runMethod(true,"setDropdownTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 98;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.W";
Debug.ShouldStop(2);
day_module.mostCurrent._endtimelinesp.runMethod(true,"setDropdownBackgroundColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 99;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.Black";
Debug.ShouldStop(4);
day_module.mostCurrent._endtimelinesp.runMethod(true,"setDropdownTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else {
 BA.debugLineNum = 101;BA.debugLine="starttimelineSP.DropdownBackgroundColor = Colors";
Debug.ShouldStop(16);
day_module.mostCurrent._starttimelinesp.runMethod(true,"setDropdownBackgroundColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 102;BA.debugLine="starttimelineSP.DropdownTextColor = Colors.White";
Debug.ShouldStop(32);
day_module.mostCurrent._starttimelinesp.runMethod(true,"setDropdownTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 103;BA.debugLine="endtimelineSP.DropdownBackgroundColor = Colors.D";
Debug.ShouldStop(64);
day_module.mostCurrent._endtimelinesp.runMethod(true,"setDropdownBackgroundColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 104;BA.debugLine="endtimelineSP.DropdownTextColor = Colors.White";
Debug.ShouldStop(128);
day_module.mostCurrent._endtimelinesp.runMethod(true,"setDropdownTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 BA.debugLineNum = 107;BA.debugLine="date_todaylbl.Text = SetDate(currentDate)";
Debug.ShouldStop(1024);
day_module.mostCurrent._date_todaylbl.runMethod(true,"setText",BA.ObjectToCharSequence(_setdate(day_module._currentdate)));
 BA.debugLineNum = 108;BA.debugLine="add_events_module.currentDate = SetDate(currentDa";
Debug.ShouldStop(2048);
day_module.mostCurrent._add_events_module._currentdate /*RemoteObject*/  = _setdate(day_module._currentdate);
 BA.debugLineNum = 109;BA.debugLine="SetUpSpinners";
Debug.ShouldStop(4096);
_setupspinners();
 BA.debugLineNum = 111;BA.debugLine="Log(currentDate)";
Debug.ShouldStop(16384);
day_module.mostCurrent.__c.runVoidMethod ("LogImpl","36488122",day_module._currentdate,0);
 BA.debugLineNum = 113;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
		Debug.PushSubsStack("Activity_Pause (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,464);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 464;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 466;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("Activity_Resume (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,456);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","activity_resume");}
 BA.debugLineNum = 456;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(128);
 BA.debugLineNum = 457;BA.debugLine="UpdateTimeLine";
Debug.ShouldStop(256);
_updatetimeline();
 BA.debugLineNum = 458;BA.debugLine="If addeventsfeedback = True Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",day_module._addeventsfeedback,day_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 459;BA.debugLine="addeventsfeedback = False";
Debug.ShouldStop(1024);
day_module._addeventsfeedback = day_module.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 460;BA.debugLine="MsgboxAsync(\"Event Saved\", \"Saved\")";
Debug.ShouldStop(2048);
day_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Event Saved")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Saved"))),day_module.processBA);
 };
 BA.debugLineNum = 462;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addevent_btn_click() throws Exception{
try {
		Debug.PushSubsStack("Addevent_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,487);
if (RapidSub.canDelegate("addevent_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","addevent_btn_click");}
 BA.debugLineNum = 487;BA.debugLine="Private Sub Addevent_btn_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 488;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(128);
day_module.mostCurrent._addpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 489;BA.debugLine="add_events_module.eventtype = \"Event\"";
Debug.ShouldStop(256);
day_module.mostCurrent._add_events_module._eventtype /*RemoteObject*/  = BA.ObjectToString("Event");
 BA.debugLineNum = 490;BA.debugLine="StartActivity(add_events_module)";
Debug.ShouldStop(512);
day_module.mostCurrent.__c.runVoidMethod ("StartActivity",day_module.processBA,(Object)((day_module.mostCurrent._add_events_module.getObject())));
 BA.debugLineNum = 491;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addnew_btn_click() throws Exception{
try {
		Debug.PushSubsStack("addnew_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,493);
if (RapidSub.canDelegate("addnew_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","addnew_btn_click");}
 BA.debugLineNum = 493;BA.debugLine="Private Sub addnew_btn_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 495;BA.debugLine="If addpanel.Visible =True Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._addpanel.runMethod(true,"getVisible"),day_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 496;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(32768);
day_module.mostCurrent._addpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 497;BA.debugLine="Return";
Debug.ShouldStop(65536);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 499;BA.debugLine="addpanel.Visible = True";
Debug.ShouldStop(262144);
day_module.mostCurrent._addpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 502;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addtask_btn_click() throws Exception{
try {
		Debug.PushSubsStack("Addtask_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,504);
if (RapidSub.canDelegate("addtask_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","addtask_btn_click");}
 BA.debugLineNum = 504;BA.debugLine="Private Sub Addtask_btn_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 505;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(16777216);
day_module.mostCurrent._addpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 506;BA.debugLine="add_events_module.eventtype = \"Task\"";
Debug.ShouldStop(33554432);
day_module.mostCurrent._add_events_module._eventtype /*RemoteObject*/  = BA.ObjectToString("Task");
 BA.debugLineNum = 507;BA.debugLine="StartActivity(add_events_module)";
Debug.ShouldStop(67108864);
day_module.mostCurrent.__c.runVoidMethod ("StartActivity",day_module.processBA,(Object)((day_module.mostCurrent._add_events_module.getObject())));
 BA.debugLineNum = 508;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _birthday_btn_click() throws Exception{
try {
		Debug.PushSubsStack("birthday_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,510);
if (RapidSub.canDelegate("birthday_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","birthday_btn_click");}
 BA.debugLineNum = 510;BA.debugLine="Private Sub birthday_btn_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 511;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(1073741824);
day_module.mostCurrent._addpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 512;BA.debugLine="add_events_module.eventtype = \"Birthday\"";
Debug.ShouldStop(-2147483648);
day_module.mostCurrent._add_events_module._eventtype /*RemoteObject*/  = BA.ObjectToString("Birthday");
 BA.debugLineNum = 513;BA.debugLine="StartActivity(add_events_module)";
Debug.ShouldStop(1);
day_module.mostCurrent.__c.runVoidMethod ("StartActivity",day_module.processBA,(Object)((day_module.mostCurrent._add_events_module.getObject())));
 BA.debugLineNum = 514;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _birthdayrb_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("birthdayrb_CheckedChange (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,615);
if (RapidSub.canDelegate("birthdayrb_checkedchange")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","birthdayrb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 615;BA.debugLine="Private Sub birthdayrb_CheckedChange(Checked As Bo";
Debug.ShouldStop(64);
 BA.debugLineNum = 616;BA.debugLine="eventtype = \"Birthday\"";
Debug.ShouldStop(128);
day_module.mostCurrent._eventtype = BA.ObjectToString("Birthday");
 BA.debugLineNum = 617;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _canceldelete_btn_click() throws Exception{
try {
		Debug.PushSubsStack("cancelDelete_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,564);
if (RapidSub.canDelegate("canceldelete_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","canceldelete_btn_click");}
 BA.debugLineNum = 564;BA.debugLine="Private Sub cancelDelete_btn_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 565;BA.debugLine="deletepanel.Visible = False";
Debug.ShouldStop(1048576);
day_module.mostCurrent._deletepanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 566;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _canceledit_btn_click() throws Exception{
try {
		Debug.PushSubsStack("cancelEdit_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,537);
if (RapidSub.canDelegate("canceledit_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","canceledit_btn_click");}
 BA.debugLineNum = 537;BA.debugLine="Private Sub cancelEdit_btn_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 538;BA.debugLine="EditInfoPanel.Visible = False";
Debug.ShouldStop(33554432);
day_module.mostCurrent._editinfopanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 539;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _canceltldelete_btn_click() throws Exception{
try {
		Debug.PushSubsStack("cancelTLdelete_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,587);
if (RapidSub.canDelegate("canceltldelete_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","canceltldelete_btn_click");}
 BA.debugLineNum = 587;BA.debugLine="Private Sub cancelTLdelete_btn_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 588;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
Debug.ShouldStop(2048);
day_module.mostCurrent._deletetlevent_confirmationpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 589;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _confirmdelete_btn_click() throws Exception{
try {
		Debug.PushSubsStack("confirmdelete_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,568);
if (RapidSub.canDelegate("confirmdelete_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","confirmdelete_btn_click");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 568;BA.debugLine="Private Sub confirmdelete_btn_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 569;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
Debug.ShouldStop(16777216);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 570;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
Debug.ShouldStop(33554432);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allevents = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("AllEvents")))));Debug.locals.put("allevents", _allevents);Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 571;BA.debugLine="allevents.RemoveAt(currentIndex)";
Debug.ShouldStop(67108864);
_allevents.runVoidMethod ("RemoveAt",(Object)(day_module._currentindex));
 BA.debugLineNum = 572;BA.debugLine="deletepanel.Visible = False";
Debug.ShouldStop(134217728);
day_module.mostCurrent._deletepanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 573;BA.debugLine="eventInfo_panel.Visible = False";
Debug.ShouldStop(268435456);
day_module.mostCurrent._eventinfo_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 574;BA.debugLine="SaveCalendar";
Debug.ShouldStop(536870912);
_savecalendar();
 BA.debugLineNum = 575;BA.debugLine="DrawMainEvents";
Debug.ShouldStop(1073741824);
_drawmainevents();
 BA.debugLineNum = 576;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _day_btn_click() throws Exception{
try {
		Debug.PushSubsStack("Day_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,478);
if (RapidSub.canDelegate("day_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","day_btn_click");}
 BA.debugLineNum = 478;BA.debugLine="Private Sub Day_btn_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 479;BA.debugLine="menupanel.Visible = False";
Debug.ShouldStop(1073741824);
day_module.mostCurrent._menupanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 480;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _deleteevent_btn_click() throws Exception{
try {
		Debug.PushSubsStack("DeleteEvent_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,533);
if (RapidSub.canDelegate("deleteevent_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","deleteevent_btn_click");}
 BA.debugLineNum = 533;BA.debugLine="Private Sub DeleteEvent_btn_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 534;BA.debugLine="deletepanel.Visible = True";
Debug.ShouldStop(2097152);
day_module.mostCurrent._deletepanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 535;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _deletetlconfirm_btn_click() throws Exception{
try {
		Debug.PushSubsStack("deleteTLconfirm_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,591);
if (RapidSub.canDelegate("deletetlconfirm_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","deletetlconfirm_btn_click");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 591;BA.debugLine="Private Sub deleteTLconfirm_btn_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 592;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
Debug.ShouldStop(32768);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 593;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
Debug.ShouldStop(65536);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_timeline = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Timeline")))));Debug.locals.put("timeline", _timeline);Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 595;BA.debugLine="For i = timeline.Size -1 To 0 Step -1";
Debug.ShouldStop(262144);
{
final int step3 = -1;
final int limit3 = 0;
_i = RemoteObject.solve(new RemoteObject[] {_timeline.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue() ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 596;BA.debugLine="Dim ev As Map = timeline.Get(i)";
Debug.ShouldStop(524288);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _timeline.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("ev", _ev);Debug.locals.put("ev", _ev);
 BA.debugLineNum = 597;BA.debugLine="If ev.Get(\"ID\") = currentevId Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("ID")))),(day_module._currentevid))) { 
 BA.debugLineNum = 598;BA.debugLine="timeline.RemoveAt(i)";
Debug.ShouldStop(2097152);
_timeline.runVoidMethod ("RemoveAt",(Object)(BA.numberCast(int.class, _i)));
 BA.debugLineNum = 599;BA.debugLine="Exit";
Debug.ShouldStop(4194304);
if (true) break;
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 603;BA.debugLine="addTL_et.Text = \"\"";
Debug.ShouldStop(67108864);
day_module.mostCurrent._addtl_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 604;BA.debugLine="deleteTLevent_confirmationpanel.Visible = False";
Debug.ShouldStop(134217728);
day_module.mostCurrent._deletetlevent_confirmationpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 605;BA.debugLine="addEventTL_panel.Visible = False";
Debug.ShouldStop(268435456);
day_module.mostCurrent._addeventtl_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 606;BA.debugLine="SaveCalendar";
Debug.ShouldStop(536870912);
_savecalendar();
 BA.debugLineNum = 607;BA.debugLine="UpdateTimeLine";
Debug.ShouldStop(1073741824);
_updatetimeline();
 BA.debugLineNum = 608;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _deletetlevent_btn_click() throws Exception{
try {
		Debug.PushSubsStack("deleteTLevent_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,583);
if (RapidSub.canDelegate("deletetlevent_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","deletetlevent_btn_click");}
 BA.debugLineNum = 583;BA.debugLine="Private Sub deleteTLevent_btn_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 584;BA.debugLine="deleteTLevent_confirmationpanel.Visible = True";
Debug.ShouldStop(128);
day_module.mostCurrent._deletetlevent_confirmationpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 585;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _drawhourlabels() throws Exception{
try {
		Debug.PushSubsStack("DrawHourLabels (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,192);
if (RapidSub.canDelegate("drawhourlabels")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","drawhourlabels");}
RemoteObject _rowh = RemoteObject.createImmutable(0);
int _h = 0;
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
 BA.debugLineNum = 192;BA.debugLine="Sub DrawHourLabels";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 195;BA.debugLine="Svtimeline.Panel.RemoveAllViews";
Debug.ShouldStop(4);
day_module.mostCurrent._svtimeline.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 197;BA.debugLine="Dim rowh As Int = 60dip";
Debug.ShouldStop(16);
_rowh = day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));Debug.locals.put("rowh", _rowh);Debug.locals.put("rowh", _rowh);
 BA.debugLineNum = 198;BA.debugLine="Svtimeline.Panel.Height = 24 * rowh";
Debug.ShouldStop(32);
day_module.mostCurrent._svtimeline.runMethod(false,"getPanel").runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(24),_rowh}, "*",0, 1));
 BA.debugLineNum = 201;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,day_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 202;BA.debugLine="For h = 0 To 23";
Debug.ShouldStop(512);
{
final int step5 = 1;
final int limit5 = 23;
_h = 0 ;
for (;(step5 > 0 && _h <= limit5) || (step5 < 0 && _h >= limit5) ;_h = ((int)(0 + _h + step5))  ) {
Debug.locals.put("h", _h);
 BA.debugLineNum = 203;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(1024);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 204;BA.debugLine="p.Initialize(\"hour\")";
Debug.ShouldStop(2048);
_p.runVoidMethod ("Initialize",day_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("hour")));
 BA.debugLineNum = 205;BA.debugLine="p.Tag = h";
Debug.ShouldStop(4096);
_p.runMethod(false,"setTag",RemoteObject.createImmutable((_h)));
 BA.debugLineNum = 206;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimelin";
Debug.ShouldStop(8192);
day_module.mostCurrent._svtimeline.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_h),_rowh}, "*",0, 1)),(Object)(day_module.mostCurrent._svtimeline.runMethod(true,"getWidth")),(Object)(_rowh));
 BA.debugLineNum = 208;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(32768);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 209;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(65536);
_lbl.runVoidMethod ("Initialize",day_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 210;BA.debugLine="lbl.Text = GetTimeString(h)";
Debug.ShouldStop(131072);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_gettimestring(BA.numberCast(int.class, _h))));
 BA.debugLineNum = 211;BA.debugLine="lbl.TextColor = Colors.DarkGray";
Debug.ShouldStop(262144);
_lbl.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 212;BA.debugLine="lbl.Gravity = Gravity.left";
Debug.ShouldStop(524288);
_lbl.runMethod(true,"setGravity",day_module.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"));
 BA.debugLineNum = 213;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
Debug.ShouldStop(1048576);
_p.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(_rowh));
 }
}Debug.locals.put("h", _h);
;
 }else {
 BA.debugLineNum = 216;BA.debugLine="For h = 0 To 23";
Debug.ShouldStop(8388608);
{
final int step18 = 1;
final int limit18 = 23;
_h = 0 ;
for (;(step18 > 0 && _h <= limit18) || (step18 < 0 && _h >= limit18) ;_h = ((int)(0 + _h + step18))  ) {
Debug.locals.put("h", _h);
 BA.debugLineNum = 217;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(16777216);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 218;BA.debugLine="p.Initialize(\"hour\")";
Debug.ShouldStop(33554432);
_p.runVoidMethod ("Initialize",day_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("hour")));
 BA.debugLineNum = 219;BA.debugLine="p.Tag = h";
Debug.ShouldStop(67108864);
_p.runMethod(false,"setTag",RemoteObject.createImmutable((_h)));
 BA.debugLineNum = 220;BA.debugLine="Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimeli";
Debug.ShouldStop(134217728);
day_module.mostCurrent._svtimeline.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_h),_rowh}, "*",0, 1)),(Object)(day_module.mostCurrent._svtimeline.runMethod(true,"getWidth")),(Object)(_rowh));
 BA.debugLineNum = 222;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(536870912);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 223;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(1073741824);
_lbl.runVoidMethod ("Initialize",day_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 224;BA.debugLine="lbl.Text = GetTimeString(h)";
Debug.ShouldStop(-2147483648);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_gettimestring(BA.numberCast(int.class, _h))));
 BA.debugLineNum = 225;BA.debugLine="lbl.TextColor = Colors.White";
Debug.ShouldStop(1);
_lbl.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 226;BA.debugLine="lbl.Gravity = Gravity.left";
Debug.ShouldStop(2);
_lbl.runMethod(true,"setGravity",day_module.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"));
 BA.debugLineNum = 227;BA.debugLine="p.AddView(lbl, 0, 0, 60dip, rowh)";
Debug.ShouldStop(4);
_p.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(_rowh));
 }
}Debug.locals.put("h", _h);
;
 };
 BA.debugLineNum = 232;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _drawmainevents() throws Exception{
try {
		Debug.PushSubsStack("DrawMainEvents (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,129);
if (RapidSub.canDelegate("drawmainevents")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","drawmainevents");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _y = RemoteObject.createImmutable(0);
RemoteObject _rowheight = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 129;BA.debugLine="Sub DrawMainEvents";
Debug.ShouldStop(1);
 BA.debugLineNum = 130;BA.debugLine="svEvents.Panel.RemoveAllViews";
Debug.ShouldStop(2);
day_module.mostCurrent._svevents.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 131;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
Debug.ShouldStop(4);
if (day_module.mostCurrent.__c.runMethod(true,"Not",(Object)(day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(true,"ContainsKey",(Object)((day_module._currentdate))))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 132;BA.debugLine="Return";
Debug.ShouldStop(8);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 135;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
Debug.ShouldStop(64);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 136;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
Debug.ShouldStop(128);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allevents = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("AllEvents")))));Debug.locals.put("allevents", _allevents);Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 138;BA.debugLine="Dim y As Int = 0";
Debug.ShouldStop(512);
_y = BA.numberCast(int.class, 0);Debug.locals.put("y", _y);Debug.locals.put("y", _y);
 BA.debugLineNum = 139;BA.debugLine="Dim rowHeight As Int = 50dip";
Debug.ShouldStop(1024);
_rowheight = day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)));Debug.locals.put("rowHeight", _rowheight);Debug.locals.put("rowHeight", _rowheight);
 BA.debugLineNum = 140;BA.debugLine="For i = 0 To allevents.Size -1";
Debug.ShouldStop(2048);
{
final int step9 = 1;
final int limit9 = RemoteObject.solve(new RemoteObject[] {_allevents.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9) ;_i = ((int)(0 + _i + step9))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 141;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(4096);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 142;BA.debugLine="Dim ev As Map = allevents.Get(i)";
Debug.ShouldStop(8192);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _allevents.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("ev", _ev);Debug.locals.put("ev", _ev);
 BA.debugLineNum = 143;BA.debugLine="lbl.Initialize(\"mainEvent\")";
Debug.ShouldStop(16384);
_lbl.runVoidMethod ("Initialize",day_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("mainEvent")));
 BA.debugLineNum = 144;BA.debugLine="lbl.Tag = i";
Debug.ShouldStop(32768);
_lbl.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 145;BA.debugLine="lbl.Text = ev.Get(\"Title\")";
Debug.ShouldStop(65536);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title"))))));
 BA.debugLineNum = 146;BA.debugLine="lbl.Gravity = Gravity.CENTER_vertical";
Debug.ShouldStop(131072);
_lbl.runMethod(true,"setGravity",day_module.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 147;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(262144);
_lbl.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 148;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
Debug.ShouldStop(524288);
_lbl.runVoidMethod ("setColor",_identifycolor(BA.ObjectToString(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags")))))));
 BA.debugLineNum = 150;BA.debugLine="svEvents.Panel.AddView(lbl, 0, y, svEvents.Width";
Debug.ShouldStop(2097152);
day_module.mostCurrent._svevents.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(_y),(Object)(day_module.mostCurrent._svevents.runMethod(true,"getWidth")),(Object)(_rowheight));
 BA.debugLineNum = 151;BA.debugLine="y = y+rowHeight";
Debug.ShouldStop(4194304);
_y = RemoteObject.solve(new RemoteObject[] {_y,_rowheight}, "+",1, 1);Debug.locals.put("y", _y);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 154;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _drawtimelineevents() throws Exception{
try {
		Debug.PushSubsStack("DrawTimelineEvents (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,259);
if (RapidSub.canDelegate("drawtimelineevents")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","drawtimelineevents");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _rowh = RemoteObject.createImmutable(0);
int _h = 0;
RemoteObject _hourpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _starth = RemoteObject.createImmutable(0);
RemoteObject _endh = RemoteObject.createImmutable(0);
RemoteObject _title = RemoteObject.createImmutable("");
RemoteObject _color = RemoteObject.createImmutable(0);
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
 BA.debugLineNum = 259;BA.debugLine="Sub DrawTimelineEvents";
Debug.ShouldStop(4);
 BA.debugLineNum = 260;BA.debugLine="If Not(CalendarActivity.CalendarMap.ContainsKey(c";
Debug.ShouldStop(8);
if (day_module.mostCurrent.__c.runMethod(true,"Not",(Object)(day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(true,"ContainsKey",(Object)((day_module._currentdate))))).<Boolean>get().booleanValue()) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 262;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
Debug.ShouldStop(32);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 263;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
Debug.ShouldStop(64);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_timeline = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Timeline")))));Debug.locals.put("timeline", _timeline);Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 265;BA.debugLine="Dim rowh As Int = 60dip";
Debug.ShouldStop(256);
_rowh = day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));Debug.locals.put("rowh", _rowh);Debug.locals.put("rowh", _rowh);
 BA.debugLineNum = 267;BA.debugLine="For h = 0 To 23";
Debug.ShouldStop(1024);
{
final int step5 = 1;
final int limit5 = 23;
_h = 0 ;
for (;(step5 > 0 && _h <= limit5) || (step5 < 0 && _h >= limit5) ;_h = ((int)(0 + _h + step5))  ) {
Debug.locals.put("h", _h);
 BA.debugLineNum = 268;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetVie";
Debug.ShouldStop(2048);
_hourpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_hourpanel = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), day_module.mostCurrent._svtimeline.runMethod(false,"getPanel").runMethod(false,"GetView",(Object)(BA.numberCast(int.class, _h))).getObject());Debug.locals.put("hourPanel", _hourpanel);Debug.locals.put("hourPanel", _hourpanel);
 BA.debugLineNum = 270;BA.debugLine="If hourPanel.NumberOfViews > 1 Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean(">",_hourpanel.runMethod(true,"getNumberOfViews"),BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 271;BA.debugLine="hourPanel.RemoveViewAt(1)";
Debug.ShouldStop(16384);
_hourpanel.runVoidMethod ("RemoveViewAt",(Object)(BA.numberCast(int.class, 1)));
 };
 }
}Debug.locals.put("h", _h);
;
 BA.debugLineNum = 275;BA.debugLine="For Each ev  As Map In timeline";
Debug.ShouldStop(262144);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group11 = _timeline;
final int groupLen11 = group11.runMethod(true,"getSize").<Integer>get()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group11.runMethod(false,"Get",index11));Debug.locals.put("ev", _ev);
Debug.locals.put("ev", _ev);
 BA.debugLineNum = 276;BA.debugLine="Dim starth As Int = ev.Get(\"Start\")";
Debug.ShouldStop(524288);
_starth = BA.numberCast(int.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Start")))));Debug.locals.put("starth", _starth);Debug.locals.put("starth", _starth);
 BA.debugLineNum = 277;BA.debugLine="Dim endh As Int = ev.Get(\"End\")";
Debug.ShouldStop(1048576);
_endh = BA.numberCast(int.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("End")))));Debug.locals.put("endh", _endh);Debug.locals.put("endh", _endh);
 BA.debugLineNum = 278;BA.debugLine="Dim title As String = ev.Get(\"Title\")";
Debug.ShouldStop(2097152);
_title = BA.ObjectToString(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title")))));Debug.locals.put("title", _title);Debug.locals.put("title", _title);
 BA.debugLineNum = 279;BA.debugLine="Dim color As Int = IdentifyColor(ev.Get(\"Tags\"))";
Debug.ShouldStop(4194304);
_color = _identifycolor(BA.ObjectToString(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags"))))));Debug.locals.put("color", _color);Debug.locals.put("color", _color);
 BA.debugLineNum = 280;BA.debugLine="For h = starth To endh - 1";
Debug.ShouldStop(8388608);
{
final int step16 = 1;
final int limit16 = RemoteObject.solve(new RemoteObject[] {_endh,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_h = _starth.<Integer>get().intValue() ;
for (;(step16 > 0 && _h <= limit16) || (step16 < 0 && _h >= limit16) ;_h = ((int)(0 + _h + step16))  ) {
Debug.locals.put("h", _h);
 BA.debugLineNum = 281;BA.debugLine="If h >= 0 And h <= 23 Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("g",RemoteObject.createImmutable(_h),BA.numberCast(double.class, 0)) && RemoteObject.solveBoolean("k",RemoteObject.createImmutable(_h),BA.numberCast(double.class, 23))) { 
 BA.debugLineNum = 282;BA.debugLine="Dim hourPanel As Panel = Svtimeline.Panel.GetV";
Debug.ShouldStop(33554432);
_hourpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_hourpanel = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), day_module.mostCurrent._svtimeline.runMethod(false,"getPanel").runMethod(false,"GetView",(Object)(BA.numberCast(int.class, _h))).getObject());Debug.locals.put("hourPanel", _hourpanel);Debug.locals.put("hourPanel", _hourpanel);
 BA.debugLineNum = 283;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(67108864);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 284;BA.debugLine="lbl.initialize(\"TimelineEvent\")";
Debug.ShouldStop(134217728);
_lbl.runVoidMethod ("Initialize",day_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("TimelineEvent")));
 BA.debugLineNum = 285;BA.debugLine="lbl.Tag = ev";
Debug.ShouldStop(268435456);
_lbl.runMethod(false,"setTag",(_ev.getObject()));
 BA.debugLineNum = 286;BA.debugLine="lbl.Text = title";
Debug.ShouldStop(536870912);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_title));
 BA.debugLineNum = 287;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(1073741824);
_lbl.runMethod(true,"setTextColor",day_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 288;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(-2147483648);
_lbl.runMethod(true,"setGravity",day_module.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 289;BA.debugLine="lbl.Color = color";
Debug.ShouldStop(1);
_lbl.runVoidMethod ("setColor",_color);
 BA.debugLineNum = 290;BA.debugLine="hourPanel.AddView(lbl, 65dip, 0, hourPanel.Wid";
Debug.ShouldStop(2);
_hourpanel.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 65)))),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_hourpanel.runMethod(true,"getWidth"),day_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))}, "-",1, 1)),(Object)(_rowh));
 };
 }
}Debug.locals.put("h", _h);
;
 }
}Debug.locals.put("ev", _ev);
;
 BA.debugLineNum = 295;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _editeventinfo_btn_click() throws Exception{
try {
		Debug.PushSubsStack("editeventinfo_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,522);
if (RapidSub.canDelegate("editeventinfo_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","editeventinfo_btn_click");}
 BA.debugLineNum = 522;BA.debugLine="Private Sub editeventinfo_btn_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 523;BA.debugLine="EditInfoPanel.Visible = True";
Debug.ShouldStop(1024);
day_module.mostCurrent._editinfopanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 524;BA.debugLine="editTitle_et.text = currenttaggedEvent.Get(\"Title";
Debug.ShouldStop(2048);
day_module.mostCurrent._edittitle_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(day_module.mostCurrent._currenttaggedevent.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title"))))));
 BA.debugLineNum = 525;BA.debugLine="editDescription_et.Text = currenttaggedEvent.Get(";
Debug.ShouldStop(4096);
day_module.mostCurrent._editdescription_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(day_module.mostCurrent._currenttaggedevent.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Description"))))));
 BA.debugLineNum = 526;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _eventrb_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("eventrb_CheckedChange (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,619);
if (RapidSub.canDelegate("eventrb_checkedchange")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","eventrb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 619;BA.debugLine="Private Sub eventrb_CheckedChange(Checked As Boole";
Debug.ShouldStop(1024);
 BA.debugLineNum = 620;BA.debugLine="eventtype = \"Event\"";
Debug.ShouldStop(2048);
day_module.mostCurrent._eventtype = BA.ObjectToString("Event");
 BA.debugLineNum = 621;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _gettimestring(RemoteObject _h) throws Exception{
try {
		Debug.PushSubsStack("GetTimeString (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,234);
if (RapidSub.canDelegate("gettimestring")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","gettimestring", _h);}
RemoteObject _num = RemoteObject.createImmutable(0);
RemoteObject _ampm = RemoteObject.createImmutable("");
Debug.locals.put("h", _h);
 BA.debugLineNum = 234;BA.debugLine="Sub GetTimeString (h As Int) As String";
Debug.ShouldStop(512);
 BA.debugLineNum = 235;BA.debugLine="Dim num As Int";
Debug.ShouldStop(1024);
_num = RemoteObject.createImmutable(0);Debug.locals.put("num", _num);
 BA.debugLineNum = 236;BA.debugLine="Dim ampm As String";
Debug.ShouldStop(2048);
_ampm = RemoteObject.createImmutable("");Debug.locals.put("ampm", _ampm);
 BA.debugLineNum = 237;BA.debugLine="If h = 0 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_h,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 238;BA.debugLine="num = 12";
Debug.ShouldStop(8192);
_num = BA.numberCast(int.class, 12);Debug.locals.put("num", _num);
 BA.debugLineNum = 239;BA.debugLine="ampm = \"am\"";
Debug.ShouldStop(16384);
_ampm = BA.ObjectToString("am");Debug.locals.put("ampm", _ampm);
 }else 
{ BA.debugLineNum = 240;BA.debugLine="Else if h = 12 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",_h,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 241;BA.debugLine="num = h";
Debug.ShouldStop(65536);
_num = _h;Debug.locals.put("num", _num);
 BA.debugLineNum = 242;BA.debugLine="ampm = \"pm\"";
Debug.ShouldStop(131072);
_ampm = BA.ObjectToString("pm");Debug.locals.put("ampm", _ampm);
 }else 
{ BA.debugLineNum = 243;BA.debugLine="Else if h > 12 Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean(">",_h,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 244;BA.debugLine="num = h - 12";
Debug.ShouldStop(524288);
_num = RemoteObject.solve(new RemoteObject[] {_h,RemoteObject.createImmutable(12)}, "-",1, 1);Debug.locals.put("num", _num);
 BA.debugLineNum = 245;BA.debugLine="If num = 12 Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",_num,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 246;BA.debugLine="ampm = \"am\"";
Debug.ShouldStop(2097152);
_ampm = BA.ObjectToString("am");Debug.locals.put("ampm", _ampm);
 }else {
 BA.debugLineNum = 248;BA.debugLine="ampm = \"pm\"";
Debug.ShouldStop(8388608);
_ampm = BA.ObjectToString("pm");Debug.locals.put("ampm", _ampm);
 };
 }else {
 BA.debugLineNum = 252;BA.debugLine="num = h";
Debug.ShouldStop(134217728);
_num = _h;Debug.locals.put("num", _num);
 BA.debugLineNum = 253;BA.debugLine="ampm = \"am\"";
Debug.ShouldStop(268435456);
_ampm = BA.ObjectToString("am");Debug.locals.put("ampm", _ampm);
 }}}
;
 BA.debugLineNum = 256;BA.debugLine="Return num & \":00\" & ampm";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.concat(_num,RemoteObject.createImmutable(":00"),_ampm);
 BA.debugLineNum = 257;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private Day_btn As Button";
day_module.mostCurrent._day_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private menupanel As Panel";
day_module.mostCurrent._menupanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private Svtimeline As ScrollView";
day_module.mostCurrent._svtimeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private date_todaylbl As Label";
day_module.mostCurrent._date_todaylbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private addpanel As Panel";
day_module.mostCurrent._addpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Addtask_btn As Button";
day_module.mostCurrent._addtask_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private svEvents As ScrollView";
day_module.mostCurrent._svevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private eventdescription_lbl As Label";
day_module.mostCurrent._eventdescription_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private eventTitle_lbl As Label";
day_module.mostCurrent._eventtitle_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private tags_lbl As Label";
day_module.mostCurrent._tags_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private dateToday_lbl As Label";
day_module.mostCurrent._datetoday_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private eventInfo_panel As Panel";
day_module.mostCurrent._eventinfo_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private EditInfoPanel As Panel";
day_module.mostCurrent._editinfopanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Dim currenttaggedEvent As Map";
day_module.mostCurrent._currenttaggedevent = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 34;BA.debugLine="Dim timeIndex As Int";
day_module._timeindex = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 35;BA.debugLine="Dim eventtype As String";
day_module.mostCurrent._eventtype = RemoteObject.createImmutable("");
 //BA.debugLineNum = 36;BA.debugLine="Dim currentevId As Long";
day_module._currentevid = RemoteObject.createImmutable(0L);
 //BA.debugLineNum = 38;BA.debugLine="Private editTitle_et As EditText";
day_module.mostCurrent._edittitle_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private editDescription_et As EditText";
day_module.mostCurrent._editdescription_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private deletepanel As Panel";
day_module.mostCurrent._deletepanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private addTL_et As EditText";
day_module.mostCurrent._addtl_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private addEventTL_panel As Panel";
day_module.mostCurrent._addeventtl_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private timelabel As Label";
day_module.mostCurrent._timelabel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private deleteTLevent_confirmationpanel As Panel";
day_module.mostCurrent._deletetlevent_confirmationpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 45;BA.debugLine="Private starttimelineSP As Spinner";
day_module.mostCurrent._starttimelinesp = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 46;BA.debugLine="Private endtimelineSP As Spinner";
day_module.mostCurrent._endtimelinesp = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private taskrb As RadioButton";
day_module.mostCurrent._taskrb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private eventrb As RadioButton";
day_module.mostCurrent._eventrb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private birthdayrb As RadioButton";
day_module.mostCurrent._birthdayrb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private ooorb As RadioButton";
day_module.mostCurrent._ooorb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _hour_click() throws Exception{
try {
		Debug.PushSubsStack("hour_click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,320);
if (RapidSub.canDelegate("hour_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","hour_click");}
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _tappedindex = RemoteObject.createImmutable("");
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 320;BA.debugLine="Sub hour_click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 321;BA.debugLine="Dim p As Panel =  Sender";
Debug.ShouldStop(1);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_p = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), day_module.mostCurrent.__c.runMethod(false,"Sender",day_module.mostCurrent.activityBA));Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 322;BA.debugLine="Dim tappedIndex = p.tag";
Debug.ShouldStop(2);
_tappedindex = BA.ObjectToString(_p.runMethod(false,"getTag"));Debug.locals.put("tappedIndex", _tappedindex);Debug.locals.put("tappedIndex", _tappedindex);
 BA.debugLineNum = 323;BA.debugLine="timeIndex = p.tag";
Debug.ShouldStop(4);
day_module._timeindex = BA.numberCast(int.class, _p.runMethod(false,"getTag"));
 BA.debugLineNum = 325;BA.debugLine="addEventTL_panel.Visible  =True";
Debug.ShouldStop(16);
day_module.mostCurrent._addeventtl_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 327;BA.debugLine="Dim eventmap As Map";
Debug.ShouldStop(64);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 328;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
Debug.ShouldStop(128);
if (day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(true,"ContainsKey",(Object)((day_module._currentdate))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 329;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
Debug.ShouldStop(256);
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);
 }else {
 BA.debugLineNum = 331;BA.debugLine="eventmap = MapInitializer";
Debug.ShouldStop(1024);
_eventmap = _mapinitializer();Debug.locals.put("eventmap", _eventmap);
 };
 BA.debugLineNum = 333;BA.debugLine="Dim timeline As List";
Debug.ShouldStop(4096);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 334;BA.debugLine="If eventmap.containskey(\"Timeline\") Then";
Debug.ShouldStop(8192);
if (_eventmap.runMethod(true,"ContainsKey",(Object)((RemoteObject.createImmutable("Timeline")))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 335;BA.debugLine="timeline = eventmap.Get(\"Timeline\")";
Debug.ShouldStop(16384);
_timeline = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Timeline")))));Debug.locals.put("timeline", _timeline);
 }else {
 BA.debugLineNum = 337;BA.debugLine="timeline.Initialize";
Debug.ShouldStop(65536);
_timeline.runVoidMethod ("Initialize");
 BA.debugLineNum = 338;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
Debug.ShouldStop(131072);
_eventmap.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Timeline"))),(Object)((_timeline.getObject())));
 };
 BA.debugLineNum = 341;BA.debugLine="starttimelineSP.SelectedIndex = timeIndex";
Debug.ShouldStop(1048576);
day_module.mostCurrent._starttimelinesp.runMethod(true,"setSelectedIndex",day_module._timeindex);
 BA.debugLineNum = 342;BA.debugLine="endtimelineSP.SelectedIndex = Min(timeIndex +1, 2";
Debug.ShouldStop(2097152);
day_module.mostCurrent._endtimelinesp.runMethod(true,"setSelectedIndex",BA.numberCast(int.class, day_module.mostCurrent.__c.runMethod(true,"Min",(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {day_module._timeindex,RemoteObject.createImmutable(1)}, "+",1, 1))),(Object)(BA.numberCast(double.class, 24)))));
 BA.debugLineNum = 343;BA.debugLine="eventrb.Checked = True";
Debug.ShouldStop(4194304);
day_module.mostCurrent._eventrb.runMethodAndSync(true,"setChecked",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 347;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _identifycolor(RemoteObject _typeofevent) throws Exception{
try {
		Debug.PushSubsStack("IdentifyColor (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,172);
if (RapidSub.canDelegate("identifycolor")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","identifycolor", _typeofevent);}
RemoteObject _mycolor = RemoteObject.createImmutable(0);
Debug.locals.put("typeofevent", _typeofevent);
 BA.debugLineNum = 172;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
Debug.ShouldStop(2048);
 BA.debugLineNum = 173;BA.debugLine="Dim mycolor As Int";
Debug.ShouldStop(4096);
_mycolor = RemoteObject.createImmutable(0);Debug.locals.put("mycolor", _mycolor);
 BA.debugLineNum = 174;BA.debugLine="If typeofevent = \"Task\" Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Task"))) { 
 BA.debugLineNum = 175;BA.debugLine="mycolor = Colors.ARGB(255, 0, 191, 255)";
Debug.ShouldStop(16384);
_mycolor = day_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 191)),(Object)(BA.numberCast(int.class, 255)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 176;BA.debugLine="Else if typeofevent = \"Event\" Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Event"))) { 
 BA.debugLineNum = 177;BA.debugLine="mycolor = Colors.ARGB(255, 152, 255, 152)";
Debug.ShouldStop(65536);
_mycolor = day_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 152)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 152)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 178;BA.debugLine="Else if typeofevent = \"Birthday\" Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Birthday"))) { 
 BA.debugLineNum = 179;BA.debugLine="mycolor = Colors.ARGB(255, 255, 182, 193)";
Debug.ShouldStop(262144);
_mycolor = day_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 182)),(Object)(BA.numberCast(int.class, 193)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 180;BA.debugLine="Else if typeofevent = \"OOO\" Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("OOO"))) { 
 BA.debugLineNum = 181;BA.debugLine="mycolor = Colors.ARGB(255, 255, 215, 0)";
Debug.ShouldStop(1048576);
_mycolor = day_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 215)),(Object)(BA.numberCast(int.class, 0)));Debug.locals.put("mycolor", _mycolor);
 }}}}
;
 BA.debugLineNum = 183;BA.debugLine="Return mycolor";
Debug.ShouldStop(4194304);
if (true) return _mycolor;
 BA.debugLineNum = 184;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _mainevent_click() throws Exception{
try {
		Debug.PushSubsStack("mainEvent_click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,156);
if (RapidSub.canDelegate("mainevent_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","mainevent_click");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 156;BA.debugLine="Sub mainEvent_click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 157;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
Debug.ShouldStop(268435456);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)(((day_module._currentdate)))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 158;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\")";
Debug.ShouldStop(536870912);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allevents = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("AllEvents")))));Debug.locals.put("allevents", _allevents);Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 159;BA.debugLine="Dim lbl As Label = Sender";
Debug.ShouldStop(1073741824);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
_lbl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), day_module.mostCurrent.__c.runMethod(false,"Sender",day_module.mostCurrent.activityBA));Debug.locals.put("lbl", _lbl);Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 160;BA.debugLine="Dim ev As Map = allevents.get(lbl.Tag)";
Debug.ShouldStop(-2147483648);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _allevents.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _lbl.runMethod(false,"getTag")))));Debug.locals.put("ev", _ev);Debug.locals.put("ev", _ev);
 BA.debugLineNum = 161;BA.debugLine="currenttaggedEvent = ev";
Debug.ShouldStop(1);
day_module.mostCurrent._currenttaggedevent = _ev;
 BA.debugLineNum = 162;BA.debugLine="currentIndex = lbl.tag";
Debug.ShouldStop(2);
day_module._currentindex = BA.numberCast(int.class, _lbl.runMethod(false,"getTag"));
 BA.debugLineNum = 164;BA.debugLine="eventInfo_panel.Visible = True";
Debug.ShouldStop(8);
day_module.mostCurrent._eventinfo_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 165;BA.debugLine="dateToday_lbl.Text = currentDate";
Debug.ShouldStop(16);
day_module.mostCurrent._datetoday_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(day_module._currentdate));
 BA.debugLineNum = 166;BA.debugLine="eventTitle_lbl.Text = ev.get(\"Title\")";
Debug.ShouldStop(32);
day_module.mostCurrent._eventtitle_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title"))))));
 BA.debugLineNum = 167;BA.debugLine="eventdescription_lbl.Text = ev.Get(\"Description\")";
Debug.ShouldStop(64);
day_module.mostCurrent._eventdescription_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Description"))))));
 BA.debugLineNum = 168;BA.debugLine="tags_lbl.text = ev.Get(\"Tags\")";
Debug.ShouldStop(128);
day_module.mostCurrent._tags_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags"))))));
 BA.debugLineNum = 170;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _mapinitializer() throws Exception{
try {
		Debug.PushSubsStack("MapInitializer (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,351);
if (RapidSub.canDelegate("mapinitializer")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","mapinitializer");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 351;BA.debugLine="Sub MapInitializer As Map";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 352;BA.debugLine="Dim eventmap As Map";
Debug.ShouldStop(-2147483648);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 354;BA.debugLine="eventmap.Initialize";
Debug.ShouldStop(2);
_eventmap.runVoidMethod ("Initialize");
 BA.debugLineNum = 355;BA.debugLine="Dim allevents As List";
Debug.ShouldStop(4);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 356;BA.debugLine="allevents.initialize";
Debug.ShouldStop(8);
_allevents.runVoidMethod ("Initialize");
 BA.debugLineNum = 358;BA.debugLine="Dim timeline As List";
Debug.ShouldStop(32);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 359;BA.debugLine="timeline.initialize";
Debug.ShouldStop(64);
_timeline.runVoidMethod ("Initialize");
 BA.debugLineNum = 361;BA.debugLine="eventmap.Put(\"AllEvents\", allevents)";
Debug.ShouldStop(256);
_eventmap.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("AllEvents"))),(Object)((_allevents.getObject())));
 BA.debugLineNum = 362;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
Debug.ShouldStop(512);
_eventmap.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Timeline"))),(Object)((_timeline.getObject())));
 BA.debugLineNum = 364;BA.debugLine="CalendarActivity.CalendarMap.Put(currentDate, eve";
Debug.ShouldStop(2048);
day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runVoidMethod ("Put",(Object)((day_module._currentdate)),(Object)((_eventmap.getObject())));
 BA.debugLineNum = 366;BA.debugLine="Return eventmap";
Debug.ShouldStop(8192);
if (true) return _eventmap;
 BA.debugLineNum = 367;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _menu_btn_click() throws Exception{
try {
		Debug.PushSubsStack("menu_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,469);
if (RapidSub.canDelegate("menu_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","menu_btn_click");}
 BA.debugLineNum = 469;BA.debugLine="Private Sub menu_btn_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 470;BA.debugLine="menupanel.Visible =True";
Debug.ShouldStop(2097152);
day_module.mostCurrent._menupanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 471;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _month_btn_click() throws Exception{
try {
		Debug.PushSubsStack("Month_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,482);
if (RapidSub.canDelegate("month_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","month_btn_click");}
 BA.debugLineNum = 482;BA.debugLine="Private Sub Month_btn_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 483;BA.debugLine="Activity.Finish";
Debug.ShouldStop(4);
day_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 484;BA.debugLine="StartActivity(CalendarActivity)";
Debug.ShouldStop(8);
day_module.mostCurrent.__c.runVoidMethod ("StartActivity",day_module.processBA,(Object)((day_module.mostCurrent._calendaractivity.getObject())));
 BA.debugLineNum = 485;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ooo_btn_click() throws Exception{
try {
		Debug.PushSubsStack("ooo_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,516);
if (RapidSub.canDelegate("ooo_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","ooo_btn_click");}
 BA.debugLineNum = 516;BA.debugLine="Private Sub ooo_btn_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 517;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(16);
day_module.mostCurrent._addpanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 518;BA.debugLine="add_events_module.eventtype = \"OOO\"";
Debug.ShouldStop(32);
day_module.mostCurrent._add_events_module._eventtype /*RemoteObject*/  = BA.ObjectToString("OOO");
 BA.debugLineNum = 519;BA.debugLine="StartActivity(add_events_module)";
Debug.ShouldStop(64);
day_module.mostCurrent.__c.runVoidMethod ("StartActivity",day_module.processBA,(Object)((day_module.mostCurrent._add_events_module.getObject())));
 BA.debugLineNum = 520;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ooorb_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("ooorb_CheckedChange (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,611);
if (RapidSub.canDelegate("ooorb_checkedchange")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","ooorb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 611;BA.debugLine="Private Sub ooorb_CheckedChange(Checked As Boolean";
Debug.ShouldStop(4);
 BA.debugLineNum = 612;BA.debugLine="eventtype = \"OOO\"";
Debug.ShouldStop(8);
day_module.mostCurrent._eventtype = BA.ObjectToString("OOO");
 BA.debugLineNum = 613;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
 //BA.debugLineNum = 9;BA.debugLine="Dim currentDate As String";
day_module._currentdate = RemoteObject.createImmutable("");
 //BA.debugLineNum = 10;BA.debugLine="Dim addeventsfeedback As Boolean";
day_module._addeventsfeedback = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 11;BA.debugLine="Dim currentIndex As Int";
day_module._currentindex = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 12;BA.debugLine="Dim schedules As Map";
day_module._schedules = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _savecalendar() throws Exception{
try {
		Debug.PushSubsStack("SaveCalendar (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,125);
if (RapidSub.canDelegate("savecalendar")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","savecalendar");}
 BA.debugLineNum = 125;BA.debugLine="Sub SaveCalendar";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 126;BA.debugLine="CalendarActivity.kvs.put(\"CalendarKVS\", CalendarA";
Debug.ShouldStop(536870912);
day_module.mostCurrent._calendaractivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("CalendarKVS")),(Object)((day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .getObject())));
 BA.debugLineNum = 127;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _saveedit_btn_click() throws Exception{
try {
		Debug.PushSubsStack("saveEdit_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,541);
if (RapidSub.canDelegate("saveedit_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","saveedit_btn_click");}
 BA.debugLineNum = 541;BA.debugLine="Private Sub saveEdit_btn_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 542;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starttimelinesp.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, day_module.mostCurrent._endtimelinesp.runMethod(true,"getSelectedIndex"))) || RemoteObject.solveBoolean(">",day_module.mostCurrent._starttimelinesp.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, day_module.mostCurrent._endtimelinesp.runMethod(true,"getSelectedIndex")))) { 
 BA.debugLineNum = 543;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
Debug.ShouldStop(1073741824);
day_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Invalid Timeline")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),day_module.processBA);
 BA.debugLineNum = 544;BA.debugLine="Return";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 547;BA.debugLine="If editTitle_et.text = \"\" Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._edittitle_et.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 548;BA.debugLine="MsgboxAsync(\"Event must have name\", \"Error\")";
Debug.ShouldStop(8);
day_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Event must have name")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),day_module.processBA);
 BA.debugLineNum = 549;BA.debugLine="Return";
Debug.ShouldStop(16);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 551;BA.debugLine="currenttaggedEvent.Put(\"Title\", editTitle_et.Text";
Debug.ShouldStop(64);
day_module.mostCurrent._currenttaggedevent.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Title"))),(Object)((day_module.mostCurrent._edittitle_et.runMethod(true,"getText"))));
 BA.debugLineNum = 552;BA.debugLine="currenttaggedEvent.Put(\"Description\", editDescrip";
Debug.ShouldStop(128);
day_module.mostCurrent._currenttaggedevent.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Description"))),(Object)((day_module.mostCurrent._editdescription_et.runMethod(true,"getText"))));
 BA.debugLineNum = 554;BA.debugLine="x_EventInfo_btn_Click";
Debug.ShouldStop(512);
_x_eventinfo_btn_click();
 BA.debugLineNum = 555;BA.debugLine="eventTitle_lbl.text = editTitle_et.Text";
Debug.ShouldStop(1024);
day_module.mostCurrent._eventtitle_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(day_module.mostCurrent._edittitle_et.runMethod(true,"getText")));
 BA.debugLineNum = 556;BA.debugLine="eventdescription_lbl.Text = editDescription_et.te";
Debug.ShouldStop(2048);
day_module.mostCurrent._eventdescription_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(day_module.mostCurrent._editdescription_et.runMethod(true,"getText")));
 BA.debugLineNum = 557;BA.debugLine="eventInfo_panel.Visible = True";
Debug.ShouldStop(4096);
day_module.mostCurrent._eventinfo_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 558;BA.debugLine="SaveCalendar";
Debug.ShouldStop(8192);
_savecalendar();
 BA.debugLineNum = 559;BA.debugLine="DrawMainEvents";
Debug.ShouldStop(16384);
_drawmainevents();
 BA.debugLineNum = 562;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _savetl_btn_click() throws Exception{
try {
		Debug.PushSubsStack("saveTL_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,369);
if (RapidSub.canDelegate("savetl_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","savetl_btn_click");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
int _i = 0;
RemoteObject _existing = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _st = RemoteObject.createImmutable(0);
RemoteObject _en = RemoteObject.createImmutable(0);
 BA.debugLineNum = 369;BA.debugLine="Private Sub saveTL_btn_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 370;BA.debugLine="If starttimelineSP.SelectedIndex = endtimelineSP.";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._starttimelinesp.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, day_module.mostCurrent._endtimelinesp.runMethod(true,"getSelectedIndex"))) || RemoteObject.solveBoolean(">",day_module.mostCurrent._starttimelinesp.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, day_module.mostCurrent._endtimelinesp.runMethod(true,"getSelectedIndex")))) { 
 BA.debugLineNum = 371;BA.debugLine="MsgboxAsync(\"Invalid Timeline\", \"Error\")";
Debug.ShouldStop(262144);
day_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Invalid Timeline")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),day_module.processBA);
 BA.debugLineNum = 372;BA.debugLine="Return";
Debug.ShouldStop(524288);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 374;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarMa";
Debug.ShouldStop(2097152);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 375;BA.debugLine="If addTL_et.text = \"\" Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",day_module.mostCurrent._addtl_et.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 376;BA.debugLine="MsgboxAsync(\"Event must have a name\", \"Error\")";
Debug.ShouldStop(8388608);
day_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Event must have a name")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),day_module.processBA);
 BA.debugLineNum = 377;BA.debugLine="Return";
Debug.ShouldStop(16777216);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 380;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(curre";
Debug.ShouldStop(134217728);
if (day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(true,"ContainsKey",(Object)((day_module._currentdate))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 381;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(curr";
Debug.ShouldStop(268435456);
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), day_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((day_module._currentdate))));Debug.locals.put("eventmap", _eventmap);
 }else {
 BA.debugLineNum = 383;BA.debugLine="eventmap = MapInitializer";
Debug.ShouldStop(1073741824);
_eventmap = _mapinitializer();Debug.locals.put("eventmap", _eventmap);
 };
 BA.debugLineNum = 385;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
Debug.ShouldStop(1);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_timeline = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Timeline")))));Debug.locals.put("timeline", _timeline);Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 386;BA.debugLine="Dim ev As Map";
Debug.ShouldStop(2);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("ev", _ev);
 BA.debugLineNum = 387;BA.debugLine="ev.Initialize";
Debug.ShouldStop(4);
_ev.runVoidMethod ("Initialize");
 BA.debugLineNum = 389;BA.debugLine="ev.Put(\"ID\", DateTime.Now)";
Debug.ShouldStop(16);
_ev.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ID"))),(Object)((day_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))));
 BA.debugLineNum = 390;BA.debugLine="ev.Put(\"Title\", addTL_et.Text)";
Debug.ShouldStop(32);
_ev.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Title"))),(Object)((day_module.mostCurrent._addtl_et.runMethod(true,"getText"))));
 BA.debugLineNum = 391;BA.debugLine="ev.Put(\"Start\", starttimelineSP.SelectedIndex)";
Debug.ShouldStop(64);
_ev.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Start"))),(Object)((day_module.mostCurrent._starttimelinesp.runMethod(true,"getSelectedIndex"))));
 BA.debugLineNum = 392;BA.debugLine="ev.Put(\"End\", endtimelineSP.SelectedIndex)";
Debug.ShouldStop(128);
_ev.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("End"))),(Object)((day_module.mostCurrent._endtimelinesp.runMethod(true,"getSelectedIndex"))));
 BA.debugLineNum = 393;BA.debugLine="ev.Put(\"Tags\", eventtype)";
Debug.ShouldStop(256);
_ev.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Tags"))),(Object)((day_module.mostCurrent._eventtype)));
 BA.debugLineNum = 396;BA.debugLine="For i = timeline.Size - 1 To 0 Step -1";
Debug.ShouldStop(2048);
{
final int step23 = -1;
final int limit23 = 0;
_i = RemoteObject.solve(new RemoteObject[] {_timeline.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue() ;
for (;(step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23) ;_i = ((int)(0 + _i + step23))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 397;BA.debugLine="Dim existing As Map = timeline.Get(i)";
Debug.ShouldStop(4096);
_existing = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_existing = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _timeline.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("existing", _existing);Debug.locals.put("existing", _existing);
 BA.debugLineNum = 398;BA.debugLine="Dim st As Int = existing.Get(\"Start\")";
Debug.ShouldStop(8192);
_st = BA.numberCast(int.class, _existing.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Start")))));Debug.locals.put("st", _st);Debug.locals.put("st", _st);
 BA.debugLineNum = 399;BA.debugLine="Dim en As Int = existing.Get(\"End\")";
Debug.ShouldStop(16384);
_en = BA.numberCast(int.class, _existing.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("End")))));Debug.locals.put("en", _en);Debug.locals.put("en", _en);
 BA.debugLineNum = 402;BA.debugLine="If (starttimelineSP.SelectedIndex < en) And (end";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(".",BA.ObjectToBoolean((RemoteObject.solveBoolean("<",day_module.mostCurrent._starttimelinesp.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, _en))))) && RemoteObject.solveBoolean(".",BA.ObjectToBoolean((RemoteObject.solveBoolean(">",day_module.mostCurrent._endtimelinesp.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, _st)))))) { 
 BA.debugLineNum = 403;BA.debugLine="timeline.RemoveAt(i)";
Debug.ShouldStop(262144);
_timeline.runVoidMethod ("RemoveAt",(Object)(BA.numberCast(int.class, _i)));
 BA.debugLineNum = 404;BA.debugLine="Exit";
Debug.ShouldStop(524288);
if (true) break;
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 408;BA.debugLine="timeline.add(ev)";
Debug.ShouldStop(8388608);
_timeline.runVoidMethod ("Add",(Object)((_ev.getObject())));
 BA.debugLineNum = 410;BA.debugLine="SaveCalendar";
Debug.ShouldStop(33554432);
_savecalendar();
 BA.debugLineNum = 411;BA.debugLine="UpdateTimeLine";
Debug.ShouldStop(67108864);
_updatetimeline();
 BA.debugLineNum = 412;BA.debugLine="addEventTL_panel.Visible  = False";
Debug.ShouldStop(134217728);
day_module.mostCurrent._addeventtl_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 414;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _sched_btn_click() throws Exception{
try {
		Debug.PushSubsStack("sched_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,473);
if (RapidSub.canDelegate("sched_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","sched_btn_click");}
 BA.debugLineNum = 473;BA.debugLine="Private Sub sched_btn_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 474;BA.debugLine="Activity.Finish";
Debug.ShouldStop(33554432);
day_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 475;BA.debugLine="StartActivity(Schedule_module)";
Debug.ShouldStop(67108864);
day_module.mostCurrent.__c.runVoidMethod ("StartActivity",day_module.processBA,(Object)((day_module.mostCurrent._schedule_module.getObject())));
 BA.debugLineNum = 476;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setdate(RemoteObject _tagdate) throws Exception{
try {
		Debug.PushSubsStack("SetDate (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,417);
if (RapidSub.canDelegate("setdate")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","setdate", _tagdate);}
RemoteObject _parts = null;
RemoteObject _year = RemoteObject.createImmutable("");
RemoteObject _monthnum = RemoteObject.createImmutable(0);
RemoteObject _day = RemoteObject.createImmutable("");
RemoteObject _monthname = RemoteObject.createImmutable("");
RemoteObject _ts = RemoteObject.createImmutable(0L);
RemoteObject _weekdaynum = RemoteObject.createImmutable(0);
RemoteObject _week = RemoteObject.createImmutable("");
Debug.locals.put("Tagdate", _tagdate);
 BA.debugLineNum = 417;BA.debugLine="Sub SetDate(Tagdate As String) As String";
Debug.ShouldStop(1);
 BA.debugLineNum = 419;BA.debugLine="Dim parts() As String = Regex.Split(\"-\", Tagdate)";
Debug.ShouldStop(4);
_parts = day_module.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString("-")),(Object)(_tagdate));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 420;BA.debugLine="Dim year As String = parts(0)";
Debug.ShouldStop(8);
_year = _parts.getArrayElement(true,BA.numberCast(int.class, 0));Debug.locals.put("year", _year);Debug.locals.put("year", _year);
 BA.debugLineNum = 421;BA.debugLine="Dim monthNum As Int = parts(1)";
Debug.ShouldStop(16);
_monthnum = BA.numberCast(int.class, _parts.getArrayElement(true,BA.numberCast(int.class, 1)));Debug.locals.put("monthNum", _monthnum);Debug.locals.put("monthNum", _monthnum);
 BA.debugLineNum = 422;BA.debugLine="Dim day As String = parts(2)";
Debug.ShouldStop(32);
_day = _parts.getArrayElement(true,BA.numberCast(int.class, 2));Debug.locals.put("day", _day);Debug.locals.put("day", _day);
 BA.debugLineNum = 424;BA.debugLine="Dim monthName As String";
Debug.ShouldStop(128);
_monthname = RemoteObject.createImmutable("");Debug.locals.put("monthName", _monthname);
 BA.debugLineNum = 425;BA.debugLine="Select monthNum";
Debug.ShouldStop(256);
switch (BA.switchObjectToInt(_monthnum,BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7),BA.numberCast(int.class, 8),BA.numberCast(int.class, 9),BA.numberCast(int.class, 10),BA.numberCast(int.class, 11),BA.numberCast(int.class, 12))) {
case 0: {
 BA.debugLineNum = 426;BA.debugLine="Case 1: monthName = \"January\"";
Debug.ShouldStop(512);
_monthname = BA.ObjectToString("January");Debug.locals.put("monthName", _monthname);
 break; }
case 1: {
 BA.debugLineNum = 427;BA.debugLine="Case 2: monthName = \"February\"";
Debug.ShouldStop(1024);
_monthname = BA.ObjectToString("February");Debug.locals.put("monthName", _monthname);
 break; }
case 2: {
 BA.debugLineNum = 428;BA.debugLine="Case 3: monthName = \"March\"";
Debug.ShouldStop(2048);
_monthname = BA.ObjectToString("March");Debug.locals.put("monthName", _monthname);
 break; }
case 3: {
 BA.debugLineNum = 429;BA.debugLine="Case 4: monthName = \"April\"";
Debug.ShouldStop(4096);
_monthname = BA.ObjectToString("April");Debug.locals.put("monthName", _monthname);
 break; }
case 4: {
 BA.debugLineNum = 430;BA.debugLine="Case 5: monthName = \"May\"";
Debug.ShouldStop(8192);
_monthname = BA.ObjectToString("May");Debug.locals.put("monthName", _monthname);
 break; }
case 5: {
 BA.debugLineNum = 431;BA.debugLine="Case 6: monthName = \"June\"";
Debug.ShouldStop(16384);
_monthname = BA.ObjectToString("June");Debug.locals.put("monthName", _monthname);
 break; }
case 6: {
 BA.debugLineNum = 432;BA.debugLine="Case 7: monthName = \"July\"";
Debug.ShouldStop(32768);
_monthname = BA.ObjectToString("July");Debug.locals.put("monthName", _monthname);
 break; }
case 7: {
 BA.debugLineNum = 433;BA.debugLine="Case 8: monthName = \"August\"";
Debug.ShouldStop(65536);
_monthname = BA.ObjectToString("August");Debug.locals.put("monthName", _monthname);
 break; }
case 8: {
 BA.debugLineNum = 434;BA.debugLine="Case 9: monthName = \"September\"";
Debug.ShouldStop(131072);
_monthname = BA.ObjectToString("September");Debug.locals.put("monthName", _monthname);
 break; }
case 9: {
 BA.debugLineNum = 435;BA.debugLine="Case 10: monthName = \"October\"";
Debug.ShouldStop(262144);
_monthname = BA.ObjectToString("October");Debug.locals.put("monthName", _monthname);
 break; }
case 10: {
 BA.debugLineNum = 436;BA.debugLine="Case 11: monthName = \"November\"";
Debug.ShouldStop(524288);
_monthname = BA.ObjectToString("November");Debug.locals.put("monthName", _monthname);
 break; }
case 11: {
 BA.debugLineNum = 437;BA.debugLine="Case 12: monthName = \"December\"";
Debug.ShouldStop(1048576);
_monthname = BA.ObjectToString("December");Debug.locals.put("monthName", _monthname);
 break; }
}
;
 BA.debugLineNum = 440;BA.debugLine="Dim ts As Long = DateTime.DateParse(Tagdate)";
Debug.ShouldStop(8388608);
_ts = day_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"DateParse",(Object)(_tagdate));Debug.locals.put("ts", _ts);Debug.locals.put("ts", _ts);
 BA.debugLineNum = 441;BA.debugLine="Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)";
Debug.ShouldStop(16777216);
_weekdaynum = day_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetDayOfWeek",(Object)(_ts));Debug.locals.put("weekdayNum", _weekdaynum);Debug.locals.put("weekdayNum", _weekdaynum);
 BA.debugLineNum = 442;BA.debugLine="Dim week As String";
Debug.ShouldStop(33554432);
_week = RemoteObject.createImmutable("");Debug.locals.put("week", _week);
 BA.debugLineNum = 443;BA.debugLine="Select weekdayNum";
Debug.ShouldStop(67108864);
switch (BA.switchObjectToInt(_weekdaynum,BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7))) {
case 0: {
 BA.debugLineNum = 444;BA.debugLine="Case 1: week = \"Sunday\"";
Debug.ShouldStop(134217728);
_week = BA.ObjectToString("Sunday");Debug.locals.put("week", _week);
 break; }
case 1: {
 BA.debugLineNum = 445;BA.debugLine="Case 2: week = \"Monday\"";
Debug.ShouldStop(268435456);
_week = BA.ObjectToString("Monday");Debug.locals.put("week", _week);
 break; }
case 2: {
 BA.debugLineNum = 446;BA.debugLine="Case 3: week = \"Tuesday\"";
Debug.ShouldStop(536870912);
_week = BA.ObjectToString("Tuesday");Debug.locals.put("week", _week);
 break; }
case 3: {
 BA.debugLineNum = 447;BA.debugLine="Case 4: week = \"Wednesday\"";
Debug.ShouldStop(1073741824);
_week = BA.ObjectToString("Wednesday");Debug.locals.put("week", _week);
 break; }
case 4: {
 BA.debugLineNum = 448;BA.debugLine="Case 5: week = \"Thursday\"";
Debug.ShouldStop(-2147483648);
_week = BA.ObjectToString("Thursday");Debug.locals.put("week", _week);
 break; }
case 5: {
 BA.debugLineNum = 449;BA.debugLine="Case 6: week = \"Friday\"";
Debug.ShouldStop(1);
_week = BA.ObjectToString("Friday");Debug.locals.put("week", _week);
 break; }
case 6: {
 BA.debugLineNum = 450;BA.debugLine="Case 7: week = \"Saturday\"";
Debug.ShouldStop(2);
_week = BA.ObjectToString("Saturday");Debug.locals.put("week", _week);
 break; }
}
;
 BA.debugLineNum = 453;BA.debugLine="Return week & \", \" & monthName & \" \" & day & \", \"";
Debug.ShouldStop(16);
if (true) return RemoteObject.concat(_week,RemoteObject.createImmutable(", "),_monthname,RemoteObject.createImmutable(" "),_day,RemoteObject.createImmutable(", "),_year);
 BA.debugLineNum = 454;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setupspinners() throws Exception{
try {
		Debug.PushSubsStack("SetUpSpinners (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,115);
if (RapidSub.canDelegate("setupspinners")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","setupspinners");}
RemoteObject _hours = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
 BA.debugLineNum = 115;BA.debugLine="Sub SetUpSpinners";
Debug.ShouldStop(262144);
 BA.debugLineNum = 116;BA.debugLine="Dim hours As List";
Debug.ShouldStop(524288);
_hours = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("hours", _hours);
 BA.debugLineNum = 117;BA.debugLine="hours.Initialize";
Debug.ShouldStop(1048576);
_hours.runVoidMethod ("Initialize");
 BA.debugLineNum = 118;BA.debugLine="For i = 0 To 24";
Debug.ShouldStop(2097152);
{
final int step3 = 1;
final int limit3 = 24;
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 119;BA.debugLine="hours.Add(GetTimeString(i))";
Debug.ShouldStop(4194304);
_hours.runVoidMethod ("Add",(Object)((_gettimestring(BA.numberCast(int.class, _i)))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 121;BA.debugLine="starttimelineSP.AddAll(hours)";
Debug.ShouldStop(16777216);
day_module.mostCurrent._starttimelinesp.runVoidMethod ("AddAll",(Object)(_hours));
 BA.debugLineNum = 122;BA.debugLine="endtimelineSP.AddAll(hours)";
Debug.ShouldStop(33554432);
day_module.mostCurrent._endtimelinesp.runVoidMethod ("AddAll",(Object)(_hours));
 BA.debugLineNum = 123;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _taskrb_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("taskrb_CheckedChange (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,623);
if (RapidSub.canDelegate("taskrb_checkedchange")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","taskrb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 623;BA.debugLine="Private Sub taskrb_CheckedChange(Checked As Boolea";
Debug.ShouldStop(16384);
 BA.debugLineNum = 624;BA.debugLine="eventtype = \"Task\"";
Debug.ShouldStop(32768);
day_module.mostCurrent._eventtype = BA.ObjectToString("Task");
 BA.debugLineNum = 625;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _timelineevent_click() throws Exception{
try {
		Debug.PushSubsStack("timelineEvent_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,297);
if (RapidSub.canDelegate("timelineevent_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","timelineevent_click");}
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 297;BA.debugLine="Sub timelineEvent_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 298;BA.debugLine="Dim lbl As Label = Sender";
Debug.ShouldStop(512);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
_lbl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), day_module.mostCurrent.__c.runMethod(false,"Sender",day_module.mostCurrent.activityBA));Debug.locals.put("lbl", _lbl);Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 299;BA.debugLine="Dim ev As Map = lbl.Tag";
Debug.ShouldStop(1024);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _lbl.runMethod(false,"getTag"));Debug.locals.put("ev", _ev);Debug.locals.put("ev", _ev);
 BA.debugLineNum = 300;BA.debugLine="currenttaggedEvent = ev";
Debug.ShouldStop(2048);
day_module.mostCurrent._currenttaggedevent = _ev;
 BA.debugLineNum = 301;BA.debugLine="currentevId = ev.Get(\"ID\")";
Debug.ShouldStop(4096);
day_module._currentevid = BA.numberCast(long.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("ID")))));
 BA.debugLineNum = 304;BA.debugLine="addEventTL_panel.Visible = True";
Debug.ShouldStop(32768);
day_module.mostCurrent._addeventtl_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 307;BA.debugLine="addTL_et.Text = ev.Get(\"Title\")";
Debug.ShouldStop(262144);
day_module.mostCurrent._addtl_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title"))))));
 BA.debugLineNum = 308;BA.debugLine="starttimelineSP.SelectedIndex = ev.Get(\"Start\")";
Debug.ShouldStop(524288);
day_module.mostCurrent._starttimelinesp.runMethod(true,"setSelectedIndex",BA.numberCast(int.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Start"))))));
 BA.debugLineNum = 309;BA.debugLine="endtimelineSP.SelectedIndex = ev.Get(\"End\")";
Debug.ShouldStop(1048576);
day_module.mostCurrent._endtimelinesp.runMethod(true,"setSelectedIndex",BA.numberCast(int.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("End"))))));
 BA.debugLineNum = 312;BA.debugLine="Select Case ev.Get(\"Tags\")";
Debug.ShouldStop(8388608);
switch (BA.switchObjectToInt(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags")))),RemoteObject.createImmutable(("Task")),RemoteObject.createImmutable(("Event")),RemoteObject.createImmutable(("Birthday")),RemoteObject.createImmutable(("OOO")))) {
case 0: {
 BA.debugLineNum = 313;BA.debugLine="Case \"Task\": taskrb.Checked = True";
Debug.ShouldStop(16777216);
day_module.mostCurrent._taskrb.runMethodAndSync(true,"setChecked",day_module.mostCurrent.__c.getField(true,"True"));
 break; }
case 1: {
 BA.debugLineNum = 314;BA.debugLine="Case \"Event\": eventrb.Checked = True";
Debug.ShouldStop(33554432);
day_module.mostCurrent._eventrb.runMethodAndSync(true,"setChecked",day_module.mostCurrent.__c.getField(true,"True"));
 break; }
case 2: {
 BA.debugLineNum = 315;BA.debugLine="Case \"Birthday\": birthdayrb.Checked = True";
Debug.ShouldStop(67108864);
day_module.mostCurrent._birthdayrb.runMethodAndSync(true,"setChecked",day_module.mostCurrent.__c.getField(true,"True"));
 break; }
case 3: {
 BA.debugLineNum = 316;BA.debugLine="Case \"OOO\": ooorb.Checked = True";
Debug.ShouldStop(134217728);
day_module.mostCurrent._ooorb.runMethodAndSync(true,"setChecked",day_module.mostCurrent.__c.getField(true,"True"));
 break; }
}
;
 BA.debugLineNum = 318;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _updatetimeline() throws Exception{
try {
		Debug.PushSubsStack("UpdateTimeLine (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,186);
if (RapidSub.canDelegate("updatetimeline")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","updatetimeline");}
 BA.debugLineNum = 186;BA.debugLine="Sub UpdateTimeLine";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="DrawMainEvents";
Debug.ShouldStop(67108864);
_drawmainevents();
 BA.debugLineNum = 188;BA.debugLine="DrawHourLabels";
Debug.ShouldStop(134217728);
_drawhourlabels();
 BA.debugLineNum = 189;BA.debugLine="DrawTimelineEvents";
Debug.ShouldStop(268435456);
_drawtimelineevents();
 BA.debugLineNum = 190;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _x_eventinfo_btn_click() throws Exception{
try {
		Debug.PushSubsStack("x_EventInfo_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,528);
if (RapidSub.canDelegate("x_eventinfo_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","x_eventinfo_btn_click");}
 BA.debugLineNum = 528;BA.debugLine="Private Sub x_EventInfo_btn_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 529;BA.debugLine="eventInfo_panel.Visible = False";
Debug.ShouldStop(65536);
day_module.mostCurrent._eventinfo_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 530;BA.debugLine="EditInfoPanel.visible = False";
Debug.ShouldStop(131072);
day_module.mostCurrent._editinfopanel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 531;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _x_tlevent_btn_click() throws Exception{
try {
		Debug.PushSubsStack("x_TLevent_btn_Click (day_module) ","day_module",6,day_module.mostCurrent.activityBA,day_module.mostCurrent,578);
if (RapidSub.canDelegate("x_tlevent_btn_click")) { return b4a.example.day_module.remoteMe.runUserSub(false, "day_module","x_tlevent_btn_click");}
 BA.debugLineNum = 578;BA.debugLine="Private Sub x_TLevent_btn_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 579;BA.debugLine="addEventTL_panel.Visible = False";
Debug.ShouldStop(4);
day_module.mostCurrent._addeventtl_panel.runMethod(true,"setVisible",day_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 580;BA.debugLine="addTL_et.text = \"\"";
Debug.ShouldStop(8);
day_module.mostCurrent._addtl_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 581;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}