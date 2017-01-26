package net.cabezudo.json;

import java.util.logging.Logger;

/**
 * @author <a href="http://cabezudo.net">Esteban Cabezudo</a>
 * @version 1.0
 * @since 1.7
 * @date 08/09/2016
 */
public class Log {

  private static final Logger LOG = Logger.getLogger(Log.class.getName());

  /**
   * Print on console a message.
   *
   * @param message the message to print in console
   *
   * @param objects an optional list of objects to print in console using the syntax of the
   * {@link java.lang.String#format} method.
   */
  public static void debug(String message, Object... objects) {
    LOG.info(String.format(message, objects));
  }

  private Log() {
    // Only to avoid the instantiation.
  }
}
