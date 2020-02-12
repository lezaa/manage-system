package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.mapper.*;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.vo.NoticeVo;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private PubService pubService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 条件组合查询
     * @param username
     * @param deptName
     * @param jobName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> getUserByCondition(String username, String deptName, String jobName,int pageNum, int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("deptName",deptName);
        map.put("jobName",jobName);
        map.put("username",username);
        if ( deptName.equals("") && jobName.equals("")){
            return pubService.getPage(pageNum,pageSize,map);

        }
        if (deptName.equals("") && !jobName.equals("")){
            Job job = jobMapper.getJobByName(map);
            map.put("jobId",job.getId());
            return pubService.getPage(pageNum,pageSize,map);
        }
        if (!deptName.equals("") && jobName.equals("")){
            Dept dept = deptMapper.getDeptByName(map);
            map.put("deptId",dept.getId());
            return pubService.getPage(pageNum,pageSize,map);
        }else{
        Dept dept = deptMapper.getDeptByName(map);
        Job job = jobMapper.getJobByName(map);
        map.put("deptId",dept.getId());
        map.put("jobId",job.getId());
            return pubService.getPage(pageNum,pageSize,map);
        }

    }

    /**
     * 添加员工
     */
    @Override
    public boolean addUser(User user,String roleName) {
        boolean i = userMapper.addUser(user) >0;
        //添加员工时自动添加档案以及角色信息
        if (i){
            File file = new File();
            file.setUserId(user.getId());
            file.setEntryTime(new Date());
            file.setDeptName(deptMapper.getDeptById(userMapper.getUserById(user.getId()).getDeptId()).getDeptName());
            file.setJobName(jobMapper.getJobById(userMapper.getUserById(user.getId()).getJobId()).getJobName());
            fileMapper.insertFile(file);

            Role role = new Role();
            role.setUserId(user.getId());
            role.setRoleName(roleName);
            roleMapper.addRole(role);
            return true;
        }else {
            return false;
        }


    }

    @Override
    public boolean update(User user,String roleName) {
        //修改部门和职位信息自动添加档案记录
        User dbuser = userMapper.getUserById(user.getId());

        //修改角色
        Role role = new Role();
        role.setUserId(user.getId());
        role.setRoleName(roleName);
        roleMapper.updateRole(role);

        boolean flag = userMapper.updateUser(user) >0;
        if (user.getDeptId()!=dbuser.getDeptId() || user.getJobId()!=dbuser.getJobId()){
            //旧档案添加离职日期
            List<File> fileByUserId = fileMapper.getFileByUserId(user.getId());

            File oldFile = fileByUserId.get(fileByUserId.size() - 1);

            oldFile.setQuitTime(new Date());


            fileMapper.updateFile(oldFile);
            //添加新档案
            File file = new File();
            file.setUserId(user.getId());
            file.setEntryTime(new Date());
            file.setDeptName(deptMapper.getDeptById(userMapper.getUserById(user.getId()).getDeptId()).getDeptName());
            file.setJobName(jobMapper.getJobById(userMapper.getUserById(user.getId()).getJobId()).getJobName());
            fileMapper.insertFile(file) ;

        }
        return flag;

    }

    @Override
    public boolean delete(Long id) {
        boolean flag = userMapper.deleteUser(id) >0;
        if (flag){
            boolean i = roleMapper.deleteRole(id) >0;
            int delete = fileMapper.delete(id);
            int delete1 = noticeMapper.deleteByUser(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean delteBatch(Long[] ids) {
        List<Long> cids = new ArrayList<>(Arrays.asList(ids));
        boolean flag = userMapper.deleleteBatch(cids) >0;
        if (flag){
            boolean i = roleMapper.deleteBatch(cids) >0;
            boolean j = fileMapper.deleleteBatch(cids) >0;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public PageInfo<Role> getRolesByName(int pageNum, int pageSize, String roleName) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = roleMapper.getRolesByName(roleName);
        PageInfo<Role> page = new PageInfo<>(roles);
        return page;
    }

    @Override
    public PageInfo<File> getAllFiles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<File> all = fileMapper.getAll();
        PageInfo<File> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public boolean updateFile(File file) {
        boolean flag = fileMapper.updateFile(file) >0;
        return flag;
    }

    @Override
    public PageInfo<File> searchFile(String userName,int pageNum, int pageSize) {
        ArrayList<Long> ids = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",userName);
        List<User> users = userMapper.getUserByName(map);
        for (User user : users) {
            Long id = user.getId();
            ids.add(id);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<File> files = fileMapper.getFileByIds(ids);
        PageInfo<File> filePageInfo = new PageInfo<>(files);
        return filePageInfo;
    }

    @Override
    public boolean quitEmp(Long id) {
        File file = fileMapper.getFileById(id);
        file.setQuitTime(new Date());
        boolean flag = fileMapper.updateFile(file) >0;
        if (flag){
            User user = userMapper.getUserById(file.getUserId());
            user.setState(0);
            boolean i= userMapper.updateUser(user)>0;
            return i;
        }else {
            return false;
        }

    }

    @Override
    public List<Dept> getDepts() {
        List<Dept> depts = deptMapper.getDepts();
        return depts;
    }

    @Override
    public List<Dept> getDeptData() {
        List<Dept> deptData = deptMapper.getDeptData();
        return deptData;
    }

    @Override
    public List<Job> getJobData() {
        List<Job> jobData = jobMapper.getJobData();
        return jobData;
    }

    @Override
    public Dept getDeptAndUsers(int id) {
        Dept deptAndUser = deptMapper.getDeptAndUser(id);
        return deptAndUser;
    }

    @Override
    public boolean checkDept(int id) {
        Dept deptById = deptMapper.getDeptById(id);
        if (deptById != null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean addDept(Dept dept) {
        boolean flag= deptMapper.addDept(dept) >0;
        return flag;
    }

    @Override
    public boolean updateDept(Dept dept) {
        boolean flag= deptMapper.updateDept(dept) >0;
        return flag;
    }

    @Override
    public PageInfo<Notice> getAllNotice(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> notices = noticeMapper.findAll();
        PageInfo<Notice> noticePageInfo = new PageInfo<>(notices);
        return noticePageInfo;
    }

    @Override
    public NoticeVo findNotice(Integer id) {
        Notice notice = noticeMapper.getNotice(id);
        NoticeVo noticeVo = new NoticeVo();
        noticeVo.setTitle(notice.getHead());
        noticeVo.setUserName(userMapper.getUserById(notice.getUserId()).getUsername());
        BeanUtils.copyProperties(notice,noticeVo);
        return noticeVo;
    }

    @Override
    public PageInfo<Notice> searchNotice(String title,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> noticesByTitle = noticeMapper.getNoticesByTitle(title);
        PageInfo<Notice> noticePageInfo = new PageInfo<>(noticesByTitle);
        return noticePageInfo;
    }

    @Override
    public boolean publishNotice(Notice notice, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        notice.setUserId(user.getId());
        notice.setCreateTime(new Date());
        boolean flag = noticeMapper.addNotice(notice) >0;
        return flag;
    }


    @Override
    public boolean delteBatchNotice(Long[] ids) {
        List<Long> cids = new ArrayList<>(Arrays.asList(ids));
        boolean flag = noticeMapper.deleleteBatch(cids) >0;
        return flag;
    }

    @Override
    public boolean deleteNotice(Integer id) {
        boolean flag = noticeMapper.delete(id)>0;
        return flag;
    }

    @Override
    public List<UserVo> downloadUser() {
        List<User> users = userMapper.getUsers();
        List<UserVo> userVos = users.stream().map(e -> (
                new UserVo(e.getId(),e.getUsername(), e.getAddress(), e.getBirthday(), e.getEmail(), e.getPhone(),
                        deptMapper.getDeptById(e.getDeptId()).getDeptName(), jobMapper.getJobById(e.getJobId()).getJobName(),
                        e.getState()
                )
        )).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public NoticeVo getLatest() {
        List<Notice> list = noticeMapper.findAll();
        Notice notice = list.get(0);
        NoticeVo noticeVo = new NoticeVo();
        noticeVo.setTitle(notice.getHead());
        noticeVo.setUserName(userMapper.getUserById(notice.getUserId()).getUsername());
        BeanUtils.copyProperties(notice,noticeVo);
        return noticeVo;
    }


}
