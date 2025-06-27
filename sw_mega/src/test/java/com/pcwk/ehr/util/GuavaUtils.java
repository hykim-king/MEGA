/**
 * Package Name : com.pcwk.ehr.util <br/>
 * Class Name: GuavaUtils.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-26<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.util;

import com.google.common.base.*;
import com.pcwk.ehr.cmn.SearchDTO;

public class GuavaUtils {
	
	public static int nvlZero(int value, int defaultValue) {
		return (value==0)?defaultValue:value;
	}
	
	public static boolean isNullOrEmpty(String str) {
		return Strings.isNullOrEmpty(str);
	}
	
	//null -> 빈문자열
	public static String nullToEmpty(String str) {
		//return (str == null) ? "":str;
		return Strings.nullToEmpty(str);
	}
	
	public static void  main(String[] args) {
		
		//Null 테스트 
		System.out.println("NullOrEmpty: "+isNullOrEmpty(null));
		System.out.println("NullOrEmpty: "+isNullOrEmpty(""));
		
		System.out.println("nullToEmpty: "+nullToEmpty(null));
		
	}

}
