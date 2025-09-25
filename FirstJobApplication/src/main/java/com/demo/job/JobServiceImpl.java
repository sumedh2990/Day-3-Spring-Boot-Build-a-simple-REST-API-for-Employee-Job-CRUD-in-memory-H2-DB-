package com.demo.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Job> getAll(int id) {
		// TODO Auto-generated method stub
		return null;
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
