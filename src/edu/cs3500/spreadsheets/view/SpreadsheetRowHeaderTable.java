package edu.cs3500.spreadsheets.view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadsheetReadOnlyAdapter;

public class SpreadsheetRowHeaderTable extends JTable {
  private SpreadsheetReadOnlyAdapter spreadsheet;
  private int initNumCells = 300;

  private DefaultTableModel model;

  /**
   * Constructs an instance of the table.
   *
   * @param spreadsheet the spreadsheet to construct
   */
  SpreadsheetRowHeaderTable(SpreadsheetReadOnlyAdapter spreadsheet) {
    this.spreadsheet = spreadsheet;
  }

  /**
   * Gets the default table model.
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
   * Gets the created table.
   *
   * @return the table
   */
  JTable getTable() {

    model = new SpecialTableModel(getRows(), 1);

    for (int i = 1; i < getRows() + 1; i++) {
      model.setValueAt(i, i - 1, 0);
    }
    return new JTable(model);
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

}
