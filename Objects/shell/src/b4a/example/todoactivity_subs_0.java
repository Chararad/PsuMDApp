package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class todoactivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,55);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","activity_create", _firsttime);}
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _title = RemoteObject.createImmutable("");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 55;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 56;BA.debugLine="Activity.LoadLayout(\"MAINtodolistlayout\")";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("MAINtodolistlayout")),todoactivity.mostCurrent.activityBA);
 BA.debugLineNum = 58;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(33554432);
switch (BA.switchObjectToInt(todoactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 60;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,todoactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 61;BA.debugLine="TabHost1.AddTab(\"Your Lists\", \"todolistLayout.";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._tabhost1.runVoidMethodAndSync ("AddTab",todoactivity.mostCurrent.activityBA,(Object)(BA.ObjectToString("Your Lists")),(Object)(RemoteObject.createImmutable("todolistLayout.bal")));
 BA.debugLineNum = 62;BA.debugLine="TabHost1.AddTab(\"Groups\", \"grouptodolistlayout";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._tabhost1.runVoidMethodAndSync ("AddTab",todoactivity.mostCurrent.activityBA,(Object)(BA.ObjectToString("Groups")),(Object)(RemoteObject.createImmutable("grouptodolistlayout.bal")));
 }else {
 BA.debugLineNum = 64;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark\")";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("todoListLayoutDark")),todoactivity.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 67;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,todoactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 68;BA.debugLine="Activity.LoadLayout(\"todoListLayout2\")";
Debug.ShouldStop(8);
todoactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("todoListLayout2")),todoactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 70;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark2\")";
Debug.ShouldStop(32);
todoactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("todoListLayoutDark2")),todoactivity.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 73;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,todoactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"todoListLayout3\")";
Debug.ShouldStop(512);
todoactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("todoListLayout3")),todoactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 76;BA.debugLine="Activity.LoadLayout(\"todoListLayoutDark3\")";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("todoListLayoutDark3")),todoactivity.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 80;BA.debugLine="addTitleTextArea.Tag = Null";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 81;BA.debugLine="addTitleTextArea.Background = Null";
Debug.ShouldStop(65536);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setBackground",(todoactivity.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 83;BA.debugLine="newAddTaskBtn";
Debug.ShouldStop(262144);
_newaddtaskbtn();
 BA.debugLineNum = 84;BA.debugLine="tasksList.GetBase.Visible = False";
Debug.ShouldStop(524288);
todoactivity.mostCurrent._taskslist.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 86;BA.debugLine="kvs = Starter.taskKvs";
Debug.ShouldStop(2097152);
todoactivity._kvs = todoactivity.mostCurrent._starter._taskkvs /*RemoteObject*/ ;
 BA.debugLineNum = 88;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
Debug.ShouldStop(8388608);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("lists"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 89;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
Debug.ShouldStop(16777216);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 90;BA.debugLine="For Each title As String In savedLists";
Debug.ShouldStop(33554432);
{
final RemoteObject group30 = _savedlists;
final int groupLen30 = group30.runMethod(true,"getSize").<Integer>get()
;int index30 = 0;
;
for (; index30 < groupLen30;index30++){
_title = BA.ObjectToString(group30.runMethod(false,"Get",index30));Debug.locals.put("title", _title);
Debug.locals.put("title", _title);
 BA.debugLineNum = 91;BA.debugLine="listsList.AddTextItem(title, title)";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._listslist.runVoidMethod ("_addtextitem",(Object)((_title)),(Object)((_title)));
 }
}Debug.locals.put("title", _title);
;
 };
 BA.debugLineNum = 95;BA.debugLine="loadMyGroups";
Debug.ShouldStop(1073741824);
_loadmygroups();
 BA.debugLineNum = 97;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addtaskbtn_click() throws Exception{
try {
		Debug.PushSubsStack("addTaskBtn_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,389);
if (RapidSub.canDelegate("addtaskbtn_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","addtaskbtn_click");}
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
 BA.debugLineNum = 389;BA.debugLine="Sub addTaskBtn_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 391;BA.debugLine="addTaskBtn.Enabled = False";
Debug.ShouldStop(64);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 392;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1)";
Debug.ShouldStop(128);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._taskslist.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 394;BA.debugLine="addTaskPanel.Initialize(\"addTaskPanel\")";
Debug.ShouldStop(512);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskPanel")));
 BA.debugLineNum = 395;BA.debugLine="addTaskPanel.SetLayout(10dip, 0, 20dip, 120dip)";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("SetLayout",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 396;BA.debugLine="addTaskPanel.Color = Colors.ARGB(0, 255, 255, 255";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 398;BA.debugLine="addTaskTextArea.Initialize(\"addTodoText\")";
Debug.ShouldStop(8192);
todoactivity.mostCurrent._addtasktextarea.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTodoText")));
 BA.debugLineNum = 399;BA.debugLine="addTaskTextArea.Hint = \"Add a task...\"";
Debug.ShouldStop(16384);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHint",BA.ObjectToString("Add a task..."));
 BA.debugLineNum = 400;BA.debugLine="addTaskTextArea.Tag = Null";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._addtasktextarea.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 402;BA.debugLine="enterTaskBtn.Initialize(\"enterTaskBtn\")";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._entertaskbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("enterTaskBtn")));
 BA.debugLineNum = 403;BA.debugLine="enterTaskBtn.Text = \"Enter task\"";
Debug.ShouldStop(262144);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Enter task"));
 BA.debugLineNum = 405;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(1048576);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 406;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(2097152);
switch (BA.switchObjectToInt(todoactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 408;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(8388608);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 409;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 410;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 411;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
Debug.ShouldStop(67108864);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 59)),(Object)(BA.numberCast(int.class, 117)),(Object)(BA.numberCast(int.class, 151)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 412;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 413;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 415;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(120, 1";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46))));
 BA.debugLineNum = 416;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 417;BA.debugLine="cd.Initialize(Colors.ARGB(120, 98, 43, 20), 20";
Debug.ShouldStop(1);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 418;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(2);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 419;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(4);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 422;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(32);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 423;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
Debug.ShouldStop(64);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 424;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(128);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 425;BA.debugLine="cd.Initialize(Colors.ARGB(100, 255, 255, 255),";
Debug.ShouldStop(256);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 426;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(512);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 427;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 429;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(255, 1";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 137)),(Object)(BA.numberCast(int.class, 162)),(Object)(BA.numberCast(int.class, 185))));
 BA.debugLineNum = 430;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(8192);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 431;BA.debugLine="cd.Initialize(Colors.ARGB(255, 137, 162, 185),";
Debug.ShouldStop(16384);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 137)),(Object)(BA.numberCast(int.class, 162)),(Object)(BA.numberCast(int.class, 185)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 432;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 433;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(65536);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 2: {
 BA.debugLineNum = 436;BA.debugLine="addTaskTextArea.Typeface = pixeltf";
Debug.ShouldStop(524288);
todoactivity.mostCurrent._addtasktextarea.runMethod(false,"setTypeface",(todoactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 437;BA.debugLine="enterTaskBtn.Typeface = pixeltf";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setTypeface",(todoactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 438;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(2097152);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 439;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 440;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 441;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
Debug.ShouldStop(16777216);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 90)),(Object)(BA.numberCast(int.class, 105)),(Object)(BA.numberCast(int.class, 136)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 442;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 443;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 445;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17))));
 BA.debugLineNum = 446;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 447;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
Debug.ShouldStop(1073741824);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 448;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 449;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
Debug.ShouldStop(1);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 break; }
}
;
 BA.debugLineNum = 453;BA.debugLine="addTaskPanel.AddView(addTaskTextArea, 0, 0, addTa";
Debug.ShouldStop(16);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("AddView",(Object)((todoactivity.mostCurrent._addtasktextarea.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent._addtaskbtnpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 454;BA.debugLine="addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addT";
Debug.ShouldStop(32);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("AddView",(Object)((todoactivity.mostCurrent._entertaskbtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(todoactivity.mostCurrent._addtaskbtnpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 456;BA.debugLine="tasksList.Add(addTaskPanel, addTaskPanel)";
Debug.ShouldStop(128);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), todoactivity.mostCurrent._addtaskpanel.getObject()),(Object)((todoactivity.mostCurrent._addtaskpanel.getObject())));
 BA.debugLineNum = 458;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addtaskbtngrp_click() throws Exception{
try {
		Debug.PushSubsStack("addTaskBtnGrp_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1341);
if (RapidSub.canDelegate("addtaskbtngrp_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","addtaskbtngrp_click");}
RemoteObject _addbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _currentgrplist = RemoteObject.createImmutable("");
RemoteObject _addpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _addet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _confirmbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _ctx2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 1341;BA.debugLine="Sub addTaskBtnGrp_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 1342;BA.debugLine="Dim addBtn As Button = Sender";
Debug.ShouldStop(536870912);
_addbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_addbtn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("addBtn", _addbtn);Debug.locals.put("addBtn", _addbtn);
 BA.debugLineNum = 1343;BA.debugLine="Dim ctx As List = addBtn.Tag";
Debug.ShouldStop(1073741824);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_ctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _addbtn.runMethod(false,"getTag"));Debug.locals.put("ctx", _ctx);Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1344;BA.debugLine="Dim code As String = ctx.Get(0)";
Debug.ShouldStop(-2147483648);
_code = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1345;BA.debugLine="Dim currentGrpList As String = ctx.Get(1)";
Debug.ShouldStop(1);
_currentgrplist = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("currentGrpList", _currentgrplist);Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1347;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
Debug.ShouldStop(4);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._taskslistgrp.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 1349;BA.debugLine="Dim addPNL As Panel";
Debug.ShouldStop(16);
_addpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("addPNL", _addpnl);
 BA.debugLineNum = 1350;BA.debugLine="addPNL.Initialize(\"addTaskPNLGrp\")";
Debug.ShouldStop(32);
_addpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskPNLGrp")));
 BA.debugLineNum = 1351;BA.debugLine="addPNL.SetLayout(10dip, 0, 20dip, 120dip)";
Debug.ShouldStop(64);
_addpnl.runVoidMethod ("SetLayout",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 1352;BA.debugLine="addPNL.Color = Colors.Transparent";
Debug.ShouldStop(128);
_addpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 1354;BA.debugLine="Dim addET As EditText";
Debug.ShouldStop(512);
_addet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("addET", _addet);
 BA.debugLineNum = 1355;BA.debugLine="addET.Initialize(\"addTaskETGrp\")";
Debug.ShouldStop(1024);
_addet.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskETGrp")));
 BA.debugLineNum = 1356;BA.debugLine="addET.Hint = \"Add a task...\"";
Debug.ShouldStop(2048);
_addet.runMethod(true,"setHint",BA.ObjectToString("Add a task..."));
 BA.debugLineNum = 1357;BA.debugLine="addET.Tag = Null";
Debug.ShouldStop(4096);
_addet.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 1359;BA.debugLine="Dim confirmBtn As Button";
Debug.ShouldStop(16384);
_confirmbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("confirmBtn", _confirmbtn);
 BA.debugLineNum = 1360;BA.debugLine="confirmBtn.Initialize(\"enterTaskBtnGrp\")";
Debug.ShouldStop(32768);
_confirmbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("enterTaskBtnGrp")));
 BA.debugLineNum = 1361;BA.debugLine="confirmBtn.Text = \"Enter task\"";
Debug.ShouldStop(65536);
_confirmbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Enter task"));
 BA.debugLineNum = 1363;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(262144);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 1364;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
Debug.ShouldStop(524288);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 1365;BA.debugLine="confirmBtn.Background = cd";
Debug.ShouldStop(1048576);
_confirmbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 1366;BA.debugLine="confirmBtn.TextColor = Colors.White";
Debug.ShouldStop(2097152);
_confirmbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 1369;BA.debugLine="Dim ctx2 As List";
Debug.ShouldStop(16777216);
_ctx2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx2", _ctx2);
 BA.debugLineNum = 1370;BA.debugLine="ctx2.Initialize";
Debug.ShouldStop(33554432);
_ctx2.runVoidMethod ("Initialize");
 BA.debugLineNum = 1371;BA.debugLine="ctx2.Add(code)";
Debug.ShouldStop(67108864);
_ctx2.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 1372;BA.debugLine="ctx2.Add(currentGrpList)";
Debug.ShouldStop(134217728);
_ctx2.runVoidMethod ("Add",(Object)((_currentgrplist)));
 BA.debugLineNum = 1373;BA.debugLine="ctx2.Add(addET)     ' index 2: the EditText";
Debug.ShouldStop(268435456);
_ctx2.runVoidMethod ("Add",(Object)((_addet.getObject())));
 BA.debugLineNum = 1374;BA.debugLine="ctx2.Add(Null)      ' index 3: oldTask (Null = ne";
Debug.ShouldStop(536870912);
_ctx2.runVoidMethod ("Add",(Object)(todoactivity.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 1375;BA.debugLine="confirmBtn.Tag = ctx2";
Debug.ShouldStop(1073741824);
_confirmbtn.runMethod(false,"setTag",(_ctx2.getObject()));
 BA.debugLineNum = 1377;BA.debugLine="addPNL.AddView(addET, 0, 0, 190dip, 60dip)";
Debug.ShouldStop(1);
_addpnl.runVoidMethod ("AddView",(Object)((_addet.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 190)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 1378;BA.debugLine="addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40di";
Debug.ShouldStop(2);
_addpnl.runVoidMethod ("AddView",(Object)((_confirmbtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 190)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 1380;BA.debugLine="tasksListGrp.Add(addPNL, addPNL)";
Debug.ShouldStop(8);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _addpnl.getObject()),(Object)((_addpnl.getObject())));
 BA.debugLineNum = 1381;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addtitletextarea_enterpressed() throws Exception{
try {
		Debug.PushSubsStack("addTitleTextArea_EnterPressed (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,177);
if (RapidSub.canDelegate("addtitletextarea_enterpressed")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","addtitletextarea_enterpressed");}
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _oldindex = RemoteObject.createImmutable(0);
RemoteObject _oldtitle = RemoteObject.createImmutable("");
RemoteObject _newtitle = RemoteObject.createImmutable("");
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _existingtitle = RemoteObject.createImmutable("");
RemoteObject _oldkey = RemoteObject.createImmutable("");
RemoteObject _newkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject _oldck = RemoteObject.createImmutable("");
RemoteObject _newck = RemoteObject.createImmutable("");
RemoteObject _savedlists2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _t = RemoteObject.createImmutable("");
RemoteObject _title = RemoteObject.createImmutable("");
 BA.debugLineNum = 177;BA.debugLine="Sub addTitleTextArea_EnterPressed";
Debug.ShouldStop(65536);
 BA.debugLineNum = 180;BA.debugLine="If addTitleTextArea.Tag <> Null And addTitleTextA";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("N",todoactivity.mostCurrent._addtitletextarea.runMethod(false,"getTag")) && RemoteObject.solveBoolean("i",todoactivity.mostCurrent._addtitletextarea.runMethod(false,"getTag"), RemoteObject.createImmutable("java.util.List"))) { 
 BA.debugLineNum = 181;BA.debugLine="Dim ctx As List = addTitleTextArea.Tag";
Debug.ShouldStop(1048576);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_ctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity.mostCurrent._addtitletextarea.runMethod(false,"getTag"));Debug.locals.put("ctx", _ctx);Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 182;BA.debugLine="Dim oldIndex As Int = ctx.Get(0)";
Debug.ShouldStop(2097152);
_oldindex = BA.numberCast(int.class, _ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("oldIndex", _oldindex);Debug.locals.put("oldIndex", _oldindex);
 BA.debugLineNum = 183;BA.debugLine="Dim oldTitle As String = ctx.Get(1)";
Debug.ShouldStop(4194304);
_oldtitle = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("oldTitle", _oldtitle);Debug.locals.put("oldTitle", _oldtitle);
 BA.debugLineNum = 184;BA.debugLine="Dim newTitle As String = addTitleTextArea.Text.T";
Debug.ShouldStop(8388608);
_newtitle = todoactivity.mostCurrent._addtitletextarea.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("newTitle", _newtitle);Debug.locals.put("newTitle", _newtitle);
 BA.debugLineNum = 186;BA.debugLine="If newTitle = \"\" Or newTitle = oldTitle Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",_newtitle,BA.ObjectToString("")) || RemoteObject.solveBoolean("=",_newtitle,_oldtitle)) { 
 BA.debugLineNum = 187;BA.debugLine="addTitleTextArea.Tag = Null";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 188;BA.debugLine="addTitleTextArea.Text = currentList";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity.mostCurrent._currentlist));
 BA.debugLineNum = 189;BA.debugLine="addTitleTextArea.Enabled = False";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 190;BA.debugLine="Return";
Debug.ShouldStop(536870912);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 194;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
Debug.ShouldStop(2);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 195;BA.debugLine="For Each existingTitle As String In savedLists";
Debug.ShouldStop(4);
{
final RemoteObject group13 = _savedlists;
final int groupLen13 = group13.runMethod(true,"getSize").<Integer>get()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_existingtitle = BA.ObjectToString(group13.runMethod(false,"Get",index13));Debug.locals.put("existingTitle", _existingtitle);
Debug.locals.put("existingTitle", _existingtitle);
 BA.debugLineNum = 196;BA.debugLine="If existingTitle = newTitle Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",_existingtitle,_newtitle)) { 
 BA.debugLineNum = 197;BA.debugLine="MsgboxAsync(\"A list with that name already exi";
Debug.ShouldStop(16);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("A list with that name already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate title"))),todoactivity.processBA);
 BA.debugLineNum = 198;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("existingTitle", _existingtitle);
;
 BA.debugLineNum = 203;BA.debugLine="savedLists.Set(oldIndex, newTitle)";
Debug.ShouldStop(1024);
_savedlists.runVoidMethod ("Set",(Object)(_oldindex),(Object)((_newtitle)));
 BA.debugLineNum = 204;BA.debugLine="kvs.Put(\"lists\", savedLists)";
Debug.ShouldStop(2048);
todoactivity._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("lists")),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 207;BA.debugLine="Dim oldKey As String = \"list_\" & oldTitle";
Debug.ShouldStop(16384);
_oldkey = RemoteObject.concat(RemoteObject.createImmutable("list_"),_oldtitle);Debug.locals.put("oldKey", _oldkey);Debug.locals.put("oldKey", _oldkey);
 BA.debugLineNum = 208;BA.debugLine="Dim newKey As String = \"list_\" & newTitle";
Debug.ShouldStop(32768);
_newkey = RemoteObject.concat(RemoteObject.createImmutable("list_"),_newtitle);Debug.locals.put("newKey", _newkey);Debug.locals.put("newKey", _newkey);
 BA.debugLineNum = 209;BA.debugLine="If kvs.ContainsKey(oldKey) Then";
Debug.ShouldStop(65536);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_oldkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 210;BA.debugLine="Dim savedTasks As List = kvs.Get(oldKey)";
Debug.ShouldStop(131072);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_oldkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 211;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(262144);
{
final RemoteObject group25 = _savedtasks;
final int groupLen25 = group25.runMethod(true,"getSize").<Integer>get()
;int index25 = 0;
;
for (; index25 < groupLen25;index25++){
_task = BA.ObjectToString(group25.runMethod(false,"Get",index25));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 212;BA.debugLine="Dim oldCK As String = \"checked_\" & oldTitle &";
Debug.ShouldStop(524288);
_oldck = RemoteObject.concat(RemoteObject.createImmutable("checked_"),_oldtitle,RemoteObject.createImmutable("_"),_task);Debug.locals.put("oldCK", _oldck);Debug.locals.put("oldCK", _oldck);
 BA.debugLineNum = 213;BA.debugLine="Dim newCK As String = \"checked_\" & newTitle &";
Debug.ShouldStop(1048576);
_newck = RemoteObject.concat(RemoteObject.createImmutable("checked_"),_newtitle,RemoteObject.createImmutable("_"),_task);Debug.locals.put("newCK", _newck);Debug.locals.put("newCK", _newck);
 BA.debugLineNum = 214;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
Debug.ShouldStop(2097152);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_oldck)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 215;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
Debug.ShouldStop(4194304);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_newck),(Object)(todoactivity._kvs.runMethod(false,"_get",(Object)(_oldck))));
 BA.debugLineNum = 216;BA.debugLine="kvs.Remove(oldCK)";
Debug.ShouldStop(8388608);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_oldck));
 };
 }
}Debug.locals.put("task", _task);
;
 BA.debugLineNum = 219;BA.debugLine="kvs.Put(newKey, savedTasks)";
Debug.ShouldStop(67108864);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_newkey),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 220;BA.debugLine="kvs.Remove(oldKey)";
Debug.ShouldStop(134217728);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_oldkey));
 };
 BA.debugLineNum = 224;BA.debugLine="If currentList = oldTitle Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._currentlist,_oldtitle)) { 
 BA.debugLineNum = 225;BA.debugLine="currentList = newTitle";
Debug.ShouldStop(1);
todoactivity.mostCurrent._currentlist = _newtitle;
 };
 BA.debugLineNum = 229;BA.debugLine="listsList.Clear";
Debug.ShouldStop(16);
todoactivity.mostCurrent._listslist.runVoidMethod ("_clear");
 BA.debugLineNum = 230;BA.debugLine="Dim savedLists2 As List = kvs.Get(\"lists\")";
Debug.ShouldStop(32);
_savedlists2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists2 = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists2", _savedlists2);Debug.locals.put("savedLists2", _savedlists2);
 BA.debugLineNum = 231;BA.debugLine="For Each t As String In savedLists2";
Debug.ShouldStop(64);
{
final RemoteObject group41 = _savedlists2;
final int groupLen41 = group41.runMethod(true,"getSize").<Integer>get()
;int index41 = 0;
;
for (; index41 < groupLen41;index41++){
_t = BA.ObjectToString(group41.runMethod(false,"Get",index41));Debug.locals.put("t", _t);
Debug.locals.put("t", _t);
 BA.debugLineNum = 232;BA.debugLine="listsList.AddTextItem(t, t)";
Debug.ShouldStop(128);
todoactivity.mostCurrent._listslist.runVoidMethod ("_addtextitem",(Object)((_t)),(Object)((_t)));
 }
}Debug.locals.put("t", _t);
;
 BA.debugLineNum = 235;BA.debugLine="addTitleTextArea.Tag = Null";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 236;BA.debugLine="addTitleTextArea.Text = currentList";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity.mostCurrent._currentlist));
 BA.debugLineNum = 237;BA.debugLine="addTitleTextArea.Enabled = False";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 238;BA.debugLine="newListBtn.Enabled = True";
Debug.ShouldStop(8192);
todoactivity.mostCurrent._newlistbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 239;BA.debugLine="isAddingList = False";
Debug.ShouldStop(16384);
todoactivity._isaddinglist = todoactivity.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 240;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
Debug.ShouldStop(32768);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("List renamed")),(Object)(todoactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 241;BA.debugLine="Return";
Debug.ShouldStop(65536);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 245;BA.debugLine="Dim title As String = addTitleTextArea.Text";
Debug.ShouldStop(1048576);
_title = todoactivity.mostCurrent._addtitletextarea.runMethod(true,"getText");Debug.locals.put("title", _title);Debug.locals.put("title", _title);
 BA.debugLineNum = 247;BA.debugLine="Dim savedLists As List";
Debug.ShouldStop(4194304);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 248;BA.debugLine="savedLists.Initialize";
Debug.ShouldStop(8388608);
_savedlists.runVoidMethod ("Initialize");
 BA.debugLineNum = 250;BA.debugLine="If title = \"\" Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",_title,BA.ObjectToString(""))) { 
 BA.debugLineNum = 251;BA.debugLine="title = \"Untitled\" & untitledNo";
Debug.ShouldStop(67108864);
_title = RemoteObject.concat(RemoteObject.createImmutable("Untitled"),todoactivity._untitledno);Debug.locals.put("title", _title);
 BA.debugLineNum = 253;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
Debug.ShouldStop(268435456);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("lists"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 254;BA.debugLine="savedLists = kvs.Get(\"lists\")";
Debug.ShouldStop(536870912);
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 255;BA.debugLine="For Each existingTitle As String In savedLists";
Debug.ShouldStop(1073741824);
{
final RemoteObject group59 = _savedlists;
final int groupLen59 = group59.runMethod(true,"getSize").<Integer>get()
;int index59 = 0;
;
for (; index59 < groupLen59;index59++){
_existingtitle = BA.ObjectToString(group59.runMethod(false,"Get",index59));Debug.locals.put("existingTitle", _existingtitle);
Debug.locals.put("existingTitle", _existingtitle);
 BA.debugLineNum = 256;BA.debugLine="If title = existingTitle Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",_title,_existingtitle)) { 
 BA.debugLineNum = 257;BA.debugLine="untitledNo = untitledNo + 1";
Debug.ShouldStop(1);
todoactivity._untitledno = RemoteObject.solve(new RemoteObject[] {todoactivity._untitledno,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 258;BA.debugLine="title = \"Untitled\" & untitledNo";
Debug.ShouldStop(2);
_title = RemoteObject.concat(RemoteObject.createImmutable("Untitled"),todoactivity._untitledno);Debug.locals.put("title", _title);
 };
 }
}Debug.locals.put("existingTitle", _existingtitle);
;
 };
 BA.debugLineNum = 263;BA.debugLine="newListBtn.Enabled = True";
Debug.ShouldStop(64);
todoactivity.mostCurrent._newlistbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 };
 BA.debugLineNum = 266;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
