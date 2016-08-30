package net.cabezudo.java.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import net.cabezudo.java.json.JSON;
import net.cabezudo.java.json.exceptions.JSONCastException;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONString extends JSONValue<JSONString> {

  private final String value;

  /**
   *
   * @param value
   */
  public JSONString(String value) {
    this.value = value;
  }

  /**
   *
   * @param character
   */
  public JSONString(Character character) {
    this.value = character.toString();
  }

  /**
   *
   * @param bigInteger
   */
  public JSONString(BigInteger bigInteger) {
    this.value = bigInteger.toString();
  }

  /**
   *
   * @param bigDecimal
   */
  public JSONString(BigDecimal bigDecimal) {
    this.value = bigDecimal.toString();
  }

  @Override
  public int compareTo(JSONString jsonString) {
    return value.compareTo(jsonString.value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    JSONString other = (JSONString) o;
    return Objects.equals(this.value, other.value);
  }

  /**
   *
   * @return
   */
  @Override
  public JSONValue getReferencedElement() {
    return this;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Objects.hashCode(this.value);
    return hash;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isString() {
    return true;
  }

  /**
   *
   * @return
   */
  public JSONValue[] toArray() {
    JSONValue array[] = new JSONValue[1];
    array[0] = new JSONString(value);
    return array;
  }

  /**
   *
   * @return
   */
  @Override
  public BigDecimal toBigDecimal() {
    return new BigDecimal(value);
  }

  /**
   *
   * @return
   */
  @Override
  public BigInteger toBigInteger() {
    return new BigInteger(value);
  }

  /**
   *
   * @return
   */
  @Override
  public Boolean toBoolean() {
    return Boolean.valueOf(value);
  }

  /**
   *
   * @return
   */
  @Override
  public Byte toByte() {
    return new Byte(value);
  }
  /**
   *
   * @return
   */
  @Override
  public byte[] toByteArray() {
    byte[] b = new byte[1];
    b[0] = toByte();
    return b;
  }
  /**
   *
   * @return
   */
  @Override
  public Calendar toCalendar() {
    SimpleDateFormat sdf = new SimpleDateFormat(JSON.SIMPLE_DATE_FORMAT_PATTERN);
    Date date;
    try {
      date = sdf.parse(value);
    } catch (ParseException e) {
      throw new JSONCastException("I can't convert a " + value + " to a Calendar.", e);
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  /**
   *
   * @return
   */
  @Override
  public Character toCharacter() {
    return value.charAt(0);
  }

  /**
   *
   * @return
   */
  @Override
  public Double toDouble() {
    return new Double(value);
  }

  /**
   *
   * @return
   */
  @Override
  public Float toFloat() {
    return Float.valueOf(value);
  }

  /**
   *
   * @return
   */
  @Override
  public Integer toInteger() {
    return Integer.valueOf(value);
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    if (value == null) {
      return null;
    }
    return "\"" + value + "\"";
  }

  /**
   *
   * @return
   */
  @Override
  public JSONArray toJSONArray() {
    return new JSONArray(value);
  }

  /**
   *
   * @return
   */
  @Override
  public JSONString toJSONString() {
    return new JSONString(value);
  }

  /**
   *
   * @return
   */
  @Override
  public List<JSONValue> toList() {
    List<JSONValue> list = new ArrayList<>();
    list.add(this);
    return list;
  }

  /**
   *
   * @return
   */
  @Override
  public Long toLong() {
    return Long.valueOf(value);
  }

  /**
   *
   * @return
   */
  @Override
  public Short toShort() {
    return Short.valueOf(value);
  }

  @Override
  public String toString() {
    return value;
  }

  /**
   *
   * @return
   */
  @Override
  public String[] toStringArray() {
    String[] array = new String[1];
    array[0] = value;
    return array;
  }

}
