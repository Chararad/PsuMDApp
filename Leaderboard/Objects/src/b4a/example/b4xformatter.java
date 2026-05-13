package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xformatter extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.b4xformatter");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xformatter.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public static class _b4xformatdata{
public boolean IsInitialized;
public String Prefix;
public String Postfix;
public int MinimumIntegers;
public int MinimumFractions;
public int MaximumFractions;
public String GroupingCharacter;
public String DecimalPoint;
public int TextColor;
public anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont FormatFont;
public double RangeStart;
public double RangeEnd;
public boolean RemoveMinusSign;
public String IntegerPaddingChar;
public String FractionPaddingChar;
public void Initialize() {
IsInitialized = true;
Prefix = "";
Postfix = "";
MinimumIntegers = 0;
MinimumFractions = 0;
MaximumFractions = 0;
GroupingCharacter = "";
DecimalPoint = "";
TextColor = 0;
FormatFont = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont();
RangeStart = 0;
RangeEnd = 0;
RemoveMinusSign = false;
IntegerPaddingChar = "";
FractionPaddingChar = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.collections.List _formats = null;
public int _max_value = 0;
public int _min_value = 0;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public b4a.example.b4xformatter._b4xformatdata  _getdefaultformat(b4a.example.b4xformatter __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "getdefaultformat", true))
	 {return ((b4a.example.b4xformatter._b4xformatdata) Debug.delegate(ba, "getdefaultformat", null));}
RDebugUtils.currentLine=20971520;
 //BA.debugLineNum = 20971520;BA.debugLine="Public Sub GetDefaultFormat As B4XFormatData";
RDebugUtils.currentLine=20971521;
 //BA.debugLineNum = 20971521;BA.debugLine="Return formats.Get(0)";
if (true) return (b4a.example.b4xformatter._b4xformatdata)(__ref._formats /*anywheresoftware.b4a.objects.collections.List*/ .Get((int) (0)));
RDebugUtils.currentLine=20971522;
 //BA.debugLineNum = 20971522;BA.debugLine="End Sub";
return null;
}
public String  _format(b4a.example.b4xformatter __ref,double _number) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "format", true))
	 {return ((String) Debug.delegate(ba, "format", new Object[] {_number}));}
b4a.example.b4xformatter._b4xformatdata _data = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _numberstartindex = 0;
double _factor = 0;
int _whole = 0;
double _frac = 0;
int _g = 0;
int _fracstartindex = 0;
int _lastzerocount = 0;
int _multipler = 0;
int _w = 0;
RDebugUtils.currentLine=21102592;
 //BA.debugLineNum = 21102592;BA.debugLine="Public Sub Format (Number As Double) As String";
RDebugUtils.currentLine=21102593;
 //BA.debugLineNum = 21102593;BA.debugLine="If Number < MIN_VALUE Or Number > MAX_VALUE Then";
if (_number<__ref._min_value /*int*/  || _number>__ref._max_value /*int*/ ) { 
if (true) return "OVERFLOW";};
RDebugUtils.currentLine=21102594;
 //BA.debugLineNum = 21102594;BA.debugLine="Dim data As B4XFormatData = GetFormatData (Number";
_data = __ref._getformatdata /*b4a.example.b4xformatter._b4xformatdata*/ (null,_number);
RDebugUtils.currentLine=21102595;
 //BA.debugLineNum = 21102595;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
RDebugUtils.currentLine=21102596;
 //BA.debugLineNum = 21102596;BA.debugLine="sb.Initialize";
_sb.Initialize();
RDebugUtils.currentLine=21102597;
 //BA.debugLineNum = 21102597;BA.debugLine="sb.Append(data.Prefix)";
_sb.Append(_data.Prefix /*String*/ );
RDebugUtils.currentLine=21102598;
 //BA.debugLineNum = 21102598;BA.debugLine="Dim NumberStartIndex As Int = sb.Length";
_numberstartindex = _sb.getLength();
RDebugUtils.currentLine=21102599;
 //BA.debugLineNum = 21102599;BA.debugLine="Dim factor As Double = Power(10, -data.MaximumFra";
