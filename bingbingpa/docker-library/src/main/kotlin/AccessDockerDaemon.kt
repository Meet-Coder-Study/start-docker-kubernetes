import com.spotify.docker.client.DefaultDockerClient

fun main() {
    DefaultDockerClient("http://localhost:2375").use {defaultDockerClient->
        println("defaultDockerClient = ${defaultDockerClient.info()}")
    }
}
