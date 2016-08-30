package net.cabezudo.java.json.objects;

import net.cabezudo.java.json.annotations.JSONProperty;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 08/02/2016
 */
public class Data {

  @JSONProperty(name = "version")
  private final Version VERSION = new Version();

  @JSONProperty
  private final int countryId = 1;

  @JSONProperty
  private final CountryName countryName = new CountryName();


  public int getCountryId() {
    return countryId;
  }

  public CountryName getCountryName() {
    return countryName;
  }
  public Version getVersion() {
    return VERSION;
  }
}
