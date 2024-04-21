package com.io.todolist.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "mate_user")
public class MateUser extends BaseTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mate_id", referencedColumnName = "id")
    private Mate mate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;


    public void setMate(Mate mate) {
        this.mate = mate;
        mate.getMateUsers().add(this);
    }

    public void setUser(Users user) {
        this.user = user;
        user.getMateUsers().add(this);
    }

}
