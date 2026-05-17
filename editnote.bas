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
	Public ActiveNote As MyNote
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private contentTxt As EditText
	Private saveBtn As Button
	Private tagsTxt As EditText
	Private titleTxt As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("editnoteLayout")
			Else
				Activity.LoadLayout("editnoteLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("editnoteLayout2")
			Else
				Activity.LoadLayout("editnoteLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("editnoteLayout3")
			Else
				Activity.LoadLayout("editnoteLayoutDark3")
			End If
	End Select
	
	contentTxt.Background = Null
	contentTxt.Gravity = Bit.Or(Gravity.TOP, Gravity.LEFT)
	If ActiveNote.IsInitialized Then
		titleTxt.Text = ActiveNote.Title
		tagsTxt.Text = ActiveNote.Tags
		contentTxt.Text = ActiveNote.Content
	End If
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	If UserClosed Then ActiveNote.Initialize
End Sub

Sub saveBtn_Click
	If titleTxt.Text.Trim = "" Then
		MsgboxAsync("Please add a title", "")
		Return
	End If

	Dim n As MyNote
	n.Initialize
  
	If ActiveNote.IsInitialized And ActiveNote.noteID <> 0 Then
		n.noteID = ActiveNote.noteID
	Else
		n.noteID = (DateTime.Now * 1000) + Rnd(1, 1000)
	End If
	' -------------------------

	n.Title = titleTxt.Text
	n.Tags = tagsTxt.Text
	n.Content = contentTxt.Text
	n.DateAdded = DateTime.Now

	MainActivity.kvs.Put("N_" & n.noteID, n)
    
	ToastMessageShow("Note Saved", False)
	Activity.Finish
End Sub
