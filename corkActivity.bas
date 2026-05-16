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
	Private imgPicker As ContentChooser
	Private xui As XUI
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private boardPnl As Panel
	Private addPnl As Panel
	Private canvaBtn As Button
	Private imgBtn As Button
	Private imgView As ImageView
	Private stickyBtn As Button
	Private notePnl As B4XView
	Private canvasPnl As B4XView
	Private LastX, LastY As Float
	Private place1 As Label
	Private place2 As Label
	Private place3 As Label
	Private place4 As Label
	Private place5 As Label
	Private place6 As Label
	Private place7 As Label
	Private place8 As Label
	Private place9 As Label
	Private place10 As Label
	Private place11 As Label
	Private place12 As Label
	Private deleteLbl As Label
	Private R = 255, G = 105, B = 97 As Int
	Private R2, G2, B2 As Int
	Private penSpnr As Spinner
	Private sizeSpnr As Spinner
	Private Width As Int
	Private Height As Int

	Dim ddn As DragDropView
	Dim ddi As DragDropView
	Dim ddc As DragDropView
	
	Private noteCount As Int = 0
	Private imgCount As Int = 0
	Private canvasCount As Int = 0
	Private isLoading As Boolean = False
	
	Dim pixeltf As Typeface
	pixeltf = Typeface.LoadFromAssets("minecraft.ttf")
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode = False Then
				Activity.LoadLayout("corkboardLayout")
			Else
				Activity.LoadLayout("corkboardLayoutDark")
			End If
		Case 1
			If Starter.darkMode = False Then
				Activity.LoadLayout("corkboardLayout2")
			Else
				Activity.LoadLayout("corkboardLayoutDark2")
			End If
		Case 2
			If Starter.darkMode = False Then
				Activity.LoadLayout("corkboardLayout3")
			Else
				Activity.LoadLayout("corkboardLayoutDark3")
			End If
	End Select
	
	If Starter.darkMode = False Then
		penSpnr.DropdownBackgroundColor = Colors.DarkGray
		penSpnr.DropdownTextColor = Colors.White
	Else
		penSpnr.DropdownTextColor = Colors.Black
	End If
	
	penSpnr.AddAll(Array As String("Black", "Blue", "Green", "Red", "Yellow", "Eraser"))
	If FirstTime Then
		imgPicker.Initialize("CC")
	End If
	penSpnr.Visible = False
	Width = 80dip
	Height = 60dip
	ddn.Initialize(Me, "NoteDrag")
	ddi.Initialize(Me, "ImgDrag")
	ddc.Initialize(Me, "CanvasDrag")
	
	If Main.kvs.IsInitialized = False Then
		Main.kvs.Initialize(File.DirInternal, "notes_data")
	End If
	
	isLoading = True
	
	If Main.kvs.ContainsKey("note_count") Then noteCount = Main.kvs.Get("note_count") Else noteCount = 0
	Dim loadedNoteCount As Int = noteCount
	For i = 0 To loadedNoteCount - 1
		Dim nkey As String = "note_" & i
		If Main.kvs.ContainsKey(nkey & "_text") Then
			Dim savedColor As Int = Main.kvs.Get(nkey & "_color")
			R = Bit.And(Bit.ShiftRight(savedColor, 16), 0xFF)
			G = Bit.And(Bit.ShiftRight(savedColor, 8), 0xFF)
			B = Bit.And(savedColor, 0xFF)
			noteCount = i + 1
			AddStickyNote(Main.kvs.Get(nkey & "_text"), Main.kvs.Get(nkey & "_x"), Main.kvs.Get(nkey & "_y"))
		End If
	Next

	If Main.kvs.ContainsKey("img_count") Then imgCount = Main.kvs.Get("img_count") Else imgCount = 0
	Dim loadedImgCount As Int = imgCount
	For i = 0 To loadedImgCount - 1
		Dim ikey As String = "img_" & i
		If Main.kvs.ContainsKey(ikey & "_file") Then
			Dim iv As ImageView
			iv.Initialize("ImgView")
			boardPnl.AddView(iv, Main.kvs.Get(ikey & "_x"), Main.kvs.Get(ikey & "_y"), 100dip, 100dip)
			iv.Bitmap = LoadBitmapResize(File.DirInternal, Main.kvs.Get(ikey & "_file"), 100dip, 100dip, True)
			iv.Tag = ikey
			ddi.AddDragView(iv, False)
			ddi.AddPlaceView(place1).AddPlaceView(place2).AddPlaceView(place3).AddPlaceView(place4).AddPlaceView(place5).AddPlaceView(place6).AddPlaceView(place7).AddPlaceView(place8).AddPlaceView(place9).AddPlaceView(place10).AddPlaceView(place11).AddPlaceView(place12).AddPlaceView(deleteLbl)
		End If
	Next

	If Main.kvs.ContainsKey("cvs_count") Then canvasCount = Main.kvs.Get("cvs_count") Else canvasCount = 0
	Dim loadedCanvasCount As Int = canvasCount
	For i = 0 To loadedCanvasCount - 1
		Dim ckey As String = "cvs_" & i
		If Main.kvs.ContainsKey(ckey & "_x") Then
			Width = Main.kvs.Get(ckey & "_w")
			Height = Main.kvs.Get(ckey & "_h")
      
			Dim f As Panel
			f.Initialize("CanvasFrame")
			f.Color = Colors.Black
			boardPnl.AddView(f, Main.kvs.Get(ckey & "_x"), Main.kvs.Get(ckey & "_y"), Width + 20dip, Height + 40dip)
			Dim p As Panel
			p.Initialize("CanvasPanel")
			p.Color = Colors.White
			f.AddView(p, 10dip, 20dip, Width, Height)
			Sleep(0)
			Dim cvs As B4XCanvas
			cvs.Initialize(p)
			cvs.DrawRect(cvs.TargetRect, Colors.LightGray, False, 1dip)
			cvs.Invalidate
			p.Tag = cvs
			ddc.AddDragView(f, False)
			ddc.AddPlaceView(place1).AddPlaceView(place2).AddPlaceView(place3).AddPlaceView(place4).AddPlaceView(place5).AddPlaceView(place6).AddPlaceView(place7).AddPlaceView(place8).AddPlaceView(place9).AddPlaceView(place10).AddPlaceView(place11).AddPlaceView(place12).AddPlaceView(deleteLbl)
			f.Tag = ckey
        
			If File.Exists(File.DirInternal, ckey & ".png") Then
				Sleep(0)
				Dim bmp As Bitmap = LoadBitmap(File.DirInternal, ckey & ".png")
				Dim canvasRect As B4XRect = cvs.TargetRect
				cvs.DrawBitmap(bmp, canvasRect)
				cvs.Invalidate
			End If
		End If
	Next
	canvasCount = loadedCanvasCount
	Width = 80dip
	Height = 60dip
	isLoading = False
