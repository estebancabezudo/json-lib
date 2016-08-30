package net.cabezudo.java.json.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Calendar;
import java.util.List;
import net.cabezudo.java.json.JSONPair;
import net.cabezudo.java.json.exceptions.ParseException;
import net.cabezudo.java.json.exceptions.PropertyNotExistException;
import net.cabezudo.java.json.objects.DigTypes;
import net.cabezudo.java.json.objects.Types;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/18/2016
 */
public class JSONObjectIT {

  private final int BIG_DECIMAL = 0;
  private final int BIG_INTEGER = 1;
  private final int BOOK = 2;
  private final int BOOLEAN = 15;
  private final int BYTE = 16;
  private final int BYTE_ARRAY = 3;
  private final int CALENDAR = 4;
  private final int CHARACTER = 17;
  private final int OBJECT_CHARACTER = 9;
  private final int DATE = 5;
  private final int DOUBLE = 18;
  private final int FLOAT = 19;
  private final int INTEGER = 20;
  private final int OBJECT_INTEGER = 12;
  private final int LONG = 21;
  private final int NULL_REFERENCE = 6;
  private final int OBJECT_BOOLEAN = 7;
  private final int OBJECT_BYTE = 8;
  private final int OBJECT_DOUBLE = 10;
  private final int OBJECT_FLOAT = 11;
  private final int OBJECT_LONG = 13;
  private final int OBJECT_SHORT = 14;
  private final int SHORT = 22;
  private final int STRING = 23;

  @Test
  public void testAdd() {
    JSONObject jsonObject = new JSONObject();
    JSONPair jsonNamePair = new JSONPair("name", "Esteban");
    jsonObject.add(jsonNamePair);
    JSONPair jsonNumberOfLegsPair = new JSONPair("numberOfLegs", 2);
    jsonObject.add(jsonNumberOfLegsPair);

    String s = jsonObject.toJSON();
    assertEquals("{ \"name\": \"Esteban\", \"numberOfLegs\": 2 }", s);
  }

  @Test
  public void testCompareTo() {
  }

  @Test
  public void testDeleteElementUsingAnIndex() {
    JSONObject jsonObject = new JSONObject();
    JSONPair jsonNamePair = new JSONPair("name", "Esteban");
    jsonObject.add(jsonNamePair);
    JSONPair jsonNumberOfLegsPair = new JSONPair("numberOfLegs", 2);
    jsonObject.add(jsonNumberOfLegsPair);

    String s;

    s = jsonObject.toJSON();
    assertEquals("{ \"name\": \"Esteban\", \"numberOfLegs\": 2 }", s);

    jsonObject.deleteElement(0);

    s = jsonObject.toJSON();
    assertEquals("{ \"numberOfLegs\": 2 }", s);
  }

  @Test
  public void testDeleteElementUsingThePropertyName() {
    JSONObject jsonObject = new JSONObject();
    JSONPair jsonNamePair = new JSONPair("name", "Esteban");
    jsonObject.add(jsonNamePair);
    JSONPair jsonNumberOfLegsPair = new JSONPair("numberOfLegs", 2);
    jsonObject.add(jsonNumberOfLegsPair);

    String s;

    s = jsonObject.toJSON();
    assertEquals("{ \"name\": \"Esteban\", \"numberOfLegs\": 2 }", s);

    jsonObject.deleteElement("name");

    s = jsonObject.toJSON();
    assertEquals("{ \"numberOfLegs\": 2 }", s);
  }