_factor = __c.Power(10,-_data.MaximumFractions /*int*/ -1)*5;
RDebugUtils.currentLine=21102600;
 //BA.debugLineNum = 21102600;BA.debugLine="If Number < -factor And data.RemoveMinusSign = Fa";
if (_number<-_factor && _data.RemoveMinusSign /*boolean*/ ==__c.False) { 
RDebugUtils.currentLine=21102601;
 //BA.debugLineNum = 21102601;BA.debugLine="sb.Append(\"-\")";
_sb.Append("-");
RDebugUtils.currentLine=21102602;
 //BA.debugLineNum = 21102602;BA.debugLine="NumberStartIndex = NumberStartIndex + 1";
_numberstartindex = (int) (_numberstartindex+1);
 };
RDebugUtils.currentLine=21102604;
 //BA.debugLineNum = 21102604;BA.debugLine="Number = Abs(Number) + factor";
_number = __c.Abs(_number)+_factor;
RDebugUtils.currentLine=21102605;
 //BA.debugLineNum = 21102605;BA.debugLine="Dim whole As Int = Number";
_whole = (int) (_number);
RDebugUtils.currentLine=21102606;
 //BA.debugLineNum = 21102606;BA.debugLine="Dim frac As Double = Number - whole";
_frac = _number-_whole;
RDebugUtils.currentLine=21102607;
 //BA.debugLineNum = 21102607;BA.debugLine="Dim g As Int";
_g = 0;
RDebugUtils.currentLine=21102608;
 //BA.debugLineNum = 21102608;BA.debugLine="Do While whole > 0";
while (_whole>0) {
RDebugUtils.currentLine=21102609;
 //BA.debugLineNum = 21102609;BA.debugLine="If g > 0 And g Mod 3 = 0 And data.GroupingCharac";
if (_g>0 && _g%3==0 && _data.GroupingCharacter /*String*/ .length()>0) { 
RDebugUtils.currentLine=21102610;
 //BA.debugLineNum = 21102610;BA.debugLine="sb.Insert(NumberStartIndex, data.GroupingCharac";
_sb.Insert(_numberstartindex,_data.GroupingCharacter /*String*/ );
 };
RDebugUtils.currentLine=21102612;
 //BA.debugLineNum = 21102612;BA.debugLine="g = g + 1";
_g = (int) (_g+1);
RDebugUtils.currentLine=21102613;
 //BA.debugLineNum = 21102613;BA.debugLine="sb.Insert(NumberStartIndex, whole Mod 10)";
_sb.Insert(_numberstartindex,BA.NumberToString(_whole%10));
RDebugUtils.currentLine=21102614;
 //BA.debugLineNum = 21102614;BA.debugLine="whole = whole / 10";
_whole = (int) (_whole/(double)10);
 }
;
RDebugUtils.currentLine=21102616;
 //BA.debugLineNum = 21102616;BA.debugLine="Do While sb.Length - NumberStartIndex < data.Mini";
while (_sb.getLength()-_numberstartindex<_data.MinimumIntegers /*int*/ ) {
RDebugUtils.currentLine=21102617;
 //BA.debugLineNum = 21102617;BA.debugLine="sb.Insert(NumberStartIndex, data.IntegerPaddingC";
_sb.Insert(_numberstartindex,_data.IntegerPaddingChar /*String*/ );
 }
;
RDebugUtils.currentLine=21102619;
 //BA.debugLineNum = 21102619;BA.debugLine="If data.MaximumFractions > 0 And (data.MinimumFra";
