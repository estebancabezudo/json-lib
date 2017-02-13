/**
 * MIT License
 *
 * Copyright (c) 2017 Esteban Cabezudo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.cabezudo.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.cabezudo.json.annotations.JSONProperty;
import net.cabezudo.json.exceptions.EmptyQueueException;
import net.cabezudo.json.exceptions.JSONParseException;
import net.cabezudo.json.exceptions.NotPropertiesException;
import net.cabezudo.json.exceptions.ObjectException;
import net.cabezudo.json.exceptions.PropertyNotExistException;
import net.cabezudo.json.exceptions.UnexpectedElementException;
import net.cabezudo.json.values.JSONArray;
import net.cabezudo.json.values.JSONNull;
import net.cabezudo.json.values.JSONObject;
import net.cabezudo.json.values.JSONValue;

/**
 * Provides the methods to parse and create JSON objects.
 * <p>
 * JSON class allow you to parse a string with JSON format in to a JSON tree object to be
 * manipulated. The input may be obtained from a file on the file system. The JSON class allow you
 * to create a JSON tree from any object and create a tree using the references to the object
 * <h1>Parse</h1>
 * <p>
 * The JSON tree obtained includes many methods to manipulate its members, such as add elements,
 * delete elements and set new values.
 * <h1>JSON tree</h1>
 * The JSON tree is a tree formed by objects that extends from
 * {@link net.cabezudo.json.values.JSONValue}, representing the JSON elements.
 * <p>
 * There are six different types of elements in a JSON structure: {@link net.cabezudo.json.values.JSONArray},
 * {@link net.cabezudo.json.values.JSONBoolean}, {@link net.cabezudo.json.values.JSONNull},
 * {@link net.cabezudo.json.values.JSONNumber}, {@link net.cabezudo.json.values.JSONObject}, and
 * {@link net.cabezudo.json.values.JSONString}. You can get the elements from the JSON structure
 * navigating deep into the structure in order to reach the elements one by one.
 * <h1>JSON referenced tree</h1>
 * <p>
 * OOLV: Please explain like I'm a five years old kid
 *
 * A referenced JSON structure is a normal JSON structure transformed into a small structure. To do
 * this we replace the value of a JSON object for their reference. The reference is the value of the
 * reference field, an arbitrary field you can choose. The default field name used is {@code id}. A
 * common example of a reference is the foreign key in a relational table.
 * <p>
 * The easy way to get a referenced tree is using annotated objects in order to create a JSON
 * structure. Is the easy way because if you get a JSON structure with a JSON string there isn't
 * information about the referenced field If you create a tree from a Java normal object or creating
 * the JSON structure using the elements object one by one, you can specify the field that going to
 * be used to create the referenced tree with the method
 * {@link net.cabezudo.json.JSONElement#setReferenceFieldName(java.lang.String)}. The JSON elements
 * has reference information.
 *
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 0.9, 10/01/2014
 */
public class JSON {

  public static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

  public static void main(String... args) throws PropertyNotExistException {
  }

