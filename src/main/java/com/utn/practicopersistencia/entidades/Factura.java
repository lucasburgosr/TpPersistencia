package com.utn.practicopersistencia.entidades;

import com.utn.practicopersistencia.enums.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "factura")
public class Factura extends BaseEntidad {

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "numero")
    private int numero;

    @Column(name = "descuento")
    private Double descuento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pago")
    private FormaPago formaPago;

    @Column(name = "total")
    private int total;

}
