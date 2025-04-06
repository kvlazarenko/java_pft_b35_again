package ru.stqa.pft.adressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contacts")
@Entity
@Table(name = "addressbook")
public class ContactDate {
	@XStreamOmitField
	@Id
	@Column(name = "id")
	private int id = Integer.MAX_VALUE;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	@Transient
	private String group;
	@Column(name = "address")
	@Type(type = "text")
	private String address;
	@Column(name = "home")
	@Type(type = "text")
	private String homePhone;
	@Column(name = "mobile")
	@Type(type = "text")
	private String mobilePhone;
	@Column(name = "work")
	@Type(type = "text")
	private String workPhone;
	@Transient
	private String allPhone;
	@Column(name = "email")
	@Type(type = "text")
	private String email;
	@Column(name = "email2")
	@Type(type = "text")
	private String email2;
	@Column(name = "email3")
	@Type(type = "text")
	private String email3;
	@Transient
	private String allemail;
	@Column(name = "photo")
	@Type(type = "text")
	private String photo;


	public ContactDate withId(int id) {
		this.id = id;
		return this;
	}

	public ContactDate withFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ContactDate withLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ContactDate withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactDate withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactDate withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactDate withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactDate withAllPhones(String allPhone) {
		this.allPhone = allPhone;
		return this;
	}

	public ContactDate withEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactDate withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactDate withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}

	public ContactDate withAllemail(String allemail) {
		this.allemail = allemail;
		return this;
	}

	public ContactDate withGroup(String group) {
		this.group = group;
		return this;
	}

	public ContactDate withPhoto(File photo) {
		this.photo = photo.getPath();
		return this;
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return address;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getAllPhones() {
		return allPhone;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}

	public String getEmail3() {
		return email3;
	}

	public String getAllEmails() {
		return allemail;
	}


	public String getGroup() {
		return group;
	}

	public File getPhoto() {
		return new File(photo);
	}


	@Override
	public String toString() {
		return "ContactDate{" +
						"id=" + id +
						", firstname='" + firstname + '\'' +
						", lastname='" + lastname + '\'' +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactDate that = (ContactDate) o;
		return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstname, lastname);
	}
}
