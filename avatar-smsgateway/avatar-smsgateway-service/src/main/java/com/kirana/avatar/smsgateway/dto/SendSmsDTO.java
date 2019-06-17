/**
 * 
 */
package com.kirana.avatar.smsgateway.dto;

import java.util.List;

/**
 * @author __Telmila__
 *
 */
public class SendSmsDTO {
	private List<String> receipients;
	private String message;
	public List<String> getReceipients() {
		return receipients;
	}
	public void setReceipients(List<String> receipients) {
		this.receipients = receipients;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((receipients == null) ? 0 : receipients.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SendSmsDTO other = (SendSmsDTO) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (receipients == null) {
			if (other.receipients != null)
				return false;
		} else if (!receipients.equals(other.receipients))
			return false;
		return true;
	}
}
