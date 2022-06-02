/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CARRIERJOOHTMLAction.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.dou.carrierjoo Parsing the value of the HTML DOM object sent to the server through the screen as a Java variable.
 * - Converts the parsed information into an event, puts it in the request, and requests execution with CarrierJooSC
 * - Set EventResponse to request to send execution result from CarrierJooSC to View (JSP).
 * @author Hoang Nam Vuong
 * @see CarrierJooEvent
 * @since J2EE 1.6
 */

public class CARRIERJOOHTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;

	public CARRIERJOOHTMLAction() {}

	/**
	 * Cast value and return event.
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		CarrierjooEvent event = new CarrierjooEvent();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setCarrierJooVOS((CarrierJooVO[])getVOs(request, CarrierJooVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			event.setCarrierJooVO((CarrierJooVO)getVO(request, CarrierJooVO .class));
		}else if(command.isCommand(FormCommand.COMMAND01)) {//check duplicate data
			event.setCarrierJooVO((CarrierJooVO)getVO(request, CarrierJooVO .class));
		}else if(command.isCommand(FormCommand.COMMAND02) || command.isCommand(FormCommand.COMMAND03) 
				|| command.isCommand(FormCommand.COMMAND04) || command.isCommand(FormCommand.COMMAND05)) {//check invalid carrier code
			event.setCarrierJooVO((CarrierJooVO)getVO(request, CarrierJooVO .class));
		}
		return  event;
	}

	/**
	 * Saving the business scenario execution result value in the attribute of HttpRequest.
	 * Setting the Result Set that transmits the execution result from Service Command to View (JSP) in the request.
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving HttpRequest parsing result value in HttpRequest attribute.
	 * Http Request parsing result value set in request.
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}