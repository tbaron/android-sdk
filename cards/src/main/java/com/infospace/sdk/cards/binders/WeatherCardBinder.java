package com.infospace.sdk.cards.binders;

import android.net.Uri;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import com.infospace.sdk.cards.*;
import com.infospace.sdk.models.*;

import java.util.Locale;

public class WeatherCardBinder extends StandardCardBinder<WeatherResult> {
	@Override
	protected void onBind() {
		super.onBind();

		WeatherResult result = getModel();

		setupTemperature(result.getTemperature());
		setupWind(result.getWindDirection(), result.getWindSpeed());
		setupWindChill(result.getWindChill());
		setupImage(result.getConditionId());
		setupForecast(result.getForecast());
	}

	private void setupTemperature(double temperature) {
		TextView view = findTextView(R.id.insp_card_temperature);

		if (view != null) {
			String format = view.getContext().getResources().getString(R.string.insp_card_weather_temperature_format);

			view.setText(String.format(Locale.getDefault(), format, temperature));
		}
	}

	private void setupWind(String direction, double windSpeed) {
		TextView view = findTextView(R.id.insp_card_wind);

		if (view != null) {
			String format = view.getContext().getResources().getString(R.string.insp_card_weather_wind_format);

			view.setText(String.format(Locale.getDefault(), format, direction, windSpeed));
		}
	}

	private void setupWindChill(int windChill) {
		TextView view = findTextView(R.id.insp_card_wind_chill);

		if (view != null) {
			String format = view.getContext().getResources().getString(R.string.insp_card_weather_wind_chill_format);

			view.setText(String.format(Locale.getDefault(), format, windChill));
		}
	}

	private void setupImage(String conditionId) {
		ImageView view = findImageView(R.id.insp_card_image);

		if (view == null) {
			return;
		}

		if (TextUtils.isEmpty(conditionId)) {
			view.setVisibility(View.GONE);
			return;
		}

		Uri uri = new ResourceUriBuilder(view.getResources())
			.setCategory("weather")
			.setName("lg-" + conditionId)
			.build();

		view.setVisibility(View.VISIBLE);

		getPicasso()
			.load(uri)
			.fit()
			.centerInside()
			.into(view);
	}

	private void setupForecast(WeatherForecastResult[] forecast) {
		ViewGroup container = findViewGroup(R.id.insp_card_forecasts);

		if (container == null) {
			return;
		}

		container.removeAllViews();

		if (forecast == null || forecast.length == 0) {
			container.setVisibility(View.GONE);
			return;
		}

		container.setVisibility(View.VISIBLE);

		for (WeatherForecastResult result : forecast) {
			container.addView(getForecastView(result, container));
		}
	}

	private View getForecastView(ResultNode result, ViewGroup container) {
		// TODO: can we reuse existing child views?
		View view = getViewFactory().getView(result, null, container);

		getBinderFactory().bind(result, view);

		return view;
	}
}
