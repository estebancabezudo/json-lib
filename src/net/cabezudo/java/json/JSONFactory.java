package net.cabezudo.java.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import net.cabezudo.java.json.exceptions.EmptyQueueException;
import net.cabezudo.java.json.exceptions.JSONParseException;
import net.cabezudo.java.json.values.JSONArray;
import net.cabezudo.java.json.values.JSONBoolean;
import net.cabezudo.java.json.values.JSONNull;
import net.cabezudo.java.json.values.JSONNumber;
import net.cabezudo.java.json.values.JSONObject;
import net.cabezudo.java.json.values.JSONString;
import net.cabezudo.java.json.values.JSONValue;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 01/22/2016
 */
public class JSONFactory {

  /**
   *
   * @param object
   * @return
   */
  public static JSONValue get(Object object) {
    if (object == null) {
      return JSONNull.getValue();
    }
    if (object instanceof JSONValue) {
      Log.debug("Return from factory, untouched, %s with the value %s.%n", object.getClass().getName(), object);
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
    if (jsonValue == null) {
      Log.debug("Return null from factory.%n");
    } else {
      Log.debug("Return from factory a %s with the value %s.%n", jsonValue.getClass().getName(), jsonValue.toJSON());
    }

    return jsonValue;
  }

  private JSONString createJSONString(Token token) {
    String valueInQuotes = token.getValue();
    String value = valueInQuotes.substring(1, valueInQuotes.length() - 1);
    return new JSONString(value);
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
        Log.debug("LEFT_BRACE.%n");
        jsonValue = getJSONObject(tokens);
        return jsonValue;
      case LEFT_BRACKET:
        Log.debug("LEFT_BRACKET.%n");
        jsonValue = getJSONArray(tokens);
        return jsonValue;
      case FALSE:
      case TRUE:
        boolean booleanValue = "true".equals(token.getValue());
        jsonValue = JSONBoolean.get(booleanValue);
        return jsonValue;
      case NULL:
        jsonValue = JSONNull.getValue();
        return jsonValue;
      default:
        position = token.getPosition();
        throw new JSONParseException("Value expected but I have '" + token.getValue() + "' of type '" + type + "' in line " + position.getLine() + ", row " + position.getRow() + ".", position);
    }
  }

  JSONArray getJSONArray(Tokens tokens) throws JSONParseException {

    JSONArray jsonArray = new JSONArray();
    Log.debug("Create a new array %s.%n", jsonArray);
    Token token;
    int line = 1;
    Position position = Position.INITIAL;
    token = tokens.element();

    if (token.isRightBracket()) {
      Log.debug("Is right bracket.%n");
      return jsonArray;
    }

    try {
      do {
        JSONValue jsonValue = get(tokens);
        Log.debug("The new JSON value created is %s.%n", jsonValue);
        jsonArray.add(jsonValue);

        token = tokens.poll();
        position = token.getPosition();
        Log.debug("Token: %s.%n", token);

      } while (token.getType() == TokenType.COMMA);

      if (!token.isRightBracket()) {
        throw new JSONParseException("Closing bracket expected and found " + token + ".", position);
      }

    } catch (EmptyQueueException e) {
      throw new JSONParseException("Unexpected end.", e, position);
    }
    return jsonArray;
  }

  JSONObject getJSONObject(Tokens tokens) throws JSONParseException {
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
        JSONPair jsonPair = new JSONPair(jsonString.toString(), jsonValue);
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
