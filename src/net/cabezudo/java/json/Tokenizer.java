package net.cabezudo.java.json;

import net.cabezudo.java.json.exceptions.InvalidTokenException;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 07/15/2016
 */
class Tokenizer {

  static Tokens tokenize(String string) throws InvalidTokenException {
    char[] chars = string.toCharArray();
    Tokens tokens = new Tokens();
    Token token = new Token(Position.INITIAL);
    boolean isString = false;

    int line = token.getPosition().getLine();
    int row = token.getPosition().getRow();
    for (int i = 0; i < chars.length; i++, row++) {
      char c = chars[i];
      if (isString) {
        do {
          if (c == '\\') {
            token.append(c);
            i++;
            if (i < chars.length) {
              c = chars[i];
              token.append(c);
            }
            break;
          }
          if (c == '"') {
            token.append(c);
            isString = false;
            tokens.add(token);
            token = new Token(new Position(line, row + 1));
          } else {
            token.append(c);
          }
        } while (false);
      } else {
        switch (c) {
          case '"':
            token.append(c);
            isString = true;
            break;
          case '\n':
            line++;
            row = 1;
          case ' ':
          case '\u00A0':
          case ':':
          case ',':
          case '{':
          case '}':
          case '[':
          case ']':
            tokens.add(token);
            token = new Token(new Position(line, row));
            token.append(c);
            tokens.add(token);
            token = new Token(new Position(line, row + 1));
            break;
          default:
            token.append(c);
            break;
        }
      }
    }
    tokens.add(token);
    return tokens;
  }

  private Tokenizer() {
  }
}
