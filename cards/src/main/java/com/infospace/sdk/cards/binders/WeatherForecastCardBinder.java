package com.infospace.sdk.cards.binders;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.infospace.sdk.cards.*;
import com.infospace.sdk.models.WeatherForecastResult;

import java.util.*;

public class WeatherForecastCardBinder extends CardBinder<WeatherForecastResult> {
	@Override
	protected void onBind() {
		super.onBind();

		WeatherForecastResult result = getModel();

		setupTitle(result.getDate());
		setupDate(result.getDate());
		setupTemperature(result.getTemperatureMin(), result.getTemperatureMax());
		setupImage(result.getConditionId());
	}

	private void setupTitle(Date date) {
		TextView view = findTextView(R.id.insp_card_title);

		if (view != null) {
			String format = view.getContext().getResources().getString(R.string.insp_card_weather_forecast_title_format);

			view.setText(String.format(Locale.getDefault(), format, date));
		}
	}

	private void setupDate(Date date) {
		TextView view = findTextView(R.id.insp_card_date);

		if (view != null) {
			String format = view.getContext().getResources().getString(R.string.insp_card_weather_forecast_date_format);

			view.setText(String.format(Locale.getDefault(), format, date));
		}
	}

	private void setupTemperature(double min, double max) {
		TextView view = findTextView(R.id.insp_card_temperature);

		if (view != null) {
			String format = view.getContext().getResources().getString(R.string.insp_card_weather_forecast_temperature_format);

			view.setText(String.format(Locale.getDefault(), format, min, max));
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
			.setName("sm-" + conditionId)
			.build();

		view.setVisibility(View.VISIBLE);

		getPicasso()
			.load(uri)
			.fit()
			.centerInside()
			.into(view);
	}
}
