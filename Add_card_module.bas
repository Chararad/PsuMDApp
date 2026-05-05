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

	'global variable mainly for layout
	Private QuestionET As EditText
	Private AnswerET As EditText
	Private spsubdecks As Spinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("ACMLayout")
			Else
				Activity.LoadLayout("ACMLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("ACMLayout2")
			Else
				Activity.LoadLayout("ACMLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("ACMLayout3")
			Else
				Activity.LoadLayout("ACMLayoutDark3")
			End If
	End Select
	
	'recovering all the subdecks
	Dim tappeddeck As Map =  FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck) 'main. means its from the main activity/module
	
	'displaying all subdecks to choose from the spinner (drop down menu)
	For Each subdeckName As String In tappeddeck.keys
		spsubdecks.Add(subdeckName)
	Next
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub SaveDecks
	FlashcardActivity.kvs.Put("deck_data", FlashcardActivity.deck)
End Sub


Private Sub SaveCard_Click
	'saving card to the chosen subdeck
	
	'recovering all subdecks
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	
	'condition for when the question aand answer are empty
	If QuestionET.Text = "" Then
		MsgboxAsync("Enter the Question!", "Error")
	Else if AnswerET.Text = "" Then
		MsgboxAsync("Enter the Answer!", "Error")
		
	Else
		'take the name of the chosen subdeck from the spinner
		Dim chosensubdeck As String = spsubdecks.SelectedItem
		
		'recovering flashcards from the chosen subdeck
		Dim flashcards As List = tappeddeck.Get(chosensubdeck)
		
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

Private Sub backbtn_Click
	'finishing activities (gives effect of going back)
	Activity.Finish
End Sub