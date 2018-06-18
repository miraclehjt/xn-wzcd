package com.cdkj.loan.dto.req;

public class XN632280Req {

    // 预算单编号
    private String code;

    // 收款账号编号
    private String zfSkBankcardCode;

    // 收款金额
    private String zfSkAmount;

    // 收款时间
    private String zfSkReceiptDatetime;

    // 备注
    private String zfFinanceRemark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZfSkBankcardCode() {
        return zfSkBankcardCode;
    }

    public void setZfSkBankcardCode(String zfSkBankcardCode) {
        this.zfSkBankcardCode = zfSkBankcardCode;
    }

    public String getZfSkAmount() {
        return zfSkAmount;
    }

    public void setZfSkAmount(String zfSkAmount) {
        this.zfSkAmount = zfSkAmount;
    }

    public String getZfSkReceiptDatetime() {
        return zfSkReceiptDatetime;
    }

    public void setZfSkReceiptDatetime(String zfSkReceiptDatetime) {
        this.zfSkReceiptDatetime = zfSkReceiptDatetime;
    }

    public String getZfFinanceRemark() {
        return zfFinanceRemark;
    }

    public void setZfFinanceRemark(String zfFinanceRemark) {
        this.zfFinanceRemark = zfFinanceRemark;
    }

}