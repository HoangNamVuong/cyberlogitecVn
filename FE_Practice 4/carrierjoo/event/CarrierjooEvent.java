/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierjooEvent.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;


/**

 * @author Hoang Nam Vuong
 * @see CARRIERJOOHTMLAction 
 * @since J2EE 1.6
 */

public class CarrierjooEvent extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object */
	CarrierJooVO carrierJooVO = null;
	
	/** Table Value Object Multi Data */
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