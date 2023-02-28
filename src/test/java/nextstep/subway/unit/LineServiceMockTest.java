package nextstep.subway.unit;

import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.StationService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.LineRepository;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LineServiceMockTest {
    @Mock
    private LineRepository lineRepository;
    @Mock
    private StationService stationService;

    @InjectMocks
    private LineService lineService;

    @Test
    void addSection() {
        // given
        // lineRepository, stationService stub 설정을 통해 초기값 셋팅
        Line 이호선 = new Line("2호선", "green");
        Station 잠실역 = new Station("잠실역");
        Station 잠실나루역 = new Station("잠실나루역");
        int distance = 7;

        when(lineRepository.findById(이호선.getId())).thenReturn(Optional.of(이호선));
        when(stationService.findById(잠실역.getId())).thenReturn(잠실역);
        when(stationService.findById(잠실나루역.getId())).thenReturn(잠실나루역);

        // when
        // lineService.addSection 호출
        lineService.addSection(이호선.getId(), new SectionRequest(잠실나루역.getId(), 잠실역.getId(), distance));


        // then
        // lineService.findLineById 메서드를 통해 검증
        Line line = lineService.findLineById(이호선.getId());
        List<Section> sections = line.getSections();
        assertThat(sections.get(0).getUpStation().getId()).isEqualTo(잠실역.getId());
        assertThat(sections.get(0).getDownStation().getId()).isEqualTo(잠실나루역.getId());
        assertThat(sections.get(0).getDistance()).isEqualTo(distance);

    }
}
