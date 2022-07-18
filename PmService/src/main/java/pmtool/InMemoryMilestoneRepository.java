package pmtool;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryMilestoneRepository implements JpaRepository<Milestone, Integer> {
    private List<Milestone> db;

    public InMemoryMilestoneRepository() {
        new ArrayList<Milestone>();
    }

    @Override
    public List<Milestone> findAll() {
        return db;
    }

    @Override
    public List<Milestone> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Milestone> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Milestone> findAllById(Iterable<Integer> ids) {
            List<Milestone> results = new ArrayList<Milestone>();

            for (Integer id : ids) {
                findById(id).ifPresent(results::add);
            }

            return results;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        db.removeIf((milestone)->milestone.getId()==id);
    }

    @Override
    public void delete(Milestone milestone) {
        db.remove(milestone);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        for (Integer id : ids) {
            db.remove(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Milestone> entities) {
        for (Milestone m : entities) {
            db.remove(m);
        }
    }

    @Override
    public void deleteAll() {
        db.clear();
    }

    @Override
    public <S extends Milestone> S save(S entity) {
        entity.setId(db.size());
        db.add(entity);
        return entity;
    }

    @Override
    public <S extends Milestone> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            entity.setId(db.size());
            db.add(entity);
        }

        return result;
    }

    @Override
    public Optional<Milestone> findById(Integer id) {
        return db.stream().filter((milestone)->milestone.getId()==id).findFirst();
    }

    @Override
    public boolean existsById(Integer id) {
        return db.stream().filter((milestone)->milestone.getId()==id).count()>0;
    }

    @Override
    public void flush() {
        db.clear();
    }

    @Override
    public <S extends Milestone> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Milestone> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Milestone> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> id) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Milestone getOne(Integer id) {
        return getById(id);
    }

    @Override
    public Milestone getById(Integer id) {
        return null;
    }

    @Override
    public Milestone getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Milestone> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Milestone> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Milestone> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Milestone> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Milestone> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Milestone> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Milestone, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
