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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import net.cabezudo.json.exceptions.EmptyQueueException;
import net.cabezudo.json.exceptions.JSONParseException;
import net.cabezudo.json.values.JSONArray;
import net.cabezudo.json.values.JSONBoolean;
import net.cabezudo.json.values.JSONNull;
import net.cabezudo.json.values.JSONNumber;
import net.cabezudo.json.values.JSONObject;
import net.cabezudo.json.values.JSONString;
import net.cabezudo.json.values.JSONValue;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.00, 01/22/2016
 */
public class JSONFactory {

  /**
   * Create a JSON structure representation of the object passed. For the conversion the method use
   * a the next rules.
   * <ul>
   * <li>
   * If the object is a {@link java.lang.Byte}, {@link java.lang.Short},
   * {@link java.lang.Integer}, {@link java.lang.Long}, {@link java.lang.Float},
   * {@link java.lang.Double}, {@link java.math.BigInteger}, or {@link java.math.BigDecimal} the
   * method use a {@link JSONNumber}.
   * </li>
   * <li>
   * If some property is a {@code byte}, {@code short}, {@code int}, {@code long}, {@code float}, or
   * {@code double}, the method use a {@link JSONNumber}.
   * </li>
   * <li>
   * If the object or some property is a {@link java.lang.Boolean} the method use a
   * {@link JSONBoolean}.
   * </li>
   * <li>
   * If some property is a {@code boolean} the method use a {@link JSONBoolean}.
   * </li>
   * <li>
   * If the object or some property is a {@link java.lang.Character}, {@link java.lang.String},
   * {@link java.util.Date}, {@link java.util.GregorianCalendar}, or {@link java.lang.Class} the
   * method convert it to a {@link net.cabezudo.json.values.JSONString}.
   * </li>
   * <li>
   * If some property is a {@code char} the method convert it to a {@link net.cabezudo.json.values.JSONString}.
   * </li>
   * <li>
   * If a propery is a {@code byte[]} the method use a {@link JSONArray}.
   * </li>
   * <li>
   * If the conversión can't be done using this rules the system try to convert the object using the
   * {@link net.cabezudo.json.annotations.JSONProperty} annotation.
   * </li>
   * </ul>
   *
   * @param object the POJO from create the JSON structure.
   * @return a {@link JSONValue} with a JSON structure.
   */
  public static JSONValue get(Object object) {
    if (object == null) {
      return new JSONNull();
    }
    if (object instanceof JSONValue) {
      return (JSONValue) object;
    }
    Class<?> objectClass = object.getClass();
    String fieldTypeName = objectClass.getName();
    SimpleDateFormat sdf;
    String stringDate;
    JSONValue jsonValue;
    switch (fieldTypeName) {
      case "byte":
      case "java.lang.Byte":
        jsonValue = new JSONNumber((Byte) object);
        break;
      case "short":
      case "java.lang.Short":
        jsonValue = new JSONNumber((Short) object);
        break;
      case "int":
      case "java.lang.Integer":
        jsonValue = new JSONNumber((Integer) object);
        break;
      case "long":
      case "java.lang.Long":
        jsonValue = new JSONNumber((Long) object);
        break;
      case "float":
      case "java.lang.Float":
        jsonValue = new JSONNumber((Float) object);
        break;
      case "double":
      case "java.lang.Double":
        jsonValue = new JSONNumber((Double) object);
        break;
      case "boolean":
      case "java.lang.Boolean":
        jsonValue = JSONBoolean.get((Boolean) object);
        break;
      case "char":
      case "java.lang.Character":
        jsonValue = new JSONString((Character) object);
        break;
      case "java.lang.String":
        jsonValue = new JSONString((String) object);
        break;
      case "java.util.Date":
        Date dateValue = (Date) object;
        sdf = new SimpleDateFormat(JSON.SIMPLE_DATE_FORMAT_PATTERN);
        stringDate = sdf.format(dateValue);
        jsonValue = new JSONString(stringDate);
        break;
      case "java.util.GregorianCalendar":
        Calendar calendarValue = (Calendar) object;
        sdf = new SimpleDateFormat(JSON.SIMPLE_DATE_FORMAT_PATTERN);
        stringDate = sdf.format(calendarValue.getTime());
        jsonValue = new JSONString(stringDate);
        break;
      case "java.math.BigInteger":
        jsonValue = new JSONNumber((BigInteger) object);
        break;
      case "java.math.BigDecimal":
        jsonValue = new JSONNumber((BigDecimal) object);
        break;
      case "java.lang.Class":
        Class classValue = (Class) object;
        String className = classValue.getName();
        jsonValue = new JSONString(className);
        break;
      case "[B":
        JSONArray jsonArray = new JSONArray();
        byte[] byteArray = (byte[]) object;
        for (Byte b : byteArray) {
          jsonArray.add(new JSONNumber(b));
        }
        jsonValue = jsonArray;
        break;
      default:
        jsonValue = null;
        break;
    }
    return jsonValue;
  }

