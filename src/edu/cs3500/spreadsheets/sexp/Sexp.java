package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * <p>Represents an s-expression.  An s-expression is one of</p>
 *
 * <ul>
 *   <li>A boolean</li>
 *   <li>A number</li>
 *   <li>A String</li>
 *   <li>A symbol</li>
 *   <li>A list of s-expressions</li>
 * </ul>
 *
 * <p>
 *   S-expressions are a general-purpose datatype, and so have no methods of their own.
 *   All processing on s-expressions is done through the visitor pattern: see {@link SexpVisitor}.
 * </p>
 */
public interface Sexp {
  /**
   * This is the method to accept the visitor to the Sexp.
   * @param visitor the given visitor
   * @param sheet the spreadsheet related to the cells being created
   * @param rawContent the raw content of the cell
   * @param <R> Cell object
   * @return the cell object of the specified information
   */
  <R> R accept(SexpVisitor<R> visitor, Spreadsheet sheet, String rawContent);  // ADD JAVA DOC
}
