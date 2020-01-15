package cn.mju.admintle.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Job implements Serializable {
    private Integer id;

    private String jobName;

    private List<User> users;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
