package com.infospace.sdk.cards;

import android.test.AndroidTestCase;

import com.infospace.sdk.cards.matchers.ResultMatcher;
import com.infospace.sdk.models.ResultNode;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ViewFactoryTest extends AndroidTestCase {
	private static final int TEST_LAYOUT_ID = R.layout.insp_card_blank;

	private ViewFactory viewFactory;

	@Override
	public void setUp() throws Exception {
		super.setUp();

		viewFactory = new ViewFactory(getContext());
	}

	public void test_clear_resetsViewTypeCount() throws Exception {
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);

		viewFactory.clear();

		assertEquals(0, viewFactory.getViewTypeCount());
	}

	private static ResultMatcher negativeMatch() {
		ResultMatcher mock = mock(ResultMatcher.class);

		when(mock.matches(any(ResultNode.class))).thenReturn(false);

		return mock;
	}

	public void test_getItemViewType_returnsInvalidViewType_whenEmpty() throws Exception {
		final ResultNode result = mockResult();

		assertEquals(ViewFactory.INVALID_VIEW_TYPE, viewFactory.getItemViewType(result));
	}

	private static ResultNode mockResult() {
		return mock(ResultNode.class);
	}

	public void test_getItemViewType_returnsInvalidViewType_whenNoMatches() throws Exception {
		final ResultNode result = mockResult();

		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);

		assertEquals(ViewFactory.INVALID_VIEW_TYPE, viewFactory.getItemViewType(result));
	}

	public void test_getItemViewType_returnsIndex_whenMatchesFirst() throws Exception {
		viewFactory.push(positiveMatch(), TEST_LAYOUT_ID);
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);

		assertEquals(0, viewFactory.getItemViewType(mockResult()));
	}

	private static ResultMatcher positiveMatch() {
		ResultMatcher mock = mock(ResultMatcher.class);

		when(mock.matches(any(ResultNode.class))).thenReturn(true);

		return mock;
	}

	public void test_getItemViewType_returnsIndex_whenMatchesSecond() throws Exception {
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);
		viewFactory.push(positiveMatch(), TEST_LAYOUT_ID);

		assertEquals(1, viewFactory.getItemViewType(mockResult()));
	}

	public void test_getViewTypeCount_returnsZero_whenEmpty() throws Exception {
		assertEquals(0, viewFactory.getViewTypeCount());
	}

	public void test_getViewTypeCount_returnsRegisteredCount() throws Exception {
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);
		viewFactory.push(negativeMatch(), TEST_LAYOUT_ID);

		assertEquals(3, viewFactory.getViewTypeCount());
	}
}
