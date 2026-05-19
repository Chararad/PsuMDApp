package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class corkactivity_subs_0 {


public static void  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,58);
if (RapidSub.canDelegate("activity_create")) { b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","activity_create", _firsttime); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.corkactivity parent,RemoteObject _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.corkactivity parent;
RemoteObject _firsttime;
RemoteObject _loadednotecount = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _nkey = RemoteObject.createImmutable("");
RemoteObject _savedcolor = RemoteObject.createImmutable(0);
RemoteObject _loadedimgcount = RemoteObject.createImmutable(0);
RemoteObject _ikey = RemoteObject.createImmutable("");
RemoteObject _iv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
RemoteObject _loadedcanvascount = RemoteObject.createImmutable(0);
RemoteObject _ckey = RemoteObject.createImmutable("");
RemoteObject _f = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cvs = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XCanvas");
RemoteObject _bmp = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
RemoteObject _canvasrect = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XCanvas.B4XRect");
int step43;
int limit43;
int step56;
int limit56;
int step70;
int limit70;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,58);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 59;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(67108864);
if (true) break;

case 1:
//select
this.state = 26;
switch (BA.switchObjectToInt(parent.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
this.state = 3;
if (true) break;
}
case 1: {
this.state = 11;
if (true) break;
}
case 2: {
this.state = 19;
if (true) break;
}
}
if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 61;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(268435456);
if (true) break;

case 4:
//if
this.state = 9;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._starter._darkmode /*RemoteObject*/ ,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 BA.debugLineNum = 62;BA.debugLine="Activity.LoadLayout(\"corkboardLayout\")";
Debug.ShouldStop(536870912);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("corkboardLayout")),corkactivity.mostCurrent.activityBA);
 if (true) break;

case 8:
//C
this.state = 9;
 BA.debugLineNum = 64;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark\")";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("corkboardLayoutDark")),corkactivity.mostCurrent.activityBA);
 if (true) break;

case 9:
//C
this.state = 26;
;
 if (true) break;

case 11:
//C
this.state = 12;
 BA.debugLineNum = 67;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (true) break;

case 12:
//if
this.state = 17;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._starter._darkmode /*RemoteObject*/ ,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 17;
 BA.debugLineNum = 68;BA.debugLine="Activity.LoadLayout(\"corkboardLayout2\")";
Debug.ShouldStop(8);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("corkboardLayout2")),corkactivity.mostCurrent.activityBA);
 if (true) break;

case 16:
//C
this.state = 17;
 BA.debugLineNum = 70;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark2\")";
Debug.ShouldStop(32);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("corkboardLayoutDark2")),corkactivity.mostCurrent.activityBA);
 if (true) break;

case 17:
//C
this.state = 26;
;
 if (true) break;

case 19:
//C
this.state = 20;
 BA.debugLineNum = 73;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(256);
if (true) break;

case 20:
//if
this.state = 25;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._starter._darkmode /*RemoteObject*/ ,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 22;
}else {
this.state = 24;
}if (true) break;

case 22:
//C
this.state = 25;
 BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"corkboardLayout3\")";
Debug.ShouldStop(512);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("corkboardLayout3")),corkactivity.mostCurrent.activityBA);
 if (true) break;

case 24:
//C
this.state = 25;
 BA.debugLineNum = 76;BA.debugLine="Activity.LoadLayout(\"corkboardLayoutDark3\")";
Debug.ShouldStop(2048);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("corkboardLayoutDark3")),corkactivity.mostCurrent.activityBA);
 if (true) break;

case 25:
//C
this.state = 26;
;
 if (true) break;
;
 BA.debugLineNum = 80;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(32768);

case 26:
//if
this.state = 31;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._starter._darkmode /*RemoteObject*/ ,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 28;
}else {
this.state = 30;
}if (true) break;

case 28:
//C
this.state = 31;
 BA.debugLineNum = 81;BA.debugLine="penSpnr.DropdownBackgroundColor = Colors.DarkGra";
Debug.ShouldStop(65536);
parent.mostCurrent._penspnr.runMethod(true,"setDropdownBackgroundColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 82;BA.debugLine="penSpnr.DropdownTextColor = Colors.White";
Debug.ShouldStop(131072);
parent.mostCurrent._penspnr.runMethod(true,"setDropdownTextColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 if (true) break;

case 30:
//C
this.state = 31;
 BA.debugLineNum = 84;BA.debugLine="penSpnr.DropdownTextColor = Colors.Black";
Debug.ShouldStop(524288);
parent.mostCurrent._penspnr.runMethod(true,"setDropdownTextColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 if (true) break;

case 31:
//C
this.state = 32;
;
 BA.debugLineNum = 87;BA.debugLine="penSpnr.AddAll(Array As String(\"Black\", \"Blue\", \"";
Debug.ShouldStop(4194304);
parent.mostCurrent._penspnr.runVoidMethod ("AddAll",(Object)(parent.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {6},new Object[] {BA.ObjectToString("Black"),BA.ObjectToString("Blue"),BA.ObjectToString("Green"),BA.ObjectToString("Red"),BA.ObjectToString("Yellow"),RemoteObject.createImmutable("Eraser")})))));
 BA.debugLineNum = 88;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(8388608);
if (true) break;

case 32:
//if
this.state = 35;
if (_firsttime.<Boolean>get().booleanValue()) { 
this.state = 34;
}if (true) break;

case 34:
//C
this.state = 35;
 BA.debugLineNum = 89;BA.debugLine="imgPicker.Initialize(\"CC\")";
Debug.ShouldStop(16777216);
parent._imgpicker.runVoidMethod ("Initialize",(Object)(RemoteObject.createImmutable("CC")));
 if (true) break;

case 35:
//C
this.state = 36;
;
 BA.debugLineNum = 91;BA.debugLine="penSpnr.Visible = False";
Debug.ShouldStop(67108864);
parent.mostCurrent._penspnr.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 92;BA.debugLine="Width = 80dip";
Debug.ShouldStop(134217728);
parent._width = parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));
 BA.debugLineNum = 93;BA.debugLine="Height = 60dip";
Debug.ShouldStop(268435456);
parent._height = parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));
 BA.debugLineNum = 94;BA.debugLine="ddn.Initialize(Me, \"NoteDrag\")";
Debug.ShouldStop(536870912);
parent.mostCurrent._ddn.runVoidMethod ("_initialize",corkactivity.processBA,(Object)(corkactivity.getObject()),(Object)(RemoteObject.createImmutable("NoteDrag")));
 BA.debugLineNum = 95;BA.debugLine="ddi.Initialize(Me, \"ImgDrag\")";
Debug.ShouldStop(1073741824);
parent.mostCurrent._ddi.runVoidMethod ("_initialize",corkactivity.processBA,(Object)(corkactivity.getObject()),(Object)(RemoteObject.createImmutable("ImgDrag")));
 BA.debugLineNum = 96;BA.debugLine="ddc.Initialize(Me, \"CanvasDrag\")";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._ddc.runVoidMethod ("_initialize",corkactivity.processBA,(Object)(corkactivity.getObject()),(Object)(RemoteObject.createImmutable("CanvasDrag")));
 BA.debugLineNum = 98;BA.debugLine="If Main.kvs.IsInitialized = False Then";
Debug.ShouldStop(2);
if (true) break;

case 36:
//if
this.state = 39;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"IsInitialized"),parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 38;
}if (true) break;

case 38:
//C
this.state = 39;
 BA.debugLineNum = 99;BA.debugLine="Main.kvs.Initialize(File.DirInternal, \"notes_dat";
Debug.ShouldStop(4);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_initialize",corkactivity.processBA,(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("notes_data")));
 if (true) break;

case 39:
//C
this.state = 40;
;
 BA.debugLineNum = 102;BA.debugLine="isLoading = True";
Debug.ShouldStop(32);
parent._isloading = parent.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 104;BA.debugLine="If Main.kvs.ContainsKey(\"note_count\") Then noteCo";
Debug.ShouldStop(128);
if (true) break;

case 40:
//if
this.state = 47;
if (parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("note_count"))).<Boolean>get().booleanValue()) { 
this.state = 42;
;}
else {
this.state = 44;
;}if (true) break;

case 42:
//C
this.state = 47;
parent._notecount = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("note_count"))));
if (true) break;

case 44:
//C
this.state = 47;
parent._notecount = BA.numberCast(int.class, 0);
if (true) break;

case 47:
//C
this.state = 48;
;
 BA.debugLineNum = 105;BA.debugLine="Dim loadedNoteCount As Int = noteCount";
Debug.ShouldStop(256);
_loadednotecount = parent._notecount;Debug.locals.put("loadedNoteCount", _loadednotecount);Debug.locals.put("loadedNoteCount", _loadednotecount);
 BA.debugLineNum = 106;BA.debugLine="For i = 0 To loadedNoteCount - 1";
Debug.ShouldStop(512);
if (true) break;

case 48:
//for
this.state = 55;
step43 = 1;
limit43 = RemoteObject.solve(new RemoteObject[] {_loadednotecount,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
Debug.locals.put("i", _i);
this.state = 90;
if (true) break;

case 90:
//C
this.state = 55;
if ((step43 > 0 && _i <= limit43) || (step43 < 0 && _i >= limit43)) this.state = 50;
if (true) break;

case 91:
//C
this.state = 90;
_i = ((int)(0 + _i + step43)) ;
Debug.locals.put("i", _i);
if (true) break;

case 50:
//C
this.state = 51;
 BA.debugLineNum = 107;BA.debugLine="Dim nkey As String = \"note_\" & i";
Debug.ShouldStop(1024);
_nkey = RemoteObject.concat(RemoteObject.createImmutable("note_"),RemoteObject.createImmutable(_i));Debug.locals.put("nkey", _nkey);Debug.locals.put("nkey", _nkey);
 BA.debugLineNum = 108;BA.debugLine="If Main.kvs.ContainsKey(nkey & \"_text\") Then";
Debug.ShouldStop(2048);
if (true) break;

case 51:
//if
this.state = 54;
if (parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.concat(_nkey,RemoteObject.createImmutable("_text")))).<Boolean>get().booleanValue()) { 
this.state = 53;
}if (true) break;

case 53:
//C
this.state = 54;
 BA.debugLineNum = 109;BA.debugLine="Dim savedColor As Int = Main.kvs.Get(nkey & \"_c";
Debug.ShouldStop(4096);
_savedcolor = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_nkey,RemoteObject.createImmutable("_color")))));Debug.locals.put("savedColor", _savedcolor);Debug.locals.put("savedColor", _savedcolor);
 BA.debugLineNum = 110;BA.debugLine="R = Bit.And(Bit.ShiftRight(savedColor, 16), 0xF";
Debug.ShouldStop(8192);
parent._r = parent.mostCurrent.__c.getField(false,"Bit").runMethod(true,"And",(Object)(parent.mostCurrent.__c.getField(false,"Bit").runMethod(true,"ShiftRight",(Object)(_savedcolor),(Object)(BA.numberCast(int.class, 16)))),(Object)(BA.numberCast(int.class, ((int)0xff))));
 BA.debugLineNum = 111;BA.debugLine="G = Bit.And(Bit.ShiftRight(savedColor, 8), 0xFF";
Debug.ShouldStop(16384);
parent._g = parent.mostCurrent.__c.getField(false,"Bit").runMethod(true,"And",(Object)(parent.mostCurrent.__c.getField(false,"Bit").runMethod(true,"ShiftRight",(Object)(_savedcolor),(Object)(BA.numberCast(int.class, 8)))),(Object)(BA.numberCast(int.class, ((int)0xff))));
 BA.debugLineNum = 112;BA.debugLine="B = Bit.And(savedColor, 0xFF)";
Debug.ShouldStop(32768);
parent._b = parent.mostCurrent.__c.getField(false,"Bit").runMethod(true,"And",(Object)(_savedcolor),(Object)(BA.numberCast(int.class, ((int)0xff))));
 BA.debugLineNum = 113;BA.debugLine="noteCount = i + 1";
Debug.ShouldStop(65536);
parent._notecount = RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 114;BA.debugLine="AddStickyNote(Main.kvs.Get(nkey & \"_text\"), Mai";
Debug.ShouldStop(131072);
_addstickynote(BA.ObjectToString(parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_nkey,RemoteObject.createImmutable("_text"))))),BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_nkey,RemoteObject.createImmutable("_x"))))),BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_nkey,RemoteObject.createImmutable("_y"))))));
 if (true) break;

