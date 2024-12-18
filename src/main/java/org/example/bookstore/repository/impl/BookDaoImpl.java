package org.example.bookstore.repository.impl;

import java.util.List;
import java.util.Optional;
import org.example.bookstore.exception.DataProcessingException;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot save book", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Book";
            return session.createQuery(hql, Book.class).list();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot save book", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Book.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Cannot retrieve book by id: " + id, e);
        }
    }
}
