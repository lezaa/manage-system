package cn.mju.admintle.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

    private Integer id;
    @NotBlank
    private String head;
    @NotBlank
    @Length(min=15, message = "公告内容长度最小为15")
    private String content;
    private Date createTime;
    private Long userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                '}';
    }
}
