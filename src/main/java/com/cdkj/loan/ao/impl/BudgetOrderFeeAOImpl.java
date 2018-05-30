package com.cdkj.loan.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.IBudgetOrderFeeAO;
import com.cdkj.loan.bo.IBudgetOrderBO;
import com.cdkj.loan.bo.IBudgetOrderFeeBO;
import com.cdkj.loan.bo.IBudgetOrderFeeDetailBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.BudgetOrderFee;
import com.cdkj.loan.domain.BudgetOrderFeeDetail;
import com.cdkj.loan.exception.BizException;

/**
 * 准入单手续费
 * @author: jiafr 
 * @since: 2018年5月30日 下午9:46:40 
 * @history:
 */
@Service
public class BudgetOrderFeeAOImpl implements IBudgetOrderFeeAO {

    @Autowired
    private IBudgetOrderFeeBO budgetOrderFeeBO;

    @Autowired
    private IBudgetOrderFeeDetailBO budgetOrderFeeDetailBO;

    @Autowired
    private IBudgetOrderBO budgetOrderBO;

    @Override
    public String addBudgetOrderFee(BudgetOrderFee data) {
        return budgetOrderFeeBO.saveBudgetOrderFee(data);
    }

    @Override
    public int editBudgetOrderFee(BudgetOrderFee data) {
        if (!budgetOrderFeeBO.isBudgetOrderFeeExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return budgetOrderFeeBO.refreshBudgetOrderFee(data);
    }

    @Override
    public int dropBudgetOrderFee(String code) {
        if (!budgetOrderFeeBO.isBudgetOrderFeeExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return budgetOrderFeeBO.removeBudgetOrderFee(code);
    }

    @Override
    public Paginable<BudgetOrderFee> queryBudgetOrderFeePage(int start,
            int limit, BudgetOrderFee condition) {
        return budgetOrderFeeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BudgetOrderFee> queryBudgetOrderFeeList(BudgetOrderFee condition) {
        return budgetOrderFeeBO.queryBudgetOrderFeeList(condition);
    }

    @Override
    public BudgetOrderFee getBudgetOrderFee(String code) {
        BudgetOrderFee budgetOrderFee = budgetOrderFeeBO
            .getBudgetOrderFee(code);
        if (null == budgetOrderFee) {
            throw new BizException("xn0000", "手续费编号不存在");
        }

        BudgetOrderFeeDetail condition = new BudgetOrderFeeDetail();
        condition.setFeeCode(code);
        List<BudgetOrderFeeDetail> list = budgetOrderFeeDetailBO
            .queryBudgetOrderFeeDetailList(condition);

        Long realAmount = 0L;

        for (BudgetOrderFeeDetail budgetOrderFeeDetail : list) {

            Long amount = budgetOrderFeeDetail.getAmount();

            amount += realAmount;

        }
        budgetOrderFee.setRealAmount(realAmount);
        if (realAmount >= budgetOrderFee.getShouldAmount()) {
            budgetOrderFee.setIsSettled("1");
        }

        budgetOrderFee.setBudgetOrderFeeDetailList(list);

        BudgetOrder budgetOrder = budgetOrderBO.getBudgetOrder(budgetOrderFee
            .getBudgetOrder());

        if (null != budgetOrder) {

            budgetOrderFee.setLoanBankCode(budgetOrder.getLoanBank());

            budgetOrderFee.setLoanAmount(budgetOrder.getLoanAmount());
        }

        return budgetOrderFee;
    }
}
