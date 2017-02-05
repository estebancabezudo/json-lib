package net.cabezudo.json.objects;

import net.cabezudo.json.annotations.JSONProperty;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.9, 08/02/2016
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
