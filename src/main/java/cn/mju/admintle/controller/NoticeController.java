package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Notice;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.utils.AJAXUtil;
import cn.mju.admintle.vo.NoticeVo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PubService pubService;

    private static final Logger log = LoggerFactory.getLogger(NoticeController.class);

    @GetMapping("/notices")
    public String noticeList(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model){
        int pageSize = 10;
        PageInfo<Notice> allNotice = adminService.getAllNotice(pageNum,pageSize);
        List<NoticeVo> noticeVos = pubService.changeNoticeVo(allNotice);
        model.addAttribute("notices",noticeVos);
        model.addAttribute("page",allNotice);
        return "notice/noticeList";
    }

    @GetMapping("/notice")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum2",required = true) Integer pageNum2,
                         Model model,@RequestParam(value = "head",required = false) String head){
        int pageSize = 10;
        PageInfo<Notice> noticePageInfo = adminService.searchNotice(head,pageNum2,pageSize);
        List<NoticeVo> noticeVos = pubService.changeNoticeVo(noticePageInfo);
        model.addAttribute("head",head);
        model.addAttribute("notices",noticeVos);
        model.addAttribute("page2",noticePageInfo);
        return "notice/noticeList";
    }


    @GetMapping("/toAdd")
    public String toAdd(){
        return "notice/addNotice";
    }

    @PostMapping("/publish")
    @ResponseBody
    public Map<String,Object> publish(@Validated Notice notice, Model model, HttpServletRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        boolean flag = adminService.publishNotice(notice,request);
        Map<String, Object> resultMap = AJAXUtil.getReturn(flag);
        return resultMap;

    }

    @RequestMapping("/getNotice/{id}")
    public String getNotice(@PathVariable("id") int id ,Model model){
        NoticeVo notice = adminService.findNotice(id);
        model.addAttribute("notice",notice);
        return "notice/updateNotice";
    }


    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestParam("check")Integer[] ids){
        boolean b = adminService.delteBatchNotice(ids);
        return "redirect:/notice/notices";
    }

    @RequestMapping("/read/{id}")
    public String read(@PathVariable("id") Integer id,Model model){
        NoticeVo noticeVo = adminService.findNotice(id);
        model.addAttribute("notice",noticeVo);
        return "notice/readNotice";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        boolean flag = adminService.deleteNotice(id);
        return "redirect:/notice/notices";

    }
}
