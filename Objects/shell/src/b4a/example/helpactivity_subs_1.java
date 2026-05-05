package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class helpactivity_subs_1 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,27);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 27;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 28;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(134217728);
switch (BA.switchObjectToInt(helpactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 30;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"helpAct\")";
Debug.ShouldStop(1073741824);
helpactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("helpAct")),helpactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"helpActDark\")";
Debug.ShouldStop(1);
helpactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("helpActDark")),helpactivity.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 36;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"helpAct2\")";
Debug.ShouldStop(16);
helpactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("helpAct2")),helpactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 39;BA.debugLine="Activity.LoadLayout(\"helpActDark2\")";
Debug.ShouldStop(64);
helpactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("helpActDark2")),helpactivity.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 42;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"helpAct3\")";
Debug.ShouldStop(1024);
helpactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("helpAct3")),helpactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 45;BA.debugLine="Activity.LoadLayout(\"helpActDark3\")";
Debug.ShouldStop(4096);
helpactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("helpActDark3")),helpactivity.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 49;BA.debugLine="showHelpPage(0)";
Debug.ShouldStop(65536);
_showhelppage(BA.numberCast(int.class, 0));
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
		Debug.PushSubsStack("Activity_Pause (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,56);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 56;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 58;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,52);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","activity_resume");}
 BA.debugLineNum = 52;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _backbtn_click() throws Exception{
try {
		Debug.PushSubsStack("backBtn_Click (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,231);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","backbtn_click");}
 BA.debugLineNum = 231;BA.debugLine="Sub backBtn_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 232;BA.debugLine="If helpPage > 0 Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean(">",helpactivity._helppage,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 233;BA.debugLine="showHelpPage(helpPage - 1)";
Debug.ShouldStop(256);
_showhelppage(RemoteObject.solve(new RemoteObject[] {helpactivity._helppage,RemoteObject.createImmutable(1)}, "-",1, 1));
 };
 BA.debugLineNum = 235;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _closehelp_click() throws Exception{
try {
		Debug.PushSubsStack("closeHelp_Click (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,237);
if (RapidSub.canDelegate("closehelp_click")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","closehelp_click");}
 BA.debugLineNum = 237;BA.debugLine="Sub closeHelp_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 238;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8192);
helpactivity.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 239;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
 //BA.debugLineNum = 17;BA.debugLine="Private titleLbl As Label";
helpactivity.mostCurrent._titlelbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private descriptionLbl As Label";
helpactivity.mostCurrent._descriptionlbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private backBtn As Button";
helpactivity.mostCurrent._backbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private nextBtn As Button";
helpactivity.mostCurrent._nextbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private helpimage As ImageView";
helpactivity.mostCurrent._helpimage = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private iconButton1 As ImageView";
helpactivity.mostCurrent._iconbutton1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private iconButton2 As ImageView";
helpactivity.mostCurrent._iconbutton2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private iconButton3 As ImageView";
helpactivity.mostCurrent._iconbutton3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _nextbtn_click() throws Exception{
try {
		Debug.PushSubsStack("nextBtn_Click (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,225);
if (RapidSub.canDelegate("nextbtn_click")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","nextbtn_click");}
 BA.debugLineNum = 225;BA.debugLine="Sub nextBtn_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 226;BA.debugLine="If helpPage < 10 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("<",helpactivity._helppage,BA.numberCast(double.class, 10))) { 
 BA.debugLineNum = 227;BA.debugLine="showHelpPage(helpPage + 1)";
Debug.ShouldStop(4);
_showhelppage(RemoteObject.solve(new RemoteObject[] {helpactivity._helppage,RemoteObject.createImmutable(1)}, "+",1, 1));
 };
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
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private xui As XUI";
helpactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 10;BA.debugLine="Dim helpPage As Int = 0";
helpactivity._helppage = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showhelppage(RemoteObject _page) throws Exception{
try {
		Debug.PushSubsStack("showHelpPage (helpactivity) ","helpactivity",4,helpactivity.mostCurrent.activityBA,helpactivity.mostCurrent,60);
if (RapidSub.canDelegate("showhelppage")) { return b4a.example.helpactivity.remoteMe.runUserSub(false, "helpactivity","showhelppage", _page);}
Debug.locals.put("page", _page);
 BA.debugLineNum = 60;BA.debugLine="Sub showHelpPage(page As Int)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 61;BA.debugLine="helpPage = page";
Debug.ShouldStop(268435456);
helpactivity._helppage = _page;
 BA.debugLineNum = 63;BA.debugLine="Select page";
Debug.ShouldStop(1073741824);
switch (BA.switchObjectToInt(_page,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7),BA.numberCast(int.class, 8),BA.numberCast(int.class, 9),BA.numberCast(int.class, 10))) {
case 0: {
 BA.debugLineNum = 65;BA.debugLine="titleLbl.Text = \"Welcome\"";
Debug.ShouldStop(1);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Welcome"));
 BA.debugLineNum = 66;BA.debugLine="descriptionLbl.Text = \"This app is tailor-made";
Debug.ShouldStop(2);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("This app is tailor-made for students—or anyone who wants to learn. It has all the features you'll need in order to learn effectively!"));
 BA.debugLineNum = 67;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 68;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(8);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("wreath.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 69;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(16);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("star.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 70;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(32);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("star.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 71;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(64);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("star.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 73;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(256);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dwreath.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 74;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(512);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dstar.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 75;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1024);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dstar.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 76;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2048);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dstar.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 1: {
 BA.debugLineNum = 79;BA.debugLine="titleLbl.Text = \"Calendar\"";
Debug.ShouldStop(16384);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Calendar"));
 BA.debugLineNum = 80;BA.debugLine="descriptionLbl.Text = \"The calendar comes in th";
Debug.ShouldStop(32768);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."));
 BA.debugLineNum = 81;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 82;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(131072);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("calendarui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 83;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(262144);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1249.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 84;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(524288);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1248.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 85;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1048576);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1247.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 87;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(4194304);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dcalendarui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 88;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(8388608);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1252.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 89;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(16777216);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1251.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 90;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(33554432);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1250.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 2: {
 BA.debugLineNum = 93;BA.debugLine="titleLbl.Text = \"Clock\"";
Debug.ShouldStop(268435456);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Clock"));
 BA.debugLineNum = 94;BA.debugLine="descriptionLbl.Text = \"The clock keeps you on t";
Debug.ShouldStop(536870912);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."));
 BA.debugLineNum = 95;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 96;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(-2147483648);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("clockui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 97;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1255.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 98;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1254.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 99;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1253.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 101;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(16);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dclockui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 102;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(32);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1258.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 103;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(64);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1257.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 104;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(128);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1256.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 3: {
 BA.debugLineNum = 107;BA.debugLine="titleLbl.Text = \"Corkboard\"";
Debug.ShouldStop(1024);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Corkboard"));
 BA.debugLineNum = 108;BA.debugLine="descriptionLbl.Text = \"The corkboard gives you";
Debug.ShouldStop(2048);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."));
 BA.debugLineNum = 109;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 110;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(8192);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("corkboardui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 111;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(16384);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1261.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 112;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(32768);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1260.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 113;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(65536);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1259.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 115;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(262144);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dcorkboardui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 116;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(524288);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1264.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 117;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1048576);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1263.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 118;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2097152);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1262.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 4: {
 BA.debugLineNum = 121;BA.debugLine="titleLbl.Text = \"Flashcards\"";
Debug.ShouldStop(16777216);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Flashcards"));
 BA.debugLineNum = 122;BA.debugLine="descriptionLbl.Text = \"The flashcard feature or";
Debug.ShouldStop(33554432);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."));
 BA.debugLineNum = 123;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 124;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(134217728);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("flashcardsui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 125;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(268435456);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1267.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 126;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(536870912);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1266.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 127;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1073741824);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1265.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 129;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(1);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dflashcardui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 130;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1270.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 131;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1269.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 132;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(8);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1268.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 5: {
 BA.debugLineNum = 135;BA.debugLine="titleLbl.Text = \"Music Player\"";
Debug.ShouldStop(64);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Music Player"));
 BA.debugLineNum = 136;BA.debugLine="descriptionLbl.Text = \"The music player plays t";
Debug.ShouldStop(128);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music."));
 BA.debugLineNum = 137;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 138;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(512);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("musicplayerui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 139;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1024);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1273.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 140;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2048);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1272.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 141;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4096);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1271.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 143;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(16384);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dmusicplayerui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 144;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(32768);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1276.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 145;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(65536);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1275.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 146;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(131072);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1274.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 6: {
 BA.debugLineNum = 149;BA.debugLine="titleLbl.Text = \"Notepad\"";
Debug.ShouldStop(1048576);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Notepad"));
 BA.debugLineNum = 150;BA.debugLine="descriptionLbl.Text = \"The notepad keeps all yo";
Debug.ShouldStop(2097152);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."));
 BA.debugLineNum = 151;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 152;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(8388608);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("notepadui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 153;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(16777216);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1279.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 154;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(33554432);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1278.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 155;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(67108864);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1277.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 157;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(268435456);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dnotepadui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 158;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(536870912);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1282.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 159;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1073741824);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1281.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 160;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(-2147483648);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1283.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 7: {
 BA.debugLineNum = 163;BA.debugLine="titleLbl.Text = \"To-do List\"";
Debug.ShouldStop(4);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("To-do List"));
 BA.debugLineNum = 164;BA.debugLine="descriptionLbl.Text = \"The to-do list enables y";
Debug.ShouldStop(8);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."));
 BA.debugLineNum = 165;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 166;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(32);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("todoui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 167;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(64);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1285.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 168;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(128);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1284.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 169;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(256);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1283.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 171;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(1024);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dtodoui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 172;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2048);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1288.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 173;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4096);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1287.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 174;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(8192);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1286.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 8: {
 BA.debugLineNum = 177;BA.debugLine="titleLbl.Text = \"Themes\"";
Debug.ShouldStop(65536);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Themes"));
 BA.debugLineNum = 178;BA.debugLine="descriptionLbl.Text = \"Themes let you put your";
Debug.ShouldStop(131072);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
 BA.debugLineNum = 179;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 180;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(524288);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("themesui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 181;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(1048576);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1291.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 182;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2097152);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1290.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 183;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4194304);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1289.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 185;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(16777216);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dthemesui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 186;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(33554432);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1294.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 187;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(67108864);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1293.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 188;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(134217728);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1292.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 9: {
 BA.debugLineNum = 191;BA.debugLine="titleLbl.Text = \"Lamp\"";
Debug.ShouldStop(1073741824);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Lamp"));
 BA.debugLineNum = 192;BA.debugLine="descriptionLbl.Text = \"The lamp gives you contr";
Debug.ShouldStop(-2147483648);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
 BA.debugLineNum = 193;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 194;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(2);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("lampui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 195;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1297.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 196;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(8);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1296.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 197;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(16);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1295.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 199;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(64);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dlampui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 200;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(128);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1298.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 201;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(256);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1299.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 202;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(512);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("IMG_1300.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
case 10: {
 BA.debugLineNum = 205;BA.debugLine="titleLbl.Text = \"Navigation\"";
Debug.ShouldStop(4096);
helpactivity.mostCurrent._titlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Navigation"));
 BA.debugLineNum = 206;BA.debugLine="descriptionLbl.Text = \"Navigation is your home";
Debug.ShouldStop(8192);
helpactivity.mostCurrent._descriptionlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
 BA.debugLineNum = 207;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",helpactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,helpactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 208;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(32768);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("navigationui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 209;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(65536);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("star.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 210;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(131072);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("Navbtn.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 211;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(262144);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("star.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 }else {
 BA.debugLineNum = 213;BA.debugLine="helpimage.Bitmap = xui.LoadBitmapResize(File.D";
Debug.ShouldStop(1048576);
helpactivity.mostCurrent._helpimage.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dnavigationui.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 214;BA.debugLine="iconButton1.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(2097152);
helpactivity.mostCurrent._iconbutton1.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dstar.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 215;BA.debugLine="iconButton2.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(4194304);
helpactivity.mostCurrent._iconbutton2.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("Navbtn.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 216;BA.debugLine="iconButton3.Bitmap = xui.LoadBitmapResize(File";
Debug.ShouldStop(8388608);
helpactivity.mostCurrent._iconbutton3.runMethod(false,"setBitmap",(helpactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(helpactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dstar.png")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getWidth")),(Object)(helpactivity.mostCurrent._helpimage.runMethod(true,"getHeight")),(Object)(helpactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 };
 break; }
}
;
 BA.debugLineNum = 221;BA.debugLine="backBtn.Enabled = (page > 0)";
Debug.ShouldStop(268435456);
helpactivity.mostCurrent._backbtn.runMethod(true,"setEnabled",BA.ObjectToBoolean((RemoteObject.solveBoolean(">",_page,BA.numberCast(double.class, 0)))));
 BA.debugLineNum = 222;BA.debugLine="nextBtn.Enabled = (page < 10)";
Debug.ShouldStop(536870912);
helpactivity.mostCurrent._nextbtn.runMethod(true,"setEnabled",BA.ObjectToBoolean((RemoteObject.solveBoolean("<",_page,BA.numberCast(double.class, 10)))));
 BA.debugLineNum = 223;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}