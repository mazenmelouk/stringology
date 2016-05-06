
import com.stringology.matchers.StringMatcher;
import com.stringology.matchers.StringMatcherType;
import com.stringology.matchers.implementations.StringMatcherFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
/**
 *
 * @author Mazen
 */
public class RobinKarpTest {

  private StringMatcher stringMatcher;

    @Before
  public void initalize() {
    stringMatcher = StringMatcherFactory.createStringMatcher(StringMatcherType.RobinKarp, TestConstants.PATTERN);
  }

  @Test
  public void exact() {
    assertEquals(stringMatcher.isPatternInText(TestConstants.PATTERN), true);
  }

  @Test
  public void match() {
    assertEquals(stringMatcher.isPatternInText(TestConstants.MATCHING_TEXT), true);
  }

  @Test
  public void mismatch() {
    assertEquals(stringMatcher.isPatternInText(TestConstants.MISMATCHING_TEXT), false);
  }
}