case 54:
//C
this.state = 91;
;
 if (true) break;
if (true) break;
Debug.locals.put("i", _i);
;
 BA.debugLineNum = 118;BA.debugLine="If Main.kvs.ContainsKey(\"img_count\") Then imgCoun";
Debug.ShouldStop(2097152);

case 55:
//if
this.state = 62;
if (parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("img_count"))).<Boolean>get().booleanValue()) { 
this.state = 57;
;}
else {
this.state = 59;
;}if (true) break;

case 57:
//C
this.state = 62;
parent._imgcount = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("img_count"))));
if (true) break;

case 59:
//C
this.state = 62;
parent._imgcount = BA.numberCast(int.class, 0);
if (true) break;

case 62:
//C
this.state = 63;
;
 BA.debugLineNum = 119;BA.debugLine="Dim loadedImgCount As Int = imgCount";
Debug.ShouldStop(4194304);
_loadedimgcount = parent._imgcount;Debug.locals.put("loadedImgCount", _loadedimgcount);Debug.locals.put("loadedImgCount", _loadedimgcount);
 BA.debugLineNum = 120;BA.debugLine="For i = 0 To loadedImgCount - 1";
Debug.ShouldStop(8388608);
if (true) break;

case 63:
//for
this.state = 70;
step56 = 1;
limit56 = RemoteObject.solve(new RemoteObject[] {_loadedimgcount,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
Debug.locals.put("i", _i);
this.state = 92;
if (true) break;

case 92:
//C
this.state = 70;
if ((step56 > 0 && _i <= limit56) || (step56 < 0 && _i >= limit56)) this.state = 65;
if (true) break;

case 93:
//C
this.state = 92;
_i = ((int)(0 + _i + step56)) ;
Debug.locals.put("i", _i);
if (true) break;

case 65:
//C
this.state = 66;
 BA.debugLineNum = 121;BA.debugLine="Dim ikey As String = \"img_\" & i";
Debug.ShouldStop(16777216);
_ikey = RemoteObject.concat(RemoteObject.createImmutable("img_"),RemoteObject.createImmutable(_i));Debug.locals.put("ikey", _ikey);Debug.locals.put("ikey", _ikey);
 BA.debugLineNum = 122;BA.debugLine="If Main.kvs.ContainsKey(ikey & \"_file\") Then";
Debug.ShouldStop(33554432);
if (true) break;

case 66:
//if
this.state = 69;
if (parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.concat(_ikey,RemoteObject.createImmutable("_file")))).<Boolean>get().booleanValue()) { 
this.state = 68;
}if (true) break;

case 68:
//C
this.state = 69;
 BA.debugLineNum = 123;BA.debugLine="Dim iv As ImageView";
Debug.ShouldStop(67108864);
_iv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");Debug.locals.put("iv", _iv);
 BA.debugLineNum = 124;BA.debugLine="iv.Initialize(\"ImgView\")";
Debug.ShouldStop(134217728);
_iv.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("ImgView")));
 BA.debugLineNum = 125;BA.debugLine="boardPnl.AddView(iv, Main.kvs.Get(ikey & \"_x\"),";
Debug.ShouldStop(268435456);
parent.mostCurrent._boardpnl.runVoidMethod ("AddView",(Object)((_iv.getObject())),(Object)(BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ikey,RemoteObject.createImmutable("_x")))))),(Object)(BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ikey,RemoteObject.createImmutable("_y")))))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))));
 BA.debugLineNum = 126;BA.debugLine="iv.Bitmap = LoadBitmapResize(File.DirInternal,";
Debug.ShouldStop(536870912);
_iv.runMethod(false,"setBitmap",(parent.mostCurrent.__c.runMethod(false,"LoadBitmapResize",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString(parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ikey,RemoteObject.createImmutable("_file")))))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(parent.mostCurrent.__c.getField(true,"True"))).getObject()));
 BA.debugLineNum = 127;BA.debugLine="iv.Tag = ikey";
Debug.ShouldStop(1073741824);
_iv.runMethod(false,"setTag",(_ikey));
 BA.debugLineNum = 128;BA.debugLine="ddi.AddDragView(iv, False)";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._ddi.runVoidMethod ("_adddragview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _iv.getObject()),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 129;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).A";
Debug.ShouldStop(1);
parent.mostCurrent._ddi.runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place1.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place2.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place3.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place4.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place5.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place6.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place7.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place8.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place9.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place10.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place11.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place12.getObject())).runVoidMethod ("_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._deletelbl.getObject()));
 if (true) break;

case 69:
//C
this.state = 93;
;
 if (true) break;
if (true) break;
Debug.locals.put("i", _i);
;
 BA.debugLineNum = 133;BA.debugLine="If Main.kvs.ContainsKey(\"cvs_count\") Then canvasC";
Debug.ShouldStop(16);

case 70:
//if
this.state = 77;
if (parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.createImmutable("cvs_count"))).<Boolean>get().booleanValue()) { 
this.state = 72;
;}
else {
this.state = 74;
;}if (true) break;

case 72:
//C
this.state = 77;
parent._canvascount = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("cvs_count"))));
if (true) break;

case 74:
//C
this.state = 77;
parent._canvascount = BA.numberCast(int.class, 0);
if (true) break;

case 77:
//C
this.state = 78;
;
 BA.debugLineNum = 134;BA.debugLine="Dim loadedCanvasCount As Int = canvasCount";
Debug.ShouldStop(32);
_loadedcanvascount = parent._canvascount;Debug.locals.put("loadedCanvasCount", _loadedcanvascount);Debug.locals.put("loadedCanvasCount", _loadedcanvascount);
 BA.debugLineNum = 135;BA.debugLine="For i = 0 To loadedCanvasCount - 1";
Debug.ShouldStop(64);
if (true) break;

case 78:
//for
this.state = 89;
step70 = 1;
limit70 = RemoteObject.solve(new RemoteObject[] {_loadedcanvascount,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
Debug.locals.put("i", _i);
this.state = 94;
if (true) break;

case 94:
//C
this.state = 89;
if ((step70 > 0 && _i <= limit70) || (step70 < 0 && _i >= limit70)) this.state = 80;
if (true) break;

case 95:
//C
this.state = 94;
_i = ((int)(0 + _i + step70)) ;
Debug.locals.put("i", _i);
if (true) break;

case 80:
//C
this.state = 81;
 BA.debugLineNum = 136;BA.debugLine="Dim ckey As String = \"cvs_\" & i";
Debug.ShouldStop(128);
_ckey = RemoteObject.concat(RemoteObject.createImmutable("cvs_"),RemoteObject.createImmutable(_i));Debug.locals.put("ckey", _ckey);Debug.locals.put("ckey", _ckey);
 BA.debugLineNum = 137;BA.debugLine="If Main.kvs.ContainsKey(ckey & \"_x\") Then";
Debug.ShouldStop(256);
if (true) break;

case 81:
//if
this.state = 88;
if (parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(true,"_containskey",(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable("_x")))).<Boolean>get().booleanValue()) { 
this.state = 83;
}if (true) break;

case 83:
//C
this.state = 84;
 BA.debugLineNum = 138;BA.debugLine="Width = Main.kvs.Get(ckey & \"_w\")";
Debug.ShouldStop(512);
parent._width = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable("_w")))));
 BA.debugLineNum = 139;BA.debugLine="Height = Main.kvs.Get(ckey & \"_h\")";
Debug.ShouldStop(1024);
parent._height = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable("_h")))));
 BA.debugLineNum = 141;BA.debugLine="Dim f As Panel";
Debug.ShouldStop(4096);
_f = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("f", _f);
 BA.debugLineNum = 142;BA.debugLine="f.Initialize(\"CanvasFrame\")";
Debug.ShouldStop(8192);
_f.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("CanvasFrame")));
 BA.debugLineNum = 143;BA.debugLine="f.Color = Colors.Black";
Debug.ShouldStop(16384);
_f.runVoidMethod ("setColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 144;BA.debugLine="boardPnl.AddView(f, Main.kvs.Get(ckey & \"_x\"),";
Debug.ShouldStop(32768);
parent.mostCurrent._boardpnl.runVoidMethod ("AddView",(Object)((_f.getObject())),(Object)(BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable("_x")))))),(Object)(BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable("_y")))))),(Object)(RemoteObject.solve(new RemoteObject[] {parent._width,parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "+",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {parent._height,parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "+",1, 1)));
 BA.debugLineNum = 145;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(65536);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 146;BA.debugLine="p.Initialize(\"CanvasPanel\")";
Debug.ShouldStop(131072);
_p.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("CanvasPanel")));
 BA.debugLineNum = 147;BA.debugLine="p.Color = Colors.White";
Debug.ShouldStop(262144);
_p.runVoidMethod ("setColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 148;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
Debug.ShouldStop(524288);
_f.runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(parent._width),(Object)(parent._height));
 BA.debugLineNum = 149;BA.debugLine="Sleep(0)";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("Sleep",corkactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "corkactivity", "activity_create"),BA.numberCast(int.class, 0));
this.state = 96;
return;
case 96:
//C
this.state = 84;
;
 BA.debugLineNum = 150;BA.debugLine="Dim cvs As B4XCanvas";
Debug.ShouldStop(2097152);
_cvs = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XCanvas");Debug.locals.put("cvs", _cvs);
 BA.debugLineNum = 151;BA.debugLine="cvs.Initialize(p)";
Debug.ShouldStop(4194304);
_cvs.runVoidMethod ("Initialize",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject()));
 BA.debugLineNum = 152;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray,";
Debug.ShouldStop(8388608);
_cvs.runVoidMethod ("DrawRect",(Object)(_cvs.runMethod(false,"getTargetRect")),(Object)(parent.mostCurrent.__c.getField(false,"Colors").getField(true,"LightGray")),(Object)(parent.mostCurrent.__c.getField(true,"False")),(Object)(BA.numberCast(float.class, parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1))))));
 BA.debugLineNum = 153;BA.debugLine="cvs.Invalidate";
Debug.ShouldStop(16777216);
_cvs.runVoidMethod ("Invalidate");
 BA.debugLineNum = 154;BA.debugLine="p.Tag = cvs";
Debug.ShouldStop(33554432);
_p.runMethod(false,"setTag",(_cvs));
 BA.debugLineNum = 155;BA.debugLine="ddc.AddDragView(f, False)";
Debug.ShouldStop(67108864);
parent.mostCurrent._ddc.runVoidMethod ("_adddragview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _f.getObject()),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 156;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).A";
Debug.ShouldStop(134217728);
parent.mostCurrent._ddc.runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place1.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place2.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place3.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place4.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place5.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place6.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place7.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place8.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place9.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place10.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place11.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place12.getObject())).runVoidMethod ("_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._deletelbl.getObject()));
 BA.debugLineNum = 157;BA.debugLine="f.Tag = ckey";
Debug.ShouldStop(268435456);
_f.runMethod(false,"setTag",(_ckey));
 BA.debugLineNum = 159;BA.debugLine="If File.Exists(File.DirInternal, ckey & \".png\")";
Debug.ShouldStop(1073741824);
if (true) break;

case 84:
//if
this.state = 87;
if (parent.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable(".png")))).<Boolean>get().booleanValue()) { 
this.state = 86;
}if (true) break;

case 86:
//C
this.state = 87;
 BA.debugLineNum = 160;BA.debugLine="Sleep(0)";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("Sleep",corkactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "corkactivity", "activity_create"),BA.numberCast(int.class, 0));
this.state = 97;
return;
case 97:
//C
this.state = 87;
;
 BA.debugLineNum = 161;BA.debugLine="Dim bmp As Bitmap = LoadBitmap(File.DirInterna";
Debug.ShouldStop(1);
_bmp = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
_bmp = parent.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.concat(_ckey,RemoteObject.createImmutable(".png"))));Debug.locals.put("bmp", _bmp);Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 162;BA.debugLine="Dim canvasRect As B4XRect = cvs.TargetRect";
Debug.ShouldStop(2);
_canvasrect = _cvs.runMethod(false,"getTargetRect");Debug.locals.put("canvasRect", _canvasrect);Debug.locals.put("canvasRect", _canvasrect);
 BA.debugLineNum = 163;BA.debugLine="cvs.DrawBitmap(bmp, canvasRect)";
Debug.ShouldStop(4);
_cvs.runVoidMethod ("DrawBitmap",(Object)((_bmp.getObject())),(Object)(_canvasrect));
 BA.debugLineNum = 164;BA.debugLine="cvs.Invalidate";
