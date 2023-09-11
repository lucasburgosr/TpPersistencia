package com.utn.practicopersistencia.entidades;

import com.utn.practicopersistencia.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "producto")
public class Producto extends BaseEntidad{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipo tipo;

    @Column(name = "tiempo_estimado_cocina")
    private int tiempoEstimadoCocina;

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "stock_actual")
    private int stockActual;

    @Column(name = "stock_minimo")
    private int stockMinimo;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "foto")
    private String foto;

    @Column(name = "receta")
    private String receta;

}
