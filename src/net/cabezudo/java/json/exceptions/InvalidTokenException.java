package net.cabezudo.java.json.exceptions;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 01/21/2016
 * @serial exclude
 */
public class InvalidTokenException extends RuntimeException {

  private static final long serialVersionUID = -5344150067571800301L;

  /**
   *
   * @param message
   */
  public InvalidTokenException(String message) {
    super(message);
  }
}
