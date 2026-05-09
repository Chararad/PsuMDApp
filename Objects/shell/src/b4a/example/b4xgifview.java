
package b4a.example;

import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RemoteObject;

public class b4xgifview {
    public static RemoteObject myClass;
	public b4xgifview() {
	}
    public static PCBA staticBA = new PCBA(null, b4xgifview.class);

public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _meventname = RemoteObject.createImmutable("");
public static RemoteObject _mcallback = RemoteObject.declareNull("Object");
public static RemoteObject _mbase = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _tag = RemoteObject.declareNull("Object");
public static RemoteObject _iv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _gifdrawable = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
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
public static b4a.example.corkactivity _corkactivity = null;
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
public static Object[] GetGlobals(RemoteObject _ref) throws Exception {
		return new Object[] {"GifDrawable",_ref.getField(false, "_gifdrawable"),"iv",_ref.getField(false, "_iv"),"mBase",_ref.getField(false, "_mbase"),"mCallBack",_ref.getField(false, "_mcallback"),"mEventName",_ref.getField(false, "_meventname"),"Tag",_ref.getField(false, "_tag"),"xui",_ref.getField(false, "_xui")};
}
}