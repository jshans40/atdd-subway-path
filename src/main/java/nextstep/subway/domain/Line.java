package nextstep.subway.domain;

import nextstep.subway.applicaion.dto.LineRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;

//    @OneToMany(mappedBy = "line", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
//    private List<Section> sections = new ArrayList<>();
    @Embedded
    private final Sections sections = new Sections();

    public Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Section> getSections() {
        return sections.getSections();
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public List<Station> getStations() {
        return sections.getStations();
    }

    public void removeSection(Section section) {
        this.sections.remove(section);
    }

    public void update(LineRequest lineRequest) {
        if (lineRequest.getName() != null) {
            this.name = lineRequest.getName();
        }
        if (lineRequest.getColor() != null) {
            this.color = lineRequest.getColor();
        }
    }
}
