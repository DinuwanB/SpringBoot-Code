package com.nod.generic.services;

import com.nod.generic.exceptions.CustomException;
import org.springframework.stereotype.Service;

@Service
public class RiskServiceImpl implements RiskService {

    @Override
    public void takeRisk() {
        throw new CustomException("Something went wrong!");
    }

}
