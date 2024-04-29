package pl.edu.pw.cinemasterbe.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2Configuration {
    @Value("${h2.tcp.port}")
    private String tcpPort;
    @Value("${h2.web.port}")
    private String webPort;

    @Bean
    @ConditionalOnExpression("${h2.tcp.enabled:false}")
    public Server tcpServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", tcpPort).start();
    }

    @Bean
    @ConditionalOnExpression("${h2.web.enabled:false}")
    public Server webServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", webPort).start();
    }
}
