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

import com.github.fonimus.ssh.shell.conf.SshShellSecurityConfigurationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.fonimus.ssh.shell.SshHelperTest.call;
import static com.github.fonimus.ssh.shell.SshHelperTest.verifyResponse;
import static com.github.fonimus.ssh.shell.SshHelperTest.write;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {SshShellApplicationSecurityTest.class, SshShellSecurityConfigurationTest.class},
        properties = {
                "ssh.shell.port=2346",
                "ssh.shell.password=pass",
                "ssh.shell.authentication=security",
                "management.endpoints.web.exposure.include=*"
        }
)
@ExtendWith(SpringExtension.class)
@SpringBootApplication
@DirtiesContext
public class SshShellApplicationSecurityTest
        extends AbstractTest {

    @Test
    void testSshCallInfoCommandAdmin() {
        call("admin", "admin", properties, (is, os) -> {
            write(os, "info");
            verifyResponse(is, "{}");
        });
    }

    @Test
    void testSshCallInfoCommandUser() {
        call("user", "password", properties, (is, os) -> {
            write(os, "health");
            verifyResponse(is, "forbidden for current user");
        });
    }
}
