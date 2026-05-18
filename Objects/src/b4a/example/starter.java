package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "b4a.example", "b4a.example.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			if (ServiceHelper.StarterHelper.runWaitForLayouts() == false) {
                BA.LogInfo("stopping spontaneous created service");
                stopSelf();
            }
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }

	public void onTimeout(int startId) {
        BA.LogInfo("** Service (starter) Timeout **");
        anywheresoftware.b4a.objects.collections.Map params = new anywheresoftware.b4a.objects.collections.Map();
        params.Initialize();
        params.Put("StartId", startId);
        processBA.raiseEvent(null, "service_timeout", params);
            
    }
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean _darkmode = false;
public static int _themenumber = 0;
public static b4a.example3.keyvaluestore _noteskvs = null;
public static b4a.example3.keyvaluestore _prefkvs = null;
public static b4a.example3.keyvaluestore _calkvs = null;
public static anywheresoftware.b4a.objects.collections.Map _calendarmap = null;
public static b4a.example3.keyvaluestore _deckkvs = null;
public static b4a.example3.keyvaluestore _taskkvs = null;
public static anywheresoftware.b4a.objects.collections.Map _deck = null;
public static boolean _finishedinit = false;
public static boolean _themechanged = false;
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public b4a.example.main _main = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.day_module _day_module = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.musicservice _musicservice = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.card_module _card_module = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.editnote _editnote = null;
public b4a.example.httputils2service _httputils2service = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 73;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return false;
}
public static String  _copytracksifneeded() throws Exception{
anywheresoftware.b4a.objects.collections.List _playlist = null;
int _i = 0;
String _trackname = "";
String _filename = "";
 //BA.debugLineNum = 80;BA.debugLine="Sub CopyTracksIfNeeded";
 //BA.debugLineNum = 81;BA.debugLine="Dim playlist As List";
_playlist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 82;BA.debugLine="playlist.Initialize";
_playlist.Initialize();
 //BA.debugLineNum = 83;BA.debugLine="playlist.Add(\"tracks/intro.mp3\")";
_playlist.Add((Object)("tracks/intro.mp3"));
 //BA.debugLineNum = 84;BA.debugLine="playlist.Add(\"tracks/taiyaki.mp3\")";
_playlist.Add((Object)("tracks/taiyaki.mp3"));
 //BA.debugLineNum = 85;BA.debugLine="playlist.Add(\"tracks/feel special.mp3\")";
_playlist.Add((Object)("tracks/feel special.mp3"));
 //BA.debugLineNum = 86;BA.debugLine="playlist.Add(\"tracks/union.mp3\")";
_playlist.Add((Object)("tracks/union.mp3"));
 //BA.debugLineNum = 87;BA.debugLine="playlist.Add(\"tracks/two in the morning.mp3\")";
_playlist.Add((Object)("tracks/two in the morning.mp3"));
 //BA.debugLineNum = 88;BA.debugLine="playlist.Add(\"tracks/happily ever after.mp3\")";
_playlist.Add((Object)("tracks/happily ever after.mp3"));
 //BA.debugLineNum = 89;BA.debugLine="playlist.Add(\"tracks/cookie.mp3\")";
_playlist.Add((Object)("tracks/cookie.mp3"));
 //BA.debugLineNum = 90;BA.debugLine="playlist.Add(\"tracks/comfy vibes.mp3\")";
_playlist.Add((Object)("tracks/comfy vibes.mp3"));
 //BA.debugLineNum = 91;BA.debugLine="playlist.Add(\"tracks/dango.mp3\")";
_playlist.Add((Object)("tracks/dango.mp3"));
 //BA.debugLineNum = 92;BA.debugLine="playlist.Add(\"tracks/iced caramel macchiato.mp3\")";
_playlist.Add((Object)("tracks/iced caramel macchiato.mp3"));
 //BA.debugLineNum = 93;BA.debugLine="playlist.Add(\"tracks/in dreamland.mp3\")";
_playlist.Add((Object)("tracks/in dreamland.mp3"));
 //BA.debugLineNum = 94;BA.debugLine="playlist.Add(\"tracks/space aquarium.mp3\")";
_playlist.Add((Object)("tracks/space aquarium.mp3"));
 //BA.debugLineNum = 95;BA.debugLine="playlist.Add(\"tracks/sunshine & butterflies.mp3\")";
_playlist.Add((Object)("tracks/sunshine & butterflies.mp3"));
 //BA.debugLineNum = 96;BA.debugLine="playlist.Add(\"tracks/soda pop.mp3\")";
_playlist.Add((Object)("tracks/soda pop.mp3"));
 //BA.debugLineNum = 97;BA.debugLine="playlist.Add(\"tracks/matcha latte.mp3\")";
_playlist.Add((Object)("tracks/matcha latte.mp3"));
 //BA.debugLineNum = 98;BA.debugLine="playlist.Add(\"tracks/midnight.mp3\")";
_playlist.Add((Object)("tracks/midnight.mp3"));
 //BA.debugLineNum = 99;BA.debugLine="playlist.Add(\"tracks/rose water.mp3\")";
_playlist.Add((Object)("tracks/rose water.mp3"));
 //BA.debugLineNum = 100;BA.debugLine="playlist.Add(\"tracks/hot.mp3\")";
_playlist.Add((Object)("tracks/hot.mp3"));
 //BA.debugLineNum = 101;BA.debugLine="playlist.Add(\"tracks/on the top.mp3\")";
_playlist.Add((Object)("tracks/on the top.mp3"));
 //BA.debugLineNum = 103;BA.debugLine="If File.Exists(File.DirInternal, \"tracks\") = Fals";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"tracks")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 104;BA.debugLine="File.MakeDir(File.DirInternal, \"tracks\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"tracks");
 };
 //BA.debugLineNum = 106;BA.debugLine="For i = 0 To playlist.Size - 1";
{
final int step25 = 1;
final int limit25 = (int) (_playlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit25 ;_i = _i + step25 ) {
 //BA.debugLineNum = 107;BA.debugLine="Dim trackName As String = playlist.Get(i)";
_trackname = BA.ObjectToString(_playlist.Get(_i));
 //BA.debugLineNum = 108;BA.debugLine="Dim fileName As String = trackName.SubString(tra";
_filename = _trackname.substring((int) (_trackname.lastIndexOf("/")+1));
 //BA.debugLineNum = 109;BA.debugLine="If File.Exists(File.DirInternal & \"/tracks\", fil";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/tracks",_filename)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 110;BA.debugLine="File.Copy(File.DirAssets, trackName, File.DirIn";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_trackname,anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/tracks",_filename);
 };
 }
};
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public darkMode As Boolean = False";
_darkmode = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 10;BA.debugLine="Public themeNumber As Int = 0";
_themenumber = (int) (0);
 //BA.debugLineNum = 11;BA.debugLine="Public notesKvs As KeyValueStore";
