/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : EsmDou0108SC.java
*@FileTitle : EsmDou0108
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.esmdou0108;

import java.util.List;

import com.clt.apps.opus.dou.esmdou0108.esmdou0108.basic.EsmDou0108BC;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.basic.EsmDou0108BCImpl;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.event.EsmDou0108Event;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.integration.EsmDou0108DBDAO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.ErrMsgMstVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.EsmDou0108VO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;


/**
 * ALPS-EsmDou0108 Business Logic ServiceCommand - ALPS-EsmDou0108 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Hoang Nam Vuong
 * @see EsmDou0108DBDAO
 * @since J2EE 1.6
 */

public class EsmDou0108SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * EsmDou0108 system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("EsmDou0108SC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * EsmDou0108 system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("EsmDou0108SC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-EsmDou0108 system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("EsmDou0108Event")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = searchPartner(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchSummary(e);				
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchPartner(e);
				eventResponse = searchLane(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {		
				eventResponse = searchTrade(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH03)) {
				eventResponse = searchSummary(e);	
			}
		}
		return eventResponse;
	}
	
	private EventResponse searchSummary(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		EsmDou0108BC command = new EsmDou0108BCImpl();
		
		
		try{
			
			List<ErrMsgMstVO> list = command.searchDetail(event.getErrMsgMstVOs());
			eventResponse.setRsVoList(list);

			
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	private	EventResponse searchLane(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		EsmDou0108BC command = new EsmDou0108BCImpl();
		
		try {
		
			List<ErrMsgMstVO> laneList = command.searchLane(event.getErrMsgMstVOs());
			StringBuilder laneBuilder = new StringBuilder();
			if(null != laneList && laneList.size() > 0){
				for(int i =0; i < laneList.size(); i++){
					laneBuilder.append(laneList.get(i).getRlaneCd());
					if (i < laneList.size() - 1){
						laneBuilder.append("|");
					}	
				}
			}
					
			eventResponse.setETCData("rlane_cd",  laneBuilder.toString());
					
		} catch (DAOException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 
		
		return eventResponse;		
	}
	
	private	EventResponse searchPartner(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		EsmDou0108BC command = new EsmDou0108BCImpl();
		try{
			List<ErrMsgMstVO> partnerList = command.searchPartner(event.getErrMsgMstVOs());		
			StringBuilder partnerBuilder = new StringBuilder();
					
			if(null != partnerList && partnerList.size() > 0){
				for(int i =0; i < partnerList.size(); i++){
					partnerBuilder.append(partnerList.get(i).getJoCrrCd());
					if (i < partnerList.size() - 1 ){
							partnerBuilder.append("|");
						}	
					}
				}
			
			eventResponse.setETCData("jo_crr_cd",  partnerBuilder.toString());		
					
			
		} catch (EventException | DAOException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 
		return eventResponse;		
	}
	
	private EventResponse searchTrade(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		EsmDou0108BC command = new EsmDou0108BCImpl();
		
		try {
			List<ErrMsgMstVO> tradeList = command.searchTrade(event.getErrMsgMstVOs());
			StringBuilder tradeBuilder = new StringBuilder();
			
			if(null != tradeList && tradeList.size() > 0){
				for(int i =0; i < tradeList.size(); i++){
					if(tradeList.get(i).getTrdCd()=="") {
						continue;
					}else
						tradeBuilder.append(tradeList.get(i).getTrdCd());
					if (i < tradeList.size() - 1){				
						tradeBuilder.append("|");
					}	
				}
			}
			
			eventResponse.setETCData("trd_cd",  tradeBuilder.toString());
		} catch (EventException | DAOException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 
		return eventResponse;
	}
	/**
	 * ESM_DOU_0108 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchEsmDou(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		EsmDou0108BC command = new EsmDou0108BCImpl();

		try{
			List<EsmDou0108VO> list = command.searchEsmDou(event.getEsmDou0108VO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * ESM_DOU_0108 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse multiEsmDou(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		EsmDou0108BC command = new EsmDou0108BCImpl();
		try{
			begin();
			command.multiEsmDou(event.getEsmDou0108VOS(),account);
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
}