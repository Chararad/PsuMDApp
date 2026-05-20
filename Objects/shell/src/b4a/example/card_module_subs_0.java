package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class card_module_subs_0 {


public static RemoteObject  _activerecall_click() throws Exception{
try {
		Debug.PushSubsStack("activerecall_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,160);
if (RapidSub.canDelegate("activerecall_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activerecall_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _number_of_cards = RemoteObject.createImmutable(0);
 BA.debugLineNum = 160;BA.debugLine="Private Sub activerecall_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 162;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(2);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 163;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(4);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 164;BA.debugLine="Dim number_of_cards As Int = subdeckcards.size";
Debug.ShouldStop(8);
_number_of_cards = _subdeckcards.runMethod(true,"getSize");Debug.locals.put("number_of_cards", _number_of_cards);Debug.locals.put("number_of_cards", _number_of_cards);
 BA.debugLineNum = 166;BA.debugLine="If number_of_cards = 0 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",_number_of_cards,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 167;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
Debug.ShouldStop(64);
card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("No cards available")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),card_module.processBA);
 BA.debugLineNum = 168;BA.debugLine="Return";
Debug.ShouldStop(128);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 170;BA.debugLine="StartActivity(active_recall)";
Debug.ShouldStop(512);
card_module.mostCurrent.__c.runVoidMethod ("StartActivity",card_module.processBA,(Object)((card_module.mostCurrent._active_recall.getObject())));
 BA.debugLineNum = 171;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
		Debug.PushSubsStack("Activity_Create (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,31);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activity_create", _firsttime);}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 32;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(-2147483648);
switch (BA.switchObjectToInt(card_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 34;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout\")";
Debug.ShouldStop(4);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayout")),card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark\")";
Debug.ShouldStop(16);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayoutDark")),card_module.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 40;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout2\")";
Debug.ShouldStop(256);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayout2")),card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark2\")";
Debug.ShouldStop(1024);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayoutDark2")),card_module.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 46;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 47;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout3\")";
Debug.ShouldStop(16384);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayout3")),card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 49;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark3\")";
Debug.ShouldStop(65536);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayoutDark3")),card_module.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 53;BA.debugLine="cc.Initialize(\"CC\")";
Debug.ShouldStop(1048576);
card_module.mostCurrent._cc.runVoidMethod ("Initialize",(Object)(RemoteObject.createImmutable("CC")));
 BA.debugLineNum = 55;BA.debugLine="subdecklabel.Text = Subdeck_Module.selectedsubdec";
Debug.ShouldStop(4194304);
card_module.mostCurrent._subdecklabel.runMethod(true,"setText",BA.ObjectToCharSequence(card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ));
 BA.debugLineNum = 57;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(16777216);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 58;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(33554432);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 61;BA.debugLine="ShowSubdeckCards(subdeckcards)";
Debug.ShouldStop(268435456);
_showsubdeckcards(_subdeckcards);
 BA.debugLineNum = 63;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("Activity_Pause (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,149);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 149;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 151;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("Activity_Resume (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,137);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activity_resume");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 137;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(256);
 BA.debugLineNum = 139;BA.debugLine="If active_recall.praise = True Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._active_recall._praise /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 140;BA.debugLine="active_recall.praise = False";
Debug.ShouldStop(2048);
card_module.mostCurrent._active_recall._praise /*RemoteObject*/  = card_module.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 141;BA.debugLine="MsgboxAsync(\"You Finished Your Sub-Deck\", \"Congr";
Debug.ShouldStop(4096);
card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("You Finished Your Sub-Deck")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Congratulations"))),card_module.processBA);
 };
 BA.debugLineNum = 144;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(32768);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 145;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(65536);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 146;BA.debugLine="ShowSubdeckCards(subdeckcards)";
Debug.ShouldStop(131072);
_showsubdeckcards(_subdeckcards);
 BA.debugLineNum = 147;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("addbtn_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,173);
if (RapidSub.canDelegate("addbtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","addbtn_click");}
 BA.debugLineNum = 173;BA.debugLine="Private Sub addbtn_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 175;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
Debug.ShouldStop(16384);
card_module._subdeck = card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ;
 BA.debugLineNum = 176;BA.debugLine="StartActivity(add_card_module2)";
Debug.ShouldStop(32768);
card_module.mostCurrent.__c.runVoidMethod ("StartActivity",card_module.processBA,(Object)((card_module.mostCurrent._add_card_module2.getObject())));
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
public static RemoteObject  _backbtn_click() throws Exception{
try {
		Debug.PushSubsStack("backbtn_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,155);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","backbtn_click");}
 BA.debugLineNum = 155;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 157;BA.debugLine="Activity.Finish";
Debug.ShouldStop(268435456);
card_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 158;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("canceldelete_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,188);
if (RapidSub.canDelegate("canceldelete_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","canceldelete_click");}
 BA.debugLineNum = 188;BA.debugLine="Private Sub canceldelete_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 189;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(268435456);
card_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",card_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 190;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("CC_RESULT (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,197);
if (RapidSub.canDelegate("cc_result")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","cc_result", _success, _dir, _filename);}
Debug.locals.put("Success", _success);
Debug.locals.put("dir", _dir);
Debug.locals.put("fileName", _filename);
 BA.debugLineNum = 197;BA.debugLine="Sub CC_RESULT (Success As Boolean, dir As String ,";
Debug.ShouldStop(16);
 BA.debugLineNum = 199;BA.debugLine="If Success Then";
Debug.ShouldStop(64);
if (_success.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 200;BA.debugLine="Log(\"Dir: \" & dir)";
Debug.ShouldStop(128);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118022403",RemoteObject.concat(RemoteObject.createImmutable("Dir: "),_dir),0);
 BA.debugLineNum = 201;BA.debugLine="Log(\"File: \" & fileName)";
Debug.ShouldStop(256);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118022404",RemoteObject.concat(RemoteObject.createImmutable("File: "),_filename),0);
 BA.debugLineNum = 203;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
Debug.ShouldStop(1024);
card_module.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(_dir),(Object)(_filename),(Object)(card_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("temp.pdf")));
 BA.debugLineNum = 204;BA.debugLine="Log(\"PDF saved as temp.pdf\")";
Debug.ShouldStop(2048);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118022407",RemoteObject.createImmutable("PDF saved as temp.pdf"),0);
 BA.debugLineNum = 206;BA.debugLine="File.Copy(dir, fileName, File.DirInternal, \"temp";
Debug.ShouldStop(8192);
card_module.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(_dir),(Object)(_filename),(Object)(card_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("temp.pdf")));
 BA.debugLineNum = 207;BA.debugLine="ProgressDialogShow(\"Generating Cards...\")";
Debug.ShouldStop(16384);
card_module.mostCurrent.__c.runVoidMethod ("ProgressDialogShow",card_module.mostCurrent.activityBA,(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Generating Cards..."))));
 BA.debugLineNum = 208;BA.debugLine="GenerateFlashCardsFromPDF";
Debug.ShouldStop(32768);
_generateflashcardsfrompdf();
 }else {
 BA.debugLineNum = 211;BA.debugLine="Log(\"User Cancelled\")";
Debug.ShouldStop(262144);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118022414",RemoteObject.createImmutable("User Cancelled"),0);
 };
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
public static RemoteObject  _confirmdelete_click() throws Exception{
try {
		Debug.PushSubsStack("confirmdelete_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,179);
if (RapidSub.canDelegate("confirmdelete_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","confirmdelete_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _cards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 179;BA.debugLine="Private Sub confirmdelete_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 180;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(524288);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 181;BA.debugLine="Dim cards As List = tappedDeck.Get(Subdeck_Module";
Debug.ShouldStop(1048576);
_cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_cards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("cards", _cards);Debug.locals.put("cards", _cards);
 BA.debugLineNum = 183;BA.debugLine="cards.RemoveAt(numtag)";
Debug.ShouldStop(4194304);
_cards.runVoidMethod ("RemoveAt",(Object)(card_module._numtag));
 BA.debugLineNum = 184;BA.debugLine="ShowSubdeckCards(cards)";
Debug.ShouldStop(8388608);
_showsubdeckcards(_cards);
 BA.debugLineNum = 185;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(16777216);
card_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",card_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 186;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _deletebtn_click() throws Exception{
try {
		Debug.PushSubsStack("deletebtn_click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,128);
if (RapidSub.canDelegate("deletebtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","deletebtn_click");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _index = RemoteObject.createImmutable(0);
 BA.debugLineNum = 128;BA.debugLine="Sub deletebtn_click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 130;BA.debugLine="Dim b As Button = Sender";
Debug.ShouldStop(2);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), card_module.mostCurrent.__c.runMethod(false,"Sender",card_module.mostCurrent.activityBA));Debug.locals.put("b", _b);Debug.locals.put("b", _b);
 BA.debugLineNum = 131;BA.debugLine="Dim index As Int = b.Tag";
Debug.ShouldStop(4);
_index = BA.numberCast(int.class, _b.runMethod(false,"getTag"));Debug.locals.put("index", _index);Debug.locals.put("index", _index);
 BA.debugLineNum = 132;BA.debugLine="numtag = index";
Debug.ShouldStop(8);
card_module._numtag = _index;
 BA.debugLineNum = 133;BA.debugLine="deleteconfirmation.Visible = True";
Debug.ShouldStop(16);
card_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",card_module.mostCurrent.__c.getField(true,"True"));
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
public static RemoteObject  _editbtn_click() throws Exception{
try {
		Debug.PushSubsStack("editbtn_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,116);
if (RapidSub.canDelegate("editbtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","editbtn_click");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _index = RemoteObject.createImmutable(0);
 BA.debugLineNum = 116;BA.debugLine="Sub editbtn_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 118;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
Debug.ShouldStop(2097152);
card_module._subdeck = card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ;
 BA.debugLineNum = 119;BA.debugLine="Dim b As Button = Sender";
Debug.ShouldStop(4194304);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), card_module.mostCurrent.__c.runMethod(false,"Sender",card_module.mostCurrent.activityBA));Debug.locals.put("b", _b);Debug.locals.put("b", _b);
 BA.debugLineNum = 120;BA.debugLine="Dim index As Int = b.Tag";
Debug.ShouldStop(8388608);
_index = BA.numberCast(int.class, _b.runMethod(false,"getTag"));Debug.locals.put("index", _index);Debug.locals.put("index", _index);
 BA.debugLineNum = 121;BA.debugLine="editindex = index";
Debug.ShouldStop(16777216);
card_module._editindex = _index;
 BA.debugLineNum = 122;BA.debugLine="isEdit = True";
Debug.ShouldStop(33554432);
card_module._isedit = card_module.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 124;BA.debugLine="StartActivity(add_card_module2)";
Debug.ShouldStop(134217728);
card_module.mostCurrent.__c.runVoidMethod ("StartActivity",card_module.processBA,(Object)((card_module.mostCurrent._add_card_module2.getObject())));
 BA.debugLineNum = 126;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ftf_btn_click() throws Exception{
try {
		Debug.PushSubsStack("ftf_btn_Click (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,193);
if (RapidSub.canDelegate("ftf_btn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","ftf_btn_click");}
 BA.debugLineNum = 193;BA.debugLine="Private Sub ftf_btn_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 194;BA.debugLine="cc.Show(\"application/pdf\", \"Select PDF\")";
Debug.ShouldStop(2);
card_module.mostCurrent._cc.runVoidMethod ("Show",card_module.processBA,(Object)(BA.ObjectToString("application/pdf")),(Object)(RemoteObject.createImmutable("Select PDF")));
 BA.debugLineNum = 195;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _generateflashcardsfrompdf() throws Exception{
try {
		Debug.PushSubsStack("GenerateFlashCardsFromPDF (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,215);
if (RapidSub.canDelegate("generateflashcardsfrompdf")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","generateflashcardsfrompdf");}
RemoteObject _in = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.InputStreamWrapper");
RemoteObject _bytes = null;
RemoteObject _su = RemoteObject.declareNull("anywheresoftware.b4a.objects.StringUtils");
RemoteObject _base64 = RemoteObject.createImmutable("");
RemoteObject _prompt = RemoteObject.createImmutable("");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _contents = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _contentitem = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _filepart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _inline = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _textpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _gen = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
RemoteObject _json = RemoteObject.createImmutable("");
RemoteObject _url = RemoteObject.createImmutable("");
RemoteObject _job = RemoteObject.declareNull("b4a.example.httpjob");
 BA.debugLineNum = 215;BA.debugLine="Sub GenerateFlashCardsFromPDF";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 218;BA.debugLine="Dim In As InputStream = File.OpenInput(File.DirIn";
Debug.ShouldStop(33554432);
_in = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.InputStreamWrapper");
_in = card_module.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenInput",(Object)(card_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("temp.pdf")));Debug.locals.put("In", _in);Debug.locals.put("In", _in);
 BA.debugLineNum = 219;BA.debugLine="Dim bytes() As Byte = Bit.InputStreamToBytes(In)";
Debug.ShouldStop(67108864);
_bytes = card_module.mostCurrent.__c.getField(false,"Bit").runMethod(false,"InputStreamToBytes",(Object)((_in.getObject())));Debug.locals.put("bytes", _bytes);Debug.locals.put("bytes", _bytes);
 BA.debugLineNum = 220;BA.debugLine="In.Close";
Debug.ShouldStop(134217728);
_in.runVoidMethod ("Close");
 BA.debugLineNum = 224;BA.debugLine="Dim su As StringUtils";
Debug.ShouldStop(-2147483648);
_su = RemoteObject.createNew ("anywheresoftware.b4a.objects.StringUtils");Debug.locals.put("su", _su);
 BA.debugLineNum = 225;BA.debugLine="Dim base64 As String = su.EncodeBase64(bytes)";
Debug.ShouldStop(1);
_base64 = _su.runMethod(true,"EncodeBase64",(Object)(_bytes));Debug.locals.put("base64", _base64);Debug.locals.put("base64", _base64);
 BA.debugLineNum = 228;BA.debugLine="Dim prompt As String = _     \"Create flashcards f";
Debug.ShouldStop(8);
_prompt = RemoteObject.concat(RemoteObject.createImmutable("Create flashcards from the provided PDF content."),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("RULES:"),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Extract ONLY from the PDF"),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Return ONLY valid JSON"),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- No markdown, no explanations"),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("- Format exactly:"),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("{"),RemoteObject.createImmutable("\"flashcards\": ["),RemoteObject.createImmutable("{"),RemoteObject.createImmutable("\"question\": \"...\","),RemoteObject.createImmutable("\"answer\": \"...\""),RemoteObject.createImmutable("}"),RemoteObject.createImmutable("]"),RemoteObject.createImmutable("}"));Debug.locals.put("prompt", _prompt);Debug.locals.put("prompt", _prompt);
 BA.debugLineNum = 245;BA.debugLine="Dim root As Map";
Debug.ShouldStop(1048576);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("root", _root);
 BA.debugLineNum = 246;BA.debugLine="root.Initialize";
Debug.ShouldStop(2097152);
_root.runVoidMethod ("Initialize");
 BA.debugLineNum = 248;BA.debugLine="Dim contents As List";
Debug.ShouldStop(8388608);
_contents = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("contents", _contents);
 BA.debugLineNum = 249;BA.debugLine="contents.Initialize";
Debug.ShouldStop(16777216);
_contents.runVoidMethod ("Initialize");
 BA.debugLineNum = 251;BA.debugLine="Dim contentItem As Map";
Debug.ShouldStop(67108864);
_contentitem = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("contentItem", _contentitem);
 BA.debugLineNum = 252;BA.debugLine="contentItem.Initialize";
Debug.ShouldStop(134217728);
_contentitem.runVoidMethod ("Initialize");
 BA.debugLineNum = 254;BA.debugLine="Dim parts As List";
Debug.ShouldStop(536870912);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("parts", _parts);
 BA.debugLineNum = 255;BA.debugLine="parts.Initialize";
Debug.ShouldStop(1073741824);
_parts.runVoidMethod ("Initialize");
 BA.debugLineNum = 258;BA.debugLine="Dim filePart As Map";
Debug.ShouldStop(2);
_filepart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("filePart", _filepart);
 BA.debugLineNum = 259;BA.debugLine="filePart.Initialize";
Debug.ShouldStop(4);
_filepart.runVoidMethod ("Initialize");
 BA.debugLineNum = 261;BA.debugLine="Dim inline As Map";
Debug.ShouldStop(16);
_inline = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("inline", _inline);
 BA.debugLineNum = 262;BA.debugLine="inline.Initialize";
Debug.ShouldStop(32);
_inline.runVoidMethod ("Initialize");
 BA.debugLineNum = 263;BA.debugLine="inline.Put(\"mime_type\", \"application/pdf\")";
Debug.ShouldStop(64);
_inline.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mime_type"))),(Object)((RemoteObject.createImmutable("application/pdf"))));
 BA.debugLineNum = 264;BA.debugLine="inline.Put(\"data\", base64)";
Debug.ShouldStop(128);
_inline.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("data"))),(Object)((_base64)));
 BA.debugLineNum = 266;BA.debugLine="filePart.Put(\"inline_data\", inline)";
Debug.ShouldStop(512);
_filepart.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("inline_data"))),(Object)((_inline.getObject())));
 BA.debugLineNum = 267;BA.debugLine="parts.Add(filePart)";
Debug.ShouldStop(1024);
_parts.runVoidMethod ("Add",(Object)((_filepart.getObject())));
 BA.debugLineNum = 270;BA.debugLine="Dim textPart As Map";
Debug.ShouldStop(8192);
_textpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("textPart", _textpart);
 BA.debugLineNum = 271;BA.debugLine="textPart.Initialize";
Debug.ShouldStop(16384);
_textpart.runVoidMethod ("Initialize");
 BA.debugLineNum = 272;BA.debugLine="textPart.Put(\"text\", prompt)";
Debug.ShouldStop(32768);
_textpart.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("text"))),(Object)((_prompt)));
 BA.debugLineNum = 273;BA.debugLine="parts.Add(textPart)";
Debug.ShouldStop(65536);
_parts.runVoidMethod ("Add",(Object)((_textpart.getObject())));
 BA.debugLineNum = 275;BA.debugLine="contentItem.Put(\"parts\", parts)";
Debug.ShouldStop(262144);
_contentitem.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("parts"))),(Object)((_parts.getObject())));
 BA.debugLineNum = 276;BA.debugLine="contents.Add(contentItem)";
Debug.ShouldStop(524288);
_contents.runVoidMethod ("Add",(Object)((_contentitem.getObject())));
 BA.debugLineNum = 278;BA.debugLine="root.Put(\"contents\", contents)";
Debug.ShouldStop(2097152);
_root.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("contents"))),(Object)((_contents.getObject())));
 BA.debugLineNum = 280;BA.debugLine="Dim gen As JSONGenerator";
Debug.ShouldStop(8388608);
_gen = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("gen", _gen);
 BA.debugLineNum = 281;BA.debugLine="gen.Initialize(root)";
Debug.ShouldStop(16777216);
_gen.runVoidMethod ("Initialize",(Object)(_root));
 BA.debugLineNum = 283;BA.debugLine="Dim json As String = gen.ToString";
Debug.ShouldStop(67108864);
_json = _gen.runMethod(true,"ToString");Debug.locals.put("json", _json);Debug.locals.put("json", _json);
 BA.debugLineNum = 285;BA.debugLine="Log(\"AUTO PDF REQUEST: \")";
Debug.ShouldStop(268435456);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118088006",RemoteObject.createImmutable("AUTO PDF REQUEST: "),0);
 BA.debugLineNum = 286;BA.debugLine="Log(json)";
Debug.ShouldStop(536870912);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118088007",_json,0);
 BA.debugLineNum = 288;BA.debugLine="Dim URL As String = \"https://generativelanguage.g";
Debug.ShouldStop(-2147483648);
_url = RemoteObject.concat(RemoteObject.createImmutable("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="),card_module.mostCurrent._myapikey);Debug.locals.put("URL", _url);Debug.locals.put("URL", _url);
 BA.debugLineNum = 290;BA.debugLine="Dim Job As HttpJob";
Debug.ShouldStop(2);
_job = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("Job", _job);
 BA.debugLineNum = 291;BA.debugLine="Job.Initialize(\"GeminiPDF\", Me)";
Debug.ShouldStop(4);
_job.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,card_module.processBA,(Object)(BA.ObjectToString("GeminiPDF")),(Object)(card_module.getObject()));
 BA.debugLineNum = 292;BA.debugLine="Job.PostString(URL, json)";
Debug.ShouldStop(8);
_job.runClassMethod (b4a.example.httpjob.class, "_poststring" /*RemoteObject*/ ,(Object)(_url),(Object)(_json));
 BA.debugLineNum = 293;BA.debugLine="Job.GetRequest.SetContentType(\"application/json\")";
Debug.ShouldStop(16);
_job.runClassMethod (b4a.example.httpjob.class, "_getrequest" /*RemoteObject*/ ).runVoidMethod ("SetContentType",(Object)(RemoteObject.createImmutable("application/json")));
 BA.debugLineNum = 295;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
 //BA.debugLineNum = 18;BA.debugLine="Private subdecklabel As Label";
card_module.mostCurrent._subdecklabel = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private ScrollView1 As ScrollView";
card_module.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private deleteconfirmation As Panel";
card_module.mostCurrent._deleteconfirmation = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Dim numtag As Int";
card_module._numtag = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 22;BA.debugLine="Dim j As JSON";
card_module.mostCurrent._j = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter");
 //BA.debugLineNum = 23;BA.debugLine="Private cc As ContentChooser";
card_module.mostCurrent._cc = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.ContentChooser");
 //BA.debugLineNum = 24;BA.debugLine="Private pickPDFBtn As Button";
card_module.mostCurrent._pickpdfbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Dim api1 As String = \"AIzaSyAGccTYG-Mscl_16Z72t\"";
card_module.mostCurrent._api1 = BA.ObjectToString("AIzaSyAGccTYG-Mscl_16Z72t");
 //BA.debugLineNum = 26;BA.debugLine="Dim api2 As String = \"-GIN9ITMdrDGhQ\"";
card_module.mostCurrent._api2 = BA.ObjectToString("-GIN9ITMdrDGhQ");
 //BA.debugLineNum = 27;BA.debugLine="Dim MyAPIKey As String = api1&api2";
card_module.mostCurrent._myapikey = RemoteObject.concat(card_module.mostCurrent._api1,card_module.mostCurrent._api2);
 //BA.debugLineNum = 28;BA.debugLine="Dim AIGlobalText As String";
card_module.mostCurrent._aiglobaltext = RemoteObject.createImmutable("");
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _jobdone(RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,297);
if (RapidSub.canDelegate("jobdone")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","jobdone", _job);}
RemoteObject _response = RemoteObject.createImmutable("");
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _candidates = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _candidate = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _content = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _firstpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _aitext = RemoteObject.createImmutable("");
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("job", _job);
 BA.debugLineNum = 297;BA.debugLine="Sub JobDone (job As HttpJob)";
Debug.ShouldStop(256);
 BA.debugLineNum = 298;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(512);
card_module.mostCurrent.__c.runVoidMethod ("ProgressDialogHide");
 BA.debugLineNum = 299;BA.debugLine="If job.Success Then";
Debug.ShouldStop(1024);
if (_job.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 300;BA.debugLine="Dim response As String";
Debug.ShouldStop(2048);
_response = RemoteObject.createImmutable("");Debug.locals.put("response", _response);
 BA.debugLineNum = 301;BA.debugLine="response = job.GetString";
Debug.ShouldStop(4096);
_response = _job.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ );Debug.locals.put("response", _response);
 BA.debugLineNum = 302;BA.debugLine="Log(\"RAW RESPONSE: \")";
Debug.ShouldStop(8192);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118153477",RemoteObject.createImmutable("RAW RESPONSE: "),0);
 BA.debugLineNum = 303;BA.debugLine="Log(response)";
Debug.ShouldStop(16384);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118153478",_response,0);
 BA.debugLineNum = 305;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(65536);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 306;BA.debugLine="jp.Initialize(response)";
Debug.ShouldStop(131072);
_jp.runVoidMethod ("Initialize",(Object)(_response));
 BA.debugLineNum = 308;BA.debugLine="Dim root As Map = jp.NextObject";
Debug.ShouldStop(524288);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _jp.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 310;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
Debug.ShouldStop(2097152);
_candidates = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_candidates = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("candidates")))));Debug.locals.put("candidates", _candidates);Debug.locals.put("candidates", _candidates);
 BA.debugLineNum = 312;BA.debugLine="Dim candidate As Map = candidates.Get(0)";
Debug.ShouldStop(8388608);
_candidate = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_candidate = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidates.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("candidate", _candidate);Debug.locals.put("candidate", _candidate);
 BA.debugLineNum = 314;BA.debugLine="Dim content As Map = candidate.Get(\"content\")";
Debug.ShouldStop(33554432);
_content = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_content = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidate.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("content")))));Debug.locals.put("content", _content);Debug.locals.put("content", _content);
 BA.debugLineNum = 316;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
Debug.ShouldStop(134217728);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_parts = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _content.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("parts")))));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 318;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
Debug.ShouldStop(536870912);
_firstpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_firstpart = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _parts.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("firstPart", _firstpart);Debug.locals.put("firstPart", _firstpart);
 BA.debugLineNum = 320;BA.debugLine="Dim aiText As String = firstPart.Get(\"text\")";
