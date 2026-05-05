package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class calendaractivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,45);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","activity_create", _firsttime);}
RemoteObject _datekey = RemoteObject.createImmutable("");
RemoteObject _dates = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _currentyear = RemoteObject.createImmutable(0);
RemoteObject _years = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _column = RemoteObject.createImmutable(0);
RemoteObject _cellw = RemoteObject.createImmutable(0);
RemoteObject _cellh = RemoteObject.createImmutable(0);
int _c = 0;
RemoteObject _celllabel = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 45;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 47;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",calendaractivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,calendaractivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"CalendarActivityLayout\")";
Debug.ShouldStop(32768);
calendaractivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("CalendarActivityLayout")),calendaractivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 50;BA.debugLine="Activity.LoadLayout(\"CalendarActivityLayoutDark\"";
Debug.ShouldStop(131072);
calendaractivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("CalendarActivityLayoutDark")),calendaractivity.mostCurrent.activityBA);
 };
 BA.debugLineNum = 53;BA.debugLine="Month_btn.Color = Colors.LightGray";
Debug.ShouldStop(1048576);
calendaractivity.mostCurrent._month_btn.runVoidMethod ("setColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"LightGray"));
 BA.debugLineNum = 55;BA.debugLine="kvs = Starter.calKvs";
Debug.ShouldStop(4194304);
calendaractivity._kvs = calendaractivity.mostCurrent._starter._calkvs /*RemoteObject*/ ;
 BA.debugLineNum = 56;BA.debugLine="CalendarMap = Starter.calendarMap";
Debug.ShouldStop(8388608);
calendaractivity._calendarmap = calendaractivity.mostCurrent._starter._calendarmap /*RemoteObject*/ ;
 BA.debugLineNum = 58;BA.debugLine="For Each datekey As String In CalendarMap.Keys";
Debug.ShouldStop(33554432);
{
final RemoteObject group9 = calendaractivity._calendarmap.runMethod(false,"Keys");
final int groupLen9 = group9.runMethod(true,"getSize").<Integer>get()
;int index9 = 0;
;
for (; index9 < groupLen9;index9++){
_datekey = BA.ObjectToString(group9.runMethod(false,"Get",index9));Debug.locals.put("datekey", _datekey);
Debug.locals.put("datekey", _datekey);
 BA.debugLineNum = 59;BA.debugLine="Dim dates As Map = CalendarMap.Get(datekey)";
Debug.ShouldStop(67108864);
_dates = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_dates = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), calendaractivity._calendarmap.runMethod(false,"Get",(Object)((_datekey))));Debug.locals.put("dates", _dates);Debug.locals.put("dates", _dates);
 BA.debugLineNum = 60;BA.debugLine="Dim timeline As List = dates.Get(\"Timeline\")";
Debug.ShouldStop(134217728);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_timeline = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _dates.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Timeline")))));Debug.locals.put("timeline", _timeline);Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 61;BA.debugLine="For i = 0 To timeline.Size -1";
Debug.ShouldStop(268435456);
{
final int step12 = 1;
final int limit12 = RemoteObject.solve(new RemoteObject[] {_timeline.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step12 > 0 && _i <= limit12) || (step12 < 0 && _i >= limit12) ;_i = ((int)(0 + _i + step12))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 62;BA.debugLine="Dim ev As Map = timeline.Get(i)";
Debug.ShouldStop(536870912);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _timeline.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("ev", _ev);Debug.locals.put("ev", _ev);
 BA.debugLineNum = 63;BA.debugLine="If ev.ContainsKey(\"ID\") = False Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",_ev.runMethod(true,"ContainsKey",(Object)((RemoteObject.createImmutable("ID")))),calendaractivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 64;BA.debugLine="ev.Put(\"ID\", DateTime.Now + Rnd(0,1000))";
Debug.ShouldStop(-2147483648);
_ev.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ID"))),(Object)((RemoteObject.solve(new RemoteObject[] {calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"),calendaractivity.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 1000)))}, "+",1, 2))));
 };
 }
}Debug.locals.put("i", _i);
;
 }
}Debug.locals.put("datekey", _datekey);
;
 BA.debugLineNum = 68;BA.debugLine="kvs.Put(\"CalendarKVS\", CalendarMap)";
Debug.ShouldStop(8);
calendaractivity._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("CalendarKVS")),(Object)((calendaractivity._calendarmap.getObject())));
 BA.debugLineNum = 71;BA.debugLine="Dim currentyear As Int = DateTime.GetYear(DateTim";
Debug.ShouldStop(64);
_currentyear = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetYear",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentyear", _currentyear);Debug.locals.put("currentyear", _currentyear);
 BA.debugLineNum = 72;BA.debugLine="Dim years As List";
Debug.ShouldStop(128);
_years = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("years", _years);
 BA.debugLineNum = 73;BA.debugLine="years.Initialize";
Debug.ShouldStop(256);
_years.runVoidMethod ("Initialize");
 BA.debugLineNum = 74;BA.debugLine="For i = currentyear -10 To currentyear+10";
