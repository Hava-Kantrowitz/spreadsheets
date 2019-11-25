package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This is a class to create a non editable table when the cells are double clicked.
 */
public class NoEditTable extends JTable {

  private int timesClicked;
  DefaultTableModel model;


  /**
   * The constructor for a non editable form of a jtable.
   * @param model the given model for the JTable
   */
  public NoEditTable(DefaultTableModel model){
    super(model);
    this.model = model;
  }


  @Override
  public boolean isCellEditable(int row, int col){
    return false;
  }

  @Override
  public void setGridColor(Color color) {
    if (timesClicked % 3 == 0) {
      super.setGridColor(color);
    }
    else {
      super.setGridColor(Color.BLACK);
    }
    System.out.println(timesClicked);
  }


  public void trackNumClicked(int numClicked) {
    timesClicked = numClicked;
  }
}
