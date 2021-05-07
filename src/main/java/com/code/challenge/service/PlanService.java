package com.code.challenge.service;

import com.code.challenge.domain.Plan;
import com.code.challenge.repository.PlanRepository;
import com.code.challenge.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Plan}.
 */
@Service
@Transactional
public class PlanService {

    private final Logger log = LoggerFactory.getLogger(PlanService.class);

    private final PlanRepository planRepository;

    private final UserRepository userRepository;

    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save a plan.
     *
     * @param plan the entity to save.
     * @return the persisted entity.
     */
    public Plan save(Plan plan) {
        log.debug("Request to save Plan : {}", plan);
        Long userId = plan.getUser().getId();
        userRepository.findById(userId).ifPresent(plan::user);
        return planRepository.save(plan);
    }

    /**
     * Partially update a plan.
     *
     * @param plan the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Plan> partialUpdate(Plan plan) {
        log.debug("Request to partially update Plan : {}", plan);

        return planRepository
            .findById(plan.getId())
            .map(
                existingPlan -> {
                    if (plan.getPlan() != null) {
                        existingPlan.setPlan(plan.getPlan());
                    }
                    if (plan.getDeductible() != null) {
                        existingPlan.setDeductible(plan.getDeductible());
                    }
                    if (plan.getCoPay() != null) {
                        existingPlan.setCoPay(plan.getCoPay());
                    }

                    return existingPlan;
                }
            )
            .map(planRepository::save);
    }

    /**
     * Get all the plans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Plan> findAll() {
        log.debug("Request to get all Plans");
        return planRepository.findAll();
    }

    /**
     * Get one plan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Plan> findOne(Long id) {
        log.debug("Request to get Plan : {}", id);
        return planRepository.findById(id);
    }

    /**
     * Delete the plan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Plan : {}", id);
        planRepository.deleteById(id);
    }
}
