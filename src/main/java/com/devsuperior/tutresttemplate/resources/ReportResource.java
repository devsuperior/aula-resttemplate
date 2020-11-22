package com.devsuperior.tutresttemplate.resources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.tutresttemplate.services.ReportService;

@RestController
@RequestMapping(value = "/report")
public class ReportResource {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getReport() {
		Map<String, Object> obj = reportService.getReport(request.getHeader("Authorization"));
		return ResponseEntity.ok(obj);
	}
}