  private static Object getFieldValue(Object object, String getterName) {
    Method method;
    Class<?> objectClass = object.getClass();
    try {
      method = objectClass.getMethod(getterName);
    } catch (NoSuchMethodException e) {
      throw new ObjectException("I can't find the getter '" + getterName + "' in the object " + objectClass.getName(), e);
    }
    try {
      return method.invoke(object);
    } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
      throw new ObjectException("Getting the field value using " + getterName + " in the object " + objectClass.getName() + ".", e);
    }
  }

  private static String getGetterName(Field field, String fieldName) {
    Class<?> fieldType = field.getType();
    String getterPrefix;

    if (fieldType == boolean.class
            || fieldType == Boolean.class) {
      getterPrefix = "is";
    } else {
      getterPrefix = "get";
    }
    return getterPrefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  private static List<Field> getInheritedFields(Class<?> clazz) {
    List<Field> fields = new ArrayList<>();
    for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
      fields.addAll(Arrays.asList(c.getDeclaredFields()));
    }
    return fields;
  }

  /**
   * Parse a string and create a JSON structure of objects representation of JSON elements.
   *
   * @param string the string used to create the JSON structure.
   * @return the JSON structure.
   * @throws JSONParseException if the {@code String} does not contain a parseable JSON string. The
   * exception contains the information of the position where the parse error raise.
   */
  public static JSONValue parse(String string) throws JSONParseException {
    Tokens tokens = Tokenizer.tokenize(string);
    JSONValue jsonElement;

    Token token;
    try {
      token = tokens.poll();
    } catch (EmptyQueueException e) {
      throw new JSONParseException("Unexpected end parsing " + string + ".", e, Position.INITIAL);
    }

    TokenType tokenType = token.getType();
    JSONFactory jsonFactory = new JSONFactory();

    switch (tokenType) {
      case LEFT_BRACE:
        jsonElement = jsonFactory.getJSONObject(tokens, token.getPosition());
        break;
      case LEFT_BRACKET:
        jsonElement = jsonFactory.getJSONArray(tokens, token.getPosition());
        break;
      default:
        throw new UnexpectedElementException(token.getValue(), token.getPosition());
    }
    return jsonElement;
  }

  /**
   * This method take the information from a file and parses it to create a JSON structure of
   * objects representation of JSON elements.
   *
   * @param filePath the {@link java.nio.file.Path} where is the file
   * @param charsetName The name of a supported {@code Charset}
   * @return A JSON structure of objects JSONValue and JSONPair representation of the data in the
   * string.
   * @throws JSONParseException if the {@code String} does not contain a parseable JSON string. The
   * exception contains the information of the position where the parse error raise.
   * @throws java.io.UnsupportedEncodingException if the Character Encoding is not supported.
   * @throws IOException if an I/O error occurs opening the file.
   */
  public static JSONValue parse(Path filePath, String charsetName) throws JSONParseException, UnsupportedEncodingException, IOException {
    byte[] data;
    data = Files.readAllBytes(filePath);
    String jsonString;
    jsonString = new String(data, charsetName);
    return parse(jsonString);
  }

  /**
   * Convert a list of objects in a {@link JSONArray} The objects in the list must have the
   * properties annotated with {@link JSONProperty} in order to be used as object property.
   *
   * @param list the list of objects to be converted.
   * @return a {@link JSONArray} with a list of JSON elements.
   */
  public static JSONArray toJSONArray(List<?> list) {
    JSONArray jsonArray = new JSONArray();

    for (Object object : list) {
      jsonArray.add(object);
    }
    return jsonArray;
  }

  /**
   * Convert a POJO in a {@link net.cabezudo.json.values.JSONObject}. The object must have the
   * properties annotated with {@link net.cabezudo.json.annotations.JSONProperty} in order to be
   * used as a object property.
   *
   * @param object the object to be converted.
   * @return a {@link net.cabezudo.json.values.JSONObject} created using the object passed.
   */
  public static JSONObject toJSONObject(Object object) {
    return toJSONTree(object).toObject();
  }

  /**
   * Convert a POJO into a {@link net.cabezudo.json.values.JSONValue}. The object must have the
   * properties annotated with {@link net.cabezudo.json.annotations.JSONProperty} in order to be
   * included in the conversion. If the object is {@code Iterable} or the object is a primitive
   * array the result is a {@link net.cabezudo.json.values.JSONArray}.
   *
   * @param object the object to be converted.
   * @return a {@link net.cabezudo.json.values.JSONValue} created using the object passed.
   */
  public static JSONValue toJSONTree(Object object) {
    if (object == null) {
      return new JSONNull();
    }
    if (object instanceof JSONValue) {
      return (JSONValue) object;
    }

    JSONValue jsonValue;

    if (Iterable.class.isAssignableFrom(object.getClass())) {
      Iterable iterable = (Iterable) object;
      JSONArray jsonArray = new JSONArray();
      for (Object child : iterable) {
        jsonValue = toJSONTree(child);
        jsonArray.add(jsonValue);
      }
      return jsonArray;
    }
    if (object.getClass().getSimpleName().startsWith("[")) {
      JSONArray jsonArray = new JSONArray();
      Object[] array = (Object[]) object;

      for (Object child : array) {
        jsonValue = toJSONTree(child);
        jsonArray.add(jsonValue);
      }
      return jsonArray;
    }

    jsonValue = JSONFactory.get(object);

    if (jsonValue != null) {
      return jsonValue;
    }

    JSONObject jsonObject = new JSONObject();

    Class<?> objectClass = object.getClass();

    List<Field> fieldsInObject = getInheritedFields(objectClass);

    for (Field field : fieldsInObject) {
      String fieldName = field.getName();

      JSONProperty property;
      Annotation annotation = field.getAnnotation(JSONProperty.class);
      if (annotation != null) {
        property = (JSONProperty) annotation;
        String propertyName = property.name();
        if (!JSONProperty.DEFAULT_NAME.equals(propertyName)) {
          fieldName = propertyName;
        }

        String getterName = getGetterName(field, fieldName);
        Object fieldValue = getFieldValue(object, getterName);

        jsonValue = JSONFactory.get(fieldValue);

        if (jsonValue == null) {
          try {
            jsonValue = toJSONTree(fieldValue);
          } catch (NotPropertiesException e) {
            throw new NotPropertiesException("The field named '" + fieldName + "' contain an object " + fieldValue.getClass().getName() + " doesn't have properties.");
          }
        } else {
          if (property.dontShowIfNull() && jsonValue instanceof JSONNull) {
            continue;
          }

          if (jsonValue.isNumber() && property.dontShowIfZero() && jsonValue.toInteger() == 0) {
            continue;
          }
        }
        jsonObject.setReferenceFieldName(property.field());
        JSONPair jsonPair = new JSONPair(fieldName, jsonValue);
        jsonObject.add(jsonPair);
      }
    }

    if (!jsonObject.hasChilds()) {
      throw new NotPropertiesException("The object " + object.getClass().getName() + " doesn't have properties.");
    }

    return jsonObject;
  }

  private JSON() {
    // Nothing to do. Just protect the object construction.
  }
}