Debug.ShouldStop(512);
{
final int step23 = 1;
final int limit23 = RemoteObject.solve(new RemoteObject[] {_currentyear,RemoteObject.createImmutable(10)}, "+",1, 1).<Integer>get().intValue();
_i = RemoteObject.solve(new RemoteObject[] {_currentyear,RemoteObject.createImmutable(10)}, "-",1, 1).<Integer>get().intValue() ;
for (;(step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23) ;_i = ((int)(0 + _i + step23))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 75;BA.debugLine="years.Add(i)";
Debug.ShouldStop(1024);
_years.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable((_i))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 77;BA.debugLine="YearSP.AddAll(years)";
Debug.ShouldStop(4096);
calendaractivity.mostCurrent._yearsp.runVoidMethod ("AddAll",(Object)(_years));
 BA.debugLineNum = 78;BA.debugLine="YearSP.SelectedIndex = 10";
Debug.ShouldStop(8192);
calendaractivity.mostCurrent._yearsp.runMethod(true,"setSelectedIndex",BA.numberCast(int.class, 10));
 BA.debugLineNum = 79;BA.debugLine="If Starter.darkMode = True Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",calendaractivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,calendaractivity.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 80;BA.debugLine="YearSP.DropdownBackgroundColor = Colors.Black";
Debug.ShouldStop(32768);
calendaractivity.mostCurrent._yearsp.runMethod(true,"setDropdownBackgroundColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 BA.debugLineNum = 84;BA.debugLine="MonthSp.AddAll(Months)";
Debug.ShouldStop(524288);
calendaractivity.mostCurrent._monthsp.runVoidMethod ("AddAll",(Object)(calendaractivity.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(calendaractivity.mostCurrent._months))));
 BA.debugLineNum = 85;BA.debugLine="MonthSp.SelectedIndex = DateTime.GetMonth(DateTim";
Debug.ShouldStop(1048576);
calendaractivity.mostCurrent._monthsp.runMethod(true,"setSelectedIndex",RemoteObject.solve(new RemoteObject[] {calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetMonth",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))),RemoteObject.createImmutable(1)}, "-",1, 1));
 BA.debugLineNum = 86;BA.debugLine="If Starter.darkMode = True Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",calendaractivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,calendaractivity.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 87;BA.debugLine="MonthSp.DropdownBackgroundColor = Colors.Black";
Debug.ShouldStop(4194304);
calendaractivity.mostCurrent._monthsp.runMethod(true,"setDropdownBackgroundColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 BA.debugLineNum = 91;BA.debugLine="DrawCalendar(DateTime.GetMonth(DateTime.Now), Dat";
Debug.ShouldStop(67108864);
_drawcalendar(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetMonth",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))),calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetYear",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))));
 BA.debugLineNum = 94;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(536870912);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 95;BA.debugLine="cd.Initialize2(Colors.White, 0, 2dip, Colors.Blac";
Debug.ShouldStop(1073741824);
_cd.runVoidMethod ("Initialize2",(Object)(calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White")),(Object)(BA.numberCast(int.class, 0)),(Object)(calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black")));
 BA.debugLineNum = 97;BA.debugLine="Dim column As Int = 7";
Debug.ShouldStop(1);
_column = BA.numberCast(int.class, 7);Debug.locals.put("column", _column);Debug.locals.put("column", _column);
 BA.debugLineNum = 98;BA.debugLine="Dim cellW As Int = calendarpanel.Width/column";
Debug.ShouldStop(2);
_cellw = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {calendaractivity.mostCurrent._calendarpanel.runMethod(true,"getWidth"),_column}, "/",0, 0));Debug.locals.put("cellW", _cellw);Debug.locals.put("cellW", _cellw);
 BA.debugLineNum = 99;BA.debugLine="Dim cellH As Int = 60dip";
Debug.ShouldStop(4);
_cellh = calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));Debug.locals.put("cellH", _cellh);Debug.locals.put("cellH", _cellh);
 BA.debugLineNum = 100;BA.debugLine="For c = 0 To column -1";
