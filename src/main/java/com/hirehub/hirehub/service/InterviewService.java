package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.InterviewRequest;
import com.hirehub.hirehub.dto.response.InterviewResponse;
import com.hirehub.hirehub.entity.Interview;
import com.hirehub.hirehub.entity.JobApplication;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.InterviewRepository;
import com.hirehub.hirehub.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public InterviewService(InterviewRepository interviewRepository, JobApplicationRepository jobApplicationRepository) {
        this.interviewRepository = interviewRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public InterviewResponse createInterview(InterviewRequest interviewRequest) {

        JobApplication jobApplication = jobApplicationRepository.findById(interviewRequest.getJobApplicationId()).orElseThrow(() ->
                new ResourceNotFoundException("Job Application Not Found"));

        Interview interview = new Interview();

        interview.setJobApplication(jobApplication);
        interview.setInterviewType(interviewRequest.getInterviewType());
        interview.setScheduledAt(interviewRequest.getScheduledAt());
        interview.setMeetingLink(interviewRequest.getMeetingLink());
        interview.setStatus(interviewRequest.getStatus());
        interview.setFeedback(interviewRequest.getFeedback());

        Interview savedinterview =  interviewRepository.save(interview);

        InterviewResponse interviewResponse = new InterviewResponse();

        interviewResponse.setId(interview.getId());
        interviewResponse.setId(savedinterview.getId());
        interviewResponse.setJobApplicationId(interview.getJobApplication().getId());
        interviewResponse.setInterviewType(interview.getInterviewType());
        interviewResponse.setScheduledAt(interview.getScheduledAt());
        interviewResponse.setMeetingLink(interview.getMeetingLink());
        interviewResponse.setStatus(interview.getStatus());
        interviewResponse.setFeedback(interview.getFeedback());

        return interviewResponse;
    }

    public InterviewResponse getInterviewById(Long id) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Interview Not Found  with id: " + id));

        InterviewResponse interviewResponse = new InterviewResponse();

        interviewResponse.setId(interview.getId());
        interviewResponse.setJobApplicationId(interview.getJobApplication().getId());
        interviewResponse.setInterviewType(interview.getInterviewType());
        interviewResponse.setScheduledAt(interview.getScheduledAt());
        interviewResponse.setMeetingLink(interview.getMeetingLink());
        interviewResponse.setStatus(interview.getStatus());
        interviewResponse.setFeedback(interview.getFeedback());

        return interviewResponse;
    }

    public List<InterviewResponse> getAllInterviews() {
        List<Interview> interviews = interviewRepository.findAll();

        List<InterviewResponse> interviewResponseList = new ArrayList<>();

        for (Interview interview : interviews) {
            InterviewResponse interviewResponse = new InterviewResponse();

            interviewResponse.setId(interview.getId());
            interviewResponse.setJobApplicationId(interview.getJobApplication().getId());
            interviewResponse.setInterviewType(interview.getInterviewType());
            interviewResponse.setScheduledAt(interview.getScheduledAt());
            interviewResponse.setMeetingLink(interview.getMeetingLink());
            interviewResponse.setStatus(interview.getStatus());
            interviewResponse.setFeedback(interview.getFeedback());

            interviewResponseList.add(interviewResponse);
        }
        return interviewResponseList;
    }

    public InterviewResponse updateInterview(InterviewRequest interviewRequest, Long id) {

        Interview interview = interviewRepository.findById(id).orElseThrow(()
            -> new ResourceNotFoundException("Interview Not Found with id: " + id));

        JobApplication jobApplication = jobApplicationRepository.findById(interviewRequest.getJobApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Job Application Not Found with id: " + interviewRequest.getJobApplicationId()));

        interview.setJobApplication(jobApplication);
        interview.setInterviewType(interviewRequest.getInterviewType());
        interview.setScheduledAt(interview.getScheduledAt());
        interview.setMeetingLink(interview.getMeetingLink());
        interview.setStatus(interview.getStatus());
        interview.setFeedback(interview.getFeedback());

        Interview updatedInterview = interviewRepository.save(interview);

        InterviewResponse interviewResponse = new InterviewResponse();

        interviewResponse.setId(updatedInterview.getId());
        interviewResponse.setJobApplicationId(updatedInterview.getJobApplication().getId());
        interviewResponse.setInterviewType(updatedInterview.getInterviewType());
        interviewResponse.setScheduledAt(updatedInterview.getScheduledAt());
        interviewResponse.setMeetingLink(updatedInterview.getMeetingLink());
        interviewResponse.setStatus(updatedInterview.getStatus());
        interviewResponse.setFeedback(updatedInterview.getFeedback());

        return interviewResponse;
    }

    public void deleteInterview(Long id) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Interview Not Found with id: " + id));

        interviewRepository.delete(interview);
    }
}
