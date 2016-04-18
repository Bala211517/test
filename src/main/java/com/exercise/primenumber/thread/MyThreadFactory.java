package com.exercise.primenumber.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory{

	private String prefix;

	private static AtomicInteger count = new AtomicInteger(1);
	
	public MyThreadFactory(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, prefix + count.getAndIncrement());
	}

}
