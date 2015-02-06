package com.infospace.sdk;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Container {
	public static class TypeNotRegisteredException extends RuntimeException {
		public TypeNotRegisteredException() {
			super();
		}

		public TypeNotRegisteredException(String message) {
			super(message);
		}
	}

	private static ConcurrentHashMap<Class<?>, Object> map = new ConcurrentHashMap<Class<?>, Object>();

	public static void register(Object instance) {
		if (instance != null) {
			registerRecursive(instance.getClass(), instance);
		}
	}

	private static void registerRecursive(Class<?> type, Object instance) {
		if (type != null) {
			map.put(type, instance);

			registerRecursive(type.getSuperclass(), instance);

			for (Class<?> i : type.getInterfaces()) {
				registerRecursive(i, instance);
			}
		}
	}

	public static <T extends Object> void register(Class<T> type, Class<?> concreteType) {
		map.put(type, concreteType);
	}

	public static <T extends Object> T resolve(Class<T> type) throws TypeNotRegisteredException {
		if (!map.containsKey(type))
			throw new TypeNotRegisteredException(String.format("%s is not registered.", type.getSimpleName()));
		return type.cast(map.get(type));
	}

	public static <T extends Object> void remove(Class<T> type) {
		map.remove(type);
	}

	public static void removeAll(List<Class<? extends Object>> types) {
		if (types == null) {
			return;
		}
		for (Class<?> type : types) {
			map.remove(type);
		}
	}

	public static <T extends Object> void addAll(Map<Class<? extends Object>, Object> objects) {
		for (Class<? extends Object> type : objects.keySet()) {
			register((Class<T>) type, (T) objects.get(type));
		}
	}

	public static <T extends Object> void register(Class<T> type, T instance) {
		map.put(type, instance);
	}
}