Debug.ShouldStop(512);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("lists"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 267;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
Debug.ShouldStop(1024);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 268;BA.debugLine="For Each existingTitle As String In savedLists";
Debug.ShouldStop(2048);
{
final RemoteObject group70 = _savedlists;
final int groupLen70 = group70.runMethod(true,"getSize").<Integer>get()
;int index70 = 0;
;
for (; index70 < groupLen70;index70++){
_existingtitle = BA.ObjectToString(group70.runMethod(false,"Get",index70));Debug.locals.put("existingTitle", _existingtitle);
Debug.locals.put("existingTitle", _existingtitle);
 BA.debugLineNum = 269;BA.debugLine="If existingTitle = title Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_existingtitle,_title)) { 
 BA.debugLineNum = 270;BA.debugLine="MsgboxAsync(\"List already exists.\", \"Duplicate";
Debug.ShouldStop(8192);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("List already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate title"))),todoactivity.processBA);
 BA.debugLineNum = 271;BA.debugLine="newListBtn.Enabled = True";
Debug.ShouldStop(16384);
todoactivity.mostCurrent._newlistbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 272;BA.debugLine="addTitleTextArea.Text = \"\"";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 273;BA.debugLine="Return";
Debug.ShouldStop(65536);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("existingTitle", _existingtitle);
;
 };
 BA.debugLineNum = 278;BA.debugLine="If kvs.ContainsKey(\"lists\") Then";
Debug.ShouldStop(2097152);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("lists"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 279;BA.debugLine="savedLists = kvs.Get(\"lists\")";
Debug.ShouldStop(4194304);
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists", _savedlists);
 };
 BA.debugLineNum = 282;BA.debugLine="savedLists.Add(title)";
Debug.ShouldStop(33554432);
_savedlists.runVoidMethod ("Add",(Object)((_title)));
 BA.debugLineNum = 283;BA.debugLine="kvs.Put(\"lists\", savedLists)";
Debug.ShouldStop(67108864);
todoactivity._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("lists")),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 285;BA.debugLine="listsList.AddTextItem(title, title)";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._listslist.runVoidMethod ("_addtextitem",(Object)((_title)),(Object)((_title)));
 BA.debugLineNum = 287;BA.debugLine="currentList = title";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._currentlist = _title;
 BA.debugLineNum = 288;BA.debugLine="addTitleTextArea.Text = currentList";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity.mostCurrent._currentlist));
 BA.debugLineNum = 289;BA.debugLine="addTitleTextArea.Visible = True";
Debug.ShouldStop(1);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 290;BA.debugLine="addTitleTextArea.Enabled = False";
Debug.ShouldStop(2);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 292;BA.debugLine="tasksList.Clear";
Debug.ShouldStop(8);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_clear");
 BA.debugLineNum = 293;BA.debugLine="tasksList.GetBase.Visible = True";
Debug.ShouldStop(16);
todoactivity.mostCurrent._taskslist.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 294;BA.debugLine="newAddTaskBtn";
Debug.ShouldStop(32);
_newaddtaskbtn();
 BA.debugLineNum = 296;BA.debugLine="newListBtn.Enabled = True";
Debug.ShouldStop(128);
todoactivity.mostCurrent._newlistbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 297;BA.debugLine="isAddingList = False";
Debug.ShouldStop(256);
todoactivity._isaddinglist = todoactivity.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 298;BA.debugLine="progressNumber.Text = \"0 / 0 tasks done!\"";
Debug.ShouldStop(512);
todoactivity.mostCurrent._progressnumber.runMethod(true,"setText",BA.ObjectToCharSequence("0 / 0 tasks done!"));
 BA.debugLineNum = 299;BA.debugLine="progressPercent.Text = \"0%\"";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._progresspercent.runMethod(true,"setText",BA.ObjectToCharSequence("0%"));
 BA.debugLineNum = 300;BA.debugLine="progressBar.Progress = 0";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._progressbar.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 BA.debugLineNum = 302;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _deletegroup(RemoteObject _code) throws Exception{
try {
		Debug.PushSubsStack("deleteGroup (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1027);
if (RapidSub.canDelegate("deletegroup")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","deletegroup", _code);}
RemoteObject _listskey = RemoteObject.createImmutable("");
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _listname = RemoteObject.createImmutable("");
RemoteObject _taskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject _allgroups = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _idx = RemoteObject.createImmutable(0);
Debug.locals.put("code", _code);
 BA.debugLineNum = 1027;BA.debugLine="Sub deleteGroup(code As String)";
Debug.ShouldStop(4);
 BA.debugLineNum = 1029;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
Debug.ShouldStop(16);
_listskey = RemoteObject.concat(RemoteObject.createImmutable("group_lists_"),_code);Debug.locals.put("listsKey", _listskey);Debug.locals.put("listsKey", _listskey);
 BA.debugLineNum = 1030;BA.debugLine="If kvs.ContainsKey(listsKey) Then";
Debug.ShouldStop(32);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_listskey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1031;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
Debug.ShouldStop(64);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_listskey)));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 1032;BA.debugLine="For Each listName As String In savedLists";
Debug.ShouldStop(128);
{
final RemoteObject group4 = _savedlists;
final int groupLen4 = group4.runMethod(true,"getSize").<Integer>get()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_listname = BA.ObjectToString(group4.runMethod(false,"Get",index4));Debug.locals.put("listName", _listname);
Debug.locals.put("listName", _listname);
 BA.debugLineNum = 1033;BA.debugLine="Dim taskKey As String = \"group_list_\" & code &";
Debug.ShouldStop(256);
_taskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_listname);Debug.locals.put("taskKey", _taskkey);Debug.locals.put("taskKey", _taskkey);
 BA.debugLineNum = 1034;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
Debug.ShouldStop(512);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_taskkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1035;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
Debug.ShouldStop(1024);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1036;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(2048);
{
final RemoteObject group8 = _savedtasks;
final int groupLen8 = group8.runMethod(true,"getSize").<Integer>get()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_task = BA.ObjectToString(group8.runMethod(false,"Get",index8));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 1037;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & li";
Debug.ShouldStop(4096);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_listname,RemoteObject.createImmutable("_"),_task)));
 }
}Debug.locals.put("task", _task);
;
 BA.debugLineNum = 1039;BA.debugLine="kvs.Remove(taskKey)";
Debug.ShouldStop(16384);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_taskkey));
 };
 }
}Debug.locals.put("listName", _listname);
;
 BA.debugLineNum = 1042;BA.debugLine="kvs.Remove(listsKey)";
Debug.ShouldStop(131072);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_listskey));
 };
 BA.debugLineNum = 1044;BA.debugLine="kvs.Remove(\"group_name_\" & code)";
Debug.ShouldStop(524288);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code)));
 BA.debugLineNum = 1045;BA.debugLine="kvs.Remove(\"group_owner_\" & code)";
Debug.ShouldStop(1048576);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_owner_"),_code)));
 BA.debugLineNum = 1046;BA.debugLine="kvs.Remove(\"group_members_\" & code)";
Debug.ShouldStop(2097152);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_members_"),_code)));
 BA.debugLineNum = 1049;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
Debug.ShouldStop(16777216);
_allgroups = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allgroups = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("groups"))));Debug.locals.put("allGroups", _allgroups);Debug.locals.put("allGroups", _allgroups);
 BA.debugLineNum = 1050;BA.debugLine="Dim idx As Int = allGroups.IndexOf(code)";
Debug.ShouldStop(33554432);
_idx = _allgroups.runMethod(true,"IndexOf",(Object)((_code)));Debug.locals.put("idx", _idx);Debug.locals.put("idx", _idx);
 BA.debugLineNum = 1051;BA.debugLine="If idx >= 0 Then allGroups.RemoveAt(idx)";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("g",_idx,BA.numberCast(double.class, 0))) { 
_allgroups.runVoidMethod ("RemoveAt",(Object)(_idx));};
 BA.debugLineNum = 1052;BA.debugLine="kvs.Put(\"groups\", allGroups)";
Debug.ShouldStop(134217728);
todoactivity._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("groups")),(Object)((_allgroups.getObject())));
 BA.debugLineNum = 1053;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _entertaskbtn_click() throws Exception{
try {
		Debug.PushSubsStack("enterTaskBtn_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,460);
if (RapidSub.canDelegate("entertaskbtn_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","entertaskbtn_click");}
RemoteObject _newtask = RemoteObject.createImmutable("");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _existingtask = RemoteObject.createImmutable("");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _oldtask = RemoteObject.createImmutable("");
RemoteObject _taskindex = RemoteObject.createImmutable(0);
RemoteObject _oldck = RemoteObject.createImmutable("");
RemoteObject _newck = RemoteObject.createImmutable("");
RemoteObject _savedtasks2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _t = RemoteObject.createImmutable("");
 BA.debugLineNum = 460;BA.debugLine="Sub enterTaskBtn_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 462;BA.debugLine="Dim newTask As String = addTaskTextArea.Text.Trim";
Debug.ShouldStop(8192);
_newtask = todoactivity.mostCurrent._addtasktextarea.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("newTask", _newtask);Debug.locals.put("newTask", _newtask);
 BA.debugLineNum = 463;BA.debugLine="If newTask = \"\" Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_newtask,BA.ObjectToString(""))) { 
 BA.debugLineNum = 464;BA.debugLine="MsgboxAsync(\"Please enter a task.\", \"No task ent";
Debug.ShouldStop(32768);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please enter a task.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("No task entered"))),todoactivity.processBA);
 BA.debugLineNum = 465;BA.debugLine="Return";
Debug.ShouldStop(65536);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 468;BA.debugLine="Dim key As String = \"list_\" & currentList";
Debug.ShouldStop(524288);
_key = RemoteObject.concat(RemoteObject.createImmutable("list_"),todoactivity.mostCurrent._currentlist);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 469;BA.debugLine="Dim savedTasks As List";
Debug.ShouldStop(1048576);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 470;BA.debugLine="savedTasks.Initialize";
Debug.ShouldStop(2097152);
_savedtasks.runVoidMethod ("Initialize");
 BA.debugLineNum = 472;BA.debugLine="If kvs.ContainsKey(key) Then";
Debug.ShouldStop(8388608);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_key)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 473;BA.debugLine="savedTasks = kvs.Get(key)";
Debug.ShouldStop(16777216);
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks", _savedtasks);
 };
 BA.debugLineNum = 476;BA.debugLine="For Each existingTask As String In savedTasks";
Debug.ShouldStop(134217728);
{
final RemoteObject group12 = _savedtasks;
final int groupLen12 = group12.runMethod(true,"getSize").<Integer>get()
;int index12 = 0;
;
for (; index12 < groupLen12;index12++){
_existingtask = BA.ObjectToString(group12.runMethod(false,"Get",index12));Debug.locals.put("existingTask", _existingtask);
Debug.locals.put("existingTask", _existingtask);
 BA.debugLineNum = 477;BA.debugLine="If existingTask = newTask Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",_existingtask,_newtask)) { 
 BA.debugLineNum = 478;BA.debugLine="MsgboxAsync(\"A task with that name already exis";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("A task with that name already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate task"))),todoactivity.processBA);
 BA.debugLineNum = 479;BA.debugLine="Return";
Debug.ShouldStop(1073741824);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("existingTask", _existingtask);
;
 BA.debugLineNum = 484;BA.debugLine="If addTaskTextArea.Tag <> Null Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("N",todoactivity.mostCurrent._addtasktextarea.runMethod(false,"getTag"))) { 
 BA.debugLineNum = 485;BA.debugLine="Dim ctx As List = addTaskTextArea.Tag";
Debug.ShouldStop(16);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_ctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity.mostCurrent._addtasktextarea.runMethod(false,"getTag"));Debug.locals.put("ctx", _ctx);Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 486;BA.debugLine="Dim oldTask As String = ctx.Get(1)";
Debug.ShouldStop(32);
_oldtask = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("oldTask", _oldtask);Debug.locals.put("oldTask", _oldtask);
 BA.debugLineNum = 488;BA.debugLine="Dim taskIndex As Int = savedTasks.IndexOf(oldTas";
Debug.ShouldStop(128);
_taskindex = _savedtasks.runMethod(true,"IndexOf",(Object)((_oldtask)));Debug.locals.put("taskIndex", _taskindex);Debug.locals.put("taskIndex", _taskindex);
 BA.debugLineNum = 489;BA.debugLine="If taskIndex >= 0 Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("g",_taskindex,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 490;BA.debugLine="savedTasks.Set(taskIndex, newTask)";
Debug.ShouldStop(512);
_savedtasks.runVoidMethod ("Set",(Object)(_taskindex),(Object)((_newtask)));
 BA.debugLineNum = 491;BA.debugLine="kvs.Put(key, savedTasks)";
Debug.ShouldStop(1024);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_key),(Object)((_savedtasks.getObject())));
 };
 BA.debugLineNum = 495;BA.debugLine="Dim oldCK As String = \"checked_\" & currentList &";
Debug.ShouldStop(16384);
_oldck = RemoteObject.concat(RemoteObject.createImmutable("checked_"),todoactivity.mostCurrent._currentlist,RemoteObject.createImmutable("_"),_oldtask);Debug.locals.put("oldCK", _oldck);Debug.locals.put("oldCK", _oldck);
 BA.debugLineNum = 496;BA.debugLine="Dim newCK As String = \"checked_\" & currentList &";
Debug.ShouldStop(32768);
_newck = RemoteObject.concat(RemoteObject.createImmutable("checked_"),todoactivity.mostCurrent._currentlist,RemoteObject.createImmutable("_"),_newtask);Debug.locals.put("newCK", _newck);Debug.locals.put("newCK", _newck);
 BA.debugLineNum = 497;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
Debug.ShouldStop(65536);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_oldck)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 498;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
Debug.ShouldStop(131072);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_newck),(Object)(todoactivity._kvs.runMethod(false,"_get",(Object)(_oldck))));
 BA.debugLineNum = 499;BA.debugLine="kvs.Remove(oldCK)";
Debug.ShouldStop(262144);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_oldck));
 };
 BA.debugLineNum = 502;BA.debugLine="addTaskTextArea.Tag = Null";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent._addtasktextarea.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 505;BA.debugLine="tasksList.Clear";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_clear");
 BA.debugLineNum = 506;BA.debugLine="Dim savedTasks2 As List = kvs.Get(key)";
Debug.ShouldStop(33554432);
_savedtasks2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks2 = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks2", _savedtasks2);Debug.locals.put("savedTasks2", _savedtasks2);
 BA.debugLineNum = 507;BA.debugLine="For Each t As String In savedTasks2";
Debug.ShouldStop(67108864);
{
final RemoteObject group35 = _savedtasks2;
final int groupLen35 = group35.runMethod(true,"getSize").<Integer>get()
;int index35 = 0;
;
for (; index35 < groupLen35;index35++){
_t = BA.ObjectToString(group35.runMethod(false,"Get",index35));Debug.locals.put("t", _t);
Debug.locals.put("t", _t);
 BA.debugLineNum = 508;BA.debugLine="tasksListUI(t)";
Debug.ShouldStop(134217728);
_taskslistui(_t);
 }
}Debug.locals.put("t", _t);
;
 BA.debugLineNum = 510;BA.debugLine="newAddTaskBtn";
Debug.ShouldStop(536870912);
_newaddtaskbtn();
 BA.debugLineNum = 511;BA.debugLine="addTaskBtn.Enabled = True";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 512;BA.debugLine="updateProgress";
Debug.ShouldStop(-2147483648);
_updateprogress();
 BA.debugLineNum = 513;BA.debugLine="ToastMessageShow(\"Task renamed\", False)";
Debug.ShouldStop(1);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Task renamed")),(Object)(todoactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 514;BA.debugLine="Return";
Debug.ShouldStop(2);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 518;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1)";
Debug.ShouldStop(32);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._taskslist.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 520;BA.debugLine="Dim key As String = \"list_\" & currentList";
Debug.ShouldStop(128);
_key = RemoteObject.concat(RemoteObject.createImmutable("list_"),todoactivity.mostCurrent._currentlist);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 521;BA.debugLine="Dim savedTasks As List";
Debug.ShouldStop(256);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 522;BA.debugLine="If kvs.ContainsKey(key) Then";
Debug.ShouldStop(512);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_key)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 523;BA.debugLine="savedTasks = kvs.Get(key)";
Debug.ShouldStop(1024);
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks", _savedtasks);
 }else {
 BA.debugLineNum = 525;BA.debugLine="savedTasks.Initialize";
Debug.ShouldStop(4096);
_savedtasks.runVoidMethod ("Initialize");
 };
 BA.debugLineNum = 528;BA.debugLine="savedTasks.Add(newTask)";
Debug.ShouldStop(32768);
_savedtasks.runVoidMethod ("Add",(Object)((_newtask)));
 BA.debugLineNum = 529;BA.debugLine="kvs.Put(key, savedTasks)";
Debug.ShouldStop(65536);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_key),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 531;BA.debugLine="tasksListUI(newTask)";
Debug.ShouldStop(262144);
_taskslistui(_newtask);
 BA.debugLineNum = 532;BA.debugLine="newAddTaskBtn";
Debug.ShouldStop(524288);
_newaddtaskbtn();
 BA.debugLineNum = 533;BA.debugLine="addTaskBtn.Enabled = True";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 534;BA.debugLine="updateProgress";
Debug.ShouldStop(2097152);
_updateprogress();
 BA.debugLineNum = 536;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _entertaskbtngrp_click() throws Exception{
try {
		Debug.PushSubsStack("enterTaskBtnGrp_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1383);
if (RapidSub.canDelegate("entertaskbtngrp_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","entertaskbtngrp_click");}
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _currentgrplist = RemoteObject.createImmutable("");
RemoteObject _addet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _oldtask = RemoteObject.declareNull("Object");
RemoteObject _newtask = RemoteObject.createImmutable("");
RemoteObject _taskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _existing = RemoteObject.createImmutable("");
RemoteObject _taskindex = RemoteObject.createImmutable(0);
RemoteObject _oldck = RemoteObject.createImmutable("");
RemoteObject _newck = RemoteObject.createImmutable("");
RemoteObject _savedtasks2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _t = RemoteObject.createImmutable("");
 BA.debugLineNum = 1383;BA.debugLine="Sub enterTaskBtnGrp_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 1384;BA.debugLine="Dim btn As Button = Sender";
Debug.ShouldStop(128);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 1385;BA.debugLine="Dim ctx As List = btn.Tag";
Debug.ShouldStop(256);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_ctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _btn.runMethod(false,"getTag"));Debug.locals.put("ctx", _ctx);Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1386;BA.debugLine="Dim code As String = ctx.Get(0)";
Debug.ShouldStop(512);
_code = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1387;BA.debugLine="Dim currentGrpList As String = ctx.Get(1)";
Debug.ShouldStop(1024);
_currentgrplist = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("currentGrpList", _currentgrplist);Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1388;BA.debugLine="Dim addET As EditText = ctx.Get(2)";
Debug.ShouldStop(2048);
_addet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
_addet = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.EditTextWrapper"), _ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 2))));Debug.locals.put("addET", _addet);Debug.locals.put("addET", _addet);
 BA.debugLineNum = 1389;BA.debugLine="Dim oldTask As Object = ctx.Get(3)";
Debug.ShouldStop(4096);
_oldtask = _ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 3)));Debug.locals.put("oldTask", _oldtask);Debug.locals.put("oldTask", _oldtask);
 BA.debugLineNum = 1391;BA.debugLine="Dim newTask As String = addET.Text.Trim";
Debug.ShouldStop(16384);
_newtask = _addet.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("newTask", _newtask);Debug.locals.put("newTask", _newtask);
 BA.debugLineNum = 1392;BA.debugLine="If newTask = \"\" Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",_newtask,BA.ObjectToString(""))) { 
 BA.debugLineNum = 1393;BA.debugLine="MsgboxAsync(\"Please enter a task.\", \"No task ent";
Debug.ShouldStop(65536);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please enter a task.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("No task entered"))),todoactivity.processBA);
 BA.debugLineNum = 1394;BA.debugLine="Return";
Debug.ShouldStop(131072);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1397;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
Debug.ShouldStop(1048576);
_taskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_currentgrplist);Debug.locals.put("taskKey", _taskkey);Debug.locals.put("taskKey", _taskkey);
 BA.debugLineNum = 1398;BA.debugLine="Dim savedTasks As List";
Debug.ShouldStop(2097152);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1399;BA.debugLine="savedTasks.Initialize";
Debug.ShouldStop(4194304);
_savedtasks.runVoidMethod ("Initialize");
 BA.debugLineNum = 1400;BA.debugLine="If kvs.ContainsKey(taskKey) Then savedTasks = kvs";
Debug.ShouldStop(8388608);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_taskkey)).<Boolean>get().booleanValue()) { 
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks", _savedtasks);};
 BA.debugLineNum = 1403;BA.debugLine="For Each existing As String In savedTasks";
Debug.ShouldStop(67108864);
{
final RemoteObject group16 = _savedtasks;
final int groupLen16 = group16.runMethod(true,"getSize").<Integer>get()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_existing = BA.ObjectToString(group16.runMethod(false,"Get",index16));Debug.locals.put("existing", _existing);
Debug.locals.put("existing", _existing);
 BA.debugLineNum = 1404;BA.debugLine="If existing = newTask Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_existing,_newtask)) { 
 BA.debugLineNum = 1405;BA.debugLine="MsgboxAsync(\"Task already exists.\", \"Duplicate\"";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Task already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate"))),todoactivity.processBA);
 BA.debugLineNum = 1406;BA.debugLine="Return";
Debug.ShouldStop(536870912);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("existing", _existing);
;
 BA.debugLineNum = 1410;BA.debugLine="If oldTask <> Null Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("N",_oldtask)) { 
 BA.debugLineNum = 1412;BA.debugLine="Dim taskIndex As Int = savedTasks.IndexOf(oldTas";
Debug.ShouldStop(8);
_taskindex = _savedtasks.runMethod(true,"IndexOf",(Object)(_oldtask));Debug.locals.put("taskIndex", _taskindex);Debug.locals.put("taskIndex", _taskindex);
 BA.debugLineNum = 1413;BA.debugLine="If taskIndex >= 0 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("g",_taskindex,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 1414;BA.debugLine="savedTasks.Set(taskIndex, newTask)";
Debug.ShouldStop(32);
_savedtasks.runVoidMethod ("Set",(Object)(_taskindex),(Object)((_newtask)));
 BA.debugLineNum = 1415;BA.debugLine="kvs.Put(taskKey, savedTasks)";
Debug.ShouldStop(64);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_taskkey),(Object)((_savedtasks.getObject())));
 };
 BA.debugLineNum = 1418;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code &";
Debug.ShouldStop(512);
_oldck = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_currentgrplist,RemoteObject.createImmutable("_"),_oldtask);Debug.locals.put("oldCK", _oldck);Debug.locals.put("oldCK", _oldck);
 BA.debugLineNum = 1419;BA.debugLine="Dim newCK As String = \"group_checked_\" & code &";
Debug.ShouldStop(1024);
_newck = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_currentgrplist,RemoteObject.createImmutable("_"),_newtask);Debug.locals.put("newCK", _newck);Debug.locals.put("newCK", _newck);
 BA.debugLineNum = 1420;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
Debug.ShouldStop(2048);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_oldck)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1421;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
Debug.ShouldStop(4096);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_newck),(Object)(todoactivity._kvs.runMethod(false,"_get",(Object)(_oldck))));
 BA.debugLineNum = 1422;BA.debugLine="kvs.Remove(oldCK)";
Debug.ShouldStop(8192);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_oldck));
 };
 BA.debugLineNum = 1426;BA.debugLine="tasksListGrp.Clear";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 1427;BA.debugLine="Dim savedTasks2 As List = kvs.Get(taskKey)";
Debug.ShouldStop(262144);
_savedtasks2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks2 = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks2", _savedtasks2);Debug.locals.put("savedTasks2", _savedtasks2);
 BA.debugLineNum = 1428;BA.debugLine="For Each t As String In savedTasks2";
Debug.ShouldStop(524288);
{
final RemoteObject group36 = _savedtasks2;
final int groupLen36 = group36.runMethod(true,"getSize").<Integer>get()
;int index36 = 0;
;
for (; index36 < groupLen36;index36++){
_t = BA.ObjectToString(group36.runMethod(false,"Get",index36));Debug.locals.put("t", _t);
Debug.locals.put("t", _t);
 BA.debugLineNum = 1429;BA.debugLine="tasksListGrpUI(t, code, currentGrpList)";
Debug.ShouldStop(1048576);
_taskslistgrpui(_t,_code,_currentgrplist);
 }
}Debug.locals.put("t", _t);
;
 BA.debugLineNum = 1431;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
Debug.ShouldStop(4194304);
_newaddtaskbtngrp(_code,_currentgrplist);
 BA.debugLineNum = 1432;BA.debugLine="updateProgressGrp(code, currentGrpList)";
Debug.ShouldStop(8388608);
_updateprogressgrp(_code,_currentgrplist);
 BA.debugLineNum = 1433;BA.debugLine="ToastMessageShow(\"Task renamed\", False)";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Task renamed")),(Object)(todoactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 1434;BA.debugLine="Return";
