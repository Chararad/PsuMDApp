
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

public class starter implements IRemote{
	public static starter mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public starter() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
public boolean isSingleton() {
		return true;
	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("starter"), "b4a.example.starter");
	}
     public static RemoteObject getObject() {
		return myClass;
	 }
	public RemoteObject _service;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
        _service = (RemoteObject) args[2];
        remoteMe = RemoteObject.declareNull("b4a.example.starter");
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[3];
		pcBA = new PCBA(this, starter.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}public static RemoteObject runMethod(boolean notUsed, String method, Object... args) throws Exception{
		return (RemoteObject) mostCurrent.pcBA.raiseEvent(method.substring(1), args);
	}
    public static void runVoidMethod(String method, Object... args) throws Exception{
		runMethod(false, method, args);
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _darkmode = RemoteObject.createImmutable(false);
public static RemoteObject _themenumber = RemoteObject.createImmutable(0);
public static RemoteObject _noteskvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _prefkvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _calkvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _calendarmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static RemoteObject _deckkvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _taskkvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _deck = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static RemoteObject _finishedinit = RemoteObject.createImmutable(false);
public static RemoteObject _themechanged = RemoteObject.createImmutable(false);
public static b4a.example.main _main = null;
public static b4a.example.mainactivity _mainactivity = null;
public static b4a.example.todoactivity _todoactivity = null;
public static b4a.example.calendaractivity _calendaractivity = null;
public static b4a.example.schedule_module _schedule_module = null;
public static b4a.example.day_module _day_module = null;
public static b4a.example.flashcardactivity _flashcardactivity = null;
public static b4a.example.corkactivity _corkactivity = null;
public static b4a.example.subdeck_module _subdeck_module = null;
public static b4a.example.add_card_module _add_card_module = null;
public static b4a.example.noteactivity _noteactivity = null;
public static b4a.example.editnote _editnote = null;
public static b4a.example.active_recall _active_recall = null;
public static b4a.example.add_card_module2 _add_card_module2 = null;
public static b4a.example.add_events_module _add_events_module = null;
public static b4a.example.all_active_recall _all_active_recall = null;
public static b4a.example.clockactivity _clockactivity = null;
public static b4a.example.deck_all_cards _deck_all_cards = null;
public static b4a.example.helpactivity _helpactivity = null;
public static b4a.example.musicactivity _musicactivity = null;
public static b4a.example.musicservice _musicservice = null;
public static b4a.example.navactivity _navactivity = null;
public static b4a.example.themeactivity _themeactivity = null;
public static b4a.example.card_module _card_module = null;
public static b4a.example.httputils2service _httputils2service = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"calendarMap",starter._calendarmap,"calKvs",starter._calkvs,"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"corkActivity",Debug.moduleToString(b4a.example.corkactivity.class),"darkMode",starter._darkmode,"day_module",Debug.moduleToString(b4a.example.day_module.class),"deck",starter._deck,"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"deckKvs",starter._deckkvs,"editnote",Debug.moduleToString(b4a.example.editnote.class),"finishedInit",starter._finishedinit,"FlashcardActivity",Debug.moduleToString(b4a.example.flashcardactivity.class),"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"HttpUtils2Service",Debug.moduleToString(b4a.example.httputils2service.class),"Main",Debug.moduleToString(b4a.example.main.class),"MainActivity",Debug.moduleToString(b4a.example.mainactivity.class),"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"notesKvs",starter._noteskvs,"prefKvs",starter._prefkvs,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"Service",starter.mostCurrent._service,"Subdeck_Module",Debug.moduleToString(b4a.example.subdeck_module.class),"taskKvs",starter._taskkvs,"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"themeChanged",starter._themechanged,"themeNumber",starter._themenumber,"todoActivity",Debug.moduleToString(b4a.example.todoactivity.class)};
}
}