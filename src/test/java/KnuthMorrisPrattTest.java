
import com.stringology.matchers.StringMatcher;
import com.stringology.matchers.StringMatcherType;
import com.stringology.matchers.implementations.StringMatcherFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

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
/**
 *
 * @author Mazen
 */
public class KnuthMorrisPrattTest {

  private StringMatcher stringMatcher;

  @Before
  public void initalize() {
    stringMatcher = StringMatcherFactory.createStringMatcher(StringMatcherType.KnuthMorrisPratt, TestConstants.PATTERN);
  }

  @Test
  public void exactPatternInText() {
    assertEquals(stringMatcher.isPatternInText(TestConstants.PATTERN), true);
  }

  @Test
  public void matchPatternInText() {
    assertEquals(stringMatcher.isPatternInText(TestConstants.MATCHING_TEXT), true);
  }

  @Test
  public void mismatchPatternInText() {
    assertEquals(stringMatcher.isPatternInText(TestConstants.MISMATCHING_TEXT), false);
  }

  @Test
  public void exactFirstOccurance() {
    assertEquals(stringMatcher.getFirstOccurance(TestConstants.PATTERN), 0);
  }

  @Test
  public void matchFirstOccurance() {
    assertEquals(stringMatcher.getFirstOccurance(TestConstants.MATCHING_TEXT), TestConstants.MATCHING_TEXT.indexOf(TestConstants.PATTERN));
  }

  @Test
  public void mismatchFirstOccurance() {
    assertEquals(stringMatcher.getFirstOccurance(TestConstants.MISMATCHING_TEXT), -1);
  }

  @Test
  public void exactHighlight() {
    assertEquals(stringMatcher.highlight(TestConstants.PATTERN), StringMatcher.getOPEN_TAG() + TestConstants.PATTERN + StringMatcher.getCLOSE_TAG());
  }

  @Test
  public void matchHighlight() {
    assertEquals(stringMatcher.highlight(TestConstants.MATCHING_TEXT), TestConstants.MATCHING_TEXT.replaceFirst(TestConstants.PATTERN,
        StringMatcher.getOPEN_TAG() + TestConstants.PATTERN + StringMatcher.getCLOSE_TAG()));
  }

  @Test
  public void mismatchHighlight() {
    assertEquals(stringMatcher.highlight(TestConstants.MISMATCHING_TEXT), TestConstants.MISMATCHING_TEXT);
  }
}
