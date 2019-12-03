package edu.cs3500.spreadsheets.view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;


/**
 * Models a spreadsheet table (a modified JTable) for the GUI view component and the editable GUI
 * view component.
 */
public class SpreadsheetTable extends JTable {

  private Spreadsheet spreadsheet;
  private int initNumCells = 300;

  private DefaultTableModel model;

  /**
   * Constructs an instance of the table.
   *
   * @param spreadsheet the spreadsheet to construct
   */
  SpreadsheetTable(Spreadsheet spreadsheet) {
    this.spreadsheet = spreadsheet;

  }

  /**
   * Gets the default table model.
   *
   * @return the model
   */
  public DefaultTableModel getModel() {
    return model;
  }

  /**
   * Gets the number of rows in the table.
   *
   * @return the number of rows
   */
  private int getRows() {
    int rowNum = initNumCells;
    int numRows = getMaxRows() + 1;
    if (numRows > rowNum) {
      rowNum = numRows;
    }

    return rowNum;
  }

  /**
   * Gets the number of columns in the table.
   *
   * @return the number of columns
   */
  private int getCols() {
    int colNum = initNumCells;
    int numCols = getMaxCols() + 1;

    if (numCols > colNum) {
      colNum = numCols;
    }

    return colNum;
  }

  /**
   * Gets the created table.
   *
   * @return the table
   */
  NoEditTable getTable() {

    String[] colHeadings = getHeaders(getCols());

    model = new DefaultTableModel(getRows(), 1);
    model.setColumnIdentifiers(colHeadings);
    for (Coord c : spreadsheet.getListCoords()) {
      try {
        model.setValueAt(spreadsheet.getCellAt(c).toString(), c.row - 1, c.col - 1);
      }
      // if a cell was invalid due to a reference to an invalid cell within calculation
      catch (IllegalArgumentException e) {
        model.setValueAt("#VALUE!", c.row - 1, c.col - 1);
      }
    }


    return new NoEditTable(model);
  }

  /**
   * Gets the maximum row number.
   *
   * @return the max amount of rows
   */
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

  /**
   * Gets the maximum column number.
   *
   * @return the max col
   */
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

  /**
   * Gets the column headers.
   *
   * @param size the number of columns in the table
   * @return the list of the headers
   */
  private String[] getHeaders(int size) {

    String[] headerList = new String[size];
    for (int i = 1; i < size; i++) {
      headerList[i] = Coord.colIndexToName(i + 1);
    }
    return headerList;
  }


}
