package com.xiom.akusu.utilities.options;

import java.util.ArrayList;
import java.util.List;

import com.xiom.akusu.options.utils.Options;
import com.xiom.akusu.utilities.options.enums.FileStripperOption;

public class FileStripperOptions extends Options {
	public static final String DEFAULT_SEPERATOR = ",";
	public static final boolean DEFAULT_KEEP_QUOTES = false;

	private List<Integer> keepFields;
	private String filePath;
	private String seperator = DEFAULT_SEPERATOR;
	private boolean keepQuotes = DEFAULT_KEEP_QUOTES;

	public FileStripperOptions(String[] args) {
		super(args);

		setKeepFieldsFromOptions();
		setFilePathFromOptions();
		setSeperatorFromOptions();
		setKeepQuotesFromOptions();
	}

	private void setKeepQuotesFromOptions() {
		setKeepQuotes(flagged(FileStripperOption.KEEP_QUOTES));
	}

	private void setSeperatorFromOptions() {
		String[] fieldOpt = getOptionValue(FileStripperOption.SEPERATOR);

		if (fieldOpt != null && fieldOpt.length > 0) {
			setSeperator(fieldOpt[0]);
		}
	}

	private void setKeepFieldsFromOptions() {
		String[] fieldOpt = getOptionValue(FileStripperOption.KEEP_FIELD);

		List<Integer> keep = new ArrayList<Integer>();

		if (fieldOpt != null && fieldOpt.length > 0) {
			for (String s : fieldOpt) {
				try {
					keep.add(Integer.valueOf(s));
				} catch (NumberFormatException e) {

				}
			}
		}

		setKeepFields(keep);
	}

	private void setFilePathFromOptions() {
		String[] fieldOpt = getOptionValue(FileStripperOption.FILE_PATH);

		if (fieldOpt != null && fieldOpt.length > 0) {
			// TODO: Add some validation here
			setFilePath(fieldOpt[0]);
		}
	}

	private void setKeepFields(List<Integer> keepFields) {
		this.keepFields = keepFields;
	}

	public List<Integer> getKeepFields() {
		return keepFields;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}

	public String getSeperator() {
		return seperator;
	}

	public void setKeepQuotes(boolean keepQuotes) {
		this.keepQuotes = keepQuotes;
	}

	public boolean isKeepQuotes() {
		return keepQuotes;
	}

}
