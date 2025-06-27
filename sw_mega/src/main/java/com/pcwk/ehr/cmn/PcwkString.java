/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * Class Name: PcwkString.java <br/>
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
package com.pcwk.ehr.cmn;

import com.google.common.base.Strings;

/**
 * @author user
 *
 */
public class PcwkString {
	
	/**
	 * value가 0이면 defaultValue 리턴
	 * @param value
	 * @param defaultValue
	 * @return int
	 */
	public static int nvlZero(int value, int defaultValue) {
		return (value==0)?defaultValue:value;
	}
	
	
	/**
	 * null 또는 빈 문자열인지 체크 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return Strings.isNullOrEmpty(str);
	}

	
	/**
	 * null -> 빈문자열
	 * @param str
	 * @return ""
	 */
	public static String nullToEmpty(String str) {
		//return (str == null) ? "":str;
		return Strings.nullToEmpty(str);
	}
	
}
