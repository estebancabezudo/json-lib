package net.cabezudo.java.json;

import net.cabezudo.java.json.values.JSONValue;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/23/2014
 */
public interface JSONable {

  /**
   *
   * @return
   */
  String toJSON();

  /**
   *
   * @return
   */
  JSONValue toJSONTree();
}