Debug.ShouldStop(8);
{
final int step42 = 1;
final int limit42 = RemoteObject.solve(new RemoteObject[] {_column,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_c = 0 ;
for (;(step42 > 0 && _c <= limit42) || (step42 < 0 && _c >= limit42) ;_c = ((int)(0 + _c + step42))  ) {
Debug.locals.put("c", _c);
 BA.debugLineNum = 101;BA.debugLine="Dim celllabel As Label";
Debug.ShouldStop(16);
_celllabel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("celllabel", _celllabel);
 BA.debugLineNum = 102;BA.debugLine="celllabel.Initialize(\"cell_label\")";
Debug.ShouldStop(32);
_celllabel.runVoidMethod ("Initialize",calendaractivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("cell_label")));
 BA.debugLineNum = 103;BA.debugLine="Weekday(c)";
Debug.ShouldStop(64);
_weekday(BA.numberCast(int.class, _c));
 BA.debugLineNum = 104;BA.debugLine="celllabel.Text = day_of_week";
Debug.ShouldStop(128);
_celllabel.runMethod(true,"setText",BA.ObjectToCharSequence(calendaractivity.mostCurrent._day_of_week));
 BA.debugLineNum = 105;BA.debugLine="celllabel.TextSize = 14";
Debug.ShouldStop(256);
_celllabel.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 106;BA.debugLine="celllabel.Color = Colors.black";
Debug.ShouldStop(512);
_celllabel.runVoidMethod ("setColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 107;BA.debugLine="weekpanel.AddView(celllabel, c*cellW, 0, cellW,";
Debug.ShouldStop(1024);
calendaractivity.mostCurrent._weekpanel.runVoidMethod ("AddView",(Object)((_celllabel.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_c),_cellw}, "*",0, 1)),(Object)(BA.numberCast(int.class, 0)),(Object)(_cellw),(Object)(_cellh));
 BA.debugLineNum = 110;BA.debugLine="celllabel.Background = cd";
Debug.ShouldStop(8192);
_celllabel.runMethod(false,"setBackground",(_cd.getObject()));
 }
}Debug.locals.put("c", _c);
;
 BA.debugLineNum = 116;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
		Debug.PushSubsStack("Activity_Pause (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,320);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 320;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 322;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("Activity_Resume (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,316);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","activity_resume");}
 BA.debugLineNum = 316;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(134217728);
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
public static RemoteObject  _cell_click_click() throws Exception{
try {
		Debug.PushSubsStack("cell_click_click (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,265);
if (RapidSub.canDelegate("cell_click_click")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","cell_click_click");}
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _datestr = RemoteObject.createImmutable("");
 BA.debugLineNum = 265;BA.debugLine="Sub cell_click_click";
Debug.ShouldStop(256);
 BA.debugLineNum = 267;BA.debugLine="Dim p As Panel = Sender";
Debug.ShouldStop(1024);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_p = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), calendaractivity.mostCurrent.__c.runMethod(false,"Sender",calendaractivity.mostCurrent.activityBA));Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 268;BA.debugLine="Dim datestr As String = p.Tag";
Debug.ShouldStop(2048);
_datestr = BA.ObjectToString(_p.runMethod(false,"getTag"));Debug.locals.put("datestr", _datestr);Debug.locals.put("datestr", _datestr);
 BA.debugLineNum = 269;BA.debugLine="Activity.finish";
Debug.ShouldStop(4096);
calendaractivity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 270;BA.debugLine="StartActivity(day_module)";
Debug.ShouldStop(8192);
calendaractivity.mostCurrent.__c.runVoidMethod ("StartActivity",calendaractivity.processBA,(Object)((calendaractivity.mostCurrent._day_module.getObject())));
 BA.debugLineNum = 271;BA.debugLine="day_module.currentDate = datestr";
Debug.ShouldStop(16384);
calendaractivity.mostCurrent._day_module._currentdate /*RemoteObject*/  = _datestr;
 BA.debugLineNum = 272;BA.debugLine="Log(datestr)";
Debug.ShouldStop(32768);
calendaractivity.mostCurrent.__c.runVoidMethod ("LogImpl","26029319",_datestr,0);
 BA.debugLineNum = 274;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cleandebugger() throws Exception{
try {
		Debug.PushSubsStack("CleanDebugger (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,118);
if (RapidSub.canDelegate("cleandebugger")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","cleandebugger");}
 BA.debugLineNum = 118;BA.debugLine="Sub CleanDebugger";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 119;BA.debugLine="CalendarMap.Clear";
Debug.ShouldStop(4194304);
calendaractivity._calendarmap.runVoidMethod ("Clear");
 BA.debugLineNum = 120;BA.debugLine="kvs.Put(\"CalendarKVS\", CalendarMap)";
Debug.ShouldStop(8388608);
calendaractivity._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("CalendarKVS")),(Object)((calendaractivity._calendarmap.getObject())));
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
public static RemoteObject  _day_btn_click() throws Exception{
try {
		Debug.PushSubsStack("Day_btn_Click (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,346);
if (RapidSub.canDelegate("day_btn_click")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","day_btn_click");}
RemoteObject _currentyear = RemoteObject.createImmutable(0);
RemoteObject _currentmonth = RemoteObject.createImmutable(0);
RemoteObject _currentday = RemoteObject.createImmutable(0);
RemoteObject _datestr = RemoteObject.createImmutable("");
 BA.debugLineNum = 346;BA.debugLine="Private Sub Day_btn_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 347;BA.debugLine="If day_module.currentDate = \"\" Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",calendaractivity.mostCurrent._day_module._currentdate /*RemoteObject*/ ,BA.ObjectToString(""))) { 
 BA.debugLineNum = 348;BA.debugLine="Dim currentyear As Int = DateTime.GetYear(DateTi";
Debug.ShouldStop(134217728);
_currentyear = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetYear",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentyear", _currentyear);Debug.locals.put("currentyear", _currentyear);
 BA.debugLineNum = 349;BA.debugLine="Dim currentmonth As Int = DateTime.GetMonth(Date";
Debug.ShouldStop(268435456);
_currentmonth = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetMonth",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentmonth", _currentmonth);Debug.locals.put("currentmonth", _currentmonth);
 BA.debugLineNum = 350;BA.debugLine="Dim currentday As Int = DateTime.GetDayOfMonth(D";
Debug.ShouldStop(536870912);
_currentday = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetDayOfMonth",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("currentday", _currentday);Debug.locals.put("currentday", _currentday);
 BA.debugLineNum = 351;BA.debugLine="Dim datestr As String= currentyear & \"-\" & Numbe";
Debug.ShouldStop(1073741824);
_datestr = RemoteObject.concat(_currentyear,RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _currentmonth)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _currentday)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("datestr", _datestr);Debug.locals.put("datestr", _datestr);
 BA.debugLineNum = 352;BA.debugLine="day_module.currentDate = datestr";
Debug.ShouldStop(-2147483648);
calendaractivity.mostCurrent._day_module._currentdate /*RemoteObject*/  = _datestr;
 BA.debugLineNum = 353;BA.debugLine="Log(day_module.currentDate)";
Debug.ShouldStop(1);
calendaractivity.mostCurrent.__c.runVoidMethod ("LogImpl","26619143",calendaractivity.mostCurrent._day_module._currentdate /*RemoteObject*/ ,0);
 };
 BA.debugLineNum = 356;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8);
calendaractivity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 357;BA.debugLine="StartActivity(day_module)";
Debug.ShouldStop(16);
calendaractivity.mostCurrent.__c.runVoidMethod ("StartActivity",calendaractivity.processBA,(Object)((calendaractivity.mostCurrent._day_module.getObject())));
 BA.debugLineNum = 358;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _daysinamonth(RemoteObject _month,RemoteObject _year) throws Exception{
try {
		Debug.PushSubsStack("Daysinamonth (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,277);
if (RapidSub.canDelegate("daysinamonth")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","daysinamonth", _month, _year);}
RemoteObject _nextmonth = RemoteObject.createImmutable(0);
RemoteObject _nextyear = RemoteObject.createImmutable(0);
RemoteObject _firstnextmonth = RemoteObject.createImmutable(0L);
RemoteObject _lastdaydate = RemoteObject.createImmutable(0L);
Debug.locals.put("Month", _month);
Debug.locals.put("year", _year);
 BA.debugLineNum = 277;BA.debugLine="Sub Daysinamonth(Month As Int, year As Int) As Int";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 278;BA.debugLine="Dim nextMonth As Int";
Debug.ShouldStop(2097152);
_nextmonth = RemoteObject.createImmutable(0);Debug.locals.put("nextMonth", _nextmonth);
 BA.debugLineNum = 279;BA.debugLine="Dim nextyear As Int";
Debug.ShouldStop(4194304);
_nextyear = RemoteObject.createImmutable(0);Debug.locals.put("nextyear", _nextyear);
 BA.debugLineNum = 281;BA.debugLine="If Month = 12 Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",_month,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 282;BA.debugLine="nextMonth = 1";
Debug.ShouldStop(33554432);
_nextmonth = BA.numberCast(int.class, 1);Debug.locals.put("nextMonth", _nextmonth);
 BA.debugLineNum = 283;BA.debugLine="nextyear = year+1";
Debug.ShouldStop(67108864);
_nextyear = RemoteObject.solve(new RemoteObject[] {_year,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("nextyear", _nextyear);
 }else {
 BA.debugLineNum = 285;BA.debugLine="nextMonth = Month +1";
Debug.ShouldStop(268435456);
_nextmonth = RemoteObject.solve(new RemoteObject[] {_month,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("nextMonth", _nextmonth);
 BA.debugLineNum = 286;BA.debugLine="nextyear = year";
Debug.ShouldStop(536870912);
_nextyear = _year;Debug.locals.put("nextyear", _nextyear);
 };
 BA.debugLineNum = 289;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
Debug.ShouldStop(1);
calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setDateFormat",BA.ObjectToString("yyyy-MM-dd"));
 BA.debugLineNum = 290;BA.debugLine="Dim firstNextMonth As Long = DateTime.DateParse(n";
Debug.ShouldStop(2);
_firstnextmonth = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"DateParse",(Object)(RemoteObject.concat(_nextyear,RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _nextmonth)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("-01"))));Debug.locals.put("firstNextMonth", _firstnextmonth);Debug.locals.put("firstNextMonth", _firstnextmonth);
 BA.debugLineNum = 291;BA.debugLine="Dim lastDayDate As Long = firstNextMonth - DateTi";
Debug.ShouldStop(4);
_lastdaydate = RemoteObject.solve(new RemoteObject[] {_firstnextmonth,calendaractivity.mostCurrent.__c.getField(false,"DateTime").getField(true,"TicksPerDay")}, "-",1, 2);Debug.locals.put("lastDayDate", _lastdaydate);Debug.locals.put("lastDayDate", _lastdaydate);
 BA.debugLineNum = 294;BA.debugLine="Return DateTime.GetDayOfMonth(lastDayDate)";
Debug.ShouldStop(32);
if (true) return calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetDayOfMonth",(Object)(_lastdaydate));
 BA.debugLineNum = 296;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _drawcalendar(RemoteObject _month,RemoteObject _year) throws Exception{
try {
		Debug.PushSubsStack("DrawCalendar (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,123);
if (RapidSub.canDelegate("drawcalendar")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","drawcalendar", _month, _year);}
RemoteObject _firstday = RemoteObject.createImmutable(0L);
RemoteObject _startday = RemoteObject.createImmutable(0);
RemoteObject _daysinmonth = RemoteObject.createImmutable(0);
RemoteObject _prevmonth = RemoteObject.createImmutable(0);
RemoteObject _prevyear = RemoteObject.createImmutable(0);
RemoteObject _daysinprevmonth = RemoteObject.createImmutable(0);
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _rows = RemoteObject.createImmutable(0);
RemoteObject _column = RemoteObject.createImmutable(0);
RemoteObject _day = RemoteObject.createImmutable(0);
RemoteObject _started = RemoteObject.createImmutable(false);
RemoteObject _index = RemoteObject.createImmutable(0);
RemoteObject _cellw = RemoteObject.createImmutable(0);
RemoteObject _cellh = RemoteObject.createImmutable(0);
int _r = 0;
int _c = 0;
RemoteObject _cell = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _displayday = RemoteObject.createImmutable(0);
RemoteObject _datestr = RemoteObject.createImmutable("");
RemoteObject _nextmonth = RemoteObject.createImmutable(0);
RemoteObject _nextyear = RemoteObject.createImmutable(0);
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _maxshow = RemoteObject.createImmutable(0);
RemoteObject _yoffset = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _ev = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _dot = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _morelbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("month", _month);
Debug.locals.put("year", _year);
 BA.debugLineNum = 123;BA.debugLine="Sub DrawCalendar (month As Int, year As Int)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 125;BA.debugLine="calendarpanel.RemoveAllViews";
Debug.ShouldStop(268435456);
calendaractivity.mostCurrent._calendarpanel.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 128;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
Debug.ShouldStop(-2147483648);
calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setDateFormat",BA.ObjectToString("yyyy-MM-dd"));
 BA.debugLineNum = 129;BA.debugLine="Dim firstDay As Long = DateTime.DateParse(year &";
Debug.ShouldStop(1);
_firstday = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"DateParse",(Object)(RemoteObject.concat(_year,RemoteObject.createImmutable("-"),_month,RemoteObject.createImmutable("-01"))));Debug.locals.put("firstDay", _firstday);Debug.locals.put("firstDay", _firstday);
 BA.debugLineNum = 130;BA.debugLine="Dim startDay As Int = DateTime.GetDayOfweek(first";
Debug.ShouldStop(2);
_startday = calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetDayOfWeek",(Object)(_firstday));Debug.locals.put("startDay", _startday);Debug.locals.put("startDay", _startday);
 BA.debugLineNum = 131;BA.debugLine="Dim daysInmonth As Int = Daysinamonth(month, year";
Debug.ShouldStop(4);
_daysinmonth = _daysinamonth(_month,_year);Debug.locals.put("daysInmonth", _daysinmonth);Debug.locals.put("daysInmonth", _daysinmonth);
 BA.debugLineNum = 135;BA.debugLine="Dim prevmonth As Int";
Debug.ShouldStop(64);
_prevmonth = RemoteObject.createImmutable(0);Debug.locals.put("prevmonth", _prevmonth);
 BA.debugLineNum = 136;BA.debugLine="Dim prevyear As Int";
Debug.ShouldStop(128);
_prevyear = RemoteObject.createImmutable(0);Debug.locals.put("prevyear", _prevyear);
 BA.debugLineNum = 138;BA.debugLine="If month = 1 Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",_month,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 139;BA.debugLine="prevmonth = 12";
Debug.ShouldStop(1024);
_prevmonth = BA.numberCast(int.class, 12);Debug.locals.put("prevmonth", _prevmonth);
 BA.debugLineNum = 140;BA.debugLine="prevyear = year - 1";
Debug.ShouldStop(2048);
_prevyear = RemoteObject.solve(new RemoteObject[] {_year,RemoteObject.createImmutable(1)}, "-",1, 1);Debug.locals.put("prevyear", _prevyear);
 }else {
 BA.debugLineNum = 142;BA.debugLine="prevmonth = month-1";
Debug.ShouldStop(8192);
_prevmonth = RemoteObject.solve(new RemoteObject[] {_month,RemoteObject.createImmutable(1)}, "-",1, 1);Debug.locals.put("prevmonth", _prevmonth);
 BA.debugLineNum = 143;BA.debugLine="prevyear = year";
Debug.ShouldStop(16384);
_prevyear = _year;Debug.locals.put("prevyear", _prevyear);
 };
 BA.debugLineNum = 145;BA.debugLine="Dim daysInprevMonth As Int = Daysinamonth(prevmon";
Debug.ShouldStop(65536);
_daysinprevmonth = _daysinamonth(_prevmonth,_prevyear);Debug.locals.put("daysInprevMonth", _daysinprevmonth);Debug.locals.put("daysInprevMonth", _daysinprevmonth);
 BA.debugLineNum = 149;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(1048576);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 150;BA.debugLine="cd.Initialize2(Colors.White, 0, 2dip, Colors.Blac";
Debug.ShouldStop(2097152);
_cd.runVoidMethod ("Initialize2",(Object)(calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White")),(Object)(BA.numberCast(int.class, 0)),(Object)(calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black")));
 BA.debugLineNum = 152;BA.debugLine="Dim rows As Int = 6  'number of rows (days)";
Debug.ShouldStop(8388608);
_rows = BA.numberCast(int.class, 6);Debug.locals.put("rows", _rows);Debug.locals.put("rows", _rows);
 BA.debugLineNum = 153;BA.debugLine="Dim column As Int = 7 'number of columns (the wee";
Debug.ShouldStop(16777216);
_column = BA.numberCast(int.class, 7);Debug.locals.put("column", _column);Debug.locals.put("column", _column);
 BA.debugLineNum = 155;BA.debugLine="Dim day As Int = 1";
Debug.ShouldStop(67108864);
_day = BA.numberCast(int.class, 1);Debug.locals.put("day", _day);Debug.locals.put("day", _day);
 BA.debugLineNum = 156;BA.debugLine="Dim started As Boolean = False";
Debug.ShouldStop(134217728);
_started = calendaractivity.mostCurrent.__c.getField(true,"False");Debug.locals.put("started", _started);Debug.locals.put("started", _started);
 BA.debugLineNum = 158;BA.debugLine="Dim index As Int = 0";
Debug.ShouldStop(536870912);
_index = BA.numberCast(int.class, 0);Debug.locals.put("index", _index);Debug.locals.put("index", _index);
 BA.debugLineNum = 160;BA.debugLine="Dim cellW As Int = calendarpanel.width / column";
Debug.ShouldStop(-2147483648);
_cellw = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {calendaractivity.mostCurrent._calendarpanel.runMethod(true,"getWidth"),_column}, "/",0, 0));Debug.locals.put("cellW", _cellw);Debug.locals.put("cellW", _cellw);
 BA.debugLineNum = 161;BA.debugLine="Dim cellH As Int = 60dip";
Debug.ShouldStop(1);
_cellh = calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));Debug.locals.put("cellH", _cellh);Debug.locals.put("cellH", _cellh);
 BA.debugLineNum = 162;BA.debugLine="For r = 0 To rows -1";
Debug.ShouldStop(2);
{
final int step25 = 1;
final int limit25 = RemoteObject.solve(new RemoteObject[] {_rows,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_r = 0 ;
for (;(step25 > 0 && _r <= limit25) || (step25 < 0 && _r >= limit25) ;_r = ((int)(0 + _r + step25))  ) {
Debug.locals.put("r", _r);
 BA.debugLineNum = 163;BA.debugLine="For c = 0 To column -1";
Debug.ShouldStop(4);
{
final int step26 = 1;
final int limit26 = RemoteObject.solve(new RemoteObject[] {_column,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_c = 0 ;
for (;(step26 > 0 && _c <= limit26) || (step26 < 0 && _c >= limit26) ;_c = ((int)(0 + _c + step26))  ) {
Debug.locals.put("c", _c);
 BA.debugLineNum = 165;BA.debugLine="Dim cell As Panel";
Debug.ShouldStop(16);
_cell = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("cell", _cell);
 BA.debugLineNum = 166;BA.debugLine="cell.Initialize(\"cell_click\")";
Debug.ShouldStop(32);
_cell.runVoidMethod ("Initialize",calendaractivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("cell_click")));
 BA.debugLineNum = 167;BA.debugLine="cell.Enabled  =True";
Debug.ShouldStop(64);
_cell.runMethod(true,"setEnabled",calendaractivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 169;BA.debugLine="calendarpanel.AddView(cell, c * cellW, r * cell";
Debug.ShouldStop(256);
calendaractivity.mostCurrent._calendarpanel.runVoidMethod ("AddView",(Object)((_cell.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_c),_cellw}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_r),_cellh}, "*",0, 1)),(Object)(_cellw),(Object)(_cellh));
 BA.debugLineNum = 170;BA.debugLine="cell.Background = cd";
Debug.ShouldStop(512);
_cell.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 172;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(2048);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 173;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(4096);
_lbl.runVoidMethod ("Initialize",calendaractivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 174;BA.debugLine="lbl.Gravity = Gravity.CENTER_Horizontal";
Debug.ShouldStop(8192);
_lbl.runMethod(true,"setGravity",calendaractivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 175;BA.debugLine="lbl.Enabled = False";
Debug.ShouldStop(16384);
_lbl.runMethod(true,"setEnabled",calendaractivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 177;BA.debugLine="index = index +1";
Debug.ShouldStop(65536);
_index = RemoteObject.solve(new RemoteObject[] {_index,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("index", _index);
 BA.debugLineNum = 179;BA.debugLine="Dim displayday As Int";
Debug.ShouldStop(262144);
_displayday = RemoteObject.createImmutable(0);Debug.locals.put("displayday", _displayday);
 BA.debugLineNum = 180;BA.debugLine="Dim datestr As String";
Debug.ShouldStop(524288);
_datestr = RemoteObject.createImmutable("");Debug.locals.put("datestr", _datestr);
 BA.debugLineNum = 183;BA.debugLine="If index < startDay Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("<",_index,BA.numberCast(double.class, _startday))) { 
 BA.debugLineNum = 184;BA.debugLine="displayday = daysInprevMonth - (startDay - ind";
Debug.ShouldStop(8388608);
_displayday = RemoteObject.solve(new RemoteObject[] {_daysinprevmonth,(RemoteObject.solve(new RemoteObject[] {_startday,_index}, "-",1, 1)),RemoteObject.createImmutable(1)}, "-+",2, 1);Debug.locals.put("displayday", _displayday);
 BA.debugLineNum = 185;BA.debugLine="lbl.TextColor = Colors.gray";
Debug.ShouldStop(16777216);
_lbl.runMethod(true,"setTextColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Gray"));
 BA.debugLineNum = 186;BA.debugLine="datestr = prevyear & \"-\" & NumberFormat(prevmo";
Debug.ShouldStop(33554432);
_datestr = RemoteObject.concat(_prevyear,RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _prevmonth)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _displayday)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("datestr", _datestr);
 }else 
{ BA.debugLineNum = 187;BA.debugLine="Else if index >= startDay And index < startDay";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("g",_index,BA.numberCast(double.class, _startday)) && RemoteObject.solveBoolean("<",_index,BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {_startday,_daysinmonth}, "+",1, 1)))) { 
 BA.debugLineNum = 188;BA.debugLine="displayday = index - startDay + 1";
Debug.ShouldStop(134217728);
_displayday = RemoteObject.solve(new RemoteObject[] {_index,_startday,RemoteObject.createImmutable(1)}, "-+",2, 1);Debug.locals.put("displayday", _displayday);
 BA.debugLineNum = 189;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(268435456);
_lbl.runMethod(true,"setTextColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 190;BA.debugLine="datestr = year&\"-\"&NumberFormat(month,2,0)&\"-\"";
Debug.ShouldStop(536870912);
_datestr = RemoteObject.concat(_year,RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _month)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _displayday)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("datestr", _datestr);
 }else {
 BA.debugLineNum = 192;BA.debugLine="displayday = index - (startDay + daysInmonth)";
Debug.ShouldStop(-2147483648);
_displayday = RemoteObject.solve(new RemoteObject[] {_index,(RemoteObject.solve(new RemoteObject[] {_startday,_daysinmonth}, "+",1, 1)),RemoteObject.createImmutable(1)}, "-+",2, 1);Debug.locals.put("displayday", _displayday);
 BA.debugLineNum = 193;BA.debugLine="lbl.TextColor = Colors.Gray";
Debug.ShouldStop(1);
_lbl.runMethod(true,"setTextColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Gray"));
 BA.debugLineNum = 194;BA.debugLine="Dim nextmonth As Int";
Debug.ShouldStop(2);
_nextmonth = RemoteObject.createImmutable(0);Debug.locals.put("nextmonth", _nextmonth);
 BA.debugLineNum = 195;BA.debugLine="Dim nextyear As Int";
Debug.ShouldStop(4);
_nextyear = RemoteObject.createImmutable(0);Debug.locals.put("nextyear", _nextyear);
 BA.debugLineNum = 197;BA.debugLine="If month = 12 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",_month,BA.numberCast(double.class, 12))) { 
 BA.debugLineNum = 198;BA.debugLine="nextmonth = 1";
Debug.ShouldStop(32);
_nextmonth = BA.numberCast(int.class, 1);Debug.locals.put("nextmonth", _nextmonth);
 BA.debugLineNum = 199;BA.debugLine="nextyear = year+1";
Debug.ShouldStop(64);
_nextyear = RemoteObject.solve(new RemoteObject[] {_year,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("nextyear", _nextyear);
 }else {
 BA.debugLineNum = 201;BA.debugLine="nextmonth = month+1";
Debug.ShouldStop(256);
_nextmonth = RemoteObject.solve(new RemoteObject[] {_month,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("nextmonth", _nextmonth);
 BA.debugLineNum = 202;BA.debugLine="nextyear = year";
Debug.ShouldStop(512);
_nextyear = _year;Debug.locals.put("nextyear", _nextyear);
 BA.debugLineNum = 203;BA.debugLine="datestr = nextyear&\"-\"&NumberFormat(nextmonth";
Debug.ShouldStop(1024);
_datestr = RemoteObject.concat(_nextyear,RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _nextmonth)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("-"),calendaractivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _displayday)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("datestr", _datestr);
 };
 }}
;
 BA.debugLineNum = 208;BA.debugLine="lbl.Text = displayday";
Debug.ShouldStop(32768);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_displayday));
 BA.debugLineNum = 209;BA.debugLine="cell.Tag = datestr";
Debug.ShouldStop(65536);
_cell.runMethod(false,"setTag",(_datestr));
 BA.debugLineNum = 210;BA.debugLine="lbl.Enabled = False";
Debug.ShouldStop(131072);
_lbl.runMethod(true,"setEnabled",calendaractivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 211;BA.debugLine="cell.AddView(lbl, 0, 0, cellW, cellH)";
Debug.ShouldStop(262144);
_cell.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_cellw),(Object)(_cellh));
 BA.debugLineNum = 213;BA.debugLine="If CalendarMap.ContainsKey(datestr) Then";
Debug.ShouldStop(1048576);
if (calendaractivity._calendarmap.runMethod(true,"ContainsKey",(Object)((_datestr))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 214;BA.debugLine="Dim eventmap As Map = CalendarMap.Get(datestr)";
Debug.ShouldStop(2097152);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), calendaractivity._calendarmap.runMethod(false,"Get",(Object)((_datestr))));Debug.locals.put("eventmap", _eventmap);Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 215;BA.debugLine="Dim allevents As List = eventmap.Get(\"AllEvent";
Debug.ShouldStop(4194304);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allevents = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("AllEvents")))));Debug.locals.put("allevents", _allevents);Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 217;BA.debugLine="Dim maxShow As Int = Min(allevents.Size, 2)";
Debug.ShouldStop(16777216);
_maxshow = BA.numberCast(int.class, calendaractivity.mostCurrent.__c.runMethod(true,"Min",(Object)(BA.numberCast(double.class, _allevents.runMethod(true,"getSize"))),(Object)(BA.numberCast(double.class, 2))));Debug.locals.put("maxShow", _maxshow);Debug.locals.put("maxShow", _maxshow);
 BA.debugLineNum = 218;BA.debugLine="Dim yoffset As Int = 20dip";
Debug.ShouldStop(33554432);
_yoffset = calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)));Debug.locals.put("yoffset", _yoffset);Debug.locals.put("yoffset", _yoffset);
 BA.debugLineNum = 220;BA.debugLine="For i = 0 To maxShow -1";
