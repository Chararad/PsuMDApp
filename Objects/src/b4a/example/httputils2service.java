package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ReceiverHelper;
import anywheresoftware.b4a.debug.*;

public class httputils2service extends android.content.BroadcastReceiver{
		
    static httputils2service mostCurrent;
	public static BA processBA;
    private ReceiverHelper _receiver;
    private static boolean firstTime = true;
    public static Class<?> getObject() {
		return httputils2service.class;
	}
	@Override
	public void onReceive(android.content.Context context, android.content.Intent intent) {
        mostCurrent = this;
       
        if (processBA == null) {
           
		    processBA = new anywheresoftware.b4a.ShellBA(context, null, null, anywheresoftware.b4a.BA.SharedProcessBA.ModuleType.RECEIVER, "b4a.example.httputils2service");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
        }
         _receiver = new ReceiverHelper(this);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.httputils2service", processBA, _receiver, anywheresoftware.b4a.keywords.Common.Density);
		}
        processBA.setActivityPaused(false);
        BA.LogInfo("*** Receiver (httputils2service) Receive " + (firstTime ? "(first time)" : "") + " ***");
        anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
        iw.setObject(intent);
        processBA.raiseEvent(null, "receiver_receive", firstTime, iw);
        firstTime = false;
    }
	

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4h.okhttp.OkHttpClientWrapper _hc = null;
public static anywheresoftware.b4a.objects.collections.Map _taskidtojob = null;
public static String _tempfolder = "";
public static int _taskcounter = 0;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.leaderboard _leaderboard = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.card_module _card_module = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.day_module _day_module = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public static String  _completejob(int _taskid,boolean _success,String _errormessage) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "completejob", false))
	 {return ((String) Debug.delegate(processBA, "completejob", new Object[] {_taskid,_success,_errormessage}));}
b4a.example.httpjob _job = null;
RDebugUtils.currentLine=32112640;
 //BA.debugLineNum = 32112640;BA.debugLine="Sub CompleteJob(TaskId As Int, success As Boolean,";
RDebugUtils.currentLine=32112644;
 //BA.debugLineNum = 32112644;BA.debugLine="Dim job As HttpJob = TaskIdToJob.Get(TaskId)";
_job = (b4a.example.httpjob)(_taskidtojob.Get((Object)(_taskid)));
RDebugUtils.currentLine=32112645;
 //BA.debugLineNum = 32112645;BA.debugLine="If job = Null Then";
if (_job== null) { 
RDebugUtils.currentLine=32112646;
 //BA.debugLineNum = 32112646;BA.debugLine="Log(\"HttpUtils2Service: job completed multiple t";
anywheresoftware.b4a.keywords.Common.LogImpl("432112646","HttpUtils2Service: job completed multiple times - "+BA.NumberToString(_taskid),0);
RDebugUtils.currentLine=32112647;
 //BA.debugLineNum = 32112647;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=32112649;
 //BA.debugLineNum = 32112649;BA.debugLine="TaskIdToJob.Remove(TaskId)";
_taskidtojob.Remove((Object)(_taskid));
RDebugUtils.currentLine=32112650;
 //BA.debugLineNum = 32112650;BA.debugLine="job.success = success";
_job._success /*boolean*/  = _success;
RDebugUtils.currentLine=32112651;
 //BA.debugLineNum = 32112651;BA.debugLine="job.errorMessage = errorMessage";
_job._errormessage /*String*/  = _errormessage;
RDebugUtils.currentLine=32112653;
 //BA.debugLineNum = 32112653;BA.debugLine="job.Complete(TaskId)";
_job._complete /*String*/ (null,_taskid);
RDebugUtils.currentLine=32112657;
 //BA.debugLineNum = 32112657;BA.debugLine="End Sub";
return "";
}
public static String  _hc_responseerror(anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,String _reason,int _statuscode,int _taskid) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "hc_responseerror", false))
	 {return ((String) Debug.delegate(processBA, "hc_responseerror", new Object[] {_response,_reason,_statuscode,_taskid}));}
