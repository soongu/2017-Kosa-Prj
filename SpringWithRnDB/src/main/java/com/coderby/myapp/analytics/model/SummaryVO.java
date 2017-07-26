package com.coderby.myapp.analytics.model;

import java.util.Arrays;

public class SummaryVO {
	private String[] colNames;
	private String[] values;
	
	public String[] getColNames() {
		return colNames;
	}
	public void setColNames(String[] colNames) {
		this.colNames = colNames;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	@Override
	public String toString() {
		return "SummaryVO [colNames=" + Arrays.toString(colNames) + ", values=" + Arrays.toString(values) + "]";
	}

}
