package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class editnote_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 27;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(67108864);
switch (BA.switchObjectToInt(editnote.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 29;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._starter._darkmode /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"editnoteLayout\")";
Debug.ShouldStop(536870912);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayout")),editnote.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark\")";
Debug.ShouldStop(-2147483648);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayoutDark")),editnote.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 35;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._starter._darkmode /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"editnoteLayout2\")";
Debug.ShouldStop(8);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayout2")),editnote.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark2\")";
Debug.ShouldStop(32);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayoutDark2")),editnote.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 41;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._starter._darkmode /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"editnoteLayout3\")";
Debug.ShouldStop(512);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayout3")),editnote.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"editnoteLayoutDark3\")";
Debug.ShouldStop(2048);
editnote.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("editnoteLayoutDark3")),editnote.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 48;BA.debugLine="cc.Initialize(\"CC\")";
Debug.ShouldStop(32768);
editnote.mostCurrent._cc.runVoidMethod ("Initialize",(Object)(RemoteObject.createImmutable("CC")));
 BA.debugLineNum = 50;BA.debugLine="contentTxt.Background = Null";
Debug.ShouldStop(131072);
editnote.mostCurrent._contenttxt.runMethod(false,"setBackground",(editnote.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 51;BA.debugLine="contentTxt.Gravity = Bit.Or(Gravity.TOP, Gravity.";
Debug.ShouldStop(262144);
editnote.mostCurrent._contenttxt.runMethod(true,"setGravity",editnote.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(editnote.mostCurrent.__c.getField(false,"Gravity").getField(true,"TOP")),(Object)(editnote.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"))));
 BA.debugLineNum = 52;BA.debugLine="If ActiveNote.IsInitialized Then";
Debug.ShouldStop(524288);
if (editnote._activenote.getField(true,"IsInitialized" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 53;BA.debugLine="titleTxt.Text = ActiveNote.Title";
Debug.ShouldStop(1048576);
editnote.mostCurrent._titletxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(editnote._activenote.getField(true,"Title" /*RemoteObject*/ )));
 BA.debugLineNum = 54;BA.debugLine="tagsTxt.Text = ActiveNote.Tags";
Debug.ShouldStop(2097152);
editnote.mostCurrent._tagstxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(editnote._activenote.getField(true,"Tags" /*RemoteObject*/ )));
 BA.debugLineNum = 55;BA.debugLine="contentTxt.Text = ActiveNote.Content";
Debug.ShouldStop(4194304);
editnote.mostCurrent._contenttxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(editnote._activenote.getField(true,"Content" /*RemoteObject*/ )));
 };
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
		Debug.PushSubsStack("Activity_Pause (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,63);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 63;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 64;BA.debugLine="If UserClosed Then ActiveNote.Initialize";
Debug.ShouldStop(-2147483648);
if (_userclosed.<Boolean>get().booleanValue()) { 
editnote._activenote.runVoidMethod ("Initialize");};
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
		Debug.PushSubsStack("Activity_Resume (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,59);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","activity_resume");}
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
public static RemoteObject  _ai_notes_click() throws Exception{
try {
		Debug.PushSubsStack("AI_notes_Click (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,96);
if (RapidSub.canDelegate("ai_notes_click")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","ai_notes_click");}
 BA.debugLineNum = 96;BA.debugLine="Private Sub AI_notes_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 97;BA.debugLine="cc.Show(\"application/pdf\", \"Select PDF\")";
Debug.ShouldStop(1);
editnote.mostCurrent._cc.runVoidMethod ("Show",editnote.processBA,(Object)(BA.ObjectToString("application/pdf")),(Object)(RemoteObject.createImmutable("Select PDF")));
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
public static RemoteObject  _cc_result(RemoteObject _success,RemoteObject _dir,RemoteObject _filename) throws Exception{
try {
		Debug.PushSubsStack("CC_RESULT (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,101);
if (RapidSub.canDelegate("cc_result")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","cc_result", _success, _dir, _filename);}
Debug.locals.put("Success", _success);
Debug.locals.put("dir", _dir);
Debug.locals.put("fileName", _filename);
 BA.debugLineNum = 101;BA.debugLine="Sub CC_RESULT (Success As Boolean, dir As String,";
Debug.ShouldStop(16);
 BA.debugLineNum = 102;BA.debugLine="If Success Then";
Debug.ShouldStop(32);
if (_success.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 103;BA.debugLine="Log(\"Selected: \" & dir & \" / \" & fileName)";
Debug.ShouldStop(64);
editnote.mostCurrent.__c.runVoidMethod ("LogImpl","222085634",RemoteObject.concat(RemoteObject.createImmutable("Selected: "),_dir,RemoteObject.createImmutable(" / "),_filename),0);
 BA.debugLineNum = 104;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
Debug.ShouldStop(128);
editnote.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(_dir),(Object)(_filename),(Object)(editnote.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("temp.pdf")));
 BA.debugLineNum = 105;BA.debugLine="Log(\"PDF saved\")";
Debug.ShouldStop(256);
editnote.mostCurrent.__c.runVoidMethod ("LogImpl","222085636",RemoteObject.createImmutable("PDF saved"),0);
 BA.debugLineNum = 106;BA.debugLine="GenerateNotesFromPDF";
Debug.ShouldStop(512);
_generatenotesfrompdf();
 }else {
 BA.debugLineNum = 108;BA.debugLine="Log(\"User Cancelled\")";
Debug.ShouldStop(2048);
editnote.mostCurrent.__c.runVoidMethod ("LogImpl","222085639",RemoteObject.createImmutable("User Cancelled"),0);
 };
 BA.debugLineNum = 110;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _generatenotesfrompdf() throws Exception{
try {
		Debug.PushSubsStack("GenerateNotesFromPDF (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,112);
if (RapidSub.canDelegate("generatenotesfrompdf")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","generatenotesfrompdf");}
RemoteObject _in = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.InputStreamWrapper");
RemoteObject _bytes = null;
RemoteObject _su = RemoteObject.declareNull("anywheresoftware.b4a.objects.StringUtils");
RemoteObject _base64 = RemoteObject.createImmutable("");
RemoteObject _prompt = RemoteObject.createImmutable("");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _contents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _item = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _filepart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _inline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _textpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _gen = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
RemoteObject _json = RemoteObject.createImmutable("");
RemoteObject _url = RemoteObject.createImmutable("");
RemoteObject _job = RemoteObject.declareNull("b4a.example.httpjob");
 BA.debugLineNum = 112;BA.debugLine="Sub GenerateNotesFromPDF";
Debug.ShouldStop(32768);
 BA.debugLineNum = 115;BA.debugLine="Dim In As InputStream = File.OpenInput(File.DirIn";
Debug.ShouldStop(262144);
_in = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.InputStreamWrapper");
_in = editnote.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenInput",(Object)(editnote.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("temp.pdf")));Debug.locals.put("In", _in);Debug.locals.put("In", _in);
 BA.debugLineNum = 116;BA.debugLine="Dim bytes() As Byte = Bit.InputStreamToBytes(In)";
Debug.ShouldStop(524288);
_bytes = editnote.mostCurrent.__c.getField(false,"Bit").runMethod(false,"InputStreamToBytes",(Object)((_in.getObject())));Debug.locals.put("bytes", _bytes);Debug.locals.put("bytes", _bytes);
 BA.debugLineNum = 117;BA.debugLine="In.Close";
Debug.ShouldStop(1048576);
_in.runVoidMethod ("Close");
 BA.debugLineNum = 119;BA.debugLine="Dim su As StringUtils";
Debug.ShouldStop(4194304);
_su = RemoteObject.createNew ("anywheresoftware.b4a.objects.StringUtils");Debug.locals.put("su", _su);
 BA.debugLineNum = 120;BA.debugLine="Dim base64 As String = su.EncodeBase64(bytes)";
Debug.ShouldStop(8388608);
_base64 = _su.runMethod(true,"EncodeBase64",(Object)(_bytes));Debug.locals.put("base64", _base64);Debug.locals.put("base64", _base64);
 BA.debugLineNum = 123;BA.debugLine="Dim prompt As String = _     \"Convert this PDF in";
Debug.ShouldStop(67108864);
_prompt = RemoteObject.concat(RemoteObject.createImmutable("Convert this PDF into clean study notes."),editnote.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("RULES:"),editnote.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Extract only important points"),editnote.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Use bullet points and headings"),editnote.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Keep it simple and study-ready"),editnote.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Output ONLY plain text notes"));Debug.locals.put("prompt", _prompt);Debug.locals.put("prompt", _prompt);
 BA.debugLineNum = 132;BA.debugLine="Dim root As Map";
Debug.ShouldStop(8);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("root", _root);
 BA.debugLineNum = 133;BA.debugLine="root.Initialize";
Debug.ShouldStop(16);
_root.runVoidMethod ("Initialize");
 BA.debugLineNum = 135;BA.debugLine="Dim contents As List";
Debug.ShouldStop(64);
_contents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("contents", _contents);
 BA.debugLineNum = 136;BA.debugLine="contents.Initialize";
Debug.ShouldStop(128);
_contents.runVoidMethod ("Initialize");
 BA.debugLineNum = 138;BA.debugLine="Dim item As Map";
Debug.ShouldStop(512);
_item = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("item", _item);
 BA.debugLineNum = 139;BA.debugLine="item.Initialize";
Debug.ShouldStop(1024);
_item.runVoidMethod ("Initialize");
 BA.debugLineNum = 141;BA.debugLine="Dim parts As List";
Debug.ShouldStop(4096);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("parts", _parts);
 BA.debugLineNum = 142;BA.debugLine="parts.Initialize";
Debug.ShouldStop(8192);
_parts.runVoidMethod ("Initialize");
 BA.debugLineNum = 145;BA.debugLine="Dim filePart As Map";
Debug.ShouldStop(65536);
_filepart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("filePart", _filepart);
 BA.debugLineNum = 146;BA.debugLine="filePart.Initialize";
Debug.ShouldStop(131072);
_filepart.runVoidMethod ("Initialize");
 BA.debugLineNum = 148;BA.debugLine="Dim inline As Map";
Debug.ShouldStop(524288);
_inline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("inline", _inline);
 BA.debugLineNum = 149;BA.debugLine="inline.Initialize";
Debug.ShouldStop(1048576);
_inline.runVoidMethod ("Initialize");
 BA.debugLineNum = 150;BA.debugLine="inline.Put(\"mime_type\", \"application/pdf\")";
Debug.ShouldStop(2097152);
_inline.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mime_type"))),(Object)((RemoteObject.createImmutable("application/pdf"))));
 BA.debugLineNum = 151;BA.debugLine="inline.Put(\"data\", base64)";
Debug.ShouldStop(4194304);
_inline.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("data"))),(Object)((_base64)));
 BA.debugLineNum = 153;BA.debugLine="filePart.Put(\"inline_data\", inline)";
Debug.ShouldStop(16777216);
_filepart.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("inline_data"))),(Object)((_inline.getObject())));
 BA.debugLineNum = 154;BA.debugLine="parts.Add(filePart)";
Debug.ShouldStop(33554432);
_parts.runVoidMethod ("Add",(Object)((_filepart.getObject())));
 BA.debugLineNum = 157;BA.debugLine="Dim textPart As Map";
Debug.ShouldStop(268435456);
_textpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("textPart", _textpart);
 BA.debugLineNum = 158;BA.debugLine="textPart.Initialize";
Debug.ShouldStop(536870912);
_textpart.runVoidMethod ("Initialize");
 BA.debugLineNum = 159;BA.debugLine="textPart.Put(\"text\", prompt)";
Debug.ShouldStop(1073741824);
_textpart.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("text"))),(Object)((_prompt)));
 BA.debugLineNum = 160;BA.debugLine="parts.Add(textPart)";
Debug.ShouldStop(-2147483648);
_parts.runVoidMethod ("Add",(Object)((_textpart.getObject())));
 BA.debugLineNum = 162;BA.debugLine="item.Put(\"parts\", parts)";
Debug.ShouldStop(2);
_item.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("parts"))),(Object)((_parts.getObject())));
 BA.debugLineNum = 163;BA.debugLine="contents.Add(item)";
Debug.ShouldStop(4);
_contents.runVoidMethod ("Add",(Object)((_item.getObject())));
 BA.debugLineNum = 165;BA.debugLine="root.Put(\"contents\", contents)";
Debug.ShouldStop(16);
_root.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("contents"))),(Object)((_contents.getObject())));
 BA.debugLineNum = 167;BA.debugLine="Dim gen As JSONGenerator";
