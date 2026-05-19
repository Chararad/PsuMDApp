package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class musicactivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,22);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 23;BA.debugLine="chooser.Initialize(\"chooser\")";
Debug.ShouldStop(4194304);
musicactivity._chooser.runVoidMethod ("Initialize",(Object)(RemoteObject.createImmutable("chooser")));
 BA.debugLineNum = 24;BA.debugLine="LoadMusicPlayer";
Debug.ShouldStop(8388608);
_loadmusicplayer();
 BA.debugLineNum = 25;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Pause (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,180);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 180;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 182;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,176);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","activity_resume");}
 BA.debugLineNum = 176;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(32768);
 BA.debugLineNum = 178;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnupload_click() throws Exception{
try {
		Debug.PushSubsStack("btnUpload_Click (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,93);
if (RapidSub.canDelegate("btnupload_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","btnupload_click");}
 BA.debugLineNum = 93;BA.debugLine="Sub btnUpload_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 94;BA.debugLine="chooser.Show(\"audio/*\", \"Choose Music File\")";
Debug.ShouldStop(536870912);
musicactivity._chooser.runVoidMethod ("Show",musicactivity.processBA,(Object)(BA.ObjectToString("audio/*")),(Object)(RemoteObject.createImmutable("Choose Music File")));
 BA.debugLineNum = 95;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _chooser_result(RemoteObject _success,RemoteObject _dir,RemoteObject _filename) throws Exception{
try {
		Debug.PushSubsStack("chooser_Result (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,97);
if (RapidSub.canDelegate("chooser_result")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","chooser_result", _success, _dir, _filename);}
RemoteObject _fulluri = RemoteObject.createImmutable("");
RemoteObject _destname = RemoteObject.createImmutable("");
RemoteObject _destdir = RemoteObject.createImmutable("");
RemoteObject _cachedpath = RemoteObject.createImmutable("");
RemoteObject _displaytitle = RemoteObject.createImmutable("");
RemoteObject _idx = RemoteObject.createImmutable(0);
Debug.locals.put("Success", _success);
Debug.locals.put("Dir", _dir);
Debug.locals.put("FileName", _filename);
 BA.debugLineNum = 97;BA.debugLine="Sub chooser_Result (Success As Boolean, Dir As Str";
Debug.ShouldStop(1);
 BA.debugLineNum = 98;BA.debugLine="If Success Then";
Debug.ShouldStop(2);
if (_success.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 106;BA.debugLine="Dim fullUri As String";
Debug.ShouldStop(512);
_fulluri = RemoteObject.createImmutable("");Debug.locals.put("fullUri", _fulluri);
 BA.debugLineNum = 107;BA.debugLine="fullUri = Dir & FileName";
Debug.ShouldStop(1024);
_fulluri = RemoteObject.concat(_dir,_filename);Debug.locals.put("fullUri", _fulluri);
 BA.debugLineNum = 111;BA.debugLine="Dim destName As String";
Debug.ShouldStop(16384);
_destname = RemoteObject.createImmutable("");Debug.locals.put("destName", _destname);
 BA.debugLineNum = 112;BA.debugLine="destName = fullUri.SubString(fullUri.LastIndexOf";
Debug.ShouldStop(32768);
_destname = _fulluri.runMethod(true,"substring",(Object)(RemoteObject.solve(new RemoteObject[] {_fulluri.runMethod(true,"lastIndexOf",(Object)(RemoteObject.createImmutable("/"))),RemoteObject.createImmutable(1)}, "+",1, 1)));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 113;BA.debugLine="destName = destName.Replace(\"%20\", \" \")";
Debug.ShouldStop(65536);
_destname = _destname.runMethod(true,"replace",(Object)(BA.ObjectToString("%20")),(Object)(RemoteObject.createImmutable(" ")));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 114;BA.debugLine="destName = destName.Replace(\"%26\", \"&\")";
Debug.ShouldStop(131072);
_destname = _destname.runMethod(true,"replace",(Object)(BA.ObjectToString("%26")),(Object)(RemoteObject.createImmutable("&")));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 115;BA.debugLine="destName = destName.Replace(\"%27\", \"'\")";
Debug.ShouldStop(262144);
_destname = _destname.runMethod(true,"replace",(Object)(BA.ObjectToString("%27")),(Object)(RemoteObject.createImmutable("'")));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 116;BA.debugLine="destName = destName.Replace(\"%28\", \"(\")";
Debug.ShouldStop(524288);
_destname = _destname.runMethod(true,"replace",(Object)(BA.ObjectToString("%28")),(Object)(RemoteObject.createImmutable("(")));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 117;BA.debugLine="destName = destName.Replace(\"%29\", \")\")";
Debug.ShouldStop(1048576);
_destname = _destname.runMethod(true,"replace",(Object)(BA.ObjectToString("%29")),(Object)(RemoteObject.createImmutable(")")));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 118;BA.debugLine="destName = destName.Replace(\"%2C\", \",\")";
Debug.ShouldStop(2097152);
_destname = _destname.runMethod(true,"replace",(Object)(BA.ObjectToString("%2C")),(Object)(RemoteObject.createImmutable(",")));Debug.locals.put("destName", _destname);
 BA.debugLineNum = 121;BA.debugLine="If destName.EndsWith(\".mp3\") = False And destNam";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",_destname.runMethod(true,"endsWith",(Object)(RemoteObject.createImmutable(".mp3"))),musicactivity.mostCurrent.__c.getField(true,"False")) && RemoteObject.solveBoolean("=",_destname.runMethod(true,"endsWith",(Object)(RemoteObject.createImmutable(".MP3"))),musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 122;BA.debugLine="destName = destName & \".mp3\"";
Debug.ShouldStop(33554432);
_destname = RemoteObject.concat(_destname,RemoteObject.createImmutable(".mp3"));Debug.locals.put("destName", _destname);
 };
 BA.debugLineNum = 126;BA.debugLine="Dim destDir As String";
Debug.ShouldStop(536870912);
_destdir = RemoteObject.createImmutable("");Debug.locals.put("destDir", _destdir);
 BA.debugLineNum = 127;BA.debugLine="destDir = File.DirInternalCache";
Debug.ShouldStop(1073741824);
_destdir = musicactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternalCache");Debug.locals.put("destDir", _destdir);
 BA.debugLineNum = 129;BA.debugLine="Try";
Debug.ShouldStop(1);
try { BA.debugLineNum = 130;BA.debugLine="File.Copy(Dir, FileName, destDir, destName)";
Debug.ShouldStop(2);
musicactivity.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(_dir),(Object)(_filename),(Object)(_destdir),(Object)(_destname));
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e20) {
			BA.rdebugUtils.runVoidMethod("setLastException",musicactivity.processBA, e20.toString()); BA.debugLineNum = 132;BA.debugLine="ToastMessageShow(\"❌ Could not copy file: \" & La";
Debug.ShouldStop(8);
musicactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("❌ Could not copy file: "),musicactivity.mostCurrent.__c.runMethod(false,"LastException",musicactivity.mostCurrent.activityBA).runMethod(true,"getMessage")))),(Object)(musicactivity.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 133;BA.debugLine="Return";
Debug.ShouldStop(16);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 137;BA.debugLine="Dim cachedPath As String";
Debug.ShouldStop(256);
_cachedpath = RemoteObject.createImmutable("");Debug.locals.put("cachedPath", _cachedpath);
 BA.debugLineNum = 138;BA.debugLine="cachedPath = destDir & \"/\" & destName";
Debug.ShouldStop(512);
_cachedpath = RemoteObject.concat(_destdir,RemoteObject.createImmutable("/"),_destname);Debug.locals.put("cachedPath", _cachedpath);
 BA.debugLineNum = 139;BA.debugLine="musicService.musicPlaylist.Add(cachedPath)";
Debug.ShouldStop(1024);
musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runVoidMethod ("Add",(Object)((_cachedpath)));
 BA.debugLineNum = 142;BA.debugLine="Dim displayTitle As String";
Debug.ShouldStop(8192);
_displaytitle = RemoteObject.createImmutable("");Debug.locals.put("displayTitle", _displaytitle);
 BA.debugLineNum = 143;BA.debugLine="displayTitle = getDisplayTitle(cachedPath)";
Debug.ShouldStop(16384);
_displaytitle = _getdisplaytitle(_cachedpath);Debug.locals.put("displayTitle", _displaytitle);
 BA.debugLineNum = 144;BA.debugLine="Dim idx As Int = musicService.musicPlaylist.Size";
Debug.ShouldStop(32768);
_idx = musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(true,"getSize");Debug.locals.put("idx", _idx);Debug.locals.put("idx", _idx);
 BA.debugLineNum = 145;BA.debugLine="ListView1.AddSingleLine(idx & \"   \" & displayTit";
Debug.ShouldStop(65536);
musicactivity.mostCurrent._listview1.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(_idx,RemoteObject.createImmutable("   "),_displaytitle))));
 BA.debugLineNum = 147;BA.debugLine="ToastMessageShow(\"✅ Added: \" & displayTitle, Fal";
Debug.ShouldStop(262144);
musicactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("✅ Added: "),_displaytitle))),(Object)(musicactivity.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 149;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
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
		Debug.PushSubsStack("formatSongDur (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,184);
if (RapidSub.canDelegate("formatsongdur")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","formatsongdur", _ms);}
RemoteObject _seconds = RemoteObject.createImmutable(0);
RemoteObject _minutes = RemoteObject.createImmutable(0);
Debug.locals.put("ms", _ms);
 BA.debugLineNum = 184;BA.debugLine="Sub formatSongDur(ms As Int) As String";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 185;BA.debugLine="Dim seconds As Int";
Debug.ShouldStop(16777216);
_seconds = RemoteObject.createImmutable(0);Debug.locals.put("seconds", _seconds);
 BA.debugLineNum = 186;BA.debugLine="Dim minutes As Int";
Debug.ShouldStop(33554432);
_minutes = RemoteObject.createImmutable(0);Debug.locals.put("minutes", _minutes);
 BA.debugLineNum = 187;BA.debugLine="seconds = ms / 1000";
Debug.ShouldStop(67108864);
_seconds = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_ms,RemoteObject.createImmutable(1000)}, "/",0, 0));Debug.locals.put("seconds", _seconds);
 BA.debugLineNum = 188;BA.debugLine="minutes = seconds / 60";
