package cn.mju.admintle.controller;


import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.utils.AJAXUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> addDept(@Validated Dept dept, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        boolean flag = adminService.addDept(dept);
        Map<String, Object> data = AJAXUtil.getReturn(flag);
        return data;

    }

    @GetMapping("/dept/{id}")
    public String getDept(@PathVariable("id")Integer id, Model model){
        Dept deptAndUsers = adminService.getDeptAndUsers(id);
        model.addAttribute("dept",deptAndUsers);
        return "dept/updateDept";
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateDept(@Validated Dept dept){
        boolean flag = adminService.updateDept(dept);
        Map<String, Object> data = AJAXUtil.getReturn(flag);
        return data;

    }

    @RequestMapping("/deptCheck")
    public void regNameCheck(@RequestParam("deptId") Integer deptId, HttpServletResponse response) throws IOException {

        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = adminService.checkDept(deptId);
        if (flag) {
                map.put("deptExsit", true);
                map.put("msg", "此部门ID可用");

            } else {
                map.put("userExsit", false);
                map.put("msg", "此部门不存在,请重新填写部门ID");

            }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }


}
