package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User get(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("SELECT u from User u where u.car.model = ?1 AND u.car.series = ?2", User.class);
      query.setParameter(1, car.getModel());
      query.setParameter(2, car.getSeries());
      return query.getResultList().get(0);
   }



}
