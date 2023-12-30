package com.hrms.dto;

import java.time.LocalDateTime;

public class ApiError {
	
	private String msg;
	private int status;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public void setTimestamp(LocalDateTime now) {
		/**
		 * The setTimestamp method is intended to set the timestamp for the ApiError,
		 * however, due to specific requirements or constraints, the implementation is left empty.
		 * This method might be intended for use in a different context or future enhancements.
		 * For specific timestamp functionality, consider completing the implementation by setting
		 * the timestamp value appropriately.
		 */

	}
}
