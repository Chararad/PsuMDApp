package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class musicservice extends android.app.Service{
	public static class musicservice_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (musicservice) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, musicservice.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, anywheresoftware.b4a.ShellBA.class);
		}

	}
    static musicservice mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return musicservice.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new anywheresoftware.b4a.ShellBA(this, null, null, "b4a.example", "b4a.example.musicservice");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.musicservice", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (musicservice) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
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
                    BA.LogInfo("** Service (musicservice) Create **");
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
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (musicservice) Start **");
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
        BA.LogInfo("** Service (musicservice) Timeout **");
        anywheresoftware.b4a.objects.collections.Map params = new anywheresoftware.b4a.objects.collections.Map();
        params.Initialize();
        params.Put("StartId", startId);
        processBA.raiseEvent(null, "service_timeout", params);
            
    }
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (false) {
            BA.LogInfo("** Service (musicservice) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (musicservice) Destroy **");
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
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.MediaPlayerWrapper _mediaplayer = null;
public static anywheresoftware.b4a.objects.collections.List _musicplaylist = null;
public static int _currentsong = 0;
public static anywheresoftware.b4a.objects.Timer _songtimer = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.todoactivity _todoactivity = null;
public b4a.example.login _login = null;
public b4a.example.register _register = null;
public b4a.example.tutorialactivity _tutorialactivity = null;
public b4a.example.leaderboard _leaderboard = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.active_recall _active_recall = null;
public b4a.example.add_card_module _add_card_module = null;
public b4a.example.add_card_module2 _add_card_module2 = null;
public b4a.example.add_events_module _add_events_module = null;
public b4a.example.all_active_recall _all_active_recall = null;
public b4a.example.calendaractivity _calendaractivity = null;
public b4a.example.card_module _card_module = null;
public b4a.example.clockactivity _clockactivity = null;
public b4a.example.corkactivity _corkactivity = null;
public b4a.example.day_module _day_module = null;
public b4a.example.deck_all_cards _deck_all_cards = null;
public b4a.example.editnote _editnote = null;
public b4a.example.flashcardactivity _flashcardactivity = null;
public b4a.example.home_activity _home_activity = null;
public b4a.example.mainactivity _mainactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _setsong(int _index) throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "setsong", false))
	 {return ((String) Debug.delegate(processBA, "setsong", new Object[] {_index}));}
RDebugUtils.currentLine=30998528;
 //BA.debugLineNum = 30998528;BA.debugLine="Sub setSong(index As Int)";
RDebugUtils.currentLine=30998529;
 //BA.debugLineNum = 30998529;BA.debugLine="currentSong = index";
_currentsong = _index;
RDebugUtils.currentLine=30998530;
 //BA.debugLineNum = 30998530;BA.debugLine="playSong";
_playsong();
RDebugUtils.currentLine=30998531;
 //BA.debugLineNum = 30998531;BA.debugLine="End Sub";
return "";
}
public static String  _nextsong() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "nextsong", false))
	 {return ((String) Debug.delegate(processBA, "nextsong", null));}
RDebugUtils.currentLine=31195136;
 //BA.debugLineNum = 31195136;BA.debugLine="Sub nextSong";
RDebugUtils.currentLine=31195137;
 //BA.debugLineNum = 31195137;BA.debugLine="currentSong = currentSong + 1";
_currentsong = (int) (_currentsong+1);
RDebugUtils.currentLine=31195138;
 //BA.debugLineNum = 31195138;BA.debugLine="If currentSong >= musicPlaylist.Size Then";
if (_currentsong>=_musicplaylist.getSize()) { 
RDebugUtils.currentLine=31195139;
 //BA.debugLineNum = 31195139;BA.debugLine="currentSong = 0";
_currentsong = (int) (0);
 };
RDebugUtils.currentLine=31195141;
 //BA.debugLineNum = 31195141;BA.debugLine="playSong";
_playsong();
RDebugUtils.currentLine=31195142;
 //BA.debugLineNum = 31195142;BA.debugLine="End Sub";
