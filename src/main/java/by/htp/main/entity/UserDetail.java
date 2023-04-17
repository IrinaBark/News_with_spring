package by.htp.main.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_details")
public class UserDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Integer id;
	
	@Column(name = "name")
	@Pattern(regexp = "^[a-zA-Z]{3,20}$")
	private String name;
	
	@Column(name = "surname")
	@Pattern(regexp = "^[a-zA-Z]{3,25}$")
	private String surname;
	
	@Column(name = "email")
	@Pattern(regexp = "^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$")
	private String email;
	
	@Column(name = "birhday")
	@Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
	private String birthday;
	
	@OneToOne(mappedBy = "userDetail", cascade = CascadeType.ALL)
	private User user;
	
	
	public UserDetail() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int users_id) {
		this.id = users_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	@Override
	public int hashCode() {
		return Objects.hash(birthday, email, name, surname, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetail other = (UserDetail) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname)
				&& id == other.id;
	}
	

}
