package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class subdeck_module_subs_0 {


public static RemoteObject  _activerecall_click() throws Exception{
try {
		Debug.PushSubsStack("activerecall_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,184);
if (RapidSub.canDelegate("activerecall_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activerecall_click");}
RemoteObject _chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _deckname = RemoteObject.createImmutable("");
RemoteObject _flashacards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 184;BA.debugLine="Private Sub activerecall_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 187;BA.debugLine="number_of_cards = 0";
Debug.ShouldStop(67108864);
subdeck_module._number_of_cards = BA.numberCast(int.class, 0);
 BA.debugLineNum = 189;BA.debugLine="Dim chosendeck As Map = alldecks.Get(selecteddeck";
Debug.ShouldStop(268435456);
_chosendeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._alldecks.runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._selecteddeck))));Debug.locals.put("chosendeck", _chosendeck);Debug.locals.put("chosendeck", _chosendeck);
 BA.debugLineNum = 191;BA.debugLine="For Each deckName As String In chosendeck.keys";
Debug.ShouldStop(1073741824);
{
final RemoteObject group3 = _chosendeck.runMethod(false,"Keys");
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.runMethod(false,"Get",index3));Debug.locals.put("deckName", _deckname);
Debug.locals.put("deckName", _deckname);
 BA.debugLineNum = 192;BA.debugLine="Dim flashacards As List = chosendeck.Get(deckNam";
Debug.ShouldStop(-2147483648);
_flashacards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashacards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _chosendeck.runMethod(false,"Get",(Object)((_deckname))));Debug.locals.put("flashacards", _flashacards);Debug.locals.put("flashacards", _flashacards);
 BA.debugLineNum = 193;BA.debugLine="number_of_cards = number_of_cards + flashacards.";
Debug.ShouldStop(1);
subdeck_module._number_of_cards = RemoteObject.solve(new RemoteObject[] {subdeck_module._number_of_cards,_flashacards.runMethod(true,"getSize")}, "+",1, 1);
 }
}Debug.locals.put("deckName", _deckname);
;
 BA.debugLineNum = 196;BA.debugLine="AR_confirmationpanel.Visible = True";
