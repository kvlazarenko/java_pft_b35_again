package ru.stqa.pft.adressbook.model;

public class ContactDate {
	private final String firstname;
	private final String lastname;
	private final String phone;
	private final String email;

	public ContactDate(String firstname, String lastname, String phone, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
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
}
