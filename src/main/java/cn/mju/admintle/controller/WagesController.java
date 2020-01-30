package cn.mju.admintle.controller;

import cn.mju.admintle.domain.User;
import cn.mju.admintle.domain.Wages;
import cn.mju.admintle.dto.WagesDto;
import cn.mju.admintle.mapper.WagesMapper;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.service.UserService;
import cn.mju.admintle.service.WagesService;
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
@RequestMapping("/wages")
public class WagesController {
    @Autowired
    private WagesService wagesService;
    @Autowired
    private PubService pubService;
    @Autowired
    private UserService userService;
    @Autowired
    private WagesMapper wagesMapper;

    private static final Logger log = LoggerFactory.getLogger(WagesController.class);

    @GetMapping("/list")
    public String getAll(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model,HttpServletRequest request){
        int pageSize = 10;
        PageInfo<Wages> pageInfo = wagesService.getAll(pageNum, pageSize);
        List<WagesDto> wagesDtos = pubService.changeWagesDto(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("wages",wagesDtos);
        User user = (User) request.getSession().getAttribute("user");
        List<WagesDto> self = wagesService.getSelf(user.getId());
        model.addAttribute("self",self);
        return "wages/wagesList";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model,@RequestParam("username") String username){
        int pageSize = 10;
        PageInfo<Wages> pageInfo = wagesService.findWagesByUserName(username,pageNum, pageSize);
        List<WagesDto> wagesDtos = pubService.changeWagesDto(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("wages",wagesDtos);
        return "wages/wagesList";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "wages/addWages";
    }

    @RequestMapping("/add")
    public String add(@Validated WagesDto wagesDto, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        boolean flag = wagesService.insertWages(wagesDto);
        if (flag){
            model.addAttribute("addMsg","添加工资信息成功！");
            return "wages/addWages";
        }else {
            model.addAttribute("addMsg","添加工资信息失败！");
            return "wages/addWages";
        }
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        wagesMapper.deleteWages(id);
        return "redirect:/wages/list";

    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestParam("check")Long[] ids){
        wagesService.deleteBatch(ids);
        return "redirect:/wages/list";
    }

    /*下载excel*/
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        String[] headers = {"编号", "姓名", "部门", "职位", "基本工资","交通房补","加班补贴","奖金","五险一金扣款","缺勤扣款","迟到扣款","实际工资","签发日期"};

        List<WagesDto> dataset = wagesService.downloadWages();


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
            WagesDto t = (WagesDto) it.next();
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
        response.setHeader("Content-disposition", "attachment;filename=wages.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/userCheck")
    public void regNameCheck(@RequestParam("username") String username,HttpServletResponse response) throws IOException {

        Map<String,Object> map = new HashMap<String,Object>();
            boolean flag = userService.judUserByName(username);

            if (flag) {
                map.put("userExsit", true);
                map.put("msg", "此用户存在");

            } else {
                map.put("userExsit", false);
                map.put("msg", "此用户不存在！");

            }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

}
