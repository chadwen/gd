package gd.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity  
@Table(name="gd_user")  
public class User{
	@Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
	
	@Column(name="usn",nullable=false)  
    private String userName;
	
	@Column(name="pwd",nullable=false)  
    private String password;
	
	@Column(name="privilege",nullable=false)
	private Integer priv;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPriv() {
		return priv;
	}

	public void setPriv(Integer priv) {
		this.priv = priv;
	}
	
}
