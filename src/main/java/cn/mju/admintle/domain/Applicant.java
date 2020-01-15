package cn.mju.admintle.domain;

import java.util.Date;

public class Applicant {
    private Long id;
    private String username;
    private Integer deptId;
    private Integer jobId;
    private Integer expWages;
    private Date comeDate;
    private String address;
    private Date birthday;
    private String email;
    private Long phone;

    //简历
    private String resume;

    //待面试0面试通过1面试未通过2
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getExpWages() {
        return expWages;
    }

    public void setExpWages(Integer expWages) {
        this.expWages = expWages;
    }

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", deptId=" + deptId +
                ", jobId=" + jobId +
                ", expWages=" + expWages +
                ", comeDate=" + comeDate +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", resume='" + resume + '\'' +
                ", state=" + state +
                '}';
    }
}
