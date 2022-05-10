/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooDBDAOSearchRLaneCdRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Hoang Nam Vuong
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierJooDBDAOSearchRLaneCdRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * CarrierJooDBDAOSearchRLaneCdRSQL
	  * </pre>
	  */
	public CarrierJooDBDAOSearchRLaneCdRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration ").append("\n"); 
		query.append("FileName : CarrierJooDBDAOSearchRLaneCdRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("Select VSL_SLAN_CD as rlane_cd" ).append("\n"); 
		query.append("FROM MDM_REV_LANE " ).append("\n"); 
		query.append("WHERE 1 = 1 " ).append("\n"); 
		query.append("     AND DECODE (DELT_FLG, 'Y','D','A') = 'A' " ).append("\n"); 
		query.append("ORDER BY RLANE_CD" ).append("\n"); 

	}
}