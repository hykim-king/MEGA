/**
 * Package Name : com.pcwk.ehr.user.cmn.aspect <br/>
 * File Name: PerformanceAdvice.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-17<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.user.cmn.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import com.pcwk.ehr.cmn.PLog;

/**
 * @author user
 *
 */
public class PerformanceAdvice implements PLog{
	
	//Service에서 성능 측정
	public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		
		Object retObj = null;

		long start = System.currentTimeMillis();
		
		//클래스 명
		String className = pjp.getTarget().getClass().getName();
				
		//메서드 명
		String methodName = pjp.getSignature().getName();
				
		retObj = pjp.proceed();
		
		long end = System.currentTimeMillis();
		
		long executionTime = end - start;
		log.debug("│ className                           │"  +className );
		log.debug("│ methodName                          │"  +methodName );
		log.debug("│executionTime:" + executionTime + "ms│");
		
		return retObj;
	}
	

}