Debug.ShouldStop(134217728);
_minutes = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_seconds,RemoteObject.createImmutable(60)}, "/",0, 0));Debug.locals.put("minutes", _minutes);
 BA.debugLineNum = 189;BA.debugLine="seconds = seconds Mod 60";
Debug.ShouldStop(268435456);
_seconds = RemoteObject.solve(new RemoteObject[] {_seconds,RemoteObject.createImmutable(60)}, "%",0, 1);Debug.locals.put("seconds", _seconds);
 BA.debugLineNum = 190;BA.debugLine="Return NumberFormat(minutes, 2, 0) & \":\" & Number";
Debug.ShouldStop(536870912);
if (true) return RemoteObject.concat(musicactivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _minutes)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable(":"),musicactivity.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(BA.numberCast(double.class, _seconds)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 0))));
 BA.debugLineNum = 191;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getdisplaytitle(RemoteObject _rawpath) throws Exception{
try {
		Debug.PushSubsStack("getDisplayTitle (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,76);
if (RapidSub.canDelegate("getdisplaytitle")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","getdisplaytitle", _rawpath);}
RemoteObject _name = RemoteObject.createImmutable("");
Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 76;BA.debugLine="Sub getDisplayTitle(rawPath As String) As String";
Debug.ShouldStop(2048);
 BA.debugLineNum = 77;BA.debugLine="Dim name As String";
Debug.ShouldStop(4096);
_name = RemoteObject.createImmutable("");Debug.locals.put("name", _name);
 BA.debugLineNum = 78;BA.debugLine="name = rawPath.SubString(rawPath.LastIndexOf(\"/\")";
Debug.ShouldStop(8192);
_name = _rawpath.runMethod(true,"substring",(Object)(RemoteObject.solve(new RemoteObject[] {_rawpath.runMethod(true,"lastIndexOf",(Object)(RemoteObject.createImmutable("/"))),RemoteObject.createImmutable(1)}, "+",1, 1)));Debug.locals.put("name", _name);
 BA.debugLineNum = 80;BA.debugLine="If name.EndsWith(\".mp3\") Or name.EndsWith(\".MP3\")";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(".",_name.runMethod(true,"endsWith",(Object)(RemoteObject.createImmutable(".mp3")))) || RemoteObject.solveBoolean(".",_name.runMethod(true,"endsWith",(Object)(RemoteObject.createImmutable(".MP3"))))) { 
 BA.debugLineNum = 81;BA.debugLine="name = name.SubString2(0, name.Length - 4)";
Debug.ShouldStop(65536);
_name = _name.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_name.runMethod(true,"length"),RemoteObject.createImmutable(4)}, "-",1, 1)));Debug.locals.put("name", _name);
 };
 BA.debugLineNum = 84;BA.debugLine="name = name.Replace(\"%20\", \" \")";