Debug.ShouldStop(64);
_gen = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("gen", _gen);
 BA.debugLineNum = 168;BA.debugLine="gen.Initialize(root)";
Debug.ShouldStop(128);
_gen.runVoidMethod ("Initialize",(Object)(_root));
 BA.debugLineNum = 170;BA.debugLine="Dim json As String = gen.ToString";
Debug.ShouldStop(512);
_json = _gen.runMethod(true,"ToString");Debug.locals.put("json", _json);Debug.locals.put("json", _json);
 BA.debugLineNum = 172;BA.debugLine="Dim url As String = \"https://generativelanguage.g";
Debug.ShouldStop(2048);
_url = RemoteObject.concat(RemoteObject.createImmutable("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="),editnote.mostCurrent._myapikey);Debug.locals.put("url", _url);Debug.locals.put("url", _url);
 BA.debugLineNum = 174;BA.debugLine="Dim job As HttpJob";
Debug.ShouldStop(8192);
_job = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("job", _job);
 BA.debugLineNum = 175;BA.debugLine="job.Initialize(\"GeminiNotes\", Me)";
Debug.ShouldStop(16384);
_job.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,editnote.processBA,(Object)(BA.ObjectToString("GeminiNotes")),(Object)(editnote.getObject()));
 BA.debugLineNum = 177;BA.debugLine="job.PostString(url, json)";
