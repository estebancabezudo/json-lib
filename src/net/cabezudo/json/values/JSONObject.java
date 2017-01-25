package net.cabezudo.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import net.cabezudo.json.JSON;
import net.cabezudo.json.JSONElement;
import net.cabezudo.json.JSONPair;
import net.cabezudo.json.Position;
import net.cabezudo.json.exceptions.JSONParseException;
import net.cabezudo.json.exceptions.PropertyNotExistException;

/**
 * A {@link JSONObject} is an object extended from {@link JSONValue} object in order to represent a
 * JSON object that can be used to create JSON structures.
 *
 * <p>
 * A {@link JSONObject} is a list of {@link JSONPair} objects that represent the JSON object
 * structure.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONObject extends JSONValue<JSONObject> implements Iterable<JSONPair> {

  private final Set<String> keys = new TreeSet<>();

  private final List<JSONPair> list = new ArrayList<>();
  private final Map<String, JSONPair> map = new HashMap<>();

  /**
   * Create a newly {@link JSONObject} object using a JSON string.
   *
   * <p>
   * This constructor parse a string passed by parameter in order to create the {@link JSONObject}
   * that represent the JSON object strcucture.
   *
   * @param data The JSON string to parse.
   * @throws JSONParseException if the string passed by parameter can not be parsed.
   */
  public JSONObject(String data) throws JSONParseException {
    super(null);
    JSONValue jsonData = JSON.parse(data);
    if (jsonData instanceof JSONObject) {
      JSONObject jsonObject = (JSONObject) jsonData;
      copy(jsonObject);
    }
  }

  /**
   * Construct an empty {@link JSONObject} object.
   * <p>
   * A empty {@link JSONObject} object can be used to create a more complex object by adding
   * {@link JSONPair} objects.
   *
   */
  public JSONObject() {
    super(null);
    // Nothing to do here. Just for convenience.
  }

  /**
   * Construct an empty {@link JSONObject} object.
   * <p>
   * A empty {@link JSONObject} object can be used to create a more complex object by adding
   * {@link JSONPair} objects.
   *
   * @param position The position for the {@link JSONObject} in the JSON string.
   */
  public JSONObject(Position position) {
    super(position);
  }

  /**
   * Construct a {@link JSONObject} object using the array of {@link JSONPair} objects to create the
   * JSON object properties.
   *
   * @param jsonPairs the {@link JSONPair} objects to create the JSON object properties
   */
  public JSONObject(JSONPair... jsonPairs) {
    super(null);
    for (JSONPair jsonPair : jsonPairs) {
      privateAdd(jsonPair);
    }
  }

  /**
   * Construct a {@link JSONObject} object using the properties of another {@link JSONObject}
   * object.
   *
   * <p>
   * Create a copy of the {@link JSONObject} object passed by parameter.
   *
   * @param jsonObject the {@link JSONObject} object which to take the properties.
   */
  public JSONObject(JSONObject jsonObject) {
    super(jsonObject.getPosition());
    copy(jsonObject);
  }

  /**
   * Construct a {@link JSONObject} object from a Java {@code Object} parameter using the
   * {@link JSON#toJSONTree(java.lang.Object)}.
   * <p>
   * If the object is
   *
   * @param object a Java {@code Object}
   */
  public JSONObject(Object object) {
    this(JSON.toJSONTree(object).toObject());
  }

  private void copy(JSONObject jsonObject) {
    for (JSONPair jsonPair : jsonObject.list) {
      this.add(jsonPair);
    }
  }

  private List<String> getKeyList() {
    return new ArrayList(keys);
  }

  private JSONPair privateAdd(JSONPair jsonPair) {
    String key = jsonPair.getKey();
    if (map.containsKey(key)) {
      throw new RuntimeException("The object " + this.toJSON() + " already has the key " + key + ".");
    }
    list.add(jsonPair);
    keys.add(key);
    return map.put(key, jsonPair);
  }

  /**
   *
   * @param jsonPair
   * @return
   */
  public JSONPair add(JSONPair jsonPair) {
    return privateAdd(jsonPair);
  }

  @Override
  public int compareTo(JSONObject jsonObject) {
    Integer a = this.size();
    Integer b = jsonObject.size();
    int c = a.compareTo(b);

    if (c != 0) {
      return c;
    }

    List<String> keyListOfThis = this.getKeyList();
    List<String> keyListOfObject = jsonObject.getKeyList();

    int size = keyListOfThis.size();

    for (int i = 0; i < size; i++) {
      String sa = keyListOfThis.get(i);
      String sb = keyListOfObject.get(i);
      c = sa.compareTo(sb);
      if (c != 0) {
        return c;
      }
    }

    for (int i = 0; i < size; i++) {
      JSONValue va;
      JSONValue vb;
      String key = keyListOfThis.get(i);
      try {
        va = this.getValue(key);
        vb = jsonObject.getValue(key);
      } catch (PropertyNotExistException e) {
        throw new RuntimeException(e);
      }
      c = va.compareTo(vb);
      if (c != 0) {
        return c;
      }
    }
    return 0;
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public JSONPair remove(String propertyName) {
    JSONPair element = map.get(propertyName);
    list.remove(element);
    keys.remove(propertyName);
    return map.remove(propertyName);
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONPair deleteElement(int index) {
    JSONPair element = list.get(index);
    list.remove(element);
    String propertyName = element.getKey();
    keys.remove(propertyName);
    return map.remove(propertyName);
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Boolean digBoolean(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toBoolean();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Byte digByte(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toByte();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Character digCharacter(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toCharacter();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Double digDouble(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toDouble();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Float digFloat(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toFloat();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Integer digInteger(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toInteger();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Long digLong(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toLong();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Boolean digNullBoolean(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Byte digNullByte(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Character digNullCharacter(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Double digNullDouble(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Float digNullFloat(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Integer digNullInteger(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Long digNullLong(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public JSONObject digNullObject(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Short digNullShort(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public String digNullString(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
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
      JSONValue jsonValue = getNullValue(propertyFullName);
      return jsonValue;
    } else {
      String propertyName = propertyFullName.substring(0, point);
      int newStartPosition = point + 1;
      if (newStartPosition >= propertyFullName.length()) {
        throw new IllegalArgumentException("Invalid parameter '" + propertyFullName + "'.");
      }
      JSONValue nextLevelValue = getNullValue(propertyName);
      if (nextLevelValue == null || !(nextLevelValue.isObject() || nextLevelValue.isArray())) {
        return null;
      }
      String nextPropertyName = propertyFullName.substring(newStartPosition);

      if (nextLevelValue.isObject()) {
        JSONObject nextLevelObject = nextLevelValue.toObject();
        return nextLevelObject.digNullValue(nextPropertyName, newStartPosition + oldPosition);
      }
      if (nextLevelValue.isArray()) {
        JSONArray nextLevelArray = nextLevelValue.toJSONArray();
        return nextLevelArray.digNullValue(nextPropertyName, newStartPosition + oldPosition);
      }
      throw new RuntimeException("The next level value is not an object nor an array.");
    }
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public JSONObject digObject(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toObject();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Short digShort(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toShort();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public String digString(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toString();
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

  public JSONValue[] getArray(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);

    return jsonValue.toArray();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public BigDecimal getBigDecimal(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toBigDecimal();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public BigDecimal getBigDecimal(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigDecimal();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public BigInteger getBigInteger(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toBigInteger();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public BigInteger getBigInteger(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigInteger();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Boolean getBoolean(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toBoolean();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Boolean getBoolean(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBoolean();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Byte getByte(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toByte();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Byte getByte(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toByte();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public byte[] getByteArray(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toByteArray();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public byte[] getByteArray(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toByteArray();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Calendar getCalendar(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toCalendar();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Calendar getCalendar(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCalendar();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Character getCharacter(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toCharacter();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Character getCharacter(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCharacter();
  }

  /**
   *
   * @return
   */
  public List<JSONPair> getChilds() {
    return Collections.unmodifiableList(list);
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Double getDouble(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toDouble();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Double getDouble(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toDouble();
  }

  /**
   * Return the element asociated with the property name. For an objet the element is a
   * {@link net.cabezudo.java.json.values.JSONPair JSONArray} formed by the property name and the
   * value.
   *
   * @param propertyName the name of the property to get
   * @return an object {@link net.cabezudo.java.json.values.JSONPair JSONArray} with the pair
   * data/value in the object with the property name specified.
   * @throws net.cabezudo.json.exceptions.PropertyNotExistException if the property specified don't
   * exist in the object
   */
  public JSONPair getElement(String propertyName) throws PropertyNotExistException {
    JSONPair jsonPair = getNullElement(propertyName);
    if (jsonPair == null) {
      throw new PropertyNotExistException("The property " + propertyName + " doesn't exist.", getPosition());
    }
    return jsonPair;
  }

  /**
   *
   * @param propertyIndex
   * @return
   * @throws PropertyNotExistException
   */
  public JSONPair getElement(int propertyIndex) throws PropertyNotExistException {
    JSONPair jsonPair = getNullElement(propertyIndex);
    if (jsonPair == null) {
      throw new PropertyNotExistException("The property " + propertyIndex + " doesn't exist.", getPosition());
    }
    return jsonPair;
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Float getFloat(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toFloat();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Float getFloat(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toFloat();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Integer getInteger(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toInteger();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Integer getInteger(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toInteger();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws net.cabezudo.json.exceptions.PropertyNotExistException
   */
  public JSONArray getJSONArray(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public JSONArray getJSONArray(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Long getLong(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toLong();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Long getLong(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toLong();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public BigDecimal getNullBigDecimal(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  /**
   *
   * @param index
   * @return
   */
  public BigDecimal getNullBigDecimal(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public BigInteger getNullBigInteger(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  /**
   *
   * @param index
   * @return
   */
  public BigInteger getNullBigInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Boolean getNullBoolean(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   *
   * @param index
   * @return
   */
  public Boolean getNullBoolean(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Byte getNullByte(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   *
   * @param index
   * @return
   */
  public Byte getNullByte(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public byte[] getNullByteArray(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByteArray();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Calendar getNullCalendar(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  /**
   *
   * @param index
   * @return
   */
  public Calendar getNullCalendar(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Character getNullCharacter(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   *
   * @param index
   * @return
   */
  public Character getNullCharacter(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Double getNullDouble(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   *
   * @param index
   * @return
   */
  public Double getNullDouble(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public JSONPair getNullElement(String propertyName) {
    JSONPair jsonPair = map.get(propertyName);
    if (jsonPair == null) {
      return null;
    }
    return jsonPair;
  }

  /**
   *
   * @param propertyIndex
   * @return
   */
  public JSONPair getNullElement(int propertyIndex) {
    JSONPair jsonPair = list.get(propertyIndex);
    if (jsonPair == null) {
      return null;
    }
    return jsonPair;
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Float getNullFloat(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   *
   * @param index
   * @return
   */
  public Float getNullFloat(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Integer getNullInteger(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   *
   * @param index
   * @return
   */
  public Integer getNullInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public JSONArray getNullJSONArray(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONArray getNullJSONArray(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Long getNullLong(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   *
   * @param index
   * @return
   */
  public Long getNullLong(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public JSONObject getNullObject(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONObject getNullObject(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public Short getNullShort(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   *
   * @param index
   * @return
   */
  public Short getNullShort(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public String getNullString(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   *
   * @param index
   * @return
   */
  public String getNullString(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   *
   * @param propertyName
   * @return
   */
  public JSONValue getNullValue(String propertyName) {
    if (propertyName == null || propertyName.isEmpty()) {
      throw new IllegalArgumentException("Invalid parameter '" + propertyName + "'.");
    }
    JSONPair jsonPair = map.get(propertyName);
    if (jsonPair == null) {
      return null;
    }
    JSONValue jsonValue = jsonPair.getValue();
    return jsonValue;
  }

  /**
   *
   * @param index
   * @return
   */
  public JSONValue getNullValue(int index) {
    JSONPair jsonPair = list.get(index);
    if (jsonPair == null) {
      return null;
    }
    return jsonPair.getValue();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public JSONObject getObject(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toObject();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public JSONObject getObject(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toObject();
  }

  /**
   *
   * @return
   */
  @Override
  public JSONValue toReferencedElement() {
    String referenceFieldNameToSearch = getReferenceFieldName();
    for (JSONPair jsonPair : list) {
      String keyName = jsonPair.getKey();
      if (keyName.equals(referenceFieldNameToSearch)) {
        JSONValue jsonReferenceValue = jsonPair.getValue();
        return jsonReferenceValue;
      }
    }
    JSONObject jsonObject = new JSONObject();
    if (list.size() > 0) {
      for (JSONPair jsonPair : list) {
        jsonObject.add(jsonPair.toReferencedElement());
      }
    }
    return jsonObject;
  }

  /**
   * Create a JSON structure where the the root object don't contain another object structure,
   * instead of it contain the references to the property value objects. The reference is a field
   * value of the object. The value of the property that has the object is replaced with the value
   * of the object property marked like reference field. The reference field must not be an object
   * or array.
   *
   * @return a new {@link net.cabezudo.json.values.JSONObject} structure with all the object
   * referenced.
   */
  public JSONObject toReferencedObject() {
    JSONObject jsonReferencedObject = new JSONObject();

    for (JSONPair jsonPair : list) {
      JSONElement referencedElement = jsonPair.getValue().toReferencedElement();

      JSONPair newJSONPair = new JSONPair(jsonPair.getKey(), referencedElement, getPosition());
      jsonReferencedObject.add(newJSONPair);
    }

    return jsonReferencedObject;
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public Short getShort(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toShort();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public Short getShort(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toShort();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public String getString(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toString();
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public String getString(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toString();
  }

  /**
   *
   * @param propertyName
   * @return
   * @throws PropertyNotExistException
   */
  public JSONValue getValue(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      throw new PropertyNotExistException("The property " + propertyName + " doesn't exist.", getPosition());
    }
    return jsonValue;
  }

  /**
   *
   * @param index
   * @return
   * @throws PropertyNotExistException
   */
  public JSONValue getValue(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      throw new PropertyNotExistException("The position " + index + " don't have a value.", getPosition());
    }
    return jsonValue;
  }

  /**
   *
   * @return
   */
  public boolean hasChilds() {
    return !list.isEmpty();
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isObject() {
    return true;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isReferenciable() {
    return true;
  }

  @Override
  public Iterator<JSONPair> iterator() {
    return list.iterator();
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
    for (JSONPair jsonPair : list) {
      JSONValue value = jsonPair.getValue();
      array[i] = value;
      i++;
    }
    return array;
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    StringBuilder sb = new StringBuilder("{ ");
    if (map.size() > 0) {
      for (JSONPair jsonPair : list) {
        sb.append(jsonPair.toJSON());
        sb.append(", ");
      }
      sb = new StringBuilder(sb.substring(0, sb.length() - 2));
    }
    sb.append(" }");
    return sb.toString();
  }

  /**
   *
   * @return
   */
  @Override
  public JSONArray toJSONArray() {
    JSONArray jsonArray = new JSONArray();
    for (JSONPair jsonPair : list) {
      jsonArray.add(jsonPair.getValue());
    }
    return jsonArray;
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
  public JSONObject toObject() {
    return this;
  }

  @Override
  public String toString() {
    return toJSON();
  }
}
