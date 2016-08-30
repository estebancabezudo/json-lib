package net.cabezudo.java.json.values;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
public class JSONNull extends JSONValue<JSONNull> {

  private static JSONNull INSTANCE;

  private final String nullString = "null";
  /**
   *
   * @return
   */
  public static synchronized JSONNull getValue() {
    if (INSTANCE == null) {
      INSTANCE = new JSONNull();
    }
    return INSTANCE;
  }

  private JSONNull() {
  }
  @Override
  public int compareTo(JSONNull jsonNull) {
    return 0;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONNull getReferencedElement() {
    return this;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isNull() {
    return true;
  }

  /**
   *
   * @return
   */
  @Override
  public String toJSON() {
    return nullString;
  }
  /**
   *
   * @return
   */
  @Override
  public JSONArray toJSONArray() {
    JSONArray jsonArray = new JSONArray();
    jsonArray.add(nullString);
    return jsonArray;
  }

  /**
   *
   * @return
   */
  @Override
  public JSONString toJSONString() {
    return new JSONString(nullString);
  }


  /**
   *
   * @return
   */
  @Override
  public List<JSONValue> toList() {
    List<JSONValue> list = new ArrayList<>();
    list.add(this);
    return list;
  }

  /**
   *
   * @return
   */
  @Override
  public String[] toStringArray() {
    String[] s = new String[1];
    s[0] = nullString;
    return s;
  }

}
