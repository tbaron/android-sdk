package com.infospace.sdk.cards.binders;

import android.net.Uri;
import android.util.Pair;
import android.widget.*;

import com.infospace.sdk.cards.*;
import com.infospace.sdk.models.HoroscopeResult;

import java.util.*;

public class HoroscopeCardBinder
	extends StandardCardBinder<HoroscopeResult> {

	private static final Map<String, Pair<Date, Date>> signDates = createDateRanges();

	@Override
	protected void onBind() {
		super.onBind();

		String sign = getModel().getPlainTextTitle().toLowerCase(Locale.US);

		setupImage(sign);
		setupDateRange(sign);
		setupDate();
	}

	private void setupDateRange(String sign) {
		TextView view = findTextView(R.id.insp_card_date_range);

		if (view == null) {
			return;
		}

		Pair<Date, Date> dateRange = signDates.get(sign);

		if (dateRange == null) {
			return;
		}

		String format = view.getResources().getString(R.string.insp_card_horoscope_date_range_format);

		view.setText(String.format(Locale.getDefault(), format, dateRange.first, dateRange.second));
	}

	private void setupDate() {
		TextView view = findTextView(R.id.insp_card_date);

		if (view != null) {
			String format = view.getResources().getString(R.string.insp_card_horoscope_date_format);

			view.setText(String.format(Locale.getDefault(), format, new Date()));
		}
	}

	private void setupImage(String sign) {
		ImageView view = findImageView(R.id.insp_card_image);

		if (view == null) {
			return;
		}

		Uri uri = new ResourceUriBuilder(view.getResources())
			.setCategory("horoscope")
			.setName(sign.toLowerCase())
			.build();

		getPicasso()
			.load(uri)
			.fit()
			.centerInside()
			.into(view);
	}

	private static Map<String, Pair<Date, Date>> createDateRanges() {
		Map<String, Pair<Date, Date>> dates = new HashMap<String, Pair<Date, Date>>();

		dates.put("aries", dateRange(3, 21, 4, 19));
		dates.put("taurus", dateRange(4, 20, 5, 20));
		dates.put("gemini", dateRange(5, 21, 6, 20));
		dates.put("cancer", dateRange(6, 21, 7, 22));
		dates.put("leo", dateRange(7, 23, 8, 22));
		dates.put("virgo", dateRange(8, 23, 9, 22));
		dates.put("libra", dateRange(9, 23, 10, 22));
		dates.put("scorpio", dateRange(10, 23, 11, 21));
		dates.put("sagittarius", dateRange(11, 22, 12, 21));
		dates.put("capricorn", dateRange(12, 22, 1, 19));
		dates.put("aquarius", dateRange(1, 20, 2, 18));
		dates.put("pisces", dateRange(2, 19, 3, 20));

		return dates;
	}

	private static Pair<Date, Date> dateRange(int startMonth, int startDay, int endMonth, int endDay) {
		return Pair.create(
			new GregorianCalendar(1970, startMonth, startDay).getTime(),
			new GregorianCalendar(1970, endMonth, endDay).getTime());
	}
}
