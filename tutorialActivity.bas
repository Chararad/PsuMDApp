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
	Dim tutorialPage As Int = 0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private headerLbl     As Label
	Private bodyLbl       As Label
	Private pageIndicator As Label
	Private btnBack       As Button
	Private btnNext       As Button
	Private chkDontShow   As CheckBox
	Private tutorialImg   As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
				Activity.LoadLayout("tutoriallayout")
	showTutorialPage(0)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause(UserClosed As Boolean)

End Sub

Sub showTutorialPage(page As Int)
	tutorialPage = page

	Select page
		Case 0
			headerLbl.Text = "Welcome to Athena!"
			bodyLbl.Text = "In legend, Athena is the goddess of wisdom — and wisdom is never rushed." & CRLF & CRLF & _
			               "She stepped back, took note of everything, and came back with more than before." & CRLF & CRLF & _
			               "Athena is your all-in-one study companion: a single hub built to keep you " & _
			               "organized, focused, and ahead — no juggling apps, no wasted space."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpwreath.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 1
			headerLbl.Text = "Home Screen  (1/2)"
			bodyLbl.Text = "This is your central hub — beautiful and intuitive." & CRLF & CRLF & _
			               "Everything you need lives right here:" & CRLF & _
			               "  • Pomodoro Timer  (Clock)" & CRLF & _
			               "  • To-Do List  (PC Screen)" & CRLF & _
			               "  • Calendar" & CRLF & _
			               "  • Flashcards  (Books)" & CRLF & _
			               "  • Corkboard" & CRLF & _
			               "  • Notepad" & CRLF & _
			               "  • Lo-fi Music Player" & CRLF & _
			               "  • Dark / Light Mode  (Lamp)" & CRLF & _
			               "  • Themes  (Plant / Shelf Toy)"
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "homescreenui.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 2
			headerLbl.Text = "Home Screen  (2/2)"
			bodyLbl.Text = "The home screen is alive — subtle animations and smooth transitions make " & _
			               "every interaction feel fluid and enjoyable." & CRLF & CRLF & _
			               "Tapping any feature icon shows a short description of that tool." & CRLF & CRLF & _
			               "Opening Athena for the first time? This Tutorial walks you through everything " & _
			               "before you start — so you're never left guessing." & CRLF & CRLF & _
			               "You can reopen this guide anytime from the Help screen."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "dhomescreenui.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 3
			headerLbl.Text = "Navigation & Help"
			bodyLbl.Text = "Navigation Button — your quick-access menu." & CRLF & _
			               "Tap it for a clean list of every feature: Pomodoro, Notepad, Corkboard, " & _
			               "Flashcards, and more. No clutter — just fast, direct navigation." & CRLF & CRLF & _
			               "Help Button — highlights every clickable object on the home screen " & _
			               "and explains what it does, so you'll never feel lost." & CRLF & CRLF & _
			               "Long-press any button to see its name and a step-by-step walkthrough " & _
			               "of how to use that specific feature."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpnav.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 4
			headerLbl.Text = "Lamp — Dark / Light Mode"
			bodyLbl.Text = "Tap the Lamp to instantly switch between Light Mode and Dark Mode." & CRLF & CRLF & _
			               "Whether you're studying under the morning sun or pulling a late-night session, " & _
			               "Athena adjusts with you — reducing eye strain and keeping your focus sharp " & _
			               "no matter the hour." & CRLF & CRLF & _
			               "One tap, and your whole environment transforms."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helplamp.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 5
			headerLbl.Text = "Music Player  (1/2)"
			bodyLbl.Text = "Tap the record player and lo-fi music fills the room — instantly setting " & _
			               "the mood for deep focus." & CRLF & CRLF & _
			               "Controls:" & CRLF & _
			               "  • Play / Pause — start or stop the current track." & CRLF & _
			               "  • Previous / Next — move between songs." & CRLF & _
			               "  • Seek bar — jump to any point in the track." & CRLF & _
			               "  • Playlist — tap any song in the list to play it directly."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpmusic.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 6
			headerLbl.Text = "Music Player  (2/2)"
			bodyLbl.Text = "Athena's music library has grown — more curated lo-fi tracks are now built in." & CRLF & CRLF & _
			               "Have a song that puts you in the zone? Upload it directly into the app " & _
			               "from the player screen anytime." & CRLF & CRLF & _
			               "Your study playlist, your rules."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpmusic.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 7
			headerLbl.Text = "Clock & Pomodoro  (1/2)"
			bodyLbl.Text = "The Clock is more than a timepiece — it is your productivity partner." & CRLF & CRLF & _
			               "Real-time clock:" & CRLF & _
			               "  • Tap the format button to switch between 12-hr and 24-hr display." & CRLF & CRLF & _
			               "Pomodoro method — default durations:" & CRLF & _
			               "  • Pomodoro session  — 25 minutes" & CRLF & _
			               "  • Short break          —   3 minutes" & CRLF & _
			               "  • Long break            — 10 minutes" & CRLF & CRLF & _
			               "After four Pomodoro cycles you automatically earn a longer rest."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpclock.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 8
			headerLbl.Text = "Clock & Pomodoro  (2/2)"
			bodyLbl.Text = "Controls:" & CRLF & _
			               "  • Start / Pause — begin or pause the current session." & CRLF & _
			               "  • Next — cycle between Pomodoro, short break, and long break." & CRLF & CRLF & _
			               "Settings:" & CRLF & _
			               "  • Customize the duration of each timer type to fit your own rhythm." & CRLF & _
			               "  • Change the clock display format." & CRLF & CRLF & _
			               "Stay focused, rest when earned, and keep the cycle going."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpclock.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 9
			headerLbl.Text = "Flashcards  (1/3)"
			bodyLbl.Text = "Tap the Books to open Flashcards — your ultimate memory tool." & CRLF & CRLF & _
			               "Deck management:" & CRLF & _
			               "  • Tap  +  to create a new deck, name it, and save." & CRLF & _
			               "  • Long-press a deck to: add cards, create subdecks, rename, or delete." & CRLF & _
			               "  • Open a deck and tap  +  to add subdecks or cards from inside." & CRLF & CRLF & _
			               "Confirmation is always required before anything is permanently removed."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpflashcard.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 10
			headerLbl.Text = "Flashcards  (2/3)"
			bodyLbl.Text = "Reviewing a deck:" & CRLF & _
			               "  • Answer — flip the card to reveal the answer." & CRLF & _
			               "  • Next — advance to the next card." & CRLF & _
			               "  • Back — revisit the previous card." & CRLF & _
			               "  • Refresh — reshuffle and restart the deck." & CRLF & CRLF & _
			               "Active Recall challenges you to remember before flipping — one of the most " & _
			               "effective techniques for long-term retention." & CRLF & CRLF & _
			               "A progress bar and percentage show how far you have made it through a deck."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpflashcard.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 11
			headerLbl.Text = "Flashcards  (3/3)"
			bodyLbl.Text = "Athena's AI can do the heavy lifting for you." & CRLF & CRLF & _
			               "Upload a file — a PDF, a document, or your lecture notes — and the AI reads " & _
			               "it and automatically generates a full set of flashcards for you." & CRLF & CRLF & _
			               "Your deck, built without the tedious effort." & CRLF & CRLF & _
			               "Because your convenience matters too."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpflashcard.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 12
			headerLbl.Text = "Calendar  (1/3)"
			bodyLbl.Text = "Tap the Calendar to open your personal time designer." & CRLF & CRLF & _
			               "By default you land on Month View — your big-picture overview of everything ahead." & CRLF & CRLF & _
			               "Three views via the Menu Button (top-left):" & CRLF & _
			               "  • Schedule View — streamlined list of all upcoming tasks and events." & CRLF & _
			               "  • Day View — focused hour-by-hour agenda for a single day." & CRLF & _
			               "  • Month View — full calendar grid; use the Arrow button to change month/year."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpcalendar.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 13
			headerLbl.Text = "Calendar  (2/3)"
			bodyLbl.Text = "Adding events:" & CRLF & _
			               "  • Tap any date to open that day's hour-by-hour timeline." & CRLF & _
			               "  • Tap any time slot to create a schedule at that time." & CRLF & _
			               "  • Tap  +  and choose from:" & CRLF & _
			               "       - Add Event" & CRLF & _
			               "       - Add Task" & CRLF & _
			               "       - Birthday" & CRLF & _
			               "       - Out of Office" & CRLF & CRLF & _
			               "Always tap Save to confirm — or Delete if plans change."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpcalendar.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 14
			headerLbl.Text = "Calendar  (3/3)"
			bodyLbl.Text = "Schedules are no longer just personal." & CRLF & CRLF & _
			               "Group Collaboration lets you create a group with classmates or coworkers " & _
			               "and share your schedule with them — keeping everyone on the same page for " & _
			               "group projects, study sessions, and deadlines." & CRLF & CRLF & _
			               "Never miss a group deadline again."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpcalendar.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 15
			headerLbl.Text = "To-Do List  (1/2)"
			bodyLbl.Text = "Tap the PC Screen to open the To-Do List — your accountability partner." & CRLF & CRLF & _
			               "Structured checkboxes keep every item actionable." & CRLF & CRLF & _
			               "How to use:" & CRLF & _
			               "  • Tap New List to create a list (school, personal, group work...)." & CRLF & _
			               "  • Open the list, type a task, and press Enter to save." & CRLF & _
			               "  • Check tasks off as you complete them." & CRLF & CRLF & _
			               "Navigate between lists easily from the side panel."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helptodo.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 16
			headerLbl.Text = "To-Do List  (2/2)"
			bodyLbl.Text = "Progress tracker:" & CRLF & _
			               "As you tick off tasks, the percentage tracker updates in real time — " & _
			               "showing exactly how close you are to finishing each list." & CRLF & CRLF & _
			               "Every checkmark is a small win that adds up to something bigger." & CRLF & CRLF & _
			               "Shared lists:" & CRLF & _
			               "Share a To-Do List with your group — assign tasks, track progress together, " & _
			               "and make sure no one falls behind."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helptodo.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 17
			headerLbl.Text = "Notepad  (1/2)"
			bodyLbl.Text = "Tap the Notepad to capture the details that matter most." & CRLF & CRLF & _
			               "Creating a note:" & CRLF & _
			               "  • Tap  +  to start a new note." & CRLF & _
			               "  • Enter a title and add tags to stay organized." & CRLF & _
			               "  • Write your content, then press Save." & CRLF & CRLF & _
			               "Managing notes:" & CRLF & _
			               "  • Long-press a note and confirm to delete it." & CRLF & _
			               "  • Use the search bar — type a title or tag and your note appears instantly."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpnotepad.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 18
			headerLbl.Text = "Notepad  (2/2)"
			bodyLbl.Text = "Athena's AI can summarize for you." & CRLF & CRLF & _
			               "Upload a file or paste a block of text, and the AI reads it and generates " & _
			               "clean, condensed notes — capturing the key ideas without reading every word." & CRLF & CRLF & _
			               "Lecture notes, project ideas, personal reflections — everything stays " & _
			               "structured, accessible, and always within reach."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpnotepad.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 19
			headerLbl.Text = "Corkboard  (1/2)"
			bodyLbl.Text = "Tap the Corkboard — your digital canvas, fully customizable and personal." & CRLF & CRLF & _
			               "What you can add:" & CRLF & _
			               "  • Sticky Notes — quick reminders or brainstorming in a variety of colors." & CRLF & _
			               "  • Images — pin pictures from your gallery to inspire or organize a project." & CRLF & _
			               "  • Canvas — draw freely with colored pens; sketch ideas or map out concepts."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpcorkboard.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 20
			headerLbl.Text = "Corkboard  (2/2)"
			bodyLbl.Text = "The Corkboard is where organization meets creativity." & CRLF & CRLF & _
			               "Arrange, move, and rearrange everything exactly the way you want — " & _
			               "turning abstract ideas into something you can see, interact with, and refine." & CRLF & CRLF & _
			               "It is not just a workspace. It is a reflection of how you think."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpcorkboard.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 21
			headerLbl.Text = "Themes"
			bodyLbl.Text = "Your workspace should feel like yours." & CRLF & CRLF & _
			               "Tap the Plant or the Stuffed Toy on the upper-right shelf to instantly " & _
			               "change the app's entire look and feel." & CRLF & CRLF & _
			               "Three themes to choose from:" & CRLF & _
			               "  • Default              — the classic Athena look." & CRLF & _
			               "  • Y2K Aero          — glossy, retro-futuristic vibes." & CRLF & _
			               "  • Pixelated Rustic — cozy pixel-art aesthetic." & CRLF & CRLF & _
			               "One tap and your environment refreshes — lively, inspiring, and uniquely you."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helptheme.png", tutorialImg.Width, tutorialImg.Height, True)

		Case 22
			headerLbl.Text = "You're All Set!"
			bodyLbl.Text = "That is everything Athena has to offer." & CRLF & CRLF & _
			               "No more juggling apps. No more wasted time." & CRLF & _
			               "Just one hub, one companion, built to keep you organized, motivated, and ahead." & CRLF & CRLF & _
			               "Athena is not just an app — she is your complete study ecosystem." & CRLF & CRLF & _
			               "Rise up and unlock your true potential, warrior of the mind." & CRLF & CRLF & _
			               "Tick  ""Don't show again""  below to skip this guide on future launches." & CRLF & _
			               "You can always reopen it from the Help screen."
			tutorialImg.Bitmap = xui.LoadBitmapResize(File.DirAssets, "helpwreath.png", tutorialImg.Width, tutorialImg.Height, True)

	End Select

	pageIndicator.Text = (page + 1) & " / 23"

	' Show checkbox on last page only
	chkDontShow.Visible = (page = 22)

	' Back button
	btnBack.Enabled = (page > 0)

	' Next / Finish label
	If page = 22 Then
		btnNext.Text = "Finish"
	Else
		btnNext.Text = "Next"
	End If
End Sub

Sub btnNext_Click
	If tutorialPage < 22 Then
		showTutorialPage(tutorialPage + 1)
	Else
		If chkDontShow.Checked Then
			Starter.prefKvs.Put("skipTutorial", True)
		End If
		StartActivity(MainActivity)
		Activity.Finish
	End If
End Sub

Sub btnBack_Click
	If tutorialPage > 0 Then
		showTutorialPage(tutorialPage - 1)
	End If
End Sub
