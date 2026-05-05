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
	Dim praise As Boolean = False
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Private DeckName_Label As Label
	Private Question As Label
	Private Answer As Label
	
	Dim cards As List
	Dim currentindex As Int 'to find the current index of the card
	Private showAnswerbtn As Button
	Private pb As ProgressBar
	Private Progress As Label
	Private nextbtn As Button
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("AARLayout")
			Else
				Activity.LoadLayout("AARLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("AARLayout")
			Else
				Activity.LoadLayout("AARLayoutDark")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("AARLayout")
			Else
				Activity.LoadLayout("AARLayoutDark")
			End If
	End Select
	
	'take the selected deck from main
	Dim tappeddeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	'take the subdeck from subdeck module
	cards = tappeddeck.Get(Subdeck_Module.selectedsubdeck)
	
	currentindex = 0
	DeckName_Label.text = Subdeck_Module.selectedsubdeck
	
	ShuffleCards(cards)
	ShowProgress
	'function for showing the card itself
	ShowCard
	
End Sub

Sub ShowProgress
	Dim totalsession As Int = cards.Size
	Dim studied As Int = currentindex
	Dim percent As Int = (studied * 100)/ totalsession
	pb.Progress = percent
	Progress.Text = studied & "/" & totalsession & " " & percent & "%"
End Sub

Sub ShuffleCards(cardList As List)
	'for shuffling cards
	For i = cardList.Size-1 To 1 Step -1
		Dim j As Int = Rnd(0, i+1)
		
		Dim temp As Object = cardList.Get(i)
		cardList.Set(i, cardList.Get(j))
		cardList.Set(j, temp)
	Next
End Sub

Sub ShowCard
	
	'get the flashcard from the subdeck
	Dim card As Map = cards.Get(currentindex)
	'take question from the flashcard
	Question.Text = card.Get("Q")
	Answer.Text = ""
End Sub


Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub showAnswerbtn_Click
	'show answer but in a switch(hide answer if answer and shown and show answer if its hidden)
	'get the current index
	Dim card As Map = cards.Get(currentindex)
	'condition for switch
	If showAnswerbtn.Text = "Show Answer" Then
		'show answer
		Answer.Text = card.Get("A")
		showAnswerbtn.Text = "Hide Answer"
	Else
		'hide answer
		Answer.Text = ""
		showAnswerbtn.Text = "Show Answer"
		
	End If
	
	nextbtn.Visible = True
End Sub

Private Sub nextbtn_Click
	nextbtn.Visible = False
	'next card by incrementing index and usinf the show function
	showAnswerbtn.Text = "Show Answer"
	currentindex = currentindex +1
	'go back to index 0  when index = size
	If currentindex >= cards.Size Then
		ShuffleCards(cards)
		praise = True
		MsgboxAsync("You've finished your subdeck", "Congratulations")
		Activity.finish
		Return
	End If
	ShowCard
	ShowProgress
End Sub

Private Sub goback_Click
	'go back
	Activity.finish
End Sub

Private Sub backbtn_Click
	'go back to previous question by decrementing and usinf show function
	showAnswerbtn.Text = "Show Answer"
	currentindex = currentindex -1
	If currentindex < 0 Then
		currentindex = cards.Size -1
	End If
	
	ShowCard
	
End Sub