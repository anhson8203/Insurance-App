package UI;

import java.util.ArrayList;

import informations.Claim;

public interface ClaimProcessManager {
    void addClaim(Claim claim);

    void updateClaim(Claim claim);

    void deleteClaim(String claimId);

    Claim getClaim(String claimId);

    ArrayList<Claim> getAllClaims();
}