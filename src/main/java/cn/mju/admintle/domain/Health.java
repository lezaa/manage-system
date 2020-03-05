package cn.mju.admintle.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Health {
    private Integer id;
    private Long userId;
    //现住址
    @NotBlank
    private String address;
    //今日体温
    @NotNull
    private Double temp;
    //今日行动轨迹
    private String path;
    //是否接触疫区人员
    @NotNull
    //接触1未接触0
    private Integer touch;
    //今日身体状况
    @NotBlank
    private String state;
    //今天日期
    @NotNull
    private Date today;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTouch() {
        return touch;
    }

    public void setTouch(Integer touch) {
        this.touch = touch;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "Health{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", temp=" + temp +
                ", path='" + path + '\'' +
                ", touch=" + touch +
                ", state='" + state + '\'' +
                ", today=" + today +
                '}';
    }
}
