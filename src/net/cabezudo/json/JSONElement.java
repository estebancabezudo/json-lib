package net.cabezudo.json;

/**
 * The <code>JSONElement</code> class is an abstract class for implement all the posible elements in a JSON structure.
 *
 * <p>
 * The class also provides additional default methods for implementing a concrete element and the default info to provide.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/03/2014
 */
public abstract class JSONElement implements JSONable {

  private String referenceFieldName;
  private final Position position;

  public Position getPosition() {
    return position;
  }

  public JSONElement(Position position) {
    this.position = position;
  }

  /**
   * Returns the name of the field whose value is used as a reference.
   *
   * @return a <code>String</code> with the name of the field whose value is used as a reference.
   */
  public String getReferenceFieldName() {
    return referenceFieldName;
  }

  /**
   * Defines the name of the field used to reference the object.
   *
   * @param referenceFieldName a <code>String</code> with the name of the field to be used to refer to the object.
   */
  public void setReferenceFieldName(String referenceFieldName) {
    this.referenceFieldName = referenceFieldName;
  }

  /**
   * This forces the implementation of a method method that returns the result of replace the objects that can be referenced by their
   * references.
   *
   * @return The element whose referable objects have been replaced with their references.
   */
  public abstract JSONElement getReferencedElement();

  /**
   * Returns whether the element has elements or not.
   *
   * @return <code>true</code> if the element has elements, <code>false</code> otherwise.
   */
  public Boolean hasElements() {
    return false;
  }

  /**
   * Returns whether the element is an array or not.
   *
   * @return <code>true</code> if the element is an array, <code>false</code> otherwise.
   */
  public boolean isArray() {
    return false;
  }

  /**
   * Returns whether the element is a boolean or not.
   *
   * @return <code>true</code> if the element is a boolean, <code>false</code> otherwise.
   */
  public boolean isBoolean() {
    return false;
  }

  /**
   * Returns whether the element is referenciable or not.
   *
   * @return <code>true</code> if the element is not referenciable, <code>false</code> otherwise.
   */
  public boolean isNotReferenciable() {
    return !isReferenciable();
  }

  /**
   * Returns whether the element is the null JSON object.
   *
   * @return <code>true</code> if the element is a null JSON object, <code>false</code> otherwise.
   */
  public boolean isNull() {
    return false;
  }

  /**
   * Returns whether the element is a JSON number object or not.
   *
   * @return <code>true</code> if the element is a JSON null object, <code>false</code> otherwise.
   */
  public boolean isNumber() {
    return false;
  }

  /**
   * Returns whether the element is a JSON object or not.
   *
   * @return <code>true</code> if the element is a JSON object, <code>false</code> otherwise.
   */
  public boolean isObject() {
    return false;
  }

  /**
   * Returns whether the element is referenciable or not.
   *
   * @return <code>true</code> if the element is referenciable, <code>false</code> otherwise.
   */
  public boolean isReferenciable() {
    return false;
  }

  /**
   * Returns whether the element is a JSON string or not.
   *
   * @return <code>true</code> if the element is a JSON string, <code>false</code> otherwise.
   */
  public boolean isString() {
    return false;
  }

  /**
   * Returns whether the object is a JSON value object.
   *
   * @return <code>true</code> if the object is a JSON value, <code>false</code> otherwise.
   */
  public boolean isValue() {
    return false;
  }

  /**
   * Returns a string representation in JSON format of the object.
   *
   * @return a <code>String</code> representation of the JSON object in JSON format.
   */
  @Override
  public String toString() {
    return toJSON();
  }
}
