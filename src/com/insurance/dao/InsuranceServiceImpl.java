package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.insurance.entity.Policy;
import com.insurance.exception.PolicyNotFoundException;
import com.insurance.util.DBConnection;

public class InsuranceServiceImpl implements IPolicyService {

    @Override
    public boolean createPolicy(Policy policy) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO policy (policyId, policyName, coverageAmount, premium, duration) VALUES (?, ?, ?, ?, ?)")) {

            ps.setInt(1, policy.getPolicyId());
            ps.setString(2, policy.getPolicyName());
            ps.setDouble(3, policy.getCoverageAmount());
            ps.setDouble(4, policy.getPremium());
            ps.setInt(5, policy.getDuration());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        Policy policy = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM policy WHERE policyId = ?")) {

            ps.setInt(1, policyId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                policy = new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("coverageAmount"),
                    rs.getDouble("premium"),
                    rs.getInt("duration")
                );
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new PolicyNotFoundException("Error retrieving policy: " + e.getMessage());
        }

        return policy;
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM policy");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Policy policy = new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("coverageAmount"),
                    rs.getDouble("premium"),
                    rs.getInt("duration")
                );
                policies.add(policy);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE policy SET policyName = ?, coverageAmount = ?, premium = ?, duration = ? WHERE policyId = ?")) {

            ps.setString(1, policy.getPolicyName());
            ps.setDouble(2, policy.getCoverageAmount());
            ps.setDouble(3, policy.getPremium());
            ps.setInt(4, policy.getDuration());
            ps.setInt(5, policy.getPolicyId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePolicy(int policyId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM policy WHERE policyId = ?")) {

            ps.setInt(1, policyId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
