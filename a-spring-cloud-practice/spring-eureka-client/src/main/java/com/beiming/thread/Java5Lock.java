package com.beiming.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁框架 两个主要接口 {@link Lock}   {@link ReadWriteLock}
 *  
 *
 */
public class Java5Lock {
		//创建成员变量Lock，用于代替synchronized
		Lock nonfairSync = new ReentrantLock();  //默认非公平锁  sync = NonfairSync
		Lock fairSync = new ReentrantLock(true);  //参数为true时，公平锁 sync = FairSync
		
		/**
		 * 公平锁加锁过程，
		 * 执行acquire()方法，获取锁，具体过程如下
		 * 								1.先执行tryAcquire()方法尝试获取锁，如果锁的状态为0且当前线程为CLH队列的第一个，则修改锁状态和设置当前持有锁的线程名，
		 *                                                                                     hasQueuedPredecessors()方法来判断是否为CLH队列第一个，该队列tail ！= head 且head.next  等于当前线程 
		 *                                                                                     如果锁的状态不为0，判断当前锁的持有者是否为当前线程，是的话修改锁状态，否则获取锁失败
		 *                              2.尝试获获取锁失败后，执行acquireQueued(addWaiter(Node.EXCLUSIVE), arg)方法，将该线程添加到CLH队列末尾
		 *                                                                                    先通过addWaiter()方法将当前线程封装为Node对象，并将其放置在队列尾端，没有队列时，需要新建再添加
		 *                                                                                    再通过acquireQueued()方法是逐步执行CLH中的对象，若获取锁，则返回，否则休眠，线程的中断状态都默认为false，用于接收中断
		 *                                                                                    循环执行如下逻辑：
		 *                                                                                    
		 * 
		 *                      
		 */
		public void fairSync() {
			fairSync.lock();
			fairSync.unlock();
		}
}




/**
 * 遇到的问题：
 * 1.锁状态是如何初始的
 *  
 *
 */



/**
 * 相关单词：
 * exclusive               独有的、专一的
 *  
 *
 */