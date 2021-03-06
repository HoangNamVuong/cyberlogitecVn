<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ESM_DOU_0108.jsp
*@FileTitle : EsmDou0108
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@Author : Hoang Nam Vuong
*@LastVersion : 1.0
* 2022.05.30 
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
<%@ page import="com.clt.apps.opus.dou.esmdou0108.esmdou0108.event.EsmDou0108Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	EsmDou0108Event  event = null;					
	Exception serverException   = null;			
	String strErrMsg = "";						
	int rowCount	 = 0;						

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	String trdCd			= "";
	String rlaneCd 			= "";
	String crrCds			= "";
	String partner			= "";
	Logger log = Logger.getLogger("com.clt.apps.EsmDou0108.EsmDou0108");

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (EsmDou0108Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		partner = eventResponse.getETCData("jo_crr_cd");
		rlaneCd = eventResponse.getETCData("rlane_cd");
		trdCd = eventResponse.getETCData("trd_cd");
	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<script language="javascript">
	var partner = "All|<%=partner%>";
	var rlaneCd = "All|<%=rlaneCd%>";
	var trdCD 	= "All|<%=trdCd%>";
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
		
		today=new Date();
	    var year=today.getFullYear();
	    var lastMonth=today.getMonth();
	   	var thisMonth=today.getMonth()+1;
	   	if(lastMonth<10) {
	   		var prev = year + "-" +"0"+ lastMonth;
	   		var now = year + "-" +"0"+ thisMonth;
	   	} else {
	   		var prev = year + "-" + lastMonth;
	   		var now = year + "-" + thisMonth;
	   	}
	   	
	   	
	    document.getElementById("fr_acct_yrmon").value = prev ;
		document.getElementById("to_acct_yrmon").value = now ;
	}
</script>
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">

<!-- ????????? ??????	-->

	<div class="page_title_area clear">
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		<div class="opus_design_btn">
		   <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
		   --><button type="button" class="btn_normal" name="btn_New" id="btn_New">New</button><!--
		   --><button type="button" class="btn_normal" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button><!--
		   --><button type="button" class="btn_normal" name="btn_DownExcel2" id="btn_DownExcel2">Down Excel 2</button>
		</div>
		
	    <div class="location">
	        <span id="navigation"></span>
	    </div>
	</div>
	
	<div class="wrap_search">
		<div class="opus_design_inquiry">
		    <table>
		    <colgroup>
					<col width="100" />				
					<col width="100" />						
					<col width="70" />	
					<col width="100" />				
					<col width="70" />					
					<col width="100" />	
					<col width="70" />			
			   </colgroup> 
		        <tbody>
					<tr>
						<th width="">Year month</th>					
							<td><input type="text" style="width:80px;" class="input1" dataformat="ym" maxlength="8" name="fr_acct_yrmon" value="" id="fr_acct_yrmon" cofield="btn_from_back"/> 
							   <button type="button" class="btn_left" name="btn_from_back" id="btn_from_back"></button><!--  
							   --><button type="button" class="btn_right" name="btn_from_next" id="btn_from_next"></button>~  
							   <input type="text" style="width:80px;" class="input1" maxlength="8" dataformat="ym"  name="to_acct_yrmon" value="" id="to_acct_yrmon" cofield="btn_to_next" />
							   <button type="button" class="btn_left" name="btn_to_back" id="btn_to_back"></button><!--  
							   --><button type="button" class="btn_right" name="btn_to_next" id="btn_to_next"></button></td>
						<th width="">Partner</th>
							<td> <script type="text/javascript">ComComboObject('jo_crr_cd');</script></td>						
						<th width="">Lane</th>
							<td> <script type="text/javascript">ComComboObject('rlane_cd');</script></td>
						<th width="">Trade</th>
							<td> <script type="text/javascript">ComComboObject('trd_cd');</script></td>
					</tr> 	
				</tbody>
			</table>
		</div>
	</div>
	
<div class="wrap_result">
	<!-- layout_wrap(S) -->
	<div class="layout_wrap">
		<div class="opus_design_tab sm">
			<script language="javascript">ComTabObject('tab1');</script>
		</div>
		
		<!-- opus_design_grid(S) -->	
		<div class="opus_design_grid clear" style="width:98%" name="tabLayer" id="tabLayer">
			<script type="text/javascript">ComSheetObject('t1sheet1');</script>
		</div>
		<div class="opus_design_grid clear" style="width:98%" name="tabLayer" id="tabLayer">
			<script type="text/javascript">ComSheetObject('t2sheet1');</script>
		</div>
		<!-- opus_design_grid(E) -->
	</div>
</div>


<!-- ????????? ??????  ??? -->
</form>