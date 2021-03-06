package com.cdkj.loan.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IBudgetOrderFeeBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.IBudgetOrderFeeDAO;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.BudgetOrderFee;
import com.cdkj.loan.enums.EBoolean;
import com.cdkj.loan.enums.EBudgetOrderFeeWay;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.exception.BizException;

@Component
public class BudgetOrderFeeBOImpl extends PaginableBOImpl<BudgetOrderFee>
        implements IBudgetOrderFeeBO {

    @Autowired
    private IBudgetOrderFeeDAO budgetOrderFeeDAO;

    @Override
    public String saveBudgetOrderFee(BudgetOrder budgetOrder, String operator) {
        String code = null;
        if (budgetOrder != null && StringUtils.isNotBlank(operator)
                && EBudgetOrderFeeWay.TRANSFER.getCode()
                    .equals(budgetOrder.getServiceChargeWay())) {// 当手续费收取方式是转账时产生手续费
            BudgetOrderFee budgetOrderFee = new BudgetOrderFee();
            code = OrderNoGenerater
                .generate(EGeneratePrefix.BUDGET_ORDER_FEE.getCode());
            budgetOrderFee.setCode(code);
            budgetOrderFee.setEffect(EBoolean.YES.getCode());
            budgetOrderFee.setCompanyCode(budgetOrder.getCompanyCode());

            budgetOrderFee.setUserId(budgetOrder.getSaleUserId());
            // 如果GPS收费方式是转账，＋GPS收费；不是就不加。如果手续费收取方式是转账，+履约保证金+担保风险金+杂费，不是就不加
            Long totalFee = 0L;
            if (EBudgetOrderFeeWay.TRANSFER.getCode()
                .equals(budgetOrder.getGpsFeeWay())) {
                totalFee += budgetOrder.getGpsFee();
            }
            if (EBudgetOrderFeeWay.TRANSFER.getCode()
                .equals(budgetOrder.getServiceChargeWay())) {
                totalFee = budgetOrder.getLyAmount() + budgetOrder.getFxAmount()
                        + budgetOrder.getOtherFee();
            }
            budgetOrderFee.setShouldAmount(totalFee);
            budgetOrderFee.setRealAmount(0L);

            budgetOrderFee.setIsSettled(EBoolean.NO.getCode());
            budgetOrderFee.setUpdater(operator);
            budgetOrderFee.setUpdateDatetime(new Date());
            budgetOrderFee.setBudgetOrder(budgetOrder.getCode());
            budgetOrderFeeDAO.insert(budgetOrderFee);
        }
        return code;
    }

    @Override
    public void refreshBudgetOrderNoEffect(String budgetOrder) {
        BudgetOrderFee condition = new BudgetOrderFee();
        condition.setBudgetOrder(budgetOrder);
        List<BudgetOrderFee> list = queryBudgetOrderFeeList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            budgetOrderFeeDAO.updateNotEffect(list.get(0));
        }
    }

    @Override
    public List<BudgetOrderFee> queryBudgetOrderFeeList(
            BudgetOrderFee condition) {
        return budgetOrderFeeDAO.selectList(condition);
    }

    @Override
    public BudgetOrderFee getBudgetOrderFee(String code) {
        BudgetOrderFee data = null;
        if (StringUtils.isNotBlank(code)) {
            BudgetOrderFee condition = new BudgetOrderFee();
            condition.setCode(code);
            data = budgetOrderFeeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "手续费不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshBudgetOrderFee(BudgetOrderFee budgetOrderFee) {
        if (null != budgetOrderFee) {
            budgetOrderFeeDAO.updateBudgetOrderFeeRealAmount(budgetOrderFee);
        }
    }

    @Override
    public void updateShouldAmountAndIsSettled(BudgetOrderFee budgetOrderFee) {
        if (null != budgetOrderFee) {
            budgetOrderFeeDAO.updateShouldAmountAndIsSettled(budgetOrderFee);
        }

    }

    @Override
    public BudgetOrderFee getBudgetOrderFeeByBudgetOrder(String BudgetOrder) {
        BudgetOrderFee data = null;
        if (StringUtils.isNotBlank(BudgetOrder)) {
            BudgetOrderFee condition = new BudgetOrderFee();
            condition.setBudgetOrder(BudgetOrder);
            data = budgetOrderFeeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "手续费不存在");
            }
        }
        return data;
    }
}
