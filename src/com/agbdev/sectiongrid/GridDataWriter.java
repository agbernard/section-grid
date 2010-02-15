package com.agbdev.sectiongrid;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class GridDataWriter {
	private static final char COL_DELIM = ',';
	private static final String NL = System.getProperty("line.separator");
	private List<Row> data;
    private final Column[] columns;
    private Writer writer;

    GridDataWriter(final List<Row> data, final Column[] columns) {
    	this.data = data;
    	this.columns = columns;
    }

    void setStream(final Writer writer) {
    	this.writer = writer;
    }

    public void writeHeaderToStream() throws IOException {
    	for (int i = 0; i < this.columns.length; i++) {
    		this.writer.write('"');
    		this.writer.write(this.columns[i].getTitleString());
    		this.writer.write('"');

    		if (i+1 < this.columns.length) {
				this.writer.write(COL_DELIM);
			}
    	}
    	this.writer.write(NL);
    }

    public void writeDataToStream() throws IOException {
	    for (Row row : this.data) {
			for (int i = 0; i < this.columns.length; i++) {
				Object value = row.getValue(this.columns[i]);
				this.writer.write('"');
				this.writer.write(value.toString());
				this.writer.write('"');

				if (i+1 < this.columns.length) {
					this.writer.write(COL_DELIM);
				}
			}
			this.writer.write(NL);
		}
    }
}