Debug.ShouldStop(8);
_cvs.runVoidMethod ("Invalidate");
 if (true) break;

case 87:
//C
this.state = 88;
;
 if (true) break;

case 88:
//C
this.state = 95;
;
 if (true) break;
if (true) break;

case 89:
//C
this.state = -1;
Debug.locals.put("i", _i);
;
 BA.debugLineNum = 168;BA.debugLine="canvasCount = loadedCanvasCount";
Debug.ShouldStop(128);
parent._canvascount = _loadedcanvascount;
 BA.debugLineNum = 169;BA.debugLine="Width = 80dip";
Debug.ShouldStop(256);
parent._width = parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));
 BA.debugLineNum = 170;BA.debugLine="Height = 60dip";
Debug.ShouldStop(512);
parent._height = parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));
 BA.debugLineNum = 171;BA.debugLine="isLoading = False";
Debug.ShouldStop(1024);
parent._isloading = parent.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 172;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,180);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 180;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
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
		Debug.PushSubsStack("Activity_Resume (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,174);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","activity_resume");}
 BA.debugLineNum = 174;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8192);
 BA.debugLineNum = 175;BA.debugLine="If canvasCount > 0 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean(">",corkactivity._canvascount,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 176;BA.debugLine="penSpnr.Visible = True";
Debug.ShouldStop(32768);
corkactivity.mostCurrent._penspnr.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"True"));
 };
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
public static void  _addcanvas(RemoteObject _x,RemoteObject _y) throws Exception{
try {
		Debug.PushSubsStack("AddCanvas (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,254);
if (RapidSub.canDelegate("addcanvas")) { b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","addcanvas", _x, _y); return;}
ResumableSub_AddCanvas rsub = new ResumableSub_AddCanvas(null,_x,_y);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_AddCanvas extends BA.ResumableSub {
public ResumableSub_AddCanvas(b4a.example.corkactivity parent,RemoteObject _x,RemoteObject _y) {
this.parent = parent;
this._x = _x;
this._y = _y;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.corkactivity parent;
RemoteObject _x;
RemoteObject _y;
RemoteObject _f = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cvs = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XCanvas");
RemoteObject _key = RemoteObject.createImmutable("");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("AddCanvas (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,254);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("x", _x);
Debug.locals.put("y", _y);
 BA.debugLineNum = 255;BA.debugLine="Dim f As Panel";
Debug.ShouldStop(1073741824);
_f = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("f", _f);
 BA.debugLineNum = 256;BA.debugLine="f.Initialize(\"CanvasFrame\")";
Debug.ShouldStop(-2147483648);
_f.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("CanvasFrame")));
 BA.debugLineNum = 257;BA.debugLine="f.Color = Colors.Black";
Debug.ShouldStop(1);
_f.runVoidMethod ("setColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 258;BA.debugLine="boardPnl.AddView(f, x, y, Width + 20dip, Height +";
Debug.ShouldStop(2);
parent.mostCurrent._boardpnl.runVoidMethod ("AddView",(Object)((_f.getObject())),(Object)(_x),(Object)(_y),(Object)(RemoteObject.solve(new RemoteObject[] {parent._width,parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "+",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {parent._height,parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "+",1, 1)));
 BA.debugLineNum = 260;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(8);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 261;BA.debugLine="p.Initialize(\"CanvasPanel\")";
Debug.ShouldStop(16);
_p.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("CanvasPanel")));
 BA.debugLineNum = 262;BA.debugLine="p.Color = Colors.White";
Debug.ShouldStop(32);
_p.runVoidMethod ("setColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 263;BA.debugLine="f.AddView(p, 10dip, 20dip, Width, Height)";
Debug.ShouldStop(64);
_f.runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(parent._width),(Object)(parent._height));
 BA.debugLineNum = 265;BA.debugLine="Sleep(0)";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("Sleep",corkactivity.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "corkactivity", "addcanvas"),BA.numberCast(int.class, 0));
this.state = 7;
return;
case 7:
//C
this.state = 1;
;
 BA.debugLineNum = 267;BA.debugLine="Dim cvs As B4XCanvas";
Debug.ShouldStop(1024);
_cvs = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XCanvas");Debug.locals.put("cvs", _cvs);
 BA.debugLineNum = 268;BA.debugLine="cvs.Initialize(p)";
Debug.ShouldStop(2048);
_cvs.runVoidMethod ("Initialize",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject()));
 BA.debugLineNum = 269;BA.debugLine="cvs.DrawRect(cvs.TargetRect, Colors.LightGray, Fa";
Debug.ShouldStop(4096);
_cvs.runVoidMethod ("DrawRect",(Object)(_cvs.runMethod(false,"getTargetRect")),(Object)(parent.mostCurrent.__c.getField(false,"Colors").getField(true,"LightGray")),(Object)(parent.mostCurrent.__c.getField(true,"False")),(Object)(BA.numberCast(float.class, parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1))))));
 BA.debugLineNum = 270;BA.debugLine="cvs.Invalidate";
Debug.ShouldStop(8192);
_cvs.runVoidMethod ("Invalidate");
 BA.debugLineNum = 271;BA.debugLine="p.Tag = cvs";
Debug.ShouldStop(16384);
_p.runMethod(false,"setTag",(_cvs));
 BA.debugLineNum = 273;BA.debugLine="ddc.AddDragView(f, False)";
Debug.ShouldStop(65536);
parent.mostCurrent._ddc.runVoidMethod ("_adddragview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _f.getObject()),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 274;BA.debugLine="ddc.AddPlaceView(place1).AddPlaceView(place2).Add";
Debug.ShouldStop(131072);
parent.mostCurrent._ddc.runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place1.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place2.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place3.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place4.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place5.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place6.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place7.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place8.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place9.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place10.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place11.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._place12.getObject())).runVoidMethod ("_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), parent.mostCurrent._deletelbl.getObject()));
 BA.debugLineNum = 275;BA.debugLine="If isLoading = False Then";
Debug.ShouldStop(262144);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",parent._isloading,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 276;BA.debugLine="Dim key As String = \"cvs_\" & canvasCount";
Debug.ShouldStop(524288);
_key = RemoteObject.concat(RemoteObject.createImmutable("cvs_"),parent._canvascount);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 277;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
Debug.ShouldStop(1048576);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))),(Object)((_x)));
 BA.debugLineNum = 278;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
Debug.ShouldStop(2097152);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))),(Object)((_y)));
 BA.debugLineNum = 279;BA.debugLine="Main.kvs.Put(key & \"_w\", Width)";
Debug.ShouldStop(4194304);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_w"))),(Object)((parent._width)));
 BA.debugLineNum = 280;BA.debugLine="Main.kvs.Put(key & \"_h\", Height)";
Debug.ShouldStop(8388608);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_h"))),(Object)((parent._height)));
 BA.debugLineNum = 281;BA.debugLine="Main.kvs.Put(\"cvs_count\", canvasCount + 1)";
Debug.ShouldStop(16777216);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("cvs_count")),(Object)((RemoteObject.solve(new RemoteObject[] {parent._canvascount,RemoteObject.createImmutable(1)}, "+",1, 1))));
 BA.debugLineNum = 282;BA.debugLine="f.Tag = key";
Debug.ShouldStop(33554432);
_f.runMethod(false,"setTag",(_key));
 BA.debugLineNum = 283;BA.debugLine="canvasCount = canvasCount + 1";
Debug.ShouldStop(67108864);
parent._canvascount = RemoteObject.solve(new RemoteObject[] {parent._canvascount,RemoteObject.createImmutable(1)}, "+",1, 1);
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 285;BA.debugLine="f.Tag = \"cvs_\" & (canvasCount - 1)";
Debug.ShouldStop(268435456);
_f.runMethod(false,"setTag",(RemoteObject.concat(RemoteObject.createImmutable("cvs_"),(RemoteObject.solve(new RemoteObject[] {parent._canvascount,RemoteObject.createImmutable(1)}, "-",1, 1)))));
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 287;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _addcbtn_click() throws Exception{
try {
		Debug.PushSubsStack("addcBtn_Click (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,458);
if (RapidSub.canDelegate("addcbtn_click")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","addcbtn_click");}
 BA.debugLineNum = 458;BA.debugLine="Private Sub addcBtn_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 459;BA.debugLine="AddCanvas(150dip, 500dip)";
Debug.ShouldStop(1024);
_addcanvas(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150))),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 500))));
 BA.debugLineNum = 460;BA.debugLine="canvasPnl.Visible = False";
Debug.ShouldStop(2048);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 461;BA.debugLine="canvaBtn.Enabled = True";
Debug.ShouldStop(4096);
corkactivity.mostCurrent._canvabtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 462;BA.debugLine="stickyBtn.Enabled = True";
Debug.ShouldStop(8192);
corkactivity.mostCurrent._stickybtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 463;BA.debugLine="imgBtn.Enabled = True";
Debug.ShouldStop(16384);
corkactivity.mostCurrent._imgbtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 464;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addnbtn_click() throws Exception{
try {
		Debug.PushSubsStack("addnBtn_Click (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,447);
if (RapidSub.canDelegate("addnbtn_click")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","addnbtn_click");}
 BA.debugLineNum = 447;BA.debugLine="Private Sub addnBtn_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 448;BA.debugLine="AddStickyNote(\"\", 150dip, 500dip)";
Debug.ShouldStop(-2147483648);
_addstickynote(BA.ObjectToString(""),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150))),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 500))));
 BA.debugLineNum = 449;BA.debugLine="notePnl.Visible = False";
Debug.ShouldStop(1);
corkactivity.mostCurrent._notepnl.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 450;BA.debugLine="R = 255";
Debug.ShouldStop(2);
corkactivity._r = BA.numberCast(int.class, 255);
 BA.debugLineNum = 451;BA.debugLine="G = 105";
Debug.ShouldStop(4);
corkactivity._g = BA.numberCast(int.class, 105);
 BA.debugLineNum = 452;BA.debugLine="B = 97";
Debug.ShouldStop(8);
corkactivity._b = BA.numberCast(int.class, 97);
 BA.debugLineNum = 453;BA.debugLine="stickyBtn.Enabled = True";
Debug.ShouldStop(16);
corkactivity.mostCurrent._stickybtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 454;BA.debugLine="canvaBtn.Enabled = True";
Debug.ShouldStop(32);
corkactivity.mostCurrent._canvabtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 455;BA.debugLine="imgBtn.Enabled = True";
Debug.ShouldStop(64);
corkactivity.mostCurrent._imgbtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 456;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addstickynote(RemoteObject _text,RemoteObject _x,RemoteObject _y) throws Exception{
try {
		Debug.PushSubsStack("AddStickyNote (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,186);
if (RapidSub.canDelegate("addstickynote")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","addstickynote", _text, _x, _y);}
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _txt = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
Debug.locals.put("Text", _text);
Debug.locals.put("x", _x);
Debug.locals.put("y", _y);
 BA.debugLineNum = 186;BA.debugLine="Sub AddStickyNote(Text As String, x As Int, y As I";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(67108864);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 188;BA.debugLine="p.Initialize(\"NotePanel\")";
Debug.ShouldStop(134217728);
_p.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("NotePanel")));
 BA.debugLineNum = 189;BA.debugLine="p.Color = Colors.RGB(R, G, B)";
Debug.ShouldStop(268435456);
_p.runVoidMethod ("setColor",corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(corkactivity._r),(Object)(corkactivity._g),(Object)(corkactivity._b)));
 BA.debugLineNum = 191;BA.debugLine="Dim txt As EditText";
Debug.ShouldStop(1073741824);
_txt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("txt", _txt);
 BA.debugLineNum = 192;BA.debugLine="txt.Initialize(\"NoteText\")";
Debug.ShouldStop(-2147483648);
_txt.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("NoteText")));
 BA.debugLineNum = 193;BA.debugLine="txt.Tag = p";
Debug.ShouldStop(1);
_txt.runMethod(false,"setTag",(_p.getObject()));
 BA.debugLineNum = 194;BA.debugLine="txt.Text = Text";
Debug.ShouldStop(2);
_txt.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_text));
 BA.debugLineNum = 195;BA.debugLine="txt.TextSize = 12";
