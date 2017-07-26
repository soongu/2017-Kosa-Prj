package com.coderby.myapp.analytics.model;

import java.util.Arrays;

public class SampleVO {
	private String type;
	private String name;
	private double[] data;
	private Marker marker;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double[] getData() {
		return data;
	}
	public void setData(double[] data) {
		this.data = data;
	}
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	@Override
	public String toString() {
		return "SampleVO [type=" + type + ", name=" + name + ", data=" + Arrays.toString(data) + ", marker=" + marker
				+ "]";
	}

}
