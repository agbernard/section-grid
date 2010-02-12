package com.agbdev.sectiongrid;

import java.util.List;


public class TestSection implements GridSection {
	private int rowCount = 0;

	private GridDataModel model = new GridDataModel(Col.values());

	@Override
    public String getName() {
	    return "Section 1";
    }

	@Override
    public GridDataModel getDataModel() {
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
		this.model.add(getFakeRow(this.rowCount++));
	    return this.model;
    }

	private Row getFakeRow(final int rowCount) {
		return new Row() {

			@Override
			public Object getValue(final Column col) {
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

		@Override
	    public int index() {
	        return ordinal();
	    }

	}

	@Override
    public ID getId() {
	    return new ID() {};
    }

	@Override
    public void update(final List<Row> data) {
	    this.model.update(data);
    }

}
