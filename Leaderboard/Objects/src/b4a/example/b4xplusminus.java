package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xplusminus extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.b4xplusminus");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xplusminus.class).invoke(this, new Object[] {null});
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
public String _meventname = "";
public Object _mcallback = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbase = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public Object _tag = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlplus = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlminus = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblplus = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblminus = null;
public String _formation = "";
public boolean _mcyclic = false;
public boolean _mrapid = false;
public anywheresoftware.b4a.objects.B4XViewWrapper _mainlabel = null;
public int _arrowssize = 0;
public anywheresoftware.b4a.objects.collections.List _mstringitems = null;
public double _mstartrange = 0;
public double _minterval = 0;
public double _mendrange = 0;
public int _mselectedindex = 0;
public int _loopindex = 0;
public int _rapidperiod1 = 0;
public int _rapidperiod2 = 0;
public b4a.example.b4xformatter _formatter = null;
public boolean _stringmode = false;
public int _size = 0;
public boolean _mhaptic = false;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public String  _base_resize(b4a.example.b4xplusminus __ref,double _width,double _height) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "base_resize", true))
	 {return ((String) Debug.delegate(ba, "base_resize", new Object[] {_width,_height}));}
RDebugUtils.currentLine=14876672;
 //BA.debugLineNum = 14876672;BA.debugLine="Public Sub Base_Resize (Width As Double, Height As";
RDebugUtils.currentLine=14876673;
 //BA.debugLineNum = 14876673;BA.debugLine="If Formation = \"Horizontal\" Then";
if ((__ref._formation /*String*/ ).equals("Horizontal")) { 
RDebugUtils.currentLine=14876674;
 //BA.debugLineNum = 14876674;BA.debugLine="pnlMinus.SetLayoutAnimated(0, 0, 0, ArrowsSize,";
__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),__ref._arrowssize /*int*/ ,(int) (_height));
RDebugUtils.currentLine=14876675;
 //BA.debugLineNum = 14876675;BA.debugLine="pnlPlus.SetLayoutAnimated(0, Width - ArrowsSize,";
__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (_width-__ref._arrowssize /*int*/ ),(int) (0),__ref._arrowssize /*int*/ ,(int) (_height));
RDebugUtils.currentLine=14876676;
 //BA.debugLineNum = 14876676;BA.debugLine="MainLabel.SetLayoutAnimated(0, 0, 0, Width, Heig";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),(int) (_height));
 }else 
{RDebugUtils.currentLine=14876677;
 //BA.debugLineNum = 14876677;BA.debugLine="Else if Formation = \"Vertical\" Then";
if ((__ref._formation /*String*/ ).equals("Vertical")) { 
RDebugUtils.currentLine=14876678;
 //BA.debugLineNum = 14876678;BA.debugLine="pnlPlus.SetLayoutAnimated(0, 0, 0, Width, Arrows";
__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),__ref._arrowssize /*int*/ );
RDebugUtils.currentLine=14876679;
 //BA.debugLineNum = 14876679;BA.debugLine="pnlMinus.SetLayoutAnimated(0, 0, Height - Arrows";
__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (_height-__ref._arrowssize /*int*/ ),(int) (_width),__ref._arrowssize /*int*/ );
RDebugUtils.currentLine=14876680;
 //BA.debugLineNum = 14876680;BA.debugLine="MainLabel.SetLayoutAnimated(0, 0, 0, Width, Heig";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),(int) (_height));
 }else {
RDebugUtils.currentLine=14876682;
 //BA.debugLineNum = 14876682;BA.debugLine="pnlMinus.SetLayoutAnimated(0, 0, Height - Arrows";
__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (_height-__ref._arrowssize /*int*/ ),(int) (_width/(double)2),__ref._arrowssize /*int*/ );
RDebugUtils.currentLine=14876683;
 //BA.debugLineNum = 14876683;BA.debugLine="pnlPlus.SetLayoutAnimated(0, Width / 2, pnlMinus";
__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (_width/(double)2),__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getTop(),__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=14876684;
 //BA.debugLineNum = 14876684;BA.debugLine="MainLabel.SetLayoutAnimated(0, 0, 0, Width, Heig";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),(int) (_height-__ref._arrowssize /*int*/ +__c.DipToCurrent((int) (5))));
 }}
;
RDebugUtils.currentLine=14876686;
 //BA.debugLineNum = 14876686;BA.debugLine="lblMinus.SetLayoutAnimated(0, 0, 0, pnlMinus.Widt";
__ref._lblminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=14876687;
 //BA.debugLineNum = 14876687;BA.debugLine="lblPlus.SetLayoutAnimated(0, 0, 0, pnlPlus.Width,";
__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=14876688;
 //BA.debugLineNum = 14876688;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.b4xplusminus __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
RDebugUtils.currentLine=14680064;
 //BA.debugLineNum = 14680064;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=14680065;
 //BA.debugLineNum = 14680065;BA.debugLine="Private mEventName As String 'ignore";
_meventname = "";
RDebugUtils.currentLine=14680066;
 //BA.debugLineNum = 14680066;BA.debugLine="Private mCallBack As Object 'ignore";
_mcallback = new Object();
RDebugUtils.currentLine=14680067;
 //BA.debugLineNum = 14680067;BA.debugLine="Public mBase As B4XView 'ignore";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=14680068;
 //BA.debugLineNum = 14680068;BA.debugLine="Private xui As XUI 'ignore";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=14680069;
 //BA.debugLineNum = 14680069;BA.debugLine="Public Tag As Object";
