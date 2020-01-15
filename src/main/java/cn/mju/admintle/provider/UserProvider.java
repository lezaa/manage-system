package cn.mju.admintle.provider;

import cn.mju.admintle.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;


public class UserProvider {
    /**
     * 动态插入
     * @param user
     * @return
     */
    public String insertUser(final User user) {
        return new SQL() {{
            INSERT_INTO("tb_user");
            if (user.getUsername() != null) {
                VALUES("username", "#{username}");
            }
            if (user.getPassword() != null) {
                VALUES("password", "#{password}");
            }
            if (user.getEmail() != null) {
                VALUES("email", "#{email}");
            }
            if (user.getPhone() != null) {
                VALUES("phone", "#{phone}");
            }
            if (user.getAddress() != null) {
                VALUES("address", "#{address}");
            }
            if (user.getBirthday() != null) {
                VALUES("birthday", "#{birthday}");
            }
            if (user.getDeptId() != null) {
                VALUES("dept_id", "#{deptId}");
            }
            if (user.getJobId() != null) {
                VALUES("job_id", "#{jobId}");
            }
            if (user.getState() != null) {
                VALUES("state", "#{state}");
            }

        }}.toString();
    }

    /**
     * 动态更新
     * @param user
     * @return
     */
    public String updateUser(final User user) {
        return new SQL() {{
            UPDATE("tb_user");
            if (user.getUsername() != null) {
                SET("username = #{username}");
            }
            if (user.getPassword() != null) {
                SET("password = #{password}");
            }
            if (user.getEmail() != null) {
                SET("email = #{email}");
            }
            if (user.getPhone() != null) {
                SET("phone = #{phone}");
            }
            if (user.getBirthday()!= null) {
                SET("birthday = #{birthday}");
            }
            if (user.getAddress()!= null) {
                SET("address = #{address}");
            }
            if (user.getDeptId()!= null) {
                SET("dept_id = #{deptId}");
            }
            if (user.getJobId()!= null) {
                SET("job_id = #{jobId}");
            }
            if (user.getState()!= null) {
                SET("state = #{state}");
            }

            WHERE("id = #{id}");
        }}.toString();

    }

    /**
     * 动态查询
     */
    public String selectUser(Map<String,Object> params) {

        StringBuffer sql=new StringBuffer("select * from tb_user  where 1 = 1"+" ");
        if (params.get("username") != null && !params.get("username").equals("")){
            sql.append("and username LIKE CONCAT ('%',#{username},'%') ");
        }
        if (params.get("deptId") != null && !params.get("deptId").equals("")){
            sql.append("and dept_id = #{deptId} ");
        }
        if (params.get("jobId") != null && !params.get("jobId").equals("")){
            sql.append("and job_id = #{jobId} ");
        }
        return sql.toString();
    }

    /**
     * 批量删除
     * @param map
     * @return
     */
    public String batchDelete(Map map) {

        List<Integer> ids = (List<Integer>) map.get("list");
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM tb_user WHERE id IN (");

        for (int i = 0; i < ids.size(); i++) {

            sb.append("'").append(ids.get(i)).append("'");

            if (i < ids.size() - 1)

                sb.append(",");

        }

        sb.append(")");

        return sb.toString();

    }
}

