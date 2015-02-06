package com.infospace.sdk.cards.demo;

import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.InspCardsSDK;
import com.infospace.sdk.cards.demo.data.DemoCategoryResult;
import com.infospace.sdk.cards.demo.events.*;
import com.infospace.sdk.cards.events.*;
import com.infospace.sdk.models.ResultNode;

public class MainActivity extends Activity
	implements OnCardClickListener, FragmentManager.OnBackStackChangedListener {

	private static final String FRAGMENT_TAG_CATEGORIES = "FRAGMENT_TAG_CATEGORIES";
	private static final String FRAGMENT_TAG_RESULTS = "FRAGMENT_TAG_RESULTS";
	private static final String FRAGMENT_TAG_EVENTLOG = "FRAGMENT_TAG_EVENTLOG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		FragmentManager fragmentManager = getFragmentManager();

		fragmentManager.addOnBackStackChangedListener(this);

		if (savedInstanceState == null) {
			Bundle args = new Bundle();
			args.putString(CardListFragment.ARG_CATEGORY, DemoCategoryResult.CATEGORY_HOME);

			CardListFragment fragment = new CardListFragment();
			fragment.setArguments(args);

			fragmentManager
				.beginTransaction()
				.add(R.id.fragment_container, fragment, FRAGMENT_TAG_CATEGORIES)
				.commit();
		}

		onBackStackChanged();
	}

	@Override
	protected void onStart() {
		super.onStart();

		InspCardsSDK
			.getCardObserver()
			.addOnCardClickListener(this);

		Container
			.resolve(CardEventLog.class)
			.onStart();
	}

	@Override
	protected void onStop() {
		InspCardsSDK
			.getCardObserver()
			.removeOnCardClickListener(this);

		Container
			.resolve(CardEventLog.class)
			.onStop();

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				getFragmentManager().popBackStack();
				return true;
			case R.id.action_github:
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.action_url_github))));
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCardClick(CardClickEvent event) {
		ResultNode result = event.getResult();

		if (result instanceof DemoCategoryResult) {
			DemoCategoryResult category = (DemoCategoryResult) result;

			if (DemoCategoryResult.CATEGORY_EVENTLOG.equals(category.getCategory())) {
				loadEventLog();
			} else {
				loadCategory(category.getCategory());
			}
		}
	}

	private void loadEventLog() {
		FragmentManager fragmentManager = getFragmentManager();
		CardEventFragment fragment = (CardEventFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG_EVENTLOG);

		if (fragment != null) {
			fragmentManager.popBackStack();
		}

		fragment = new CardEventFragment();

		fragmentManager
			.beginTransaction()
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
			.add(R.id.fragment_container, fragment, FRAGMENT_TAG_EVENTLOG)
			.addToBackStack(null)
			.commit();
	}

	private void loadCategory(String category) {
		FragmentManager fragmentManager = getFragmentManager();
		CardListFragment fragment = (CardListFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG_RESULTS);

		if (fragment != null) {
			fragmentManager.popBackStack();
		}

		Bundle args = new Bundle();
		args.putString(CardListFragment.ARG_CATEGORY, category);

		fragment = new CardListFragment();
		fragment.setArguments(args);

		fragmentManager
			.beginTransaction()
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
			.add(R.id.fragment_container, fragment, FRAGMENT_TAG_RESULTS)
			.addToBackStack(null)
			.commit();
	}

	@Override
	public void onBackStackChanged() {
		boolean hasBackStack = getFragmentManager().getBackStackEntryCount() > 0;

		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(hasBackStack);
			actionBar.setHomeButtonEnabled(hasBackStack);
		}
	}
}
