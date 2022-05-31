//package vu.lt.services;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Default;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceUnit;
//import javax.persistence.SynchronizationType;
//import javax.transaction.TransactionScoped;
//
//@ApplicationScoped
//public class Resources2 {
////    The entity manager implements the API and encapsulates
////    all of them within a single interface.
////    Entity manager is used to read, delete and write an entity.
//
//    @PersistenceUnit
//    private EntityManagerFactory emf;
//
//    @Produces
//    @TransactionScoped
//    private EntityManager createJTAEntityManager() {
//
//        return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
//    }
//
//    private void closeDefaultEntityManager(@Disposes @Default EntityManager em) {
//        em.close();
//    }
//}