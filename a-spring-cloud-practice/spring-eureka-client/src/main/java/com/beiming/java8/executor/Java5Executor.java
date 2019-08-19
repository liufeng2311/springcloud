package com.beiming.java8.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. 线程池的基类: Executor
 * 2. 线程池: ThreadPoolExecutor,初始化过程如下
 * ctl变量高三位表示线程池的状态，低29位表示任务的数量，初始化高三位为111，低29位全为0
 * workerCountOf()方法返回低29位值，runStateOf()返回高三位值，ctlOf()返回状态和任务数量
 *
 */
public class Java5Executor {
	public static void main(String[] args) {
		//使用Executors工具类创建线程池
		System.out.println("=============创建固定大小的线程池================");
		ExecutorService pool = Executors.newFixedThreadPool(3);   //使用LinkedBlockingQueue队列
		Executors.newFixedThreadPool(3, Executors.defaultThreadFactory());
		System.out.println("=============创建带有缓存的线程池================");
		Executors.newCachedThreadPool();  //使用TransferQueue队列，线程空闲时间为60秒
		Executors.newCachedThreadPool(Executors.defaultThreadFactory());
		System.out.println("=============创建带有定时任务的线程池================");
		Executors.newScheduledThreadPool(3); //使用DelayedWorkQueue队列
		Executors.newScheduledThreadPool(3, Executors.defaultThreadFactory());
		System.out.println("=============创建单个线程的线程池================");
		Executors.newSingleThreadExecutor();//使用LinkedBlockingQueue队列
		Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
		Executors.newSingleThreadScheduledExecutor();
		Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
		
		
		Thread ta = new Thread(() -> System.out.println(Thread.currentThread().getName()),"Thread-1");
		Thread tb = new Thread(() -> System.out.println(Thread.currentThread().getName()),"Thread-2");
		Thread tc = new Thread(() -> System.out.println(Thread.currentThread().getName()),"Thread-3");
		Thread td = new Thread(() -> System.out.println(Thread.currentThread().getName()),"Thread-4");
		Thread te = new Thread(() -> System.out.println(Thread.currentThread().getName()),"Thread-5");
		
		pool.execute(ta);
		pool.execute(tb);
		pool.execute(tc);
		pool.execute(td);
		pool.execute(te);
		pool.shutdown();
	}
}
