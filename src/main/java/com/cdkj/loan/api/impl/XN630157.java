package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.INodeFlowAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.NodeFlow;
import com.cdkj.loan.dto.req.XN630157Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 列表查询节点流程
 * @author: xieyj 
 * @since: 2018年5月28日 下午8:55:13 
 * @history:
 */
public class XN630157 extends AProcessor {
    private INodeFlowAO nodeFlowAO = SpringContextHolder
        .getBean(INodeFlowAO.class);

    private XN630157Req req = null;

    /** 
     * @see com.cdkj.loan.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        NodeFlow condition = new NodeFlow();
        condition.setType(req.getType());
        condition.setCurrentNode(req.getCurrentNode());
        condition.setNextNode(req.getNextNode());
        condition.setBackNode(req.getBackNode());

        return nodeFlowAO.queryNodeFlowList(condition);
    }

    /** 
     * @see com.cdkj.loan.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630157Req.class);
        ObjValidater.validateReq(req);
    }

}
