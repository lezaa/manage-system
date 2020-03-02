package cn.mju.admintle.domain;


import java.io.Serializable;
import java.util.Date;

public class Wages implements Serializable {
    //工资
    private Long id;
    private Long userId;
    //基本工资
    private Integer basicWages;
    //交通房补
    private Integer livePay;
    //加班补贴
    private Integer nightPay;
    //奖金
    private Integer rewardPay;
    //保险金
    private Integer socialPay;
    //缺勤罚款
    private Integer absenceFines;
    //迟到罚款
    private Integer lateFines;
    //实际工资
    private Integer realWages;
    //发放日期
    private Date payDate;

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

    public Integer getBasicWages() {
        return basicWages;
    }

    public void setBasicWages(Integer basicWages) {
        this.basicWages = basicWages;
    }

    public Integer getLivePay() {
        return livePay;
    }

    public void setLivePay(Integer livePay) {
        this.livePay = livePay;
    }

    public Integer getNightPay() {
        return nightPay;
    }

    public void setNightPay(Integer nightPay) {
        this.nightPay = nightPay;
    }

    public Integer getRewardPay() {
        return rewardPay;
    }

    public void setRewardPay(Integer rewardPay) {
        this.rewardPay = rewardPay;
    }

    public Integer getSocialPay() {
        return socialPay;
    }

    public void setSocialPay(Integer socialPay) {
        this.socialPay = socialPay;
    }

    public Integer getAbsenceFines() {
        return absenceFines;
    }

    public void setAbsenceFines(Integer absenceFines) {
        this.absenceFines = absenceFines;
    }

    public Integer getLateFines() {
        return lateFines;
    }

    public void setLateFines(Integer lateFines) {
        this.lateFines = lateFines;
    }

    public Integer getRealWages() {
        return realWages;
    }

    public void setRealWages(Integer realWages) {
        this.realWages = realWages;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "Wages{" +
                "id=" + id +
                ", userId=" + userId +
                ", basicWages=" + basicWages +
                ", livePay=" + livePay +
                ", nightPay=" + nightPay +
                ", rewardPay=" + rewardPay +
                ", socialPay=" + socialPay +
                ", absenceFines=" + absenceFines +
                ", lateFines=" + lateFines +
                ", realWages=" + realWages +
                ", payDate=" + payDate +
                '}';
    }
}
