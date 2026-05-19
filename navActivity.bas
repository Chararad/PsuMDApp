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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("navAct")
			Else
				Activity.LoadLayout("navActDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("navAct2")
			Else
				Activity.LoadLayout("navActDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("navAct3")
			Else
				Activity.LoadLayout("navActDark3")
			End If
	End Select

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub calendarBtn_Click
	StartActivity(CalendarActivity)
End Sub

Private Sub clkBtn_Click
	StartActivity(clockActivity)
End Sub

Private Sub corkpadBtn_Click
	StartActivity(corkActivity)
End Sub

Private Sub flashBtn_Click
	StartActivity(FlashcardActivity)	
End Sub

Private Sub musicBtn_Click
	StartActivity(musicActivity)
End Sub

Private Sub ntpdBtn_Click
	StartActivity(noteActivity)
End Sub

Private Sub todoBtn_Click
	StartActivity(todoActivity)
End Sub

Private Sub helpBtn_Click
	StartActivity(helpActivity)
End Sub

Private Sub themeBtn_Click
	StartActivity(themeActivity)
End Sub

Private Sub lbBtn_Click
	StartActivity(Leaderboard)
End Sub

Private Sub exitBtn_Click
	Activity.Finish
End Sub

