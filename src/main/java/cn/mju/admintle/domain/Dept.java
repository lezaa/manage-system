package cn.mju.admintle.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable {
    private Integer id;
    @NotBlank
    private String deptName;
    private String book;
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", book='" + book + '\'' +
                ", users=" + users +
                '}';
    }
}

