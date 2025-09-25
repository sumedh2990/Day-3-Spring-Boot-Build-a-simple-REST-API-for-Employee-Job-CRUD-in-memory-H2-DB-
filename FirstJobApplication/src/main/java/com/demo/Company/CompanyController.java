package com.demo.Company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping("/getCompanies")
	public ResponseEntity<List<Company>> getAllCompanies(){
		return new ResponseEntity<>(this.companyService.getAllCompanies(),HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company,@PathVariable Long id) {
		companyService.updateCompany(company, id);
		return new ResponseEntity<>("Company updated successfully",HttpStatus.OK);
		
	}
	
	@PostMapping("/createCompany")
	public ResponseEntity<String> addCompany(@RequestBody Company company){
		companyService.createCompany(company);
		return new ResponseEntity<>("Company Saved Successfully",HttpStatus.OK);
	}
	
	
}
