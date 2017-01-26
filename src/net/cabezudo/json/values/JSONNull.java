package net.cabezudo.json.values;

import java.util.ArrayList;
import java.util.List;
import net.cabezudo.json.Position;

/**
 * A {@link JSONNull} is an object extended from {@link JSONValue} object in order to represent a
 * null that can be used to create JSON structures.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONNull extends JSONValue<JSONNull> {

  private final String nullString = "null";

  /**
   * Construct a {@code JSONNull}. The method also take a {@link Position} in order to save the
   * position in origin and give it when a parse error is throw.
   *
   * @param position the position of the value in the source.
   */
  public JSONNull(Position position) {
    super(position);
  }

  /**
   * Construct a {@code JSONNull}.
   */
  public JSONNull() {
    this(null);
  }

  /**
   * Compares two {@code JSONNull} objects.
   *
   * @param o the {@code Object} to be compared.
   * @return {@code true} if {@code this} {@code JSONNull} is equal to the argument {@code false}
   * otherwise.
   */
  @Override
  public boolean equals(Object o) {
    return o instanceof JSONNull;
  }

  /**
   * Returns the hash code for this {@code JSONNull}.
   *
   * @return hash code for this {@code JSONNull}.
   */
  @Override
  public int hashCode() {
    return this.nullString.hashCode();
  }

  /**
   * Compares two {@code JSONNull} objects.
   *
   * @param jsonNull the {@code JSONNull} to be compared.
   *
   * @return the value {@code 0}.
   */
  @Override
  public int compareTo(JSONNull jsonNull) {
    return 0;
  }

  /**
   * Return the referenced element for {@code this} object. For a {@code JSONNull} object,
   * {@code this} object and the referenced version is the same.
   *
   * @return {@code this} object.
   */
  @Override
  public JSONNull toReferencedElement() {
    return this;
  }

  /**
   * Returns whether the element is a {@code JSONNull} or not.
   *
   * @return {@code true} if the element is a {@code JSONNull}; {@code false} otherwise.
   */
  @Override
  public boolean isNull() {
    return true;
  }

  /**
   * Convert {@code this} object to a string with the representation of the JSON structure in a JSON
   * string form.
   *
   * @return a {@code String} with the JSON string representation of {@code this} object.
   */
  @Override
  public String toJSON() {
    return nullString;
  }

  /**
   * Convert {@code this} object to a {@link JSONArray} object. The result {@link JSONArray} only
   * has {@code this} element.
   *
   * @return a {@link JSONArray} object.
   */
  @Override
  public JSONArray toJSONArray() {
    JSONArray jsonArray = new JSONArray();
    jsonArray.add(this);
    return jsonArray;
  }

  /**
   * Convert {@code this} object to a {@link JSONString} object.
   *
   * @return a {@link JSONString}.
   */
  @Override
  public JSONString toJSONString() {
    return new JSONString(nullString, getPosition());
  }

  /**
   * Convert {@code this} object to a {@code List} of {@link JSONValue} objects.
   *
   * @return a {@code List} of {@link JSONValue} with only {@code this} element.
   */
  @Override
  public List<JSONValue> toList() {
    List<JSONValue> list = new ArrayList<>();
    list.add(this);
    return list;
  }

  /**
   * Convert {@code this} object to an array of {@code String} objects.
   *
   * @return an array of {@code String} with only one element.
   */
  @Override
  public String[] toStringArray() {
    String[] s = new String[1];
    s[0] = nullString;
    return s;
  }

}
