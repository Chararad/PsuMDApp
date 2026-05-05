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
	Dim themePage As Int = 0
	Dim xui As XUI
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private titleLbl As Label
	Private backBtn As Button
	Private nextBtn As Button
	Private theme1 As ImageView
	Private theme2 As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("themeLayout")
			Else
				Activity.LoadLayout("themeLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("themeLayout2")
			Else
				Activity.LoadLayout("themeLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("themeLayout3")
			Else
				Activity.LoadLayout("themeLayoutDark3")
			End If
	End Select

	showThemePage(0)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub showThemePage(page As Int)
	
themePage = page
    
Select page
	Case 0
		titleLbl.Text = "Default Theme"
		theme1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "Theme1.PNG", theme1.Width, theme1.Height, True)
			theme2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "Dtheme1.PNG", theme2.Width, theme2.Height, True)
	Case 1
		titleLbl.Text = "Y2K Aero"
			theme1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "Theme2.PNG", theme1.Width, theme1.Height, True)
			theme2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "Dtheme2.PNG", theme2.Width, theme2.Height, True)
	Case 2
		titleLbl.Text = "Pixelated Rustic"
			theme1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "Theme3.PNG", theme1.Width, theme1.Height, True)
			theme2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "Dtheme3.PNG", theme2.Width, theme2.Height, True)
	End Select
	backBtn.Enabled = (page > 0)
	nextBtn.Enabled = (page < 2)
End Sub

Sub nextBtn_Click
	If themePage < 2 Then
		showThemePage(themePage + 1)
	End If
End Sub

Sub backBtn_Click
	If themePage > 0 Then
		showThemePage(themePage - 1)
	End If
End Sub

Sub closeHelp_Click
	Activity.Finish
End Sub

Sub applyBtn_Click
	If themePage = 0 Then
		Starter.themeNumber = 0
		Starter.themeChanged = True
	Else If themePage = 1 Then
		Starter.themeNumber = 1
		Starter.themeChanged = True
	Else If themePage = 2 Then
		Starter.themeNumber = 2
		Starter.themeChanged = True
	End If
	ToastMessageShow("Theme changed!", False)
	MainActivity.kvsPref.Put("themeNumber", Starter.themeNumber)
End Sub