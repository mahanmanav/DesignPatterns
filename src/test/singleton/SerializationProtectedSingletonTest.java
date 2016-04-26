package test.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import main.singleton.SerializationProtectedSingleton;

import org.junit.Test;

public class SerializationProtectedSingletonTest {

	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException {
		SerializationProtectedSingleton instanceOne = SerializationProtectedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton"));
        out.writeObject(instanceOne);
        out.close();
         
        ObjectInput in = new ObjectInputStream(new FileInputStream("singleton"));
        SerializationProtectedSingleton instanceTwo = (SerializationProtectedSingleton) in.readObject();
        in.close();
         
        System.out.println("instanceOne hashCode="+instanceOne.hashCode());
        System.out.println("instanceTwo hashCode="+instanceTwo.hashCode());
	}
}