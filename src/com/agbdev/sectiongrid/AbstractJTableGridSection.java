package com.agbdev.sectiongrid;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public abstract class AbstractJTableGridSection extends AbstractGridSection {
	private GridDataModel model;
	private JScrollPane scrollPane;

	protected AbstractJTableGridSection(final String sectionName, final String desc, final GridDataModel model) {
		super(sectionName, desc);
		this.model = model;
    	JTable table = new JTable(new GridModel(model));
    	table.setAutoCreateRowSorter(true);
    	this.scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
	}

	public void update() {
		this.scrollPane.repaint();
    }

	public Object getImplementation() {
	    return this.scrollPane;
    }

	public GridDataModel getDataModel() {
	    return this.model;
    }

	private final class GridModel extends AbstractTableModel {
		private static final long serialVersionUID = 80394553666744922L;
		private GridDataModel model;

		private GridModel(final GridDataModel model) {
			this.model = model;
		}

		@Override
        public String getColumnName(final int col) {
	        return this.model.getColumnName(col);
	    }


		public int getColumnCount() {
			return this.model.getNumColumns();
		}

		public int getRowCount() {
			return this.model.getNumRows();
		}

		public Object getValueAt(final int rowIndex, final int colIndex) {
			return this.model.getValueAt(rowIndex, colIndex);
		}

	}


}
