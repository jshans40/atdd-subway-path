package nextstep.subway.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Embeddable
public class Sections {

    @OneToMany(mappedBy = "line", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    public List<Section> getSections() {
        return this.sections;
    }

    public void add(Section section) {
        this.sections.add(section);
    }

    public List<Station> getStations() {
        Stream<Station> downStations = this.sections.stream().map(Section::getDownStation);
        Stream<Station> upStations = this.sections.stream().map(Section::getUpStation);

        return Stream.concat(upStations, downStations)
                .distinct()
                .collect(Collectors.toList());
    }

    public void remove(Section section) {
        this.sections.remove(section);
    }
}
