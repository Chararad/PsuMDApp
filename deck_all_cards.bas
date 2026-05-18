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
	Dim cards As List
	Private ScrollView1 As ScrollView
	Private deckname As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("DACLayout")
			Else
				Activity.LoadLayout("DACLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("DACLayout2")
			Else
				Activity.LoadLayout("DACLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("DACLayout3")
			Else
				Activity.LoadLayout("DACLayoutDark3")
			End If
	End Select
	
	deckname.Text = FlashcardActivity.item_longclick
	cards.Initialize
	
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
	
	ShowALLCards(cards)

End Sub

Sub ShowALLCards(CardsList As List)
		'custom scrollview
		ScrollView1.Panel.RemoveAllViews
		'initializing card sizes for the scroll view
		Dim topPos As Int = 0
		Dim cardHeight As Int = 150dip 'height
	
		For i = 0 To CardsList.Size -1
			Dim card As Map = CardsList.Get(i)
			Dim p As Panel
			p.Initialize("")
			p.Color = Colors.Transparent
			ScrollView1.Panel.AddView(p, 10dip, topPos, ScrollView1.Width - 20dip, cardHeight)
		
			Dim lbl2 As Label
			lbl2.Initialize("")
			lbl2.Text = card.Get("subdeck")
			lbl2.TextColor = Colors.Black
			lbl2.TextSize = 14
			lbl2.SingleLine = False
			lbl2.Typeface = Typeface.DEFAULT_BOLD
			p.AddView(lbl2, 10dip, 10dip, ScrollView1.Width - 20dip, 30dip)
		
			Dim lbl As Label
			lbl.Initialize("")
			lbl.Text = "Q: " & card.Get("Q") & CRLF & "A: " & card.Get("A")
			lbl.TextColor = Colors.black
			lbl.TextSize = 12
			lbl.SingleLine = False
		
			p.AddView(lbl, 10dip, 30dip, ScrollView1.Width - 20dip, 30dip)
			topPos = topPos + lbl.height + 10dip
		
			topPos = topPos + cardHeight + 10dip
		Next
		ScrollView1.Panel.Height = topPos + 10dip
	
End Sub

Sub Activity_Resume
	ShowALLCards(cards)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub addbtn_Click
	StartActivity(Add_card_module)
End Sub

Private Sub backbtn_Click
	Activity.finish
End Sub