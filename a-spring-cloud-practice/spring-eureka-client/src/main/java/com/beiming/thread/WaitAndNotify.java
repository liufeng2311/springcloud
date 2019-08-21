package com.beiming.thread;

/**
 *  线程等待与唤醒
 *  wait()方法的作用是使当前正在运行的线程等待，不管是谁调用，都是使当前正在运行的线程进入等待状态
 */
public class WaitAndNotify {

    public static void main(String[] args) {

    	SecndThread t1 = new SecndThread("t1");

        synchronized(t1) {
            try {
                t1.start(); //启动t1线程，由于run()里面的方法体需要t1对象的锁，但该锁被主线程所持有，t1进入阻塞状态
                t1.wait();  //虽然调用的是t1的等待方法，作用是使当前线程终止，即man线程释放该对象的锁并进入等待，此时t1线程获取到对象锁，并执行，此时的锁对象为t1
                System.out.println(Thread.currentThread().getName()+" continue"); //该局不会执行，虽然t1唤醒了主线程，但是t1处于死循环，不会释放锁，所以主线程拿不到锁,t1中无死循环则可以执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
                System.out.println(Thread.currentThread().getName() + " call wait ");
                t1.wait(3000);

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    }
	
}


class SecndThread extends Thread{

    public SecndThread(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {  //获取锁并执行
            System.out.println(Thread.currentThread().getName()+" call notify()");
            notify();  //唤醒该对象上处于等待状态的一个线程
            for(;;) {  //进入死循环
            	
            }
        }
    }
}