Debug.ShouldStop(-2147483648);
_aitext = BA.ObjectToString(_firstpart.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("text")))));Debug.locals.put("aiText", _aitext);Debug.locals.put("aiText", _aitext);
 BA.debugLineNum = 322;BA.debugLine="Log(\"AI JSON:\")";
Debug.ShouldStop(2);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118153497",RemoteObject.createImmutable("AI JSON:"),0);
 BA.debugLineNum = 323;BA.debugLine="Log(aiText)";
Debug.ShouldStop(4);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118153498",_aitext,0);
 BA.debugLineNum = 324;BA.debugLine="File.WriteString(File.DirInternal, \"cached_ai.tx";
Debug.ShouldStop(8);
card_module.mostCurrent.__c.getField(false,"File").runVoidMethod ("WriteString",(Object)(card_module.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("cached_ai.txt")),(Object)(_aitext));
 BA.debugLineNum = 325;BA.debugLine="ParseFlashcard(aiText)";
Debug.ShouldStop(16);
_parseflashcard(_aitext);
 BA.debugLineNum = 327;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.G";
Debug.ShouldStop(64);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 328;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdec";
Debug.ShouldStop(128);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 331;BA.debugLine="ShowSubdeckCards(subdeckcards)";
Debug.ShouldStop(1024);
_showsubdeckcards(_subdeckcards);
 }else {
 BA.debugLineNum = 334;BA.debugLine="Log(\"ERROR: \")";
Debug.ShouldStop(8192);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118153509",RemoteObject.createImmutable("ERROR: "),0);
 BA.debugLineNum = 335;BA.debugLine="Log(job.ErrorMessage)";
Debug.ShouldStop(16384);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118153510",_job.getField(true,"_errormessage" /*RemoteObject*/ ),0);
 BA.debugLineNum = 336;BA.debugLine="Msgbox(\"Error Parsing your PDF file\", \"Error\")";
Debug.ShouldStop(32768);
card_module.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Error Parsing your PDF file")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),card_module.mostCurrent.activityBA);
 };
 BA.debugLineNum = 339;BA.debugLine="job.Release";
