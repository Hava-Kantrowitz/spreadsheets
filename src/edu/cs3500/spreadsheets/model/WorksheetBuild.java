package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.BasicSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * This is the class to build the worksheet.
 */
public class WorksheetBuild implements WorksheetReader.WorksheetBuilder<Spreadsheet> {

  Spreadsheet sheet = new BasicSpreadsheet();

  @Override
  public WorksheetReader.WorksheetBuilder<Spreadsheet>
  createCell(int col, int row, String contents) {
    if(contents.toCharArray()[0] == '='){

    }
    Coord coord = new Coord(col,row);
    SexpVisitor visit = new BasicSexpVisitor();
    Cell addedCell = (Cell) Parser.parse(contents).accept(visit);
    sheet.setCellAt(coord, addedCell);

    return this;
  }

  @Override
  public Spreadsheet createWorksheet() {
    return sheet;
  }
}
