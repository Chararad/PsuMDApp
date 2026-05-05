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
	Dim currentDate As String
	Dim addeventsfeedback As Boolean
	Dim currentIndex As Int
	Dim schedules As Map
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private Day_btn As Button
	Private menupanel As Panel
	Private Svtimeline As ScrollView
	Private date_todaylbl As Label
	Private addpanel As Panel
	Private Addtask_btn As Button
	Private svEvents As ScrollView
	Private eventdescription_lbl As Label
	Private eventTitle_lbl As Label
	Private tags_lbl As Label
	Private dateToday_lbl As Label
	Private eventInfo_panel As Panel
	Private EditInfoPanel As Panel
	
	Dim currenttaggedEvent As Map
	Dim timeIndex As Int
	Dim eventtype As String
	Dim currentevId As Long
	
	Private editTitle_et As EditText
	Private editDescription_et As EditText
	Private deletepanel As Panel
	Private addTL_et As EditText
	Private addEventTL_panel As Panel
	Private timelabel As Label
	Private deleteTLevent_confirmationpanel As Panel
	Private starttimelineSP As Spinner
	Private endtimelineSP As Spinner
	Private taskrb As RadioButton
	Private eventrb As RadioButton
	Private birthdayrb As RadioButton
	Private ooorb As RadioButton
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("Day_ModuleLayout")
			Else
				Activity.LoadLayout("Day_ModuleLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("Day_ModuleLayout2")
			Else
				Activity.LoadLayout("Day_ModuleLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("Day_ModuleLayout3")
			Else
				Activity.LoadLayout("Day_ModuleLayoutDark3")
			End If
	End Select
	
	If Starter.darkMode = False Then
		starttimelineSP.DropdownBackgroundColor = Colors.White
		starttimelineSP.DropdownTextColor = Colors.Black
		endtimelineSP.DropdownBackgroundColor = Colors.White
		endtimelineSP.DropdownTextColor = Colors.Black
	Else
		starttimelineSP.DropdownBackgroundColor = Colors.DarkGray
		starttimelineSP.DropdownTextColor = Colors.White
		endtimelineSP.DropdownBackgroundColor = Colors.DarkGray
		endtimelineSP.DropdownTextColor = Colors.White
	End If
	
	Day_btn.Color = Colors.LightGray
	date_todaylbl.Text = SetDate(currentDate)
	add_events_module.currentDate = SetDate(currentDate)
	SetUpSpinners
	
	Log(currentDate)
	
End Sub

Sub SetUpSpinners
	Dim hours As List
	hours.Initialize
	For i = 0 To 24
		hours.Add(GetTimeString(i))
	Next
	starttimelineSP.AddAll(hours)
	endtimelineSP.AddAll(hours)
End Sub

Sub SaveCalendar
	CalendarActivity.kvs.put("CalendarKVS", CalendarActivity.CalendarMap)
End Sub

Sub DrawMainEvents
	svEvents.Panel.RemoveAllViews
	If Not(CalendarActivity.CalendarMap.ContainsKey(currentDate)) Then
		Return
	End If
	
	Dim eventmap As Map = CalendarActivity.CalendarMap.Get(currentDate)
	Dim allevents As List = eventmap.Get("AllEvents")
	
	Dim y As Int = 0
	Dim rowHeight As Int = 50dip
	For i = 0 To allevents.Size -1
		Dim lbl As Label
		Dim ev As Map = allevents.Get(i)
		lbl.Initialize("mainEvent")
		lbl.Tag = i
		lbl.Text = ev.Get("Title")
		lbl.Gravity = Gravity.CENTER_vertical
		lbl.TextColor = Colors.Black
		lbl.Color = IdentifyColor(ev.Get("Tags"))
		
		svEvents.Panel.AddView(lbl, 0, y, svEvents.Width, rowHeight)
		y = y+rowHeight
	Next
	
End Sub

Sub mainEvent_click
	Dim eventmap As Map = CalendarActivity.CalendarMap.Get((currentDate))
	Dim allevents As List = eventmap.Get("AllEvents")
	Dim lbl As Label = Sender
	Dim ev As Map = allevents.get(lbl.Tag)
	currenttaggedEvent = ev
	currentIndex = lbl.tag

	eventInfo_panel.Visible = True
	dateToday_lbl.Text = currentDate
	eventTitle_lbl.Text = ev.get("Title")
	eventdescription_lbl.Text = ev.Get("Description")
	tags_lbl.text = ev.Get("Tags")
	
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

Sub UpdateTimeLine
	DrawMainEvents
	DrawHourLabels
	DrawTimelineEvents
End Sub

Sub DrawHourLabels
	
	
	Svtimeline.Panel.RemoveAllViews
	
	Dim rowh As Int = 60dip
	Svtimeline.Panel.Height = 24 * rowh
	
	
	If Starter.darkMode = False Then
		For h = 0 To 23
		Dim p As Panel
		p.Initialize("hour")
		p.Tag = h
		Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimeline.width, rowh)
			
		Dim lbl As Label
		lbl.Initialize("")
		lbl.Text = GetTimeString(h)
			lbl.TextColor = Colors.DarkGray
			lbl.Gravity = Gravity.left
			p.AddView(lbl, 0, 0, 60dip, rowh)
		Next
	Else
		For h = 0 To 23
			Dim p As Panel
			p.Initialize("hour")
			p.Tag = h
			Svtimeline.Panel.AddView(p, 0, h*rowh, Svtimeline.width, rowh)
			
			Dim lbl As Label
			lbl.Initialize("")
			lbl.Text = GetTimeString(h)
			lbl.TextColor = Colors.White
			lbl.Gravity = Gravity.left
			p.AddView(lbl, 0, 0, 60dip, rowh)
		Next
	End If
		
		
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

Sub DrawTimelineEvents
	If Not(CalendarActivity.CalendarMap.ContainsKey(currentDate)) Then Return

	Dim eventmap As Map = CalendarActivity.CalendarMap.Get(currentDate)
	Dim timeline As List = eventmap.Get("Timeline")
    
	Dim rowh As Int = 60dip
    
	For h = 0 To 23
		Dim hourPanel As Panel = Svtimeline.Panel.GetView(h) ' the panel you added in DrawHourLabels
		' Only remove the event label (assume index 0 is the time label)
		If hourPanel.NumberOfViews > 1 Then
			hourPanel.RemoveViewAt(1)
		End If
	Next
        
	For Each ev  As Map In timeline
		Dim starth As Int = ev.Get("Start")
		Dim endh As Int = ev.Get("End")
		Dim title As String = ev.Get("Title")
		Dim color As Int = IdentifyColor(ev.Get("Tags"))
		For h = starth To endh - 1
			If h >= 0 And h <= 23 Then
				Dim hourPanel As Panel = Svtimeline.Panel.GetView(h)
				Dim lbl As Label
				lbl.initialize("TimelineEvent")
				lbl.Tag = ev
				lbl.Text = title
				lbl.TextColor = Colors.Black
				lbl.Gravity = Gravity.CENTER_VERTICAL
				lbl.Color = color
				hourPanel.AddView(lbl, 65dip, 0, hourPanel.Width - 70dip, rowh)
			End If
		Next
	Next
		
End Sub

Sub timelineEvent_Click
	Dim lbl As Label = Sender
	Dim ev As Map = lbl.Tag
	currenttaggedEvent = ev
	currentevId = ev.Get("ID")
    
	' Show the add/edit panel
	addEventTL_panel.Visible = True
    
	' Populate fields with existing event data
	addTL_et.Text = ev.Get("Title")
	starttimelineSP.SelectedIndex = ev.Get("Start")
	endtimelineSP.SelectedIndex = ev.Get("End")
    
	' Set the correct radio button
	Select Case ev.Get("Tags")
		Case "Task": taskrb.Checked = True
		Case "Event": eventrb.Checked = True
		Case "Birthday": birthdayrb.Checked = True
		Case "OOO": ooorb.Checked = True
	End Select
End Sub

Sub hour_click
	Dim p As Panel =  Sender
	Dim tappedIndex = p.tag
	timeIndex = p.tag
	
	addEventTL_panel.Visible  =True
	
	Dim eventmap As Map
	If CalendarActivity.CalendarMap.ContainsKey(currentDate) Then
		eventmap = CalendarActivity.CalendarMap.Get(currentDate)
	Else
		eventmap = MapInitializer
	End If
	Dim timeline As List
	If eventmap.containskey("Timeline") Then
		timeline = eventmap.Get("Timeline")
	Else
		timeline.Initialize
		eventmap.Put("Timeline", timeline)
	End If
	
	starttimelineSP.SelectedIndex = timeIndex
	endtimelineSP.SelectedIndex = Min(timeIndex +1, 24)
	eventrb.Checked = True
	
	
	
End Sub


'newly paste wip
Sub MapInitializer As Map
	Dim eventmap As Map
	
	eventmap.Initialize
	Dim allevents As List
	allevents.initialize
	
	Dim timeline As List
	timeline.initialize
	
	eventmap.Put("AllEvents", allevents)
	eventmap.Put("Timeline", timeline)
		
	CalendarActivity.CalendarMap.Put(currentDate, eventmap)
	
	Return eventmap
End Sub

Private Sub saveTL_btn_Click
	If starttimelineSP.SelectedIndex = endtimelineSP.SelectedIndex  Or starttimelineSP.SelectedIndex > endtimelineSP.selectedindex Then
		MsgboxAsync("Invalid Timeline", "Error")
		Return
	End If
	Dim eventmap As Map = CalendarActivity.CalendarMap.Get(currentDate)
	If addTL_et.text = "" Then
		MsgboxAsync("Event must have a name", "Error")
		Return
	End If
	
	If CalendarActivity.CalendarMap.ContainsKey(currentDate) Then
		eventmap = CalendarActivity.CalendarMap.Get(currentDate)
	Else
		eventmap = MapInitializer
	End If
	Dim timeline As List = eventmap.Get("Timeline")
	Dim ev As Map
	ev.Initialize
	
	ev.Put("ID", DateTime.Now)
	ev.Put("Title", addTL_et.Text)
	ev.Put("Start", starttimelineSP.SelectedIndex)
	ev.Put("End", endtimelineSP.SelectedIndex)
	ev.Put("Tags", eventtype)
	
	
	For i = timeline.Size - 1 To 0 Step -1
		Dim existing As Map = timeline.Get(i)
		Dim st As Int = existing.Get("Start")
		Dim en As Int = existing.Get("End")
    
		
		If (starttimelineSP.SelectedIndex < en) And (endtimelineSP.SelectedIndex > st) Then
			timeline.RemoveAt(i)
			Exit
			
		End If
	Next
	timeline.add(ev)
	
	SaveCalendar
	UpdateTimeLine
	addEventTL_panel.Visible  = False
	
End Sub
'newly paste wip (end)

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
	UpdateTimeLine
	If addeventsfeedback = True Then
		addeventsfeedback = False
		MsgboxAsync("Event Saved", "Saved")
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub menu_btn_Click
	menupanel.Visible =True
End Sub

Private Sub sched_btn_Click
	Activity.Finish
	StartActivity(Schedule_module)
End Sub

Private Sub Day_btn_Click
	menupanel.Visible = False
End Sub

Private Sub Month_btn_Click
	Activity.Finish
	StartActivity(CalendarActivity)
End Sub

Private Sub Addevent_btn_Click
	addpanel.Visible = False
	add_events_module.eventtype = "Event"
	StartActivity(add_events_module)
End Sub

Private Sub addnew_btn_Click
	
	If addpanel.Visible =True Then
		addpanel.Visible = False
		Return
	End If
	addpanel.Visible = True
	
	
End Sub

Private Sub Addtask_btn_Click
	addpanel.Visible = False
	add_events_module.eventtype = "Task"
	StartActivity(add_events_module)
End Sub

Private Sub birthday_btn_Click
	addpanel.Visible = False
	add_events_module.eventtype = "Birthday"
	StartActivity(add_events_module)
End Sub

Private Sub ooo_btn_Click
	addpanel.Visible = False
	add_events_module.eventtype = "OOO"
	StartActivity(add_events_module)
End Sub

Private Sub editeventinfo_btn_Click
	EditInfoPanel.Visible = True
	editTitle_et.text = currenttaggedEvent.Get("Title")
	editDescription_et.Text = currenttaggedEvent.Get("Description")
End Sub

Private Sub x_EventInfo_btn_Click
	eventInfo_panel.Visible = False
	EditInfoPanel.visible = False
End Sub

Private Sub DeleteEvent_btn_Click
	deletepanel.Visible = True
End Sub

Private Sub cancelEdit_btn_Click
	EditInfoPanel.Visible = False
End Sub

Private Sub saveEdit_btn_Click
	If starttimelineSP.SelectedIndex = endtimelineSP.SelectedIndex  Or starttimelineSP.SelectedIndex > endtimelineSP.selectedindex Then
		MsgboxAsync("Invalid Timeline", "Error")
		Return
	End If
	
	If editTitle_et.text = "" Then
		MsgboxAsync("Event must have name", "Error")
		Return
	End If
	currenttaggedEvent.Put("Title", editTitle_et.Text)
	currenttaggedEvent.Put("Description", editDescription_et.Text)
	
	x_EventInfo_btn_Click
	eventTitle_lbl.text = editTitle_et.Text
	eventdescription_lbl.Text = editDescription_et.text
	eventInfo_panel.Visible = True
	SaveCalendar
	DrawMainEvents
	
	
End Sub

Private Sub cancelDelete_btn_Click
	deletepanel.Visible = False
End Sub

Private Sub confirmdelete_btn_Click
	Dim eventmap As Map = CalendarActivity.CalendarMap.get(currentDate)
	Dim allevents As List = eventmap.Get("AllEvents")
	allevents.RemoveAt(currentIndex)
	deletepanel.Visible = False
	eventInfo_panel.Visible = False
	SaveCalendar
	DrawMainEvents
End Sub

Private Sub x_TLevent_btn_Click
	addEventTL_panel.Visible = False
	addTL_et.text = ""
End Sub

Private Sub deleteTLevent_btn_Click
	deleteTLevent_confirmationpanel.Visible = True
End Sub

Private Sub cancelTLdelete_btn_Click
	deleteTLevent_confirmationpanel.Visible = False
End Sub

Private Sub deleteTLconfirm_btn_Click
	Dim eventmap As Map = CalendarActivity.CalendarMap.Get(currentDate)
	Dim timeline As List = eventmap.Get("Timeline")
	
	For i = timeline.Size -1 To 0 Step -1
		Dim ev As Map = timeline.Get(i)
		If ev.Get("ID") = currentevId Then
			timeline.RemoveAt(i)
			Exit
		End If
		
	Next
	addTL_et.Text = ""
	deleteTLevent_confirmationpanel.Visible = False
	addEventTL_panel.Visible = False
	SaveCalendar
	UpdateTimeLine
End Sub


Private Sub ooorb_CheckedChange(Checked As Boolean)
	eventtype = "OOO"
End Sub

Private Sub birthdayrb_CheckedChange(Checked As Boolean)
	eventtype = "Birthday"
End Sub

Private Sub eventrb_CheckedChange(Checked As Boolean)
	eventtype = "Event"
End Sub

Private Sub taskrb_CheckedChange(Checked As Boolean)
	eventtype = "Task"
End Sub