Debug.ShouldStop(262144);
_job.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 340;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
		Debug.PushSubsStack("ParseFlashcard (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,342);
if (RapidSub.canDelegate("parseflashcard")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","parseflashcard", _jsontext);}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _cards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _question = RemoteObject.createImmutable("");
RemoteObject _answer = RemoteObject.createImmutable("");
Debug.locals.put("jsonText", _jsontext);
 BA.debugLineNum = 342;BA.debugLine="Sub ParseFlashcard (jsonText As String)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 343;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(4194304);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 344;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(8388608);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 346;BA.debugLine="Try";
Debug.ShouldStop(33554432);
try { BA.debugLineNum = 348;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(134217728);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 349;BA.debugLine="jp.Initialize(jsonText)";
Debug.ShouldStop(268435456);
_jp.runVoidMethod ("Initialize",(Object)(_jsontext));
 BA.debugLineNum = 351;BA.debugLine="Dim root As Map = jp.NextObject";
Debug.ShouldStop(1073741824);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _jp.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 353;BA.debugLine="Dim flashcards As List = root.Get(\"flashcards\")";
Debug.ShouldStop(1);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("flashcards")))));Debug.locals.put("flashcards", _flashcards);Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 355;BA.debugLine="For Each card As Map In flashcards";
Debug.ShouldStop(4);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group8 = _flashcards;
final int groupLen8 = group8.runMethod(true,"getSize").<Integer>get()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group8.runMethod(false,"Get",index8));Debug.locals.put("card", _card);
Debug.locals.put("card", _card);
 BA.debugLineNum = 357;BA.debugLine="Dim cards As Map";
