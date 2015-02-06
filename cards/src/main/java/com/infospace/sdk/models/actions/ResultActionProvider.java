package com.infospace.sdk.models.actions;

import com.infospace.sdk.models.Result;

import java.util.*;

/**
 * A provider which populates results with context specific actions.
 * <p/>
 * To use this feature, first initialize the result action providers in Application.onCreate():
 * <pre>
 * AndroidActivityStarter starter = new AndroidActivityStarter(context);
 * ResultActionProvider.register(new PhoneIntentActionProvider(starter, R.layout.button_phone);
 * ResultActionProvider.register(new EmailIntentActionProvider(starter, R.layout.button_email);
 * </pre>
 * <p/>
 * Registering these concrete implementations of ResultActionProvider
 * allows the SDK to populate Result.getActions() with
 * actions the provider parses from the result information.
 *
 * @see com.infospace.sdk.models.Result#getActions()
 * <p/>
 * When populating the result data into a container:
 * <pre>
 *   for (OnActionListener action : result.getActions())
 *   {
 *     View view = findViewById(action.getLayoutId());
 *     view.setOnClickListener(new OnClickListener()
 *     {
 *       public void onClick(View v) {
 *         action.onAction();
 *       }
 *     }
 *   }
 * </pre>
 * Note: it is important that the container contains views with the ids
 * defined in ResultActionProvider.register().
 */
public abstract class ResultActionProvider {
	private static Set<ResultActionProvider> factories = new LinkedHashSet<ResultActionProvider>();

	@Override
	public int hashCode() {
		String fullClassName = this.getClass().getPackage() +
			this.getClass().getName();

		return fullClassName.hashCode();
	}

	/**
	 * Registers a new ResultActionProvider to parse results for actions usable by 3rd party apps
	 *
	 * @param factory
	 */
	public static void register(ResultActionProvider factory) {
		factories.add(factory);
	}

	public static List<OnActionListener> getResultActions(Result result) {
		List<OnActionListener> actions = new ArrayList<OnActionListener>();
		for (ResultActionProvider factory : factories) {
			OnActionListener action = factory.createAction(result);

			if (action != null) {
				actions.add(action);
			}
		}

		return actions;
	}

	public abstract OnActionListener createAction(Result result);
}