_tag = new Object();
RDebugUtils.currentLine=14680070;
 //BA.debugLineNum = 14680070;BA.debugLine="Public pnlPlus, pnlMinus As B4XView";
_pnlplus = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnlminus = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=14680071;
 //BA.debugLineNum = 14680071;BA.debugLine="Public lblPlus, lblMinus As B4XView";
_lblplus = new anywheresoftware.b4a.objects.B4XViewWrapper();
_lblminus = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=14680072;
 //BA.debugLineNum = 14680072;BA.debugLine="Public Formation As String";
_formation = "";
RDebugUtils.currentLine=14680073;
 //BA.debugLineNum = 14680073;BA.debugLine="Public mCyclic As Boolean";
_mcyclic = false;
RDebugUtils.currentLine=14680074;
 //BA.debugLineNum = 14680074;BA.debugLine="Public mRapid As Boolean";
_mrapid = false;
RDebugUtils.currentLine=14680075;
 //BA.debugLineNum = 14680075;BA.debugLine="Public MainLabel As B4XView";
_mainlabel = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=14680076;
 //BA.debugLineNum = 14680076;BA.debugLine="Public ArrowsSize As Int = 30dip";
_arrowssize = __c.DipToCurrent((int) (30));
RDebugUtils.currentLine=14680077;
 //BA.debugLineNum = 14680077;BA.debugLine="Private mStringItems As List";
_mstringitems = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=14680078;
 //BA.debugLineNum = 14680078;BA.debugLine="Private mStartRange, mInterval, mEndRange As Doub";
_mstartrange = 0;
_minterval = 0;
_mendrange = 0;
RDebugUtils.currentLine=14680079;
 //BA.debugLineNum = 14680079;BA.debugLine="Private mSelectedIndex As Int = -1";
_mselectedindex = (int) (-1);
RDebugUtils.currentLine=14680080;
 //BA.debugLineNum = 14680080;BA.debugLine="Private LoopIndex As Int";
_loopindex = 0;
RDebugUtils.currentLine=14680081;
 //BA.debugLineNum = 14680081;BA.debugLine="Public RapidPeriod1 As Int = 1000";
_rapidperiod1 = (int) (1000);
RDebugUtils.currentLine=14680082;
 //BA.debugLineNum = 14680082;BA.debugLine="Public RapidPeriod2 As Int = 30";
_rapidperiod2 = (int) (30);
RDebugUtils.currentLine=14680083;
 //BA.debugLineNum = 14680083;BA.debugLine="Public Formatter As B4XFormatter";
_formatter = new b4a.example.b4xformatter();
RDebugUtils.currentLine=14680084;
 //BA.debugLineNum = 14680084;BA.debugLine="Private StringMode As Boolean";
_stringmode = false;
RDebugUtils.currentLine=14680085;
 //BA.debugLineNum = 14680085;BA.debugLine="Private Size As Int";
_size = 0;
RDebugUtils.currentLine=14680086;
 //BA.debugLineNum = 14680086;BA.debugLine="Public mHaptic As Boolean";
_mhaptic = false;
RDebugUtils.currentLine=14680087;
 //BA.debugLineNum = 14680087;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _createlabel(b4a.example.b4xplusminus __ref,String _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "createlabel", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(ba, "createlabel", new Object[] {_text}));}
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.B4XViewWrapper _xlbl = null;
RDebugUtils.currentLine=15663104;
 //BA.debugLineNum = 15663104;BA.debugLine="Private Sub CreateLabel (text As String) As B4XVie";
RDebugUtils.currentLine=15663105;
 //BA.debugLineNum = 15663105;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=15663106;
 //BA.debugLineNum = 15663106;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(ba,"");
RDebugUtils.currentLine=15663107;
 //BA.debugLineNum = 15663107;BA.debugLine="Dim xlbl As B4XView = lbl";
_xlbl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xlbl = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbl.getObject()));
RDebugUtils.currentLine=15663108;
 //BA.debugLineNum = 15663108;BA.debugLine="xlbl.Font = xui.CreateMaterialIcons(30)";
_xlbl.setFont(__ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreateMaterialIcons((float) (30)));
RDebugUtils.currentLine=15663109;
 //BA.debugLineNum = 15663109;BA.debugLine="xlbl.Text = text";
_xlbl.setText(BA.ObjectToCharSequence(_text));
RDebugUtils.currentLine=15663110;
 //BA.debugLineNum = 15663110;BA.debugLine="xlbl.TextColor = MainLabel.TextColor";
_xlbl.setTextColor(__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getTextColor());
RDebugUtils.currentLine=15663111;
 //BA.debugLineNum = 15663111;BA.debugLine="Return xlbl";
if (true) return _xlbl;
RDebugUtils.currentLine=15663112;
 //BA.debugLineNum = 15663112;BA.debugLine="End Sub";
return null;
}
public String  _decrement(b4a.example.b4xplusminus __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "decrement", true))
	 {return ((String) Debug.delegate(ba, "decrement", null));}
RDebugUtils.currentLine=15400960;
 //BA.debugLineNum = 15400960;BA.debugLine="Public Sub Decrement";
RDebugUtils.currentLine=15400961;
 //BA.debugLineNum = 15400961;BA.debugLine="If mSelectedIndex <= 0 Then";
