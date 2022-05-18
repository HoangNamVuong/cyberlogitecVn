/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierJooDBDAOCarrierJooVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.18
*@LastModifier : HoangNamVuong
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Hoang Nam Vuong
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierJooDBDAOCarrierJooVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  *    
	  * </pre>
	  */
	public CarrierJooDBDAOCarrierJooVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("rlane_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("s_cre_dt_to",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("s_vndr_seq",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("s_cre_dt_fm",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.dou.carrierjoo.carrierjoo.integration").append("\n"); 
		query.append("FileName : CarrierJooDBDAOCarrierJooVORSQL").append("\n"); 
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
		query.append("SELECT A.JO_CRR_CD" ).append("\n"); 
		query.append("     , A.RLANE_CD" ).append("\n"); 
		query.append("     , A.VNDR_SEQ" ).append("\n"); 
		query.append("     , A.CUST_CNT_CD" ).append("\n"); 
		query.append("     , A.CUST_SEQ" ).append("\n"); 
		query.append("     , A.TRD_CD" ).append("\n"); 
		query.append("     , A.DELT_FLG" ).append("\n"); 
		query.append("     , TO_CHAR(A.CRE_DT, 'YYYY/MM/DD HH24:MI:SS') AS CRE_DT" ).append("\n"); 
		query.append("     , A.CRE_USR_ID" ).append("\n"); 
		query.append("     , TO_CHAR(A.UPD_DT, 'YYYY/MM/DD HH24:MI:SS') AS UPD_DT" ).append("\n"); 
		query.append("     , A.UPD_USR_ID" ).append("\n"); 
		query.append("FROM JOO_CARRIER A" ).append("\n"); 
		query.append("WHERE 1 = 1" ).append("\n"); 
		query.append("#if (${s_jo_crr_cd} != '' && ${s_jo_crr_cd} != 'All') " ).append("\n"); 
		query.append("AND A.JO_CRR_CD IN (" ).append("\n"); 
		query.append("	#foreach($key IN ${list_jo_crr_cd}) #if($velocityCount < $list_jo_crr_cd.size()) '$key', #else '$key' #end #end" ).append("\n"); 
		query.append(")" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${rlane_cd} != '') " ).append("\n"); 
		query.append("AND A.RLANE_CD = @[rlane_cd]" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${s_vndr_seq} != '' ) " ).append("\n"); 
		query.append("AND A.VNDR_SEQ Like @[s_vndr_seq] || '%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${s_cre_dt_fm} != '') " ).append("\n"); 
		query.append("AND A.CRE_DT >= TO_DATE(@[s_cre_dt_fm],'YYYY-MM-DD')" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${s_cre_dt_to} != '') " ).append("\n"); 
		query.append("AND A.CRE_DT <= TO_DATE(@[s_cre_dt_to]||' 23:59','YYYY-MM-DD HH24:MI')" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("ORDER BY A.JO_CRR_CD" ).append("\n"); 

	}
}