Debug.ShouldStop(33554432);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1438;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._taskslistgrp.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 1439;BA.debugLine="savedTasks.Add(newTask)";
Debug.ShouldStop(1073741824);
_savedtasks.runVoidMethod ("Add",(Object)((_newtask)));
 BA.debugLineNum = 1440;BA.debugLine="kvs.Put(taskKey, savedTasks)";
Debug.ShouldStop(-2147483648);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_taskkey),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 1441;BA.debugLine="tasksListGrpUI(newTask, code, currentGrpList)";
Debug.ShouldStop(1);
_taskslistgrpui(_newtask,_code,_currentgrplist);
 BA.debugLineNum = 1442;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
Debug.ShouldStop(2);
_newaddtaskbtngrp(_code,_currentgrplist);
 BA.debugLineNum = 1443;BA.debugLine="updateProgressGrp(code, currentGrpList)";
Debug.ShouldStop(4);
_updateprogressgrp(_code,_currentgrplist);
 BA.debugLineNum = 1444;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _generategroupcode(RemoteObject _length) throws Exception{
try {
		Debug.PushSubsStack("generateGroupCode (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,857);
if (RapidSub.canDelegate("generategroupcode")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","generategroupcode", _length);}
RemoteObject _chars = RemoteObject.createImmutable("");
RemoteObject _code = RemoteObject.createImmutable("");
int _i = 0;
Debug.locals.put("length", _length);
 BA.debugLineNum = 857;BA.debugLine="Sub generateGroupCode (length As Int) As String";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 858;BA.debugLine="Dim chars As String = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
Debug.ShouldStop(33554432);
_chars = BA.ObjectToString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");Debug.locals.put("chars", _chars);Debug.locals.put("chars", _chars);
 BA.debugLineNum = 859;BA.debugLine="Dim code As String = \"\"";
Debug.ShouldStop(67108864);
_code = BA.ObjectToString("");Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 860;BA.debugLine="For i = 0 To length - 1";
Debug.ShouldStop(134217728);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {_length,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 861;BA.debugLine="code = code & (chars.CharAt(Rnd(0,chars.Length))";
Debug.ShouldStop(268435456);
_code = RemoteObject.concat(_code,(_chars.runMethod(true,"charAt",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 0)),(Object)(_chars.runMethod(true,"length")))))));Debug.locals.put("code", _code);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 863;BA.debugLine="Return code";
Debug.ShouldStop(1073741824);
if (true) return _code;
 BA.debugLineNum = 864;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getcurrentgrplist() throws Exception{
try {
		Debug.PushSubsStack("getCurrentGrpList (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1606);
if (RapidSub.canDelegate("getcurrentgrplist")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","getcurrentgrplist");}
 BA.debugLineNum = 1606;BA.debugLine="Sub getCurrentGrpList As String";
Debug.ShouldStop(32);
 BA.debugLineNum = 1607;BA.debugLine="Return currentGrpListName   ' module-level var yo";
Debug.ShouldStop(64);
if (true) return todoactivity.mostCurrent._currentgrplistname;
 BA.debugLineNum = 1608;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Private addTitleTextArea As EditText";
todoactivity.mostCurrent._addtitletextarea = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private listsList As CustomListView";
todoactivity.mostCurrent._listslist = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 16;BA.debugLine="Private newListBtn As Button";
todoactivity.mostCurrent._newlistbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private tasksList As CustomListView";
todoactivity.mostCurrent._taskslist = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 18;BA.debugLine="Dim isAddingList As Boolean = False";
todoactivity._isaddinglist = todoactivity.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 20;BA.debugLine="Dim addTaskBtnPNL As Panel";
todoactivity.mostCurrent._addtaskbtnpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Dim addTaskBtn As Button";
todoactivity.mostCurrent._addtaskbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private currentList As String = \"\"";
todoactivity.mostCurrent._currentlist = BA.ObjectToString("");
 //BA.debugLineNum = 25;BA.debugLine="Dim addTaskPanel As Panel";
todoactivity.mostCurrent._addtaskpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Dim addTaskTextArea As EditText";
todoactivity.mostCurrent._addtasktextarea = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Dim enterTaskBtn As Button";
todoactivity.mostCurrent._entertaskbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Dim untitledNo As Int = 1";
todoactivity._untitledno = BA.numberCast(int.class, 1);
 //BA.debugLineNum = 31;BA.debugLine="Private progressNumber As Label";
todoactivity.mostCurrent._progressnumber = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private progressPercent As Label";
todoactivity.mostCurrent._progresspercent = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private progressBar As ProgressBar";
todoactivity.mostCurrent._progressbar = RemoteObject.createNew ("anywheresoftware.b4a.objects.ProgressBarWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Dim pixeltf As Typeface";
todoactivity.mostCurrent._pixeltf = RemoteObject.createNew ("anywheresoftware.b4a.keywords.constants.TypefaceWrapper");
 //BA.debugLineNum = 36;BA.debugLine="pixeltf = Typeface.LoadFromAssets(\"minecraft.ttf\"";
todoactivity.mostCurrent._pixeltf = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.constants.TypefaceWrapper"), todoactivity.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"LoadFromAssets",(Object)(RemoteObject.createImmutable("minecraft.ttf"))));
 //BA.debugLineNum = 39;BA.debugLine="Private TabHost1 As TabHost";
todoactivity.mostCurrent._tabhost1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.TabHostWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private groupList As CustomListView";
todoactivity.mostCurrent._grouplist = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 43;BA.debugLine="Private addTitleTextAreaGrp As EditText";
todoactivity.mostCurrent._addtitletextareagrp = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private listsListGrp As CustomListView";
todoactivity.mostCurrent._listslistgrp = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 45;BA.debugLine="Private newGroupBtn As Button";
todoactivity.mostCurrent._newgroupbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 46;BA.debugLine="Private newListBtnGrp As Button";
todoactivity.mostCurrent._newlistbtngrp = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private progressBarGrp As ProgressBar";
todoactivity.mostCurrent._progressbargrp = RemoteObject.createNew ("anywheresoftware.b4a.objects.ProgressBarWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private progressNumberGrp As Label";
todoactivity.mostCurrent._progressnumbergrp = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private progressPercentGrp As Label";
todoactivity.mostCurrent._progresspercentgrp = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private tasksListGrp As CustomListView";
todoactivity.mostCurrent._taskslistgrp = RemoteObject.createNew ("b4a.example3.customlistview");
 //BA.debugLineNum = 51;BA.debugLine="Private groupET As EditText";
todoactivity.mostCurrent._groupet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private currentGrpListName As String = \"\"";
todoactivity.mostCurrent._currentgrplistname = BA.ObjectToString("");
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _groupet_enterpressed() throws Exception{
try {
		Debug.PushSubsStack("groupET_EnterPressed (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,805);
if (RapidSub.canDelegate("groupet_enterpressed")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","groupet_enterpressed");}
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _newname = RemoteObject.createImmutable("");
RemoteObject _groupname = RemoteObject.createImmutable("");
RemoteObject _allgroups = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _members = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 805;BA.debugLine="Sub groupET_EnterPressed";
Debug.ShouldStop(16);
 BA.debugLineNum = 806;BA.debugLine="If groupET.Tag Is List Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("i",todoactivity.mostCurrent._groupet.runMethod(false,"getTag"), RemoteObject.createImmutable("java.util.List"))) { 
 BA.debugLineNum = 808;BA.debugLine="Dim ctx As List = groupET.Tag";
Debug.ShouldStop(128);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_ctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity.mostCurrent._groupet.runMethod(false,"getTag"));Debug.locals.put("ctx", _ctx);Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 809;BA.debugLine="Dim code As String = ctx.Get(1)";
Debug.ShouldStop(256);
_code = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 810;BA.debugLine="Dim newName As String = groupET.Text.Trim";
Debug.ShouldStop(512);
_newname = todoactivity.mostCurrent._groupet.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("newName", _newname);Debug.locals.put("newName", _newname);
 BA.debugLineNum = 811;BA.debugLine="If newName = \"\" Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",_newname,BA.ObjectToString(""))) { 
 BA.debugLineNum = 812;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._groupet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code)))));
 BA.debugLineNum = 813;BA.debugLine="groupET.Enabled = False";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 814;BA.debugLine="groupET.Tag = code";
Debug.ShouldStop(8192);
todoactivity.mostCurrent._groupet.runMethod(false,"setTag",(_code));
 BA.debugLineNum = 815;BA.debugLine="Return";
Debug.ShouldStop(16384);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 817;BA.debugLine="kvs.Put(\"group_name_\" & code, newName)";
Debug.ShouldStop(65536);
todoactivity._kvs.runVoidMethod ("_put",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code)),(Object)((_newname)));
 BA.debugLineNum = 818;BA.debugLine="groupET.Enabled = False";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 819;BA.debugLine="groupET.Tag = code";
Debug.ShouldStop(262144);
todoactivity.mostCurrent._groupet.runMethod(false,"setTag",(_code));
 BA.debugLineNum = 820;BA.debugLine="loadMyGroups";
Debug.ShouldStop(524288);
_loadmygroups();
 BA.debugLineNum = 821;BA.debugLine="groupET.Text = newName";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._groupet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_newname));
 BA.debugLineNum = 822;BA.debugLine="ToastMessageShow(\"Group renamed\", False)";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Group renamed")),(Object)(todoactivity.mostCurrent.__c.getField(true,"False")));
 }else 
{ BA.debugLineNum = 824;BA.debugLine="Else If groupET.Tag = \"creating\" Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._groupet.runMethod(false,"getTag"),RemoteObject.createImmutable(("creating")))) { 
 BA.debugLineNum = 826;BA.debugLine="Dim groupName As String = groupET.Text.Trim";
Debug.ShouldStop(33554432);
_groupname = todoactivity.mostCurrent._groupet.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("groupName", _groupname);Debug.locals.put("groupName", _groupname);
 BA.debugLineNum = 827;BA.debugLine="If groupName = \"\" Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",_groupname,BA.ObjectToString(""))) { 
 BA.debugLineNum = 828;BA.debugLine="MsgboxAsync(\"Please enter a group name.\", \"No n";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please enter a group name.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("No name"))),todoactivity.processBA);
 BA.debugLineNum = 829;BA.debugLine="Return";
Debug.ShouldStop(268435456);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 832;BA.debugLine="Dim code As String = generateGroupCode(6)";
Debug.ShouldStop(-2147483648);
_code = _generategroupcode(BA.numberCast(int.class, 6));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 834;BA.debugLine="Dim allGroups As List";
Debug.ShouldStop(2);
_allgroups = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("allGroups", _allgroups);
 BA.debugLineNum = 835;BA.debugLine="allGroups.Initialize";
Debug.ShouldStop(4);
_allgroups.runVoidMethod ("Initialize");
 BA.debugLineNum = 836;BA.debugLine="If kvs.ContainsKey(\"groups\") Then allGroups = kv";
Debug.ShouldStop(8);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("groups"))).<Boolean>get().booleanValue()) { 
_allgroups = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("groups"))));Debug.locals.put("allGroups", _allgroups);};
 BA.debugLineNum = 837;BA.debugLine="allGroups.Add(code)";
Debug.ShouldStop(16);
_allgroups.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 838;BA.debugLine="kvs.Put(\"groups\", allGroups)";
Debug.ShouldStop(32);
todoactivity._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("groups")),(Object)((_allgroups.getObject())));
 BA.debugLineNum = 840;BA.debugLine="kvs.Put(\"group_name_\" & code, groupName)";
Debug.ShouldStop(128);
todoactivity._kvs.runVoidMethod ("_put",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code)),(Object)((_groupname)));
 BA.debugLineNum = 841;BA.debugLine="kvs.Put(\"group_owner_\" & code, Starter.currentUs";
Debug.ShouldStop(256);
todoactivity._kvs.runVoidMethod ("_put",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_owner_"),_code)),(Object)((todoactivity.mostCurrent._starter._currentuserid /*RemoteObject*/ )));
 BA.debugLineNum = 843;BA.debugLine="Dim members As List";
Debug.ShouldStop(1024);
_members = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("members", _members);
 BA.debugLineNum = 844;BA.debugLine="members.Initialize";
Debug.ShouldStop(2048);
_members.runVoidMethod ("Initialize");
 BA.debugLineNum = 845;BA.debugLine="members.Add(Starter.currentUserID)";
Debug.ShouldStop(4096);
_members.runVoidMethod ("Add",(Object)((todoactivity.mostCurrent._starter._currentuserid /*RemoteObject*/ )));
 BA.debugLineNum = 846;BA.debugLine="kvs.Put(\"group_members_\" & code, members)";
Debug.ShouldStop(8192);
todoactivity._kvs.runVoidMethod ("_put",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_members_"),_code)),(Object)((_members.getObject())));
 BA.debugLineNum = 848;BA.debugLine="groupET.Tag = Null";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._groupet.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 849;BA.debugLine="groupET.Enabled = False";
Debug.ShouldStop(65536);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 851;BA.debugLine="ToastMessageShow(\"Group \"\"\" & groupName & \"\"\" cr";
Debug.ShouldStop(262144);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Group \""),_groupname,RemoteObject.createImmutable("\" created! Code: "),_code))),(Object)(todoactivity.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 852;BA.debugLine="loadMyGroups";
Debug.ShouldStop(524288);
_loadmygroups();
 }}
;
 BA.debugLineNum = 854;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _grouplist_itemclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("groupList_ItemClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,945);
if (RapidSub.canDelegate("grouplist_itemclick")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","grouplist_itemclick", _index, _value);}
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _listskey = RemoteObject.createImmutable("");
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _listname = RemoteObject.createImmutable("");
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 945;BA.debugLine="Sub groupList_ItemClick(Index As Int, Value As Obj";
Debug.ShouldStop(65536);
 BA.debugLineNum = 946;BA.debugLine="Dim code As String = Value";
Debug.ShouldStop(131072);
_code = BA.ObjectToString(_value);Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 947;BA.debugLine="If code = \"joinPanel\" Then Return";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_code,BA.ObjectToString("joinPanel"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 950;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent._groupet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code)))));
 BA.debugLineNum = 951;BA.debugLine="groupET.Enabled = False";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 952;BA.debugLine="groupET.Tag = code   ' store current group code i";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._groupet.runMethod(false,"setTag",(_code));
 BA.debugLineNum = 955;BA.debugLine="listsListGrp.Clear";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 956;BA.debugLine="listsListGrp.GetBase.Visible = True";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent._listslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 957;BA.debugLine="tasksListGrp.Clear";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 958;BA.debugLine="tasksListGrp.GetBase.Visible = False";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._taskslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 960;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
Debug.ShouldStop(-2147483648);
_listskey = RemoteObject.concat(RemoteObject.createImmutable("group_lists_"),_code);Debug.locals.put("listsKey", _listskey);Debug.locals.put("listsKey", _listskey);
 BA.debugLineNum = 961;BA.debugLine="If kvs.ContainsKey(listsKey) Then";
Debug.ShouldStop(1);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_listskey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 962;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
Debug.ShouldStop(2);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_listskey)));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 963;BA.debugLine="For Each listName As String In savedLists";
Debug.ShouldStop(4);
{
final RemoteObject group13 = _savedlists;
final int groupLen13 = group13.runMethod(true,"getSize").<Integer>get()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_listname = BA.ObjectToString(group13.runMethod(false,"Get",index13));Debug.locals.put("listName", _listname);
Debug.locals.put("listName", _listname);
 BA.debugLineNum = 964;BA.debugLine="listsListGrp.AddTextItem(listName, listName)";
Debug.ShouldStop(8);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_addtextitem",(Object)((_listname)),(Object)((_listname)));
 }
}Debug.locals.put("listName", _listname);
;
 };
 BA.debugLineNum = 968;BA.debugLine="progressBarGrp.Progress = 0";
Debug.ShouldStop(128);
todoactivity.mostCurrent._progressbargrp.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 BA.debugLineNum = 969;BA.debugLine="progressNumberGrp.Text = \"\"";
Debug.ShouldStop(256);
todoactivity.mostCurrent._progressnumbergrp.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 970;BA.debugLine="progressPercentGrp.Text = \"\"";
Debug.ShouldStop(512);
todoactivity.mostCurrent._progresspercentgrp.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 971;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _grouplist_itemlongclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("groupList_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,973);
if (RapidSub.canDelegate("grouplist_itemlongclick")) { b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","grouplist_itemlongclick", _index, _value); return;}
ResumableSub_groupList_ItemLongClick rsub = new ResumableSub_groupList_ItemLongClick(null,_index,_value);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_groupList_ItemLongClick extends BA.ResumableSub {
public ResumableSub_groupList_ItemLongClick(b4a.example.todoactivity parent,RemoteObject _index,RemoteObject _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.todoactivity parent;
RemoteObject _index;
RemoteObject _value;
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _clipmanager = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _clipdata = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _ownerkey = RemoteObject.createImmutable("");
RemoteObject _res2 = RemoteObject.createImmutable(0);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("groupList_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,973);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 974;BA.debugLine="If Value = \"joinPanel\" Then Return";
Debug.ShouldStop(8192);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_value,RemoteObject.createImmutable(("joinPanel")))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
 BA.debugLineNum = 976;BA.debugLine="Dim code As String = Value";
Debug.ShouldStop(32768);
_code = BA.ObjectToString(_value);Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 977;BA.debugLine="Msgbox2Async(\"What do you want to do with this gr";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("What do you want to do with this group?")),(Object)(BA.ObjectToCharSequence(parent._kvs.runMethod(false,"_get",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code))))),(Object)(BA.ObjectToString("Rename")),(Object)(BA.ObjectToString("Copy Invite Code")),(Object)(BA.ObjectToString("Delete")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 978;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(131072);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "grouplist_itemlongclick"), null);
this.state = 27;
return;
case 27:
//C
this.state = 7;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 980;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(524288);
if (true) break;

case 7:
//if
this.state = 26;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 9;
}else 
{ BA.debugLineNum = 984;BA.debugLine="Else If res = DialogResponse.CANCEL Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"CANCEL")))) { 
this.state = 11;
}else 
{ BA.debugLineNum = 994;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 13;
}}}
if (true) break;

case 9:
//C
this.state = 26;
 BA.debugLineNum = 982;BA.debugLine="showRenameGroupPanel(Index, code)";
Debug.ShouldStop(2097152);
_showrenamegrouppanel(_index,_code);
 if (true) break;

case 11:
//C
this.state = 26;
 BA.debugLineNum = 986;BA.debugLine="Dim jo As JavaObject";
Debug.ShouldStop(33554432);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("jo", _jo);
 BA.debugLineNum = 987;BA.debugLine="jo.InitializeContext";
Debug.ShouldStop(67108864);
_jo.runVoidMethod ("InitializeContext",todoactivity.processBA);
 BA.debugLineNum = 988;BA.debugLine="Dim clipManager As JavaObject = jo.RunMethod(\"ge";
Debug.ShouldStop(134217728);
_clipmanager = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_clipmanager = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), _jo.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getSystemService")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(RemoteObject.createImmutable("clipboard"))}))));Debug.locals.put("clipManager", _clipmanager);Debug.locals.put("clipManager", _clipmanager);
 BA.debugLineNum = 989;BA.debugLine="Dim clipData As JavaObject";
Debug.ShouldStop(268435456);
_clipdata = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("clipData", _clipdata);
 BA.debugLineNum = 990;BA.debugLine="clipData.InitializeStatic(\"android.content.ClipD";
Debug.ShouldStop(536870912);
_clipdata.runVoidMethod ("InitializeStatic",(Object)(RemoteObject.createImmutable("android.content.ClipData")));
 BA.debugLineNum = 991;BA.debugLine="clipManager.RunMethod(\"setPrimaryClip\", Array(cl";
Debug.ShouldStop(1073741824);
_clipmanager.runVoidMethod ("RunMethod",(Object)(BA.ObjectToString("setPrimaryClip")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {_clipdata.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("newPlainText")),(Object)(RemoteObject.createNewArray("Object",new int[] {2},new Object[] {RemoteObject.createImmutable(("Group Code")),(_code)})))})));
 BA.debugLineNum = 992;BA.debugLine="ToastMessageShow(\"Invite code copied: \" & code,";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Invite code copied: "),_code))),(Object)(parent.mostCurrent.__c.getField(true,"True")));
 if (true) break;

case 13:
//C
this.state = 14;
 BA.debugLineNum = 996;BA.debugLine="Dim ownerKey As String = \"group_owner_\" & code";
Debug.ShouldStop(8);
_ownerkey = RemoteObject.concat(RemoteObject.createImmutable("group_owner_"),_code);Debug.locals.put("ownerKey", _ownerkey);Debug.locals.put("ownerKey", _ownerkey);
 BA.debugLineNum = 997;BA.debugLine="If kvs.ContainsKey(ownerKey) Then";
Debug.ShouldStop(16);
if (true) break;

case 14:
//if
this.state = 21;
if (parent._kvs.runMethod(true,"_containskey",(Object)(_ownerkey)).<Boolean>get().booleanValue()) { 
this.state = 16;
}if (true) break;

case 16:
//C
this.state = 17;
 BA.debugLineNum = 998;BA.debugLine="If kvs.Get(ownerKey) <> Starter.currentUserID T";
Debug.ShouldStop(32);
if (true) break;

case 17:
//if
this.state = 20;
if (RemoteObject.solveBoolean("!",parent._kvs.runMethod(false,"_get",(Object)(_ownerkey)),(parent.mostCurrent._starter._currentuserid /*RemoteObject*/ ))) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
 BA.debugLineNum = 999;BA.debugLine="MsgboxAsync(\"Only the group creator can delete";
Debug.ShouldStop(64);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Only the group creator can delete the group.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Not allowed"))),todoactivity.processBA);
 BA.debugLineNum = 1000;BA.debugLine="Return";
Debug.ShouldStop(128);
if (true) return ;
 if (true) break;

case 20:
//C
this.state = 21;
;
 if (true) break;

case 21:
//C
this.state = 22;
;
 BA.debugLineNum = 1004;BA.debugLine="Msgbox2Async(\"Delete this group and all its data";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Delete this group and all its data?")),(Object)(BA.ObjectToCharSequence("Confirm")),(Object)(BA.ObjectToString("No")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Yes")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 1005;BA.debugLine="Wait For Msgbox_Result (res2 As Int)";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "grouplist_itemlongclick"), null);
this.state = 28;
return;
case 28:
//C
this.state = 22;
_res2 = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res2", _res2);
;
 BA.debugLineNum = 1006;BA.debugLine="If res2 = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(8192);
if (true) break;

case 22:
//if
this.state = 25;
if (RemoteObject.solveBoolean("=",_res2,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 24;
}if (true) break;

case 24:
//C
this.state = 25;
 BA.debugLineNum = 1007;BA.debugLine="deleteGroup(code)";
Debug.ShouldStop(16384);
_deletegroup(_code);
 BA.debugLineNum = 1008;BA.debugLine="loadMyGroups";
Debug.ShouldStop(32768);
_loadmygroups();
 if (true) break;

case 25:
//C
this.state = 26;
;
 if (true) break;

case 26:
//C
this.state = -1;
;
 BA.debugLineNum = 1011;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _msgbox_result(RemoteObject _res) throws Exception{
}
public static RemoteObject  _iscodeunique(RemoteObject _code) throws Exception{
try {
		Debug.PushSubsStack("isCodeUnique (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,866);
if (RapidSub.canDelegate("iscodeunique")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","iscodeunique", _code);}
RemoteObject _allgroups = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _c = RemoteObject.createImmutable("");
Debug.locals.put("code", _code);
 BA.debugLineNum = 866;BA.debugLine="Sub isCodeUnique(code As String) As Boolean";
Debug.ShouldStop(2);
 BA.debugLineNum = 867;BA.debugLine="If kvs.ContainsKey(\"groups\") = False Then Return";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("groups"))),todoactivity.mostCurrent.__c.getField(true,"False"))) { 
if (true) return todoactivity.mostCurrent.__c.getField(true,"True");};
 BA.debugLineNum = 868;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
Debug.ShouldStop(8);
_allgroups = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allgroups = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("groups"))));Debug.locals.put("allGroups", _allgroups);Debug.locals.put("allGroups", _allgroups);
 BA.debugLineNum = 869;BA.debugLine="For Each c As String In allGroups";
Debug.ShouldStop(16);
{
final RemoteObject group3 = _allgroups;
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_c = BA.ObjectToString(group3.runMethod(false,"Get",index3));Debug.locals.put("c", _c);
Debug.locals.put("c", _c);
 BA.debugLineNum = 870;BA.debugLine="If c = code Then Return False";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",_c,_code)) { 
if (true) return todoactivity.mostCurrent.__c.getField(true,"False");};
 }
}Debug.locals.put("c", _c);
;
 BA.debugLineNum = 872;BA.debugLine="Return True";
