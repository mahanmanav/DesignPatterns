package main.singleton;

import java.io.Serializable;

public class SerializationProtectedSingleton implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SerializationProtectedSingleton() {}
	
	private static class SingletonHelper {
		private static final SerializationProtectedSingleton instance = new SerializationProtectedSingleton();
	}
	
	public static SerializationProtectedSingleton getInstance() {
		return SingletonHelper.instance;
	}
	
	protected Object readResolve() {
		return getInstance();
	}
}