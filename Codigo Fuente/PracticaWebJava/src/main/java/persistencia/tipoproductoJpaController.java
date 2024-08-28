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
import logica.tipoproducto;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author DiegoBP
 */
public class tipoproductoJpaController implements Serializable {

    public tipoproductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public tipoproductoJpaController() {
        emf = Persistence.createEntityManagerFactory("PracticaWebJavaPU");
    }

    public void create(tipoproducto tipoproducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoproducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(tipoproducto tipoproducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoproducto = em.merge(tipoproducto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipoproducto.getId();
                if (findtipoproducto(id) == null) {
                    throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.");
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
            tipoproducto tipoproducto;
            try {
                tipoproducto = em.getReference(tipoproducto.class, id);
                tipoproducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoproducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<tipoproducto> findtipoproductoEntities() {
        return findtipoproductoEntities(true, -1, -1);
    }

    public List<tipoproducto> findtipoproductoEntities(int maxResults, int firstResult) {
        return findtipoproductoEntities(false, maxResults, firstResult);
    }

    private List<tipoproducto> findtipoproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(tipoproducto.class));
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

    public tipoproducto findtipoproducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(tipoproducto.class, id);
        } finally {
            em.close();
        }
    }

    public int gettipoproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<tipoproducto> rt = cq.from(tipoproducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
