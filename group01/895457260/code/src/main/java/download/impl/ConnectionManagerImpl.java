package download.impl;

import download.Config;
import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

import java.lang.reflect.InvocationTargetException;

public class ConnectionManagerImpl implements ConnectionManager {
	@Override
	public Connection open(String url, int startPos, int endPos) throws ConnectionException {
		String protocol = url.split(":", 2)[0];
		try {
			Class<?> clazz = Class.forName(getClassName(protocol));
			return (Connection) clazz.getDeclaredConstructor(String.class, int.class, int.class)
					.newInstance(url, startPos, endPos);
		} catch (ClassNotFoundException | IllegalAccessException |
				NoSuchMethodException | InstantiationException | InvocationTargetException e) {
			return new DefaultConnection(url, startPos, endPos);
		}
	}

	private String getClassName(String protocol) {
		String packageName = Config.packageName + ".impl.";
		String className = Character.toUpperCase(protocol.charAt(0)) + protocol.substring(1) + "Connection";
		return packageName + className;
	}
}
