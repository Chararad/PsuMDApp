package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class card_module_subs_1 {


public static RemoteObject  _activerecall_click() throws Exception{
try {
		Debug.PushSubsStack("activerecall_Click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,152);
if (RapidSub.canDelegate("activerecall_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activerecall_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _number_of_cards = RemoteObject.createImmutable(0);
 BA.debugLineNum = 152;BA.debugLine="Private Sub activerecall_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 154;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(33554432);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 155;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(67108864);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 156;BA.debugLine="Dim number_of_cards As Int = subdeckcards.size";
Debug.ShouldStop(134217728);
_number_of_cards = _subdeckcards.runMethod(true,"getSize");Debug.locals.put("number_of_cards", _number_of_cards);Debug.locals.put("number_of_cards", _number_of_cards);
 BA.debugLineNum = 158;BA.debugLine="If number_of_cards = 0 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",_number_of_cards,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 159;BA.debugLine="MsgboxAsync(\"No cards available\", \"Error\")";
Debug.ShouldStop(1073741824);
card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("No cards available")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),card_module.processBA);
 BA.debugLineNum = 160;BA.debugLine="Return";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 162;BA.debugLine="StartActivity(active_recall)";
Debug.ShouldStop(2);
card_module.mostCurrent.__c.runVoidMethod ("StartActivity",card_module.processBA,(Object)((card_module.mostCurrent._active_recall.getObject())));
 BA.debugLineNum = 163;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("Activity_Create (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,24);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activity_create", _firsttime);}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 25;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(16777216);
switch (BA.switchObjectToInt(card_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 27;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout\")";
Debug.ShouldStop(134217728);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayout")),card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark\")";
Debug.ShouldStop(536870912);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayoutDark")),card_module.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 33;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout2\")";
Debug.ShouldStop(2);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayout2")),card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark2\")";
Debug.ShouldStop(8);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayoutDark2")),card_module.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 39;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 40;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayout3\")";
Debug.ShouldStop(128);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayout3")),card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"Card_ModuleLayoutDark3\")";
Debug.ShouldStop(512);
card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Card_ModuleLayoutDark3")),card_module.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 47;BA.debugLine="subdecklabel.Text = Subdeck_Module.selectedsubdec";
Debug.ShouldStop(16384);
card_module.mostCurrent._subdecklabel.runMethod(true,"setText",BA.ObjectToCharSequence(card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ));
 BA.debugLineNum = 49;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(65536);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 50;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(131072);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 53;BA.debugLine="ShowSubdeckCards(subdeckcards)";
Debug.ShouldStop(1048576);
_showsubdeckcards(_subdeckcards);
 BA.debugLineNum = 55;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Pause (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,141);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 141;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 143;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
		Debug.PushSubsStack("Activity_Resume (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,129);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","activity_resume");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 129;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1);
 BA.debugLineNum = 131;BA.debugLine="If active_recall.praise = True Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._active_recall._praise /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 132;BA.debugLine="active_recall.praise = False";
Debug.ShouldStop(8);
card_module.mostCurrent._active_recall._praise /*RemoteObject*/  = card_module.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 133;BA.debugLine="MsgboxAsync(\"You Finished Your Sub-Deck\", \"Congr";
Debug.ShouldStop(16);
card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("You Finished Your Sub-Deck")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Congratulations"))),card_module.processBA);
 };
 BA.debugLineNum = 136;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(128);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 137;BA.debugLine="Dim subdeckcards As List = tappedDeck.Get(Subdeck";
Debug.ShouldStop(256);
_subdeckcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subdeckcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("subdeckcards", _subdeckcards);Debug.locals.put("subdeckcards", _subdeckcards);
 BA.debugLineNum = 138;BA.debugLine="ShowSubdeckCards(subdeckcards)";
Debug.ShouldStop(512);
_showsubdeckcards(_subdeckcards);
 BA.debugLineNum = 139;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
		Debug.PushSubsStack("addbtn_Click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,165);
if (RapidSub.canDelegate("addbtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","addbtn_click");}
 BA.debugLineNum = 165;BA.debugLine="Private Sub addbtn_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 167;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
Debug.ShouldStop(64);
card_module._subdeck = card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ;
 BA.debugLineNum = 168;BA.debugLine="StartActivity(add_card_module2)";
Debug.ShouldStop(128);
card_module.mostCurrent.__c.runVoidMethod ("StartActivity",card_module.processBA,(Object)((card_module.mostCurrent._add_card_module2.getObject())));
 BA.debugLineNum = 169;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("backbtn_Click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,147);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","backbtn_click");}
 BA.debugLineNum = 147;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 149;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1048576);
card_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 150;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
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
		Debug.PushSubsStack("canceldelete_Click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,180);
if (RapidSub.canDelegate("canceldelete_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","canceldelete_click");}
 BA.debugLineNum = 180;BA.debugLine="Private Sub canceldelete_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 181;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(1048576);
card_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",card_module.mostCurrent.__c.getField(true,"False"));
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
public static RemoteObject  _confirmdelete_click() throws Exception{
try {
		Debug.PushSubsStack("confirmdelete_Click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,171);
if (RapidSub.canDelegate("confirmdelete_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","confirmdelete_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _cards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 171;BA.debugLine="Private Sub confirmdelete_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 172;BA.debugLine="Dim tappedDeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(2048);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappedDeck", _tappeddeck);Debug.locals.put("tappedDeck", _tappeddeck);
 BA.debugLineNum = 173;BA.debugLine="Dim cards As List = tappedDeck.Get(Subdeck_Module";
Debug.ShouldStop(4096);
_cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_cards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ))));Debug.locals.put("cards", _cards);Debug.locals.put("cards", _cards);
 BA.debugLineNum = 175;BA.debugLine="cards.RemoveAt(numtag)";
Debug.ShouldStop(16384);
_cards.runVoidMethod ("RemoveAt",(Object)(card_module._numtag));
 BA.debugLineNum = 176;BA.debugLine="ShowSubdeckCards(cards)";
Debug.ShouldStop(32768);
_showsubdeckcards(_cards);
 BA.debugLineNum = 177;BA.debugLine="deleteconfirmation.Visible = False";
Debug.ShouldStop(65536);
card_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",card_module.mostCurrent.__c.getField(true,"False"));
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
public static RemoteObject  _deletebtn_click() throws Exception{
try {
		Debug.PushSubsStack("deletebtn_click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,120);
if (RapidSub.canDelegate("deletebtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","deletebtn_click");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _index = RemoteObject.createImmutable(0);
 BA.debugLineNum = 120;BA.debugLine="Sub deletebtn_click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 122;BA.debugLine="Dim b As Button = Sender";
Debug.ShouldStop(33554432);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), card_module.mostCurrent.__c.runMethod(false,"Sender",card_module.mostCurrent.activityBA));Debug.locals.put("b", _b);Debug.locals.put("b", _b);
 BA.debugLineNum = 123;BA.debugLine="Dim index As Int = b.Tag";
Debug.ShouldStop(67108864);
_index = BA.numberCast(int.class, _b.runMethod(false,"getTag"));Debug.locals.put("index", _index);Debug.locals.put("index", _index);
 BA.debugLineNum = 124;BA.debugLine="numtag = index";
Debug.ShouldStop(134217728);
card_module._numtag = _index;
 BA.debugLineNum = 125;BA.debugLine="deleteconfirmation.Visible = True";
Debug.ShouldStop(268435456);
card_module.mostCurrent._deleteconfirmation.runMethod(true,"setVisible",card_module.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 127;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("editbtn_Click (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,108);
if (RapidSub.canDelegate("editbtn_click")) { return b4a.example.card_module.remoteMe.runUserSub(false, "card_module","editbtn_click");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _index = RemoteObject.createImmutable(0);
 BA.debugLineNum = 108;BA.debugLine="Sub editbtn_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 110;BA.debugLine="subdeck = Subdeck_Module.selectedsubdeck";
Debug.ShouldStop(8192);
card_module._subdeck = card_module.mostCurrent._subdeck_module._selectedsubdeck /*RemoteObject*/ ;
 BA.debugLineNum = 111;BA.debugLine="Dim b As Button = Sender";
Debug.ShouldStop(16384);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), card_module.mostCurrent.__c.runMethod(false,"Sender",card_module.mostCurrent.activityBA));Debug.locals.put("b", _b);Debug.locals.put("b", _b);
 BA.debugLineNum = 112;BA.debugLine="Dim index As Int = b.Tag";
Debug.ShouldStop(32768);
_index = BA.numberCast(int.class, _b.runMethod(false,"getTag"));Debug.locals.put("index", _index);Debug.locals.put("index", _index);
 BA.debugLineNum = 113;BA.debugLine="editindex = index";
Debug.ShouldStop(65536);
card_module._editindex = _index;
 BA.debugLineNum = 114;BA.debugLine="isEdit = True";
Debug.ShouldStop(131072);
card_module._isedit = card_module.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 116;BA.debugLine="StartActivity(add_card_module2)";
Debug.ShouldStop(524288);
card_module.mostCurrent.__c.runVoidMethod ("StartActivity",card_module.processBA,(Object)((card_module.mostCurrent._add_card_module2.getObject())));
 BA.debugLineNum = 118;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
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
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
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
		Debug.PushSubsStack("ShowSubdeckCards (card_module) ","card_module",7,card_module.mostCurrent.activityBA,card_module.mostCurrent,57);
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
 BA.debugLineNum = 57;BA.debugLine="Sub ShowSubdeckCards(cardsList As List)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 59;BA.debugLine="ScrollView1.Panel.RemoveAllViews";
Debug.ShouldStop(67108864);
card_module.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 61;BA.debugLine="Dim topPos As Int = 0";
Debug.ShouldStop(268435456);
_toppos = BA.numberCast(int.class, 0);Debug.locals.put("topPos", _toppos);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 62;BA.debugLine="Dim cardHeight As Int = 150dip 'height";
Debug.ShouldStop(536870912);
_cardheight = card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150)));Debug.locals.put("cardHeight", _cardheight);Debug.locals.put("cardHeight", _cardheight);
 BA.debugLineNum = 64;BA.debugLine="For i = 0 To cardsList.Size -1";
Debug.ShouldStop(-2147483648);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_cardslist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 65;BA.debugLine="Dim card As Map = cardsList.Get(i)";
Debug.ShouldStop(1);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _cardslist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 66;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(2);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 67;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(4);
_p.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 68;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 69;BA.debugLine="p.Color = Colors.White";
Debug.ShouldStop(16);
_p.runVoidMethod ("setColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 71;BA.debugLine="p.Color = Colors.Black";
Debug.ShouldStop(64);
_p.runVoidMethod ("setColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 BA.debugLineNum = 73;BA.debugLine="ScrollView1.Panel.AddView(p, 10dip, topPos, Scro";
Debug.ShouldStop(256);
card_module.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_toppos),(Object)(RemoteObject.solve(new RemoteObject[] {card_module.mostCurrent._scrollview1.runMethod(true,"getWidth"),card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(_cardheight));
 BA.debugLineNum = 75;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(1024);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 76;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(2048);
_lbl.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 77;BA.debugLine="lbl.Text = \"Q: \" & card.Get(\"Q\") & CRLF & \"A: \"";
Debug.ShouldStop(4096);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Q: "),_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q")))),card_module.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("A: "),_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A")))))));
 BA.debugLineNum = 78;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 79;BA.debugLine="lbl.TextColor = Colors.black";
Debug.ShouldStop(16384);
_lbl.runMethod(true,"setTextColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else {
 BA.debugLineNum = 81;BA.debugLine="lbl.TextColor = Colors.White";
Debug.ShouldStop(65536);
_lbl.runMethod(true,"setTextColor",card_module.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 BA.debugLineNum = 83;BA.debugLine="lbl.TextSize = 12";
Debug.ShouldStop(262144);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 84;BA.debugLine="lbl.SingleLine = False";
Debug.ShouldStop(524288);
_lbl.runVoidMethod ("setSingleLine",card_module.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 86;BA.debugLine="p.AddView(lbl, 10dip, 10dip, ScrollView1.Width -";
Debug.ShouldStop(2097152);
_p.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {card_module.mostCurrent._scrollview1.runMethod(true,"getWidth"),card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 87;BA.debugLine="topPos = topPos + lbl.height + 10dip";
Debug.ShouldStop(4194304);
_toppos = RemoteObject.solve(new RemoteObject[] {_toppos,_lbl.runMethod(true,"getHeight"),card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "++",2, 1);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 89;BA.debugLine="Dim btnwidth As Int = 100dip";
Debug.ShouldStop(16777216);
_btnwidth = card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)));Debug.locals.put("btnwidth", _btnwidth);Debug.locals.put("btnwidth", _btnwidth);
 BA.debugLineNum = 91;BA.debugLine="Dim editbtn As Button";
Debug.ShouldStop(67108864);
_editbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("editbtn", _editbtn);
 BA.debugLineNum = 92;BA.debugLine="editbtn.Initialize(\"Editbtn\") 'btn name";
Debug.ShouldStop(134217728);
_editbtn.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("Editbtn")));
 BA.debugLineNum = 93;BA.debugLine="editbtn.Tag = i 'tag/index";
Debug.ShouldStop(268435456);
_editbtn.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 94;BA.debugLine="editbtn.Text = \"Edit\" 'button text display";
Debug.ShouldStop(536870912);
_editbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Edit"));
 BA.debugLineNum = 95;BA.debugLine="p.AddView(editbtn, 30dip, 100dip, btnwidth, 40di";
Debug.ShouldStop(1073741824);
_p.runVoidMethod ("AddView",(Object)((_editbtn.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(_btnwidth),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 97;BA.debugLine="Dim deletebtn As Button";
Debug.ShouldStop(1);
_deletebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("deletebtn", _deletebtn);
 BA.debugLineNum = 98;BA.debugLine="deletebtn.Initialize(\"Deletebtn\")";
Debug.ShouldStop(2);
_deletebtn.runVoidMethod ("Initialize",card_module.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("Deletebtn")));
 BA.debugLineNum = 99;BA.debugLine="deletebtn.Tag = i";
Debug.ShouldStop(4);
_deletebtn.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 100;BA.debugLine="deletebtn.Text = \"Delete\"";
Debug.ShouldStop(8);
_deletebtn.runMethod(true,"setText",BA.ObjectToCharSequence("Delete"));
 BA.debugLineNum = 101;BA.debugLine="p.AddView(deletebtn, 200dip, 100dip, btnwidth, 4";
Debug.ShouldStop(16);
_p.runVoidMethod ("AddView",(Object)((_deletebtn.getObject())),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(_btnwidth),(Object)(card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 102;BA.debugLine="topPos = topPos + cardHeight + 10dip";
Debug.ShouldStop(32);
_toppos = RemoteObject.solve(new RemoteObject[] {_toppos,_cardheight,card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "++",2, 1);Debug.locals.put("topPos", _toppos);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 104;BA.debugLine="ScrollView1.Panel.Height = topPos + 10dip";
Debug.ShouldStop(128);
card_module.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_toppos,card_module.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "+",1, 1));
 BA.debugLineNum = 105;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}