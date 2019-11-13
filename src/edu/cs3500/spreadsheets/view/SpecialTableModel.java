package edu.cs3500.spreadsheets.view;

import javax.swing.table.DefaultTableModel;

/**
 * Creates a specific table model in order to override which cells are editable.
 */
public class SpecialTableModel extends DefaultTableModel {

  /**
   * Constructs the special table.
   * @param numRows the number of rows in the table
   * @param numCols the number of columns in the table
   */
  SpecialTableModel(int numRows, int numCols) {
    super(numRows, numCols);
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return col != 0;
  }

}
