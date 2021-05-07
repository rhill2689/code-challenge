package com.code.challenge.service;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import com.code.challenge.IntegrationTest;
import com.code.challenge.domain.Plan;
import com.code.challenge.domain.User;
import com.code.challenge.repository.PlanRepository;
import com.code.challenge.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@IntegrationTest
@Transactional
public class PlanServiceIT {

    private static final String DEFAULT_LOGIN = "johndoe";

    private static final String DEFAULT_EMAIL = "johndoe@localhost";

    private static final String DEFAULT_FIRSTNAME = "john";

    private static final String DEFAULT_LASTNAME = "doe";

    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";

    private static final String DEFAULT_LANGKEY = "dummy";

    @Autowired
    private PlanService planService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    private User user;
    private Plan plan;

    @BeforeEach
    public void init() {
        user = new User();
        user.setLogin(DEFAULT_LOGIN);
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail(DEFAULT_EMAIL);
        user.setFirstName(DEFAULT_FIRSTNAME);
        user.setLastName(DEFAULT_LASTNAME);
        user.setImageUrl(DEFAULT_IMAGEURL);
        user.setLangKey(DEFAULT_LANGKEY);
    }

    @Test
    @Transactional
    void CreateAndUpdatePlan() {
        userRepository.saveAndFlush(user);

        plan = new Plan();
        plan.plan("Copper"); //the generator messed up so plan name is just plan
        plan.setUser(user);
        plan.setCoPay(40.00);
        plan.setDeductible(5000);
        planRepository.saveAndFlush(plan);
        Optional<Plan> optPlan = planService.findOne(1001L);
        Plan myPlan = optPlan.get();
        if (myPlan != null) {
            myPlan.plan("Silver");
            myPlan.setDeductible(3000);
            myPlan.setCoPay(30.00);
        }
        planService.save(myPlan);
        Optional<Plan> optPlanA = planService.findOne(1001L);
        Plan updatedPlan = optPlanA.get();
        assertThat(updatedPlan.getPlan() == "Silver");
        assertThat(updatedPlan.getCoPay() == 30.00);
        assertThat(updatedPlan.getDeductible() == 3000);
    }

    @Test
    @Transactional
    void deletePlan() {
        userRepository.saveAndFlush(user);

        Plan plan = new Plan();
        plan.plan("Gold"); //the generator messed up so plan name is just plan
        plan.setUser(user);
        plan.setCoPay(20.00);
        plan.setDeductible(2000);
        planRepository.saveAndFlush(plan);

        List<Plan> myPlans = planService.findAll();
        Optional<Plan> planToDeleteOpt = myPlans.stream().filter(p -> "Gold".equalsIgnoreCase(p.getPlan())).findAny();
        Plan planToDelete = planToDeleteOpt.get();
        planService.delete(planToDelete.getId());
        assertThat(planService.findOne(planToDelete.getId()) == null);
    }
}
