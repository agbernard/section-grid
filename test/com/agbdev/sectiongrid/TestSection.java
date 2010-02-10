package com.agbdev.sectiongrid;


public class TestSection implements GridSection {

	@Override
    public GridDataModel getDataSource() {
		GridDataModel model = new GridDataModel(null);

	    return null;
    }

	@Override
    public String getName() {
	    return "Section 1";
    }

}
