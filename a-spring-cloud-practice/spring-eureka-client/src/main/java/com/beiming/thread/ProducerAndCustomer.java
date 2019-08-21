package com.beiming.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者问题
 * 
 */
public class ProducerAndCustomer {

	public static void main(String[] args) {
		//启动生产者
		Producer producer = new Producer();
		producer.producer();
		//启动消费者
		Customer customer = new Customer();
		customer.customer();
	}




}


//生产者
class Producer  extends Base implements Runnable{

	public Producer() {}

	public void producer() {
		pool.execute(this);
	}

	@Override
	public void run() {
		lock.lock();
			try {
				int i=1;
				for(;;) {
				if(queue.size() == 100) {
					i=0;
					put.await();
				}
				Thread.sleep(500);
				queue.add(i);
				take.signal();
				//Thread.yield();
				System.out.println(Thread.currentThread().getName() + "===put===" + (i++));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
	}



}

//消费者
class Customer extends Base implements Runnable{

	public Customer() {}

	public void customer() {
		pool.execute(this);
	}

	@Override
	public void run() {
		lock.lock();
		try {
			for(;;) {
				if(queue.size() == 0) {
					take.await();
				}
				Integer poll = queue.poll();
				put.signal();
				//Thread.yield();
				System.out.println(Thread.currentThread().getName() + "===take===" + poll);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
	
}

//生产者和消费者继承的Base类
class Base{
	static ExecutorService pool = Executors.newFixedThreadPool(2);   //定义线程池
	static BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(100);  //设置队列大小
	static Lock lock =new ReentrantLock();  //创建锁
	static Condition put = lock.newCondition(); //控制生产者流程的条件
	static Condition take = lock.newCondition(); ////控制消费者流程的条件
}