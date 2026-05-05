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

	Private subdeckname As Label
	
	Private QuestionET As EditText
	Private AnswerET As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("ACM2Layout")
			Else
				Activity.LoadLayout("ACM2LayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("ACM2Layout2")
			Else
				Activity.LoadLayout("ACM2LayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("ACM2Layout3")
			Else
				Activity.LoadLayout("ACM2LayoutDark3")
			End If
	End Select
	
	'label for subdeck for cards to be added
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	
	subdeckname.text = Card_Module.subdeck
	
	If Card_Module.isedit = True Then
		Dim flashcard As List = tappeddeck.Get(Card_Module.subdeck)
		Dim card As Map = flashcard.Get(Card_Module.editindex)
		QuestionET.Text = card.Get("Q")
		AnswerET.Text = card.Get("A")
	End If
	
End Sub

Sub SaveDecks
	FlashcardActivity.kvs.Put("deck_data", FlashcardActivity.deck)
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Card_Module.Isedit = False
	QuestionET.Text = ""
	AnswerET.Text = ""
End Sub


Private Sub backbtn_Click
	'go back
	Activity.finish
End Sub

Private Sub SaveCard_Click
	'recovering all subdecks
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	'Editing prupose (reusing this activity)
	If Card_Module.isEdit = True Then
		Card_Module.isEdit = False
	
		'condition for when the question aand answer are empty
		If QuestionET.Text = "" Then
			MsgboxAsync("Enter the Question!", "Error")
		Else if AnswerET.Text = "" Then
			MsgboxAsync("Enter the Answer!", "Error")
		
		Else
			Dim Editcards As List = tappeddeck.Get(Card_Module.subdeck)
			Dim card As Map = Editcards.Get(Card_Module.editindex)
			card.Put("Q", QuestionET.text)
			card.Put("A", AnswerET.text)
		End If
		SaveDecks
		Activity.Finish
		Return
	End If
	'saving card to the chosen subdeck
	
	
	
	'condition for when the question aand answer are empty
	If QuestionET.Text = "" Then
		MsgboxAsync("Enter the Question!", "Error")
	Else if AnswerET.Text = "" Then
		MsgboxAsync("Enter the Answer!", "Error")
		
	Else
		
		'recovering flashcards from the chosen subdeck
		Dim flashcards As List = tappeddeck.Get(Card_Module.subdeck)
		
		'initialization of cards (to input the question and answer input)
		Dim cards As Map
		cards.initialize
		cards.Put("Q", QuestionET.Text)
		cards.Put("A", AnswerET.Text)
		
		'adding cards as map to the flashcard (saving the qanda in the flashcards list)
		flashcards.Add(cards)
		QuestionET.Text = ""
		AnswerET.Text = ""
		
		SaveDecks
		'feedback
		MsgboxAsync("Card is Successfully Saved", "Card Saved!")
	End If
	
End Sub