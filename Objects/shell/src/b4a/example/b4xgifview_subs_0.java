package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class b4xgifview_subs_0 {


public static RemoteObject  _base_resize(RemoteObject __ref,RemoteObject _width,RemoteObject _height) throws Exception{
try {
		Debug.PushSubsStack("Base_Resize (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,106);
if (RapidSub.canDelegate("base_resize")) { return __ref.runUserSub(false, "b4xgifview","base_resize", __ref, _width, _height);}
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 106;BA.debugLine="Public Sub Base_Resize (Width As Double, Height As";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 108;BA.debugLine="iv.SetLayoutAnimated(0, 0, 0, Width, Height)";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_iv" /*RemoteObject*/ ).runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, _width)),(Object)(BA.numberCast(int.class, _height)));
 BA.debugLineNum = 117;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Private mEventName As String 'ignore";
b4xgifview._meventname = RemoteObject.createImmutable("");__ref.setField("_meventname",b4xgifview._meventname);
 //BA.debugLineNum = 3;BA.debugLine="Private mCallBack As Object 'ignore";
b4xgifview._mcallback = RemoteObject.createNew ("Object");__ref.setField("_mcallback",b4xgifview._mcallback);
 //BA.debugLineNum = 4;BA.debugLine="Public mBase As B4XView";
b4xgifview._mbase = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");__ref.setField("_mbase",b4xgifview._mbase);
 //BA.debugLineNum = 5;BA.debugLine="Private xui As XUI 'ignore";
b4xgifview._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");__ref.setField("_xui",b4xgifview._xui);
 //BA.debugLineNum = 6;BA.debugLine="Public Tag As Object";
b4xgifview._tag = RemoteObject.createNew ("Object");__ref.setField("_tag",b4xgifview._tag);
 //BA.debugLineNum = 10;BA.debugLine="Private iv As ImageView";
b4xgifview._iv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");__ref.setField("_iv",b4xgifview._iv);
 //BA.debugLineNum = 11;BA.debugLine="Public GifDrawable As JavaObject";
b4xgifview._gifdrawable = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");__ref.setField("_gifdrawable",b4xgifview._gifdrawable);
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _designercreateview(RemoteObject __ref,RemoteObject _base,RemoteObject _lbl,RemoteObject _props) throws Exception{
try {
		Debug.PushSubsStack("DesignerCreateView (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,22);
if (RapidSub.canDelegate("designercreateview")) { return __ref.runUserSub(false, "b4xgifview","designercreateview", __ref, _base, _lbl, _props);}
Debug.locals.put("Base", _base);
Debug.locals.put("Lbl", _lbl);
Debug.locals.put("Props", _props);
 BA.debugLineNum = 22;BA.debugLine="Public Sub DesignerCreateView (Base As Object, Lbl";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 23;BA.debugLine="mBase = Base";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_mbase" /*RemoteObject*/ ).setObject (_base);
 BA.debugLineNum = 24;BA.debugLine="Tag = mBase.Tag";
Debug.JustUpdateDeviceLine();
__ref.setField ("_tag" /*RemoteObject*/ ,__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(false,"getTag"));
 BA.debugLineNum = 25;BA.debugLine="mBase.Tag = Me";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(false,"setTag",__ref);
 BA.debugLineNum = 36;BA.debugLine="iv.Initialize(\"\")";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_iv" /*RemoteObject*/ ).runVoidMethod ("Initialize",__ref.getField(false, "ba"),(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 37;BA.debugLine="mBase.AddView(iv, 0, 0, mBase.Width, mBase.Height";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_mbase" /*RemoteObject*/ ).runVoidMethod ("AddView",(Object)((__ref.getField(false,"_iv" /*RemoteObject*/ ).getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getWidth")),(Object)(__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getHeight")));
 BA.debugLineNum = 39;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba,RemoteObject _callback,RemoteObject _eventname) throws Exception{
try {
		Debug.PushSubsStack("Initialize (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,17);
if (RapidSub.canDelegate("initialize")) { return __ref.runUserSub(false, "b4xgifview","initialize", __ref, _ba, _callback, _eventname);}
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
Debug.locals.put("Callback", _callback);
Debug.locals.put("EventName", _eventname);
 BA.debugLineNum = 17;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 18;BA.debugLine="mEventName = EventName";
Debug.JustUpdateDeviceLine();
__ref.setField ("_meventname" /*RemoteObject*/ ,_eventname);
 BA.debugLineNum = 19;BA.debugLine="mCallBack = Callback";
Debug.JustUpdateDeviceLine();
__ref.setField ("_mcallback" /*RemoteObject*/ ,_callback);
 BA.debugLineNum = 20;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _resizebasedonimage(RemoteObject __ref,RemoteObject _xiv,RemoteObject _bmpratio) throws Exception{
try {
		Debug.PushSubsStack("ResizeBasedOnImage (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,93);
if (RapidSub.canDelegate("resizebasedonimage")) { return __ref.runUserSub(false, "b4xgifview","resizebasedonimage", __ref, _xiv, _bmpratio);}
RemoteObject _viewratio = RemoteObject.createImmutable(0f);
RemoteObject _height = RemoteObject.createImmutable(0);
RemoteObject _width = RemoteObject.createImmutable(0);
Debug.locals.put("xiv", _xiv);
Debug.locals.put("BmpRatio", _bmpratio);
 BA.debugLineNum = 93;BA.debugLine="Private Sub ResizeBasedOnImage(xiv As B4XView, Bmp";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 94;BA.debugLine="Dim viewRatio As Float = mBase.Width / mBase.Heig";
Debug.JustUpdateDeviceLine();
_viewratio = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getWidth"),__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getHeight")}, "/",0, 0));Debug.locals.put("viewRatio", _viewratio);Debug.locals.put("viewRatio", _viewratio);
 BA.debugLineNum = 95;BA.debugLine="Dim Height, Width As Int";
Debug.JustUpdateDeviceLine();
_height = RemoteObject.createImmutable(0);Debug.locals.put("Height", _height);
_width = RemoteObject.createImmutable(0);Debug.locals.put("Width", _width);
 BA.debugLineNum = 96;BA.debugLine="If viewRatio > BmpRatio Then";
Debug.JustUpdateDeviceLine();
if (RemoteObject.solveBoolean(">",_viewratio,BA.numberCast(double.class, _bmpratio))) { 
 BA.debugLineNum = 97;BA.debugLine="Height = mBase.Height";
Debug.JustUpdateDeviceLine();
_height = __ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getHeight");Debug.locals.put("Height", _height);
 BA.debugLineNum = 98;BA.debugLine="Width = mBase.Height * BmpRatio";
Debug.JustUpdateDeviceLine();
_width = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getHeight"),_bmpratio}, "*",0, 0));Debug.locals.put("Width", _width);
 }else {
 BA.debugLineNum = 100;BA.debugLine="Width = mBase.Width";
Debug.JustUpdateDeviceLine();
_width = __ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getWidth");Debug.locals.put("Width", _width);
 BA.debugLineNum = 101;BA.debugLine="Height = mBase.Width / BmpRatio";
Debug.JustUpdateDeviceLine();
_height = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getWidth"),_bmpratio}, "/",0, 0));Debug.locals.put("Height", _height);
 };
 BA.debugLineNum = 103;BA.debugLine="xiv.SetLayoutAnimated(0, mBase.Width / 2 - Width";
Debug.JustUpdateDeviceLine();
_xiv.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getWidth"),RemoteObject.createImmutable(2),_width,RemoteObject.createImmutable(2)}, "/-/",1, 0))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {__ref.getField(false,"_mbase" /*RemoteObject*/ ).runMethod(true,"getHeight"),RemoteObject.createImmutable(2),_height,RemoteObject.createImmutable(2)}, "/-/",1, 0))),(Object)(_width),(Object)(_height));
 BA.debugLineNum = 104;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setbitmap(RemoteObject __ref,RemoteObject _obj) throws Exception{
try {
		Debug.PushSubsStack("SetBitmap (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,81);
if (RapidSub.canDelegate("setbitmap")) { return __ref.runUserSub(false, "b4xgifview","setbitmap", __ref, _obj);}
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _w = RemoteObject.createImmutable(0);
RemoteObject _h = RemoteObject.createImmutable(0);
Debug.locals.put("obj", _obj);
 BA.debugLineNum = 81;BA.debugLine="Private Sub SetBitmap(obj As Object)";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 82;BA.debugLine="Dim GifDrawable As JavaObject";
Debug.JustUpdateDeviceLine();
b4xgifview._gifdrawable = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");__ref.setField("_gifdrawable",b4xgifview._gifdrawable);
 BA.debugLineNum = 83;BA.debugLine="GifDrawable.InitializeNewInstance(\"pl.droidsonroi";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_gifdrawable" /*RemoteObject*/ ).runVoidMethod ("InitializeNewInstance",(Object)(BA.ObjectToString("pl.droidsonroids.gif.GifDrawable")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {_obj})));
 BA.debugLineNum = 84;BA.debugLine="iv.Background = GifDrawable";
Debug.JustUpdateDeviceLine();
__ref.getField(false,"_iv" /*RemoteObject*/ ).runMethod(false,"setBackground",(__ref.getField(false,"_gifdrawable" /*RemoteObject*/ ).getObject()));
 BA.debugLineNum = 85;BA.debugLine="Dim jo As JavaObject = GifDrawable";
Debug.JustUpdateDeviceLine();
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_jo = __ref.getField(false,"_gifdrawable" /*RemoteObject*/ );Debug.locals.put("jo", _jo);Debug.locals.put("jo", _jo);
 BA.debugLineNum = 86;BA.debugLine="Dim w As Int = jo.RunMethod(\"getIntrinsicWidth\",";
Debug.JustUpdateDeviceLine();
_w = BA.numberCast(int.class, _jo.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getIntrinsicWidth")),(Object)((b4xgifview.__c.getField(false,"Null")))));Debug.locals.put("w", _w);Debug.locals.put("w", _w);
 BA.debugLineNum = 87;BA.debugLine="Dim h As Int = jo.RunMethod(\"getIntrinsicHeight\",";
Debug.JustUpdateDeviceLine();
_h = BA.numberCast(int.class, _jo.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getIntrinsicHeight")),(Object)((b4xgifview.__c.getField(false,"Null")))));Debug.locals.put("h", _h);Debug.locals.put("h", _h);
 BA.debugLineNum = 88;BA.debugLine="ResizeBasedOnImage(iv, w / h)";
Debug.JustUpdateDeviceLine();
__ref.runClassMethod (b4a.example.b4xgifview.class, "_resizebasedonimage" /*RemoteObject*/ ,RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), __ref.getField(false,"_iv" /*RemoteObject*/ ).getObject()),(Object)(BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {_w,_h}, "/",0, 0))));
 BA.debugLineNum = 89;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setgif(RemoteObject __ref,RemoteObject _dir,RemoteObject _filename) throws Exception{
try {
		Debug.PushSubsStack("SetGif (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,41);
if (RapidSub.canDelegate("setgif")) { return __ref.runUserSub(false, "b4xgifview","setgif", __ref, _dir, _filename);}
Debug.locals.put("Dir", _dir);
Debug.locals.put("FileName", _filename);
 BA.debugLineNum = 41;BA.debugLine="Public Sub SetGif(Dir As String, FileName As Strin";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 47;BA.debugLine="If Dir = File.DirAssets Then";
Debug.JustUpdateDeviceLine();
if (RemoteObject.solveBoolean("=",_dir,b4xgifview.__c.getField(false,"File").runMethod(true,"getDirAssets"))) { 
 BA.debugLineNum = 48;BA.debugLine="SetGif2(File.ReadBytes(Dir, FileName))";
Debug.JustUpdateDeviceLine();
__ref.runClassMethod (b4a.example.b4xgifview.class, "_setgif2" /*RemoteObject*/ ,(Object)(b4xgifview.__c.getField(false,"File").runMethod(false,"ReadBytes",(Object)(_dir),(Object)(_filename))));
 }else {
 BA.debugLineNum = 50;BA.debugLine="SetBitmap(File.Combine(Dir, FileName))";
Debug.JustUpdateDeviceLine();
__ref.runClassMethod (b4a.example.b4xgifview.class, "_setbitmap" /*RemoteObject*/ ,(Object)((b4xgifview.__c.getField(false,"File").runMethod(true,"Combine",(Object)(_dir),(Object)(_filename)))));
 };
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setgif2(RemoteObject __ref,RemoteObject _data) throws Exception{
try {
		Debug.PushSubsStack("SetGif2 (b4xgifview) ","b4xgifview",25,__ref.getField(false, "ba"),__ref,55);
if (RapidSub.canDelegate("setgif2")) { return __ref.runUserSub(false, "b4xgifview","setgif2", __ref, _data);}
Debug.locals.put("Data", _data);
 BA.debugLineNum = 55;BA.debugLine="Public Sub SetGif2 (Data() As Byte)";
Debug.JustUpdateDeviceLine();
 BA.debugLineNum = 69;BA.debugLine="SetBitmap(Data)";
Debug.JustUpdateDeviceLine();
__ref.runClassMethod (b4a.example.b4xgifview.class, "_setbitmap" /*RemoteObject*/ ,(Object)((_data)));
 BA.debugLineNum = 71;BA.debugLine="End Sub";
Debug.JustUpdateDeviceLine();
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}