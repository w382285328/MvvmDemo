package com.huantai.qhytims.bean;

import java.io.Serializable;

public class PopSelectInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private float value;
	private int OrganizationID;
	private boolean isS;
	private int imageId;

	public PopSelectInfo(String name, int imageId) {
		this.name = name;
		this.imageId = imageId;
	}

	public PopSelectInfo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PopSelectInfo(int id, String name, boolean isS) {
		this.id = id;
		this.name = name;
		this.isS = isS;
	}

	public PopSelectInfo(int id, String name, float value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public PopSelectInfo(int id, String name, int organizationID) {
		this.id = id;
		this.name = name;
		OrganizationID = organizationID;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public PopSelectInfo() {
		super();
	}
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
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}

	public int getOrganizationID() {
		return OrganizationID;
	}

	public boolean isS() {
		return isS;
	}

	public void setS(boolean s) {
		isS = s;
	}

	public void setOrganizationID(int organizationID) {
		OrganizationID = organizationID;
	}
}