_noteskvs = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 12;BA.debugLine="Public prefKvs As KeyValueStore";
_prefkvs = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 13;BA.debugLine="Public calKvs As KeyValueStore";
_calkvs = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 14;BA.debugLine="Public calendarMap As Map";
_calendarmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 15;BA.debugLine="Public deckKvs As KeyValueStore";
_deckkvs = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 16;BA.debugLine="Public taskKvs As KeyValueStore";
_taskkvs = new b4a.example3.keyvaluestore();
 //BA.debugLineNum = 17;BA.debugLine="Public deck As Map";
_deck = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 18;BA.debugLine="Public finishedInit As Boolean = False";
_finishedinit = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 19;BA.debugLine="Public themeChanged As Boolean = False";
_themechanged = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 20;BA.debugLine="Public SQL1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 24;BA.debugLine="prefKvs.Initialize(File.DirInternal, \"prefData\")";
_prefkvs._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"prefData");
 //BA.debugLineNum = 26;BA.debugLine="If prefKvs.ContainsKey(\"darkMode\") Then";
if (_prefkvs._containskey("darkMode")) { 
 //BA.debugLineNum = 27;BA.debugLine="darkMode = prefKvs.Get(\"darkMode\")";
_darkmode = BA.ObjectToBoolean(_prefkvs._get("darkMode"));
 };
 //BA.debugLineNum = 30;BA.debugLine="If prefKvs.ContainsKey(\"themeNumber\") Then";
if (_prefkvs._containskey("themeNumber")) { 
 //BA.debugLineNum = 31;BA.debugLine="themeNumber = prefKvs.Get(\"themeNumber\")";
_themenumber = (int)(BA.ObjectToNumber(_prefkvs._get("themeNumber")));
 };
 //BA.debugLineNum = 34;BA.debugLine="calKvs.Initialize(File.DirInternal, \"mydata\")";
_calkvs._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"mydata");
 //BA.debugLineNum = 35;BA.debugLine="If calKvs.ContainsKey(\"CalendarKVS\") Then";
if (_calkvs._containskey("CalendarKVS")) { 
 //BA.debugLineNum = 36;BA.debugLine="calendarMap = calKvs.Get(\"CalendarKVS\")";
_calendarmap = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_calkvs._get("CalendarKVS")));
 }else {
 //BA.debugLineNum = 38;BA.debugLine="calendarMap.Initialize";
_calendarmap.Initialize();
 };
 //BA.debugLineNum = 41;BA.debugLine="deckKvs.Initialize(File.DirInternal, \"mydata\")";
_deckkvs._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"mydata");
 //BA.debugLineNum = 42;BA.debugLine="If deckKvs.ContainsKey(\"deck_data\") Then";
if (_deckkvs._containskey("deck_data")) { 
 //BA.debugLineNum = 43;BA.debugLine="deck = deckKvs.Get(\"deck_data\")";
_deck = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_deckkvs._get("deck_data")));
 }else {
 //BA.debugLineNum = 45;BA.debugLine="deck.Initialize";
_deck.Initialize();
 };
 //BA.debugLineNum = 48;BA.debugLine="taskKvs.Initialize(File.DirInternal, \"todoListDat";
_taskkvs._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"todoListData");
 //BA.debugLineNum = 49;BA.debugLine="notesKvs.Initialize(File.DirInternal, \"notes_data";
_noteskvs._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"notes_data");
 //BA.debugLineNum = 51;BA.debugLine="CopyTracksIfNeeded";
_copytracksifneeded();
 //BA.debugLineNum = 53;BA.debugLine="finishedInit = True";
_finishedinit = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 55;BA.debugLine="SQL1.Initialize(File.DirInternal, \"users.db\", Tru";
_sql1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"users.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 56;BA.debugLine="SQL1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS use";
_sql1.ExecNonQuery("CREATE TABLE IF NOT EXISTS users ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"email TEXT UNIQUE, "+"name TEXT, "+"password TEXT)");
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 64;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
}
