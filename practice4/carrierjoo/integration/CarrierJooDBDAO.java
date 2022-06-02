/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooDBDAO.java
*@FileTitle : Carrier Joo Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.carrierjoo.carrierjoo.basic.CarrierJooBCImpl;
import com.clt.apps.opus.dou.carrierjoo.carrierjoo.vo.CarrierJooVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;


/**
 * @author Hoang Nam Vuong
 * @see CarrierJooBCImpl
 * @since J2EE 1.6
 */
public class CarrierJooDBDAO extends DBDAOSupport {

	/**
	 * This method search list data for Grid.
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<CarrierJooVO> searchListCarrierJoo(CarrierJooVO carrierJooVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierJooVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(carrierJooVO != null){
				Map<String, String> mapVO = carrierJooVO .getColumnValues();
				List<String> list_jo_crr_cd = new ArrayList<>();
				if(null != carrierJooVO.getSJoCrrCd()){
					String[] crr_cd = carrierJooVO.getSJoCrrCd().split(",");
					for(int i = 0; i < crr_cd.length; i++){
						list_jo_crr_cd.add(crr_cd[i]);
					}
				}
				param.putAll(mapVO);
				param.put("list_jo_crr_cd", list_jo_crr_cd);
				
				velParam.putAll(mapVO);
				velParam.put("list_jo_crr_cd", list_jo_crr_cd); 
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierJooDBDAOCarrierJooVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierJooVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
//	/**
//	 * This is a method make action insert data.
//	 * 
//	 * @param CarrierJooVO carrierJooVO
//	 * @exception DAOException
//	 * @exception Exception
//	 */
//	public void addmultiCarrierJoo(CarrierJooVO carrierJooVO) throws DAOException,Exception {
//		//query parameter
//		Map<String, Object> param = new HashMap<String, Object>();
//		//velocity parameter
//		Map<String, Object> velParam = new HashMap<String, Object>();
//		try {
//			Map<String, String> mapVO = carrierJooVO .getColumnValues();
//			
//			param.putAll(mapVO);
//			velParam.putAll(mapVO);
//			
//			SQLExecuter sqlExe = new SQLExecuter("");
//			int result = sqlExe.executeUpdate((ISQLTemplate)new CarrierJooDBDAOCarrierJooVOCSQL(), param, velParam);
//			if(result == Statement.EXECUTE_FAILED)
//					throw new DAOException("Fail to insert SQL");
//		} catch(SQLException se) {
//			log.error(se.getMessage(),se);
//			throw new DAOException(new ErrorHandler(se).getMessage());
//		} catch(Exception ex) {
//			log.error(ex.getMessage(),ex);
//			throw new DAOException(new ErrorHandler(ex).getMessage());
//		}
//	}
//	
//	/**
//	 * This is a method make action modify data.
//	 * 
//	 * @param CarrierJooVO carrierJooVO
//	 * @return int
//	 * @exception DAOException
//	 * @exception Exception
//	 */
//	public int modifymultiCarrierJoo(CarrierJooVO carrierJooVO) throws DAOException,Exception {
//		//query parameter
//		Map<String, Object> param = new HashMap<String, Object>();
//		//velocity parameter
//		Map<String, Object> velParam = new HashMap<String, Object>();
//		
//		int result = 0;
//		try {
//			Map<String, String> mapVO = carrierJooVO .getColumnValues();
//			
//			param.putAll(mapVO);
//			velParam.putAll(mapVO);
//			
//			SQLExecuter sqlExe = new SQLExecuter("");
//			result = sqlExe.executeUpdate((ISQLTemplate)new CarrierJooDBDAOCarrierJooVOUSQL(), param, velParam);
//			if(result == Statement.EXECUTE_FAILED)
//					throw new DAOException("Fail to modify SQL");
//		} catch(SQLException se) {
//			log.error(se.getMessage(),se);
//			throw new DAOException(new ErrorHandler(se).getMessage());
//		} catch(Exception ex) {
//			log.error(ex.getMessage(),ex);
//			throw new DAOException(new ErrorHandler(ex).getMessage());
//		}
//		return result;
//	}
//	
//	/**
//	 * This is a method make action remove data.
//	 * 
//	 * @param CarrierJooVO carrierJooVO
//	 * @return int
//	 * @exception DAOException
//	 * @exception Exception
//	 */
//	public int removemultiCarrierJoo(CarrierJooVO carrierJooVO) throws DAOException,Exception {
//		//query parameter
//		Map<String, Object> param = new HashMap<String, Object>();
//		//velocity parameter
//		Map<String, Object> velParam = new HashMap<String, Object>();
//		
//		int result = 0;
//		try {
//			Map<String, String> mapVO = carrierJooVO .getColumnValues();
//			
//			param.putAll(mapVO);
//			velParam.putAll(mapVO);
//			
//			SQLExecuter sqlExe = new SQLExecuter("");
//			result = sqlExe.executeUpdate((ISQLTemplate)new CarrierJooDBDAOCarrierJooVODSQL(), param, velParam);
//			if(result == Statement.EXECUTE_FAILED)
//					throw new DAOException("Fail to remove SQL");
//		} catch(SQLException se) {
//			log.error(se.getMessage(),se);
//			throw new DAOException(new ErrorHandler(se).getMessage());
//		} catch(Exception ex) {
//			log.error(ex.getMessage(),ex);
//			throw new DAOException(new ErrorHandler(ex).getMessage());
//		}
//		return result;
//	}

