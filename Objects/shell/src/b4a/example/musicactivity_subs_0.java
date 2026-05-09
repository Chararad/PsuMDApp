package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class musicactivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,19);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","activity_create", _firsttime);}
int _i = 0;
RemoteObject _title = RemoteObject.createImmutable("");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 19;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 20;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(524288);
switch (BA.switchObjectToInt(musicactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 22;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"musicLayout\")";
Debug.ShouldStop(4194304);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayout")),musicactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark\")";
Debug.ShouldStop(16777216);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayoutDark")),musicactivity.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 28;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"musicLayout2\")";
Debug.ShouldStop(268435456);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayout2")),musicactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark2\")";
Debug.ShouldStop(1073741824);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayoutDark2")),musicactivity.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 34;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"musicLayout3\")";
Debug.ShouldStop(4);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayout3")),musicactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark3\")";
Debug.ShouldStop(16);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayoutDark3")),musicactivity.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 42;BA.debugLine="If musicService.mediaPlayer.IsInitialized = False";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"IsInitialized"),musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 43;BA.debugLine="StartService(musicService)";
Debug.ShouldStop(1024);
musicactivity.mostCurrent.__c.runVoidMethod ("StartService",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())));
 };
 BA.debugLineNum = 46;BA.debugLine="For i = 0 To musicService.musicPlaylist.Size - 1";
Debug.ShouldStop(8192);
{
final int step24 = 1;
final int limit24 = RemoteObject.solve(new RemoteObject[] {musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step24 > 0 && _i <= limit24) || (step24 < 0 && _i >= limit24) ;_i = ((int)(0 + _i + step24))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 47;BA.debugLine="Dim title As String = musicService.musicPlaylist";
Debug.ShouldStop(16384);
_title = BA.ObjectToString(musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("title", _title);Debug.locals.put("title", _title);
 BA.debugLineNum = 48;BA.debugLine="title = title.SubString2(0, title.Length - 4)  '";
Debug.ShouldStop(32768);
_title = _title.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_title.runMethod(true,"length"),RemoteObject.createImmutable(4)}, "-",1, 1)));Debug.locals.put("title", _title);
 BA.debugLineNum = 49;BA.debugLine="title = title.SubString(7)";
Debug.ShouldStop(65536);
_title = _title.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 7)));Debug.locals.put("title", _title);
 BA.debugLineNum = 50;BA.debugLine="ListView1.AddSingleLine((i + 1) & \"   \" & title)";
Debug.ShouldStop(131072);
musicactivity.mostCurrent._listview1.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(RemoteObject.concat((RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1)),RemoteObject.createImmutable("   "),_title))));
 BA.debugLineNum = 51;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Col";
Debug.ShouldStop(262144);
musicactivity.mostCurrent._listview1.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",musicactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 55;BA.debugLine="uiTimer.Initialize(\"uiTimer\", 500)";
Debug.ShouldStop(4194304);
musicactivity._uitimer.runVoidMethod ("Initialize",musicactivity.processBA,(Object)(BA.ObjectToString("uiTimer")),(Object)(BA.numberCast(long.class, 500)));
 BA.debugLineNum = 56;BA.debugLine="uiTimer.Enabled = True";
Debug.ShouldStop(8388608);
musicactivity._uitimer.runMethod(true,"setEnabled",musicactivity.mostCurrent.__c.getField(true,"True"));
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
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,63);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 63;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 65;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("Activity_Resume (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,59);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","activity_resume");}
 BA.debugLineNum = 59;BA.debugLine="Sub Activity_Resume";
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
public static RemoteObject  _formatsongdur(RemoteObject _ms) throws Exception{
try {
		Debug.PushSubsStack("formatSongDur (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,67);
if (RapidSub.canDelegate("formatsongdur")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","formatsongdur", _ms);}
RemoteObject _seconds = RemoteObject.createImmutable(0);
RemoteObject _minutes = RemoteObject.createImmutable(0);
Debug.locals.put("ms", _ms);
 BA.debugLineNum = 67;BA.debugLine="Sub formatSongDur(ms As Int) As String";
Debug.ShouldStop(4);
 BA.debugLineNum = 68;BA.debugLine="Dim seconds As Int = ms / 1000";
Debug.ShouldStop(8);
_seconds = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_ms,RemoteObject.createImmutable(1000)}, "/",0, 0));Debug.locals.put("seconds", _seconds);Debug.locals.put("seconds", _seconds);
 BA.debugLineNum = 69;BA.debugLine="Dim minutes As Int = seconds / 60";
Debug.ShouldStop(16);
_minutes = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_seconds,RemoteObject.createImmutable(60)}, "/",0, 0));Debug.locals.put("minutes", _minutes);Debug.locals.put("minutes", _minutes);
 BA.debugLineNum = 70;BA.debugLine="seconds = seconds Mod 60";
