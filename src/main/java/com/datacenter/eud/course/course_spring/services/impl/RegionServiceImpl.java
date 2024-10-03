package com.datacenter.eud.course.course_spring.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.persistence.repositories.RegionRepository;
import com.datacenter.eud.course.course_spring.services.RegionService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    @NonNull
    private final RegionRepository repository;

    @Override
    public void execute() {
        this.repository.findAll().forEach(reg -> {
            System.out.println("region -> " + reg.getId() + " " + reg.getName());
        });

        // Count
        System.out.println("Cantidad de registros Regions ==> " + this.repository.count());
    }

}