if (__ref._mselectedindex /*int*/ <=0) { 
RDebugUtils.currentLine=15400962;
 //BA.debugLineNum = 15400962;BA.debugLine="If mCyclic Then";
if (__ref._mcyclic /*boolean*/ ) { 
RDebugUtils.currentLine=15400963;
 //BA.debugLineNum = 15400963;BA.debugLine="mSelectedIndex = Size";
__ref._mselectedindex /*int*/  = __ref._size /*int*/ ;
 }else {
RDebugUtils.currentLine=15400965;
 //BA.debugLineNum = 15400965;BA.debugLine="mSelectedIndex = 0";
__ref._mselectedindex /*int*/  = (int) (0);
RDebugUtils.currentLine=15400966;
 //BA.debugLineNum = 15400966;BA.debugLine="LoopIndex = LoopIndex + 1";
__ref._loopindex /*int*/  = (int) (__ref._loopindex /*int*/ +1);
RDebugUtils.currentLine=15400967;
 //BA.debugLineNum = 15400967;BA.debugLine="Return";
if (true) return "";
 };
 };
RDebugUtils.currentLine=15400970;
 //BA.debugLineNum = 15400970;BA.debugLine="SetIndex(mSelectedIndex - 1, True)";
__ref._setindex /*String*/ (null,(int) (__ref._mselectedindex /*int*/ -1),__c.True);
RDebugUtils.currentLine=15400971;
 //BA.debugLineNum = 15400971;BA.debugLine="End Sub";
return "";
}
public String  _setindex(b4a.example.b4xplusminus __ref,int _i,boolean _raiseevent) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "setindex", true))
	 {return ((String) Debug.delegate(ba, "setindex", new Object[] {_i,_raiseevent}));}
Object _value = null;
RDebugUtils.currentLine=15269888;
 //BA.debugLineNum = 15269888;BA.debugLine="Private Sub SetIndex(i As Int, RaiseEvent As Boole";
RDebugUtils.currentLine=15269889;
 //BA.debugLineNum = 15269889;BA.debugLine="If i >= Size Then i = -1";
if (_i>=__ref._size /*int*/ ) { 
_i = (int) (-1);};
RDebugUtils.currentLine=15269890;
 //BA.debugLineNum = 15269890;BA.debugLine="mSelectedIndex = i";
__ref._mselectedindex /*int*/  = _i;
RDebugUtils.currentLine=15269891;
 //BA.debugLineNum = 15269891;BA.debugLine="If mSelectedIndex = -1 Then";
if (__ref._mselectedindex /*int*/ ==-1) { 
RDebugUtils.currentLine=15269892;
 //BA.debugLineNum = 15269892;BA.debugLine="MainLabel.Text = \"\"";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setText(BA.ObjectToCharSequence(""));
 }else {
RDebugUtils.currentLine=15269894;
 //BA.debugLineNum = 15269894;BA.debugLine="Dim value As Object = GetValueImpl (mSelectedInd";
_value = __ref._getvalueimpl /*Object*/ (null,__ref._mselectedindex /*int*/ );
RDebugUtils.currentLine=15269895;
 //BA.debugLineNum = 15269895;BA.debugLine="If StringMode Then";
if (__ref._stringmode /*boolean*/ ) { 
RDebugUtils.currentLine=15269896;
 //BA.debugLineNum = 15269896;BA.debugLine="InternalSetTextOrCSBuilderToLabel(MainLabel, va";
__ref._internalsettextorcsbuildertolabel /*String*/ (null,__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ,_value);
 }else {
RDebugUtils.currentLine=15269898;
 //BA.debugLineNum = 15269898;BA.debugLine="MainLabel.Text = Formatter.Format(value)";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setText(BA.ObjectToCharSequence(__ref._formatter /*b4a.example.b4xformatter*/ ._format /*String*/ (null,(double)(BA.ObjectToNumber(_value)))));
 };
RDebugUtils.currentLine=15269900;
 //BA.debugLineNum = 15269900;BA.debugLine="If RaiseEvent And xui.SubExists(mCallBack, mEven";
if (_raiseevent && __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .SubExists(ba,__ref._mcallback /*Object*/ ,__ref._meventname /*String*/ +"_valuechanged",(int) (1))) { 
RDebugUtils.currentLine=15269901;
 //BA.debugLineNum = 15269901;BA.debugLine="CallSub2(mCallBack, mEventName & \"_valuechanged";
__c.CallSubNew2(ba,__ref._mcallback /*Object*/ ,__ref._meventname /*String*/ +"_valuechanged",_value);
 };
 };
RDebugUtils.currentLine=15269904;
 //BA.debugLineNum = 15269904;BA.debugLine="lblPlus.Enabled = Size > 0 And (mCyclic Or mSelec";
__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setEnabled(__ref._size /*int*/ >0 && (__ref._mcyclic /*boolean*/  || __ref._mselectedindex /*int*/ <__ref._size /*int*/ -1));
RDebugUtils.currentLine=15269905;
 //BA.debugLineNum = 15269905;BA.debugLine="lblMinus.Enabled = Size > 0 And (mCyclic Or mSele";
__ref._lblminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setEnabled(__ref._size /*int*/ >0 && (__ref._mcyclic /*boolean*/  || __ref._mselectedindex /*int*/ >0));
RDebugUtils.currentLine=15269906;
 //BA.debugLineNum = 15269906;BA.debugLine="End Sub";
return "";
}
public String  _designercreateview(b4a.example.b4xplusminus __ref,Object _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "designercreateview", true))
	 {return ((String) Debug.delegate(ba, "designercreateview", new Object[] {_base,_lbl,_props}));}
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
RDebugUtils.currentLine=14811136;
 //BA.debugLineNum = 14811136;BA.debugLine="Public Sub DesignerCreateView (Base As Object, lbl";
