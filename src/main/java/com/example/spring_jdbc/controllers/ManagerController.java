package com.example.spring_jdbc.controllers;

import com.example.spring_jdbc.dto.ManagerDto;
import com.example.spring_jdbc.exceptions.ManagerException;
import com.example.spring_jdbc.model.Manager;
import com.example.spring_jdbc.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/manager")
    public ResponseEntity<HttpStatus> createManager(@RequestBody @Valid ManagerDto managerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ManagerException(managerService.buildMessageErrorField(bindingResult));
        }
        log.info("в методе createManager");
        managerService.createManager(managerDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<Manager> findByIdManager(@PathVariable Integer id) {
        log.info("в методе findByIdManager");
        return new ResponseEntity<>(managerService.findByIdManager(id), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/manager")
    public ResponseEntity<HttpStatus> deleteManagerById(@RequestParam Integer id) {
        log.info("в методе deleteManagerById");
        managerService.deleteManager(id);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/manager")
    public List<Manager> findAllManager() {
        log.info("в методе findAllManager");
        return managerService.findAllManager();
    }

    @PutMapping("/manager")
    public ResponseEntity<HttpStatus> updateManagerById(@RequestParam Integer id,
                                                        @RequestBody @Valid ManagerDto managerDto,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ManagerException(managerService.buildMessageErrorField(bindingResult));
        }
        log.info("в методе updateManagerById");
        managerService.updateManager(id, managerDto);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/manager/groupBy/company_name")
    public Map<String, Integer> findCountsGroupByCompany() {
        log.info("в методе findCountsGroupByCompany");
        return managerService.findCountManagersGroupByCompany();
    }

    @GetMapping("/manager/avg/ages")
    public Double findAvgAges() {
        log.info("в методе findAvgAges");
        return managerService.findAvgAges();
    }

    @GetMapping("/manager/find/young_in_company/{company}")
    public ResponseEntity<ManagerDto> findYoungManagerInCompany(@PathVariable String company) {
        log.info("в методе findYoungManagerInCompany");
        return new ResponseEntity<>(managerService.findYoungManagerInCompany(company),
                HttpStatusCode.valueOf(200));
    }
}

