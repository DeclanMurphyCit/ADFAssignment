package com.citonline.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.citonline.domain.Module;

/* Author: Tim Wallace
 * Date: 03/11/14
 * 
 * Description: Module mapper, maps the row
 * 
 * Inputs: Module semester, code, crn, name
 */

public class ModuleMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		String code = rs.getString("code");
		String crn = rs.getString("crn");
		String name = rs.getString("name");
		int semester = rs.getInt("semester");
		Module module = new Module(crn, name, code, 
				semester);
		
		return module;
	}
}
