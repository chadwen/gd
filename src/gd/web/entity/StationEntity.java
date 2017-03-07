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
@Table(name="gd_station") 
public class StationEntity {
	@Id
    @GeneratedValue
    @Column(name="out_id")
    private int id;
	
	@Column(name="sta_alias",nullable=false)
	private String staAlias;
	
	@Column(name="sta_fullname",nullable=false)
	private String staFullName;
	
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

	public String getStaAlias() {
		return staAlias;
	}

	public void setStaAlias(String staAlias) {
		this.staAlias = staAlias;
	}

	public String getStaFullName() {
		return staFullName;
	}

	public void setStaFullName(String staFullName) {
		this.staFullName = staFullName;
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