End Sub

Sub Activity_Resume
	If canvasCount > 0 Then
		penSpnr.Visible = True
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'----------------------MAIN SPAWNING/ADDINNG EVENTS---------------------------

Sub AddStickyNote(Text As String, x As Int, y As Int)
	Dim p As Panel
	p.Initialize("NotePanel")
	p.Color = Colors.RGB(R, G, B)

	Dim txt As EditText
	txt.Initialize("NoteText")
	txt.Tag = p
	txt.Text = Text
	txt.TextSize = 12
	txt.Background = Null
	txt.TextColor = Colors.Black
	txt.Gravity = Gravity.TOP
	
	p.AddView(txt, 5dip, 15dip, 90dip, 70dip)
	
	boardPnl.AddView(p, x, y, 100dip, 100dip)

	ddn.AddDragView(p, False)
	ddn.AddPlaceView(place1).AddPlaceView(place2).AddPlaceView(place3).AddPlaceView(place4).AddPlaceView(place5).AddPlaceView(place6).AddPlaceView(place7).AddPlaceView(place8).AddPlaceView(place9).AddPlaceView(place10).AddPlaceView(place11).AddPlaceView(place12).AddPlaceView(deleteLbl)
	
	If isLoading = False Then
		Dim key As String = "note_" & noteCount
		Main.kvs.Put(key & "_text", Text)
		Main.kvs.Put(key & "_color", Colors.RGB(R, G, B))
		Main.kvs.Put(key & "_x", x)
		Main.kvs.Put(key & "_y", y)
		Main.kvs.Put("note_count", noteCount + 1)
		p.Tag = key
		noteCount = noteCount + 1
	Else
		p.Tag = "note_" & (noteCount - 1)
	End If
	Log("deleteLbl initialized: " & (deleteLbl.IsInitialized))
