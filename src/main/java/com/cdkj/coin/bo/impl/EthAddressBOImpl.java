package com.cdkj.coin.bo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.RandomUtil;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.IEthAddressDAO;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.enums.EEthAddressStatus;
import com.cdkj.coin.enums.EEthAddressType;
import com.cdkj.coin.eth.AdminClient;
import com.cdkj.coin.exception.BizException;

@Component
public class EthAddressBOImpl extends PaginableBOImpl<EthAddress> implements
        IEthAddressBO {

    private static Logger logger = Logger.getLogger(EthAddressBOImpl.class);

    private static Admin selfAdmin = AdminClient.getSelfClient();

    private static Admin thirdAdmin = AdminClient.getThirdClient();

    @Autowired
    private IEthAddressDAO ethAddressDAO;

    @Override
    public String generateAddress(EEthAddressType type, String ethAccountName,
            String userId, Date availableDatetimeStart,
            Date availableDatetimeEnd) {
        String address = null;
        String password = RandomUtil.generate8();
        try {
            NewAccountIdentifier newAccountIdentifier = selfAdmin
                .personalNewAccount(password).send();
            if (newAccountIdentifier != null) {
                address = newAccountIdentifier.getAccountId();
            }
        } catch (IOException e) {
            throw new BizException("xn625000", "以太坊账户创建失败，请检查节点是否正常！原因："
                    + e.getMessage());
        }
        if (StringUtils.isBlank(address)) {
            throw new BizException("xn625000", "以太坊账户创建失败，请检查节点是否正常！");
        }
        logger.info("以太坊账户创建成功:" + address);
        try {
            // 获取keystore文件
            File keystoreFile = null;
            String fileDirPath = PropertiesUtil.Config.KEY_STORE_PATH;
            File keyStoreFileDir = new File(fileDirPath);
            File[] subFiles = keyStoreFileDir.listFiles();

            for (File file : subFiles) {
                if (file.isDirectory() != true) {
                    // from: 0x244eb6078add0d58b2490ae53976d80f54a404ae
                    if (file.getName().endsWith(address.substring(2))) {
                        // 找到了该文件
                        keystoreFile = file;
                        break;
                    }
                }
            }

            if (keystoreFile == null) {
                throw new BizException("xn6250000", "未找到keystore文件，请仔细检查本地节点配置");
            }

            FileInputStream fis = new FileInputStream(keystoreFile);
            byte[] buf = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while ((fis.read(buf)) != -1) {
                sb.append(new String(buf));
                buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
            }
            String keystoreContent = new String(sb.toString().getBytes(),
                "utf-8");
            fis.close();
            // 落地地址信息
            this.saveEthAddress(type, userId, address, type.getValue(),
                password, BigDecimal.ZERO, availableDatetimeStart,
                availableDatetimeEnd, EEthAddressStatus.NORMAL,
                keystoreFile.getName(), keystoreContent);
        } catch (Exception e) {
            throw new BizException("xn6250000", "获取keystore文件异常，原因："
                    + e.getMessage());
        }

        return address;
    }

    @Override
    public String saveEthAddress(EEthAddressType type, String userId,
            String address, String label, String password, BigDecimal balance,
            Date availableDatetimeStart, Date availableDatetimeEnd,
            EEthAddressStatus status, String keystoreName,
            String keystoreContent) {
        String code = OrderNoGenerater.generate("ETH");
        Date now = new Date();
        EthAddress data = new EthAddress();
        data.setCode(code);
        data.setType(type.getCode());
        data.setAddress(address);
        data.setLabel(label);
        data.setPassword(password);
        data.setUserId(userId);
        data.setInitialBalance(getEthBalance(address));
        data.setBalance(balance);
        data.setAvailableDatetimeStart(availableDatetimeStart);
        data.setAvailableDatetimeEnd(availableDatetimeEnd);
        data.setStatus(status.getCode());
        data.setCreateDatetime(now);
        data.setUpdateDatetime(now);
        data.setKeystoreName(keystoreName);
        data.setKeystoreContent(keystoreContent);
        ethAddressDAO.insert(data);
        return code;
    }

    @Override
    public List<EthAddress> queryEthAddressList(EthAddress condition) {
        return ethAddressDAO.selectList(condition);
    }

    @Override
    public EthAddress getMEthAddressToday() {
        EthAddress condition = new EthAddress();
        condition.setType(EEthAddressType.M.getCode());
        condition.setStatus(EEthAddressStatus.NORMAL.getCode());
        condition.setToday(new Date());
        List<EthAddress> mList = ethAddressDAO.selectList(condition);
        if (CollectionUtils.isEmpty(mList)) {
            throw new BizException("xn625000", "未找到今日可用的散取地址");
        }
        return mList.get(0);
    }

    @Override
    public EthAddress getWEthAddressToday() {
        EthAddress condition = new EthAddress();
        condition.setType(EEthAddressType.W.getCode());
        condition.setStatus(EEthAddressStatus.NORMAL.getCode());
        condition.setToday(new Date());
        List<EthAddress> wList = ethAddressDAO.selectList(condition);
        if (CollectionUtils.isEmpty(wList)) {
            throw new BizException("xn625000", "未找到今日可用的归集地址");
        }
        return wList.get(0);
    }

    @Override
    public EthAddress getEthAddress(String code) {
        EthAddress data = null;
        if (StringUtils.isNotBlank(code)) {
            EthAddress condition = new EthAddress();
            condition.setCode(code);
            data = ethAddressDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "以太坊地址不存在");
            }
        }
        return data;
    }

    @Override
    public EthAddress getEthAddressSecret(String code) {
        EthAddress data = null;
        if (StringUtils.isNotBlank(code)) {
            EthAddress condition = new EthAddress();
            condition.setCode(code);
            data = ethAddressDAO.selectSecret(condition);
            if (data == null) {
                throw new BizException("xn0000", "以太坊地址不存在");
            }
        }
        return data;
    }

    @Override
    public EthAddress getEthAddress(EEthAddressType type, String address) {
        EthAddress data = null;
        EthAddress condition = new EthAddress();
        condition.setType(type.getCode());
        condition.setAddress(address);
        List<EthAddress> results = ethAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public EthAddress getEthAddressByUserId(String userId) {
        EthAddress data = null;
        EthAddress condition = new EthAddress();
        condition.setUserId(userId);
        List<EthAddress> results = ethAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public BigDecimal getEthBalance(String address) {
        try {
            DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.LATEST;
            EthGetBalance ethGetBalance = thirdAdmin.ethGetBalance(address,
                defaultBlockParameter).send();
            if (ethGetBalance != null) {
                return new BigDecimal(ethGetBalance.getBalance().toString());
            } else {
                throw new BizException("xn625000", "以太坊余额查询失败");
            }
        } catch (Exception e) {
            throw new BizException("xn625000", "以太坊余额查询异常，原因：" + e.getMessage());
        }
    }

    @Override
    public int abandonAddress(EthAddress ethAddress) {
        int count = 0;
        if (ethAddress != null) {
            Date now = new Date();
            ethAddress.setStatus(EEthAddressStatus.INVALID.getCode());
            ethAddress.setAbandonDatetime(now);
            ethAddress.setUpdateDatetime(now);
            ethAddressDAO.updateAbandon(ethAddress);
        }
        return count;
    }

    @Override
    public int refreshBalance(EthAddress address) {
        int count = 0;
        if (address != null) {
            address.setBalance(getEthBalance(address.getAddress()));
            address.setUpdateDatetime(new Date());
            ethAddressDAO.updateBalance(address);
        }
        return count;
    }

    @Override
    public boolean isEthAddressExist(String address) {
        boolean flag = false;
        if (StringUtils.isNotBlank(address)) {
            EthAddress condition = new EthAddress();
            condition.setAddress(address);
            if (ethAddressDAO.selectTotalCount(condition) > 0) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public BigDecimal getTotalBalance(EEthAddressType type) {
        EthAddress condition = new EthAddress();
        condition.setType(type.getCode());
        return ethAddressDAO.selectTotalBalance(condition);
    }

}
