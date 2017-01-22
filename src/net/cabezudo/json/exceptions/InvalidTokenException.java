package net.cabezudo.json.exceptions;

import net.cabezudo.json.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 01/21/2016
 * @serial exclude
 */
public class InvalidTokenException extends JSONParseException {

  private static final long serialVersionUID = -5344150067571800301L;

  /**
   *
   * @param message
   * @param cause
   * @param position
   */
  public InvalidTokenException(String message, Throwable cause, Position position) {
    super(message, cause, position);
  }

  /**
   *
   * @param message
   * @param position
   */
  public InvalidTokenException(String message, Position position) {
    super(message, position);
  }
}
