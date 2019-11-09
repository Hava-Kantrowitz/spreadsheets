package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the class for a scroll specifically for a spreadsheet.
 */
public class HorizontalScrollListener implements AdjustmentListener {

  JScrollPane scrollPane;
  DefaultTableModel table;

  /**
   * This constructs a new spreadsheet scroll listener.
   */
  public HorizontalScrollListener(JScrollPane scrollPane, DefaultTableModel table){
    super();
    this.scrollPane = scrollPane;
    this.table = table;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
      // checks if the current value of the horizontal scroll bar is at max (subtraction accounts
      // for the difference in max and how far scroll goes
      if(e.getValue() == scrollPane.getHorizontalScrollBar().getMaximum() - 981){

        // getting the name of the new column
        String colName = Coord.colIndexToName(table.getColumnCount());
//        TableColumn addCol = new TableColumn();     // creates a new column
//        addCol.setHeaderValue(colName);             // put the name
//        table.addColumn(addCol);                    // adds the new column

        table.addColumn(colName);  // adds the column to default table


      }
  }
}
