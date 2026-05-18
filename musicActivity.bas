B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
#Region  Activity Attributes 
    #FullScreen: False
    #IncludeTitle: False
#End Region

Sub Process_Globals
	Private xui As XUI
	Dim uiTimer As Timer
	Private currentPage As Int
	Private totalPages As Int = 3
	Private chooser As ContentChooser
	Private skipTutorial As Boolean = False
	Private saveCheckState As Boolean = False
End Sub

Sub Globals
	Private lblDesc As Label
	Private btnnext As Button
	Private chkDontShow As CheckBox
	
	Private SeekBar1 As SeekBar
	Private songTitle As Label
	Private pauseBtn As Button
	Private songRuntime As Label
	Private ListView1 As ListView
	Private btnUpload As Button
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	chooser.Initialize("chooser")
	currentPage = 0
	
	If skipTutorial Then
		LoadMusicPlayer
	Else
		ShowTutorialPage
	End If
End Sub

Sub ShowTutorialPage
	Activity.RemoveAllViews
	
	' Description Label
	lblDesc.Initialize("")
	lblDesc.TextSize = 20	
	lblDesc.TextColor = Colors.RGB(20, 40, 80)
	lblDesc.Gravity = Gravity.CENTER
	lblDesc.Typeface = Typeface.DEFAULT_BOLD
	lblDesc.SetLayout(20dip, 30dip, 100%x - 40dip, 60%y)
	Activity.AddView(lblDesc, 20dip, 30dip, 100%x - 40dip, 60%y)

	' Checkbox
	If currentPage = 2 Then
	chkDontShow.Initialize("chkDontShow")
	chkDontShow.Text = "🙈 Don't show this again"
	chkDontShow.TextSize = 14
	chkDontShow.Typeface = Typeface.DEFAULT
	chkDontShow.Checked = saveCheckState
	chkDontShow.SetLayout(20dip, 55%y, 200dip, 30dip)
	Activity.AddView(chkDontShow, 20dip, 55%y, 200dip, 30dip)
	End If
	
	' Next Button
	btnnext.Initialize("btnnext")
	btnnext.TextSize = 16
	btnnext.Color = Colors.RGB(100, 120, 180)
	btnnext.TextColor = Colors.White
	btnnext.Typeface = Typeface.DEFAULT_BOLD
	btnnext.SetLayout(50dip, 60%y, 200dip, 50dip)
	Activity.AddView(btnnext, 50dip, 60%y, 200dip, 50dip)
	
	' Tutorial Texts
	If currentPage = 0 Then
		lblDesc.Text = "🎵 WELCOME TO MUSIC PLAYER 🎵" & CRLF & CRLF & _
		"Easily play, pause, skip " & CRLF & CRLF & _
		"and enjoy all your music " & CRLF & CRLF & _
		"Everything you need is right here "
		btnnext.Text = "➡️ Next"
		
	else If currentPage = 1 Then
		lblDesc.Text = "️HOW TO USE THE BUTTONS: ️" & CRLF & CRLF & _
		"⏮️ PREVIOUS: Go back to last song " & CRLF & CRLF & _
		"⏯️ PLAY/PAUSE: Start/stop music" & CRLF & CRLF & _
		"⏭️ NEXT: Go to the next song" 
		btnnext.Text = "➡️ Next"
		
	else If currentPage = 2 Then
		lblDesc.Text = "✅ ALL DONE! ✅" & CRLF & CRLF & _
		"Now you know all features " & CRLF & CRLF & _
		"Upload your own music" & CRLF & CRLF & _
		"Anytime from the Main player " & CRLF & CRLF & _
		"Enjoy listening!"
		btnnext.Text = "✅ Finish"
	End If
End Sub
Sub btnnext_Click
	currentPage = currentPage + 1
	
	If currentPage < totalPages Then
		ShowTutorialPage
	Else
		
		If chkDontShow.Checked Then
			skipTutorial = True
		Else
			skipTutorial = False
		End If
		
		LoadMusicPlayer
	End If
End Sub

