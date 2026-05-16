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
	Private xui As XUI
	Dim CalendarMap As Map
	Dim kvs As KeyValueStore
	'ffs
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	
	'Month (int to string) Converter
	Dim Months(12) As String
	Months(0) = "January"
	Months(1) = "February"
	Months(2) = "March"
	Months(3) = "April"
	Months(4) = "May"
	Months(5) = "June"
	Months(6) = "July"
	Months(7) = "August"
	Months(8) = "September"
	Months(9) = "October"
	Months(10) = "November"
	Months(11) = "December"
	
	'Month (int to string) Converter(end)
	
	Private calendarpanel As Panel
	Private weekpanel As Panel
	Dim day_of_week As String
	Private MonthSp As Spinner
	Private YearSP As Spinner
	Private menupanel As Panel
	Private Month_btn As Button
	Dim xOffset As Int = 2dip
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Dim bd As BitmapDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("CalendarActivityLayout")
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				Month_btn.Background = bd
				Month_btn.TextColor = Colors.White
			Else
				Activity.LoadLayout("CalendarActivityLayoutDark")
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				Month_btn.Background = bd
				Month_btn.TextColor = Colors.White
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("CalendarActivityLayout2")
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				Month_btn.Background = bd
				Month_btn.TextColor = Colors.White
			Else
				Activity.LoadLayout("CalendarActivityLayoutDark2")
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				Month_btn.Background = bd
				Month_btn.TextColor = Colors.White
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("CalendarActivityLayout3")
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				Month_btn.Background = bd
				Month_btn.TextColor = Colors.White
			Else
				Activity.LoadLayout("CalendarActivityLayoutDark3")
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtnd.png"))
				Month_btn.Background = bd
				Month_btn.TextColor = Colors.White
			End If
	End Select
	
	kvs = Starter.calKvs
	CalendarMap = Starter.calendarMap
	
	For Each datekey As String In CalendarMap.Keys
		Dim dates As Map = CalendarMap.Get(datekey)
		Dim timeline As List = dates.Get("Timeline")
		For i = 0 To timeline.Size -1
			Dim ev As Map = timeline.Get(i)
			If ev.ContainsKey("ID") = False Then
				ev.Put("ID", DateTime.Now + Rnd(0,1000))
			End If
		Next
	Next
	kvs.Put("CalendarKVS", CalendarMap)
	
	'Year Spinner
	Dim currentyear As Int = DateTime.GetYear(DateTime.Now)
	Dim years As List
	years.Initialize
	For i = currentyear -10 To currentyear+10
		years.Add(i)
	Next
	YearSP.AddAll(years)
	YearSP.SelectedIndex = 10
	If Starter.darkMode = True Then
		YearSP.DropdownBackgroundColor = Colors.Black
	End If
	'Year Spinner(end)
	'Month Spinner
	MonthSp.AddAll(Months)
	MonthSp.SelectedIndex = DateTime.GetMonth(DateTime.Now) -1
	If Starter.darkMode = True Then
		MonthSp.DropdownBackgroundColor = Colors.Black
	End If
	'Month Spinner (end)
	
	DrawCalendar(DateTime.GetMonth(DateTime.Now), DateTime.GetYear(DateTime.Now))
	
	'table for weeks
	Dim cd As ColorDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			Else
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			End If
		Case 1
			If Starter.darkMode Then
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			Else
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			End If
		Case 2
			If Starter.darkMode Then
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.RGB(201, 205, 211))
			Else
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.RGB(73, 44, 46))
			End If
	End Select
	
	
	Dim column As Int = 7
	Dim cellW As Int = calendarpanel.Width/column
	Dim cellH As Int = 60dip
	For c = 0 To column -1
		Dim celllabel As Label
		celllabel.Initialize("cell_label")
		Weekday(c)
		celllabel.Text = day_of_week
		celllabel.TextSize = 14
		
		If Starter.darkMode = False Then
			celllabel.TextColor = Colors.black
		Else
			celllabel.TextColor = Colors.White
		End If
		
		celllabel.Gravity = Gravity.CENTER
		weekpanel.AddView(celllabel, c*cellW + xOffset, xOffset, cellW, cellH)
		
		
		celllabel.Background = cd
	Next
	'table for weeks (end)

	
	
