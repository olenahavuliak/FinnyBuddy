package com.example.finnybuddy.domain.budget.repository;

import com.example.finnybuddy.domain.budget.model.IncomeSettings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeSettingsRepository extends MongoRepository<IncomeSettings, String> {
}
