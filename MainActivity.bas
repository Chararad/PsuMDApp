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
	Dim xui As XUI
	Private timerClock As Timer
	Public kvs As KeyValueStore
	Public kvsPref As KeyValueStore
	Public format24h As Boolean = False
End Sub

Sub Globals
	Dim regLayout, darkModeLayout As B4XView 
	Dim size As Int = 100%y
	Private hsv As HorizontalScrollView
	Private computerGif As B4XGifView 
	Private dcomputerGif As B4XGifView
	Private curtain As B4XGifView
	Private dCurtain As B4XGifView
	Private notesOpen As B4XGifView
	Private noteBook As ImageView
	Private dnotesOpen As B4XGifView
	Private clockBtn As Button
	Private clockLightBtn As Button
	Private infoPnl As B4XView
	Private infoTitleLbl As Label
	Private infoDescLbl As Label
	Private infoPageLbl As Label
	Dim infoPage As Int = 0
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Layouthsv")
	If FirstTime Then
		kvs = Starter.notesKvs
		kvsPref = Starter.prefKvs
		timerClock.Initialize("timerClock", 1000)
		timerClock.Enabled = True
	End If
	
	hsv.Panel.Width = size
	hsv.Panel.Height = size

	regLayout = xui.CreatePanel("")
	darkModeLayout = xui.CreatePanel("")

	hsv.Panel.AddView(regLayout, 0, 0, hsv.Panel.Width, hsv.Panel.Height)
	hsv.Panel.AddView(darkModeLayout, 0, 0, hsv.Panel.Width, hsv.Panel.Height)
	
	Select Starter.themeNumber
		Case 0
			regLayout.LoadLayout("Layout") 
			darkModeLayout.LoadLayout("Layout2")
			computerGif.SetGif(File.DirAssets, "BtnComputer.GIF")
			dcomputerGif.SetGif(File.DirAssets, "darkbtncomputer.GIF")
		Case 1
			regLayout.LoadLayout("Layout3") 
			darkModeLayout.LoadLayout("Layout4")  
			computerGif.SetGif(File.DirAssets, "mikucomp2.GIF") 'miku
			dcomputerGif.SetGif(File.DirAssets, "DComp2.GIF")'miku
		Case 2
			regLayout.LoadLayout("Layout5") 
			darkModeLayout.LoadLayout("Layout6") 
			computerGif.SetGif(File.DirAssets, "Comp3.GIF")
			dcomputerGif.SetGif(File.DirAssets, "DComp3.GIF")
			curtain.SetGif(File.DirAssets, "Curtain.GIF")
			dCurtain.SetGif(File.DirAssets, "DCurtain.GIF")
	End Select
	
	If Starter.darkMode Then
		darkModeLayout.Visible = True
		darkModeLayout.BringToFront
		regLayout.Visible = False
	Else
		darkModeLayout.Visible = False
		regLayout.BringToFront
	End If
	
	Sleep(50)
	hsv.ScrollPosition = Max(0, (hsv.Panel.Width - 100%x) / 2)
	
End Sub

Sub Activity_Resume
	
	If format24h Then
		DateTime.TimeFormat = "HH:mm" ' 24-Hour Format
	Else
		DateTime.TimeFormat = "hh:mm a" ' AM/PM Format
	End If
	
	If Starter.themeChanged Then
		regLayout.RemoveAllViews
		darkModeLayout.RemoveAllViews
		
		Select Starter.themeNumber
			Case 0
				regLayout.LoadLayout("Layout")
				darkModeLayout.LoadLayout("Layout2")
				computerGif.SetGif(File.DirAssets, "BtnComputer.GIF")
				dcomputerGif.SetGif(File.DirAssets, "darkbtnComputer.GIF")
			Case 1
				regLayout.LoadLayout("Layout3")
				darkModeLayout.LoadLayout("Layout4")
				computerGif.SetGif(File.DirAssets, "mikucomp2.GIF") 'miku
				dcomputerGif.SetGif(File.DirAssets, "DComp2.GIF") 'miku
			Case 2
				regLayout.LoadLayout("Layout5")
				darkModeLayout.LoadLayout("Layout6")
				computerGif.SetGif(File.DirAssets, "Comp3.GIF")
				dcomputerGif.SetGif(File.DirAssets, "DComp3.GIF")
				curtain.SetGif(File.DirAssets, "Curtain.GIF")
				dCurtain.SetGif(File.DirAssets, "DCurtain.GIF")
		End Select
		Starter.themeChanged = False
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Private Sub lamp_Click
	Starter.darkMode = True
	kvsPref.Put("darkMode", True)
	darkModeLayout.Visible = True
	darkModeLayout.BringToFront
	darkModeLayout.Alpha = 0
	darkModeLayout.SetAlphaAnimated(250, 1)
	regLayout.SetAlphaAnimated(250, 0)
	Sleep(250)
	regLayout.Visible = False