Debug.ShouldStop(134217728);
{
final int step70 = 1;
final int limit70 = RemoteObject.solve(new RemoteObject[] {_maxshow,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step70 > 0 && _i <= limit70) || (step70 < 0 && _i >= limit70) ;_i = ((int)(0 + _i + step70))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 221;BA.debugLine="Dim ev As Map = allevents.Get(i)";
Debug.ShouldStop(268435456);
_ev = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_ev = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _allevents.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("ev", _ev);Debug.locals.put("ev", _ev);
 BA.debugLineNum = 223;BA.debugLine="Dim dot As Label";
Debug.ShouldStop(1073741824);
_dot = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("dot", _dot);
 BA.debugLineNum = 224;BA.debugLine="dot.Initialize(\"\")";
Debug.ShouldStop(-2147483648);
_dot.runVoidMethod ("Initialize",calendaractivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 225;BA.debugLine="dot.Text = ev.Get(\"Title\")";
Debug.ShouldStop(1);
_dot.runMethod(true,"setText",BA.ObjectToCharSequence(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Title"))))));
 BA.debugLineNum = 226;BA.debugLine="dot.TextSize = 8";
Debug.ShouldStop(2);
_dot.runMethod(true,"setTextSize",BA.numberCast(float.class, 8));
 BA.debugLineNum = 227;BA.debugLine="dot.TextColor = Colors.white";
Debug.ShouldStop(4);
_dot.runMethod(true,"setTextColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 228;BA.debugLine="dot.Color = IdentifyColor(ev.Get(\"Tags\"))";
Debug.ShouldStop(8);
_dot.runVoidMethod ("setColor",_identifycolor(BA.ObjectToString(_ev.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Tags")))))));
 BA.debugLineNum = 229;BA.debugLine="dot.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(16);
_dot.runMethod(true,"setGravity",calendaractivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 230;BA.debugLine="dot.SingleLine = True";
Debug.ShouldStop(32);
_dot.runVoidMethod ("setSingleLine",calendaractivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 231;BA.debugLine="dot.Ellipsize = \"END\"";
Debug.ShouldStop(64);
_dot.runMethod(true,"setEllipsize",BA.ObjectToString("END"));
 BA.debugLineNum = 232;BA.debugLine="cell.AddView(dot, 2dip, yoffset +i*12dip, cel";
Debug.ShouldStop(128);
_cell.runVoidMethod ("AddView",(Object)((_dot.getObject())),(Object)(calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(RemoteObject.solve(new RemoteObject[] {_yoffset,RemoteObject.createImmutable(_i),calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 12)))}, "+*",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_cellw,calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))}, "-",1, 1)),(Object)(calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 235;BA.debugLine="If allevents.Size > 2 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean(">",_allevents.runMethod(true,"getSize"),BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 236;BA.debugLine="Dim morelbl As Label";
Debug.ShouldStop(2048);
_morelbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("morelbl", _morelbl);
 BA.debugLineNum = 237;BA.debugLine="morelbl.Initialize(\"\")";
Debug.ShouldStop(4096);
_morelbl.runVoidMethod ("Initialize",calendaractivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 238;BA.debugLine="morelbl.Text = \"+\" & (allevents.Size -2)";
Debug.ShouldStop(8192);
_morelbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("+"),(RemoteObject.solve(new RemoteObject[] {_allevents.runMethod(true,"getSize"),RemoteObject.createImmutable(2)}, "-",1, 1)))));
 BA.debugLineNum = 239;BA.debugLine="morelbl.TextSize = 8";
Debug.ShouldStop(16384);
_morelbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 8));
 BA.debugLineNum = 240;BA.debugLine="morelbl.TextColor = Colors.black";
Debug.ShouldStop(32768);
_morelbl.runMethod(true,"setTextColor",calendaractivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 241;BA.debugLine="cell.AddView(morelbl, 2dip , yoffset + 2 * 12";
Debug.ShouldStop(65536);
_cell.runVoidMethod ("AddView",(Object)((_morelbl.getObject())),(Object)(calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(RemoteObject.solve(new RemoteObject[] {_yoffset,RemoteObject.createImmutable(2),calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 12)))}, "+*",1, 1)),(Object)(_cellw),(Object)(calendaractivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))));
 };
 };
 }
}Debug.locals.put("c", _c);
;
 }
}Debug.locals.put("r", _r);
;
 BA.debugLineNum = 248;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
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
 //BA.debugLineNum = 20;BA.debugLine="Dim Months(12) As String";
