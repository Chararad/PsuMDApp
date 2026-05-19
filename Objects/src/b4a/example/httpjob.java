package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class httpjob extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.httpjob");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.httpjob.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public static class _multipartfiledata{
public boolean IsInitialized;
public String Dir;
public String FileName;
public String KeyName;
public String ContentType;
public void Initialize() {
IsInitialized = true;
Dir = "";
FileName = "";
KeyName = "";
ContentType = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public anywheresoftware.b4a.keywords.Common __c = null;
public String _jobname = "";
public boolean _success = false;
public String _username = "";
public String _password = "";
public String _errormessage = "";
public Object _target = null;
public String _taskid = "";
public anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest _req = null;
public anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response = null;
public Object _tag = null;
public String _invalidurl = "";
public String _defaultscheme = "";
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
public b4a.example.httputils2service _httputils2service = null;
public String  _initialize(b4a.example.httpjob __ref,anywheresoftware.b4a.BA _ba,String _name,Object _targetmodule) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_name,_targetmodule}));}
RDebugUtils.currentLine=32243712;
 //BA.debugLineNum = 32243712;BA.debugLine="Public Sub Initialize (Name As String, TargetModul";
RDebugUtils.currentLine=32243713;
 //BA.debugLineNum = 32243713;BA.debugLine="JobName = Name";
__ref._jobname /*String*/  = _name;
RDebugUtils.currentLine=32243714;
 //BA.debugLineNum = 32243714;BA.debugLine="target = TargetModule";
__ref._target /*Object*/  = _targetmodule;
RDebugUtils.currentLine=32243722;
 //BA.debugLineNum = 32243722;BA.debugLine="End Sub";
return "";
}
public String  _poststring(b4a.example.httpjob __ref,String _link,String _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "poststring", true))
	 {return ((String) Debug.delegate(ba, "poststring", new Object[] {_link,_text}));}
RDebugUtils.currentLine=32374784;
 //BA.debugLineNum = 32374784;BA.debugLine="Public Sub PostString(Link As String, Text As Stri";
RDebugUtils.currentLine=32374785;
 //BA.debugLineNum = 32374785;BA.debugLine="PostBytes(Link, Text.GetBytes(\"UTF8\"))";
__ref._postbytes /*String*/ (null,_link,_text.getBytes("UTF8"));
RDebugUtils.currentLine=32374786;
 //BA.debugLineNum = 32374786;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest  _getrequest(b4a.example.httpjob __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getrequest", true))
	 {return ((anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest) Debug.delegate(ba, "getrequest", null));}
RDebugUtils.currentLine=33554432;
 //BA.debugLineNum = 33554432;BA.debugLine="Public Sub GetRequest As OkHttpRequest";
RDebugUtils.currentLine=33554433;
 //BA.debugLineNum = 33554433;BA.debugLine="Return req";
if (true) return __ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ;
RDebugUtils.currentLine=33554434;
 //BA.debugLineNum = 33554434;BA.debugLine="End Sub";
return null;
}
public String  _getstring(b4a.example.httpjob __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getstring", true))
	 {return ((String) Debug.delegate(ba, "getstring", null));}
RDebugUtils.currentLine=33423360;
 //BA.debugLineNum = 33423360;BA.debugLine="Public Sub GetString As String";
RDebugUtils.currentLine=33423361;
 //BA.debugLineNum = 33423361;BA.debugLine="Return GetString2(\"UTF8\")";
if (true) return __ref._getstring2 /*String*/ (null,"UTF8");
RDebugUtils.currentLine=33423362;
 //BA.debugLineNum = 33423362;BA.debugLine="End Sub";
return "";
}
public String  _release(b4a.example.httpjob __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "release", true))
	 {return ((String) Debug.delegate(ba, "release", null));}
RDebugUtils.currentLine=33357824;
 //BA.debugLineNum = 33357824;BA.debugLine="Public Sub Release";
RDebugUtils.currentLine=33357826;
 //BA.debugLineNum = 33357826;BA.debugLine="File.Delete(HttpUtils2Service.TempFolder, taskId)";
__c.File.Delete(_httputils2service._tempfolder /*String*/ ,__ref._taskid /*String*/ );
RDebugUtils.currentLine=33357828;
 //BA.debugLineNum = 33357828;BA.debugLine="End Sub";
return "";
}
public String  _complete(b4a.example.httpjob __ref,int _id) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "complete", true))
	 {return ((String) Debug.delegate(ba, "complete", new Object[] {_id}));}
RDebugUtils.currentLine=33619968;
 //BA.debugLineNum = 33619968;BA.debugLine="Public Sub Complete (id As Int)";
RDebugUtils.currentLine=33619969;
 //BA.debugLineNum = 33619969;BA.debugLine="taskId = id";
__ref._taskid /*String*/  = BA.NumberToString(_id);
RDebugUtils.currentLine=33619970;
 //BA.debugLineNum = 33619970;BA.debugLine="CallSubDelayed2(target, \"JobDone\", Me)";
