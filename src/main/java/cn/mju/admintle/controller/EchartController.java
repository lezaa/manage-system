package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Echarts;
import cn.mju.admintle.domain.Job;
import cn.mju.admintle.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EchartController {

    private static final Logger log = LoggerFactory.getLogger(EchartController.class);

    private static final  ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AdminService adminService;

    @RequestMapping("/pie1")
    public List<Echarts> getDepts()  {
        ArrayList<Echarts> list = new ArrayList<>();
        List<Dept> depts = adminService.getDepts();
        List<Dept> relDpets = objectMapper.convertValue(depts, new TypeReference<List<Dept>>() {});
        for (Dept data : relDpets) {
            list.add(new Echarts(data.getDeptName(),data.getUsers().size()));
        }
        return list;
    }

    @RequestMapping("/pie2")
    public List<Echarts> getJobs()  {
        ArrayList<Echarts> list = new ArrayList<>();
        List<Job> jobs = adminService.getJobs();
        List<Job> relJobs = objectMapper.convertValue(jobs, new TypeReference<List<Job>>() {});
        for (Job data : relJobs) {
            list.add(new Echarts(data.getJobName(),data.getUsers().size()));
        }
        return list;
    }
}
