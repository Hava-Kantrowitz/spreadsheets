package edu.cs3500.spreadsheets.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import edu.cs3500.spreadsheets.controller.Features;

/**
 * This is a listener on the RowHeaderTable to react to mouse pressed and released events for the
 * purpose of resizing given rows.
 */
public class RowSizeListener implements MouseListener {

  private JTable rowHeaderTable;
  private JTable fullSheet;
  private Features controller;

  private int currCellRow = -1;
  private int startingY = -1;

  /**
   * This is the constructor to make the listener for changing the row size.
   *
   * @param rows      the given table of row headers
   * @param fullSheet the given sheet (with all the cells)
   */
  public RowSizeListener(JTable rows, JTable fullSheet, Features controller) {
    this.rowHeaderTable = rows;
    this.fullSheet = fullSheet;
    this.controller = controller;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    //nothing to do when mouse clicked
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // getting the row that is selected to change
    this.currCellRow = rowHeaderTable.getSelectedRow();
    // gets the place where the y started
    this.startingY = e.getY();


  }

  @Override
  public void mouseReleased(MouseEvent e) {

    // checking that there is a currCell selected and have a starting Y Value
    if (currCellRow != -1 && startingY != -1) {
      // gets the change in y
      int changeY = e.getY() - startingY;
      // gets the new height of the row
      int newRowHeight = fullSheet.getRowHeight(currCellRow) + changeY;

      // making sure we have a valid row height (stops at default starting size)
      if (newRowHeight < 16) {
        newRowHeight = 16;
      }
      fullSheet.setRowHeight(currCellRow, newRowHeight); // set in the full sheet
      rowHeaderTable.setRowHeight(currCellRow, newRowHeight); // set in the row headers
      controller.onRowResized(currCellRow, newRowHeight); // setting with controller

    }

    // set back to defaults
    currCellRow = -1;
    startingY = -1;

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //nothing to do when mouse entered
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //nothing to do when mouse exited
  }
}
