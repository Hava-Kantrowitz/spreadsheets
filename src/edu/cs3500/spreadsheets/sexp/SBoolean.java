package edu.cs3500.spreadsheets.sexp;

import java.util.Objects;


/**
 * A boolean constant {@link Sexp}.
 */
public class SBoolean implements Sexp {
  boolean val;

  SBoolean(boolean val) {
    this.val = val;
  }

  @Override
  public <R> R accept(SexpVisitor<R> visitor, String rawContent) {
    return visitor.visitBoolean(this.val, rawContent);
  }

  @Override
  public String toString() {
    return Boolean.toString(this.val);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SBoolean sBoolean = (SBoolean) o;
    return val == sBoolean.val;
  }

  @Override
  public int hashCode() {
    return Objects.hash(val);
  }
}
