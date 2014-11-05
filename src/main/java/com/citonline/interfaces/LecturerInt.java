/**
 * @author Fabien
 * 
 * @since 25 oct. 2014
 */
package com.citonline.interfaces;

import org.springframework.stereotype.Service;

import com.citonline.domain.Deferral;
import com.citonline.domain.Module;

@Service
public interface LecturerInt {
	
	/**
	 * Set the lecturer to teach this module
	 * 
	 * @author Fabien Dubar
	 * @param module
	 */
	void teach(Module module);
	
	/**
	 * Stop the lecturer from teaching this module
	 * 
	 * @author Fabien Dubar
	 * @param module
	 */
	void stopTeach(Module module);

	/**
	 * Inform if the Lecturer is also a program Manager
	 * 
	 * @author Fabien Dubar
	 * @return true if the lecturer manages a program, false otherwise.
	 */
	boolean isProgramManager();
	
	/**
	 * Upload the signed deferral.
	 * 
	 * @author Fabien Dubar
	 * @param deferral
	 */
	void uploadSignedDeferral(Deferral deferral);
	
	void answerSupportCall();
	
}
