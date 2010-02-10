package com.agbdev.sectiongrid;


public class TestSection implements GridSection {
	private int rowCount = 0;
	
	@Override
    public String getName() {
	    return "Section 1";
    }
	
	@Override
    public GridDataModel getDataModel() {
		GridDataModel model = new GridDataModel(Col.values());
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
		model.add(getFakeRow(rowCount++));
	    return model;
    }
	
	private Row getFakeRow(final int rowCount) {
		return new Row() {
			
			@Override
			public Object getValue(Column col) {
				return "Row "+ rowCount + " data";
			}
		};
	}
	
	private enum Col implements Column {
		COL1 {
			@Override
			public String getTitleString() {
				return "Column 1";
			}
		},
		
		COL2 {
			@Override
			public String getTitleString() {
				return "Column 2";
			}
		};

		
	}

}
