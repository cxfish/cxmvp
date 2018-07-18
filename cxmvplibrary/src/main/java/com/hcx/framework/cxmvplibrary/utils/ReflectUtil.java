package com.hcx.framework.cxmvplibrary.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 */
@SuppressWarnings("unchecked")
public class ReflectUtil {

	/**
	 * 获得超类的参数类型，取第一个参数类型
	 * 
	 * @param <T> 类型参数
	 * @param clazz 超类类型
	 */
	@SuppressWarnings("rawtypes")
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	/**
	 * 根据索引获得超类的参数类型
	 * 
	 * @param clazz 超类类型
	 * @param index 索引
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 反射产生类的实例
	 * 
	 * @param className 类名
	 */
	public static Object createInstance(final String className) {
		if (null != className) {
			try {
				return Class.forName(className).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> void copy(T src, T dst) throws IllegalAccessException {
		for (Class<?> clz = src.getClass(); clz != null; clz = clz
		        .getSuperclass()) {
			for (Field field : clz.getDeclaredFields()) {
				boolean accessible = field.isAccessible();
				field.setAccessible(true);
				field.set(dst, field.get(dst));
				field.setAccessible(accessible);
			}
		}
	}

}
