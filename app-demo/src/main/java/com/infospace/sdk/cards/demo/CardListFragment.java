package com.infospace.sdk.cards.demo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.infospace.sdk.cards.CardAdapter;
import com.infospace.sdk.cards.demo.data.*;
import com.infospace.sdk.models.ResultNode;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.List;

public class CardListFragment extends Fragment {
	public static final String ARG_CATEGORY = "ARG_CATEGORY";

	private List<ResultNode> results;
	private BaseAdapter adapter;
	private AbsListView listView;
	private String category;
	private int resourceId;

	public void setCategory(String category) {
		int resourceId = getCategoryResourceId(category);
		if (resourceId != 0 && resourceId == this.resourceId) {
			return;
		}

		this.resourceId = resourceId;

		loadResults();
	}

	private int getCategoryResourceId(String category) {
		this.category = category;

		if (DemoCategoryResult.CATEGORY_HOME.equalsIgnoreCase(category)) {
			return R.raw.category_cards;
		}
		if (DemoCategoryResult.CATEGORY_LIST.equalsIgnoreCase(category)) {
			return R.raw.list_result_cards;
		}
		if (DemoCategoryResult.CATEGORY_GRID.equalsIgnoreCase(category)) {
			return R.raw.grid_result_cards;
		}

		return 0;
	}

	private void loadResults() {
		results = new DemoResultProvider(getActivity()).getResults(resourceId);
		adapter = new CardAdapter(results);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();

		if (args != null) {
			setCategory(args.getString(ARG_CATEGORY, null));
		} else {
			setCategory(null);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getFragmentLayout(), container, false);

		if (container instanceof LinearLayout) {
			view.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, getLayoutWeight()));
		} else {
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		}

		listView = (AbsListView) view.findViewById(android.R.id.list);

		addAdapterToListView(adapter, listView);

		return view;
	}

	private float getLayoutWeight() {
		if (DemoCategoryResult.CATEGORY_HOME.equalsIgnoreCase(category)) {
			return 1;
		}

		return 2;
	}

	private int getFragmentLayout() {
		if (DemoCategoryResult.CATEGORY_GRID.equalsIgnoreCase(category)) {
			return R.layout.fragment_card_grid;
		}

		return R.layout.fragment_card_list;
	}

	private void addAdapterToListView(BaseAdapter adapter, AbsListView listView) {
		AnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(adapter);

		animationAdapter.setAbsListView(listView);
		listView.setAdapter(animationAdapter);
	}
}
