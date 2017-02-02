package net.cabezudo.json;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 09/16/2016
 */
public class Position {

  public static final Position INITIAL = new Position(1, 1);
  private final int row;
  public final int line;

  Position(int line, int row) {
    this.line = line;
    this.row = row;
  }

  /**
   * Return a primitive {@code int} with the line number of the position.
   *
   * @return the line number for the position.
   */
  public int getLine() {
    return line;
  }

  /**
   * Return a primitive {@code int} with the row number of the position.
   *
   * @return the row number for the position.
   */
  public int getRow() {
    return row;
  }

  @Override
  public String toString() {
    return " Line " + getLine() + ", row " + getRow();
  }
}
