package cn.mju.admintle.service;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.dto.FileDto;
import cn.mju.admintle.vo.NoticeVo;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminService {

    PageInfo<User> getUserByCondition(String username, String deptName, String jobName,int pageNum, int pageSize);

    boolean addUser(User user,String roleName);

    boolean update(User user,String roleName);

    boolean delete(Long id);

    boolean delteBatch(Long[] ids);

    PageInfo<Role> getRolesByName(int pageNum, int pageSize,String roleName);

    PageInfo<File> getAllFiles(int pageNum, int pageSize);

    boolean updateFile(File file);

    PageInfo<File> searchFile(String userName,int pageNum, int pageSize);

    boolean quitEmp(Long id);

    List<Dept> getDepts();

    Dept getDeptAndUsers(int id);

    boolean addDept(Dept dept);

    boolean updateDept(Dept dept);

    PageInfo<Notice> getAllNotice(int pageNum, int pageSize);

    NoticeVo findNotice(Integer id);

    PageInfo<Notice> searchNotice(String title,int pageNum, int pageSize);

    boolean publishNotice(Notice notice, HttpServletRequest request);

    boolean delteBatchNotice(Long[] ids);

    boolean deleteNotice(Integer id);

    List<UserVo> downloadUser();

    NoticeVo getLatest();




}


