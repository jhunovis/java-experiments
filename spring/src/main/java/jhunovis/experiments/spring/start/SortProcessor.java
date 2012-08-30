package jhunovis.experiments.spring.start;

import java.util.Arrays;

public class SortProcessor implements Processor {

	public String process(String input) {
		String split[] = input.split("\n");
		if (split.length > 0) {
			Arrays.sort(split);
			StringBuilder builder = new StringBuilder(input.length());
			builder.append(split[0]);
			for (int i=1; i < split.length; i++ ) {
				builder.append("\n").append(split[i]);
			}
			return builder.toString();
		} else {
			return input;
		}		
	}

}
