package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.mapper.DeptMapper;
import cn.mju.admintle.mapper.JobMapper;
import cn.mju.admintle.mapper.RoleMapper;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.service.UserService;
import cn.mju.admintle.vo.UserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptMapper deptMapper;


    @Autowired
    private AdminService adminService;

    @Autowired
    private PubService pubService;


    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @GetMapping("/emps")
    public String empsList(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model){

        int pageSize = 10;
        PageInfo<User> pageInfo = userService.getUsersByPage(pageNum,pageSize);
        List<UserVo> userVos = pubService.changeVo(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("users",userVos);

        return "emp/empsList";

    }

    @GetMapping("/emp")
    public String searchEmp(String username,String deptName,String jobName,@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum,Model model){
        int pageSize = 10;
        PageInfo<User> pageInfo = adminService.getUserByCondition(username,deptName,jobName,pageNum,pageSize);
        List<UserVo> userVos = pubService.changeVo(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("users",userVos);
        return "emp/empsList";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "emp/addEmp";
    }

    @RequestMapping("/addEmp")
    public String addEmp(@Validated User user, Model model, BindingResult bindingResult, String roleName){
        if (bindingResult.hasErrors()) {

            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Dept dept = deptMapper.getDeptById(user.getDeptId());
        if (dept != null){
            User dbUser = pubService.passwordToMD5(user);

            boolean flag = adminService.addUser(dbUser, roleName);
            if (flag){
                model.addAttribute("addMsg","添加成功！");
                return "emp/addEmp";
            }else{
                model.addAttribute("addMsg","添加失败,请重试！");
                return "emp/addEmp";
            }
        }else{
            model.addAttribute("addMsg", "该部门不存在！");
            return "emp/addEmp";
        }


    }

    @RequestMapping("/getEmp/{id}")
    public String getEmp(@PathVariable("id") long id ,Model model){
        User user = userService.findUserById(id);
        String roleName = userService.getRoleName(id);
        model.addAttribute("user",user);
        model.addAttribute("roleName",roleName);
        return "emp/updateEmp";
    }

    @RequestMapping("/update")
    public String updateEmp(Model model,String roleName,User user ,HttpServletRequest request){
        Dept dept = deptMapper.getDeptById(user.getDeptId());
        if (dept != null){
            boolean flag = adminService.update(user,roleName);
            if (flag){
                model.addAttribute("updateMsg","修改成功！");
                return "emp/updateEmp";
            }else{

               model.addAttribute("updateMsg","修改失败,请重试！");
                return "emp/updateEmp";
            }
        }else{
            model.addAttribute("updateMsg", "该部门不存在！");
            model.addAttribute("roleName",roleName);
            return "emp/updateEmp";
        }

    }

    @RequestMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Long id){
        boolean flag = adminService.delete(id);
        return "redirect:/emp/emps";

    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestParam("check")Long[] ids){
        boolean flag = adminService.delteBatch(ids);
        return "redirect:/emp/emps";

    }



    /*下载excel*/
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        String[] headers = { "ID", "姓名", "地址", "出生日期","邮箱","手机","部门","职位","状态（在职1/离职0）"};

        List<UserVo> dataset = adminService.downloadUser();


        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //遍历集合数据，产生数据行
        Iterator it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            UserVo t = (UserVo) it.next();
            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    String textValue = null;


                    if (value instanceof Date)
                    {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        textValue = sdf.format(date);
                    }
                    else
                    {
                        if (value !=null){//其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }

                    }

                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    HSSFFont font3 = workbook.createFont();
                    font3.setColor(IndexedColors.BLUE.getIndex());//定义Excel数据颜色
                    richString.applyFont(font3);
                    cell.setCellValue(richString);

                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=users.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/userCheck")
    public void regNameCheck(@RequestParam("username") String username,HttpServletResponse response) throws IOException {

        Map<String,Object> map = new HashMap<String,Object>();
        if (!username.equals("") && username != null) {
            boolean flag = userService.judUserByName(username);

            if (flag) {
                map.put("userExsit", true);
                map.put("msg", "此用户名已存在");

            } else {
                map.put("userExsit", false);
                map.put("msg", "此用户名可用");

            }
        }else{
            map.put("userExsit", true);
            map.put("msg", "此用户名不可用");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }






}
