B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Public darkMode As Boolean = False
	Public themeNumber As Int = 0
	Public notesKvs As KeyValueStore
	Public prefKvs As KeyValueStore
	Public calKvs As KeyValueStore
	Public calendarMap As Map
	Public deckKvs As KeyValueStore
	Public taskKvs As KeyValueStore
	Public deck As Map
	Public finishedInit As Boolean = False
	Public themeChanged As Boolean = False
	Public SQL1 As SQL
	Public currentUserID As String
End Sub

Sub Service_Create
	prefKvs.Initialize(File.DirInternal, "prefData")
	
	If prefKvs.ContainsKey("darkMode") Then
		darkMode = prefKvs.Get("darkMode")
	End If
	
	If prefKvs.ContainsKey("themeNumber") Then
		themeNumber = prefKvs.Get("themeNumber")
	End If
	
	calKvs.Initialize(File.DirInternal, "mydata")
	If calKvs.ContainsKey("CalendarKVS") Then
		calendarMap = calKvs.Get("CalendarKVS")
	Else
		calendarMap.Initialize
	End If
	
	deckKvs.Initialize(File.DirInternal, "mydata")
	If deckKvs.ContainsKey("deck_data") Then
		deck = deckKvs.Get("deck_data")
	Else
		deck.Initialize
	End If
	
	taskKvs.Initialize(File.DirInternal, "todoListData")
	notesKvs.Initialize(File.DirInternal, "notes_data")
	
	CopyTracksIfNeeded
	
	finishedInit = True
	
	SQL1.Initialize(File.DirInternal, "users.db", True)
	SQL1.ExecNonQuery("CREATE TABLE IF NOT EXISTS users (" & _
                      "id INTEGER PRIMARY KEY AUTOINCREMENT, " & _
                      "email TEXT UNIQUE, " & _
                      "name TEXT, " & _
                      "password TEXT)")
End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StopAutomaticForeground 'Starter service can start in the foreground state in some edge cases.
End Sub

Sub Service_TaskRemoved
	'This event will be raised when the user removes the app from the recent apps list.
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

Sub CopyTracksIfNeeded
	Dim playlist As List
	playlist.Initialize
	playlist.Add("tracks/intro.mp3")
	playlist.Add("tracks/taiyaki.mp3")
	playlist.Add("tracks/feel special.mp3")
	playlist.Add("tracks/union.mp3")
	playlist.Add("tracks/two in the morning.mp3")
	playlist.Add("tracks/happily ever after.mp3")
	playlist.Add("tracks/cookie.mp3")
	playlist.Add("tracks/comfy vibes.mp3")
	playlist.Add("tracks/dango.mp3")
	playlist.Add("tracks/iced caramel macchiato.mp3")
	playlist.Add("tracks/in dreamland.mp3")
	playlist.Add("tracks/space aquarium.mp3")
	playlist.Add("tracks/sunshine & butterflies.mp3")
	playlist.Add("tracks/soda pop.mp3")
	playlist.Add("tracks/matcha latte.mp3")
	playlist.Add("tracks/midnight.mp3")
	playlist.Add("tracks/rose water.mp3")
	playlist.Add("tracks/hot.mp3")
	playlist.Add("tracks/on the top.mp3")

	If File.Exists(File.DirInternal, "tracks") = False Then
		File.MakeDir(File.DirInternal, "tracks")
	End If
	For i = 0 To playlist.Size - 1
		Dim trackName As String = playlist.Get(i)
		Dim fileName As String = trackName.SubString(trackName.LastIndexOf("/") + 1)
		If File.Exists(File.DirInternal & "/tracks", fileName) = False Then
			File.Copy(File.DirAssets, trackName, File.DirInternal & "/tracks", fileName)
		End If
	Next
End Sub