package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;
import edu.cs3500.spreadsheets.provider.view.IView;
import edu.cs3500.spreadsheets.provider.view.JPanelCell;
import edu.cs3500.spreadsheets.provider.view.JPanelGrid;

/**
 * UHH GOTTA HEAVILY JAVADOC THE REASONING OF THIS CLASS
 */
public class ProviderViewExtender extends JPanelGrid implements IView {

  public ProviderViewExtender(IViewWorksheet model) {
    super(model);
  }

  @Override
  public void setLabel(String s) {

  }

  @Override
  public void initialize(IViewWorksheet model, int width, int height) {

  }

  @Override
  public void display() {

  }

  @Override
  public JPanelCell getCell(Coord c) {
    return null;
  }

  @Override
  public void setToolbar(String s) {

  }
}
