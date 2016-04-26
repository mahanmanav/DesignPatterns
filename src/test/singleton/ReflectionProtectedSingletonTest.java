package test.singleton;

import java.lang.reflect.Constructor;

import main.singleton.ReflectionProtectedSingleton;

import org.junit.Test;

public class ReflectionProtectedSingletonTest {

	@Test
	public void test() {
		ReflectionProtectedSingleton instanceOne = ReflectionProtectedSingleton.getInstance();
		System.out.println("instanceOne hashCode=" + instanceOne.hashCode());
		
		try {
			Class<ReflectionProtectedSingleton> clazz = ReflectionProtectedSingleton.class;
			Constructor<ReflectionProtectedSingleton> cons = clazz.getConstructor();
			cons.setAccessible(true);
			ReflectionProtectedSingleton instanceTwo = cons.newInstance();
			System.out.println("instanceTwo hashCode=" + instanceTwo.hashCode());
		} catch (Exception ex) {
			System.out.println("Failed to create singleton object using reflection");
		}
	}
}