__c.CallSubDelayed2(ba,__ref._target /*Object*/ ,"JobDone",this);
RDebugUtils.currentLine=33619971;
 //BA.debugLineNum = 33619971;BA.debugLine="End Sub";
return "";
}
public String  _addscheme(b4a.example.httpjob __ref,String _link) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "addscheme", true))
	 {return ((String) Debug.delegate(ba, "addscheme", new Object[] {_link}));}
RDebugUtils.currentLine=32309248;
 //BA.debugLineNum = 32309248;BA.debugLine="Private Sub AddScheme (Link As String) As String";
RDebugUtils.currentLine=32309249;
 //BA.debugLineNum = 32309249;BA.debugLine="If DefaultScheme = \"\" Or Link.Contains(\":\") Then";
if ((__ref._defaultscheme /*String*/ ).equals("") || _link.contains(":")) { 
if (true) return _link;};
RDebugUtils.currentLine=32309250;
 //BA.debugLineNum = 32309250;BA.debugLine="Return DefaultScheme & \"://\" & Link";
if (true) return __ref._defaultscheme /*String*/ +"://"+_link;
RDebugUtils.currentLine=32309251;
 //BA.debugLineNum = 32309251;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.httpjob __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
RDebugUtils.currentLine=32178176;
 //BA.debugLineNum = 32178176;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=32178177;
 //BA.debugLineNum = 32178177;BA.debugLine="Public JobName As String";
_jobname = "";
RDebugUtils.currentLine=32178178;
 //BA.debugLineNum = 32178178;BA.debugLine="Public Success As Boolean";
_success = false;
RDebugUtils.currentLine=32178179;
 //BA.debugLineNum = 32178179;BA.debugLine="Public Username, Password As String";
_username = "";
_password = "";
RDebugUtils.currentLine=32178180;
 //BA.debugLineNum = 32178180;BA.debugLine="Public ErrorMessage As String";
_errormessage = "";
RDebugUtils.currentLine=32178181;
 //BA.debugLineNum = 32178181;BA.debugLine="Private target As Object";
_target = new Object();
RDebugUtils.currentLine=32178187;
 //BA.debugLineNum = 32178187;BA.debugLine="Private taskId As String";
_taskid = "";
RDebugUtils.currentLine=32178189;
 //BA.debugLineNum = 32178189;BA.debugLine="Private req As OkHttpRequest";
_req = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest();
RDebugUtils.currentLine=32178190;
 //BA.debugLineNum = 32178190;BA.debugLine="Public Response As OkHttpResponse";
_response = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse();
RDebugUtils.currentLine=32178199;
 //BA.debugLineNum = 32178199;BA.debugLine="Public Tag As Object";
_tag = new Object();
RDebugUtils.currentLine=32178200;
 //BA.debugLineNum = 32178200;BA.debugLine="Type MultipartFileData (Dir As String, FileName A";
;
RDebugUtils.currentLine=32178204;
 //BA.debugLineNum = 32178204;BA.debugLine="Private Const InvalidURL As String = \"https://inv";
_invalidurl = "https://invalid-url/";
RDebugUtils.currentLine=32178205;
 //BA.debugLineNum = 32178205;BA.debugLine="Public DefaultScheme As String = \"https\"";
_defaultscheme = "https";
RDebugUtils.currentLine=32178206;
 //BA.debugLineNum = 32178206;BA.debugLine="End Sub";
return "";
}
public String  _delete(b4a.example.httpjob __ref,String _link) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "delete", true))
	 {return ((String) Debug.delegate(ba, "delete", new Object[] {_link}));}
RDebugUtils.currentLine=33226752;
 //BA.debugLineNum = 33226752;BA.debugLine="Public Sub Delete(Link As String)";
RDebugUtils.currentLine=33226753;
 //BA.debugLineNum = 33226753;BA.debugLine="Try";
try {RDebugUtils.currentLine=33226754;
 //BA.debugLineNum = 33226754;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=33226755;
 //BA.debugLineNum = 33226755;BA.debugLine="req.InitializeDelete(Link)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeDelete(_link);
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=33226757;
 //BA.debugLineNum = 33226757;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("933226757",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=33226758;
 //BA.debugLineNum = 33226758;BA.debugLine="req.InitializeDelete(InvalidURL)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeDelete(__ref._invalidurl /*String*/ );
 };
RDebugUtils.currentLine=33226760;
 //BA.debugLineNum = 33226760;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=33226761;
 //BA.debugLineNum = 33226761;BA.debugLine="End Sub";
return "";
}
public String  _delete2(b4a.example.httpjob __ref,String _link,String[] _parameters) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "delete2", true))
	 {return ((String) Debug.delegate(ba, "delete2", new Object[] {_link,_parameters}));}
RDebugUtils.currentLine=33292288;
 //BA.debugLineNum = 33292288;BA.debugLine="Public Sub Delete2(Link As String, Parameters() As";
RDebugUtils.currentLine=33292289;
 //BA.debugLineNum = 33292289;BA.debugLine="Try";
