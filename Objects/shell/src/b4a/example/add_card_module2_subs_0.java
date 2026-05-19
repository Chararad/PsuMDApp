package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class add_card_module2_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (add_card_module2) ","add_card_module2",15,add_card_module2.mostCurrent.activityBA,add_card_module2.mostCurrent,22);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.add_card_module2.remoteMe.runUserSub(false, "add_card_module2","activity_create", _firsttime);}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _flashcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 24;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(8388608);
switch (BA.switchObjectToInt(add_card_module2.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 26;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_card_module2.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout(\"ACM2Layout\")";
Debug.ShouldStop(67108864);
add_card_module2.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACM2Layout")),add_card_module2.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"ACM2LayoutDark\")";
Debug.ShouldStop(268435456);
add_card_module2.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACM2LayoutDark")),add_card_module2.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 32;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_card_module2.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"ACM2Layout2\")";
Debug.ShouldStop(1);
add_card_module2.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACM2Layout2")),add_card_module2.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"ACM2LayoutDark2\")";
Debug.ShouldStop(4);
add_card_module2.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACM2LayoutDark2")),add_card_module2.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 38;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_card_module2.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 39;BA.debugLine="Activity.LoadLayout(\"ACM2Layout3\")";
Debug.ShouldStop(64);
add_card_module2.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACM2Layout3")),add_card_module2.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"ACM2LayoutDark3\")";
Debug.ShouldStop(256);
add_card_module2.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACM2LayoutDark3")),add_card_module2.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 46;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(8192);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), add_card_module2.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((add_card_module2.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 48;BA.debugLine="subdeckname.text = Card_Module.subdeck";
Debug.ShouldStop(32768);
add_card_module2.mostCurrent._subdeckname.runMethod(true,"setText",BA.ObjectToCharSequence(add_card_module2.mostCurrent._card_module._subdeck /*RemoteObject*/ ));
 BA.debugLineNum = 50;BA.debugLine="If Card_Module.isedit = True Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._card_module._isedit /*RemoteObject*/ ,add_card_module2.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 51;BA.debugLine="Dim flashcard As List = tappeddeck.Get(Card_Modu";
Debug.ShouldStop(262144);
_flashcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashcard = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((add_card_module2.mostCurrent._card_module._subdeck /*RemoteObject*/ ))));Debug.locals.put("flashcard", _flashcard);Debug.locals.put("flashcard", _flashcard);
 BA.debugLineNum = 52;BA.debugLine="Dim card As Map = flashcard.Get(Card_Module.edit";
Debug.ShouldStop(524288);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _flashcard.runMethod(false,"Get",(Object)(add_card_module2.mostCurrent._card_module._editindex /*RemoteObject*/ )));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 53;BA.debugLine="QuestionET.Text = card.Get(\"Q\")";
Debug.ShouldStop(1048576);
add_card_module2.mostCurrent._questionet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q"))))));
 BA.debugLineNum = 54;BA.debugLine="AnswerET.Text = card.Get(\"A\")";
Debug.ShouldStop(2097152);
add_card_module2.mostCurrent._answeret.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A"))))));
 };
 BA.debugLineNum = 57;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("Activity_Pause (add_card_module2) ","add_card_module2",15,add_card_module2.mostCurrent.activityBA,add_card_module2.mostCurrent,67);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.add_card_module2.remoteMe.runUserSub(false, "add_card_module2","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 67;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(4);
 BA.debugLineNum = 68;BA.debugLine="Card_Module.Isedit = False";
Debug.ShouldStop(8);
add_card_module2.mostCurrent._card_module._isedit /*RemoteObject*/  = add_card_module2.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 69;BA.debugLine="QuestionET.Text = \"\"";
Debug.ShouldStop(16);
add_card_module2.mostCurrent._questionet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 70;BA.debugLine="AnswerET.Text = \"\"";
Debug.ShouldStop(32);
add_card_module2.mostCurrent._answeret.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 71;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("Activity_Resume (add_card_module2) ","add_card_module2",15,add_card_module2.mostCurrent.activityBA,add_card_module2.mostCurrent,63);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.add_card_module2.remoteMe.runUserSub(false, "add_card_module2","activity_resume");}
 BA.debugLineNum = 63;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 65;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("backbtn_Click (add_card_module2) ","add_card_module2",15,add_card_module2.mostCurrent.activityBA,add_card_module2.mostCurrent,74);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.add_card_module2.remoteMe.runUserSub(false, "add_card_module2","backbtn_click");}
 BA.debugLineNum = 74;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 76;BA.debugLine="Activity.finish";
