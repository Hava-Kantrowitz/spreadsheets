package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Adapter to allow our model to be used with the provider's view.
 */
public class ProviderAdapter extends BasicSpreadsheet implements IViewWorksheet {

  private BasicSpreadsheet sheet;

  /**
   * Constructs an instance of the adapter.
   * @param sheet our model to be adapted
   */
  public ProviderAdapter(BasicSpreadsheet sheet) {
    this.sheet = sheet;
  }

  @Override
  public String evaluate(Coord c) {
    sheet.evaluateCellAt(c);
    return "";
  }

  @Override
  public List<Coord> allCells() {
    List<Coord> coordList = new ArrayList<>();
    coordList.addAll(sheet.getListCoords());
    return coordList;
  }

  @Override
  public Sexp getCellFrom(Coord c) {
    return (Sexp) sheet.getCellAt(c);
  }

  @Override
  public String getStringCell(Coord c) {
    return sheet.getCellAt(c).toString();
  }
}
