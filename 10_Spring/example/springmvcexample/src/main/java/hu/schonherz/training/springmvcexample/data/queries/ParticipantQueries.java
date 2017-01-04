package hu.schonherz.training.springmvcexample.data.queries;

public final class ParticipantQueries {

    private static final String SCHEMA_NAME = "promotion_tracker";
    private static final String PARTICIPANT_TABLE = SCHEMA_NAME + ".participant";

    public static final String QUERY_FIND_ALL = "SELECT * FROM " + PARTICIPANT_TABLE;
    public static final String QUERY_FIND_BY_ID = "SELECT * FROM " + PARTICIPANT_TABLE + " WHERE id = ?";
    public static final String QUERY_SAVE = "INSERT INTO " + PARTICIPANT_TABLE + " (name, email) VALUES (?, ?)";

    private ParticipantQueries() {}
}
