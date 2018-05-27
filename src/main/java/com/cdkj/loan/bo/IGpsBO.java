package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.Gps;

public interface IGpsBO extends IPaginableBO<Gps> {

    public void saveGps(Gps data);

    public void applyGps(Gps code);

    public void refreshUseGps(Gps data);

    public List<Gps> queryGpsList(Gps condition);

    public Gps getGps(String code);

}