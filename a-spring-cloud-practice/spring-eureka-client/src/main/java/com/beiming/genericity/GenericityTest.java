package com.beiming.genericity;

import java.util.List;

/**
 * 泛型中通配符的约定
 * T 表示具体的java类型
 * ？表示不确定的java类型
 * K 表示键值对中的键
 * V 表示键值对中的值
 *  E 表示Element
 */
public class GenericityTest {

	List<Animal> listAnimals;
	
	List<? extends Animal> listAnimal;
	
	class Animal{
		private int countLegs;
	}
	
	static void count(List<Animal> listAnimals) {
		
	}
	
	public static void main(String[] args) {
	}
}
