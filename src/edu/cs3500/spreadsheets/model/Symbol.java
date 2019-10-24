package edu.cs3500.spreadsheets.model;

public class Symbol implements Cell {

  private Object evalVal;

  public Symbol(String evalVal) {
    this.evalVal = evalVal;
  }

  @Override
  public Value evaluateCell() {
    return null;
  }

  @Override
  public boolean equals(Object otherCell){
    boolean isEqual = false;
    // checking if the other cell is a blank cell too
    if(otherCell instanceof Symbol && ((Symbol) otherCell).evalVal.equals(this.evalVal)){
      isEqual = true;
    }
    return isEqual;
  }
}