try {RDebugUtils.currentLine=33292290;
 //BA.debugLineNum = 33292290;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=33292291;
 //BA.debugLineNum = 33292291;BA.debugLine="req.InitializeDelete(escapeLink(Link, Parameters";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeDelete(__ref._escapelink /*String*/ (null,_link,_parameters));
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=33292293;
 //BA.debugLineNum = 33292293;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("933292293",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=33292294;
 //BA.debugLineNum = 33292294;BA.debugLine="req.InitializeDelete(escapeLink(InvalidURL, Para";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeDelete(__ref._escapelink /*String*/ (null,__ref._invalidurl /*String*/ ,_parameters));
 };
RDebugUtils.currentLine=33292296;
 //BA.debugLineNum = 33292296;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=33292297;
 //BA.debugLineNum = 33292297;BA.debugLine="End Sub";
return "";
}
public String  _escapelink(b4a.example.httpjob __ref,String _link,String[] _parameters) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "escapelink", true))
	 {return ((String) Debug.delegate(ba, "escapelink", new Object[] {_link,_parameters}));}
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
RDebugUtils.currentLine=33161216;
 //BA.debugLineNum = 33161216;BA.debugLine="Private Sub escapeLink(Link As String, Parameters(";
RDebugUtils.currentLine=33161217;
 //BA.debugLineNum = 33161217;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
RDebugUtils.currentLine=33161218;
 //BA.debugLineNum = 33161218;BA.debugLine="sb.Initialize";
_sb.Initialize();
RDebugUtils.currentLine=33161219;
 //BA.debugLineNum = 33161219;BA.debugLine="sb.Append(Link)";
_sb.Append(_link);
RDebugUtils.currentLine=33161220;
 //BA.debugLineNum = 33161220;BA.debugLine="If Parameters.Length > 0 Then sb.Append(\"?\")";
if (_parameters.length>0) { 
_sb.Append("?");};
RDebugUtils.currentLine=33161221;
 //BA.debugLineNum = 33161221;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
RDebugUtils.currentLine=33161222;
 //BA.debugLineNum = 33161222;BA.debugLine="For i = 0 To Parameters.Length - 1 Step 2";
{
final int step6 = 2;
final int limit6 = (int) (_parameters.length-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
RDebugUtils.currentLine=33161223;
 //BA.debugLineNum = 33161223;BA.debugLine="If i > 0 Then sb.Append(\"&\")";
if (_i>0) { 
_sb.Append("&");};
RDebugUtils.currentLine=33161224;
 //BA.debugLineNum = 33161224;BA.debugLine="sb.Append(su.EncodeUrl(Parameters(i), \"UTF8\")).A";
_sb.Append(_su.EncodeUrl(_parameters[_i],"UTF8")).Append("=");
RDebugUtils.currentLine=33161225;
 //BA.debugLineNum = 33161225;BA.debugLine="sb.Append(su.EncodeUrl(Parameters(i + 1), \"UTF8\"";
_sb.Append(_su.EncodeUrl(_parameters[(int) (_i+1)],"UTF8"));
 }
};
RDebugUtils.currentLine=33161227;
 //BA.debugLineNum = 33161227;BA.debugLine="Return sb.ToString";
if (true) return _sb.ToString();
RDebugUtils.currentLine=33161228;
 //BA.debugLineNum = 33161228;BA.debugLine="End Sub";
return "";
}
public String  _download(b4a.example.httpjob __ref,String _link) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "download", true))
	 {return ((String) Debug.delegate(ba, "download", new Object[] {_link}));}
RDebugUtils.currentLine=33030144;
 //BA.debugLineNum = 33030144;BA.debugLine="Public Sub Download(Link As String)";
RDebugUtils.currentLine=33030145;
 //BA.debugLineNum = 33030145;BA.debugLine="Try";
try {RDebugUtils.currentLine=33030146;
 //BA.debugLineNum = 33030146;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=33030147;
 //BA.debugLineNum = 33030147;BA.debugLine="req.InitializeGet(Link)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeGet(_link);
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=33030149;
 //BA.debugLineNum = 33030149;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("933030149",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=33030150;
 //BA.debugLineNum = 33030150;BA.debugLine="req.InitializeGet(InvalidURL)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeGet(__ref._invalidurl /*String*/ );
 };
RDebugUtils.currentLine=33030152;
 //BA.debugLineNum = 33030152;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=33030153;
 //BA.debugLineNum = 33030153;BA.debugLine="End Sub";
return "";
}
public String  _download2(b4a.example.httpjob __ref,String _link,String[] _parameters) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "download2", true))
	 {return ((String) Debug.delegate(ba, "download2", new Object[] {_link,_parameters}));}
RDebugUtils.currentLine=33095680;
 //BA.debugLineNum = 33095680;BA.debugLine="Public Sub Download2(Link As String, Parameters()";
RDebugUtils.currentLine=33095681;
 //BA.debugLineNum = 33095681;BA.debugLine="Try";
