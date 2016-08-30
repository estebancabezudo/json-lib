package net.cabezudo.java.json.objects;

import java.util.Objects;
import net.cabezudo.java.json.JSON;
import net.cabezudo.java.json.JSONable;
import net.cabezudo.java.json.annotations.JSONProperty;
import net.cabezudo.java.json.values.JSONObject;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/08/2016
 */
public class Book implements JSONable {

  @JSONProperty
  private final int id;

  @JSONProperty
  private final String name;

  public Book(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    final Book book = (Book) o;
    if (this.id != book.id) {
      return false;
    }
    return Objects.equals(this.name, book.name);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + this.id;
    hash = 47 * hash + Objects.hashCode(this.name);
    return hash;
  }

  @Override
  public String toJSON() {
    return toJSONTree().toString();
  }

  @Override
  public JSONObject toJSONTree() {
    return JSON.toJSONObject(this);
  }
}
