package com.agbdev.sectiongrid;

public class TestSection extends AbstractJTableGridSection {
	private GridDataModel model = new GridDataModel(Col.values());


	public TestSection() {
	    super("Test Section", null, new GridDataModel(Col.values()));
    }

	@Override
    public GridDataModel getDataModel() {
		int rowCount = 0;
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
		this.model.add(getFakeRow(rowCount++));
	    return this.model;
    }

	private Row getFakeRow(final int rowCount) {
		return new Row() {

			public Object getValue(final Column col) {
				return "Row "+ rowCount + " data";
			}
		};
	}

	private enum Col implements Column {
		COL1 {
			public String getTitleString() {
				return "Column 1";
			}
		},

		COL2 {
			public String getTitleString() {
				return "Column 2";
			}
		};

		public int index() {
	        return ordinal();
	    }

	}

	@Override
    public ID getId() {
	    return new ID() {};
    }

}
