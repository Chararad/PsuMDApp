package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class deck_all_cards_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (deck_all_cards) ","deck_all_cards",18,deck_all_cards.mostCurrent.activityBA,deck_all_cards.mostCurrent,20);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.deck_all_cards.remoteMe.runUserSub(false, "deck_all_cards","activity_create", _firsttime);}
RemoteObject _chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _subdeckname = RemoteObject.createImmutable("");
RemoteObject _subcards = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _newcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 21;BA.debugLine="Select Starter.themeNumber";
Debug.ShouldStop(1048576);
switch (BA.switchObjectToInt(deck_all_cards.mostCurrent._starter._themenumber /*RemoteObject*/ ,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2))) {
case 0: {
 BA.debugLineNum = 23;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",deck_all_cards.mostCurrent._starter._darkmode /*RemoteObject*/ ,deck_all_cards.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 24;BA.debugLine="Activity.LoadLayout(\"DACLayout\")";
Debug.ShouldStop(8388608);
deck_all_cards.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("DACLayout")),deck_all_cards.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout(\"DACLayoutDark\")";
Debug.ShouldStop(33554432);
deck_all_cards.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("DACLayoutDark")),deck_all_cards.mostCurrent.activityBA);
 };
 break; }
case 1: {
 BA.debugLineNum = 29;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",deck_all_cards.mostCurrent._starter._darkmode /*RemoteObject*/ ,deck_all_cards.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"DACLayout2\")";
Debug.ShouldStop(536870912);
deck_all_cards.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("DACLayout2")),deck_all_cards.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"DACLayoutDark2\")";
Debug.ShouldStop(-2147483648);
deck_all_cards.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("DACLayoutDark2")),deck_all_cards.mostCurrent.activityBA);
 };
 break; }
case 2: {
 BA.debugLineNum = 35;BA.debugLine="If Starter.darkMode = False Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",deck_all_cards.mostCurrent._starter._darkmode /*RemoteObject*/ ,deck_all_cards.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"DACLayout3\")";
Debug.ShouldStop(8);
deck_all_cards.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("DACLayout3")),deck_all_cards.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"DACLayoutDark3\")";
Debug.ShouldStop(32);
deck_all_cards.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("DACLayoutDark3")),deck_all_cards.mostCurrent.activityBA);
 };
 break; }
}
;
 BA.debugLineNum = 42;BA.debugLine="deckname.Text = FlashcardActivity.item_longclick";
Debug.ShouldStop(512);
deck_all_cards.mostCurrent._deckname.runMethod(true,"setText",BA.ObjectToCharSequence(deck_all_cards.mostCurrent._flashcardactivity._item_longclick /*RemoteObject*/ ));
 BA.debugLineNum = 43;BA.debugLine="cards.Initialize";
Debug.ShouldStop(1024);
deck_all_cards.mostCurrent._cards.runVoidMethod ("Initialize");
 BA.debugLineNum = 45;BA.debugLine="Dim chosendeck As Map = FlashcardActivity.deck.Ge";
Debug.ShouldStop(4096);
_chosendeck = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_chosendeck = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), deck_all_cards.mostCurrent._flashcardactivity._deck /*RemoteObject*/ .runMethod(false,"Get",(Object)((deck_all_cards.mostCurrent._flashcardactivity._selecteddeck /*RemoteObject*/ ))));Debug.locals.put("chosendeck", _chosendeck);Debug.locals.put("chosendeck", _chosendeck);
 BA.debugLineNum = 46;BA.debugLine="For Each subdeckname As String In chosendeck.Keys";
Debug.ShouldStop(8192);
{
final RemoteObject group24 = _chosendeck.runMethod(false,"Keys");
final int groupLen24 = group24.runMethod(true,"getSize").<Integer>get()
;int index24 = 0;
;
for (; index24 < groupLen24;index24++){
_subdeckname = BA.ObjectToString(group24.runMethod(false,"Get",index24));Debug.locals.put("subdeckname", _subdeckname);
Debug.locals.put("subdeckname", _subdeckname);
 BA.debugLineNum = 47;BA.debugLine="Dim subcards As List = chosendeck.Get(subdecknam";
Debug.ShouldStop(16384);
_subcards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_subcards = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _chosendeck.runMethod(false,"Get",(Object)((_subdeckname))));Debug.locals.put("subcards", _subcards);Debug.locals.put("subcards", _subcards);
 BA.debugLineNum = 48;BA.debugLine="For Each card As Map In subcards";
Debug.ShouldStop(32768);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group26 = _subcards;
final int groupLen26 = group26.runMethod(true,"getSize").<Integer>get()
;int index26 = 0;
;
for (; index26 < groupLen26;index26++){
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group26.runMethod(false,"Get",index26));Debug.locals.put("card", _card);
Debug.locals.put("card", _card);
 BA.debugLineNum = 49;BA.debugLine="Dim newcard As Map";
Debug.ShouldStop(65536);
_newcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("newcard", _newcard);
 BA.debugLineNum = 50;BA.debugLine="newcard.Initialize";
Debug.ShouldStop(131072);
_newcard.runVoidMethod ("Initialize");
 BA.debugLineNum = 51;BA.debugLine="newcard.Put(\"Q\", card.Get(\"Q\"))";
Debug.ShouldStop(262144);
_newcard.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("Q"))),(Object)(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q"))))));
 BA.debugLineNum = 52;BA.debugLine="newcard.Put(\"A\", card.Get(\"A\"))";
Debug.ShouldStop(524288);
_newcard.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("A"))),(Object)(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A"))))));
 BA.debugLineNum = 53;BA.debugLine="newcard.Put(\"subdeck\", subdeckname) 'get subdec";
Debug.ShouldStop(1048576);
_newcard.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("subdeck"))),(Object)((_subdeckname)));
 BA.debugLineNum = 54;BA.debugLine="cards.Add(newcard)";
