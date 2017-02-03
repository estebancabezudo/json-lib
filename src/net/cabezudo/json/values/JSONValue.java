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
package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.json.JSONElement;
import net.cabezudo.json.Position;
import net.cabezudo.json.exceptions.JSONConvertionException;

/**
 * The {code JSONValue} class is an abstract class that represents a JSON attribute. A JSON value
 * extends from {@link net.cabezudo.json.JSONElement} and can be used in a attribute-value pair or
 * in an array. There are seven types for values: string, number, object, array, true, false, and
 * null.
 *
 * <p>
 * The class also provides additional default methods for implementing a concrete object value and
 * the default type conversions. (OOLV define:concrete value)
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @param <T> the type of elements in this list
 * @version 1.00, 10/01/2014
 */
public abstract class JSONValue<T> extends JSONElement implements Comparable<T> {

  public JSONValue(Position position) {
    super(position);
  }

  /**
   * Returns if the JSON element is a value. The method override the
   * {@link net.cabezudo.json.JSONElement#isValue()} in order to indicate that all the object
   * derived from this are values. Not all the element of a JSON structure are values, some jus are
   * elements of the structure, like an object, a pair or an array. This object represent a JSON
   * value, so always return a true. (OOLV When is not a value? Define: value)
   *
   * @return {@code true} because the object is a JSON value.
   */
  @Override
  public boolean isValue() {
    return true;
  }

  /**
   * This method implements the default behavior to convert a value to an array of JSON values. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return an array of {@link JSONValue} object if the conversion is possible.
   */
  public JSONValue[] toArray() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a JSONValue array.");
  }

  /**
   * This method implements the default behavior to convert a value to a {code BigDecimal} object.
   * The default behavior is to throw a cast exception. Each class that implements a value may have
   * a different behavior so it could override this method.
   *
   * @return a {@code BigDecimal} object if the conversion is possible.
   */
  public BigDecimal toBigDecimal() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a BigDecimal");
  }

  /**
   * This method implements the default behavior to convert a value to a {code BigInteger} object.
   * The default behavior is to throw a cast exception. Each class that implements a value may have
   * a different behavior so it could override this method.
   *
   * @return a {@code BigInteger} object if the conversion is possible.
   */
  public BigInteger toBigInteger() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a BigInteger");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Boolean} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Boolean} object if the conversion is possible.
   */
  public Boolean toBoolean() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Boolean");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Byte} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Byte} object if the conversion is possible.
   */
  public Byte toByte() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Byte");
  }

  /**
   * This method implements the default behavior to convert a value to an array of primitives {code
   * byte} object. The default behavior is to throw a cast exception. Each class that implements a
   * value may have a different behavior so it could override this method.
   *
   * @return an array of {@code byte} object if the conversion is possible.
   */
  public byte[] toByteArray() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a ByteArray");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Calendar} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Calendar} object if the conversion is possible.
   */
  public Calendar toCalendar() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Calendar");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Character} object.
   * The default behavior is to throw a cast exception. Each class that implements a value may have
   * a different behavior so it could override this method.
   *
   * @return a {@code Character} object if the conversion is possible.
   */
  public Character toCharacter() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Character");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Double} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Double} object if the conversion is possible.
   */
  public Double toDouble() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Double");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Float} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Float} object if the conversion is possible.
   */
  public Float toFloat() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Float");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Integer} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Integer} object if the conversion is possible.
   */
  public Integer toInteger() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to an Integer");
  }

  /**
   * This method implements the default behavior to convert a value to a {@link JSONArray} object.
   * The default behavior is to throw a cast exception. Each class that implements a value may have
   * a different behavior so it could override this method.
   *
   * @return a {@link JSONArray} object if the conversion is possible.
   */
  public JSONArray toJSONArray() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a JSONArray");
  }

  /**
   * This method implements the default behavior to convert a value to a {@link JSONString} object.
   * The default behavior is to throw a cast exception. Each class that implements a value may have
   * a different behavior so it could override this method.
   *
   * @return a {@link net.cabezudo.json.values.JSONString} object if the conversion is possible.
   */
  public JSONString toJSONString() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a JSONString");
  }

  /**
   * OOLV: Please explain like I'm a five years old kid
   *
   * This method return the JSON structure of this object. A JSON structure is created with JSON
   * elements and have a root element with child elements and this child elements can have another
   * child element, creating a tree structure with several leafs. If you convert this object to a
   * JSON structure, the root element is {@code this} object.
   *
   * @return a {@link JSONValue} object with the JSON structure with the representation of
   * {@code this} object if the conversion is possible.
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   * This method implements the default behavior to convert a value to a {code List} of
   * {@link JSONValue} object. The default behavior is to throw a cast exception. Each class that
   * implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code List} of {@link JSONValue} objects if the conversion is possible.
   */
  public List<JSONValue> toList() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a List");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Long} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Long} object if the conversion is possible.
   */
  public Long toLong() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Long");
  }

  /**
   * This method implements the default behavior to convert a value to a {@link JSONObject} object.
   * The default behavior is to throw a cast exception. Each class that implements a value may have
   * a different behavior so it could override this method.
   *
   * @return a {@link JSONObject} object if the conversion is possible.
   */
  public JSONObject toObject() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a JSONObject");
  }

  /**
   * This method implements the default behavior to convert a value to a {code Short} object. The
   * default behavior is to throw a cast exception. Each class that implements a value may have a
   * different behavior so it could override this method.
   *
   * @return a {@code Short} object if the conversion is possible.
   */
  public Short toShort() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a Short");
  }

  /**
   * This method implements the default behavior to convert a value to an array of {code String}
   * objects. The default behavior is to throw a cast exception. Each class that implements a value
   * may have a different behavior so it could override this method.
   *
   * @return an array of {@code String} object if the conversion is possible.
   */
  public String[] toStringArray() {
    throw new JSONConvertionException("I can't convert a " + this.getClass().getName() + " to a String array.");
  }
}