try {RDebugUtils.currentLine=33095682;
 //BA.debugLineNum = 33095682;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=33095683;
 //BA.debugLineNum = 33095683;BA.debugLine="req.InitializeGet(escapeLink(Link, Parameters))";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeGet(__ref._escapelink /*String*/ (null,_link,_parameters));
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=33095685;
 //BA.debugLineNum = 33095685;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("933095685",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=33095686;
 //BA.debugLineNum = 33095686;BA.debugLine="req.InitializeGet(escapeLink(InvalidURL, Paramet";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeGet(__ref._escapelink /*String*/ (null,__ref._invalidurl /*String*/ ,_parameters));
 };
RDebugUtils.currentLine=33095688;
 //BA.debugLineNum = 33095688;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=33095689;
 //BA.debugLineNum = 33095689;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getbitmap(b4a.example.httpjob __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getbitmap", true))
	 {return ((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) Debug.delegate(ba, "getbitmap", null));}
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
RDebugUtils.currentLine=33685504;
 //BA.debugLineNum = 33685504;BA.debugLine="Public Sub GetBitmap As Bitmap";
RDebugUtils.currentLine=33685505;
 //BA.debugLineNum = 33685505;BA.debugLine="Dim b As Bitmap";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
RDebugUtils.currentLine=33685506;
 //BA.debugLineNum = 33685506;BA.debugLine="b = LoadBitmap(HttpUtils2Service.TempFolder, task";
_b = __c.LoadBitmap(_httputils2service._tempfolder /*String*/ ,__ref._taskid /*String*/ );
RDebugUtils.currentLine=33685507;
 //BA.debugLineNum = 33685507;BA.debugLine="Return b";
if (true) return _b;
RDebugUtils.currentLine=33685508;
 //BA.debugLineNum = 33685508;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getbitmapresize(b4a.example.httpjob __ref,int _width,int _height,boolean _keepaspectratio) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getbitmapresize", true))
	 {return ((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) Debug.delegate(ba, "getbitmapresize", new Object[] {_width,_height,_keepaspectratio}));}
RDebugUtils.currentLine=33816576;
 //BA.debugLineNum = 33816576;BA.debugLine="Public Sub GetBitmapResize(Width As Int, Height As";
RDebugUtils.currentLine=33816577;
 //BA.debugLineNum = 33816577;BA.debugLine="Return LoadBitmapResize(HttpUtils2Service.TempFol";
if (true) return __c.LoadBitmapResize(_httputils2service._tempfolder /*String*/ ,__ref._taskid /*String*/ ,_width,_height,_keepaspectratio);
RDebugUtils.currentLine=33816578;
 //BA.debugLineNum = 33816578;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getbitmapsample(b4a.example.httpjob __ref,int _width,int _height) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getbitmapsample", true))
	 {return ((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) Debug.delegate(ba, "getbitmapsample", new Object[] {_width,_height}));}
RDebugUtils.currentLine=33751040;
 //BA.debugLineNum = 33751040;BA.debugLine="Public Sub GetBitmapSample(Width As Int, Height As";
RDebugUtils.currentLine=33751041;
 //BA.debugLineNum = 33751041;BA.debugLine="Return LoadBitmapSample(HttpUtils2Service.TempFol";
if (true) return __c.LoadBitmapSample(_httputils2service._tempfolder /*String*/ ,__ref._taskid /*String*/ ,_width,_height);
RDebugUtils.currentLine=33751042;
 //BA.debugLineNum = 33751042;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.streams.File.InputStreamWrapper  _getinputstream(b4a.example.httpjob __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getinputstream", true))
	 {return ((anywheresoftware.b4a.objects.streams.File.InputStreamWrapper) Debug.delegate(ba, "getinputstream", null));}
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
RDebugUtils.currentLine=33882112;
 //BA.debugLineNum = 33882112;BA.debugLine="Public Sub GetInputStream As InputStream";
RDebugUtils.currentLine=33882113;
 //BA.debugLineNum = 33882113;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
RDebugUtils.currentLine=33882114;
 //BA.debugLineNum = 33882114;BA.debugLine="In = File.OpenInput(HttpUtils2Service.TempFolder,";
_in = __c.File.OpenInput(_httputils2service._tempfolder /*String*/ ,__ref._taskid /*String*/ );
RDebugUtils.currentLine=33882115;
 //BA.debugLineNum = 33882115;BA.debugLine="Return In";
if (true) return _in;
RDebugUtils.currentLine=33882116;
 //BA.debugLineNum = 33882116;BA.debugLine="End Sub";
return null;
}
public String  _getstring2(b4a.example.httpjob __ref,String _encoding) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "getstring2", true))
	 {return ((String) Debug.delegate(ba, "getstring2", new Object[] {_encoding}));}
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _tr = null;
String _res = "";
RDebugUtils.currentLine=33488896;
 //BA.debugLineNum = 33488896;BA.debugLine="Public Sub GetString2(Encoding As String) As Strin";
RDebugUtils.currentLine=33488900;
 //BA.debugLineNum = 33488900;BA.debugLine="Dim tr As TextReader";
