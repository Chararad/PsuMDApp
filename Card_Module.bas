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