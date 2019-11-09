package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * This is the class for a scroll specifically for a spreadsheet.
 */
public class SpreadsheetScrollListener implements AdjustmentListener {

  JScrollPane scrollPane;
  SpreadsheetTable table;

  /**
   * This constructs a new spreadsheet scroll listener
   */
  public SpreadsheetScrollListener(JScrollPane scrollPane, SpreadsheetTable table){
    super();
    this.scrollPane = scrollPane;
    this.table = table;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the value of the bar is at the maximum
    // creates new columns if so
      if(e.getValue() == scrollPane.getHorizontalScrollBar().getMaximum()){
        TableColumn addCol = new TableColumn();
        table.addColumn("hi");
        

      }
  }
}
