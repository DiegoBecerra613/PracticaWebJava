/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import logica.producto;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author DiegoBP
 */
public class productoJpaController implements Serializable {

    public productoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public productoJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWebJavaPU");
    }

    public void create(producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto = em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId();
                if (findproducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto producto;
            try {
                producto = em.getReference(producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<producto> findproductoEntities() {
        return findproductoEntities(true, -1, -1);
    }

    public List<producto> findproductoEntities(int maxResults, int firstResult) {
        return findproductoEntities(false, maxResults, firstResult);
    }

    private List<producto> findproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public producto findproducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<producto> rt = cq.from(producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<producto> findProductosByParams(String claveProducto, String tipoProducto) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<producto> cq = cb.createQuery(producto.class);
            Root<producto> producto = cq.from(producto.class);

            // Crear una lista para los predicados
            List<Predicate> predicates = new ArrayList<>();

            if (claveProducto != null && !claveProducto.isEmpty()) {
                predicates.add(cb.like(producto.get("claveProducto"), "%" + claveProducto + "%"));
            }

            if (tipoProducto != null && !tipoProducto.isEmpty()) {
                predicates.add(cb.like(producto.get("IdTipoProducto"), "%" + tipoProducto + "%"));
            }

            // Combinar los predicados en una consulta
            cq.where(predicates.toArray(new Predicate[0]));

            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
