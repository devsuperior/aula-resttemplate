package com.devsuperior.tutresttemplate.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.tutresttemplate.dto.GithubUsersPageDTO;
import com.devsuperior.tutresttemplate.dto.UserDetailsDTO;
import com.devsuperior.tutresttemplate.dto.UserRepoDTO;
import com.devsuperior.tutresttemplate.services.GithubService;

@RestController
@RequestMapping(value = "/api")
public class GithubResource {

	@Autowired
	private GithubService service;
	
	@GetMapping(value = "/users")
	public ResponseEntity<GithubUsersPageDTO> findGithubPage(
			@RequestParam(name = "since", defaultValue = "0") Long since) {
		GithubUsersPageDTO dto = service.getGithubUsersPage(since);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/users/{username}/details")
	public ResponseEntity<UserDetailsDTO> findUserDetails(@PathVariable String username) {
		UserDetailsDTO dto = service.getUserDetails(username);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/users/{username}/repos")
	public ResponseEntity<List<UserRepoDTO>> findUserRepos(@PathVariable String username) {
		List<UserRepoDTO> dto = service.getUserRepos(username);
		return ResponseEntity.ok(dto);
	}
}