calendaractivity.mostCurrent._months = RemoteObject.createNewArray ("String", new int[] {12}, new Object[]{});
 //BA.debugLineNum = 21;BA.debugLine="Months(0) = \"January\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("January"),BA.numberCast(int.class, 0));
 //BA.debugLineNum = 22;BA.debugLine="Months(1) = \"February\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("February"),BA.numberCast(int.class, 1));
 //BA.debugLineNum = 23;BA.debugLine="Months(2) = \"March\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("March"),BA.numberCast(int.class, 2));
 //BA.debugLineNum = 24;BA.debugLine="Months(3) = \"April\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("April"),BA.numberCast(int.class, 3));
 //BA.debugLineNum = 25;BA.debugLine="Months(4) = \"May\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("May"),BA.numberCast(int.class, 4));
 //BA.debugLineNum = 26;BA.debugLine="Months(5) = \"June\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("June"),BA.numberCast(int.class, 5));
 //BA.debugLineNum = 27;BA.debugLine="Months(6) = \"July\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("July"),BA.numberCast(int.class, 6));
 //BA.debugLineNum = 28;BA.debugLine="Months(7) = \"August\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("August"),BA.numberCast(int.class, 7));
 //BA.debugLineNum = 29;BA.debugLine="Months(8) = \"September\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("September"),BA.numberCast(int.class, 8));
 //BA.debugLineNum = 30;BA.debugLine="Months(9) = \"October\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("October"),BA.numberCast(int.class, 9));
 //BA.debugLineNum = 31;BA.debugLine="Months(10) = \"November\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("November"),BA.numberCast(int.class, 10));
 //BA.debugLineNum = 32;BA.debugLine="Months(11) = \"December\"";
