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
	Public kvs As KeyValueStore
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private addTitleTextArea As EditText
	Private listsList As CustomListView
	Private newListBtn As Button
	Private tasksList As CustomListView
	Dim isAddingList As Boolean = False
	
	Dim addTaskBtnPNL As Panel
	Dim addTaskBtn As Button
	
	Private currentList As String = ""
	
	Dim addTaskPanel As Panel
	Dim addTaskTextArea As EditText
	Dim enterTaskBtn As Button
	
	Dim untitledNo As Int = 1
	
	Private progressNumber As Label
	Private progressPercent As Label
	Private progressBar As ProgressBar
	
	Dim pixeltf As Typeface
	pixeltf = Typeface.LoadFromAssets("minecraft.ttf")
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("todoListLayout")
			Else
				Activity.LoadLayout("todoListLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("todoListLayout2")
			Else
				Activity.LoadLayout("todoListLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("todoListLayout3")
			Else
				Activity.LoadLayout("todoListLayoutDark3")
			End If
	End Select
	
	addTitleTextArea.Tag = Null
	addTitleTextArea.Background = Null
	
	newAddTaskBtn
	tasksList.GetBase.Visible = False
	
	kvs = Starter.taskKvs
	
	If kvs.ContainsKey("lists") Then
		Dim savedLists As List = kvs.Get("lists")
		For Each title As String In savedLists
			listsList.AddTextItem(title, title)
		Next
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub newAddTaskBtn
	addTaskBtnPNL.Initialize("addTaskBtnPNL")
	addTaskBtnPNL.SetLayout(10dip, 0dip, 200dip, 70dip)
	addTaskBtnPNL.Color = Colors.ARGB(0, 255, 255, 255)
	addTaskBtn.Initialize("addTaskBtn")
	addTaskBtn.Text = "+ add a task "
	addTaskBtnPNL.AddView(addTaskBtn, 10dip, 20dip, addTaskBtnPNL.Width, 50dip)
	tasksList.Add(addTaskBtnPNL, "")
	
	Dim cd As ColorDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				addTaskBtn.TextColor = Colors.DarkGray
				cd.Initialize(Colors.ARGB(120, 90, 105, 136), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.White
			Else
				addTaskBtnPNL.Color = Colors.ARGB(0, 232, 213, 179)
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.Black
			End If
		Case 1
			If Starter.darkMode Then
				addTaskBtn.TextColor = Colors.DarkGray
				cd.Initialize(Colors.ARGB(120, 90, 105, 136), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.White
			Else
				addTaskBtnPNL.Color = Colors.Transparent
				cd.Initialize(Colors.ARGB(120, 255, 255, 255), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.Black
			End If
		Case 2
			addTaskBtn.Typeface = pixeltf
			If Starter.darkMode Then
				addTaskBtn.TextColor = Colors.DarkGray
				cd.Initialize(Colors.ARGB(120, 90, 105, 136), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.White
			Else
				addTaskBtnPNL.Color = Colors.ARGB(0, 232, 213, 179)
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.Black
			End If
	End Select
	
End Sub

Sub newListBtn_Click
	
	tasksList.Clear
	isAddingList = True
	
	progressNumber.Text = ""
	progressPercent.Text = ""
	progressBar.Progress = 0
	
	addTitleTextArea.Visible = True
	addTitleTextArea.Enabled = True
	addTitleTextArea.Background = Null
	addTitleTextArea.Text = ""
	addTitleTextArea.Hint = "+ add a title..."
	addTitleTextArea.RequestFocus
	addTitleTextArea.Tag = Null
	
	newListBtn.Enabled = False
	
	tasksList.GetBase.Visible = True
	addTaskBtn.Visible = True
	
End Sub

Sub addTitleTextArea_EnterPressed

	' Check if we're renaming an existing list
	If addTitleTextArea.Tag <> Null And addTitleTextArea.Tag Is List Then
		Dim ctx As List = addTitleTextArea.Tag
		Dim oldIndex As Int = ctx.Get(0)
		Dim oldTitle As String = ctx.Get(1)
		Dim newTitle As String = addTitleTextArea.Text.Trim

		If newTitle = "" Or newTitle = oldTitle Then
			addTitleTextArea.Tag = Null
			addTitleTextArea.Text = currentList
			addTitleTextArea.Enabled = False
			Return
		End If

		' Check duplicate
		Dim savedLists As List = kvs.Get("lists")
		For Each existingTitle As String In savedLists
			If existingTitle = newTitle Then
				MsgboxAsync("A list with that name already exists.", "Duplicate title")
				Return
			End If
		Next

		' Update kvs list index
		savedLists.Set(oldIndex, newTitle)
		kvs.Put("lists", savedLists)

		' Migrate task data and checkbox keys
		Dim oldKey As String = "list_" & oldTitle
		Dim newKey As String = "list_" & newTitle
		If kvs.ContainsKey(oldKey) Then
			Dim savedTasks As List = kvs.Get(oldKey)
			For Each task As String In savedTasks
				Dim oldCK As String = "checked_" & oldTitle & "_" & task
				Dim newCK As String = "checked_" & newTitle & "_" & task
				If kvs.ContainsKey(oldCK) Then
					kvs.Put(newCK, kvs.Get(oldCK))
					kvs.Remove(oldCK)
				End If
			Next
			kvs.Put(newKey, savedTasks)
			kvs.Remove(oldKey)
		End If

		' Update currentList if it was open
		If currentList = oldTitle Then
			currentList = newTitle
		End If

		' Rebuild listsList
		listsList.Clear
		Dim savedLists2 As List = kvs.Get("lists")
		For Each t As String In savedLists2
			listsList.AddTextItem(t, t)
		Next

		addTitleTextArea.Tag = Null
		addTitleTextArea.Text = currentList
		addTitleTextArea.Enabled = False
		newListBtn.Enabled = True
		isAddingList = False
		ToastMessageShow("List renamed", False)
		Return
	End If

	' Normal new list creation flow
	Dim title As String = addTitleTextArea.Text

	Dim savedLists As List
	savedLists.Initialize

	If title = "" Then
		title = "Untitled" & untitledNo

		If kvs.ContainsKey("lists") Then
			savedLists = kvs.Get("lists")
			For Each existingTitle As String In savedLists
				If title = existingTitle Then
					untitledNo = untitledNo + 1
					title = "Untitled" & untitledNo
				End If
			Next
		End If

		newListBtn.Enabled = True
	End If

	If kvs.ContainsKey("lists") Then
		Dim savedLists As List = kvs.Get("lists")
		For Each existingTitle As String In savedLists
			If existingTitle = title Then
				MsgboxAsync("List already exists.", "Duplicate title")
				newListBtn.Enabled = True
				addTitleTextArea.Text = ""
				Return
			End If
		Next
	End If

	If kvs.ContainsKey("lists") Then
		savedLists = kvs.Get("lists")
	End If

	savedLists.Add(title)
	kvs.Put("lists", savedLists)

	listsList.AddTextItem(title, title)
	
	currentList = title
	addTitleTextArea.Text = currentList
	addTitleTextArea.Visible = True
	addTitleTextArea.Enabled = False

	tasksList.Clear
	tasksList.GetBase.Visible = True
	newAddTaskBtn

	newListBtn.Enabled = True
	isAddingList = False
	progressNumber.Text = "0 / 0 tasks done!"
	progressPercent.Text = "0%"
	progressBar.Progress = 0

End Sub

Sub listsList_ItemClick(Index As Int, Value As Object)
	
	If isAddingList Then Return
	
	Dim listPNL As B4XView = listsList.GetPanel(Index)
	Dim listLBL As Label = listPNL.GetView(0)
	
	currentList = listLBL.Text
	addTitleTextArea.Text = currentList
	addTitleTextArea.Visible = True
	
	tasksList.Clear
	
	Dim key As String = "list_" & currentList
	
	If kvs.ContainsKey(key) Then
		Dim savedTasks As List = kvs.Get(key)
		For Each task As String In savedTasks
			tasksListUI(task)
		Next
	End If
	
	tasksList.GetBase.Visible = True
	newAddTaskBtn
	updateProgress
	
End Sub

Sub listsList_ItemLongClick(Index As Int, Value As Object)

	Msgbox2Async("Delete or rename this list?", Value, "Rename", "", "Delete", Null, True)
	Wait For Msgbox_Result (res As Int)

	If res = DialogResponse.POSITIVE Then ' Rename
		showRenameListPanel(Index, Value)

	Else If res = DialogResponse.NEGATIVE Then ' Delete
		
		Msgbox2Async("Are you sure you want to delete the list """ & Value & """?", "Confirmation", "No", "", "Yes", Null, True)
		Wait For Msgbox_Result (res As Int)
		If res = DialogResponse.NEGATIVE Then
			Dim savedLists As List = kvs.Get("lists")
			savedLists.RemoveAt(Index)
			kvs.Put("lists", savedLists)
			listsList.RemoveAt(Index)
			ToastMessageShow("List deleted", False)
		End If

		' Clear tasks and checkbox keys for deleted list
		Dim key As String = "list_" & Value
		If kvs.ContainsKey(key) Then
			Dim savedTasks As List = kvs.Get(key)
			For Each task As String In savedTasks
				kvs.Remove("checked_" & Value & "_" & task)
			Next
			kvs.Remove(key)
		End If

		' If deleted list was open, clear task panel
		If currentList = Value Then
			currentList = ""
			tasksList.Clear
			tasksList.GetBase.Visible = False
			addTitleTextArea.Text = ""
			addTitleTextArea.Visible = False
		End If
	End If
	
End Sub

Sub showRenameListPanel(Index As Int, oldTitle As String)
	
	addTitleTextArea.Text = oldTitle
	addTitleTextArea.Visible = True
	addTitleTextArea.Enabled = True
	addTitleTextArea.RequestFocus
	
	Dim ctx As List
	ctx.Initialize
	ctx.Add(Index)
	ctx.Add(oldTitle)
	addTitleTextArea.Tag = ctx
	
End Sub

Sub addTaskBtn_Click
	
	addTaskBtn.Enabled = False
	tasksList.RemoveAt(tasksList.Size - 1)
	
	addTaskPanel.Initialize("addTaskPanel")
	addTaskPanel.SetLayout(10dip, 0, 250dip, 120dip)
	addTaskPanel.Color = Colors.ARGB(0, 255, 255, 255)
	
	addTaskTextArea.Initialize("addTodoText")
	addTaskTextArea.Hint = "Add a task..."
	addTaskTextArea.Tag = Null
	
	enterTaskBtn.Initialize("enterTaskBtn")
	enterTaskBtn.Text = "Enter task"
	
	Dim cd As ColorDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				addTaskTextArea.HintColor = Colors.ARGB(100, 247, 247, 247)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(100, 17, 17, 17)
				addTaskTextArea.TextColor = Colors.Black
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.Black
			End If
		Case 1
			If Starter.darkMode Then
				addTaskTextArea.HintColor = Colors.ARGB(100, 247, 247, 247)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(100, 255, 255, 255), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(255, 137, 162, 185)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(255, 137, 162, 185), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			End If
		Case 2
			addTaskTextArea.Typeface = pixeltf
			enterTaskBtn.Typeface = pixeltf
			If Starter.darkMode Then
				addTaskTextArea.HintColor = Colors.ARGB(100, 247, 247, 247)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 90, 105, 136), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(100, 17, 17, 17)
				addTaskTextArea.TextColor = Colors.Black
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.Black
			End If
	End Select
	
	addTaskPanel.AddView(addTaskTextArea, 0, 0, addTaskBtnPNL.Width, 60dip)
	addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addTaskBtnPNL.Width, 40dip)
	
	tasksList.Add(addTaskPanel, addTaskPanel)
	
End Sub

Sub enterTaskBtn_Click

	Dim newTask As String = addTaskTextArea.Text.Trim
	If newTask = "" Then
		MsgboxAsync("Please enter a task.", "No task entered")
		Return
	End If
	
	Dim key As String = "list_" & currentList
	Dim savedTasks As List
	savedTasks.Initialize
	
	If kvs.ContainsKey(key) Then
		savedTasks = kvs.Get(key)
	End If
	
	For Each existingTask As String In savedTasks
		If existingTask = newTask Then
			MsgboxAsync("A task with that name already exists.", "Duplicate task")
			Return
		End If
	Next

	' Check if renaming
	If addTaskTextArea.Tag <> Null Then
		Dim ctx As List = addTaskTextArea.Tag
		Dim oldTask As String = ctx.Get(1)

		Dim taskIndex As Int = savedTasks.IndexOf(oldTask)
		If taskIndex >= 0 Then
			savedTasks.Set(taskIndex, newTask)
			kvs.Put(key, savedTasks)
		End If

		' Migrate checkbox key
		Dim oldCK As String = "checked_" & currentList & "_" & oldTask
		Dim newCK As String = "checked_" & currentList & "_" & newTask
		If kvs.ContainsKey(oldCK) Then
			kvs.Put(newCK, kvs.Get(oldCK))
			kvs.Remove(oldCK)
		End If

		addTaskTextArea.Tag = Null

		' Rebuild tasksList
		tasksList.Clear
		Dim savedTasks2 As List = kvs.Get(key)
		For Each t As String In savedTasks2
			tasksListUI(t)
		Next
		newAddTaskBtn
		addTaskBtn.Enabled = True
		updateProgress
		ToastMessageShow("Task renamed", False)
		Return
	End If

	' Normal new task flow
	tasksList.RemoveAt(tasksList.Size - 1)

	Dim key As String = "list_" & currentList
	Dim savedTasks As List
	If kvs.ContainsKey(key) Then
		savedTasks = kvs.Get(key)
	Else
		savedTasks.Initialize
	End If
	
	savedTasks.Add(newTask)
	kvs.Put(key, savedTasks)

	tasksListUI(newTask)
	newAddTaskBtn
	addTaskBtn.Enabled = True
	updateProgress

End Sub

Sub tasksList_ItemLongClick(Index As Int, Value As Object)

	If Value = "" Then Return

	Msgbox2Async("Delete or rename this task?", Value, "Rename", "", "Delete", Null, True)
	Wait For Msgbox_Result (res As Int)

	If res = DialogResponse.POSITIVE Then ' Rename
		showRenameTaskPanel(Index, Value)

	Else If res = DialogResponse.NEGATIVE Then ' Delete
		Dim key As String = "list_" & currentList
		Dim savedTasks As List = kvs.Get(key)
		savedTasks.RemoveAt(Index)
		kvs.Put(key, savedTasks)
		kvs.Remove("checked_" & currentList & "_" & Value)
		tasksList.RemoveAt(Index)
		updateProgress
		ToastMessageShow("Task deleted", False)
	End If

End Sub

Sub showRenameTaskPanel(Index As Int, oldTask As String)

	tasksList.RemoveAt(tasksList.Size - 1) ' remove "+ add a task" btn

	addTaskPanel.Initialize("addTaskPanel")
	addTaskPanel.SetLayout(10dip, 0, 250dip, 120dip)
	addTaskPanel.Color = Colors.ARGB(0, 247, 247, 247)

	addTaskTextArea.Initialize("addTodoText")
	addTaskTextArea.Text = oldTask
	Dim ctx As List
	ctx.Initialize
	ctx.Add(Index)
	ctx.Add(oldTask)
	addTaskTextArea.Tag = ctx

	enterTaskBtn.Initialize("enterTaskBtn")
	enterTaskBtn.Text = "Rename task"
	
	Dim cd As ColorDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				addTaskTextArea.HintColor = Colors.ARGB(100, 247, 247, 247)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(100, 17, 17, 17)
				addTaskTextArea.TextColor = Colors.Black
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.Black
			End If
		Case 1
			If Starter.darkMode Then
				addTaskTextArea.HintColor = Colors.ARGB(100, 247, 247, 247)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(100, 17, 17, 17)
				addTaskTextArea.TextColor = Colors.Black
				cd.Initialize(Colors.ARGB(255, 137, 162, 185), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.Black
			End If
		Case 2
			addTaskTextArea.Typeface = pixeltf
			enterTaskBtn.Typeface = pixeltf
			If Starter.darkMode Then
				addTaskTextArea.HintColor = Colors.ARGB(100, 247, 247, 247)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 90, 105, 136), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(100, 17, 17, 17)
				addTaskTextArea.TextColor = Colors.Black
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.Black
			End If
	End Select

	addTaskPanel.AddView(addTaskTextArea, 0, 0, addTaskBtnPNL.Width, 60dip)
	addTaskPanel.AddView(enterTaskBtn, 0, 70dip, addTaskBtnPNL.Width, 40dip)

	tasksList.Add(addTaskPanel, addTaskPanel)

End Sub

Sub taskCheckbox_CheckedChange(Checked As Boolean)
	
	Dim taskCheckbox As CheckBox = Sender
	Dim taskLBL As Label = taskCheckbox.Tag
	If Starter.darkMode Then
		If Checked Then
			taskLBL.TextColor = Colors.ARGB(255, 128, 128, 128)
		Else
			taskLBL.TextColor = Colors.White
		End If
	Else
		If Checked Then
			taskLBL.TextColor = Colors.ARGB(255, 128, 128, 128)
		Else
			taskLBL.TextColor = Colors.Black
		End If
	End If
	

	Dim key As String = "checked_" & currentList & "_" & taskLBL.Text
	kvs.Put(key, Checked)
	
	updateProgress

End Sub

Sub tasksListUI(newTask As String)
	
	Dim taskPNL As Panel
	taskPNL.Initialize("taskPNL")
	taskPNL.SetLayout(0, 0, 250dip, 60dip)
	
	Dim taskCheckbox As CheckBox
	taskCheckbox.Initialize("taskCheckbox")
	taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip, 40dip)
	
	Dim taskLBL As Label
	If Starter.darkMode Then
		taskLBL.Initialize("taskLBL")
		taskLBL.Text = newTask
		taskLBL.TextColor = Colors.White
		taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.Width - 80dip, taskPNL.Height)
	Else
		taskLBL.Initialize("taskLBL")
		taskLBL.Text = newTask
		taskLBL.TextColor = Colors.Black
		taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.Width - 80dip, taskPNL.Height)
	End If
	
	
	Dim divider As Panel
	divider.Initialize("line")
	divider.Color = Colors.ARGB(255, 60, 60, 60)
	taskPNL.AddView(divider, 0, 59dip, taskPNL.Width, 1dip)
	
	taskCheckbox.Tag = taskLBL

	' Restore saved checkbox state
	Dim checkedKey As String = "checked_" & currentList & "_" & newTask
	If kvs.ContainsKey(checkedKey) Then
		Dim isChecked As Boolean = kvs.Get(checkedKey)
		taskCheckbox.Checked = isChecked
		If isChecked Then
			taskLBL.TextColor = Colors.ARGB(50, 0, 0, 0)
		End If
	End If

	tasksList.Add(taskPNL, newTask)
	
End Sub

Sub updateProgress
	
	If currentList = "" Then
		progressNumber.Text = ""
		Return
	End If
	
	Dim key As String = "list_" & currentList
	If kvs.ContainsKey(key) = False Then
		progressNumber.Text = "0 / 0 tasks done!"
		progressPercent.Text = "0%"
		progressBar.Progress = 0
		Return
	End If
	
	
	Dim savedTasks As List = kvs.Get(key)
	Dim totalTasks As Int = savedTasks.Size
	Dim doneTasks As Int = 0
	Dim percentageTasks As Int = 0
	
	For Each task As String In savedTasks
		Dim checkedKey As String = "checked_" & currentList & "_" & task
		If kvs.ContainsKey(checkedKey) Then
			If kvs.Get(checkedKey) = True Then
				doneTasks = doneTasks + 1
			End If
		End If
	Next
	
	percentageTasks = (doneTasks / totalTasks) * 100
	
	progressNumber.Text = doneTasks & " / " & totalTasks & " tasks done!"
	progressPercent.Text = percentageTasks & "%"
	progressBar.Progress = percentageTasks
	
End Sub