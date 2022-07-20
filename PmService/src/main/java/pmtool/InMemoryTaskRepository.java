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

public class InMemoryTaskRepository implements JpaRepository<Task, Integer> {
    private List<Task> db;
    public InMemoryTaskRepository() {
        db = new ArrayList<Task>();
    }

    @Override
    public List<Task> findAll() {
        return db;
    }

    @Override
    public List<Task> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Task> findAllById(Iterable<Integer> ids) {
        List<Task> results = new ArrayList<Task>();

        for (Integer id : ids) {
            findById(id).ifPresent(results::add);
        }

        return results;
    }

    @Override
    public long count() { return db.size(); }

    @Override
    public void deleteById(Integer id) {
        db.removeIf((Task)->Task.getId()==id);
    }

    @Override
    public void delete(Task Task) {
        db.remove(Task);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        ids.forEach(i -> db.removeIf(m -> m.getId() == i));
    }

    @Override
    public void deleteAll(Iterable<? extends Task> entities) {
        for (Task m : entities) {
            db.remove(m);
        }
    }

    @Override
    public void deleteAll() {
        if(db!=null) db.clear();
    }

    @Override
    public <S extends Task> S save(S entity) {
        Optional Task=findById(entity.getId());
        if (Task.isPresent()){
            db.set(db.indexOf(Task.get()),entity);
        }else{
            entity.setId(db.size());
            db.add(entity);
        }
        return entity;
    }

    @Override
    public <S extends Task> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return db.stream().filter((Task)->Task.getId()==id).findFirst();
    }

    @Override
    public boolean existsById(Integer id) {
        return db.stream().filter((Task)->Task.getId()==id).count()>0;
    }

    @Override
    public void flush() {
        if(db!=null) db.clear();
    }

    @Override
    public <S extends Task> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Task> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Task> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> id) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Task getOne(Integer id) {
        return findById(id).orElse(null);
    }

    @Override
    public Task getById(Integer id) {
        return findById(id).orElse(null);
    }

    @Override
    public Task getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Task> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Task> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Task> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Task> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Task> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Task> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Task, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
