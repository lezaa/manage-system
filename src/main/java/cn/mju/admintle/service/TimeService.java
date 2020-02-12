package cn.mju.admintle.service;

import cn.mju.admintle.domain.Sign;

import java.util.Date;
import java.util.List;

public interface TimeService {

    List<Sign> getAllSigns();

    Sign getOne(int id);

    List<Sign> getOneList(long userId);

    Sign getSign(long userId);

    boolean addSign(long userId);

    boolean applyLeave(long userId, String reason, Date beginTime,Date endTime);

    boolean approvalLeave(int id);


}
