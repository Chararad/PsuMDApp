package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xgifview extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.b4xgifview");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xgifview.class).invoke(this, new Object[] {null});
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
public anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
public anywheresoftware.b4j.object.JavaObject _gifdrawable = null;
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
public String  _setgif(b4a.example.b4xgifview __ref,String _dir,String _filename) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "setgif", true))
	 {return ((String) Debug.delegate(ba, "setgif", new Object[] {_dir,_filename}));}
RDebugUtils.currentLine=30146560;
 //BA.debugLineNum = 30146560;BA.debugLine="Public Sub SetGif(Dir As String, FileName As Strin";
RDebugUtils.currentLine=30146566;
 //BA.debugLineNum = 30146566;BA.debugLine="If Dir = File.DirAssets Then";
if ((_dir).equals(__c.File.getDirAssets())) { 
RDebugUtils.currentLine=30146567;
 //BA.debugLineNum = 30146567;BA.debugLine="SetGif2(File.ReadBytes(Dir, FileName))";
__ref._setgif2 /*String*/ (null,__c.File.ReadBytes(_dir,_filename));
 }else {
RDebugUtils.currentLine=30146569;
 //BA.debugLineNum = 30146569;BA.debugLine="SetBitmap(File.Combine(Dir, FileName))";
__ref._setbitmap /*String*/ (null,(Object)(__c.File.Combine(_dir,_filename)));
 };
RDebugUtils.currentLine=30146572;
 //BA.debugLineNum = 30146572;BA.debugLine="End Sub";
return "";
}
public String  _base_resize(b4a.example.b4xgifview __ref,double _width,double _height) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "base_resize", true))
	 {return ((String) Debug.delegate(ba, "base_resize", new Object[] {_width,_height}));}
RDebugUtils.currentLine=30408704;
 //BA.debugLineNum = 30408704;BA.debugLine="Public Sub Base_Resize (Width As Double, Height As";
RDebugUtils.currentLine=30408706;
 //BA.debugLineNum = 30408706;BA.debugLine="iv.SetLayoutAnimated(0, 0, 0, Width, Height)";
__ref._iv /*anywheresoftware.b4a.objects.ImageViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),(int) (_height));
RDebugUtils.currentLine=30408715;
 //BA.debugLineNum = 30408715;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.b4xgifview __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
RDebugUtils.currentLine=29949952;
 //BA.debugLineNum = 29949952;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=29949953;
 //BA.debugLineNum = 29949953;BA.debugLine="Private mEventName As String 'ignore";
_meventname = "";
RDebugUtils.currentLine=29949954;
 //BA.debugLineNum = 29949954;BA.debugLine="Private mCallBack As Object 'ignore";
_mcallback = new Object();
RDebugUtils.currentLine=29949955;
 //BA.debugLineNum = 29949955;BA.debugLine="Public mBase As B4XView";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=29949956;
 //BA.debugLineNum = 29949956;BA.debugLine="Private xui As XUI 'ignore";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=29949957;
 //BA.debugLineNum = 29949957;BA.debugLine="Public Tag As Object";
_tag = new Object();
RDebugUtils.currentLine=29949961;
 //BA.debugLineNum = 29949961;BA.debugLine="Private iv As ImageView";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=29949962;
 //BA.debugLineNum = 29949962;BA.debugLine="Public GifDrawable As JavaObject";
_gifdrawable = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=29949966;
 //BA.debugLineNum = 29949966;BA.debugLine="End Sub";
return "";
}
public String  _designercreateview(b4a.example.b4xgifview __ref,Object _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "designercreateview", true))
	 {return ((String) Debug.delegate(ba, "designercreateview", new Object[] {_base,_lbl,_props}));}
RDebugUtils.currentLine=30081024;
 //BA.debugLineNum = 30081024;BA.debugLine="Public Sub DesignerCreateView (Base As Object, Lbl";
RDebugUtils.currentLine=30081025;
 //BA.debugLineNum = 30081025;BA.debugLine="mBase = Base";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_base));
RDebugUtils.currentLine=30081026;
 //BA.debugLineNum = 30081026;BA.debugLine="Tag = mBase.Tag";
__ref._tag /*Object*/  = __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getTag();
RDebugUtils.currentLine=30081027;
 //BA.debugLineNum = 30081027;BA.debugLine="mBase.Tag = Me";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setTag(this);
