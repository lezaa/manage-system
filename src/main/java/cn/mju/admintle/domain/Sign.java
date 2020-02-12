package cn.mju.admintle.domain;

import java.io.Serializable;
import java.util.Date;

public class Sign implements Serializable {

    private Integer id;
    private Long userId;
    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
