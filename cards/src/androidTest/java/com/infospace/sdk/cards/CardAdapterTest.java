package com.infospace.sdk.cards;

import android.test.AndroidTestCase;
import android.view.*;
import android.widget.FrameLayout;

import com.infospace.sdk.Container;
import com.infospace.sdk.models.ResultNode;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CardAdapterTest extends AndroidTestCase {

	private CardBinderFactory cardBinderFactory;
	private ViewFactory viewFactory;

	@Override
	public void setUp() throws Exception {
		super.setUp();

		cardBinderFactory = mock(CardBinderFactory.class);
		viewFactory = mock(ViewFactory.class);

		Container.register(cardBinderFactory);
		Container.register(viewFactory);
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		Container.remove(CardBinderFactory.class);
		Container.remove(ViewFactory.class);
	}

	public void test_getSize_returnsItemsSize() throws Exception {
		CardAdapter adapter = createCardAdapter(
			mockResult(),
			mockResult(),
			mockResult()
		);

		assertEquals(3, adapter.getCount());
	}

	private static CardAdapter createCardAdapter(ResultNode... items) {
		return new CardAdapter(Arrays.asList(items));
	}

	private static ResultNode mockResult() {
		return mock(ResultNode.class);
	}

	public void test_getItem_returnsItem_atPosition() throws Exception {
		final ResultNode[] items = {
			mockResult(),
			mockResult(),
			mockResult()
		};

		CardAdapter adapter = createCardAdapter(items);

		assertSame(items[0], adapter.getItem(0));
		assertSame(items[1], adapter.getItem(1));
		assertSame(items[2], adapter.getItem(2));
	}

	public void test_getItem_throwsIndexOutOfBounds_ifPositionNegative() throws Exception {
		CardAdapter adapter = createCardAdapter();

		try {
			adapter.getItem(-1);

			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void test_getItem_throwsIndexOutOfBounds_ifPositionAboveCount() throws Exception {
		CardAdapter adapter = createCardAdapter();

		try {
			adapter.getItem(0);

			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void test_getItemId_returnsPosition_ifInRange() throws Exception {
		CardAdapter adapter = createCardAdapter(
			mockResult(),
			mockResult()
		);

		assertEquals(0, adapter.getItemId(0));
		assertEquals(1, adapter.getItemId(1));
	}

	public void test_getItemId_returnsPosition_ifNegative() throws Exception {
		CardAdapter adapter = createCardAdapter();

		assertEquals(-1, adapter.getItemId(-1));
	}

	public void test_getItemId_returnsPosition_ifAboveCount() throws Exception {
		CardAdapter adapter = createCardAdapter();

		assertEquals(1, adapter.getItemId(1));
	}

	public void test_getItemViewType_proxiesToViewFactory() throws Exception {
		final ResultNode result = mockResult();
		final int viewType = 12345;

		when(viewFactory.getItemViewType(result))
			.thenReturn(12345);

		CardAdapter adapter = createCardAdapter(
			result
		);

		assertEquals(viewType, adapter.getItemViewType(0));
		verify(viewFactory).getItemViewType(result);
	}

	public void test_getViewTypeCount_proxiesToViewFactory() throws Exception {
		final int expectedCount = 12345;

		when(viewFactory.getViewTypeCount())
			.thenReturn(expectedCount);

		CardAdapter adapter = createCardAdapter();

		assertEquals(expectedCount, adapter.getViewTypeCount());
		verify(viewFactory).getViewTypeCount();
	}

	public void test_getView_passesResult_ToViewFactory() throws Exception {
		final ResultNode result = mockResult();

		CardAdapter adapter = createCardAdapter(
			result
		);

		adapter.getView(0, null, null);

		verify(viewFactory).getView(eq(result), any(View.class), any(ViewGroup.class));
	}

	public void test_getView_passesConvertView_ToViewFactory() throws Exception {
		final ResultNode result = mockResult();
		final View convertView = mockView();

		CardAdapter adapter = createCardAdapter(
			result
		);

		adapter.getView(0, convertView, null);

		verify(viewFactory).getView(any(ResultNode.class), eq(convertView), any(ViewGroup.class));
	}

	private ViewGroup mockView() {
		// Using FrameLayout is faster than mock(ViewGroup.class)
		return new FrameLayout(getContext());
	}

	public void test_getView_passesParentView_ToViewFactory() throws Exception {
		final ResultNode result = mockResult();
		final ViewGroup parent = mockView();

		CardAdapter adapter = createCardAdapter(
			result
		);

		adapter.getView(0, null, parent);

		verify(viewFactory).getView(any(ResultNode.class), any(View.class), eq(parent));
	}

	public void test_getView_getsViewAndBindsResult() throws Exception {
		final ResultNode result = mockResult();
		final View convertView = mockView();
		final ViewGroup parentView = mockView();
		final View resultView = mockView();

		when(viewFactory.getView(result, convertView, parentView))
			.thenReturn(resultView);

		CardAdapter adapter = createCardAdapter(
			result
		);

		assertSame(resultView, adapter.getView(0, convertView, parentView));
		verify(viewFactory).getView(result, convertView, parentView);
		verify(cardBinderFactory).bind(result, resultView);
	}
}