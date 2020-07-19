package net.cabezudo.json.values;

import net.cabezudo.json.exceptions.JSONConversionException;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.9, 05/09/2017
 */
public class JSONValueTest {

  @Test(expected = JSONConversionException.class)
  public void testToArray() {
    JSONValue jsonValue = JSONBoolean.TRUE;
    jsonValue.toArray();
  }

  @Test(expected = JSONConversionException.class)
  public void testToBigDecimal() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toBigDecimal();
  }

  @Test(expected = JSONConversionException.class)
  public void testToBigInteger() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toBigInteger();
  }

  @Test(expected = JSONConversionException.class)
  public void testToBoolean() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toBoolean();
  }

  @Test(expected = JSONConversionException.class)
  public void testToByte() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toByte();
  }

  @Test(expected = JSONConversionException.class)
  public void testToByteArray() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toByteArray();
  }

  @Test(expected = JSONConversionException.class)
  public void testToCalendar() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toCalendar();
  }

  @Test(expected = JSONConversionException.class)
  public void testToCharacter() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toCharacter();
  }

  @Test(expected = JSONConversionException.class)
  public void testToDouble() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toDouble();
  }

  @Test(expected = JSONConversionException.class)
  public void testToFloat() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toFloat();
  }

  @Test(expected = JSONConversionException.class)
  public void testToInteger() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toInteger();
  }

  @Test(expected = JSONConversionException.class)
  public void testToJSONString() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toJSONString();
  }

  @Test(expected = JSONConversionException.class)
  public void testToList() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toList();
  }

  @Test(expected = JSONConversionException.class)
  public void testToLong() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toLong();
  }

  @Test(expected = JSONConversionException.class)
  public void testToStringArray() {
    JSONValue jsonValue = new JSONObject();
    jsonValue.toStringArray();
  }
}
