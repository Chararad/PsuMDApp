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
	Private txtemail As EditText
	Private txtpassword As EditText
	Private btneyes As Button
	Dim isPasswordHidden As Boolean = True
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Login_layout")
	
	txtpassword.PasswordMode = True
	btneyes.SetBackgroundImage(LoadBitmap(File.DirAssets, "close-eye.png"))
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub btnlogin_Click
	Dim email As String = txtemail.Text.Trim
	Dim password As String = txtpassword.Text.Trim
	If email = "" Or password = "" Then
		ToastMessageShow("Please enter both email and password", False)
	Else
		Dim Cursor1 As Cursor
		Cursor1 = Starter.SQL1.ExecQuery2("SELECT * FROM users WHERE email=? AND password=?", _
                                  Array As String(email, password))
        
		If Cursor1.RowCount > 0 Then
			ToastMessageShow("Login successful", False)
			Cursor1.Close
			
			txtemail.Text = ""
			txtpassword.Text = ""
			StartActivity(MainActivity)
		Else
			ToastMessageShow("Invalid email or password", False)
			Cursor1.Close
		End If
	End If
End Sub



Private Sub btneyes_Click
	btneyes.Height = 30dip
	btneyes.Width = 30dip
	
	If isPasswordHidden Then
		txtpassword.PasswordMode = False
		btneyes.SetBackgroundImage(LoadBitmap(File.DirAssets, "open-eye.png"))
		isPasswordHidden = False
	
	Else
		txtpassword.PasswordMode = True
		btneyes.SetBackgroundImage(LoadBitmap(File.DirAssets, "close-eye.png"))
		isPasswordHidden = True
	End If
End Sub

Private Sub btnback_Click
	Activity.Finish
End Sub

Private Sub btnSignUp_Click
	StartActivity(Register)
	Activity.Finish
End Sub