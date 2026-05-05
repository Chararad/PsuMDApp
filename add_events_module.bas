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
	Dim eventtype As String
	Dim currentDate As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private taskrb As RadioButton
	Private eventrb As RadioButton
	Private birthdayrb As RadioButton
	Private ooorb As RadioButton
	Private timelbl As Label
	Private title_et As EditText
	Private description_et As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("AEMLayout")
			Else
				Activity.LoadLayout("AEMLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("AEMLayout2")
			Else
				Activity.LoadLayout("AEMLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("AEMLayout3")
			Else
				Activity.LoadLayout("AEMLayoutDark3")
			End If
	End Select
	
	timelbl.Text = currentDate

End Sub

Sub Activity_Resume	
	If eventtype = "Event" Then
		eventrb.Checked = True
	Else if eventtype = "Task" Then
		taskrb.Checked = True
	Else if eventtype = "Birthday" Then
		birthdayrb.Checked = True
	Else if eventtype = "OOO" Then
		ooorb.Checked = True
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

	eventrb.Checked = False
	taskrb.Checked = False
	birthdayrb.Checked = False
	ooorb.Checked = False
End Sub


Private Sub x_btn_Click
	Activity.Finish
End Sub

Sub SaveCalendar
	CalendarActivity.kvs.put("CalendarKVS", CalendarActivity.CalendarMap)
End Sub

Sub MapInitializer As Map
	Dim eventmap As Map
	
	eventmap.Initialize
	Dim allevents As List
	allevents.initialize
	
	Dim timeline As List
	timeline.initialize
	
	eventmap.Put("AllEvents", allevents)
	eventmap.Put("Timeline", timeline)
		
	CalendarActivity.CalendarMap.Put(day_module.currentDate, eventmap)
	
	Return eventmap
End Sub

Private Sub save_btn_Click
	Dim eventmap As Map
	If title_et.text = "" Then
		MsgboxAsync("Enter The Event Title", "Error")
		Return
	End If
	
	If CalendarActivity.CalendarMap.ContainsKey(day_module.currentDate) Then
		eventmap = CalendarActivity.CalendarMap.Get(day_module.currentDate)
	Else
		eventmap = MapInitializer
	End If
	
	Dim getAllevents As List = eventmap.Get("AllEvents")
	Dim putevent As Map
	putevent.Initialize
	putevent.Put("Title", title_et.Text)
	putevent.Put("Description", description_et.Text)
	putevent.Put("Tags", eventtype)
	
	getAllevents.Add(putevent)
	SaveCalendar
	day_module.addeventsfeedback = True
	Activity.finish
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