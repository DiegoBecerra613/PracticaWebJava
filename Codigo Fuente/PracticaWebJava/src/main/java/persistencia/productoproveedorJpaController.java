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
import logica.productoproveedor;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author DiegoBP
 */
public class productoproveedorJpaController implements Serializable {

    public productoproveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public productoproveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWebJavaPU");
    }

    public void create(productoproveedor productoproveedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productoproveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(productoproveedor productoproveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productoproveedor = em.merge(productoproveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = productoproveedor.getIdProductoProveedor();
                if (findproductoproveedor(id) == null) {
                    throw new NonexistentEntityException("The productoproveedor with id " + id + " no longer exists.");
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
            productoproveedor productoproveedor;
            try {
                productoproveedor = em.getReference(productoproveedor.class, id);
                productoproveedor.getIdProductoProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoproveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(productoproveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<productoproveedor> findproductoproveedorEntities() {
        return findproductoproveedorEntities(true, -1, -1);
    }

    public List<productoproveedor> findproductoproveedorEntities(int maxResults, int firstResult) {
        return findproductoproveedorEntities(false, maxResults, firstResult);
    }

    private List<productoproveedor> findproductoproveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(productoproveedor.class));
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

    public productoproveedor findproductoproveedor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(productoproveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getproductoproveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<productoproveedor> rt = cq.from(productoproveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<productoproveedor> findProductosByParams(String IdProducto) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<productoproveedor> cq = cb.createQuery(productoproveedor.class);
            Root<productoproveedor> productoproveedor = cq.from(productoproveedor.class);

            // Crear una lista para los predicados
            List<Predicate> predicates = new ArrayList<>();

            if (IdProducto != null && !IdProducto.isEmpty()) {
                // Cambiar de cb.like a cb.equal para la coincidencia exacta
                predicates.add(cb.equal(productoproveedor.get("IdProducto"), IdProducto));
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
