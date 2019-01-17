package com.github.nurkholik.LibUtilityJava;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	/**
     * Mapping JSON to Entity
     * @param T
     * @param strJson
     * @return
     */
	public static <T> T fromJson (Class<T> T, String strJson) {
		return new GsonBuilder()
				.setDateFormat("dd-MM-yyyy HH:mm:ss")
				.create()
				.fromJson(strJson, T);
    }
	
	public static <T> String toJson (Class<T> T,  T strJson) {
		return new GsonBuilder()
				.setDateFormat("dd-MM-yyyy HH:mm:ss")
				.create()
				.toJson(strJson, T);
    }
	
	public static <T> String toJson (T obj) {
		return new GsonBuilder()
				.setDateFormat("dd-MM-yyyy HH:mm:ss")
				.create()
				.toJson(obj, obj.getClass());
    }
	
	@SuppressWarnings("unchecked")
	public static <T> HashMap<String, String> toHashmap (T obj) {
		return new Gson().fromJson(toJson(obj), HashMap.class);
    }
}
