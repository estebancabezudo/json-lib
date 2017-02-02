package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.json.Position;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONNumber extends JSONValue<JSONNumber> {

  /**
   *
   */
  public static final int DEFAULT_SCALE = 6;

  private final BigDecimal value;

  /**
   *
   * @param value
   */
  public JSONNumber(String value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param value
   */
  public JSONNumber(Byte value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param value
   */
  public JSONNumber(Short value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param value
   */
  public JSONNumber(Integer value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param value
   */
  public JSONNumber(Long value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param value
   */
  public JSONNumber(Float value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param value
   */
  public JSONNumber(Double value) {
    this(new BigDecimal(value));
  }

  /**
   *
   * @param bigDecimal
   */
  public JSONNumber(BigDecimal bigDecimal) {
    this(bigDecimal, null);
  }

  /**
   *
   * @param bigDecimal
   * @param position
   */
  public JSONNumber(BigDecimal bigDecimal, Position position) {
    super(position);
    if (bigDecimal == null) {
      throw new IllegalArgumentException("You can't create an object using null.");
    }
    this.value = bigDecimal.round(MathContext.UNLIMITED).setScale(DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
  }

  /**
   *
   * @param bigInteger
   */
  public JSONNumber(BigInteger bigInteger) {
    super(null);
    if (bigInteger == null) {
      throw new IllegalArgumentException("You can't create an object using null.");
    }
    this.value = new BigDecimal(bigInteger).setScale(DEFAULT_SCALE).stripTrailingZeros();
  }

  @Override
  public int compareTo(JSONNumber jsonNumber) {
    return value.compareTo(jsonNumber.value);
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
    final JSONNumber jsonNumber = (JSONNumber) o;
    return value.equals(jsonNumber.value);
  }

  /**
   *
   * @return
   */
  @Override
  public JSONNumber toReferencedElement() {
    return this;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isNumber() {
    return true;
  }

  /**
   *
   * @return
   */
  @Override
  public BigDecimal toBigDecimal() {
    return value;
  }

  /**
   *
   * @return
   */
  @Override
  public BigInteger toBigInteger() {
    return value.toBigInteger();
  }

  /**
   *
   * @return
   */
  @Override
  public Byte toByte() {
    byte b = value.byteValueExact();
    return b;
  }

  /**
   *
   * @return
   */
  @Override
  public byte[] toByteArray() {
    byte[] byteArray = new byte[1];
    byteArray[0] = this.toByte();
    return byteArray;
  }

  /**
   *
   * @return
   */
  @Override
  public Calendar toCalendar() {
    long time = this.toLong();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time);
    return calendar;
  }

  /**
   *
   * @return
   */
  @Override
  public Character toCharacter() {
    Character character = (char) value.intValue();
    return character;
  }

  /**
   *
   * @return
   */
  @Override
  public Double toDouble() {
    return value.doubleValue();
  }

  /**
   *
   * @return
   */
  @Override
  public Float toFloat() {
    float f = value.floatValue();
    return f;
  }

  /**
   *
   * @return
   */
  @Override
  public Integer toInteger() {
    return value.intValueExact();
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    if (value.intValue() == 0) { // To fix Java 7 trailing bug
      return "0";
    }
    String out = value.stripTrailingZeros().toPlainString();
    return out;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONArray toJSONArray() {
    JSONArray jsonArray = new JSONArray();
    jsonArray.add(value);
    return jsonArray;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONString toJSONString() {
    JSONString jsonString = new JSONString(value);
    return jsonString;
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
    return value.longValueExact();
  }

  /**
   *
   * @return
   */
  @Override
  public Short toShort() {
    short s = value.shortValueExact();
    return s;
  }

  @Override
  public String toString() {
    return toJSON();
  }

  /**
   *
   * @return
   */
  @Override
  public String[] toStringArray() {
    String[] s = new String[1];
    s[0] = value.toString();
    return s;
  }

}
