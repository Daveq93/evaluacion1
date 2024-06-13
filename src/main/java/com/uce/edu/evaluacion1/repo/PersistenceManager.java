package com.uce.edu.evaluacion1.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {

private static final EntityManagerFactory entityManagerFactory;

static {
    entityManagerFactory = Persistence.createEntityManagerFactory("booksPU");
}


public static EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
}
}
