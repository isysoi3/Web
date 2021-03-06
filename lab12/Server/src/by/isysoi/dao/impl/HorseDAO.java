package by.isysoi.dao.impl;

import by.isysoi.dao.HorseDAOInterface;
import by.isysoi.entity.Horse;
import by.isysoi.entity.Horse_;
import by.isysoi.entity.Race;
import by.isysoi.entity.Race_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * horse dao class
 *
 * @author Ilya Sysoi
 * @version 1.0.0
 */
@WebService()
@Stateless
public class HorseDAO implements HorseDAOInterface {

//    protected Logger logger = LogManager.getLogger("dao_layer");

    @Resource
    UserTransaction transaction;

    @PersistenceContext(unitName = "Test_Local")
    private EntityManager entityManager;

    /**
     * DAO constructor
     */
    public HorseDAO(EntityManagerFactory emf) {
        entityManager = emf.createEntityManager();
//        logger.info("HorseDAO created ");
    }

    public HorseDAO() {
    }


    /**
     * read horses
     *
     * @return list of horses
     */
    @WebMethod
    public List<Horse> readHorses() {
        List horses = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Horse.class);
            Root horseRoot = criteriaQuery.from(Horse.class);

            horses = entityManager.createQuery(criteriaQuery)
                    .getResultList();
        } catch (Exception e) {
            //logger.error("failed to read horses", e);
        }
        return horses;
    }

    /**
     * read horse by id
     *
     * @param id id of horse
     * @return horse
     */
    @WebMethod
    public Horse readHorseById(int id) {
        Horse horse = null;

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Horse.class);
            Root rootHorse = criteriaQuery.from(Horse.class);
            Predicate condition = criteriaBuilder.equal(rootHorse.get(Horse_.id), id);
            criteriaQuery.where(condition);

            horse = (Horse) entityManager.createQuery(criteriaQuery)
                    .getSingleResult();
        } catch (Exception e) {
            //logger.error("failed to read horse", e);
        }
        return horse;
    }

    /**
     * insert horse
     *
     * @param horse horse object
     */
    @WebMethod
    public void insertHorse(Horse horse) {
        try {
            transaction.begin();
            entityManager.joinTransaction();

            entityManager.persist(horse);

            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (SystemException e1) {
                //logger.error("transaction rollback failed", e);
            }
            //logger.error("failed to insert horse", e);
        }
    }

    /**
     * read horses in race
     *
     * @param raceId id of race
     */
    @WebMethod
    public List<Horse> readHorcesInRace(int raceId) {
        List horses = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Horse.class);
            Root rootHorse = criteriaQuery.from(Horse.class);
            Join<Horse, Race> raceJoin = rootHorse.join(Horse_.races);
            Predicate condition = criteriaBuilder.equal(raceJoin.get(Race_.id), raceId);
            criteriaQuery.where(condition);

            horses = entityManager.createQuery(criteriaQuery)
                    .getResultList();
        } catch (Exception e) {
            //logger.error("failed to read horses in race", e);
        }
        return horses;
    }


}
