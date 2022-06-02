package com.clt.apps.opus.dou.esmdou0108.esmdou0108.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

public class InvoiceCondVO extends AbstractValueObject{
private static final long serialVersionUID = 1L;
	
	private Collection<InvoiceCondVO> models = new ArrayList<InvoiceCondVO>();
	
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String modiCostCtrCd = null;
	/* Column Info */
	private String joCrrCd = null;
	/* Column Info */
	private String rlaneCd = null;
	/* Column Info */
	private String toAcctYrmon = null;
	/* Column Info */
	private String frAcctYrmon = null;
	/* Page Number */
	private String pagerows = null;

	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public InvoiceCondVO() {}

	public InvoiceCondVO(String ibflag, String pagerows, String modiCostCtrCd, String joCrrCd, String rlaneCd, String frAcctYrmon, String toAcctYrmon) {
		this.ibflag = ibflag;
		this.modiCostCtrCd = modiCostCtrCd;
		this.joCrrCd = joCrrCd;
		this.rlaneCd = rlaneCd;
		this.toAcctYrmon = toAcctYrmon;
		this.frAcctYrmon = frAcctYrmon;
		this.pagerows = pagerows;
	}
	
	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("modi_cost_ctr_cd", getModiCostCtrCd());
		this.hashColumns.put("jo_crr_cd", getJoCrrCd());
		this.hashColumns.put("rlane_cd", getRlaneCd());
		this.hashColumns.put("to_acct_yrmon", getToAcctYrmon());
		this.hashColumns.put("fr_acct_yrmon", getFrAcctYrmon());
		this.hashColumns.put("pagerows", getPagerows());
		return this.hashColumns;
	}
	
	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환   
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("modi_cost_ctr_cd", "modiCostCtrCd");
		this.hashFields.put("jo_crr_cd", "joCrrCd");
		this.hashFields.put("rlane_cd", "rlaneCd");
		this.hashFields.put("to_acct_yrmon", "toAcctYrmon");
		this.hashFields.put("fr_acct_yrmon", "frAcctYrmon");
		this.hashFields.put("pagerows", "pagerows");
		return this.hashFields;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}
	
	/**
	 * Column Info
	 * @return modiCostCtrCd
	 */
	public String getModiCostCtrCd() {
		return this.modiCostCtrCd;
	}
	
	/**
	 * Column Info
	 * @return joCrrCd
	 */
	public String getJoCrrCd() {
		return this.joCrrCd;
	}
	
	/**
	 * Column Info
	 * @return rlaneCd
	 */
	public String getRlaneCd() {
		return this.rlaneCd;
	}
	
	/**
	 * Column Info
	 * @return toAcctYrmon
	 */
	public String getToAcctYrmon() {
		return this.toAcctYrmon;
	}
	
	/**
	 * Column Info
	 * @return frAcctYrmon
	 */
	public String getFrAcctYrmon() {
		return this.frAcctYrmon;
	}
	
	/**
	 * Page Number
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}
	

	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}
	
	/**
	 * Column Info
	 * @param modiCostCtrCd
	 */
	public void setModiCostCtrCd(String modiCostCtrCd) {
		this.modiCostCtrCd = modiCostCtrCd;
	}
	
	/**
	 * Column Info
	 * @param joCrrCd
	 */
	public void setJoCrrCd(String joCrrCd) {
		this.joCrrCd = joCrrCd;
	}
	
	/**
	 * Column Info
	 * @param rlaneCd
	 */
	public void setRlaneCd(String rlaneCd) {
		this.rlaneCd = rlaneCd;
	}
	
	/**
	 * Column Info
	 * @param toAcctYrmon
	 */
	public void setToAcctYrmon(String toAcctYrmon) {
		this.toAcctYrmon = toAcctYrmon;
	}
	
	/**
	 * Column Info
	 * @param frAcctYrmon
	 */
	public void setFrAcctYrmon(String frAcctYrmon) {
		this.frAcctYrmon = frAcctYrmon;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		fromRequest(request,"");
	}

	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request, String prefix) {
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setModiCostCtrCd(JSPUtil.getParameter(request, prefix + "modi_cost_ctr_cd", ""));
		setJoCrrCd(JSPUtil.getParameter(request, prefix + "jo_crr_cd", ""));
		setRlaneCd(JSPUtil.getParameter(request, prefix + "rlane_cd", ""));
		setToAcctYrmon(JSPUtil.getParameter(request, prefix + "to_acct_yrmon", ""));
		setFrAcctYrmon(JSPUtil.getParameter(request, prefix + "fr_acct_yrmon", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * @param request
	 * @return InvoiceCondVO[]
	 */
	public InvoiceCondVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return InvoiceCondVO[]
	 */
	public InvoiceCondVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		InvoiceCondVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] modiCostCtrCd = (JSPUtil.getParameter(request, prefix	+ "modi_cost_ctr_cd", length));
			String[] joCrrCd = (JSPUtil.getParameter(request, prefix	+ "jo_crr_cd", length));
			String[] rlaneCd = (JSPUtil.getParameter(request, prefix	+ "rlane_cd", length));
			String[] toAcctYrmon = (JSPUtil.getParameter(request, prefix	+ "to_acct_yrmon", length));
			String[] frAcctYrmon = (JSPUtil.getParameter(request, prefix	+ "fr_acct_yrmon", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			
			for (int i = 0; i < length; i++) {
				model = new InvoiceCondVO();
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (modiCostCtrCd[i] != null)
					model.setModiCostCtrCd(modiCostCtrCd[i]);
				if (joCrrCd[i] != null)
					model.setJoCrrCd(joCrrCd[i]);
				if (rlaneCd[i] != null)
					model.setRlaneCd(rlaneCd[i]);
				if (toAcctYrmon[i] != null)
					model.setToAcctYrmon(toAcctYrmon[i]);
				if (frAcctYrmon[i] != null)
					model.setFrAcctYrmon(frAcctYrmon[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getInvoiceCondVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return InvoiceCondVO[]
	 */
	public InvoiceCondVO[] getInvoiceCondVOs(){
		InvoiceCondVO[] vos = (InvoiceCondVO[])models.toArray(new InvoiceCondVO[models.size()]);
		return vos;
	}
	
	/**
	 * VO Class의 내용을 String으로 변환
	 */
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
	   }

	/**
	* 포맷팅된 문자열에서 특수문자 제거("-","/",",",":")
	*/
	public void unDataFormat(){
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.modiCostCtrCd = this.modiCostCtrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.joCrrCd = this.joCrrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.rlaneCd = this.rlaneCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.toAcctYrmon = this.toAcctYrmon .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.frAcctYrmon = this.frAcctYrmon .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}

}
