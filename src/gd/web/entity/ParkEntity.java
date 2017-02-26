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
    @Column(name="out_id")
    private Integer id;
	
	@Column(name="position_x",nullable=false)
	private String posx;
	
	@Column(name="position_y",nullable=false)
	private String posy;
	
	@Column(name="total_lot",nullable=false)
	private int totalLot;
	
	@Column(name="available_lot",nullable=false)
	private int avaiLot;
	
	@Column(name="createtime",nullable=false)  
    private Date createTime = new Date();
	
	@Column(name="modifytime",nullable=false)  
    private Date modifyTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
