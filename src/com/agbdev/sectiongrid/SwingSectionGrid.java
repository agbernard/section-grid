package com.agbdev.sectiongrid;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import com.agbdev.sectiongrid.GridSection.ID;

public class SwingSectionGrid
implements SectionGrid {
	private JPanel pnlMain;
	private Map<ID, GridSection> sections;

	public SwingSectionGrid() {
    	this.pnlMain = new JPanel(new SpringLayout());
    	this.sections = new HashMap<ID, GridSection>();
	}

	public void addSection(final GridSection section) {
    	GridDataModel model = section.getDataModel();
    	JTable table = new JTable(new GridModel(model));
    	JScrollPane scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);

    	JPanel panel = new JPanel(new BorderLayout());
    	panel.add(new JLabel(section.getName()), BorderLayout.PAGE_START);
    	panel.add(scrollPane, BorderLayout.CENTER);
    	this.pnlMain.add(panel);
    	this.sections.put(section.getId(), section);
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


		@Override
		public int getColumnCount() {
			return this.model.getNumColumns();
		}

		@Override
		public int getRowCount() {
			return this.model.getNumRows();
		}

		@Override
		public Object getValueAt(final int rowIndex, final int colIndex) {
			return this.model.getValueAt(rowIndex, colIndex);
		}

	}

	@Override
    public Object getImplementation() {
		SpringUtilities.makeCompactGrid(this.pnlMain,
		                                this.sections.size(), 1, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
	    return this.pnlMain;
    }

	@Override
    public void updateSection(final ID id, final List<Row> data) {
	    this.sections.get(id).update(data);
    }

}
