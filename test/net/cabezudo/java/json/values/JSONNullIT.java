package net.cabezudo.java.json.values;

import java.util.List;
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
    assertEquals(JSONNull.getValue(), JSONNull.getValue());
  }
  @Test
  public void testGetReferencedElement() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(jsonNull.getElement(), jsonNull.getReferencedElement());
  }


  @Test
  public void testIsArray() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(false, jsonNull.isArray());
  }

  @Test
  public void testIsBoolean() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(false, jsonNull.isBoolean());
  }

  @Test
  public void testIsNotReferenciable() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(true, jsonNull.isNotReferenciable());
  }

  @Test
  public void testIsNull() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(true, jsonNull.isNull());
  }

  @Test
  public void testIsNumber() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(false, jsonNull.isNumber());
  }

  @Test
  public void testIsObject() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(false, jsonNull.isObject());
  }

  @Test
  public void testIsReferenciable() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(false, jsonNull.isReferenciable());
  }

  @Test
  public void testIsString() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(false, jsonNull.isString());
  }

  @Test
  public void testIsValue() {
    JSONNull jsonNull = JSONNull.getValue();
    assertEquals(true, jsonNull.isValue());
  }
  @Test
  public void testToJSON() {
    assertEquals("null", JSONNull.getValue().toJSON());
  }
  @Test
  public void testToJSONArray() {
    JSONNull jsonNull = JSONNull.getValue();
    JSONArray array = jsonNull.toJSONArray();
    assertEquals(1, array.size());
    assertEquals(new JSONString("null"), array.get(0));
  }
  @Test
  public void testToJSONString() {
    assertEquals(new JSONString("null"), JSONNull.getValue().toJSONString());
  }
  @Test
  public void testToJSONTree() {
    assertEquals(JSONNull.getValue(), JSONNull.getValue().toJSONTree());
  }
  @Test
  public void testToList() {
    JSONNull jsonNull = JSONNull.getValue();
    List<JSONValue> array = jsonNull.toList();
    assertEquals(1, array.size());
    assertEquals(JSONNull.getValue(), array.get(0));
  }
  @Test
  public void testToStringArray() {
    JSONNull jsonNull = JSONNull.getValue();
    String[] array = jsonNull.toStringArray();
    assertEquals(1, array.length);
    assertEquals("null", array[0]);
  }
}