_tr = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
RDebugUtils.currentLine=33488901;
 //BA.debugLineNum = 33488901;BA.debugLine="tr.Initialize2(File.OpenInput(HttpUtils2Service.T";
_tr.Initialize2((java.io.InputStream)(__c.File.OpenInput(_httputils2service._tempfolder /*String*/ ,__ref._taskid /*String*/ ).getObject()),_encoding);
RDebugUtils.currentLine=33488902;
 //BA.debugLineNum = 33488902;BA.debugLine="Dim res As String = tr.ReadAll";
_res = _tr.ReadAll();
RDebugUtils.currentLine=33488903;
 //BA.debugLineNum = 33488903;BA.debugLine="tr.Close";
_tr.Close();
RDebugUtils.currentLine=33488904;
 //BA.debugLineNum = 33488904;BA.debugLine="Return res";
if (true) return _res;
RDebugUtils.currentLine=33488906;
 //BA.debugLineNum = 33488906;BA.debugLine="End Sub";
return "";
}
public String  _head(b4a.example.httpjob __ref,String _link) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "head", true))
	 {return ((String) Debug.delegate(ba, "head", new Object[] {_link}));}
RDebugUtils.currentLine=32768000;
 //BA.debugLineNum = 32768000;BA.debugLine="Public Sub Head(Link As String)";
RDebugUtils.currentLine=32768001;
 //BA.debugLineNum = 32768001;BA.debugLine="Try";
try {RDebugUtils.currentLine=32768002;
 //BA.debugLineNum = 32768002;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=32768003;
 //BA.debugLineNum = 32768003;BA.debugLine="req.InitializeHead(Link)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeHead(_link);
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=32768005;
 //BA.debugLineNum = 32768005;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("932768005",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=32768006;
 //BA.debugLineNum = 32768006;BA.debugLine="req.InitializeHead(InvalidURL)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializeHead(__ref._invalidurl /*String*/ );
 };
RDebugUtils.currentLine=32768008;
 //BA.debugLineNum = 32768008;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=32768009;
 //BA.debugLineNum = 32768009;BA.debugLine="End Sub";
return "";
}
public boolean  _multipartstartsection(b4a.example.httpjob __ref,anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _stream,boolean _empty) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "multipartstartsection", true))
	 {return ((Boolean) Debug.delegate(ba, "multipartstartsection", new Object[] {_stream,_empty}));}
RDebugUtils.currentLine=32899072;
 //BA.debugLineNum = 32899072;BA.debugLine="Private Sub MultipartStartSection (stream As Outpu";
RDebugUtils.currentLine=32899073;
 //BA.debugLineNum = 32899073;BA.debugLine="If empty = False Then";
if (_empty==__c.False) { 
RDebugUtils.currentLine=32899074;
 //BA.debugLineNum = 32899074;BA.debugLine="stream.WriteBytes(Array As Byte(13, 10), 0, 2)";
_stream.WriteBytes(new byte[]{(byte) (13),(byte) (10)},(int) (0),(int) (2));
 }else {
RDebugUtils.currentLine=32899076;
 //BA.debugLineNum = 32899076;BA.debugLine="empty = False";
_empty = __c.False;
 };
RDebugUtils.currentLine=32899078;
 //BA.debugLineNum = 32899078;BA.debugLine="Return empty";
if (true) return _empty;
RDebugUtils.currentLine=32899079;
 //BA.debugLineNum = 32899079;BA.debugLine="End Sub";
return false;
}
public String  _patchbytes(b4a.example.httpjob __ref,String _link,byte[] _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "patchbytes", true))
	 {return ((String) Debug.delegate(ba, "patchbytes", new Object[] {_link,_data}));}
RDebugUtils.currentLine=32702464;
 //BA.debugLineNum = 32702464;BA.debugLine="Public Sub PatchBytes(Link As String, Data() As By";
RDebugUtils.currentLine=32702465;
 //BA.debugLineNum = 32702465;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=32702473;
 //BA.debugLineNum = 32702473;BA.debugLine="Try";
try {RDebugUtils.currentLine=32702474;
 //BA.debugLineNum = 32702474;BA.debugLine="req.InitializePatch2(Link, Data)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePatch2(_link,_data);
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=32702476;
 //BA.debugLineNum = 32702476;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("932702476",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=32702477;
 //BA.debugLineNum = 32702477;BA.debugLine="req.InitializePatch2(InvalidURL, Data)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePatch2(__ref._invalidurl /*String*/ ,_data);
 };
RDebugUtils.currentLine=32702481;
 //BA.debugLineNum = 32702481;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=32702482;
 //BA.debugLineNum = 32702482;BA.debugLine="End Sub";
return "";
}
public String  _patchstring(b4a.example.httpjob __ref,String _link,String _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "patchstring", true))
	 {return ((String) Debug.delegate(ba, "patchstring", new Object[] {_link,_text}));}
RDebugUtils.currentLine=32636928;
 //BA.debugLineNum = 32636928;BA.debugLine="Public Sub PatchString(Link As String, Text As Str";
