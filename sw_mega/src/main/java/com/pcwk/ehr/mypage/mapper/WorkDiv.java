/**
 * Package Name : com.pcwk.ehr.mapper <br/>
 * Class Name: WorkDiv.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-26<br/>
 *
 * ------------------------------------------<br/>
 * @author :PC
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.mypage.mapper;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;

public interface WorkDiv<T>{
	
	/**
	 * 단건조회
	 * @param param
	 * @return T
	 */
	T doSelectOne(T param);
	
	/**
	 * 전체 조회
	 * @param param
	 * @return List<T>
	 */
	List<T> doRetrieve(T param);
	
	/**
	 * 단건 등록
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int doSave(T param);
	
	/**
	 * 단건 수정
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int doUpdate(T param);
	
	/**
	 * 단건 삭제
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int doDelete(T param);

}
