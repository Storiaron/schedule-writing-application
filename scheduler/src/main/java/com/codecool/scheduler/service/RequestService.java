package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.RequestDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.model.Request;
import com.codecool.scheduler.repository.EmployeeRepository;
import com.codecool.scheduler.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public RequestService(RequestRepository requestRepository, EmployeeRepository employeeRepository) {
        this.requestRepository = requestRepository;
        this.employeeRepository = employeeRepository;
    }
    @Transactional
    public void addRequest(RequestDTO requestDTO){
        Employee employee = employeeRepository.findByName(requestDTO.getEmployeeName());
        Request request = requestRepository.findByDateAndEmployee(requestDTO.getDate(), employee);
        if(request == null){
            request = new Request();
            request.setEmployee(employee);
            request.setDate(requestDTO.getDate());
            requestRepository.save(request);
            employee.addRequest(request);
            employeeRepository.save(employee);
        }
    }

    public void handleRequests(List<RequestDTO> requestDTOList){
        for(RequestDTO requestDTO : requestDTOList){
            addRequest(requestDTO);
        }
    }
}
