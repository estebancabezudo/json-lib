package net.cabezudo.java.json.objects;

import net.cabezudo.java.json.annotations.JSONProperty;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 08/02/2016
 */
public class CountryName {

  @JSONProperty(name = "version")
  private final Version VERSION = new Version();

  @JSONProperty
  private final Language language = new Language();


  @JSONProperty()
  private final NameType nameType = NameType.LONG;

  @JSONProperty
  private final Word word = new Word();

  public Language getLanguage() {
    return language;
  }


  public NameType getNameType() {
    return nameType;
  }
  public Version getVersion() {
    return VERSION;
  }
  public Word getWord() {
    return word;
  }
}