Debug.ShouldStop(128);
if (true) return todoactivity.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 873;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _joinbtn_click() throws Exception{
try {
		Debug.PushSubsStack("joinBtn_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,904);
if (RapidSub.canDelegate("joinbtn_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","joinbtn_click");}
RemoteObject _joinbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _joinet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _memberskey = RemoteObject.createImmutable("");
RemoteObject _members = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _m = RemoteObject.createImmutable("");
int _i = 0;
RemoteObject _pnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _groupname = RemoteObject.createImmutable("");
 BA.debugLineNum = 904;BA.debugLine="Sub joinBtn_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 905;BA.debugLine="Dim joinBtn As Button = Sender";
Debug.ShouldStop(256);
_joinbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_joinbtn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("joinBtn", _joinbtn);Debug.locals.put("joinBtn", _joinbtn);
 BA.debugLineNum = 906;BA.debugLine="Dim joinET As EditText = joinBtn.Tag";
Debug.ShouldStop(512);
_joinet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
_joinet = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.EditTextWrapper"), _joinbtn.runMethod(false,"getTag"));Debug.locals.put("joinET", _joinet);Debug.locals.put("joinET", _joinet);
 BA.debugLineNum = 907;BA.debugLine="Dim code As String = joinET.Text.Trim.ToUpperCase";
Debug.ShouldStop(1024);
_code = _joinet.runMethod(true,"getText").runMethod(true,"trim").runMethod(true,"toUpperCase");Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 909;BA.debugLine="If code.Length <> 6 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("!",_code.runMethod(true,"length"),BA.numberCast(double.class, 6))) { 
 BA.debugLineNum = 910;BA.debugLine="MsgboxAsync(\"Code must be exactly 6 characters.\"";
Debug.ShouldStop(8192);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Code must be exactly 6 characters.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Invalid code"))),todoactivity.processBA);
 BA.debugLineNum = 911;BA.debugLine="Return";
Debug.ShouldStop(16384);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 914;BA.debugLine="If kvs.ContainsKey(\"group_name_\" & code) = False";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code))),todoactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 915;BA.debugLine="MsgboxAsync(\"No group found with that code.\", \"N";
Debug.ShouldStop(262144);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("No group found with that code.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Not found"))),todoactivity.processBA);
 BA.debugLineNum = 916;BA.debugLine="Return";
Debug.ShouldStop(524288);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 920;BA.debugLine="Dim membersKey As String = \"group_members_\" & cod";
Debug.ShouldStop(8388608);
_memberskey = RemoteObject.concat(RemoteObject.createImmutable("group_members_"),_code);Debug.locals.put("membersKey", _memberskey);Debug.locals.put("membersKey", _memberskey);
 BA.debugLineNum = 921;BA.debugLine="Dim members As List = kvs.Get(membersKey)";
Debug.ShouldStop(16777216);
_members = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_members = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_memberskey)));Debug.locals.put("members", _members);Debug.locals.put("members", _members);
 BA.debugLineNum = 922;BA.debugLine="For Each m As String In members";
Debug.ShouldStop(33554432);
{
final RemoteObject group14 = _members;
final int groupLen14 = group14.runMethod(true,"getSize").<Integer>get()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_m = BA.ObjectToString(group14.runMethod(false,"Get",index14));Debug.locals.put("m", _m);
Debug.locals.put("m", _m);
 BA.debugLineNum = 923;BA.debugLine="If m = Starter.currentUserID Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",_m,todoactivity.mostCurrent._starter._currentuserid /*RemoteObject*/ )) { 
 BA.debugLineNum = 924;BA.debugLine="MsgboxAsync(\"You are already in this group.\", \"";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("You are already in this group.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Already joined"))),todoactivity.processBA);
 BA.debugLineNum = 925;BA.debugLine="Return";
Debug.ShouldStop(268435456);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("m", _m);
;
 BA.debugLineNum = 929;BA.debugLine="members.Add(Starter.currentUserID)";
Debug.ShouldStop(1);
_members.runVoidMethod ("Add",(Object)((todoactivity.mostCurrent._starter._currentuserid /*RemoteObject*/ )));
 BA.debugLineNum = 930;BA.debugLine="kvs.Put(membersKey, members)";
Debug.ShouldStop(2);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_memberskey),(Object)((_members.getObject())));
 BA.debugLineNum = 934;BA.debugLine="For i = 0 To groupList.Size - 1";
Debug.ShouldStop(32);
{
final int step22 = 1;
final int limit22 = RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._grouplist.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step22 > 0 && _i <= limit22) || (step22 < 0 && _i >= limit22) ;_i = ((int)(0 + _i + step22))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 935;BA.debugLine="Dim pnl As B4XView = groupList.GetPanel(i)";
Debug.ShouldStop(64);
_pnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_pnl = todoactivity.mostCurrent._grouplist.runMethod(false,"_getpanel",(Object)(BA.numberCast(int.class, _i)));Debug.locals.put("pnl", _pnl);Debug.locals.put("pnl", _pnl);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 938;BA.debugLine="groupList.RemoveAt(groupList.Size - 1)";
Debug.ShouldStop(512);
todoactivity.mostCurrent._grouplist.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._grouplist.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 940;BA.debugLine="Dim groupName As String = kvs.Get(\"group_name_\" &";
Debug.ShouldStop(2048);
_groupname = BA.ObjectToString(todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code))));Debug.locals.put("groupName", _groupname);Debug.locals.put("groupName", _groupname);
 BA.debugLineNum = 941;BA.debugLine="ToastMessageShow(\"Joined group: \" & groupName, Tr";
Debug.ShouldStop(4096);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Joined group: "),_groupname))),(Object)(todoactivity.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 942;BA.debugLine="loadMyGroups";
Debug.ShouldStop(8192);
_loadmygroups();
 BA.debugLineNum = 943;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _listslist_itemclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("listsList_ItemClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,304);
if (RapidSub.canDelegate("listslist_itemclick")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","listslist_itemclick", _index, _value);}
RemoteObject _listpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _listlbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 304;BA.debugLine="Sub listsList_ItemClick(Index As Int, Value As Obj";
Debug.ShouldStop(32768);
 BA.debugLineNum = 306;BA.debugLine="If isAddingList Then Return";
Debug.ShouldStop(131072);
if (todoactivity._isaddinglist.<Boolean>get().booleanValue()) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 308;BA.debugLine="Dim listPNL As B4XView = listsList.GetPanel(Index";
Debug.ShouldStop(524288);
_listpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_listpnl = todoactivity.mostCurrent._listslist.runMethod(false,"_getpanel",(Object)(_index));Debug.locals.put("listPNL", _listpnl);Debug.locals.put("listPNL", _listpnl);
 BA.debugLineNum = 309;BA.debugLine="Dim listLBL As Label = listPNL.GetView(0)";
Debug.ShouldStop(1048576);
_listlbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
_listlbl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), _listpnl.runMethod(false,"GetView",(Object)(BA.numberCast(int.class, 0))).getObject());Debug.locals.put("listLBL", _listlbl);Debug.locals.put("listLBL", _listlbl);
 BA.debugLineNum = 311;BA.debugLine="currentList = listLBL.Text";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._currentlist = _listlbl.runMethod(true,"getText");
 BA.debugLineNum = 312;BA.debugLine="addTitleTextArea.Text = currentList";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity.mostCurrent._currentlist));
 BA.debugLineNum = 313;BA.debugLine="addTitleTextArea.Visible = True";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 315;BA.debugLine="tasksList.Clear";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_clear");
 BA.debugLineNum = 317;BA.debugLine="Dim key As String = \"list_\" & currentList";
Debug.ShouldStop(268435456);
_key = RemoteObject.concat(RemoteObject.createImmutable("list_"),todoactivity.mostCurrent._currentlist);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 319;BA.debugLine="If kvs.ContainsKey(key) Then";
Debug.ShouldStop(1073741824);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_key)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 320;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
Debug.ShouldStop(-2147483648);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 321;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(1);
{
final RemoteObject group11 = _savedtasks;
final int groupLen11 = group11.runMethod(true,"getSize").<Integer>get()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_task = BA.ObjectToString(group11.runMethod(false,"Get",index11));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 322;BA.debugLine="tasksListUI(task)";
Debug.ShouldStop(2);
_taskslistui(_task);
 }
}Debug.locals.put("task", _task);
;
 };
 BA.debugLineNum = 326;BA.debugLine="tasksList.GetBase.Visible = True";
Debug.ShouldStop(32);
todoactivity.mostCurrent._taskslist.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 327;BA.debugLine="newAddTaskBtn";
Debug.ShouldStop(64);
_newaddtaskbtn();
 BA.debugLineNum = 328;BA.debugLine="updateProgress";
Debug.ShouldStop(128);
_updateprogress();
 BA.debugLineNum = 330;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _listslist_itemlongclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("listsList_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,332);
if (RapidSub.canDelegate("listslist_itemlongclick")) { b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","listslist_itemlongclick", _index, _value); return;}
ResumableSub_listsList_ItemLongClick rsub = new ResumableSub_listsList_ItemLongClick(null,_index,_value);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_listsList_ItemLongClick extends BA.ResumableSub {
public ResumableSub_listsList_ItemLongClick(b4a.example.todoactivity parent,RemoteObject _index,RemoteObject _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.todoactivity parent;
RemoteObject _index;
RemoteObject _value;
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject group18;
int index18;
int groupLen18;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("listsList_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,332);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 334;BA.debugLine="Msgbox2Async(\"Delete or rename this list?\", Value";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Delete or rename this list?")),(Object)(BA.ObjectToCharSequence(_value)),(Object)(BA.ObjectToString("Rename")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Delete")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 335;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "listslist_itemlongclick"), null);
this.state = 22;
return;
case 22:
//C
this.state = 1;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 337;BA.debugLine="If res = DialogResponse.POSITIVE Then ' Rename";
Debug.ShouldStop(65536);
if (true) break;

case 1:
//if
this.state = 21;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 3;
}else 
{ BA.debugLineNum = 340;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then ' Dele";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 5;
}}
if (true) break;

case 3:
//C
this.state = 21;
 BA.debugLineNum = 338;BA.debugLine="showRenameListPanel(Index, Value)";
Debug.ShouldStop(131072);
_showrenamelistpanel(_index,BA.ObjectToString(_value));
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 342;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete th";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Are you sure you want to delete the list \""),_value,RemoteObject.createImmutable("\"?")))),(Object)(BA.ObjectToCharSequence("Confirmation")),(Object)(BA.ObjectToString("No")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Yes")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 343;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(4194304);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "listslist_itemlongclick"), null);
this.state = 23;
return;
case 23:
//C
this.state = 6;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 344;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(8388608);
if (true) break;

case 6:
//if
this.state = 9;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 8;
}if (true) break;

case 8:
//C
this.state = 9;
 BA.debugLineNum = 345;BA.debugLine="Dim savedLists As List = kvs.Get(\"lists\")";
Debug.ShouldStop(16777216);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("lists"))));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 346;BA.debugLine="savedLists.RemoveAt(Index)";
Debug.ShouldStop(33554432);
_savedlists.runVoidMethod ("RemoveAt",(Object)(_index));
 BA.debugLineNum = 347;BA.debugLine="kvs.Put(\"lists\", savedLists)";
Debug.ShouldStop(67108864);
parent._kvs.runVoidMethod ("_put",(Object)(BA.ObjectToString("lists")),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 348;BA.debugLine="listsList.RemoveAt(Index)";
Debug.ShouldStop(134217728);
parent.mostCurrent._listslist.runVoidMethod ("_removeat",(Object)(_index));
 BA.debugLineNum = 349;BA.debugLine="ToastMessageShow(\"List deleted\", False)";
Debug.ShouldStop(268435456);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("List deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 9:
//C
this.state = 10;
;
 BA.debugLineNum = 353;BA.debugLine="Dim key As String = \"list_\" & Value";
Debug.ShouldStop(1);
_key = RemoteObject.concat(RemoteObject.createImmutable("list_"),_value);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 354;BA.debugLine="If kvs.ContainsKey(key) Then";
Debug.ShouldStop(2);
if (true) break;

case 10:
//if
this.state = 17;
if (parent._kvs.runMethod(true,"_containskey",(Object)(_key)).<Boolean>get().booleanValue()) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 BA.debugLineNum = 355;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
Debug.ShouldStop(4);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 356;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(8);
if (true) break;

case 13:
//for
this.state = 16;
group18 = _savedtasks;
index18 = 0;
groupLen18 = group18.runMethod(true,"getSize").<Integer>get();
Debug.locals.put("task", _task);
this.state = 24;
if (true) break;

case 24:
//C
this.state = 16;
if (index18 < groupLen18) {
this.state = 15;
_task = BA.ObjectToString(group18.runMethod(false,"Get",index18));Debug.locals.put("task", _task);}
if (true) break;

case 25:
//C
this.state = 24;
index18++;
Debug.locals.put("task", _task);
if (true) break;

case 15:
//C
this.state = 25;
 BA.debugLineNum = 357;BA.debugLine="kvs.Remove(\"checked_\" & Value & \"_\" & task)";
Debug.ShouldStop(16);
parent._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("checked_"),_value,RemoteObject.createImmutable("_"),_task)));
 if (true) break;
if (true) break;

case 16:
//C
this.state = 17;
Debug.locals.put("task", _task);
;
 BA.debugLineNum = 359;BA.debugLine="kvs.Remove(key)";
Debug.ShouldStop(64);
parent._kvs.runVoidMethod ("_remove",(Object)(_key));
 if (true) break;
;
 BA.debugLineNum = 363;BA.debugLine="If currentList = Value Then";
Debug.ShouldStop(1024);

case 17:
//if
this.state = 20;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._currentlist,BA.ObjectToString(_value))) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
 BA.debugLineNum = 364;BA.debugLine="currentList = \"\"";
Debug.ShouldStop(2048);
parent.mostCurrent._currentlist = BA.ObjectToString("");
 BA.debugLineNum = 365;BA.debugLine="tasksList.Clear";
Debug.ShouldStop(4096);
parent.mostCurrent._taskslist.runVoidMethod ("_clear");
 BA.debugLineNum = 366;BA.debugLine="tasksList.GetBase.Visible = False";
Debug.ShouldStop(8192);
parent.mostCurrent._taskslist.runMethod(false,"_getbase").runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 367;BA.debugLine="addTitleTextArea.Text = \"\"";
Debug.ShouldStop(16384);
parent.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 368;BA.debugLine="addTitleTextArea.Visible = False";
Debug.ShouldStop(32768);
parent.mostCurrent._addtitletextarea.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 if (true) break;

case 20:
//C
this.state = 21;
;
 if (true) break;

case 21:
//C
this.state = -1;
;
 BA.debugLineNum = 372;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _listslistgrp_itemclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("listsListGrp_ItemClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1126);
if (RapidSub.canDelegate("listslistgrp_itemclick")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","listslistgrp_itemclick", _index, _value);}
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _currentgrplist = RemoteObject.createImmutable("");
RemoteObject _taskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 1126;BA.debugLine="Sub listsListGrp_ItemClick(Index As Int, Value As";
Debug.ShouldStop(32);
 BA.debugLineNum = 1127;BA.debugLine="If Value = \"newListPanel\" Then Return";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",_value,RemoteObject.createImmutable(("newListPanel")))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 1128;BA.debugLine="Dim code As String = groupET.Tag";
Debug.ShouldStop(128);
_code = BA.ObjectToString(todoactivity.mostCurrent._groupet.runMethod(false,"getTag"));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1129;BA.debugLine="Dim currentGrpList As String = Value";
Debug.ShouldStop(256);
_currentgrplist = BA.ObjectToString(_value);Debug.locals.put("currentGrpList", _currentgrplist);Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1131;BA.debugLine="tasksListGrp.Clear";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 1132;BA.debugLine="tasksListGrp.GetBase.Visible = True";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._taskslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 1134;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
Debug.ShouldStop(8192);
_taskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_currentgrplist);Debug.locals.put("taskKey", _taskkey);Debug.locals.put("taskKey", _taskkey);
 BA.debugLineNum = 1135;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
Debug.ShouldStop(16384);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_taskkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1136;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
Debug.ShouldStop(32768);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1137;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(65536);
{
final RemoteObject group9 = _savedtasks;
final int groupLen9 = group9.runMethod(true,"getSize").<Integer>get()
;int index9 = 0;
;
for (; index9 < groupLen9;index9++){
_task = BA.ObjectToString(group9.runMethod(false,"Get",index9));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 1138;BA.debugLine="tasksListGrpUI(task, code, currentGrpList)";
Debug.ShouldStop(131072);
_taskslistgrpui(_task,_code,_currentgrplist);
 }
}Debug.locals.put("task", _task);
;
 };
 BA.debugLineNum = 1142;BA.debugLine="newAddTaskBtnGrp(code, currentGrpList)";
Debug.ShouldStop(2097152);
_newaddtaskbtngrp(_code,_currentgrplist);
 BA.debugLineNum = 1143;BA.debugLine="updateProgressGrp(code, currentGrpList)";
Debug.ShouldStop(4194304);
_updateprogressgrp(_code,_currentgrplist);
 BA.debugLineNum = 1144;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _listslistgrp_itemlongclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("listsListGrp_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1146);
if (RapidSub.canDelegate("listslistgrp_itemlongclick")) { b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","listslistgrp_itemlongclick", _index, _value); return;}
ResumableSub_listsListGrp_ItemLongClick rsub = new ResumableSub_listsListGrp_ItemLongClick(null,_index,_value);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_listsListGrp_ItemLongClick extends BA.ResumableSub {
public ResumableSub_listsListGrp_ItemLongClick(b4a.example.todoactivity parent,RemoteObject _index,RemoteObject _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.todoactivity parent;
RemoteObject _index;
RemoteObject _value;
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _newname = RemoteObject.createImmutable("");
RemoteObject _listskey = RemoteObject.createImmutable("");
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _existing = RemoteObject.createImmutable("");
RemoteObject _oldtaskkey = RemoteObject.createImmutable("");
RemoteObject _newtaskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject _oldck = RemoteObject.createImmutable("");
RemoteObject _newck = RemoteObject.createImmutable("");
RemoteObject _ln = RemoteObject.createImmutable("");
RemoteObject _res2 = RemoteObject.createImmutable(0);
RemoteObject _taskkey = RemoteObject.createImmutable("");
RemoteObject group12;
int index12;
int groupLen12;
RemoteObject group22;
int index22;
int groupLen22;
RemoteObject group36;
int index36;
int groupLen36;
RemoteObject group51;
int index51;
int groupLen51;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("listsListGrp_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1146);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 1147;BA.debugLine="If Value = \"newListPanel\" Then Return";
Debug.ShouldStop(67108864);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_value,RemoteObject.createImmutable(("newListPanel")))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
 BA.debugLineNum = 1148;BA.debugLine="Dim code As String = groupET.Tag";
Debug.ShouldStop(134217728);
_code = BA.ObjectToString(parent.mostCurrent._groupet.runMethod(false,"getTag"));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1150;BA.debugLine="Msgbox2Async(\"Delete or rename this list?\", Value";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Delete or rename this list?")),(Object)(BA.ObjectToCharSequence(_value)),(Object)(BA.ObjectToString("Rename")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Delete")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 1151;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(1073741824);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "listslistgrp_itemlongclick"), null);
this.state = 53;
return;
case 53:
//C
this.state = 7;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 1153;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(1);
if (true) break;

case 7:
//if
this.state = 52;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 9;
}else 
{ BA.debugLineNum = 1199;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 39;
}}
if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 1155;BA.debugLine="Dim newName As String = \"\"";
Debug.ShouldStop(4);
_newname = BA.ObjectToString("");Debug.locals.put("newName", _newname);Debug.locals.put("newName", _newname);
 BA.debugLineNum = 1156;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(8);
if (true) break;

case 10:
//if
this.state = 13;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 BA.debugLineNum = 1158;BA.debugLine="showRenameListPanelGrp(Index, Value, code)";
Debug.ShouldStop(32);
_showrenamelistpanelgrp(_index,BA.ObjectToString(_value),_code);
 if (true) break;

case 13:
//C
this.state = 14;
;
 BA.debugLineNum = 1161;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
Debug.ShouldStop(256);
_listskey = RemoteObject.concat(RemoteObject.createImmutable("group_lists_"),_code);Debug.locals.put("listsKey", _listskey);Debug.locals.put("listsKey", _listskey);
 BA.debugLineNum = 1162;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
Debug.ShouldStop(512);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_listskey)));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 1165;BA.debugLine="For Each existing As String In savedLists";
Debug.ShouldStop(4096);
if (true) break;

case 14:
//for
this.state = 21;
group12 = _savedlists;
index12 = 0;
groupLen12 = group12.runMethod(true,"getSize").<Integer>get();
Debug.locals.put("existing", _existing);
this.state = 54;
if (true) break;

case 54:
//C
this.state = 21;
if (index12 < groupLen12) {
this.state = 16;
_existing = BA.ObjectToString(group12.runMethod(false,"Get",index12));Debug.locals.put("existing", _existing);}
if (true) break;

case 55:
//C
this.state = 54;
index12++;
Debug.locals.put("existing", _existing);
if (true) break;

case 16:
//C
this.state = 17;
 BA.debugLineNum = 1166;BA.debugLine="If existing = newName Then";
Debug.ShouldStop(8192);
if (true) break;

case 17:
//if
this.state = 20;
if (RemoteObject.solveBoolean("=",_existing,_newname)) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
 BA.debugLineNum = 1167;BA.debugLine="MsgboxAsync(\"Name already exists.\", \"Duplicate";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Name already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate"))),todoactivity.processBA);
 BA.debugLineNum = 1168;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return ;
 if (true) break;

case 20:
//C
this.state = 55;
;
 if (true) break;
if (true) break;

case 21:
//C
this.state = 22;
Debug.locals.put("existing", _existing);
;
 BA.debugLineNum = 1173;BA.debugLine="Dim oldTaskKey As String = \"group_list_\" & code";
Debug.ShouldStop(1048576);
_oldtaskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_value);Debug.locals.put("oldTaskKey", _oldtaskkey);Debug.locals.put("oldTaskKey", _oldtaskkey);
 BA.debugLineNum = 1174;BA.debugLine="Dim newTaskKey As String = \"group_list_\" & code";
Debug.ShouldStop(2097152);
_newtaskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_newname);Debug.locals.put("newTaskKey", _newtaskkey);Debug.locals.put("newTaskKey", _newtaskkey);
 BA.debugLineNum = 1175;BA.debugLine="If kvs.ContainsKey(oldTaskKey) Then";
Debug.ShouldStop(4194304);
if (true) break;

case 22:
//if
this.state = 33;
if (parent._kvs.runMethod(true,"_containskey",(Object)(_oldtaskkey)).<Boolean>get().booleanValue()) { 
this.state = 24;
}if (true) break;

case 24:
//C
this.state = 25;
 BA.debugLineNum = 1176;BA.debugLine="Dim savedTasks As List = kvs.Get(oldTaskKey)";
Debug.ShouldStop(8388608);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_oldtaskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1177;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(16777216);
if (true) break;

case 25:
//for
this.state = 32;
group22 = _savedtasks;
index22 = 0;
groupLen22 = group22.runMethod(true,"getSize").<Integer>get();
Debug.locals.put("task", _task);
this.state = 56;
if (true) break;

case 56:
//C
this.state = 32;
if (index22 < groupLen22) {
this.state = 27;
_task = BA.ObjectToString(group22.runMethod(false,"Get",index22));Debug.locals.put("task", _task);}
if (true) break;

case 57:
//C
this.state = 56;
index22++;
Debug.locals.put("task", _task);
if (true) break;

case 27:
//C
this.state = 28;
 BA.debugLineNum = 1178;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code";
Debug.ShouldStop(33554432);
_oldck = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_value,RemoteObject.createImmutable("_"),_task);Debug.locals.put("oldCK", _oldck);Debug.locals.put("oldCK", _oldck);
 BA.debugLineNum = 1179;BA.debugLine="Dim newCK As String = \"group_checked_\" & code";
Debug.ShouldStop(67108864);
_newck = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_newname,RemoteObject.createImmutable("_"),_task);Debug.locals.put("newCK", _newck);Debug.locals.put("newCK", _newck);
 BA.debugLineNum = 1180;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
Debug.ShouldStop(134217728);
if (true) break;

case 28:
//if
this.state = 31;
if (parent._kvs.runMethod(true,"_containskey",(Object)(_oldck)).<Boolean>get().booleanValue()) { 
this.state = 30;
}if (true) break;

case 30:
//C
this.state = 31;
 BA.debugLineNum = 1181;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
Debug.ShouldStop(268435456);
parent._kvs.runVoidMethod ("_put",(Object)(_newck),(Object)(parent._kvs.runMethod(false,"_get",(Object)(_oldck))));
 BA.debugLineNum = 1182;BA.debugLine="kvs.Remove(oldCK)";
Debug.ShouldStop(536870912);
parent._kvs.runVoidMethod ("_remove",(Object)(_oldck));
 if (true) break;

case 31:
//C
this.state = 57;
;
 if (true) break;
if (true) break;

case 32:
//C
this.state = 33;
Debug.locals.put("task", _task);
;
 BA.debugLineNum = 1185;BA.debugLine="kvs.Put(newTaskKey, savedTasks)";
