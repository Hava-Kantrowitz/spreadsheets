import javax.swing.*;

/**
 * Creates a mock table which always selects cell A1.
 */
public class MockTable extends JTable {

  @Override
  public int getSelectedRow() {
    return 1;
  }

  @Override
  public int getSelectedColumn() {
    return 1;
  }

}