Debug.ShouldStop(8);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 197;BA.debugLine="confirmlabel.Text = \"You got \" & number_of_cards";
Debug.ShouldStop(16);
subdeck_module.mostCurrent._confirmlabel.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("You got "),subdeck_module._number_of_cards,RemoteObject.createImmutable(" cards"))));
 BA.debugLineNum = 198;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("Activity_Create (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,45);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 45;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 46;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(8192);
switch (BA.switchObjectToInt(subdeck_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 48;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 49;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout\")";
Debug.ShouldStop(65536);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayout")),subdeck_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 51;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark\"";
Debug.ShouldStop(262144);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayoutDark")),subdeck_module.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 54;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout2\")";
Debug.ShouldStop(4194304);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayout2")),subdeck_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 57;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark2";
Debug.ShouldStop(16777216);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayoutDark2")),subdeck_module.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 60;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 61;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayout3\")";
Debug.ShouldStop(268435456);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayout3")),subdeck_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 63;BA.debugLine="Activity.LoadLayout(\"Subdeck_ModuleLayoutDark3";
Debug.ShouldStop(1073741824);
subdeck_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Subdeck_ModuleLayoutDark3")),subdeck_module.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 67;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 69;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
Debug.ShouldStop(16);
subdeck_module.mostCurrent._lvsubdecks.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",subdeck_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else {
 BA.debugLineNum = 71;BA.debugLine="LVSubdecks.SingleLineLayout.Label.textColor = Co";
Debug.ShouldStop(64);
subdeck_module.mostCurrent._lvsubdecks.runMethod(false,"getSingleLineLayout").getField(false,"Label").runMethod(true,"setTextColor",subdeck_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 BA.debugLineNum = 74;BA.debugLine="cc.Initialize(\"CC\")";
Debug.ShouldStop(512);
subdeck_module.mostCurrent._cc.runVoidMethod ("Initialize",(Object)(RemoteObject.createImmutable("CC")));
 BA.debugLineNum = 76;BA.debugLine="decknamelabel.Text = selecteddeck";
Debug.ShouldStop(2048);
subdeck_module.mostCurrent._decknamelabel.runMethod(true,"setText",BA.ObjectToCharSequence(subdeck_module.mostCurrent._selecteddeck));
 BA.debugLineNum = 78;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(8192);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 79;BA.debugLine="addpanel2.Visible = False";
Debug.ShouldStop(16384);
subdeck_module.mostCurrent._addpanel2.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 80;BA.debugLine="AR_confirmationpanel.Visible = False";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 81;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(65536);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 82;BA.debugLine="renamepanel.Visible = False";
Debug.ShouldStop(131072);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 83;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(262144);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 85;BA.debugLine="Refresh";
Debug.ShouldStop(1048576);
_refresh();
 BA.debugLineNum = 87;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("Activity_Pause (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,112);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 112;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 114;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("Activity_Resume (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,104);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","activity_resume");}
 BA.debugLineNum = 104;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="If all_active_recall.praise = True Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._all_active_recall._praise /*RemoteObject*/ ,subdeck_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 106;BA.debugLine="all_active_recall.praise = False";
Debug.ShouldStop(512);
subdeck_module.mostCurrent._all_active_recall._praise /*RemoteObject*/  = subdeck_module.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 107;BA.debugLine="MsgboxAsync(\"You Finished Your Deck\", \"Congratul";
Debug.ShouldStop(1024);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("You Finished Your Deck")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Congratulations"))),subdeck_module.processBA);
 };
 BA.debugLineNum = 109;BA.debugLine="AR_confirmationpanel.Visible = False";
Debug.ShouldStop(4096);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
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
public static RemoteObject  _addbtn_click() throws Exception{
try {
		Debug.PushSubsStack("Addbtn_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,117);
if (RapidSub.canDelegate("addbtn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","addbtn_click");}
 BA.debugLineNum = 117;BA.debugLine="Private Sub Addbtn_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 119;BA.debugLine="If addpanel2.Visible = False Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._addpanel2.runMethod(true,"getVisible"),subdeck_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 120;BA.debugLine="addpanel2.Visible = True";
Debug.ShouldStop(8388608);
subdeck_module.mostCurrent._addpanel2.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 122;BA.debugLine="addpanel2.Visible = False";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent._addpanel2.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 };
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
public static RemoteObject  _addcard_click() throws Exception{
try {
		Debug.PushSubsStack("addcard_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,127);
if (RapidSub.canDelegate("addcard_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","addcard_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 127;BA.debugLine="Private Sub addcard_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 129;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(1);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 130;BA.debugLine="If tappeddeck.Size = 0 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",_tappeddeck.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 131;BA.debugLine="MsgboxAsync(\"Create A Sub-Deck first\", \"Error\")";
Debug.ShouldStop(4);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Create A Sub-Deck first")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 132;BA.debugLine="Return";
Debug.ShouldStop(8);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 134;BA.debugLine="StartActivity(Add_card_module)";
Debug.ShouldStop(32);
subdeck_module.mostCurrent.__c.runVoidMethod ("StartActivity",subdeck_module.processBA,(Object)((subdeck_module.mostCurrent._add_card_module.getObject())));
 BA.debugLineNum = 135;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("addsub_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,137);
if (RapidSub.canDelegate("addsub_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","addsub_click");}
 BA.debugLineNum = 137;BA.debugLine="Private Sub addsub_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 139;BA.debugLine="addpanel.Visible = True";
Debug.ShouldStop(1024);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 140;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ai_cards_click() throws Exception{
try {
		Debug.PushSubsStack("AI_cards_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,288);
if (RapidSub.canDelegate("ai_cards_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","ai_cards_click");}
 BA.debugLineNum = 288;BA.debugLine="Private Sub AI_cards_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 289;BA.debugLine="If topic_panel.Visible = True Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._topic_panel.runMethod(true,"getVisible"),subdeck_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 290;BA.debugLine="topic_panel.visible = False";
Debug.ShouldStop(2);
subdeck_module.mostCurrent._topic_panel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 291;BA.debugLine="Return";
Debug.ShouldStop(4);
if (true) return RemoteObject.createImmutable("");
 }else {
 BA.debugLineNum = 293;BA.debugLine="topic_panel.Visible = True";
Debug.ShouldStop(16);
subdeck_module.mostCurrent._topic_panel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 };
 BA.debugLineNum = 296;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("backbtn_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,221);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","backbtn_click");}
 BA.debugLineNum = 221;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 222;BA.debugLine="Activity.Finish";
Debug.ShouldStop(536870912);
subdeck_module.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _cancel_click() throws Exception{
try {
		Debug.PushSubsStack("cancel_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,142);
if (RapidSub.canDelegate("cancel_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancel_click");}
 BA.debugLineNum = 142;BA.debugLine="Private Sub cancel_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 144;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 145;BA.debugLine="et1.Text = \"\"";
Debug.ShouldStop(65536);
subdeck_module.mostCurrent._et1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 146;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("cancelalter_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,235);
if (RapidSub.canDelegate("cancelalter_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancelalter_click");}
 BA.debugLineNum = 235;BA.debugLine="Private Sub cancelalter_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 236;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(2048);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
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
public static RemoteObject  _cancelconfirmation_click() throws Exception{
try {
		Debug.PushSubsStack("cancelconfirmation_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,209);
if (RapidSub.canDelegate("cancelconfirmation_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancelconfirmation_click");}
 BA.debugLineNum = 209;BA.debugLine="Private Sub cancelconfirmation_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 211;BA.debugLine="AR_confirmationpanel.Visible = False";
Debug.ShouldStop(262144);
subdeck_module.mostCurrent._ar_confirmationpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 212;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
		Debug.PushSubsStack("canceldelete_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,283);
if (RapidSub.canDelegate("canceldelete_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","canceldelete_click");}
 BA.debugLineNum = 283;BA.debugLine="Private Sub canceldelete_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 284;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(134217728);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 285;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
		Debug.PushSubsStack("cancelrename_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,244);
if (RapidSub.canDelegate("cancelrename_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","cancelrename_click");}
 BA.debugLineNum = 244;BA.debugLine="Private Sub cancelrename_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 245;BA.debugLine="renamepanel.Visible = False";
Debug.ShouldStop(1048576);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 246;BA.debugLine="renameet.Text = \"\"";
Debug.ShouldStop(2097152);
subdeck_module.mostCurrent._renameet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 247;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("confirmdelete_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,275);
if (RapidSub.canDelegate("confirmdelete_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","confirmdelete_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 275;BA.debugLine="Private Sub confirmdelete_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 276;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.ge";
Debug.ShouldStop(524288);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 277;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
Debug.ShouldStop(1048576);
_tappeddeck.runVoidMethod ("Remove",(Object)((subdeck_module._selectedsubdeck)));
 BA.debugLineNum = 278;BA.debugLine="SaveDecks";
Debug.ShouldStop(2097152);
_savedecks();
 BA.debugLineNum = 279;BA.debugLine="Refresh";
Debug.ShouldStop(4194304);
_refresh();
 BA.debugLineNum = 280;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(8388608);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 281;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("confirmrename_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,249);
if (RapidSub.canDelegate("confirmrename_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","confirmrename_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _getsubdeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _names = RemoteObject.createImmutable("");
 BA.debugLineNum = 249;BA.debugLine="Private Sub confirmrename_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 250;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(33554432);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 252;BA.debugLine="If renameet.Text = \"\" Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._renameet.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 253;BA.debugLine="MsgboxAsync(\"New Name must have a name\", \"Error\"";
Debug.ShouldStop(268435456);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("New Name must have a name")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 254;BA.debugLine="Return";
Debug.ShouldStop(536870912);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 258;BA.debugLine="Dim getsubdeck As List = tappeddeck.Get(selecteds";
Debug.ShouldStop(2);
_getsubdeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_getsubdeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((subdeck_module._selectedsubdeck))));Debug.locals.put("getsubdeck", _getsubdeck);Debug.locals.put("getsubdeck", _getsubdeck);
 BA.debugLineNum = 260;BA.debugLine="For Each names As String In tappeddeck.Keys";
Debug.ShouldStop(8);
{
final RemoteObject group7 = _tappeddeck.runMethod(false,"Keys");
final int groupLen7 = group7.runMethod(true,"getSize").<Integer>get()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.runMethod(false,"Get",index7));Debug.locals.put("names", _names);
Debug.locals.put("names", _names);
 BA.debugLineNum = 261;BA.debugLine="If renameet.Text = names Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._renameet.runMethod(true,"getText"),_names)) { 
 BA.debugLineNum = 262;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
Debug.ShouldStop(32);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub Deck Name Already Exist")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 263;BA.debugLine="Return";
Debug.ShouldStop(64);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("names", _names);
;
 BA.debugLineNum = 267;BA.debugLine="tappeddeck.Remove(selectedsubdeck)";
Debug.ShouldStop(1024);
_tappeddeck.runVoidMethod ("Remove",(Object)((subdeck_module._selectedsubdeck)));
 BA.debugLineNum = 268;BA.debugLine="tappeddeck.Put(renameet.Text, getsubdeck)";
Debug.ShouldStop(2048);
_tappeddeck.runVoidMethod ("Put",(Object)((subdeck_module.mostCurrent._renameet.runMethod(true,"getText"))),(Object)((_getsubdeck.getObject())));
 BA.debugLineNum = 269;BA.debugLine="renameet.Text = \"\"";
Debug.ShouldStop(4096);
subdeck_module.mostCurrent._renameet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 270;BA.debugLine="SaveDecks";
Debug.ShouldStop(8192);
_savedecks();
 BA.debugLineNum = 271;BA.debugLine="Refresh";
Debug.ShouldStop(16384);
_refresh();
 BA.debugLineNum = 272;BA.debugLine="renamepanel.Visible = False";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 273;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
		Debug.PushSubsStack("create_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,148);
if (RapidSub.canDelegate("create_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","create_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _name = RemoteObject.declareNull("Object");
 BA.debugLineNum = 148;BA.debugLine="Private Sub create_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 151;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
Debug.ShouldStop(4194304);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._alldecks.runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._selecteddeck))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 152;BA.debugLine="Dim flashcards As List";
Debug.ShouldStop(8388608);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 154;BA.debugLine="flashcards.initialize";
Debug.ShouldStop(33554432);
_flashcards.runVoidMethod ("Initialize");
 BA.debugLineNum = 157;BA.debugLine="For Each name In tappeddeck.Keys";
Debug.ShouldStop(268435456);
{
final RemoteObject group4 = _tappeddeck.runMethod(false,"Keys");
final int groupLen4 = group4.runMethod(true,"getSize").<Integer>get()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_name = group4.runMethod(false,"Get",index4);Debug.locals.put("name", _name);
Debug.locals.put("name", _name);
 BA.debugLineNum = 158;BA.debugLine="If et1.Text = name Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._et1.runMethod(true,"getText"),BA.ObjectToString(_name))) { 
 BA.debugLineNum = 159;BA.debugLine="MsgboxAsync(\"Sub-Deck Already Exist\", \"Error\")";
Debug.ShouldStop(1073741824);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub-Deck Already Exist")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 160;BA.debugLine="Return";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("name", _name);
;
 BA.debugLineNum = 165;BA.debugLine="If et1.Text = \"\" Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._et1.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 166;BA.debugLine="MsgboxAsync(\"Sub-Deck must have a name\", \"Error\"";
Debug.ShouldStop(32);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub-Deck must have a name")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 }else {
 BA.debugLineNum = 169;BA.debugLine="addpanel.Visible = False";
Debug.ShouldStop(256);
subdeck_module.mostCurrent._addpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 170;BA.debugLine="LVSubdecks.AddSingleLine(et1.Text)";
Debug.ShouldStop(512);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(subdeck_module.mostCurrent._et1.runMethod(true,"getText"))));
 BA.debugLineNum = 171;BA.debugLine="tappeddeck.Put(et1.Text, flashcards)";
Debug.ShouldStop(1024);
_tappeddeck.runVoidMethod ("Put",(Object)((subdeck_module.mostCurrent._et1.runMethod(true,"getText"))),(Object)((_flashcards.getObject())));
 BA.debugLineNum = 172;BA.debugLine="SaveDecks";
Debug.ShouldStop(2048);
_savedecks();
 BA.debugLineNum = 174;BA.debugLine="et1.Text = \"\"";
Debug.ShouldStop(8192);
subdeck_module.mostCurrent._et1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 };
 BA.debugLineNum = 177;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
		Debug.PushSubsStack("deletesubdeck_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,225);
if (RapidSub.canDelegate("deletesubdeck_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","deletesubdeck_click");}
 BA.debugLineNum = 225;BA.debugLine="Private Sub deletesubdeck_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 226;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(2);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 227;BA.debugLine="deleteconfirmation.Visible = True";
Debug.ShouldStop(4);
subdeck_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 228;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _generateflashcards(RemoteObject _topic) throws Exception{
try {
		Debug.PushSubsStack("GenerateFlashCards (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,326);
if (RapidSub.canDelegate("generateflashcards")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","generateflashcards", _topic);}
RemoteObject _url = RemoteObject.createImmutable("");
RemoteObject _job = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _prompt = RemoteObject.createImmutable("");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _contents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _contentitem = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _textpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _gen = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
RemoteObject _json = RemoteObject.createImmutable("");
Debug.locals.put("Topic", _topic);
 BA.debugLineNum = 326;BA.debugLine="Sub GenerateFlashCards(Topic As String)";
Debug.ShouldStop(32);
 BA.debugLineNum = 328;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
Debug.ShouldStop(128);
_url = RemoteObject.concat(RemoteObject.createImmutable("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="),subdeck_module.mostCurrent._myapikey);Debug.locals.put("URL", _url);Debug.locals.put("URL", _url);
 BA.debugLineNum = 330;BA.debugLine="Dim Job As HttpJob";
Debug.ShouldStop(512);
_job = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("Job", _job);
 BA.debugLineNum = 331;BA.debugLine="Job.Initialize(\"Gemini\", Me)";
Debug.ShouldStop(1024);
_job.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,subdeck_module.processBA,(Object)(BA.ObjectToString("Gemini")),(Object)(subdeck_module.getObject()));
 BA.debugLineNum = 333;BA.debugLine="Dim prompt As String = _ 	\"Create flashcards from";
Debug.ShouldStop(4096);
_prompt = RemoteObject.concat(RemoteObject.createImmutable("Create flashcards from the Topic below."),RemoteObject.createImmutable("RULES:"),RemoteObject.createImmutable("- Return ONLY valid JSON"),RemoteObject.createImmutable("- No explanations"),RemoteObject.createImmutable("- No markdown"),RemoteObject.createImmutable("- Must have 20+ Flashcards"),RemoteObject.createImmutable("- Format must exactly follow this structure:"),RemoteObject.createImmutable("{"),RemoteObject.createImmutable("\"flashcards\": ["),RemoteObject.createImmutable("{"),RemoteObject.createImmutable("\"question\": \"Question here\","),RemoteObject.createImmutable("\"answer\": \"Answer here\""),RemoteObject.createImmutable("}"),RemoteObject.createImmutable("]"),RemoteObject.createImmutable("}"),RemoteObject.createImmutable("Topic:"),_topic);Debug.locals.put("prompt", _prompt);Debug.locals.put("prompt", _prompt);
 BA.debugLineNum = 351;BA.debugLine="Dim root As Map";
Debug.ShouldStop(1073741824);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("root", _root);
 BA.debugLineNum = 352;BA.debugLine="root.Initialize";
Debug.ShouldStop(-2147483648);
_root.runVoidMethod ("Initialize");
 BA.debugLineNum = 354;BA.debugLine="Dim contents As List";
Debug.ShouldStop(2);
_contents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("contents", _contents);
 BA.debugLineNum = 355;BA.debugLine="contents.Initialize";
Debug.ShouldStop(4);
_contents.runVoidMethod ("Initialize");
 BA.debugLineNum = 357;BA.debugLine="Dim contentItem As Map";
Debug.ShouldStop(16);
_contentitem = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("contentItem", _contentitem);
 BA.debugLineNum = 358;BA.debugLine="contentItem.Initialize";
Debug.ShouldStop(32);
_contentitem.runVoidMethod ("Initialize");
 BA.debugLineNum = 360;BA.debugLine="Dim parts As List";
Debug.ShouldStop(128);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("parts", _parts);
 BA.debugLineNum = 361;BA.debugLine="parts.Initialize";
Debug.ShouldStop(256);
_parts.runVoidMethod ("Initialize");
 BA.debugLineNum = 363;BA.debugLine="Dim textPart As Map";
Debug.ShouldStop(1024);
_textpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("textPart", _textpart);
 BA.debugLineNum = 364;BA.debugLine="textPart.Initialize";
Debug.ShouldStop(2048);
_textpart.runVoidMethod ("Initialize");
 BA.debugLineNum = 366;BA.debugLine="textPart.Put(\"text\", prompt)";
Debug.ShouldStop(8192);
_textpart.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("text"))),(Object)((_prompt)));
 BA.debugLineNum = 367;BA.debugLine="parts.Add(textPart)";
Debug.ShouldStop(16384);
_parts.runVoidMethod ("Add",(Object)((_textpart.getObject())));
 BA.debugLineNum = 369;BA.debugLine="contentItem.Put(\"parts\", parts)";
Debug.ShouldStop(65536);
_contentitem.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("parts"))),(Object)((_parts.getObject())));
 BA.debugLineNum = 370;BA.debugLine="contents.Add(contentItem)";
Debug.ShouldStop(131072);
_contents.runVoidMethod ("Add",(Object)((_contentitem.getObject())));
 BA.debugLineNum = 371;BA.debugLine="root.Put(\"contents\", contents)";
Debug.ShouldStop(262144);
_root.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("contents"))),(Object)((_contents.getObject())));
 BA.debugLineNum = 373;BA.debugLine="Dim gen As JSONGenerator";
Debug.ShouldStop(1048576);
_gen = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("gen", _gen);
 BA.debugLineNum = 374;BA.debugLine="gen.Initialize(root)";
Debug.ShouldStop(2097152);
_gen.runVoidMethod ("Initialize",(Object)(_root));
 BA.debugLineNum = 376;BA.debugLine="Dim json As String = gen.ToString";
Debug.ShouldStop(8388608);
_json = _gen.runMethod(true,"ToString");Debug.locals.put("json", _json);Debug.locals.put("json", _json);
 BA.debugLineNum = 378;BA.debugLine="Log(\"REQUEST: \")";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527197492",RemoteObject.createImmutable("REQUEST: "),0);
 BA.debugLineNum = 379;BA.debugLine="Log(json)";
Debug.ShouldStop(67108864);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527197493",_json,0);
 BA.debugLineNum = 381;BA.debugLine="Job.PostString(URL, json)";
Debug.ShouldStop(268435456);
_job.runClassMethod (b4a.example.httpjob.class, "_poststring" /*RemoteObject*/ ,(Object)(_url),(Object)(_json));
 BA.debugLineNum = 382;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
Debug.ShouldStop(536870912);
_job.runClassMethod (b4a.example.httpjob.class, "_getrequest" /*RemoteObject*/ ).runVoidMethod ("SetContentType",(Object)(RemoteObject.createImmutable("application/json")));
 BA.debugLineNum = 384;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
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
 //BA.debugLineNum = 34;BA.debugLine="Private topic_et As EditText";
subdeck_module.mostCurrent._topic_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private topic_panel As Panel";
subdeck_module.mostCurrent._topic_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Dim j As JSON";
subdeck_module.mostCurrent._j = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter");
 //BA.debugLineNum = 37;BA.debugLine="Private cc As ContentChooser";
subdeck_module.mostCurrent._cc = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.ContentChooser");
 //BA.debugLineNum = 38;BA.debugLine="Private pickPDFBtn As Button";
subdeck_module.mostCurrent._pickpdfbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Dim api1 As String = \"AIzaSyAGccTYG-Mscl_16Z72t\"";
subdeck_module.mostCurrent._api1 = BA.ObjectToString("AIzaSyAGccTYG-Mscl_16Z72t");
 //BA.debugLineNum = 40;BA.debugLine="Dim api2 As String = \"-GIN9ITMdrDGhQ\"";
subdeck_module.mostCurrent._api2 = BA.ObjectToString("-GIN9ITMdrDGhQ");
 //BA.debugLineNum = 41;BA.debugLine="Dim MyAPIKey As String = api1&api2";
subdeck_module.mostCurrent._myapikey = RemoteObject.concat(subdeck_module.mostCurrent._api1,subdeck_module.mostCurrent._api2);
 //BA.debugLineNum = 42;BA.debugLine="Dim AIGlobalText As String";
subdeck_module.mostCurrent._aiglobaltext = RemoteObject.createImmutable("");
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _goback_click() throws Exception{
try {
		Debug.PushSubsStack("goback_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,179);
if (RapidSub.canDelegate("goback_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","goback_click");}
 BA.debugLineNum = 179;BA.debugLine="Private Sub goback_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 181;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1048576);
subdeck_module.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _jobdone(RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,387);
if (RapidSub.canDelegate("jobdone")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","jobdone", _job);}
RemoteObject _response = RemoteObject.createImmutable("");
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _candidates = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _candidate = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _content = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _firstpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _aitext = RemoteObject.createImmutable("");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
Debug.locals.put("job", _job);
 BA.debugLineNum = 387;BA.debugLine="Sub JobDone (job As HttpJob)";
Debug.ShouldStop(4);
 BA.debugLineNum = 388;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(8);
subdeck_module.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 389;BA.debugLine="If job.Success Then";
Debug.ShouldStop(16);
if (_job.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 391;BA.debugLine="Dim response As String = job.GetString";
Debug.ShouldStop(64);
_response = _job.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ );Debug.locals.put("response", _response);Debug.locals.put("response", _response);
 BA.debugLineNum = 392;BA.debugLine="Log(response)";
Debug.ShouldStop(128);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527262981",_response,0);
 BA.debugLineNum = 394;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(512);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 395;BA.debugLine="jp.Initialize(response)";
Debug.ShouldStop(1024);
_jp.runVoidMethod ("Initialize",(Object)(_response));
 BA.debugLineNum = 397;BA.debugLine="Dim root As Map = jp.NextObject";
Debug.ShouldStop(4096);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _jp.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 398;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
Debug.ShouldStop(8192);
_candidates = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_candidates = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("candidates")))));Debug.locals.put("candidates", _candidates);Debug.locals.put("candidates", _candidates);
 BA.debugLineNum = 399;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
Debug.ShouldStop(16384);
_candidate = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_candidate = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidates.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("candidate", _candidate);Debug.locals.put("candidate", _candidate);
 BA.debugLineNum = 400;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
Debug.ShouldStop(32768);
_content = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_content = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidate.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("content")))));Debug.locals.put("content", _content);Debug.locals.put("content", _content);
 BA.debugLineNum = 401;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
Debug.ShouldStop(65536);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_parts = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _content.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("parts")))));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 402;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
Debug.ShouldStop(131072);
_firstpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_firstpart = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _parts.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("firstPart", _firstpart);Debug.locals.put("firstPart", _firstpart);
 BA.debugLineNum = 404;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
Debug.ShouldStop(524288);
_aitext = BA.ObjectToString(_firstpart.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("text")))));Debug.locals.put("aiText", _aitext);Debug.locals.put("aiText", _aitext);
 BA.debugLineNum = 405;BA.debugLine="AIGlobalText = aiText";
Debug.ShouldStop(1048576);
subdeck_module.mostCurrent._aiglobaltext = _aitext;
 BA.debugLineNum = 407;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
Debug.ShouldStop(4194304);
subdeck_module.mostCurrent.__c.getField(false,"File").runVoidMethod ("WriteString",(Object)(subdeck_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("cached_ai.txt")),(Object)(_aitext));
 BA.debugLineNum = 409;BA.debugLine="Dim flashcards As List = ParseFlashcard(aiText)";
Debug.ShouldStop(16777216);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashcards = _parseflashcard(_aitext);Debug.locals.put("flashcards", _flashcards);Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 411;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.G";
Debug.ShouldStop(67108864);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 413;BA.debugLine="LVSubdecks.AddSingleLine(topic_et.Text)";
Debug.ShouldStop(268435456);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(subdeck_module.mostCurrent._topic_et.runMethod(true,"getText"))));
 BA.debugLineNum = 414;BA.debugLine="tappeddeck.Put(topic_et.Text, flashcards)";
Debug.ShouldStop(536870912);
_tappeddeck.runVoidMethod ("Put",(Object)((subdeck_module.mostCurrent._topic_et.runMethod(true,"getText"))),(Object)((_flashcards.getObject())));
 BA.debugLineNum = 415;BA.debugLine="topic_et.Text = \"\"";
Debug.ShouldStop(1073741824);
subdeck_module.mostCurrent._topic_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 416;BA.debugLine="SaveDecks";
Debug.ShouldStop(-2147483648);
_savedecks();
 }else {
 BA.debugLineNum = 419;BA.debugLine="Log(job.ErrorMessage)";
Debug.ShouldStop(4);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527263008",_job.getField(true,"_errormessage" /*RemoteObject*/ ),0);
 BA.debugLineNum = 420;BA.debugLine="Msgbox(\"Error making your AI Flashcards\", \"Error";
Debug.ShouldStop(8);
subdeck_module.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Error making your AI Flashcards")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.mostCurrent.activityBA);
 };
 BA.debugLineNum = 423;BA.debugLine="job.Release";
Debug.ShouldStop(64);
_job.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 424;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("LVSubdecks_ItemClick (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,214);
if (RapidSub.canDelegate("lvsubdecks_itemclick")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","lvsubdecks_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 214;BA.debugLine="Private Sub LVSubdecks_ItemClick (Position As Int,";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 216;BA.debugLine="selectedsubdeck = Value 'gives the chsoen subdeck";
Debug.ShouldStop(8388608);
subdeck_module._selectedsubdeck = BA.ObjectToString(_value);
 BA.debugLineNum = 218;BA.debugLine="StartActivity(Card_Module)";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent.__c.runVoidMethod ("StartActivity",subdeck_module.processBA,(Object)((subdeck_module.mostCurrent._card_module.getObject())));
 BA.debugLineNum = 219;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("LVSubdecks_ItemLongClick (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,239);
if (RapidSub.canDelegate("lvsubdecks_itemlongclick")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","lvsubdecks_itemlongclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 239;BA.debugLine="Private Sub LVSubdecks_ItemLongClick (Position As";
Debug.ShouldStop(16384);
 BA.debugLineNum = 240;BA.debugLine="alterpanel.Visible = True";
Debug.ShouldStop(32768);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 241;BA.debugLine="selectedsubdeck = Value";
Debug.ShouldStop(65536);
subdeck_module._selectedsubdeck = BA.ObjectToString(_value);
 BA.debugLineNum = 242;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _parseflashcard(RemoteObject _jsontext) throws Exception{
try {
		Debug.PushSubsStack("ParseFlashcard (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,427);
if (RapidSub.canDelegate("parseflashcard")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","parseflashcard", _jsontext);}
RemoteObject _flashcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _cards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _question = RemoteObject.createImmutable("");
RemoteObject _answer = RemoteObject.createImmutable("");
Debug.locals.put("jsonText", _jsontext);
 BA.debugLineNum = 427;BA.debugLine="Sub ParseFlashcard (jsonText As String) As List";
Debug.ShouldStop(1024);
 BA.debugLineNum = 429;BA.debugLine="Dim flashcard As List";
Debug.ShouldStop(4096);
_flashcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("flashcard", _flashcard);
 BA.debugLineNum = 430;BA.debugLine="flashcard.Initialize";
Debug.ShouldStop(8192);
_flashcard.runVoidMethod ("Initialize");
 BA.debugLineNum = 431;BA.debugLine="Try";
Debug.ShouldStop(16384);
try { BA.debugLineNum = 433;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(65536);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 434;BA.debugLine="jp.Initialize(jsonText)";
Debug.ShouldStop(131072);
_jp.runVoidMethod ("Initialize",(Object)(_jsontext));
 BA.debugLineNum = 436;BA.debugLine="Dim root As Map = jp.NextObject";
Debug.ShouldStop(524288);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _jp.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 438;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
Debug.ShouldStop(2097152);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("flashcards")))));Debug.locals.put("flashcards", _flashcards);Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 440;BA.debugLine="For Each card As Map In flashcards";
Debug.ShouldStop(8388608);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group8 = _flashcards;
final int groupLen8 = group8.runMethod(true,"getSize").<Integer>get()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group8.runMethod(false,"Get",index8));Debug.locals.put("card", _card);
Debug.locals.put("card", _card);
 BA.debugLineNum = 442;BA.debugLine="Dim cards As Map";
Debug.ShouldStop(33554432);
_cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("cards", _cards);
 BA.debugLineNum = 443;BA.debugLine="cards.initialize";
Debug.ShouldStop(67108864);
_cards.runVoidMethod ("Initialize");
 BA.debugLineNum = 445;BA.debugLine="Dim question As String = card.Get(\"question\")";
Debug.ShouldStop(268435456);
_question = BA.ObjectToString(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("question")))));Debug.locals.put("question", _question);Debug.locals.put("question", _question);
 BA.debugLineNum = 447;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
Debug.ShouldStop(1073741824);
_answer = BA.ObjectToString(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("answer")))));Debug.locals.put("answer", _answer);Debug.locals.put("answer", _answer);
 BA.debugLineNum = 449;BA.debugLine="cards.Put(\"Q\", question)";
Debug.ShouldStop(1);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)((_question)));
 BA.debugLineNum = 450;BA.debugLine="cards.Put(\"A\", answer)";
Debug.ShouldStop(2);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)((_answer)));
 BA.debugLineNum = 451;BA.debugLine="flashcard.Add(cards)";
Debug.ShouldStop(4);
_flashcard.runVoidMethod ("Add",(Object)((_cards.getObject())));
 BA.debugLineNum = 453;BA.debugLine="Log(\"===================\")";
Debug.ShouldStop(16);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527328538",RemoteObject.createImmutable("==================="),0);
 BA.debugLineNum = 454;BA.debugLine="Log(\"QUESTION: \" & question)";
Debug.ShouldStop(32);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527328539",RemoteObject.concat(RemoteObject.createImmutable("QUESTION: "),_question),0);
 BA.debugLineNum = 455;BA.debugLine="Log(\"ANSWER: \" & answer)";
Debug.ShouldStop(64);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527328540",RemoteObject.concat(RemoteObject.createImmutable("ANSWER: "),_answer),0);
 }
}Debug.locals.put("card", _card);
;
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e21) {
			BA.rdebugUtils.runVoidMethod("setLastException",subdeck_module.processBA, e21.toString()); BA.debugLineNum = 461;BA.debugLine="Log(\"INVALID JSON\")";
Debug.ShouldStop(4096);
subdeck_module.mostCurrent.__c.runVoidMethod ("LogImpl","527328546",RemoteObject.createImmutable("INVALID JSON"),0);
 };
 BA.debugLineNum = 466;BA.debugLine="Return flashcard";
