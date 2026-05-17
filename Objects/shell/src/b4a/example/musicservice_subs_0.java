package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class musicservice_subs_0 {


public static RemoteObject  _nextsong() throws Exception{
try {
		Debug.PushSubsStack("nextSong (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,86);
if (RapidSub.canDelegate("nextsong")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","nextsong");}
 BA.debugLineNum = 86;BA.debugLine="Sub nextSong";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 87;BA.debugLine="currentSong = currentSong + 1";
Debug.ShouldStop(4194304);
musicservice._currentsong = RemoteObject.solve(new RemoteObject[] {musicservice._currentsong,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 88;BA.debugLine="If currentSong >= musicPlaylist.Size Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("g",musicservice._currentsong,BA.numberCast(double.class, musicservice._musicplaylist.runMethod(true,"getSize")))) { 
 BA.debugLineNum = 89;BA.debugLine="currentSong = 0";
Debug.ShouldStop(16777216);
musicservice._currentsong = BA.numberCast(int.class, 0);
 };
 BA.debugLineNum = 91;BA.debugLine="playSong";
Debug.ShouldStop(67108864);
_playsong();
 BA.debugLineNum = 92;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pausetoggle() throws Exception{
try {
		Debug.PushSubsStack("pauseToggle (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,52);
if (RapidSub.canDelegate("pausetoggle")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","pausetoggle");}
 BA.debugLineNum = 52;BA.debugLine="Sub pauseToggle";
Debug.ShouldStop(524288);
 BA.debugLineNum = 53;BA.debugLine="If mediaPlayer.IsPlaying Then";
Debug.ShouldStop(1048576);
if (musicservice._mediaplayer.runMethod(true,"IsPlaying").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 54;BA.debugLine="mediaPlayer.Pause";
Debug.ShouldStop(2097152);
musicservice._mediaplayer.runVoidMethod ("Pause");
 }else {
 BA.debugLineNum = 56;BA.debugLine="mediaPlayer.Play";
Debug.ShouldStop(8388608);
musicservice._mediaplayer.runVoidMethod ("Play");
 };
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
public static RemoteObject  _playsong() throws Exception{
try {
		Debug.PushSubsStack("playSong (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,65);
if (RapidSub.canDelegate("playsong")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","playsong");}
RemoteObject _trackname = RemoteObject.createImmutable("");
RemoteObject _filename = RemoteObject.createImmutable("");
 BA.debugLineNum = 65;BA.debugLine="Sub playSong";
Debug.ShouldStop(1);
 BA.debugLineNum = 66;BA.debugLine="If mediaPlayer.IsInitialized Then";
Debug.ShouldStop(2);
if (musicservice._mediaplayer.runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 67;BA.debugLine="mediaPlayer.Stop";
Debug.ShouldStop(4);
musicservice._mediaplayer.runVoidMethod ("Stop");
 };
 BA.debugLineNum = 69;BA.debugLine="Dim trackName As String = musicPlaylist.Get(curre";
Debug.ShouldStop(16);
_trackname = BA.ObjectToString(musicservice._musicplaylist.runMethod(false,"Get",(Object)(musicservice._currentsong)));Debug.locals.put("trackName", _trackname);Debug.locals.put("trackName", _trackname);
 BA.debugLineNum = 70;BA.debugLine="Dim fileName As String = trackName.SubString(trac";
Debug.ShouldStop(32);
_filename = _trackname.runMethod(true,"substring",(Object)(RemoteObject.solve(new RemoteObject[] {_trackname.runMethod(true,"lastIndexOf",(Object)(RemoteObject.createImmutable("/"))),RemoteObject.createImmutable(1)}, "+",1, 1)));Debug.locals.put("fileName", _filename);Debug.locals.put("fileName", _filename);
 BA.debugLineNum = 72;BA.debugLine="mediaPlayer.Load(File.DirInternal & \"/tracks\", fi";
Debug.ShouldStop(128);
musicservice._mediaplayer.runVoidMethod ("Load",(Object)(RemoteObject.concat(musicservice.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal"),RemoteObject.createImmutable("/tracks"))),(Object)(_filename));
 BA.debugLineNum = 73;BA.debugLine="mediaPlayer.Play";
Debug.ShouldStop(256);
musicservice._mediaplayer.runVoidMethod ("Play");
 BA.debugLineNum = 74;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _prevsong() throws Exception{
try {
		Debug.PushSubsStack("prevSong (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,94);
if (RapidSub.canDelegate("prevsong")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","prevsong");}
 BA.debugLineNum = 94;BA.debugLine="Sub prevSong";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 95;BA.debugLine="currentSong = currentSong - 1";
Debug.ShouldStop(1073741824);
musicservice._currentsong = RemoteObject.solve(new RemoteObject[] {musicservice._currentsong,RemoteObject.createImmutable(1)}, "-",1, 1);
 BA.debugLineNum = 96;BA.debugLine="If currentSong < 0 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("<",musicservice._currentsong,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 97;BA.debugLine="currentSong = musicPlaylist.Size - 1";
Debug.ShouldStop(1);
musicservice._currentsong = RemoteObject.solve(new RemoteObject[] {musicservice._musicplaylist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1);
 };
 BA.debugLineNum = 99;BA.debugLine="playSong";
Debug.ShouldStop(4);
_playsong();
 BA.debugLineNum = 100;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 7;BA.debugLine="Dim mediaPlayer As MediaPlayer";
musicservice._mediaplayer = RemoteObject.createNew ("anywheresoftware.b4a.objects.MediaPlayerWrapper");
 //BA.debugLineNum = 8;BA.debugLine="Dim musicPlaylist As List";
musicservice._musicplaylist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 9;BA.debugLine="Dim currentSong As Int";
musicservice._currentsong = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 10;BA.debugLine="Dim songTimer As Timer";
musicservice._songtimer = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,13);
if (RapidSub.canDelegate("service_create")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","service_create");}
 BA.debugLineNum = 13;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(4096);
 BA.debugLineNum = 14;BA.debugLine="mediaPlayer.Initialize";
Debug.ShouldStop(8192);
musicservice._mediaplayer.runVoidMethod ("Initialize");
 BA.debugLineNum = 15;BA.debugLine="musicPlaylist.Initialize";
Debug.ShouldStop(16384);
musicservice._musicplaylist.runVoidMethod ("Initialize");
 BA.debugLineNum = 17;BA.debugLine="musicPlaylist.Add(\"tracks/intro.mp3\")";
Debug.ShouldStop(65536);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/intro.mp3"))));
 BA.debugLineNum = 18;BA.debugLine="musicPlaylist.Add(\"tracks/taiyaki.mp3\")";
Debug.ShouldStop(131072);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/taiyaki.mp3"))));
 BA.debugLineNum = 19;BA.debugLine="musicPlaylist.Add(\"tracks/feel special.mp3\")";
Debug.ShouldStop(262144);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/feel special.mp3"))));
 BA.debugLineNum = 20;BA.debugLine="musicPlaylist.Add(\"tracks/union.mp3\")";
Debug.ShouldStop(524288);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/union.mp3"))));
 BA.debugLineNum = 21;BA.debugLine="musicPlaylist.Add(\"tracks/two in the morning.mp3\"";
Debug.ShouldStop(1048576);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/two in the morning.mp3"))));
 BA.debugLineNum = 22;BA.debugLine="musicPlaylist.Add(\"tracks/happily ever after.mp3\"";
Debug.ShouldStop(2097152);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/happily ever after.mp3"))));
 BA.debugLineNum = 23;BA.debugLine="musicPlaylist.Add(\"tracks/cookie.mp3\")";
Debug.ShouldStop(4194304);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/cookie.mp3"))));
 BA.debugLineNum = 24;BA.debugLine="musicPlaylist.Add(\"tracks/comfy vibes.mp3\")";
Debug.ShouldStop(8388608);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/comfy vibes.mp3"))));
 BA.debugLineNum = 25;BA.debugLine="musicPlaylist.Add(\"tracks/dango.mp3\")";
Debug.ShouldStop(16777216);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/dango.mp3"))));
 BA.debugLineNum = 26;BA.debugLine="musicPlaylist.Add(\"tracks/iced caramel macchiato.";
Debug.ShouldStop(33554432);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/iced caramel macchiato.mp3"))));
 BA.debugLineNum = 27;BA.debugLine="musicPlaylist.Add(\"tracks/in dreamland.mp3\")";
Debug.ShouldStop(67108864);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/in dreamland.mp3"))));
 BA.debugLineNum = 28;BA.debugLine="musicPlaylist.Add(\"tracks/space aquarium.mp3\")";
Debug.ShouldStop(134217728);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/space aquarium.mp3"))));
 BA.debugLineNum = 29;BA.debugLine="musicPlaylist.Add(\"tracks/sunshine & butterflies.";
Debug.ShouldStop(268435456);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/sunshine & butterflies.mp3"))));
 BA.debugLineNum = 30;BA.debugLine="musicPlaylist.Add(\"tracks/soda pop.mp3\")";
Debug.ShouldStop(536870912);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/soda pop.mp3"))));
 BA.debugLineNum = 31;BA.debugLine="musicPlaylist.Add(\"tracks/matcha latte.mp3\")";
Debug.ShouldStop(1073741824);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/matcha latte.mp3"))));
 BA.debugLineNum = 32;BA.debugLine="musicPlaylist.Add(\"tracks/midnight.mp3\")";
Debug.ShouldStop(-2147483648);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/midnight.mp3"))));
 BA.debugLineNum = 33;BA.debugLine="musicPlaylist.Add(\"tracks/rose water.mp3\")";
Debug.ShouldStop(1);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/rose water.mp3"))));
 BA.debugLineNum = 34;BA.debugLine="musicPlaylist.Add(\"tracks/hot.mp3\")";
Debug.ShouldStop(2);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/hot.mp3"))));
 BA.debugLineNum = 35;BA.debugLine="musicPlaylist.Add(\"tracks/on the top.mp3\")";
Debug.ShouldStop(4);
musicservice._musicplaylist.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("tracks/on the top.mp3"))));
 BA.debugLineNum = 37;BA.debugLine="currentSong = 0";
Debug.ShouldStop(16);
musicservice._currentsong = BA.numberCast(int.class, 0);
 BA.debugLineNum = 39;BA.debugLine="songTimer.Initialize(\"songTimer\", 500)";
Debug.ShouldStop(64);
musicservice._songtimer.runVoidMethod ("Initialize",musicservice.processBA,(Object)(BA.ObjectToString("songTimer")),(Object)(BA.numberCast(long.class, 500)));
 BA.debugLineNum = 40;BA.debugLine="songTimer.Enabled = True";
Debug.ShouldStop(128);
musicservice._songtimer.runMethod(true,"setEnabled",musicservice.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 41;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("Service_Destroy (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,48);
if (RapidSub.canDelegate("service_destroy")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","service_destroy");}
 BA.debugLineNum = 48;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(32768);
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
public static void  _service_start(RemoteObject _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,43);
if (RapidSub.canDelegate("service_start")) { b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","service_start", _startingintent); return;}
ResumableSub_Service_Start rsub = new ResumableSub_Service_Start(null,_startingintent);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Service_Start extends BA.ResumableSub {
public ResumableSub_Service_Start(b4a.example.musicservice parent,RemoteObject _startingintent) {
this.parent = parent;
this._startingintent = _startingintent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.musicservice parent;
RemoteObject _startingintent;
RemoteObject _qiu = RemoteObject.createImmutable(false);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,43);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 44;BA.debugLine="Service.StopAutomaticForeground";
Debug.ShouldStop(2048);
parent.mostCurrent._service.runVoidMethod ("StopAutomaticForeground");
 BA.debugLineNum = 45;BA.debugLine="Wait For (waitStarter) Complete (qiu As Boolean)";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","complete", musicservice.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "musicservice", "service_start"), _waitstarter());
this.state = 1;
return;
case 1:
//C
this.state = -1;
_qiu = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("qiu", _qiu);
;
 BA.debugLineNum = 46;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
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
public static void  _complete(RemoteObject _qiu) throws Exception{
}
public static RemoteObject  _setsong(RemoteObject _index) throws Exception{
try {
		Debug.PushSubsStack("setSong (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,60);
if (RapidSub.canDelegate("setsong")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","setsong", _index);}
Debug.locals.put("index", _index);
 BA.debugLineNum = 60;BA.debugLine="Sub setSong(index As Int)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 61;BA.debugLine="currentSong = index";
Debug.ShouldStop(268435456);
musicservice._currentsong = _index;
 BA.debugLineNum = 62;BA.debugLine="playSong";
Debug.ShouldStop(536870912);
_playsong();
 BA.debugLineNum = 63;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _songtimer_tick() throws Exception{
try {
		Debug.PushSubsStack("songTimer_Tick (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,76);
if (RapidSub.canDelegate("songtimer_tick")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","songtimer_tick");}
 BA.debugLineNum = 76;BA.debugLine="Sub songTimer_Tick";
Debug.ShouldStop(2048);
 BA.debugLineNum = 77;BA.debugLine="If mediaPlayer.IsInitialized Then";
Debug.ShouldStop(4096);
if (musicservice._mediaplayer.runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 78;BA.debugLine="If mediaPlayer.IsPlaying = False And mediaPlayer";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",musicservice._mediaplayer.runMethod(true,"IsPlaying"),musicservice.mostCurrent.__c.getField(true,"False")) && RemoteObject.solveBoolean(">",musicservice._mediaplayer.runMethod(true,"getDuration"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 79;BA.debugLine="If mediaPlayer.Position >= mediaPlayer.Duration";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("g",musicservice._mediaplayer.runMethod(true,"getPosition"),BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {musicservice._mediaplayer.runMethod(true,"getDuration"),RemoteObject.createImmutable(100)}, "-",1, 1)))) { 
 BA.debugLineNum = 80;BA.debugLine="nextSong";
Debug.ShouldStop(32768);
_nextsong();
 };
 };
 };
 BA.debugLineNum = 84;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _waitstarter() throws Exception{
try {
		Debug.PushSubsStack("waitStarter (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,102);
if (RapidSub.canDelegate("waitstarter")) { return b4a.example.musicservice.remoteMe.runUserSub(false, "musicservice","waitstarter");}
ResumableSub_waitStarter rsub = new ResumableSub_waitStarter(null);
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
public static class ResumableSub_waitStarter extends BA.ResumableSub {
public ResumableSub_waitStarter(b4a.example.musicservice parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.musicservice parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("waitStarter (musicservice) ","musicservice",21,musicservice.processBA,musicservice.mostCurrent,102);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = 1;
 BA.debugLineNum = 103;BA.debugLine="Do While Starter.finishedInit = False";
Debug.ShouldStop(64);
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
 BA.debugLineNum = 104;BA.debugLine="Sleep(100)";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("Sleep",musicservice.processBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "musicservice", "waitstarter"),BA.numberCast(int.class, 100));
this.state = 8;
return;
case 8:
//C
this.state = 1;
;
 if (true) break;
;
 BA.debugLineNum = 107;BA.debugLine="If mediaPlayer.IsPlaying = False Then";
Debug.ShouldStop(1024);

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",parent._mediaplayer.runMethod(true,"IsPlaying"),parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 108;BA.debugLine="playSong";
Debug.ShouldStop(2048);
_playsong();
 if (true) break;

case 7:
//C
this.state = -1;
;
 BA.debugLineNum = 111;BA.debugLine="Return True";
Debug.ShouldStop(16384);
if (true) {
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,(parent.mostCurrent.__c.getField(true,"True")));return;};
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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