return "";
}
public static String  _pausetoggle() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "pausetoggle", false))
	 {return ((String) Debug.delegate(processBA, "pausetoggle", null));}
RDebugUtils.currentLine=30932992;
 //BA.debugLineNum = 30932992;BA.debugLine="Sub pauseToggle";
RDebugUtils.currentLine=30932993;
 //BA.debugLineNum = 30932993;BA.debugLine="If mediaPlayer.IsPlaying Then";
if (_mediaplayer.IsPlaying()) { 
RDebugUtils.currentLine=30932994;
 //BA.debugLineNum = 30932994;BA.debugLine="mediaPlayer.Pause";
_mediaplayer.Pause();
 }else {
RDebugUtils.currentLine=30932996;
 //BA.debugLineNum = 30932996;BA.debugLine="mediaPlayer.Play";
_mediaplayer.Play();
 };
RDebugUtils.currentLine=30932998;
 //BA.debugLineNum = 30932998;BA.debugLine="End Sub";
return "";
}
public static String  _prevsong() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "prevsong", false))
	 {return ((String) Debug.delegate(processBA, "prevsong", null));}
RDebugUtils.currentLine=31260672;
 //BA.debugLineNum = 31260672;BA.debugLine="Sub prevSong";
RDebugUtils.currentLine=31260673;
 //BA.debugLineNum = 31260673;BA.debugLine="currentSong = currentSong - 1";
_currentsong = (int) (_currentsong-1);
RDebugUtils.currentLine=31260674;
 //BA.debugLineNum = 31260674;BA.debugLine="If currentSong < 0 Then";
if (_currentsong<0) { 
RDebugUtils.currentLine=31260675;
 //BA.debugLineNum = 31260675;BA.debugLine="currentSong = musicPlaylist.Size - 1";
_currentsong = (int) (_musicplaylist.getSize()-1);
 };
RDebugUtils.currentLine=31260677;
 //BA.debugLineNum = 31260677;BA.debugLine="playSong";
_playsong();
RDebugUtils.currentLine=31260678;
 //BA.debugLineNum = 31260678;BA.debugLine="End Sub";
return "";
}
public static String  _playsong() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "playsong", false))
	 {return ((String) Debug.delegate(processBA, "playsong", null));}
String _trackpath = "";
String _filename = "";
String _uploaddir = "";
String _uploadfile = "";
RDebugUtils.currentLine=31064064;
 //BA.debugLineNum = 31064064;BA.debugLine="Sub playSong";
RDebugUtils.currentLine=31064065;
 //BA.debugLineNum = 31064065;BA.debugLine="If mediaPlayer.IsInitialized Then";
if (_mediaplayer.IsInitialized()) { 
RDebugUtils.currentLine=31064066;
 //BA.debugLineNum = 31064066;BA.debugLine="mediaPlayer.Stop";
_mediaplayer.Stop();
 };
RDebugUtils.currentLine=31064069;
 //BA.debugLineNum = 31064069;BA.debugLine="Dim trackPath As String";
_trackpath = "";
RDebugUtils.currentLine=31064070;
 //BA.debugLineNum = 31064070;BA.debugLine="trackPath = musicPlaylist.Get(currentSong)";
_trackpath = BA.ObjectToString(_musicplaylist.Get(_currentsong));
RDebugUtils.currentLine=31064072;
 //BA.debugLineNum = 31064072;BA.debugLine="If trackPath.StartsWith(\"tracks/\") Then";
