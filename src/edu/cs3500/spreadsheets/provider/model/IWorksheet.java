package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.sexp.Sexp;

public interface IWorksheet {

  void createCell(int col, int row, String s);

  String evaluate(Coord c);

  List<Coord> allCells();

  public Sexp getCellFrom(Coord c);

}