Debug.ShouldStop(1);
parent._kvs.runVoidMethod ("_put",(Object)(_newtaskkey),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 1186;BA.debugLine="kvs.Remove(oldTaskKey)";
Debug.ShouldStop(2);
parent._kvs.runVoidMethod ("_remove",(Object)(_oldtaskkey));
 if (true) break;

case 33:
//C
this.state = 34;
;
 BA.debugLineNum = 1189;BA.debugLine="savedLists.Set(Index, newName)";
Debug.ShouldStop(16);
_savedlists.runVoidMethod ("Set",(Object)(_index),(Object)((_newname)));
 BA.debugLineNum = 1190;BA.debugLine="kvs.Put(listsKey, savedLists)";
Debug.ShouldStop(32);
parent._kvs.runVoidMethod ("_put",(Object)(_listskey),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 1192;BA.debugLine="listsListGrp.Clear";
Debug.ShouldStop(128);
parent.mostCurrent._listslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 1193;BA.debugLine="For Each ln As String In savedLists";
Debug.ShouldStop(256);
if (true) break;

case 34:
//for
this.state = 37;
group36 = _savedlists;
index36 = 0;
groupLen36 = group36.runMethod(true,"getSize").<Integer>get();
Debug.locals.put("ln", _ln);
this.state = 58;
if (true) break;

case 58:
//C
this.state = 37;
if (index36 < groupLen36) {
this.state = 36;
_ln = BA.ObjectToString(group36.runMethod(false,"Get",index36));Debug.locals.put("ln", _ln);}
if (true) break;

case 59:
//C
this.state = 58;
index36++;
Debug.locals.put("ln", _ln);
if (true) break;

case 36:
//C
this.state = 59;
 BA.debugLineNum = 1194;BA.debugLine="listsListGrp.AddTextItem(ln, ln)";
Debug.ShouldStop(512);
parent.mostCurrent._listslistgrp.runVoidMethod ("_addtextitem",(Object)((_ln)),(Object)((_ln)));
 if (true) break;
if (true) break;

case 37:
//C
this.state = 52;
Debug.locals.put("ln", _ln);
;
 BA.debugLineNum = 1197;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("List renamed")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 39:
//C
this.state = 40;
 BA.debugLineNum = 1200;BA.debugLine="Msgbox2Async(\"Delete list \"\"\" & Value & \"\"\"?\", \"";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Delete list \""),_value,RemoteObject.createImmutable("\"?")))),(Object)(BA.ObjectToCharSequence("Confirm")),(Object)(BA.ObjectToString("No")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Yes")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 1201;BA.debugLine="Wait For Msgbox_Result (res2 As Int)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "listslistgrp_itemlongclick"), null);
this.state = 60;
return;
case 60:
//C
this.state = 40;
_res2 = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res2", _res2);
;
 BA.debugLineNum = 1202;BA.debugLine="If res2 = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(131072);
if (true) break;

case 40:
//if
this.state = 51;
if (RemoteObject.solveBoolean("=",_res2,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 42;
}if (true) break;

case 42:
//C
this.state = 43;
 BA.debugLineNum = 1203;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
Debug.ShouldStop(262144);
_listskey = RemoteObject.concat(RemoteObject.createImmutable("group_lists_"),_code);Debug.locals.put("listsKey", _listskey);Debug.locals.put("listsKey", _listskey);
 BA.debugLineNum = 1204;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
Debug.ShouldStop(524288);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_listskey)));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 1205;BA.debugLine="savedLists.RemoveAt(Index)";
Debug.ShouldStop(1048576);
_savedlists.runVoidMethod ("RemoveAt",(Object)(_index));
 BA.debugLineNum = 1206;BA.debugLine="kvs.Put(listsKey, savedLists)";
Debug.ShouldStop(2097152);
parent._kvs.runVoidMethod ("_put",(Object)(_listskey),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 1209;BA.debugLine="Dim taskKey As String = \"group_list_\" & code &";
Debug.ShouldStop(16777216);
_taskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_value);Debug.locals.put("taskKey", _taskkey);Debug.locals.put("taskKey", _taskkey);
 BA.debugLineNum = 1210;BA.debugLine="If kvs.ContainsKey(taskKey) Then";
Debug.ShouldStop(33554432);
if (true) break;

case 43:
//if
this.state = 50;
if (parent._kvs.runMethod(true,"_containskey",(Object)(_taskkey)).<Boolean>get().booleanValue()) { 
this.state = 45;
}if (true) break;

case 45:
//C
this.state = 46;
 BA.debugLineNum = 1211;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
Debug.ShouldStop(67108864);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1212;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(134217728);
if (true) break;

case 46:
//for
this.state = 49;
group51 = _savedtasks;
index51 = 0;
groupLen51 = group51.runMethod(true,"getSize").<Integer>get();
Debug.locals.put("task", _task);
this.state = 61;
if (true) break;

case 61:
//C
this.state = 49;
if (index51 < groupLen51) {
this.state = 48;
_task = BA.ObjectToString(group51.runMethod(false,"Get",index51));Debug.locals.put("task", _task);}
if (true) break;

case 62:
//C
this.state = 61;
index51++;
Debug.locals.put("task", _task);
if (true) break;

case 48:
//C
this.state = 62;
 BA.debugLineNum = 1213;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & Va";
Debug.ShouldStop(268435456);
parent._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_value,RemoteObject.createImmutable("_"),_task)));
 if (true) break;
if (true) break;

case 49:
//C
this.state = 50;
Debug.locals.put("task", _task);
;
 BA.debugLineNum = 1215;BA.debugLine="kvs.Remove(taskKey)";
Debug.ShouldStop(1073741824);
parent._kvs.runVoidMethod ("_remove",(Object)(_taskkey));
 if (true) break;

case 50:
//C
this.state = 51;
;
 BA.debugLineNum = 1218;BA.debugLine="listsListGrp.RemoveAt(Index)";
Debug.ShouldStop(2);
parent.mostCurrent._listslistgrp.runVoidMethod ("_removeat",(Object)(_index));
 BA.debugLineNum = 1219;BA.debugLine="tasksListGrp.Clear";
Debug.ShouldStop(4);
parent.mostCurrent._taskslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 1220;BA.debugLine="tasksListGrp.GetBase.Visible = False";
Debug.ShouldStop(8);
parent.mostCurrent._taskslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1221;BA.debugLine="ToastMessageShow(\"List deleted\", False)";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("List deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 51:
//C
this.state = 52;
;
 if (true) break;

case 52:
//C
this.state = -1;
;
 BA.debugLineNum = 1224;BA.debugLine="End Sub";
Debug.ShouldStop(128);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _loadmygroups() throws Exception{
try {
		Debug.PushSubsStack("loadMyGroups (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,752);
if (RapidSub.canDelegate("loadmygroups")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","loadmygroups");}
RemoteObject _allgroups = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _memberskey = RemoteObject.createImmutable("");
RemoteObject _members = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _myid = RemoteObject.createImmutable("");
RemoteObject _m = RemoteObject.createImmutable("");
RemoteObject _groupname = RemoteObject.createImmutable("");
 BA.debugLineNum = 752;BA.debugLine="Sub loadMyGroups";
Debug.ShouldStop(32768);
 BA.debugLineNum = 753;BA.debugLine="groupList.Clear";
Debug.ShouldStop(65536);
todoactivity.mostCurrent._grouplist.runVoidMethod ("_clear");
 BA.debugLineNum = 754;BA.debugLine="groupET.Text = \"\"";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._groupet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 755;BA.debugLine="groupET.Enabled = False";
Debug.ShouldStop(262144);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 756;BA.debugLine="listsListGrp.Clear";
Debug.ShouldStop(524288);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 757;BA.debugLine="listsListGrp.GetBase.Visible = False";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._listslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 758;BA.debugLine="tasksListGrp.Clear";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 759;BA.debugLine="tasksListGrp.GetBase.Visible = False";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._taskslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 760;BA.debugLine="progressBarGrp.Progress = 0";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._progressbargrp.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 BA.debugLineNum = 761;BA.debugLine="progressNumberGrp.Text = \"\"";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._progressnumbergrp.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 762;BA.debugLine="progressPercentGrp.Text = \"\"";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._progresspercentgrp.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 764;BA.debugLine="If kvs.ContainsKey(\"groups\") = False Then Return";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("groups"))),todoactivity.mostCurrent.__c.getField(true,"False"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 766;BA.debugLine="Dim allGroups As List = kvs.Get(\"groups\")";
Debug.ShouldStop(536870912);
_allgroups = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_allgroups = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("groups"))));Debug.locals.put("allGroups", _allgroups);Debug.locals.put("allGroups", _allgroups);
 BA.debugLineNum = 767;BA.debugLine="For Each code As String In allGroups";
Debug.ShouldStop(1073741824);
{
final RemoteObject group13 = _allgroups;
final int groupLen13 = group13.runMethod(true,"getSize").<Integer>get()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_code = BA.ObjectToString(group13.runMethod(false,"Get",index13));Debug.locals.put("code", _code);
Debug.locals.put("code", _code);
 BA.debugLineNum = 768;BA.debugLine="Dim membersKey As String = \"group_members_\" & co";
Debug.ShouldStop(-2147483648);
_memberskey = RemoteObject.concat(RemoteObject.createImmutable("group_members_"),_code);Debug.locals.put("membersKey", _memberskey);Debug.locals.put("membersKey", _memberskey);
 BA.debugLineNum = 769;BA.debugLine="If kvs.ContainsKey(membersKey) Then";
Debug.ShouldStop(1);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_memberskey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 770;BA.debugLine="Dim members As List = kvs.Get(membersKey)";
Debug.ShouldStop(2);
_members = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_members = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_memberskey)));Debug.locals.put("members", _members);Debug.locals.put("members", _members);
 BA.debugLineNum = 771;BA.debugLine="Dim myID As String = Starter.currentUserID ' ho";
Debug.ShouldStop(4);
_myid = todoactivity.mostCurrent._starter._currentuserid /*RemoteObject*/ ;Debug.locals.put("myID", _myid);Debug.locals.put("myID", _myid);
 BA.debugLineNum = 772;BA.debugLine="For Each m As String In members";
Debug.ShouldStop(8);
{
final RemoteObject group18 = _members;
final int groupLen18 = group18.runMethod(true,"getSize").<Integer>get()
;int index18 = 0;
;
for (; index18 < groupLen18;index18++){
_m = BA.ObjectToString(group18.runMethod(false,"Get",index18));Debug.locals.put("m", _m);
Debug.locals.put("m", _m);
 BA.debugLineNum = 773;BA.debugLine="If m = myID Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",_m,_myid)) { 
 BA.debugLineNum = 774;BA.debugLine="Dim groupName As String = kvs.Get(\"group_name";
Debug.ShouldStop(32);
_groupname = BA.ObjectToString(todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code))));Debug.locals.put("groupName", _groupname);Debug.locals.put("groupName", _groupname);
 BA.debugLineNum = 775;BA.debugLine="groupList.AddTextItem(groupName, code)";
Debug.ShouldStop(64);
todoactivity.mostCurrent._grouplist.runVoidMethod ("_addtextitem",(Object)((_groupname)),(Object)((_code)));
 BA.debugLineNum = 776;BA.debugLine="Exit";
Debug.ShouldStop(128);
if (true) break;
 };
 }
}Debug.locals.put("m", _m);
;
 };
 }
}Debug.locals.put("code", _code);
;
 BA.debugLineNum = 781;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _newaddtaskbtn() throws Exception{
try {
		Debug.PushSubsStack("newAddTaskBtn (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,101);
if (RapidSub.canDelegate("newaddtaskbtn")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","newaddtaskbtn");}
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
 BA.debugLineNum = 101;BA.debugLine="Sub newAddTaskBtn";
Debug.ShouldStop(16);
 BA.debugLineNum = 102;BA.debugLine="addTaskBtnPNL.Initialize(\"addTaskBtnPNL\")";
Debug.ShouldStop(32);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskBtnPNL")));
 BA.debugLineNum = 103;BA.debugLine="addTaskBtnPNL.SetLayout(10dip, 0dip, 190dip, 70di";
Debug.ShouldStop(64);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("SetLayout",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 190)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))));
 BA.debugLineNum = 104;BA.debugLine="addTaskBtnPNL.Color = Colors.ARGB(0, 255, 255, 25";
Debug.ShouldStop(128);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 105;BA.debugLine="addTaskBtn.Initialize(\"addTaskBtn\")";
Debug.ShouldStop(256);
todoactivity.mostCurrent._addtaskbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskBtn")));
 BA.debugLineNum = 106;BA.debugLine="addTaskBtn.Text = \"+ add a task \"";
Debug.ShouldStop(512);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setText",BA.ObjectToCharSequence("+ add a task "));
 BA.debugLineNum = 107;BA.debugLine="addTaskBtnPNL.AddView(addTaskBtn, 10dip, 20dip, a";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("AddView",(Object)((todoactivity.mostCurrent._addtaskbtn.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(todoactivity.mostCurrent._addtaskbtnpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 108;BA.debugLine="tasksList.Add(addTaskBtnPNL, \"\")";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), todoactivity.mostCurrent._addtaskbtnpnl.getObject()),(Object)((RemoteObject.createImmutable(""))));
 BA.debugLineNum = 110;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(8192);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 111;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(16384);
switch (BA.switchObjectToInt(todoactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 113;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(65536);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 114;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 115;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
Debug.ShouldStop(262144);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 59)),(Object)(BA.numberCast(int.class, 117)),(Object)(BA.numberCast(int.class, 151)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 116;BA.debugLine="addTaskBtn.Background = cd";
Debug.ShouldStop(524288);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 117;BA.debugLine="addTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 119;BA.debugLine="addTaskBtnPNL.Color = Colors.Transparent";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 120;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
Debug.ShouldStop(8388608);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 121;BA.debugLine="addTaskBtn.Background = cd";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 122;BA.debugLine="addTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 125;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(268435456);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 126;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 127;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
Debug.ShouldStop(1073741824);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 90)),(Object)(BA.numberCast(int.class, 105)),(Object)(BA.numberCast(int.class, 136)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 128;BA.debugLine="addTaskBtn.Background = cd";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 129;BA.debugLine="addTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(1);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 131;BA.debugLine="addTaskBtnPNL.Color = Colors.Transparent";
Debug.ShouldStop(4);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 132;BA.debugLine="cd.Initialize(Colors.ARGB(120, 255, 255, 255),";
Debug.ShouldStop(8);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 133;BA.debugLine="addTaskBtn.Background = cd";
Debug.ShouldStop(16);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 134;BA.debugLine="addTaskBtn.TextColor = Colors.Black";
Debug.ShouldStop(32);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 break; }
case 2: {
 BA.debugLineNum = 137;BA.debugLine="addTaskBtn.Typeface = pixeltf";
Debug.ShouldStop(256);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setTypeface",(todoactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 138;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(512);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 139;BA.debugLine="addTaskBtn.TextColor = Colors.DarkGray";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 140;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
Debug.ShouldStop(2048);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 90)),(Object)(BA.numberCast(int.class, 105)),(Object)(BA.numberCast(int.class, 136)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 141;BA.debugLine="addTaskBtn.Background = cd";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 142;BA.debugLine="addTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(8192);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 144;BA.debugLine="addTaskBtnPNL.Color = Colors.ARGB(0, 232, 213,";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._addtaskbtnpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 232)),(Object)(BA.numberCast(int.class, 213)),(Object)(BA.numberCast(int.class, 179))));
 BA.debugLineNum = 145;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
Debug.ShouldStop(65536);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 146;BA.debugLine="addTaskBtn.Background = cd";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._addtaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 147;BA.debugLine="addTaskBtn.TextColor = Colors.Black";
Debug.ShouldStop(262144);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 break; }
}
;
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
public static RemoteObject  _newaddtaskbtngrp(RemoteObject _code,RemoteObject _currentgrplist) throws Exception{
try {
		Debug.PushSubsStack("newAddTaskBtnGrp (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1315);
if (RapidSub.canDelegate("newaddtaskbtngrp")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","newaddtaskbtngrp", _code, _currentgrplist);}
RemoteObject _addbtnpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _addbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("code", _code);
Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1315;BA.debugLine="Sub newAddTaskBtnGrp(code As String, currentGrpLis";
Debug.ShouldStop(4);
 BA.debugLineNum = 1316;BA.debugLine="Dim addBtnPNL As Panel";
Debug.ShouldStop(8);
_addbtnpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("addBtnPNL", _addbtnpnl);
 BA.debugLineNum = 1317;BA.debugLine="addBtnPNL.Initialize(\"addTaskBtnPNLGrp\")";
Debug.ShouldStop(16);
_addbtnpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskBtnPNLGrp")));
 BA.debugLineNum = 1318;BA.debugLine="addBtnPNL.SetLayout(10dip, 0dip, 190dip, 70dip)";
Debug.ShouldStop(32);
_addbtnpnl.runVoidMethod ("SetLayout",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 190)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))));
 BA.debugLineNum = 1319;BA.debugLine="addBtnPNL.Color = Colors.Transparent";
Debug.ShouldStop(64);
_addbtnpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 1321;BA.debugLine="Dim addBtn As Button";
Debug.ShouldStop(256);
_addbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("addBtn", _addbtn);
 BA.debugLineNum = 1322;BA.debugLine="addBtn.Initialize(\"addTaskBtnGrp\")";
Debug.ShouldStop(512);
_addbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskBtnGrp")));
 BA.debugLineNum = 1323;BA.debugLine="addBtn.Text = \"+ add a task\"";
Debug.ShouldStop(1024);
_addbtn.runMethod(true,"setText",BA.ObjectToCharSequence("+ add a task"));
 BA.debugLineNum = 1325;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(4096);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 1326;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
Debug.ShouldStop(8192);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 1327;BA.debugLine="addBtn.Background = cd";
Debug.ShouldStop(16384);
_addbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 1328;BA.debugLine="addBtn.TextColor = Colors.White";
Debug.ShouldStop(32768);
_addbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 1331;BA.debugLine="Dim ctx As List";
Debug.ShouldStop(262144);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1332;BA.debugLine="ctx.Initialize";
Debug.ShouldStop(524288);
_ctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 1333;BA.debugLine="ctx.Add(code)";
Debug.ShouldStop(1048576);
_ctx.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 1334;BA.debugLine="ctx.Add(currentGrpList)";
Debug.ShouldStop(2097152);
_ctx.runVoidMethod ("Add",(Object)((_currentgrplist)));
 BA.debugLineNum = 1335;BA.debugLine="addBtn.Tag = ctx";
Debug.ShouldStop(4194304);
_addbtn.runMethod(false,"setTag",(_ctx.getObject()));
 BA.debugLineNum = 1337;BA.debugLine="addBtnPNL.AddView(addBtn, 10dip, 20dip, addBtnPNL";
Debug.ShouldStop(16777216);
_addbtnpnl.runVoidMethod ("AddView",(Object)((_addbtn.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(_addbtnpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 1338;BA.debugLine="tasksListGrp.Add(addBtnPNL, \"\")";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _addbtnpnl.getObject()),(Object)((RemoteObject.createImmutable(""))));
 BA.debugLineNum = 1339;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _newgroupbtn_click() throws Exception{
try {
		Debug.PushSubsStack("newGroupBtn_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,783);
if (RapidSub.canDelegate("newgroupbtn_click")) { b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","newgroupbtn_click"); return;}
ResumableSub_newGroupBtn_Click rsub = new ResumableSub_newGroupBtn_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_newGroupBtn_Click extends BA.ResumableSub {
public ResumableSub_newGroupBtn_Click(b4a.example.todoactivity parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.todoactivity parent;
RemoteObject _res = RemoteObject.createImmutable(0);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("newGroupBtn_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,783);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 784;BA.debugLine="Msgbox2Async(\"Join or create a group?\", \"Groups\",";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Join or create a group?")),(Object)(BA.ObjectToCharSequence("Groups")),(Object)(BA.ObjectToString("Create")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Join")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 785;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "newgroupbtn_click"), null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 787;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(262144);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 3;
}else 
{ BA.debugLineNum = 790;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 5;
}}
if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 789;BA.debugLine="showCreateGroupPanel";
Debug.ShouldStop(1048576);
_showcreategrouppanel();
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 792;BA.debugLine="showJoinGroupPanel";
Debug.ShouldStop(8388608);
_showjoingrouppanel();
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 794;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _newlistbtn_click() throws Exception{
try {
		Debug.PushSubsStack("newListBtn_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,153);
if (RapidSub.canDelegate("newlistbtn_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","newlistbtn_click");}
 BA.debugLineNum = 153;BA.debugLine="Sub newListBtn_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 155;BA.debugLine="tasksList.Clear";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_clear");
 BA.debugLineNum = 156;BA.debugLine="isAddingList = True";
Debug.ShouldStop(134217728);
todoactivity._isaddinglist = todoactivity.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 158;BA.debugLine="progressNumber.Text = \"\"";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._progressnumber.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 159;BA.debugLine="progressPercent.Text = \"\"";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._progresspercent.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 160;BA.debugLine="progressBar.Progress = 0";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._progressbar.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 BA.debugLineNum = 162;BA.debugLine="addTitleTextArea.Visible = True";
Debug.ShouldStop(2);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 163;BA.debugLine="addTitleTextArea.Enabled = True";
Debug.ShouldStop(4);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 164;BA.debugLine="addTitleTextArea.Background = Null";
Debug.ShouldStop(8);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setBackground",(todoactivity.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 165;BA.debugLine="addTitleTextArea.Text = \"\"";
Debug.ShouldStop(16);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 166;BA.debugLine="addTitleTextArea.Hint = \"+ add a title...\"";
Debug.ShouldStop(32);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setHint",BA.ObjectToString("+ add a title..."));
 BA.debugLineNum = 167;BA.debugLine="addTitleTextArea.RequestFocus";
Debug.ShouldStop(64);
todoactivity.mostCurrent._addtitletextarea.runVoidMethod ("RequestFocus");
 BA.debugLineNum = 168;BA.debugLine="addTitleTextArea.Tag = Null";
Debug.ShouldStop(128);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setTag",todoactivity.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 170;BA.debugLine="newListBtn.Enabled = False";
Debug.ShouldStop(512);
todoactivity.mostCurrent._newlistbtn.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 172;BA.debugLine="tasksList.GetBase.Visible = True";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._taskslist.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 173;BA.debugLine="addTaskBtn.Visible = True";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._addtaskbtn.runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 175;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _newlistbtngrp_click() throws Exception{
try {
		Debug.PushSubsStack("newListBtnGrp_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1057);
if (RapidSub.canDelegate("newlistbtngrp_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","newlistbtngrp_click");}
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _newlistpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _newlistet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _newlistconfirmbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
 BA.debugLineNum = 1057;BA.debugLine="Sub newListBtnGrp_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 1058;BA.debugLine="If groupET.Tag = Null Or groupET.Tag Is List Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("n",todoactivity.mostCurrent._groupet.runMethod(false,"getTag")) || RemoteObject.solveBoolean("i",todoactivity.mostCurrent._groupet.runMethod(false,"getTag"), RemoteObject.createImmutable("java.util.List"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 1060;BA.debugLine="Dim code As String = groupET.Tag";
Debug.ShouldStop(8);
_code = BA.ObjectToString(todoactivity.mostCurrent._groupet.runMethod(false,"getTag"));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1062;BA.debugLine="listsListGrp.GetBase.Visible = True";
Debug.ShouldStop(32);
todoactivity.mostCurrent._listslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 1063;BA.debugLine="tasksListGrp.Clear";
Debug.ShouldStop(64);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 1064;BA.debugLine="tasksListGrp.GetBase.Visible = False";
Debug.ShouldStop(128);
todoactivity.mostCurrent._taskslistgrp.runMethod(false,"_getbase").runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1067;BA.debugLine="Dim newListPNL As Panel";
Debug.ShouldStop(1024);
_newlistpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("newListPNL", _newlistpnl);
 BA.debugLineNum = 1068;BA.debugLine="newListPNL.Initialize(\"newListPNLGrp\")";
Debug.ShouldStop(2048);
_newlistpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("newListPNLGrp")));
 BA.debugLineNum = 1069;BA.debugLine="newListPNL.SetLayout(0, 0, 250dip, 120dip)";
Debug.ShouldStop(4096);
_newlistpnl.runVoidMethod ("SetLayout",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 1070;BA.debugLine="newListPNL.Color = Colors.Transparent";
Debug.ShouldStop(8192);
_newlistpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 1072;BA.debugLine="Dim newListET As EditText";
Debug.ShouldStop(32768);
_newlistet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("newListET", _newlistet);
 BA.debugLineNum = 1073;BA.debugLine="newListET.Initialize(\"newListETGrp\")";
Debug.ShouldStop(65536);
_newlistet.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("newListETGrp")));
 BA.debugLineNum = 1074;BA.debugLine="newListET.Hint = \"List name...\"";
Debug.ShouldStop(131072);
_newlistet.runMethod(true,"setHint",BA.ObjectToString("List name..."));
 BA.debugLineNum = 1076;BA.debugLine="Dim newListConfirmBtn As Button";
Debug.ShouldStop(524288);
_newlistconfirmbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("newListConfirmBtn", _newlistconfirmbtn);
 BA.debugLineNum = 1077;BA.debugLine="newListConfirmBtn.Initialize(\"newListConfirmBtnGr";
Debug.ShouldStop(1048576);
_newlistconfirmbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("newListConfirmBtnGrp")));
 BA.debugLineNum = 1078;BA.debugLine="newListConfirmBtn.Text = \"Create List\"";
Debug.ShouldStop(2097152);
_newlistconfirmbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Create List"));
 BA.debugLineNum = 1080;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(8388608);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 1081;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
Debug.ShouldStop(16777216);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 1082;BA.debugLine="newListConfirmBtn.Background = cd";
Debug.ShouldStop(33554432);
_newlistconfirmbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 1083;BA.debugLine="newListConfirmBtn.TextColor = Colors.White";
Debug.ShouldStop(67108864);
_newlistconfirmbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 1085;BA.debugLine="newListPNL.AddView(newListET, 0, 0, 250dip, 55dip";
Debug.ShouldStop(268435456);
_newlistpnl.runVoidMethod ("AddView",(Object)((_newlistet.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))));
 BA.debugLineNum = 1086;BA.debugLine="newListPNL.AddView(newListConfirmBtn, 0, 65dip, 2";
Debug.ShouldStop(536870912);
_newlistpnl.runVoidMethod ("AddView",(Object)((_newlistconfirmbtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 65)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 1088;BA.debugLine="newListConfirmBtn.Tag = newListET";
Debug.ShouldStop(-2147483648);
_newlistconfirmbtn.runMethod(false,"setTag",(_newlistet.getObject()));
 BA.debugLineNum = 1090;BA.debugLine="listsListGrp.Add(newListPNL, \"newListPanel\")";
Debug.ShouldStop(2);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _newlistpnl.getObject()),(Object)((RemoteObject.createImmutable("newListPanel"))));
 BA.debugLineNum = 1091;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _newlistconfirmbtngrp_click() throws Exception{
try {
		Debug.PushSubsStack("newListConfirmBtnGrp_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1093);
if (RapidSub.canDelegate("newlistconfirmbtngrp_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","newlistconfirmbtngrp_click");}
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _et = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _listname = RemoteObject.createImmutable("");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _listskey = RemoteObject.createImmutable("");
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _existing = RemoteObject.createImmutable("");
 BA.debugLineNum = 1093;BA.debugLine="Sub newListConfirmBtnGrp_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 1094;BA.debugLine="Dim btn As Button = Sender";
Debug.ShouldStop(32);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 1095;BA.debugLine="Dim et As EditText = btn.Tag";
Debug.ShouldStop(64);
_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
_et = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.EditTextWrapper"), _btn.runMethod(false,"getTag"));Debug.locals.put("et", _et);Debug.locals.put("et", _et);
 BA.debugLineNum = 1096;BA.debugLine="Dim listName As String = et.Text.Trim";
Debug.ShouldStop(128);
_listname = _et.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("listName", _listname);Debug.locals.put("listName", _listname);
 BA.debugLineNum = 1097;BA.debugLine="Dim code As String = groupET.Tag";
Debug.ShouldStop(256);
_code = BA.ObjectToString(todoactivity.mostCurrent._groupet.runMethod(false,"getTag"));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1099;BA.debugLine="If listName = \"\" Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",_listname,BA.ObjectToString(""))) { 
 BA.debugLineNum = 1100;BA.debugLine="MsgboxAsync(\"Please enter a list name.\", \"No nam";
Debug.ShouldStop(2048);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please enter a list name.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("No name"))),todoactivity.processBA);
 BA.debugLineNum = 1101;BA.debugLine="Return";
Debug.ShouldStop(4096);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1104;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
Debug.ShouldStop(32768);
_listskey = RemoteObject.concat(RemoteObject.createImmutable("group_lists_"),_code);Debug.locals.put("listsKey", _listskey);Debug.locals.put("listsKey", _listskey);
 BA.debugLineNum = 1105;BA.debugLine="Dim savedLists As List";
Debug.ShouldStop(65536);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 1106;BA.debugLine="savedLists.Initialize";
Debug.ShouldStop(131072);
_savedlists.runVoidMethod ("Initialize");
 BA.debugLineNum = 1107;BA.debugLine="If kvs.ContainsKey(listsKey) Then savedLists = kv";
Debug.ShouldStop(262144);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_listskey)).<Boolean>get().booleanValue()) { 
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_listskey)));Debug.locals.put("savedLists", _savedlists);};
 BA.debugLineNum = 1109;BA.debugLine="For Each existing As String In savedLists";
