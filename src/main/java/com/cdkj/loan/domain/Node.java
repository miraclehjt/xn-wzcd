package com.cdkj.loan.domain;

import com.cdkj.loan.dao.base.ABaseDO;

public class Node extends ABaseDO {
    private static final long serialVersionUID = 4733016091171187458L;

    private String code;// 节点编号

    private String name;// 节点名称

    private String type;// 类型

    private String remark;// 备注

    // **********db properties**********

    private String nameQuery;

    private String roleCode;// 角色编号

    private String isChoice; // 当前角色是否拥有

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNameQuery() {
        return nameQuery;
    }

    public void setNameQuery(String nameQuery) {
        this.nameQuery = nameQuery;
    }

}
