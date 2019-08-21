package com.beiming.thread;

/**
 *  线程分为用户线程和守护线程，通过isDaemon()方法返回，true表示守护线程，守护线程是为用户线程服务的，Java虚拟机在用户线程都结束后会后退出
 *  线程优先级的范围为1~10，默认为5；高优先级线程不一定优先于低优先级线程执行，只是在竞争中较大概率获取CPU的使用权
 *  创建子线程时，线程的优先级和类别都会默认为父线程的
 *
 */
public class DaemonThread {

	public static void main(String[] args) {
		MyThread  t1 = new MyThread("t1");
		MyThread  t2 = new MyThread("t2");
		t1.setDaemon(true);
		System.out.println(Thread.currentThread().getName() + "------->"  + Thread.currentThread().getPriority());
		System.out.println("t1====" + t1.isDaemon());
		System.out.println("t2====" + t2.isDaemon());
		t1.setPriority(1);
		t2.setPriority(10);           //t2有更高的几率获取CPU，t1是守护线程，t2运行完后，JVM退出，所以t1不可能全部执行完
		t1.start();
		t2.start();
	}
	
	
}


class MyThread extends Thread{
	
	public MyThread(String name) {
		super(name);
	}
	
	public void run() {
		for(int i =1; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "------->"  + Thread.currentThread().getPriority() +  "------->" + i);
		}
	};
}