package com.example.finnybuddy.domain.budget.repository;

import com.example.finnybuddy.domain.budget.model.IncomeSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncomeSettingsRepository extends MongoRepository<IncomeSettings, String> {
}