Debug.ShouldStop(16);
_cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("cards", _cards);
 BA.debugLineNum = 358;BA.debugLine="cards.Initialize";
Debug.ShouldStop(32);
_cards.runVoidMethod ("Initialize");
 BA.debugLineNum = 360;BA.debugLine="Dim question As String = card.Get(\"question\")";
Debug.ShouldStop(128);
_question = BA.ObjectToString(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("question")))));Debug.locals.put("question", _question);Debug.locals.put("question", _question);
 BA.debugLineNum = 362;BA.debugLine="Dim answer As String = card.Get(\"answer\")";
Debug.ShouldStop(512);
_answer = BA.ObjectToString(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("answer")))));Debug.locals.put("answer", _answer);Debug.locals.put("answer", _answer);
 BA.debugLineNum = 364;BA.debugLine="cards.Put(\"Q\", question)";
Debug.ShouldStop(2048);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)((_question)));
 BA.debugLineNum = 365;BA.debugLine="cards.Put(\"A\", answer)";
Debug.ShouldStop(4096);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)((_answer)));
 BA.debugLineNum = 366;BA.debugLine="subdeckcards.Add(cards)";
Debug.ShouldStop(8192);
_subdeckcards.runVoidMethod ("Add",(Object)((_cards.getObject())));
 BA.debugLineNum = 368;BA.debugLine="Log(\"===================\")";
