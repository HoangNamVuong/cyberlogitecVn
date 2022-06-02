/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CARRIERJOO.js
*@FileTitle : Carrier Joint Operation Management
*Open Issues : No
*Change history :Change comment
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
var sheetObjects = new Array(); 
var sheetCnt = 0; 
var comboObjects = new Array(); 
var comboCnt = 0; 

// Event handler when click button.
document.onclick = processButtonClick;

/**
 * This function handler button.
 * */ 
function processButtonClick() {
	var sheetObject1 = sheetObjects[0];
	var formObj = document.form;
	try {
		// Get event name which corresponding to button.
		var srcName = ComGetEvent("name");
		if (srcName == null) {
			return;
		}
		switch (srcName) {
		    // Event fires when retrieve button is clicked.
			case "btn_Retrieve":
				doActionIBSheet(sheetObject1, formObj, IBSEARCH);
				break;
				
			// Event fires when New button is clicked, reset form.
			case "btn_New":
				resetForm(formObj);
				break;
				
			// Event fires when Save button is clicked, save new data.
			case "btn_Save":
				doActionIBSheet(sheetObject1, formObj, IBSAVE);
				break;
				
			// Event fires when DownExcel button is clicked, down sheet to excel file.
			case "btn_DownExcel":
				if (sheetObject1.RowCount() < 1) {// check no data.
					ComShowCodeMessage("COM132501");
				} else {
					sheetObject1.Down2Excel({ HiddenColumn:1,Merge:1});
				}
				break;
				
			// Event fires when Calendar button is clicked, choose date.
			case "btns_calendar1":
			case "btns_calendar2":
                var cal=new ComCalendar();
                if(srcName == "btns_calendar1"){
                    cal.select(formObj.s_cre_dt_fm, 'yyyy-MM-dd');
                }else{
                    cal.select(formObj.s_cre_dt_to, 'yyyy-MM-dd');
                }
                break;
                
            // Event fires when Delete button is clicked, delete current row.
			case "btn_RowDelete":
				doActionIBSheet(sheetObject1, formObj, IBDELETE);
				break;
				
			// Event fires when Add button is clicked, add new row.
			case "btn_RowAdd":
				doActionIBSheet(sheetObject1, formObj, IBINSERT);
				break;
			default :
				break;
		} // End switch.
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage('COM132102');
		} else {
			ComShowMessage(e.message);
		}
	}
}

/**
 *  This function calls a common function that sets the default settings of the sheet,
 *  It is the first called area when file *jsp onload event.
 * */
function loadPage() {
	// Generate Grid Layout.
	for (i = 0; i < sheetObjects.length; i++) {
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i + 1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	// Generate dopdownlist data combo box.
	for ( var k = 0; k < comboObjects.length; k++) {
		initCombo(comboObjects[k], k + 1);
	}
	// Search data after loading finish.
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH); 
}

/**
 *  This function defines the transaction logic between the user interface and the server of IBSheet.
 *  
 *  @param sheetObj:  IBSSheet Object.
 *  @param formObj :  Form object.
 *  @param sAction :  Action Code (e.g. IBSEARCH, IBSAVE, IBDELETE, IBDOWNEXCEL).
 * */
function doActionIBSheet(sheetObj, formObj, sAction) {
	if (!validateForm(sheetObj, formObj, sAction)) {
		return false;
	}
	switch (sAction) {
	// Retrieve button event.
	case IBSEARCH: 
		// Get value of form command.
		formObj.f_cmd.value = SEARCH;
		ComOpenWait(true);
		// Connect to search page to read search XML then load XML data internally in IBSheet.
		sheetObj.DoSearch("CARRIERJOOGS.do", FormQueryString(formObj) );
		break;
		
	// Save data button event.
	case IBSAVE: 
		formObj.f_cmd.value = MULTI;
		sheetObj.DoSave("CARRIERJOOGS.do", FormQueryString(formObj));
		break;
		
	// Row Add button event.
	case IBINSERT: 
		// When click Row Add, empty row shall be inserted at the bottom.
		sheetObj.DataInsert(-1);
		break;
		
	// Row Delete button event	.
	case IBDELETE: 
		for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
			if(sheetObj.GetCellValue(i, "del_chk") == 1){
				sheetObj.SetRowHidden(i, 1);
				// Change status of ibflag to delete on Grid.
				sheetObj.SetRowStatus(i,"D");
			}
		}
		break;
	}
}

