package com.example.candidateservice.service;

import com.example.candidateservice.modal.entity.Apply;
import com.example.candidateservice.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    public List<Integer> countApplyByMonthAndYear(int year){
        System.err.println(year);
        List<Apply> list = applyRepository.findAll().stream()
                .filter(y -> y.getApplyDate().getYear() == year)
                .collect(Collectors.toList());
        List<Integer> listApply = new ArrayList<>();
        for (int i=1; i<=12;i++) {
            int month = i;
            Long count = list.stream()
                    .filter(m -> m.getApplyDate().getMonth().getValue() == month)
                    .count();
            listApply.add(count.intValue());
        }
        return listApply;
    }
}