if (_data.MaximumFractions /*int*/ >0 && (_data.MinimumFractions /*int*/ >0 || _frac>0)) { 
RDebugUtils.currentLine=21102620;
 //BA.debugLineNum = 21102620;BA.debugLine="Dim FracStartIndex As Int = sb.Length";
_fracstartindex = _sb.getLength();
RDebugUtils.currentLine=21102621;
 //BA.debugLineNum = 21102621;BA.debugLine="Dim LastZeroCount As Int";
_lastzerocount = 0;
RDebugUtils.currentLine=21102622;
 //BA.debugLineNum = 21102622;BA.debugLine="Dim Multipler As Int = 10";
_multipler = (int) (10);
RDebugUtils.currentLine=21102623;
 //BA.debugLineNum = 21102623;BA.debugLine="Do While frac >= 2 * factor And sb.Length - Frac";
while (_frac>=2*_factor && _sb.getLength()-_fracstartindex<_data.MaximumFractions /*int*/ ) {
RDebugUtils.currentLine=21102624;
 //BA.debugLineNum = 21102624;BA.debugLine="Dim w As Int = (frac * Multipler)";
_w = (int) ((_frac*_multipler));
RDebugUtils.currentLine=21102625;
 //BA.debugLineNum = 21102625;BA.debugLine="w = w Mod 10";
_w = (int) (_w%10);
RDebugUtils.currentLine=21102626;
 //BA.debugLineNum = 21102626;BA.debugLine="If w = 0 Then LastZeroCount = LastZeroCount + 1";
if (_w==0) { 
_lastzerocount = (int) (_lastzerocount+1);}
else {
_lastzerocount = (int) (0);};
RDebugUtils.currentLine=21102627;
 //BA.debugLineNum = 21102627;BA.debugLine="sb.Append(w)";
_sb.Append(BA.NumberToString(_w));
RDebugUtils.currentLine=21102628;
 //BA.debugLineNum = 21102628;BA.debugLine="Multipler = Multipler * 10";
_multipler = (int) (_multipler*10);
 }
;
RDebugUtils.currentLine=21102630;
 //BA.debugLineNum = 21102630;BA.debugLine="If data.FractionPaddingChar <> \"0\" And LastZeroC";
if ((_data.FractionPaddingChar /*String*/ ).equals("0") == false && _lastzerocount>0) { 
RDebugUtils.currentLine=21102631;
 //BA.debugLineNum = 21102631;BA.debugLine="sb.Remove(sb.Length - LastZeroCount, sb.Length)";
_sb.Remove((int) (_sb.getLength()-_lastzerocount),_sb.getLength());
RDebugUtils.currentLine=21102632;
 //BA.debugLineNum = 21102632;BA.debugLine="LastZeroCount = 0";
_lastzerocount = (int) (0);
 };
RDebugUtils.currentLine=21102634;
 //BA.debugLineNum = 21102634;BA.debugLine="Do While sb.Length - FracStartIndex < data.Minim";
while (_sb.getLength()-_fracstartindex<_data.MinimumFractions /*int*/ ) {
RDebugUtils.currentLine=21102635;
 //BA.debugLineNum = 21102635;BA.debugLine="sb.Append(data.FractionPaddingChar)";
_sb.Append(_data.FractionPaddingChar /*String*/ );
RDebugUtils.currentLine=21102636;
 //BA.debugLineNum = 21102636;BA.debugLine="LastZeroCount = 0";
_lastzerocount = (int) (0);
 }
;
RDebugUtils.currentLine=21102638;
 //BA.debugLineNum = 21102638;BA.debugLine="LastZeroCount = Min(LastZeroCount, sb.Length - F";
_lastzerocount = (int) (__c.Min(_lastzerocount,_sb.getLength()-_fracstartindex-_data.MinimumFractions /*int*/ ));
RDebugUtils.currentLine=21102639;
 //BA.debugLineNum = 21102639;BA.debugLine="If LastZeroCount > 0 Then";
if (_lastzerocount>0) { 
RDebugUtils.currentLine=21102640;
 //BA.debugLineNum = 21102640;BA.debugLine="sb.Remove(sb.Length - LastZeroCount, sb.Length)";
_sb.Remove((int) (_sb.getLength()-_lastzerocount),_sb.getLength());
 };
RDebugUtils.currentLine=21102642;
 //BA.debugLineNum = 21102642;BA.debugLine="If sb.Length > FracStartIndex Then sb.Insert(Fra";
if (_sb.getLength()>_fracstartindex) { 
_sb.Insert(_fracstartindex,_data.DecimalPoint /*String*/ );};
 };
RDebugUtils.currentLine=21102644;
 //BA.debugLineNum = 21102644;BA.debugLine="sb.Append(data.Postfix)";
_sb.Append(_data.Postfix /*String*/ );
RDebugUtils.currentLine=21102645;
 //BA.debugLineNum = 21102645;BA.debugLine="Return sb.ToString";
if (true) return _sb.ToString();
RDebugUtils.currentLine=21102646;
 //BA.debugLineNum = 21102646;BA.debugLine="End Sub";
