package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.JobRequest;
import com.hirehub.hirehub.dto.response.JobResponse;
import com.hirehub.hirehub.entity.Company;
import com.hirehub.hirehub.entity.Job;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.CompanyRepository;
import com.hirehub.hirehub.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public JobResponse createJob(JobRequest jobRequest) {

        Company company = companyRepository.findById(jobRequest.getCompanyId()).orElseThrow(() ->
                new ResourceNotFoundException("Company ID: " + jobRequest.getCompanyId()));

        Job job = new Job();

        job.setCompany(company);
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setSalaryMin(jobRequest.getSalaryMin());
        job.setSalaryMax(jobRequest.getSalaryMax());
        job.setExperienceRequired(jobRequest.getExperienceRequired());
        job.setEmploymentType(jobRequest.getEmploymentType());
        job.setStatus(jobRequest.getStatus());

        Job savedJob = jobRepository.save(job);

        JobResponse jobResponse = new JobResponse();

        jobResponse.setId(savedJob.getId());
        jobResponse.setCompanyId(savedJob.getCompany().getId());
        jobResponse.setTitle(savedJob.getTitle());
        jobResponse.setDescription(savedJob.getDescription());
        jobResponse.setLocation(savedJob.getLocation());
        jobResponse.setSalaryMin(savedJob.getSalaryMin());
        jobResponse.setSalaryMax(savedJob.getSalaryMax());
        jobResponse.setExperienceRequired(savedJob.getExperienceRequired());
        jobResponse.setEmploymentType(savedJob.getEmploymentType());
        jobResponse.setStatus(savedJob.getStatus());

        return jobResponse;
    }

    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Job not found with id: " + id));

        JobResponse jobResponse = new JobResponse();

        jobResponse.setId(job.getId());
        jobResponse.setCompanyId(job.getCompany().getId());
        jobResponse.setTitle(job.getTitle());
        jobResponse.setDescription(job.getDescription());
        jobResponse.setLocation(job.getLocation());
        jobResponse.setSalaryMin(job.getSalaryMin());
        jobResponse.setSalaryMax(job.getSalaryMax());
        jobResponse.setExperienceRequired(job.getExperienceRequired());
        jobResponse.setEmploymentType(job.getEmploymentType());
        jobResponse.setStatus(job.getStatus());

        return jobResponse;
    }

    public List<JobResponse> getAllJob() {
        List<Job> jobs = jobRepository.findAll();

        List<JobResponse> jobResponseList = new ArrayList<>();

        for (Job job1 : jobs) {
            JobResponse jobResponse = new JobResponse();

            jobResponse.setId(job1.getId());
            jobResponse.setCompanyId(job1.getCompany().getId());
            jobResponse.setTitle(job1.getTitle());
            jobResponse.setDescription(job1.getDescription());
            jobResponse.setLocation(job1.getLocation());
            jobResponse.setSalaryMin(job1.getSalaryMin());
            jobResponse.setSalaryMax(job1.getSalaryMax());
            jobResponse.setExperienceRequired(job1.getExperienceRequired());
            jobResponse.setEmploymentType(job1.getEmploymentType());
            jobResponse.setStatus(job1.getStatus());

            jobResponseList.add(jobResponse);
        }
        return jobResponseList;
    }

    public JobResponse updateJob(JobRequest jobRequest,Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Job not found with id: " + id));

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setSalaryMin(jobRequest.getSalaryMin());
        job.setSalaryMax(jobRequest.getSalaryMax());
        job.setExperienceRequired(jobRequest.getExperienceRequired());
        job.setEmploymentType(jobRequest.getEmploymentType());
        job.setStatus(jobRequest.getStatus());

        Job savedJob = jobRepository.save(job);

        JobResponse jobResponse = new JobResponse();

        jobResponse.setId(savedJob.getId());
        jobResponse.setCompanyId(savedJob.getCompany().getId());
        jobResponse.setTitle(savedJob.getTitle());
        jobResponse.setDescription(savedJob.getDescription());
        jobResponse.setLocation(savedJob.getLocation());
        jobResponse.setSalaryMin(savedJob.getSalaryMin());
        jobResponse.setSalaryMax(savedJob.getSalaryMax());
        jobResponse.setExperienceRequired(savedJob.getExperienceRequired());
        jobResponse.setEmploymentType(savedJob.getEmploymentType());
        jobResponse.setStatus(savedJob.getStatus());
        return jobResponse;
    }

    public void deleteJobById(Long id) {
       Job job = jobRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("Job not found with id: " + id));
       jobRepository.delete(job);
    }
}
