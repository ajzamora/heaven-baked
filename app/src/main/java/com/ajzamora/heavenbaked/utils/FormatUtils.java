package com.ajzamora.heavenbaked.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class FormatUtils {
    private static final Locale DEFAULT_LOCALE = Locale.US;

    public static String toFormattedStringDecimal(double numToFormat, int places) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(DEFAULT_LOCALE);
        String pattern = generatePattern(places);

        DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
        return df.format(numToFormat);
    }

    public static String generatePattern(int places) {
        String symbol = "#";
        if (places <= 0) return symbol;
        StringBuilder sb = new StringBuilder();
        sb.append(symbol).append(".");
        while (places-- > 0) {
            sb.append("#");
        }
        return sb.toString();
    }

}
