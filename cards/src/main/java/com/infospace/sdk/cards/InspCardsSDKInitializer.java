package com.infospace.sdk.cards;

import android.content.*;
import android.net.Uri;
import android.widget.Toast;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.binders.*;
import com.infospace.sdk.cards.events.DefaultCardObserver;
import com.infospace.sdk.cards.matchers.*;
import com.infospace.sdk.models.*;
import com.infospace.sdk.models.actions.*;
import com.infospace.sdk.models.parsers.*;
import com.infospace.sdk.models.parsers.matchers.TypeFieldJsonMatcher;
import com.infospace.sdk.util.UtilConfig;
import com.squareup.picasso.Picasso;

class InspCardsSDKInitializer {
	private final Context context;

	public InspCardsSDKInitializer(Context context) {
		this.context = context;
	}

	public void initialize() {
		registerResultActionProviders();
		registerCardObserver();
		registerResultParsers();
		registerCardBinderFactory();
		registerViewFactory();
		registerPicasso();
		registerUtils();
	}

	private void registerPicasso() {
		Picasso p = Picasso.with(context.getApplicationContext());
		p.setLoggingEnabled(true);

		Container.register(p);
	}

	private void registerCardObserver() {
		Container.register(new DefaultCardObserver());
	}

	private void registerResultParsers() {
		ResultParserFactory factory = new ResultParserFactory();

		registerDefaultParsers(factory);

		Container.register(factory);
	}

