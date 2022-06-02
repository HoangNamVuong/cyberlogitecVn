/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : EsmDou0108DBDAO.java
*@FileTitle : EsmDou0108
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.esmdou0108.esmdou0108.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.esmdou0108.esmdou0108.basic.EsmDou0108BCImpl;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.ErrMsgMstVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.EsmDou0108VO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;


/**
 * ALPS EsmDou0108DBDAO <br>
 * - ALPS-EsmDou0108 system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Hoang Nam Vuong
 * @see EsmDou0108BCImpl 참조
 * @since J2EE 1.6
 */
public class EsmDou0108DBDAO extends DBDAOSupport {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<ErrMsgMstVO> searchPartner(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;	
		List<ErrMsgMstVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			if(errMsgMstVO!=null) {
				Map<String, String> mapVO = errMsgMstVO.getColumnValues();
				param.putAll(mapVO);	
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOSearchPartnerRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	public List<ErrMsgMstVO> searchLane(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;		
		List<ErrMsgMstVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			if(errMsgMstVO != null) {
				Map<String, String> mapVO = errMsgMstVO.getColumnValues();
				
				List<String>  jo_crr_cds = new ArrayList<>();
				if(errMsgMstVO.getJoCrrCd() != null) {
					String[] crr_cd = errMsgMstVO.getJoCrrCd().split(",");
					for(int i = 0; i<crr_cd.length; i++) {
						jo_crr_cds.add(crr_cd[i]);
					}
				}
				
				param.putAll(mapVO);
				param.put("jo_crr_cds", jo_crr_cds);
									
				velParam.putAll(mapVO);
				velParam.put("jo_crr_cds", jo_crr_cds);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOSearchRLaneCdRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);		
			
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	public List<ErrMsgMstVO> searchTrade(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<ErrMsgMstVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		
		
		try {
			if(errMsgMstVO != null) {
				Map<String, String> mapVO = errMsgMstVO.getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			}	
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOSearchTrdCdRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);	
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ErrMsgMstVO> searchDetail(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<ErrMsgMstVO> list = null;
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(errMsgMstVO != null){
				Map<String, String> mapVO = errMsgMstVO .getColumnValues();
				List<String>  jo_crr_cds = new ArrayList<>();
				if(errMsgMstVO.getJoCrrCd() != null) {
					String[] crr_cd = errMsgMstVO.getJoCrrCd().split(",");
					for(int i = 0; i<crr_cd.length; i++) {
						jo_crr_cds.add(crr_cd[i]);
					}
				}
				
				param.putAll(mapVO);
				param.put("jo_crr_cds", jo_crr_cds);
									
				velParam.putAll(mapVO);
				velParam.put("jo_crr_cds", jo_crr_cds);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);	
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO esmDou0108VO
	 * @return List<EsmDou0108VO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<EsmDou0108VO> searchEsmDou(EsmDou0108VO esmDou0108VO) throws DAOException {
		DBRowSet dbRowset = null;
		List<EsmDou0108VO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(esmDou0108VO != null){
				Map<String, String> mapVO = esmDou0108VO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, EsmDou0108VO .class);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO esmDou0108VO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addmultiEsmDou(EsmDou0108VO esmDou0108VO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = esmDou0108VO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VOCSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO esmDou0108VO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifymultiEsmDou(EsmDou0108VO esmDou0108VO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = esmDou0108VO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VOUSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param EsmDou0108VO esmDou0108VO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removemultiEsmDou(EsmDou0108VO esmDou0108VO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = esmDou0108VO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VODSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<EsmDou0108VO> esmDou0108VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmultiEsmDouS(List<EsmDou0108VO> esmDou0108VO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(esmDou0108VO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VOCSQL(), esmDou0108VO,null);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<EsmDou0108VO> esmDou0108VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymultiEsmDouS(List<EsmDou0108VO> esmDou0108VO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(esmDou0108VO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VOUSQL(), esmDou0108VO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
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
		return updCnt;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<EsmDou0108VO> esmDou0108VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemultiEsmDouS(List<EsmDou0108VO> esmDou0108VO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(esmDou0108VO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new EsmDou0108DBDAOEsmDou0108VODSQL(), esmDou0108VO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
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
		return delCnt;
	}
	
}