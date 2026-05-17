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
	
	'for global activities
	Dim selectedsubdeck As String 'chosen subdeck
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	'global variables (mostly layouts)
	Private Addbtn As Button
	Private addpanel2 As Panel
	Dim alldecks As Map = FlashcardActivity.deck 'taking the whole deck from the main
	Dim selecteddeck As String = FlashcardActivity.selecteddeck 'taking selected deck from the main
	Private LVSubdecks As ListView
	Private decknamelabel As Label
	Private addpanel As Panel
	Private et1 As EditText
	Private AR_confirmationpanel As Panel
	Private confirmlabel As Label
	Dim number_of_cards As Int = 0 'to count the number of cards
	Private alterpanel As Panel
	Private renamepanel As Panel
	Private renameet As EditText
	Private deleteconfirmation As Panel
	Private topic_et As EditText
	Private topic_panel As Panel
	Dim j As JSON
	Private cc As ContentChooser
	Private pickPDFBtn As Button
	Dim api1 As String = "AIzaSyAGccTYG-Mscl_16Z72t"
	Dim api2 As String = "-GIN9ITMdrDGhQ"
	Dim MyAPIKey As String = api1&api2
	Dim AIGlobalText As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("Subdeck_ModuleLayout")
			Else
				Activity.LoadLayout("Subdeck_ModuleLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("Subdeck_ModuleLayout2")
			Else
				Activity.LoadLayout("Subdeck_ModuleLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("Subdeck_ModuleLayout3")
			Else
				Activity.LoadLayout("Subdeck_ModuleLayoutDark3")
			End If
	End Select
	
	If Starter.darkMode = False Then
		'to change the color of label etxt for each lit view for visibility
		LVSubdecks.SingleLineLayout.Label.textColor = Colors.black
	Else
		LVSubdecks.SingleLineLayout.Label.textColor = Colors.White
	End If
	
	cc.Initialize("CC")
	'design for add button
	Dim radius As Int = Addbtn.Width/2
	Dim cd As ColorDrawable
	cd.Initialize(Colors.Gray, radius)
	Addbtn.Background = cd
	
	'to label the deck chosen erlier
	decknamelabel.Text = selecteddeck
	
	
	Refresh
	
End Sub

Sub SaveDecks
	FlashcardActivity.kvs.Put("deck_data", FlashcardActivity.deck)
End Sub

Sub Refresh
	LVSubdecks.clear
	'get the selected deck
	Dim tappeddeck As Map = alldecks.Get(selecteddeck)
	
	'displays all subdecks in a dck chosen
	For Each deckName As String In tappeddeck.keys
		LVSubdecks.AddSingleLine(deckName)
	Next
End Sub

Sub Activity_Resume
	If all_active_recall.praise = True Then
		all_active_recall.praise = False
		MsgboxAsync("You Finished Your Deck", "Congratulations")
	End If
	AR_confirmationpanel.Visible = False
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub Addbtn_Click
	'a panel for addcard and add subdeck
	If addpanel2.Visible = False Then
		addpanel2.Visible = True
	Else
		addpanel2.Visible = False
	End If
	
End Sub

Private Sub addcard_Click
	'start activity for adding cards
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	If tappeddeck.Size = 0 Then
		MsgboxAsync("Create A Sub-Deck first", "Error")
		Return
	End If
	StartActivity(Add_card_module)
End Sub

Private Sub addsub_Click
	'for adding subdeck panel
	addpanel.Visible = True
End Sub

Private Sub cancel_Click
	'cancel the addsubdeck
	addpanel.Visible = False
	et1.Text = ""
End Sub

Private Sub create_Click
	'creating subdecks
	'initialization of map of deck and list of flashcards
	Dim tappeddeck As Map = alldecks.Get(selecteddeck)
	Dim flashcards As List
	'initialize if empty (empty so we can put smth later)
	flashcards.initialize
	
	'check if subdeck exist
	For Each name In tappeddeck.Keys
		If et1.Text = name Then
			MsgboxAsync("Sub-Deck Already Exist", "Error")
			Return
		End If
	Next
	
	'check if the editext is empty
	If et1.Text = "" Then
		MsgboxAsync("Sub-Deck must have a name", "Error")
	Else
		'adding subdeck to map and listview
		addpanel.Visible = False
		LVSubdecks.AddSingleLine(et1.Text)
		tappeddeck.Put(et1.Text, flashcards)
		SaveDecks
		'removing the text
		et1.Text = ""
	End If

End Sub

Private Sub goback_Click
	'go back
	Activity.Finish
End Sub

Private Sub activerecall_Click
	'for active recall for whole deck
	'for counting the number of cards
	number_of_cards = 0
	'chosen deck
	Dim chosendeck As Map = alldecks.Get(selecteddeck)
	'counting all cards
	For Each deckName As String In chosendeck.keys
		Dim flashacards As List = chosendeck.Get(deckName)
		number_of_cards = number_of_cards + flashacards.size
	Next
	'confirmation for active recall
	AR_confirmationpanel.Visible = True
	confirmlabel.Text = "You got " & number_of_cards & " cards"
End Sub

Private Sub startArbtn_Click
	'activity active recall
	If number_of_cards = 0 Then
		MsgboxAsync("No cards available", "Error")
		Return
	End If
	StartActivity(all_active_recall)
End Sub

Private Sub cancelconfirmation_Click
	'cancellation
	AR_confirmationpanel.Visible = False
End Sub

Private Sub LVSubdecks_ItemClick (Position As Int, Value As Object)
	'per click to subdeck cards
	selectedsubdeck = Value 'gives the chsoen subdeck to another activity
	'start the activity
	StartActivity(Card_Module)
End Sub

Private Sub backbtn_Click
	Activity.Finish
End Sub

Private Sub deletesubdeck_Click
	alterpanel.Visible = False
	deleteconfirmation.Visible = True
End Sub

Private Sub renamesubdeck_Click
	alterpanel.Visible = False
	renamepanel.visible = True
End Sub

Private Sub cancelalter_Click
	alterpanel.Visible = False
End Sub

Private Sub LVSubdecks_ItemLongClick (Position As Int, Value As Object)
	alterpanel.Visible = True
	selectedsubdeck = Value
End Sub

Private Sub cancelrename_Click
	renamepanel.Visible = False
	renameet.Text = ""
End Sub

Private Sub confirmrename_Click
	
	Dim getsubdeck As List
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	If renameet.Text = "" Then
		MsgboxAsync("New Name must have a name", "Error")
		Return
	End If
	For Each names As String In tappeddeck.keys
		getsubdeck = tappeddeck.Get(selectedsubdeck)
		If renameet.Text = names Then
			MsgboxAsync("Sub Deck Name Already Exist", "Error")
			Return
		End If
	Next
	tappeddeck.Remove(selectedsubdeck)
	tappeddeck.Put(renameet.Text, getsubdeck)
	renameet.text = ""
	SaveDecks
	Refresh
	renamepanel.Visible = False
	renamepanel.Visible = False
	
	
End Sub

Private Sub confirmdelete_Click
	Dim tappeddeck As Map = FlashcardActivity.deck.get(FlashcardActivity.selecteddeck)
	tappeddeck.Remove(selectedsubdeck)
	SaveDecks
	Refresh
	deleteconfirmation.Visible = False
End Sub

Private Sub canceldelete_Click
	deleteconfirmation.Visible = False
End Sub

Private Sub AI_cards_Click
	If topic_panel.Visible = True Then
		topic_panel.visible = False
		Return
	Else
		topic_panel.Visible = True
	End If
	
End Sub

Private Sub topic_cancel_Click
	topic_panel.Visible = False
	topic_et.Text = ""
End Sub

Private Sub topic_btn_Click
	If topic_et.Text = "" Then
		Msgbox("Invalid Subdeck Topic", "Error")
		Return
	End If
	
	Dim getsubdeck As List
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	For Each names As String In tappeddeck.keys
		getsubdeck = tappeddeck.Get(selectedsubdeck)
		If topic_et.Text = names Then
			MsgboxAsync("Sub Deck Name Already Exist", "Error")
			Return
		End If
	Next
	
	ProgressDialogShow("Generating Flashcards...")
	
	GenerateFlashCards(topic_et.Text)

	topic_panel.Visible = False
End Sub

Sub GenerateFlashCards(Topic As String)
	
	Dim URL As String = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" & MyAPIKey
	
	Dim Job As HttpJob
	Job.Initialize("Gemini", Me)
	
	Dim prompt As String = _
	"Create flashcards from the Topic below." & _
	"RULES:" & _
	"- Return ONLY valid JSON" & _
	"- No explanations" & _
	"- No markdown" & _
	"- Must have 20+ Flashcards" & _
	"- Format must exactly follow this structure:" & _
"{" & _
	"""flashcards"": [" & _
	"{" & _
	"""question"": ""Question here""," & _
	"""answer"": ""Answer here""" & _
	"}" & _
	"]" & _
	"}" & _
	"Topic:" & Topic
	
	Dim root As Map
	root.Initialize
	
	Dim contents As List
	contents.Initialize
	
	Dim contentItem As Map
	contentItem.Initialize
	
	Dim parts As List
	parts.Initialize
	
	Dim textPart As Map
	textPart.Initialize
	
	textPart.Put("text", prompt)
	parts.Add(textPart)
	
	contentItem.Put("parts", parts)
	contents.Add(contentItem)
	root.Put("contents", contents)
	
	Dim gen As JSONGenerator
	gen.Initialize(root)
	
	Dim json As String = gen.ToString
	
	Log("REQUEST: ")
	Log(json)
	
	Job.PostString(URL, json)
	Job.GetRequest.SetContentType("application/json")
	
End Sub


Sub JobDone (job As HttpJob)
	ProgressDialogHide
	If job.Success Then

		Dim response As String = job.GetString
		Log(response)

		Dim jp As JSONParser
		jp.Initialize(response)

		Dim root As Map = jp.NextObject
		Dim candidates As List = root.Get("candidates")
		Dim candidate As Map = candidates.Get(0)
		Dim content As Map = candidate.Get("content")
		Dim parts As List = content.Get("parts")
		Dim firstPart As Map = parts.Get(0)

		Dim aiText As String = firstPart.Get("text")
		AIGlobalText = aiText

		File.WriteString(File.DirInternal, "cached_ai.txt", aiText)

		Dim flashcards As List = ParseFlashcard(aiText)
		
		Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)

		LVSubdecks.AddSingleLine(topic_et.Text)
		tappeddeck.Put(topic_et.Text, flashcards)
		topic_et.Text = ""
		SaveDecks

	Else
		Log(job.ErrorMessage)
		Msgbox("Error making your AI Flashcards", "Error")
	End If

	job.Release
End Sub


Sub ParseFlashcard (jsonText As String) As List
	
	Dim flashcard As List
	flashcard.Initialize
	Try

		Dim jp As JSONParser
		jp.Initialize(jsonText)

		Dim root As Map = jp.NextObject

		Dim flashcards As List = root.Get("flashcards")

		For Each card As Map In flashcards
			
			Dim cards As Map
			cards.initialize

			Dim question As String = card.Get("question")

			Dim answer As String = card.Get("answer")
			
			cards.Put("Q", question)
			cards.Put("A", answer)
			flashcard.Add(cards)

			Log("===================")
			Log("QUESTION: " & question)
			Log("ANSWER: " & answer)

		Next

	Catch

		Log("INVALID JSON")

	End Try
	
	
	Return flashcard
End Sub