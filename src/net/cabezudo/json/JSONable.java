package net.cabezudo.json;

import net.cabezudo.json.values.JSONValue;

/**
 * The user of this interface can convert the objet in a JSON string representation of the object and to a JSON structure representation of
 * the object.
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