RDebugUtils.currentLine=14811137;
 //BA.debugLineNum = 14811137;BA.debugLine="mBase = Base";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_base));
RDebugUtils.currentLine=14811138;
 //BA.debugLineNum = 14811138;BA.debugLine="Tag = mBase.Tag : mBase.Tag = Me";
__ref._tag /*Object*/  = __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getTag();
RDebugUtils.currentLine=14811138;
 //BA.debugLineNum = 14811138;BA.debugLine="Tag = mBase.Tag : mBase.Tag = Me";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setTag(this);
RDebugUtils.currentLine=14811139;
 //BA.debugLineNum = 14811139;BA.debugLine="MainLabel = lbl";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbl.getObject()));
RDebugUtils.currentLine=14811140;
 //BA.debugLineNum = 14811140;BA.debugLine="MainLabel.SetTextAlignment(\"CENTER\", \"CENTER\")";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=14811141;
 //BA.debugLineNum = 14811141;BA.debugLine="Dim pnl As B4XView = Props.Get(\"page\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_props.Get((Object)("page"))));
RDebugUtils.currentLine=14811151;
 //BA.debugLineNum = 14811151;BA.debugLine="pnlPlus = xui.CreatePanel(\"pnlArrow\")";
__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"pnlArrow");
RDebugUtils.currentLine=14811152;
 //BA.debugLineNum = 14811152;BA.debugLine="pnlMinus = xui.CreatePanel(\"pnlArrow\")";
__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"pnlArrow");
RDebugUtils.currentLine=14811154;
 //BA.debugLineNum = 14811154;BA.debugLine="pnlPlus.Tag = True";
__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setTag((Object)(__c.True));
RDebugUtils.currentLine=14811155;
 //BA.debugLineNum = 14811155;BA.debugLine="pnlMinus.Tag = False";
__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setTag((Object)(__c.False));
RDebugUtils.currentLine=14811156;
 //BA.debugLineNum = 14811156;BA.debugLine="Formation = Props.Get(\"Orientation\")";
__ref._formation /*String*/  = BA.ObjectToString(_props.Get((Object)("Orientation")));
RDebugUtils.currentLine=14811157;
 //BA.debugLineNum = 14811157;BA.debugLine="mCyclic = Props.Get(\"Cyclic\")";
__ref._mcyclic /*boolean*/  = BA.ObjectToBoolean(_props.Get((Object)("Cyclic")));
RDebugUtils.currentLine=14811158;
 //BA.debugLineNum = 14811158;BA.debugLine="mRapid = Props.Get(\"Rapid\")";
__ref._mrapid /*boolean*/  = BA.ObjectToBoolean(_props.Get((Object)("Rapid")));
RDebugUtils.currentLine=14811159;
 //BA.debugLineNum = 14811159;BA.debugLine="mHaptic = Props.GetDefault(\"HapticFeedback\", Fals";
__ref._mhaptic /*boolean*/  = BA.ObjectToBoolean(_props.GetDefault((Object)("HapticFeedback"),(Object)(__c.False)));
RDebugUtils.currentLine=14811160;
 //BA.debugLineNum = 14811160;BA.debugLine="Formatter.GetDefaultFormat.FormatFont = MainLabel";
__ref._formatter /*b4a.example.b4xformatter*/ ._getdefaultformat /*b4a.example.b4xformatter._b4xformatdata*/ (null).FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/  = __ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getFont();
RDebugUtils.currentLine=14811161;
 //BA.debugLineNum = 14811161;BA.debugLine="Formatter.GetDefaultFormat.TextColor = MainLabel.";
__ref._formatter /*b4a.example.b4xformatter*/ ._getdefaultformat /*b4a.example.b4xformatter._b4xformatdata*/ (null).TextColor /*int*/  = __ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getTextColor();
RDebugUtils.currentLine=14811162;
 //BA.debugLineNum = 14811162;BA.debugLine="If Formation = \"Horizontal\" Then";
if ((__ref._formation /*String*/ ).equals("Horizontal")) { 
RDebugUtils.currentLine=14811163;
 //BA.debugLineNum = 14811163;BA.debugLine="lblPlus = CreateLabel(Chr(0xE315))";
__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._createlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null,BA.ObjectToString(__c.Chr(((int)0xe315))));
RDebugUtils.currentLine=14811164;
 //BA.debugLineNum = 14811164;BA.debugLine="lblMinus = CreateLabel(Chr(0xE314))";
__ref._lblminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._createlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null,BA.ObjectToString(__c.Chr(((int)0xe314))));
 }else {
RDebugUtils.currentLine=14811166;
 //BA.debugLineNum = 14811166;BA.debugLine="lblPlus = CreateLabel(Chr(0xE316))";
__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._createlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null,BA.ObjectToString(__c.Chr(((int)0xe316))));
RDebugUtils.currentLine=14811167;
 //BA.debugLineNum = 14811167;BA.debugLine="lblMinus = CreateLabel(Chr(0xE313))";
__ref._lblminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._createlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null,BA.ObjectToString(__c.Chr(((int)0xe313))));
 };
RDebugUtils.currentLine=14811169;
 //BA.debugLineNum = 14811169;BA.debugLine="lblMinus.SetTextAlignment(\"CENTER\", \"CENTER\")";
