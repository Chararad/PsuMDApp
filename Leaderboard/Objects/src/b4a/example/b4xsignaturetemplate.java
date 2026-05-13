package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xsignaturetemplate extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.b4xsignaturetemplate");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xsignaturetemplate.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbase = null;
public anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
public float _lastx = 0f;
public float _lasty = 0f;
public float _strokewidth = 0f;
public int _strokecolor = 0;
public int _textcolor = 0;
public int _backgroundcolor = 0;
public boolean _adddateandtime = false;
public anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont _textfont = null;
public int _numberofpoints = 0;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public anywheresoftware.b4a.objects.B4XViewWrapper  _getpanel(b4a.example.b4xsignaturetemplate __ref,b4a.example.b4xdialog _dialog) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "getpanel", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(ba, "getpanel", new Object[] {_dialog}));}
RDebugUtils.currentLine=18939904;
 //BA.debugLineNum = 18939904;BA.debugLine="Public Sub GetPanel (Dialog As B4XDialog) As B4XVi";
RDebugUtils.currentLine=18939905;
 //BA.debugLineNum = 18939905;BA.debugLine="Return mBase";
if (true) return __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ;
RDebugUtils.currentLine=18939906;
 //BA.debugLineNum = 18939906;BA.debugLine="End Sub";
return null;
}
public String  _show(b4a.example.b4xsignaturetemplate __ref,b4a.example.b4xdialog _dialog) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "show", true))
	 {return ((String) Debug.delegate(ba, "show", new Object[] {_dialog}));}
RDebugUtils.currentLine=19005440;
 //BA.debugLineNum = 19005440;BA.debugLine="Private Sub Show (Dialog As B4XDialog) 'ignore";
RDebugUtils.currentLine=19005441;
 //BA.debugLineNum = 19005441;BA.debugLine="cvs.DrawRect(cvs.TargetRect, BackgroundColor, Tru";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .DrawRect(__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .getTargetRect(),__ref._backgroundcolor /*int*/ ,__c.True,(float) (0));
RDebugUtils.currentLine=19005442;
 //BA.debugLineNum = 19005442;BA.debugLine="cvs.Invalidate";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .Invalidate();
RDebugUtils.currentLine=19005443;
 //BA.debugLineNum = 19005443;BA.debugLine="NumberOfPoints = 0";
__ref._numberofpoints /*int*/  = (int) (0);
RDebugUtils.currentLine=19005444;
 //BA.debugLineNum = 19005444;BA.debugLine="End Sub";
return "";
}
public String  _dialogclosed(b4a.example.b4xsignaturetemplate __ref,int _result) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "dialogclosed", true))
	 {return ((String) Debug.delegate(ba, "dialogclosed", new Object[] {_result}));}
anywheresoftware.b4a.objects.B4XCanvas.B4XRect _r = null;
int _baseline = 0;
RDebugUtils.currentLine=19202048;
 //BA.debugLineNum = 19202048;BA.debugLine="Private Sub DialogClosed (Result As Int)";
RDebugUtils.currentLine=19202049;
 //BA.debugLineNum = 19202049;BA.debugLine="If Result = xui.DialogResponse_Positive And AddDa";
if (_result==__ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .DialogResponse_Positive && __ref._adddateandtime /*boolean*/ ) { 
RDebugUtils.currentLine=19202050;
 //BA.debugLineNum = 19202050;BA.debugLine="Dim r As B4XRect = cvs.MeasureText(\"M\", TextFont";
_r = __ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .MeasureText("M",__ref._textfont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ );
RDebugUtils.currentLine=19202051;
 //BA.debugLineNum = 19202051;BA.debugLine="Dim Baseline As Int = cvs.TargetRect.Bottom - r.";
_baseline = (int) (__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .getTargetRect().getBottom()-_r.getHeight()-_r.getTop()-__c.DipToCurrent((int) (2)));
RDebugUtils.currentLine=19202052;
 //BA.debugLineNum = 19202052;BA.debugLine="cvs.DrawText($\"$DateTime{DateTime.Now}\"$, 2dip,";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .DrawText(ba,(""+__c.SmartStringFormatter("datetime",(Object)(__c.DateTime.getNow()))+""),(float) (__c.DipToCurrent((int) (2))),(float) (_baseline),__ref._textfont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ ,__ref._textcolor /*int*/ ,BA.getEnumFromString(android.graphics.Paint.Align.class,"LEFT"));
 };
