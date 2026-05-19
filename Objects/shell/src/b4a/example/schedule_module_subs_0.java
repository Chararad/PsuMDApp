package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class schedule_module_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,22);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","activity_create", _firsttime);}
RemoteObject _bd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 24;BA.debugLine="Dim bd As BitmapDrawable";
Debug.ShouldStop(8388608);
_bd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd", _bd);
 BA.debugLineNum = 25;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(16777216);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 26;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(33554432);
switch (BA.switchObjectToInt(schedule_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 28;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",schedule_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,schedule_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayout\")";
Debug.ShouldStop(268435456);
schedule_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Schedule_ModuleLayout")),schedule_module.mostCurrent.activityBA);
 BA.debugLineNum = 30;BA.debugLine="cd.Initialize(Colors.ARGB(125, 98, 43, 20), 20";
Debug.ShouldStop(536870912);
_cd.runVoidMethod ("Initialize",(Object)(schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 125)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 31;BA.debugLine="sched_btn.Background = cd";
Debug.ShouldStop(1073741824);
schedule_module.mostCurrent._sched_btn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 32;BA.debugLine="sched_btn.TextColor = Colors.White";
Debug.ShouldStop(-2147483648);
schedule_module.mostCurrent._sched_btn.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayoutDark";
Debug.ShouldStop(2);
schedule_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Schedule_ModuleLayoutDark")),schedule_module.mostCurrent.activityBA);
 BA.debugLineNum = 35;BA.debugLine="cd.Initialize(Colors.ARGB(125, 59, 117, 151),";
Debug.ShouldStop(4);
_cd.runVoidMethod ("Initialize",(Object)(schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 125)),(Object)(BA.numberCast(int.class, 59)),(Object)(BA.numberCast(int.class, 117)),(Object)(BA.numberCast(int.class, 151)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 36;BA.debugLine="sched_btn.Background = cd";
Debug.ShouldStop(8);
schedule_module.mostCurrent._sched_btn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 37;BA.debugLine="sched_btn.TextColor = Colors.White";
Debug.ShouldStop(16);
schedule_module.mostCurrent._sched_btn.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 40;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("=",schedule_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,schedule_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayout2\")";
Debug.ShouldStop(256);
schedule_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Schedule_ModuleLayout2")),schedule_module.mostCurrent.activityBA);
 BA.debugLineNum = 42;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
Debug.ShouldStop(512);
_bd.runVoidMethod ("Initialize",(Object)((schedule_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(schedule_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Skeumorphic Pink .png"))).getObject())));
 BA.debugLineNum = 43;BA.debugLine="sched_btn.Background = bd";
Debug.ShouldStop(1024);
schedule_module.mostCurrent._sched_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 44;BA.debugLine="sched_btn.TextColor = Colors.White";
Debug.ShouldStop(2048);
schedule_module.mostCurrent._sched_btn.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 46;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayoutDark";
Debug.ShouldStop(8192);
schedule_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Schedule_ModuleLayoutDark2")),schedule_module.mostCurrent.activityBA);
 BA.debugLineNum = 47;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"pink";
Debug.ShouldStop(16384);
_bd.runVoidMethod ("Initialize",(Object)((schedule_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(schedule_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("pink Skeumorphic Button DARK.png"))).getObject())));
 BA.debugLineNum = 48;BA.debugLine="sched_btn.Background = bd";
Debug.ShouldStop(32768);
schedule_module.mostCurrent._sched_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 49;BA.debugLine="sched_btn.TextColor = Colors.White";
Debug.ShouldStop(65536);
schedule_module.mostCurrent._sched_btn.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 2: {
 BA.debugLineNum = 52;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",schedule_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,schedule_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 53;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayout3\")";
Debug.ShouldStop(1048576);
schedule_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Schedule_ModuleLayout3")),schedule_module.mostCurrent.activityBA);
 BA.debugLineNum = 54;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(2097152);
_bd.runVoidMethod ("Initialize",(Object)((schedule_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(schedule_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 55;BA.debugLine="sched_btn.Background = bd";
Debug.ShouldStop(4194304);
schedule_module.mostCurrent._sched_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 56;BA.debugLine="sched_btn.TextColor = Colors.White";
Debug.ShouldStop(8388608);
schedule_module.mostCurrent._sched_btn.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 58;BA.debugLine="Activity.LoadLayout(\"Schedule_ModuleLayoutDark";
Debug.ShouldStop(33554432);
schedule_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Schedule_ModuleLayoutDark3")),schedule_module.mostCurrent.activityBA);
 BA.debugLineNum = 59;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(67108864);
_bd.runVoidMethod ("Initialize",(Object)((schedule_module.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(schedule_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtnd.png"))).getObject())));
 BA.debugLineNum = 60;BA.debugLine="sched_btn.Background = bd";
Debug.ShouldStop(134217728);
schedule_module.mostCurrent._sched_btn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 61;BA.debugLine="sched_btn.TextColor = Colors.White";
Debug.ShouldStop(268435456);
schedule_module.mostCurrent._sched_btn.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
}
;
 BA.debugLineNum = 65;BA.debugLine="DrawSchedule";
Debug.ShouldStop(1);
_drawschedule();
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
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,244);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 244;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(524288);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,240);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","activity_resume");}
 BA.debugLineNum = 240;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(32768);
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
public static RemoteObject  _day_btn_click() throws Exception{
try {
		Debug.PushSubsStack("Day_btn_Click (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,257);
if (RapidSub.canDelegate("day_btn_click")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","day_btn_click");}
RemoteObject _currentyear = RemoteObject.createImmutable(0);
RemoteObject _currentmonth = RemoteObject.createImmutable(0);
RemoteObject _currentday = RemoteObject.createImmutable(0);
 BA.debugLineNum = 257;BA.debugLine="Private Sub Day_btn_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 258;BA.debugLine="Dim currentyear As Int = DateTime.GetYear(DateTim";
Debug.ShouldStop(2);
_currentyear = schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetYear",(Object)(schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentyear", _currentyear);Debug.locals.put("currentyear", _currentyear);
 BA.debugLineNum = 259;BA.debugLine="Dim currentmonth As Int = DateTime.GetMonth(DateT";
Debug.ShouldStop(4);
_currentmonth = schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetMonth",(Object)(schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentmonth", _currentmonth);Debug.locals.put("currentmonth", _currentmonth);
 BA.debugLineNum = 260;BA.debugLine="Dim currentday As Int = DateTime.GetDayOfMonth(Da";
Debug.ShouldStop(8);
_currentday = schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetDayOfMonth",(Object)(schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentday", _currentday);Debug.locals.put("currentday", _currentday);
 BA.debugLineNum = 261;BA.debugLine="day_module.currentDate = currentyear & \"-\" & curr";
Debug.ShouldStop(16);
schedule_module.mostCurrent._day_module._currentdate /*RemoteObject*/  = RemoteObject.concat(_currentyear,RemoteObject.createImmutable("-"),_currentmonth,RemoteObject.createImmutable("-"),_currentday);
 BA.debugLineNum = 262;BA.debugLine="Activity.Finish";
Debug.ShouldStop(32);
schedule_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 263;BA.debugLine="StartActivity(day_module)";
Debug.ShouldStop(64);
schedule_module.mostCurrent.__c.runVoidMethod ("StartActivity",schedule_module.processBA,(Object)((schedule_module.mostCurrent._day_module.getObject())));
 BA.debugLineNum = 264;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _drawschedule() throws Exception{
try {
		Debug.PushSubsStack("DrawSchedule (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,69);
if (RapidSub.canDelegate("drawschedule")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","drawschedule");}
RemoteObject _y = RemoteObject.createImmutable(0);
RemoteObject _sorteddates = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _keys = RemoteObject.createImmutable("");
RemoteObject _date = RemoteObject.createImmutable("");
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _lbldate = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _st = RemoteObject.createImmutable(0);
RemoteObject _en = RemoteObject.createImmutable(0);
 BA.debugLineNum = 69;BA.debugLine="Sub DrawSchedule";
Debug.ShouldStop(16);
 BA.debugLineNum = 70;BA.debugLine="scheduleSV.Panel.RemoveAllViews";
Debug.ShouldStop(32);
schedule_module.mostCurrent._schedulesv.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 72;BA.debugLine="Dim y As Int = 0";
Debug.ShouldStop(128);
_y = BA.numberCast(int.class, 0);Debug.locals.put("y", _y);Debug.locals.put("y", _y);
 BA.debugLineNum = 73;BA.debugLine="Dim sortedDates As List";
Debug.ShouldStop(256);
_sorteddates = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("sortedDates", _sorteddates);
 BA.debugLineNum = 74;BA.debugLine="sortedDates.Initialize";
Debug.ShouldStop(512);
_sorteddates.runVoidMethod ("Initialize");
 BA.debugLineNum = 76;BA.debugLine="If CalendarActivity.CalendarMap.Size = 0 Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",schedule_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 77;BA.debugLine="noschedlabel.Text = \"No Schedule\"";
Debug.ShouldStop(4096);
schedule_module.mostCurrent._noschedlabel.runMethod(true,"setText",BA.ObjectToCharSequence("No Schedule"));
 };
 BA.debugLineNum = 80;BA.debugLine="For Each keys As String In CalendarActivity.Calen";
Debug.ShouldStop(32768);
{
final RemoteObject group8 = schedule_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Keys");
final int groupLen8 = group8.runMethod(true,"getSize").<Integer>get()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_keys = BA.ObjectToString(group8.runMethod(false,"Get",index8));Debug.locals.put("keys", _keys);
Debug.locals.put("keys", _keys);
 BA.debugLineNum = 81;BA.debugLine="sortedDates.Add(keys)";
Debug.ShouldStop(65536);
_sorteddates.runVoidMethod ("Add",(Object)((_keys)));
 BA.debugLineNum = 82;BA.debugLine="Log(keys)";
Debug.ShouldStop(131072);
schedule_module.mostCurrent.__c.runVoidMethod ("LogImpl","45767181",_keys,0);
 }
}Debug.locals.put("keys", _keys);
;
 BA.debugLineNum = 85;BA.debugLine="sortedDates.Sort(True)";
Debug.ShouldStop(1048576);
_sorteddates.runVoidMethod ("Sort",(Object)(schedule_module.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 87;BA.debugLine="For Each date As String In sortedDates";
Debug.ShouldStop(4194304);
{
final RemoteObject group13 = _sorteddates;
final int groupLen13 = group13.runMethod(true,"getSize").<Integer>get()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_date = BA.ObjectToString(group13.runMethod(false,"Get",index13));Debug.locals.put("date", _date);
Debug.locals.put("date", _date);
 BA.debugLineNum = 88;BA.debugLine="Dim eventmap As Map = CalendarActivity.CalendarM";
Debug.ShouldStop(8388608);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), schedule_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((_date))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 89;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvents\"";
Debug.ShouldStop(16777216);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allevents = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("AllEvents")))));Debug.locals.put("allevents", _allevents);Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 90;BA.debugLine="Dim timeline As List = eventmap.Get(\"Timeline\")";
Debug.ShouldStop(33554432);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_timeline = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Timeline")))));Debug.locals.put("timeline", _timeline);Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 92;BA.debugLine="Dim lbldate As Label";
Debug.ShouldStop(134217728);
_lbldate = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbldate", _lbldate);
 BA.debugLineNum = 93;BA.debugLine="lbldate.initialize(\"\")";
Debug.ShouldStop(268435456);
_lbldate.runVoidMethod ("Initialize",schedule_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 94;BA.debugLine="lbldate.Text = SetDate(date)";
Debug.ShouldStop(536870912);
_lbldate.runMethod(true,"setText",BA.ObjectToCharSequence(_setdate(_date)));
 BA.debugLineNum = 95;BA.debugLine="lbldate.TextSize = 16";
Debug.ShouldStop(1073741824);
_lbldate.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 96;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(-2147483648);
switch (BA.switchObjectToInt(schedule_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 98;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(2);
if (schedule_module.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 99;BA.debugLine="lbldate.Color = Colors.ARGB(120, 23, 20, 36)";
Debug.ShouldStop(4);
_lbldate.runVoidMethod ("setColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 23)),(Object)(BA.numberCast(int.class, 20)),(Object)(BA.numberCast(int.class, 36))));
 BA.debugLineNum = 100;BA.debugLine="lbldate.TextColor = Colors.Cyan";
Debug.ShouldStop(8);
_lbldate.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Cyan"));
 }else {
 BA.debugLineNum = 102;BA.debugLine="lbldate.Color = Colors.ARGB(120, 23, 20, 36)";
Debug.ShouldStop(32);
_lbldate.runVoidMethod ("setColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 23)),(Object)(BA.numberCast(int.class, 20)),(Object)(BA.numberCast(int.class, 36))));
 BA.debugLineNum = 103;BA.debugLine="lbldate.TextColor = Colors.Cyan";
Debug.ShouldStop(64);
_lbldate.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Cyan"));
 };
 break; }
case 1: {
 BA.debugLineNum = 106;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(512);
if (schedule_module.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 107;BA.debugLine="lbldate.Color = Colors.ARGB(120, 16, 26, 95)";
Debug.ShouldStop(1024);
_lbldate.runVoidMethod ("setColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 16)),(Object)(BA.numberCast(int.class, 26)),(Object)(BA.numberCast(int.class, 95))));
 BA.debugLineNum = 108;BA.debugLine="lbldate.TextColor = Colors.White";
Debug.ShouldStop(2048);
_lbldate.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 110;BA.debugLine="lbldate.Color = Colors.ARGB(200, 137, 162, 18";
Debug.ShouldStop(8192);
_lbldate.runVoidMethod ("setColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 200)),(Object)(BA.numberCast(int.class, 137)),(Object)(BA.numberCast(int.class, 162)),(Object)(BA.numberCast(int.class, 185))));
 BA.debugLineNum = 111;BA.debugLine="lbldate.TextColor = Colors.White";
Debug.ShouldStop(16384);
_lbldate.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 2: {
 BA.debugLineNum = 114;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(131072);
if (schedule_module.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 115;BA.debugLine="lbldate.Color = Colors.ARGB(120, 0, 0, 0)";
Debug.ShouldStop(262144);
_lbldate.runVoidMethod ("setColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0))));
 BA.debugLineNum = 116;BA.debugLine="lbldate.TextColor = Colors.Cyan";
Debug.ShouldStop(524288);
_lbldate.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Cyan"));
 }else {
 BA.debugLineNum = 118;BA.debugLine="lbldate.Color = Colors.ARGB(120, 255, 255, 25";
Debug.ShouldStop(2097152);
_lbldate.runVoidMethod ("setColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 119;BA.debugLine="lbldate.TextColor = Colors.RGB(79, 46, 46)";
Debug.ShouldStop(4194304);
_lbldate.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 79)),(Object)(BA.numberCast(int.class, 46)),(Object)(BA.numberCast(int.class, 46))));
 };
 break; }
}
;
 BA.debugLineNum = 124;BA.debugLine="If allevents.Size = 0 And timeline.size = 0 Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_allevents.runMethod(true,"getSize"),BA.numberCast(double.class, 0)) && RemoteObject.solveBoolean("=",_timeline.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 125;BA.debugLine="Continue";
Debug.ShouldStop(268435456);
if (true) continue;
 };
 BA.debugLineNum = 128;BA.debugLine="scheduleSV.Panel.AddView(lbldate, 0, y, schedule";
Debug.ShouldStop(-2147483648);
schedule_module.mostCurrent._schedulesv.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_lbldate.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(_y),(Object)(schedule_module.mostCurrent._schedulesv.runMethod(true,"getWidth")),(Object)(schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 129;BA.debugLine="y = y+ 40dip";
Debug.ShouldStop(1);
_y = RemoteObject.solve(new RemoteObject[] {_y,schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "+",1, 1);Debug.locals.put("y", _y);
 BA.debugLineNum = 131;BA.debugLine="For Each ev As Map In allevents";
Debug.ShouldStop(4);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group52 = _allevents;
final int groupLen52 = group52.runMethod(true,"getSize").<Integer>get()
;int index52 = 0;
;
for (; index52 < groupLen52;index52++){
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group52.runMethod(false,"Get",index52));Debug.locals.put("ev", _ev);
Debug.locals.put("ev", _ev);
 BA.debugLineNum = 132;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(8);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 133;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(16);
_lbl.runVoidMethod ("Initialize",schedule_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 134;BA.debugLine="lbl.Text = ev.Get(\"Title\")";
Debug.ShouldStop(32);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title"))))));
 BA.debugLineNum = 135;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
Debug.ShouldStop(64);
_lbl.runVoidMethod ("setColor",_identifycolor(BA.ObjectToString(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags")))))));
 BA.debugLineNum = 136;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(128);
_lbl.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 137;BA.debugLine="scheduleSV.Panel.AddView(lbl, 10dip, y, schedul";
Debug.ShouldStop(256);
schedule_module.mostCurrent._schedulesv.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_y),(Object)(RemoteObject.solve(new RemoteObject[] {schedule_module.mostCurrent._schedulesv.runMethod(true,"getWidth"),schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 138;BA.debugLine="y = y + 40dip";
Debug.ShouldStop(512);
_y = RemoteObject.solve(new RemoteObject[] {_y,schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "+",1, 1);Debug.locals.put("y", _y);
 }
}Debug.locals.put("ev", _ev);
;
 BA.debugLineNum = 142;BA.debugLine="For Each ev As Map In timeline";
Debug.ShouldStop(8192);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group61 = _timeline;
final int groupLen61 = group61.runMethod(true,"getSize").<Integer>get()
;int index61 = 0;
;
for (; index61 < groupLen61;index61++){
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group61.runMethod(false,"Get",index61));Debug.locals.put("ev", _ev);
Debug.locals.put("ev", _ev);
 BA.debugLineNum = 143;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(16384);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 144;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(32768);
_lbl.runVoidMethod ("Initialize",schedule_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 145;BA.debugLine="Dim st As Int = ev.Get(\"Start\")";
Debug.ShouldStop(65536);
_st = BA.numberCast(int.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Start")))));Debug.locals.put("st", _st);Debug.locals.put("st", _st);
 BA.debugLineNum = 146;BA.debugLine="Dim en As Int = ev.Get(\"End\")";
Debug.ShouldStop(131072);
_en = BA.numberCast(int.class, _ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("End")))));Debug.locals.put("en", _en);Debug.locals.put("en", _en);
 BA.debugLineNum = 147;BA.debugLine="lbl.Text = ev.Get(\"Title\") & \" (\" & GetTimeStri";
Debug.ShouldStop(262144);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title")))),RemoteObject.createImmutable(" ("),_gettimestring(_st),RemoteObject.createImmutable(" - "),_gettimestring(_en),RemoteObject.createImmutable(")"))));
 BA.debugLineNum = 149;BA.debugLine="lbl.Color = IdentifyColor(ev.Get(\"Tags\"))";
Debug.ShouldStop(1048576);
_lbl.runVoidMethod ("setColor",_identifycolor(BA.ObjectToString(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags")))))));
 BA.debugLineNum = 150;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(2097152);
_lbl.runMethod(true,"setTextColor",schedule_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 151;BA.debugLine="scheduleSV.Panel.AddView(lbl, 10dip, y, schedul";
Debug.ShouldStop(4194304);
schedule_module.mostCurrent._schedulesv.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_y),(Object)(RemoteObject.solve(new RemoteObject[] {schedule_module.mostCurrent._schedulesv.runMethod(true,"getWidth"),schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 152;BA.debugLine="y = y+40dip";
Debug.ShouldStop(8388608);
_y = RemoteObject.solve(new RemoteObject[] {_y,schedule_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "+",1, 1);Debug.locals.put("y", _y);
 }
}Debug.locals.put("ev", _ev);
;
 }
}Debug.locals.put("date", _date);
;
 BA.debugLineNum = 155;BA.debugLine="scheduleSV.Panel.Height = y";
Debug.ShouldStop(67108864);
schedule_module.mostCurrent._schedulesv.runMethod(false,"getPanel").runMethod(true,"setHeight",_y);
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
public static RemoteObject  _gettimestring(RemoteObject _h) throws Exception{
try {
		Debug.PushSubsStack("GetTimeString (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,160);
if (RapidSub.canDelegate("gettimestring")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","gettimestring", _h);}
RemoteObject _num = RemoteObject.createImmutable(0);
RemoteObject _ampm = RemoteObject.createImmutable("");
Debug.locals.put("h", _h);
 BA.debugLineNum = 160;BA.debugLine="Sub GetTimeString (h As Int) As String";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 161;BA.debugLine="Dim num As Int";
Debug.ShouldStop(1);
_num = RemoteObject.createImmutable(0);Debug.locals.put("num", _num);
 BA.debugLineNum = 162;BA.debugLine="Dim ampm As String";
Debug.ShouldStop(2);
_ampm = RemoteObject.createImmutable("");Debug.locals.put("ampm", _ampm);
 BA.debugLineNum = 163;BA.debugLine="If h = 0 Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",_h,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 164;BA.debugLine="num = 12";
Debug.ShouldStop(8);
_num = BA.numberCast(int.class, 12);Debug.locals.put("num", _num);
 BA.debugLineNum = 165;BA.debugLine="ampm = \"am\"";
Debug.ShouldStop(16);
_ampm = BA.ObjectToString("am");Debug.locals.put("ampm", _ampm);
 }else 
{ BA.debugLineNum = 166;BA.debugLine="Else if h = 12 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",_h,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 167;BA.debugLine="num = h";
Debug.ShouldStop(64);
_num = _h;Debug.locals.put("num", _num);
 BA.debugLineNum = 168;BA.debugLine="ampm = \"pm\"";
Debug.ShouldStop(128);
_ampm = BA.ObjectToString("pm");Debug.locals.put("ampm", _ampm);
 }else 
{ BA.debugLineNum = 169;BA.debugLine="Else if h > 12 Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean(">",_h,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 170;BA.debugLine="num = h - 12";
Debug.ShouldStop(512);
_num = RemoteObject.solve(new RemoteObject[] {_h,RemoteObject.createImmutable(12)}, "-",1, 1);Debug.locals.put("num", _num);
 BA.debugLineNum = 171;BA.debugLine="If num = 12 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",_num,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 172;BA.debugLine="ampm = \"am\"";
Debug.ShouldStop(2048);
_ampm = BA.ObjectToString("am");Debug.locals.put("ampm", _ampm);
 }else {
 BA.debugLineNum = 174;BA.debugLine="ampm = \"pm\"";
Debug.ShouldStop(8192);
_ampm = BA.ObjectToString("pm");Debug.locals.put("ampm", _ampm);
 };
 }else {
 BA.debugLineNum = 178;BA.debugLine="num = h";
Debug.ShouldStop(131072);
_num = _h;Debug.locals.put("num", _num);
 BA.debugLineNum = 179;BA.debugLine="ampm = \"am\"";
Debug.ShouldStop(262144);
_ampm = BA.ObjectToString("am");Debug.locals.put("ampm", _ampm);
 }}}
;
 BA.debugLineNum = 182;BA.debugLine="Return num & \":00\" & ampm";
Debug.ShouldStop(2097152);
if (true) return RemoteObject.concat(_num,RemoteObject.createImmutable(":00"),_ampm);
 BA.debugLineNum = 183;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 16;BA.debugLine="Private menupanel As Panel";
schedule_module.mostCurrent._menupanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private sched_btn As Button";
schedule_module.mostCurrent._sched_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private scheduleSV As ScrollView";
schedule_module.mostCurrent._schedulesv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private noschedlabel As Label";
schedule_module.mostCurrent._noschedlabel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _identifycolor(RemoteObject _typeofevent) throws Exception{
try {
		Debug.PushSubsStack("IdentifyColor (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,186);
if (RapidSub.canDelegate("identifycolor")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","identifycolor", _typeofevent);}
RemoteObject _mycolor = RemoteObject.createImmutable(0);
Debug.locals.put("typeofevent", _typeofevent);
 BA.debugLineNum = 186;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="Dim mycolor As Int";
Debug.ShouldStop(67108864);
_mycolor = RemoteObject.createImmutable(0);Debug.locals.put("mycolor", _mycolor);
 BA.debugLineNum = 188;BA.debugLine="If typeofevent = \"Task\" Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Task"))) { 
 BA.debugLineNum = 189;BA.debugLine="mycolor = Colors.ARGB(190, 0, 191, 255)";
Debug.ShouldStop(268435456);
_mycolor = schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 190)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 191)),(Object)(BA.numberCast(int.class, 255)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 190;BA.debugLine="Else if typeofevent = \"Event\" Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Event"))) { 
 BA.debugLineNum = 191;BA.debugLine="mycolor = Colors.ARGB(190, 152, 255, 152)";
Debug.ShouldStop(1073741824);
_mycolor = schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 190)),(Object)(BA.numberCast(int.class, 152)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 152)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 192;BA.debugLine="Else if typeofevent = \"Birthday\" Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Birthday"))) { 
 BA.debugLineNum = 193;BA.debugLine="mycolor = Colors.ARGB(190, 255, 182, 193)";
Debug.ShouldStop(1);
_mycolor = schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 190)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 182)),(Object)(BA.numberCast(int.class, 193)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 194;BA.debugLine="Else if typeofevent = \"OOO\" Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("OOO"))) { 
 BA.debugLineNum = 195;BA.debugLine="mycolor = Colors.ARGB(190, 255, 215, 0)";
Debug.ShouldStop(4);
_mycolor = schedule_module.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 190)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 215)),(Object)(BA.numberCast(int.class, 0)));Debug.locals.put("mycolor", _mycolor);
 }}}}
;
 BA.debugLineNum = 198;BA.debugLine="Return mycolor";
Debug.ShouldStop(32);
if (true) return _mycolor;
 BA.debugLineNum = 199;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _menu_btn_click() throws Exception{
try {
		Debug.PushSubsStack("menu_btn_Click (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,248);
if (RapidSub.canDelegate("menu_btn_click")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","menu_btn_click");}
 BA.debugLineNum = 248;BA.debugLine="Private Sub menu_btn_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 249;BA.debugLine="menupanel.Visible =True";
Debug.ShouldStop(16777216);
schedule_module.mostCurrent._menupanel.runMethod(true,"setVisible",schedule_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 250;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("Month_btn_Click (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,266);
if (RapidSub.canDelegate("month_btn_click")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","month_btn_click");}
 BA.debugLineNum = 266;BA.debugLine="Private Sub Month_btn_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 267;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1024);
schedule_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 268;BA.debugLine="StartActivity(CalendarActivity)";
Debug.ShouldStop(2048);
schedule_module.mostCurrent.__c.runVoidMethod ("StartActivity",schedule_module.processBA,(Object)((schedule_module.mostCurrent._calendaractivity.getObject())));
 BA.debugLineNum = 269;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
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
public static RemoteObject  _sched_btn_click() throws Exception{
try {
		Debug.PushSubsStack("sched_btn_Click (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,253);
if (RapidSub.canDelegate("sched_btn_click")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","sched_btn_click");}
 BA.debugLineNum = 253;BA.debugLine="Private Sub sched_btn_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 254;BA.debugLine="menupanel.visible = False";
Debug.ShouldStop(536870912);
schedule_module.mostCurrent._menupanel.runMethod(true,"setVisible",schedule_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 255;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("SetDate (schedule_module) ","schedule_module",5,schedule_module.mostCurrent.activityBA,schedule_module.mostCurrent,201);
if (RapidSub.canDelegate("setdate")) { return b4a.example.schedule_module.remoteMe.runUserSub(false, "schedule_module","setdate", _tagdate);}
RemoteObject _parts = null;
RemoteObject _year = RemoteObject.createImmutable("");
RemoteObject _monthnum = RemoteObject.createImmutable(0);
RemoteObject _day = RemoteObject.createImmutable("");
RemoteObject _monthname = RemoteObject.createImmutable("");
RemoteObject _ts = RemoteObject.createImmutable(0L);
RemoteObject _weekdaynum = RemoteObject.createImmutable(0);
RemoteObject _week = RemoteObject.createImmutable("");
Debug.locals.put("Tagdate", _tagdate);
 BA.debugLineNum = 201;BA.debugLine="Sub SetDate(Tagdate As String) As String";
Debug.ShouldStop(256);
 BA.debugLineNum = 203;BA.debugLine="Dim parts() As String = Regex.Split(\"-\", Tagdate)";
Debug.ShouldStop(1024);
_parts = schedule_module.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString("-")),(Object)(_tagdate));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 204;BA.debugLine="Dim year As String = parts(0)";
Debug.ShouldStop(2048);
_year = _parts.getArrayElement(true,BA.numberCast(int.class, 0));Debug.locals.put("year", _year);Debug.locals.put("year", _year);
 BA.debugLineNum = 205;BA.debugLine="Dim monthNum As Int = parts(1)";
Debug.ShouldStop(4096);
_monthnum = BA.numberCast(int.class, _parts.getArrayElement(true,BA.numberCast(int.class, 1)));Debug.locals.put("monthNum", _monthnum);Debug.locals.put("monthNum", _monthnum);
 BA.debugLineNum = 206;BA.debugLine="Dim day As String = parts(2)";
Debug.ShouldStop(8192);
_day = _parts.getArrayElement(true,BA.numberCast(int.class, 2));Debug.locals.put("day", _day);Debug.locals.put("day", _day);
 BA.debugLineNum = 208;BA.debugLine="Dim monthName As String";
Debug.ShouldStop(32768);
_monthname = RemoteObject.createImmutable("");Debug.locals.put("monthName", _monthname);
 BA.debugLineNum = 209;BA.debugLine="Select monthNum";
Debug.ShouldStop(65536);
switch (BA.switchObjectToInt(_monthnum,BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7),BA.numberCast(int.class, 8),BA.numberCast(int.class, 9),BA.numberCast(int.class, 10),BA.numberCast(int.class, 11),BA.numberCast(int.class, 12))) {
case 0: {
 BA.debugLineNum = 210;BA.debugLine="Case 1: monthName = \"January\"";
Debug.ShouldStop(131072);
_monthname = BA.ObjectToString("January");Debug.locals.put("monthName", _monthname);
 break; }
case 1: {
 BA.debugLineNum = 211;BA.debugLine="Case 2: monthName = \"February\"";
Debug.ShouldStop(262144);
_monthname = BA.ObjectToString("February");Debug.locals.put("monthName", _monthname);
 break; }
case 2: {
 BA.debugLineNum = 212;BA.debugLine="Case 3: monthName = \"March\"";
Debug.ShouldStop(524288);
_monthname = BA.ObjectToString("March");Debug.locals.put("monthName", _monthname);
 break; }
case 3: {
 BA.debugLineNum = 213;BA.debugLine="Case 4: monthName = \"April\"";
Debug.ShouldStop(1048576);
_monthname = BA.ObjectToString("April");Debug.locals.put("monthName", _monthname);
 break; }
case 4: {
 BA.debugLineNum = 214;BA.debugLine="Case 5: monthName = \"May\"";
Debug.ShouldStop(2097152);
_monthname = BA.ObjectToString("May");Debug.locals.put("monthName", _monthname);
 break; }
case 5: {
 BA.debugLineNum = 215;BA.debugLine="Case 6: monthName = \"June\"";
Debug.ShouldStop(4194304);
_monthname = BA.ObjectToString("June");Debug.locals.put("monthName", _monthname);
 break; }
case 6: {
 BA.debugLineNum = 216;BA.debugLine="Case 7: monthName = \"July\"";
Debug.ShouldStop(8388608);
_monthname = BA.ObjectToString("July");Debug.locals.put("monthName", _monthname);
 break; }
case 7: {
 BA.debugLineNum = 217;BA.debugLine="Case 8: monthName = \"August\"";
Debug.ShouldStop(16777216);
_monthname = BA.ObjectToString("August");Debug.locals.put("monthName", _monthname);
 break; }
case 8: {
 BA.debugLineNum = 218;BA.debugLine="Case 9: monthName = \"September\"";
Debug.ShouldStop(33554432);
_monthname = BA.ObjectToString("September");Debug.locals.put("monthName", _monthname);
 break; }
case 9: {
 BA.debugLineNum = 219;BA.debugLine="Case 10: monthName = \"October\"";
Debug.ShouldStop(67108864);
_monthname = BA.ObjectToString("October");Debug.locals.put("monthName", _monthname);
 break; }
case 10: {
 BA.debugLineNum = 220;BA.debugLine="Case 11: monthName = \"November\"";
Debug.ShouldStop(134217728);
_monthname = BA.ObjectToString("November");Debug.locals.put("monthName", _monthname);
 break; }
case 11: {
 BA.debugLineNum = 221;BA.debugLine="Case 12: monthName = \"December\"";
Debug.ShouldStop(268435456);
_monthname = BA.ObjectToString("December");Debug.locals.put("monthName", _monthname);
 break; }
}
;
 BA.debugLineNum = 224;BA.debugLine="Dim ts As Long = DateTime.DateParse(Tagdate)";
Debug.ShouldStop(-2147483648);
_ts = schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"DateParse",(Object)(_tagdate));Debug.locals.put("ts", _ts);Debug.locals.put("ts", _ts);
 BA.debugLineNum = 225;BA.debugLine="Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)";
Debug.ShouldStop(1);
_weekdaynum = schedule_module.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetDayOfWeek",(Object)(_ts));Debug.locals.put("weekdayNum", _weekdaynum);Debug.locals.put("weekdayNum", _weekdaynum);
 BA.debugLineNum = 226;BA.debugLine="Dim week As String";
Debug.ShouldStop(2);
_week = RemoteObject.createImmutable("");Debug.locals.put("week", _week);
 BA.debugLineNum = 227;BA.debugLine="Select weekdayNum";
Debug.ShouldStop(4);
switch (BA.switchObjectToInt(_weekdaynum,BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7))) {
case 0: {
 BA.debugLineNum = 228;BA.debugLine="Case 1: week = \"Sunday\"";
Debug.ShouldStop(8);
_week = BA.ObjectToString("Sunday");Debug.locals.put("week", _week);
 break; }
case 1: {
 BA.debugLineNum = 229;BA.debugLine="Case 2: week = \"Monday\"";
Debug.ShouldStop(16);
_week = BA.ObjectToString("Monday");Debug.locals.put("week", _week);
 break; }
case 2: {
 BA.debugLineNum = 230;BA.debugLine="Case 3: week = \"Tuesday\"";
Debug.ShouldStop(32);
_week = BA.ObjectToString("Tuesday");Debug.locals.put("week", _week);
 break; }
case 3: {
 BA.debugLineNum = 231;BA.debugLine="Case 4: week = \"Wednesday\"";
Debug.ShouldStop(64);
_week = BA.ObjectToString("Wednesday");Debug.locals.put("week", _week);
 break; }
case 4: {
 BA.debugLineNum = 232;BA.debugLine="Case 5: week = \"Thursday\"";
Debug.ShouldStop(128);
_week = BA.ObjectToString("Thursday");Debug.locals.put("week", _week);
 break; }
case 5: {
 BA.debugLineNum = 233;BA.debugLine="Case 6: week = \"Friday\"";
Debug.ShouldStop(256);
_week = BA.ObjectToString("Friday");Debug.locals.put("week", _week);
 break; }
case 6: {
 BA.debugLineNum = 234;BA.debugLine="Case 7: week = \"Saturday\"";
Debug.ShouldStop(512);
_week = BA.ObjectToString("Saturday");Debug.locals.put("week", _week);
 break; }
}
;
 BA.debugLineNum = 237;BA.debugLine="Return week & \", \" & monthName & \" \" & day & \", \"";
Debug.ShouldStop(4096);
if (true) return RemoteObject.concat(_week,RemoteObject.createImmutable(", "),_monthname,RemoteObject.createImmutable(" "),_day,RemoteObject.createImmutable(", "),_year);
 BA.debugLineNum = 238;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}