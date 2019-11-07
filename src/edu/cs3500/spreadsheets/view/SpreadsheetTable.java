package edu.cs3500.spreadsheets.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;

public class SpreadsheetTable extends JTable {

  private BasicSpreadsheet spreadsheet;
  JTable table;

  public SpreadsheetTable(BasicSpreadsheet model) {
    this.spreadsheet = model;
  }

  public JTable getTable() {

    String[] colHeadings = getHeaders();
    int numRows = 5 ;
    DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
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
    for (int i = 1; i < highestCol; i++) {
      headerList[i] = getColName(i+1);
    }
    return headerList;
  }

  private String getRowName(int num) {
    return Coord.colIndexToName(num-1);
  }

  private String getColName(int num) {
    int parseNum = num-1;
    return Integer.toString(parseNum);
  }


}
