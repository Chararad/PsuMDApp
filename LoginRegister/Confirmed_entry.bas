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

	Private btnshowusers As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("confirm_layout")

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub btnshowusers_Click
	Dim Cursor1 As Cursor
	Cursor1 = Starter.SQL1.ExecQuery("SELECT id, email, name FROM users")

	If Cursor1.RowCount = 0 Then
		MsgboxAsync("No users found in database", "Users")
	Else
		Dim sb As StringBuilder
		sb.Initialize
		sb.Append("ID | Email | Name").Append(CRLF)
		sb.Append("---------------------------").Append(CRLF)

		For i = 0 To Cursor1.RowCount - 1
			Cursor1.Position = i
			Dim id As Int = Cursor1.GetInt("id")
			Dim email As String = Cursor1.GetString("email")
			Dim name As String = Cursor1.GetString("name")

			sb.Append(id).Append(" | ").Append(email).Append(" | ").Append(name).Append(CRLF)
		Next

		' Show all users in a message box
		MsgboxAsync(sb.ToString, "Users in Database")
	End If

	Cursor1.Close
End Sub