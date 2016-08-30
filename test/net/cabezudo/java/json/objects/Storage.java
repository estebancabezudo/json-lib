package net.cabezudo.java.json.objects;

import net.cabezudo.java.json.annotations.JSONProperty;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/08/2016
 */
public class Storage {

  @JSONProperty
  private final int id;

  @JSONProperty
  private final BookList list;

  @JSONProperty
  private final Book mostImportantBook;

  public Storage(int id, BookList list, Book mostImportantBook) {
    this.id = id;
    this.list = list;
    this.mostImportantBook = mostImportantBook;
  }

  public int getId() {
    return id;
  }

  public BookList getList() {
    return list;
  }

  public Book getMostImportantBook() {
    return mostImportantBook;
  }
}
