package net.cabezudo.java.json.objects;

import net.cabezudo.java.json.annotations.JSONProperty;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 08/02/2016
 */
public class Version {

  @JSONProperty
  private final int id = 1;


  @JSONProperty
  private final int minor = 0;
  @JSONProperty
  private final int version = 1;

  public int getId() {
    return id;
  }

  public int getMinor() {
    return minor;
  }

  public int getVersion() {
    return version;
  }
}