Debug.ShouldStop(32);
_seconds = RemoteObject.solve(new RemoteObject[] {_seconds,RemoteObject.createImmutable(60)}, "%",0, 1);Debug.locals.put("seconds", _seconds);
 BA.debugLineNum = 71;BA.debugLine="Return NumberFormat(minutes, 2, 0) & \":\" & Number";
Debug.ShouldStop(64);
if (true) return RemoteObject.concat(musicactivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _minutes)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable(":"),musicactivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _seconds)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))));
 BA.debugLineNum = 72;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 12;BA.debugLine="Private SeekBar1 As SeekBar";
musicactivity.mostCurrent._seekbar1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SeekBarWrapper");
 //BA.debugLineNum = 13;BA.debugLine="Private songTitle As Label";
musicactivity.mostCurrent._songtitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private pauseBtn As Button";
musicactivity.mostCurrent._pausebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private songRuntime As Label";
musicactivity.mostCurrent._songruntime = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private ListView1 As ListView";
musicactivity.mostCurrent._listview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _listview1_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("ListView1_ItemClick (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,113);
if (RapidSub.canDelegate("listview1_itemclick")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","listview1_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 113;BA.debugLine="Sub ListView1_ItemClick(Position As Int, Value As";
Debug.ShouldStop(65536);
 BA.debugLineNum = 114;BA.debugLine="CallSub2(musicService, \"setSong\", Position)";
Debug.ShouldStop(131072);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew2",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(BA.ObjectToString("setSong")),(Object)((_position)));
 BA.debugLineNum = 115;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _nextbtn_click() throws Exception{
try {
		Debug.PushSubsStack("nextBtn_Click (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,101);
if (RapidSub.canDelegate("nextbtn_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","nextbtn_click");}
 BA.debugLineNum = 101;BA.debugLine="Sub nextBtn_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 102;BA.debugLine="CallSub(musicService, \"nextSong\")";
Debug.ShouldStop(32);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(RemoteObject.createImmutable("nextSong")));
 BA.debugLineNum = 103;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pausebtn_click() throws Exception{
try {
		Debug.PushSubsStack("pauseBtn_Click (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,109);
if (RapidSub.canDelegate("pausebtn_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","pausebtn_click");}
 BA.debugLineNum = 109;BA.debugLine="Sub pauseBtn_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 110;BA.debugLine="CallSub(musicService, \"pauseToggle\")";
Debug.ShouldStop(8192);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(RemoteObject.createImmutable("pauseToggle")));
 BA.debugLineNum = 111;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _prevbtn_click() throws Exception{
try {
		Debug.PushSubsStack("prevBtn_Click (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,105);
if (RapidSub.canDelegate("prevbtn_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","prevbtn_click");}
 BA.debugLineNum = 105;BA.debugLine="Sub prevBtn_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 106;BA.debugLine="CallSub(musicService, \"prevSong\")";
Debug.ShouldStop(512);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(RemoteObject.createImmutable("prevSong")));
 BA.debugLineNum = 107;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
musicactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 8;BA.debugLine="Dim uiTimer As Timer";
musicactivity._uitimer = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _seekbar1_valuechanged(RemoteObject _value,RemoteObject _userchanged) throws Exception{
try {
		Debug.PushSubsStack("SeekBar1_ValueChanged (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,95);
if (RapidSub.canDelegate("seekbar1_valuechanged")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","seekbar1_valuechanged", _value, _userchanged);}
Debug.locals.put("Value", _value);
Debug.locals.put("UserChanged", _userchanged);
 BA.debugLineNum = 95;BA.debugLine="Sub SeekBar1_ValueChanged(Value As Int, UserChange";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="If UserChanged Then";
Debug.ShouldStop(-2147483648);
if (_userchanged.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 97;BA.debugLine="musicService.mediaPlayer.Position = Value";
Debug.ShouldStop(1);
musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"setPosition",_value);
 };
 BA.debugLineNum = 99;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _uitimer_tick() throws Exception{
try {
		Debug.PushSubsStack("uiTimer_Tick (musicactivity) ","musicactivity",17,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,75);
if (RapidSub.canDelegate("uitimer_tick")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","uitimer_tick");}
RemoteObject _title = RemoteObject.createImmutable("");
 BA.debugLineNum = 75;BA.debugLine="Sub uiTimer_Tick";
Debug.ShouldStop(1024);
 BA.debugLineNum = 76;BA.debugLine="If musicService.mediaPlayer.IsInitialized Then";
Debug.ShouldStop(2048);
if (musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 78;BA.debugLine="Dim title As String = musicService.musicPlaylist";
Debug.ShouldStop(8192);
_title = BA.ObjectToString(musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(false,"Get",(Object)(musicactivity.mostCurrent._musicservice._currentsong /*RemoteObject*/ )));Debug.locals.put("title", _title);Debug.locals.put("title", _title);
 BA.debugLineNum = 79;BA.debugLine="title = title.SubString2(0, title.Length - 4)  '";
Debug.ShouldStop(16384);
_title = _title.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_title.runMethod(true,"length"),RemoteObject.createImmutable(4)}, "-",1, 1)));Debug.locals.put("title", _title);
 BA.debugLineNum = 80;BA.debugLine="title = title.SubString(7) 'remove /tracks";
Debug.ShouldStop(32768);
_title = _title.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 7)));Debug.locals.put("title", _title);
 BA.debugLineNum = 82;BA.debugLine="SeekBar1.Max = musicService.mediaPlayer.Duration";
Debug.ShouldStop(131072);
musicactivity.mostCurrent._seekbar1.runMethod(true,"setMax",musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getDuration"));
 BA.debugLineNum = 83;BA.debugLine="SeekBar1.Value = musicService.mediaPlayer.Positi";
Debug.ShouldStop(262144);
musicactivity.mostCurrent._seekbar1.runMethod(true,"setValue",musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getPosition"));
 BA.debugLineNum = 84;BA.debugLine="songRuntime.Text = formatSongDur(musicService.me";
Debug.ShouldStop(524288);
musicactivity.mostCurrent._songruntime.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_formatsongdur(musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getPosition")),RemoteObject.createImmutable(" / "),_formatsongdur(musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getDuration")))));
 BA.debugLineNum = 85;BA.debugLine="songTitle.Text = title";
Debug.ShouldStop(1048576);
musicactivity.mostCurrent._songtitle.runMethod(true,"setText",BA.ObjectToCharSequence(_title));
 BA.debugLineNum = 87;BA.debugLine="If musicService.mediaPlayer.IsPlaying Then";
Debug.ShouldStop(4194304);
if (musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"IsPlaying").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 88;BA.debugLine="pauseBtn.Text = \"❚❚\"";
Debug.ShouldStop(8388608);
musicactivity.mostCurrent._pausebtn.runMethod(true,"setText",BA.ObjectToCharSequence("❚❚"));
 }else {
 BA.debugLineNum = 90;BA.debugLine="pauseBtn.Text = \"▶\"";
Debug.ShouldStop(33554432);
musicactivity.mostCurrent._pausebtn.runMethod(true,"setText",BA.ObjectToCharSequence("▶"));
 };
 };
 BA.debugLineNum = 93;BA.debugLine="End Sub";
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