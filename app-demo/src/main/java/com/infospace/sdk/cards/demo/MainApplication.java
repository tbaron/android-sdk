package com.infospace.sdk.cards.demo;

import android.app.Application;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.*;
import com.infospace.sdk.cards.demo.data.*;
import com.infospace.sdk.cards.demo.events.*;
import com.infospace.sdk.cards.matchers.ResultTypeMatcher;
import com.infospace.sdk.models.parsers.ResultParserFactory;
import com.infospace.sdk.models.parsers.matchers.TypeFieldJsonMatcher;

public class MainApplication
	extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		InspCardsSDK.setup(this);

		register(Container.resolve(CardBinderFactory.class));
		register(Container.resolve(ViewFactory.class));
		register(Container.resolve(ResultParserFactory.class));

		registerEventLog();
	}

	private void register(CardBinderFactory factory) {
		factory
			.push(new ResultTypeMatcher(DemoCategoryResult.class), DemoCategoryCardBinder.class)
			.push(new ResultTypeMatcher(DemoDividerResult.class), DemoDividerViewBinder.class)
			.push(new ResultTypeMatcher(DemoHeaderResult.class), DemoHeaderViewBinder.class)
			.push(new ResultTypeMatcher(DemoCardEvent.class), DemoCardEventViewBinder.class);
	}

	private void register(ViewFactory factory) {
		factory
			.push(new DemoCategoryResultMatcher(DemoCategoryResult.CATEGORY_LIST), R.layout.insp_card_demo_category_list)
			.push(new DemoCategoryResultMatcher(DemoCategoryResult.CATEGORY_GRID), R.layout.insp_card_demo_category_grid)
			.push(new DemoCategoryResultMatcher(DemoCategoryResult.CATEGORY_EVENTLOG), R.layout.insp_card_demo_category_eventlog)
			.push(new ResultTypeMatcher(DemoDividerResult.class), R.layout.insp_card_demo_divider)
			.push(new ResultTypeMatcher(DemoHeaderResult.class), R.layout.insp_card_demo_header)
			.push(new ResultTypeMatcher(DemoCardEvent.class), R.layout.insp_card_demo_event);
	}

	private void register(ResultParserFactory factory) {
		factory
			.push(new TypeFieldJsonMatcher("demoCategory"), DemoCategoryResultParser.class)
			.push(new TypeFieldJsonMatcher("demoDivider"), DemoDividerResultParser.class)
			.push(new TypeFieldJsonMatcher("demoHeader"), DemoHeaderResultParser.class);
	}

	private void registerEventLog() {
		Container.register(new CardEventLog());
	}
}
