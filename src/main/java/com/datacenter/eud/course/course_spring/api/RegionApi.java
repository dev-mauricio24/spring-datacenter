package com.datacenter.eud.course.course_spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.eud.course.course_spring.services.RegionService;

@RestController
@RequestMapping("/api/region")
public class RegionApi {

    private final RegionService service;

    // @Autowired
    public RegionApi(RegionService service) {
        this.service = service;
    }
    
    @GetMapping
    public void getAll() {
        service.execute();
    }

}
