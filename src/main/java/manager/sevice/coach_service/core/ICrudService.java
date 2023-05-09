package manager.sevice.coach_service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICrudService<E> {
    List<E> findAll();
    Page<E> displayAll(Pageable pageable);
    E save(E e);
    E  findById(Long id);
    void delete(Long id);
}
