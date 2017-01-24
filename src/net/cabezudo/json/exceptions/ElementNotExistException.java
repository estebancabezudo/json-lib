package net.cabezudo.json.exceptions;

import net.cabezudo.json.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 01/23/2017
 * @serial exclude
 */
public class ElementNotExistException extends Exception {

  private static final long serialVersionUID = 6985635990014405620L;

  private final Position position;

  public ElementNotExistException(String message, Position position) {
    super(message);
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }
}