calendaractivity.mostCurrent._months.setArrayElement (BA.ObjectToString("December"),BA.numberCast(int.class, 11));
 //BA.debugLineNum = 36;BA.debugLine="Private calendarpanel As Panel";
calendaractivity.mostCurrent._calendarpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private weekpanel As Panel";
calendaractivity.mostCurrent._weekpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Dim day_of_week As String";
calendaractivity.mostCurrent._day_of_week = RemoteObject.createImmutable("");
 //BA.debugLineNum = 39;BA.debugLine="Private MonthSp As Spinner";
calendaractivity.mostCurrent._monthsp = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private YearSP As Spinner";
calendaractivity.mostCurrent._yearsp = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private menupanel As Panel";
calendaractivity.mostCurrent._menupanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private Month_btn As Button";
calendaractivity.mostCurrent._month_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _identifycolor(RemoteObject _typeofevent) throws Exception{
try {
		Debug.PushSubsStack("IdentifyColor (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,250);
if (RapidSub.canDelegate("identifycolor")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","identifycolor", _typeofevent);}
RemoteObject _mycolor = RemoteObject.createImmutable(0);
Debug.locals.put("typeofevent", _typeofevent);
 BA.debugLineNum = 250;BA.debugLine="Sub IdentifyColor (typeofevent As String) As Int";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 251;BA.debugLine="Dim mycolor As Int";
Debug.ShouldStop(67108864);
_mycolor = RemoteObject.createImmutable(0);Debug.locals.put("mycolor", _mycolor);
 BA.debugLineNum = 252;BA.debugLine="If typeofevent = \"Task\" Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Task"))) { 
 BA.debugLineNum = 253;BA.debugLine="mycolor = Colors.ARGB(255, 0, 191, 255)";
Debug.ShouldStop(268435456);
_mycolor = calendaractivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 191)),(Object)(BA.numberCast(int.class, 255)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 254;BA.debugLine="Else if typeofevent = \"Event\" Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Event"))) { 
 BA.debugLineNum = 255;BA.debugLine="mycolor = Colors.ARGB(255, 152, 255, 152)";
Debug.ShouldStop(1073741824);
_mycolor = calendaractivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 152)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 152)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 256;BA.debugLine="Else if typeofevent = \"Birthday\" Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("Birthday"))) { 
 BA.debugLineNum = 257;BA.debugLine="mycolor = Colors.ARGB(255, 255, 182, 193)";