__ref._lblminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=14811170;
 //BA.debugLineNum = 14811170;BA.debugLine="lblPlus.SetTextAlignment(\"CENTER\", \"CENTER\")";
__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=14811171;
 //BA.debugLineNum = 14811171;BA.debugLine="MainLabel.SetTextAlignment(\"CENTER\", \"CENTER\")";
__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=14811172;
 //BA.debugLineNum = 14811172;BA.debugLine="mBase.AddView(MainLabel, 0, 0, 0, 0)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._mainlabel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
RDebugUtils.currentLine=14811173;
 //BA.debugLineNum = 14811173;BA.debugLine="mBase.AddView(pnlPlus, 0, 0, 0, 0)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
RDebugUtils.currentLine=14811174;
 //BA.debugLineNum = 14811174;BA.debugLine="mBase.AddView(pnlMinus, 0, 0, 0, 0)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
RDebugUtils.currentLine=14811175;
 //BA.debugLineNum = 14811175;BA.debugLine="pnlPlus.AddView(lblPlus, 0, 0, 0, 0)";
__ref._pnlplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
RDebugUtils.currentLine=14811176;
 //BA.debugLineNum = 14811176;BA.debugLine="pnlMinus.AddView(lblMinus, 0, 0, 0, 0)";
__ref._pnlminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._lblminus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
RDebugUtils.currentLine=14811177;
 //BA.debugLineNum = 14811177;BA.debugLine="SetIndex(-1, False)";
__ref._setindex /*String*/ (null,(int) (-1),__c.False);
RDebugUtils.currentLine=14811178;
 //BA.debugLineNum = 14811178;BA.debugLine="Base_Resize(mBase.Width, mBase.Height)";
__ref._base_resize /*String*/ (null,__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=14811179;
 //BA.debugLineNum = 14811179;BA.debugLine="End Sub";
return "";
}
public Object  _getselectedvalue(b4a.example.b4xplusminus __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "getselectedvalue", true))
	 {return ((Object) Debug.delegate(ba, "getselectedvalue", null));}
RDebugUtils.currentLine=15073280;
 //BA.debugLineNum = 15073280;BA.debugLine="Public Sub getSelectedValue As Object";
RDebugUtils.currentLine=15073281;
 //BA.debugLineNum = 15073281;BA.debugLine="Return GetValueImpl (mSelectedIndex)";
if (true) return __ref._getvalueimpl /*Object*/ (null,__ref._mselectedindex /*int*/ );
RDebugUtils.currentLine=15073282;
 //BA.debugLineNum = 15073282;BA.debugLine="End Sub";
return null;
}
public Object  _getvalueimpl(b4a.example.b4xplusminus __ref,int _index) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "getvalueimpl", true))
	 {return ((Object) Debug.delegate(ba, "getvalueimpl", new Object[] {_index}));}
RDebugUtils.currentLine=15204352;
 //BA.debugLineNum = 15204352;BA.debugLine="Private Sub GetValueImpl (Index As Int) As Object";
RDebugUtils.currentLine=15204353;
 //BA.debugLineNum = 15204353;BA.debugLine="If StringMode Then";
if (__ref._stringmode /*boolean*/ ) { 
RDebugUtils.currentLine=15204354;
 //BA.debugLineNum = 15204354;BA.debugLine="Return mStringItems.Get(Index)";
if (true) return __ref._mstringitems /*anywheresoftware.b4a.objects.collections.List*/ .Get(_index);
 }else {
RDebugUtils.currentLine=15204356;
 //BA.debugLineNum = 15204356;BA.debugLine="If Index = Size - 1 Then";
if (_index==__ref._size /*int*/ -1) { 
RDebugUtils.currentLine=15204357;
 //BA.debugLineNum = 15204357;BA.debugLine="Return mEndRange";
if (true) return (Object)(__ref._mendrange /*double*/ );
 }else {
RDebugUtils.currentLine=15204359;
 //BA.debugLineNum = 15204359;BA.debugLine="Return mStartRange + Index * mInterval";
if (true) return (Object)(__ref._mstartrange /*double*/ +_index*__ref._minterval /*double*/ );
 };
 };
RDebugUtils.currentLine=15204362;
 //BA.debugLineNum = 15204362;BA.debugLine="End Sub";
return null;
}
public String  _increment(b4a.example.b4xplusminus __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "increment", true))
	 {return ((String) Debug.delegate(ba, "increment", null));}
double _v1 = 0;
double _v2 = 0;
RDebugUtils.currentLine=15335424;
 //BA.debugLineNum = 15335424;BA.debugLine="Public Sub Increment";
RDebugUtils.currentLine=15335425;
 //BA.debugLineNum = 15335425;BA.debugLine="If mSelectedIndex = Size - 3 And StringMode = Fal";
