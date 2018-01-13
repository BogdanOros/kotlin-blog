import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author: orossbogdan@gmail.com (Bogdan Oros)
 * @date: 14.01.18
 */

@SpringBootApplication
class BlogApplication

fun main(args: Array<String>) {
    SpringApplication.run(BlogApplication::class.java, *args)
}