Debug.ShouldStop(65536);
_job.runClassMethod (b4a.example.httpjob.class, "_poststring" /*RemoteObject*/ ,(Object)(_url),(Object)(_json));
 BA.debugLineNum = 178;BA.debugLine="job.GetRequest.SetContentType(\"application/json\")";
Debug.ShouldStop(131072);
_job.runClassMethod (b4a.example.httpjob.class, "_getrequest" /*RemoteObject*/ ).runVoidMethod ("SetContentType",(Object)(RemoteObject.createImmutable("application/json")));
 BA.debugLineNum = 180;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
 //BA.debugLineNum = 16;BA.debugLine="Private contentTxt As EditText";
editnote.mostCurrent._contenttxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private saveBtn As Button";
editnote.mostCurrent._savebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private tagsTxt As EditText";
editnote.mostCurrent._tagstxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private titleTxt As EditText";
editnote.mostCurrent._titletxt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Dim api1 As String = \"AIzaSyAGccTYG-Mscl_16Z72t\"";
editnote.mostCurrent._api1 = BA.ObjectToString("AIzaSyAGccTYG-Mscl_16Z72t");
 //BA.debugLineNum = 21;BA.debugLine="Dim api2 As String = \"-GIN9ITMdrDGhQ\"";
editnote.mostCurrent._api2 = BA.ObjectToString("-GIN9ITMdrDGhQ");
 //BA.debugLineNum = 22;BA.debugLine="Dim MyAPIKey As String = api1&api2";
