package edu.cs3500.spreadsheets.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;

public class SpreadsheetTable extends JTable {

  private BasicSpreadsheet spreadsheet;

  public SpreadsheetTable(BasicSpreadsheet model) {
    this.spreadsheet = model;
  }

  public JTable getTable() {

    String[] colHeadings = getHeaders();
    int numRows = 1 ;
    DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length);
    model.setColumnIdentifiers(colHeadings);
    model.setNumRows(getMaxRows() + 1);

    for (Coord c : spreadsheet.getListCoords()) {
      model.setValueAt(spreadsheet.getCellAt(c).getRawContents(), c.row, c.col);
    }

    return new JTable(model);
  }

  private int getMaxRows() {
    int highestRow = 0;

    for (Coord coord : spreadsheet.getListCoords()) {
      int checkRow = coord.row;
      if (checkRow > highestRow) {
        highestRow = checkRow;
      }
    }

    return highestRow;
  }

  private String[] getHeaders() {
    int highestCol = 0;

    for (Coord coord : spreadsheet.getListCoords()) {
      int checkCol = coord.col;
      if (checkCol > highestCol) {
        highestCol = checkCol;
      }
    }
    String[] headerList = new String[highestCol+1];
    // starting at zero for empty string 0 col (gets a list of columns to the highest
    for (int i = 0; i < highestCol + 1; i++) {
      headerList[i] = Coord.colIndexToName(i);
    }
    return headerList;
  }

  private String getRowName(int num) {
    return Coord.colIndexToName(num-1);
  }


}
