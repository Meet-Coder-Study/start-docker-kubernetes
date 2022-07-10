import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig
import com.spotify.docker.client.messages.PortBinding

fun main() {
    val dc = DefaultDockerClient.builder()
        .uri("http://localhost:2375")
        .build()

    val hostPort = listOf(
        PortBinding.of("0.0.0.0", "10080")
    )

    val portBindings = hashMapOf("80/tcp" to hostPort)

    val hostConfig = HostConfig.builder()
        .portBindings(portBindings)
        .build()

    val container = dc.createContainer(ContainerConfig.builder()
        .image("nginx")
        .hostConfig(hostConfig)
        .attachStderr(false).attachStdin(false).attachStdout(false)
        .build(), "mynginx")

    dc.startContainer(container.id())

    // console 에서 확인
    // docker -H tcp://localhost:2375 container ls -a
}