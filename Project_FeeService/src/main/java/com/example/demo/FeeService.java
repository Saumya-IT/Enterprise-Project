package com.example.demo;

import java.util.List;

public interface FeeService {
    Fee saveBuyFee(Fee fee);
    Fee saveSellFee(Fee fee);
    List<Fee> getAllFees();
    Fee getFeeById(String id);
    void updateFee(Fee fee);
    void deleteFeeById(String id);
}