	private void registerDefaultParsers(ResultParserFactory factory) {
		factory
			.push(new TypeFieldJsonMatcher("web"), WebResultParser.class)
			.push(new TypeFieldJsonMatcher("site"), SiteParser.class)
			.push(new TypeFieldJsonMatcher("deeplink"), DeeplinkParser.class)
			.push(new TypeFieldJsonMatcher("sellerRating"), SellerRatingParser.class)
			.push(new TypeFieldJsonMatcher("news"), NewsResultParser.class)
			.push(new TypeFieldJsonMatcher("video"), VideoResultParser.class)
			.push(new TypeFieldJsonMatcher("image"), ImageResultParser.class)
			.push(new TypeFieldJsonMatcher("product"), ShoppingProductResultParser.class)
			.push(new TypeFieldJsonMatcher("offer"), ShoppingOfferResultParser.class)
			.push(new TypeFieldJsonMatcher("review"), ShoppingReviewResultParser.class)
			.push(new TypeFieldJsonMatcher("deal"), ShoppingDealResultParser.class)
			.push(new TypeFieldJsonMatcher("ad"), AdResultParser.class)
			.push(new TypeFieldJsonMatcher("localAd"), LocalAdResultParser.class)
			.push(new TypeFieldJsonMatcher("related"), RelatedResultParser.class)
			.push(new TypeFieldJsonMatcher("weather"), WeatherResultParser.class)
			.push(new TypeFieldJsonMatcher("weatherForecast"), WeatherForecastResultParser.class)
			.push(new TypeFieldJsonMatcher("spelling"), SpellingResultParser.class)
			.push(new TypeFieldJsonMatcher("horoscope"), HoroscopeResultParser.class)

			.push(new TypeFieldJsonMatcher("webGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("newsGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("imageGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("videoGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("shoppingGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("dealGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("offerGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("relatedGroup"), ResultGroupParser.class)
			.push(new TypeFieldJsonMatcher("reviews"), ResultGroupParser.class);
	}

	private void registerCardBinderFactory() {
		CardBinderFactory factory = new CardBinderFactory();

		registerDefaultBinders(factory);

		Container.register(factory);
	}

	private static void registerDefaultBinders(CardBinderFactory factory) {
		factory
			.push(new ResultTypeMatcher(ResultGroup.class), GroupCardBinder.class)

			.push(new ResultTypeMatcher(AdResult.class), AdCardBinder.class)
			.push(new ResultTypeMatcher(LocalAdResult.class), LocalAdCardBinder.class)
			.push(new ResultTypeMatcher(WebResult.class), WebCardBinder.class)
			.push(new ResultTypeMatcher(NewsResult.class), NewsCardBinder.class)
			.push(new ResultTypeMatcher(ImageResult.class), ImageCardBinder.class)
			.push(new ResultTypeMatcher(DeeplinkResult.class), DeeplinkCardBinder.class)
			.push(new ResultTypeMatcher(RelatedResult.class), RelatedCardBinder.class)
			.push(new ResultTypeMatcher(ShoppingOfferResult.class), ShoppingOfferCardBinder.class)
			.push(new ResultTypeMatcher(ShoppingProductResult.class), ShoppingProductCardBinder.class)
			.push(new ResultTypeMatcher(VideoResult.class), VideoCardBinder.class)
			.push(new ResultTypeMatcher(HoroscopeResult.class), HoroscopeCardBinder.class)
			.push(new ResultTypeMatcher(WeatherResult.class), WeatherCardBinder.class)
			.push(new ResultTypeMatcher(WeatherForecastResult.class), WeatherForecastCardBinder.class);
	}

	private void registerViewFactory() {
		ViewFactory factory = new ViewFactory(context);

		registerDefaultViews(factory);

		Container.register(factory);
	}

	private static void registerDefaultViews(ViewFactory viewSelector) {
		viewSelector
			.push(new CatchAllResultMatcher(), R.layout.insp_card_blank)

			.push(new ResultGroupMatcher(ResultGroupType.RELATEDGROUP), R.layout.insp_card_related_group)
			.push(new ResultGroupMatcher(ResultGroupType.SHOPPINGGROUP), R.layout.insp_card_shopping_group)

			.push(new ResultTypeMatcher(AdResult.class), R.layout.insp_card_ad)
			.push(new ResultTypeMatcher(LocalAdResult.class), R.layout.insp_card_ad_local)
			.push(new ResultTypeMatcher(WebResult.class), R.layout.insp_card_web)
			.push(new ResultTypeMatcher(NewsResult.class), R.layout.insp_card_news)
			.push(new NewsWithImageResultMatcher(), R.layout.insp_card_news_image)
			.push(new ResultTypeMatcher(ImageResult.class), R.layout.insp_card_image)
			.push(new ResultTypeMatcher(DeeplinkResult.class), R.layout.insp_card_deeplink)
			.push(new ResultTypeMatcher(RelatedResult.class), R.layout.insp_card_related)
			.push(new ResultTypeMatcher(ShoppingOfferResult.class), R.layout.insp_card_shopping)
			.push(new ResultTypeMatcher(ShoppingProductResult.class), R.layout.insp_card_shopping)
			.push(new ResultTypeMatcher(VideoResult.class), R.layout.insp_card_video)
			.push(new ResultTypeMatcher(HoroscopeResult.class), R.layout.insp_card_horoscope)
			.push(new ResultTypeMatcher(WeatherResult.class), R.layout.insp_card_weather)
			.push(new ResultTypeMatcher(WeatherForecastResult.class), R.layout.insp_card_weather_forecast);

	}

	private void registerResultActionProviders() {
		final AndroidActivityStarter implicitActivityStarter = new AndroidActivityStarter(context);

		AndroidActivityStarter phoneActivityStarter = new AndroidActivityStarter(context);
		phoneActivityStarter.setOnMissingActivityListener(new AndroidActivityStarter.OnMissingActivityListener() {
			@Override
			public void handleMissingIntent(Intent intent) {
				String phoneNumber = extractPhoneNumberFromData(intent.getData());
				if (phoneNumber != null) {
					Toast.makeText(context, "Phone Number: " + phoneNumber, Toast.LENGTH_LONG).show();
				}
			}
		});

		ResultActionProvider.register(new PhoneIntentActionProvider(phoneActivityStarter, R.layout.insp_action_call));
		ResultActionProvider.register(new DirectionsIntentActionProvider(implicitActivityStarter, R.layout.insp_action_directions));
		ResultActionProvider.register(new EmailIntentActionProvider(implicitActivityStarter, R.layout.insp_action_email));
	}

	private static String extractPhoneNumberFromData(Uri uri) {
		if (uri != null) {
			String dataString = uri.toString();
			String schemeString = uri.getScheme();

			if (dataString != null && schemeString != null && dataString.startsWith(schemeString)) {
				return dataString.substring(schemeString.length() + 1);
			}
		}

		return null;
	}

	private void registerUtils() {
		UtilConfig.register();
	}
}
