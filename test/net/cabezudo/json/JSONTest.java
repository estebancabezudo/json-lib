package net.cabezudo.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import net.cabezudo.json.exceptions.ElementNotExistException;
import net.cabezudo.json.exceptions.JSONParseException;
import net.cabezudo.json.exceptions.PropertyNotExistException;
import net.cabezudo.json.objects.Book;
import net.cabezudo.json.objects.Types;
import net.cabezudo.json.values.JSONArray;
import net.cabezudo.json.values.JSONNull;
import net.cabezudo.json.values.JSONObject;
import net.cabezudo.json.values.JSONValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 08/03/2016
 */
public class JSONTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testParse() throws ElementNotExistException {
    Log.debug("Parse a string into a JSONElement tree.");
    String jsonUnparsedString = "{ \"array\": [ 1, 2, \"3\", 4], \"boolean\": true, \"null\": null, \"number\": 324, \"anotherNumber\": 324.3, \"object\": { \"string\": \"George \\\"Baby Face\\\" Nelson\", \"number\": 234 } }";
    Log.debug("String: " + jsonUnparsedString);
    try {
      JSONObject jsonObject = JSON.parse(jsonUnparsedString).toObject();

      JSONArray jsonArray = jsonObject.getJSONArray("array");
      JSONValue jsonValue = jsonArray.getValue(1);
      assertTrue(jsonValue.isNumber());

      jsonValue = jsonObject.getValue("boolean");
      assertTrue(jsonValue.isBoolean());

      jsonValue = jsonObject.getValue("null");
      assertTrue(jsonValue.isNull());

      jsonValue = jsonObject.getValue("anotherNumber");
      assertTrue(jsonValue.isNumber());

      jsonObject = jsonObject.getValue("object").toObject();
      assertTrue(jsonObject.isObject());

      jsonValue = jsonObject.getValue("string");
      assertTrue(jsonValue.isString());
      String name = jsonValue.toString();
      Log.debug("Name: " + name);
      assertEquals("George \\\"Baby Face\\\" Nelson", name);

      jsonValue = jsonObject.getValue("number");
      assertTrue(jsonValue.isNumber());

    } catch (PropertyNotExistException | JSONParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testParsePath() throws IOException, ElementNotExistException {
    Log.debug("Get a string from a file and parse into a JSONElement tree.");

    final File temporaryFile = folder.newFile("tempFile.txt");
    try {
      try (PrintWriter writer = new PrintWriter(temporaryFile, "UTF-8")) {
        writer.println("{ \"array\": [ 1, 2, \"3\", 4], \"boolean\": true, \"null\": null, \"number\": 324, \"anotherNumber\": 324.3, \"object\": { \"string\": \"Esteban Cabezudo\", \"number\": 234 } }");
      }
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      fail(e.getMessage());
    }

    Path path;
    try {
      URI uri = temporaryFile.toURI();
      path = Paths.get(uri);

      JSONObject jsonObject = JSON.parse(path, "utf-8").toObject();
//  { "array": [ 1, 2, "3", 4], "boolean": true, "null": null, "number": 324, "anotherNumber": 324.3, "object": { "string": "Esteban Cabezudo", "number": 234 } }

      JSONArray jsonArray = jsonObject.getJSONArray("array");
      JSONValue jsonValue = jsonArray.getValue(1);
      assertTrue(jsonValue.isNumber());

      jsonValue = jsonObject.getValue("boolean");
      assertTrue(jsonValue.isBoolean());

      jsonValue = jsonObject.getValue("null");
      assertTrue(jsonValue.isNull());

      jsonValue = jsonObject.getValue("anotherNumber");
      assertTrue(jsonValue.isNumber());

      jsonObject = jsonObject.getValue("object").toObject();
      assertTrue(jsonObject.isObject());

      jsonValue = jsonObject.getValue("string");
      assertTrue(jsonValue.isString());
      String name = jsonValue.toString();
      assertEquals("Esteban Cabezudo", name);

      jsonValue = jsonObject.getValue("number");
      assertTrue(jsonValue.isNumber());
    } catch (JSONParseException | PropertyNotExistException e) {
      fail(e.getMessage());
    }
  }

  // TODO the parse fail to explain the lack of rigth brace.
  public void testUnexpectedBracket() throws JSONParseException {
    JSONArray jsonArray = JSON.parse("[ { \"person\": { \"name\": \"John\" } ]").toJSONArray();
  }

  @Test
  public void testToJSONArray() throws ElementNotExistException {

    List<JSONable> list = new ArrayList<>();

    Book book;

    book = new Book(1, "Evolution");
    list.add(book);
    book = new Book(2, "The double");
    list.add(book);

    JSONArray jsonArray = JSON.toJSONArray(list);
    assertEquals(2, jsonArray.size());

    JSONValue jsonValue;
    jsonValue = jsonArray.getValue(0);
    assertEquals("{ \"id\": 1, \"name\": \"Evolution\" }", jsonValue.toJSON());
    jsonValue = jsonArray.getValue(1);
    assertEquals("{ \"id\": 2, \"name\": \"The double\" }", jsonValue.toJSON());
  }

  @Test
  public void testToJSONTree() {

    try {
      Log.debug("Create a JSON tree with all the know types.%n");
      Types types = new Types();
      JSONObject jsonTypesValue = JSON.toJSONTree(types).toObject();
      Log.debug("Types to JSON string: %s.%n", jsonTypesValue.toJSON());
      assertEquals(BigDecimal.class, jsonTypesValue.getBigDecimal("bigDecimal").getClass());
      assertEquals(BigInteger.class, jsonTypesValue.getBigInteger("bigInteger").getClass());
      assertEquals(byte[].class, jsonTypesValue.getByteArray("byteArray").getClass());
      assertEquals(GregorianCalendar.class, jsonTypesValue.getCalendar("calendar").getClass());
      assertEquals(Boolean.class, jsonTypesValue.getBoolean("oBoolean").getClass());
      assertEquals(Byte.class, jsonTypesValue.getByte("oByte").getClass());
      assertEquals(Character.class, jsonTypesValue.getCharacter("oCharacter").getClass());
      assertEquals(Double.class, jsonTypesValue.getDouble("oDouble").getClass());
      assertEquals(Float.class, jsonTypesValue.getFloat("oFloat").getClass());
      assertEquals(Integer.class, jsonTypesValue.getInteger("oInteger").getClass());
      assertEquals(Long.class, jsonTypesValue.getLong("oLong").getClass());
      assertEquals(Short.class, jsonTypesValue.getShort("oShort").getClass());
      assertEquals(Boolean.class, jsonTypesValue.getBoolean("pBoolean").getClass());
      assertEquals(Byte.class, jsonTypesValue.getByte("pByte").getClass());
      assertEquals(Character.class, jsonTypesValue.getCharacter("pCharacter").getClass());
      assertEquals(Double.class, jsonTypesValue.getDouble("pDouble").getClass());
      assertEquals(Float.class, jsonTypesValue.getFloat("pFloat").getClass());
      assertEquals(Integer.class, jsonTypesValue.getInteger("pInteger").getClass());
      assertEquals(Long.class, jsonTypesValue.getLong("pLong").getClass());
      assertEquals(Short.class, jsonTypesValue.getShort("pShort").getClass());
      assertEquals(String.class, jsonTypesValue.getString("string").getClass());
      assertEquals(types.getBigDecimal(), jsonTypesValue.getBigDecimal("bigDecimal"));
      assertEquals(types.getBigInteger(), jsonTypesValue.getBigInteger("bigInteger"));
      assertTrue(Arrays.equals(types.getByteArray(), jsonTypesValue.getByteArray("byteArray")));
      assertEquals(types.getCalendar(), jsonTypesValue.getCalendar("calendar"));
      assertEquals(types.isOBoolean(), jsonTypesValue.getBoolean("oBoolean"));
      assertEquals(types.getOByte(), jsonTypesValue.getByte("oByte"));
      assertEquals(types.getOCharacter(), jsonTypesValue.getCharacter("oCharacter"));
      assertEquals(types.getODouble(), jsonTypesValue.getDouble("oDouble"));
      assertEquals(types.getOFloat(), jsonTypesValue.getFloat("oFloat"));
      assertEquals(types.getOInteger(), jsonTypesValue.getInteger("oInteger"));
      assertEquals(types.getOLong(), jsonTypesValue.getLong("oLong"));
      assertEquals(types.getOShort(), jsonTypesValue.getShort("oShort"));
      assertEquals(new JSONNull(), jsonTypesValue.getValue("nullReference"));
      assertEquals(types.isPBoolean(), jsonTypesValue.getBoolean("pBoolean"));
      assertEquals(types.getPByte(), (long) jsonTypesValue.getByte("pByte"));
      assertEquals(types.getPCharacter(), (long) jsonTypesValue.getCharacter("pCharacter"));
      assertEquals(types.getPDouble(), jsonTypesValue.getDouble("pDouble"), 0.1);
      assertEquals(types.getPFloat(), jsonTypesValue.getFloat("pFloat"), 0.1);
      assertEquals(types.getPInteger(), (long) jsonTypesValue.getInteger("pInteger"));
      assertEquals(types.getPLong(), (long) jsonTypesValue.getLong("pLong"));
      assertEquals(types.getPShort(), (long) jsonTypesValue.getShort("pShort"));
      assertEquals(types.getString(), jsonTypesValue.getString("string"));
    } catch (PropertyNotExistException e) {
      fail(e.getMessage());
    }
  }
}
