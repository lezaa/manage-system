package cn.mju.admintle.domain;

import java.util.Date;

public class File {
    private Long id;
    private Long userId;
    private Date entryTime;
    private Date quitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", userId=" + userId +
                ", entryTime=" + entryTime +
                ", quitTime=" + quitTime +
                '}';
    }
}
