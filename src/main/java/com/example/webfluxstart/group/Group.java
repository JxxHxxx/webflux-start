package com.example.webfluxstart.group;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("xuni_group")
public class Group {
    @Id
    @Column("GROUP_ID")
    private Long id;
    @Column("NAME")
    private String name;
    @Column("CAPACITY")
    private Integer capacity;
    @Column("LEFT_CAPACITY")
    private Integer leftCapacity;

    @Builder
    public Group(Long id, String name, Integer capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.leftCapacity = capacity;
    }

    public boolean isWithinRangeCapacity() {
        return this.capacity > 5 || this.capacity < 0 ? false : true;
    }
}