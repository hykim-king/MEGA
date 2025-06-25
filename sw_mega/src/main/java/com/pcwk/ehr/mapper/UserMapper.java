/**
 * Package Name : com.pcwk.ehr.mapper <br/>
 * 파일명: UserMapper.java <br/>
 */
package com.pcwk.ehr.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.user.domain.UserDTO;

/**
 * @author user
 *
 */
@Mapper
public interface UserMapper extends WorkDiv<UserDTO> {

	int saveAll();

	List<UserDTO> getAll();

	void deleteAll() throws SQLException;

	int getCount() throws SQLException;

}
