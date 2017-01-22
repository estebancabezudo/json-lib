package net.cabezudo.json.exceptions;

import net.cabezudo.json.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 06/04/2014
 * @serial exclude
 */
public class JSONParseException extends Exception {

  private static final long serialVersionUID = -1056432009939512679L;

  private final Position position;

  /**
   *
   * @param message
   * @param cause
   * @param position
   */
  public JSONParseException(String message, Throwable cause, Position position) {
    super(message, cause);
    this.position = position;
  }

  /**
   *
   * @param message
   * @param position
   */
  public JSONParseException(String message, Position position) {
    super(message);
    this.position = position;
  }

  /**
   *
   * @return
   */
  public Position getPosition() {
    return position;
  }
}
