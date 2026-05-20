package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class mainactivity_subs_0 {


public static void  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,34);
if (RapidSub.canDelegate("activity_create")) { b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","activity_create", _firsttime); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.mainactivity parent,RemoteObject _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.mainactivity parent;
RemoteObject _firsttime;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,34);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"Layouthsv\")";
Debug.ShouldStop(4);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layouthsv")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 36;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(8);
if (true) break;

case 1:
//if
this.state = 4;
if (_firsttime.<Boolean>get().booleanValue()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 37;BA.debugLine="kvs = Starter.notesKvs";
Debug.ShouldStop(16);
parent._kvs = parent.mostCurrent._starter._noteskvs /*RemoteObject*/ ;
 BA.debugLineNum = 38;BA.debugLine="kvsPref = Starter.prefKvs";
Debug.ShouldStop(32);
parent._kvspref = parent.mostCurrent._starter._prefkvs /*RemoteObject*/ ;
 BA.debugLineNum = 39;BA.debugLine="timerClock.Initialize(\"timerClock\", 1000)";
Debug.ShouldStop(64);
parent._timerclock.runVoidMethod ("Initialize",mainactivity.processBA,(Object)(BA.ObjectToString("timerClock")),(Object)(BA.numberCast(long.class, 1000)));
 BA.debugLineNum = 40;BA.debugLine="timerClock.Enabled = True";
Debug.ShouldStop(128);
parent._timerclock.runMethod(true,"setEnabled",parent.mostCurrent.__c.getField(true,"True"));
 if (true) break;

case 4:
//C
this.state = 5;
;
 BA.debugLineNum = 43;BA.debugLine="hsv.Panel.Width = size";
Debug.ShouldStop(1024);
parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"setWidth",parent._size);
 BA.debugLineNum = 44;BA.debugLine="hsv.Panel.Height = size";
Debug.ShouldStop(2048);
parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"setHeight",parent._size);
 BA.debugLineNum = 46;BA.debugLine="regLayout = xui.CreatePanel(\"\")";
Debug.ShouldStop(8192);
parent.mostCurrent._reglayout = parent._xui.runMethod(false,"CreatePanel",mainactivity.processBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 47;BA.debugLine="darkModeLayout = xui.CreatePanel(\"\")";
Debug.ShouldStop(16384);
parent.mostCurrent._darkmodelayout = parent._xui.runMethod(false,"CreatePanel",mainactivity.processBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 49;BA.debugLine="hsv.Panel.AddView(regLayout, 0, 0, hsv.Panel.Widt";
Debug.ShouldStop(65536);
parent.mostCurrent._hsv.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((parent.mostCurrent._reglayout.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"getWidth")),(Object)(parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"getHeight")));
 BA.debugLineNum = 50;BA.debugLine="hsv.Panel.AddView(darkModeLayout, 0, 0, hsv.Panel";
Debug.ShouldStop(131072);
parent.mostCurrent._hsv.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((parent.mostCurrent._darkmodelayout.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"getWidth")),(Object)(parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"getHeight")));
 BA.debugLineNum = 52;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(524288);
if (true) break;

case 5:
//select
this.state = 12;
switch (BA.switchObjectToInt(parent.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
this.state = 7;
if (true) break;
}
case 1: {
this.state = 9;
if (true) break;
}
case 2: {
this.state = 11;
if (true) break;
}
}
if (true) break;

case 7:
//C
this.state = 12;
 BA.debugLineNum = 54;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
Debug.ShouldStop(2097152);
parent.mostCurrent._reglayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 55;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
Debug.ShouldStop(4194304);
parent.mostCurrent._darkmodelayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout2")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 56;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnComputer";
Debug.ShouldStop(8388608);
parent.mostCurrent._computergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("BtnComputer.GIF")));
 BA.debugLineNum = 57;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtncom";
Debug.ShouldStop(16777216);
parent.mostCurrent._dcomputergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("darkbtncomputer.GIF")));
 if (true) break;

case 9:
//C
this.state = 12;
 BA.debugLineNum = 59;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
Debug.ShouldStop(67108864);
parent.mostCurrent._reglayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout3")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 60;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
Debug.ShouldStop(134217728);
parent.mostCurrent._darkmodelayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout4")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 61;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.G";
Debug.ShouldStop(268435456);
parent.mostCurrent._computergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("mikucomp2.GIF")));
 BA.debugLineNum = 62;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GIF";
Debug.ShouldStop(536870912);
parent.mostCurrent._dcomputergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DComp2.GIF")));
 if (true) break;

case 11:
//C
this.state = 12;
 BA.debugLineNum = 64;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._reglayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout5")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 65;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
Debug.ShouldStop(1);
parent.mostCurrent._darkmodelayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout6")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 66;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\")";
Debug.ShouldStop(2);
parent.mostCurrent._computergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Comp3.GIF")));
 BA.debugLineNum = 67;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GIF";
Debug.ShouldStop(4);
parent.mostCurrent._dcomputergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DComp3.GIF")));
 BA.debugLineNum = 68;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
Debug.ShouldStop(8);
parent.mostCurrent._curtain.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Curtain.GIF")));
 BA.debugLineNum = 69;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\")";
Debug.ShouldStop(16);
parent.mostCurrent._dcurtain.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DCurtain.GIF")));
 if (true) break;
;
 BA.debugLineNum = 72;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(128);

case 12:
//if
this.state = 17;
if (parent.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 17;
 BA.debugLineNum = 73;BA.debugLine="darkModeLayout.Visible = True";
Debug.ShouldStop(256);
parent.mostCurrent._darkmodelayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 74;BA.debugLine="darkModeLayout.BringToFront";
Debug.ShouldStop(512);
parent.mostCurrent._darkmodelayout.runVoidMethod ("BringToFront");
 BA.debugLineNum = 75;BA.debugLine="regLayout.Visible = False";
Debug.ShouldStop(1024);
parent.mostCurrent._reglayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 if (true) break;

case 16:
//C
this.state = 17;
 BA.debugLineNum = 77;BA.debugLine="darkModeLayout.Visible = False";
Debug.ShouldStop(4096);
parent.mostCurrent._darkmodelayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 78;BA.debugLine="regLayout.BringToFront";
Debug.ShouldStop(8192);
parent.mostCurrent._reglayout.runVoidMethod ("BringToFront");
 if (true) break;

case 17:
//C
this.state = -1;
;
 BA.debugLineNum = 81;BA.debugLine="Sleep(50)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("Sleep",mainactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "mainactivity", "activity_create"),BA.numberCast(int.class, 50));
this.state = 18;
return;
case 18:
//C
this.state = -1;
;
 BA.debugLineNum = 82;BA.debugLine="hsv.ScrollPosition = Max(0, (hsv.Panel.Width - 10";
Debug.ShouldStop(131072);
parent.mostCurrent._hsv.runMethod(true,"setScrollPosition",BA.numberCast(int.class, parent.mostCurrent.__c.runMethod(true,"Max",(Object)(BA.numberCast(double.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {parent.mostCurrent._hsv.runMethod(false,"getPanel").runMethod(true,"getWidth"),parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),mainactivity.mostCurrent.activityBA)}, "-",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0)))));
 BA.debugLineNum = 84;BA.debugLine="End Sub";
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
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,121);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 121;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16777216);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,86);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","activity_resume");}
 BA.debugLineNum = 86;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 88;BA.debugLine="If format24h Then";
