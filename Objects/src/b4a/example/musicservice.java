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
public b4a.example.mainactivity _mainactivity = null;
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
public b4a.example.helpactivity _helpactivity = null;
public b4a.example.musicactivity _musicactivity = null;
public b4a.example.navactivity _navactivity = null;
public b4a.example.noteactivity _noteactivity = null;
public b4a.example.schedule_module _schedule_module = null;
public b4a.example.subdeck_module _subdeck_module = null;
public b4a.example.themeactivity _themeactivity = null;
public b4a.example.todoactivity _todoactivity = null;
public static String  _setsong(int _index) throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "setsong", false))
	 {return ((String) Debug.delegate(processBA, "setsong", new Object[] {_index}));}
RDebugUtils.currentLine=18415616;
 //BA.debugLineNum = 18415616;BA.debugLine="Sub setSong(index As Int)";
RDebugUtils.currentLine=18415617;
 //BA.debugLineNum = 18415617;BA.debugLine="currentSong = index";
_currentsong = _index;
RDebugUtils.currentLine=18415618;
 //BA.debugLineNum = 18415618;BA.debugLine="playSong";
_playsong();
RDebugUtils.currentLine=18415619;
 //BA.debugLineNum = 18415619;BA.debugLine="End Sub";
return "";
}
public static String  _nextsong() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "nextsong", false))
	 {return ((String) Debug.delegate(processBA, "nextsong", null));}
RDebugUtils.currentLine=18612224;
 //BA.debugLineNum = 18612224;BA.debugLine="Sub nextSong";
RDebugUtils.currentLine=18612225;
 //BA.debugLineNum = 18612225;BA.debugLine="currentSong = currentSong + 1";
_currentsong = (int) (_currentsong+1);
RDebugUtils.currentLine=18612226;
 //BA.debugLineNum = 18612226;BA.debugLine="If currentSong >= musicPlaylist.Size Then";
if (_currentsong>=_musicplaylist.getSize()) { 
RDebugUtils.currentLine=18612227;
 //BA.debugLineNum = 18612227;BA.debugLine="currentSong = 0";
_currentsong = (int) (0);
 };
RDebugUtils.currentLine=18612229;
 //BA.debugLineNum = 18612229;BA.debugLine="playSong";
_playsong();
RDebugUtils.currentLine=18612230;
 //BA.debugLineNum = 18612230;BA.debugLine="End Sub";
return "";
}
public static String  _pausetoggle() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "pausetoggle", false))
	 {return ((String) Debug.delegate(processBA, "pausetoggle", null));}
RDebugUtils.currentLine=18350080;
 //BA.debugLineNum = 18350080;BA.debugLine="Sub pauseToggle";
RDebugUtils.currentLine=18350081;
 //BA.debugLineNum = 18350081;BA.debugLine="If mediaPlayer.IsPlaying Then";
if (_mediaplayer.IsPlaying()) { 
RDebugUtils.currentLine=18350082;
 //BA.debugLineNum = 18350082;BA.debugLine="mediaPlayer.Pause";
_mediaplayer.Pause();
 }else {
RDebugUtils.currentLine=18350084;
 //BA.debugLineNum = 18350084;BA.debugLine="mediaPlayer.Play";
_mediaplayer.Play();
 };
RDebugUtils.currentLine=18350086;
 //BA.debugLineNum = 18350086;BA.debugLine="End Sub";
return "";
}
public static String  _prevsong() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "prevsong", false))
	 {return ((String) Debug.delegate(processBA, "prevsong", null));}
RDebugUtils.currentLine=18677760;
 //BA.debugLineNum = 18677760;BA.debugLine="Sub prevSong";
RDebugUtils.currentLine=18677761;
 //BA.debugLineNum = 18677761;BA.debugLine="currentSong = currentSong - 1";
_currentsong = (int) (_currentsong-1);
RDebugUtils.currentLine=18677762;
 //BA.debugLineNum = 18677762;BA.debugLine="If currentSong < 0 Then";