  @Test
  public void testDigBoolean() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    boolean b = jsonObject.digBoolean("types.pBoolean");
    assertEquals(false, b);
  }

  @Test
  public void testDigByte() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    byte b = jsonObject.digByte("types.pByte");
    assertEquals(1, b);
  }

  @Test
  public void testDigChar() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    char c = jsonObject.digCharacter("types.pCharacter");
    assertEquals('a', c);
  }

  @Test
  public void testDigCharacter() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    char c = jsonObject.digCharacter("types.oCharacter");
    assertEquals('b', c);
  }

  @Test
  public void testDigDouble() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    double d = jsonObject.digDouble("types.pDouble");
    assertEquals(11.5, d, 0);
  }

  @Test
  public void testDigFloat() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Float f = jsonObject.digFloat("types.pFloat");
    assertEquals(9.5, f, 0);
  }

  @Test
  public void testDigInt() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    int i = jsonObject.digInteger("types.pInteger");
    assertEquals(5, i);
  }

  @Test
  public void testDigInteger() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    int i = jsonObject.digInteger("types.oInteger");
    assertEquals(6, i);
  }

  @Test
  public void testDigLong() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    long l = jsonObject.digLong("types.pLong");
    assertEquals(7, l);
  }

  @Test
  public void testDigNullByte() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullByte("noExiste");
    assertEquals(null, o);

    byte b = jsonObject.digNullByte("types.pByte");
    assertEquals(1, b);
  }

  @Test
  public void testDigNullChar() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullCharacter("noExiste");
    assertEquals(null, o);

    char c = jsonObject.digNullCharacter("types.pCharacter");
    assertEquals('a', c);
  }

  @Test
  public void testDigNullCharacter() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    char c = jsonObject.digNullCharacter("types.oCharacter");
    assertEquals('b', c);
  }

  @Test
  public void testDigNullDouble() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullDouble("noExiste");
    assertEquals(null, o);

    double d = jsonObject.digNullDouble("types.pDouble");
    assertEquals(11.5, d, 0);
  }

  @Test
  public void testDigNullFloat() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullFloat("noExiste");
    assertEquals(null, o);

    Float f = jsonObject.digNullFloat("types.pFloat");
    assertEquals(9.5, f, 0);
  }

  @Test
  public void testDigNullInt() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullInteger("noExiste");
    assertEquals(null, o);

    int i = jsonObject.digNullInteger("types.pInteger");
    assertEquals(5, i);
  }

  @Test
  public void testDigNullInteger() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    int i = jsonObject.digNullInteger("types.oInteger");
    assertEquals(6, i);
  }

  @Test
  public void testDigNullLong() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullLong("noExiste");
    assertEquals(null, o);

    long l = jsonObject.digNullLong("types.pLong");
    assertEquals(7, l);
  }

  @Test
  public void testDigNullObjectByte() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullByte("noExiste");
    assertEquals(null, o);

    byte b = jsonObject.digNullByte("types.oByte");
    assertEquals(2, b);
  }

  @Test
  public void testDigNullObjectDouble() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    double d = jsonObject.digNullDouble("types.oDouble");
    assertEquals(12.5, d, 0);
  }

  @Test
  public void testDigNullObjectFloat() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Float f = jsonObject.digNullFloat("types.oFloat");
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testDigNullObjectLong() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    long l = jsonObject.digNullLong("types.oLong");
    assertEquals(8, l);
  }

  @Test
  public void testDigNullObjectShort() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    short s = jsonObject.digNullShort("types.oShort");
    assertEquals(4, s);
  }

  @Test
  public void testDigNullShort() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullShort("noExiste");
    assertEquals(null, o);

    short s = jsonObject.digNullShort("types.pShort");
    assertEquals(3, s);
  }

  @Test
  public void testDigNullString() throws ParseException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Object o = jsonObject.digNullString("noExiste");
    assertEquals(null, o);

    String s = jsonObject.digNullString("types.string");
    assertEquals("Esteban", s);
  }

  @Test
  public void testDigObjectBoolean() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    boolean b = jsonObject.digBoolean("types.oBoolean");
    assertEquals(true, b);
  }

  @Test
  public void testDigObjectByte() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    byte b = jsonObject.digByte("types.oByte");
    assertEquals(2, b);
  }

  @Test
  public void testDigObjectDouble() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    double d = jsonObject.digDouble("types.oDouble");
    assertEquals(12.5, d, 0);
  }

  @Test
  public void testDigObjectFloat() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    Float f = jsonObject.digFloat("types.oFloat");
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testDigObjectLong() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    long l = jsonObject.digLong("types.oLong");
    assertEquals(8, l);
  }

  @Test
  public void testDigObjectShort() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    short s = jsonObject.digShort("types.oShort");
    assertEquals(4, s);
  }

  @Test
  public void testDigShort() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    short s = jsonObject.digShort("types.pShort");
    assertEquals(3, s);
  }

  @Test
  public void testDigString() throws ParseException, PropertyNotExistException {
    DigTypes digTypes = new DigTypes();
    JSONObject jsonObject = new JSONObject(digTypes);

    String s = jsonObject.digString("types.string");
    assertEquals("Esteban", s);
  }

  @Test
  public void testGetBigDecimalUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigDecimal b = jsonObject.getBigDecimal(BIG_DECIMAL);
    BigDecimal bigDecimal = new BigDecimal(15.4, MathContext.DECIMAL32).setScale(JSONNumber.DEFAULT_SCALE).stripTrailingZeros();
    assertEquals(bigDecimal, b);
  }

  @Test
  public void testGetBigDecimalUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigDecimal b = jsonObject.getBigDecimal("bigDecimal");
    BigDecimal bigDecimal = new BigDecimal(15.4, MathContext.DECIMAL32).setScale(JSONNumber.DEFAULT_SCALE).stripTrailingZeros();
    assertEquals(bigDecimal, b);
  }

  @Test
  public void testGetBigIntegerUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigInteger b = jsonObject.getBigInteger(BIG_INTEGER);
    assertEquals(new BigInteger("14"), b);
  }

  @Test
  public void testGetBigIntegerUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigInteger b = jsonObject.getBigInteger("bigInteger");
    assertEquals(new BigInteger("14"), b);
  }

  @Test
  public void testGetBooleanObjectUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getBoolean("oBoolean");
    assertEquals(true, b);
  }

  @Test
  public void testGetBooleanUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getBoolean(BOOLEAN);
    assertEquals(false, b);
  }

  @Test
  public void testGetBooleanUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getBoolean("pBoolean");
    assertEquals(false, b);
  }

  @Test
  public void testGetByteArrayUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte[] b = jsonObject.getByteArray(BYTE_ARRAY);
    assertEquals(4, b.length);
    assertEquals(1, b[0]);
    assertEquals(2, b[1]);
    assertEquals(3, b[2]);
    assertEquals(4, b[3]);
  }

  @Test
  public void testGetByteArrayUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte[] b = jsonObject.getByteArray("byteArray");
    assertEquals(4, b.length);
    assertEquals(1, b[0]);
    assertEquals(2, b[1]);
    assertEquals(3, b[2]);
    assertEquals(4, b[3]);
  }

  @Test
  public void testGetByteUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getByte(BYTE);
    assertEquals(1, b);
  }

  @Test
  public void testGetByteUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getByte("pByte");
    assertEquals(1, b);
  }

  @Test
  public void testGetCalendarUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Calendar expectedCalendar = Calendar.getInstance();
    expectedCalendar.set(1974, 0, 30, 14, 20, 12);
    expectedCalendar.set(Calendar.MILLISECOND, 125);

    Calendar calendar = jsonObject.getCalendar(CALENDAR);
    assertEquals(expectedCalendar, calendar);
  }

  @Test
  public void testGetCalendarUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Calendar expectedCalendar = Calendar.getInstance();
    expectedCalendar.set(1974, 0, 30, 14, 20, 12);
    expectedCalendar.set(Calendar.MILLISECOND, 125);

    Calendar calendar = jsonObject.getCalendar("calendar");
    assertEquals(expectedCalendar, calendar);
  }

  @Test
  public void testGetCharUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    char c = jsonObject.getCharacter(CHARACTER);
    assertEquals('a', c);
  }

  @Test
  public void testGetCharUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    char c = jsonObject.getCharacter("pCharacter");
    assertEquals('a', c);
  }

  @Test
  public void testGetCharacterUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    char c = jsonObject.getCharacter(OBJECT_CHARACTER);
    assertEquals('b', c);
  }

  @Test
  public void testGetCharacterUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    char c = jsonObject.getCharacter("oCharacter");
    assertEquals('b', c);
  }

  @Test
  public void testGetChilds() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    List<JSONPair> childs = jsonObject.getChilds();
    assertEquals(24, childs.size());

    JSONPair pair;

    pair = childs.get(BIG_DECIMAL);
    assertEquals("bigDecimal", pair.getKey());
    assertEquals(new JSONNumber(15.4), pair.getValue());

    pair = childs.get(BIG_INTEGER);
    assertEquals("bigInteger", pair.getKey());
    assertEquals(new JSONNumber(14), pair.getValue());

    pair = childs.get(BYTE_ARRAY);
    assertEquals("byteArray", pair.getKey());
    JSONArray array = (JSONArray) pair.getValue();
    assertEquals(4, array.size());
    assertEquals(new JSONNumber(1), array.getValue(0));
    assertEquals(new JSONNumber(2), array.getValue(1));
    assertEquals(new JSONNumber(3), array.getValue(2));
    assertEquals(new JSONNumber(4), array.getValue(3));

    pair = childs.get(CALENDAR);
    assertEquals("calendar", pair.getKey());
    assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), pair.getValue());

    pair = childs.get(DATE);
    assertEquals("date", pair.getKey());
    assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), pair.getValue());

    pair = childs.get(OBJECT_BOOLEAN);
    assertEquals("oBoolean", pair.getKey());
    assertEquals(JSONBoolean.TRUE, pair.getValue());

    pair = childs.get(OBJECT_BYTE);
    assertEquals("oByte", pair.getKey());
    assertEquals(new JSONNumber(2), pair.getValue());

    pair = childs.get(OBJECT_CHARACTER);
    assertEquals("oCharacter", pair.getKey());
    assertEquals(new JSONString("b"), pair.getValue());

    pair = childs.get(OBJECT_DOUBLE);
    assertEquals("oDouble", pair.getKey());
    assertEquals(new JSONNumber(12.5), pair.getValue());

    pair = childs.get(OBJECT_FLOAT);
    assertEquals("oFloat", pair.getKey());
    assertEquals(new JSONNumber(10.5), pair.getValue());

    pair = childs.get(OBJECT_INTEGER);
    assertEquals("oInteger", pair.getKey());
    assertEquals(new JSONNumber(6), pair.getValue());

    pair = childs.get(OBJECT_LONG);
    assertEquals("oLong", pair.getKey());
    assertEquals(new JSONNumber(8), pair.getValue());

    pair = childs.get(OBJECT_SHORT);
    assertEquals("oShort", pair.getKey());
    assertEquals(new JSONNumber(4), pair.getValue());

    pair = childs.get(NULL_REFERENCE);
    assertEquals("nullReference", pair.getKey());
    assertEquals(JSONNull.getValue(), pair.getValue());

    pair = childs.get(BOOLEAN);
    assertEquals("pBoolean", pair.getKey());
    assertEquals(JSONBoolean.get(false), pair.getValue());

    pair = childs.get(BYTE);
    assertEquals("pByte", pair.getKey());
    assertEquals(new JSONNumber(1), pair.getValue());

    pair = childs.get(CHARACTER);
    assertEquals("pCharacter", pair.getKey());
    assertEquals(new JSONString('a'), pair.getValue());

    pair = childs.get(DOUBLE);
    assertEquals("pDouble", pair.getKey());
    assertEquals(new JSONNumber(11.5), pair.getValue());

    pair = childs.get(FLOAT);
    assertEquals("pFloat", pair.getKey());
    assertEquals(new JSONNumber(9.5), pair.getValue());

    pair = childs.get(INTEGER);
    assertEquals("pInteger", pair.getKey());
    assertEquals(new JSONNumber(5), pair.getValue());

    pair = childs.get(LONG);
    assertEquals("pLong", pair.getKey());
    assertEquals(new JSONNumber(7), pair.getValue());

    pair = childs.get(SHORT);
    assertEquals("pShort", pair.getKey());
    assertEquals(new JSONNumber(3), pair.getValue());

    pair = childs.get(STRING);
    assertEquals("string", pair.getKey());
    assertEquals(new JSONString("Esteban"), pair.getValue());

    pair = childs.get(BOOK);
    assertEquals("book", pair.getKey());
    JSONObject jsonBook = pair.getValue().toObject();
    int id = jsonBook.getInteger("id");
    String name = jsonBook.getString("name");
    assertEquals(1, id);
    assertEquals("Evolution", name);
  }

  @Test
  public void testGetDoubleUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    double d = jsonObject.getDouble(DOUBLE);
    assertEquals(11.5, d, 0);
  }

  @Test
  public void testGetDoubleUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    double d = jsonObject.getDouble("pDouble");
    assertEquals(11.5, d, 0);
  }

  @Test
  public void testGetElementUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONPair pair = jsonObject.getElement(STRING);
    assertEquals(new JSONPair("string", "Esteban"), pair);
  }

  @Test
  public void testGetElementUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONPair pair = jsonObject.getElement("string");
    assertEquals(new JSONPair("string", "Esteban"), pair);
  }

  @Test
  public void testGetFloatUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    float f = jsonObject.getFloat(FLOAT);
    assertEquals(9.5, f, 0);
  }

  @Test
  public void testGetFloatUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    float f = jsonObject.getFloat("pFloat");
    assertEquals(9.5, f, 0);
  }

  @Test
  public void testGetIntUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = jsonObject.getInteger(INTEGER);
    assertEquals(5, i);
  }

  @Test
  public void testGetIntUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = jsonObject.getInteger("pInteger");
    assertEquals(5, i);
  }

  @Test
  public void testGetIntegerUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = jsonObject.getInteger(OBJECT_INTEGER);
    assertEquals(6, i);
  }

  @Test
  public void testGetIntegerUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = jsonObject.getInteger("oInteger");
    assertEquals(6, i);
  }

  @Test
  public void testGetJSONArrayUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONArray array = jsonObject.getJSONArray(BYTE_ARRAY);
    assertEquals(4, array.size());
    assertEquals(new JSONNumber(1), array.getValue(0));
    assertEquals(new JSONNumber(2), array.getValue(1));
    assertEquals(new JSONNumber(3), array.getValue(2));
    assertEquals(new JSONNumber(4), array.getValue(3));
  }

  @Test
  public void testGetJSONArrayUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONArray array = jsonObject.getJSONArray("byteArray");
    assertEquals(4, array.size());
    assertEquals(new JSONNumber(1), array.getValue(0));
    assertEquals(new JSONNumber(2), array.getValue(1));
    assertEquals(new JSONNumber(3), array.getValue(2));
    assertEquals(new JSONNumber(4), array.getValue(3));
  }

  @Test
  public void testGetLongUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    long l = jsonObject.getLong(LONG);
    assertEquals(7, l);
  }

  @Test
  public void testGetLongUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    long l = jsonObject.getLong("pLong");
    assertEquals(7, l);
  }

  @Test
  public void testGetNullBigDecimalUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigDecimal b = jsonObject.getNullBigDecimal(BIG_DECIMAL);
    BigDecimal bigDecimal = new BigDecimal(15.4, MathContext.DECIMAL32).setScale(JSONNumber.DEFAULT_SCALE).stripTrailingZeros();
    assertEquals(bigDecimal, b);
  }

  @Test
  public void testGetNullBigDecimalUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullBigDecimal("noExiste");
    assertEquals(null, o);

    BigDecimal b = jsonObject.getNullBigDecimal("bigDecimal");
    BigDecimal bigDecimal = new BigDecimal(15.4, MathContext.DECIMAL32).setScale(JSONNumber.DEFAULT_SCALE).stripTrailingZeros();
    assertEquals(bigDecimal, b);
  }

  @Test
  public void testGetNullBigIntegerUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigInteger b = jsonObject.getNullBigInteger(BIG_INTEGER);
    assertEquals(new BigInteger("14"), b);
  }

  @Test
  public void testGetNullBigIntegerUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    BigInteger b = jsonObject.getNullBigInteger("bigInteger");
    assertEquals(new BigInteger("14"), b);
  }

  @Test
  public void testGetNullBooleanUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getNullBoolean(BOOLEAN);
    assertEquals(false, b);
  }

  @Test
  public void testGetNullBooleanUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullBoolean("noExiste");
    assertEquals(null, o);

    boolean b = jsonObject.getNullBoolean("pBoolean");
    assertEquals(false, b);
  }

  @Test
  public void testGetNullByteArray() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullByteArray("noExiste");
    assertEquals(null, o);

    byte[] array = jsonObject.getNullByteArray("byteArray");
    assertEquals(4, array.length);
    assertEquals(1, array[0]);
    assertEquals(2, array[1]);
    assertEquals(3, array[2]);
    assertEquals(4, array[3]);
  }

  @Test
  public void testGetNullByteUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getNullByte(BYTE);
    assertEquals(1, b);
  }

  @Test
  public void testGetNullByteUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullByte("noExiste");
    assertEquals(null, o);

    byte b = jsonObject.getNullByte("pByte");
    assertEquals(1, b);
  }

  @Test
  public void testGetNullCalendarUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullByte("noExiste");
    assertEquals(null, o);

    Calendar expectedCalendar = Calendar.getInstance();
    expectedCalendar.set(1974, 0, 30, 14, 20, 12);
    expectedCalendar.set(Calendar.MILLISECOND, 125);

    Calendar calendar = jsonObject.getCalendar(CALENDAR);
    assertEquals(expectedCalendar, calendar);
  }

  @Test
  public void testGetNullCalendarUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullCalendar("noExiste");
    assertEquals(null, o);

    Calendar expectedCalendar = Calendar.getInstance();
    expectedCalendar.set(1974, 0, 30, 14, 20, 12);
    expectedCalendar.set(Calendar.MILLISECOND, 125);

    Calendar calendar = jsonObject.getNullCalendar("calendar");
    assertEquals(expectedCalendar, calendar);
  }

  @Test
  public void testGetNullCharString() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullCharacter("noExiste");
    assertEquals(null, o);

    char c = jsonObject.getNullCharacter("pCharacter");
    assertEquals('a', c);
  }

  @Test
  public void testGetNullCharUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    char c = jsonObject.getNullCharacter(CHARACTER);
    assertEquals('a', c);
  }

  @Test
  public void testGetNullCharacterUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    String a = jsonObject.toJSONArray().toJSON();

    Object o = jsonObject.getNullCharacter("noExiste");
    assertEquals(null, o);

    char c = jsonObject.getNullCharacter(OBJECT_CHARACTER);
    assertEquals('b', c);
  }

  @Test
  public void testGetNullCharacterUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullCharacter("noExiste");
    assertEquals(null, o);

    char c = jsonObject.getNullCharacter("oCharacter");
    assertEquals('b', c);
  }

  @Test
  public void testGetNullDoubleUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    double d = jsonObject.getNullDouble(DOUBLE);
    assertEquals(11.5, d, 0);
  }

  @Test
  public void testGetNullDoubleUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullDouble("noExiste");
    assertEquals(null, o);

    double d = jsonObject.getNullDouble("pDouble");
    assertEquals(11.5, d, 0);
  }

  @Test
  public void testGetNullElementUsingThePropertyIndex() throws PropertyNotExistException, ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONPair pair = jsonObject.getElement(STRING);
    assertEquals(new JSONPair("string", "Esteban"), pair);
  }

  @Test
  public void testGetNullElementUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullElement("noExiste");
    assertEquals(null, o);

    JSONPair pair = jsonObject.getNullElement("string");
    assertEquals(new JSONPair("string", "Esteban"), pair);
  }

  @Test
  public void testGetNullFloatUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    float f = jsonObject.getNullFloat(FLOAT);
    assertEquals(9.5, f, 0);
  }

  @Test
  public void testGetNullFloatUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullFloat("noExiste");
    assertEquals(null, o);

    float f = jsonObject.getNullFloat("pFloat");
    assertEquals(9.5, f, 0);
  }

  @Test
  public void testGetNullIntUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = jsonObject.getNullInteger(INTEGER);
    assertEquals(5, i);
  }

  @Test
  public void testGetNullIntUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullInteger("noExiste");
    assertEquals(null, o);

    int i = jsonObject.getNullInteger("pInteger");
    assertEquals(5, i);
  }

  @Test
  public void testGetNullIntegerUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = jsonObject.getNullInteger(OBJECT_INTEGER);
    assertEquals(6, i);
  }

  @Test
  public void testGetNullIntegerUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullInteger("noExiste");
    assertEquals(null, o);

    int i = jsonObject.getNullInteger("oInteger");
    assertEquals(6, i);
  }

  @Test
  public void testGetNullJSONArrayUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONArray array = jsonObject.getNullJSONArray(BYTE_ARRAY);
    assertEquals(4, array.size());
    assertEquals(new JSONNumber(1), array.getValue(0));
    assertEquals(new JSONNumber(2), array.getValue(1));
    assertEquals(new JSONNumber(3), array.getValue(2));
    assertEquals(new JSONNumber(4), array.getValue(3));
  }

  @Test
  public void testGetNullJSONArrayUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullJSONArray("noExiste");
    assertEquals(null, o);

    JSONArray array = jsonObject.getNullJSONArray("byteArray");
    assertEquals(4, array.size());
    assertEquals(new JSONNumber(1), array.getValue(0));
    assertEquals(new JSONNumber(2), array.getValue(1));
    assertEquals(new JSONNumber(3), array.getValue(2));
    assertEquals(new JSONNumber(4), array.getValue(3));
  }

  @Test
  public void testGetNullLongUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    long l = jsonObject.getNullLong(LONG);
    assertEquals(7, l);
  }

  @Test
  public void testGetNullLongUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullLong("noExiste");
    assertEquals(null, o);

    long l = jsonObject.getNullLong("pLong");
    assertEquals(7, l);
  }

  @Test
  public void testGetNullObjectBooleanUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getNullBoolean(OBJECT_BOOLEAN);
    assertEquals(true, b);
  }

  @Test
  public void testGetNullObjectBooleanUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getNullBoolean("oBoolean");
    assertEquals(true, b);
  }

  public void testGetNullObjectByteUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getNullByte(OBJECT_BYTE);
    assertEquals(2, b);
  }

  @Test
  public void testGetNullObjectByteUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getNullByte("oByte");
    assertEquals(2, b);
  }

  @Test
  public void testGetNullObjectDoubleUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    double d = jsonObject.getNullDouble(OBJECT_DOUBLE);
    assertEquals(12.5, d, 0);
  }

  @Test
  public void testGetNullObjectDoubleUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullDouble("noExiste");
    assertEquals(null, o);

    double d = jsonObject.getNullDouble("oDouble");
    assertEquals(12.5, d, 0);
  }

  @Test
  public void testGetNullObjectFloatUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    float f = jsonObject.getNullFloat(OBJECT_FLOAT);
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testGetNullObjectFloatUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullFloat("noExiste");
    assertEquals(null, o);

    float f = jsonObject.getNullFloat("oFloat");
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testGetNullObjectLongUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    long l = jsonObject.getNullLong(OBJECT_LONG);
    assertEquals(8, l);
  }

  @Test
  public void testGetNullObjectLongUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullLong("noExiste");
    assertEquals(null, o);

    long l = jsonObject.getNullLong("oLong");
    assertEquals(8, l);
  }

  @Test
  public void testGetNullObjectShortUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    short s = jsonObject.getNullShort(OBJECT_SHORT);
    assertEquals(4, s);
  }

  @Test
  public void testGetNullObjectShortUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullShort("noExiste");
    assertEquals(null, o);

    short s = jsonObject.getNullShort("oShort");
    assertEquals(4, s);
  }

  @Test
  public void testGetNullShortUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    short s = jsonObject.getNullShort(SHORT);
    assertEquals(3, s);
  }

  @Test
  public void testGetNullShortUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullShort("noExiste");
    assertEquals(null, o);

    short s = jsonObject.getNullShort("pShort");
    assertEquals(3, s);
  }

  @Test
  public void testGetNullStringUsingThePropertyIndex() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullByte("noExiste");
    assertEquals(null, o);

    String s = jsonObject.getNullString(STRING);
    assertEquals("Esteban", s);
  }

  @Test
  public void testGetNullStringUsingThePropertyName() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    Object o = jsonObject.getNullByte("noExiste");
    assertEquals(null, o);

    String s = jsonObject.getNullString("string");
    assertEquals("Esteban", s);
  }

  @Test
  public void testGetObjectBooleanUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    boolean b = jsonObject.getBoolean(OBJECT_BOOLEAN);
    assertEquals(true, b);
  }

  @Test
  public void testGetObjectByteUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getByte(OBJECT_BYTE);
    assertEquals(2, b);
  }

  @Test
  public void testGetObjectByteUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    byte b = jsonObject.getByte("oByte");
    assertEquals(2, b);
  }

  @Test
  public void testGetObjectDoubleUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    double d = jsonObject.getDouble(OBJECT_DOUBLE);
    assertEquals(12.5, d, 0);
  }

  @Test
  public void testGetObjectDoubleUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    double d = jsonObject.getDouble("oDouble");
    assertEquals(12.5, d, 0);
  }

  @Test
  public void testGetObjectFloatUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    float f = jsonObject.getFloat(OBJECT_FLOAT);
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testGetObjectFloatUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    float f = jsonObject.getFloat("oFloat");
    assertEquals(10.5, f, 0);
  }

  @Test
  public void testGetObjectLongUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    long l = jsonObject.getLong(OBJECT_LONG);
    assertEquals(8, l);
  }

  @Test
  public void testGetObjectLongUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    long l = jsonObject.getLong("oLong");
    assertEquals(8, l);
  }

  @Test
  public void testGetObjectShortUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    short s = jsonObject.getShort(OBJECT_SHORT);
    assertEquals(4, s);
  }

  @Test
  public void testGetObjectShortUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    short s = jsonObject.getShort("oShort");
    assertEquals(4, s);
  }

  @Test
  public void testGetObjectUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONObject jsonBook = jsonObject.getObject(BOOK);
    int id = jsonBook.getInteger("id");
    String name = jsonBook.getString("name");
    assertEquals(1, id);
    assertEquals("Evolution", name);
  }

  @Test
  public void testGetObjectUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONObject jsonBook = jsonObject.getObject("book");
    int id = jsonBook.getInteger("id");
    String name = jsonBook.getString("name");
    assertEquals(1, id);
    assertEquals("Evolution", name);
  }

  @Test
  public void testGetReferencedElement() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONValue jsonTypes = jsonObject.getReferencedElement();
    JSONObject referencedObject = ((JSONValue) jsonTypes.getReferencedElement()).toObject();
    JSONPair referencedPair = referencedObject.getElement("book");
    assertEquals("\"book\": 1", referencedPair.toJSON());
  }

  @Test
  public void testGetShortUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    short s = jsonObject.getShort(SHORT);
    assertEquals(3, s);
  }

  @Test
  public void testGetShortUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    short s = jsonObject.getShort("pShort");
    assertEquals(3, s);
  }

  @Test
  public void testGetStringUsingThePropertyIndex() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    String s = jsonObject.getString(STRING);
    assertEquals("Esteban", s);
  }

  @Test
  public void testGetStringUsingThePropertyName() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    String s = jsonObject.getString("string");
    assertEquals("Esteban", s);
  }

  @Test
  public void testHasChilds() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertTrue(jsonObject.hasChilds());
  }

  @Test
  public void testIsArray() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(false, jsonObject.isArray());
  }

  @Test
  public void testIsBoolean() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(false, jsonObject.isBoolean());
  }

  @Test
  public void testIsNotReferenciable() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(false, jsonObject.isNotReferenciable());
  }

  @Test
  public void testIsNull() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(false, jsonObject.isNull());
  }

  @Test
  public void testIsNumber() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(false, jsonObject.isNumber());
  }

  @Test
  public void testIsObject() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(true, jsonObject.isObject());
  }

  @Test
  public void testIsReferenciable() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(true, jsonObject.isReferenciable());
  }

  @Test
  public void testIsString() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(false, jsonObject.isString());
  }

  @Test
  public void testIsValue() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    assertEquals(true, jsonObject.isValue());
  }

  @Test
  public void testIterator() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    int i = 0;
    for (JSONPair jsonPair : jsonObject) {
      switch (i) {
        case BIG_DECIMAL:
          assertEquals("bigDecimal", jsonPair.getKey());
          assertEquals(new JSONNumber(15.4), jsonPair.getValue());
          break;
        case BIG_INTEGER:
          assertEquals("bigInteger", jsonPair.getKey());
          assertEquals(new JSONNumber(14), jsonPair.getValue());
          break;
        case BYTE_ARRAY:
          assertEquals("byteArray", jsonPair.getKey());
          JSONArray array = (JSONArray) jsonPair.getValue();
          assertEquals(4, array.size());
          assertEquals(new JSONNumber(1), array.getValue(0));
          assertEquals(new JSONNumber(2), array.getValue(1));
          assertEquals(new JSONNumber(3), array.getValue(2));
          assertEquals(new JSONNumber(4), array.getValue(3));
          break;
        case CALENDAR:
          assertEquals("calendar", jsonPair.getKey());
          assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), jsonPair.getValue());
          break;
        case DATE:
          assertEquals("date", jsonPair.getKey());
          assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), jsonPair.getValue());
          break;
        case OBJECT_BOOLEAN:
          assertEquals("oBoolean", jsonPair.getKey());
          assertEquals(JSONBoolean.TRUE, jsonPair.getValue());
          break;
        case OBJECT_BYTE:
          assertEquals("oByte", jsonPair.getKey());
          assertEquals(new JSONNumber(2), jsonPair.getValue());
          break;
        case OBJECT_CHARACTER:
          assertEquals("oCharacter", jsonPair.getKey());
          assertEquals(new JSONString("b"), jsonPair.getValue());
          break;
        case OBJECT_DOUBLE:
          assertEquals("oDouble", jsonPair.getKey());
          assertEquals(new JSONNumber(12.5), jsonPair.getValue());
          break;
        case OBJECT_FLOAT:
          assertEquals("oFloat", jsonPair.getKey());
          assertEquals(new JSONNumber(10.5), jsonPair.getValue());
          break;
        case OBJECT_INTEGER:
          assertEquals("oInteger", jsonPair.getKey());
          assertEquals(new JSONNumber(6), jsonPair.getValue());
          break;
        case OBJECT_LONG:
          assertEquals("oLong", jsonPair.getKey());
          assertEquals(new JSONNumber(8), jsonPair.getValue());
          break;
        case OBJECT_SHORT:
          assertEquals("oShort", jsonPair.getKey());
          assertEquals(new JSONNumber(4), jsonPair.getValue());
          break;
        case NULL_REFERENCE:
          assertEquals("nullReference", jsonPair.getKey());
          assertEquals(JSONNull.getValue(), jsonPair.getValue());
          break;
        case BOOLEAN:
          assertEquals("pBoolean", jsonPair.getKey());
          assertEquals(JSONBoolean.get(false), jsonPair.getValue());
          break;
        case BYTE:
          assertEquals("pByte", jsonPair.getKey());
          assertEquals(new JSONNumber(1), jsonPair.getValue());
          break;
        case CHARACTER:
          assertEquals("pCharacter", jsonPair.getKey());
          assertEquals(new JSONString('a'), jsonPair.getValue());
          break;
        case DOUBLE:
          assertEquals("pDouble", jsonPair.getKey());
          assertEquals(new JSONNumber(11.5), jsonPair.getValue());
          break;
        case FLOAT:
          assertEquals("pFloat", jsonPair.getKey());
          assertEquals(new JSONNumber(9.5), jsonPair.getValue());
          break;
        case INTEGER:
          assertEquals("pInteger", jsonPair.getKey());
          assertEquals(new JSONNumber(5), jsonPair.getValue());
          break;
        case LONG:
          assertEquals("pLong", jsonPair.getKey());
          assertEquals(new JSONNumber(7), jsonPair.getValue());
          break;
        case SHORT:
          assertEquals("pShort", jsonPair.getKey());
          assertEquals(new JSONNumber(3), jsonPair.getValue());
          break;
        case STRING:
          assertEquals("string", jsonPair.getKey());
          assertEquals(new JSONString("Esteban"), jsonPair.getValue());
          break;
        case BOOK:
          assertEquals("book", jsonPair.getKey());
          JSONObject jsonBook = jsonPair.getValue().toObject();
          int id = jsonBook.getInteger("id");
          String name = jsonBook.getString("name");
          assertEquals(1, id);
          assertEquals("Evolution", name);
          break;
        default:
          fail();
          break;
      }
      i++;
    }
    assertEquals(24, i);
  }

  @Test
  public void testToArray() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONValue[] array = jsonObject.toArray();

    assertEquals(24, array.length);

    JSONValue value;

    value = array[BIG_DECIMAL];
    assertEquals(new JSONNumber(15.4), value);

    value = array[BIG_INTEGER];
    assertEquals(new JSONNumber(14), value);

    value = array[BYTE_ARRAY];
    JSONArray a = (JSONArray) value;
    assertEquals(4, a.size());
    assertEquals(new JSONNumber(1), a.getValue(0));
    assertEquals(new JSONNumber(2), a.getValue(1));
    assertEquals(new JSONNumber(3), a.getValue(2));
    assertEquals(new JSONNumber(4), a.getValue(3));

    value = array[CALENDAR];
    assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), value);

    value = array[DATE];
    assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), value);

    value = array[OBJECT_BOOLEAN];
    assertEquals(JSONBoolean.TRUE, value);

    value = array[OBJECT_BYTE];
    assertEquals(new JSONNumber(2), value);

    value = array[OBJECT_CHARACTER];
    assertEquals(new JSONString("b"), value);

    value = array[OBJECT_DOUBLE];
    assertEquals(new JSONNumber(12.5), value);

    value = array[OBJECT_FLOAT];
    assertEquals(new JSONNumber(10.5), value);

    value = array[OBJECT_INTEGER];
    assertEquals(new JSONNumber(6), value);

    value = array[OBJECT_LONG];
    assertEquals(new JSONNumber(8), value);

    value = array[OBJECT_SHORT];
    assertEquals(new JSONNumber(4), value);

    value = array[NULL_REFERENCE];
    assertEquals(JSONNull.getValue(), value);

    value = array[BOOLEAN];
    assertEquals(JSONBoolean.get(false), value
    );

    value = array[BYTE];
    assertEquals(new JSONNumber(1), value);

    value = array[CHARACTER];
    assertEquals(new JSONString('a'), value);

    value = array[DOUBLE];
    assertEquals(new JSONNumber(11.5), value);

    value = array[FLOAT];
    assertEquals(new JSONNumber(9.5), value);

    value = array[INTEGER];
    assertEquals(new JSONNumber(5), value);

    value = array[LONG];
    assertEquals(new JSONNumber(7), value);

    value = array[SHORT];
    assertEquals(new JSONNumber(3), value);

    value = array[STRING];
    assertEquals(new JSONString("Esteban"), value);

    JSONObject jsonBook = array[BOOK].toObject();
    int id = jsonBook.getInteger("id");
    String name = jsonBook.getString("name");
    assertEquals(1, id);
    assertEquals("Evolution", name);
  }

  @Test
  public void testToJSON() throws ParseException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    String json = jsonObject.toJSON();

    System.out.println(json);

    assertEquals("{ \"bigDecimal\": 15.4, \"bigInteger\": 14, \"book\": { \"id\": 1, \"name\": \"Evolution\" }, \"byteArray\": [ 1, 2, 3, 4 ], \"calendar\": \"1974-01-30T14:20:12.125Z\", \"date\": \"1974-01-30T14:20:12.125Z\", \"nullReference\": null, \"oBoolean\": true, \"oByte\": 2, \"oCharacter\": \"b\", \"oDouble\": 12.5, \"oFloat\": 10.5, \"oInteger\": 6, \"oLong\": 8, \"oShort\": 4, \"pBoolean\": false, \"pByte\": 1, \"pCharacter\": \"a\", \"pDouble\": 11.5, \"pFloat\": 9.5, \"pInteger\": 5, \"pLong\": 7, \"pShort\": 3, \"string\": \"Esteban\" }", json);
  }

  @Test
  public void testToJSONArray() throws ParseException, PropertyNotExistException {
    Types types = new Types();
    JSONObject jsonObject = new JSONObject(types);

    JSONArray array = jsonObject.toJSONArray();

    assertEquals(24, array.size());

    JSONValue value;

    value = array.get(BIG_DECIMAL);
    assertEquals(new JSONNumber(15.4), value);

    value = array.get(BIG_INTEGER);
    assertEquals(new JSONNumber(14), value);

    value = array.get(BYTE_ARRAY);
    JSONArray a = (JSONArray) value;
    assertEquals(4, a.size());
    assertEquals(new JSONNumber(1), a.getValue(0));
    assertEquals(new JSONNumber(2), a.getValue(1));
    assertEquals(new JSONNumber(3), a.getValue(2));
    assertEquals(new JSONNumber(4), a.getValue(3));

    value = array.get(CALENDAR);
    assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), value);

    value = array.get(DATE);
    assertEquals(new JSONString("1974-01-30T14:20:12.125Z"), value);

    value = array.get(OBJECT_BOOLEAN);
    assertEquals(JSONBoolean.TRUE, value);

    value = array.get(OBJECT_BYTE);
    assertEquals(new JSONNumber(2), value);

    value = array.get(OBJECT_CHARACTER);
    assertEquals(new JSONString("b"), value);

    value = array.get(OBJECT_DOUBLE);
    assertEquals(new JSONNumber(12.5), value);

    value = array.get(OBJECT_FLOAT);
    assertEquals(new JSONNumber(10.5), value);

    value = array.get(OBJECT_INTEGER);
    assertEquals(new JSONNumber(6), value);

    value = array.get(OBJECT_LONG);
    assertEquals(new JSONNumber(8), value);

    value = array.get(OBJECT_SHORT);
    assertEquals(new JSONNumber(4), value);

    value = array.get(NULL_REFERENCE);
    assertEquals(JSONNull.getValue(), value);

    value = array.get(BOOLEAN);
    assertEquals(JSONBoolean.get(false), value);

    value = array.get(BYTE);
    assertEquals(new JSONNumber(1), value);

    value = array.get(CHARACTER);
    assertEquals(new JSONString('a'), value);

    value = array.get(DOUBLE);
    assertEquals(new JSONNumber(11.5), value);

    value = array.get(FLOAT);
    assertEquals(new JSONNumber(9.5), value);

    value = array.get(INTEGER);
    assertEquals(new JSONNumber(5), value);

    value = array.get(LONG);
    assertEquals(new JSONNumber(7), value);

    value = array.get(SHORT);
    assertEquals(new JSONNumber(3), value);

    value = array.get(STRING);
    assertEquals(new JSONString("Esteban"), value);

    JSONObject jsonBook = array.get(BOOK).toObject();
    int id = jsonBook.getInteger("id");
    String name = jsonBook.getString("name");
    assertEquals(1, id);
    assertEquals("Evolution", name);
  }
}
