package net.cabezudo.java.json;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 07/15/2016
 */
class Tokenizer {

  static Tokens tokenize(String string) {
    char[] chars = string.toCharArray();
    Tokens tokens = new Tokens();
    Token token = new Token(0);
    boolean isString = false;

    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (isString) {
        if (c == '"') {
          token.append(c);
          isString = false;
          tokens.add(token);
          token = new Token(i + 1);
        } else {
          token.append(c);
        }
      } else {
        switch (c) {
          case '"':
            token.append(c);
            isString = true;
            break;
          case '\n':
          case ' ':
          case ':':
          case ',':
          case '{':
          case '}':
          case '[':
          case ']':
            tokens.add(token);
            token = new Token(i);
            token.append(c);
            tokens.add(token);
            token = new Token(i + 1);
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
