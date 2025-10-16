package com.demo.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@RestController
public class JobController {

	@Autowired
	private JobService jobService;
		
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}
	
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

	@GetMapping("/getJob")
	public ResponseEntity<List<Job>> getAll(@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction) {
	    return new ResponseEntity<>(jobService.getAll(page, size, sortBy, direction), HttpStatus.OK);
	}
	
	@PostMapping("/addJob")
	public ResponseEntity<String> postData(@RequestBody Job job){
		System.out.println("Job : "+job);

		this.jobService.createJob(job);
		return new ResponseEntity<>("Job Added Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getJob/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id){
		System.out.println("in controller");
		Job job = jobService.getJobById(id);
		if(job != null) {
			return new ResponseEntity<>(job,HttpStatus.OK);
		}
		return new ResponseEntity<>(job,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteJob/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		System.out.println("in controller");
		boolean deleted = jobService.deleteById(id);
		System.out.println("in controller"+deleted);
		if(deleted) {
			return new ResponseEntity<String>("Job Deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateJob/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job){
		boolean updated = jobService.updateJob(id,job);
		if(updated) {
			return new ResponseEntity<String>("Job Updated Succesfully",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadJob(@RequestParam("file") MultipartFile file) {
		try {
			Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
			Files.createDirectories(filePath.getParent());
			Files.write(filePath, file.getBytes());
			return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed!");
		}
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
		Path filePath = Paths.get(uploadDir + filename);
		if (!Files.exists(filePath)) {
			return ResponseEntity.notFound().build();
		}
		Resource resource = new UrlResource(filePath.toUri());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=" + resource.getFilename())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	/*
	 * 
	  -- Insert sample data with explicit IDs 
	 INSERT INTO company (id, desc, name) VALUES
	 (1, 'Leading software development company specializing in enterprise Java applications.', 'TechNova Solutions'),
	 (2, 'Innovative fintech startup focusing on secure digital payments and analytics.', 'FinEdge Labs'), 
	 (3, 'Global IT consultancy offering cloud and AI-based solutions.', 'CloudAxis Consulting'), 
	 (4, 'Product-based company building HR and payroll automation systems.', 'PeopleSync Pvt Ltd'),
	 (5, 'E-commerce platform delivering groceries and daily essentials across India.', 'QuickMart Online');
 		-- Insert sample data with explicit IDs 
	 INSERT INTO job (id, desc, location, max_salary, min_salary, title, companies_id) VALUES 
	 (1, 'Develop and maintain REST APIs using Spring Boot and Hibernate.', 'Pune', '1200000', '800000','Java Developer', 1), 
	 (2, 'Work on frontend applications using React.js and integrate with backend APIs.', 'Bangalore', '1000000', '700000', 'Frontend Developer', 1),
	 (3, 'Build scalable microservices for payment systems using Spring Cloud.', 'Mumbai', '1500000', '1000000', 'Backend Engineer', 2),
	 (4, 'Design database schemas and optimize SQL queries for performance.','Hyderabad', '900000', '600000', 'Database Engineer', 3),
	 (5, 'Maintain and enhance HRMS application using Angular and Spring Boot.', 'Chennai','1100000', '750000', 'Full Stack Developer', 4),
	 (6, 'Develop APIs for product catalog and integrate with payment gateways.', 'Delhi', '950000', '700000', 'Software Engineer', 5);
	 */
}
