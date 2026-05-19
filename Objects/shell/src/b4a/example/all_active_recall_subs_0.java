package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class all_active_recall_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","activity_create", _firsttime);}
RemoteObject _chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckname = RemoteObject.createImmutable("");
RemoteObject _subcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _newcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 28;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(134217728);
switch (BA.switchObjectToInt(all_active_recall.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 30;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",all_active_recall.mostCurrent._starter._darkmode /*RemoteObject*/ ,all_active_recall.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"AARLayout\")";
Debug.ShouldStop(1073741824);
all_active_recall.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AARLayout")),all_active_recall.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"AEMLayoutDark\")";
Debug.ShouldStop(1);
all_active_recall.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AEMLayoutDark")),all_active_recall.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 36;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",all_active_recall.mostCurrent._starter._darkmode /*RemoteObject*/ ,all_active_recall.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"AARLayout2\")";
Debug.ShouldStop(16);
all_active_recall.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AARLayout2")),all_active_recall.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 39;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark2\")";
Debug.ShouldStop(64);
all_active_recall.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AARLayoutDark2")),all_active_recall.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 42;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",all_active_recall.mostCurrent._starter._darkmode /*RemoteObject*/ ,all_active_recall.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"AARLayout3\")";
Debug.ShouldStop(1024);
all_active_recall.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AARLayout3")),all_active_recall.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 45;BA.debugLine="Activity.LoadLayout(\"AARLayoutDark3\")";
Debug.ShouldStop(4096);
all_active_recall.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AARLayoutDark3")),all_active_recall.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 49;BA.debugLine="cards.Initialize";
Debug.ShouldStop(65536);
all_active_recall.mostCurrent._cards.runVoidMethod ("Initialize");
 BA.debugLineNum = 50;BA.debugLine="RndSeed(DateTime.Now) 'seeding randomizer";
Debug.ShouldStop(131072);
all_active_recall.mostCurrent.__c.runVoidMethod ("RndSeed",(Object)(all_active_recall.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));
 BA.debugLineNum = 53;BA.debugLine="Dim chosendeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(1048576);
_chosendeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), all_active_recall.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((all_active_recall.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("chosendeck", _chosendeck);Debug.locals.put("chosendeck", _chosendeck);
 BA.debugLineNum = 54;BA.debugLine="For Each subdeckname As String In chosendeck.Keys";
Debug.ShouldStop(2097152);
{
final RemoteObject group24 = _chosendeck.runMethod(false,"Keys");
final int groupLen24 = group24.runMethod(true,"getSize").<Integer>get()
;int index24 = 0;
;
for (; index24 < groupLen24;index24++){
_subdeckname = BA.ObjectToString(group24.runMethod(false,"Get",index24));Debug.locals.put("subdeckname", _subdeckname);
Debug.locals.put("subdeckname", _subdeckname);
 BA.debugLineNum = 55;BA.debugLine="Dim subcards As List = chosendeck.Get(subdecknam";
Debug.ShouldStop(4194304);
_subcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _chosendeck.runMethod(false,"Get",(Object)((_subdeckname))));Debug.locals.put("subcards", _subcards);Debug.locals.put("subcards", _subcards);
 BA.debugLineNum = 56;BA.debugLine="For Each card As Map In subcards";
Debug.ShouldStop(8388608);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group26 = _subcards;
final int groupLen26 = group26.runMethod(true,"getSize").<Integer>get()
;int index26 = 0;
;
for (; index26 < groupLen26;index26++){
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group26.runMethod(false,"Get",index26));Debug.locals.put("card", _card);
Debug.locals.put("card", _card);
 BA.debugLineNum = 57;BA.debugLine="Dim newcard As Map";
Debug.ShouldStop(16777216);
_newcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("newcard", _newcard);
 BA.debugLineNum = 58;BA.debugLine="newcard.Initialize";
Debug.ShouldStop(33554432);
_newcard.runVoidMethod ("Initialize");
 BA.debugLineNum = 59;BA.debugLine="newcard.Put(\"Q\", card.Get(\"Q\"))";
Debug.ShouldStop(67108864);
_newcard.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q"))))));
 BA.debugLineNum = 60;BA.debugLine="newcard.Put(\"A\", card.Get(\"A\"))";
Debug.ShouldStop(134217728);
_newcard.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A"))))));
 BA.debugLineNum = 61;BA.debugLine="newcard.Put(\"subdeck\", subdeckname) 'get subdec";
Debug.ShouldStop(268435456);
_newcard.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("subdeck"))),(Object)((_subdeckname)));
 BA.debugLineNum = 62;BA.debugLine="cards.Add(newcard)";
Debug.ShouldStop(536870912);
all_active_recall.mostCurrent._cards.runVoidMethod ("Add",(Object)((_newcard.getObject())));
 }
}Debug.locals.put("card", _card);
;
 }
}Debug.locals.put("subdeckname", _subdeckname);
;
 BA.debugLineNum = 66;BA.debugLine="ShuffleCards(cards)";