RDebugUtils.currentLine=30081038;
 //BA.debugLineNum = 30081038;BA.debugLine="iv.Initialize(\"\")";
__ref._iv /*anywheresoftware.b4a.objects.ImageViewWrapper*/ .Initialize(ba,"");
RDebugUtils.currentLine=30081039;
 //BA.debugLineNum = 30081039;BA.debugLine="mBase.AddView(iv, 0, 0, mBase.Width, mBase.Height";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._iv /*anywheresoftware.b4a.objects.ImageViewWrapper*/ .getObject()),(int) (0),(int) (0),__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=30081041;
 //BA.debugLineNum = 30081041;BA.debugLine="End Sub";
return "";
}
public String  _initialize(b4a.example.b4xgifview __ref,anywheresoftware.b4a.BA _ba,Object _callback,String _eventname) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_callback,_eventname}));}
RDebugUtils.currentLine=30015488;
 //BA.debugLineNum = 30015488;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
RDebugUtils.currentLine=30015489;
 //BA.debugLineNum = 30015489;BA.debugLine="mEventName = EventName";
__ref._meventname /*String*/  = _eventname;
RDebugUtils.currentLine=30015490;
 //BA.debugLineNum = 30015490;BA.debugLine="mCallBack = Callback";
__ref._mcallback /*Object*/  = _callback;
RDebugUtils.currentLine=30015491;
 //BA.debugLineNum = 30015491;BA.debugLine="End Sub";
return "";
}
public String  _resizebasedonimage(b4a.example.b4xgifview __ref,anywheresoftware.b4a.objects.B4XViewWrapper _xiv,float _bmpratio) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "resizebasedonimage", true))
	 {return ((String) Debug.delegate(ba, "resizebasedonimage", new Object[] {_xiv,_bmpratio}));}
float _viewratio = 0f;
int _height = 0;
int _width = 0;
RDebugUtils.currentLine=30343168;
 //BA.debugLineNum = 30343168;BA.debugLine="Private Sub ResizeBasedOnImage(xiv As B4XView, Bmp";
RDebugUtils.currentLine=30343169;
 //BA.debugLineNum = 30343169;BA.debugLine="Dim viewRatio As Float = mBase.Width / mBase.Heig";
_viewratio = (float) (__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth()/(double)__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=30343170;
 //BA.debugLineNum = 30343170;BA.debugLine="Dim Height, Width As Int";
_height = 0;
_width = 0;
RDebugUtils.currentLine=30343171;
 //BA.debugLineNum = 30343171;BA.debugLine="If viewRatio > BmpRatio Then";
if (_viewratio>_bmpratio) { 
RDebugUtils.currentLine=30343172;
 //BA.debugLineNum = 30343172;BA.debugLine="Height = mBase.Height";
_height = __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight();
RDebugUtils.currentLine=30343173;
 //BA.debugLineNum = 30343173;BA.debugLine="Width = mBase.Height * BmpRatio";
_width = (int) (__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight()*_bmpratio);
 }else {
RDebugUtils.currentLine=30343175;
 //BA.debugLineNum = 30343175;BA.debugLine="Width = mBase.Width";
_width = __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth();
RDebugUtils.currentLine=30343176;
 //BA.debugLineNum = 30343176;BA.debugLine="Height = mBase.Width / BmpRatio";
_height = (int) (__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth()/(double)_bmpratio);
 };
RDebugUtils.currentLine=30343178;
 //BA.debugLineNum = 30343178;BA.debugLine="xiv.SetLayoutAnimated(0, mBase.Width / 2 - Width";
_xiv.SetLayoutAnimated((int) (0),(int) (__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth()/(double)2-_width/(double)2),(int) (__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight()/(double)2-_height/(double)2),_width,_height);
RDebugUtils.currentLine=30343179;
 //BA.debugLineNum = 30343179;BA.debugLine="End Sub";
return "";
}
public String  _setbitmap(b4a.example.b4xgifview __ref,Object _obj) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "setbitmap", true))
	 {return ((String) Debug.delegate(ba, "setbitmap", new Object[] {_obj}));}
