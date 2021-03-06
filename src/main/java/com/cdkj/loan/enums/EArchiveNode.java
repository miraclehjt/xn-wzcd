package com.cdkj.loan.enums;

/**
 * 人事档案状态
 * @author: CYL 
 * @since: 2018年8月30日 上午11:05:49 
 * @history:
 */
public enum EArchiveNode {

    BRANCH_CEO_APPROVE("015_01", "分公司总经理审批"), ADMINISTRATION_APPROVE(
        "015_02","行政部审批"), NETWORK_SKILL_APPROVE("015_03","网络技术部审批"), REAPPLY(
        "015_04", "重新申请"),Completed("015_05","已完成");

    EArchiveNode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
