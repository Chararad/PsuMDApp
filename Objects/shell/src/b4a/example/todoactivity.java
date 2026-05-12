
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

public class todoactivity implements IRemote{
	public static todoactivity mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public todoactivity() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("todoactivity"), "b4a.example.todoactivity");
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
		pcBA = new PCBA(this, todoactivity.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _kvs = RemoteObject.declareNull("b4a.example3.keyvaluestore");
public static RemoteObject _pixeltf = RemoteObject.declareNull("anywheresoftware.b4a.keywords.constants.TypefaceWrapper");
public static RemoteObject _addtitletextarea = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _listslist = RemoteObject.declareNull("b4a.example3.customlistview");
public static RemoteObject _newlistbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _taskslist = RemoteObject.declareNull("b4a.example3.customlistview");
public static RemoteObject _isaddinglist = RemoteObject.createImmutable(false);
public static RemoteObject _addtaskbtnpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _addtaskbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _currentlist = RemoteObject.createImmutable("");
public static RemoteObject _addtaskpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _addtasktextarea = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _entertaskbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _untitledno = RemoteObject.createImmutable(0);
public static RemoteObject _progressnumber = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _progresspercent = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _progressbar = RemoteObject.declareNull("anywheresoftware.b4a.objects.ProgressBarWrapper");
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
public static b4a.example.subdeck_module _subdeck_module = null;
public static b4a.example.themeactivity _themeactivity = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Activity",todoactivity.mostCurrent._activity,"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"addTaskBtn",todoactivity.mostCurrent._addtaskbtn,"addTaskBtnPNL",todoactivity.mostCurrent._addtaskbtnpnl,"addTaskPanel",todoactivity.mostCurrent._addtaskpanel,"addTaskTextArea",todoactivity.mostCurrent._addtasktextarea,"addTitleTextArea",todoactivity.mostCurrent._addtitletextarea,"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"corkActivity",Debug.moduleToString(b4a.example.corkactivity.class),"currentList",todoactivity.mostCurrent._currentlist,"day_module",Debug.moduleToString(b4a.example.day_module.class),"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"editnote",Debug.moduleToString(b4a.example.editnote.class),"enterTaskBtn",todoactivity.mostCurrent._entertaskbtn,"FlashcardActivity",Debug.moduleToString(b4a.example.flashcardactivity.class),"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"isAddingList",todoactivity._isaddinglist,"kvs",todoactivity._kvs,"listsList",todoactivity.mostCurrent._listslist,"Main",Debug.moduleToString(b4a.example.main.class),"MainActivity",Debug.moduleToString(b4a.example.mainactivity.class),"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"newListBtn",todoactivity.mostCurrent._newlistbtn,"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"pixeltf",todoactivity._pixeltf,"progressBar",todoactivity.mostCurrent._progressbar,"progressNumber",todoactivity.mostCurrent._progressnumber,"progressPercent",todoactivity.mostCurrent._progresspercent,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"Starter",Debug.moduleToString(b4a.example.starter.class),"Subdeck_Module",Debug.moduleToString(b4a.example.subdeck_module.class),"tasksList",todoactivity.mostCurrent._taskslist,"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"untitledNo",todoactivity._untitledno,"xui",todoactivity._xui};
}
}