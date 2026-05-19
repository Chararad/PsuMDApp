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
	Private txtname As EditText
	Private txtpassword_regis As EditText
	Dim isPasswordHidden As Boolean = True
	Private btneyes_regis As Button
	Private btnconfirm_pass As Button
	Private txtconfirm_password As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Register_layout")

	txtpassword_regis.PasswordMode = True
	btneyes_regis.SetBackgroundImage(LoadBitmap(File.DirAssets, "close-eye.png"))
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub btnregister_Click
	Dim email As String = txtemail.Text.Trim
	Dim password As String = txtpassword_regis.Text.Trim
	Dim name As String = txtname.Text.Trim
	Dim confirm As String = txtconfirm_password.Text.Trim

	If email = "" Or password = "" Or name = "" Or confirm = "" Then
		ToastMessageShow("Please enter all fields", False)

	Else If email.EndsWith("@gmail.com") = False Then
		ToastMessageShow("Email must be a Gmail address", False)

	Else If password.Length < 8 Then
		ToastMessageShow("Password must be at least 8 characters", False)

	Else If password <> confirm Then
		ToastMessageShow("Passwords do not match", False)

	Else
		Try
			Starter.SQL1.ExecNonQuery2("INSERT INTO users (email, name, password) VALUES (?, ?, ?)", _
                               Array As Object(email, name, password))
			ToastMessageShow("Registration successful", False)

			txtemail.Text = ""
			txtname.Text = ""
			txtpassword_regis.Text = ""
			txtconfirm_password.Text = ""

			Dim skipTutorial As Boolean = False
			If Starter.prefKvs.ContainsKey("skipTutorial") Then
				skipTutorial = Starter.prefKvs.Get("skipTutorial")
			End If
	
			If skipTutorial Then
				StartActivity(MainActivity)
			Else
				StartActivity(tutorialActivity)
			End If
		Catch
			ToastMessageShow("Registration failed: Account exist", False)
		End Try
	End If

End Sub

Private Sub btnlogin_Click
	StartActivity(LogIn)
	Activity.Finish
End Sub

Private Sub btneyes_regis_Click
	btneyes_regis.Height = 30dip
	btneyes_regis.Width = 30dip
	
	If isPasswordHidden Then
		txtpassword_regis.PasswordMode = False
		btneyes_regis.SetBackgroundImage(LoadBitmap(File.DirAssets, "open-eye.png"))
		isPasswordHidden = False
	
	Else
		txtpassword_regis.PasswordMode = True
		btneyes_regis.SetBackgroundImage(LoadBitmap(File.DirAssets, "close-eye.png"))
		isPasswordHidden = True
	End If
End Sub

Private Sub btnback_Click
	Activity.Finish
End Sub

Private Sub btnconfirm_pass_Click
	btnconfirm_pass.Height = 30dip
	btnconfirm_pass.Width = 30dip
	
	If isPasswordHidden Then
		txtconfirm_password.PasswordMode = False
		btnconfirm_pass.SetBackgroundImage(LoadBitmap(File.DirAssets, "open-eye.png"))
		isPasswordHidden = False
	
	Else
		txtconfirm_password.PasswordMode = True
		btnconfirm_pass.SetBackgroundImage(LoadBitmap(File.DirAssets, "close-eye.png"))
		isPasswordHidden = True
	End If
	
End Sub

