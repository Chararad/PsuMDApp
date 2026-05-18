B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim subdeck As String
	Dim isEdit As Boolean
	Dim editindex As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private subdecklabel As Label
	Private ScrollView1 As ScrollView
	Private deleteconfirmation As Panel
	Dim numtag As Int
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
				Activity.LoadLayout("Card_ModuleLayout")
			Else
				Activity.LoadLayout("Card_ModuleLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("Card_ModuleLayout2")
			Else
				Activity.LoadLayout("Card_ModuleLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("Card_ModuleLayout3")
			Else
				Activity.LoadLayout("Card_ModuleLayoutDark3")
			End If
	End Select
	
	cc.Initialize("CC")
	'subdeck name
	subdecklabel.Text = Subdeck_Module.selectedsubdeck
	
	Dim tappedDeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	Dim subdeckcards As List = tappedDeck.Get(Subdeck_Module.selectedsubdeck)
	
	'show all cards function in list view
	ShowSubdeckCards(subdeckcards)
	
End Sub

Sub ShowSubdeckCards(cardsList As List)
	'custom scrollview
	ScrollView1.Panel.RemoveAllViews
	'initializing card sizes for the scroll view
	Dim topPos As Int = 0
	Dim cardHeight As Int = 150dip 'height
	
	For i = 0 To cardsList.Size -1
		Dim card As Map = cardsList.Get(i)
		Dim p As Panel
		p.Initialize("")
		If Starter.darkMode = False Then
			p.Color = Colors.White
		Else
			p.Color = Colors.Black
		End If
		ScrollView1.Panel.AddView(p, 10dip, topPos, ScrollView1.Width - 20dip, cardHeight)
	
		Dim lbl As Label
		lbl.Initialize("")
		lbl.Text = "Q: " & card.Get("Q") & CRLF & "A: " & card.Get("A")
		If Starter.darkMode = False Then
			lbl.TextColor = Colors.black
		Else
			lbl.TextColor = Colors.White
		End If
		lbl.TextSize = 12
		lbl.SingleLine = False
		
		p.AddView(lbl, 10dip, 10dip, ScrollView1.Width - 20dip, 30dip)
		topPos = topPos + lbl.height + 10dip
		
		Dim btnwidth As Int = 100dip
		
		Dim editbtn As Button
		editbtn.Initialize("Editbtn") 'btn name
		editbtn.Tag = i 'tag/index
		editbtn.Text = "Edit" 'button text display
		p.AddView(editbtn, 30dip, 100dip, btnwidth, 40dip) '(horizontal position, vertical position, width, height)
		
		Dim deletebtn As Button
		deletebtn.Initialize("Deletebtn")
		deletebtn.Tag = i
		deletebtn.Text = "Delete"
		p.AddView(deletebtn, 200dip, 100dip, btnwidth, 40dip)
		topPos = topPos + cardHeight + 10dip
	Next
	ScrollView1.Panel.Height = topPos + 10dip
End Sub

'edit card
Sub editbtn_Click
	'edit card - reuse the activity for saving a card
	subdeck = Subdeck_Module.selectedsubdeck
	Dim b As Button = Sender
	Dim index As Int = b.Tag
	editindex = index
	isEdit = True
	
	StartActivity(add_card_module2)
	
End Sub
'delete card
Sub deletebtn_click
	'directly deleting the index and reload
	Dim b As Button = Sender
	Dim index As Int = b.Tag
	numtag = index
	deleteconfirmation.Visible = True
	
End Sub

Sub Activity_Resume
	
	If active_recall.praise = True Then
		active_recall.praise = False
		MsgboxAsync("You Finished Your Sub-Deck", "Congratulations")
	End If
	'update when activity is started and needs update
	Dim tappedDeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	Dim subdeckcards As List = tappedDeck.Get(Subdeck_Module.selectedsubdeck)
	ShowSubdeckCards(subdeckcards)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Private Sub backbtn_Click
	'go back
	Activity.Finish
End Sub

Private Sub activerecall_Click
	'active recall activity
	Dim tappedDeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	Dim subdeckcards As List = tappedDeck.Get(Subdeck_Module.selectedsubdeck)
	Dim number_of_cards As Int = subdeckcards.size
	
	If number_of_cards = 0 Then
		MsgboxAsync("No cards available", "Error")
		Return
	End If
	StartActivity(active_recall)
End Sub

Private Sub addbtn_Click
	'add card button
	subdeck = Subdeck_Module.selectedsubdeck
	StartActivity(add_card_module2)
End Sub

Private Sub confirmdelete_Click
	Dim tappedDeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	Dim cards As List = tappedDeck.Get(Subdeck_Module.selectedsubdeck)
	
	cards.RemoveAt(numtag)
	ShowSubdeckCards(cards)
	deleteconfirmation.Visible = False
End Sub

Private Sub canceldelete_Click
	deleteconfirmation.Visible = False
End Sub


Private Sub ftf_btn_Click
	cc.Show("application/pdf", "Select PDF")
End Sub

Sub CC_RESULT (Success As Boolean, dir As String , fileName As String)
	
	If Success Then
		Log("Dir: " & dir)
		Log("File: " & fileName)
		
		File.Copy(dir, fileName, File.DirInternal, "temp.pdf")
		Log("PDF saved as temp.pdf")
		
		File.Copy(dir, fileName, File.DirInternal, "temp.pdf")
		ProgressDialogShow("Generating Cards...")
		GenerateFlashCardsFromPDF
		
	Else
		Log("User Cancelled")
	End If
End Sub

Sub GenerateFlashCardsFromPDF

	'--- LOAD PDF ---
	Dim In As InputStream = File.OpenInput(File.DirInternal, "temp.pdf")
	Dim bytes() As Byte = Bit.InputStreamToBytes(In)
	In.Close



	Dim su As StringUtils
	Dim base64 As String = su.EncodeBase64(bytes)

	'--- BUILD PROMPT (AUTO MODE) ---
	Dim prompt As String = _
    "Create flashcards from the provided PDF content." & CRLF & _
    "RULES:" & CRLF & _
    "- Extract ONLY from the PDF" & CRLF & _
    "- Return ONLY valid JSON" & CRLF & _
    "- No markdown, no explanations" & CRLF & _
    "- Format exactly:" & CRLF & _
    "{" & _
    """flashcards"": [" & _
    "{" & _
    """question"": ""...""," & _
    """answer"": ""...""" & _
    "}" & _
    "]" & _
    "}"

	'--- BUILD REQUEST BODY ---
	Dim root As Map
	root.Initialize

	Dim contents As List
	contents.Initialize

	Dim contentItem As Map
	contentItem.Initialize

	Dim parts As List
	parts.Initialize

	'1. PDF PART
	Dim filePart As Map
	filePart.Initialize

	Dim inline As Map
	inline.Initialize
	inline.Put("mime_type", "application/pdf")
	inline.Put("data", base64)

	filePart.Put("inline_data", inline)
	parts.Add(filePart)

	'2. PROMPT PART
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

	Log("AUTO PDF REQUEST: ")
	Log(json)

	Dim URL As String = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" & MyAPIKey

	Dim Job As HttpJob
	Job.Initialize("GeminiPDF", Me)
	Job.PostString(URL, json)
	Job.GetRequest.SetContentType("application/json")

End Sub

Sub JobDone (job As HttpJob)
	ProgressDialogHide
	If job.Success Then
		Dim response As String
		response = job.GetString
		Log("RAW RESPONSE: ")
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

		Log("AI JSON:")
		Log(aiText)
		File.WriteString(File.DirInternal, "cached_ai.txt", aiText)
		ParseFlashcard(aiText)
		
		Dim tappedDeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
		Dim subdeckcards As List = tappedDeck.Get(Subdeck_Module.selectedsubdeck)
	
		'show all cards function in list view
		ShowSubdeckCards(subdeckcards)
	
	Else
		Log("ERROR: ")
		Log(job.ErrorMessage)
		Msgbox("Error Parsing your PDF file", "Error")
	End If
	
	job.Release
End Sub

Sub ParseFlashcard (jsonText As String)
	Dim tappedDeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	Dim subdeckcards As List = tappedDeck.Get(Subdeck_Module.selectedsubdeck)
	
	Try

		Dim jp As JSONParser
		jp.Initialize(jsonText)

		Dim root As Map = jp.NextObject

		Dim flashcards As List = root.Get("flashcards")

		For Each card As Map In flashcards
			
			Dim cards As Map
			cards.Initialize

			Dim question As String = card.Get("question")

			Dim answer As String = card.Get("answer")
			
			cards.Put("Q", question)
			cards.Put("A", answer)
			subdeckcards.Add(cards)

			Log("===================")
			Log("QUESTION: " & question)
			Log("ANSWER: " & answer)

		Next

	Catch

		Log("INVALID JSON")

	End Try

End Sub