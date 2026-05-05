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
	Dim cards As List
	Dim currentindex As Int
	Private Question As Label
	Private Answer As Label
	Private DeckName_Label As Label
	Private showAnswerbtn As Button
	Private pb As ProgressBar
	Private Progress As Label
	Private nextbtn As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("AARLayout")
			Else
				Activity.LoadLayout("AEMLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("AARLayout2")
			Else
				Activity.LoadLayout("AARLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("AARLayout3")
			Else
				Activity.LoadLayout("AARLayoutDark3")
			End If
	End Select

	cards.Initialize
	RndSeed(DateTime.Now) 'seeding randomizer
	
	'getting all cards across the all subdecks into a cards as map
	Dim chosendeck As Map = FlashcardActivity.deck.Get(FlashcardActivity.selecteddeck)
	For Each subdeckname As String In chosendeck.Keys
		Dim subcards As List = chosendeck.Get(subdeckname)
		For Each card As Map In subcards
			Dim newcard As Map
			newcard.Initialize
			newcard.Put("Q", card.Get("Q"))
			newcard.Put("A", card.Get("A"))
			newcard.Put("subdeck", subdeckname) 'get subdeck name (tags)
			cards.Add(newcard)
		Next
	Next
	'shuffle function
	ShuffleCards(cards)
	currentindex = 0
	'use the function showcard to show the card
	Showcard
	ShowProgress
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

Sub Showcard
	'show the card question
	Dim card As Map = cards.Get(currentindex)
	Question.Text = card.Get("Q")
	Answer.Text = ""
	DeckName_Label.Text = card.Get("subdeck")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub showAnswerbtn_Click
	'show answer
	Dim card As Map = cards.Get(currentindex)
	If showAnswerbtn.Text = "Show Answer" Then
		Answer.Text = card.Get("A")
		showAnswerbtn.Text = "Hide Answer"
		
	Else
		Answer.Text = ""
		showAnswerbtn.Text = "Show Answer"
	End If
	nextbtn.Visible = True
End Sub

Private Sub backbtn_Click
	'back
	showAnswerbtn.Text = "Show Answer"
	If currentindex = 0 Then
		
	Else
		currentindex = currentindex-1
		Showcard
	End If
End Sub

Private Sub nextbtn_Click
	'next
	nextbtn.Visible = False
	showAnswerbtn.Text = "Show Answer"
	currentindex = currentindex +1
	If currentindex >= cards.Size Then
		MsgboxAsync("Decks Finished", "Active Recall")
		praise = True
		Activity.finish
		Return
	End If
	
	Showcard
	ShowProgress
End Sub

Private Sub goback_Click
	Activity.finish
End Sub