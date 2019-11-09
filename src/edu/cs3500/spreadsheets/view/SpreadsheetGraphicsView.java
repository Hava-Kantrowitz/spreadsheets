package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;

public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  BasicSpreadsheet model;

  public SpreadsheetGraphicsView(BasicSpreadsheet model) {

    super();
    this.model = model;
    this.setTitle("Spreadsheet");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setResizable(false);

    SpreadsheetTable table = new SpreadsheetTable(model);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    JTable sheet = table.getTable();

    sheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    DefaultTableCellRenderer rowRenderer = new DefaultTableCellRenderer();
    rowRenderer.setBackground(Color.LIGHT_GRAY);
    sheet.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    for (int i = 0; i < sheet.getColumnCount(); i++) {
      sheet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    rowRenderer.setBackground(Color.LIGHT_GRAY);
    rowRenderer.setHorizontalAlignment(JLabel.CENTER);
    sheet.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);

    JScrollPane scrollPane = new JScrollPane(sheet);
    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(model);
    view.setVisible(true);


  }
}
