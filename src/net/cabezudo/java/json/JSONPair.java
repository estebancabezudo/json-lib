package net.cabezudo.java.json;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import net.cabezudo.java.json.values.JSONArray;
import net.cabezudo.java.json.values.JSONNull;
import net.cabezudo.java.json.values.JSONObject;
import net.cabezudo.java.json.values.JSONString;
import net.cabezudo.java.json.values.JSONValue;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @date 10/01/2014
 * @version 1.0
 * @since 1.7
 */
public class JSONPair extends JSONElement implements Comparable<JSONPair> {

  private final String key;
  private final JSONValue value;

  /**
   *
   * @param key
   * @param object
   */
  public JSONPair(String key, Object object) {
    if (key == null) {
      throw new IllegalArgumentException("The parameter key is null.");
    }
    this.key = key;
    JSONValue jsonValue = JSON.toJSONTree(object);
    this.value = jsonValue;
  }

  @Override
  public int compareTo(JSONPair jsonPair) {
    int c = this.getKey().compareTo(jsonPair.getKey());
    if (c == 0) {
      c = this.getValue().compareTo(jsonPair.getValue());
    }
    return c;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    final JSONPair other = (JSONPair) o;

    if (!Objects.equals(this.getKey(), other.getKey())) {
      return false;
    }
    return Objects.equals(this.getValue(), other.getValue());
  }

  /**
   *
   * @return
   */
  public String getKey() {
    return key;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONPair getReferencedElement() {
    JSONElement jsonReferencedElement = value.getReferencedElement();
    Log.debug("The referenced value for %s is %s.%n", value, jsonReferencedElement);
    JSONPair jsonPair = new JSONPair(key, jsonReferencedElement);
    return jsonPair;
  }

  /**
   *
   * @return
   */
  public JSONValue getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.getKey());
    hash = 97 * hash + Objects.hashCode(this.getValue());
    return hash;
  }

  /**
   *
   * @return
   */
  public BigDecimal toBigDecimal() {
    return value.toBigDecimal();
  }

  /**
   *
   * @return
   */
  public Boolean toBoolean() {
    return value.toBoolean();
  }

  /**
   *
   * @return
   */
  public Byte toByte() {
    return value.toByte();
  }

  /**
   *
   * @return
   */
  public Character toCharacter() {
    return value.toCharacter();
  }

  /**
   *
   * @return
   */
  public Double toDouble() {
    return value.toDouble();
  }

  /**
   *
   * @return
   */
  public Float toFloat() {
    return value.toFloat();
  }

  /**
   *
   * @return
   */
  public Integer toInteger() {
    return value.toInteger();
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    JSONValue jsonValue;
    if (value == null) {
      jsonValue = JSONNull.getValue();
    } else {
      jsonValue = value;
    }
    return "\"" + key + "\": " + jsonValue.toJSON();
  }

  /**
   *
   * @return
   */
  public JSONArray toJSONArray() {
    return value.toJSONArray();
  }

  /**
   *
   * @return
   */
  public JSONString toJSONString() {
    return value.toJSONString();
  }

  /**
   *
   * @return
   */
  @Override
  public JSONValue toJSONTree() {
    return value.toJSONTree();
  }

  /**
   *
   * @return
   */
  public List<JSONValue> toList() {
    return value.toList();
  }

  /**
   *
   * @return
   */
  public Long toLong() {
    return value.toLong();
  }

  /**
   *
   * @return
   */
  public JSONObject toObject() {
    return value.toObject();
  }

  /**
   *
   * @return
   */
  public Short toShort() {
    return value.toShort();
  }

  @Override
  public String toString() {
    return "(" + key + ", " + value + ")";
  }

  /**
   *
   * @return
   */
  public String[] toStringArray() {
    return value.toStringArray();
  }
}
