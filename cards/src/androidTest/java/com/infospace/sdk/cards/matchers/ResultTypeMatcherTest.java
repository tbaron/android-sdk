package com.infospace.sdk.cards.matchers;

import android.test.AndroidTestCase;

import com.infospace.sdk.models.ResultNode;

public class ResultTypeMatcherTest extends AndroidTestCase {
	private class MockResult extends ResultNode {
	}

	private class MockSecondResult extends ResultNode {
	}

	private class MockDerivedResult extends MockResult {
	}

	public void test_ctor_throwsIllegalArgumentException_ifTypeIsNull() {
		try {
			new ResultTypeMatcher(null);
			fail("Expected IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}
	}

	public void test_matches_returnsFalse_ifResultNull() {
		ResultTypeMatcher matcher = new ResultTypeMatcher(MockResult.class);

		assertFalse(matcher.matches(null));
	}

	public void test_matches_returnsFalse_ifResultNotOfType() {
		ResultTypeMatcher matcher = new ResultTypeMatcher(MockResult.class);

		assertFalse(matcher.matches(new MockSecondResult()));
	}

	public void test_matches_returnsFalse_ifResultIsParentType() {
		ResultTypeMatcher matcher = new ResultTypeMatcher(MockResult.class);

		assertFalse(matcher.matches(new ResultNode() {
		}));
	}

	public void test_matches_returnsTrue_ifExactType() {
		ResultTypeMatcher matcher = new ResultTypeMatcher(MockResult.class);

		assertTrue(matcher.matches(new MockResult()));
	}

	public void test_matches_returnsTrue_ifDerivedType() {
		ResultTypeMatcher matcher = new ResultTypeMatcher(MockResult.class);

		assertTrue(matcher.matches(new MockDerivedResult()));
	}
}
