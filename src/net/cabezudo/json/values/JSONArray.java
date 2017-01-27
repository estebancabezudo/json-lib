/**
 * MIT License
 *
 * Copyright (c) 2017 Esteban Cabezudo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import net.cabezudo.json.JSON;
import net.cabezudo.json.JSONElement;
import net.cabezudo.json.Position;
import net.cabezudo.json.exceptions.ElementNotExistException;
import net.cabezudo.json.exceptions.PropertyNotExistException;

/**
 * A {@code JSONArray} is an object extended from {@link JSONValue} object in order to represent a
 * JSON array that can be used to create JSON structures.
 *
 * <p>
 * A {@code JSONArray} is a list of {@link net.cabezudo.json.values.JSONValue} objects that
 * represent the JSON array structure.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.00, 10/01/2014
 */
public class JSONArray extends JSONValue<JSONArray> implements Iterable<JSONValue> {

  private final List<JSONValue> list = new ArrayList<>();

  /**
   * Construct an empty JSON array object.
   *
   * @param position a {@link net.cabezudo.json.Position} object with the position of the array in a
   * source file or {@code String}.
   */
  public JSONArray(Position position) {
    // Nothing to do here. Just needed for create an empty array.
    super(position);
  }

  /**
   * Construct a {@code JSONArray} using the values in the {@code List} provided.
   *
   * @param valuesList a {@code List} with the values for array elements.
   */
  public JSONArray(List<JSONValue> valuesList) {
    super(null);
    for (JSONValue jsonValue : valuesList) {
      internalAdd(jsonValue);
    }
  }

  /**
   * Construct a {@code JSONArray} using the values provided of type {@link JSONValue}.
   *
   * @param jsonValues an array with the values for array elements.
   */
  public JSONArray(JSONValue... jsonValues) {
    super(null);
    for (JSONValue jsonValue : jsonValues) {
      internalAdd(jsonValue);
    }
  }

  /**
   * Construct a {@code JSONArray} using the values provided than will be converted using
   * {@link JSON#toJSONTree(java.lang.Object)} into the array elements.
   *
   * @param objects an array with POJO elements.
   */
  public JSONArray(Object... objects) {
    super(null);
    for (Object object : objects) {
      internalAdd(object);
    }
  }

  private void internalAdd(JSONValue jsonValue) {
    if (jsonValue == null) {
      list.add(new JSONNull());
    } else {
      list.add(jsonValue);
    }
  }

  private void internalAdd(Object object) {
    if (object == null) {
      list.add(new JSONNull());
    } else {
      JSONValue jsonValue = JSON.toJSONTree(object);
      list.add(jsonValue);
    }
  }

  /**
   * Add an element using the provided.
   *
   * @param jsonValue a {@link JSONValue} value to add.
   */
  public void add(JSONValue jsonValue) {
    internalAdd(jsonValue);
  }

  /**
   * Add an element using a POJO provided.
   *
   * @param object a {@link JSONValue} value to add.
   */
  public void add(Object object) {
    internalAdd(object);
  }

  /**
   * Compare two array using the size first and compare the elements one by one if the size is the
   * same.
   * <p>
   * First a value less than {@code 0} if the size of {@code this} {@code JSONArray} is less than
   * the size of the {@code JSONArray} passed as argument; and a value greater than {@code 0} if the
   * size of {@code this} {@code JSONObject} is greater than the size of argument.
   *
   * @param jsonArray the {@code JSONArray} to be compared.
   *
   * @return a value less than {@code 0} if the size of {@code this} {@code JSONArray} is less than
   * the size of the {@code JSONArray} passed as argument; and a value greater than {@code 0} if the
   * size of {@code this} {@code JSONObject} is greater than the size of argument. Return {@code 0}
   * if {@code this} {@code JSONArray} size is equal to the argument {@code JSONArray};
   */
  @Override
  public int compareTo(JSONArray jsonArray) {
    Integer thisSize = this.size();
    Integer arraySize = jsonArray.size();

    return thisSize.compareTo(arraySize);
  }

  /**
   * Remove the element in the {@code index} position.
   *
   * @param index the position of the element to remove.
   * @return the element removed.
   */
  public JSONElement remove(int index) {
    return list.remove(index);
  }

  /**
   * Retrieve from {@code this} {@code JSONArray} the element in the position {@code index}.
   *
   * @param index the position of the element to retrieve.
   * @return the element in the position {@code index}.
   */
  public JSONElement getElement(int index) {
    return list.get(index);
  }