if (_trackpath.startsWith("tracks/")) { 
RDebugUtils.currentLine=31064074;
 //BA.debugLineNum = 31064074;BA.debugLine="Dim fileName As String";
_filename = "";
RDebugUtils.currentLine=31064075;
 //BA.debugLineNum = 31064075;BA.debugLine="fileName = trackPath.SubString(trackPath.LastInd";
_filename = _trackpath.substring((int) (_trackpath.lastIndexOf("/")+1));
RDebugUtils.currentLine=31064076;
 //BA.debugLineNum = 31064076;BA.debugLine="mediaPlayer.Load(File.DirInternal & \"/tracks\", f";
_mediaplayer.Load(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/tracks",_filename);
 }else {
RDebugUtils.currentLine=31064080;
 //BA.debugLineNum = 31064080;BA.debugLine="Dim uploadDir As String";
_uploaddir = "";
RDebugUtils.currentLine=31064081;
 //BA.debugLineNum = 31064081;BA.debugLine="Dim uploadFile As String";
_uploadfile = "";
RDebugUtils.currentLine=31064082;
 //BA.debugLineNum = 31064082;BA.debugLine="uploadDir = trackPath.SubString2(0, trackPath.La";
_uploaddir = _trackpath.substring((int) (0),_trackpath.lastIndexOf("/"));
RDebugUtils.currentLine=31064083;
 //BA.debugLineNum = 31064083;BA.debugLine="uploadFile = trackPath.SubString(trackPath.LastI";
_uploadfile = _trackpath.substring((int) (_trackpath.lastIndexOf("/")+1));
RDebugUtils.currentLine=31064084;
 //BA.debugLineNum = 31064084;BA.debugLine="mediaPlayer.Load(uploadDir, uploadFile)";
_mediaplayer.Load(_uploaddir,_uploadfile);
 };
RDebugUtils.currentLine=31064087;
 //BA.debugLineNum = 31064087;BA.debugLine="mediaPlayer.Play";
_mediaplayer.Play();
RDebugUtils.currentLine=31064088;
 //BA.debugLineNum = 31064088;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "service_create", false))
	 {return ((String) Debug.delegate(processBA, "service_create", null));}
RDebugUtils.currentLine=30736384;
 //BA.debugLineNum = 30736384;BA.debugLine="Sub Service_Create";
RDebugUtils.currentLine=30736385;
 //BA.debugLineNum = 30736385;BA.debugLine="mediaPlayer.Initialize";
_mediaplayer.Initialize();
RDebugUtils.currentLine=30736386;
 //BA.debugLineNum = 30736386;BA.debugLine="musicPlaylist.Initialize";
_musicplaylist.Initialize();
RDebugUtils.currentLine=30736388;
 //BA.debugLineNum = 30736388;BA.debugLine="musicPlaylist.Add(\"tracks/intro.mp3\")";
_musicplaylist.Add((Object)("tracks/intro.mp3"));
RDebugUtils.currentLine=30736389;
 //BA.debugLineNum = 30736389;BA.debugLine="musicPlaylist.Add(\"tracks/taiyaki.mp3\")";
_musicplaylist.Add((Object)("tracks/taiyaki.mp3"));
RDebugUtils.currentLine=30736390;
 //BA.debugLineNum = 30736390;BA.debugLine="musicPlaylist.Add(\"tracks/feel special.mp3\")";
_musicplaylist.Add((Object)("tracks/feel special.mp3"));
RDebugUtils.currentLine=30736391;
 //BA.debugLineNum = 30736391;BA.debugLine="musicPlaylist.Add(\"tracks/union.mp3\")";
_musicplaylist.Add((Object)("tracks/union.mp3"));
RDebugUtils.currentLine=30736392;
 //BA.debugLineNum = 30736392;BA.debugLine="musicPlaylist.Add(\"tracks/two in the morning.mp3\"";
_musicplaylist.Add((Object)("tracks/two in the morning.mp3"));
RDebugUtils.currentLine=30736393;
 //BA.debugLineNum = 30736393;BA.debugLine="musicPlaylist.Add(\"tracks/happily ever after.mp3\"";
_musicplaylist.Add((Object)("tracks/happily ever after.mp3"));
RDebugUtils.currentLine=30736394;
 //BA.debugLineNum = 30736394;BA.debugLine="musicPlaylist.Add(\"tracks/cookie.mp3\")";
_musicplaylist.Add((Object)("tracks/cookie.mp3"));
RDebugUtils.currentLine=30736395;
 //BA.debugLineNum = 30736395;BA.debugLine="musicPlaylist.Add(\"tracks/comfy vibes.mp3\")";
_musicplaylist.Add((Object)("tracks/comfy vibes.mp3"));
RDebugUtils.currentLine=30736396;
 //BA.debugLineNum = 30736396;BA.debugLine="musicPlaylist.Add(\"tracks/dango.mp3\")";
_musicplaylist.Add((Object)("tracks/dango.mp3"));
RDebugUtils.currentLine=30736397;
 //BA.debugLineNum = 30736397;BA.debugLine="musicPlaylist.Add(\"tracks/iced caramel macchiato.";
_musicplaylist.Add((Object)("tracks/iced caramel macchiato.mp3"));
RDebugUtils.currentLine=30736398;
 //BA.debugLineNum = 30736398;BA.debugLine="musicPlaylist.Add(\"tracks/in dreamland.mp3\")";
_musicplaylist.Add((Object)("tracks/in dreamland.mp3"));
RDebugUtils.currentLine=30736399;
 //BA.debugLineNum = 30736399;BA.debugLine="musicPlaylist.Add(\"tracks/space aquarium.mp3\")";
_musicplaylist.Add((Object)("tracks/space aquarium.mp3"));
RDebugUtils.currentLine=30736400;
 //BA.debugLineNum = 30736400;BA.debugLine="musicPlaylist.Add(\"tracks/sunshine & butterflies.";
_musicplaylist.Add((Object)("tracks/sunshine & butterflies.mp3"));
RDebugUtils.currentLine=30736401;
 //BA.debugLineNum = 30736401;BA.debugLine="musicPlaylist.Add(\"tracks/soda pop.mp3\")";
_musicplaylist.Add((Object)("tracks/soda pop.mp3"));
RDebugUtils.currentLine=30736402;
 //BA.debugLineNum = 30736402;BA.debugLine="musicPlaylist.Add(\"tracks/matcha latte.mp3\")";
_musicplaylist.Add((Object)("tracks/matcha latte.mp3"));
RDebugUtils.currentLine=30736403;
 //BA.debugLineNum = 30736403;BA.debugLine="musicPlaylist.Add(\"tracks/midnight.mp3\")";
_musicplaylist.Add((Object)("tracks/midnight.mp3"));
RDebugUtils.currentLine=30736404;
 //BA.debugLineNum = 30736404;BA.debugLine="musicPlaylist.Add(\"tracks/rose water.mp3\")";
_musicplaylist.Add((Object)("tracks/rose water.mp3"));
RDebugUtils.currentLine=30736405;
 //BA.debugLineNum = 30736405;BA.debugLine="musicPlaylist.Add(\"tracks/hot.mp3\")";
_musicplaylist.Add((Object)("tracks/hot.mp3"));
RDebugUtils.currentLine=30736406;
 //BA.debugLineNum = 30736406;BA.debugLine="musicPlaylist.Add(\"tracks/on the top.mp3\")";
_musicplaylist.Add((Object)("tracks/on the top.mp3"));
RDebugUtils.currentLine=30736408;
 //BA.debugLineNum = 30736408;BA.debugLine="currentSong = 0";
_currentsong = (int) (0);
RDebugUtils.currentLine=30736410;
 //BA.debugLineNum = 30736410;BA.debugLine="songTimer.Initialize(\"songTimer\", 500)";
_songtimer.Initialize(processBA,"songTimer",(long) (500));
RDebugUtils.currentLine=30736411;
 //BA.debugLineNum = 30736411;BA.debugLine="songTimer.Enabled = True";
_songtimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=30736412;
 //BA.debugLineNum = 30736412;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "service_destroy", false))
	 {return ((String) Debug.delegate(processBA, "service_destroy", null));}
RDebugUtils.currentLine=30867456;
 //BA.debugLineNum = 30867456;BA.debugLine="Sub Service_Destroy";
RDebugUtils.currentLine=30867458;
 //BA.debugLineNum = 30867458;BA.debugLine="End Sub";
return "";
}
public static void  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "service_start", false))
	 {Debug.delegate(processBA, "service_start", new Object[] {_startingintent}); return;}
