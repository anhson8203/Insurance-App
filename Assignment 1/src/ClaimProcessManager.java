import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(long id, Claim claim);
    void delete(long id);
    Claim getOne(long id);
    List<Claim> getAll();
}