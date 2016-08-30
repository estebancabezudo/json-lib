package net.cabezudo.java.json;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/02/2014
 */
enum TokenType {

  NONE("none"),
  LEFT_BRACE("left brace"),
  RIGHT_BRACE("right brace"),
  COLON("colon"),
  COMMA("comma"),
  LEFT_BRACKET("left bracket"),
  RIGHT_BRACKET("right bracket"),
  SPACE("space"),
  NEWLINE("newline"),
  STRING("string"),
  TRUE("true"),
  FALSE("false"),
  NULL("null"),
  NUMBER("number");

  private final String name;

  private TokenType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
