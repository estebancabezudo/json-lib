package net.cabezudo.java.json;

import java.util.LinkedList;
import java.util.Queue;
import net.cabezudo.java.json.exceptions.EmptyQueueException;
import net.cabezudo.java.json.exceptions.InvalidTokenException;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 10/02/2014
 */
class Tokens {

  private Position position;
  private final Queue<Token> queue = new LinkedList<>();

  boolean add(Token token) throws InvalidTokenException {
    if (token == null || token.empty()) {
      return false;
    }
    token.clasify();

    TokenType tokenType = token.getType();
    switch (tokenType) {
      case SPACE:
      case NEWLINE:
        return false;
    }

    if (queue.isEmpty()) {
      position = token.getPosition();
    }

    return queue.offer(token);
  }

  Token element() {
    return queue.element();
  }

  Position getPosition() {
    return position;
  }

  boolean hasNext() {
    return queue.size() > 0;
  }

  Token poll() throws EmptyQueueException {
    Token token = queue.poll();
    if (token == null) {
      throw new EmptyQueueException();
    }
    return token;
  }
}
