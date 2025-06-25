/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * 파일명: WorkDiv.java <br/>
 */
package com.pcwk.ehr.cmn;

import java.sql.SQLException;
import java.util.List;

/**
 * @author user
 *
 */
public interface WorkDiv<T> {
	
	
	List<T> doRetrieve(DTO param);

	/**
	 * 단건삭제
	 * 
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	
	int doDelete(T param);

	int doUpdate(T param);
	
	T doSelectOne(T param);
	
	int doSave(T param);
}