Debug.ShouldStop(2097152);
deck_all_cards.mostCurrent._cards.runVoidMethod ("Add",(Object)((_newcard.getObject())));
 }
}Debug.locals.put("card", _card);
;
 }
}Debug.locals.put("subdeckname", _subdeckname);
;
 BA.debugLineNum = 58;BA.debugLine="ShowALLCards(cards)";
Debug.ShouldStop(33554432);
_showallcards(deck_all_cards.mostCurrent._cards);
 BA.debugLineNum = 60;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
		Debug.PushSubsStack("Activity_Pause (deck_all_cards) ","deck_all_cards",18,deck_all_cards.mostCurrent.activityBA,deck_all_cards.mostCurrent,105);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.deck_all_cards.remoteMe.runUserSub(false, "deck_all_cards","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 105;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(256);
 BA.debugLineNum = 107;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
		Debug.PushSubsStack("Activity_Resume (deck_all_cards) ","deck_all_cards",18,deck_all_cards.mostCurrent.activityBA,deck_all_cards.mostCurrent,101);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.deck_all_cards.remoteMe.runUserSub(false, "deck_all_cards","activity_resume");}
 BA.debugLineNum = 101;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(16);
 BA.debugLineNum = 102;BA.debugLine="ShowALLCards(cards)";