Debug.ShouldStop(2);
_shufflecards(all_active_recall.mostCurrent._cards);
 BA.debugLineNum = 67;BA.debugLine="currentindex = 0";
Debug.ShouldStop(4);
all_active_recall._currentindex = BA.numberCast(int.class, 0);
 BA.debugLineNum = 69;BA.debugLine="Showcard";
Debug.ShouldStop(16);
_showcard();
 BA.debugLineNum = 70;BA.debugLine="ShowProgress";
Debug.ShouldStop(32);
_showprogress();
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
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,104);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(128);
 BA.debugLineNum = 106;BA.debugLine="End Sub";
Debug.ShouldStop(512);
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
		Debug.PushSubsStack("Activity_Resume (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,100);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","activity_resume");}
 BA.debugLineNum = 100;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8);
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
public static RemoteObject  _backbtn_click() throws Exception{
try {
		Debug.PushSubsStack("backbtn_Click (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,123);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","backbtn_click");}
 BA.debugLineNum = 123;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 125;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
Debug.ShouldStop(268435456);
all_active_recall.mostCurrent._showanswerbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Show Answer"));
 BA.debugLineNum = 126;BA.debugLine="If currentindex = 0 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",all_active_recall._currentindex,BA.numberCast(double.class, 0))) { 
 }else {
 BA.debugLineNum = 129;BA.debugLine="currentindex = currentindex-1";
Debug.ShouldStop(1);
all_active_recall._currentindex = RemoteObject.solve(new RemoteObject[] {all_active_recall._currentindex,RemoteObject.createImmutable(1)}, "-",1, 1);
 BA.debugLineNum = 130;BA.debugLine="Showcard";
Debug.ShouldStop(2);
_showcard();
 };
 BA.debugLineNum = 132;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
 //BA.debugLineNum = 15;BA.debugLine="Dim cards As List";
