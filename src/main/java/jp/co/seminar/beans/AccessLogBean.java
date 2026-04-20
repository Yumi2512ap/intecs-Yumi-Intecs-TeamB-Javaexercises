package jp.co.seminar.beans;

import java.io.Serializable;

public class AccessLogBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String userId;
	private String result;
	private String ip;
	private String agent;
	private String whenTime;

	public AccessLogBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public AccessLogBean(String id,
			String userId,
			String result,
			String ip,
			String agent,
			String whenTime) {
		this.id = id;
		this.userId = userId;
		this.result = result;
		this.ip = ip;
		this.agent = agent;
		this.whenTime = whenTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getResult() {
		return result;
	}

	public String getIp() {
		return ip;
	}

	public String getAgent() {
		return agent;
	}

	public String getWhenTime() {
		return whenTime;
	}
	
}
