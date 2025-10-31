package pl.techwolf.intervals.mcp.api;

import org.springframework.cloud.openfeign.FeignClient;
import icu.intervals.api.ApiApi;

@FeignClient(name = "intervals", url = "${intervals.api.base-url}", configuration = IntervalsApiConfiguration.class)
public interface IntervalsApiClient extends ApiApi {

}
