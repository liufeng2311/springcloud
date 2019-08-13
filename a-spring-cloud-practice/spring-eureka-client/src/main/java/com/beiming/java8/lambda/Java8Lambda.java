package com.beiming.java8.lambda;

import java.util.Comparator;

/**
 * 1. 该表达式是一个匿名函数,可以使代码更简洁易懂,只需注重参数和逻辑,只能作用于函数式接口，没有方法体（接口只有一个方法，或者用@FunctionalInterface声明）
 * 2. 格式如下: () -> {}     参数  ->  逻辑代码
 * 3. 左侧参数定义如下  ()表示没有参数 ;  (x)表示一个参数 ;  (x,y) 多个参数用逗号隔开
 * 4. 右侧参数定义如下  {}中写逻辑代码, 只有一行时，{}和return都可以省略，
 * 
 * 5. 四大内置函数式接口
  *    Consumer<T>：消费型接口(void accept(T t))，接收一个参数，无返回值。
  *    Supplier<T>：供给型接口(T get())，无参数，有返回值。
  *    Function<T,R>：函数型接口(R apply(T t))，接收一个参数，有返回值。
  *    Predicate<T>：断言型接口(boolean test(T t))，接收一个参数，返回Boolean值
 */

@SuppressWarnings("unused")
public class Java8Lambda {

	//无返回值
	public void  runnable ( ) {

		//常规定义Runnable
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world");
			}
		};

		//Lambda表达式定义
		Runnable lambda =  () -> System.out.println("hello world");

	}
	
	//有返回值
	public void comparator( ) {
		
		//常规定义Comparator
		Comparator<Integer> comparator = new Comparator<Integer>() {
		     @Override
		     public int compare(Integer o1, Integer o2) {
		         return Integer.compare(o1,o2);
		     }
		};
		
		//Lambda表达式定义
		Comparator<Integer> comparator1 = (x,y) -> Integer.compare(x,y);
		//方法引用
		Comparator<Integer> comparator2 = Integer::compare;
	}
}