/**
 * This function that define the basic properties of the combo.
 * 
 * @param comboObj:    IBSheet Object.
 * @param comboNo :    Number of IBMultiCombo Object.
 * */
function initCombo(comboObj, comboNo) {
	var formObj = document.form
	switch (comboNo) {
	case 1:
		with (comboObj) {
		    // Could select multi box in combobox.
			SetMultiSelect(1);
			// Set the height for drop list.
	        SetDropHeight(200);
	        // Input upper case, numbers.
	        ValidChar(2,1);
		}
		// Split all components to array.
		var comboItems = crrCombo.split("|");
		// Then add content to combo.
		addComboItem(comboObj, comboItems);
		// Default selection at index 0.
		comboObj.SetSelectIndex(0);
		break;
	}
}

/**
 * This function initSheet define the basic properties of the sheet on the screen.
 * 
 * @param sheetObj: IBSheet Object.
 * @param sheetNo:  Number of IBSheet Object.
 * */
function initSheet(sheetObj, sheetNo) {
	switch (sheetNo) {
	// Initiated sheet1.
	case 1: 
		with (sheetObj) {
			/* *
			 * This function SetConfig Configure how to fetch initialized sheet, location of frozen rows or columns and other basic configurations.
			 * 
			 * @param SearchMode:   Is where you can configure search mode by selecting one from General, Paging,LazyLoad or real-time server processing modes, 
			 *                      the default value is: 0 load all data|1 load by page mode|2 is lazy load.
			 * @param Page:         Number of rows to display in one page (default:20).
			 * @param MergeSheet:   Is where you can configure merge styles, The default value is: 0 no merge|1 merge for all|5 merge only header.
			 * @param DataRowMerge: Whether to allow horizontal merge of the entire row, The default value is: 0
			 * */
			SetConfig({SearchMode : 2, MergeSheet : 5, Page : 20, DataRowMerge : 0});

			/*
			 * info set information for sheet.
			 * 
			 * @param Sort:        Whether to allow sorting by clicking on the header, value default is: 1 yes|0 no.
			 * @param ColMove:     Whether to allow column movement in header, value default is: 1 yes|0 no.
			 * @param HeaderCheck: Whether the CheckAll in the header is checked, value default: 1 yes|0 no.
			 * @param ColResize:   Whether to allow resizing of column width, value default: 1 yes| 0 no.
			 * */
			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
			
			/*
			 * setting for header
			 * 
			 * @param Text:  String of texts to display in header,adjoined by "|"
			 * @param Align: String How to align header text, value default: "Center"
			 * */
			var HeadTitle = "STS|Chk|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
			var headers = [ { Text : HeadTitle, Align : "Center" }];
			
			/**
			 * This function define header of Grid, can define the header title and function using this method.
			 * 
			 * @param headers: Make header list.
			 * @param info:    Set information for sheet.
			 * */
			InitHeaders(headers, info);

			/*
			 * configure for each column
			 * 
			 * @param Type:       String  - Column data type, this is Required.
			 * @param Hidden:     Boolean - Whether a column is hidden, value: 1 hide|0 show.
			 * @param Width:      Number  - Column width.
			 * @param Align:      String  - Data alignment.
			 * @param ColMerge:   Boolean - whether to allow vertical merge for data columns, value: 1 yes|0 no.
			 * @param SaveName:   String  - Can be used to configure the parameter names to use when saving data.
			 * @param KeyField:   Boolean - Whether to make a data cell a required field, value: 1 required| 0 not required.
			 * @param UpdateEdit: Boolean - Can be used to configure editable of data the transaction status of which is Search, value: 1 yes|0 no.
			 * @param InsertEdit: Boolean - can be used to configure editable of data the transaction status of which is Insert, value: 1 yes|0 no.
			 * @param EditLen:    Number  - Can be used to configure the maximum number of characters to allow for a piece of data.
			 * */
			var cols = [ 
	             { Type : "Status",   Hidden : 1, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
	             { Type : "CheckBox", Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
	             { Type : "Popup",    Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "jo_crr_cd",   KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1,   EditLen: 3 }, 
	             { Type : "Combo",    Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "rlane_cd",    KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1,   EditLen: 5 }, 
	             { Type : "Popup",    Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "vndr_seq",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1,   EditLen: 6 }, 
	             { Type : "Text",     Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "cust_cnt_cd", KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 0,   EditLen: 2 }, 
	             { Type : "Popup",    Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "cust_seq",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1,   EditLen: 6 }, 
	             { Type : "Popup",    Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "trd_cd",      KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1,   EditLen: 3 }, 
	             { Type : "Combo",    Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "delt_flg",    KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1}, 
	             { Type : "Text",     Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "cre_dt",      KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
	             { Type : "Text",     Hidden : 0, Width : 180, Align : "Left",   ColMerge : 0, SaveName : "cre_usr_id",  KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
	             { Type : "Text",     Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "upd_dt",      KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
	             { Type : "Text",     Hidden : 0, Width : 180, Align : "Left",   ColMerge : 0, SaveName : "upd_usr_id",  KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0},
	             ];

			InitColumns(cols);
			
			/**
			 * This function Configure overall editable before initial load.
			 * The value: 1 editable|0 no editable.
			 * */
			SetEditable(1);
			
			/**
			 * This function use when you want to define a property of a particular column dynamically, after the property is set in InitColumns Method.
			 * 
			 * @param SaveName:           String - Get column name.
			 * @param AcceptKeys:         String - Accepted key configuration, value E alphabet|N number.
			 * @param InputCaseSensitive: Number - Whether to use particular case automatically for alphabet inputs, value: 0 no configuration|1 replace with upper case|2 replace with lower case.
			 * @param ComboText:          String - Combo list text string group.
			 * @param ComboCode:          String - Combo list code group.
			 * */
			SetColProperty("jo_crr_cd",   { AcceptKeys : "E|N", InputCaseSensitive : 1 });
			SetColProperty("vndr_seq",    { AcceptKeys : "N"});
			SetColProperty("cust_cnt_cd", { AcceptKeys : "E",   InputCaseSensitive : 1});
			SetColProperty("cust_seq",    { AcceptKeys : "N"});
			SetColProperty("trd_cd",      { AcceptKeys : "E",   InputCaseSensitive : 1 });
			SetColProperty("rlane_cd",    { ComboText  : rlanCombo, ComboCode : rlanCombo });
			SetColProperty("delt_flg",    { ComboText  : "N|Y",     ComboCode : "N|Y" });
			
			/**
			 * Check or configure whether to display waiting image during processing.
			 * The value: 0 no waiting image.
			 * */
			SetWaitImageVisible(0);
			resizeSheet();
		}
		break;
	}
}

/**
 * This function resize sheet,
 * If don't call this functions, it will may make UI breakable.
 * */
function resizeSheet(){
    ComResizeSheet(sheetObjects[0]);
}

/**
 * This function will delete the values ​​in the input and the Grid that are displayed in the UI when click new button.
 * reset()         : Remove all configurations in IBSheet and reset to OOTB state.
 * RemoveAll()     : Delete all data rows and leave the header row only.
 * SetSelectIndex(): Set the index of the currently selected item.
 * 
 * @param formObj: document.form, The forms property returns a collection of all <form> elements in a document.
 * */
function resetForm(formObj){
	formObj.reset();
	sheetObjects[0].RemoveAll();
	s_jo_crr_cd.SetSelectIndex(0);
}

/**
 * Registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source.
 * 
 * @param sheet_obj: String - sheet name.
 * */
function setSheetObject(sheet_obj) {
	sheetObjects[sheetCnt++] = sheet_obj;
}

/**
 * Registering IBCombo Object as list parameter : combo_obj adding process for list
 * in case of needing batch processing with other items defining list on the top of source.
 * 
 * @param combo_obj: String - combo object name.
 * */
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}

/**
 * This function check options of check box.
 * Event fires when Carrier check box is clicked, if multiple selection is used.
 * GetItemCheck(): Select a specific item in the check box as an index .
 * GetItemCount(): Return the total number of columns.
 * SetItemCheck(): Get the status of item selection.
 * 
 * @param comboObj:  - IBMultiCombo Object name.
 * @param index:     - Index value of the clicked item.
 * @param code:      - Code value of the clicked item.
 * */
function s_jo_crr_cd_OnCheckClick(comboObj, index, code) {
    if(index==0) {          
        var bChk=comboObj.GetItemCheck(index);
        if(bChk){
            for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
                comboObj.SetItemCheck(i,0);
            }
        }
    } else {
        // Check items other than ALL.
        var bChk=comboObj.GetItemCheck(index);
        if (bChk) {
            comboObj.SetItemCheck(0,0);
        }
    }
    //When all Combo Items are Unchecked, All is automatically selected.
    var checkCnt=0;
    var count = parseInt(comboObj.GetItemCount());
    for(var i = 1 ; i <  count; i++) {
        if(comboObj.GetItemCheck(i)) {
            checkCnt++;
        }
    }
    if(checkCnt == 0) {
        comboObj.SetItemCheck(0,true,false);
    }
}

/**
 * This function adding data to combo field.
 * Web IBMultiCombo object.InsertItem(Index, Text, Code);
 * 
 * @param comboObj:   - IBMultiCombo Object name.
 * @param comboItems: - Item ComboText.
 */	
function addComboItem(comboObj, comboItems) {
	for (var i=0 ; i < comboItems.length ; i++) {
		var comboItem=comboItems[i].split(",");
		if(comboItem.length == 1){
			comboObj.InsertItem(i, comboItem[0], comboItem[0]);
		}
	}   		
}

/**
 * This function close image wait.
 * Event fires when search is completed using a search function and other internal data processing are also completed.
 * ComOpenWait() :configure whether a loading image will appears and lock the screen, value: true lock screen| false normal.
 * */
function sheet1_OnSearchEnd() { 
 	ComOpenWait(false);
}

/**
 * This function open image wait.
 * Event fires promptly before  saving method is called.
 * */
function sheet1_OnBeforeSave() {
	ComOpenWait(true);
}

/**
 * This function load search data went add button completed.
 * Event fires when saving is completed using saving function and other internal processing has been also completed.
 * */
function sheet1_OnSaveEnd() { 
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

/**
 * This function handling when sheet1 on change.
 * Event fires when the cell editing is completed and the previous value has been updated.
 * ColSaveName(): Check the SaveName set in InitColumns function that corresponds to Index of a particular column.
 * GetSearchData(): Call search page, complete search and return search result data in string.
 * 
 * @param sheetObj : Object  - Object sheet.
 * @param Row      : Long    - Row index of the cell.
 * @param Col      : Long    - Column index of the cell.
 * @param Value    : string  - Updated value.
 * @param OldValue : string  - Value before update.
 * @param RaiseFlag: Integer - Event fire option, value: 0 manual editing|1 method|2 paste.
 * */
function sheet1_OnChange(sheetObj, Row, Col, Value, OldValue, RaiseFlag){
	var formObj=document.form ;
	var colName=sheetObj.ColSaveName(Col);
	
	if(Value == ""){
		return;
	}
	
//	switch (colName) {
//	
//	//check invalid with carrier.
//	case "jo_crr_cd":
//		formObj.f_cmd.value	= COMMAND02;
//		var sParam			= FormQueryString(formObj) + "&jo_crr_cd=" + Value;
//		var sXml 			= sheetObj.GetSearchData("CARRIERJOOGS.do", sParam, {sync:1});	
//		var flag			= ComGetEtcData(sXml, "ISEXIST");
//		if(flag == 'N'){
//			ComShowCodeMessage("COM132201",["Carrier"]);
//			sheetObj.SetCellValue(Row, Col,OldValue,0);
//			sheetObj.SelectCell(Row, Col);
//		}
//		break;
//
//	//check invalid vendor code
//	case "vndr_seq":
//		formObj.f_cmd.value	= COMMAND03;
//		var sParam			= FormQueryString(formObj) + "&vndr_seq=" + Value;
//		var sXml 			= sheetObj.GetSearchData("CARRIERJOOGS.do", sParam, {sync:1});	
//		var flag			= ComGetEtcData(sXml, "ISEXIST");
//		if(flag == 'N'){
//			ComShowCodeMessage("COM132201",["Vendor"]);
//			sheetObj.SetCellValue(Row, Col,OldValue,0);
//			sheetObj.SelectCell(Row, Col);
//		}
//		break;
//		
//	//check invalid customer information
//	case "cust_seq":
//	case "cust_cnt_cd":
//		if(sheetObj.GetCellValue(Row,"cust_seq") != "" && sheetObj.GetCellValue(Row,"cust_cnt_cd") != ""){
//			formObj.f_cmd.value	= COMMAND04;
//			var sParam			= FormQueryString(formObj)+ "&cust_cnt_cd=" + sheetObj.GetCellValue(Row,"cust_cnt_cd") + "&cust_seq=" + sheetObj.GetCellValue(Row,"cust_seq") ;
//			var sXml 			= sheetObj.GetSearchData("CARRIERJOOGS.do", sParam, {sync:1});	
//			var flag			= ComGetEtcData(sXml, "ISEXIST");
//			if(flag == 'N'){
//				ComShowCodeMessage("COM132201",["Customer"]);
//				sheetObj.SetCellValue(Row, Col,OldValue,0);
//				sheetObj.SelectCell(Row, Col);
//			}
//	    }
//		break;
//	
//	//check invalid trade code
//	case "trd_cd":
//		formObj.f_cmd.value		= COMMAND05;
//		var sParam				= FormQueryString(formObj) + "&trd_cd=" + Value;
//		var sXml 				= sheetObj.GetSearchData("CARRIERJOOGS.do", sParam, {sync:1});	
//		var flag				= ComGetEtcData(sXml, "ISEXIST");
//		if(flag == 'N'){
//			ComShowCodeMessage("COM132201",["Trade"]);
//			sheetObj.SetCellValue(Row, Col,OldValue,0);
//			sheetObj.SelectCell(Row, Col);
//		}
//		break;
//	
//	default:
//		break;
//	}

	//check duplicate data
	if(colName == "jo_crr_cd" || colName == "rlane_cd"){
		if(sheetObj.GetCellValue(Row,"jo_crr_cd") != "" && sheetObj.GetCellValue(Row,"rlane_cd") != ""){
			//check on UI side
			if(sheetObj.ColValueDup("jo_crr_cd|rlane_cd") > -1){
				ComShowCodeMessage("COM12115", "The Carrier and Rev.Lane");
				sheetObj.SetCellValue(Row, Col,OldValue,0);
				sheetObj.SelectCell(Row, Col);
				return;
			}
			//check on Service side
			formObj.f_cmd.value	= COMMAND01;
			var sParam			= FormQueryString(formObj) + "&jo_crr_cd=" + sheetObj.GetCellValue(Row,"jo_crr_cd") + "&rlane_cd=" + sheetObj.GetCellValue(Row,"rlane_cd");
			var sXml 			= sheetObj.GetSearchData("CARRIERJOOGS.do", sParam, {sync:1});	
			var flag			= ComGetEtcData(sXml, "ISEXIST");
			if(flag == 'Y'){
				ComShowCodeMessage("COM12115");
				sheetObj.SetCellValue(Row, Col,OldValue,0);
				sheetObj.SelectCell(Row, Col);
			}
		}
	}
	
}

/**
 * This function handling process for input validation.
 * */
function validateForm(sheetObj, formObj, sAction) {
	switch (sAction) {
	case IBSEARCH: // retrieve
		var creDtFm = form.s_cre_dt_fm;
        var creDtTo = form.s_cre_dt_to;
        if(creDtFm.value != "" && creDtTo.value != "" && creDtFm.value > creDtTo.value) {
            ComShowCodeMessage("COMD1213");
            ComSetFocus(creDtFm);
            return false;
        }
        if(!ComChkObjValid(creDtFm)) {return false;}
        if(!ComChkObjValid(creDtTo)) {return false;}
		break;
	}
	return true;
}

/**
 * This function handling process for input validation vendor.
 * */
function validateVendor(vendor) {
	if(vendor == ""){
		return;
	}
	if(!ComIsNumber(vendor)){
		ComShowCodeMessage("COM140000");
		return;
		
	} 
}

/**
 * Event fires when user clicks the pop-up button in the cell that appears when focus is put on the cell,
 * or tries to edit the cell, given that a cell type is either Pop-up or PopupEdit.
 * 
 * @param Row: Long - Row index of the cell.
 * @param Col: Long - Column index of the cell.
 * */
function sheet1_OnPopupClick(sheetObj,Row,Col) {
	switch (sheetObj.ColSaveName(Col)) {
	case "jo_crr_cd":
		/**
		 * This function open the pop-up.
		 * 
		 * @param sUrl: {string} - Required, pop-up address to be called.
		 * @param iWidth: {int} - Required, the width of the pop-up window
		 * @param iHeight: {int} - Required, the height of the pop-up window
		 * @param sFunc: {string} - Required, function return data to parent window.
		 * @param sDisplay: {string} - Required, column of the grid in the pop-up window is hidden, value: 1 visible|0 hidden.
		 * @param bModal: {bool} - Selection, is the pop-up modal?
		 * */
		ComOpenPopup('/opuscntr/COM_ENS_0N1.do?', 900, 520, 'setJoCrrCd', '1,0,1', true, false, Row, Col);
   		break;
	case "vndr_seq":
		ComOpenPopup('/opuscntr/COM_COM_0007.do?', 900, 520, 'setVndrCd', '1,0,1', true, false, Row, Col);
   		break;	
	case "cust_cnt_cd":
	case "cust_seq":
		ComOpenPopup('/opuscntr/COM_ENS_041.do?', 900, 520, 'setCustCd', '1,0,1', true, false, Row, Col);
   		break;	
	case "trd_cd":
		ComOpenPopup('/opuscntr/COM_COM_0012.do?', 900, 520, 'setTrdCd', '1,0,1', true, false, Row, Col);
   		break;	
	default:
		break;
	}
}


/**
 * This function return data for cell pop-up column carrier.
 * */
function setJoCrrCd(aryPopupData, row, col, sheetIdx){
	var sheetObject=sheetObjects[0];
	sheetObject.SetCellValue(row,col,aryPopupData[0][3]);
}

/**
 * This function return data for cell pop-up column vendor code.
 * */
function setVndrCd(aryPopupData, row, col, sheetIdx){
	var sheetObject=sheetObjects[0];
	sheetObject.SetCellValue(row,col,aryPopupData[0][2]);
}

/**
 * This function return data for cell pop-up column customer code.
 * */
function setCustCd(aryPopupData, row, col, sheetIdx){
	var sheetObject=sheetObjects[0];
	sheetObject.SetCellValue(row,"cust_cnt_cd",aryPopupData[0][3].substring(0,2));
	sheetObject.SetCellValue(row,"cust_seq",aryPopupData[0][3].substring(2));
}

/**
 * This function return data for cell pop-up column trade.
 * */
function setTrdCd(aryPopupData, row, col, sheetIdx){
	var sheetObject=sheetObjects[0];
	sheetObject.SetCellValue(row,col,aryPopupData[0][3]);
}