End Sub

Private Sub dlamp_Click
	Starter.darkMode = False
	kvsPref.Put("darkMode", False)
	regLayout.Visible = True
	regLayout.BringToFront
	regLayout.Alpha = 0
	regLayout.SetAlphaAnimated(250, 1)
	darkModeLayout.SetAlphaAnimated(250, 0)
	Sleep(250)
	darkModeLayout.Visible = False
End Sub

Sub timerClock_Tick
	clockBtn.Text = DateTime.Time(DateTime.Now)
	clockLightBtn.Text = DateTime.Time(DateTime.Now)
End Sub


Private Sub showInfoPopup

	infoPnl = xui.CreatePanel("infoPnl")
	Activity.AddView(infoPnl, 75dip, 225dip, 300dip, 220dip)
	infoPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)

	Dim closeBtn As Button
	closeBtn.Initialize("infoPnlClose")
	closeBtn.Text = "x"
	closeBtn.TextSize = 6
	infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28dip)

	infoTitleLbl.Initialize("")
	infoTitleLbl.TextSize = 16
	infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL
	infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248dip, 30dip)

	infoDescLbl.Initialize("")
	infoDescLbl.TextSize = 10
	infoDescLbl.Gravity = Gravity.TOP
	infoDescLbl.SingleLine = False
	infoPnl.AddView(infoDescLbl, 12dip, 52dip, 276dip, 140dip)

	infoPageLbl.Initialize("")
	infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL
	infoPageLbl.TextSize = 10
	infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110dip, 22dip)


	showInfoPage(0)
End Sub

Private Sub showInfoPage(page As Int)
	infoPage = page
	Select page
		Case 0
			infoTitleLbl.Text = "Calendar"
			infoDescLbl.Text = "The calendar comes in three views to match however you like to plan. The Schedule view lets you see all your upcoming days at a glance, with major events and to-do items laid out on a timeline. The Day view shows you the full timeline for a single day, where you can add events, tasks, or birthdays, and color-coded backgrounds make each type of entry easy to tell apart. The Month view presents the classic calendar layout, complete with a month and year spinner to jump around quickly, a sneak peek of events on each day's cell, and neighboring dates filling in the empty cells so the grid always looks complete."
		Case 1
			infoTitleLbl.Text = "Clock"
			infoDescLbl.Text = "The clock keeps you on time and on task all in one place. A format button lets you switch between clock displays to whatever you prefer. It also comes with a built-in Pomodoro timer to help you stay focused and productive, and if the default durations don't work for you, you can freely adjust them to fit your own rhythm."
		Case 2
			infoTitleLbl.Text = "Corkboard"
			infoDescLbl.Text = "The corkboard gives you a creative space to pin your thoughts and ideas. You can place sticky notes in a variety of colors to keep things visually organized and easy to tell apart. Images from your gallery can be attached right onto the board for extra context or inspiration. And when words and notes aren't enough, you can open up a drawable canvas with a selection of colored pens and sketch, doodle, or diagram anything you have in mind."
		Case 3
			infoTitleLbl.Text = "Flashcards"
			infoDescLbl.Text = "The flashcard feature organizes your cards into decks and subdecks, and you have the freedom to add, rename, or delete any of them — with a confirmation before anything gets deleted for good. Cards are automatically shuffled so every session feels fresh. A progress bar and percentage track how far you've made it through a deck, and when you're ready to dive back in, two buttons have you covered — restart the entire deck from scratch, or continue right where you left off."
		Case 4
			infoTitleLbl.Text = "Music Player"
			infoDescLbl.Text = "The music player plays the music on startup, meaning the app greets you with songs! Filled with chill, lo-fi tracks, you have the freedom to choose what track you want to play--either through the previous/next song buttons, or through the list of songs below where you can click on whatever chooses your fancy. There's a segment of the song that you want to listen to again or you want to zoom through a certain bit of a song? The seek bar presents itself to you where you can navigate within the music.  "
		Case 5
			infoTitleLbl.Text = "Notepad"
			infoDescLbl.Text = "The notepad keeps all your notes in one tidy list that's easy to browse through. A search bar lets you quickly find any note by its title or tags, so nothing ever gets lost in the pile. Adding a new note is simple — just give it a title, attach some tags to keep things organized, and write away to your heart's content."
		Case 6
			infoTitleLbl.Text = "To-do List"
			infoDescLbl.Text = "The to-do list enables you to create lists with titles and, especially, tasks you want or are obligated to do. You are able to keep a lot of lists that you can easily navigate through the left side of your screen. The task section is a checklist of your tasks that you input, and once achieved, you can tick it off the list. The to-do list will also show you your progress in the list."
		Case 7
			infoTitleLbl.Text = "Themes"
			infoDescLbl.Text = "Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."
		Case 8
			infoTitleLbl.Text = "Lamp"
			infoDescLbl.Text = "The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."
		Case 9
			infoTitleLbl.Text = "Navigation"
			infoDescLbl.Text = "Navigation is your home base for getting around the app. All of the app's features are laid out in one accessible spot, with clearly labeled and easy-to-read buttons so you can jump to wherever you need to be without any hassle."

			
	End Select
