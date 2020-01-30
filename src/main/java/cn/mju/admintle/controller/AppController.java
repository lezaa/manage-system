package cn.mju.admintle.controller;

import cn.mju.admintle.domain.Applicant;
import cn.mju.admintle.domain.Dept;
import cn.mju.admintle.domain.Notice;
import cn.mju.admintle.domain.User;
import cn.mju.admintle.mapper.ApplicantMapper;
import cn.mju.admintle.service.ApplicantService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.vo.ApplicantVo;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private PubService pubService;
    @Autowired
    private ApplicantMapper applicantMapper;
    //简历存储路径
    String  filePath = "F:/resume/";

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/apps")
    public String appsList(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model){

        int pageSize = 10;
        PageInfo<Applicant> pageInfo = applicantService.getApps(pageNum,pageSize);
        List<ApplicantVo> applicantVos = pubService.changeApplicantVo(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("apps",applicantVos);
        return "app/appList";

    }

    @GetMapping("/app")
    public String searchApp(String username,String deptName,String jobName,@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum,Model model){
        int pageSize = 10;
        PageInfo<Applicant> pageInfo = applicantService.getAppByCondition(username,deptName,jobName,pageNum,pageSize);
        List<ApplicantVo> applicantVos = pubService.changeApplicantVo(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("apps",applicantVos);
        return "app/appList";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "app/addApp";
    }

    @PostMapping("/add")
    public String addApp(@RequestParam("file") MultipartFile file,Model model,@Validated Applicant applicant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            if (file.isEmpty()) {
                applicant.setResume("");
            }else {
                String fileName = file.getOriginalFilename();

                String path = filePath + fileName;
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                file.transferTo(dest);// 文件写入
                applicant.setResume(path);
            }

            applicantService.addApp(applicant);
            model.addAttribute("addMsg","添加成功！");
            return "app/addApp";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("addMsg","文件上传失败！");
        return "app/addApp";
    }

    @GetMapping("/app/{id}")
    public String getApp(@PathVariable("id")Integer id, Model model){
        Applicant applicant = applicantMapper.getApplicantById(id);
        model.addAttribute("app",applicant);
        return "app/updateApp";
    }

    @PostMapping("/update")
    public String updateApp(@RequestParam("file") MultipartFile file,Model model,Applicant applicant) {
        try {
            if (file.isEmpty()) {

            }else{
                String fileName = file.getOriginalFilename();

                String path = filePath + fileName;
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                file.transferTo(dest);// 文件写入
                applicant.setResume(path);
            }
            applicantService.update(applicant);
            model.addAttribute("updateMsg","信息修改成功！");
            model.addAttribute("app",applicant);
            return "app/updateApp";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("updateMsg","文件上传失败！");
        return "app/updateApp";
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Long id){
        applicantService.delete(id);
        return "redirect:/app/apps";

    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestParam("check")Long[] ids){
        applicantService.delteBatch(ids);
        return "redirect:/app/apps";

    }

    //下载简历
    @GetMapping("/resume/{id}")
    public void getResume(@PathVariable("id")Long id, HttpServletRequest request, HttpServletResponse response){

            //设置文件路径
            Applicant app = applicantMapper.getApplicantById(id);
            String path = app.getResume();
            String fileName = path.substring(path.lastIndexOf('/')+1);
            File file = new File(path);

            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


    }

    @GetMapping("/talents")
    public String talentsList(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model){

        int pageSize = 10;
        PageInfo<Applicant> pageInfo = applicantService.getTalents(pageNum,pageSize);
        List<ApplicantVo> applicantVos = pubService.changeApplicantVo(pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("apps",applicantVos);
        return "app/talentList";

    }


}

