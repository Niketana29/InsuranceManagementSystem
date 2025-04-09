package com.insurance.dao;

import java.util.List;

import com.insurance.entity.Policy;
import com.insurance.exception.PolicyNotFoundException;


public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException;;
    List<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);

}
