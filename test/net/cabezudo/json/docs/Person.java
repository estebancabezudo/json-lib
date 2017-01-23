package net.cabezudo.json.docs;

import java.util.ArrayList;
import java.util.List;
import net.cabezudo.json.annotations.JSONProperty;

/**
 * @autor Esteban Cabezudo
 * @version 1.00.00
 * @date 01/22/2017
 */
public class Person {

  @JSONProperty
  private final String name;

  @JSONProperty
  private final String lastName;

  @JSONProperty
  private final int age;

  @JSONProperty(field = "name")
  private final List<Person> childs = new ArrayList<>();

  public Person(String name, String lastName, int age, Person... persons) {
    this.name = name;
    this.lastName = lastName;
    this.age = age;
    for (Person child : persons) {
      childs.add(child);
    }
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public List<Person> getChilds() {
    return childs;
  }

  public int getAge() {
    return age;
  }
}