Debug.ShouldStop(2048);
add_card_module2.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private subdeckname As Label";
add_card_module2.mostCurrent._subdeckname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private QuestionET As EditText";
add_card_module2.mostCurrent._questionet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private AnswerET As EditText";
add_card_module2.mostCurrent._answeret = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _savecard_click() throws Exception{
try {
		Debug.PushSubsStack("SaveCard_Click (add_card_module2) ","add_card_module2",15,add_card_module2.mostCurrent.activityBA,add_card_module2.mostCurrent,79);
if (RapidSub.canDelegate("savecard_click")) { return b4a.example.add_card_module2.remoteMe.runUserSub(false, "add_card_module2","savecard_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _editcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _cards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 79;BA.debugLine="Private Sub SaveCard_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 81;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(65536);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), add_card_module2.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((add_card_module2.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 83;BA.debugLine="If Card_Module.isEdit = True Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._card_module._isedit /*RemoteObject*/ ,add_card_module2.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 84;BA.debugLine="Card_Module.isEdit = False";
Debug.ShouldStop(524288);
add_card_module2.mostCurrent._card_module._isedit /*RemoteObject*/  = add_card_module2.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 87;BA.debugLine="If QuestionET.Text = \"\" Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._questionet.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 88;BA.debugLine="MsgboxAsync(\"Enter the Question!\", \"Error\")";
Debug.ShouldStop(8388608);
add_card_module2.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter the Question!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_card_module2.processBA);
 }else 
{ BA.debugLineNum = 89;BA.debugLine="Else if AnswerET.Text = \"\" Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._answeret.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 90;BA.debugLine="MsgboxAsync(\"Enter the Answer!\", \"Error\")";
Debug.ShouldStop(33554432);
add_card_module2.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter the Answer!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_card_module2.processBA);
 }else {
 BA.debugLineNum = 93;BA.debugLine="Dim Editcards As List = tappeddeck.Get(Card_Mod";
Debug.ShouldStop(268435456);
_editcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_editcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((add_card_module2.mostCurrent._card_module._subdeck /*RemoteObject*/ ))));Debug.locals.put("Editcards", _editcards);Debug.locals.put("Editcards", _editcards);
 BA.debugLineNum = 94;BA.debugLine="Dim card As Map = Editcards.Get(Card_Module.edi";
Debug.ShouldStop(536870912);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _editcards.runMethod(false,"Get",(Object)(add_card_module2.mostCurrent._card_module._editindex /*RemoteObject*/ )));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 95;BA.debugLine="card.Put(\"Q\", QuestionET.text)";
Debug.ShouldStop(1073741824);
_card.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)((add_card_module2.mostCurrent._questionet.runMethod(true,"getText"))));
 BA.debugLineNum = 96;BA.debugLine="card.Put(\"A\", AnswerET.text)";
Debug.ShouldStop(-2147483648);
_card.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)((add_card_module2.mostCurrent._answeret.runMethod(true,"getText"))));
 }}
;
 BA.debugLineNum = 98;BA.debugLine="SaveDecks";
Debug.ShouldStop(2);
_savedecks();
 BA.debugLineNum = 99;BA.debugLine="Activity.Finish";
Debug.ShouldStop(4);
add_card_module2.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 100;BA.debugLine="Return";
Debug.ShouldStop(8);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 107;BA.debugLine="If QuestionET.Text = \"\" Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._questionet.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 108;BA.debugLine="MsgboxAsync(\"Enter the Question!\", \"Error\")";
Debug.ShouldStop(2048);
add_card_module2.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter the Question!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_card_module2.processBA);
 }else 
{ BA.debugLineNum = 109;BA.debugLine="Else if AnswerET.Text = \"\" Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",add_card_module2.mostCurrent._answeret.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 110;BA.debugLine="MsgboxAsync(\"Enter the Answer!\", \"Error\")";
Debug.ShouldStop(8192);
add_card_module2.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter the Answer!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_card_module2.processBA);
 }else {
 BA.debugLineNum = 115;BA.debugLine="Dim flashcards As List = tappeddeck.Get(Card_Mod";
Debug.ShouldStop(262144);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((add_card_module2.mostCurrent._card_module._subdeck /*RemoteObject*/ ))));Debug.locals.put("flashcards", _flashcards);Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 118;BA.debugLine="Dim cards As Map";
Debug.ShouldStop(2097152);
_cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("cards", _cards);
 BA.debugLineNum = 119;BA.debugLine="cards.initialize";
Debug.ShouldStop(4194304);
_cards.runVoidMethod ("Initialize");
 BA.debugLineNum = 120;BA.debugLine="cards.Put(\"Q\", QuestionET.Text)";
Debug.ShouldStop(8388608);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)((add_card_module2.mostCurrent._questionet.runMethod(true,"getText"))));
 BA.debugLineNum = 121;BA.debugLine="cards.Put(\"A\", AnswerET.Text)";
Debug.ShouldStop(16777216);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)((add_card_module2.mostCurrent._answeret.runMethod(true,"getText"))));
 BA.debugLineNum = 124;BA.debugLine="flashcards.Add(cards)";
Debug.ShouldStop(134217728);
_flashcards.runVoidMethod ("Add",(Object)((_cards.getObject())));
 BA.debugLineNum = 125;BA.debugLine="QuestionET.Text = \"\"";
Debug.ShouldStop(268435456);
add_card_module2.mostCurrent._questionet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 126;BA.debugLine="AnswerET.Text = \"\"";
Debug.ShouldStop(536870912);
add_card_module2.mostCurrent._answeret.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 128;BA.debugLine="SaveDecks";
Debug.ShouldStop(-2147483648);
_savedecks();
 BA.debugLineNum = 130;BA.debugLine="MsgboxAsync(\"Card is Successfully Saved\", \"Card";
Debug.ShouldStop(2);
add_card_module2.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Card is Successfully Saved")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Card Saved!"))),add_card_module2.processBA);
 }}
;
 BA.debugLineNum = 133;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("SaveDecks (add_card_module2) ","add_card_module2",15,add_card_module2.mostCurrent.activityBA,add_card_module2.mostCurrent,59);
if (RapidSub.canDelegate("savedecks")) { return b4a.example.add_card_module2.remoteMe.runUserSub(false, "add_card_module2","savedecks");}
 BA.debugLineNum = 59;BA.debugLine="Sub SaveDecks";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 60;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
Debug.ShouldStop(134217728);
add_card_module2.mostCurrent._flashcardactivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("deck_data")),(Object)((add_card_module2.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .getObject())));
 BA.debugLineNum = 61;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}