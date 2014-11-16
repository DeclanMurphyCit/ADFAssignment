package com.citonline.db.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citonline.domain.Deferral;
/**
 * 
 * @author peter halligan
 * 
 *implementation of the Rowmapper interface for the deferral object
 */
public class DeferralMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		int id_student = rs.getInt("id_student");
		int id_program = rs.getInt("id_program");
		boolean programDeferred = rs.getBoolean("program_deferred");
		int id = rs.getInt("id_deferral");
		String deferralDate = rs.getDate("deferral_date").toString();
		int status = rs.getInt("id_deferral_status");
		return new Deferral(id, deferralDate, id_student, id_program, programDeferred, status);
	}

}