End Sub

Sub CleanDebugger
	CalendarMap.Clear
	kvs.Put("CalendarKVS", CalendarMap)
End Sub

Sub DrawCalendar (month As Int, year As Int)
	
	calendarpanel.RemoveAllViews
	
	'the date and time
	DateTime.DateFormat = "yyyy-MM-dd"
	Dim firstDay As Long = DateTime.DateParse(year & "-" & month & "-01")
	Dim startDay As Int = DateTime.GetDayOfweek(firstDay)
	Dim daysInmonth As Int = Daysinamonth(month, year)
	'the date and time (end)
	
	'getprev month
	Dim prevmonth As Int
	Dim prevyear As Int
	
	If month = 1 Then
		prevmonth = 12
		prevyear = year - 1
	Else
		prevmonth = month-1
		prevyear = year
	End If
	Dim daysInprevMonth As Int = Daysinamonth(prevmonth, prevyear)
	'getprev month(end)
	
	'creating tables
	Dim cd As ColorDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			Else
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			End If
		Case 1
			If Starter.darkMode Then
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			Else
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.Black)
			End If
		Case 2
			If Starter.darkMode Then
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.RGB(201, 205, 211))
			Else
				cd.Initialize2(Colors.Transparent, 0, 2dip, Colors.RGB(73, 44, 46))
			End If
	End Select
	
	Dim rows As Int = 6  'number of rows (days)
	Dim column As Int = 7 'number of columns (the weeks)
	
	Dim day As Int = 1
	Dim started As Boolean = False
	
	Dim index As Int = 0
	
	Dim cellW As Int = calendarpanel.width / column
	Dim cellH As Int = 60dip
	For r = 0 To rows -1
		For c = 0 To column -1
			
			Dim cell As Panel
			cell.Initialize("cell_click")
			cell.Enabled  =True
			
			calendarpanel.AddView(cell, (c * cellW) + xOffset, (r * cellH) + xOffset, cellW, cellH)
			cell.Background = cd
			
			Dim lbl As Label
			lbl.Initialize("")
			lbl.Gravity = Gravity.CENTER_Horizontal
			lbl.Enabled = False
			
			index = index +1
			
			Dim displayday As Int
			Dim datestr As String
			
		
			If index < startDay Then
				displayday = daysInprevMonth - (startDay - index) +1
				lbl.TextColor = Colors.gray
				datestr = prevyear & "-" & NumberFormat(prevmonth,2,0) & "-" & NumberFormat(displayday,2 ,0 )
			Else if index >= startDay And index < startDay + daysInmonth Then
				displayday = index - startDay + 1
				
				If Starter.darkMode = False Then
					lbl.TextColor = Colors.Black
				Else
					lbl.TextColor = Colors.White
				End If
				
				datestr = year&"-"&NumberFormat(month,2,0)&"-"&NumberFormat(displayday,2,0)
			Else
				displayday = index - (startDay + daysInmonth) + 1
				lbl.TextColor = Colors.Gray
				Dim nextmonth As Int
				Dim nextyear As Int
			
				If month = 12 Then
					nextmonth = 1
					nextyear = year+1
				Else
					nextmonth = month+1
					nextyear = year
					datestr = nextyear&"-"&NumberFormat(nextmonth,2,0)&"-"&NumberFormat(displayday,2,0)
				End If
			End If
			
			
			lbl.Text = displayday
			cell.Tag = datestr
			lbl.Enabled = False
			cell.AddView(lbl, 0, 0, cellW, cellH)
			
			If CalendarMap.ContainsKey(datestr) Then
				Dim eventmap As Map = CalendarMap.Get(datestr)
				Dim allevents As List = eventmap.Get("AllEvents")
				
				Dim maxShow As Int = Min(allevents.Size, 2)
				Dim yoffset As Int = 20dip
				
				For i = 0 To maxShow -1
					Dim ev As Map = allevents.Get(i)
					
					Dim dot As Label
					dot.Initialize("")
					dot.Text = ev.Get("Title")
					dot.TextSize = 8
					dot.TextColor = Colors.white
					dot.Color = IdentifyColor(ev.Get("Tags"))
					dot.Gravity = Gravity.CENTER_VERTICAL
					dot.SingleLine = True
					dot.Ellipsize = "END"
					cell.AddView(dot, 2dip, yoffset +i*12dip, cellW - 4dip, 10dip)
				Next
				
				If allevents.Size > 2 Then
					Dim morelbl As Label
					morelbl.Initialize("")
					morelbl.Text = "+" & (allevents.Size -2)
					morelbl.TextSize = 8
					morelbl.TextColor = Colors.black
					cell.AddView(morelbl, 2dip , yoffset + 2 * 12dip, cellW, 10dip)
				End If
			End If
		Next
	Next
	
	'creating tables (end)
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


