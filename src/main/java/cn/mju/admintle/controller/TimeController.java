package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cn.mju.admintle.utils.AJAXUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    // 验证用户是否签到
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signIn(HttpSession session) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");

        Sign sign = timeService.getSign(user.getId());
        if (sign == null) {

            resultMap.put("result", "false");
        } else {
            resultMap.put("result", "true");
        }
        return resultMap;
    }


    // 签到
    @RequestMapping(value = "/toSignIn", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toSignIn(HttpSession session) {

        User user = (User) session.getAttribute("user");

        boolean flag = timeService.addSign(user.getId());
        Map<String, Object> resultMap = AJAXUtil.getReturn(flag);
        return resultMap;

    }

    //申请请假
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String applyLeave(HttpSession session, Date beginTime,Date endTime,String reason,Model model) {
        User user = (User) session.getAttribute("user");

        boolean flag = timeService.applyLeave(user.getId(),reason,beginTime,endTime);
        if (flag){
            model.addAttribute("addMsg","请假请求发送成功，等待人事审核!");
            return "time/addLeave";
        }else{
            model.addAttribute("addMsg","请假请求发送失败，请重试!");
            return "time/addLeave";
        }

    }


    //批准申请
    @RequestMapping(value = "/approval/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> Approval(@PathVariable("id")Integer id) {
        boolean flag = timeService.approvalLeave(id);
        Map<String, Object> resultMap = AJAXUtil.getReturn(flag);
        return resultMap;
    }


}



