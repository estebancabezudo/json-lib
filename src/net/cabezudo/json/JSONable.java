package net.cabezudo.json;

import net.cabezudo.json.values.JSONValue;

/**
 * Enables the capability to convert an object into a JSON string representation and into a JSON java object.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/23/2014
 */
public interface JSONable {

  /**
   *
   * @return a <code>String</code> with a JSON string representation of the object.
   */
  String toJSON();

  /**
   *
   * @return a JSON structure representation of the object.
   */
  JSONValue toJSONTree();
}