editnote.mostCurrent._myapikey = RemoteObject.concat(editnote.mostCurrent._api1,editnote.mostCurrent._api2);
 //BA.debugLineNum = 23;BA.debugLine="Dim cc As ContentChooser";
editnote.mostCurrent._cc = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.ContentChooser");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _jobdone(RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,181);
if (RapidSub.canDelegate("jobdone")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","jobdone", _job);}
RemoteObject _response = RemoteObject.createImmutable("");
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _candidates = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _candidate = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _content = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _firstpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _notes = RemoteObject.createImmutable("");
Debug.locals.put("job", _job);
 BA.debugLineNum = 181;BA.debugLine="Sub JobDone (job As HttpJob)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 182;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(2097152);
editnote.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 183;BA.debugLine="If job.Success Then";
Debug.ShouldStop(4194304);
if (_job.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 185;BA.debugLine="If job.JobName = \"GeminiNotes\" Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",_job.getField(true,"_jobname" /*RemoteObject*/ ),BA.ObjectToString("GeminiNotes"))) { 
 BA.debugLineNum = 187;BA.debugLine="Dim response As String = job.GetString";
Debug.ShouldStop(67108864);
_response = _job.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ );Debug.locals.put("response", _response);Debug.locals.put("response", _response);
 BA.debugLineNum = 189;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(268435456);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 190;BA.debugLine="jp.Initialize(response)";
