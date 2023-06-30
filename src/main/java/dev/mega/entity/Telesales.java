package dev.mega.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telesales {

    @Id
    @SequenceGenerator(name = "telesales_seq", sequenceName = "telesales_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telesales_seq")
    private Long id;

    @Column(columnDefinition = "VARCHAR(30)", unique = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(6)")
    private String code;

    @ElementCollection
    private Set<String> workingPeriods;

    @ElementCollection
    private Set<String> workingRegions;

    @OneToMany(mappedBy = "telesales")
    private List<CallsReport> callsReports;

    @OneToMany(mappedBy = "telesales")
    private List<BillsReport> billsReports;

}
