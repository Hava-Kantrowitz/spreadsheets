package edu.cs3500.spreadsheets.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;

public class SpreadsheetTable extends JTable {

  private BasicSpreadsheet spreadsheet;
  private int INIT_NUM_CELLS = 300;

  public SpreadsheetTable(BasicSpreadsheet spreadsheet) {
    this.spreadsheet = spreadsheet;
  }

  private int getRows() {
    int rowNum = INIT_NUM_CELLS;
    int numRows = getMaxRows()+1;
    if (numRows > rowNum) {
      rowNum = numRows;
    }

    return rowNum;
  }

  private int getCols() {
    int colNum = INIT_NUM_CELLS;
    int numCols = getMaxCols()+1;

    if (numCols > colNum) {
      colNum = numCols;
    }

    return colNum;
  }

  JTable getTable() {

    String[] colHeadings = getHeaders(getCols());

    DefaultTableModel model = new SpecialTableModel(getRows(), getCols()) ;
    model.setColumnIdentifiers(colHeadings);
    for (Coord c : spreadsheet.getListCoords()) {
      model.setValueAt(spreadsheet.getCellAt(c).toString(), c.row, c.col);
    }

    for (int i = 1; i < getRows()+1; i++) {
      model.setValueAt(i, i-1, 0);
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

  private int getMaxCols() {
    int highestCol = 0;

    for (Coord coord : spreadsheet.getListCoords()) {
      int checkCol = coord.col;
      if (checkCol > highestCol) {
        highestCol = checkCol;
      }
    }

    return highestCol;

  }

  private String[] getHeaders(int size) {

    String[] headerList = new String[size];
    for (int i = 0; i < size; i++) {
      headerList[i] = Coord.colIndexToName(i);
    }
    return headerList;
  }


}
