package edu.cs3500.spreadsheets.model;

/**
 * This is the class to build the worksheet.
 */
public class WorksheetBuild implements WorksheetReader.WorksheetBuilder<BasicSpreadsheet> {

  BasicSpreadsheet sheet;


  /**
   * This is the constructor for a worksheet builder.
   */
  public WorksheetBuild(BasicSpreadsheet sheet) {
    // this is the empty constructor for the worksheet
    this.sheet = sheet;

  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicSpreadsheet> createCell(int col, int row, String contents) {

    Coord coord = new Coord(col, row);
    sheet.setCellAt(coord, contents);

    return this;
  }

  @Override
  public BasicSpreadsheet createWorksheet() {
    return sheet;
  }
}
