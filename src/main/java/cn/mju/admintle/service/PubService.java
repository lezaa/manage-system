package cn.mju.admintle.service;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.dto.FileDto;
import cn.mju.admintle.dto.RoleDto;
import cn.mju.admintle.dto.WagesDto;
import cn.mju.admintle.vo.ApplicantVo;
import cn.mju.admintle.vo.NoticeVo;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;


public interface PubService {
    PageInfo<User> getPage(int pageNum, int pageSize, HashMap<String, Object> map);

    PageInfo<Applicant> getAppPage(int pageNum, int pageSize, HashMap<String, Object> map);

    List<UserVo> changeVo(PageInfo<User> pageInfo);

    List<RoleDto> changeRoleDto(PageInfo<Role> pageInfo);

    List<FileDto> changeFileDto(PageInfo<File> pageInfo);

    List<NoticeVo> changeNoticeVo(PageInfo<Notice> pageInfo);

    List<WagesDto> changeWagesDto(PageInfo<Wages> pageInfo);

    List<ApplicantVo> changeApplicantVo(PageInfo<Applicant> pageInfo);

    User changeEntity(UserVo userVo);

    User passwordToMD5(User user);


}
