package bg.jwd.library.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import bg.jwd.library.entity.AbstractEntity;

@Entity
@Table(name = "LIBRARY_USER")
public class LibraryUser extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 8982237127885447860L;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATED_BY")
	private long createdBy;

	@Column(name = "ROLE")
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
