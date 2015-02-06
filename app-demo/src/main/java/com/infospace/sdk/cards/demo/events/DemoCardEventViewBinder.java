package com.infospace.sdk.cards.demo.events;

import android.text.Html;
import android.view.*;
import android.widget.TextView;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.*;
import com.infospace.sdk.models.ResultNode;

import java.util.*;

public class DemoCardEventViewBinder extends ViewBinder<DemoCardEvent> {
	@Override
	protected void onBind() {
		setupTitle(getModel().getType());
		setupDate(getModel().getDate());
		setupResult(getModel().getResult());
	}

	private void setupTitle(String title) {
		TextView textView = findTextView(com.infospace.sdk.cards.R.id.insp_card_title);

		if (textView != null) {
			if (title == null) {
				title = "";
			}

			textView.setText(Html.fromHtml(title));
		}
	}

	private void setupDate(Date date) {
		TextView view = findTextView(com.infospace.sdk.cards.R.id.insp_card_date);

		if (view != null) {
			String str = date == null
				? ""
				: date.toString();

			view.setText(String.format(Locale.getDefault(), str, date));
		}
	}

	private void setupResult(ResultNode result) {
		ViewGroup container = findViewGroup(com.infospace.sdk.cards.R.id.insp_card_results);

		if (container == null) {
			return;
		}

		View convertView = container.getChildAt(0);

		View view = Container
			.resolve(ViewFactory.class)
			.getView(result, null, container);

		if (view != convertView) {
			container.removeView(convertView);
			container.addView(view);
		}

		Container
			.resolve(CardBinderFactory.class)
			.bind(result, view);
	}
}