return "";
}
public String  _initialize(b4a.example.b4xformatter __ref,anywheresoftware.b4a.BA _ba) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba}));}
b4a.example.b4xformatter._b4xformatdata _d = null;
RDebugUtils.currentLine=20643840;
 //BA.debugLineNum = 20643840;BA.debugLine="Public Sub Initialize";
RDebugUtils.currentLine=20643841;
 //BA.debugLineNum = 20643841;BA.debugLine="formats.Initialize";
__ref._formats /*anywheresoftware.b4a.objects.collections.List*/ .Initialize();
RDebugUtils.currentLine=20643842;
 //BA.debugLineNum = 20643842;BA.debugLine="Dim d As B4XFormatData = CreateDefaultFormat";
_d = __ref._createdefaultformat /*b4a.example.b4xformatter._b4xformatdata*/ (null);
RDebugUtils.currentLine=20643843;
 //BA.debugLineNum = 20643843;BA.debugLine="AddFormatData(d, MIN_VALUE, MAX_VALUE, True)";
__ref._addformatdata /*String*/ (null,_d,__ref._min_value /*int*/ ,__ref._max_value /*int*/ ,__c.True);
RDebugUtils.currentLine=20643844;
 //BA.debugLineNum = 20643844;BA.debugLine="End Sub";
return "";
}
public String  _addformatdata(b4a.example.b4xformatter __ref,b4a.example.b4xformatter._b4xformatdata _data,double _rangestart,double _rangeend,boolean _includeedges) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "addformatdata", true))
	 {return ((String) Debug.delegate(ba, "addformatdata", new Object[] {_data,_rangestart,_rangeend,_includeedges}));}
double _factor = 0;
RDebugUtils.currentLine=20905984;
 //BA.debugLineNum = 20905984;BA.debugLine="Public Sub AddFormatData (Data As B4XFormatData, R";
RDebugUtils.currentLine=20905985;
 //BA.debugLineNum = 20905985;BA.debugLine="Dim factor As Double = Power(10, -Data.MaximumFra";
_factor = __c.Power(10,-_data.MaximumFractions /*int*/ );
RDebugUtils.currentLine=20905986;
 //BA.debugLineNum = 20905986;BA.debugLine="If IncludeEdges = False Then";
if (_includeedges==__c.False) { 
RDebugUtils.currentLine=20905987;
 //BA.debugLineNum = 20905987;BA.debugLine="RangeStart = RangeStart + factor";
_rangestart = _rangestart+_factor;
RDebugUtils.currentLine=20905988;
 //BA.debugLineNum = 20905988;BA.debugLine="RangeEnd = RangeEnd - factor";
_rangeend = _rangeend-_factor;
 };
RDebugUtils.currentLine=20905990;
 //BA.debugLineNum = 20905990;BA.debugLine="RangeStart = RangeStart - factor / 2";
_rangestart = _rangestart-_factor/(double)2;
RDebugUtils.currentLine=20905991;
 //BA.debugLineNum = 20905991;BA.debugLine="RangeEnd = RangeEnd + factor / 2";
_rangeend = _rangeend+_factor/(double)2;
RDebugUtils.currentLine=20905992;
 //BA.debugLineNum = 20905992;BA.debugLine="Data.RangeStart = RangeStart";
_data.RangeStart /*double*/  = _rangestart;
RDebugUtils.currentLine=20905993;
 //BA.debugLineNum = 20905993;BA.debugLine="Data.RangeEnd = RangeEnd";
_data.RangeEnd /*double*/  = _rangeend;
RDebugUtils.currentLine=20905994;
 //BA.debugLineNum = 20905994;BA.debugLine="formats.Add(Data)";
__ref._formats /*anywheresoftware.b4a.objects.collections.List*/ .Add((Object)(_data));
RDebugUtils.currentLine=20905995;
 //BA.debugLineNum = 20905995;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.b4xformatter __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
RDebugUtils.currentLine=20578304;
 //BA.debugLineNum = 20578304;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=20578305;
 //BA.debugLineNum = 20578305;BA.debugLine="Type B4XFormatData (Prefix As String, Postfix As";