Debug.ShouldStop(524288);
_name = _name.runMethod(true,"replace",(Object)(BA.ObjectToString("%20")),(Object)(RemoteObject.createImmutable(" ")));Debug.locals.put("name", _name);
 BA.debugLineNum = 85;BA.debugLine="name = name.Replace(\"%26\", \"&\")";
Debug.ShouldStop(1048576);
_name = _name.runMethod(true,"replace",(Object)(BA.ObjectToString("%26")),(Object)(RemoteObject.createImmutable("&")));Debug.locals.put("name", _name);
 BA.debugLineNum = 86;BA.debugLine="name = name.Replace(\"%27\", \"'\")";
Debug.ShouldStop(2097152);
_name = _name.runMethod(true,"replace",(Object)(BA.ObjectToString("%27")),(Object)(RemoteObject.createImmutable("'")));Debug.locals.put("name", _name);
 BA.debugLineNum = 87;BA.debugLine="name = name.Replace(\"%28\", \"(\")";
Debug.ShouldStop(4194304);
_name = _name.runMethod(true,"replace",(Object)(BA.ObjectToString("%28")),(Object)(RemoteObject.createImmutable("(")));Debug.locals.put("name", _name);
 BA.debugLineNum = 88;BA.debugLine="name = name.Replace(\"%29\", \")\")";
