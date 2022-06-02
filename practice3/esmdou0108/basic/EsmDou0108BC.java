/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : EsmDou0108BC.java
*@FileTitle : EsmDou0108
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.esmdou0108.esmdou0108.basic;

import java.util.List;

import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.ErrMsgMstVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.EsmDou0108VO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Esmdou0108 Business Logic Command Interface<br>
 * - ALPS-Esmdou0108에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Hoang Nam Vuong
 * @since J2EE 1.6
 */

public interface EsmDou0108BC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO	esmDou0108VO
	 * @return List<EsmDou0108VO>
	 * @exception EventException
	 */
	public List<EsmDou0108VO> searchEsmDou(EsmDou0108VO esmDou0108VO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO[] esmDou0108VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiEsmDou(EsmDou0108VO[] esmDou0108VO,SignOnUserAccount account) throws EventException;
	
	public List<ErrMsgMstVO> searchPartner(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException;
	
	public List<ErrMsgMstVO> searchLane(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException;
	
	public List<ErrMsgMstVO> searchTrade(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException;

	public List<ErrMsgMstVO> searchDetail(ErrMsgMstVO errMsgMstVO) throws EventException;
}