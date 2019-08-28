package com.beiming.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;



/**
 * {@link ReadWriteLock}  该接口包含两个方法，获取读锁 {@link ReadLock}  ，获取写锁 {@link WriteLock}  这两个都是 {@link Lock} 的实现类
 * {@link ReentrantReadWriteLock} 是 {@link ReadWriteLock} 接口的实现类，包括三个属性 ReadLock、 WriteLock、 Sync三个属性 ，读写锁功能的实现都是在Sync中实现的
 * 互斥原则：
 * 读-读能共存，
 * 读-写不能共存，
 * 写-写不能共存
 */
public class Java5ReentrantReadWriteLock {
	 
}




/**
 * 公平锁
 * 读锁的获取过程
 *  1. 通过tryAcquireShared(1)尝试获取线程，步骤如下
 *  	如果存在写锁且写锁的持有者不是当前线程，获取锁失败
 *
 */
class nonfairSync{

	 ReadWriteLock fairSync = new ReentrantReadWriteLock(true); 
	 
	 //读锁
	 public void readLock() {
		 Lock readLock = fairSync.readLock();
		 readLock.lock();
		 readLock.unlock();
	 }
	 
	 //写锁
	 public void writeLock() {
		 Lock readLock = fairSync.writeLock();
		 readLock.lock();
		 readLock.unlock();
	 }
}





/**
 * 非公平锁、
 *
 */
class  fairSync{

	static ReadWriteLock fairSync = new ReentrantReadWriteLock();  
	 
	 public static void main(String[] args) {
		 readLock();
	}
	 
	 /**
	  * 获取读锁，分为两步
	  * 1.tryAcquireShared(1)尝试获取读锁，获取成功则拿到锁，结束
	  * 	如果写锁被其他线程所持有，则获取锁失败
	  * 	队列的第一个元素不为空
	  * 2.doAcquireShared(1)获取锁，尝试获取锁失败后，通过该办法获取锁
	  * 
	  */
	 public static void readLock() {
		 Lock readLock = fairSync.readLock();
		 readLock.lock();
		 readLock.unlock();
	 }
	 
	 //写锁
	 public static void writeLock() {
		 Lock readLock = fairSync.writeLock();
		 readLock.lock();
		 readLock.unlock();
	 }
}

