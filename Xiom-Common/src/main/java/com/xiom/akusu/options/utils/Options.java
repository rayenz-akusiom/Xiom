package com.xiom.akusu.options.utils;

import java.util.HashMap;
import java.util.Map;

import com.xiom.akusu.enums.OptionEnum;

public class Options {

	protected Map<String, String[]> optionMap = new HashMap<String, String[]>();

	public Options(String[] args) {
		optionMap = (new OptionsProcessor()).parse(args);
	}

	protected String[] getOptionValue(OptionEnum option) {

		String[] optionValue = optionMap.get(option.getFullName().toUpperCase());
		if (optionValue == null) {
			optionValue = optionMap.get(option.getShortName().toUpperCase());
		}

		return optionValue;
	}
	
	protected boolean flagged(OptionEnum option)
	{
		return optionMap.containsKey(option);
	}
}
