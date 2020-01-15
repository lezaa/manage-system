package cn.mju.admintle.controller;


import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.mapper.DeptMapper;
import cn.mju.admintle.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//对部门的增改查
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private DeptMapper deptMapper;

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
    public String addDept(Model model,Dept dept){
        boolean flag = adminService.addDept(dept);
        if (flag){
            return "redirect:/dept/depts";
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
            return "redirect:/dept/depts";
        }else{
            model.addAttribute("updateMsg","修改部门失败！");
            return "dept/updateDept";
        }


    }
}
