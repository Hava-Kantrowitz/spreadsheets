package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;
import edu.cs3500.spreadsheets.provider.view.IView;
import edu.cs3500.spreadsheets.provider.view.JPanelCell;
import edu.cs3500.spreadsheets.provider.view.JPanelGrid;

/**
 * Decorator class to allow the provider's grid class to implement their interface. The interface
 * methods are never used, and therefore left as stub implementations.
 */
public class ProviderViewExtender extends JPanelGrid implements IView {

  /**
   * Sends the model to the provider's grid class in order to construct the view.
   *
   * @param model the model of the sheet
   */
  public ProviderViewExtender(IViewWorksheet model) {
    super(model);
  }

  @Override
  public void setLabel(String s) {
    //stub implementation for provider, interface never used
  }

  @Override
  public void initialize(IViewWorksheet model, int width, int height) {
    //stub implementation for provider, interface never used
  }

  @Override
  public void display() {
    //stub implementation for provider, interface never used
  }

  @Override
  public JPanelCell getCell(Coord c) {
    return null;
  }

  @Override
  public void setToolbar(String s) {
    //stub implementation for provider, interface never used
  }
}
