package net.cabezudo.java.json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/03/2014
 */
public abstract class JSONElement implements JSONable {

  private String referenceFieldName;

  /**
   *
   * @return
   */
  public JSONElement getElement() {
    return this;
  }

  /**
   *
   * @return
   */
  public List<JSONElement> getElements() {
    List<JSONElement> list = new ArrayList<>();
    return list;
  }


  /**
   *
   * @return
   */
  public String getReferenceFieldName() {
    return referenceFieldName;
  }

  /**
   *
   * @param referenceFieldName
   */
  public void setReferenceFieldName(String referenceFieldName) {
    this.referenceFieldName = referenceFieldName;
  }
  /**
   *
   * @return
   */
  public abstract JSONElement getReferencedElement();

  /**
   *
   * @return
   */
  public Boolean hasElements() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isArray() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isBoolean() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isNotReferenciable() {
    return !isReferenciable();
  }

  /**
   *
   * @return
   */
  public boolean isNull() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isNumber() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isObject() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isReferenciable() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isString() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isValue() {
    return false;
  }

  @Override
  public String toString() {
    return toJSON();
  }
}
