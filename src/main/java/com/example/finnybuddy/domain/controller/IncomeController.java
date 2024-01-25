package com.example.finnybuddy.domain.controller;

import com.example.finnybuddy.core.RestEndpoints;
import com.example.finnybuddy.domain.dto.IncomeRequestDTO;
import com.example.finnybuddy.domain.dto.IncomeResponseDTO;
import com.example.finnybuddy.domain.mapper.IncomeMapper;
import com.example.finnybuddy.domain.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<IncomeResponseDTO> createIncome(@RequestBody IncomeRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(incomeMapper.toDto(incomeService.createIncome(incomeMapper.toEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeResponseDTO> updateIncome(@PathVariable String id, @RequestBody IncomeRequestDTO dto) {
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

}
