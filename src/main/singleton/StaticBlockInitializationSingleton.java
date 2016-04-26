package main.singleton;

public class StaticBlockInitializationSingleton {
	private static StaticBlockInitializationSingleton instance;
	
	private StaticBlockInitializationSingleton() {}
	
	static {
		try {
			instance = new StaticBlockInitializationSingleton();
		} catch (Exception ex) {
			throw new RuntimeException("Failed to create singleton object through static initialization");
		}
	}
	
	public static StaticBlockInitializationSingleton getInstance() {
		return instance;
	}
}