Debug.ShouldStop(8388608);
_name = _name.runMethod(true,"replace",(Object)(BA.ObjectToString("%29")),(Object)(RemoteObject.createImmutable(")")));Debug.locals.put("name", _name);
 BA.debugLineNum = 89;BA.debugLine="name = name.Replace(\"%2C\", \",\")";
Debug.ShouldStop(16777216);
_name = _name.runMethod(true,"replace",(Object)(BA.ObjectToString("%2C")),(Object)(RemoteObject.createImmutable(",")));Debug.locals.put("name", _name);
 BA.debugLineNum = 90;BA.debugLine="Return name";
Debug.ShouldStop(33554432);
if (true) return _name;
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
 //BA.debugLineNum = 13;BA.debugLine="Private SeekBar1 As SeekBar";
musicactivity.mostCurrent._seekbar1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SeekBarWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private songTitle As Label";
musicactivity.mostCurrent._songtitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private pauseBtn As Button";
musicactivity.mostCurrent._pausebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private songRuntime As Label";
musicactivity.mostCurrent._songruntime = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private ListView1 As ListView";
musicactivity.mostCurrent._listview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private btnUpload As Button";
musicactivity.mostCurrent._btnupload = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private Panel1 As Panel";
musicactivity.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _listview1_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("ListView1_ItemClick (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,172);
if (RapidSub.canDelegate("listview1_itemclick")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","listview1_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 172;BA.debugLine="Sub ListView1_ItemClick(Position As Int, Value As";
Debug.ShouldStop(2048);
 BA.debugLineNum = 173;BA.debugLine="CallSub2(musicService, \"setSong\", Position)";
Debug.ShouldStop(4096);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew2",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(BA.ObjectToString("setSong")),(Object)((_position)));
 BA.debugLineNum = 174;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _listview1_itemlongclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("ListView1_ItemLongClick (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,151);
if (RapidSub.canDelegate("listview1_itemlongclick")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","listview1_itemlongclick", _position, _value);}
RemoteObject _rawpath = RemoteObject.createImmutable("");
RemoteObject _displaytitle = RemoteObject.createImmutable("");
RemoteObject _sourcelabel = RemoteObject.createImmutable("");
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 151;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 153;BA.debugLine="Dim rawPath As String";
Debug.ShouldStop(16777216);
_rawpath = RemoteObject.createImmutable("");Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 154;BA.debugLine="rawPath = musicService.musicPlaylist.Get(Position";
Debug.ShouldStop(33554432);
_rawpath = BA.ObjectToString(musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(false,"Get",(Object)(_position)));Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 155;BA.debugLine="Dim displayTitle As String";
Debug.ShouldStop(67108864);
_displaytitle = RemoteObject.createImmutable("");Debug.locals.put("displayTitle", _displaytitle);
 BA.debugLineNum = 156;BA.debugLine="displayTitle = getDisplayTitle(rawPath)";
Debug.ShouldStop(134217728);
_displaytitle = _getdisplaytitle(_rawpath);Debug.locals.put("displayTitle", _displaytitle);
 BA.debugLineNum = 159;BA.debugLine="Dim sourceLabel As String";
Debug.ShouldStop(1073741824);
_sourcelabel = RemoteObject.createImmutable("");Debug.locals.put("sourceLabel", _sourcelabel);
 BA.debugLineNum = 160;BA.debugLine="If rawPath.StartsWith(\"tracks/\") Then";
Debug.ShouldStop(-2147483648);
if (_rawpath.runMethod(true,"startsWith",(Object)(RemoteObject.createImmutable("tracks/"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 161;BA.debugLine="sourceLabel = \"Built-in / Assets\"";
Debug.ShouldStop(1);
_sourcelabel = BA.ObjectToString("Built-in / Assets");Debug.locals.put("sourceLabel", _sourcelabel);
 }else {
 BA.debugLineNum = 163;BA.debugLine="sourceLabel = rawPath";
Debug.ShouldStop(4);
_sourcelabel = _rawpath;Debug.locals.put("sourceLabel", _sourcelabel);
 };
 BA.debugLineNum = 166;BA.debugLine="MsgboxAsync (\"📝 SONG INFO:\" & CRLF & CRLF & _";
Debug.ShouldStop(32);
musicactivity.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("📝 SONG INFO:"),musicactivity.mostCurrent.__c.getField(true,"CRLF"),musicactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Title: "),_displaytitle,musicactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Track #: "),(RemoteObject.solve(new RemoteObject[] {_position,RemoteObject.createImmutable(1)}, "+",1, 1)),musicactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Path: "),_sourcelabel))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Song Details"))),musicactivity.processBA);
 BA.debugLineNum = 170;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loadmusicplayer() throws Exception{
try {
		Debug.PushSubsStack("LoadMusicPlayer (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,27);
if (RapidSub.canDelegate("loadmusicplayer")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","loadmusicplayer");}
int _i = 0;
RemoteObject _rawpath = RemoteObject.createImmutable("");
RemoteObject _displaytitle = RemoteObject.createImmutable("");
 BA.debugLineNum = 27;BA.debugLine="Sub LoadMusicPlayer";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 28;BA.debugLine="Activity.RemoveAllViews";
Debug.ShouldStop(134217728);
musicactivity.mostCurrent._activity.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 30;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(536870912);
switch (BA.switchObjectToInt(musicactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 32;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"musicLayout\")";
Debug.ShouldStop(1);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayout")),musicactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark\")";
Debug.ShouldStop(4);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayoutDark")),musicactivity.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 38;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 39;BA.debugLine="Activity.LoadLayout(\"musicLayout2\")";
Debug.ShouldStop(64);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayout2")),musicactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark2\")";
Debug.ShouldStop(256);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayoutDark2")),musicactivity.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 44;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 45;BA.debugLine="Activity.LoadLayout(\"musicLayout3\")";
Debug.ShouldStop(4096);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayout3")),musicactivity.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 47;BA.debugLine="Activity.LoadLayout(\"musicLayoutDark3\")";
Debug.ShouldStop(16384);
musicactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("musicLayoutDark3")),musicactivity.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 51;BA.debugLine="If musicService.mediaPlayer.IsInitialized = False";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"IsInitialized"),musicactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 52;BA.debugLine="StartService(musicService)";
Debug.ShouldStop(524288);
musicactivity.mostCurrent.__c.runVoidMethod ("StartService",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())));
 };
 BA.debugLineNum = 57;BA.debugLine="For i = 0 To musicService.musicPlaylist.Size - 1";
Debug.ShouldStop(16777216);
{
final int step25 = 1;
final int limit25 = RemoteObject.solve(new RemoteObject[] {musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step25 > 0 && _i <= limit25) || (step25 < 0 && _i >= limit25) ;_i = ((int)(0 + _i + step25))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 58;BA.debugLine="Dim rawPath As String";
Debug.ShouldStop(33554432);
_rawpath = RemoteObject.createImmutable("");Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 59;BA.debugLine="rawPath = musicService.musicPlaylist.Get(i)";
Debug.ShouldStop(67108864);
_rawpath = BA.ObjectToString(musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 60;BA.debugLine="Dim displayTitle As String";
Debug.ShouldStop(134217728);
_displaytitle = RemoteObject.createImmutable("");Debug.locals.put("displayTitle", _displaytitle);
 BA.debugLineNum = 61;BA.debugLine="displayTitle = getDisplayTitle(rawPath)";
Debug.ShouldStop(268435456);
_displaytitle = _getdisplaytitle(_rawpath);Debug.locals.put("displayTitle", _displaytitle);
 BA.debugLineNum = 62;BA.debugLine="ListView1.AddSingleLine((i + 1) & \"   \" & displa";
Debug.ShouldStop(536870912);
musicactivity.mostCurrent._listview1.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(RemoteObject.concat((RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1)),RemoteObject.createImmutable("   "),_displaytitle))));
 BA.debugLineNum = 63;BA.debugLine="If Starter.themeNumber = 2 And Starter.darkMode";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(double.class, 2)) && RemoteObject.solveBoolean("=",musicactivity.mostCurrent._starter._darkmode /*RemoteObject*/ ,musicactivity.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 64;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Co";
Debug.ShouldStop(-2147483648);
musicactivity.mostCurrent._listview1.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",musicactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 66;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor = Co";
Debug.ShouldStop(2);
musicactivity.mostCurrent._listview1.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",musicactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 24)),(Object)(BA.numberCast(int.class, 20)),(Object)(BA.numberCast(int.class, 37))));
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 70;BA.debugLine="uiTimer.Initialize(\"uiTimer\", 500)";
Debug.ShouldStop(32);
musicactivity._uitimer.runVoidMethod ("Initialize",musicactivity.processBA,(Object)(BA.ObjectToString("uiTimer")),(Object)(BA.numberCast(long.class, 500)));
 BA.debugLineNum = 71;BA.debugLine="uiTimer.Enabled = True";
Debug.ShouldStop(64);
musicactivity._uitimer.runMethod(true,"setEnabled",musicactivity.mostCurrent.__c.getField(true,"True"));
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
public static RemoteObject  _nextbtn_click() throws Exception{
try {
		Debug.PushSubsStack("nextBtn_Click (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,220);
if (RapidSub.canDelegate("nextbtn_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","nextbtn_click");}
 BA.debugLineNum = 220;BA.debugLine="Sub nextBtn_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 221;BA.debugLine="CallSub(musicService, \"nextSong\")";
Debug.ShouldStop(268435456);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(RemoteObject.createImmutable("nextSong")));
 BA.debugLineNum = 222;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("pauseBtn_Click (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,228);
if (RapidSub.canDelegate("pausebtn_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","pausebtn_click");}
 BA.debugLineNum = 228;BA.debugLine="Sub pauseBtn_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 229;BA.debugLine="CallSub(musicService, \"pauseToggle\")";
Debug.ShouldStop(16);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(RemoteObject.createImmutable("pauseToggle")));
 BA.debugLineNum = 230;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("prevBtn_Click (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,224);
if (RapidSub.canDelegate("prevbtn_click")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","prevbtn_click");}
 BA.debugLineNum = 224;BA.debugLine="Sub prevBtn_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 225;BA.debugLine="CallSub(musicService, \"prevSong\")";
Debug.ShouldStop(1);
musicactivity.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",musicactivity.processBA,(Object)((musicactivity.mostCurrent._musicservice.getObject())),(Object)(RemoteObject.createImmutable("prevSong")));
 BA.debugLineNum = 226;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
 //BA.debugLineNum = 9;BA.debugLine="Private chooser As ContentChooser";
musicactivity._chooser = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.ContentChooser");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _seekbar1_valuechanged(RemoteObject _value,RemoteObject _userchanged) throws Exception{
try {
		Debug.PushSubsStack("SeekBar1_ValueChanged (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,214);
if (RapidSub.canDelegate("seekbar1_valuechanged")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","seekbar1_valuechanged", _value, _userchanged);}
Debug.locals.put("Value", _value);
Debug.locals.put("UserChanged", _userchanged);
 BA.debugLineNum = 214;BA.debugLine="Sub SeekBar1_ValueChanged(Value As Int, UserChange";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 215;BA.debugLine="If UserChanged Then";
Debug.ShouldStop(4194304);
if (_userchanged.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 216;BA.debugLine="musicService.mediaPlayer.Position = Value";
Debug.ShouldStop(8388608);
musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"setPosition",_value);
 };
 BA.debugLineNum = 218;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("uiTimer_Tick (musicactivity) ","musicactivity",24,musicactivity.mostCurrent.activityBA,musicactivity.mostCurrent,193);
if (RapidSub.canDelegate("uitimer_tick")) { return b4a.example.musicactivity.remoteMe.runUserSub(false, "musicactivity","uitimer_tick");}
RemoteObject _rawpath = RemoteObject.createImmutable("");
RemoteObject _title = RemoteObject.createImmutable("");
 BA.debugLineNum = 193;BA.debugLine="Sub uiTimer_Tick";
Debug.ShouldStop(1);
 BA.debugLineNum = 194;BA.debugLine="If musicService.mediaPlayer.IsInitialized Then";
Debug.ShouldStop(2);
if (musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 195;BA.debugLine="Dim rawPath As String";
Debug.ShouldStop(4);
_rawpath = RemoteObject.createImmutable("");Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 196;BA.debugLine="rawPath = musicService.musicPlaylist.Get(musicSe";
Debug.ShouldStop(8);
_rawpath = BA.ObjectToString(musicactivity.mostCurrent._musicservice._musicplaylist /*RemoteObject*/ .runMethod(false,"Get",(Object)(musicactivity.mostCurrent._musicservice._currentsong /*RemoteObject*/ )));Debug.locals.put("rawPath", _rawpath);
 BA.debugLineNum = 197;BA.debugLine="Dim title As String";
Debug.ShouldStop(16);
_title = RemoteObject.createImmutable("");Debug.locals.put("title", _title);
 BA.debugLineNum = 198;BA.debugLine="title = getDisplayTitle(rawPath)";
Debug.ShouldStop(32);
_title = _getdisplaytitle(_rawpath);Debug.locals.put("title", _title);
 BA.debugLineNum = 200;BA.debugLine="SeekBar1.Max = musicService.mediaPlayer.Duration";
Debug.ShouldStop(128);
musicactivity.mostCurrent._seekbar1.runMethod(true,"setMax",musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getDuration"));
 BA.debugLineNum = 201;BA.debugLine="SeekBar1.Value = musicService.mediaPlayer.Positi";
Debug.ShouldStop(256);
musicactivity.mostCurrent._seekbar1.runMethod(true,"setValue",musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getPosition"));
 BA.debugLineNum = 202;BA.debugLine="songRuntime.Text = formatSongDur(musicService.me";
Debug.ShouldStop(512);
musicactivity.mostCurrent._songruntime.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_formatsongdur(musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getPosition")),RemoteObject.createImmutable(" / "),_formatsongdur(musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"getDuration")))));
 BA.debugLineNum = 203;BA.debugLine="songTitle.Text = title";
Debug.ShouldStop(1024);
musicactivity.mostCurrent._songtitle.runMethod(true,"setText",BA.ObjectToCharSequence(_title));
 BA.debugLineNum = 205;BA.debugLine="If musicService.mediaPlayer.IsPlaying Then";
Debug.ShouldStop(4096);
if (musicactivity.mostCurrent._musicservice._mediaplayer /*RemoteObject*/ .runMethod(true,"IsPlaying").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 206;BA.debugLine="pauseBtn.Text = \"❚❚\"";
Debug.ShouldStop(8192);
musicactivity.mostCurrent._pausebtn.runMethod(true,"setText",BA.ObjectToCharSequence("❚❚"));
 }else {
 BA.debugLineNum = 208;BA.debugLine="pauseBtn.Text = \"▶\"";
Debug.ShouldStop(32768);
musicactivity.mostCurrent._pausebtn.runMethod(true,"setText",BA.ObjectToCharSequence("▶"));
 BA.debugLineNum = 209;BA.debugLine="pauseBtn.TextSize = 24";
Debug.ShouldStop(65536);
musicactivity.mostCurrent._pausebtn.runMethod(true,"setTextSize",BA.numberCast(float.class, 24));
 };
 };
 BA.debugLineNum = 212;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}