if (_currentsong<0) { 
RDebugUtils.currentLine=18677763;
 //BA.debugLineNum = 18677763;BA.debugLine="currentSong = musicPlaylist.Size - 1";
_currentsong = (int) (_musicplaylist.getSize()-1);
 };
RDebugUtils.currentLine=18677765;
 //BA.debugLineNum = 18677765;BA.debugLine="playSong";
_playsong();
RDebugUtils.currentLine=18677766;
 //BA.debugLineNum = 18677766;BA.debugLine="End Sub";
return "";
}
public static String  _playsong() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "playsong", false))
	 {return ((String) Debug.delegate(processBA, "playsong", null));}
String _trackname = "";
String _filename = "";
RDebugUtils.currentLine=18481152;
 //BA.debugLineNum = 18481152;BA.debugLine="Sub playSong";
RDebugUtils.currentLine=18481153;
 //BA.debugLineNum = 18481153;BA.debugLine="If mediaPlayer.IsInitialized Then";
if (_mediaplayer.IsInitialized()) { 
RDebugUtils.currentLine=18481154;
 //BA.debugLineNum = 18481154;BA.debugLine="mediaPlayer.Stop";
_mediaplayer.Stop();
 };
RDebugUtils.currentLine=18481156;
 //BA.debugLineNum = 18481156;BA.debugLine="Dim trackName As String = musicPlaylist.Get(curre";
_trackname = BA.ObjectToString(_musicplaylist.Get(_currentsong));
RDebugUtils.currentLine=18481157;
 //BA.debugLineNum = 18481157;BA.debugLine="Dim fileName As String = trackName.SubString(trac";
_filename = _trackname.substring((int) (_trackname.lastIndexOf("/")+1));
RDebugUtils.currentLine=18481159;
 //BA.debugLineNum = 18481159;BA.debugLine="mediaPlayer.Load(File.DirInternal & \"/tracks\", fi";
