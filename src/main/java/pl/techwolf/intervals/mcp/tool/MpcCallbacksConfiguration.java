package pl.techwolf.intervals.mcp.tool;

import java.util.List;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpcCallbacksConfiguration {
  @Bean
  public List<ToolCallback> findTools(IntervalsToolProvider intervalsToolProvider) {
    return List.of(ToolCallbacks.from(intervalsToolProvider));
  }
}