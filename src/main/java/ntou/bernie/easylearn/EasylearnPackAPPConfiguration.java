package ntou.bernie.easylearn;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author bernie Get Configuration from file
 */
public class EasylearnPackAPPConfiguration extends Configuration {
    @Valid
    @NotNull
    private DatabaseConfiguration databaseConfiguration;
    @Valid
    @NotNull
    private String noteServicePort;
    @Valid
    @NotNull
    private String userServicePort;
    @Valid
    @NotNull
    private String host;

    /**
     * @return the databaseConfiguration
     */
    @JsonProperty("mongoDB")
    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    /**
     * @param databaseConfiguration the databaseConfiguration to set
     */
    @JsonProperty("mongoDB")
    public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public String getNoteServicePort() {
        return noteServicePort;
    }

    public void setNoteServicePort(String noteServicePort) {
        this.noteServicePort = noteServicePort;
    }

    public String getUserServicePort() {
        return userServicePort;
    }

    public void setUserServicePort(String userServicePort) {
        this.userServicePort = userServicePort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