Debug.ShouldStop(1);
_mycolor = calendaractivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 182)),(Object)(BA.numberCast(int.class, 193)));Debug.locals.put("mycolor", _mycolor);
 }else 
{ BA.debugLineNum = 258;BA.debugLine="Else if typeofevent = \"OOO\" Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",_typeofevent,BA.ObjectToString("OOO"))) { 
 BA.debugLineNum = 259;BA.debugLine="mycolor = Colors.ARGB(255, 255, 215, 0)";
Debug.ShouldStop(4);
_mycolor = calendaractivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 215)),(Object)(BA.numberCast(int.class, 0)));Debug.locals.put("mycolor", _mycolor);
 }}}}
;
 BA.debugLineNum = 261;BA.debugLine="Return mycolor";
Debug.ShouldStop(16);
if (true) return _mycolor;
 BA.debugLineNum = 262;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("menu_btn_Click (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,337);
if (RapidSub.canDelegate("menu_btn_click")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","menu_btn_click");}
 BA.debugLineNum = 337;BA.debugLine="Private Sub menu_btn_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 338;BA.debugLine="menupanel.Visible =True";
Debug.ShouldStop(131072);
calendaractivity.mostCurrent._menupanel.runMethod(true,"setVisible",calendaractivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 339;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("Month_btn_Click (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,360);
if (RapidSub.canDelegate("month_btn_click")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","month_btn_click");}
 BA.debugLineNum = 360;BA.debugLine="Private Sub Month_btn_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 361;BA.debugLine="menupanel.Visible = False";
Debug.ShouldStop(256);
calendaractivity.mostCurrent._menupanel.runMethod(true,"setVisible",calendaractivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 362;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _monthsp_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("MonthSp_ItemClick (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,325);
if (RapidSub.canDelegate("monthsp_itemclick")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","monthsp_itemclick", _position, _value);}
RemoteObject _selectedmonth = RemoteObject.createImmutable(0);
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 325;BA.debugLine="Private Sub MonthSp_ItemClick (Position As Int, Va";
Debug.ShouldStop(16);
 BA.debugLineNum = 326;BA.debugLine="Dim selectedmonth As Int = Position +1";
Debug.ShouldStop(32);
_selectedmonth = RemoteObject.solve(new RemoteObject[] {_position,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("selectedmonth", _selectedmonth);Debug.locals.put("selectedmonth", _selectedmonth);
 BA.debugLineNum = 328;BA.debugLine="DrawCalendar(selectedmonth, DateTime.GetYear(Date";
Debug.ShouldStop(128);
_drawcalendar(_selectedmonth,calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"GetYear",(Object)(calendaractivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))));
 BA.debugLineNum = 329;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
calendaractivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 11;BA.debugLine="Dim CalendarMap As Map";
calendaractivity._calendarmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 12;BA.debugLine="Dim kvs As KeyValueStore";
calendaractivity._kvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _sched_btn_click() throws Exception{
try {
		Debug.PushSubsStack("sched_btn_Click (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,341);
if (RapidSub.canDelegate("sched_btn_click")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","sched_btn_click");}
 BA.debugLineNum = 341;BA.debugLine="Private Sub sched_btn_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 342;BA.debugLine="Activity.Finish";
Debug.ShouldStop(2097152);
calendaractivity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 343;BA.debugLine="StartActivity(Schedule_module)";
Debug.ShouldStop(4194304);
calendaractivity.mostCurrent.__c.runVoidMethod ("StartActivity",calendaractivity.processBA,(Object)((calendaractivity.mostCurrent._schedule_module.getObject())));
 BA.debugLineNum = 344;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _weekday(RemoteObject _day) throws Exception{
try {
		Debug.PushSubsStack("Weekday (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,298);
if (RapidSub.canDelegate("weekday")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","weekday", _day);}
Debug.locals.put("Day", _day);
 BA.debugLineNum = 298;BA.debugLine="Sub Weekday (Day As Int)";
Debug.ShouldStop(512);
 BA.debugLineNum = 299;BA.debugLine="If Day = 0 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",_day,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 300;BA.debugLine="day_of_week = \"Sun\"";
Debug.ShouldStop(2048);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Sun");
 }else 
{ BA.debugLineNum = 301;BA.debugLine="Else if Day = 1 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_day,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 302;BA.debugLine="day_of_week = \"Mon\"";
Debug.ShouldStop(8192);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Mon");
 }else 
{ BA.debugLineNum = 303;BA.debugLine="Else if Day = 2 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_day,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 304;BA.debugLine="day_of_week = \"Tue\"";
Debug.ShouldStop(32768);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Tue");
 }else 
{ BA.debugLineNum = 305;BA.debugLine="Else if Day = 3 Then";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",_day,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 306;BA.debugLine="day_of_week = \"Wed\"";
Debug.ShouldStop(131072);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Wed");
 }else 
{ BA.debugLineNum = 307;BA.debugLine="Else if Day = 4 Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_day,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 308;BA.debugLine="day_of_week = \"Thu\"";
Debug.ShouldStop(524288);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Thu");
 }else 
{ BA.debugLineNum = 309;BA.debugLine="Else if Day = 5 Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",_day,BA.numberCast(double.class, 5))) { 
 BA.debugLineNum = 310;BA.debugLine="day_of_week = \"Fri\"";
Debug.ShouldStop(2097152);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Fri");
 }else {
 BA.debugLineNum = 312;BA.debugLine="day_of_week = \"Sat\"";
Debug.ShouldStop(8388608);
calendaractivity.mostCurrent._day_of_week = BA.ObjectToString("Sat");
 }}}}}}
;
 BA.debugLineNum = 314;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _yearsp_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("YearSP_ItemClick (calendaractivity) ","calendaractivity",6,calendaractivity.mostCurrent.activityBA,calendaractivity.mostCurrent,331);
if (RapidSub.canDelegate("yearsp_itemclick")) { return b4a.example.calendaractivity.remoteMe.runUserSub(false, "calendaractivity","yearsp_itemclick", _position, _value);}
RemoteObject _selectedyear = RemoteObject.createImmutable(0);
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 331;BA.debugLine="Private Sub YearSP_ItemClick (Position As Int, Val";
Debug.ShouldStop(1024);
 BA.debugLineNum = 332;BA.debugLine="Dim selectedyear As Int = Value";
Debug.ShouldStop(2048);
_selectedyear = BA.numberCast(int.class, _value);Debug.locals.put("selectedyear", _selectedyear);Debug.locals.put("selectedyear", _selectedyear);
 BA.debugLineNum = 334;BA.debugLine="DrawCalendar(MonthSp.SelectedIndex +1, selectedye";
Debug.ShouldStop(8192);
_drawcalendar(RemoteObject.solve(new RemoteObject[] {calendaractivity.mostCurrent._monthsp.runMethod(true,"getSelectedIndex"),RemoteObject.createImmutable(1)}, "+",1, 1),_selectedyear);
 BA.debugLineNum = 335;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}