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
class NaiveMatcher extends StringMatcher {

  public NaiveMatcher(final String pattern) {
    super(pattern);
  }

  @Override
  public int getFirstOccurance(String text) {
    for (int i = 0; i <= text.length() - pattern.length(); i++) {
      int j;
      for (j = 0; j < pattern.length(); j++) {
        if (text.charAt(i + j) != pattern.charAt(j)) {
          break;
        }
      }
      if (j == pattern.length()) {
        return (i + j) - pattern.length();
      }

    }
    return -1;
  }

}
