package net.cabezudo.json.exceptions;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 01/22/2016
 * @serial exclude
 */
public class ObjectException extends RuntimeException {

  private static final long serialVersionUID = -3601249847252864843L;

  /**
   *
   * @param message
   * @param cause
   */
  public ObjectException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   *
   * @param message
   */
  public ObjectException(String message) {
    super(message);
  }

  /**
   *
   * @param cause
   */
  public ObjectException(Throwable cause) {
    super(cause);
  }
}
