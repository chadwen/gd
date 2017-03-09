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
@Table(name="gd_park")  
public class ParkEntity {
	@Id
    @GeneratedValue
    @Column(name="park_id")
    private int id;
	
	@Column(name="park_alias",nullable=false)
	private String alias;
	
	@Column(name="park_fullname",nullable=false)
	private String fullName;
	
	@Column(name="position_x",nullable=false)
	private String posx;
	
	@Column(name="position_y",nullable=false)
	private String posy;
	
	@Column(name="img_path",nullable=false)
	private String imgPath = "http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg";
	
	@Column(name="address",nullable=false,length=512)
	private String addr;
	
	@Column(name="brief",nullable=false,length=512)
	private String brief;
	
	@Column(name="total_lot",nullable=false)
	private int totalLot;
	
	@Column(name="available_lot",nullable=false)
	private int avaiLot;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPosx() {
		return posx;
	}

	public void setPosx(String posx) {
		this.posx = posx;
	}

	public String getPosy() {
		return posy;
	}

	public void setPosy(String posy) {
		this.posy = posy;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public int getTotalLot() {
		return totalLot;
	}

	public void setTotalLot(int totalLot) {
		this.totalLot = totalLot;
	}

	public int getAvaiLot() {
		return avaiLot;
	}

	public void setAvaiLot(int avaiLot) {
		this.avaiLot = avaiLot;
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
