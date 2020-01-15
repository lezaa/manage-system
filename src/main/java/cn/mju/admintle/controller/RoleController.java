package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Role;
import cn.mju.admintle.dto.RoleDto;
import cn.mju.admintle.mapper.RoleMapper;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PubService pubService;

    @Autowired
    public RoleMapper roleMapper;

    @GetMapping("/list")
    public String getRoleByName(@RequestParam(defaultValue = "1", value = "pageNum1",required = false) Integer pageNum1,
                                @RequestParam(defaultValue = "1", value = "pageNum2",required = false) Integer pageNum2,
                                Model model){
        int pageSize = 10;
        PageInfo<Role> adminPage = adminService.getRolesByName(pageNum1,pageSize,"admin");
        List<RoleDto> roleDtos = pubService.changeRoleDto(adminPage);
        PageInfo<Role> userPage = adminService.getRolesByName(pageNum2, pageSize, "user");
        List<RoleDto> roleDtos1 = pubService.changeRoleDto(userPage);
        model.addAttribute("adminList",roleDtos);
        model.addAttribute("userList",roleDtos1);
        model.addAttribute("adminPage",adminPage);
        model.addAttribute("userPage",userPage);
        return "role/roleList";
    }


    @RequestMapping("/change")
    public String changeRole(@RequestParam("id")Long id,@RequestParam("roleName") String roleName){
        Role role = roleMapper.getRole(id);
        role.setRoleName(roleName);
        roleMapper.updateRole(role);
        return "redirect:/role/list";
    }
}