Debug.ShouldStop(8388608);
if (mainactivity._format24h.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 89;BA.debugLine="DateTime.TimeFormat = \"HH:mm\" ' 24-Hour Format";
Debug.ShouldStop(16777216);
mainactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setTimeFormat",BA.ObjectToString("HH:mm"));
 }else {
 BA.debugLineNum = 91;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\" ' AM/PM Format";
Debug.ShouldStop(67108864);
mainactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setTimeFormat",BA.ObjectToString("hh:mm a"));
 };
 BA.debugLineNum = 94;BA.debugLine="If Starter.themeChanged Then";
Debug.ShouldStop(536870912);
if (mainactivity.mostCurrent._starter._themechanged /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 95;BA.debugLine="regLayout.RemoveAllViews";
Debug.ShouldStop(1073741824);
mainactivity.mostCurrent._reglayout.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 96;BA.debugLine="darkModeLayout.RemoveAllViews";
Debug.ShouldStop(-2147483648);
mainactivity.mostCurrent._darkmodelayout.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 98;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(2);
switch (BA.switchObjectToInt(mainactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 100;BA.debugLine="regLayout.LoadLayout(\"Layout\")";
Debug.ShouldStop(8);
mainactivity.mostCurrent._reglayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 101;BA.debugLine="darkModeLayout.LoadLayout(\"Layout2\")";
Debug.ShouldStop(16);
mainactivity.mostCurrent._darkmodelayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout2")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 102;BA.debugLine="computerGif.SetGif(File.DirAssets, \"BtnCompute";
Debug.ShouldStop(32);
mainactivity.mostCurrent._computergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("BtnComputer.GIF")));
 BA.debugLineNum = 103;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"darkbtnCo";
Debug.ShouldStop(64);
mainactivity.mostCurrent._dcomputergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("darkbtnComputer.GIF")));
 break; }
case 1: {
 BA.debugLineNum = 105;BA.debugLine="regLayout.LoadLayout(\"Layout3\")";
Debug.ShouldStop(256);
mainactivity.mostCurrent._reglayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout3")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 106;BA.debugLine="darkModeLayout.LoadLayout(\"Layout4\")";
Debug.ShouldStop(512);
mainactivity.mostCurrent._darkmodelayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout4")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 107;BA.debugLine="computerGif.SetGif(File.DirAssets, \"mikucomp2.";
Debug.ShouldStop(1024);
mainactivity.mostCurrent._computergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("mikucomp2.GIF")));
 BA.debugLineNum = 108;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp2.GI";
Debug.ShouldStop(2048);
mainactivity.mostCurrent._dcomputergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DComp2.GIF")));
 break; }
