package com.cdkj.loan.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.loan.domain.SupplementReason;

public class XN632153Req {
    // 编号
    @NotBlank
    private String code;

    // 寄送材料清单(逗号隔开)
    @NotBlank
    private String sendFileList;

    // 寄送方式(1 线下 2 快递)
    @NotBlank
    private String sendType;

    // 补件原因
    private List<SupplementReason> supplementReasonList;

    // 快递公司
    private String logisticsCompany;

    // 快递单号
    private String logisticsCode;

    // 发货时间
    @NotBlank
    private String sendDatetime;

    // 发货说明
    private String sendNote;

    // 操作人
    private String operater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSendFileList() {
        return sendFileList;
    }

    public void setSendFileList(String sendFileList) {
        this.sendFileList = sendFileList;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public List<SupplementReason> getSupplementReasonList() {
        return supplementReasonList;
    }

    public void setSupplementReasonList(
            List<SupplementReason> supplementReasonList) {
        this.supplementReasonList = supplementReasonList;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public String getSendNote() {
        return sendNote;
    }

    public void setSendNote(String sendNote) {
        this.sendNote = sendNote;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

}
