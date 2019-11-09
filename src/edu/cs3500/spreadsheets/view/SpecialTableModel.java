package edu.cs3500.spreadsheets.view;

import javax.swing.table.DefaultTableModel;

public class SpecialTableModel extends DefaultTableModel {

  public SpecialTableModel (int numRows, int numCols) {
    super(numRows, numCols);
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return col != 0;
  }
}
