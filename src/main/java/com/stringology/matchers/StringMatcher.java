/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.stringology.matchers;

/**
 *
 * @author Mazen
 */
public abstract class StringMatcher {

  protected final String pattern;

  public StringMatcher(final String pattern) {
    this.pattern = pattern;

  }

  public abstract boolean isPatternInText(final String text);

}
