package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;

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
    SpreadsheetPanel spreadsheetPanel = new SpreadsheetPanel(model);
    spreadsheetPanel.setPreferredSize(new Dimension(500, 500));
    JScrollPane scrollPane = new JScrollPane(table.getTable());
    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(model);
    view.setVisible(true);


  }
}