  /**
   * Retrieve the {@link JSONValue} of the element in the position {@code index} of {@code this}
   * {@code JSONArray}.
   *
   * @param index the position in the array for the element to retrieve.
   * @return a {@link JSONValue} or null if the {@code index} is out of range.
   */
  public JSONValue getNullValue(int index) {
    if (index < 0 || index >= list.size()) {
      return null;
    }
    return list.get(index);
  }

  /**
   * Create a JSON structure where the the root array don't contain another object, instead of it
   * contain the references to the objects. The reference is a field value of the object. The value
   * of the property that has the object is replaced with the value of the object property marked
   * like reference field. The reference field should not be an object or array.
   *
   * @return a new {@link JSONArray} structure with all the object referenced.
   */
  public JSONArray toReferencedTree() {
    return toReferencedElement();
  }

  /**
   * Retrieve a referenced {@link JSONArray}. For an {@code JSONArray} the referenced element is the
   * same than a referenced {@code JSONArray}, all the element of type
   * {@link net.cabezudo.json.values.JSONObject} will be replaces by the reference.
   *
   * @return a referenced {@code JSONArray}.
   */
  @Override
  public JSONArray toReferencedElement() {
    JSONArray jsonArray = new JSONArray();
    for (JSONElement jsonElement : list) {
      jsonArray.add(jsonElement.toReferencedElement());
    }
    return jsonArray;
  }

