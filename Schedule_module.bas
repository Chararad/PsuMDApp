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

	Private menupanel As Panel
	Private sched_btn As Button
	Private scheduleSV As ScrollView
	Private noschedlabel As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("Schedule_ModuleLayout")
			Else
				Activity.LoadLayout("Schedule_ModuleLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("Schedule_ModuleLayout2")
			Else
				Activity.LoadLayout("Schedule_ModuleLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("Schedule_ModuleLayout3")
			Else
				Activity.LoadLayout("Schedule_ModuleLayoutDark3")
			End If
	End Select
	
	sched_btn.Color = Colors.LightGray
	DrawSchedule

End Sub

Sub DrawSchedule
	scheduleSV.Panel.RemoveAllViews
	
	Dim y As Int = 0
	Dim sortedDates As List
	sortedDates.Initialize
	
	If CalendarActivity.CalendarMap.Size = 0 Then
		noschedlabel.Text = "No Schedule"
	End If
	
	For Each keys As String In CalendarActivity.CalendarMap.Keys
		sortedDates.Add(keys)
		Log(keys)
	Next
	
	sortedDates.Sort(True)
	
	For Each date As String In sortedDates
		Dim eventmap As Map = CalendarActivity.CalendarMap.Get(date)
		Dim allevents As List = eventmap.Get("AllEvents")
		Dim timeline As List = eventmap.Get("Timeline")
		
		Dim lbldate As Label
		lbldate.initialize("")
		lbldate.Text = SetDate(date)
		lbldate.TextSize = 16
		lbldate.Color = Colors.LightGray
		lbldate.TextColor = Colors.Black
		
		If allevents.Size = 0 And timeline.size = 0 Then
			Continue
		End If
		
		scheduleSV.Panel.AddView(lbldate, 0, y, scheduleSV.Width, 40dip)
		y = y+ 40dip
		
		For Each ev As Map In allevents
			Dim lbl As Label
			lbl.Initialize("")
			lbl.Text = ev.Get("Title")
			lbl.Color = IdentifyColor(ev.Get("Tags"))
			lbl.TextColor = Colors.Black
			scheduleSV.Panel.AddView(lbl, 10dip, y, scheduleSV.Width - 20dip, 40dip)
			y = y + 40dip
			
		Next
		
		For Each ev As Map In timeline
			Dim lbl As Label
			lbl.Initialize("")
			Dim st As Int = ev.Get("Start")
			Dim en As Int = ev.Get("End")
			lbl.Text = ev.Get("Title") & " (" & GetTimeString(st) & _
			" - " & GetTimeString(en) & ")"
			lbl.Color = IdentifyColor(ev.Get("Tags"))
			lbl.TextColor = Colors.Black
			scheduleSV.Panel.AddView(lbl, 10dip, y, scheduleSV.Width - 20dip, 40dip)
			y = y+40dip
		Next
	Next
	scheduleSV.Panel.Height = y
	
End Sub


Sub GetTimeString (h As Int) As String
	Dim num As Int
	Dim ampm As String
	If h = 0 Then
		num = 12
		ampm = "am"
	Else if h = 12 Then
		num = h
		ampm = "pm"
	Else if h > 12 Then
		num = h - 12
		If num = 12 Then
			ampm = "am"
		Else
			ampm = "pm"
		End If
		
	Else
		num = h
		ampm = "am"
	End If
	
	Return num & ":00" & ampm
End Sub


Sub IdentifyColor (typeofevent As String) As Int
	Dim mycolor As Int
	If typeofevent = "Task" Then
		mycolor = Colors.ARGB(255, 0, 191, 255)
	Else if typeofevent = "Event" Then
		mycolor = Colors.ARGB(255, 152, 255, 152)
	Else if typeofevent = "Birthday" Then
		mycolor = Colors.ARGB(255, 255, 182, 193)
	Else if typeofevent = "OOO" Then
		mycolor = Colors.ARGB(255, 255, 215, 0)
		
	End If
	Return mycolor
End Sub

Sub SetDate(Tagdate As String) As String
	
	Dim parts() As String = Regex.Split("-", Tagdate)
	Dim year As String = parts(0)
	Dim monthNum As Int = parts(1)
	Dim day As String = parts(2)
	
	Dim monthName As String
	Select monthNum
		Case 1: monthName = "January"
		Case 2: monthName = "February"
		Case 3: monthName = "March"
		Case 4: monthName = "April"
		Case 5: monthName = "May"
		Case 6: monthName = "June"
		Case 7: monthName = "July"
		Case 8: monthName = "August"
		Case 9: monthName = "September"
		Case 10: monthName = "October"
		Case 11: monthName = "November"
		Case 12: monthName = "December"
	End Select
	
	Dim ts As Long = DateTime.DateParse(Tagdate)
	Dim weekdayNum As Int = DateTime.GetDayOfWeek(ts)
	Dim week As String
	Select weekdayNum
		Case 1: week = "Sunday"
		Case 2: week = "Monday"
		Case 3: week = "Tuesday"
		Case 4: week = "Wednesday"
		Case 5: week = "Thursday"
		Case 6: week = "Friday"
		Case 7: week = "Saturday"
	End Select
	
	Return week & ", " & monthName & " " & day & ", " & year
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Private Sub menu_btn_Click
	menupanel.Visible =True
End Sub


Private Sub sched_btn_Click
	menupanel.visible = False
End Sub

Private Sub Day_btn_Click
	Dim currentyear As Int = DateTime.GetYear(DateTime.Now)
	Dim currentmonth As Int = DateTime.GetMonth(DateTime.Now)
	Dim currentday As Int = DateTime.GetDayOfMonth(DateTime.Now)
	day_module.currentDate = currentyear & "-" & currentmonth & "-" & currentday
	Activity.Finish
	StartActivity(day_module)
End Sub

Private Sub Month_btn_Click
	Activity.Finish
	StartActivity(CalendarActivity)
End Sub