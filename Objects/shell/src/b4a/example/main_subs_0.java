package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout(\"loadingLayout\")";
Debug.ShouldStop(67108864);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("loadingLayout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 28;BA.debugLine="loadingScreen.SetGif(File.DirAssets, \"loading.GIF";
Debug.ShouldStop(134217728);
main.mostCurrent._loadingscreen.runClassMethod (b4a.example.b4xgifview.class, "_setgif" /*RemoteObject*/ ,(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("loading.GIF")));
 BA.debugLineNum = 29;BA.debugLine="StartService(Starter)";
Debug.ShouldStop(268435456);
main.mostCurrent.__c.runVoidMethod ("StartService",main.processBA,(Object)((main.mostCurrent._starter.getObject())));
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
public static void  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,33);
if (RapidSub.canDelegate("activity_resume")) { b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume"); return;}
ResumableSub_Activity_Resume rsub = new ResumableSub_Activity_Resume(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Resume extends BA.ResumableSub {
public ResumableSub_Activity_Resume(b4a.example.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _result = RemoteObject.createImmutable(false);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,33);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 BA.debugLineNum = 34;BA.debugLine="Wait For (startLoad) Complete (Result As Boolean)";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","complete", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "activity_resume"), _startload());
this.state = 1;
return;
case 1:
//C
this.state = -1;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 35;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
public static void  _complete(RemoteObject _result) throws Exception{
}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private loadingScreen As B4XGifView";
main.mostCurrent._loadingscreen = RemoteObject.createNew ("b4a.example.b4xgifview");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
mainactivity_subs_0._process_globals();
todoactivity_subs_0._process_globals();
calendaractivity_subs_0._process_globals();
schedule_module_subs_0._process_globals();
deck_all_cards_subs_0._process_globals();
clockactivity_subs_0._process_globals();
home_activity_subs_0._process_globals();
login_subs_0._process_globals();
register_subs_0._process_globals();
musicactivity_subs_0._process_globals();
tutorialactivity_subs_0._process_globals();
helpactivity_subs_0._process_globals();
active_recall_subs_0._process_globals();
add_card_module_subs_0._process_globals();
add_card_module2_subs_0._process_globals();
add_events_module_subs_0._process_globals();
all_active_recall_subs_0._process_globals();
card_module_subs_0._process_globals();
corkactivity_subs_0._process_globals();
day_module_subs_0._process_globals();
editnote_subs_0._process_globals();
flashcardactivity_subs_0._process_globals();
musicservice_subs_0._process_globals();
navactivity_subs_0._process_globals();
noteactivity_subs_0._process_globals();
subdeck_module_subs_0._process_globals();
themeactivity_subs_0._process_globals();
httputils2service_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
mainactivity.myClass = BA.getDeviceClass ("b4a.example.mainactivity");
todoactivity.myClass = BA.getDeviceClass ("b4a.example.todoactivity");
calendaractivity.myClass = BA.getDeviceClass ("b4a.example.calendaractivity");
schedule_module.myClass = BA.getDeviceClass ("b4a.example.schedule_module");
deck_all_cards.myClass = BA.getDeviceClass ("b4a.example.deck_all_cards");
clockactivity.myClass = BA.getDeviceClass ("b4a.example.clockactivity");
home_activity.myClass = BA.getDeviceClass ("b4a.example.home_activity");
login.myClass = BA.getDeviceClass ("b4a.example.login");
register.myClass = BA.getDeviceClass ("b4a.example.register");
musicactivity.myClass = BA.getDeviceClass ("b4a.example.musicactivity");
tutorialactivity.myClass = BA.getDeviceClass ("b4a.example.tutorialactivity");
helpactivity.myClass = BA.getDeviceClass ("b4a.example.helpactivity");
active_recall.myClass = BA.getDeviceClass ("b4a.example.active_recall");
add_card_module.myClass = BA.getDeviceClass ("b4a.example.add_card_module");
add_card_module2.myClass = BA.getDeviceClass ("b4a.example.add_card_module2");
add_events_module.myClass = BA.getDeviceClass ("b4a.example.add_events_module");
all_active_recall.myClass = BA.getDeviceClass ("b4a.example.all_active_recall");
card_module.myClass = BA.getDeviceClass ("b4a.example.card_module");
corkactivity.myClass = BA.getDeviceClass ("b4a.example.corkactivity");
day_module.myClass = BA.getDeviceClass ("b4a.example.day_module");
editnote.myClass = BA.getDeviceClass ("b4a.example.editnote");
flashcardactivity.myClass = BA.getDeviceClass ("b4a.example.flashcardactivity");
musicservice.myClass = BA.getDeviceClass ("b4a.example.musicservice");
navactivity.myClass = BA.getDeviceClass ("b4a.example.navactivity");
noteactivity.myClass = BA.getDeviceClass ("b4a.example.noteactivity");
subdeck_module.myClass = BA.getDeviceClass ("b4a.example.subdeck_module");
themeactivity.myClass = BA.getDeviceClass ("b4a.example.themeactivity");
b4xgifview.myClass = BA.getDeviceClass ("b4a.example.b4xgifview");
keyvaluestore.myClass = BA.getDeviceClass ("b4a.example.keyvaluestore");
httputils2service.myClass = BA.getDeviceClass ("b4a.example.httputils2service");
httpjob.myClass = BA.getDeviceClass ("b4a.example.httpjob");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 16;BA.debugLine="Public format24h As Boolean = False";
main._format24h = main.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 17;BA.debugLine="Type MyNote (Title As String, Tags As String, Con";
;
 //BA.debugLineNum = 18;BA.debugLine="Public kvs As KeyValueStore";
main._kvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _startload() throws Exception{
try {
		Debug.PushSubsStack("startLoad (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,37);
if (RapidSub.canDelegate("startload")) { return b4a.example.main.remoteMe.runUserSub(false, "main","startload");}
ResumableSub_startLoad rsub = new ResumableSub_startLoad(null);
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
public static class ResumableSub_startLoad extends BA.ResumableSub {
public ResumableSub_startLoad(b4a.example.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("startLoad (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,37);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = 1;
 BA.debugLineNum = 38;BA.debugLine="Sleep(750)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("Sleep",main.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "startload"),BA.numberCast(int.class, 750));
this.state = 5;
return;
case 5:
//C
this.state = 1;
;
 BA.debugLineNum = 40;BA.debugLine="Do While Starter.finishedInit = False";
Debug.ShouldStop(128);
if (true) break;

case 1:
//do while
this.state = 4;
while (RemoteObject.solveBoolean("=",parent.mostCurrent._starter._finishedinit /*RemoteObject*/ ,parent.mostCurrent.__c.getField(true,"False"))) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 1;
 BA.debugLineNum = 41;BA.debugLine="Sleep(100)";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("Sleep",main.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "startload"),BA.numberCast(int.class, 100));
this.state = 6;
return;
case 6:
//C
this.state = 1;
;
 if (true) break;

case 4:
//C
this.state = -1;
;
 BA.debugLineNum = 43;BA.debugLine="StartService(musicService)";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("StartService",main.processBA,(Object)((parent.mostCurrent._musicservice.getObject())));
 BA.debugLineNum = 46;BA.debugLine="StartActivity(Home_Activity)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((parent.mostCurrent._home_activity.getObject())));
 BA.debugLineNum = 47;BA.debugLine="Activity.Finish";
Debug.ShouldStop(16384);
parent.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 48;BA.debugLine="Return True";
Debug.ShouldStop(32768);
if (true) {
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,(parent.mostCurrent.__c.getField(true,"True")));return;};
 BA.debugLineNum = 49;BA.debugLine="End Sub";
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
}