Sub cell_click_click
	'IF cell clicks
	Dim p As Panel = Sender
	Dim datestr As String = p.Tag
	Activity.finish
	StartActivity(day_module)
	day_module.currentDate = datestr
	Log(datestr)
	'if cell clicks (end)
End Sub


Sub Daysinamonth(Month As Int, year As Int) As Int
	Dim nextMonth As Int
	Dim nextyear As Int
	
	If Month = 12 Then
		nextMonth = 1
		nextyear = year+1
	Else
		nextMonth = Month +1
		nextyear = year
	End If
	
	DateTime.DateFormat = "yyyy-MM-dd"
	Dim firstNextMonth As Long = DateTime.DateParse(nextyear & "-"& NumberFormat(nextMonth, 2, 0) & "-01")
	Dim lastDayDate As Long = firstNextMonth - DateTime.TicksPerDay
	
	
	Return DateTime.GetDayOfMonth(lastDayDate)
	
End Sub

Sub Weekday (Day As Int)
	If Day = 0 Then
		day_of_week = "Sun"
	Else if Day = 1 Then
		day_of_week = "Mon"
	Else if Day = 2 Then
		day_of_week = "Tue"
	Else if Day = 3 Then
		day_of_week = "Wed"
	Else if Day = 4 Then
		day_of_week = "Thu"
	Else if Day = 5 Then
		day_of_week = "Fri"
	Else
		day_of_week = "Sat"
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub MonthSp_ItemClick (Position As Int, Value As Object)
	Dim selectedmonth As Int = Position +1
	
	DrawCalendar(selectedmonth, DateTime.GetYear(DateTime.Now))
End Sub

Private Sub YearSP_ItemClick (Position As Int, Value As Object)
	Dim selectedyear As Int = Value
	
	DrawCalendar(MonthSp.SelectedIndex +1, selectedyear)
End Sub

Private Sub menu_btn_Click
	menupanel.Visible =True
End Sub

Private Sub sched_btn_Click
	Activity.Finish
	StartActivity(Schedule_module)
End Sub

Private Sub Day_btn_Click
	If day_module.currentDate = "" Then
		Dim currentyear As Int = DateTime.GetYear(DateTime.Now)
		Dim currentmonth As Int = DateTime.GetMonth(DateTime.Now)
		Dim currentday As Int = DateTime.GetDayOfMonth(DateTime.Now)
		Dim datestr As String= currentyear & "-" & NumberFormat(currentmonth,2, 0) & "-" & NumberFormat(currentday,2,0)
		day_module.currentDate = datestr
		Log(day_module.currentDate)
	End If
	
	Activity.Finish
	StartActivity(day_module)
End Sub

Private Sub Month_btn_Click
	menupanel.Visible = False
End Sub