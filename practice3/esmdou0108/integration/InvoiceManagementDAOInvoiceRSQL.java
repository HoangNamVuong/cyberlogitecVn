/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceManagementDAOInvoiceRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.31
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.31 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.esmdou0108.esmdou0108.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Hoang Nam Vuong
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class InvoiceManagementDAOInvoiceRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * InvoiceManagementDAOInvoiceRSQL
	  * </pre>
	  */
	public InvoiceManagementDAOInvoiceRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("to_acct_yrmon",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("fr_acct_yrmon",new String[]{arrTmp[0],arrTmp[1]});

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
		params.put("trd_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.dou.esmdou0108.esmdou0108.integration ").append("\n"); 
		query.append("FileName : InvoiceManagementDAOInvoiceRSQL").append("\n"); 
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
		query.append("SELECT JO_CRR_CD" ).append("\n"); 
		query.append("     , RLANE_CD" ).append("\n"); 
		query.append("     , INV_NO" ).append("\n"); 
		query.append("     , CSR_NO" ).append("\n"); 
		query.append("     , APRO_FLG" ).append("\n"); 
		query.append("	 , LOCL_CURR_CD" ).append("\n"); 
		query.append("	 , INV_REV_ACT_AMT" ).append("\n"); 
		query.append("     , INV_EXP_ACT_AMT" ).append("\n"); 
		query.append("     , CUST_VNDR_CNT_CD" ).append("\n"); 
		query.append("     , CUST_VNDR_SEQ	 " ).append("\n"); 
		query.append("     , CUST_VNDR_ENG_NM" ).append("\n"); 
		query.append("     , PRNR_REF_NO" ).append("\n"); 
		query.append("FROM TESTPRC4" ).append("\n"); 
		query.append("WHERE 1=1" ).append("\n"); 
		query.append("AND ACCT_YRMON   BETWEEN REPLACE(@[fr_acct_yrmon],'-','') AND REPLACE(@[to_acct_yrmon],'-','')" ).append("\n"); 
		query.append("	#if (${jo_crr_cds}!=''  && ${jo_crr_cds} != 'ALL')" ).append("\n"); 
		query.append("        AND JO_CRR_CD    IN ( #foreach($key IN ${jo_crr_cds})#if($velocityCount < $jo_crr_cds.size()) '$key', #else '$key' #end #end)" ).append("\n"); 
		query.append("	#end" ).append("\n"); 
		query.append("	#if (${rlane_cd}!='')" ).append("\n"); 
		query.append("		AND RLANE_CD   = @[rlane_cd]" ).append("\n"); 
		query.append("	#end" ).append("\n"); 
		query.append("	#if (${trd_cd}!='')" ).append("\n"); 
		query.append("	   AND MODI_COST_CTR_CD = @[trd_cd]" ).append("\n"); 
		query.append("	#end" ).append("\n"); 

	}
}