Debug.ShouldStop(32);
_showallcards(deck_all_cards.mostCurrent._cards);
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
public static RemoteObject  _addbtn_click() throws Exception{
try {
		Debug.PushSubsStack("addbtn_Click (deck_all_cards) ","deck_all_cards",18,deck_all_cards.mostCurrent.activityBA,deck_all_cards.mostCurrent,110);
if (RapidSub.canDelegate("addbtn_click")) { return b4a.example.deck_all_cards.remoteMe.runUserSub(false, "deck_all_cards","addbtn_click");}
 BA.debugLineNum = 110;BA.debugLine="Private Sub addbtn_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 111;BA.debugLine="StartActivity(Add_card_module)";
Debug.ShouldStop(16384);
deck_all_cards.mostCurrent.__c.runVoidMethod ("StartActivity",deck_all_cards.processBA,(Object)((deck_all_cards.mostCurrent._add_card_module.getObject())));
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("backbtn_Click (deck_all_cards) ","deck_all_cards",18,deck_all_cards.mostCurrent.activityBA,deck_all_cards.mostCurrent,114);
if (RapidSub.canDelegate("backbtn_click")) { return b4a.example.deck_all_cards.remoteMe.runUserSub(false, "deck_all_cards","backbtn_click");}
 BA.debugLineNum = 114;BA.debugLine="Private Sub backbtn_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="Activity.finish";
Debug.ShouldStop(262144);
deck_all_cards.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 116;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
deck_all_cards.mostCurrent._cards = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 16;BA.debugLine="Private ScrollView1 As ScrollView";
deck_all_cards.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private deckname As Label";
deck_all_cards.mostCurrent._deckname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showallcards(RemoteObject _cardslist) throws Exception{
try {
		Debug.PushSubsStack("ShowALLCards (deck_all_cards) ","deck_all_cards",18,deck_all_cards.mostCurrent.activityBA,deck_all_cards.mostCurrent,62);
if (RapidSub.canDelegate("showallcards")) { return b4a.example.deck_all_cards.remoteMe.runUserSub(false, "deck_all_cards","showallcards", _cardslist);}
RemoteObject _toppos = RemoteObject.createImmutable(0);
RemoteObject _cardheight = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _card = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lbl2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("CardsList", _cardslist);
 BA.debugLineNum = 62;BA.debugLine="Sub ShowALLCards(CardsList As List)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 64;BA.debugLine="ScrollView1.Panel.RemoveAllViews";
Debug.ShouldStop(-2147483648);
deck_all_cards.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 66;BA.debugLine="Dim topPos As Int = 0";
Debug.ShouldStop(2);
_toppos = BA.numberCast(int.class, 0);Debug.locals.put("topPos", _toppos);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 67;BA.debugLine="Dim cardHeight As Int = 150dip 'height";
Debug.ShouldStop(4);
_cardheight = deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150)));Debug.locals.put("cardHeight", _cardheight);Debug.locals.put("cardHeight", _cardheight);
 BA.debugLineNum = 69;BA.debugLine="For i = 0 To CardsList.Size -1";
Debug.ShouldStop(16);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_cardslist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 70;BA.debugLine="Dim card As Map = CardsList.Get(i)";
Debug.ShouldStop(32);
_card = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_card = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _cardslist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("card", _card);Debug.locals.put("card", _card);
 BA.debugLineNum = 71;BA.debugLine="Dim p As Panel";
Debug.ShouldStop(64);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 72;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(128);
_p.runVoidMethod ("Initialize",deck_all_cards.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 73;BA.debugLine="p.Color = Colors.White";
Debug.ShouldStop(256);
_p.runVoidMethod ("setColor",deck_all_cards.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 74;BA.debugLine="ScrollView1.Panel.AddView(p, 10dip, topPos, Scr";
Debug.ShouldStop(512);
deck_all_cards.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_toppos),(Object)(RemoteObject.solve(new RemoteObject[] {deck_all_cards.mostCurrent._scrollview1.runMethod(true,"getWidth"),deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(_cardheight));
 BA.debugLineNum = 76;BA.debugLine="Dim lbl2 As Label";
Debug.ShouldStop(2048);
_lbl2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl2", _lbl2);
 BA.debugLineNum = 77;BA.debugLine="lbl2.Initialize(\"\")";
Debug.ShouldStop(4096);
_lbl2.runVoidMethod ("Initialize",deck_all_cards.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 78;BA.debugLine="lbl2.Text = card.Get(\"subdeck\")";
Debug.ShouldStop(8192);
_lbl2.runMethod(true,"setText",BA.ObjectToCharSequence(_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("subdeck"))))));
 BA.debugLineNum = 79;BA.debugLine="lbl2.TextColor = Colors.Black";
Debug.ShouldStop(16384);
_lbl2.runMethod(true,"setTextColor",deck_all_cards.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 80;BA.debugLine="lbl2.TextSize = 14";
Debug.ShouldStop(32768);
_lbl2.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 81;BA.debugLine="lbl2.SingleLine = False";
Debug.ShouldStop(65536);
_lbl2.runVoidMethod ("setSingleLine",deck_all_cards.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 82;BA.debugLine="lbl2.Typeface = Typeface.DEFAULT_BOLD";
Debug.ShouldStop(131072);
_lbl2.runMethod(false,"setTypeface",deck_all_cards.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 83;BA.debugLine="p.AddView(lbl2, 10dip, 10dip, ScrollView1.Width";
Debug.ShouldStop(262144);
_p.runVoidMethod ("AddView",(Object)((_lbl2.getObject())),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {deck_all_cards.mostCurrent._scrollview1.runMethod(true,"getWidth"),deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 85;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(1048576);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 86;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(2097152);
_lbl.runVoidMethod ("Initialize",deck_all_cards.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 87;BA.debugLine="lbl.Text = \"Q: \" & card.Get(\"Q\") & CRLF & \"A: \"";
Debug.ShouldStop(4194304);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Q: "),_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("Q")))),deck_all_cards.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("A: "),_card.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("A")))))));
 BA.debugLineNum = 88;BA.debugLine="lbl.TextColor = Colors.black";
Debug.ShouldStop(8388608);
_lbl.runMethod(true,"setTextColor",deck_all_cards.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 89;BA.debugLine="lbl.TextSize = 12";
Debug.ShouldStop(16777216);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 90;BA.debugLine="lbl.SingleLine = False";
Debug.ShouldStop(33554432);
_lbl.runVoidMethod ("setSingleLine",deck_all_cards.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 92;BA.debugLine="p.AddView(lbl, 10dip, 30dip, ScrollView1.Width";
Debug.ShouldStop(134217728);
_p.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))),(Object)(RemoteObject.solve(new RemoteObject[] {deck_all_cards.mostCurrent._scrollview1.runMethod(true,"getWidth"),deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 93;BA.debugLine="topPos = topPos + lbl.height + 10dip";
Debug.ShouldStop(268435456);
_toppos = RemoteObject.solve(new RemoteObject[] {_toppos,_lbl.runMethod(true,"getHeight"),deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "++",2, 1);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 95;BA.debugLine="topPos = topPos + cardHeight + 10dip";
Debug.ShouldStop(1073741824);
_toppos = RemoteObject.solve(new RemoteObject[] {_toppos,_cardheight,deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "++",2, 1);Debug.locals.put("topPos", _toppos);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 97;BA.debugLine="ScrollView1.Panel.Height = topPos + 10dip";
Debug.ShouldStop(1);
deck_all_cards.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_toppos,deck_all_cards.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))}, "+",1, 1));
 BA.debugLineNum = 99;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}