/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * Class Name: WorkDiv.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-25<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.cmn;

import java.sql.SQLException;
import java.util.List;

/**
 * @author user
 *
 */
public interface WorkDiv<T> {

	/**
	 * 목록조회
	 * 
	 * @param param
	 * @return List<T>
	 */
	List<T> doRetrieve(SearchDTO param);

	/**
	 * 단건 삭제
	 * 
	 * @param param
	 * @return 성공(1)/ 실패(0)
	 */
	int doDelete(T param);

	/**
	 * 수정
	 * 
	 * @param param
	 * @return 성공(1)/ 실패(0)
	 */
	int doUpdate(T param);

	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return T
	 */
	T doSelectOne(T param);

	/**
	 * 단건등록
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int doSave(T param);
	
	/**
<<<<<<< HEAD
	 * 조회수 증가
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int viewCount(T param);
	
	/*
	 * 다건등록
	 * @return 1(성공)/0(실패)
	 */
	int saveAll();
	
	/**
	 * 전체삭제
	 */
	void deleteAll();
	
	/**
	 * 등록 건수 세기
	 * @return
	 */
	int getCount();

}
