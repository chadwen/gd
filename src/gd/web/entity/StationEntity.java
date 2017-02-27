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
    private Integer id;
	
	@Column(name="sta_name",nullable=false)
	private String staName;
	
	@Column(name="sta_fullname",nullable=false)
	private String staFullName;
	
	@Column(name="position_x",nullable=false)
	private String posx;
	
	@Column(name="position_y",nullable=false)
	private String posy;
	
	@Column(name="comment",nullable=false)
	private String comment;
	
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

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
