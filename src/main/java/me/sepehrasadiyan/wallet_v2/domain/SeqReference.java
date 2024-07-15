package me.sepehrasadiyan.wallet_v2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Table(name = "simple_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeqReference {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ref_seq")
    @SequenceGenerator(name = "ref_seq", sequenceName = "reference_number_seq", allocationSize = 1)
    private Long id;
}