Debug.ShouldStop(32768);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118219034",RemoteObject.createImmutable("==================="),0);
 BA.debugLineNum = 369;BA.debugLine="Log(\"QUESTION: \" & question)";
Debug.ShouldStop(65536);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118219035",RemoteObject.concat(RemoteObject.createImmutable("QUESTION: "),_question),0);
 BA.debugLineNum = 370;BA.debugLine="Log(\"ANSWER: \" & answer)";
Debug.ShouldStop(131072);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118219036",RemoteObject.concat(RemoteObject.createImmutable("ANSWER: "),_answer),0);
 }
}Debug.locals.put("card", _card);
;
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e21) {
			BA.rdebugUtils.runVoidMethod("setLastException",card_module.processBA, e21.toString()); BA.debugLineNum = 376;BA.debugLine="Log(\"INVALID JSON\")";
Debug.ShouldStop(8388608);
card_module.mostCurrent.__c.runVoidMethod ("LogImpl","118219042",RemoteObject.createImmutable("INVALID JSON"),0);
 };
 BA.debugLineNum = 380;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
 //BA.debugLineNum = 9;BA.debugLine="Dim subdeck As String";
card_module._subdeck = RemoteObject.createImmutable("");
 //BA.debugLineNum = 10;BA.debugLine="Dim isEdit As Boolean";
