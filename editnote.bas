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
	Public ActiveNote As MyNote
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private contentTxt As EditText
	Private saveBtn As Button
	Private tagsTxt As EditText
	Private titleTxt As EditText
	Dim api1 As String = "AIzaSyAGccTYG-Mscl_16Z72t"
	Dim api2 As String = "-GIN9ITMdrDGhQ"
	Dim MyAPIKey As String = api1&api2
	Dim cc As ContentChooser
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("editnoteLayout")
			Else
				Activity.LoadLayout("editnoteLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("editnoteLayout2")
			Else
				Activity.LoadLayout("editnoteLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("editnoteLayout3")
			Else
				Activity.LoadLayout("editnoteLayoutDark3")
			End If
	End Select
	
	cc.Initialize("CC")
	
	contentTxt.Background = Null
	contentTxt.Gravity = Bit.Or(Gravity.TOP, Gravity.LEFT)
	If ActiveNote.IsInitialized Then
		titleTxt.Text = ActiveNote.Title
		tagsTxt.Text = ActiveNote.Tags
		contentTxt.Text = ActiveNote.Content
	End If
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	If UserClosed Then ActiveNote.Initialize
End Sub

Sub saveBtn_Click
	If titleTxt.Text.Trim = "" Then
		MsgboxAsync("Please add a title", "")
		Return
	End If

	Dim n As MyNote
	n.Initialize
  
	If ActiveNote.IsInitialized And ActiveNote.noteID <> 0 Then
		n.noteID = ActiveNote.noteID
	Else
		n.noteID = (DateTime.Now * 1000) + Rnd(1, 1000)
	End If
	' -------------------------

	n.Title = titleTxt.Text
	n.Tags = tagsTxt.Text
	n.Content = contentTxt.Text
	n.DateAdded = DateTime.Now

	MainActivity.kvs.Put("N_" & n.noteID, n)
    
	ToastMessageShow("Note Saved", False)
	Activity.Finish
End Sub



Private Sub AI_notes_Click
	cc.Show("application/pdf", "Select PDF")
	
End Sub

Sub CC_RESULT (Success As Boolean, dir As String, fileName As String)
	If Success Then
		Log("Selected: " & dir & " / " & fileName)
		File.Copy(dir, fileName, File.DirInternal, "temp.pdf")
		Log("PDF saved")
		GenerateNotesFromPDF
	Else
		Log("User Cancelled")
	End If
End Sub

Sub GenerateNotesFromPDF

	'--- READ PDF ---
	Dim In As InputStream = File.OpenInput(File.DirInternal, "temp.pdf")
	Dim bytes() As Byte = Bit.InputStreamToBytes(In)
	In.Close

	Dim su As StringUtils
	Dim base64 As String = su.EncodeBase64(bytes)

	'--- PROMPT ---
	Dim prompt As String = _
    "Convert this PDF into clean study notes." & CRLF & _
    "RULES:" & CRLF & _
    "- Extract only important points" & CRLF & _
    "- Use bullet points and headings" & CRLF & _
    "- Keep it simple and study-ready" & CRLF & _
    "- Output ONLY plain text notes"

	'--- BUILD REQUEST ---
	Dim root As Map
	root.Initialize

	Dim contents As List
	contents.Initialize

	Dim item As Map
	item.Initialize

	Dim parts As List
	parts.Initialize

	' PDF PART
	Dim filePart As Map
	filePart.Initialize

	Dim inline As Map
	inline.Initialize
	inline.Put("mime_type", "application/pdf")
	inline.Put("data", base64)

	filePart.Put("inline_data", inline)
	parts.Add(filePart)

	' TEXT PART
	Dim textPart As Map
	textPart.Initialize
	textPart.Put("text", prompt)
	parts.Add(textPart)

	item.Put("parts", parts)
	contents.Add(item)

	root.Put("contents", contents)

	Dim gen As JSONGenerator
	gen.Initialize(root)

	Dim json As String = gen.ToString

	Dim url As String = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" & MyAPIKey

	Dim job As HttpJob
	job.Initialize("GeminiNotes", Me)

	job.PostString(url, json)
	job.GetRequest.SetContentType("application/json")

End Sub
Sub JobDone (job As HttpJob)
	ProgressDialogHide
	If job.Success Then

		If job.JobName = "GeminiNotes" Then

			Dim response As String = job.GetString

			Dim jp As JSONParser
			jp.Initialize(response)

			Dim root As Map = jp.NextObject
			Dim candidates As List = root.Get("candidates")
			Dim candidate As Map = candidates.Get(0)
			Dim content As Map = candidate.Get("content")
			Dim parts As List = content.Get("parts")
			Dim firstPart As Map = parts.Get(0)

			Dim notes As String = firstPart.Get("text")

			Log("NOTES OUTPUT:")
			Log(notes)

			'APPEND INSTEAD OF REPLACE
			If contentTxt.Text.Trim = "" Then
				contentTxt.Text = notes
			Else
				contentTxt.Text = contentTxt.Text & CRLF & CRLF & notes
			End If

			'OPTIONAL SAVE FILE
			File.WriteString(File.DirInternal, "notes.txt", contentTxt.Text)

		End If

	Else
		Log("ERROR: " & job.ErrorMessage)
	End If

	job.Release

End Sub