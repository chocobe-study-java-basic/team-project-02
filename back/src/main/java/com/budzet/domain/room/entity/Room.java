package com.budzet.domain.room.entity;

import com.budzet.domain.budget.entity.BudgetChange;
import com.budzet.domain.budget.entity.BudgetRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    private Long totalBudget;

    private Long availableBudget;

    @Column(length = 3)
    private String currency;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "room")
    private List<UserRoomConnection> userConnections = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<BudgetRequest> budgetRequests = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<BudgetChange> budgetChanges = new ArrayList<>();
}
