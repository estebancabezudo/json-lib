package net.cabezudo.java.json.values;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.cabezudo.java.json.JSON;
import net.cabezudo.java.json.JSONElement;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @date 10/01/2014
 * @version 1.0
 * @since 1.7
 */
public class JSONArray extends JSONValue<JSONArray> implements Iterable<JSONValue> {

  private final List<JSONValue> list = new ArrayList<>();

  /**
   * Construct an empty JSON array object.
   */
  public JSONArray() {
    // Nothing to do here. Just needed for create an empty array.
  }

  /**
   *
   * @param valuesList
   */
  public JSONArray(List<JSONValue> valuesList) {
    for (JSONValue jsonValue : valuesList) {
      internalAdd(jsonValue);
    }
  }

  /**
   *
   * @param jsonValues
   */
  public JSONArray(JSONValue... jsonValues) {
    for (JSONValue jsonValue : jsonValues) {
      internalAdd(jsonValue);
    }
  }

  /**
   *
   * @param objects
   */
  public JSONArray(Object... objects) {
    for (Object object : objects) {
      internalAdd(object);
    }
  }


  private void internalAdd(JSONValue jsonValue) {
    if (jsonValue == null) {
      list.add(JSONNull.getValue());
    } else {
      list.add(jsonValue);
    }
  }


  private void internalAdd(Object object) {
    if (object == null) {
      list.add(JSONNull.getValue());
    } else {
      JSONValue jsonValue = JSON.toJSONTree(object);
      list.add(jsonValue);
    }
  }
  /**
   *
   * @param jsonValue
   */
  public void add(JSONValue jsonValue) {
    internalAdd(jsonValue);
  }
  /**
   *
   * @param object
   */
  public void add(Object object) {
    internalAdd(object);
  }

  @Override
  public int compareTo(JSONArray jsonArray) {
    Integer thisSize = this.size();
    Integer arraySize = jsonArray.size();

    return thisSize.compareTo(arraySize);
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONElement deleteElement(int index) {
    return list.remove(index);
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONValue get(int index) {
    return getValue(index);
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONElement getElement(int index) {
    return list.get(index);
  }

  /**
   *
   * @param propertyIndex
   * @return
   */
  public JSONValue getNullValue(int propertyIndex) {
    return list.get(propertyIndex);
  }

  /**
   *
   * @return
   */
  @Override
  public JSONArray getReferencedElement() {
    JSONArray jsonArray = new JSONArray();
    for (JSONElement jsonElement : list) {
      jsonArray.add(jsonElement.getReferencedElement());
    }
    return jsonArray;
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONValue getValue(int index) {
    JSONValue jsonValue = getNullValue(index);
    return jsonValue;
  }

  /**
   *
   * @return
   */
  @Override
  public Boolean hasElements() {
    return !list.isEmpty();
  }

  @Override
  public Iterator<JSONValue> iterator() {
    return list.iterator();
  }

  /**
   *
   * @param index
   * @param jsonValue
   * @return
   */
  public JSONValue setValue(int index, JSONValue jsonValue) {
    return list.set(index, jsonValue);
  }

  /**
   *
   * @return
   */
  public int size() {
    return list.size();
  }

  /**
   *
   * @return
   */
  @Override
  public JSONValue[] toArray() {
    JSONValue[] array = new JSONValue[list.size()];
    int i = 0;
    for (JSONValue jsonValue : list) {
      array[i] = jsonValue;
      i++;
    }
    return array;
  }

  /**
   *
   * @return
   */
  @Override
  public byte[] toByteArray() {
    byte[] byteArray = new byte[list.size()];
    int i = 0;
    for (JSONValue jsonValue : list) {
      byteArray[i] = jsonValue.toByte();
      i++;
    }
    return byteArray;
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    StringBuilder sb = new StringBuilder("[ ");
    if (list.size() > 0) {
      for (JSONValue jsonElement : list) {
        sb.append(jsonElement.toJSON());
        sb.append(", ");
      }
      sb = new StringBuilder(sb.substring(0, sb.length() - 2));
    }
    sb.append(" ]");
    return sb.toString();
  }

  /**
   *
   * @return
   */
  @Override
  public JSONArray toJSONArray() {
    return this;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   *
   * @return
   */
  @Override
  public List<JSONValue> toList() {
    List<JSONValue> newList = new ArrayList<>();
    for (JSONValue jsonValue : list) {
      newList.add(jsonValue);
    }
    return newList;
  }

  /**
   *
   * @return
   */
  @Override
  public String[] toStringArray() {
    String[] array = new String[list.size()];
    int i = 0;
    for (JSONValue jsonElement : list) {
      array[i] = jsonElement.toString();
      i++;
    }
    return array;
  }

}
