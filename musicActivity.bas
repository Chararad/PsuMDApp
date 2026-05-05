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
End Sub

Sub Globals
	Private SeekBar1 As SeekBar
	Private songTitle As Label
	Private pauseBtn As Button
	Private songRuntime As Label
	Private ListView1 As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
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
    
	' Populate ListView from service playlist
	If musicService.mediaPlayer.IsInitialized = False Then
		StartService(musicService)
	End If
    
	For i = 0 To musicService.musicPlaylist.Size - 1
		Dim title As String = musicService.musicPlaylist.Get(i)
		title = title.SubString2(0, title.Length - 4)  ' remove .mp3
		title = title.SubString(7)                      ' remove "tracks/"
		ListView1.AddSingleLine((i + 1) & "   " & title)
		ListView1.SingleLineLayout.Label.TextColor = Colors.Black
	Next
    
	' UI update timer
	uiTimer.Initialize("uiTimer", 500)
	uiTimer.Enabled = True
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause(UserClosed As Boolean)

End Sub

Sub formatSongDur(ms As Int) As String
	Dim seconds As Int = ms / 1000
	Dim minutes As Int = seconds / 60
	seconds = seconds Mod 60
	Return NumberFormat(minutes, 2, 0) & ":" & NumberFormat(seconds, 2, 0)
End Sub

' Updates UI from service state
Sub uiTimer_Tick
	If musicService.mediaPlayer.IsInitialized Then
        
		Dim title As String = musicService.musicPlaylist.Get(musicService.currentSong)
		title = title.SubString2(0, title.Length - 4)  'remove .mp3
		title = title.SubString(7) 'remove /tracks
		
		SeekBar1.Max = musicService.mediaPlayer.Duration
		SeekBar1.Value = musicService.mediaPlayer.Position
		songRuntime.Text = formatSongDur(musicService.mediaPlayer.Position) & " / " & formatSongDur(musicService.mediaPlayer.Duration)
		songTitle.Text = title
        
		If musicService.mediaPlayer.IsPlaying Then
			pauseBtn.Text = "❚❚"
		Else
			pauseBtn.Text = "▶"
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

Sub ListView1_ItemClick(Position As Int, Value As Object)
	CallSub2(musicService, "setSong", Position)
End Sub