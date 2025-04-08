package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeRepository repository;

    @Override
    public Fee saveBuyFee(Fee fee) {
        fee.setFeeType(true);
        return saveAndExport(fee);
    }

    @Override
    public Fee saveSellFee(Fee fee) {
        fee.setFeeType(false);
        return saveAndExport(fee);
    }

    private Fee saveAndExport(Fee fee) {
        repository.save(fee);
        ObjectMapper mapper = new ObjectMapper();
        try {
            File dir = new File("fees-json");
            if (!dir.exists()) dir.mkdirs();
            mapper.writeValue(new File(dir, "fee_" + fee.getFeeId() + ".json"), fee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fee;
    }

    @Override
    public List<Fee> getAllFees() {
        return repository.findAll();
    }

    @Override
    public Fee getFeeById(String id) {
        Optional<Fee> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public void updateFee(Fee fee) {
        repository.save(fee);
    }

    @Override
    public void deleteFeeById(String id) {
        repository.deleteById(id);
    }
}
