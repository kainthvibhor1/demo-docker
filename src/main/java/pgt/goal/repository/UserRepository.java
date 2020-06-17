package pgt.goal.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pgt.goal.models.User;

@Service
@Slf4j
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${pgt.mongo.user.collection:users}")
    private String userCollection;

    public List<User> find(String id) {
        if (id == null || id.length() == 0) {
            log.info("Getting all users");
            return mongoTemplate.find(new BasicQuery("{}"), User.class, userCollection);
        }
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.find(query, User.class, userCollection);
    }

    public String insertUser(User user) {
        log.info("Inserting user with name: {}", user.getName());
        mongoTemplate.insert(user, userCollection);
        return user.get_id();
    }
}