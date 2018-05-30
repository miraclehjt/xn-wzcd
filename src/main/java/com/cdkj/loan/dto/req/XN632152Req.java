package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 资料传递收件并补发
 * @author: silver 
 * @since: 2018年5月29日 下午11:02:18 
 * @history:
 */
public class XN632152Req {
    // 编号
    @NotBlank
    private String code;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}