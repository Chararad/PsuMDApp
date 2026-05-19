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
	Private SQL1 As SQL
	Private DB_NAME As String = "leaderboard.db"
End Sub

Sub Globals
	Private xui As XUI

	'Views from Leaderboard.bal
	Private pnlHeader As Panel
	Private lblTitle As Label
	Private lblSubtitle As Label

	Private pnlTopCard As Panel
	Private lblTopUser As Label
	Private lblTopPoints As Label
	Private lblTopStreak As Label

	Private clvBoard As CustomListView

	Private btnDaily As Button
	Private btnWeekly As Button
	Private btnAllTime As Button

	Private lblFooter As Label
	Private pnlInsert As Panel
	Private edtName As EditText
	Private edtXP As EditText
	Private edtStreak As EditText
	Private btnInsert As Button

	'Data
	Type UserScore(Name As String, XP As Int, Streak As Int, CorrectRate As Int)
	Private DailyList As List
	Private WeeklyList As List
	Private AllTimeList As List
	Private CurrentMode As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("leaderboard")
			Else
				Activity.LoadLayout("leaderboarddark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("leaderboard2")
			Else
				Activity.LoadLayout("leaderboarddark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("leaderboard3")
			Else
				Activity.LoadLayout("leaderboarddark3")
			End If
	End Select
	
	DailyList.Initialize
	WeeklyList.Initialize
	AllTimeList.Initialize
	InitDB
	EnsureSeedData
	SetupInsertControls
	ShowBoard("DAILY")
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

Private Sub SetupInsertControls
	pnlInsert.Initialize("")
	pnlInsert.Color = xui.Color_ARGB(255, 255, 247, 236)
	Activity.AddView(pnlInsert, 8dip, Activity.Height - 162dip, Activity.Width - 16dip, 154dip)

	edtName.Initialize("")
	edtName.Hint = "Name"
	edtName.TextSize = 16
	pnlInsert.AddView(edtName, 8dip, 6dip, pnlInsert.Width - 16dip, 42dip)

	edtXP.Initialize("")
	edtXP.Hint = "XP"
	edtXP.TextSize = 16
	edtXP.InputType = edtXP.INPUT_TYPE_NUMBERS
	pnlInsert.AddView(edtXP, 8dip, 54dip, (pnlInsert.Width - 24dip) / 2, 42dip)

	edtStreak.Initialize("")
	edtStreak.Hint = "Streak"
	edtStreak.TextSize = 16
	edtStreak.InputType = edtStreak.INPUT_TYPE_NUMBERS
	pnlInsert.AddView(edtStreak, edtXP.Left + edtXP.Width + 8dip, 54dip, (pnlInsert.Width - 24dip) / 2, 42dip)

	btnInsert.Initialize("btnInsert")
	btnInsert.Text = "Insert to Leaderboard"
	btnInsert.TextSize = 16
	btnInsert.Color = xui.Color_ARGB(255, 141, 113, 176)
	btnInsert.TextColor = xui.Color_White
	pnlInsert.AddView(btnInsert, 8dip, 102dip, pnlInsert.Width - 16dip, 44dip)
End Sub

Private Sub SetupMockData
	'Daily
	InsertUserToDB("DAILY", "Mika", 320, 7, 93)
	InsertUserToDB("DAILY", "Rei", 295, 5, 90)
	InsertUserToDB("DAILY", "Noah", 280, 4, 88)
	InsertUserToDB("DAILY", "Ava", 250, 3, 86)
	InsertUserToDB("DAILY", "Luna", 220, 2, 82)

	'Weekly
	InsertUserToDB("WEEKLY", "Mika", 1760, 19, 92)
	InsertUserToDB("WEEKLY", "Ava", 1690, 13, 91)
	InsertUserToDB("WEEKLY", "Rei", 1610, 11, 89)
	InsertUserToDB("WEEKLY", "Noah", 1495, 9, 87)
	InsertUserToDB("WEEKLY", "Luna", 1420, 8, 85)

	'All Time
	InsertUserToDB("ALLTIME", "Ava", 12850, 41, 90)
	InsertUserToDB("ALLTIME", "Mika", 12110, 37, 91)
	InsertUserToDB("ALLTIME", "Noah", 11680, 29, 88)
	InsertUserToDB("ALLTIME", "Rei", 11300, 24, 87)
	InsertUserToDB("ALLTIME", "Luna", 10950, 22, 86)
End Sub

Private Sub CreateUser(n As String, xp As Int, st As Int, cr As Int) As UserScore
	Dim u As UserScore
	u.Initialize
	u.Name = n
	u.XP = xp
	u.Streak = st
	u.CorrectRate = cr
	Return u
End Sub

'Call this to add a user to any board. It auto-sorts by score.
Private Sub AddUserToMode(mode As String, n As String, xp As Int, st As Int, cr As Int)
	Dim target As List = GetModeList(mode)
	If target.IsInitialized = False Then Return
	target.Add(CreateUser(n, xp, st, cr))
	SortBoard(target)
End Sub

'Public helper for user-defined inputs (for example from EditText fields).
Public Sub AddUserScore(mode As String, n As String, xp As Int, st As Int, cr As Int)
	InsertUserToDB(mode, n, xp, st, cr)
	If mode = CurrentMode Then ShowBoard(mode)
End Sub

Private Sub InitDB
	SQL1.Initialize(File.DirInternal, DB_NAME, True)
	SQL1.ExecNonQuery("CREATE TABLE IF NOT EXISTS leaderboard (" & _
		"id INTEGER PRIMARY KEY AUTOINCREMENT, " & _
		"mode TEXT, " & _
		"name TEXT, " & _
		"xp INTEGER, " & _
		"streak INTEGER, " & _
		"correct_rate INTEGER)")
End Sub

Private Sub EnsureSeedData
	Dim c As Int = SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM leaderboard")
	If c = 0 Then SetupMockData
End Sub

Private Sub InsertUserToDB(mode As String, n As String, xp As Int, st As Int, cr As Int)
	SQL1.ExecNonQuery2("INSERT INTO leaderboard(mode, name, xp, streak, correct_rate) VALUES (?, ?, ?, ?, ?)", _
		Array As Object(mode.ToUpperCase, n, xp, st, cr))
End Sub

Private Sub LoadBoardFromDB(mode As String)
	Dim target As List = GetModeList(mode)
	target.Clear
	Dim rs As ResultSet = SQL1.ExecQuery2( _
		"SELECT name, xp, streak, correct_rate FROM leaderboard WHERE mode = ? " & _
		"ORDER BY xp DESC, streak DESC, correct_rate DESC", _
		Array As String(mode.ToUpperCase))
	Do While rs.NextRow
		target.Add(CreateUser(rs.GetString("name"), rs.GetInt("xp"), rs.GetInt("streak"), rs.GetInt("correct_rate")))
	Loop
	rs.Close
End Sub

Private Sub GetModeList(mode As String) As List
	Select mode.ToUpperCase
		Case "DAILY"
			Return DailyList
		Case "WEEKLY"
			Return WeeklyList
		Case Else
			Return AllTimeList
	End Select
End Sub

Private Sub SortBoard(board As List)
	For i = 0 To board.Size - 2
		For j = i + 1 To board.Size - 1
			Dim a As UserScore = board.Get(i)
			Dim b As UserScore = board.Get(j)
			If ShouldSwapForRanking(a, b) Then
				board.Set(i, b)
				board.Set(j, a)
			End If
		Next
	Next
End Sub

Private Sub ShouldSwapForRanking(a As UserScore, b As UserScore) As Boolean
	If b.XP > a.XP Then Return True
	If b.XP = a.XP And b.Streak > a.Streak Then Return True
	If b.XP = a.XP And b.Streak = a.Streak And b.CorrectRate > a.CorrectRate Then Return True
	Return False
End Sub

Private Sub ShowBoard(mode As String)
	mode = mode.ToUpperCase
	CurrentMode = mode
	
	clvBoard.AsView.SetLayoutAnimated(0, clvBoard.AsView.Left, clvBoard.AsView.Top, _
    clvBoard.AsView.Width, Activity.Height - clvBoard.AsView.Top - 200dip)
	
	Dim source As List
	Select mode
		Case "DAILY"
			source = DailyList
			lblSubtitle.Text = "Today's top flashcard learners"
		Case "WEEKLY"
			source = WeeklyList
			lblSubtitle.Text = "Weekly focus champions"
		Case Else
			source = AllTimeList
			lblSubtitle.Text = "All-time cozy legends"
	End Select

	LoadBoardFromDB(mode)
	If source.Size = 0 Then Return

	'Top card
	Dim top As UserScore = source.Get(0)
	lblTopUser.Text = "🏆 " & top.Name
	lblTopPoints.Text = top.XP & " XP"
	lblTopStreak.Text = "🔥 " & top.Streak & " day streak · " & top.CorrectRate & "% correct"

	'List
	clvBoard.Clear
	For i = 0 To source.Size - 1
		Dim u As UserScore = source.Get(i)
		Dim p As Panel
		p.Initialize("")
		p.Color = xui.Color_ARGB(0, 255, 250, 242)
		p.SetLayoutAnimated(0, 0, 0, clvBoard.AsView.Width, 62dip)

		Dim lblRank As Label
		lblRank.Initialize("")
		lblRank.Text = "#" & (i + 1)
		lblRank.TextSize = 15
		lblRank.Gravity = Gravity.CENTER_VERTICAL
		p.AddView(lblRank, 10dip, 0, 40dip, 62dip)

		Dim lblName As Label
		lblName.Initialize("")
		lblName.Text = u.Name
		lblName.TextSize = 16
		lblName.Gravity = Gravity.CENTER_VERTICAL
		p.AddView(lblName, 55dip, 0, 110dip, 62dip)

		Dim lblStats As Label
		lblStats.Initialize("")
		lblStats.Text = u.XP & " XP   •   🔥" & u.Streak & "   •   " & u.CorrectRate & "%"
		lblStats.TextSize = 13
		lblStats.Gravity = Gravity.CENTER_VERTICAL
		p.AddView(lblStats, 165dip, 0, clvBoard.AsView.Width - 175dip, 62dip)
		
		Select Starter.themeNumber
			Case 0
				If Starter.darkMode Then
					lblRank.TextColor = xui.Color_ARGB(255, 84, 72, 101)
					lblName.TextColor = xui.Color_ARGB(255, 62, 50, 80)
					lblStats.TextColor = xui.Color_ARGB(255, 122, 98, 82)
				Else
					lblRank.TextColor = xui.Color_ARGB(255, 84, 72, 101)
					lblName.TextColor = xui.Color_ARGB(255, 62, 50, 80)
					lblStats.TextColor = xui.Color_ARGB(255, 122, 98, 82)
				End If
			Case 1
				If Starter.darkMode Then
					lblRank.TextColor = xui.Color_ARGB(255, 84, 72, 101)
					lblName.TextColor = xui.Color_ARGB(255, 62, 50, 80)
					lblStats.TextColor = xui.Color_ARGB(255, 122, 98, 82)
				Else
					lblRank.TextColor = xui.Color_ARGB(255, 84, 72, 101)
					lblName.TextColor = xui.Color_ARGB(255, 62, 50, 80)
					lblStats.TextColor = xui.Color_ARGB(255, 122, 98, 82)
				End If
			Case 2
				If Starter.darkMode Then
					lblRank.TextColor = xui.Color_ARGB(255, 255, 255, 255)
					lblName.TextColor = xui.Color_ARGB(255, 255, 255, 255)
					lblStats.TextColor = xui.Color_ARGB(255, 122, 98, 82)
				Else
					lblRank.TextColor = xui.Color_ARGB(255, 84, 72, 101)
					lblName.TextColor = xui.Color_ARGB(255, 62, 50, 80)
					lblStats.TextColor = xui.Color_ARGB(255, 122, 98, 82)
				End If
		End Select

		clvBoard.Add(p, u.Name)
	Next

	lblFooter.Text = "Keep reviewing your flashcards to climb the cozy board ✨"
End Sub

Private Sub btnDaily_Click
	ShowBoard("DAILY")
End Sub

Private Sub btnWeekly_Click
	ShowBoard("WEEKLY")
End Sub

Private Sub btnAllTime_Click
	ShowBoard("ALLTIME")
End Sub

Private Sub btnInsert_Click
	Dim n As String = edtName.Text.Trim
	Dim xpText As String = edtXP.Text.Trim
	Dim streakText As String = edtStreak.Text.Trim

	If n.Length = 0 Or xpText.Length = 0 Or streakText.Length = 0 Then
		ToastMessageShow("Please fill Name, XP, and Streak.", False)
		Return
	End If

	If IsNumber(xpText) = False Or IsNumber(streakText) = False Then
		ToastMessageShow("XP and Streak must be numbers.", False)
		Return
	End If

	Dim xp As Int = xpText
	Dim st As Int = streakText
	If xp < 0 Or st < 0 Then
		ToastMessageShow("XP and Streak cannot be negative.", False)
		Return
	End If

	'CorrectRate isn't part of user input; use 0 as default.
	AddUserScore(CurrentMode, n, xp, st, 0)

	edtName.Text = ""
	edtXP.Text = ""
	edtStreak.Text = ""
	ToastMessageShow("Inserted and ranked successfully.", False)
End Sub