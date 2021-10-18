package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.dto.classes.ClassDto;
import com.falterziu.flightdata.service.ClassService;
import com.falterziu.flightdata.util.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Routes.CLASSES)
public class ClassController {


    private final ClassService classService;

    public ClassController(ClassService classService) {this.classService = classService;}

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<List<ClassDto>> getAll() {
        return ResponseEntity.ok(classService.getAllClasses());
    }
}
