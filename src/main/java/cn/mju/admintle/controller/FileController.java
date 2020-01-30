package cn.mju.admintle.controller;

import cn.mju.admintle.domain.File;

import cn.mju.admintle.dto.FileDto;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//离入职档案
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PubService pubService;


    @RequestMapping("/list")
    public String getAllFiles(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum, Model model){
        int pageSize = 10;
        PageInfo<File> filePageInfo = adminService.getAllFiles(pageNum, pageSize);
        List<FileDto> files = pubService.changeFileDto(filePageInfo);
        model.addAttribute("filePage",filePageInfo);
        model.addAttribute("files",files);
        return "file/fileList";
    }


    @RequestMapping("/quit/{id}")
    public String updateFile(@PathVariable("id")Long id,Model model){
        boolean flag = adminService.quitEmp(id);
        if (flag){
            model.addAttribute("fileMsg","设置离职成功！");
        }else{
            model.addAttribute("fileMsg","设置离职失败！请重试！");
        }
        return "redirect:/file/list";
    }

    @RequestMapping("/search")
    public String serachFile(@RequestParam(defaultValue = "1", value = "pageNum",required = true) Integer pageNum,@RequestParam("username") String username,Model model){
        int pageSize = 10;
        PageInfo<File> filePageInfo = adminService.searchFile(username, pageNum, pageSize);
        List<FileDto> fileDtos = pubService.changeFileDto(filePageInfo);
        model.addAttribute("filePage",filePageInfo);
        model.addAttribute("files",fileDtos);
        return "file/fileList";
    }
}
