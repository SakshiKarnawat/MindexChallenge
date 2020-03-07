package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompensationServiceImpl implements CompensationService {
    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation createCompensation(Compensation compensation) {
        String id = compensation.getEmployee().getEmployeeId();
        System.out.println(id);
        Employee e =employeeRepository.findByEmployeeId(id);
        compensation.setEmployee(e);
        compensationRepository.save(compensation);
        return compensation;
    }

    @Override
    public Compensation readCompensation(String id) {
        int salary=0;
        Compensation c = compensationRepository.findByEmployee_EmployeeId(id);
        try {
            salary=c.getSalary();
        } catch(NullPointerException e){
            System.out.println("Information not available");
        }

        return c;
    }
}
