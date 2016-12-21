package net.cabezudo.java.json.exceptions;

import net.cabezudo.java.json.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/09/2015
 * @serial exclude
 */
public class PropertyNotExistException extends Exception {

  private static final long serialVersionUID = 6985635990014405620L;

  private final Position position;

  public PropertyNotExistException(String message, Position position) {
    super(message);
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }
}
