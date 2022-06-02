/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooBC.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.basic;

import java.util.List;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Carrierjoo Business Logic Command Interface<br>
 *
 * @author Hoang Nam Vuong
 * @since J2EE 1.6
 */

public interface CarrierJooBC {

	/**
	 * This method search list data for Grid.
	 * 
	 * @param CarrierJooVO	carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchListCarrierJoo(CarrierJooVO carrierJooVO) throws EventException;
	
	/**
	 * This is a method make actions(save,modify,remove). 
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
	 * This method for searching Carrier Code list for list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchCrrCd(CarrierJooVO carrierJooVO) throws EventException;

	/**
	 * search vendor code for validation check
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchVndrCd(CarrierJooVO carrierJooVO) throws EventException;

	/**
	 * search trade code for validation check
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchTrdCd(CarrierJooVO carrierJooVO) throws EventException;

	/**
	 * search customer code for validation check
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchCusCd(CarrierJooVO carrierJooVO) throws EventException;


}