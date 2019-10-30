package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * An abstracted function object for processing any {@link Sexp}ressions.
 * @param <R> The return type of this function
 */
public interface SexpVisitor<R> {
  /**
   * Process a boolean value.
   * @param b the value
   * @param rawContent the raw input to the cell
   * @return the desired result
   */
  R visitBoolean(boolean b, String rawContent);

  /**
   * Process a numeric value.
   * @param d the value
   * @param rawContent the raw input to the cell
   * @return the desired result
   */
  R visitNumber(double d, String rawContent);

  /**
   * Process a list value.
   * @param l the contents of the list (not yet visited)
   * @param sheet the given spreadsheet
   * @param rawContent the raw input to the cell
   * @return the desired result
   */
  R visitSList(List<Sexp> l, Spreadsheet sheet, String rawContent);

  /**
   * Process a symbol.
   * @param s the value
   * @param sheet the given spreadsheet
   * @param rawContent the raw input to the cell
   * @return the desired result
   */
  R visitSymbol(String s, Spreadsheet sheet, String rawContent);

  /**
   * Process a string value.
   * @param s the value
   * @param rawContent the raw input to the cell
   * @return the desired result
   */
  R visitString(String s, String rawContent);
}
