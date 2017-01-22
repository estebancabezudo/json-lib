package net.cabezudo.json.exceptions;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 01/21/2016
 * @serial exclude
 */
public class JSONCastException extends RuntimeException {

  private static final long serialVersionUID = 4633405861210769003L;

  /**
   *
   * @param message
   * @param cause
   */
  public JSONCastException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   *
   * @param message
   */
  public JSONCastException(String message) {
    super(message);
  }
}
