package net.cabezudo.java.json.exceptions;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 07/06/2016
 * @serial exclude
 */
public class NotPropertiesException extends RuntimeException {

  private static final long serialVersionUID = -6963919273551386164L;

  /**
   *
   * @param message
   */
  public NotPropertiesException(String message) {
    super(message);
  }
}
