package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogMessageTest {
	// Logger 클래스의 인스턴스를 받아온다.(로그기록을 남기는 Class 파일)
	static Logger logger = Logger.getLogger(LogMessageTest.class);
	
	public static void main(String[] args) {
		// 메시지 출력하기(사용하는 메서드명이 Logger의 레벨명과 같다.)
		
		// 형식1) Logger객체변수.출력할레벨명(출력할 메시지)
		logger.trace("이것은 log4j의 [TRACE]레벨의 출력입니다");
		logger.debug("이것은 log4j의 [DEBUG]레벨의 출력입니다");
		logger.info("이것은 log4j의 [INFO]레벨의 출력입니다");
		logger.warn("이것은 log4j의 [WARN]레벨의 출력입니다");
		logger.error("이것은 log4j의 [ERROR]레벨의 출력입니다");
		logger.fatal("이것은 log4j의 [FATAL]레벨의 출력입니다");
		
		// 형식2) Logger객체변수.log(Level.레벨명, "출력할메시지")
		logger.log(Level.INFO, "log() 메서드를 이용한 INFO레벨 출력입니다.");
		
	}

}