Debug.ShouldStop(536870912);
_jp.runVoidMethod ("Initialize",(Object)(_response));
 BA.debugLineNum = 192;BA.debugLine="Dim root As Map = jp.NextObject";
Debug.ShouldStop(-2147483648);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _jp.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 193;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
Debug.ShouldStop(1);
_candidates = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_candidates = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("candidates")))));Debug.locals.put("candidates", _candidates);Debug.locals.put("candidates", _candidates);
 BA.debugLineNum = 194;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
Debug.ShouldStop(2);
_candidate = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_candidate = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidates.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("candidate", _candidate);Debug.locals.put("candidate", _candidate);
 BA.debugLineNum = 195;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
Debug.ShouldStop(4);
_content = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_content = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidate.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("content")))));Debug.locals.put("content", _content);Debug.locals.put("content", _content);
 BA.debugLineNum = 196;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
Debug.ShouldStop(8);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_parts = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _content.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("parts")))));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 197;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
Debug.ShouldStop(16);
_firstpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_firstpart = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _parts.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("firstPart", _firstpart);Debug.locals.put("firstPart", _firstpart);
 BA.debugLineNum = 199;BA.debugLine="Dim notes As String = firstPart.Get(\"text\")";
Debug.ShouldStop(64);
_notes = BA.ObjectToString(_firstpart.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("text")))));Debug.locals.put("notes", _notes);Debug.locals.put("notes", _notes);
 BA.debugLineNum = 201;BA.debugLine="Log(\"NOTES OUTPUT:\")";
Debug.ShouldStop(256);
editnote.mostCurrent.__c.runVoidMethod ("LogImpl","222216724",RemoteObject.createImmutable("NOTES OUTPUT:"),0);
 BA.debugLineNum = 202;BA.debugLine="Log(notes)";
Debug.ShouldStop(512);
editnote.mostCurrent.__c.runVoidMethod ("LogImpl","222216725",_notes,0);
 BA.debugLineNum = 205;BA.debugLine="If contentTxt.Text.Trim = \"\" Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._contenttxt.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 206;BA.debugLine="contentTxt.Text = notes";
Debug.ShouldStop(8192);
editnote.mostCurrent._contenttxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_notes));
 }else {
 BA.debugLineNum = 208;BA.debugLine="contentTxt.Text = contentTxt.Text & CRLF & CRL";
Debug.ShouldStop(32768);
editnote.mostCurrent._contenttxt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(editnote.mostCurrent._contenttxt.runMethod(true,"getText"),editnote.mostCurrent.__c.getField(true,"CRLF"),editnote.mostCurrent.__c.getField(true,"CRLF"),_notes)));
 };
 BA.debugLineNum = 212;BA.debugLine="File.WriteString(File.DirInternal, \"notes.txt\",";
Debug.ShouldStop(524288);
editnote.mostCurrent.__c.getField(false,"File").runVoidMethod ("WriteString",(Object)(editnote.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("notes.txt")),(Object)(editnote.mostCurrent._contenttxt.runMethod(true,"getText")));
 };
 }else {
 BA.debugLineNum = 217;BA.debugLine="Log(\"ERROR: \" & job.ErrorMessage)";
Debug.ShouldStop(16777216);
editnote.mostCurrent.__c.runVoidMethod ("LogImpl","222216740",RemoteObject.concat(RemoteObject.createImmutable("ERROR: "),_job.getField(true,"_errormessage" /*RemoteObject*/ )),0);
 };
 BA.debugLineNum = 220;BA.debugLine="job.Release";