RDebugUtils.currentLine=32636929;
 //BA.debugLineNum = 32636929;BA.debugLine="PatchBytes(Link, Text.GetBytes(\"UTF8\"))";
__ref._patchbytes /*String*/ (null,_link,_text.getBytes("UTF8"));
RDebugUtils.currentLine=32636930;
 //BA.debugLineNum = 32636930;BA.debugLine="End Sub";
return "";
}
public String  _postbytes(b4a.example.httpjob __ref,String _link,byte[] _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "postbytes", true))
	 {return ((String) Debug.delegate(ba, "postbytes", new Object[] {_link,_data}));}
RDebugUtils.currentLine=32440320;
 //BA.debugLineNum = 32440320;BA.debugLine="Public Sub PostBytes(Link As String, Data() As Byt";
RDebugUtils.currentLine=32440321;
 //BA.debugLineNum = 32440321;BA.debugLine="Try";
try {RDebugUtils.currentLine=32440322;
 //BA.debugLineNum = 32440322;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=32440323;
 //BA.debugLineNum = 32440323;BA.debugLine="req.InitializePost2(Link, Data)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePost2(_link,_data);
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=32440325;
 //BA.debugLineNum = 32440325;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("932440325",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=32440326;
 //BA.debugLineNum = 32440326;BA.debugLine="req.InitializePost2(InvalidURL, Data)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePost2(__ref._invalidurl /*String*/ ,_data);
 };
RDebugUtils.currentLine=32440328;
 //BA.debugLineNum = 32440328;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=32440329;
 //BA.debugLineNum = 32440329;BA.debugLine="End Sub";
return "";
}
public String  _postfile(b4a.example.httpjob __ref,String _link,String _dir,String _filename) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "postfile", true))
	 {return ((String) Debug.delegate(ba, "postfile", new Object[] {_link,_dir,_filename}));}
int _length = 0;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
RDebugUtils.currentLine=32964608;
 //BA.debugLineNum = 32964608;BA.debugLine="Public Sub PostFile(Link As String, Dir As String,";
RDebugUtils.currentLine=32964609;
 //BA.debugLineNum = 32964609;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=32964614;
 //BA.debugLineNum = 32964614;BA.debugLine="Dim length As Int";
_length = 0;
RDebugUtils.currentLine=32964615;
 //BA.debugLineNum = 32964615;BA.debugLine="If Dir = File.DirAssets Then";
if ((_dir).equals(__c.File.getDirAssets())) { 
RDebugUtils.currentLine=32964616;
 //BA.debugLineNum = 32964616;BA.debugLine="Log(\"Cannot send files from the assets folder.\")";
__c.LogImpl("932964616","Cannot send files from the assets folder.",0);
RDebugUtils.currentLine=32964617;
 //BA.debugLineNum = 32964617;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=32964619;
 //BA.debugLineNum = 32964619;BA.debugLine="length = File.Size(Dir, FileName)";
_length = (int) (__c.File.Size(_dir,_filename));
RDebugUtils.currentLine=32964620;
 //BA.debugLineNum = 32964620;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
RDebugUtils.currentLine=32964621;
 //BA.debugLineNum = 32964621;BA.debugLine="In = File.OpenInput(Dir, FileName)";
_in = __c.File.OpenInput(_dir,_filename);
RDebugUtils.currentLine=32964622;
 //BA.debugLineNum = 32964622;BA.debugLine="If length < 1000000 Then '1mb";
if (_length<1000000) { 
RDebugUtils.currentLine=32964625;
 //BA.debugLineNum = 32964625;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=32964626;
 //BA.debugLineNum = 32964626;BA.debugLine="out.InitializeToBytesArray(length)";
_out.InitializeToBytesArray(_length);
RDebugUtils.currentLine=32964627;
 //BA.debugLineNum = 32964627;BA.debugLine="File.Copy2(In, out)";
__c.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_out.getObject()));
RDebugUtils.currentLine=32964628;
 //BA.debugLineNum = 32964628;BA.debugLine="PostBytes(Link, out.ToBytesArray)";
__ref._postbytes /*String*/ (null,_link,_out.ToBytesArray());
 }else {
RDebugUtils.currentLine=32964630;
 //BA.debugLineNum = 32964630;BA.debugLine="req.InitializePost(Link, In, length)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePost(_link,(java.io.InputStream)(_in.getObject()),_length);
RDebugUtils.currentLine=32964631;
 //BA.debugLineNum = 32964631;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\",";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
 };
RDebugUtils.currentLine=32964634;
 //BA.debugLineNum = 32964634;BA.debugLine="End Sub";
return "";
}
public String  _postmultipart(b4a.example.httpjob __ref,String _link,anywheresoftware.b4a.objects.collections.Map _namevalues,anywheresoftware.b4a.objects.collections.List _files) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "postmultipart", true))
	 {return ((String) Debug.delegate(ba, "postmultipart", new Object[] {_link,_namevalues,_files}));}
