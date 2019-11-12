package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the class for a scroll specifically for a spreadsheet.
 */
public class HorizontalScrollListener implements AdjustmentListener {

  private JScrollPane scrollPane;
  private SpecialTableModel table;
  private int scrollMaxCount;

  /**
   * This constructs a new spreadsheet scroll listener.
   */
  HorizontalScrollListener(JScrollPane scrollPane, DefaultTableModel table) {
    super();
    this.scrollPane = scrollPane;
    this.table = (SpecialTableModel) table;
    this.scrollMaxCount = 0;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the current value of the horizontal scroll bar is at max (subtraction accounts
    // for the difference in max and how far scroll goes
    if (e.getValue() >= scrollMaxCount) {

      // getting the name of the new column
      String colName = Coord.colIndexToName(table.getColumnCount());

      table.addColumn(colName);  // adds the column to default table

      scrollMaxCount = e.getValue(); // set new max
    }
  }
}
