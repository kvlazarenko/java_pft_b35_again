package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public class ContactDate {

	private int id;
	private final String firstname;
	private final String lastname;
	private final String phone;
	private final String email;
	private String group;

	public ContactDate(String firstname, String lastname, String phone, String email, String group) {
		this.id = Integer.MAX_VALUE;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.group = group;
	}

	public ContactDate(int id, String firstname, String lastname, String phone, String email, String group) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.group = group;
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

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getGroup() {
		return group;
	}
	public void setId(int id) {
		this.id = id;
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