End Sub

Sub NoteText_TextChanged(Old As String, New As String)
	Dim txt As EditText = Sender
	Dim p As Panel = txt.Tag
	Dim key As String = p.Tag
	Main.kvs.Put(key & "_text", New)
End Sub

Sub CC_Result (Success As Boolean, Dir As String, FileName As String)
	If Success Then
		imgView.Initialize("ImgView")
		Dim bmp As Bitmap
		bmp = LoadBitmapResize(Dir, FileName, 100dip, 100dip, True)
		imgView.Bitmap = bmp
		boardPnl.AddView(imgView, 150dip, 500dip, 100dip, 100dip)
		ddi.AddDragView(imgView, False)
		ddi.AddPlaceView(place1).AddPlaceView(place2).AddPlaceView(place3).AddPlaceView(place4).AddPlaceView(place5).AddPlaceView(place6).AddPlaceView(place7).AddPlaceView(place8).AddPlaceView(place9).AddPlaceView(place10).AddPlaceView(place11).AddPlaceView(place12).AddPlaceView(deleteLbl)
		Dim key As String = "img_" & imgCount
		Dim out As OutputStream
		out = File.OpenOutput(File.DirInternal, key & ".png", False)
		bmp.WriteToStream(out, 100, "PNG")
		out.Close
		Main.kvs.Put(key & "_file", key & ".png")
		Main.kvs.Put(key & "_x", 150dip)
		Main.kvs.Put(key & "_y", 500dip)
		Main.kvs.Put("img_count", imgCount + 1)
		imgView.Tag = key
		imgCount = imgCount + 1
	Else
		ToastMessageShow("No image selected", False)
	End If
End Sub

Sub AddCanvas(x As Int, y As Int)
	Dim f As Panel
	f.Initialize("CanvasFrame")
	f.Color = Colors.Black
	boardPnl.AddView(f, x, y, Width + 20dip, Height + 40dip)
	
	Dim p As Panel
	p.Initialize("CanvasPanel")
	p.Color = Colors.White
	f.AddView(p, 10dip, 20dip, Width, Height)
	
	Sleep(0)
	
	Dim cvs As B4XCanvas
	cvs.Initialize(p)
	cvs.DrawRect(cvs.TargetRect, Colors.LightGray, False, 1dip)
	cvs.Invalidate
	p.Tag = cvs

	ddc.AddDragView(f, False)
	ddc.AddPlaceView(place1).AddPlaceView(place2).AddPlaceView(place3).AddPlaceView(place4).AddPlaceView(place5).AddPlaceView(place6).AddPlaceView(place7).AddPlaceView(place8).AddPlaceView(place9).AddPlaceView(place10).AddPlaceView(place11).AddPlaceView(place12).AddPlaceView(deleteLbl)
	If isLoading = False Then
		Dim key As String = "cvs_" & canvasCount
		Main.kvs.Put(key & "_x", x)
		Main.kvs.Put(key & "_y", y)
		Main.kvs.Put(key & "_w", Width)
		Main.kvs.Put(key & "_h", Height)
		Main.kvs.Put("cvs_count", canvasCount + 1)
		f.Tag = key
		canvasCount = canvasCount + 1
	Else
		f.Tag = "cvs_" & (canvasCount - 1)
	End If
