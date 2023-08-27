package com.project.bookuluv.domain.rebate.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RebateService {

    private final RebateItemRepository rebateItemRepository;

    public void rebate(RebateOrderItem rebateOrderItem){
        if (rebateOrderItem.isPaid()){
            return;
        }

        int price = rebateOrderItem.getPrice();

        int commission = calculateCommission(price);

        int payAbleAmount = price - commission;

        rebateOrderItem.setPayPrice(payAbleAmount);

        rebateOrderItem.setPaid(true);

        rebateOrderItem.setCreateDate(LocalDateTime.now());

        rebateItemRepository.save(rebateOrderItem);

    }

    private int calculateCommission(int price) {
        // 수수료 계산 로직
        // 여기서 수수료를 어떻게 계산할지 로직을 작성하면 됩니다.
        // 예를 들어, 판매가의 10%를 수수료로 계산하는 경우:
        return (int) (price * 0.1);
    }

    public List<RebateOrderItem> calculateRebateData(String period) {

        LocalDateTime startDate = null;
        if ("day".equals(period)) {
            startDate = LocalDateTime.now().minusDays(1);
            // 하루 동안의 데이터 조회 및 계산
            // 예시: LocalDateTime.now().minusDays(1)
        } else if ("week".equals(period)) {
            startDate = LocalDateTime.now().minusWeeks(1);
            // 일주일 동안의 데이터 조회 및 계산
            // 예시: LocalDateTime.now().minusWeeks(1)
        } else if ("year".equals(period)) {
            startDate = LocalDateTime.now().minusYears(1);
            // 일년 동안의 데이터 조회 및 계산
            // 예시: LocalDateTime.now().minusYears(1)
        }
        List<RebateOrderItem> rebateDataList = rebateItemRepository.findByPayDateAfter(startDate);

        // 각 기간에 따른 데이터 조회 및 계산 로직 작성
        // rebateDataList에 계산한 데이터를 추가

        return rebateDataList;
    }

}