;
RDebugUtils.currentLine=20578309;
 //BA.debugLineNum = 20578309;BA.debugLine="Private formats As List";
_formats = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=20578310;
 //BA.debugLineNum = 20578310;BA.debugLine="Public Const MAX_VALUE = 0x7fffffff, MIN_VALUE =";
_max_value = ((int)0x7fffffff);
_min_value = ((int)0x80000000);
RDebugUtils.currentLine=20578312;
 //BA.debugLineNum = 20578312;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=20578314;
 //BA.debugLineNum = 20578314;BA.debugLine="End Sub";
return "";
}
public b4a.example.b4xformatter._b4xformatdata  _copyformatdata(b4a.example.b4xformatter __ref,b4a.example.b4xformatter._b4xformatdata _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "copyformatdata", true))
	 {return ((b4a.example.b4xformatter._b4xformatdata) Debug.delegate(ba, "copyformatdata", new Object[] {_data}));}
b4a.example.b4xformatter._b4xformatdata _d = null;
RDebugUtils.currentLine=20840448;
 //BA.debugLineNum = 20840448;BA.debugLine="Public Sub CopyFormatData (Data As B4XFormatData)";
RDebugUtils.currentLine=20840449;
 //BA.debugLineNum = 20840449;BA.debugLine="Dim d As B4XFormatData";
_d = new b4a.example.b4xformatter._b4xformatdata();
RDebugUtils.currentLine=20840450;
 //BA.debugLineNum = 20840450;BA.debugLine="d.Initialize";
_d.Initialize();
RDebugUtils.currentLine=20840451;
 //BA.debugLineNum = 20840451;BA.debugLine="d.DecimalPoint = Data.DecimalPoint";
_d.DecimalPoint /*String*/  = _data.DecimalPoint /*String*/ ;
RDebugUtils.currentLine=20840452;
 //BA.debugLineNum = 20840452;BA.debugLine="If Data.FormatFont.IsInitialized Then";
if (_data.FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ .getIsInitialized()) { 
RDebugUtils.currentLine=20840454;
 //BA.debugLineNum = 20840454;BA.debugLine="d.FormatFont = xui.CreateFont(Data.FormatFont.To";
_d.FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreateFont((android.graphics.Typeface)(_data.FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ .ToNativeFont().getObject()),_data.FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ .getSize());
 };
RDebugUtils.currentLine=20840457;
 //BA.debugLineNum = 20840457;BA.debugLine="d.GroupingCharacter = Data.GroupingCharacter";
_d.GroupingCharacter /*String*/  = _data.GroupingCharacter /*String*/ ;
RDebugUtils.currentLine=20840458;
 //BA.debugLineNum = 20840458;BA.debugLine="d.MaximumFractions = Data.MaximumFractions";
_d.MaximumFractions /*int*/  = _data.MaximumFractions /*int*/ ;
RDebugUtils.currentLine=20840459;
 //BA.debugLineNum = 20840459;BA.debugLine="d.MinimumFractions = Data.MinimumFractions";
_d.MinimumFractions /*int*/  = _data.MinimumFractions /*int*/ ;
RDebugUtils.currentLine=20840460;
 //BA.debugLineNum = 20840460;BA.debugLine="d.MinimumIntegers = Data.MinimumIntegers";
_d.MinimumIntegers /*int*/  = _data.MinimumIntegers /*int*/ ;
RDebugUtils.currentLine=20840461;
 //BA.debugLineNum = 20840461;BA.debugLine="d.Postfix = Data.Postfix";
_d.Postfix /*String*/  = _data.Postfix /*String*/ ;
RDebugUtils.currentLine=20840462;
 //BA.debugLineNum = 20840462;BA.debugLine="d.Prefix = Data.Prefix";
_d.Prefix /*String*/  = _data.Prefix /*String*/ ;
RDebugUtils.currentLine=20840463;
 //BA.debugLineNum = 20840463;BA.debugLine="d.RangeEnd = Data.RangeEnd";
_d.RangeEnd /*double*/  = _data.RangeEnd /*double*/ ;
RDebugUtils.currentLine=20840464;
 //BA.debugLineNum = 20840464;BA.debugLine="d.RangeStart = Data.RangeStart";
