package com.hs.fruits.common.utils;

import java.util.UUID;

public class CommonUtils {

	/**
	 * 32자리의 랜덤한 파일이름을 생성
	 * @return
	 */
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		for (int i=0; i<10; i++)
		System.out.println(i + " : " + getRandomString());
	}
}