RDebugUtils.currentLine=19202054;
 //BA.debugLineNum = 19202054;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.b4xsignaturetemplate __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
RDebugUtils.currentLine=18743296;
 //BA.debugLineNum = 18743296;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=18743297;
 //BA.debugLineNum = 18743297;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=18743298;
 //BA.debugLineNum = 18743298;BA.debugLine="Public mBase As B4XView";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=18743299;
 //BA.debugLineNum = 18743299;BA.debugLine="Private cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
RDebugUtils.currentLine=18743300;
 //BA.debugLineNum = 18743300;BA.debugLine="Private LastX, LastY As Float";
_lastx = 0f;
_lasty = 0f;
RDebugUtils.currentLine=18743301;
 //BA.debugLineNum = 18743301;BA.debugLine="Public StrokeWidth As Float = 2dip";
_strokewidth = (float) (__c.DipToCurrent((int) (2)));
RDebugUtils.currentLine=18743302;
 //BA.debugLineNum = 18743302;BA.debugLine="Public StrokeColor As Int = xui.Color_Black";
_strokecolor = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .Color_Black;
RDebugUtils.currentLine=18743303;
 //BA.debugLineNum = 18743303;BA.debugLine="Public TextColor As Int = 0xFFFF8800";
_textcolor = ((int)0xffff8800);
RDebugUtils.currentLine=18743304;
 //BA.debugLineNum = 18743304;BA.debugLine="Public BackgroundColor As Int = xui.Color_White";
_backgroundcolor = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .Color_White;
RDebugUtils.currentLine=18743305;
 //BA.debugLineNum = 18743305;BA.debugLine="Public AddDateAndTime As Boolean = True";
_adddateandtime = __c.True;
RDebugUtils.currentLine=18743306;
 //BA.debugLineNum = 18743306;BA.debugLine="Public TextFont As B4XFont";
_textfont = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont();
RDebugUtils.currentLine=18743307;
 //BA.debugLineNum = 18743307;BA.debugLine="Public NumberOfPoints As Int";
_numberofpoints = 0;
RDebugUtils.currentLine=18743308;
 //BA.debugLineNum = 18743308;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper  _getbitmap(b4a.example.b4xsignaturetemplate __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "getbitmap", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper) Debug.delegate(ba, "getbitmap", null));}
RDebugUtils.currentLine=19136512;
 //BA.debugLineNum = 19136512;BA.debugLine="Public Sub getBitmap As B4XBitmap";
RDebugUtils.currentLine=19136513;
 //BA.debugLineNum = 19136513;BA.debugLine="Return cvs.CreateBitmap";
if (true) return __ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .CreateBitmap();
RDebugUtils.currentLine=19136514;
 //BA.debugLineNum = 19136514;BA.debugLine="End Sub";
return null;
}
public String  _initialize(b4a.example.b4xsignaturetemplate __ref,anywheresoftware.b4a.BA _ba) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba}));}
RDebugUtils.currentLine=18808832;
 //BA.debugLineNum = 18808832;BA.debugLine="Public Sub Initialize";
RDebugUtils.currentLine=18808833;
 //BA.debugLineNum = 18808833;BA.debugLine="mBase = xui.CreatePanel(\"mBase\")";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"mBase");
RDebugUtils.currentLine=18808834;
 //BA.debugLineNum = 18808834;BA.debugLine="mBase.SetLayoutAnimated(0, 0, 0, 300dip, 200dip)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),__c.DipToCurrent((int) (300)),__c.DipToCurrent((int) (200)));
