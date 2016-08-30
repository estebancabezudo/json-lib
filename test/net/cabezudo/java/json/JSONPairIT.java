package net.cabezudo.java.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import net.cabezudo.java.json.exceptions.JSONCastException;
import net.cabezudo.java.json.values.JSONString;
import net.cabezudo.java.json.values.JSONValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONPairIT {

  @Test
  public void testCompareTo() {
    System.out.println("Compare two objects JSONPair.");
    JSONPair a = new JSONPair("b", "b");
    JSONPair b = new JSONPair("b", "b");
    JSONPair c = new JSONPair("b", "a");
    JSONPair d = new JSONPair("b", "c");
    JSONPair e = new JSONPair("a", "x");
    JSONPair f = new JSONPair("c", "x");
    int comparation = a.compareTo(b);
    assertEquals(0, comparation);
    comparation = a.compareTo(c);
    assertTrue(comparation > 0);
    comparation = a.compareTo(d);
    assertTrue(comparation < 0);
    comparation = a.compareTo(e);
    assertTrue(comparation > 0);
    comparation = a.compareTo(f);
    assertTrue(comparation < 0);
  }

  @Test
  public void testEquals() {
    System.out.println("Compare two objects JSONPair for equality.");
    JSONPair a = new JSONPair("a", "b");
    JSONPair b = new JSONPair("a", "b");
    JSONPair c = new JSONPair("a", "c");
    JSONPair d = new JSONPair("b", "b");
    boolean comparation = a.equals(b);
    assertTrue(comparation);
    comparation = a.equals(c);
    assertFalse(comparation);
    comparation = a.equals(d);
    assertFalse(comparation);
  }

  @Test
  public void testGetValue() {
    System.out.println("Get the element value.");
    JSONString jsonExpectedValue = new JSONString("A string");
    JSONPair jsonPair = new JSONPair("key", jsonExpectedValue);
    JSONValue jsonValue = jsonPair.getValue();
    assertEquals(jsonExpectedValue, jsonValue);
  }

  @Test
  public void testHasElements() {
    System.out.println("Check if an element has childs.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.hasElements();
  }

  @Test
  public void testHash() {
    System.out.println("Compare two objects JSONPair hash.");
    JSONPair a = new JSONPair("a", "b");
    JSONPair b = new JSONPair("a", "b");
    JSONPair c = new JSONPair("a", "c");
    JSONPair d = new JSONPair("b", "b");
    int ha = a.hashCode();
    int hb = b.hashCode();
    int hc = c.hashCode();
    int hd = d.hashCode();
    assertTrue(ha == hb);
    assertFalse(ha == hc);
    assertFalse(ha == hd);
  }

  @Test
  public void testIsArray() {
    System.out.println("Check if the element is an array.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isArray());
  }

  @Test
  public void testIsBoolean() {
    System.out.println("Check if the element is a boolean.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isBoolean());
  }

  @Test
  public void testIsNull() {
    System.out.println("Check if the element is a null.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isNull());
  }

  @Test
  public void testIsNumber() {
    System.out.println("Check if the element is a number.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isNumber());
  }

  @Test
  public void testIsObject() {
    System.out.println("Check if the element is an object.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isObject());
  }

  @Test
  public void testIsReferenciable() {
    System.out.println("Check if the element is referenciable.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isReferenciable());
  }

  @Test
  public void testIsString() {
    System.out.println("Check if the element is a string.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isString());
  }

  @Test
  public void testIsValue() {
    System.out.println("Check if the element is a value.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    assertTrue(!jsonPair.isValue());
  }

  @Test
  public void testObjectUsingABigDecimal() {
    System.out.println("Create a JSONValue using a BigDecimal object.");
    BigDecimal bigDecimal = new BigDecimal("10");
    JSONPair jsonPair = new JSONPair("key", bigDecimal);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
    String newBigDecimalString = jsonValue.toString();
    BigDecimal newBigDecimal = new BigDecimal(newBigDecimalString);
    assertEquals(bigDecimal, newBigDecimal);
  }

  @Test
  public void testObjectUsingABigInteger() {
    System.out.println("Create a JSONValue using a BigInteger object.");
    BigInteger bigInteger = new BigInteger("10");
    JSONPair jsonPair = new JSONPair("key", bigInteger);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
    String newBigIntegerString = jsonValue.toString();
    BigInteger newBigInteger = new BigInteger(newBigIntegerString);
    assertEquals(bigInteger, newBigInteger);
  }

  @Test
  public void testObjectUsingABoolean() {
    System.out.println("Create a JSONValue using a Boolean object.");
    Boolean b = true;
    JSONPair jsonPair = new JSONPair("key", b);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isBoolean());
  }

  @Test
  public void testObjectUsingAByte() {
    System.out.println("Create a JSONValue using a Byte object.");
    Byte b = 10;
    JSONPair jsonPair = new JSONPair("key", b);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingACalendar() {
    System.out.println("Create a JSONValue using a Calendar object.");
    Calendar calendar = Calendar.getInstance();
    JSONPair jsonPair = new JSONPair("key", calendar);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isString());
    SimpleDateFormat sdf = new SimpleDateFormat(JSON.SIMPLE_DATE_FORMAT_PATTERN);
    String stringDate = jsonValue.toString();
    try {
      Date date = sdf.parse(stringDate);
      Calendar newCalendar = Calendar.getInstance();
      newCalendar.setTime(date);
      assertEquals(newCalendar, calendar);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testObjectUsingACharacter() {
    System.out.println("Create a JSONValue using a Character object.");
    Character c = 'a';
    JSONPair jsonPair = new JSONPair("key", c);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isString());
  }

  @Test
  public void testObjectUsingADate() {
    System.out.println("Create a JSONValue using a Date object.");
    Date date = new Date();
    JSONPair jsonPair = new JSONPair("key", date);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isString());
    SimpleDateFormat sdf = new SimpleDateFormat(JSON.SIMPLE_DATE_FORMAT_PATTERN);
    String stringDate = jsonValue.toString();
    try {
      Date newDate = sdf.parse(stringDate);
      assertEquals(newDate, date);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testObjectUsingADouble() {
    System.out.println("Create a JSONValue using a Double object.");
    Double d = new Double(10);
    JSONPair jsonPair = new JSONPair("key", d);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAFloat() {
    System.out.println("Create a JSONValue using a Float object.");
    Float f = new Float(10);
    JSONPair jsonPair = new JSONPair("key", f);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAInteger() {
    System.out.println("Create a JSONValue using a Integer object.");
    Integer i = 10;
    JSONPair jsonPair = new JSONPair("key", i);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingALong() {
    System.out.println("Create a JSONValue using a Long object.");
    Long l = 10l;
    JSONPair jsonPair = new JSONPair("key", l);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingANull() {
    System.out.println("Create a JSONValue using a null.");
    Object n = null;
    JSONPair jsonPair = new JSONPair("key", n);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNull());
  }

  @Test
  public void testObjectUsingAPrimitiveBoolean() {
    System.out.println("Create a JSONValue using a primitive boolean.");
    boolean b = true;
    JSONPair jsonPair = new JSONPair("key", b);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isBoolean());
  }

  @Test
  public void testObjectUsingAPrimitiveByte() {
    System.out.println("Create a JSONValue using a primitive byte.");
    byte b = 10;
    JSONPair jsonPair = new JSONPair("key", b);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAPrimitiveChar() {
    System.out.println("Create a JSONValue using a primitive character.");
    char c = 'a';
    JSONPair jsonPair = new JSONPair("key", c);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isString());
  }

  @Test
  public void testObjectUsingAPrimitiveDouble() {
    System.out.println("Create a JSONValue using a primitive double.");
    double d = 10;
    JSONPair jsonPair = new JSONPair("key", d);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAPrimitiveFloat() {
    System.out.println("Create a JSONValue using a primitive float.");
    float f = 10;
    JSONPair jsonPair = new JSONPair("key", f);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAPrimitiveInt() {
    System.out.println("Create a JSONValue using a primitive integer.");
    int i = 10;
    JSONPair jsonPair = new JSONPair("key", i);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAPrimitiveLong() {
    System.out.println("Create a JSONValue using a primitive long.");
    long l = 10;
    JSONPair jsonPair = new JSONPair("key", l);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAPrimitiveShort() {
    System.out.println("Create a JSONValue using a primitive short.");
    short s = 10;
    JSONPair jsonPair = new JSONPair("key", s);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAShort() {
    System.out.println("Create a JSONValue using a Short object.");
    Short s = 10;
    JSONPair jsonPair = new JSONPair("key", s);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isNumber());
  }

  @Test
  public void testObjectUsingAString() {
    System.out.println("Create a JSONValue using a String object.");
    String s = "A string";
    JSONPair jsonPair = new JSONPair("key", s);
    String key = jsonPair.getKey();
    assertEquals("key", key);
    JSONValue jsonValue = jsonPair.getValue();
    assertTrue(jsonValue.isString());
  }

  @Test
  public void testSetReferenceFieldName() {
    System.out.println("Set the reference field name.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.setReferenceFieldName("fieldName");
  }

  @Test
  public void testToBigDecimal() {
    System.out.println("Convert the element into an object BigDecimal.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 10);
    jsonPair.toBigDecimal();

    jsonPair = new JSONPair("key", "10");
    jsonPair.toBigDecimal();
  }

  @Test
  public void testToBoolean() {
    System.out.println("Convert the element into an object Boolean.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toBoolean();
  }

  @Test
  public void testToByte() {
    System.out.println("Convert the element into a Byte object.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 120);
    jsonPair.toByte();

    jsonPair = new JSONPair("key", "120");
    jsonPair.toByte();
  }

  @Test
  public void testToCharacter() {
    System.out.println("Convert the element into a Character object.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toCharacter();
  }

  @Test
  public void testToDouble() {
    System.out.println("Convert the element into a Double object.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 1364.45);
    jsonPair.toDouble();

    jsonPair = new JSONPair("key", "1364.45");
    jsonPair.toDouble();
  }

  @Test
  public void testToFloat() {
    System.out.println("Convert the element into a Float object.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 1234.5);
    jsonPair.toFloat();

    jsonPair = new JSONPair("key", "1234.5");
    jsonPair.toFloat();
  }

  @Test
  public void testToInteger() {
    System.out.println("Convert the element into a Integer object.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 1234567890);
    jsonPair.toInteger();

    jsonPair = new JSONPair("key", "1234567890");
    jsonPair.toInteger();
  }

  @Test
  public void testToJSON() {
    System.out.println("Returnn the JSON string element representation.");
    String expectedValue = "\"" + "key" + "\": \"" + "A string" + "\"";
    JSONPair jsonPair = new JSONPair("key", "A string");
    String value = jsonPair.toJSON();
    assertEquals(expectedValue, value);
  }

  @Test
  public void testToJSONArray() {
    System.out.println("Convert the element into a JSONArray object.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toJSONArray();
  }

  @Test
  public void testToJSONString() {
    System.out.println("Convert the element into a JSONString object.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toJSONString();
  }

  @Test
  public void testToJSONTree() {
    System.out.println("Convert the element to a JSON tree.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toJSONTree();
  }

  @Test
  public void testToList() {
    System.out.println("Convert the element into a List<JSONValues>.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toList();
  }

  @Test
  public void testToLong() {
    System.out.println("Convert the element into a Long object.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 1234567890123456789L);
    jsonPair.toLong();

    jsonPair = new JSONPair("key", "1234567890123456789");
    jsonPair.toLong();
  }

  @Test(expected = JSONCastException.class)
  public void testToObject() {
    System.out.println("Convert the element into a JSONObject object.");
    JSONPair jsonPair = new JSONPair("key", "A string");
    jsonPair.toObject();
  }

  @Test
  public void testToShort() {
    System.out.println("Convert the element into a Short object.");

    JSONPair jsonPair;

    jsonPair = new JSONPair("key", 234);
    jsonPair.toShort();

    jsonPair = new JSONPair("key", "234");
    jsonPair.toShort();
  }

  @Test
  public void testToString() {
    System.out.println("Return the element string representation.");
    String expectedValue = "(" + "key" + ", " + "A string" + ")";
    JSONPair jsonPair = new JSONPair("key", "A string");
    String value = jsonPair.toString();
    assertEquals(expectedValue, value);
  }
}