Sub LoadMusicPlayer
	Activity.RemoveAllViews
	
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("musicLayout")
			Else
				Activity.LoadLayout("musicLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("musicLayout2")
			Else
				Activity.LoadLayout("musicLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("musicLayout3")
			Else
				Activity.LoadLayout("musicLayoutDark3")
			End If
	End Select
    
	If musicService.mediaPlayer.IsInitialized = False Then
		StartService(musicService)
	End If
    
	btnUpload.Initialize("btnUpload")
	btnUpload.Text = "📤 UPLOAD MUSIC"
	btnUpload.TextSize = 14
	btnUpload.Color = Colors.RGB(40, 120, 200)
	btnUpload.TextColor = Colors.White
	btnUpload.Typeface = Typeface.DEFAULT_BOLD
	btnUpload.SetLayout(50dip, 92%y, 200dip, 40dip)
	Activity.AddView(btnUpload, 50dip, 92%y, 200dip, 40dip)
	
	' Load songs
	For i = 0 To musicService.musicPlaylist.Size - 1
		Dim title As String
		title = musicService.musicPlaylist.Get(i)
		title = title.SubString2(0, title.Length - 4)
		title = title.SubString(7)
		ListView1.AddSingleLine((i + 1) & "   " & title)
		If Starter.themeNumber = 2 And Starter.darkMode = True Then
			ListView1.SingleLineLayout.Label.TextColor = Colors.White
		Else
			ListView1.SingleLineLayout.Label.TextColor = Colors.RGB(24, 20, 37)
		End If
	Next
    
	uiTimer.Initialize("uiTimer", 500)
	uiTimer.Enabled = True
End Sub

Sub btnUpload_Click
	chooser.Show("audio/*", "Choose Music File")
End Sub

Sub chooser_Result (Success As Boolean, Dir As String, FileName As String)
	If Success Then
		Dim songName As String
		songName = FileName.SubString(FileName.LastIndexOf("/") + 1)
		ListView1.AddSingleLine(songName)
		ToastMessageShow("✅ Added: " & songName, False)
	End If
End Sub

Sub ListView1_ItemLongClick (Position As Int, Value As Object)
	MsgboxAsync ("📝 DETAILED SONG INFO:" & CRLF & CRLF & _
	"Title: " & Value & CRLF & _
	"Duration: 03:45" & CRLF & _
	"Size: 4.2 MB" & CRLF & _
	"Path: Internal Storage/Music/", "Song Details")
End Sub

Sub ListView1_ItemClick(Position As Int, Value As Object)
	CallSub2(musicService, "setSong", Position)
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause(UserClosed As Boolean)

End Sub

Sub formatSongDur(ms As Int) As String
	Dim seconds As Int
	Dim minutes As Int
	seconds = ms / 1000
	minutes = seconds / 60
	seconds = seconds Mod 60
	Return NumberFormat(minutes, 2, 0) & ":" & NumberFormat(seconds, 2, 0)
End Sub

Sub uiTimer_Tick
	If musicService.mediaPlayer.IsInitialized Then
		Dim title As String
		title = musicService.musicPlaylist.Get(musicService.currentSong)
		title = title.SubString2(0, title.Length - 4)
		title = title.SubString(7)
		
		SeekBar1.Max = musicService.mediaPlayer.Duration
		SeekBar1.Value = musicService.mediaPlayer.Position
		songRuntime.Text = formatSongDur(musicService.mediaPlayer.Position) & " / " & formatSongDur(musicService.mediaPlayer.Duration)
		songTitle.Text = title
        
		If musicService.mediaPlayer.IsPlaying Then
			pauseBtn.Text = "❚❚"
		Else
			pauseBtn.Text = "▶"
			pauseBtn.TextSize = 24
		End If
	End If
End Sub

Sub SeekBar1_ValueChanged(Value As Int, UserChanged As Boolean)
	If UserChanged Then
		musicService.mediaPlayer.Position = Value
	End If
End Sub

Sub nextBtn_Click
	CallSub(musicService, "nextSong")
End Sub

Sub prevBtn_Click
	CallSub(musicService, "prevSong")
End Sub

Sub pauseBtn_Click
	CallSub(musicService, "pauseToggle")
End Sub

