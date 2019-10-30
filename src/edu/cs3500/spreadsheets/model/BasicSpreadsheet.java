package edu.cs3500.spreadsheets.model;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import edu.cs3500.spreadsheets.sexp.BasicSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

public class BasicSpreadsheet implements Spreadsheet {

  private static final int MAXINT = 10000000;
  private ArrayList<ArrayList<Cell>> sheet;
  private int numRows;
  private int numCols;
  String fileName;

  /**
   * This is the blank constructor for a basic spreadsheet. Constructs a 10 by 10 spreadsheet of
   * blank cells.
   */
  public BasicSpreadsheet() {

  }

  @Override
  public void initializeSpreadsheet(String fileName) {
    sheet = new ArrayList<ArrayList<Cell>>();
    FileReader fr;

    try {
      fr = new FileReader(fileName);  // get the given file
    }
    catch(FileNotFoundException e){
      throw new IllegalArgumentException("The given file does not exist");
    }
    // scan the file in
    Scanner scan = new Scanner(fr);
    Parser parser = new Parser();
    Coord givenCoord;
    String coordAsString;
    String currLine;


    while(scan.hasNextLine()){
      currLine = scan.nextLine();
      Scanner scanLine = new Scanner(currLine);

      String firstInput = scanLine.next();

      char[] cellName = firstInput.toCharArray();

      String actualFirstInput = "";


      for(int i = 0; i < cellName.length; i++){
        if(Character.isLetter(cellName[i])){
          actualFirstInput = actualFirstInput + cellName[i];
          if(Character.isDigit(cellName[i+1])){
            actualFirstInput = actualFirstInput +" ";
          }
        }
        else if(Character.isDigit(cellName[i])){
          actualFirstInput = actualFirstInput + cellName[i];
        }
      }

      Scanner colAndRow = new Scanner(actualFirstInput);
      String givenCol = colAndRow.next();
      int givenRow = colAndRow.nextInt();

      givenCoord = new Coord(Coord.colNameToIndex(givenCol),givenRow);

      System.out.println(givenCoord.col);
      System.out.println(givenCoord.row);

      String secondInput = scanLine.next();
      if(!secondInput.equals("=")){
        Sexp element = Parser.parse(secondInput);
        SexpVisitor visitor = new BasicSexpVisitor();
        Cell addedCell = (Cell) element.accept(visitor);
        System.out.println(addedCell.toString());
        this.setCellAt(givenCoord, addedCell);
        numRows = sheet.size();
        numCols = sheet.get(0).size();
      }


    }



  }


  @Override
  public Cell getCellAt(Coord coord) {
    int givenCol = coord.col - 1;
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol >= numCols || givenRow >= numRows) {
      expandSheet(givenCol, givenRow);
    }

    return sheet.get(givenRow).get(givenCol);
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    int givenCol = coord.col - 1;  // adjust for the 1 based indexing
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol >= numCols || givenRow >= numRows) {
      expandSheet(givenCol, givenRow);
    }
    // get the given row then set the column of that row
    sheet.get(givenRow).set(givenCol, cellVal);

  }

  @Override
  public ArrayList<Cell> getCellSection(Coord coord, Coord cord) {
    int givenCol1 = coord.col - 1;
    int givenRow1 = coord.row - 1;
    int givenCol2 = cord.col - 1;
    int givenRow2 = cord.row - 1;

    if (givenCol2 < givenCol1 || givenRow2 < givenRow1) {
      throw new IllegalArgumentException("Cannot find cell range");
    }

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol1 >= numCols || givenRow1 >= numRows) {
      expandSheet(givenCol1, givenRow1);
    }

    if (givenCol2 >= numCols || givenRow2 >= numRows) {
      expandSheet(givenCol2, givenRow2);
    }

    ArrayList multRows = new ArrayList<>();

    for (int i = givenRow1; i <= givenRow2; i++) {
      for (int j = givenCol1; j <= givenCol2; j++) {
        multRows.add(sheet.get(i).get(j));
      }

    }

    return multRows;
  }


  @Override
  public void evaluateSheet() {

    for (int i = 1; i < numCols; i++) {
      for (int j = 1; j < numRows; j++) {
        Coord currCor = new Coord(i, j);
        getCellAt(currCor).evaluateCell();
      }
    }

  }

  @Override
  public void evaluateCellAt(Coord coord) throws IllegalArgumentException {
    getCellAt(coord).evaluateCell();
  }



  /**
   * This is the method to expand the spreadsheet when the coordinates selected are out of bounds.
   * If the column is out of bounds then expand column wise, same with row. If both are out of
   * bounds expand in both directions. When new cells are added they should be blank.
   *
   * @param inputCol the column that was input
   * @param inputRow the row that was input
   */
  private void expandSheet(int inputCol, int inputRow) {

    if (inputCol >= MAXINT) {
      while (sheet.size() <= MAXINT) {
        sheet.add(new ArrayList<Cell>());
      }
    }

    if (inputRow >= MAXINT) {
      for (int i = 0; i <= MAXINT; i++) {
        while (sheet.get(i).size() <= inputRow) {
          sheet.get(i).add(new Blank());
        }
      }
    } else {
      sheet.ensureCapacity(inputRow);

      while (sheet.size() <= inputRow) {
        sheet.add(new ArrayList<Cell>());
      }

      for (int i = 0; i <= inputRow; i++) {
        sheet.get(i).ensureCapacity(inputCol);
        while (sheet.get(i).size() <= inputCol) {
          sheet.get(i).add(new Blank());
        }
      }
    }


  }

  // put the set cell here
  @Override
  public WorksheetReader.WorksheetBuilder createCell(int col, int row, String contents) {
    return null;
  }

  @Override
  public Object createWorksheet() {
    Spreadsheet spreadsheet = new BasicSpreadsheet();
    return spreadsheet;
  }
}