Debug.ShouldStop(4);
_txt.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 196;BA.debugLine="txt.Background = Null";
Debug.ShouldStop(8);
_txt.runMethod(false,"setBackground",(corkactivity.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 197;BA.debugLine="txt.TextColor = Colors.Black";
Debug.ShouldStop(16);
_txt.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 198;BA.debugLine="txt.Gravity = Gravity.TOP";
Debug.ShouldStop(32);
_txt.runMethod(true,"setGravity",corkactivity.mostCurrent.__c.getField(false,"Gravity").getField(true,"TOP"));
 BA.debugLineNum = 200;BA.debugLine="p.AddView(txt, 5dip, 15dip, 90dip, 70dip)";
Debug.ShouldStop(128);
_p.runVoidMethod ("AddView",(Object)((_txt.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))));
 BA.debugLineNum = 202;BA.debugLine="boardPnl.AddView(p, x, y, 100dip, 100dip)";
Debug.ShouldStop(512);
corkactivity.mostCurrent._boardpnl.runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(_x),(Object)(_y),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))));
 BA.debugLineNum = 204;BA.debugLine="ddn.AddDragView(p, False)";
Debug.ShouldStop(2048);
corkactivity.mostCurrent._ddn.runVoidMethod ("_adddragview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject()),(Object)(corkactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 205;BA.debugLine="ddn.AddPlaceView(place1).AddPlaceView(place2).Add";
Debug.ShouldStop(4096);
corkactivity.mostCurrent._ddn.runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place1.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place2.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place3.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place4.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place5.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place6.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place7.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place8.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place9.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place10.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place11.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place12.getObject())).runVoidMethod ("_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._deletelbl.getObject()));
 BA.debugLineNum = 207;BA.debugLine="If isLoading = False Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",corkactivity._isloading,corkactivity.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 208;BA.debugLine="Dim key As String = \"note_\" & noteCount";
Debug.ShouldStop(32768);
_key = RemoteObject.concat(RemoteObject.createImmutable("note_"),corkactivity._notecount);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 209;BA.debugLine="Main.kvs.Put(key & \"_text\", Text)";
Debug.ShouldStop(65536);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_text"))),(Object)((_text)));
 BA.debugLineNum = 210;BA.debugLine="Main.kvs.Put(key & \"_color\", Colors.RGB(R, G, B)";
Debug.ShouldStop(131072);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_color"))),(Object)((corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(corkactivity._r),(Object)(corkactivity._g),(Object)(corkactivity._b)))));
 BA.debugLineNum = 211;BA.debugLine="Main.kvs.Put(key & \"_x\", x)";
Debug.ShouldStop(262144);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))),(Object)((_x)));
 BA.debugLineNum = 212;BA.debugLine="Main.kvs.Put(key & \"_y\", y)";
Debug.ShouldStop(524288);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))),(Object)((_y)));
 BA.debugLineNum = 213;BA.debugLine="Main.kvs.Put(\"note_count\", noteCount + 1)";
Debug.ShouldStop(1048576);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("note_count")),(Object)((RemoteObject.solve(new RemoteObject[] {corkactivity._notecount,RemoteObject.createImmutable(1)}, "+",1, 1))));
 BA.debugLineNum = 214;BA.debugLine="p.Tag = key";
Debug.ShouldStop(2097152);
_p.runMethod(false,"setTag",(_key));
 BA.debugLineNum = 215;BA.debugLine="noteCount = noteCount + 1";
Debug.ShouldStop(4194304);
corkactivity._notecount = RemoteObject.solve(new RemoteObject[] {corkactivity._notecount,RemoteObject.createImmutable(1)}, "+",1, 1);
 }else {
 BA.debugLineNum = 217;BA.debugLine="p.Tag = \"note_\" & (noteCount - 1)";
Debug.ShouldStop(16777216);
_p.runMethod(false,"setTag",(RemoteObject.concat(RemoteObject.createImmutable("note_"),(RemoteObject.solve(new RemoteObject[] {corkactivity._notecount,RemoteObject.createImmutable(1)}, "-",1, 1)))));
 };
 BA.debugLineNum = 219;BA.debugLine="Log(\"deleteLbl initialized: \" & (deleteLbl.IsInit";
Debug.ShouldStop(67108864);
corkactivity.mostCurrent.__c.runVoidMethod ("LogImpl","217629217",RemoteObject.concat(RemoteObject.createImmutable("deleteLbl initialized: "),(corkactivity.mostCurrent._deletelbl.runMethod(true,"IsInitialized"))),0);
 BA.debugLineNum = 220;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _canvabtn_click() throws Exception{
try {
		Debug.PushSubsStack("canvaBtn_Click (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,480);
if (RapidSub.canDelegate("canvabtn_click")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","canvabtn_click");}
 BA.debugLineNum = 480;BA.debugLine="Private Sub canvaBtn_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 481;BA.debugLine="canvasWindow(250dip, 180dip)";
Debug.ShouldStop(1);
_canvaswindow(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250))),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 482;BA.debugLine="canvasPnl.Visible = True";
Debug.ShouldStop(2);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 483;BA.debugLine="penSpnr.Visible = True";
Debug.ShouldStop(4);
corkactivity.mostCurrent._penspnr.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 484;BA.debugLine="stickyBtn.Enabled = False";
Debug.ShouldStop(8);
corkactivity.mostCurrent._stickybtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 485;BA.debugLine="canvaBtn.Enabled = False";
Debug.ShouldStop(16);
corkactivity.mostCurrent._canvabtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 486;BA.debugLine="imgBtn.Enabled = False";
Debug.ShouldStop(32);
corkactivity.mostCurrent._imgbtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 487;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _canvasdrag_placedview(RemoteObject _cdragview,RemoteObject _cplaceview) throws Exception{
try {
		Debug.PushSubsStack("CanvasDrag_PlacedView (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,610);
if (RapidSub.canDelegate("canvasdrag_placedview")) { b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","canvasdrag_placedview", _cdragview, _cplaceview); return;}
ResumableSub_CanvasDrag_PlacedView rsub = new ResumableSub_CanvasDrag_PlacedView(null,_cdragview,_cplaceview);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_CanvasDrag_PlacedView extends BA.ResumableSub {
public ResumableSub_CanvasDrag_PlacedView(b4a.example.corkactivity parent,RemoteObject _cdragview,RemoteObject _cplaceview) {
this.parent = parent;
this._cdragview = _cdragview;
this._cplaceview = _cplaceview;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.corkactivity parent;
RemoteObject _cdragview;
RemoteObject _cplaceview;
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _f = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _newcount = RemoteObject.createImmutable(0);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("CanvasDrag_PlacedView (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,610);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("cDragView", _cdragview);
Debug.locals.put("cPlaceView", _cplaceview);
 BA.debugLineNum = 611;BA.debugLine="If cPlaceView.Tag = \"delete\" Then";
Debug.ShouldStop(4);
if (true) break;

case 1:
//if
this.state = 14;
if (RemoteObject.solveBoolean("=",_cplaceview.runMethod(false,"getTag"),RemoteObject.createImmutable(("delete")))) { 
this.state = 3;
}else {
this.state = 13;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 612;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete ca";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Are you sure you want to delete canvas?")),(Object)(BA.ObjectToCharSequence("Delete Canvas")),(Object)(BA.ObjectToString("No")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Yes")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),corkactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 613;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", corkactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "corkactivity", "canvasdrag_placedview"), null);
this.state = 15;
return;
case 15:
//C
this.state = 4;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 614;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(32);
if (true) break;

case 4:
//if
this.state = 11;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 615;BA.debugLine="Dim f As Panel = cDragView";
Debug.ShouldStop(64);
_f = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_f = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), _cdragview.getObject());Debug.locals.put("f", _f);Debug.locals.put("f", _f);
 BA.debugLineNum = 616;BA.debugLine="Dim key As String = f.Tag";
Debug.ShouldStop(128);
_key = BA.ObjectToString(_f.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 617;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
Debug.ShouldStop(256);
parent.mostCurrent.__c.getField(false,"File").runVoidMethod ("Delete",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable(".png"))));
 BA.debugLineNum = 618;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
Debug.ShouldStop(512);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))));
 BA.debugLineNum = 619;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
Debug.ShouldStop(1024);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))));
 BA.debugLineNum = 620;BA.debugLine="Main.kvs.Remove(key & \"_w\")";
Debug.ShouldStop(2048);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_w"))));
 BA.debugLineNum = 621;BA.debugLine="Main.kvs.Remove(key & \"_h\")";
Debug.ShouldStop(4096);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_h"))));
 BA.debugLineNum = 622;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"cvs_count\")";
Debug.ShouldStop(8192);
_newcount = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("cvs_count"))));Debug.locals.put("newCount", _newcount);Debug.locals.put("newCount", _newcount);
 BA.debugLineNum = 623;BA.debugLine="Main.kvs.Put(\"cvs_count\", newCount - 1)";
Debug.ShouldStop(16384);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("cvs_count")),(Object)((RemoteObject.solve(new RemoteObject[] {_newcount,RemoteObject.createImmutable(1)}, "-",1, 1))));
 BA.debugLineNum = 624;BA.debugLine="canvasCount = canvasCount - 1";
Debug.ShouldStop(32768);
parent._canvascount = RemoteObject.solve(new RemoteObject[] {parent._canvascount,RemoteObject.createImmutable(1)}, "-",1, 1);
 BA.debugLineNum = 625;BA.debugLine="f.Visible = False";
Debug.ShouldStop(65536);
_f.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 626;BA.debugLine="ToastMessageShow(\"Canvas Deleted\", False)";
Debug.ShouldStop(131072);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Canvas Deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 627;BA.debugLine="If canvasCount = 0 Then";
Debug.ShouldStop(262144);
if (true) break;

case 7:
//if
this.state = 10;
if (RemoteObject.solveBoolean("=",parent._canvascount,BA.numberCast(double.class, 0))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 628;BA.debugLine="penSpnr.Visible = False";
Debug.ShouldStop(524288);
parent.mostCurrent._penspnr.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
 BA.debugLineNum = 632;BA.debugLine="Dim f As Panel = cDragView";
Debug.ShouldStop(8388608);
_f = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_f = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), _cdragview.getObject());Debug.locals.put("f", _f);Debug.locals.put("f", _f);
 BA.debugLineNum = 633;BA.debugLine="Dim key As String = f.Tag";
Debug.ShouldStop(16777216);
_key = BA.ObjectToString(_f.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 634;BA.debugLine="Main.kvs.Put(key & \"_x\", f.Left)";
Debug.ShouldStop(33554432);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))),(Object)((_f.runMethod(true,"getLeft"))));
 BA.debugLineNum = 635;BA.debugLine="Main.kvs.Put(key & \"_y\", f.Top)";
Debug.ShouldStop(67108864);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))),(Object)((_f.runMethod(true,"getTop"))));
 if (true) break;

case 14:
//C
this.state = -1;
;
 BA.debugLineNum = 637;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _msgbox_result(RemoteObject _res) throws Exception{
}
public static RemoteObject  _canvaspanel_touch(RemoteObject _action,RemoteObject _x,RemoteObject _y) throws Exception{
try {
		Debug.PushSubsStack("CanvasPanel_Touch (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,289);
if (RapidSub.canDelegate("canvaspanel_touch")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","canvaspanel_touch", _action, _x, _y);}
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cvs = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XCanvas");
RemoteObject _f = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _out = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");
Debug.locals.put("Action", _action);
Debug.locals.put("X", _x);
Debug.locals.put("Y", _y);
 BA.debugLineNum = 289;BA.debugLine="Sub CanvasPanel_Touch (Action As Int, X As Float,";
Debug.ShouldStop(1);
 BA.debugLineNum = 290;BA.debugLine="Dim p As Panel = Sender";
Debug.ShouldStop(2);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_p = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), corkactivity.mostCurrent.__c.runMethod(false,"Sender",corkactivity.mostCurrent.activityBA));Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 291;BA.debugLine="Dim cvs As B4XCanvas = p.Tag";
Debug.ShouldStop(4);
_cvs = (_p.runMethod(false,"getTag"));Debug.locals.put("cvs", _cvs);Debug.locals.put("cvs", _cvs);
 BA.debugLineNum = 292;BA.debugLine="Select Action";
Debug.ShouldStop(8);
switch (BA.switchObjectToInt(_action,corkactivity.mostCurrent._activity.getField(true,"ACTION_DOWN"),corkactivity.mostCurrent._activity.getField(true,"ACTION_MOVE"))) {
case 0: {
 BA.debugLineNum = 294;BA.debugLine="LastX = X";
Debug.ShouldStop(32);
corkactivity._lastx = _x;
 BA.debugLineNum = 295;BA.debugLine="LastY = Y";
Debug.ShouldStop(64);
corkactivity._lasty = _y;
 break; }
case 1: {
 BA.debugLineNum = 297;BA.debugLine="cvs.DrawLine(LastX, LastY, X, Y, Colors.RGB(R2,";
Debug.ShouldStop(256);
_cvs.runVoidMethod ("DrawLine",(Object)(corkactivity._lastx),(Object)(corkactivity._lasty),(Object)(_x),(Object)(_y),(Object)(corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(corkactivity._r2),(Object)(corkactivity._g2),(Object)(corkactivity._b2))),(Object)(BA.numberCast(float.class, corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3))))));
 BA.debugLineNum = 298;BA.debugLine="cvs.Invalidate";
