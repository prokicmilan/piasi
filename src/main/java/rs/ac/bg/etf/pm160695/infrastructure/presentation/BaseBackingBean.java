package rs.ac.bg.etf.pm160695.infrastructure.presentation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;

import rs.ac.bg.etf.pm160695.infrastructure.messaging.MessageDispatcher;
import rs.ac.bg.etf.pm160695.infrastructure.security.CurrentUserBean;

public abstract class BaseBackingBean implements Serializable {

	private static final long serialVersionUID = 7430607846102458560L;

	@Inject
	protected Logger logger;

	@Inject
	protected MessageDispatcher messageDispatcher;

	@Inject
	protected CurrentUserBean currentUserBean;

}
