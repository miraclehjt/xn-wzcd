package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.IBudgetOrderAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN632122Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 *  区域总经理审核
 * @author: CYL 
 * @since: 2018年6月18日 下午5:22:23 
 * @history:
 */
public class XN632122 extends AProcessor {
    private IBudgetOrderAO budgetOrderAO = SpringContextHolder
        .getBean(IBudgetOrderAO.class);

    private XN632122Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        budgetOrderAO.approveAreaManager(req.getCode(), req.getOperator(),
            req.getApproveResult(), req.getApproveNote(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN632122Req.class);
        ObjValidater.validateReq(req);
    }

}
