import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.view.NoEditTable;

/**
 * Creates a mock table which always selects cell B2.
 */
public class MockTable extends NoEditTable {

  /**
   * The constructor for a non editable form of a jtable.
   *
   * @param model the default table model to create from
   */
  MockTable(DefaultTableModel model) {
    super(model);
  }

  @Override
  public int getSelectedRow() {
    return 1;
  }

  @Override
  public int getSelectedColumn() {
    return 1;
  }

}