Debug.ShouldStop(512);
_cvs.runVoidMethod ("Invalidate");
 BA.debugLineNum = 299;BA.debugLine="LastX = X";
Debug.ShouldStop(1024);
corkactivity._lastx = _x;
 BA.debugLineNum = 300;BA.debugLine="LastY = Y";
Debug.ShouldStop(2048);
corkactivity._lasty = _y;
 BA.debugLineNum = 301;BA.debugLine="Dim f As Panel = p.Parent";
Debug.ShouldStop(4096);
_f = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_f = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), _p.runMethod(false,"getParent"));Debug.locals.put("f", _f);Debug.locals.put("f", _f);
 BA.debugLineNum = 302;BA.debugLine="Dim key As String = f.Tag";
Debug.ShouldStop(8192);
_key = BA.ObjectToString(_f.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 303;BA.debugLine="Dim out As OutputStream";
Debug.ShouldStop(16384);
_out = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");Debug.locals.put("out", _out);
 BA.debugLineNum = 304;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \"";
Debug.ShouldStop(32768);
_out = corkactivity.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenOutput",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable(".png"))),(Object)(corkactivity.mostCurrent.__c.getField(true,"False")));Debug.locals.put("out", _out);
 BA.debugLineNum = 305;BA.debugLine="cvs.CreateBitmap.WriteToStream(out, 100, \"PNG\")";
Debug.ShouldStop(65536);
_cvs.runMethod(false,"CreateBitmap").runVoidMethod ("WriteToStream",(Object)((_out.getObject())),(Object)(BA.numberCast(int.class, 100)),(Object)(BA.getEnumFromString(BA.getDeviceClass("android.graphics.Bitmap.CompressFormat"),RemoteObject.createImmutable("PNG"))));
 BA.debugLineNum = 306;BA.debugLine="out.Close";
Debug.ShouldStop(131072);
_out.runVoidMethod ("Close");
 break; }
}
;
 BA.debugLineNum = 308;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _canvaswindow(RemoteObject _pw,RemoteObject _ph) throws Exception{
try {
		Debug.PushSubsStack("canvasWindow (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,379);
if (RapidSub.canDelegate("canvaswindow")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","canvaswindow", _pw, _ph);}
RemoteObject _addcbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _bd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("pW", _pw);
Debug.locals.put("pH", _ph);
 BA.debugLineNum = 379;BA.debugLine="Private Sub canvasWindow(pW As Int, pH As Int)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 380;BA.debugLine="canvasPnl = xui.CreatePanel(\"canvasPanel\")";
Debug.ShouldStop(134217728);
corkactivity.mostCurrent._canvaspnl = corkactivity._xui.runMethod(false,"CreatePanel",corkactivity.processBA,(Object)(RemoteObject.createImmutable("canvasPanel")));
 BA.debugLineNum = 381;BA.debugLine="Activity.AddView(canvasPnl, 100dip, 225dip, pW, p";
Debug.ShouldStop(268435456);
corkactivity.mostCurrent._activity.runVoidMethod ("AddView",(Object)((corkactivity.mostCurrent._canvaspnl.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 225)))),(Object)(_pw),(Object)(_ph));
 BA.debugLineNum = 385;BA.debugLine="sizeSpnr.Initialize(\"sizeSpnr\")";
Debug.ShouldStop(1);
corkactivity.mostCurrent._sizespnr.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("sizeSpnr")));
 BA.debugLineNum = 386;BA.debugLine="sizeSpnr.AddAll(Array As String(\"1x1\", \"2x1\", \"1x";
Debug.ShouldStop(2);
corkactivity.mostCurrent._sizespnr.runVoidMethod ("AddAll",(Object)(corkactivity.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {6},new Object[] {BA.ObjectToString("1x1"),BA.ObjectToString("2x1"),BA.ObjectToString("1x2"),BA.ObjectToString("2x2"),BA.ObjectToString("3x2"),RemoteObject.createImmutable("2x3")})))));
 BA.debugLineNum = 387;BA.debugLine="canvasPnl.AddView(sizeSpnr, 10dip, 10dip, pW - 20";
Debug.ShouldStop(4);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("AddView",(Object)((corkactivity.mostCurrent._sizespnr.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {_pw,corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 389;BA.debugLine="Dim addcBtn As Button";
Debug.ShouldStop(16);
_addcbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("addcBtn", _addcbtn);
 BA.debugLineNum = 390;BA.debugLine="addcBtn.Initialize(\"addcBtn\")";
Debug.ShouldStop(32);
_addcbtn.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addcBtn")));
 BA.debugLineNum = 391;BA.debugLine="addcBtn.Text = \"Add Canvas\"";
Debug.ShouldStop(64);
_addcbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Add Canvas"));
 BA.debugLineNum = 392;BA.debugLine="canvasPnl.AddView(addcBtn, 10dip, 60dip, (pW / 2)";
Debug.ShouldStop(128);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("AddView",(Object)((_addcbtn.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_pw,RemoteObject.createImmutable(2)}, "/",0, 0)),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))}, "-",1, 0))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 394;BA.debugLine="canvasPnl.Enabled = False";
Debug.ShouldStop(512);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 395;BA.debugLine="canvasPnl.Visible = False";
Debug.ShouldStop(1024);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 397;BA.debugLine="Dim bd As BitmapDrawable";
Debug.ShouldStop(4096);
_bd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd", _bd);
 BA.debugLineNum = 398;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(8192);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 399;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(16384);
switch (BA.switchObjectToInt(corkactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 401;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(65536);
if (corkactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 402;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(131072);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 403;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(187,";
Debug.ShouldStop(262144);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 187)),(Object)(BA.numberCast(int.class, 213)),(Object)(BA.numberCast(int.class, 218)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_Black")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 404;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
Debug.ShouldStop(524288);
_cd.runVoidMethod ("Initialize",(Object)(corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 59)),(Object)(BA.numberCast(int.class, 117)),(Object)(BA.numberCast(int.class, 151)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 405;BA.debugLine="addcBtn.Background = cd";
Debug.ShouldStop(1048576);
_addcbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 406;BA.debugLine="addcBtn.TextColor = Colors.White";
Debug.ShouldStop(2097152);
_addcbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 408;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(8388608);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 409;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(227,";
Debug.ShouldStop(16777216);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 227)),(Object)(BA.numberCast(int.class, 193)),(Object)(BA.numberCast(int.class, 159)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_Black")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 410;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
Debug.ShouldStop(33554432);
_cd.runVoidMethod ("Initialize",(Object)(corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 411;BA.debugLine="addcBtn.Background = cd";
Debug.ShouldStop(67108864);
_addcbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 412;BA.debugLine="addcBtn.TextColor = Colors.White";
Debug.ShouldStop(134217728);
_addcbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 415;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(1073741824);
if (corkactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 416;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(-2147483648);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 417;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(27,";
Debug.ShouldStop(1);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 27)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 161)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 16)),(Object)(BA.numberCast(int.class, 26)),(Object)(BA.numberCast(int.class, 95)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 418;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"dark";
Debug.ShouldStop(2);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("dark Skeumorphic Button blue.png"))).getObject())));
 BA.debugLineNum = 419;BA.debugLine="addcBtn.Background = bd";
Debug.ShouldStop(4);
_addcbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 420;BA.debugLine="addcBtn.TextColor = Colors.White";
Debug.ShouldStop(8);
_addcbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 422;BA.debugLine="canvasPnl.Color = xui.Color_RGB(255, 255, 255)";
Debug.ShouldStop(32);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 423;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(51,";
Debug.ShouldStop(64);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 51)),(Object)(BA.numberCast(int.class, 187)),(Object)(BA.numberCast(int.class, 152)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_Black")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 424;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
Debug.ShouldStop(128);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Skeumorphic Button 03.png"))).getObject())));
 BA.debugLineNum = 425;BA.debugLine="addcBtn.Background = bd";
Debug.ShouldStop(256);
_addcbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 426;BA.debugLine="addcBtn.TextColor = Colors.White";
Debug.ShouldStop(512);
_addcbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 2: {
 BA.debugLineNum = 429;BA.debugLine="addcBtn.Typeface = pixeltf";
Debug.ShouldStop(4096);
_addcbtn.runMethod(false,"setTypeface",(corkactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 430;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(8192);
if (corkactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 431;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(16384);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 432;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(37,";
Debug.ShouldStop(32768);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 37)),(Object)(BA.numberCast(int.class, 57)),(Object)(BA.numberCast(int.class, 94)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_White")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 433;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(65536);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 434;BA.debugLine="addcBtn.Background = bd";
Debug.ShouldStop(131072);
_addcbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 435;BA.debugLine="addcBtn.TextColor = Colors.White";
Debug.ShouldStop(262144);
_addcbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 437;BA.debugLine="canvasPnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(1048576);
corkactivity.mostCurrent._canvaspnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 438;BA.debugLine="canvasPnl.SetColorAndBorder(xui.Color_RGB(234,";
Debug.ShouldStop(2097152);
corkactivity.mostCurrent._canvaspnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 234)),(Object)(BA.numberCast(int.class, 212)),(Object)(BA.numberCast(int.class, 179)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 79)),(Object)(BA.numberCast(int.class, 46)),(Object)(BA.numberCast(int.class, 46)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 439;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(4194304);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 440;BA.debugLine="addcBtn.Background = bd";
Debug.ShouldStop(8388608);
_addcbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 441;BA.debugLine="addcBtn.TextColor = Colors.White";
Debug.ShouldStop(16777216);
_addcbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
}
;
 BA.debugLineNum = 445;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
		Debug.PushSubsStack("CC_Result (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,229);
if (RapidSub.canDelegate("cc_result")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","cc_result", _success, _dir, _filename);}
RemoteObject _bmp = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _out = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");
Debug.locals.put("Success", _success);
Debug.locals.put("Dir", _dir);
Debug.locals.put("FileName", _filename);
 BA.debugLineNum = 229;BA.debugLine="Sub CC_Result (Success As Boolean, Dir As String,";
Debug.ShouldStop(16);
 BA.debugLineNum = 230;BA.debugLine="If Success Then";
Debug.ShouldStop(32);
if (_success.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 231;BA.debugLine="imgView.Initialize(\"ImgView\")";
Debug.ShouldStop(64);
corkactivity.mostCurrent._imgview.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("ImgView")));
 BA.debugLineNum = 232;BA.debugLine="Dim bmp As Bitmap";
Debug.ShouldStop(128);
_bmp = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 233;BA.debugLine="bmp = LoadBitmapResize(Dir, FileName, 100dip, 10";
Debug.ShouldStop(256);
_bmp = corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmapResize",(Object)(_dir),(Object)(_filename),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(corkactivity.mostCurrent.__c.getField(true,"True")));Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 234;BA.debugLine="imgView.Bitmap = bmp";
Debug.ShouldStop(512);
corkactivity.mostCurrent._imgview.runMethod(false,"setBitmap",(_bmp.getObject()));
 BA.debugLineNum = 235;BA.debugLine="boardPnl.AddView(imgView, 150dip, 500dip, 100dip";
Debug.ShouldStop(1024);
corkactivity.mostCurrent._boardpnl.runVoidMethod ("AddView",(Object)((corkactivity.mostCurrent._imgview.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 500)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))));
 BA.debugLineNum = 236;BA.debugLine="ddi.AddDragView(imgView, False)";
Debug.ShouldStop(2048);
corkactivity.mostCurrent._ddi.runVoidMethod ("_adddragview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._imgview.getObject()),(Object)(corkactivity.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 237;BA.debugLine="ddi.AddPlaceView(place1).AddPlaceView(place2).Ad";
Debug.ShouldStop(4096);
corkactivity.mostCurrent._ddi.runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place1.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place2.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place3.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place4.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place5.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place6.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place7.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place8.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place9.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place10.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place11.getObject())).runMethod(false,"_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._place12.getObject())).runVoidMethod ("_addplaceview",RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), corkactivity.mostCurrent._deletelbl.getObject()));
 BA.debugLineNum = 238;BA.debugLine="Dim key As String = \"img_\" & imgCount";
Debug.ShouldStop(8192);
_key = RemoteObject.concat(RemoteObject.createImmutable("img_"),corkactivity._imgcount);Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 239;BA.debugLine="Dim out As OutputStream";
Debug.ShouldStop(16384);
_out = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");Debug.locals.put("out", _out);
 BA.debugLineNum = 240;BA.debugLine="out = File.OpenOutput(File.DirInternal, key & \".";
Debug.ShouldStop(32768);
_out = corkactivity.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenOutput",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable(".png"))),(Object)(corkactivity.mostCurrent.__c.getField(true,"False")));Debug.locals.put("out", _out);
 BA.debugLineNum = 241;BA.debugLine="bmp.WriteToStream(out, 100, \"PNG\")";
Debug.ShouldStop(65536);
_bmp.runVoidMethod ("WriteToStream",(Object)((_out.getObject())),(Object)(BA.numberCast(int.class, 100)),(Object)(BA.getEnumFromString(BA.getDeviceClass("android.graphics.Bitmap.CompressFormat"),RemoteObject.createImmutable("PNG"))));
 BA.debugLineNum = 242;BA.debugLine="out.Close";
Debug.ShouldStop(131072);
_out.runVoidMethod ("Close");
 BA.debugLineNum = 243;BA.debugLine="Main.kvs.Put(key & \"_file\", key & \".png\")";
Debug.ShouldStop(262144);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_file"))),(Object)((RemoteObject.concat(_key,RemoteObject.createImmutable(".png")))));
 BA.debugLineNum = 244;BA.debugLine="Main.kvs.Put(key & \"_x\", 150dip)";
Debug.ShouldStop(524288);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))),(Object)((corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150))))));
 BA.debugLineNum = 245;BA.debugLine="Main.kvs.Put(key & \"_y\", 500dip)";
