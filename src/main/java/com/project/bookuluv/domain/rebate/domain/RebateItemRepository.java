package com.project.bookuluv.domain.rebate.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RebateItemRepository extends JpaRepository<RebateOrderItem, Long> {
    List<RebateOrderItem> findByPayDateAfter(LocalDateTime startDate);
}
