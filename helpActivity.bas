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
	Dim helpPage As Int = 0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private titleLbl As Label
	Private descriptionLbl As Label
	Private backBtn As Button
	Private nextBtn As Button
	Private helpimage As ImageView
	Private iconButton1 As ImageView
	Private iconButton2 As ImageView
	Private iconButton3 As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("helpAct")
			Else
				Activity.LoadLayout("helpActDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("helpAct2")
			Else
				Activity.LoadLayout("helpActDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("helpAct3")
			Else
				Activity.LoadLayout("helpActDark3")
			End If
	End Select

	showHelpPage(0)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub showHelpPage(page As Int)
	helpPage = page
    
	Select page
		Case 0
			titleLbl.Text = "Welcome"
			descriptionLbl.Text = "This app is tailor-made for students—or anyone who wants to learn. It has all the features you'll need in order to learn effectively!"
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "wreath2.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star2.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star2.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star2.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "wreath3.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 1
			titleLbl.Text = "Calendar"
			descriptionLbl.Text = "The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dcalendarui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1249.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1248.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1247.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "calendarui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1252.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1251.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1250.png", helpimage.Width, helpimage.Height, True)
				End If
		Case 2
			titleLbl.Text = "Clock"
			descriptionLbl.Text = "The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dclockui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1255.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1254.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1253.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "clockui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1258.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1257.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1256.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 3
			titleLbl.Text = "Corkboard"
			descriptionLbl.Text = "The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dcorkboardui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1261.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1260.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1259.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "corkboardui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1264.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1263.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1262.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 4
			titleLbl.Text = "Flashcards"
			descriptionLbl.Text = "The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dflashcardsui2.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1267.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1266.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1265.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "flashcardui2.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1270.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1269.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1268.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 5
			titleLbl.Text = "Music Player"
			descriptionLbl.Text = "The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dmusicplayerui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1273.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1272.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1271.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "musicplayerui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1276.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1275.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1274.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 6
			titleLbl.Text = "Notepad"
			descriptionLbl.Text = "The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dnotepadui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1279.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1278.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1277.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "notepadui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1282.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1281.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1283.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 7
			titleLbl.Text = "To-do List"
			descriptionLbl.Text = "The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dtodoui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1285.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1284.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1283.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "todoui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1288.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1287.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1286.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 8
			titleLbl.Text = "Themes"
			descriptionLbl.Text = "Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dthemesui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1291.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1290.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1289.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "themesui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1294.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1293.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1292.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 9
			titleLbl.Text = "Lamp"
			descriptionLbl.Text = "The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dlampui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1297.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1296.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1295.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "lampui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1298.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1299.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "IMG_1300.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 10
			titleLbl.Text = "Navigation"
			descriptionLbl.Text = "Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dnavigationui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "menu.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "navigationui.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "menu.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
			End If
		Case 11
			titleLbl.Text = "Leaderboard"
			descriptionLbl.Text = "Leaderboard — see who's on top. Track XP, streaks, And correct rates across Daily, Weekly, And All-Time. Study more, rank higher, And climb your way To #1."
			If Starter.darkMode = False Then
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "leaderboarddark3.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "lbbutton.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "star.png", helpimage.Width, helpimage.Height, True)
			Else
				helpimage.Bitmap = xui.LoadBitmapResize(File.DirAssets, "leaderboard3.png", helpimage.Width, helpimage.Height, True)
				iconButton1.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
				iconButton2.Bitmap = xui.LoadBitmapResize(File.DirAssets, "lbbutton.png", helpimage.Width, helpimage.Height, True)
				iconButton3.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dstar.png", helpimage.Width, helpimage.Height, True)
			End If
	End Select
    
	' Disable buttons at limits
	backBtn.Enabled = (page > 0)
	nextBtn.Enabled = (page < 11)
End Sub

Sub nextBtn_Click
	If helpPage < 11 Then
		showHelpPage(helpPage + 1)
	End If
End Sub

Sub backBtn_Click
	If helpPage > 0 Then
		showHelpPage(helpPage - 1)
	End If
End Sub

Sub closeHelp_Click
	Activity.Finish
End Sub
