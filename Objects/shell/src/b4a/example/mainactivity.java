
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class mainactivity implements IRemote{
	public static mainactivity mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public mainactivity() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("mainactivity"), "b4a.example.mainactivity");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, mainactivity.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _timerclock = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _kvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _kvspref = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _format24h = RemoteObject.createImmutable(false);
public static RemoteObject _reglayout = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
public static RemoteObject _darkmodelayout = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
public static RemoteObject _size = RemoteObject.createImmutable(0);
public static RemoteObject _hsv = RemoteObject.declareNull("anywheresoftware.b4a.objects.HorizontalScrollViewWrapper");
public static RemoteObject _computergif = RemoteObject.declareNull("b4a.example.b4xgifview");
public static RemoteObject _dcomputergif = RemoteObject.declareNull("b4a.example.b4xgifview");
public static RemoteObject _curtain = RemoteObject.declareNull("b4a.example.b4xgifview");
public static RemoteObject _dcurtain = RemoteObject.declareNull("b4a.example.b4xgifview");
public static RemoteObject _notesopen = RemoteObject.declareNull("b4a.example.b4xgifview");
public static RemoteObject _notebook = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dnotesopen = RemoteObject.declareNull("b4a.example.b4xgifview");
public static RemoteObject _clockbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _clocklightbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _infopnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
public static RemoteObject _infotitlelbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _infodesclbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _infopagelbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _infopage = RemoteObject.createImmutable(0);
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.todoactivity _todoactivity = null;
public static b4a.example.calendaractivity _calendaractivity = null;
public static b4a.example.schedule_module _schedule_module = null;
public static b4a.example.deck_all_cards _deck_all_cards = null;
public static b4a.example.clockactivity _clockactivity = null;
public static b4a.example.home_activity _home_activity = null;
public static b4a.example.login _login = null;
public static b4a.example.register _register = null;
public static b4a.example.musicactivity _musicactivity = null;
public static b4a.example.tutorialactivity _tutorialactivity = null;
public static b4a.example.helpactivity _helpactivity = null;
public static b4a.example.active_recall _active_recall = null;
public static b4a.example.add_card_module _add_card_module = null;
public static b4a.example.add_card_module2 _add_card_module2 = null;
public static b4a.example.add_events_module _add_events_module = null;
public static b4a.example.all_active_recall _all_active_recall = null;
public static b4a.example.card_module _card_module = null;
public static b4a.example.corkactivity _corkactivity = null;
public static b4a.example.day_module _day_module = null;
public static b4a.example.editnote _editnote = null;
public static b4a.example.flashcardactivity _flashcardactivity = null;
public static b4a.example.musicservice _musicservice = null;
public static b4a.example.navactivity _navactivity = null;
public static b4a.example.noteactivity _noteactivity = null;
public static b4a.example.subdeck_module _subdeck_module = null;
public static b4a.example.themeactivity _themeactivity = null;
public static b4a.example.httputils2service _httputils2service = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Activity",mainactivity.mostCurrent._activity,"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"clockBtn",mainactivity.mostCurrent._clockbtn,"clockLightBtn",mainactivity.mostCurrent._clocklightbtn,"computerGif",mainactivity.mostCurrent._computergif,"corkActivity",Debug.moduleToString(b4a.example.corkactivity.class),"curtain",mainactivity.mostCurrent._curtain,"darkModeLayout",mainactivity.mostCurrent._darkmodelayout,"day_module",Debug.moduleToString(b4a.example.day_module.class),"dcomputerGif",mainactivity.mostCurrent._dcomputergif,"dCurtain",mainactivity.mostCurrent._dcurtain,"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"dnotesOpen",mainactivity.mostCurrent._dnotesopen,"editnote",Debug.moduleToString(b4a.example.editnote.class),"FlashcardActivity",Debug.moduleToString(b4a.example.flashcardactivity.class),"format24h",mainactivity._format24h,"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"Home_Activity",Debug.moduleToString(b4a.example.home_activity.class),"hsv",mainactivity.mostCurrent._hsv,"HttpUtils2Service",Debug.moduleToString(b4a.example.httputils2service.class),"infoDescLbl",mainactivity.mostCurrent._infodesclbl,"infoPage",mainactivity._infopage,"infoPageLbl",mainactivity.mostCurrent._infopagelbl,"infoPnl",mainactivity.mostCurrent._infopnl,"infoTitleLbl",mainactivity.mostCurrent._infotitlelbl,"kvs",mainactivity._kvs,"kvsPref",mainactivity._kvspref,"LogIn",Debug.moduleToString(b4a.example.login.class),"Main",Debug.moduleToString(b4a.example.main.class),"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"noteBook",mainactivity.mostCurrent._notebook,"notesOpen",mainactivity.mostCurrent._notesopen,"Register",Debug.moduleToString(b4a.example.register.class),"regLayout",mainactivity.mostCurrent._reglayout,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"size",mainactivity._size,"Starter",Debug.moduleToString(b4a.example.starter.class),"Subdeck_Module",Debug.moduleToString(b4a.example.subdeck_module.class),"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"timerClock",mainactivity._timerclock,"todoActivity",Debug.moduleToString(b4a.example.todoactivity.class),"tutorialActivity",Debug.moduleToString(b4a.example.tutorialactivity.class),"xui",mainactivity._xui};
}
}