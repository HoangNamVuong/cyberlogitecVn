/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooBC.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.29
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.29 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.basic;

import java.util.List;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.vo.JooCarrierVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Carrierjoo Business Logic Command Interface<br>
 * - ALPS-Carrierjoo에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Hoang Nam Vuong
 * @since J2EE 1.6
 */

public interface CarrierJooBC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierJooVO	carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchListCarrierJoo(CarrierJooVO carrierJooVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierJooVO[] carrierJooVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCarrierJoo(CarrierJooVO[] carrierJooVO,SignOnUserAccount account) throws EventException;

	/**
	 * This method for searching RD Lane Code list for dropdown list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchRLaneCd(CarrierJooVO carrierJooVO) throws EventException;

	/**
	 * This method for searching Carrier Code list for dropdown list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchCrrCd(CarrierJooVO carrierJooVO) throws EventException;


}