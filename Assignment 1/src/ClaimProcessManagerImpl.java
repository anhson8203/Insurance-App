import java.util.ArrayList;
import java.util.List;

public class ClaimProcessManagerImpl implements ClaimProcessManager {
    private List<Claim> claims;

    public ClaimProcessManagerImpl() {
        claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(long id, Claim claim) {
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public Claim getOne(long id) {
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }
}