Debug.ShouldStop(1048576);
{
final RemoteObject group13 = _savedlists;
final int groupLen13 = group13.runMethod(true,"getSize").<Integer>get()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_existing = BA.ObjectToString(group13.runMethod(false,"Get",index13));Debug.locals.put("existing", _existing);
Debug.locals.put("existing", _existing);
 BA.debugLineNum = 1110;BA.debugLine="If existing = listName Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",_existing,_listname)) { 
 BA.debugLineNum = 1111;BA.debugLine="MsgboxAsync(\"A list with that name already exis";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("A list with that name already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate"))),todoactivity.processBA);
 BA.debugLineNum = 1112;BA.debugLine="Return";
Debug.ShouldStop(8388608);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("existing", _existing);
;
 BA.debugLineNum = 1116;BA.debugLine="savedLists.Add(listName)";
Debug.ShouldStop(134217728);
_savedlists.runVoidMethod ("Add",(Object)((_listname)));
 BA.debugLineNum = 1117;BA.debugLine="kvs.Put(listsKey, savedLists)";
Debug.ShouldStop(268435456);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_listskey),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 1120;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._listslistgrp.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 1121;BA.debugLine="listsListGrp.AddTextItem(listName, listName)";
Debug.ShouldStop(1);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_addtextitem",(Object)((_listname)),(Object)((_listname)));
 BA.debugLineNum = 1123;BA.debugLine="ToastMessageShow(\"List created\", False)";
Debug.ShouldStop(4);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("List created")),(Object)(todoactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 1124;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
 //BA.debugLineNum = 7;BA.debugLine="Private xui As XUI";
todoactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 8;BA.debugLine="Public kvs As KeyValueStore";
todoactivity._kvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _renamelistbtngrp_click() throws Exception{
try {
		Debug.PushSubsStack("renameListBtnGrp_Click (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1259);
if (RapidSub.canDelegate("renamelistbtngrp_click")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","renamelistbtngrp_click");}
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _index = RemoteObject.createImmutable(0);
RemoteObject _oldname = RemoteObject.createImmutable("");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _renameet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _newname = RemoteObject.createImmutable("");
RemoteObject _listskey = RemoteObject.createImmutable("");
RemoteObject _savedlists = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _existing = RemoteObject.createImmutable("");
RemoteObject _oldtaskkey = RemoteObject.createImmutable("");
RemoteObject _newtaskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject _oldck = RemoteObject.createImmutable("");
RemoteObject _newck = RemoteObject.createImmutable("");
RemoteObject _ln = RemoteObject.createImmutable("");
 BA.debugLineNum = 1259;BA.debugLine="Sub renameListBtnGrp_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 1260;BA.debugLine="Dim btn As Button = Sender";
Debug.ShouldStop(2048);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 1261;BA.debugLine="Dim ctx As List = btn.Tag";
Debug.ShouldStop(4096);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_ctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _btn.runMethod(false,"getTag"));Debug.locals.put("ctx", _ctx);Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1262;BA.debugLine="Dim Index As Int = ctx.Get(0)";
Debug.ShouldStop(8192);
_index = BA.numberCast(int.class, _ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("Index", _index);Debug.locals.put("Index", _index);
 BA.debugLineNum = 1263;BA.debugLine="Dim oldName As String = ctx.Get(1)";
Debug.ShouldStop(16384);
_oldname = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("oldName", _oldname);Debug.locals.put("oldName", _oldname);
 BA.debugLineNum = 1264;BA.debugLine="Dim code As String = ctx.Get(2)";
Debug.ShouldStop(32768);
_code = BA.ObjectToString(_ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 2))));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1265;BA.debugLine="Dim renameET As EditText = ctx.Get(3)";
Debug.ShouldStop(65536);
_renameet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
_renameet = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.EditTextWrapper"), _ctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 3))));Debug.locals.put("renameET", _renameet);Debug.locals.put("renameET", _renameet);
 BA.debugLineNum = 1266;BA.debugLine="Dim newName As String = renameET.Text.Trim";
Debug.ShouldStop(131072);
_newname = _renameet.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("newName", _newname);Debug.locals.put("newName", _newname);
 BA.debugLineNum = 1268;BA.debugLine="If newName = \"\" Or newName = oldName Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",_newname,BA.ObjectToString("")) || RemoteObject.solveBoolean("=",_newname,_oldname)) { 
 BA.debugLineNum = 1269;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._listslistgrp.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 1270;BA.debugLine="Return";
Debug.ShouldStop(2097152);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1273;BA.debugLine="Dim listsKey As String = \"group_lists_\" & code";
Debug.ShouldStop(16777216);
_listskey = RemoteObject.concat(RemoteObject.createImmutable("group_lists_"),_code);Debug.locals.put("listsKey", _listskey);Debug.locals.put("listsKey", _listskey);
 BA.debugLineNum = 1274;BA.debugLine="Dim savedLists As List = kvs.Get(listsKey)";
Debug.ShouldStop(33554432);
_savedlists = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedlists = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_listskey)));Debug.locals.put("savedLists", _savedlists);Debug.locals.put("savedLists", _savedlists);
 BA.debugLineNum = 1276;BA.debugLine="For Each existing As String In savedLists";
Debug.ShouldStop(134217728);
{
final RemoteObject group14 = _savedlists;
final int groupLen14 = group14.runMethod(true,"getSize").<Integer>get()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_existing = BA.ObjectToString(group14.runMethod(false,"Get",index14));Debug.locals.put("existing", _existing);
Debug.locals.put("existing", _existing);
 BA.debugLineNum = 1277;BA.debugLine="If existing = newName Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",_existing,_newname)) { 
 BA.debugLineNum = 1278;BA.debugLine="MsgboxAsync(\"Name already exists.\", \"Duplicate\"";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Name already exists.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Duplicate"))),todoactivity.processBA);
 BA.debugLineNum = 1279;BA.debugLine="Return";
Debug.ShouldStop(1073741824);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("existing", _existing);
;
 BA.debugLineNum = 1284;BA.debugLine="Dim oldTaskKey As String = \"group_list_\" & code &";
Debug.ShouldStop(8);
_oldtaskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_oldname);Debug.locals.put("oldTaskKey", _oldtaskkey);Debug.locals.put("oldTaskKey", _oldtaskkey);
 BA.debugLineNum = 1285;BA.debugLine="Dim newTaskKey As String = \"group_list_\" & code &";
Debug.ShouldStop(16);
_newtaskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_newname);Debug.locals.put("newTaskKey", _newtaskkey);Debug.locals.put("newTaskKey", _newtaskkey);
 BA.debugLineNum = 1286;BA.debugLine="If kvs.ContainsKey(oldTaskKey) Then";
Debug.ShouldStop(32);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_oldtaskkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1287;BA.debugLine="Dim savedTasks As List = kvs.Get(oldTaskKey)";
Debug.ShouldStop(64);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_oldtaskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1288;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(128);
{
final RemoteObject group24 = _savedtasks;
final int groupLen24 = group24.runMethod(true,"getSize").<Integer>get()
;int index24 = 0;
;
for (; index24 < groupLen24;index24++){
_task = BA.ObjectToString(group24.runMethod(false,"Get",index24));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 1289;BA.debugLine="Dim oldCK As String = \"group_checked_\" & code &";
Debug.ShouldStop(256);
_oldck = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_oldname,RemoteObject.createImmutable("_"),_task);Debug.locals.put("oldCK", _oldck);Debug.locals.put("oldCK", _oldck);
 BA.debugLineNum = 1290;BA.debugLine="Dim newCK As String = \"group_checked_\" & code &";
Debug.ShouldStop(512);
_newck = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_newname,RemoteObject.createImmutable("_"),_task);Debug.locals.put("newCK", _newck);Debug.locals.put("newCK", _newck);
 BA.debugLineNum = 1291;BA.debugLine="If kvs.ContainsKey(oldCK) Then";
Debug.ShouldStop(1024);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_oldck)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1292;BA.debugLine="kvs.Put(newCK, kvs.Get(oldCK))";
Debug.ShouldStop(2048);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_newck),(Object)(todoactivity._kvs.runMethod(false,"_get",(Object)(_oldck))));
 BA.debugLineNum = 1293;BA.debugLine="kvs.Remove(oldCK)";
Debug.ShouldStop(4096);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_oldck));
 };
 }
}Debug.locals.put("task", _task);
;
 BA.debugLineNum = 1296;BA.debugLine="kvs.Put(newTaskKey, savedTasks)";
Debug.ShouldStop(32768);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_newtaskkey),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 1297;BA.debugLine="kvs.Remove(oldTaskKey)";
Debug.ShouldStop(65536);
todoactivity._kvs.runVoidMethod ("_remove",(Object)(_oldtaskkey));
 };
 BA.debugLineNum = 1300;BA.debugLine="savedLists.Set(Index, newName)";
Debug.ShouldStop(524288);
_savedlists.runVoidMethod ("Set",(Object)(_index),(Object)((_newname)));
 BA.debugLineNum = 1301;BA.debugLine="kvs.Put(listsKey, savedLists)";
Debug.ShouldStop(1048576);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_listskey),(Object)((_savedlists.getObject())));
 BA.debugLineNum = 1303;BA.debugLine="listsListGrp.RemoveAt(listsListGrp.Size - 1)";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._listslistgrp.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 1304;BA.debugLine="listsListGrp.Clear";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_clear");
 BA.debugLineNum = 1305;BA.debugLine="For Each ln As String In savedLists";
Debug.ShouldStop(16777216);
{
final RemoteObject group39 = _savedlists;
final int groupLen39 = group39.runMethod(true,"getSize").<Integer>get()
;int index39 = 0;
;
for (; index39 < groupLen39;index39++){
_ln = BA.ObjectToString(group39.runMethod(false,"Get",index39));Debug.locals.put("ln", _ln);
Debug.locals.put("ln", _ln);
 BA.debugLineNum = 1306;BA.debugLine="listsListGrp.AddTextItem(ln, ln)";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_addtextitem",(Object)((_ln)),(Object)((_ln)));
 }
}Debug.locals.put("ln", _ln);
;
 BA.debugLineNum = 1309;BA.debugLine="If currentGrpListName = oldName Then currentGrpLi";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._currentgrplistname,_oldname)) { 
todoactivity.mostCurrent._currentgrplistname = _newname;};
 BA.debugLineNum = 1310;BA.debugLine="ToastMessageShow(\"List renamed\", False)";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("List renamed")),(Object)(todoactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 1311;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showcreategrouppanel() throws Exception{
try {
		Debug.PushSubsStack("showCreateGroupPanel (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,796);
if (RapidSub.canDelegate("showcreategrouppanel")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showcreategrouppanel");}
 BA.debugLineNum = 796;BA.debugLine="Sub showCreateGroupPanel";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 798;BA.debugLine="groupET.Text = \"\"";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._groupet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 799;BA.debugLine="groupET.Hint = \"Enter group name...\"";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._groupet.runMethod(true,"setHint",BA.ObjectToString("Enter group name..."));
 BA.debugLineNum = 800;BA.debugLine="groupET.Enabled = True";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 801;BA.debugLine="groupET.Tag = \"creating\"";
Debug.ShouldStop(1);
todoactivity.mostCurrent._groupet.runMethod(false,"setTag",RemoteObject.createImmutable(("creating")));
 BA.debugLineNum = 802;BA.debugLine="groupET.RequestFocus";
Debug.ShouldStop(2);
todoactivity.mostCurrent._groupet.runVoidMethod ("RequestFocus");
 BA.debugLineNum = 803;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showjoingrouppanel() throws Exception{
try {
		Debug.PushSubsStack("showJoinGroupPanel (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,875);
if (RapidSub.canDelegate("showjoingrouppanel")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showjoingrouppanel");}
RemoteObject _joinpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _joinet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _joinbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
 BA.debugLineNum = 875;BA.debugLine="Sub showJoinGroupPanel";
Debug.ShouldStop(1024);
 BA.debugLineNum = 877;BA.debugLine="Dim joinPNL As Panel";
Debug.ShouldStop(4096);
_joinpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("joinPNL", _joinpnl);
 BA.debugLineNum = 878;BA.debugLine="joinPNL.Initialize(\"joinPNL\")";
Debug.ShouldStop(8192);
_joinpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("joinPNL")));
 BA.debugLineNum = 879;BA.debugLine="joinPNL.SetLayout(0, 0, 250dip, 120dip)";
Debug.ShouldStop(16384);
_joinpnl.runVoidMethod ("SetLayout",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 880;BA.debugLine="joinPNL.Color = Colors.Transparent";
Debug.ShouldStop(32768);
_joinpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 882;BA.debugLine="Dim joinET As EditText";
Debug.ShouldStop(131072);
_joinet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("joinET", _joinet);
 BA.debugLineNum = 883;BA.debugLine="joinET.Initialize(\"joinET\")";
Debug.ShouldStop(262144);
_joinet.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("joinET")));
 BA.debugLineNum = 884;BA.debugLine="joinET.Hint = \"Enter 6-char group code...\"";
Debug.ShouldStop(524288);
_joinet.runMethod(true,"setHint",BA.ObjectToString("Enter 6-char group code..."));
 BA.debugLineNum = 886;BA.debugLine="Dim joinBtn As Button";
Debug.ShouldStop(2097152);
_joinbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("joinBtn", _joinbtn);
 BA.debugLineNum = 887;BA.debugLine="joinBtn.Initialize(\"joinBtn\")";
Debug.ShouldStop(4194304);
_joinbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("joinBtn")));
 BA.debugLineNum = 888;BA.debugLine="joinBtn.Text = \"Join Group\"";
Debug.ShouldStop(8388608);
_joinbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Join Group"));
 BA.debugLineNum = 890;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(33554432);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 891;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
Debug.ShouldStop(67108864);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 892;BA.debugLine="joinBtn.Background = cd";
Debug.ShouldStop(134217728);
_joinbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 893;BA.debugLine="joinBtn.TextColor = Colors.White";
Debug.ShouldStop(268435456);
_joinbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 895;BA.debugLine="joinPNL.AddView(joinET, 0, 0, 250dip, 55dip)";
Debug.ShouldStop(1073741824);
_joinpnl.runVoidMethod ("AddView",(Object)((_joinet.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))));
 BA.debugLineNum = 896;BA.debugLine="joinPNL.AddView(joinBtn, 0, 65dip, 250dip, 40dip)";
Debug.ShouldStop(-2147483648);
_joinpnl.runVoidMethod ("AddView",(Object)((_joinbtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 65)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 899;BA.debugLine="joinBtn.Tag = joinET";
Debug.ShouldStop(4);
_joinbtn.runMethod(false,"setTag",(_joinet.getObject()));
 BA.debugLineNum = 901;BA.debugLine="groupList.Add(joinPNL, \"joinPanel\")";
Debug.ShouldStop(16);
todoactivity.mostCurrent._grouplist.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _joinpnl.getObject()),(Object)((RemoteObject.createImmutable("joinPanel"))));
 BA.debugLineNum = 902;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showrenamegrouppanel(RemoteObject _index,RemoteObject _code) throws Exception{
try {
		Debug.PushSubsStack("showRenameGroupPanel (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1013);
if (RapidSub.canDelegate("showrenamegrouppanel")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showrenamegrouppanel", _index, _code);}
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("Index", _index);
Debug.locals.put("code", _code);
 BA.debugLineNum = 1013;BA.debugLine="Sub showRenameGroupPanel(Index As Int, code As Str";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 1014;BA.debugLine="groupET.Text = kvs.Get(\"group_name_\" & code)";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent._groupet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(todoactivity._kvs.runMethod(false,"_get",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_name_"),_code)))));
 BA.debugLineNum = 1015;BA.debugLine="groupET.Enabled = True";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._groupet.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 1016;BA.debugLine="groupET.RequestFocus";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._groupet.runVoidMethod ("RequestFocus");
 BA.debugLineNum = 1017;BA.debugLine="Dim ctx As List";
Debug.ShouldStop(16777216);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1018;BA.debugLine="ctx.Initialize";
Debug.ShouldStop(33554432);
_ctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 1019;BA.debugLine="ctx.Add(Index)";
Debug.ShouldStop(67108864);
_ctx.runVoidMethod ("Add",(Object)((_index)));
 BA.debugLineNum = 1020;BA.debugLine="ctx.Add(code)";
Debug.ShouldStop(134217728);
_ctx.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 1021;BA.debugLine="groupET.Tag = ctx   ' Tag is now a List, not \"cre";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._groupet.runMethod(false,"setTag",(_ctx.getObject()));
 BA.debugLineNum = 1022;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showrenamelistpanel(RemoteObject _index,RemoteObject _oldtitle) throws Exception{
try {
		Debug.PushSubsStack("showRenameListPanel (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,374);
if (RapidSub.canDelegate("showrenamelistpanel")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showrenamelistpanel", _index, _oldtitle);}
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("Index", _index);
Debug.locals.put("oldTitle", _oldtitle);
 BA.debugLineNum = 374;BA.debugLine="Sub showRenameListPanel(Index As Int, oldTitle As";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 376;BA.debugLine="addTitleTextArea.Text = oldTitle";
Debug.ShouldStop(8388608);
todoactivity.mostCurrent._addtitletextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_oldtitle));
 BA.debugLineNum = 377;BA.debugLine="addTitleTextArea.Visible = True";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setVisible",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 378;BA.debugLine="addTitleTextArea.Enabled = True";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._addtitletextarea.runMethod(true,"setEnabled",todoactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 379;BA.debugLine="addTitleTextArea.RequestFocus";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._addtitletextarea.runVoidMethod ("RequestFocus");
 BA.debugLineNum = 381;BA.debugLine="Dim ctx As List";
Debug.ShouldStop(268435456);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 382;BA.debugLine="ctx.Initialize";
Debug.ShouldStop(536870912);
_ctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 383;BA.debugLine="ctx.Add(Index)";
Debug.ShouldStop(1073741824);
_ctx.runVoidMethod ("Add",(Object)((_index)));
 BA.debugLineNum = 384;BA.debugLine="ctx.Add(oldTitle)";
Debug.ShouldStop(-2147483648);
_ctx.runVoidMethod ("Add",(Object)((_oldtitle)));
 BA.debugLineNum = 385;BA.debugLine="addTitleTextArea.Tag = ctx";
Debug.ShouldStop(1);
todoactivity.mostCurrent._addtitletextarea.runMethod(false,"setTag",(_ctx.getObject()));
 BA.debugLineNum = 387;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showrenamelistpanelgrp(RemoteObject _index,RemoteObject _oldname,RemoteObject _code) throws Exception{
try {
		Debug.PushSubsStack("showRenameListPanelGrp (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1226);
if (RapidSub.canDelegate("showrenamelistpanelgrp")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showrenamelistpanelgrp", _index, _oldname, _code);}
RemoteObject _renamepnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _renameet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _renamebtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("Index", _index);
Debug.locals.put("oldName", _oldname);
Debug.locals.put("code", _code);
 BA.debugLineNum = 1226;BA.debugLine="Sub showRenameListPanelGrp(Index As Int, oldName A";
Debug.ShouldStop(512);
 BA.debugLineNum = 1227;BA.debugLine="Dim renamePNL As Panel";
Debug.ShouldStop(1024);
_renamepnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("renamePNL", _renamepnl);
 BA.debugLineNum = 1228;BA.debugLine="renamePNL.Initialize(\"renameListPNLGrp\")";
Debug.ShouldStop(2048);
_renamepnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("renameListPNLGrp")));
 BA.debugLineNum = 1229;BA.debugLine="renamePNL.SetLayout(0, 0, 250dip, 120dip)";
Debug.ShouldStop(4096);
_renamepnl.runVoidMethod ("SetLayout",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 1230;BA.debugLine="renamePNL.Color = Colors.Transparent";
Debug.ShouldStop(8192);
_renamepnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 1232;BA.debugLine="Dim renameET As EditText";
Debug.ShouldStop(32768);
_renameet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("renameET", _renameet);
 BA.debugLineNum = 1233;BA.debugLine="renameET.Initialize(\"renameListETGrp\")";
Debug.ShouldStop(65536);
_renameet.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("renameListETGrp")));
 BA.debugLineNum = 1234;BA.debugLine="renameET.Text = oldName";
Debug.ShouldStop(131072);
_renameet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_oldname));
 BA.debugLineNum = 1236;BA.debugLine="Dim renameBtn As Button";
Debug.ShouldStop(524288);
_renamebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("renameBtn", _renamebtn);
 BA.debugLineNum = 1237;BA.debugLine="renameBtn.Initialize(\"renameListBtnGrp\")";
Debug.ShouldStop(1048576);
_renamebtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("renameListBtnGrp")));
 BA.debugLineNum = 1238;BA.debugLine="renameBtn.Text = \"Rename\"";
Debug.ShouldStop(2097152);
_renamebtn.runMethod(true,"setText",BA.ObjectToCharSequence("Rename"));
 BA.debugLineNum = 1240;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(8388608);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 1241;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
Debug.ShouldStop(16777216);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 1242;BA.debugLine="renameBtn.Background = cd";
Debug.ShouldStop(33554432);
_renamebtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 1243;BA.debugLine="renameBtn.TextColor = Colors.White";
Debug.ShouldStop(67108864);
_renamebtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 1245;BA.debugLine="Dim ctx As List";
Debug.ShouldStop(268435456);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1246;BA.debugLine="ctx.Initialize";
Debug.ShouldStop(536870912);
_ctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 1247;BA.debugLine="ctx.Add(Index)";
Debug.ShouldStop(1073741824);
_ctx.runVoidMethod ("Add",(Object)((_index)));
 BA.debugLineNum = 1248;BA.debugLine="ctx.Add(oldName)";
Debug.ShouldStop(-2147483648);
_ctx.runVoidMethod ("Add",(Object)((_oldname)));
 BA.debugLineNum = 1249;BA.debugLine="ctx.Add(code)";
Debug.ShouldStop(1);
_ctx.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 1250;BA.debugLine="ctx.Add(renameET)";
Debug.ShouldStop(2);
_ctx.runVoidMethod ("Add",(Object)((_renameet.getObject())));
 BA.debugLineNum = 1251;BA.debugLine="renameBtn.Tag = ctx";
Debug.ShouldStop(4);
_renamebtn.runMethod(false,"setTag",(_ctx.getObject()));
 BA.debugLineNum = 1253;BA.debugLine="renamePNL.AddView(renameET, 0, 0, 250dip, 55dip)";
Debug.ShouldStop(16);
_renamepnl.runVoidMethod ("AddView",(Object)((_renameet.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))));
 BA.debugLineNum = 1254;BA.debugLine="renamePNL.AddView(renameBtn, 0, 65dip, 250dip, 40";
Debug.ShouldStop(32);
_renamepnl.runVoidMethod ("AddView",(Object)((_renamebtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 65)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 1256;BA.debugLine="listsListGrp.Add(renamePNL, \"renameListPanel\")";
Debug.ShouldStop(128);
todoactivity.mostCurrent._listslistgrp.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _renamepnl.getObject()),(Object)((RemoteObject.createImmutable("renameListPanel"))));
 BA.debugLineNum = 1257;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showrenametaskpanel(RemoteObject _index,RemoteObject _oldtask) throws Exception{
try {
		Debug.PushSubsStack("showRenameTaskPanel (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,561);
if (RapidSub.canDelegate("showrenametaskpanel")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showrenametaskpanel", _index, _oldtask);}
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("Index", _index);
Debug.locals.put("oldTask", _oldtask);
 BA.debugLineNum = 561;BA.debugLine="Sub showRenameTaskPanel(Index As Int, oldTask As S";
Debug.ShouldStop(65536);
 BA.debugLineNum = 563;BA.debugLine="tasksList.RemoveAt(tasksList.Size - 1) ' remove \"";
Debug.ShouldStop(262144);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._taskslist.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 565;BA.debugLine="addTaskPanel.Initialize(\"addTaskPanel\")";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskPanel")));
 BA.debugLineNum = 566;BA.debugLine="addTaskPanel.SetLayout(10dip, 0, 240dip, 120dip)";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("SetLayout",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 567;BA.debugLine="addTaskPanel.Color = Colors.ARGB(0, 247, 247, 247";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 569;BA.debugLine="addTaskTextArea.Initialize(\"addTodoText\")";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._addtasktextarea.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTodoText")));
 BA.debugLineNum = 570;BA.debugLine="addTaskTextArea.Text = oldTask";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._addtasktextarea.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_oldtask));
 BA.debugLineNum = 571;BA.debugLine="Dim ctx As List";
Debug.ShouldStop(67108864);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 572;BA.debugLine="ctx.Initialize";
Debug.ShouldStop(134217728);
_ctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 573;BA.debugLine="ctx.Add(Index)";
Debug.ShouldStop(268435456);
_ctx.runVoidMethod ("Add",(Object)((_index)));
 BA.debugLineNum = 574;BA.debugLine="ctx.Add(oldTask)";
Debug.ShouldStop(536870912);
_ctx.runVoidMethod ("Add",(Object)((_oldtask)));
 BA.debugLineNum = 575;BA.debugLine="addTaskTextArea.Tag = ctx";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._addtasktextarea.runMethod(false,"setTag",(_ctx.getObject()));
 BA.debugLineNum = 577;BA.debugLine="enterTaskBtn.Initialize(\"enterTaskBtn\")";
Debug.ShouldStop(1);
todoactivity.mostCurrent._entertaskbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("enterTaskBtn")));
 BA.debugLineNum = 578;BA.debugLine="enterTaskBtn.Text = \"Rename task\"";
Debug.ShouldStop(2);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Rename task"));
 BA.debugLineNum = 580;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(8);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 581;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(16);
switch (BA.switchObjectToInt(todoactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 583;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(64);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 584;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
Debug.ShouldStop(128);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 585;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(256);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 586;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
Debug.ShouldStop(512);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 587;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 588;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 590;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
Debug.ShouldStop(8192);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17))));
 BA.debugLineNum = 591;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(16384);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 592;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
Debug.ShouldStop(32768);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 593;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(65536);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 594;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(131072);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 597;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(1048576);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 598;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
Debug.ShouldStop(2097152);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 599;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 600;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
Debug.ShouldStop(8388608);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 601;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(16777216);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 602;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(33554432);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 604;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17))));
 BA.debugLineNum = 605;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 606;BA.debugLine="cd.Initialize(Colors.ARGB(255, 137, 162, 185),";
Debug.ShouldStop(536870912);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 137)),(Object)(BA.numberCast(int.class, 162)),(Object)(BA.numberCast(int.class, 185)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 607;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(1073741824);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 608;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 break; }
case 2: {
 BA.debugLineNum = 611;BA.debugLine="addTaskTextArea.Typeface = pixeltf";
Debug.ShouldStop(4);
todoactivity.mostCurrent._addtasktextarea.runMethod(false,"setTypeface",(todoactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 612;BA.debugLine="enterTaskBtn.Typeface = pixeltf";
Debug.ShouldStop(8);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setTypeface",(todoactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 613;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(16);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 614;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 2";
Debug.ShouldStop(32);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247)),(Object)(BA.numberCast(int.class, 247))));
 BA.debugLineNum = 615;BA.debugLine="addTaskTextArea.TextColor = Colors.White";