_d.RangeStart /*double*/  = _data.RangeStart /*double*/ ;
RDebugUtils.currentLine=20840465;
 //BA.debugLineNum = 20840465;BA.debugLine="d.RemoveMinusSign = Data.RemoveMinusSign";
_d.RemoveMinusSign /*boolean*/  = _data.RemoveMinusSign /*boolean*/ ;
RDebugUtils.currentLine=20840466;
 //BA.debugLineNum = 20840466;BA.debugLine="d.TextColor = Data.TextColor";
_d.TextColor /*int*/  = _data.TextColor /*int*/ ;
RDebugUtils.currentLine=20840467;
 //BA.debugLineNum = 20840467;BA.debugLine="d.FractionPaddingChar = Data.FractionPaddingChar";
_d.FractionPaddingChar /*String*/  = _data.FractionPaddingChar /*String*/ ;
RDebugUtils.currentLine=20840468;
 //BA.debugLineNum = 20840468;BA.debugLine="d.IntegerPaddingChar = Data.IntegerPaddingChar";
_d.IntegerPaddingChar /*String*/  = _data.IntegerPaddingChar /*String*/ ;
RDebugUtils.currentLine=20840469;
 //BA.debugLineNum = 20840469;BA.debugLine="Return d";
if (true) return _d;
RDebugUtils.currentLine=20840470;
 //BA.debugLineNum = 20840470;BA.debugLine="End Sub";
return null;
}
public b4a.example.b4xformatter._b4xformatdata  _createdefaultformat(b4a.example.b4xformatter __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "createdefaultformat", true))
	 {return ((b4a.example.b4xformatter._b4xformatdata) Debug.delegate(ba, "createdefaultformat", null));}
b4a.example.b4xformatter._b4xformatdata _d = null;
RDebugUtils.currentLine=20709376;
 //BA.debugLineNum = 20709376;BA.debugLine="Private Sub CreateDefaultFormat As B4XFormatData";
RDebugUtils.currentLine=20709377;
 //BA.debugLineNum = 20709377;BA.debugLine="Dim d As B4XFormatData";
_d = new b4a.example.b4xformatter._b4xformatdata();
RDebugUtils.currentLine=20709378;
 //BA.debugLineNum = 20709378;BA.debugLine="d.Initialize";
_d.Initialize();
RDebugUtils.currentLine=20709379;
 //BA.debugLineNum = 20709379;BA.debugLine="d.GroupingCharacter = \",\"";
_d.GroupingCharacter /*String*/  = ",";
RDebugUtils.currentLine=20709380;
 //BA.debugLineNum = 20709380;BA.debugLine="d.DecimalPoint = \".\"";
_d.DecimalPoint /*String*/  = ".";
RDebugUtils.currentLine=20709381;
 //BA.debugLineNum = 20709381;BA.debugLine="d.MaximumFractions = 3";
_d.MaximumFractions /*int*/  = (int) (3);
RDebugUtils.currentLine=20709382;
 //BA.debugLineNum = 20709382;BA.debugLine="d.MinimumIntegers = 1";
_d.MinimumIntegers /*int*/  = (int) (1);
RDebugUtils.currentLine=20709383;
 //BA.debugLineNum = 20709383;BA.debugLine="d.IntegerPaddingChar = \"0\"";
_d.IntegerPaddingChar /*String*/  = "0";
RDebugUtils.currentLine=20709384;
 //BA.debugLineNum = 20709384;BA.debugLine="d.FractionPaddingChar = \"0\"";
_d.FractionPaddingChar /*String*/  = "0";
RDebugUtils.currentLine=20709385;
 //BA.debugLineNum = 20709385;BA.debugLine="Return d";
if (true) return _d;
RDebugUtils.currentLine=20709386;
 //BA.debugLineNum = 20709386;BA.debugLine="End Sub";
return null;
}
public b4a.example.b4xformatter._b4xformatdata  _getformatdata(b4a.example.b4xformatter __ref,double _number) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "getformatdata", true))
	 {return ((b4a.example.b4xformatter._b4xformatdata) Debug.delegate(ba, "getformatdata", new Object[] {_number}));}
int _i = 0;
b4a.example.b4xformatter._b4xformatdata _d = null;
RDebugUtils.currentLine=21037056;
 //BA.debugLineNum = 21037056;BA.debugLine="Public Sub GetFormatData (Number As Double) As B4X";
