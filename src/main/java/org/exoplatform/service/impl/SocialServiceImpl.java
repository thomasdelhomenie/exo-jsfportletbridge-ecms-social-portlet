package org.exoplatform.service.impl;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.service.SocialService;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.ActivityManager;
import org.exoplatform.social.core.manager.IdentityManager;

public class SocialServiceImpl implements SocialService {

	@Override
	public void postActivity(String activityText) {

		// Gets the current container.
		PortalContainer container = PortalContainer.getInstance();

		// Gets the current user id
		ConversationState conversationState = ConversationState.getCurrent();
		org.exoplatform.services.security.Identity identity = conversationState.getIdentity();
		String userId = identity.getUserId();

		
		// Gets identityManager to handle an identity operation.
		IdentityManager identityManager = (IdentityManager) container.getComponentInstanceOfType(IdentityManager.class);

		// Gets an existing social identity or creates a new one.
		Identity userIdentity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, userId, false);

		// Gets activityManager to handle an activity operation.
		ActivityManager activityManager = (ActivityManager) container.getComponentInstanceOfType(ActivityManager.class);

		// Saves an activity by using ActivityManager.
		activityManager.saveActivity(userIdentity, null, activityText);

	}

}
