package cn.mju.admintle.controller;


import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//对部门的增改查
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private AdminService adminService;


    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @GetMapping("/depts")
    public String getDepts(Model model){
        List<Dept> depts = adminService.getDepts();
        model.addAttribute("depts",depts);
        return "dept/deptList";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "dept/addDept";
    }

    @RequestMapping("/add")
    public String addDept(Model model,@Validated Dept dept, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        boolean flag = adminService.addDept(dept);
        if (flag){
            model.addAttribute("addMsg","添加部门成功！");
            return "dept/addDept";
        }else{
            model.addAttribute("addMsg","添加部门失败！");
            return "dept/addDept";
        }

    }

    @GetMapping("/dept/{id}")
    public String getDept(@PathVariable("id")Integer id, Model model){
        Dept deptAndUsers = adminService.getDeptAndUsers(id);
        model.addAttribute("dept",deptAndUsers);
        return "dept/updateDept";
    }

    @RequestMapping("/update")
    public String updateDept(Model model,Dept dept){
        boolean flag = adminService.updateDept(dept);
        if (flag){
            model.addAttribute("updateMsg","修改部门信息成功！");
            model.addAttribute("dept",dept);
            return "dept/updateDept";
        }else{
            model.addAttribute("updateMsg","修改部门信息失败！");
            return "dept/updateDept";
        }


    }


}
