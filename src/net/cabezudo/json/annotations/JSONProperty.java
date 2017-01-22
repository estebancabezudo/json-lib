package net.cabezudo.json.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Esteban Cabezudo
 * @version 1.0
 * @since 1.7
 * @date 07/06/2016
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONProperty {

  /**
   *
   */
  public static final String DEFAULT_NAME = "The same as the name of the property.";

  /**
   *
   */
  public static final String DEFAULT_REFERENCED_PROPERTY = "id";

  /**
   *
   * @return
   */
  String name() default DEFAULT_NAME;

  /**
   *
   * @return
   */
  boolean dontShowIfZero() default false;

  /**
   *
   * @return
   */
  boolean dontShowIfNull() default false;

  /**
   *
   * @return
   */
  String field() default DEFAULT_REFERENCED_PROPERTY;

  /**
   *
   * @return
   */
  boolean referenced() default true;
}
