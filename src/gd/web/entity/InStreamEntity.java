package gd.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity  
@Table(name="gd_in_stream") 
public class InStreamEntity {
	@Id
    @GeneratedValue
    @Column(name="in_id")
    private Integer id;
	
	@Column(name="t_01",nullable=false)  
    private Integer t01 = -1;
	
	@Column(name="t_02",nullable=false)  
    private Integer t02 = -1;
	
	@Column(name="t_03",nullable=false)  
    private Integer t03 = -1;
	
	@Column(name="t_04",nullable=false)  
    private Integer t04 = -1;
	
	@Column(name="t_05",nullable=false)  
    private Integer t05 = -1;
	
	@Column(name="t_06",nullable=false)  
    private Integer t06 = -1;
	
	@Column(name="t_07",nullable=false)  
    private Integer t07 = -1;
	
	@Column(name="t_08",nullable=false)  
    private Integer t08 = -1;
	
	@Column(name="t_09",nullable=false)  
    private Integer t09 = -1;
	
	@Column(name="t_10",nullable=false)  
    private Integer t10 = -1;
	
	@Column(name="t_11",nullable=false)  
    private Integer t11 = -1;
	
	@Column(name="t_12",nullable=false)  
    private Integer t12 = -1;
	
	@Column(name="t_13",nullable=false)  
    private Integer t13 = -1;
	
	@Column(name="t_14",nullable=false)  
    private Integer t14 = -1;
	
	@Column(name="t_15",nullable=false)  
    private Integer t15 = -1;
	
	@Column(name="t_16",nullable=false)  
    private Integer t16 = -1;
	
	@Column(name="t_17",nullable=false)  
    private Integer t17 = -1;
	
	@Column(name="t_18",nullable=false)  
    private Integer t18 = -1;
	
	@Column(name="t_19",nullable=false)  
    private Integer t19 = -1;
	
	@Column(name="t_20",nullable=false)  
    private Integer t20 = -1;
	
	@Column(name="t_21",nullable=false)  
    private Integer t21 = -1;
	
	@Column(name="t_22",nullable=false)  
    private Integer t22 = -1;
	
	@Column(name="t_23",nullable=false)  
    private Integer t23 = -1;
	
	@Column(name="t_00",nullable=false)  
    private Integer t00 = -1;
	
	@Column(name="sta_id",nullable=false)  
    private Integer staId;
	
	@Column(name="total",nullable=false)  
    private Integer total = -1;
	
	@Column(name="curr_date",nullable=false)  
    private String currDate;

	@Column(name="createtime",nullable=false)  
    private Date createTime = new Date();
	
	@Column(name="modifytime",nullable=false)  
    private Date modifyTime = new Date();
	
	@Column(name="isvalid",nullable=false)  
    private int isValid = 1;
	
	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getT01() {
		return t01;
	}

	public void setT01(Integer t01) {
		this.t01 = t01;
	}

	public Integer getT02() {
		return t02;
	}

	public void setT02(Integer t02) {
		this.t02 = t02;
	}

	public Integer getT03() {
		return t03;
	}

	public void setT03(Integer t03) {
		this.t03 = t03;
	}

	public Integer getT04() {
		return t04;
	}

	public void setT04(Integer t04) {
		this.t04 = t04;
	}

	public Integer getT05() {
		return t05;
	}

	public void setT05(Integer t05) {
		this.t05 = t05;
	}

	public Integer getT06() {
		return t06;
	}

	public void setT06(Integer t06) {
		this.t06 = t06;
	}

	public Integer getT07() {
		return t07;
	}

	public void setT07(Integer t07) {
		this.t07 = t07;
	}

	public Integer getT08() {
		return t08;
	}

	public void setT08(Integer t08) {
		this.t08 = t08;
	}

	public Integer getT09() {
		return t09;
	}

	public void setT09(Integer t09) {
		this.t09 = t09;
	}

	public Integer getT10() {
		return t10;
	}

	public void setT10(Integer t10) {
		this.t10 = t10;
	}

	public Integer getT11() {
		return t11;
	}

	public void setT11(Integer t11) {
		this.t11 = t11;
	}

	public Integer getT12() {
		return t12;
	}

	public void setT12(Integer t12) {
		this.t12 = t12;
	}

	public Integer getT13() {
		return t13;
	}

	public void setT13(Integer t13) {
		this.t13 = t13;
	}

	public Integer getT14() {
		return t14;
	}

	public void setT14(Integer t14) {
		this.t14 = t14;
	}

	public Integer getT15() {
		return t15;
	}

	public void setT15(Integer t15) {
		this.t15 = t15;
	}

	public Integer getT16() {
		return t16;
	}

	public void setT16(Integer t16) {
		this.t16 = t16;
	}

	public Integer getT17() {
		return t17;
	}

	public void setT17(Integer t17) {
		this.t17 = t17;
	}

	public Integer getT18() {
		return t18;
	}

	public void setT18(Integer t18) {
		this.t18 = t18;
	}

	public Integer getT19() {
		return t19;
	}

	public void setT19(Integer t19) {
		this.t19 = t19;
	}

	public Integer getT20() {
		return t20;
	}

	public void setT20(Integer t20) {
		this.t20 = t20;
	}

	public Integer getT21() {
		return t21;
	}

	public void setT21(Integer t21) {
		this.t21 = t21;
	}

	public Integer getT22() {
		return t22;
	}

	public void setT22(Integer t22) {
		this.t22 = t22;
	}

	public Integer getT23() {
		return t23;
	}

	public void setT23(Integer t23) {
		this.t23 = t23;
	}

	public Integer getT00() {
		return t00;
	}

	public void setT00(Integer t00) {
		this.t00 = t00;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getStaId() {
		return staId;
	}

	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	
}
