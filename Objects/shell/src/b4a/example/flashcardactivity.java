
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

public class flashcardactivity implements IRemote{
	public static flashcardactivity mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public flashcardactivity() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("flashcardactivity"), "b4a.example.flashcardactivity");
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
		pcBA = new PCBA(this, flashcardactivity.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _deck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static RemoteObject _selecteddeck = RemoteObject.createImmutable("");
public static RemoteObject _item_longclick = RemoteObject.createImmutable("");
public static RemoteObject _kvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _lvdecks = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _addbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _addpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _et1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _decknamelabel = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _decksettingpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _renamepanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _newdeckname = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _newsubdeckpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _newsubdecket = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _deleteconfirmation = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.todoactivity _todoactivity = null;
public static b4a.example.login _login = null;
public static b4a.example.register _register = null;
public static b4a.example.tutorialactivity _tutorialactivity = null;
public static b4a.example.leaderboard _leaderboard = null;
public static b4a.example.navactivity _navactivity = null;
public static b4a.example.helpactivity _helpactivity = null;
public static b4a.example.active_recall _active_recall = null;
public static b4a.example.add_card_module _add_card_module = null;
public static b4a.example.add_card_module2 _add_card_module2 = null;
public static b4a.example.add_events_module _add_events_module = null;
public static b4a.example.all_active_recall _all_active_recall = null;
public static b4a.example.calendaractivity _calendaractivity = null;
public static b4a.example.card_module _card_module = null;
public static b4a.example.clockactivity _clockactivity = null;
public static b4a.example.corkactivity _corkactivity = null;
public static b4a.example.day_module _day_module = null;
public static b4a.example.deck_all_cards _deck_all_cards = null;
public static b4a.example.editnote _editnote = null;
public static b4a.example.home_activity _home_activity = null;
public static b4a.example.mainactivity _mainactivity = null;
public static b4a.example.musicactivity _musicactivity = null;
public static b4a.example.musicservice _musicservice = null;
public static b4a.example.noteactivity _noteactivity = null;
public static b4a.example.schedule_module _schedule_module = null;
public static b4a.example.subdeck_module _subdeck_module = null;
public static b4a.example.themeactivity _themeactivity = null;
public static b4a.example.httputils2service _httputils2service = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Activity",flashcardactivity.mostCurrent._activity,"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"Addbtn",flashcardactivity.mostCurrent._addbtn,"addpanel",flashcardactivity.mostCurrent._addpanel,"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"corkActivity",Debug.moduleToString(b4a.example.corkactivity.class),"day_module",Debug.moduleToString(b4a.example.day_module.class),"deck",flashcardactivity._deck,"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"decknamelabel",flashcardactivity.mostCurrent._decknamelabel,"decksettingpanel",flashcardactivity.mostCurrent._decksettingpanel,"deleteconfirmation",flashcardactivity.mostCurrent._deleteconfirmation,"editnote",Debug.moduleToString(b4a.example.editnote.class),"et1",flashcardactivity.mostCurrent._et1,"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"Home_Activity",Debug.moduleToString(b4a.example.home_activity.class),"HttpUtils2Service",Debug.moduleToString(b4a.example.httputils2service.class),"item_longclick",flashcardactivity._item_longclick,"kvs",flashcardactivity._kvs,"Leaderboard",Debug.moduleToString(b4a.example.leaderboard.class),"LogIn",Debug.moduleToString(b4a.example.login.class),"LVdecks",flashcardactivity.mostCurrent._lvdecks,"Main",Debug.moduleToString(b4a.example.main.class),"MainActivity",Debug.moduleToString(b4a.example.mainactivity.class),"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"newdeckname",flashcardactivity.mostCurrent._newdeckname,"newsubdecket",flashcardactivity.mostCurrent._newsubdecket,"newsubdeckpanel",flashcardactivity.mostCurrent._newsubdeckpanel,"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"Register",Debug.moduleToString(b4a.example.register.class),"renamepanel",flashcardactivity.mostCurrent._renamepanel,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"selecteddeck",flashcardactivity._selecteddeck,"Starter",Debug.moduleToString(b4a.example.starter.class),"Subdeck_Module",Debug.moduleToString(b4a.example.subdeck_module.class),"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"todoActivity",Debug.moduleToString(b4a.example.todoactivity.class),"tutorialActivity",Debug.moduleToString(b4a.example.tutorialactivity.class),"xui",flashcardactivity._xui};
}
}