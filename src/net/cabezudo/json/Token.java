package net.cabezudo.json;

import java.math.BigDecimal;
import net.cabezudo.json.exceptions.InvalidTokenException;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/01/2014
 */
class Token {

  private final Position position;

  private StringBuilder sb;
  private TokenType type = TokenType.NONE;

  Token(Position position) {
    this.position = position;
    sb = new StringBuilder();
  }

  @Override
  public String toString() {
    return sb + " (" + type + ", " + position.getLine() + ", " + position.getRow() + ")";
  }

  void append(Character c) {
    sb.append(c);
  }

  void clasify() throws InvalidTokenException {
    String s = sb.toString();

    switch (s.length()) {
      case 0:
        throw new InvalidTokenException("Zero length token.", position);
      case 1:
        switch (s) {
          case "\n":
            type = TokenType.NEWLINE;
            break;
          case " ":
          case "\u00A0":
            type = TokenType.SPACE;
            break;
          case ":":
            type = TokenType.COLON;
            break;
          case ",":
            type = TokenType.COMMA;
            break;
          case "{":
            type = TokenType.LEFT_BRACE;
            break;
          case "}":
            type = TokenType.RIGHT_BRACE;
            break;
          case "[":
            type = TokenType.LEFT_BRACKET;
            break;
          case "]":
            type = TokenType.RIGHT_BRACKET;
            break;
        }
        break;
      case 2:
        switch (s) {
          case "\"\"":
            type = TokenType.STRING;
            break;
        }
        break;
    }
    if (type == TokenType.NONE) {
      switch (s.toLowerCase()) {
        case "true":
          type = TokenType.TRUE;
          break;
        case "false":
          type = TokenType.FALSE;
          break;
        case "null":
          type = TokenType.NULL;
          break;
        default:
          do {
            if (s.startsWith("\"") && s.endsWith("\"")) {
              type = TokenType.STRING;
              break;
            }
            try {
              BigDecimal number = new BigDecimal(s);
              type = TokenType.NUMBER;
              sb = new StringBuilder(number.toString());
              break;
            } catch (NumberFormatException e) {
              throw new InvalidTokenException("Unexpected token " + s + ".", position);
            }
          } while (false);
          break;
      }
    }
  }

  boolean empty() {
    return sb.length() == 0;
  }

  Position getPosition() {
    return position;
  }

  TokenType getType() {
    return type;
  }

  String getValue() {
    return sb.toString();
  }

  boolean isLeftBrace() {
    return type == TokenType.LEFT_BRACE;
  }

  boolean isLeftBracket() {
    return type == TokenType.LEFT_BRACKET;
  }

  boolean isNumber() {
    return type == TokenType.NUMBER;
  }

  boolean isRightBrace() {
    return type == TokenType.RIGHT_BRACE;
  }

  boolean isRightBracket() {
    return type == TokenType.RIGHT_BRACKET;
  }

  int length() {
    return sb.length();
  }

}