String _boundary = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _stream = null;
byte[] _b = null;
String _eol = "";
boolean _empty = false;
String _key = "";
String _value = "";
String _s = "";
b4a.example.httpjob._multipartfiledata _fd = null;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
RDebugUtils.currentLine=32833536;
 //BA.debugLineNum = 32833536;BA.debugLine="Public Sub PostMultipart(Link As String, NameValue";
RDebugUtils.currentLine=32833537;
 //BA.debugLineNum = 32833537;BA.debugLine="Dim boundary As String = \"-----------------------";
_boundary = "---------------------------1461124740692";
RDebugUtils.currentLine=32833538;
 //BA.debugLineNum = 32833538;BA.debugLine="Dim stream As OutputStream";
_stream = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=32833539;
 //BA.debugLineNum = 32833539;BA.debugLine="stream.InitializeToBytesArray(0)";
_stream.InitializeToBytesArray((int) (0));
RDebugUtils.currentLine=32833540;
 //BA.debugLineNum = 32833540;BA.debugLine="Dim b() As Byte";
_b = new byte[(int) (0)];
;
RDebugUtils.currentLine=32833541;
 //BA.debugLineNum = 32833541;BA.debugLine="Dim eol As String = Chr(13) & Chr(10)";
_eol = BA.ObjectToString(__c.Chr((int) (13)))+BA.ObjectToString(__c.Chr((int) (10)));
RDebugUtils.currentLine=32833542;
 //BA.debugLineNum = 32833542;BA.debugLine="Dim empty As Boolean = True";
_empty = __c.True;
RDebugUtils.currentLine=32833543;
 //BA.debugLineNum = 32833543;BA.debugLine="If NameValues <> Null And NameValues.IsInitialize";
if (_namevalues!= null && _namevalues.IsInitialized()) { 
RDebugUtils.currentLine=32833544;
 //BA.debugLineNum = 32833544;BA.debugLine="For Each key As String In NameValues.Keys";
{
final anywheresoftware.b4a.BA.IterableList group8 = _namevalues.Keys();
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_key = BA.ObjectToString(group8.Get(index8));
RDebugUtils.currentLine=32833545;
 //BA.debugLineNum = 32833545;BA.debugLine="Dim value As String = NameValues.Get(key)";
_value = BA.ObjectToString(_namevalues.Get((Object)(_key)));
RDebugUtils.currentLine=32833546;
 //BA.debugLineNum = 32833546;BA.debugLine="empty = MultipartStartSection (stream, empty)";
_empty = __ref._multipartstartsection /*boolean*/ (null,_stream,_empty);
RDebugUtils.currentLine=32833547;
 //BA.debugLineNum = 32833547;BA.debugLine="Dim s As String = _ $\"--${boundary} Content-Dis";
_s = ("--"+__c.SmartStringFormatter("",(Object)(_boundary))+"\n"+"Content-Disposition: form-data; name=\""+__c.SmartStringFormatter("",(Object)(_key))+"\"\n"+"\n"+""+__c.SmartStringFormatter("",(Object)(_value))+"");
RDebugUtils.currentLine=32833552;
 //BA.debugLineNum = 32833552;BA.debugLine="b = s.Replace(CRLF, eol).GetBytes(\"UTF8\")";
_b = _s.replace(__c.CRLF,_eol).getBytes("UTF8");
RDebugUtils.currentLine=32833553;
 //BA.debugLineNum = 32833553;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
 }
};
 };
RDebugUtils.currentLine=32833556;
 //BA.debugLineNum = 32833556;BA.debugLine="If Files <> Null And Files.IsInitialized Then";
if (_files!= null && _files.IsInitialized()) { 
RDebugUtils.currentLine=32833557;
 //BA.debugLineNum = 32833557;BA.debugLine="For Each fd As MultipartFileData In Files";
{
final anywheresoftware.b4a.BA.IterableList group17 = _files;
final int groupLen17 = group17.getSize()
;int index17 = 0;
;
for (; index17 < groupLen17;index17++){
_fd = (b4a.example.httpjob._multipartfiledata)(group17.Get(index17));
RDebugUtils.currentLine=32833558;
 //BA.debugLineNum = 32833558;BA.debugLine="empty = MultipartStartSection (stream, empty)";
_empty = __ref._multipartstartsection /*boolean*/ (null,_stream,_empty);
RDebugUtils.currentLine=32833559;
 //BA.debugLineNum = 32833559;BA.debugLine="Dim s As String = _ $\"--${boundary} Content-Dis";
_s = ("--"+__c.SmartStringFormatter("",(Object)(_boundary))+"\n"+"Content-Disposition: form-data; name=\""+__c.SmartStringFormatter("",(Object)(_fd.KeyName /*String*/ ))+"\"; filename=\""+__c.SmartStringFormatter("",(Object)(_fd.FileName /*String*/ ))+"\"\n"+"Content-Type: "+__c.SmartStringFormatter("",(Object)(_fd.ContentType /*String*/ ))+"\n"+"\n"+"");
RDebugUtils.currentLine=32833565;
 //BA.debugLineNum = 32833565;BA.debugLine="b = s.Replace(CRLF, eol).GetBytes(\"UTF8\")";
_b = _s.replace(__c.CRLF,_eol).getBytes("UTF8");
RDebugUtils.currentLine=32833566;
 //BA.debugLineNum = 32833566;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
RDebugUtils.currentLine=32833567;
 //BA.debugLineNum = 32833567;BA.debugLine="Dim in As InputStream = File.OpenInput(fd.Dir,";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
_in = __c.File.OpenInput(_fd.Dir /*String*/ ,_fd.FileName /*String*/ );
RDebugUtils.currentLine=32833568;
 //BA.debugLineNum = 32833568;BA.debugLine="File.Copy2(in, stream)";
__c.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_stream.getObject()));
 }
};
 };