Debug.ShouldStop(131072);
if (true) return _flashcard;
 BA.debugLineNum = 467;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable(null);
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
		Debug.PushSubsStack("Refresh (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,93);
if (RapidSub.canDelegate("refresh")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","refresh");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _deckname = RemoteObject.createImmutable("");
 BA.debugLineNum = 93;BA.debugLine="Sub Refresh";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 94;BA.debugLine="LVSubdecks.clear";
Debug.ShouldStop(536870912);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("Clear");
 BA.debugLineNum = 96;BA.debugLine="Dim tappeddeck As Map = alldecks.Get(selecteddeck";
Debug.ShouldStop(-2147483648);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._alldecks.runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._selecteddeck))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 99;BA.debugLine="For Each deckName As String In tappeddeck.keys";
Debug.ShouldStop(4);
{
final RemoteObject group3 = _tappeddeck.runMethod(false,"Keys");
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_deckname = BA.ObjectToString(group3.runMethod(false,"Get",index3));Debug.locals.put("deckName", _deckname);
Debug.locals.put("deckName", _deckname);
 BA.debugLineNum = 100;BA.debugLine="LVSubdecks.AddSingleLine(deckName)";
Debug.ShouldStop(8);
subdeck_module.mostCurrent._lvsubdecks.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(_deckname)));
 }
}Debug.locals.put("deckName", _deckname);
;
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("renamesubdeck_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,230);
if (RapidSub.canDelegate("renamesubdeck_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","renamesubdeck_click");}
 BA.debugLineNum = 230;BA.debugLine="Private Sub renamesubdeck_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 231;BA.debugLine="alterpanel.Visible = False";
Debug.ShouldStop(64);
subdeck_module.mostCurrent._alterpanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 232;BA.debugLine="renamepanel.visible = True";
Debug.ShouldStop(128);
subdeck_module.mostCurrent._renamepanel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 233;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("SaveDecks (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,89);
if (RapidSub.canDelegate("savedecks")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","savedecks");}
 BA.debugLineNum = 89;BA.debugLine="Sub SaveDecks";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 90;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent._flashcardactivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("deck_data")),(Object)((subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .getObject())));
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("startArbtn_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,200);
if (RapidSub.canDelegate("startarbtn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","startarbtn_click");}
 BA.debugLineNum = 200;BA.debugLine="Private Sub startArbtn_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 202;BA.debugLine="If number_of_cards = 0 Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",subdeck_module._number_of_cards,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 203;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
Debug.ShouldStop(1024);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("No cards available")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 204;BA.debugLine="Return";
Debug.ShouldStop(2048);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 206;BA.debugLine="StartActivity(all_active_recall)";
Debug.ShouldStop(8192);
subdeck_module.mostCurrent.__c.runVoidMethod ("StartActivity",subdeck_module.processBA,(Object)((subdeck_module.mostCurrent._all_active_recall.getObject())));
 BA.debugLineNum = 207;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _topic_btn_click() throws Exception{
try {
		Debug.PushSubsStack("topic_btn_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,303);
if (RapidSub.canDelegate("topic_btn_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","topic_btn_click");}
RemoteObject _getsubdeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _names = RemoteObject.createImmutable("");
 BA.debugLineNum = 303;BA.debugLine="Private Sub topic_btn_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 304;BA.debugLine="If topic_et.Text = \"\" Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._topic_et.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 305;BA.debugLine="Msgbox(\"Invalid Subdeck Topic\", \"Error\")";
Debug.ShouldStop(65536);
subdeck_module.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Invalid Subdeck Topic")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.mostCurrent.activityBA);
 BA.debugLineNum = 306;BA.debugLine="Return";
Debug.ShouldStop(131072);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 309;BA.debugLine="Dim getsubdeck As List";
Debug.ShouldStop(1048576);
_getsubdeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("getsubdeck", _getsubdeck);
 BA.debugLineNum = 310;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(2097152);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), subdeck_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((subdeck_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 311;BA.debugLine="For Each names As String In tappeddeck.keys";
Debug.ShouldStop(4194304);
{
final RemoteObject group7 = _tappeddeck.runMethod(false,"Keys");
final int groupLen7 = group7.runMethod(true,"getSize").<Integer>get()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_names = BA.ObjectToString(group7.runMethod(false,"Get",index7));Debug.locals.put("names", _names);
Debug.locals.put("names", _names);
 BA.debugLineNum = 312;BA.debugLine="getsubdeck = tappeddeck.Get(selectedsubdeck)";
Debug.ShouldStop(8388608);
_getsubdeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((subdeck_module._selectedsubdeck))));Debug.locals.put("getsubdeck", _getsubdeck);
 BA.debugLineNum = 313;BA.debugLine="If topic_et.Text = names Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",subdeck_module.mostCurrent._topic_et.runMethod(true,"getText"),_names)) { 
 BA.debugLineNum = 314;BA.debugLine="MsgboxAsync(\"Sub Deck Name Already Exist\", \"Err";
Debug.ShouldStop(33554432);
subdeck_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Sub Deck Name Already Exist")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),subdeck_module.processBA);
 BA.debugLineNum = 315;BA.debugLine="Return";
Debug.ShouldStop(67108864);
if (true) return RemoteObject.createImmutable("");
 };
 }
}Debug.locals.put("names", _names);
;
 BA.debugLineNum = 319;BA.debugLine="ProgressDialogShow(\"Generating Flashcards...\")";
Debug.ShouldStop(1073741824);
subdeck_module.mostCurrent.__c.runVoidMethod ("ProgressDialogShow",subdeck_module.mostCurrent.activityBA,(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Generating Flashcards..."))));
 BA.debugLineNum = 321;BA.debugLine="GenerateFlashCards(topic_et.Text)";
Debug.ShouldStop(1);
_generateflashcards(subdeck_module.mostCurrent._topic_et.runMethod(true,"getText"));
 BA.debugLineNum = 323;BA.debugLine="topic_panel.Visible = False";
Debug.ShouldStop(4);
subdeck_module.mostCurrent._topic_panel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 324;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _topic_cancel_click() throws Exception{
try {
		Debug.PushSubsStack("topic_cancel_Click (subdeck_module) ","subdeck_module",26,subdeck_module.mostCurrent.activityBA,subdeck_module.mostCurrent,298);
if (RapidSub.canDelegate("topic_cancel_click")) { return b4a.example.subdeck_module.remoteMe.runUserSub(false, "subdeck_module","topic_cancel_click");}
 BA.debugLineNum = 298;BA.debugLine="Private Sub topic_cancel_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 299;BA.debugLine="topic_panel.Visible = False";
Debug.ShouldStop(1024);
subdeck_module.mostCurrent._topic_panel.runMethod(true,"setVisible",subdeck_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 300;BA.debugLine="topic_et.Text = \"\"";
Debug.ShouldStop(2048);
subdeck_module.mostCurrent._topic_et.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 301;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}