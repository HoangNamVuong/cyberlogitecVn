/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierjooEvent.java
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

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;


/**
 * CARRIERJOO 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  CARRIERJOOHTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Hoang Nam Vuong
 * @see CARRIERJOOHTMLAction 참조
 * @since J2EE 1.6
 */

public class CarrierjooEvent extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CarrierJooVO carrierJooVO = null;
	
	/** Table Value Object Multi Data 처리 */
	CarrierJooVO[] carrierJooVOs = null;

	public CarrierjooEvent(){}
	
	public void setCarrierJooVO(CarrierJooVO carrierJooVO){
		this. carrierJooVO = carrierJooVO;
	}

	public void setCarrierJooVOS(CarrierJooVO[] carrierJooVOs){
		this. carrierJooVOs = carrierJooVOs;
	}

	public CarrierJooVO getCarrierJooVO(){
		return carrierJooVO;
	}

	public CarrierJooVO[] getCarrierJooVOS(){
		return carrierJooVOs;
	}

}