RDebugUtils.currentLine=21037057;
 //BA.debugLineNum = 21037057;BA.debugLine="For i = formats.Size - 1 To 1 Step - 1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_i = (int) (__ref._formats /*anywheresoftware.b4a.objects.collections.List*/ .getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
RDebugUtils.currentLine=21037058;
 //BA.debugLineNum = 21037058;BA.debugLine="Dim d As B4XFormatData = formats.Get(i)";
_d = (b4a.example.b4xformatter._b4xformatdata)(__ref._formats /*anywheresoftware.b4a.objects.collections.List*/ .Get(_i));
RDebugUtils.currentLine=21037059;
 //BA.debugLineNum = 21037059;BA.debugLine="If Number <= d.RangeEnd And Number >= d.RangeSta";
if (_number<=_d.RangeEnd /*double*/  && _number>=_d.RangeStart /*double*/ ) { 
if (true) return _d;};
 }
};
RDebugUtils.currentLine=21037061;
 //BA.debugLineNum = 21037061;BA.debugLine="Return formats.Get(0)";
if (true) return (b4a.example.b4xformatter._b4xformatdata)(__ref._formats /*anywheresoftware.b4a.objects.collections.List*/ .Get((int) (0)));
RDebugUtils.currentLine=21037062;
 //BA.debugLineNum = 21037062;BA.debugLine="End Sub";
return null;
}
public String  _formatlabel(b4a.example.b4xformatter __ref,double _number,anywheresoftware.b4a.objects.B4XViewWrapper _label) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "formatlabel", true))
	 {return ((String) Debug.delegate(ba, "formatlabel", new Object[] {_number,_label}));}
b4a.example.b4xformatter._b4xformatdata _data = null;
RDebugUtils.currentLine=21168128;
 //BA.debugLineNum = 21168128;BA.debugLine="Public Sub FormatLabel (Number As Double, Label As";
RDebugUtils.currentLine=21168129;
 //BA.debugLineNum = 21168129;BA.debugLine="Label.Text = Format(Number)";
_label.setText(BA.ObjectToCharSequence(__ref._format /*String*/ (null,_number)));
RDebugUtils.currentLine=21168130;
 //BA.debugLineNum = 21168130;BA.debugLine="Dim data As B4XFormatData = GetFormatData(Number)";
_data = __ref._getformatdata /*b4a.example.b4xformatter._b4xformatdata*/ (null,_number);
RDebugUtils.currentLine=21168131;
 //BA.debugLineNum = 21168131;BA.debugLine="If data.TextColor <> 0 Then Label.TextColor = dat";
if (_data.TextColor /*int*/ !=0) { 
_label.setTextColor(_data.TextColor /*int*/ );};
RDebugUtils.currentLine=21168132;
 //BA.debugLineNum = 21168132;BA.debugLine="If data.FormatFont.IsInitialized Then Label.Font";
if (_data.FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ .getIsInitialized()) { 
_label.setFont(_data.FormatFont /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XFont*/ );};
RDebugUtils.currentLine=21168133;
 //BA.debugLineNum = 21168133;BA.debugLine="End Sub";
return "";
}
public b4a.example.b4xformatter._b4xformatdata  _newformatdata(b4a.example.b4xformatter __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xformatter";
if (Debug.shouldDelegate(ba, "newformatdata", true))
	 {return ((b4a.example.b4xformatter._b4xformatdata) Debug.delegate(ba, "newformatdata", null));}
RDebugUtils.currentLine=20774912;
 //BA.debugLineNum = 20774912;BA.debugLine="Public Sub NewFormatData As B4XFormatData";
RDebugUtils.currentLine=20774913;
 //BA.debugLineNum = 20774913;BA.debugLine="Return CopyFormatData(GetDefaultFormat)";
if (true) return __ref._copyformatdata /*b4a.example.b4xformatter._b4xformatdata*/ (null,__ref._getdefaultformat /*b4a.example.b4xformatter._b4xformatdata*/ (null));
RDebugUtils.currentLine=20774914;
 //BA.debugLineNum = 20774914;BA.debugLine="End Sub";
return null;
}
}