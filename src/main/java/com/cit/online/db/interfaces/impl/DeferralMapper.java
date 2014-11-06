package com.cit.online.db.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.citonline.domain.Deferral;

public class DeferralMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		int id_student = rs.getInt("id_student");
		int id_program = rs.getInt("id_program");
		boolean programDeferred = rs.getBoolean("program_deferred");
		int id_deferral = rs.getInt("id_deferral");
		Date deferralDate = rs.getDate("defferal_date");
		int status = rs.getInt("id_deferral_status");
		
		return new Deferral(deferralDate, id_student, id_program, programDeferred, status);
	}

}
