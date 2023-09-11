package com.utn.practicopersistencia.entidades;

import com.utn.practicopersistencia.enums.Estado;
import com.utn.practicopersistencia.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pedido")
public class Pedido extends BaseEntidad {

    @Column(name = "fecha")
    private String fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Column(name = "hora_estimada_entrega")
    private LocalTime horaEstimadaEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_envio")
    private TipoEnvio tipoEnvio;

    @Column(name = "total")
    private double total;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detallepedido")
    private List<DetallePedido> detallesPedidos;

}
