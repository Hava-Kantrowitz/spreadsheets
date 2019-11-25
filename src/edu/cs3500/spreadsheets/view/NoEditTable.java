package edu.cs3500.spreadsheets.view;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This is a class to create a non editable table when the cells are double clicked.
 */
public class NoEditTable extends JTable {

  /**
   * The constructor for a non editable form of a jtable.
   */
  public NoEditTable(DefaultTableModel model){
    super(model);
  }


  @Override
  public boolean isCellEditable(int row, int col){
    return false;
  }

}
