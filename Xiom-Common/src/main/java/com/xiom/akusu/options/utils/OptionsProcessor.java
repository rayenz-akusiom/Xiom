package com.xiom.akusu.options.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptionsProcessor {

	private static final String OPTION_PREFIX = "-";

	private Map<String, String[]> optionsMap = new HashMap<String, String[]>();

	public Map<String, String[]> parse(String[] args) {
		for (int opt = 0; opt < args.length; opt++) {
			if (checkPrefix(args[opt])) {
				int det = opt + 1;
				for (; det < args.length; det++) {
					if (checkPrefix(args[det])) {
						break;
					}
				}

				// Place the option flag and all specifiers into the map
				optionsMap.put(stripPrefix(args[opt]).toUpperCase(), Arrays.copyOfRange(args, opt + 1, det));
				opt = det-1;
			}
		}

		return optionsMap;
	}

	private boolean checkPrefix(String arg) {
		return arg.startsWith(OPTION_PREFIX);
	}

	private String stripPrefix(String arg) {
		return arg.replaceFirst("-", "");
	}

	public Map<String, String[]> getOptionsMap() {
		return optionsMap;
	}
}
