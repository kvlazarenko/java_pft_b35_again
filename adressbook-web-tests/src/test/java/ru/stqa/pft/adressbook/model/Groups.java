package ru.stqa.pft.adressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Groups extends ForwardingSet<GroupDate> {
	private Set<GroupDate> delegate;

	public Groups(Groups groups) {
		this.delegate = new HashSet<GroupDate>(groups.delegate);
	}

	public Groups() {
		this.delegate = new HashSet<GroupDate>();
	}

	public Groups(List<GroupDate> groups) {
		this.delegate = new HashSet<GroupDate>(groups);
	}

	public Groups(Set<GroupDate> groups) {
		this.delegate = new HashSet<GroupDate>(groups);
	}

	@Override
	protected Set<GroupDate> delegate() {
		return delegate;
	}

	public Groups withAdded(GroupDate group) {
		Groups groups = new Groups(this);
		groups.add(group);
		return groups;
	}

	public Groups without(GroupDate group) {
		Groups groups = new Groups(this);
		groups.remove(group);
		return groups;
	}

	/*@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
		)
	*/
}

