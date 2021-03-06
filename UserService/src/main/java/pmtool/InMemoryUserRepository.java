package pmtool;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryUserRepository implements JpaRepository<User, Integer>{
    List<User> db;
    public InMemoryUserRepository(){
        db = new ArrayList<>();
    }
    @Override
    public List<User> findAll() {
        return db;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Integer> integers) {
        List<User> u = new ArrayList<>();

        for (Integer i : integers) {
            findById(i).ifPresent(u::add);
        }

        return u;
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(Integer id) {
        db.removeIf((user)-> Objects.equals(user.getId(), id));
    }

    @Override
    public void delete(User entity) {
        db.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        for (Integer i:integers) {
            deleteById(i);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        for (User u:entities) {
            delete(u);
        }
    }

    @Override
    public void deleteAll() {
        db.clear();
    }

    @Override
    public <S extends User> S save(S entity) {
        Optional<User> user = findById(entity.getId());
        if (user.isPresent()) {
            db.set(db.indexOf(user.get()), entity);
        } else {
            db.add(entity);
        }
        return entity;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        List<S> u = new ArrayList<>();
        for (S s:entities) {
            u.add(save(s));
        }
        return u;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return db.stream().filter((user) -> user.getId().equals(integer)).findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return db.stream().anyMatch(users -> integer.equals(users.getId()));
    }

    @Override
    public void flush() {
        db.clear();
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Integer integer) {
        return null;
    }

    @Override
    public User getById(Integer integer) {
        return null;
    }

    @Override
    public User getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
