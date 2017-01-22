package net.cabezudo.json;

import net.cabezudo.json.JSON;
import net.cabezudo.json.Formatter;
import net.cabezudo.json.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import net.cabezudo.json.objects.Data;
import net.cabezudo.json.values.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 08/03/2016
 */
public class FormatterIT {

  private void createFile(String filename, String text) {
    URL url = this.getClass().getResource("");
    String fullFilename = url.getFile() + filename;
    File file = new File(fullFilename);
    Log.debug("Create file %s.%n", file.getAbsolutePath());
    try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
      writer.println(text);
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testIndentTest() {
    Log.debug("Ident a unformated JSON string.");

    String expectedString = "{\n"
        + "   \"version\": {\n"
        + "      \"id\": 1,\n"
        + "      \"minor\": 0,\n"
        + "      \"version\": 1\n"
        + "   },\n"
        + "   \"countryId\": 1,\n"
        + "   \"countryName\": {\n"
        + "      \"version\": {\n"
        + "         \"id\": 1,\n"
        + "         \"minor\": 0,\n"
        + "         \"version\": 1\n"
        + "      },\n"
        + "      \"language\": {\n"
        + "         \"version\": {\n"
        + "            \"id\": 1,\n"
        + "            \"minor\": 0,\n"
        + "            \"version\": 1\n"
        + "         },\n"
        + "         \"charset\": \"utf8\",\n"
        + "         \"collation\": \"utf8_general_ci\",\n"
        + "         \"id\": 97,\n"
        + "         \"letterCode\": \"ps\"\n"
        + "      },\n"
        + "      \"nameType\": {\n"
        + "         \"index\": 1\n"
        + "      },\n"
        + "      \"word\": {\n"
        + "         \"version\": {\n"
        + "            \"id\": 1,\n"
        + "            \"minor\": 0,\n"
        + "            \"version\": 1\n"
        + "         },\n"
        + "         \"id\": 2,\n"
        + "         \"language\": {\n"
        + "            \"version\": {\n"
        + "               \"id\": 1,\n"
        + "               \"minor\": 0,\n"
        + "               \"version\": 1\n"
        + "            },\n"
        + "            \"charset\": \"utf8\",\n"
        + "            \"collation\": \"utf8_general_ci\",\n"
        + "            \"id\": 97,\n"
        + "            \"letterCode\": \"ps\"\n"
        + "         },\n"
        + "         \"string\": \"افغانستان\"\n"
        + "      }\n"
        + "   }\n"
        + "}\n";

    Data data = new Data();
    JSONObject jsonTree = JSON.toJSONTree(data).toObject();
    String jsonString = jsonTree.toJSON();

    String indentString = Formatter.indent(jsonString);

    createFile("jsonString.txt", jsonString);
    createFile("expectedString.txt", expectedString);
    createFile("indentString.txt", indentString);

    Log.debug("jsonString: %s.%n", jsonString);
    Log.debug("expectedString: %s.%n", expectedString);
    Log.debug("indentString: %s.%n", indentString);

    assertEquals(expectedString, indentString);
  }
}
