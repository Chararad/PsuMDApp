package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class subdeck_module_subs_1 {


public static RemoteObject  _activerecall_click() throws Exception{
try {
		Debug.PushSubsStack("activerecall_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,174);
if (RapidSub.canDelegate("activerecall_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activerecall_click");}
RemoteObject _chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _deckname = RemoteObject.createImmutable("");
RemoteObject _flashacards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 174;BA.debugLine="Private Sub activerecall_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 177;BA.debugLine="number_of_cards = 0";
Debug.ShouldStop(65536);
subdeck_module._number_of_cards = BA.numberCast(int.class, 0);
 BA.debugLineNum = 179;BA.debugLine="Dim chosendeck As Map = alldecks.Get(selecteddeck";
Debug.ShouldStop(262144);
_chosendeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._alldecks.runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._selecteddeck))));Debug.locals.put("chosendeck", _chosendeck);Debug.locals.put("chosendeck", _chosendeck);
 BA.debugLineNum = 181;BA.debugLine="For Each deckName As String In chosendeck.keys";
Debug.ShouldStop(1048576);
{
final RemoteObject group3 = _chosendeck.runMethod(false,"Keys");
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.runMethod(false,"Get",index3));Debug.locals.put("deckName", _deckname);
Debug.locals.put("deckName", _deckname);
 BA.debugLineNum = 182;BA.debugLine="Dim flashacards As List = chosendeck.Get(deckNam";
Debug.ShouldStop(2097152);
_flashacards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashacards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _chosendeck.runMethod(false,"Get",(Object)((_deckname))));Debug.locals.put("flashacards", _flashacards);Debug.locals.put("flashacards", _flashacards);
 BA.debugLineNum = 183;BA.debugLine="number_of_cards = number_of_cards + flashacards.";
Debug.ShouldStop(4194304);
subdeck_module._number_of_cards = RemoteObject.solve(new RemoteObject[] {subdeck_module._number_of_cards,_flashacards.runMethod(true,"getSize")}, "+",1, 1);
 }
}Debug.locals.put("deckName", _deckname);
;
 BA.debugLineNum = 186;BA.debugLine="AR_confirmationpanel.Visible = True";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 187;BA.debugLine="confirmlabel.Text = \"You got \" & number_of_cards";
Debug.ShouldStop(67108864);
subdeck_module.mostCurrent._confirmlabel.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("You got "),subdeck_module._number_of_cards,RemoteObject.createImmutable(" cards"))));
 BA.debugLineNum = 188;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,36);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activity_create", _firsttime);}
RemoteObject _radius = RemoteObject.createImmutable(0);
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8);
 BA.debugLineNum = 37;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(16);
switch (BA.switchObjectToInt(subdeck_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 39;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 40;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout\")";
Debug.ShouldStop(128);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayout")),subdeck_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark\"";
Debug.ShouldStop(512);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayoutDark")),subdeck_module.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 45;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 46;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout2\")";
Debug.ShouldStop(8192);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayout2")),subdeck_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark2";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayoutDark2")),subdeck_module.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 51;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 52;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout3\")";
Debug.ShouldStop(524288);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayout3")),subdeck_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 54;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark3";
Debug.ShouldStop(2097152);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayoutDark3")),subdeck_module.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 58;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 60;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
Debug.ShouldStop(134217728);
subdeck_module.mostCurrent._lvsubdecks.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",subdeck_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else {
 BA.debugLineNum = 62;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
Debug.ShouldStop(536870912);
subdeck_module.mostCurrent._lvsubdecks.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",subdeck_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 BA.debugLineNum = 66;BA.debugLine="Dim radius As Int = Addbtn.Width/2";
Debug.ShouldStop(2);
_radius = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {subdeck_module.mostCurrent._addbtn.runMethod(true,"getWidth"),RemoteObject.createImmutable(2)}, "/",0, 0));Debug.locals.put("radius", _radius);Debug.locals.put("radius", _radius);
 BA.debugLineNum = 67;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(4);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 68;BA.debugLine="cd.Initialize(Colors.Gray, radius)";
