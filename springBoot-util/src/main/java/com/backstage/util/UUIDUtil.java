package com.backstage.util;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		// return UUID.randomUUID().toString().toUpperCase();
	}
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(getUUID());
		}
		
	}
}
