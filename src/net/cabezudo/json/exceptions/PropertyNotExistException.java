package net.cabezudo.json.exceptions;

import net.cabezudo.json.Position;

/**
 * Thrown when an application tried to get a non existent property from an object.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 *
 * @version 1.0
 * @since 1.7
 * @date 08/09/2015
 * @serial exclude
 */
public class PropertyNotExistException extends Exception {

  private static final long serialVersionUID = 6985635990014405620L;

  private final Position position;

  /**
   * Constructs a {@code PropertyNotExistException} with the specified detail message and a
   * {@link Position}. The position is used to store a position of the property in a source in order
   * to search the misspelled property.
   *
   * @param message the detail message.
   * @param position the position to store.
   */
  public PropertyNotExistException(String message, Position position) {
    super(message);
    this.position = position;
  }

  /**
   * Retrieve a {@link Position} object used to store additional data about the exception.
   *
   * @return the {@link Position} stored in the exception.
   */
  public Position getPosition() {
    return position;
  }
}