b4a.example.httpjob _job = null;
RDebugUtils.currentLine=32047104;
 //BA.debugLineNum = 32047104;BA.debugLine="Sub hc_ResponseError (Response As OkHttpResponse,";
RDebugUtils.currentLine=32047106;
 //BA.debugLineNum = 32047106;BA.debugLine="Log($\"ResponseError. Reason: ${Reason}, Response:";
anywheresoftware.b4a.keywords.Common.LogImpl("432047106",("ResponseError. Reason: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_reason))+", Response: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_response.getErrorResponse()))+""),0);
RDebugUtils.currentLine=32047108;
 //BA.debugLineNum = 32047108;BA.debugLine="Response.Release";
_response.Release();
RDebugUtils.currentLine=32047109;
 //BA.debugLineNum = 32047109;BA.debugLine="Dim job As HttpJob = TaskIdToJob.Get(TaskId)";
_job = (b4a.example.httpjob)(_taskidtojob.Get((Object)(_taskid)));
RDebugUtils.currentLine=32047110;
 //BA.debugLineNum = 32047110;BA.debugLine="If job = Null Then";
if (_job== null) { 
RDebugUtils.currentLine=32047111;
 //BA.debugLineNum = 32047111;BA.debugLine="Log(\"HttpUtils2Service (hc_ResponseError): job c";
anywheresoftware.b4a.keywords.Common.LogImpl("432047111","HttpUtils2Service (hc_ResponseError): job completed multiple times - "+BA.NumberToString(_taskid),0);
RDebugUtils.currentLine=32047112;
 //BA.debugLineNum = 32047112;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=32047114;
 //BA.debugLineNum = 32047114;BA.debugLine="job.Response = Response";
_job._response /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse*/  = _response;
RDebugUtils.currentLine=32047115;
 //BA.debugLineNum = 32047115;BA.debugLine="If Response.ErrorResponse <> \"\" Then";
if ((_response.getErrorResponse()).equals("") == false) { 
RDebugUtils.currentLine=32047116;
 //BA.debugLineNum = 32047116;BA.debugLine="CompleteJob(TaskId, False, Response.ErrorRespons";
_completejob(_taskid,anywheresoftware.b4a.keywords.Common.False,_response.getErrorResponse());
 }else {
RDebugUtils.currentLine=32047118;
 //BA.debugLineNum = 32047118;BA.debugLine="CompleteJob(TaskId, False, Reason)";
_completejob(_taskid,anywheresoftware.b4a.keywords.Common.False,_reason);
 };
RDebugUtils.currentLine=32047120;
 //BA.debugLineNum = 32047120;BA.debugLine="End Sub";
return "";
}
public static String  _hc_responsesuccess(anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,int _taskid) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "hc_responsesuccess", false))
	 {return ((String) Debug.delegate(processBA, "hc_responsesuccess", new Object[] {_response,_taskid}));}
b4a.example.httpjob _job = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
RDebugUtils.currentLine=31916032;
 //BA.debugLineNum = 31916032;BA.debugLine="Sub hc_ResponseSuccess (Response As OkHttpResponse";
RDebugUtils.currentLine=31916033;
 //BA.debugLineNum = 31916033;BA.debugLine="Dim job As HttpJob = TaskIdToJob.Get(TaskId)";
_job = (b4a.example.httpjob)(_taskidtojob.Get((Object)(_taskid)));
RDebugUtils.currentLine=31916034;
 //BA.debugLineNum = 31916034;BA.debugLine="If job = Null Then";