Debug.ShouldStop(8);
_cd.runVoidMethod ("Initialize",(Object)(subdeck_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Gray")),(Object)(_radius));
 BA.debugLineNum = 69;BA.debugLine="Addbtn.Background = cd";
Debug.ShouldStop(16);
subdeck_module.mostCurrent._addbtn.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 72;BA.debugLine="decknamelabel.Text = selecteddeck";
Debug.ShouldStop(128);
subdeck_module.mostCurrent._decknamelabel.runMethod(true,"setText",BA.ObjectToCharSequence(subdeck_module.mostCurrent._selecteddeck));
 BA.debugLineNum = 75;BA.debugLine="Refresh";
Debug.ShouldStop(1024);
_refresh();
 BA.debugLineNum = 77;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
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
		Debug.PushSubsStack("Activity_Pause (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,102);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 102;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32);
 BA.debugLineNum = 104;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("Activity_Resume (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,94);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activity_resume");}
 BA.debugLineNum = 94;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 95;BA.debugLine="If all_active_recall.praise = True Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._all_active_recall._praise /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 96;BA.debugLine="all_active_recall.praise = False";
Debug.ShouldStop(-2147483648);
subdeck_module.mostCurrent._all_active_recall._praise /*RemoteObject*/  = subdeck_module.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 97;BA.debugLine="MsgboxAsync(\"You Finished Your Deck\", \"Congratul";
Debug.ShouldStop(1);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("You Finished Your Deck")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Congratulations"))),subdeck_module.processBA);
 };
 BA.debugLineNum = 99;BA.debugLine="AR_confirmationpanel.Visible = False";
Debug.ShouldStop(4);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 100;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addbtn_click() throws Exception{
try {
		Debug.PushSubsStack("Addbtn_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,107);
if (RapidSub.canDelegate("addbtn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","addbtn_click");}
 BA.debugLineNum = 107;BA.debugLine="Private Sub Addbtn_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 109;BA.debugLine="If addpanel2.Visible = False Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._addpanel2.runMethod(true,"getVisible"),subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 110;BA.debugLine="addpanel2.Visible = True";
Debug.ShouldStop(8192);
subdeck_module.mostCurrent._addpanel2.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 112;BA.debugLine="addpanel2.Visible = False";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._addpanel2.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 115;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addcard_click() throws Exception{
try {
		Debug.PushSubsStack("addcard_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,117);
if (RapidSub.canDelegate("addcard_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","addcard_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 117;BA.debugLine="Private Sub addcard_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 119;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(4194304);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 120;BA.debugLine="If tappeddeck.Size = 0 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",_tappeddeck.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 121;BA.debugLine="MsgboxAsync(\"Create A Sub-Deck first\", \"Error\")";
Debug.ShouldStop(16777216);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Create A Sub-Deck first")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 122;BA.debugLine="Return";
Debug.ShouldStop(33554432);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 124;BA.debugLine="StartActivity(Add_card_module)";
Debug.ShouldStop(134217728);
subdeck_module.mostCurrent.__c.runVoidMethod ("StartActivity",subdeck_module.processBA,(Object)((subdeck_module.mostCurrent._add_card_module.getObject())));
 BA.debugLineNum = 125;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addsub_click() throws Exception{
try {
		Debug.PushSubsStack("addsub_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,127);
if (RapidSub.canDelegate("addsub_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","addsub_click");}
 BA.debugLineNum = 127;BA.debugLine="Private Sub addsub_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 129;BA.debugLine="addpanel.Visible = True";
Debug.ShouldStop(1);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 130;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _backbtn_click() throws Exception{
try {
		Debug.PushSubsStack("backbtn_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,211);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","backbtn_click");}
 BA.debugLineNum = 211;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 212;BA.debugLine="Activity.Finish";
Debug.ShouldStop(524288);
subdeck_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 213;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cancel_click() throws Exception{
try {
		Debug.PushSubsStack("cancel_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,132);
if (RapidSub.canDelegate("cancel_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancel_click");}
 BA.debugLineNum = 132;BA.debugLine="Private Sub cancel_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 134;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(32);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 135;BA.debugLine="et1.Text = \"\"";
Debug.ShouldStop(64);
subdeck_module.mostCurrent._et1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 136;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cancelalter_click() throws Exception{
try {
		Debug.PushSubsStack("cancelalter_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,225);
if (RapidSub.canDelegate("cancelalter_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancelalter_click");}
 BA.debugLineNum = 225;BA.debugLine="Private Sub cancelalter_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 226;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(2);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
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
public static RemoteObject  _cancelconfirmation_click() throws Exception{
try {
		Debug.PushSubsStack("cancelconfirmation_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,199);
if (RapidSub.canDelegate("cancelconfirmation_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancelconfirmation_click");}
 BA.debugLineNum = 199;BA.debugLine="Private Sub cancelconfirmation_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 201;BA.debugLine="AR_confirmationpanel.Visible = False";
Debug.ShouldStop(256);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 202;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _canceldelete_click() throws Exception{
try {
		Debug.PushSubsStack("canceldelete_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,273);
if (RapidSub.canDelegate("canceldelete_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","canceldelete_click");}
 BA.debugLineNum = 273;BA.debugLine="Private Sub canceldelete_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 274;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(131072);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 275;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cancelrename_click() throws Exception{
try {
		Debug.PushSubsStack("cancelrename_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,234);
if (RapidSub.canDelegate("cancelrename_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancelrename_click");}
 BA.debugLineNum = 234;BA.debugLine="Private Sub cancelrename_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 235;BA.debugLine="renamepanel.Visible = False";
Debug.ShouldStop(1024);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 236;BA.debugLine="renameet.Text = \"\"";
Debug.ShouldStop(2048);
subdeck_module.mostCurrent._renameet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 237;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _confirmdelete_click() throws Exception{
try {
		Debug.PushSubsStack("confirmdelete_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,265);
if (RapidSub.canDelegate("confirmdelete_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","confirmdelete_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 265;BA.debugLine="Private Sub confirmdelete_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 266;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.ge";
Debug.ShouldStop(512);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 267;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
Debug.ShouldStop(1024);
_tappeddeck.runVoidMethod ("Remove",(Object)((subdeck_module._selectedsubdeck)));
 BA.debugLineNum = 268;BA.debugLine="SaveDecks";
Debug.ShouldStop(2048);
_savedecks();
 BA.debugLineNum = 269;BA.debugLine="Refresh";
Debug.ShouldStop(4096);
_refresh();
 BA.debugLineNum = 270;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(8192);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 271;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _confirmrename_click() throws Exception{
try {
		Debug.PushSubsStack("confirmrename_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,239);
if (RapidSub.canDelegate("confirmrename_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","confirmrename_click");}
RemoteObject _getsubdeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _names = RemoteObject.createImmutable("");
 BA.debugLineNum = 239;BA.debugLine="Private Sub confirmrename_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 241;BA.debugLine="Dim getsubdeck As List";
Debug.ShouldStop(65536);
_getsubdeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("getsubdeck", _getsubdeck);
 BA.debugLineNum = 242;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(131072);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 243;BA.debugLine="If renameet.Text = \"\" Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._renameet.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 244;BA.debugLine="MsgboxAsync(\"New Name must have a name\", \"Error\"";
Debug.ShouldStop(524288);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("New Name must have a name")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 245;BA.debugLine="Return";
Debug.ShouldStop(1048576);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 247;BA.debugLine="For Each names As String In tappeddeck.keys";
Debug.ShouldStop(4194304);
{
final RemoteObject group7 = _tappeddeck.runMethod(false,"Keys");
final int groupLen7 = group7.runMethod(true,"getSize").<Integer>get()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.runMethod(false,"Get",index7));Debug.locals.put("names", _names);
Debug.locals.put("names", _names);
 BA.debugLineNum = 248;BA.debugLine="getsubdeck = tappeddeck.Get(selectedsubdeck)";
Debug.ShouldStop(8388608);
_getsubdeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((subdeck_module._selectedsubdeck))));Debug.locals.put("getsubdeck", _getsubdeck);
 BA.debugLineNum = 249;BA.debugLine="If renameet.Text = names Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._renameet.runMethod(true,"getText"),_names)) { 
 BA.debugLineNum = 250;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub Deck Name Already Exist")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 251;BA.debugLine="Return";
Debug.ShouldStop(67108864);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("names", _names);
;
 BA.debugLineNum = 254;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
Debug.ShouldStop(536870912);
_tappeddeck.runVoidMethod ("Remove",(Object)((subdeck_module._selectedsubdeck)));
 BA.debugLineNum = 255;BA.debugLine="tappeddeck.Put(renameet.Text, getsubdeck)";
Debug.ShouldStop(1073741824);
_tappeddeck.runVoidMethod ("Put",(Object)((subdeck_module.mostCurrent._renameet.runMethod(true,"getText"))),(Object)((_getsubdeck.getObject())));
 BA.debugLineNum = 256;BA.debugLine="renameet.text = \"\"";
Debug.ShouldStop(-2147483648);
subdeck_module.mostCurrent._renameet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 257;BA.debugLine="SaveDecks";
Debug.ShouldStop(1);
_savedecks();
 BA.debugLineNum = 258;BA.debugLine="Refresh";
Debug.ShouldStop(2);
_refresh();
 BA.debugLineNum = 259;BA.debugLine="renamepanel.Visible = False";
Debug.ShouldStop(4);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 260;BA.debugLine="renamepanel.Visible = False";
Debug.ShouldStop(8);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 263;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _create_click() throws Exception{
try {
		Debug.PushSubsStack("create_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,138);
if (RapidSub.canDelegate("create_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","create_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _name = RemoteObject.declareNull("Object");
 BA.debugLineNum = 138;BA.debugLine="Private Sub create_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 141;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
Debug.ShouldStop(4096);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._alldecks.runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._selecteddeck))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 142;BA.debugLine="Dim flashcards As List";
Debug.ShouldStop(8192);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 144;BA.debugLine="flashcards.initialize";
Debug.ShouldStop(32768);
_flashcards.runVoidMethod ("Initialize");
 BA.debugLineNum = 147;BA.debugLine="For Each name In tappeddeck.Keys";
Debug.ShouldStop(262144);
{
final RemoteObject group4 = _tappeddeck.runMethod(false,"Keys");
final int groupLen4 = group4.runMethod(true,"getSize").<Integer>get()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_name = group4.runMethod(false,"Get",index4);Debug.locals.put("name", _name);
Debug.locals.put("name", _name);
 BA.debugLineNum = 148;BA.debugLine="If et1.Text = name Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._et1.runMethod(true,"getText"),BA.ObjectToString(_name))) { 
 BA.debugLineNum = 149;BA.debugLine="MsgboxAsync(\"Sub-Deck Already Exist\", \"Error\")";
Debug.ShouldStop(1048576);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub-Deck Already Exist")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 150;BA.debugLine="Return";
Debug.ShouldStop(2097152);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("name", _name);
;
 BA.debugLineNum = 155;BA.debugLine="If et1.Text = \"\" Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._et1.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 156;BA.debugLine="MsgboxAsync(\"Sub-Deck must have a name\", \"Error\"";
Debug.ShouldStop(134217728);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub-Deck must have a name")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 }else {
 BA.debugLineNum = 159;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(1073741824);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 160;BA.debugLine="LVSubdecks.AddSingleLine(et1.Text)";
Debug.ShouldStop(-2147483648);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(subdeck_module.mostCurrent._et1.runMethod(true,"getText"))));
 BA.debugLineNum = 161;BA.debugLine="tappeddeck.Put(et1.Text, flashcards)";
Debug.ShouldStop(1);
_tappeddeck.runVoidMethod ("Put",(Object)((subdeck_module.mostCurrent._et1.runMethod(true,"getText"))),(Object)((_flashcards.getObject())));
 BA.debugLineNum = 162;BA.debugLine="SaveDecks";
Debug.ShouldStop(2);
_savedecks();
 BA.debugLineNum = 164;BA.debugLine="et1.Text = \"\"";
Debug.ShouldStop(8);
subdeck_module.mostCurrent._et1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 };
 BA.debugLineNum = 167;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _deletesubdeck_click() throws Exception{
try {
		Debug.PushSubsStack("deletesubdeck_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,215);
if (RapidSub.canDelegate("deletesubdeck_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","deletesubdeck_click");}
 BA.debugLineNum = 215;BA.debugLine="Private Sub deletesubdeck_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 216;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(8388608);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 217;BA.debugLine="deleteconfirmation.Visible = True";
Debug.ShouldStop(16777216);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private Addbtn As Button";
subdeck_module.mostCurrent._addbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private addpanel2 As Panel";
subdeck_module.mostCurrent._addpanel2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Dim alldecks As Map = FlashcardActivity.deck 'tak";
subdeck_module.mostCurrent._alldecks = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
subdeck_module.mostCurrent._alldecks = subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ ;
 //BA.debugLineNum = 22;BA.debugLine="Dim selecteddeck As String = FlashcardActivity.se";
subdeck_module.mostCurrent._selecteddeck = subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ;
 //BA.debugLineNum = 23;BA.debugLine="Private LVSubdecks As ListView";
subdeck_module.mostCurrent._lvsubdecks = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private decknamelabel As Label";
subdeck_module.mostCurrent._decknamelabel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private addpanel As Panel";
subdeck_module.mostCurrent._addpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private et1 As EditText";
subdeck_module.mostCurrent._et1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private AR_confirmationpanel As Panel";
subdeck_module.mostCurrent._ar_confirmationpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private confirmlabel As Label";
subdeck_module.mostCurrent._confirmlabel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Dim number_of_cards As Int = 0 'to count the numb";
subdeck_module._number_of_cards = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 30;BA.debugLine="Private alterpanel As Panel";
subdeck_module.mostCurrent._alterpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private renamepanel As Panel";
subdeck_module.mostCurrent._renamepanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private renameet As EditText";
subdeck_module.mostCurrent._renameet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private deleteconfirmation As Panel";
subdeck_module.mostCurrent._deleteconfirmation = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _goback_click() throws Exception{
try {
		Debug.PushSubsStack("goback_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,169);
if (RapidSub.canDelegate("goback_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","goback_click");}
 BA.debugLineNum = 169;BA.debugLine="Private Sub goback_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 171;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1024);
subdeck_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 172;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _lvsubdecks_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("LVSubdecks_ItemClick (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,204);
if (RapidSub.canDelegate("lvsubdecks_itemclick")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","lvsubdecks_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 204;BA.debugLine="Private Sub LVSubdecks_ItemClick (Position As Int,";
Debug.ShouldStop(2048);
 BA.debugLineNum = 206;BA.debugLine="selectedsubdeck = Value 'gives the chsoen subdeck";
Debug.ShouldStop(8192);
subdeck_module._selectedsubdeck = BA.ObjectToString(_value);
 BA.debugLineNum = 208;BA.debugLine="StartActivity(Card_Module)";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent.__c.runVoidMethod ("StartActivity",subdeck_module.processBA,(Object)((subdeck_module.mostCurrent._card_module.getObject())));
 BA.debugLineNum = 209;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _lvsubdecks_itemlongclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("LVSubdecks_ItemLongClick (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,229);
if (RapidSub.canDelegate("lvsubdecks_itemlongclick")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","lvsubdecks_itemlongclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 229;BA.debugLine="Private Sub LVSubdecks_ItemLongClick (Position As";
Debug.ShouldStop(16);
 BA.debugLineNum = 230;BA.debugLine="alterpanel.Visible = True";
Debug.ShouldStop(32);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 231;BA.debugLine="selectedsubdeck = Value";
Debug.ShouldStop(64);
subdeck_module._selectedsubdeck = BA.ObjectToString(_value);
 BA.debugLineNum = 232;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
 //BA.debugLineNum = 11;BA.debugLine="Dim selectedsubdeck As String 'chosen subdeck";
subdeck_module._selectedsubdeck = RemoteObject.createImmutable("");
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _refresh() throws Exception{
try {
		Debug.PushSubsStack("Refresh (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,83);
if (RapidSub.canDelegate("refresh")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","refresh");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _deckname = RemoteObject.createImmutable("");
 BA.debugLineNum = 83;BA.debugLine="Sub Refresh";
Debug.ShouldStop(262144);
 BA.debugLineNum = 84;BA.debugLine="LVSubdecks.clear";
Debug.ShouldStop(524288);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("Clear");
 BA.debugLineNum = 86;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
Debug.ShouldStop(2097152);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._alldecks.runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._selecteddeck))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 89;BA.debugLine="For Each deckName As String In tappeddeck.keys";
Debug.ShouldStop(16777216);
{
final RemoteObject group3 = _tappeddeck.runMethod(false,"Keys");
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.runMethod(false,"Get",index3));Debug.locals.put("deckName", _deckname);
Debug.locals.put("deckName", _deckname);
 BA.debugLineNum = 90;BA.debugLine="LVSubdecks.AddSingleLine(deckName)";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(_deckname)));
 }
}Debug.locals.put("deckName", _deckname);
;
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
public static RemoteObject  _renamesubdeck_click() throws Exception{
try {
		Debug.PushSubsStack("renamesubdeck_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,220);
if (RapidSub.canDelegate("renamesubdeck_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","renamesubdeck_click");}
 BA.debugLineNum = 220;BA.debugLine="Private Sub renamesubdeck_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 221;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(268435456);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 222;BA.debugLine="renamepanel.visible = True";
Debug.ShouldStop(536870912);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 223;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _savedecks() throws Exception{
try {
		Debug.PushSubsStack("SaveDecks (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,79);
if (RapidSub.canDelegate("savedecks")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","savedecks");}
 BA.debugLineNum = 79;BA.debugLine="Sub SaveDecks";
Debug.ShouldStop(16384);
 BA.debugLineNum = 80;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._flashcardactivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("deck_data")),(Object)((subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .getObject())));
 BA.debugLineNum = 81;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _startarbtn_click() throws Exception{
try {
		Debug.PushSubsStack("startArbtn_Click (subdeck_module) ","subdeck_module",8,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,190);
if (RapidSub.canDelegate("startarbtn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","startarbtn_click");}
 BA.debugLineNum = 190;BA.debugLine="Private Sub startArbtn_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 192;BA.debugLine="If number_of_cards = 0 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",subdeck_module._number_of_cards,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 193;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
Debug.ShouldStop(1);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("No cards available")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 194;BA.debugLine="Return";
Debug.ShouldStop(2);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 196;BA.debugLine="StartActivity(all_active_recall)";
Debug.ShouldStop(8);
subdeck_module.mostCurrent.__c.runVoidMethod ("StartActivity",subdeck_module.processBA,(Object)((subdeck_module.mostCurrent._all_active_recall.getObject())));
 BA.debugLineNum = 197;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}