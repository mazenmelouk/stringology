/*
 *  
 *  Copyright 2015 Mazen Melouk
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and  limitations under the License.
  * 
 */
package com.stringology.matchers.implementations;

import com.stringology.matchers.StringMatcher;

/**
 *
 * @author Mazen
 */
class RobinKarpMatcher extends StringMatcher {

  private final int patternHashCode;

  public RobinKarpMatcher(final String pattern) {
    super(pattern);
    patternHashCode = pattern.hashCode();
  }

  @Override
  public boolean isPatternInText(final String text) {
    String textSubString;
    for (int i = 0; i <= text.length() - pattern.length(); i++) {
      textSubString = text.substring(i, i + pattern.length());
      if (patternHashCode == textSubString.hashCode()) {
        return true;
      }
    }
    return false;
  }

}
