/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * Class Name: MessageDTO.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-26<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.cmn;

/**
 * @author user
 *
 */
public class MessageDTO extends DTO {
	
	private int messageId; //상태:1(성공)/1이외는 실패
	private String message; //메시지
	
	/**
	 * 
	 */
	public MessageDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param messageId
	 * @param message
	 */
	public MessageDTO(int messageId, String message) {
		super();
		this.messageId = messageId;
		this.message = message;
	}

	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageDTO [messageId=" + messageId + ", message=" + message + "]";
	}

}
