package com.devsuperior.tutresttemplate.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.tutresttemplate.dto.ProductDTO;
import com.devsuperior.tutresttemplate.services.ReportService;

@RestController
@RequestMapping(value = "/report")
public class ReportResource {

	@Autowired
	private ReportService service;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		
		List<ProductDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		String token = request.getHeader("Authorization");
		dto = service.insert(dto, token);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		String token = request.getHeader("Authorization");
		dto = service.update(id, dto, token);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		String token = request.getHeader("Authorization");
		service.delete(id, token);
		return ResponseEntity.noContent().build();
	}
}
