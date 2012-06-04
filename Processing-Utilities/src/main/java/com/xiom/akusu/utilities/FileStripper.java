package com.xiom.akusu.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.xiom.akusu.utilities.options.FileStripperOptions;

public class FileStripper {

	private FileStripperOptions options;

	public static void main(String args[]) {
		FileStripper stripper = new FileStripper(new FileStripperOptions(args));

		stripper.run();
	}

	private void run() {
		Path readPath = Paths.get(options.getFilePath());
		Path writePath = renameStripped(readPath);
		
		try {
			Files.createFile(writePath);
		 } catch (IOException e) {
			 System.err.format("IOException: %s%n", e);
		}
		
		Charset charset = Charset.forName("US-ASCII");

		try (BufferedReader br =
                 Files.newBufferedReader(readPath, charset); BufferedWriter bw = Files.newBufferedWriter(writePath, charset)) {
			 
			stripFile(br, bw);
			
			bw.close();
			 
			 
		 } catch (IOException e) {
			 System.err.format("IOException: %s%n", e);
		}

	}

	private Path renameStripped(Path oldPath) {
		String newName = oldPath.getFileName().toString();
		String extension = newName.substring(newName.indexOf("."));
		newName = newName.replace(extension, "_stripped" + extension);
		
		Path newPath = oldPath.resolveSibling(newName);
		
		return newPath;
	}

	private void stripFile(BufferedReader br, BufferedWriter bw) throws IOException {
		
		String line = "";
		while((line = br.readLine()) != null)
		{
			bw.write(stripLine(line));
			bw.newLine();
		}
	}

	private String stripLine(String line) {
		
		String[] tokens = line.split(options.getSeperator());
		
		String strippedLine = "";
		int tokenCount = 0;
		
		for (Integer i = 0; i < tokens.length; i++)
		{
			if (options.getKeepFields().contains(i+1))
			{
				if (!options.isKeepQuotes())
				{
					if (tokens[i].charAt(0) == '"' && tokens[i].charAt(tokens[i].length()-1) == '"')
					{
						tokens[i] = tokens[i].substring(1, tokens[i].length() - 1);
					}
				}
				
				strippedLine += (tokenCount > 0 ? options.getSeperator() : "") + tokens[i] ;
				tokenCount++;
			}
		}
		
		return strippedLine;
	}

	public FileStripper(FileStripperOptions opts) {
		setOptions(opts);
	}

	public void setOptions(FileStripperOptions options) {
		this.options = options;
	}

	public FileStripperOptions getOptions() {
		return options;
	}
}
