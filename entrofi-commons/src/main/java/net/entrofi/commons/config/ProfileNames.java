
package net.entrofi.commons.config;

/**
 * Pofiles names to use for different environments.
 * Created on 22/03/2016.
 */
public final class ProfileNames {

    public static final String APPLICATION_PREFIX = "";

    public static final String UNIT_TEST = APPLICATION_PREFIX + "unit-test";

    public static final String INTEGRATION_TEST = APPLICATION_PREFIX + "int-test";

    public static final String PRODUCTION = APPLICATION_PREFIX + "prod";

    public static final String DEVELOPMENT = APPLICATION_PREFIX + "dev";

    public static final String QATEST = APPLICATION_PREFIX + "qa-test";

    private ProfileNames() { }
}
