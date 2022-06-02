/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : EsmDou0108BCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.dou.esmdou0108.esmdou0108.integration.EsmDou0108DBDAO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.ErrMsgMstVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.EsmDou0108VO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.InvoiceCondVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.InvoiceDtlVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.InvoiceMstVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-EsmDou0108 Business Logic Command Interface<br>
 * - ALPS-EsmDou0108에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Hoang Nam Vuong
 * @since J2EE 1.6
 */
public class EsmDou0108BCImpl extends BasicCommandSupport implements EsmDou0108BC {

	// Database Access Object
	private transient EsmDou0108DBDAO dbDao = null;

	/**
	 * EsmDou0108BCImpl 객체 생성<br>
	 * EsmDou0108DBDAO를 생성한다.<br>
	 */
	public EsmDou0108BCImpl() {
		dbDao = new EsmDou0108DBDAO();
	}
	
	@Override
	public List<ErrMsgMstVO> searchPartner(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException {
		// TODO Auto-generated method stub
		return dbDao.searchPartner(errMsgMstVO);
	}


	@Override
	public List<ErrMsgMstVO> searchLane(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException {
		// TODO Auto-generated method stub
		return dbDao.searchLane(errMsgMstVO);
	}
	
	@Override
	public List<ErrMsgMstVO> searchTrade(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException {
		// TODO Auto-generated method stub
		return dbDao.searchTrade(errMsgMstVO);
	}


	@Override
	public List<ErrMsgMstVO> searchDetail(ErrMsgMstVO errMsgMstVO)
			throws EventException {

		try {
			return dbDao.searchDetail(errMsgMstVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO esmDou0108VO
	 * @return List<EsmDou0108VO>
	 * @exception EventException
	 */
	public List<EsmDou0108VO> searchEsmDou(EsmDou0108VO esmDou0108VO) throws EventException {
		try {
			return dbDao.searchEsmDou(esmDou0108VO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO[] esmDou0108VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiEsmDou(EsmDou0108VO[] esmDou0108VO, SignOnUserAccount account) throws EventException{
		try {
			List<EsmDou0108VO> insertVoList = new ArrayList<EsmDou0108VO>();
			List<EsmDou0108VO> updateVoList = new ArrayList<EsmDou0108VO>();
			List<EsmDou0108VO> deleteVoList = new ArrayList<EsmDou0108VO>();
//			for ( int i=0; i<esmDou0108VO .length; i++ ) {
//				if ( esmDou0108VO[i].getIbflag().equals("I")){
//					esmDou0108VO[i].setCreUsrId(account.getUsr_id());
//					insertVoList.add(esmDou0108VO[i]);
//				} else if ( esmDou0108VO[i].getIbflag().equals("U")){
//					esmDou0108VO[i].setUpdUsrId(account.getUsr_id());
//					updateVoList.add(esmDou0108VO[i]);
//				} else if ( esmDou0108VO[i].getIbflag().equals("D")){
//					deleteVoList.add(esmDou0108VO[i]);
//				}
//			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmultiEsmDouS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymultiEsmDouS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemultiEsmDouS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}