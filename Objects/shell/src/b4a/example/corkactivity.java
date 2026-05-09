
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

public class corkactivity implements IRemote{
	public static corkactivity mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public corkactivity() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("corkactivity"), "b4a.example.corkactivity");
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
		pcBA = new PCBA(this, corkactivity.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _imgpicker = RemoteObject.declareNull("anywheresoftware.b4a.phone.Phone.ContentChooser");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _boardpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _addpnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _canvabtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _imgbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _imgview = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _stickybtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _notepnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
public static RemoteObject _canvaspnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
public static RemoteObject _lastx = RemoteObject.createImmutable(0f);
public static RemoteObject _lasty = RemoteObject.createImmutable(0f);
public static RemoteObject _place1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place11 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _place12 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _deletelbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _r = RemoteObject.createImmutable(0);
public static RemoteObject _g = RemoteObject.createImmutable(0);
public static RemoteObject _b = RemoteObject.createImmutable(0);
public static RemoteObject _r2 = RemoteObject.createImmutable(0);
public static RemoteObject _g2 = RemoteObject.createImmutable(0);
public static RemoteObject _b2 = RemoteObject.createImmutable(0);
public static RemoteObject _penspnr = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
public static RemoteObject _sizespnr = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
public static RemoteObject _width = RemoteObject.createImmutable(0);
public static RemoteObject _height = RemoteObject.createImmutable(0);
public static RemoteObject _ddn = RemoteObject.declareNull("b4a.example.dragdropview");
public static RemoteObject _ddi = RemoteObject.declareNull("b4a.example.dragdropview");
public static RemoteObject _ddc = RemoteObject.declareNull("b4a.example.dragdropview");
public static RemoteObject _notecount = RemoteObject.createImmutable(0);
public static RemoteObject _imgcount = RemoteObject.createImmutable(0);
public static RemoteObject _canvascount = RemoteObject.createImmutable(0);
public static RemoteObject _isloading = RemoteObject.createImmutable(false);
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.mainactivity _mainactivity = null;
public static b4a.example.active_recall _active_recall = null;
public static b4a.example.add_card_module _add_card_module = null;
public static b4a.example.add_card_module2 _add_card_module2 = null;
public static b4a.example.add_events_module _add_events_module = null;
public static b4a.example.all_active_recall _all_active_recall = null;
public static b4a.example.calendaractivity _calendaractivity = null;
public static b4a.example.card_module _card_module = null;
public static b4a.example.clockactivity _clockactivity = null;
public static b4a.example.day_module _day_module = null;
public static b4a.example.deck_all_cards _deck_all_cards = null;
public static b4a.example.editnote _editnote = null;
public static b4a.example.flashcardactivity _flashcardactivity = null;
public static b4a.example.helpactivity _helpactivity = null;
public static b4a.example.musicactivity _musicactivity = null;
public static b4a.example.musicservice _musicservice = null;
public static b4a.example.navactivity _navactivity = null;
public static b4a.example.noteactivity _noteactivity = null;
public static b4a.example.schedule_module _schedule_module = null;
public static b4a.example.subdeck_module _subdeck_module = null;
public static b4a.example.themeactivity _themeactivity = null;
public static b4a.example.todoactivity _todoactivity = null;
  public Object[] GetGlobals() {
		return new Object[] {"active_recall",Debug.moduleToString(b4a.example.active_recall.class),"Activity",corkactivity.mostCurrent._activity,"Add_card_module",Debug.moduleToString(b4a.example.add_card_module.class),"add_card_module2",Debug.moduleToString(b4a.example.add_card_module2.class),"add_events_module",Debug.moduleToString(b4a.example.add_events_module.class),"addPnl",corkactivity.mostCurrent._addpnl,"all_active_recall",Debug.moduleToString(b4a.example.all_active_recall.class),"B",corkactivity._b,"B2",corkactivity._b2,"boardPnl",corkactivity.mostCurrent._boardpnl,"CalendarActivity",Debug.moduleToString(b4a.example.calendaractivity.class),"canvaBtn",corkactivity.mostCurrent._canvabtn,"canvasCount",corkactivity._canvascount,"canvasPnl",corkactivity.mostCurrent._canvaspnl,"Card_Module",Debug.moduleToString(b4a.example.card_module.class),"clockActivity",Debug.moduleToString(b4a.example.clockactivity.class),"day_module",Debug.moduleToString(b4a.example.day_module.class),"ddc",corkactivity.mostCurrent._ddc,"ddi",corkactivity.mostCurrent._ddi,"ddn",corkactivity.mostCurrent._ddn,"deck_all_cards",Debug.moduleToString(b4a.example.deck_all_cards.class),"deleteLbl",corkactivity.mostCurrent._deletelbl,"editnote",Debug.moduleToString(b4a.example.editnote.class),"FlashcardActivity",Debug.moduleToString(b4a.example.flashcardactivity.class),"G",corkactivity._g,"G2",corkactivity._g2,"Height",corkactivity._height,"helpActivity",Debug.moduleToString(b4a.example.helpactivity.class),"imgBtn",corkactivity.mostCurrent._imgbtn,"imgCount",corkactivity._imgcount,"imgPicker",corkactivity._imgpicker,"imgView",corkactivity.mostCurrent._imgview,"isLoading",corkactivity._isloading,"LastX",corkactivity._lastx,"LastY",corkactivity._lasty,"Main",Debug.moduleToString(b4a.example.main.class),"MainActivity",Debug.moduleToString(b4a.example.mainactivity.class),"musicActivity",Debug.moduleToString(b4a.example.musicactivity.class),"musicService",Debug.moduleToString(b4a.example.musicservice.class),"navActivity",Debug.moduleToString(b4a.example.navactivity.class),"noteActivity",Debug.moduleToString(b4a.example.noteactivity.class),"noteCount",corkactivity._notecount,"notePnl",corkactivity.mostCurrent._notepnl,"penSpnr",corkactivity.mostCurrent._penspnr,"place1",corkactivity.mostCurrent._place1,"place10",corkactivity.mostCurrent._place10,"place11",corkactivity.mostCurrent._place11,"place12",corkactivity.mostCurrent._place12,"place2",corkactivity.mostCurrent._place2,"place3",corkactivity.mostCurrent._place3,"place4",corkactivity.mostCurrent._place4,"place5",corkactivity.mostCurrent._place5,"place6",corkactivity.mostCurrent._place6,"place7",corkactivity.mostCurrent._place7,"place8",corkactivity.mostCurrent._place8,"place9",corkactivity.mostCurrent._place9,"R",corkactivity._r,"R2",corkactivity._r2,"Schedule_module",Debug.moduleToString(b4a.example.schedule_module.class),"sizeSpnr",corkactivity.mostCurrent._sizespnr,"Starter",Debug.moduleToString(b4a.example.starter.class),"stickyBtn",corkactivity.mostCurrent._stickybtn,"Subdeck_Module",Debug.moduleToString(b4a.example.subdeck_module.class),"themeActivity",Debug.moduleToString(b4a.example.themeactivity.class),"todoActivity",Debug.moduleToString(b4a.example.todoactivity.class),"Width",corkactivity._width,"xui",corkactivity._xui};
}
}