card_module._isedit = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 11;BA.debugLine="Dim editindex As Int";
card_module._editindex = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showsubdeckcards(RemoteObject _cardslist) throws Exception{
try {
		Debug.PushSubsStack("ShowSubdeckCards (card_module) ","card_module",15,card_module.mostCurrent.activityBA,card_module.mostCurrent,65);
if (RapidSub.canDelegate("showsubdeckcards")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","showsubdeckcards", _cardslist);}
RemoteObject _toppos = RemoteObject.createImmutable(0);
RemoteObject _cardheight = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _btnwidth = RemoteObject.createImmutable(0);
RemoteObject _editbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _deletebtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
Debug.locals.put("cardsList", _cardslist);
 BA.debugLineNum = 65;BA.debugLine="Sub ShowSubdeckCards(cardsList As List)";
Debug.ShouldStop(1);
 BA.debugLineNum = 67;BA.debugLine="ScrollView1.Panel.RemoveAllViews";
Debug.ShouldStop(4);
card_module.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 69;BA.debugLine="Dim topPos As Int = 0";
Debug.ShouldStop(16);
_toppos = BA.numberCast(int.class, 0);Debug.locals.put("topPos", _toppos);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 70;BA.debugLine="Dim cardHeight As Int = 150dip 'height";
Debug.ShouldStop(32);
_cardheight = card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150)));Debug.locals.put("cardHeight", _cardheight);Debug.locals.put("cardHeight", _cardheight);
 BA.debugLineNum = 72;BA.debugLine="For i = 0 To cardsList.Size -1";
