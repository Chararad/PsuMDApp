package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class starter_subs_0 {


public static RemoteObject  _application_error(RemoteObject _error,RemoteObject _stacktrace) throws Exception{
try {
		Debug.PushSubsStack("Application_Error (starter) ","starter",1,starter.processBA,starter.mostCurrent,64);
if (RapidSub.canDelegate("application_error")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","application_error", _error, _stacktrace);}
Debug.locals.put("Error", _error);
Debug.locals.put("StackTrace", _stacktrace);
 BA.debugLineNum = 64;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 65;BA.debugLine="Return True";
Debug.ShouldStop(1);
if (true) return starter.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 66;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _copytracksifneeded() throws Exception{
try {
		Debug.PushSubsStack("CopyTracksIfNeeded (starter) ","starter",1,starter.processBA,starter.mostCurrent,72);
if (RapidSub.canDelegate("copytracksifneeded")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","copytracksifneeded");}
RemoteObject _playlist = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _trackname = RemoteObject.createImmutable("");
RemoteObject _filename = RemoteObject.createImmutable("");
 BA.debugLineNum = 72;BA.debugLine="Sub CopyTracksIfNeeded";
Debug.ShouldStop(128);
 BA.debugLineNum = 73;BA.debugLine="Dim playlist As List";
Debug.ShouldStop(256);
_playlist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("playlist", _playlist);
 BA.debugLineNum = 74;BA.debugLine="playlist.Initialize";
Debug.ShouldStop(512);
_playlist.runVoidMethod ("Initialize");
 BA.debugLineNum = 75;BA.debugLine="playlist.Add(\"tracks/intro.mp3\")";
Debug.ShouldStop(1024);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/intro.mp3"))));
 BA.debugLineNum = 76;BA.debugLine="playlist.Add(\"tracks/taiyaki.mp3\")";
Debug.ShouldStop(2048);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/taiyaki.mp3"))));
 BA.debugLineNum = 77;BA.debugLine="playlist.Add(\"tracks/feel special.mp3\")";
Debug.ShouldStop(4096);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/feel special.mp3"))));
 BA.debugLineNum = 78;BA.debugLine="playlist.Add(\"tracks/union.mp3\")";
Debug.ShouldStop(8192);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/union.mp3"))));
 BA.debugLineNum = 79;BA.debugLine="playlist.Add(\"tracks/two in the morning.mp3\")";
Debug.ShouldStop(16384);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/two in the morning.mp3"))));
 BA.debugLineNum = 80;BA.debugLine="playlist.Add(\"tracks/happily ever after.mp3\")";
Debug.ShouldStop(32768);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/happily ever after.mp3"))));
 BA.debugLineNum = 81;BA.debugLine="playlist.Add(\"tracks/cookie.mp3\")";
Debug.ShouldStop(65536);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/cookie.mp3"))));
 BA.debugLineNum = 82;BA.debugLine="playlist.Add(\"tracks/comfy vibes.mp3\")";
Debug.ShouldStop(131072);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/comfy vibes.mp3"))));
 BA.debugLineNum = 83;BA.debugLine="playlist.Add(\"tracks/dango.mp3\")";
Debug.ShouldStop(262144);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/dango.mp3"))));
 BA.debugLineNum = 84;BA.debugLine="playlist.Add(\"tracks/iced caramel macchiato.mp3\")";
Debug.ShouldStop(524288);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/iced caramel macchiato.mp3"))));
 BA.debugLineNum = 85;BA.debugLine="playlist.Add(\"tracks/in dreamland.mp3\")";
Debug.ShouldStop(1048576);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/in dreamland.mp3"))));
 BA.debugLineNum = 86;BA.debugLine="playlist.Add(\"tracks/space aquarium.mp3\")";
Debug.ShouldStop(2097152);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/space aquarium.mp3"))));
 BA.debugLineNum = 87;BA.debugLine="playlist.Add(\"tracks/sunshine & butterflies.mp3\")";
Debug.ShouldStop(4194304);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/sunshine & butterflies.mp3"))));
 BA.debugLineNum = 88;BA.debugLine="playlist.Add(\"tracks/soda pop.mp3\")";
Debug.ShouldStop(8388608);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/soda pop.mp3"))));
 BA.debugLineNum = 89;BA.debugLine="playlist.Add(\"tracks/matcha latte.mp3\")";
Debug.ShouldStop(16777216);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/matcha latte.mp3"))));
 BA.debugLineNum = 90;BA.debugLine="playlist.Add(\"tracks/midnight.mp3\")";
Debug.ShouldStop(33554432);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/midnight.mp3"))));
 BA.debugLineNum = 91;BA.debugLine="playlist.Add(\"tracks/rose water.mp3\")";
Debug.ShouldStop(67108864);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/rose water.mp3"))));
 BA.debugLineNum = 92;BA.debugLine="playlist.Add(\"tracks/hot.mp3\")";
Debug.ShouldStop(134217728);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/hot.mp3"))));
 BA.debugLineNum = 93;BA.debugLine="playlist.Add(\"tracks/on the top.mp3\")";
Debug.ShouldStop(268435456);
_playlist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/on the top.mp3"))));
 BA.debugLineNum = 95;BA.debugLine="If File.Exists(File.DirInternal, \"tracks\") = Fals";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",starter.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("tracks"))),starter.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 96;BA.debugLine="File.MakeDir(File.DirInternal, \"tracks\")";
Debug.ShouldStop(-2147483648);
starter.mostCurrent.__c.getField(false,"File").runVoidMethod ("MakeDir",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("tracks")));
 };
 BA.debugLineNum = 98;BA.debugLine="For i = 0 To playlist.Size - 1";
Debug.ShouldStop(2);
{
final int step25 = 1;
final int limit25 = RemoteObject.solve(new RemoteObject[] {_playlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step25 > 0 && _i <= limit25) || (step25 < 0 && _i >= limit25) ;_i = ((int)(0 + _i + step25))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 99;BA.debugLine="Dim trackName As String = playlist.Get(i)";
Debug.ShouldStop(4);
_trackname = BA.ObjectToString(_playlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("trackName", _trackname);Debug.locals.put("trackName", _trackname);
 BA.debugLineNum = 100;BA.debugLine="Dim fileName As String = trackName.SubString(tra";
Debug.ShouldStop(8);
_filename = _trackname.runMethod(true,"substring",(Object)(RemoteObject.solve(new RemoteObject[] {_trackname.runMethod(true,"lastIndexOf",(Object)(RemoteObject.createImmutable("/"))),RemoteObject.createImmutable(1)}, "+",1, 1)));Debug.locals.put("fileName", _filename);Debug.locals.put("fileName", _filename);
 BA.debugLineNum = 101;BA.debugLine="If File.Exists(File.DirInternal & \"/tracks\", fil";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",starter.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(RemoteObject.concat(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal"),RemoteObject.createImmutable("/tracks"))),(Object)(_filename)),starter.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 102;BA.debugLine="File.Copy(File.DirAssets, trackName, File.DirIn";
Debug.ShouldStop(32);
starter.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(_trackname),(Object)(RemoteObject.concat(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal"),RemoteObject.createImmutable("/tracks"))),(Object)(_filename));
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 105;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public darkMode As Boolean = False";
starter._darkmode = starter.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 10;BA.debugLine="Public themeNumber As Int = 0";
starter._themenumber = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 11;BA.debugLine="Public notesKvs As KeyValueStore";
starter._noteskvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 12;BA.debugLine="Public prefKvs As KeyValueStore";
starter._prefkvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 13;BA.debugLine="Public calKvs As KeyValueStore";
starter._calkvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 14;BA.debugLine="Public calendarMap As Map";
starter._calendarmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 15;BA.debugLine="Public deckKvs As KeyValueStore";
starter._deckkvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 16;BA.debugLine="Public taskKvs As KeyValueStore";
starter._taskkvs = RemoteObject.createNew ("b4a.example3.keyvaluestore");
 //BA.debugLineNum = 17;BA.debugLine="Public deck As Map";
starter._deck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 18;BA.debugLine="Public finishedInit As Boolean = False";
starter._finishedinit = starter.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 19;BA.debugLine="Public themeChanged As Boolean = False";
starter._themechanged = starter.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (starter) ","starter",1,starter.processBA,starter.mostCurrent,22);
if (RapidSub.canDelegate("service_create")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_create");}
 BA.debugLineNum = 22;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 23;BA.debugLine="prefKvs.Initialize(File.DirInternal, \"prefData\")";
Debug.ShouldStop(4194304);
starter._prefkvs.runVoidMethod ("_initialize",starter.processBA,(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("prefData")));
 BA.debugLineNum = 25;BA.debugLine="If prefKvs.ContainsKey(\"darkMode\") Then";
Debug.ShouldStop(16777216);
if (starter._prefkvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("darkMode"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 26;BA.debugLine="darkMode = prefKvs.Get(\"darkMode\")";
Debug.ShouldStop(33554432);
starter._darkmode = BA.ObjectToBoolean(starter._prefkvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("darkMode"))));
 };
 BA.debugLineNum = 29;BA.debugLine="If prefKvs.ContainsKey(\"themeNumber\") Then";
Debug.ShouldStop(268435456);
if (starter._prefkvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("themeNumber"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 30;BA.debugLine="themeNumber = prefKvs.Get(\"themeNumber\")";
Debug.ShouldStop(536870912);
starter._themenumber = BA.numberCast(int.class, starter._prefkvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("themeNumber"))));
 };
 BA.debugLineNum = 33;BA.debugLine="calKvs.Initialize(File.DirInternal, \"mydata\")";
Debug.ShouldStop(1);
starter._calkvs.runVoidMethod ("_initialize",starter.processBA,(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("mydata")));
 BA.debugLineNum = 34;BA.debugLine="If calKvs.ContainsKey(\"CalendarKVS\") Then";
Debug.ShouldStop(2);
if (starter._calkvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("CalendarKVS"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 35;BA.debugLine="calendarMap = calKvs.Get(\"CalendarKVS\")";
Debug.ShouldStop(4);
starter._calendarmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), starter._calkvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("CalendarKVS"))));
 }else {
 BA.debugLineNum = 37;BA.debugLine="calendarMap.Initialize";
Debug.ShouldStop(16);
starter._calendarmap.runVoidMethod ("Initialize");
 };
 BA.debugLineNum = 40;BA.debugLine="deckKvs.Initialize(File.DirInternal, \"mydata\")";
Debug.ShouldStop(128);
starter._deckkvs.runVoidMethod ("_initialize",starter.processBA,(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("mydata")));
 BA.debugLineNum = 41;BA.debugLine="If deckKvs.ContainsKey(\"deck_data\") Then";
Debug.ShouldStop(256);
if (starter._deckkvs.runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("deck_data"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 42;BA.debugLine="deck = deckKvs.Get(\"deck_data\")";
Debug.ShouldStop(512);
starter._deck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), starter._deckkvs.runMethod(false,"_get",(Object)(RemoteObject.createImmutable("deck_data"))));
 }else {
 BA.debugLineNum = 44;BA.debugLine="deck.Initialize";
Debug.ShouldStop(2048);
starter._deck.runVoidMethod ("Initialize");
 };
 BA.debugLineNum = 47;BA.debugLine="taskKvs.Initialize(File.DirInternal, \"todoListDat";
Debug.ShouldStop(16384);
starter._taskkvs.runVoidMethod ("_initialize",starter.processBA,(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("todoListData")));
 BA.debugLineNum = 48;BA.debugLine="notesKvs.Initialize(File.DirInternal, \"notes_data";
Debug.ShouldStop(32768);
starter._noteskvs.runVoidMethod ("_initialize",starter.processBA,(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("notes_data")));
 BA.debugLineNum = 50;BA.debugLine="CopyTracksIfNeeded";
Debug.ShouldStop(131072);
_copytracksifneeded();
 BA.debugLineNum = 52;BA.debugLine="finishedInit = True";
Debug.ShouldStop(524288);
starter._finishedinit = starter.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_destroy() throws Exception{
try {
		Debug.PushSubsStack("Service_Destroy (starter) ","starter",1,starter.processBA,starter.mostCurrent,68);
if (RapidSub.canDelegate("service_destroy")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_destroy");}
 BA.debugLineNum = 68;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(8);
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
public static RemoteObject  _service_start(RemoteObject _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (starter) ","starter",1,starter.processBA,starter.mostCurrent,55);
if (RapidSub.canDelegate("service_start")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_start", _startingintent);}
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 55;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 56;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
Debug.ShouldStop(8388608);
starter.mostCurrent._service.runVoidMethod ("StopAutomaticForeground");
 BA.debugLineNum = 57;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_taskremoved() throws Exception{
try {
		Debug.PushSubsStack("Service_TaskRemoved (starter) ","starter",1,starter.processBA,starter.mostCurrent,59);
if (RapidSub.canDelegate("service_taskremoved")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_taskremoved");}
 BA.debugLineNum = 59;BA.debugLine="Sub Service_TaskRemoved";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 61;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}