case 2: {
 BA.debugLineNum = 110;BA.debugLine="regLayout.LoadLayout(\"Layout5\")";
Debug.ShouldStop(8192);
mainactivity.mostCurrent._reglayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout5")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 111;BA.debugLine="darkModeLayout.LoadLayout(\"Layout6\")";
Debug.ShouldStop(16384);
mainactivity.mostCurrent._darkmodelayout.runVoidMethodAndSync ("LoadLayout",(Object)(RemoteObject.createImmutable("Layout6")),mainactivity.mostCurrent.activityBA);
 BA.debugLineNum = 112;BA.debugLine="computerGif.SetGif(File.DirAssets, \"Comp3.GIF\"";
Debug.ShouldStop(32768);
mainactivity.mostCurrent._computergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Comp3.GIF")));
 BA.debugLineNum = 113;BA.debugLine="dcomputerGif.SetGif(File.DirAssets, \"DComp3.GI";
Debug.ShouldStop(65536);
mainactivity.mostCurrent._dcomputergif.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DComp3.GIF")));
 BA.debugLineNum = 114;BA.debugLine="curtain.SetGif(File.DirAssets, \"Curtain.GIF\")";
Debug.ShouldStop(131072);
mainactivity.mostCurrent._curtain.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Curtain.GIF")));
 BA.debugLineNum = 115;BA.debugLine="dCurtain.SetGif(File.DirAssets, \"DCurtain.GIF\"";
Debug.ShouldStop(262144);
mainactivity.mostCurrent._dcurtain.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(mainactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DCurtain.GIF")));
 break; }
}
;
 BA.debugLineNum = 117;BA.debugLine="Starter.themeChanged = False";
Debug.ShouldStop(1048576);
mainactivity.mostCurrent._starter._themechanged /*RemoteObject*/  = mainactivity.mostCurrent.__c.getField(true,"False");
 };
 BA.debugLineNum = 119;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _bookie_click() throws Exception{
try {
		Debug.PushSubsStack("bookie_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,313);
if (RapidSub.canDelegate("bookie_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","bookie_click");}
 BA.debugLineNum = 313;BA.debugLine="Private Sub bookie_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 314;BA.debugLine="StartActivity(FlashcardActivity)";
Debug.ShouldStop(33554432);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._flashcardactivity.getObject())));
 BA.debugLineNum = 315;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _bookie_longclick() throws Exception{
try {
		Debug.PushSubsStack("bookie_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,420);
if (RapidSub.canDelegate("bookie_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","bookie_longclick");}
 BA.debugLineNum = 420;BA.debugLine="Private Sub bookie_LongClick";
Debug.ShouldStop(8);
 BA.debugLineNum = 421;BA.debugLine="showInfoPopup";
Debug.ShouldStop(16);
_showinfopopup();
 BA.debugLineNum = 422;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 423;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(64);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 424;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(128);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 425;BA.debugLine="showInfoPage(3)";
Debug.ShouldStop(256);
_showinfopage(BA.numberCast(int.class, 3));
 BA.debugLineNum = 426;BA.debugLine="Return";
Debug.ShouldStop(512);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 428;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _calendar_click() throws Exception{
try {
		Debug.PushSubsStack("calendar_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,317);
if (RapidSub.canDelegate("calendar_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","calendar_click");}
 BA.debugLineNum = 317;BA.debugLine="Private Sub calendar_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 318;BA.debugLine="StartActivity(CalendarActivity)";
Debug.ShouldStop(536870912);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._calendaractivity.getObject())));
 BA.debugLineNum = 319;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _calendar_longclick() throws Exception{
try {
		Debug.PushSubsStack("calendar_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,380);
if (RapidSub.canDelegate("calendar_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","calendar_longclick");}
 BA.debugLineNum = 380;BA.debugLine="Private Sub calendar_LongClick";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 381;BA.debugLine="showInfoPopup";
Debug.ShouldStop(268435456);
_showinfopopup();
 BA.debugLineNum = 382;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 383;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(1073741824);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 384;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(-2147483648);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 385;BA.debugLine="showInfoPage(0)";
Debug.ShouldStop(1);
_showinfopage(BA.numberCast(int.class, 0));
 BA.debugLineNum = 386;BA.debugLine="Return";
Debug.ShouldStop(2);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 388;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _clockbtn_click() throws Exception{
try {
		Debug.PushSubsStack("clockBtn_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,281);
if (RapidSub.canDelegate("clockbtn_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","clockbtn_click");}
 BA.debugLineNum = 281;BA.debugLine="Private Sub clockBtn_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 282;BA.debugLine="StartActivity(clockActivity)";
Debug.ShouldStop(33554432);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._clockactivity.getObject())));
 BA.debugLineNum = 283;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _clockbtn_longclick() throws Exception{
try {
		Debug.PushSubsStack("clockBtn_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,390);
if (RapidSub.canDelegate("clockbtn_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","clockbtn_longclick");}
 BA.debugLineNum = 390;BA.debugLine="Private Sub clockBtn_LongClick";
Debug.ShouldStop(32);
 BA.debugLineNum = 391;BA.debugLine="showInfoPopup";
Debug.ShouldStop(64);
_showinfopopup();
 BA.debugLineNum = 392;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 393;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(256);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 394;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(512);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 395;BA.debugLine="showInfoPage(1)";
Debug.ShouldStop(1024);
_showinfopage(BA.numberCast(int.class, 1));
 BA.debugLineNum = 396;BA.debugLine="Return";
Debug.ShouldStop(2048);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 398;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _clocklightbtn_click() throws Exception{
try {
		Debug.PushSubsStack("clockLightBtn_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,285);
if (RapidSub.canDelegate("clocklightbtn_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","clocklightbtn_click");}
 BA.debugLineNum = 285;BA.debugLine="Private Sub  clockLightBtn_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 286;BA.debugLine="StartActivity(clockActivity)";
Debug.ShouldStop(536870912);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._clockactivity.getObject())));
 BA.debugLineNum = 287;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _clocklightbtn_longclick() throws Exception{
try {
		Debug.PushSubsStack("clockLightBtn_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,400);
if (RapidSub.canDelegate("clocklightbtn_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","clocklightbtn_longclick");}
 BA.debugLineNum = 400;BA.debugLine="Private Sub clockLightBtn_LongClick";
Debug.ShouldStop(32768);
 BA.debugLineNum = 401;BA.debugLine="showInfoPopup";
Debug.ShouldStop(65536);
_showinfopopup();
 BA.debugLineNum = 402;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 403;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(262144);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 404;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(524288);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 405;BA.debugLine="showInfoPage(1)";
Debug.ShouldStop(1048576);
_showinfopage(BA.numberCast(int.class, 1));
 BA.debugLineNum = 406;BA.debugLine="Return";
Debug.ShouldStop(2097152);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 408;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _corkie_click() throws Exception{
try {
		Debug.PushSubsStack("corkie_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,371);
if (RapidSub.canDelegate("corkie_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","corkie_click");}
 BA.debugLineNum = 371;BA.debugLine="Private Sub corkie_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 372;BA.debugLine="StartActivity(corkActivity)";
Debug.ShouldStop(524288);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._corkactivity.getObject())));
 BA.debugLineNum = 373;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _corkie_longclick() throws Exception{
try {
		Debug.PushSubsStack("corkie_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,410);
if (RapidSub.canDelegate("corkie_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","corkie_longclick");}
 BA.debugLineNum = 410;BA.debugLine="Private Sub corkie_LongClick";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 411;BA.debugLine="showInfoPopup";
Debug.ShouldStop(67108864);
_showinfopopup();
 BA.debugLineNum = 412;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 413;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(268435456);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 414;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(536870912);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 415;BA.debugLine="showInfoPage(2)";
Debug.ShouldStop(1073741824);
_showinfopage(BA.numberCast(int.class, 2));
 BA.debugLineNum = 416;BA.debugLine="Return";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 418;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _dlamp_click() throws Exception{
try {
		Debug.PushSubsStack("dlamp_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,137);
if (RapidSub.canDelegate("dlamp_click")) { b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","dlamp_click"); return;}
ResumableSub_dlamp_Click rsub = new ResumableSub_dlamp_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_dlamp_Click extends BA.ResumableSub {
public ResumableSub_dlamp_Click(b4a.example.mainactivity parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("dlamp_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,137);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 BA.debugLineNum = 138;BA.debugLine="Starter.darkMode = False";
Debug.ShouldStop(512);
parent.mostCurrent._starter._darkmode /*RemoteObject*/  = parent.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 139;BA.debugLine="kvsPref.Put(\"darkMode\", False)";
Debug.ShouldStop(1024);
parent._kvspref.runVoidMethod ("_put",(Object)(BA.ObjectToString("darkMode")),(Object)((parent.mostCurrent.__c.getField(true,"False"))));
 BA.debugLineNum = 140;BA.debugLine="regLayout.Visible = True";
Debug.ShouldStop(2048);
parent.mostCurrent._reglayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 141;BA.debugLine="regLayout.BringToFront";
Debug.ShouldStop(4096);
parent.mostCurrent._reglayout.runVoidMethod ("BringToFront");
 BA.debugLineNum = 142;BA.debugLine="regLayout.Alpha = 0";
Debug.ShouldStop(8192);
parent.mostCurrent._reglayout.runMethod(true,"setAlpha",BA.numberCast(float.class, 0));
 BA.debugLineNum = 143;BA.debugLine="regLayout.SetAlphaAnimated(250, 1)";
Debug.ShouldStop(16384);
parent.mostCurrent._reglayout.runVoidMethod ("SetAlphaAnimated",(Object)(BA.numberCast(int.class, 250)),(Object)(BA.numberCast(float.class, 1)));
 BA.debugLineNum = 144;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 0)";
Debug.ShouldStop(32768);
parent.mostCurrent._darkmodelayout.runVoidMethod ("SetAlphaAnimated",(Object)(BA.numberCast(int.class, 250)),(Object)(BA.numberCast(float.class, 0)));
 BA.debugLineNum = 145;BA.debugLine="Sleep(250)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("Sleep",mainactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "mainactivity", "dlamp_click"),BA.numberCast(int.class, 250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 146;BA.debugLine="darkModeLayout.Visible = False";
Debug.ShouldStop(131072);
parent.mostCurrent._darkmodelayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 147;BA.debugLine="End Sub";
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
public static RemoteObject  _dlamp_longclick() throws Exception{
try {
		Debug.PushSubsStack("dlamp_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,480);
if (RapidSub.canDelegate("dlamp_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","dlamp_longclick");}
 BA.debugLineNum = 480;BA.debugLine="Private Sub dlamp_LongClick";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 481;BA.debugLine="showInfoPopup";
Debug.ShouldStop(1);
_showinfopopup();
 BA.debugLineNum = 482;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 483;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(4);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 484;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(8);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 485;BA.debugLine="showInfoPage(8)";
Debug.ShouldStop(16);
_showinfopage(BA.numberCast(int.class, 8));
 BA.debugLineNum = 486;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 488;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim regLayout, darkModeLayout As B4XView";
mainactivity.mostCurrent._reglayout = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
mainactivity.mostCurrent._darkmodelayout = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Dim size As Int = 100%y";
mainactivity._size = mainactivity.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),mainactivity.mostCurrent.activityBA);
 //BA.debugLineNum = 17;BA.debugLine="Private hsv As HorizontalScrollView";
mainactivity.mostCurrent._hsv = RemoteObject.createNew ("anywheresoftware.b4a.objects.HorizontalScrollViewWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private computerGif As B4XGifView";
mainactivity.mostCurrent._computergif = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 19;BA.debugLine="Private dcomputerGif As B4XGifView";
mainactivity.mostCurrent._dcomputergif = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 20;BA.debugLine="Private curtain As B4XGifView";
mainactivity.mostCurrent._curtain = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 21;BA.debugLine="Private dCurtain As B4XGifView";
mainactivity.mostCurrent._dcurtain = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 22;BA.debugLine="Private notesOpen As B4XGifView";
mainactivity.mostCurrent._notesopen = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 23;BA.debugLine="Private noteBook As ImageView";
mainactivity.mostCurrent._notebook = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private dnotesOpen As B4XGifView";
mainactivity.mostCurrent._dnotesopen = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 25;BA.debugLine="Private clockBtn As Button";
mainactivity.mostCurrent._clockbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private clockLightBtn As Button";
mainactivity.mostCurrent._clocklightbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private infoPnl As B4XView";
mainactivity.mostCurrent._infopnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private infoTitleLbl As Label";
mainactivity.mostCurrent._infotitlelbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private infoDescLbl As Label";
mainactivity.mostCurrent._infodesclbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private infoPageLbl As Label";
mainactivity.mostCurrent._infopagelbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Dim infoPage As Int = 0";
mainactivity._infopage = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _helpbtn_click() throws Exception{
try {
		Debug.PushSubsStack("helpBtn_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,293);
if (RapidSub.canDelegate("helpbtn_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","helpbtn_click");}
 BA.debugLineNum = 293;BA.debugLine="Private Sub helpBtn_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 294;BA.debugLine="StartActivity(helpActivity)";
Debug.ShouldStop(32);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._helpactivity.getObject())));
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
public static RemoteObject  _infopnlclose_click() throws Exception{
try {
		Debug.PushSubsStack("infoPnlClose_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,301);
if (RapidSub.canDelegate("infopnlclose_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","infopnlclose_click");}
 BA.debugLineNum = 301;BA.debugLine="Private Sub infoPnlClose_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 302;BA.debugLine="infoPnl.Visible = False";
Debug.ShouldStop(8192);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 303;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _lamp_click() throws Exception{
try {
		Debug.PushSubsStack("lamp_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,125);
if (RapidSub.canDelegate("lamp_click")) { b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","lamp_click"); return;}
ResumableSub_lamp_Click rsub = new ResumableSub_lamp_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_lamp_Click extends BA.ResumableSub {
public ResumableSub_lamp_Click(b4a.example.mainactivity parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("lamp_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,125);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 BA.debugLineNum = 126;BA.debugLine="Starter.darkMode = True";
Debug.ShouldStop(536870912);
parent.mostCurrent._starter._darkmode /*RemoteObject*/  = parent.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 127;BA.debugLine="kvsPref.Put(\"darkMode\", True)";
Debug.ShouldStop(1073741824);
parent._kvspref.runVoidMethod ("_put",(Object)(BA.ObjectToString("darkMode")),(Object)((parent.mostCurrent.__c.getField(true,"True"))));
 BA.debugLineNum = 128;BA.debugLine="darkModeLayout.Visible = True";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._darkmodelayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 129;BA.debugLine="darkModeLayout.BringToFront";
Debug.ShouldStop(1);
parent.mostCurrent._darkmodelayout.runVoidMethod ("BringToFront");
 BA.debugLineNum = 130;BA.debugLine="darkModeLayout.Alpha = 0";
Debug.ShouldStop(2);
parent.mostCurrent._darkmodelayout.runMethod(true,"setAlpha",BA.numberCast(float.class, 0));
 BA.debugLineNum = 131;BA.debugLine="darkModeLayout.SetAlphaAnimated(250, 1)";
Debug.ShouldStop(4);
parent.mostCurrent._darkmodelayout.runVoidMethod ("SetAlphaAnimated",(Object)(BA.numberCast(int.class, 250)),(Object)(BA.numberCast(float.class, 1)));
 BA.debugLineNum = 132;BA.debugLine="regLayout.SetAlphaAnimated(250, 0)";
Debug.ShouldStop(8);
parent.mostCurrent._reglayout.runVoidMethod ("SetAlphaAnimated",(Object)(BA.numberCast(int.class, 250)),(Object)(BA.numberCast(float.class, 0)));
 BA.debugLineNum = 133;BA.debugLine="Sleep(250)";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("Sleep",mainactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "mainactivity", "lamp_click"),BA.numberCast(int.class, 250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 134;BA.debugLine="regLayout.Visible = False";
Debug.ShouldStop(32);
parent.mostCurrent._reglayout.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 135;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
public static RemoteObject  _lamp_longclick() throws Exception{
try {
		Debug.PushSubsStack("lamp_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,470);
if (RapidSub.canDelegate("lamp_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","lamp_longclick");}
 BA.debugLineNum = 470;BA.debugLine="Private Sub lamp_LongClick";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 471;BA.debugLine="showInfoPopup";
Debug.ShouldStop(4194304);
_showinfopopup();
 BA.debugLineNum = 472;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 473;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(16777216);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 474;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(33554432);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 475;BA.debugLine="showInfoPage(8)";
Debug.ShouldStop(67108864);
_showinfopage(BA.numberCast(int.class, 8));
 BA.debugLineNum = 476;BA.debugLine="Return";
Debug.ShouldStop(134217728);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 478;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _mp_click() throws Exception{
try {
		Debug.PushSubsStack("mP_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,309);
if (RapidSub.canDelegate("mp_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","mp_click");}
 BA.debugLineNum = 309;BA.debugLine="Private Sub mP_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 310;BA.debugLine="StartActivity(musicActivity)";
Debug.ShouldStop(2097152);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._musicactivity.getObject())));
 BA.debugLineNum = 311;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _mp_longclick() throws Exception{
try {
		Debug.PushSubsStack("mP_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,430);
if (RapidSub.canDelegate("mp_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","mp_longclick");}
 BA.debugLineNum = 430;BA.debugLine="Private Sub mP_LongClick";
Debug.ShouldStop(8192);
 BA.debugLineNum = 431;BA.debugLine="showInfoPopup";
Debug.ShouldStop(16384);
_showinfopopup();
 BA.debugLineNum = 432;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 433;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(65536);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 434;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(131072);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 435;BA.debugLine="showInfoPage(4)";
Debug.ShouldStop(262144);
_showinfopage(BA.numberCast(int.class, 4));
 BA.debugLineNum = 436;BA.debugLine="Return";
Debug.ShouldStop(524288);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 438;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _navbtn_click() throws Exception{
try {
		Debug.PushSubsStack("navBtn_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,289);
if (RapidSub.canDelegate("navbtn_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","navbtn_click");}
 BA.debugLineNum = 289;BA.debugLine="Private Sub navBtn_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 290;BA.debugLine="StartActivity(navActivity)";
Debug.ShouldStop(2);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._navactivity.getObject())));
 BA.debugLineNum = 291;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _navbtn_longclick() throws Exception{
try {
		Debug.PushSubsStack("navBtn_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,490);
if (RapidSub.canDelegate("navbtn_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","navbtn_longclick");}
 BA.debugLineNum = 490;BA.debugLine="Private Sub navBtn_LongClick";
Debug.ShouldStop(512);
 BA.debugLineNum = 491;BA.debugLine="showInfoPopup";
Debug.ShouldStop(1024);
_showinfopopup();
 BA.debugLineNum = 492;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 493;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(4096);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 494;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(8192);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 495;BA.debugLine="showInfoPage(9)";
Debug.ShouldStop(16384);
_showinfopage(BA.numberCast(int.class, 9));
 BA.debugLineNum = 496;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 498;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _notebook_click() throws Exception{
try {
		Debug.PushSubsStack("noteBook_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,321);
if (RapidSub.canDelegate("notebook_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","notebook_click");}
 BA.debugLineNum = 321;BA.debugLine="Private Sub noteBook_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 322;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(2);
switch (BA.switchObjectToInt(mainactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 324;BA.debugLine="CallSub(Me, \"NotesTransition1\")";
Debug.ShouldStop(8);
mainactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",mainactivity.processBA,(Object)(mainactivity.getObject()),(Object)(RemoteObject.createImmutable("NotesTransition1")));
 break; }
case 1: {
 BA.debugLineNum = 326;BA.debugLine="CallSub(Me, \"NotesTransition2\")";
Debug.ShouldStop(32);
mainactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",mainactivity.processBA,(Object)(mainactivity.getObject()),(Object)(RemoteObject.createImmutable("NotesTransition2")));
 break; }
case 2: {
 BA.debugLineNum = 328;BA.debugLine="CallSub(Me, \"NotesTransition3\")";
Debug.ShouldStop(128);
mainactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",mainactivity.processBA,(Object)(mainactivity.getObject()),(Object)(RemoteObject.createImmutable("NotesTransition3")));
 break; }
}
;
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
public static RemoteObject  _notebook_longclick() throws Exception{
try {
		Debug.PushSubsStack("noteBook_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,440);
if (RapidSub.canDelegate("notebook_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","notebook_longclick");}
 BA.debugLineNum = 440;BA.debugLine="Private Sub noteBook_LongClick";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 441;BA.debugLine="showInfoPopup";
Debug.ShouldStop(16777216);
_showinfopopup();
 BA.debugLineNum = 442;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 443;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(67108864);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 444;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(134217728);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 445;BA.debugLine="showInfoPage(5)";
Debug.ShouldStop(268435456);
_showinfopage(BA.numberCast(int.class, 5));
 BA.debugLineNum = 446;BA.debugLine="Return";
Debug.ShouldStop(536870912);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 448;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _notestransition1() throws Exception{
try {
		Debug.PushSubsStack("NotesTransition1 (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,332);
if (RapidSub.canDelegate("notestransition1")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","notestransition1");}
ResumableSub_NotesTransition1 rsub = new ResumableSub_NotesTransition1(null);
rsub.remoteResumableSub = anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSubForFilter();
rsub.resume(null, null);
return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.Common.ResumableSubWrapper"), rsub.remoteResumableSub);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_NotesTransition1 extends BA.ResumableSub {
public ResumableSub_NotesTransition1(b4a.example.mainactivity parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("NotesTransition1 (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,332);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = -1;
 BA.debugLineNum = 333;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"Openbook.GIF\")";
Debug.ShouldStop(4096);
parent.mostCurrent._notesopen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Openbook.GIF")));
 BA.debugLineNum = 334;BA.debugLine="notesOpen.mBase.Visible = True";
Debug.ShouldStop(8192);
parent.mostCurrent._notesopen.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 335;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"Darkopenbook.G";
Debug.ShouldStop(16384);
parent.mostCurrent._dnotesopen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Darkopenbook.GIF")));
 BA.debugLineNum = 336;BA.debugLine="dnotesOpen.mBase.Visible = True";
Debug.ShouldStop(32768);
parent.mostCurrent._dnotesopen.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 337;BA.debugLine="noteBook.Enabled = False";
Debug.ShouldStop(65536);
parent.mostCurrent._notebook.runMethod(true,"setEnabled",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 338;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
Debug.ShouldStop(131072);
parent.mostCurrent._notebook.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 340;BA.debugLine="Sleep(1500)";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("Sleep",mainactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "mainactivity", "notestransition1"),BA.numberCast(int.class, 1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 342;BA.debugLine="StartActivity(noteActivity)";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((parent.mostCurrent._noteactivity.getObject())));
 BA.debugLineNum = 343;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
public static RemoteObject  _notestransition2() throws Exception{
try {
		Debug.PushSubsStack("NotesTransition2 (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,345);
if (RapidSub.canDelegate("notestransition2")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","notestransition2");}
ResumableSub_NotesTransition2 rsub = new ResumableSub_NotesTransition2(null);
rsub.remoteResumableSub = anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSubForFilter();
rsub.resume(null, null);
return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.Common.ResumableSubWrapper"), rsub.remoteResumableSub);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_NotesTransition2 extends BA.ResumableSub {
public ResumableSub_NotesTransition2(b4a.example.mainactivity parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("NotesTransition2 (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,345);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = -1;
 BA.debugLineNum = 346;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes2.GIF\"";
Debug.ShouldStop(33554432);
parent.mostCurrent._notesopen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("OpenNotes2.GIF")));
 BA.debugLineNum = 347;BA.debugLine="notesOpen.mBase.Visible = True";
Debug.ShouldStop(67108864);
parent.mostCurrent._notesopen.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 348;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes2.GI";
Debug.ShouldStop(134217728);
parent.mostCurrent._dnotesopen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DOpenNotes2.GIF")));
 BA.debugLineNum = 349;BA.debugLine="dnotesOpen.mBase.Visible = True";
Debug.ShouldStop(268435456);
parent.mostCurrent._dnotesopen.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 350;BA.debugLine="noteBook.Enabled = False";
Debug.ShouldStop(536870912);
parent.mostCurrent._notebook.runMethod(true,"setEnabled",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 351;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
Debug.ShouldStop(1073741824);
parent.mostCurrent._notebook.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 353;BA.debugLine="Sleep(1500)";
Debug.ShouldStop(1);
parent.mostCurrent.__c.runVoidMethod ("Sleep",mainactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "mainactivity", "notestransition2"),BA.numberCast(int.class, 1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 355;BA.debugLine="StartActivity(noteActivity)";
Debug.ShouldStop(4);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((parent.mostCurrent._noteactivity.getObject())));
 BA.debugLineNum = 356;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
public static RemoteObject  _notestransition3() throws Exception{
try {
		Debug.PushSubsStack("NotesTransition3 (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,358);
if (RapidSub.canDelegate("notestransition3")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","notestransition3");}
ResumableSub_NotesTransition3 rsub = new ResumableSub_NotesTransition3(null);
rsub.remoteResumableSub = anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSubForFilter();
rsub.resume(null, null);
return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.Common.ResumableSubWrapper"), rsub.remoteResumableSub);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_NotesTransition3 extends BA.ResumableSub {
public ResumableSub_NotesTransition3(b4a.example.mainactivity parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.mainactivity parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("NotesTransition3 (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,358);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = -1;
 BA.debugLineNum = 359;BA.debugLine="notesOpen.SetGif(File.DirAssets, \"OpenNotes3.GIF\"";
Debug.ShouldStop(64);
parent.mostCurrent._notesopen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("OpenNotes3.GIF")));
 BA.debugLineNum = 360;BA.debugLine="notesOpen.mBase.Visible = True";
Debug.ShouldStop(128);
parent.mostCurrent._notesopen.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 361;BA.debugLine="dnotesOpen.SetGif(File.DirAssets, \"DOpenNotes3.GI";
Debug.ShouldStop(256);
parent.mostCurrent._dnotesopen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("DOpenNotes3.GIF")));
 BA.debugLineNum = 362;BA.debugLine="dnotesOpen.mBase.Visible = True";
Debug.ShouldStop(512);
parent.mostCurrent._dnotesopen.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 363;BA.debugLine="noteBook.Enabled = False";
Debug.ShouldStop(1024);
parent.mostCurrent._notebook.runMethod(true,"setEnabled",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 364;BA.debugLine="noteBook.Visible = False 'bat ayaw mawala T-T";
Debug.ShouldStop(2048);
parent.mostCurrent._notebook.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 366;BA.debugLine="Sleep(1500)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("Sleep",mainactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "mainactivity", "notestransition3"),BA.numberCast(int.class, 1500));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 368;BA.debugLine="StartActivity(noteActivity)";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((parent.mostCurrent._noteactivity.getObject())));
 BA.debugLineNum = 369;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
public static RemoteObject  _plant_click() throws Exception{
try {
		Debug.PushSubsStack("plant_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,375);
if (RapidSub.canDelegate("plant_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","plant_click");}
 BA.debugLineNum = 375;BA.debugLine="Private Sub plant_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 376;BA.debugLine="StartActivity(themeActivity)";
Debug.ShouldStop(8388608);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._themeactivity.getObject())));
 BA.debugLineNum = 377;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _plant_longclick() throws Exception{
try {
		Debug.PushSubsStack("plant_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,460);
if (RapidSub.canDelegate("plant_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","plant_longclick");}
 BA.debugLineNum = 460;BA.debugLine="Private Sub plant_LongClick";
Debug.ShouldStop(2048);
 BA.debugLineNum = 461;BA.debugLine="showInfoPopup";
Debug.ShouldStop(4096);
_showinfopopup();
 BA.debugLineNum = 462;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 463;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(16384);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 464;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(32768);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 465;BA.debugLine="showInfoPage(7)";
Debug.ShouldStop(65536);
_showinfopage(BA.numberCast(int.class, 7));
 BA.debugLineNum = 466;BA.debugLine="Return";
Debug.ShouldStop(131072);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 468;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
 //BA.debugLineNum = 7;BA.debugLine="Dim xui As XUI";
mainactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 8;BA.debugLine="Private timerClock As Timer";
mainactivity._timerclock = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 9;BA.debugLine="Public kvs As KeyValueStore";
mainactivity._kvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 10;BA.debugLine="Public kvsPref As KeyValueStore";
mainactivity._kvspref = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 11;BA.debugLine="Public format24h As Boolean = False";
mainactivity._format24h = mainactivity.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showinfopage(RemoteObject _page) throws Exception{
try {
		Debug.PushSubsStack("showInfoPage (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,187);
if (RapidSub.canDelegate("showinfopage")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","showinfopage", _page);}
Debug.locals.put("page", _page);
 BA.debugLineNum = 187;BA.debugLine="Private Sub showInfoPage(page As Int)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 188;BA.debugLine="infoPage = page";
Debug.ShouldStop(134217728);
mainactivity._infopage = _page;
 BA.debugLineNum = 189;BA.debugLine="Select page";
Debug.ShouldStop(268435456);
switch (BA.switchObjectToInt(_page,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7),BA.numberCast(int.class, 8),BA.numberCast(int.class, 9))) {
case 0: {
 BA.debugLineNum = 191;BA.debugLine="infoDescLbl.TextSize = 12";
Debug.ShouldStop(1073741824);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 192;BA.debugLine="infoTitleLbl.Text = \"Calendar\"";
Debug.ShouldStop(-2147483648);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Calendar"));
 BA.debugLineNum = 193;BA.debugLine="infoDescLbl.Text = \"1. Tap the Calendar on the";
Debug.ShouldStop(1);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the Calendar on the home screen to open the Scheduling System."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. You'll land on the Month View by default — a full overview of your calendar."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. Use the Menu Button (upper left) to switch between Month, Day, and Schedule views."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. Tap any date to open a detailed hour-by-hour timeline for that day."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. Select any time slot to begin creating a schedule entry."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. Tap the + Icon and choose from: Add Event, Add Task, Birthday, or Out of Office."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("7. Press Save to confirm, or Delete to remove an existing entry."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("8. Use the Arrow Down Button to navigate between months and years."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("9. To share your schedule with a group, tap the group tab and tap the join/create button and enter or create your group."))));
 break; }
case 1: {
 BA.debugLineNum = 203;BA.debugLine="infoDescLbl.TextSize = 14";
Debug.ShouldStop(1024);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 204;BA.debugLine="infoTitleLbl.Text = \"Clock\"";
Debug.ShouldStop(2048);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Clock"));
 BA.debugLineNum = 205;BA.debugLine="infoDescLbl.Text = \"1. Tap the Clock on the hom";
Debug.ShouldStop(4096);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the Clock on the home screen To open the Pomodoro timer."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. Press Start To begin a 25-minute focus session."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. When the session ends, the timer automatically shifts to a short break (3 mins) Or long break (10 mins)."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. Use the Next button To manually switch between Pomodoro, short break, And long break modes."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. Press Pause anytime To pause the timer."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. Tap Settings To customize the length of each session To your preference."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("7. You can also switch the clock display format from within Settings."))));
 break; }
case 2: {
 BA.debugLineNum = 213;BA.debugLine="infoDescLbl.TextSize = 16";
Debug.ShouldStop(1048576);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 214;BA.debugLine="infoTitleLbl.Text = \"Corkboard\"";
Debug.ShouldStop(2097152);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Corkboard"));
 BA.debugLineNum = 215;BA.debugLine="infoDescLbl.Text = \"1. Tap the Corkboard on the";
Debug.ShouldStop(4194304);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the Corkboard on the home screen to open your digital canvas."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. Tap the Note Button to add a sticky note — type your reminder or idea and place it anywhere on the board."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. Tap the Image Button to pin a photo or image onto the board."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. Tap the Canvas Tool to draw, sketch, or map out ideas freehand."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. Press and drag any element to reposition it wherever you like."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. Arrange your board however feels right — there's no wrong way to use it."))));
 break; }
case 3: {
 BA.debugLineNum = 222;BA.debugLine="infoDescLbl.TextSize = 10";
Debug.ShouldStop(536870912);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 223;BA.debugLine="infoTitleLbl.Text = \"Flashcards\"";
Debug.ShouldStop(1073741824);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Flashcards"));
 BA.debugLineNum = 224;BA.debugLine="infoDescLbl.Text = \"1. Tap the Books on the hom";
Debug.ShouldStop(-2147483648);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the Books on the home screen to open the Flashcards feature."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. Tap the + Icon to create a new deck — enter a name and save."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. Long-press a deck to access options: Add Card, Browse Cards, Rename, Create Subdeck, or Delete."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. To add a card, open a deck and tap the + Icon — enter the question on one side and the answer on the other."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. To review, open a deck and tap a card — press Show Answer to flip it."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. Use the Next button to move to the next card, or Refresh to restart the set."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("7. Use Active Recall mode To challenge yourself To remember the answer before flipping."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("8. To edit r remove a card, long-press it And select your action."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("9. To generate flashcards from a File, tap the Files To Flashcard button on any subdeck, upload your document, and Athena will build a full deck For you automatically."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("10. To generate flashcards from a topic, tap the AI cards And subdecks button on any deck, and Athena will build the rest for you."))));
 break; }
case 4: {
 BA.debugLineNum = 235;BA.debugLine="infoDescLbl.TextSize = 16";
Debug.ShouldStop(1024);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 236;BA.debugLine="infoTitleLbl.Text = \"Music Player\"";
Debug.ShouldStop(2048);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Music Player"));
 BA.debugLineNum = 237;BA.debugLine="infoDescLbl.Text = \"1. Tap the Music Player on";
Debug.ShouldStop(4096);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the Music Player on the home screen to open it."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. Press Play to start the lo-fi music."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. Use the Select option to choose a specific track from the library."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. Press Next to skip to the next track."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. Press Pause anytime to stop the music."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. Scroll through the expanded music library for more track options."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("7. To add your own music, tap the Upload Music button and select a song from your device."))));
 break; }
case 5: {
 BA.debugLineNum = 245;BA.debugLine="infoDescLbl.TextSize = 14";
Debug.ShouldStop(1048576);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 246;BA.debugLine="infoTitleLbl.Text = \"Notepad\"";
Debug.ShouldStop(2097152);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Notepad"));
 BA.debugLineNum = 247;BA.debugLine="infoDescLbl.Text = \"1. Tap the Notepad on the h";
Debug.ShouldStop(4194304);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the Notepad on the home screen to open the Notes feature."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. Press the + Button to create a new note."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. Enter a title, add relevant tags, then type your content in the body field."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. Press Save when you're done."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. To find a note, use the search bar — type the title or a tag and your note appears instantly."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. To delete a note, long-press it and tap Yes to confirm."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("7. To generate summarized notes from a file, tap Insert PDF button, upload your file, and let Athena do the rest."))));
 break; }
case 6: {
 BA.debugLineNum = 255;BA.debugLine="infoDescLbl.TextSize = 14";
Debug.ShouldStop(1073741824);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 256;BA.debugLine="infoTitleLbl.Text = \"To-do List\"";
Debug.ShouldStop(-2147483648);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("To-do List"));
 BA.debugLineNum = 257;BA.debugLine="infoDescLbl.Text = \"1. Tap the PC Screen on the";
Debug.ShouldStop(1);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("1. Tap the PC Screen on the home screen to open the To-Do List."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("2. Press + New List to create a new list."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("3. Open the list and tap the input field to add a task."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("4. Type your task and press Enter to save it."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("5. Tap the checkbox beside a task to mark it as complete — watch your progress percentage go up."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("6. To delete a task or list, long-press it and confirm deletion."),mainactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("7. To share your to-do list with a group, tap the group tab and tap the join/create button and enter or create your group."))));
 break; }
case 7: {
 BA.debugLineNum = 265;BA.debugLine="infoDescLbl.TextSize = 22";
Debug.ShouldStop(256);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 22));
 BA.debugLineNum = 266;BA.debugLine="infoTitleLbl.Text = \"Themes\"";
Debug.ShouldStop(512);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Themes"));
 BA.debugLineNum = 267;BA.debugLine="infoDescLbl.Text = \"Themes let you put your own";
Debug.ShouldStop(1024);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence("Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."));
 break; }
case 8: {
 BA.debugLineNum = 269;BA.debugLine="infoDescLbl.TextSize = 22";
Debug.ShouldStop(4096);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 22));
 BA.debugLineNum = 270;BA.debugLine="infoTitleLbl.Text = \"Lamp\"";
Debug.ShouldStop(8192);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Lamp"));
 BA.debugLineNum = 271;BA.debugLine="infoDescLbl.Text = \"The lamp gives you control";
Debug.ShouldStop(16384);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence("The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."));
 break; }
case 9: {
 BA.debugLineNum = 273;BA.debugLine="infoDescLbl.TextSize = 22";
Debug.ShouldStop(65536);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 22));
 BA.debugLineNum = 274;BA.debugLine="infoTitleLbl.Text = \"Navigation\"";
Debug.ShouldStop(131072);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setText",BA.ObjectToCharSequence("Navigation"));
 BA.debugLineNum = 275;BA.debugLine="infoDescLbl.Text = \"Navigation is your home bas";
Debug.ShouldStop(262144);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setText",BA.ObjectToCharSequence("Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."));
 break; }
}
;
 BA.debugLineNum = 279;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showinfopopup() throws Exception{
try {
		Debug.PushSubsStack("showInfoPopup (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,155);
if (RapidSub.canDelegate("showinfopopup")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","showinfopopup");}
RemoteObject _closebtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 155;BA.debugLine="Private Sub showInfoPopup";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 157;BA.debugLine="infoPnl = xui.CreatePanel(\"infoPnl\")";
Debug.ShouldStop(268435456);
mainactivity.mostCurrent._infopnl = mainactivity._xui.runMethod(false,"CreatePanel",mainactivity.processBA,(Object)(RemoteObject.createImmutable("infoPnl")));
 BA.debugLineNum = 158;BA.debugLine="Activity.AddView(infoPnl, 75dip, 205dip, 300dip,";
Debug.ShouldStop(536870912);
mainactivity.mostCurrent._activity.runVoidMethod ("AddView",(Object)((mainactivity.mostCurrent._infopnl.getObject())),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 75)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 205)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 340)))));
 BA.debugLineNum = 159;BA.debugLine="infoPnl.SetColorAndBorder(xui.Color_White, 2dip,";
Debug.ShouldStop(1073741824);
mainactivity.mostCurrent._infopnl.runVoidMethod ("SetColorAndBorder",(Object)(mainactivity._xui.getField(true,"Color_White")),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(mainactivity._xui.getField(true,"Color_Black")),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 161;BA.debugLine="Dim closeBtn As Button";
Debug.ShouldStop(1);
_closebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("closeBtn", _closebtn);
 BA.debugLineNum = 162;BA.debugLine="closeBtn.Initialize(\"infoPnlClose\")";
Debug.ShouldStop(2);
_closebtn.runVoidMethod ("Initialize",mainactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("infoPnlClose")));
 BA.debugLineNum = 163;BA.debugLine="closeBtn.Text = \"x\"";
Debug.ShouldStop(4);
_closebtn.runMethod(true,"setText",BA.ObjectToCharSequence("x"));
 BA.debugLineNum = 164;BA.debugLine="closeBtn.TextSize = 7";
Debug.ShouldStop(8);
_closebtn.runMethod(true,"setTextSize",BA.numberCast(float.class, 7));
 BA.debugLineNum = 165;BA.debugLine="infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28";
Debug.ShouldStop(16);
mainactivity.mostCurrent._infopnl.runVoidMethod ("AddView",(Object)((_closebtn.getObject())),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 265)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 28)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 28)))));
 BA.debugLineNum = 167;BA.debugLine="infoTitleLbl.Initialize(\"\")";
Debug.ShouldStop(64);
mainactivity.mostCurrent._infotitlelbl.runVoidMethod ("Initialize",mainactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 168;BA.debugLine="infoTitleLbl.TextSize = 16";
Debug.ShouldStop(128);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 169;BA.debugLine="infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(256);
mainactivity.mostCurrent._infotitlelbl.runMethod(true,"setGravity",mainactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 170;BA.debugLine="infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248di";
Debug.ShouldStop(512);
mainactivity.mostCurrent._infopnl.runVoidMethod ("AddView",(Object)((mainactivity.mostCurrent._infotitlelbl.getObject())),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 12)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 12)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 248)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 172;BA.debugLine="infoDescLbl.Initialize(\"\")";
Debug.ShouldStop(2048);
mainactivity.mostCurrent._infodesclbl.runVoidMethod ("Initialize",mainactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 173;BA.debugLine="infoDescLbl.TextSize = 10";
Debug.ShouldStop(4096);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 174;BA.debugLine="infoDescLbl.Gravity = Gravity.TOP";
Debug.ShouldStop(8192);
mainactivity.mostCurrent._infodesclbl.runMethod(true,"setGravity",mainactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"TOP"));
 BA.debugLineNum = 175;BA.debugLine="infoDescLbl.SingleLine = False";
Debug.ShouldStop(16384);
mainactivity.mostCurrent._infodesclbl.runVoidMethod ("setSingleLine",mainactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 176;BA.debugLine="infoPnl.AddView(infoDescLbl, 12dip, 43dip, 276dip";
Debug.ShouldStop(32768);
mainactivity.mostCurrent._infopnl.runVoidMethod ("AddView",(Object)((mainactivity.mostCurrent._infodesclbl.getObject())),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 12)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 43)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 276)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 280)))));
 BA.debugLineNum = 178;BA.debugLine="infoPageLbl.Initialize(\"\")";
Debug.ShouldStop(131072);
mainactivity.mostCurrent._infopagelbl.runVoidMethod ("Initialize",mainactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 179;BA.debugLine="infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL";
Debug.ShouldStop(262144);
mainactivity.mostCurrent._infopagelbl.runMethod(true,"setGravity",mainactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"));
 BA.debugLineNum = 180;BA.debugLine="infoPageLbl.TextSize = 10";
Debug.ShouldStop(524288);
mainactivity.mostCurrent._infopagelbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 181;BA.debugLine="infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110di";
Debug.ShouldStop(1048576);
mainactivity.mostCurrent._infopnl.runVoidMethod ("AddView",(Object)((mainactivity.mostCurrent._infopagelbl.getObject())),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 95)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 184)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 110)))),(Object)(mainactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 35)))));
 BA.debugLineNum = 184;BA.debugLine="showInfoPage(0)";
Debug.ShouldStop(8388608);
_showinfopage(BA.numberCast(int.class, 0));
 BA.debugLineNum = 185;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _statsbtn_click() throws Exception{
try {
		Debug.PushSubsStack("statsBtn_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,297);
if (RapidSub.canDelegate("statsbtn_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","statsbtn_click");}
 BA.debugLineNum = 297;BA.debugLine="Private Sub statsBtn_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 298;BA.debugLine="StartActivity(Leaderboard)";
Debug.ShouldStop(512);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._leaderboard.getObject())));
 BA.debugLineNum = 299;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _timerclock_tick() throws Exception{
try {
		Debug.PushSubsStack("timerClock_Tick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,149);
if (RapidSub.canDelegate("timerclock_tick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","timerclock_tick");}
 BA.debugLineNum = 149;BA.debugLine="Sub timerClock_Tick";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 150;BA.debugLine="clockBtn.Text = DateTime.Time(DateTime.Now)";
Debug.ShouldStop(2097152);
mainactivity.mostCurrent._clockbtn.runMethod(true,"setText",BA.ObjectToCharSequence(mainactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"Time",(Object)(mainactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")))));
 BA.debugLineNum = 151;BA.debugLine="clockLightBtn.Text = DateTime.Time(DateTime.Now)";
Debug.ShouldStop(4194304);
mainactivity.mostCurrent._clocklightbtn.runMethod(true,"setText",BA.ObjectToCharSequence(mainactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"Time",(Object)(mainactivity.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")))));
 BA.debugLineNum = 152;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _todolistbtn_click() throws Exception{
try {
		Debug.PushSubsStack("todolistBtn_Click (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,305);
if (RapidSub.canDelegate("todolistbtn_click")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","todolistbtn_click");}
 BA.debugLineNum = 305;BA.debugLine="Private Sub todolistBtn_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 306;BA.debugLine="StartActivity(todoActivity)";
Debug.ShouldStop(131072);
mainactivity.mostCurrent.__c.runVoidMethod ("StartActivity",mainactivity.processBA,(Object)((mainactivity.mostCurrent._todoactivity.getObject())));
 BA.debugLineNum = 307;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _todolistbtn_longclick() throws Exception{
try {
		Debug.PushSubsStack("todolistBtn_LongClick (mainactivity) ","mainactivity",23,mainactivity.mostCurrent.activityBA,mainactivity.mostCurrent,450);
if (RapidSub.canDelegate("todolistbtn_longclick")) { return b4a.example.mainactivity.remoteMe.runUserSub(false, "mainactivity","todolistbtn_longclick");}
 BA.debugLineNum = 450;BA.debugLine="Private Sub todolistBtn_LongClick";
Debug.ShouldStop(2);
 BA.debugLineNum = 451;BA.debugLine="showInfoPopup";
Debug.ShouldStop(4);
_showinfopopup();
 BA.debugLineNum = 452;BA.debugLine="If infoPnl <> Null Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("N",mainactivity.mostCurrent._infopnl)) { 
 BA.debugLineNum = 453;BA.debugLine="infoPnl.Visible = True";
Debug.ShouldStop(16);
mainactivity.mostCurrent._infopnl.runMethod(true,"setVisible",mainactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 454;BA.debugLine="infoPnl.BringToFront";
Debug.ShouldStop(32);
mainactivity.mostCurrent._infopnl.runVoidMethod ("BringToFront");
 BA.debugLineNum = 455;BA.debugLine="showInfoPage(6)";
Debug.ShouldStop(64);
_showinfopage(BA.numberCast(int.class, 6));
 BA.debugLineNum = 456;BA.debugLine="Return";
Debug.ShouldStop(128);
if (true) return RemoteObject.createImmutable("");
 };
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
}