Debug.ShouldStop(1048576);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))),(Object)((corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 500))))));
 BA.debugLineNum = 246;BA.debugLine="Main.kvs.Put(\"img_count\", imgCount + 1)";
Debug.ShouldStop(2097152);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("img_count")),(Object)((RemoteObject.solve(new RemoteObject[] {corkactivity._imgcount,RemoteObject.createImmutable(1)}, "+",1, 1))));
 BA.debugLineNum = 247;BA.debugLine="imgView.Tag = key";
Debug.ShouldStop(4194304);
corkactivity.mostCurrent._imgview.runMethod(false,"setTag",(_key));
 BA.debugLineNum = 248;BA.debugLine="imgCount = imgCount + 1";
Debug.ShouldStop(8388608);
corkactivity._imgcount = RemoteObject.solve(new RemoteObject[] {corkactivity._imgcount,RemoteObject.createImmutable(1)}, "+",1, 1);
 }else {
 BA.debugLineNum = 250;BA.debugLine="ToastMessageShow(\"No image selected\", False)";
Debug.ShouldStop(33554432);
corkactivity.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("No image selected")),(Object)(corkactivity.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 252;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _colorsspnr_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("colorsSpnr_ItemClick (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,514);
if (RapidSub.canDelegate("colorsspnr_itemclick")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","colorsspnr_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 514;BA.debugLine="Private Sub colorsSpnr_ItemClick (Position As Int,";
Debug.ShouldStop(2);
 BA.debugLineNum = 515;BA.debugLine="Select Position";
Debug.ShouldStop(4);
switch (BA.switchObjectToInt(_position,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 517;BA.debugLine="R = 255";
Debug.ShouldStop(16);
corkactivity._r = BA.numberCast(int.class, 255);
 BA.debugLineNum = 518;BA.debugLine="G = 105";
Debug.ShouldStop(32);
corkactivity._g = BA.numberCast(int.class, 105);
 BA.debugLineNum = 519;BA.debugLine="B = 97";
Debug.ShouldStop(64);
corkactivity._b = BA.numberCast(int.class, 97);
 break; }
case 1: {
 BA.debugLineNum = 521;BA.debugLine="R = 155";
Debug.ShouldStop(256);
corkactivity._r = BA.numberCast(int.class, 155);
 BA.debugLineNum = 522;BA.debugLine="G = 190";
Debug.ShouldStop(512);
corkactivity._g = BA.numberCast(int.class, 190);
 BA.debugLineNum = 523;BA.debugLine="B = 237";
Debug.ShouldStop(1024);
corkactivity._b = BA.numberCast(int.class, 237);
 break; }
case 2: {
 BA.debugLineNum = 525;BA.debugLine="R = 248";
Debug.ShouldStop(4096);
corkactivity._r = BA.numberCast(int.class, 248);
 BA.debugLineNum = 526;BA.debugLine="G = 241";
Debug.ShouldStop(8192);
corkactivity._g = BA.numberCast(int.class, 241);
 BA.debugLineNum = 527;BA.debugLine="B = 174";
Debug.ShouldStop(16384);
corkactivity._b = BA.numberCast(int.class, 174);
 break; }
}
;
 BA.debugLineNum = 529;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private boardPnl As Panel";
corkactivity.mostCurrent._boardpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private addPnl As Panel";
corkactivity.mostCurrent._addpnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private canvaBtn As Button";
corkactivity.mostCurrent._canvabtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private imgBtn As Button";
corkactivity.mostCurrent._imgbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private imgView As ImageView";
corkactivity.mostCurrent._imgview = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private stickyBtn As Button";
corkactivity.mostCurrent._stickybtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private notePnl As B4XView";
corkactivity.mostCurrent._notepnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private canvasPnl As B4XView";
corkactivity.mostCurrent._canvaspnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private LastX, LastY As Float";
corkactivity._lastx = RemoteObject.createImmutable(0f);
corkactivity._lasty = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 25;BA.debugLine="Private place1 As Label";
corkactivity.mostCurrent._place1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private place2 As Label";
corkactivity.mostCurrent._place2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private place3 As Label";
corkactivity.mostCurrent._place3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private place4 As Label";
corkactivity.mostCurrent._place4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private place5 As Label";
corkactivity.mostCurrent._place5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private place6 As Label";
corkactivity.mostCurrent._place6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private place7 As Label";
corkactivity.mostCurrent._place7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private place8 As Label";
corkactivity.mostCurrent._place8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private place9 As Label";
corkactivity.mostCurrent._place9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private place10 As Label";
corkactivity.mostCurrent._place10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private place11 As Label";
corkactivity.mostCurrent._place11 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private place12 As Label";
corkactivity.mostCurrent._place12 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private deleteLbl As Label";
corkactivity.mostCurrent._deletelbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private R = 255, G = 105, B = 97 As Int";
corkactivity._r = BA.numberCast(int.class, 255);
corkactivity._g = BA.numberCast(int.class, 105);
corkactivity._b = BA.numberCast(int.class, 97);
 //BA.debugLineNum = 39;BA.debugLine="Private R2, G2, B2 As Int";
corkactivity._r2 = RemoteObject.createImmutable(0);
corkactivity._g2 = RemoteObject.createImmutable(0);
corkactivity._b2 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 40;BA.debugLine="Private penSpnr As Spinner";
corkactivity.mostCurrent._penspnr = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private sizeSpnr As Spinner";
corkactivity.mostCurrent._sizespnr = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private Width As Int";
corkactivity._width = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 43;BA.debugLine="Private Height As Int";
corkactivity._height = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 45;BA.debugLine="Dim ddn As DragDropView";
corkactivity.mostCurrent._ddn = RemoteObject.createNew ("b4a.example.dragdropview");
 //BA.debugLineNum = 46;BA.debugLine="Dim ddi As DragDropView";
corkactivity.mostCurrent._ddi = RemoteObject.createNew ("b4a.example.dragdropview");
 //BA.debugLineNum = 47;BA.debugLine="Dim ddc As DragDropView";
corkactivity.mostCurrent._ddc = RemoteObject.createNew ("b4a.example.dragdropview");
 //BA.debugLineNum = 49;BA.debugLine="Private noteCount As Int = 0";
corkactivity._notecount = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 50;BA.debugLine="Private imgCount As Int = 0";
corkactivity._imgcount = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 51;BA.debugLine="Private canvasCount As Int = 0";
corkactivity._canvascount = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 52;BA.debugLine="Private isLoading As Boolean = False";
corkactivity._isloading = corkactivity.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 54;BA.debugLine="Dim pixeltf As Typeface";
corkactivity.mostCurrent._pixeltf = RemoteObject.createNew ("anywheresoftware.b4a.keywords.constants.TypefaceWrapper");
 //BA.debugLineNum = 55;BA.debugLine="pixeltf = Typeface.LoadFromAssets(\"minecraft.ttf\"";
corkactivity.mostCurrent._pixeltf = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.constants.TypefaceWrapper"), corkactivity.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"LoadFromAssets",(Object)(RemoteObject.createImmutable("minecraft.ttf"))));
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _imgbtn_click() throws Exception{
try {
		Debug.PushSubsStack("imgBtn_Click (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,476);
if (RapidSub.canDelegate("imgbtn_click")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","imgbtn_click");}
 BA.debugLineNum = 476;BA.debugLine="Private Sub imgBtn_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 477;BA.debugLine="imgPicker.Show(\"image/*\", \"Select a Photo\")";
Debug.ShouldStop(268435456);
corkactivity._imgpicker.runVoidMethod ("Show",corkactivity.processBA,(Object)(BA.ObjectToString("image/*")),(Object)(RemoteObject.createImmutable("Select a Photo")));
 BA.debugLineNum = 478;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _imgdrag_placedview(RemoteObject _idragview,RemoteObject _iplaceview) throws Exception{
try {
		Debug.PushSubsStack("ImgDrag_PlacedView (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,586);
if (RapidSub.canDelegate("imgdrag_placedview")) { b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","imgdrag_placedview", _idragview, _iplaceview); return;}
ResumableSub_ImgDrag_PlacedView rsub = new ResumableSub_ImgDrag_PlacedView(null,_idragview,_iplaceview);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_ImgDrag_PlacedView extends BA.ResumableSub {
public ResumableSub_ImgDrag_PlacedView(b4a.example.corkactivity parent,RemoteObject _idragview,RemoteObject _iplaceview) {
this.parent = parent;
this._idragview = _idragview;
this._iplaceview = _iplaceview;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.corkactivity parent;
RemoteObject _idragview;
RemoteObject _iplaceview;
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _iv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _newcount = RemoteObject.createImmutable(0);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("ImgDrag_PlacedView (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,586);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("iDragView", _idragview);
Debug.locals.put("iPlaceView", _iplaceview);
 BA.debugLineNum = 587;BA.debugLine="If iPlaceView.Tag = \"delete\" Then";
Debug.ShouldStop(1024);
if (true) break;

case 1:
//if
this.state = 10;
if (RemoteObject.solveBoolean("=",_iplaceview.runMethod(false,"getTag"),RemoteObject.createImmutable(("delete")))) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 588;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete im";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Are you sure you want to delete image?")),(Object)(BA.ObjectToCharSequence("Delete Image")),(Object)(BA.ObjectToString("No")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Yes")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),corkactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 589;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", corkactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "corkactivity", "imgdrag_placedview"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 590;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(8192);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 591;BA.debugLine="Dim iv As ImageView = iDragView";
Debug.ShouldStop(16384);
_iv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
_iv = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ImageViewWrapper"), _idragview.getObject());Debug.locals.put("iv", _iv);Debug.locals.put("iv", _iv);
 BA.debugLineNum = 592;BA.debugLine="Dim key As String = iv.Tag";
Debug.ShouldStop(32768);
_key = BA.ObjectToString(_iv.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 593;BA.debugLine="File.Delete(File.DirInternal, key & \".png\")";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.getField(false,"File").runVoidMethod ("Delete",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable(".png"))));
 BA.debugLineNum = 594;BA.debugLine="Main.kvs.Remove(key & \"_file\")";
Debug.ShouldStop(131072);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_file"))));
 BA.debugLineNum = 595;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
Debug.ShouldStop(262144);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))));
 BA.debugLineNum = 596;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
Debug.ShouldStop(524288);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))));
 BA.debugLineNum = 597;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"img_count\")";
Debug.ShouldStop(1048576);
_newcount = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("img_count"))));Debug.locals.put("newCount", _newcount);Debug.locals.put("newCount", _newcount);
 BA.debugLineNum = 598;BA.debugLine="Main.kvs.Put(\"img_count\", newCount - 1)";
Debug.ShouldStop(2097152);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("img_count")),(Object)((RemoteObject.solve(new RemoteObject[] {_newcount,RemoteObject.createImmutable(1)}, "-",1, 1))));
 BA.debugLineNum = 599;BA.debugLine="iv.Visible = False";