  private JSONString createJSONString(Token token) {
    String valueInQuotes = token.getValue();
    String value = valueInQuotes.substring(1, valueInQuotes.length() - 1);
    return new JSONString(value, token.getPosition());
  }

  private JSONValue get(Tokens tokens) throws JSONParseException {

    Token token;
    Position position = Position.INITIAL;

    try {
      token = tokens.poll();
    } catch (EmptyQueueException e) {
      throw new JSONParseException("Unexpected end of tokens", e, position);
    }

    JSONValue jsonValue;

    TokenType type = token.getType();
    switch (type) {
      case STRING:
        jsonValue = createJSONString(token);
        return jsonValue;
      case NUMBER:
        BigDecimal bigDecimal = new BigDecimal(token.getValue());
        jsonValue = new JSONNumber(bigDecimal);
        return jsonValue;
      case LEFT_BRACE:
        jsonValue = getJSONObject(tokens, token.getPosition());
        return jsonValue;
      case LEFT_BRACKET:
        jsonValue = getJSONArray(tokens, token.getPosition());
        return jsonValue;
      case FALSE:
      case TRUE:
        boolean booleanValue = "true".equals(token.getValue());
        jsonValue = JSONBoolean.get(booleanValue);
        return jsonValue;
      case NULL:
        jsonValue = new JSONNull();
        return jsonValue;
      default:
        position = token.getPosition();
        throw new JSONParseException("Value expected but I have '" + token.getValue() + "' of type '" + type + "' in line " + position.getLine() + ", row " + position.getRow() + ".", position);
    }
  }

  JSONArray getJSONArray(Tokens tokens, Position position) throws JSONParseException {

    JSONArray jsonArray = new JSONArray(position);
    Token token;
    token = tokens.element();

    if (token.isRightBracket()) {
      return jsonArray;
    }

    try {
      do {
        JSONValue jsonValue = get(tokens);
        jsonArray.add(jsonValue);

        token = tokens.poll();
        position = token.getPosition();

      } while (token.getType() == TokenType.COMMA);

      if (!token.isRightBracket()) {
        throw new JSONParseException("Closing bracket expected and found " + token + ".", position);
      }

    } catch (EmptyQueueException e) {
      throw new JSONParseException("Unexpected end.", e, position);
    }
    return jsonArray;
  }

  JSONObject getJSONObject(Tokens tokens, Position position) throws JSONParseException {
    JSONObject jsonObject = new JSONObject();
    Token token;
    Position positionOnToken = Position.INITIAL;
    token = tokens.element();
    if (token.isRightBrace()) {
      return jsonObject;
    }
    try {
      do {
        token = tokens.poll();
        if (token.getType() != TokenType.STRING) {
          throw new JSONParseException("Unexpected token: " + token, token.getPosition());
        }
        JSONString jsonString = createJSONString(token);

        token = tokens.poll();
        if (token.getType() != TokenType.COLON) {
          positionOnToken = token.getPosition();
          throw new JSONParseException("Colon expected.", positionOnToken);
        }

        JSONValue jsonValue = get(tokens);
        JSONPair jsonPair = new JSONPair(jsonString.toString(), jsonValue, position);
        jsonObject.add(jsonPair);

        token = tokens.poll();
        positionOnToken = token.getPosition();
      } while (token.getType() == TokenType.COMMA);
    } catch (EmptyQueueException e) {
      throw new JSONParseException("Unexpected end.", e, positionOnToken);
    }
    return jsonObject;
  }
}
