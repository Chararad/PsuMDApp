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
	Activity.AddView(infoPnl, 75dip, 205dip, 300dip, 340dip)
	infoPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)

	Dim closeBtn As Button
	closeBtn.Initialize("infoPnlClose")
	closeBtn.Text = "x"
	closeBtn.TextSize = 7
	infoPnl.AddView(closeBtn, 265dip, 8dip, 28dip, 28dip)

	infoTitleLbl.Initialize("")
	infoTitleLbl.TextSize = 16
	infoTitleLbl.Gravity = Gravity.CENTER_HORIZONTAL
	infoPnl.AddView(infoTitleLbl, 12dip, 12dip, 248dip, 30dip)

	infoDescLbl.Initialize("")
	infoDescLbl.TextSize = 10
	infoDescLbl.Gravity = Gravity.TOP
	infoDescLbl.SingleLine = False
	infoPnl.AddView(infoDescLbl, 12dip, 43dip, 276dip, 280dip)

	infoPageLbl.Initialize("")
	infoPageLbl.Gravity = Gravity.CENTER_HORIZONTAL
	infoPageLbl.TextSize = 10
	infoPnl.AddView(infoPageLbl, 95dip, 184dip, 110dip, 35dip)


	showInfoPage(0)
End Sub

Private Sub showInfoPage(page As Int)
	infoPage = page
	Select page
		Case 0
			infoDescLbl.TextSize = 12
			infoTitleLbl.Text = "Calendar"
			infoDescLbl.Text = "1. Tap the Calendar on the home screen to open the Scheduling System." & CRLF & _
								"2. You'll land on the Month View by default — a full overview of your calendar." & CRLF & _
								"3. Use the Menu Button (upper left) to switch between Month, Day, and Schedule views." & CRLF & _
								"4. Tap any date to open a detailed hour-by-hour timeline for that day." & CRLF & _
								"5. Select any time slot to begin creating a schedule entry." & CRLF & _
								"6. Tap the + Icon and choose from: Add Event, Add Task, Birthday, or Out of Office." & CRLF & _
								"7. Press Save to confirm, or Delete to remove an existing entry." & CRLF & _
								"8. Use the Arrow Down Button to navigate between months and years." & CRLF & _
								"9. To share your schedule with a group, tap the group tab and tap the join/create button and enter or create your group."
		Case 1
			infoDescLbl.TextSize = 14
			infoTitleLbl.Text = "Clock"
			infoDescLbl.Text = "1. Tap the Clock on the home screen To open the Pomodoro timer." & CRLF & _
								"2. Press Start To begin a 25-minute focus session." & CRLF & _
								"3. When the session ends, the timer automatically shifts to a short break (3 mins) Or long break (10 mins)." & CRLF & _
								"4. Use the Next button To manually switch between Pomodoro, short break, And long break modes." & CRLF & _
								"5. Press Pause anytime To pause the timer." & CRLF & _
								"6. Tap Settings To customize the length of each session To your preference." & CRLF & _
								"7. You can also switch the clock display format from within Settings."
		Case 2
			infoDescLbl.TextSize = 16
			infoTitleLbl.Text = "Corkboard"
			infoDescLbl.Text = "1. Tap the Corkboard on the home screen to open your digital canvas." & CRLF & _
								"2. Tap the Note Button to add a sticky note — type your reminder or idea and place it anywhere on the board." & CRLF & _
								"3. Tap the Image Button to pin a photo or image onto the board." & CRLF & _
								"4. Tap the Canvas Tool to draw, sketch, or map out ideas freehand." & CRLF & _
								"5. Press and drag any element to reposition it wherever you like." & CRLF & _
								"6. Arrange your board however feels right — there's no wrong way to use it."
		Case 3
			infoDescLbl.TextSize = 10
			infoTitleLbl.Text = "Flashcards"
			infoDescLbl.Text = "1. Tap the Books on the home screen to open the Flashcards feature." & CRLF & _
							"2. Tap the + Icon to create a new deck — enter a name and save." & CRLF & _
							"3. Long-press a deck to access options: Add Card, Browse Cards, Rename, Create Subdeck, or Delete." & CRLF & _
							"4. To add a card, open a deck and tap the + Icon — enter the question on one side and the answer on the other." & CRLF & _
							"5. To review, open a deck and tap a card — press Show Answer to flip it." & CRLF & _
							"6. Use the Next button to move to the next card, or Refresh to restart the set." & CRLF & _
							"7. Use Active Recall mode To challenge yourself To remember the answer before flipping." & CRLF & _
							"8. To edit r remove a card, long-press it And select your action." & CRLF & _
							"9. To generate flashcards from a File, tap the Files To Flashcard button on any subdeck, upload your document, and Athena will build a full deck For you automatically." & CRLF & _
							"10. To generate flashcards from a topic, tap the AI cards And subdecks button on any deck, and Athena will build the rest for you."
		Case 4
			infoDescLbl.TextSize = 16
			infoTitleLbl.Text = "Music Player"
			infoDescLbl.Text = "1. Tap the Music Player on the home screen to open it." & CRLF & _
								"2. Press Play to start the lo-fi music." & CRLF & _
								"3. Use the Select option to choose a specific track from the library." & CRLF & _
								"4. Press Next to skip to the next track." & CRLF & _
								"5. Press Pause anytime to stop the music." & CRLF & _
								"6. Scroll through the expanded music library for more track options." & CRLF & _
								"7. To add your own music, tap the Upload Music button and select a song from your device."
		Case 5
			infoDescLbl.TextSize = 14
			infoTitleLbl.Text = "Notepad"
			infoDescLbl.Text = "1. Tap the Notepad on the home screen to open the Notes feature." & CRLF & _
								"2. Press the + Button to create a new note." & CRLF & _
								"3. Enter a title, add relevant tags, then type your content in the body field." & CRLF & _
								"4. Press Save when you're done." & CRLF & _
								"5. To find a note, use the search bar — type the title or a tag and your note appears instantly." & CRLF & _
								"6. To delete a note, long-press it and tap Yes to confirm." & CRLF & _
								"7. To generate summarized notes from a file, tap Insert PDF button, upload your file, and let Athena do the rest."
		Case 6
			infoDescLbl.TextSize = 14
			infoTitleLbl.Text = "To-do List"
			infoDescLbl.Text = "1. Tap the PC Screen on the home screen to open the To-Do List." & CRLF & _
								"2. Press + New List to create a new list." & CRLF & _
								"3. Open the list and tap the input field to add a task." & CRLF & _
								"4. Type your task and press Enter to save it." & CRLF & _
								"5. Tap the checkbox beside a task to mark it as complete — watch your progress percentage go up." & CRLF & _
								"6. To delete a task or list, long-press it and confirm deletion." & CRLF & _
								"7. To share your to-do list with a group, tap the group tab and tap the join/create button and enter or create your group."
		Case 7
			infoDescLbl.TextSize = 22
			infoTitleLbl.Text = "Themes"
			infoDescLbl.Text = "Themes let you put your own fun twist on the app. Choose from a range of styles and color schemes to dress up your experience, making the space you study and plan in feel a little more like yours."
		Case 8
			infoDescLbl.TextSize = 22
			infoTitleLbl.Text = "Lamp"
			infoDescLbl.Text = "The lamp gives you control over how the app looks in any lighting. Switch effortlessly between light mode for bright environments and dark mode for low-light settings, so your eyes stay comfortable no matter when or where you're working."
		Case 9
			infoDescLbl.TextSize = 22
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

Private Sub statsBtn_Click
	StartActivity(Leaderboard)
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


