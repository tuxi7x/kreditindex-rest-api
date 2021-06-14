package io.tuxi.kreditindexrestapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Subject {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int credit;

    @Getter
    @Setter
    private int mark;

    private static int idCounter = 1;

    public Subject(int credit, int mark, String id) {
        this.id = id;
        this.name = "";
        this.credit = credit;
        this.mark = mark;
        idCounter++;
    }

    public Subject(String name, int credit, int mark) {
        this.id = String.valueOf(idCounter);
        this.name = name;
        this.credit = credit;
        this.mark = mark;
        idCounter++;
    }
}
