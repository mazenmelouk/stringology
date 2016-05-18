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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mazen
 */
class BoyerMooreMatcher extends StringMatcher {

  private final int[] goodSuffixTable;
  private final Map<Character, Integer> badCharTable;

  public BoyerMooreMatcher(final String pattern) {
    super(pattern);
    goodSuffixTable = generateGoodSuffixTable();
    badCharTable = generateBadCharTable();
  }

  private int[] generateGoodSuffixTable() {
    int[] suffixArray = new int[pattern.length()];
    suffixArray[0] = 0;
    for (int i = 1; i < suffixArray.length; i++) {
      suffixArray[i] = getGoodSuffixLen(i);
    }
    return suffixArray;
  }

  private int getGoodSuffixLen(int i) {
    String sub = pattern.substring(i);
    int cmpNumbers = i - 1;
    while (cmpNumbers > 0) {
      int patternIndex = cmpNumbers;
      int subIndex = 0;
      if (pattern.charAt(patternIndex) != sub.charAt(subIndex)) {
        boolean broken = false;
        while (++patternIndex < pattern.length() && ++subIndex < sub.length()) {
          if (pattern.charAt(patternIndex) != sub.charAt(subIndex)) {
            broken = true;
            break;
          }
        }
        if (!broken) {
          return cmpNumbers;
        }
      }
      cmpNumbers--;
    }
    return 0;
  }

  private Map<Character, Integer> generateBadCharTable() {
    Map<Character, Integer> lastIndexOf = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      lastIndexOf.put(pattern.charAt(i), i);
    }
    return lastIndexOf;
  }

  @Override
  public boolean isPatternInText(final String text) {
    for (int i = 0; i <= text.length() - pattern.length();) {
      int j;
      for (j = pattern.length() - 1; j >= 0; j--) {
        for (j = 0; j < pattern.length(); j++) {
          if (text.charAt(i + j) != pattern.charAt(j)) {
            break;
          }
        }
        if (j == pattern.length()) {
          return true;
        }
        int goodScore = j - goodSuffixTable[j];
        int badScore = j - (badCharTable.getOrDefault(text.charAt(i + j), 0));
        i += Math.max(1, Math.max(goodScore, badScore));
      }
    }
    return false;
  }

  @Override
  public int getFirstOccurance(String text) {
    for (int i = 0; i <= text.length() - pattern.length();) {
      int j;
      for (j = pattern.length() - 1; j >= 0; j--) {
        for (j = 0; j < pattern.length(); j++) {
          if (text.charAt(i + j) != pattern.charAt(j)) {
            break;
          }
        }
        if (j == pattern.length()) {
          return i;
        }
        int goodScore = j - goodSuffixTable[j];
        int badScore = j - (badCharTable.getOrDefault(text.charAt(i + j), 0));
        i += Math.max(1, Math.max(goodScore, badScore));
      }
    }
    return -1;
  }

}
