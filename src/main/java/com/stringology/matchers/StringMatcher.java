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
  private static final String OPEN_TAG = "<mark>";
  private static final String CLOSE_TAG = "</mark>";

  public StringMatcher(final String pattern) {
    this.pattern = pattern;

  }

  public abstract int getFirstOccurance(final String text);

  public boolean isPatternInText(final String text) {
    return getFirstOccurance(text) != -1;
  }

  public String highlight(final String text) {
    String result = "";
    String temp = text;
    int firstOccuranceIndex;
    do {
      firstOccuranceIndex = getFirstOccurance(temp);
      if (firstOccuranceIndex != -1) {
        result += temp.substring(0, firstOccuranceIndex);
        result += OPEN_TAG + temp.substring(firstOccuranceIndex, firstOccuranceIndex + pattern.length()) + CLOSE_TAG;
        temp = temp.substring(firstOccuranceIndex + pattern.length());
      } else {
        result += temp;
      }
    } while (firstOccuranceIndex != -1);
    return result;

  }

  public static String getOPEN_TAG() {
    return OPEN_TAG;
  }

  public static String getCLOSE_TAG() {
    return CLOSE_TAG;
  }

}
