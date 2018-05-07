package com.cdkj.loan.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.loan.ao.ICUserAO;
import com.cdkj.loan.bo.ICUserBO;
import com.cdkj.loan.bo.ISmsOutBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.MD5Util;
import com.cdkj.loan.domain.CUser;
import com.cdkj.loan.dto.req.XN630200Req;
import com.cdkj.loan.enums.EUserStatus;
import com.cdkj.loan.exception.BizException;

@Service
public class CUserAOImpl implements ICUserAO {

    @Autowired
    private ICUserBO cUserBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    public XN630200Req doRegister(String mobile, String loginPwd,
            String smsCaptcha) {
        String userId = cUserBO.doRegister(mobile, loginPwd, smsCaptcha);
        return new XN630200Req(userId);
    }

    // 验证手机号

    @Override
    public void doCheckMobile(String mobile) {
        // cUserBO.isMobileExist(mobile);
    }

    @Override
    public String doCaptchaLoginReg(String mobile, String smsCaptcha,
            String loginPwd, String confirmPwd) {
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "630080");
        String userId = cUserBO.getUserIdByMobile(mobile);
        if (StringUtils.isNotBlank(userId)) {
            throw new BizException("mag", "手机号已存在，请重新输入！！！");
        }
        if (!loginPwd.equals(confirmPwd)) {
            throw new BizException("mag", "两次密码不一致，请重新输入！！！");
        }
        userId = cUserBO.saveUser(mobile);
        return userId;
    }

    // 用户登录
    @Override
    @Transactional
    public String doLogin(String loginName, String loginPwd) {
        CUser condition = new CUser();
        // if (EUserKind.Customer.getCode().equals(kind)
        // || EUserKind.Merchant.getCode().equals(kind)) {
        // condition.setLoginName(loginName);
        // condition.setLoginType(ELoginType.MOBILE.getCode());
        // } else {
        condition.setLoginName(loginName);
        // }
        List<CUser> userList1 = cUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<CUser> userList2 = cUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        CUser cuser = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(cuser.getStatus())) {
            throw new BizException("xn805050",
                "该账号" + EUserStatus.getMap().get(cuser.getStatus()).getValue()
                        + "，请联系工作人员");
        }
        // addLoginAmount(cuser);
        // cUserBO.refreshLastLogin(cuser.getUserId());
        return cuser.getUserId();
    }

    // 登录名密码验证
    // @Override
    // public void doCheckLoginPwd(String loginName, String loginPwd) {
    // CUser condition = new CUser();
    // condition.setLoginName(loginName);
    // List<CUser> userList1 = cUserBO.queryUserList(condition);
    // if (CollectionUtils.isEmpty(userList1)) {
    // throw new BizException("xn702002", "用户不存在");
    // }
    // condition.setLoginPwd(MD5Util.md5(loginPwd));
    // List<CUser> userList2 = cUserBO.queryUserList(condition);
    // if (CollectionUtils.isEmpty(userList2)) {
    // throw new BizException("xn702002", "登录密码错误");
    // }
    //
    // }

    @Override
    public String insertCUser(CUser data) {
        return cUserBO.saveCNavigate(data);
    }

    @Override
    public Paginable<CUser> queryCUserPage(int start, int limit,
            CUser condition) {
        return cUserBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CUser> queryCUserList(CUser condition) {
        return cUserBO.queryUserList(condition);
    }

    @Override
    public CUser getCUser(String userId) {
        return cUserBO.getUser(userId);
    }

}