if (__ref._mselectedindex /*int*/ ==__ref._size /*int*/ -3 && __ref._stringmode /*boolean*/ ==__c.False) { 
RDebugUtils.currentLine=15335426;
 //BA.debugLineNum = 15335426;BA.debugLine="Dim v1 As Double = GetValueImpl(mSelectedIndex +";
_v1 = (double)(BA.ObjectToNumber(__ref._getvalueimpl /*Object*/ (null,(int) (__ref._mselectedindex /*int*/ +1))));
RDebugUtils.currentLine=15335427;
 //BA.debugLineNum = 15335427;BA.debugLine="Dim v2 As Double = GetValueImpl(mSelectedIndex +";
_v2 = (double)(BA.ObjectToNumber(__ref._getvalueimpl /*Object*/ (null,(int) (__ref._mselectedindex /*int*/ +2))));
RDebugUtils.currentLine=15335428;
 //BA.debugLineNum = 15335428;BA.debugLine="If Formatter.Format(v1) = Formatter.Format(v2) T";
if ((__ref._formatter /*b4a.example.b4xformatter*/ ._format /*String*/ (null,_v1)).equals(__ref._formatter /*b4a.example.b4xformatter*/ ._format /*String*/ (null,_v2))) { 
RDebugUtils.currentLine=15335429;
 //BA.debugLineNum = 15335429;BA.debugLine="mSelectedIndex = mSelectedIndex + 1";
__ref._mselectedindex /*int*/  = (int) (__ref._mselectedindex /*int*/ +1);
 };
 };
RDebugUtils.currentLine=15335432;
 //BA.debugLineNum = 15335432;BA.debugLine="If mSelectedIndex >= Size - 1 Then";
if (__ref._mselectedindex /*int*/ >=__ref._size /*int*/ -1) { 
RDebugUtils.currentLine=15335433;
 //BA.debugLineNum = 15335433;BA.debugLine="If mCyclic Then";
if (__ref._mcyclic /*boolean*/ ) { 
RDebugUtils.currentLine=15335434;
 //BA.debugLineNum = 15335434;BA.debugLine="mSelectedIndex = -1";
__ref._mselectedindex /*int*/  = (int) (-1);
 }else {
RDebugUtils.currentLine=15335436;
 //BA.debugLineNum = 15335436;BA.debugLine="mSelectedIndex = Size - 1";
__ref._mselectedindex /*int*/  = (int) (__ref._size /*int*/ -1);
RDebugUtils.currentLine=15335437;
 //BA.debugLineNum = 15335437;BA.debugLine="LoopIndex = LoopIndex + 1";
__ref._loopindex /*int*/  = (int) (__ref._loopindex /*int*/ +1);
RDebugUtils.currentLine=15335438;
 //BA.debugLineNum = 15335438;BA.debugLine="Return";
if (true) return "";
 };
 };
RDebugUtils.currentLine=15335441;
 //BA.debugLineNum = 15335441;BA.debugLine="SetIndex(mSelectedIndex + 1, True)";
__ref._setindex /*String*/ (null,(int) (__ref._mselectedindex /*int*/ +1),__c.True);
RDebugUtils.currentLine=15335442;
 //BA.debugLineNum = 15335442;BA.debugLine="End Sub";
return "";
}
public String  _initialize(b4a.example.b4xplusminus __ref,anywheresoftware.b4a.BA _ba,Object _callback,String _eventname) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_callback,_eventname}));}
RDebugUtils.currentLine=14745600;
 //BA.debugLineNum = 14745600;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
RDebugUtils.currentLine=14745601;
 //BA.debugLineNum = 14745601;BA.debugLine="mEventName = EventName";
__ref._meventname /*String*/  = _eventname;
RDebugUtils.currentLine=14745602;
 //BA.debugLineNum = 14745602;BA.debugLine="mCallBack = Callback";
__ref._mcallback /*Object*/  = _callback;
RDebugUtils.currentLine=14745603;
 //BA.debugLineNum = 14745603;BA.debugLine="Formatter.Initialize";
__ref._formatter /*b4a.example.b4xformatter*/ ._initialize /*String*/ (null,ba);
RDebugUtils.currentLine=14745604;
 //BA.debugLineNum = 14745604;BA.debugLine="End Sub";
return "";
}
public String  _internalsettextorcsbuildertolabel(b4a.example.b4xplusminus __ref,anywheresoftware.b4a.objects.B4XViewWrapper _xlbl,Object _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "internalsettextorcsbuildertolabel", true))
	 {return ((String) Debug.delegate(ba, "internalsettextorcsbuildertolabel", new Object[] {_xlbl,_text}));}
RDebugUtils.currentLine=15728640;
 //BA.debugLineNum = 15728640;BA.debugLine="Private Sub InternalSetTextOrCSBuilderToLabel(xlbl";
RDebugUtils.currentLine=15728642;
 //BA.debugLineNum = 15728642;BA.debugLine="xlbl.Text = Text";
_xlbl.setText(BA.ObjectToCharSequence(_text));
RDebugUtils.currentLine=15728652;
 //BA.debugLineNum = 15728652;BA.debugLine="End Sub";
return "";
}
public String  _pnlarrow_touch(b4a.example.b4xplusminus __ref,int _action,float _x,float _y) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "pnlarrow_touch", true))
	 {return ((String) Debug.delegate(ba, "pnlarrow_touch", new Object[] {_action,_x,_y}));}
RDebugUtils.currentLine=15466496;
 //BA.debugLineNum = 15466496;BA.debugLine="Private Sub pnlArrow_Touch (Action As Int, X As Fl";
RDebugUtils.currentLine=15466497;
 //BA.debugLineNum = 15466497;BA.debugLine="Touch(Action = lblPlus.TOUCH_ACTION_DOWN, Action";
__ref._touch /*String*/ (null,_action==__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_DOWN,_action!=__ref._lblplus /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_MOVE,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(__c.Sender(ba))));
RDebugUtils.currentLine=15466498;
 //BA.debugLineNum = 15466498;BA.debugLine="End Sub";
return "";
}
public String  _touch(b4a.example.b4xplusminus __ref,boolean _start,boolean _stop,anywheresoftware.b4a.objects.B4XViewWrapper _pnl) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "touch", true))
	 {return ((String) Debug.delegate(ba, "touch", new Object[] {_start,_stop,_pnl}));}
