package jhunovis.experiments.spring.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LineProcessor {
	private ApplicationContext ctx;
	
	public LineProcessor() {
		ctx = new ClassPathXmlApplicationContext("line-processor-beans.xml");
	}
	
	public String process(String processor, String input) {		
		Processor p = (Processor)ctx.getBean("SortProcessor");
		return p.process(input);
	}
}
