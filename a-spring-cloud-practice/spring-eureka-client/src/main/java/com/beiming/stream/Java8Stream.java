package com.beiming.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.beiming.mybatis.bean.entity.User;

/**
 * 流是一次性的，即每一个stream对象只能被消费一次
 *
 */
public class Java8Stream {

	private static List<User> users;
	
	private static final String PASSWORD = "123456";

	//初始化结合用做数据源
	static {
		users = new ArrayList<User>();
		for(int i = 1; i < 10; i++) {
			User user = new User();
			user.setId(i);
			user.setUsername("liufeng" + i);
			user.setPassword("123456");
			users.add(user);
		}
	}


	public static void main(String[] args) {
		streamToList(users);
		check(users);
		find(users);
		number(users);
		noneBack(users);
		joining(users);
	}
	
	public static  List<?> streamToList(List<User> list){
			List<?> collect = list.stream()
				.filter(e -> PASSWORD.equals(e.getPassword()))      //过滤元素
				.distinct()                                         //去重,自定义对象需要重写该方法equal()
				.sorted((p1, p2) -> p1.getId() - p2.getId())        //排序,需要对象实现Comparable接口，或定义Comparator方法
				.sorted(Comparator.comparingInt(User::getId))       //根据Id字段排序，同上
				.limit(2)                                           //截取前n个元素
				.skip(1)                                            //去除前n各元素，同limit可以实现截取元素
				.map(e -> e.getPassword())                          //将集合中的元素映射为新类型的元素，该语句等价于map(Person::getName)
				.map(s -> s.split(""))                              //该返回值中流的类型是String[],通过下面可以转换为String
				.flatMap(Arrays::stream)                            //flatMap将流中的每一个元素 T 映射为一个流，再把每一个流连接成为一个流
				.collect(Collectors.toList());                      //Stream --->  List
			System.out.println(collect);
			return null;
	}
	
	
	public static boolean check(List<User> list) {
		boolean anyMatch = list.stream().anyMatch(e -> PASSWORD.equals(e.getPassword()));  //是否存在一个元素符合条件
		System.out.println(anyMatch);
		boolean allMatch = list.stream().allMatch(e -> PASSWORD.equals(e.getPassword()));  //是否全部符合条件
		System.out.println(allMatch);
		boolean noneMatch = list.stream().noneMatch(e -> PASSWORD.equals(e.getPassword())); //是否没有元素符合条件
		System.out.println(noneMatch);
		return false;
		
	}
	
	//Optional  java8为了防止空指针而设计的类，isPresent() ：值存在时返回 true，反之 flase
	public static Optional<?> find(List<User> list){
		Optional<User> findAny = list.stream().findAny(); //串行流返回第一个 并行流返回其中一个
		System.out.println(findAny.isPresent());
		Optional<User> findFirst = list.stream().findFirst(); //返回元素中第一个
		System.out.println(findFirst);
		return null;
		
	}
	
	public static Object number(List<User> list) {
		long count = list.stream().count();   //计数
		System.out.println(count);
		Integer reduce = list.stream().map(User :: getId).reduce(0,Integer :: sum);  //求和
		System.out.println(reduce);
		Integer reduce2 = list.stream().map(User :: getId).reduce(0,Integer :: max);  //求最大值
		System.out.println(reduce2);
		//流在计算时会进行拆箱操作 ,为此我们可以使用数字流
		int sum = list.stream().mapToInt(User :: getId).sum();           //转化为数字流Int
		System.out.println(sum);
		long sum2 = list.stream().mapToLong(e -> e.getId()).sum();       //转化为数字流Long
		System.out.println(sum2);
		list.stream().mapToInt(User :: getId).boxed();                   //数组流转为流boxed
		int sum4 = IntStream.range(1, 100).sum();                        //生成1到100的数字流，半闭半开区间，不包括100
		System.out.println(sum4);
		int sum3 = IntStream.rangeClosed(1, 100).sum();                  //生成1到100的数字流，闭区间
		System.out.println(sum3);
		return null;
		
	}
	
	
	public static void noneBack(List<User> list) {
		list.stream().forEach(System.out :: println);  //该方法没有返回值，可以再次循环处理没有返回值的方法
	}
	
	//用于字符串拼接 只能作用于字符串字段
	public static void joining(List<User> list) {
		String collect = list.stream().map(User :: getPassword).collect(Collectors.joining());  //拼接所有元素的password字段
		System.out.println(collect);
		String collect1 = list.stream().map(User :: getPassword).collect(Collectors.joining(",")); //拼接所有元素的某一字符串字段 并用逗号隔开
		System.out.println(collect1);
		String collect2 = list.stream().map(User :: getPassword).collect(Collectors.joining(",","{","}")); //拼接所有元素的某一字符串字段 并用逗号隔开,并加上前后缀
		System.out.println(collect2);
	}
	
}
