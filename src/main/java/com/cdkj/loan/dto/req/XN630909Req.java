package com.cdkj.loan.dto.req;

/**
 * 月度业绩完成情况表
 * @author: jiafr 
 * @since: 2018年8月22日 上午10:40:17 
 * @history:
 */
public class XN630909Req {

    // 放款年份
    private String fkYear;

    // 业务公司
    private String companyCode;

    // 业务员
    private String saleUserId;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getFkYear() {
        return fkYear;
    }

    public void setFkYear(String fkYear) {
        this.fkYear = fkYear;
    }

    public String getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(String saleUserId) {
        this.saleUserId = saleUserId;
    }

}
