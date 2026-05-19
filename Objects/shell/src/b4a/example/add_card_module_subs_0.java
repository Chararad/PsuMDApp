package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class add_card_module_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (add_card_module) ","add_card_module",14,add_card_module.mostCurrent.activityBA,add_card_module.mostCurrent,22);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.add_card_module.remoteMe.runUserSub(false, "add_card_module","activity_create", _firsttime);}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckname = RemoteObject.createImmutable("");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 25;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(16777216);
switch (BA.switchObjectToInt(add_card_module.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 27;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",add_card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"ACMLayout\")";
Debug.ShouldStop(134217728);
add_card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACMLayout")),add_card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"ACMLayoutDark\")";
Debug.ShouldStop(536870912);
add_card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACMLayoutDark")),add_card_module.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 33;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",add_card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"ACMLayout2\")";
Debug.ShouldStop(2);
add_card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACMLayout2")),add_card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"ACMLayoutDark2\")";
Debug.ShouldStop(8);
add_card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACMLayoutDark2")),add_card_module.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 39;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",add_card_module.mostCurrent._starter._darkmode /*RemoteObject*/ ,add_card_module.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 40;BA.debugLine="Activity.LoadLayout(\"ACMLayout3\")";
Debug.ShouldStop(128);
add_card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACMLayout3")),add_card_module.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"ACMLayoutDark3\")";
Debug.ShouldStop(512);
add_card_module.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("ACMLayoutDark3")),add_card_module.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 47;BA.debugLine="Dim tappeddeck As Map =  FlashcardActivity.deck.G";
Debug.ShouldStop(16384);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), add_card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((add_card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 50;BA.debugLine="For Each subdeckName As String In tappeddeck.keys";
Debug.ShouldStop(131072);
{
final RemoteObject group22 = _tappeddeck.runMethod(false,"Keys");
final int groupLen22 = group22.runMethod(true,"getSize").<Integer>get()
;int index22 = 0;
;
for (; index22 < groupLen22;index22++){
_subdeckname = BA.ObjectToString(group22.runMethod(false,"Get",index22));Debug.locals.put("subdeckName", _subdeckname);
Debug.locals.put("subdeckName", _subdeckname);
 BA.debugLineNum = 51;BA.debugLine="spsubdecks.Add(subdeckName)";
Debug.ShouldStop(262144);
add_card_module.mostCurrent._spsubdecks.runVoidMethod ("Add",(Object)(_subdeckname));
 }
}Debug.locals.put("subdeckName", _subdeckname);
;
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
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
		Debug.PushSubsStack("Activity_Pause (add_card_module) ","add_card_module",14,add_card_module.mostCurrent.activityBA,add_card_module.mostCurrent,60);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.add_card_module.remoteMe.runUserSub(false, "add_card_module","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 60;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 62;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("Activity_Resume (add_card_module) ","add_card_module",14,add_card_module.mostCurrent.activityBA,add_card_module.mostCurrent,56);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.add_card_module.remoteMe.runUserSub(false, "add_card_module","activity_resume");}
 BA.debugLineNum = 56;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 58;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("backbtn_Click (add_card_module) ","add_card_module",14,add_card_module.mostCurrent.activityBA,add_card_module.mostCurrent,105);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.add_card_module.remoteMe.runUserSub(false, "add_card_module","backbtn_click");}
 BA.debugLineNum = 105;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 107;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1024);
add_card_module.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 108;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
 //BA.debugLineNum = 17;BA.debugLine="Private QuestionET As EditText";
add_card_module.mostCurrent._questionet = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private AnswerET As EditText";
add_card_module.mostCurrent._answeret = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private spsubdecks As Spinner";
add_card_module.mostCurrent._spsubdecks = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
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
		Debug.PushSubsStack("SaveCard_Click (add_card_module) ","add_card_module",14,add_card_module.mostCurrent.activityBA,add_card_module.mostCurrent,68);
if (RapidSub.canDelegate("savecard_click")) { return b4a.example.add_card_module.remoteMe.runUserSub(false, "add_card_module","savecard_click");}
RemoteObject _tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _chosensubdeck = RemoteObject.createImmutable("");
RemoteObject _flashcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _cards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 68;BA.debugLine="Private Sub SaveCard_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 72;BA.debugLine="Dim tappeddeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(128);
_tappeddeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_tappeddeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), add_card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((add_card_module.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("tappeddeck", _tappeddeck);Debug.locals.put("tappeddeck", _tappeddeck);
 BA.debugLineNum = 75;BA.debugLine="If QuestionET.Text = \"\" Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",add_card_module.mostCurrent._questionet.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 76;BA.debugLine="MsgboxAsync(\"Enter the Question!\", \"Error\")";
Debug.ShouldStop(2048);
add_card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter the Question!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_card_module.processBA);
 }else 
{ BA.debugLineNum = 77;BA.debugLine="Else if AnswerET.Text = \"\" Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",add_card_module.mostCurrent._answeret.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 78;BA.debugLine="MsgboxAsync(\"Enter the Answer!\", \"Error\")";
Debug.ShouldStop(8192);
add_card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Enter the Answer!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),add_card_module.processBA);
 }else {
 BA.debugLineNum = 82;BA.debugLine="Dim chosensubdeck As String = spsubdecks.Selecte";
Debug.ShouldStop(131072);
_chosensubdeck = add_card_module.mostCurrent._spsubdecks.runMethod(true,"getSelectedItem");Debug.locals.put("chosensubdeck", _chosensubdeck);Debug.locals.put("chosensubdeck", _chosensubdeck);
 BA.debugLineNum = 85;BA.debugLine="Dim flashcards As List = tappeddeck.Get(chosensu";
Debug.ShouldStop(1048576);
_flashcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_flashcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _tappeddeck.runMethod(false,"Get",(Object)((_chosensubdeck))));Debug.locals.put("flashcards", _flashcards);Debug.locals.put("flashcards", _flashcards);
 BA.debugLineNum = 88;BA.debugLine="Dim cards As Map";
