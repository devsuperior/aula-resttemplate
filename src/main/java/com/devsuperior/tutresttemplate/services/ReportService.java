package com.devsuperior.tutresttemplate.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.tutresttemplate.dto.GithubUserDTO;
import com.devsuperior.tutresttemplate.dto.GithubUsersPageDTO;

@Service
public class ReportService {

	@Autowired
	private RestTemplate restTemplate;

	public GithubUsersPageDTO getGithubUsersPage(Long since) {
		ParameterizedTypeReference<List<GithubUserDTO>> responseType = new ParameterizedTypeReference<List<GithubUserDTO>>() { };

		ResponseEntity<List<GithubUserDTO>> result = restTemplate.exchange("https://api.github.com/users?since=" + since, HttpMethod.GET, null, responseType);
		
		GithubUsersPageDTO dto = new GithubUsersPageDTO();
		dto.setContent(result.getBody());
		long newSince = result.getBody().get(result.getBody().size()-1).getId();
		dto.setNext("http://localhost:8080/api/users?since=" + newSince);
		
		return dto;		
	}	
}
