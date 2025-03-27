package ru.stqa.pft.adressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("group")
public class GroupDate {
	@XStreamOmitField
	private int id = Integer.MAX_VALUE;
	private String name;
	private String header;
	private String footer;


	public GroupDate withId(int id) {
		this.id = id;
		return this;
	}

	public GroupDate withName(String name) {
		this.name = name;
		return this;
	}

	public GroupDate withHeader(String header) {
		this.header = header;
		return this;
	}

	public GroupDate withFooter(String footer) {
		this.footer = footer;
		return this;
	}

	@Override
	public String toString() {
		return "GroupDate{" +
						"id='" + id + '\'' +
						", name='" + name + '\'' +
						'}';
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GroupDate groupDate = (GroupDate) o;
		return id == groupDate.id && Objects.equals(name, groupDate.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
}
