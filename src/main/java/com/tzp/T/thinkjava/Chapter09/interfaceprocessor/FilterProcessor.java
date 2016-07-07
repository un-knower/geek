package com.tzp.T.thinkjava.Chapter09.interfaceprocessor;

import com.tzp.T.thinkjava.Chapter09.classprocessor.BandPass;
import com.tzp.T.thinkjava.Chapter09.classprocessor.Filter;
import com.tzp.T.thinkjava.Chapter09.classprocessor.HighPass;
import com.tzp.T.thinkjava.Chapter09.classprocessor.LowPass;
import com.tzp.T.thinkjava.Chapter09.classprocessor.Waveform;

class FilterAdapter implements Processor {

	Filter filter;

	public FilterAdapter(Filter filter) {
		this.filter = filter;
	}

	public String name() {
		return filter.name();
	}

	public Waveform process(Object input) {
		return filter.process((Waveform) input);
	}
}

public class FilterProcessor {
	public static void main(String[] args) {
		Waveform w = new Waveform();
		Apply.process(new FilterAdapter(new LowPass(1.0)), w);
		Apply.process(new FilterAdapter(new HighPass(2.0)), w);
		Apply.process(new FilterAdapter(new BandPass(3.0, 4.0)), w);
	}
}
