Local $hWnd = WinWait("Open", "", 7)
WinActivate($hWnd)
Send(@ScriptDir&"\BulkUploads\"&"BulkEmployee_firefox.xlsx")
Send("{ENTER}")