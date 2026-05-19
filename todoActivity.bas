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
	Public kvs As KeyValueStore
End Sub

Sub Globals
	
	'these things are for solo lists
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
	'until here

	Private TabHost1 As TabHost
	
	'group
	Private groupList As CustomListView
	Private addTitleTextAreaGrp As EditText
	Private listsListGrp As CustomListView
	Private newGroupBtn As Button
	Private newListBtnGrp As Button
	Private progressBarGrp As ProgressBar
	Private progressNumberGrp As Label
	Private progressPercentGrp As Label
	Private tasksListGrp As CustomListView
	Private groupET As EditText
	Private currentGrpListName As String = ""
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("MAINtodolistlayout")
	
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				TabHost1.AddTab("Your Lists", "todolistLayout.bal")
				TabHost1.AddTab("Groups", "grouptodolistlayout.bal")
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
	
	loadMyGroups

End Sub

'solo list

Sub newAddTaskBtn
	addTaskBtnPNL.Initialize("addTaskBtnPNL")
	addTaskBtnPNL.SetLayout(10dip, 0dip, 190dip, 70dip)
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
				cd.Initialize(Colors.ARGB(255, 59, 117, 151), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.White
			Else
				addTaskBtnPNL.Color = Colors.Transparent
				cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200) ' 200 = corner radius
				addTaskBtn.Background = cd
				addTaskBtn.TextColor = Colors.White
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
	addTaskPanel.SetLayout(10dip, 0, 20dip, 120dip)
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
				cd.Initialize(Colors.ARGB(255, 59, 117, 151), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
			Else
				addTaskTextArea.HintColor = Colors.ARGB(120, 184, 120, 46)
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 98, 43, 20), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
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
	addTaskPanel.SetLayout(10dip, 0, 240dip, 120dip)
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
				addTaskTextArea.TextColor = Colors.White
				cd.Initialize(Colors.ARGB(120, 184, 120, 46), 200) ' 200 = corner radius
				enterTaskBtn.Background = cd
				enterTaskBtn.TextColor = Colors.White
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

'until here solo list




'this one for the group

'loads all groups the current user is a member of into groupList
Sub loadMyGroups
	groupList.Clear
	groupET.Text = ""
	groupET.Enabled = False
	listsListGrp.Clear
	listsListGrp.GetBase.Visible = False
	tasksListGrp.Clear
	tasksListGrp.GetBase.Visible = False
	progressBarGrp.Progress = 0
	progressNumberGrp.Text = ""
	progressPercentGrp.Text = ""

	If kvs.ContainsKey("groups") = False Then Return

	Dim allGroups As List = kvs.Get("groups")
	For Each code As String In allGroups
		Dim membersKey As String = "group_members_" & code
		If kvs.ContainsKey(membersKey) Then
			Dim members As List = kvs.Get(membersKey)
			Dim myID As String = Starter.currentUserID ' however you store the logged-in user's ID
			For Each m As String In members
				If m = myID Then
					Dim groupName As String = kvs.Get("group_name_" & code)
					groupList.AddTextItem(groupName, code)
					Exit
				End If
			Next
		End If
	Next
End Sub

Sub newGroupBtn_Click
	Msgbox2Async("Join or create a group?", "Groups", "Create", "", "Join", Null, True)
	Wait For Msgbox_Result (res As Int)

	If res = DialogResponse.POSITIVE Then
		' Create group
		showCreateGroupPanel
	Else If res = DialogResponse.NEGATIVE Then
		' Join group — show input panel inside groupList
		showJoinGroupPanel
	End If
End Sub

Sub showCreateGroupPanel
	' Reuse groupET as the input field
	groupET.Text = ""
	groupET.Hint = "Enter group name..."
	groupET.Enabled = True
	groupET.Tag = "creating"
	groupET.RequestFocus
End Sub

