package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Echarts;
import cn.mju.admintle.domain.Job;
import cn.mju.admintle.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EchartController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/pie1")
    public List<Echarts> getDepts(){
        ArrayList<Echarts> list = new ArrayList<>();
        List<Dept> deptData = adminService.getDeptData();
        for (Dept data : deptData) {
            list.add(new Echarts(data.getDeptName(),data.getUsers().size()));
        }
        return list;
    }

    @RequestMapping("/pie2")
    public List<Echarts> getJobs(){
        ArrayList<Echarts> list = new ArrayList<>();
        List<Job> jobData = adminService.getJobData();
        for (Job data : jobData) {
            list.add(new Echarts(data.getJobName(),data.getUsers().size()));
        }
        return list;
    }
}
