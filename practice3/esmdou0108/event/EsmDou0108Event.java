/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : EsmDou0108Event.java
*@FileTitle : EsmDou0108
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.esmdou0108.esmdou0108.event;

import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.ErrMsgMstVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.EsmDou0108VO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.InvoiceCondVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.InvoiceDtlVO;
import com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo.InvoiceMstVO;


/**
 * ESM_DOU_0108 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  ESM_DOU_0108HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Hoang Nam Vuong
 * @see ESM_DOU_0108HTMLAction 참조
 * @since J2EE 1.6
 */

public class EsmDou0108Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	ErrMsgMstVO errMsgMstVOs = null;
	
	private DBRowSet dbrowset1 = null;
	
	private DBRowSet dbrowset2 = null;
	
	private InvoiceCondVO invoiceCondVO = null;
	
	private InvoiceDtlVO invoiceDtlVO = null;
	
	private InvoiceMstVO invoiceVO = null;
	
	private InvoiceDtlVO[] invoiceDtlVOs = null;
	
	private InvoiceMstVO[] invoiceVOs = null;
	
	//Khai bao cac bien Condition
	
	//Carrier
	private String joCrrCd = null;
	//Lane
	private String rlaneCd = null;
	//Trade
	private String modiCostCtrCd = null;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	EsmDou0108VO esmDou0108VO = null;
	
	/** Table Value Object Multi Data 처리 */
	EsmDou0108VO[] esmDou0108VOs = null;

	public EsmDou0108Event(){}
	
	public void setEsmDou0108VO(EsmDou0108VO esmDou0108VO){
		this. esmDou0108VO = esmDou0108VO;
	}

	public void setEsmDou0108VOS(EsmDou0108VO[] esmDou0108VOs){
		this. esmDou0108VOs = esmDou0108VOs;
	}

	public EsmDou0108VO getEsmDou0108VO(){
		return esmDou0108VO;
	}

	public EsmDou0108VO[] getEsmDou0108VOS(){
		return esmDou0108VOs;
	}

	public void setInvoiceCondVO(InvoiceCondVO invoiceCondVO) {
		this.invoiceCondVO = invoiceCondVO;
	}

	public InvoiceCondVO getInvoiceCondVO() {
		return invoiceCondVO;
	}
	
	public ErrMsgMstVO getErrMsgMstVOs() {
		return errMsgMstVOs;
	}

	public void setErrMsgMstVOs(ErrMsgMstVO errMsgMstVOs) {
		this.errMsgMstVOs = errMsgMstVOs;
	}


	public DBRowSet getDbrowset1() {
		return dbrowset1;
	}

	public void setDbrowset1(DBRowSet dbrowset1) {
		this.dbrowset1 = dbrowset1;
	}

	public DBRowSet getDbrowset2() {
		return dbrowset2;
	}

	public void setDbrowset2(DBRowSet dbrowset2) {
		this.dbrowset2 = dbrowset2;
	}

}