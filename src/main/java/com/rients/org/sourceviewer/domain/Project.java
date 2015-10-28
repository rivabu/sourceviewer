package com.rients.org.sourceviewer.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Project implements Serializable{
 
    private static final long serialVersionUID = -7788619177798333712L;
	private int id;

	@NotNull
	@NotEmpty
	private String name;

	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description;
	}
}
