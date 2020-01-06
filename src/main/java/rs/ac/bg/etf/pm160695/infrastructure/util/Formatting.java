package rs.ac.bg.etf.pm160695.infrastructure.util;

import java.time.format.DateTimeFormatter;

public final class Formatting {
	public static final String DATUM_STRING_FORMAT = "dd.MM.yyyy.";
	public static final String DATUM_VREME_STRING_FORMAT = "dd.MM.yyyy. HH:mm:ss";

	public static final DateTimeFormatter DATUM_VREME_FORMATTER = DateTimeFormatter.ofPattern(DATUM_VREME_STRING_FORMAT);
	public static final DateTimeFormatter DATUM_FORMATTER = DateTimeFormatter.ofPattern(DATUM_STRING_FORMAT);
}