package com.example.webfluxstart.group;

import com.example.webfluxstart.annotated.Hello;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Mono<Group> save(Group group) {

        return Mono.just(group)
                   .flatMap(g -> verifyCapacity(g))
                   .then(groupRepository.save(group));
    }

    public Mono<Group> find(Long groupId) {
        return groupRepository.findById(groupId).switchIfEmpty(
                Mono.error(new IllegalArgumentException("존재하지 않는 그룹입니다"))
        );
    }

    private Mono<Void> verifyCapacity(Group group) {
        if (group.checkWithinRangeCapacity()) {
            return Mono.empty();
        }
        return Mono.error(new CapacityValidException("1 ~ 5 사이의 인원을 입력해주세요"));
    }
}
