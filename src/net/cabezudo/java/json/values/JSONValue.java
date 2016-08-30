package net.cabezudo.java.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.java.json.JSONElement;
import net.cabezudo.java.json.exceptions.JSONCastException;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @param <T>
 * @since 1.7
 * @date 10/01/2014
 */
public abstract class JSONValue<T> extends JSONElement implements Comparable<T> {

  /**
   *
   * @return
   */
  @Override
  public boolean isValue() {
    return true;
  }

  public JSONValue[] toArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONValue array.");
  }

  /**
   *
   * @return
   */
  public BigDecimal toBigDecimal() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a BigDecimal");
  }

  /**
   *
   * @return
   */
  public BigInteger toBigInteger() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a BigInteger");
  }

  /**
   *
   * @return
   */
  public Boolean toBoolean() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Boolean");
  }

  /**
   *
   * @return
   */
  public Byte toByte() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Byte");
  }

  /**
   *
   * @return
   */
  public byte[] toByteArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a ByteArray");
  }

  /**
   *
   * @return
   */
  public Calendar toCalendar() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Calendar");
  }

  /**
   *
   * @return
   */
  public Character toCharacter() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Character");
  }

  /**
   *
   * @return
   */
  public Double toDouble() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Double");
  }

  /**
   *
   * @return
   */
  public Float toFloat() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Float");
  }

  /**
   *
   * @return
   */
  public Integer toInteger() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to an Integer");
  }

  /**
   *
   * @return
   */
  public JSONArray toJSONArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONArray");
  }

  /**
   *
   * @return
   */
  public JSONString toJSONString() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONString");
  }

  /**
   *
   * @return
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   *
   * @return
   */
  public List<JSONValue> toList() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a List");
  }

  /**
   *
   * @return
   */
  public Long toLong() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Long");
  }

  /**
   *
   * @return
   */
  public JSONObject toObject() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a JSONObject");
  }

  /**
   *
   * @return
   */
  public Short toShort() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a Short");
  }

  /**
   *
   * @return
   */
  public String[] toStringArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a String array.");
  }
}
