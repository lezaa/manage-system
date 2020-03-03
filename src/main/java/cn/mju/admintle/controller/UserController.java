package cn.mju.admintle.controller;

import cn.mju.admintle.domain.User;
import cn.mju.admintle.mapper.RoleMapper;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.service.UserService;
import cn.mju.admintle.utils.AJAXUtil;
import cn.mju.admintle.utils.RandomValidateCode;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    //登录
    @RequestMapping("/login")
    public String login( @RequestParam("role") String role,User user, Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String sysCode = (String) session.getAttribute("randomcode_key");
        String formCode = request.getParameter("verifycode");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if (user.getUsername() !=null && !user.getUsername().equals("")) {
            User userByName = userService.findUserByName(user.getUsername());

            if (userByName != null) {
                String roleName = userService.getRoleName(userByName.getId());
                if (role.equals(roleName) && role != null) {

                    if (sysCode.equalsIgnoreCase(formCode) && formCode != null) {
                        try {
                            subject.login(token);
                            session.setAttribute("user", userByName);
                            session.setAttribute("role", roleName);
                            return "redirect:/main";
                        } catch (UnknownAccountException e) {
                            model.addAttribute("msg", "用户名错误");
                            return "forward:/user/toLogin";
                        } catch (IncorrectCredentialsException e) {
                            model.addAttribute("msg", "密码错误");
                            return "forward:/user/toLogin";
                        }
                    } else {
                        model.addAttribute("msg", "验证码错误,请重新输入");
                        return "forward:/user/toLogin";
                    }
                } else {
                    model.addAttribute("msg", "请选择正确角色");
                    return "forward:/user/toLogin";
                }
            }else {
                model.addAttribute("msg", "用户名不存在");
                return "forward:/user/toLogin";
            }
            } else {
                model.addAttribute("msg", "用户名不允许为空");
                return "forward:/user/toLogin";
            }

    }

    //未授权
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "noauth";
    }

    //登出
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/user/login";
    }

    //验证码接口
    @RequestMapping(value="/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/toPassword")
    public String toPass() {
        return "emp/password";
    }


    @RequestMapping("/password")
    @ResponseBody
    public Map<String,Object> pass(@RequestParam(value = "password",required = false) String password,HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (password.equals("") || password.length()<3 ||password.length()>15){
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", "false");
            return resultMap;
        }
        user.setPassword(password);
        boolean flag = adminService.updatePass(user);
        Map<String, Object> resultMap = AJAXUtil.getReturn(flag);
        return resultMap;
    }

}
