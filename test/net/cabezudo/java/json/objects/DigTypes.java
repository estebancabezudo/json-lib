package net.cabezudo.java.json.objects;

import net.cabezudo.java.json.annotations.JSONProperty;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/05/2016
 */
public class DigTypes {

  @JSONProperty
  private final Types types = new Types();

  public Types getTypes() {
    return types;
  }
}
