package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.RequestDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.model.Request;
import com.codecool.scheduler.repository.EmployeeRepository;
import com.codecool.scheduler.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
       Request request = new Request();
       request.setEmployee(employee);
       request.setDate(requestDTO.getDate());
       employee.addRequest(request);
       requestRepository.save(request);
       employeeRepository.save(employee);

    }
}
