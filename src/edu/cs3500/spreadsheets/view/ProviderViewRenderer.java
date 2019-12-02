package edu.cs3500.spreadsheets.view;

import javax.swing.JFrame;

/**
 * Decorator class to allow provider's view to render.
 */
public class ProviderViewRenderer {

  private JFrame frame;

  /**
   * Takes the provider's JPanel view and adds it to a JFrame for display purposes.
   * @param view the provider's JPanel view
   */
  public ProviderViewRenderer(ProviderViewExtender view) {
    frame = new JFrame();
    frame.add(view);
  }

  /**
   * Renders the JFrame as drawn in the provider's JPanel.
   */
  public void render() {
    frame.setVisible(true);
    frame.setTitle("Spreadsheet");
    frame.setSize(1000, 1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


}
