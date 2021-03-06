package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630568Req {

    // 还款业务编号
    @NotBlank
    private String repayBizCode;

    // 判决附件
    @NotBlank
    private String judgePdf;

    // 判决书送达时间
    @NotBlank
    private String judgePdfDeliveryTime;

    // 操作人
    @NotBlank
    private String operator;

    public String getRepayBizCode() {
        return repayBizCode;
    }

    public void setRepayBizCode(String repayBizCode) {
        this.repayBizCode = repayBizCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getJudgePdf() {
        return judgePdf;
    }

    public void setJudgePdf(String judgePdf) {
        this.judgePdf = judgePdf;
    }

    public String getJudgePdfDeliveryTime() {
        return judgePdfDeliveryTime;
    }

    public void setJudgePdfDeliveryTime(String judgePdfDeliveryTime) {
        this.judgePdfDeliveryTime = judgePdfDeliveryTime;
    }

}