RDebugUtils.currentLine=15532032;
 //BA.debugLineNum = 15532032;BA.debugLine="Private Sub Touch (Start As Boolean, Stop As Boole";
RDebugUtils.currentLine=15532033;
 //BA.debugLineNum = 15532033;BA.debugLine="If pnl.GetView(0).Enabled = False Then Return";
if (_pnl.GetView((int) (0)).getEnabled()==__c.False) { 
if (true) return "";};
RDebugUtils.currentLine=15532034;
 //BA.debugLineNum = 15532034;BA.debugLine="If Start Then";
if (_start) { 
RDebugUtils.currentLine=15532035;
 //BA.debugLineNum = 15532035;BA.debugLine="If mHaptic Then XUIViewsUtils.PerformHapticFeedb";
if (__ref._mhaptic /*boolean*/ ) { 
_xuiviewsutils._performhapticfeedback /*String*/ (ba,_pnl);};
RDebugUtils.currentLine=15532036;
 //BA.debugLineNum = 15532036;BA.debugLine="If pnl.Tag = True Then";
if ((_pnl.getTag()).equals((Object)(__c.True))) { 
RDebugUtils.currentLine=15532037;
 //BA.debugLineNum = 15532037;BA.debugLine="Increment";
__ref._increment /*String*/ (null);
RDebugUtils.currentLine=15532038;
 //BA.debugLineNum = 15532038;BA.debugLine="StartDownLoop(True)";
__ref._startdownloop /*void*/ (null,__c.True);
 }else {
RDebugUtils.currentLine=15532040;
 //BA.debugLineNum = 15532040;BA.debugLine="Decrement";
__ref._decrement /*String*/ (null);
RDebugUtils.currentLine=15532041;
 //BA.debugLineNum = 15532041;BA.debugLine="StartDownLoop(False)";
__ref._startdownloop /*void*/ (null,__c.False);
 };
 }else 
{RDebugUtils.currentLine=15532043;
 //BA.debugLineNum = 15532043;BA.debugLine="Else If Stop Then";
if (_stop) { 
RDebugUtils.currentLine=15532044;
 //BA.debugLineNum = 15532044;BA.debugLine="LoopIndex = LoopIndex + 1";
__ref._loopindex /*int*/  = (int) (__ref._loopindex /*int*/ +1);
 }}
;
RDebugUtils.currentLine=15532046;
 //BA.debugLineNum = 15532046;BA.debugLine="End Sub";
return "";
}
public String  _setnumericrange(b4a.example.b4xplusminus __ref,double _startrange,double _endrange,double _interval) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "setnumericrange", true))
	 {return ((String) Debug.delegate(ba, "setnumericrange", new Object[] {_startrange,_endrange,_interval}));}
RDebugUtils.currentLine=15007744;
 //BA.debugLineNum = 15007744;BA.debugLine="Public Sub SetNumericRange (StartRange As Double,";
RDebugUtils.currentLine=15007745;
 //BA.debugLineNum = 15007745;BA.debugLine="Size = Ceil((EndRange - StartRange) / Interval) +";
__ref._size /*int*/  = (int) (__c.Ceil((_endrange-_startrange)/(double)_interval)+1);
RDebugUtils.currentLine=15007746;
 //BA.debugLineNum = 15007746;BA.debugLine="mStartRange = StartRange";
__ref._mstartrange /*double*/  = _startrange;
RDebugUtils.currentLine=15007747;
 //BA.debugLineNum = 15007747;BA.debugLine="mEndRange = EndRange";
__ref._mendrange /*double*/  = _endrange;
RDebugUtils.currentLine=15007748;
 //BA.debugLineNum = 15007748;BA.debugLine="mInterval = Interval";
__ref._minterval /*double*/  = _interval;
RDebugUtils.currentLine=15007749;
 //BA.debugLineNum = 15007749;BA.debugLine="StringMode = False";
__ref._stringmode /*boolean*/  = __c.False;
RDebugUtils.currentLine=15007750;
 //BA.debugLineNum = 15007750;BA.debugLine="SetIndex(0, False)";
__ref._setindex /*String*/ (null,(int) (0),__c.False);
RDebugUtils.currentLine=15007751;
 //BA.debugLineNum = 15007751;BA.debugLine="End Sub";
return "";
}
public String  _setselectedvalue(b4a.example.b4xplusminus __ref,Object _v) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "setselectedvalue", true))
	 {return ((String) Debug.delegate(ba, "setselectedvalue", new Object[] {_v}));}
double _n = 0;
RDebugUtils.currentLine=15138816;
 //BA.debugLineNum = 15138816;BA.debugLine="Public Sub setSelectedValue (v As Object)";
RDebugUtils.currentLine=15138817;
 //BA.debugLineNum = 15138817;BA.debugLine="If StringMode Then";
if (__ref._stringmode /*boolean*/ ) { 
RDebugUtils.currentLine=15138818;
 //BA.debugLineNum = 15138818;BA.debugLine="SetIndex(mStringItems.IndexOf(v), False)";
__ref._setindex /*String*/ (null,__ref._mstringitems /*anywheresoftware.b4a.objects.collections.List*/ .IndexOf(_v),__c.False);
 }else {
RDebugUtils.currentLine=15138820;
 //BA.debugLineNum = 15138820;BA.debugLine="Dim n As Double = v";
_n = (double)(BA.ObjectToNumber(_v));
RDebugUtils.currentLine=15138821;
 //BA.debugLineNum = 15138821;BA.debugLine="SetIndex(Min(Size - 1, Max(0, Round((n - mStartR";
__ref._setindex /*String*/ (null,(int) (__c.Min(__ref._size /*int*/ -1,__c.Max(0,__c.Round((_n-__ref._mstartrange /*double*/ )/(double)__ref._minterval /*double*/ )))),__c.False);
 };
