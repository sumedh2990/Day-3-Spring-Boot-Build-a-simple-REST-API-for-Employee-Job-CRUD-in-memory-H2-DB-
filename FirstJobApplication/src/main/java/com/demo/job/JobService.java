package com.demo.job;

import java.util.List;

public interface JobService {

	public List<Job> getAll(int id, int size, String sortBy, String direction);
	public List<Job> getJob();
	public void createJob(Job job);
	public Job getJobById(Long id);
	public boolean deleteById(Long id);
	public boolean updateJob(Long id, Job job);
}
