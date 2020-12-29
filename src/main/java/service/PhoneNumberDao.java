package service;

import entities.PhoneNumber;
import javax.persistence.EntityManager;

public class PhoneNumberDao extends EntityDao<PhoneNumber, Integer> {

    public PhoneNumberDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<PhoneNumber> getEntityClass() {
        return PhoneNumber.class;
    }
}
