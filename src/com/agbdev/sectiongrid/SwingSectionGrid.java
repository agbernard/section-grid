package com.agbdev.sectiongrid;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
    	table.setAutoCreateRowSorter(true);
    	JScrollPane scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);

    	JPanel panel = new JPanel(new BorderLayout());
    	Component sectionHeader = getSectionHeading(section.getHeader());
    	panel.add(sectionHeader, BorderLayout.PAGE_START);
    	panel.add(scrollPane, BorderLayout.CENTER);
    	this.pnlMain.add(panel);
    	this.sections.put(section.getId(), section);
	}

	private Component getSectionHeading(final GridSection.Header sectionHeader) {
		String text = sectionHeader.getName();
		if(sectionHeader.isBold()) {
			text = "<b>" + text + "</b>";
		}
		
		String desc = sectionHeader.getDesc();		
		if (desc != null) {
			text += " (" + sectionHeader.getDesc() + ")";
		}
		return new JLabel("<html>" + text + "</html>");
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

	public Object getImplementation() {
		SpringUtilities.makeCompactGrid(this.pnlMain,
		                                this.sections.size(), 1, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
	    return this.pnlMain;
    }

	public void updateSection(final ID id, final List<Row> data) {
	    GridDataModel model = this.sections.get(id).getDataModel();
	    model.update(data);
    }

	public void writeToCsv(String filePath) throws IOException {
		File csvFile = new File(filePath);
		Writer writer = null;
		try {
			writer = new FileWriter(csvFile);
			boolean wroteHeader = false;
			for (ID id : sections.keySet()) {
				GridSection section = sections.get(id);
				GridDataModel model = section.getDataModel();
				GridDataWriter gridWriter = model.getWriter(writer);
				
				if(!wroteHeader) {
					gridWriter.writeHeaderToStream();
					wroteHeader = true;
				}
				
				gridWriter.writeDataToStream();
			}
		} 
		finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

}
