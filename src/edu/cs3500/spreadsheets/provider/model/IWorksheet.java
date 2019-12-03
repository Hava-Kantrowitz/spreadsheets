package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * NOTE: This Javadoc was not written by the providers, placed by us for the style.
 */
public interface IWorksheet {

  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  void createCell(int col, int row, String s);

  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  String evaluate(Coord c);

  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  List<Coord> allCells();

  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  public Sexp getCellFrom(Coord c);

}
