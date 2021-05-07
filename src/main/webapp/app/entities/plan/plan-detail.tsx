import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './plan.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlanDetail = (props: IPlanDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { planEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="planDetailsHeading">Plan</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{planEntity.id}</dd>
          <dt>
            <span id="plan">Plan</span>
          </dt>
          <dd>{planEntity.plan}</dd>
          <dt>
            <span id="deductible">Deductible</span>
          </dt>
          <dd>{planEntity.deductible}</dd>
          <dt>
            <span id="coPay">Co Pay</span>
          </dt>
          <dd>{planEntity.coPay}</dd>
          <dt>User</dt>
          <dd>{planEntity.user ? planEntity.user.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/plan" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/plan/${planEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ plan }: IRootState) => ({
  planEntity: plan.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlanDetail);
