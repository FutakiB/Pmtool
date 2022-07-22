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

public class InMemoryProjectRepository implements JpaRepository<Project, Integer> {

    private final List<Project> db;

    private static int id = 0;

    public InMemoryProjectRepository() {
        db = new ArrayList<>();
    }

    List<Project> getDb() {
        return db;
    }

    @Override
    public List<Project> findAll() {
        return db;
    }

    @Override
    public List<Project> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Project> findAllById(Iterable<Integer> integers) {
        List<Project> p = new ArrayList<>();
        for (Integer i : integers) {
            findById(i).ifPresent(p::add);
        }
        return p;
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(Integer integer) {
        db.removeIf(p -> p.getId() == integer);
    }

    @Override
    public void delete(Project entity) {
        db.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        integers.forEach(i -> db.removeIf(p -> p.getId() == i));
    }

    @Override
    public void deleteAll(Iterable<? extends Project> entities) {
        entities.forEach(db::remove);
    }

    @Override
    public void deleteAll() {
        db.clear();
    }

    @Override
    public <S extends Project> S save(S entity) {
        Optional<Project> project = findById(entity.getId());
        if (project.isPresent()) {
            db.set(db.indexOf(project.get()), entity);
        } else {
            entity.setId(++id);
            db.add(entity);
        }
        return entity;
    }

    @Override
    public <S extends Project> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return (List<S>) db;
    }

    @Override
    public Optional<Project> findById(Integer integer) {
        return db.stream().filter(p -> p.getId() == integer).findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return db.stream().anyMatch(p -> p.getId() == integer);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Project> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Project> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Project> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Project getOne(Integer integer) {
        return getById(integer);
    }

    @Override
    public Project getById(Integer integer) {
        return findById(integer).orElse(null);
    }

    @Override
    public Project getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Project> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Project> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Project> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}