if (_job== null) { 
RDebugUtils.currentLine=31916035;
 //BA.debugLineNum = 31916035;BA.debugLine="Log(\"HttpUtils2Service (hc_ResponseSuccess): job";
anywheresoftware.b4a.keywords.Common.LogImpl("431916035","HttpUtils2Service (hc_ResponseSuccess): job completed multiple times - "+BA.NumberToString(_taskid),0);
RDebugUtils.currentLine=31916036;
 //BA.debugLineNum = 31916036;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=31916038;
 //BA.debugLineNum = 31916038;BA.debugLine="job.Response = Response";
_job._response /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse*/  = _response;
RDebugUtils.currentLine=31916039;
 //BA.debugLineNum = 31916039;BA.debugLine="Dim out As OutputStream = File.OpenOutput(TempFol";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(_tempfolder,BA.NumberToString(_taskid),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=31916043;
 //BA.debugLineNum = 31916043;BA.debugLine="Response.GetAsynchronously(\"response\", out , _";
_response.GetAsynchronously(processBA,"response",(java.io.OutputStream)(_out.getObject()),anywheresoftware.b4a.keywords.Common.True,_taskid);
RDebugUtils.currentLine=31916045;
 //BA.debugLineNum = 31916045;BA.debugLine="End Sub";
return "";
}
public static String  _receiver_receive(boolean _firsttime,anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "receiver_receive", false))
	 {return ((String) Debug.delegate(processBA, "receiver_receive", new Object[] {_firsttime,_startingintent}));}
RDebugUtils.currentLine=31653888;
 //BA.debugLineNum = 31653888;BA.debugLine="Private Sub Receiver_Receive (FirstTime As Boolean";
RDebugUtils.currentLine=31653889;
 //BA.debugLineNum = 31653889;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
RDebugUtils.currentLine=31653890;
 //BA.debugLineNum = 31653890;BA.debugLine="Service_Create";
_service_create();
 };
RDebugUtils.currentLine=31653892;
 //BA.debugLineNum = 31653892;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "service_create", false))
	 {return ((String) Debug.delegate(processBA, "service_create", null));}
RDebugUtils.currentLine=31588352;
 //BA.debugLineNum = 31588352;BA.debugLine="Sub Service_Create";
RDebugUtils.currentLine=31588354;
 //BA.debugLineNum = 31588354;BA.debugLine="TempFolder = File.DirInternalCache";
_tempfolder = anywheresoftware.b4a.keywords.Common.File.getDirInternalCache();
RDebugUtils.currentLine=31588355;
 //BA.debugLineNum = 31588355;BA.debugLine="Try";
try {RDebugUtils.currentLine=31588356;
 //BA.debugLineNum = 31588356;BA.debugLine="File.WriteString(TempFolder, \"~test.test\", \"test";
anywheresoftware.b4a.keywords.Common.File.WriteString(_tempfolder,"~test.test","test");
RDebugUtils.currentLine=31588357;
 //BA.debugLineNum = 31588357;BA.debugLine="File.Delete(TempFolder, \"~test.test\")";
anywheresoftware.b4a.keywords.Common.File.Delete(_tempfolder,"~test.test");
 } 
       catch (Exception e6) {
			processBA.setLastException(e6);RDebugUtils.currentLine=31588359;
 //BA.debugLineNum = 31588359;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("431588359",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
RDebugUtils.currentLine=31588360;
 //BA.debugLineNum = 31588360;BA.debugLine="Log(\"Switching to File.DirInternal\")";
anywheresoftware.b4a.keywords.Common.LogImpl("431588360","Switching to File.DirInternal",0);
RDebugUtils.currentLine=31588361;
 //BA.debugLineNum = 31588361;BA.debugLine="TempFolder = File.DirInternal";
_tempfolder = anywheresoftware.b4a.keywords.Common.File.getDirInternal();
 };
RDebugUtils.currentLine=31588366;
 //BA.debugLineNum = 31588366;BA.debugLine="If hc.IsInitialized = False Then";
if (_hc.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=31588371;
 //BA.debugLineNum = 31588371;BA.debugLine="hc.Initialize(\"hc\")";
_hc.Initialize("hc");
 };
RDebugUtils.currentLine=31588379;
 //BA.debugLineNum = 31588379;BA.debugLine="TaskIdToJob.Initialize";
_taskidtojob.Initialize();
RDebugUtils.currentLine=31588381;
 //BA.debugLineNum = 31588381;BA.debugLine="End Sub";
