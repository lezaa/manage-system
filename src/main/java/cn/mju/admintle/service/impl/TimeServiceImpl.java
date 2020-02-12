package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.Leave;
import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.mapper.LeaveMapper;
import cn.mju.admintle.mapper.SignMapper;
import cn.mju.admintle.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TimeServiceImpl implements TimeService {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<Sign> getAllSigns() {
        List<Sign> all = signMapper.getAll();
        return all;
    }

    @Override
    public Sign getOne(int id) {
        Sign one = signMapper.getOne(id);
        return one;
    }

    @Override
    public List<Sign> getOneList(long userId) {

        List<Sign> one = signMapper.getOneList(userId);
        return one;
    }

    @Override
    public Sign getSign(long userId) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sign sign = signMapper.getSign(userId, time);
        return sign;
    }

    @Override
    public boolean addSign(long userId) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sign sign = new Sign();
        sign.setUserId(userId);
        sign.setTime(time);
        boolean flag = signMapper.insertSign(sign) >0;
        return flag;
    }

    @Override
    public boolean applyLeave(long userId, String reason, Date beginTime, Date endTime) {
        Leave leave = new Leave();
        leave.setUserId(userId);
        leave.setReason(reason);
        leave.setBeginTime(beginTime);
        leave.setEndTime(endTime);
        leave.setState(0);
        boolean flag = leaveMapper.insertLeave(leave) >0;
        return flag;
    }

    @Override
    public boolean approvalLeave(int id) {
        Leave one = leaveMapper.getOne(id);
        one.setState(1);
        boolean flag = leaveMapper.updateLeave(one) >0;
        return flag;
    }


}
