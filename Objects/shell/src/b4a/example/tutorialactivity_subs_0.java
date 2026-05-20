package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class tutorialactivity_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (tutorialactivity) ","tutorialactivity",5,tutorialactivity.mostCurrent.activityBA,tutorialactivity.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.tutorialactivity.remoteMe.runUserSub(false, "tutorialactivity","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout(\"tutoriallayout\")";
Debug.ShouldStop(67108864);
tutorialactivity.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("tutoriallayout")),tutorialactivity.mostCurrent.activityBA);
 BA.debugLineNum = 28;BA.debugLine="showTutorialPage(0)";
Debug.ShouldStop(134217728);
_showtutorialpage(BA.numberCast(int.class, 0));
 BA.debugLineNum = 29;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
		Debug.PushSubsStack("Activity_Pause (tutorialactivity) ","tutorialactivity",5,tutorialactivity.mostCurrent.activityBA,tutorialactivity.mostCurrent,35);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.tutorialactivity.remoteMe.runUserSub(false, "tutorialactivity","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 35;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
Debug.ShouldStop(4);
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("Activity_Resume (tutorialactivity) ","tutorialactivity",5,tutorialactivity.mostCurrent.activityBA,tutorialactivity.mostCurrent,31);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.tutorialactivity.remoteMe.runUserSub(false, "tutorialactivity","activity_resume");}
 BA.debugLineNum = 31;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 33;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnback_click() throws Exception{
try {
		Debug.PushSubsStack("btnBack_Click (tutorialactivity) ","tutorialactivity",5,tutorialactivity.mostCurrent.activityBA,tutorialactivity.mostCurrent,317);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.tutorialactivity.remoteMe.runUserSub(false, "tutorialactivity","btnback_click");}
 BA.debugLineNum = 317;BA.debugLine="Sub btnBack_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 318;BA.debugLine="If tutorialPage > 0 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean(">",tutorialactivity._tutorialpage,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 319;BA.debugLine="showTutorialPage(tutorialPage - 1)";
Debug.ShouldStop(1073741824);
_showtutorialpage(RemoteObject.solve(new RemoteObject[] {tutorialactivity._tutorialpage,RemoteObject.createImmutable(1)}, "-",1, 1));
 };
 BA.debugLineNum = 321;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnnext_click() throws Exception{
try {
		Debug.PushSubsStack("btnNext_Click (tutorialactivity) ","tutorialactivity",5,tutorialactivity.mostCurrent.activityBA,tutorialactivity.mostCurrent,305);
if (RapidSub.canDelegate("btnnext_click")) { return b4a.example.tutorialactivity.remoteMe.runUserSub(false, "tutorialactivity","btnnext_click");}
 BA.debugLineNum = 305;BA.debugLine="Sub btnNext_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 306;BA.debugLine="If tutorialPage < 23 Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("<",tutorialactivity._tutorialpage,BA.numberCast(double.class, 23))) { 
 BA.debugLineNum = 307;BA.debugLine="showTutorialPage(tutorialPage + 1)";
Debug.ShouldStop(262144);
_showtutorialpage(RemoteObject.solve(new RemoteObject[] {tutorialactivity._tutorialpage,RemoteObject.createImmutable(1)}, "+",1, 1));
 }else {
 BA.debugLineNum = 309;BA.debugLine="If chkDontShow.Checked Then";
Debug.ShouldStop(1048576);
if (tutorialactivity.mostCurrent._chkdontshow.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 310;BA.debugLine="Starter.prefKvs.Put(\"skipTutorial\", True)";
Debug.ShouldStop(2097152);
tutorialactivity.mostCurrent._starter._prefkvs /*RemoteObject*/ .runVoidMethod ("_put",(Object)(BA.ObjectToString("skipTutorial")),(Object)((tutorialactivity.mostCurrent.__c.getField(true,"True"))));
 };
 BA.debugLineNum = 312;BA.debugLine="StartActivity(MainActivity)";
Debug.ShouldStop(8388608);
tutorialactivity.mostCurrent.__c.runVoidMethod ("StartActivity",tutorialactivity.processBA,(Object)((tutorialactivity.mostCurrent._mainactivity.getObject())));
 BA.debugLineNum = 313;BA.debugLine="Activity.Finish";
Debug.ShouldStop(16777216);
tutorialactivity.mostCurrent._activity.runVoidMethod ("Finish");
 };
 BA.debugLineNum = 315;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
 //BA.debugLineNum = 17;BA.debugLine="Private headerLbl     As Label";
tutorialactivity.mostCurrent._headerlbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private bodyLbl       As Label";
tutorialactivity.mostCurrent._bodylbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private pageIndicator As Label";
tutorialactivity.mostCurrent._pageindicator = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private btnBack       As Button";
tutorialactivity.mostCurrent._btnback = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private btnNext       As Button";
tutorialactivity.mostCurrent._btnnext = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private chkDontShow   As CheckBox";
tutorialactivity.mostCurrent._chkdontshow = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private tutorialImg   As ImageView";
tutorialactivity.mostCurrent._tutorialimg = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private xui As XUI";
tutorialactivity._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 10;BA.debugLine="Dim tutorialPage As Int = 0";
tutorialactivity._tutorialpage = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showtutorialpage(RemoteObject _page) throws Exception{
try {
		Debug.PushSubsStack("showTutorialPage (tutorialactivity) ","tutorialactivity",5,tutorialactivity.mostCurrent.activityBA,tutorialactivity.mostCurrent,39);
if (RapidSub.canDelegate("showtutorialpage")) { return b4a.example.tutorialactivity.remoteMe.runUserSub(false, "tutorialactivity","showtutorialpage", _page);}
Debug.locals.put("page", _page);
 BA.debugLineNum = 39;BA.debugLine="Sub showTutorialPage(page As Int)";
Debug.ShouldStop(64);
 BA.debugLineNum = 40;BA.debugLine="tutorialPage = page";
Debug.ShouldStop(128);
tutorialactivity._tutorialpage = _page;
 BA.debugLineNum = 42;BA.debugLine="Select page";
Debug.ShouldStop(512);
switch (BA.switchObjectToInt(_page,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4),BA.numberCast(int.class, 5),BA.numberCast(int.class, 6),BA.numberCast(int.class, 7),BA.numberCast(int.class, 8),BA.numberCast(int.class, 9),BA.numberCast(int.class, 10),BA.numberCast(int.class, 11),BA.numberCast(int.class, 12),BA.numberCast(int.class, 13),BA.numberCast(int.class, 14),BA.numberCast(int.class, 15),BA.numberCast(int.class, 16),BA.numberCast(int.class, 17),BA.numberCast(int.class, 18),BA.numberCast(int.class, 19),BA.numberCast(int.class, 20),BA.numberCast(int.class, 21),BA.numberCast(int.class, 22),BA.numberCast(int.class, 23))) {
case 0: {
 BA.debugLineNum = 44;BA.debugLine="headerLbl.Text = \"Welcome to Athena!\"";
Debug.ShouldStop(2048);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Welcome to Athena!"));
 BA.debugLineNum = 45;BA.debugLine="bodyLbl.Text = \"Welcome to Athena — your all-in";
Debug.ShouldStop(4096);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence("Welcome to Athena — your all-in-one study companion. She's here to keep you focused, organized, and inspired. Before we get started, let's show you around."));
 BA.debugLineNum = 46;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(8192);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpwreath.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 1: {
 BA.debugLineNum = 49;BA.debugLine="headerLbl.Text = \"Home Screen  (1/2)\"";
Debug.ShouldStop(65536);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Home Screen  (1/2)"));
 BA.debugLineNum = 50;BA.debugLine="bodyLbl.Text = \"This is your central hub — beau";
Debug.ShouldStop(131072);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("This is your central hub — beautiful and intuitive."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Everything you need lives right here:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Pomodoro Timer  (Clock)"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • To-Do List  (PC Screen)"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Calendar"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Flashcards  (Books)"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Corkboard"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Notepad"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Lo-fi Music Player"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Dark / Light Mode  (Lamp)"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Themes  (Plant / Shelf Toy)"))));
 BA.debugLineNum = 61;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("homescreenui.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 2: {
 BA.debugLineNum = 64;BA.debugLine="headerLbl.Text = \"Home Screen  (2/2)\"";
Debug.ShouldStop(-2147483648);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Home Screen  (2/2)"));
 BA.debugLineNum = 65;BA.debugLine="bodyLbl.Text = \"The home screen is alive — subt";
Debug.ShouldStop(1);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("The home screen is alive — subtle animations and smooth transitions make "),RemoteObject.createImmutable("every interaction feel fluid and enjoyable."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Tapping any feature icon shows a short description of that tool."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Opening Athena for the first time? This Tutorial walks you through everything "),RemoteObject.createImmutable("before you start — so you're never left guessing."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("You can reopen this guide anytime from the Help screen."))));
 BA.debugLineNum = 71;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(64);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("dhomescreenui.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 3: {
 BA.debugLineNum = 74;BA.debugLine="headerLbl.Text = \"Navigation & Help\"";
Debug.ShouldStop(512);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Navigation & Help"));
 BA.debugLineNum = 75;BA.debugLine="bodyLbl.Text = \"Navigation Button — your quick-";
Debug.ShouldStop(1024);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Navigation Button — your quick-access menu."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Tap it for a clean list of every feature: Pomodoro, Notepad, Corkboard, "),RemoteObject.createImmutable("Flashcards, and more. No clutter — just fast, direct navigation."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Help Button — highlights every clickable object on the home screen "),RemoteObject.createImmutable("and explains what it does, so you'll never feel lost."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Long-press any button to see its name and a step-by-step walkthrough "),RemoteObject.createImmutable("of how to use that specific feature."))));
 BA.debugLineNum = 82;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(131072);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpnav.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 4: {
 BA.debugLineNum = 85;BA.debugLine="headerLbl.Text = \"Leaderboard\"";
Debug.ShouldStop(1048576);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Leaderboard"));
 BA.debugLineNum = 86;BA.debugLine="bodyLbl.Text = \"Leaderboard Button — your ranki";
Debug.ShouldStop(2097152);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Leaderboard Button — your ranking board for flashcard progress."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Tap the star button for to see every player's XP, streak, and their ranking!"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Daily, Weekly, All-Time — pick your battleground. Tap any TAB To switch views."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Top card — the #1 spot, front and center."),RemoteObject.createImmutable("The current leader gets the big card up top. That could be you."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Ranking — XP wins, streak breaks ties. More XP = higher rank. Same XP? "),RemoteObject.createImmutable("Longer streak goes first. Still tied? Better correct rate wins."))));
 BA.debugLineNum = 93;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helplb.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 5: {
 BA.debugLineNum = 96;BA.debugLine="headerLbl.Text = \"Lamp — Dark / Light Mode\"";
Debug.ShouldStop(-2147483648);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Lamp — Dark / Light Mode"));
 BA.debugLineNum = 97;BA.debugLine="bodyLbl.Text = \"Tap the Lamp to instantly switc";
Debug.ShouldStop(1);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the Lamp to instantly switch between Light Mode and Dark Mode."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Whether you're studying under the morning sun or pulling a late-night session, "),RemoteObject.createImmutable("Athena adjusts with you — reducing eye strain and keeping your focus sharp "),RemoteObject.createImmutable("no matter the hour."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("One tap, and your whole environment transforms."))));
 BA.debugLineNum = 102;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(32);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helplamp.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 6: {
 BA.debugLineNum = 105;BA.debugLine="headerLbl.Text = \"Music Player  (1/2)\"";
Debug.ShouldStop(256);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Music Player  (1/2)"));
 BA.debugLineNum = 106;BA.debugLine="bodyLbl.Text = \"Tap the record player and lo-fi";
Debug.ShouldStop(512);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the record player and lo-fi music fills the room — instantly setting "),RemoteObject.createImmutable("the mood for deep focus."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Controls:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Play / Pause — start or stop the current track."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Previous / Next — move between songs."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Seek bar — jump to any point in the track."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Playlist — tap any song in the list to play it directly."))));
 BA.debugLineNum = 113;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(65536);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpmusic.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 7: {
 BA.debugLineNum = 116;BA.debugLine="headerLbl.Text = \"Music Player  (2/2)\"";
Debug.ShouldStop(524288);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Music Player  (2/2)"));
 BA.debugLineNum = 117;BA.debugLine="bodyLbl.Text = \"Athena's music library has grow";
Debug.ShouldStop(1048576);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Athena's music library has grown — more curated lo-fi tracks are now built in."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Have a song that puts you in the zone? Upload it directly into the app "),RemoteObject.createImmutable("from the player screen anytime."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Your study playlist, your rules."))));
 BA.debugLineNum = 121;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(16777216);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpmusic.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 8: {
 BA.debugLineNum = 124;BA.debugLine="headerLbl.Text = \"Clock & Pomodoro  (1/2)\"";
Debug.ShouldStop(134217728);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Clock & Pomodoro  (1/2)"));
 BA.debugLineNum = 125;BA.debugLine="bodyLbl.Text = \"The Clock is more than a timepi";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("The Clock is more than a timepiece — it is your productivity partner."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Real-time clock:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap the format button to switch between 12-hr and 24-hr display."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Pomodoro method — default durations:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Pomodoro session  — 25 minutes"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Short break          —   3 minutes"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Long break            — 10 minutes"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("After four Pomodoro cycles you automatically earn a longer rest."))));
 BA.debugLineNum = 133;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(16);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpclock.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 9: {
 BA.debugLineNum = 136;BA.debugLine="headerLbl.Text = \"Clock & Pomodoro  (2/2)\"";
Debug.ShouldStop(128);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Clock & Pomodoro  (2/2)"));
 BA.debugLineNum = 137;BA.debugLine="bodyLbl.Text = \"Controls:\" & CRLF & _";
Debug.ShouldStop(256);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Controls:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Start / Pause — begin or pause the current session."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Next — cycle between Pomodoro, short break, and long break."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Settings:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Customize the duration of each timer type to fit your own rhythm."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Change the clock display format."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Stay focused, rest when earned, and keep the cycle going."))));
 BA.debugLineNum = 144;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(32768);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpclock.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 10: {
 BA.debugLineNum = 147;BA.debugLine="headerLbl.Text = \"Flashcards  (1/3)\"";
Debug.ShouldStop(262144);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Flashcards  (1/3)"));
 BA.debugLineNum = 148;BA.debugLine="bodyLbl.Text = \"Tap the Books to open Flashcard";
Debug.ShouldStop(524288);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the Books to open Flashcards — your ultimate memory tool."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Deck management:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap  +  to create a new deck, name it, and save."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Long-press a deck to: add cards, create subdecks, rename, or delete."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Open a deck and tap  +  to add subdecks or cards from inside."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Confirmation is always required before anything is permanently removed."))));
 BA.debugLineNum = 154;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(33554432);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpflashcard.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 11: {
 BA.debugLineNum = 157;BA.debugLine="headerLbl.Text = \"Flashcards  (2/3)\"";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Flashcards  (2/3)"));
 BA.debugLineNum = 158;BA.debugLine="bodyLbl.Text = \"Reviewing a deck:\" & CRLF & _";
Debug.ShouldStop(536870912);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Reviewing a deck:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Answer — flip the card to reveal the answer."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Next — advance to the next card."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Back — revisit the previous card."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Refresh — reshuffle and restart the deck."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Active Recall challenges you to remember before flipping — one of the most "),RemoteObject.createImmutable("effective techniques for long-term retention."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("A progress bar and percentage show how far you have made it through a deck."))));
 BA.debugLineNum = 166;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(32);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpflashcard.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 12: {
 BA.debugLineNum = 169;BA.debugLine="headerLbl.Text = \"Flashcards  (3/3)\"";
Debug.ShouldStop(256);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Flashcards  (3/3)"));
 BA.debugLineNum = 170;BA.debugLine="bodyLbl.Text = \"Athena's AI can do the heavy li";
Debug.ShouldStop(512);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Athena's AI can do the heavy lifting for you."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Upload a file — a PDF, a document, or your lecture notes — and the AI reads "),RemoteObject.createImmutable("it and automatically generates a full set of flashcards for you."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Your deck, built without the tedious effort."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Because your convenience matters too."))));
 BA.debugLineNum = 175;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(16384);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpflashcard.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 13: {
 BA.debugLineNum = 178;BA.debugLine="headerLbl.Text = \"Calendar  (1/3)\"";
Debug.ShouldStop(131072);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Calendar  (1/3)"));
 BA.debugLineNum = 179;BA.debugLine="bodyLbl.Text = \"Tap the Calendar to open your p";
Debug.ShouldStop(262144);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the Calendar to open your personal time designer."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("By default you land on Month View — your big-picture overview of everything ahead."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Three views via the Menu Button (top-left):"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Schedule View — streamlined list of all upcoming tasks and events."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Day View — focused hour-by-hour agenda for a single day."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Month View — full calendar grid; use the Arrow button to change month/year."))));
 BA.debugLineNum = 185;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(16777216);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpcalendar.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 14: {
 BA.debugLineNum = 188;BA.debugLine="headerLbl.Text = \"Calendar  (2/3)\"";
Debug.ShouldStop(134217728);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Calendar  (2/3)"));
 BA.debugLineNum = 189;BA.debugLine="bodyLbl.Text = \"Adding events:\" & CRLF & _";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Adding events:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap any date to open that day's hour-by-hour timeline."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap any time slot to create a schedule at that time."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap  +  and choose from:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("       - Add Event"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("       - Add Task"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("       - Birthday"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("       - Out of Office"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Always tap Save to confirm — or Delete if plans change."))));
 BA.debugLineNum = 198;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(32);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpcalendar.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 15: {
 BA.debugLineNum = 201;BA.debugLine="headerLbl.Text = \"Calendar  (3/3)\"";
Debug.ShouldStop(256);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Calendar  (3/3)"));
 BA.debugLineNum = 202;BA.debugLine="bodyLbl.Text = \"Schedules are no longer just pe";
Debug.ShouldStop(512);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Schedules are no longer just personal."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Group Collaboration lets you create a group with classmates or coworkers "),RemoteObject.createImmutable("and share your schedule with them — keeping everyone on the same page for "),RemoteObject.createImmutable("group projects, study sessions, and deadlines."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Never miss a group deadline again."))));
 BA.debugLineNum = 207;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(16384);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpcalendar.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 16: {
 BA.debugLineNum = 210;BA.debugLine="headerLbl.Text = \"To-Do List  (1/2)\"";
Debug.ShouldStop(131072);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("To-Do List  (1/2)"));
 BA.debugLineNum = 211;BA.debugLine="bodyLbl.Text = \"Tap the PC Screen to open the T";
Debug.ShouldStop(262144);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the PC Screen to open the To-Do List — your accountability partner."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Structured checkboxes keep every item actionable."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("How to use:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap New List to create a list (school, personal, group work...)."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Open the list, type a task, and press Enter to save."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Check tasks off as you complete them."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Navigate between lists easily from the side panel."))));
 BA.debugLineNum = 218;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(33554432);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helptodo.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 17: {
 BA.debugLineNum = 221;BA.debugLine="headerLbl.Text = \"To-Do List  (2/2)\"";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("To-Do List  (2/2)"));
 BA.debugLineNum = 222;BA.debugLine="bodyLbl.Text = \"Progress tracker:\" & CRLF & _";
Debug.ShouldStop(536870912);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Progress tracker:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("As you tick off tasks, the percentage tracker updates in real time — "),RemoteObject.createImmutable("showing exactly how close you are to finishing each list."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Every checkmark is a small win that adds up to something bigger."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Shared lists:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Share a To-Do List with your group — assign tasks, track progress together, "),RemoteObject.createImmutable("and make sure no one falls behind."))));
 BA.debugLineNum = 229;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(16);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helptodo.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 18: {
 BA.debugLineNum = 232;BA.debugLine="headerLbl.Text = \"Notepad  (1/2)\"";
Debug.ShouldStop(128);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Notepad  (1/2)"));
 BA.debugLineNum = 233;BA.debugLine="bodyLbl.Text = \"Tap the Notepad to capture the";
Debug.ShouldStop(256);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the Notepad to capture the details that matter most."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Creating a note:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Tap  +  to start a new note."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Enter a title and add tags to stay organized."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Write your content, then press Save."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Managing notes:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Long-press a note and confirm to delete it."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Use the search bar — type a title or tag and your note appears instantly."))));
 BA.debugLineNum = 241;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(65536);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpnotepad.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 19: {
 BA.debugLineNum = 244;BA.debugLine="headerLbl.Text = \"Notepad  (2/2)\"";
Debug.ShouldStop(524288);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Notepad  (2/2)"));
 BA.debugLineNum = 245;BA.debugLine="bodyLbl.Text = \"Athena's AI can summarize for y";
Debug.ShouldStop(1048576);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Athena's AI can summarize for you."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Upload a file or paste a block of text, and the AI reads it and generates "),RemoteObject.createImmutable("clean, condensed notes — capturing the key ideas without reading every word."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Lecture notes, project ideas, personal reflections — everything stays "),RemoteObject.createImmutable("structured, accessible, and always within reach."))));
 BA.debugLineNum = 250;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(33554432);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpnotepad.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 20: {
 BA.debugLineNum = 253;BA.debugLine="headerLbl.Text = \"Corkboard  (1/2)\"";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Corkboard  (1/2)"));
 BA.debugLineNum = 254;BA.debugLine="bodyLbl.Text = \"Tap the Corkboard — your digita";
Debug.ShouldStop(536870912);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Tap the Corkboard — your digital canvas, fully customizable and personal."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("What you can add:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Sticky Notes — quick reminders or brainstorming in a variety of colors."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Images — pin pictures from your gallery to inspire or organize a project."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Canvas — draw freely with colored pens; sketch ideas or map out concepts."))));
 BA.debugLineNum = 259;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(4);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpcorkboard.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 21: {
 BA.debugLineNum = 262;BA.debugLine="headerLbl.Text = \"Corkboard  (2/2)\"";
Debug.ShouldStop(32);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Corkboard  (2/2)"));
 BA.debugLineNum = 263;BA.debugLine="bodyLbl.Text = \"The Corkboard is where organiza";
Debug.ShouldStop(64);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("The Corkboard is where organization meets creativity."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Arrange, move, and rearrange everything exactly the way you want — "),RemoteObject.createImmutable("turning abstract ideas into something you can see, interact with, and refine."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("It is not just a workspace. It is a reflection of how you think."))));
 BA.debugLineNum = 267;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(1024);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpcorkboard.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 22: {
 BA.debugLineNum = 270;BA.debugLine="headerLbl.Text = \"Themes\"";
Debug.ShouldStop(8192);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("Themes"));
 BA.debugLineNum = 271;BA.debugLine="bodyLbl.Text = \"Your workspace should feel like";
Debug.ShouldStop(16384);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Your workspace should feel like yours."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Tap the Plant or the Stuffed Toy on the upper-right shelf to instantly "),RemoteObject.createImmutable("change the app's entire look and feel."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Three themes to choose from:"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Default              — the classic Athena look."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Y2K Aero          — glossy, retro-futuristic vibes."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("  • Pixelated Rustic — cozy pixel-art aesthetic."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("One tap and your environment refreshes — lively, inspiring, and uniquely you."))));
 BA.debugLineNum = 279;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(4194304);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helptheme.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
case 23: {
 BA.debugLineNum = 282;BA.debugLine="headerLbl.Text = \"You're All Set!\"";
Debug.ShouldStop(33554432);
tutorialactivity.mostCurrent._headerlbl.runMethod(true,"setText",BA.ObjectToCharSequence("You're All Set!"));
 BA.debugLineNum = 283;BA.debugLine="bodyLbl.Text = \"Everything you need is right at";
Debug.ShouldStop(67108864);
tutorialactivity.mostCurrent._bodylbl.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Everything you need is right at your fingertips — now go use Athena to your heart and brain's content."),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("Tick \"Don't show again\" below To skip this guide on future launches. You always have the Help Screen And Long Press feature If you ever need help. "),tutorialactivity.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable(""))));
 BA.debugLineNum = 285;BA.debugLine="tutorialImg.Bitmap = xui.LoadBitmapResize(File.";
Debug.ShouldStop(268435456);
tutorialactivity.mostCurrent._tutorialimg.runMethod(false,"setBitmap",(tutorialactivity._xui.runMethod(false,"LoadBitmapResize",(Object)(tutorialactivity.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("helpwreath.png")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getWidth")),(Object)(tutorialactivity.mostCurrent._tutorialimg.runMethod(true,"getHeight")),(Object)(tutorialactivity.mostCurrent.__c.getField(true,"True"))).getObject()));
 break; }
}
;
 BA.debugLineNum = 289;BA.debugLine="pageIndicator.Text = (page + 1) & \" / 24\"";
Debug.ShouldStop(1);
tutorialactivity.mostCurrent._pageindicator.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat((RemoteObject.solve(new RemoteObject[] {_page,RemoteObject.createImmutable(1)}, "+",1, 1)),RemoteObject.createImmutable(" / 24"))));
 BA.debugLineNum = 292;BA.debugLine="chkDontShow.Visible = (page = 23)";
Debug.ShouldStop(8);
tutorialactivity.mostCurrent._chkdontshow.runMethod(true,"setVisible",BA.ObjectToBoolean((RemoteObject.solveBoolean("=",_page,BA.numberCast(double.class, 23)))));
 BA.debugLineNum = 295;BA.debugLine="btnBack.Enabled = (page > 0)";
Debug.ShouldStop(64);
tutorialactivity.mostCurrent._btnback.runMethod(true,"setEnabled",BA.ObjectToBoolean((RemoteObject.solveBoolean(">",_page,BA.numberCast(double.class, 0)))));
 BA.debugLineNum = 298;BA.debugLine="If page = 23 Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",_page,BA.numberCast(double.class, 23))) { 
 BA.debugLineNum = 299;BA.debugLine="btnNext.Text = \"Finish\"";
Debug.ShouldStop(1024);
tutorialactivity.mostCurrent._btnnext.runMethod(true,"setText",BA.ObjectToCharSequence("Finish"));
 }else {
 BA.debugLineNum = 301;BA.debugLine="btnNext.Text = \"Next\"";
Debug.ShouldStop(4096);
tutorialactivity.mostCurrent._btnnext.runMethod(true,"setText",BA.ObjectToCharSequence("Next"));
 };
 BA.debugLineNum = 303;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}