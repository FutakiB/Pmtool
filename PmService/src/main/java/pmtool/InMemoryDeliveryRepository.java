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

public class InMemoryDeliveryRepository implements JpaRepository<Delivery, Integer> {
    ArrayList<Delivery> deliveries;
    public InMemoryDeliveryRepository(){
        deliveries = new ArrayList<>();
    }

    @Override
    public List<Delivery> findAll() {
        return deliveries;
    }

    @Override
    public List<Delivery> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Delivery> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Delivery> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return deliveries.size();
    }

    @Override
    public void deleteById(Integer integer) {
        deliveries.remove(integer.intValue());

    }

    @Override
    public void delete(Delivery entity) {
        deliveries.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        for (Integer i : integers) {
            deliveries.remove(i.intValue());
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Delivery> entities) {
        for (Delivery d:entities) {
            deliveries.remove(d);
        }
    }

    @Override
    public void deleteAll() {
        deliveries.clear();
    }

    @Override
    public <S extends Delivery> S save(S entity) {
        deliveries.add(entity);
        return entity;
    }

    @Override
    public <S extends Delivery> ArrayList<S> saveAll(Iterable<S> entities) {
        ArrayList<S>saved = new ArrayList<>();
        for (S s:entities) {
            saved.add(s);
            deliveries.add(s);
        }
        return saved;
    }

    @Override
    public Optional<Delivery> findById(Integer integer) {
        return deliveries.stream()
                .filter(deliveries -> integer.equals(deliveries.getId()))
                .findAny();
    }

    @Override
    public boolean existsById(Integer integer) {
        return deliveries.stream().anyMatch(deliveries -> integer.equals(deliveries.getId()));
    }

    @Override
    public void flush() {
        deliveries.clear();
    }

    @Override
    public <S extends Delivery> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Delivery> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Delivery> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Delivery getOne(Integer integer) {

        return (Delivery) deliveries.stream()
                .filter(deliveries -> integer.equals(deliveries.getId()));
    }

    @Override
    public Delivery getById(Integer integer) {

        return getOne(integer);
    }

    @Override
    public Delivery getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Delivery> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Delivery> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Delivery> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Delivery> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Delivery> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Delivery> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Delivery, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
