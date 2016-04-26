package main.singleton;

import java.lang.reflect.ReflectPermission;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

public class ReflectionProtectedSingleton {
	private static ReflectionProtectedSingleton instance;
	
	private ReflectionProtectedSingleton() {
		ReflectPermission perm = new ReflectPermission("suppressAccessCheck", "");
		AccessController.checkPermission(perm);
	}
	
	static {
		Policy.setPolicy(new CustomPolicy());
	}
	
	public static ReflectionProtectedSingleton getInstance() {
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			@Override
			public Object run() {
				instance = new ReflectionProtectedSingleton();
				return null;
			}
		});
		return instance;
	}
}

class CustomPolicy extends Policy {
	private static PermissionCollection perms;
	
	public CustomPolicy() {
		super();
		if(perms == null) {
			perms = new CustomPermissionCollection();
			addPermissions();
		}
	}
	
	@Override
	public PermissionCollection getPermissions(CodeSource codesource) {
		return perms;
	}
	
	private void addPermissions() {
		ReflectPermission reflectPermission = new ReflectPermission("src/main", "suppressAccessCheck");
		perms.add(reflectPermission);
	}
}

class CustomPermissionCollection extends PermissionCollection {
	private static final long serialVersionUID = 1L;

	ArrayList<Permission> perms = new ArrayList<Permission>();
	
	@Override
	public void add(Permission permission) {
		perms.add(permission);
	}

	@Override
	public boolean implies(Permission permission) {
		for(Iterator<Permission> i = perms.iterator(); i.hasNext(); ) {
			if(((Permission) i.next()).implies(permission)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Enumeration<Permission> elements() {
		return Collections.enumeration(perms);
	}
}