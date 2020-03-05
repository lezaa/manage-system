package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Leave;
import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.mapper.LeaveMapper;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.service.TimeService;
import cn.mju.admintle.vo.LeaveVo;
import cn.mju.admintle.vo.NoticeVo;
import cn.mju.admintle.vo.SignVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.mju.admintle.utils.AJAXUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;
    @Autowired
    private PubService pubService;

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @GetMapping("/sign/signs")
    public String getAllSigns(@RequestParam(defaultValue = "1", value = "pageNum1",required = false) Integer pageNum1,
                              @RequestParam(defaultValue = "1", value = "pageNum2",required = false) Integer pageNum2,
                              @RequestParam(defaultValue = "1", value = "pageNum3",required = false) Integer pageNum3,
                              Model model,HttpServletRequest request){
        int pageSize = 10;
        //所有签到记录
        PageInfo<Sign> pageInfo1 = timeService.getAllSigns(pageNum1, pageSize);
        List<SignVo> signVos1 = pubService.changeSignVo(pageInfo1);
        model.addAttribute("page1",pageInfo1);
        model.addAttribute("signs1",signVos1);
        //所有迟到的签到记录
        PageInfo<Sign> pageInfo2 = timeService.getAllLateSign(pageNum2, pageSize);
        List<SignVo> signVos2 = pubService.changeSignVo(pageInfo2);
        model.addAttribute("page2",pageInfo2);
        model.addAttribute("signs2",signVos2);
        //我的本月签到记录
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH)+1;
        User user = (User) request.getSession().getAttribute("user");
        Long userId = user.getId();
        PageInfo<Sign> pageInfo3 = timeService.getMonthSigns(pageNum3, pageSize, userId, month);
        List<SignVo> signVos3 = pubService.changeSignVo(pageInfo3);
        model.addAttribute("page3",pageInfo3);
        model.addAttribute("signs3",signVos3);

        return "time/signMain";
    }




    //全部请假
    @GetMapping("/leave/leaves")
    public String leaveManage(@RequestParam(defaultValue = "1", value = "pageNum",required = false) Integer pageNum,
                              @RequestParam(defaultValue = "1", value = "pageNum",required = false) Integer pageNum1,
                              Model model,HttpServletRequest request){
        int pageSize = 10;
        //管理端
        PageInfo<Leave> pageInfo = timeService.getAllLeaves(pageNum, pageSize);
        List<LeaveVo> leaveVos = pubService.changeLeaveVo(pageInfo);
        PageInfo<Leave> leaveByState = timeService.getLeaveByState(pageNum, pageSize, 0);
        model.addAttribute("leaves",leaveVos);
        model.addAttribute("page",pageInfo);
        model.addAttribute("unreadNum",leaveByState.getList().size());
        //员工端
        User user = (User) request.getSession().getAttribute("user");
        PageInfo<Leave> pageInfo1 = timeService.getOneleaves(pageNum1, pageSize, user.getId());
        List<LeaveVo> leaveVo1s = pubService.changeLeaveVo(pageInfo1);
        model.addAttribute("leave1s",leaveVo1s);
        model.addAttribute("page1",pageInfo1);

        return "time/leaveMain";
    }



    //尚未操作（新申请）
    @GetMapping("/unreadLeaves")
    public String unreadLeaves(@RequestParam(defaultValue = "1", value = "pageNum",required = false) Integer pageNum,
                               Model model){
        int pageSize = 10;
        PageInfo<Leave> pageInfo = timeService.getLeaveByState(pageNum, pageSize, 0);
        List<LeaveVo> leaveVos = pubService.changeLeaveVo(pageInfo);
        model.addAttribute("leaves",leaveVos);
        model.addAttribute("page",pageInfo);
        return "time/newLeaves";
    }

    //根据用户名字来搜索请假条
    @RequestMapping("/searchLeave")
    public String getSearchLeave(@RequestParam(defaultValue = "1", value = "pageNum2",required = true) Integer pageNum2,
                                 @RequestParam(value = "username",required = false) String username,Model model){
        int pageSize = 10;
        PageInfo<Leave> pageInfo = timeService.searchLeave(pageNum2, pageSize, username);
        List<LeaveVo> leaveVos = pubService.changeLeaveVo(pageInfo);
        model.addAttribute("username",username);
        model.addAttribute("page2",pageInfo);
        model.addAttribute("leaves",leaveVos);
        return "time/leaveMain";
    }


    @RequestMapping("/read/{id}")
    public String read(@PathVariable("id") Integer id,Model model){

        LeaveVo leaveVo = timeService.getLeave(id);

        model.addAttribute("leave",leaveVo);
        return "time/readLeave";
    }

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

    @GetMapping("/toApply")
    public String toAdd(){
        return "time/addLeave";
    }


    //申请请假
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> applyLeave(HttpSession session, @Validated Leave leave, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (leave.getReason().equals("") || leave.getReason().length()<5){
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", "false");
            return resultMap;
        }
        User user = (User) session.getAttribute("user");
        boolean flag = timeService.applyLeave(user.getId(),leave.getReason(),leave.getBeginTime(),leave.getEndTime());
        return AJAXUtil.getReturn(flag);

    }

    //操作申请
    @RequestMapping(value = "/approval", method = RequestMethod.POST)
    @ResponseBody
    public void Approval(Integer id,Integer state) throws JsonProcessingException {
        boolean flag = timeService.approvalLeave(id,state);
        Map<String,Object> map = new HashMap<String,Object>();
        if (flag){
            map.put("result",true);
        }else{
            map.put("result",false);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(map);

    }



}