return "";
}
public static String  _response_streamfinish(boolean _success,int _taskid) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "response_streamfinish", false))
	 {return ((String) Debug.delegate(processBA, "response_streamfinish", new Object[] {_success,_taskid}));}
RDebugUtils.currentLine=31981568;
 //BA.debugLineNum = 31981568;BA.debugLine="Private Sub Response_StreamFinish (Success As Bool";
RDebugUtils.currentLine=31981569;
 //BA.debugLineNum = 31981569;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=31981570;
 //BA.debugLineNum = 31981570;BA.debugLine="CompleteJob(TaskId, Success, \"\")";
_completejob(_taskid,_success,"");
 }else {
RDebugUtils.currentLine=31981572;
 //BA.debugLineNum = 31981572;BA.debugLine="CompleteJob(TaskId, Success, LastException.Messa";
_completejob(_taskid,_success,anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage());
 };
RDebugUtils.currentLine=31981574;
 //BA.debugLineNum = 31981574;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "service_destroy", false))
	 {return ((String) Debug.delegate(processBA, "service_destroy", null));}
RDebugUtils.currentLine=31784960;
 //BA.debugLineNum = 31784960;BA.debugLine="Sub Service_Destroy";
RDebugUtils.currentLine=31784962;
 //BA.debugLineNum = 31784962;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "service_start", false))
	 {return ((String) Debug.delegate(processBA, "service_start", new Object[] {_startingintent}));}
RDebugUtils.currentLine=31719424;
 //BA.debugLineNum = 31719424;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
RDebugUtils.currentLine=31719426;
 //BA.debugLineNum = 31719426;BA.debugLine="End Sub";
return "";
}
public static String  _submitjob(b4a.example.httpjob _job) throws Exception{
RDebugUtils.currentModule="httputils2service";
if (Debug.shouldDelegate(processBA, "submitjob", false))
	 {return ((String) Debug.delegate(processBA, "submitjob", new Object[] {_job}));}
int _taskid = 0;
RDebugUtils.currentLine=31850496;
 //BA.debugLineNum = 31850496;BA.debugLine="Public Sub SubmitJob(job As HttpJob)";
RDebugUtils.currentLine=31850497;
 //BA.debugLineNum = 31850497;BA.debugLine="If TaskIdToJob.IsInitialized = False Then Service";
if (_taskidtojob.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_service_create();};
RDebugUtils.currentLine=31850501;
 //BA.debugLineNum = 31850501;BA.debugLine="taskCounter = taskCounter + 1";
_taskcounter = (int) (_taskcounter+1);
RDebugUtils.currentLine=31850502;
 //BA.debugLineNum = 31850502;BA.debugLine="Dim TaskId As Int = taskCounter";
_taskid = _taskcounter;
RDebugUtils.currentLine=31850504;
 //BA.debugLineNum = 31850504;BA.debugLine="TaskIdToJob.Put(TaskId, job)";
_taskidtojob.Put((Object)(_taskid),(Object)(_job));
RDebugUtils.currentLine=31850505;
 //BA.debugLineNum = 31850505;BA.debugLine="If job.Username <> \"\" And job.Password <> \"\" Then";
if ((_job._username /*String*/ ).equals("") == false && (_job._password /*String*/ ).equals("") == false) { 
RDebugUtils.currentLine=31850506;
 //BA.debugLineNum = 31850506;BA.debugLine="hc.ExecuteCredentials(job.GetRequest, TaskId, jo";
_hc.ExecuteCredentials(processBA,_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null),_taskid,_job._username /*String*/ ,_job._password /*String*/ );
 }else {
RDebugUtils.currentLine=31850508;
 //BA.debugLineNum = 31850508;BA.debugLine="hc.Execute(job.GetRequest, TaskId)";
_hc.Execute(processBA,_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null),_taskid);
 };
RDebugUtils.currentLine=31850510;
 //BA.debugLineNum = 31850510;BA.debugLine="End Sub";
return "";
}
}