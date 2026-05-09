B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private timerCount As Timer
	Private xui As XUI
	Private secondsRemain As Int = 1500

End Sub
'stopwatch

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private pomotimerLbl As Label
	Private playBtn As Button
	Private sessionLbl As Label
	Private pomoCounter As Label
	Private settingsBtn As Button
	Private settingsPnl As B4XView
	Private pomoTxt As EditText
	Private shortTxt As EditText
	Private longTxt As EditText
	Dim timerState As Int = 0
	Dim counter As Int = 0
	Dim pomoDef As Int = 1500
	Dim shortDef As Int = 300
	Dim longDef As Int = 900
	Dim centerLeft As Int = 100dip
	Dim centerTop As Int = 285dip
	Dim playing As Boolean = False
	Dim break As Int = 0

End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("clocklayout")
			Else
				Activity.LoadLayout("clocklayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("clocklayout2")
			Else
				Activity.LoadLayout("clocklayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("clocklayout3")
			Else
				Activity.LoadLayout("clocklayoutDark3")
			End If
	End Select

	If FirstTime Then
		timerCount.Initialize("tmr", 1000)
	End If
	
	timerCount.Enabled = False
	updateLbl
	pomoCounter.Text = counter
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Private Sub exitBtn_Click
	Activity.Finish
End Sub

Private Sub formatBtn_Click
	
	MainActivity.format24h = Not(MainActivity.format24h)
	
	If MainActivity.format24h Then
		DateTime.TimeFormat = "HH:mm"
	Else
		DateTime.TimeFormat = "hh:mm a"
	End If
	CallSub(MainActivity, "timerClock_Tick")
	
End Sub

Private Sub playBtn_Click
	If secondsRemain > 0 Then
		timerCount.Enabled = True
	End If
	If playing = True Then
		timerStop
	Else
		timerCount.Enabled = True
		playing = True
		playBtn.Text = "Pause"
	End If

End Sub

Sub tmr_Tick
	If secondsRemain > 0 Then
		secondsRemain = secondsRemain - 1
		updateLbl
	Else
		timerStop
		
		If timerState = 0 Then
			counter = counter + 1
			
			If counter Mod 4 = 0 Then
				secondsRemain = longDef
				break = 1
			Else
				secondsRemain = shortDef
				break = 0
			End If
			timerState = 1
        
		Else If timerState = 1 Then
			secondsRemain = pomoDef
			timerState = 0
		End If
    
		updateLbl
	End If
End Sub

Sub updateLbl
	Dim mins As Int = secondsRemain / 60
	Dim secs As Int = secondsRemain Mod 60
	pomotimerLbl.Text = $"$02.0{mins}:$02.0{secs}"$
	pomoCounter.Text = counter
	
	If timerState = 0 Then
		sessionLbl.Text = "Pomodoro"
	Else
		If break = 1 Then
			sessionLbl.Text = "Long Break"
		Else
			sessionLbl.Text = "Short Break"
		End If
	End If
End Sub

Private Sub settingsWindow(pW As Int, pH As Int)
	settingsPnl = xui.CreatePanel("settingsPnl")
	Activity.AddView(settingsPnl, centerLeft, centerTop, pW, pH)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				settingsPnl.Color = xui.Color_RGB(50, 50, 50)
				settingsPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
			Else
				settingsPnl.Color = xui.Color_RGB(50, 50, 50)
				settingsPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
			End If
		Case 1
			If Starter.darkMode = False Then
				settingsPnl.Color = xui.Color_RGB(50, 50, 50)
				settingsPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
			Else
				settingsPnl.Color = xui.Color_RGB(50, 50, 50)
				settingsPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
			End If
		Case 2
			If Starter.darkMode = False Then
				settingsPnl.Color = xui.Color_RGB(50, 50, 50)
				settingsPnl.SetColorAndBorder(xui.Color_RGB(231, 213, 179), 2dip, xui.Color_RGB(73, 43, 50), 3dip)
			Else
				settingsPnl.Color = xui.Color_RGB(50, 50, 50)
				settingsPnl.SetColorAndBorder(xui.Color_RGB(24, 20, 37), 2dip, xui.Color_white, 3dip)
			End If
	End Select

	settingsPnl.Enabled = False
	settingsPnl.Visible = False
	
	

	pomoTxt.Initialize("pomoTxt")
	pomoTxt.Hint = "Pomo"
	pomoTxt.InputType = pomoTxt.INPUT_TYPE_NUMBERS
	pomoTxt.Text = pomoDef / 60
	pomoTxt.Gravity = Gravity.CENTER_HORIZONTAL
	settingsPnl.AddView(pomoTxt, 10dip, 40dip, 70dip, 40dip)
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		pomoTxt.HintColor = Colors.ARGB(50, 255, 255, 255)
	End If

	shortTxt.Initialize("shortTxt")
	shortTxt.Hint = "Short"
	shortTxt.InputType = shortTxt.INPUT_TYPE_NUMBERS
	shortTxt.Text = shortDef / 60
	shortTxt.Gravity = Gravity.CENTER_HORIZONTAL
	settingsPnl.AddView(shortTxt, 90dip, 40dip, 70dip, 40dip)
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		shortTxt.HintColor = Colors.ARGB(50, 255, 255, 255)
	End If

	longTxt.Initialize("longTxt")
	longTxt.Hint = "Long"
	longTxt.InputType = longTxt.INPUT_TYPE_NUMBERS
	longTxt.Text = longDef / 60
	longTxt.Gravity = Gravity.CENTER_HORIZONTAL
	settingsPnl.AddView(longTxt, 170dip, 40dip, 70dip, 40dip)
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		longTxt.HintColor = Colors.ARGB(50, 255, 255, 255)
	End If
	
	Dim lblP, lblS, lblL As Label
	
	lblP.Initialize("")
	lblP.Text = "Pomo"
	lblP.TextSize = 12
	lblP.Gravity = Gravity.CENTER_HORIZONTAL
	settingsPnl.AddView(lblP, 10dip, 80dip, 70dip, 20dip)
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		lblP.TextColor = Colors.White
	End If
	
	lblS.Initialize("")
	lblS.Text = "Short"
	lblS.TextSize = 12
	lblS.Gravity = Gravity.CENTER_HORIZONTAL
	settingsPnl.AddView(lblS, 90dip, 80dip, 70dip, 20dip)
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		lblS.TextColor = Colors.White
	End If
	
	lblL.Initialize("")
	lblL.Text = "Long"
	lblL.TextSize = 12
	lblL.Gravity = Gravity.CENTER_HORIZONTAL
	settingsPnl.AddView(lblL, 170dip, 80dip, 70dip, 20dip)
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		lblL.TextColor = Colors.White
	End If
	
	Dim closeL As Label
	closeL.Initialize("closeL")
	settingsPnl.AddView(closeL, 10dip, 10dip, 20dip, 20dip)
	closeL.Text = "X"
	If Starter.themeNumber = 2 And Starter.darkMode = True Then
		closeL.TextColor = Colors.White
	End If
	
	Dim saveBtn As Button
	saveBtn.Initialize("saveBtn")
	saveBtn.Text = "Save Settings"
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				saveBtn.TextColor = Colors.RGB(231, 213, 179)
				saveBtn.Color = Colors.RGB(115,62,57)
			Else
				saveBtn.TextColor = Colors.RGB(231, 213, 179)
				saveBtn.Color = Colors.RGB(115,62,57)
			End If
		Case 1
			If Starter.darkMode = False Then
				saveBtn.TextColor = Colors.RGB(231, 213, 179)
				saveBtn.Color = Colors.RGB(115,62,57)
			Else
				saveBtn.TextColor = Colors.RGB(231, 213, 179)
				saveBtn.Color = Colors.RGB(115,62,57)
			End If
		Case 2
			If Starter.darkMode = False Then
				saveBtn.TextColor = Colors.RGB(231, 213, 179)
				saveBtn.Color = Colors.RGB(115,62,57)
			Else
				saveBtn.TextColor = Colors.RGB(24, 20, 37)
				saveBtn.Color = Colors.RGB(90,105,136)
			End If
	End Select
	settingsPnl.AddView(saveBtn, 10dip, 130dip, 230dip, 40dip)
	
End Sub

Private Sub pomoBtn_Click
	timerStop
	secondsRemain = pomoDef
	timerState = 0
	updateLbl
End Sub

Private Sub shortBtn_Click
	timerStop
	secondsRemain = shortDef
	timerState = 1
	break = 0
	updateLbl
End Sub

Private Sub longBtn_Click
	timerStop
	secondsRemain = longDef
	timerState = 1
	break = 1
	updateLbl
End Sub

Private Sub settingsBtn_Click
	settingsWindow(250dip, 180dip)
	settingsPnl.Visible = True
End Sub

Private Sub closeL_Click
	settingsPnl.Visible = False
End Sub

Private Sub saveBtn_Click

	If IsNumber(pomoTxt.Text) Then pomoDef = pomoTxt.Text * 60
	If IsNumber(shortTxt.Text) Then shortDef = shortTxt.Text * 60
	If IsNumber(longTxt.Text) Then longDef = longTxt.Text * 60

	If timerState = 0 Then
		secondsRemain = pomoDef
	Else

		If break = 1 Then
			secondsRemain = longDef
		Else
			secondsRemain = shortDef
		End If
	End If
	updateLbl
	settingsPnl.Visible = False
End Sub

Private Sub skipBtn_Click
	If timerState = 0 Then
		counter = counter + 1
			
		If counter Mod 4 = 0 Then
			secondsRemain = longDef
			break = 1
		Else
			secondsRemain = shortDef
			break = 0
		End If
		timerState = 1
        
	Else If timerState = 1 Then
		secondsRemain = pomoDef
		timerState = 0
	End If
    
	updateLbl
	timerStop
End Sub

Private Sub timerStop
	timerCount.Enabled = False
	playing = False
	playBtn.Text = "Start"
End Sub