	/**
	 * This is a method make action insert data.
	 * 
	 * @param List<CarrierJooVO> carrierJooVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmultiCarrierJooS(List<CarrierJooVO> carrierJooVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierJooVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierJooDBDAOCarrierJooVOCSQL(), carrierJooVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	/**
	 * This is a method make action modify data.
	 * 
	 * @param List<CarrierJooVO> carrierJooVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymultiCarrierJooS(List<CarrierJooVO> carrierJooVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierJooVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierJooDBDAOCarrierJooVOUSQL(), carrierJooVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to modify No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * This is a method make action remove data.
	 * 
	 * @param List<CarrierJooVO> carrierJooVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemultiCarrierJooS(List<CarrierJooVO> carrierJooVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierJooVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierJooDBDAOCarrierJooVODSQL(), carrierJooVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to remove No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}

	/**
	 * this method for searching RLane Code list, it's used drop down list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception DAOException
	 */
	public List<CarrierJooVO> searchRLaneCd(CarrierJooVO carrierJooVO) throws DAOException{
		DBRowSet dbRowset = null;
		List<CarrierJooVO> list = new ArrayList();
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierJooDBDAOSearchRLaneCdRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierJooVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * this method for searching Carrier Code list, it's used drop down list
	 * 
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception DAOException
	 */
	public List<CarrierJooVO> searchCrrCd(CarrierJooVO carrierJooVO) throws DAOException{
		 DBRowSet dbRowset = null;
		 List<CarrierJooVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(carrierJooVO != null){
				Map<String, String> mapVO = carrierJooVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierJooDBDAOSearchCrrCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierJooVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	}
	
	/**
	 * this method for searching vendor Code list, it's used checking invalid data
	 * 
	 * search vendor code
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception DAOException
	 */
	public List<CarrierJooVO> searchVndrCd(CarrierJooVO carrierJooVO) throws DAOException{
		 DBRowSet dbRowset = null;
		 List<CarrierJooVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(carrierJooVO != null){
				Map<String, String> mapVO = carrierJooVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierJooDBDAOSearchVndrCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierJooVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	}

	/**
	 * this method for searching trade Code list, it's used checking invalid data
	 * 
	 * search trade code
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception DAOException
	 */
	public List<CarrierJooVO> searchTrdCd(CarrierJooVO carrierJooVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<CarrierJooVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(carrierJooVO != null){
				Map<String, String> mapVO = carrierJooVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierJooDBDAOSearchTrdCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierJooVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	}

	/**
	 * this method for searching customer Code list, it's used checking invalid data
	 * 
	 * search customer code
	 * @param CarrierJooVO carrierJooVO
	 * @return List<CarrierJooVO>
	 * @exception DAOException
	 */
	public List<CarrierJooVO> searchCusCd(CarrierJooVO carrierJooVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<CarrierJooVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(carrierJooVO != null){
				Map<String, String> mapVO = carrierJooVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierJooDBDAOSearchCustCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierJooVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	}
	
}