package com.devsuperior.tutresttemplate.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.tutresttemplate.dto.GithubUsersPageDTO;
import com.devsuperior.tutresttemplate.services.ReportService;

@RestController
@RequestMapping(value = "/api")
public class ReportResource {

	@Autowired
	private ReportService service;
	
	@GetMapping(value = "/users")
	public ResponseEntity<GithubUsersPageDTO> findGithubPage(
			@RequestParam(name = "since", defaultValue = "0") Long since) {
		GithubUsersPageDTO dto = service.getGithubUsersPage(since);
		return ResponseEntity.ok(dto);
	}
}
