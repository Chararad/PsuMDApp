package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class add_events_module_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 27;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(67108864);
switch (BA.switchObjectToInt(add_events_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 29;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",add_events_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_events_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"AEMLayout\")";
Debug.ShouldStop(536870912);
add_events_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayout")),add_events_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark\")";
Debug.ShouldStop(-2147483648);
add_events_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayoutDark")),add_events_module.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 35;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",add_events_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_events_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"AEMLayout2\")";
Debug.ShouldStop(8);
add_events_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayout2")),add_events_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark2\")";
Debug.ShouldStop(32);
add_events_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayoutDark2")),add_events_module.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 41;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",add_events_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_events_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"AEMLayout3\")";
Debug.ShouldStop(512);
add_events_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayout3")),add_events_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark3\")";
Debug.ShouldStop(2048);
add_events_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayoutDark3")),add_events_module.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 48;BA.debugLine="timelbl.Text = currentDate";
Debug.ShouldStop(32768);
add_events_module.mostCurrent._timelbl.runMethod(true,"setText",BA.ObjectToCharSequence(add_events_module._currentdate));
 BA.debugLineNum = 50;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Pause (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,64);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 64;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 66;BA.debugLine="eventrb.Checked = False";
Debug.ShouldStop(2);
add_events_module.mostCurrent._eventrb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 67;BA.debugLine="taskrb.Checked = False";
Debug.ShouldStop(4);
add_events_module.mostCurrent._taskrb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 68;BA.debugLine="birthdayrb.Checked = False";
Debug.ShouldStop(8);
add_events_module.mostCurrent._birthdayrb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 69;BA.debugLine="ooorb.Checked = False";
Debug.ShouldStop(16);
add_events_module.mostCurrent._ooorb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"False"));
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
		Debug.PushSubsStack("Activity_Resume (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,52);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","activity_resume");}
 BA.debugLineNum = 52;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 53;BA.debugLine="If eventtype = \"Event\" Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",add_events_module._eventtype,BA.ObjectToString("Event"))) { 
 BA.debugLineNum = 54;BA.debugLine="eventrb.Checked = True";
Debug.ShouldStop(2097152);
add_events_module.mostCurrent._eventrb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"True"));
 }else 
{ BA.debugLineNum = 55;BA.debugLine="Else if eventtype = \"Task\" Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",add_events_module._eventtype,BA.ObjectToString("Task"))) { 
 BA.debugLineNum = 56;BA.debugLine="taskrb.Checked = True";
Debug.ShouldStop(8388608);
add_events_module.mostCurrent._taskrb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"True"));
 }else 
{ BA.debugLineNum = 57;BA.debugLine="Else if eventtype = \"Birthday\" Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",add_events_module._eventtype,BA.ObjectToString("Birthday"))) { 
 BA.debugLineNum = 58;BA.debugLine="birthdayrb.Checked = True";
Debug.ShouldStop(33554432);
add_events_module.mostCurrent._birthdayrb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"True"));
 }else 
{ BA.debugLineNum = 59;BA.debugLine="Else if eventtype = \"OOO\" Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",add_events_module._eventtype,BA.ObjectToString("OOO"))) { 
 BA.debugLineNum = 60;BA.debugLine="ooorb.Checked = True";
Debug.ShouldStop(134217728);
add_events_module.mostCurrent._ooorb.runMethodAndSync(true,"setChecked",add_events_module.mostCurrent.__c.getField(true,"True"));
 }}}}
