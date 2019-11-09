package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import edu.cs3500.spreadsheets.model.Coord;

public class VerticalScrollListener implements AdjustmentListener {


    JScrollPane scrollPane;
    DefaultTableModel table;

    /**
     * This constructs a new spreadsheet scroll listener.
     */
  public VerticalScrollListener(JScrollPane scrollPane, DefaultTableModel table){
      super();
      this.scrollPane = scrollPane;
      this.table = table;
    }


    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
      // checks if the current value of the vertical scroll bar is at max (subtraction accounts
      // for the difference in max and how far scroll goes

      System.out.println(scrollPane.getVerticalScrollBar().getMaximum());
      System.out.println(e.getValue());

      if(e.getValue() == scrollPane.getVerticalScrollBar().getMaximum() - 755){
//        System.out.println("in here");
//        System.out.println(scrollPane.getVerticalScrollBar().getMaximum());
        Integer currRow = table.getRowCount();
//        table.addRowSelectionInterval(currRow-1, currRow -1);
        table.addRow(new Integer[table.getColumnCount()]);
      }
    }

}
