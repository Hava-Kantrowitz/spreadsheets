package edu.cs3500.spreadsheets.sexp;

import java.util.Objects;

import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * An arbitrary symbol.
 */
public class SSymbol implements Sexp {
  String name;

  public SSymbol(String name) {
    this.name = Objects.requireNonNull(name);
  }

  @Override
  public <R> R accept(SexpVisitor<R> visitor, Spreadsheet sheet) {
    return visitor.visitSymbol(this.name, sheet);
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SSymbol sSymbol = (SSymbol) o;
    return name.equals(sSymbol.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
