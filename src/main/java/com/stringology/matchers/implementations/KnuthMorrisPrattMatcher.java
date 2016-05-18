/*
 * 
 * Copyright 2015 Mazen Melouk Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License
 * at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 */
package com.stringology.matchers.implementations;

import com.stringology.matchers.StringMatcher;

/**
 *
 * @author Mazen
 */
class KnuthMorrisPrattMatcher extends StringMatcher {

  private final int[] skipTable;

  public KnuthMorrisPrattMatcher(final String pattern) {
    super(pattern);
    skipTable = generateSkipTable();
  }

  private int[] generateSkipTable() {
    int[] skips = new int[pattern.length()];
    for (int i = 0; i < pattern.length(); i++) {
      skips[i] = getMaxLen(pattern.substring(0, i + 1));
    }
    return skips;
  }

  private static int getMaxLen(final String substring) {
    if (substring.length() <= 1) {
      return 0;
    }
    int i = 0;
    String suffix = "", prefix = "";
    do {
      int end = substring.length() - 1 - i;

      if (end < 0) {
        break;
      }
      prefix = substring.substring(0, end);
      int start = 1 + i;
      if (start > substring.length()) {
        break;
      }
      suffix = substring.substring(start);
      i++;
    } while (!suffix.equals(prefix));
    return suffix.length();
  }

  @Override
  public int getFirstOccurance(String text) {
    for (int i = 0; i <= text.length() - pattern.length();) {
      int j;
      for (j = 0; j < pattern.length(); j++) {
        if (text.charAt(i + j) != pattern.charAt(j)) {
          break;
        }
      }
      if (j == pattern.length()) {
        return i;
      }
      int skip = skipTable[j - 1 > 0 ? j - 1 : 0];
      skip = skip == 0 ? 1 : skip;
      i += skip;
    }
    return -1;
  }

}
