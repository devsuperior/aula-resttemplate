package com.devsuperior.tutresttemplate.dto;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersPageDTO {

	private List<GithubUserDTO> content = new ArrayList<>();
	private String next;
	
	public List<GithubUserDTO> getContent() {
		return content;
	}
	public void setContent(List<GithubUserDTO> content) {
		this.content = content;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
}
