package net.cabezudo.java.json.exceptions;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 06/04/2014
 * @serial exclude
 */
public class ParseException extends Exception {

  private static final long serialVersionUID = -1056432009939512679L;

  private final Integer position;

  /**
   *
   * @param message
   * @param cause
   */
  public ParseException(String message, Throwable cause) {
    super(message, cause);
    this.position = null;
  }

  /**
   *
   * @param message
   * @param cause
   * @param position
   */
  public ParseException(String message, Throwable cause, Integer position) {
    super(message, cause);
    this.position = position;
  }

  /**
   *
   * @param message
   * @param position
   */
  public ParseException(String message, Integer position) {
    super(message);
    this.position = position;
  }

  /**
   *
   * @return
   */
  public Integer getPosition() {
    return position;
  }
}
