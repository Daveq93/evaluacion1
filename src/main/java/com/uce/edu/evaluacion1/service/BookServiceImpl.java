package com.uce.edu.evaluacion1.service;

import com.uce.edu.evaluacion1.modelo.Book;
import com.uce.edu.evaluacion1.repo.PersistenceManager;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class BookServiceImpl implements IBookService{

    @PersistenceContext(unitName = "booksPU")
    private EntityManager em;

    public BookServiceImpl() {
        EntityManagerFactory entityManagerFactory = PersistenceManager.getEntityManagerFactory();
        this.em = entityManagerFactory.createEntityManager();
    }

    @Override
    public Book findById(Integer id) {
        return this.em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery myQ = this.em.createQuery("SELECT b FROM Book b",Book.class);
        return myQ.getResultList();
    }

    @Override
    public Book save(Book book) {
        this.em.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        return this.em.merge(book);
    }

    @Override
    public Integer delete(Book book) {
        int flag=0;
        try{
            Book b = this.findById(book.getId());
            this.em.remove(b);
            flag=1;
        }catch (NoResultException ex){
            throw new NoResultException("No se puede eliminar el libro");
        }

        return flag;
    }
}
