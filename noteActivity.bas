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
	Private addBtn As Button
	Private noteClv As CustomListView
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("notepadLayout")
			Else
				Activity.LoadLayout("notepadLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("notepadLayout2")
			Else
				Activity.LoadLayout("notepadLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("notepadLayout3")
			Else
				Activity.LoadLayout("notepadLayoutDark3")
			End If
	End Select

End Sub

Sub Activity_Resume
	RefreshList("")
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub noteClv_ItemClick (Index As Int, Value As Object)
	' Value is the MyNote object we stored earlier
	Dim selectedNote As MyNote = Value
    
	' Tell the EditActivity which note we want to open
	editnote.ActiveNote = selectedNote
    
	StartActivity(editnote)
End Sub

Sub noteClv_ItemLongClick (Index As Int, Value As Object)
	Dim n As MyNote = Value
    
	Msgbox2Async("Are you sure you want to delete '" & n.Title & "'?", "Delete Note", "No", "", "Yes", Null, False)
	Wait For Msgbox_Result (res As Int)
	If res = DialogResponse.NEGATIVE Then
		MainActivity.kvs.Remove("N_" & n.noteID)
		RefreshList("")
		ToastMessageShow("Note deleted", False)
	End If
End Sub

Sub RefreshList(SearchQuery As String)
	noteClv.Clear
	Dim AllNotes As List
	AllNotes.Initialize
    
	Dim keys As List = MainActivity.kvs.ListKeys
    
	For Each k As String In keys
		If k.StartsWith("N_") Then
			Dim N As MyNote = MainActivity.kvs.Get(k)
			Dim query As String = SearchQuery.ToLowerCase
			If query = "" Then
				AllNotes.Add(N)
			Else
				If N.Tags.ToLowerCase.Contains(query) Or N.Title.ToLowerCase.Contains(query) Then
					AllNotes.Add(N)
				End If
			End If
		End If
	Next

	AllNotes.SortType("Title", True)

	For Each n As MyNote In AllNotes
		noteClv.AddTextItem(n.Title & CRLF & n.Tags, n)
	Next
    
End Sub

Sub searchTxt_TextChanged (Old As String, New As String)
	RefreshList(New)
End Sub

Sub addBtn_Click
	StartActivity(editnote)
End Sub

Sub noteCvl_ItemClick (Index As Int, Value As Object)
End Sub

