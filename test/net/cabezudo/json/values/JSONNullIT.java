package net.cabezudo.json.values;

import java.util.List;
import net.cabezudo.json.exceptions.ElementNotExistException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/18/2016
 */
public class JSONNullIT {

  @Test
  public void testCompareTo() {
    assertEquals(new JSONNull(), new JSONNull());
  }

  @Test
  public void testGetReferencedElement() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(jsonNull, jsonNull.toReferencedElement());
  }

  @Test
  public void testIsArray() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(false, jsonNull.isArray());
  }

  @Test
  public void testIsBoolean() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(false, jsonNull.isBoolean());
  }

  @Test
  public void testIsNotReferenciable() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(true, jsonNull.isNotReferenciable());
  }

  @Test
  public void testIsNull() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(true, jsonNull.isNull());
  }

  @Test
  public void testIsNumber() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(false, jsonNull.isNumber());
  }

  @Test
  public void testIsObject() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(false, jsonNull.isObject());
  }

  @Test
  public void testIsReferenciable() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(false, jsonNull.isReferenciable());
  }

  @Test
  public void testIsString() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(false, jsonNull.isString());
  }

  @Test
  public void testIsValue() {
    JSONNull jsonNull = new JSONNull();
    assertEquals(true, jsonNull.isValue());
  }

  @Test
  public void testToJSON() {
    assertEquals("null", new JSONNull().toJSON());
  }

  @Test
  public void testToJSONArray() throws ElementNotExistException {
    JSONNull jsonNull = new JSONNull();
    JSONArray array = jsonNull.toJSONArray();
    assertEquals(1, array.size());
    assertEquals(new JSONString("null"), array.getValue(0));
  }

  @Test
  public void testToJSONString() {
    assertEquals(new JSONString("null"), new JSONNull().toJSONString());
  }

  @Test
  public void testToJSONTree() {
    assertEquals(new JSONNull(), new JSONNull().toJSONTree());
  }

  @Test
  public void testToList() {
    JSONNull jsonNull = new JSONNull();
    List<JSONValue> array = jsonNull.toList();
    assertEquals(1, array.size());
    assertEquals(new JSONNull(), array.get(0));
  }

  @Test
  public void testToStringArray() {
    JSONNull jsonNull = new JSONNull();
    String[] array = jsonNull.toStringArray();
    assertEquals(1, array.length);
    assertEquals("null", array[0]);
  }
}
