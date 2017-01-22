package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.cabezudo.json.Position;
import net.cabezudo.json.exceptions.JSONCastException;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONBoolean extends JSONValue<JSONBoolean> {

  /**
   *
   */
  public static final JSONBoolean FALSE = new JSONBoolean(false, null);
  /**
   *
   */
  public static final JSONBoolean TRUE = new JSONBoolean(true, null);

  private final Boolean value;

  /**
   *
   * @param value
   * @return
   */
  public static JSONBoolean get(boolean value) {
    if (value) {
      return TRUE;
    } else {
      return FALSE;
    }
  }

  private JSONBoolean(boolean value, Position position) {
    super(position);
    this.value = value;
  }

  @Override
  public int compareTo(JSONBoolean jsonBoolean) {
    return value.compareTo(jsonBoolean.toBoolean());
  }

  /**
   *
   * @return
   */
  @Override
  public JSONBoolean getReferencedElement() {
    return this;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isBoolean() {
    return true;
  }

  /**
   *
   * @return
   */
  @Override
  public BigDecimal toBigDecimal() {
    int numericValue = value ? 1 : 0;
    return BigDecimal.valueOf(numericValue);
  }

  /**
   *
   * @return
   */
  @Override
  public BigInteger toBigInteger() {
    int numericValue = value ? 1 : 0;
    return BigInteger.valueOf(numericValue);
  }

  /**
   *
   * @return
   */
  @Override
  public Boolean toBoolean() {
    return value;
  }

  /**
   *
   * @return
   */
  @Override
  public Byte toByte() {
    return (byte) (value ? 1 : 0);
  }

  /**
   *
   * @return
   */
  @Override
  public byte[] toByteArray() {
    throw new JSONCastException("I can't convert a " + this.getClass().getName() + " to a ByteArray");
  }

  /**
   *
   * @return
   */
  @Override
  public Character toCharacter() {
    return (value ? '1' : '0');
  }

  /**
   *
   * @return
   */
  @Override
  public Double toDouble() {
    return (double) (value ? 1 : 0);
  }

  /**
   *
   * @return
   */
  @Override
  public Float toFloat() {
    return (float) (value ? 1 : 0);
  }

  /**
   *
   * @return
   */
  @Override
  public Integer toInteger() {
    return (value ? 1 : 0);
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    return value ? "true" : "false";
  }

  /**
   *
   * @return
   */
  @Override
  public JSONArray toJSONArray() {
    JSONArray jsonArray = new JSONArray();
    jsonArray.add(this);
    return jsonArray;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONString toJSONString() {
    return new JSONString(this.toString(), getPosition());
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
    return (long) (value ? 1 : 0);
  }

  /**
   *
   * @return
   */
  @Override
  public Short toShort() {
    return (short) (value ? 1 : 0);
  }

  /**
   *
   * @return
   */
  @Override
  public String[] toStringArray() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
