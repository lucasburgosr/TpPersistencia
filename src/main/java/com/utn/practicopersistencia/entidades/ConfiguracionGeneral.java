package com.utn.practicopersistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "configuracion_general")
public class ConfiguracionGeneral extends BaseEntidad{

    @Column(name = "cantidad_cocineros")
    private int cantidadCocineros;

    @Column(name = "email_empresa")
    private String emailEmpresa;

    @Column(name = "token_mercadopago")
    private String tokenMercadoPago;

}
