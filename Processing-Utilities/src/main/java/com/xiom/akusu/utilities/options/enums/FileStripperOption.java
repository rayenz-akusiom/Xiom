package com.xiom.akusu.utilities.options.enums;

import com.xiom.akusu.enums.OptionEnum;

public enum FileStripperOption implements OptionEnum {
	KEEP_FIELD("Keep", "K"), FILE_PATH("File", "F"), SEPERATOR("Seperator", "S"), KEEP_QUOTES("Quotes", "Q");

	private String fullName = "";
	private String shortName = "";

	FileStripperOption(String fullName, String shortName) {
		this.fullName = fullName;
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public boolean equals(String toCheck) {
		return fullName.equalsIgnoreCase(toCheck) || shortName.equalsIgnoreCase(toCheck);
	}
}
