package com.example.finnybuddy.domain.budget.controller;

import com.example.finnybuddy.core.constants.RestEndpoints;
import com.example.finnybuddy.domain.budget.dto.income.IncomeCalculationDTO;
import com.example.finnybuddy.domain.budget.dto.income.IncomeRequestDTO;
import com.example.finnybuddy.domain.budget.dto.income.IncomeResponseDTO;
import com.example.finnybuddy.domain.budget.dto.income.IncomeSettingsDTO;
import com.example.finnybuddy.domain.budget.mapper.IncomeMapper;
import com.example.finnybuddy.domain.budget.service.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(RestEndpoints.INCOME_BASE_URL)
public class IncomeController {
    private final IncomeService incomeService;
    private final IncomeMapper incomeMapper;

    @GetMapping("/all")
    public ResponseEntity<List<IncomeResponseDTO>> getAllIncomes() {
        return ResponseEntity.ok(incomeMapper.toListDto(incomeService.getAllIncomes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponseDTO> getIncomeById(@PathVariable String id) {
        return ResponseEntity.ok(incomeMapper.toDto(incomeService.getIncomeById(id)));
    }

    @PostMapping
    public ResponseEntity<IncomeResponseDTO> createIncome(@Valid @RequestBody IncomeRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(incomeMapper.toDto(incomeService.createIncome(incomeMapper.toEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeResponseDTO> updateIncome(@PathVariable String id, @Valid @RequestBody IncomeRequestDTO dto) {
        return ResponseEntity.ok(incomeMapper.toDto(incomeService.updateIncome(id, incomeMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IncomeResponseDTO> deleteIncome(@PathVariable String id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<IncomeResponseDTO>> getAllIncomesByUser(@PathVariable String userId) {
        return ResponseEntity.ok(incomeMapper.toListDto(incomeService.getAllIncomesByUserId(userId)));
    }

    @GetMapping("/{userId}/calculate-income")
    public ResponseEntity<IncomeCalculationDTO> calculateIncomesDueDate(@PathVariable String userId, @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(incomeService.calculateIncomeDueDate(userId, date));
    }

    @GetMapping("/income-settings")
    public ResponseEntity<IncomeSettingsDTO> getIncomeSettings(){
        return ResponseEntity.ok(incomeMapper.toDto(incomeService.getIncomeSettings()));
    }

    @PutMapping("/income-settings")
    public ResponseEntity<IncomeSettingsDTO> updateIncomeSettings(@Valid @RequestBody IncomeSettingsDTO dto){
        return ResponseEntity.ok(incomeMapper.toDto(incomeService.updateIncomeSettings(incomeMapper.toEntity(dto))));
    }

}