_mediaplayer.Load(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/tracks",_filename);
RDebugUtils.currentLine=18481160;
 //BA.debugLineNum = 18481160;BA.debugLine="mediaPlayer.Play";
_mediaplayer.Play();
RDebugUtils.currentLine=18481161;
 //BA.debugLineNum = 18481161;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "service_create", false))
	 {return ((String) Debug.delegate(processBA, "service_create", null));}
RDebugUtils.currentLine=18153472;
 //BA.debugLineNum = 18153472;BA.debugLine="Sub Service_Create";
RDebugUtils.currentLine=18153473;
 //BA.debugLineNum = 18153473;BA.debugLine="mediaPlayer.Initialize";
_mediaplayer.Initialize();
RDebugUtils.currentLine=18153474;
 //BA.debugLineNum = 18153474;BA.debugLine="musicPlaylist.Initialize";
_musicplaylist.Initialize();
RDebugUtils.currentLine=18153476;
 //BA.debugLineNum = 18153476;BA.debugLine="musicPlaylist.Add(\"tracks/intro.mp3\")";
_musicplaylist.Add((Object)("tracks/intro.mp3"));
RDebugUtils.currentLine=18153477;
 //BA.debugLineNum = 18153477;BA.debugLine="musicPlaylist.Add(\"tracks/taiyaki.mp3\")";
_musicplaylist.Add((Object)("tracks/taiyaki.mp3"));
RDebugUtils.currentLine=18153478;
 //BA.debugLineNum = 18153478;BA.debugLine="musicPlaylist.Add(\"tracks/feel special.mp3\")";
_musicplaylist.Add((Object)("tracks/feel special.mp3"));
RDebugUtils.currentLine=18153479;
 //BA.debugLineNum = 18153479;BA.debugLine="musicPlaylist.Add(\"tracks/union.mp3\")";
_musicplaylist.Add((Object)("tracks/union.mp3"));
RDebugUtils.currentLine=18153480;
 //BA.debugLineNum = 18153480;BA.debugLine="musicPlaylist.Add(\"tracks/two in the morning.mp3\"";
_musicplaylist.Add((Object)("tracks/two in the morning.mp3"));
RDebugUtils.currentLine=18153481;
 //BA.debugLineNum = 18153481;BA.debugLine="musicPlaylist.Add(\"tracks/happily ever after.mp3\"";
_musicplaylist.Add((Object)("tracks/happily ever after.mp3"));
RDebugUtils.currentLine=18153482;
 //BA.debugLineNum = 18153482;BA.debugLine="musicPlaylist.Add(\"tracks/cookie.mp3\")";
_musicplaylist.Add((Object)("tracks/cookie.mp3"));
RDebugUtils.currentLine=18153483;
 //BA.debugLineNum = 18153483;BA.debugLine="musicPlaylist.Add(\"tracks/comfy vibes.mp3\")";
_musicplaylist.Add((Object)("tracks/comfy vibes.mp3"));
RDebugUtils.currentLine=18153484;
 //BA.debugLineNum = 18153484;BA.debugLine="musicPlaylist.Add(\"tracks/dango.mp3\")";
_musicplaylist.Add((Object)("tracks/dango.mp3"));
RDebugUtils.currentLine=18153485;
 //BA.debugLineNum = 18153485;BA.debugLine="musicPlaylist.Add(\"tracks/iced caramel macchiato.";
_musicplaylist.Add((Object)("tracks/iced caramel macchiato.mp3"));
RDebugUtils.currentLine=18153486;
 //BA.debugLineNum = 18153486;BA.debugLine="musicPlaylist.Add(\"tracks/in dreamland.mp3\")";
_musicplaylist.Add((Object)("tracks/in dreamland.mp3"));
RDebugUtils.currentLine=18153487;
 //BA.debugLineNum = 18153487;BA.debugLine="musicPlaylist.Add(\"tracks/space aquarium.mp3\")";
_musicplaylist.Add((Object)("tracks/space aquarium.mp3"));
RDebugUtils.currentLine=18153488;
 //BA.debugLineNum = 18153488;BA.debugLine="musicPlaylist.Add(\"tracks/sunshine & butterflies.";
_musicplaylist.Add((Object)("tracks/sunshine & butterflies.mp3"));
RDebugUtils.currentLine=18153489;
 //BA.debugLineNum = 18153489;BA.debugLine="musicPlaylist.Add(\"tracks/soda pop.mp3\")";
_musicplaylist.Add((Object)("tracks/soda pop.mp3"));
RDebugUtils.currentLine=18153490;
 //BA.debugLineNum = 18153490;BA.debugLine="musicPlaylist.Add(\"tracks/matcha latte.mp3\")";
_musicplaylist.Add((Object)("tracks/matcha latte.mp3"));
RDebugUtils.currentLine=18153491;
 //BA.debugLineNum = 18153491;BA.debugLine="musicPlaylist.Add(\"tracks/midnight.mp3\")";
_musicplaylist.Add((Object)("tracks/midnight.mp3"));
RDebugUtils.currentLine=18153492;
 //BA.debugLineNum = 18153492;BA.debugLine="musicPlaylist.Add(\"tracks/rose water.mp3\")";
_musicplaylist.Add((Object)("tracks/rose water.mp3"));
RDebugUtils.currentLine=18153493;
 //BA.debugLineNum = 18153493;BA.debugLine="musicPlaylist.Add(\"tracks/hot.mp3\")";
_musicplaylist.Add((Object)("tracks/hot.mp3"));
RDebugUtils.currentLine=18153494;
 //BA.debugLineNum = 18153494;BA.debugLine="musicPlaylist.Add(\"tracks/on the top.mp3\")";
_musicplaylist.Add((Object)("tracks/on the top.mp3"));
RDebugUtils.currentLine=18153496;
 //BA.debugLineNum = 18153496;BA.debugLine="currentSong = 0";
_currentsong = (int) (0);
RDebugUtils.currentLine=18153498;
 //BA.debugLineNum = 18153498;BA.debugLine="songTimer.Initialize(\"songTimer\", 500)";
_songtimer.Initialize(processBA,"songTimer",(long) (500));
RDebugUtils.currentLine=18153499;
 //BA.debugLineNum = 18153499;BA.debugLine="songTimer.Enabled = True";
_songtimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=18153500;
 //BA.debugLineNum = 18153500;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "service_destroy", false))
	 {return ((String) Debug.delegate(processBA, "service_destroy", null));}
RDebugUtils.currentLine=18284544;
 //BA.debugLineNum = 18284544;BA.debugLine="Sub Service_Destroy";
RDebugUtils.currentLine=18284546;
 //BA.debugLineNum = 18284546;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18219009;
 //BA.debugLineNum = 18219009;BA.debugLine="Service.StopAutomaticForeground";
parent.mostCurrent._service.StopAutomaticForeground();
RDebugUtils.currentLine=18219010;
 //BA.debugLineNum = 18219010;BA.debugLine="Wait For (waitStarter) Complete (qiu As Boolean)";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "musicservice", "service_start"), _waitstarter());
this.state = 1;
return;
case 1:
//C
this.state = -1;
_qiu = (Boolean) result[0];
;
RDebugUtils.currentLine=18219011;
 //BA.debugLineNum = 18219011;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=18743297;
 //BA.debugLineNum = 18743297;BA.debugLine="Do While Starter.finishedInit = False";
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
RDebugUtils.currentLine=18743298;
 //BA.debugLineNum = 18743298;BA.debugLine="Sleep(100)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "musicservice", "waitstarter"),(int) (100));
this.state = 8;
return;
case 8:
//C
this.state = 1;
;
 if (true) break;
;
RDebugUtils.currentLine=18743301;
 //BA.debugLineNum = 18743301;BA.debugLine="If mediaPlayer.IsPlaying = False Then";

case 4:
//if
this.state = 7;
if (parent._mediaplayer.IsPlaying()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=18743302;
 //BA.debugLineNum = 18743302;BA.debugLine="playSong";
_playsong();
 if (true) break;

case 7:
//C
this.state = -1;
;
RDebugUtils.currentLine=18743305;
 //BA.debugLineNum = 18743305;BA.debugLine="Return True";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
RDebugUtils.currentLine=18743306;
 //BA.debugLineNum = 18743306;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _songtimer_tick() throws Exception{
RDebugUtils.currentModule="musicservice";
if (Debug.shouldDelegate(processBA, "songtimer_tick", false))
	 {return ((String) Debug.delegate(processBA, "songtimer_tick", null));}
RDebugUtils.currentLine=18546688;
 //BA.debugLineNum = 18546688;BA.debugLine="Sub songTimer_Tick";
RDebugUtils.currentLine=18546689;
 //BA.debugLineNum = 18546689;BA.debugLine="If mediaPlayer.IsInitialized Then";
if (_mediaplayer.IsInitialized()) { 
RDebugUtils.currentLine=18546690;
 //BA.debugLineNum = 18546690;BA.debugLine="If mediaPlayer.IsPlaying = False And mediaPlayer";
if (_mediaplayer.IsPlaying()==anywheresoftware.b4a.keywords.Common.False && _mediaplayer.getDuration()>0) { 
RDebugUtils.currentLine=18546691;
 //BA.debugLineNum = 18546691;BA.debugLine="If mediaPlayer.Position >= mediaPlayer.Duration";
if (_mediaplayer.getPosition()>=_mediaplayer.getDuration()-100) { 
RDebugUtils.currentLine=18546692;
 //BA.debugLineNum = 18546692;BA.debugLine="nextSong";
_nextsong();
 };
 };
 };
RDebugUtils.currentLine=18546696;
 //BA.debugLineNum = 18546696;BA.debugLine="End Sub";
return "";
}
}