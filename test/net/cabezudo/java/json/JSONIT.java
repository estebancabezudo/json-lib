package net.cabezudo.java.json;

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
import net.cabezudo.java.json.exceptions.JSONParseException;
import net.cabezudo.java.json.exceptions.PropertyNotExistException;
import net.cabezudo.java.json.exceptions.ReadFileException;
import net.cabezudo.java.json.objects.Book;
import net.cabezudo.java.json.objects.BookList;
import net.cabezudo.java.json.objects.Data;
import net.cabezudo.java.json.objects.Storage;
import net.cabezudo.java.json.objects.Types;
import net.cabezudo.java.json.values.JSONArray;
import net.cabezudo.java.json.values.JSONNull;
import net.cabezudo.java.json.values.JSONObject;
import net.cabezudo.java.json.values.JSONValue;
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
public class JSONIT {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testParse() {
    System.out.println("Parse a string into a JSONElement tree.");
    String jsonUnparsedString = "{ \"array\": [ 1, 2, \"3\", 4], \"boolean\": true, \"null\": null, \"number\": 324, \"anotherNumber\": 324.3, \"object\": { \"string\": \"Esteban Cabezudo\", \"number\": 234 } }";
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
      assertEquals("Esteban Cabezudo", name);

      jsonValue = jsonObject.getValue("number");
      assertTrue(jsonValue.isNumber());

    } catch (PropertyNotExistException | JSONParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testParsePath() throws IOException, ReadFileException {
    System.out.println("Get a string from a file and parse into a JSONElement tree.");

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

    // TODO Make this test
    // TODO Make this test
  }

  @Test
  public void testToJSONArray() {

    List<JSONable> list = new ArrayList<>();

    Book book;

    book = new Book(1, "Evolution");
    list.add(book);
    book = new Book(2, "The double");
    list.add(book);

    JSONArray jsonArray = JSON.toJSONArray(list);
    assertEquals(2, jsonArray.size());

    JSONValue jsonElement;
    jsonElement = jsonArray.get(0);
    assertEquals("{ \"id\": 1, \"name\": \"Evolution\" }", jsonElement.toJSON());
    jsonElement = jsonArray.get(1);
    assertEquals("{ \"id\": 2, \"name\": \"The double\" }", jsonElement.toJSON());
  }

  @Test
  public void testToJSONReferencedTree() {
    System.out.println("Create a refered JSON tree using Java objects.");
    Data data = new Data();
    JSONObject jsonObject = JSON.toJSONTree(data).toObject();
    JSONElement jsonReferencedTree = JSON.toJSONReferencedTree(jsonObject);

    String expectedString = "{ \"version\": 1, \"countryId\": 1, \"countryName\": { \"version\": 1, \"language\": 97, \"nameType\": 1, \"word\": 2 } }";

    assertEquals(expectedString, jsonReferencedTree.toJSON());

    BookList bookList = new BookList();
    Book book;

    book = new Book(1, "El doble.");
    bookList.add(book);

    book = new Book(8, "El principito.");
    Book mostImportantBook = book;
    bookList.add(book);

    book = new Book(13, "Crónica de una muerte anunciada.");
    bookList.add(book);

    JSONObject jsonBookObject = JSON.toJSONTree(book).toObject();
    assertEquals("{ \"id\": 13, \"name\": \"Crónica de una muerte anunciada.\" }", jsonBookObject.toJSON());

    JSONElement jsonBookReferencedTree = JSON.toJSONReferencedTree(jsonBookObject);
    assertEquals("{ \"id\": 13, \"name\": \"Crónica de una muerte anunciada.\" }", jsonBookReferencedTree.toJSON());

    JSONValue jsonBookListObject = JSON.toJSONTree(bookList);
    assertEquals("[ { \"id\": 1, \"name\": \"El doble.\" }, { \"id\": 8, \"name\": \"El principito.\" }, { \"id\": 13, \"name\": \"Crónica de una muerte anunciada.\" } ]", jsonBookListObject.toJSON());

    Storage storage = new Storage(69, bookList, mostImportantBook);

    JSONObject jsonStorageObject = JSON.toJSONTree(storage).toObject();
    assertEquals("{ \"id\": 69, \"list\": [ { \"id\": 1, \"name\": \"El doble.\" }, { \"id\": 8, \"name\": \"El principito.\" }, { \"id\": 13, \"name\": \"Crónica de una muerte anunciada.\" } ], \"mostImportantBook\": { \"id\": 8, \"name\": \"El principito.\" } }", jsonStorageObject.toJSON());

    JSONElement jsonStorageReferencedTree = JSON.toJSONReferencedTree(jsonStorageObject);
    assertEquals("{ \"id\": 69, \"list\": [ 1, 8, 13 ], \"mostImportantBook\": 8 }", jsonStorageReferencedTree.toJSON());
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
      assertEquals(JSONNull.getValue(), jsonTypesValue.getValue("nullReference"));
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
