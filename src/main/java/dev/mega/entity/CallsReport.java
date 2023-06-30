package dev.mega.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calls_report")
public class CallsReport {

    @Id
    @SequenceGenerator(name = "calls_seq", sequenceName = "calls_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calls_seq")
    private Long id;

    private Integer totalCalls;

    private Integer positiveCalls;

    @ManyToOne
    @JoinColumn(name = "telesales_name", referencedColumnName = "name")
    private Telesales telesales;

    private LocalDate date;

}