Sub groupET_EnterPressed
	If groupET.Tag Is List Then
		' Renaming an existing group
		Dim ctx As List = groupET.Tag
		Dim code As String = ctx.Get(1)
		Dim newName As String = groupET.Text.Trim
		If newName = "" Then
			groupET.Text = kvs.Get("group_name_" & code)
			groupET.Enabled = False
			groupET.Tag = code
			Return
		End If
		kvs.Put("group_name_" & code, newName)
		groupET.Enabled = False
		groupET.Tag = code
		loadMyGroups
		groupET.Text = newName
		ToastMessageShow("Group renamed", False)

	Else If groupET.Tag = "creating" Then
		' Creating a new group
		Dim groupName As String = groupET.Text.Trim
		If groupName = "" Then
			MsgboxAsync("Please enter a group name.", "No name")
			Return
		End If

		Dim code As String = generateGroupCode(6)

		Dim allGroups As List
		allGroups.Initialize
		If kvs.ContainsKey("groups") Then allGroups = kvs.Get("groups")
		allGroups.Add(code)
		kvs.Put("groups", allGroups)

		kvs.Put("group_name_" & code, groupName)
		kvs.Put("group_owner_" & code, Starter.currentUserID)

		Dim members As List
		members.Initialize
		members.Add(Starter.currentUserID)
		kvs.Put("group_members_" & code, members)

		groupET.Tag = Null
		groupET.Enabled = False

		ToastMessageShow("Group """ & groupName & """ created! Code: " & code, True)
		loadMyGroups
	End If
End Sub

' Generates a random 6-char alphanumeric code, checks for uniqueness
Sub generateGroupCode (length As Int) As String
	Dim chars As String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	Dim code As String = ""
	For i = 0 To length - 1
		code = code & (chars.CharAt(Rnd(0,chars.Length)))
	Next
	Return code
End Sub

Sub isCodeUnique(code As String) As Boolean
	If kvs.ContainsKey("groups") = False Then Return True
	Dim allGroups As List = kvs.Get("groups")
	For Each c As String In allGroups
		If c = code Then Return False
	Next
	Return True
End Sub

Sub showJoinGroupPanel
	' Add a small panel into groupList for code input
	Dim joinPNL As Panel
	joinPNL.Initialize("joinPNL")
	joinPNL.SetLayout(0, 0, 250dip, 120dip)
	joinPNL.Color = Colors.Transparent

	Dim joinET As EditText
	joinET.Initialize("joinET")
	joinET.Hint = "Enter 6-char group code..."

	Dim joinBtn As Button
	joinBtn.Initialize("joinBtn")
	joinBtn.Text = "Join Group"

	Dim cd As ColorDrawable
	cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)
	joinBtn.Background = cd
	joinBtn.TextColor = Colors.White

	joinPNL.AddView(joinET, 0, 0, 250dip, 55dip)
	joinPNL.AddView(joinBtn, 0, 65dip, 250dip, 40dip)

	' Store joinET ref in joinBtn tag so we can read it on click
	joinBtn.Tag = joinET

	groupList.Add(joinPNL, "joinPanel")
End Sub

Sub joinBtn_Click
	Dim joinBtn As Button = Sender
	Dim joinET As EditText = joinBtn.Tag
	Dim code As String = joinET.Text.Trim.ToUpperCase

	If code.Length <> 6 Then
		MsgboxAsync("Code must be exactly 6 characters.", "Invalid code")
		Return
	End If

	If kvs.ContainsKey("group_name_" & code) = False Then
		MsgboxAsync("No group found with that code.", "Not found")
		Return
	End If

	' Check if already a member
	Dim membersKey As String = "group_members_" & code
	Dim members As List = kvs.Get(membersKey)
	For Each m As String In members
		If m = Starter.currentUserID Then
			MsgboxAsync("You are already in this group.", "Already joined")
			Return
		End If
	Next

	members.Add(Starter.currentUserID)
	kvs.Put(membersKey, members)

	' Remove join panel from groupList
	' Find it by value
	For i = 0 To groupList.Size - 1
		Dim pnl As B4XView = groupList.GetPanel(i)
		' the join panel is the last item; just remove last
	Next
	groupList.RemoveAt(groupList.Size - 1)

	Dim groupName As String = kvs.Get("group_name_" & code)
	ToastMessageShow("Joined group: " & groupName, True)
	loadMyGroups
End Sub

Sub groupList_ItemClick(Index As Int, Value As Object)
	Dim code As String = Value
	If code = "joinPanel" Then Return

	' Set groupET as label showing group name
	groupET.Text = kvs.Get("group_name_" & code)
	groupET.Enabled = False
	groupET.Tag = code   ' store current group code in Tag

	' Load lists for this group
	listsListGrp.Clear
	listsListGrp.GetBase.Visible = True
	tasksListGrp.Clear
	tasksListGrp.GetBase.Visible = False

	Dim listsKey As String = "group_lists_" & code
	If kvs.ContainsKey(listsKey) Then
		Dim savedLists As List = kvs.Get(listsKey)
		For Each listName As String In savedLists
			listsListGrp.AddTextItem(listName, listName)
		Next
	End If

	progressBarGrp.Progress = 0
	progressNumberGrp.Text = ""
	progressPercentGrp.Text = ""
End Sub

Sub groupList_ItemLongClick(Index As Int, Value As Object)
	If Value = "joinPanel" Then Return

	Dim code As String = Value
	Msgbox2Async("What do you want to do with this group?", kvs.Get("group_name_" & code), "Rename", "Copy Invite Code", "Delete", Null, True)
	Wait For Msgbox_Result (res As Int)

	If res = DialogResponse.POSITIVE Then
		' Rename
		showRenameGroupPanel(Index, code)

	Else If res = DialogResponse.CANCEL Then
		' Simpler B4A clipboard copy:
		Dim jo As JavaObject
		jo.InitializeContext
		Dim clipManager As JavaObject = jo.RunMethod("getSystemService", Array("clipboard"))
		Dim clipData As JavaObject
		clipData.InitializeStatic("android.content.ClipData")
		clipManager.RunMethod("setPrimaryClip", Array(clipData.RunMethod("newPlainText", Array("Group Code", code))))
		ToastMessageShow("Invite code copied: " & code, True)

	Else If res = DialogResponse.NEGATIVE Then
		' Delete — only owner can delete
		Dim ownerKey As String = "group_owner_" & code
		If kvs.ContainsKey(ownerKey) Then
			If kvs.Get(ownerKey) <> Starter.currentUserID Then
				MsgboxAsync("Only the group creator can delete the group.", "Not allowed")
				Return
			End If
		End If

		Msgbox2Async("Delete this group and all its data?", "Confirm", "No", "", "Yes", Null, True)
		Wait For Msgbox_Result (res2 As Int)
		If res2 = DialogResponse.NEGATIVE Then
			deleteGroup(code)
			loadMyGroups
		End If
	End If
End Sub

Sub showRenameGroupPanel(Index As Int, code As String)
	groupET.Text = kvs.Get("group_name_" & code)
	groupET.Enabled = True
	groupET.RequestFocus
	Dim ctx As List
	ctx.Initialize
	ctx.Add(Index)
	ctx.Add(code)
	groupET.Tag = ctx   ' Tag is now a List, not "creating"
End Sub

' Call this from groupET_EnterPressed — check if Tag is a List (rename ctx)
' Update groupET_EnterPressed like so:

Sub deleteGroup(code As String)
	' Remove all lists and their tasks
	Dim listsKey As String = "group_lists_" & code
	If kvs.ContainsKey(listsKey) Then
		Dim savedLists As List = kvs.Get(listsKey)
		For Each listName As String In savedLists
			Dim taskKey As String = "group_list_" & code & "_" & listName
			If kvs.ContainsKey(taskKey) Then
				Dim savedTasks As List = kvs.Get(taskKey)
				For Each task As String In savedTasks
					kvs.Remove("group_checked_" & code & "_" & listName & "_" & task)
				Next
				kvs.Remove(taskKey)
			End If
		Next
		kvs.Remove(listsKey)
	End If
	kvs.Remove("group_name_" & code)
	kvs.Remove("group_owner_" & code)
	kvs.Remove("group_members_" & code)

	' Remove from master groups list
	Dim allGroups As List = kvs.Get("groups")
	Dim idx As Int = allGroups.IndexOf(code)
	If idx >= 0 Then allGroups.RemoveAt(idx)
	kvs.Put("groups", allGroups)
End Sub

'==================== GROUP LISTS ====================

Sub newListBtnGrp_Click
	If groupET.Tag = Null Or groupET.Tag Is List Then Return  ' no group selected

	Dim code As String = groupET.Tag

	listsListGrp.GetBase.Visible = True
	tasksListGrp.Clear
	tasksListGrp.GetBase.Visible = False

	' Add an inline input panel at the bottom of listsListGrp
	Dim newListPNL As Panel
	newListPNL.Initialize("newListPNLGrp")
	newListPNL.SetLayout(0, 0, 250dip, 120dip)
	newListPNL.Color = Colors.Transparent

	Dim newListET As EditText
	newListET.Initialize("newListETGrp")
	newListET.Hint = "List name..."

	Dim newListConfirmBtn As Button
	newListConfirmBtn.Initialize("newListConfirmBtnGrp")
	newListConfirmBtn.Text = "Create List"

	Dim cd As ColorDrawable
	cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)
	newListConfirmBtn.Background = cd
	newListConfirmBtn.TextColor = Colors.White

	newListPNL.AddView(newListET, 0, 0, 250dip, 55dip)
	newListPNL.AddView(newListConfirmBtn, 0, 65dip, 250dip, 40dip)

	newListConfirmBtn.Tag = newListET

	listsListGrp.Add(newListPNL, "newListPanel")
End Sub

Sub newListConfirmBtnGrp_Click
	Dim btn As Button = Sender
	Dim et As EditText = btn.Tag
	Dim listName As String = et.Text.Trim
	Dim code As String = groupET.Tag

	If listName = "" Then
		MsgboxAsync("Please enter a list name.", "No name")
		Return
	End If

	Dim listsKey As String = "group_lists_" & code
	Dim savedLists As List
	savedLists.Initialize
	If kvs.ContainsKey(listsKey) Then savedLists = kvs.Get(listsKey)

	For Each existing As String In savedLists
		If existing = listName Then
			MsgboxAsync("A list with that name already exists.", "Duplicate")
			Return
		End If
	Next

	savedLists.Add(listName)
	kvs.Put(listsKey, savedLists)

	' Remove input panel
	listsListGrp.RemoveAt(listsListGrp.Size - 1)
	listsListGrp.AddTextItem(listName, listName)

	ToastMessageShow("List created", False)
End Sub

Sub listsListGrp_ItemClick(Index As Int, Value As Object)
	If Value = "newListPanel" Then Return
	Dim code As String = groupET.Tag
	Dim currentGrpList As String = Value

	tasksListGrp.Clear
	tasksListGrp.GetBase.Visible = True

	Dim taskKey As String = "group_list_" & code & "_" & currentGrpList
	If kvs.ContainsKey(taskKey) Then
		Dim savedTasks As List = kvs.Get(taskKey)
		For Each task As String In savedTasks
			tasksListGrpUI(task, code, currentGrpList)
		Next
	End If

	newAddTaskBtnGrp(code, currentGrpList)
	updateProgressGrp(code, currentGrpList)
End Sub

Sub listsListGrp_ItemLongClick(Index As Int, Value As Object)
	If Value = "newListPanel" Then Return
	Dim code As String = groupET.Tag

	Msgbox2Async("Delete or rename this list?", Value, "Rename", "", "Delete", Null, True)
	Wait For Msgbox_Result (res As Int)

	If res = DialogResponse.POSITIVE Then
		' Rename — inline via a small msgbox input
		Dim newName As String = ""
		If res = DialogResponse.POSITIVE Then
			' Remove any existing rename panel first
			showRenameListPanelGrp(Index, Value, code)
		End If

		Dim listsKey As String = "group_lists_" & code
		Dim savedLists As List = kvs.Get(listsKey)

		' Check duplicate
		For Each existing As String In savedLists
			If existing = newName Then
				MsgboxAsync("Name already exists.", "Duplicate")
				Return
			End If
		Next

		' Migrate task data
		Dim oldTaskKey As String = "group_list_" & code & "_" & Value
		Dim newTaskKey As String = "group_list_" & code & "_" & newName
		If kvs.ContainsKey(oldTaskKey) Then
			Dim savedTasks As List = kvs.Get(oldTaskKey)
			For Each task As String In savedTasks
				Dim oldCK As String = "group_checked_" & code & "_" & Value & "_" & task
				Dim newCK As String = "group_checked_" & code & "_" & newName & "_" & task
				If kvs.ContainsKey(oldCK) Then
					kvs.Put(newCK, kvs.Get(oldCK))
					kvs.Remove(oldCK)
				End If
			Next
			kvs.Put(newTaskKey, savedTasks)
			kvs.Remove(oldTaskKey)
		End If

		savedLists.Set(Index, newName)
		kvs.Put(listsKey, savedLists)

		listsListGrp.Clear
		For Each ln As String In savedLists
			listsListGrp.AddTextItem(ln, ln)
		Next

		ToastMessageShow("List renamed", False)

	Else If res = DialogResponse.NEGATIVE Then
		Msgbox2Async("Delete list """ & Value & """?", "Confirm", "No", "", "Yes", Null, True)
		Wait For Msgbox_Result (res2 As Int)
		If res2 = DialogResponse.NEGATIVE Then
			Dim listsKey As String = "group_lists_" & code
			Dim savedLists As List = kvs.Get(listsKey)
			savedLists.RemoveAt(Index)
			kvs.Put(listsKey, savedLists)

			' Delete tasks and checkboxes
			Dim taskKey As String = "group_list_" & code & "_" & Value
			If kvs.ContainsKey(taskKey) Then
				Dim savedTasks As List = kvs.Get(taskKey)
				For Each task As String In savedTasks
					kvs.Remove("group_checked_" & code & "_" & Value & "_" & task)
				Next
				kvs.Remove(taskKey)
			End If

			listsListGrp.RemoveAt(Index)
			tasksListGrp.Clear
			tasksListGrp.GetBase.Visible = False
			ToastMessageShow("List deleted", False)
		End If
	End If
End Sub

Sub showRenameListPanelGrp(Index As Int, oldName As String, code As String)
	Dim renamePNL As Panel
	renamePNL.Initialize("renameListPNLGrp")
	renamePNL.SetLayout(0, 0, 250dip, 120dip)
	renamePNL.Color = Colors.Transparent

	Dim renameET As EditText
	renameET.Initialize("renameListETGrp")
	renameET.Text = oldName

	Dim renameBtn As Button
	renameBtn.Initialize("renameListBtnGrp")
	renameBtn.Text = "Rename"

	Dim cd As ColorDrawable
	cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)
	renameBtn.Background = cd
	renameBtn.TextColor = Colors.White

	Dim ctx As List
	ctx.Initialize
	ctx.Add(Index)
	ctx.Add(oldName)
	ctx.Add(code)
	ctx.Add(renameET)
	renameBtn.Tag = ctx

	renamePNL.AddView(renameET, 0, 0, 250dip, 55dip)
	renamePNL.AddView(renameBtn, 0, 65dip, 250dip, 40dip)

	listsListGrp.Add(renamePNL, "renameListPanel")
End Sub

Sub renameListBtnGrp_Click
	Dim btn As Button = Sender
	Dim ctx As List = btn.Tag
	Dim Index As Int = ctx.Get(0)
	Dim oldName As String = ctx.Get(1)
	Dim code As String = ctx.Get(2)
	Dim renameET As EditText = ctx.Get(3)
	Dim newName As String = renameET.Text.Trim

	If newName = "" Or newName = oldName Then
		listsListGrp.RemoveAt(listsListGrp.Size - 1)
		Return
	End If

	Dim listsKey As String = "group_lists_" & code
	Dim savedLists As List = kvs.Get(listsKey)

	For Each existing As String In savedLists
		If existing = newName Then
			MsgboxAsync("Name already exists.", "Duplicate")
			Return
		End If
	Next

	' Migrate task and checkbox keys
	Dim oldTaskKey As String = "group_list_" & code & "_" & oldName
	Dim newTaskKey As String = "group_list_" & code & "_" & newName
	If kvs.ContainsKey(oldTaskKey) Then
		Dim savedTasks As List = kvs.Get(oldTaskKey)
		For Each task As String In savedTasks
			Dim oldCK As String = "group_checked_" & code & "_" & oldName & "_" & task
			Dim newCK As String = "group_checked_" & code & "_" & newName & "_" & task
			If kvs.ContainsKey(oldCK) Then
				kvs.Put(newCK, kvs.Get(oldCK))
				kvs.Remove(oldCK)
			End If
		Next
		kvs.Put(newTaskKey, savedTasks)
		kvs.Remove(oldTaskKey)
	End If

	savedLists.Set(Index, newName)
	kvs.Put(listsKey, savedLists)

	listsListGrp.RemoveAt(listsListGrp.Size - 1)
	listsListGrp.Clear
	For Each ln As String In savedLists
		listsListGrp.AddTextItem(ln, ln)
	Next

	If currentGrpListName = oldName Then currentGrpListName = newName
	ToastMessageShow("List renamed", False)
End Sub

'==================== GROUP TASKS ====================

Sub newAddTaskBtnGrp(code As String, currentGrpList As String)
	Dim addBtnPNL As Panel
	addBtnPNL.Initialize("addTaskBtnPNLGrp")
	addBtnPNL.SetLayout(10dip, 0dip, 190dip, 70dip)
	addBtnPNL.Color = Colors.Transparent

	Dim addBtn As Button
	addBtn.Initialize("addTaskBtnGrp")
	addBtn.Text = "+ add a task"

	Dim cd As ColorDrawable
	cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)
	addBtn.Background = cd
	addBtn.TextColor = Colors.White

	' Pass context via Tag
	Dim ctx As List
	ctx.Initialize
	ctx.Add(code)
	ctx.Add(currentGrpList)
	addBtn.Tag = ctx

	addBtnPNL.AddView(addBtn, 10dip, 20dip, addBtnPNL.Width, 50dip)
	tasksListGrp.Add(addBtnPNL, "")
End Sub

Sub addTaskBtnGrp_Click
	Dim addBtn As Button = Sender
	Dim ctx As List = addBtn.Tag
	Dim code As String = ctx.Get(0)
	Dim currentGrpList As String = ctx.Get(1)

	tasksListGrp.RemoveAt(tasksListGrp.Size - 1)

	Dim addPNL As Panel
	addPNL.Initialize("addTaskPNLGrp")
	addPNL.SetLayout(10dip, 0, 20dip, 120dip)
	addPNL.Color = Colors.Transparent

	Dim addET As EditText
	addET.Initialize("addTaskETGrp")
	addET.Hint = "Add a task..."
	addET.Tag = Null

	Dim confirmBtn As Button
	confirmBtn.Initialize("enterTaskBtnGrp")
	confirmBtn.Text = "Enter task"

	Dim cd As ColorDrawable
	cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)
	confirmBtn.Background = cd
	confirmBtn.TextColor = Colors.White

	' Pass all context via confirmBtn Tag
	Dim ctx2 As List
	ctx2.Initialize
	ctx2.Add(code)
	ctx2.Add(currentGrpList)
	ctx2.Add(addET)     ' index 2: the EditText
	ctx2.Add(Null)      ' index 3: oldTask (Null = new task, not rename)
	confirmBtn.Tag = ctx2

	addPNL.AddView(addET, 0, 0, 190dip, 60dip)
	addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40dip)

	tasksListGrp.Add(addPNL, addPNL)
End Sub

Sub enterTaskBtnGrp_Click
	Dim btn As Button = Sender
	Dim ctx As List = btn.Tag
	Dim code As String = ctx.Get(0)
	Dim currentGrpList As String = ctx.Get(1)
	Dim addET As EditText = ctx.Get(2)
	Dim oldTask As Object = ctx.Get(3)

	Dim newTask As String = addET.Text.Trim
	If newTask = "" Then
		MsgboxAsync("Please enter a task.", "No task entered")
		Return
	End If

	Dim taskKey As String = "group_list_" & code & "_" & currentGrpList
	Dim savedTasks As List
	savedTasks.Initialize
	If kvs.ContainsKey(taskKey) Then savedTasks = kvs.Get(taskKey)

	' Check duplicate
	For Each existing As String In savedTasks
		If existing = newTask Then
			MsgboxAsync("Task already exists.", "Duplicate")
			Return
		End If
	Next

	If oldTask <> Null Then
		' Rename flow
		Dim taskIndex As Int = savedTasks.IndexOf(oldTask)
		If taskIndex >= 0 Then
			savedTasks.Set(taskIndex, newTask)
			kvs.Put(taskKey, savedTasks)
		End If

		Dim oldCK As String = "group_checked_" & code & "_" & currentGrpList & "_" & oldTask
		Dim newCK As String = "group_checked_" & code & "_" & currentGrpList & "_" & newTask
		If kvs.ContainsKey(oldCK) Then
			kvs.Put(newCK, kvs.Get(oldCK))
			kvs.Remove(oldCK)
		End If

		' Rebuild tasksListGrp
		tasksListGrp.Clear
		Dim savedTasks2 As List = kvs.Get(taskKey)
		For Each t As String In savedTasks2
			tasksListGrpUI(t, code, currentGrpList)
		Next
		newAddTaskBtnGrp(code, currentGrpList)
		updateProgressGrp(code, currentGrpList)
		ToastMessageShow("Task renamed", False)
		Return
	End If

	' New task
	tasksListGrp.RemoveAt(tasksListGrp.Size - 1)
	savedTasks.Add(newTask)
	kvs.Put(taskKey, savedTasks)
	tasksListGrpUI(newTask, code, currentGrpList)
	newAddTaskBtnGrp(code, currentGrpList)
	updateProgressGrp(code, currentGrpList)
End Sub

Sub tasksListGrp_ItemLongClick(Index As Int, Value As Object)
	If Value = "" Then Return

	Dim code As String = groupET.Tag
	' Identify current group list from listsListGrp selection
	Dim currentGrpList As String = getCurrentGrpList

	Msgbox2Async("Delete or rename this task?", Value, "Rename", "", "Delete", Null, True)
	Wait For Msgbox_Result (res As Int)

	If res = DialogResponse.POSITIVE Then
		' Rename — show rename panel
		showRenameTaskPanelGrp(Index, Value, code, currentGrpList)

	Else If res = DialogResponse.NEGATIVE Then
		Dim taskKey As String = "group_list_" & code & "_" & currentGrpList
		Dim savedTasks As List = kvs.Get(taskKey)
		savedTasks.RemoveAt(Index)
		kvs.Put(taskKey, savedTasks)
		kvs.Remove("group_checked_" & code & "_" & currentGrpList & "_" & Value)
		tasksListGrp.RemoveAt(Index)
		updateProgressGrp(code, currentGrpList)
		ToastMessageShow("Task deleted", False)
	End If
End Sub

Sub showRenameTaskPanelGrp(Index As Int, oldTask As String, code As String, currentGrpList As String)
	tasksListGrp.RemoveAt(tasksListGrp.Size - 1)

	Dim addPNL As Panel
	addPNL.Initialize("addTaskPNLGrp")
	addPNL.SetLayout(10dip, 0, 240dip, 120dip)
	addPNL.Color = Colors.Transparent

	Dim addET As EditText
	addET.Initialize("addTaskETGrp")
	addET.Text = oldTask

	Dim confirmBtn As Button
	confirmBtn.Initialize("enterTaskBtnGrp")
	confirmBtn.Text = "Rename task"

	Dim cd As ColorDrawable
	cd.Initialize(Colors.ARGB(255, 98, 43, 20), 200)
	confirmBtn.Background = cd
	confirmBtn.TextColor = Colors.White

	Dim ctx As List
	ctx.Initialize
	ctx.Add(code)
	ctx.Add(currentGrpList)
	ctx.Add(addET)
	ctx.Add(oldTask)    ' index 3 is not Null → rename mode
	confirmBtn.Tag = ctx

	addPNL.AddView(addET, 0, 0, 190dip, 60dip)
	addPNL.AddView(confirmBtn, 0, 70dip, 190dip, 40dip)

	tasksListGrp.Add(addPNL, addPNL)
End Sub

Sub tasksListGrpUI(newTask As String, code As String, currentGrpList As String)
	Dim taskPNL As Panel
	taskPNL.Initialize("taskPNLGrp")
	taskPNL.SetLayout(0, 0, 250dip, 60dip)

	Dim taskCheckbox As CheckBox
	taskCheckbox.Initialize("taskCheckboxGrp")
	taskPNL.AddView(taskCheckbox, 0dip, 10dip, 40dip, 40dip)

	Dim taskLBL As Label
	taskLBL.Initialize("taskLBLGrp")
	taskLBL.Text = newTask
	If Starter.darkMode Then
		taskLBL.TextColor = Colors.White
	Else
		taskLBL.TextColor = Colors.Black
	End If
	taskPNL.AddView(taskLBL, 40dip, 20dip, taskPNL.Width - 80dip, taskPNL.Height)

	Dim divider As Panel
	divider.Initialize("line")
	divider.Color = Colors.ARGB(255, 60, 60, 60)
	taskPNL.AddView(divider, 0, 59dip, taskPNL.Width, 1dip)

	' Store code + listName + taskLBL in checkbox tag
	Dim cbCtx As List
	cbCtx.Initialize
	cbCtx.Add(code)
	cbCtx.Add(currentGrpList)
	cbCtx.Add(taskLBL)
	taskCheckbox.Tag = cbCtx

	' Restore checkbox state
	Dim checkedKey As String = "group_checked_" & code & "_" & currentGrpList & "_" & newTask
	If kvs.ContainsKey(checkedKey) Then
		Dim isChecked As Boolean = kvs.Get(checkedKey)
		taskCheckbox.Checked = isChecked
		If isChecked Then taskLBL.TextColor = Colors.ARGB(50, 0, 0, 0)
	End If

	tasksListGrp.Add(taskPNL, newTask)
End Sub

Sub taskCheckboxGrp_CheckedChange(Checked As Boolean)
	Dim cb As CheckBox = Sender
	Dim cbCtx As List = cb.Tag
	Dim code As String = cbCtx.Get(0)
	Dim currentGrpList As String = cbCtx.Get(1)
	Dim taskLBL As Label = cbCtx.Get(2)

	If Checked Then
		taskLBL.TextColor = Colors.ARGB(255, 128, 128, 128)
	Else
		If Starter.darkMode Then
			taskLBL.TextColor = Colors.White
		Else
			taskLBL.TextColor = Colors.Black
		End If
	End If

	Dim key As String = "group_checked_" & code & "_" & currentGrpList & "_" & taskLBL.Text
	kvs.Put(key, Checked)
	updateProgressGrp(code, currentGrpList)
End Sub

Sub updateProgressGrp(code As String, currentGrpList As String)
	Dim taskKey As String = "group_list_" & code & "_" & currentGrpList
	If kvs.ContainsKey(taskKey) = False Then
		progressBarGrp.Progress = 0
		progressNumberGrp.Text = "0 / 0 tasks done!"
		progressPercentGrp.Text = "0%"
		Return
	End If

	Dim savedTasks As List = kvs.Get(taskKey)
	Dim totalTasks As Int = savedTasks.Size
	Dim doneTasks As Int = 0

	For Each task As String In savedTasks
		Dim checkedKey As String = "group_checked_" & code & "_" & currentGrpList & "_" & task
		If kvs.ContainsKey(checkedKey) Then
			If kvs.Get(checkedKey) = True Then doneTasks = doneTasks + 1
		End If
	Next

	Dim pct As Int = 0
	If totalTasks > 0 Then pct = (doneTasks / totalTasks) * 100

	progressNumberGrp.Text = doneTasks & " / " & totalTasks & " tasks done!"
	progressPercentGrp.Text = pct & "%"
	progressBarGrp.Progress = pct
End Sub

' Helper: returns the currently selected list name in listsListGrp
' You need to track this — add a module-level variable:
' Private currentGrpListName As String = ""
' Set it in listsListGrp_ItemClick:
'   currentGrpListName = Value

Sub getCurrentGrpList As String
	Return currentGrpListName   ' module-level var you maintain
End Sub