Debug.ShouldStop(128);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_cardslist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 73;BA.debugLine="Dim card As Map = cardsList.Get(i)";
Debug.ShouldStop(256);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _cardslist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 74;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(512);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 75;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(1024);
_p.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 76;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 77;BA.debugLine="p.Color = Colors.White";
Debug.ShouldStop(4096);
_p.runVoidMethod ("setColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 79;BA.debugLine="p.Color = Colors.Black";
Debug.ShouldStop(16384);
_p.runVoidMethod ("setColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 BA.debugLineNum = 81;BA.debugLine="ScrollView1.Panel.AddView(p, 10dip, topPos, Scro";
Debug.ShouldStop(65536);
card_module.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_toppos),(Object)(RemoteObject.solve(new RemoteObject[] {card_module.mostCurrent._scrollview1.runMethod(true,"getWidth"),card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(_cardheight));
 BA.debugLineNum = 83;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(262144);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 84;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(524288);
_lbl.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 85;BA.debugLine="lbl.Text = \"Q: \" & card.Get(\"Q\") & CRLF & \"A: \"";
Debug.ShouldStop(1048576);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Q: "),_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q")))),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("A: "),_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A")))))));
 BA.debugLineNum = 86;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 87;BA.debugLine="lbl.TextColor = Colors.black";
