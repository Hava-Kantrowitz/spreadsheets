package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.controller.Features;

/**
 * Models a vertical scroll bar listener for the spreadsheet graphical view with the purpose of
 * adding data to allow for infinite scrolling.
 */
public class VerticalScrollListener implements AdjustmentListener {


  private DefaultTableModel table;
  private int scrollMaxCount;
  private DefaultTableModel rows;
  private JScrollBar rowScroll;
  private Features controller;

  /**
   * This constructs a new spreadsheet scroll listener that does not include a controller.
   *
   * @param table     the data of the spreadsheet in the form of a table
   * @param rows      the row headers of the table
   * @param rowScroll the vertical scroll bar for the rows
   */
  VerticalScrollListener(DefaultTableModel table, DefaultTableModel rows, JScrollBar rowScroll) {
    super();
    this.table = table;
    this.scrollMaxCount = 0;
    this.rows = rows;
    this.rowScroll = rowScroll;
  }

  /**
   * This constructs a new spreadsheet scroll listener that does include a controller.
   *
   * @param table     the data of the spreadsheet in the form of a table
   * @param rows      the row headers of the table
   * @param rowScroll the vertical scroll bar for the rows
   */
  VerticalScrollListener(DefaultTableModel table, DefaultTableModel rows, JScrollBar rowScroll,
                         Features controller) {
    super();
    this.table = table;
    this.scrollMaxCount = 0;
    this.rows = rows;
    this.rowScroll = rowScroll;
    this.controller = controller;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the current value of the vertical scroll bar is at greater than max it has been
    if (e.getValue() >= scrollMaxCount) {
      Integer[] addedRow = new Integer[table.getColumnCount()];
      Integer[] addedHeader = new Integer[table.getColumnCount()];
      table.addRow(addedRow);
      rows.addRow(addedHeader);
      for (int i = 1; i < rows.getRowCount() + 1; i++) {
        rows.setValueAt(i, i - 1, 0);
      }


      scrollMaxCount = e.getValue(); // set new max it has scrolled to current value

    }

    // if it is the editable version
    if (controller != null) {
      controller.onScroll();
    }


    // scrolling the row headers (setting to the value of the table scroll)
    rowScroll.setValue(e.getValue());
  }

}
