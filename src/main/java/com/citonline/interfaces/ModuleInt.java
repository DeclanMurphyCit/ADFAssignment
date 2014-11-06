package com.citonline.interfaces;

import java.util.Calendar;

import org.springframework.stereotype.Service;

/*
 * Author: Tim Wallace
 * Date: 29/10/14
 * 
 * Description:Module interface
 * 
 * Inputs: none
 * 
 * Expected Outputs: module start date
 */ 

@Service
public interface ModuleInt {

	Calendar moduleStart();
}
