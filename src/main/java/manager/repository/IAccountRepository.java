package manager.repository;

import manager.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Long> {


    @Query(value = "select p from Account p where p.status=true ")
    List<Account>findAllByStatus();

    Account findAccountByUsername(String username);
    Account findAccountById(Long id);
    @Override
    void deleteById(Long id);
}
