package com.infospace.sdk.cards.binders;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.*;

import java.util.Locale;

public class LocalAdCardBinder
	extends StandardAdCardBinder<LocalAdResult> {

	@Override
	protected void onBind() {
		super.onBind();

		setupMap();
		setupAddress(getModel().getAddress());
		setupDistance(getModel().getDistance());
	}

	private void setupMap() {
		final ImageView view = findImageView(R.id.insp_card_map);

		if (view != null) {
			view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					view.getViewTreeObserver().removeOnPreDrawListener(this);

					setupMapView(view);

					return true;
				}
			});

			final Intent intent = createIntent();

			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					v.getContext().startActivity(intent);
				}
			});
		}
	}

	private void setupMapView(ImageView view) {
		int density = (int) view.getResources().getDisplayMetrics().density;

		density = Math.min(density, 2);

		Uri uri = createMapUrl(view.getMeasuredWidth() / density, view.getMeasuredHeight() / density, density);

		if (uri == null) {
			return;
		}

		getPicasso()
			.load(uri)
			.into(view);
	}

	private Uri createMapUrl(int width, int height, int scale) {
		String location = getMapLocation();

		if (location == null) {
			return null;
		}

		int markerColor = getView().getResources().getColor(R.color.insp_card_map_marker) & 0xFFFFFF;

		String markersFormat = "color:0x%06x|%s";
		String markers = String.format(Locale.US, markersFormat, markerColor, location);

		return new Uri.Builder()
			.scheme("https")
			.authority("maps.googleapis.com")
			.path("maps/api/staticmap")
			.appendQueryParameter("sensor", "false")
			.appendQueryParameter("key", getView().getResources().getString(R.string.google_maps_api_key))
			.appendQueryParameter("size", width + "x" + height)
			.appendQueryParameter("scale", Integer.toString(scale))
			.appendQueryParameter("zoom", "15")
			.appendQueryParameter("center", location)
			.appendQueryParameter("markers", markers)
			.build();
	}

	private String getMapLocation() {
		Coordinate coordinate = getModel().getCoordinate();

		if (coordinate != null) {
			return coordinate.getLatitude() + "," + coordinate.getLongitude();
		}

		return getModel().getAddress();
	}

	private Intent createIntent() {
		LocalAdResult model = getModel();
		String title = model.getPlainTextTitle();

		StringBuilder uri = new StringBuilder("geo:0,0?q=")
			.append(Uri.encode(model.getAddress()));

		if (!TextUtils.isEmpty(title)) {
			uri.append(" (")
				.append(Uri.encode(title))
				.append(")");
		}

		return new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
			.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	}

	private void setupAddress(String value) {
		TextView view = findTextView(R.id.insp_card_address);

		if (view != null) {
			view.setText(value);
		}
	}

	private void setupDistance(double value) {
		TextView view = findTextView(R.id.insp_card_distance);

		if (view != null) {
			String format = view.getContext().getString(R.string.insp_card_distance_format);

			view.setText(String.format(format, value));
		}
	}
}