Debug.ShouldStop(4194304);
_iv.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 600;BA.debugLine="ToastMessageShow(\"Image Deleted\", False)";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Image Deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 603;BA.debugLine="Dim iv As ImageView = iDragView";
Debug.ShouldStop(67108864);
_iv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
_iv = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ImageViewWrapper"), _idragview.getObject());Debug.locals.put("iv", _iv);Debug.locals.put("iv", _iv);
 BA.debugLineNum = 604;BA.debugLine="Dim key As String = iv.Tag";
Debug.ShouldStop(134217728);
_key = BA.ObjectToString(_iv.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 605;BA.debugLine="Main.kvs.Put(key & \"_x\", iv.Left)";
Debug.ShouldStop(268435456);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))),(Object)((_iv.runMethod(true,"getLeft"))));
 BA.debugLineNum = 606;BA.debugLine="Main.kvs.Put(key & \"_y\", iv.Top)";
Debug.ShouldStop(536870912);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))),(Object)((_iv.runMethod(true,"getTop"))));
 if (true) break;

case 10:
//C
this.state = -1;
;
 BA.debugLineNum = 608;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _notedrag_placedview(RemoteObject _ndragview,RemoteObject _nplaceview) throws Exception{
try {
		Debug.PushSubsStack("NoteDrag_PlacedView (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,562);
if (RapidSub.canDelegate("notedrag_placedview")) { b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","notedrag_placedview", _ndragview, _nplaceview); return;}
ResumableSub_NoteDrag_PlacedView rsub = new ResumableSub_NoteDrag_PlacedView(null,_ndragview,_nplaceview);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_NoteDrag_PlacedView extends BA.ResumableSub {
public ResumableSub_NoteDrag_PlacedView(b4a.example.corkactivity parent,RemoteObject _ndragview,RemoteObject _nplaceview) {
this.parent = parent;
this._ndragview = _ndragview;
this._nplaceview = _nplaceview;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.corkactivity parent;
RemoteObject _ndragview;
RemoteObject _nplaceview;
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
RemoteObject _newcount = RemoteObject.createImmutable(0);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("NoteDrag_PlacedView (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,562);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("nDragView", _ndragview);
Debug.locals.put("nPlaceView", _nplaceview);
 BA.debugLineNum = 563;BA.debugLine="If nPlaceView.Tag = \"delete\" Then";
Debug.ShouldStop(262144);
if (true) break;

case 1:
//if
this.state = 10;
if (RemoteObject.solveBoolean("=",_nplaceview.runMethod(false,"getTag"),RemoteObject.createImmutable(("delete")))) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 564;BA.debugLine="Msgbox2Async(\"Are you sure you want to delete no";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Are you sure you want to delete note?")),(Object)(BA.ObjectToCharSequence("Delete Note")),(Object)(BA.ObjectToString("No")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Yes")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),corkactivity.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 565;BA.debugLine="Wait For Msgbox_Result (res As Int)";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", corkactivity.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "corkactivity", "notedrag_placedview"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_res = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("res", _res);
;
 BA.debugLineNum = 566;BA.debugLine="If res = DialogResponse.NEGATIVE Then";
Debug.ShouldStop(2097152);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"NEGATIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 567;BA.debugLine="Dim p As Panel = nDragView";
Debug.ShouldStop(4194304);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_p = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), _ndragview.getObject());Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 568;BA.debugLine="Dim key As String = p.Tag";
Debug.ShouldStop(8388608);
_key = BA.ObjectToString(_p.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 569;BA.debugLine="Main.kvs.Remove(key & \"_text\")";
Debug.ShouldStop(16777216);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_text"))));
 BA.debugLineNum = 570;BA.debugLine="Main.kvs.Remove(key & \"_color\")";
Debug.ShouldStop(33554432);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_color"))));
 BA.debugLineNum = 571;BA.debugLine="Main.kvs.Remove(key & \"_x\")";
Debug.ShouldStop(67108864);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))));
 BA.debugLineNum = 572;BA.debugLine="Main.kvs.Remove(key & \"_y\")";
Debug.ShouldStop(134217728);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_remove",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))));
 BA.debugLineNum = 573;BA.debugLine="Dim newCount As Int = Main.kvs.Get(\"note_count\"";
Debug.ShouldStop(268435456);
_newcount = BA.numberCast(int.class, parent.mostCurrent._main._kvs /*RemoteObject*/ .runMethod(false,"_get",(Object)(RemoteObject.createImmutable("note_count"))));Debug.locals.put("newCount", _newcount);Debug.locals.put("newCount", _newcount);
 BA.debugLineNum = 574;BA.debugLine="Main.kvs.Put(\"note_count\", newCount - 1)";
Debug.ShouldStop(536870912);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("note_count")),(Object)((RemoteObject.solve(new RemoteObject[] {_newcount,RemoteObject.createImmutable(1)}, "-",1, 1))));
 BA.debugLineNum = 575;BA.debugLine="p.Visible = False";
Debug.ShouldStop(1073741824);
_p.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 576;BA.debugLine="ToastMessageShow(\"Note Deleted\", False)";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Note Deleted")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 579;BA.debugLine="Dim p As Panel = nDragView";
Debug.ShouldStop(4);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_p = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), _ndragview.getObject());Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 580;BA.debugLine="Dim key As String = p.Tag";
Debug.ShouldStop(8);
_key = BA.ObjectToString(_p.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 581;BA.debugLine="Main.kvs.Put(key & \"_x\", p.Left)";
Debug.ShouldStop(16);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_x"))),(Object)((_p.runMethod(true,"getLeft"))));
 BA.debugLineNum = 582;BA.debugLine="Main.kvs.Put(key & \"_y\", p.Top)";
Debug.ShouldStop(32);
parent.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_y"))),(Object)((_p.runMethod(true,"getTop"))));
 if (true) break;

case 10:
//C
this.state = -1;
;
 BA.debugLineNum = 584;BA.debugLine="End Sub";
Debug.ShouldStop(128);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _notetext_textchanged(RemoteObject _old,RemoteObject _new) throws Exception{
try {
		Debug.PushSubsStack("NoteText_TextChanged (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,222);
if (RapidSub.canDelegate("notetext_textchanged")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","notetext_textchanged", _old, _new);}
RemoteObject _txt = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _key = RemoteObject.createImmutable("");
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 222;BA.debugLine="Sub NoteText_TextChanged(Old As String, New As Str";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 223;BA.debugLine="Dim txt As EditText = Sender";
Debug.ShouldStop(1073741824);
_txt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
_txt = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.EditTextWrapper"), corkactivity.mostCurrent.__c.runMethod(false,"Sender",corkactivity.mostCurrent.activityBA));Debug.locals.put("txt", _txt);Debug.locals.put("txt", _txt);
 BA.debugLineNum = 224;BA.debugLine="Dim p As Panel = txt.Tag";
Debug.ShouldStop(-2147483648);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_p = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), _txt.runMethod(false,"getTag"));Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 225;BA.debugLine="Dim key As String = p.Tag";
Debug.ShouldStop(1);
_key = BA.ObjectToString(_p.runMethod(false,"getTag"));Debug.locals.put("key", _key);Debug.locals.put("key", _key);
 BA.debugLineNum = 226;BA.debugLine="Main.kvs.Put(key & \"_text\", New)";
Debug.ShouldStop(2);
corkactivity.mostCurrent._main._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(RemoteObject.concat(_key,RemoteObject.createImmutable("_text"))),(Object)((_new)));
 BA.debugLineNum = 227;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _notewindow(RemoteObject _pw,RemoteObject _ph) throws Exception{
try {
		Debug.PushSubsStack("noteWindow (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,312);
if (RapidSub.canDelegate("notewindow")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","notewindow", _pw, _ph);}
RemoteObject _colorsspnr = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
RemoteObject _addnbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _bd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("pW", _pw);
Debug.locals.put("pH", _ph);
 BA.debugLineNum = 312;BA.debugLine="Private Sub noteWindow(pW As Int, pH As Int)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 313;BA.debugLine="notePnl = xui.CreatePanel(\"notePnl\")";
Debug.ShouldStop(16777216);
corkactivity.mostCurrent._notepnl = corkactivity._xui.runMethod(false,"CreatePanel",corkactivity.processBA,(Object)(RemoteObject.createImmutable("notePnl")));
 BA.debugLineNum = 314;BA.debugLine="Activity.AddView(notePnl, 100dip, 225dip, pW, pH)";
Debug.ShouldStop(33554432);
corkactivity.mostCurrent._activity.runVoidMethod ("AddView",(Object)((corkactivity.mostCurrent._notepnl.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 225)))),(Object)(_pw),(Object)(_ph));
 BA.debugLineNum = 317;BA.debugLine="Dim colorsSpnr As Spinner";
Debug.ShouldStop(268435456);
_colorsspnr = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");Debug.locals.put("colorsSpnr", _colorsspnr);
 BA.debugLineNum = 318;BA.debugLine="colorsSpnr.Initialize(\"colorsSpnr\")";
Debug.ShouldStop(536870912);
_colorsspnr.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("colorsSpnr")));
 BA.debugLineNum = 319;BA.debugLine="colorsSpnr.AddAll(Array As String(\"Red\", \"Blue\",";
Debug.ShouldStop(1073741824);
_colorsspnr.runVoidMethod ("AddAll",(Object)(corkactivity.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {3},new Object[] {BA.ObjectToString("Red"),BA.ObjectToString("Blue"),RemoteObject.createImmutable("Yellow")})))));
 BA.debugLineNum = 320;BA.debugLine="notePnl.AddView(colorsSpnr, 10dip, 10dip, pW - 20";
Debug.ShouldStop(-2147483648);
corkactivity.mostCurrent._notepnl.runVoidMethod ("AddView",(Object)((_colorsspnr.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {_pw,corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 322;BA.debugLine="Dim addnBtn As Button";
Debug.ShouldStop(2);
_addnbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("addnBtn", _addnbtn);
 BA.debugLineNum = 323;BA.debugLine="addnBtn.Initialize(\"addnBtn\")";
Debug.ShouldStop(4);
_addnbtn.runVoidMethod ("Initialize",corkactivity.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("addnBtn")));
 BA.debugLineNum = 324;BA.debugLine="addnBtn.Text = \"Add Note\"";
Debug.ShouldStop(8);
_addnbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Add Note"));
 BA.debugLineNum = 325;BA.debugLine="notePnl.AddView(addnBtn, 10dip, 60dip, (pW / 2) -";
Debug.ShouldStop(16);
corkactivity.mostCurrent._notepnl.runVoidMethod ("AddView",(Object)((_addnbtn.getObject())),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_pw,RemoteObject.createImmutable(2)}, "/",0, 0)),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))}, "-",1, 0))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 327;BA.debugLine="notePnl.Enabled = False";
Debug.ShouldStop(64);
corkactivity.mostCurrent._notepnl.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 328;BA.debugLine="notePnl.Visible = False";
Debug.ShouldStop(128);
corkactivity.mostCurrent._notepnl.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 330;BA.debugLine="Dim bd As BitmapDrawable";
Debug.ShouldStop(512);
_bd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd", _bd);
 BA.debugLineNum = 331;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(1024);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 332;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(2048);
