package net.cabezudo.json.values;

import java.util.List;
import net.cabezudo.json.exceptions.ElementNotExistException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/18/2016
 */
public class JSONBooleanTest {

  @Test
  public void testEquals() {
    JSONBoolean a = JSONBoolean.get(true);
    JSONBoolean b = JSONBoolean.get(false);
    JSONBoolean c = JSONBoolean.TRUE;
    JSONBoolean d = JSONBoolean.FALSE;
    assertTrue(a.equals(a));
    assertFalse(a.equals(b));
    assertTrue(a.equals(c));
    assertFalse(a.equals(d));

    assertTrue(b.equals(b));
    assertFalse(b.equals(a));
    assertTrue(b.equals(d));
    assertFalse(b.equals(c));

    assertTrue(c.equals(a));
    assertFalse(c.equals(b));
    assertTrue(c.equals(c));
    assertFalse(c.equals(d));

    assertTrue(d.equals(b));
    assertFalse(d.equals(a));
    assertTrue(d.equals(d));
    assertFalse(d.equals(c));
  }

  @Test
  public void testGetReferencedElement() {
    JSONBoolean e = JSONBoolean.TRUE;
    JSONBoolean r = e.toReferencedElement();

    assertEquals(e, r);
  }

  @Test
  public void testIsArray() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(false, jsonBoolean.isArray());
  }

  @Test
  public void testIsBoolean() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(true, jsonBoolean.isBoolean());
  }

  @Test
  public void testIsNotReferenciable() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(true, jsonBoolean.isNotReferenceable());
  }

  @Test
  public void testIsNull() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(false, jsonBoolean.isNull());
  }

  @Test
  public void testIsNumber() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(false, jsonBoolean.isNumber());
  }

  @Test
  public void testIsObject() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(false, jsonBoolean.isObject());
  }

  @Test
  public void testIsReferenciable() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(false, jsonBoolean.isReferenceable());
  }

  @Test
  public void testIsString() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(false, jsonBoolean.isString());
  }

  @Test
  public void testIsValue() {
    JSONBoolean jsonBoolean = JSONBoolean.TRUE;
    assertEquals(true, jsonBoolean.isValue());
  }

  @Test
  public void testToBoolean() {
    JSONBoolean a = JSONBoolean.get(true);
    JSONBoolean b = JSONBoolean.get(false);
    JSONBoolean c = JSONBoolean.TRUE;
    JSONBoolean d = JSONBoolean.FALSE;
    assertEquals(true, a.toBoolean());
    assertEquals(false, b.toBoolean());
    assertEquals(true, c.toBoolean());
    assertEquals(false, d.toBoolean());
  }

  @Test
  public void testToJSON() {
    JSONBoolean a = JSONBoolean.TRUE;
    JSONBoolean b = JSONBoolean.FALSE;

    String aStirng = a.toJSON();
    String bStirng = b.toJSON();

    assertEquals("true", aStirng);
    assertEquals("false", bStirng);
  }

  @Test
  public void testToJSONArray() throws ElementNotExistException {
    JSONBoolean b = JSONBoolean.TRUE;

    JSONArray array = b.toJSONArray();

    assertEquals(1, array.size());
    assertEquals(JSONBoolean.TRUE, array.getValue(0));
  }

  @Test
  public void testToJSONString() {
    JSONBoolean a = JSONBoolean.TRUE;
    JSONBoolean b = JSONBoolean.FALSE;

    JSONString jsonTrue = a.toJSONString();
    JSONString jsonFalse = b.toJSONString();

    assertEquals(new JSONString("true"), jsonTrue);
    assertEquals(new JSONString("false"), jsonFalse);
  }

  @Test
  public void testToList() {
    JSONBoolean b = JSONBoolean.TRUE;

    List<JSONValue> list = b.toList();

    assertEquals(1, list.size());
    assertEquals(JSONBoolean.TRUE, list.get(0));
  }

  @Test
  public void testToStringArray() {
    JSONBoolean a = JSONBoolean.TRUE;
    JSONBoolean b = JSONBoolean.FALSE;

    String stirngTrue = a.toString();
    String stirngFalse = b.toString();

    assertEquals("true", stirngTrue);
    assertEquals("false", stirngFalse);
  }
}
