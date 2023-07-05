package com.devsuperior.tutresttemplate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenseDTO {

	@JsonProperty("key")
	private String key;
	@JsonProperty("name")
	private String name;
	@JsonProperty("spdx_id")
	private String spdxId;
	@JsonProperty("url")
	private String url;
	@JsonProperty("node_id")
	private String nodeId;

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("spdx_id")
	public String getSpdxId() {
		return spdxId;
	}

	@JsonProperty("spdx_id")
	public void setSpdxId(String spdxId) {
		this.spdxId = spdxId;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("node_id")
	public String getNodeId() {
		return nodeId;
	}

	@JsonProperty("node_id")
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