switch (BA.switchObjectToInt(corkactivity.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 334;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(8192);
if (corkactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 335;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(16384);
corkactivity.mostCurrent._notepnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 336;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(187, 2";
Debug.ShouldStop(32768);
corkactivity.mostCurrent._notepnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 187)),(Object)(BA.numberCast(int.class, 213)),(Object)(BA.numberCast(int.class, 218)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_Black")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 337;BA.debugLine="cd.Initialize(Colors.ARGB(255, 59, 117, 151),";
Debug.ShouldStop(65536);
_cd.runVoidMethod ("Initialize",(Object)(corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 59)),(Object)(BA.numberCast(int.class, 117)),(Object)(BA.numberCast(int.class, 151)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 338;BA.debugLine="addnBtn.Background = cd";
Debug.ShouldStop(131072);
_addnbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 339;BA.debugLine="addnBtn.TextColor = Colors.Black";
Debug.ShouldStop(262144);
_addnbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else {
 BA.debugLineNum = 341;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(1048576);
corkactivity.mostCurrent._notepnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 342;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(227, 1";
Debug.ShouldStop(2097152);
corkactivity.mostCurrent._notepnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 227)),(Object)(BA.numberCast(int.class, 193)),(Object)(BA.numberCast(int.class, 159)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_Black")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 343;BA.debugLine="cd.Initialize(Colors.ARGB(255, 98, 43, 20), 20";
Debug.ShouldStop(4194304);
_cd.runVoidMethod ("Initialize",(Object)(corkactivity.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 43)),(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 200)));
 BA.debugLineNum = 344;BA.debugLine="addnBtn.Background = cd";
Debug.ShouldStop(8388608);
_addnbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 345;BA.debugLine="addnBtn.TextColor = Colors.White";
Debug.ShouldStop(16777216);
_addnbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 1: {
 BA.debugLineNum = 348;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(134217728);
if (corkactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 349;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(268435456);
corkactivity.mostCurrent._notepnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 350;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(27, 98";
Debug.ShouldStop(536870912);
corkactivity.mostCurrent._notepnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 27)),(Object)(BA.numberCast(int.class, 98)),(Object)(BA.numberCast(int.class, 161)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 16)),(Object)(BA.numberCast(int.class, 26)),(Object)(BA.numberCast(int.class, 95)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 351;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"dark";
Debug.ShouldStop(1073741824);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("dark Skeumorphic Button blue.png"))).getObject())));
 BA.debugLineNum = 352;BA.debugLine="addnBtn.Background = bd";
Debug.ShouldStop(-2147483648);
_addnbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 353;BA.debugLine="addnBtn.TextColor = Colors.White";
Debug.ShouldStop(1);
_addnbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 355;BA.debugLine="notePnl.Color = xui.Color_RGB(255, 255, 255)";
Debug.ShouldStop(4);
corkactivity.mostCurrent._notepnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 356;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(51, 18";
Debug.ShouldStop(8);
corkactivity.mostCurrent._notepnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 51)),(Object)(BA.numberCast(int.class, 187)),(Object)(BA.numberCast(int.class, 152)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_White")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 357;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"Skeu";
Debug.ShouldStop(16);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Skeumorphic Button 03.png"))).getObject())));
 BA.debugLineNum = 358;BA.debugLine="addnBtn.Background = bd";
Debug.ShouldStop(32);
_addnbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 359;BA.debugLine="addnBtn.TextColor = Colors.White";
Debug.ShouldStop(64);
_addnbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
case 2: {
 BA.debugLineNum = 362;BA.debugLine="addnBtn.Typeface = pixeltf";
Debug.ShouldStop(512);
_addnbtn.runMethod(false,"setTypeface",(corkactivity.mostCurrent._pixeltf.getObject()));
 BA.debugLineNum = 363;BA.debugLine="If Starter.darkMode Then";
Debug.ShouldStop(1024);
if (corkactivity.mostCurrent._starter._darkmode /*RemoteObject*/ .<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 364;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(2048);
corkactivity.mostCurrent._notepnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 365;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(37, 57";
Debug.ShouldStop(4096);
corkactivity.mostCurrent._notepnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 37)),(Object)(BA.numberCast(int.class, 57)),(Object)(BA.numberCast(int.class, 94)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.getField(true,"Color_Black")),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 366;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(8192);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 367;BA.debugLine="addnBtn.Background = bd";
Debug.ShouldStop(16384);
_addnbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 368;BA.debugLine="addnBtn.TextColor = Colors.White";
Debug.ShouldStop(32768);
_addnbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 370;BA.debugLine="notePnl.Color = xui.Color_RGB(50, 50, 50)";
Debug.ShouldStop(131072);
corkactivity.mostCurrent._notepnl.runMethod(true,"setColor",corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50)),(Object)(BA.numberCast(int.class, 50))));
 BA.debugLineNum = 371;BA.debugLine="notePnl.SetColorAndBorder(xui.Color_RGB(234, 2";
Debug.ShouldStop(262144);
corkactivity.mostCurrent._notepnl.runVoidMethod ("SetColorAndBorder",(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 234)),(Object)(BA.numberCast(int.class, 212)),(Object)(BA.numberCast(int.class, 179)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(corkactivity._xui.runMethod(true,"Color_RGB",(Object)(BA.numberCast(int.class, 79)),(Object)(BA.numberCast(int.class, 46)),(Object)(BA.numberCast(int.class, 46)))),(Object)(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 372;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets, \"cale";
Debug.ShouldStop(524288);
_bd.runVoidMethod ("Initialize",(Object)((corkactivity.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(corkactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("calendarpbtn.png"))).getObject())));
 BA.debugLineNum = 373;BA.debugLine="addnBtn.Background = bd";
Debug.ShouldStop(1048576);
_addnbtn.runMethod(false,"setBackground",(_bd.getObject()));
 BA.debugLineNum = 374;BA.debugLine="addnBtn.TextColor = Colors.White";
Debug.ShouldStop(2097152);
_addnbtn.runMethod(true,"setTextColor",corkactivity.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 break; }
}
;
 BA.debugLineNum = 377;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _penspnr_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("penSpnr_ItemClick (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,531);
if (RapidSub.canDelegate("penspnr_itemclick")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","penspnr_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 531;BA.debugLine="Private Sub penSpnr_ItemClick (Position As Int, Va";
Debug.ShouldStop(262144);
 BA.debugLineNum = 532;BA.debugLine="Select Position";
Debug.ShouldStop(524288);
switch (BA.switchObjectToInt(_position,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5))) {
case 0: {
 BA.debugLineNum = 534;BA.debugLine="R2 = 0";
Debug.ShouldStop(2097152);
corkactivity._r2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 535;BA.debugLine="G2 = 0";
Debug.ShouldStop(4194304);
corkactivity._g2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 536;BA.debugLine="B2 = 0";
Debug.ShouldStop(8388608);
corkactivity._b2 = BA.numberCast(int.class, 0);
 break; }
case 1: {
 BA.debugLineNum = 538;BA.debugLine="R2 = 0";
Debug.ShouldStop(33554432);
corkactivity._r2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 539;BA.debugLine="G2 = 0";
Debug.ShouldStop(67108864);
corkactivity._g2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 540;BA.debugLine="B2 = 255";
Debug.ShouldStop(134217728);
corkactivity._b2 = BA.numberCast(int.class, 255);
 break; }
case 2: {
 BA.debugLineNum = 542;BA.debugLine="R2 = 0";
Debug.ShouldStop(536870912);
corkactivity._r2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 543;BA.debugLine="G2 = 255";
Debug.ShouldStop(1073741824);
corkactivity._g2 = BA.numberCast(int.class, 255);
 BA.debugLineNum = 544;BA.debugLine="B2 = 0";
Debug.ShouldStop(-2147483648);
corkactivity._b2 = BA.numberCast(int.class, 0);
 break; }
case 3: {
 BA.debugLineNum = 546;BA.debugLine="R2 = 255";
Debug.ShouldStop(2);
corkactivity._r2 = BA.numberCast(int.class, 255);
 BA.debugLineNum = 547;BA.debugLine="G2 = 0";
Debug.ShouldStop(4);
corkactivity._g2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 548;BA.debugLine="B2 = 0";
Debug.ShouldStop(8);
corkactivity._b2 = BA.numberCast(int.class, 0);
 break; }
case 4: {
 BA.debugLineNum = 550;BA.debugLine="R2 = 255";
Debug.ShouldStop(32);
corkactivity._r2 = BA.numberCast(int.class, 255);
 BA.debugLineNum = 551;BA.debugLine="G2 = 255";
Debug.ShouldStop(64);
corkactivity._g2 = BA.numberCast(int.class, 255);
 BA.debugLineNum = 552;BA.debugLine="B2 = 0";
Debug.ShouldStop(128);
corkactivity._b2 = BA.numberCast(int.class, 0);
 break; }
case 5: {
 BA.debugLineNum = 554;BA.debugLine="R2 = 255";
Debug.ShouldStop(512);
corkactivity._r2 = BA.numberCast(int.class, 255);
 BA.debugLineNum = 555;BA.debugLine="G2 = 255";
Debug.ShouldStop(1024);
corkactivity._g2 = BA.numberCast(int.class, 255);
 BA.debugLineNum = 556;BA.debugLine="B2 = 255";
Debug.ShouldStop(2048);
corkactivity._b2 = BA.numberCast(int.class, 255);
 break; }
}
;
 BA.debugLineNum = 558;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
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
 //BA.debugLineNum = 9;BA.debugLine="Private imgPicker As ContentChooser";
corkactivity._imgpicker = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.ContentChooser");
 //BA.debugLineNum = 10;BA.debugLine="Private xui As XUI";
corkactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _sizespnr_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("sizeSpnr_ItemClick (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,491);
if (RapidSub.canDelegate("sizespnr_itemclick")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","sizespnr_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 491;BA.debugLine="Private Sub sizeSpnr_ItemClick (Position As Int, V";
Debug.ShouldStop(1024);
 BA.debugLineNum = 492;BA.debugLine="Select Position";
Debug.ShouldStop(2048);
switch (BA.switchObjectToInt(_position,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5))) {
case 0: {
 BA.debugLineNum = 494;BA.debugLine="Width = 80dip";
Debug.ShouldStop(8192);
corkactivity._width = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));
 BA.debugLineNum = 495;BA.debugLine="Height = 60dip";
Debug.ShouldStop(16384);
corkactivity._height = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));
 break; }
case 1: {
 BA.debugLineNum = 497;BA.debugLine="Width = 205dip";
Debug.ShouldStop(65536);
corkactivity._width = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 205)));
 BA.debugLineNum = 498;BA.debugLine="Height = 60dip";
Debug.ShouldStop(131072);
corkactivity._height = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)));
 break; }
case 2: {
 BA.debugLineNum = 500;BA.debugLine="Width = 80dip";
Debug.ShouldStop(524288);
corkactivity._width = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));
 BA.debugLineNum = 501;BA.debugLine="Height = 185dip";
Debug.ShouldStop(1048576);
corkactivity._height = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 185)));
 break; }
case 3: {
 BA.debugLineNum = 503;BA.debugLine="Width = 205dip";
Debug.ShouldStop(4194304);
corkactivity._width = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 205)));
 BA.debugLineNum = 504;BA.debugLine="Height = 185dip";
Debug.ShouldStop(8388608);
corkactivity._height = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 185)));
 break; }
case 4: {
 BA.debugLineNum = 506;BA.debugLine="Width = 330dip";
Debug.ShouldStop(33554432);
corkactivity._width = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 330)));
 BA.debugLineNum = 507;BA.debugLine="Height = 185dip";
Debug.ShouldStop(67108864);
corkactivity._height = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 185)));
 break; }
case 5: {
 BA.debugLineNum = 509;BA.debugLine="Width = 205dip";
Debug.ShouldStop(268435456);
corkactivity._width = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 205)));
 BA.debugLineNum = 510;BA.debugLine="Height = 310dip";
Debug.ShouldStop(536870912);
corkactivity._height = corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 310)));
 break; }
}
;
 BA.debugLineNum = 512;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _stickybtn_click() throws Exception{
try {
		Debug.PushSubsStack("stickyBtn_Click (corkactivity) ","corkactivity",20,corkactivity.mostCurrent.activityBA,corkactivity.mostCurrent,468);
if (RapidSub.canDelegate("stickybtn_click")) { return b4a.example.corkactivity.remoteMe.runUserSub(false, "corkactivity","stickybtn_click");}
 BA.debugLineNum = 468;BA.debugLine="Private Sub stickyBtn_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 469;BA.debugLine="noteWindow(250dip, 180dip)";
Debug.ShouldStop(1048576);
_notewindow(corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 250))),corkactivity.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 470;BA.debugLine="notePnl.Visible = True";
Debug.ShouldStop(2097152);
corkactivity.mostCurrent._notepnl.runMethod(true,"setVisible",corkactivity.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 471;BA.debugLine="stickyBtn.Enabled = False";
Debug.ShouldStop(4194304);
corkactivity.mostCurrent._stickybtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 472;BA.debugLine="canvaBtn.Enabled = False";
Debug.ShouldStop(8388608);
corkactivity.mostCurrent._canvabtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 473;BA.debugLine="imgBtn.Enabled = False";
Debug.ShouldStop(16777216);
corkactivity.mostCurrent._imgbtn.runMethod(true,"setEnabled",corkactivity.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 474;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}