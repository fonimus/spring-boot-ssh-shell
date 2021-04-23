# Usage

1. Build sample application

    ```bash
    mvn clean install -f samples/complete [-DskipTests]
    ```
1. Start application

    ```bash
    java -jar samples/complete/target/ssh-shell-spring-boot-complete-sample[-version].jar
    ```
1. Connect to application via ssh (default password: pass)

    ```bash
    ~/home$ ssh -p 2222 [user|actuator|admin]@localhost
    Password authentication
    Password: [password]
    
            _         _        _ _
      _____| |_    __| |_  ___| | |
     (_-<_-< ' \  (_-< ' \/ -_) | |
     /__/__/_||_| /__/_||_\___|_|_| v1.5.0-SNAPSHOT
    
    
    Please type `help` to see available commands
    complete::>help
    AVAILABLE COMMANDS
    
    Actuator Commands
          * audit: Display audit endpoint.
            beans: Display beans endpoint.
            conditions: Display conditions endpoint.
            configprops: Display configprops endpoint.
            env: Display env endpoint.
            health: Display health endpoint.
          * httptrace: Display httptrace endpoint.
            info: Display info endpoint.
            loggers: Display or configure loggers.
            mappings: Display mappings endpoint.
            metrics: Display metrics endpoint.
            scheduledtasks: Display scheduledtasks endpoint.
          * sessions: Display sessions endpoint.
            shutdown: Shutdown application.
          * threaddump: Display threaddump endpoint.
    
    Built-In Commands
            clear: Clear the shell screen.
            exit, quit: Exit the shell.
            help: Display help about available commands.
            history: Display or save the history of previously run commands
            postprocessors: Display the available post processors
            script: Read and execute commands from a file.
            stacktrace: Display the full stacktrace of the last error.
    
    Datasource Commands
            datasource-list: List available datasources
            datasource-properties: Datasource properties command. Executes 'show variables'
            datasource-query: Datasource query command.
            datasource-update: Datasource update command.
    
    Demo Command
            admin: Admin command
            authentication: Authentication command
            conf: Confirmation command
            display-ssh-env: Displays ssh env information
            display-ssh-session: Displays ssh session information
            echo: Echo command
            ex: Ex command
            file: File command
            interactive: Interactive command
            progress: Progress command
            size: Terminal size command
            welcome: Welcome command
    
    Jmx Commands
            jmx-info: Displays information about jmx mbean. Use -a option to query attribute values.
            jmx-invoke: Invoke operation on object name.
            jmx-list: List jmx mbeans.
    
    Manage Sessions Commands
            manage-sessions-info: Displays session
            manage-sessions-list: Displays active sessions
            manage-sessions-stop: Stop session
    
    System Commands
            system-env: List system environment.
            system-properties: List system properties.
            system-threads: Thread command.
    
    Tasks Commands
            tasks-list: Display the available scheduled tasks
            tasks-restart: Restart all or specified task(s)
            tasks-stop: Stop all or specified task(s)
    
    Commands marked with (*) are currently unavailable.
    Type `help <command>` to learn more.
    ```
