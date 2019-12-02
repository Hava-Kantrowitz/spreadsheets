package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents an IViewWorksheet.
 * This worksheet can be accessed in the view classes without modifying the model.
 */
public interface IViewWorksheet {

  /*
        evaluate:  evaluates the cell at a given Coord.
        This method locates the requested cell using the coord, evaluates it with EvalVisitor(),
        and then converts it to a string.  This method returns the contents of a cell as a String.

        @param c        the given Coord which specifies which cell to evaluate
     */
  public String evaluate(Coord c);

  /*
      AllCells():  returns all the cells in this class.
   */
  public List<Coord> allCells();

  /*
      CreateCell:  creates a cell and adds it to the models grid

      @param col        the Coord col location of the new cell
      @param row        the Coord row location of the new cell
      @param sexp       the Sexp value to be entered into the new cell
   */

  public Sexp getCellFrom(Coord c);

  public String getStringCell(Coord c);
}