End Sub

Private Sub clockBtn_Click
	StartActivity(clockActivity)
End Sub

Private Sub  clockLightBtn_Click
	StartActivity(clockActivity)
End Sub

Private Sub navBtn_Click
	StartActivity(navActivity)
End Sub

Private Sub helpBtn_Click
	StartActivity(helpActivity)
End Sub

Private Sub infoPnlClose_Click
	infoPnl.Visible = False
End Sub

Private Sub todolistBtn_Click
	StartActivity(todoActivity)
End Sub

Private Sub mP_Click
	StartActivity(musicActivity)
End Sub

Private Sub bookie_Click
	StartActivity(FlashcardActivity)
End Sub

Private Sub calendar_Click
	StartActivity(CalendarActivity)
End Sub

Private Sub noteBook_Click
	Select Starter.themeNumber
		Case 0
			CallSub(Me, "NotesTransition1")
		Case 1
			CallSub(Me, "NotesTransition2")
		Case 2
			CallSub(Me, "NotesTransition3")
	End Select
End Sub

Sub NotesTransition1 As ResumableSub
	notesOpen.SetGif(File.DirAssets, "Openbook.GIF")
	notesOpen.mBase.Visible = True
	dnotesOpen.SetGif(File.DirAssets, "Darkopenbook.GIF")
	dnotesOpen.mBase.Visible = True
	noteBook.Enabled = False
	noteBook.Visible = False 'bat ayaw mawala T-T
	
	Sleep(1500)
    
	StartActivity(noteActivity)
End Sub

Sub NotesTransition2 As ResumableSub
	notesOpen.SetGif(File.DirAssets, "OpenNotes2.GIF")
	notesOpen.mBase.Visible = True
	dnotesOpen.SetGif(File.DirAssets, "DOpenNotes2.GIF")
	dnotesOpen.mBase.Visible = True
	noteBook.Enabled = False
	noteBook.Visible = False 'bat ayaw mawala T-T
	
	Sleep(1500)
    
	StartActivity(noteActivity)
End Sub

Sub NotesTransition3 As ResumableSub
	notesOpen.SetGif(File.DirAssets, "OpenNotes3.GIF")
	notesOpen.mBase.Visible = True
	dnotesOpen.SetGif(File.DirAssets, "DOpenNotes3.GIF")
	dnotesOpen.mBase.Visible = True
	noteBook.Enabled = False
	noteBook.Visible = False 'bat ayaw mawala T-T
	
	Sleep(1500)
    
	StartActivity(noteActivity)
End Sub

Private Sub corkie_Click
	StartActivity(corkActivity)
End Sub

Private Sub plant_Click
	StartActivity(themeActivity)
End Sub
'----------------------------------------LONG CLICK---------------------------------------------

Private Sub calendar_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(0)
		Return
	End If
End Sub

Private Sub clockBtn_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(1)
		Return
	End If
End Sub

Private Sub clockLightBtn_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(1)
		Return
	End If
End Sub

Private Sub corkie_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(2)
		Return
	End If
End Sub

Private Sub bookie_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(3)
		Return
	End If
End Sub

Private Sub mP_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(4)
		Return
	End If
End Sub

Private Sub noteBook_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(5)
		Return
	End If
End Sub

Private Sub todolistBtn_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(6)
		Return
	End If
End Sub

Private Sub plant_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(7)
		Return
	End If
End Sub

Private Sub lamp_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(8)
		Return
	End If
End Sub

Private Sub dlamp_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(8)
		Return
	End If
End Sub

Private Sub navBtn_LongClick
	showInfoPopup
	If infoPnl <> Null Then
		infoPnl.Visible = True
		infoPnl.BringToFront
		showInfoPage(9)
		Return
	End If
End Sub
