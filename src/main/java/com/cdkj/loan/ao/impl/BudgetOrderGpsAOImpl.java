package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.IBudgetOrderGpsAO;
import com.cdkj.loan.bo.IBudgetOrderGpsBO;
import com.cdkj.loan.bo.IGpsBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.domain.BudgetOrderGps;
import com.cdkj.loan.domain.Gps;
import com.cdkj.loan.dto.req.XN632342Req;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EBudgetOrderGpsStatus;
import com.cdkj.loan.enums.EGpsUseStatus;
import com.cdkj.loan.exception.BizException;

@Service
public class BudgetOrderGpsAOImpl implements IBudgetOrderGpsAO {
    @Autowired
    private IBudgetOrderGpsBO budgetOrderGpsBO;

    @Autowired
    private IGpsBO gpsBO;

    @Override
    public String saveBudgetOrderGps(XN632342Req req) {
        BudgetOrderGps data = new BudgetOrderGps();
        Gps gps = gpsBO.getGps(req.getGpsCode());
        data.setCode(req.getGpsCode());
        data.setStatus(EBudgetOrderGpsStatus.USE_ING.getCode());
        data.setGpsDevNo(gps.getGpsDevNo());
        data.setGpsType(gps.getGpsType());
        data.setAzLocation(req.getAzLocation());
        if (StringUtils.isNotBlank(req.getAzLocationRemark())) {
            data.setAzLocationRemark(req.getAzLocationRemark());
        }

        data.setAzDatetime(DateUtil.strToDate(req.getAzDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setAzUser(req.getAzUser());
        data.setRemark(req.getRemark());
        data.setBudgetOrder(req.getBudgetOrder());
        budgetOrderGpsBO.saveBudgetOrderGps(data);

        gpsBO.refreshUseGps(req.getGpsCode(), req.getBudgetOrder());
        return req.getGpsCode();
    }

    @Override
    public void abandonBudgetOrderGps(String code, String remark,
            String operator) {
        BudgetOrderGps data = budgetOrderGpsBO.getBudgetOrderGps(code);
        if (EBudgetOrderGpsStatus.INVALID.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "当前gps已作废");
        }
        data.setStatus(EBudgetOrderGpsStatus.INVALID.getCode());
        data.setRemark(remark);
        budgetOrderGpsBO.abandonBudgetOrderGps(data);
        Gps gps = gpsBO.getGpsByDevNo(data.getGpsDevNo());
        gps.setUseStatus(EGpsUseStatus.DAMAGE.getCode());
        gps.setUseDatetime(new Date());
        gps.setBizCode(null);
        gpsBO.refreshUseGpsToDamage(gps);// 作废后使用状态改为已损坏
    }

    @Override
    public Paginable<BudgetOrderGps> queryBudgetOrderGpsPage(int start,
            int limit, BudgetOrderGps condition) {
        return budgetOrderGpsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BudgetOrderGps> queryBudgetOrderGpsList(
            BudgetOrderGps condition) {
        return budgetOrderGpsBO.queryBudgetOrderGpsList(condition);
    }

    @Override
    public BudgetOrderGps getBudgetOrderGps(String code) {
        return budgetOrderGpsBO.getBudgetOrderGps(code);
    }

}
