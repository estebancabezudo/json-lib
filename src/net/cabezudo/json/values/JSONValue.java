package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.json.JSONElement;
import net.cabezudo.json.Position;
import net.cabezudo.json.exceptions.JSONCastException;

/**
 * The {code JSONValue} class is an abstract class for implement a JSON value. A JSON value is a
 * JSON structure component inherited from {@link JSONElement} that can be used in
 * a pair or in an array. There are seven types for values: string, number, object, array, true,
 * false, and null.
 *
 * <p>
 * The class also provides additional default methods for implementing a concrete value and the
 * default behavior for the conversions.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @param <T>
 * @since 1.7
 * @date 10/01/2014
 */
public abstract class JSONValue<T> extends JSONElement implements Comparable<T> {

  public JSONValue(Position position) {
    super(position);
  }

  /**
   * Returns if the JSON element is a value. This object represent a JSON value, so always return a
   * {@code true}.
   *
   * @return {@code true} because the object is a JSON value.
   */
  @Override
  public boolean isValue() {
    return true;
  }

  /**
   * This method implements the default behavior to convert a value to an array of
   * {@link JSONValue} object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return an array of {@link JSONValue} object if the conversion is
   * possible.
   */
  public JSONValue[] toArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONValue array.");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code BigDecimal} object.
   * The default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code BigDecimal} object if the conversion is possible.
   */
  public BigDecimal toBigDecimal() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a BigDecimal");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code BigInteger} object.
   * The default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code BigInteger} object if the conversion is possible.
   */
  public BigInteger toBigInteger() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a BigInteger");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Boolean} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Boolean} object if the conversion is possible.
   */
  public Boolean toBoolean() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Boolean");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Byte} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Byte} object if the conversion is possible.
   */
  public Byte toByte() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Byte");
  }

  /**
   * This method implements the default behavior to convert a value to an array of {@code byte}
   * object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return an array of {@code byte} object if the conversion is possible.
   */
  public byte[] toByteArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a ByteArray");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Calendar} object.
   * The default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Calendar} object if the conversion is possible.
   */
  public Calendar toCalendar() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Calendar");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Character} object.
   * The default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Character} object if the conversion is possible.
   */
  public Character toCharacter() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Character");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Double} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Double} object if the conversion is possible.
   */
  public Double toDouble() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Double");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Float} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Float} object if the conversion is possible.
   */
  public Float toFloat() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Float");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Integer} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Integer} object if the conversion is possible.
   */
  public Integer toInteger() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to an Integer");
  }

  /**
   * This method implements the default behavior to convert a value to a
   * {@link JSONArray} object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return a {@link JSONArray} object if the conversion is possible.
   */
  public JSONArray toJSONArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONArray");
  }

  /**
   * This method implements the default behavior to convert a value to a
   * {@link JSONString} object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return a {@link JSONString} object if the conversion is possible.
   */
  public JSONString toJSONString() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONString");
  }

  /**
   * This method implements the default behavior to convert a value to a JSON structure with the
   * representation of {@code this} object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return a {@link JSONValue} object with the JSON structure with the
   * representation of {@code this} object if the conversion is possible.
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   * This method implements the default behavior to convert a value to a {@code List} of
   * {@link JSONValue} objects. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return a {@code List} of {@link JSONValue} objects if the conversion
   * is possible.
   */
  public List<JSONValue> toList() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a List");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Long} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Long} object if the conversion is possible.
   */
  public Long toLong() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Long");
  }

  /**
   * This method implements the default behavior to convert a value to a
   * {@link JSONObject} object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return a {@link JSONObject} object if the conversion is possible.
   */
  public JSONObject toObject() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONObject");
  }

  /**
   * This method implements the default behavior to convert a value to a {@code Short} object. The
   * default behavior is to throw a {@link JSONCastException} Each
   * object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@code Short} object if the conversion is possible.
   */
  public Short toShort() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Short");
  }

  /**
   * This method implements the default behavior to convert a value to an array of {@code String}
   * object. The default behavior is to throw a
   * {@link JSONCastException} Each object that implements a value may
   * have a different behavior so it could override this method.
   *
   * @return an array of {@code String} object if the conversion is possible.
   */
  public String[] toStringArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a String array.");
  }
}
