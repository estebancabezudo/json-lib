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
   * Construct a {@link JSONObject} object from a POJO {@code Object} using the
   * {@link JSON#toJSONTree(java.lang.Object)}. The object must have the properties annotated with
   * {@link net.cabezudo.json.annotations.JSONProperty} in order to be used as a JSON object
   * property. If the object is {@code Iterable} or the object is a primitive array the method throw
   * a {@link net.cabezudo.json.exceptions.JSONCastException}.
   *
   * @param object a POJO {@code Object}.
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
   * Add a pair to the list of properties of this object.
   *
   * @param jsonPair a {@link net.cabezudo.json.JSONPair}.
   * @return the same {@link net.cabezudo.json.JSONPair} passed.
   */
  public JSONPair add(JSONPair jsonPair) {
    return privateAdd(jsonPair);
  }

  /**
   *
   * Compares two {@code JSONObject} objects.
   * <p>
   * The rules for comparation are the next.
   * <p>
   * The method first compare the number of properties and return a value less than {@code 0} if
   * this {@code JSONObject} has less properties than the argument and a value greater than
   * {@code 0} if this {@code JSONObject} has more properties than the argument.
   * <p>
   * If the comparation is {@code 0} compare the keys in natural order. Compare one at a time the
   * keys and the result is a negative integer if the property name of a this {@code JSONObject}
   * object lexicographically precedes the property name of the argument @code JSONObject}. The
   * result is a positive integer if this {@code JSONObject}} object lexicographically follows the
   * argument property name in the same position. The result is zero if all the property names are
   * equal and in the same order.
   * <p>
   * If the properties names are the same, compare the values. The method use the property order to
   * compare the values. Compare one at a time the values and the result is a negative integer if
   * the property value of this {@code JSONObject} object is less than the property value of the
   * argument @code JSONObject} object for the same property. The result is a positive integer the
   * property value if this {@code JSONObject} object is great than the property value of the
   * argument @code JSONObject} object for the same property. The result is zero if the property
   * values are equals for all the properties.
   *
   * @param jsonObject the {@code JSONObject} to be compared.
   *
   * @return the value {@code 0} if this {@code JSONObject} is equal to the argument
   * {@code JSONObject}; a value less than {@code 0} if this {@code JSONObject} is less (using the
   * rules) than the argument {@code JSONObject}; and a value greater than {@code 0} if this
   * {@code JSONObject} is greater (using the rules) than the argument {@code Integer}.
   */
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
   * Remove a property from this {@code JSONObject} object using the property name.
   *
   * @param propertyName the name of the property to remove.
   * @return the {@code JSONPair} object removed from this {@code JSONObject} object.
   */
  public JSONPair remove(String propertyName) {
    JSONPair element = map.get(propertyName);
    list.remove(element);
    keys.remove(propertyName);
    return map.remove(propertyName);
  }

  /**
   * Remove a property from this {@code JSONObject} object using the position of the property.
   *
   * @param index a{@code int} with the position of the property in this {@code JSONObject} object.
   * @return the {@code JSONPair} object removed from this {@code JSONObject} object.
   */
  public JSONPair remove(int index) {
    JSONPair element = list.get(index);
    list.remove(element);
    String propertyName = element.getKey();
    keys.remove(propertyName);
    return map.remove(propertyName);
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Boolean}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Boolean} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Boolean digBoolean(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toBoolean();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Byte}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Byte} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Byte digByte(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toByte();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Character}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Character} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Character digCharacter(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toCharacter();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Double}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Double} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Double digDouble(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toDouble();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Float}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Float} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Float digFloat(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toFloat();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Integer}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Integer} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Integer digInteger(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toInteger();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Long}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Long} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Long digLong(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toLong();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Boolean}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Boolean} with the property value or {@code null} if the property doesn't
   * exist.
   */
  public Boolean digNullBoolean(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Byte}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Byte} with the property value or {@code null} if the property doesn't exist.
   */
  public Byte digNullByte(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Character}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Character} with the property value or {@code null} if the property doesn't
   * exist.
   */
  public Character digNullCharacter(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Double}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Double} with the property value or {@code null} if the property doesn't exist.
   */
  public Double digNullDouble(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Float}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Float} with the property value or {@code null} if the property doesn't exist.
   */
  public Float digNullFloat(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Integer}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Integer} with the property value or {@code null} if the property doesn't
   * exist.
   */
  public Integer digNullInteger(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Long}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Long} with the property value or {@code null} if the property doesn't exist.
   */
  public Long digNullLong(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code JSONObject}.
   * The properties are separated by dots and the position of elements in an array are specified
   * using the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code JSONObject} with the property value or {@code null} if the property doesn't
   * exist.
   */
  public JSONObject digNullObject(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Short}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Short} with the property value or {@code null} if the property doesn't exist.
   */
  public Short digNullShort(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code String}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code String} with the property value or {@code null} if the property doesn't exist.
   */
  public String digNullString(String propertyName) {
    JSONValue jsonValue = digNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code JSONValue}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyFullName The path of the property to search.
   * @return a {@code JSONValue} with the property value or {@code null} if the property doesn't
   * exist.
   */
  public JSONValue digNullValue(String propertyFullName) {
    return digNullValue(propertyFullName, 1);
  }

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
   * Dig into this {@code JSONObject} object to find a property to convert to {@code JSONObject}.
   * The properties are separated by dots and the position of elements in an array are specified
   * using the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code JSONObject} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public JSONObject digObject(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toObject();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code Short}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code Short} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Short digShort(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toShort();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property to convert to {@code String}. The
   * properties are separated by dots and the position of elements in an array are specified using
   * the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code String} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public String digString(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = digValue(propertyName);
    return jsonValue.toString();
  }

  /**
   * Dig into this {@code JSONObject} object to find a property and return a {@code JSONValue}
   * object. The properties are separated by dots and the position of elements in an array are
   * specified using the index in brackets. Example: person.childs.[3].name
   *
   * @param propertyName The path of the property to search.
   * @return a {@code JSONValue} with the property value.
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public JSONValue digValue(String propertyName) throws PropertyNotExistException {
    JSONValue value = digNullValue(propertyName);
    if (value == null) {
      throw new PropertyNotExistException("The property " + propertyName + " doesn't exist.", getPosition());
    }
    return value;
  }

  /**
   * Return the value of the property with the name passed converted to {@code JSONValue[]}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code JSONValue[]}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public JSONValue[] getArray(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);

    return jsonValue.toArray();
  }

  /**
   * Return the value of the property with the name passed converted to {@code BigDecimal}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code BigDecimal}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public BigDecimal getBigDecimal(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toBigDecimal();
  }

  /**
   * Return the value of the property with the index passed converted to {@code BigDecimal}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code BigDecimal}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public BigDecimal getBigDecimal(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigDecimal();
  }

  /**
   * Return the value of the property with the name passed converted to {@code BigInteger}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code BigInteger}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public BigInteger getBigInteger(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toBigInteger();
  }

  /**
   * Return the value of the property with the index passed converted to {@code BigInteger}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code BigInteger}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public BigInteger getBigInteger(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBigInteger();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Boolean}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Boolean}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Boolean getBoolean(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toBoolean();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Boolean}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Boolean}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Boolean getBoolean(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toBoolean();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Byte}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Byte}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Byte getByte(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toByte();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Byte}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Byte}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Byte getByte(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toByte();
  }

  /**
   * Return the value of the property with the name passed converted to {@code byte[]}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code byte[]}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public byte[] getByteArray(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toByteArray();
  }

  /**
   * Return the value of the property with the index passed converted to {@code byte[]}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code byte[]}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public byte[] getByteArray(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toByteArray();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Calendar}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Calendar}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Calendar getCalendar(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toCalendar();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Calendar}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Calendar}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Calendar getCalendar(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCalendar();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Character}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Character}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Character getCharacter(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toCharacter();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Character}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Character}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Character getCharacter(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toCharacter();
  }

  /**
   * Return a unmodifiable list of all childs of type {@link net.cabezudo.json.JSONPair} of the
   * object in the natural order.
   *
   * @return a {@code List<JSONPair>} objecto with the chids.
   */
  public List<JSONPair> getChilds() {
    return Collections.unmodifiableList(list);
  }

  /**
   * Return the value of the property with the name passed converted to {@code Double}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Double}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Double getDouble(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toDouble();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Double}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Double}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Double getDouble(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toDouble();
  }

  /**
   * Return the element asociated with the property name. For an objet the element is a
   * {@link net.cabezudo.java.json.values.JSONPair} formed by the property name and the value.
   *
   * @param propertyName the name of the property to get
   * @return an object {@link net.cabezudo.java.json.values.JSONPair JSONArray} with the pair
   * data/value in the object with the property name specified.
   * @throws PropertyNotExistException if the property specified don't exist in the object
   */
  public JSONPair getElement(String propertyName) throws PropertyNotExistException {
    JSONPair jsonPair = getNullElement(propertyName);
    if (jsonPair == null) {
      throw new PropertyNotExistException("The property " + propertyName + " doesn't exist.", getPosition());
    }
    return jsonPair;
  }

  /**
   * Return the element in the position specified. For an objet the element is a
   * {@link net.cabezudo.java.json.values.JSONPair} formed by the property name and the value.
   *
   * @param index the index of the property
   * @return an object {@link net.cabezudo.java.json.values.JSONPair} with the pair data/value in
   * the object with the index specified.
   * @throws PropertyNotExistException if the index is out of range
   */
  public JSONPair getElement(int index) throws PropertyNotExistException {
    JSONPair jsonPair = getNullElement(index);
    if (jsonPair == null) {
      throw new PropertyNotExistException("The property " + index + " doesn't exist.", getPosition());
    }
    return jsonPair;
  }

  /**
   * Return the value of the property with the name passed converted to {@code Float}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Float}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Float getFloat(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toFloat();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Float}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Float}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Float getFloat(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toFloat();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Integer}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Integer}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Integer getInteger(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toInteger();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Integer}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Integer}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Integer getInteger(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toInteger();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.values.JSONArray}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONArray}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public JSONArray getJSONArray(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.values.JSONArray}. If the property doesn't exist throw a
   * {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONArray}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public JSONArray getJSONArray(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Long}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Long}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Long getLong(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toLong();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Long}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Long}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Long getLong(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toLong();
  }

  /**
   * Return the value of the property with the name passed converted to {@code BigDecimal}. Return
   * {@code null} if the property doen't exist.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code BigDecimal}
   */
  public BigDecimal getNullBigDecimal(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  /**
   * Return the value of the property with the index passed converted to {@code BigDecimal}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code BigDecimal}
   */
  public BigDecimal getNullBigDecimal(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigDecimal();
  }

  /**
   * Return the value of the property with the name passed converted to {@code BigInteger}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code BigInteger}
   */
  public BigInteger getNullBigInteger(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  /**
   * Return the value of the property with the index passed converted to {@code BigInteger}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code BigInteger}
   */
  public BigInteger getNullBigInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBigInteger();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Boolean}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Boolean}
   */
  public Boolean getNullBoolean(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Boolean}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Boolean}
   */
  public Boolean getNullBoolean(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toBoolean();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Byte}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Byte}
   */
  public Byte getNullByte(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Byte}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Byte}
   */
  public Byte getNullByte(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByte();
  }

  /**
   * Return the value of the property with the name passed converted to {@code byte[]}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code byte[]}
   */
  public byte[] getNullByteArray(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toByteArray();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Calendar}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Calendar}
   */
  public Calendar getNullCalendar(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Calendar}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Calendar}
   */
  public Calendar getNullCalendar(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCalendar();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Character}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Character}
   */
  public Character getNullCharacter(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toCharacter();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Character}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
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
   * Return the value of the property with the name passed converted to {@code Double}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Double}
   */
  public Double getNullDouble(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Double}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Double}
   */
  public Double getNullDouble(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toDouble();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.JSONPair}. If the property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.JSONPair}
   */
  public JSONPair getNullElement(String propertyName) {
    JSONPair jsonPair = map.get(propertyName);
    if (jsonPair == null) {
      return null;
    }
    return jsonPair;
  }

  /**
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.JSONPair}. If the property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.JSONPair}
   */
  public JSONPair getNullElement(int index) {
    if (index < 0 || index > list.size()) {
      return null;
    }
    JSONPair jsonPair = list.get(index);
    return jsonPair;
  }

  /**
   * Return the value of the property with the name passed converted to {@code Float}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Float}
   */
  public Float getNullFloat(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Float}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Float}
   */
  public Float getNullFloat(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toFloat();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Integer}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Integer}
   */
  public Integer getNullInteger(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Integer}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Integer}
   */
  public Integer getNullInteger(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toInteger();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.values.JSONArray}. If the property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONArray}
   */
  public JSONArray getNullJSONArray(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.values.JSONArray}. If the property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONArray}
   */
  public JSONArray getNullJSONArray(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toJSONArray();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Long}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Long}
   */
  public Long getNullLong(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Long}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Long}
   */
  public Long getNullLong(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toLong();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.values.JSONObject}. If the property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONObject}
   */
  public JSONObject getNullObject(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.values.JSONObject}. If the property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONObject}
   */
  public JSONObject getNullObject(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toObject();
  }

  /**
   * Return the value of the property with the name passed converted to {@code Short}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Short}
   */
  public Short getNullShort(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Short}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code Short}
   */
  public Short getNullShort(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toShort();
  }

  /**
   * Return the value of the property with the name passed converted to {@code String}. If the
   * property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code String}
   */
  public String getNullString(String propertyName) {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   * Return the value of the property with the index passed converted to {@code String}. If the
   * property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@code String}
   */
  public String getNullString(int index) {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      return null;
    }
    return jsonValue.toString();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.values.JSONValue}. If the property doesn't exist return {@code null}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONValue}
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
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.values.JSONValue}. If the property doesn't exist return {@code null}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONValue}
   */
  public JSONValue getNullValue(int index) {
    if (index < 0 || index > list.size()) {
      return null;
    }
    JSONPair jsonPair = list.get(index);
    return jsonPair.getValue();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.values.JSONObject}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONObject}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public JSONObject getObject(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toObject();
  }

  /**
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.values.JSONObject}. If the property doesn't exist throw a
   * {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONObject}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public JSONObject getObject(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toObject();
  }

  /**
   * Convert this object in a referenced {@link net.cabezudo.json.JSONElement}. Because the
   * referenced element is the reference, this method return the value of the reference field.
   *
   * @return a {@link net.cabezudo.json.values.JSONValue} with the referenced element for this.
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
   * Return the value of the property with the name passed converted to {@code Short}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code Short}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public Short getShort(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toShort();
  }

  /**
   * Return the value of the property with the index passed converted to {@code Short}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code Short}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public Short getShort(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toShort();
  }

  /**
   * Return the value of the property with the name passed converted to {@code String}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@code String}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public String getString(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(propertyName);
    return jsonValue.toString();
  }

  /**
   * Return the value of the property with the index passed converted to {@code String}. If the
   * property doesn't exist throw a {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@code String}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public String getString(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getValue(index);
    return jsonValue.toString();
  }

  /**
   * Return the value of the property with the name passed converted to
   * {@link net.cabezudo.json.values.JSONValue}.
   *
   * @param propertyName the name of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONValue}
   * @throws PropertyNotExistException if the property doesn't exist.
   */
  public JSONValue getValue(String propertyName) throws PropertyNotExistException {
    JSONValue jsonValue = getNullValue(propertyName);
    if (jsonValue == null) {
      throw new PropertyNotExistException("The property " + propertyName + " doesn't exist.", getPosition());
    }
    return jsonValue;
  }

  /**
   * Return the value of the property with the index passed converted to
   * {@link net.cabezudo.json.values.JSONValue}. If the property doesn't exist throw a
   * {@link net.cabezudo.json.exceptions.PropertyNotExistException}.
   *
   * @param index the index of the property to return.
   * @return a {@link net.cabezudo.json.values.JSONValue}
   * @throws PropertyNotExistException if the index is out of range.
   */
  public JSONValue getValue(int index) throws PropertyNotExistException {
    JSONValue jsonValue = getNullValue(index);
    if (jsonValue == null) {
      throw new PropertyNotExistException("The position " + index + " don't have a value.", getPosition());
    }
    return jsonValue;
  }

  /**
   * Tells whether or not this object has childs
   *
   * @return {@code true} if, and only if, this object has childs, {@code false} otherwise.
   */
  public boolean hasChilds() {
    return !list.isEmpty();
  }

  /**
   * Tells whether or not this object is a {@code JSONObject}.
   *
   * @return {@code true}.
   */
  @Override
  public boolean isObject() {
    return true;
  }

  /**
   * Tells whether or not this object is referenciable.
   *
   * @return {@code true}.
   */
  @Override
  public boolean isReferenciable() {
    return true;
  }

  /**
   * Returns an iterator over the properties in this object in proper sequence.
   *
   * @return an iterator over the properties in this object in proper sequence
   */
  @Override
  public Iterator<JSONPair> iterator() {
    return list.iterator();
  }

  /**
   * Returns the number of propertis in this object. If this objec contains more than
   * <tt>Integer.MAX_VALUE</tt> elements, returns <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of elements in this object.
   */
  public int size() {
    return list.size();
  }

  /**
   * Convert the properties values of this object in an array of elements of type
   * {@link net.cabezudo.json.values.JSONValue} using the values of element and leaving out the
   * properties names.
   *
   * @return an array of type {@link net.cabezudo.json.values.JSONValue} with the values of this
   * object properties.
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
   * Create a JSON string representation of this {@code JSONObject} including the JSON string
   * representation of the properties.
   *
   * @return a {@code String} representation of this {@code JSONObject}.
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
   * Convert the properties values of this object in a {@link net.cabezudo.json.values.JSONArray}
   * object using the values and leaving out the properties names.
   *
   * @return an array of type {@link net.cabezudo.json.values.JSONArray} with the values of this
   * object properties.
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
   * Return the JSON structure behind this object that is the reference to this object.
   *
   * @return this object
   */
  @Override
  public JSONValue toJSONTree() {
    return this;
  }

  /**
   * Return this object.
   *
   * @return this object
   */
  @Override
  public JSONObject toObject() {
    return this;
  }

  /**
   * Return a {@code String} with a representation of this object.
   *
   * @return a {@code String} with a representation of this object.
   */
  @Override
  public String toString() {
    return toJSON();
  }
}
