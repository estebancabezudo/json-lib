package net.cabezudo.java.json.exceptions;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/09/2015
 * @serial exclude
 */
public class PropertyNotExistException extends Exception {

  private static final long serialVersionUID = 6985635990014405620L;

  /**
   *
   * @param message
   */
  public PropertyNotExistException(String message) {
    super(message);
  }
}
