package pl.techwolf.intervals.mcp.tool;

import icu.intervals.api.ApiApi;
import icu.intervals.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class IntervalsToolProvider {

  private final ApiApi intervalsApi;
  @Value("${intervals.api.athlete-id}")
  private String defaultAthleteId;

  public IntervalsToolProvider(ApiApi intervalsApi) {
    this.intervalsApi = intervalsApi;
    log.info("TOOLS LOADED");
  }

  @Tool(name = "ping", description = "A simple tool that returns 'pong'")
  public String ping() {
    return "pong";
  }

  @Tool(name = "applyCurrentPlanChanges", description = "Apply any changes from the athlete's current plan to the athlete's calendar")
  public Map<String, Object> applyCurrentPlanChanges(String id) {
    return intervalsApi.applyCurrentPlanChanges(id).getBody();
  }

  @Tool(name = "applyToActivities", description = "Apply sport settings to matching activities (updates zones), done asynchronously")
  public Object applyToActivities(String athleteId, String id) {
    return intervalsApi.applyToActivities(athleteId, id).getBody();
  }

  @Tool(name = "calcDistanceEtc", description = "Recalculate gear stats")
  public GearStats calcDistanceEtc(String id, String gearId) {
    return intervalsApi.calcDistanceEtc(id, gearId).getBody();
  }

  @Tool(name = "checkMerge", description = "How similar is this route to another?")
  public RouteSimilarity checkMerge(String id, Long routeId, Long otherId) {
    return intervalsApi.checkMerge(id, routeId, otherId).getBody();
  }

  @Tool(name = "createCustomItem", description = "Create a custom item")
  public NewCustomItem createCustomItem(String id, CustomItem customItem) {
    return intervalsApi.createCustomItem(id, customItem).getBody();
  }

  @Tool(name = "createEvent", description = "Create an event on the athlete's calendar")
  public Event createEvent(String id, Boolean upsertOnUid, EventEx eventEx) {
    return intervalsApi.createEvent(id, upsertOnUid, eventEx).getBody();
  }

  @Tool(name = "createFolder", description = "Create a new workout folder or plan")
  public Folder createFolder(String id, CreateFolderDTO createFolderDTO) {
    return intervalsApi.createFolder(id, createFolderDTO).getBody();
  }

  @Tool(name = "createGear", description = "Create a new gear or component")
  public Gear createGear(String id, Gear gear) {
    return intervalsApi.createGear(id, gear).getBody();
  }

  @Tool(name = "createManualActivity", description = "Create a manual activity")
  public Activity createManualActivity(String id, Activity activity) {
    return intervalsApi.createManualActivity(id, activity).getBody();
  }

  @Tool(name = "createMultipleEvents", description = "Create multiple events on the athlete's calendar")
  public List<Event> createMultipleEvents(String id, Boolean upsertOnUid, Boolean updatePlanApplied,
      List<EventEx> eventEx, Boolean upsert) {
    return intervalsApi.createMultipleEvents(id, upsertOnUid, updatePlanApplied, eventEx, upsert).getBody();
  }

  @Tool(name = "createMultipleWorkouts", description = "Create multiple new workouts in a folder or plan in the athlete's workout library")
  public List<Workout> createMultipleWorkouts(String id, List<WorkoutEx> workoutEx) {
    return intervalsApi.createMultipleWorkouts(id, workoutEx).getBody();
  }

  @Tool(name = "createReminder", description = "Create a new reminder")
  public Gear createReminder(String id, String gearId, GearReminder gearReminder) {
    return intervalsApi.createReminder(id, gearId, gearReminder).getBody();
  }

  // @Tool(name = "createSettings", description = "Create settings for a sport
  // with default values")
  public SportSettings createSettings(String athleteId, SportSettings sportSettings) {
    return intervalsApi.createSettings(athleteId, sportSettings).getBody();
  }

  @Tool(name = "createSharedEvent", description = "Create a shared event (e.g. race)")
  public SharedEvent createSharedEvent(SharedEvent sharedEvent, Integer linkToEventId) {
    return intervalsApi.createSharedEvent(sharedEvent, linkToEventId).getBody();
  }

  @Tool(name = "createWorkout", description = "Create a new workout in a folder or plan in the athlete's workout library")
  public Workout createWorkout(String id, WorkoutEx workoutEx) {
    return intervalsApi.createWorkout(id, workoutEx).getBody();
  }

  // @Tool(name = "deleteActivity", description = "Delete an activity")
  public ActivityId deleteActivity(String id) {
    return intervalsApi.deleteActivity(id).getBody();
  }

  // @Tool(name = "deleteCustomItem", description = "Delete a custom item")
  public void deleteCustomItem(String id, Integer itemId) {
    intervalsApi.deleteCustomItem(id, itemId);
  }

  // @Tool(name = "deleteEvent", description = "Delete an event from an athlete's
  // calendar")
  public Map<String, Object> deleteEvent(String id, Integer eventId, @Nullable Boolean others,
      @Nullable String notBefore) {
    return intervalsApi.deleteEvent(id, eventId, others, notBefore).getBody();
  }

  // @Tool(name = "deleteEvents", description = "Delete a range of events from the
  // athlete's calendar")
  public void deleteEvents(String id, String oldest, List<String> category, @Nullable String newest,
      @Nullable String createdById) {
    intervalsApi.deleteEvents(id, oldest, category, newest, createdById);
  }

  //// @Tool(name = "deleteEventsBulk", description = "Delete events from an
  //// athlete's calendar by id or external_id")
  public DeleteEventsResponse deleteEventsBulk(String id, List<DoomedEvent> doomedEvent) {
    return intervalsApi.deleteEventsBulk(id, doomedEvent).getBody();
  }

  // @Tool(name = "deleteFolder", description = "Delete a workout folder or plan
  // including all workouts")
  public Map<String, Object> deleteFolder(String id, Integer folderId) {
    return intervalsApi.deleteFolder(id, folderId).getBody();
  }

  // @Tool(name = "deleteGear", description = "Delete a gear or component")
  public void deleteGear(String id, String gearId) {
    intervalsApi.deleteGear(id, gearId);
  }

  // @Tool(name = "deleteIntervals", description = "Delete intervals")
  public IntervalsDTO deleteIntervals(String id, List<Interval> interval) {
    return intervalsApi.deleteIntervals(id, interval).getBody();
  }

  // @Tool(name = "deleteMessage", description = "Delete a message")
  public Object deleteMessage(Integer id, Long msgId) {
    return intervalsApi.deleteMessage(id, msgId).getBody();
  }

  // @Tool(name = "deleteReminder", description = "Delete a reminder")
  public Gear deleteReminder(String id, String gearId, Integer reminderId) {
    return intervalsApi.deleteReminder(id, gearId, reminderId).getBody();
  }

  // @Tool(name = "deleteSettings", description = "Delete sport settings")
  public Object deleteSettings(String athleteId, Integer id) {
    return intervalsApi.deleteSettings(athleteId, id).getBody();
  }

  // @Tool(name = "deleteSharedEvent", description = "Delete a shared event (e.g.
  // race)")
  public void deleteSharedEvent(Integer id) {
    intervalsApi.deleteSharedEvent(id);
  }

  //// @Tool(name = "deleteWorkout", description = "Delete a workout")
  public List<Integer> deleteWorkout(String id, Integer workoutId, @Nullable Boolean others) {
    return intervalsApi.deleteWorkout(id, workoutId, others).getBody();
  }

  // @Tool(name = "disconnectApp", description = "Disconnect the athlete from the
  // app matching the bearer token")
  public void disconnectApp() {
    intervalsApi.disconnectApp();
  }

  @Tool(name = "downloadActivitiesAsCSV", description = "Download activities as CSV")
  public void downloadActivitiesAsCSV(String id) {
    intervalsApi.downloadActivitiesAsCSV(id);
  }

  @Tool(name = "downloadActivityFile", description = "Download original activity file, Strava activities not supported")
  public void downloadActivityFile(String id) {
    intervalsApi.downloadActivityFile(id);
  }

  @Tool(name = "downloadActivityFitFile", description = "Download Intervals.icu generated activity fit file")
  public void downloadActivityFitFile(String id, @Nullable Boolean power, @Nullable Boolean hr) {
    intervalsApi.downloadActivityFitFile(id, power, hr);
  }

  @Tool(name = "downloadActivityFitFiles", description = "Download zip of Intervals.icu generated activity fit files")
  public void downloadActivityFitFiles(String id, List<String> ids, @Nullable Boolean power, @Nullable Boolean hr) {
    intervalsApi.downloadActivityFitFiles(id, ids, power, hr);
  }

  @Tool(name = "downloadActivityGpxFile", description = "Download Intervals.icu generated activity gpx file")
  public void downloadActivityGpxFile(String id, @Nullable Boolean power, @Nullable Boolean hr) {
    intervalsApi.downloadActivityGpxFile(id, power, hr);
  }

  @Tool(name = "downloadWorkout", description = "Convert a workout to .zwo (Zwift), .mrc, .erg or .fit")
  public void downloadWorkout(String ext, Workout workout) {
    intervalsApi.downloadWorkout(ext, workout);
  }

  @Tool(name = "downloadWorkout1", description = "Download a planned workout in zwo, mrc, erg or fit format")
  public void downloadWorkout1(String id, Integer eventId, String ext) {
    intervalsApi.downloadWorkout1(id, eventId, ext);
  }

  @Tool(name = "downloadWorkoutForAthlete", description = "Convert a workout to .zwo (Zwift), .mrc, .erg or .fit.")
  public void downloadWorkoutForAthlete(String id, String ext, Workout workout) {
    intervalsApi.downloadWorkoutForAthlete(id, ext, workout);
  }

  @Tool(name = "downloadWorkouts", description = "Download one or more workouts from the athlete's calendar in a zip file")
  public void downloadWorkouts(String id, String ext, String oldest, String newest, @Nullable Double powerRange,
      @Nullable Double hrRange, @Nullable Double paceRange, @Nullable String locale) {
    intervalsApi.downloadWorkouts(id, ext, oldest, newest, powerRange, hrRange, paceRange, locale);
  }

  @Tool(name = "duplicateEvents", description = "Duplicate one or more events on the athlete's calendar")
  public List<Event> duplicateEvents(String id, DuplicateEventsDTO duplicateEventsDTO) {
    return intervalsApi.duplicateEvents(id, duplicateEventsDTO).getBody();
  }

  @Tool(name = "duplicateWorkouts", description = "Duplicate workouts on a plan")
  public List<Workout> duplicateWorkouts(String id, DuplicateWorkoutsDTO duplicateWorkoutsDTO) {
    return intervalsApi.duplicateWorkouts(id, duplicateWorkoutsDTO).getBody();
  }

  @Tool(name = "findBestEfforts", description = "Find best efforts in the activity")
  public BestEfforts findBestEfforts(String id, String stream, @Nullable Integer duration, @Nullable Float distance,
      @Nullable Integer count,
      @Nullable Float minValue, @Nullable Boolean excludeIntervals, @Nullable Integer startIndex,
      @Nullable Integer endIndex) {
    return intervalsApi
        .findBestEfforts(id, stream, duration, distance, count, minValue, excludeIntervals, startIndex, endIndex)
        .getBody();
  }

  @Tool(name = "getActivities", description = "Fetch multiple activities by id. Missing activities are ignored")
  public List<Activity> getActivities(String athleteId, List<String> ids, @Nullable Boolean intervals) {
    return intervalsApi.getActivities(athleteId, ids, intervals).getBody();
  }

  @Tool(name = "getActivity", description = "Get an activity")
  public GetActivityDefaultResponse getActivity(String id, @Nullable Boolean intervals) {
    return intervalsApi.getActivity(id, intervals).getBody();
  }

  @Tool(name = "getActivityHRCurve", description = "Get activity heart rate curve in JSON or CSV (use .csv ext) format")
  public HRCurve getActivityHRCurve(String id, String ext) {
    return intervalsApi.getActivityHRCurve(id, ext).getBody();
  }

  @Tool(name = "getActivityMap", description = "Get activity map data")
  public MapData getActivityMap(String id, @Nullable List<Float> bounds, @Nullable Boolean boundsOnly,
      @Nullable Boolean weather) {
    return intervalsApi.getActivityMap(id, bounds, boundsOnly, weather).getBody();
  }

  @Tool(name = "getActivityPaceCurve", description = "Get activity pace curve in JSON or CSV (use .csv ext) format")
  public PaceCurve getActivityPaceCurve(String id, String ext, @Nullable Boolean gap) {
    return intervalsApi.getActivityPaceCurve(id, ext, gap).getBody();
  }

  @Tool(name = "getActivityPowerCurve", description = "Get activity power curve in JSON or CSV (use .csv ext) format")
  public PowerCurve getActivityPowerCurve(String id, String ext, @Nullable String fatigue) {
    return intervalsApi.getActivityPowerCurve(id, ext, fatigue).getBody();
  }

  @Tool(name = "getActivityPowerSpikeModel", description = "Get activity power spike detection model")
  public PowerModel getActivityPowerSpikeModel(String id) {
    return intervalsApi.getActivityPowerSpikeModel(id).getBody();
  }

  @Tool(name = "getActivitySegments", description = "Get activity segments")
  public List<IcuSegment> getActivitySegments(String id) {
    return intervalsApi.getActivitySegments(id).getBody();
  }

  @Tool(name = "getActivityStreams", description = "List streams for the activity")
  public List<ActivityStream> getActivityStreams(String id, String ext, @Nullable List<String> types) {
    return intervalsApi.getActivityStreams(id, ext, types).getBody();
  }

  @Tool(name = "getActivityWeatherSummary", description = "Get activity weather summary information")
  public ActivityWeatherSummary getActivityWeatherSummary(String id, @Nullable Integer startIndex,
      @Nullable Integer endIndex) {
    return intervalsApi.getActivityWeatherSummary(id, startIndex, endIndex).getBody();
  }

  @Tool(name = "getAthlete", description = "Get the athlete with sportSettings and custom_items")
  public WithSportSettings getAthlete(String id) {
    return intervalsApi.getAthlete(id).getBody();
  }

  @Tool(name = "getSupportedAthletes", description = "Get the supported athlete profile, returned as a map of id to profile")
  public Map<String, AthleteProfile> getSupportedAthletes() {
    AthleteProfile profile = intervalsApi.getAthleteProfile(defaultAthleteId).getBody();
    return Map.of(defaultAthleteId, profile);
  }

  @Tool(name = "getAthleteMMPModel", description = "Get the power model used to resolve %MMP steps in workouts for the athlete")
  public PowerModel getAthleteMMPModel(String id, String type) {
    return intervalsApi.getAthleteMMPModel(id, type).getBody();
  }

  @Tool(name = "getAthleteProfile", description = "Get athlete profile info")
  public AthleteProfile getAthleteProfile(String id) {
    return intervalsApi.getAthleteProfile(id).getBody();
  }

  @Tool(name = "getAthleteRoute", description = "Get a route for an athlete")
  public AthleteRoute getAthleteRoute(String id, Long routeId, @Nullable Boolean includePath) {
    return intervalsApi.getAthleteRoute(id, routeId, includePath).getBody();
  }

  @Tool(name = "getAthleteSummary", description = "Summary information for followed athletes")
  public List<SummaryWithCats> getAthleteSummary(String id, String ext, @Nullable String start, @Nullable String end,
      @Nullable List<String> tags) {
    return intervalsApi.getAthleteSummary(id, ext, start, end, tags).getBody();
  }

  @Tool(name = "getAthleteTrainingPlan", description = "Get the athlete's training plan")
  public AthleteTrainingPlan getAthleteTrainingPlan(String id) {
    return intervalsApi.getAthleteTrainingPlan(id).getBody();
  }

  @Tool(name = "getCustomItem", description = "Get a custom item")
  public CustomItem getCustomItem(String id, Integer itemId) {
    return intervalsApi.getCustomItem(id, itemId).getBody();
  }

  @Tool(name = "getForecast", description = "Get weather forecast information")
  public WeatherDTO getForecast(String id) {
    return intervalsApi.getForecast(id).getBody();
  }

  @Tool(name = "getGapHistogram", description = "Get activity gradient adjusted pace histogram")
  public List<Bucket> getGapHistogram(String id) {
    return intervalsApi.getGapHistogram(id).getBody();
  }

  @Tool(name = "getHRHistogram", description = "Get activity heart rate histogram")
  public List<Bucket> getHRHistogram(String id, @Nullable Integer bucketSize) {
    return intervalsApi.getHRHistogram(id, bucketSize).getBody();
  }

  @Tool(name = "getHRTrainingLoadModel", description = "Get activity heart rate training load model")
  public HRLoadModel getHRTrainingLoadModel(String id) {
    return intervalsApi.getHRTrainingLoadModel(id).getBody();
  }

  @Tool(name = "getIntervalStats", description = "Return interval like stats for part of the activity")
  public Interval getIntervalStats(String id, Integer startIndex, Integer endIndex) {
    return intervalsApi.getIntervalStats(id, startIndex, endIndex).getBody();
  }

  @Tool(name = "getIntervals", description = "Get activity intervals")
  public IntervalsDTO getIntervals(String id) {
    return intervalsApi.getIntervals(id).getBody();
  }

  @Tool(name = "getPaceHistogram", description = "Get activity pace histogram")
  public List<Bucket> getPaceHistogram(String id) {
    return intervalsApi.getPaceHistogram(id).getBody();
  }

  @Tool(name = "getPowerHRCurve", description = "Get the athlete's power vs heart rate curve for a date range")
  public PowerHRCurve getPowerHRCurve(String id, String start, String end) {
    return intervalsApi.getPowerHRCurve(id, start, end).getBody();
  }

  @Tool(name = "getPowerHistogram", description = "Get activity power histogram")
  public List<Bucket> getPowerHistogram(String id, @Nullable Integer bucketSize) {
    return intervalsApi.getPowerHistogram(id, bucketSize).getBody();
  }

  @Tool(name = "getPowerVsHR", description = "Get activity power vs heart rate data in JSON or CSV (use .csv ext) format")
  public PowerVsHRPlot getPowerVsHR(String id, String ext) {
    return intervalsApi.getPowerVsHR(id, ext).getBody();
  }

  @Tool(name = "getRecord", description = "Get wellness record for date (local ISO-8601 day)")
  public Wellness getRecord(String id, String date) {
    return intervalsApi.getRecord(id, date).getBody();
  }

  @Tool(name = "getSettings", description = "Get the athlete's settings for phone, tablet or desktop")
  public Map<String, Object> getSettings(String id, String deviceClass) {
    return intervalsApi.getSettings(id, deviceClass).getBody();
  }

  @Tool(name = "getSettings1", description = "Get sport settings by id or activity type e.g. Run, Ride etc.")
  public SportSettings getSettings1(String athleteId, String id) {
    return intervalsApi.getSettings1(athleteId, id).getBody();
  }

  @Tool(name = "getSharedEvent", description = "Get a shared event (e.g. race)")
  public SharedEvent getSharedEvent(Integer id) {
    return intervalsApi.getSharedEvent(id).getBody();
  }

  @Tool(name = "getTimeAtHR", description = "Get activity time at heart rate data")
  public Plot getTimeAtHR(String id) {
    return intervalsApi.getTimeAtHR(id).getBody();
  }

  @Tool(name = "getWeatherConfig", description = "Get the athlete's weather forecast configuration")
  public WeatherConfig getWeatherConfig(String id) {
    return intervalsApi.getWeatherConfig(id).getBody();
  }

  @Tool(name = "importWorkoutFile", description = "Create new workout from .zwo, .mrc, .erg or .fit file in a folder")
  public Workout importWorkoutFile(String id, Integer folderId, String type, @Nullable String athleteId,
      UploadWellnessRequest uploadWellnessRequest) {
    return intervalsApi.importWorkoutFile(id, folderId, type, athleteId, uploadWellnessRequest).getBody();
  }

  @Tool(name = "listActivities", description = "List activities for a date range in desc date order")
  public List<Activity> listActivities(String id, String oldest, @Nullable String newest, @Nullable Long routeId,
      @Nullable Integer limit,
      @Nullable Set<String> fields) {
    return intervalsApi.listActivities(id, oldest, newest, routeId, limit, fields).getBody();
  }

  @Tool(name = "listActivitiesAround", description = "List activities before and after another activity in closest first order")
  public List<Activity> listActivitiesAround(String id, String activityId, @Nullable Long routeId,
      @Nullable Integer limit) {
    return intervalsApi.listActivitiesAround(id, activityId, routeId, limit).getBody();
  }

  @Tool(name = "listActivityHRCurves", description = "Get best HR for a range of durations for matching activities in the date range")
  public ActivityHRCurvePayload listActivityHRCurves(String id, String ext, String oldest, String newest,
      @Nullable List<ActivityFilter> filters, @Nullable List<Integer> secs, @Nullable String type) {
    return intervalsApi.listActivityHRCurves(id, ext, oldest, newest, filters, secs, type).getBody();
  }

  @Tool(name = "listActivityMessages", description = "List all messages (comments) for the activity")
  public List<Message> listActivityMessages(String id, @Nullable Long sinceId, @Nullable Integer limit) {
    return intervalsApi.listActivityMessages(id, sinceId, limit).getBody();
  }

  @Tool(name = "listActivityPaceCurves", description = "Get best pace for a range of distances for matching activities in the date range")
  public void listActivityPaceCurves(String id, String ext, String oldest, String newest, @Nullable String type,
      @Nullable List<ActivityFilter> filters, @Nullable List<Float> distances, @Nullable Boolean gap) {
    intervalsApi.listActivityPaceCurves(id, ext, oldest, newest, type, filters, distances, gap);
  }

  @Tool(name = "listActivityPowerCurves", description = "Get best power for a range of durations for matching activities in the date range")
  public ActivityPowerCurvePayload listActivityPowerCurves(String id, String ext, String oldest, String newest,
      @Nullable List<ActivityFilter> filters, @Nullable List<Integer> secs, @Nullable String type,
      @Nullable String fatigue) {
    return intervalsApi.listActivityPowerCurves(id, ext, oldest, newest, filters, secs, type, fatigue).getBody();
  }

  @Tool(name = "listActivityPowerCurves1", description = "Get activity power curves for one or more streams in JSON or CSV (use .csv ext) format")
  public List<PowerCurve> listActivityPowerCurves1(String id, String ext, @Nullable List<String> types,
      @Nullable List<String> fatigue) {
    return intervalsApi.listActivityPowerCurves1(id, ext, types, fatigue).getBody();
  }

  @Tool(name = "listAthleteHRCurves", description = "List best heart rate curves for the athlete")
  public DataCurveSetHRCurve listAthleteHRCurves(String id, String ext, List<ActivityFilter> f1,
      List<ActivityFilter> f2, List<ActivityFilter> f3, @Nullable String newest, @Nullable List<String> curves,
      @Nullable String type,
      @Nullable Integer subMaxEfforts, @Nullable String now, @Nullable List<ActivityFilter> filters) {
    return intervalsApi
        .listAthleteHRCurves(id, ext, f1, f2, f3, newest, curves, type, subMaxEfforts, now, filters).getBody();
  }

  @Tool(name = "listAthletePaceCurves", description = "List best pace curves for the athlete")
  public DataCurveSetPaceCurve listAthletePaceCurves(String id, String ext, List<ActivityFilter> f1,
      List<ActivityFilter> f2, List<ActivityFilter> f3, @Nullable String newest, @Nullable List<String> curves,
      @Nullable String type,
      @Nullable Boolean includeRanks, @Nullable Integer subMaxEfforts, @Nullable String now, @Nullable Boolean gap,
      @Nullable String pmType,
      @Nullable List<ActivityFilter> filters) {
    return intervalsApi.listAthletePaceCurves(id, ext, f1, f2, f3, newest, curves, type,
        includeRanks, subMaxEfforts, now, gap, pmType, filters).getBody();
  }

  @Tool(name = "listAthletePowerCurves", description = "List best power curves for the athlete")
  public DataCurveSetPowerCurve listAthletePowerCurves(String id, String ext, String type,
      List<ActivityFilter> f1, List<ActivityFilter> f2, List<ActivityFilter> f3, @Nullable String newest,
      @Nullable List<String> curves,
      @Nullable Boolean includeRanks, @Nullable Integer subMaxEfforts, @Nullable String now, @Nullable String pmType,
      @Nullable List<ActivityFilter> filters) {
    return intervalsApi.listAthletePowerCurves(id, ext, type, f1, f2, f3, newest, curves,
        includeRanks, subMaxEfforts, now, pmType, filters).getBody();
  }

  @Tool(name = "listAthleteRoutes", description = "List routes for an athlete with activity counts")
  public List<WithCount> listAthleteRoutes(String id) {
    return intervalsApi.listAthleteRoutes(id).getBody();
  }

  @Tool(name = "listChats", description = "List chats for the athlete, most recently active first")
  public List<Chat> listChats(String id) {
    return intervalsApi.listChats(id).getBody();
  }

  @Tool(name = "listCustomItems", description = "List custom items (charts, custom fields etc.)")
  public List<CustomItem> listCustomItems(String id) {
    return intervalsApi.listCustomItems(id).getBody();
  }

  @Tool(name = "listEvents", description = "List events (planned workouts, notes etc.) on the athlete's calendar, add .csv for CSV output")
  public List<Event> listEvents(String id, String format, @Nullable String oldest, @Nullable String newest,
      @Nullable List<String> category, @Nullable Integer limit, @Nullable Integer calendarId, @Nullable String ext,
      @Nullable Double powerRange,
      @Nullable Double hrRange, @Nullable Double paceRange, @Nullable String locale,
      @Nullable Boolean resolve) {
    return intervalsApi.listEvents(id, format, oldest, newest, category, limit, calendarId, ext,
        powerRange, hrRange, paceRange, locale, resolve).getBody();
  }

  @Tool(name = "listFitnessModelEvents", description = "List events that influence the athlete's fitness calculation in ascending date order")
  public List<Event> listFitnessModelEvents(String id) {
    return intervalsApi.listFitnessModelEvents(id).getBody();
  }

  @Tool(name = "listFolderSharedWith", description = "List athletes that the folder or plan has been shared with")
  public List<SharedWith> listFolderSharedWith(String id, Integer folderId) {
    return intervalsApi.listFolderSharedWith(id, folderId).getBody();
  }

  @Tool(name = "listFolders", description = "List all the athlete's folders, plans and workouts")
  public List<Folder> listFolders(String id) {
    return intervalsApi.listFolders(id).getBody();
  }

  @Tool(name = "listGear", description = "List athlete gear (use .csv for CSV format)")
  public List<Gear> listGear(String id, String ext) {
    return intervalsApi.listGear(id, ext).getBody();
  }

  @Tool(name = "listMatchingActivities", description = "List activities matching the settings")
  public List<ActivityMini> listMatchingActivities(String athleteId, String id) {
    return intervalsApi.listMatchingActivities(athleteId, id).getBody();
  }

  @Tool(name = "listMessages", description = "List messages for the chat, most recent first")
  public List<Message> listMessages(Integer id, @Nullable Long beforeId, @Nullable Integer limit) {
    return intervalsApi.listMessages(id, beforeId, limit).getBody();
  }

  @Tool(name = "listPaceDistances", description = "List pace curve distances")
  public PaceDistancesDTO listPaceDistances() {
    return intervalsApi.listPaceDistances().getBody();
  }

  @Tool(name = "listPaceDistancesForSport", description = "List pace curve distances and best effort defaults for the sport")
  public PaceDistancesDTO listPaceDistancesForSport(String athleteId, String id) {
    return intervalsApi.listPaceDistancesForSport(athleteId, id).getBody();
  }

  @Tool(name = "listSettings", description = "List sport settings for the athlete")
  public List<SportSettings> listSettings(String athleteId) {
    return intervalsApi.listSettings(athleteId).getBody();
  }

  @Tool(name = "listTags", description = "List all tags that have been applied to workouts in the athlete's library")
  public List<String> listTags(String id) {
    return intervalsApi.listTags(id).getBody();
  }

  @Tool(name = "listTags1", description = "List all tags that have been applied to events on the athlete's calendar")
  public List<String> listTags1(String id) {
    return intervalsApi.listTags1(id).getBody();
  }

  @Tool(name = "listTags2", description = "List all tags that have been applied to the athlete's activities")
  public List<String> listTags2(String id) {
    return intervalsApi.listTags2(id).getBody();
  }

  @Tool(name = "listWellnessRecords", description = "List wellness records for date range (use .csv for CSV format)")
  public List<Wellness> listWellnessRecords(String id, String ext, @Nullable String oldest, @Nullable String newest,
      @Nullable List<String> cols,
      @Nullable Set<String> fields) {
    return intervalsApi.listWellnessRecords(id, ext, oldest, newest, cols, fields).getBody();
  }

  @Tool(name = "listWorkouts", description = "List all the workouts in the athlete's library")
  public List<Workout> listWorkouts(String id) {
    return intervalsApi.listWorkouts(id).getBody();
  }

  @Tool(name = "markEventAsDone", description = "Create a manual activity to match a planned workout")
  public Activity markEventAsDone(String id, Integer eventId) {
    return intervalsApi.markEventAsDone(id, eventId).getBody();
  }

  // @Tool(name = "replaceGear", description = "Retire a component and replace it
  // with a copy with the same reminders etc.")
  public List<Gear> replaceGear(String id, String gearId, Gear gear) {
    return intervalsApi.replaceGear(id, gearId, gear).getBody();
  }

  @Tool(name = "searchForActivities", description = "Search for activities by name or tag, returns summary info")
  public List<ActivitySearchResult> searchForActivities(String id, String q, @Nullable Integer limit) {
    return intervalsApi.searchForActivities(id, q, limit).getBody();
  }

  @Tool(name = "searchForActivitiesFull", description = "Search for activities by name or tag, returns full activities")
  public List<Activity> searchForActivitiesFull(String id, String q, @Nullable Integer limit) {
    return intervalsApi.searchForActivitiesFull(id, q, limit).getBody();
  }

  @Tool(name = "searchForIntervals", description = "Find activities with intervals matching duration and intensity")
  public List<Activity> searchForIntervals(String id, Integer minSecs, Integer maxSecs, Integer minIntensity,
      Integer maxIntensity, @Nullable String type, @Nullable Integer minReps, @Nullable Integer maxReps,
      @Nullable Integer limit) {
    return intervalsApi
        .searchForIntervals(id, minSecs, maxSecs, minIntensity, maxIntensity, type, minReps, maxReps, limit).getBody();
  }

  @Tool(name = "sendActivityMessage", description = "Add a message (comment) to an activity")
  public NewMsg sendActivityMessage(String id, NewActivityMsg newActivityMsg) {
    return intervalsApi.sendActivityMessage(id, newActivityMsg).getBody();
  }

  @Tool(name = "sendMessage", description = "Send a message")
  public SendResponse sendMessage(NewMessage newMessage) {
    return intervalsApi.sendMessage(newMessage).getBody();
  }

  @Tool(name = "showChat", description = "Get a chat by id")
  public Chat showChat(Integer id) {
    return intervalsApi.showChat(id).getBody();
  }

  @Tool(name = "showEvent", description = "Get an event")
  public Event showEvent(String id, Integer eventId) {
    return intervalsApi.showEvent(id, eventId).getBody();
  }

  @Tool(name = "showWorkout", description = "Get a workout")
  public Workout showWorkout(String id, Integer workoutId) {
    return intervalsApi.showWorkout(id, workoutId).getBody();
  }

  // @Tool(name = "splitInterval", description = "Split an interval")
  public IntervalsDTO splitInterval(String id, Integer splitAt) {
    return intervalsApi.splitInterval(id, splitAt).getBody();
  }

  // @Tool(name = "updateActivity", description = "Update activity")
  public Activity updateActivity(String id, Activity activity) {
    return intervalsApi.updateActivity(id, activity).getBody();
  }

  // @Tool(name = "updateAthlete", description = "Update an athlete")
  public Athlete updateAthlete(String id, AthleteUpdateDTO athleteUpdateDTO) {
    return intervalsApi.updateAthlete(id, athleteUpdateDTO).getBody();
  }

  // @Tool(name = "updateAthletePlan", description = "Change the athlete's
  // training plan")
  public AthleteTrainingPlan updateAthletePlan(String id, AthleteTrainingPlanUpdate athleteTrainingPlanUpdate) {
    return intervalsApi.updateAthletePlan(id, athleteTrainingPlanUpdate).getBody();
  }

  // @Tool(name = "updateAthletePlans", description = "Change training plans for a
  // list of athletes")
  public Object updateAthletePlans(List<AthleteTrainingPlanUpdate> athleteTrainingPlanUpdate) {
    return intervalsApi.updateAthletePlans(athleteTrainingPlanUpdate).getBody();
  }

  // @Tool(name = "updateAthleteRoute", description = "Update a route for an
  // athlete")
  public AthleteRoute updateAthleteRoute(String id, Long routeId, AthleteRoute athleteRoute) {
    return intervalsApi.updateAthleteRoute(id, routeId, athleteRoute).getBody();
  }

  // @Tool(name = "updateCustomItem", description = "Update a custom item")
  public CustomItem updateCustomItem(String id, Integer itemId, CustomItem customItem) {
    return intervalsApi.updateCustomItem(id, itemId, customItem).getBody();
  }

  // @Tool(name = "updateCustomItemImage", description = "Upload a new image for a
  // custom item as multipart/form-data")
  public CustomItem updateCustomItemImage(String id, Integer itemId,
      UploadWellnessRequest uploadWellnessRequest) {
    return intervalsApi.updateCustomItemImage(id, itemId, uploadWellnessRequest).getBody();
  }

  // @Tool(name = "updateCustomItemIndexes", description = "Re-order custom
  // items")
  public void updateCustomItemIndexes(String id, List<CustomItem> customItem) {
    intervalsApi.updateCustomItemIndexes(id, customItem);
  }

  @Tool(name = "updateEvent", description = "Update an event")
  public Event updateEvent(String id, Integer eventId, EventEx eventEx) {
    return intervalsApi.updateEvent(id, eventId, eventEx).getBody();
  }

  @Tool(name = "updateEvents", description = "Update all events for a date range at once. Only hide_from_athlete and athlete_cannot_edit can be updated")
  public List<Event> updateEvents(String id, String oldest, String newest, Event event) {
    return intervalsApi.updateEvents(id, oldest, newest, event).getBody();
  }

  // @Tool(name = "updateFolder", description = "Update a workout folder or plan")
  public Folder updateFolder(String id, Integer folderId, Folder folder) {
    return intervalsApi.updateFolder(id, folderId, folder).getBody();
  }

  // @Tool(name = "updateFolderSharedWith", description = "Add/remove athletes
  // from the share list for the folder")
  public List<SharedWith> updateFolderSharedWith(String id, Integer folderId, List<SharedWith> sharedWith) {
    return intervalsApi.updateFolderSharedWith(id, folderId, sharedWith).getBody();
  }

  // @Tool(name = "updateGear", description = "Update a gear or component")
  public Gear updateGear(String id, String gearId, Gear gear) {
    return intervalsApi.updateGear(id, gearId, gear).getBody();
  }

  // @Tool(name = "updateInterval", description = "Update/create an interval")
  public IntervalsDTO updateInterval(String id, Integer intervalId, Interval interval) {
    return intervalsApi.updateInterval(id, intervalId, interval).getBody();
  }

  // @Tool(name = "updateIntervals", description = "Update intervals for an
  // activity")
  public IntervalsDTO updateIntervals(String id, List<Interval> interval, @Nullable Boolean all) {
    return intervalsApi.updateIntervals(id, interval, all).getBody();
  }

  // @Tool(name = "updateLastSeenMessageId", description = "Update last seen
  // message for the chat")
  public Object updateLastSeenMessageId(Integer id, Long msgId) {
    return intervalsApi.updateLastSeenMessageId(id, msgId).getBody();
  }

  // @Tool(name = "updatePlanWorkouts", description = "Update a range of workouts
  // on a plan. Currently only hide_from_athlete can be changed")
  public List<Workout> updatePlanWorkouts(String id, Integer folderId, Integer oldest, Integer newest,
      Workout workout) {
    return intervalsApi.updatePlanWorkouts(id, folderId, oldest, newest, workout).getBody();
  }

  @Tool(name = "updateReminder", description = "Update a reminder")
  public Gear updateReminder(String id, String gearId, Integer reminderId, Boolean reset, Integer snoozeDays,
      GearReminder gearReminder) {
    return intervalsApi.updateReminder(id, gearId, reminderId, reset, snoozeDays, gearReminder).getBody();
  }

  // @Tool(name = "updateSettings", description = "Update sport settings by id or
  // activity type e.g. Run, Ride etc.")
  public SportSettings updateSettings(String athleteId, String id, Boolean recalcHrZones,
      SportSettings sportSettings) {
    return intervalsApi.updateSettings(athleteId, id, recalcHrZones, sportSettings).getBody();
  }

  // @Tool(name = "updateSettingsMulti", description = "Update multiple sport
  // settings")
  public List<SportSettings> updateSettingsMulti(String athleteId, Boolean recalcHrZones,
      List<SportSettings> sportSettings) {
    return intervalsApi.updateSettingsMulti(athleteId, recalcHrZones, sportSettings).getBody();
  }

  @Tool(name = "updateSharedEvent", description = "Update a shared event (e.g. race)")
  public SharedEvent updateSharedEvent(Integer id, SharedEvent sharedEvent) {
    return intervalsApi.updateSharedEvent(id, sharedEvent).getBody();
  }

  // @Tool(name = "updateWeatherConfig", description = "Update the athlete's
  // weather forecast configuration")
  public WeatherConfig updateWeatherConfig(String id, WeatherConfig weatherConfig) {
    return intervalsApi.updateWeatherConfig(id, weatherConfig).getBody();
  }

  @Tool(name = "updateWellness", description = "Update the wellness record for the date (ISO-8601)")
  public Wellness updateWellness(String id, String date, Wellness wellness) {
    return intervalsApi.updateWellness(id, date, wellness).getBody();
  }

  // @Tool(name = "updateWellness1", description = "Update a wellness record, id
  // is the day (ISO-8601)")
  public Wellness updateWellness1(String id, Wellness wellness) {
    return intervalsApi.updateWellness1(id, wellness).getBody();
  }

  @Tool(name = "updateWellnessBulk", description = "Update an array of wellness records all for the same athlete")
  public void updateWellnessBulk(String id, List<Wellness> wellness) {
    intervalsApi.updateWellnessBulk(id, wellness);
  }

  @Tool(name = "updateWorkout", description = "Update a workout")
  public Workout updateWorkout(String id, Integer workoutId, WorkoutEx workoutEx) {
    return intervalsApi.updateWorkout(id, workoutId, workoutEx).getBody();
  }

  @Tool(name = "uploadActivity", description = "Create new activities from an uploaded file")
  public UploadResponse uploadActivity(String id, @Nullable String name, @Nullable String description,
      @Nullable String deviceName,
      @Nullable String externalId, @Nullable Integer pairedEventId) {
    return intervalsApi.uploadActivity(id, name, description, deviceName, externalId, pairedEventId).getBody();
  }

  // @Tool(name = "uploadActivityStreamsCSV", description = "Update streams for
  // the activity from CSV")
  public UpdateStreamsResult uploadActivityStreamsCSV(String id) {
    return intervalsApi.uploadActivityStreamsCSV(id).getBody();
  }

  @Tool(name = "uploadWellness", description = "Upload wellness records in CSV format as multipart/form-data")
  public Object uploadWellness(String id, @Nullable Boolean ignoreMissingFields,
      UploadWellnessRequest uploadWellnessRequest) {
    return intervalsApi.uploadWellness(id, ignoreMissingFields, uploadWellnessRequest).getBody();
  }

}
