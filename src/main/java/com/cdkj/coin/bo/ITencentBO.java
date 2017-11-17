/**
 * @Title ITencentBO.java 
 * @Package com.cdkj.coin.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午4:36:01 
 * @version V1.0   
 */
package com.cdkj.coin.bo;

import com.cdkj.coin.dto.res.XN625000Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月16日 下午4:36:01 
 * @history:
 */
public interface ITencentBO {

    // 腾讯云注册
    public void register(String userId, String companyCode, String systemCode);

    // 获取签名信息
    public XN625000Res getSign(String userId, String companyCode,
            String systemCode);

}