End Sub

Sub CanvasPanel_Touch (Action As Int, X As Float, Y As Float)
	Dim p As Panel = Sender
	Dim cvs As B4XCanvas = p.Tag
	Select Action
		Case Activity.ACTION_DOWN
			LastX = X
			LastY = Y
		Case Activity.ACTION_MOVE
			cvs.DrawLine(LastX, LastY, X, Y, Colors.RGB(R2,G2,B2), 3dip)
			cvs.Invalidate
			LastX = X
			LastY = Y
			Dim f As Panel = p.Parent
			Dim key As String = f.Tag
			Dim out As OutputStream
			out = File.OpenOutput(File.DirInternal, key & ".png", False)
			cvs.CreateBitmap.WriteToStream(out, 100, "PNG")
			out.Close
	End Select
End Sub

'---------------PANELS/WINDOWS SPAWNED AND BUTTONS INSIDE IT------------------

Private Sub noteWindow(pW As Int, pH As Int)
	notePnl = xui.CreatePanel("notePnl")
	Activity.AddView(notePnl, 100dip, 225dip, pW, pH)
	

	Dim colorsSpnr As Spinner
	colorsSpnr.Initialize("colorsSpnr")
	colorsSpnr.AddAll(Array As String("Red", "Blue", "Yellow"))
	notePnl.AddView(colorsSpnr, 10dip, 10dip, pW - 20dip, 40dip)
	
	Dim addnBtn As Button
	addnBtn.Initialize("addnBtn")
	addnBtn.Text = "Add Note"
	notePnl.AddView(addnBtn, 10dip, 60dip, (pW / 2) - 15dip, 50dip)
	
	notePnl.Enabled = False
	notePnl.Visible = False
	
	Dim bd As BitmapDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				notePnl.Color = xui.Color_RGB(50, 50, 50)
				notePnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addnBtn.Background = bd
				addnBtn.TextColor = Colors.White
			Else
				notePnl.Color = xui.Color_RGB(50, 50, 50)
				notePnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addnBtn.Background = bd
				addnBtn.TextColor = Colors.White
			End If
		Case 1
			If Starter.darkMode Then
				notePnl.Color = xui.Color_RGB(50, 50, 50)
				notePnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addnBtn.Background = bd
				addnBtn.TextColor = Colors.White
			Else
				notePnl.Color = xui.Color_RGB(50, 50, 50)
				notePnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addnBtn.Background = bd
				addnBtn.TextColor = Colors.White
			End If
		Case 2
			addnBtn.Typeface = pixeltf
			If Starter.darkMode Then
				notePnl.Color = xui.Color_RGB(50, 50, 50)
				notePnl.SetColorAndBorder(xui.Color_RGB(37, 57, 94), 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addnBtn.Background = bd
				addnBtn.TextColor = Colors.White
			Else
				notePnl.Color = xui.Color_RGB(50, 50, 50)
				notePnl.SetColorAndBorder(xui.Color_RGB(234, 212, 179), 2dip, xui.Color_RGB(79, 46, 46), 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addnBtn.Background = bd
				addnBtn.TextColor = Colors.White
			End If
	End Select
End Sub

Private Sub canvasWindow(pW As Int, pH As Int)
	canvasPnl = xui.CreatePanel("canvasPanel")
	Activity.AddView(canvasPnl, 100dip, 225dip, pW, pH)
	


	sizeSpnr.Initialize("sizeSpnr")
	sizeSpnr.AddAll(Array As String("1x1", "2x1", "1x2", "2x2", "3x2", "2x3"))
	canvasPnl.AddView(sizeSpnr, 10dip, 10dip, pW - 20dip, 40dip)
	
	Dim addcBtn As Button
	addcBtn.Initialize("addcBtn")
	addcBtn.Text = "Add Canvas"
	canvasPnl.AddView(addcBtn, 10dip, 60dip, (pW / 2) - 15dip, 50dip)
	
	canvasPnl.Enabled = False
	canvasPnl.Visible = False
	
	Dim bd As BitmapDrawable
	Select Starter.themeNumber
		Case 0
			If Starter.darkMode Then
				canvasPnl.Color = xui.Color_RGB(50, 50, 50)
				canvasPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addcBtn.Background = bd
				addcBtn.TextColor = Colors.White
			Else
				canvasPnl.Color = xui.Color_RGB(50, 50, 50)
				canvasPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addcBtn.Background = bd
				addcBtn.TextColor = Colors.White
			End If
		Case 1
			If Starter.darkMode Then
				canvasPnl.Color = xui.Color_RGB(50, 50, 50)
				canvasPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addcBtn.Background = bd
				addcBtn.TextColor = Colors.White
			Else
				canvasPnl.Color = xui.Color_RGB(50, 50, 50)
				canvasPnl.SetColorAndBorder(xui.Color_White, 2dip, xui.Color_Black, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addcBtn.Background = bd
				addcBtn.TextColor = Colors.White
			End If
		Case 2
			addcBtn.Typeface = pixeltf
			If Starter.darkMode Then
				canvasPnl.Color = xui.Color_RGB(50, 50, 50)
				canvasPnl.SetColorAndBorder(xui.Color_RGB(37, 57, 94), 2dip, xui.Color_White, 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addcBtn.Background = bd
				addcBtn.TextColor = Colors.White
			Else
				canvasPnl.Color = xui.Color_RGB(50, 50, 50)
				canvasPnl.SetColorAndBorder(xui.Color_RGB(234, 212, 179), 2dip, xui.Color_RGB(79, 46, 46), 3dip)
				bd.Initialize(LoadBitmap(File.DirAssets, "calendarpbtn.png"))
				addcBtn.Background = bd
				addcBtn.TextColor = Colors.White
			End If
	End Select
	
End Sub

Private Sub addnBtn_Click
	AddStickyNote("", 150dip, 500dip)
	notePnl.Visible = False
	R = 255
	G = 105
	B = 97
	stickyBtn.Enabled = True
	canvaBtn.Enabled = True
	imgBtn.Enabled = True
End Sub

Private Sub addcBtn_Click
	AddCanvas(150dip, 500dip)
	canvasPnl.Visible = False
	canvaBtn.Enabled = True
	stickyBtn.Enabled = True
	imgBtn.Enabled = True
End Sub

'--------------------------MAIN ADDING BUTTONS--------------------------------

Private Sub stickyBtn_Click
	noteWindow(250dip, 180dip)
	notePnl.Visible = True
	stickyBtn.Enabled = False
	canvaBtn.Enabled = False
	imgBtn.Enabled = False
End Sub

Private Sub imgBtn_Click
	imgPicker.Show("image/*", "Select a Photo")
End Sub

Private Sub canvaBtn_Click
	canvasWindow(250dip, 180dip)
	canvasPnl.Visible = True
	penSpnr.Visible = True
	stickyBtn.Enabled = False
	canvaBtn.Enabled = False
	imgBtn.Enabled = False
End Sub

'------------------------------SPINNERS---------------------------------------

Private Sub sizeSpnr_ItemClick (Position As Int, Value As Object)
	Select Position
		Case 0
			Width = 80dip
			Height = 60dip
		Case 1
			Width = 205dip
			Height = 60dip
		Case 2
			Width = 80dip
			Height = 185dip
		Case 3
			Width = 205dip
			Height = 185dip
		Case 4
			Width = 330dip
			Height = 185dip
		Case 5
			Width = 205dip
			Height = 310dip
	End Select
End Sub

Private Sub colorsSpnr_ItemClick (Position As Int, Value As Object)
	Select Position
		Case 0
			R = 255
			G = 105
			B = 97
		Case 1
			R = 155
			G = 190
			B = 237
		Case 2
			R = 248
			G = 241
			B = 174
	End Select
End Sub

Private Sub penSpnr_ItemClick (Position As Int, Value As Object)
	Select Position
		Case 0
			R2 = 0
			G2 = 0
			B2 = 0
		Case 1
			R2 = 0
			G2 = 0
			B2 = 255
		Case 2
			R2 = 0
			G2 = 255
			B2 = 0
		Case 3
			R2 = 255
			G2 = 0
			B2 = 0
		Case 4
			R2 = 255
			G2 = 255
			B2 = 0
		Case 5
			R2 = 255
			G2 = 255
			B2 = 255
	End Select
End Sub

'---------------------------DELETE FUNCTION-----------------------------------

Sub NoteDrag_PlacedView(nDragView As View, nPlaceView As View)
	If nPlaceView.Tag = "delete" Then
		Msgbox2Async("Are you sure you want to delete note?", "Delete Note", "No", "", "Yes", Null, False)
		Wait For Msgbox_Result (res As Int)
		If res = DialogResponse.NEGATIVE Then
			Dim p As Panel = nDragView
			Dim key As String = p.Tag
			Main.kvs.Remove(key & "_text")
			Main.kvs.Remove(key & "_color")
			Main.kvs.Remove(key & "_x")
			Main.kvs.Remove(key & "_y")
			Dim newCount As Int = Main.kvs.Get("note_count")
			Main.kvs.Put("note_count", newCount - 1)
			p.Visible = False
			ToastMessageShow("Note Deleted", False)
		End If
	Else
		Dim p As Panel = nDragView
		Dim key As String = p.Tag
		Main.kvs.Put(key & "_x", p.Left)
		Main.kvs.Put(key & "_y", p.Top)
	End If
End Sub

Sub ImgDrag_PlacedView(iDragView As View, iPlaceView As View)
	If iPlaceView.Tag = "delete" Then
		Msgbox2Async("Are you sure you want to delete image?", "Delete Image", "No", "", "Yes", Null, False)
		Wait For Msgbox_Result (res As Int)
		If res = DialogResponse.NEGATIVE Then
			Dim iv As ImageView = iDragView
			Dim key As String = iv.Tag
			File.Delete(File.DirInternal, key & ".png")
			Main.kvs.Remove(key & "_file")
			Main.kvs.Remove(key & "_x")
			Main.kvs.Remove(key & "_y")
			Dim newCount As Int = Main.kvs.Get("img_count")
			Main.kvs.Put("img_count", newCount - 1)
			iv.Visible = False
			ToastMessageShow("Image Deleted", False)
		End If
	Else
		Dim iv As ImageView = iDragView
		Dim key As String = iv.Tag
		Main.kvs.Put(key & "_x", iv.Left)
		Main.kvs.Put(key & "_y", iv.Top)
	End If
End Sub

Sub CanvasDrag_PlacedView(cDragView As View, cPlaceView As View)
	If cPlaceView.Tag = "delete" Then
		Msgbox2Async("Are you sure you want to delete canvas?", "Delete Canvas", "No", "", "Yes", Null, False)
		Wait For Msgbox_Result (res As Int)
		If res = DialogResponse.NEGATIVE Then
			Dim f As Panel = cDragView
			Dim key As String = f.Tag
			File.Delete(File.DirInternal, key & ".png")
			Main.kvs.Remove(key & "_x")
			Main.kvs.Remove(key & "_y")
			Main.kvs.Remove(key & "_w")
			Main.kvs.Remove(key & "_h")
			Dim newCount As Int = Main.kvs.Get("cvs_count")
			Main.kvs.Put("cvs_count", newCount - 1)
			canvasCount = canvasCount - 1
			f.Visible = False
			ToastMessageShow("Canvas Deleted", False)
			If canvasCount = 0 Then
				penSpnr.Visible = False
			End If
		End If
	Else
		Dim f As Panel = cDragView
		Dim key As String = f.Tag
		Main.kvs.Put(key & "_x", f.Left)
		Main.kvs.Put(key & "_y", f.Top)
	End If
End Sub