Debug.ShouldStop(8388608);
_cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("cards", _cards);
 BA.debugLineNum = 89;BA.debugLine="cards.initialize";
Debug.ShouldStop(16777216);
_cards.runVoidMethod ("Initialize");
 BA.debugLineNum = 90;BA.debugLine="cards.Put(\"Q\", QuestionET.Text)";
Debug.ShouldStop(33554432);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)((add_card_module.mostCurrent._questionet.runMethod(true,"getText"))));
 BA.debugLineNum = 91;BA.debugLine="cards.Put(\"A\", AnswerET.Text)";
Debug.ShouldStop(67108864);
_cards.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)((add_card_module.mostCurrent._answeret.runMethod(true,"getText"))));
 BA.debugLineNum = 94;BA.debugLine="flashcards.Add(cards)";
Debug.ShouldStop(536870912);
_flashcards.runVoidMethod ("Add",(Object)((_cards.getObject())));
 BA.debugLineNum = 95;BA.debugLine="QuestionET.Text = \"\"";
Debug.ShouldStop(1073741824);
add_card_module.mostCurrent._questionet.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 96;BA.debugLine="AnswerET.Text = \"\"";
Debug.ShouldStop(-2147483648);
add_card_module.mostCurrent._answeret.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 98;BA.debugLine="SaveDecks";
Debug.ShouldStop(2);
_savedecks();
 BA.debugLineNum = 100;BA.debugLine="MsgboxAsync(\"Card is Successfully Saved\", \"Card";
Debug.ShouldStop(8);
add_card_module.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Card is Successfully Saved")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Card Saved!"))),add_card_module.processBA);
 }}
;
 BA.debugLineNum = 103;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("SaveDecks (add_card_module) ","add_card_module",14,add_card_module.mostCurrent.activityBA,add_card_module.mostCurrent,63);
if (RapidSub.canDelegate("savedecks")) { return b4a.example.add_card_module.remoteMe.runUserSub(false, "add_card_module","savedecks");}
 BA.debugLineNum = 63;BA.debugLine="Sub SaveDecks";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 64;BA.debugLine="FlashcardActivity.kvs.Put(\"deck_data\", FlashcardA";
Debug.ShouldStop(-2147483648);
add_card_module.mostCurrent._flashcardactivity._kvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("deck_data")),(Object)((add_card_module.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .getObject())));
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
}