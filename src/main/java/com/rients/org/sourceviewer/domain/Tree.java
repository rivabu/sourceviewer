package com.rients.org.sourceviewer.domain;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	private int id;
	
	private List<TreeElement> elements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TreeElement> getElements() {
		if (elements == null) {
			elements = new ArrayList<TreeElement>();
		}
		return elements;
	}

	public void setElements(List<TreeElement> elements) {
		this.elements = elements;
	}
}
