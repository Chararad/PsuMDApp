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
	Private xui As XUI
	
	'Global variables for all across the activities
	
	Dim deck As Map 'to access deck created
	Dim selecteddeck As String 'selected subdeck as string
	Dim item_longclick As String
	Dim kvs As KeyValueStore
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'Variables for activities (mostly in the layout)
	Private LVdecks As ListView
	Private Addbtn As Button
	Private addpanel As Panel
	Private et1 As EditText
	Private decknamelabel As Label
	Private decksettingpanel As Panel
	
	
	Private renamepanel As Panel
	Private newdeckname As EditText
	
	Private newsubdeckpanel As Panel
	Private newsubdecket As EditText
	Private deleteconfirmation As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Main Activity - active when running the whole program
	'Load Layout - for designer layout
	
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("FlashCardLayout")
			Else
				Activity.LoadLayout("FlashCardLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("FlashCardLayout2")
			Else
				Activity.LoadLayout("FlashCardLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("FlashCardLayout3")
			Else
				Activity.LoadLayout("FlashCardLayoutDark3")
			End If
	End Select
	
	'Resizing Add button (dont mind)
	Dim radius As Int = Addbtn.Width/2
	Dim cd As ColorDrawable
	cd.Initialize(Colors.Gray, radius)
	Addbtn.Background = cd
	
	'recoloring label color for each list view (default color is gray - not visible)
	If Starter.darkMode = False Then
		LVdecks.SingleLineLayout.Label.textcolor = Colors.black
	Else
		LVdecks.SingleLineLayout.Label.textcolor = Colors.White
	End If
	
	
	kvs = Starter.deckKvs
	deck = Starter.deck
	
	'deck.Put("Math", subdeck)
	
	'display all decks in list view
	refreshbtn_Click

	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub SaveDecks
	kvs.Put("deck_data", deck)
End Sub

Private Sub Addbtn_Click
	'Just makes the add panel visible
	If addpanel.Visible = True Then
		addpanel.Visible = False
	End If
	addpanel.Visible = True
End Sub

Private Sub cancel_Click
	'Just makes the add panel dissapear (makes cancel effects)
	addpanel.Visible = False
	'removing input text from the edit text meant for the deckname
	et1.Text = ""
End Sub

'creation of deck
Private Sub create_Click
	
	'adds a map which is empty for the subdeck will be inputted inside
	Dim newsubdeck As Map
	'initialization of the created map
	newsubdeck.initialize
	
	'for checking if the map already exist
	For Each names As String In deck.Keys
		If et1.Text = names Then
			MsgboxAsync("Deck Already Exist", "Error")
			Return
		End If
	Next
	
	'conditon statement to prevent empty deckname
	If et1.Text = "" Then
		MsgboxAsync("Deck must have a name", "Error")
	Else
		'creation of a new deck itself
		addpanel.Visible = False
		LVdecks.AddSingleLine(et1.Text)
		'new map for new deck (name of deck(key), a map for subdecks)
		
		deck.Put(et1.Text, newsubdeck)
		SaveDecks
		'remove
		et1.Text = ""
	End If

End Sub

Private Sub refreshbtn_Click
	'refresh button - to refresh decks
	LVdecks.clear
	For Each deckName As String In deck.keys
		LVdecks.AddSingleLine(deckName)
	Next
End Sub

Private Sub LVdecks_ItemClick (Position As Int, Value As Object)
	'clickable items - send information of deck clicked to the next page and start the next activity(subdeck)
	selecteddeck = Value
	StartActivity(Subdeck_Module)
End Sub

Private Sub LVdecks_ItemLongClick (Position As Int, Value As Object)
	'for add, delete, browse, rename, create subdeck panel
	decksettingpanel.Visible = True
	item_longclick = Value
	selecteddeck = Value
End Sub



Private Sub addcards_Click
	Dim tappeddeck As Map = deck.Get(item_longclick)
	If tappeddeck.Size = 0 Then
		MsgboxAsync("Create A Sub-Deck first", "Error")
		Return
	End If
	decksettingpanel.Visible = False
	StartActivity(Add_card_module)
End Sub

Private Sub rename_deck_Click
	decksettingpanel.Visible = False
	renamepanel.Visible = True
End Sub

Private Sub delete_deck_Click
	decksettingpanel.Visible = False
	deleteconfirmation.Visible = True
End Sub

Private Sub create_subdeck_Click
	newsubdeckpanel.Visible = True
	decksettingpanel.Visible = False
End Sub

Private Sub browse_cards_Click
	decksettingpanel.Visible = False
	StartActivity(deck_all_cards)
End Sub

Private Sub cancelbtn_Click
	decksettingpanel.Visible = False
End Sub

Private Sub confirmnew_Click
	Dim getsubdeck As Map
	If newdeckname.Text = "" Then
		MsgboxAsync("New Name must have a name", "Error")
		Return
	End If
	For Each names As String In deck.keys
		getsubdeck = deck.Get(item_longclick)
		If newdeckname.Text = names Then
			MsgboxAsync("Sub-Deck Name Already Exist", "Error")
			Return
		End If
	Next
	deck.Remove(item_longclick)
	deck.Put(newdeckname.Text, getsubdeck)
	newdeckname.text = ""
	SaveDecks
	refreshbtn_Click
	renamepanel.Visible = False
End Sub

Private Sub cancelnew_Click
	newdeckname.text = False
	renamepanel.Visible = False
End Sub

Private Sub createnewsubdeck_Click
	If newsubdecket.Text = "" Then
		MsgboxAsync("New Sub Deck must have a name", "Error")
		Return
	End If
	Dim createdeck As Map = deck.Get(item_longclick)
	Dim flashcard As List
	flashcard.Initialize
	For Each subdeck As String In createdeck.Keys
		If newsubdecket.Text = subdeck Then
			MsgboxAsync("Subdeck Already Exist", "Error")
			Return
		End If
	Next
	createdeck.Put(newsubdecket.Text, flashcard)
	SaveDecks
	newsubdecket.Text = ""
	newsubdeckpanel.Visible = False
	StartActivity(Subdeck_Module)
End Sub

Private Sub cancelnewsubdeck_Click
	newsubdecket.Text = ""
	newsubdeckpanel.Visible = False
End Sub

Private Sub confirmdelete_Click
	deck.Remove(item_longclick)
	decksettingpanel.Visible = False
	deleteconfirmation.Visible = False
	refreshbtn_Click
End Sub

Private Sub canceldelete_Click
	deleteconfirmation.Visible = False
End Sub