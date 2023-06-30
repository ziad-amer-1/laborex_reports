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
@Table(name = "bills_report")
public class BillsReport {

    @Id
    @SequenceGenerator(name = "bill_seq", sequenceName = "bill_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")
    private Long id;

    private Integer numberOfClients;

    private Integer numberOfBills;

    private Double totalSale;

    @ManyToOne
    @JoinColumn(name = "telesales_name", referencedColumnName = "name")
    private Telesales telesales;

    private LocalDate date;

}
