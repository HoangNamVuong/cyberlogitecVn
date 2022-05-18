/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooSC.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
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
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;


/**
 * ALPS-CarrierJoo Business Logic ServiceCommand - ALPS-CarrierJoo process business transactions.
 * 
 * @author Hoang Nam Vuong
 * @see CarrierJooDBDAO
 * @since J2EE 1.6
 */

public class CarrierJooSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * CarrierJoo Start the work scenario.
	 * Creating related internal objects when calling a business scenario.
	 */
	public void doStart() {
		log.debug("CarrierJooSC Start");
		try {
			// comment --> login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * CarrierJoo system Closing the work scenario
	 * Release related internal objects at the end of the business scenario
	 */
	public void doEnd() {
		log.debug("CarrierJooSC End");
	}

	/**
	 * This is a method that divides tasks by different actions
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		EventResponse eventResponse = null;

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
	 * this method for checking duplicate data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse chkDupData(Event e) throws EventException {
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
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();
		List<CarrierJooVO> list = null;
		try{
			if (e.getFormCommand().isCommand(FormCommand.COMMAND02)) {//check invalid carrier code
				list = command.searchCrrCd(event.getCarrierJooVO());
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND03)) {//check invalid vendor code
				list = command.searchVndrCd(event.getCarrierJooVO());
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND04)) {//check invalid customer code
				list = command.searchCusCd(event.getCarrierJooVO());
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND05)) {//check invalid trade code
				list = command.searchTrdCd(event.getCarrierJooVO());
			}
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
	 * This is a method search a list data on Grid.
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchListCarrierJoo(Event e) throws EventException {
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
	 *This is a method make actions(save,modify,remove). 
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse multiCarrierJoo(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierjooEvent event = (CarrierjooEvent)e;
		CarrierJooBC command = new CarrierJooBCImpl();
		try{
			begin();
			command.multiCarrierJoo(event.getCarrierJooVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("DOU00001").getUserMessage());
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