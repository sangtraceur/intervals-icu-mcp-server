package pl.techwolf.intervals.mcp;

import static org.assertj.core.api.Assertions.assertThat;

import icu.intervals.model.Activity;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.techwolf.intervals.mcp.api.IntervalsApiClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class IntervalsMcpApplicationTests {

  @Autowired
  private IntervalsApiClient intervalsApiClient;

  @Value("${intervals.api.athlete-id}")
  private String athleteId;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void contextLoads() {
  }

  // @Test
  void shouldInvokeListActivities() {
    // given
    String oldest = LocalDate.now().minusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE);
    String newest = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

    // when
    ResponseEntity<List<Activity>> response = intervalsApiClient.listActivities(athleteId, oldest, newest, null, null,
        null);
    log.info(response.toString());
    // then
    assertThat(response).isNotNull();
    assertThat(response.getBody()).isNotNull();
  }

}
