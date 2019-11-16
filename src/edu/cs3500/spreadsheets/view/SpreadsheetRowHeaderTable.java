package edu.cs3500.spreadsheets.view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadsheetReadOnlyAdapter;

/**
 * This class represents the table which holds the row headers (a table with one column).
 */
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
   * Gets the created table.
   *
   * @return the table
   */
  JTable getTable() {

    model = new DefaultTableModel(getRows(), 1);

    String[] col = new String[1];
    col[0] = " ";


    model.setColumnIdentifiers(col);


    for (int i = 1; i < getRows(); i++) {
      model.setValueAt(i, i, 0);
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

}
