/*
 * Copyright (c) 2020 François Onimus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fonimus.ssh.shell;

import com.github.fonimus.ssh.shell.commands.CommandProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.github.fonimus.ssh.shell.SshShellProperties.SSH_SHELL_PREFIX;
import static com.github.fonimus.ssh.shell.commands.DatasourceCommand.COMMAND_DATA_SOURCE_UPDATE;

/**
 * Ssh shell properties (prefix : {@link SshShellProperties#SSH_SHELL_PREFIX})
 */
@Data
@ConfigurationProperties(prefix = SSH_SHELL_PREFIX)
@Validated
public class SshShellProperties {

    public static final String SSH_SHELL_PREFIX = "ssh.shell";

    public static final String SSH_SHELL_ENABLE = SSH_SHELL_PREFIX + ".enable";

    public static final String DISABLE_SSH_SHELL = SSH_SHELL_ENABLE + "=false";

    public static final String SPRING_SHELL_AUTO_CONFIG_CLASSES = "org.springframework.shell.jline" +
            ".JLineShellAutoConfiguration," +
            "org.springframework.shell.SpringShellAutoConfiguration," +
            "org.springframework.shell.jcommander.JCommanderParameterResolverAutoConfiguration," +
            "org.springframework.shell.legacy.LegacyAdapterAutoConfiguration," +
            "org.springframework.shell.standard.StandardAPIAutoConfiguration," +
            "org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration";

    public static final String DISABLE_SPRING_SHELL_AUTO_CONFIG =
            "spring.autoconfigure.exclude=" + SPRING_SHELL_AUTO_CONFIG_CLASSES;

    public static final String ACTUATOR_ROLE = "ACTUATOR";

    public static final String ADMIN_ROLE = "ADMIN";

    private boolean enable = true;

    private String host = "127.0.0.1";

    private int port = 2222;

    private String user = "user";

    private String password;

    private boolean displayBanner = true;

    private boolean extendedFileProvider = true;

    private AuthenticationType authentication = AuthenticationType.simple;

    private String authProviderBeanName;

    private File hostKeyFile = new File(System.getProperty("java.io.tmpdir"), "hostKey.ser");

    private File authorizedPublicKeysFile;

    private File historyFile = new File(System.getProperty("java.io.tmpdir"), "sshShellHistory.log");

    private boolean sharedHistory = true;

    /**
     * Note: only used if @link {@link SshShellProperties#sharedHistory}} set to false
     */
    private File historyDirectory = new File(System.getProperty("java.io.tmpdir"));

    private List<String> confirmationWords;

    public enum AuthenticationType {
        simple, security
    }

    private Prompt prompt = new Prompt();

    private Commands commands = new Commands();

    /**
     * Prompt configuration
     */
    @Data
    public static class Prompt {

        private String text = "shell>";

        private PromptColor color = PromptColor.WHITE;

        private Local local = new Local();
    }

    /**
     * Prompt local configuration
     */
    @Data
    public static class Local {

        private boolean enable;
    }

    /**
     * Commands configuration
     */
    @Data
    public static class Commands {

        @NestedConfigurationProperty
        private CommandProperties actuator = CommandProperties.withAuthorizedRoles(Arrays.asList(ACTUATOR_ROLE));

        @NestedConfigurationProperty
        private CommandProperties jmx = new CommandProperties();

        @NestedConfigurationProperty
        private CommandProperties jvm = new CommandProperties();

        @NestedConfigurationProperty
        private CommandProperties datasource =
                CommandProperties.withExcludedByDefault(Arrays.asList(COMMAND_DATA_SOURCE_UPDATE));

        @NestedConfigurationProperty
        private CommandProperties postprocessors = CommandProperties.notRestrictedByDefault();

        @NestedConfigurationProperty
        private CommandProperties threads = new CommandProperties();

        @NestedConfigurationProperty
        private CommandProperties manageSessions = CommandProperties.disabledByDefault();
    }

}
