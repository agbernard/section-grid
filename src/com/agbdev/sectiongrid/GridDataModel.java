package com.agbdev.sectiongrid;

import java.util.ArrayList;
import java.util.List;

public class GridDataModel {
    private List<Row> data;
    private final Column[] columns;

	public GridDataModel(final Column[] columns) {
		this.columns = columns;
		this.data = new ArrayList<Row>();
	}

	public int getNumColumns() {
		return this.columns.length;
	}

	public int getNumRows() {
		return this.data.size();
	}

	public Object getValueAt(final int rowIndex, final int columnIndex) {
		Row record = this.data.get(rowIndex);
		Column col = this.columns[columnIndex];
		return record.getValue(col);
	}

    public String getColumnName(final int col) {
        return this.columns[col].name();
    }

}