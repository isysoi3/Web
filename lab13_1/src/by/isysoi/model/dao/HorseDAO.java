package by.isysoi.model.dao;

import by.isysoi.model.entity.Horse;
import by.isysoi.model.entity.Horse_;
import by.isysoi.model.entity.Race;
import by.isysoi.model.entity.Race_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * horse dao class
 *
 * @author Ilya Sysoi
 * @version 1.0.0
 */

public class HorseDAO {

    protected Logger logger = LogManager.getLogger("dao_layer");

    @PersistenceContext(unitName = "Test_Local")
    private EntityManager entityManager;

    /**
     * DAO constructor
     */
    public HorseDAO(EntityManagerFactory emf) {
        entityManager = emf.createEntityManager();
        logger.info("HorseDAO created ");
    }

    public HorseDAO() {
    }


    /**
     * read horses
     *
     * @return list of horses
     */

    public List<Horse> readHorses() {
        List horses = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Horse.class);
            Root horseRoot = criteriaQuery.from(Horse.class);

            horses = entityManager.createQuery(criteriaQuery)
                    .getResultList();
        } catch (Exception e) {
            logger.error("failed to read horses", e);
        }
        return horses;
    }

    /**
     * read horse by id
     *
     * @param id id of horse
     * @return horse
     */

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
            logger.error("failed to read horse", e);
        }
        return horse;
    }

    /**
     * insert horse
     *
     * @param horse horse object
     */

    public void insertHorse(Horse horse) {
        try {
            UserTransaction transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
            entityManager.joinTransaction();
            entityManager.persist(horse);
            transaction.commit();
        } catch (Exception e) {
            logger.error("failed to insert horse", e);
        }
    }

    /**
     * read horses in race
     *
     * @param raceId id of race
     */

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
            logger.error("failed to read horses in race", e);
        }
        return horses;
    }


}
