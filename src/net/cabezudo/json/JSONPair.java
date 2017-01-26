package net.cabezudo.json;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import net.cabezudo.json.values.JSONArray;
import net.cabezudo.json.values.JSONNull;
import net.cabezudo.json.values.JSONObject;
import net.cabezudo.json.values.JSONString;
import net.cabezudo.json.values.JSONValue;

/**
 * A JSON object is build with only one element. A pair. This class represent the element that
 * contain a JSON object. A JSON pair has a key and a value. The key is the property name for an
 * element in a JSON object and the value can be any type of value. A value only can be an object
 * inherited of JSONValue.
 *
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
   * @param key the name for the key pair part
   * @param object the object to use like value. The constructor convert, if possible, the object to
   * a JSONValue. using {@link JSON#toJSONTree(java.lang.Object) toJSONTree}
   * method.
   * @param position the position of the pair in the JSON source code. Using for locate the new
   * created pair in the source file. If is not used can be null but is better use the constructor
   * without the parameter,
   */
  public JSONPair(String key, Object object, Position position) {
    super(position);
    if (key == null) {
      throw new IllegalArgumentException("The parameter key is null.");
    }
    this.key = key;
    JSONValue jsonValue = JSON.toJSONTree(object);
    this.value = jsonValue;
  }

  /**
   *
   * @param key the name for the key pair part
   * @param object the object to use like value. The constructor convert, if possible, the object to
   * a JSONValue. using {@link JSON#toJSONTree(java.lang.Object) toJSONTree}
   * method.
   */
  public JSONPair(String key, Object object) {
    this(key, object, null);
  }

  /**
   * Compare two JSONPair objects. Two JSONPair are the same if the key is the same and the value is
   * the same. For the comparison the key is compared first and if is the same is used to return the
   * result. If the key in both object is the same the value is used to return the result of the
   * comparison.
   *
   *
   * @param jsonPair the {@code JSONPair} to be compared.
   * @return the value {@code 0} if the argument string is equal to this string; a value less than
   * {@code 0} if this {code JSONPair} is less than the string argument; and a value greater than
   * {@code 0} if this {code JSONPair} is greater than the {@code JSONPair} argument.
   */
  @Override
  public int compareTo(JSONPair jsonPair) {
    int c = this.getKey().compareTo(jsonPair.getKey());
    if (c == 0) {
      c = this.getValue().compareTo(jsonPair.getValue());
    }
    return c;
  }

  /**
   * Compares this {code JSONPair] to the object passed in the parameter. The result is {@code
   * true} if and only if the argument is not {@code null} and is a {@code
   * JSONPair} object has equals key and value.
   *
   * @param o The object to compare this {@code JSONPair} against
   *
   * @return {@code true} if the given object represents a {@code JSONPair} has an equal key and an
   * equal value, {@code false} otherwise.
   *
   */
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
   * @return the key part of the {@code JSONPair}
   */
  public String getKey() {
    return key;
  }

  /**
   * Create a new {@code JSONPair} and replace the object for the value of the reference field. The
   * key is the same and the value, if is a object, and have a reference field, for the value of the
   * reference field of the object.
   *
   * @return the referenced JSONPair for this {@code JSONPair}
   */
  @Override
  public JSONPair toReferencedElement() {
    JSONElement jsonReferencedElement = value.toReferencedElement();
    JSONPair jsonPair = new JSONPair(key, jsonReferencedElement, getPosition());
    return jsonPair;
  }

  /**
   * Return a {@link JSONValue} object, value part of this {@code JSONPair}
   *
   * @return the value part of this {@code JSONPair}
   */
  public JSONValue getValue() {
    return value;
  }

  /**
   * Returns a hash code for this {code JSONPair}. The hash code is computed using the hash code of
   * the key and the hash code for de value.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.getKey());
    hash = 97 * hash + Objects.hashCode(this.getValue());
    return hash;
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code BigDecimal} and return it. If the value
   * can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code BigDecimal} with the conversion of value.
   */
  public BigDecimal toBigDecimal() {
    return value.toBigDecimal();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code Boolean} and return it. If the value
   * can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code Boolean} with the conversion of value.
   */
  public Boolean toBoolean() {
    return value.toBoolean();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code Byte} and return it. If the value can't
   * be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code Byte} with the conversion of value.
   */
  public Byte toByte() {
    return value.toByte();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code Character} and return it. If the value
   * can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code Character} with the conversion of value.
   */
  public Character toCharacter() {
    return value.toCharacter();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code Double} and return it. If the value
   * can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code Double} with the conversion of value.
   */
  public Double toDouble() {
    return value.toDouble();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code Float} and return it. If the value can't
   * be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code Float} with the conversion of value.
   */
  public Float toFloat() {
    return value.toFloat();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@code Integer} and return it. If the value
   * can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code Integer} with the conversion of value.
   */
  public Integer toInteger() {
    return value.toInteger();
  }

  /**
   * Create a JSON string representation of this {@code JSONPair} including the JSON string
   * representation of the value. The JSON string is the key in double quotation marks, a colon and
   * the JSON string value using the {@link JSONValue#toJSON() }
   * method. If the value is {@code null} the method return {@link JSONNull
   * }.
   *
   * @return a {@code String} representation of this {@code JSONPair}.
   */
  @Override
  public String toJSON() {
    JSONValue jsonValue;
    if (value == null) {
      jsonValue = new JSONNull();
    } else {
      jsonValue = value;
    }
    return "\"" + key + "\": " + jsonValue.toJSON();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@link JSONArray} and
   * return it. If the value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@link JSONArray} with the conversion of value.
   */
  public JSONArray toJSONArray() {
    return value.toJSONArray();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@link JSONString }
   * and return it. If the value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@link JSONString } with the conversion of value.
   */
  public JSONString toJSONString() {
    return value.toJSONString();
  }

  /**
   * Return this {@code JSONPair}.
   *
   * @return a {@code JSONPair} with this object.
   */
  @Override
  public JSONValue toJSONTree() {
    return value.toJSONTree();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@link java.util.List } and return it. If the
   * value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@link java.util.List } with the conversion of value.
   */
  public List<JSONValue> toList() {
    return value.toList();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@link java.lang.Long } and return it. If the
   * value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@link java.lang.Long } with the conversion of value.
   */
  public Long toLong() {
    return value.toLong();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@link JSONObject }
   * and return it. If the value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@link JSONObject } with the conversion of value.
   */
  public JSONObject toObject() {
    return value.toObject();
  }

  /**
   * Convert the value of this {@code JSONPair} to a {@link java.lang.Short } and return it. If the
   * value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@link java.lang.Short } with the conversion of value.
   */
  public Short toShort() {
    return value.toShort();
  }

  /**
   * Return a string representation of this {@code JSONPair}. The string is formed using a open
   * parenthesis, the key, a comma, the string representation of the value and a close parenthesis.
   *
   * @return a {@code String} representation of this {@code JSONPair}
   */
  @Override
  public String toString() {
    return "(" + key + ", " + value + ")";
  }

  /**
   * Convert the value of this {@code JSONPair} to an native array of strings and return it. If the
   * value can't be converter the method throw a runtime exception
   * {@link JSONCastException}. The rules for conversión depends of the
   * type of value.
   *
   * @return a {@code String[]} with the conversion of value.
   */
  public String[] toStringArray() {
    return value.toStringArray();
  }
}
