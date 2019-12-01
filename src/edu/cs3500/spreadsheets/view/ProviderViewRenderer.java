package edu.cs3500.spreadsheets.view;

import javax.swing.*;

/**
 * Well fudge
 */
public class ProviderViewRenderer {

  private JFrame frame;

  public ProviderViewRenderer(ProviderViewExtender view) {
    frame = new JFrame();
    frame.add(view);
  }

  public void render() {
    frame.setVisible(true);
    frame.setTitle("Spreadsheet");
    frame.setSize(1000, 1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


}
