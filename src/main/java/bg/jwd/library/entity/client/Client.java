package bg.jwd.library.entity.client;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bg.jwd.library.entity.AbstractEntity;
import bg.jwd.library.entity.user.LibraryUser;

@Entity
@Table(name = "CLIENT")
public class Client extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7265644640817017364L;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PID")
	private String pid;

	@Column(name = "BIRTHDATE")
	private Date birthdate;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private LibraryUser createdBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public LibraryUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(LibraryUser createdBy) {
		this.createdBy = createdBy;
	}
}