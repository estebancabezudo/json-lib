package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.json.exceptions.ElementNotExistException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/18/2016
 */
public class JSONNumberIT {

  @Test
  public void testCompareTo() {
    JSONNumber a = new JSONNumber(1);
    JSONNumber b = new JSONNumber(1);
    JSONNumber c = new JSONNumber(2);
    assertTrue(a.compareTo(b) == 0);
    assertTrue(a.compareTo(c) < 0);
    assertTrue(c.compareTo(b) > 0);
  }

  @Test
  public void testEquals() {
    JSONNumber a = new JSONNumber(10);
    JSONNumber b = new JSONNumber(10);
    assertEquals(a, b);
  }

  @Test
  public void testGetReferencedElement() {
    JSONNumber jsonNumber = new JSONNumber(1);
    JSONNumber referencedElement = jsonNumber.getReferencedElement();
    assertEquals(jsonNumber, referencedElement);
  }

  @Test
  public void testIsArray() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(false, jsonNumber.isArray());
  }

  @Test
  public void testIsBoolean() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(false, jsonNumber.isBoolean());
  }

  @Test
  public void testIsNotReferenciable() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(true, jsonNumber.isNotReferenciable());
  }

  @Test
  public void testIsNull() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(false, jsonNumber.isNull());
  }

  @Test
  public void testIsNumber() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(true, jsonNumber.isNumber());
  }

  @Test
  public void testIsObject() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(false, jsonNumber.isObject());
  }

  @Test
  public void testIsReferenciable() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(false, jsonNumber.isReferenciable());
  }

  @Test
  public void testIsString() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(false, jsonNumber.isString());
  }

  @Test
  public void testIsValue() {
    JSONNumber jsonNumber = new JSONNumber(1);
    assertEquals(true, jsonNumber.isValue());
  }

  @Test
  public void testToBigDecimal() {
    JSONNumber jsonNumber = new JSONNumber(1.2);
    BigDecimal b = jsonNumber.toBigDecimal();
    assertEquals(new BigDecimal("1.2").setScale(6).stripTrailingZeros(), b);
  }

  @Test
  public void testToBigInteger() {
    JSONNumber jsonNumber = new JSONNumber(7);
    BigInteger b = jsonNumber.toBigInteger();
    assertEquals(new BigInteger("7"), b);
  }

  @Test
  public void testToByte() {
    JSONNumber jsonNumber = new JSONNumber(69);
    byte b = jsonNumber.toByte();
    assertEquals(69, b);
  }

  @Test
  public void testToByteArray() {
    JSONNumber jsonNumber = new JSONNumber(69);
    byte[] array = jsonNumber.toByteArray();
    assertEquals(1, array.length);
    assertEquals(69, array[0]);
  }

  @Test
  public void testToCalendar() {
    JSONNumber jsonNumber = new JSONNumber(128809212125l);
    Calendar calendar = jsonNumber.toCalendar();

    Calendar expectedCalendar = Calendar.getInstance();
    expectedCalendar.set(1974, 0, 30, 14, 20, 12);
    expectedCalendar.set(Calendar.MILLISECOND, 125);

    assertEquals(expectedCalendar, calendar);
  }

  @Test
  public void testToCharacter() {
    JSONNumber jsonNumber = new JSONNumber(97);
    char c = jsonNumber.toCharacter();
    assertEquals('a', c);
  }

  @Test
  public void testToDouble() {
    JSONNumber jsonNumber = new JSONNumber("123456789.5676");
    double d = jsonNumber.toDouble();
    assertEquals(123456789.5676, d, 0);
  }

  @Test
  public void testToFloat() {
    JSONNumber jsonNumber = new JSONNumber("10.5");
    float f = jsonNumber.toFloat();
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testToInteger() {
    JSONNumber jsonNumber = new JSONNumber(123456789);
    int i = jsonNumber.toInteger();
    assertEquals(123456789, i);
  }

  @Test
  public void testToJSON() {
    JSONNumber jsonNumber = new JSONNumber(69);
    String s = jsonNumber.toJSON();
    assertEquals("69", s);
  }

  @Test
  public void testToJSONArray() throws ElementNotExistException {
    JSONNumber jsonNumber = new JSONNumber(1);
    JSONArray array = jsonNumber.toJSONArray();
    assertEquals(1, array.size());
    assertEquals(new JSONNumber(1), array.getValue(0));
  }

  @Test
  public void testToJSONString() {
    JSONNumber jsonNumber = new JSONNumber(69.7);
    String s = jsonNumber.toString();
    assertEquals("69.7", s);
  }

  @Test
  public void testToList() {
    JSONNumber jsonNumber = new JSONNumber(69);
    List<JSONValue> array = jsonNumber.toList();
    assertEquals(1, array.size());
    assertEquals(new JSONNumber(69), array.get(0));
  }

  @Test
  public void testToLong() {
    JSONNumber jsonNumber = new JSONNumber("12345678912345678");
    long l = jsonNumber.toLong();
    assertEquals(12345678912345678l, l);
  }

  @Test
  public void testToShort() {
    JSONNumber jsonNumber = new JSONNumber(1000);
    short s = jsonNumber.toShort();
    assertEquals(1000, s);
  }

  @Test
  public void testToStringArray() {
    JSONNumber jsonNumber = new JSONNumber(69.7);
    String[] array = jsonNumber.toStringArray();
    assertEquals(1, array.length);
    assertEquals("69.7", array[0]);
  }

}
