package edu.cs3500.spreadsheets.provider.view;

import java.awt.event.ActionListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * NOTE: This Javadoc was not written by the providers, placed by us for the style.
 */
public interface IView {

  /**
   * SetLabel modifies the label of a JFRAME.
   *
   * @param s The new string to become the label.
   */
  void setLabel(String s);

  /**
   * Initializes the view with a model and window dimensions.
   *
   * @param model  The WorksheetModel storing the cell information.
   * @param width  The width of the View window.
   * @param height The height of the View window.
   */
  public void initialize(IViewWorksheet model, int width, int height);

  /**
   * Displays the view.
   */
  void display();

  /**
   * Adds an actionListener to this JPanel.
   * @param mouseListener       the actionListener to be added.
   */
  void addActionListener(ActionListener mouseListener);

  JPanelCell getCell(Coord c);

  void setToolbar(String s);
}
