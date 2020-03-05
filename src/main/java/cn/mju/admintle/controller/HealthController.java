package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Applicant;
import cn.mju.admintle.domain.Health;
import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.service.HealthService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.utils.AJAXUtil;
import cn.mju.admintle.vo.ApplicantVo;
import cn.mju.admintle.vo.HealthVo;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;
    @Autowired
    private PubService pubService;

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);



    @GetMapping("/healths")
    public String dayList(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum,
                          @RequestParam(value = "date",required = false)Date date,Model model){
        int pageSize = 10;
        PageInfo<Health>  pageInfo;
        Map<String, Object> data;
        if (date == null){
            pageInfo= healthService.getListByDate(pageNum, pageSize,new Date());
            data= healthService.getDayData(new Date());
        }else {
            pageInfo= healthService.getListByDate(pageNum, pageSize,date);
            data= healthService.getDayData(date);
        }
        List<HealthVo> healthVos = pubService.changeHealthVo(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("healths",healthVos);
        model.addAttribute("params",data);
        return "health/healthMain";

    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "health/addHealth";
    }

    @RequestMapping("/addHealth")
    @ResponseBody
    public Map<String,Object> add(@Validated Health health, BindingResult bindingResult,HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        User user = (User)request.getSession().getAttribute("user");
        health.setUserId(user.getId());
        health.setToday(new Date());
        boolean flag = healthService.addHealth(health);
        Map<String, Object> resultMap = AJAXUtil.getReturn(flag);
        return resultMap;
    }


    // 验证用户是否今日已填写健康表
    @RequestMapping(value = "/checkHealth", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkHealth(HttpSession session) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        Health oneToday = healthService.getOneToday(user.getId());

        if (oneToday == null) {
            resultMap.put("result", "false");
        } else {
            resultMap.put("result", "true");
        }
        return resultMap;
    }


}
