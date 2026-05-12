
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

public class subdeck_module implements IRemote{
	public static subdeck_module mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public subdeck_module() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("subdeck_module"), "b4a.example.subdeck_module");
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
		pcBA = new PCBA(this, subdeck_module.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _selectedsubdeck = RemoteObject.createImmutable("");
public static RemoteObject _addbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _addpanel2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _alldecks = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static RemoteObject _selecteddeck = RemoteObject.createImmutable("");
public static RemoteObject _lvsubdecks = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _decknamelabel = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _addpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _et1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _ar_confirmationpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _confirmlabel = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _number_of_cards = RemoteObject.createImmutable(0);
public static RemoteObject _alterpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _renamepanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _renameet = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _deleteconfirmation = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.mainactivity _mainactivity = null;
public static b4a.example.calendaractivity _calendaractivity = null;
public static b4a.example.clockactivity _clockactivity = null;
public static b4a.example.musicactivity _musicactivity = null;
public static b4a.example.active_recall _active_recall = null;
public static b4a.example.add_card_module _add_card_module = null;
public static b4a.example.add_card_module2 _add_card_module2 = null;
public static b4a.example.add_events_module _add_events_module = null;
public static b4a.example.all_active_recall _all_active_recall = null;
public static b4a.example.card_module _card_module = null;
public static b4a.example.corkactivity _corkactivity = null;
public static b4a.example.day_module _day_module = null;
public static b4a.example.deck_all_cards _deck_all_cards = null;
public static b4a.example.editnote _editnote = null;
public static b4a.example.flashcardactivity _flashcardactivity = null;
public static b4a.example.helpactivity _helpactivity = null;
public static b4a.example.musicservice _musicservice = null;
public static b4a.example.navactivity _navactivity = null;
public static b4a.example.noteactivity _noteactivity = null;
public static b4a.example.schedule_module _schedule_module = null;
public static b4a.example.themeactivity _themeactivity = null;
public static b4a.example.todoactivity _todoactivity = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Activity",subdeck_module.mostCurrent._activity,"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"Addbtn",subdeck_module.mostCurrent._addbtn,"addpanel",subdeck_module.mostCurrent._addpanel,"addpanel2",subdeck_module.mostCurrent._addpanel2,"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"alldecks",subdeck_module.mostCurrent._alldecks,"alterpanel",subdeck_module.mostCurrent._alterpanel,"AR_confirmationpanel",subdeck_module.mostCurrent._ar_confirmationpanel,"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"confirmlabel",subdeck_module.mostCurrent._confirmlabel,"corkActivity",Debug.moduleToString(b4a.example.corkactivity.class),"day_module",Debug.moduleToString(b4a.example.day_module.class),"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"decknamelabel",subdeck_module.mostCurrent._decknamelabel,"deleteconfirmation",subdeck_module.mostCurrent._deleteconfirmation,"editnote",Debug.moduleToString(b4a.example.editnote.class),"et1",subdeck_module.mostCurrent._et1,"FlashcardActivity",Debug.moduleToString(b4a.example.flashcardactivity.class),"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"LVSubdecks",subdeck_module.mostCurrent._lvsubdecks,"Main",Debug.moduleToString(b4a.example.main.class),"MainActivity",Debug.moduleToString(b4a.example.mainactivity.class),"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"number_of_cards",subdeck_module._number_of_cards,"renameet",subdeck_module.mostCurrent._renameet,"renamepanel",subdeck_module.mostCurrent._renamepanel,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"selecteddeck",subdeck_module.mostCurrent._selecteddeck,"selectedsubdeck",subdeck_module._selectedsubdeck,"Starter",Debug.moduleToString(b4a.example.starter.class),"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"todoActivity",Debug.moduleToString(b4a.example.todoactivity.class)};
}
}