package net.cabezudo.java.json;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 09/16/2016
 */
public class Position {

  public static final Position INITIAL = new Position(1, 1);
  public final int line;
  private final int row;

  Position(int line, int row) {
    this.line = line;
    this.row = row;
  }

  public int getLine() {
    return line;
  }

  public int getRow() {
    return row;
  }
}
