/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooSC.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.29
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.29 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.basic.CarrierJooBC;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.basic.CarrierJooBCImpl;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.event.CarrierjooEvent;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration.CarrierJooDBDAO;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.basic.JooCarrierMgmtBC;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.basic.JooCarrierMgmtBCImpl;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.event.FnsJoo0901Event;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.vo.JooCarrierVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;


/**
 * ALPS-CarrierJoo Business Logic ServiceCommand - ALPS-CarrierJoo 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Hoang Nam Vuong
 * @see CarrierJooDBDAO
 * @since J2EE 1.6
 */

public class CarrierJooSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * CarrierJoo system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("CarrierJooSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * CarrierJoo system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("CarrierJooSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-CarrierJoo system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("CarrierjooEvent")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initData(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchListCarrierJoo(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = multiCarrierJoo(e);
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = chkDupData(e);
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND02) 
					||e.getFormCommand().isCommand(FormCommand.COMMAND03)
					||e.getFormCommand().isCommand(FormCommand.COMMAND04)
					||e.getFormCommand().isCommand(FormCommand.COMMAND05)) {
				eventResponse = chkInvalid(e);
			}
			
		}
		return eventResponse;
	}
	
	/**
	 * This method for initial data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse initData(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();

		try{
			List<CarrierJooVO> rlaneCds = command.searchRLaneCd(event.getCarrierJooVO());
			StringBuilder rlaneCdsBuilder = new StringBuilder();
			if(null != rlaneCds && rlaneCds.size() > 0){
				for(int i =0; i < rlaneCds.size(); i++){
					rlaneCdsBuilder.append(rlaneCds.get(i).getRlaneCd());
					if (i < rlaneCds.size() - 1){
						rlaneCdsBuilder.append("|");
					}	
				}
			}
			List<CarrierJooVO> crrCds = command.searchCrrCd(event.getCarrierJooVO());
			StringBuilder crrCdsBuilder = new StringBuilder();
			if(null != crrCds && crrCds.size() > 0){
				for(int i =0; i < crrCds.size(); i++){
					crrCdsBuilder.append(crrCds.get(i).getJoCrrCd());
					if (i < crrCds.size() - 1){
						crrCdsBuilder.append("|");
					}	
				}
			}
			eventResponse.setETCData("rlaneCds", rlaneCdsBuilder.toString());
			eventResponse.setETCData("crrCds", crrCdsBuilder.toString());
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}

	/**
	 * CARRIERJOO : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchListCarrierJoo(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();

		try{
			List<CarrierJooVO> list = command.searchListCarrierJoo(event.getCarrierJooVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * CARRIERJOO : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse multiCarrierJoo(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();
		try{
			begin();
			command.multiCarrierJoo(event.getCarrierJooVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	
	/**
	 * this method for checking duplicate data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse chkDupData(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();
		
		try{
			List<CarrierJooVO> list = command.searchListCarrierJoo(event.getCarrierJooVO());
			if(null == list){
				list = new ArrayList<>();
			}
			eventResponse.setETCData("ISEXIST", list.size() > 0 ? "Y" : "N");
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}

	/**
	 * this method for checking invalid data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse chkInvalid(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();
		List<JooCarrierVO> list = null;
//		try{
//			if (e.getFormCommand().isCommand(FormCommand.COMMAND02)) {//check invalid carrier code
//				list = command.searchCrrCd(event.getCarrierJooVO());
//			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND03)) {//check invalid vendor code
//				list = command.searchVndrCd(event.getCarrierJooVO());
//			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND04)) {//check invalid customer code
//				list = command.searchCusCd(event.getCarrierJooVO());
//			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND05)) {//check invalid trade code
//				list = command.searchTrdCd(event.getCarrierJooVO());
//			}
//			if(null == list){
//				list = new ArrayList<>();
//			}
//			eventResponse.setETCData("ISEXIST", list.size() > 0 ? "Y" : "N");
//		}catch(EventException ex){
//			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
//		}catch(Exception ex){
//			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
//		}	
		return eventResponse;
	}
	
}