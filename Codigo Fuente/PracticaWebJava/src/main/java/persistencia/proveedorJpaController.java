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
import logica.proveedor;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author DiegoBP
 */
public class proveedorJpaController implements Serializable {

    public proveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public proveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWebJavaPU");
    }

    public void create(proveedor proveedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proveedor = em.merge(proveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = proveedor.getIdProveedor();
                if (findproveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            proveedor proveedor;
            try {
                proveedor = em.getReference(proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<proveedor> findproveedorEntities() {
        return findproveedorEntities(true, -1, -1);
    }

    public List<proveedor> findproveedorEntities(int maxResults, int firstResult) {
        return findproveedorEntities(false, maxResults, firstResult);
    }

    private List<proveedor> findproveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(proveedor.class));
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

    public proveedor findproveedor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getproveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<proveedor> rt = cq.from(proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<proveedor> findProductosByParams(String claveProducto, String tipoProducto) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<proveedor> cq = cb.createQuery(proveedor.class);
            Root<proveedor> proveedor = cq.from(proveedor.class);

            // Crear una lista para los predicados
            List<Predicate> predicates = new ArrayList<>();

            if (claveProducto != null && !claveProducto.isEmpty()) {
                predicates.add(cb.like(proveedor.get("claveProducto"), "%" + claveProducto + "%"));
            }

            if (tipoProducto != null && !tipoProducto.isEmpty()) {
                predicates.add(cb.like(proveedor.get("IdTipoProducto"), "%" + tipoProducto + "%"));
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