ResumableSub_Service_Start rsub = new ResumableSub_Service_Start(null,_startingintent);
rsub.resume(processBA, null);
}
public static class ResumableSub_Service_Start extends BA.ResumableSub {
public ResumableSub_Service_Start(b4a.example.musicservice parent,anywheresoftware.b4a.objects.IntentWrapper _startingintent) {
this.parent = parent;
this._startingintent = _startingintent;
}
b4a.example.musicservice parent;
anywheresoftware.b4a.objects.IntentWrapper _startingintent;
boolean _qiu = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="musicservice";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
RDebugUtils.currentLine=30801921;
 //BA.debugLineNum = 30801921;BA.debugLine="Service.StopAutomaticForeground";
parent.mostCurrent._service.StopAutomaticForeground();
RDebugUtils.currentLine=30801922;
 //BA.debugLineNum = 30801922;BA.debugLine="Wait For (waitStarter) Complete (qiu As Boolean)";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "musicservice", "service_start"), _waitstarter());
this.state = 1;
return;
case 1:
//C
this.state = -1;
_qiu = (Boolean) result[0];
;
RDebugUtils.currentLine=30801923;
 //BA.debugLineNum = 30801923;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _waitstarter() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "waitstarter", false))
	 {return ((anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) Debug.delegate(processBA, "waitstarter", null));}
ResumableSub_waitStarter rsub = new ResumableSub_waitStarter(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_waitStarter extends BA.ResumableSub {
public ResumableSub_waitStarter(b4a.example.musicservice parent) {
this.parent = parent;
}
b4a.example.musicservice parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="musicservice";

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
RDebugUtils.currentLine=31326209;
 //BA.debugLineNum = 31326209;BA.debugLine="Do While Starter.finishedInit = False";
if (true) break;

case 1:
//do while
this.state = 4;
while (parent.mostCurrent._starter._finishedinit /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 1;
RDebugUtils.currentLine=31326210;
 //BA.debugLineNum = 31326210;BA.debugLine="Sleep(100)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "musicservice", "waitstarter"),(int) (100));
this.state = 8;
return;
case 8:
//C
this.state = 1;
;
 if (true) break;
;
RDebugUtils.currentLine=31326213;
 //BA.debugLineNum = 31326213;BA.debugLine="If mediaPlayer.IsPlaying = False Then";

case 4:
//if
this.state = 7;
if (parent._mediaplayer.IsPlaying()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=31326214;
 //BA.debugLineNum = 31326214;BA.debugLine="playSong";
_playsong();
 if (true) break;

case 7:
//C
this.state = -1;
;
RDebugUtils.currentLine=31326217;
 //BA.debugLineNum = 31326217;BA.debugLine="Return True";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
RDebugUtils.currentLine=31326218;
 //BA.debugLineNum = 31326218;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _songtimer_tick() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "songtimer_tick", false))
	 {return ((String) Debug.delegate(processBA, "songtimer_tick", null));}
RDebugUtils.currentLine=31129600;
 //BA.debugLineNum = 31129600;BA.debugLine="Sub songTimer_Tick";
RDebugUtils.currentLine=31129601;
 //BA.debugLineNum = 31129601;BA.debugLine="If mediaPlayer.IsInitialized Then";
if (_mediaplayer.IsInitialized()) { 
RDebugUtils.currentLine=31129602;
 //BA.debugLineNum = 31129602;BA.debugLine="If mediaPlayer.IsPlaying = False And mediaPlayer";
if (_mediaplayer.IsPlaying()==anywheresoftware.b4a.keywords.Common.False && _mediaplayer.getDuration()>0) { 
RDebugUtils.currentLine=31129603;
 //BA.debugLineNum = 31129603;BA.debugLine="If mediaPlayer.Position >= mediaPlayer.Duration";
if (_mediaplayer.getPosition()>=_mediaplayer.getDuration()-100) { 
RDebugUtils.currentLine=31129604;
 //BA.debugLineNum = 31129604;BA.debugLine="nextSong";
_nextsong();
 };
 };
 };
RDebugUtils.currentLine=31129608;
 //BA.debugLineNum = 31129608;BA.debugLine="End Sub";
return "";
}
}