package com.demo.job;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

	@Autowired
	private JobService jobService;
		
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping("/getJob")
	public ResponseEntity<List<Job>> getAll(){
		return new ResponseEntity<>(this.jobService.getJob(),HttpStatus.OK);
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
}
