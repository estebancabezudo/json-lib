package net.cabezudo.java.json;

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
   *
   * @param message
   * @param object
   */
  public static void debug(String message, Object... object) {
//    LOG.log(Level.FINEST, String.format(message, object));
  }
}
