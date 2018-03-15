package com.github.fonimus.ssh.shell.complete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.fonimus.ssh.shell.SshShellHelper;

public class DemoCommandTest {

	private static DemoCommand cmd;

	@BeforeAll
	static void prepare() {
		cmd = new DemoCommand(new SshShellHelper(null));
	}

	@Test
	void testCommandEcho() {
		Assertions.assertEquals("message", cmd.echo("message"));
	}

	@Test
	void testCommandEx() {
		IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> cmd.ex());
		Assertions.assertEquals("Test exception message", ex.getMessage());
	}

	@Test
	void testLog() {
		cmd.log();
	}
}