Debug.ShouldStop(4194304);
_lbl.runMethod(true,"setTextColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else {
 BA.debugLineNum = 89;BA.debugLine="lbl.TextColor = Colors.White";
Debug.ShouldStop(16777216);
_lbl.runMethod(true,"setTextColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 BA.debugLineNum = 91;BA.debugLine="lbl.TextSize = 12";
Debug.ShouldStop(67108864);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 92;BA.debugLine="lbl.SingleLine = False";
Debug.ShouldStop(134217728);
_lbl.runVoidMethod ("setSingleLine",card_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 94;BA.debugLine="p.AddView(lbl, 10dip, 10dip, ScrollView1.Width -";
Debug.ShouldStop(536870912);
_p.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {card_module.mostCurrent._scrollview1.runMethod(true,"getWidth"),card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 95;BA.debugLine="topPos = topPos + lbl.height + 10dip";
Debug.ShouldStop(1073741824);
_toppos = RemoteObject.solve(new RemoteObject[] {_toppos,_lbl.runMethod(true,"getHeight"),card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "++",2, 1);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 97;BA.debugLine="Dim btnwidth As Int = 100dip";
Debug.ShouldStop(1);
_btnwidth = card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)));Debug.locals.put("btnwidth", _btnwidth);Debug.locals.put("btnwidth", _btnwidth);
 BA.debugLineNum = 99;BA.debugLine="Dim editbtn As Button";
Debug.ShouldStop(4);
_editbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("editbtn", _editbtn);
 BA.debugLineNum = 100;BA.debugLine="editbtn.Initialize(\"Editbtn\") 'btn name";
Debug.ShouldStop(8);
_editbtn.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("Editbtn")));
 BA.debugLineNum = 101;BA.debugLine="editbtn.Tag = i 'tag/index";
Debug.ShouldStop(16);
_editbtn.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 102;BA.debugLine="editbtn.Text = \"Edit\" 'button text display";
Debug.ShouldStop(32);
_editbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Edit"));
 BA.debugLineNum = 103;BA.debugLine="p.AddView(editbtn, 30dip, 100dip, btnwidth, 40di";
Debug.ShouldStop(64);
_p.runVoidMethod ("AddView",(Object)((_editbtn.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(_btnwidth),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 105;BA.debugLine="Dim deletebtn As Button";
Debug.ShouldStop(256);
_deletebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("deletebtn", _deletebtn);
 BA.debugLineNum = 106;BA.debugLine="deletebtn.Initialize(\"Deletebtn\")";
Debug.ShouldStop(512);
_deletebtn.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("Deletebtn")));
 BA.debugLineNum = 107;BA.debugLine="deletebtn.Tag = i";
Debug.ShouldStop(1024);
_deletebtn.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 108;BA.debugLine="deletebtn.Text = \"Delete\"";
Debug.ShouldStop(2048);
_deletebtn.runMethod(true,"setText",BA.ObjectToCharSequence("Delete"));
 BA.debugLineNum = 109;BA.debugLine="p.AddView(deletebtn, 200dip, 100dip, btnwidth, 4";
Debug.ShouldStop(4096);
_p.runVoidMethod ("AddView",(Object)((_deletebtn.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(_btnwidth),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 110;BA.debugLine="topPos = topPos + cardHeight + 10dip";
Debug.ShouldStop(8192);
_toppos = RemoteObject.solve(new RemoteObject[] {_toppos,_cardheight,card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "++",2, 1);Debug.locals.put("topPos", _toppos);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 112;BA.debugLine="ScrollView1.Panel.Height = topPos + 10dip";
Debug.ShouldStop(32768);
card_module.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_toppos,card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "+",1, 1));
 BA.debugLineNum = 113;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}