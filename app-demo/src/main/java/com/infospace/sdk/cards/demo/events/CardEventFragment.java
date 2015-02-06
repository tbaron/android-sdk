package com.infospace.sdk.cards.demo.events;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.CardAdapter;
import com.infospace.sdk.cards.demo.R;
import com.infospace.sdk.cards.demo.data.DemoHeaderResult;
import com.infospace.sdk.models.ResultNode;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.*;

public class CardEventFragment
	extends Fragment
	implements OnNewEventListener {

	private CardEventLog eventLog;
	private List<ResultNode> results;
	private CardAdapter adapter;
	private AbsListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		eventLog = Container.resolve(CardEventLog.class);
		eventLog.addOnNewEventListener(this);
		eventLog.onStop();

		results = new ArrayList<ResultNode>();

		setupResultList();

		adapter = new CardAdapter(results);
	}

	private void setupResultList() {
		DemoHeaderResult header = new DemoHeaderResult();
		header.setTitle("Card Event Log");
		header.setDescription("This is a log of all card events that have occurred in the app. It demonstrates some of the interactions that can be observed.");

		results.clear();
		results.add(header);

		results.addAll(eventLog.getEvents());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_card_list, container, false);

		if (container instanceof LinearLayout) {
			view.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
		} else {
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		}

		listView = (AbsListView) view.findViewById(android.R.id.list);

		addAdapterToListView(adapter, listView);

		return view;
	}

	private void addAdapterToListView(BaseAdapter adapter, AbsListView listView) {
		AnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(adapter);

		animationAdapter.setAbsListView(listView);
		listView.setAdapter(animationAdapter);
	}

	@Override
	public void onDestroy() {
		eventLog.removeOnNewEventListener(this);
		eventLog.onStart();
		eventLog = null;

		super.onDestroy();
	}

	@Override
	public void onNewEvent(CardEventLog eventLog) {
		setupResultList();
		adapter.notifyDataSetChanged();
	}
}
