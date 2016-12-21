package net.cabezudo.java.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.java.json.JSONElement;
import net.cabezudo.java.json.Position;
import net.cabezudo.java.json.exceptions.JSONCastException;

/**
 * The <code>JSONValue</code> class is an abstract class for implement a JSON value. A JSON value is a JSON sctructure component inherited
 * from {@link net.cabezudo.java.json.JSONElement} thet can be used in a pair or in an array. There are seven types for values: string,
 * number, object, array, true, false, and null.
 *
 * <p>
 * The class also provides additional default methods for implementing a concrete value and the default behavior for the conversions.
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
   * Returns if the JSON element is a value. This object represent a JSON value, so allways return a true.
   *
   * @return <code>true</code> because the object is a JSON value.
   */
  @Override
  public boolean isValue() {
    return true;
  }

  /**
   * This method implements the default behavior to convert a value to an array of JSON values. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public JSONValue[] toArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONValue array.");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>BigDecimal</code> object. The default behavior is to throw a
   * cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public BigDecimal toBigDecimal() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a BigDecimal");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>BigInteger</code> object. The default behavior is to throw a
   * cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public BigInteger toBigInteger() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a BigInteger");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Boolean</code> object. The default behavior is to throw a
   * cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Boolean toBoolean() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Boolean");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Byte</code> object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Byte toByte() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Byte");
  }

  /**
   * This method implements the default behavior to convert a value to an array of primitives <code>byte</code> object. The default behavior
   * is to throw a cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public byte[] toByteArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a ByteArray");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Calendar</code> object. The default behavior is to throw a
   * cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Calendar toCalendar() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Calendar");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Character</code> object. The default behavior is to throw a
   * cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Character toCharacter() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Character");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Double</code> object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Double toDouble() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Double");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Float</code> object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Float toFloat() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Float");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Integer</code> object. The default behavior is to throw a
   * cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public Integer toInteger() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to an Integer");
  }

  /**
   * This method implements the default behavior to convert a value to a {@link JSONArray} object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public JSONArray toJSONArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONArray");
  }

  /**
   * This method implements the default behavior to convert a value to a {@link JSONString} object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a JSON object if can make the conversion.
   */
  public JSONString toJSONString() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONString");
  }

  /**
   * This method return the tree structure of this object in a structure with a root node of type {@link JSONValue}. A JSON tree is a
   * {@code JSONElement} object and the child nodes, the child nodes of the child nodes an so on. Therefore the root element is {@code this}
   * object.
   *
   * @return a {@link JSONTree} object for the value.
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   * This method implements the default behavior to convert a value to a <code>List</code> of {@link JSONValue} object. The default behavior
   * is to throw a cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a <code>List</code> of {@link JSONValue} objects if can make the conversion.
   */
  public List<JSONValue> toList() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a List");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Long</code> object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a <code>Long</code> object if can make the conversion.
   */
  public Long toLong() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Long");
  }

  /**
   * This method implements the default behavior to convert a value to a {@link JSONObject} object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a {@link JSONObject} object if can make the conversion.
   */
  public JSONObject toObject() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONObject");
  }

  /**
   * This method implements the default behavior to convert a value to a <code>Short</code> object. The default behavior is to throw a cast
   * exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a <code>Short</code> object if can make the conversion.
   */
  public Short toShort() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Short");
  }

  /**
   * This method implements the default behavior to convert a value to an array of <code>String</code> objects. The default behavior is to
   * throw a cast exception. Each object that implements a value may have a different behavior so it could override this method.
   *
   * @return a an array of <code>String</code> objects if can make the conversion.
   */
  public String[] toStringArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a String array.");
  }
}