RDebugUtils.currentLine=18808835;
 //BA.debugLineNum = 18808835;BA.debugLine="cvs.Initialize(mBase)";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .Initialize(__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ );
RDebugUtils.currentLine=18808836;
 //BA.debugLineNum = 18808836;BA.debugLine="TextFont = xui.CreateDefaultFont(14)";
__ref._textfont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreateDefaultFont((float) (14));
RDebugUtils.currentLine=18808837;
 //BA.debugLineNum = 18808837;BA.debugLine="End Sub";
return "";
}
public String  _mbase_touch(b4a.example.b4xsignaturetemplate __ref,int _action,float _x,float _y) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "mbase_touch", true))
	 {return ((String) Debug.delegate(ba, "mbase_touch", new Object[] {_action,_x,_y}));}
RDebugUtils.currentLine=19070976;
 //BA.debugLineNum = 19070976;BA.debugLine="Private Sub mBase_Touch (Action As Int, X As Float";
RDebugUtils.currentLine=19070977;
 //BA.debugLineNum = 19070977;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_DOWN,__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_MOVE)) {
case 0: {
RDebugUtils.currentLine=19070979;
 //BA.debugLineNum = 19070979;BA.debugLine="LastX = X";
__ref._lastx /*float*/  = _x;
RDebugUtils.currentLine=19070980;
 //BA.debugLineNum = 19070980;BA.debugLine="LastY = Y";
__ref._lasty /*float*/  = _y;
 break; }
case 1: {
RDebugUtils.currentLine=19070982;
 //BA.debugLineNum = 19070982;BA.debugLine="cvs.DrawLine(LastX, LastY, X, Y, StrokeColor, S";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .DrawLine(__ref._lastx /*float*/ ,__ref._lasty /*float*/ ,_x,_y,__ref._strokecolor /*int*/ ,__ref._strokewidth /*float*/ );
RDebugUtils.currentLine=19070983;
 //BA.debugLineNum = 19070983;BA.debugLine="LastX = X";
__ref._lastx /*float*/  = _x;
RDebugUtils.currentLine=19070984;
 //BA.debugLineNum = 19070984;BA.debugLine="LastY = Y";
__ref._lasty /*float*/  = _y;
RDebugUtils.currentLine=19070985;
 //BA.debugLineNum = 19070985;BA.debugLine="cvs.Invalidate";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .Invalidate();
RDebugUtils.currentLine=19070986;
 //BA.debugLineNum = 19070986;BA.debugLine="NumberOfPoints = NumberOfPoints + 1";
__ref._numberofpoints /*int*/  = (int) (__ref._numberofpoints /*int*/ +1);
 break; }
}
;
RDebugUtils.currentLine=19070988;
 //BA.debugLineNum = 19070988;BA.debugLine="End Sub";
return "";
}
public String  _resize(b4a.example.b4xsignaturetemplate __ref,int _width,int _height) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xsignaturetemplate";
if (Debug.shouldDelegate(ba, "resize", true))
	 {return ((String) Debug.delegate(ba, "resize", new Object[] {_width,_height}));}
RDebugUtils.currentLine=18874368;
 //BA.debugLineNum = 18874368;BA.debugLine="Public Sub Resize(Width As Int, Height As Int)";
RDebugUtils.currentLine=18874369;
 //BA.debugLineNum = 18874369;BA.debugLine="mBase.SetLayoutAnimated(0, 0, 0, Width, Height)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
RDebugUtils.currentLine=18874370;
 //BA.debugLineNum = 18874370;BA.debugLine="cvs.Resize(Width, Height)";
__ref._cvs /*anywheresoftware.b4a.objects.B4XCanvas*/ .Resize((float) (_width),(float) (_height));
RDebugUtils.currentLine=18874371;
 //BA.debugLineNum = 18874371;BA.debugLine="End Sub";
return "";
}
}