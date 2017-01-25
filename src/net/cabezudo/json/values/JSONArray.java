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
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @date 10/01/2014
 * @version 1.0
 * @since 1.7
 */
public class JSONArray extends JSONValue<JSONArray> implements Iterable<JSONValue> {

  private final List<JSONValue> list = new ArrayList<>();

  /**
   * Construct an empty JSON array object.
   *
   * @param position
   */
  public JSONArray(Position position) {
    // Nothing to do here. Just needed for create an empty array.
    super(position);
  }

  /**
   *
   * @param valuesList
   */
  public JSONArray(List<JSONValue> valuesList) {
    super(null);
    for (JSONValue jsonValue : valuesList) {
      internalAdd(jsonValue);
    }
  }

  /**
   *
   * @param jsonValues
   */
  public JSONArray(JSONValue... jsonValues) {
    super(null);
    for (JSONValue jsonValue : jsonValues) {
      internalAdd(jsonValue);
    }
  }

  /**
   *
   * @param objects
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
  public JSONElement remove(int index) {
    return list.remove(index);
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
   * Create a JSON structure where the the root array don't contain another object, instead of it
   * contain the references to the objects. The reference is a field value of the object. The value
   * of the property that has the object is replaced with the value of the object property marked
   * like reference field. The reference field should not be an object or array.
   *
   * @return a new {@link net.cabezudo.json.values.JSONArray} structure with all the object
   * referenced.
   */
  public JSONArray toReferencedTree() {
    return toReferencedElement();
  }

  /**
   *
   * @return
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
   *
   * @param index
   * @return
   * @throws net.cabezudo.json.exceptions.ElementNotExistException
   */
  public JSONValue getValue(int index) throws ElementNotExistException {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      throw new ElementNotExistException("The index " + index + " doesn't have a value.", getPosition());
    }
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

  /**
   * Returns whether the element is an array or not.
   *
   * @return <code>true</code> if the element is an array, <code>false</code> otherwise.
   */
  @Override
  public boolean isArray() {
    return true;
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
   * Create a JSON string representation of this {@code JSONArray} including the JSON string
   * representation of the elements.
   *
   * @return a {@code String} representation of this {@code JSONArray}.
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

  public void addAll(Iterable list) {
    for (Object object : list) {
      internalAdd(object);
    }
  }

  public BigDecimal getBigDecimal(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigDecimal();
  }

  public BigInteger getBigInteger(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigInteger();
  }

  public Boolean getBoolean(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBoolean();
  }

  public Byte getByte(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toByte();
  }

  public Calendar getCalendar(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCalendar();
  }

  public Character getCharacter(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCharacter();
  }

  public Double getDouble(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toDouble();
  }

  public Float getFloat(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toFloat();
  }

  public Long getLong(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toLong();
  }

  public Integer getInteger(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toInteger();
  }

  public JSONObject getObject(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toObject();
  }

  public Short getShort(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toShort();
  }

  public String getString(int index) throws ElementNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toString();
  }

  public BigDecimal getNullBigDecimal(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  public BigInteger getNullBigInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  public Boolean getNullBoolean(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  public Byte getNullByte(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  public Calendar getNullCalendar(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  public Character getNullCharacter(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  public Double getNullDouble(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  public Float getNullFloat(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  public Long getNullLong(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  public Integer getNullInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  public JSONObject getNullObject(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  public Short getNullShort(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  public String getNullString(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   *
   * @param propertyFullName
   * @return
   */
  public JSONValue digNullValue(String propertyFullName) {
    return digNullValue(propertyFullName, 1);
  }

  /**
   *
   * @param propertyFullName
   * @param oldPosition
   * @return
   */
  protected JSONValue digNullValue(String propertyFullName, int oldPosition) {
    if (propertyFullName == null || propertyFullName.isEmpty()) {
      throw new IllegalArgumentException("Invalid parameter '" + propertyFullName + "'.");
    }

    int point = propertyFullName.indexOf('.');
    if (point == -1) {
      int index = getIndexFrom(propertyFullName, oldPosition);
      JSONValue jsonValue = getNullValue(index);
      return jsonValue;
    } else {
      String propertyName = propertyFullName.substring(0, point);
      int p = point + 1;
      if (p >= propertyFullName.length()) {
        throw new IllegalArgumentException("Invalid parameter '" + propertyFullName + "'.");
      }
      int index = getIndexFrom(propertyFullName, oldPosition);
      JSONValue nextLevelValue = getNullValue(index);
      if (nextLevelValue == null || !(nextLevelValue.isObject() || nextLevelValue.isArray())) {
        return null;
      }
      String nextPropertyName = propertyFullName.substring(p);

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
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public JSONValue digValue(String propertyName) throws PropertyNotExistException {
    JSONValue value = digNullValue(propertyName);
    if (value == null) {
      throw new PropertyNotExistException("The property " + propertyName + " doesn't exist.", getPosition());
    }
    return value;
  }

  public BigDecimal digBigDecimal(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toBigDecimal();
  }

  public BigInteger digBigInteger(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toBigInteger();
  }

  public Boolean digBoolean(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toBoolean();
  }

  public Byte digByte(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toByte();
  }

  public Calendar digCalendar(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toCalendar();
  }

  public Character digCharacter(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toCharacter();
  }

  public Double digDouble(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toDouble();
  }

  public Float digFloat(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toFloat();
  }

  public Long digLong(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toLong();
  }

  public Integer digInteger(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toInteger();
  }

  public JSONObject digObject(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toObject();
  }

  public Short digShort(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toShort();
  }

  public String digString(String propertyFullName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyFullName);
    return jsonValue.toString();
  }

  public BigDecimal digNullBigDecimal(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  public BigInteger digNullBigInteger(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  public Boolean digNullBoolean(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  public Byte digNullByte(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  public Calendar digNullCalendar(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  public Character digNullCharacter(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  public Double digNullDouble(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  public Float digNullFloat(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  public Long digNullLong(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  public Integer digNullInteger(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  public JSONObject digNullObject(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  public Short digNullShort(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  public String digNullString(String propertyFullName) {
    JSONValue jsonValue = digNullValue(propertyFullName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }
}
