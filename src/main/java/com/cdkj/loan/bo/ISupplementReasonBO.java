package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.SupplementReason;

public interface ISupplementReasonBO extends IPaginableBO<SupplementReason> {

    public Long saveSupplementReason(SupplementReason data);

    public List<SupplementReason> querySupplementReasonList(
            SupplementReason condition);

    public SupplementReason getSupplementReason(Long id);

    // 改变是否已补件
    public void refreshSupplementReason(SupplementReason reason);

    // 通过物流单编号查询补件原因
    public List<SupplementReason> getSupplementReasonByLogisticsCode(
            String logisticsCode);

    // 改变原来的补件原因的物流单编号
    public void refreshLogisticsCode(Long id, String loCode);

}
