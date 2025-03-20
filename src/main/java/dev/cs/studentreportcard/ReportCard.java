package dev.cs.studentreportcard;
import dev.cs.studentreportcard.utility.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ReportCard {
    public static void main(String[] args) {
        System.out.println("[School Management & Recording System Application is loading ..........] " + Util.orderDate());
        SpringApplication.run(ReportCard.class, args);
    }
}