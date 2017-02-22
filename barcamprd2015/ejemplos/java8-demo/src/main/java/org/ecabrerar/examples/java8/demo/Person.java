package org.ecabrerar.examples.java8.demo;

/**
 * @author ecabrerar
 * @date Feb 22, 2017
 */
public class Person {

	private String name;
	private int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return name + " - " + age;
	}
}