Debug.ShouldStop(134217728);
_job.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
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
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public ActiveNote As MyNote";
editnote._activenote = RemoteObject.createNew ("b4a.example.main._mynote");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _savebtn_click() throws Exception{
try {
		Debug.PushSubsStack("saveBtn_Click (editnote) ","editnote",22,editnote.mostCurrent.activityBA,editnote.mostCurrent,67);
if (RapidSub.canDelegate("savebtn_click")) { return b4a.example.editnote.remoteMe.runUserSub(false, "editnote","savebtn_click");}
RemoteObject _n = RemoteObject.declareNull("b4a.example.main._mynote");
 BA.debugLineNum = 67;BA.debugLine="Sub saveBtn_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 68;BA.debugLine="If titleTxt.Text.Trim = \"\" Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",editnote.mostCurrent._titletxt.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 69;BA.debugLine="MsgboxAsync(\"Please add a title\", \"\")";
Debug.ShouldStop(16);
editnote.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please add a title")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),editnote.processBA);
 BA.debugLineNum = 70;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 73;BA.debugLine="Dim n As MyNote";
Debug.ShouldStop(256);
_n = RemoteObject.createNew ("b4a.example.main._mynote");Debug.locals.put("n", _n);
 BA.debugLineNum = 74;BA.debugLine="n.Initialize";
Debug.ShouldStop(512);
_n.runVoidMethod ("Initialize");
 BA.debugLineNum = 76;BA.debugLine="If ActiveNote.IsInitialized And ActiveNote.noteID";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean(".",editnote._activenote.getField(true,"IsInitialized" /*RemoteObject*/ )) && RemoteObject.solveBoolean("!",editnote._activenote.getField(true,"noteID" /*RemoteObject*/ ),BA.numberCast(long.class, 0))) { 
 BA.debugLineNum = 77;BA.debugLine="n.noteID = ActiveNote.noteID";
Debug.ShouldStop(4096);
_n.setField ("noteID" /*RemoteObject*/ ,editnote._activenote.getField(true,"noteID" /*RemoteObject*/ ));
 }else {
 BA.debugLineNum = 79;BA.debugLine="n.noteID = (DateTime.Now * 1000) + Rnd(1, 1000)";
Debug.ShouldStop(16384);
_n.setField ("noteID" /*RemoteObject*/ ,RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {editnote.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"),RemoteObject.createImmutable(1000)}, "*",0, 2)),editnote.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 1000)))}, "+",1, 2));
 };
 BA.debugLineNum = 83;BA.debugLine="n.Title = titleTxt.Text";
Debug.ShouldStop(262144);
_n.setField ("Title" /*RemoteObject*/ ,editnote.mostCurrent._titletxt.runMethod(true,"getText"));
 BA.debugLineNum = 84;BA.debugLine="n.Tags = tagsTxt.Text";
Debug.ShouldStop(524288);
_n.setField ("Tags" /*RemoteObject*/ ,editnote.mostCurrent._tagstxt.runMethod(true,"getText"));
 BA.debugLineNum = 85;BA.debugLine="n.Content = contentTxt.Text";
Debug.ShouldStop(1048576);
_n.setField ("Content" /*RemoteObject*/ ,editnote.mostCurrent._contenttxt.runMethod(true,"getText"));
 BA.debugLineNum = 86;BA.debugLine="n.DateAdded = DateTime.Now";
Debug.ShouldStop(2097152);
_n.setField ("DateAdded" /*RemoteObject*/ ,editnote.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"));
 BA.debugLineNum = 88;BA.debugLine="MainActivity.kvs.Put(\"N_\" & n.noteID, n)";
Debug.ShouldStop(8388608);
editnote.mostCurrent._mainactivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(RemoteObject.createImmutable("N_"),_n.getField(true,"noteID" /*RemoteObject*/ ))),(Object)((_n)));
 BA.debugLineNum = 90;BA.debugLine="ToastMessageShow(\"Note Saved\", False)";
Debug.ShouldStop(33554432);
editnote.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Note Saved")),(Object)(editnote.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 91;BA.debugLine="Activity.Finish";
Debug.ShouldStop(67108864);
editnote.mostCurrent._activity.runVoidMethod ("Finish");
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
}