package pmtool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDeliveryRepositoryTest {
    InMemoryDeliveryRepository repository = new InMemoryDeliveryRepository();
    static ArrayList<Delivery> deliveries = new ArrayList<>();
    @BeforeAll
    static void beforeAll(){
        boolean deliveryType = true;
        for (int i = 0; i < 5; i++) {
            if (deliveryType) {
                deliveries.add(new Delivery(i, "Test " + i,DeliveryType.ARTIFACT,1));
            }else {
                deliveries.add(new Delivery(i, "Test " + i,DeliveryType.DOCUMENT,1));
            }
            deliveryType = !deliveryType;
        }
    }

    @BeforeEach
    void beforeEach(){
        repository.deleteAll();
        repository.saveAll(deliveries);
    }

    @Test
    void testfindAll() {
        ArrayList<Delivery> results;
        results = (ArrayList<Delivery>) repository.findAll();
        assertEquals(results.size(), repository.count());
        for (int i = 0; i < deliveries.size(); i++) {
            assertEquals(deliveries.get(i).getId(), results.get(i).getId());
            assertEquals(deliveries.get(i).getName(), results.get(i).getName());
            assertEquals(deliveries.get(i).getType(), results.get(i).getType());
        }

    }


    @Test
    void testcount() {
        assertEquals(repository.count(), deliveries.size());
    }

    @Test
    void testdeleteById() {
        Random rnd = new Random();
        Integer n = rnd.nextInt(deliveries.size());
        repository.deleteById(n);
        assertEquals(deliveries.size(), repository.deliveries.size() + 1);
    }

    @Test
    void testdeleteEntity() {
        Random rnd = new Random();
        int n = rnd.nextInt(deliveries.size());
        Delivery d = deliveries.get(n);
        repository.delete(d);
        assertEquals(deliveries.size(), repository.deliveries.size() + 1);

    }

    @Test
    void testdeleteAllById() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(deliveries.size()-1);
        integers.add(deliveries.size()-2);
        repository.deleteAllById(integers);
        assertEquals(deliveries.size(), repository.deliveries.size() + 2);


    }


    @Test
    void testdeleteAll() {
        repository.deleteAll();
        assertEquals(0, repository.deliveries.size());
    }

    @Test
    void testsave() {
        Delivery d = new Delivery(100, "SaveTest", DeliveryType.ARTIFACT, 1);
        Delivery result = repository.save(d);
        assertTrue(repository.deliveries.contains(result));
        assertTrue(repository.deliveries.contains(d));


    }

    @Test
    void testsaveAll() {
        Delivery d1 = new Delivery(100, "SaveTest1", DeliveryType.ARTIFACT, 1);
        Delivery d2 = new Delivery(101, "SaveTest2", DeliveryType.DOCUMENT, 1);
        ArrayList<Delivery> del = new ArrayList<>();
        ArrayList<Delivery> result;
        del.add(d1);

        del.add(d2);
        result = repository.saveAll(del);
        assertTrue(repository.deliveries.containsAll(result));
        assertTrue(repository.deliveries.containsAll(del));
    }

    @Test
    void testfindById() {

        assertNotNull(repository.findById(2));
        assertEquals(repository.findById(200), Optional.empty());
    }

    @Test
    void testexistsById() {
        assertTrue(repository.existsById(2));
        assertFalse(repository.existsById(200));
    }

    @Test
    void testflush() {
        repository.flush();
        assertEquals(repository.deliveries.size(),0);
    }

    @Test
    void saveAndFlush() {
    }

    @Test
    void saveAllAndFlush() {
    }

    @Test
    void deleteAllInBatch() {
    }

    @Test
    void deleteAllByIdInBatch() {
    }

    @Test
    void testDeleteAllInBatch() {
    }

    @Test
    void getOne() {

    }

    @Test
    void getById() {

    }

    @Test
    void getReferenceById() {
    }
}