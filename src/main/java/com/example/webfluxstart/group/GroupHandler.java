package com.example.webfluxstart.group;

import com.example.webfluxstart.group.dto.GroupForm;
import com.example.webfluxstart.group.dto.GroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class GroupHandler {

    private final GroupService groupService;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(GroupForm.class)
                .flatMap(form -> groupService.save(
                        Group.builder()
                                .name(form.name())
                                .capacity(form.capacity())
                                .build()
                ))
                .flatMap(group -> ServerResponse
                        .created(URI.create("/groups"))
                        .build());
    }

    public Mono<ServerResponse> findOne(ServerRequest request) {
        Long groupId = Long.valueOf(request.pathVariable("group-id"));
        return groupService.find(groupId)
                .flatMap(group -> ServerResponse.ok().bodyValue(new GroupResponse(group.getName(), group.getLeftCapacity())));
//                .flatMap(group -> ServerResponse.ok().bodyValue(group));

    }
}
