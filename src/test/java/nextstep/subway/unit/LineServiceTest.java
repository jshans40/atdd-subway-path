package nextstep.subway.unit;

import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class LineServiceTest {
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineService lineService;

    @Test
    void addSection() {
        // given
        // stationRepository와 lineRepository를 활용하여 초기값 셋팅
        Line 이호선 = lineRepository.save(new Line("2호선", "green"));
        Station 잠실역 = stationRepository.save(new Station("잠실역"));
        Station 잠실나루역 = stationRepository.save(new Station("잠실나루역"));
        int distance = 7;

        // when
        // lineService.addSection 호출
        lineService.addSection(이호선.getId(), new SectionRequest(잠실나루역.getId(), 잠실역.getId(), distance));

        // then
        // line.getSections 메서드를 통해 검증
        Line findLine = lineRepository.findById(이호선.getId()).get();
        List<Section> sections = 이호선.getSections();
        assertThat(sections.get(0).getUpStation().getId()).isEqualTo(잠실나루역.getId());
        assertThat(sections.get(0).getDownStation().getId()).isEqualTo(잠실역.getId());
        assertThat(sections.get(0).getDistance()).isEqualTo(distance);

    }
}
