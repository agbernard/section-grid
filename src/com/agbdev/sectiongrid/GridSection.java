package com.agbdev.sectiongrid;

import java.util.List;

public interface GridSection {
	public interface ID {}

	public String getName();
	public GridDataModel getDataModel();
	public ID getId();
	public void update(List<Row> data);
}
