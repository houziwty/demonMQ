package demonmq.rmq;

import com.sun.istack.internal.NotNull;

/**
 * Created by Demon on 2017/6/16.
 * rabbitMQ连接需要的信息
 */
public class RabbitConnectionBean {
    private String host;

    private Integer port;

    private String vHost;

    private String username;

    private String password;

    public RabbitConnectionBean(@NotNull String host, @NotNull Integer port,@NotNull String vHost,@NotNull String username,@NotNull String password) {
        this.host = host;
        this.port = port;
        this.vHost = vHost;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getvHost() {
        return vHost;
    }

    public void setvHost(String vHost) {
        this.vHost = vHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "RabbitConnectionBean{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", vHost='" + vHost + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RabbitConnectionBean bean = (RabbitConnectionBean) o;

        if (!host.equals(bean.host)) return false;
        if (!port.equals(bean.port)) return false;
        if (!vHost.equals(bean.vHost)) return false;
        if (!username.equals(bean.username)) return false;
        return password.equals(bean.password);
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + port.hashCode();
        result = 31 * result + vHost.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