RDebugUtils.currentLine=32833571;
 //BA.debugLineNum = 32833571;BA.debugLine="empty = MultipartStartSection (stream, empty)";
_empty = __ref._multipartstartsection /*boolean*/ (null,_stream,_empty);
RDebugUtils.currentLine=32833572;
 //BA.debugLineNum = 32833572;BA.debugLine="s = _ $\"--${boundary}-- \"$";
_s = ("--"+__c.SmartStringFormatter("",(Object)(_boundary))+"--\n"+"");
RDebugUtils.currentLine=32833575;
 //BA.debugLineNum = 32833575;BA.debugLine="b = s.Replace(CRLF, eol).GetBytes(\"UTF8\")";
_b = _s.replace(__c.CRLF,_eol).getBytes("UTF8");
RDebugUtils.currentLine=32833576;
 //BA.debugLineNum = 32833576;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
RDebugUtils.currentLine=32833577;
 //BA.debugLineNum = 32833577;BA.debugLine="PostBytes(Link, stream.ToBytesArray)";
__ref._postbytes /*String*/ (null,_link,_stream.ToBytesArray());
RDebugUtils.currentLine=32833578;
 //BA.debugLineNum = 32833578;BA.debugLine="req.SetContentType(\"multipart/form-data; boundary";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .SetContentType("multipart/form-data; boundary="+_boundary);
RDebugUtils.currentLine=32833579;
 //BA.debugLineNum = 32833579;BA.debugLine="req.SetContentEncoding(\"UTF8\")";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .SetContentEncoding("UTF8");
RDebugUtils.currentLine=32833580;
 //BA.debugLineNum = 32833580;BA.debugLine="End Sub";
return "";
}
public String  _putbytes(b4a.example.httpjob __ref,String _link,byte[] _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "putbytes", true))
	 {return ((String) Debug.delegate(ba, "putbytes", new Object[] {_link,_data}));}
RDebugUtils.currentLine=32571392;
 //BA.debugLineNum = 32571392;BA.debugLine="Public Sub PutBytes(Link As String, Data() As Byte";
RDebugUtils.currentLine=32571393;
 //BA.debugLineNum = 32571393;BA.debugLine="Try";
try {RDebugUtils.currentLine=32571394;
 //BA.debugLineNum = 32571394;BA.debugLine="Link = AddScheme(Link)";
_link = __ref._addscheme /*String*/ (null,_link);
RDebugUtils.currentLine=32571395;
 //BA.debugLineNum = 32571395;BA.debugLine="req.InitializePut2(Link, Data)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePut2(_link,_data);
 } 
       catch (Exception e5) {
			ba.setLastException(e5);RDebugUtils.currentLine=32571397;
 //BA.debugLineNum = 32571397;BA.debugLine="Log($\"Invalid link: ${Link}\"$)";
__c.LogImpl("932571397",("Invalid link: "+__c.SmartStringFormatter("",(Object)(_link))+""),0);
RDebugUtils.currentLine=32571398;
 //BA.debugLineNum = 32571398;BA.debugLine="req.InitializePut2(InvalidURL, Data)";
__ref._req /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ .InitializePut2(__ref._invalidurl /*String*/ ,_data);
 };
RDebugUtils.currentLine=32571400;
 //BA.debugLineNum = 32571400;BA.debugLine="CallSubDelayed2(HttpUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(ba,(Object)(_httputils2service.getObject()),"SubmitJob",this);
RDebugUtils.currentLine=32571401;
 //BA.debugLineNum = 32571401;BA.debugLine="End Sub";
return "";
}
public String  _putstring(b4a.example.httpjob __ref,String _link,String _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="httpjob";
if (Debug.shouldDelegate(ba, "putstring", true))
	 {return ((String) Debug.delegate(ba, "putstring", new Object[] {_link,_text}));}
RDebugUtils.currentLine=32505856;
 //BA.debugLineNum = 32505856;BA.debugLine="Public Sub PutString(Link As String, Text As Strin";
RDebugUtils.currentLine=32505857;
 //BA.debugLineNum = 32505857;BA.debugLine="PutBytes(Link, Text.GetBytes(\"UTF8\"))";
__ref._putbytes /*String*/ (null,_link,_text.getBytes("UTF8"));
RDebugUtils.currentLine=32505858;
 //BA.debugLineNum = 32505858;BA.debugLine="End Sub";
return "";
}
}