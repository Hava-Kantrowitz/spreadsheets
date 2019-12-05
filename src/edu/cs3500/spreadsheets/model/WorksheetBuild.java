package edu.cs3500.spreadsheets.model;

/**
 * This is the class to build the worksheet.
 */
public class WorksheetBuild implements WorksheetReader.WorksheetBuilder<BasicSpreadsheet> {

  private BasicSpreadsheet sheet;


  /**
   * This is the constructor for a worksheet builder.
   */
  WorksheetBuild(BasicSpreadsheet sheet) {
    // this is the empty constructor for the worksheet
    this.sheet = sheet;

  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicSpreadsheet> createCell(int col, int row, String c) {

    Coord coord = new Coord(col, row);
    sheet.setCellAt(coord, c);

    return this;
  }

  @Override
  public BasicSpreadsheet createWorksheet() {
    return sheet;
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicSpreadsheet>
  addRowColSize(String rowOrCol, int index, String changedSize) {
    try {
      if (rowOrCol.equals("R")) {
        sheet.addChangedRow(index, Integer.parseInt(changedSize));
      } else if (rowOrCol.equals("C")) {
        sheet.addChangedCol(index, Integer.parseInt(changedSize));
      } else {
        throw new IllegalStateException("Invalid row/column specification.");
      }
    }
    catch(NumberFormatException e){
      throw new IllegalStateException("Invalid row/column index");
    }
    return this;

  }
}
