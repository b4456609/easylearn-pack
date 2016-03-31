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
    private String appName;
    @Valid
    @NotNull
    private DatabaseConfiguration databaseConfiguration;
    @Valid
    @NotNull
    private String noteServiceHost;
    @Valid
    @NotNull
    private String userServiceHost;

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

    /**
     * @return the appName
     */
    @JsonProperty
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    @JsonProperty
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @JsonProperty
	public String getNoteServiceHost() {
		return noteServiceHost;
	}

    @JsonProperty
	public void setNoteServiceHost(String noteServiceHost) {
		this.noteServiceHost = noteServiceHost;
	}

    @JsonProperty
	public String getUserServiceHost() {
		return userServiceHost;
	}

    @JsonProperty
	public void setUserServiceHost(String userServiceHost) {
		this.userServiceHost = userServiceHost;
	}
}
