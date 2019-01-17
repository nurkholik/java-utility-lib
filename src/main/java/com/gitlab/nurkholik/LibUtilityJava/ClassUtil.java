package com.gitlab.nurkholik.LibUtilityJava;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClassUtil {
	
	private static List<Class<?>> USER_CLASS = 
		Arrays.asList(
			String.class, 
			BigDecimal.class, 
			BigInteger.class, 
			Date.class, 
			Timestamp.class, 
			Time.class, 
			LocalDate.class, 
			Byte.class, 
			Long.class, 
			Double.class, 
			Character.class,  
			Integer.class,  
			Boolean.class, 
			Float.class, 
			byte.class,  
			long.class,  
			double.class, 
			char.class,  
			int.class,  
			boolean.class, 
			float.class
		);
	
	/**
	 * Get NULL field/attribute of class
	 * @param object
	 * @param exeptionField
	 * @return
	 * @throws Exception
	 */
	public static List<String> nullField(Object object, String...exeptionField) throws Exception {
		List<String> nullFields = new ArrayList<String>();
		Class<?> mClass = object.getClass();
		Field[] mField = mClass.getDeclaredFields();
		while (mField != null) {
						
			for (Field field : mField) {
				field.setAccessible(true);
				
				// Check field not in exception
				if (!Arrays.asList(exeptionField).contains(field.getName())) {
					if (field.get(object) == null) {
						// Store field name with null value
						nullFields.add(field.getName());
					} else if (!USER_CLASS.contains( field.get(object).getClass() )) {
						// Child Class
						nullFields.addAll(nullField(field.get(object)));
					}
				}
			}
			
			// Get super class data
			mClass = mClass.getSuperclass();
			mField = mClass.equals(Object.class) ? null : mClass.getDeclaredFields();
		}
		
		return nullFields;
	}
	
	/**
	 * Validate string of attribute name from a class or model entity
	 * @param object
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidAttributeFrom(Object object, String field) throws Exception {
		return isValidAttributeFrom(object.getClass(), field);
	}
	
	/**
	 * Validate string of attribute name from a class or model entity
	 * @param persistance
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidAttributeFrom(Class<?> persistance, String field) throws Exception {
		Class<?> mClass = persistance;
		Field[] mField = persistance.getDeclaredFields();
		while (mField != null) {
			if (Arrays.asList(mField).stream().filter(e -> e.getName().equals(field)).findFirst().isPresent()) {
				return true;
			}
			mClass = mClass.getClass().getSuperclass();
			mField = mClass.equals(Object.class) ? null : mClass.getClass().getDeclaredFields();
		}
		return false;
	}
}
