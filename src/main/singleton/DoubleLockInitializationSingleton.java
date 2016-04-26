package main.singleton;

public class DoubleLockInitializationSingleton {
	private static DoubleLockInitializationSingleton instance;
	
	private DoubleLockInitializationSingleton() {}
	public static DoubleLockInitializationSingleton getInstance() {
		if(instance == null) {
			synchronized(DoubleLockInitializationSingleton.class) {
				if(instance == null) {
					instance = new DoubleLockInitializationSingleton();
				}
			}
		}
		return instance;
	}
}
