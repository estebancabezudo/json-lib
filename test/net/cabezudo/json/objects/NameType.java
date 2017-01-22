package net.cabezudo.json.objects;

import net.cabezudo.json.annotations.JSONProperty;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 08/02/2016
 */
public enum NameType {
  SHORT, LONG;

  @JSONProperty(field = "index")
  private final int index;

  private NameType() {
    index = this.ordinal();
  }

  public int getIndex() {
    return index;
  }
}
