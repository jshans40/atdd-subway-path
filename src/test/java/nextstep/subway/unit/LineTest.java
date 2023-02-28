package nextstep.subway.unit;

import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {

    private Line 이호선;
    private Station 잠실역;
    private Station 잠실나루역;

    @BeforeEach
    void setUp() {
        this.이호선 = new Line("2호선", "green");
        this.잠실역 = new Station("잠실역");
        this.잠실나루역 = new Station("잠실나루역");
    }

    @DisplayName("구간 추가")
    @Test
    void addSection() {
        Section 구간 = createSection();
        이호선.addSection(구간);
        assertThat(이호선.getSections()).containsExactly(구간);

    }

    @Test
    void getStations() {
        // Given
        Section section = createSection();
        이호선.addSection(section);

        // when
        List<Station> stations = 이호선.getStations();

        // then
        assertThat(stations).containsExactly(잠실역, 잠실나루역);
    }

    @Test
    void removeSection() {
        // Given
        Section section = createSection();
        이호선.addSection(section);

        // When
        이호선.removeSection(section);

        // Then
        assertThat(이호선.getSections()).isEmpty();
    }

    private Section createSection() {
        return new Section(이호선, 잠실역, 잠실나루역, 7);
    }
}
