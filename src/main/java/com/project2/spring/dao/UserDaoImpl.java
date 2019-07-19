package com.project2.spring.dao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import com.project2.spring.model.MaritalStatus;
import com.project2.spring.model.Role;
import com.project2.spring.model.Skill;
import com.project2.spring.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> {


    public List<User> searchUsers(String filterType, String filterText) {
        CriteriaQuery<User> cq = getCriteriaQuery();
        CriteriaBuilder cb = getCriteriaBuilder();
        Root<User> root = getRoot();
//        if (filterType == null || filterType.equals("")) {
//            TypedQuery<User> userTypedQuery = getEntityManager().createQuery(cq);
//            return userTypedQuery.getResultList();
//        } else {
//            if (filterText.equals("") && (!filterType.equals("skill"))) {
//                cq.select(root).where(cb.isNull(root.get(filterType)));
//            } else {
//                switch (filterType) {
//                    case "firstname":
//                    case "lastname":
//                    case "countryname":
//                    case "birthdate":
//                        cq.select(root).where(cb.equal(root.get(filterType), Date.valueOf(filterText)));
//                        break;
//                    case "role":
//                        Join<User, Role> joinUsrRole = root.join("role");
//                        cq.select(root).where(cb.equal(joinUsrRole.get("name"), filterText));
//                        break;
//                    case "maritalStatus":
//                        Join<User, MaritalStatus> joinUsrMrtSts = root.join("maritalStatus");
//                        cq.select(root).where(cb.equal(joinUsrMrtSts.get("name"), filterText));
//                        break;
//                    case "skill":
//                        Join<User, Skill> joinUsrSkill = root.join("skills");
//                        cq.select(root).where(cb.equal(joinUsrSkill.get("name"), filterText));
//                        break;
//                }
//            }

        if (!filterType.isEmpty()) {
            cq.select(root)
                     .where(
                             cb.equal(root.get(filterType), filterText)
                     );
        }

            TypedQuery<User> userTypedQuery = getEntityManager().createQuery(cq);
            List<User> userList = userTypedQuery.getResultList();
            resetCriteriaQuery();
            return userList;
        }

}

