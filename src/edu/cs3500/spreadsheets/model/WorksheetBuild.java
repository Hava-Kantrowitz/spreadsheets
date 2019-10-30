package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.sexp.BasicSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * This is the class to build the worksheet.
 */
public class WorksheetBuild implements WorksheetReader.WorksheetBuilder<BasicSpreadsheet> {

  BasicSpreadsheet sheet;



  /**
   * This is the constructor for a worksheet builder.
   */
  public WorksheetBuild(BasicSpreadsheet sheet){
    // this is the empty constructor for the worksheet
    this.sheet = sheet;

  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicSpreadsheet>
  createCell(int col, int row, String contents) {
    char[] arrayForm = contents.toCharArray();

    // checking if it is a formula to get only the s expression
    // NEED TO DEAL WITH IF IT HAS EQUALS BUT IT IS A VALUE
    if(arrayForm[0] == '='){
      contents = contents.substring(1,contents.length());
    }
    Coord coord = new Coord(col,row);
    SexpVisitor visit = new BasicSexpVisitor();
    Cell addedCell = (Cell) Parser.parse(contents).accept(visit);
    sheet.setCellAt(coord, addedCell);


    return this;
  }

  @Override
  public BasicSpreadsheet createWorksheet() {
    return sheet;
  }
}
