package com.example.spring_jdbc.service;

import com.example.spring_jdbc.dto.ManagerDto;
import com.example.spring_jdbc.exceptions.ManagerException;
import com.example.spring_jdbc.exceptions.ManagerNotFoundException;
import com.example.spring_jdbc.model.Manager;
import com.example.spring_jdbc.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerService {
    private final ManagerRepository managerRepository;
    public static final String MESSAGE_ERROR_FIELD = "ошибка некорректные данные поля: %s = %s (%s)";

    public void createManager(ManagerDto managerDto) {
        managerRepository.createManager(new Manager(managerDto.getName(),
                managerDto.getAge(), managerDto.getProject(), managerDto.getCompanyName()));
    }

    public Manager findByIdManager(Integer id) {
        if (managerRepository.findByIdManager(id).isPresent()) {
            return managerRepository.findByIdManager(id).get();
        }
        throw new ManagerNotFoundException("Manager по id = " + id + " не найден");
    }

    public void deleteManager(Integer id) {
        if (managerRepository.findByIdManager(id).isPresent()) {
            managerRepository.deleteManager(id);
        } else {
            throw new ManagerNotFoundException("Manager по id = " + id + " не найден");
        }
    }

    public List<Manager> findAllManager() {
        return managerRepository.findAllManager();
    }

    public void updateManager(Integer id, ManagerDto managerDto) {
        if (managerRepository.findByIdManager(id).isPresent()) {
            managerRepository.updateManager(new Manager(
                    id,
                    managerDto.getName(),
                    managerDto.getAge(),
                    managerDto.getProject(),
                    managerDto.getCompanyName()));
        } else {
            throw new ManagerNotFoundException("Manager по id = " + id + " не найден");
        }
    }

    public String buildMessageErrorField(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError error : fieldErrors) {
            message.append(String.format(
                    MESSAGE_ERROR_FIELD,
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage())).append(" && ");
        }
        return message.toString();
    }

    public Map<String, Integer> findCountManagersGroupByCompany() {
       return managerRepository.findCountsManagersByGroupByCompanyName();
    }

    public Double findAvgAges() {
        return managerRepository.findAvgAges();
    }

    public ManagerDto findYoungManagerInCompany(String company) {
        Manager youngManager = null;
        if (managerRepository.findYoungManagerInCompany(company).isPresent()) {
            youngManager = managerRepository.findYoungManagerInCompany(company).get();
        } else {
            throw new ManagerException("список менеджеров в этой компании пуст!");
        }
        return new ManagerDto(
                youngManager.getName(),
                youngManager.getAge(),
                youngManager.getProject(),
                youngManager.getCompanyName());
    }
}
