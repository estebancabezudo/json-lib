package net.cabezudo.java.json.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.cabezudo.java.json.annotations.JSONProperty;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/08/2016
 */
public class BookList implements Iterable<Book> {

  @JSONProperty()
  List<Book> list = new ArrayList<>();

  public void add(Book book) {
    list.add(book);
  }

  public List<Book> getList() {
    return Collections.unmodifiableList(list);
  }

  @Override
  public Iterator<Book> iterator() {
    return list.iterator();
  }
}
