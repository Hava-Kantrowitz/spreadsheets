package edu.cs3500.spreadsheets.model;

/**
 * Models a blank cell.
 */
public class Blank implements Cell {

  /**
   * This is an empty constructor for an instance of Blank.
   */
  public Blank() {
    //this is an empty constructor because the blank class does not contain anything,
    //it is a placeholder.
  }

  @Override
  public Value evaluateCell() {
    return null;
  }
}