  /**
   * Retrieve the {@link JSONValue} of the element in the position {@code index} of {@code this}
   * {@code JSONArray}.
   *
   * @param index the position in the array for the element to retrieve.
   * @return a {@link JSONValue}.
   * @throws ElementNotExistException if the {@code index} is out of range.
   */
  public JSONValue getValue(int index) throws ElementNotExistException {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      throw new ElementNotExistException("The index " + index + " doesn't have a value.", getPosition());
    }
    return jsonValue;
  }

  /**
   * Tells whether or not {@code this} object is a has elements.
   *
   * @return {@code true} if {@code this} object has elements; {@code false} otherwise.
   */
  @Override
  public Boolean hasElements() {
    return !list.isEmpty();
  }

  /**
   * Returns whether the element is an array or not.
   *
   * @return {@code true} if the element is an array; {@code false} otherwise.
   */
  @Override
  public boolean isArray() {
    return true;
  }

  /**
   * Returns an iterator over the properties in {@code this} {@code JSONArray} in proper sequence.
   *
   * @return an iterator over the properties in {@code this} {@code JSONArray} in proper sequence.
   */
  @Override
  public Iterator<JSONValue> iterator() {
    return list.iterator();
  }

  /**
   * Replace the old value in {@code this} {@code JSONArray} for the passed in the position
   * indicated by {@code index}.
   *
   * @param index the position of the element to replace.
   * @param jsonValue the value in the position passed.
   * @return the {@link net.cabezudo.json.values.JSONValue} element previously at the specified
   * position.
   */
  public JSONValue setValue(int index, JSONValue jsonValue) {
    return list.set(index, jsonValue);
  }

  /**
   * Returns the number of properties in {@code this} {@code JSONArray}. If {@code this}
   * {@code JSONArray} contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of elements in {@code this} {@code JSONArray}.
   */
  public int size() {
    return list.size();
  }

  /**
   * Convert the elements of {@code this} {@code JSONArray} in an array of objects of type
   * {@link JSONValue}.
   *
   * @return an array of type {@link JSONValue} with the values of {@code this} {@code JSONArray}
   * elements.
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
   * Convert the elements of {@code this} {@code JSONArray} in an array of primitive {@code byte}.
   *
   * @return an array of type {@code byte} with the values of {@code this} {@code JSONArray}
   * elements.
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
   * Create a JSON string representation of {@code this} {@code JSONArray} including the JSON string
   * representation of the elements.
   *
   * @return a {@code String} representation of {@code this} {@code JSONArray}.
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
   * Return {@code this} object.
   *
   * @return {@code this} object
   */
  @Override
  public JSONArray toJSONArray() {
    return this;
  }

  /**
   * Return the JSON structure behind {@code this} object that is the reference to {@code this}
   * object.
   *
   * @return {@code this} object.
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   * Return a {@code List} of {@link JSONValue} with the values of {@code this} {@code JSONArray}
   * elements.
   *
   * @return a {@code List} of {@link JSONValue}.
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
   * Return an array of {@code String} objects with the values of {@code this} {@code JSONArray}
   * elements converted to {@code String}.
   *
   * @return an array of {@code String} objects.
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

  /**
   * Add all the objects in a {@code Iterable} list of POJO. The elements will be converted to JSON
   * elements using {@link JSON#toJSONTree(java.lang.Object)}.
   *
   * @param list an {@code Iterable} list of objects; that is an object that implements the
   * interface {@code Iterable}.
   *
   */
  public void addAll(Iterable list) {
    for (Object object : list) {
      internalAdd(object);
    }
  }

  /**
   * Return the value of the element in the position passed converted to {@code BigDecimal}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code BigDecimal}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public BigDecimal getBigDecimal(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigDecimal();
  }

  /**
   * Return the value of the element in the position passed converted to {@code BigInteger}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code BigInteger}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public BigInteger getBigInteger(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigInteger();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Boolean}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Boolean}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Boolean getBoolean(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBoolean();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Byte}. If the index
   * are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Byte}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Byte getByte(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toByte();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Calendar}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Calendar}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Calendar getCalendar(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCalendar();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Character}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Character}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Character getCharacter(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCharacter();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Double}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Double}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Double getDouble(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toDouble();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Float}. If the index
   * are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Float}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Float getFloat(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toFloat();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Long}. If the index
   * are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Long}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Long getLong(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toLong();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Integer}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Integer}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Integer getInteger(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toInteger();
  }

  /**
   * Return the value of the element in the position passed converted to {@link JSONObject}. If the
   * {@code index} value is out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@link JSONObject}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public JSONObject getObject(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toObject();
  }

  /**
   * Return the value of the element in the position passed converted to {@code Short}. If the index
   * are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code Short}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public Short getShort(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toShort();
  }

  /**
   * Return the value of the element in the position passed converted to {@code String}. If the
   * index are out of range throw a {@link ElementNotExistException}.
   *
   * @param index the index of the element to return.
   * @return a {@code String}.
   * @throws ElementNotExistException if the {@code index} value is out of range.
   */
  public String getString(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toString();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code BigDecimal}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code BigDecimal}.
   */
  public BigDecimal getNullBigDecimal(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code BigInteger}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code BigInteger}.
   */
  public BigInteger getNullBigInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code Boolean}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Boolean}.
   */
  public Boolean getNullBoolean(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to {@code Byte}.
   * If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Byte}.
   */
  public Byte getNullByte(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code Calendar}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Calendar}.
   */
  public Calendar getNullCalendar(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code Character}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Character}
   */
  public Character getNullCharacter(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code Double}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Double}.
   */
  public Double getNullDouble(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to {@code Float}.
   * If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Float}.
   */
  public Float getNullFloat(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to {@code Long}.
   * If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Long}.
   */
  public Long getNullLong(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code Integer}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Integer}.
   */
  public Integer getNullInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@link JSONObject}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@link JSONObject}.
   */
  public JSONObject getNullObject(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to {@code Short}.
   * If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code Short}.
   */
  public Short getNullShort(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   * Return the value of the element with the {@code index} value passed converted to
   * {@code String}. If {@code index} value is out of range return {@code null}.
   *
   * @param index the index of the element to return.
   * @return a {@code String}.
   */
  public String getNullString(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@link JSONValue}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@link JSONValue} with the property value or {@code null} if the element or property
   * doesn't exist.
   */
  public JSONValue digNullValue(String fullPropertyName) {
    return digNullValue(fullPropertyName, 1);
  }

  JSONValue digNullValue(String fullPropertyName, int oldPosition) {
    if (fullPropertyName == null || fullPropertyName.isEmpty()) {
      throw new IllegalArgumentException("Invalid parameter '" + fullPropertyName + "'.");
    }

    int point = fullPropertyName.indexOf('.');
    if (point == -1) {
      int index = getIndexFrom(fullPropertyName, oldPosition);
      JSONValue jsonValue = getNullValue(index);
      return jsonValue;
    } else {
      int p = point + 1;
      if (p >= fullPropertyName.length()) {
        throw new IllegalArgumentException("Invalid parameter '" + fullPropertyName + "'.");
      }
      int index = getIndexFrom(fullPropertyName, oldPosition);
      JSONValue nextLevelValue = getNullValue(index);
      if (nextLevelValue == null || !(nextLevelValue.isObject() || nextLevelValue.isArray())) {
        return null;
      }
      String nextPropertyName = fullPropertyName.substring(p);

      if (nextLevelValue.isObject()) {
        JSONObject nextLevelObject = nextLevelValue.toObject();
        return nextLevelObject.digNullValue(nextPropertyName, p + oldPosition);
      }
      if (nextLevelValue.isArray()) {
        JSONArray nextLevelArray = nextLevelValue.toJSONArray();
        return nextLevelArray.digNullValue(nextPropertyName, p + oldPosition);
      }
    }
    throw new RuntimeException("The next level value is not an object nor an array.");
  }

  private int getIndexFrom(String property, int oldPosition) {
    char c = property.charAt(0);
    if (c != '[') {
      throw new RuntimeException("Invalid format for property. Expect a left bracket ([) and have a '" + c + "' in position " + oldPosition + ".");
    }
    int pointPosition = property.indexOf('.');
    int lastPosition;
    if (pointPosition == -1) {
      lastPosition = property.length() - 1;
    } else {
      lastPosition = pointPosition - 1;
    }
    c = property.charAt(lastPosition);
    if (c != ']') {
      throw new RuntimeException("Invalid format for property. Expect a left bracket (]) and have a '" + c + "' in position " + (oldPosition + lastPosition) + ".");
    }
    String stringIndex = property.substring(1, lastPosition);

    try {
      int index = Integer.parseInt(stringIndex);
      return index;
    } catch (NumberFormatException e) {
      throw new RuntimeException("Invalid format for index property '" + stringIndex + "' in position  " + (oldPosition + 1) + ".");
    }
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code BigDecimal}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code BigDecimal} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public JSONValue digValue(String fullPropertyName) throws PropertyNotExistException {
    JSONValue value = digNullValue(fullPropertyName);
    if (value == null) {
      throw new PropertyNotExistException("The property " + fullPropertyName + " doesn't exist.", getPosition());
    }
    return value;
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code BigDecimal}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code BigDecimal} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public BigDecimal digBigDecimal(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toBigDecimal();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code BigInteger}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code BigInteger} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public BigInteger digBigInteger(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toBigInteger();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Boolean}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Boolean} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Boolean digBoolean(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toBoolean();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Byte}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Byte} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Byte digByte(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toByte();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Calendar}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Calendar} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Calendar digCalendar(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toCalendar();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Character}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Character} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Character digCharacter(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toCharacter();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Double}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Double} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Double digDouble(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toDouble();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Float}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Float} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Float digFloat(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toFloat();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Long}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Long} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Long digLong(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toLong();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Integer}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Integer} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Integer digInteger(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toInteger();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@link JSONObject}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@link JSONObject} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public JSONObject digObject(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toObject();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Short}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Short} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public Short digShort(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toShort();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code String}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code String} with the property or element.
   * @throws PropertyNotExistException if the {@code index} value is out of range.
   */
  public String digString(String fullPropertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(fullPropertyName);
    return jsonValue.toString();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code BigDecimal}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code BigDecimal} with the property value, the element or {@code null} if the
   * element; or property doesn't exist.
   */
  public BigDecimal digNullBigDecimal(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code BigInteger}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code BigInteger} with the property value, the element or {@code null} if the
   * element or property doesn't exist.
   */
  public BigInteger digNullBigInteger(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Boolean}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Boolean} with the property value or, the element {@code null} if the element
   * or property doesn't exist.
   */
  public Boolean digNullBoolean(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Byte}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Byte} with the property value, the element or {@code null} if the element or
   * property doesn't exist.
   */
  public Byte digNullByte(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Calendar}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Calendar} with the property value, the element or {@code null} if the element
   * or property doesn't exist.
   */
  public Calendar digNullCalendar(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Character}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Character} with the property value, the element or {@code null} if the element
   * or property doesn't exist.
   */
  public Character digNullCharacter(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Double}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Double} with the property value, the element or {@code null} if the element or
   * property doesn't exist.
   */
  public Double digNullDouble(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Float}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Float} with the property value, the element or {@code null} if the element or
   * property doesn't exist.
   */
  public Float digNullFloat(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Long}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Long} with the property value, the element or {@code null} if the element or
   * property doesn't exist.
   */
  public Long digNullLong(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Integer}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Integer} with the property value, the element or {@code null} if the element
   * or property doesn't exist.
   */
  public Integer digNullInteger(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@link JSONObject}. The position of elements in an array are specified using the index in
   * brackets and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@link JSONObject} with the property value, the element or {@code null} if the
   * element or property doesn't exist.
   */
  public JSONObject digNullObject(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code Short}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code Short} with the property value, the element or {@code null} if the element or
   * property doesn't exist.
   */
  public Short digNullShort(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   * Dig into {@code this} {@code JSONArray} object to find a element or property to convert to
   * {@code String}. The position of elements in an array are specified using the index in brackets
   * and the index and the objects properties are separated by dots. Example:
   * population.[343].childs.[2]
   *
   * @param fullPropertyName The full path of the property or element to search.
   * @return a {@code String} with the property value, the element or {@code null} if the element or
   * property doesn't exist.
   */
  public String digNullString(String fullPropertyName) {
    JSONValue jsonValue = digNullValue(fullPropertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }
}
