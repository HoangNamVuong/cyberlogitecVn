/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooBCImpl.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration.CarrierJooDBDAO;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-CarrierJoo Business Logic Command Interface<br>
 *
 * @author Hoang Nam Vuong
 * @since J2EE 1.6
 */
public class CarrierJooBCImpl extends BasicCommandSupport implements CarrierJooBC {

	// Database Access Object
	private transient CarrierJooDBDAO dbDao = null;


	public CarrierJooBCImpl() {
		dbDao = new CarrierJooDBDAO();
	}
	
	
	/**
	 * This method search list data for Grid.
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	public List<CarrierJooVO> searchListCarrierJoo(CarrierJooVO carrierJooVO) throws EventException {
		try {
			return dbDao.searchListCarrierJoo(carrierJooVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 *This is a method make actions(save,modify,remove). 
	 * 
	 * @param CarrierJooVO[] carrierJooVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCarrierJoo(CarrierJooVO[] carrierJooVO, SignOnUserAccount account) throws EventException{
		try {
			List<CarrierJooVO> insertVoList = new ArrayList<CarrierJooVO>();
			List<CarrierJooVO> updateVoList = new ArrayList<CarrierJooVO>();
			List<CarrierJooVO> deleteVoList = new ArrayList<CarrierJooVO>();
			List<CarrierJooVO> list = null;
			for ( int i=0; i<carrierJooVO .length; i++ ) {
				//validation
				if ( carrierJooVO[i].getIbflag().equals("I") || carrierJooVO[i].getIbflag().equals("U")){
					//check mandatory
					if(carrierJooVO[i].getJoCrrCd().isEmpty() || carrierJooVO[i].getRlaneCd().isEmpty()
							|| carrierJooVO[i].getVndrSeq().isEmpty() || carrierJooVO[i].getCustCntCd().isEmpty()
							|| carrierJooVO[i].getCustSeq().isEmpty()){
						throw new EventException(new ErrorHandler("JOO00000").getMessage());
					}
					//check duplicate
					if(carrierJooVO[i].getIbflag().equals("I")){
						list = searchListCarrierJoo(carrierJooVO[i]);
						if(null != list && list.size() > 0){
							throw new EventException(new ErrorHandler("JOO00161").getMessage());
						}
					}
				}
				if ( carrierJooVO[i].getIbflag().equals("I")){
					carrierJooVO[i].setCreUsrId(account.getUsr_id());
					carrierJooVO[i].setUpdUsrId(account.getUsr_id());
					insertVoList.add(carrierJooVO[i]);
				} else if ( carrierJooVO[i].getIbflag().equals("U")){
					carrierJooVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(carrierJooVO[i]);
				} else if ( carrierJooVO[i].getIbflag().equals("D")){
					deleteVoList.add(carrierJooVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmultiCarrierJooS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymultiCarrierJooS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemultiCarrierJooS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * this method for searching RLane Code list, it's used drop down list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierJooVO> searchRLaneCd(CarrierJooVO carrierJooVO) throws EventException {
		try {
			return dbDao.searchRLaneCd(carrierJooVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * this method for searching Carrier Code list, it's used drop down list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierJooVO> searchCrrCd(CarrierJooVO carrierJooVO) throws EventException {
		try {
			return dbDao.searchCrrCd(carrierJooVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * this method for searching vendor Code list, it's used checking invalid data
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierJooVO> searchVndrCd(CarrierJooVO carrierJooVO) throws EventException {
		// TODO Auto-generated method stub
		try {
			return dbDao.searchVndrCd(carrierJooVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * this method for searching trade Code list, it's used checking invalid data
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierJooVO> searchTrdCd(CarrierJooVO carrierJooVO) throws EventException {
		// TODO Auto-generated method stub
		try {
			return dbDao.searchTrdCd(carrierJooVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * this method for searching customer Code list, it's used checking invalid data
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierJooVO> searchCusCd(CarrierJooVO carrierJooVO) throws EventException {
		// TODO Auto-generated method stub
		try {
			return dbDao.searchCusCd(carrierJooVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	
}