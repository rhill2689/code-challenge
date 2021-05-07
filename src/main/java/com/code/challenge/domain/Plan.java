package com.code.challenge.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Plan.
 */
@Entity
@Table(name = "plan")
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Column(name = "plan", nullable = false, unique = true)
    private String plan;

    @NotNull
    @Column(name = "deductible", nullable = false)
    private Integer deductible;

    @NotNull
    @Column(name = "co_pay", nullable = false)
    private Double coPay;

    @OneToOne(optional = false)
    @NotNull
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plan id(Long id) {
        this.id = id;
        return this;
    }

    public String getPlan() {
        return this.plan;
    }

    public Plan plan(String plan) {
        this.plan = plan;
        return this;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Integer getDeductible() {
        return this.deductible;
    }

    public Plan deductible(Integer deductible) {
        this.deductible = deductible;
        return this;
    }

    public void setDeductible(Integer deductible) {
        this.deductible = deductible;
    }

    public Double getCoPay() {
        return this.coPay;
    }

    public Plan coPay(Double coPay) {
        this.coPay = coPay;
        return this;
    }

    public void setCoPay(Double coPay) {
        this.coPay = coPay;
    }

    public User getUser() {
        return this.user;
    }

    public Plan user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plan)) {
            return false;
        }
        return id != null && id.equals(((Plan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Plan{" +
            "id=" + getId() +
            ", plan='" + getPlan() + "'" +
            ", deductible=" + getDeductible() +
            ", coPay=" + getCoPay() +
            "}";
    }
}