RDebugUtils.currentLine=15138823;
 //BA.debugLineNum = 15138823;BA.debugLine="End Sub";
return "";
}
public String  _setstringitems(b4a.example.b4xplusminus __ref,anywheresoftware.b4a.objects.collections.List _list) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "setstringitems", true))
	 {return ((String) Debug.delegate(ba, "setstringitems", new Object[] {_list}));}
RDebugUtils.currentLine=14942208;
 //BA.debugLineNum = 14942208;BA.debugLine="Public Sub SetStringItems (list As List)";
RDebugUtils.currentLine=14942209;
 //BA.debugLineNum = 14942209;BA.debugLine="mStringItems = list";
__ref._mstringitems /*anywheresoftware.b4a.objects.collections.List*/  = _list;
RDebugUtils.currentLine=14942210;
 //BA.debugLineNum = 14942210;BA.debugLine="StringMode = True";
__ref._stringmode /*boolean*/  = __c.True;
RDebugUtils.currentLine=14942211;
 //BA.debugLineNum = 14942211;BA.debugLine="Size = mStringItems.Size";
__ref._size /*int*/  = __ref._mstringitems /*anywheresoftware.b4a.objects.collections.List*/ .getSize();
RDebugUtils.currentLine=14942212;
 //BA.debugLineNum = 14942212;BA.debugLine="SetIndex(-1, False)";
__ref._setindex /*String*/ (null,(int) (-1),__c.False);
RDebugUtils.currentLine=14942213;
 //BA.debugLineNum = 14942213;BA.debugLine="End Sub";
return "";
}
public void  _startdownloop(b4a.example.b4xplusminus __ref,boolean _up) throws Exception{
RDebugUtils.currentModule="b4xplusminus";
if (Debug.shouldDelegate(ba, "startdownloop", true))
	 {Debug.delegate(ba, "startdownloop", new Object[] {_up}); return;}
ResumableSub_StartDownLoop rsub = new ResumableSub_StartDownLoop(this,__ref,_up);
rsub.resume(ba, null);
}
public static class ResumableSub_StartDownLoop extends BA.ResumableSub {
public ResumableSub_StartDownLoop(b4a.example.b4xplusminus parent,b4a.example.b4xplusminus __ref,boolean _up) {
this.parent = parent;
this.__ref = __ref;
this._up = _up;
this.__ref = parent;
}
b4a.example.b4xplusminus __ref;
b4a.example.b4xplusminus parent;
boolean _up;
int _myindex = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="b4xplusminus";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=15597569;
 //BA.debugLineNum = 15597569;BA.debugLine="LoopIndex = LoopIndex + 1";
__ref._loopindex /*int*/  = (int) (__ref._loopindex /*int*/ +1);
RDebugUtils.currentLine=15597570;
 //BA.debugLineNum = 15597570;BA.debugLine="If mRapid = False Then Return";
if (true) break;

case 1:
//if
this.state = 6;
if (__ref._mrapid /*boolean*/ ==parent.__c.False) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
RDebugUtils.currentLine=15597571;
 //BA.debugLineNum = 15597571;BA.debugLine="Dim MyIndex As Int = LoopIndex";
_myindex = __ref._loopindex /*int*/ ;
RDebugUtils.currentLine=15597572;
 //BA.debugLineNum = 15597572;BA.debugLine="Sleep(RapidPeriod1)";
parent.__c.Sleep(ba,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "b4xplusminus", "startdownloop"),__ref._rapidperiod1 /*int*/ );
this.state = 19;
return;
case 19:
//C
this.state = 7;
;
RDebugUtils.currentLine=15597573;
 //BA.debugLineNum = 15597573;BA.debugLine="Do While MyIndex = LoopIndex";
if (true) break;

case 7:
//do while
this.state = 18;
while (_myindex==__ref._loopindex /*int*/ ) {
this.state = 9;
if (true) break;
}
if (true) break;

case 9:
//C
this.state = 10;
RDebugUtils.currentLine=15597574;
 //BA.debugLineNum = 15597574;BA.debugLine="If Up Then Increment Else Decrement";
if (true) break;

case 10:
//if
this.state = 17;
if (_up) { 
this.state = 12;
;}
else {
this.state = 14;
;}if (true) break;

case 12:
//C
this.state = 17;
__ref._increment /*String*/ (null);
if (true) break;

case 14:
//C
this.state = 17;
__ref._decrement /*String*/ (null);
if (true) break;

case 17:
//C
this.state = 7;
;
RDebugUtils.currentLine=15597575;
 //BA.debugLineNum = 15597575;BA.debugLine="Sleep(RapidPeriod2)";
parent.__c.Sleep(ba,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "b4xplusminus", "startdownloop"),__ref._rapidperiod2 /*int*/ );
this.state = 20;
return;
case 20:
//C
this.state = 7;
;
 if (true) break;

case 18:
//C
this.state = -1;
;
RDebugUtils.currentLine=15597577;
 //BA.debugLineNum = 15597577;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
}