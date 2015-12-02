   Local $hWnd = WinWait("Open", "", 7)
   WinActivate($hWnd)
   Send(@ScriptDir&"\BulkUploads\"&"BulkLoadMoney_firefox.xlsx")
   Send("{ENTER}")