all_active_recall.mostCurrent._cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 16;BA.debugLine="Dim currentindex As Int";
all_active_recall._currentindex = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 17;BA.debugLine="Private Question As Label";
all_active_recall.mostCurrent._question = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private Answer As Label";
all_active_recall.mostCurrent._answer = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private DeckName_Label As Label";
all_active_recall.mostCurrent._deckname_label = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private showAnswerbtn As Button";
all_active_recall.mostCurrent._showanswerbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private pb As ProgressBar";
all_active_recall.mostCurrent._pb = RemoteObject.createNew ("anywheresoftware.b4a.objects.ProgressBarWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private Progress As Label";
all_active_recall.mostCurrent._progress = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private nextbtn As Button";
all_active_recall.mostCurrent._nextbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _goback_click() throws Exception{
try {
		Debug.PushSubsStack("goback_Click (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,150);
if (RapidSub.canDelegate("goback_click")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","goback_click");}
 BA.debugLineNum = 150;BA.debugLine="Private Sub goback_Click";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 151;BA.debugLine="Activity.finish";
Debug.ShouldStop(4194304);
all_active_recall.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 152;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _nextbtn_click() throws Exception{
try {
		Debug.PushSubsStack("nextbtn_Click (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,134);
if (RapidSub.canDelegate("nextbtn_click")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","nextbtn_click");}
 BA.debugLineNum = 134;BA.debugLine="Private Sub nextbtn_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 136;BA.debugLine="nextbtn.Visible = False";
Debug.ShouldStop(128);
all_active_recall.mostCurrent._nextbtn.runMethod(true,"setVisible",all_active_recall.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 137;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
Debug.ShouldStop(256);
all_active_recall.mostCurrent._showanswerbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Show Answer"));
 BA.debugLineNum = 138;BA.debugLine="currentindex = currentindex +1";
Debug.ShouldStop(512);
all_active_recall._currentindex = RemoteObject.solve(new RemoteObject[] {all_active_recall._currentindex,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 139;BA.debugLine="If currentindex >= cards.Size Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("g",all_active_recall._currentindex,BA.numberCast(double.class, all_active_recall.mostCurrent._cards.runMethod(true,"getSize")))) { 
 BA.debugLineNum = 140;BA.debugLine="MsgboxAsync(\"Decks Finished\", \"Active Recall\")";
Debug.ShouldStop(2048);
all_active_recall.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Decks Finished")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Active Recall"))),all_active_recall.processBA);
 BA.debugLineNum = 141;BA.debugLine="praise = True";
Debug.ShouldStop(4096);
all_active_recall._praise = all_active_recall.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 142;BA.debugLine="Activity.finish";
Debug.ShouldStop(8192);
all_active_recall.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 143;BA.debugLine="Return";
Debug.ShouldStop(16384);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 146;BA.debugLine="Showcard";
Debug.ShouldStop(131072);
_showcard();
 BA.debugLineNum = 147;BA.debugLine="ShowProgress";
Debug.ShouldStop(262144);
_showprogress();
 BA.debugLineNum = 148;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
 //BA.debugLineNum = 9;BA.debugLine="Dim praise As Boolean = False";
all_active_recall._praise = all_active_recall.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showanswerbtn_click() throws Exception{
try {
		Debug.PushSubsStack("showAnswerbtn_Click (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,109);
if (RapidSub.canDelegate("showanswerbtn_click")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","showanswerbtn_click");}
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 109;BA.debugLine="Private Sub showAnswerbtn_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 111;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
Debug.ShouldStop(16384);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), all_active_recall.mostCurrent._cards.runMethod(false,"Get",(Object)(all_active_recall._currentindex)));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 112;BA.debugLine="If showAnswerbtn.Text = \"Show Answer\" Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",all_active_recall.mostCurrent._showanswerbtn.runMethod(true,"getText"),BA.ObjectToString("Show Answer"))) { 
 BA.debugLineNum = 113;BA.debugLine="Answer.Text = card.Get(\"A\")";
Debug.ShouldStop(65536);
all_active_recall.mostCurrent._answer.runMethod(true,"setText",BA.ObjectToCharSequence(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A"))))));
 BA.debugLineNum = 114;BA.debugLine="showAnswerbtn.Text = \"Hide Answer\"";
Debug.ShouldStop(131072);
all_active_recall.mostCurrent._showanswerbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Hide Answer"));
 }else {
 BA.debugLineNum = 117;BA.debugLine="Answer.Text = \"\"";
Debug.ShouldStop(1048576);
all_active_recall.mostCurrent._answer.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 118;BA.debugLine="showAnswerbtn.Text = \"Show Answer\"";
Debug.ShouldStop(2097152);
all_active_recall.mostCurrent._showanswerbtn.runMethod(true,"setText",BA.ObjectToCharSequence("Show Answer"));
 };
 BA.debugLineNum = 120;BA.debugLine="nextbtn.Visible = True";
Debug.ShouldStop(8388608);
all_active_recall.mostCurrent._nextbtn.runMethod(true,"setVisible",all_active_recall.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 121;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showcard() throws Exception{
try {
		Debug.PushSubsStack("Showcard (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,92);
if (RapidSub.canDelegate("showcard")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","showcard");}
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 92;BA.debugLine="Sub Showcard";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 94;BA.debugLine="Dim card As Map = cards.Get(currentindex)";
Debug.ShouldStop(536870912);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), all_active_recall.mostCurrent._cards.runMethod(false,"Get",(Object)(all_active_recall._currentindex)));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 95;BA.debugLine="Question.Text = card.Get(\"Q\")";
Debug.ShouldStop(1073741824);
all_active_recall.mostCurrent._question.runMethod(true,"setText",BA.ObjectToCharSequence(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q"))))));
 BA.debugLineNum = 96;BA.debugLine="Answer.Text = \"\"";
Debug.ShouldStop(-2147483648);
all_active_recall.mostCurrent._answer.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 97;BA.debugLine="DeckName_Label.Text = card.Get(\"subdeck\")";
Debug.ShouldStop(1);
all_active_recall.mostCurrent._deckname_label.runMethod(true,"setText",BA.ObjectToCharSequence(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("subdeck"))))));
 BA.debugLineNum = 98;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showprogress() throws Exception{
try {
		Debug.PushSubsStack("ShowProgress (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,73);
if (RapidSub.canDelegate("showprogress")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","showprogress");}
RemoteObject _totalsession = RemoteObject.createImmutable(0);
RemoteObject _studied = RemoteObject.createImmutable(0);
RemoteObject _percent = RemoteObject.createImmutable(0);
 BA.debugLineNum = 73;BA.debugLine="Sub ShowProgress";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="Dim totalsession As Int = cards.Size";
Debug.ShouldStop(512);
_totalsession = all_active_recall.mostCurrent._cards.runMethod(true,"getSize");Debug.locals.put("totalsession", _totalsession);Debug.locals.put("totalsession", _totalsession);
 BA.debugLineNum = 75;BA.debugLine="Dim studied As Int = currentindex";
Debug.ShouldStop(1024);
_studied = all_active_recall._currentindex;Debug.locals.put("studied", _studied);Debug.locals.put("studied", _studied);
 BA.debugLineNum = 76;BA.debugLine="Dim percent As Int = (studied * 100)/ totalsessio";
Debug.ShouldStop(2048);
_percent = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_studied,RemoteObject.createImmutable(100)}, "*",0, 1)),_totalsession}, "/",0, 0));Debug.locals.put("percent", _percent);Debug.locals.put("percent", _percent);
 BA.debugLineNum = 77;BA.debugLine="pb.Progress = percent";
Debug.ShouldStop(4096);
all_active_recall.mostCurrent._pb.runMethod(true,"setProgress",_percent);
 BA.debugLineNum = 78;BA.debugLine="Progress.Text = studied & \"/\" & totalsession & \"";
Debug.ShouldStop(8192);
all_active_recall.mostCurrent._progress.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_studied,RemoteObject.createImmutable("/"),_totalsession,RemoteObject.createImmutable(" "),_percent,RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 79;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _shufflecards(RemoteObject _cardlist) throws Exception{
try {
		Debug.PushSubsStack("ShuffleCards (all_active_recall) ","all_active_recall",17,all_active_recall.mostCurrent.activityBA,all_active_recall.mostCurrent,81);
if (RapidSub.canDelegate("shufflecards")) { return b4a.example.all_active_recall.remoteMe.runUserSub(false, "all_active_recall","shufflecards", _cardlist);}
int _i = 0;
RemoteObject _j = RemoteObject.createImmutable(0);
RemoteObject _temp = RemoteObject.declareNull("Object");
Debug.locals.put("cardList", _cardlist);
 BA.debugLineNum = 81;BA.debugLine="Sub ShuffleCards(cardList As List)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 83;BA.debugLine="For i = cardList.Size-1 To 1 Step -1";
Debug.ShouldStop(262144);
{
final int step1 = -1;
final int limit1 = 1;
_i = RemoteObject.solve(new RemoteObject[] {_cardlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue() ;
for (;(step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1) ;_i = ((int)(0 + _i + step1))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 84;BA.debugLine="Dim j As Int = Rnd(0, i+1)";
Debug.ShouldStop(524288);
_j = all_active_recall.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1)));Debug.locals.put("j", _j);Debug.locals.put("j", _j);
 BA.debugLineNum = 86;BA.debugLine="Dim temp As Object = cardList.Get(i)";
Debug.ShouldStop(2097152);
_temp = _cardlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)));Debug.locals.put("temp", _temp);Debug.locals.put("temp", _temp);
 BA.debugLineNum = 87;BA.debugLine="cardList.Set(i, cardList.Get(j))";
Debug.ShouldStop(4194304);
_cardlist.runVoidMethod ("Set",(Object)(BA.numberCast(int.class, _i)),(Object)(_cardlist.runMethod(false,"Get",(Object)(_j))));
 BA.debugLineNum = 88;BA.debugLine="cardList.Set(j, temp)";
Debug.ShouldStop(8388608);
_cardlist.runVoidMethod ("Set",(Object)(_j),(Object)(_temp));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 90;BA.debugLine="End Sub";
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