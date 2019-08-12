package com.beiming.java8.interfaces;

/**
 * 1.接口中引入默认方法和静态方法的初衷是不破坏原有框架
 * 2.默认方法只可以定义在接口中,即default
 * 3.当父类和接口(只有一个接口)中的默认方面同名时,会调用父类中的方法
 * 4.当实现的两个接口有同名的默认方法时,必须重写该方法以明确调用哪个接口里的方法,重写后与父类方法同名时,调用重写后的方法
 * 5.接口中的静态方法直接由类名调用,两个接口中方法重名也不存在影响
 */
public class InterfaceTest extends Java8Class implements Java8Interface, Java8Interface2{

	@Override
	public String previous(String str) {
		return str;
	}

	@Override
	public String java8Default() {
		return Java8Interface.super.java8Default();
	}
	
	public static void main(String[] args) {
		InterfaceTest demo = new InterfaceTest();
		String previous = demo.previous("普通的抽象方法");
		System.out.println(previous);
		String java8Default = demo.java8Default();
		System.out.println(java8Default);
		Java8Class.java8Static();
	}
}
