
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

public class day_module implements IRemote{
	public static day_module mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public day_module() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("day_module"), "b4a.example.day_module");
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
		pcBA = new PCBA(this, day_module.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _currentdate = RemoteObject.createImmutable("");
public static RemoteObject _addeventsfeedback = RemoteObject.createImmutable(false);
public static RemoteObject _currentindex = RemoteObject.createImmutable(0);
public static RemoteObject _schedules = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static RemoteObject _day_btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _menupanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _svtimeline = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _date_todaylbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _addpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _addtask_btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _svevents = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _eventdescription_lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _eventtitle_lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _tags_lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _datetoday_lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _eventinfo_panel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _editinfopanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _currenttaggedevent = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static RemoteObject _timeindex = RemoteObject.createImmutable(0);
public static RemoteObject _eventtype = RemoteObject.createImmutable("");
public static RemoteObject _currentevid = RemoteObject.createImmutable(0L);
public static RemoteObject _edittitle_et = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _editdescription_et = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _deletepanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _addtl_et = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _addeventtl_panel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _timelabel = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _deletetlevent_confirmationpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _starttimelinesp = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
public static RemoteObject _endtimelinesp = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
public static RemoteObject _taskrb = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
public static RemoteObject _eventrb = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
public static RemoteObject _birthdayrb = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
public static RemoteObject _ooorb = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
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
public static b4a.example.deck_all_cards _deck_all_cards = null;
public static b4a.example.editnote _editnote = null;
public static b4a.example.flashcardactivity _flashcardactivity = null;
public static b4a.example.helpactivity _helpactivity = null;
public static b4a.example.musicservice _musicservice = null;
public static b4a.example.navactivity _navactivity = null;
public static b4a.example.noteactivity _noteactivity = null;
public static b4a.example.schedule_module _schedule_module = null;
public static b4a.example.subdeck_module _subdeck_module = null;
public static b4a.example.themeactivity _themeactivity = null;
public static b4a.example.todoactivity _todoactivity = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Activity",day_module.mostCurrent._activity,"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"addeventsfeedback",day_module._addeventsfeedback,"addEventTL_panel",day_module.mostCurrent._addeventtl_panel,"addpanel",day_module.mostCurrent._addpanel,"Addtask_btn",day_module.mostCurrent._addtask_btn,"addTL_et",day_module.mostCurrent._addtl_et,"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"birthdayrb",day_module.mostCurrent._birthdayrb,"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"corkActivity",Debug.moduleToString(b4a.example.corkactivity.class),"currentDate",day_module._currentdate,"currentevId",day_module._currentevid,"currentIndex",day_module._currentindex,"currenttaggedEvent",day_module.mostCurrent._currenttaggedevent,"date_todaylbl",day_module.mostCurrent._date_todaylbl,"dateToday_lbl",day_module.mostCurrent._datetoday_lbl,"Day_btn",day_module.mostCurrent._day_btn,"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"deletepanel",day_module.mostCurrent._deletepanel,"deleteTLevent_confirmationpanel",day_module.mostCurrent._deletetlevent_confirmationpanel,"editDescription_et",day_module.mostCurrent._editdescription_et,"EditInfoPanel",day_module.mostCurrent._editinfopanel,"editnote",Debug.moduleToString(b4a.example.editnote.class),"editTitle_et",day_module.mostCurrent._edittitle_et,"endtimelineSP",day_module.mostCurrent._endtimelinesp,"eventdescription_lbl",day_module.mostCurrent._eventdescription_lbl,"eventInfo_panel",day_module.mostCurrent._eventinfo_panel,"eventrb",day_module.mostCurrent._eventrb,"eventTitle_lbl",day_module.mostCurrent._eventtitle_lbl,"eventtype",day_module.mostCurrent._eventtype,"FlashcardActivity",Debug.moduleToString(b4a.example.flashcardactivity.class),"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"Main",Debug.moduleToString(b4a.example.main.class),"MainActivity",Debug.moduleToString(b4a.example.mainactivity.class),"menupanel",day_module.mostCurrent._menupanel,"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"ooorb",day_module.mostCurrent._ooorb,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"schedules",day_module._schedules,"Starter",Debug.moduleToString(b4a.example.starter.class),"starttimelineSP",day_module.mostCurrent._starttimelinesp,"Subdeck_Module",Debug.moduleToString(b4a.example.subdeck_module.class),"svEvents",day_module.mostCurrent._svevents,"Svtimeline",day_module.mostCurrent._svtimeline,"tags_lbl",day_module.mostCurrent._tags_lbl,"taskrb",day_module.mostCurrent._taskrb,"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"timeIndex",day_module._timeindex,"timelabel",day_module.mostCurrent._timelabel,"todoActivity",Debug.moduleToString(b4a.example.todoactivity.class)};
}
}