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
import com.devsuperior.tutresttemplate.dto.UserDetailsDTO;
import com.devsuperior.tutresttemplate.dto.UserRepoDTO;

@Service
public class GithubService {

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

	public UserDetailsDTO getUserDetails(String username) {
		ResponseEntity<UserDetailsDTO> result = restTemplate.getForEntity("https://api.github.com/users/" + username, UserDetailsDTO.class);				
		return result.getBody();
	}

	public List<UserRepoDTO> getUserRepos(String username) {
		
		ParameterizedTypeReference<List<UserRepoDTO>> responseType = new ParameterizedTypeReference<List<UserRepoDTO>>() { };
		
		ResponseEntity<List<UserRepoDTO>> result = restTemplate.exchange("https://api.github.com/users/" + username + "/repos", HttpMethod.GET, null, responseType);

		return result.getBody();
	}	
}