;
 BA.debugLineNum = 62;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("birthdayrb_CheckedChange (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,129);
if (RapidSub.canDelegate("birthdayrb_checkedchange")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","birthdayrb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 129;BA.debugLine="Private Sub birthdayrb_CheckedChange(Checked As Bo";
Debug.ShouldStop(1);
 BA.debugLineNum = 130;BA.debugLine="eventtype = \"Birthday\"";
Debug.ShouldStop(2);
add_events_module._eventtype = BA.ObjectToString("Birthday");
 BA.debugLineNum = 131;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("eventrb_CheckedChange (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,133);
if (RapidSub.canDelegate("eventrb_checkedchange")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","eventrb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 133;BA.debugLine="Private Sub eventrb_CheckedChange(Checked As Boole";
Debug.ShouldStop(16);
 BA.debugLineNum = 134;BA.debugLine="eventtype = \"Event\"";
Debug.ShouldStop(32);
add_events_module._eventtype = BA.ObjectToString("Event");
 BA.debugLineNum = 135;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private taskrb As RadioButton";
add_events_module.mostCurrent._taskrb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private eventrb As RadioButton";
add_events_module.mostCurrent._eventrb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private birthdayrb As RadioButton";
add_events_module.mostCurrent._birthdayrb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private ooorb As RadioButton";
add_events_module.mostCurrent._ooorb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private timelbl As Label";
add_events_module.mostCurrent._timelbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private title_et As EditText";
add_events_module.mostCurrent._title_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private description_et As EditText";
add_events_module.mostCurrent._description_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _mapinitializer() throws Exception{
try {
		Debug.PushSubsStack("MapInitializer (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,81);
if (RapidSub.canDelegate("mapinitializer")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","mapinitializer");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _allevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _timeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 81;BA.debugLine="Sub MapInitializer As Map";
Debug.ShouldStop(65536);
 BA.debugLineNum = 82;BA.debugLine="Dim eventmap As Map";
Debug.ShouldStop(131072);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 84;BA.debugLine="eventmap.Initialize";
Debug.ShouldStop(524288);
_eventmap.runVoidMethod ("Initialize");
 BA.debugLineNum = 85;BA.debugLine="Dim allevents As List";
Debug.ShouldStop(1048576);
_allevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("allevents", _allevents);
 BA.debugLineNum = 86;BA.debugLine="allevents.initialize";
Debug.ShouldStop(2097152);
_allevents.runVoidMethod ("Initialize");
 BA.debugLineNum = 88;BA.debugLine="Dim timeline As List";
Debug.ShouldStop(8388608);
_timeline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("timeline", _timeline);
 BA.debugLineNum = 89;BA.debugLine="timeline.initialize";
Debug.ShouldStop(16777216);
_timeline.runVoidMethod ("Initialize");
 BA.debugLineNum = 91;BA.debugLine="eventmap.Put(\"AllEvents\", allevents)";
Debug.ShouldStop(67108864);
_eventmap.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("AllEvents"))),(Object)((_allevents.getObject())));
 BA.debugLineNum = 92;BA.debugLine="eventmap.Put(\"Timeline\", timeline)";
Debug.ShouldStop(134217728);
_eventmap.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Timeline"))),(Object)((_timeline.getObject())));
 BA.debugLineNum = 94;BA.debugLine="CalendarActivity.CalendarMap.Put(day_module.curre";
Debug.ShouldStop(536870912);
add_events_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runVoidMethod ("Put",(Object)((add_events_module.mostCurrent._day_module._currentdate /*RemoteObject*/ )),(Object)((_eventmap.getObject())));
 BA.debugLineNum = 96;BA.debugLine="Return eventmap";
Debug.ShouldStop(-2147483648);
if (true) return _eventmap;
 BA.debugLineNum = 97;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ooorb_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("ooorb_CheckedChange (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,125);
if (RapidSub.canDelegate("ooorb_checkedchange")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","ooorb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 125;BA.debugLine="Private Sub ooorb_CheckedChange(Checked As Boolean";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 126;BA.debugLine="eventtype = \"OOO\"";
Debug.ShouldStop(536870912);
add_events_module._eventtype = BA.ObjectToString("OOO");
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
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim eventtype As String";
add_events_module._eventtype = RemoteObject.createImmutable("");
 //BA.debugLineNum = 10;BA.debugLine="Dim currentDate As String";
add_events_module._currentdate = RemoteObject.createImmutable("");
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _save_btn_click() throws Exception{
try {
		Debug.PushSubsStack("save_btn_Click (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,99);
if (RapidSub.canDelegate("save_btn_click")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","save_btn_click");}
RemoteObject _eventmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _getallevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _putevent = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 99;BA.debugLine="Private Sub save_btn_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 100;BA.debugLine="Dim eventmap As Map";
Debug.ShouldStop(8);
_eventmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("eventmap", _eventmap);
 BA.debugLineNum = 101;BA.debugLine="If title_et.text = \"\" Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",add_events_module.mostCurrent._title_et.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 102;BA.debugLine="MsgboxAsync(\"Enter The Event Title\", \"Error\")";
Debug.ShouldStop(32);
add_events_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter The Event Title")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_events_module.processBA);
 BA.debugLineNum = 103;BA.debugLine="Return";
Debug.ShouldStop(64);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 106;BA.debugLine="If CalendarActivity.CalendarMap.ContainsKey(day_m";
Debug.ShouldStop(512);
if (add_events_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(true,"ContainsKey",(Object)((add_events_module.mostCurrent._day_module._currentdate /*RemoteObject*/ ))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 107;BA.debugLine="eventmap = CalendarActivity.CalendarMap.Get(day_";
Debug.ShouldStop(1024);
_eventmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), add_events_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .runMethod(false,"Get",(Object)((add_events_module.mostCurrent._day_module._currentdate /*RemoteObject*/ ))));Debug.locals.put("eventmap", _eventmap);
 }else {
 BA.debugLineNum = 109;BA.debugLine="eventmap = MapInitializer";
Debug.ShouldStop(4096);
_eventmap = _mapinitializer();Debug.locals.put("eventmap", _eventmap);
 };
 BA.debugLineNum = 112;BA.debugLine="Dim getAllevents As List = eventmap.Get(\"AllEvent";
Debug.ShouldStop(32768);
_getallevents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_getallevents = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _eventmap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("AllEvents")))));Debug.locals.put("getAllevents", _getallevents);Debug.locals.put("getAllevents", _getallevents);
 BA.debugLineNum = 113;BA.debugLine="Dim putevent As Map";
Debug.ShouldStop(65536);
_putevent = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("putevent", _putevent);
 BA.debugLineNum = 114;BA.debugLine="putevent.Initialize";
Debug.ShouldStop(131072);
_putevent.runVoidMethod ("Initialize");
 BA.debugLineNum = 115;BA.debugLine="putevent.Put(\"Title\", title_et.Text)";
Debug.ShouldStop(262144);
_putevent.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Title"))),(Object)((add_events_module.mostCurrent._title_et.runMethod(true,"getText"))));
 BA.debugLineNum = 116;BA.debugLine="putevent.Put(\"Description\", description_et.Text)";
Debug.ShouldStop(524288);
_putevent.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Description"))),(Object)((add_events_module.mostCurrent._description_et.runMethod(true,"getText"))));
 BA.debugLineNum = 117;BA.debugLine="putevent.Put(\"Tags\", eventtype)";
Debug.ShouldStop(1048576);
_putevent.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Tags"))),(Object)((add_events_module._eventtype)));
 BA.debugLineNum = 119;BA.debugLine="getAllevents.Add(putevent)";
Debug.ShouldStop(4194304);
_getallevents.runVoidMethod ("Add",(Object)((_putevent.getObject())));
 BA.debugLineNum = 120;BA.debugLine="SaveCalendar";
Debug.ShouldStop(8388608);
_savecalendar();
 BA.debugLineNum = 121;BA.debugLine="day_module.addeventsfeedback = True";
Debug.ShouldStop(16777216);
add_events_module.mostCurrent._day_module._addeventsfeedback /*RemoteObject*/  = add_events_module.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 122;BA.debugLine="Activity.finish";
Debug.ShouldStop(33554432);
add_events_module.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _savecalendar() throws Exception{
try {
		Debug.PushSubsStack("SaveCalendar (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,77);
if (RapidSub.canDelegate("savecalendar")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","savecalendar");}
 BA.debugLineNum = 77;BA.debugLine="Sub SaveCalendar";
Debug.ShouldStop(4096);
 BA.debugLineNum = 78;BA.debugLine="CalendarActivity.kvs.put(\"CalendarKVS\", CalendarA";
Debug.ShouldStop(8192);
add_events_module.mostCurrent._calendaractivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("CalendarKVS")),(Object)((add_events_module.mostCurrent._calendaractivity._calendarmap /*RemoteObject*/ .getObject())));
 BA.debugLineNum = 79;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
		Debug.PushSubsStack("taskrb_CheckedChange (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,137);
if (RapidSub.canDelegate("taskrb_checkedchange")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","taskrb_checkedchange", _checked);}
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 137;BA.debugLine="Private Sub taskrb_CheckedChange(Checked As Boolea";
Debug.ShouldStop(256);
 BA.debugLineNum = 138;BA.debugLine="eventtype = \"Task\"";
Debug.ShouldStop(512);
add_events_module._eventtype = BA.ObjectToString("Task");
 BA.debugLineNum = 139;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _x_btn_click() throws Exception{
try {
		Debug.PushSubsStack("x_btn_Click (add_events_module) ","add_events_module",16,add_events_module.mostCurrent.activityBA,add_events_module.mostCurrent,73);
if (RapidSub.canDelegate("x_btn_click")) { return b4a.example.add_events_module.remoteMe.runUserSub(false, "add_events_module","x_btn_click");}
 BA.debugLineNum = 73;BA.debugLine="Private Sub x_btn_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="Activity.Finish";
Debug.ShouldStop(512);
add_events_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 75;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}