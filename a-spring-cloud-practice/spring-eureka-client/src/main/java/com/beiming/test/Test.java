package com.beiming.test;

public class Test {
	
	public static void main(String[] args) {
		doubleBreak();
		System.out.println("=====================");
		doubleContinue();
		System.out.println("=====================");
		doubleBreakTo();
		System.out.println("=====================");
		doubleContinueTo();
	}
	
	
	//break  只能跳出本层循环，不会跳出外层循环
	public static void doubleBreak() {
		for (int i = 0; i < 5; i++) {
			System.out.println("I=====" + i);
			if(i == 2) break;
			for (int j = 0; j < 5; j++) {
				System.out.println("J=====" + j);
				if(j == 2) break;
			}
		}
	}
	
	//continue  只能跳出本层本次循环，不会跳出对外层循环
	public static void doubleContinue() {
		for (int i = 0; i < 5; i++) {
			System.out.println("I=====" + i);
			for (int j = 0; j < 5; j++) {
				if(j == 2) continue;
				System.out.println("J=====" + j);
			}
		}
	}

	//break 跳出外层循环 在循环开始处自定义起始标志，内部循环指定break 跳出的循环标志即可跳出制定循环
	public static void doubleBreakTo() {
		out:
		for (int i = 0; i < 5; i++) {
			System.out.println("I=====" + i);
			for (int j = 0; j < 5; j++) {
				System.out.println("J=====" + j);
				if(j == 2) break out;
			}
		}
	}
	
	//continue  跳至循环标记处继续循环
	public static void doubleContinueTo() {
		out:
		for (int i = 0; i < 5; i++) {
			System.out.println("I=====" + i);
			for (int j = 0; j < 5; j++) {
				System.out.println("J=====" + j);
				if(j == 2) continue out;
			}
		}
	}
}
