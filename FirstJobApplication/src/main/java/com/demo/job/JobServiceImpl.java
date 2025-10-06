package com.demo.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class JobServiceImpl implements JobService{

	//private List<Job> jobs = new ArrayList<>();
	private Long next = 1L;
	//JPA implementation
	@Autowired
	JobRepository jobRepository;
	
	
	public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> getAll(int page, int size, String sortBy, String direction) {

	    // Step 1️⃣: Print the inputs received from the controller
	    System.out.println("🟢 Pagination & Sorting Request Received:");
	    System.out.println("   👉 Page Number : " + page);
	    System.out.println("   👉 Page Size   : " + size);
	    System.out.println("   👉 Sort By     : " + sortBy);
	    System.out.println("   👉 Direction   : " + direction);

	    // Step 2️⃣: Create Sort object based on direction
	    Sort sort = direction.equalsIgnoreCase("desc") ?
	            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    System.out.println("📦 Sort Object Created: " + sort);

	    // Step 3️⃣: Create Pageable object using page, size, and sort
	    Pageable pageable = PageRequest.of(page, size, sort);
	    System.out.println("📄 Pageable Object: " + pageable);

	    // Step 4️⃣: Fetch data using repository
	    Page<Job> jobPage = jobRepository.findAll(pageable);

	    // Step 5️⃣: Print metadata (useful for debugging)
	    System.out.println("✅ Total Elements (All Records): " + jobPage.getTotalElements());
	    System.out.println("✅ Total Pages: " + jobPage.getTotalPages());
	    System.out.println("✅ Current Page Number: " + jobPage.getNumber());
	    System.out.println("✅ Number of Records in this Page: " + jobPage.getNumberOfElements());

	    // Step 6️⃣: Print data for visual verification
	    System.out.println("📋 Records Returned in This Page:");
	    jobPage.getContent().forEach(job -> System.out.println("   - " + job));

	    // Step 7️⃣: Return paginated list
	    return jobPage.getContent();
	}

	@Override
	public List<Job> getJob() {
		// TODO Auto-generated method stub
		//return jobs;
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
//		job.setId(next++);
//		jobs.add(job);
		System.out.println("Job1 : "+job);
		job.setId(next++);
		System.out.println("next : "+job);
		jobRepository.save(job);
		
	}

	@Override
	public Job getJobById(Long id) {
//		for (Job job : jobs) {
//			System.out.println("in for");
//			if(job.getId().equals(id)) {
//				System.out.println("in if");
//				return job;
//			}
//		}
//		return new Job(1L, "TestJob", "Test Desc", "0", "0", "def");
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteById(Long id) {
//		Iterator<Job> iterator = jobs.iterator();
//		while(iterator.hasNext()) {
//			Job job = iterator.next();
//			if(job.getId().equals(id)) {
//				iterator.remove();
//				return true;
//			}
//		}
//		return false;
		try {
			jobRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} 
	}

	@Override
	public boolean updateJob(Long id, Job updateJob) {
//		for (Job jobs : jobs) {
//			if(jobs.id.equals(id)) {
////				jobs.setId(updateJob.getId());
//				jobs.setDesc(updateJob.getDesc());
//				jobs.setTitle(updateJob.getTitle());
//				jobs.setMaxSalary(updateJob.getMaxSalary());
//				jobs.setMinSalary(updateJob.getMinSalary());
//				jobs.setLocation(updateJob.getLocation());
//				return true;
//			}
//		}
//		return false;
		
		Optional<Job> jobOptional = jobRepository.findById(id);
		if(jobOptional.isPresent()) {
			Job jobs = jobOptional.get();
//			jobs.setId(updateJob.getId());
			jobs.setDesc(updateJob.getDesc());
			jobs.setTitle(updateJob.getTitle());
			jobs.setMaxSalary(updateJob.getMaxSalary());
			jobs.setMinSalary(updateJob.getMinSalary());
			jobs.setLocation(updateJob.getLocation());
			jobRepository.save(jobs);
			return true;
		}
		return false;
	}
	
	

}
