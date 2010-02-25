package com.agbdev.sectiongrid;

import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public abstract class AbstractJTableGridSection extends AbstractGridSection {
	private GridDataModel model;
	private JScrollPane scrollPane;
	private SectionGridJTableModel jTableModel;

	protected AbstractJTableGridSection(final String sectionName, final String desc, final GridDataModel model) {
		super(sectionName, desc);
		this.model = model;
		this.jTableModel = new SectionGridJTableModel(model);
    	JTable table = new JTable(this.jTableModel);
    	table.setAutoCreateRowSorter(true);
    	this.scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
	}

	public void update() {
		this.scrollPane.repaint();
    }

	public void update(final List<Row> data) {
		this.jTableModel.update(data);
		this.scrollPane.repaint();
	}

	public Object getImplementation() {
	    return this.scrollPane;
    }

	public GridDataModel getDataModel() {
	    return this.model;
    }

	private final class SectionGridJTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 80394553666744922L;
		private GridDataModel model;

		private SectionGridJTableModel(final GridDataModel model) {
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

		public void update(final List<Row> data) {
			this.model.update(data);
			super.fireTableDataChanged();
		}

	}


}
