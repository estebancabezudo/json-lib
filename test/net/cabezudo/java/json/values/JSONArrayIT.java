package net.cabezudo.java.json.values;

import java.util.List;
import net.cabezudo.java.json.JSONElement;
import net.cabezudo.java.json.exceptions.JSONParseException;
import net.cabezudo.java.json.exceptions.PropertyNotExistException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @date 10/01/2014
 * @version 1.0
 * @since 1.7
 */
public class JSONArrayIT {

  private JSONArray jsonArray;

  @Before
  public void createJSONArray() {
    jsonArray = new JSONArray();
  }

  @Test
  public void testAddJSONValue() throws PropertyNotExistException {
    int size = jsonArray.size();
    Assert.assertEquals(0, size);
    JSONNumber jsonNumber = new JSONNumber(10);
    jsonArray.add(jsonNumber);
    size = jsonArray.size();
    Assert.assertEquals(1, size);
    JSONValue jsonValue = jsonArray.getValue(0);
    assertEquals(new JSONNumber(10), jsonValue);
  }

  @Test
  public void testAddObject() {
    int size = jsonArray.size();
    Assert.assertEquals(0, size);
    jsonArray.add(new JSONNumber(10));
    size = jsonArray.size();
    Assert.assertEquals(1, size);
  }

  @Test
  public void testCompareTo() {
    JSONArray firstJSONArray = new JSONArray();
    JSONArray secondJSONArray = new JSONArray();

    JSONNumber firstJSONNumber = new JSONNumber(1);
    firstJSONArray.add(firstJSONNumber);
    JSONNumber secondJSONNumber = new JSONNumber(1);
    secondJSONArray.add(secondJSONNumber);

    JSONString firstJSONString = new JSONString("Esteban");
    firstJSONArray.add(firstJSONString);
    JSONString secondJSONString = new JSONString("Esteban");
    secondJSONArray.add(secondJSONString);

    assertEquals(firstJSONArray.compareTo(secondJSONArray), 0);
  }

  @Test
  public void testDeleteElement() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    jsonArray.deleteElement(0);

    assertEquals(1, jsonArray.size());

    JSONValue element = jsonArray.getValue(0);
    assertEquals(new JSONString("Esteban"), element);
  }

  @Test
  public void testGetElements() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    List<JSONElement> list = jsonArray.getElements();

    assertEquals(2, list.size());

    JSONValue element;

    element = jsonArray.getValue(0);
    assertEquals(new JSONNumber(1), element);

    element = jsonArray.getValue(1);
    assertEquals(new JSONString("Esteban"), element);
  }

  @Test
  public void testGetNullValue() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    List<JSONElement> list = jsonArray.getElements();

    assertEquals(2, list.size());

    JSONValue element;

    element = jsonArray.getNullValue(0);
    assertEquals(new JSONNumber(1), element);

    element = jsonArray.getNullValue(1);
    assertEquals(new JSONString("Esteban"), element);
  }

  @Test
  public void testGetReferencedElement() throws JSONParseException {
    JSONObject a = new JSONObject("{ \"id\": 2, \"name\": \"Esteban\" }");
    a.setReferenceFieldName("id");
    jsonArray.add(a);

    JSONObject b = new JSONObject("{ \"id\": 1, \"name\": \"Ismael\" }");
    b.setReferenceFieldName("id");
    jsonArray.add(b);

    JSONArray referencedElement = jsonArray.getReferencedElement();

    JSONValue c = referencedElement.getValue(0);
    long d = c.toInteger();
    assertEquals(2, d);

    JSONValue e = referencedElement.getValue(1);
    long f = e.toInteger();
    assertEquals(1, f);
  }

  @Test
  public void testGetValue() throws Exception {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    List<JSONElement> list = jsonArray.getElements();

    assertEquals(2, list.size());

    JSONValue element;

    element = jsonArray.getValue(0);
    assertEquals(new JSONNumber(1), element);

    element = jsonArray.getValue(1);
    assertEquals(new JSONString("Esteban"), element);
  }

  @Test
  public void testHasElements() {
    Assert.assertFalse(jsonArray.hasElements());

    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    List<JSONElement> list = jsonArray.getElements();

    assertEquals(2, list.size());

    Assert.assertTrue(jsonArray.hasElements());
  }
  @Test
  public void testIsValue() {
    assertEquals(true, jsonArray.isValue());
  }

  @Test
  public void testIterator() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    int i = 0;
    for (JSONValue jsonValue : jsonArray) {
      switch (i) {
        case 0:
          assertEquals(new JSONNumber(1), jsonValue);
          break;
        case 1:
          assertEquals(new JSONString("Esteban"), jsonValue);
          break;
        default:
          fail();
      }
      i++;
    }
  }

  @Test
  public void testSetValue() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    jsonString = new JSONString("Ismael");
    jsonArray.setValue(0, jsonString);

    JSONValue element;

    element = jsonArray.getValue(0);
    assertEquals(new JSONString("Ismael"), element);

    element = jsonArray.getValue(1);
    assertEquals(new JSONString("Esteban"), element);
  }

  @Test
  public void testToArray() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    JSONValue[] array = jsonArray.toArray();

    JSONValue a = array[0];
    JSONValue b = array[1];

    assertEquals(2, array.length);
    assertEquals(new JSONNumber(1), a);
    assertEquals(new JSONString("Esteban"), b);
  }

  @Test
  public void testToJSON() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    String json = jsonArray.toJSON();

    assertEquals("[ 1, \"Esteban\" ]", json);
  }

  @Test
  public void testToJSONArray() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    JSONArray otherJSONArray = jsonArray.toJSONArray();

    JSONValue n1 = jsonArray.getValue(0);
    JSONValue s1 = jsonArray.getValue(1);

    JSONValue n2 = otherJSONArray.getValue(0);
    JSONValue s2 = otherJSONArray.getValue(1);

    assertEquals(jsonArray.size(), otherJSONArray.size());
    assertEquals(n1, n2);
    assertEquals(s1, s2);
  }

  @Test
  public void testToJSONTree() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    JSONArray jsonTree = (JSONArray) jsonArray.toJSONTree();

    JSONValue n1 = jsonArray.getValue(0);
    JSONValue s1 = jsonArray.getValue(1);

    JSONValue n2 = jsonTree.getValue(0);
    JSONValue s2 = jsonTree.getValue(1);

    assertEquals(jsonArray.size(), jsonTree.size());
    assertEquals(n1, n2);
    assertEquals(s1, s2);
  }

  @Test
  public void testToList() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    List<JSONValue> list = jsonArray.toList();

    JSONValue a = list.get(0);
    JSONValue b = list.get(1);

    assertEquals(new JSONNumber(1), a);
    assertEquals(new JSONString("Esteban"), b);
  }

  @Test
  public void testToStringArray() {
    JSONNumber jsonNumber = new JSONNumber(1);
    jsonArray.add(jsonNumber);

    JSONString jsonString = new JSONString("Esteban");
    jsonArray.add(jsonString);

    String[] list = jsonArray.toStringArray();

    String a = list[0];
    String b = list[1];

    assertEquals("1", a);
    assertEquals("Esteban", b);
  }

}
