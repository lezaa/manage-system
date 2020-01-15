package cn.mju.admintle.controller;

import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/main")
    public String main(Model model){
        NoticeVo noticeVo = adminService.getLatest();
        model.addAttribute("notice",noticeVo);
        return "main";
    }

}