anywheresoftware.b4j.object.JavaObject _jo = null;
int _w = 0;
int _h = 0;
RDebugUtils.currentLine=30277632;
 //BA.debugLineNum = 30277632;BA.debugLine="Private Sub SetBitmap(obj As Object)";
RDebugUtils.currentLine=30277633;
 //BA.debugLineNum = 30277633;BA.debugLine="Dim GifDrawable As JavaObject";
_gifdrawable = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=30277634;
 //BA.debugLineNum = 30277634;BA.debugLine="GifDrawable.InitializeNewInstance(\"pl.droidsonroi";
__ref._gifdrawable /*anywheresoftware.b4j.object.JavaObject*/ .InitializeNewInstance("pl.droidsonroids.gif.GifDrawable",new Object[]{_obj});
RDebugUtils.currentLine=30277635;
 //BA.debugLineNum = 30277635;BA.debugLine="iv.Background = GifDrawable";
__ref._iv /*anywheresoftware.b4a.objects.ImageViewWrapper*/ .setBackground((android.graphics.drawable.Drawable)(__ref._gifdrawable /*anywheresoftware.b4j.object.JavaObject*/ .getObject()));
RDebugUtils.currentLine=30277636;
 //BA.debugLineNum = 30277636;BA.debugLine="Dim jo As JavaObject = GifDrawable";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = __ref._gifdrawable /*anywheresoftware.b4j.object.JavaObject*/ ;
RDebugUtils.currentLine=30277637;
 //BA.debugLineNum = 30277637;BA.debugLine="Dim w As Int = jo.RunMethod(\"getIntrinsicWidth\",";
_w = (int)(BA.ObjectToNumber(_jo.RunMethod("getIntrinsicWidth",(Object[])(__c.Null))));
RDebugUtils.currentLine=30277638;
 //BA.debugLineNum = 30277638;BA.debugLine="Dim h As Int = jo.RunMethod(\"getIntrinsicHeight\",";
_h = (int)(BA.ObjectToNumber(_jo.RunMethod("getIntrinsicHeight",(Object[])(__c.Null))));
RDebugUtils.currentLine=30277639;
 //BA.debugLineNum = 30277639;BA.debugLine="ResizeBasedOnImage(iv, w / h)";
__ref._resizebasedonimage /*String*/ (null,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(__ref._iv /*anywheresoftware.b4a.objects.ImageViewWrapper*/ .getObject())),(float) (_w/(double)_h));
RDebugUtils.currentLine=30277640;
 //BA.debugLineNum = 30277640;BA.debugLine="End Sub";
return "";
}
public String  _setgif2(b4a.example.b4xgifview __ref,byte[] _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xgifview";
if (Debug.shouldDelegate(ba, "setgif2", true))
	 {return ((String) Debug.delegate(ba, "setgif2", new Object[] {_data}));}
RDebugUtils.currentLine=30212096;
 //BA.debugLineNum = 30212096;BA.debugLine="Public Sub SetGif2 (Data() As Byte)";
RDebugUtils.currentLine=30212110;
 //BA.debugLineNum = 30212110;BA.debugLine="SetBitmap(Data)";
__ref._setbitmap /*String*/ (null,(Object)(_data));
RDebugUtils.currentLine=30212112;
 //BA.debugLineNum = 30212112;BA.debugLine="End Sub";
return "";
}
}