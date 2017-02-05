/**
 * MIT License
 *
 * Copyright (c) 2017 Esteban Cabezudo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.cabezudo.json.exceptions;

import net.cabezudo.json.Position;

/**
 * Thrown when an invalid token if found while parsing a JSON string.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.9, 01/21/2016
 */
public class InvalidTokenException extends JSONParseException {

  private static final long serialVersionUID = -5344150067571800301L;

  /**
   * Constructs a {@link net.cabezudo.json.exceptions.InvalidTokenException} with a specified detail
   * message, a cause, and a {@link net.cabezudo.json.Position}. The position is used to store a
   * position of the property in a source in order to search the misspelled property.
   *
   * @param message the detail message
   * @param position the position to store.
   */
  public InvalidTokenException(String message, Position position) {
    super(message, position);
  }

  /**
   * Constructs a {@link net.cabezudo.json.exceptions.InvalidTokenException} with a series of values
   * to create a detail message, a cause, and a {@link net.cabezudo.json.Position}. The position is
   * used to store a position of the property in a source in order to search the misspelled
   * property.
   *
   * @param expected the value expected for the token
   * @param value the value of the token
   * @param cause The cause (which is saved for later retrieval by the
   * {@link java.lang.Throwable#getCause()} method. (A null value is permitted, and indicates that
   * the cause is nonexistent or unknown.)
   * @param position the position to store.
   */
  public InvalidTokenException(String expected, String value, Throwable cause, Position position) {
    super("Unexpected token. Waiting for a " + expected + " and have a " + value + ".", cause, position);
  }

  /**
   * Constructs a {@link net.cabezudo.json.exceptions.InvalidTokenException} with a specified detail
   * message, and a {@link net.cabezudo.json.Position}. The position is used to store a position of
   * the property in a source in order to search the misspelled property.
   *
   * @param expected the value expected for the token
   * @param value the value of the token
   * @param position the position to store.
   */
  public InvalidTokenException(String expected, String value, Position position) {
    this(expected, value, null, position);
  }
}
