<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName       : CARRIERJOO.jsp
*@FileTitle      : Carrier Joo Management
*Open Issues     : No
*Change history  : Change comment
*@LastModifyDate : 2022.05.18
*@LastModifier   : HoangNamVuong
*@LastVersion    : 1.0
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.dou.carrierjoo.carrierjoo.event.CarrierjooEvent"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	CarrierjooEvent  event = null;					
	String rlaneCds			= "";
	String crrCds			= "";

	try {
		event = (CarrierjooEvent)request.getAttribute("Event");
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		rlaneCds = eventResponse.getETCData("rlaneCds");
		crrCds = eventResponse.getETCData("crrCds");
		
	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<html>
<script language="javascript">
	var rlanCombo = "|<%=rlaneCds%>";
	var crrCombo = "All|<%=crrCds%>";
	function setupPage(){
		loadPage();
	}
</script>
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
	<div class="page_title_area clear">
		<div class="opus_design_btn">
			<button class="btn_accent" name="btn_Retrieve" id="btn_Retrieve" type="button">Retrieve</button><!-- 
		    --><button class="btn_normal" name="btn_New" id="btn_New" type="button">New</button><!--
			--><button class="btn_normal" name="btn_Save" id="btn_Save" type="button">Save</button><!--
			--><button class="btn_normal" name="btn_DownExcel" id=btn_DownExcel type="button">Down Excel</button>
		</div>
	</div>
	<div class="wrap_search">
		<div class="opus_design_inquiry wFit">
			<table>
				<colgroup>
					<col width="70" />				
					<col width="100" />						
					<col width="70" />	
					<col width="100" />				
					<col width="70" />					
					<col width="*" />				
			   </colgroup> 
			   <tbody>
			   		<tr>
						<th>Carrier</th>
						<td><script type="text/javascript">ComComboObject('s_jo_crr_cd',1,80, 1, 0, 0);</script></td>
						<th>Vendor</th>
						<td><input type="text" style="width:60px;" name="s_vndr_seq" id="s_vndr_seq" onKeyPress="ComKeyOnlyNumber(this)" dataformat="num" maxlength="6"></td>
						<th>Create Date</th>
						<td>
							<input type="text" style="width:78px;" caption="Create Date From" name="s_cre_dt_fm" onKeyPress="ComKeyOnlyNumber(this, '-')" dataformat="ymd" maxLength="10" minlength="8"><!--  
							--><button type="button" class="calendar ir" name="btns_calendar1" id="btns_calendar1" ></button>~
							<input type="text" style="width:78px;" caption="Create Date To" name="s_cre_dt_to" onKeyPress="ComKeyOnlyNumber(this, '-')" dataformat="ymd" maxLength="10" minlength="8"><!-- 
							--><button type="button" class="calendar ir" name="btns_calendar2" id="btns_calendar2" ></button>
						</td>
					</tr> 
			   </tbody>
			</table>
		</div>
	</div>
	<div class="wrap_result">
		<div class="opus_design_grid">
			<div class="opus_design_btn">
				<button class="btn_normal" name="btn_RowDelete" id="btn_RowDelete" type="button">Row Delete</button>
				<button class="btn_normal" name="btn_RowAdd" id="btn_RowAdd" type="button">Row Add</button>
			</div>
			<script type="text/javascript">ComSheetObject('sheet1');</script>			
		</div>
	</div>
</form>
</html>