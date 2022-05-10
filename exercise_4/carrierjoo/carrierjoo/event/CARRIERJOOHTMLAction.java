/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CARRIERJOOHTMLAction.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.29
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.29 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.vo.JooCarrierVO;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.dou.carrierjoo 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 CarrierJooSC로 실행요청<br>
 * - CarrierJooSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Hoang Nam Vuong
 * @see CarrierJooEvent 참조
 * @since J2EE 1.6
 */

public class CARRIERJOOHTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * CARRIERJOOHTMLAction 객체를 생성
	 */
	public CARRIERJOOHTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 CarrierJooEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
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
				|| command.isCommand(FormCommand.COMMAND04) || command.isCommand(FormCommand.COMMAND05)) {//check invalid carrrier code
			event.setCarrierJooVO((CarrierJooVO)getVO(request, CarrierJooVO .class));
		}
		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}