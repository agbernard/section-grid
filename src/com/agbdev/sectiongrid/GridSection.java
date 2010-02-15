package com.agbdev.sectiongrid;


public interface GridSection {
	public interface ID {}

	public interface Header {
		public String getName();
		public String getDesc();
		public void setIsBold(boolean isBold);
		public boolean isBold();
	}

	public Header getHeader();
	public GridDataModel getDataModel();
	public ID getId();
	public void update();
	public Object getImplementation();
}
