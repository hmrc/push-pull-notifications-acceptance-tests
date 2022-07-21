package steps.apis;

/**
 *
 * See https://www.planetgeek.ch/2012/03/07/create-your-own-matcher
 * for original code and explanation - other examples are available
 */

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

public final class CombinableMatcher<T> extends BaseMatcher<T> {
    private final List<Matcher<? super T>> matchers = new ArrayList<>();
    private final List<Matcher<? super T>> failed = new ArrayList<>();

    private CombinableMatcher(Matcher matcher) {
        matchers.add(matcher);
    }

//    public CombinableMatcher and(Matcher matcher) {
//        matchers.add(matcher);
//        return this;
//    }

    @Override
    public boolean matches(Object item) {
        for (Matcher<? super T> matcher : matchers) {
            if (!matcher.matches(item)) {
                failed.add(matcher);
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("(", " " + "and" + " ", ")", matchers);
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        for (Matcher<? super T> matcher : failed) {
            description.appendDescriptionOf(matcher).appendText(" but ");
            matcher.describeMismatch(item, description);
        }
    }

//    public static <LHS> CombinableMatcher<LHS> all(Matcher<? super LHS> matcher) {
//        return new CombinableMatcher<LHS>(matcher);
//    }
}
