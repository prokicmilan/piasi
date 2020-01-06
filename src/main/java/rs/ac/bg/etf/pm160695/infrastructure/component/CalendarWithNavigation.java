package rs.ac.bg.etf.pm160695.infrastructure.component;

import org.primefaces.component.calendar.Calendar;

import rs.ac.bg.etf.pm160695.infrastructure.util.Formatting;

public class CalendarWithNavigation extends Calendar {

	@Override
	public boolean isNavigator() {
		return ((Boolean) getStateHelper().eval(PropertyKeys.navigator, true)).booleanValue();
	}

	@Override
	public String getPattern() {
		return (String) getStateHelper().eval(PropertyKeys.pattern, Formatting.DATUM_STRING_FORMAT);
	}

}
