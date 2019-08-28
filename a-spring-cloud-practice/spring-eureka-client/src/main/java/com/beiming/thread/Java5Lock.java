package com.beiming.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
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
		 * 执行acquire(1)方法，获取锁，参数1表示获取锁的，将锁的状态+1 具体过程如下
		 * 		1.先执行tryAcquire()方法尝试获取锁，具体逻辑如下
		 *               如果锁的状态为0且当前线程为CLH队列的第一个，则修改锁状态和设置当前持有锁的线程名，
		 *               hasQueuedPredecessors()方法来判断是否为CLH队列第一个
		 *               如果锁的状态不为0，判断当前锁的持有者是否为当前线程，是的话修改锁状态，否则尝试获取锁失败
		 *      2.尝试获获取锁失败后，执行acquireQueued(addWaiter(Node.EXCLUSIVE), arg)方法获取锁
		 *      		 addWaiter(Node.EXCLUSIVE)方法是将当前线程封装为Node对象，并将该线程添加到CLH队列末尾
		 *               acquireQueued()方法主要判断该线程所在的节点是不是tail.next节点
		 *               是的尝试获取锁，并将该节点设置为tail，设置原tail节点的next为null，便于回收
		 *               不是的话，通过LockSupport.park(this)阻塞该线程，并等待上一个线程释放锁时唤醒该线程
		 *               记录此时的中断状态并返回，因为获取锁后可能失去cpu的使用权，在这个期间如果再对其进行中断，中断标记会被清除，所以我们提前记录
		 *               
		 *               ！！！！！！！！！！！！！
		 *               在该过程中如果发生异常的话，会在队列中取消该线程，并唤醒下一个线程
		 *      3.根据线程在获取锁到真正执行起来之间有没有被中断过，执行selfInterrupt()方法 
		 *               因为当线程没有拿到cpu的执行权时，对该线程中断时，线程的中断标记是会被清除的，所以通过二中的这个方法进行中断
		 * 
		 *
		 *                      
		 *                      
		 *                      
		 *释放锁的过程
		 *执行release(1)方法释放锁，参数1的作用同加锁的参数
		 *		1.通过tryRelease()方法尝试释放锁，
		 *		        通过判断-1后锁的状态是否为0返回true或者false，false的话进一步会一步步释放(说明多次获取锁)，直到返回true
		 *		2.成功的话返回true，执行 LockSupport.unpark(s.thread)唤醒下一个线程，否则返回false
		 *		        
		 */
		public void fairSync() {
			fairSync.lock();
			fairSync.unlock();
		}
		
		
		/**
		 * 非公平锁加锁过程
		 * 1.与公平锁不同的时，线程获取锁时，不会判断还不是队列里的第一个元素，而是之前判断锁的状态是不是0，是的话直接获取锁
		 * 		不是的话，在执行acquire(1)方法，与公平锁不同的是，再尝试获取锁时tryAcquire()不会判断是不是位于队列的第一个，加入队列后逻辑一样
		 * 
		 * 
		 * 
		 * 2.释放锁过程同公平锁一样
		 */
		public void nonfairSync() {
			nonfairSync.lock();
			nonfairSync.unlock();
		}
}




/**
 * 遇到的问题：
 * 1.锁状态是如何初始的？       加载后int的默认初始值即为0
 * 2.Node中的tail和head是如何赋值的      在添加线程节点的时候，会通过tail = head = new Node()初始化
 * 3.hasQueuedPredecessors()中h.next ==null 为什么获取锁失败
 * 4.为什么参数、成员内部类、方法要定义为final
 * 5.线程获取锁后获取到cpu，再次失去cpu使用权时，期间被中断怎么办，要通过异常捕获吗？
 */



/**
 * 相关单词：
 * exclusive               独有的、专一的
 *  
 *
 */

