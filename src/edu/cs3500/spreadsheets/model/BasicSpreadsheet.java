package edu.cs3500.spreadsheets.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BasicSpreadsheet implements Spreadsheet {

  private ArrayList<ArrayList<Cell>> sheet;

  /**
   * This is the blank constructor for a basic spreadsheet. Constructs a 10 by 10 spreadsheet
   * of blank cells.
   */
  public BasicSpreadsheet(){
    sheet = new ArrayList<>();  // initialize the overall array list

    // goes through the columns and rows
    for(int i = 0; i < 10; i++){
      sheet.add(new ArrayList<>());  // adds a new row (initialize each array list)
      for(int j = 0; j < 10; j++){
        // adds an element to the new row
        sheet.get(i).add(new Blank());
      }
    }
  }

  /**
   * This is the constructor that takes in a file and creates the model.
   * @param fileName the name of the initial file.
   */
  public BasicSpreadsheet(String fileName){
    initializeSpreadsheet(fileName);
  }


  @Override
  public Cell getCellAt(Coord coord) {
    return null;
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {

  }

  @Override
  public List<Cell> getCellSection(Coord coord, Coord cord) {
    return null;
  }


  @Override
  public void evaluateSheet() {

  }

  @Override
  public void evaluateCellAt(Coord coord){

  }

  /**
   * This is the method to initialize the spreadsheet of the board from the file
   */
  private void initializeSpreadsheet(String fileName){

  }

  /**
   * This is the method to expand the spreadsheet when the coordinates selected are out of bounds.
   */
  private void expandSheet() {

  }
}
