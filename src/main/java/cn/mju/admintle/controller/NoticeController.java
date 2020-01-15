package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Notice;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.vo.NoticeVo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PubService pubService;

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
    public String search(@RequestParam(defaultValue = "1", value = "pageNum",required = false) Integer pageNum,
                         Model model,@RequestParam("head") String head){
        int pageSize = 10;
        PageInfo<Notice> noticePageInfo = adminService.searchNotice(head,pageNum,pageSize);
        List<NoticeVo> noticeVos = pubService.changeNoticeVo(noticePageInfo);
        model.addAttribute("notices",noticeVos);
        model.addAttribute("page",noticePageInfo);
        return "notice/noticeList";
    }


    @GetMapping("/toAdd")
    public String toAdd(){
        return "notice/addNotice";
    }

    @PostMapping("/publish")
    public String publish(Notice notice, Model model, HttpServletRequest request){
        boolean flag = adminService.publishNotice(notice,request);
        if (flag){
            model.addAttribute("addMsg","发布公告成功！");
            return "notice/addNotice";
        }else {
            model.addAttribute("faddMsg","发布公告失败！");
            return "notice/addNotice";
        }

    }

    @RequestMapping("/getNotice/{id}")
    public String getNotice(@PathVariable("id") int id ,Model model){
        NoticeVo notice = adminService.findNotice(id);
        model.addAttribute("notice",notice);
        return "notice/updateNotice";
    }


    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestParam("check")Long[] ids){
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