Debug.ShouldStop(64);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 616;BA.debugLine="cd.Initialize(Colors.ARGB(120, 90, 105, 136),";
Debug.ShouldStop(128);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 90)),(Object)(BA.numberCast(int.class, 105)),(Object)(BA.numberCast(int.class, 136)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 617;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(256);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 618;BA.debugLine="enterTaskBtn.TextColor = Colors.White";
Debug.ShouldStop(512);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 620;BA.debugLine="addTaskTextArea.HintColor = Colors.ARGB(100, 1";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setHintColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17)),(Object)(BA.numberCast(int.class, 17))));
 BA.debugLineNum = 621;BA.debugLine="addTaskTextArea.TextColor = Colors.Black";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._addtasktextarea.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 622;BA.debugLine="cd.Initialize(Colors.ARGB(120, 184, 120, 46),";
Debug.ShouldStop(8192);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 184)),(Object)(BA.numberCast(int.class, 120)),(Object)(BA.numberCast(int.class, 46)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 623;BA.debugLine="enterTaskBtn.Background = cd";
Debug.ShouldStop(16384);
todoactivity.mostCurrent._entertaskbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 624;BA.debugLine="enterTaskBtn.TextColor = Colors.Black";
Debug.ShouldStop(32768);
todoactivity.mostCurrent._entertaskbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 break; }
}
;
 BA.debugLineNum = 628;BA.debugLine="addTaskPanel.AddView(addTaskTextArea, 0, 0, addTa";
Debug.ShouldStop(524288);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("AddView",(Object)((todoactivity.mostCurrent._addtasktextarea.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent._addtaskbtnpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 629;BA.debugLine="addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addT";
Debug.ShouldStop(1048576);
todoactivity.mostCurrent._addtaskpanel.runVoidMethod ("AddView",(Object)((todoactivity.mostCurrent._entertaskbtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(todoactivity.mostCurrent._addtaskbtnpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 631;BA.debugLine="tasksList.Add(addTaskPanel, addTaskPanel)";
Debug.ShouldStop(4194304);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), todoactivity.mostCurrent._addtaskpanel.getObject()),(Object)((todoactivity.mostCurrent._addtaskpanel.getObject())));
 BA.debugLineNum = 633;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showrenametaskpanelgrp(RemoteObject _index,RemoteObject _oldtask,RemoteObject _code,RemoteObject _currentgrplist) throws Exception{
try {
		Debug.PushSubsStack("showRenameTaskPanelGrp (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1472);
if (RapidSub.canDelegate("showrenametaskpanelgrp")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","showrenametaskpanelgrp", _index, _oldtask, _code, _currentgrplist);}
RemoteObject _addpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _addet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _confirmbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("Index", _index);
Debug.locals.put("oldTask", _oldtask);
Debug.locals.put("code", _code);
Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1472;BA.debugLine="Sub showRenameTaskPanelGrp(Index As Int, oldTask A";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 1473;BA.debugLine="tasksListGrp.RemoveAt(tasksListGrp.Size - 1)";
Debug.ShouldStop(1);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_removeat",(Object)(RemoteObject.solve(new RemoteObject[] {todoactivity.mostCurrent._taskslistgrp.runMethod(true,"_getsize"),RemoteObject.createImmutable(1)}, "-",1, 1)));
 BA.debugLineNum = 1475;BA.debugLine="Dim addPNL As Panel";
Debug.ShouldStop(4);
_addpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("addPNL", _addpnl);
 BA.debugLineNum = 1476;BA.debugLine="addPNL.Initialize(\"addTaskPNLGrp\")";
Debug.ShouldStop(8);
_addpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskPNLGrp")));
 BA.debugLineNum = 1477;BA.debugLine="addPNL.SetLayout(10dip, 0, 240dip, 120dip)";
Debug.ShouldStop(16);
_addpnl.runVoidMethod ("SetLayout",(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))));
 BA.debugLineNum = 1478;BA.debugLine="addPNL.Color = Colors.Transparent";
Debug.ShouldStop(32);
_addpnl.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 1480;BA.debugLine="Dim addET As EditText";
Debug.ShouldStop(128);
_addet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("addET", _addet);
 BA.debugLineNum = 1481;BA.debugLine="addET.Initialize(\"addTaskETGrp\")";
Debug.ShouldStop(256);
_addet.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addTaskETGrp")));
 BA.debugLineNum = 1482;BA.debugLine="addET.Text = oldTask";
Debug.ShouldStop(512);
_addet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_oldtask));
 BA.debugLineNum = 1484;BA.debugLine="Dim confirmBtn As Button";
Debug.ShouldStop(2048);
_confirmbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("confirmBtn", _confirmbtn);
 BA.debugLineNum = 1485;BA.debugLine="confirmBtn.Initialize(\"enterTaskBtnGrp\")";
Debug.ShouldStop(4096);
_confirmbtn.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("enterTaskBtnGrp")));
 BA.debugLineNum = 1486;BA.debugLine="confirmBtn.Text = \"Rename task\"";
Debug.ShouldStop(8192);
_confirmbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Rename task"));
 BA.debugLineNum = 1488;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(32768);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 1489;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)";
Debug.ShouldStop(65536);
_cd.runVoidMethod ("Initialize",(Object)(todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 1490;BA.debugLine="confirmBtn.Background = cd";
Debug.ShouldStop(131072);
_confirmbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 1491;BA.debugLine="confirmBtn.TextColor = Colors.White";
Debug.ShouldStop(262144);
_confirmbtn.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 1493;BA.debugLine="Dim ctx As List";
Debug.ShouldStop(1048576);
_ctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 1494;BA.debugLine="ctx.Initialize";
Debug.ShouldStop(2097152);
_ctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 1495;BA.debugLine="ctx.Add(code)";
Debug.ShouldStop(4194304);
_ctx.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 1496;BA.debugLine="ctx.Add(currentGrpList)";
Debug.ShouldStop(8388608);
_ctx.runVoidMethod ("Add",(Object)((_currentgrplist)));
 BA.debugLineNum = 1497;BA.debugLine="ctx.Add(addET)";
Debug.ShouldStop(16777216);
_ctx.runVoidMethod ("Add",(Object)((_addet.getObject())));
 BA.debugLineNum = 1498;BA.debugLine="ctx.Add(oldTask)    ' index 3 is not Null → renam";
Debug.ShouldStop(33554432);
_ctx.runVoidMethod ("Add",(Object)((_oldtask)));
 BA.debugLineNum = 1499;BA.debugLine="confirmBtn.Tag = ctx";
Debug.ShouldStop(67108864);
_confirmbtn.runMethod(false,"setTag",(_ctx.getObject()));
 BA.debugLineNum = 1501;BA.debugLine="addPNL.AddView(addET, 0, 0, 190dip, 60dip)";
Debug.ShouldStop(268435456);
_addpnl.runVoidMethod ("AddView",(Object)((_addet.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 190)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 1502;BA.debugLine="addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40di";
Debug.ShouldStop(536870912);
_addpnl.runVoidMethod ("AddView",(Object)((_confirmbtn.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 190)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 1504;BA.debugLine="tasksListGrp.Add(addPNL, addPNL)";
Debug.ShouldStop(-2147483648);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _addpnl.getObject()),(Object)((_addpnl.getObject())));
 BA.debugLineNum = 1505;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _taskcheckbox_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("taskCheckbox_CheckedChange (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,635);
if (RapidSub.canDelegate("taskcheckbox_checkedchange")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","taskcheckbox_checkedchange", _checked);}
RemoteObject _taskcheckbox = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
RemoteObject _tasklbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 635;BA.debugLine="Sub taskCheckbox_CheckedChange(Checked As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 637;BA.debugLine="Dim taskCheckbox As CheckBox = Sender";
Debug.ShouldStop(268435456);
_taskcheckbox = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
_taskcheckbox = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("taskCheckbox", _taskcheckbox);Debug.locals.put("taskCheckbox", _taskcheckbox);
 BA.debugLineNum = 638;BA.debugLine="Dim taskLBL As Label = taskCheckbox.Tag";
Debug.ShouldStop(536870912);
_tasklbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
_tasklbl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), _taskcheckbox.runMethod(false,"getTag"));Debug.locals.put("taskLBL", _tasklbl);Debug.locals.put("taskLBL", _tasklbl);
 BA.debugLineNum = 639;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(1073741824);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 640;BA.debugLine="If Checked Then";
Debug.ShouldStop(-2147483648);
if (_checked.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 641;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128,";
Debug.ShouldStop(1);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 128))));
 }else {
 BA.debugLineNum = 643;BA.debugLine="taskLBL.TextColor = Colors.White";
Debug.ShouldStop(4);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 }else {
 BA.debugLineNum = 646;BA.debugLine="If Checked Then";
Debug.ShouldStop(32);
if (_checked.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 647;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128,";
Debug.ShouldStop(64);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 128))));
 }else {
 BA.debugLineNum = 649;BA.debugLine="taskLBL.TextColor = Colors.Black";
Debug.ShouldStop(256);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 };
 BA.debugLineNum = 654;BA.debugLine="Dim key As String = \"checked_\" & currentList & \"_";
Debug.ShouldStop(8192);
_key = RemoteObject.concat(RemoteObject.createImmutable("checked_"),todoactivity.mostCurrent._currentlist,RemoteObject.createImmutable("_"),_tasklbl.runMethod(true,"getText"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 655;BA.debugLine="kvs.Put(key, Checked)";
Debug.ShouldStop(16384);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_key),(Object)((_checked)));
 BA.debugLineNum = 657;BA.debugLine="updateProgress";
Debug.ShouldStop(65536);
_updateprogress();
 BA.debugLineNum = 659;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _taskcheckboxgrp_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("taskCheckboxGrp_CheckedChange (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1550);
if (RapidSub.canDelegate("taskcheckboxgrp_checkedchange")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","taskcheckboxgrp_checkedchange", _checked);}
RemoteObject _cb = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
RemoteObject _cbctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _currentgrplist = RemoteObject.createImmutable("");
RemoteObject _tasklbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 1550;BA.debugLine="Sub taskCheckboxGrp_CheckedChange(Checked As Boole";
Debug.ShouldStop(8192);
 BA.debugLineNum = 1551;BA.debugLine="Dim cb As CheckBox = Sender";
Debug.ShouldStop(16384);
_cb = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
_cb = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper"), todoactivity.mostCurrent.__c.runMethod(false,"Sender",todoactivity.mostCurrent.activityBA));Debug.locals.put("cb", _cb);Debug.locals.put("cb", _cb);
 BA.debugLineNum = 1552;BA.debugLine="Dim cbCtx As List = cb.Tag";
Debug.ShouldStop(32768);
_cbctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_cbctx = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _cb.runMethod(false,"getTag"));Debug.locals.put("cbCtx", _cbctx);Debug.locals.put("cbCtx", _cbctx);
 BA.debugLineNum = 1553;BA.debugLine="Dim code As String = cbCtx.Get(0)";
Debug.ShouldStop(65536);
_code = BA.ObjectToString(_cbctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1554;BA.debugLine="Dim currentGrpList As String = cbCtx.Get(1)";
Debug.ShouldStop(131072);
_currentgrplist = BA.ObjectToString(_cbctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 1))));Debug.locals.put("currentGrpList", _currentgrplist);Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1555;BA.debugLine="Dim taskLBL As Label = cbCtx.Get(2)";
Debug.ShouldStop(262144);
_tasklbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
_tasklbl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), _cbctx.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 2))));Debug.locals.put("taskLBL", _tasklbl);Debug.locals.put("taskLBL", _tasklbl);
 BA.debugLineNum = 1557;BA.debugLine="If Checked Then";
Debug.ShouldStop(1048576);
if (_checked.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1558;BA.debugLine="taskLBL.TextColor = Colors.ARGB(255, 128, 128, 1";
Debug.ShouldStop(2097152);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 128))));
 }else {
 BA.debugLineNum = 1560;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(8388608);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1561;BA.debugLine="taskLBL.TextColor = Colors.White";
Debug.ShouldStop(16777216);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 1563;BA.debugLine="taskLBL.TextColor = Colors.Black";
Debug.ShouldStop(67108864);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 };
 BA.debugLineNum = 1567;BA.debugLine="Dim key As String = \"group_checked_\" & code & \"_\"";
Debug.ShouldStop(1073741824);
_key = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_currentgrplist,RemoteObject.createImmutable("_"),_tasklbl.runMethod(true,"getText"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 1568;BA.debugLine="kvs.Put(key, Checked)";
Debug.ShouldStop(-2147483648);
todoactivity._kvs.runVoidMethod ("_put",(Object)(_key),(Object)((_checked)));
 BA.debugLineNum = 1569;BA.debugLine="updateProgressGrp(code, currentGrpList)";
Debug.ShouldStop(1);
_updateprogressgrp(_code,_currentgrplist);
 BA.debugLineNum = 1570;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _taskslist_itemlongclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("tasksList_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,538);
if (RapidSub.canDelegate("taskslist_itemlongclick")) { b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","taskslist_itemlongclick", _index, _value); return;}
ResumableSub_tasksList_ItemLongClick rsub = new ResumableSub_tasksList_ItemLongClick(null,_index,_value);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_tasksList_ItemLongClick extends BA.ResumableSub {
public ResumableSub_tasksList_ItemLongClick(b4a.example.todoactivity parent,RemoteObject _index,RemoteObject _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.todoactivity parent;
RemoteObject _index;
RemoteObject _value;
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("tasksList_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,538);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 540;BA.debugLine="If Value = \"\" Then Return";
Debug.ShouldStop(134217728);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_value,RemoteObject.createImmutable(("")))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
 BA.debugLineNum = 542;BA.debugLine="Msgbox2Async(\"Delete or rename this task?\", Value";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Delete or rename this task?")),(Object)(BA.ObjectToCharSequence(_value)),(Object)(BA.ObjectToString("Rename")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Delete")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 543;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(1073741824);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "taskslist_itemlongclick"), null);
this.state = 13;
return;
case 13:
//C
this.state = 7;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 545;BA.debugLine="If res = DialogResponse.POSITIVE Then ' Rename";
Debug.ShouldStop(1);
if (true) break;

case 7:
//if
this.state = 12;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 9;
}else 
{ BA.debugLineNum = 548;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then ' Dele";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 11;
}}
if (true) break;

case 9:
//C
this.state = 12;
 BA.debugLineNum = 546;BA.debugLine="showRenameTaskPanel(Index, Value)";
Debug.ShouldStop(2);
_showrenametaskpanel(_index,BA.ObjectToString(_value));
 if (true) break;

case 11:
//C
this.state = 12;
 BA.debugLineNum = 549;BA.debugLine="Dim key As String = \"list_\" & currentList";
Debug.ShouldStop(16);
_key = RemoteObject.concat(RemoteObject.createImmutable("list_"),parent.mostCurrent._currentlist);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 550;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
Debug.ShouldStop(32);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 551;BA.debugLine="savedTasks.RemoveAt(Index)";
Debug.ShouldStop(64);
_savedtasks.runVoidMethod ("RemoveAt",(Object)(_index));
 BA.debugLineNum = 552;BA.debugLine="kvs.Put(key, savedTasks)";
Debug.ShouldStop(128);
parent._kvs.runVoidMethod ("_put",(Object)(_key),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 553;BA.debugLine="kvs.Remove(\"checked_\" & currentList & \"_\" & Valu";
Debug.ShouldStop(256);
parent._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("checked_"),parent.mostCurrent._currentlist,RemoteObject.createImmutable("_"),_value)));
 BA.debugLineNum = 554;BA.debugLine="tasksList.RemoveAt(Index)";
Debug.ShouldStop(512);
parent.mostCurrent._taskslist.runVoidMethod ("_removeat",(Object)(_index));
 BA.debugLineNum = 555;BA.debugLine="updateProgress";
Debug.ShouldStop(1024);
_updateprogress();
 BA.debugLineNum = 556;BA.debugLine="ToastMessageShow(\"Task deleted\", False)";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Task deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 12:
//C
this.state = -1;
;
 BA.debugLineNum = 559;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _taskslistgrp_itemlongclick(RemoteObject _index,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("tasksListGrp_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1446);
if (RapidSub.canDelegate("taskslistgrp_itemlongclick")) { b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","taskslistgrp_itemlongclick", _index, _value); return;}
ResumableSub_tasksListGrp_ItemLongClick rsub = new ResumableSub_tasksListGrp_ItemLongClick(null,_index,_value);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_tasksListGrp_ItemLongClick extends BA.ResumableSub {
public ResumableSub_tasksListGrp_ItemLongClick(b4a.example.todoactivity parent,RemoteObject _index,RemoteObject _value) {
this.parent = parent;
this._index = _index;
this._value = _value;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.todoactivity parent;
RemoteObject _index;
RemoteObject _value;
RemoteObject _code = RemoteObject.createImmutable("");
RemoteObject _currentgrplist = RemoteObject.createImmutable("");
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _taskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("tasksListGrp_ItemLongClick (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1446);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("Index", _index);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 1447;BA.debugLine="If Value = \"\" Then Return";
Debug.ShouldStop(64);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_value,RemoteObject.createImmutable(("")))) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
 BA.debugLineNum = 1449;BA.debugLine="Dim code As String = groupET.Tag";
Debug.ShouldStop(256);
_code = BA.ObjectToString(parent.mostCurrent._groupet.runMethod(false,"getTag"));Debug.locals.put("code", _code);Debug.locals.put("code", _code);
 BA.debugLineNum = 1451;BA.debugLine="Dim currentGrpList As String = getCurrentGrpList";
Debug.ShouldStop(1024);
_currentgrplist = _getcurrentgrplist();Debug.locals.put("currentGrpList", _currentgrplist);Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1453;BA.debugLine="Msgbox2Async(\"Delete or rename this task?\", Value";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Delete or rename this task?")),(Object)(BA.ObjectToCharSequence(_value)),(Object)(BA.ObjectToString("Rename")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Delete")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),todoactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 1454;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", todoactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "todoactivity", "taskslistgrp_itemlongclick"), null);
this.state = 13;
return;
case 13:
//C
this.state = 7;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 1456;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(32768);
if (true) break;

case 7:
//if
this.state = 12;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 9;
}else 
{ BA.debugLineNum = 1460;BA.debugLine="Else If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 11;
}}
if (true) break;

case 9:
//C
this.state = 12;
 BA.debugLineNum = 1458;BA.debugLine="showRenameTaskPanelGrp(Index, Value, code, curre";
Debug.ShouldStop(131072);
_showrenametaskpanelgrp(_index,BA.ObjectToString(_value),_code,_currentgrplist);
 if (true) break;

case 11:
//C
this.state = 12;
 BA.debugLineNum = 1461;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"";
Debug.ShouldStop(1048576);
_taskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_currentgrplist);Debug.locals.put("taskKey", _taskkey);Debug.locals.put("taskKey", _taskkey);
 BA.debugLineNum = 1462;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
Debug.ShouldStop(2097152);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), parent._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1463;BA.debugLine="savedTasks.RemoveAt(Index)";
Debug.ShouldStop(4194304);
_savedtasks.runVoidMethod ("RemoveAt",(Object)(_index));
 BA.debugLineNum = 1464;BA.debugLine="kvs.Put(taskKey, savedTasks)";
Debug.ShouldStop(8388608);
parent._kvs.runVoidMethod ("_put",(Object)(_taskkey),(Object)((_savedtasks.getObject())));
 BA.debugLineNum = 1465;BA.debugLine="kvs.Remove(\"group_checked_\" & code & \"_\" & curre";
Debug.ShouldStop(16777216);
parent._kvs.runVoidMethod ("_remove",(Object)(RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_currentgrplist,RemoteObject.createImmutable("_"),_value)));
 BA.debugLineNum = 1466;BA.debugLine="tasksListGrp.RemoveAt(Index)";
Debug.ShouldStop(33554432);
parent.mostCurrent._taskslistgrp.runVoidMethod ("_removeat",(Object)(_index));
 BA.debugLineNum = 1467;BA.debugLine="updateProgressGrp(code, currentGrpList)";
Debug.ShouldStop(67108864);
_updateprogressgrp(_code,_currentgrplist);
 BA.debugLineNum = 1468;BA.debugLine="ToastMessageShow(\"Task deleted\", False)";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Task deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 12:
//C
this.state = -1;
;
 BA.debugLineNum = 1470;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _taskslistgrpui(RemoteObject _newtask,RemoteObject _code,RemoteObject _currentgrplist) throws Exception{
try {
		Debug.PushSubsStack("tasksListGrpUI (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1507);
if (RapidSub.canDelegate("taskslistgrpui")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","taskslistgrpui", _newtask, _code, _currentgrplist);}
RemoteObject _taskpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _taskcheckbox = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
RemoteObject _tasklbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _divider = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cbctx = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _checkedkey = RemoteObject.createImmutable("");
RemoteObject _ischecked = RemoteObject.createImmutable(false);
Debug.locals.put("newTask", _newtask);
Debug.locals.put("code", _code);
Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1507;BA.debugLine="Sub tasksListGrpUI(newTask As String, code As Stri";
Debug.ShouldStop(4);
 BA.debugLineNum = 1508;BA.debugLine="Dim taskPNL As Panel";
Debug.ShouldStop(8);
_taskpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("taskPNL", _taskpnl);
 BA.debugLineNum = 1509;BA.debugLine="taskPNL.Initialize(\"taskPNLGrp\")";
Debug.ShouldStop(16);
_taskpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskPNLGrp")));
 BA.debugLineNum = 1510;BA.debugLine="taskPNL.SetLayout(0, 0, 250dip, 60dip)";
Debug.ShouldStop(32);
_taskpnl.runVoidMethod ("SetLayout",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 1512;BA.debugLine="Dim taskCheckbox As CheckBox";
Debug.ShouldStop(128);
_taskcheckbox = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");Debug.locals.put("taskCheckbox", _taskcheckbox);
 BA.debugLineNum = 1513;BA.debugLine="taskCheckbox.Initialize(\"taskCheckboxGrp\")";
Debug.ShouldStop(256);
_taskcheckbox.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskCheckboxGrp")));
 BA.debugLineNum = 1514;BA.debugLine="taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip,";
Debug.ShouldStop(512);
_taskpnl.runVoidMethod ("AddView",(Object)((_taskcheckbox.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 1516;BA.debugLine="Dim taskLBL As Label";
Debug.ShouldStop(2048);
_tasklbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("taskLBL", _tasklbl);
 BA.debugLineNum = 1517;BA.debugLine="taskLBL.Initialize(\"taskLBLGrp\")";
Debug.ShouldStop(4096);
_tasklbl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskLBLGrp")));
 BA.debugLineNum = 1518;BA.debugLine="taskLBL.Text = newTask";
Debug.ShouldStop(8192);
_tasklbl.runMethod(true,"setText",BA.ObjectToCharSequence(_newtask));
 BA.debugLineNum = 1519;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(16384);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1520;BA.debugLine="taskLBL.TextColor = Colors.White";
Debug.ShouldStop(32768);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 1522;BA.debugLine="taskLBL.TextColor = Colors.Black";
Debug.ShouldStop(131072);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 BA.debugLineNum = 1524;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.Wi";
Debug.ShouldStop(524288);
_taskpnl.runVoidMethod ("AddView",(Object)((_tasklbl.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(RemoteObject.solve(new RemoteObject[] {_taskpnl.runMethod(true,"getWidth"),todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))}, "-",1, 1)),(Object)(_taskpnl.runMethod(true,"getHeight")));
 BA.debugLineNum = 1526;BA.debugLine="Dim divider As Panel";
Debug.ShouldStop(2097152);
_divider = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("divider", _divider);
 BA.debugLineNum = 1527;BA.debugLine="divider.Initialize(\"line\")";
Debug.ShouldStop(4194304);
_divider.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("line")));
 BA.debugLineNum = 1528;BA.debugLine="divider.Color = Colors.ARGB(255, 60, 60, 60)";
Debug.ShouldStop(8388608);
_divider.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60))));
 BA.debugLineNum = 1529;BA.debugLine="taskPNL.AddView(divider, 0, 59dip, taskPNL.Width,";
Debug.ShouldStop(16777216);
_taskpnl.runVoidMethod ("AddView",(Object)((_divider.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 59)))),(Object)(_taskpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1)))));
 BA.debugLineNum = 1532;BA.debugLine="Dim cbCtx As List";
Debug.ShouldStop(134217728);
_cbctx = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("cbCtx", _cbctx);
 BA.debugLineNum = 1533;BA.debugLine="cbCtx.Initialize";
Debug.ShouldStop(268435456);
_cbctx.runVoidMethod ("Initialize");
 BA.debugLineNum = 1534;BA.debugLine="cbCtx.Add(code)";
Debug.ShouldStop(536870912);
_cbctx.runVoidMethod ("Add",(Object)((_code)));
 BA.debugLineNum = 1535;BA.debugLine="cbCtx.Add(currentGrpList)";
Debug.ShouldStop(1073741824);
_cbctx.runVoidMethod ("Add",(Object)((_currentgrplist)));
 BA.debugLineNum = 1536;BA.debugLine="cbCtx.Add(taskLBL)";
Debug.ShouldStop(-2147483648);
_cbctx.runVoidMethod ("Add",(Object)((_tasklbl.getObject())));
 BA.debugLineNum = 1537;BA.debugLine="taskCheckbox.Tag = cbCtx";
Debug.ShouldStop(1);
_taskcheckbox.runMethod(false,"setTag",(_cbctx.getObject()));
 BA.debugLineNum = 1540;BA.debugLine="Dim checkedKey As String = \"group_checked_\" & cod";
Debug.ShouldStop(8);
_checkedkey = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_currentgrplist,RemoteObject.createImmutable("_"),_newtask);Debug.locals.put("checkedKey", _checkedkey);Debug.locals.put("checkedKey", _checkedkey);
 BA.debugLineNum = 1541;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
Debug.ShouldStop(16);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_checkedkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1542;BA.debugLine="Dim isChecked As Boolean = kvs.Get(checkedKey)";
Debug.ShouldStop(32);
_ischecked = BA.ObjectToBoolean(todoactivity._kvs.runMethod(false,"_get",(Object)(_checkedkey)));Debug.locals.put("isChecked", _ischecked);Debug.locals.put("isChecked", _ischecked);
 BA.debugLineNum = 1543;BA.debugLine="taskCheckbox.Checked = isChecked";
Debug.ShouldStop(64);
_taskcheckbox.runMethodAndSync(true,"setChecked",_ischecked);
 BA.debugLineNum = 1544;BA.debugLine="If isChecked Then taskLBL.TextColor = Colors.ARG";
Debug.ShouldStop(128);
if (_ischecked.<Boolean>get().booleanValue()) { 
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0))));};
 };
 BA.debugLineNum = 1547;BA.debugLine="tasksListGrp.Add(taskPNL, newTask)";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._taskslistgrp.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _taskpnl.getObject()),(Object)((_newtask)));
 BA.debugLineNum = 1548;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _taskslistui(RemoteObject _newtask) throws Exception{
try {
		Debug.PushSubsStack("tasksListUI (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,661);
if (RapidSub.canDelegate("taskslistui")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","taskslistui", _newtask);}
RemoteObject _taskpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _taskcheckbox = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
RemoteObject _tasklbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _divider = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _checkedkey = RemoteObject.createImmutable("");
RemoteObject _ischecked = RemoteObject.createImmutable(false);
Debug.locals.put("newTask", _newtask);
 BA.debugLineNum = 661;BA.debugLine="Sub tasksListUI(newTask As String)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 663;BA.debugLine="Dim taskPNL As Panel";
Debug.ShouldStop(4194304);
_taskpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("taskPNL", _taskpnl);
 BA.debugLineNum = 664;BA.debugLine="taskPNL.Initialize(\"taskPNL\")";
Debug.ShouldStop(8388608);
_taskpnl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskPNL")));
 BA.debugLineNum = 665;BA.debugLine="taskPNL.SetLayout(0, 0, 250dip, 60dip)";
Debug.ShouldStop(16777216);
_taskpnl.runVoidMethod ("SetLayout",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 667;BA.debugLine="Dim taskCheckbox As CheckBox";
Debug.ShouldStop(67108864);
_taskcheckbox = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");Debug.locals.put("taskCheckbox", _taskcheckbox);
 BA.debugLineNum = 668;BA.debugLine="taskCheckbox.Initialize(\"taskCheckbox\")";
Debug.ShouldStop(134217728);
_taskcheckbox.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskCheckbox")));
 BA.debugLineNum = 669;BA.debugLine="taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip,";
Debug.ShouldStop(268435456);
_taskpnl.runVoidMethod ("AddView",(Object)((_taskcheckbox.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 671;BA.debugLine="Dim taskLBL As Label";
Debug.ShouldStop(1073741824);
_tasklbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("taskLBL", _tasklbl);
 BA.debugLineNum = 672;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(-2147483648);
if (todoactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 673;BA.debugLine="taskLBL.Initialize(\"taskLBL\")";
Debug.ShouldStop(1);
_tasklbl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskLBL")));
 BA.debugLineNum = 674;BA.debugLine="taskLBL.Text = newTask";
Debug.ShouldStop(2);
_tasklbl.runMethod(true,"setText",BA.ObjectToCharSequence(_newtask));
 BA.debugLineNum = 675;BA.debugLine="taskLBL.TextColor = Colors.White";
Debug.ShouldStop(4);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 676;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.W";
Debug.ShouldStop(8);
_taskpnl.runVoidMethod ("AddView",(Object)((_tasklbl.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(RemoteObject.solve(new RemoteObject[] {_taskpnl.runMethod(true,"getWidth"),todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))}, "-",1, 1)),(Object)(_taskpnl.runMethod(true,"getHeight")));
 }else {
 BA.debugLineNum = 678;BA.debugLine="taskLBL.Initialize(\"taskLBL\")";
Debug.ShouldStop(32);
_tasklbl.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("taskLBL")));
 BA.debugLineNum = 679;BA.debugLine="taskLBL.Text = newTask";
Debug.ShouldStop(64);
_tasklbl.runMethod(true,"setText",BA.ObjectToCharSequence(_newtask));
 BA.debugLineNum = 680;BA.debugLine="taskLBL.TextColor = Colors.Black";
Debug.ShouldStop(128);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 681;BA.debugLine="taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.W";
Debug.ShouldStop(256);
_taskpnl.runVoidMethod ("AddView",(Object)((_tasklbl.getObject())),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(RemoteObject.solve(new RemoteObject[] {_taskpnl.runMethod(true,"getWidth"),todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))}, "-",1, 1)),(Object)(_taskpnl.runMethod(true,"getHeight")));
 };
 BA.debugLineNum = 685;BA.debugLine="Dim divider As Panel";
Debug.ShouldStop(4096);
_divider = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("divider", _divider);
 BA.debugLineNum = 686;BA.debugLine="divider.Initialize(\"line\")";
Debug.ShouldStop(8192);
_divider.runVoidMethod ("Initialize",todoactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("line")));
 BA.debugLineNum = 687;BA.debugLine="divider.Color = Colors.ARGB(255, 60, 60, 60)";
Debug.ShouldStop(16384);
_divider.runVoidMethod ("setColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60))));
 BA.debugLineNum = 688;BA.debugLine="taskPNL.AddView(divider, 0, 59dip, taskPNL.Width,";
Debug.ShouldStop(32768);
_taskpnl.runVoidMethod ("AddView",(Object)((_divider.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 59)))),(Object)(_taskpnl.runMethod(true,"getWidth")),(Object)(todoactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1)))));
 BA.debugLineNum = 690;BA.debugLine="taskCheckbox.Tag = taskLBL";
Debug.ShouldStop(131072);
_taskcheckbox.runMethod(false,"setTag",(_tasklbl.getObject()));
 BA.debugLineNum = 693;BA.debugLine="Dim checkedKey As String = \"checked_\" & currentLi";
Debug.ShouldStop(1048576);
_checkedkey = RemoteObject.concat(RemoteObject.createImmutable("checked_"),todoactivity.mostCurrent._currentlist,RemoteObject.createImmutable("_"),_newtask);Debug.locals.put("checkedKey", _checkedkey);Debug.locals.put("checkedKey", _checkedkey);
 BA.debugLineNum = 694;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
Debug.ShouldStop(2097152);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_checkedkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 695;BA.debugLine="Dim isChecked As Boolean = kvs.Get(checkedKey)";
Debug.ShouldStop(4194304);
_ischecked = BA.ObjectToBoolean(todoactivity._kvs.runMethod(false,"_get",(Object)(_checkedkey)));Debug.locals.put("isChecked", _ischecked);Debug.locals.put("isChecked", _ischecked);
 BA.debugLineNum = 696;BA.debugLine="taskCheckbox.Checked = isChecked";
Debug.ShouldStop(8388608);
_taskcheckbox.runMethodAndSync(true,"setChecked",_ischecked);
 BA.debugLineNum = 697;BA.debugLine="If isChecked Then";
Debug.ShouldStop(16777216);
if (_ischecked.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 698;BA.debugLine="taskLBL.TextColor = Colors.ARGB(50, 0, 0, 0)";
Debug.ShouldStop(33554432);
_tasklbl.runMethod(true,"setTextColor",todoactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0))));
 };
 };
 BA.debugLineNum = 702;BA.debugLine="tasksList.Add(taskPNL, newTask)";
Debug.ShouldStop(536870912);
todoactivity.mostCurrent._taskslist.runVoidMethod ("_add",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _taskpnl.getObject()),(Object)((_newtask)));
 BA.debugLineNum = 704;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _updateprogress() throws Exception{
try {
		Debug.PushSubsStack("updateProgress (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,706);
if (RapidSub.canDelegate("updateprogress")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","updateprogress");}
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _totaltasks = RemoteObject.createImmutable(0);
RemoteObject _donetasks = RemoteObject.createImmutable(0);
RemoteObject _percentagetasks = RemoteObject.createImmutable(0);
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject _checkedkey = RemoteObject.createImmutable("");
 BA.debugLineNum = 706;BA.debugLine="Sub updateProgress";
Debug.ShouldStop(2);
 BA.debugLineNum = 708;BA.debugLine="If currentList = \"\" Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",todoactivity.mostCurrent._currentlist,BA.ObjectToString(""))) { 
 BA.debugLineNum = 709;BA.debugLine="progressNumber.Text = \"\"";
Debug.ShouldStop(16);
todoactivity.mostCurrent._progressnumber.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 710;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 713;BA.debugLine="Dim key As String = \"list_\" & currentList";
Debug.ShouldStop(256);
_key = RemoteObject.concat(RemoteObject.createImmutable("list_"),todoactivity.mostCurrent._currentlist);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 714;BA.debugLine="If kvs.ContainsKey(key) = False Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(true,"_containskey",(Object)(_key)),todoactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 715;BA.debugLine="progressNumber.Text = \"0 / 0 tasks done!\"";
Debug.ShouldStop(1024);
todoactivity.mostCurrent._progressnumber.runMethod(true,"setText",BA.ObjectToCharSequence("0 / 0 tasks done!"));
 BA.debugLineNum = 716;BA.debugLine="progressPercent.Text = \"0%\"";
Debug.ShouldStop(2048);
todoactivity.mostCurrent._progresspercent.runMethod(true,"setText",BA.ObjectToCharSequence("0%"));
 BA.debugLineNum = 717;BA.debugLine="progressBar.Progress = 0";
Debug.ShouldStop(4096);
todoactivity.mostCurrent._progressbar.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 BA.debugLineNum = 718;BA.debugLine="Return";
Debug.ShouldStop(8192);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 722;BA.debugLine="Dim savedTasks As List = kvs.Get(key)";
Debug.ShouldStop(131072);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_key)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 723;BA.debugLine="Dim totalTasks As Int = savedTasks.Size";
Debug.ShouldStop(262144);
_totaltasks = _savedtasks.runMethod(true,"getSize");Debug.locals.put("totalTasks", _totaltasks);Debug.locals.put("totalTasks", _totaltasks);
 BA.debugLineNum = 724;BA.debugLine="Dim doneTasks As Int = 0";
Debug.ShouldStop(524288);
_donetasks = BA.numberCast(int.class, 0);Debug.locals.put("doneTasks", _donetasks);Debug.locals.put("doneTasks", _donetasks);
 BA.debugLineNum = 725;BA.debugLine="Dim percentageTasks As Int = 0";
Debug.ShouldStop(1048576);
_percentagetasks = BA.numberCast(int.class, 0);Debug.locals.put("percentageTasks", _percentagetasks);Debug.locals.put("percentageTasks", _percentagetasks);
 BA.debugLineNum = 727;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(4194304);
{
final RemoteObject group16 = _savedtasks;
final int groupLen16 = group16.runMethod(true,"getSize").<Integer>get()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_task = BA.ObjectToString(group16.runMethod(false,"Get",index16));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 728;BA.debugLine="Dim checkedKey As String = \"checked_\" & currentL";
Debug.ShouldStop(8388608);
_checkedkey = RemoteObject.concat(RemoteObject.createImmutable("checked_"),todoactivity.mostCurrent._currentlist,RemoteObject.createImmutable("_"),_task);Debug.locals.put("checkedKey", _checkedkey);Debug.locals.put("checkedKey", _checkedkey);
 BA.debugLineNum = 729;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
Debug.ShouldStop(16777216);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_checkedkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 730;BA.debugLine="If kvs.Get(checkedKey) = True Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(false,"_get",(Object)(_checkedkey)),(todoactivity.mostCurrent.__c.getField(true,"True")))) { 
 BA.debugLineNum = 731;BA.debugLine="doneTasks = doneTasks + 1";
Debug.ShouldStop(67108864);
_donetasks = RemoteObject.solve(new RemoteObject[] {_donetasks,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("doneTasks", _donetasks);
 };
 };
 }
}Debug.locals.put("task", _task);
;
 BA.debugLineNum = 736;BA.debugLine="percentageTasks = (doneTasks / totalTasks) * 100";
Debug.ShouldStop(-2147483648);
_percentagetasks = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_donetasks,_totaltasks}, "/",0, 0)),RemoteObject.createImmutable(100)}, "*",0, 0));Debug.locals.put("percentageTasks", _percentagetasks);
 BA.debugLineNum = 738;BA.debugLine="progressNumber.Text = doneTasks & \" / \" & totalTa";
Debug.ShouldStop(2);
todoactivity.mostCurrent._progressnumber.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_donetasks,RemoteObject.createImmutable(" / "),_totaltasks,RemoteObject.createImmutable(" tasks done!"))));
 BA.debugLineNum = 739;BA.debugLine="progressPercent.Text = percentageTasks & \"%\"";
Debug.ShouldStop(4);
todoactivity.mostCurrent._progresspercent.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_percentagetasks,RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 740;BA.debugLine="progressBar.Progress = percentageTasks";
Debug.ShouldStop(8);
todoactivity.mostCurrent._progressbar.runMethod(true,"setProgress",_percentagetasks);
 BA.debugLineNum = 742;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _updateprogressgrp(RemoteObject _code,RemoteObject _currentgrplist) throws Exception{
try {
		Debug.PushSubsStack("updateProgressGrp (todoactivity) ","todoactivity",2,todoactivity.mostCurrent.activityBA,todoactivity.mostCurrent,1572);
if (RapidSub.canDelegate("updateprogressgrp")) { return b4a.example.todoactivity.remoteMe.runUserSub(false, "todoactivity","updateprogressgrp", _code, _currentgrplist);}
RemoteObject _taskkey = RemoteObject.createImmutable("");
RemoteObject _savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _totaltasks = RemoteObject.createImmutable(0);
RemoteObject _donetasks = RemoteObject.createImmutable(0);
RemoteObject _task = RemoteObject.createImmutable("");
RemoteObject _checkedkey = RemoteObject.createImmutable("");
RemoteObject _pct = RemoteObject.createImmutable(0);
Debug.locals.put("code", _code);
Debug.locals.put("currentGrpList", _currentgrplist);
 BA.debugLineNum = 1572;BA.debugLine="Sub updateProgressGrp(code As String, currentGrpLi";
Debug.ShouldStop(8);
 BA.debugLineNum = 1573;BA.debugLine="Dim taskKey As String = \"group_list_\" & code & \"_";
Debug.ShouldStop(16);
_taskkey = RemoteObject.concat(RemoteObject.createImmutable("group_list_"),_code,RemoteObject.createImmutable("_"),_currentgrplist);Debug.locals.put("taskKey", _taskkey);Debug.locals.put("taskKey", _taskkey);
 BA.debugLineNum = 1574;BA.debugLine="If kvs.ContainsKey(taskKey) = False Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(true,"_containskey",(Object)(_taskkey)),todoactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 1575;BA.debugLine="progressBarGrp.Progress = 0";
Debug.ShouldStop(64);
todoactivity.mostCurrent._progressbargrp.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 BA.debugLineNum = 1576;BA.debugLine="progressNumberGrp.Text = \"0 / 0 tasks done!\"";
Debug.ShouldStop(128);
todoactivity.mostCurrent._progressnumbergrp.runMethod(true,"setText",BA.ObjectToCharSequence("0 / 0 tasks done!"));
 BA.debugLineNum = 1577;BA.debugLine="progressPercentGrp.Text = \"0%\"";
Debug.ShouldStop(256);
todoactivity.mostCurrent._progresspercentgrp.runMethod(true,"setText",BA.ObjectToCharSequence("0%"));
 BA.debugLineNum = 1578;BA.debugLine="Return";
Debug.ShouldStop(512);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1581;BA.debugLine="Dim savedTasks As List = kvs.Get(taskKey)";
Debug.ShouldStop(4096);
_savedtasks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_savedtasks = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), todoactivity._kvs.runMethod(false,"_get",(Object)(_taskkey)));Debug.locals.put("savedTasks", _savedtasks);Debug.locals.put("savedTasks", _savedtasks);
 BA.debugLineNum = 1582;BA.debugLine="Dim totalTasks As Int = savedTasks.Size";
Debug.ShouldStop(8192);
_totaltasks = _savedtasks.runMethod(true,"getSize");Debug.locals.put("totalTasks", _totaltasks);Debug.locals.put("totalTasks", _totaltasks);
 BA.debugLineNum = 1583;BA.debugLine="Dim doneTasks As Int = 0";
Debug.ShouldStop(16384);
_donetasks = BA.numberCast(int.class, 0);Debug.locals.put("doneTasks", _donetasks);Debug.locals.put("doneTasks", _donetasks);
 BA.debugLineNum = 1585;BA.debugLine="For Each task As String In savedTasks";
Debug.ShouldStop(65536);
{
final RemoteObject group11 = _savedtasks;
final int groupLen11 = group11.runMethod(true,"getSize").<Integer>get()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_task = BA.ObjectToString(group11.runMethod(false,"Get",index11));Debug.locals.put("task", _task);
Debug.locals.put("task", _task);
 BA.debugLineNum = 1586;BA.debugLine="Dim checkedKey As String = \"group_checked_\" & co";
Debug.ShouldStop(131072);
_checkedkey = RemoteObject.concat(RemoteObject.createImmutable("group_checked_"),_code,RemoteObject.createImmutable("_"),_currentgrplist,RemoteObject.createImmutable("_"),_task);Debug.locals.put("checkedKey", _checkedkey);Debug.locals.put("checkedKey", _checkedkey);
 BA.debugLineNum = 1587;BA.debugLine="If kvs.ContainsKey(checkedKey) Then";
Debug.ShouldStop(262144);
if (todoactivity._kvs.runMethod(true,"_containskey",(Object)(_checkedkey)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1588;BA.debugLine="If kvs.Get(checkedKey) = True Then doneTasks =";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",todoactivity._kvs.runMethod(false,"_get",(Object)(_checkedkey)),(todoactivity.mostCurrent.__c.getField(true,"True")))) { 
_donetasks = RemoteObject.solve(new RemoteObject[] {_donetasks,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("doneTasks", _donetasks);};
 };
 }
}Debug.locals.put("task", _task);
;
 BA.debugLineNum = 1592;BA.debugLine="Dim pct As Int = 0";
Debug.ShouldStop(8388608);
_pct = BA.numberCast(int.class, 0);Debug.locals.put("pct", _pct);Debug.locals.put("pct", _pct);
 BA.debugLineNum = 1593;BA.debugLine="If totalTasks > 0 Then pct = (doneTasks / totalTa";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean(">",_totaltasks,BA.numberCast(double.class, 0))) { 
_pct = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_donetasks,_totaltasks}, "/",0, 0)),RemoteObject.createImmutable(100)}, "*",0, 0));Debug.locals.put("pct", _pct);};
 BA.debugLineNum = 1595;BA.debugLine="progressNumberGrp.Text = doneTasks & \" / \" & tota";
Debug.ShouldStop(67108864);
todoactivity.mostCurrent._progressnumbergrp.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_donetasks,RemoteObject.createImmutable(" / "),_totaltasks,RemoteObject.createImmutable(" tasks done!"))));
 BA.debugLineNum = 1596;BA.debugLine="progressPercentGrp.Text = pct & \"%\"";
Debug.ShouldStop(134217728);
todoactivity.mostCurrent._progresspercentgrp.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_pct,RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 1597;BA.debugLine="progressBarGrp.Progress = pct";
Debug.ShouldStop(268435456);
todoactivity.mostCurrent._progressbargrp.runMethod(true,"setProgress",_pct);
 BA.debugLineNum = 1598;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}